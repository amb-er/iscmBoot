package com.armitage.server.ifbc.basedata.webservice.impl;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.ifbc.basedata.model.ScmAccountingCycleWSBean;
import com.armitage.server.ifbc.basedata.service.ScmAccountingCycleBiz;
import com.armitage.server.ifbc.basedata.webservice.ScmAccountingCycleService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmAccountingCycleService/")
public class ScmAccountingCycleServiceImpl extends BaseServiceImpl<ScmAccountingCycleBiz, ScmAccountingCycleWSBean> implements ScmAccountingCycleService {
	
} 