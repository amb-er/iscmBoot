package com.armitage.server.iscm.common.webservice.impl;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.util.ParamHelper;
import com.armitage.server.iscm.common.model.ScmSyncTaskInfo2;
import com.armitage.server.iscm.common.model.ScmSyncTaskInfoWSBean;
import com.armitage.server.iscm.common.service.ScmSyncTaskInfoBiz;
import com.armitage.server.iscm.common.webservice.ScmSyncTaskInfoService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmSyncTaskInfoService")
public class ScmSyncTaskInfoServiceImpl extends BaseServiceImpl<ScmSyncTaskInfoBiz, ScmSyncTaskInfoWSBean> implements ScmSyncTaskInfoService{

	@Override
	public ScmSyncTaskInfoWSBean disableTask(ScmSyncTaskInfoWSBean bean) {
		try {
			biz.disableTask((ScmSyncTaskInfo2)bean.getObject(), ParamHelper.createParam(bean));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmSyncTaskInfoWSBean queryForBatchTask(ScmSyncTaskInfoWSBean bean) {
		try {
			bean.setList(biz.queryForBatchTask((ScmSyncTaskInfo2)bean.getObject(), ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmSyncTaskInfoWSBean generateSyncData(ScmSyncTaskInfoWSBean bean) {
		try {
			biz.generateSyncData((ScmSyncTaskInfo2)bean.getObject(), bean.getList(),ParamHelper.createParam(bean));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

}
