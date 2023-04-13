package com.armitage.server.iscm.inventorymanage.warehouseinbusiness.webservice;


import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvOtherInWarehsBillWSBean;

public interface ScmInvOtherInWarehsBillService {
	
	@POST
	@Path("/findPage")
	public ScmInvOtherInWarehsBillWSBean findPage(ScmInvOtherInWarehsBillWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmInvOtherInWarehsBillWSBean queryPage(ScmInvOtherInWarehsBillWSBean bean);

	@POST
	@Path("/select")
	public ScmInvOtherInWarehsBillWSBean select(ScmInvOtherInWarehsBillWSBean bean);

	@POST
	@Path("/add")
	public ScmInvOtherInWarehsBillWSBean add(ScmInvOtherInWarehsBillWSBean bean);

	@POST
	@Path("/update")
	public ScmInvOtherInWarehsBillWSBean update(ScmInvOtherInWarehsBillWSBean bean);
	
	@POST
	@Path("/updateDirect")
	public ScmInvOtherInWarehsBillWSBean updateDirect(ScmInvOtherInWarehsBillWSBean bean);

	@POST
	@Path("/delete")
	public ScmInvOtherInWarehsBillWSBean delete(ScmInvOtherInWarehsBillWSBean bean);
	
	@POST
	@Path("/submit")
	public ScmInvOtherInWarehsBillWSBean submit(ScmInvOtherInWarehsBillWSBean bean);
	
	@POST
	@Path("/undoSubmit")
	public ScmInvOtherInWarehsBillWSBean undoSubmit(ScmInvOtherInWarehsBillWSBean bean);

	@POST
	@Path("/postBillCheck")
	public ScmInvOtherInWarehsBillWSBean postBillCheck(ScmInvOtherInWarehsBillWSBean bean);
	
	@POST
	@Path("/postBill")
	public ScmInvOtherInWarehsBillWSBean postBill(ScmInvOtherInWarehsBillWSBean bean);
	
	@POST
	@Path("/cancelPostBillCheck")
	public ScmInvOtherInWarehsBillWSBean cancelPostBillCheck(ScmInvOtherInWarehsBillWSBean bean);
	
	@POST
	@Path("/cancelPostBill")
	public ScmInvOtherInWarehsBillWSBean cancelPostBill(ScmInvOtherInWarehsBillWSBean bean);

	@POST
	@Path("/updatePrtCount")
	public ScmInvOtherInWarehsBillWSBean updatePrtCount(ScmInvOtherInWarehsBillWSBean bean);
}

