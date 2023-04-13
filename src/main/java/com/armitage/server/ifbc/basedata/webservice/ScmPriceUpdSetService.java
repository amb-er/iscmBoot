package com.armitage.server.ifbc.basedata.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.ifbc.basedata.model.ScmPriceUpdSetWSBean;


public interface ScmPriceUpdSetService {
	
	@POST
	@Path("/findPage")
	public ScmPriceUpdSetWSBean findPage(ScmPriceUpdSetWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmPriceUpdSetWSBean queryPage(ScmPriceUpdSetWSBean bean);

	@POST
	@Path("/select")
	public ScmPriceUpdSetWSBean select(ScmPriceUpdSetWSBean bean);

	@POST
	@Path("/add")
	public ScmPriceUpdSetWSBean add(ScmPriceUpdSetWSBean bean);

	@POST
	@Path("/update")
	public ScmPriceUpdSetWSBean update(ScmPriceUpdSetWSBean bean);
	

	@POST
	@Path("/delete")
	public ScmPriceUpdSetWSBean delete(ScmPriceUpdSetWSBean bean);
	
}
