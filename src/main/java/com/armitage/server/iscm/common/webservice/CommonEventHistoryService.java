package com.armitage.server.iscm.common.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.iscm.common.model.CommonEventHistoryWSBean;

@Path("/commonEventHistory/")
public interface CommonEventHistoryService {
	
	@POST
	@Path("/findPage")
	public CommonEventHistoryWSBean findPage(CommonEventHistoryWSBean bean);

	@POST
	@Path("/queryPage")
	public CommonEventHistoryWSBean queryPage(CommonEventHistoryWSBean bean);

	@POST
	@Path("/select")
	public CommonEventHistoryWSBean select(CommonEventHistoryWSBean bean);

	@POST
	@Path("/add")
	public CommonEventHistoryWSBean add(CommonEventHistoryWSBean bean);

	@POST
	@Path("/update")
	public CommonEventHistoryWSBean update(CommonEventHistoryWSBean bean);
	

	@POST
	@Path("/delete")
	public CommonEventHistoryWSBean delete(CommonEventHistoryWSBean bean);
	
	@POST
	@Path("/loadAuditStatus")
	public CommonEventHistoryWSBean loadAuditStatus(CommonEventHistoryWSBean bean);
}
