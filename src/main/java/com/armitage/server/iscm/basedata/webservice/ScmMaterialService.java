
package com.armitage.server.iscm.basedata.webservice;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.iscm.basedata.model.ScmMaterialWSBean;

public interface ScmMaterialService {
	@POST
	@Path("/findPage")
	public ScmMaterialWSBean findPage(ScmMaterialWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmMaterialWSBean queryPage(ScmMaterialWSBean bean);

	@POST
	@Path("/select")
	public ScmMaterialWSBean select(ScmMaterialWSBean bean);

	@POST
	@Path("/add")
	public ScmMaterialWSBean add(ScmMaterialWSBean bean);

	@POST
	@Path("/update")
	public ScmMaterialWSBean update(ScmMaterialWSBean bean);
	

	@POST
	@Path("/delete")
	public ScmMaterialWSBean delete(ScmMaterialWSBean bean);
	
	@POST
	@Path("/selectItemUnit")
	public ScmMaterialWSBean selectItemUnit(ScmMaterialWSBean bean);
	
	@POST
	@Path("/updateStatus")
	public ScmMaterialWSBean updateStatus(ScmMaterialWSBean bean);
	
	@POST
	@Path("/findCountingMaterial")
	public ScmMaterialWSBean findCountingMaterial(ScmMaterialWSBean bean);
	
	@POST
	@Path("/checkUse")
	public ScmMaterialWSBean checkUse(ScmMaterialWSBean bean);
	
	@POST
	@Path("/checkAllUse")
	public ScmMaterialWSBean checkAllUse(ScmMaterialWSBean bean);
	
	@POST
	@Path("/selectByItemNo")
	public ScmMaterialWSBean selectByItemNo(ScmMaterialWSBean bean);
	
	@POST
	@Path("/findSameNameMaterial")
	public ScmMaterialWSBean findSameNameMaterial(ScmMaterialWSBean bean);
	
	@POST
	@Path("/getConvrate")
	public ScmMaterialWSBean getConvrate(ScmMaterialWSBean bean);

	@POST
	@Path("/approval")
	public ScmMaterialWSBean approval(ScmMaterialWSBean bean);

	@POST
	@Path("/findByFinItemId")
	public ScmMaterialWSBean findByFinItemId(ScmMaterialWSBean bean);
	
	@POST
	@Path("/importScmMaterial")
	public ScmMaterialWSBean importScmMaterial(ScmMaterialWSBean bean);
	
	@POST
	@Path("/batchRatioSet")
	public ScmMaterialWSBean batchRatioSet(ScmMaterialWSBean bean);

	@POST
	@Path("/disable")
	public ScmMaterialWSBean disable(ScmMaterialWSBean bean);

	@POST
	@Path("/enable")
	public ScmMaterialWSBean enable(ScmMaterialWSBean bean);
}
