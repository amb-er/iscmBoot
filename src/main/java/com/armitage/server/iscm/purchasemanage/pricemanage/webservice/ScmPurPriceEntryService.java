
package com.armitage.server.iscm.purchasemanage.pricemanage.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurPriceEntryWSBean;

public interface ScmPurPriceEntryService {
	
	@POST
	@Path("/findPage")
	public ScmPurPriceEntryWSBean findPage(ScmPurPriceEntryWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmPurPriceEntryWSBean queryPage(ScmPurPriceEntryWSBean bean);

	@POST
	@Path("/select")
	public ScmPurPriceEntryWSBean select(ScmPurPriceEntryWSBean bean);

	@POST
	@Path("/add")
	public ScmPurPriceEntryWSBean add(ScmPurPriceEntryWSBean bean);

	@POST
	@Path("/update")
	public ScmPurPriceEntryWSBean update(ScmPurPriceEntryWSBean bean);
	

	@POST
	@Path("/delete")
	public ScmPurPriceEntryWSBean delete(ScmPurPriceEntryWSBean bean);
	
	@POST
	@Path("/selectByPmId")
	public ScmPurPriceEntryWSBean selectByPmId(ScmPurPriceEntryWSBean bean);
	
}
