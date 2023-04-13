package com.armitage.server.iscm.purchasemanage.purchasebusiness.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurReceiveWSBean;

public interface ScmPurReceiveService {
	
	@POST
	@Path("/findPage")
	public ScmPurReceiveWSBean findPage(ScmPurReceiveWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmPurReceiveWSBean queryPage(ScmPurReceiveWSBean bean);

	@POST
	@Path("/select")
	public ScmPurReceiveWSBean select(ScmPurReceiveWSBean bean);

	@POST
	@Path("/add")
	public ScmPurReceiveWSBean add(ScmPurReceiveWSBean bean);

	@POST
	@Path("/update")
	public ScmPurReceiveWSBean update(ScmPurReceiveWSBean bean);

	@POST
	@Path("/delete")
	public ScmPurReceiveWSBean delete(ScmPurReceiveWSBean bean);
	
	@POST
	@Path("/submit")
	public ScmPurReceiveWSBean submit(ScmPurReceiveWSBean bean);

	@POST
	@Path("/undoSubmit")
	public ScmPurReceiveWSBean undoSubmit(ScmPurReceiveWSBean bean);

	@POST
	@Path("/release")
	public ScmPurReceiveWSBean release(ScmPurReceiveWSBean bean);

	@POST
	@Path("/undoRelease")
	public ScmPurReceiveWSBean undoRelease(ScmPurReceiveWSBean bean);

	@POST
	@Path("/startReceive")
	public ScmPurReceiveWSBean startReceive(ScmPurReceiveWSBean bean);

	@POST
	@Path("/generateWrReceipts")
	public ScmPurReceiveWSBean generateWrReceipts(ScmPurReceiveWSBean bean);
	
	@POST
    @Path("/undoFinish")
    public ScmPurReceiveWSBean undoFinish(ScmPurReceiveWSBean bean);
	
	@POST
	@Path("/doAudit")
	public ScmPurReceiveWSBean doAudit(ScmPurReceiveWSBean bean);

	@POST
	@Path("/undoAudit")
	public ScmPurReceiveWSBean undoAudit(ScmPurReceiveWSBean bean);
	
	@POST
	@Path("/selectDrillBills")
	public ScmPurReceiveWSBean selectDrillBills(ScmPurReceiveWSBean bean);

	@POST
	@Path("/updatePrtCount")
	public ScmPurReceiveWSBean updatePrtCount(ScmPurReceiveWSBean bean);
}