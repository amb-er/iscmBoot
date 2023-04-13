package com.armitage.server.iscm.common.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.iscm.common.model.ScmMsginfoWSBean;


@Path("/ScmMsginfoService/")
public interface ScmMsginfoService {

	@POST
	@Path("/queryMsgList")
	public ScmMsginfoWSBean queryMsgList(ScmMsginfoWSBean bean);
}
