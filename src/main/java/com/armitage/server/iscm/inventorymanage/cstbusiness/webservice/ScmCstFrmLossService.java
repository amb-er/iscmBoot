package com.armitage.server.iscm.inventorymanage.cstbusiness.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmCstFrmLossWSBean;

public interface ScmCstFrmLossService {
    
    @POST
    @Path("/findPage")
    public ScmCstFrmLossWSBean findPage(ScmCstFrmLossWSBean bean);

    @POST
    @Path("/queryPage")
    public ScmCstFrmLossWSBean queryPage(ScmCstFrmLossWSBean bean);

    @POST
    @Path("/select")
    public ScmCstFrmLossWSBean select(ScmCstFrmLossWSBean bean);

    @POST
    @Path("/add")
    public ScmCstFrmLossWSBean add(ScmCstFrmLossWSBean bean);

    @POST
    @Path("/update")
    public ScmCstFrmLossWSBean update(ScmCstFrmLossWSBean bean);
    
    @POST
    @Path("/updateDirect")
    public ScmCstFrmLossWSBean updateDirect(ScmCstFrmLossWSBean bean);

    @POST
    @Path("/delete")
    public ScmCstFrmLossWSBean delete(ScmCstFrmLossWSBean bean);
    
    @POST
    @Path("/submit")
    public ScmCstFrmLossWSBean submit(ScmCstFrmLossWSBean bean);
    
    @POST
    @Path("/undoSubmit")
    public ScmCstFrmLossWSBean undoSubmit(ScmCstFrmLossWSBean bean);
    
    @POST
	@Path("/postBillCheck")
	public ScmCstFrmLossWSBean postBillCheck(ScmCstFrmLossWSBean bean);
	
	@POST
	@Path("/postBill")
	public ScmCstFrmLossWSBean postBill(ScmCstFrmLossWSBean bean);
	
	@POST
	@Path("/cancelPostBillCheck")
	public ScmCstFrmLossWSBean cancelPostBillCheck(ScmCstFrmLossWSBean bean);
	
	@POST
	@Path("/cancelPostBill")
	public ScmCstFrmLossWSBean cancelPostBill(ScmCstFrmLossWSBean bean);

	@POST
	@Path("/doAudit")
	public ScmCstFrmLossWSBean doAudit(ScmCstFrmLossWSBean bean);

	@POST
	@Path("/undoAudit")
	public ScmCstFrmLossWSBean undoAudit(ScmCstFrmLossWSBean bean);

	@POST
	@Path("/updatePrtCount")
	public ScmCstFrmLossWSBean updatePrtCount(ScmCstFrmLossWSBean bean);
}
