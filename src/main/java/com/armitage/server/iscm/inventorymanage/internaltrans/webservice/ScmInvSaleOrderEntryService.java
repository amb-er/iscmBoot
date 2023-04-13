package com.armitage.server.iscm.inventorymanage.internaltrans.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import com.armitage.server.iscm.inventorymanage.internaltrans.model.ScmInvSaleOrderEntryWSBean;

public interface ScmInvSaleOrderEntryService {
	
	@POST
	@Path("/findPage")
	public ScmInvSaleOrderEntryWSBean findPage(ScmInvSaleOrderEntryWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmInvSaleOrderEntryWSBean queryPage(ScmInvSaleOrderEntryWSBean bean);

	@POST
	@Path("/select")
	public ScmInvSaleOrderEntryWSBean select(ScmInvSaleOrderEntryWSBean bean);

	@POST
	@Path("/add")
	public ScmInvSaleOrderEntryWSBean add(ScmInvSaleOrderEntryWSBean bean);

	@POST
	@Path("/update")
	public ScmInvSaleOrderEntryWSBean update(ScmInvSaleOrderEntryWSBean bean);
	

	@POST
	@Path("/delete")
	public ScmInvSaleOrderEntryWSBean delete(ScmInvSaleOrderEntryWSBean bean);
	
}
