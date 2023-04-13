package com.armitage.server.iscm.inventorymanage.countbusiness.webservice.impl;


import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.iscm.inventorymanage.countbusiness.model.ScmInvCountingListWarehouseWSBean;
import com.armitage.server.iscm.inventorymanage.countbusiness.service.ScmInvCountingListWarehouseBiz;
import com.armitage.server.iscm.inventorymanage.countbusiness.webservice.ScmInvCountingListWarehouseService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmInvCountingListWarehouseService")
public class ScmInvCountingListWarehouseServiceImpl extends BaseServiceImpl<ScmInvCountingListWarehouseBiz, ScmInvCountingListWarehouseWSBean> implements ScmInvCountingListWarehouseService {
	
}

