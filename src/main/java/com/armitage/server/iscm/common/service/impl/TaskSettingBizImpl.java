package com.armitage.server.iscm.common.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.alibaba.druid.util.StringUtils;
import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Page;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.util.CalendarUtil;
import com.armitage.server.iscm.common.dao.TaskSettingDAO;
import com.armitage.server.iscm.common.model.TaskCode;
import com.armitage.server.iscm.common.model.TaskSetting2;
import com.armitage.server.iscm.common.model.TaskSettingDetail;
import com.armitage.server.iscm.common.service.TaskCodeBiz;
import com.armitage.server.iscm.common.service.TaskSettingBiz;
import com.armitage.server.iscm.common.service.TaskSettingDetailBiz;
import com.armitage.server.quartz.job.ScmEspCommonTaskJob;
import com.armitage.server.quartz.job.ScmFbcCommonTaskJob;
import com.armitage.server.quartz.model.ScmSystemTask;
import com.armitage.server.quartz.service.ScmSystemTaskBiz;
import com.armitage.server.quartz.util.QuartzUtil;
import org.springframework.stereotype.Service;

@Service("taskSettingBiz")
public class TaskSettingBizImpl extends BaseBizImpl<TaskSetting2> implements TaskSettingBiz {
	private TaskSettingDetailBiz taskSettingDetailBiz;
	private TaskCodeBiz taskCodeBiz;
	private ScmSystemTaskBiz scmSystemTaskBiz;
	
	public void setTaskSettingDetailBiz(TaskSettingDetailBiz taskSettingDetailBiz) {
		this.taskSettingDetailBiz = taskSettingDetailBiz;
	}

	public void setTaskCodeBiz(TaskCodeBiz taskCodeBiz) {
		this.taskCodeBiz = taskCodeBiz;
	}

	public void setScmSystemTaskBiz(ScmSystemTaskBiz scmSystemTaskBiz) {
		this.scmSystemTaskBiz = scmSystemTaskBiz;
	}

	@Override
	protected HashMap<String, Object> addFindAllPageParam(Page page, HashMap<String, Object> map, Param param) {
		map.put("controlUnitNo", param.getControlUnitNo());
		return map;
	}

	@Override
	protected void beforeAdd(TaskSetting2 entity, Param param) throws AppException {
		TaskSetting2 taskSetting = this.selectByTaskId(entity.getTaskId(),param);
		if(taskSetting!=null)
			throw new AppException("iscm.service.TaskSettingBizImpl.error.taskrepeat");
		entity.setCreator(param.getUsrCode());
		entity.setCreateDate(CalendarUtil.getDate(param));
	}

	@Override
	protected void beforeUpdate(TaskSetting2 oldEntity, TaskSetting2 newEntity, Param param) throws AppException {
		TaskSetting2 taskSetting = this.selectByTaskId(newEntity.getTaskId(),param);
		if(taskSetting!=null && taskSetting.getTaskId()!=newEntity.getTaskId())
			throw new AppException("iscm.service.TaskSettingBizImpl.error.taskrepeat");
		newEntity.setEditor(param.getUsrCode());
		newEntity.setEditDate(CalendarUtil.getDate(param));
	}

	@Override
	protected void afterAdd(TaskSetting2 entity, Param param) throws AppException {
		if(entity!=null) {
			this.setConvertMap(entity,param);
			taskSettingDetailBiz.updateByTaskSetting(entity, param);
			updateSystemTask(entity,param);
		}
	}

	@Override
	protected void afterUpdate(TaskSetting2 oldEntity, TaskSetting2 newEntity, Param param) throws AppException {
		if(newEntity!=null) {
			this.setConvertMap(newEntity,param);
			taskSettingDetailBiz.updateByTaskSetting(newEntity,param);
			updateSystemTask(newEntity,param);
		}
	}

	@Override
	protected void afterSelect(TaskSetting2 entity, Param param) throws AppException {
		List<TaskSettingDetail> taskSettingDetailList = taskSettingDetailBiz.selectBySetId(entity.getId(), param);
		if(taskSettingDetailList!=null && !taskSettingDetailList.isEmpty()) {
			for(TaskSettingDetail taskSettingDetail:taskSettingDetailList) {
				if(StringUtils.equalsIgnoreCase(taskSettingDetail.getTaskObject(),"purOrderPush")){
					entity.setPurOrderFlag(taskSettingDetail.isFlag());
					entity.setTaskCreateMode(taskSettingDetail.getTaskCreateMode());
				}
				if(StringUtils.equalsIgnoreCase(taskSettingDetail.getTaskObject(),"invPurInWarehsBillPush")){
					entity.setInvPurInWarehsFlag(taskSettingDetail.isFlag());
					entity.setTaskCreateMode(taskSettingDetail.getTaskCreateMode());
				}
				if(StringUtils.equalsIgnoreCase(taskSettingDetail.getTaskObject(),"supplierConfirmDataGet")){
					entity.setConfirmInfoFlag(taskSettingDetail.isFlag());
					entity.setTaskCreateMode(taskSettingDetail.getTaskCreateMode());
				}
				if(StringUtils.equalsIgnoreCase(taskSettingDetail.getTaskObject(),"supplierRegInfoGet")){
					entity.setSupplerRegInfoFlag(taskSettingDetail.isFlag());
					entity.setTaskCreateMode(taskSettingDetail.getTaskCreateMode());
				}
				if(StringUtils.equalsIgnoreCase(taskSettingDetail.getTaskObject(),"supplierConfirmRulePush")){
					entity.setConfirmRuleFlag(taskSettingDetail.isFlag());
					entity.setConfirmRuleCreateMode(taskSettingDetail.getTaskCreateMode());
				}
				if(StringUtils.equalsIgnoreCase(taskSettingDetail.getTaskObject(),"purPricePush")){
					entity.setPurPriceFlag(taskSettingDetail.isFlag());
					entity.setTaskCreateMode(taskSettingDetail.getTaskCreateMode());
				}
				if(StringUtils.equalsIgnoreCase(taskSettingDetail.getTaskObject(),"businessQuotationGet")){
					entity.setBusinessQuotationFlag(taskSettingDetail.isFlag());
					entity.setTaskCreateMode(taskSettingDetail.getTaskCreateMode());
				}
				if(StringUtils.equalsIgnoreCase(taskSettingDetail.getTaskObject(),"supplierStatusPush")){
					entity.setSupplierStatusPushFlag(taskSettingDetail.isFlag());
					entity.setSupplierCreateMode(taskSettingDetail.getTaskCreateMode());
					entity.setLotQty2(Integer.parseInt(taskSettingDetail.getExtendedParam2()));
				}
				if(StringUtils.equalsIgnoreCase(taskSettingDetail.getTaskObject(),"qualificationInfoPush")){
					entity.setQualificationInfoPushFlag(taskSettingDetail.isFlag());
					entity.setSupplierCreateMode(taskSettingDetail.getTaskCreateMode());
					entity.setLotQty2(Integer.parseInt(taskSettingDetail.getExtendedParam2()));
				}
				if(StringUtils.equalsIgnoreCase(taskSettingDetail.getTaskObject(),"supplierInfoPull")){
					entity.setSupplierInfoPullFlag(taskSettingDetail.isFlag());
					entity.setSupplierCreateMode(taskSettingDetail.getTaskCreateMode());
					entity.setLotQty2(Integer.parseInt(taskSettingDetail.getExtendedParam2()));
				}
				if(StringUtils.equalsIgnoreCase(taskSettingDetail.getTaskObject(),"qualificationInfoPull")){
					entity.setQualificationInfoPullFlag(taskSettingDetail.isFlag());
					entity.setSupplierCreateMode(taskSettingDetail.getTaskCreateMode());
					entity.setLotQty2(Integer.parseInt(taskSettingDetail.getExtendedParam2()));
				}
				if(StringUtils.equalsIgnoreCase(taskSettingDetail.getTaskObject(),"industryQualifyPush")){
					entity.setIndustryQualifyPushFlag(taskSettingDetail.isFlag());
					entity.setSupplierCreateMode(taskSettingDetail.getTaskCreateMode());
					entity.setLotQty2(Integer.parseInt(taskSettingDetail.getExtendedParam2()));
				}
				if(StringUtils.equalsIgnoreCase(taskSettingDetail.getTaskObject(),"updateFbcItemPrice")){
					entity.setUpdateFbcItemPriceFlag(taskSettingDetail.isFlag());
				}
				if(StringUtils.equalsIgnoreCase(taskSettingDetail.getTaskObject(),"getFbmSalePrice")){
					entity.setGetFbmSalePriceFlag(taskSettingDetail.isFlag());
				}
				if(StringUtils.equalsIgnoreCase(taskSettingDetail.getTaskObject(),"updateFbcCostPrice")){
					entity.setUpdateFbcCostPriceFlag(taskSettingDetail.isFlag());
				}
				if(StringUtils.equalsIgnoreCase(taskSettingDetail.getTaskObject(),"generateCostConsume")){
					entity.setGenerateCostConsumeFlag(taskSettingDetail.isFlag());
				}
				if(StringUtils.equalsIgnoreCase(taskSettingDetail.getTaskObject(),"calcFbcRptData")){
					entity.setCalcFbcRptDataFlag(taskSettingDetail.isFlag());
				}
				if(StringUtils.equalsIgnoreCase(taskSettingDetail.getTaskObject(),"controlUnitPush")){
					entity.setControlUnitPushFlag(taskSettingDetail.isFlag());
					entity.setControlUnitCreateMode(taskSettingDetail.getTaskCreateMode());
					entity.setControlCycleTime(Integer.parseInt(taskSettingDetail.getExtendedParam1()));
				}
			}
		}
		if(StringUtils.isEmpty(entity.getSupplierCreateMode())){
			entity.setSupplierCreateMode("1");
		}
		if(StringUtils.isEmpty(entity.getControlUnitCreateMode())){
			entity.setControlUnitCreateMode("1");
		}
		if(entity.getControlCycleTime() == 0){
			entity.setControlCycleTime(600);
		}
		entity.setDataScope2(entity.getDataScope());
		entity.setControlUnitDataScope(entity.getDataScope());
		this.setConvertMap(entity,param);
	}
	
	@Override
	protected void afterFindPage(List list, Page page, Param param) throws AppException {
		if(list!=null && !list.isEmpty()) {
			for(TaskSetting2 taskSetting:(List<TaskSetting2>)list) {
				this.setConvertMap(taskSetting,param);
			}
		}
	}

	private void setConvertMap(TaskSetting2 taskSetting,Param param){
		if(taskSetting.getTaskId()>0) {
			TaskCode taskCode = taskCodeBiz.selectDirect(taskSetting.getTaskId(), param);
			if(taskCode!=null) {
				taskSetting.setTaskCode(taskCode.getCode());
				taskSetting.setTaskName(taskCode.getName());
				taskSetting.setChannel(taskCode.getChannel());
				taskSetting.setConvertMap(TaskSetting2.FN_TASKCODE, taskCode);
			}
		}
	}

	@Override
	protected void afterDelete(TaskSetting2 entity, Param param) throws AppException {
		this.setConvertMap(entity,param);
		ScmSystemTask systemTask = new ScmSystemTask(true);
		if(StringUtils.equalsIgnoreCase("SCM_ESP", entity.getTaskCode())){
			systemTask.setTaskType("iSCMESPComTask");
		}else if(StringUtils.equalsIgnoreCase("SCM_FBC", entity.getTaskCode())){
			systemTask.setTaskType("iSCMFBCComTask");
		}
		//systemTask.setTaskType("iSCMESPComTask");
		systemTask.setOrgUnitNo(param.getControlUnitNo());
		List<ScmSystemTask> systemTaskList = scmSystemTaskBiz.selectByTask(systemTask,true, param);
		if(systemTaskList != null && !systemTaskList.isEmpty()){
			//更新物料价格更新定时任务
			systemTask = systemTaskList.get(0);
			systemTask.setFlag(false);
			systemTask.setUpdateTime(new Date());
			QuartzUtil quartzUtil = new QuartzUtil();
			quartzUtil.removeJobBySystemTask(systemTask);
			scmSystemTaskBiz.update(systemTask, param);
		}
		taskSettingDetailBiz.delete(taskSettingDetailBiz.selectBySetId(entity.getId(), param),param);
	}
	
	private void updateSystemTask(TaskSetting2 taskSetting, Param param){
		int hour=0,minute=0,second=0;
		if(taskSetting.getMaintainCycleTime()>0) {
			hour = (int)taskSetting.getMaintainCycleTime()/3600;
			minute = (int)(taskSetting.getMaintainCycleTime()-hour*3600)/60;
			second = taskSetting.getMaintainCycleTime()-hour*3600-minute*60;
		}
		String cronExpression="";
		if(second>0) {
			cronExpression = ""+second;
		}else {
			cronExpression = "0";
		}
		if(minute>0) {
			cronExpression = cronExpression+" 0/"+minute;
		}else {
			cronExpression = cronExpression+" *";
		}
		if(hour>0) {
			cronExpression = cronExpression+" 0/"+hour+" * * ?";
		}else {
			cronExpression = cronExpression+" * * * ?";
		}
		ScmSystemTask systemTask = new ScmSystemTask(true);
		if(StringUtils.equalsIgnoreCase("SCM_ESP", taskSetting.getTaskCode())){
			systemTask.setTaskType("iSCMESPComTask");
		}else if(StringUtils.equalsIgnoreCase("SCM_FBC", taskSetting.getTaskCode())){
			systemTask.setTaskType("iSCMFBCComTask");
		}
		//systemTask.setTaskType("iSCMESPComTask");
		systemTask.setOrgUnitNo(param.getControlUnitNo());
		List<ScmSystemTask> systemTaskList = scmSystemTaskBiz.selectByTask(systemTask,true, param);
		if(systemTaskList != null && !systemTaskList.isEmpty()){
			//更新物料价格更新定时任务
			systemTask = systemTaskList.get(0);
			systemTask.setFlag(taskSetting.isStarted());
			if(taskSetting.isStarted()) {
				systemTask.setCronExpression(cronExpression);
			}
			systemTask.setUpdateTime(new Date());
			QuartzUtil quartzUtil = new QuartzUtil();
			quartzUtil.removeJobBySystemTask(systemTask);
			scmSystemTaskBiz.update(systemTask, param);
		}else{
			//新增物料价格更新定时任务
			if(StringUtils.equalsIgnoreCase("SCM_ESP", taskSetting.getTaskCode())){
				systemTask.setTaskName(ScmEspCommonTaskJob.class.getSimpleName());
			}else if(StringUtils.equalsIgnoreCase("SCM_FBC", taskSetting.getTaskCode())){
				systemTask.setTaskName(ScmFbcCommonTaskJob.class.getSimpleName());
			}
			//systemTask.setTaskName(ScmEspCommonTaskJob.class.getSimpleName());
			systemTask.setTaskGroup("iSCM");
			systemTask.setCronExpression(cronExpression);
			if(StringUtils.equalsIgnoreCase("SCM_ESP", taskSetting.getTaskCode())){
				systemTask.setTaskClass(ScmEspCommonTaskJob.class.getName());
			}else if(StringUtils.equalsIgnoreCase("SCM_FBC", taskSetting.getTaskCode())){
				systemTask.setTaskClass(ScmFbcCommonTaskJob.class.getName());
			}
			//systemTask.setTaskClass(ScmEspCommonTaskJob.class.getName());
			systemTask.setFlag(taskSetting.isStarted());
			systemTask.setUpdateTime(new Date());
			systemTask.setControlUnitNo(param.getControlUnitNo());
			scmSystemTaskBiz.add(systemTask, param);
		}
		
	}

	@Override
	public TaskSetting2 selectByTaskId(long taskId, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("taskId", taskId);
		map.put("controlUnitNo", param.getControlUnitNo());
		return ((TaskSettingDAO)dao).selectByTaskId(map);
	}
}
