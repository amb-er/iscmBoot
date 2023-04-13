package com.armitage.server.iscm.basedata.webservice.impl;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.iscm.basedata.model.ScmPurchaseTypeWSBean;
import com.armitage.server.iscm.basedata.service.ScmPurchaseTypeBiz;
import com.armitage.server.iscm.basedata.webservice.ScmPurchaseTypeService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmPurchaseTypeService")
public class ScmPurchaseTypeServiceImpl extends BaseServiceImpl<ScmPurchaseTypeBiz, ScmPurchaseTypeWSBean> implements ScmPurchaseTypeService {

}
