package com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.webservice.impl;


import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvMaterialReqBillEntryWSBean;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.service.ScmInvMaterialReqBillEntryBiz;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.webservice.ScmInvMaterialReqBillEntryService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmInvMaterialReqBillEntryService")
public class ScmInvMaterialReqBillEntryServiceImpl extends BaseServiceImpl<ScmInvMaterialReqBillEntryBiz, ScmInvMaterialReqBillEntryWSBean> implements ScmInvMaterialReqBillEntryService {
	
}

