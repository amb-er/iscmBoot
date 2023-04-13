package com.armitage.server.iscm.inventorymanage.warehouseinbusiness.webservice.impl;


import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.util.ParamHelper;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvOtherInWarehsBill2;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvOtherInWarehsBillWSBean;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.service.ScmInvOtherInWarehsBillBiz;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.webservice.ScmInvOtherInWarehsBillService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmInvOtherInWarehsBillService")
public class ScmInvOtherInWarehsBillServiceImpl extends BaseServiceImpl<ScmInvOtherInWarehsBillBiz, ScmInvOtherInWarehsBillWSBean> implements ScmInvOtherInWarehsBillService {
	@Override
	public ScmInvOtherInWarehsBillWSBean updateDirect(ScmInvOtherInWarehsBillWSBean bean) {
		return null;
	}

	@Override
	public ScmInvOtherInWarehsBillWSBean submit(ScmInvOtherInWarehsBillWSBean bean) {
		try {
			bean.setObject(biz.submit((ScmInvOtherInWarehsBill2)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmInvOtherInWarehsBillWSBean undoSubmit(ScmInvOtherInWarehsBillWSBean bean) {
		try {
			bean.setObject(biz.undoSubmit((ScmInvOtherInWarehsBill2)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}
	
	@Override
	public ScmInvOtherInWarehsBillWSBean postBillCheck(ScmInvOtherInWarehsBillWSBean bean) {
		try {
			ScmInvOtherInWarehsBill2 scmInvOtherInWarehsBill = (ScmInvOtherInWarehsBill2)bean.getObject();
			bean.setList(biz.postBillCheck(scmInvOtherInWarehsBill,ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmInvOtherInWarehsBillWSBean postBill(ScmInvOtherInWarehsBillWSBean bean) {
		try {
			ScmInvOtherInWarehsBill2 scmInvOtherInWarehsBill = (ScmInvOtherInWarehsBill2)bean.getObject();
			bean.setObject(biz.postBill(scmInvOtherInWarehsBill,ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}
	
	@Override
	public ScmInvOtherInWarehsBillWSBean cancelPostBillCheck(ScmInvOtherInWarehsBillWSBean bean) {
		try {
			ScmInvOtherInWarehsBill2 scmInvOtherInWarehsBill = (ScmInvOtherInWarehsBill2)bean.getObject();
			bean.setList(biz.cancelPostBillCheck(scmInvOtherInWarehsBill,ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmInvOtherInWarehsBillWSBean cancelPostBill(ScmInvOtherInWarehsBillWSBean bean) {
		try {
			ScmInvOtherInWarehsBill2 scmInvOtherInWarehsBill = (ScmInvOtherInWarehsBill2)bean.getObject();
			bean.setObject(biz.cancelPostBill(scmInvOtherInWarehsBill,ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmInvOtherInWarehsBillWSBean updatePrtCount(ScmInvOtherInWarehsBillWSBean bean) {
		try {
			bean.setObject(biz.updatePrtCount((ScmInvOtherInWarehsBill2)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}
}

