package com.armitage.server.iscm.basedata.webservice;


import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.iscm.basedata.model.ScmIndustryGroupWSBean;

public interface ScmIndustryGroupService {
	
	@POST
	@Path("/findPage")
	public ScmIndustryGroupWSBean findPage(ScmIndustryGroupWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmIndustryGroupWSBean queryPage(ScmIndustryGroupWSBean bean);

	@POST
	@Path("/select")
	public ScmIndustryGroupWSBean select(ScmIndustryGroupWSBean bean);

	@POST
	@Path("/add")
	public ScmIndustryGroupWSBean add(ScmIndustryGroupWSBean bean);

	@POST
	@Path("/update")
	public ScmIndustryGroupWSBean update(ScmIndustryGroupWSBean bean);
	

	@POST
	@Path("/delete")
	public ScmIndustryGroupWSBean delete(ScmIndustryGroupWSBean bean);
	
}

