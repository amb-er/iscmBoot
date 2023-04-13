package com.armitage.server.iscm.basedata.webservice.impl;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.iscm.basedata.model.ScmsupplierpurchaseinfoWSBean;
import com.armitage.server.iscm.basedata.service.ScmsupplierpurchaseinfoBiz;
import com.armitage.server.iscm.basedata.webservice.ScmsupplierpurchaseinfoService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmsupplierpurchaseinfoService")
public class ScmsupplierpurchaseinfoServiceImpl extends BaseServiceImpl<ScmsupplierpurchaseinfoBiz, ScmsupplierpurchaseinfoWSBean> implements ScmsupplierpurchaseinfoService {
	
}

