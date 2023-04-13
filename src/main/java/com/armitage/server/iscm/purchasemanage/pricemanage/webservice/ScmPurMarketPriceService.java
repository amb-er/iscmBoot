package com.armitage.server.iscm.purchasemanage.pricemanage.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurMarketPriceWSBean;

public interface ScmPurMarketPriceService {
	
	@POST
	@Path("/findPage")
	public ScmPurMarketPriceWSBean findPage(ScmPurMarketPriceWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmPurMarketPriceWSBean queryPage(ScmPurMarketPriceWSBean bean);

	@POST
	@Path("/select")
	public ScmPurMarketPriceWSBean select(ScmPurMarketPriceWSBean bean);

	@POST
	@Path("/add")
	public ScmPurMarketPriceWSBean add(ScmPurMarketPriceWSBean bean);

	@POST
	@Path("/update")
	public ScmPurMarketPriceWSBean update(ScmPurMarketPriceWSBean bean);
	

	@POST
	@Path("/delete")
	public ScmPurMarketPriceWSBean delete(ScmPurMarketPriceWSBean bean);
	
	@POST
	@Path("/submit")
	public ScmPurMarketPriceWSBean submit(ScmPurMarketPriceWSBean bean);

	@POST
	@Path("/undoSubmit")
	public ScmPurMarketPriceWSBean undoSubmit(ScmPurMarketPriceWSBean bean);

	@POST
	@Path("/release")
	public ScmPurMarketPriceWSBean release(ScmPurMarketPriceWSBean bean);

	@POST
	@Path("/undoRelease")
	public ScmPurMarketPriceWSBean undoRelease(ScmPurMarketPriceWSBean bean);
	
	@POST
	@Path("/finish")
	public ScmPurMarketPriceWSBean finish(ScmPurMarketPriceWSBean bean);
	
	@POST
	@Path("/undoFinish")
	public ScmPurMarketPriceWSBean undoFinish(ScmPurMarketPriceWSBean bean);

	@POST
	@Path("/updatePrtCount")
	public ScmPurMarketPriceWSBean updatePrtCount(ScmPurMarketPriceWSBean bean);
}
