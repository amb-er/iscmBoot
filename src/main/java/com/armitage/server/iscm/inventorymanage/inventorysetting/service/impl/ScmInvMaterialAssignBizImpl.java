package com.armitage.server.iscm.inventorymanage.inventorysetting.service.impl;


import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Page;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.base.model.SimpleDataDisplay;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.util.ClassUtils;
import com.armitage.server.common.util.SimpleDataDisplayUtil;
import com.armitage.server.common.util.StringUtil;
import com.armitage.server.iscm.basedata.model.ScmMaterial2;
import com.armitage.server.iscm.basedata.model.ScmMaterialGroup;
import com.armitage.server.iscm.basedata.service.ScmMaterialBiz;
import com.armitage.server.iscm.basedata.service.ScmMaterialGroupBiz;
import com.armitage.server.iscm.inventorymanage.inventorysetting.dao.ScmInvMaterialAssignDAO;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvAccreditWh;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvMaterialAssign2;
import com.armitage.server.iscm.inventorymanage.inventorysetting.service.ScmInvAccreditWhBiz;
import com.armitage.server.iscm.inventorymanage.inventorysetting.service.ScmInvMaterialAssignBiz;
import com.armitage.server.user.model.Usr;
import com.armitage.server.user.service.UsrBiz;
import org.springframework.stereotype.Service;

@Service("scmInvMaterialAssignBiz")
public class ScmInvMaterialAssignBizImpl extends BaseBizImpl<ScmInvMaterialAssign2> implements ScmInvMaterialAssignBiz {

	private UsrBiz usrBiz;
	private ScmMaterialBiz scmMaterialBiz;
	private ScmMaterialGroupBiz scmMaterialGroupBiz;
	private ScmInvAccreditWhBiz scmInvAccreditWhBiz;
	
	public void setUsrBiz(UsrBiz usrBiz) {
		this.usrBiz = usrBiz;
	}

	public void setScmMaterialBiz(ScmMaterialBiz scmMaterialBiz) {
		this.scmMaterialBiz = scmMaterialBiz;
	}

	public void setScmMaterialGroupBiz(ScmMaterialGroupBiz scmMaterialGroupBiz) {
		this.scmMaterialGroupBiz = scmMaterialGroupBiz;
	}

	public void setScmInvAccreditWhBiz(ScmInvAccreditWhBiz scmInvAccreditWhBiz) {
		this.scmInvAccreditWhBiz = scmInvAccreditWhBiz;
	}

	@Override
	public List<ScmInvMaterialAssign2> selectMaterialAssign(long wareHouseId, Param param) throws AppException {
		if(wareHouseId > 0){
			HashMap<String, Object> map = new HashMap<>();
			map.put("wareHouseId", wareHouseId);
			List<ScmInvMaterialAssign2> scmInvMaterialAssignList =  ((ScmInvMaterialAssignDAO) dao).selectMaterialAssign(map);
			if(scmInvMaterialAssignList != null && !scmInvMaterialAssignList.isEmpty()){
				HashMap<String,Object> cacheMap = new HashMap<String,Object>();
				for(ScmInvMaterialAssign2 scmInvMaterialAssign : scmInvMaterialAssignList){
					setConvertMap(scmInvMaterialAssign,cacheMap,param);
				}
				return scmInvMaterialAssignList;
			}
		}
		return null;
	}
	
	@Override
	protected void afterSelect(ScmInvMaterialAssign2 entity, Param param) throws AppException {
		ScmInvMaterialAssign2 scmInvMaterialAssign = entity;
		if(scmInvMaterialAssign != null){
			HashMap<String,Object> cacheMap = new HashMap<String,Object>();
			if (StringUtils.isNotBlank(scmInvMaterialAssign.getCreator())){
				//设置人
				Usr usr = (Usr) cacheMap.get(ClassUtils.getFinalModelSimpleName(Usr.class)+"_"+scmInvMaterialAssign.getCreator());
				if(usr==null){
					usr = usrBiz.selectByCode(scmInvMaterialAssign.getCreator(), param);
					cacheMap.put(ClassUtils.getFinalModelSimpleName(Usr.class)+"_"+scmInvMaterialAssign.getCreator(),usr);
				}
				if (usr != null) {
					scmInvMaterialAssign.setConvertMap(ScmInvMaterialAssign2.FN_CREATOR, usr);
				}
			}
			if (StringUtils.isNotBlank(scmInvMaterialAssign.getEditor())){
				//修改人
				Usr usr = (Usr) cacheMap.get(ClassUtils.getFinalModelSimpleName(Usr.class)+"_"+scmInvMaterialAssign.getEditor());
				if(usr==null){
					usr = usrBiz.selectByCode(scmInvMaterialAssign.getEditor(), param);
					cacheMap.put(ClassUtils.getFinalModelSimpleName(Usr.class)+"_"+scmInvMaterialAssign.getEditor(),usr);
				}
				if (usr != null) {
					scmInvMaterialAssign.setConvertMap(ScmInvMaterialAssign2.FN_EDITOR, usr);
				}
			}
		}
	}
	
	@Override
	protected void afterFindPage(List list, Page page, Param param)
			throws AppException {
		HashMap<String,Object> cacheMap = new HashMap<String,Object>();
		List<ScmInvMaterialAssign2> scmInvMaterialAssignList = list;
		if(scmInvMaterialAssignList != null && !scmInvMaterialAssignList.isEmpty()){
			for(ScmInvMaterialAssign2 scmInvMaterialAssign : scmInvMaterialAssignList){
				setConvertMap(scmInvMaterialAssign,cacheMap,param);
			}
		}
	}
	
	private void setConvertMap(ScmInvMaterialAssign2 scmInvMaterialAssign, HashMap<String,Object> cacheMap, Param param){
		if (scmInvMaterialAssign.getItemClsId() > 0){
			ScmMaterialGroup scmMaterialGroup = (ScmMaterialGroup) cacheMap.get(ClassUtils.getFinalModelSimpleName(ScmMaterialGroup.class)+"_"+scmInvMaterialAssign.getItemClsId());
			if(scmMaterialGroup == null){
				scmMaterialGroup = scmMaterialGroupBiz.selectDirect(scmInvMaterialAssign.getItemClsId(), param);
				cacheMap.put(ClassUtils.getFinalModelSimpleName(ScmMaterialGroup.class)+"_"+scmInvMaterialAssign.getItemClsId(),scmMaterialGroup);
			}
			if(scmMaterialGroup != null){
				scmInvMaterialAssign.setConvertMap(ScmInvMaterialAssign2.FN_ITEMNO, scmMaterialGroup);
			}
		}else if(scmInvMaterialAssign.getItemId() > 0){
			ScmMaterial2 scmMaterial = (ScmMaterial2) cacheMap.get(ClassUtils.getFinalModelSimpleName(ScmMaterial2.class)+"_"+scmInvMaterialAssign.getItemId());
			if(scmMaterial == null){
				scmMaterial = scmMaterialBiz.selectDirect(scmInvMaterialAssign.getItemId(), param);
				cacheMap.put(ClassUtils.getFinalModelSimpleName(ScmMaterial2.class)+"_"+scmInvMaterialAssign.getItemId(),scmMaterial);
			}
			if(scmMaterial != null){
				scmInvMaterialAssign.setConvertMap(ScmInvMaterialAssign2.FN_ITEMNO, scmMaterial);
			}
		}
		if (StringUtils.isNotBlank(scmInvMaterialAssign.getCreator())){
			//设置人
			Usr usr = (Usr) cacheMap.get(ClassUtils.getFinalModelSimpleName(Usr.class)+"_"+scmInvMaterialAssign.getCreator());
			if(usr==null){
				usr = usrBiz.selectByCode(scmInvMaterialAssign.getCreator(), param);
				cacheMap.put(ClassUtils.getFinalModelSimpleName(Usr.class)+"_"+scmInvMaterialAssign.getCreator(),usr);
			}
			if (usr != null) {
				if(scmInvMaterialAssign.getDataDisplayMap()==null){
					scmInvMaterialAssign.setDataDisplayMap(new HashMap<String,SimpleDataDisplay>());
				}
				scmInvMaterialAssign.getDataDisplayMap().put(ScmInvMaterialAssign2.FN_CREATOR, SimpleDataDisplayUtil.convert(usr));
				scmInvMaterialAssign.setConvertMap(ScmInvMaterialAssign2.FN_CREATOR, usr);
			}
		}
		if (StringUtils.isNotBlank(scmInvMaterialAssign.getEditor())){
			//修改人
			Usr usr = (Usr) cacheMap.get(ClassUtils.getFinalModelSimpleName(Usr.class)+"_"+scmInvMaterialAssign.getEditor());
			if(usr==null){
				usr = usrBiz.selectByCode(scmInvMaterialAssign.getEditor(), param);
				cacheMap.put(ClassUtils.getFinalModelSimpleName(Usr.class)+"_"+scmInvMaterialAssign.getEditor(),usr);
			}
			if (usr != null) {
				if(scmInvMaterialAssign.getDataDisplayMap()==null){
					scmInvMaterialAssign.setDataDisplayMap(new HashMap<String,SimpleDataDisplay>());
				}
				scmInvMaterialAssign.getDataDisplayMap().put(ScmInvMaterialAssign2.FN_EDITOR, SimpleDataDisplayUtil.convert(usr));
				scmInvMaterialAssign.setConvertMap(ScmInvMaterialAssign2.FN_EDITOR, usr);
			}
		}
	}

	@Override
	public boolean checkItemAssign(long wareHouseId, long itemId, String orgUnitNo, Param param) throws AppException {
		ScmInvAccreditWh scmInvAccreditWh = scmInvAccreditWhBiz.selectByWareHouseId(wareHouseId,orgUnitNo, param);
		if(StringUtils.equals("A",scmInvAccreditWh.getStockType()))
			return true;
		HashMap<String, Object> map = new HashMap<>();
		map.put("wareHouseId", wareHouseId);
		map.put("orgUnitNo", orgUnitNo);
		List<ScmInvMaterialAssign2> scmInvMaterialAssignList =  ((ScmInvMaterialAssignDAO) dao).selectByOrgUnitNo(map);
		if(scmInvMaterialAssignList != null && !scmInvMaterialAssignList.isEmpty()){
			
			for(ScmInvMaterialAssign2 scmInvMaterialAssign : scmInvMaterialAssignList){
				if(StringUtils.equals("1", scmInvMaterialAssign.getAssignType())){
					ScmMaterial2 scmMaterial = scmMaterialBiz.selectByItemId(itemId, param);
					if(scmMaterial != null && scmInvMaterialAssign.getItemClsId() == scmMaterial.getGroupId()){
						return true;
					}
				}else if(StringUtils.equals("2", scmInvMaterialAssign.getAssignType()) && scmInvMaterialAssign.getItemId() == itemId){
					return true;
				}
			}
		}
		return false;
	}
}

