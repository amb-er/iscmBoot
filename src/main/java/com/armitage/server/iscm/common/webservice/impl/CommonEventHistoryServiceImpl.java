package com.armitage.server.iscm.common.webservice.impl;

import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.util.ParamHelper;
import com.armitage.server.iscm.common.model.CommonEventHistoryWSBean;
import com.armitage.server.iscm.common.service.CommonEventHistoryBiz;
import com.armitage.server.iscm.common.webservice.CommonEventHistoryService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/commonEventHistoryService")
public class CommonEventHistoryServiceImpl extends BaseServiceImpl<CommonEventHistoryBiz, CommonEventHistoryWSBean> implements CommonEventHistoryService{

	@Override
	public CommonEventHistoryWSBean loadAuditStatus(CommonEventHistoryWSBean bean) {
		try {
			bean.setList(biz.loadAuditStatus((BaseModel)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

}
