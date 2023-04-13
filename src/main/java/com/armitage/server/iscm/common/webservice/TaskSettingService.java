package com.armitage.server.iscm.common.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.iscm.common.model.TaskSettingWSBean;

@Path("/taskSettingService/")
public interface TaskSettingService {
	
	@POST
	@Path("/findPage")
	public TaskSettingWSBean findPage(TaskSettingWSBean bean);

	@POST
	@Path("/queryPage")
	public TaskSettingWSBean queryPage(TaskSettingWSBean bean);

	@POST
	@Path("/select")
	public TaskSettingWSBean select(TaskSettingWSBean bean);

	@POST
	@Path("/add")
	public TaskSettingWSBean add(TaskSettingWSBean bean);

	@POST
	@Path("/update")
	public TaskSettingWSBean update(TaskSettingWSBean bean);

	@POST
	@Path("/delete")
	public TaskSettingWSBean delete(TaskSettingWSBean bean);

}
