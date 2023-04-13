
package com.armitage.server.iscm.inventorymanage.inventorysetting.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvSupplyRuleWSBean;

public interface ScmInvSupplyRuleService {
	
	@POST
	@Path("/findPage")
	public ScmInvSupplyRuleWSBean findPage(ScmInvSupplyRuleWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmInvSupplyRuleWSBean queryPage(ScmInvSupplyRuleWSBean bean);

	@POST
	@Path("/select")
	public ScmInvSupplyRuleWSBean select(ScmInvSupplyRuleWSBean bean);

	@POST
	@Path("/add")
	public ScmInvSupplyRuleWSBean add(ScmInvSupplyRuleWSBean bean);

	@POST
	@Path("/update")
	public ScmInvSupplyRuleWSBean update(ScmInvSupplyRuleWSBean bean);
	

	@POST
	@Path("/delete")
	public ScmInvSupplyRuleWSBean delete(ScmInvSupplyRuleWSBean bean);

	@POST
	@Path("/generatePlan")
	public ScmInvSupplyRuleWSBean generatePlan(ScmInvSupplyRuleWSBean bean);
	
}
