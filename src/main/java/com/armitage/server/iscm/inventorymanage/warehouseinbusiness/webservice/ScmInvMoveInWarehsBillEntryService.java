package com.armitage.server.iscm.inventorymanage.warehouseinbusiness.webservice;


import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvMoveInWarehsBillEntryWSBean;

public interface ScmInvMoveInWarehsBillEntryService {
	
	@POST
	@Path("/findPage")
	public ScmInvMoveInWarehsBillEntryWSBean findPage(ScmInvMoveInWarehsBillEntryWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmInvMoveInWarehsBillEntryWSBean queryPage(ScmInvMoveInWarehsBillEntryWSBean bean);

	@POST
	@Path("/select")
	public ScmInvMoveInWarehsBillEntryWSBean select(ScmInvMoveInWarehsBillEntryWSBean bean);

	@POST
	@Path("/add")
	public ScmInvMoveInWarehsBillEntryWSBean add(ScmInvMoveInWarehsBillEntryWSBean bean);

	@POST
	@Path("/update")
	public ScmInvMoveInWarehsBillEntryWSBean update(ScmInvMoveInWarehsBillEntryWSBean bean);
	

	@POST
	@Path("/delete")
	public ScmInvMoveInWarehsBillEntryWSBean delete(ScmInvMoveInWarehsBillEntryWSBean bean);
	
}

