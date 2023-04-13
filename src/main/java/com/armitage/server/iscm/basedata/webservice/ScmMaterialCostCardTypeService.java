package com.armitage.server.iscm.basedata.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.iscm.basedata.model.ScmMaterialCostCardTypeWSBean;

public interface ScmMaterialCostCardTypeService {
	
	@POST
	@Path("/findPage")
	public ScmMaterialCostCardTypeWSBean findPage(ScmMaterialCostCardTypeWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmMaterialCostCardTypeWSBean queryPage(ScmMaterialCostCardTypeWSBean bean);

	@POST
	@Path("/select")
	public ScmMaterialCostCardTypeWSBean select(ScmMaterialCostCardTypeWSBean bean);

	@POST
	@Path("/add")
	public ScmMaterialCostCardTypeWSBean add(ScmMaterialCostCardTypeWSBean bean);

	@POST
	@Path("/update")
	public ScmMaterialCostCardTypeWSBean update(ScmMaterialCostCardTypeWSBean bean);
	

	@POST
	@Path("/delete")
	public ScmMaterialCostCardTypeWSBean delete(ScmMaterialCostCardTypeWSBean bean);
	
	@POST
	@Path("/selectForCostCard")
	public ScmMaterialCostCardTypeWSBean selectForCostCard(ScmMaterialCostCardTypeWSBean bean);
}

