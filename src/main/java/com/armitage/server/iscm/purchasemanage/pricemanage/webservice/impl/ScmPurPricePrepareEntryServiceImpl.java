package com.armitage.server.iscm.purchasemanage.pricemanage.webservice.impl;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurPricePrepareEntryWSBean;
import com.armitage.server.iscm.purchasemanage.pricemanage.service.ScmPurPricePrepareEntryBiz;
import com.armitage.server.iscm.purchasemanage.pricemanage.webservice.ScmPurPricePrepareEntryService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmPurPricePrepareEntryService")
public class ScmPurPricePrepareEntryServiceImpl extends BaseServiceImpl<ScmPurPricePrepareEntryBiz, ScmPurPricePrepareEntryWSBean> implements ScmPurPricePrepareEntryService {
	
}

