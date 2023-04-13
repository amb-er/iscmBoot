package com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvMoveIssueBillWSBean;

public interface ScmInvMoveIssueBillService {
	
	@POST
	@Path("/findPage")
	public ScmInvMoveIssueBillWSBean findPage(ScmInvMoveIssueBillWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmInvMoveIssueBillWSBean queryPage(ScmInvMoveIssueBillWSBean bean);

	@POST
	@Path("/select")
	public ScmInvMoveIssueBillWSBean select(ScmInvMoveIssueBillWSBean bean);

	@POST
	@Path("/add")
	public ScmInvMoveIssueBillWSBean add(ScmInvMoveIssueBillWSBean bean);

	@POST
	@Path("/update")
	public ScmInvMoveIssueBillWSBean update(ScmInvMoveIssueBillWSBean bean);
	
	@POST
	@Path("/updateDirect")
	public ScmInvMoveIssueBillWSBean updateDirect(ScmInvMoveIssueBillWSBean bean);

	@POST
	@Path("/delete")
	public ScmInvMoveIssueBillWSBean delete(ScmInvMoveIssueBillWSBean bean);
	
	@POST
	@Path("/submit")
	public ScmInvMoveIssueBillWSBean submit(ScmInvMoveIssueBillWSBean bean);
	
	@POST
	@Path("/undoSubmit")
	public ScmInvMoveIssueBillWSBean undoSubmit(ScmInvMoveIssueBillWSBean bean);

	@POST
	@Path("/postBill")
	public ScmInvMoveIssueBillWSBean postBill(ScmInvMoveIssueBillWSBean bean);
	
	@POST
    @Path("/postBillCheck")
    public ScmInvMoveIssueBillWSBean postBillCheck(ScmInvMoveIssueBillWSBean bean);

	@POST
    @Path("/cancelPostBill")
	public ScmInvMoveIssueBillWSBean cancelPostBill(ScmInvMoveIssueBillWSBean bean);

	@POST
	@Path("/updatePrtCount")
	public ScmInvMoveIssueBillWSBean updatePrtCount(ScmInvMoveIssueBillWSBean bean);
}