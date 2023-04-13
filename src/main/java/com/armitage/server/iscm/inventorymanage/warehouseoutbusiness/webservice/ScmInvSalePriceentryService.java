package com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvSalePriceentryWSBean;

@Path("/scminvsalepriceentry/")
public interface ScmInvSalePriceentryService {
	@POST
	@Path("/findPage")
	public ScmInvSalePriceentryWSBean findPage(ScmInvSalePriceentryWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmInvSalePriceentryWSBean queryPage(ScmInvSalePriceentryWSBean bean);

	@POST
	@Path("/select")
	public ScmInvSalePriceentryWSBean select(ScmInvSalePriceentryWSBean bean);
	
	@POST
	@Path("/add")
	public ScmInvSalePriceentryWSBean add(ScmInvSalePriceentryWSBean bean);
	
	@POST
	@Path("/update")
	public ScmInvSalePriceentryWSBean update(ScmInvSalePriceentryWSBean bean);
	
	@POST
	@Path("/delete")
	public ScmInvSalePriceentryWSBean delete(ScmInvSalePriceentryWSBean bean);

}
