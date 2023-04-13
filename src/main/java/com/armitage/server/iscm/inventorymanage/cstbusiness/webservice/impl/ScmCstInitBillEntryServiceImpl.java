package com.armitage.server.iscm.inventorymanage.cstbusiness.webservice.impl;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmCstInitBillEntryWSBean;
import com.armitage.server.iscm.inventorymanage.cstbusiness.service.ScmCstInitBillEntryBiz;
import com.armitage.server.iscm.inventorymanage.cstbusiness.webservice.ScmCstInitBillEntryService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmCstInitBillEntryService")
public class ScmCstInitBillEntryServiceImpl extends BaseServiceImpl<ScmCstInitBillEntryBiz, ScmCstInitBillEntryWSBean> implements ScmCstInitBillEntryService {
	
}
