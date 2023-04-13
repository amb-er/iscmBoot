package com.armitage.server.iscm.common.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Page;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.base.model.QueryParam;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.util.ClassUtils;
import com.armitage.server.common.util.FormatUtils;
import com.armitage.server.iscm.common.dao.ScmSyncTaskInfoDAO;
import com.armitage.server.iscm.common.model.ScmSyncData;
import com.armitage.server.iscm.common.model.ScmSyncTaskInfo2;
import com.armitage.server.iscm.common.model.ScmSyncTaskInfoAdvQuery;
import com.armitage.server.iscm.common.model.TaskSetting2;
import com.armitage.server.iscm.common.model.TaskSettingDetail2;
import com.armitage.server.iscm.common.service.ScmSyncDataBiz;
import com.armitage.server.iscm.common.service.ScmSyncTaskInfoBiz;
import com.armitage.server.iscm.common.service.TaskSettingBiz;
import com.armitage.server.iscm.common.service.TaskSettingDetailBiz;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvPurInWarehsBill2;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvPurInWarehsBillAdvQuery;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.service.ScmInvPurInWarehsBillBiz;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurOrder2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurOrderAdvQuery;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.service.ScmPurOrderBiz;
import org.springframework.stereotype.Service;

@Service("scmSyncTaskInfoBiz")
public class ScmSyncTaskInfoBizImpl extends BaseBizImpl<ScmSyncTaskInfo2> implements ScmSyncTaskInfoBiz {
	private ScmSyncDataBiz scmSyncDataBiz;
	private TaskSettingBiz taskSettingBiz;
	private ScmPurOrderBiz scmPurOrderBiz;
	private ScmInvPurInWarehsBillBiz scmInvPurInWarehsBillBiz;
	private TaskSettingDetailBiz taskSettingDetailBiz;
	
	public void setScmSyncDataBiz(ScmSyncDataBiz scmSyncDataBiz) {
		this.scmSyncDataBiz = scmSyncDataBiz;
	}

	public void setTaskSettingBiz(TaskSettingBiz taskSettingBiz) {
		this.taskSettingBiz = taskSettingBiz;
	}

	public void setScmPurOrderBiz(ScmPurOrderBiz scmPurOrderBiz) {
		this.scmPurOrderBiz = scmPurOrderBiz;
	}

	public void setScmInvPurInWarehsBillBiz(ScmInvPurInWarehsBillBiz scmInvPurInWarehsBillBiz) {
		this.scmInvPurInWarehsBillBiz = scmInvPurInWarehsBillBiz;
	}

	public void setTaskSettingDetailBiz(TaskSettingDetailBiz taskSettingDetailBiz) {
		this.taskSettingDetailBiz = taskSettingDetailBiz;
	}

	@Override
	public ScmSyncTaskInfo2 selectByScmSyncTaskInfo(ScmSyncTaskInfo2 scmSyncTaskInfo, Param param) throws AppException {
		HashMap<String, Object> map = new HashMap<>();
		map.put("syncDataId", scmSyncTaskInfo.getSyncDataId());
		map.put("orgUnitNo", scmSyncTaskInfo.getOrgUnitNo());
		map.put("taskCode", scmSyncTaskInfo.getTaskCode());
		map.put("taskAction", scmSyncTaskInfo.getTaskAction());
		map.put("taskType", scmSyncTaskInfo.getTaskType());
		map.put("taskOwner", scmSyncTaskInfo.getTaskOwner());
		map.put("taskStatus", scmSyncTaskInfo.getTaskStatus());
		//map.put("taskExecutor", scmSyncTaskInfo.getTaskExecutor());
		return ((ScmSyncTaskInfoDAO) dao).selectByScmSyncTaskInfo(map);
	}

	@Override
	protected void beforeFindPage(Page page, Param param) throws AppException {
		page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmSyncTaskInfo2.class) + "." + ScmSyncTaskInfo2.FN_CONTROLUNITNO), 
				new QueryParam((ClassUtils.getFinalModelSimpleName(ScmSyncTaskInfo2.class) + "." + ScmSyncTaskInfo2.FN_CONTROLUNITNO), QueryParam.QUERY_EQ, param.getControlUnitNo()));
		page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmSyncTaskInfo2.class) + "." + ScmSyncTaskInfo2.FN_PRODUCTCODE), 
				new QueryParam((ClassUtils.getFinalModelSimpleName(ScmSyncTaskInfo2.class) + "." + ScmSyncTaskInfo2.FN_PRODUCTCODE), QueryParam.QUERY_EQ, param.getProductCode()));
	}

	@Override
	public void disableTask(ScmSyncTaskInfo2 scmSyncTaskInfo, Param param) throws AppException {
		if(scmSyncTaskInfo!=null) {
			scmSyncTaskInfo = (ScmSyncTaskInfo2) this.select(scmSyncTaskInfo, param);
			scmSyncTaskInfo.setTaskStatus("C");
			this.updateDirect(scmSyncTaskInfo, param);
		}
	}

	@Override
	protected void beforeAdd(ScmSyncTaskInfo2 entity, Param param) throws AppException {
		if(StringUtils.contains("M,B", entity.getTaskSource())) {
			//手工新增任务且指定新增对象ID时
			if(StringUtils.equalsIgnoreCase(entity.getTaskAction(), "Push")) {
				ScmSyncData scmSyncData = scmSyncDataBiz.addBySyncTaskInfo(entity,param);
				entity.setSyncDataId(scmSyncData.getId());
			}
			entity.setTaskOwner(param.getUsrCode());
			Page page = new Page();
			page.setModelClass(TaskSetting2.class);
			page.setShowCount(Integer.MAX_VALUE);
			page.setSqlCondition("TaskCode.code='SCM_ESP'");
			List<TaskSetting2> taskSettingList = taskSettingBiz.findPage(page, param);
			int seconds=0;
			if(taskSettingList!=null && !taskSettingList.isEmpty()) {
				seconds = taskSettingList.get(0).getCycleTime();
			}else {
				seconds=300;
			}
			Date date = new Date();
			date.setTime(date.getTime() + seconds*1000);
			entity.setLogtime(date);
		}
	}

	@Override
	public List queryForBatchTask(ScmSyncTaskInfo2 scmSyncTaskInfo, Param param) throws AppException {
		if(StringUtils.equals("PurOrder",scmSyncTaskInfo.getBillType())){
			//采购订单
			ScmPurOrderAdvQuery scmPurOrderAdvQuery = new ScmPurOrderAdvQuery();
			scmPurOrderAdvQuery.setVendorId(scmSyncTaskInfo.getVendorId());
			scmPurOrderAdvQuery.setCreateDateFrom(scmSyncTaskInfo.getBegDate());
			scmPurOrderAdvQuery.setCreateDateTo(scmSyncTaskInfo.getEndDate());
			scmPurOrderAdvQuery.setPoNo(scmSyncTaskInfo.getBillNo());
			scmPurOrderAdvQuery.setOrgUnitNo(scmSyncTaskInfo.getOrgUnitNo());
			Page page = new Page();
			page.setModelClass(ScmPurOrder2.class);
			page.setShowCount(Integer.MAX_VALUE);
			page.setModel(scmPurOrderAdvQuery);
			List<ScmPurOrder2> scmPurOrderList = scmPurOrderBiz.findPage(page, param);
			if(scmPurOrderList!=null && !scmPurOrderList.isEmpty()) {
				for(ScmPurOrder2 scmPurOrder:scmPurOrderList) {
					scmPurOrder.setChoosed(true);
				}
			}
			return scmPurOrderList;
		}else {
			//采购入库
			ScmInvPurInWarehsBillAdvQuery scmInvPurInWarehsBillAdvQuery = new ScmInvPurInWarehsBillAdvQuery();
			scmInvPurInWarehsBillAdvQuery.setVendorId(scmSyncTaskInfo.getVendorId());
			scmInvPurInWarehsBillAdvQuery.setCreateDateFrom(scmSyncTaskInfo.getBegDate());
			scmInvPurInWarehsBillAdvQuery.setCreateDateTo(scmSyncTaskInfo.getEndDate());
			scmInvPurInWarehsBillAdvQuery.setWrNo(scmSyncTaskInfo.getBillNo());
			scmInvPurInWarehsBillAdvQuery.setOrgUnitNo(scmSyncTaskInfo.getOrgUnitNo());
			Page page = new Page();
			page.setModelClass(ScmInvPurInWarehsBill2.class);
			page.setShowCount(Integer.MAX_VALUE);
			page.setModel(scmInvPurInWarehsBillAdvQuery);
			List<ScmInvPurInWarehsBill2> scmInvPurInWarehsBillList = scmInvPurInWarehsBillBiz.findPage(page, param);
			if(scmInvPurInWarehsBillList!=null && !scmInvPurInWarehsBillList.isEmpty()) {
				for(ScmInvPurInWarehsBill2 scmInvPurInWarehsBill:scmInvPurInWarehsBillList) {
					scmInvPurInWarehsBill.setChoosed(true);
				}
			}
			return scmInvPurInWarehsBillList;
		}
	}

	@Override
	public void generateSyncData(ScmSyncTaskInfo2 scmSyncTaskInfo, List list, Param param) throws AppException {
		if(scmSyncTaskInfo!=null && list!=null && !list.isEmpty()) {
			TaskSettingDetail2 taskSettingDetail=null;
			if(list.get(0) instanceof ScmPurOrder2) {
				//采购订单初始数据生成
				taskSettingDetail = taskSettingDetailBiz.selectByTaskObject("purOrderPush", param.getControlUnitNo(), param);
				if(list!=null && !list.isEmpty()) {
					for(ScmPurOrder2 scmPurOrder:(List<ScmPurOrder2>)list) {
						ScmSyncTaskInfo2 scmSyncTaskInfo2 = new ScmSyncTaskInfo2(true);
						scmSyncTaskInfo2.setBizCode("orgPur");
						scmSyncTaskInfo2.setOrgUnitNo(scmPurOrder.getOrgUnitNo());
						scmSyncTaskInfo2.setTaskCode(scmSyncTaskInfo.getTaskCode());
						scmSyncTaskInfo2.setTaskAction(taskSettingDetail.getInteractionMode());
						scmSyncTaskInfo2.setTaskType(taskSettingDetail.getTaskObject());
						scmSyncTaskInfo2.setProductCode(taskSettingDetail.getProductCode());
						scmSyncTaskInfo2.setTaskOwner(taskSettingDetail.getChannel());
						scmSyncTaskInfo2.setTaskStatus("W");
						scmSyncTaskInfo2.setControlUnitNo(param.getControlUnitNo());
						scmSyncTaskInfo2.setSyncDataId(scmPurOrder.getId());
						scmSyncTaskInfo2.setCreateTime(new Date());
						scmSyncTaskInfo2.setTaskSource("B");
						this.add(scmSyncTaskInfo2, param);
					}
				}
			}else if(list.get(0) instanceof ScmInvPurInWarehsBill2) {
				//采购入库
				taskSettingDetail = taskSettingDetailBiz.selectByTaskObject("invPurInWarehsBillPush", param.getControlUnitNo(), param);
				for(ScmInvPurInWarehsBill2 scmInvPurInWarehsBill:(List<ScmInvPurInWarehsBill2>)list) {
					ScmSyncTaskInfo2 scmSyncTaskInfo2 = new ScmSyncTaskInfo2(true);
					scmSyncTaskInfo2.setBizCode("orgInv");
					scmSyncTaskInfo2.setOrgUnitNo(scmInvPurInWarehsBill.getOrgUnitNo());
					scmSyncTaskInfo2.setTaskCode(scmSyncTaskInfo.getTaskCode());
					scmSyncTaskInfo2.setTaskAction(taskSettingDetail.getInteractionMode());
					scmSyncTaskInfo2.setTaskType(taskSettingDetail.getTaskObject());
					scmSyncTaskInfo2.setProductCode(taskSettingDetail.getProductCode());
					scmSyncTaskInfo2.setTaskOwner(taskSettingDetail.getChannel());
					scmSyncTaskInfo2.setTaskStatus("W");
					scmSyncTaskInfo2.setControlUnitNo(param.getControlUnitNo());
					scmSyncTaskInfo2.setSyncDataId(scmInvPurInWarehsBill.getWrId());
					scmSyncTaskInfo2.setCreateTime(new Date());
					scmSyncTaskInfo2.setTaskSource("B");
					this.add(scmSyncTaskInfo2, param);
				}
			}
		}
	}

	@Override
	protected void afterFindPage(List list, Page page, Param param) throws AppException {
		if(list!=null && !list.isEmpty()) {
			HashMap<String,TaskSetting2> map = new HashMap<String,TaskSetting2>();
			for(ScmSyncTaskInfo2 scmSyncTaskInfo:(List<ScmSyncTaskInfo2>)list) {
				TaskSetting2 taskSetting = null;
				if(map.containsKey("SCM_ESP")) {
					taskSetting = map.get("SCM_ESP");
				}else {
					Page page2 = new Page();
					page2.setModelClass(TaskSetting2.class);
					page2.setShowCount(Integer.MAX_VALUE);
					page2.setSqlCondition("TaskCode.code='SCM_ESP'");
					List<TaskSetting2> taskSettingList = taskSettingBiz.findPage(page2, param);
					if(taskSettingList!=null && !taskSettingList.isEmpty()) {
						taskSetting = taskSettingList.get(0);
						map.put("SCM_ESP",taskSetting);
					}
				}
				if (taskSetting != null) {
					scmSyncTaskInfo.setCycleTime(taskSetting.getCycleTime());
				}
			}
		}
	}

	@Override
	protected void afterSelect(ScmSyncTaskInfo2 entity, Param param) throws AppException {
		if(entity!=null) {
			Page page2 = new Page();
			page2.setModelClass(TaskSetting2.class);
			page2.setShowCount(Integer.MAX_VALUE);
			page2.setSqlCondition("TaskCode.code='SCM_ESP'");
			List<TaskSetting2> taskSettingList = taskSettingBiz.findPage(page2, param);
			if(taskSettingList!=null && !taskSettingList.isEmpty()) {
				entity.setCycleTime(taskSettingList.get(0).getCycleTime());
			}
		}
	}

	@Override
	public void doAdvQuery(Page page, Param param) {
		if (page.getModel() != null) {
			if (page.getModel() instanceof ScmSyncTaskInfoAdvQuery) {
				ScmSyncTaskInfoAdvQuery scmSyncTaskInfoAdvQuery = (ScmSyncTaskInfoAdvQuery) page.getModel();
				if(StringUtils.isNotBlank(scmSyncTaskInfoAdvQuery.getTaskStatus())){
					page.getParam().put(ScmSyncTaskInfo2.FN_TASKSTATUS,new QueryParam(ScmSyncTaskInfo2.FN_TASKSTATUS, QueryParam.QUERY_EQ,scmSyncTaskInfoAdvQuery.getTaskStatus()));
				}
				if(StringUtils.isNotBlank(scmSyncTaskInfoAdvQuery.getTaskType())){
					page.getParam().put(ScmSyncTaskInfo2.FN_TASKTYPE,new QueryParam(ScmSyncTaskInfo2.FN_TASKTYPE, QueryParam.QUERY_EQ,scmSyncTaskInfoAdvQuery.getTaskType()));
				}
				if(scmSyncTaskInfoAdvQuery.getEndDate() != null) {
					Calendar ca = Calendar.getInstance();
                    ca.setTime(scmSyncTaskInfoAdvQuery.getEndDate());
                    ca.add(Calendar.DATE, (+1));
                    scmSyncTaskInfoAdvQuery.setEndDate(ca.getTime());
				}
				if(scmSyncTaskInfoAdvQuery.getBegDate()!=null){
					if(scmSyncTaskInfoAdvQuery.getEndDate()!=null) {
						page.getParam().put(ScmSyncTaskInfo2.FN_CREATETIME,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmSyncTaskInfo2.class) + "." +ScmSyncTaskInfo2.FN_CREATETIME, QueryParam.QUERY_BETWEEN,FormatUtils.fmtDate(scmSyncTaskInfoAdvQuery.getBegDate()),FormatUtils.fmtDate(scmSyncTaskInfoAdvQuery.getEndDate())));
					}else {
						page.getParam().put(ScmSyncTaskInfo2.FN_CREATETIME,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmSyncTaskInfo2.class) + "." +ScmSyncTaskInfo2.FN_CREATETIME, QueryParam.QUERY_GE,FormatUtils.fmtDate(scmSyncTaskInfoAdvQuery.getBegDate())));
					}
				}else if(scmSyncTaskInfoAdvQuery.getEndDate()!=null) {
					page.getParam().put(ScmSyncTaskInfo2.FN_CREATETIME,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmSyncTaskInfo2.class) + "." +ScmSyncTaskInfo2.FN_CREATETIME, QueryParam.QUERY_LE,FormatUtils.fmtDate(scmSyncTaskInfoAdvQuery.getEndDate())));
				}
			}
		}
	}
}
