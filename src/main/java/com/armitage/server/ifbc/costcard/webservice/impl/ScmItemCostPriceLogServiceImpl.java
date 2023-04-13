package com.armitage.server.ifbc.costcard.webservice.impl;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.util.ParamHelper;
import com.armitage.server.ifbc.costcard.model.ScmItemCostPriceLog;
import com.armitage.server.ifbc.costcard.model.ScmItemCostPriceLogWSBean;
import com.armitage.server.ifbc.costcard.service.ScmItemCostPriceLogBiz;
import com.armitage.server.ifbc.costcard.webservice.ScmItemCostPriceLogService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmItemCostPriceLogService")
public class ScmItemCostPriceLogServiceImpl extends BaseServiceImpl<ScmItemCostPriceLogBiz, ScmItemCostPriceLogWSBean> implements ScmItemCostPriceLogService {

	@Override
	public ScmItemCostPriceLogWSBean getItemPrice(ScmItemCostPriceLogWSBean bean) {
		try {
			bean.setList(biz.getItemPrice((ScmItemCostPriceLog)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}
	
}
