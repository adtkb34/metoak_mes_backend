import { Controller, Get } from '@nestjs/common';
import { DashboardService, ProductOption } from './dashboard.service';

@Controller('dashboard')
export class DashboardController {
  constructor(private readonly dashboardService: DashboardService) {}

  @Get('product-options')
  async getProductOptions(): Promise<ProductOption[]> {
    return this.dashboardService.getProductOptions();
  }
}
