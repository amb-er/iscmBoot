package com.armitage.server.iscm.report.purchase.service.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Page;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.util.ClassUtils;
import com.armitage.server.common.util.FormatUtils;
import com.armitage.server.iscm.basedata.model.ScmMaterial2;
import com.armitage.server.iscm.basedata.model.ScmMaterialGroup;
import com.armitage.server.iscm.basedata.model.ScmMeasureUnit;
import com.armitage.server.iscm.basedata.model.Scmsupplier2;
import com.armitage.server.iscm.basedata.service.ScmMaterialBiz;
import com.armitage.server.iscm.basedata.service.ScmMaterialGroupBiz;
import com.armitage.server.iscm.basedata.service.ScmMeasureUnitBiz;
import com.armitage.server.iscm.basedata.service.ScmsupplierBiz;
import com.armitage.server.iscm.basedata.service.ScmsuppliergroupBiz;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurPrice2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurDelivery;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurDeliveryAdvQuery;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurOrder2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurRequire2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurRequireEntry2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.service.ScmPurDeliveryBiz;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurBuyer2;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurGroup;
import com.armitage.server.iscm.purchasemanage.purchasesetting.service.ScmPurBuyerBiz;
import com.armitage.server.iscm.purchasemanage.purchasesetting.service.ScmPurGroupBiz;
import com.armitage.server.iscm.report.purchase.dao.ScmPurReportDAO;
import com.armitage.server.iscm.report.purchase.model.OrderTransTotalAPI;
import com.armitage.server.iscm.report.purchase.model.ScmPurHistoryPrice;
import com.armitage.server.iscm.report.purchase.model.ScmPurOrderTransInfo;
import com.armitage.server.iscm.report.purchase.model.ScmPurOrderTransTotal;
import com.armitage.server.iscm.report.purchase.model.ScmPurReport;
import com.armitage.server.iscm.report.purchase.model.ScmPurReturnInfo;
import com.armitage.server.iscm.report.purchase.model.ScmPurSupplierAppraiseDetails;
import com.armitage.server.iscm.report.purchase.service.ScmPurReportBiz;
import com.armitage.server.system.model.Code;
import com.armitage.server.system.model.OrgAdmin2;
import com.armitage.server.system.model.OrgBaseUnit;
import com.armitage.server.system.model.OrgPurchase2;
import com.armitage.server.system.model.OrgStorage2;
import com.armitage.server.system.service.CodeBiz;
import com.armitage.server.system.service.OrgAdminBiz;
import com.armitage.server.system.service.OrgPurchaseBiz;
import com.armitage.server.system.service.OrgStorageBiz;
import com.armitage.server.system.service.OrgUnitBiz;
import com.armitage.server.user.model.Usr;
import com.armitage.server.user.service.UsrBiz;
import org.springframework.stereotype.Service;

@Service("scmPurReportBiz")
public class ScmPurReportBizImpl extends BaseBizImpl<ScmPurReport> implements ScmPurReportBiz {
	private static Log log = LogFactory.getLog(ScmPurReportBizImpl.class);
	private OrgUnitBiz orgUnitBiz;
    private OrgPurchaseBiz orgPurchaseBiz;
    private OrgStorageBiz orgStorageBiz;
    private OrgAdminBiz orgAdminBiz;
    private ScmMeasureUnitBiz scmMeasureUnitBiz;
	private ScmMaterialGroupBiz scmMaterialGroupBiz;
	private CodeBiz codeBiz;
	private ScmsupplierBiz scmsupplierBiz;
	private ScmMaterialBiz scmMaterialBiz;
	private UsrBiz usrBiz;
	private ScmPurGroupBiz scmPurGroupBiz;
	private ScmPurBuyerBiz scmPurBuyerBiz;
	private ScmsuppliergroupBiz scmsuppliergroupBiz;
	private ScmPurDeliveryBiz scmPurDeliveryBiz;
	
	public void setOrgUnitBiz(OrgUnitBiz orgUnitBiz) {
		this.orgUnitBiz = orgUnitBiz;
	}

	public void setOrgPurchaseBiz(OrgPurchaseBiz orgPurchaseBiz) {
		this.orgPurchaseBiz = orgPurchaseBiz;
	}

	public void setOrgStorageBiz(OrgStorageBiz orgStorageBiz) {
		this.orgStorageBiz = orgStorageBiz;
	}

	public void setOrgAdminBiz(OrgAdminBiz orgAdminBiz) {
		this.orgAdminBiz = orgAdminBiz;
	}

	public void setScmMeasureUnitBiz(ScmMeasureUnitBiz scmMeasureUnitBiz) {
		this.scmMeasureUnitBiz = scmMeasureUnitBiz;
	}

	public void setScmMaterialGroupBiz(ScmMaterialGroupBiz scmMaterialGroupBiz) {
		this.scmMaterialGroupBiz = scmMaterialGroupBiz;
	}

	public void setCodeBiz(CodeBiz codeBiz) {
		this.codeBiz = codeBiz;
	}

	public void setScmsupplierBiz(ScmsupplierBiz scmsupplierBiz) {
		this.scmsupplierBiz = scmsupplierBiz;
	}

	public void setScmMaterialBiz(ScmMaterialBiz scmMaterialBiz) {
		this.scmMaterialBiz = scmMaterialBiz;
	}

	public void setUsrBiz(UsrBiz usrBiz) {
		this.usrBiz = usrBiz;
	}

	public void setScmPurGroupBiz(ScmPurGroupBiz scmPurGroupBiz) {
		this.scmPurGroupBiz = scmPurGroupBiz;
	}

	public void setScmPurBuyerBiz(ScmPurBuyerBiz scmPurBuyerBiz) {
		this.scmPurBuyerBiz = scmPurBuyerBiz;
	}

	public void setScmsuppliergroupBiz(ScmsuppliergroupBiz scmsuppliergroupBiz) {
		this.scmsuppliergroupBiz = scmsuppliergroupBiz;
	}

	public void setScmPurDeliveryBiz(ScmPurDeliveryBiz scmPurDeliveryBiz) {
		this.scmPurDeliveryBiz = scmPurDeliveryBiz;
	}

	/*供应商综合情况表*/
	@Override
	public List<ScmPurOrder2> selectSupplierConsolidation(HttpServletRequest request) throws Exception {
		String currentOrgUnitNo=request.getParameter("orgUnitNo");
		String currentControlUnitNo=request.getParameter("controlUnitNo");
		String finOrgUnitNo=request.getParameter("finOrgUnitNo");//财务组织
		String vendorName=request.getParameter("vendorName");
		String isFlag=request.getParameter("isFlag");
		String minOrderDate=request.getParameter("minOrderDate");
		String maxOrderDate=request.getParameter("maxOrderDate");
		if(StringUtils.isBlank(finOrgUnitNo))
			finOrgUnitNo = currentOrgUnitNo;
		Param param = new Param();
		param.setOrgUnitNo(currentOrgUnitNo);
		param.setControlUnitNo(currentControlUnitNo);
		HashMap<String, Object> map=new HashMap<>();
		map.put("finOrgUnitNo", finOrgUnitNo);
		map.put("vendorName", vendorName);
		map.put("isFlag", isFlag);
		map.put("minOrderDate", minOrderDate);
		map.put("maxOrderDate", maxOrderDate);
		List<ScmPurOrder2> list=((ScmPurReportDAO)dao).selectSupplierConsolidation(map);
		if(list != null && list.size()>0){
			for(int i=0;i<list.size();i++){
				ScmPurOrder2 scmPurOrder=list.get(i);
				OrgBaseUnit orgBaseUnit=orgUnitBiz.selectbyOrgNo(scmPurOrder.getFinOrgUnitNo(), param);
				if(orgBaseUnit!=null){
					scmPurOrder.setFinOrgUnitNo(orgBaseUnit.getOrgUnitName());
				}	
			}
		}
		return list;
	}
	
	/*
	 *物资采购排行榜 
	 */
	@Override
	public List<ScmPurOrder2> selectMaterialProcurement(HttpServletRequest request) throws Exception {
	    HashMap<String, Object> map=new HashMap<>();
		String currentOrgUnitNo=request.getParameter("orgUnitNo");
		String currentControlUnitNo=request.getParameter("controlUnitNo");
	    String startDate=request.getParameter("startDate");
		String endDate=request.getParameter("endDate");
		String purOrgUnitNo=request.getParameter("purOrgUnitNo");
		if(StringUtils.isBlank(purOrgUnitNo))
			purOrgUnitNo = currentOrgUnitNo;
		Param param = new Param();
		param.setOrgUnitNo(currentOrgUnitNo);
		param.setControlUnitNo(currentControlUnitNo);
		List<OrgPurchase2> orgPurchaseList = orgPurchaseBiz.findChild(purOrgUnitNo, param);
        StringBuffer sbPur = new StringBuffer("");
        if (orgPurchaseList == null || orgPurchaseList.isEmpty()) {
            map.put("purOrgUnitNo", null);
        }
        if (orgPurchaseList != null && orgPurchaseList.size() > 0) {
            sbPur.append("(");
            for(int i=0; i<orgPurchaseList.size(); i++) {
                sbPur.append(orgPurchaseList.get(i).getOrgUnitNo());
                if (i == orgPurchaseList.size()-1) {
                    break;
                }
                sbPur.append(",");
            }
            sbPur.append(")");
            map.put("purOrgUnitNo", sbPur.toString());
        }
        String sortBy=request.getParameter("sortBy");
		String status=request.getParameter("status");
		String invOrgUnitNo=request.getParameter("purToInvTo");
        List<OrgStorage2> orgStorageList = orgStorageBiz.findChild(invOrgUnitNo, param);
        StringBuffer sbInv = new StringBuffer("");
        if (orgStorageList == null || orgStorageList.isEmpty()) {
            map.put("invOrgUnitNo", null);
        }
        if (orgStorageList != null && orgStorageList.size() > 0) {
            sbInv.append("(");
            for(int i=0; i<orgStorageList.size(); i++) {
                sbInv.append(orgStorageList.get(i).getOrgUnitNo());
                if (i == orgStorageList.size()-1) {
                    break;
                }
                sbInv.append(",");
            }
            sbInv.append(")");
            map.put("invOrgUnitNo", sbInv.toString());
        }
        String materialClassName = request.getParameter("materialClassName");
        if (StringUtils.isNotBlank(materialClassName) && StringUtils.isNumeric(materialClassName)) {
            long materialClassId = (Integer.parseInt(materialClassName));
            List<ScmMaterialGroup> scmMaterialGroupList = scmMaterialGroupBiz.findChild(materialClassId, param);
            StringBuffer sbMaterilaClass = new StringBuffer("");
            if (scmMaterialGroupList != null && scmMaterialGroupList.size() > 0) {
                sbMaterilaClass.append("(");
                for (int i = 0; i < scmMaterialGroupList.size(); i++) {
                    sbMaterilaClass.append(scmMaterialGroupList.get(i).getId());
                    if (i == scmMaterialGroupList.size() - 1) {
                        break;
                    }
                    sbMaterilaClass.append(",");
                }
                sbMaterilaClass.append(")");
                map.put("materialClassName", sbMaterilaClass.toString());
            }
        }
        String materialName=request.getParameter("materialName");
        int materialId = 0;
        if (StringUtils.isNotBlank(materialName) && StringUtils.isNumeric(materialName)) {
            materialId = (Integer.parseInt(materialName));
        }
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("materialId", materialId);
		map.put("status", status);
		if(StringUtils.isNotBlank(sortBy))
			map.put("sortBy", sortBy);
		List<ScmPurOrder2> list=((ScmPurReportDAO)dao).selectMaterialProcurement(map);
		/*BigDecimal totalOrderQty = BigDecimal.ZERO;
        BigDecimal totalOrderAmt = BigDecimal.ZERO;
        BigDecimal totalStorageQty = BigDecimal.ZERO;
        BigDecimal totalStorageAmt = BigDecimal.ZERO;
        BigDecimal totalReturnQty = BigDecimal.ZERO;
        BigDecimal totalReturnAmt = BigDecimal.ZERO;
		if(list != null && list.size()>0){
			for(int i=0;i<list.size();i++){
				ScmPurOrder2 scmPurOrder=list.get(i);
				OrgBaseUnit orgBaseUnit=orgUnitBiz.selectbyOrgNo(scmPurOrder.getOrgUnitNo(), param);
				if(orgBaseUnit!=null){
					scmPurOrder.setOrgUnitNo(orgBaseUnit.getOrgUnitName());
				}	
				totalOrderQty = totalOrderQty.add(scmPurOrder.getOrderQty());
                totalOrderAmt = totalOrderAmt.add(scmPurOrder.getOrderAmt());
                totalStorageQty = totalStorageQty.add(scmPurOrder.getStorageQty());
                totalStorageAmt = totalStorageAmt.add(scmPurOrder.getStorageAmt());
                totalReturnQty = totalReturnQty.add(scmPurOrder.getReturnQty());
                totalReturnAmt = totalReturnAmt.add(scmPurOrder.getReturnAmt());
			}
			for(int i=0;i<list.size();i++){
                list.get(i).setTotalOrderAmt(totalOrderAmt);
                list.get(i).setTotalOrderQty(totalOrderQty);
                list.get(i).setTotalStorageAmt(totalStorageAmt);
                list.get(i).setTotalStorageQty(totalStorageQty);
                list.get(i).setTotalReturnAmt(totalReturnAmt);
                list.get(i).setTotalReturnQty(totalReturnQty);
            }
		}*/
		return list;
	}
	
	/*
	 * 物资交易明细表（按供应商*库存组织）
	 */
	@Override
	public List<ScmPurOrder2> selectMaterialTransDetails(HttpServletRequest request) throws Exception {
	    HashMap<String, Object> map=new HashMap<>();
		String currentOrgUnitNo=request.getParameter("orgUnitNo");
		String currentControlUnitNo=request.getParameter("controlUnitNo");
		String startDate=request.getParameter("startDate");
		String endDate=request.getParameter("endDate");
		String purOrgUnitNo=request.getParameter("purOrgUnitNo");
		if(StringUtils.isBlank(purOrgUnitNo))
			purOrgUnitNo = currentOrgUnitNo;
		Param param = new Param();
		param.setOrgUnitNo(currentOrgUnitNo);
		param.setControlUnitNo(currentControlUnitNo);
        List<OrgPurchase2> orgPurchaseList = orgPurchaseBiz.findChild(purOrgUnitNo, param);
        StringBuffer sbPur = new StringBuffer("");
        if (orgPurchaseList == null || orgPurchaseList.isEmpty()) {
            map.put("purOrgUnitNo", null);
        }
        if (orgPurchaseList != null && orgPurchaseList.size() > 0) {
            sbPur.append("(");
            for(int i=0; i<orgPurchaseList.size(); i++) {
                sbPur.append(orgPurchaseList.get(i).getOrgUnitNo());
                if (i == orgPurchaseList.size()-1) {
                    break;
                }
                sbPur.append(",");
            }
            sbPur.append(")");
            map.put("purOrgUnitNo", sbPur.toString());
        }
		String status=request.getParameter("status");
		String materialName=request.getParameter("materialName");
        int materialId = 0;
        if (StringUtils.isNotBlank(materialName) && StringUtils.isNumeric(materialName)) {
            materialId = (Integer.parseInt(materialName));
        }
		String invOrgUnitNo=request.getParameter("purToInvTo");
		if(StringUtils.isBlank(invOrgUnitNo)){
			map.put("invOrgUnitNo", null);
		}else{
			List<OrgStorage2> orgStorageList = orgStorageBiz.findChild(invOrgUnitNo, param);
	        StringBuffer sbInv = new StringBuffer("");
	        if (orgStorageList == null || orgStorageList.isEmpty()) {
	            map.put("invOrgUnitNo", null);
	        }
	        if (orgStorageList != null && orgStorageList.size() > 0) {
	            sbInv.append("(");
	            for(int i=0; i<orgStorageList.size(); i++) {
	                sbInv.append(orgStorageList.get(i).getOrgUnitNo());
	                if (i == orgStorageList.size()-1) {
	                    break;
	                }
	                sbInv.append(",");
	            }
	            sbInv.append(")");
	            map.put("invOrgUnitNo", sbInv.toString());
	        }
		}
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("status", status);      
		map.put("materialId", materialId);      
		List<ScmPurOrder2> list=((ScmPurReportDAO)dao).selectMaterialTransDetails(map);
        BigDecimal totalOrderQty = BigDecimal.ZERO;
        BigDecimal totalOrderAmt = BigDecimal.ZERO;
        BigDecimal totalStorageQty = BigDecimal.ZERO;
        BigDecimal totalStorageAmt = BigDecimal.ZERO;
        BigDecimal totalReturnQty = BigDecimal.ZERO;
        BigDecimal totalReturnAmt = BigDecimal.ZERO;
		if(list != null && list.size()>0){
			for(int i=0;i<list.size();i++){
                ScmPurOrder2 scmPurOrder = list.get(i);
                OrgBaseUnit orgBaseUnit = orgUnitBiz.selectbyOrgNo(scmPurOrder.getStorageOrgUnitNo(), param);
                if (orgBaseUnit != null) {
                    scmPurOrder.setStorageOrgUnitNo(orgBaseUnit.getOrgUnitName());
                }	
                totalOrderQty = totalOrderQty.add(scmPurOrder.getOrderQty());
                totalOrderAmt = totalOrderAmt.add(scmPurOrder.getOrderAmt());
                totalStorageQty = totalStorageQty.add(scmPurOrder.getStorageQty());
                totalStorageAmt = totalStorageAmt.add(scmPurOrder.getStorageAmt());
                totalReturnQty = totalReturnQty.add(scmPurOrder.getReturnQty());
                totalReturnAmt = totalReturnAmt.add(scmPurOrder.getReturnAmt());
			}
			for(int i=0;i<list.size();i++){
                list.get(i).setTotalOrderAmt(totalOrderAmt);
                list.get(i).setTotalOrderQty(totalOrderQty);
                list.get(i).setTotalStorageAmt(totalStorageAmt);
                list.get(i).setTotalStorageQty(totalStorageQty);
                list.get(i).setTotalReturnAmt(totalReturnAmt);
                list.get(i).setTotalReturnQty(totalReturnQty);
            }
		}
		return list;
	}
	
	//供应商交易汇总表
	@Override
	public List<OrderTransTotalAPI> selectSupTransSummary(HttpServletRequest request) throws Exception {
		String currentOrgUnitNo=request.getParameter("orgUnitNo");
		String currentControlUnitNo=request.getParameter("controlUnitNo");
		String purOrgUnitNo=request.getParameter("purOrgUnitNo");
		String invOrgUnitNo=request.getParameter("invOrgUnitNo");
		String vendorName = request.getParameter("vendorName");
		String startDate=request.getParameter("minOrderDate");
		String endDate=request.getParameter("maxOrderDate");
		String flag = "1".equals(request.getParameter("flag")) ? "1" : "0";
		if(StringUtils.isBlank(purOrgUnitNo))
			purOrgUnitNo = currentOrgUnitNo;
		String unified=request.getParameter("unified");
		if(StringUtils.isBlank(unified))
			unified = "2";
		String self=request.getParameter("self");
		if(StringUtils.isBlank(self))
			self = "0";
		Param param = new Param();
		param.setOrgUnitNo(currentOrgUnitNo);
		param.setControlUnitNo(currentControlUnitNo);
		
		HashMap<String, Object> map=new HashMap<>();
		map.put("minOrderDate", startDate);
		map.put("maxOrderDate", endDate);
		map.put("vendorName", vendorName);
		
		List<OrgStorage2> orgStorageList = orgStorageBiz.findChild(invOrgUnitNo, param);
        StringBuffer sbInv = new StringBuffer("");
        if (orgStorageList == null || orgStorageList.isEmpty()) {
            map.put("invOrgUnitNo", null);
        }
        if (orgStorageList != null && orgStorageList.size() > 0) {
            for(int i=0; i<orgStorageList.size(); i++) {
            	sbInv.append(orgStorageList.get(i).getOrgUnitNo());
                if (i == orgStorageList.size()-1) {
                    break;
                }
                sbInv.append(",");
            }
            map.put("invOrgUnitNo", sbInv.toString());
        }
		
        List<OrgPurchase2> orgPurchaseList = orgPurchaseBiz.findChild(purOrgUnitNo, param);
        StringBuffer sbPur = new StringBuffer("");
        if (orgPurchaseList == null || orgPurchaseList.isEmpty()) {
            map.put("purOrgUnitNo", null);
        }
        if (orgPurchaseList != null && orgPurchaseList.size() > 0) {
            sbPur.append("(");
            for(int i=0; i<orgPurchaseList.size(); i++) {
                sbPur.append(orgPurchaseList.get(i).getOrgUnitNo());
                if (i == orgPurchaseList.size()-1) {
                    break;
                }
                sbPur.append(",");
            }
            sbPur.append(")");
            map.put("purOrgUnitNo", sbPur.toString());
        }
        map.put("unified", unified);
        map.put("self", self);
        map.put("flag", flag);
		List<OrderTransTotalAPI> rntList = new ArrayList();
		List<ScmPurOrderTransTotal> list=((ScmPurReportDAO)dao).selectSupTransSummary(map);
		if(list != null && list.size()>0){
			for(int i=0;i<list.size();i++){
				ScmPurOrderTransTotal scmPurOrderTransTotal=list.get(i);
				OrgBaseUnit orgBaseUnit=orgUnitBiz.selectbyOrgNo(scmPurOrderTransTotal.getPurOrgUnitNo(), param);
				if(orgBaseUnit!=null){
					scmPurOrderTransTotal.setPurOrgUnitName(orgBaseUnit.getOrgUnitName());
				}
				OrgBaseUnit orgBaseUnit2=orgUnitBiz.selectbyOrgNo(scmPurOrderTransTotal.getStorageOrgUnitNo(), param);
				if(orgBaseUnit2!=null){
					scmPurOrderTransTotal.setStorageOrgUnitName(orgBaseUnit2.getOrgUnitName());			
				}
				if(scmPurOrderTransTotal.getVendorId()>0){
					Scmsupplier2 scmsupplier = scmsupplierBiz.selectDirect(scmPurOrderTransTotal.getVendorId(), param);
					if(scmsupplier!=null){
						scmPurOrderTransTotal.setVendorNo(scmsupplier.getVendorNo());
						scmPurOrderTransTotal.setVendorName(scmsupplier.getVendorName());
					}
				}
				scmPurOrderTransTotal.setNotAddInAmt(scmPurOrderTransTotal.getReceiveAmt().subtract(scmPurOrderTransTotal.getAddInAmt()));
				scmPurOrderTransTotal.setNotAddInTaxAmt(scmPurOrderTransTotal.getReceiveTaxAmt().subtract(scmPurOrderTransTotal.getAddInTaxAmt()));
				scmPurOrderTransTotal.setRealAddInAmt(scmPurOrderTransTotal.getAddInAmt().subtract(scmPurOrderTransTotal.getOutAmt()));
				scmPurOrderTransTotal.setRealAddInTaxAmt(scmPurOrderTransTotal.getAddInTaxAmt().subtract(scmPurOrderTransTotal.getOutTaxAmt()));
			    OrderTransTotalAPI orderTransTotalAPI = new OrderTransTotalAPI();
			    BeanUtils.copyProperties(list.get(i), orderTransTotalAPI);
			    rntList.add(orderTransTotalAPI);
			}
		}
		return rntList;
	}
	
	//供应商交易物资汇总表
	@Override
	public List<ScmPurOrderTransInfo> selectSupTransItemSummary(HttpServletRequest request) throws Exception {
		String currentOrgUnitNo=request.getParameter("orgUnitNo");
		String currentControlUnitNo=request.getParameter("controlUnitNo");
		String purOrgUnitNo=request.getParameter("purOrgUnitNo");
		String startDate=request.getParameter("minOrderDate");
		String endDate=request.getParameter("maxOrderDate");
		String invOrgUnitNo=request.getParameter("invOrgUnitNo");
		String vendorName = request.getParameter("vendorName");
		String unified = request.getParameter("unified");	//配送方式
		if(StringUtils.isBlank(unified))
			unified="2";
		String self = request.getParameter("self");	//包含自采
		if(StringUtils.isBlank(self))
			self="0";
		if(StringUtils.isBlank(purOrgUnitNo))
			purOrgUnitNo = currentOrgUnitNo;
		Param param = new Param();
		param.setOrgUnitNo(currentOrgUnitNo);
		param.setControlUnitNo(currentControlUnitNo);
		HashMap<String, Object> map=new HashMap<>();
		map.put("minOrderDate", startDate);
		map.put("maxOrderDate", endDate);
		map.put("vendorName", vendorName);
		map.put("controlUnitNo", currentControlUnitNo);
		
		List<OrgStorage2> orgStorageList = orgStorageBiz.findChild(invOrgUnitNo, param);
        StringBuffer sbInv = new StringBuffer("");
        if (orgStorageList == null || orgStorageList.isEmpty()) {
            map.put("invOrgUnitNo", null);
        }
        if (orgStorageList != null && orgStorageList.size() > 0) {
            for(int i=0; i<orgStorageList.size(); i++) {
            	sbInv.append(orgStorageList.get(i).getOrgUnitNo());
                if (i == orgStorageList.size()-1) {
                    break;
                }
                sbInv.append(",");
            }
            map.put("invOrgUnitNo", sbInv.toString());
        }
		
        List<OrgPurchase2> orgPurchaseList = orgPurchaseBiz.findChild(purOrgUnitNo, param);
        StringBuffer sbPur = new StringBuffer("");
        if (orgPurchaseList == null || orgPurchaseList.isEmpty()) {
            map.put("purOrgUnitNo", null);
        }
        if (orgPurchaseList != null && orgPurchaseList.size() > 0) {
            sbPur.append("(");
            for(int i=0; i<orgPurchaseList.size(); i++) {
                sbPur.append("'").append(orgPurchaseList.get(i).getOrgUnitNo()).append("'");
                if (i == orgPurchaseList.size()-1) {
                    break;
                }
                sbPur.append(",");
            }
            sbPur.append(")");
            map.put("purOrgUnitNo", sbPur.toString());
        }
        map.put("unified", unified);
        map.put("self", self);
		List<ScmPurOrderTransInfo> list=((ScmPurReportDAO)dao).selectSupTransItemSummary(map);
		if(list != null && list.size()>0){
			for(int i=0;i<list.size();i++){
				ScmPurOrderTransInfo scmPurOrderTransInfo=list.get(i);
				scmPurOrderTransInfo.setNotAddInAmt(scmPurOrderTransInfo.getReceiveAmt().subtract(scmPurOrderTransInfo.getAddInAmt()));
				scmPurOrderTransInfo.setNotAddInQty(scmPurOrderTransInfo.getReceiveQty().subtract(scmPurOrderTransInfo.getAddInQty()));
				scmPurOrderTransInfo.setNotAddInTaxAmt(scmPurOrderTransInfo.getReceiveTaxAmt().subtract(scmPurOrderTransInfo.getAddInTaxAmt()));
				scmPurOrderTransInfo.setRealAddInAmt(scmPurOrderTransInfo.getAddInAmt().subtract(scmPurOrderTransInfo.getOutAmt()));
				scmPurOrderTransInfo.setRealAddInQty(scmPurOrderTransInfo.getAddInQty().subtract(scmPurOrderTransInfo.getOutQty()));
				scmPurOrderTransInfo.setRealAddInTaxAmt(scmPurOrderTransInfo.getAddInTaxAmt().subtract(scmPurOrderTransInfo.getOutTaxAmt()));
				if(scmPurOrderTransInfo.getRealAddInQty().compareTo(BigDecimal.ZERO)!=0) {
					scmPurOrderTransInfo.setRealAddInPrice(scmPurOrderTransInfo.getRealAddInAmt().divide(scmPurOrderTransInfo.getRealAddInQty(), 2, RoundingMode.HALF_UP));
					scmPurOrderTransInfo.setRealAddInTaxPrice(scmPurOrderTransInfo.getRealAddInTaxAmt().divide(scmPurOrderTransInfo.getRealAddInQty(), 2, RoundingMode.HALF_UP));
				}
				OrgBaseUnit orgBaseUnit = (OrgBaseUnit) cacheDataMap.get(ClassUtils.getFinalModelSimpleName(OrgBaseUnit.class)+"_"+scmPurOrderTransInfo.getPurOrgUnitNo());
				if(orgBaseUnit==null) {
					orgBaseUnit=orgUnitBiz.selectbyOrgNo(scmPurOrderTransInfo.getPurOrgUnitNo(), param);
					cacheDataMap.put(ClassUtils.getFinalModelSimpleName(OrgBaseUnit.class)+"_"+scmPurOrderTransInfo.getPurOrgUnitNo(),orgBaseUnit);
				}
				if(orgBaseUnit!=null){
					scmPurOrderTransInfo.setPurOrgUnitName(orgBaseUnit.getOrgUnitName());
				}	
				OrgBaseUnit orgBaseUnit2 = (OrgBaseUnit) cacheDataMap.get(ClassUtils.getFinalModelSimpleName(OrgBaseUnit.class)+"_"+scmPurOrderTransInfo.getStorageOrgUnitNo());
				if(orgBaseUnit2==null) {
					orgBaseUnit2=orgUnitBiz.selectbyOrgNo(scmPurOrderTransInfo.getStorageOrgUnitNo(), param);
					cacheDataMap.put(ClassUtils.getFinalModelSimpleName(OrgBaseUnit.class)+"_"+scmPurOrderTransInfo.getStorageOrgUnitNo(),orgBaseUnit2);
				}
				if(orgBaseUnit2!=null){
					scmPurOrderTransInfo.setStorageOrgUnitName(orgBaseUnit2.getOrgUnitName());
				}
				if(scmPurOrderTransInfo.getVendorId()>0){
					Scmsupplier2 scmsupplier = (Scmsupplier2) cacheDataMap.get(ClassUtils.getFinalModelSimpleName(Scmsupplier2.class)+"_"+scmPurOrderTransInfo.getVendorId());
					if(scmsupplier==null) {
						scmsupplier = scmsupplierBiz.selectDirect(scmPurOrderTransInfo.getVendorId(), param);
						cacheDataMap.put(ClassUtils.getFinalModelSimpleName(Scmsupplier2.class)+"_"+scmPurOrderTransInfo.getVendorId(),scmsupplier);
					}
					if(scmsupplier!=null)
						scmPurOrderTransInfo.setVendorName(scmsupplier.getVendorName());
					
				}
				if(scmPurOrderTransInfo.getItemId()>0){
					ScmMaterial2 scmMaterial = (ScmMaterial2) cacheDataMap.get(ClassUtils.getFinalModelSimpleName(ScmMaterial2.class)+"_"+scmPurOrderTransInfo.getItemId());
					if(scmMaterial==null) {
						scmMaterial = scmMaterialBiz.selectDirect(scmPurOrderTransInfo.getItemId(), param);
						cacheDataMap.put(ClassUtils.getFinalModelSimpleName(ScmMaterial2.class)+"_"+scmPurOrderTransInfo.getItemId(),scmMaterial);
					}
					if(scmMaterial!=null){
						scmPurOrderTransInfo.setItemNo(scmMaterial.getItemNo());
						scmPurOrderTransInfo.setItemName(scmMaterial.getItemName());
						scmPurOrderTransInfo.setSpec(scmMaterial.getSpec());
					}
				}
				if(scmPurOrderTransInfo.getPurUnit()>0){
					ScmMeasureUnit scmMeasureUnit = (ScmMeasureUnit) cacheDataMap.get(ClassUtils.getFinalModelSimpleName(ScmMeasureUnit.class)+"_"+scmPurOrderTransInfo.getPurUnit());
					if(scmMeasureUnit==null) {
						scmMeasureUnit = scmMeasureUnitBiz.selectDirect(scmPurOrderTransInfo.getPurUnit(), param);
						cacheDataMap.put(ClassUtils.getFinalModelSimpleName(ScmMeasureUnit.class)+"_"+scmPurOrderTransInfo.getPurUnit(),scmMeasureUnit);
					}
					if(scmMeasureUnit!=null)
						scmPurOrderTransInfo.setPurUnitName(scmMeasureUnit.getUnitName());
				}
			}
		}
		return list;
	}
		
	// 采购订单应到未到明细表
    @SuppressWarnings("unchecked")
    @Override
    public List<ScmPurOrder2> selectPODueOrNot(HttpServletRequest httpServletRequest) throws Exception {
        HashMap<String, Object> map = new HashMap<>();
		String currentOrgUnitNo=httpServletRequest.getParameter("orgUnitNo");
		String currentControlUnitNo=httpServletRequest.getParameter("controlUnitNo");
        String endDate = httpServletRequest.getParameter("endDate");
        String purBizType = httpServletRequest.getParameter("purBizType");
        String purOrgUnitNo = httpServletRequest.getParameter("purOrgUnitNo");
		if(StringUtils.isBlank(purOrgUnitNo))
			purOrgUnitNo = currentOrgUnitNo;
		Param param = new Param();
		param.setOrgUnitNo(currentOrgUnitNo);
		param.setControlUnitNo(currentControlUnitNo);
        List<OrgPurchase2> orgPurchaseList = orgPurchaseBiz.findChild(purOrgUnitNo, param);
        StringBuffer sbPur = new StringBuffer("");
        if (orgPurchaseList == null || orgPurchaseList.isEmpty()) {
            map.put("purOrgUnitNo", null);
        }
        if (orgPurchaseList != null && orgPurchaseList.size() > 0) {
            sbPur.append("(");
            for(int i=0; i<orgPurchaseList.size(); i++) {
                sbPur.append(orgPurchaseList.get(i).getOrgUnitNo());
                if (i == orgPurchaseList.size()-1) {
                    break;
                }
                sbPur.append(",");
            }
            sbPur.append(")");
            map.put("purOrgUnitNo", sbPur.toString());
        }
        String reqOrgUnitNo = httpServletRequest.getParameter("adminToPurFrom");
        List<OrgAdmin2> orgAdminList = orgAdminBiz.findChild(reqOrgUnitNo, param);
        StringBuffer sbReq = new StringBuffer("");
        if (orgAdminList == null || orgAdminList.isEmpty()) {
            map.put("reqOrgUnitNo", null);
        }
        if (orgAdminList != null && orgAdminList.size() > 0) {
            sbReq.append("(");
            for(int i=0; i<orgAdminList.size(); i++) {
                sbReq.append(orgAdminList.get(i).getOrgUnitNo());
                if (i == orgAdminList.size()-1) {
                    break;
                }
                sbReq.append(",");
            }
            sbReq.append(")");
            map.put("reqOrgUnitNo", sbReq.toString());
        }
        String vendorName = httpServletRequest.getParameter("vendorName");
        int vendorId = 0;
        if (StringUtils.isNotBlank(vendorName) && StringUtils.isNumeric(vendorName)) {
            vendorId = (Integer.parseInt(vendorName));
        }
        String materialName = httpServletRequest.getParameter("materialName");
        int materialId = 0;
        if (StringUtils.isNotBlank(materialName) && StringUtils.isNumeric(materialName)) {
            materialId = (Integer.parseInt(materialName));
        }
        String flag = httpServletRequest.getParameter("flag");//统计方式
        if (StringUtils.isBlank(flag)) {
            flag = "2"; //收货
        }
        String buyerName = httpServletRequest.getParameter("buyerName");
        int buyerId = 0;
        if (StringUtils.isNotBlank(buyerName) && StringUtils.isNumeric(buyerName)) {
            buyerId = (Integer.parseInt(buyerName));
        }
        String reqDate = httpServletRequest.getParameter("reqDate");
        map.put("endDate", endDate);
        map.put("bizType", purBizType);
        map.put("materialId", materialId);
        map.put("vendorId", vendorId);
        map.put("flag", flag);
        map.put("buyerId", buyerId);
        map.put("reqDate", reqDate);
        List<ScmPurOrder2> list = ((ScmPurReportDAO) dao).selectPODueOrNot(map);
        BigDecimal totalQty = BigDecimal.ZERO;
        BigDecimal totalRecOrInQty = BigDecimal.ZERO;
        BigDecimal totalNotRecQty = BigDecimal.ZERO;
        if (list != null && list.size() > 0 ) {
            for (int i = 0;i<list.size();i++) {
                ScmPurOrder2 scmPurOrder = list.get(i);
                if (scmPurOrder.getPurOrgUnitNo() != null){
                    //采购组织
                    OrgBaseUnit orgBaseUnit =  orgUnitBiz.selectbyOrgNo(scmPurOrder.getPurOrgUnitNo(), param);
                    if (orgBaseUnit != null) {
                        scmPurOrder.setPurOrgUnitNo(orgBaseUnit.getOrgUnitName());
                    }
                } 
                if (scmPurOrder.getRecOrgUnitNo() != null){
                    //收货组织
                    OrgBaseUnit orgBaseUnit =  orgUnitBiz.selectbyOrgNo(scmPurOrder.getRecOrgUnitNo(), param);
                    if (orgBaseUnit != null) {
                        scmPurOrder.setRecOrgUnitNo(orgBaseUnit.getOrgUnitName());
                    }
                }
                if (scmPurOrder.getStatus() != null){
                    //单据状态
                    Code code =  codeBiz.selectByCategoryAndCode("quotationStatus", scmPurOrder.getStatus());
                    if (code != null) {
                        scmPurOrder.setStatus(code.getName());
                    }
                }
                if (scmPurOrder.getBizType() != null){
                    //业务类型
                    Code code =  codeBiz.selectByCategoryAndCode("scmbizType", scmPurOrder.getBizType());
                    if (code != null) {
                        scmPurOrder.setBizType(code.getName());
                    }
                }
                if (scmPurOrder.getVendorId() > 0) {
                    Scmsupplier2 scmSupplier = scmsupplierBiz.selectDirect(scmPurOrder.getVendorId(), param);
                    if (scmSupplier != null) {
                        scmPurOrder.setVendorName(scmSupplier.getVendorName());
                    }
                }
                if (scmPurOrder.getPurUnit() > 0) {
                    ScmMeasureUnit scmMeasureUnit = scmMeasureUnitBiz.selectDirect(scmPurOrder.getPurUnit(), param);
                    if (scmMeasureUnit != null) {
                        scmPurOrder.setUnitName(scmMeasureUnit.getUnitName());
                    }
                }
                totalQty = totalQty.add(scmPurOrder.getQty());
                if ("1".equals(scmPurOrder.getFlag())) {
                    totalRecOrInQty = totalRecOrInQty.add(scmPurOrder.getAddinQty());
                } else if ("2".equals(scmPurOrder.getFlag())) {
                    totalRecOrInQty = totalRecOrInQty.add(scmPurOrder.getReceiveQty());
                }
                totalNotRecQty = totalNotRecQty.add(scmPurOrder.getNotReceiveQty());
            }
            for (int i = 0;i<list.size();i++) {
                list.get(i).setTotalQty(totalQty);
                list.get(i).setTotalRecOrInQty(totalRecOrInQty);
                list.get(i).setTotalNotRecQty(totalNotRecQty);
            }
        } 
        return list;
    }

    @Override
    public List<ScmPurRequire2> selectOrderDeliverySummary(HttpServletRequest httpServletRequest) throws Exception {
        // 1 获取查询参数
        HashMap<String, Object> map = new HashMap<>();
		String currentOrgUnitNo=httpServletRequest.getParameter("orgUnitNo");
		String currentControlUnitNo=httpServletRequest.getParameter("controlUnitNo");
        String purOrgUnitNo = httpServletRequest.getParameter("purOrgUnitNo");
        String date_s = httpServletRequest.getParameter("date_s");
        String date_e = httpServletRequest.getParameter("date_e");
        String vendorName = httpServletRequest.getParameter("vendorName");
		if(StringUtils.isBlank(purOrgUnitNo))
			purOrgUnitNo = currentOrgUnitNo;
		Param param = new Param();
		param.setOrgUnitNo(currentOrgUnitNo);
		param.setControlUnitNo(currentControlUnitNo);
        int vendorId = 0;
        if (StringUtils.isNotBlank(vendorName) && StringUtils.isNumeric(vendorName)) {
            vendorId = (Integer.parseInt(vendorName));
        }
        String reqOrgUnitNo = httpServletRequest.getParameter("adminToPurFrom");
        String materialClassName = httpServletRequest.getParameter("materialClassName");
        int materialClassId = 0;
        if (StringUtils.isNotBlank(materialClassName) && StringUtils.isNumeric(materialClassName)) {
            materialClassId = (Integer.parseInt(materialClassName));
            List<ScmMaterialGroup> scmMaterialGroupList = scmMaterialGroupBiz.findChild(materialClassId, param);
            StringBuffer sbMaterilaClass = new StringBuffer("");
            if (scmMaterialGroupList != null && scmMaterialGroupList.size() > 0) {
                sbMaterilaClass.append("(");
                for(int i=0; i<scmMaterialGroupList.size(); i++) {
                    sbMaterilaClass.append(scmMaterialGroupList.get(i).getId());
                    if (i == scmMaterialGroupList.size()-1) {
                        break;
                    }
                    sbMaterilaClass.append(",");
                }
                sbMaterilaClass.append(")");
                map.put("materialClassIds", sbMaterilaClass.toString());
            }
        }
        String flag = httpServletRequest.getParameter("flag");
        if (StringUtils.isBlank(flag)) {
            flag = "1"; //申购组织
        }
        String status = httpServletRequest.getParameter("status");
        // 2 设置查询条件
        List<OrgPurchase2> orgPurchaseList = orgPurchaseBiz.findChild(purOrgUnitNo, param);
        StringBuffer sbPur = new StringBuffer("");
        if (orgPurchaseList == null || orgPurchaseList.isEmpty()) {
            map.put("purOrgUnitNo", null);
        }
        if (orgPurchaseList != null && orgPurchaseList.size() > 0) {
            sbPur.append("(");
            for(int i=0; i<orgPurchaseList.size(); i++) {
                sbPur.append(orgPurchaseList.get(i).getOrgUnitNo());
                if (i == orgPurchaseList.size()-1) {
                    break;
                }
                sbPur.append(",");
            }
            sbPur.append(")");
            map.put("purOrgUnitNo", sbPur.toString());
        }
        List<OrgAdmin2> orgAdminList = orgAdminBiz.findChild(reqOrgUnitNo, param);
        StringBuffer sbReq = new StringBuffer("");
        if (orgAdminList == null || orgAdminList.isEmpty()) {
            map.put("reqOrgUnitNo", null);
        }
        if (orgAdminList != null && orgAdminList.size() > 0) {
            sbReq.append("(");
            for(int i=0; i<orgAdminList.size(); i++) {
                sbReq.append(orgAdminList.get(i).getOrgUnitNo());
                if (i == orgAdminList.size()-1) {
                    break;
                }
                sbReq.append(",");
            }
            sbReq.append(")");
            map.put("reqOrgUnitNo", sbReq.toString());
        }
        map.put("date_s", date_s);
        map.put("date_e", date_e);
        map.put("vendorId", vendorId);
        map.put("materialClassId", materialClassId);
        map.put("flag", flag);
        map.put("status", status);
        // 3 查询数据库
        List<ScmPurRequire2> list = ((ScmPurReportDAO) dao).selectOrderDeliverySummary(map);
        // 4 数据处理及返回
        BigDecimal totalAmt = new BigDecimal(BigInteger.ZERO);
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                ScmPurRequire2 scmPurRequire = list.get(i);
                if (scmPurRequire.getOrgUnitNo() != null){
                    //申购组织
                    OrgBaseUnit orgBaseUnit =  orgUnitBiz.selectbyOrgNo(scmPurRequire.getOrgUnitNo(), param);
                    if (orgBaseUnit != null) {
                        scmPurRequire.setOrgUnitNo(orgBaseUnit.getOrgUnitName());
                    }
                } 
                if (scmPurRequire.getFtype().equals("1")) {
                    totalAmt = totalAmt.add(scmPurRequire.getAmt());
                }
                if (StringUtils.isBlank(scmPurRequire.getSpec())) {
                    scmPurRequire.setSpec("");
                }
                if (StringUtils.isBlank(scmPurRequire.getVendorName())) {
                    scmPurRequire.setVendorName("*");
                }
            }
            for (int i = 0; i < list.size(); i++) {
                ScmPurRequire2 scmPurRequire = list.get(i);
                scmPurRequire.setTotalAmt(totalAmt);
            }
        } 
        return list;
    }

    // 部门申购汇总表
    @Override
    public List<ScmPurRequire2> selectDeptApplySummary(HttpServletRequest request) throws Exception {
        // 1 获取查询参数
        HashMap<String, Object> map = new HashMap<>();
		String currentOrgUnitNo=request.getParameter("orgUnitNo");
		String currentControlUnitNo=request.getParameter("controlUnitNo");
        String reqOrgUnitNo = request.getParameter("reqOrgUnitNo");      
        String vendorName = request.getParameter("vendorName");
        String dateType=request.getParameter("dateType");
        if("1".equals(dateType)){
        	String applyBegDate = request.getParameter("beginDate");
            String applyEndDate = request.getParameter("endDate");
        	map.put("applyBegDate", applyBegDate);
            map.put("applyEndDate", applyEndDate);
        }else if("2".equals(dateType)){
        	String reqBegDate = request.getParameter("beginDate");
            String reqEndDate = request.getParameter("endDate");
            map.put("reqBegDate", reqBegDate);
            map.put("reqEndDate", reqEndDate);
        }
       
		if(StringUtils.isBlank(reqOrgUnitNo))
			reqOrgUnitNo = currentOrgUnitNo;
		Param param = new Param();
		param.setOrgUnitNo(currentOrgUnitNo);
		param.setControlUnitNo(currentControlUnitNo);
        int vendorId = 0;
        if (StringUtils.isNotBlank(vendorName) && StringUtils.isNumeric(vendorName)) {
            vendorId = (Integer.parseInt(vendorName));
        }
        String materialClassName = request.getParameter("materialClassName");
        int materialClassId = 0;
        if (StringUtils.isNotBlank(materialClassName) && StringUtils.isNumeric(materialClassName)) {
            materialClassId = (Integer.parseInt(materialClassName));
            List<ScmMaterialGroup> scmMaterialGroupList = scmMaterialGroupBiz.findChild(materialClassId, param);
            StringBuffer sbMaterilaClass = new StringBuffer("");
            if (scmMaterialGroupList != null && scmMaterialGroupList.size() > 0) {
                sbMaterilaClass.append("(");
                for(int i=0; i<scmMaterialGroupList.size(); i++) {
                    sbMaterilaClass.append(scmMaterialGroupList.get(i).getId());
                    if (i == scmMaterialGroupList.size()-1) {
                        break;
                    }
                    sbMaterilaClass.append(",");
                }
                sbMaterilaClass.append(")");
                map.put("materialClassIds", sbMaterilaClass.toString());
            }
        }
        String status = request.getParameter("status");
        // 2 设置查询条件
        List<OrgAdmin2> orgAdminList = orgAdminBiz.findChild(reqOrgUnitNo, param);
        StringBuffer sbReq = new StringBuffer("");
        if (orgAdminList == null || orgAdminList.isEmpty()) {
            map.put("reqOrgUnitNo", null);
        }
        if (orgAdminList != null && orgAdminList.size() > 0) {
            sbReq.append("(");
            for(int i=0; i<orgAdminList.size(); i++) {
                sbReq.append(orgAdminList.get(i).getOrgUnitNo());
                if (i == orgAdminList.size()-1) {
                    break;
                }
                sbReq.append(",");
            }
            sbReq.append(")");
            map.put("reqOrgUnitNo", sbReq.toString());
        }
        map.put("vendorId", vendorId);
        map.put("materialClassId", materialClassId);
        map.put("status", status);
        // 3 查询数据库
        List<ScmPurRequire2> list = ((ScmPurReportDAO) dao).selectDeptApplySummary(map);
        // 4 数据处理及返回
        BigDecimal totalAmt = new BigDecimal(BigInteger.ZERO);
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                ScmPurRequire2 scmPurRequire = list.get(i);
                if (scmPurRequire.getOrgUnitNo() != null) {
                    // 申购组织
                    OrgBaseUnit orgBaseUnit = orgUnitBiz.selectbyOrgNo(scmPurRequire.getOrgUnitNo(), param);
                    if (orgBaseUnit != null) {
                        scmPurRequire.setOrgUnitNo(orgBaseUnit.getOrgUnitName());
                    }
                }
                if (scmPurRequire.getFtype().equals("1")) {
                    totalAmt = totalAmt.add(scmPurRequire.getAmt());
                }
                if (StringUtils.isBlank(scmPurRequire.getSpec())) {
                    scmPurRequire.setSpec("");
                }
                if (StringUtils.isBlank(scmPurRequire.getVendorName())) {
                    scmPurRequire.setVendorName("*");
                }
            }
            for (int i = 0; i < list.size(); i++) {
                ScmPurRequire2 scmPurRequire = list.get(i);
                scmPurRequire.setTotalAmt(totalAmt);
            }
        }
        return list;
    }
	
    @Override
    public List<ScmPurPrice2> selectPurPriceInfo(HttpServletRequest httpServletRequest) throws Exception {
        // 1 获取查询参数
        HashMap<String, Object> map = new HashMap<>();
		String currentOrgUnitNo=httpServletRequest.getParameter("orgUnitNo");
		String currentControlUnitNo=httpServletRequest.getParameter("controlUnitNo");
        String purOrgUnitNo = httpServletRequest.getParameter("purOrgUnitNo");
        String bizDate = httpServletRequest.getParameter("bizDate");
        String materialName = httpServletRequest.getParameter("materialName");
		if(StringUtils.isBlank(purOrgUnitNo))
			purOrgUnitNo = currentOrgUnitNo;
		Param param = new Param();
		param.setOrgUnitNo(currentOrgUnitNo);
		param.setControlUnitNo(currentControlUnitNo);
        int materialId = 0;
        if (StringUtils.isNotBlank(materialName) && StringUtils.isNumeric(materialName)) {
            materialId = (Integer.parseInt(materialName));
        }
        String materialClassName = httpServletRequest.getParameter("materialClassName");
        int materialClassId = 0;
        if (StringUtils.isNotBlank(materialClassName) && StringUtils.isNumeric(materialClassName)) {
            materialClassId = (Integer.parseInt(materialClassName));
            List<ScmMaterialGroup> scmMaterialGroupList = scmMaterialGroupBiz.findChild(materialClassId, param);
            StringBuffer sbMaterilaClass = new StringBuffer("");
            if (scmMaterialGroupList != null && scmMaterialGroupList.size() > 0) {
                sbMaterilaClass.append("(");
                for(int i=0; i<scmMaterialGroupList.size(); i++) {
                    sbMaterilaClass.append(scmMaterialGroupList.get(i).getId());
                    if (i == scmMaterialGroupList.size()-1) {
                        break;
                    }
                    sbMaterilaClass.append(",");
                }
                sbMaterilaClass.append(")");
                map.put("materialClassIds", sbMaterilaClass.toString());
            }
        }
        String priceType = httpServletRequest.getParameter("priceType");
        if (priceType == null || priceType.length() == 0) {
            priceType = "1";
        }
        // 获取当前采购组织底下的全部采购组织
        List<OrgPurchase2> purList = orgPurchaseBiz.findChild(purOrgUnitNo, param);
        StringBuffer sbPur = new StringBuffer("");
        if (purList != null && purList.size() > 0) {
            for (int i = 0; i < purList.size(); i++) {
                sbPur.append(purList.get(i).getOrgUnitNo());
                if (i == purList.size()-1) {
                    break;
                }
                sbPur.append(",");
            }
        }
        // 2 设置查询条件
        map.put("purOrgUnitNo", sbPur.toString());  // 报价定价使用的都是采购组织及下级
        map.put("bizDate", bizDate);
        map.put("materialId", materialId);
        map.put("priceType", priceType);
        // 3 查询数据库
        List<ScmPurPrice2> list = ((ScmPurReportDAO) dao).selectPurPriceInfo(map);
        BigDecimal totalAmt = BigDecimal.ZERO;
        // 4 数据处理及返回
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                ScmPurPrice2 scmPurPrice = list.get(i);
                if (scmPurPrice.getPurOrgUnitNo() != null) { 
                    OrgBaseUnit orgBaseUnit = orgUnitBiz.selectbyOrgNo(scmPurPrice.getPurOrgUnitNo(), param);
                    if (orgBaseUnit != null) {
                        scmPurPrice.setPurOrgUnitNo(orgBaseUnit.getOrgUnitName());
                    }
                }
                if (scmPurPrice.getSelVndrId() > 0){
                    //供应商
                    Scmsupplier2 scmsupplier = scmsupplierBiz.selectDirect(scmPurPrice.getSelVndrId(), param);
                    if (scmsupplier != null) {
                        scmPurPrice.setVendorName(scmsupplier.getVendorName());
                    }
                }
                if (StringUtils.isNotBlank(scmPurPrice.getPurUnit())){
                    //库存单位
                    ScmMeasureUnit scmMeasureUnit = scmMeasureUnitBiz.selectDirect(Integer.parseInt(scmPurPrice.getPurUnit()), param);
                    if (scmMeasureUnit != null) {
                        scmPurPrice.setUnitName(scmMeasureUnit.getUnitName());
                    }
                }
                // 定价类型1, 显示的是pmNo;
                // 报价类型2, 显示的是pqNo;
//                if (scmPurPrice.getPriceType().equals("1")) {
//                    scmPurPrice.setPqNo("0");
//                } else if (scmPurPrice.getPriceType().equals("2")) {
//                    scmPurPrice.setPmNo("0");
//                }
//                totalAmt = totalAmt.add(scmPurPrice.getPrice());
            }
        } 
        return list;
    }
    
 	@Override
	public List<ScmPurPrice2> selectPurPriceInfoCheck(HttpServletRequest request) throws Exception {
        // 1 获取查询参数
        HashMap<String, Object> map = new HashMap<>();
		String currentOrgUnitNo=request.getParameter("orgUnitNo");
		String currentControlUnitNo=request.getParameter("controlUnitNo");
        String purOrgUnitNo = request.getParameter("purOrgUnitNo");
        String bizDate = request.getParameter("bizDate");
		if(StringUtils.isBlank(purOrgUnitNo))
			purOrgUnitNo = currentOrgUnitNo;
		Param param = new Param();
		param.setOrgUnitNo(currentOrgUnitNo);
		param.setControlUnitNo(currentControlUnitNo);
        String materialName = request.getParameter("materialName");
        int materialId = 0;
        if (StringUtils.isNotBlank(materialName) && StringUtils.isNumeric(materialName)) {
            materialId = (Integer.parseInt(materialName));
        }
        String materialClassName = request.getParameter("materialClassName");
        int materialClassId = 0;
        if (StringUtils.isNotBlank(materialClassName) && StringUtils.isNumeric(materialClassName)) {
            materialClassId = (Integer.parseInt(materialClassName));
            List<ScmMaterialGroup> scmMaterialGroupList = scmMaterialGroupBiz.findChild(materialClassId, param);
            StringBuffer sbMaterilaClass = new StringBuffer("");
            if (scmMaterialGroupList != null && scmMaterialGroupList.size() > 0) {
                sbMaterilaClass.append("(");
                for(int i=0; i<scmMaterialGroupList.size(); i++) {
                    sbMaterilaClass.append(scmMaterialGroupList.get(i).getId());
                    if (i == scmMaterialGroupList.size()-1) {
                        break;
                    }
                    sbMaterilaClass.append(",");
                }
                sbMaterilaClass.append(")");
                map.put("materialClassIds", sbMaterilaClass.toString());
            }
        }
        String priceType = request.getParameter("priceType");
        if (priceType == null || priceType.length() == 0) {
            priceType = "1";
        }
        // 获取当前采购组织底下的全部采购组织
        List<OrgPurchase2> purList = orgPurchaseBiz.findChild(purOrgUnitNo, param);
        StringBuffer sbPur = new StringBuffer("");
        if (purList != null && purList.size() > 0) {
            for (int i = 0; i < purList.size(); i++) {
                sbPur.append(purList.get(i).getOrgUnitNo());
                if (i == purList.size()-1) {
                    break;
                }
                sbPur.append(",");
            }
        }
        // 2 设置查询条件
        map.put("purOrgUnitNo", sbPur.toString());  // 报价定价使用的都是采购组织及下级
        map.put("bizDate", bizDate);
        map.put("materialId", materialId);
        map.put("priceType", priceType);
        
        List<ScmPurPrice2> list = ((ScmPurReportDAO) dao).selectPurPriceInfoCheck(map);
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                ScmPurPrice2 scmPurPrice = list.get(i);
                if (scmPurPrice.getPurOrgUnitNo() != null) { 
                    OrgBaseUnit orgBaseUnit = orgUnitBiz.selectbyOrgNo(scmPurPrice.getPurOrgUnitNo(), param);
                    if (orgBaseUnit != null) {
                        scmPurPrice.setPurOrgUnitNo(orgBaseUnit.getOrgUnitName());
                    }
                }
                if (StringUtils.isNotBlank(scmPurPrice.getPurUnit())){
                    //库存单位
                    ScmMeasureUnit scmMeasureUnit = scmMeasureUnitBiz.selectDirect(Integer.parseInt(scmPurPrice.getPurUnit()), param);
                    if (scmMeasureUnit != null) {
                        scmPurPrice.setUnitName(scmMeasureUnit.getUnitName());
                    }
                }
                if (list.get(i).getCreator()!=null) { 
                	//制单人
                	Usr usr = usrBiz.selectByCode(list.get(i).getCreator(), param);
                	if (usr != null) {
                        scmPurPrice.setCreator(usr.getName());
                    }
                }
            }
        }
        return list;
	}
    
 	/*采购历史价格查询表*/
	@Override
	public List<ScmPurHistoryPrice> selectPurHistoryPrice(HttpServletRequest httpServletRequest) {
		String currentOrgUnitNo=httpServletRequest.getParameter("orgUnitNo");
		String currentControlUnitNo=httpServletRequest.getParameter("controlUnitNo");
		String purOrgUnitNo=httpServletRequest.getParameter("purOrgUnitNo");
		String vendorName=httpServletRequest.getParameter("vendorName");
		String materialName=httpServletRequest.getParameter("materialName");
		String rowStatus=httpServletRequest.getParameter("rowStatus");
		String minOrderDate=httpServletRequest.getParameter("minOrderDate");
		String maxOrderDate=httpServletRequest.getParameter("maxOrderDate");
		if(StringUtils.isBlank(purOrgUnitNo))
			purOrgUnitNo = currentOrgUnitNo;
		Param param = new Param();
		param.setOrgUnitNo(currentOrgUnitNo);
		param.setControlUnitNo(currentControlUnitNo);
		HashMap<String, Object> map=new HashMap<>();
		
        List<OrgPurchase2> orgPurchaseList = orgPurchaseBiz.findChild(purOrgUnitNo, param);
        StringBuffer sbPur = new StringBuffer("");
        if (orgPurchaseList == null || orgPurchaseList.isEmpty()) {
            map.put("purOrgUnitNo", null);
        }
        if (orgPurchaseList != null && orgPurchaseList.size() > 0) {
            sbPur.append("(");
            for(int i=0; i<orgPurchaseList.size(); i++) {
                sbPur.append(orgPurchaseList.get(i).getOrgUnitNo());
                if (i == orgPurchaseList.size()-1) {
                    break;
                }
                sbPur.append(",");
            }
            sbPur.append(")");
            map.put("purOrgUnitNo", sbPur.toString());
        }
		
		map.put("minOrderDate", minOrderDate);
	 	map.put("maxOrderDate", maxOrderDate);
		map.put("vendorName", vendorName);
		map.put("materialName", materialName);
		map.put("rowStatus", rowStatus);
		List<ScmPurHistoryPrice> list=((ScmPurReportDAO)dao).selectPurHistoryPrice(map);
		if(list != null && list.size()>0){
			for(int i=0;i<list.size();i++){
				ScmPurHistoryPrice scmPurHistoryPrice=list.get(i);
				OrgBaseUnit orgBaseUnit=orgUnitBiz.selectbyOrgNo(scmPurHistoryPrice.getPurOrgUnitNo(), param);
				if(orgBaseUnit!=null){
					scmPurHistoryPrice.setPurOrgUnitName(orgBaseUnit.getOrgUnitName());
				}	
			}
		}
		return list;
	}
	
	/*供应商交易明细表     */
	@Override
	public List<ScmPurOrderTransInfo> selectSupplierDetails(HttpServletRequest request) throws Exception {
		long ss = new Date().getTime();
		String currentOrgUnitNo=request.getParameter("orgUnitNo");
		String currentControlUnitNo=request.getParameter("controlUnitNo");
		String purOrgUnitNo=request.getParameter("purOrgUnitNo");
		String invOrgUnitNo=request.getParameter("purToInvTo");
		String vendorName=request.getParameter("vendorName");
		String minOrderDate=request.getParameter("minOrderDate");
		String maxOrderDate=request.getParameter("maxOrderDate");
		if(StringUtils.isBlank(purOrgUnitNo))
			purOrgUnitNo = currentOrgUnitNo;
		String unified=request.getParameter("unified");
		if(StringUtils.isBlank(unified))
			unified="2";//默认全部
		Param param = new Param();
		param.setOrgUnitNo(currentOrgUnitNo);
		param.setControlUnitNo(currentControlUnitNo);
		HashMap<String, Object> map=new HashMap<>();
		
		map.put("vendorName", vendorName);
		
		List<OrgStorage2> orgStorageList = orgStorageBiz.findChild(invOrgUnitNo, param);
        StringBuffer sbInv = new StringBuffer("");
        if (orgStorageList == null || orgStorageList.isEmpty()) {
            map.put("invOrgUnitNo", null);
        }
        if (orgStorageList != null && orgStorageList.size() > 0) {
        	sbInv.append("(");
            for(int i=0; i<orgStorageList.size(); i++) {
            	sbInv.append(orgStorageList.get(i).getOrgUnitNo());
                if (i == orgStorageList.size()-1) {
                    break;
                }
                sbInv.append(",");
            }
            sbInv.append(")");
            map.put("invOrgUnitNo", sbInv.toString());
        }
		
        List<OrgPurchase2> orgPurchaseList = orgPurchaseBiz.findChild(purOrgUnitNo, param);
        StringBuffer sbPur = new StringBuffer("");
        if (orgPurchaseList == null || orgPurchaseList.isEmpty()) {
            map.put("purOrgUnitNo", null);
        }
        if (orgPurchaseList != null && orgPurchaseList.size() > 0) {
            sbPur.append("(");
            for(int i=0; i<orgPurchaseList.size(); i++) {
                sbPur.append(orgPurchaseList.get(i).getOrgUnitNo());
                if (i == orgPurchaseList.size()-1) {
                    break;
                }
                sbPur.append(",");
            }
            sbPur.append(")");
            map.put("purOrgUnitNo", sbPur.toString());
        }
		
		map.put("minOrderDate", minOrderDate);
		map.put("maxOrderDate", maxOrderDate);
		map.put("unified", unified);
		List<ScmPurOrderTransInfo> list=((ScmPurReportDAO)dao).selectSupplierDetails(map);
		if(list != null && list.size()>0){
			for(int i=0;i<list.size();i++){
				ScmPurOrderTransInfo scmPurOrderTransInfo=list.get(i);
				OrgBaseUnit orgBaseUnit=orgUnitBiz.selectbyOrgNo(scmPurOrderTransInfo.getPurOrgUnitNo(), param);
				OrgBaseUnit orgBaseUnit2=orgUnitBiz.selectbyOrgNo(scmPurOrderTransInfo.getStorageOrgUnitNo(), param);
				OrgBaseUnit orgBaseUnit3=orgUnitBiz.selectbyOrgNo(scmPurOrderTransInfo.getFinOrgUnitNo(), param);
				if(orgBaseUnit!=null){
					scmPurOrderTransInfo.setPurOrgUnitName(orgBaseUnit.getOrgUnitName());
				}
				if(orgBaseUnit2!=null){
					scmPurOrderTransInfo.setStorageOrgUnitName(orgBaseUnit2.getOrgUnitName());			
				}
				if(orgBaseUnit3!=null){
					scmPurOrderTransInfo.setFinOrgUnitName(orgBaseUnit3.getOrgUnitName());
				}
				if(scmPurOrderTransInfo.getVendorId()>0){
					Scmsupplier2 scmsupplier = scmsupplierBiz.selectDirect(scmPurOrderTransInfo.getVendorId(), param);
					if(scmsupplier!=null)
						scmPurOrderTransInfo.setVendorName(scmsupplier.getVendorName());
					
				}
				if(scmPurOrderTransInfo.getItemId()>0){
					ScmMaterial2 scmMaterial = scmMaterialBiz.selectDirect(scmPurOrderTransInfo.getItemId(), param);
					if(scmMaterial!=null){
						scmPurOrderTransInfo.setItemNo(scmMaterial.getItemNo());
						scmPurOrderTransInfo.setItemName(scmMaterial.getItemName());
						scmPurOrderTransInfo.setSpec(scmMaterial.getSpec());
					}
				}
				if(scmPurOrderTransInfo.getPurGroupId()>0){
					ScmPurGroup scmPurGroup = scmPurGroupBiz.selectDirect(scmPurOrderTransInfo.getPurGroupId(), param);
					if(scmPurGroup!=null)
						scmPurOrderTransInfo.setPurGroupName(scmPurGroup.getPurGroupName());
				}
				if(scmPurOrderTransInfo.getBuyerId()>0){
					ScmPurBuyer2 scmPurBuyer = scmPurBuyerBiz.select(scmPurOrderTransInfo.getBuyerId(), param);
					if(scmPurBuyer!=null)
						scmPurOrderTransInfo.setBuyerName(scmPurBuyer.getBuyerName());
				}
				if(scmPurOrderTransInfo.getPurUnit()>0){
					ScmMeasureUnit scmMeasureUnit = scmMeasureUnitBiz.selectDirect(scmPurOrderTransInfo.getPurUnit(), param);
					if(scmMeasureUnit!=null)
						scmPurOrderTransInfo.setPurUnitName(scmMeasureUnit.getUnitName());
				}
				scmPurOrderTransInfo.setNotAddInAmt(scmPurOrderTransInfo.getReceiveAmt().subtract(scmPurOrderTransInfo.getAddInAmt()));
				scmPurOrderTransInfo.setNotAddInQty(scmPurOrderTransInfo.getReceiveQty().subtract(scmPurOrderTransInfo.getAddInQty()));
				scmPurOrderTransInfo.setNotAddInTaxAmt(scmPurOrderTransInfo.getReceiveTaxAmt().subtract(scmPurOrderTransInfo.getAddInTaxAmt()));
				scmPurOrderTransInfo.setRealAddInAmt(scmPurOrderTransInfo.getAddInAmt().subtract(scmPurOrderTransInfo.getOutAmt()));
				scmPurOrderTransInfo.setRealAddInQty(scmPurOrderTransInfo.getAddInQty().subtract(scmPurOrderTransInfo.getOutQty()));
				scmPurOrderTransInfo.setRealAddInTaxAmt(scmPurOrderTransInfo.getAddInTaxAmt().subtract(scmPurOrderTransInfo.getOutTaxAmt()));
				if(scmPurOrderTransInfo.getRealAddInQty().compareTo(BigDecimal.ZERO)!=0) {
					scmPurOrderTransInfo.setRealAddInPrice(scmPurOrderTransInfo.getRealAddInAmt().divide(scmPurOrderTransInfo.getRealAddInQty(), 2, RoundingMode.HALF_UP));
					scmPurOrderTransInfo.setRealAddInTaxPrice(scmPurOrderTransInfo.getRealAddInTaxAmt().divide(scmPurOrderTransInfo.getRealAddInQty(), 2, RoundingMode.HALF_UP));
				}
			}
		}
		log.info("selectSupplierDetails取数用时：" + (new Date().getTime() - ss) + "毫秒");
		return list;
	}
	
	/*供应商交易汇总表*/
	@Override
	public List<ScmPurOrderTransTotal> selectSupplierSummary(HttpServletRequest request) throws Exception {
		String currentOrgUnitNo=request.getParameter("orgUnitNo");
		String currentControlUnitNo=request.getParameter("controlUnitNo");
		String purOrgUnitNo=request.getParameter("purOrgUnitNo");
		String invOrgUnitNo=request.getParameter("purToInvTo");
		String vendorName=request.getParameter("vendorName");
		if(StringUtils.isBlank(purOrgUnitNo))
			purOrgUnitNo = currentOrgUnitNo;
		String unified=request.getParameter("unified");
		if(StringUtils.isBlank(unified))
			unified = "2";	//默认全部
		Param param = new Param();
		param.setOrgUnitNo(currentOrgUnitNo);
		param.setControlUnitNo(currentControlUnitNo);
		String minOrderDate=request.getParameter("minOrderDate");
		String maxOrderDate=request.getParameter("maxOrderDate");
		HashMap<String, Object> map=new HashMap<>();
		map.put("vendorName", vendorName);
		
		List<OrgStorage2> orgStorageList = orgStorageBiz.findChild(invOrgUnitNo, param);
        StringBuffer sbInv = new StringBuffer("");
        if (orgStorageList == null || orgStorageList.isEmpty()) {
            map.put("invOrgUnitNo", null);
        }
        if (orgStorageList != null && orgStorageList.size() > 0) {
        	sbInv.append("(");
            for(int i=0; i<orgStorageList.size(); i++) {
            	sbInv.append(orgStorageList.get(i).getOrgUnitNo());
                if (i == orgStorageList.size()-1) {
                    break;
                }
                sbInv.append(",");
            }
            sbInv.append(")");
            map.put("invOrgUnitNo", sbInv.toString());
        }
		
        List<OrgPurchase2> orgPurchaseList = orgPurchaseBiz.findChild(purOrgUnitNo, param);
        StringBuffer sbPur = new StringBuffer("");
        if (orgPurchaseList == null || orgPurchaseList.isEmpty()) {
            map.put("purOrgUnitNo", null);
        }
        if (orgPurchaseList != null && orgPurchaseList.size() > 0) {
            sbPur.append("(");
            for(int i=0; i<orgPurchaseList.size(); i++) {
                sbPur.append(orgPurchaseList.get(i).getOrgUnitNo());
                if (i == orgPurchaseList.size()-1) {
                    break;
                }
                sbPur.append(",");
            }
            sbPur.append(")");
            map.put("purOrgUnitNo", sbPur.toString());
        }
		
		map.put("minOrderDate", minOrderDate);
		map.put("maxOrderDate", maxOrderDate);
		map.put("unified", unified);
		List<ScmPurOrderTransTotal> list=((ScmPurReportDAO)dao).selectSupplierSummary(map);
		if(list != null && list.size()>0){
			for(int i=0;i<list.size();i++){
				ScmPurOrderTransTotal scmPurOrderTransTotal=list.get(i);
				OrgBaseUnit orgBaseUnit=orgUnitBiz.selectbyOrgNo(scmPurOrderTransTotal.getPurOrgUnitNo(), param);
				if(orgBaseUnit!=null){
					scmPurOrderTransTotal.setPurOrgUnitName(orgBaseUnit.getOrgUnitName());
				}
				OrgBaseUnit orgBaseUnit2=orgUnitBiz.selectbyOrgNo(scmPurOrderTransTotal.getStorageOrgUnitNo(), param);
				if(orgBaseUnit2!=null){
					scmPurOrderTransTotal.setStorageOrgUnitName(orgBaseUnit2.getOrgUnitName());			
				}
				if(scmPurOrderTransTotal.getVendorId()>0){
					Scmsupplier2 scmsupplier = scmsupplierBiz.selectDirect(scmPurOrderTransTotal.getVendorId(), param);
					if(scmsupplier!=null){
						scmPurOrderTransTotal.setVendorNo(scmsupplier.getVendorNo());
						scmPurOrderTransTotal.setVendorName(scmsupplier.getVendorName());
					}
				}
				scmPurOrderTransTotal.setNotAddInAmt(scmPurOrderTransTotal.getReceiveAmt().subtract(scmPurOrderTransTotal.getAddInAmt()));
				scmPurOrderTransTotal.setNotAddInTaxAmt(scmPurOrderTransTotal.getReceiveTaxAmt().subtract(scmPurOrderTransTotal.getAddInTaxAmt()));
				scmPurOrderTransTotal.setRealAddInAmt(scmPurOrderTransTotal.getAddInAmt().subtract(scmPurOrderTransTotal.getOutAmt()));
				scmPurOrderTransTotal.setRealAddInTaxAmt(scmPurOrderTransTotal.getAddInTaxAmt().subtract(scmPurOrderTransTotal.getOutTaxAmt()));
			}
		}
		return list;
	}
	
	/* 供应商订货汇总表 */
    @Override
    public List<ScmPurRequireEntry2> selectSupplierOrderSummary(HttpServletRequest request) throws Exception {
		String currentOrgUnitNo=request.getParameter("orgUnitNo");
		String currentControlUnitNo=request.getParameter("controlUnitNo");
        String purOrgUnitNo = request.getParameter("purOrgUnitNo");
        String minOrderDate = request.getParameter("minOrderDate");
        String maxOrderDate = request.getParameter("maxOrderDate");
        String vendorName = request.getParameter("vendorName");
		if(StringUtils.isBlank(purOrgUnitNo))
			purOrgUnitNo = currentOrgUnitNo;
		Param param = new Param();
		param.setOrgUnitNo(currentOrgUnitNo);
		param.setControlUnitNo(currentControlUnitNo);
        int vendorId = 0;
        if (StringUtils.isNotBlank(vendorName) && StringUtils.isNumeric(vendorName)) {
            vendorId = (Integer.parseInt(vendorName));
        }
        String reqOrgUnitNo = request.getParameter("adminToPurFrom");
        String materialClassName = request.getParameter("materialClassName");
        String rowStatus = request.getParameter("rowStatus");
        String groupType = request.getParameter("groupType");
        if (StringUtils.isBlank(groupType)) {
            groupType = "1"; //申购组织汇总
        }
        HashMap<String, Object> map = new HashMap<>();
        List<OrgAdmin2> orgAdminList = orgAdminBiz.findChild(reqOrgUnitNo, param);
        StringBuffer sbReq = new StringBuffer("");
        if (orgAdminList == null || orgAdminList.isEmpty()) {
            map.put("reqOrgUnitNo", null);
        }
        if (orgAdminList != null && orgAdminList.size() > 0) {
            sbReq.append("(");
            for (int i = 0; i < orgAdminList.size(); i++) {
                sbReq.append(orgAdminList.get(i).getOrgUnitNo());
                if (i == orgAdminList.size() - 1) {
                    break;
                }
                sbReq.append(",");
            }
            sbReq.append(")");
            map.put("reqOrgUnitNo", sbReq.toString());
        }
        List<OrgPurchase2> orgPurchaseList = orgPurchaseBiz.findChild(purOrgUnitNo, param);
        StringBuffer sbPur = new StringBuffer("");
        if (orgPurchaseList == null || orgPurchaseList.isEmpty()) {
            map.put("purOrgUnitNo", null);
        }
        if (orgPurchaseList != null && orgPurchaseList.size() > 0) {
            sbPur.append("(");
            for (int i = 0; i < orgPurchaseList.size(); i++) {
                sbPur.append(orgPurchaseList.get(i).getOrgUnitNo());
                if (i == orgPurchaseList.size() - 1) {
                    break;
                }
                sbPur.append(",");
            }
            sbPur.append(")");
            map.put("purOrgUnitNo", sbPur.toString());
        }
        int materialClassId = 0;
        if (StringUtils.isNotBlank(materialClassName) && StringUtils.isNumeric(materialClassName)) {
            materialClassId = (Integer.parseInt(materialClassName));
            List<ScmMaterialGroup> scmMaterialGroupList = scmMaterialGroupBiz.findChild(materialClassId, param);
            StringBuffer sbMaterilaClass = new StringBuffer("");
            if (scmMaterialGroupList != null && scmMaterialGroupList.size() > 0) {
                sbMaterilaClass.append("(");
                for (int i = 0; i < scmMaterialGroupList.size(); i++) {
                    sbMaterilaClass.append(scmMaterialGroupList.get(i).getId());
                    if (i == scmMaterialGroupList.size() - 1) {
                        break;
                    }
                    sbMaterilaClass.append(",");
                }
                sbMaterilaClass.append(")");
                map.put("materialClassIds", sbMaterilaClass.toString());
            }
        }
        map.put("minOrderDate", minOrderDate);
        map.put("maxOrderDate", maxOrderDate);
        map.put("vendorId", vendorId);
        map.put("rowStatus", rowStatus);
        map.put("groupType", groupType);
        List<ScmPurRequireEntry2> list = ((ScmPurReportDAO) dao).selectSupplierOrderSummary(map);
        BigDecimal totalAmt = BigDecimal.ZERO;
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                ScmPurRequireEntry2 scmPurRequireEntry = list.get(i);
                if (scmPurRequireEntry.getOrgUnitNo() != null) {
                    OrgBaseUnit orgBaseUnit = orgUnitBiz.selectbyOrgNo(scmPurRequireEntry.getOrgUnitNo(), param);
                    if (orgBaseUnit != null) {
                        scmPurRequireEntry.setOrgUnitNo(orgBaseUnit.getOrgUnitName());
                    }
                }
                if (scmPurRequireEntry.getVendorId() > 0) {
                    Scmsupplier2 scmSupplier = scmsupplierBiz.select(scmPurRequireEntry.getVendorId(), param);
                    if (scmSupplier != null) {
                        scmPurRequireEntry.setVendorName(scmSupplier.getVendorName());
                    }
                }
                // 合计
                totalAmt = totalAmt.add(scmPurRequireEntry.getAmt());
            }
            for (int i = 0; i < list.size(); i++) {
                list.get(i).setTotalAmt(totalAmt);
            }
        }
        return list;
    }
    /*
	 * 采购退货情况表
	 */
    @Override
	public List<ScmPurReturnInfo> selectPurchaseReturn(HttpServletRequest request) throws Exception {
		String currentOrgUnitNo=request.getParameter("orgUnitNo");
		String currentControlUnitNo=request.getParameter("controlUnitNo");
        String purOrgUnitNo = request.getParameter("purOrgUnitNo");// 采购组织
        String minOrderDate = request.getParameter("minOrderDate");// 退货日期
        String maxOrderDate = request.getParameter("maxOrderDate");
        String invOrgUnitNo = request.getParameter("purToInvTo");// 库存组织
        String vendorName = request.getParameter("vendorName");
        String status = request.getParameter("status");
        if(StringUtils.isBlank(status)) 
        	status="A";
        String reasonCode = request.getParameter("reasonCode");
        if(StringUtils.isBlank(purOrgUnitNo))
			purOrgUnitNo = currentOrgUnitNo;
		Param param = new Param();
		param.setOrgUnitNo(currentOrgUnitNo);
		param.setControlUnitNo(currentControlUnitNo);
        int vendorId = 0;
        if (StringUtils.isNotBlank(vendorName) && StringUtils.isNumeric(vendorName)) {
            vendorId = (Integer.parseInt(vendorName));
        }
        HashMap<String, Object> map = new HashMap<>();
        List<OrgPurchase2> orgPurchaseList = orgPurchaseBiz.findChild(purOrgUnitNo, param);
        StringBuffer sbPur = new StringBuffer("");
        if (orgPurchaseList == null || orgPurchaseList.isEmpty()) {
            map.put("purOrgUnitNo", null);
        }
        if (orgPurchaseList != null && orgPurchaseList.size() > 0) {
            sbPur.append("(");
            for (int i = 0; i < orgPurchaseList.size(); i++) {
                sbPur.append(orgPurchaseList.get(i).getOrgUnitNo());
                if (i == orgPurchaseList.size() - 1) {
                    break;
                }
                sbPur.append(",");
            }
            sbPur.append(")");
            map.put("purOrgUnitNo", sbPur.toString());
        }
        map.put("minOrderDate", minOrderDate);
        map.put("maxOrderDate", maxOrderDate);
        map.put("invOrgUnitNo", invOrgUnitNo);
        map.put("vendorId", vendorId);
        map.put("status", status);
        if(StringUtils.isNotBlank(reasonCode) && !StringUtils.equals("0", reasonCode))
        	map.put("reasonCode", reasonCode);
        List<ScmPurReturnInfo> list = ((ScmPurReportDAO) dao).selectPurchaseReturn(map);
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
            	ScmPurReturnInfo scmPurReturnInfo = list.get(i);
                if (scmPurReturnInfo.getInvOrgUnitNo() != null) {
                    OrgBaseUnit orgBaseUnit = orgUnitBiz.selectbyOrgNo(scmPurReturnInfo.getInvOrgUnitNo(), param);
                    if (orgBaseUnit != null) {
                    	scmPurReturnInfo.setInvOrgUnitName(orgBaseUnit.getOrgUnitName());
                    }
                }
                if (scmPurReturnInfo.getPurUnit() > 0){
                    //库存单位
                    ScmMeasureUnit scmMeasureUnit = scmMeasureUnitBiz.selectDirect(scmPurReturnInfo.getPurUnit(), param);
                    if (scmMeasureUnit != null) {
                    	scmPurReturnInfo.setUnitName(scmMeasureUnit.getUnitName());
                    }
                }
                if (scmPurReturnInfo.getVendorId() > 0){
                    //供应商
                    Scmsupplier2 scmsupplier = scmsupplierBiz.selectDirect(scmPurReturnInfo.getVendorId(), param);
                    if (scmsupplier != null) {
                    	scmPurReturnInfo.setVendorName(scmsupplier.getVendorName());
                    }
                }
                if (scmPurReturnInfo.getItemId() > 0){
                	ScmMaterial2 scmMaterial = scmMaterialBiz.selectDirect(scmPurReturnInfo.getItemId(), param);
                	if(scmMaterial!=null) {
                		scmPurReturnInfo.setItemNo(scmMaterial.getItemNo());
                		scmPurReturnInfo.setItemName(scmMaterial.getItemName());
                	}
                }
                if(StringUtils.isNotBlank(scmPurReturnInfo.getReasonCode())) {
                	Code code =  codeBiz.selectByCategoryAndCode("scmReturnType", scmPurReturnInfo.getReasonCode());
                    if (code != null) {
                    	scmPurReturnInfo.setReasonCode(code.getName());
                    }
                }
            }
        }
        return list;
	}

	@Override
	public List<ScmPurSupplierAppraiseDetails> selectScmPurSupplierAppraiseDetails(
			HttpServletRequest request) throws Exception {
		String currentOrgUnitNo=request.getParameter("currentOrgUnitNo");
		String currentControlUnitNo=request.getParameter("currentControlUnitNo");
		String begDate = request.getParameter("begDate");
		String endDate = request.getParameter("endDate");
		String appraiseType = request.getParameter("appraiseType");
		String vendorName = request.getParameter("vendorName");
		String materialClassName = request.getParameter("materialClassName");
		String materialName = request.getParameter("materialName");
		Param param = new Param();
        param.setOrgUnitNo(currentOrgUnitNo);
        param.setControlUnitNo(currentControlUnitNo);
        
    	//物资类别
		StringBuffer sbMaterilaClass = new StringBuffer("");
		if (StringUtils.isNotBlank(materialClassName)) {
			String[] materialClassNameList = StringUtils.split(materialClassName, ",");
			for (String materialClass : materialClassNameList) {
				if (StringUtils.isBlank(materialClass))
					continue;
				int materialClassId = Integer.parseInt(materialClass);
				List<ScmMaterialGroup> scmMaterialGroupList = scmMaterialGroupBiz.findChild(materialClassId, param);
				if (scmMaterialGroupList != null && scmMaterialGroupList.size() > 0) {
					for (ScmMaterialGroup scmMaterialGroup : scmMaterialGroupList) {
						if (StringUtils.isNotBlank(sbMaterilaClass.toString()))
							sbMaterilaClass.append(",");
						sbMaterilaClass.append(String.valueOf(scmMaterialGroup.getId()));
					}
				}
			}
		}
        
        //供应商多选
        StringBuffer vendorIds=new StringBuffer("");
        if(StringUtils.isNotBlank(vendorName)) {
            String[] idList = StringUtils.split(vendorName, "|");
            for(String id:idList) {
                if(StringUtils.isBlank(id)) continue;
                if(StringUtils.isNotBlank(vendorIds.toString()))
                    vendorIds.append(",");
                vendorIds.append(id);
            }
        }
        
        //考核类型多选
        StringBuffer appraiseTypeIds=new StringBuffer("");
        if(StringUtils.isNotBlank(appraiseType)) {
            String[] idList = StringUtils.split(appraiseType, "|");
            for(String id:idList) {
                if(StringUtils.isBlank(id)) continue;
                if(StringUtils.isNotBlank(appraiseTypeIds.toString()))
                	appraiseTypeIds.append(",");
                appraiseTypeIds.append(id);
            }
        }
		
		HashMap<String, Object> map = new HashMap<>(); 
        map.put("begDate", begDate);
        map.put("endDate", endDate);
        map.put("appraiseTypeIds", appraiseTypeIds.toString());
        map.put("vendorIds", vendorIds.toString());
        map.put("materialIds", materialName);
        map.put("materialClassIds", sbMaterilaClass.toString());
        map.put("purOrgUnitNo", currentOrgUnitNo);
        
        List<ScmPurSupplierAppraiseDetails> list = ((ScmPurReportDAO) dao).selectScmPurSupplierAppraiseDetails(map);

        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
            	ScmPurSupplierAppraiseDetails scmPurSupplierAppraiseDetails = list.get(i);
                if (scmPurSupplierAppraiseDetails.getOrgUnitNo() != null) {
                    OrgBaseUnit orgBaseUnit = orgUnitBiz.selectbyOrgNo(scmPurSupplierAppraiseDetails.getOrgUnitNo(), param);
                    if (orgBaseUnit != null) {
                    	scmPurSupplierAppraiseDetails.setOrgUnitName(orgBaseUnit.getOrgUnitName());
                    }
                }
                
                scmPurSupplierAppraiseDetails.setDiffQty(scmPurSupplierAppraiseDetails.getOrderQty().subtract(scmPurSupplierAppraiseDetails.getReceiveQty())); 
            }
        }
        return list;
	}

	@Override
	public List<ScmPurDelivery> selectScmPurDelivery(HttpServletRequest request) throws Exception {
		String currentOrgUnitNo=request.getParameter("currentOrgUnitNo");
		String currentControlUnitNo=request.getParameter("currentControlUnitNo");
		String begDate=request.getParameter("begDate");
		String endDate=request.getParameter("endDate");
        String itemClassId = request.getParameter("itemClassId");
        String reqOrgUnitNo = request.getParameter("reqOrgUnitNo");
        String vendorClassId = request.getParameter("vendorClassId");
        String vendorId = request.getParameter("vendorId");
        ScmPurDeliveryAdvQuery scmPurDeliveryAdvQuery = new ScmPurDeliveryAdvQuery();
        if(StringUtils.isNotBlank(begDate))
        	scmPurDeliveryAdvQuery.setBegDate(FormatUtils.parseDate(begDate));
        if(StringUtils.isNotBlank(endDate))
        	scmPurDeliveryAdvQuery.setEndDate(FormatUtils.parseDate(endDate));
        if(StringUtils.isNotBlank(itemClassId))
        	scmPurDeliveryAdvQuery.setItemClassId(Long.valueOf(itemClassId));
        if(StringUtils.isNotBlank(reqOrgUnitNo))
        	scmPurDeliveryAdvQuery.setReqOrgUnitNo(reqOrgUnitNo);
        if(StringUtils.isNotBlank(vendorClassId))
        	scmPurDeliveryAdvQuery.setVendorClassId(Long.valueOf(vendorClassId));
        if(StringUtils.isNotBlank(vendorId))
        	scmPurDeliveryAdvQuery.setVendorId(Long.valueOf(vendorId));
        Param param = new Param();
        param.setOrgUnitNo(currentOrgUnitNo);
        param.setControlUnitNo(currentControlUnitNo);
        Page page = new Page();
        page.setModelClass(ScmPurDelivery.class);
        page.setShowCount(Integer.MAX_VALUE);
        page.setModel(scmPurDeliveryAdvQuery);
        return scmPurDeliveryBiz.findPage(page, param);
	}

}

