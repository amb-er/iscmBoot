package com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.webservice.impl;

import java.util.List;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.util.ParamHelper;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvOtherIssueBill2;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvOtherIssueBillEntry2;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvOtherIssueBillWSBean;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.service.ScmInvOtherIssueBillBiz;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.webservice.ScmInvOtherIssueBillService;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.CommonAuditParams;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmInvOtherIssueBillService")
public class ScmInvOtherIssueBillServiceImpl  extends BaseServiceImpl<ScmInvOtherIssueBillBiz, ScmInvOtherIssueBillWSBean> implements ScmInvOtherIssueBillService{
	@Override
	public ScmInvOtherIssueBillWSBean updateDirect(ScmInvOtherIssueBillWSBean bean) {
		return null;
	}

	@Override
	public ScmInvOtherIssueBillWSBean submit(ScmInvOtherIssueBillWSBean bean) {
		try {
			bean.setObject(biz.submit((ScmInvOtherIssueBill2)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}
	
	@Override
	public ScmInvOtherIssueBillWSBean undoSubmit(ScmInvOtherIssueBillWSBean bean) {
		try {
			bean.setObject(biz.undoSubmit((ScmInvOtherIssueBill2)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}
	
	@Override
	public ScmInvOtherIssueBillWSBean postBill(ScmInvOtherIssueBillWSBean bean) {
		try {
			ScmInvOtherIssueBill2 scmInvOtherIssueBill = (ScmInvOtherIssueBill2)bean.getObject();
			bean.setObject(biz.postBill(scmInvOtherIssueBill,ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmInvOtherIssueBillWSBean postBillCheck(ScmInvOtherIssueBillWSBean bean) {
		try {
			ScmInvOtherIssueBill2 scmInvOtherIssueBill = (ScmInvOtherIssueBill2)bean.getObject();
            List<ScmInvOtherIssueBillEntry2> scmInvOtherIssueBillEntryList = (List<ScmInvOtherIssueBillEntry2>)bean.getList();
            bean.setList(biz.postBillCheck(scmInvOtherIssueBill,ParamHelper.createParam(bean)));
        } catch (AppException e) {
            Message.inMessage(bean, e);
        } catch (Exception e) {
            Message.inMessage(bean, e);
        }
        return bean;
	}

	@Override
	public ScmInvOtherIssueBillWSBean cancelPostBill(ScmInvOtherIssueBillWSBean bean) {
		 try {
			 ScmInvOtherIssueBill2 scmInvOtherIssueBill = (ScmInvOtherIssueBill2)bean.getObject();
			 List<ScmInvOtherIssueBillEntry2> scmInvOtherIssueBillEntryList = (List<ScmInvOtherIssueBillEntry2>)bean.getList();
			 bean.setObject(biz.cancelPostBill(scmInvOtherIssueBill,ParamHelper.createParam(bean)));
		 } catch (AppException e) {
			 Message.inMessage(bean, e);
		 } catch (Exception e) {
			 Message.inMessage(bean, e);
		 }
		 return bean;
	}

	@Override
	public ScmInvOtherIssueBillWSBean doAudit(ScmInvOtherIssueBillWSBean bean) {
		try {
			bean.setObject(biz.doAuditInvOtherIssue((CommonAuditParams)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmInvOtherIssueBillWSBean undoAudit(ScmInvOtherIssueBillWSBean bean) {
		try {
			bean.setObject(biz.doUnAuditInvOtherIssue((ScmInvOtherIssueBill2)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmInvOtherIssueBillWSBean updatePrtCount(ScmInvOtherIssueBillWSBean bean) {
		try {
			bean.setObject(biz.updatePrtCount((ScmInvOtherIssueBill2)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}
}
