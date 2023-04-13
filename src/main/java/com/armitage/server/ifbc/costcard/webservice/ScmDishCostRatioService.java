package com.armitage.server.ifbc.costcard.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.ifbc.costcard.model.ScmDishCostRatioWSBean;

public interface ScmDishCostRatioService {
	
	@POST
	@Path("/findPage")
	public ScmDishCostRatioWSBean findPage(ScmDishCostRatioWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmDishCostRatioWSBean queryPage(ScmDishCostRatioWSBean bean);

	@POST
	@Path("/select")
	public ScmDishCostRatioWSBean select(ScmDishCostRatioWSBean bean);

	@POST
	@Path("/add")
	public ScmDishCostRatioWSBean add(ScmDishCostRatioWSBean bean);

	@POST
	@Path("/update")
	public ScmDishCostRatioWSBean update(ScmDishCostRatioWSBean bean);
	

	@POST
	@Path("/delete")
	public ScmDishCostRatioWSBean delete(ScmDishCostRatioWSBean bean);
	
	@POST
	@Path("/selectCostRatio")
	public ScmDishCostRatioWSBean selectCostRatio(ScmDishCostRatioWSBean bean);
	
	@POST
	@Path("/saveCostRatio")
	public ScmDishCostRatioWSBean saveCostRatio(ScmDishCostRatioWSBean bean);
	
}

