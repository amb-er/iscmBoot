
package com.armitage.server.iscm.basedata.service;

import java.math.BigDecimal;
import java.util.List;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.basedata.model.ScmMaterial2;
import com.armitage.server.iscm.basedata.model.ScmMaterialUnitRelation2;

public interface ScmMaterialUnitRelationBiz extends BaseBiz<ScmMaterialUnitRelation2> {
	public List<ScmMaterialUnitRelation2> selectByItemOrUnit(long itemId, long unitId, Param param) throws AppException;
	public ScmMaterialUnitRelation2 selectByItemAndUnit(long itemId, long unitId, Param param) throws AppException;
	public List<ScmMaterialUnitRelation2> selectUnitRelation(ScmMaterial2 scmMaterial, Param param) throws AppException;
	public List<ScmMaterialUnitRelation2> saveUnitRelation(List<ScmMaterialUnitRelation2> scmMaterialUnitRelationList, Param param) throws AppException;
	public ScmMaterialUnitRelation2 selectBaseUnitByItem(long itemId, Param param) throws AppException;
}
