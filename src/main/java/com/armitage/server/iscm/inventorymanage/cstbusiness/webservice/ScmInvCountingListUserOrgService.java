package com.armitage.server.iscm.inventorymanage.cstbusiness.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCountingListUserOrgWSBean;

public interface ScmInvCountingListUserOrgService {
	
	@POST
	@Path("/findPage")
	public ScmInvCountingListUserOrgWSBean findPage(ScmInvCountingListUserOrgWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmInvCountingListUserOrgWSBean queryPage(ScmInvCountingListUserOrgWSBean bean);

	@POST
	@Path("/select")
	public ScmInvCountingListUserOrgWSBean select(ScmInvCountingListUserOrgWSBean bean);

	@POST
	@Path("/add")
	public ScmInvCountingListUserOrgWSBean add(ScmInvCountingListUserOrgWSBean bean);

	@POST
	@Path("/update")
	public ScmInvCountingListUserOrgWSBean update(ScmInvCountingListUserOrgWSBean bean);
	

	@POST
	@Path("/delete")
	public ScmInvCountingListUserOrgWSBean delete(ScmInvCountingListUserOrgWSBean bean);
	
}
