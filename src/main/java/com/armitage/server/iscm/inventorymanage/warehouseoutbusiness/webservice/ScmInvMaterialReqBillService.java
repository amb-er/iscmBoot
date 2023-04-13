package com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.webservice;


import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvMaterialReqBillWSBean;

public interface ScmInvMaterialReqBillService {
	
	@POST
	@Path("/findPage")
	public ScmInvMaterialReqBillWSBean findPage(ScmInvMaterialReqBillWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmInvMaterialReqBillWSBean queryPage(ScmInvMaterialReqBillWSBean bean);

	@POST
	@Path("/select")
	public ScmInvMaterialReqBillWSBean select(ScmInvMaterialReqBillWSBean bean);

	@POST
	@Path("/add")
	public ScmInvMaterialReqBillWSBean add(ScmInvMaterialReqBillWSBean bean);

	@POST
	@Path("/update")
	public ScmInvMaterialReqBillWSBean update(ScmInvMaterialReqBillWSBean bean);
	
	@POST
	@Path("/updateDirect")
	public ScmInvMaterialReqBillWSBean updateDirect(ScmInvMaterialReqBillWSBean bean);

	@POST
	@Path("/delete")
	public ScmInvMaterialReqBillWSBean delete(ScmInvMaterialReqBillWSBean bean);
	
	@POST
	@Path("/submit")
	public ScmInvMaterialReqBillWSBean submit(ScmInvMaterialReqBillWSBean bean);
	
	@POST
	@Path("/undoSubmit")
	public ScmInvMaterialReqBillWSBean undoSubmit(ScmInvMaterialReqBillWSBean bean);

	@POST
	@Path("/postBillCheck")
	public ScmInvMaterialReqBillWSBean postBillCheck(ScmInvMaterialReqBillWSBean bean);
	
	@POST
	@Path("/postBill")
	public ScmInvMaterialReqBillWSBean postBill(ScmInvMaterialReqBillWSBean bean);
	
	@POST
	@Path("/cancelPostBill")
	public ScmInvMaterialReqBillWSBean cancelPostBill(ScmInvMaterialReqBillWSBean bean);
	
	@POST
	@Path("/cancelPostBillCheck")
	public ScmInvMaterialReqBillWSBean cancelPostBillCheck(ScmInvMaterialReqBillWSBean bean);

	@POST
	@Path("/doAudit")
	public ScmInvMaterialReqBillWSBean doAudit(ScmInvMaterialReqBillWSBean bean);

	@POST
	@Path("/undoAudit")
	public ScmInvMaterialReqBillWSBean undoAudit(ScmInvMaterialReqBillWSBean bean);
	
	@POST
	@Path("/selectDrillBills")
	public ScmInvMaterialReqBillWSBean selectDrillBills(ScmInvMaterialReqBillWSBean bean);

	@POST
	@Path("/updatePrtCount")
	public ScmInvMaterialReqBillWSBean updatePrtCount(ScmInvMaterialReqBillWSBean bean);
}

