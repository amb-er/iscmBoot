
package com.armitage.server.iscm.purchasemanage.pricemanage.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.util.ClassUtils;
import com.armitage.server.ifbc.costcard.model.ScmProductCostCardDetailHistory;
import com.armitage.server.ifbc.costcard.service.ScmProductCostCardDetailHistoryBiz;
import com.armitage.server.iscm.basedata.model.PubSysBasicInfo;
import com.armitage.server.iscm.basedata.model.ScmMaterial2;
import com.armitage.server.iscm.basedata.model.ScmMaterialCompanyInfo;
import com.armitage.server.iscm.basedata.model.ScmMaterialPurchase2;
import com.armitage.server.iscm.basedata.model.ScmMaterialUnitRelation2;
import com.armitage.server.iscm.basedata.model.Scmsupplier2;
import com.armitage.server.iscm.basedata.model.Scmsupplierpurchaseinfo2;
import com.armitage.server.iscm.basedata.service.PubSysBasicInfoBiz;
import com.armitage.server.iscm.basedata.service.ScmMaterialCompanyInfoBiz;
import com.armitage.server.iscm.basedata.service.ScmMaterialPurchaseBiz;
import com.armitage.server.iscm.basedata.service.ScmMaterialUnitRelationBiz;
import com.armitage.server.iscm.basedata.service.ScmsupplierBiz;
import com.armitage.server.iscm.basedata.service.ScmsupplierpurchaseinfoBiz;
import com.armitage.server.iscm.basedata.util.ScmMaterialUtil;
import com.armitage.server.iscm.inventorymanage.inventorydata.model.ScmInvStock2;
import com.armitage.server.iscm.inventorymanage.inventorydata.service.ScmInvStockBiz;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvPurInWarehsBillEntry2;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.service.ScmInvPurInWarehsBillBiz;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.service.ScmInvSalePriceBiz;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmInvPriceQuery;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmMaterialPrePrice;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmMaterialPrice;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurMarketPrice2;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurPrice2;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurPriceAll;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurPriceAllQuery;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurPriceQuery;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurQuotation2;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurSupplyInfoQuery;
import com.armitage.server.iscm.purchasemanage.pricemanage.service.ScmMaterialPriceBiz;
import com.armitage.server.iscm.purchasemanage.pricemanage.service.ScmPurMarketPriceBiz;
import com.armitage.server.iscm.purchasemanage.pricemanage.service.ScmPurPriceBiz;
import com.armitage.server.iscm.purchasemanage.pricemanage.service.ScmPurQuotationBiz;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurSupplyInfo2;
import com.armitage.server.iscm.purchasemanage.purchasesetting.service.ScmPurSupplyInfoBiz;
import com.armitage.server.system.model.OrgCompany2;
import com.armitage.server.system.service.OrgCompanyBiz;
import com.armitage.server.system.service.OrgUnitRelationBiz;
import com.armitage.server.system.service.SysParamBiz;
import com.armitage.server.system.util.OrgUnitRelationType;
import org.springframework.stereotype.Service;

@Service("scmMaterialPriceBiz")
public class ScmMaterialPriceBizImpl extends BaseBizImpl<ScmMaterialPrice> implements ScmMaterialPriceBiz {
	private ScmPurPriceBiz scmPurPriceBiz;
	private ScmPurQuotationBiz scmPurQuotationBiz;
	private ScmPurMarketPriceBiz scmPurMarketPriceBiz;
	private PubSysBasicInfoBiz pubSysBasicInfoBiz;
	private ScmInvSalePriceBiz scmInvSalePriceBiz;
	private ScmPurSupplyInfoBiz scmPurSupplyInfoBiz;
	private ScmsupplierpurchaseinfoBiz scmsupplierpurchaseinfoBiz;
	private ScmMaterialPurchaseBiz scmMaterialPurchaseBiz;
	private ScmsupplierBiz scmsupplierBiz;
	private SysParamBiz sysParamBiz;
	private ScmInvStockBiz scmInvStockBiz;
	private ScmInvPurInWarehsBillBiz scmInvPurInWarehsBillBiz;
	private OrgUnitRelationBiz orgUnitRelationBiz;
	private OrgCompanyBiz orgCompanyBiz;
	private ScmMaterialUnitRelationBiz scmMaterialUnitRelationBiz;
	private ScmMaterialCompanyInfoBiz scmMaterialCompanyInfoBiz;
	private ScmProductCostCardDetailHistoryBiz scmProductCostCardDetailHistoryBiz;
	
	public void setScmPurPriceBiz(ScmPurPriceBiz scmPurPriceBiz) {
		this.scmPurPriceBiz = scmPurPriceBiz;
	}

	public void setScmPurQuotationBiz(ScmPurQuotationBiz scmPurQuotationBiz) {
		this.scmPurQuotationBiz = scmPurQuotationBiz;
	}

	public void setScmPurMarketPriceBiz(ScmPurMarketPriceBiz scmPurMarketPriceBiz) {
		this.scmPurMarketPriceBiz = scmPurMarketPriceBiz;
	}

	public void setPubSysBasicInfoBiz(PubSysBasicInfoBiz pubSysBasicInfoBiz) {
		this.pubSysBasicInfoBiz = pubSysBasicInfoBiz;
	}

	public void setScmInvSalePriceBiz(ScmInvSalePriceBiz scmInvSalePriceBiz) {
		this.scmInvSalePriceBiz = scmInvSalePriceBiz;
	}

	public void setScmPurSupplyInfoBiz(ScmPurSupplyInfoBiz scmPurSupplyInfoBiz) {
		this.scmPurSupplyInfoBiz = scmPurSupplyInfoBiz;
	}

	public void setScmsupplierpurchaseinfoBiz(ScmsupplierpurchaseinfoBiz scmsupplierpurchaseinfoBiz) {
		this.scmsupplierpurchaseinfoBiz = scmsupplierpurchaseinfoBiz;
	}

	public void setScmMaterialPurchaseBiz(ScmMaterialPurchaseBiz scmMaterialPurchaseBiz) {
		this.scmMaterialPurchaseBiz = scmMaterialPurchaseBiz;
	}

	public void setScmsupplierBiz(ScmsupplierBiz scmsupplierBiz) {
		this.scmsupplierBiz = scmsupplierBiz;
	}

	public void setSysParamBiz(SysParamBiz sysParamBiz) {
		this.sysParamBiz = sysParamBiz;
	}

	public void setScmInvStockBiz(ScmInvStockBiz scmInvStockBiz) {
		this.scmInvStockBiz = scmInvStockBiz;
	}

	public void setScmInvPurInWarehsBillBiz(ScmInvPurInWarehsBillBiz scmInvPurInWarehsBillBiz) {
		this.scmInvPurInWarehsBillBiz = scmInvPurInWarehsBillBiz;
	}

	public void setOrgUnitRelationBiz(OrgUnitRelationBiz orgUnitRelationBiz) {
		this.orgUnitRelationBiz = orgUnitRelationBiz;
	}

	public void setOrgCompanyBiz(OrgCompanyBiz orgCompanyBiz) {
		this.orgCompanyBiz = orgCompanyBiz;
	}

	public void setScmMaterialUnitRelationBiz(ScmMaterialUnitRelationBiz scmMaterialUnitRelationBiz) {
		this.scmMaterialUnitRelationBiz = scmMaterialUnitRelationBiz;
	}

	public void setScmMaterialCompanyInfoBiz(ScmMaterialCompanyInfoBiz scmMaterialCompanyInfoBiz) {
		this.scmMaterialCompanyInfoBiz = scmMaterialCompanyInfoBiz;
	}

	public void setScmProductCostCardDetailHistoryBiz(
			ScmProductCostCardDetailHistoryBiz scmProductCostCardDetailHistoryBiz) {
		this.scmProductCostCardDetailHistoryBiz = scmProductCostCardDetailHistoryBiz;
	}

	@Override
	public ScmPurPriceAll getMaterialPriceAll(ScmPurPriceAllQuery scmPurPriceAllQuery, Param param) throws AppException {
		ScmPurPriceAll scmPurPriceAll = new ScmPurPriceAll(true);
		scmPurPriceAll.setItemId(scmPurPriceAllQuery.getItemId());
		//获取市调价
		StringBuffer enquiryGroupIds = new StringBuffer("");
		if(scmPurPriceAllQuery.getGroup1()>0){
			if(StringUtils.isNotBlank(enquiryGroupIds.toString()))
				enquiryGroupIds.append(",");
			enquiryGroupIds.append(scmPurPriceAllQuery.getGroup1());
		}
		if(scmPurPriceAllQuery.getGroup2()>0){
			if(StringUtils.isNotBlank(enquiryGroupIds.toString()))
				enquiryGroupIds.append(",");
			enquiryGroupIds.append(scmPurPriceAllQuery.getGroup2());
		}
		if(scmPurPriceAllQuery.getGroup3()>0){
			if(StringUtils.isNotBlank(enquiryGroupIds.toString()))
				enquiryGroupIds.append(",");
			enquiryGroupIds.append(scmPurPriceAllQuery.getGroup3());
		}
		if(StringUtils.isNotBlank(enquiryGroupIds.toString())){
			List<ScmPurMarketPrice2> scmPurMarketPriceList = scmPurMarketPriceBiz
					.selectRecentPrice(scmPurPriceAllQuery.getItemId(),
							scmPurPriceAllQuery.getBegDate(),
							scmPurPriceAllQuery.getEndDate(),
							enquiryGroupIds.toString(), param);
			if(scmPurMarketPriceList!=null && !scmPurMarketPriceList.isEmpty()){
				for(ScmPurMarketPrice2 scmPurMarketPrice:scmPurMarketPriceList){
					if(scmPurMarketPrice.getEnquiryGroupId()==scmPurPriceAllQuery.getGroup1())
						scmPurPriceAll.setGroupPrice1(scmPurMarketPrice.getPrice());
					if(scmPurMarketPrice.getEnquiryGroupId()==scmPurPriceAllQuery.getGroup2())
						scmPurPriceAll.setGroupPrice2(scmPurMarketPrice.getPrice());
					if(scmPurMarketPrice.getEnquiryGroupId()==scmPurPriceAllQuery.getGroup3())
						scmPurPriceAll.setGroupPrice3(scmPurMarketPrice.getPrice());
				}
			}
		}
		//获取报价
		StringBuffer vendorIds = new StringBuffer("");
		if(scmPurPriceAllQuery.getVendor1()>0){
			if(StringUtils.isNotBlank(vendorIds.toString()))
				vendorIds.append(",");
			vendorIds.append(scmPurPriceAllQuery.getVendor1());
		}
		if(scmPurPriceAllQuery.getVendor2()>0){
			if(StringUtils.isNotBlank(vendorIds.toString()))
				vendorIds.append(",");
			vendorIds.append(scmPurPriceAllQuery.getVendor2());
		}
		if(scmPurPriceAllQuery.getVendor3()>0){
			if(StringUtils.isNotBlank(vendorIds.toString()))
				vendorIds.append(",");
			vendorIds.append(scmPurPriceAllQuery.getVendor3());
		}
		if (StringUtils.isNotBlank(vendorIds.toString())) {
			List<ScmPurQuotation2> scmPurQuotationList = scmPurQuotationBiz.selectRecentPrice(
					scmPurPriceAllQuery.getItemId(),
					scmPurPriceAllQuery.getBegDate(),
					scmPurPriceAllQuery.getEndDate(), vendorIds.toString(),
					param);
			boolean vendor1 = false;
			boolean vendor2 = false;
			boolean vendor3 = false;
			if(scmPurQuotationList!=null && !scmPurQuotationList.isEmpty()){
				for(ScmPurQuotation2 scmPurQuotation:scmPurQuotationList){
					if(scmPurQuotation.getVendorId()==scmPurPriceAllQuery.getVendor1()){
						scmPurPriceAll.setVendorPrice1(scmPurQuotation.getPrice());
						scmPurPriceAll.setVendorTaxRate1(scmPurQuotation.getTaxRate());
						vendor1 = true;
					}
					if(scmPurQuotation.getVendorId()==scmPurPriceAllQuery.getVendor2()){
						scmPurPriceAll.setVendorPrice2(scmPurQuotation.getPrice());
						scmPurPriceAll.setVendorTaxRate2(scmPurQuotation.getTaxRate());
						vendor2 = true;
					}
					if(scmPurQuotation.getVendorId()==scmPurPriceAllQuery.getVendor3()){
						scmPurPriceAll.setVendorPrice3(scmPurQuotation.getPrice());
						scmPurPriceAll.setVendorTaxRate3(scmPurQuotation.getTaxRate());
						vendor3 = true;
					}
				}
			}
			if(!vendor1 && scmPurPriceAllQuery.getVendor1()>0){
				scmPurPriceAll.setVendorTaxRate1(getTaxRate(param.getOrgUnitNo(), scmPurPriceAllQuery.getVendor1(), scmPurPriceAllQuery.getItemId(), param));
			}
			if(!vendor2 && scmPurPriceAllQuery.getVendor2()>0){
				scmPurPriceAll.setVendorTaxRate2(getTaxRate(param.getOrgUnitNo(), scmPurPriceAllQuery.getVendor2(), scmPurPriceAllQuery.getItemId(), param));
			}
			if(!vendor3 && scmPurPriceAllQuery.getVendor3()>0){
				scmPurPriceAll.setVendorTaxRate3(getTaxRate(param.getOrgUnitNo(), scmPurPriceAllQuery.getVendor3(), scmPurPriceAllQuery.getItemId(), param));
			}
		}
		//获取上次定价
		ScmPurPrice2 scmPurPrice = scmPurPriceBiz.getPrePrice(
				param.getOrgUnitNo(), scmPurPriceAllQuery.getItemId(),
				scmPurPriceAllQuery.getBegDate(), param);
		if(scmPurPrice!=null)
			scmPurPriceAll.setPrePrice(scmPurPrice.getPrice());
		//获取去年同期
		ScmPurPrice2 scmPurPriceLastYear = scmPurPriceBiz.getLastYearPrice(
				param.getOrgUnitNo(), scmPurPriceAllQuery.getItemId(),
				scmPurPriceAllQuery.getBegDate(),
				scmPurPriceAllQuery.getEndDate(), param);
		if(scmPurPriceLastYear!=null)
			scmPurPriceAll.setLastYearPrice(scmPurPriceLastYear.getPrice());
		return scmPurPriceAll;
	}

	@Override
	public List<ScmMaterialPrice> getMaterialPrice(ScmPurPriceQuery scmPurPriceQuery,Param param) throws AppException {
		/************************************************************************
		 * 6:临时定价
		 * 5:定供应商、定价格、定税率
		 * 4:不定供应商、定价格、定税率
		 * 3:定供应商、定价格、不定税率
		 * 2:不定供应商、定价格、不定税率
		 * 1:报价
		 * 0:手工价
		 ************************************************************************/
//		
		String priceMode = sysParamBiz.getValue(scmPurPriceQuery.getPurOrgUnitNo(), "SCM_PriceMode", "1", param);
		String getPriceWay = sysParamBiz.getValue(scmPurPriceQuery.getPurOrgUnitNo(), "SCM_GetPriceWay", "N", param);
		int pricePrec = Integer.valueOf(sysParamBiz.getValue(scmPurPriceQuery.getPurOrgUnitNo(), "SCM_PricePrecision", "2", param));
		String itemIds = scmPurPriceQuery.getItemIds();
		//先获取备选定价
		List<ScmMaterialPrice> scmMaterialPriceList = scmPurPriceBiz.getPreParePriceByVendorId(scmPurPriceQuery.getPurOrgUnitNo(),scmPurPriceQuery.getVendorId(),
				itemIds, scmPurPriceQuery.getBizDate(), scmPurPriceQuery.getFinOrgUnitNo(),
				scmPurPriceQuery.getPmId(), param);
		itemIds = ","+itemIds+",";
		if(scmMaterialPriceList!=null && !scmMaterialPriceList.isEmpty()){
			for(ScmMaterialPrice scmMaterialPrice:scmMaterialPriceList){
				if(scmMaterialPrice.getVendorId()>0){
					if(scmMaterialPrice.getTaxRate().compareTo(BigDecimal.ZERO)>0){
						scmMaterialPrice.setRefPriceStatus("5");	//定价定税且绑定供应商
					}else{
						scmMaterialPrice.setTaxRate(getTaxRate(scmPurPriceQuery.getPurOrgUnitNo(), scmPurPriceQuery.getVendorId(), scmMaterialPrice.getItemId(), param));
						scmMaterialPrice.setRefPriceStatus("3");	//定价不定税绑定供应商
					}
				}
				itemIds = StringUtils.replace(itemIds, ","+scmMaterialPrice.getItemId()+",", ",");
			}
		}
		itemIds = StringUtils.left(itemIds, StringUtils.length(itemIds)-1);
		if(StringUtils.isNotBlank(itemIds))
			itemIds = StringUtils.right(itemIds, StringUtils.length(itemIds)-1);
		if(StringUtils.isNotBlank(itemIds)){
			List<ScmMaterialPrice> scmMaterialPriceList3=new ArrayList<>();
			if(scmMaterialPriceList==null)
				scmMaterialPriceList = new ArrayList<ScmMaterialPrice>();
			//再获取定价
			if (StringUtils.equals("Y", getPriceWay)) {
				scmMaterialPriceList3 = scmPurPriceBiz.getPrice(scmPurPriceQuery.getPurOrgUnitNo(),
						itemIds, scmPurPriceQuery.getBizDate(), scmPurPriceQuery.getFinOrgUnitNo(),
						scmPurPriceQuery.getPmId(),scmPurPriceQuery.getVendorId(), param);
			}else {
				//先获取定价
				scmMaterialPriceList3 = scmPurPriceBiz.getPrice(scmPurPriceQuery.getPurOrgUnitNo(),
						itemIds, scmPurPriceQuery.getBizDate(), scmPurPriceQuery.getFinOrgUnitNo(),
						scmPurPriceQuery.getPmId(), param);
			}
			itemIds = ","+itemIds+",";
			if(scmMaterialPriceList3!=null && !scmMaterialPriceList3.isEmpty()){
				for(ScmMaterialPrice scmMaterialPrice:scmMaterialPriceList3){
					if (StringUtils.equals("2", scmMaterialPrice.getBizType())) {
						if(scmMaterialPrice.getTaxRate().compareTo(BigDecimal.ZERO)>0){
							scmMaterialPrice.setRefPriceStatus("7");	//临时定价定税,临时定价必绑定供应商
						}else{
							scmMaterialPrice.setTaxRate(getTaxRate(scmPurPriceQuery.getPurOrgUnitNo(), scmPurPriceQuery.getVendorId(), scmMaterialPrice.getItemId(), param));
							scmMaterialPrice.setRefPriceStatus("6");	//临时定价不定税,临时定价必绑定供应商
						}
					} else {
						if(scmMaterialPrice.getVendorId()>0){
							if(scmMaterialPrice.getTaxRate().compareTo(BigDecimal.ZERO)>0){
								scmMaterialPrice.setRefPriceStatus("5");	//定价定税且绑定供应商
							}else{
								scmMaterialPrice.setTaxRate(getTaxRate(scmPurPriceQuery.getPurOrgUnitNo(), scmPurPriceQuery.getVendorId(), scmMaterialPrice.getItemId(), param));
								scmMaterialPrice.setRefPriceStatus("3");	//定价不定税绑定供应商
							}
						}else {
							if(scmMaterialPrice.getTaxRate().compareTo(BigDecimal.ZERO)>0){
								scmMaterialPrice.setRefPriceStatus("4");	//定价定税不绑定供应商
							}else{
								scmMaterialPrice.setTaxRate(getTaxRate(scmPurPriceQuery.getPurOrgUnitNo(), scmPurPriceQuery.getVendorId(), scmMaterialPrice.getItemId(), param));
								scmMaterialPrice.setPrice(scmMaterialPrice.getTaxPrice().divide((BigDecimal.ONE.add(scmMaterialPrice.getTaxRate())), pricePrec, RoundingMode.HALF_UP));
								scmMaterialPrice.setRefPriceStatus("2");	//定价不定税不绑定供应商
							}
						}
					}
					
					itemIds = StringUtils.replace(itemIds, ","+scmMaterialPrice.getItemId()+",", ",");
				}
				scmMaterialPriceList.addAll(scmMaterialPriceList3);
			}
			
		}
		itemIds = StringUtils.left(itemIds, StringUtils.length(itemIds)-1);
		if(StringUtils.isNotBlank(itemIds))
			itemIds = StringUtils.right(itemIds, StringUtils.length(itemIds)-1);
		if(StringUtils.isNotBlank(itemIds)){
			if(scmMaterialPriceList==null)
				scmMaterialPriceList = new ArrayList<ScmMaterialPrice>();
			//定价不存在同获取报价
			List<ScmMaterialPrice> scmMaterialPriceList2 = scmPurQuotationBiz.getPrice(scmPurPriceQuery.getPurOrgUnitNo(),scmPurPriceQuery.getVendorId(),
					itemIds, scmPurPriceQuery.getBizDate(),param);
			itemIds = ","+itemIds+",";
			if(scmMaterialPriceList2!=null && !scmMaterialPriceList2.isEmpty()){
				for(ScmMaterialPrice scmMaterialPrice:scmMaterialPriceList2){
					if(scmMaterialPrice.getTaxRate().compareTo(BigDecimal.ZERO)==0){
						scmMaterialPrice.setTaxRate(getTaxRate(scmPurPriceQuery.getPurOrgUnitNo(), scmPurPriceQuery.getVendorId(), scmMaterialPrice.getItemId(), param));
						if(StringUtils.equals("1", priceMode)) {
							scmMaterialPrice.setPrice(scmMaterialPrice.getTaxPrice().divide((BigDecimal.ONE.add(scmMaterialPrice.getTaxRate())), pricePrec, RoundingMode.HALF_UP));
						}else {
							scmMaterialPrice.setTaxPrice(scmMaterialPrice.getPrice().multiply((BigDecimal.ONE.add(scmMaterialPrice.getTaxRate()))).setScale(pricePrec, RoundingMode.HALF_UP));
						}
					}
					scmMaterialPrice.setRefPriceStatus("1");	//报价
					itemIds = StringUtils.replace(itemIds, ","+scmMaterialPrice.getItemId()+",", ",");
				}
				scmMaterialPriceList.addAll(scmMaterialPriceList2);
			}
			
		}
		itemIds = StringUtils.left(itemIds, StringUtils.length(itemIds)-1);
		if(StringUtils.isNotBlank(itemIds))
			itemIds = StringUtils.right(itemIds, StringUtils.length(itemIds)-1);
		if(StringUtils.isNotBlank(itemIds)) {
			if(scmMaterialPriceList==null)
				scmMaterialPriceList = new ArrayList<ScmMaterialPrice>();
			String[] itemIdList = StringUtils.split(itemIds,",");
			for(String itemId:itemIdList) {
				ScmMaterialPrice scmMaterialPrice = new ScmMaterialPrice();
				scmMaterialPrice.setItemId(Long.valueOf(itemId));
				scmMaterialPrice.setPrice(BigDecimal.ZERO);
				scmMaterialPrice.setTaxRate(getTaxRate(scmPurPriceQuery.getPurOrgUnitNo(), scmPurPriceQuery.getVendorId(), scmMaterialPrice.getItemId(), param));
				scmMaterialPrice.setTaxPrice(BigDecimal.ZERO);
				scmMaterialPrice.setRefPriceStatus("0");
				scmMaterialPriceList.add(scmMaterialPrice);
			}
		}
		if(scmMaterialPriceList!=null && !scmMaterialPriceList.isEmpty()){
			HashMap<String,Object> myCacheDataMap = new HashMap<String,Object>();
			for(ScmMaterialPrice scmMaterialPrice:scmMaterialPriceList){
				PubSysBasicInfo pubSysBasicInfo = pubSysBasicInfoBiz.selectByTaxRate("TaxRate", scmMaterialPrice.getTaxRate().toString(), null, param);
				if (pubSysBasicInfo != null) {
					scmMaterialPrice.setTaxRateStr(pubSysBasicInfo.getFInfoNo());
					scmMaterialPrice.setConvertMap("taxRateStr", pubSysBasicInfo);
				}
				if(scmMaterialPrice.getVendorId()>0) {
					Scmsupplier2 scmsupplier = scmsupplierBiz.selectDirect(scmMaterialPrice.getVendorId(), param);
					if(scmsupplier!=null) {
						scmMaterialPrice.setConvertMap("vendorId", scmsupplier);
					}
				}
				if(scmMaterialPrice.getPriceBillId() > 0 && StringUtils.isNotBlank(scmMaterialPrice.getRefPriceStatus())){
		        	//价格来源
		        	if("1".equals(scmMaterialPrice.getRefPriceStatus())){
		        		//报价
		        		ScmPurQuotation2 scmPurQuotation = (ScmPurQuotation2) myCacheDataMap.get(ClassUtils.getFinalModelSimpleName(ScmPurQuotation2.class)+"_"+scmMaterialPrice.getPriceBillId());
		        		if(scmPurQuotation==null) {
		        			scmPurQuotation = scmPurQuotationBiz.selectDirect(scmMaterialPrice.getPriceBillId(), param);
		        			myCacheDataMap.put(ClassUtils.getFinalModelSimpleName(ScmPurQuotation2.class)+"_"+scmMaterialPrice.getPriceBillId(),scmPurQuotation);
		        		}
			            if (scmPurQuotation != null) {
			            	scmMaterialPrice.setPriceBillNo(scmPurQuotation.getPqNo());
			            	scmMaterialPrice.setPriceBillStatus(scmPurQuotation.getStatus());
			            }
		        	}else{
		        		//定价（包括临时定价）
		        		ScmPurPrice2 scmPurPrice = (ScmPurPrice2) myCacheDataMap.get(ClassUtils.getFinalModelSimpleName(ScmPurPrice2.class)+"_"+scmMaterialPrice.getPriceBillId());
		        		if(scmPurPrice==null) {
		        			scmPurPrice = scmPurPriceBiz.selectDirect(scmMaterialPrice.getPriceBillId(), param);
		        			myCacheDataMap.put(ClassUtils.getFinalModelSimpleName(ScmPurPrice2.class)+"_"+scmMaterialPrice.getPriceBillId(),scmPurPrice);
		        		}
			            if (scmPurPrice != null) {
			            	scmMaterialPrice.setPriceBillNo(scmPurPrice.getPmNo());
			            	scmMaterialPrice.setPriceBillStatus(scmPurPrice.getStatus());
			            }
		        	}
		        }
			}
		}
		return scmMaterialPriceList;
	}

	private BigDecimal getTaxRate(String purOrgUnitNo,Long vendorId,long itemId,Param param) {
		BigDecimal taxRate = BigDecimal.ZERO;
		Scmsupplierpurchaseinfo2 scmsupplierpurchaseinfo = scmsupplierpurchaseinfoBiz.selectByVendorIdAndOrgUnitNo(vendorId, purOrgUnitNo, param);
		if(scmsupplierpurchaseinfo == null){
			scmsupplierpurchaseinfo = scmsupplierpurchaseinfoBiz.selectByVendorIdAndOrgUnitNo(vendorId, param.getControlUnitNo(), param);
		}
		if(scmsupplierpurchaseinfo!=null && scmsupplierpurchaseinfo.getVatRate().compareTo(BigDecimal.ZERO)>0) {
			taxRate = scmsupplierpurchaseinfo.getVatRate();
		}else {
			ScmMaterialPurchase2 scmMaterialPurchase = scmMaterialPurchaseBiz.selectByItemIdAndOrgUnitNo(itemId, purOrgUnitNo, param);
			if(scmMaterialPurchase == null){
				scmMaterialPurchase = scmMaterialPurchaseBiz.selectByItemIdAndOrgUnitNo(itemId, param.getControlUnitNo(), param);
			}
			if(scmMaterialPurchase!=null && scmMaterialPurchase.getDefaultRate().compareTo(BigDecimal.ZERO)>0) {
				taxRate = scmMaterialPurchase.getDefaultRate();
			}
		}
		return taxRate;
	}
	@Override
	public List<ScmMaterialPrice> getLastQuotationPrice(ScmPurPriceQuery scmPurPriceQuery,Param param) throws AppException {
		return scmPurQuotationBiz.getLastPrice(scmPurPriceQuery.getItemIds(),scmPurPriceQuery.getVendorId(),scmPurPriceQuery.getBizDate(),param);
	}

	@Override
	public List<ScmMaterialPrice> getMaterialSalePrice(ScmInvPriceQuery scmInvPriceQuery, Param param) throws AppException {
		return scmInvSalePriceBiz.getPrice(scmInvPriceQuery.getInvOrgUnitNo(),
				scmInvPriceQuery.getItemIds(), scmInvPriceQuery.getBizDate(),param);
	}

	@Override
	public ScmMaterialPrice getMaterialSupplyInfo(ScmPurSupplyInfoQuery scmPurSupplyInfoQuery, Param param) throws AppException {
		ScmPurSupplyInfo2 scmPurSupplyInfo = scmPurSupplyInfoBiz.getSupplyInfoByItem(scmPurSupplyInfoQuery.getPurOrgUnitNo(),scmPurSupplyInfoQuery.getInvOrgUnitNo(),scmPurSupplyInfoQuery.getItemId(),scmPurSupplyInfoQuery.getBizDate(),param);
		if(scmPurSupplyInfo!=null){
			ScmMaterialPrice scmMaterialPrice = new ScmMaterialPrice();
			scmMaterialPrice.setItemId(scmPurSupplyInfoQuery.getItemId());
			scmMaterialPrice.setVendorId(scmPurSupplyInfo.getVendorId());
			scmMaterialPrice.setDirectPurchase(scmPurSupplyInfo.isDirectPurchase());
			if(scmPurSupplyInfo.getVendorId()>0) {
				Scmsupplier2 scmsupplier = scmsupplierBiz.selectDirect(scmPurSupplyInfo.getVendorId(), param);
				if(scmsupplier!=null && StringUtils.equals("A",scmsupplier.getStatus())) {
					scmMaterialPrice.setConvertMap("vendorId", scmsupplier);
				} else {
					scmMaterialPrice.setVendorId(0);
				}
			}
				
			return scmMaterialPrice;
		}
			
		return null;
	}

	@Override
	public List<ScmMaterialPrice> getMaterialSupplyInfos(ScmPurSupplyInfoQuery scmPurSupplyInfoQuery, Param param)
			throws AppException {
		List<ScmPurSupplyInfo2> scmPurSupplyInfoList = scmPurSupplyInfoBiz.getSupplyInfoByItems(
				scmPurSupplyInfoQuery.getPurOrgUnitNo(), scmPurSupplyInfoQuery.getInvOrgUnitNo(),
				scmPurSupplyInfoQuery.getItemIds(), scmPurSupplyInfoQuery.getBizDate(), param);
		if(scmPurSupplyInfoList != null && !scmPurSupplyInfoList.isEmpty()){
			List<ScmMaterialPrice> scmMaterialPriceList = new ArrayList<>();
			for(ScmPurSupplyInfo2 scmPurSupplyInfo : scmPurSupplyInfoList){
				ScmMaterialPrice scmMaterialPrice = new ScmMaterialPrice();
				scmMaterialPrice.setItemId(scmPurSupplyInfo.getItemId());
				scmMaterialPrice.setVendorId(scmPurSupplyInfo.getVendorId());
				if(scmPurSupplyInfo.getVendorId()>0) {
					Scmsupplier2 scmsupplier = scmsupplierBiz.selectDirect(scmPurSupplyInfo.getVendorId(), param);
					if(scmsupplier!=null && StringUtils.equals("A",scmsupplier.getStatus())) {
						scmMaterialPrice.setConvertMap("vendorId", scmsupplier);
					} else {
						scmMaterialPrice.setVendorId(0);
					}
				}
				scmMaterialPriceList.add(scmMaterialPrice);
			}
			return scmMaterialPriceList;
		}
		return null;
	}

	@Override
	public List<ScmPurPriceAll> getMaterialPriceAllList(ScmPurPriceAllQuery scmPurPriceAllQuery, Param param)
			throws AppException {
		List<String> itemIds = new ArrayList<>();
		List<ScmPurPriceAll> scmPurPriceAllList = new ArrayList<>();
		List<ScmPurPriceAll> scmPurQuoPriceAllList = new ArrayList<>();
		List<ScmPurPriceAll> scmPurEnquiryPriceAllList = new ArrayList<>();
		// 获取报价
		StringBuffer vendorIds = new StringBuffer("");
		if (scmPurPriceAllQuery.getVendor1() > 0) {
			if (StringUtils.isNotBlank(vendorIds.toString()))
				vendorIds.append(",");
			vendorIds.append(scmPurPriceAllQuery.getVendor1());
		}
		if (scmPurPriceAllQuery.getVendor2() > 0) {
			if (StringUtils.isNotBlank(vendorIds.toString()))
				vendorIds.append(",");
			vendorIds.append(scmPurPriceAllQuery.getVendor2());
		}
		if (scmPurPriceAllQuery.getVendor3() > 0) {
			if (StringUtils.isNotBlank(vendorIds.toString()))
				vendorIds.append(",");
			vendorIds.append(scmPurPriceAllQuery.getVendor3());
		}
		// 市调价
		StringBuffer enquiryGroupIds = new StringBuffer("");
		if (scmPurPriceAllQuery.getGroup1() > 0) {
			if (StringUtils.isNotBlank(enquiryGroupIds.toString()))
				enquiryGroupIds.append(",");
			enquiryGroupIds.append(scmPurPriceAllQuery.getGroup1());
		}
		if (scmPurPriceAllQuery.getGroup2() > 0) {
			if (StringUtils.isNotBlank(enquiryGroupIds.toString()))
				enquiryGroupIds.append(",");
			enquiryGroupIds.append(scmPurPriceAllQuery.getGroup2());
		}
		if (scmPurPriceAllQuery.getGroup3() > 0) {
			if (StringUtils.isNotBlank(enquiryGroupIds.toString()))
				enquiryGroupIds.append(",");
			enquiryGroupIds.append(scmPurPriceAllQuery.getGroup3());
		}
		if (StringUtils.isNotBlank(enquiryGroupIds.toString())) {
			List<ScmPurMarketPrice2> scmPurMarketPriceList = scmPurMarketPriceBiz.selectItemsRecentPrice(
					scmPurPriceAllQuery.getItemIds(), scmPurPriceAllQuery.getBegDate(),
					scmPurPriceAllQuery.getEndDate(), enquiryGroupIds.toString());
			if (scmPurMarketPriceList != null && !scmPurMarketPriceList.isEmpty()) {
				for (ScmPurMarketPrice2 tempScmPurMarketPrice2 : scmPurMarketPriceList) {
					ScmPurPriceAll scmPurPriceAll = new ScmPurPriceAll(true);
					if (!itemIds.contains(String.valueOf(tempScmPurMarketPrice2.getItemId()))) {
						for (ScmPurMarketPrice2 scmPurMarketPrice : scmPurMarketPriceList) {
							if (scmPurMarketPrice.getItemId() == tempScmPurMarketPrice2.getItemId()) {
								if (scmPurMarketPrice.getEnquiryGroupId() == scmPurPriceAllQuery.getGroup1())
									scmPurPriceAll.setGroupPrice1(scmPurMarketPrice.getPrice());
								if (scmPurMarketPrice.getEnquiryGroupId() == scmPurPriceAllQuery.getGroup2())
									scmPurPriceAll.setGroupPrice2(scmPurMarketPrice.getPrice());
								if (scmPurMarketPrice.getEnquiryGroupId() == scmPurPriceAllQuery.getGroup3())
									scmPurPriceAll.setGroupPrice3(scmPurMarketPrice.getPrice());
							}
						}
						itemIds.add(String.valueOf(tempScmPurMarketPrice2.getItemId()));
						scmPurPriceAll.setItemId(tempScmPurMarketPrice2.getItemId());
						scmPurEnquiryPriceAllList.add(scmPurPriceAll);
					}
				}
			}
		}
		itemIds.clear();
		if (StringUtils.isNotBlank(vendorIds.toString())) {
			List<ScmPurQuotation2> scmPurQuotationList = scmPurQuotationBiz.selectItemsRecentPrice(
					scmPurPriceAllQuery.getItemIds(), scmPurPriceAllQuery.getBegDate(),
					scmPurPriceAllQuery.getEndDate(), vendorIds.toString(), param);
			if (scmPurQuotationList != null && !scmPurQuotationList.isEmpty()) {
				for (ScmPurQuotation2 tempScmPurQuotation : scmPurQuotationList) {
					if (!itemIds.contains(String.valueOf(tempScmPurQuotation.getItemId()))) {
						ScmPurPriceAll scmPurPriceAll = new ScmPurPriceAll(true);
						for (ScmPurQuotation2 scmPurQuotation : scmPurQuotationList) {
							if (tempScmPurQuotation.getItemId() == scmPurQuotation.getItemId()) {
								if (scmPurQuotation.getVendorId() == scmPurPriceAllQuery.getVendor1()) {
									scmPurPriceAll.setVendorPrice1(scmPurQuotation.getPrice());
									scmPurPriceAll.setVendorTaxRate1(scmPurQuotation.getTaxRate());
								}
								if (scmPurQuotation.getVendorId() == scmPurPriceAllQuery.getVendor2()) {
									scmPurPriceAll.setVendorPrice2(scmPurQuotation.getPrice());
									scmPurPriceAll.setVendorTaxRate2(scmPurQuotation.getTaxRate());
								}
								if (scmPurQuotation.getVendorId() == scmPurPriceAllQuery.getVendor3()) {
									scmPurPriceAll.setVendorPrice3(scmPurQuotation.getPrice());
									scmPurPriceAll.setVendorTaxRate3(scmPurQuotation.getTaxRate());
								}
							}
						}
						scmPurPriceAll.setItemId(tempScmPurQuotation.getItemId());
						itemIds.add(String.valueOf(tempScmPurQuotation.getItemId()));
						scmPurQuoPriceAllList.add(scmPurPriceAll);
					}
				}
			}
		}
		if (scmPurQuoPriceAllList != null && !scmPurQuoPriceAllList.isEmpty() && scmPurEnquiryPriceAllList != null
				&& !scmPurEnquiryPriceAllList.isEmpty()) {
			for (int i = scmPurQuoPriceAllList.size(); i > 0; i--) {
				ScmPurPriceAll scmPurQuoPrice = scmPurQuoPriceAllList.get(i - 1);
				for (int j = scmPurEnquiryPriceAllList.size(); j > 0; j--) {
					ScmPurPriceAll scmPurEnquiryPrice = scmPurEnquiryPriceAllList.get(j - 1);
					if (scmPurQuoPrice.getItemId() == scmPurEnquiryPrice.getItemId()) {
						scmPurQuoPrice.setGroupPrice1(scmPurEnquiryPrice.getGroupPrice1());
						scmPurQuoPrice.setGroupPrice2(scmPurEnquiryPrice.getGroupPrice2());
						scmPurQuoPrice.setGroupPrice3(scmPurEnquiryPrice.getGroupPrice3());
						scmPurPriceAllList.add(scmPurQuoPrice);
						scmPurQuoPriceAllList.remove(scmPurQuoPrice);
						scmPurEnquiryPriceAllList.remove(scmPurEnquiryPrice);
					}
				}
			}
			scmPurPriceAllList.addAll(scmPurQuoPriceAllList);
			scmPurPriceAllList.addAll(scmPurEnquiryPriceAllList);
		} else {
			if (scmPurEnquiryPriceAllList != null && !scmPurEnquiryPriceAllList.isEmpty()) {
				scmPurPriceAllList.addAll(scmPurEnquiryPriceAllList);
			} else if (scmPurQuoPriceAllList != null && !scmPurQuoPriceAllList.isEmpty()) {
				scmPurPriceAllList.addAll(scmPurQuoPriceAllList);
			}
		}
		return scmPurPriceAllList;
	}

	@Override
	public List<ScmMaterialPrePrice> getPreMaterialPrice(ScmPurPriceQuery scmPurPriceQuery, Param param)
			throws AppException {
		/************************************************************************
		 * 6:临时定价
		 * 5:定供应商、定价格、定税率
		 * 4:不定供应商、定价格、定税率
		 * 3:定供应商、定价格、不定税率
		 * 2:不定供应商、定价格、不定税率
		 * 1:报价
		 * 0:手工价
		 ************************************************************************/
//		
		String priceMode = sysParamBiz.getValue(scmPurPriceQuery.getPurOrgUnitNo(), "SCM_PriceMode", "1", param);
		int pricePrec = Integer.valueOf(sysParamBiz.getValue(scmPurPriceQuery.getPurOrgUnitNo(), "SCM_PricePrecision", "2", param));
		String itemIds = scmPurPriceQuery.getItemIds();
		//先获取定价
		List<ScmMaterialPrePrice> scmMaterialPriceList = scmPurPriceBiz.getPreMaterialPrice(scmPurPriceQuery.getPurOrgUnitNo(),
				itemIds, scmPurPriceQuery.getBizDate(), scmPurPriceQuery.getFinOrgUnitNo(),
				scmPurPriceQuery.getPmId(), param);
		itemIds = ","+itemIds+",";
		if(scmMaterialPriceList!=null && !scmMaterialPriceList.isEmpty()){
			for(ScmMaterialPrePrice scmMaterialPrice:scmMaterialPriceList){
				scmMaterialPrice.setPrice(BigDecimal.ZERO);
				scmMaterialPrice.setTaxRate(BigDecimal.ZERO);
				scmMaterialPrice.setTaxPrice(BigDecimal.ZERO);
				scmMaterialPrice.setVendorId(0);
				if(scmMaterialPrice.getPreVendorId1() > 0 && scmMaterialPrice.getPreVendorId1() == scmPurPriceQuery.getVendorId()
						&& scmMaterialPrice.getPrePrice1().compareTo(BigDecimal.ZERO)>0){
					scmMaterialPrice.setPrice(scmMaterialPrice.getPrePrice1());
					scmMaterialPrice.setTaxRate(scmMaterialPrice.getPreTaxRate1());
					scmMaterialPrice.setTaxPrice(scmMaterialPrice.getPreTaxPrice1());
					scmMaterialPrice.setVendorId(scmMaterialPrice.getPreVendorId1());
				}else if(scmMaterialPrice.getPreVendorId2() > 0 && scmMaterialPrice.getPreVendorId2() == scmPurPriceQuery.getVendorId()
						&& scmMaterialPrice.getPrePrice2().compareTo(BigDecimal.ZERO)>0){
					scmMaterialPrice.setPrice(scmMaterialPrice.getPrePrice2());
					scmMaterialPrice.setTaxRate(scmMaterialPrice.getPreTaxRate2());
					scmMaterialPrice.setTaxPrice(scmMaterialPrice.getPreTaxPrice2());
					scmMaterialPrice.setVendorId(scmMaterialPrice.getPreVendorId2());
				}else if(scmMaterialPrice.getPreVendorId3() > 0 && scmMaterialPrice.getPreVendorId3() == scmPurPriceQuery.getVendorId()
						&& scmMaterialPrice.getPrePrice3().compareTo(BigDecimal.ZERO)>0){
					scmMaterialPrice.setPrice(scmMaterialPrice.getPrePrice3());
					scmMaterialPrice.setTaxRate(scmMaterialPrice.getPreTaxRate3());
					scmMaterialPrice.setTaxPrice(scmMaterialPrice.getPreTaxPrice3());
					scmMaterialPrice.setVendorId(scmMaterialPrice.getPreVendorId3());
				}
				if (StringUtils.equals("2", scmMaterialPrice.getBizType())) {
					if(scmMaterialPrice.getTaxRate().compareTo(BigDecimal.ZERO)>0){
						scmMaterialPrice.setRefPriceStatus("7");	//临时定价定税,临时定价必绑定供应商
					}else{
						scmMaterialPrice.setTaxRate(getTaxRate(scmPurPriceQuery.getPurOrgUnitNo(), scmPurPriceQuery.getVendorId(), scmMaterialPrice.getItemId(), param));
						scmMaterialPrice.setRefPriceStatus("6");	//临时定价不定税,临时定价必绑定供应商
					}
				} else {
					if(scmMaterialPrice.getVendorId()>0){
						if(scmMaterialPrice.getTaxRate().compareTo(BigDecimal.ZERO)>0){
							scmMaterialPrice.setRefPriceStatus("5");	//定价定税且绑定供应商
						}else{
							scmMaterialPrice.setTaxRate(getTaxRate(scmPurPriceQuery.getPurOrgUnitNo(), scmPurPriceQuery.getVendorId(), scmMaterialPrice.getItemId(), param));
							scmMaterialPrice.setRefPriceStatus("3");	//定价不定税绑定供应商
						}
					}else {
						if(scmMaterialPrice.getTaxRate().compareTo(BigDecimal.ZERO)>0){
							scmMaterialPrice.setRefPriceStatus("4");	//定价定税不绑定供应商
						}else{
							scmMaterialPrice.setTaxRate(getTaxRate(scmPurPriceQuery.getPurOrgUnitNo(), scmPurPriceQuery.getVendorId(), scmMaterialPrice.getItemId(), param));
							scmMaterialPrice.setPrice(scmMaterialPrice.getTaxPrice().divide((BigDecimal.ONE.add(scmMaterialPrice.getTaxRate())), pricePrec, RoundingMode.HALF_UP));
							scmMaterialPrice.setRefPriceStatus("2");	//定价不定税不绑定供应商
						}
					}
				}
				
				itemIds = StringUtils.replace(itemIds, ","+scmMaterialPrice.getItemId()+",", ",");
			}
		}
		itemIds = StringUtils.left(itemIds, StringUtils.length(itemIds)-1);
		if(StringUtils.isNotBlank(itemIds))
			itemIds = StringUtils.right(itemIds, StringUtils.length(itemIds)-1);
		if(StringUtils.isNotBlank(itemIds)) {
			if(scmMaterialPriceList==null)
				scmMaterialPriceList = new ArrayList<ScmMaterialPrePrice>();
			String[] itemIdList = StringUtils.split(itemIds,",");
			for(String itemId:itemIdList) {
				ScmMaterialPrePrice scmMaterialPrice = new ScmMaterialPrePrice();
				scmMaterialPrice.setItemId(Long.valueOf(itemId));
				scmMaterialPrice.setPrice(BigDecimal.ZERO);
				scmMaterialPrice.setTaxRate(getTaxRate(scmPurPriceQuery.getPurOrgUnitNo(), scmPurPriceQuery.getVendorId(), scmMaterialPrice.getItemId(), param));
				scmMaterialPrice.setTaxPrice(BigDecimal.ZERO);
				scmMaterialPrice.setRefPriceStatus("0");
				scmMaterialPriceList.add(scmMaterialPrice);
			}
		}
		if(scmMaterialPriceList!=null && !scmMaterialPriceList.isEmpty()){
			for(ScmMaterialPrePrice scmMaterialPrice:scmMaterialPriceList){
				PubSysBasicInfo pubSysBasicInfo = pubSysBasicInfoBiz.selectByTaxRate("TaxRate", scmMaterialPrice.getTaxRate().toString(), null, param);
				if (pubSysBasicInfo != null) {
					scmMaterialPrice.setTaxRateStr(pubSysBasicInfo.getFInfoNo());
					scmMaterialPrice.setConvertMap("taxRateStr", pubSysBasicInfo);
				}
				if(scmMaterialPrice.getVendorId()>0) {
					Scmsupplier2 scmsupplier = scmsupplierBiz.selectDirect(scmMaterialPrice.getVendorId(), param);
					if(scmsupplier!=null) {
						scmMaterialPrice.setConvertMap("vendorId", scmsupplier);
					}
				}
				
				if(scmMaterialPrice.getPrePrice1() == null){
					scmMaterialPrice.setPrePrice1(BigDecimal.ZERO);
				}
				if(scmMaterialPrice.getPreTaxRate1() == null){
					scmMaterialPrice.setPreTaxRate1(BigDecimal.ZERO);
				}
				if(scmMaterialPrice.getPreTaxPrice1() == null){
					scmMaterialPrice.setPreTaxPrice1(BigDecimal.ZERO);
				}
				PubSysBasicInfo pubSysBasicInfo2 = pubSysBasicInfoBiz.selectByTaxRate("TaxRate", scmMaterialPrice.getPreTaxRate1().toString(), null, param);
				if (pubSysBasicInfo2 != null) {
					scmMaterialPrice.setPreTaxRateStr1(pubSysBasicInfo2.getFInfoNo());
					scmMaterialPrice.setConvertMap("preTaxRateStr1", pubSysBasicInfo2);
				}
				
				if(scmMaterialPrice.getPrePrice2() == null){
					scmMaterialPrice.setPrePrice2(BigDecimal.ZERO);
				}
				if(scmMaterialPrice.getPreTaxRate2() == null){
					scmMaterialPrice.setPreTaxRate2(BigDecimal.ZERO);
				}
				if(scmMaterialPrice.getPreTaxPrice2() == null){
					scmMaterialPrice.setPreTaxPrice2(BigDecimal.ZERO);
				}
				PubSysBasicInfo pubSysBasicInfo3 = pubSysBasicInfoBiz.selectByTaxRate("TaxRate", scmMaterialPrice.getPreTaxRate2().toString(), null, param);
				if (pubSysBasicInfo3 != null) {
					scmMaterialPrice.setPreTaxRateStr2(pubSysBasicInfo3.getFInfoNo());
					scmMaterialPrice.setConvertMap("preTaxRateStr2", pubSysBasicInfo3);
				}
				
				if(scmMaterialPrice.getPrePrice3() == null){
					scmMaterialPrice.setPrePrice3(BigDecimal.ZERO);
				}
				if(scmMaterialPrice.getPreTaxRate3() == null){
					scmMaterialPrice.setPreTaxRate3(BigDecimal.ZERO);
				}
				if(scmMaterialPrice.getPreTaxPrice3() == null){
					scmMaterialPrice.setPreTaxPrice3(BigDecimal.ZERO);
				}
				PubSysBasicInfo pubSysBasicInfo4 = pubSysBasicInfoBiz.selectByTaxRate("TaxRate", scmMaterialPrice.getPreTaxRate3().toString(), null, param);
				if (pubSysBasicInfo4 != null) {
					scmMaterialPrice.setPreTaxRateStr3(pubSysBasicInfo4.getFInfoNo());
					scmMaterialPrice.setConvertMap("preTaxRateStr3", pubSysBasicInfo4);
				}
			}
		}
		return scmMaterialPriceList;
	}

	@Override
	public List<ScmMaterialPrice> getPreParePrice(ScmPurPriceQuery scmPurPriceQuery, Param param) throws AppException {
		/************************************************************************
		 * 6:临时定价
		 * 5:定供应商、定价格、定税率
		 * 4:不定供应商、定价格、定税率
		 * 3:定供应商、定价格、不定税率
		 * 2:不定供应商、定价格、不定税率
		 * 1:报价
		 * 0:手工价
		 ************************************************************************/
//		
		String priceMode = sysParamBiz.getValue(scmPurPriceQuery.getPurOrgUnitNo(), "SCM_PriceMode", "1", param);
		String getPriceWay = sysParamBiz.getValue(scmPurPriceQuery.getPurOrgUnitNo(), "SCM_GetPriceWay", "N", param);
		int pricePrec = Integer.valueOf(sysParamBiz.getValue(scmPurPriceQuery.getPurOrgUnitNo(), "SCM_PricePrecision", "2", param));
		String itemIds = scmPurPriceQuery.getItemIds();
		List<ScmMaterialPrice> scmMaterialPriceList=new ArrayList<>();
		if (StringUtils.equals("Y", getPriceWay)) {
			scmMaterialPriceList=scmPurPriceBiz.getPreParePrice(scmPurPriceQuery.getPurOrgUnitNo(),
					itemIds, scmPurPriceQuery.getBizDate(), scmPurPriceQuery.getFinOrgUnitNo(),
					scmPurPriceQuery.getPmId(),scmPurPriceQuery.getVendorId(), param);
		}else {
			//先获取定价
			 scmMaterialPriceList = scmPurPriceBiz.getPreParePrice(scmPurPriceQuery.getPurOrgUnitNo(),
					itemIds, scmPurPriceQuery.getBizDate(), scmPurPriceQuery.getFinOrgUnitNo(),
					scmPurPriceQuery.getPmId(), param);
		}
		itemIds = ","+itemIds+",";
		if(scmMaterialPriceList!=null && !scmMaterialPriceList.isEmpty()){
			for(ScmMaterialPrice scmMaterialPrice:scmMaterialPriceList){
				if (StringUtils.equals("2", scmMaterialPrice.getBizType())) {
					if(scmMaterialPrice.getTaxRate().compareTo(BigDecimal.ZERO)>0){
						scmMaterialPrice.setRefPriceStatus("7");	//临时定价定税,临时定价必绑定供应商
					}else{
						scmMaterialPrice.setTaxRate(getTaxRate(scmPurPriceQuery.getPurOrgUnitNo(), scmPurPriceQuery.getVendorId(), scmMaterialPrice.getItemId(), param));
						scmMaterialPrice.setRefPriceStatus("6");	//临时定价不定税,临时定价必绑定供应商
					}
				} else {
					if(scmMaterialPrice.getVendorId()>0){
						if(scmMaterialPrice.getTaxRate().compareTo(BigDecimal.ZERO)>0){
							scmMaterialPrice.setRefPriceStatus("5");	//定价定税且绑定供应商
						}else{
							scmMaterialPrice.setTaxRate(getTaxRate(scmPurPriceQuery.getPurOrgUnitNo(), scmPurPriceQuery.getVendorId(), scmMaterialPrice.getItemId(), param));
							scmMaterialPrice.setRefPriceStatus("3");	//定价不定税绑定供应商
						}
					}else {
						if(scmMaterialPrice.getTaxRate().compareTo(BigDecimal.ZERO)>0){
							scmMaterialPrice.setRefPriceStatus("4");	//定价定税不绑定供应商
						}else{
							scmMaterialPrice.setTaxRate(getTaxRate(scmPurPriceQuery.getPurOrgUnitNo(), scmPurPriceQuery.getVendorId(), scmMaterialPrice.getItemId(), param));
							scmMaterialPrice.setPrice(scmMaterialPrice.getTaxPrice().divide((BigDecimal.ONE.add(scmMaterialPrice.getTaxRate())), pricePrec, RoundingMode.HALF_UP));
							scmMaterialPrice.setRefPriceStatus("2");	//定价不定税不绑定供应商
						}
					}
				}
				
				itemIds = StringUtils.replace(itemIds, ","+scmMaterialPrice.getItemId()+",", ",");
			}
		}
		itemIds = StringUtils.left(itemIds, StringUtils.length(itemIds)-1);
		if(StringUtils.isNotBlank(itemIds))
			itemIds = StringUtils.right(itemIds, StringUtils.length(itemIds)-1);
		if(StringUtils.isNotBlank(itemIds)){
			if(scmMaterialPriceList==null)
				scmMaterialPriceList = new ArrayList<ScmMaterialPrice>();
			//定价不存在同获取报价
			List<ScmMaterialPrice> scmMaterialPriceList2 = scmPurQuotationBiz.getPrice(scmPurPriceQuery.getPurOrgUnitNo(),scmPurPriceQuery.getVendorId(),
					itemIds, scmPurPriceQuery.getBizDate(),param);
			itemIds = ","+itemIds+",";
			if(scmMaterialPriceList2!=null && !scmMaterialPriceList2.isEmpty()){
				for(ScmMaterialPrice scmMaterialPrice:scmMaterialPriceList2){
					if(scmMaterialPrice.getTaxRate().compareTo(BigDecimal.ZERO)==0){
						scmMaterialPrice.setTaxRate(getTaxRate(scmPurPriceQuery.getPurOrgUnitNo(), scmPurPriceQuery.getVendorId(), scmMaterialPrice.getItemId(), param));
						if(StringUtils.equals("1", priceMode)) {
							scmMaterialPrice.setPrice(scmMaterialPrice.getTaxPrice().divide((BigDecimal.ONE.add(scmMaterialPrice.getTaxRate())), pricePrec, RoundingMode.HALF_UP));
						}else {
							scmMaterialPrice.setTaxPrice(scmMaterialPrice.getPrice().multiply((BigDecimal.ONE.add(scmMaterialPrice.getTaxRate()))).setScale(pricePrec, RoundingMode.HALF_UP));
						}
					}
					scmMaterialPrice.setRefPriceStatus("1");	//报价
					itemIds = StringUtils.replace(itemIds, ","+scmMaterialPrice.getItemId()+",", ",");
				}
				scmMaterialPriceList.addAll(scmMaterialPriceList2);
			}
			
		}
		itemIds = StringUtils.left(itemIds, StringUtils.length(itemIds)-1);
		if(StringUtils.isNotBlank(itemIds))
			itemIds = StringUtils.right(itemIds, StringUtils.length(itemIds)-1);
		if(StringUtils.isNotBlank(itemIds)) {
			if(scmMaterialPriceList==null)
				scmMaterialPriceList = new ArrayList<ScmMaterialPrice>();
			String[] itemIdList = StringUtils.split(itemIds,",");
			for(String itemId:itemIdList) {
				ScmMaterialPrice scmMaterialPrice = new ScmMaterialPrice();
				scmMaterialPrice.setItemId(Long.valueOf(itemId));
				scmMaterialPrice.setPrice(BigDecimal.ZERO);
				scmMaterialPrice.setTaxRate(getTaxRate(scmPurPriceQuery.getPurOrgUnitNo(), scmPurPriceQuery.getVendorId(), scmMaterialPrice.getItemId(), param));
				scmMaterialPrice.setTaxPrice(BigDecimal.ZERO);
				scmMaterialPrice.setRefPriceStatus("0");
				scmMaterialPriceList.add(scmMaterialPrice);
			}
		}
		if(scmMaterialPriceList!=null && !scmMaterialPriceList.isEmpty()){
			HashMap<String,Object> myCacheDataMap = new HashMap<String,Object>();
			for(ScmMaterialPrice scmMaterialPrice:scmMaterialPriceList){
				PubSysBasicInfo pubSysBasicInfo = pubSysBasicInfoBiz.selectByTaxRate("TaxRate", scmMaterialPrice.getTaxRate().toString(), null, param);
				if (pubSysBasicInfo != null) {
					scmMaterialPrice.setTaxRateStr(pubSysBasicInfo.getFInfoNo());
					scmMaterialPrice.setConvertMap("taxRateStr", pubSysBasicInfo);
				}
				if(scmMaterialPrice.getVendorId()>0) {
					Scmsupplier2 scmsupplier = scmsupplierBiz.selectDirect(scmMaterialPrice.getVendorId(), param);
					if(scmsupplier!=null) {
						scmMaterialPrice.setConvertMap("vendorId", scmsupplier);
					}
				}
				if(scmMaterialPrice.getPreTaxPrice1() == null){
					scmMaterialPrice.setPreTaxPrice1(BigDecimal.ZERO);
				}
				
				if(scmMaterialPrice.getPreTaxPrice2() == null){
					scmMaterialPrice.setPreTaxPrice2(BigDecimal.ZERO);
				}
				
				if(scmMaterialPrice.getPreTaxPrice3() == null){
					scmMaterialPrice.setPreTaxPrice3(BigDecimal.ZERO);
				}
				
				if(scmMaterialPrice.getPriceBillId() > 0 && StringUtils.isNotBlank(scmMaterialPrice.getRefPriceStatus())){
		        	//价格来源
		        	if("1".equals(scmMaterialPrice.getRefPriceStatus())){
		        		//报价
		        		ScmPurQuotation2 scmPurQuotation = (ScmPurQuotation2) myCacheDataMap.get(ClassUtils.getFinalModelSimpleName(ScmPurQuotation2.class)+"_"+scmMaterialPrice.getPriceBillId());
		        		if(scmPurQuotation==null) {
		        			scmPurQuotation = scmPurQuotationBiz.selectDirect(scmMaterialPrice.getPriceBillId(), param);
		        			myCacheDataMap.put(ClassUtils.getFinalModelSimpleName(ScmPurQuotation2.class)+"_"+scmMaterialPrice.getPriceBillId(),scmPurQuotation);
		        		}
			            if (scmPurQuotation != null) {
			            	scmMaterialPrice.setPriceBillNo(scmPurQuotation.getPqNo());
			            	scmMaterialPrice.setPriceBillStatus(scmPurQuotation.getStatus());
			            }
		        	}else{
		        		//定价（包括临时定价）
		        		ScmPurPrice2 scmPurPrice = (ScmPurPrice2) myCacheDataMap.get(ClassUtils.getFinalModelSimpleName(ScmPurPrice2.class)+"_"+scmMaterialPrice.getPriceBillId());
		        		if(scmPurPrice==null) {
		        			scmPurPrice = scmPurPriceBiz.selectDirect(scmMaterialPrice.getPriceBillId(), param);
		        			myCacheDataMap.put(ClassUtils.getFinalModelSimpleName(ScmPurPrice2.class)+"_"+scmMaterialPrice.getPriceBillId(),scmPurPrice);
		        		}
			            if (scmPurPrice != null) {
			            	scmMaterialPrice.setPriceBillNo(scmPurPrice.getPmNo());
			            	scmMaterialPrice.setPriceBillStatus(scmPurPrice.getStatus());
			            }
		        	}
		        }
			}
		}
		return scmMaterialPriceList;
	}

	@Override
	public List<ScmMaterialPrice> getMaterialSupplyInfoList(ScmPurSupplyInfoQuery scmPurSupplyInfoQuery,
			Param param) throws AppException {
		List<ScmPurSupplyInfo2> scmPurSupplyInfo2s = scmPurSupplyInfoBiz.getSupplyInfoByItemList(
				scmPurSupplyInfoQuery.getPurOrgUnitNo(), scmPurSupplyInfoQuery.getInvOrgUnitNo(),
				scmPurSupplyInfoQuery.getItemIdList(), scmPurSupplyInfoQuery.getBizDate(), param);
		List<ScmMaterialPrice> scmMaterialPrices=new ArrayList<ScmMaterialPrice>();
		if (scmPurSupplyInfo2s !=null && scmPurSupplyInfo2s.size()>0) {
			for (ScmPurSupplyInfo2 scmPurSupplyInfo : scmPurSupplyInfo2s) {
				ScmMaterialPrice scmMaterialPrice = new ScmMaterialPrice();
				scmMaterialPrice.setItemId(scmPurSupplyInfo.getItemId());
				scmMaterialPrice.setVendorId(scmPurSupplyInfo.getVendorId());
				scmMaterialPrice.setDirectPurchase(scmPurSupplyInfo.isDirectPurchase());
				if(scmPurSupplyInfo.getVendorId()>0) {
					Scmsupplier2 scmsupplier = scmsupplierBiz.selectDirect(scmPurSupplyInfo.getVendorId(), param);
					if(scmsupplier!=null && StringUtils.equals("A",scmsupplier.getStatus())) {
						scmMaterialPrice.setConvertMap("vendorId", scmsupplier);
					} else {
						scmMaterialPrice.setVendorId(0);
					}
				}
				scmMaterialPrices.add(scmMaterialPrice);
			}
			return scmMaterialPrices;
		}
		return null;
	}

	@Override
	public List<ScmMaterialPrice> getMaterialPriceByItemidsAndVendorIdsList(List<ScmPurPriceQuery> scmPurPriceQueries, Param param)
			throws AppException {
		/************************************************************************
		 * 7:临时定价定税,临时定价必绑定供应商
		 * 6:临时定价不定税,临时定价必绑定供应商
		 * 5:定供应商、定价格、定税率
		 * 4:不定供应商、定价格、定税率
		 * 3:定供应商、定价格、不定税率
		 * 2:不定供应商、定价格、不定税率
		 * 1:报价
		 * 0:手工价
		 ************************************************************************/
		ArrayList<ScmPurPriceQuery> arrayList = new ArrayList<>();
		arrayList.addAll(scmPurPriceQueries);
		
		String priceMode = sysParamBiz.getValue(scmPurPriceQueries.get(0).getPurOrgUnitNo(), "SCM_PriceMode", "1", param);
		String getPriceWay = sysParamBiz.getValue(scmPurPriceQueries.get(0).getPurOrgUnitNo(), "SCM_GetPriceWay", "N", param);
		int pricePrec = Integer.valueOf(sysParamBiz.getValue(scmPurPriceQueries.get(0).getPurOrgUnitNo(), "SCM_PricePrecision", "2", param));
		List<ScmMaterialPrice> scmMaterialPriceList=new ArrayList<>();
		scmMaterialPriceList=scmPurPriceBiz.getMaterialPriceByItemidsAndVendorIdsList(scmPurPriceQueries, param);
		if(scmMaterialPriceList!=null && !scmMaterialPriceList.isEmpty()){
			for(ScmMaterialPrice scmMaterialPrice:scmMaterialPriceList){
				if (StringUtils.equals("2", scmMaterialPrice.getBizType())) {
					if(scmMaterialPrice.getTaxRate().compareTo(BigDecimal.ZERO)>0){
						scmMaterialPrice.setRefPriceStatus("7");	//临时定价定税,临时定价必绑定供应商
					}else{
						scmMaterialPrice.setTaxRate(getTaxRate(scmPurPriceQueries.get(0).getPurOrgUnitNo(), scmMaterialPrice.getVendorId(), scmMaterialPrice.getItemId(), param));
						scmMaterialPrice.setRefPriceStatus("6");	//临时定价不定税,临时定价必绑定供应商
					}
				} else {
					if(scmMaterialPrice.getVendorId()>0){
						if(scmMaterialPrice.getTaxRate().compareTo(BigDecimal.ZERO)>0){
							scmMaterialPrice.setRefPriceStatus("5");	//定价定税且绑定供应商
						}else{
							scmMaterialPrice.setTaxRate(getTaxRate(scmPurPriceQueries.get(0).getPurOrgUnitNo(), scmMaterialPrice.getVendorId(), scmMaterialPrice.getItemId(), param));
							scmMaterialPrice.setRefPriceStatus("3");	//定价不定税绑定供应商
						}
					}else {
						if(scmMaterialPrice.getTaxRate().compareTo(BigDecimal.ZERO)>0){
							scmMaterialPrice.setRefPriceStatus("4");	//定价定税不绑定供应商
						}else{
							scmMaterialPrice.setTaxRate(getTaxRate(scmPurPriceQueries.get(0).getPurOrgUnitNo(), scmMaterialPrice.getVendorId(), scmMaterialPrice.getItemId(), param));
							scmMaterialPrice.setPrice(scmMaterialPrice.getTaxPrice().divide((BigDecimal.ONE.add(scmMaterialPrice.getTaxRate())), pricePrec, RoundingMode.HALF_UP));
							scmMaterialPrice.setRefPriceStatus("2");	//定价不定税不绑定供应商
						}
					}
				}
				for (ScmPurPriceQuery scmPurPriceQuery : arrayList) {
					if (scmMaterialPrice.getItemId()==scmPurPriceQuery.getItemId()) {
						scmPurPriceQueries.remove(scmPurPriceQuery);
					}
				}
			}
		}
		if(scmPurPriceQueries != null && !scmPurPriceQueries.isEmpty()) {
			if(scmMaterialPriceList==null)
				scmMaterialPriceList = new ArrayList<ScmMaterialPrice>();
			//定价不存在同获取报价
			List<ScmMaterialPrice> scmMaterialPriceList2 = scmPurQuotationBiz.getPriceByVendorIds(scmPurPriceQueries,param);
			if(scmMaterialPriceList2!=null && !scmMaterialPriceList2.isEmpty()){
				arrayList.clear();
				arrayList.addAll(scmPurPriceQueries);
				for(ScmMaterialPrice scmMaterialPrice:scmMaterialPriceList2){
					if(scmMaterialPrice.getTaxRate().compareTo(BigDecimal.ZERO)==0){
						scmMaterialPrice.setTaxRate(getTaxRate(scmPurPriceQueries.get(0).getPurOrgUnitNo(), scmPurPriceQueries.get(0).getVendorId(), scmMaterialPrice.getItemId(), param));
						if(StringUtils.equals("1", priceMode)) {
							scmMaterialPrice.setPrice(scmMaterialPrice.getTaxPrice().divide((BigDecimal.ONE.add(scmMaterialPrice.getTaxRate())), pricePrec, RoundingMode.HALF_UP));
						}else {
							scmMaterialPrice.setTaxPrice(scmMaterialPrice.getPrice().multiply((BigDecimal.ONE.add(scmMaterialPrice.getTaxRate()))).setScale(pricePrec, RoundingMode.HALF_UP));
						}
					}
					scmMaterialPrice.setRefPriceStatus("1");	//报价
					for (ScmPurPriceQuery scmPurPriceQuery : arrayList) {
						if (scmMaterialPrice.getItemId()==scmPurPriceQuery.getItemId()) {
							scmPurPriceQueries.remove(scmPurPriceQuery);
						}
					}
				}
				scmMaterialPriceList.addAll(scmMaterialPriceList2);
			}
		}
		if(scmPurPriceQueries != null && !scmPurPriceQueries.isEmpty()) {
			if(scmMaterialPriceList==null)
				scmMaterialPriceList = new ArrayList<ScmMaterialPrice>();
			for(ScmPurPriceQuery scmPurPriceQuery:scmPurPriceQueries) {
				ScmMaterialPrice scmMaterialPrice = new ScmMaterialPrice();
				scmMaterialPrice.setItemId(Long.valueOf(scmPurPriceQuery.getItemId()));
				scmMaterialPrice.setPrice(BigDecimal.ZERO);
				scmMaterialPrice.setTaxRate(getTaxRate(scmPurPriceQuery.getPurOrgUnitNo(), scmPurPriceQuery.getVendorId(), scmMaterialPrice.getItemId(), param));
				scmMaterialPrice.setTaxPrice(BigDecimal.ZERO);
				scmMaterialPrice.setRefPriceStatus("0");
				scmMaterialPriceList.add(scmMaterialPrice);
			}
		}
		if(scmMaterialPriceList!=null && !scmMaterialPriceList.isEmpty()){
			for(ScmMaterialPrice scmMaterialPrice:scmMaterialPriceList){
				PubSysBasicInfo pubSysBasicInfo = pubSysBasicInfoBiz.selectByTaxRate("TaxRate", scmMaterialPrice.getTaxRate().toString(), null, param);
				if (pubSysBasicInfo != null) {
					scmMaterialPrice.setTaxRateStr(pubSysBasicInfo.getFInfoNo());
					scmMaterialPrice.setConvertMap("taxRateStr", pubSysBasicInfo);
				}
				if(scmMaterialPrice.getVendorId()>0) {
					Scmsupplier2 scmsupplier = scmsupplierBiz.selectDirect(scmMaterialPrice.getVendorId(), param);
					if(scmsupplier!=null) {
						scmMaterialPrice.setConvertMap("vendorId", scmsupplier);
					}
				}
				if(scmMaterialPrice.getPreTaxPrice1() == null){
					scmMaterialPrice.setPreTaxPrice1(BigDecimal.ZERO);
				}
				
				if(scmMaterialPrice.getPreTaxPrice2() == null){
					scmMaterialPrice.setPreTaxPrice2(BigDecimal.ZERO);
				}
				
				if(scmMaterialPrice.getPreTaxPrice3() == null){
					scmMaterialPrice.setPreTaxPrice3(BigDecimal.ZERO);
				}
				
				if(scmMaterialPrice.getPriceBillId() > 0 && StringUtils.isNotBlank(scmMaterialPrice.getRefPriceStatus())){
		        	//价格来源
		        	if("1".equals(scmMaterialPrice.getRefPriceStatus())){
		        		//报价
		        		ScmPurQuotation2 scmPurQuotation = scmPurQuotationBiz.selectDirect(scmMaterialPrice.getPriceBillId(), param);
			            if (scmPurQuotation != null) {
			            	scmMaterialPrice.setPriceBillNo(scmPurQuotation.getPqNo());
			            	scmMaterialPrice.setPriceBillStatus(scmPurQuotation.getStatus());
			            }
		        	}else{
		        		//定价（包括临时定价）
		        		ScmPurPrice2 scmPurPrice = scmPurPriceBiz.selectDirect(scmMaterialPrice.getPriceBillId(), param);
			            if (scmPurPrice != null) {
			            	scmMaterialPrice.setPriceBillNo(scmPurPrice.getPmNo());
			            	scmMaterialPrice.setPriceBillStatus(scmPurPrice.getStatus());
			            }
		        	}
		        }
			}
		}
		return scmMaterialPriceList;
	}
	
	@Override
	public List<ScmMaterialPrice> getRecentPriceAndStockList(ScmInvPriceQuery scmInvPriceQuery, Param param)throws AppException {
		ArrayList<ScmMaterialPrice> scmMaterialPrices = new ArrayList<>();
		if (scmInvPriceQuery.getWarehouseId()>0) {
			List<ScmInvStock2> scmInvStock2List = scmInvStockBiz.getStockQtyList(scmInvPriceQuery.getInvOrgUnitNo(), scmInvPriceQuery.getItemIds(),0, param);
			if (scmInvStock2List != null && !scmInvStock2List.isEmpty()) {
				for (ScmInvStock2 scmInvStock2 : scmInvStock2List) {
					ScmMaterialPrice scmMaterialPrice = new ScmMaterialPrice();
					scmMaterialPrice.setInvQty(scmInvStock2.getQty());
					scmMaterialPrice.setItemId(scmInvStock2.getItemId());
					scmMaterialPrices.add(scmMaterialPrice);
				}
			}
		}else {
			List<OrgCompany2> finOrgList = orgUnitRelationBiz.findToOrgUnitByType(OrgUnitRelationType.ADMINTOFIN, scmInvPriceQuery.getReqOrgUnitNo(), false, null, param);
			if (finOrgList != null &&!finOrgList.isEmpty()) {
				List<ScmInvStock2> scmInvStock2List = new ArrayList<>();
				String orgOrWhStockQty = sysParamBiz.getValue(finOrgList.get(0).getOrgUnitNo(), "SCM_OrgOrWhStockQty", "S", param);
				long i = 0;
				if (StringUtils.equals(orgOrWhStockQty, "S")) {
					scmInvStock2List = scmInvStockBiz.getStockQtyList(scmInvPriceQuery.getInvOrgUnitNo(), scmInvPriceQuery.getItemIds(),i, param);
				}else {
					i=1;
					scmInvStock2List = scmInvStockBiz.getStockQtyList(scmInvPriceQuery.getReqOrgUnitNo(), scmInvPriceQuery.getItemIds(),i, param);
				}
				if (scmInvStock2List != null && !scmInvStock2List.isEmpty()) {
					for (ScmInvStock2 scmInvStock2 : scmInvStock2List) {
						ScmMaterialPrice scmMaterialPrice = new ScmMaterialPrice();
						scmMaterialPrice.setInvQty(scmInvStock2.getQty());
						scmMaterialPrice.setItemId(scmInvStock2.getItemId());
						scmMaterialPrices.add(scmMaterialPrice);
					}
				}
			}
		}
		List<ScmInvPurInWarehsBillEntry2> scmInvPurInWarehsBillEntrieList = scmInvPurInWarehsBillBiz.getInvPriceList(scmInvPriceQuery.getInvOrgUnitNo(), scmInvPriceQuery.getItemIds(), param);
		if (scmInvPurInWarehsBillEntrieList != null && !scmInvPurInWarehsBillEntrieList.isEmpty()) {
			for (ScmInvPurInWarehsBillEntry2 scmInvPurInWarehsBillEntry2 : scmInvPurInWarehsBillEntrieList) {
				boolean flag =true;
				if (scmMaterialPrices != null && !scmMaterialPrices.isEmpty()) {
					for (ScmMaterialPrice scmMaterialPrice : scmMaterialPrices) {
						if (scmMaterialPrice.getItemId()==scmInvPurInWarehsBillEntry2.getItemId()) {
							flag=false;
							scmMaterialPrice.setPrice(scmInvPurInWarehsBillEntry2.getPrice());
						}
					}
				}
				if (flag) {
					ScmMaterialPrice scmMaterialPrice = new ScmMaterialPrice();
					scmMaterialPrice.setPrice(scmInvPurInWarehsBillEntry2.getPrice());
					scmMaterialPrice.setItemId(scmInvPurInWarehsBillEntry2.getItemId());
					scmMaterialPrices.add(scmMaterialPrice);
				}
			}
		}
		return scmMaterialPrices;
	}

	@Override
	public ScmMaterialPrice getRecentPriceAndStock(ScmInvPriceQuery scmInvPriceQuery, Param param) throws AppException {
		ScmMaterialPrice scmMaterialPrice = new ScmMaterialPrice();
		if (scmInvPriceQuery.getWarehouseId()>0) {
			BigDecimal invQty = scmInvStockBiz.getStockQty(scmInvPriceQuery.getInvOrgUnitNo(), scmInvPriceQuery.getItemNo(), param);
			BigDecimal price = scmInvPurInWarehsBillBiz.getInvPrice(scmInvPriceQuery.getInvOrgUnitNo(), scmInvPriceQuery.getItemNo(), param);
			scmMaterialPrice.setInvQty(invQty);
			scmMaterialPrice.setPrice(price);
		}else {
			List<OrgCompany2> finOrgList = orgUnitRelationBiz.findToOrgUnitByType(OrgUnitRelationType.ADMINTOFIN, scmInvPriceQuery.getReqOrgUnitNo(), false, null, param);
			if (finOrgList != null &&!finOrgList.isEmpty()) {
				String orgOrWhStockQty = sysParamBiz.getValue(finOrgList.get(0).getOrgUnitNo(), "SCM_OrgOrWhStockQty", "S", param);
				if (StringUtils.equals(orgOrWhStockQty, "S")) {
					BigDecimal invQty = scmInvStockBiz.getStockQty(scmInvPriceQuery.getInvOrgUnitNo(), scmInvPriceQuery.getItemNo(), param);
					BigDecimal price = scmInvPurInWarehsBillBiz.getInvPrice(scmInvPriceQuery.getInvOrgUnitNo(), scmInvPriceQuery.getItemNo(), param);
					scmMaterialPrice.setInvQty(invQty);
					scmMaterialPrice.setPrice(price);
				}else {
					BigDecimal invQty = scmInvStockBiz.getStockQtyByReqOrg(scmInvPriceQuery.getReqOrgUnitNo(), scmInvPriceQuery.getItemNo(), param);
					BigDecimal price = scmInvPurInWarehsBillBiz.getInvPrice(scmInvPriceQuery.getInvOrgUnitNo(), scmInvPriceQuery.getItemNo(), param);
					scmMaterialPrice.setInvQty(invQty);
					scmMaterialPrice.setPrice(price);
				}
			}
		}
		return scmMaterialPrice;
	}

	@Override
	public ScmMaterialPrice selectCostPrice(long itemId, Param param) throws AppException {
		ScmMaterialPrice scmMaterialPrice = new ScmMaterialPrice(true);
		if (itemId>0) {
			List<OrgCompany2> findChild= null;
			String orgCompany = null;
			StringBuffer finOrg = new StringBuffer();
			ScmMaterialCompanyInfo scmMaterialCompanyInfo = null;
			if (StringUtils.equals(param.getControlUnitNo(), param.getOrgUnitNo())) {
				orgCompany = param.getControlUnitNo();
				findChild = orgCompanyBiz.findChild(param.getControlUnitNo(), param);
			}else {
				List<OrgCompany2> finOrgList = orgUnitRelationBiz.findToOrgUnitByType(OrgUnitRelationType.RESTOFIN, param.getOrgUnitNo(), false, null, param);
				if (finOrgList != null && !finOrgList.isEmpty()) {
					orgCompany = finOrgList.get(0).getOrgUnitNo();
					findChild = orgCompanyBiz.findChild(orgCompany, param);
				}
			}
			if (findChild != null && !findChild.isEmpty()) {
				for (OrgCompany2 orgCompany2 : findChild) {
					if (StringUtils.isNotBlank(finOrg.toString())) {
						finOrg.append(",");
					}
					finOrg.append("'").append(orgCompany2.getOrgUnitNo()).append("'");
				}
			}
			if (StringUtils.isNotBlank(orgCompany)) {
				scmMaterialCompanyInfo = scmMaterialCompanyInfoBiz.selectByItemIdAndOrgUnitNo(itemId, orgCompany, param);
			}
			//1成品，2半成品；根据配方管理获取价格
			if (scmMaterialCompanyInfo != null && StringUtils.contains("1,2", scmMaterialCompanyInfo.getType())) {
				scmMaterialPrice = getProductCostCardPrice(itemId,finOrg.toString(),param);
			}else {
			 	ScmInvStock2 scmInvStock2 = scmInvStockBiz.selectCostPrice(itemId,finOrg.toString(),param);
			 	if (scmInvStock2 != null) {
				 	scmMaterialPrice.setStockAvgPrice(scmInvStock2.getAvgPrice());
				 	scmMaterialPrice.setInvUnit(scmInvStock2.getUnit());
				 	if (scmInvStock2.getUnit() > 0){
				 		ScmMaterialUnitRelation2 scmMaterialUnitRelation = scmMaterialUnitRelationBiz.selectByItemAndUnit(itemId, scmInvStock2.getUnit(), param);
						if(scmMaterialUnitRelation != null){
							scmMaterialPrice.setConvertMap(ScmMaterial2.FN_UNITID, scmMaterialUnitRelation);
						}
					}
				}
			}
		}
		return scmMaterialPrice;
	}

	/**
	 * 获取半成品，成品成本价
	 * @param itemId
	 * @param finOrg 
	 * @param param
	 * @return
	 */
	private ScmMaterialPrice getProductCostCardPrice(long itemId, String finOrg, Param param) {
		ScmMaterialPrice scmMaterialPrice = new ScmMaterialPrice(true);
		List<ScmProductCostCardDetailHistory> scmProductCostCardDetailHistories = scmProductCostCardDetailHistoryBiz.selectByProductIdAndDate(param.getOrgUnitNo(), itemId, new Date(), param);
		if (scmProductCostCardDetailHistories != null && !scmProductCostCardDetailHistories.isEmpty()) {
			scmMaterialPrice.setStockAvgPrice(scmProductCostCardDetailHistories.get(0).getCostPrice().divide(scmProductCostCardDetailHistories.get(0).getProductQty(),4,RoundingMode.HALF_UP));
			scmMaterialPrice.setInvUnit(scmProductCostCardDetailHistories.get(0).getProductUnit());
		}
		if (scmMaterialPrice.getInvUnit() > 0){
	 		ScmMaterialUnitRelation2 scmMaterialUnitRelation = scmMaterialUnitRelationBiz.selectByItemAndUnit(itemId, scmMaterialPrice.getInvUnit(), param);
			if(scmMaterialUnitRelation != null){
				scmMaterialPrice.setConvertMap(ScmMaterial2.FN_UNITID, scmMaterialUnitRelation);
			}
		}
		return scmMaterialPrice;
	}

}
