package com.armitage.server.quartz.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.armitage.server.common.base.model.OperationParam;
import com.armitage.server.common.base.model.Page;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.base.model.QueryParam;
import com.armitage.server.common.base.model.RoutineParam;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.util.AppContextUtil;
import com.armitage.server.common.util.CalendarUtil;
import com.armitage.server.common.util.ClassUtils;
import com.armitage.server.common.util.FormatUtils;
import com.armitage.server.ifbc.basedata.model.ScmPriceUpdSet2;
import com.armitage.server.ifbc.basedata.service.ScmPriceUpdSetBiz;
import com.armitage.server.ifbc.costcard.service.ScmCookCostCardPriceBiz;
import com.armitage.server.ifbc.costcard.service.ScmCostCardPriceBiz;
import com.armitage.server.ifbc.costcard.service.ScmItemCostPriceBiz;
import com.armitage.server.ifbc.rptdata.service.ScmCookStdCostInfoBiz;
import com.armitage.server.ifbc.rptdata.service.ScmDiskStdCostInfoBiz;
import com.armitage.server.ifbc.rptdata.service.ScmFbmSellCookDetailBiz;
import com.armitage.server.ifbc.rptdata.service.ScmFbmSellDetailBiz;
import com.armitage.server.iscm.common.model.ScmSyncTaskInfo2;
import com.armitage.server.iscm.common.model.TaskSettingDetail2;
import com.armitage.server.iscm.common.service.ScmSyncTaskInfoBiz;
import com.armitage.server.iscm.common.service.TaskSettingDetailBiz;
import com.armitage.server.quartz.job.ScmBaseCostUpdateJob;
import com.armitage.server.quartz.job.ScmCalcFbcRptDataJob;
import com.armitage.server.quartz.job.ScmFbcCommonTaskJob;
import com.armitage.server.quartz.job.ScmGenerateCostConsumeJob;
import com.armitage.server.quartz.job.ScmItemPriceUpdateJob;
import com.armitage.server.quartz.job.ScmSalePriceGetJob;
import com.armitage.server.quartz.model.AppInfo;
import com.armitage.server.quartz.model.ScmSyncTaskLog;
import com.armitage.server.quartz.model.ScmSystemTask;
import com.armitage.server.quartz.model.ScmSystemTaskExecTime;
import com.armitage.server.quartz.service.ScmSyncTaskLogBiz;
import com.armitage.server.quartz.service.ScmSystemTaskBiz;
import com.armitage.server.quartz.service.ScmSystemTaskExecTimeBiz;
import com.armitage.server.system.model.OrgBaseUnit2;
import com.armitage.server.system.service.OrgBaseUnitBiz;
import com.armitage.server.system.util.AppServiceUtil;

public class ScmTaskUtil {

	static Log log = LogFactory.getLog(ScmTaskUtil.class);
	
	private static ScmCostCardPriceBiz scmCostCardPriceBiz = (ScmCostCardPriceBiz) AppContextUtil.getBean("scmCostCardPriceBiz");
	private static ScmCookCostCardPriceBiz scmCookCostCardPriceBiz = (ScmCookCostCardPriceBiz) AppContextUtil.getBean("scmCookCostCardPriceBiz");
	private static ScmItemCostPriceBiz scmItemCostPriceBiz = (ScmItemCostPriceBiz) AppContextUtil.getBean("scmItemCostPriceBiz");
	private static OrgBaseUnitBiz orgBaseUnitBiz = (OrgBaseUnitBiz) AppContextUtil.getBean("orgBaseUnitBiz");
	private static ScmSyncTaskLogBiz scmSyncTaskLogBiz = (ScmSyncTaskLogBiz) AppContextUtil.getBean("scmSyncTaskLogBiz");
	private ScmSystemTaskBiz scmSystemTaskBiz = (ScmSystemTaskBiz) AppContextUtil.getBean("scmSystemTaskBiz");
	private ScmSyncTaskInfoBiz scmSyncTaskInfoBiz = (ScmSyncTaskInfoBiz) AppContextUtil.getBean("scmSyncTaskInfoBiz");
	private TaskSettingDetailBiz taskSettingDetailBiz = (TaskSettingDetailBiz) AppContextUtil.getBean("taskSettingDetailBiz");
	private ScmSystemTaskExecTimeBiz scmSystemTaskExecTimeBiz = (ScmSystemTaskExecTimeBiz) AppContextUtil.getBean("scmSystemTaskExecTimeBiz");
	private ScmPriceUpdSetBiz scmPriceUpdSetBiz = (ScmPriceUpdSetBiz) AppContextUtil.getBean("scmPriceUpdSetBiz");
	private ScmDiskStdCostInfoBiz scmDiskStdCostInfoBiz = (ScmDiskStdCostInfoBiz) AppContextUtil.getBean("scmDiskStdCostInfoBiz");
	private ScmCookStdCostInfoBiz scmCookStdCostInfoBiz = (ScmCookStdCostInfoBiz) AppContextUtil.getBean("scmCookStdCostInfoBiz");
	private ScmFbmSellDetailBiz scmFbmSellDetailBiz = (ScmFbmSellDetailBiz) AppContextUtil.getBean("scmFbmSellDetailBiz");
	private ScmFbmSellCookDetailBiz scmFbmSellCookDetailBiz = (ScmFbmSellCookDetailBiz) AppContextUtil.getBean("scmFbmSellCookDetailBiz");

	/**
	 * 更新物料价格
	 * @param systemTask
	 * @return
	 */
	public static boolean updateFbcItemPrice(ScmSystemTask systemTask) {
		if(systemTask != null){
			RoutineParam param = new RoutineParam();
			param.setOrgUnitNo(systemTask.getOrgUnitNo());
			OrgBaseUnit2 orgBaseUnit = orgBaseUnitBiz.selectbyOrgNo(param.getOrgUnitNo(), param);
			if (orgBaseUnit != null) {
				param.setControlUnitNo(orgBaseUnit.getControlUnitNo());
				param.setTimeZone(orgBaseUnit.getTimeZone());
			}
			try {
				scmItemCostPriceBiz.updateByPriceUpdSet(systemTask.getOrgUnitNo(), param);
				//记录成功日志
				ScmSyncTaskLog syncTaskLog = new ScmSyncTaskLog(true);
				syncTaskLog.setTaskType(systemTask.getTaskType());
				syncTaskLog.setOrgUnitNo(systemTask.getOrgUnitNo());
				syncTaskLog.setLogtime(CalendarUtil.getDate(param));
				syncTaskLog.setTaskStatus("1");
				syncTaskLog.setControlUnitNo(systemTask.getControlUnitNo());
				scmSyncTaskLogBiz.add(syncTaskLog, param);
			} catch (Exception e) {
				//记录失败日志
				ScmSyncTaskLog syncTaskLog = new ScmSyncTaskLog(true);
				syncTaskLog.setTaskType(systemTask.getTaskType());
				syncTaskLog.setOrgUnitNo(systemTask.getOrgUnitNo());
				syncTaskLog.setLogtime(CalendarUtil.getDate(param));
				syncTaskLog.setTaskStatus("2");
				syncTaskLog.setErrorMessage(Message.getMessage(param.getLang(),"quartz.util.ScmTaskUtil.excuteTaskThread.error"));
				syncTaskLog.setControlUnitNo(systemTask.getControlUnitNo());
				scmSyncTaskLogBiz.add(syncTaskLog, param);
				log.error(Message.getMessage(param.getLang(),"quartz.util.ScmTaskUtil.excuteTaskThread.error"), e);
			}
		}
		return true;
	}
	
	/**
	 * 获取销售价
	 * @param systemTask
	 * @return
	 */
	public static boolean getFbmSalePrice(ScmSystemTask systemTask) {
		if(systemTask != null){
			RoutineParam param = new RoutineParam();
			param.setOrgUnitNo(systemTask.getOrgUnitNo());
			OrgBaseUnit2 orgBaseUnit = orgBaseUnitBiz.selectbyOrgNo(param.getOrgUnitNo(), param);
			if (orgBaseUnit != null) {
				param.setControlUnitNo(orgBaseUnit.getControlUnitNo());
				param.setTimeZone(orgBaseUnit.getTimeZone());
			}
			try {
				scmCostCardPriceBiz.modifySalePrice(systemTask.getOrgUnitNo(), param);
				scmCookCostCardPriceBiz.modifySalePrice(systemTask.getOrgUnitNo(), param);
				//记录成功日志
				ScmSyncTaskLog syncTaskLog = new ScmSyncTaskLog(true);
				syncTaskLog.setTaskType(systemTask.getTaskType());
				syncTaskLog.setOrgUnitNo(systemTask.getOrgUnitNo());
				syncTaskLog.setLogtime(CalendarUtil.getDate(param));
				syncTaskLog.setTaskStatus("1");
				syncTaskLog.setControlUnitNo(systemTask.getControlUnitNo());
				scmSyncTaskLogBiz.add(syncTaskLog, param);
			} catch (Exception e) {
				//记录失败日志
				ScmSyncTaskLog syncTaskLog = new ScmSyncTaskLog(true);
				syncTaskLog.setTaskType(systemTask.getTaskType());
				syncTaskLog.setOrgUnitNo(systemTask.getOrgUnitNo());
				syncTaskLog.setLogtime(CalendarUtil.getDate(param));
				syncTaskLog.setTaskStatus("2");
				syncTaskLog.setErrorMessage(Message.getMessage(param.getLang(),"quartz.util.ScmTaskUtil.excuteTaskThread.error"));
				syncTaskLog.setControlUnitNo(systemTask.getControlUnitNo());
				scmSyncTaskLogBiz.add(syncTaskLog, param);
				log.error(Message.getMessage(param.getLang(),"quartz.util.ScmTaskUtil.excuteTaskThread.error"), e);
			}
		}
		return true;
	}
	
	/**
	 * 更新成本价
	 * @param systemTask
	 * @return
	 */
	public static boolean updateFbcCostPrice(ScmSystemTask systemTask) {
		if(systemTask != null){
			RoutineParam param = new RoutineParam();
			param.setOrgUnitNo(systemTask.getOrgUnitNo());
			OrgBaseUnit2 orgBaseUnit = orgBaseUnitBiz.selectbyOrgNo(param.getOrgUnitNo(), param);
			if (orgBaseUnit != null) {
				param.setControlUnitNo(orgBaseUnit.getControlUnitNo());
				param.setTimeZone(orgBaseUnit.getTimeZone());
			}
			try {
				scmCostCardPriceBiz.updateCostPrice(systemTask.getOrgUnitNo(), param);
				scmCookCostCardPriceBiz.updateCostPrice(systemTask.getOrgUnitNo(), param);
				//记录成功日志
				ScmSyncTaskLog syncTaskLog = new ScmSyncTaskLog(true);
				syncTaskLog.setTaskType(systemTask.getTaskType());
				syncTaskLog.setOrgUnitNo(systemTask.getOrgUnitNo());
				syncTaskLog.setLogtime(CalendarUtil.getDate(param));
				syncTaskLog.setTaskStatus("1");
				syncTaskLog.setControlUnitNo(systemTask.getControlUnitNo());
				scmSyncTaskLogBiz.add(syncTaskLog, param);
			} catch (Exception e) {
				//记录失败日志
				ScmSyncTaskLog syncTaskLog = new ScmSyncTaskLog(true);
				syncTaskLog.setTaskType(systemTask.getTaskType());
				syncTaskLog.setOrgUnitNo(systemTask.getOrgUnitNo());
				syncTaskLog.setLogtime(CalendarUtil.getDate(param));
				syncTaskLog.setTaskStatus("2");
				syncTaskLog.setControlUnitNo(systemTask.getControlUnitNo());
				syncTaskLog.setErrorMessage(Message.getMessage(param.getLang(),"quartz.util.ScmTaskUtil.excuteTaskThread.error"));
				scmSyncTaskLogBiz.add(syncTaskLog, param);
				log.error(Message.getMessage(param.getLang(),"quartz.util.ScmTaskUtil.excuteTaskThread.error"), e);
			}
		}
		return true;
	}
	
	public void fbcComTaskScan(AppInfo appInfo) {
		OperationParam param = new OperationParam();
		param.setOrgUnitNo(appInfo.getOrgUnitNo());
		param.setControlUnitNo(appInfo.getControlUnitNo());
		param.setProductCode("iSCM");
		param.setUsrCode(appInfo.getUsrCode());
		ScmSystemTask systemTask = new ScmSystemTask(true);
		systemTask.setTaskType("iSCMFBCComTask");
		systemTask.setOrgUnitNo(appInfo.getOrgUnitNo());
		List<ScmSystemTask> systemTaskList = scmSystemTaskBiz.selectByTask(systemTask, param);
		if(systemTaskList == null || systemTaskList.isEmpty()){
			return;
		}else{
			systemTask = systemTaskList.get(0);
		}
		List<TaskSettingDetail2> taskSettingDetailList = taskSettingDetailBiz.selectByCtrl(param.getControlUnitNo(), param);
		if(taskSettingDetailList != null && !taskSettingDetailList.isEmpty()){
			HashMap<String, Object> map = new HashMap<>();
			for(TaskSettingDetail2 taskSettingDetail : taskSettingDetailList){
				//生成同步标识子表及同步任务子表
				if(!StringUtils.equalsIgnoreCase("SCM_FBC", taskSettingDetail.getTaskCode())){
					continue;
				}
				generateSyncTaskDataByTaskSettingDetail(taskSettingDetail, param);
				if(!map.containsKey(taskSettingDetail.getTaskCode())){
					map.put(taskSettingDetail.getTaskCode(), taskSettingDetail.getTaskDays());
				}
			}
			//清除任务
			if(map != null && !map.isEmpty()){
				List<ScmSyncTaskInfo2> scmSyncTaskInfoList = new ArrayList<>();
				for(Map.Entry<String, Object> entry:map.entrySet()){ 
					Page page=new Page();
					page.setModelClass(ScmSyncTaskInfo2.class);
					page.setShowCount(100);
					Calendar calendar = new GregorianCalendar();
			        calendar.setTime(new Date());
			        calendar.add(Calendar.DATE, -Integer.parseInt(String.valueOf(entry.getValue())));
					ArrayList argList = new ArrayList();
			        argList.add("taskCode="+entry.getKey());
			        argList.add("controlUnitNo=" + param.getControlUnitNo());
			        argList.add("productCode=" + param.getProductCode());
			        argList.add("createTime=" + FormatUtils.fmtDate(calendar.getTime()));
			        List<ScmSyncTaskInfo2> tempScmSyncTaskInfoList = scmSyncTaskInfoBiz.queryPage(page, argList, "findDeleteTaskPage", param);
					scmSyncTaskInfoList.addAll(tempScmSyncTaskInfoList); 
			    } 
				if(scmSyncTaskInfoList != null && !scmSyncTaskInfoList.isEmpty()){
					scmSyncTaskInfoBiz.delete(scmSyncTaskInfoList, param);
				}
			}
		}
	}
	
	public void generateSyncTaskDataByTaskSettingDetail(TaskSettingDetail2 taskSettingDetail, Param param) {
		generateSyncTaskDataByTaskSettingDetail(taskSettingDetail,null,param);
	}
	
	public void generateSyncTaskDataByTaskSettingDetail(TaskSettingDetail2 taskSettingDetail,List list, Param param) {
		Date currDate = new Date();		//当前执行时间
		Calendar calendar = new GregorianCalendar();
        calendar.setTime(currDate);
        calendar.add(Calendar.SECOND,Integer.parseInt(taskSettingDetail.getExtendedParam1()));
        Date nextExecDate = FormatUtils.parseDateTime(FormatUtils.fmtDateTime(calendar.getTime()), "yyyy-MM-dd HH:mm:ss");	//下次执行的时间
		switch (taskSettingDetail.getTaskObject()) {
		case "updateFbcItemPrice":
			ScmSystemTask updateFbcItemPriceSystemTask = new ScmSystemTask(true);
			updateFbcItemPriceSystemTask.setTaskType("updateFbcItemPrice");
			updateFbcItemPriceSystemTask.setOrgUnitNo(param.getOrgUnitNo());
			List<ScmSystemTask> updateFbcItemPriceSystemTaskList = scmSystemTaskBiz.selectByTask(updateFbcItemPriceSystemTask, param);
			if(updateFbcItemPriceSystemTaskList == null || updateFbcItemPriceSystemTaskList.isEmpty()){
				return;
			}else{
				updateFbcItemPriceSystemTask = updateFbcItemPriceSystemTaskList.get(0);
			}
			List<ScmPriceUpdSet2> updateFbcItemPriceUpdSetList = getPendingTask(param.getControlUnitNo(), "updateFbcItemPrice",currDate, nextExecDate, param);
			if(updateFbcItemPriceUpdSetList != null && !updateFbcItemPriceUpdSetList.isEmpty()){
				Date updateFbcItemPriceUpdateTime = FormatUtils.parseDateTimeMs(FormatUtils.fmtDateTimeMS(updateFbcItemPriceSystemTask.getUpdateTime()));
				for(ScmPriceUpdSet2 scmPriceUpdSet : updateFbcItemPriceUpdSetList){
					generateSyncTaskInfo(taskSettingDetail,scmPriceUpdSet.getOrgUnitNo(),updateFbcItemPriceUpdateTime,"G",param);
				}
			}
			break;
		case "getFbmSalePrice":
			ScmSystemTask getFbmSalePriceSystemTask = new ScmSystemTask(true);
			getFbmSalePriceSystemTask.setTaskType("getFbmSalePrice");
			getFbmSalePriceSystemTask.setOrgUnitNo(param.getOrgUnitNo());
			List<ScmSystemTask> getFbmSalePriceSystemTaskList = scmSystemTaskBiz.selectByTask(getFbmSalePriceSystemTask, param);
			if(getFbmSalePriceSystemTaskList == null || getFbmSalePriceSystemTaskList.isEmpty()){
				return;
			}else{
				getFbmSalePriceSystemTask = getFbmSalePriceSystemTaskList.get(0);
			}
			List<ScmPriceUpdSet2> getFbmSalePriceUpdSetList = getPendingTask(param.getControlUnitNo(), "getFbmSalePrice",currDate, nextExecDate, param);
			if(getFbmSalePriceUpdSetList != null && !getFbmSalePriceUpdSetList.isEmpty()){
				Date getFbmSalePriceUpdateTime = FormatUtils.parseDateTimeMs(FormatUtils.fmtDateTimeMS(getFbmSalePriceSystemTask.getUpdateTime()));
				for(ScmPriceUpdSet2 scmPriceUpdSet : getFbmSalePriceUpdSetList){
					generateSyncTaskInfo(taskSettingDetail,scmPriceUpdSet.getOrgUnitNo(),getFbmSalePriceUpdateTime,"G",param);
				}
			}
			break;
		case "updateFbcCostPrice":
			ScmSystemTask updateFbcCostPriceSystemTask = new ScmSystemTask(true);
			updateFbcCostPriceSystemTask.setTaskType("updateFbcCostPrice");
			updateFbcCostPriceSystemTask.setOrgUnitNo(param.getOrgUnitNo());
			List<ScmSystemTask> updateFbcCostPriceSystemTaskList = scmSystemTaskBiz.selectByTask(updateFbcCostPriceSystemTask, param);
			if(updateFbcCostPriceSystemTaskList == null || updateFbcCostPriceSystemTaskList.isEmpty()){
				return;
			}else{
				updateFbcCostPriceSystemTask = updateFbcCostPriceSystemTaskList.get(0);
			}
			List<ScmPriceUpdSet2> updateFbcCostPriceUpdSetList = getPendingTask(param.getControlUnitNo(), "updateFbcCostPrice",currDate, nextExecDate, param);
			if(updateFbcCostPriceUpdSetList != null && !updateFbcCostPriceUpdSetList.isEmpty()){
				Date updateFbcCostPriceUpdateTime = FormatUtils.parseDateTimeMs(FormatUtils.fmtDateTimeMS(updateFbcCostPriceSystemTask.getUpdateTime()));
				for(ScmPriceUpdSet2 scmPriceUpdSet : updateFbcCostPriceUpdSetList){
					generateSyncTaskInfo(taskSettingDetail,scmPriceUpdSet.getOrgUnitNo(),updateFbcCostPriceUpdateTime,"G",param);
				}
			}
			break;
		case "generateCostConsume":
			ScmSystemTask generateCostConsumeSystemTask = new ScmSystemTask(true);
			generateCostConsumeSystemTask.setTaskType("generateCostConsume");
			generateCostConsumeSystemTask.setOrgUnitNo(param.getOrgUnitNo());
			List<ScmSystemTask> generateCostConsumeSystemTaskList = scmSystemTaskBiz.selectByTask(generateCostConsumeSystemTask, param);
			if(generateCostConsumeSystemTaskList == null || generateCostConsumeSystemTaskList.isEmpty()){
				return;
			}else{
				generateCostConsumeSystemTask = generateCostConsumeSystemTaskList.get(0);
			}
			List<ScmPriceUpdSet2> generateCostConsumeUpdSetList = getPendingTask(param.getControlUnitNo(), "generateCostConsume",currDate, nextExecDate, param);
			if(generateCostConsumeUpdSetList != null && !generateCostConsumeUpdSetList.isEmpty()){
				Date generateCostConsumeUpdateTime = FormatUtils.parseDateTimeMs(FormatUtils.fmtDateTimeMS(generateCostConsumeSystemTask.getUpdateTime()));
				for(ScmPriceUpdSet2 scmPriceUpdSet : generateCostConsumeUpdSetList){
					generateSyncTaskInfo(taskSettingDetail,scmPriceUpdSet.getOrgUnitNo(),generateCostConsumeUpdateTime,"G",param);
				}
			}
			break;
		case "calcFbcRptData":
			ScmSystemTask calcFbcRptDataSystemTask = new ScmSystemTask(true);
			calcFbcRptDataSystemTask.setTaskType("calcFbcRptData");
			calcFbcRptDataSystemTask.setOrgUnitNo(param.getOrgUnitNo());
			List<ScmSystemTask> calcFbcRptDataSystemTaskList = scmSystemTaskBiz.selectByTask(calcFbcRptDataSystemTask, param);
			if(calcFbcRptDataSystemTaskList == null || calcFbcRptDataSystemTaskList.isEmpty()){
				return;
			}else{
				calcFbcRptDataSystemTask = calcFbcRptDataSystemTaskList.get(0);
			}
			List<ScmPriceUpdSet2> calcFbcRptDataPriceUpdSetList = getPendingTask(param.getControlUnitNo(), "calcFbcRptData",currDate, nextExecDate, param);
			if(calcFbcRptDataPriceUpdSetList != null && !calcFbcRptDataPriceUpdSetList.isEmpty()){
				Date calcFbcRptDataUpdateTime = FormatUtils.parseDateTimeMs(FormatUtils.fmtDateTimeMS(calcFbcRptDataSystemTask.getUpdateTime()));
				for(ScmPriceUpdSet2 scmPriceUpdSet : calcFbcRptDataPriceUpdSetList){
					generateSyncTaskInfo(taskSettingDetail,scmPriceUpdSet.getOrgUnitNo(),calcFbcRptDataUpdateTime,"G",param);
				}
			}
			break;
		default:
			break;
		}
	}
	
	private void generateSyncTaskInfo(TaskSettingDetail2 taskSettingDetail,String orgUnitNo, Date updateTime,String taskSource,Param param){
		//生成同步任务子表
		ScmSyncTaskInfo2 scmSyncTaskInfo = new ScmSyncTaskInfo2(true);
		scmSyncTaskInfo.setOrgUnitNo(orgUnitNo);
		scmSyncTaskInfo.setTaskCode(taskSettingDetail.getTaskCode());
		scmSyncTaskInfo.setTaskAction(taskSettingDetail.getInteractionMode());
		scmSyncTaskInfo.setTaskType(taskSettingDetail.getTaskObject());
		scmSyncTaskInfo.setProductCode(taskSettingDetail.getProductCode());
		scmSyncTaskInfo.setTaskOwner(taskSettingDetail.getChannel());
		scmSyncTaskInfo.setTaskStatus("W");
		scmSyncTaskInfo.setControlUnitNo(param.getControlUnitNo());
		scmSyncTaskInfo.setSyncDataId(0);
		ScmSyncTaskInfo2 scmSyncTaskInfo2 = scmSyncTaskInfoBiz.selectByScmSyncTaskInfo(scmSyncTaskInfo, param);
		if(scmSyncTaskInfo2 != null){
			Calendar newTime = Calendar.getInstance();
            newTime.add(Calendar.SECOND,Integer.parseInt(taskSettingDetail.getExtendedParam1()));
            scmSyncTaskInfo2.setLogtime(newTime.getTime());
            scmSyncTaskInfoBiz.update(scmSyncTaskInfo2, param);
		}else{
			scmSyncTaskInfo.setBegDate(new Date());
			scmSyncTaskInfo.setSyncDataId(0);
			//scmSyncTaskInfo.setTaskExecutor("");
            scmSyncTaskInfo.setLogtime(updateTime);
			scmSyncTaskInfo.setCreateTime(new Date());
			scmSyncTaskInfo.setTaskSource(taskSource);
			scmSyncTaskInfoBiz.add(scmSyncTaskInfo, param);
		}
	}
	
	public void updateFbcItemPrice(AppInfo appInfo) {
		OperationParam param = new OperationParam();
		param.setOrgUnitNo(appInfo.getOrgUnitNo());
		param.setControlUnitNo(appInfo.getControlUnitNo());
		param.setProductCode("iSCM");
		param.setUsrCode(appInfo.getUsrCode());
		ScmSystemTask systemTask = new ScmSystemTask(true);
		systemTask.setTaskType("updateFbcItemPrice");
		systemTask.setOrgUnitNo(appInfo.getOrgUnitNo());
		List<ScmSystemTask> systemTaskList = scmSystemTaskBiz.selectByTask(systemTask, param);
		if(systemTaskList == null || systemTaskList.isEmpty()){
			return;
		}else{
			systemTask = systemTaskList.get(0);
		}
		if(systemTask.getSize() > 0){
			Page page=new Page();
			page.setModelClass(ScmSyncTaskInfo2.class);
			page.setShowCount(systemTask.getSize());
			page.getParam().put(ScmSyncTaskInfo2.FN_TASKCODE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmSyncTaskInfo2.class) + "." +ScmSyncTaskInfo2.FN_TASKCODE, QueryParam.QUERY_EQ,"SCM_FBC"));
			page.getParam().put(ScmSyncTaskInfo2.FN_TASKTYPE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmSyncTaskInfo2.class) + "." +ScmSyncTaskInfo2.FN_TASKTYPE, QueryParam.QUERY_EQ,systemTask.getTaskType()));
			page.getParam().put(ScmSyncTaskInfo2.FN_PRODUCTCODE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmSyncTaskInfo2.class) + "." +ScmSyncTaskInfo2.FN_PRODUCTCODE, QueryParam.QUERY_EQ,"iSCM"));
			page.getParam().put(ScmSyncTaskInfo2.FN_TASKOWNER,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmSyncTaskInfo2.class) + "." +ScmSyncTaskInfo2.FN_TASKOWNER, QueryParam.QUERY_EQ,"FBC"));
			page.getParam().put(ScmSyncTaskInfo2.FN_TASKSTATUS,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmSyncTaskInfo2.class) + "." +ScmSyncTaskInfo2.FN_TASKSTATUS, QueryParam.QUERY_EQ,"W"));
			page.getParam().put(ScmSyncTaskInfo2.FN_CONTROLUNITNO,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmSyncTaskInfo2.class) + "." +ScmSyncTaskInfo2.FN_CONTROLUNITNO, QueryParam.QUERY_EQ,param.getControlUnitNo()));
			List<ScmSyncTaskInfo2> scmSyncTaskInfoList = scmSyncTaskInfoBiz.findPage(page, param);
			if(scmSyncTaskInfoList != null && !scmSyncTaskInfoList.isEmpty()){
				int count = 0;
				for(int i=0;i<scmSyncTaskInfoList.size();i++){
					ScmSyncTaskInfo2 scmSyncTaskInfo = scmSyncTaskInfoList.get(i);
					if(count<systemTask.getSize()){
						count++;
						try {
							scmSyncTaskInfo.setTaskStatus("D");
							scmSyncTaskInfo.setUpdateTime(new Date());
							scmSyncTaskInfoBiz.update(scmSyncTaskInfo, param);
							String errorMsg = "";
							param.setOrgUnitNo(scmSyncTaskInfo.getOrgUnitNo());
							scmItemCostPriceBiz.updateByPriceUpdSet(scmSyncTaskInfo.getOrgUnitNo(), param);
							//记录成功日志
							ScmSyncTaskLog syncTaskLog = new ScmSyncTaskLog(true);
							syncTaskLog.setTaskType(systemTask.getTaskType());
							syncTaskLog.setOrgUnitNo(scmSyncTaskInfo.getOrgUnitNo());
							syncTaskLog.setLogtime(CalendarUtil.getDate(param));
							syncTaskLog.setTaskStatus("1");
							syncTaskLog.setControlUnitNo(systemTask.getControlUnitNo());
							scmSyncTaskLogBiz.add(syncTaskLog, param);
							scmSyncTaskInfo = scmSyncTaskInfoBiz.select(scmSyncTaskInfo.getId(), param);
							if(StringUtils.isBlank(errorMsg)){
								scmSyncTaskInfo.setTaskStatus("S");
								scmSyncTaskInfo.setUpdateTime(new Date());
								scmSyncTaskInfo.setEndDate(new Date());
								scmSyncTaskInfoBiz.update(scmSyncTaskInfo, param);
							}else{
								scmSyncTaskInfo.setTaskStatus("F");
								scmSyncTaskInfo.setStatusMessage(errorMsg);
								scmSyncTaskInfo.setUpdateTime(new Date());
								scmSyncTaskInfo.setEndDate(new Date());
								scmSyncTaskInfoBiz.update(scmSyncTaskInfo, param);
							}
						} catch (Exception e) {
							//log.error(e);
							String errorMsg = e.getMessage();
							if(StringUtils.isNotBlank(errorMsg) && errorMsg.length()>255){
								errorMsg = StringUtils.substring(errorMsg, 0, 255-1);
							}
							scmSyncTaskInfo = scmSyncTaskInfoBiz.select(scmSyncTaskInfo.getId(), param);
							scmSyncTaskInfo.setTaskStatus("F");
							scmSyncTaskInfo.setStatusMessage(errorMsg);
							scmSyncTaskInfo.setUpdateTime(new Date());
							scmSyncTaskInfo.setEndDate(new Date());
							scmSyncTaskInfoBiz.update(scmSyncTaskInfo, param);
							//记录失败日志
							ScmSyncTaskLog syncTaskLog = new ScmSyncTaskLog(true);
							syncTaskLog.setTaskType(systemTask.getTaskType());
							syncTaskLog.setOrgUnitNo(scmSyncTaskInfo.getOrgUnitNo());
							syncTaskLog.setLogtime(CalendarUtil.getDate(param));
							syncTaskLog.setTaskStatus("2");
							syncTaskLog.setErrorMessage(Message.getMessage(param.getLang(),"quartz.util.ScmTaskUtil.excuteTaskThread.error"));
							syncTaskLog.setControlUnitNo(systemTask.getControlUnitNo());
							scmSyncTaskLogBiz.add(syncTaskLog, param);
							log.error(Message.getMessage(param.getLang(),"quartz.util.ScmTaskUtil.excuteTaskThread.error"), e);
							continue;
						}
					}
				}
			}else{
				log.info("不存在更新物资成本价格任务！");
			}
		}else{
			log.info("不存在更新物资成本价格任务！");
		}
	}
	
	public void getFbmSalePrice(AppInfo appInfo) {
		OperationParam param = new OperationParam();
		param.setOrgUnitNo(appInfo.getOrgUnitNo());
		param.setControlUnitNo(appInfo.getControlUnitNo());
		param.setProductCode("iSCM");
		param.setUsrCode(appInfo.getUsrCode());
		ScmSystemTask systemTask = new ScmSystemTask(true);
		systemTask.setTaskType("getFbmSalePrice");
		systemTask.setOrgUnitNo(appInfo.getOrgUnitNo());
		List<ScmSystemTask> systemTaskList = scmSystemTaskBiz.selectByTask(systemTask, param);
		if(systemTaskList == null || systemTaskList.isEmpty()){
			return;
		}else{
			systemTask = systemTaskList.get(0);
		}
		if(systemTask.getSize() > 0){
			Page page=new Page();
			page.setModelClass(ScmSyncTaskInfo2.class);
			page.setShowCount(systemTask.getSize());
			page.getParam().put(ScmSyncTaskInfo2.FN_TASKCODE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmSyncTaskInfo2.class) + "." +ScmSyncTaskInfo2.FN_TASKCODE, QueryParam.QUERY_EQ,"SCM_FBC"));
			page.getParam().put(ScmSyncTaskInfo2.FN_TASKTYPE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmSyncTaskInfo2.class) + "." +ScmSyncTaskInfo2.FN_TASKTYPE, QueryParam.QUERY_EQ,systemTask.getTaskType()));
			page.getParam().put(ScmSyncTaskInfo2.FN_PRODUCTCODE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmSyncTaskInfo2.class) + "." +ScmSyncTaskInfo2.FN_PRODUCTCODE, QueryParam.QUERY_EQ,"iSCM"));
			page.getParam().put(ScmSyncTaskInfo2.FN_TASKOWNER,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmSyncTaskInfo2.class) + "." +ScmSyncTaskInfo2.FN_TASKOWNER, QueryParam.QUERY_EQ,"FBC"));
			page.getParam().put(ScmSyncTaskInfo2.FN_TASKSTATUS,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmSyncTaskInfo2.class) + "." +ScmSyncTaskInfo2.FN_TASKSTATUS, QueryParam.QUERY_EQ,"W"));
			page.getParam().put(ScmSyncTaskInfo2.FN_CONTROLUNITNO,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmSyncTaskInfo2.class) + "." +ScmSyncTaskInfo2.FN_CONTROLUNITNO, QueryParam.QUERY_EQ,param.getControlUnitNo()));
			List<ScmSyncTaskInfo2> scmSyncTaskInfoList = scmSyncTaskInfoBiz.findPage(page, param);
			if(scmSyncTaskInfoList != null && !scmSyncTaskInfoList.isEmpty()){
				int count = 0;
				for(int i=0;i<scmSyncTaskInfoList.size();i++){
					ScmSyncTaskInfo2 scmSyncTaskInfo = scmSyncTaskInfoList.get(i);
					if(count<systemTask.getSize()){
						count++;
						try {
							scmSyncTaskInfo.setTaskStatus("D");
							scmSyncTaskInfo.setUpdateTime(new Date());
							scmSyncTaskInfoBiz.update(scmSyncTaskInfo, param);
							String errorMsg = "";
							param.setOrgUnitNo(scmSyncTaskInfo.getOrgUnitNo());
							scmCostCardPriceBiz.modifySalePrice(scmSyncTaskInfo.getOrgUnitNo(), param);
							scmCookCostCardPriceBiz.modifySalePrice(scmSyncTaskInfo.getOrgUnitNo(), param);
							//记录成功日志
							ScmSyncTaskLog syncTaskLog = new ScmSyncTaskLog(true);
							syncTaskLog.setTaskType(systemTask.getTaskType());
							syncTaskLog.setOrgUnitNo(scmSyncTaskInfo.getOrgUnitNo());
							syncTaskLog.setLogtime(CalendarUtil.getDate(param));
							syncTaskLog.setTaskStatus("1");
							syncTaskLog.setControlUnitNo(systemTask.getControlUnitNo());
							scmSyncTaskLogBiz.add(syncTaskLog, param);
							scmSyncTaskInfo = scmSyncTaskInfoBiz.select(scmSyncTaskInfo.getId(), param);
							if(StringUtils.isBlank(errorMsg)){
								scmSyncTaskInfo.setTaskStatus("S");
								scmSyncTaskInfo.setUpdateTime(new Date());
								scmSyncTaskInfo.setEndDate(new Date());
								scmSyncTaskInfoBiz.update(scmSyncTaskInfo, param);
							}else{
								scmSyncTaskInfo.setTaskStatus("F");
								scmSyncTaskInfo.setStatusMessage(errorMsg);
								scmSyncTaskInfo.setUpdateTime(new Date());
								scmSyncTaskInfo.setEndDate(new Date());
								scmSyncTaskInfoBiz.update(scmSyncTaskInfo, param);
							}
						} catch (Exception e) {
							//log.error(e);
							String errorMsg = e.getMessage();
							if(StringUtils.isNotBlank(errorMsg) && errorMsg.length()>255){
								errorMsg = StringUtils.substring(errorMsg, 0, 255-1);
							}
							scmSyncTaskInfo = scmSyncTaskInfoBiz.select(scmSyncTaskInfo.getId(), param);
							scmSyncTaskInfo.setTaskStatus("F");
							scmSyncTaskInfo.setStatusMessage(errorMsg);
							scmSyncTaskInfo.setUpdateTime(new Date());
							scmSyncTaskInfo.setEndDate(new Date());
							scmSyncTaskInfoBiz.update(scmSyncTaskInfo, param);
							//记录失败日志
							ScmSyncTaskLog syncTaskLog = new ScmSyncTaskLog(true);
							syncTaskLog.setTaskType(systemTask.getTaskType());
							syncTaskLog.setOrgUnitNo(scmSyncTaskInfo.getOrgUnitNo());
							syncTaskLog.setLogtime(CalendarUtil.getDate(param));
							syncTaskLog.setTaskStatus("2");
							syncTaskLog.setErrorMessage(Message.getMessage(param.getLang(),"quartz.util.ScmTaskUtil.excuteTaskThread.error"));
							syncTaskLog.setControlUnitNo(systemTask.getControlUnitNo());
							scmSyncTaskLogBiz.add(syncTaskLog, param);
							log.error(Message.getMessage(param.getLang(),"quartz.util.ScmTaskUtil.excuteTaskThread.error"), e);
							continue;
						}
					}
				}
			}else{
				log.info("不存在获取餐饮标准售价任务！");
			}
		}else{
			log.info("不存在获取餐饮标准售价任务！");
		}
	}
	
	public void updateFbcCostPrice(AppInfo appInfo) {
		OperationParam param = new OperationParam();
		param.setOrgUnitNo(appInfo.getOrgUnitNo());
		param.setControlUnitNo(appInfo.getControlUnitNo());
		param.setProductCode("iSCM");
		param.setUsrCode(appInfo.getUsrCode());
		ScmSystemTask systemTask = new ScmSystemTask(true);
		systemTask.setTaskType("updateFbcCostPrice");
		systemTask.setOrgUnitNo(appInfo.getOrgUnitNo());
		List<ScmSystemTask> systemTaskList = scmSystemTaskBiz.selectByTask(systemTask, param);
		if(systemTaskList == null || systemTaskList.isEmpty()){
			return;
		}else{
			systemTask = systemTaskList.get(0);
		}
		if(systemTask.getSize() > 0){
			Page page=new Page();
			page.setModelClass(ScmSyncTaskInfo2.class);
			page.setShowCount(systemTask.getSize());
			page.getParam().put(ScmSyncTaskInfo2.FN_TASKCODE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmSyncTaskInfo2.class) + "." +ScmSyncTaskInfo2.FN_TASKCODE, QueryParam.QUERY_EQ,"SCM_FBC"));
			page.getParam().put(ScmSyncTaskInfo2.FN_TASKTYPE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmSyncTaskInfo2.class) + "." +ScmSyncTaskInfo2.FN_TASKTYPE, QueryParam.QUERY_EQ,systemTask.getTaskType()));
			page.getParam().put(ScmSyncTaskInfo2.FN_PRODUCTCODE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmSyncTaskInfo2.class) + "." +ScmSyncTaskInfo2.FN_PRODUCTCODE, QueryParam.QUERY_EQ,"iSCM"));
			page.getParam().put(ScmSyncTaskInfo2.FN_TASKOWNER,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmSyncTaskInfo2.class) + "." +ScmSyncTaskInfo2.FN_TASKOWNER, QueryParam.QUERY_EQ,"FBC"));
			page.getParam().put(ScmSyncTaskInfo2.FN_TASKSTATUS,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmSyncTaskInfo2.class) + "." +ScmSyncTaskInfo2.FN_TASKSTATUS, QueryParam.QUERY_EQ,"W"));
			page.getParam().put(ScmSyncTaskInfo2.FN_CONTROLUNITNO,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmSyncTaskInfo2.class) + "." +ScmSyncTaskInfo2.FN_CONTROLUNITNO, QueryParam.QUERY_EQ,param.getControlUnitNo()));
			List<ScmSyncTaskInfo2> scmSyncTaskInfoList = scmSyncTaskInfoBiz.findPage(page, param);
			if(scmSyncTaskInfoList != null && !scmSyncTaskInfoList.isEmpty()){
				int count = 0;
				for(int i=0;i<scmSyncTaskInfoList.size();i++){
					ScmSyncTaskInfo2 scmSyncTaskInfo = scmSyncTaskInfoList.get(i);
					if(count<systemTask.getSize()){
						count++;
						try {
							scmSyncTaskInfo.setTaskStatus("D");
							scmSyncTaskInfo.setUpdateTime(new Date());
							scmSyncTaskInfoBiz.update(scmSyncTaskInfo, param);
							String errorMsg = "";
							param.setOrgUnitNo(scmSyncTaskInfo.getOrgUnitNo());
							scmCostCardPriceBiz.updateCostPrice(scmSyncTaskInfo.getOrgUnitNo(), param);
							scmCookCostCardPriceBiz.updateCostPrice(scmSyncTaskInfo.getOrgUnitNo(), param);
							//记录成功日志
							ScmSyncTaskLog syncTaskLog = new ScmSyncTaskLog(true);
							syncTaskLog.setTaskType(systemTask.getTaskType());
							syncTaskLog.setOrgUnitNo(scmSyncTaskInfo.getOrgUnitNo());
							syncTaskLog.setLogtime(CalendarUtil.getDate(param));
							syncTaskLog.setTaskStatus("1");
							syncTaskLog.setControlUnitNo(systemTask.getControlUnitNo());
							scmSyncTaskLogBiz.add(syncTaskLog, param);
							scmSyncTaskInfo = scmSyncTaskInfoBiz.select(scmSyncTaskInfo.getId(), param);
							if(StringUtils.isBlank(errorMsg)){
								scmSyncTaskInfo.setTaskStatus("S");
								scmSyncTaskInfo.setUpdateTime(new Date());
								scmSyncTaskInfo.setEndDate(new Date());
								scmSyncTaskInfoBiz.update(scmSyncTaskInfo, param);
							}else{
								scmSyncTaskInfo.setTaskStatus("F");
								scmSyncTaskInfo.setStatusMessage(errorMsg);
								scmSyncTaskInfo.setUpdateTime(new Date());
								scmSyncTaskInfo.setEndDate(new Date());
								scmSyncTaskInfoBiz.update(scmSyncTaskInfo, param);
							}
						} catch (Exception e) {
							//log.error(e);
							String errorMsg = e.getMessage();
							if(StringUtils.isNotBlank(errorMsg) && errorMsg.length()>255){
								errorMsg = StringUtils.substring(errorMsg, 0, 255-1);
							}
							scmSyncTaskInfo = scmSyncTaskInfoBiz.select(scmSyncTaskInfo.getId(), param);
							scmSyncTaskInfo.setTaskStatus("F");
							scmSyncTaskInfo.setStatusMessage(errorMsg);
							scmSyncTaskInfo.setUpdateTime(new Date());
							scmSyncTaskInfo.setEndDate(new Date());
							scmSyncTaskInfoBiz.update(scmSyncTaskInfo, param);
							//记录失败日志
							ScmSyncTaskLog syncTaskLog = new ScmSyncTaskLog(true);
							syncTaskLog.setTaskType(systemTask.getTaskType());
							syncTaskLog.setOrgUnitNo(scmSyncTaskInfo.getOrgUnitNo());
							syncTaskLog.setLogtime(CalendarUtil.getDate(param));
							syncTaskLog.setTaskStatus("2");
							syncTaskLog.setControlUnitNo(systemTask.getControlUnitNo());
							syncTaskLog.setErrorMessage(Message.getMessage(param.getLang(),"quartz.util.ScmTaskUtil.excuteTaskThread.error"));
							scmSyncTaskLogBiz.add(syncTaskLog, param);
							log.error(Message.getMessage(param.getLang(),"quartz.util.ScmTaskUtil.excuteTaskThread.error"), e);
							continue;
						}
					}
				}
			}else{
				log.info("不存在更新标准成本任务！");
			}
		}else{
			log.info("不存在更新标准成本任务！");
		}
	}
	
	/**
	 * 生成耗用单
	 * @param appInfo
	 */
	public void generateCostConsume(AppInfo appInfo) {
		OperationParam param = new OperationParam();
		param.setOrgUnitNo(appInfo.getOrgUnitNo());
		param.setControlUnitNo(appInfo.getControlUnitNo());
		param.setProductCode("iSCM");
		param.setUsrCode(appInfo.getUsrCode());
		ScmSystemTask systemTask = new ScmSystemTask(true);
		systemTask.setTaskType("generateCostConsume");
		systemTask.setOrgUnitNo(appInfo.getOrgUnitNo());
		List<ScmSystemTask> systemTaskList = scmSystemTaskBiz.selectByTask(systemTask, param);
		if(systemTaskList == null || systemTaskList.isEmpty()){
			return;
		}else{
			systemTask = systemTaskList.get(0);
		}
		if(systemTask.getSize() > 0){
			Page page=new Page();
			page.setModelClass(ScmSyncTaskInfo2.class);
			page.setShowCount(systemTask.getSize());
			page.getParam().put(ScmSyncTaskInfo2.FN_TASKCODE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmSyncTaskInfo2.class) + "." +ScmSyncTaskInfo2.FN_TASKCODE, QueryParam.QUERY_EQ,"SCM_FBC"));
			page.getParam().put(ScmSyncTaskInfo2.FN_TASKTYPE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmSyncTaskInfo2.class) + "." +ScmSyncTaskInfo2.FN_TASKTYPE, QueryParam.QUERY_EQ,systemTask.getTaskType()));
			page.getParam().put(ScmSyncTaskInfo2.FN_PRODUCTCODE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmSyncTaskInfo2.class) + "." +ScmSyncTaskInfo2.FN_PRODUCTCODE, QueryParam.QUERY_EQ,"iSCM"));
			page.getParam().put(ScmSyncTaskInfo2.FN_TASKOWNER,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmSyncTaskInfo2.class) + "." +ScmSyncTaskInfo2.FN_TASKOWNER, QueryParam.QUERY_EQ,"FBC"));
			page.getParam().put(ScmSyncTaskInfo2.FN_TASKSTATUS,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmSyncTaskInfo2.class) + "." +ScmSyncTaskInfo2.FN_TASKSTATUS, QueryParam.QUERY_EQ,"W"));
			page.getParam().put(ScmSyncTaskInfo2.FN_CONTROLUNITNO,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmSyncTaskInfo2.class) + "." +ScmSyncTaskInfo2.FN_CONTROLUNITNO, QueryParam.QUERY_EQ,param.getControlUnitNo()));
			List<ScmSyncTaskInfo2> scmSyncTaskInfoList = scmSyncTaskInfoBiz.findPage(page, param);
			if(scmSyncTaskInfoList != null && !scmSyncTaskInfoList.isEmpty()){
				int count = 0;
				for(int i=0;i<scmSyncTaskInfoList.size();i++){
					ScmSyncTaskInfo2 scmSyncTaskInfo = scmSyncTaskInfoList.get(i);
					if(count<systemTask.getSize()){
						count++;
						try {
							scmSyncTaskInfo.setTaskStatus("D");
							scmSyncTaskInfo.setUpdateTime(new Date());
							scmSyncTaskInfoBiz.update(scmSyncTaskInfo, param);
							String errorMsg = "";
							Map<String, String> map = getDeptCodeMap(param.getControlUnitNo(), param);
							if(map != null && map.size() > 0){
								param.setOrgUnitNo(scmSyncTaskInfo.getOrgUnitNo());
								scmDiskStdCostInfoBiz.generateCostConsume(map, scmSyncTaskInfo.getOrgUnitNo(), FormatUtils.parseDate(FormatUtils.fmtDate(scmSyncTaskInfo.getBegDate())), FormatUtils.parseDate(FormatUtils.fmtDate(scmSyncTaskInfo.getBegDate())), param);
								//记录成功日志
								ScmSyncTaskLog syncTaskLog = new ScmSyncTaskLog(true);
								syncTaskLog.setTaskType(systemTask.getTaskType());
								syncTaskLog.setOrgUnitNo(scmSyncTaskInfo.getOrgUnitNo());
								syncTaskLog.setLogtime(CalendarUtil.getDate(param));
								syncTaskLog.setTaskStatus("1");
								syncTaskLog.setControlUnitNo(systemTask.getControlUnitNo());
								scmSyncTaskLogBiz.add(syncTaskLog, param);
							}
							scmSyncTaskInfo = scmSyncTaskInfoBiz.select(scmSyncTaskInfo.getId(), param);
							if(StringUtils.isBlank(errorMsg)){
								scmSyncTaskInfo.setTaskStatus("S");
								scmSyncTaskInfo.setUpdateTime(new Date());
								scmSyncTaskInfo.setEndDate(new Date());
								scmSyncTaskInfoBiz.update(scmSyncTaskInfo, param);
							}else{
								scmSyncTaskInfo.setTaskStatus("F");
								scmSyncTaskInfo.setStatusMessage(errorMsg);
								scmSyncTaskInfo.setUpdateTime(new Date());
								scmSyncTaskInfo.setEndDate(new Date());
								scmSyncTaskInfoBiz.update(scmSyncTaskInfo, param);
							}
						} catch (Exception e) {
							//log.error(e);
							String errorMsg = e.getMessage();
							if(StringUtils.isNotBlank(errorMsg) && errorMsg.length()>255){
								errorMsg = StringUtils.substring(errorMsg, 0, 255-1);
							}
							scmSyncTaskInfo = scmSyncTaskInfoBiz.select(scmSyncTaskInfo.getId(), param);
							scmSyncTaskInfo.setTaskStatus("F");
							scmSyncTaskInfo.setStatusMessage(errorMsg);
							scmSyncTaskInfo.setUpdateTime(new Date());
							scmSyncTaskInfo.setEndDate(new Date());
							scmSyncTaskInfoBiz.update(scmSyncTaskInfo, param);
							//记录失败日志
							ScmSyncTaskLog syncTaskLog = new ScmSyncTaskLog(true);
							syncTaskLog.setTaskType(systemTask.getTaskType());
							syncTaskLog.setOrgUnitNo(scmSyncTaskInfo.getOrgUnitNo());
							syncTaskLog.setLogtime(CalendarUtil.getDate(param));
							syncTaskLog.setTaskStatus("2");
							syncTaskLog.setControlUnitNo(systemTask.getControlUnitNo());
							syncTaskLog.setErrorMessage(Message.getMessage(param.getLang(),"quartz.util.ScmTaskUtil.excuteTaskThread.error"));
							scmSyncTaskLogBiz.add(syncTaskLog, param);
							log.error(Message.getMessage(param.getLang(),"quartz.util.ScmTaskUtil.excuteTaskThread.error"), e);
							continue;
						}
					}
				}
			}else{
				log.info("不存在自动生成耗用单任务！");
			}
		}else{
			log.info("不存在自动生成耗用单任务！");
		}
	}
	
	public void calcFbcRptData(AppInfo appInfo) {
		OperationParam param = new OperationParam();
		param.setOrgUnitNo(appInfo.getOrgUnitNo());
		param.setControlUnitNo(appInfo.getControlUnitNo());
		param.setProductCode("iSCM");
		param.setUsrCode(appInfo.getUsrCode());
		ScmSystemTask systemTask = new ScmSystemTask(true);
		systemTask.setTaskType("calcFbcRptData");
		systemTask.setOrgUnitNo(appInfo.getOrgUnitNo());
		List<ScmSystemTask> systemTaskList = scmSystemTaskBiz.selectByTask(systemTask, param);
		if(systemTaskList == null || systemTaskList.isEmpty()){
			return;
		}else{
			systemTask = systemTaskList.get(0);
		}
		if(systemTask.getSize() > 0){
			Page page=new Page();
			page.setModelClass(ScmSyncTaskInfo2.class);
			page.setShowCount(systemTask.getSize());
			page.getParam().put(ScmSyncTaskInfo2.FN_TASKCODE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmSyncTaskInfo2.class) + "." +ScmSyncTaskInfo2.FN_TASKCODE, QueryParam.QUERY_EQ,"SCM_FBC"));
			page.getParam().put(ScmSyncTaskInfo2.FN_TASKTYPE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmSyncTaskInfo2.class) + "." +ScmSyncTaskInfo2.FN_TASKTYPE, QueryParam.QUERY_EQ,systemTask.getTaskType()));
			page.getParam().put(ScmSyncTaskInfo2.FN_PRODUCTCODE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmSyncTaskInfo2.class) + "." +ScmSyncTaskInfo2.FN_PRODUCTCODE, QueryParam.QUERY_EQ,"iSCM"));
			page.getParam().put(ScmSyncTaskInfo2.FN_TASKOWNER,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmSyncTaskInfo2.class) + "." +ScmSyncTaskInfo2.FN_TASKOWNER, QueryParam.QUERY_EQ,"FBC"));
			page.getParam().put(ScmSyncTaskInfo2.FN_TASKSTATUS,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmSyncTaskInfo2.class) + "." +ScmSyncTaskInfo2.FN_TASKSTATUS, QueryParam.QUERY_EQ,"W"));
			page.getParam().put(ScmSyncTaskInfo2.FN_CONTROLUNITNO,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmSyncTaskInfo2.class) + "." +ScmSyncTaskInfo2.FN_CONTROLUNITNO, QueryParam.QUERY_EQ,param.getControlUnitNo()));
			List<ScmSyncTaskInfo2> scmSyncTaskInfoList = scmSyncTaskInfoBiz.findPage(page, param);
			if(scmSyncTaskInfoList != null && !scmSyncTaskInfoList.isEmpty()){
				int count = 0;
				for(int i=0;i<scmSyncTaskInfoList.size();i++){
					ScmSyncTaskInfo2 scmSyncTaskInfo = scmSyncTaskInfoList.get(i);
					if(count<systemTask.getSize()){
						count++;
						try {
							scmSyncTaskInfo.setTaskStatus("D");
							scmSyncTaskInfo.setUpdateTime(new Date());
							scmSyncTaskInfoBiz.update(scmSyncTaskInfo, param);
							String errorMsg = "";
							param.setOrgUnitNo(scmSyncTaskInfo.getOrgUnitNo());
							//统计销售数据
							scmFbmSellDetailBiz.calcRptDataByTask(scmSyncTaskInfo.getOrgUnitNo(), FormatUtils.parseDate(FormatUtils.fmtDate(scmSyncTaskInfo.getBegDate())), FormatUtils.parseDate(FormatUtils.fmtDate(scmSyncTaskInfo.getBegDate())), param);
							scmFbmSellCookDetailBiz.calcRptDataByTask(scmSyncTaskInfo.getOrgUnitNo(), FormatUtils.parseDate(FormatUtils.fmtDate(scmSyncTaskInfo.getBegDate())), FormatUtils.parseDate(FormatUtils.fmtDate(scmSyncTaskInfo.getBegDate())), param);
							scmCostCardPriceBiz.getSalePriceByTask(scmSyncTaskInfo.getOrgUnitNo(), FormatUtils.parseDate(FormatUtils.fmtDate(scmSyncTaskInfo.getBegDate())), FormatUtils.parseDate(FormatUtils.fmtDate(scmSyncTaskInfo.getBegDate())), param);
							scmCookCostCardPriceBiz.getSalePriceByTask(scmSyncTaskInfo.getOrgUnitNo(), FormatUtils.parseDate(FormatUtils.fmtDate(scmSyncTaskInfo.getBegDate())), FormatUtils.parseDate(FormatUtils.fmtDate(scmSyncTaskInfo.getBegDate())), param);
							//统计标准成本
							scmDiskStdCostInfoBiz.calcRptDataByTask(scmSyncTaskInfo.getOrgUnitNo(), FormatUtils.parseDate(FormatUtils.fmtDate(scmSyncTaskInfo.getBegDate())), FormatUtils.parseDate(FormatUtils.fmtDate(scmSyncTaskInfo.getBegDate())), param);
							scmCookStdCostInfoBiz.calcRptDataByTask(scmSyncTaskInfo.getOrgUnitNo(), FormatUtils.parseDate(FormatUtils.fmtDate(scmSyncTaskInfo.getBegDate())), FormatUtils.parseDate(FormatUtils.fmtDate(scmSyncTaskInfo.getBegDate())), param);
							scmCostCardPriceBiz.calcCostPriceByTask(scmSyncTaskInfo.getOrgUnitNo(), FormatUtils.parseDate(FormatUtils.fmtDate(scmSyncTaskInfo.getBegDate())), FormatUtils.parseDate(FormatUtils.fmtDate(scmSyncTaskInfo.getBegDate())), param);
							scmCookCostCardPriceBiz.calcCostPriceByTask(scmSyncTaskInfo.getOrgUnitNo(), FormatUtils.parseDate(FormatUtils.fmtDate(scmSyncTaskInfo.getBegDate())), FormatUtils.parseDate(FormatUtils.fmtDate(scmSyncTaskInfo.getBegDate())), param);
							//记录成功日志
							ScmSyncTaskLog syncTaskLog = new ScmSyncTaskLog(true);
							syncTaskLog.setTaskType(systemTask.getTaskType());
							syncTaskLog.setOrgUnitNo(scmSyncTaskInfo.getOrgUnitNo());
							syncTaskLog.setLogtime(CalendarUtil.getDate(param));
							syncTaskLog.setTaskStatus("1");
							syncTaskLog.setControlUnitNo(systemTask.getControlUnitNo());
							scmSyncTaskLogBiz.add(syncTaskLog, param);
							scmSyncTaskInfo = scmSyncTaskInfoBiz.select(scmSyncTaskInfo.getId(), param);
							if(StringUtils.isBlank(errorMsg)){
								scmSyncTaskInfo.setTaskStatus("S");
								scmSyncTaskInfo.setUpdateTime(new Date());
								scmSyncTaskInfo.setEndDate(new Date());
								scmSyncTaskInfoBiz.update(scmSyncTaskInfo, param);
							}else{
								scmSyncTaskInfo.setTaskStatus("F");
								scmSyncTaskInfo.setStatusMessage(errorMsg);
								scmSyncTaskInfo.setUpdateTime(new Date());
								scmSyncTaskInfo.setEndDate(new Date());
								scmSyncTaskInfoBiz.update(scmSyncTaskInfo, param);
							}
						} catch (Exception e) {
							//log.error(e);
							String errorMsg = e.getMessage();
							if(StringUtils.isNotBlank(errorMsg) && errorMsg.length()>255){
								errorMsg = StringUtils.substring(errorMsg, 0, 255-1);
							}
							scmSyncTaskInfo = scmSyncTaskInfoBiz.select(scmSyncTaskInfo.getId(), param);
							scmSyncTaskInfo.setTaskStatus("F");
							scmSyncTaskInfo.setStatusMessage(errorMsg);
							scmSyncTaskInfo.setUpdateTime(new Date());
							scmSyncTaskInfo.setEndDate(new Date());
							scmSyncTaskInfoBiz.update(scmSyncTaskInfo, param);
							//记录失败日志
							ScmSyncTaskLog syncTaskLog = new ScmSyncTaskLog(true);
							syncTaskLog.setTaskType(systemTask.getTaskType());
							syncTaskLog.setOrgUnitNo(scmSyncTaskInfo.getOrgUnitNo());
							syncTaskLog.setLogtime(CalendarUtil.getDate(param));
							syncTaskLog.setTaskStatus("2");
							syncTaskLog.setControlUnitNo(systemTask.getControlUnitNo());
							syncTaskLog.setErrorMessage(Message.getMessage(param.getLang(),"quartz.util.ScmTaskUtil.excuteTaskThread.error"));
							scmSyncTaskLogBiz.add(syncTaskLog, param);
							log.error(Message.getMessage(param.getLang(),"quartz.util.ScmTaskUtil.excuteTaskThread.error"), e);
							continue;
						}
					}
				}
			}else{
				log.info("不存在报表数据汇集任务！");
			}
		}else{
			log.info("不存在报表数据汇集任务！");
		}
	}
	
	/**
	 * 
	 * @param controlUnitNo
	 * @param type 任务类型
	 * @param currDate 当前时间
	 * @param nextExecDate 下次执行时间
	 * @param param
	 * @return 定时任务设置
	 */
	private List<ScmPriceUpdSet2> getPendingTask(String controlUnitNo, String type,Date currDate, Date nextExecDate, Param param){
		Calendar calendar = new GregorianCalendar();
        calendar.setTime(currDate);
        calendar.add(Calendar.SECOND,-10);	//减10秒，防止条件不满足
		currDate = calendar.getTime();
		List<ScmPriceUpdSet2> scmPriceUpdSetList = scmPriceUpdSetBiz.selectByCtrl(controlUnitNo, param);
		if(scmPriceUpdSetList != null && !scmPriceUpdSetList.isEmpty()){
			for(int i = scmPriceUpdSetList.size() - 1; i >= 0; i--){
				boolean flag = false;
				ScmPriceUpdSet2 scmPriceUpdSet = scmPriceUpdSetList.get(i);
				ScmSystemTaskExecTime scmSystemTaskExecTime = scmSystemTaskExecTimeBiz.selectByTaskType(scmPriceUpdSet.getOrgUnitNo(),type,param);
				if(StringUtils.isNotBlank(scmPriceUpdSet.getItemPriceUpd()) && StringUtils.isNotBlank(scmPriceUpdSet.getOrgUnitNo())
						&& StringUtils.equalsIgnoreCase(type, "updateFbcItemPrice") 
						&& ((currDate.compareTo(FormatUtils.parseDateTime(FormatUtils.fmtDate(currDate)+" "+scmPriceUpdSet.getItemPriceUpd(), "yyyy-MM-dd HH:mm"))<=0
						&& nextExecDate.compareTo(FormatUtils.parseDateTime(FormatUtils.fmtDate(currDate)+" "+scmPriceUpdSet.getItemPriceUpd(), "yyyy-MM-dd HH:mm")) > 0)
						|| scmSystemTaskExecTime==null || FormatUtils.fmtDate(currDate).compareTo(StringUtils.left(scmSystemTaskExecTime.getExecTime(),10))!=0)){
					flag = true;
				}
				if(StringUtils.isNotBlank(scmPriceUpdSet.getPriceGetTime()) && StringUtils.isNotBlank(scmPriceUpdSet.getOrgUnitNo())
						&& StringUtils.equalsIgnoreCase(type, "getFbmSalePrice")
						&& ((currDate.compareTo(FormatUtils.parseDateTime(FormatUtils.fmtDate(currDate)+" "+scmPriceUpdSet.getPriceGetTime(), "yyyy-MM-dd HH:mm"))<=0
						&& nextExecDate.compareTo(FormatUtils.parseDateTime(FormatUtils.fmtDate(currDate)+" "+scmPriceUpdSet.getPriceGetTime(), "yyyy-MM-dd HH:mm")) > 0)
						|| scmSystemTaskExecTime==null || FormatUtils.fmtDate(currDate).compareTo(StringUtils.left(scmSystemTaskExecTime.getExecTime(),10))!=0)){
					flag = true;
				}
				if(StringUtils.isNotBlank(scmPriceUpdSet.getBaseCostUpd()) && StringUtils.isNotBlank(scmPriceUpdSet.getOrgUnitNo())
						&& StringUtils.equalsIgnoreCase(type, "updateFbcCostPrice")
						&& ((currDate.compareTo(FormatUtils.parseDateTime(FormatUtils.fmtDate(currDate)+" "+scmPriceUpdSet.getBaseCostUpd(), "yyyy-MM-dd HH:mm"))<=0
						&& nextExecDate.compareTo(FormatUtils.parseDateTime(FormatUtils.fmtDate(nextExecDate)+" "+scmPriceUpdSet.getBaseCostUpd(), "yyyy-MM-dd HH:mm")) > 0)
						|| scmSystemTaskExecTime==null || FormatUtils.fmtDate(currDate).compareTo(StringUtils.left(scmSystemTaskExecTime.getExecTime(),10))!=0)){
					flag = true;
				}
				if(StringUtils.isNotBlank(scmPriceUpdSet.getGenerateCostConsumeTime()) && StringUtils.isNotBlank(scmPriceUpdSet.getOrgUnitNo()) 
						&& StringUtils.equalsIgnoreCase(type, "generateCostConsume")
						&& ((currDate.compareTo(FormatUtils.parseDateTime(FormatUtils.fmtDate(currDate)+" "+scmPriceUpdSet.getGenerateCostConsumeTime(), "yyyy-MM-dd HH:mm"))<=0
						&& nextExecDate.compareTo(FormatUtils.parseDateTime(FormatUtils.fmtDate(nextExecDate)+" "+scmPriceUpdSet.getGenerateCostConsumeTime(), "yyyy-MM-dd HH:mm")) > 0)
						|| scmSystemTaskExecTime==null || FormatUtils.fmtDate(currDate).compareTo(StringUtils.left(scmSystemTaskExecTime.getExecTime(),10))!=0)){
					flag = true;
				}
				if(StringUtils.isNotBlank(scmPriceUpdSet.getCalcFbcRptDataTime()) && StringUtils.isNotBlank(scmPriceUpdSet.getOrgUnitNo())
						&& StringUtils.equalsIgnoreCase(type, "calcFbcRptData")
						&& ((currDate.compareTo(FormatUtils.parseDateTime(FormatUtils.fmtDate(currDate)+" "+scmPriceUpdSet.getCalcFbcRptDataTime(), "yyyy-MM-dd HH:mm"))<=0
						&& nextExecDate.compareTo(FormatUtils.parseDateTime(FormatUtils.fmtDate(nextExecDate)+" "+scmPriceUpdSet.getCalcFbcRptDataTime(), "yyyy-MM-dd HH:mm")) > 0)
						|| scmSystemTaskExecTime==null || FormatUtils.fmtDate(currDate).compareTo(StringUtils.left(scmSystemTaskExecTime.getExecTime(),10))!=0)){
					flag = true;
				}
				if(!flag){
					scmPriceUpdSetList.remove(i);
				}
				if(scmSystemTaskExecTime==null) {
					scmSystemTaskExecTime = new ScmSystemTaskExecTime(true);
					scmSystemTaskExecTime.setOrgUnitNo(scmPriceUpdSet.getOrgUnitNo());
					scmSystemTaskExecTime.setTaskType(type);
					scmSystemTaskExecTime.setExecTime(FormatUtils.fmtDateTimeMS(new Date()));
					scmSystemTaskExecTime.setControlUnitNo(param.getControlUnitNo());
					scmSystemTaskExecTimeBiz.add(scmSystemTaskExecTime, param);
				}else {
					scmSystemTaskExecTime.setExecTime(FormatUtils.fmtDateTimeMS(new Date()));
					scmSystemTaskExecTimeBiz.update(scmSystemTaskExecTime, param);
				}
			}
			return scmPriceUpdSetList;
		}
		return null;
	}
	
	private Map<String,String> getDeptCodeMap(String controlUnitNo, Param param){
		Map<String, String> map = new HashMap<String, String>();
		List<ScmPriceUpdSet2> scmPriceUpdSetList = scmPriceUpdSetBiz.selectByCtrl(controlUnitNo, param);
		if(scmPriceUpdSetList != null && !scmPriceUpdSetList.isEmpty()){
			for(ScmPriceUpdSet2 scmPriceUpdSet : scmPriceUpdSetList){
				if(StringUtils.isNotBlank(scmPriceUpdSet.getDeptCode()) && StringUtils.isNotBlank(scmPriceUpdSet.getDeptNo())
						&& !map.containsKey(scmPriceUpdSet.getDeptCode())){
					map.put(scmPriceUpdSet.getDeptCode(), scmPriceUpdSet.getDeptNo());
				}
			}
			return map;
		}
		return null;
	}
	
	public void excuteTaskThread(ScmSystemTask systemTask) {
		AppInfo appInfo = new AppInfo("iSCM",systemTask.getOrgUnitNo(),systemTask.getControlUnitNo());
		OperationParam param = new OperationParam();
		param.setControlUnitNo(appInfo.getControlUnitNo());
		String usrCode = AppServiceUtil.getAppServiceParamValue(appInfo.getControlUnitNo(),  appInfo.getAppName(), "usrCode", param);
		if (StringUtils.isBlank(usrCode)) {
			usrCode = "admin";
		}
		appInfo.setUsrCode(usrCode);
		if(systemTask != null && appInfo != null){
			if(systemTask.getTaskName().startsWith(ScmItemPriceUpdateJob.class.getSimpleName())){
				updateFbcItemPrice(appInfo);
			}else if(systemTask.getTaskName().startsWith(ScmSalePriceGetJob.class.getSimpleName())){
				getFbmSalePrice(appInfo);
			}else if(systemTask.getTaskName().startsWith(ScmBaseCostUpdateJob.class.getSimpleName())){
				updateFbcCostPrice(appInfo);
			}else if(systemTask.getTaskName().startsWith(ScmFbcCommonTaskJob.class.getSimpleName())){
				fbcComTaskScan(appInfo);
			}else if(systemTask.getTaskName().startsWith(ScmGenerateCostConsumeJob.class.getSimpleName())){
				generateCostConsume(appInfo);
			}else if(systemTask.getTaskName().startsWith(ScmCalcFbcRptDataJob.class.getSimpleName())){
				calcFbcRptData(appInfo);
			}
		}
	}
}
