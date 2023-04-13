package com.armitage.server.iscm.purchasemanage.pricemanage.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.basedata.model.PubSysBasicInfo;
import com.armitage.server.iscm.basedata.service.PubSysBasicInfoBiz;
import com.armitage.server.iscm.purchasemanage.pricemanage.dao.ScmPurQuotationEntryDAO;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurQuotationEntry2;
import com.armitage.server.iscm.purchasemanage.pricemanage.service.ScmPurQuotationEntryBiz;
import org.springframework.stereotype.Service;

@Service("scmPurQuotationEntryBiz")
public class ScmPurQuotationEntryBizImpl extends BaseBizImpl<ScmPurQuotationEntry2> implements ScmPurQuotationEntryBiz {

	private PubSysBasicInfoBiz pubSysBasicInfoBiz;
	
	public void setPubSysBasicInfoBiz(PubSysBasicInfoBiz pubSysBasicInfoBiz) {
		this.pubSysBasicInfoBiz = pubSysBasicInfoBiz;
	}

	@Override
	public List<ScmPurQuotationEntry2> selectByPqId(long pqId, Param param) throws AppException {
		if(pqId > 0){
			HashMap<String, Object> map = new HashMap<>();
			map.put("pqId", pqId);
			List<ScmPurQuotationEntry2> scmPurQuotationEntryList = ((ScmPurQuotationEntryDAO) dao).selectByPqId(map);
			if(scmPurQuotationEntryList!=null && !scmPurQuotationEntryList.isEmpty()){
				for (ScmPurQuotationEntry2 scmPurQuotationEntry : scmPurQuotationEntryList) {
					setConvertMap(scmPurQuotationEntry, param);
				}
			}
			return scmPurQuotationEntryList;
		}
		return null;
	}

	@Override
	public void deleteByPqId(long pqId, Param param) throws AppException {
		if(pqId > 0){
			List<ScmPurQuotationEntry2> scmPurQuotationEntryList = this.selectByPqId(pqId,param);
			this.delete(scmPurQuotationEntryList, param);
		}
	}

	@Override
	public void updateRowStatusByPqId(long pqId, String status, String checker, Date checkDate, Param param) throws AppException {
		if(pqId > 0 && StringUtils.isNotBlank(status)){
			HashMap<String, Object> map = new HashMap<>();
			map.put("pqId", pqId);
			map.put("rowStatus", status);
			map.put("checker", checker);
			map.put("checkDate", checkDate);
			((ScmPurQuotationEntryDAO) dao).updateRowStatusByPqId(map);
		}
	}

	@Override
	protected void afterSelect(ScmPurQuotationEntry2 entity, Param param) throws AppException {
		if(entity != null){
			if (entity.getTaxRate() != null){
				//税率
				PubSysBasicInfo pubSysBasicInfo = pubSysBasicInfoBiz.selectByTaxRate("TaxRate", entity.getTaxRate().toString(), null, param);
				if (pubSysBasicInfo != null) {
					entity.setTaxRateStr(pubSysBasicInfo.getFInfoNo());
					entity.setConvertMap(ScmPurQuotationEntry2.FN_TAXRATESTR, pubSysBasicInfo);
				}
			}
		}
	}
	
	private void setConvertMap(ScmPurQuotationEntry2 entity, Param param) throws AppException {
		//税率
		PubSysBasicInfo pubSysBasicInfo = pubSysBasicInfoBiz.selectByTaxRate("TaxRate", entity.getTaxRate().toString(), null, param);
		if (pubSysBasicInfo != null) {
			entity.setTaxRateStr(pubSysBasicInfo.getFInfoNo());
			entity.setConvertMap(ScmPurQuotationEntry2.FN_TAXRATESTR, pubSysBasicInfo);
		}
	}

	@Override
	public ScmPurQuotationEntry2 selectTaxRateByPqId(long pqId, long itemId, Param param) throws AppException {
		if (pqId>0) {
			HashMap<String,Object> hashMap = new HashMap<String, Object>();
			hashMap.put("pqId", pqId);
			hashMap.put("itemId", itemId);
			ScmPurQuotationEntry2 selectTaxRateByPqId = ((ScmPurQuotationEntryDAO) dao).selectTaxRateByPqId(hashMap);
			if (selectTaxRateByPqId != null) {
				PubSysBasicInfo pubSysBasicInfo = pubSysBasicInfoBiz.selectByTaxRate("TaxRate", selectTaxRateByPqId.getTaxRate().toString(), null, param);
				if (pubSysBasicInfo != null) {
					selectTaxRateByPqId.setTaxRateStr(pubSysBasicInfo.getFInfoNo());
				}
			}
			return selectTaxRateByPqId;
		}
		return null;
	}
}
