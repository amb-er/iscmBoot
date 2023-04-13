package com.armitage.server.ifbc.costcard.webservice.impl;

import java.util.List;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.util.CommonBeanHelper;
import com.armitage.server.common.util.ParamHelper;
import com.armitage.server.ifbc.costcard.model.ScmCostCard2;
import com.armitage.server.ifbc.costcard.model.ScmCostCardDetail2;
import com.armitage.server.ifbc.costcard.model.ScmCostCardWSBean;
import com.armitage.server.ifbc.costcard.model.ScmDishReplaceCostCard;
import com.armitage.server.ifbc.costcard.service.ScmCostCardBiz;
import com.armitage.server.ifbc.costcard.webservice.ScmCostCardService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmCostCardService")
public class ScmCostCardServiceImpl extends BaseServiceImpl<ScmCostCardBiz, ScmCostCardWSBean> implements ScmCostCardService {

	@Override
	public ScmCostCardWSBean lock(ScmCostCardWSBean bean) {
		try {
			ScmCostCard2 scmCostCard = (ScmCostCard2)bean.getObject();
			bean.setObject(biz.lock(scmCostCard,ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmCostCardWSBean unlock(ScmCostCardWSBean bean) {
		try {
			ScmCostCard2 scmCostCard = (ScmCostCard2)bean.getObject();
			bean.setObject(biz.unlock(scmCostCard,ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}
	
	@Override
	public ScmCostCardWSBean auditCostCard(ScmCostCardWSBean bean) {
		try {
			ScmCostCard2 scmCostCard = (ScmCostCard2)bean.getObject();
			bean.setObject(biz.auditCostCard(scmCostCard,ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}
	
//	@Override
//	public ScmCostCardWSBean getDataForReplace(ScmCostCardWSBean bean) {
//		try {
//			CommonBeanHelper.toWSBean(biz.getDataForReplace((ScmDishReplaceCostCard)bean.getObject(),ParamHelper.createParam(bean)),bean);
//		} catch (AppException e) {
//			Message.inMessage(bean, e);
//		} catch (Exception e) {
//			Message.inMessage(bean, e);
//		}
//		return bean;
//	}
//
//	@Override
//	public ScmCostCardWSBean replaceItem(ScmCostCardWSBean bean) {
//		try {
//			biz.replaceItem((List<ScmCostCard2>)bean.getList(),(ScmDishReplaceCostCard)bean.getObject(),ParamHelper.createParam(bean));
//		} catch (AppException e) {
//			Message.inMessage(bean, e);
//		} catch (Exception e) {
//			Message.inMessage(bean, e);
//		}
//		return bean;
//	}

	@Override
	public ScmCostCardWSBean copyCostCard(ScmCostCardWSBean bean) {
		try {
			biz.copyCostCard((ScmCostCard2)bean.getObject(),(List<ScmCostCardDetail2>)bean.getList(),ParamHelper.createParam(bean));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}
}
