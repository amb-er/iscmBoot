package com.armitage.server.iscm.inventorymanage.internaltrans.webservice.impl;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.iscm.inventorymanage.internaltrans.model.ScmInvSaleOrderEntryWSBean;
import com.armitage.server.iscm.inventorymanage.internaltrans.service.ScmInvSaleOrderEntryBiz;
import com.armitage.server.iscm.inventorymanage.internaltrans.webservice.ScmInvSaleOrderEntryService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmInvSaleOrderEntryService")
public class ScmInvSaleOrderEntryServiceImpl extends BaseServiceImpl<ScmInvSaleOrderEntryBiz, ScmInvSaleOrderEntryWSBean> implements ScmInvSaleOrderEntryService {
	
}
