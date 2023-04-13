package com.armitage.server.ifbc.costcard.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.ifbc.costcard.model.ScmStandardRatioWSBean;

public interface ScmStandardRatioService {
	
	@POST
	@Path("/findPage")
	public ScmStandardRatioWSBean findPage(ScmStandardRatioWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmStandardRatioWSBean queryPage(ScmStandardRatioWSBean bean);

	@POST
	@Path("/select")
	public ScmStandardRatioWSBean select(ScmStandardRatioWSBean bean);

	@POST
	@Path("/add")
	public ScmStandardRatioWSBean add(ScmStandardRatioWSBean bean);

	@POST
	@Path("/update")
	public ScmStandardRatioWSBean update(ScmStandardRatioWSBean bean);
	

	@POST
	@Path("/delete")
	public ScmStandardRatioWSBean delete(ScmStandardRatioWSBean bean);
	
}
