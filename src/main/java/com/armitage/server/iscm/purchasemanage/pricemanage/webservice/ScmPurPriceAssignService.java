
package com.armitage.server.iscm.purchasemanage.pricemanage.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurPriceAssignWSBean;

public interface ScmPurPriceAssignService {
	
	@POST
	@Path("/findPage")
	public ScmPurPriceAssignWSBean findPage(ScmPurPriceAssignWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmPurPriceAssignWSBean queryPage(ScmPurPriceAssignWSBean bean);

	@POST
	@Path("/select")
	public ScmPurPriceAssignWSBean select(ScmPurPriceAssignWSBean bean);

	@POST
	@Path("/add")
	public ScmPurPriceAssignWSBean add(ScmPurPriceAssignWSBean bean);

	@POST
	@Path("/update")
	public ScmPurPriceAssignWSBean update(ScmPurPriceAssignWSBean bean);
	

	@POST
	@Path("/delete")
	public ScmPurPriceAssignWSBean delete(ScmPurPriceAssignWSBean bean);
	
}
