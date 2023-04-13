
package com.armitage.server.iscm.inventorymanage.inventorysetting.service.impl;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.basedata.model.ScmMaterial2;
import com.armitage.server.iscm.basedata.model.ScmMeasureUnit;
import com.armitage.server.iscm.basedata.service.ScmMeasureUnitBiz;
import com.armitage.server.iscm.inventorymanage.inventorysetting.dao.ScmInvSupplyRuleEntryDAO;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvSupplyRuleEntry;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvSupplyRuleEntry2;
import com.armitage.server.iscm.inventorymanage.inventorysetting.service.ScmInvSupplyRuleEntryBiz;
import org.springframework.stereotype.Service;

@Service("scmInvSupplyRuleEntryBiz")
public class ScmInvSupplyRuleEntryBizImpl extends BaseBizImpl<ScmInvSupplyRuleEntry> implements ScmInvSupplyRuleEntryBiz {

	private ScmMeasureUnitBiz scmMeasureUnitBiz;
	
	public ScmMeasureUnitBiz getScmMeasureUnitBiz() {
		return scmMeasureUnitBiz;
	}


	public void setScmMeasureUnitBiz(ScmMeasureUnitBiz scmMeasureUnitBiz) {
		this.scmMeasureUnitBiz = scmMeasureUnitBiz;
	}


	@Override
	public List<ScmInvSupplyRuleEntry2> selectByRuleId(long id, Param param) throws AppException {
		HashMap map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("controlUnitNo",param.getControlUnitNo());
		map.put("orgUnitNo",param.getOrgUnitNo());
		List<ScmInvSupplyRuleEntry2> scmInvSupplyRuleEntry2s = ((ScmInvSupplyRuleEntryDAO) dao).selectByRuleId(map);
		if(scmInvSupplyRuleEntry2s!=null && !scmInvSupplyRuleEntry2s.isEmpty()) {
			for(ScmInvSupplyRuleEntry2 scmInvSupplyRuleEntry2: scmInvSupplyRuleEntry2s) {
				setConvertMap(scmInvSupplyRuleEntry2, param);
			}
		}
		return scmInvSupplyRuleEntry2s;
	}

	
	private void setConvertMap(ScmInvSupplyRuleEntry2 scmInvSupplyRuleEntry2,Param param) {
		if (scmInvSupplyRuleEntry2.getPurUnitId()>0){
			ScmMeasureUnit scmMeasureUnit = scmMeasureUnitBiz.selectDirect(scmInvSupplyRuleEntry2.getPurUnitId(), param);
			if (scmMeasureUnit!=null){
				scmInvSupplyRuleEntry2.setPurUnitName(scmMeasureUnit.getUnitName());
				scmInvSupplyRuleEntry2.setConvertMap(ScmMaterial2.FN_PURUNITID, scmMeasureUnit);
			}
		}
		if (scmInvSupplyRuleEntry2.getPieUnitId()>0){
			ScmMeasureUnit scmMeasureUnit = scmMeasureUnitBiz.selectDirect(scmInvSupplyRuleEntry2.getPieUnitId(), param);
			if (scmMeasureUnit!=null){
				scmInvSupplyRuleEntry2.setPieUnitName(scmMeasureUnit.getUnitName());
				scmInvSupplyRuleEntry2.setConvertMap(ScmMaterial2.FN_PIEUNITNAME, scmMeasureUnit);
			}
		}
	}
	
	@Override
	public void deleteByRuleIds(Long ruleId, Param param) throws AppException {
		((ScmInvSupplyRuleEntryDAO) dao).deleteByRuleIds(ruleId,param);
	}

	@Override
	public List<Long> selectEntryIdsByRuleId(long ruleId, Param createParam) throws AppException {
		return ((ScmInvSupplyRuleEntryDAO) dao).selectEntryIdsByRuleId(ruleId,createParam);
	}

}
