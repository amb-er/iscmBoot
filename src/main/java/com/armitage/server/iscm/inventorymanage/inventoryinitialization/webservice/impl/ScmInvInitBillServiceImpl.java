package com.armitage.server.iscm.inventorymanage.inventoryinitialization.webservice.impl;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.util.ParamHelper;
import com.armitage.server.iscm.inventorymanage.inventoryinitialization.model.ScmInvInitBill2;
import com.armitage.server.iscm.inventorymanage.inventoryinitialization.model.ScmInvInitBillImPort;
import com.armitage.server.iscm.inventorymanage.inventoryinitialization.model.ScmInvInitBillWSBean;
import com.armitage.server.iscm.inventorymanage.inventoryinitialization.service.ScmInvInitBillBiz;
import com.armitage.server.iscm.inventorymanage.inventoryinitialization.webservice.ScmInvInitBillService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmInvInitBillService")
public class ScmInvInitBillServiceImpl extends BaseServiceImpl<ScmInvInitBillBiz, ScmInvInitBillWSBean> implements ScmInvInitBillService {

	@Override
	public ScmInvInitBillWSBean updateDirect(ScmInvInitBillWSBean bean) {
		return null;
	}

	@Override
	public ScmInvInitBillWSBean submit(ScmInvInitBillWSBean bean) {
		try {
			bean.setObject(biz.submit((ScmInvInitBill2)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmInvInitBillWSBean undoSubmit(ScmInvInitBillWSBean bean) {
		try {
			bean.setObject(biz.undoSubmit((ScmInvInitBill2)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}
	
	@Override
	public ScmInvInitBillWSBean postBillCheck(ScmInvInitBillWSBean bean) {
		try {
			ScmInvInitBill2 scmInvInitBill = (ScmInvInitBill2)bean.getObject();
			bean.setList(biz.postBillCheck(scmInvInitBill,ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmInvInitBillWSBean postBill(ScmInvInitBillWSBean bean) {
		try {
			ScmInvInitBill2 scmInvInitBill = (ScmInvInitBill2)bean.getObject();
			bean.setObject(biz.postBill(scmInvInitBill,ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmInvInitBillWSBean cancelPostBillCheck(ScmInvInitBillWSBean bean) {
		try {
			ScmInvInitBill2 scmInvInitBill = (ScmInvInitBill2)bean.getObject();
			bean.setList(biz.cancelPostBillCheck(scmInvInitBill,ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmInvInitBillWSBean cancelPostBill(ScmInvInitBillWSBean bean) {
		try {
			ScmInvInitBill2 scmInvInitBill = (ScmInvInitBill2)bean.getObject();
			bean.setObject(biz.cancelPostBill(scmInvInitBill,ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmInvInitBillWSBean importExcel(ScmInvInitBillWSBean bean) {
		try {
			bean.setObject(biz.importExcel((ScmInvInitBillImPort)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmInvInitBillWSBean updatePrtCount(ScmInvInitBillWSBean bean) {
		try {
			bean.setObject(biz.updatePrtCount((ScmInvInitBill2)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}
}
