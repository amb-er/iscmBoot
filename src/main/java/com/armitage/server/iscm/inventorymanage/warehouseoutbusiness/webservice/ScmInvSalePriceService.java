package com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvSalePriceWSBean;

@Path("/scminvsaleprice/")
public interface ScmInvSalePriceService {
	@POST
	@Path("/findPage")
	public ScmInvSalePriceWSBean findPage(ScmInvSalePriceWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmInvSalePriceWSBean queryPage(ScmInvSalePriceWSBean bean);

	@POST
	@Path("/select")
	public ScmInvSalePriceWSBean select(ScmInvSalePriceWSBean bean);
	
	@POST
	@Path("/add")
	public ScmInvSalePriceWSBean add(ScmInvSalePriceWSBean bean);
	
	@POST
	@Path("/update")
	public ScmInvSalePriceWSBean update(ScmInvSalePriceWSBean bean);
	
	@POST
	@Path("/delete")
	public ScmInvSalePriceWSBean delete(ScmInvSalePriceWSBean bean);
	
	@POST
	@Path("/submit")
	
	public ScmInvSalePriceWSBean submit(ScmInvSalePriceWSBean bean);
	
	@POST
	@Path("/undoSubmit")
	public ScmInvSalePriceWSBean undoSubmit(ScmInvSalePriceWSBean bean);

	@POST
	@Path("/release")
	public ScmInvSalePriceWSBean release(ScmInvSalePriceWSBean bean);

	@POST
	@Path("/undoRelease")
	public ScmInvSalePriceWSBean undoRelease(ScmInvSalePriceWSBean bean);
	
	@POST
	@Path("/finish")
	public ScmInvSalePriceWSBean finish(ScmInvSalePriceWSBean bean);

	@POST
	@Path("/undoFinish")
	public ScmInvSalePriceWSBean undoFinish(ScmInvSalePriceWSBean bean);

	@POST
	@Path("/importInvSalePrice")
	public ScmInvSalePriceWSBean importInvSalePrice(ScmInvSalePriceWSBean bean);

	@POST
	@Path("/updatePrtCount")
	public ScmInvSalePriceWSBean updatePrtCount(ScmInvSalePriceWSBean bean);
}
