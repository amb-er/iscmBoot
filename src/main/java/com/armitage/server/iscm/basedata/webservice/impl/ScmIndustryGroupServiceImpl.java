package com.armitage.server.iscm.basedata.webservice.impl;


import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.iscm.basedata.model.ScmIndustryGroupWSBean;
import com.armitage.server.iscm.basedata.service.ScmIndustryGroupBiz;
import com.armitage.server.iscm.basedata.webservice.ScmIndustryGroupService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmIndustryGroupService")
public class ScmIndustryGroupServiceImpl extends BaseServiceImpl<ScmIndustryGroupBiz, ScmIndustryGroupWSBean> implements ScmIndustryGroupService {
	
}

