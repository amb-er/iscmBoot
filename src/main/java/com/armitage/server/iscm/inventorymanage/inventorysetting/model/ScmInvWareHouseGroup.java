
package com.armitage.server.iscm.inventorymanage.inventorysetting.model;

import java.util.List;

import com.armitage.server.common.base.model.BaseModel;

public class ScmInvWareHouseGroup extends  BaseModel {
	 
    public static final String FN_ID ="id";
    public static final String FN_PARENTID ="parentId";
    public static final String FN_GROUPNAME ="groupName";
    
    private long id;
    private long parentId;
    private String groupName;

    public long getId() {
        return id;
    }
    
    public void setId(long val) {
        this.id = val;
    }
    
    public long getParentId() {
		return parentId;
	}

	public void setParentId(long parentId) {
		this.parentId = parentId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public ScmInvWareHouseGroup(boolean defaultValue){
       if(defaultValue){
       }
    }
  	public ScmInvWareHouseGroup() {

	}

	@Override
	public String getPkKey() {
		// TODO Auto-generated method stub
		return FN_ID;
	}

	@Override
	public long getPK() {
		// TODO Auto-generated method stub
		return id;
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
