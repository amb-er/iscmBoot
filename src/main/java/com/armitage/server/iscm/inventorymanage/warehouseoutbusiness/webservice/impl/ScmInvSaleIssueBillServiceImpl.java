package com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.webservice.impl;

import java.util.List;

import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.util.CommonBeanHelper;
import com.armitage.server.common.util.ParamHelper;
import com.armitage.server.iscm.common.model.ScmDataCollectionStepState2;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvSaleIssueBill2;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvSaleIssueBillEntry2;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvSaleIssueBillWSBean;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.service.ScmInvSaleIssueBillBiz;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.webservice.ScmInvSaleIssueBillService;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.CommonAuditParams;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmInvSaleIssueBillService")
public class ScmInvSaleIssueBillServiceImpl extends BaseServiceImpl<ScmInvSaleIssueBillBiz, ScmInvSaleIssueBillWSBean> implements ScmInvSaleIssueBillService {

	@Override
	public ScmInvSaleIssueBillWSBean updateDirect(ScmInvSaleIssueBillWSBean bean) {
		return null;
	}

	@Override
	public ScmInvSaleIssueBillWSBean submit(ScmInvSaleIssueBillWSBean bean) {
		try {
			bean.setObject(biz.doSubmit((ScmInvSaleIssueBill2)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmInvSaleIssueBillWSBean undoSubmit(ScmInvSaleIssueBillWSBean bean) {
		try {
			bean.setObject(biz.doUnSubmit((ScmInvSaleIssueBill2)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

    @Override
    public ScmInvSaleIssueBillWSBean postBill(ScmInvSaleIssueBillWSBean bean) {
        try {
            ScmInvSaleIssueBill2 scmInvSaleIssueBill = (ScmInvSaleIssueBill2)bean.getObject();
            bean.setObject(biz.postBill(scmInvSaleIssueBill,ParamHelper.createParam(bean)));
        } catch (AppException e) {
            Message.inMessage(bean, e);
        } catch (Exception e) {
            Message.inMessage(bean, e);
        }
        return bean;
    }

    @Override
    public ScmInvSaleIssueBillWSBean postBillCheck(ScmInvSaleIssueBillWSBean bean) {
        try {
            ScmInvSaleIssueBill2 scmInvSaleIssueBill = (ScmInvSaleIssueBill2)bean.getObject();
            List<ScmInvSaleIssueBillEntry2> scmInvSaleIssueBillEntryList = (List<ScmInvSaleIssueBillEntry2>)bean.getList();
            bean.setList(biz.postBillCheck(scmInvSaleIssueBill, ParamHelper.createParam(bean)));
        } catch (AppException e) {
            Message.inMessage(bean, e);
        } catch (Exception e) {
            Message.inMessage(bean, e);
        }
        return bean;
    }

    @Override
    public ScmInvSaleIssueBillWSBean cancelPostBill(ScmInvSaleIssueBillWSBean bean) {
        try {
            ScmInvSaleIssueBill2 scmInvSaleIssueBill = (ScmInvSaleIssueBill2)bean.getObject();
            List<ScmInvSaleIssueBillEntry2> scmInvSaleIssueBillEntryList = (List<ScmInvSaleIssueBillEntry2>)bean.getList();
            bean.setObject(biz.cancelPostBill(scmInvSaleIssueBill,ParamHelper.createParam(bean)));
        } catch (AppException e) {
            Message.inMessage(bean, e);
        } catch (Exception e) {
            Message.inMessage(bean, e);
        }
        return bean;
    }

	@Override
	public ScmInvSaleIssueBillWSBean generateArInvoice(ScmInvSaleIssueBillWSBean bean) {
		try {
			Object stepState = bean.getObject();
			List scmInvSaleIssueBillList = bean.getList();
			Param param = ParamHelper.createParam(bean);
			CommonBeanHelper.toWSBean(biz.generateArInvoice((ScmDataCollectionStepState2) stepState, scmInvSaleIssueBillList, param), bean);
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmInvSaleIssueBillWSBean updatePrtCount(ScmInvSaleIssueBillWSBean bean) {
		try {
			bean.setObject(biz.updatePrtCount((ScmInvSaleIssueBill2)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmInvSaleIssueBillWSBean doAudit(ScmInvSaleIssueBillWSBean bean) {
			try {
				bean.setObject(biz.doAudit((CommonAuditParams)bean.getObject(),ParamHelper.createParam(bean)));
			} catch (AppException e) {
				Message.inMessage(bean, e);
			} catch (Exception e) {
				Message.inMessage(bean, e);
			}
			return bean;
	}

	@Override
	public ScmInvSaleIssueBillWSBean undoAudit(ScmInvSaleIssueBillWSBean bean) {
		try {
			bean.setObject(biz.doUnAudit((ScmInvSaleIssueBill2)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}
	
}
