package com.armitage.server.iscm.inventorymanage.warehouseinbusiness.webservice;


import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvOtherInWarehsBillEntryWSBean;

public interface ScmInvOtherInWarehsBillEntryService {
	
	@POST
	@Path("/findPage")
	public ScmInvOtherInWarehsBillEntryWSBean findPage(ScmInvOtherInWarehsBillEntryWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmInvOtherInWarehsBillEntryWSBean queryPage(ScmInvOtherInWarehsBillEntryWSBean bean);

	@POST
	@Path("/select")
	public ScmInvOtherInWarehsBillEntryWSBean select(ScmInvOtherInWarehsBillEntryWSBean bean);

	@POST
	@Path("/add")
	public ScmInvOtherInWarehsBillEntryWSBean add(ScmInvOtherInWarehsBillEntryWSBean bean);

	@POST
	@Path("/update")
	public ScmInvOtherInWarehsBillEntryWSBean update(ScmInvOtherInWarehsBillEntryWSBean bean);
	

	@POST
	@Path("/delete")
	public ScmInvOtherInWarehsBillEntryWSBean delete(ScmInvOtherInWarehsBillEntryWSBean bean);
	
}

