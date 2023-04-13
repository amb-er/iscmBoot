package com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.webservice.impl;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvMoveIssueBillEntryWSBean;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.service.ScmInvMoveIssueBillEntryBiz;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.webservice.ScmInvMoveIssueBillEntryService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmInvMoveIssueBillEntryService")
public class ScmInvMoveIssueBillEntryServiceImpl  extends BaseServiceImpl<ScmInvMoveIssueBillEntryBiz, ScmInvMoveIssueBillEntryWSBean> implements ScmInvMoveIssueBillEntryService{
	
}
