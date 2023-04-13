package com.armitage.server.iscm.purchasemanage.purchasebusiness.webservice.impl;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurDeliveryWSBean;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.service.ScmPurDeliveryBiz;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.webservice.ScmPurDeliveryService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmPurDeliveryService")
public class ScmPurDeliveryServiceImpl extends BaseServiceImpl<ScmPurDeliveryBiz, ScmPurDeliveryWSBean> implements ScmPurDeliveryService {

}
