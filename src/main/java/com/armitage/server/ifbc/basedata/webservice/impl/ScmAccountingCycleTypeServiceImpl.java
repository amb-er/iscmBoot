package com.armitage.server.ifbc.basedata.webservice.impl;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.ifbc.basedata.model.ScmAccountingCycleTypeWSBean;
import com.armitage.server.ifbc.basedata.service.ScmAccountingCycleTypeBiz;
import com.armitage.server.ifbc.basedata.webservice.ScmAccountingCycleTypeService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmAccountingCycleTypeService/")
public class ScmAccountingCycleTypeServiceImpl extends BaseServiceImpl<ScmAccountingCycleTypeBiz, ScmAccountingCycleTypeWSBean> implements ScmAccountingCycleTypeService {
	
} 