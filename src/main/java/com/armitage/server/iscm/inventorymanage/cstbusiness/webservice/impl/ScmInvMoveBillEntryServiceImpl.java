package com.armitage.server.iscm.inventorymanage.cstbusiness.webservice.impl;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvMoveBillEntryWSBean;
import com.armitage.server.iscm.inventorymanage.cstbusiness.service.ScmInvMoveBillEntryBiz;
import com.armitage.server.iscm.inventorymanage.cstbusiness.webservice.ScmInvMoveBillEntryService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmInvMoveBillEntryService")
public class ScmInvMoveBillEntryServiceImpl extends BaseServiceImpl<ScmInvMoveBillEntryBiz, ScmInvMoveBillEntryWSBean> implements ScmInvMoveBillEntryService {
	
}
