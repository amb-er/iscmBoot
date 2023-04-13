
package com.armitage.server.iscm.purchasemanage.purchasebusiness.webservice.impl;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurREReuseWSBean;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.service.ScmPurREReuseBiz;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.webservice.ScmPurREReuseService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmPurREReuseService")
public class ScmPurREReuseServiceImpl extends BaseServiceImpl<ScmPurREReuseBiz, ScmPurREReuseWSBean> implements ScmPurREReuseService {
	
}
