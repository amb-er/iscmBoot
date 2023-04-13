package com.armitage.server.iscm.purchasemanage.purchasebusiness.webservice;


import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurRequireWSBean;

public interface ScmPurRequireService {
	
	@POST
	@Path("/findPage")
	public ScmPurRequireWSBean findPage(ScmPurRequireWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmPurRequireWSBean queryPage(ScmPurRequireWSBean bean);

	@POST
	@Path("/select")
	public ScmPurRequireWSBean select(ScmPurRequireWSBean bean);

	@POST
	@Path("/add")
	public ScmPurRequireWSBean add(ScmPurRequireWSBean bean);

	@POST
	@Path("/update")
	public ScmPurRequireWSBean update(ScmPurRequireWSBean bean);
	
	@POST
	@Path("/delete")
	public ScmPurRequireWSBean delete(ScmPurRequireWSBean bean);
	
	@POST
	@Path("/submit")
	public ScmPurRequireWSBean submit(ScmPurRequireWSBean bean);

	@POST
	@Path("/undoSubmit")
	public ScmPurRequireWSBean undoSubmit(ScmPurRequireWSBean bean);
	
	@POST
    @Path("/saveTemplete")
    public ScmPurRequireWSBean saveTemplete(ScmPurRequireWSBean bean);
	
	@POST
	@Path("/templeteMake")
	public ScmPurRequireWSBean templeteMake(ScmPurRequireWSBean bean);

	@POST
	@Path("/doAudit")
	public ScmPurRequireWSBean doAudit(ScmPurRequireWSBean bean);

	@POST
	@Path("/undoAudit")
	public ScmPurRequireWSBean undoAudit(ScmPurRequireWSBean bean);

	@POST
	@Path("/finish")
	public ScmPurRequireWSBean finish(ScmPurRequireWSBean bean);

	@POST
	@Path("/undoFinish")
	public ScmPurRequireWSBean undoFinish(ScmPurRequireWSBean bean);
	
	@POST
	@Path("/selectDrillBills")
	public ScmPurRequireWSBean selectDrillBills(ScmPurRequireWSBean bean);
	
	@POST
	@Path("/doEntryAudit")
	public ScmPurRequireWSBean doEntryAudit(ScmPurRequireWSBean bean);

	@POST
    @Path("/deleteTemplete")
    public ScmPurRequireWSBean deleteTemplete(ScmPurRequireWSBean bean);

	@POST
    @Path("/queryTemplete")
    public ScmPurRequireWSBean queryTemplete(ScmPurRequireWSBean bean);

	@POST
	@Path("/updatePrtCount")
	public ScmPurRequireWSBean updatePrtCount(ScmPurRequireWSBean bean);
}

