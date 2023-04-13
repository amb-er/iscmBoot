
package com.armitage.server.iscm.basedata.service;

import java.math.BigDecimal;
import java.util.List;

import com.armitage.server.api.business.basedata.params.InvOrgMaterialListParams;
import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.CommonBean;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.basedata.model.ScmMaterial2;
import com.armitage.server.iscm.basedata.model.ScmMaterialAdd;
import com.armitage.server.iscm.basedata.model.ScmMaterialAdvQuery;
import com.armitage.server.iscm.basedata.model.ScmMaterialUnitRelation2;
import com.armitage.server.iscm.common.model.ScmDataCollectionStepState2;

public interface ScmMaterialBiz extends BaseBiz<ScmMaterial2> {
	public List<ScmMaterialUnitRelation2> selectItemUnit(ScmMaterial2 scmMaterial, Param param) throws AppException;
	public BaseModel updateStatus(BaseModel baseModel, Param param) throws AppException;
	public ScmMaterial2 selectByItemId(long itemId, Param param) throws AppException;
	public List<ScmMaterial2> selectByOrgUnitNo(String orgUnitNo, String fromItemNo, String toItemNo, Param param) throws AppException;
	public List<ScmMaterial2> findCountingMaterial(String orgUnitNo, String depts,String fromItemNo, String toItemNo, String type, Param param) throws AppException;
	public ScmMaterial2 selectByItemNo(String controlUnitNo,String itemNo, Param param) throws AppException;
	public List<ScmMaterial2> selectByCostOrgUnitNo(String orgUnitNo, String fromItemNo, String toItemNo, Param param) throws AppException;
	public boolean checkUse(long itemId, Param param) throws AppException;
	public boolean checkAllUse(long itemId, Param param) throws AppException;
	public ScmMaterial2 findByInvItemId(String controlUnitNo,String orgUnitNo,long itemId, Param param) throws AppException;
	public ScmMaterial2 findByPurItemId(String controlUnitNo,String orgUnitNo,long itemId, Param param) throws AppException;
	public List<ScmMaterial2> queryMaterialList(ScmMaterialAdvQuery scmMaterialAdvQuery, int pageNum,Param param) throws AppException;
	public ScmMaterial2 selectByStock(String orgUnitNo,long wareHouseId,long itemId, Param param) throws AppException;
	public ScmMaterial2 findSameNameMaterial(ScmMaterial2 scmMaterial, Param param) throws AppException;
	public BigDecimal getConvrate(long itemId, long unitId, long baseUnitId, Param param) throws AppException;
	public List<ScmMaterial2> findByFinAllList(String controlUnitNo,int groupLevel, Param param) throws AppException;
	public ScmMaterial2 approval(ScmMaterial2 scmMaterial, Param param) throws AppException;
	public ScmMaterial2 findByFinItemId(String controlUnitNo,String orgUnitNo,long itemId, Param param) throws AppException;
	public CommonBean importScmMaterial(ScmDataCollectionStepState2 stepState,List<ScmMaterialAdd> list,List<String> msgInfoList, Param param) throws AppException;
	public CommonBean batchRatioSet(List<ScmMaterial2> scmMaterial2, Param param) throws AppException;
	public List<String> disable(BaseModel baseModel,List<String> orgUnitList, Param param) throws AppException;
	public List<String> enable(BaseModel baseModel,List<String> orgUnitList, Param param) throws AppException;
	public List<ScmMaterial2> findByPurItemIds(String controlUnitNo, String purOrgUnitNo, String itemids,Param param) throws AppException;
	public List<ScmMaterial2> findByInvItemIds(String controlUnitNo, String purOrgUnitNo, String string, Param param) throws AppException;
	public List<ScmMaterial2> queryInvOrgMaterialList(InvOrgMaterialListParams invOrgMaterialListParams, int pageIndex,Param createParam) throws AppException;
	public List<ScmMaterial2> checkUnitRelation(long itemId, long targetUnitId, Param param) throws AppException;
}
