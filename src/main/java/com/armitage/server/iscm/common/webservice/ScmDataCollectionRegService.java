package com.armitage.server.iscm.common.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.iscm.common.model.ScmDataCollectionRegWSBean;

@Path("/scmDataCollectionRegService/")
public interface ScmDataCollectionRegService {
	
	@POST
	@Path("/findPage")
	public ScmDataCollectionRegWSBean findPage(ScmDataCollectionRegWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmDataCollectionRegWSBean queryPage(ScmDataCollectionRegWSBean bean);

	@POST
	@Path("/select")
	public ScmDataCollectionRegWSBean select(ScmDataCollectionRegWSBean bean);

	@POST
	@Path("/add")
	public ScmDataCollectionRegWSBean add(ScmDataCollectionRegWSBean bean);

	@POST
	@Path("/update")
	public ScmDataCollectionRegWSBean update(ScmDataCollectionRegWSBean bean);
	

	@POST
	@Path("/delete")
	public ScmDataCollectionRegWSBean delete(ScmDataCollectionRegWSBean bean);

	@POST
	@Path("/findAsynStep")
	public ScmDataCollectionRegWSBean findAsynStep(ScmDataCollectionRegWSBean bean);
	
	@POST
	@Path("/selectByStepIdAndOrgUnitNo")
	public ScmDataCollectionRegWSBean selectByStepIdAndOrgUnitNo(ScmDataCollectionRegWSBean bean);
}
