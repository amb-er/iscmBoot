package com.armitage.server.iscm.basedata.model;

public class ScmMaterialGroup2 extends ScmMaterialGroup {
  
    private boolean checkAll;
	
	public boolean isCheckAll() {
		return checkAll;
	}

	public void setCheckAll(boolean checkAll) {
		this.checkAll = checkAll;
	}

	public ScmMaterialGroup2(boolean defaultValue) {
		super(defaultValue);
		if(defaultValue){
			this.checkAll=false;
		}
	}
	
	public ScmMaterialGroup2(){
		super();
	}
}
