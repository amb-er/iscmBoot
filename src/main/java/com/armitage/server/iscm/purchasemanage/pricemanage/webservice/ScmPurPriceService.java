
package com.armitage.server.iscm.purchasemanage.pricemanage.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurPriceWSBean;

public interface ScmPurPriceService {
	
	@POST
	@Path("/findPage")
	public ScmPurPriceWSBean findPage(ScmPurPriceWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmPurPriceWSBean queryPage(ScmPurPriceWSBean bean);

	@POST
	@Path("/select")
	public ScmPurPriceWSBean select(ScmPurPriceWSBean bean);

	@POST
	@Path("/add")
	public ScmPurPriceWSBean add(ScmPurPriceWSBean bean);

	@POST
	@Path("/update")
	public ScmPurPriceWSBean update(ScmPurPriceWSBean bean);
	
	@POST
	@Path("/delete")
	public ScmPurPriceWSBean delete(ScmPurPriceWSBean bean);
	
	@POST
	@Path("/submit")
	public ScmPurPriceWSBean submit(ScmPurPriceWSBean bean);

	@POST
	@Path("/undoSubmit")
	public ScmPurPriceWSBean undoSubmit(ScmPurPriceWSBean bean);

	@POST
	@Path("/release")
	public ScmPurPriceWSBean release(ScmPurPriceWSBean bean);

	@POST
	@Path("/undoRelease")
	public ScmPurPriceWSBean undoRelease(ScmPurPriceWSBean bean);
	 	
	@POST
	@Path("/finish")
	public ScmPurPriceWSBean finish(ScmPurPriceWSBean bean);

	@POST
	@Path("/undoFinish")
	public ScmPurPriceWSBean undoFinish(ScmPurPriceWSBean bean);
	
	@POST
    @Path("/importExcel")
    public ScmPurPriceWSBean importExcel(ScmPurPriceWSBean bean);
	@POST
	@Path("/doAudit")
	public ScmPurPriceWSBean doAudit(ScmPurPriceWSBean bean);

	@POST
	@Path("/undoAudit")
	public ScmPurPriceWSBean undoAudit(ScmPurPriceWSBean bean);
	
	@POST
	@Path("/getEnterSupplierPlat")
	public ScmPurPriceWSBean getEnterSupplierPlat(ScmPurPriceWSBean bean);
	
	@POST
	@Path("/getPrePrice")
	public ScmPurPriceWSBean getPrePrice(ScmPurPriceWSBean bean);
	
	@POST
	@Path("/selectPrePriceByVendor")
	public ScmPurPriceWSBean selectPrePriceByVendor(ScmPurPriceWSBean bean);
	
	@POST
	@Path("/selectLastYearPriceByVendor")
	public ScmPurPriceWSBean selectLastYearPriceByVendor(ScmPurPriceWSBean bean);

	@POST
	@Path("/updatePrtCount")
	public ScmPurPriceWSBean updatePrtCount(ScmPurPriceWSBean bean);
}
