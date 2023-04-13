package com.armitage.server.iscm.purchasemanage.pricemanage.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurQuotationWSBean;

public interface ScmPurQuotationService {
	
	@POST
	@Path("/findPage")
	public ScmPurQuotationWSBean findPage(ScmPurQuotationWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmPurQuotationWSBean queryPage(ScmPurQuotationWSBean bean);

	@POST
	@Path("/select")
	public ScmPurQuotationWSBean select(ScmPurQuotationWSBean bean);

	@POST
	@Path("/add")
	public ScmPurQuotationWSBean add(ScmPurQuotationWSBean bean);

	@POST
	@Path("/update")
	public ScmPurQuotationWSBean update(ScmPurQuotationWSBean bean);
	

	@POST
	@Path("/delete")
	public ScmPurQuotationWSBean delete(ScmPurQuotationWSBean bean);
	
	@POST
	@Path("/release")
	public ScmPurQuotationWSBean release(ScmPurQuotationWSBean bean);
	
	@POST
	@Path("/undoRelease")
	public ScmPurQuotationWSBean undoRelease(ScmPurQuotationWSBean bean);

	@POST
	@Path("/importPurQuotation")
	public ScmPurQuotationWSBean importPurQuotation(ScmPurQuotationWSBean bean);
	
	@POST
	@Path("/submit")
	public ScmPurQuotationWSBean submit(ScmPurQuotationWSBean bean);
	
	@POST
	@Path("/undoSubmit")
	public ScmPurQuotationWSBean undoSubmit(ScmPurQuotationWSBean bean);

	@POST
	@Path("/doAudit")
	public ScmPurQuotationWSBean doAudit(ScmPurQuotationWSBean bean);

	@POST
	@Path("/undoAudit")
	public ScmPurQuotationWSBean undoAudit(ScmPurQuotationWSBean bean);

	@POST
	@Path("/finish")
	public ScmPurQuotationWSBean finish(ScmPurQuotationWSBean bean);

	@POST
	@Path("/undoFinish")
	public ScmPurQuotationWSBean undoFinish(ScmPurQuotationWSBean bean);

	@POST
	@Path("/updatePrtCount")
	public ScmPurQuotationWSBean updatePrtCount(ScmPurQuotationWSBean bean);
}
