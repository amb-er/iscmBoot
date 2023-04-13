package com.armitage.server.ifbm.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.ifbm.model.FbmItemWSBean;

public interface FbmItemService {
	
	@POST
	@Path("/findPage")
	public FbmItemWSBean findPage(FbmItemWSBean bean);

	@POST
	@Path("/queryPage")
	public FbmItemWSBean queryPage(FbmItemWSBean bean);

	@POST
	@Path("/select")
	public FbmItemWSBean select(FbmItemWSBean bean);

	@POST
	@Path("/add")
	public FbmItemWSBean add(FbmItemWSBean bean);

	@POST
	@Path("/update")
	public FbmItemWSBean update(FbmItemWSBean bean);
	

	@POST
	@Path("/delete")
	public FbmItemWSBean delete(FbmItemWSBean bean);
	
}
