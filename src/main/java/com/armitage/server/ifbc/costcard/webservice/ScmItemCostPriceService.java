package com.armitage.server.ifbc.costcard.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.ifbc.costcard.model.ScmItemCostPriceWSBean;

public interface ScmItemCostPriceService {
	
	@POST
	@Path("/findPage")
	public ScmItemCostPriceWSBean findPage(ScmItemCostPriceWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmItemCostPriceWSBean queryPage(ScmItemCostPriceWSBean bean);

	@POST
	@Path("/select")
	public ScmItemCostPriceWSBean select(ScmItemCostPriceWSBean bean);

	@POST
	@Path("/add")
	public ScmItemCostPriceWSBean add(ScmItemCostPriceWSBean bean);

	@POST
	@Path("/update")
	public ScmItemCostPriceWSBean update(ScmItemCostPriceWSBean bean);
	
	@POST
	@Path("/delete")
	public ScmItemCostPriceWSBean delete(ScmItemCostPriceWSBean bean);
}
