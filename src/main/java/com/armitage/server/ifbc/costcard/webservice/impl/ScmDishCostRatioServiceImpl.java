package com.armitage.server.ifbc.costcard.webservice.impl;

import java.util.List;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.util.ParamHelper;
import com.armitage.server.ifbc.costcard.model.ScmCostCard2;
import com.armitage.server.ifbc.costcard.model.ScmDishCostRatio2;
import com.armitage.server.ifbc.costcard.model.ScmDishCostRatioWSBean;
import com.armitage.server.ifbc.costcard.service.ScmDishCostRatioBiz;
import com.armitage.server.ifbc.costcard.webservice.ScmDishCostRatioService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmDishCostRatioService")
public class ScmDishCostRatioServiceImpl extends BaseServiceImpl<ScmDishCostRatioBiz, ScmDishCostRatioWSBean> implements ScmDishCostRatioService {

	@Override
	public ScmDishCostRatioWSBean selectCostRatio(ScmDishCostRatioWSBean bean) {
		try {
			bean.setList(biz.selectCostRatio((ScmCostCard2)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmDishCostRatioWSBean saveCostRatio(ScmDishCostRatioWSBean bean) {
		try {
			bean.setList(biz.saveCostRatio((List<ScmDishCostRatio2>)bean.getList(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}
	
}
