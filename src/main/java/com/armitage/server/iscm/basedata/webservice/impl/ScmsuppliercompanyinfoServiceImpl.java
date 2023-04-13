package com.armitage.server.iscm.basedata.webservice.impl;


import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.iscm.basedata.model.ScmsuppliercompanyinfoWSBean;
import com.armitage.server.iscm.basedata.service.ScmsuppliercompanyinfoBiz;
import com.armitage.server.iscm.basedata.webservice.ScmsuppliercompanyinfoService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmsuppliercompanyinfoService")
public class ScmsuppliercompanyinfoServiceImpl extends BaseServiceImpl<ScmsuppliercompanyinfoBiz, ScmsuppliercompanyinfoWSBean> implements ScmsuppliercompanyinfoService {
	
}

