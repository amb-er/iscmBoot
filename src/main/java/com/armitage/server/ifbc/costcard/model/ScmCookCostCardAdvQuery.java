package com.armitage.server.ifbc.costcard.model;

import java.util.List;

import com.armitage.server.common.base.model.BaseModel;

public class ScmCookCostCardAdvQuery extends BaseModel{
	public static final String FN_TYPE = "type";
	
	private String type;
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String getPkKey() {
		// TODO Auto-generated method stub
		return null;
	}

	public ScmCookCostCardAdvQuery() {
		super();
	}

	public ScmCookCostCardAdvQuery(boolean flag) {
		if(flag) {
			type="A";
		}
	}
	@Override
	public long getPK() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String[] getRequiredFields() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getFieldNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String[]> getUniqueKeys() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
