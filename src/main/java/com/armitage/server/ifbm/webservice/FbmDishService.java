package com.armitage.server.ifbm.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.ifbm.model.FbmDishWSBean;

public interface FbmDishService {
	
	@POST
	@Path("/findPage")
	public FbmDishWSBean findPage(FbmDishWSBean bean);

	@POST
	@Path("/queryPage")
	public FbmDishWSBean queryPage(FbmDishWSBean bean);

	@POST
	@Path("/select")
	public FbmDishWSBean select(FbmDishWSBean bean);

	@POST
	@Path("/add")
	public FbmDishWSBean add(FbmDishWSBean bean);

	@POST
	@Path("/update")
	public FbmDishWSBean update(FbmDishWSBean bean);
	

	@POST
	@Path("/delete")
	public FbmDishWSBean delete(FbmDishWSBean bean);
	
}
