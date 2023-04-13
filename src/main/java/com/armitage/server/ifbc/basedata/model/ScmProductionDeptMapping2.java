package com.armitage.server.ifbc.basedata.model;

public class ScmProductionDeptMapping2 extends ScmProductionDeptMapping  {
	 
    public static final String FN_DEPTNAME ="deptName";
    
    private String deptName;
    private String productDeptName;

    public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getProductDeptName() {
		return productDeptName;
	}
	public void setProductDeptName(String productDeptName) {
		this.productDeptName = productDeptName;
	}
	public ScmProductionDeptMapping2() {
		super();
	}
	public ScmProductionDeptMapping2(boolean defaultValue) {
		super(defaultValue);
	}
	
}