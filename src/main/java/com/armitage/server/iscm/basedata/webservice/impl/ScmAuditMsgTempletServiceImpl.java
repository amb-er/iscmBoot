package com.armitage.server.iscm.basedata.webservice.impl;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.iscm.basedata.model.ScmAuditMsgTempletWSBean;
import com.armitage.server.iscm.basedata.service.ScmAuditMsgTempletBiz;
import com.armitage.server.iscm.basedata.webservice.ScmAuditMsgTempletService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmAuditMsgTempletService")
public class ScmAuditMsgTempletServiceImpl extends BaseServiceImpl<ScmAuditMsgTempletBiz, ScmAuditMsgTempletWSBean> implements ScmAuditMsgTempletService {
	
}
