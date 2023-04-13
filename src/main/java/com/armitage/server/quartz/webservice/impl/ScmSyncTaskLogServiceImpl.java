package com.armitage.server.quartz.webservice.impl;


import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.util.ParamHelper;
import com.armitage.server.quartz.model.ScmSyncTaskLog;
import com.armitage.server.quartz.model.ScmSyncTaskLogWSBean;
import com.armitage.server.quartz.service.ScmSyncTaskLogBiz;
import com.armitage.server.quartz.webservice.ScmSyncTaskLogService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmSyncTaskLogService")
public class ScmSyncTaskLogServiceImpl extends BaseServiceImpl<ScmSyncTaskLogBiz, ScmSyncTaskLogWSBean> implements ScmSyncTaskLogService {

	@Override
	public ScmSyncTaskLogWSBean manualUpdate(ScmSyncTaskLogWSBean bean) {
		try {
			ScmSyncTaskLog syncTaskLog = (ScmSyncTaskLog)bean.getObject();
			bean.setObject(biz.manualUpdate(syncTaskLog,ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}
	
}

