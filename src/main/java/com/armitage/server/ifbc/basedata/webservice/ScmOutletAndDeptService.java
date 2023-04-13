package com.armitage.server.ifbc.basedata.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.ifbc.basedata.model.ScmOutletAndDeptWSBean;

public interface ScmOutletAndDeptService {
	@POST
	@Path("/findPage")
	public ScmOutletAndDeptWSBean findPage(ScmOutletAndDeptWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmOutletAndDeptWSBean queryPage(ScmOutletAndDeptWSBean bean);

	@POST
	@Path("/select")
	public ScmOutletAndDeptWSBean select(ScmOutletAndDeptWSBean bean);

	@POST
	@Path("/add")
	public ScmOutletAndDeptWSBean add(ScmOutletAndDeptWSBean bean);

	@POST
	@Path("/update")
	public ScmOutletAndDeptWSBean update(ScmOutletAndDeptWSBean bean);
	

	@POST
	@Path("/delete")
	public ScmOutletAndDeptWSBean delete(ScmOutletAndDeptWSBean bean);

}
