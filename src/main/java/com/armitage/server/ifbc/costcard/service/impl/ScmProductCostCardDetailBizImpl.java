
package com.armitage.server.ifbc.costcard.service.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.util.CalendarUtil;
import com.armitage.server.ifbc.costcard.dao.ScmProductCostCardDetailDAO;
import com.armitage.server.ifbc.costcard.model.ScmCostCardDetail2;
import com.armitage.server.ifbc.costcard.model.ScmProductCostCard;
import com.armitage.server.ifbc.costcard.model.ScmProductCostCard2;
import com.armitage.server.ifbc.costcard.model.ScmProductCostCardDetail;
import com.armitage.server.ifbc.costcard.model.ScmProductCostCardDetail2;
import com.armitage.server.ifbc.costcard.model.ScmProductCostCardDetailHistory;
import com.armitage.server.ifbc.costcard.service.ScmProductCostCardBiz;
import com.armitage.server.ifbc.costcard.service.ScmProductCostCardDetailBiz;
import com.armitage.server.ifbc.costcard.service.ScmProductCostCardDetailHistoryBiz;
import com.armitage.server.iscm.basedata.model.ScmMaterial;
import com.armitage.server.iscm.basedata.model.ScmMaterial2;
import com.armitage.server.iscm.basedata.model.ScmMaterialUnitRelation2;
import com.armitage.server.iscm.basedata.model.ScmMeasureUnit;
import com.armitage.server.iscm.basedata.service.ScmMaterialBiz;
import com.armitage.server.iscm.basedata.service.ScmMeasureUnitBiz;
import com.armitage.server.user.model.Usr;
import com.armitage.server.user.service.UsrBiz;
import org.springframework.stereotype.Service;

@Service("scmProductCostCardDetailBiz")
public class ScmProductCostCardDetailBizImpl extends BaseBizImpl<ScmProductCostCardDetail2> implements ScmProductCostCardDetailBiz {

	private ScmMaterialBiz scmMaterialBiz;
	private ScmMeasureUnitBiz scmMeasureUnitBiz;
	private UsrBiz usrBiz;
	private ScmProductCostCardDetailHistoryBiz scmProductCostCardDetailHistoryBiz;
	private ScmProductCostCardBiz scmProductCostCardBiz;
	
	public UsrBiz getUsrBiz() {
		return usrBiz;
	}

	public void setUsrBiz(UsrBiz usrBiz) {
		this.usrBiz = usrBiz;
	}

	public ScmMeasureUnitBiz getScmMeasureUnitBiz() {
		return scmMeasureUnitBiz;
	}

	public void setScmMeasureUnitBiz(ScmMeasureUnitBiz scmMeasureUnitBiz) {
		this.scmMeasureUnitBiz = scmMeasureUnitBiz;
	}

	public ScmMaterialBiz getScmMaterialBiz() {
		return scmMaterialBiz;
	}

	public void setScmMaterialBiz(ScmMaterialBiz scmMaterialBiz) {
		this.scmMaterialBiz = scmMaterialBiz;
	}

	public void setScmProductCostCardDetailHistoryBiz(
			ScmProductCostCardDetailHistoryBiz scmProductCostCardDetailHistoryBiz) {
		this.scmProductCostCardDetailHistoryBiz = scmProductCostCardDetailHistoryBiz;
	}

	public void setScmProductCostCardBiz(ScmProductCostCardBiz scmProductCostCardBiz) {
		this.scmProductCostCardBiz = scmProductCostCardBiz;
	}

	@Override
	public List<ScmProductCostCardDetail2> selectByCardId(long id,Param param) {
		List<ScmProductCostCardDetail2> scmProductCostCardDetail2s = ((ScmProductCostCardDetailDAO)dao).selectByCardId(id);
		for(ScmProductCostCardDetail2 scmProductCostCardDetail:scmProductCostCardDetail2s) {
			setConvertMap(scmProductCostCardDetail,param);
		}
		return scmProductCostCardDetail2s;
	}

	private void setConvertMap(ScmProductCostCardDetail2 scmProductCostCardDetail, Param param) {
		if (scmProductCostCardDetail.getItemId() > 0){
			ScmMaterial scmMaterial = scmMaterialBiz.selectDirect(scmProductCostCardDetail.getItemId(), param);
			if (scmMaterial!=null){
				scmProductCostCardDetail.setConvertMap(ScmProductCostCardDetail2.FN_ITEMNO, scmMaterial);
			}
		}
		if (scmProductCostCardDetail.getCstUnit() > 0){
			ScmMeasureUnit scmMeasureUnit = scmMeasureUnitBiz.selectDirect(scmProductCostCardDetail.getCstUnit(), param);
			if (scmMeasureUnit!=null){
				ScmMaterialUnitRelation2 scmMaterialUnitRelation = new ScmMaterialUnitRelation2(true);
				scmMaterialUnitRelation.setTargetUnitId(scmMeasureUnit.getId());
				scmMaterialUnitRelation.setUnitName(scmMeasureUnit.getUnitName());
				scmProductCostCardDetail.setConvertMap(ScmProductCostCardDetail2.FN_CSTUNIT, scmMaterialUnitRelation);
			}
		}
		if (scmProductCostCardDetail.getInvUnit() > 0){
			ScmMeasureUnit scmMeasureUnit = scmMeasureUnitBiz.selectDirect(scmProductCostCardDetail.getInvUnit(), param);
			if (scmMeasureUnit!=null){
				ScmMaterialUnitRelation2 scmMaterialUnitRelation = new ScmMaterialUnitRelation2(true);
				scmMaterialUnitRelation.setTargetUnitId(scmMeasureUnit.getId());
				scmMaterialUnitRelation.setUnitName(scmMeasureUnit.getUnitName());
				scmProductCostCardDetail.setConvertMap(ScmProductCostCardDetail2.FN_INVUNIT, scmMaterialUnitRelation);
			}
		}
		if (StringUtils.isNotBlank(scmProductCostCardDetail.getCreator())){
			//设置人
			Usr usr = usrBiz.selectByCode(scmProductCostCardDetail.getCreator(), param);
			if (usr != null) {
				scmProductCostCardDetail.setConvertMap(ScmCostCardDetail2.FN_CREATOR, usr);
			}
		}
		if (StringUtils.isNotBlank(scmProductCostCardDetail.getChecker())){
			//审核人
			Usr usr = usrBiz.selectByCode(scmProductCostCardDetail.getChecker(), param);
			if (usr != null) {
				scmProductCostCardDetail.setConvertMap(ScmCostCardDetail2.FN_CHECKER, usr);
			}
		}
	}

	@Override
	public void deleteByCardId(long id) {
		HashMap<String,Object> map = new HashMap(); 
		map.put("cardId", id);
		((ScmProductCostCardDetailDAO)dao).deleteByCardId(map);
	}
	
	@Override
	protected void afterDelete(ScmProductCostCardDetail2 entity, Param param) throws AppException {
		if (entity != null) {
			ScmProductCostCard2 scmProductCostCard2 = scmProductCostCardBiz.select(entity.getCardId(), param);
			if (scmProductCostCard2 != null) {
				// 删除历史记录中删除当天生效的该配方明细
				scmProductCostCardDetailHistoryBiz.deleteByEffectDayAndDetailItemId(scmProductCostCard2.getId(),scmProductCostCard2.getEffectiveDate(), entity.getItemId(), param);
				// 修改历史记录中结束日期大于当前日期的数据
				scmProductCostCardDetailHistoryBiz.updateByAuditCostCardAndDetailItemId(scmProductCostCard2.getId(),CalendarUtil.getDate(param), entity.getItemId(), param);
			}
		}
	}

}
