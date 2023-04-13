
package com.armitage.server.iscm.inventorymanage.countbusiness.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.iscm.inventorymanage.countbusiness.model.ScmInvCountingListMaterialGroupWSBean;

public interface ScmInvCountingListMaterialGroupService {
	
	@POST
	@Path("/findPage")
	public ScmInvCountingListMaterialGroupWSBean findPage(ScmInvCountingListMaterialGroupWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmInvCountingListMaterialGroupWSBean queryPage(ScmInvCountingListMaterialGroupWSBean bean);

	@POST
	@Path("/select")
	public ScmInvCountingListMaterialGroupWSBean select(ScmInvCountingListMaterialGroupWSBean bean);

	@POST
	@Path("/add")
	public ScmInvCountingListMaterialGroupWSBean add(ScmInvCountingListMaterialGroupWSBean bean);

	@POST
	@Path("/update")
	public ScmInvCountingListMaterialGroupWSBean update(ScmInvCountingListMaterialGroupWSBean bean);

	@POST
	@Path("/delete")
	public ScmInvCountingListMaterialGroupWSBean delete(ScmInvCountingListMaterialGroupWSBean bean);
	
}
