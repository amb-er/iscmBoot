package com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvSaleIssueBillWSBean;

public interface ScmInvSaleIssueBillService {
	
	@POST
	@Path("/findPage")
	public ScmInvSaleIssueBillWSBean findPage(ScmInvSaleIssueBillWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmInvSaleIssueBillWSBean queryPage(ScmInvSaleIssueBillWSBean bean);

	@POST
	@Path("/select")
	public ScmInvSaleIssueBillWSBean select(ScmInvSaleIssueBillWSBean bean);

	@POST
	@Path("/add")
	public ScmInvSaleIssueBillWSBean add(ScmInvSaleIssueBillWSBean bean);

	@POST
	@Path("/update")
	public ScmInvSaleIssueBillWSBean update(ScmInvSaleIssueBillWSBean bean);
	
	@POST
	@Path("/updateDirect")
	public ScmInvSaleIssueBillWSBean updateDirect(ScmInvSaleIssueBillWSBean bean);

	@POST
	@Path("/delete")
	public ScmInvSaleIssueBillWSBean delete(ScmInvSaleIssueBillWSBean bean);
	
	@POST
	@Path("/submit")
	public ScmInvSaleIssueBillWSBean submit(ScmInvSaleIssueBillWSBean bean);

	@POST
	@Path("/undoSubmit")
	public ScmInvSaleIssueBillWSBean undoSubmit(ScmInvSaleIssueBillWSBean bean);
    
	@POST
    @Path("/postBillCheck")
    public ScmInvSaleIssueBillWSBean postBillCheck(ScmInvSaleIssueBillWSBean bean);
	
	@POST
    @Path("/postBill")
    public ScmInvSaleIssueBillWSBean postBill(ScmInvSaleIssueBillWSBean bean);
	
	@POST
    @Path("/cancelPostBill")
    public ScmInvSaleIssueBillWSBean cancelPostBill(ScmInvSaleIssueBillWSBean bean);
	
	@POST
	@Path("/generateArInvoice")
	public ScmInvSaleIssueBillWSBean generateArInvoice(ScmInvSaleIssueBillWSBean bean);

	@POST
	@Path("/updatePrtCount")
	public ScmInvSaleIssueBillWSBean updatePrtCount(ScmInvSaleIssueBillWSBean bean);

	@POST
	@Path("/doAudit")
	public ScmInvSaleIssueBillWSBean doAudit(ScmInvSaleIssueBillWSBean bean);

	@POST
	@Path("/undoAudit")
	public ScmInvSaleIssueBillWSBean undoAudit(ScmInvSaleIssueBillWSBean bean);
}
