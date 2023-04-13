package com.armitage.server.iscm.inventorymanage.countbusiness.webservice;


import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.iscm.inventorymanage.countbusiness.model.ScmInvCountingTableWSBean;

public interface ScmInvCountingTableService {
	
	@POST
	@Path("/findPage")
	public ScmInvCountingTableWSBean findPage(ScmInvCountingTableWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmInvCountingTableWSBean queryPage(ScmInvCountingTableWSBean bean);

	@POST
	@Path("/select")
	public ScmInvCountingTableWSBean select(ScmInvCountingTableWSBean bean);

	@POST
	@Path("/add")
	public ScmInvCountingTableWSBean add(ScmInvCountingTableWSBean bean);

	@POST
	@Path("/update")
	public ScmInvCountingTableWSBean update(ScmInvCountingTableWSBean bean);
	

	@POST
	@Path("/delete")
	public ScmInvCountingTableWSBean delete(ScmInvCountingTableWSBean bean);
	
	@POST
	@Path("/findDifference")
	public ScmInvCountingTableWSBean findDifference(ScmInvCountingTableWSBean bean);
	
}

