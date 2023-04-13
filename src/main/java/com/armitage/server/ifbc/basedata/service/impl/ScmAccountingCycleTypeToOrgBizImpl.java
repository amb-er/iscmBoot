package com.armitage.server.ifbc.basedata.service.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.ifbc.basedata.dao.ScmAccountingCycleTypeToOrgDAO;
import com.armitage.server.ifbc.basedata.model.ScmAccountingCycleTypeToOrg2;
import com.armitage.server.ifbc.basedata.service.ScmAccountingCycleTypeToOrgBiz;
import com.armitage.server.system.model.OrgBaseUnit2;
import com.armitage.server.system.service.OrgUnitBiz;
import org.springframework.stereotype.Service;

@Service("scmAccountingCycleTypeToOrgBiz")
public class ScmAccountingCycleTypeToOrgBizImpl extends BaseBizImpl<ScmAccountingCycleTypeToOrg2> implements ScmAccountingCycleTypeToOrgBiz {
	private OrgUnitBiz orgUnitBiz;

	public void setOrgUnitBiz(OrgUnitBiz orgUnitBiz) {
		this.orgUnitBiz = orgUnitBiz;
	}

	@Override
	public ScmAccountingCycleTypeToOrg2 selectByOrgUnit(String orgUnitNo, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("orgUnitNo",orgUnitNo);
		return ((ScmAccountingCycleTypeToOrgDAO)dao).selectByOrgUnit(map);
	}

	@Override
	public List<ScmAccountingCycleTypeToOrg2> selectByTypeId(long typeId, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put(ScmAccountingCycleTypeToOrg2.FN_TYPEID, typeId);
		List<ScmAccountingCycleTypeToOrg2> scmAccountingCycleTypeToOrgList = this.findAll(map, param);
		if(scmAccountingCycleTypeToOrgList!=null && !scmAccountingCycleTypeToOrgList.isEmpty()) {
			for(ScmAccountingCycleTypeToOrg2 scmAccountingCycleTypeToOrg:scmAccountingCycleTypeToOrgList) {
				if(StringUtils.isNotBlank(scmAccountingCycleTypeToOrg.getOrgUnitNo())) {
					OrgBaseUnit2 orgBaseUnit = orgUnitBiz.selectbyOrgNo(scmAccountingCycleTypeToOrg.getOrgUnitNo(), param);
					if(orgBaseUnit!=null)
						scmAccountingCycleTypeToOrg.setOrgUnitName(orgBaseUnit.getOrgUnitName());
				}
			}
		}
		return scmAccountingCycleTypeToOrgList;
	}

	@Override
	protected void beforeAdd(ScmAccountingCycleTypeToOrg2 entity, Param param) throws AppException {
		ScmAccountingCycleTypeToOrg2 scmAccountingCycleTypeToOrg = this.selectByOrgUnit(entity.getOrgUnitNo(), param);
		if(scmAccountingCycleTypeToOrg!=null) {
			throw new AppException(Message.getMessage(param.getLang(),"field.ScmAccountingCycleToOrg.error.existorgunit",new String[] {entity.getOrgUnitNo()}));
		}
	}

	@Override
	protected void beforeUpdate(ScmAccountingCycleTypeToOrg2 oldEntity, ScmAccountingCycleTypeToOrg2 newEntity,
			Param param) throws AppException {
		ScmAccountingCycleTypeToOrg2 scmAccountingCycleTypeToOrg = this.selectByOrgUnit(newEntity.getOrgUnitNo(), param);
		if(scmAccountingCycleTypeToOrg!=null && scmAccountingCycleTypeToOrg.getId()!=newEntity.getId()) {
			throw new AppException(Message.getMessage(param.getLang(),"field.ScmAccountingCycleToOrg.error.existorgunit",new String[] {newEntity.getOrgUnitNo()}));
		}
	}

}
