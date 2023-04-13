package com.armitage.server.quartz.job;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.armitage.server.quartz.model.ScmSystemTask;
import com.armitage.server.quartz.task.SupplierPlatTaskThread;

public class ScmPlatSupplierIdGetJob implements Job{
    private static Log log = LogFactory.getLog(ScmPlatSupplierIdGetJob.class);
   
    @Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
        try {
            //启动任务
            log.info("===========执行任务调度========"+arg0.getJobDetail().getKey().getName()+"====start");
            getPlatSupplierId(arg0.getJobDetail().getKey().getName());
            log.info("===========执行任务调度========"+arg0.getJobDetail().getKey().getName()+"====end");
        } catch (Exception e) {
            e.printStackTrace();
            log.info("==========="+arg0.getJobDetail().getKey().getName()+"=======ERROR=====" + e.getMessage());
        }
    }
    
    public void getPlatSupplierId(String jobName){
    	if(StringUtils.isNotBlank(jobName)){
    		String[] params = jobName.split("_");
    		if(params != null && params.length>=2){
    			ScmSystemTask systemTask = new ScmSystemTask(true);
    			systemTask.setTaskType("supplierRegInfoGet");
    			systemTask.setTaskName(jobName);
    			systemTask.setOrgUnitNo(params[1]);
    			systemTask.setControlUnitNo(params[1]);
    			SupplierPlatTaskThread executeTaskThread = new SupplierPlatTaskThread();
    			executeTaskThread.setSystemTask(systemTask);
    			executeTaskThread.setName("Execute_Sync_Task_"+jobName);
    			executeTaskThread.start();
    		}
    	}
    }
}
