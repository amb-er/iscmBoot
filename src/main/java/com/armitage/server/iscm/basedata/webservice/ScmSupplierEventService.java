package com.armitage.server.iscm.basedata.webservice;


import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.iscm.basedata.model.ScmSupplierEventWSBean;

public interface ScmSupplierEventService {
	
	@POST
	@Path("/findPage")
	public ScmSupplierEventWSBean findPage(ScmSupplierEventWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmSupplierEventWSBean queryPage(ScmSupplierEventWSBean bean);

	@POST
	@Path("/select")
	public ScmSupplierEventWSBean select(ScmSupplierEventWSBean bean);

	@POST
	@Path("/add")
	public ScmSupplierEventWSBean add(ScmSupplierEventWSBean bean);

	@POST
	@Path("/update")
	public ScmSupplierEventWSBean update(ScmSupplierEventWSBean bean);
	

	@POST
	@Path("/delete")
	public ScmSupplierEventWSBean delete(ScmSupplierEventWSBean bean);
	
	@POST
	@Path("/generateEventQRcode")
	public ScmSupplierEventWSBean generateEventQRcode(ScmSupplierEventWSBean bean);
	
}

