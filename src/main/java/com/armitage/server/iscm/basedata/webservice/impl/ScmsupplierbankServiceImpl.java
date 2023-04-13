package com.armitage.server.iscm.basedata.webservice.impl;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.iscm.basedata.model.ScmsupplierbankWSBean;
import com.armitage.server.iscm.basedata.service.ScmsupplierbankBiz;
import com.armitage.server.iscm.basedata.webservice.ScmsupplierbankService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmsupplierbankService")
public class ScmsupplierbankServiceImpl extends BaseServiceImpl<ScmsupplierbankBiz, ScmsupplierbankWSBean> implements ScmsupplierbankService {
	
}
