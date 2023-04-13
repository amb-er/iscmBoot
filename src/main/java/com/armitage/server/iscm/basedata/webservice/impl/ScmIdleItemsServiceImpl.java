
package com.armitage.server.iscm.basedata.webservice.impl;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.util.ParamHelper;
import com.armitage.server.iscm.basedata.model.ScmIdleItemsWSBean;
import com.armitage.server.iscm.basedata.service.ScmIdleItemsBiz;
import com.armitage.server.iscm.basedata.webservice.ScmIdleItemsService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmIdleItemsService")
public class ScmIdleItemsServiceImpl extends BaseServiceImpl<ScmIdleItemsBiz, ScmIdleItemsWSBean> implements ScmIdleItemsService {

	@Override
	public ScmIdleItemsWSBean selectIdleItemsByItems(ScmIdleItemsWSBean bean) {
		try {
			bean.setList(biz.selectIdleItemsByItems((String) bean.getObject(), ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmIdleItemsWSBean selectIdleDrillData(ScmIdleItemsWSBean bean) {
		try {
			bean.setList(biz.selectIdleDrillData((Long)bean.getObject(), ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}
	
}
