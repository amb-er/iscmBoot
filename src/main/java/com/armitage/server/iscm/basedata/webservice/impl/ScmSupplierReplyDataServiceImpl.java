package com.armitage.server.iscm.basedata.webservice.impl;


import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.iscm.basedata.model.ScmSupplierReplyDataWSBean;
import com.armitage.server.iscm.basedata.service.ScmSupplierReplyDataBiz;
import com.armitage.server.iscm.basedata.webservice.ScmSupplierReplyDataService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmSupplierReplyDataService")
public class ScmSupplierReplyDataServiceImpl extends BaseServiceImpl<ScmSupplierReplyDataBiz, ScmSupplierReplyDataWSBean> implements ScmSupplierReplyDataService {
	
}

