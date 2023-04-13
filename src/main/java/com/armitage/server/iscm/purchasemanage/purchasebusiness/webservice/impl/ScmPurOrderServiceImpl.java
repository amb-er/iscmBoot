package com.armitage.server.iscm.purchasemanage.purchasebusiness.webservice.impl;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.util.ParamHelper;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.CommonAuditParams;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurOrder2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurOrderAdvQuery;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurOrderWSBean;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.service.ScmPurOrderBiz;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.webservice.ScmPurOrderService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmPurOrderService")
public class ScmPurOrderServiceImpl extends BaseServiceImpl<ScmPurOrderBiz, ScmPurOrderWSBean> implements ScmPurOrderService {

	@Override
	public ScmPurOrderWSBean submit(ScmPurOrderWSBean bean) {
		try {
			ScmPurOrder2 scmPurOrder = (ScmPurOrder2)bean.getObject();
			bean.setObject(biz.submit(scmPurOrder,ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmPurOrderWSBean undoSubmit(ScmPurOrderWSBean bean) {
		try {
			ScmPurOrder2 scmPurOrder = (ScmPurOrder2)bean.getObject();
			bean.setObject(biz.undoSubmit(scmPurOrder,ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmPurOrderWSBean release(ScmPurOrderWSBean bean) {
		try {
			ScmPurOrder2 scmPurOrder = (ScmPurOrder2)bean.getObject();
			bean.setObject(biz.release(scmPurOrder,ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmPurOrderWSBean undoRelease(ScmPurOrderWSBean bean) {
		try {
			ScmPurOrder2 scmPurOrder = (ScmPurOrder2)bean.getObject();
			bean.setObject(biz.undoRelease(scmPurOrder,ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}
	
	@Override
	public ScmPurOrderWSBean sendOrder(ScmPurOrderWSBean bean) {
		try {
			biz.sendOrder((ScmPurOrder2)bean.getObject(),ParamHelper.createParam(bean));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmPurOrderWSBean unSendOrder(ScmPurOrderWSBean bean) {
		try {
			biz.unSendOrder((ScmPurOrder2)bean.getObject(),ParamHelper.createParam(bean));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmPurOrderWSBean doAudit(ScmPurOrderWSBean bean) {
		try {
			bean.setObject(biz.doAuditPurOrder((CommonAuditParams)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmPurOrderWSBean undoAudit(ScmPurOrderWSBean bean) {
		try {
			bean.setObject(biz.doUnAuditPurOrder((ScmPurOrder2)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmPurOrderWSBean getTotalOrderQty(ScmPurOrderWSBean bean) {
		try {
			bean.setObject(biz.getTotalOrderQty((ScmPurOrderAdvQuery)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmPurOrderWSBean unlockBill(ScmPurOrderWSBean bean) {
		try {
			ScmPurOrder2 scmPurOrder = (ScmPurOrder2)bean.getObject();
			bean.setObject(biz.unlockBill(scmPurOrder,ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmPurOrderWSBean selectDrillBills(ScmPurOrderWSBean bean) {
		try {
			ScmPurOrder2 scmPurOrder = (ScmPurOrder2)bean.getObject();
			bean.setList(biz.selectDrillBills(scmPurOrder,ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmPurOrderWSBean updatePrtCount(ScmPurOrderWSBean bean) {
		try {
			bean.setObject(biz.updatePrtCount((ScmPurOrder2)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

}

