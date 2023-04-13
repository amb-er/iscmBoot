package com.armitage.server.iscm.inventorymanage.countbusiness.webservice;


import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.iscm.inventorymanage.countbusiness.model.ScmInvCountingListWarehouseWSBean;

public interface ScmInvCountingListWarehouseService {
	
	@POST
	@Path("/findPage")
	public ScmInvCountingListWarehouseWSBean findPage(ScmInvCountingListWarehouseWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmInvCountingListWarehouseWSBean queryPage(ScmInvCountingListWarehouseWSBean bean);

	@POST
	@Path("/select")
	public ScmInvCountingListWarehouseWSBean select(ScmInvCountingListWarehouseWSBean bean);

	@POST
	@Path("/add")
	public ScmInvCountingListWarehouseWSBean add(ScmInvCountingListWarehouseWSBean bean);

	@POST
	@Path("/update")
	public ScmInvCountingListWarehouseWSBean update(ScmInvCountingListWarehouseWSBean bean);
	

	@POST
	@Path("/delete")
	public ScmInvCountingListWarehouseWSBean delete(ScmInvCountingListWarehouseWSBean bean);
	
}

