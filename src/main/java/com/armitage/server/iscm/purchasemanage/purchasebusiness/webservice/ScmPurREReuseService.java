
package com.armitage.server.iscm.purchasemanage.purchasebusiness.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurREReuseWSBean;

public interface ScmPurREReuseService {
	
	@POST
	@Path("/findPage")
	public ScmPurREReuseWSBean findPage(ScmPurREReuseWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmPurREReuseWSBean queryPage(ScmPurREReuseWSBean bean);

	@POST
	@Path("/select")
	public ScmPurREReuseWSBean select(ScmPurREReuseWSBean bean);

	@POST
	@Path("/add")
	public ScmPurREReuseWSBean add(ScmPurREReuseWSBean bean);

	@POST
	@Path("/update")
	public ScmPurREReuseWSBean update(ScmPurREReuseWSBean bean);
	

	@POST
	@Path("/delete")
	public ScmPurREReuseWSBean delete(ScmPurREReuseWSBean bean);
	
}
