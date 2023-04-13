package com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.webservice.impl;


import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.util.ParamHelper;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvMaterialReqBill2;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvMaterialReqBillWSBean;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.service.ScmInvMaterialReqBillBiz;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.webservice.ScmInvMaterialReqBillService;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.CommonAuditParams;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmInvMaterialReqBillService")
public class ScmInvMaterialReqBillServiceImpl extends BaseServiceImpl<ScmInvMaterialReqBillBiz, ScmInvMaterialReqBillWSBean> implements ScmInvMaterialReqBillService {

	@Override
	public ScmInvMaterialReqBillWSBean updateDirect(ScmInvMaterialReqBillWSBean bean) {
		return null;
	}

	@Override
	public ScmInvMaterialReqBillWSBean submit(ScmInvMaterialReqBillWSBean bean) {
		try {
			bean.setObject(biz.doSubmitInvMaterialReq((ScmInvMaterialReqBill2)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}
	
	@Override
	public ScmInvMaterialReqBillWSBean undoSubmit(ScmInvMaterialReqBillWSBean bean) {
		try {
			bean.setObject(biz.doUnSubmitInvMaterialReq((ScmInvMaterialReqBill2)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmInvMaterialReqBillWSBean postBillCheck(ScmInvMaterialReqBillWSBean bean) {
		try {
			ScmInvMaterialReqBill2 scmInvMaterialReqBill = (ScmInvMaterialReqBill2)bean.getObject();
			bean.setList(biz.postBillCheck(scmInvMaterialReqBill,ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}
	
	@Override
	public ScmInvMaterialReqBillWSBean postBill(ScmInvMaterialReqBillWSBean bean) {
		try {
			ScmInvMaterialReqBill2 scmInvMaterialReqBill = (ScmInvMaterialReqBill2)bean.getObject();
			bean.setObject(biz.postBill(scmInvMaterialReqBill,ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmInvMaterialReqBillWSBean cancelPostBill(ScmInvMaterialReqBillWSBean bean) {
		try {
			ScmInvMaterialReqBill2 scmInvMaterialReqBill = (ScmInvMaterialReqBill2)bean.getObject();
			bean.setObject(biz.cancelPostBill(scmInvMaterialReqBill,ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmInvMaterialReqBillWSBean cancelPostBillCheck(ScmInvMaterialReqBillWSBean bean) {
		try {
			bean.setList(biz.cancelPostBillCheck((ScmInvMaterialReqBill2)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmInvMaterialReqBillWSBean doAudit(ScmInvMaterialReqBillWSBean bean) {
		try {
			bean.setObject(biz.doAuditInvMaterialReqBill((CommonAuditParams)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmInvMaterialReqBillWSBean undoAudit(ScmInvMaterialReqBillWSBean bean) {
		try {
			bean.setObject(biz.doUnAuditInvMaterialReqBill((ScmInvMaterialReqBill2)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmInvMaterialReqBillWSBean selectDrillBills(ScmInvMaterialReqBillWSBean bean) {
		try {
			ScmInvMaterialReqBill2 scmInvMaterialReqBill = (ScmInvMaterialReqBill2)bean.getObject();
			bean.setList(biz.selectDrillBills(scmInvMaterialReqBill, ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmInvMaterialReqBillWSBean updatePrtCount(ScmInvMaterialReqBillWSBean bean) {
		try {
			bean.setObject(biz.updatePrtCount((ScmInvMaterialReqBill2)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}
}

