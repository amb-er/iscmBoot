package com.armitage.server.iscm.basedata.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.iscm.basedata.model.ScmsupplierWSBean;

public interface ScmsupplierService {
	
	@POST
	@Path("/findPage")
	public ScmsupplierWSBean findPage(ScmsupplierWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmsupplierWSBean queryPage(ScmsupplierWSBean bean);

	@POST
	@Path("/select")
	public ScmsupplierWSBean select(ScmsupplierWSBean bean);

	@POST
	@Path("/add")
	public ScmsupplierWSBean add(ScmsupplierWSBean bean);

	@POST
	@Path("/update")
	public ScmsupplierWSBean update(ScmsupplierWSBean bean);
	
	@POST
	@Path("/delete")
	public ScmsupplierWSBean delete(ScmsupplierWSBean bean);
	
	@POST
	@Path("/updateStatus")
	public ScmsupplierWSBean updateStatus(ScmsupplierWSBean bean);
	
	@POST
	@Path("/checkUse")
	public ScmsupplierWSBean checkUse(ScmsupplierWSBean bean);
	
	@POST
	@Path("/saveUnified")
	public ScmsupplierWSBean saveUnified(ScmsupplierWSBean bean);

	@POST
	@Path("/selectUnified")
	public ScmsupplierWSBean selectUnified(ScmsupplierWSBean bean);
	
	@POST
	@Path("/selectByCode")
	public ScmsupplierWSBean selectByCode(ScmsupplierWSBean bean);

	@POST
	@Path("/findSameNameVendor")
	public ScmsupplierWSBean findSameNameVendor(ScmsupplierWSBean bean);

	@POST
	@Path("/selectPlatFormUser")
	public ScmsupplierWSBean selectPlatFormUser(ScmsupplierWSBean bean);

	@POST
	@Path("/changePlatFormAdmin")
	public ScmsupplierWSBean changePlatFormAdmin(ScmsupplierWSBean bean);

	@POST
	@Path("/auditPlatFormAdmin")
	public ScmsupplierWSBean auditPlatFormAdmin(ScmsupplierWSBean bean);

	@POST
	@Path("/disable")
	public ScmsupplierWSBean disable(ScmsupplierWSBean bean);

	@POST
	@Path("/enable")
	public ScmsupplierWSBean enable(ScmsupplierWSBean bean);
	
	@POST
	@Path("/getQualifieInfo")
	public ScmsupplierWSBean getQualifieInfo(ScmsupplierWSBean bean);
}
