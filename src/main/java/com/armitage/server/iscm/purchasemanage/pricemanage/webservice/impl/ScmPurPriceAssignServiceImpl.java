
package com.armitage.server.iscm.purchasemanage.pricemanage.webservice.impl;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurPriceAssignWSBean;
import com.armitage.server.iscm.purchasemanage.pricemanage.service.ScmPurPriceAssignBiz;
import com.armitage.server.iscm.purchasemanage.pricemanage.webservice.ScmPurPriceAssignService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmPurPriceAssignService")
public class ScmPurPriceAssignServiceImpl extends BaseServiceImpl<ScmPurPriceAssignBiz, ScmPurPriceAssignWSBean> implements ScmPurPriceAssignService {
	
}
