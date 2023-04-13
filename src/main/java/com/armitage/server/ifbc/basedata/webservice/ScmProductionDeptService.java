package com.armitage.server.ifbc.basedata.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.ifbc.basedata.model.ScmProductionDeptWSBean;


public interface ScmProductionDeptService {
	
	@POST
	@Path("/findPage")
	public ScmProductionDeptWSBean findPage(ScmProductionDeptWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmProductionDeptWSBean queryPage(ScmProductionDeptWSBean bean);

	@POST
	@Path("/select")
	public ScmProductionDeptWSBean select(ScmProductionDeptWSBean bean);

	@POST
	@Path("/add")
	public ScmProductionDeptWSBean add(ScmProductionDeptWSBean bean);

	@POST
	@Path("/update")
	public ScmProductionDeptWSBean update(ScmProductionDeptWSBean bean);
	

	@POST
	@Path("/delete")
	public ScmProductionDeptWSBean delete(ScmProductionDeptWSBean bean);
	
}
