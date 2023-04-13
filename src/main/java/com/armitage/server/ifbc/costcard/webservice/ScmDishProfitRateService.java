package com.armitage.server.ifbc.costcard.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.ifbc.costcard.model.ScmDishProfitRateWSBean;

public interface ScmDishProfitRateService {
	
	@POST
	@Path("/findPage")
	public ScmDishProfitRateWSBean findPage(ScmDishProfitRateWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmDishProfitRateWSBean queryPage(ScmDishProfitRateWSBean bean);

	@POST
	@Path("/select")
	public ScmDishProfitRateWSBean select(ScmDishProfitRateWSBean bean);

	@POST
	@Path("/add")
	public ScmDishProfitRateWSBean add(ScmDishProfitRateWSBean bean);

	@POST
	@Path("/update")
	public ScmDishProfitRateWSBean update(ScmDishProfitRateWSBean bean);
	

	@POST
	@Path("/delete")
	public ScmDishProfitRateWSBean delete(ScmDishProfitRateWSBean bean);
	
	@POST
	@Path("/selectProfitRate")
	public ScmDishProfitRateWSBean selectProfitRate(ScmDishProfitRateWSBean bean);
	
}
