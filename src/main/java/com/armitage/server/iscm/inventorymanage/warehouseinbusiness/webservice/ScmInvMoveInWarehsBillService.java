package com.armitage.server.iscm.inventorymanage.warehouseinbusiness.webservice;


import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvMoveInWarehsBillWSBean;

public interface ScmInvMoveInWarehsBillService {
	
	@POST
	@Path("/findPage")
	public ScmInvMoveInWarehsBillWSBean findPage(ScmInvMoveInWarehsBillWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmInvMoveInWarehsBillWSBean queryPage(ScmInvMoveInWarehsBillWSBean bean);

	@POST
	@Path("/select")
	public ScmInvMoveInWarehsBillWSBean select(ScmInvMoveInWarehsBillWSBean bean);

	@POST
	@Path("/add")
	public ScmInvMoveInWarehsBillWSBean add(ScmInvMoveInWarehsBillWSBean bean);

	@POST
	@Path("/update")
	public ScmInvMoveInWarehsBillWSBean update(ScmInvMoveInWarehsBillWSBean bean);
	
	@POST
	@Path("/updateDirect")
	public ScmInvMoveInWarehsBillWSBean updateDirect(ScmInvMoveInWarehsBillWSBean bean);

	@POST
	@Path("/delete")
	public ScmInvMoveInWarehsBillWSBean delete(ScmInvMoveInWarehsBillWSBean bean);
	
	@POST
    @Path("/submit")
    public ScmInvMoveInWarehsBillWSBean submit(ScmInvMoveInWarehsBillWSBean bean);
    
	@POST
    @Path("/undoSubmit")
    public ScmInvMoveInWarehsBillWSBean undoSubmit(ScmInvMoveInWarehsBillWSBean bean);
    
	@POST
    @Path("/postBillCheck")
    public ScmInvMoveInWarehsBillWSBean postBillCheck(ScmInvMoveInWarehsBillWSBean bean);
    
	@POST
    @Path("/postBill")
    public ScmInvMoveInWarehsBillWSBean postBill(ScmInvMoveInWarehsBillWSBean bean);
    
	@POST
    @Path("/cancelPostBill")
    public ScmInvMoveInWarehsBillWSBean cancelPostBill(ScmInvMoveInWarehsBillWSBean bean);
    
	@POST
    @Path("/cancelPostBillCheck")
    public ScmInvMoveInWarehsBillWSBean cancelPostBillCheck(ScmInvMoveInWarehsBillWSBean bean);

	@POST
	@Path("/updatePrtCount")
	public ScmInvMoveInWarehsBillWSBean updatePrtCount(ScmInvMoveInWarehsBillWSBean bean);
}

