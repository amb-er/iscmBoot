package com.armitage.server.quartz.task;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.armitage.server.quartz.model.ScmSystemTask;
import com.armitage.server.quartz.util.ScmTaskUtil;

public class ExecuteTaskThread extends Thread{
	private static Log log = LogFactory.getLog(ExecuteTaskThread.class);
	
	private ScmSystemTask systemTask;
	
	public ScmSystemTask getSystemTask() {
		return systemTask;
	}

	public void setSystemTask(ScmSystemTask systemTask) {
		this.systemTask = systemTask;
	}

	@Override
	public void run() {
		doWork();
		
    }
	
	private void doWork(){
		log.info(systemTask.getTaskName()+"===========Start");
		ScmTaskUtil scmTaskUtil = new ScmTaskUtil();
		scmTaskUtil.excuteTaskThread(systemTask);
    }
}
