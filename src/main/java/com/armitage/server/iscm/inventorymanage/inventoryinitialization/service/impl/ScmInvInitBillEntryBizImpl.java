package com.armitage.server.iscm.inventorymanage.inventoryinitialization.service.impl;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.basedata.model.PubSysBasicInfo;
import com.armitage.server.iscm.basedata.model.ScmMeasureUnit;
import com.armitage.server.iscm.basedata.service.PubSysBasicInfoBiz;
import com.armitage.server.iscm.basedata.service.ScmMeasureUnitBiz;
import com.armitage.server.iscm.inventorymanage.inventoryinitialization.dao.ScmInvInitBillEntryDAO;
import com.armitage.server.iscm.inventorymanage.inventoryinitialization.model.ScmInvInitBillEntry2;
import com.armitage.server.iscm.inventorymanage.inventoryinitialization.service.ScmInvInitBillBiz;
import com.armitage.server.iscm.inventorymanage.inventoryinitialization.service.ScmInvInitBillEntryBiz;
import org.springframework.stereotype.Service;

@Service("scmInvInitBillEntryBiz")
public class ScmInvInitBillEntryBizImpl extends BaseBizImpl<ScmInvInitBillEntry2> implements ScmInvInitBillEntryBiz {
	private ScmInvInitBillBiz scmInvInitBillBiz;
	private PubSysBasicInfoBiz pubSysBasicInfoBiz;
	private ScmMeasureUnitBiz scmMeasureUnitBiz;
	
	public void setPubSysBasicInfoBiz(PubSysBasicInfoBiz pubSysBasicInfoBiz) {
		this.pubSysBasicInfoBiz = pubSysBasicInfoBiz;
	}

	public ScmInvInitBillBiz getScmInvInitBillBiz() {
		return scmInvInitBillBiz;
	}

	public void setScmInvInitBillBiz(ScmInvInitBillBiz scmInvInitBillBiz) {
		this.scmInvInitBillBiz = scmInvInitBillBiz;
	}
	
	public void setScmMeasureUnitBiz(ScmMeasureUnitBiz scmMeasureUnitBiz) {
		this.scmMeasureUnitBiz = scmMeasureUnitBiz;
	}

	@Override
	protected void afterSelect(ScmInvInitBillEntry2 entity, Param param) throws AppException {
		setConvertMap(entity,param);
	}
	
	@Override
	public List<ScmInvInitBillEntry2> selectByInitId(long initId, Param param)
			throws AppException {
		if(initId > 0){
			HashMap<String, Object> map = new HashMap<>();
			map.put("initId", initId);
			List<ScmInvInitBillEntry2>  scmInvInitBillEntryList = ((ScmInvInitBillEntryDAO) dao).selectByInitId(map);
			if(scmInvInitBillEntryList!=null && !scmInvInitBillEntryList.isEmpty()) {
				for(ScmInvInitBillEntry2 scmInvInitBillEntry:scmInvInitBillEntryList) {
					setConvertMap(scmInvInitBillEntry,param);
				}
			}
			return scmInvInitBillEntryList;
		}
		return null;
	}

	@Override
	public void deleteByInitId(long initId, Param param) throws AppException {
		if (initId > 0) {
			HashMap<String, Object> map = new HashMap<>();
			map.put("initId", initId);
			((ScmInvInitBillEntryDAO) dao).deleteByInitId(map);
		}
	}
	
	private void setConvertMap(ScmInvInitBillEntry2 scmInvInitBillEntry,Param param) throws AppException {
		if(scmInvInitBillEntry != null){
			if (scmInvInitBillEntry.getTaxRate() != null){
				//税率
				PubSysBasicInfo pubSysBasicInfo = pubSysBasicInfoBiz.selectByTaxRate("TaxRate", scmInvInitBillEntry.getTaxRate().toString(), null, param);
				if (pubSysBasicInfo != null) {
					scmInvInitBillEntry.setTaxRateStr(pubSysBasicInfo.getFInfoNo());
					scmInvInitBillEntry.setConvertMap(ScmInvInitBillEntry2.FN_TAXRATESTR, pubSysBasicInfo);
				}
			}
			if(scmInvInitBillEntry.getPieUnit()>0) {
				ScmMeasureUnit scmMeasureUnit = scmMeasureUnitBiz.selectDirect(scmInvInitBillEntry.getPieUnit(), param);
				if(scmMeasureUnit!=null)
					scmInvInitBillEntry.setPieUnitName(scmMeasureUnit.getUnitName());
			}
		}
	}

	@Override
	public List<ScmInvInitBillEntry2> selectCancelEffectRow(long initId, Param param) throws AppException {
		HashMap<String, Object> map = new HashMap<>();
		map.put("initId", initId);
		return ((ScmInvInitBillEntryDAO) dao).selectCancelEffectRow(map);
	}
}
