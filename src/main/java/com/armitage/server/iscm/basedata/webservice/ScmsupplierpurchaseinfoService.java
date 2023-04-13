package com.armitage.server.iscm.basedata.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.iscm.basedata.model.ScmsupplierpurchaseinfoWSBean;

public interface ScmsupplierpurchaseinfoService {
	
	@POST
	@Path("/findPage")
	public ScmsupplierpurchaseinfoWSBean findPage(ScmsupplierpurchaseinfoWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmsupplierpurchaseinfoWSBean queryPage(ScmsupplierpurchaseinfoWSBean bean);

	@POST
	@Path("/select")
	public ScmsupplierpurchaseinfoWSBean select(ScmsupplierpurchaseinfoWSBean bean);

	@POST
	@Path("/add")
	public ScmsupplierpurchaseinfoWSBean add(ScmsupplierpurchaseinfoWSBean bean);

	@POST
	@Path("/update")
	public ScmsupplierpurchaseinfoWSBean update(ScmsupplierpurchaseinfoWSBean bean);
	

	@POST
	@Path("/delete")
	public ScmsupplierpurchaseinfoWSBean delete(ScmsupplierpurchaseinfoWSBean bean);
	
}

