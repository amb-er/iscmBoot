
package com.armitage.server.iscm.basedata.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.iscm.basedata.model.ScmMeasureUnitWSBean;

public interface ScmMeasureUnitService {
	
	@POST
	@Path("/findPage")
	public ScmMeasureUnitWSBean findPage(ScmMeasureUnitWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmMeasureUnitWSBean queryPage(ScmMeasureUnitWSBean bean);

	@POST
	@Path("/select")
	public ScmMeasureUnitWSBean select(ScmMeasureUnitWSBean bean);

	@POST
	@Path("/add")
	public ScmMeasureUnitWSBean add(ScmMeasureUnitWSBean bean);

	@POST
	@Path("/update")
	public ScmMeasureUnitWSBean update(ScmMeasureUnitWSBean bean);
	

	@POST
	@Path("/delete")
	public ScmMeasureUnitWSBean delete(ScmMeasureUnitWSBean bean);
	
	@POST
	@Path("/updateStatus")
	public ScmMeasureUnitWSBean updateStatus(ScmMeasureUnitWSBean bean);
	
	@POST
	@Path("/checkUnitUse")
	public ScmMeasureUnitWSBean checkUnitUse(ScmMeasureUnitWSBean bean);
	
}
