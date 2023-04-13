package com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.webservice.impl;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvSalePriceentryWSBean;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.service.ScmInvSalePriceentryBiz;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.webservice.ScmInvSalePriceentryService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmInvSalePriceentryService")
public class ScmInvSalePriceentryServiceImpl extends BaseServiceImpl<ScmInvSalePriceentryBiz, ScmInvSalePriceentryWSBean> implements ScmInvSalePriceentryService{

}
