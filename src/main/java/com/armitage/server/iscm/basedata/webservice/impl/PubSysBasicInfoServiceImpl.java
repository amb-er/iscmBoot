package com.armitage.server.iscm.basedata.webservice.impl;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.util.ParamHelper;
import com.armitage.server.iscm.basedata.model.PubSysBasicInfo;
import com.armitage.server.iscm.basedata.model.PubSysBasicInfoWSBean;
import com.armitage.server.iscm.basedata.service.PubSysBasicInfoBiz;
import com.armitage.server.iscm.basedata.webservice.PubSysBasicInfoService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/pubSysBasicInfoService")
public class PubSysBasicInfoServiceImpl extends BaseServiceImpl<PubSysBasicInfoBiz, PubSysBasicInfoWSBean> implements PubSysBasicInfoService {
	
	@Override
	public PubSysBasicInfoWSBean checkTaxRateExist(PubSysBasicInfoWSBean bean) {
		try {
			bean.setObject(biz.checkTaxRateExist((PubSysBasicInfo) bean.getObject(), ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}
}
