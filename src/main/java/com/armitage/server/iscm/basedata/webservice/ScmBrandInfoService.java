package com.armitage.server.iscm.basedata.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.iscm.basedata.model.ScmBrandInfoWSBean;

public interface ScmBrandInfoService {
	
	@POST
	@Path("/findPage")
	public ScmBrandInfoWSBean findPage(ScmBrandInfoWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmBrandInfoWSBean queryPage(ScmBrandInfoWSBean bean);

	@POST
	@Path("/select")
	public ScmBrandInfoWSBean select(ScmBrandInfoWSBean bean);

	@POST
	@Path("/add")
	public ScmBrandInfoWSBean add(ScmBrandInfoWSBean bean);

	@POST
	@Path("/update")
	public ScmBrandInfoWSBean update(ScmBrandInfoWSBean bean);
	

	@POST
	@Path("/delete")
	public ScmBrandInfoWSBean delete(ScmBrandInfoWSBean bean);
	
}
