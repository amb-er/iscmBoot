package com.armitage.server.ifbc.basedata.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.ifbc.basedata.model.ScmAccountingCycleWSBean;


public interface ScmAccountingCycleService {
	
	@POST
	@Path("/findPage")
	public ScmAccountingCycleWSBean findPage(ScmAccountingCycleWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmAccountingCycleWSBean queryPage(ScmAccountingCycleWSBean bean);

	@POST
	@Path("/select")
	public ScmAccountingCycleWSBean select(ScmAccountingCycleWSBean bean);

	@POST
	@Path("/add")
	public ScmAccountingCycleWSBean add(ScmAccountingCycleWSBean bean);

	@POST
	@Path("/update")
	public ScmAccountingCycleWSBean update(ScmAccountingCycleWSBean bean);
	

	@POST
	@Path("/delete")
	public ScmAccountingCycleWSBean delete(ScmAccountingCycleWSBean bean);
	
}
