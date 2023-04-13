package com.armitage.server.iscm.inventorymanage.inventoryinitialization.webservice.impl;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.iscm.inventorymanage.inventoryinitialization.model.ScmInvInitBillEntryWSBean;
import com.armitage.server.iscm.inventorymanage.inventoryinitialization.service.ScmInvInitBillBiz;
import com.armitage.server.iscm.inventorymanage.inventoryinitialization.service.ScmInvInitBillEntryBiz;
import com.armitage.server.iscm.inventorymanage.inventoryinitialization.webservice.ScmInvInitBillEntryService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmInvInitBillEntryService")
public class ScmInvInitBillEntryServiceImpl extends BaseServiceImpl<ScmInvInitBillEntryBiz, ScmInvInitBillEntryWSBean> implements ScmInvInitBillEntryService {
	
	@Override
	public ScmInvInitBillEntryWSBean getDataForLeadInto(
			ScmInvInitBillEntryWSBean bean) {
		// TODO Auto-generated method stub
		return null;
	}

}
