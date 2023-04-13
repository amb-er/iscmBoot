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
import com.armitage.server.system.model.Report2;
import com.armitage.server.system.service.BaseReportDataBiz;
import com.armitage.server.system.service.OrgAdminBiz;
import com.armitage.server.system.service.impl.BaseReportDataBizImpl;
import com.armitage.server.user.model.Usr;

public class PurOrderBizImpl extends BaseReportDataBizImpl implements BaseReportDataBiz{
	
    private OrgAdminBiz orgAdminBiz = (OrgAdminBiz) AppContextUtil.getBean("orgAdminBiz");
    
    
    public void setOrgAdminBiz(OrgAdminBiz orgAdminBiz) {
        this.orgAdminBiz = orgAdminBiz;
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
	    PayMethod paymethod = null;
	    SettleType settleType = null;
	    Usr creator = null;
        Usr checker = null;
	    if (list != null && list.size() > 0) {
	    	for(int i=0;i<list.size();i++){
	        	Object obj = list.get(i);
		        HashMap<String, Object> keyMap=(HashMap<String, Object>) obj;
	            String reqOrgUnitNo = (String)keyMap.get("reqOrgUnitNo");
	    		if(StringUtils.isNotBlank(reqOrgUnitNo)) {
	    			OrgAdmin2 orgAdmin = orgAdminBiz.selectByOrgUnitNo(reqOrgUnitNo, param);
	    			if(orgAdmin!=null)
	    				((HashMap) obj).put("reqOrgUnitNo", orgAdmin.getOrgUnitName());
	    		}
	    	}
        }
	}

}
