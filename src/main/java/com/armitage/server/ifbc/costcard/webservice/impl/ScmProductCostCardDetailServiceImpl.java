
package com.armitage.server.ifbc.costcard.webservice.impl;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.ifbc.costcard.model.ScmProductCostCardDetailWSBean;
import com.armitage.server.ifbc.costcard.service.ScmProductCostCardDetailBiz;
import com.armitage.server.ifbc.costcard.webservice.ScmProductCostCardDetailService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmProductCostCardDetailService")
public class ScmProductCostCardDetailServiceImpl extends BaseServiceImpl<ScmProductCostCardDetailBiz, ScmProductCostCardDetailWSBean> implements ScmProductCostCardDetailService {
	
}
