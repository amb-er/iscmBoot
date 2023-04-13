package com.armitage.server.iscm.basedata.webservice.impl;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.iscm.basedata.model.ScmMaterialPurchaseWSBean;
import com.armitage.server.iscm.basedata.service.ScmMaterialPurchaseBiz;
import com.armitage.server.iscm.basedata.webservice.ScmMaterialPurchaseService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmMaterialPurchaseService")
public class ScmMaterialPurchaseServiceImpl extends BaseServiceImpl<ScmMaterialPurchaseBiz, ScmMaterialPurchaseWSBean> implements ScmMaterialPurchaseService {
	
}
