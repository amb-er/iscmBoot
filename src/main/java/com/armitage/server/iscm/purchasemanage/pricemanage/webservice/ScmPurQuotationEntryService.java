package com.armitage.server.iscm.purchasemanage.pricemanage.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurQuotationEntryWSBean;

public interface ScmPurQuotationEntryService {
	
	@POST
	@Path("/findPage")
	public ScmPurQuotationEntryWSBean findPage(ScmPurQuotationEntryWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmPurQuotationEntryWSBean queryPage(ScmPurQuotationEntryWSBean bean);

	@POST
	@Path("/select")
	public ScmPurQuotationEntryWSBean select(ScmPurQuotationEntryWSBean bean);

	@POST
	@Path("/add")
	public ScmPurQuotationEntryWSBean add(ScmPurQuotationEntryWSBean bean);

	@POST
	@Path("/update")
	public ScmPurQuotationEntryWSBean update(ScmPurQuotationEntryWSBean bean);
	

	@POST
	@Path("/delete")
	public ScmPurQuotationEntryWSBean delete(ScmPurQuotationEntryWSBean bean);
		
}
