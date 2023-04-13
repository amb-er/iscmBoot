package com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvOtherIssueBillEntryWSBean;

public interface ScmInvOtherIssueBillEntryService {
	
	@POST
	@Path("/findPage")
	public ScmInvOtherIssueBillEntryWSBean findPage(ScmInvOtherIssueBillEntryWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmInvOtherIssueBillEntryWSBean queryPage(ScmInvOtherIssueBillEntryWSBean bean);

	@POST
	@Path("/select")
	public ScmInvOtherIssueBillEntryWSBean select(ScmInvOtherIssueBillEntryWSBean bean);

	@POST
	@Path("/add")
	public ScmInvOtherIssueBillEntryWSBean add(ScmInvOtherIssueBillEntryWSBean bean);

	@POST
	@Path("/update")
	public ScmInvOtherIssueBillEntryWSBean update(ScmInvOtherIssueBillEntryWSBean bean);
	

	@POST
	@Path("/delete")
	public ScmInvOtherIssueBillEntryWSBean delete(ScmInvOtherIssueBillEntryWSBean bean);
	
}
