
package com.armitage.server.iscm.purchasemanage.pricemanage.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseWSBean;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurInquiryGroup;

@XmlRootElement(name = "scmPurQuotationPlanWSBean")
public class ScmPurQuotationPlanWSBean extends BaseWSBean {
	
	private ScmPurQuotationPlan x;
	private ScmPurQuotationPlanEntry2 y;
	private ScmPurInquiryGroup z;
	private ScmPurQuotationPlanAdvQuery a;
	
	public ScmPurQuotationPlan getX() {
		return x;
	}
	public void setX(ScmPurQuotationPlan x) {
		this.x = x;
	}
	public ScmPurQuotationPlanEntry2 getY() {
		return y;
	}
	public void setY(ScmPurQuotationPlanEntry2 y) {
		this.y = y;
	}
	public ScmPurInquiryGroup getZ() {
		return z;
	}
	public void setZ(ScmPurInquiryGroup z) {
		this.z = z;
	}
	public ScmPurQuotationPlanAdvQuery getA() {
		return a;
	}
	public void setA(ScmPurQuotationPlanAdvQuery a) {
		this.a = a;
	}
	
}