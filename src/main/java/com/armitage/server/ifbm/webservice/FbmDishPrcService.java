package com.armitage.server.ifbm.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.ifbm.model.FbmDishPrcWSBean;

public interface FbmDishPrcService {
	
	@POST
	@Path("/findPage")
	public FbmDishPrcWSBean findPage(FbmDishPrcWSBean bean);

	@POST
	@Path("/queryPage")
	public FbmDishPrcWSBean queryPage(FbmDishPrcWSBean bean);

	@POST
	@Path("/select")
	public FbmDishPrcWSBean select(FbmDishPrcWSBean bean);

	@POST
	@Path("/add")
	public FbmDishPrcWSBean add(FbmDishPrcWSBean bean);

	@POST
	@Path("/update")
	public FbmDishPrcWSBean update(FbmDishPrcWSBean bean);
	

	@POST
	@Path("/delete")
	public FbmDishPrcWSBean delete(FbmDishPrcWSBean bean);
	
}
