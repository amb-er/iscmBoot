package com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.webservice.impl;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvOtherIssueBillEntryWSBean;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.service.ScmInvOtherIssueBillEntryBiz;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.webservice.ScmInvOtherIssueBillEntryService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmInvOtherIssueBillEntryService")
public class ScmInvOtherIssueBillEntryServiceImpl  extends BaseServiceImpl<ScmInvOtherIssueBillEntryBiz, ScmInvOtherIssueBillEntryWSBean> implements ScmInvOtherIssueBillEntryService{
	
}
