
package com.armitage.server.iscm.purchasemanage.purchasesetting.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurSupplierSourceWSBean;

public interface ScmPurSupplierSourceService {
	
	@POST
	@Path("/findPage")
	public ScmPurSupplierSourceWSBean findPage(ScmPurSupplierSourceWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmPurSupplierSourceWSBean queryPage(ScmPurSupplierSourceWSBean bean);

	@POST
	@Path("/select")
	public ScmPurSupplierSourceWSBean select(ScmPurSupplierSourceWSBean bean);

	@POST
	@Path("/add")
	public ScmPurSupplierSourceWSBean add(ScmPurSupplierSourceWSBean bean);

	@POST
	@Path("/update")
	public ScmPurSupplierSourceWSBean update(ScmPurSupplierSourceWSBean bean);
	

	@POST
	@Path("/delete")
	public ScmPurSupplierSourceWSBean delete(ScmPurSupplierSourceWSBean bean);
	
	@POST
	@Path("/submit")
	public ScmPurSupplierSourceWSBean submit(ScmPurSupplierSourceWSBean bean);

	@POST
	@Path("/undoSubmit")
	public ScmPurSupplierSourceWSBean undoSubmit(ScmPurSupplierSourceWSBean bean);

	@POST
	@Path("/doAudit")
	public ScmPurSupplierSourceWSBean doAudit(ScmPurSupplierSourceWSBean bean);

	@POST
	@Path("/undoAudit")
	public ScmPurSupplierSourceWSBean undoAudit(ScmPurSupplierSourceWSBean bean);

	@POST
	@Path("/release")
	public ScmPurSupplierSourceWSBean release(ScmPurSupplierSourceWSBean bean);
}
