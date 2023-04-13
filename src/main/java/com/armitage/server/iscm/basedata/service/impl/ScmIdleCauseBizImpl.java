
package com.armitage.server.iscm.basedata.service.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Page;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.base.model.QueryParam;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.util.ClassUtils;
import com.armitage.server.iscm.basedata.dao.ScmIdleCauseDAO;
import com.armitage.server.iscm.basedata.model.ScmIdleCause;
import com.armitage.server.iscm.basedata.model.ScmIdleCauseRelation;
import com.armitage.server.iscm.basedata.model.ScmIdleItems2;
import com.armitage.server.iscm.basedata.service.ScmIdleCauseBiz;
import com.armitage.server.iscm.basedata.service.ScmIdleCauseRelationBiz;
import com.armitage.server.iscm.basedata.service.ScmIdleItemsBiz;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCountingCostCenterEntry2;
import com.armitage.server.iscm.inventorymanage.cstbusiness.service.ScmInvCountingCostCenterEntryBiz;
import org.springframework.stereotype.Service;

@Service("scmIdleCauseBiz")
public class ScmIdleCauseBizImpl extends BaseBizImpl<ScmIdleCause> implements ScmIdleCauseBiz {
	private ScmIdleItemsBiz scmIdleItemsBiz;
	private ScmInvCountingCostCenterEntryBiz scmInvCountingCostCenterEntryBiz;
	private ScmIdleCauseRelationBiz scmIdleCauseRelationBiz;
	
	public void setScmIdleItemsBiz(ScmIdleItemsBiz scmIdleItemsBiz) {
		this.scmIdleItemsBiz = scmIdleItemsBiz;
	}

	public void setScmInvCountingCostCenterEntryBiz(ScmInvCountingCostCenterEntryBiz scmInvCountingCostCenterEntryBiz) {
		this.scmInvCountingCostCenterEntryBiz = scmInvCountingCostCenterEntryBiz;
	}

	public void setScmIdleCauseRelationBiz(ScmIdleCauseRelationBiz scmIdleCauseRelationBiz) {
		this.scmIdleCauseRelationBiz = scmIdleCauseRelationBiz;
	}

	@Override
	public ScmIdleCause selectByCode(String object, long object2, Param createParam) throws AppException {
		if (object != null) {
			HashMap<String, Object> map = new HashMap<>();
			map.put("code", object);
			if (object2>0) {
				map.put("id", object2);
			}
			map.put("controlUnitNo", createParam.getControlUnitNo());
			return ((ScmIdleCauseDAO) dao).selectByCode(map);
		}
		return null;
	}

	@Override
	protected HashMap<String, Object> addFindAllPageParam(Page page, HashMap<String, Object> map, Param param) {
		map.put("controlUnitNo", param.getControlUnitNo());
		return map;
	}
	
	@Override
	protected void beforeDelete(ScmIdleCause entity, Param param) throws AppException {
		if (entity != null) {
			//检查是否被使用过
			List<ScmIdleItems2> scmIdleCauses = scmIdleItemsBiz.selectByIdleCauseId(entity.getId(),param);
			List<ScmInvCountingCostCenterEntry2> scmInvCountingCostCenterEntry2s = scmInvCountingCostCenterEntryBiz.selectByIdleCauseId(entity.getId(),param);
			if ((scmIdleCauses != null && !scmIdleCauses.isEmpty()) || (scmInvCountingCostCenterEntry2s != null && !scmInvCountingCostCenterEntry2s.isEmpty())) {
				throw new AppException("iscm.basedata.ScmIdleCauseBizImpl.validateDelete");
			}
		}
	}

	@Override
	protected void beforeUpdate(ScmIdleCause oldEntity, ScmIdleCause newEntity, Param param) throws AppException {
		if(!StringUtils.equals(param.getControlUnitNo(),newEntity.getControlUnitNo())) {
			long sourceId = newEntity.getId();
			newEntity.setId(0);
			newEntity.setControlUnitNo(param.getControlUnitNo());
			newEntity = this.add(newEntity, param);
			ScmIdleCauseRelation scmIdleCauseRelation = new ScmIdleCauseRelation();
			scmIdleCauseRelation.setSourceId(sourceId);
			scmIdleCauseRelation.setTargetId(newEntity.getId());
			scmIdleCauseRelationBiz.add(scmIdleCauseRelation, param);
		}
	}

}
