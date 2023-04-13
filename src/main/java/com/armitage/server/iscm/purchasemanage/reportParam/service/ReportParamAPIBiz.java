package com.armitage.server.iscm.purchasemanage.reportParam.service;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.BaseModel;

public interface ReportParamAPIBiz extends BaseBiz<BaseModel> {
	
    
    // 返回组织的keylist
     String getOrgUnitNoKeyList(String orgUnitNo);
    
    // 返回组织的list
     String getOrgUnitNoList(String orgUnitNo, String controlUnitNo);
    
    // 返回申购组织的list
     String getReqOrgUnitNoList(String orgUnitNo, String controlUnitNo);
    
    // 返回采购组织的list
     String getPurOrgUnitNoList(String orgUnitNo, String controlUnitNo);
    
    // 返回供应商的list
     String getVenderNameList(String controlUnitNo);
    
    // 返回供应商类别的list
     String getVenderClassNameList(String controlUnitNo);
    
    // 返回物资类别的list
     String getMaterialClassNameList(String controlUnitNo);

}

