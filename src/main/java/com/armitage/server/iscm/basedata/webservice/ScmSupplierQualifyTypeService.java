package com.armitage.server.iscm.basedata.webservice;


import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.iscm.basedata.model.ScmSupplierQualifyTypeWSBean;

public interface ScmSupplierQualifyTypeService {
	
	@POST
	@Path("/findPage")
	public ScmSupplierQualifyTypeWSBean findPage(ScmSupplierQualifyTypeWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmSupplierQualifyTypeWSBean queryPage(ScmSupplierQualifyTypeWSBean bean);

	@POST
	@Path("/select")
	public ScmSupplierQualifyTypeWSBean select(ScmSupplierQualifyTypeWSBean bean);

	@POST
	@Path("/add")
	public ScmSupplierQualifyTypeWSBean add(ScmSupplierQualifyTypeWSBean bean);

	@POST
	@Path("/update")
	public ScmSupplierQualifyTypeWSBean update(ScmSupplierQualifyTypeWSBean bean);
	

	@POST
	@Path("/delete")
	public ScmSupplierQualifyTypeWSBean delete(ScmSupplierQualifyTypeWSBean bean);
	
}
