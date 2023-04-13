
package com.armitage.server.ifbc.costcard.model;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import com.armitage.server.common.base.model.BaseWSBean; 

@XmlRootElement(name = "ScmProductCostCardDetailHistoryWSBean")
public class ScmProductCostCardDetailHistoryWSBean extends BaseWSBean {
	
	private ScmProductCostCardDetailHistory x;
	
	public ScmProductCostCardDetailHistory getX() {
		return x;
	}
	
	public void setX(ScmProductCostCardDetailHistory x) {
		this.x = x;
	}

}