package com.armitage.server.iscm.inventorymanage.warehouseinbusiness.webservice.impl;


import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvOtherInWarehsBillEntryWSBean;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.service.ScmInvOtherInWarehsBillEntryBiz;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.webservice.ScmInvOtherInWarehsBillEntryService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmInvOtherInWarehsBillEntryService")
public class ScmInvOtherInWarehsBillEntryServiceImpl extends BaseServiceImpl<ScmInvOtherInWarehsBillEntryBiz, ScmInvOtherInWarehsBillEntryWSBean> implements ScmInvOtherInWarehsBillEntryService {
	
}

