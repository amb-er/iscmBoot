package com.armitage.server.iscm.inventorymanage.warehouseinbusiness.service.impl;


import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Page;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.base.model.QueryParam;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.util.ClassUtils;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvPurInWarehsBill2;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.service.ScmDeptPurInWarehsBillBiz;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.service.ScmInvPurInWarehsBillBiz;
import com.armitage.server.system.model.OrgAdmin2;
import com.armitage.server.system.service.OrgAdminBiz;
import org.springframework.stereotype.Service;

@Service("scmDeptPurInWarehsBillBiz")
public class ScmDeptPurInWarehsBillBizImpl extends BaseBizImpl<ScmInvPurInWarehsBill2> implements ScmDeptPurInWarehsBillBiz {
	private ScmInvPurInWarehsBillBiz scmInvPurInWarehsBillBiz;
	private OrgAdminBiz orgAdminBiz;
	
	public void setScmInvPurInWarehsBillBiz(ScmInvPurInWarehsBillBiz scmInvPurInWarehsBillBiz) {
		this.scmInvPurInWarehsBillBiz = scmInvPurInWarehsBillBiz;
	}

	public void setOrgAdminBiz(OrgAdminBiz orgAdminBiz) {
		this.orgAdminBiz = orgAdminBiz;
	}

	@Override
	protected void beforeFindPage(Page page, Param param) throws AppException {
		List<OrgAdmin2> orgAdminList = orgAdminBiz.findChild(param.getOrgUnitNo(), param);
		if (orgAdminList != null && !orgAdminList.isEmpty()) {
			StringBuffer orgunitList = new StringBuffer("");
			for (OrgAdmin2 orgAdmin : orgAdminList) {
				if(StringUtils.isNotBlank(orgunitList.toString()))
					orgunitList.append(",");
				orgunitList.append("'").append(orgAdmin.getOrgUnitNo()).append("'");
			}
			page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBill2.class) + "." + ScmInvPurInWarehsBill2.FN_USEORGUNITNOS), 
					new QueryParam((ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBill2.class) + "." + ScmInvPurInWarehsBill2.FN_USEORGUNITNOS), QueryParam.QUERY_IN, orgunitList.toString()));
		}else{
			page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBill2.class) + "." + ScmInvPurInWarehsBill2.FN_USEORGUNITNOS), 
					new QueryParam((ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBill2.class) + "." + ScmInvPurInWarehsBill2.FN_USEORGUNITNOS), QueryParam.QUERY_EQ, "0"));
		}
	}

	@Override
	protected void afterFindPage(List list, Page page, Param param)
			throws AppException {
		if(list != null && !list.isEmpty()){
			for(ScmInvPurInWarehsBill2 scmInvPurInWarehsBill : (List<ScmInvPurInWarehsBill2>)list){
				scmInvPurInWarehsBillBiz.setConvertMap(scmInvPurInWarehsBill,param);
				if("I,R".contains(scmInvPurInWarehsBill.getStatus())) {
					scmInvPurInWarehsBill.setPendingUsrName("");
				}
			} 
		}
	}

	@Override
	public void doAdvQuery(Page page, Param param) {
		scmInvPurInWarehsBillBiz.doAdvQuery(page, param);
	}

	@Override
	public ScmInvPurInWarehsBill2 updatePrtCount(ScmInvPurInWarehsBill2 scmInvPurInWarehsBill, Param param)
			throws AppException {
		return scmInvPurInWarehsBillBiz.updatePrtCount(scmInvPurInWarehsBill, param);
	}
	
}

