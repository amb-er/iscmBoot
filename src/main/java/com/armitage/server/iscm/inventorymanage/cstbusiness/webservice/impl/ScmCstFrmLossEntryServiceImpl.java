package com.armitage.server.iscm.inventorymanage.cstbusiness.webservice.impl;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmCstFrmLossEntryWSBean;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmCstFrmLossWSBean;
import com.armitage.server.iscm.inventorymanage.cstbusiness.service.ScmCstFrmLossBiz;
import com.armitage.server.iscm.inventorymanage.cstbusiness.service.ScmCstFrmLossEntryBiz;
import com.armitage.server.iscm.inventorymanage.cstbusiness.webservice.ScmCstFrmLossEntryService;
import com.armitage.server.iscm.inventorymanage.cstbusiness.webservice.ScmCstFrmLossService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmCstFrmLossEntryService")
public class ScmCstFrmLossEntryServiceImpl extends BaseServiceImpl<ScmCstFrmLossEntryBiz, ScmCstFrmLossEntryWSBean> implements ScmCstFrmLossEntryService {

	

}
