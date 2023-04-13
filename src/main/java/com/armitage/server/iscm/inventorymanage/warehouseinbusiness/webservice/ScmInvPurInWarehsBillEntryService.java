package com.armitage.server.iscm.inventorymanage.warehouseinbusiness.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvPurInWarehsBillEntryWSBean;

public interface ScmInvPurInWarehsBillEntryService {
	
	@POST
	@Path("/findPage")
	public ScmInvPurInWarehsBillEntryWSBean findPage(ScmInvPurInWarehsBillEntryWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmInvPurInWarehsBillEntryWSBean queryPage(ScmInvPurInWarehsBillEntryWSBean bean);

	@POST
	@Path("/select")
	public ScmInvPurInWarehsBillEntryWSBean select(ScmInvPurInWarehsBillEntryWSBean bean);

	@POST
	@Path("/add")
	public ScmInvPurInWarehsBillEntryWSBean add(ScmInvPurInWarehsBillEntryWSBean bean);

	@POST
	@Path("/update")
	public ScmInvPurInWarehsBillEntryWSBean update(ScmInvPurInWarehsBillEntryWSBean bean);
	

	@POST
	@Path("/delete")
	public ScmInvPurInWarehsBillEntryWSBean delete(ScmInvPurInWarehsBillEntryWSBean bean);
	
	@POST
	@Path("/getDataForLeadInto")
	public ScmInvPurInWarehsBillEntryWSBean getDataForLeadInto(ScmInvPurInWarehsBillEntryWSBean bean);
}

