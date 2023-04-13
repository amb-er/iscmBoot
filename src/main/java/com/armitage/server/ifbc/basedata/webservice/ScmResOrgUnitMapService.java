package com.armitage.server.ifbc.basedata.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.ifbc.basedata.model.ScmResOrgUnitMapWSBean;


public interface ScmResOrgUnitMapService {
	
	@POST
	@Path("/findPage")
	public ScmResOrgUnitMapWSBean findPage(ScmResOrgUnitMapWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmResOrgUnitMapWSBean queryPage(ScmResOrgUnitMapWSBean bean);

	@POST
	@Path("/select")
	public ScmResOrgUnitMapWSBean select(ScmResOrgUnitMapWSBean bean);

	@POST
	@Path("/add")
	public ScmResOrgUnitMapWSBean add(ScmResOrgUnitMapWSBean bean);

	@POST
	@Path("/update")
	public ScmResOrgUnitMapWSBean update(ScmResOrgUnitMapWSBean bean);
	

	@POST
	@Path("/delete")
	public ScmResOrgUnitMapWSBean delete(ScmResOrgUnitMapWSBean bean);
	
}
