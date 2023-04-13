
package com.armitage.server.iscm.inventorymanage.countbusiness.model;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import com.armitage.server.common.base.model.BaseWSBean; 

@XmlRootElement(name = "ScmInvCountingListMaterialGroupWSBean")
public class ScmInvCountingListMaterialGroupWSBean extends BaseWSBean {
	
	private ScmInvCountingListMaterialGroup x;
	private ScmInvCountingListMaterialGroup y;
	
	public ScmInvCountingListMaterialGroup getX() {
		return x;
	}
	
	public void setX(ScmInvCountingListMaterialGroup x) {
		this.x = x;
	}

	public ScmInvCountingListMaterialGroup getY() {
		return y;
	}

	public void setY(ScmInvCountingListMaterialGroup y) {
		this.y = y;
	}

}