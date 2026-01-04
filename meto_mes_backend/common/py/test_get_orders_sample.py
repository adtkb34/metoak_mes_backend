import os
import json
import sys
import io

sys.stdout = io.TextIOWrapper(sys.stdout.buffer, encoding='utf-8')
from k3cloud_webapi_sdk.main import K3CloudApiSdk

api_sdk = K3CloudApiSdk("http://10.10.10.18/k3cloud/")

api_sdk.Init(config_path=f'{ os.path.dirname(os.path.abspath(__file__))}/conf.ini', config_node='config')



def PRD_MO_ExecuteBillQuery():
    # 查询条件 - 需要根据实际业务调整
    customer_doc_no = "XSCKD001410"
    filter_data = {
        "FormId": "SAL_SaleOrder",  # 销售订单表单ID
        "FilterString": f"FBillNo='{customer_doc_no}'",
        "FieldKeys": "FBillNo,FID,FCustomerID,FCustomerName"
    }
    para = {
        "FormId": "PRD_MO",
        "FieldKeys": "FBillNo,FMaterialID.FName,FQty, FPlanStartDate, FPlanFinishDate,FMaterialID.FNumber, FStatus",
        "FilterString": "FPrdOrgId.FNumber='Metoak-KXJA'",
    }
#     if kwargs:
#         para.update(kwargs)
    response = api_sdk.ExecuteBillQuery(filter_data)
    # response = api_sdk.ExecuteBillQuery(para)
    print(response)
    res = json.loads(response)
    if len(res) > 0:
        return True
    return False


def query_related_work_orders(source_bill_no):
    """
    根据源单据编号查询关联的工单

    :param source_bill_no: 源单据编号 (例如：销售订单号 'SO20231120001')
    :return: 关联的工单列表
    """
    try:
        # 设置查询参数（仿照PRD_MO_ExecuteBillQuery实现）
        para = {
            "FormId": "PRD_MO",  # 生产订单（工单）的表单ID
            "FieldKeys": "FBillNo,FMaterialID.FName,FQty,FPlanStartDate,FPlanFinishDate,FMaterialID.FNumber,FStatus",  # 需要返回的字段
            "FilterString": f"FSrcBillNo='{source_bill_no}'"  # 过滤条件：根据源单编号过滤
        }

        # 执行查询（使用ExecuteBillQuery方法，与PRD_MO_ExecuteBillQuery保持一致）
        response = api_sdk.ExecuteBillQuery(para)
        print(response)

        # 解析JSON响应
        res = json.loads(response)

        if len(res) > 0:
            print(f"成功查询到 {len(res)} 个关联工单")
            return res
        else:
            print("未查询到关联工单")
            return []

    except Exception as e:
        print(f"操作过程中出现异常: {e}")
        return []


def get_all_customer_orders_with_work_orders():
    """
    获取金蝶所有客户订单，并尝试解析订单关联的工单

    :return: 包含订单及其关联工单信息的列表
    """
    try:
        # 第一步：查询所有销售订单（客户订单）
        print("开始查询所有客户订单...")
        order_para = {
            "FormId": "SAL_SaleOrder",  # 销售订单表单ID
            "FieldKeys": "FBillNo,FID,FCustomerID.FName,FDate,FStatus",  # 订单号、ID、客户名称、日期、状态
            # 可以添加过滤条件，例如只查询特定状态的订单
            # "FilterString": "FStatus='C'"  # C表示已审核状态
        }

        order_response = api_sdk.ExecuteBillQuery(order_para)
        orders = json.loads(order_response)

        if len(orders) == 0:
            print("未查询到任何客户订单")
            return []

        print(f"成功查询到 {len(orders)} 个客户订单")

        # 第二步：为每个订单查询关联的工单
        result = []
        for order in orders:
            order_no = order[0] if isinstance(order, list) else order.get('FBillNo', '')
            print(f"\n正在处理订单: {order_no}")

            # 构造订单详细信息
            order_info = {
                "order_no": order[0] if isinstance(order, list) and len(order) > 0 else '',
                "order_id": order[1] if isinstance(order, list) and len(order) > 1 else '',
                "customer_name": order[2] if isinstance(order, list) and len(order) > 2 else '',
                "order_date": order[3] if isinstance(order, list) and len(order) > 3 else '',
                "status": order[4] if isinstance(order, list) and len(order) > 4 else '',
                "work_orders": []
            }

            # 查询该订单关联的工单
            if order_no:
                work_orders = query_related_work_orders(order_no)
                if work_orders:
                    # 解析工单信息
                    for wo in work_orders:
                        work_order_info = {
                            "work_order_no": wo[0] if isinstance(wo, list) and len(wo) > 0 else '',
                            "material_name": wo[1] if isinstance(wo, list) and len(wo) > 1 else '',
                            "qty": wo[2] if isinstance(wo, list) and len(wo) > 2 else 0,
                            "plan_start_date": wo[3] if isinstance(wo, list) and len(wo) > 3 else '',
                            "plan_finish_date": wo[4] if isinstance(wo, list) and len(wo) > 4 else '',
                            "material_number": wo[5] if isinstance(wo, list) and len(wo) > 5 else '',
                            "status": wo[6] if isinstance(wo, list) and len(wo) > 6 else ''
                        }
                        order_info["work_orders"].append(work_order_info)

                    print(f"  - 找到 {len(work_orders)} 个关联工单")
                else:
                    print(f"  - 未找到关联工单")

            result.append(order_info)

        print(f"\n处理完成！共处理 {len(result)} 个订单")

        # 输出汇总信息
        total_work_orders = sum(len(order['work_orders']) for order in result)
        print(f"总共找到 {total_work_orders} 个关联工单")

        # 保存结果到JSON文件（可选）
        output_file = f"{os.path.dirname(os.path.abspath(__file__))}/customer_orders_with_work_orders.json"
        with open(output_file, 'w', encoding='utf-8') as f:
            json.dump(result, f, ensure_ascii=False, indent=2)
        print(f"\n结果已保存到: {output_file}")

        return result

    except Exception as e:
        print(f"获取客户订单及工单时出现异常: {e}")
        import traceback
        traceback.print_exc()
        return []


if __name__ == "__main__":
    # 测试：获取所有客户订单及关联工单
    get_all_customer_orders_with_work_orders()

    # 其他测试用例（可选）
    # query_related_work_orders('XSDD001156')
    # PRD_MO_ExecuteBillQuery()
