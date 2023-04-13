package com.armitage.server.iscm.basedata.model;

import java.util.List;

public class ScmCostUseSet2 extends ScmCostUseSet {
	public static final String FN_SCMCOSTUSETYPENAME = "scmCostUseTypeName";

	private String scmCostUseTypeName;
	private String scmCostUseTypeCode;
	private String saterialClassName;
	private String scmCostUseTypeOldName;
	private String orgUnitName;
	private String classCode;
	private long ScmCostUseSetId;
	private long groupId;
	private boolean checkAll;
	private String scmCostUseTypeIds;
	private long itemId;
	private List<ScmCostUseType> scmCostUseTypeList;

	public String getScmCostUseTypeOldName() {
		return scmCostUseTypeOldName;
	}

	public void setScmCostUseTypeOldName(String scmCostUseTypeOldName) {
		this.scmCostUseTypeOldName = scmCostUseTypeOldName;
	}

	public List<ScmCostUseType> getScmCostUseTypeList() {
		return scmCostUseTypeList;
	}

	public void setScmCostUseTypeList(List<ScmCostUseType> scmCostUseTypeList) {
		this.scmCostUseTypeList = scmCostUseTypeList;
	}

	public String getScmCostUseTypeIds() {
		return scmCostUseTypeIds;
	}

	public void setScmCostUseTypeIds(String scmCostUseTypeIds) {
		this.scmCostUseTypeIds = scmCostUseTypeIds;
	}

	public long getGroupId() {
		return groupId;
	}

	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}

	public long getScmCostUseSetId() {
		return ScmCostUseSetId;
	}

	public void setScmCostUseSetId(long scmCostUseSetId) {
		ScmCostUseSetId = scmCostUseSetId;
	}

	public boolean isCheckAll() {
		return checkAll;
	}

	public void setCheckAll(boolean checkAll) {
		this.checkAll = checkAll;
	}

	public String getClassCode() {
		return classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}

	public String getScmCostUseTypeName() {
		return scmCostUseTypeName;
	}

	public String getSaterialClassName() {
		return saterialClassName;
	}
	
	public String getScmCostUseTypeCode() {
		return scmCostUseTypeCode;
	}

	public void setScmCostUseTypeCode(String scmCostUseTypeCode) {
		this.scmCostUseTypeCode = scmCostUseTypeCode;
	}

	public void setSaterialClassName(String saterialClassName) {
		this.saterialClassName = saterialClassName;
	}

	public String getOrgUnitName() {
		return orgUnitName;
	}

	public void setOrgUnitName(String orgUnitName) {
		this.orgUnitName = orgUnitName;
	}

	public void setScmCostUseTypeName(String scmCostUseTypeName) {
		this.scmCostUseTypeName = scmCostUseTypeName;
	}
	
	public long getItemId() {
		return itemId;
	}

	public void setItemId(long itemId) {
		this.itemId = itemId;
	}

	public ScmCostUseSet2(boolean defaultValue) {
		super(defaultValue);
		if(defaultValue){
			
		}
	}
	
	public ScmCostUseSet2(){
		super();
	}

}
