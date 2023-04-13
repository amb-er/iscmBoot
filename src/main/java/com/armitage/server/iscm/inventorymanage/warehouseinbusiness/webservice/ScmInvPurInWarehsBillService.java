package com.armitage.server.iscm.inventorymanage.warehouseinbusiness.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvPurInWarehsBillWSBean;

public interface ScmInvPurInWarehsBillService {
	
	@POST
	@Path("/findPage")
	public ScmInvPurInWarehsBillWSBean findPage(ScmInvPurInWarehsBillWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmInvPurInWarehsBillWSBean queryPage(ScmInvPurInWarehsBillWSBean bean);

	@POST
	@Path("/select")
	public ScmInvPurInWarehsBillWSBean select(ScmInvPurInWarehsBillWSBean bean);

	@POST
	@Path("/add")
	public ScmInvPurInWarehsBillWSBean add(ScmInvPurInWarehsBillWSBean bean);

	@POST
	@Path("/update")
	public ScmInvPurInWarehsBillWSBean update(ScmInvPurInWarehsBillWSBean bean);
	
	@POST
	@Path("/updateDirect")
	public ScmInvPurInWarehsBillWSBean updateDirect(ScmInvPurInWarehsBillWSBean bean);

	@POST
	@Path("/delete")
	public ScmInvPurInWarehsBillWSBean delete(ScmInvPurInWarehsBillWSBean bean);
	
	@POST
	@Path("/undoSubmit")
	public ScmInvPurInWarehsBillWSBean undoSubmit(ScmInvPurInWarehsBillWSBean bean);

	@POST
	@Path("/submit")
	public ScmInvPurInWarehsBillWSBean submit(ScmInvPurInWarehsBillWSBean bean);

	@POST
	@Path("/postBillCheck")
	public ScmInvPurInWarehsBillWSBean postBillCheck(ScmInvPurInWarehsBillWSBean bean);
	
	@POST
	@Path("/postBill")
	public ScmInvPurInWarehsBillWSBean postBill(ScmInvPurInWarehsBillWSBean bean);
	
	@POST
	@Path("/cancelPostBillCheck")
	public ScmInvPurInWarehsBillWSBean cancelPostBillCheck(ScmInvPurInWarehsBillWSBean bean);
	
	@POST
	@Path("/cancelPostBill")
	public ScmInvPurInWarehsBillWSBean cancelPostBill(ScmInvPurInWarehsBillWSBean bean);
	
	@POST
    @Path("/generateApInvoice")
    public ScmInvPurInWarehsBillWSBean generateApInvoice(ScmInvPurInWarehsBillWSBean bean);

	@POST
	@Path("/doAudit")
	public ScmInvPurInWarehsBillWSBean doAudit(ScmInvPurInWarehsBillWSBean bean);

	@POST
	@Path("/undoAudit")
	public ScmInvPurInWarehsBillWSBean undoAudit(ScmInvPurInWarehsBillWSBean bean);
	
	@POST
	@Path("/getTotalPurInWarehsQty")
	public ScmInvPurInWarehsBillWSBean getTotalPurInWarehsQty(ScmInvPurInWarehsBillWSBean bean);
	
	@POST
	@Path("/asyngenerateApInvoice")
	public ScmInvPurInWarehsBillWSBean asyngenerateApInvoice(ScmInvPurInWarehsBillWSBean bean);
	
	@POST
	@Path("/getTotalPurInWarehsQtyByItems")
	public ScmInvPurInWarehsBillWSBean getTotalPurInWarehsQtyByItems(ScmInvPurInWarehsBillWSBean bean);
	
	@POST
	@Path("/selectDrillBills")
	public ScmInvPurInWarehsBillWSBean selectDrillBills(ScmInvPurInWarehsBillWSBean bean);

	@POST
	@Path("/updatePrtCount")
	public ScmInvPurInWarehsBillWSBean updatePrtCount(ScmInvPurInWarehsBillWSBean bean);
}
