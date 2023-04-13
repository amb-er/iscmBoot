package com.armitage.server.quartz.task;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.armitage.server.quartz.model.ScmSystemTask;
import com.armitage.server.quartz.util.SupplierPlatUtil;

public class SupplierPlatTaskThread extends Thread{
	private static Log log = LogFactory.getLog(SupplierPlatTaskThread.class);
	
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
		try {
			log.info(systemTask.getTaskName()+"===========Start");
			SupplierPlatUtil supplierPlatUtil = new SupplierPlatUtil();
			supplierPlatUtil.excuteTaskThread(systemTask);
		} catch (Exception e) {
			log.error(e);
			throw e;
		}
    }
}
