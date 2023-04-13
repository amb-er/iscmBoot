package com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.webservice.impl;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.util.ParamHelper;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvMaterialRequestBill2;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvMaterialRequestBillWSBean;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.service.ScmInvMaterialRequestBillBiz;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.webservice.ScmInvMaterialRequestBillService;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.CommonAuditParams;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmInvMaterialRequestBillService")
public class ScmInvMaterialRequestBillServiceImpl extends BaseServiceImpl<ScmInvMaterialRequestBillBiz, ScmInvMaterialRequestBillWSBean> implements ScmInvMaterialRequestBillService {
	
	@Override
	public ScmInvMaterialRequestBillWSBean submit(ScmInvMaterialRequestBillWSBean bean) {
		try {
			bean.setObject(biz.doSubmit((ScmInvMaterialRequestBill2)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmInvMaterialRequestBillWSBean undoSubmit(ScmInvMaterialRequestBillWSBean bean) {
		try {
			bean.setObject(biz.doUnSubmit((ScmInvMaterialRequestBill2)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmInvMaterialRequestBillWSBean release(
			ScmInvMaterialRequestBillWSBean bean) {
		try {
			bean.setObject(biz.release((ScmInvMaterialRequestBill2)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmInvMaterialRequestBillWSBean undoRelease(
			ScmInvMaterialRequestBillWSBean bean) {
		try {
			bean.setObject(biz.undoRelease((ScmInvMaterialRequestBill2)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmInvMaterialRequestBillWSBean finish(
			ScmInvMaterialRequestBillWSBean bean) {
		try {
			bean.setObject(biz.finish((ScmInvMaterialRequestBill2)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmInvMaterialRequestBillWSBean undoFinish(
			ScmInvMaterialRequestBillWSBean bean) {
		try {
			bean.setObject(biz.undoFinish((ScmInvMaterialRequestBill2)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}
	
	@Override
	public ScmInvMaterialRequestBillWSBean generateMaterialReqBill(
			ScmInvMaterialRequestBillWSBean bean) {
		try {
            biz.generateMaterialReqBill((ScmInvMaterialRequestBill2)bean.getObject(),ParamHelper.createParam(bean));
        } catch (AppException e) {
            Message.inMessage(bean, e);
        } catch (Exception e) {
            Message.inMessage(bean, e);
        }
        return bean;
	}

	@Override
	public ScmInvMaterialRequestBillWSBean doAudit(ScmInvMaterialRequestBillWSBean bean) {
		try {
			bean.setObject(biz.doAuditInvMaterialReq((CommonAuditParams)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmInvMaterialRequestBillWSBean undoAudit(ScmInvMaterialRequestBillWSBean bean) {
		try {
			bean.setObject(biz.doUnAuditInvMaterialReq((ScmInvMaterialRequestBill2)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}
	
	@Override
	public ScmInvMaterialRequestBillWSBean updatePrtCount(ScmInvMaterialRequestBillWSBean bean) {
		try {
			bean.setObject(biz.updatePrtCount((ScmInvMaterialRequestBill2)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}
	
	@Override
	public ScmInvMaterialRequestBillWSBean selectDrillBills(ScmInvMaterialRequestBillWSBean bean) {
		try {
			ScmInvMaterialRequestBill2 scmInvMaterialRequestBill = (ScmInvMaterialRequestBill2)bean.getObject();
			bean.setList(biz.selectDrillBills(scmInvMaterialRequestBill, ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}
}

