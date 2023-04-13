
package com.armitage.server.iscm.purchasemanage.purchasesetting.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurGroupWSBean;

@Path("/scmPurGroup/")
public interface ScmPurGroupService {
	
	@POST
	@Path("/findPage")
	public ScmPurGroupWSBean findPage(ScmPurGroupWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmPurGroupWSBean queryPage(ScmPurGroupWSBean bean);

	@POST
	@Path("/select")
	public ScmPurGroupWSBean select(ScmPurGroupWSBean bean);

	@POST
	@Path("/add")
	public ScmPurGroupWSBean add(ScmPurGroupWSBean bean);

	@POST
	@Path("/update")
	public ScmPurGroupWSBean update(ScmPurGroupWSBean bean);
	

	@POST
	@Path("/delete")
	public ScmPurGroupWSBean delete(ScmPurGroupWSBean bean);
	
	@POST
	@Path("/selectByPurGroupNo")
	public ScmPurGroupWSBean selectByPurGroupNo(ScmPurGroupWSBean bean);
	
}
