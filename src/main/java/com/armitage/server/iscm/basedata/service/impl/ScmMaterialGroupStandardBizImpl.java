
package com.armitage.server.iscm.basedata.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Page;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.base.model.RelationModel;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.util.AppContextUtil;
import com.armitage.server.common.util.BeanUtil;
import com.armitage.server.common.util.ClassUtils;
import com.armitage.server.common.util.VarConstant;
import com.armitage.server.iscm.basedata.dao.ScmMaterialGroupStandardDAO;
import com.armitage.server.iscm.basedata.model.ScmMaterial2;
import com.armitage.server.iscm.basedata.model.ScmMaterialGroup;
import com.armitage.server.iscm.basedata.model.ScmMaterialGroupStandard;
import com.armitage.server.iscm.basedata.model.ScmMaterialGroupStandard2;
import com.armitage.server.iscm.basedata.service.ScmMaterialGroupBiz;
import com.armitage.server.iscm.basedata.service.ScmMaterialGroupStandardBiz;
import com.armitage.server.system.model.ForeignKey;
import com.armitage.server.user.model.Usr;
import com.armitage.server.user.service.UsrBiz;
import org.springframework.stereotype.Service;

@Service("scmMaterialGroupStandardBiz")
public class ScmMaterialGroupStandardBizImpl extends BaseBizImpl<ScmMaterialGroupStandard> implements ScmMaterialGroupStandardBiz {
	private UsrBiz usrBiz;
	private ScmMaterialGroupBiz scmMaterialGroupBiz;
	public void setUsrBiz(UsrBiz usrBiz) {
		this.usrBiz = usrBiz;
	}

	@Override
	protected void beforeDelete(ScmMaterialGroupStandard model, Param param) throws AppException {
		String tableName = ClassUtils.getFinalModelSimpleName(model);
		Map<String, List<RelationModel>> map = model.getReferenceMap();
		// 载入外键定义表中登记的引用
		if (VarConstant.REFERENCE_KEY_MAP.containsKey(tableName)) {
			if (map == null) {
				map = new HashMap();
			}
			
			HashMap<String, List<ForeignKey>> tableReferenceKeyMap = VarConstant.REFERENCE_KEY_MAP.get(tableName);
			for (String key : tableReferenceKeyMap.keySet()) {
				List<RelationModel> relationModelList = new ArrayList();
				for (ForeignKey foreignKey : tableReferenceKeyMap.get(key)) {
					BaseBiz baseBiz = null;
					try {
						baseBiz = (BaseBiz) AppContextUtil.getBean(StringUtils.uncapitalize(foreignKey.getTableName()) + "Biz");
					}catch(Exception e){
						//Biz异常时跳过
					}
					if (baseBiz!=null && BeanUtil.hasProperty(ClassUtils.getFinalModelClass(baseBiz.getModelClass()), foreignKey.getColumnName())) {
							RelationModel rm = new RelationModel(foreignKey.getTableName(), foreignKey.getColumnName());
							if (StringUtils.isNotBlank(foreignKey.getForFilter())) {
							BaseBiz biz = (BaseBiz) AppContextUtil.getBean(StringUtils.uncapitalize(foreignKey.getForTableName()) + "Biz");
								biz.addReferenceFilter(rm, foreignKey.getForFilter(), param);
							}
							relationModelList.add(rm);
						}
				}
				map.put(key, relationModelList);
			}
		}

		if (map == null || map.isEmpty()) {
			return;
		}
		
		String[] fields = (String[]) map.keySet().toArray(new String[0]);
		for (String field : fields) {
			List<RelationModel> relationList = map.get(field);
			for (RelationModel rm : relationList) {

				HashMap paramMap = rm.getFilterMap();
				String code = "";
				try {
//
//					// 检查当前模型是否需要检查begin
//					if (!isNeedCheck(rm, model))
//						continue;
//
					paramMap.put(rm.getFieldName(),
							BeanUtil.getPropertyValue(model, field));
					code = (String) BeanUtil.getPropertyValue(model, "standardNo");

				} catch (Exception e) {
					code = "";
				}
				BaseBiz biz = null;
				try {
					biz = (BaseBiz) AppContextUtil.getBean(StringUtils.uncapitalize(rm.getTableName()) + "Biz");
				}catch(Exception e){
					//Biz异常时跳过
				}
				boolean exist = (biz==null?false:biz.findExist(paramMap, null));
				if (exist) {
				 try {
	
					throw new AppException("", new String[] {
							"field." + tableName,
							(field.equals("id") ? (StringUtils.isBlank(code) ? paramMap.get(rm.getFieldName()).toString() : code)
									: paramMap.get(rm.getFieldName()).toString()),
							"field." + rm.getTableName()});
					
					} catch (AppException e) {
						throw getCheckException(e, CheckTypeReferenceDelete);
					}
				}
			}

		}
	
		super.beforeDelete(model, param);
	}
	
	public void setScmMaterialGroupBiz(ScmMaterialGroupBiz scmMaterialGroupBiz) {
		this.scmMaterialGroupBiz = scmMaterialGroupBiz;
	}

	@Override
	protected void afterSelect(ScmMaterialGroupStandard entity, Param param) throws AppException {
		if(entity != null){
			setConvertMap(entity,param);
		}
	}

	@Override
	protected void afterFindPage(List list, Page page, Param param) throws AppException {
		if(list!=null && !list.isEmpty()){
			List<ScmMaterialGroupStandard> scmMaterialGroupStandardList = list;
			for(ScmMaterialGroupStandard scmMaterialGroupStandard:scmMaterialGroupStandardList){
				setConvertMap(scmMaterialGroupStandard,param);
			}
		}
	}

	@Override
	protected void afterQueryPage(List list, Page page, String xmlId, Param param) throws AppException {
		if(list!=null && !list.isEmpty()){
			List<ScmMaterialGroupStandard> scmMaterialGroupStandardList = list;
			for(ScmMaterialGroupStandard scmMaterialGroupStandard:scmMaterialGroupStandardList){
				setConvertMap(scmMaterialGroupStandard,param);
			}
		}
	}

	private void setConvertMap(ScmMaterialGroupStandard scmMaterialGroupStandard,Param param) {
		if(scmMaterialGroupStandard!=null) {
			if(StringUtils.isNotBlank(scmMaterialGroupStandard.getCreator())) {
				Usr usr = usrBiz.selectByCode(scmMaterialGroupStandard.getCreator(), param);
				if(usr!=null)
					scmMaterialGroupStandard.setConvertMap(ScmMaterialGroupStandard.FN_CREATOR, usr);
			}
			if(StringUtils.isNotBlank(scmMaterialGroupStandard.getEditor())) {
				Usr usr = usrBiz.selectByCode(scmMaterialGroupStandard.getEditor(), param);
				if(usr!=null)
					scmMaterialGroupStandard.setConvertMap(ScmMaterialGroupStandard.FN_EDITOR, usr);
			}
		}
	}
	@Override
	protected HashMap<String, Object> addFindAllPageParam(Page page,HashMap<String, Object> map, Param param) {
		map.put("controlUnitNo", param.getControlUnitNo());
		return map;
	}

	@Override
	public List<ScmMaterialGroupStandard2> selectSubsidiaryTypeByItem(ScmMaterial2 scmMaterial, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("itemId", scmMaterial.getId());
		map.put("controlUnitNo", param.getControlUnitNo());
		List<ScmMaterialGroupStandard2> scmMaterialGroupStandardList = ((ScmMaterialGroupStandardDAO)dao).selectSubsidiaryTypeByItem(map);
		if(scmMaterialGroupStandardList!=null && !scmMaterialGroupStandardList.isEmpty()) {
			for(ScmMaterialGroupStandard2 scmMaterialGroupStandard:scmMaterialGroupStandardList) {
				if(scmMaterialGroupStandard.getClassId()>0) {
					ScmMaterialGroup scmMaterialGroup = scmMaterialGroupBiz.selectDirect(scmMaterialGroupStandard.getClassId(), param);
					if(scmMaterialGroup!=null)
						scmMaterialGroupStandard.setConvertMap(ScmMaterialGroupStandard2.FN_CLASSID, scmMaterialGroup);
				}
			}
		}
		return scmMaterialGroupStandardList;
	}
}
