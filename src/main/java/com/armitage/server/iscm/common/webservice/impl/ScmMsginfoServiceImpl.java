package com.armitage.server.iscm.common.webservice.impl;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.util.ParamHelper;
import com.armitage.server.iscm.common.model.ScmMsginfoWSBean;
import com.armitage.server.iscm.common.service.ScmMsginfoBiz;
import com.armitage.server.iscm.common.webservice.ScmMsginfoService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmMsginfoService")
public class ScmMsginfoServiceImpl extends BaseServiceImpl<ScmMsginfoBiz,ScmMsginfoWSBean> implements ScmMsginfoService{

	@Override
	public ScmMsginfoWSBean queryMsgList(ScmMsginfoWSBean bean) {
		try {
			bean.setList(biz.queryMsgList(bean.getPage(), ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

}
