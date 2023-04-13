package com.armitage.server.iscm.basedata.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.iscm.basedata.model.ScmAuditMsgTempletWSBean;

public interface ScmAuditMsgTempletService {
	
	@POST
	@Path("/findPage")
	public ScmAuditMsgTempletWSBean findPage(ScmAuditMsgTempletWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmAuditMsgTempletWSBean queryPage(ScmAuditMsgTempletWSBean bean);

	@POST
	@Path("/select")
	public ScmAuditMsgTempletWSBean select(ScmAuditMsgTempletWSBean bean);

	@POST
	@Path("/add")
	public ScmAuditMsgTempletWSBean add(ScmAuditMsgTempletWSBean bean);

	@POST
	@Path("/update")
	public ScmAuditMsgTempletWSBean update(ScmAuditMsgTempletWSBean bean);
	

	@POST
	@Path("/delete")
	public ScmAuditMsgTempletWSBean delete(ScmAuditMsgTempletWSBean bean);
	
}
