package com.armitage.server.iscm.report.purchase.service.impl;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.util.AppContextUtil;
import com.armitage.server.common.util.DAOHelper;
import com.armitage.server.iscm.basedata.model.ScmMaterial2;
import com.armitage.server.iscm.basedata.model.ScmMeasureUnit;
import com.armitage.server.iscm.basedata.model.Scmsupplier2;
import com.armitage.server.iscm.basedata.service.ScmMaterialBiz;
import com.armitage.server.iscm.basedata.service.ScmMeasureUnitBiz;
import com.armitage.server.iscm.basedata.service.ScmsupplierBiz;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurBuyer2;
import com.armitage.server.iscm.purchasemanage.purchasesetting.service.ScmPurBuyerBiz;
import com.armitage.server.iscm.report.purchase.dao.ISCMPurchaseDAO;
import com.armitage.server.iscm.report.purchase.model.ISCMPurchase;
import com.armitage.server.system.model.Report2;
import com.armitage.server.system.service.BaseReportDataBiz;
import com.armitage.server.system.service.impl.BaseReportDataBizImpl;
import com.armitage.server.user.model.Usr;
import com.armitage.server.user.service.UsrBiz;

public class PurQuotationBizImpl extends BaseReportDataBizImpl implements BaseReportDataBiz{
	
    private ScmsupplierBiz scmsupplierBiz = (ScmsupplierBiz) AppContextUtil.getBean("scmsupplierBiz");
    private ScmMaterialBiz scmMaterialBiz = (ScmMaterialBiz) AppContextUtil.getBean("scmMaterialBiz");
    private ScmMeasureUnitBiz scmMeasureUnitBiz = (ScmMeasureUnitBiz) AppContextUtil.getBean("scmMeasureUnitBiz");
    private ScmPurBuyerBiz scmPurBuyerBiz = (ScmPurBuyerBiz) AppContextUtil.getBean("scmPurBuyerBiz");
    private UsrBiz usrBiz = (UsrBiz) AppContextUtil.getBean("usrBiz");

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
	protected void afterGetReportData(Report2 report,LinkedHashMap<String, Object> paramValueMap, List list, Param param)
			throws AppException {
	    if (list != null && list.size() > 0) {
	    	for(int i=0;i<list.size();i++){
	        	Object obj = list.get(i);
		        HashMap<String, Object> keyMap=(HashMap<String, Object>) obj;
		        if(keyMap.get("vendorId")!=null && Long.valueOf(keyMap.get("vendorId").toString())>0) {
		        	Scmsupplier2 scmsupplier = scmsupplierBiz.selectDirect(Long.valueOf(keyMap.get("vendorId").toString()), param);
		        	if(scmsupplier!=null) {
		        		((HashMap) obj).put("vendorNo",scmsupplier.getVendorNo());
		        		((HashMap) obj).put("vendorName",scmsupplier.getVendorName());
		        	}
		        }
		        if(keyMap.get("purUnit")!=null && Long.valueOf(keyMap.get("purUnit").toString())>0) {
		        	ScmMeasureUnit scmMeasureUnit = scmMeasureUnitBiz.selectDirect(Long.valueOf(keyMap.get("purUnit").toString()), param);
		        	if(scmMeasureUnit!=null)
		        		((HashMap) obj).put("unitName", scmMeasureUnit.getUnitName());
	            }
		        if(keyMap.get("itemId")!=null && Long.valueOf(keyMap.get("itemId").toString())>0) {
		        	ScmMaterial2 scmMaterial = scmMaterialBiz.selectDirect(Long.valueOf(keyMap.get("itemId").toString()), param);
		        	if (scmMaterial != null) {
		        		((HashMap) obj).put("itemNo", scmMaterial.getItemNo());
		        		((HashMap) obj).put("itemName",StringUtils.isBlank(scmMaterial.getSpec())?scmMaterial.getItemName():scmMaterial.getItemName()+"("+scmMaterial.getSpec()+")");
		        	}
	            }
		        if(keyMap.get("buyerId")!=null && Long.valueOf(keyMap.get("buyerId").toString())>0) {
		        	ScmPurBuyer2 scmPurBuyer = scmPurBuyerBiz.selectDirect(Long.valueOf(keyMap.get("buyerId").toString()), param);
		        	if(scmPurBuyer!=null)
		        		((HashMap) obj).put("buyerName", scmPurBuyer.getBuyerName());
		        }
		        if(keyMap.get("creator")!=null && StringUtils.isNotBlank(keyMap.get("creator").toString())) {
			        Usr creator = usrBiz.selectByCode((String)keyMap.get("creator"), param);
		            if (creator != null) {
		                ((HashMap) obj).put("creator", creator.getName());
		            }
		        }
	    	}
        }
	}

}
