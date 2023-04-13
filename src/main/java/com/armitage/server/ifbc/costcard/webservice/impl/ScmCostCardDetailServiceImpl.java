package com.armitage.server.ifbc.costcard.webservice.impl;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.ifbc.costcard.model.ScmCostCardDetailWSBean;
import com.armitage.server.ifbc.costcard.service.ScmCostCardDetailBiz;
import com.armitage.server.ifbc.costcard.webservice.ScmCostCardDetailService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmCostCardDetailService")
public class ScmCostCardDetailServiceImpl extends BaseServiceImpl<ScmCostCardDetailBiz, ScmCostCardDetailWSBean> implements ScmCostCardDetailService {
	
}
