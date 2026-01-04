import os
import json
import sys
import io

sys.stdout = io.TextIOWrapper(sys.stdout.buffer, encoding='utf-8')
from k3cloud_webapi_sdk.main import K3CloudApiSdk

api_sdk = K3CloudApiSdk("http://10.10.10.18/k3cloud/")
api_sdk.Init(config_path=f'{ os.path.dirname(os.path.abspath(__file__))}/conf.ini', config_node='config')

def get_field_names(form_id):
    """
    获取表单的字段名，用于调试
    """
    try:
        para = {
            "FormId": form_id,
            "FieldKeys": ""  # 不指定字段，让系统返回所有可用字段或错误信息
        }
        response = api_sdk.ExecuteBillQuery(para)
        print(f"表单 {form_id} 的字段测试响应: {response}")
    except Exception as e:
        print(f"获取字段名时出错: {e}")

def query_related_work_orders(source_bill_no):
    """
    根据源单据编号查询关联的工单
    """
    try:
        para = {
            "FormId": "PRD_MO",
            "FieldKeys": "FBillNo,FMaterialID.FName,FQty,FPlanStartDate,FPlanFinishDate,FMaterialID.FNumber,FStatus",
            "FilterString": f"FSrcBillNo='{source_bill_no}'"
        }

        response = api_sdk.ExecuteBillQuery(para)
        print(f"工单查询响应: {response}")

        res = json.loads(response)

        # 检查响应结构
        if isinstance(res, dict) and 'Result' in res:
            # 可能是错误响应
            if 'ResponseStatus' in res['Result'] and not res['Result']['ResponseStatus']['IsSuccess']:
                print(f"工单查询失败: {res['Result']['ResponseStatus']['Errors']}")
                return []

        if len(res) > 0:
            print(f"成功查询到 {len(res)} 个关联工单")
            return res
        else:
            print("未查询到关联工单")
            return []

    except Exception as e:
        print(f"查询工单时出现异常: {e}")
        import traceback
        traceback.print_exc()
        return []

def get_all_customer_orders_with_work_orders():
    """
    获取金蝶所有客户订单，并尝试解析订单关联的工单
    """
    try:
        # 先调试一下字段名
        print("正在调试销售订单字段...")
        get_field_names("SAL_SaleOrder")

        # 使用更通用的字段查询
        print("开始查询所有客户订单...")
        order_para = {
            "FormId": "SAL_SaleOrder",
            "FieldKeys": "FBillNo,FID",  # 先用最基础的字段测试
            # "FilterString": "FDocumentStatus='C'"  # 已审核状态
        }

        order_response = api_sdk.ExecuteBillQuery(order_para)
        print(f"订单查询原始响应: {order_response}")

        orders = json.loads(order_response)

        # 检查响应结构
        if isinstance(orders, dict) and 'Result' in orders:
            if 'ResponseStatus' in orders['Result'] and not orders['Result']['ResponseStatus']['IsSuccess']:
                print(f"订单查询失败: {orders['Result']['ResponseStatus']['Errors']}")
                return []
            # 如果成功，尝试获取实际数据
            if 'Result' in orders['Result']:
                orders = orders['Result']['Result']

        if len(orders) == 0:
            print("未查询到任何客户订单")
            return []

        print(f"成功查询到 {len(orders)} 个客户订单")
        print(f"第一个订单样例: {orders[0]}")

        # 处理订单数据
        result = []
        for order in orders:
            # 根据实际响应结构解析订单号
            if isinstance(order, list):
                order_no = order[0] if len(order) > 0 else ''
            elif isinstance(order, dict):
                order_no = order.get('FBillNo', '')
            else:
                order_no = str(order)

            print(f"\n正在处理订单: {order_no}")

            order_info = {
                "order_no": order_no,
                "work_orders": []
            }

            # 查询关联工单
            if order_no:
                work_orders = query_related_work_orders(order_no)
                if work_orders:
                    for wo in work_orders:
                        if isinstance(wo, list):
                            work_order_info = {
                                "work_order_no": wo[0] if len(wo) > 0 else '',
                                "material_name": wo[1] if len(wo) > 1 else '',
                                "qty": wo[2] if len(wo) > 2 else 0,
                                "plan_start_date": wo[3] if len(wo) > 3 else '',
                                "plan_finish_date": wo[4] if len(wo) > 4 else '',
                                "material_number": wo[5] if len(wo) > 5 else '',
                                "status": wo[6] if len(wo) > 6 else ''
                            }
                        else:
                            work_order_info = {
                                "work_order_no": wo.get('FBillNo', ''),
                                "material_name": wo.get('FMaterialID.FName', ''),
                                "qty": wo.get('FQty', 0),
                                "plan_start_date": wo.get('FPlanStartDate', ''),
                                "plan_finish_date": wo.get('FPlanFinishDate', ''),
                                "material_number": wo.get('FMaterialID.FNumber', ''),
                                "status": wo.get('FStatus', '')
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

        # 保存结果
        output_file = f"{os.path.dirname(os.path.abspath(__file__))}/customer_orders_with_work_orders.json"
        with open(output_file, 'w', encoding='utf-8') as f:
            json.dump(result, f, ensure_ascii=False, indent=2)
        print(f"结果已保存到: {output_file}")

        return result

    except Exception as e:
        print(f"获取客户订单及工单时出现异常: {e}")
        import traceback
        traceback.print_exc()
        return []

def debug_form_fields():
    """
    调试函数：获取销售订单和工单的可用字段
    """
    print("=== 调试销售订单字段 ===")

    # 测试销售订单字段
    test_fields = [
        "FBillNo", "FID", "FCustId", "FCustomerID",
        "FCustomer", "FCustName", "FCustomerName",
        "FDate", "FDocumentStatus", "FStatus"
    ]

    for field in test_fields:
        try:
            para = {
                "FormId": "SAL_SaleOrder",
                "FieldKeys": field,
                "TopRowCount": 1
            }
            response = api_sdk.ExecuteBillQuery(para)
            result = json.loads(response)
            if isinstance(result, list) and len(result) > 0:
                print(f"✓ 字段 {field} 可用")
            else:
                print(f"✗ 字段 {field} 不可用或无数据")
        except Exception as e:
            print(f"✗ 字段 {field} 查询失败: {e}")

if __name__ == "__main__":
    # 先调试字段
    debug_form_fields()

    # 然后执行主查询
    get_all_customer_orders_with_work_orders()
