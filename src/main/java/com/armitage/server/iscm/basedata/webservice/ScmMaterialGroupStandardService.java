package com.armitage.server.iscm.basedata.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.iscm.basedata.model.ScmMaterialGroupStandardWSBean;

public interface ScmMaterialGroupStandardService {
	
	@POST
	@Path("/findPage")
	public ScmMaterialGroupStandardWSBean findPage(ScmMaterialGroupStandardWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmMaterialGroupStandardWSBean queryPage(ScmMaterialGroupStandardWSBean bean);

	@POST
	@Path("/select")
	public ScmMaterialGroupStandardWSBean select(ScmMaterialGroupStandardWSBean bean);

	@POST
	@Path("/add")
	public ScmMaterialGroupStandardWSBean add(ScmMaterialGroupStandardWSBean bean);

	@POST
	@Path("/update")
	public ScmMaterialGroupStandardWSBean update(ScmMaterialGroupStandardWSBean bean);
	

	@POST
	@Path("/delete")
	public ScmMaterialGroupStandardWSBean delete(ScmMaterialGroupStandardWSBean bean);
	
	@POST
	@Path("/selectSubsidiaryTypeByItem")
	public ScmMaterialGroupStandardWSBean selectSubsidiaryTypeByItem(ScmMaterialGroupStandardWSBean bean);
}
