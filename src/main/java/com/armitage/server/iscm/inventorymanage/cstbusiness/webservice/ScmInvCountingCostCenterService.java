package com.armitage.server.iscm.inventorymanage.cstbusiness.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCountingCostCenterWSBean;

public interface ScmInvCountingCostCenterService {
	
	@POST
	@Path("/findPage")
	public ScmInvCountingCostCenterWSBean findPage(ScmInvCountingCostCenterWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmInvCountingCostCenterWSBean queryPage(ScmInvCountingCostCenterWSBean bean);

	@POST
	@Path("/select")
	public ScmInvCountingCostCenterWSBean select(ScmInvCountingCostCenterWSBean bean);

	@POST
	@Path("/add")
	public ScmInvCountingCostCenterWSBean add(ScmInvCountingCostCenterWSBean bean);

	@POST
	@Path("/update")
	public ScmInvCountingCostCenterWSBean update(ScmInvCountingCostCenterWSBean bean);
	

	@POST
	@Path("/delete")
	public ScmInvCountingCostCenterWSBean delete(ScmInvCountingCostCenterWSBean bean);
	
	@POST
	@Path("/findDifference")
	public ScmInvCountingCostCenterWSBean findDifference(ScmInvCountingCostCenterWSBean bean);
	
}
