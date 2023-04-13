package com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.webservice;


import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvMaterialReqBillEntryWSBean;

public interface ScmInvMaterialReqBillEntryService {
	
	@POST
	@Path("/findPage")
	public ScmInvMaterialReqBillEntryWSBean findPage(ScmInvMaterialReqBillEntryWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmInvMaterialReqBillEntryWSBean queryPage(ScmInvMaterialReqBillEntryWSBean bean);

	@POST
	@Path("/select")
	public ScmInvMaterialReqBillEntryWSBean select(ScmInvMaterialReqBillEntryWSBean bean);

	@POST
	@Path("/add")
	public ScmInvMaterialReqBillEntryWSBean add(ScmInvMaterialReqBillEntryWSBean bean);

	@POST
	@Path("/update")
	public ScmInvMaterialReqBillEntryWSBean update(ScmInvMaterialReqBillEntryWSBean bean);
	

	@POST
	@Path("/delete")
	public ScmInvMaterialReqBillEntryWSBean delete(ScmInvMaterialReqBillEntryWSBean bean);
	
}

