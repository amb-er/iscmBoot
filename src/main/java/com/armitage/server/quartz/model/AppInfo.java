package com.armitage.server.quartz.model;

public class AppInfo {
	/**iSCMApi*/
	public static final String APP_NAME = "iESP";
	
	private String appName;
	private String orgUnitNo;
	private String url;
	private String usrCode;
	private String pass;
	private String controlUnitNo;
	
	
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getOrgUnitNo() {
		return orgUnitNo;
	}
	public void setOrgUnitNo(String orgUnitNo) {
		this.orgUnitNo = orgUnitNo;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUsrCode() {
		return usrCode;
	}
	public void setUsrCode(String usrCode) {
		this.usrCode = usrCode;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getControlUnitNo() {
		return controlUnitNo;
	}
	public void setControlUnitNo(String controlUnitNo) {
		this.controlUnitNo = controlUnitNo;
	}
	public AppInfo() {
		super();
	}
	public AppInfo(String appName, String orgUnitNo, String controlUnitNo) {
		super();
		this.appName = appName;
		this.orgUnitNo = orgUnitNo;
		this.controlUnitNo = controlUnitNo;
	}
	
}
