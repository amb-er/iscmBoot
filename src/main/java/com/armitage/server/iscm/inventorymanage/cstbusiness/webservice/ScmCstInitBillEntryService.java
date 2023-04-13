package com.armitage.server.iscm.inventorymanage.cstbusiness.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmCstInitBillEntryWSBean;

public interface ScmCstInitBillEntryService {
	
	@POST
	@Path("/findPage")
	public ScmCstInitBillEntryWSBean findPage(ScmCstInitBillEntryWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmCstInitBillEntryWSBean queryPage(ScmCstInitBillEntryWSBean bean);

	@POST
	@Path("/select")
	public ScmCstInitBillEntryWSBean select(ScmCstInitBillEntryWSBean bean);

	@POST
	@Path("/add")
	public ScmCstInitBillEntryWSBean add(ScmCstInitBillEntryWSBean bean);

	@POST
	@Path("/update")
	public ScmCstInitBillEntryWSBean update(ScmCstInitBillEntryWSBean bean);
	

	@POST
	@Path("/delete")
	public ScmCstInitBillEntryWSBean delete(ScmCstInitBillEntryWSBean bean);
	
}
