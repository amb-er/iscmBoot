
package com.armitage.server.iscm.basedata.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.iscm.basedata.dao.ScmMaterialUnitRelationDAO;
import com.armitage.server.iscm.basedata.model.ScmMaterial2;
import com.armitage.server.iscm.basedata.model.ScmMaterialUnitRelation2;
import com.armitage.server.iscm.basedata.model.ScmMeasureUnit;
import com.armitage.server.iscm.basedata.service.ScmMaterialBiz;
import com.armitage.server.iscm.basedata.service.ScmMaterialUnitRelationBiz;
import com.armitage.server.iscm.basedata.service.ScmMeasureUnitBiz;
import org.springframework.stereotype.Service;

@Service("scmMaterialUnitRelationBiz")
public class ScmMaterialUnitRelationBizImpl extends BaseBizImpl<ScmMaterialUnitRelation2> implements ScmMaterialUnitRelationBiz {
	
	private ScmMeasureUnitBiz scmMeasureUnitBiz;
	private ScmMaterialBiz scmMaterialBiz;
	
	public void setScmMeasureUnitBiz(ScmMeasureUnitBiz scmMeasureUnitBiz) {
		this.scmMeasureUnitBiz = scmMeasureUnitBiz;
	}

	public void setScmMaterialBiz(ScmMaterialBiz scmMaterialBiz) {
		this.scmMaterialBiz = scmMaterialBiz;
	}

	@Override
	public List<ScmMaterialUnitRelation2> selectByItemOrUnit(long itemId, long unitId, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("itemId", itemId);
		map.put("unitId", unitId);
		return ((ScmMaterialUnitRelationDAO)dao).selectByItemOrUnit(map);
	}
	
	@Override
	public ScmMaterialUnitRelation2 selectByItemAndUnit(long itemId, long unitId, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("itemId", itemId);
		map.put("unitId", unitId);
		return ((ScmMaterialUnitRelationDAO)dao).selectByItemAndUnit(map);
	}

	@Override
	public List<ScmMaterialUnitRelation2> selectUnitRelation(ScmMaterial2 scmMaterial, Param param)
			throws AppException {
		if(scmMaterial != null){
			List<ScmMaterialUnitRelation2> list = this.selectByItemOrUnit(scmMaterial.getId(), 0, param);
			if(list != null && !list.isEmpty()){
				for(ScmMaterialUnitRelation2 scmMaterialUnitRelation : list){
					if(scmMaterialUnitRelation.getTargetUnitId() > 0){
						ScmMeasureUnit scmMeasureUnit = scmMeasureUnitBiz.selectDirect(scmMaterialUnitRelation.getTargetUnitId(), param);
						if(scmMeasureUnit != null){
							scmMaterialUnitRelation.setConvertMap(ScmMaterialUnitRelation2.FN_TARGETUNITID, scmMeasureUnit);
						}
					}
				}
				return list;
			}
		}
		return null;
	}

	@Override
	public List<ScmMaterialUnitRelation2> saveUnitRelation(List<ScmMaterialUnitRelation2> scmMaterialUnitRelationList,
			Param param) throws AppException {
		if(scmMaterialUnitRelationList != null && !scmMaterialUnitRelationList.isEmpty()){
			List<ScmMaterialUnitRelation2> list = new ArrayList<>();
			for(ScmMaterialUnitRelation2 scmMaterialUnitRelation : scmMaterialUnitRelationList){
				if(scmMaterialUnitRelation.getId() > 0){
					this.update(scmMaterialUnitRelation, param);
				}else{
					this.add(scmMaterialUnitRelation, param);
				}
				list.add(scmMaterialUnitRelation);
			}
			return list;
		}
		return null;
	}
	
	@Override
	protected void afterSelect(ScmMaterialUnitRelation2 entity, Param param) throws AppException {
		if(entity != null){
			if(entity.getTargetUnitId() > 0){
				ScmMeasureUnit scmMeasureUnit = scmMeasureUnitBiz.selectDirect(entity.getTargetUnitId(), param);
				if(scmMeasureUnit != null){
					entity.setConvertMap(ScmMaterialUnitRelation2.FN_TARGETUNITID, scmMeasureUnit);
				}
			}
		}
	}

	@Override
	protected void beforeUpdate(ScmMaterialUnitRelation2 oldEntity, ScmMaterialUnitRelation2 newEntity, Param param)throws AppException {
		//检查是否被使用过
		boolean checkAllUse = scmMaterialBiz.checkAllUse(oldEntity.getItemId(), param);
		if (checkAllUse) {
			if(oldEntity.getTargetUnitId() != newEntity.getTargetUnitId() || !StringUtils.equals(oldEntity.getMeasureUnitType(), newEntity.getMeasureUnitType())
					|| oldEntity.getConvrate().compareTo(newEntity.getConvrate())!=0 || oldEntity.getQtyPrecision() != newEntity.getQtyPrecision()) {
				List<ScmMaterial2> scmmaterialList = scmMaterialBiz.checkUnitRelation(oldEntity.getItemId(),oldEntity.getTargetUnitId(), param);
				if(scmmaterialList != null && !scmmaterialList.isEmpty()) {
					throw new AppException(Message.getMessage(param.getLang(),
	    					"iscm.basedata.ScmMaterialUnitRelationBizImpl.checkUnitRelation", new String[] {scmmaterialList.get(0).getUnitName()}));
				}
			}
		}
	}

	@Override
	public ScmMaterialUnitRelation2 selectBaseUnitByItem(long itemId, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("itemId", itemId);
		return ((ScmMaterialUnitRelationDAO)dao).selectBaseUnitByItem(map);
	}

}
