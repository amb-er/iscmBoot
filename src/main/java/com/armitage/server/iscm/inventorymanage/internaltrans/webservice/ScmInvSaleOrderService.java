package com.armitage.server.iscm.inventorymanage.internaltrans.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.iscm.inventorymanage.internaltrans.model.ScmInvSaleOrderWSBean;

public interface ScmInvSaleOrderService {
	
	@POST
	@Path("/findPage")
	public ScmInvSaleOrderWSBean findPage(ScmInvSaleOrderWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmInvSaleOrderWSBean queryPage(ScmInvSaleOrderWSBean bean);

	@POST
	@Path("/select")
	public ScmInvSaleOrderWSBean select(ScmInvSaleOrderWSBean bean);

	@POST
	@Path("/add")
	public ScmInvSaleOrderWSBean add(ScmInvSaleOrderWSBean bean);

	@POST
	@Path("/update")
	public ScmInvSaleOrderWSBean update(ScmInvSaleOrderWSBean bean);
	
	@POST
	@Path("/delete")
	public ScmInvSaleOrderWSBean delete(ScmInvSaleOrderWSBean bean);
	
	@POST
    @Path("/updateStatus")
    public ScmInvSaleOrderWSBean updateStatus(ScmInvSaleOrderWSBean bean);
    
	@POST
    @Path("/generateOutBoundOrders")
    public ScmInvSaleOrderWSBean generateOutBoundOrders(ScmInvSaleOrderWSBean bean);

	@POST
    @Path("/checkFollowUpBill")
    public ScmInvSaleOrderWSBean checkFollowUpBill(ScmInvSaleOrderWSBean bean);

	@POST
    @Path("/finish")
    public ScmInvSaleOrderWSBean finish(ScmInvSaleOrderWSBean bean);

	@POST
    @Path("/undoFinish")
    public ScmInvSaleOrderWSBean undoFinish(ScmInvSaleOrderWSBean bean);

	@POST
    @Path("/undoGenerateOutBoundOrders")
    public ScmInvSaleOrderWSBean undoGenerateOutBoundOrders(ScmInvSaleOrderWSBean bean);
	
	@POST
	@Path("/submit")
	public ScmInvSaleOrderWSBean submit(ScmInvSaleOrderWSBean bean);
	
	@POST
	@Path("/undoSubmit")
	public ScmInvSaleOrderWSBean undoSubmit(ScmInvSaleOrderWSBean bean);

	@POST
	@Path("/doAudit")
	public ScmInvSaleOrderWSBean doAudit(ScmInvSaleOrderWSBean bean);

	@POST
	@Path("/undoAudit")
	public ScmInvSaleOrderWSBean undoAudit(ScmInvSaleOrderWSBean bean);

	@POST
	@Path("/updatePrtCount")
	public ScmInvSaleOrderWSBean updatePrtCount(ScmInvSaleOrderWSBean bean);
}
