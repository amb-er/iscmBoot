package com.armitage.server.ifbm.webservice.impl;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.ifbm.model.FbmCookTypeWSBean;
import com.armitage.server.ifbm.service.FbmCookTypeBiz;
import com.armitage.server.ifbm.webservice.FbmCookTypeService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/fbmCookTypeService")
public class FbmCookTypeServiceImpl extends BaseServiceImpl<FbmCookTypeBiz, FbmCookTypeWSBean> implements FbmCookTypeService {
	
}
