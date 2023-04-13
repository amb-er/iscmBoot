
package com.armitage.server.iscm.inventorymanage.inventorysetting.service.impl;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.basedata.model.ScmMaterial2;
import com.armitage.server.iscm.basedata.model.ScmMeasureUnit;
import com.armitage.server.iscm.basedata.service.ScmMaterialBiz;
import com.armitage.server.iscm.basedata.service.ScmMeasureUnitBiz;
import com.armitage.server.iscm.inventorymanage.inventorysetting.dao.ScmInvSupplyPlanEntryDAO;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvSupplyPlanEntry;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvSupplyPlanEntry2;
import com.armitage.server.iscm.inventorymanage.inventorysetting.service.ScmInvSupplyPlanEntryBiz;
import org.springframework.stereotype.Service;

@Service("scmInvSupplyPlanEntryBiz")
public class ScmInvSupplyPlanEntryBizImpl extends BaseBizImpl<ScmInvSupplyPlanEntry> implements ScmInvSupplyPlanEntryBiz {

	ScmMaterialBiz scmMaterialBiz;
	ScmMeasureUnitBiz scmMeasureUnitBiz;
	
	public ScmMaterialBiz getScmMaterialBiz() {
		return scmMaterialBiz;
	}

	public void setScmMaterialBiz(ScmMaterialBiz scmMaterialBiz) {
		this.scmMaterialBiz = scmMaterialBiz;
	}

	public ScmMeasureUnitBiz getScmMeasureUnitBiz() {
		return scmMeasureUnitBiz;
	}

	public void setScmMeasureUnitBiz(ScmMeasureUnitBiz scmMeasureUnitBiz) {
		this.scmMeasureUnitBiz = scmMeasureUnitBiz;
	}

	@Override
	public List<ScmInvSupplyPlanEntry2> selectByPlanId(long id, Param param) throws AppException {
		HashMap map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("controlUnitNo",param.getControlUnitNo());
		map.put("orgUnitNo",param.getOrgUnitNo());
		List<ScmInvSupplyPlanEntry2> scmInvSupplyPlanEntry2s = ((ScmInvSupplyPlanEntryDAO) dao).selectByPlanId(map);
		for(ScmInvSupplyPlanEntry2 scmInvSupplyPlanEntry2: scmInvSupplyPlanEntry2s) {
			setConvertMap(scmInvSupplyPlanEntry2, param);
		}
		return scmInvSupplyPlanEntry2s;
	}
	
	private void setConvertMap(ScmInvSupplyPlanEntry2 scmInvSupplyPlanEntry2,Param param) {
		if (scmInvSupplyPlanEntry2.getPurUnitId()>0){
			ScmMeasureUnit scmMeasureUnit = scmMeasureUnitBiz.selectDirect(scmInvSupplyPlanEntry2.getPurUnitId(), param);
			if (scmMeasureUnit!=null){
				scmInvSupplyPlanEntry2.setPurUnitName(scmMeasureUnit.getUnitName());
				scmInvSupplyPlanEntry2.setConvertMap(ScmMaterial2.FN_PURUNITID, scmMeasureUnit);
			}
		}
	}
}
