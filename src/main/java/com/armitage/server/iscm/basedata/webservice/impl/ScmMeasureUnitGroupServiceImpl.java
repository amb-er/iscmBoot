package com.armitage.server.iscm.basedata.webservice.impl;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.iscm.basedata.model.ScmMeasureUnitGroupWSBean;
import com.armitage.server.iscm.basedata.service.ScmMeasureUnitGroupBiz;
import com.armitage.server.iscm.basedata.webservice.ScmMeasureUnitGroupService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmMeasureUnitGroupService")
public class ScmMeasureUnitGroupServiceImpl extends BaseServiceImpl<ScmMeasureUnitGroupBiz, ScmMeasureUnitGroupWSBean> implements ScmMeasureUnitGroupService {
	
}
