package com.armitage.server.iscm.purchasemanage.pricemanage.webservice;


import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurPricePrepareEntryWSBean;

public interface ScmPurPricePrepareEntryService {
	
	@POST
	@Path("/findPage")
	public ScmPurPricePrepareEntryWSBean findPage(ScmPurPricePrepareEntryWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmPurPricePrepareEntryWSBean queryPage(ScmPurPricePrepareEntryWSBean bean);

	@POST
	@Path("/select")
	public ScmPurPricePrepareEntryWSBean select(ScmPurPricePrepareEntryWSBean bean);

	@POST
	@Path("/add")
	public ScmPurPricePrepareEntryWSBean add(ScmPurPricePrepareEntryWSBean bean);

	@POST
	@Path("/update")
	public ScmPurPricePrepareEntryWSBean update(ScmPurPricePrepareEntryWSBean bean);
	

	@POST
	@Path("/delete")
	public ScmPurPricePrepareEntryWSBean delete(ScmPurPricePrepareEntryWSBean bean);
	
}

