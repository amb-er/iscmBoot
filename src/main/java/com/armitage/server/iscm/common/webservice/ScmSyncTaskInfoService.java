package com.armitage.server.iscm.common.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.iscm.common.model.ScmSyncTaskInfoWSBean;

@Path("/taskCodeService/")
public interface ScmSyncTaskInfoService {
	
	@POST
	@Path("/findPage")
	public ScmSyncTaskInfoWSBean findPage(ScmSyncTaskInfoWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmSyncTaskInfoWSBean queryPage(ScmSyncTaskInfoWSBean bean);

	@POST
	@Path("/select")
	public ScmSyncTaskInfoWSBean select(ScmSyncTaskInfoWSBean bean);

	@POST
	@Path("/add")
	public ScmSyncTaskInfoWSBean add(ScmSyncTaskInfoWSBean bean);

	@POST
	@Path("/update")
	public ScmSyncTaskInfoWSBean update(ScmSyncTaskInfoWSBean bean);

	@POST
	@Path("/delete")
	public ScmSyncTaskInfoWSBean delete(ScmSyncTaskInfoWSBean bean);

	@POST
	@Path("/disableTask")
	public ScmSyncTaskInfoWSBean disableTask(ScmSyncTaskInfoWSBean bean);

	@POST
	@Path("/queryForBatchTask")
	public ScmSyncTaskInfoWSBean queryForBatchTask(ScmSyncTaskInfoWSBean bean);
	
	@POST
	@Path("/generateSyncData")
	public ScmSyncTaskInfoWSBean generateSyncData(ScmSyncTaskInfoWSBean bean);
}
