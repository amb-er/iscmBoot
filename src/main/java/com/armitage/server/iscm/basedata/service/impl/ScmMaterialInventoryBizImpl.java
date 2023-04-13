
package com.armitage.server.iscm.basedata.service.impl;

import java.util.HashMap;

import org.apache.commons.lang.StringUtils;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.util.BeanUtil;
import com.armitage.server.common.util.CalendarUtil;
import com.armitage.server.common.util.JSONUtils;
import com.armitage.server.iscm.basedata.dao.ScmMaterialInventoryDAO;
import com.armitage.server.iscm.basedata.model.PubSysBasicInfo;
import com.armitage.server.iscm.basedata.model.ScmMaterialInventory2;
import com.armitage.server.iscm.basedata.model.ScmMaterialUnitRelation2;
import com.armitage.server.iscm.basedata.service.PubSysBasicInfoBiz;
import com.armitage.server.iscm.basedata.service.ScmMaterialInventoryBiz;
import com.armitage.server.iscm.basedata.service.ScmMaterialUnitRelationBiz;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvWareHouse;
import com.armitage.server.iscm.inventorymanage.inventorysetting.service.ScmInvWareHouseBiz;
import com.armitage.server.user.model.Usr;
import com.armitage.server.user.service.UsrBiz;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

@Service("scmMaterialInventoryBiz")
public class ScmMaterialInventoryBizImpl extends BaseBizImpl<ScmMaterialInventory2> implements ScmMaterialInventoryBiz {
	private ScmMaterialUnitRelationBiz scmMaterialUnitRelationBiz;
	private UsrBiz usrBiz;
	private PubSysBasicInfoBiz pubSysBasicInfoBiz;
	private ScmInvWareHouseBiz scmInvWareHouseBiz;

	public void setScmMaterialUnitRelationBiz(ScmMaterialUnitRelationBiz scmMaterialUnitRelationBiz) {
		this.scmMaterialUnitRelationBiz = scmMaterialUnitRelationBiz;
	}

	public void setUsrBiz(UsrBiz usrBiz) {
		this.usrBiz = usrBiz;
	}

	public void setPubSysBasicInfoBiz(PubSysBasicInfoBiz pubSysBasicInfoBiz) {
		this.pubSysBasicInfoBiz = pubSysBasicInfoBiz;
	}

	public void setScmInvWareHouseBiz(ScmInvWareHouseBiz scmInvWareHouseBiz) {
		this.scmInvWareHouseBiz = scmInvWareHouseBiz;
	}

	@Override
	protected void beforeAdd(ScmMaterialInventory2 entity, Param param)
			throws AppException {
		if(entity!=null){
			entity.setCreator(param.getUsrCode());
			entity.setCreateDate(CalendarUtil.getDate(param));
		}
	}

	@Override
	protected void beforeUpdate(ScmMaterialInventory2 oldEntity,
			ScmMaterialInventory2 newEntity, Param param) throws AppException {
		if(newEntity!=null){
			if(StringUtils.isBlank(newEntity.getCreator()))
				newEntity.setCreator(param.getUsrCode());
			if(newEntity.getCreateDate()==null)
				newEntity.setCreateDate(CalendarUtil.getDate(param));
			newEntity.setEditor(param.getUsrCode());
			newEntity.setCreateDate(CalendarUtil.getDate(param));
		}
	}

	@Override
	public ScmMaterialInventory2 selectByItemIdAndOrgUnitNo(long itemId, String orgUnitNo, Param param)	throws AppException {
		HashMap<String, Object> map = new HashMap<>();
		map.put("itemId", itemId);
		map.put("orgUnitNo", orgUnitNo);
		ScmMaterialInventory2 scmMaterialInventory =  ((ScmMaterialInventoryDAO) dao).selectByItemIdAndOrgUnitNo(map);
		if(scmMaterialInventory!=null) {
			setConvertMap(scmMaterialInventory,param);
		}
		return scmMaterialInventory;
	}
	
	private void setConvertMap(ScmMaterialInventory2 scmMaterialInventory, Param param) {
		if(scmMaterialInventory != null){
			if(scmMaterialInventory.getItemId() > 0 && scmMaterialInventory.getUnitId() > 0){
				//库存单位
				ScmMaterialUnitRelation2 scmMaterialUnitRelation = scmMaterialUnitRelationBiz.selectByItemAndUnit(scmMaterialInventory.getItemId(), scmMaterialInventory.getUnitId(), param);
				if(scmMaterialUnitRelation != null){
					scmMaterialInventory.setConvertMap(ScmMaterialInventory2.FN_UNITID, scmMaterialUnitRelation);
				}
			}
			if(scmMaterialInventory.getDefaultWhId()>0) {
				ScmInvWareHouse scmInvWareHouse = scmInvWareHouseBiz.selectDirect(scmMaterialInventory.getDefaultWhId(), param);
				if(scmInvWareHouse!=null) {
					scmMaterialInventory.setConvertMap(ScmMaterialInventory2.FN_DEFAULTWHID, scmInvWareHouse);
				}
			}
			if (StringUtils.isNotBlank(scmMaterialInventory.getCreator())){
				//创建人
				Usr usr = usrBiz.selectByCode(scmMaterialInventory.getCreator(), param);
				if (usr != null) {
					scmMaterialInventory.setConvertMap(ScmMaterialInventory2.FN_CREATOR, usr);
				}
			}
			if (StringUtils.isNotBlank(scmMaterialInventory.getEditor())){
				//修改人
				Usr usr = usrBiz.selectByCode(scmMaterialInventory.getEditor(), param);
				if (usr != null) {
					scmMaterialInventory.setConvertMap(ScmMaterialInventory2.FN_EDITOR, usr);
				}
			}
			if(scmMaterialInventory != null && scmMaterialInventory.getSaleTaxRate()!=null){
				PubSysBasicInfo pubSysBasicInfo = pubSysBasicInfoBiz.selectByTaxRate("TaxRate", scmMaterialInventory.getSaleTaxRate().toString(), null, param);
				if (pubSysBasicInfo != null) {
					scmMaterialInventory.setSaleTaxRateStr(pubSysBasicInfo.getFInfoNo());
					scmMaterialInventory.setConvertMap(ScmMaterialInventory2.FN_SALETAXRATESTR, pubSysBasicInfo);
				}
			}
		}
	}

	@Override
	public ScmMaterialInventory2 updateByInventory(ScmMaterialInventory2 scmMaterialInventory, Param param)
			throws AppException {
		if(scmMaterialInventory!=null) {
			ScmMaterialInventory2 oldScmMaterialInventory = this.selectByItemIdAndOrgUnitNo(scmMaterialInventory.getItemId(),param.getOrgUnitNo(),param);
			ScmMaterialInventory2 seleScmMaterialInventory2 = this.selectByItemIdAndOrgUnitNo(scmMaterialInventory.getItemId(),param.getControlUnitNo(),param);
			boolean flag =true;
			if (seleScmMaterialInventory2 != null) {
				Gson newGson = JSONUtils.newGson();
				String json = newGson.toJson(seleScmMaterialInventory2);
				String json1 = newGson.toJson(scmMaterialInventory);
				if (StringUtils.equals(json, json1)) {
					flag=false;
				}
			}
			boolean exists = true;
			if(oldScmMaterialInventory==null) {
				oldScmMaterialInventory = new ScmMaterialInventory2(true);
				exists = false;
			}
			long id = oldScmMaterialInventory.getId();
			BeanUtil.copyProperties(oldScmMaterialInventory, scmMaterialInventory);
			oldScmMaterialInventory.setId(id);
			if(exists) {
				this.update(oldScmMaterialInventory, param);
			}else {
				if (flag) {
					oldScmMaterialInventory.setOrgUnitNo(param.getOrgUnitNo());
					this.add(oldScmMaterialInventory, param);
				}
			}
			return oldScmMaterialInventory;
		}
		return null;
	}

	@Override
	public ScmMaterialInventory2 selectByItemId(long itemId, String orgUnitNo, String controlUnitNo, Param param)
			throws AppException {
		HashMap<String, Object> map = new HashMap<>();
		map.put("itemId", itemId);
		map.put("orgUnitNo", orgUnitNo);
		map.put("controlUnitNo", controlUnitNo);
		ScmMaterialInventory2 scmMaterialInventory =  ((ScmMaterialInventoryDAO) dao).selectByItemId(map);
		return scmMaterialInventory;
	}
}
