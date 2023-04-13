package com.armitage.server.ifbc.basedata.service.impl;

import java.util.HashMap;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.CommonBean;
import com.armitage.server.common.base.model.Page;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.base.model.QueryParam;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.util.ClassUtils;
import com.armitage.server.ifbc.basedata.model.ScmAccountingCycleType2;
import com.armitage.server.ifbc.basedata.model.ScmAccountingCycleTypeToOrg2;
import com.armitage.server.ifbc.basedata.service.ScmAccountingCycleTypeBiz;
import com.armitage.server.ifbc.basedata.service.ScmAccountingCycleTypeToOrgBiz;
import com.armitage.server.system.model.Code;
import com.armitage.server.system.service.CodeBiz;
import org.springframework.stereotype.Service;

@Service("scmAccountingCycleTypeBiz")
public class ScmAccountingCycleTypeBizImpl extends BaseBizImpl<ScmAccountingCycleType2> implements ScmAccountingCycleTypeBiz {
	private CodeBiz codeBiz;
	private ScmAccountingCycleTypeToOrgBiz scmAccountingCycleTypeToOrgBiz;
	
	public void setCodeBiz(CodeBiz codeBiz) {
		this.codeBiz = codeBiz;
	}

	public void setScmAccountingCycleTypeToOrgBiz(ScmAccountingCycleTypeToOrgBiz scmAccountingCycleTypeToOrgBiz) {
		this.scmAccountingCycleTypeToOrgBiz = scmAccountingCycleTypeToOrgBiz;
	}

	@Override
	protected void beforeFindPage(Page page, Param param) throws AppException {
		page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmAccountingCycleType2.class) + "." + ScmAccountingCycleType2.FN_CONTROLUNITNO), 
				new QueryParam((ClassUtils.getFinalModelSimpleName(ScmAccountingCycleType2.class) + "." + ScmAccountingCycleType2.FN_CONTROLUNITNO), QueryParam.QUERY_EQ, param.getControlUnitNo()	));
	}

	@Override
	protected void afterFindPage(List list, Page page, Param param) throws AppException {
		if(list!=null && !list.isEmpty()) {
			for(ScmAccountingCycleType2 scmAccountingCycleType:(List<ScmAccountingCycleType2>)list) {
				this.setConverMap(scmAccountingCycleType,param);
			}
		}
	}

	private void setConverMap(ScmAccountingCycleType2 scmAccountingCycleType, Param param) throws AppException {
		if(scmAccountingCycleType!=null) {
			if(StringUtils.isNotBlank(scmAccountingCycleType.getPeriodType())) {
				Code code = codeBiz.selectByCategoryAndCode("ScmPeriodType", scmAccountingCycleType.getPeriodType());
				if(code!=null)
					scmAccountingCycleType.setPeriodTypeName(code.getName());
			}
		}
	}

	@Override
	protected void afterAdd(CommonBean bean, Param param) throws AppException {
		ScmAccountingCycleType2 scmAccountingCycleType = (ScmAccountingCycleType2) bean.getList().get(0);
		if(scmAccountingCycleType != null && scmAccountingCycleType.getId() > 0){
			//新增适用部门
			List<ScmAccountingCycleTypeToOrg2> scmAccountingCycleTypeToOrgList = bean.getList2();
			if(scmAccountingCycleTypeToOrgList != null && !scmAccountingCycleTypeToOrgList.isEmpty()){
				for(ScmAccountingCycleTypeToOrg2 scmAccountingCycleTypeToOrg : scmAccountingCycleTypeToOrgList){
					scmAccountingCycleTypeToOrg.setTypeId(scmAccountingCycleType.getId());
				}
			}
			scmAccountingCycleTypeToOrgBiz.update(scmAccountingCycleType, scmAccountingCycleTypeToOrgList, ScmAccountingCycleTypeToOrg2.FN_TYPEID, param);
		}
	}

	@Override
	protected void afterDelete(ScmAccountingCycleType2 entity, Param param) throws AppException {
		if(entity!=null) {
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put(ScmAccountingCycleTypeToOrg2.FN_TYPEID, entity.getId());
			scmAccountingCycleTypeToOrgBiz.delete(map, param);
		}
	}

	@Override
	protected void afterUpdate(CommonBean bean, Param param) throws AppException {
		ScmAccountingCycleType2 scmAccountingCycleType = (ScmAccountingCycleType2) bean.getList().get(0);
		if(scmAccountingCycleType != null && scmAccountingCycleType.getId() > 0){
			//新增适用部门
			List<ScmAccountingCycleTypeToOrg2> scmAccountingCycleTypeToOrgList = bean.getList2();
			if(scmAccountingCycleTypeToOrgList != null && !scmAccountingCycleTypeToOrgList.isEmpty()){
				for(ScmAccountingCycleTypeToOrg2 scmAccountingCycleTypeToOrg : scmAccountingCycleTypeToOrgList){
					scmAccountingCycleTypeToOrg.setTypeId(scmAccountingCycleType.getId());
				}
			}
			scmAccountingCycleTypeToOrgBiz.update(scmAccountingCycleType, scmAccountingCycleTypeToOrgList, ScmAccountingCycleTypeToOrg2.FN_TYPEID, param);
		}
	}

	@Override
	protected void beforeAdd(ScmAccountingCycleType2 entity, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put(ScmAccountingCycleType2.FN_PERIODTYPE, entity.getPeriodType());
		map.put(ScmAccountingCycleType2.FN_CONTROLUNITNO,param.getControlUnitNo());
		List<ScmAccountingCycleType2> scmAccountingCycleTypeList = this.findAll(map, param);
		if(scmAccountingCycleTypeList!=null && !scmAccountingCycleTypeList.isEmpty()) {
			throw new AppException(Message.getMessage(param.getLang(),"field.ScmAccountingCycleType.typerepet"));
		}
	}

	@Override
	protected void afterSelect(CommonBean bean, Param param) throws AppException {
		ScmAccountingCycleType2 scmAccountingCycleType = (ScmAccountingCycleType2) bean.getList().get(0);
		if(scmAccountingCycleType != null && scmAccountingCycleType.getId() > 0){
			bean.setList2(scmAccountingCycleTypeToOrgBiz.selectByTypeId(scmAccountingCycleType.getId(), param));
		}
	}

	@Override
	protected void beforeUpdate(ScmAccountingCycleType2 oldEntity, ScmAccountingCycleType2 newEntity, Param param)
			throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put(ScmAccountingCycleType2.FN_PERIODTYPE, newEntity.getPeriodType());
		map.put(ScmAccountingCycleType2.FN_CONTROLUNITNO,param.getControlUnitNo());
		List<ScmAccountingCycleType2> scmAccountingCycleTypeList = this.findAll(map, param);
		if(scmAccountingCycleTypeList!=null && !scmAccountingCycleTypeList.isEmpty()) {
			for(ScmAccountingCycleType2 scmAccountingCycleType:scmAccountingCycleTypeList) {
				if(scmAccountingCycleType.getId()!=newEntity.getId())
					throw new AppException(Message.getMessage(param.getLang(),"field.ScmAccountingCycleType.typerepet"));
			}
		}
	}

	@Override
	protected void afterQueryPage(List list, Page page, String xmlId, Param param) throws AppException {
		if(list!=null && !list.isEmpty()) {
			for(ScmAccountingCycleType2 scmAccountingCycleType:(List<ScmAccountingCycleType2>)list) {
				this.setConverMap(scmAccountingCycleType,param);
			}
		}
	}
	
}
