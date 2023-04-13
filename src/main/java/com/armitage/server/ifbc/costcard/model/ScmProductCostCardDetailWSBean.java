
package com.armitage.server.ifbc.costcard.model;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import com.armitage.server.common.base.model.BaseWSBean; 

@XmlRootElement(name = "ScmProductCostCardDetailWSBean")
public class ScmProductCostCardDetailWSBean extends BaseWSBean {
	
	private ScmProductCostCardDetail x;
	
	public ScmProductCostCardDetail getX() {
		return x;
	}
	
	public void setX(ScmProductCostCardDetail x) {
		this.x = x;
	}

}