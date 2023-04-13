package com.armitage.server.ifbc.costcard.webservice;


import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.ifbc.costcard.model.ScmDishReplaceCostCardWSBean;

public interface ScmDishReplaceCostCardService {
	
	@POST
	@Path("/findPage")
	public ScmDishReplaceCostCardWSBean findPage(ScmDishReplaceCostCardWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmDishReplaceCostCardWSBean queryPage(ScmDishReplaceCostCardWSBean bean);

	@POST
	@Path("/select")
	public ScmDishReplaceCostCardWSBean select(ScmDishReplaceCostCardWSBean bean);

	@POST
	@Path("/add")
	public ScmDishReplaceCostCardWSBean add(ScmDishReplaceCostCardWSBean bean);

	@POST
	@Path("/update")
	public ScmDishReplaceCostCardWSBean update(ScmDishReplaceCostCardWSBean bean);

	@POST
	@Path("/delete")
	public ScmDishReplaceCostCardWSBean delete(ScmDishReplaceCostCardWSBean bean);

	@POST
	@Path("/getDataForReplace")
	public ScmDishReplaceCostCardWSBean getDataForReplace(ScmDishReplaceCostCardWSBean bean);
	
	@POST
	@Path("/replaceItem")
	public ScmDishReplaceCostCardWSBean replaceItem(ScmDishReplaceCostCardWSBean bean);
}
