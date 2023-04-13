package com.armitage.server.iscm.purchasemanage.pricemanage.webservice.impl;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurQuotationEntryWSBean;
import com.armitage.server.iscm.purchasemanage.pricemanage.service.ScmPurQuotationEntryBiz;
import com.armitage.server.iscm.purchasemanage.pricemanage.webservice.ScmPurQuotationEntryService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmPurQuotationEntryService")
public class ScmPurQuotationEntryServiceImpl extends BaseServiceImpl<ScmPurQuotationEntryBiz, ScmPurQuotationEntryWSBean> implements ScmPurQuotationEntryService {
	
}
