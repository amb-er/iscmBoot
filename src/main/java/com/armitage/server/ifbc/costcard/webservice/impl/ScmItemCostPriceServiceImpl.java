package com.armitage.server.ifbc.costcard.webservice.impl;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.ifbc.costcard.model.ScmItemCostPriceWSBean;
import com.armitage.server.ifbc.costcard.service.ScmItemCostPriceBiz;
import com.armitage.server.ifbc.costcard.webservice.ScmItemCostPriceService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmItemCostPriceService")
public class ScmItemCostPriceServiceImpl extends BaseServiceImpl<ScmItemCostPriceBiz, ScmItemCostPriceWSBean> implements ScmItemCostPriceService {

}
