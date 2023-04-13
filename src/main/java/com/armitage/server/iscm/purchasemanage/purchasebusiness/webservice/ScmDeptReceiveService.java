package com.armitage.server.iscm.purchasemanage.purchasebusiness.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurReceiveWSBean;

public interface ScmDeptReceiveService {
	
	@POST
	@Path("/findPage")
	public ScmPurReceiveWSBean findPage(ScmPurReceiveWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmPurReceiveWSBean queryPage(ScmPurReceiveWSBean bean);

	@POST
	@Path("/select")
	public ScmPurReceiveWSBean select(ScmPurReceiveWSBean bean);

	@POST
	@Path("/add")
	public ScmPurReceiveWSBean add(ScmPurReceiveWSBean bean);

	@POST
	@Path("/update")
	public ScmPurReceiveWSBean update(ScmPurReceiveWSBean bean);

	@POST
	@Path("/delete")
	public ScmPurReceiveWSBean delete(ScmPurReceiveWSBean bean);
}