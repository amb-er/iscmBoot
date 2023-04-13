package com.armitage.server.iscm.report.purchase.service.impl;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.util.AppContextUtil;
import com.armitage.server.common.util.DAOHelper;
import com.armitage.server.iscm.report.purchase.dao.ISCMPurchaseDAO;
import com.armitage.server.iscm.report.purchase.model.ISCMPurchase;
import com.armitage.server.system.model.Report2;
import com.armitage.server.system.service.BaseReportDataBiz;
import com.armitage.server.system.service.impl.BaseReportDataBizImpl;
import com.armitage.server.user.model.Usr;
import com.armitage.server.user.service.UsrBiz;

public class PurPriceBizImpl extends BaseReportDataBizImpl implements BaseReportDataBiz{
	
	private UsrBiz usrBiz = (UsrBiz) AppContextUtil.getBean("usrBiz");
    
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
	    Usr creator = null;
        Usr pricer = null;
	    if (list != null && list.size() > 0) {
	    	for(int i=0;i<list.size();i++){
	        	Object obj = list.get(i);
		        HashMap<String, Object> keyMap=(HashMap<String, Object>) obj;
	            String priceName = (String)keyMap.get("priceName");
	            String creatord = (String)keyMap.get("creator");
	    		if(StringUtils.isNotBlank(priceName)) {
	    			pricer = usrBiz.selectByCode(priceName, param);
		            if (pricer != null) {
		                ((HashMap) obj).put("priceName", pricer.getName());
		            }
	    		}
	    		if(StringUtils.isNotBlank(creatord)){
	    			creator=usrBiz.selectByCode(creatord, param);
	    			 if (creator != null) {
			                ((HashMap) obj).put("creator", creator.getName());
			            }
	    		}
        }
	}
	}
}
