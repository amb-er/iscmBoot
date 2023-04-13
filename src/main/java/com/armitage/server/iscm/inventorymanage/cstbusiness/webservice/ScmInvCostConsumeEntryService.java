package com.armitage.server.iscm.inventorymanage.cstbusiness.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCostConsumeEntryWSBean;

public interface ScmInvCostConsumeEntryService {
	
	@POST
	@Path("/findPage")
	public ScmInvCostConsumeEntryWSBean findPage(ScmInvCostConsumeEntryWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmInvCostConsumeEntryWSBean queryPage(ScmInvCostConsumeEntryWSBean bean);

	@POST
	@Path("/select")
	public ScmInvCostConsumeEntryWSBean select(ScmInvCostConsumeEntryWSBean bean);

	@POST
	@Path("/add")
	public ScmInvCostConsumeEntryWSBean add(ScmInvCostConsumeEntryWSBean bean);

	@POST
	@Path("/update")
	public ScmInvCostConsumeEntryWSBean update(ScmInvCostConsumeEntryWSBean bean);
	

	@POST
	@Path("/delete")
	public ScmInvCostConsumeEntryWSBean delete(ScmInvCostConsumeEntryWSBean bean);
	
}
