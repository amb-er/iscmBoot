package com.armitage.server.iscm.purchasemanage.purchasebusiness.webservice.impl;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurReceiveWSBean;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.service.ScmDeptReceiveBiz;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.webservice.ScmDeptReceiveService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmDeptReceiveService")
public class ScmDeptReceiveServiceImpl extends BaseServiceImpl<ScmDeptReceiveBiz, ScmPurReceiveWSBean> implements ScmDeptReceiveService {
}

