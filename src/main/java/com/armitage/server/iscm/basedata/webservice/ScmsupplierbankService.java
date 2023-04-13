package com.armitage.server.iscm.basedata.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.iscm.basedata.model.ScmsupplierbankWSBean;

public interface ScmsupplierbankService {
	
	@POST
	@Path("/findPage")
	public ScmsupplierbankWSBean findPage(ScmsupplierbankWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmsupplierbankWSBean queryPage(ScmsupplierbankWSBean bean);

	@POST
	@Path("/select")
	public ScmsupplierbankWSBean select(ScmsupplierbankWSBean bean);

	@POST
	@Path("/add")
	public ScmsupplierbankWSBean add(ScmsupplierbankWSBean bean);

	@POST
	@Path("/update")
	public ScmsupplierbankWSBean update(ScmsupplierbankWSBean bean);
	

	@POST
	@Path("/delete")
	public ScmsupplierbankWSBean delete(ScmsupplierbankWSBean bean);
	
}
