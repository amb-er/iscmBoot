package com.armitage.server.ifbc.costcard.webservice.impl;

import java.util.List;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.util.ParamHelper;
import com.armitage.server.ifbc.costcard.model.ScmCookCostCard2;
import com.armitage.server.ifbc.costcard.model.ScmCookCostCardDetail2;
import com.armitage.server.ifbc.costcard.model.ScmCookCostCardWSBean;
import com.armitage.server.ifbc.costcard.service.ScmCookCostCardBiz;
import com.armitage.server.ifbc.costcard.webservice.ScmCookCostCardService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmCookCostCardService")
public class ScmCookCostCardServiceImpl extends BaseServiceImpl<ScmCookCostCardBiz, ScmCookCostCardWSBean> implements ScmCookCostCardService {

	@Override
	public ScmCookCostCardWSBean lock(ScmCookCostCardWSBean bean) {
		try {
			ScmCookCostCard2 scmCookCostCard = (ScmCookCostCard2)bean.getObject();
			bean.setObject(biz.lock(scmCookCostCard,ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmCookCostCardWSBean unlock(ScmCookCostCardWSBean bean) {
		try {
			ScmCookCostCard2 scmCookCostCard = (ScmCookCostCard2)bean.getObject();
			bean.setObject(biz.unlock(scmCookCostCard,ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmCookCostCardWSBean copyCostCard(ScmCookCostCardWSBean bean) {
		try {
			biz.copyCostCard((ScmCookCostCard2)bean.getObject(),(List<ScmCookCostCardDetail2>)bean.getList(),ParamHelper.createParam(bean));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmCookCostCardWSBean auditCostCard(ScmCookCostCardWSBean bean) {
		try {
			ScmCookCostCard2 scmCookCostCard = (ScmCookCostCard2)bean.getObject();
			bean.setObject(biz.auditCostCard(scmCookCostCard,ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

}