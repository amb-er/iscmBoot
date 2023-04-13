package com.armitage.server.iscm.report.inventory.service.impl;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.util.AppContextUtil;
import com.armitage.server.common.util.DAOHelper;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvWareHouse;
import com.armitage.server.iscm.report.inventory.dao.ISCMInventoryDAO;
import com.armitage.server.iscm.report.inventory.model.ISCMInventory;
import com.armitage.server.system.model.Code;
import com.armitage.server.system.model.OrgBaseUnit;
import com.armitage.server.system.model.Report2;
import com.armitage.server.system.service.BaseReportDataBiz;
import com.armitage.server.system.service.CodeBiz;
import com.armitage.server.system.service.OrgUnitBiz;
import com.armitage.server.system.service.impl.BaseReportDataBizImpl;
import com.armitage.server.user.model.Usr;
import com.armitage.server.user.service.UsrBiz;

public class InvmaterialrequestubillBizImpl extends BaseReportDataBizImpl implements BaseReportDataBiz{
	 private CodeBiz codeBiz = (CodeBiz) AppContextUtil.getBean("codeBiz");
	 private UsrBiz usrBiz = (UsrBiz) AppContextUtil.getBean("usrBiz");
	 private OrgUnitBiz orgUnitBiz = (OrgUnitBiz) AppContextUtil.getBean("orgUnitBiz");
	    
	 public void setCodeBiz(CodeBiz codeBiz) {
	 	this.codeBiz = codeBiz;
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
		Usr creator = null;
	    Usr checker = null;
	    Usr requestPerson = null;
		if (list != null && list.size() > 0) {
			for(int i=0;i<list.size();i++){
	        	Object obj = list.get(i);
		        HashMap<String, Object> keyMap=(HashMap<String, Object>) obj;
		        requestPerson = usrBiz.selectByCode((String)keyMap.get("requestPerson"), param);
		        if (requestPerson != null) {
		        ((HashMap) obj).put("requestPerson", requestPerson.getName());
		        }
		        creator = usrBiz.selectByCode((String)keyMap.get("creator"), param);
		        if (creator != null) {
		        ((HashMap) obj).put("creator", creator.getName());
		        }
		        checker = usrBiz.selectByCode((String)keyMap.get("checker"), param);
		        if (checker != null) {
		        ((HashMap) obj).put("checker", checker.getName());
		        }
		        String orgUnitNo = (String)keyMap.get("orgUnitNo");
	    		if(StringUtils.isNotBlank(orgUnitNo)) {
	    			OrgBaseUnit orgBaseUnit = orgUnitBiz.selectbyOrgNo(orgUnitNo, param);
	    			if(orgBaseUnit!=null)
	    				((HashMap) obj).put("orgUnitNo", orgBaseUnit.getOrgUnitName());
	    		}
			}
		}
	}
}
