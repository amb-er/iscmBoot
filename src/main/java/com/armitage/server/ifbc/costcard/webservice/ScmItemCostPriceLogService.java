package com.armitage.server.ifbc.costcard.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.ifbc.costcard.model.ScmItemCostPriceLogWSBean;

public interface ScmItemCostPriceLogService {
	
	@POST
	@Path("/findPage")
	public ScmItemCostPriceLogWSBean findPage(ScmItemCostPriceLogWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmItemCostPriceLogWSBean queryPage(ScmItemCostPriceLogWSBean bean);

	@POST
	@Path("/select")
	public ScmItemCostPriceLogWSBean select(ScmItemCostPriceLogWSBean bean);

	@POST
	@Path("/add")
	public ScmItemCostPriceLogWSBean add(ScmItemCostPriceLogWSBean bean);

	@POST
	@Path("/update")
	public ScmItemCostPriceLogWSBean update(ScmItemCostPriceLogWSBean bean);
	
	@POST
	@Path("/delete")
	public ScmItemCostPriceLogWSBean delete(ScmItemCostPriceLogWSBean bean);
	
	@POST
	@Path("/getItemPrice")
	public ScmItemCostPriceLogWSBean getItemPrice(ScmItemCostPriceLogWSBean bean);
}
