package com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.webservice.impl;

import java.util.List;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.util.ParamHelper;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvMoveIssueBill2;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvMoveIssueBillEntry2;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvMoveIssueBillWSBean;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.service.ScmInvMoveIssueBillBiz;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.webservice.ScmInvMoveIssueBillService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmInvMoveIssueBillService")
public class ScmInvMoveIssueBillServiceImpl  extends BaseServiceImpl<ScmInvMoveIssueBillBiz, ScmInvMoveIssueBillWSBean> implements ScmInvMoveIssueBillService{
	@Override
	public ScmInvMoveIssueBillWSBean updateDirect(ScmInvMoveIssueBillWSBean bean) {
		return null;
	}

	@Override
	public ScmInvMoveIssueBillWSBean submit(ScmInvMoveIssueBillWSBean bean) {
		try {
			bean.setObject(biz.submit((ScmInvMoveIssueBill2)bean.getObject(), ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}
	
	@Override
	public ScmInvMoveIssueBillWSBean undoSubmit(ScmInvMoveIssueBillWSBean bean) {
		try {
			bean.setObject(biz.undoSubmit((ScmInvMoveIssueBill2)bean.getObject(), ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}
	
	@Override
	public ScmInvMoveIssueBillWSBean postBill(ScmInvMoveIssueBillWSBean bean) {
		try {
			ScmInvMoveIssueBill2 scmInvMoveIssueBill = (ScmInvMoveIssueBill2)bean.getObject();
			bean.setObject(biz.postBill(scmInvMoveIssueBill,ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmInvMoveIssueBillWSBean postBillCheck(ScmInvMoveIssueBillWSBean bean) {
		try {
			ScmInvMoveIssueBill2 scmInvMoveIssueBill = (ScmInvMoveIssueBill2)bean.getObject();
            List<ScmInvMoveIssueBillEntry2> scmInvMoveIssueBillEntryList = (List<ScmInvMoveIssueBillEntry2>)bean.getList();
            bean.setList(biz.postBillCheck(scmInvMoveIssueBill,ParamHelper.createParam(bean)));
        } catch (AppException e) {
            Message.inMessage(bean, e);
        } catch (Exception e) {
            Message.inMessage(bean, e);
        }
        return bean;
	}

	@Override
	public ScmInvMoveIssueBillWSBean cancelPostBill(ScmInvMoveIssueBillWSBean bean) {
		 try {
			 ScmInvMoveIssueBill2 scmInvMoveIssueBill = (ScmInvMoveIssueBill2)bean.getObject();
			 List<ScmInvMoveIssueBillEntry2> scmInvMoveIssueBillEntryList = (List<ScmInvMoveIssueBillEntry2>)bean.getList();
			 bean.setObject(biz.cancelPostBill(scmInvMoveIssueBill,ParamHelper.createParam(bean)));
		 } catch (AppException e) {
			 Message.inMessage(bean, e);
		 } catch (Exception e) {
			 Message.inMessage(bean, e);
		 }
		 return bean;
	}

	@Override
	public ScmInvMoveIssueBillWSBean updatePrtCount(ScmInvMoveIssueBillWSBean bean) {
		try {
			bean.setObject(biz.updatePrtCount((ScmInvMoveIssueBill2)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}
}
