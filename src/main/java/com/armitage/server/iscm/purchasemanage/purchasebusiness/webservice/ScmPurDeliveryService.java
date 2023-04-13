package com.armitage.server.iscm.purchasemanage.purchasebusiness.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurDeliveryWSBean;

public interface ScmPurDeliveryService {
	
	@POST
	@Path("/findPage")
	public ScmPurDeliveryWSBean findPage(ScmPurDeliveryWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmPurDeliveryWSBean queryPage(ScmPurDeliveryWSBean bean);

	@POST
	@Path("/select")
	public ScmPurDeliveryWSBean select(ScmPurDeliveryWSBean bean);

	@POST
	@Path("/add")
	public ScmPurDeliveryWSBean add(ScmPurDeliveryWSBean bean);

	@POST
	@Path("/update")
	public ScmPurDeliveryWSBean update(ScmPurDeliveryWSBean bean);
	

	@POST
	@Path("/delete")
	public ScmPurDeliveryWSBean delete(ScmPurDeliveryWSBean bean);
}

