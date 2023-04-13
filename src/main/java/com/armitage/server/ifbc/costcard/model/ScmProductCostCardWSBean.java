
package com.armitage.server.ifbc.costcard.model;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import com.armitage.server.common.base.model.BaseWSBean; 

@XmlRootElement(name = "ScmProductCostCardWSBean")
public class ScmProductCostCardWSBean extends BaseWSBean {
	
	private ScmProductCostCard2 x;
	private ScmProductCostCardDetail2 y;
	
	public ScmProductCostCardDetail2 getY() {
		return y;
	}

	public void setY(ScmProductCostCardDetail2 y) {
		this.y = y;
	}

	public ScmProductCostCard2 getX() {
		return x;
	}
	
	public void setX(ScmProductCostCard2 x) {
		this.x = x;
	}

}