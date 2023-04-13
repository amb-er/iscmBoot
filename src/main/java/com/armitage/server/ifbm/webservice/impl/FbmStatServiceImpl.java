package com.armitage.server.ifbm.webservice.impl;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.ifbm.model.FbmStatWSBean;
import com.armitage.server.ifbm.service.FbmStatBiz;
import com.armitage.server.ifbm.webservice.FbmStatService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/fbmStatService")
public class FbmStatServiceImpl extends BaseServiceImpl<FbmStatBiz, FbmStatWSBean> implements FbmStatService {
	
}
