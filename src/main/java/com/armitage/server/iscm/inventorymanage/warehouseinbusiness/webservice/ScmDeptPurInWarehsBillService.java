package com.armitage.server.iscm.inventorymanage.warehouseinbusiness.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvPurInWarehsBillWSBean;

public interface ScmDeptPurInWarehsBillService {
	
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
	@Path("/delete")
	public ScmInvPurInWarehsBillWSBean delete(ScmInvPurInWarehsBillWSBean bean);

	@POST
	@Path("/updatePrtCount")
	public ScmInvPurInWarehsBillWSBean updatePrtCount(ScmInvPurInWarehsBillWSBean bean);
}
