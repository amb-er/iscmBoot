
package com.armitage.server.iscm.inventorymanage.inventorysetting.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvSupplyRuleEntryWSBean;

public interface ScmInvSupplyRuleEntryService {
	
	@POST
	@Path("/findPage")
	public ScmInvSupplyRuleEntryWSBean findPage(ScmInvSupplyRuleEntryWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmInvSupplyRuleEntryWSBean queryPage(ScmInvSupplyRuleEntryWSBean bean);

	@POST
	@Path("/select")
	public ScmInvSupplyRuleEntryWSBean select(ScmInvSupplyRuleEntryWSBean bean);

	@POST
	@Path("/add")
	public ScmInvSupplyRuleEntryWSBean add(ScmInvSupplyRuleEntryWSBean bean);

	@POST
	@Path("/update")
	public ScmInvSupplyRuleEntryWSBean update(ScmInvSupplyRuleEntryWSBean bean);
	

	@POST
	@Path("/delete")
	public ScmInvSupplyRuleEntryWSBean delete(ScmInvSupplyRuleEntryWSBean bean);
	
}
