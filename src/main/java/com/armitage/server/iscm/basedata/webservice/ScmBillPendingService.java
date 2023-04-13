package com.armitage.server.iscm.basedata.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.iscm.basedata.model.ScmBillPendingWSBean;

public interface ScmBillPendingService {
	
	@POST
	@Path("/findPage")
	public ScmBillPendingWSBean findPage(ScmBillPendingWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmBillPendingWSBean queryPage(ScmBillPendingWSBean bean);

	@POST
	@Path("/select")
	public ScmBillPendingWSBean select(ScmBillPendingWSBean bean);

	@POST
	@Path("/add")
	public ScmBillPendingWSBean add(ScmBillPendingWSBean bean);

	@POST
	@Path("/update")
	public ScmBillPendingWSBean update(ScmBillPendingWSBean bean);
	

	@POST
	@Path("/delete")
	public ScmBillPendingWSBean delete(ScmBillPendingWSBean bean);
	
	@POST
	@Path("/checkExistPendingBill")
	public ScmBillPendingWSBean checkExistPendingBill(ScmBillPendingWSBean bean);
	
}
