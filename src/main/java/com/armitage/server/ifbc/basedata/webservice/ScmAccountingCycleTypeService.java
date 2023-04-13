package com.armitage.server.ifbc.basedata.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.ifbc.basedata.model.ScmAccountingCycleTypeWSBean;


public interface ScmAccountingCycleTypeService {
	
	@POST
	@Path("/findPage")
	public ScmAccountingCycleTypeWSBean findPage(ScmAccountingCycleTypeWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmAccountingCycleTypeWSBean queryPage(ScmAccountingCycleTypeWSBean bean);

	@POST
	@Path("/select")
	public ScmAccountingCycleTypeWSBean select(ScmAccountingCycleTypeWSBean bean);

	@POST
	@Path("/add")
	public ScmAccountingCycleTypeWSBean add(ScmAccountingCycleTypeWSBean bean);

	@POST
	@Path("/update")
	public ScmAccountingCycleTypeWSBean update(ScmAccountingCycleTypeWSBean bean);
	

	@POST
	@Path("/delete")
	public ScmAccountingCycleTypeWSBean delete(ScmAccountingCycleTypeWSBean bean);
	
}
