
package com.armitage.server.iscm.inventorymanage.cstbusiness.webservice.impl;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCountingCostCenterProductWSBean;
import com.armitage.server.iscm.inventorymanage.cstbusiness.service.ScmInvCountingCostCenterProductBiz;
import com.armitage.server.iscm.inventorymanage.cstbusiness.webservice.ScmInvCountingCostCenterProductService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmInvCountingCostCenterProductService")
public class ScmInvCountingCostCenterProductServiceImpl extends BaseServiceImpl<ScmInvCountingCostCenterProductBiz, ScmInvCountingCostCenterProductWSBean> implements ScmInvCountingCostCenterProductService {
	
}
