package com.armitage.server.iscm.basedata.model;

public class ScmSupplierEvent2 extends ScmSupplierEvent{
	public static final String FN_EVENTTYPENAME = "eventTypeName";			//活动类型名称
	
	private String eventTypeName;

	public String getEventTypeName() {
		return eventTypeName;
	}

	public void setEventTypeName(String eventTypeName) {
		this.eventTypeName = eventTypeName;
	}

	public ScmSupplierEvent2(boolean defaultValue) {
		super(defaultValue);
		if(defaultValue){
		}
	}
	
	public ScmSupplierEvent2(){
		super();
	}
}
