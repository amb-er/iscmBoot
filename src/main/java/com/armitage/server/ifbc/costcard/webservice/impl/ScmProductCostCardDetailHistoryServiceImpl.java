
package com.armitage.server.ifbc.costcard.webservice.impl;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.ifbc.costcard.model.ScmProductCostCardDetailHistoryWSBean;
import com.armitage.server.ifbc.costcard.service.ScmProductCostCardDetailHistoryBiz;
import com.armitage.server.ifbc.costcard.webservice.ScmProductCostCardDetailHistoryService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmProductCostCardDetailHistoryService")
public class ScmProductCostCardDetailHistoryServiceImpl extends BaseServiceImpl<ScmProductCostCardDetailHistoryBiz, ScmProductCostCardDetailHistoryWSBean> implements ScmProductCostCardDetailHistoryService {
	
}
