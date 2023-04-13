package com.armitage.server.iscm.inventorymanage.countbusiness.webservice;


import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.iscm.inventorymanage.countbusiness.model.ScmInvCountingListMaterialWSBean;

public interface ScmInvCountingListMaterialService {
	
	@POST
	@Path("/findPage")
	public ScmInvCountingListMaterialWSBean findPage(ScmInvCountingListMaterialWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmInvCountingListMaterialWSBean queryPage(ScmInvCountingListMaterialWSBean bean);

	@POST
	@Path("/select")
	public ScmInvCountingListMaterialWSBean select(ScmInvCountingListMaterialWSBean bean);

	@POST
	@Path("/add")
	public ScmInvCountingListMaterialWSBean add(ScmInvCountingListMaterialWSBean bean);

	@POST
	@Path("/update")
	public ScmInvCountingListMaterialWSBean update(ScmInvCountingListMaterialWSBean bean);
	

	@POST
	@Path("/delete")
	public ScmInvCountingListMaterialWSBean delete(ScmInvCountingListMaterialWSBean bean);
	
}

