package com.armitage.server.ifbc.basedata.service.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.CommonBean;
import com.armitage.server.common.base.model.Page;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.base.model.QueryParam;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.util.ClassUtils;
import com.armitage.server.ifbc.basedata.dao.ScmProductionDeptDAO;
import com.armitage.server.ifbc.basedata.model.ScmProductionDept;
import com.armitage.server.ifbc.basedata.model.ScmProductionDeptMapping2;
import com.armitage.server.ifbc.basedata.service.ScmProductionDeptBiz;
import com.armitage.server.ifbc.basedata.service.ScmProductionDeptMappingBiz;
import com.armitage.server.system.model.OrgResource2;
import com.armitage.server.system.model.Outlet;
import com.armitage.server.system.service.OrgResourceBiz;
import com.armitage.server.system.service.OutletBiz;
import org.springframework.stereotype.Service;

@Service("scmProductionDeptBiz")
public class ScmProductionDeptBizImpl extends BaseBizImpl<ScmProductionDept> implements ScmProductionDeptBiz {
	private ScmProductionDeptMappingBiz scmProductionDeptMappingBiz;
	private OrgResourceBiz orgResourceBiz;
	private OutletBiz outletBiz;
	
	public void setScmProductionDeptMappingBiz(ScmProductionDeptMappingBiz scmProductionDeptMappingBiz) {
		this.scmProductionDeptMappingBiz = scmProductionDeptMappingBiz;
	}

	public void setOrgResourceBiz(OrgResourceBiz orgResourceBiz) {
		this.orgResourceBiz = orgResourceBiz;
	}

	public void setOutletBiz(OutletBiz outletBiz) {
		this.outletBiz = outletBiz;
	}

	@Override
	protected void beforeFindPage(Page page, Param param) throws AppException {
		List<OrgResource2> orgResource2s = orgResourceBiz.findChild(param.getOrgUnitNo(), param);
		if (orgResource2s != null && !orgResource2s.isEmpty()) {
			StringBuffer orgunitList = new StringBuffer("");
			for (OrgResource2 orgResource2 : orgResource2s) {
				if (StringUtils.isNotBlank(orgunitList.toString())) {
					orgunitList.append(",");
				}
				orgunitList.append("'").append(orgResource2.getOrgUnitNo()).append("'");
			}
			page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmProductionDept.class) + "." + ScmProductionDept.FN_ORGUNITNO), 
					new QueryParam((ClassUtils.getFinalModelSimpleName(ScmProductionDept.class) + "." + ScmProductionDept.FN_ORGUNITNO), QueryParam.QUERY_IN, orgunitList.toString()));
		}else {
			page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmProductionDept.class) + "." + ScmProductionDept.FN_ORGUNITNO), 
					new QueryParam((ClassUtils.getFinalModelSimpleName(ScmProductionDept.class) + "." + ScmProductionDept.FN_ORGUNITNO), QueryParam.QUERY_EQ, param.getOrgUnitNo()));
		}
	}

	@Override
	protected void beforeAdd(ScmProductionDept entity, Param param) throws AppException {
		ScmProductionDept scmProductionDept = this.findRepeat(param.getOrgUnitNo(), entity);
		if(scmProductionDept!=null) {
			throw new AppException(Message.getMessage(param.getLang(),"iscm.common.input.coderepet"));
		}
	}

	@Override
	protected void beforeUpdate(ScmProductionDept oldEntity, ScmProductionDept newEntity, Param param)
			throws AppException {
		ScmProductionDept scmProductionDept = this.findRepeat(param.getOrgUnitNo(), newEntity);
		if(scmProductionDept!=null && scmProductionDept.getId()!=newEntity.getId()) {
			throw new AppException(Message.getMessage(param.getLang(),"iscm.common.input.coderepet"));
		}
	}

	@Override
	protected void afterAdd(CommonBean bean, Param param) throws AppException {
		ScmProductionDept scmProductionDept = (ScmProductionDept) bean.getList().get(0);
		if(scmProductionDept != null && scmProductionDept.getId() > 0){
			//新增部门对照
			List<ScmProductionDeptMapping2> scmProductionDeptMappingList = bean.getList2();
			if(scmProductionDeptMappingList != null && !scmProductionDeptMappingList.isEmpty()){
				for(ScmProductionDeptMapping2 scmProductionDeptMapping : scmProductionDeptMappingList){
					scmProductionDeptMapping.setProductDeptId(scmProductionDept.getId());
					scmProductionDeptMappingBiz.add(scmProductionDeptMapping, param);
				}
			}
		}
	}

	@Override
	protected void afterSelect(CommonBean bean, Param param) throws AppException {
		ScmProductionDept scmProductionDept = (ScmProductionDept) bean.getList().get(0);
		if(scmProductionDept != null && scmProductionDept.getId() > 0){
			bean.setList2(scmProductionDeptMappingBiz.selectByProductDeptId(scmProductionDept.getId(), param));
		}
	}

	@Override
	protected void afterUpdate(CommonBean bean, Param param) throws AppException {
		ScmProductionDept scmProductionDept = (ScmProductionDept) bean.getList().get(0);
		if(scmProductionDept != null && scmProductionDept.getId() > 0){
			//新增部门对照
			List<ScmProductionDeptMapping2> scmProductionDeptMappingList = bean.getList2();
			if(scmProductionDeptMappingList != null && !scmProductionDeptMappingList.isEmpty()){
				for(ScmProductionDeptMapping2 scmProductionDeptMapping : scmProductionDeptMappingList){
					scmProductionDeptMapping.setProductDeptId(scmProductionDept.getId());
				}
			}
			scmProductionDeptMappingBiz.update(scmProductionDept, scmProductionDeptMappingList, ScmProductionDeptMapping2.FN_PRODUCTDEPTID, param);
		}
	}

	@Override
	protected void afterDelete(ScmProductionDept entity, Param param) throws AppException {
		if(entity != null && entity.getId() > 0){
			//删除定价明细
			scmProductionDeptMappingBiz.deleteByProductDeptId(entity.getId(), param);
		}
	}

	@Override
	public ScmProductionDept findRepeat(String orgUnitNo, ScmProductionDept scmProductionDept) throws AppException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("orgUnitNo", orgUnitNo);
		map.put("code", scmProductionDept.getCode());
		List<ScmProductionDept> scmProductionDeptList = ((ScmProductionDeptDAO) dao).findRepeat(map);
		return (scmProductionDeptList == null || scmProductionDeptList.isEmpty()) ? null
				: scmProductionDeptList.get(0);
	}

	@Override
	protected void afterFindPage(List list, Page page, Param param) throws AppException {
		if(list != null && !list.isEmpty()){
			for(ScmProductionDept scmProductionDept : (List<ScmProductionDept>)list){
				this.setConvertMap(scmProductionDept, param);
			}
		}
	}

	@Override
	protected void afterSelect(ScmProductionDept entity, Param param) throws AppException {
		if (entity != null){
			setConvertMap(entity,param);
		}
	}

	private void setConvertMap(ScmProductionDept scmProductionDept, Param param) throws AppException {
		if(scmProductionDept!=null) {
			if(scmProductionDept.getOutletId()>0) {
				Outlet outlet = outletBiz.selectDirect(scmProductionDept.getOutletId(), param);
				if(outlet!=null) {
					scmProductionDept.setConvertMap(ScmProductionDept.FN_OUTLETID, outlet);
				}
			}
		}
	}
}
