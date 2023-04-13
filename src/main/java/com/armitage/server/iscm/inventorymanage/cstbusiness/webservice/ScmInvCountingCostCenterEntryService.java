package com.armitage.server.iscm.inventorymanage.cstbusiness.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCountingCostCenterEntryWSBean;

public interface ScmInvCountingCostCenterEntryService {
	
	@POST
	@Path("/findPage")
	public ScmInvCountingCostCenterEntryWSBean findPage(ScmInvCountingCostCenterEntryWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmInvCountingCostCenterEntryWSBean queryPage(ScmInvCountingCostCenterEntryWSBean bean);

	@POST
	@Path("/select")
	public ScmInvCountingCostCenterEntryWSBean select(ScmInvCountingCostCenterEntryWSBean bean);

	@POST
	@Path("/add")
	public ScmInvCountingCostCenterEntryWSBean add(ScmInvCountingCostCenterEntryWSBean bean);

	@POST
	@Path("/update")
	public ScmInvCountingCostCenterEntryWSBean update(ScmInvCountingCostCenterEntryWSBean bean);
	

	@POST
	@Path("/delete")
	public ScmInvCountingCostCenterEntryWSBean delete(ScmInvCountingCostCenterEntryWSBean bean);
	
}
