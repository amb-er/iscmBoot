package com.armitage.server.iscm.inventorymanage.inventoryinitialization.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.iscm.inventorymanage.inventoryinitialization.model.ScmInvInitBillEntryWSBean;


public interface ScmInvInitBillEntryService {
	
	@POST
	@Path("/findPage")
	public ScmInvInitBillEntryWSBean findPage(ScmInvInitBillEntryWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmInvInitBillEntryWSBean queryPage(ScmInvInitBillEntryWSBean bean);

	@POST
	@Path("/select")
	public ScmInvInitBillEntryWSBean select(ScmInvInitBillEntryWSBean bean);

	@POST
	@Path("/add")
	public ScmInvInitBillEntryWSBean add(ScmInvInitBillEntryWSBean bean);

	@POST
	@Path("/update")
	public ScmInvInitBillEntryWSBean update(ScmInvInitBillEntryWSBean bean);
	

	@POST
	@Path("/delete")
	public ScmInvInitBillEntryWSBean delete(ScmInvInitBillEntryWSBean bean);
	
	@POST
	@Path("/getDataForLeadInto")
	public ScmInvInitBillEntryWSBean getDataForLeadInto(ScmInvInitBillEntryWSBean bean);
}
