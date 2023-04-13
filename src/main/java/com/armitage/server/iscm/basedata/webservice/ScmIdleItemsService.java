
package com.armitage.server.iscm.basedata.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import com.armitage.server.iscm.basedata.model.ScmIdleItemsWSBean;

public interface ScmIdleItemsService {
	
	@POST
	@Path("/findPage")
	public ScmIdleItemsWSBean findPage(ScmIdleItemsWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmIdleItemsWSBean queryPage(ScmIdleItemsWSBean bean);

	@POST
	@Path("/select")
	public ScmIdleItemsWSBean select(ScmIdleItemsWSBean bean);

	@POST
	@Path("/add")
	public ScmIdleItemsWSBean add(ScmIdleItemsWSBean bean);

	@POST
	@Path("/update")
	public ScmIdleItemsWSBean update(ScmIdleItemsWSBean bean);
	
	@POST
	@Path("/delete")
	public ScmIdleItemsWSBean delete(ScmIdleItemsWSBean bean);
	
	@POST
	@Path("/selectIdleItemsByItems")
	public ScmIdleItemsWSBean selectIdleItemsByItems(ScmIdleItemsWSBean bean);
	
	@POST
	@Path("/selectIdleDrillData")
	public ScmIdleItemsWSBean selectIdleDrillData(ScmIdleItemsWSBean bean);
}
