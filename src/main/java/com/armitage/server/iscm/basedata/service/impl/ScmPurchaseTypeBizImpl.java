
package com.armitage.server.iscm.basedata.service.impl;

import java.util.HashMap;

import org.apache.commons.lang.StringUtils;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Page;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.cache.ModelCacheMana;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.basedata.dao.ScmPurchaseTypeDAO;
import com.armitage.server.iscm.basedata.model.ScmPurchaseType2;
import com.armitage.server.iscm.basedata.model.ScmPurchaseTypeOrg;
import com.armitage.server.iscm.basedata.service.ScmPurchaseTypeBiz;
import com.armitage.server.iscm.basedata.service.ScmPurchaseTypeOrgBiz;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurOrder2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurOrderEntry2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurRequire2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurRequireEntry2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.service.ScmPurOrderBiz;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.service.ScmPurOrderEntryBiz;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.service.ScmPurRequireBiz;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.service.ScmPurRequireEntryBiz;
import com.armitage.server.user.model.Usr2;
import org.springframework.stereotype.Service;

@Service("scmPurchaseTypeBiz")
public class ScmPurchaseTypeBizImpl extends BaseBizImpl<ScmPurchaseType2> implements ScmPurchaseTypeBiz {
	private ScmPurchaseTypeOrgBiz scmPurchaseTypeOrgBiz;
	private ScmPurRequireBiz scmPurRequireBiz;
	private ScmPurOrderBiz scmPurOrderBiz;

	public void setScmPurchaseTypeOrgBiz(ScmPurchaseTypeOrgBiz scmPurchaseTypeOrgBiz) {
		this.scmPurchaseTypeOrgBiz = scmPurchaseTypeOrgBiz;
	}

	public void setScmPurRequireBiz(ScmPurRequireBiz scmPurRequireBiz) {
		this.scmPurRequireBiz = scmPurRequireBiz;
	}

	public void setScmPurOrderBiz(ScmPurOrderBiz scmPurOrderBiz) {
		this.scmPurOrderBiz = scmPurOrderBiz;
	}

	@Override
	protected void afterAdd(ScmPurchaseType2 entity, Param param) throws AppException {
		ScmPurchaseTypeOrg scmPurchaseTypeOrg = new ScmPurchaseTypeOrg(true);
		scmPurchaseTypeOrg.setTypeId(entity.getId());
		scmPurchaseTypeOrg.setOrgUnitNo(param.getControlUnitNo());
		scmPurchaseTypeOrg.setFlag(entity.isFlag());
		scmPurchaseTypeOrg.setSort(entity.getSort());
		scmPurchaseTypeOrgBiz.add(scmPurchaseTypeOrg, param);
		if(!StringUtils.equals(param.getOrgUnitNo(),param.getControlUnitNo())){
			ScmPurchaseTypeOrg scmPurchaseTypeOrg2 = new ScmPurchaseTypeOrg(true);
			scmPurchaseTypeOrg2.setTypeId(entity.getId());
			scmPurchaseTypeOrg2.setOrgUnitNo(param.getOrgUnitNo());
			scmPurchaseTypeOrg2.setFlag(entity.isFlag());
			scmPurchaseTypeOrg2.setSort(entity.getSort());
			scmPurchaseTypeOrgBiz.add(scmPurchaseTypeOrg2, param);
		}
	}

	@Override
	protected HashMap<String, Object> addFindAllPageParam(Page page, HashMap<String, Object> map, Param param) {
		map.put("orgUnitNo", param.getOrgUnitNo());
		map.put("controlUnitNo", param.getControlUnitNo());
		return map;
	}

	@Override
	protected void afterSelect(ScmPurchaseType2 entity, Param param) throws AppException {
		ScmPurchaseTypeOrg scmPurchaseTypeOrg = scmPurchaseTypeOrgBiz.selectByTypeAndOrgNo(entity.getId(), param.getOrgUnitNo(),param);
		// 查找其所属管理单元在ScmPurchaseTypeOrg表中的数据
		ScmPurchaseTypeOrg scmPurchaseTypeOrg1 = scmPurchaseTypeOrgBiz.selectByTypeAndOrgNo(entity.getId(),param.getControlUnitNo(), param);
		if (scmPurchaseTypeOrg != null) {
			entity.setSort(scmPurchaseTypeOrg.getSort());
			// 查看当前记录是否有效
			if (entity.isFlag()) {
				// 该记录在当前管理单元内是否有效
				if (scmPurchaseTypeOrg1 != null) {
					entity.setFlag(scmPurchaseTypeOrg.isFlag()
							&& scmPurchaseTypeOrg1.isFlag());
				} else {
					entity.setFlag(scmPurchaseTypeOrg.isFlag());
				}
			}
		}
	}
	
	protected void beforeDelete(ScmPurchaseType2 entity, Param param) throws AppException {
		if (entity != null) {
			ScmPurRequire2 scmPurRequire2 = scmPurRequireBiz.selectByTypeCode(entity.getCode(),param);
			ScmPurOrder2 scmPurOrder2 = scmPurOrderBiz.selectByTypeCode(entity.getCode(),param);
			if (scmPurOrder2 != null || scmPurRequire2 != null) {
				throw new AppException("iscm.basedata.ScmPurchaseTypeBizImpl.validateDelete");
			}
		}
	}

	@Override
	protected void beforeUpdate(ScmPurchaseType2 oldEntity, ScmPurchaseType2 newEntity, Param param)
			throws AppException {
		if(newEntity!=null) {
			scmPurchaseTypeOrgBiz.updateByPurchaseType(newEntity,param);
			if (!param.getControlUnitNo().equals(param.getOrgUnitNo())){
				newEntity.setFlag(oldEntity.isFlag());
			}
		}
	}

	@Override
	public ScmPurchaseType2 selectByCodeAncCtrl(String code, Param param) throws AppException {
		String key = code+"_byCode";
		if (ModelCacheMana.keyExists(key, modelClass)) {
			Object obj = ModelCacheMana.get(key, modelClass);
			if (obj != null && obj.getClass().equals(modelClass)) {
				return (ScmPurchaseType2) obj;
			}
		}
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("controlUnitNo", param.getControlUnitNo());
		map.put("code", code);
		ScmPurchaseType2 scmPurchaseType = ((ScmPurchaseTypeDAO)dao).selectByCodeAncCtrl(map);
		if(scmPurchaseType!=null) {
			// 放进缓存
			ModelCacheMana.set(key, scmPurchaseType);
		}
		return scmPurchaseType;
	}

	
}
