package com.armitage.server.ifbc.basedata.model;

public class ScmPriceUpdSet2 extends ScmPriceUpdSet{

    private String deptCode;//出品部门
    private String deptNo;//领料部门
	
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public String getDeptNo() {
		return deptNo;
	}
	public void setDeptNo(String deptNo) {
		this.deptNo = deptNo;
	}
	public ScmPriceUpdSet2() {
		super();
	}
	public ScmPriceUpdSet2(boolean defaultValue) {
		super(defaultValue);
	}
}
