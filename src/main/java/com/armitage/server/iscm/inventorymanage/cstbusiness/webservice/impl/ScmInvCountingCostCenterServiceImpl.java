package com.armitage.server.iscm.inventorymanage.cstbusiness.webservice.impl;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.util.ParamHelper;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCountingCostCenterWSBean;
import com.armitage.server.iscm.inventorymanage.cstbusiness.service.ScmInvCountingCostCenterBiz;
import com.armitage.server.iscm.inventorymanage.cstbusiness.webservice.ScmInvCountingCostCenterService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmInvCountingCostCenterService")
public class ScmInvCountingCostCenterServiceImpl extends BaseServiceImpl<ScmInvCountingCostCenterBiz, ScmInvCountingCostCenterWSBean> implements ScmInvCountingCostCenterService {
	@Override
	public ScmInvCountingCostCenterWSBean findDifference(ScmInvCountingCostCenterWSBean bean) {
		try {
			long taskId = (Long)bean.getObject();
			bean.setList(biz.findDifference(taskId,ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}
}
