package com.armitage.server.ifbc.basedata.webservice.impl;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.ifbc.basedata.model.ScmProductionDeptWSBean;
import com.armitage.server.ifbc.basedata.service.ScmProductionDeptBiz;
import com.armitage.server.ifbc.basedata.webservice.ScmProductionDeptService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmProductionDeptService/")
public class ScmProductionDeptServiceImpl extends BaseServiceImpl<ScmProductionDeptBiz, ScmProductionDeptWSBean> implements ScmProductionDeptService {
	
}