import { Injectable } from '@nestjs/common';
import { Prisma, PrismaClient } from '@prisma/client';

export interface ProductOption {
  readonly id: number;
  readonly name: string;
}

@Injectable()
export class DashboardService {
  constructor(private readonly prisma: PrismaClient) {}

  async getProductOptions(): Promise<ProductOption[]> {
    const rawOptions = await this.prisma.$queryRaw<ProductOption[]>(Prisma.sql`
      SELECT id, name
      FROM product
      ORDER BY name ASC
    `);

    return rawOptions.map((option) => ({ ...option }));
  }

  private buildWhereClause(conditions: Prisma.Sql[]): Prisma.Sql {
    if (!conditions.length) {
      return Prisma.empty;
    }

    return Prisma.sql`WHERE ${Prisma.join(conditions, ' AND ')}`;
  }

  private normalizeDate(kind: 'start' | 'end', value?: string): string | undefined {
    if (!value) {
      return undefined;
    }

    const parsed = new Date(value);
    if (Number.isNaN(parsed.getTime())) {
      return undefined;
    }

    if (kind === 'start') {
      parsed.setUTCHours(0, 0, 0, 0);
    } else {
      parsed.setUTCHours(23, 59, 59, 999);
    }

    return parsed.toISOString();
  }
}
