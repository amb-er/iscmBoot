package com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvMoveIssueBillEntryWSBean;

public interface ScmInvMoveIssueBillEntryService {
	
	@POST
	@Path("/findPage")
	public ScmInvMoveIssueBillEntryWSBean findPage(ScmInvMoveIssueBillEntryWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmInvMoveIssueBillEntryWSBean queryPage(ScmInvMoveIssueBillEntryWSBean bean);

	@POST
	@Path("/select")
	public ScmInvMoveIssueBillEntryWSBean select(ScmInvMoveIssueBillEntryWSBean bean);

	@POST
	@Path("/add")
	public ScmInvMoveIssueBillEntryWSBean add(ScmInvMoveIssueBillEntryWSBean bean);

	@POST
	@Path("/update")
	public ScmInvMoveIssueBillEntryWSBean update(ScmInvMoveIssueBillEntryWSBean bean);
	

	@POST
	@Path("/delete")
	public ScmInvMoveIssueBillEntryWSBean delete(ScmInvMoveIssueBillEntryWSBean bean);
	
}
