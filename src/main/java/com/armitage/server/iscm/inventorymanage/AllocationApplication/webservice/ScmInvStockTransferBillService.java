package com.armitage.server.iscm.inventorymanage.AllocationApplication.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.iscm.inventorymanage.AllocationApplication.model.ScmInvStockTransferBillWSBean;

public interface ScmInvStockTransferBillService {
	
	@POST
	@Path("/findPage")
	public ScmInvStockTransferBillWSBean findPage(ScmInvStockTransferBillWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmInvStockTransferBillWSBean queryPage(ScmInvStockTransferBillWSBean bean);

	@POST
	@Path("/select")
	public ScmInvStockTransferBillWSBean select(ScmInvStockTransferBillWSBean bean);

	@POST
	@Path("/add")
	public ScmInvStockTransferBillWSBean add(ScmInvStockTransferBillWSBean bean);

	@POST
	@Path("/update")
	public ScmInvStockTransferBillWSBean update(ScmInvStockTransferBillWSBean bean);
	

	@POST
	@Path("/delete")
	public ScmInvStockTransferBillWSBean delete(ScmInvStockTransferBillWSBean bean);
	
	@POST
	@Path("/updateStatus")
	public ScmInvStockTransferBillWSBean updateStatus(ScmInvStockTransferBillWSBean bean);
	
	@POST
    @Path("/generateMoveIssue")
    public ScmInvStockTransferBillWSBean generateMoveIssue(ScmInvStockTransferBillWSBean bean);

	@POST
	@Path("/updatePrtCount")
	public ScmInvStockTransferBillWSBean updatePrtCount(ScmInvStockTransferBillWSBean bean);
}
