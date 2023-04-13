package com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.webservice.impl;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvMaterialRequestBillEntryWSBean;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.service.ScmInvMaterialRequestBillEntryBiz;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.webservice.ScmInvMaterialRequestBillEntryService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmInvMaterialRequestBillEntryService")
public class ScmInvMaterialRequestBillEntryServiceImpl extends BaseServiceImpl<ScmInvMaterialRequestBillEntryBiz, ScmInvMaterialRequestBillEntryWSBean> implements ScmInvMaterialRequestBillEntryService {
	
}

