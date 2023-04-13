package com.armitage.server.ifbm.webservice.impl;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.ifbm.model.FbmItemWSBean;
import com.armitage.server.ifbm.service.FbmItemBiz;
import com.armitage.server.ifbm.webservice.FbmItemService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/fbmItemService")
public class FbmItemServiceImpl extends BaseServiceImpl<FbmItemBiz, FbmItemWSBean> implements FbmItemService {
	
}
