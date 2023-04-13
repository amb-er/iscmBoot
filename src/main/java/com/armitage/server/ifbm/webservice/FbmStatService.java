package com.armitage.server.ifbm.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.ifbm.model.FbmStatWSBean;

public interface FbmStatService {
	
	@POST
	@Path("/findPage")
	public FbmStatWSBean findPage(FbmStatWSBean bean);

	@POST
	@Path("/queryPage")
	public FbmStatWSBean queryPage(FbmStatWSBean bean);

	@POST
	@Path("/select")
	public FbmStatWSBean select(FbmStatWSBean bean);

	@POST
	@Path("/add")
	public FbmStatWSBean add(FbmStatWSBean bean);

	@POST
	@Path("/update")
	public FbmStatWSBean update(FbmStatWSBean bean);
	

	@POST
	@Path("/delete")
	public FbmStatWSBean delete(FbmStatWSBean bean);
	
}
