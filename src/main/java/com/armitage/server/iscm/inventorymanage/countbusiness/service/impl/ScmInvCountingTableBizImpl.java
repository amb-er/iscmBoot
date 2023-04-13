package com.armitage.server.iscm.inventorymanage.countbusiness.service.impl;


import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.CommonBean;
import com.armitage.server.common.base.model.Page;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.base.model.QueryParam;
import com.armitage.server.common.base.model.SimpleDataDisplay;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.util.ClassUtils;
import com.armitage.server.common.util.SimpleDataDisplayUtil;
import com.armitage.server.iscm.inventorymanage.countbusiness.dao.ScmInvCountingTableDAO;
import com.armitage.server.iscm.inventorymanage.countbusiness.model.ScmInvCountingTable2;
import com.armitage.server.iscm.inventorymanage.countbusiness.model.ScmInvCountingTableEntry2;
import com.armitage.server.iscm.inventorymanage.countbusiness.service.ScmInvCountingTableBiz;
import com.armitage.server.iscm.inventorymanage.countbusiness.service.ScmInvCountingTableEntryBiz;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCountingTask2;
import com.armitage.server.iscm.inventorymanage.cstbusiness.service.ScmInvCountingTaskBiz;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvWareHouse;
import com.armitage.server.iscm.inventorymanage.inventorysetting.service.ScmInvWareHouseBiz;
import com.armitage.server.system.model.OrgBaseUnit;
import com.armitage.server.system.model.OrgStorage2;
import com.armitage.server.system.service.OrgStorageBiz;
import com.armitage.server.system.service.OrgUnitBiz;
import com.armitage.server.system.util.CodeAutoCalUtil;
import com.armitage.server.user.model.Usr;
import com.armitage.server.user.service.UsrBiz;
import org.springframework.stereotype.Service;

@Service("scmInvCountingTableBiz")
public class ScmInvCountingTableBizImpl extends BaseBizImpl<ScmInvCountingTable2> implements ScmInvCountingTableBiz {
	
	private UsrBiz usrBiz;
	private OrgUnitBiz orgUnitBiz;
	private ScmInvCountingTableEntryBiz scmInvCountingTableEntryBiz;
	private ScmInvWareHouseBiz scmInvWareHouseBiz;
	private ScmInvCountingTaskBiz scmInvCountingTaskBiz;
	private OrgStorageBiz orgStorageBiz;
	
	public void setUsrBiz(UsrBiz usrBiz) {
		this.usrBiz = usrBiz;
	}

	public void setOrgUnitBiz(OrgUnitBiz orgUnitBiz) {
		this.orgUnitBiz = orgUnitBiz;
	}

	public void setScmInvCountingTableEntryBiz(ScmInvCountingTableEntryBiz scmInvCountingTableEntryBiz) {
		this.scmInvCountingTableEntryBiz = scmInvCountingTableEntryBiz;
	}
	
	public void setScmInvWareHouseBiz(ScmInvWareHouseBiz scmInvWareHouseBiz) {
		this.scmInvWareHouseBiz = scmInvWareHouseBiz;
	}

	public void setScmInvCountingTaskBiz(ScmInvCountingTaskBiz scmInvCountingTaskBiz) {
		this.scmInvCountingTaskBiz = scmInvCountingTaskBiz;
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
			page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmInvCountingTable2.class) + "." + ScmInvCountingTable2.FN_ORGUNITNO), 
					new QueryParam((ClassUtils.getFinalModelSimpleName(ScmInvCountingTable2.class) + "." + ScmInvCountingTable2.FN_ORGUNITNO), QueryParam.QUERY_IN, orgunitList.toString()));
		}else{
			page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmInvCountingTable2.class) + "." + ScmInvCountingTable2.FN_ORGUNITNO), 
					new QueryParam((ClassUtils.getFinalModelSimpleName(ScmInvCountingTable2.class) + "." + ScmInvCountingTable2.FN_ORGUNITNO), QueryParam.QUERY_EQ, "0"));
		}
	}

	@Override
	protected void afterSelect(ScmInvCountingTable2 entity, Param param) throws AppException {
		if (entity != null){
			setConvertMap(entity,param);
		}
	}
	
	@Override
	protected void afterFindPage(List list, Page page, Param param)
			throws AppException {
		HashMap<String,Object> cacheMap = new HashMap<String,Object>();
		List<ScmInvCountingTable2> scmInvCountingTableList = list;
		if(scmInvCountingTableList != null && !scmInvCountingTableList.isEmpty()){
			for(ScmInvCountingTable2 scmInvCountingTable : scmInvCountingTableList){
				if (StringUtils.isNotBlank(scmInvCountingTable.getCreator())){
					//制单人
					Usr usr = (Usr) cacheMap.get(ClassUtils.getFinalModelSimpleName(Usr.class)+"_"+scmInvCountingTable.getCreator());
					if(usr==null){
						usr = usrBiz.selectByCode(scmInvCountingTable.getCreator(), param);
						cacheMap.put(ClassUtils.getFinalModelSimpleName(Usr.class)+"_"+scmInvCountingTable.getCreator(),usr);
					}
					if (usr != null) {
						if(scmInvCountingTable.getDataDisplayMap()==null){
							scmInvCountingTable.setDataDisplayMap(new HashMap<String,SimpleDataDisplay>());
						}
						scmInvCountingTable.getDataDisplayMap().put(ScmInvCountingTable2.FN_CREATOR, SimpleDataDisplayUtil.convert(usr));
					}
				}
				if (StringUtils.isNotBlank(scmInvCountingTable.getOrgUnitNo())){
					//库存组织
					OrgBaseUnit orgBaseUnit = (OrgBaseUnit) cacheMap.get(ClassUtils.getFinalModelSimpleName(OrgBaseUnit.class)+"_"+scmInvCountingTable.getOrgUnitNo());
					if(orgBaseUnit==null){
						orgBaseUnit = orgUnitBiz.selectbyOrgNo(scmInvCountingTable.getOrgUnitNo(), param);
						cacheMap.put(ClassUtils.getFinalModelSimpleName(OrgBaseUnit.class)+"_"+scmInvCountingTable.getOrgUnitNo(),orgBaseUnit);
					}
					if (orgBaseUnit != null) {
						scmInvCountingTable.setConvertMap(ScmInvCountingTable2.FN_ORGUNITNO, orgBaseUnit);
					}
				}
			}
		}
	}
	
	@Override
	protected void afterSelect(CommonBean bean, Param param) throws AppException {
		ScmInvCountingTable2 scmInvCountingTable = (ScmInvCountingTable2) bean.getList().get(0);
		if(scmInvCountingTable != null && scmInvCountingTable.getTableId() > 0){
			List<ScmInvCountingTableEntry2> scmInvCountingTableEntryList = scmInvCountingTableEntryBiz.selectByTableId(scmInvCountingTable.getTableId(), param);
			if(scmInvCountingTableEntryList != null && !scmInvCountingTableEntryList.isEmpty()){
				bean.setList2(scmInvCountingTableEntryList);
			}
		}
	}

	
	@Override
	protected void afterAdd(CommonBean bean, Param param) throws AppException {
		ScmInvCountingTable2 scmInvCountingTable = (ScmInvCountingTable2) bean.getList().get(0);
		if(scmInvCountingTable != null && scmInvCountingTable.getTableId() > 0){
			//新增盘点明细
			List<ScmInvCountingTableEntry2> scmInvCountingTableEntryList = bean.getList2();
			if(scmInvCountingTableEntryList != null && !scmInvCountingTableEntryList.isEmpty()){
				int lineId = 1;
				for(ScmInvCountingTableEntry2 scmInvCountingTableEntry : scmInvCountingTableEntryList){
					scmInvCountingTableEntry.setLineId(lineId);
					scmInvCountingTableEntry.setAmt(scmInvCountingTableEntry.getQty().multiply(scmInvCountingTableEntry.getPrice()));
					scmInvCountingTableEntry.setTaxAmt(scmInvCountingTableEntry.getQty().multiply(scmInvCountingTableEntry.getTaxPrice()));
					scmInvCountingTableEntry.setDifferPieQty(scmInvCountingTableEntry.getPieQty().subtract(scmInvCountingTableEntry.getAccountPieQty()));
					scmInvCountingTableEntry.setDifferQty(scmInvCountingTableEntry.getQty().subtract(scmInvCountingTableEntry.getAccountQty()));
					scmInvCountingTableEntry.setDifferAmt(scmInvCountingTableEntry.getAmt().subtract(scmInvCountingTableEntry.getAccountAmt()));
					scmInvCountingTableEntry.setDifferTaxAmt(scmInvCountingTableEntry.getTaxAmt().subtract(scmInvCountingTableEntry.getAccountTaxAmt()));
					scmInvCountingTableEntry.setTableId(scmInvCountingTable.getTableId());
					scmInvCountingTableEntry.setWareHouseId(scmInvCountingTable.getWareHouseId());
					scmInvCountingTableEntryBiz.add(scmInvCountingTableEntry, param);
					lineId = lineId+1;
				}
			}
		}
	}
	
	@Override
	protected void afterUpdate(CommonBean bean, Param param) throws AppException {
		ScmInvCountingTable2 scmInvCountingTable = (ScmInvCountingTable2) bean.getList().get(0);
		if(scmInvCountingTable != null && scmInvCountingTable.getTableId() > 0){
			//更新盘点明细
			List<ScmInvCountingTableEntry2> scmInvCountingTableEntryList = bean.getList2();
			if(scmInvCountingTableEntryList != null && !scmInvCountingTableEntryList.isEmpty()){
				int lineId = 1;
				generateLot(scmInvCountingTable,scmInvCountingTableEntryList,param);
				for(ScmInvCountingTableEntry2 scmInvCountingTableEntry : scmInvCountingTableEntryList){
					scmInvCountingTableEntry.setLineId(lineId);
					scmInvCountingTableEntry.setAmt(scmInvCountingTableEntry.getQty().multiply(scmInvCountingTableEntry.getPrice()));
					scmInvCountingTableEntry.setTaxAmt(scmInvCountingTableEntry.getQty().multiply(scmInvCountingTableEntry.getTaxPrice()));
					scmInvCountingTableEntry.setDifferPieQty(scmInvCountingTableEntry.getPieQty().subtract(scmInvCountingTableEntry.getAccountPieQty()));
					scmInvCountingTableEntry.setDifferQty(scmInvCountingTableEntry.getQty().subtract(scmInvCountingTableEntry.getAccountQty()));
					scmInvCountingTableEntry.setDifferAmt(scmInvCountingTableEntry.getAmt().subtract(scmInvCountingTableEntry.getAccountAmt()));
					scmInvCountingTableEntry.setDifferTaxAmt(scmInvCountingTableEntry.getTaxAmt().subtract(scmInvCountingTableEntry.getAccountTaxAmt()));
					scmInvCountingTableEntry.setTableId(scmInvCountingTable.getTableId());
					scmInvCountingTableEntry.setWareHouseId(scmInvCountingTable.getWareHouseId());
					lineId = lineId+1;
				}
				scmInvCountingTableEntryBiz.update(scmInvCountingTable, scmInvCountingTableEntryList, ScmInvCountingTableEntry2.FN_TABLEID, param);
			}
		}
	}
	
	private void generateLot(ScmInvCountingTable2 scmInvCountingTable,List<ScmInvCountingTableEntry2> scmInvCountingTableEntryList,Param param){
		ScmInvCountingTask2 scmInvCountingTask = scmInvCountingTaskBiz.select(scmInvCountingTable.getTaskId(), param);
		if(scmInvCountingTableEntryList!=null && !scmInvCountingTableEntryList.isEmpty()){
            String addLot = CodeAutoCalUtil.autoAddOne("00");
            for(int i= 0;i<scmInvCountingTableEntryList.size();i++){
            	if(!scmInvCountingTableEntryList.get(i).isUsrAdd()){
            		continue;
            	}
                if(StringUtils.isBlank(scmInvCountingTableEntryList.get(i).getLot())){
                    scmInvCountingTableEntryList.get(i).setLot((scmInvCountingTask.getTaskNo())+"-"+addLot);
                    long itemId = scmInvCountingTableEntryList.get(i).getItemId();
                    String outLot = addLot;
                    for(int j= i+1;j<scmInvCountingTableEntryList.size();j++){
                    	if(StringUtils.isBlank(scmInvCountingTableEntryList.get(j).getLot())){
                    		if(itemId == scmInvCountingTableEntryList.get(j).getItemId()){
                    			outLot = CodeAutoCalUtil.autoAddOne(outLot);
                               	scmInvCountingTableEntryList.get(j).setLot((scmInvCountingTask.getTaskNo())+"-"+outLot);
                            }
                    	}
                    }
                }
            }
        }
	}
	@Override
	protected void afterDelete(ScmInvCountingTable2 entity, Param param) throws AppException {
		if(entity != null && entity.getTableId() > 0){
			//删除盘点明细
			scmInvCountingTableEntryBiz.deleteByTableId(entity.getTableId(), param);
		}
	}

	@Override
	protected void beforeUpdate(CommonBean bean, Param param) throws AppException {
		ScmInvCountingTable2 scmInvCountingTable = (ScmInvCountingTable2) bean.getList().get(0);
		if(scmInvCountingTable != null && scmInvCountingTable.getTaskId() > 0){
			ScmInvCountingTask2 scmInvCountingTask = scmInvCountingTaskBiz.select(scmInvCountingTable.getTaskId(), param);
			if(scmInvCountingTask!=null && !StringUtils.equals(scmInvCountingTask.getStatus(),"B") && StringUtils.contains("O,C", scmInvCountingTask.getStatus())){
				throw new AppException(Message.getMessage(param.getLang(), "iscm.inventorymanage.ScmInvCountingTable.handlecounting.statusError", new String[]{scmInvCountingTask.getTaskNo()}));
			}
		}
	}

	@Override
	public void deleteNotExist(long taskId, Param param) throws AppException {
		if(taskId > 0){
			HashMap<String, Object> map = new HashMap<>();
			map.put("taskId", taskId);
			((ScmInvCountingTableDAO) dao).deleteNotExist(map);
		}
	}

	@Override
	public List<ScmInvCountingTable2> findDifference(long taskId, Param param) throws AppException {
		if(taskId > 0){
			HashMap<String, Object> map = new HashMap<>();
			map.put("taskId", taskId);
			return ((ScmInvCountingTableDAO) dao).findDifference(map);
		}
		return null;
	}

	@Override
	public List<ScmInvCountingTableEntry2> queryCountInvTaskDiff(long taskId,boolean showSum, Param param) throws AppException {
		return scmInvCountingTableEntryBiz.queryCountInvTaskDiff(taskId,showSum,param);
	}

	@Override
	public List<ScmInvCountingTable2> selectByTaskNo(String taskNo,String controlUnitNo, Param param) throws AppException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("controlUnitNo", controlUnitNo);
		map.put("taskNo", taskNo);
		return ((ScmInvCountingTableDAO) dao).selectByTaskNo(map);
	}

	@Override
	public ScmInvCountingTable2 selectByTaskNoAndWhNo(String taskNo,String whNo, String controlUnitNo, Param param) throws AppException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("controlUnitNo", controlUnitNo);
		map.put("taskNo", taskNo);
		map.put("whNo", whNo);
		ScmInvCountingTable2 scmInvCountingTable =  ((ScmInvCountingTableDAO) dao).selectByTaskNoAndWhNo(map);
		if(scmInvCountingTable!=null)
			setConvertMap(scmInvCountingTable,param);
		return scmInvCountingTable;
	}

	private void setConvertMap(ScmInvCountingTable2 scmInvCountingTable,Param param){
		if(scmInvCountingTable!=null){
			if (StringUtils.isNotBlank(scmInvCountingTable.getCreator())){
				//制单人
				Usr usr = usrBiz.selectByCode(scmInvCountingTable.getCreator(), param);
				if (usr != null) {
					scmInvCountingTable.setConvertMap(ScmInvCountingTable2.FN_CREATOR, usr);
					scmInvCountingTable.setCreatorName(usr.getName());
				}
			}
			if (StringUtils.isNotBlank(scmInvCountingTable.getEditor())){
				//修改人
				Usr usr = usrBiz.selectByCode(scmInvCountingTable.getEditor(), param);
				if (usr != null) {
					scmInvCountingTable.setConvertMap(ScmInvCountingTable2.FN_EDITOR, usr);
				}
			}
			if (StringUtils.isNotBlank(scmInvCountingTable.getCountingPerson())){
				//盘点人
				Usr usr = usrBiz.selectByCode(scmInvCountingTable.getCountingPerson(), param);
				if (usr != null) {
					scmInvCountingTable.setConvertMap(ScmInvCountingTable2.FN_COUNTINGPERSON, usr);
					scmInvCountingTable.setCountingPersonName(usr.getName());
				}
			}
			if (StringUtils.isNotBlank(scmInvCountingTable.getCountingAgainPerson())){
				//复盘人
				Usr usr = usrBiz.selectByCode(scmInvCountingTable.getCountingAgainPerson(), param);
				if (usr != null) {
					scmInvCountingTable.setConvertMap(ScmInvCountingTable2.FN_COUNTINGAGAINPERSON, usr);
					scmInvCountingTable.setCountingAgainPersonName(usr.getName());
				}
			}
			if (StringUtils.isNotBlank(scmInvCountingTable.getCountingMonitorer())){
				//监盘人
				Usr usr = usrBiz.selectByCode(scmInvCountingTable.getCountingMonitorer(), param);
				if (usr != null) {
					scmInvCountingTable.setConvertMap(ScmInvCountingTable2.FN_COUNTINGMONITORER, usr);
					scmInvCountingTable.setCountingMonitorerName(usr.getName());

				}
			}
			if(scmInvCountingTable.getWareHouseId()>0){
				ScmInvWareHouse scmInvWareHouse = scmInvWareHouseBiz.selectDirect(scmInvCountingTable.getWareHouseId(), param);
				if(scmInvWareHouse!=null){
					scmInvCountingTable.setConvertMap(ScmInvCountingTable2.FN_WAREHOUSEID, scmInvWareHouse);
					scmInvCountingTable.setWareHouseNo(scmInvWareHouse.getWhNo());
					scmInvCountingTable.setWhName(scmInvWareHouse.getWhName());
				}
			}
		}
	}
}

