package com.armitage.server.iscm.common.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.iscm.common.model.ScmConfirmRuleWSBean;
import com.armitage.server.iscm.common.model.TaskSettingWSBean;

@Path("/taskCodeService/")
public interface ScmConfirmRuleService {
	
	@POST
	@Path("/findPage")
	public ScmConfirmRuleWSBean findPage(ScmConfirmRuleWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmConfirmRuleWSBean queryPage(ScmConfirmRuleWSBean bean);

	@POST
	@Path("/select")
	public ScmConfirmRuleWSBean select(ScmConfirmRuleWSBean bean);

	@POST
	@Path("/add")
	public ScmConfirmRuleWSBean add(ScmConfirmRuleWSBean bean);

	@POST
	@Path("/update")
	public ScmConfirmRuleWSBean update(ScmConfirmRuleWSBean bean);

	@POST
	@Path("/delete")
	public ScmConfirmRuleWSBean delete(ScmConfirmRuleWSBean bean);

	@POST
	@Path("/selectByBillType")
	public ScmConfirmRuleWSBean selectByBillType(ScmConfirmRuleWSBean bean);
}
