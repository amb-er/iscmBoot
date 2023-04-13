package com.armitage.server.ifbm.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.ifbm.model.FbmCookTypeWSBean;

public interface FbmCookTypeService {
	
	@POST
	@Path("/findPage")
	public FbmCookTypeWSBean findPage(FbmCookTypeWSBean bean);

	@POST
	@Path("/queryPage")
	public FbmCookTypeWSBean queryPage(FbmCookTypeWSBean bean);

	@POST
	@Path("/select")
	public FbmCookTypeWSBean select(FbmCookTypeWSBean bean);

	@POST
	@Path("/add")
	public FbmCookTypeWSBean add(FbmCookTypeWSBean bean);

	@POST
	@Path("/update")
	public FbmCookTypeWSBean update(FbmCookTypeWSBean bean);
	

	@POST
	@Path("/delete")
	public FbmCookTypeWSBean delete(FbmCookTypeWSBean bean);
	
}