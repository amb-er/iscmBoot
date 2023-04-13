package com.armitage.server.iscm.inventorymanage.AllocationApplication.webservice.impl;

import java.util.List;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.util.ParamHelper;
import com.armitage.server.iscm.inventorymanage.AllocationApplication.model.ScmInvMoveReqBillEntry2;
import com.armitage.server.iscm.inventorymanage.AllocationApplication.model.ScmInvMoveReqBillEntryWSBean;
import com.armitage.server.iscm.inventorymanage.AllocationApplication.model.ScmInvMoveReqBillWSBean;
import com.armitage.server.iscm.inventorymanage.AllocationApplication.service.ScmInvMoveReqBillEntryBiz;
import com.armitage.server.iscm.inventorymanage.AllocationApplication.webservice.ScmInvMoveReqBillEntryService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmInvMoveReqBillEntryService")
public class ScmInvMoveReqBillEntryServiceImpl extends BaseServiceImpl<ScmInvMoveReqBillEntryBiz, ScmInvMoveReqBillEntryWSBean> implements ScmInvMoveReqBillEntryService{

	@Override
	public ScmInvMoveReqBillEntryWSBean findGrantPage(ScmInvMoveReqBillEntryWSBean bean) {
		try {
			bean.setList(biz.findGrantPage(bean.getPage(), ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmInvMoveReqBillEntryWSBean updateStatus(ScmInvMoveReqBillEntryWSBean bean) {
		try {
			List<ScmInvMoveReqBillEntry2> scmInvMoveReqBillEntryList = (List<ScmInvMoveReqBillEntry2>)bean.getList();
			bean.setList(biz.updateStatus(scmInvMoveReqBillEntryList,ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmInvMoveReqBillEntryWSBean pushBill(ScmInvMoveReqBillEntryWSBean bean) {
		try {
			List<ScmInvMoveReqBillEntry2> scmInvMoveReqBillEntryList = (List<ScmInvMoveReqBillEntry2>)bean.getList();
			bean.setList(biz.pushBill(scmInvMoveReqBillEntryList,ParamHelper.createParam(bean)));
			
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

}
