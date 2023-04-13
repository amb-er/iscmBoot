package com.armitage.server.iscm.report.inventory.service.impl;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.util.AppContextUtil;
import com.armitage.server.common.util.DAOHelper;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvWareHouse;
import com.armitage.server.iscm.inventorymanage.inventorysetting.service.ScmInvWareHouseBiz;
import com.armitage.server.iscm.report.inventory.dao.ISCMInventoryDAO;
import com.armitage.server.iscm.report.inventory.model.ISCMInventory;
import com.armitage.server.system.model.Code;
import com.armitage.server.system.model.OrgBaseUnit;
import com.armitage.server.system.model.Report2;
import com.armitage.server.system.service.BaseReportDataBiz;
import com.armitage.server.system.service.CodeBiz;
import com.armitage.server.system.service.OrgBaseUnitBiz;
import com.armitage.server.system.service.impl.BaseReportDataBizImpl;
import com.armitage.server.user.model.Usr;
import com.armitage.server.user.service.UsrBiz;


public class InvCountingCostCenterBizImpl extends BaseReportDataBizImpl implements BaseReportDataBiz{
	 private CodeBiz codeBiz = (CodeBiz) AppContextUtil.getBean("codeBiz");
	 private ScmInvWareHouseBiz scmInvWareHouseBiz = (ScmInvWareHouseBiz) AppContextUtil.getBean("scmInvWareHouseBiz");
	 private UsrBiz usrBiz = (UsrBiz) AppContextUtil.getBean("usrBiz");
	 private OrgBaseUnitBiz orgBaseUnitBiz = (OrgBaseUnitBiz) AppContextUtil.getBean("orgBaseUnitBiz");   
	 
	 public void setCodeBiz(CodeBiz codeBiz) {
	 	this.codeBiz = codeBiz;
	 }
	 public void setScmInvWareHouseBiz(ScmInvWareHouseBiz scmInvWareHouseBiz) {
		this.scmInvWareHouseBiz = scmInvWareHouseBiz;
	 }
	    
	 public void setUsrBiz(UsrBiz usrBiz) {
	 	this.usrBiz = usrBiz;
	 }
	

	protected void beforeGetReportData(Report2 report,
			LinkedHashMap<String, Object> paramValueMap, Param param) throws AppException {
		
	}
	public List getReportDataList(Report2 report,
			LinkedHashMap<String, Object> paramValueMap, Param param)
			throws AppException {
		if (report != null)  {
			try {
				ISCMInventoryDAO dao = (ISCMInventoryDAO) DAOHelper.getDAO(ISCMInventory.class);
				List list =  dao.exec(report.getExecSql(), paramValueMap); 
				return list;
			} catch (Exception e) {
				throw new AppException(
						"system.service.imp.ReportBizImpl.exec.error", e);
			}
		}
		return null;
	}

	@Override
	protected void afterGetReportData(Report2 report,
			LinkedHashMap<String, Object> paramValueMap, List list, Param param)
			throws AppException {
		Usr countingPerson = null;
	    Usr countingMonitorer = null;
	    OrgBaseUnit useOrgUnitNo = null;
		if (list != null && list.size() > 0) {
	        for(int i=0;i<list.size();i++){
	        	Object obj = list.get(i);
		        HashMap<String, Object> keyMap=(HashMap<String, Object>) obj;
		        countingPerson = usrBiz.selectByCode((String)keyMap.get("countingPerson"), param);
		        if (countingPerson != null) {
		        ((HashMap) obj).put("countingPerson", countingPerson.getName());
		        }
		        countingMonitorer = usrBiz.selectByCode((String)keyMap.get("checker"), param);
		        if (countingMonitorer != null) {
		        ((HashMap) obj).put("countingMonitorer", countingMonitorer.getName());
		        }
		        useOrgUnitNo = orgBaseUnitBiz.selectbyOrgNo((String)keyMap.get("useOrgUnitNo"), param);
	            if (useOrgUnitNo != null) {
	                ((HashMap) obj).put("useOrgUnitNo", useOrgUnitNo.getOrgUnitName());
	            }
	        }
		}
	}
}
