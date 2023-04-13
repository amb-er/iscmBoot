package com.armitage.server.iscm.basedata.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.iscm.basedata.model.ScmCostCategoryWSBean;

public interface ScmCostCategoryService {
	
	@POST
	@Path("/findPage")
	public ScmCostCategoryWSBean findPage(ScmCostCategoryWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmCostCategoryWSBean queryPage(ScmCostCategoryWSBean bean);

	@POST
	@Path("/select")
	public ScmCostCategoryWSBean select(ScmCostCategoryWSBean bean);

	@POST
	@Path("/add")
	public ScmCostCategoryWSBean add(ScmCostCategoryWSBean bean);

	@POST
	@Path("/update")
	public ScmCostCategoryWSBean update(ScmCostCategoryWSBean bean);
	

	@POST
	@Path("/delete")
	public ScmCostCategoryWSBean delete(ScmCostCategoryWSBean bean);
	
}

