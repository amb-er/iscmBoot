package com.armitage.server.iscm.inventorymanage.warehouseinbusiness.webservice.impl;


import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvMoveInWarehsBillEntryWSBean;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.service.ScmInvMoveInWarehsBillEntryBiz;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.webservice.ScmInvMoveInWarehsBillEntryService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmInvMoveInWarehsBillEntryService")
public class ScmInvMoveInWarehsBillEntryServiceImpl extends BaseServiceImpl<ScmInvMoveInWarehsBillEntryBiz, ScmInvMoveInWarehsBillEntryWSBean> implements ScmInvMoveInWarehsBillEntryService {
	
}

