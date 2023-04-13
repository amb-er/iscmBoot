package com.armitage.server.iscm.common.webservice.impl;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.util.ParamHelper;
import com.armitage.server.iscm.common.model.ScmConfirmRuleWSBean;
import com.armitage.server.iscm.common.service.ScmConfirmRuleBiz;
import com.armitage.server.iscm.common.webservice.ScmConfirmRuleService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmConfirmRuleService")
public class ScmConfirmRuleServiceImpl extends BaseServiceImpl<ScmConfirmRuleBiz, ScmConfirmRuleWSBean> implements ScmConfirmRuleService{

	@Override
	public ScmConfirmRuleWSBean selectByBillType(ScmConfirmRuleWSBean bean) {
		try {
			bean.setObject(biz.selectByBillType((String)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

}
