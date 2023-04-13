package com.armitage.server.iscm.inventorymanage.cstbusiness.webservice.impl;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCostConsumeEntryWSBean;
import com.armitage.server.iscm.inventorymanage.cstbusiness.service.ScmInvCostConsumeEntryBiz;
import com.armitage.server.iscm.inventorymanage.cstbusiness.webservice.ScmInvCostConsumeEntryService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmInvCostConsumeEntryService")
public class ScmInvCostConsumeEntryServiceImpl extends BaseServiceImpl<ScmInvCostConsumeEntryBiz, ScmInvCostConsumeEntryWSBean> implements ScmInvCostConsumeEntryService {
	
}
