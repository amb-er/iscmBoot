
package com.armitage.server.iscm.basedata.webservice.impl;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.iscm.basedata.model.ScmIndustryGroupQualifyTypeWSBean;
import com.armitage.server.iscm.basedata.service.ScmIndustryGroupQualifyTypeBiz;
import com.armitage.server.iscm.basedata.webservice.ScmIndustryGroupQualifyTypeService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmIndustryGroupQualifyTypeService")
public class ScmIndustryGroupQualifyTypeServiceImpl extends BaseServiceImpl<ScmIndustryGroupQualifyTypeBiz, ScmIndustryGroupQualifyTypeWSBean> implements ScmIndustryGroupQualifyTypeService {
	
}
