package com.armitage.server.iscm.inventorymanage.AllocationApplication.webservice.impl;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.util.ParamHelper;
import com.armitage.server.iscm.inventorymanage.AllocationApplication.model.ScmInvMoveReqBill;
import com.armitage.server.iscm.inventorymanage.AllocationApplication.model.ScmInvMoveReqBillWSBean;
import com.armitage.server.iscm.inventorymanage.AllocationApplication.service.ScmInvMoveReqBillBiz;
import com.armitage.server.iscm.inventorymanage.AllocationApplication.webservice.ScmInvMoveReqBillService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmInvMoveReqBillService")
public class ScmInvMoveReqBillServiceImpl extends BaseServiceImpl<ScmInvMoveReqBillBiz, ScmInvMoveReqBillWSBean> implements ScmInvMoveReqBillService{
	
	@Override
	public ScmInvMoveReqBillWSBean submit(ScmInvMoveReqBillWSBean bean) {
		try {
			bean.setObject(biz.submit((ScmInvMoveReqBill)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmInvMoveReqBillWSBean undoSubmit(ScmInvMoveReqBillWSBean bean) {
		try {
			bean.setObject(biz.undoSubmit((ScmInvMoveReqBill)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmInvMoveReqBillWSBean release(ScmInvMoveReqBillWSBean bean) {
		try {
			bean.setObject(biz.release((ScmInvMoveReqBill)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmInvMoveReqBillWSBean undoRelease(ScmInvMoveReqBillWSBean bean) {
		try {
			bean.setObject(biz.undoRelease((ScmInvMoveReqBill)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmInvMoveReqBillWSBean finish(ScmInvMoveReqBillWSBean bean) {
		try {
			bean.setObject(biz.finish((ScmInvMoveReqBill)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmInvMoveReqBillWSBean undoFinish(ScmInvMoveReqBillWSBean bean) {
		try {
			bean.setObject(biz.undoFinish((ScmInvMoveReqBill)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}
	
	@Override
	public ScmInvMoveReqBillWSBean selectByReqId(ScmInvMoveReqBillWSBean bean) {
		try {
			long reqId = (Long)bean.getObject();
			bean.setList(biz.selectByReqId(reqId, ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmInvMoveReqBillWSBean updatePrtCount(ScmInvMoveReqBillWSBean bean) {
		try {
			bean.setObject(biz.updatePrtCount((ScmInvMoveReqBill)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

}
