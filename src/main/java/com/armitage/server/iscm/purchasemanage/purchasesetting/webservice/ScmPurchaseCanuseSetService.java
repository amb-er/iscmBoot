
package com.armitage.server.iscm.purchasemanage.purchasesetting.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurchaseCanuseSetWSBean;

public interface ScmPurchaseCanuseSetService {
	
	@POST
	@Path("/findPage")
	public ScmPurchaseCanuseSetWSBean findPage(ScmPurchaseCanuseSetWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmPurchaseCanuseSetWSBean queryPage(ScmPurchaseCanuseSetWSBean bean);

	@POST
	@Path("/select")
	public ScmPurchaseCanuseSetWSBean select(ScmPurchaseCanuseSetWSBean bean);

	@POST
	@Path("/add")
	public ScmPurchaseCanuseSetWSBean add(ScmPurchaseCanuseSetWSBean bean);

	@POST
	@Path("/update")
	public ScmPurchaseCanuseSetWSBean update(ScmPurchaseCanuseSetWSBean bean);
	

	@POST
	@Path("/delete")
	public ScmPurchaseCanuseSetWSBean delete(ScmPurchaseCanuseSetWSBean bean);
	
	@POST
	@Path("/checkDate")
	public ScmPurchaseCanuseSetWSBean checkDate(ScmPurchaseCanuseSetWSBean bean);
	
}
