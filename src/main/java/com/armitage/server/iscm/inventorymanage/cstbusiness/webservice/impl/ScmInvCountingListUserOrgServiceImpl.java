package com.armitage.server.iscm.inventorymanage.cstbusiness.webservice.impl;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCountingListUserOrgWSBean;
import com.armitage.server.iscm.inventorymanage.cstbusiness.service.ScmInvCountingListUserOrgBiz;
import com.armitage.server.iscm.inventorymanage.cstbusiness.webservice.ScmInvCountingListUserOrgService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmInvCountingListUserOrgService")
public class ScmInvCountingListUserOrgServiceImpl extends BaseServiceImpl<ScmInvCountingListUserOrgBiz, ScmInvCountingListUserOrgWSBean> implements ScmInvCountingListUserOrgService {
	
}
