package com.armitage.server.iscm.common.model;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseModel;
 
@XmlRootElement(name = "commonAuditOpinion")  
public class CommonAuditOpinion extends BaseModel {
	private String activeType;
	private String stepNo;
	private String preStepNo;
	private String opinion;
	private String handlerContent;
	private String remarks;
	
	public String getActiveType() {
		return activeType;
	}
	public void setActiveType(String activeType) {
		this.activeType = activeType;
	}
	public String getStepNo() {
		return stepNo;
	}
	public void setStepNo(String stepNo) {
		this.stepNo = stepNo;
	}
	public String getPreStepNo() {
		return preStepNo;
	}
	public void setPreStepNo(String preStepNo) {
		this.preStepNo = preStepNo;
	}
	public String getOpinion() {
		return opinion;
	}
	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}
	public String getHandlerContent() {
		return handlerContent;
	}
	public void setHandlerContent(String handlerContent) {
		this.handlerContent = handlerContent;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	@Override
	public String getPkKey() {
		// TODO Auto-generated method stub
		return null;
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
