package com.armitage.server.iscm.inventorymanage.countbusiness.webservice;


import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.iscm.inventorymanage.countbusiness.model.ScmInvCountingTableEntryWSBean;

public interface ScmInvCountingTableEntryService {
	
	@POST
	@Path("/findPage")
	public ScmInvCountingTableEntryWSBean findPage(ScmInvCountingTableEntryWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmInvCountingTableEntryWSBean queryPage(ScmInvCountingTableEntryWSBean bean);

	@POST
	@Path("/select")
	public ScmInvCountingTableEntryWSBean select(ScmInvCountingTableEntryWSBean bean);

	@POST
	@Path("/add")
	public ScmInvCountingTableEntryWSBean add(ScmInvCountingTableEntryWSBean bean);

	@POST
	@Path("/update")
	public ScmInvCountingTableEntryWSBean update(ScmInvCountingTableEntryWSBean bean);
	

	@POST
	@Path("/delete")
	public ScmInvCountingTableEntryWSBean delete(ScmInvCountingTableEntryWSBean bean);

}
