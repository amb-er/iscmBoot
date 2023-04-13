package com.armitage.server.iscm.inventorymanage.countbusiness.webservice.impl;


import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.util.ParamHelper;
import com.armitage.server.iscm.inventorymanage.countbusiness.model.ScmInvCountingTableWSBean;
import com.armitage.server.iscm.inventorymanage.countbusiness.service.ScmInvCountingTableBiz;
import com.armitage.server.iscm.inventorymanage.countbusiness.webservice.ScmInvCountingTableService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmInvCountingTableService")
public class ScmInvCountingTableServiceImpl extends BaseServiceImpl<ScmInvCountingTableBiz, ScmInvCountingTableWSBean> implements ScmInvCountingTableService {

	@Override
	public ScmInvCountingTableWSBean findDifference(ScmInvCountingTableWSBean bean) {
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

