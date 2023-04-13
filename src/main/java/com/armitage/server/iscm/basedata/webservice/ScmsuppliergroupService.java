package com.armitage.server.iscm.basedata.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.iscm.basedata.model.ScmsuppliergroupWSBean;

public interface ScmsuppliergroupService {
	
	@POST
	@Path("/findPage")
	public ScmsuppliergroupWSBean findPage(ScmsuppliergroupWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmsuppliergroupWSBean queryPage(ScmsuppliergroupWSBean bean);

	@POST
	@Path("/select")
	public ScmsuppliergroupWSBean select(ScmsuppliergroupWSBean bean);

	@POST
	@Path("/add")
	public ScmsuppliergroupWSBean add(ScmsuppliergroupWSBean bean);

	@POST
	@Path("/update")
	public ScmsuppliergroupWSBean update(ScmsuppliergroupWSBean bean);
	

	@POST
	@Path("/delete")
	public ScmsuppliergroupWSBean delete(ScmsuppliergroupWSBean bean);
	
}
