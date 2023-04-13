package com.armitage.server.iscm.basedata.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.iscm.basedata.model.ScmsuppliergroupstandardWSBean;

public interface ScmsuppliergroupstandardService {
	
	@POST
	@Path("/findPage")
	public ScmsuppliergroupstandardWSBean findPage(ScmsuppliergroupstandardWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmsuppliergroupstandardWSBean queryPage(ScmsuppliergroupstandardWSBean bean);

	@POST
	@Path("/select")
	public ScmsuppliergroupstandardWSBean select(ScmsuppliergroupstandardWSBean bean);

	@POST
	@Path("/add")
	public ScmsuppliergroupstandardWSBean add(ScmsuppliergroupstandardWSBean bean);

	@POST
	@Path("/update")
	public ScmsuppliergroupstandardWSBean update(ScmsuppliergroupstandardWSBean bean);
	

	@POST
	@Path("/delete")
	public ScmsuppliergroupstandardWSBean delete(ScmsuppliergroupstandardWSBean bean);
	
}
