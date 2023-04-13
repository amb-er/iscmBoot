package com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvMaterialRequestBillWSBean;

public interface ScmInvMaterialRequestBillService {
	
	@POST
	@Path("/findPage")
	public ScmInvMaterialRequestBillWSBean findPage(ScmInvMaterialRequestBillWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmInvMaterialRequestBillWSBean queryPage(ScmInvMaterialRequestBillWSBean bean);

	@POST
	@Path("/select")
	public ScmInvMaterialRequestBillWSBean select(ScmInvMaterialRequestBillWSBean bean);

	@POST
	@Path("/add")
	public ScmInvMaterialRequestBillWSBean add(ScmInvMaterialRequestBillWSBean bean);

	@POST
	@Path("/update")
	public ScmInvMaterialRequestBillWSBean update(ScmInvMaterialRequestBillWSBean bean);
	

	@POST
	@Path("/delete")
	public ScmInvMaterialRequestBillWSBean delete(ScmInvMaterialRequestBillWSBean bean);

	@POST
	@Path("/submit")
	public ScmInvMaterialRequestBillWSBean submit(ScmInvMaterialRequestBillWSBean bean);

	@POST
	@Path("/undoSubmit")
	public ScmInvMaterialRequestBillWSBean undoSubmit(ScmInvMaterialRequestBillWSBean bean);

	@POST
	@Path("/release")
	public ScmInvMaterialRequestBillWSBean release(ScmInvMaterialRequestBillWSBean bean);

	@POST
	@Path("/undoRelease")
	public ScmInvMaterialRequestBillWSBean undoRelease(ScmInvMaterialRequestBillWSBean bean);

	@POST
	@Path("/finish")
	public ScmInvMaterialRequestBillWSBean finish(ScmInvMaterialRequestBillWSBean bean);

	@POST
	@Path("/undoFinish")
	public ScmInvMaterialRequestBillWSBean undoFinish(ScmInvMaterialRequestBillWSBean bean);
	
	@POST
	@Path("/generateMaterialReqBill")
	public ScmInvMaterialRequestBillWSBean generateMaterialReqBill(ScmInvMaterialRequestBillWSBean bean);

	@POST
	@Path("/doAudit")
	public ScmInvMaterialRequestBillWSBean doAudit(ScmInvMaterialRequestBillWSBean bean);

	@POST
	@Path("/undoAudit")
	public ScmInvMaterialRequestBillWSBean undoAudit(ScmInvMaterialRequestBillWSBean bean);
	
	@POST
	@Path("/updatePrtCount")
	public ScmInvMaterialRequestBillWSBean updatePrtCount(ScmInvMaterialRequestBillWSBean bean);
	
	@POST
	@Path("/selectDrillBills")
	public ScmInvMaterialRequestBillWSBean selectDrillBills(ScmInvMaterialRequestBillWSBean bean);
}

