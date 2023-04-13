
package com.armitage.server.iscm.purchasemanage.pricemanage.webservice.impl;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.util.CommonBeanHelper;
import com.armitage.server.common.util.ParamHelper;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurQuotationPlan;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurQuotationPlanAdvQuery;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurQuotationPlanWSBean;
import com.armitage.server.iscm.purchasemanage.pricemanage.service.ScmPurQuotationPlanBiz;
import com.armitage.server.iscm.purchasemanage.pricemanage.webservice.ScmPurQuotationPlanService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmPurQuotationPlanService")
public class ScmPurQuotationPlanServiceImpl extends BaseServiceImpl<ScmPurQuotationPlanBiz, ScmPurQuotationPlanWSBean> implements ScmPurQuotationPlanService {

	@Override
	public ScmPurQuotationPlanWSBean submit(ScmPurQuotationPlanWSBean bean) {
		try {
			bean.setObject(biz.submit((ScmPurQuotationPlan)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmPurQuotationPlanWSBean undoSubmit(ScmPurQuotationPlanWSBean bean) {
		try {
			bean.setObject(biz.undoSubmit((ScmPurQuotationPlan)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmPurQuotationPlanWSBean release(ScmPurQuotationPlanWSBean bean) {
		try {
			bean.setObject(biz.release((ScmPurQuotationPlan)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmPurQuotationPlanWSBean undoRelease(	ScmPurQuotationPlanWSBean bean) {
		try {
			bean.setObject(biz.undoRelease((ScmPurQuotationPlan)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmPurQuotationPlanWSBean distribute(ScmPurQuotationPlanWSBean bean) {
		try {
			biz.distribute(bean.getList(),bean.getList2(), ParamHelper.createParam(bean));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmPurQuotationPlanWSBean getDataForLeadInto(ScmPurQuotationPlanWSBean bean) {
		try {
			CommonBeanHelper.toWSBean(biz.getDataForLeadInto((ScmPurQuotationPlanAdvQuery)bean.getObject(),ParamHelper.createParam(bean)),bean);
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmPurQuotationPlanWSBean updatePrtCount(ScmPurQuotationPlanWSBean bean) {
		try {
			bean.setObject(biz.updatePrtCount((ScmPurQuotationPlan)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}
}
