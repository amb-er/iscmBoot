
package com.armitage.server.iscm.basedata.model;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import com.armitage.server.common.base.model.BaseWSBean; 

@XmlRootElement(name = "ScmIdleItemsWSBean")
public class ScmIdleItemsWSBean extends BaseWSBean {
	
	private ScmIdleItems x;
	private ScmIdleItems2 y;
	private ScmIdleItemsAdvQuery z;
	
	public ScmIdleItems getX() {
		return x;
	}
	
	public void setX(ScmIdleItems x) {
		this.x = x;
	}

	public ScmIdleItems2 getY() {
		return y;
	}

	public void setY(ScmIdleItems2 y) {
		this.y = y;
	}

	public ScmIdleItemsAdvQuery getZ() {
		return z;
	}

	public void setZ(ScmIdleItemsAdvQuery z) {
		this.z = z;
	}

}