
package com.armitage.server.iscm.purchasemanage.purchasesetting.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurBuyerWSBean;
@Path("/scmPurBuyer/")
public interface ScmPurBuyerService {
	
	@POST
	@Path("/findPage")
	public ScmPurBuyerWSBean findPage(ScmPurBuyerWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmPurBuyerWSBean queryPage(ScmPurBuyerWSBean bean);

	@POST
	@Path("/select")
	public ScmPurBuyerWSBean select(ScmPurBuyerWSBean bean);

	@POST
	@Path("/add")
	public ScmPurBuyerWSBean add(ScmPurBuyerWSBean bean);

	@POST
	@Path("/update")
	public ScmPurBuyerWSBean update(ScmPurBuyerWSBean bean);
	

	@POST
	@Path("/delete")
	public ScmPurBuyerWSBean delete(ScmPurBuyerWSBean bean);
	
	@POST
	@Path("/updateGroup")
	public ScmPurBuyerWSBean updateGroup(ScmPurBuyerWSBean bean);
	
	@POST
	@Path("/selectByParentId")
	public ScmPurBuyerWSBean selectByParentId(ScmPurBuyerWSBean bean);
	
	@POST
	@Path("/deleteBuyerCheck")
	public ScmPurBuyerWSBean deleteBuyerCheck(ScmPurBuyerWSBean bean);
	
	@POST
	@Path("/getGrantedOrg")
	public ScmPurBuyerWSBean getGrantedOrg(ScmPurBuyerWSBean bean);
	
	@POST
	@Path("/grantOrgUnit")
	public ScmPurBuyerWSBean grantOrgUnit(ScmPurBuyerWSBean bean);
}
