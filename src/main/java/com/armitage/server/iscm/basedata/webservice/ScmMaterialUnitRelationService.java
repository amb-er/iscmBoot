package com.armitage.server.iscm.basedata.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.iscm.basedata.model.ScmMaterialUnitRelationWSBean;

public interface ScmMaterialUnitRelationService {
	
	@POST
	@Path("/findPage")
	public ScmMaterialUnitRelationWSBean findPage(ScmMaterialUnitRelationWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmMaterialUnitRelationWSBean queryPage(ScmMaterialUnitRelationWSBean bean);

	@POST
	@Path("/select")
	public ScmMaterialUnitRelationWSBean select(ScmMaterialUnitRelationWSBean bean);

	@POST
	@Path("/add")
	public ScmMaterialUnitRelationWSBean add(ScmMaterialUnitRelationWSBean bean);

	@POST
	@Path("/update")
	public ScmMaterialUnitRelationWSBean update(ScmMaterialUnitRelationWSBean bean);
	
	@POST
	@Path("/delete")
	public ScmMaterialUnitRelationWSBean delete(ScmMaterialUnitRelationWSBean bean);
	
	@POST
	@Path("/selectUnitRelation")
	public ScmMaterialUnitRelationWSBean selectUnitRelation(ScmMaterialUnitRelationWSBean bean);
	
	@POST
	@Path("/saveUnitRelation")
	public ScmMaterialUnitRelationWSBean saveUnitRelation(ScmMaterialUnitRelationWSBean bean);
}

