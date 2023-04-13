package com.armitage.server.iscm.common.model;

public class TaskSettingDetail2 extends TaskSettingDetail{

	private String taskCode;
	private String channel;
	private String productCode;
	private int maxDays;
    private int taskDays;
	
	public String getTaskCode() {
		return taskCode;
	}

	public void setTaskCode(String taskCode) {
		this.taskCode = taskCode;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public int getMaxDays() {
		return maxDays;
	}

	public void setMaxDays(int maxDays) {
		this.maxDays = maxDays;
	}

	public int getTaskDays() {
		return taskDays;
	}

	public void setTaskDays(int taskDays) {
		this.taskDays = taskDays;
	}

	public TaskSettingDetail2(boolean defaultValue) {
		super(defaultValue);
		if(defaultValue) {
		}
	}
	
	public TaskSettingDetail2(){
		super();
	}
}
