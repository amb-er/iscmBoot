package com.armitage.server.iscm.inventorymanage.cstbusiness.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvMoveBillEntryWSBean;

public interface ScmInvMoveBillEntryService {
	
	@POST
	@Path("/findPage")
	public ScmInvMoveBillEntryWSBean findPage(ScmInvMoveBillEntryWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmInvMoveBillEntryWSBean queryPage(ScmInvMoveBillEntryWSBean bean);

	@POST
	@Path("/select")
	public ScmInvMoveBillEntryWSBean select(ScmInvMoveBillEntryWSBean bean);

	@POST
	@Path("/add")
	public ScmInvMoveBillEntryWSBean add(ScmInvMoveBillEntryWSBean bean);

	@POST
	@Path("/update")
	public ScmInvMoveBillEntryWSBean update(ScmInvMoveBillEntryWSBean bean);
	

	@POST
	@Path("/delete")
	public ScmInvMoveBillEntryWSBean delete(ScmInvMoveBillEntryWSBean bean);
	
}
