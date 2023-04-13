package com.armitage.server.iscm.basedata.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.iscm.basedata.model.ScmMeasureUnitGroupWSBean;

public interface ScmMeasureUnitGroupService {
	
	@POST
	@Path("/findPage")
	public ScmMeasureUnitGroupWSBean findPage(ScmMeasureUnitGroupWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmMeasureUnitGroupWSBean queryPage(ScmMeasureUnitGroupWSBean bean);

	@POST
	@Path("/select")
	public ScmMeasureUnitGroupWSBean select(ScmMeasureUnitGroupWSBean bean);

	@POST
	@Path("/add")
	public ScmMeasureUnitGroupWSBean add(ScmMeasureUnitGroupWSBean bean);

	@POST
	@Path("/update")
	public ScmMeasureUnitGroupWSBean update(ScmMeasureUnitGroupWSBean bean);
	

	@POST
	@Path("/delete")
	public ScmMeasureUnitGroupWSBean delete(ScmMeasureUnitGroupWSBean bean);
	
}

