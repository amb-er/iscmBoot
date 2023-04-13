package com.armitage.server.iscm.report.selectdata;

import com.armitage.server.common.base.biz.BaseBiz;

public interface ReportSelectDataBiz extends BaseBiz {
	
    // 返回组织的list
    public String getOrgUnitNoList(String defaultValue, String orgUnitNo, String controlUnitNo);
    
    // 返回申购组织的list
    public String getReqOrgUnitNoList(String defaultValue, String orgUnitNo, String controlUnitNo);
    
    // 返回采购组织的list
    public String getPurOrgUnitNoList(String defaultValue, String orgUnitNo, String controlUnitNo);
    
    //返回财务组织的list
    public String getFinOrgUnitNoList(String defaultValue, String orgUnitNo, String controlUnitNo);
    
    // 返回供应商的list
    public String getVenderNameList(String defaultValue, String controlUnitNo);
    
    // 返回供应商类别的list
    public String getVenderClassNameList(String defaultValue, String controlUnitNo);
    
    // 返回物资的list
    public String getMaterialNameList(String defaultValue, String controlUnitNo);
    
    // 返回物资类别的list
    public String getMaterialClassNameList(String defaultValue, String controlUnitNo);
    
    // 返回物资类别的list(有层级关系)
    public String getMaterialClassNameForParentList(String defaultValue, String controlUnitNo);

    // 返回库存组织的list
    public String getInvOrgUnitNoList(String defaultValue, String orgUnitNo, String controlUnitNo);

    // 返回仓库的list
    public String getWhNameList(String defaultValue, String orgUnitNo, String controlUnitNo);

    // 返回采购业务类型的list
    public String getPurBizTypeList(String defaultValue, String scmBizType);
    // 获取FromOrgUnitNo
    public String getFormOrgUnitNoList(String defaultValue, String orgUnitNo, String controlUnitNo, String type);

    
    // 返回成本中心的list
    public String getCostOrgunitNoList(String defaultValue, String orgUnitNo, String controlUnitNo);

    // 获取ToOrgUnitNo
    public String getToOrgUnitNoList(String defaultValue, String orgUnitNo, String controlUnitNo, String type);

    // 获取buyerName
    public String getBuyerNameList(String defaultValue, String orgUnitNo, String controlUnitNo);
    /**
     * 按库存组织查询仓库及部门
     * @param orgUnitNo
     * @param controlUnitNo
     * @return
     */
    public String getWhOrDept(String defaultValue, String orgUnitNo, String controlUnitNo);

	public String getCustNameList(String defaultValue, String orgUnitNo,String controlUnitNo);
	
    // 返回物资分类标准的list
    public String getMaterialStandardNameList(String controlUnitNo);
    
    
    //返回集团下财务组织的list
    public String getReqFinOrgUnitNoList(String defaultValue, String controlUnitNo);
    
    //返回考核类型的list
    public String getAppraiseTypeList(String defaultValue, String appraiseType);
}

