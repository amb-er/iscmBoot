package com.armitage.server.ifbc.costcard.webservice.impl;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.util.ParamHelper;
import com.armitage.server.ifbc.costcard.model.ScmCostCard2;
import com.armitage.server.ifbc.costcard.model.ScmDishProfitRateWSBean;
import com.armitage.server.ifbc.costcard.service.ScmDishProfitRateBiz;
import com.armitage.server.ifbc.costcard.webservice.ScmDishProfitRateService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmDishProfitRateService")
public class ScmDishProfitRateServiceImpl extends BaseServiceImpl<ScmDishProfitRateBiz, ScmDishProfitRateWSBean> implements ScmDishProfitRateService {

	@Override
	public ScmDishProfitRateWSBean selectProfitRate(ScmDishProfitRateWSBean bean) {
		try {
			ScmCostCard2 scmCostCard = (ScmCostCard2)bean.getObject();
			bean.setObject(biz.selectProfitRateByCostCard(scmCostCard,ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}
	
}
