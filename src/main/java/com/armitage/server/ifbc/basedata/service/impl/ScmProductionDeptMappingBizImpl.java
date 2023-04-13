package com.armitage.server.ifbc.basedata.service.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.ifbc.basedata.dao.ScmProductionDeptDAO;
import com.armitage.server.ifbc.basedata.dao.ScmProductionDeptMappingDAO;
import com.armitage.server.ifbc.basedata.model.ScmProductionDept;
import com.armitage.server.ifbc.basedata.model.ScmProductionDeptMapping;
import com.armitage.server.ifbc.basedata.model.ScmProductionDeptMapping2;
import com.armitage.server.ifbc.basedata.service.ScmProductionDeptMappingBiz;
import com.armitage.server.system.model.OrgBaseUnit2;
import com.armitage.server.system.service.OrgBaseUnitBiz;
import org.springframework.stereotype.Service;

@Service("scmProductionDeptMappingBiz")
public class ScmProductionDeptMappingBizImpl extends BaseBizImpl<ScmProductionDeptMapping2> implements ScmProductionDeptMappingBiz {
	private OrgBaseUnitBiz orgBaseUnitBiz;

	public void setOrgBaseUnitBiz(OrgBaseUnitBiz orgBaseUnitBiz) {
		this.orgBaseUnitBiz = orgBaseUnitBiz;
	}

	@Override
	public List<ScmProductionDeptMapping2> selectByProductDeptId(long productDeptId, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("productDeptId", productDeptId);
		List<ScmProductionDeptMapping2> scmProductionDeptMappingList = ((ScmProductionDeptMappingDAO)dao).selectByProductDeptId(map);
		if(scmProductionDeptMappingList!=null && !scmProductionDeptMappingList.isEmpty()) {
			for(ScmProductionDeptMapping2 scmProductionDeptMapping:scmProductionDeptMappingList) {
				if(StringUtils.isNotBlank(scmProductionDeptMapping.getDeptNo())) {
					OrgBaseUnit2 orgBaseUnit = orgBaseUnitBiz.selectbyOrgNo(scmProductionDeptMapping.getDeptNo(), param);
					if(orgBaseUnit!=null)
						scmProductionDeptMapping.setDeptName(orgBaseUnit.getOrgUnitName());
				}
			}
		}
		return scmProductionDeptMappingList;
	}

	@Override
	public void deleteByProductDeptId(long productDeptId, Param param) throws AppException {
		if(productDeptId > 0){
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("productDeptId", productDeptId);
			List<ScmProductionDeptMapping2> scmProductionDeptMappingList = ((ScmProductionDeptMappingDAO)dao).selectByProductDeptId(map);
			this.delete(scmProductionDeptMappingList, param);
		}
	}

	@Override
	public List<ScmProductionDeptMapping2> selectByOrgUnit(String orgUnitNo, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("orgUnitNo", orgUnitNo);
		return ((ScmProductionDeptMappingDAO)dao).selectByOrgUnit(map);
	}

	@Override
	protected void beforeAdd(ScmProductionDeptMapping2 entity, Param param) throws AppException {
		ScmProductionDeptMapping2 scmProductionDeptMapping = this.findRepeat(param.getOrgUnitNo(), entity,param);
		if (scmProductionDeptMapping != null) {
			throw new AppException(
					Message.getMessage(param.getLang(), "iscm.scmProductionDept.errot.deptnorepet", new String[] {
							scmProductionDeptMapping.getDeptName(), scmProductionDeptMapping.getProductDeptName() }));
		}
	}

	@Override
	protected void beforeUpdate(ScmProductionDeptMapping2 oldEntity, ScmProductionDeptMapping2 newEntity, Param param)
			throws AppException {
		ScmProductionDeptMapping2 scmProductionDeptMapping = this.findRepeat(param.getOrgUnitNo(), newEntity,param);
		if (scmProductionDeptMapping != null && scmProductionDeptMapping.getId() != newEntity.getId()) {
			throw new AppException(
					Message.getMessage(param.getLang(), "iscm.scmProductionDept.errot.deptnorepet", new String[] {
							scmProductionDeptMapping.getDeptName(), scmProductionDeptMapping.getProductDeptName() }));
		}
	}

	@Override
	public ScmProductionDeptMapping2 findRepeat(String orgUnitNo, ScmProductionDeptMapping2 scmProductionDeptMapping, Param param)
			throws AppException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("deptNo", scmProductionDeptMapping.getDeptNo());
		List<ScmProductionDeptMapping2> scmProductionDeptMappingList = ((ScmProductionDeptMappingDAO) dao).findRepeat(map);
		if(scmProductionDeptMappingList!=null && !scmProductionDeptMappingList.isEmpty()) {
			OrgBaseUnit2 orgBaseUnit = orgBaseUnitBiz.selectbyOrgNo(scmProductionDeptMappingList.get(0).getDeptNo(), param);
			if(orgBaseUnit!=null)
				(scmProductionDeptMappingList.get(0)).setDeptName(orgBaseUnit.getOrgUnitName());
			return scmProductionDeptMappingList.get(0);
		}
		return (scmProductionDeptMappingList == null || scmProductionDeptMappingList.isEmpty()) ? null
				: scmProductionDeptMappingList.get(0);
	}


}
