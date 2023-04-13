package com.armitage.server.iscm.activityTask.audit;

import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.armitage.server.activity.util.ActivityUtil;

public class DeleteJobThread extends Thread{
	private static Log log = LogFactory.getLog(DeleteJobThread.class);
	
	private String processInstanceId;
	
	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public void run() {
		try {
			TimeUnit.SECONDS.sleep(30);
		} catch (InterruptedException e1) {
			log.error(e1);
		}
		try {
			ActivityUtil.deleteJob(processInstanceId);
		} catch (Exception e) {
			log.error(e);
		}
		
    }
}
