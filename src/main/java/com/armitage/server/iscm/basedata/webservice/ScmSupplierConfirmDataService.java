package com.armitage.server.iscm.basedata.webservice;


import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.iscm.basedata.model.ScmSupplierConfirmDataWSBean;

public interface ScmSupplierConfirmDataService {
	
	@POST
	@Path("/findPage")
	public ScmSupplierConfirmDataWSBean findPage(ScmSupplierConfirmDataWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmSupplierConfirmDataWSBean queryPage(ScmSupplierConfirmDataWSBean bean);

	@POST
	@Path("/select")
	public ScmSupplierConfirmDataWSBean select(ScmSupplierConfirmDataWSBean bean);

	@POST
	@Path("/add")
	public ScmSupplierConfirmDataWSBean add(ScmSupplierConfirmDataWSBean bean);

	@POST
	@Path("/update")
	public ScmSupplierConfirmDataWSBean update(ScmSupplierConfirmDataWSBean bean);
	

	@POST
	@Path("/delete")
	public ScmSupplierConfirmDataWSBean delete(ScmSupplierConfirmDataWSBean bean);
	

	@POST
	@Path("/saveConfirmAdj")
	public ScmSupplierConfirmDataWSBean saveConfirmAdj(ScmSupplierConfirmDataWSBean bean);
}

