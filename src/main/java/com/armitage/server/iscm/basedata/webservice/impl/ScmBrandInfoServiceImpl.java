package com.armitage.server.iscm.basedata.webservice.impl;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.iscm.basedata.model.ScmBrandInfoWSBean;
import com.armitage.server.iscm.basedata.service.ScmBrandInfoBiz;
import com.armitage.server.iscm.basedata.webservice.ScmBrandInfoService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmBrandInfoService")
public class ScmBrandInfoServiceImpl extends BaseServiceImpl<ScmBrandInfoBiz, ScmBrandInfoWSBean> implements ScmBrandInfoService {

}
