package com.armitage.server.iscm.basedata.webservice.impl;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.iscm.basedata.model.ScmMaterialCompanyInfoWSBean;
import com.armitage.server.iscm.basedata.service.ScmMaterialCompanyInfoBiz;
import com.armitage.server.iscm.basedata.webservice.ScmMaterialCompanyInfoService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/ScmMaterialCompanyInfoService")
public class ScmMaterialCompanyInfoServiceImpl extends BaseServiceImpl<ScmMaterialCompanyInfoBiz, ScmMaterialCompanyInfoWSBean> implements ScmMaterialCompanyInfoService {
	
}
