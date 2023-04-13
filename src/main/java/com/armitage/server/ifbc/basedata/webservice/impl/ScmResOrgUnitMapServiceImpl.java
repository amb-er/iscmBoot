package com.armitage.server.ifbc.basedata.webservice.impl;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.ifbc.basedata.model.ScmResOrgUnitMapWSBean;
import com.armitage.server.ifbc.basedata.service.ScmResOrgUnitMapBiz;
import com.armitage.server.ifbc.basedata.webservice.ScmResOrgUnitMapService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmResOrgUnitMapService/")
public class ScmResOrgUnitMapServiceImpl extends BaseServiceImpl<ScmResOrgUnitMapBiz, ScmResOrgUnitMapWSBean> implements ScmResOrgUnitMapService {
	
} 