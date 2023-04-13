
package com.armitage.server.iscm.inventorymanage.inventorysetting.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvSupplyPlanWSBean;

public interface ScmInvSupplyPlanService {
	
	@POST
	@Path("/findPage")
	public ScmInvSupplyPlanWSBean findPage(ScmInvSupplyPlanWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmInvSupplyPlanWSBean queryPage(ScmInvSupplyPlanWSBean bean);

	@POST
	@Path("/select")
	public ScmInvSupplyPlanWSBean select(ScmInvSupplyPlanWSBean bean);

	@POST
	@Path("/add")
	public ScmInvSupplyPlanWSBean add(ScmInvSupplyPlanWSBean bean);

	@POST
	@Path("/update")
	public ScmInvSupplyPlanWSBean update(ScmInvSupplyPlanWSBean bean);
	

	@POST
	@Path("/delete")
	public ScmInvSupplyPlanWSBean delete(ScmInvSupplyPlanWSBean bean);
	
}
