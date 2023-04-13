package com.armitage.server.ifbm.webservice.impl;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.ifbm.model.FbmDeptWSBean;
import com.armitage.server.ifbm.service.FbmDeptBiz;
import com.armitage.server.ifbm.webservice.FbmDeptService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/fbmDeptService")
public class FbmDeptServiceImpl extends BaseServiceImpl<FbmDeptBiz, FbmDeptWSBean> implements FbmDeptService {
	
}
