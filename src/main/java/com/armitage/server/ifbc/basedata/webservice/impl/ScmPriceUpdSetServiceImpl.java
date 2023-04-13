package com.armitage.server.ifbc.basedata.webservice.impl;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.ifbc.basedata.model.ScmPriceUpdSetWSBean;
import com.armitage.server.ifbc.basedata.service.ScmPriceUpdSetBiz;
import com.armitage.server.ifbc.basedata.webservice.ScmPriceUpdSetService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmPriceUpdSetService/")
public class ScmPriceUpdSetServiceImpl extends BaseServiceImpl<ScmPriceUpdSetBiz, ScmPriceUpdSetWSBean> implements ScmPriceUpdSetService {
	
} 