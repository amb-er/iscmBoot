package com.armitage.server.iscm.report.purchase.service.impl;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.util.AppContextUtil;
import com.armitage.server.common.util.DAOHelper;
import com.armitage.server.iscm.basedata.model.PayMethod;
import com.armitage.server.iscm.basedata.model.SettleType;
import com.armitage.server.iscm.report.purchase.dao.ISCMPurchaseDAO;
import com.armitage.server.iscm.report.purchase.model.ISCMPurchase;
import com.armitage.server.system.model.OrgAdmin2;
import com.armitage.server.system.model.OrgBaseUnit2;
import com.armitage.server.system.model.Report2;
import com.armitage.server.system.service.BaseReportDataBiz;
import com.armitage.server.system.service.OrgAdminBiz;
import com.armitage.server.system.service.OrgBaseUnitBiz;
import com.armitage.server.system.service.impl.BaseReportDataBizImpl;
import com.armitage.server.user.model.Usr;

public class PurCheckBizImpl extends BaseReportDataBizImpl implements BaseReportDataBiz{
	
    private OrgBaseUnitBiz orgBaseUnitBiz = (OrgBaseUnitBiz) AppContextUtil.getBean("orgBaseUnitBiz");
    
    

    public OrgBaseUnitBiz getOrgBaseUnitBiz() {
		return orgBaseUnitBiz;
	}
	public void setOrgBaseUnitBiz(OrgBaseUnitBiz orgBaseUnitBiz) {
		this.orgBaseUnitBiz = orgBaseUnitBiz;
	}
	protected void beforeGetReportData(Report2 report,
			LinkedHashMap<String, Object> paramValueMap, Param param) throws AppException {
		
	}
	public List getReportDataList(Report2 report,
			LinkedHashMap<String, Object> paramValueMap, Param param)
			throws AppException {
		if (report != null)  {
			try {
			    ISCMPurchaseDAO dao = (ISCMPurchaseDAO) DAOHelper.getDAO(ISCMPurchase.class);
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
	    if (list != null && list.size() > 0) {
	    	Object obj2 = list.get(0);
	    	HashMap<String, Object> keyMap2=(HashMap<String, Object>) obj2;
	    	String invOrgUnitNo = (String)keyMap2.get("invOrgUnitNo");
	    	OrgBaseUnit2 orgAdmin2 = null;
    		if(StringUtils.isNotBlank(invOrgUnitNo)) {
    			orgAdmin2 = orgBaseUnitBiz.selectbyOrgNo(invOrgUnitNo, param);
    		}
	    	for(int i=0;i<list.size();i++){
	        	Object obj = list.get(i);
		        HashMap<String, Object> keyMap=(HashMap<String, Object>) obj;
	            String reqOrgUnitNo = (String)keyMap.get("reqOrgUnitNo");
	    		if(StringUtils.isNotBlank(reqOrgUnitNo)) {
	    			OrgBaseUnit2 orgAdmin = orgBaseUnitBiz.selectbyOrgNo(reqOrgUnitNo, param);
	    			if(orgAdmin!=null)
	    				((HashMap) obj).put("reqOrgUnitNo", orgAdmin.getOrgUnitName());
	    		}
    			if(orgAdmin2!=null)
    			((HashMap) obj).put("invOrgUnitNo", orgAdmin2.getOrgUnitName());
	    	}
        }

	}

}
