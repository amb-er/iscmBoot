package com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvSaleIssueBillEntryWSBean;

public interface ScmInvSaleIssueBillEntryService {
	
	@POST
	@Path("/findPage")
	public ScmInvSaleIssueBillEntryWSBean findPage(ScmInvSaleIssueBillEntryWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmInvSaleIssueBillEntryWSBean queryPage(ScmInvSaleIssueBillEntryWSBean bean);

	@POST
	@Path("/select")
	public ScmInvSaleIssueBillEntryWSBean select(ScmInvSaleIssueBillEntryWSBean bean);

	@POST
	@Path("/add")
	public ScmInvSaleIssueBillEntryWSBean add(ScmInvSaleIssueBillEntryWSBean bean);

	@POST
	@Path("/update")
	public ScmInvSaleIssueBillEntryWSBean update(ScmInvSaleIssueBillEntryWSBean bean);
	

	@POST
	@Path("/delete")
	public ScmInvSaleIssueBillEntryWSBean delete(ScmInvSaleIssueBillEntryWSBean bean);
	
}
