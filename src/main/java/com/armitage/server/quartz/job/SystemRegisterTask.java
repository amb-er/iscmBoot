package com.armitage.server.quartz.job;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.PersistJobDataAfterExecution;
import org.quartz.SchedulerContext;
import org.quartz.SchedulerException;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.armitage.server.common.base.model.Param;
import com.armitage.server.iscm.basedata.dao.ScmSupplierDemanderDAO;
import com.armitage.server.iscm.basedata.model.ScmSupplierDemander;
import com.armitage.server.quartz.dao.ScmSystemTaskDAO;
import com.armitage.server.quartz.model.BaseJob;
import com.armitage.server.quartz.model.ScmSystemTask;
import com.armitage.server.quartz.service.ScmSystemTaskBiz;
import com.armitage.server.quartz.util.QuartzUtil;
import org.springframework.scheduling.quartz.QuartzJobBean;


/**
 * Quartz的任务调度
 * @author 
 *
 */
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class SystemRegisterTask extends QuartzJobBean {
	private static Log log = LogFactory.getLog(SystemRegisterTask.class);

	private static final long serialVersionUID = 1L;

	@Autowired
	private ScmSystemTaskDAO scmSystemTaskDAO;
	
	@Autowired
	private ScmSupplierDemanderDAO scmSupplierDemanderDAO;
	
	@Autowired
	private ScmSystemTaskBiz scmSystemTaskBiz;

	@Override
	protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		try {
			//获取JobExecutionContext中的service对象
			SchedulerContext schCtx = jobExecutionContext.getScheduler().getContext();
			//获取Spring中的上下文
			ApplicationContext appCtx = (ApplicationContext)schCtx.get("applicationContext");
			scmSystemTaskDAO = (ScmSystemTaskDAO) appCtx.getBean("scmSystemTaskDAO");
			scmSupplierDemanderDAO = (ScmSupplierDemanderDAO) appCtx.getBean("scmSupplierDemanderDAO");
			scmSystemTaskBiz = (ScmSystemTaskBiz) appCtx.getBean("scmSystemTaskBiz");
		} catch (SchedulerException e1) {
			// TODO 尚未处理异常
			e1.printStackTrace();
		}
		init();
	}
	
    public void init(){
    	List<ScmSystemTask> systemTaskList = scmSystemTaskDAO.findAll();
    	QuartzUtil quartzUtil = new QuartzUtil();

    	if (systemTaskList != null && !systemTaskList.isEmpty()) {
    		boolean needStart = false;
			for (ScmSystemTask bean : systemTaskList) {
				if (!bean.isFlag()) {
					continue;
				}
				JobDataMap jobDataMap = new JobDataMap();
				BaseJob bJob = new BaseJob();
				bJob.setJobName(bean.getTaskName()+"_"+bean.getOrgUnitNo());
				bJob.setJobGroupName(bean.getTaskGroup());
				bJob.setTriggerGroupName(bean.getTaskGroup());
				bJob.setCronTime(bean.getCronExpression());
				bJob.setJobDataMap(jobDataMap);
				Class cla = null;
				try {
					cla = Class.forName(bean.getTaskClass());
				} catch (ArrayIndexOutOfBoundsException e) {
					log.info("没有指定类名称" + bean.getTaskClass());
				} catch (ClassNotFoundException e) {
					log.info("找不到指定的类" + bean.getTaskClass());
				}
				if(!quartzUtil.isJobKey(bJob.getJobName(), bJob.getJobGroupName())){
					quartzUtil.modifyJobTime(bJob.getJobName(), bJob.getCronTime(), bJob.getJobGroupName(), bJob.getTriggerGroupName(), jobDataMap);
				}else{
					needStart = true;
					try {
						JobKey jobKey = JobKey.jobKey(bJob.getJobName(), bJob.getJobGroupName());
						TriggerKey triggerKey = TriggerKey.triggerKey(bJob.getJobName(), bJob.getTriggerGroupName());
						// 判断是否有job和trigger
						if (jobKey != null && triggerKey != null) {
							quartzUtil.removeJob(quartzUtil.getScheduler(), triggerKey, jobKey);
						}
					} catch (SchedulerException e) {
						e.printStackTrace();
					}
					quartzUtil.addJobByCronTrigger(cla, bJob);
				}
			}
			if (needStart) {
				quartzUtil.startJobs();
			}
		}

    }
    
    public void generateJob(){
    	List<ScmSupplierDemander> scmSupplierDemanderList = scmSupplierDemanderDAO.findAll();
    	if (scmSupplierDemanderList != null && !scmSupplierDemanderList.isEmpty()) {
    		QuartzUtil quartzUtil = new QuartzUtil();
			for (ScmSupplierDemander scmSupplierDemander : scmSupplierDemanderList) {
				if(StringUtils.isNotBlank(scmSupplierDemander.getUniqueNo())
						&& StringUtils.isNotBlank(scmSupplierDemander.getControlUnitNo())
						&& scmSupplierDemander.getDemanderId() > 0){
					Param param = new Param();
		    		param.setOrgUnitNo(scmSupplierDemander.getControlUnitNo());
		    		param.setControlUnitNo(scmSupplierDemander.getControlUnitNo());
		    		//获取供应商对照
					ScmSystemTask scmPlatSupplierIdGetJob = getScmPlatSupplierIdGetJob(scmSupplierDemander.getControlUnitNo());
		    		List<ScmSystemTask> scmPlatSupplierIdGetJobList = scmSystemTaskBiz.selectByTask(scmPlatSupplierIdGetJob, param);
		    		//确认信息获取
					ScmSystemTask scmSupplierConfirmDataGetJob = getScmSupplierConfirmDataGetJob(scmSupplierDemander.getControlUnitNo());
		    		List<ScmSystemTask> scmSupplierConfirmDataGetJobList = scmSystemTaskBiz.selectByTask(scmSupplierConfirmDataGetJob, param);
		    		//采购订单推送
					ScmSystemTask scmPurOrderPushJob = getScmPurOrderPushJob(scmSupplierDemander.getControlUnitNo());
		    		List<ScmSystemTask> scmPurOrderPushJobList = scmSystemTaskBiz.selectByTask(scmPurOrderPushJob, param);
		    		//采购入库单推送
					ScmSystemTask ScmInvPurInWarehsBillPushJob = getScmInvPurInWarehsBillPushJob(scmSupplierDemander.getControlUnitNo());
		    		List<ScmSystemTask> ScmInvPurInWarehsBillPushJobList = scmSystemTaskBiz.selectByTask(ScmInvPurInWarehsBillPushJob, param);
					if(scmSupplierDemander.getDemanderId() > 0){
			    		if(scmPlatSupplierIdGetJobList == null || scmPlatSupplierIdGetJobList.isEmpty()){
							quartzUtil.addJobBySystemTask(scmPlatSupplierIdGetJob);
							scmPlatSupplierIdGetJob.setFlag(false);
			    			scmSystemTaskBiz.add(scmPlatSupplierIdGetJob, param);
			    		}
			    		if(scmSupplierConfirmDataGetJobList == null || scmSupplierConfirmDataGetJobList.isEmpty()){
			    			quartzUtil.addJobBySystemTask(scmSupplierConfirmDataGetJob);
			    			scmSupplierConfirmDataGetJob.setFlag(false);
			    			scmSystemTaskBiz.add(scmSupplierConfirmDataGetJob, param);
			    		}
			    		if(scmPurOrderPushJobList == null || scmPurOrderPushJobList.isEmpty()){
			    			quartzUtil.addJobBySystemTask(scmPurOrderPushJob);
			    			scmPurOrderPushJob.setFlag(false);
			    			scmSystemTaskBiz.add(scmPurOrderPushJob, param);
			    		}
			    		if(ScmInvPurInWarehsBillPushJobList == null || ScmInvPurInWarehsBillPushJobList.isEmpty()){
			    			quartzUtil.addJobBySystemTask(ScmInvPurInWarehsBillPushJob);
			    			ScmInvPurInWarehsBillPushJob.setFlag(false);
			    			scmSystemTaskBiz.add(ScmInvPurInWarehsBillPushJob, param);
			    		}
					}
				}
			}
    	}
    }
    
    public ScmSystemTask getScmPlatSupplierIdGetJob(String orgUnitNo){
    	ScmSystemTask systemTask = new ScmSystemTask(true);
		systemTask.setTaskType("supplierRegInfoGet");
		systemTask.setOrgUnitNo(orgUnitNo);
		systemTask.setTaskName(ScmPlatSupplierIdGetJob.class.getSimpleName());
		systemTask.setTaskGroup("iSCM");
		systemTask.setCronExpression("0 0/1 * * * ?");
		systemTask.setTaskClass(ScmPlatSupplierIdGetJob.class.getName());
		systemTask.setFlag(true);
		systemTask.setUpdateTime(null);
		systemTask.setSize(20);
		systemTask.setControlUnitNo(orgUnitNo);
		return systemTask;
    }
    
    public ScmSystemTask getScmSupplierConfirmDataGetJob(String orgUnitNo){
    	ScmSystemTask systemTask = new ScmSystemTask(true);
		systemTask.setTaskType("supplierConfirmDataGet");
		systemTask.setOrgUnitNo(orgUnitNo);
		systemTask.setTaskName(ScmSupplierConfirmDataGetJob.class.getSimpleName());
		systemTask.setTaskGroup("iSCM");
		systemTask.setCronExpression("0 0/1 * * * ?");
		systemTask.setTaskClass(ScmSupplierConfirmDataGetJob.class.getName());
		systemTask.setFlag(true);
		systemTask.setUpdateTime(null);
		systemTask.setSize(20);
		systemTask.setControlUnitNo(orgUnitNo);
		return systemTask;
    }
    
    public ScmSystemTask getScmPurOrderPushJob(String orgUnitNo){
    	ScmSystemTask systemTask = new ScmSystemTask(true);
		systemTask.setTaskType("purOrderPush");
		systemTask.setOrgUnitNo(orgUnitNo);
		systemTask.setTaskName(ScmPurOrderPushJob.class.getSimpleName());
		systemTask.setTaskGroup("iSCM");
		systemTask.setCronExpression("0 0/2 * * * ?");
		systemTask.setTaskClass(ScmPurOrderPushJob.class.getName());
		systemTask.setFlag(true);
		systemTask.setUpdateTime(new Date());
		systemTask.setSize(5);
		systemTask.setControlUnitNo(orgUnitNo);
		return systemTask;
    }
    
    public ScmSystemTask getScmInvPurInWarehsBillPushJob(String orgUnitNo){
    	ScmSystemTask systemTask = new ScmSystemTask(true);
		systemTask.setTaskType("invPurInWarehsBillPush");
		systemTask.setOrgUnitNo(orgUnitNo);
		systemTask.setTaskName(ScmInvPurInWarehsBillPushJob.class.getSimpleName());
		systemTask.setTaskGroup("iSCM");
		systemTask.setCronExpression("0 0/2 * * * ?");
		systemTask.setTaskClass(ScmInvPurInWarehsBillPushJob.class.getName());
		systemTask.setFlag(true);
		systemTask.setUpdateTime(new Date());
		systemTask.setSize(5);
		systemTask.setControlUnitNo(orgUnitNo);
		return systemTask;
    }


}