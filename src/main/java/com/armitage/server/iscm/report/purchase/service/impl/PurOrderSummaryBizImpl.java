package com.armitage.server.iscm.report.purchase.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
import com.armitage.server.system.model.OrgBaseUnit;
import com.armitage.server.system.model.Report2;
import com.armitage.server.system.service.BaseReportDataBiz;
import com.armitage.server.system.service.OrgBaseUnitBiz;
import com.armitage.server.system.service.impl.BaseReportDataBizImpl;

public class PurOrderSummaryBizImpl extends BaseReportDataBizImpl implements BaseReportDataBiz{
	
	private OrgBaseUnitBiz orgBaseUnitBiz = (OrgBaseUnitBiz) AppContextUtil.getBean("orgBaseUnitBiz");
    
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
	protected void afterGetReportData(Report2 report,LinkedHashMap<String, Object> paramValueMap, List list, Param param) throws AppException {
		OrgBaseUnit orgUnitNo = null;
		HashMap<String,HashMap<String,Object>> map = new HashMap<>();
	    if (list != null && list.size() > 0) {
	       for(int i = list.size()-1; i >= 0; i--){
	    	   Object obj = list.get(i);
		       HashMap<String, Object> keyMap=(HashMap<String, Object>) obj;
		       BigDecimal qty =  (BigDecimal)keyMap.get("qty");
		       String receiveorgunitno = (String)keyMap.get("receiveorgunitno");
		       String remarks = (String)keyMap.get("remarks");
	    	   String info = (String)keyMap.get("vendorName") + "_" + (String)keyMap.get("itemNo");
	    	   if(map.containsKey(info)){
	    		   String qtyStr="";
	    		   if(qty.multiply(new BigDecimal("100")).divide(new BigDecimal("100"), 0, RoundingMode.HALF_UP).compareTo(qty)==0) {
	    			   qtyStr = String.valueOf(qty.intValue());
	    		   }else {
	    			   qtyStr = String.valueOf(qty.setScale(2, RoundingMode.HALF_UP));
	    		   }
	    		   BigDecimal sumQty = (BigDecimal)(map.get(info)).get("qty");
	    		   String totalRemarks =  (String)(map.get(info)).get("remarks");
	    		   sumQty = sumQty.add(qty).setScale(2, RoundingMode.HALF_UP);
	    		   if(StringUtils.isNotBlank(receiveorgunitno)){
		        		orgUnitNo = orgBaseUnitBiz.selectbyOrgNo(receiveorgunitno, param);
		        		if(orgUnitNo!=null){
		        			totalRemarks = totalRemarks + " " + orgUnitNo.getOrgUnitName() + "("
		        					+ qtyStr + ")" + remarks + ";";
		        		}else{
		        			totalRemarks = totalRemarks + " ("
		        					+ qtyStr + ")" + remarks + ";";
		        		}
	    		   }else{
	        			totalRemarks = totalRemarks + " ("
	        					+ qtyStr + ")" + remarks + ";";
	    		   }
	    		   Object existObj = (map.get(info)).get("existObj");
	    		   if(existObj != null){
	    			   list.remove(existObj);
	    		   }
	    		   ((HashMap) obj).put("qty", sumQty);
	    		   ((HashMap) obj).put("remarks", totalRemarks);
	    		   (map.get(info)).put("qty", sumQty);
	    		   (map.get(info)).put("remarks", totalRemarks);
	    		   (map.get(info)).put("existObj", obj);
	    	   }else{
	    		   HashMap<String,Object> tempMap = new HashMap<>();
	    		   String qtyStr="";
	    		   if(qty.multiply(new BigDecimal("100")).divide(new BigDecimal("100"), 0, RoundingMode.HALF_UP).compareTo(qty)==0) {
	    			   qtyStr = String.valueOf(qty.intValue());
	    		   }else {
	    			   qtyStr = String.valueOf(qty.setScale(2, RoundingMode.HALF_UP));
	    		   }
	    		   if(StringUtils.isNotBlank(receiveorgunitno)){
		        		orgUnitNo = orgBaseUnitBiz.selectbyOrgNo(receiveorgunitno, param);
		        		if(orgUnitNo!=null){
		        			remarks = orgUnitNo.getOrgUnitName() + "("
		        					+ qtyStr + ")" + remarks + ";";
		        		}else{
		        			remarks = "("
		        					+ qtyStr + ")" + remarks + ";";
		        		}
	    		   }
	    		   ((HashMap) obj).put("qty", qty);
	    		   ((HashMap) obj).put("remarks", remarks);
	    		   tempMap.put("qty", qty);
	    		   tempMap.put("remarks", remarks);
	    		   tempMap.put("existObj", obj);
	    		   map.put(info, tempMap);
	    	   }
	       }
        }
	}

}
