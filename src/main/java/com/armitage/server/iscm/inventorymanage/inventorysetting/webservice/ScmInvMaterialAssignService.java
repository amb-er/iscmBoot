package com.armitage.server.iscm.inventorymanage.inventorysetting.webservice;


import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvMaterialAssignWSBean;

public interface ScmInvMaterialAssignService {
	
	@POST
	@Path("/findPage")
	public ScmInvMaterialAssignWSBean findPage(ScmInvMaterialAssignWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmInvMaterialAssignWSBean queryPage(ScmInvMaterialAssignWSBean bean);

	@POST
	@Path("/select")
	public ScmInvMaterialAssignWSBean select(ScmInvMaterialAssignWSBean bean);

	@POST
	@Path("/add")
	public ScmInvMaterialAssignWSBean add(ScmInvMaterialAssignWSBean bean);

	@POST
	@Path("/update")
	public ScmInvMaterialAssignWSBean update(ScmInvMaterialAssignWSBean bean);
	

	@POST
	@Path("/delete")
	public ScmInvMaterialAssignWSBean delete(ScmInvMaterialAssignWSBean bean);
	
	
	@POST
	@Path("/selectMaterialAssign")
	public ScmInvMaterialAssignWSBean selectMaterialAssign(ScmInvMaterialAssignWSBean bean);
	
	@POST
	@Path("/checkItemAssign")
	public ScmInvMaterialAssignWSBean checkItemAssign(ScmInvMaterialAssignWSBean bean);
}

