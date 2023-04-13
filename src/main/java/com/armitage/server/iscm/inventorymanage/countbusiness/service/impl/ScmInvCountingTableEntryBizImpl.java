package com.armitage.server.iscm.inventorymanage.countbusiness.service.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.util.ClassUtils;
import com.armitage.server.iscm.basedata.model.PubSysBasicInfo;
import com.armitage.server.iscm.basedata.model.ScmMaterial2;
import com.armitage.server.iscm.basedata.model.ScmMaterialGroup;
import com.armitage.server.iscm.basedata.model.ScmMeasureUnit;
import com.armitage.server.iscm.basedata.service.PubSysBasicInfoBiz;
import com.armitage.server.iscm.basedata.service.ScmMaterialBiz;
import com.armitage.server.iscm.basedata.service.ScmMaterialGroupBiz;
import com.armitage.server.iscm.basedata.service.ScmMeasureUnitBiz;
import com.armitage.server.iscm.inventorymanage.countbusiness.dao.ScmInvCountingTableEntryDAO;
import com.armitage.server.iscm.inventorymanage.countbusiness.model.ScmInvCountingListMaterial2;
import com.armitage.server.iscm.inventorymanage.countbusiness.model.ScmInvCountingListMaterialGroup2;
import com.armitage.server.iscm.inventorymanage.countbusiness.model.ScmInvCountingTableEntry2;
import com.armitage.server.iscm.inventorymanage.countbusiness.service.ScmInvCountingListMaterialBiz;
import com.armitage.server.iscm.inventorymanage.countbusiness.service.ScmInvCountingListMaterialGroupBiz;
import com.armitage.server.iscm.inventorymanage.countbusiness.service.ScmInvCountingTableEntryBiz;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCountingTask2;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvWareHouse;
import com.armitage.server.iscm.inventorymanage.inventorysetting.service.ScmInvWareHouseBiz;
import com.armitage.server.system.model.OrgStorage2;
import com.armitage.server.system.service.OrgStorageBiz;
import org.springframework.stereotype.Service;

@Service("scmInvCountingTableEntryBiz")
public class ScmInvCountingTableEntryBizImpl extends BaseBizImpl<ScmInvCountingTableEntry2> implements ScmInvCountingTableEntryBiz {
	private ScmInvWareHouseBiz scmInvWareHouseBiz;
	private ScmMaterialBiz scmMaterialBiz;
	private ScmMeasureUnitBiz scmMeasureUnitBiz;
	private OrgStorageBiz orgStorageBiz;
	private PubSysBasicInfoBiz pubSysBasicInfoBiz;
	private ScmInvCountingListMaterialGroupBiz scmInvCountingListMaterialGroupBiz;
	private ScmMaterialGroupBiz scmMaterialGroupBiz;
	private ScmInvCountingListMaterialBiz scmInvCountingListMaterialBiz;
	
	public void setScmInvWareHouseBiz(ScmInvWareHouseBiz scmInvWareHouseBiz) {
		this.scmInvWareHouseBiz = scmInvWareHouseBiz;
	}

	public void setScmMaterialBiz(ScmMaterialBiz scmMaterialBiz) {
		this.scmMaterialBiz = scmMaterialBiz;
	}

	public void setScmMeasureUnitBiz(ScmMeasureUnitBiz scmMeasureUnitBiz) {
		this.scmMeasureUnitBiz = scmMeasureUnitBiz;
	}
	
    public void setOrgStorageBiz(OrgStorageBiz orgStorageBiz) {
        this.orgStorageBiz = orgStorageBiz;
    }

    public void setPubSysBasicInfoBiz(PubSysBasicInfoBiz pubSysBasicInfoBiz) {
		this.pubSysBasicInfoBiz = pubSysBasicInfoBiz;
	}

	public void setScmInvCountingListMaterialGroupBiz(
			ScmInvCountingListMaterialGroupBiz scmInvCountingListMaterialGroupBiz) {
		this.scmInvCountingListMaterialGroupBiz = scmInvCountingListMaterialGroupBiz;
	}

	public void setScmMaterialGroupBiz(ScmMaterialGroupBiz scmMaterialGroupBiz) {
		this.scmMaterialGroupBiz = scmMaterialGroupBiz;
	}

	public void setScmInvCountingListMaterialBiz(ScmInvCountingListMaterialBiz scmInvCountingListMaterialBiz) {
		this.scmInvCountingListMaterialBiz = scmInvCountingListMaterialBiz;
	}

	@Override
	public List<ScmInvCountingTableEntry2> selectByTableId(long tableId, Param param) throws AppException {
		if(tableId > 0){
			HashMap<String, Object> map = new HashMap<>();
			map.put("tableId", tableId);
			List<ScmInvCountingTableEntry2> scmInvCountingTableEntryList = ((ScmInvCountingTableEntryDAO) dao).selectByTableId(map);
			int lineId = 1;
			for (ScmInvCountingTableEntry2 scmInvCountingTableEntry : scmInvCountingTableEntryList) {
				scmInvCountingTableEntry.setLineId(lineId);
				lineId = lineId+1;
			    setConvertMap(scmInvCountingTableEntry, param);
            }
			return scmInvCountingTableEntryList;
		}
		return null;
	}
    
    @Override
    protected void afterSelect(ScmInvCountingTableEntry2 entity, Param param) throws AppException {
        if(entity != null){
            setConvertMap(entity,param);
        }
    }

	@Override
	public void deleteByTableId(long tableId, Param param) throws AppException {
		if(tableId > 0){
			HashMap<String, Object> map = new HashMap<>();
			map.put("tableId", tableId);
			((ScmInvCountingTableEntryDAO) dao).deleteByTableId(map);
		}
	}

	@Override
	public void addByItemIdList(long taskId, String itemIdList, boolean genAccQty, boolean genTableForZero, Param param) throws AppException {
		if(StringUtils.isNotBlank(itemIdList)){
			HashMap<String, Object> map = new HashMap<>();
			map.put("itemIdList", itemIdList);
			map.put("genAccQty", genAccQty);
			map.put("genTableForZero", genTableForZero);
			map.put("taskId", taskId);
			((ScmInvCountingTableEntryDAO) dao).addByItemIdList(map);
		}
	}

	@Override
	public List<ScmInvCountingTableEntry2> selectByTaskId(long taskId, Param param) throws AppException {
		if(taskId > 0){
			HashMap<String, Object> map = new HashMap<>();
			map.put("taskId", taskId);
			return ((ScmInvCountingTableEntryDAO) dao).selectByTaskId(map);
		}
		return null;
	}

	@Override
	public int checkAccount(long taskId, Param param) throws AppException {
		if(taskId > 0){
			return ((ScmInvCountingTableEntryDAO) dao).checkAccount(taskId);
		}
		return 0;
	}

	@Override
	public List<ScmInvCountingTableEntry2> queryCountInvTaskDiff(long taskId,boolean showSum, Param param) throws AppException {
		if(taskId > 0){
			HashMap<String, Object> map = new HashMap<>();
			map.put("taskId", taskId);
			map.put("showSum", (showSum?"1":"0"));
			List<ScmInvCountingTableEntry2> scmInvCountingTableEntryList = ((ScmInvCountingTableEntryDAO) dao).queryCountInvTaskDiff(map);
			if(scmInvCountingTableEntryList!=null && !scmInvCountingTableEntryList.isEmpty()){
				for(ScmInvCountingTableEntry2 scmInvCountingTableEntry:scmInvCountingTableEntryList){
					setConvertMap(scmInvCountingTableEntry,param);
				}
			}
			return scmInvCountingTableEntryList;
		}
		return null;
	}

	@Override
	public List<ScmInvCountingTableEntry2> selectForOtherInWh(long taskId, Param param) throws AppException {
		if(taskId > 0){
			HashMap<String, Object> map = new HashMap<>();
			map.put("taskId", taskId);
			return ((ScmInvCountingTableEntryDAO) dao).selectForOtherInWh(map);
		}
		return null;
	}

	@Override
	public List<ScmInvCountingTableEntry2> selectForOtherOutWh(long taskId, Param param) throws AppException {
		if(taskId > 0){
			HashMap<String, Object> map = new HashMap<>();
			map.put("taskId", taskId);
			return ((ScmInvCountingTableEntryDAO) dao).selectForOtherOutWh(map);
		}
		return null;
	}

	@Override
	public void updateLot(long taskId, Param param) throws AppException {
		if(taskId > 0){
			HashMap<String, Object> map = new HashMap<>();
			map.put("taskId", taskId);
			((ScmInvCountingTableEntryDAO) dao).updateLot(map);
		}
	}

	private void setConvertMap(ScmInvCountingTableEntry2 scmInvCountingTableEntry,Param param){
	    HashMap<String,Object> cacheMap = new HashMap<String,Object>();
		if(scmInvCountingTableEntry!=null){
			if(scmInvCountingTableEntry.getWareHouseId()>0){
				ScmInvWareHouse scmInvWareHouse = scmInvWareHouseBiz.selectDirect(scmInvCountingTableEntry.getWareHouseId(), param);
				if(scmInvWareHouse!=null){
					scmInvCountingTableEntry.setWareHouseNo(scmInvWareHouse.getWhNo());
					scmInvCountingTableEntry.setWareHouseName(scmInvWareHouse.getWhName());
				}
			}
			if(scmInvCountingTableEntry.getItemId()>0){
				ScmMaterial2 scmMaterial = scmMaterialBiz.selectDirect(scmInvCountingTableEntry.getItemId(), param);
				if(scmMaterial!=null){
					scmInvCountingTableEntry.setItemNo(scmMaterial.getItemNo());
					scmInvCountingTableEntry.setItemName(scmMaterial.getItemName());
					scmInvCountingTableEntry.setSpec(scmMaterial.getSpec());
				}
			}
			if(scmInvCountingTableEntry.getUnit()>0){
				ScmMeasureUnit scmMeasureUnit = scmMeasureUnitBiz.selectDirect(scmInvCountingTableEntry.getUnit(), param);
				if(scmMeasureUnit!=null)
					scmInvCountingTableEntry.setUnitName(scmMeasureUnit.getUnitName());
			}
			if(scmInvCountingTableEntry.getPieUnit()>0){
				ScmMeasureUnit scmMeasureUnit = scmMeasureUnitBiz.selectDirect(scmInvCountingTableEntry.getPieUnit(), param);
				if(scmMeasureUnit!=null)
					scmInvCountingTableEntry.setPieUnitName(scmMeasureUnit.getUnitName());
			}
			if(StringUtils.isNotBlank(scmInvCountingTableEntry.getOrgUnitNo())){
			    OrgStorage2 orgStorage = (OrgStorage2) cacheMap.get(ClassUtils.getFinalModelSimpleName(OrgStorage2.class)+"_"+scmInvCountingTableEntry.getOrgUnitNo());
			    if (orgStorage == null) {
			        orgStorage = orgStorageBiz.selectByOrgUnitNo(scmInvCountingTableEntry.getOrgUnitNo(), param);
			        cacheMap.put(ClassUtils.getFinalModelSimpleName(OrgStorage2.class)+"_"+scmInvCountingTableEntry.getOrgUnitNo(),orgStorage);
                }
			    if (orgStorage != null) {
			        scmInvCountingTableEntry.setConvertMap(ScmInvCountingTableEntry2.FN_ORGUNITNO, orgStorage);
                }
            }
            //税率
			if (scmInvCountingTableEntry.getTaxRate() !=null) {
				PubSysBasicInfo pubSysBasicInfo = pubSysBasicInfoBiz.selectByTaxRate("TaxRate", scmInvCountingTableEntry.getTaxRate().toString(), null, param);
	            if (pubSysBasicInfo != null) {
	            	scmInvCountingTableEntry.setTaxRateStr(pubSysBasicInfo.getFInfoNo());
	            	scmInvCountingTableEntry.setConvertMap(ScmInvCountingTableEntry2.FN_TAXRATESTR, pubSysBasicInfo);
	            }
			}
		}
	}

	@Override
	public List<ScmInvCountingTableEntry2> findAccount(long taskId, String orgUnitNo, long wareHouseId, Param param)
			throws AppException {
		if(taskId > 0){
			HashMap<String, Object> map = new HashMap<>();
			map.put("taskId", taskId);
			map.put("orgUnitNo", orgUnitNo);
			map.put("wareHouseId",wareHouseId);
			return ((ScmInvCountingTableEntryDAO) dao).findAccount(map);
		}
		return null;
	}

	@Override
	public void refreshAccount(ScmInvCountingTask2 scmInvCountingTask, Param param) throws AppException {
		if(scmInvCountingTask!=null && scmInvCountingTask.getTaskId()>0){
			HashMap<String, Object> map = new HashMap<>();
			map.put("taskId", scmInvCountingTask.getTaskId());
			((ScmInvCountingTableEntryDAO) dao).refreshAccount(map);
			List<ScmInvCountingListMaterial2> scmInvCountingListMaterialList = scmInvCountingListMaterialBiz
					.selectByTaskId(scmInvCountingTask.getTaskId(), param);
			StringBuffer itemIdList = new StringBuffer("");
			if (scmInvCountingTask.isAssignItem()) {
				if (scmInvCountingListMaterialList != null && !scmInvCountingListMaterialList.isEmpty()) {
					for (ScmInvCountingListMaterial2 scmInvCountingListMaterial : scmInvCountingListMaterialList) {
						if (scmInvCountingListMaterial.isSelectOrExclude()) {
							if (StringUtils.isNotBlank(itemIdList.toString())) {
								itemIdList.append(",");
							}
							itemIdList.append(scmInvCountingListMaterial.getItemId());
						}
					}
				}
			}
			if(!scmInvCountingTask.isAssignItem() || StringUtils.isNotBlank(itemIdList.toString())) {
				if (scmInvCountingTask.isAssignItemGroup() && StringUtils.isBlank(itemIdList.toString())) {
					ArrayList<ScmMaterialGroup> scmMaterialGroupList = new ArrayList();
					StringBuffer itemGroupId = new StringBuffer();
					List<ScmInvCountingListMaterialGroup2> scmInvCountingListMaterialGroupList = scmInvCountingListMaterialGroupBiz.selectByTaskId(scmInvCountingTask.getTaskId(), param);
					if (scmInvCountingListMaterialGroupList != null && !scmInvCountingListMaterialGroupList.isEmpty() && scmInvCountingTask.isAssignItemGroup()) {
						for (ScmInvCountingListMaterialGroup2 scmInvCountingListMaterialGroup2 : scmInvCountingListMaterialGroupList) {
							scmMaterialGroupList.addAll(scmMaterialGroupBiz.findChild(scmInvCountingListMaterialGroup2.getClassId(), param)); 
						}
					}
					if (scmMaterialGroupList != null && !scmMaterialGroupList.isEmpty()) {
						for (ScmMaterialGroup scmMaterialGroup : scmMaterialGroupList) {
							if (StringUtils.isNotBlank(itemGroupId.toString())) {
								itemGroupId.append(",");
							}
							itemGroupId.append(scmMaterialGroup.getId());
						}
					}
					map.put("classId", itemGroupId.toString());
				}
				if(StringUtils.isNotBlank(itemIdList.toString())){
					map.put("itemIds", itemIdList.toString());
				}
				((ScmInvCountingTableEntryDAO) dao).addAccount(map);
			}
		}
	}

	@Override
	protected void beforeAdd(ScmInvCountingTableEntry2 entity, Param param) throws AppException {
		if(entity!=null) {
			entity.setOrigAccQty(entity.getAccountQty());
		}
	}

	@Override
	protected void beforeUpdate(ScmInvCountingTableEntry2 oldEntity, ScmInvCountingTableEntry2 newEntity, Param param)
			throws AppException {
		if(newEntity!=null) {
			newEntity.setOrigAccQty(newEntity.getAccountQty());
		}
	}
}

