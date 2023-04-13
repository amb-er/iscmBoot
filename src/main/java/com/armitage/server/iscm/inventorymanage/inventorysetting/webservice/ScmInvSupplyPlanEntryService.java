
package com.armitage.server.iscm.inventorymanage.inventorysetting.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvSupplyPlanEntryWSBean;

public interface ScmInvSupplyPlanEntryService {
	
	@POST
	@Path("/findPage")
	public ScmInvSupplyPlanEntryWSBean findPage(ScmInvSupplyPlanEntryWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmInvSupplyPlanEntryWSBean queryPage(ScmInvSupplyPlanEntryWSBean bean);

	@POST
	@Path("/select")
	public ScmInvSupplyPlanEntryWSBean select(ScmInvSupplyPlanEntryWSBean bean);

	@POST
	@Path("/add")
	public ScmInvSupplyPlanEntryWSBean add(ScmInvSupplyPlanEntryWSBean bean);

	@POST
	@Path("/update")
	public ScmInvSupplyPlanEntryWSBean update(ScmInvSupplyPlanEntryWSBean bean);
	

	@POST
	@Path("/delete")
	public ScmInvSupplyPlanEntryWSBean delete(ScmInvSupplyPlanEntryWSBean bean);
	
}
