package com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvOtherIssueBillWSBean;

public interface ScmInvOtherIssueBillService {
	
	@POST
	@Path("/findPage")
	public ScmInvOtherIssueBillWSBean findPage(ScmInvOtherIssueBillWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmInvOtherIssueBillWSBean queryPage(ScmInvOtherIssueBillWSBean bean);

	@POST
	@Path("/select")
	public ScmInvOtherIssueBillWSBean select(ScmInvOtherIssueBillWSBean bean);

	@POST
	@Path("/add")
	public ScmInvOtherIssueBillWSBean add(ScmInvOtherIssueBillWSBean bean);

	@POST
	@Path("/update")
	public ScmInvOtherIssueBillWSBean update(ScmInvOtherIssueBillWSBean bean);
	
	@POST
	@Path("/updateDirect")
	public ScmInvOtherIssueBillWSBean updateDirect(ScmInvOtherIssueBillWSBean bean);

	@POST
	@Path("/delete")
	public ScmInvOtherIssueBillWSBean delete(ScmInvOtherIssueBillWSBean bean);
	
	@POST
	@Path("/submit")
	public ScmInvOtherIssueBillWSBean submit(ScmInvOtherIssueBillWSBean bean);
	
	@POST
	@Path("/undoSubmit")
	public ScmInvOtherIssueBillWSBean undoSubmit(ScmInvOtherIssueBillWSBean bean);

	@POST
	@Path("/postBill")
	public ScmInvOtherIssueBillWSBean postBill(ScmInvOtherIssueBillWSBean bean);
	
	@POST
    @Path("/postBillCheck")
    public ScmInvOtherIssueBillWSBean postBillCheck(ScmInvOtherIssueBillWSBean bean);
   
	@POST
    @Path("/cancelPostBill")
    public ScmInvOtherIssueBillWSBean cancelPostBill(ScmInvOtherIssueBillWSBean bean);
	
	@POST
    @Path("/doAudit")
	public ScmInvOtherIssueBillWSBean doAudit(ScmInvOtherIssueBillWSBean bean);
	
	@POST
    @Path("/undoAudit")
	public ScmInvOtherIssueBillWSBean undoAudit(ScmInvOtherIssueBillWSBean bean);

	@POST
	@Path("/updatePrtCount")
	public ScmInvOtherIssueBillWSBean updatePrtCount(ScmInvOtherIssueBillWSBean bean);
}
