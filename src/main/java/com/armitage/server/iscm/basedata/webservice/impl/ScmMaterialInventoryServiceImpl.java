package com.armitage.server.iscm.basedata.webservice.impl;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.iscm.basedata.model.ScmMaterialInventoryWSBean;
import com.armitage.server.iscm.basedata.service.ScmMaterialInventoryBiz;
import com.armitage.server.iscm.basedata.webservice.ScmMaterialInventoryService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmMaterialInventoryService")
public class ScmMaterialInventoryServiceImpl extends BaseServiceImpl<ScmMaterialInventoryBiz, ScmMaterialInventoryWSBean> implements ScmMaterialInventoryService {
	
}
