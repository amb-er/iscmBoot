package com.armitage.server.iscm.purchasemanage.purchasebusiness.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurCheckWSBean;

public interface ScmPurCheckService {
	
	@POST
	@Path("/findPage")
	public ScmPurCheckWSBean findPage(ScmPurCheckWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmPurCheckWSBean queryPage(ScmPurCheckWSBean bean);

	@POST
	@Path("/select")
	public ScmPurCheckWSBean select(ScmPurCheckWSBean bean);

	@POST
	@Path("/add")
	public ScmPurCheckWSBean add(ScmPurCheckWSBean bean);

	@POST
	@Path("/update")
	public ScmPurCheckWSBean update(ScmPurCheckWSBean bean);
	

	@POST
	@Path("/delete")
	public ScmPurCheckWSBean delete(ScmPurCheckWSBean bean);
	
	@POST
	@Path("/confirm")
	public ScmPurCheckWSBean confirm(ScmPurCheckWSBean bean);

	@POST
	@Path("/unConfirm")
	public ScmPurCheckWSBean unConfirm(ScmPurCheckWSBean bean);

	@POST
	@Path("/updatePrtCount")
	public ScmPurCheckWSBean updatePrtCount(ScmPurCheckWSBean bean);
}

