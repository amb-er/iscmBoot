package com.armitage.server.ifbm.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.ifbm.model.FbmCookWSBean;

public interface FbmCookService {
	
	@POST
	@Path("/findPage")
	public FbmCookWSBean findPage(FbmCookWSBean bean);

	@POST
	@Path("/queryPage")
	public FbmCookWSBean queryPage(FbmCookWSBean bean);

	@POST
	@Path("/select")
	public FbmCookWSBean select(FbmCookWSBean bean);

	@POST
	@Path("/add")
	public FbmCookWSBean add(FbmCookWSBean bean);

	@POST
	@Path("/update")
	public FbmCookWSBean update(FbmCookWSBean bean);
	

	@POST
	@Path("/delete")
	public FbmCookWSBean delete(FbmCookWSBean bean);
	
	@POST
	@Path("/selectByDishId")
	public FbmCookWSBean selectByDishId(FbmCookWSBean bean);
	
}

