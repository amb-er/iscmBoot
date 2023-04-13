package com.armitage.server.iscm.inventorymanage.warehouseinbusiness.webservice.impl;


import java.util.List;

import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.util.CommonBeanHelper;
import com.armitage.server.common.util.ParamHelper;
import com.armitage.server.iscm.common.model.ScmDataCollectionStepState2;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvPurInWarehsBill2;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvPurInWarehsBillAdvQuery;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvPurInWarehsBillWSBean;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.service.ScmInvPurInWarehsBillBiz;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.webservice.ScmInvPurInWarehsBillService;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.CommonAuditParams;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmInvPurInWarehsBillService")
public class ScmInvPurInWarehsBillServiceImpl extends BaseServiceImpl<ScmInvPurInWarehsBillBiz, ScmInvPurInWarehsBillWSBean> implements ScmInvPurInWarehsBillService {
	@Override
	public ScmInvPurInWarehsBillWSBean submit(ScmInvPurInWarehsBillWSBean bean) {
		try {
			bean.setList(biz.submit(bean.getList(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmInvPurInWarehsBillWSBean updateDirect(ScmInvPurInWarehsBillWSBean bean) {
		return null;
	}

	@Override
	public ScmInvPurInWarehsBillWSBean undoSubmit(ScmInvPurInWarehsBillWSBean	 bean) {
		try {
			bean.setList(biz.undoSubmit(bean.getList(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}
	
	@Override
	public ScmInvPurInWarehsBillWSBean postBillCheck(ScmInvPurInWarehsBillWSBean bean) {
		try {
			ScmInvPurInWarehsBill2 scmInvPurInWarehsBill = (ScmInvPurInWarehsBill2)bean.getObject();
			bean.setList(biz.postBillCheck(scmInvPurInWarehsBill,ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmInvPurInWarehsBillWSBean postBill(ScmInvPurInWarehsBillWSBean bean) {
		try {
		bean.setList(biz.postBill((ScmInvPurInWarehsBill2)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}
	
	@Override
	public ScmInvPurInWarehsBillWSBean cancelPostBillCheck(ScmInvPurInWarehsBillWSBean bean) {
		try {
			ScmInvPurInWarehsBill2 scmInvPurInWarehsBill = (ScmInvPurInWarehsBill2)bean.getObject();
			bean.setList(biz.cancelPostBillCheck(scmInvPurInWarehsBill,ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmInvPurInWarehsBillWSBean cancelPostBill(ScmInvPurInWarehsBillWSBean bean) {
		try {
			ScmInvPurInWarehsBill2 scmInvPurInWarehsBill = (ScmInvPurInWarehsBill2)bean.getObject();
			bean.setObject(biz.cancelPostBill(scmInvPurInWarehsBill,ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

    @Override
    public ScmInvPurInWarehsBillWSBean generateApInvoice(ScmInvPurInWarehsBillWSBean bean) {
        try {
            bean.setList(biz.generateApInvoice(bean.getList(),ParamHelper.createParam(bean)));
        } catch (AppException e) {
            Message.inMessage(bean, e);
        } catch (Exception e) {
            Message.inMessage(bean, e);
        }
        return bean;
    }

	@Override
	public ScmInvPurInWarehsBillWSBean doAudit(ScmInvPurInWarehsBillWSBean bean) {
		try {
			bean.setObject(biz.doAuditInvPurInWarehs((CommonAuditParams)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmInvPurInWarehsBillWSBean undoAudit(ScmInvPurInWarehsBillWSBean bean) {
		try {
			bean.setObject(biz.doUnAuditInvPurInWarehs((ScmInvPurInWarehsBill2)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmInvPurInWarehsBillWSBean getTotalPurInWarehsQty(
			ScmInvPurInWarehsBillWSBean bean) {
		try {
			bean.setObject(biz.getTotalPurInWarehsQty((ScmInvPurInWarehsBillAdvQuery)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmInvPurInWarehsBillWSBean asyngenerateApInvoice(ScmInvPurInWarehsBillWSBean bean) {
		try {
			Object stepState = bean.getObject();
			List scmInvPurInWarehsBillList = bean.getList();
			Param param = ParamHelper.createParam(bean);
			CommonBeanHelper.toWSBean(biz.asyngenerateApInvoice((ScmDataCollectionStepState2) stepState, scmInvPurInWarehsBillList, param), bean);
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}
	
	@Override
	public ScmInvPurInWarehsBillWSBean getTotalPurInWarehsQtyByItems(ScmInvPurInWarehsBillWSBean bean) {
		try {
			bean.setList(biz.getTotalPurInWarehsQtyByItems((ScmInvPurInWarehsBillAdvQuery)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}
	
	@Override
	public ScmInvPurInWarehsBillWSBean selectDrillBills(ScmInvPurInWarehsBillWSBean bean) {
		try {
			ScmInvPurInWarehsBill2 scmInvPurInWarehsBill = (ScmInvPurInWarehsBill2)bean.getObject();
			bean.setList(biz.selectDrillBills(scmInvPurInWarehsBill,ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmInvPurInWarehsBillWSBean updatePrtCount(ScmInvPurInWarehsBillWSBean bean) {
		try {
			bean.setObject(biz.updatePrtCount((ScmInvPurInWarehsBill2)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}
}

