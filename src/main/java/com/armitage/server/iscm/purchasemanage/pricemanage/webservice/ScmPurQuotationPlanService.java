
package com.armitage.server.iscm.purchasemanage.pricemanage.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurQuotationPlanWSBean;

public interface ScmPurQuotationPlanService {
	
	@POST
	@Path("/findPage")
	public ScmPurQuotationPlanWSBean findPage(ScmPurQuotationPlanWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmPurQuotationPlanWSBean queryPage(ScmPurQuotationPlanWSBean bean);

	@POST
	@Path("/select")
	public ScmPurQuotationPlanWSBean select(ScmPurQuotationPlanWSBean bean);

	@POST
	@Path("/add")
	public ScmPurQuotationPlanWSBean add(ScmPurQuotationPlanWSBean bean);

	@POST
	@Path("/update")
	public ScmPurQuotationPlanWSBean update(ScmPurQuotationPlanWSBean bean);
	
	@POST
	@Path("/delete")
	public ScmPurQuotationPlanWSBean delete(ScmPurQuotationPlanWSBean bean);
	
	@POST
	@Path("/submit")
	public ScmPurQuotationPlanWSBean submit(ScmPurQuotationPlanWSBean bean);

	@POST
	@Path("/undoSubmit")
	public ScmPurQuotationPlanWSBean undoSubmit(ScmPurQuotationPlanWSBean bean);

	@POST
	@Path("/release")
	public ScmPurQuotationPlanWSBean release(ScmPurQuotationPlanWSBean bean);

	@POST
	@Path("/undoRelease")
	public ScmPurQuotationPlanWSBean undoRelease(ScmPurQuotationPlanWSBean bean);

	@POST
	@Path("/distribute")
	public ScmPurQuotationPlanWSBean distribute(ScmPurQuotationPlanWSBean bean);
	
	@POST
	@Path("/getDataForLeadInto")
	public ScmPurQuotationPlanWSBean getDataForLeadInto(ScmPurQuotationPlanWSBean bean);

	@POST
	@Path("/updatePrtCount")
	public ScmPurQuotationPlanWSBean updatePrtCount(ScmPurQuotationPlanWSBean bean);
}
