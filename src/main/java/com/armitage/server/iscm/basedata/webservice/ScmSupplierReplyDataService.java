package com.armitage.server.iscm.basedata.webservice;


import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.iscm.basedata.model.ScmSupplierReplyDataWSBean;

public interface ScmSupplierReplyDataService {
	
	@POST
	@Path("/findPage")
	public ScmSupplierReplyDataWSBean findPage(ScmSupplierReplyDataWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmSupplierReplyDataWSBean queryPage(ScmSupplierReplyDataWSBean bean);

	@POST
	@Path("/select")
	public ScmSupplierReplyDataWSBean select(ScmSupplierReplyDataWSBean bean);

	@POST
	@Path("/add")
	public ScmSupplierReplyDataWSBean add(ScmSupplierReplyDataWSBean bean);

	@POST
	@Path("/update")
	public ScmSupplierReplyDataWSBean update(ScmSupplierReplyDataWSBean bean);
	

	@POST
	@Path("/delete")
	public ScmSupplierReplyDataWSBean delete(ScmSupplierReplyDataWSBean bean);
	
}
