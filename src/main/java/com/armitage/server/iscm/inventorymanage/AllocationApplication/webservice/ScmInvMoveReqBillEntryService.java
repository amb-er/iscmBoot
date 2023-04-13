package com.armitage.server.iscm.inventorymanage.AllocationApplication.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.iscm.inventorymanage.AllocationApplication.model.ScmInvMoveReqBillEntryWSBean;
import com.armitage.server.iscm.inventorymanage.AllocationApplication.model.ScmInvMoveReqBillWSBean;

@Path("/ScmInvMoveReqBillEntry/")
public interface ScmInvMoveReqBillEntryService {
	
	@POST
	@Path("/findPage")
	public ScmInvMoveReqBillEntryWSBean findPage(ScmInvMoveReqBillEntryWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmInvMoveReqBillEntryWSBean queryPage(ScmInvMoveReqBillEntryWSBean bean);

	@POST
	@Path("/select")
	public ScmInvMoveReqBillEntryWSBean select(ScmInvMoveReqBillEntryWSBean bean);

	@POST
	@Path("/add")
	public ScmInvMoveReqBillEntryWSBean add(ScmInvMoveReqBillEntryWSBean bean);

	@POST
	@Path("/update")
	public ScmInvMoveReqBillEntryWSBean update(ScmInvMoveReqBillEntryWSBean bean);
	

	@POST
	@Path("/delete")
	public ScmInvMoveReqBillEntryWSBean delete(ScmInvMoveReqBillEntryWSBean bean);
	
	@POST
	@Path("/findGrantPage")
	public ScmInvMoveReqBillEntryWSBean findGrantPage(ScmInvMoveReqBillEntryWSBean bean);
	
	@POST
	@Path("/updateStatus")
	public ScmInvMoveReqBillEntryWSBean updateStatus(ScmInvMoveReqBillEntryWSBean bean);
	
	@POST
	@Path("/pushBill")
	public ScmInvMoveReqBillEntryWSBean pushBill(ScmInvMoveReqBillEntryWSBean bean);
}
