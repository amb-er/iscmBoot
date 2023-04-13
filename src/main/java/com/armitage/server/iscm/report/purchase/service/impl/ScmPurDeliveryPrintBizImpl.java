package com.armitage.server.iscm.report.purchase.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import com.armitage.server.common.base.model.Page;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.util.AppContextUtil;
import com.armitage.server.common.util.FormatUtils;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurDelivery;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurDeliveryAdvQuery;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.service.ScmPurDeliveryBiz;
import com.armitage.server.system.model.Report2;
import com.armitage.server.system.service.BaseReportDataBiz;
import com.armitage.server.system.service.impl.BaseReportDataBizImpl;

public class ScmPurDeliveryPrintBizImpl extends BaseReportDataBizImpl implements BaseReportDataBiz{
	
    private ScmPurDeliveryBiz scmPurDeliveryBiz = (ScmPurDeliveryBiz) AppContextUtil.getBean("scmPurDeliveryBiz");

    @Override
    public List getReportDataList(Report2 report,LinkedHashMap<String, Object> paramValueMap, Param param)
			throws AppException {
		if (report != null)  {
			try {
				ScmPurDeliveryAdvQuery scmPurDeliveryAdvQuery = new ScmPurDeliveryAdvQuery();
				scmPurDeliveryAdvQuery.setBegDate(FormatUtils.parseDate(paramValueMap.get("begDate").toString()));
				scmPurDeliveryAdvQuery.setEndDate(FormatUtils.parseDate(paramValueMap.get("endDate").toString()));
				scmPurDeliveryAdvQuery.setItemClassId(Long.valueOf(paramValueMap.get("itemClassId").toString()));
				scmPurDeliveryAdvQuery.setReqOrgUnitNo(paramValueMap.get("reqOrgUnitNo")==null?"":paramValueMap.get("reqOrgUnitNo").toString());
				scmPurDeliveryAdvQuery.setVendorClassId(Long.valueOf(paramValueMap.get("vendorClassId").toString()));
				scmPurDeliveryAdvQuery.setVendorId(Long.valueOf(paramValueMap.get("vendorId").toString()));
			    Page page = new Page();
			    page.setModelClass(ScmPurDelivery.class);
			    page.setShowCount(Integer.MAX_VALUE);
			    page.setModel(scmPurDeliveryAdvQuery);
				List list =  scmPurDeliveryBiz.findPage(page, param);
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
    	 if (list != null && !list.isEmpty()) {
  	       for(ScmPurDelivery scmPurDelivery:(List<ScmPurDelivery>)list){
  	    	   if(scmPurDelivery.getRemarks()==null) {
  	    		 scmPurDelivery.setRemarks("");
  	    	   }
  	       }
    	 }
    }
}
