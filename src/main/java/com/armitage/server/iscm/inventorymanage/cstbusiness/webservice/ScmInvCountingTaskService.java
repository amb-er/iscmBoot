package com.armitage.server.iscm.inventorymanage.cstbusiness.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCountingTaskWSBean;

public interface ScmInvCountingTaskService {
	
	@POST
	@Path("/findPage")
	public ScmInvCountingTaskWSBean findPage(ScmInvCountingTaskWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmInvCountingTaskWSBean queryPage(ScmInvCountingTaskWSBean bean);

	@POST
	@Path("/select")
	public ScmInvCountingTaskWSBean select(ScmInvCountingTaskWSBean bean);

	@POST
	@Path("/add")
	public ScmInvCountingTaskWSBean add(ScmInvCountingTaskWSBean bean);

	@POST
	@Path("/update")
	public ScmInvCountingTaskWSBean update(ScmInvCountingTaskWSBean bean);
	

	@POST
	@Path("/delete")
	public ScmInvCountingTaskWSBean delete(ScmInvCountingTaskWSBean bean);
	
	@POST
    @Path("/selectMaxIdByDate")
    public ScmInvCountingTaskWSBean selectMaxIdByDate(ScmInvCountingTaskWSBean bean);
	
	@POST
    @Path("/selectByDate")
    public ScmInvCountingTaskWSBean selectByDate(ScmInvCountingTaskWSBean bean);
	
	@POST
    @Path("/generateCounting")
    public ScmInvCountingTaskWSBean generateCounting(ScmInvCountingTaskWSBean bean);
	

	@POST
    @Path("/generateCountingCheck")
    public ScmInvCountingTaskWSBean generateCountingCheck(ScmInvCountingTaskWSBean bean);
	
	@POST
    @Path("/selectByOrgUnitNoAndWareHouseId")
    public ScmInvCountingTaskWSBean selectByOrgUnitNoAndWareHouseId(ScmInvCountingTaskWSBean bean);
	
	@POST
    @Path("/selectByOrgUnitNoAndUseOrgUnitNo")
    public ScmInvCountingTaskWSBean selectByOrgUnitNoAndUseOrgUnitNo(ScmInvCountingTaskWSBean bean);
	
	@POST
    @Path("/generateCosting")
    public ScmInvCountingTaskWSBean generateCosting(ScmInvCountingTaskWSBean bean);
	
	@POST
    @Path("/generateCostingCheck")
    public ScmInvCountingTaskWSBean generateCostingCheck(ScmInvCountingTaskWSBean bean);
	
	@POST
    @Path("/countingFinish")
    public ScmInvCountingTaskWSBean countingFinish(ScmInvCountingTaskWSBean bean);
	
	@POST
    @Path("/refreshAccount")
    public ScmInvCountingTaskWSBean refreshAccount(ScmInvCountingTaskWSBean bean);
	
	@POST
	@Path("/queryCountInvTaskDiff")
	public ScmInvCountingTaskWSBean queryCountInvTaskDiff(ScmInvCountingTaskWSBean bean);
	
	@POST
    @Path("/queryCountCostTaskDiff")
    public ScmInvCountingTaskWSBean queryCountCostTaskDiff(ScmInvCountingTaskWSBean bean);

	@POST
    @Path("/confirm")
    public ScmInvCountingTaskWSBean confirm(ScmInvCountingTaskWSBean bean);
	
	@POST
    @Path("/splitOrgAdd")
    public ScmInvCountingTaskWSBean splitOrgAdd(ScmInvCountingTaskWSBean bean);
	
	@POST
    @Path("/queryByTaskNo")
    public ScmInvCountingTaskWSBean queryByTaskNo(ScmInvCountingTaskWSBean bean);
}
