package com.armitage.server.iscm.purchasemanage.purchasebusiness.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurReceiveEntryWSBean;

public interface ScmPurReceiveEntryService {
	
	
	@POST
	@Path("/findPage")
	public ScmPurReceiveEntryWSBean findPage(ScmPurReceiveEntryWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmPurReceiveEntryWSBean queryPage(ScmPurReceiveEntryWSBean bean);

	@POST
	@Path("/select")
	public ScmPurReceiveEntryWSBean select(ScmPurReceiveEntryWSBean bean);

	@POST
	@Path("/add")
	public ScmPurReceiveEntryWSBean add(ScmPurReceiveEntryWSBean bean);

	@POST
	@Path("/update")
	public ScmPurReceiveEntryWSBean update(ScmPurReceiveEntryWSBean bean);
	

	@POST
	@Path("/delete")
	public ScmPurReceiveEntryWSBean delete(ScmPurReceiveEntryWSBean bean);
	
	@POST
	@Path("/updateStatus")
	public ScmPurReceiveEntryWSBean updateStatus(ScmPurReceiveEntryWSBean bean);
	
	@POST
	@Path("/getDataForLeadInto")
	public ScmPurReceiveEntryWSBean getDataForLeadInto(ScmPurReceiveEntryWSBean bean);
	
}

