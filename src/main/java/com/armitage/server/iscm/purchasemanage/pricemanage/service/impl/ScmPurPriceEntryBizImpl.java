
package com.armitage.server.iscm.purchasemanage.pricemanage.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.basedata.model.PubSysBasicInfo;
import com.armitage.server.iscm.basedata.model.Scmsupplier2;
import com.armitage.server.iscm.basedata.service.PubSysBasicInfoBiz;
import com.armitage.server.iscm.basedata.service.ScmsupplierBiz;
import com.armitage.server.iscm.purchasemanage.pricemanage.dao.ScmPurPriceEntryDAO;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurPrice2;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurPriceEntry2;
import com.armitage.server.iscm.purchasemanage.pricemanage.service.ScmPurPriceBiz;
import com.armitage.server.iscm.purchasemanage.pricemanage.service.ScmPurPriceEntryBiz;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.service.ScmPurRequireEntryBiz;
import com.armitage.server.system.model.Code;
import com.armitage.server.system.service.CodeBiz;
import com.armitage.server.user.model.Usr;
import com.armitage.server.user.service.UsrBiz;
import org.springframework.stereotype.Service;

@Service("scmPurPriceEntryBiz")
public class ScmPurPriceEntryBizImpl extends BaseBizImpl<ScmPurPriceEntry2> implements ScmPurPriceEntryBiz {

	private ScmsupplierBiz scmsupplierBiz;
	private ScmPurPriceBiz scmPurPriceBiz;
	private UsrBiz usrBiz;
	private CodeBiz codeBiz;
	private PubSysBasicInfoBiz pubSysBasicInfoBiz;
	private ScmPurRequireEntryBiz scmPurRequireEntryBiz;
	
	public void setScmsupplierBiz(ScmsupplierBiz scmsupplierBiz) {
		this.scmsupplierBiz = scmsupplierBiz;
	}

	public void setScmPurPriceBiz(ScmPurPriceBiz scmPurPriceBiz) {
		this.scmPurPriceBiz = scmPurPriceBiz;
	}
	
	public void setUsrBiz(UsrBiz usrBiz) {
		this.usrBiz = usrBiz;
	}
	
	public void setCodeBiz(CodeBiz codeBiz) {
		this.codeBiz = codeBiz;
	}
	
	public void setPubSysBasicInfoBiz(PubSysBasicInfoBiz pubSysBasicInfoBiz) {
		this.pubSysBasicInfoBiz = pubSysBasicInfoBiz;
	}
	
	public void setScmPurRequireEntryBiz(ScmPurRequireEntryBiz scmPurRequireEntryBiz) {
		this.scmPurRequireEntryBiz = scmPurRequireEntryBiz;
	}
	
	
	@Override
	protected void beforeUpdate(ScmPurPriceEntry2 oldEntity, ScmPurPriceEntry2 newEntity, Param param)
			throws AppException {
		updateRate(newEntity,param);
	}

	@Override
	protected void beforeAdd(ScmPurPriceEntry2 entity, Param param) throws AppException {
		updateRate(entity,param);
	}

	protected void updateRate(ScmPurPriceEntry2 scmPurPriceEntry, Param param) throws AppException {
        if ((BigDecimal.ZERO).compareTo(scmPurPriceEntry.getPrePrice()) < 0) {
            BigDecimal b = (scmPurPriceEntry.getPrice().subtract(scmPurPriceEntry.getPrePrice()).multiply(new BigDecimal("100"))).divide(scmPurPriceEntry.getPrePrice(), 4, RoundingMode.HALF_UP);
            scmPurPriceEntry.setRiseRate(b);
        }
        if ((BigDecimal.ZERO).compareTo(scmPurPriceEntry.getInquiryPrice1().add(scmPurPriceEntry.getInquiryPrice2()).add(scmPurPriceEntry.getInquiryPrice3())) < 0) {
            int inquiryCount = 0;
            BigDecimal inquiryPrice = BigDecimal.ZERO;
            if (scmPurPriceEntry.getInquiryPrice1().compareTo(BigDecimal.ZERO) > 0) {
                inquiryCount++;
                inquiryPrice = inquiryPrice.add(scmPurPriceEntry.getInquiryPrice1());
            }
            if (scmPurPriceEntry.getInquiryPrice2().compareTo(BigDecimal.ZERO) > 0) {
                inquiryCount++;
                inquiryPrice = inquiryPrice.add(scmPurPriceEntry.getInquiryPrice2());
            }
            if (scmPurPriceEntry.getInquiryPrice3().compareTo(BigDecimal.ZERO) > 0) {
                inquiryCount++;
                inquiryPrice = inquiryPrice.add(scmPurPriceEntry.getInquiryPrice3());
            }
            BigDecimal avg = BigDecimal.ZERO;
            if (inquiryCount > 0) {
                avg = inquiryPrice.divide(new BigDecimal(inquiryCount),4, RoundingMode.HALF_UP);
                BigDecimal b = (scmPurPriceEntry.getPrice().subtract(avg)).multiply(new BigDecimal("100")).divide(avg, 4, RoundingMode.HALF_UP);
                scmPurPriceEntry.setRiseInquiryRate(b);
            }
        }
	}

	@Override
	public List<ScmPurPriceEntry2> selectByPmId(long pmId, Param param) throws AppException {
		if(pmId > 0){
			HashMap<String, Object> map = new HashMap<>();
			map.put("pmId", pmId);
			List<ScmPurPriceEntry2> scmPurPriceEntryList = ((ScmPurPriceEntryDAO) dao).selectByPmId(map);
			if(scmPurPriceEntryList!=null && !scmPurPriceEntryList.isEmpty()){
				for(ScmPurPriceEntry2 scmPurPriceEntry:scmPurPriceEntryList){
					setConvertMap(scmPurPriceEntry,param);
				}
			}
			return scmPurPriceEntryList;
		}
		return null;
	}

	@Override
	public void deleteByPmId(long pmId, Param param) throws AppException {
		if(pmId > 0){
			List<ScmPurPriceEntry2> scmPurPriceEntryList = this.selectByPmId(pmId,param);
			for(ScmPurPriceEntry2 scmPurPriceEntry : scmPurPriceEntryList) {
				scmPurRequireEntryBiz.updatePurRequestPriceBillIdByPmId(scmPurPriceEntry, param);
			}
			this.delete(scmPurPriceEntryList, param);
		}
	}
	@Override
	public void updateRowStatusByPmId(long PmId, String status, Param param) throws AppException {
		if(PmId > 0 && StringUtils.isNotBlank(status)){
			HashMap<String, Object> map = new HashMap<>();
			map.put("pmId", PmId);
			map.put("rowStatus", status);
			((ScmPurPriceEntryDAO) dao).updateRowStatusByPmId(map);
		}
	}
	@Override
	protected void afterSelect(ScmPurPriceEntry2 entity, Param param) throws AppException {
		setConvertMap(entity,param);
	}
	
	private void setConvertMap(ScmPurPriceEntry2 entity, Param param) throws AppException {
		if(entity != null && entity.getPmId() > 0){
			//通过pmId获取到定价单数据，根据数据取到供应商1-3的ID
			ScmPurPrice2 scmPurPrice = scmPurPriceBiz.selectDirect(entity.getPmId(), param);
			if (scmPurPrice != null) {
			    if(scmPurPrice.getVendorId1()>0){
			        Scmsupplier2 scmsupplier1 = scmsupplierBiz.selectDirect(scmPurPrice.getVendorId1(), param);
			        if(scmsupplier1!=null)
			            entity.setVendorName1(scmsupplier1.getVendorName());
			    }
			    if(scmPurPrice.getVendorId2()>0){
			        Scmsupplier2 scmsupplier2 = scmsupplierBiz.selectDirect(scmPurPrice.getVendorId2(), param);
			        if(scmsupplier2!=null)
			            entity.setVendorName2(scmsupplier2.getVendorName());
			    }
			    if(scmPurPrice.getVendorId3()>0){
			        Scmsupplier2 scmsupplier3 = scmsupplierBiz.selectDirect(scmPurPrice.getVendorId3(), param);
			        if(scmsupplier3!=null)
			            entity.setVendorName3(scmsupplier3.getVendorName());
			    }
            }
			if (StringUtils.isNotBlank(entity.getChecker())){
				//审核人
				Usr usr = usrBiz.selectByCode(entity.getChecker(), param);
				if (usr != null) {
					entity.setConvertMap(ScmPurPriceEntry2.FN_CHECKER, usr);
					entity.setCheckerName(usr.getName());
				}
			}
			if(StringUtils.isNotBlank(entity.getRowStatus())){
				Code code = codeBiz.selectByCategoryAndCode("quotationStatus", entity.getRowStatus());
				if(code!=null)
					entity.setRowStatusName(code.getName());
			}
			if (entity.getTaxRate() != null){
				//税率
				PubSysBasicInfo pubSysBasicInfo = pubSysBasicInfoBiz.selectByTaxRate("TaxRate", entity.getTaxRate().toString(), null, param);
				if (pubSysBasicInfo != null) {
					entity.setTaxRateStr(pubSysBasicInfo.getFInfoNo());
					entity.setConvertMap(ScmPurPriceEntry2.FN_TAXRATESTR, pubSysBasicInfo);
				}
			}
			if (entity.getTaxRate1() != null){
				//税率1
				PubSysBasicInfo pubSysBasicInfo = pubSysBasicInfoBiz.selectByTaxRate("TaxRate", entity.getTaxRate1().toString(), null, param);
				if (pubSysBasicInfo != null) {
					entity.setTaxRateStr1(pubSysBasicInfo.getFInfoNo());
					entity.setConvertMap(ScmPurPriceEntry2.FN_TAXRATESTR1, pubSysBasicInfo);
				}
			}
			if (entity.getTaxRate2() != null){
				//税率2
				PubSysBasicInfo pubSysBasicInfo = pubSysBasicInfoBiz.selectByTaxRate("TaxRate", entity.getTaxRate2().toString(), null, param);
				if (pubSysBasicInfo != null) {
					entity.setTaxRateStr2(pubSysBasicInfo.getFInfoNo());
					entity.setConvertMap(ScmPurPriceEntry2.FN_TAXRATESTR2, pubSysBasicInfo);
				}
			}
			if (entity.getTaxRate3() != null){
				//税率3
				PubSysBasicInfo pubSysBasicInfo = pubSysBasicInfoBiz.selectByTaxRate("TaxRate", entity.getTaxRate3().toString(), null, param);
				if (pubSysBasicInfo != null) {
					entity.setTaxRateStr3(pubSysBasicInfo.getFInfoNo());
					entity.setConvertMap(ScmPurPriceEntry2.FN_TAXRATESTR3, pubSysBasicInfo);
				}
			}
			if (entity.getSelVndrId() > 0) {
				//选定供应商
				Scmsupplier2 scmsupplier = scmsupplierBiz.selectDirect(entity.getSelVndrId(), param);
				if(scmsupplier != null){
					entity.setConvertMap(ScmPurPriceEntry2.FN_SELVNDRIDNAME, scmsupplier);
					entity.setSelVndrIdName(scmsupplier.getVendorName());
				}
			}
		}
	}

	@Override
	public void updateVendorQuotation(ScmPurPriceEntry2 scmPurPriceEntry, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("price", scmPurPriceEntry.getPrice());
		map.put("taxRate", scmPurPriceEntry.getTaxRate());
		map.put("selVndrId", scmPurPriceEntry.getSelVndrId());
		map.put("price1", scmPurPriceEntry.getPrice1());
		map.put("price2", scmPurPriceEntry.getPrice2());
		map.put("price3", scmPurPriceEntry.getPrice3());
		map.put("taxRate1", scmPurPriceEntry.getTaxRate1());
		map.put("taxRate2", scmPurPriceEntry.getTaxRate2());
		map.put("taxRate3", scmPurPriceEntry.getTaxRate3());
		map.put("entryId", scmPurPriceEntry.getId());
		((ScmPurPriceEntryDAO)dao).updateVendorQuotation(map);
	}

	@Override
	public ScmPurPriceEntry2 selectTaxRateByPmId(long pmId, long itemId, Param param) throws AppException {
		if (pmId>0) {
			HashMap<String,Object> hashMap = new HashMap<String, Object>();
			hashMap.put("pmid", pmId);
			hashMap.put("itemId", itemId);
			ScmPurPriceEntry2 selectTaxRateByPmId = ((ScmPurPriceEntryDAO)dao).selectTaxRateByPmId(hashMap);
			if (selectTaxRateByPmId != null) {
				PubSysBasicInfo pubSysBasicInfo = pubSysBasicInfoBiz.selectByTaxRate("TaxRate", selectTaxRateByPmId.getTaxRate().toString(), null, param);
				if (pubSysBasicInfo != null) {
					selectTaxRateByPmId.setTaxRateStr(pubSysBasicInfo.getFInfoNo());
				}
			}
			return selectTaxRateByPmId;
		}
		return null;
	}
}
