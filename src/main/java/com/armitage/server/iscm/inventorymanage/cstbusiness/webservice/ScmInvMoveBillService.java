package com.armitage.server.iscm.inventorymanage.cstbusiness.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvMoveBillWSBean;

public interface ScmInvMoveBillService {
	
	@POST
	@Path("/findPage")
	public ScmInvMoveBillWSBean findPage(ScmInvMoveBillWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmInvMoveBillWSBean queryPage(ScmInvMoveBillWSBean bean);

	@POST
	@Path("/select")
	public ScmInvMoveBillWSBean select(ScmInvMoveBillWSBean bean);

	@POST
	@Path("/add")
	public ScmInvMoveBillWSBean add(ScmInvMoveBillWSBean bean);

	@POST
	@Path("/update")
	public ScmInvMoveBillWSBean update(ScmInvMoveBillWSBean bean);
	
	@POST
	@Path("/updateDirect")
	public ScmInvMoveBillWSBean updateDirect(ScmInvMoveBillWSBean bean);

	@POST
	@Path("/delete")
	public ScmInvMoveBillWSBean delete(ScmInvMoveBillWSBean bean);
	
	@POST
	@Path("/submit")
	public ScmInvMoveBillWSBean submit(ScmInvMoveBillWSBean bean);

	@POST
	@Path("/undoSubmit")
	public ScmInvMoveBillWSBean undoSubmit(ScmInvMoveBillWSBean bean);
	
	@POST
    @Path("/postBillCheck")
    public ScmInvMoveBillWSBean postBillCheck(ScmInvMoveBillWSBean bean);
    
	@POST
    @Path("/postBill")
    public ScmInvMoveBillWSBean postBill(ScmInvMoveBillWSBean bean);
    
	@POST
    @Path("/cancelPostBill")
    public ScmInvMoveBillWSBean cancelPostBill(ScmInvMoveBillWSBean bean);
    
	@POST
    @Path("/cancelPostBillCheck")
    public ScmInvMoveBillWSBean cancelPostBillCheck(ScmInvMoveBillWSBean bean);

	@POST
	@Path("/doAudit")
	public ScmInvMoveBillWSBean doAudit(ScmInvMoveBillWSBean bean);

	@POST
	@Path("/undoAudit")
	public ScmInvMoveBillWSBean undoAudit(ScmInvMoveBillWSBean bean);

	@POST
	@Path("/updatePrtCount")
	public ScmInvMoveBillWSBean updatePrtCount(ScmInvMoveBillWSBean bean);
}
