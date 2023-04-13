package com.armitage.server.iscm.inventorymanage.inventorydata.webservice.impl;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.iscm.inventorymanage.inventorydata.model.ScmInvStockWSBean;
import com.armitage.server.iscm.inventorymanage.inventorydata.service.ScmInvStockBiz;
import com.armitage.server.iscm.inventorymanage.inventorydata.webservice.ScmInvStockService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmInvStockService")
public class ScmInvStockServiceImpl extends BaseServiceImpl<ScmInvStockBiz, ScmInvStockWSBean> implements ScmInvStockService {

}
