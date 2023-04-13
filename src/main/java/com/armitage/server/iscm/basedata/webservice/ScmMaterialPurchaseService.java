package com.armitage.server.iscm.basedata.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.iscm.basedata.model.ScmMaterialPurchaseWSBean;

public interface ScmMaterialPurchaseService {
	
	@POST
	@Path("/findPage")
	public ScmMaterialPurchaseWSBean findPage(ScmMaterialPurchaseWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmMaterialPurchaseWSBean queryPage(ScmMaterialPurchaseWSBean bean);

	@POST
	@Path("/select")
	public ScmMaterialPurchaseWSBean select(ScmMaterialPurchaseWSBean bean);

	@POST
	@Path("/add")
	public ScmMaterialPurchaseWSBean add(ScmMaterialPurchaseWSBean bean);

	@POST
	@Path("/update")
	public ScmMaterialPurchaseWSBean update(ScmMaterialPurchaseWSBean bean);
	

	@POST
	@Path("/delete")
	public ScmMaterialPurchaseWSBean delete(ScmMaterialPurchaseWSBean bean);
	
}
