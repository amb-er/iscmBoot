package com.armitage.server.iscm.inventorymanage.inventorydata.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.iscm.inventorymanage.inventorydata.model.ScmInvStockWSBean;

public interface ScmInvStockService {
	
	@POST
	@Path("/findPage")
	public ScmInvStockWSBean findPage(ScmInvStockWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmInvStockWSBean queryPage(ScmInvStockWSBean bean);

	@POST
	@Path("/select")
	public ScmInvStockWSBean select(ScmInvStockWSBean bean);

	@POST
	@Path("/add")
	public ScmInvStockWSBean add(ScmInvStockWSBean bean);

	@POST
	@Path("/update")
	public ScmInvStockWSBean update(ScmInvStockWSBean bean);
	

	@POST
	@Path("/delete")
	public ScmInvStockWSBean delete(ScmInvStockWSBean bean);
}
