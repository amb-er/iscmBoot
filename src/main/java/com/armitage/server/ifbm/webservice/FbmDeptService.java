package com.armitage.server.ifbm.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.ifbm.model.FbmDeptWSBean;

public interface FbmDeptService {
	
	@POST
	@Path("/findPage")
	public FbmDeptWSBean findPage(FbmDeptWSBean bean);

	@POST
	@Path("/queryPage")
	public FbmDeptWSBean queryPage(FbmDeptWSBean bean);

	@POST
	@Path("/select")
	public FbmDeptWSBean select(FbmDeptWSBean bean);

	@POST
	@Path("/add")
	public FbmDeptWSBean add(FbmDeptWSBean bean);

	@POST
	@Path("/update")
	public FbmDeptWSBean update(FbmDeptWSBean bean);
	

	@POST
	@Path("/delete")
	public FbmDeptWSBean delete(FbmDeptWSBean bean);
	
}
