package com.armitage.server.iscm.basedata.webservice.impl;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.iscm.basedata.model.ScmsupplierlinkmanWSBean;
import com.armitage.server.iscm.basedata.service.ScmsupplierlinkmanBiz;
import com.armitage.server.iscm.basedata.webservice.ScmsupplierlinkmanService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;


@Controller
@Path("/scmsupplierlinkmanService")
public class ScmsupplierlinkmanServiceImpl extends BaseServiceImpl<ScmsupplierlinkmanBiz, ScmsupplierlinkmanWSBean> implements ScmsupplierlinkmanService {
	
}
