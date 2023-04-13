
package com.armitage.server.iscm.basedata.webservice;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.iscm.basedata.model.ScmMaterialInventoryWSBean;

public interface ScmMaterialInventoryService {
	@POST
	@Path("/findPage")
	public ScmMaterialInventoryWSBean findPage(ScmMaterialInventoryWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmMaterialInventoryWSBean queryPage(ScmMaterialInventoryWSBean bean);

	@POST
	@Path("/select")
	public ScmMaterialInventoryWSBean select(ScmMaterialInventoryWSBean bean);

	@POST
	@Path("/add")
	public ScmMaterialInventoryWSBean add(ScmMaterialInventoryWSBean bean);

	@POST
	@Path("/update")
	public ScmMaterialInventoryWSBean update(ScmMaterialInventoryWSBean bean);
	

	@POST
	@Path("/delete")
	public ScmMaterialInventoryWSBean delete(ScmMaterialInventoryWSBean bean);
}
