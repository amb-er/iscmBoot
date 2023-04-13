package com.armitage.server.iscm.basedata.webservice.impl;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.iscm.basedata.model.ScmsuppliergroupWSBean;
import com.armitage.server.iscm.basedata.service.ScmsuppliergroupBiz;
import com.armitage.server.iscm.basedata.webservice.ScmsuppliergroupService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmsuppliergroupService")
public class ScmsuppliergroupServiceImpl extends BaseServiceImpl<ScmsuppliergroupBiz, ScmsuppliergroupWSBean> implements ScmsuppliergroupService {
	
}