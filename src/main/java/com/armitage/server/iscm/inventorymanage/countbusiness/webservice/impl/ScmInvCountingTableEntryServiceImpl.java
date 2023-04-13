package com.armitage.server.iscm.inventorymanage.countbusiness.webservice.impl;


import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.iscm.inventorymanage.countbusiness.model.ScmInvCountingTableEntryWSBean;
import com.armitage.server.iscm.inventorymanage.countbusiness.service.ScmInvCountingTableEntryBiz;
import com.armitage.server.iscm.inventorymanage.countbusiness.webservice.ScmInvCountingTableEntryService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmInvCountingTableEntryService")
public class ScmInvCountingTableEntryServiceImpl extends BaseServiceImpl<ScmInvCountingTableEntryBiz, ScmInvCountingTableEntryWSBean> implements ScmInvCountingTableEntryService {

}

