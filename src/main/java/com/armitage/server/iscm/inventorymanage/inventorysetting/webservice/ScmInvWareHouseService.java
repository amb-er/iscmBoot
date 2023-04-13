package com.armitage.server.iscm.inventorymanage.inventorysetting.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvWareHouseWSBean;

public interface ScmInvWareHouseService {
	
	@POST
	@Path("/findPage")
	public ScmInvWareHouseWSBean findPage(ScmInvWareHouseWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmInvWareHouseWSBean queryPage(ScmInvWareHouseWSBean bean);

	@POST
	@Path("/select")
	public ScmInvWareHouseWSBean select(ScmInvWareHouseWSBean bean);

	@POST
	@Path("/add")
	public ScmInvWareHouseWSBean add(ScmInvWareHouseWSBean bean);

	@POST
	@Path("/update")
	public ScmInvWareHouseWSBean update(ScmInvWareHouseWSBean bean);
	

	@POST
	@Path("/delete")
	public ScmInvWareHouseWSBean delete(ScmInvWareHouseWSBean bean);
	
	@POST
	@Path("/enableWareHouse")
	public ScmInvWareHouseWSBean enableWareHouse(ScmInvWareHouseWSBean bean);
	
	@POST
	@Path("/selectWareHouseSet")
	public ScmInvWareHouseWSBean selectWareHouseSet(ScmInvWareHouseWSBean bean);
	
	@POST
	@Path("/selectByOrgUnitNo")
	public ScmInvWareHouseWSBean selectByOrgUnitNo(ScmInvWareHouseWSBean bean);
	
	@POST
	@Path("/selectByWhName")
	public ScmInvWareHouseWSBean selectByWhName(ScmInvWareHouseWSBean bean);
	
	@POST
	@Path("/selectWareHouseUsr")
	public ScmInvWareHouseWSBean selectWareHouseUsr(ScmInvWareHouseWSBean bean);
	
	@POST
	@Path("/saveWareHouseUsr")
	public ScmInvWareHouseWSBean saveWareHouseUsr(ScmInvWareHouseWSBean bean);
}
