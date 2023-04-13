package com.armitage.server.ifbm.webservice.impl;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.util.ParamHelper;
import com.armitage.server.ifbc.costcard.model.ScmCostCard2;
import com.armitage.server.ifbm.model.FbmCookWSBean;
import com.armitage.server.ifbm.service.FbmCookBiz;
import com.armitage.server.ifbm.webservice.FbmCookService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/fbmCookService")
public class FbmCookServiceImpl extends BaseServiceImpl<FbmCookBiz, FbmCookWSBean> implements FbmCookService {

	@Override
	public FbmCookWSBean selectByDishId(FbmCookWSBean bean) {
		try {
			bean.setList(biz.selectByDishId((ScmCostCard2)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}
	
}
