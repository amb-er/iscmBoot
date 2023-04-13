
package com.armitage.server.iscm.inventorymanage.inventorysetting.webservice.impl;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvSupplyPlanEntryWSBean;
import com.armitage.server.iscm.inventorymanage.inventorysetting.service.ScmInvSupplyPlanEntryBiz;
import com.armitage.server.iscm.inventorymanage.inventorysetting.webservice.ScmInvSupplyPlanEntryService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmInvSupplyPlanEntryService")
public class ScmInvSupplyPlanEntryServiceImpl extends BaseServiceImpl<ScmInvSupplyPlanEntryBiz, ScmInvSupplyPlanEntryWSBean> implements ScmInvSupplyPlanEntryService {
	
}
