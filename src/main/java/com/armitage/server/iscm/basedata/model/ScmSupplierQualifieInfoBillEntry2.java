package com.armitage.server.iscm.basedata.model;

import java.util.List;

import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmBaseAttachment2;

public class ScmSupplierQualifieInfoBillEntry2 extends ScmSupplierQualifieInfoBillEntry {
	private String typeName;
    private List<ScmBaseAttachment2> scmBaseAttachmentList;

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public List<ScmBaseAttachment2> getScmBaseAttachmentList() {
		return scmBaseAttachmentList;
	}

	public void setScmBaseAttachmentList(List<ScmBaseAttachment2> scmBaseAttachmentList) {
		this.scmBaseAttachmentList = scmBaseAttachmentList;
	}

	public ScmSupplierQualifieInfoBillEntry2(boolean defaultValue) {
		super(defaultValue);
		if(defaultValue){
		}
	}
	
	public ScmSupplierQualifieInfoBillEntry2(){
		super();
	}
}
