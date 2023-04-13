package com.armitage.server.iscm.inventorymanage.countbusiness.webservice.impl;



import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.iscm.inventorymanage.countbusiness.model.ScmInvCountingListMaterialWSBean;
import com.armitage.server.iscm.inventorymanage.countbusiness.service.ScmInvCountingListMaterialBiz;
import com.armitage.server.iscm.inventorymanage.countbusiness.webservice.ScmInvCountingListMaterialService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmInvCountingListMaterialService")
public class ScmInvCountingListMaterialServiceImpl extends BaseServiceImpl<ScmInvCountingListMaterialBiz, ScmInvCountingListMaterialWSBean> implements ScmInvCountingListMaterialService {
	
}

