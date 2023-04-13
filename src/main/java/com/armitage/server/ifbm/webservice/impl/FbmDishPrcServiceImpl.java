package com.armitage.server.ifbm.webservice.impl;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.ifbm.model.FbmDishPrcWSBean;
import com.armitage.server.ifbm.service.FbmDishPrcBiz;
import com.armitage.server.ifbm.webservice.FbmDishPrcService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;


@Controller
@Path("/fbmDishPrcService")
public class FbmDishPrcServiceImpl extends BaseServiceImpl<FbmDishPrcBiz, FbmDishPrcWSBean> implements FbmDishPrcService {
	
}
