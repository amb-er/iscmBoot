
package com.armitage.server.iscm.purchasemanage.purchasesetting.webservice.impl;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.util.ParamHelper;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.CommonAuditParams;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurSupplierSource2;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurSupplierSourceWSBean;
import com.armitage.server.iscm.purchasemanage.purchasesetting.service.ScmPurSupplierSourceBiz;
import com.armitage.server.iscm.purchasemanage.purchasesetting.webservice.ScmPurSupplierSourceService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/ScmPurSupplierSourceService")
public class ScmPurSupplierSourceServiceImpl extends BaseServiceImpl<ScmPurSupplierSourceBiz, ScmPurSupplierSourceWSBean> implements ScmPurSupplierSourceService {

	@Override
	public ScmPurSupplierSourceWSBean submit(ScmPurSupplierSourceWSBean bean) {
		try {
			ScmPurSupplierSource2 scmPurSupplierSource = (ScmPurSupplierSource2)bean.getObject();
			bean.setObject(biz.doSubmit(scmPurSupplierSource,ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmPurSupplierSourceWSBean undoSubmit(ScmPurSupplierSourceWSBean bean) {
		try {
			ScmPurSupplierSource2 scmPurSupplierSource = (ScmPurSupplierSource2)bean.getObject();
			bean.setObject(biz.doUnSubmit(scmPurSupplierSource,ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmPurSupplierSourceWSBean doAudit(ScmPurSupplierSourceWSBean bean) {
		try {
			bean.setObject(biz.doAudit((CommonAuditParams)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmPurSupplierSourceWSBean undoAudit(ScmPurSupplierSourceWSBean bean) {
		try {
			bean.setObject(biz.doUnAudit((ScmPurSupplierSource2)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmPurSupplierSourceWSBean release(ScmPurSupplierSourceWSBean bean) {
		try {
			ScmPurSupplierSource2 scmPurSupplierSource = (ScmPurSupplierSource2)bean.getObject();
			bean.setObject(biz.doRelease(scmPurSupplierSource,ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}
	
}
