package com.armitage.server.iscm.inventorymanage.AllocationApplication.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import com.armitage.server.iscm.inventorymanage.AllocationApplication.model.ScmInvStockTransferBillEntryWSBean;
import com.armitage.server.iscm.inventorymanage.AllocationApplication.model.ScmInvStockTransferBillWSBean;

public interface ScmInvStockTransferBillEntryService {
	
	@POST
	@Path("/findPage")
	public ScmInvStockTransferBillEntryWSBean findPage(ScmInvStockTransferBillEntryWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmInvStockTransferBillEntryWSBean queryPage(ScmInvStockTransferBillEntryWSBean bean);

	@POST
	@Path("/select")
	public ScmInvStockTransferBillEntryWSBean select(ScmInvStockTransferBillEntryWSBean bean);

	@POST
	@Path("/add")
	public ScmInvStockTransferBillEntryWSBean add(ScmInvStockTransferBillEntryWSBean bean);

	@POST
	@Path("/update")
	public ScmInvStockTransferBillEntryWSBean update(ScmInvStockTransferBillEntryWSBean bean);
	

	@POST
	@Path("/delete")
	public ScmInvStockTransferBillEntryWSBean delete(ScmInvStockTransferBillEntryWSBean bean);
	
}
