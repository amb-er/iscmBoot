
package com.armitage.server.iscm.purchasemanage.purchasebusiness.webservice.impl;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.util.ParamHelper;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.CommonAuditParams;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurAuditParam;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurReturns2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurReturnsWSBean;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.service.ScmPurReturnsBiz;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.webservice.ScmPurReturnsService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmPurReturnsService")
public class ScmPurReturnsServiceImpl extends BaseServiceImpl<ScmPurReturnsBiz, ScmPurReturnsWSBean> implements ScmPurReturnsService {
	
	@Override
	public ScmPurReturnsWSBean submit(ScmPurReturnsWSBean bean) {
		try {
			ScmPurReturns2 scmPurReturns = (ScmPurReturns2)bean.getObject();
			bean.setObject(biz.submit(scmPurReturns,ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmPurReturnsWSBean undoSubmit(ScmPurReturnsWSBean bean) {
		try {
			ScmPurReturns2 scmPurReturns = (ScmPurReturns2)bean.getObject();
			bean.setObject(biz.undoSubmit(scmPurReturns,ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmPurReturnsWSBean release(ScmPurReturnsWSBean bean) {
		try {
			ScmPurReturns2 scmPurReturns = (ScmPurReturns2)bean.getObject();
			bean.setObject(biz.release(scmPurReturns,ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmPurReturnsWSBean undoRelease(ScmPurReturnsWSBean bean) {
		try {
			ScmPurReturns2 scmPurReturns = (ScmPurReturns2)bean.getObject();
			bean.setObject(biz.undoRelease(scmPurReturns,ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmPurReturnsWSBean audit(ScmPurReturnsWSBean bean) {
		try {
			bean.setList(biz.audit((ScmPurAuditParam)bean.getObject(),bean.getList(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmPurReturnsWSBean antiAudit(ScmPurReturnsWSBean bean) {
		try {
			bean.setList(biz.antiAudit(bean.getList(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmPurReturnsWSBean doAudit(ScmPurReturnsWSBean bean) {
		try {
			bean.setObject(biz.doAuditPurReturns((CommonAuditParams)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmPurReturnsWSBean undoAudit(ScmPurReturnsWSBean bean) {
		try {
			bean.setObject(biz.doUnAuditPurReturns((ScmPurReturns2)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmPurReturnsWSBean updatePrtCount(ScmPurReturnsWSBean bean) {
		try {
			bean.setObject(biz.updatePrtCount((ScmPurReturns2)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}
}
