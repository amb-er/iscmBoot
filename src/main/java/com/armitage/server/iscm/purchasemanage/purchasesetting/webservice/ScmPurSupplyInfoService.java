
package com.armitage.server.iscm.purchasemanage.purchasesetting.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurSupplyInfoWSBean;

public interface ScmPurSupplyInfoService {
	
	@POST
	@Path("/findPage")
	public ScmPurSupplyInfoWSBean findPage(ScmPurSupplyInfoWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmPurSupplyInfoWSBean queryPage(ScmPurSupplyInfoWSBean bean);

	@POST
	@Path("/select")
	public ScmPurSupplyInfoWSBean select(ScmPurSupplyInfoWSBean bean);

	@POST
	@Path("/add")
	public ScmPurSupplyInfoWSBean add(ScmPurSupplyInfoWSBean bean);

	@POST
	@Path("/update")
	public ScmPurSupplyInfoWSBean update(ScmPurSupplyInfoWSBean bean);

	@POST
	@Path("/delete")
	public ScmPurSupplyInfoWSBean delete(ScmPurSupplyInfoWSBean bean);
	
	@POST
	@Path("/findVendor")
	public ScmPurSupplyInfoWSBean findVendor(ScmPurSupplyInfoWSBean bean);
	
	@POST
	@Path("/getSupplyInfoByItemSAndVendorS")
	public ScmPurSupplyInfoWSBean getSupplyInfoByItemSAndVendorS(ScmPurSupplyInfoWSBean bean);

//	@POST
//	@Path("/approved")
//	public ScmPurSupplyInfoWSBean approved(ScmPurSupplyInfoWSBean bean);
//
//	@POST
//	@Path("/unApproved")
//	public ScmPurSupplyInfoWSBean unApproved(ScmPurSupplyInfoWSBean bean);
}
