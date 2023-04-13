package com.armitage.server.iscm.inventorymanage.inventoryinitialization.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.iscm.inventorymanage.inventoryinitialization.model.ScmInvInitBillWSBean;

public interface ScmInvInitBillService {
	
	@POST
	@Path("/findPage")
	public ScmInvInitBillWSBean findPage(ScmInvInitBillWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmInvInitBillWSBean queryPage(ScmInvInitBillWSBean bean);

	@POST
	@Path("/select")
	public ScmInvInitBillWSBean select(ScmInvInitBillWSBean bean);

	@POST
	@Path("/add")
	public ScmInvInitBillWSBean add(ScmInvInitBillWSBean bean);

	@POST
	@Path("/update")
	public ScmInvInitBillWSBean update(ScmInvInitBillWSBean bean);
	
	@POST
	@Path("/updateDirect")
	public ScmInvInitBillWSBean updateDirect(ScmInvInitBillWSBean bean);

	@POST
	@Path("/delete")
	public ScmInvInitBillWSBean delete(ScmInvInitBillWSBean bean);
	
	@POST
	@Path("/submit")
	public ScmInvInitBillWSBean submit(ScmInvInitBillWSBean bean);
	
	@POST
	@Path("/undoSubmit")
	public ScmInvInitBillWSBean undoSubmit(ScmInvInitBillWSBean bean);
	
	@POST
	@Path("/postBillCheck")
	public ScmInvInitBillWSBean postBillCheck(ScmInvInitBillWSBean bean);
	
	@POST
	@Path("/postBill")
	public ScmInvInitBillWSBean postBill(ScmInvInitBillWSBean bean);
	
	@POST
	@Path("/cancelPostBillCheck")
	public ScmInvInitBillWSBean cancelPostBillCheck(ScmInvInitBillWSBean bean);
	
	@POST
	@Path("/cancelPostBill")
	public ScmInvInitBillWSBean cancelPostBill(ScmInvInitBillWSBean bean);
		
	@POST
	@Path("/importExcel")
	public ScmInvInitBillWSBean importExcel(ScmInvInitBillWSBean bean);

	@POST
	@Path("/updatePrtCount")
	public ScmInvInitBillWSBean updatePrtCount(ScmInvInitBillWSBean bean);
}
