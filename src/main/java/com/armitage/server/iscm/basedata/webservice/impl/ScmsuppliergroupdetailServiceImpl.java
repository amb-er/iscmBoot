package com.armitage.server.iscm.basedata.webservice.impl;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.iscm.basedata.model.ScmsuppliergroupdetailWSBean;
import com.armitage.server.iscm.basedata.service.ScmsuppliergroupdetailBiz;
import com.armitage.server.iscm.basedata.webservice.ScmsuppliergroupdetailService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmsuppliergroupdetailService")
public class ScmsuppliergroupdetailServiceImpl extends BaseServiceImpl<ScmsuppliergroupdetailBiz, ScmsuppliergroupdetailWSBean> implements ScmsuppliergroupdetailService {
	
}
