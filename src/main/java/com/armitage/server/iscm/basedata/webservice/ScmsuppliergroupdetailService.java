package com.armitage.server.iscm.basedata.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.iscm.basedata.model.ScmsuppliergroupdetailWSBean;

public interface ScmsuppliergroupdetailService {
	
	@POST
	@Path("/findPage")
	public ScmsuppliergroupdetailWSBean findPage(ScmsuppliergroupdetailWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmsuppliergroupdetailWSBean queryPage(ScmsuppliergroupdetailWSBean bean);

	@POST
	@Path("/select")
	public ScmsuppliergroupdetailWSBean select(ScmsuppliergroupdetailWSBean bean);

	@POST
	@Path("/add")
	public ScmsuppliergroupdetailWSBean add(ScmsuppliergroupdetailWSBean bean);

	@POST
	@Path("/update")
	public ScmsuppliergroupdetailWSBean update(ScmsuppliergroupdetailWSBean bean);
	

	@POST
	@Path("/delete")
	public ScmsuppliergroupdetailWSBean delete(ScmsuppliergroupdetailWSBean bean);
	
}

