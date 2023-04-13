package com.armitage.server.iscm.inventorymanage.countbusiness.webservice.impl;


import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.util.ParamHelper;
import com.armitage.server.iscm.inventorymanage.countbusiness.model.ScmInvBalOver;
import com.armitage.server.iscm.inventorymanage.countbusiness.model.ScmInvBalOverWSBean;
import com.armitage.server.iscm.inventorymanage.countbusiness.service.ScmInvBalOverBiz;
import com.armitage.server.iscm.inventorymanage.countbusiness.webservice.ScmInvBalOverService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmInvBalOverService")
public class ScmInvBalOverServiceImpl extends BaseServiceImpl<ScmInvBalOverBiz, ScmInvBalOverWSBean> implements ScmInvBalOverService {

	@Override
	public ScmInvBalOverWSBean balOver(ScmInvBalOverWSBean bean) {
		try {
			bean.setObject(biz.balOver((ScmInvBalOver)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}
	
}

