
package com.armitage.server.iscm.inventorymanage.inventorysetting.webservice.impl;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvSupplyPlanWSBean;
import com.armitage.server.iscm.inventorymanage.inventorysetting.service.ScmInvSupplyPlanBiz;
import com.armitage.server.iscm.inventorymanage.inventorysetting.webservice.ScmInvSupplyPlanService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmInvSupplyPlanService")
public class ScmInvSupplyPlanServiceImpl extends BaseServiceImpl<ScmInvSupplyPlanBiz, ScmInvSupplyPlanWSBean> implements ScmInvSupplyPlanService {
	
}
