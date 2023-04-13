package com.armitage.server.iscm.inventorymanage.AllocationApplication.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.iscm.inventorymanage.AllocationApplication.model.ScmInvMoveReqBillWSBean;

@Path("/scmInvMoveReqBill/")
public interface ScmInvMoveReqBillService {

	@POST
	@Path("/findPage")
	public ScmInvMoveReqBillWSBean findPage(ScmInvMoveReqBillWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmInvMoveReqBillWSBean queryPage(ScmInvMoveReqBillWSBean bean);

	@POST
	@Path("/select")
	public ScmInvMoveReqBillWSBean select(ScmInvMoveReqBillWSBean bean);

	@POST
	@Path("/add")
	public ScmInvMoveReqBillWSBean add(ScmInvMoveReqBillWSBean bean);

	@POST
	@Path("/update")
	public ScmInvMoveReqBillWSBean update(ScmInvMoveReqBillWSBean bean);
	
	@POST
	@Path("/delete")
	public ScmInvMoveReqBillWSBean delete(ScmInvMoveReqBillWSBean bean);
	
	@POST
	@Path("/submit")
	public ScmInvMoveReqBillWSBean submit(ScmInvMoveReqBillWSBean bean);
	
	@POST
	@Path("/undoSubmit")
	public ScmInvMoveReqBillWSBean undoSubmit(ScmInvMoveReqBillWSBean bean);

	@POST
	@Path("/release")
	public ScmInvMoveReqBillWSBean release(ScmInvMoveReqBillWSBean bean);
	
	@POST
	@Path("/undoRelease")
	public ScmInvMoveReqBillWSBean undoRelease(ScmInvMoveReqBillWSBean bean);

	@POST
	@Path("/finish")
	public ScmInvMoveReqBillWSBean finish(ScmInvMoveReqBillWSBean bean);

	@POST
	@Path("/undoFinish")
	public ScmInvMoveReqBillWSBean undoFinish(ScmInvMoveReqBillWSBean bean);
	
	@POST
	@Path("/selectByReqId")
	public ScmInvMoveReqBillWSBean selectByReqId(ScmInvMoveReqBillWSBean bean);

	@POST
	@Path("/updatePrtCount")
	public ScmInvMoveReqBillWSBean updatePrtCount(ScmInvMoveReqBillWSBean bean);
}
