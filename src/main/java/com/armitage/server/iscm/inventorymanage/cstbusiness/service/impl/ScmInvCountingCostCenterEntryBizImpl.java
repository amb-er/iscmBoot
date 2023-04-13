package com.armitage.server.iscm.inventorymanage.cstbusiness.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.basedata.model.PubSysBasicInfo;
import com.armitage.server.iscm.basedata.model.ScmIdleCause;
import com.armitage.server.iscm.basedata.model.ScmMaterialGroup;
import com.armitage.server.iscm.basedata.service.PubSysBasicInfoBiz;
import com.armitage.server.iscm.basedata.service.ScmIdleCauseBiz;
import com.armitage.server.iscm.basedata.service.ScmMaterialGroupBiz;
import com.armitage.server.iscm.inventorymanage.countbusiness.model.ScmInvCountingListMaterial2;
import com.armitage.server.iscm.inventorymanage.countbusiness.model.ScmInvCountingListMaterialGroup2;
import com.armitage.server.iscm.inventorymanage.countbusiness.service.ScmInvCountingListMaterialBiz;
import com.armitage.server.iscm.inventorymanage.countbusiness.service.ScmInvCountingListMaterialGroupBiz;
import com.armitage.server.iscm.inventorymanage.cstbusiness.dao.ScmInvCountingCostCenterEntryDAO;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCostConsumeEntry2;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCountingCostCenterEntry2;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCountingTask2;
import com.armitage.server.iscm.inventorymanage.cstbusiness.service.ScmInvCountingCostCenterEntryBiz;
import org.springframework.stereotype.Service;

@Service("scmInvCountingCostCenterEntryBiz")
public class ScmInvCountingCostCenterEntryBizImpl extends BaseBizImpl<ScmInvCountingCostCenterEntry2> implements ScmInvCountingCostCenterEntryBiz {

	private PubSysBasicInfoBiz pubSysBasicInfoBiz;
	private ScmInvCountingListMaterialGroupBiz scmInvCountingListMaterialGroupBiz;
	private ScmMaterialGroupBiz scmMaterialGroupBiz;
	private ScmInvCountingListMaterialBiz scmInvCountingListMaterialBiz;
	private ScmIdleCauseBiz scmIdleCauseBiz;
	
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

	public void setScmIdleCauseBiz(ScmIdleCauseBiz scmIdleCauseBiz) {
		this.scmIdleCauseBiz = scmIdleCauseBiz;
	}

	@Override
	public List<ScmInvCountingCostCenterEntry2> selectByTableId(long tableId, Param param) throws AppException {
		if(tableId > 0){
			HashMap<String, Object> map = new HashMap<>();
			map.put("tableId", tableId);
			List<ScmInvCountingCostCenterEntry2> scmInvCountingCostCenterEntryList = ((ScmInvCountingCostCenterEntryDAO) dao).selectByTableId(map);
			if(scmInvCountingCostCenterEntryList != null && !scmInvCountingCostCenterEntryList.isEmpty()){
				int lineId = 1;
				for (ScmInvCountingCostCenterEntry2 scmInvCountingCostCenterEntry : scmInvCountingCostCenterEntryList) {
					scmInvCountingCostCenterEntry.setLineId(lineId);
					lineId = lineId+1;
				    setConvertMap(scmInvCountingCostCenterEntry, param);
	            }
				return scmInvCountingCostCenterEntryList;
			}
		}
		return null;
	}
	
	private void setConvertMap(ScmInvCountingCostCenterEntry2 scmInvCountingCostCenterEntry,Param param){
		if(scmInvCountingCostCenterEntry!=null){
            //税率
            PubSysBasicInfo pubSysBasicInfo = pubSysBasicInfoBiz.selectByTaxRate("TaxRate", scmInvCountingCostCenterEntry.getTaxRate().toString(), null, param);
            if (pubSysBasicInfo != null) {
            	scmInvCountingCostCenterEntry.setTaxRateStr(pubSysBasicInfo.getFInfoNo());
            	scmInvCountingCostCenterEntry.setConvertMap(ScmInvCountingCostCenterEntry2.FN_TAXRATESTR, pubSysBasicInfo);
            }
            //闲置原因
            ScmIdleCause scmIdleCause = scmIdleCauseBiz.select(scmInvCountingCostCenterEntry.getIdleCauseId(), param);
            if (scmIdleCause != null) {
				scmInvCountingCostCenterEntry.setIdleCause(scmIdleCause.getName());
				scmInvCountingCostCenterEntry.setConvertMap(ScmInvCountingCostCenterEntry2.FN_IDLECAUSE, scmIdleCause);
			}
		}
	}

	@Override
	public void deleteByTableId(long tableId, Param param) throws AppException {
		if(tableId > 0){
			HashMap<String, Object> map = new HashMap<>();
			map.put("tableId", tableId);
			((ScmInvCountingCostCenterEntryDAO) dao).deleteByTableId(map);
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
			((ScmInvCountingCostCenterEntryDAO) dao).addByItemIdList(map);
		}
	}

	@Override
	public List<ScmInvCountingCostCenterEntry2> selectByTaskId(long taskId, Param param) throws AppException {
		if(taskId > 0){
			HashMap<String, Object> map = new HashMap<>();
			map.put("taskId", taskId);
			return ((ScmInvCountingCostCenterEntryDAO) dao).selectByTaskId(map);
		}
		return null;
	}

	@Override
	public List<ScmInvCountingCostCenterEntry2> queryCountCostTaskDiff(long taskId,boolean showSum, Param param) throws AppException {
		if(taskId > 0){
			HashMap<String, Object> map = new HashMap<>();
			map.put("taskId", taskId);
			map.put("showSum", (showSum?"1":"0"));
			return ((ScmInvCountingCostCenterEntryDAO) dao).queryCountCostTaskDiff(map);
		}
		return null;
	}

	@Override
	public int selectByTaskIdAndFinOrg(long taskId, String finOrgUnitNo, Param param) throws AppException {
		if(taskId > 0 && StringUtils.isNotBlank(finOrgUnitNo)){
			HashMap<String, Object> map = new HashMap<>();
			map.put("taskId", taskId);
			map.put("finOrgUnitNo", finOrgUnitNo);
			return ((ScmInvCountingCostCenterEntryDAO) dao).selectByTaskIdAndFinOrg(map);
		}
		return 0;
	}

	@Override
	public void refreshAccount(ScmInvCountingTask2 scmInvCountingTask, Param param) throws AppException {
		if(scmInvCountingTask!=null && scmInvCountingTask.getTaskId() > 0){
			HashMap<String, Object> map = new HashMap<>();
			map.put("taskId", scmInvCountingTask.getTaskId());
			((ScmInvCountingCostCenterEntryDAO) dao).refreshAccount(map);
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
				((ScmInvCountingCostCenterEntryDAO) dao).addAccount(map);
			}
		}
	}

	@Override
	public int checkAccount(long taskId, Param param) throws AppException {
		if(taskId > 0){
			HashMap<String, Object> map = new HashMap();
			map.put("taskId", taskId);	
			return ((ScmInvCountingCostCenterEntryDAO) dao).checkAccount(map);
		}
		return 0;
	}

	@Override
	protected void beforeAdd(ScmInvCountingCostCenterEntry2 entity, Param param) throws AppException {
		if(entity!=null) {
			entity.setOrigAccQty(entity.getAccountQty());
		}
	}

	@Override
	protected void beforeUpdate(ScmInvCountingCostCenterEntry2 oldEntity, ScmInvCountingCostCenterEntry2 newEntity,
			Param param) throws AppException {
		if(newEntity!=null) {
			newEntity.setOrigAccQty(newEntity.getAccountQty());
		}
	}

	@Override
	public List<ScmInvCountingCostCenterEntry2> selectByIdleCauseId(long id, Param param) throws AppException {
		if(id > 0){
			HashMap<String, Object> map = new HashMap();
			map.put("idleCauseId", id);	
			return ((ScmInvCountingCostCenterEntryDAO) dao).selectByIdleCauseId(map);
		}
		return null;
	}

}
