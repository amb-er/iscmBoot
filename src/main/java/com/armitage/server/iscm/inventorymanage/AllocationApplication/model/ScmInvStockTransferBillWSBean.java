package com.armitage.server.iscm.inventorymanage.AllocationApplication.model;


import javax.xml.bind.annotation.XmlRootElement;
import com.armitage.server.common.base.model.BaseWSBean; 

@XmlRootElement(name = "ScmInvStockTransferBillWSBean")
public class ScmInvStockTransferBillWSBean extends BaseWSBean {
	
	private ScmInvStockTransferBill2 x;
	private ScmInvStockTransferBillEntry2 y;
	public ScmInvStockTransferBill2 getX() {
		return x;
	}
	public void setX(ScmInvStockTransferBill2 x) {
		this.x = x;
	}
	public ScmInvStockTransferBillEntry2 getY() {
		return y;
	}
	public void setY(ScmInvStockTransferBillEntry2 y) {
		this.y = y;
	}
	
}