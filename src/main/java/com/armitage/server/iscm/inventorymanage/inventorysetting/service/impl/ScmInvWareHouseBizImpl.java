package com.armitage.server.iscm.inventorymanage.inventorysetting.service.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.CommonBean;
import com.armitage.server.common.base.model.Page;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.base.model.QueryParam;
import com.armitage.server.common.cache.ModelCacheMana;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.util.ClassUtils;
import com.armitage.server.iscm.inventorymanage.inventorysetting.dao.ScmInvWareHouseDAO;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvAccreditWh2;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvWareHouse;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvWareHouseUsr;
import com.armitage.server.iscm.inventorymanage.inventorysetting.service.ScmInvAccreditWhBiz;
import com.armitage.server.iscm.inventorymanage.inventorysetting.service.ScmInvWareHouseBiz;
import com.armitage.server.iscm.inventorymanage.inventorysetting.service.ScmInvWareHouseUsrBiz;
import com.armitage.server.system.model.OrgStorage2;
import com.armitage.server.system.service.OrgStorageBiz;
import com.armitage.server.system.util.CodeAutoCalUtil;
import com.armitage.server.user.model.Usr;
import com.armitage.server.user.service.UsrBiz;
import org.springframework.stereotype.Service;

@Service("scmInvWareHouseBiz")
public class ScmInvWareHouseBizImpl extends BaseBizImpl<ScmInvWareHouse> implements ScmInvWareHouseBiz {

	private UsrBiz usrBiz;
	private ScmInvAccreditWhBiz scmInvAccreditWhBiz;
	private ScmInvWareHouseUsrBiz scmInvWareHouseUsrBiz;
	private OrgStorageBiz orgStorageBiz;
	
	public void setUsrBiz(UsrBiz usrBiz) {
		this.usrBiz = usrBiz;
	}

	public void setScmInvAccreditWhBiz(ScmInvAccreditWhBiz scmInvAccreditWhBiz) {
		this.scmInvAccreditWhBiz = scmInvAccreditWhBiz;
	}

	public void setScmInvWareHouseUsrBiz(ScmInvWareHouseUsrBiz scmInvWareHouseUsrBiz) {
		this.scmInvWareHouseUsrBiz = scmInvWareHouseUsrBiz;
	}

	public void setOrgStorageBiz(OrgStorageBiz orgStorageBiz) {
		this.orgStorageBiz = orgStorageBiz;
	}

	@Override
	protected void beforeFindPage(Page page, Param param) throws AppException {
		List<OrgStorage2> orgStorageList = orgStorageBiz.findChild(param.getOrgUnitNo(), param);
		if (orgStorageList != null && !orgStorageList.isEmpty()) {
			StringBuffer orgunitList = new StringBuffer("");
			for (OrgStorage2 orgStorage : orgStorageList) {
				if(StringUtils.isNotBlank(orgunitList.toString()))
					orgunitList.append(",");
				orgunitList.append("'").append(orgStorage.getOrgUnitNo()).append("'");
			}
			page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmInvWareHouse.class) + "." + ScmInvWareHouse.FN_ORGUNITNO), 
					new QueryParam((ClassUtils.getFinalModelSimpleName(ScmInvWareHouse.class) + "." + ScmInvWareHouse.FN_ORGUNITNO), QueryParam.QUERY_IN, orgunitList.toString()));
		}else{
			page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmInvWareHouse.class) + "." + ScmInvWareHouse.FN_ORGUNITNO), 
					new QueryParam((ClassUtils.getFinalModelSimpleName(ScmInvWareHouse.class) + "." + ScmInvWareHouse.FN_ORGUNITNO), QueryParam.QUERY_EQ, "0"));
		}
	}
	
	@Override
	protected void beforeAdd(ScmInvWareHouse entity, Param param) throws AppException {
		if(entity != null){
			ScmInvWareHouse scmInvWareHouse = ((ScmInvWareHouseDAO) dao).selectMaxId();
			StringBuffer s = new StringBuffer("");
			if(scmInvWareHouse == null){
				s.append("W000001");
			}else if(scmInvWareHouse != null && StringUtils.isNotBlank(scmInvWareHouse.getWhNo())
					&& scmInvWareHouse.getWhNo().contains("W")){
				String str = StringUtils.substring(scmInvWareHouse.getWhNo(), (scmInvWareHouse.getWhNo().indexOf("W")+"W".length()));
				str = CodeAutoCalUtil.autoAddOne(str);
				s.append("W").append(str);
			}
			entity.setWhNo(s.toString());
		}
	}
	
	@Override
	protected void afterSelect(ScmInvWareHouse entity, Param param) throws AppException {
		if (entity != null){
			if (StringUtils.isNotBlank(entity.getCreator())){
				//制单人
				Usr usr = usrBiz.selectByCode(entity.getCreator(), param);
				if (usr != null) {
					entity.setConvertMap(ScmInvWareHouse.FN_CREATOR, usr);
				}
			}
		}
	}

	@Override
	public ScmInvWareHouse enableWareHouse(ScmInvWareHouse scmInvWareHouse, Param param) throws AppException {
		scmInvWareHouse = this.selectDirect(scmInvWareHouse.getId(), param);
		if(StringUtils.equals("Y",scmInvWareHouse.getStatus())){
			throw new AppException("iscm.basedata.controller.ScmMaterial.alreadyStart");
		}
		scmInvWareHouse.setStatus("Y");
		scmInvWareHouse = this.update(scmInvWareHouse, param);
		ScmInvAccreditWh2 scmInvAccreditWh = new ScmInvAccreditWh2(true);
		scmInvAccreditWh.setWareHouseId(scmInvWareHouse.getId());
		scmInvAccreditWh.setOrgUnitNo(scmInvWareHouse.getOrgUnitNo());
		scmInvAccreditWh.setMorgUnitNo(scmInvWareHouse.getOrgUnitNo());
		scmInvAccreditWhBiz.add(scmInvAccreditWh, param);
		return scmInvWareHouse;
	}

	@Override
	public List<ScmInvAccreditWh2> selectWareHouseSet(List<ScmInvWareHouse> scmInvWareHouseList, Param param)
			throws AppException {
		if(scmInvWareHouseList != null && !scmInvWareHouseList.isEmpty()){
			StringBuffer wareHouseIdList = new StringBuffer("");
			for(ScmInvWareHouse scmInvWareHouse : scmInvWareHouseList){
				if(scmInvWareHouse.getId() > 0){
					wareHouseIdList.append(scmInvWareHouse.getId()).append(",");
				}
			}
			if(StringUtils.isNotBlank(wareHouseIdList.toString())){
				return scmInvAccreditWhBiz.selectByWareHouseIdList(StringUtils.substring(wareHouseIdList.toString(), 0, (wareHouseIdList.toString()).length()-1), param);
			}
		}
		return null;
	}

	@Override
	public List<ScmInvWareHouse> selectByOrgUnitNo(String orgUnitNo, String fromWhNo, String toWhNo, Param param) throws AppException {
		if(StringUtils.isNotBlank(orgUnitNo)){
			HashMap<String, Object> map = new HashMap<>();
			map.put("orgUnitNo", orgUnitNo);
			if(StringUtils.isNotBlank(fromWhNo)){
				map.put("fromWhNo", fromWhNo);
			}
			if(StringUtils.isNotBlank(toWhNo)){
				map.put("toWhNo", toWhNo);
			}
			return ((ScmInvWareHouseDAO) dao).selectByOrgUnitNo(map);
		}
		return null;
	}

	@Override
	public ScmInvWareHouse selectByWhNo(String orgUnitNo, String whNo,Param param) throws AppException {
		String key = orgUnitNo+"_"+whNo;
		if (ModelCacheMana.keyExists(key, modelClassById == null ? modelClass : modelClassById)) {
			Object obj = ModelCacheMana.get(key, modelClassById == null ? modelClass : modelClassById);
			if (obj != null && obj.getClass().equals(modelClass)) {
				return (ScmInvWareHouse) obj;
			}
		}
		HashMap<String, Object> map = new HashMap<>();
		map.put("orgUnitNo", orgUnitNo);
		map.put("whNo", whNo);
		ScmInvWareHouse scmInvWareHouse = ((ScmInvWareHouseDAO) dao).selectByWhNo(map);
		if (scmInvWareHouse != null) {
			ModelCacheMana.set(key, scmInvWareHouse);
		}
		return scmInvWareHouse;
	}

	@Override
	public List<ScmInvWareHouse> queryWareHouseList(ScmInvWareHouse wareHouse,int pageNum, Param param) throws AppException {
		Page page=new Page();
		page.setModelClass(ScmInvWareHouse.class);
		if (pageNum == -1) {
			page.setPagePos(1);
			page.setShowCount(Integer.MAX_VALUE);
		} else {
			page.setPagePos(pageNum);
			page.setShowCount(20);
		}
		if(StringUtils.isNotBlank(wareHouse.getWhName())){
			page.setSqlCondition("whName like '%"+wareHouse.getWhName()+"%'");
		}
		return this.findPage(page, param);
	}

	@Override
	public List<ScmInvWareHouse> selectByWhName(ScmInvWareHouse scmInvWareHouse, Param param) throws AppException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("whName", scmInvWareHouse.getWhName());
		map.put("id", scmInvWareHouse.getId());
		map.put("controlUnitNo", param.getControlUnitNo());
		return ((ScmInvWareHouseDAO) dao).selectByWhName(map);
	}

	@Override
	public CommonBean selectWareHouseUsr(ScmInvWareHouse scmInvWareHouse, Param param) throws AppException {
		CommonBean bean = new CommonBean();
		List<ScmInvWareHouse> scmInvWareHouseList = new ArrayList();
		scmInvWareHouse = (ScmInvWareHouse) this.select(scmInvWareHouse, param);
		scmInvWareHouseList.add(scmInvWareHouse);
		bean.setList(scmInvWareHouseList);
		bean.setList2(scmInvWareHouseUsrBiz.selectByWareHouseId(scmInvWareHouse, param));
		return bean;
	}

	@Override
	public void saveWareHouseUsr(CommonBean bean, Param param) throws AppException {
		if(bean!=null && bean.getList()!=null && !bean.getList().isEmpty()) {
			scmInvWareHouseUsrBiz.update((ScmInvWareHouse)bean.getList().get(0), bean.getList2(), ScmInvWareHouseUsr.FN_WAREHOUSEID, param);
		}
		
	}

}
