package com.armitage.server.iscm.basedata.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import com.armitage.server.iscm.basedata.model.PubSysBasicInfoWSBean;

public interface PubSysBasicInfoService {
	
	@POST
	@Path("/findPage")
	public PubSysBasicInfoWSBean findPage(PubSysBasicInfoWSBean bean);

	@POST
	@Path("/queryPage")
	public PubSysBasicInfoWSBean queryPage(PubSysBasicInfoWSBean bean);

	@POST
	@Path("/select")
	public PubSysBasicInfoWSBean select(PubSysBasicInfoWSBean bean);

	@POST
	@Path("/add")
	public PubSysBasicInfoWSBean add(PubSysBasicInfoWSBean bean);

	@POST
	@Path("/update")
	public PubSysBasicInfoWSBean update(PubSysBasicInfoWSBean bean);
	

	@POST
	@Path("/delete")
	public PubSysBasicInfoWSBean delete(PubSysBasicInfoWSBean bean);
	
	@POST
	@Path("/checkTaxRateExist")
	public PubSysBasicInfoWSBean checkTaxRateExist(PubSysBasicInfoWSBean bean);
	
}
