package com.armitage.server.iscm.common.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.iscm.common.model.TaskCodeWSBean;

@Path("/taskCodeService/")
public interface TaskCodeService {
	
	@POST
	@Path("/findPage")
	public TaskCodeWSBean findPage(TaskCodeWSBean bean);

	@POST
	@Path("/queryPage")
	public TaskCodeWSBean queryPage(TaskCodeWSBean bean);

	@POST
	@Path("/select")
	public TaskCodeWSBean select(TaskCodeWSBean bean);

	@POST
	@Path("/add")
	public TaskCodeWSBean add(TaskCodeWSBean bean);

	@POST
	@Path("/update")
	public TaskCodeWSBean update(TaskCodeWSBean bean);

	@POST
	@Path("/delete")
	public TaskCodeWSBean delete(TaskCodeWSBean bean);
}
