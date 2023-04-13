package com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvMaterialRequestBillEntryWSBean;

public interface ScmInvMaterialRequestBillEntryService {
	
	@POST
	@Path("/findPage")
	public ScmInvMaterialRequestBillEntryWSBean findPage(ScmInvMaterialRequestBillEntryWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmInvMaterialRequestBillEntryWSBean queryPage(ScmInvMaterialRequestBillEntryWSBean bean);

	@POST
	@Path("/select")
	public ScmInvMaterialRequestBillEntryWSBean select(ScmInvMaterialRequestBillEntryWSBean bean);

	@POST
	@Path("/add")
	public ScmInvMaterialRequestBillEntryWSBean add(ScmInvMaterialRequestBillEntryWSBean bean);

	@POST
	@Path("/update")
	public ScmInvMaterialRequestBillEntryWSBean update(ScmInvMaterialRequestBillEntryWSBean bean);
	

	@POST
	@Path("/delete")
	public ScmInvMaterialRequestBillEntryWSBean delete(ScmInvMaterialRequestBillEntryWSBean bean);
	
}

