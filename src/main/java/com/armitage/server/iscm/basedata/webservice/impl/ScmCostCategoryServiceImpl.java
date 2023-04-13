package com.armitage.server.iscm.basedata.webservice.impl;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.iscm.basedata.model.ScmCostCategoryWSBean;
import com.armitage.server.iscm.basedata.service.ScmCostCategoryBiz;
import com.armitage.server.iscm.basedata.webservice.ScmCostCategoryService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmCostCategoryService")
public class ScmCostCategoryServiceImpl extends BaseServiceImpl<ScmCostCategoryBiz, ScmCostCategoryWSBean> implements ScmCostCategoryService {

}
