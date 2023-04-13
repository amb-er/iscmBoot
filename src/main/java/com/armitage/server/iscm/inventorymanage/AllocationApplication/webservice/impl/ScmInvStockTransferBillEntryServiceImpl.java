package com.armitage.server.iscm.inventorymanage.AllocationApplication.webservice.impl;

import javax.ws.rs.Path;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.iscm.inventorymanage.AllocationApplication.model.ScmInvStockTransferBillEntryWSBean;
import com.armitage.server.iscm.inventorymanage.AllocationApplication.service.ScmInvStockTransferBillEntryBiz;
import com.armitage.server.iscm.inventorymanage.AllocationApplication.webservice.ScmInvStockTransferBillEntryService;
import org.springframework.stereotype.Controller;

@Controller
@Path("/scmInvStockTransferBillEntryService")
public class ScmInvStockTransferBillEntryServiceImpl extends BaseServiceImpl<ScmInvStockTransferBillEntryBiz, ScmInvStockTransferBillEntryWSBean> implements ScmInvStockTransferBillEntryService {

}
