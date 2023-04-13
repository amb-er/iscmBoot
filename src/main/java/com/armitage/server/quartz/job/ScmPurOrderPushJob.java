package com.armitage.server.quartz.job;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.armitage.server.quartz.model.ScmSystemTask;
import com.armitage.server.quartz.task.SupplierPlatTaskThread;
import com.armitage.server.quartz.util.QuartzUtil;

public class ScmPurOrderPushJob implements Job{
	//private final static int excuteTimes = 9;
    private static Log log = LogFactory.getLog(ScmPurOrderPushJob.class);
   
    @Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
        try {
            //启动任务
            log.info("===========执行任务调度========"+arg0.getJobDetail().getKey().getName()+"====start");
            purOrderPush(arg0.getJobDetail().getKey().getName());
            log.info("===========执行任务调度========"+arg0.getJobDetail().getKey().getName()+"====end");
        } catch (Exception e) {
            e.printStackTrace();
            log.info("==========="+arg0.getJobDetail().getKey().getName()+"=======ERROR=====" + e.getMessage());
        }
    }
    
    public void purOrderPush(String jobName){
    	if(StringUtils.isNotBlank(jobName)){
    		String[] params = jobName.split("_");
    		if(params != null && params.length>=3){
    			ScmSystemTask systemTask = new ScmSystemTask(true);
				systemTask.setTaskType("purOrderPush");
				systemTask.setOrgUnitNo(params[1]);
				systemTask.setTaskName(ScmPurOrderPushJob.class.getSimpleName());
				systemTask.setTaskGroup("iSCM");
				systemTask.setTaskClass(ScmPurOrderPushJob.class.getName());
				systemTask.setFlag(true);
				systemTask.setControlUnitNo(params[1]);
				QuartzUtil quartzUtil = new QuartzUtil();
				quartzUtil.removeJobByBillId(systemTask, Long.parseLong(params[2]));
    		}else if(params != null && params.length>=2){
    			ScmSystemTask systemTask = new ScmSystemTask(true);
    			systemTask.setTaskType("purOrderPush");
    			systemTask.setTaskName(jobName);
    			systemTask.setOrgUnitNo(params[1]);
    			systemTask.setControlUnitNo(params[1]);
    			SupplierPlatTaskThread executeTaskThread = new SupplierPlatTaskThread();
    			executeTaskThread.setSystemTask(systemTask);
    			executeTaskThread.setName("Execute_Sync_Task_"+jobName);
    			executeTaskThread.start();
    		}
    	}
    	/*if(StringUtils.isNotBlank(jobName)){
    		String[] params = jobName.split("_");
    		if(params != null && params.length>=3){
    			Param param = new Param();
    			param.setOrgUnitNo(params[1]);
    			param.setControlUnitNo(params[1]);
    			Integer count = (Integer) jobDataMap.get("count");  
                if(count==null){  
                    count=1;  
                } 
    			QuartzUtil quartzUtil = new QuartzUtil();
    			if(count > excuteTimes){
    				ScmSystemTask systemTask = new ScmSystemTask(true);
					systemTask.setTaskType("purOrderPush");
					systemTask.setOrgUnitNo(param.getControlUnitNo());
					systemTask.setTaskName(ScmPurOrderPushJob.class.getSimpleName());
					systemTask.setTaskGroup("iSCM");
					//systemTask.setCronExpression("0 0/1 * * * ?");
					systemTask.setTaskClass(ScmPurOrderPushJob.class.getName());
					systemTask.setFlag(true);
					systemTask.setControlUnitNo(param.getControlUnitNo());
					quartzUtil.removeJobByBillId(systemTask, Long.parseLong(params[2]));
    				return;
    			}
    			log.info("执行订货单推送次数："+Long.parseLong(params[2])+"_"+count);
    			try {
					Thread.sleep(6000);
				} catch (InterruptedException e) {
					log.error(e);
				}
    			BaseJob bJob = new BaseJob();
				bJob.setJobName(ScmPurOrderPushJob.class.getSimpleName()+"_"+param.getControlUnitNo()+"_"+Long.parseLong(params[2]));
				bJob.setJobGroupName("iSCM");
				bJob.setTriggerGroupName("iSCM");
				bJob.setCronTime("0 0/1 * * * ?");
				count++;  
	            jobDataMap.put("count", count);
	            bJob.setJobDataMap(jobDataMap);
				quartzUtil.modifyJobDateMap(null, bJob);
    			PurOrderPushTaskThread executeTaskThread = new PurOrderPushTaskThread();
    			executeTaskThread.setPurOrderId(Long.parseLong(params[2]));
    			executeTaskThread.setAdd(true);
    			executeTaskThread.setParam(param);
    			executeTaskThread.setName("Execute_Sync_Task_PurOrderPush_"+params[2]);
    			executeTaskThread.start();
    		}
    	}*/
    }
}
