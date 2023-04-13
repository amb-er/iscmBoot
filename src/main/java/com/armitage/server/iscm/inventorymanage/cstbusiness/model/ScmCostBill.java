package com.armitage.server.iscm.inventorymanage.cstbusiness.model;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseModel;

@XmlRootElement(name = "scmCostBill")
public class ScmCostBill extends BaseModel{
	private long id;
	private String costOrgUnitNo;//成本中心
	private String orgUnitNo;//部门
	private Date postDate;
	private String billType;
	private long itemId;
	private String editor;
	private String billNo;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCostOrgUnitNo() {
		return costOrgUnitNo;
	}

	public void setCostOrgUnitNo(String costOrgUnitNo) {
		this.costOrgUnitNo = costOrgUnitNo;
	}

	public String getOrgUnitNo() {
		return orgUnitNo;
	}

	public void setOrgUnitNo(String orgUnitNo) {
		this.orgUnitNo = orgUnitNo;
	}

	public Date getPostDate() {
		return postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}

	public String getBillType() {
		return billType;
	}

	public void setBillType(String billType) {
		this.billType = billType;
	}

	public long getItemId() {
		return itemId;
	}

	public void setItemId(long itemId) {
		this.itemId = itemId;
	}

	@Override
	public String getPkKey() {
		return null;
	}

	@Override
	public long getPK() {
		return 0;
	}

	@Override
	public String[] getRequiredFields() {
		return null;
	}

	@Override
	public String[] getFieldNames() {
		return null;
	}

	@Override
	public List<String[]> getUniqueKeys() {
		return null;
	}

	public String getEditor() {
		return editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

}
