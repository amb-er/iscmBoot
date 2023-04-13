
package com.armitage.server.iscm.inventorymanage.countbusiness.webservice.impl;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.iscm.inventorymanage.countbusiness.model.ScmInvCountingListMaterialGroupWSBean;
import com.armitage.server.iscm.inventorymanage.countbusiness.service.ScmInvCountingListMaterialGroupBiz;
import com.armitage.server.iscm.inventorymanage.countbusiness.webservice.ScmInvCountingListMaterialGroupService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmInvCountingListMaterialGroupService")
public class ScmInvCountingListMaterialGroupServiceImpl extends BaseServiceImpl<ScmInvCountingListMaterialGroupBiz, ScmInvCountingListMaterialGroupWSBean> implements ScmInvCountingListMaterialGroupService {
	
}
