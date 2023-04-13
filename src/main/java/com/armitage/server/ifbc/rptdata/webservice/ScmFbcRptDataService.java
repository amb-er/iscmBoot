package com.armitage.server.ifbc.rptdata.webservice;


import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.ifbc.rptdata.model.ScmFbcRptDataWSBean;

public interface ScmFbcRptDataService {
	
	@POST
	@Path("/findPage")
	public ScmFbcRptDataWSBean findPage(ScmFbcRptDataWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmFbcRptDataWSBean queryPage(ScmFbcRptDataWSBean bean);

	@POST
	@Path("/select")
	public ScmFbcRptDataWSBean select(ScmFbcRptDataWSBean bean);

	@POST
	@Path("/add")
	public ScmFbcRptDataWSBean add(ScmFbcRptDataWSBean bean);

	@POST
	@Path("/update")
	public ScmFbcRptDataWSBean update(ScmFbcRptDataWSBean bean);
	
	@POST
	@Path("/delete")
	public ScmFbcRptDataWSBean delete(ScmFbcRptDataWSBean bean);
	
	@POST
	@Path("/calcRptData")
	public ScmFbcRptDataWSBean calcRptData(ScmFbcRptDataWSBean bean);
}
