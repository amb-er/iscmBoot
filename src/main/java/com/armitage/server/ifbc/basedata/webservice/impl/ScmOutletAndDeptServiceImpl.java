package com.armitage.server.ifbc.basedata.webservice.impl;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.ifbc.basedata.model.ScmOutletAndDeptWSBean;
import com.armitage.server.ifbc.basedata.service.ScmOutletAndDeptBiz;
import com.armitage.server.ifbc.basedata.webservice.ScmOutletAndDeptService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmOutletAndDeptService/")
public class ScmOutletAndDeptServiceImpl extends BaseServiceImpl<ScmOutletAndDeptBiz, ScmOutletAndDeptWSBean> implements ScmOutletAndDeptService{

}
