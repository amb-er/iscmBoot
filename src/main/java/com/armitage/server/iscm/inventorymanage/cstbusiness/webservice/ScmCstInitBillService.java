package com.armitage.server.iscm.inventorymanage.cstbusiness.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmCstInitBillWSBean;

public interface ScmCstInitBillService {
    
    @POST
    @Path("/findPage")
    public ScmCstInitBillWSBean findPage(ScmCstInitBillWSBean bean);

    @POST
    @Path("/queryPage")
    public ScmCstInitBillWSBean queryPage(ScmCstInitBillWSBean bean);

    @POST
    @Path("/select")
    public ScmCstInitBillWSBean select(ScmCstInitBillWSBean bean);

    @POST
    @Path("/add")
    public ScmCstInitBillWSBean add(ScmCstInitBillWSBean bean);

    @POST
    @Path("/update")
    public ScmCstInitBillWSBean update(ScmCstInitBillWSBean bean);
    
    @POST
    @Path("/updateDirect")
    public ScmCstInitBillWSBean updateDirect(ScmCstInitBillWSBean bean);

    @POST
    @Path("/delete")
    public ScmCstInitBillWSBean delete(ScmCstInitBillWSBean bean);
    
    @POST
    @Path("/submit")
    public ScmCstInitBillWSBean submit(ScmCstInitBillWSBean bean);
    
    @POST
    @Path("/undoSubmit")
    public ScmCstInitBillWSBean undoSubmit(ScmCstInitBillWSBean bean);
    
    @POST
	@Path("/postBillCheck")
	public ScmCstInitBillWSBean postBillCheck(ScmCstInitBillWSBean bean);
	
	@POST
	@Path("/postBill")
	public ScmCstInitBillWSBean postBill(ScmCstInitBillWSBean bean);
	
	@POST
	@Path("/cancelPostBillCheck")
	public ScmCstInitBillWSBean cancelPostBillCheck(ScmCstInitBillWSBean bean);
	
	@POST
	@Path("/cancelPostBill")
	public ScmCstInitBillWSBean cancelPostBill(ScmCstInitBillWSBean bean);

	@POST
	@Path("/importExcel")
	public ScmCstInitBillWSBean importExcel(ScmCstInitBillWSBean bean);

	@POST
	@Path("/updatePrtCount")
	public ScmCstInitBillWSBean updatePrtCount(ScmCstInitBillWSBean bean);
}
