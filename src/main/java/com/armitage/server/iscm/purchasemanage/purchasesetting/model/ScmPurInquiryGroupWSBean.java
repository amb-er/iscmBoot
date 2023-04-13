
package com.armitage.server.iscm.purchasemanage.purchasesetting.model;

import javax.xml.bind.annotation.XmlRootElement;
import com.armitage.server.common.base.model.BaseWSBean;

@XmlRootElement(name = "scmPurInquiryGroupWSBean")
public class ScmPurInquiryGroupWSBean extends BaseWSBean {
	
	private ScmPurInquiryGroup x;

	public ScmPurInquiryGroup getX() {
		return x;
	}

	public void setX(ScmPurInquiryGroup x) {
		this.x = x;
	}
	
	
}