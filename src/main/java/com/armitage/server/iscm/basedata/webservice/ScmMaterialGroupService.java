package com.armitage.server.iscm.basedata.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.iscm.basedata.model.ScmMaterialGroupWSBean;

public interface ScmMaterialGroupService {
	
	@POST
	@Path("/findPage")
	public ScmMaterialGroupWSBean findPage(ScmMaterialGroupWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmMaterialGroupWSBean queryPage(ScmMaterialGroupWSBean bean);

	@POST
	@Path("/select")
	public ScmMaterialGroupWSBean select(ScmMaterialGroupWSBean bean);

	@POST
	@Path("/add")
	public ScmMaterialGroupWSBean add(ScmMaterialGroupWSBean bean);

	@POST
	@Path("/update")
	public ScmMaterialGroupWSBean update(ScmMaterialGroupWSBean bean);
	

	@POST
	@Path("/delete")
	public ScmMaterialGroupWSBean delete(ScmMaterialGroupWSBean bean);
	
	@POST
	@Path("/queryDetailClassList")
	public ScmMaterialGroupWSBean queryDetailClassList(ScmMaterialGroupWSBean bean);
	
	@POST
	@Path("/queryMaterialClassList")
	public ScmMaterialGroupWSBean queryMaterialClassList(ScmMaterialGroupWSBean bean);
	
	@POST
	@Path("/updateCostType")
	public ScmMaterialGroupWSBean updateCostType(ScmMaterialGroupWSBean bean);
}
