package com.armitage.server.ifbm.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.ifbm.model.FbmStatOrDeptWSBean;

public interface FbmStatOrDeptService {
	
	@POST
	@Path("/findPage")
	public FbmStatOrDeptWSBean findPage(FbmStatOrDeptWSBean bean);

	@POST
	@Path("/queryPage")
	public FbmStatOrDeptWSBean queryPage(FbmStatOrDeptWSBean bean);

	@POST
	@Path("/select")
	public FbmStatOrDeptWSBean select(FbmStatOrDeptWSBean bean);

	@POST
	@Path("/add")
	public FbmStatOrDeptWSBean add(FbmStatOrDeptWSBean bean);

	@POST
	@Path("/update")
	public FbmStatOrDeptWSBean update(FbmStatOrDeptWSBean bean);
	

	@POST
	@Path("/delete")
	public FbmStatOrDeptWSBean delete(FbmStatOrDeptWSBean bean);
	
}
