package com.armitage.server.iscm.inventorymanage.cstbusiness.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCostConsumeWSBean;

public interface ScmInvCostConsumeService {
	
	@POST
	@Path("/findPage")
	public ScmInvCostConsumeWSBean findPage(ScmInvCostConsumeWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmInvCostConsumeWSBean queryPage(ScmInvCostConsumeWSBean bean);

	@POST
	@Path("/select")
	public ScmInvCostConsumeWSBean select(ScmInvCostConsumeWSBean bean);

	@POST
	@Path("/add")
	public ScmInvCostConsumeWSBean add(ScmInvCostConsumeWSBean bean);

	@POST
	@Path("/update")
	public ScmInvCostConsumeWSBean update(ScmInvCostConsumeWSBean bean);
	
	@POST
	@Path("/updateDirect")
	public ScmInvCostConsumeWSBean updateDirect(ScmInvCostConsumeWSBean bean);

	@POST
	@Path("/delete")
	public ScmInvCostConsumeWSBean delete(ScmInvCostConsumeWSBean bean);
	
	@POST
    @Path("/submit")
    public ScmInvCostConsumeWSBean submit(ScmInvCostConsumeWSBean bean);
    
	@POST
    @Path("/undoSubmit")
    public ScmInvCostConsumeWSBean undoSubmit(ScmInvCostConsumeWSBean bean);
    
	@POST
	@Path("/postBillCheck")
	public ScmInvCostConsumeWSBean postBillCheck(ScmInvCostConsumeWSBean bean);
	
	@POST
	@Path("/postBill")
	public ScmInvCostConsumeWSBean postBill(ScmInvCostConsumeWSBean bean);
	
	@POST
	@Path("/cancelPostBillCheck")
	public ScmInvCostConsumeWSBean cancelPostBillCheck(ScmInvCostConsumeWSBean bean);
	
	@POST
	@Path("/cancelPostBill")
	public ScmInvCostConsumeWSBean cancelPostBill(ScmInvCostConsumeWSBean bean);
	
	@POST
    @Path("/importExcel")
    public ScmInvCostConsumeWSBean importExcel(ScmInvCostConsumeWSBean bean);

	@POST
	@Path("/updatePrtCount")
	public ScmInvCostConsumeWSBean updatePrtCount(ScmInvCostConsumeWSBean bean);
}
