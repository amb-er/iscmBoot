package com.armitage.server.iscm.inventorymanage.inventorysetting.model;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import com.armitage.server.common.base.model.BaseWSBean; 

@XmlRootElement(name = "ScmInvWareHouseWSBean")
public class ScmInvWareHouseWSBean extends BaseWSBean {
	
	private ScmInvWareHouse x;
	private ScmInvAccreditWh2 y;
	private ScmInvWareHouseUsr2 z;
	
	public ScmInvWareHouse getX() {
		return x;
	}
	
	public void setX(ScmInvWareHouse x) {
		this.x = x;
	}

	public ScmInvAccreditWh2 getY() {
		return y;
	}

	public void setY(ScmInvAccreditWh2 y) {
		this.y = y;
	}

	public ScmInvWareHouseUsr2 getZ() {
		return z;
	}

	public void setZ(ScmInvWareHouseUsr2 z) {
		this.z = z;
	}

}