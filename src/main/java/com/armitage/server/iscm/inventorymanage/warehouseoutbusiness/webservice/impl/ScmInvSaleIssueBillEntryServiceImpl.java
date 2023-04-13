package com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.webservice.impl;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvSaleIssueBillEntryWSBean;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.service.ScmInvSaleIssueBillEntryBiz;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.webservice.ScmInvSaleIssueBillEntryService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmInvSaleIssueBillEntryService")
public class ScmInvSaleIssueBillEntryServiceImpl extends BaseServiceImpl<ScmInvSaleIssueBillEntryBiz, ScmInvSaleIssueBillEntryWSBean> implements ScmInvSaleIssueBillEntryService {
	
}
