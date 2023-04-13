package com.armitage.server.quartz.webservice;


import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.quartz.model.ScmSyncTaskLogWSBean;

public interface ScmSyncTaskLogService {
	
	@POST
	@Path("/findPage")
	public ScmSyncTaskLogWSBean findPage(ScmSyncTaskLogWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmSyncTaskLogWSBean queryPage(ScmSyncTaskLogWSBean bean);

	@POST
	@Path("/select")
	public ScmSyncTaskLogWSBean select(ScmSyncTaskLogWSBean bean);

	@POST
	@Path("/add")
	public ScmSyncTaskLogWSBean add(ScmSyncTaskLogWSBean bean);

	@POST
	@Path("/update")
	public ScmSyncTaskLogWSBean update(ScmSyncTaskLogWSBean bean);
	

	@POST
	@Path("/delete")
	public ScmSyncTaskLogWSBean delete(ScmSyncTaskLogWSBean bean);
	
	@POST
	@Path("/manualUpdate")
	public ScmSyncTaskLogWSBean manualUpdate(ScmSyncTaskLogWSBean bean);
	
}

