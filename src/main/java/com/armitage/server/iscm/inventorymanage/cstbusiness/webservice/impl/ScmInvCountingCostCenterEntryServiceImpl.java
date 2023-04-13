package com.armitage.server.iscm.inventorymanage.cstbusiness.webservice.impl;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCountingCostCenterEntryWSBean;
import com.armitage.server.iscm.inventorymanage.cstbusiness.service.ScmInvCountingCostCenterEntryBiz;
import com.armitage.server.iscm.inventorymanage.cstbusiness.webservice.ScmInvCountingCostCenterEntryService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmInvCountingCostCenterEntryService")
public class ScmInvCountingCostCenterEntryServiceImpl extends BaseServiceImpl<ScmInvCountingCostCenterEntryBiz, ScmInvCountingCostCenterEntryWSBean> implements ScmInvCountingCostCenterEntryService {
	
}
