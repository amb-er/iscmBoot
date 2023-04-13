package com.armitage.server.ifbm.webservice.impl;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.ifbm.model.FbmStatOrDeptWSBean;
import com.armitage.server.ifbm.service.FbmStatOrDeptBiz;
import com.armitage.server.ifbm.webservice.FbmStatOrDeptService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/fbmStatOrDeptService")
public class FbmStatOrDeptServiceImpl extends BaseServiceImpl<FbmStatOrDeptBiz, FbmStatOrDeptWSBean> implements FbmStatOrDeptService {
	
}
