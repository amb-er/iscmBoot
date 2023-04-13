
package com.armitage.server.iscm.basedata.webservice.impl;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.util.ParamHelper;
import com.armitage.server.iscm.basedata.model.ScmIdleCause;
import com.armitage.server.iscm.basedata.model.ScmIdleCauseWSBean;
import com.armitage.server.iscm.basedata.service.ScmIdleCauseBiz;
import com.armitage.server.iscm.basedata.webservice.ScmIdleCauseService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmIdleCauseService")
public class ScmIdleCauseServiceImpl extends BaseServiceImpl<ScmIdleCauseBiz, ScmIdleCauseWSBean> implements ScmIdleCauseService {

	@Override
	public ScmIdleCauseWSBean selectByCode(ScmIdleCauseWSBean bean) throws AppException {
		try {
			bean.setObject(biz.selectByCode((String) bean.getObject(),(long) bean.getObject2(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}
	
}
