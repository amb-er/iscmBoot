package com.armitage.server.iscm.inventorymanage.inventorysetting.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvAccreditWhWSBean;

public interface ScmInvAccreditWhService {
	
	@POST
	@Path("/findPage")
	public ScmInvAccreditWhWSBean findPage(ScmInvAccreditWhWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmInvAccreditWhWSBean queryPage(ScmInvAccreditWhWSBean bean);

	@POST
	@Path("/select")
	public ScmInvAccreditWhWSBean select(ScmInvAccreditWhWSBean bean);

	@POST
	@Path("/add")
	public ScmInvAccreditWhWSBean add(ScmInvAccreditWhWSBean bean);

	@POST
	@Path("/update")
	public ScmInvAccreditWhWSBean update(ScmInvAccreditWhWSBean bean);
	

	@POST
	@Path("/delete")
	public ScmInvAccreditWhWSBean delete(ScmInvAccreditWhWSBean bean);
	
	@POST
    @Path("/initEnd")
    public ScmInvAccreditWhWSBean initEnd(ScmInvAccreditWhWSBean bean);
	
	@POST
    @Path("/reverseInit")
    public ScmInvAccreditWhWSBean reverseInit(ScmInvAccreditWhWSBean bean);
}

