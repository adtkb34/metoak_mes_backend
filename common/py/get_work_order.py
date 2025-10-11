import os
import json
import sys
import io

sys.stdout = io.TextIOWrapper(sys.stdout.buffer, encoding='utf-8')
from k3cloud_webapi_sdk.main import K3CloudApiSdk

api_sdk = K3CloudApiSdk("http://10.10.10.18/k3cloud/")

api_sdk.Init(config_path=f'{ os.path.dirname(os.path.abspath(__file__))}/conf.ini', config_node='config')



def PRD_MO_ExecuteBillQuery():
    para = {
        "FormId": "PRD_MO",
        "FieldKeys": "FBillNo,FMaterialID.FName,FQty, FPlanStartDate, FPlanFinishDate,FMaterialID.FNumber, FStatus",
        "FilterString": "FPrdOrgId.FNumber='Metoak-KXJA'",
    }
#     if kwargs:
#         para.update(kwargs)
    response = api_sdk.ExecuteBillQuery(para)
    print(response)
    res = json.loads(response)
    if len(res) > 0:
        return True
    return False

PRD_MO_ExecuteBillQuery()