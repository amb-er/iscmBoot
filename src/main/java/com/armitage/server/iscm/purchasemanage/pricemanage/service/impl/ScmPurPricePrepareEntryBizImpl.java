package com.armitage.server.iscm.purchasemanage.pricemanage.service.impl;


import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.basedata.model.PubSysBasicInfo;
import com.armitage.server.iscm.basedata.model.Scmsupplier2;
import com.armitage.server.iscm.basedata.service.PubSysBasicInfoBiz;
import com.armitage.server.iscm.basedata.service.ScmsupplierBiz;
import com.armitage.server.iscm.purchasemanage.pricemanage.dao.ScmPurPricePrepareEntryDAO;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurPricePrepareEntry2;
import com.armitage.server.iscm.purchasemanage.pricemanage.service.ScmPurPricePrepareEntryBiz;
import org.springframework.stereotype.Service;

@Service("scmPurPricePrepareEntryBiz")
public class ScmPurPricePrepareEntryBizImpl extends BaseBizImpl<ScmPurPricePrepareEntry2> implements ScmPurPricePrepareEntryBiz {
	
	private ScmsupplierBiz scmsupplierBiz;
	private PubSysBasicInfoBiz pubSysBasicInfoBiz;
	
	public void setScmsupplierBiz(ScmsupplierBiz scmsupplierBiz) {
		this.scmsupplierBiz = scmsupplierBiz;
	}

	public void setPubSysBasicInfoBiz(PubSysBasicInfoBiz pubSysBasicInfoBiz) {
		this.pubSysBasicInfoBiz = pubSysBasicInfoBiz;
	}

	@Override
	public List<ScmPurPricePrepareEntry2> selectByPmId(long pmId, Param param) throws AppException {
		if(pmId > 0){
			HashMap<String, Object> map = new HashMap<>();
			map.put("pmId", pmId);
			List<ScmPurPricePrepareEntry2> scmPurPricePrepareEntryList = ((ScmPurPricePrepareEntryDAO) dao).selectByPmId(map);
			if(scmPurPricePrepareEntryList!=null && !scmPurPricePrepareEntryList.isEmpty()){
				for(ScmPurPricePrepareEntry2 scmPurPricePrepareEntry:scmPurPricePrepareEntryList){
					setConvertMap(scmPurPricePrepareEntry,param);
				}
			}
			return scmPurPricePrepareEntryList;
		}
		return null;
	}

	@Override
	public void deleteByPmId(long pmId, Param param) throws AppException {
		if(pmId > 0){
			List<ScmPurPricePrepareEntry2> scmPurPricePrepareEntryList = this.selectByPmId(pmId,param);
			this.delete(scmPurPricePrepareEntryList, param);
		}
	}
	
	@Override
	protected void afterSelect(ScmPurPricePrepareEntry2 entity, Param param) throws AppException {
		setConvertMap(entity,param);
	}
	
	private void setConvertMap(ScmPurPricePrepareEntry2 entity, Param param) throws AppException {
		if(entity != null && entity.getPmId() > 0){
			if(entity.getVendorId1()>0){
		        Scmsupplier2 scmsupplier1 = scmsupplierBiz.selectDirect(entity.getVendorId1(), param);
		        if(scmsupplier1!=null){
		        	entity.setVendorName1(scmsupplier1.getVendorName());
		        	entity.setConvertMap(ScmPurPricePrepareEntry2.FN_VENDORID1, scmsupplier1);
		        }
		    }
		    if(entity.getVendorId2()>0){
		        Scmsupplier2 scmsupplier2 = scmsupplierBiz.selectDirect(entity.getVendorId2(), param);
		        if(scmsupplier2!=null){
		        	entity.setVendorName2(scmsupplier2.getVendorName());
		        	entity.setConvertMap(ScmPurPricePrepareEntry2.FN_VENDORID2, scmsupplier2);
		        }
		    }
		    if(entity.getVendorId3()>0){
		        Scmsupplier2 scmsupplier3 = scmsupplierBiz.selectDirect(entity.getVendorId3(), param);
		        if(scmsupplier3!=null){
		        	entity.setVendorName3(scmsupplier3.getVendorName());
		            entity.setConvertMap(ScmPurPricePrepareEntry2.FN_VENDORID3, scmsupplier3);
		        }
		    }
		    if(entity.getPreSelVndrId()>0){
		    	if(entity.getVendorId1()==entity.getPreSelVndrId()){
		    		entity.setPreSelVndr1(true);
		    	}else if(entity.getVendorId2()==entity.getPreSelVndrId()){
		    		entity.setPreSelVndr2(true);
		    	}else if(entity.getVendorId3()==entity.getPreSelVndrId()){
		    		entity.setPreSelVndr3(true);
		    	}
		    }
			if (entity.getPreTaxRate1() != null){
				//税率
				PubSysBasicInfo pubSysBasicInfo = pubSysBasicInfoBiz.selectByTaxRate("TaxRate", entity.getPreTaxRate1().toString(), null, param);
				if (pubSysBasicInfo != null) {
					entity.setTaxRateStr1(pubSysBasicInfo.getFInfoNo());
					entity.setConvertMap(ScmPurPricePrepareEntry2.FN_TAXRATESTR1, pubSysBasicInfo);
				}
			}
			if (entity.getPreTaxRate2() != null){
				//税率
				PubSysBasicInfo pubSysBasicInfo2 = pubSysBasicInfoBiz.selectByTaxRate("TaxRate", entity.getPreTaxRate2().toString(), null, param);
				if (pubSysBasicInfo2 != null) {
					entity.setTaxRateStr2(pubSysBasicInfo2.getFInfoNo());
					entity.setConvertMap(ScmPurPricePrepareEntry2.FN_TAXRATESTR2, pubSysBasicInfo2);
				}
			}
			if (entity.getPreTaxRate3() != null){
				//税率
				PubSysBasicInfo pubSysBasicInfo3 = pubSysBasicInfoBiz.selectByTaxRate("TaxRate", entity.getPreTaxRate3().toString(), null, param);
				if (pubSysBasicInfo3 != null) {
					entity.setTaxRateStr3(pubSysBasicInfo3.getFInfoNo());
					entity.setConvertMap(ScmPurPricePrepareEntry2.FN_TAXRATESTR3, pubSysBasicInfo3);
				}
			}
		}
	}
}

