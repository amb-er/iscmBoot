package com.armitage.server.ifbc.costcard.webservice.impl;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.ifbc.costcard.model.ScmStandardRatioWSBean;
import com.armitage.server.ifbc.costcard.service.ScmStandardRatioBiz;
import com.armitage.server.ifbc.costcard.webservice.ScmStandardRatioService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmStandardRatioService")
public class ScmStandardRatioServiceImpl extends BaseServiceImpl<ScmStandardRatioBiz, ScmStandardRatioWSBean> implements ScmStandardRatioService {
	
}
