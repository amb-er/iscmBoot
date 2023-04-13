
package com.armitage.server.iscm.inventorymanage.inventorysetting.webservice.impl;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvSupplyRuleEntryWSBean;
import com.armitage.server.iscm.inventorymanage.inventorysetting.service.ScmInvSupplyRuleEntryBiz;
import com.armitage.server.iscm.inventorymanage.inventorysetting.webservice.ScmInvSupplyRuleEntryService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmInvSupplyRuleEntryService")
public class ScmInvSupplyRuleEntryServiceImpl extends BaseServiceImpl<ScmInvSupplyRuleEntryBiz, ScmInvSupplyRuleEntryWSBean> implements ScmInvSupplyRuleEntryService {
	
}
