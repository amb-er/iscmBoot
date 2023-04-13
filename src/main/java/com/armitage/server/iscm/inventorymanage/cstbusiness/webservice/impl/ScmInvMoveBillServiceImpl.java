package com.armitage.server.iscm.inventorymanage.cstbusiness.webservice.impl;

import java.util.List;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.util.ParamHelper;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvMoveBill2;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvMoveBillEntry2;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvMoveBillWSBean;
import com.armitage.server.iscm.inventorymanage.cstbusiness.service.ScmInvMoveBillBiz;
import com.armitage.server.iscm.inventorymanage.cstbusiness.webservice.ScmInvMoveBillService;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.CommonAuditParams;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmInvMoveBillService")
public class ScmInvMoveBillServiceImpl extends BaseServiceImpl<ScmInvMoveBillBiz, ScmInvMoveBillWSBean> implements ScmInvMoveBillService {

    @Override
    public ScmInvMoveBillWSBean updateDirect(ScmInvMoveBillWSBean bean) {
        return null;
    }

    @Override
    public ScmInvMoveBillWSBean submit(ScmInvMoveBillWSBean bean) {
        try {
            bean.setObject(biz.submit((ScmInvMoveBill2)bean.getObject(),ParamHelper.createParam(bean)));
        } catch (AppException e) {
            Message.inMessage(bean, e);
        } catch (Exception e) {
            Message.inMessage(bean, e);
        }
        return bean;
    }

    @Override
    public ScmInvMoveBillWSBean undoSubmit(ScmInvMoveBillWSBean bean) {
        try {
            bean.setObject(biz.undoSubmit((ScmInvMoveBill2)bean.getObject(),ParamHelper.createParam(bean)));
        } catch (AppException e) {
            Message.inMessage(bean, e);
        } catch (Exception e) {
            Message.inMessage(bean, e);
        }
        return bean;
    }
    
    @Override
    public ScmInvMoveBillWSBean postBillCheck(ScmInvMoveBillWSBean bean) {
        try {
            ScmInvMoveBill2 scmInvMoveBill = (ScmInvMoveBill2)bean.getObject();
            bean.setList(biz.postBillCheck(scmInvMoveBill,"1",ParamHelper.createParam(bean)));
        } catch (AppException e) {
            Message.inMessage(bean, e);
        } catch (Exception e) {
            Message.inMessage(bean, e);
        }
        return bean;
    }

    @Override
    public ScmInvMoveBillWSBean postBill(ScmInvMoveBillWSBean bean) {
        try {
            ScmInvMoveBill2 scmInvMoveBill = (ScmInvMoveBill2)bean.getObject();
            List<ScmInvMoveBillEntry2> scmInvMoveBillEntryList = (List<ScmInvMoveBillEntry2>)bean.getList();
            bean.setObject(biz.postBill(scmInvMoveBill,ParamHelper.createParam(bean)));
        } catch (AppException e) {
            Message.inMessage(bean, e);
        } catch (Exception e) {
            Message.inMessage(bean, e);
        }
        return bean;
    }

    @Override
    public ScmInvMoveBillWSBean cancelPostBill(ScmInvMoveBillWSBean bean) {
        try {
            ScmInvMoveBill2 scmInvMoveBill = (ScmInvMoveBill2)bean.getObject();
            List<ScmInvMoveBillEntry2> scmInvMoveBillEntryList = (List<ScmInvMoveBillEntry2>)bean.getList();
            bean.setObject(biz.cancelPostBill(scmInvMoveBill,ParamHelper.createParam(bean)));
        } catch (AppException e) {
            Message.inMessage(bean, e);
        } catch (Exception e) {
            Message.inMessage(bean, e);
        }
        return bean;
    }

	@Override
	public ScmInvMoveBillWSBean cancelPostBillCheck(ScmInvMoveBillWSBean bean) {
		try {
            ScmInvMoveBill2 scmInvMoveBill = (ScmInvMoveBill2)bean.getObject();
            List<ScmInvMoveBillEntry2> scmInvMoveBillEntryList = (List<ScmInvMoveBillEntry2>)bean.getList();
            bean.setList(biz.postBillCheck(scmInvMoveBill,"2",ParamHelper.createParam(bean)));
        } catch (AppException e) {
            Message.inMessage(bean, e);
        } catch (Exception e) {
            Message.inMessage(bean, e);
        }
        return bean;
	}

	@Override
	public ScmInvMoveBillWSBean doAudit(ScmInvMoveBillWSBean bean) {
		try {
			bean.setObject(biz.doAuditInvMove((CommonAuditParams)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmInvMoveBillWSBean undoAudit(ScmInvMoveBillWSBean bean) {
		try {
			bean.setObject(biz.doUnAuditInvMove((ScmInvMoveBill2)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmInvMoveBillWSBean updatePrtCount(ScmInvMoveBillWSBean bean) {
		try {
			bean.setObject(biz.updatePrtCount((ScmInvMoveBill2)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}
	
}
