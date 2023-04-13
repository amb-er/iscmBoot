package com.armitage.server.iscm.basedata.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.iscm.basedata.model.ScmPurchaseTypeWSBean;

public interface ScmPurchaseTypeService {
	
	@POST
	@Path("/findPage")
	public ScmPurchaseTypeWSBean findPage(ScmPurchaseTypeWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmPurchaseTypeWSBean queryPage(ScmPurchaseTypeWSBean bean);

	@POST
	@Path("/select")
	public ScmPurchaseTypeWSBean select(ScmPurchaseTypeWSBean bean);

	@POST
	@Path("/add")
	public ScmPurchaseTypeWSBean add(ScmPurchaseTypeWSBean bean);

	@POST
	@Path("/update")
	public ScmPurchaseTypeWSBean update(ScmPurchaseTypeWSBean bean);
	

	@POST
	@Path("/delete")
	public ScmPurchaseTypeWSBean delete(ScmPurchaseTypeWSBean bean);
}
