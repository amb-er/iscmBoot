package com.armitage.server.iscm.basedata.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.iscm.basedata.model.ScmSupplierQualifieInfoBillWSBean;

public interface ScmSupplierQualifieInfoBillService {
	
	@POST
	@Path("/findPage")
	public ScmSupplierQualifieInfoBillWSBean findPage(ScmSupplierQualifieInfoBillWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmSupplierQualifieInfoBillWSBean queryPage(ScmSupplierQualifieInfoBillWSBean bean);

	@POST
	@Path("/select")
	public ScmSupplierQualifieInfoBillWSBean select(ScmSupplierQualifieInfoBillWSBean bean);

	@POST
	@Path("/add")
	public ScmSupplierQualifieInfoBillWSBean add(ScmSupplierQualifieInfoBillWSBean bean);

	@POST
	@Path("/update")
	public ScmSupplierQualifieInfoBillWSBean update(ScmSupplierQualifieInfoBillWSBean bean);
	

	@POST
	@Path("/delete")
	public ScmSupplierQualifieInfoBillWSBean delete(ScmSupplierQualifieInfoBillWSBean bean);
	
	@POST
	@Path("/submit")
	public ScmSupplierQualifieInfoBillWSBean submit(ScmSupplierQualifieInfoBillWSBean bean);

	@POST
	@Path("/undoSubmit")
	public ScmSupplierQualifieInfoBillWSBean undoSubmit(ScmSupplierQualifieInfoBillWSBean bean);

	@POST
	@Path("/doAudit")
	public ScmSupplierQualifieInfoBillWSBean doAudit(ScmSupplierQualifieInfoBillWSBean bean);

	@POST
	@Path("/undoAudit")
	public ScmSupplierQualifieInfoBillWSBean undoAudit(ScmSupplierQualifieInfoBillWSBean bean);
}
