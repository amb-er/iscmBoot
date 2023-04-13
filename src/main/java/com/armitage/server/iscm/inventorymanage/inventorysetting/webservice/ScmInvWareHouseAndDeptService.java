package com.armitage.server.iscm.inventorymanage.inventorysetting.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvWareHouseAndDeptWSBean;

public interface ScmInvWareHouseAndDeptService {
	
	@POST
	@Path("/findPage")
	public ScmInvWareHouseAndDeptWSBean findPage(ScmInvWareHouseAndDeptWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmInvWareHouseAndDeptWSBean queryPage(ScmInvWareHouseAndDeptWSBean bean);

	@POST
	@Path("/select")
	public ScmInvWareHouseAndDeptWSBean select(ScmInvWareHouseAndDeptWSBean bean);

	@POST
	@Path("/add")
	public ScmInvWareHouseAndDeptWSBean add(ScmInvWareHouseAndDeptWSBean bean);

	@POST
	@Path("/update")
	public ScmInvWareHouseAndDeptWSBean update(ScmInvWareHouseAndDeptWSBean bean);
	

	@POST
	@Path("/delete")
	public ScmInvWareHouseAndDeptWSBean delete(ScmInvWareHouseAndDeptWSBean bean);
}
