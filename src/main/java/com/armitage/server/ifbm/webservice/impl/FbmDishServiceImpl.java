package com.armitage.server.ifbm.webservice.impl;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.ifbm.model.FbmDishWSBean;
import com.armitage.server.ifbm.service.FbmDishBiz;
import com.armitage.server.ifbm.webservice.FbmDishService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/fbmDishService")
public class FbmDishServiceImpl extends BaseServiceImpl<FbmDishBiz, FbmDishWSBean> implements FbmDishService {
	
}
