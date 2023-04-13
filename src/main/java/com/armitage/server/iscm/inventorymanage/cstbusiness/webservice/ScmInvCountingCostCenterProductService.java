
package com.armitage.server.iscm.inventorymanage.cstbusiness.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCountingCostCenterProductWSBean;

public interface ScmInvCountingCostCenterProductService {
	
	@POST
	@Path("/findPage")
	public ScmInvCountingCostCenterProductWSBean findPage(ScmInvCountingCostCenterProductWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmInvCountingCostCenterProductWSBean queryPage(ScmInvCountingCostCenterProductWSBean bean);

	@POST
	@Path("/select")
	public ScmInvCountingCostCenterProductWSBean select(ScmInvCountingCostCenterProductWSBean bean);

	@POST
	@Path("/add")
	public ScmInvCountingCostCenterProductWSBean add(ScmInvCountingCostCenterProductWSBean bean);

	@POST
	@Path("/update")
	public ScmInvCountingCostCenterProductWSBean update(ScmInvCountingCostCenterProductWSBean bean);
	

	@POST
	@Path("/delete")
	public ScmInvCountingCostCenterProductWSBean delete(ScmInvCountingCostCenterProductWSBean bean);
	
}
