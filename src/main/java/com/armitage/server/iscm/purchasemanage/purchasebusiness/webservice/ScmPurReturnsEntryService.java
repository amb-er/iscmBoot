package com.armitage.server.iscm.purchasemanage.purchasebusiness.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurReturnsEntryWSBean;

public interface ScmPurReturnsEntryService {
	
	@POST
	@Path("/findPage")
	public ScmPurReturnsEntryWSBean findPage(ScmPurReturnsEntryWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmPurReturnsEntryWSBean queryPage(ScmPurReturnsEntryWSBean bean);

	@POST
	@Path("/select")
	public ScmPurReturnsEntryWSBean select(ScmPurReturnsEntryWSBean bean);

	@POST
	@Path("/add")
	public ScmPurReturnsEntryWSBean add(ScmPurReturnsEntryWSBean bean);

	@POST
	@Path("/update")
	public ScmPurReturnsEntryWSBean update(ScmPurReturnsEntryWSBean bean);
	

	@POST
	@Path("/delete")
	public ScmPurReturnsEntryWSBean delete(ScmPurReturnsEntryWSBean bean);
	
	@POST
	@Path("/updateStatus")
	public ScmPurReturnsEntryWSBean updateStatus(ScmPurReturnsEntryWSBean bean);
	
}
