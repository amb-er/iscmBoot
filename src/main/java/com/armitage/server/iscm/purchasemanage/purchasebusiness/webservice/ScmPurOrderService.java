package com.armitage.server.iscm.purchasemanage.purchasebusiness.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurOrderWSBean;

public interface ScmPurOrderService {
	
	@POST
	@Path("/findPage")
	public ScmPurOrderWSBean findPage(ScmPurOrderWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmPurOrderWSBean queryPage(ScmPurOrderWSBean bean);

	@POST
	@Path("/select")
	public ScmPurOrderWSBean select(ScmPurOrderWSBean bean);

	@POST
	@Path("/add")
	public ScmPurOrderWSBean add(ScmPurOrderWSBean bean);

	@POST
	@Path("/update")
	public ScmPurOrderWSBean update(ScmPurOrderWSBean bean);
	

	@POST
	@Path("/delete")
	public ScmPurOrderWSBean delete(ScmPurOrderWSBean bean);
	
	@POST
	@Path("/submit")
	public ScmPurOrderWSBean submit(ScmPurOrderWSBean bean);

	@POST
	@Path("/undoSubmit")
	public ScmPurOrderWSBean undoSubmit(ScmPurOrderWSBean bean);

	@POST
	@Path("/release")
	public ScmPurOrderWSBean release(ScmPurOrderWSBean bean);

	@POST
	@Path("/undoRelease")
	public ScmPurOrderWSBean undoRelease(ScmPurOrderWSBean bean);
	
	@POST
	@Path("/sendOrder")
	public ScmPurOrderWSBean sendOrder(ScmPurOrderWSBean bean);
	
	@POST
	@Path("/unSendOrder")
	public ScmPurOrderWSBean unSendOrder(ScmPurOrderWSBean bean);

	@POST
	@Path("/doAudit")
	public ScmPurOrderWSBean doAudit(ScmPurOrderWSBean bean);

	@POST
	@Path("/undoAudit")
	public ScmPurOrderWSBean undoAudit(ScmPurOrderWSBean bean);

	@POST
	@Path("/getTotalOrderQty")
	public ScmPurOrderWSBean getTotalOrderQty(ScmPurOrderWSBean bean);
	
	@POST
	@Path("/unlockBill")
	public ScmPurOrderWSBean unlockBill(ScmPurOrderWSBean bean);
	
	@POST
	@Path("/selectDrillBills")
	public ScmPurOrderWSBean selectDrillBills(ScmPurOrderWSBean bean);

	@POST
	@Path("/updatePrtCount")
	public ScmPurOrderWSBean updatePrtCount(ScmPurOrderWSBean bean);
}

