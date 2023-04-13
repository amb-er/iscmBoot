package com.armitage.server.iscm.inventorymanage.cstbusiness.webservice.impl;


import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.util.ParamHelper;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCostConsume2;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCostConsumeImPort;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCostConsumeWSBean;
import com.armitage.server.iscm.inventorymanage.cstbusiness.service.ScmInvCostConsumeBiz;
import com.armitage.server.iscm.inventorymanage.cstbusiness.webservice.ScmInvCostConsumeService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmInvCostConsumeService")
public class ScmInvCostConsumeServiceImpl extends BaseServiceImpl<ScmInvCostConsumeBiz, ScmInvCostConsumeWSBean> implements ScmInvCostConsumeService {

	@Override
	public ScmInvCostConsumeWSBean updateDirect(ScmInvCostConsumeWSBean bean) {
		return null;
	}

	@Override
    public ScmInvCostConsumeWSBean submit(ScmInvCostConsumeWSBean bean) {
        try {
            bean.setObject(biz.submit((ScmInvCostConsume2)bean.getObject(),ParamHelper.createParam(bean)));
        } catch (AppException e) {
            Message.inMessage(bean, e);
        } catch (Exception e) {
            Message.inMessage(bean, e);
        }
        return bean;
    }
    
    @Override
    public ScmInvCostConsumeWSBean undoSubmit(ScmInvCostConsumeWSBean bean) {
        try {
            bean.setObject(biz.undoSubmit((ScmInvCostConsume2)bean.getObject(),ParamHelper.createParam(bean)));
        } catch (AppException e) {
            Message.inMessage(bean, e);
        } catch (Exception e) {
            Message.inMessage(bean, e);
        }
        return bean;
    }

	@Override
	public ScmInvCostConsumeWSBean postBillCheck(ScmInvCostConsumeWSBean bean) {
		try {
			ScmInvCostConsume2 scmInvCostConsume = (ScmInvCostConsume2)bean.getObject();
			bean.setList(biz.postBillCheck(scmInvCostConsume,ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmInvCostConsumeWSBean postBill(ScmInvCostConsumeWSBean bean) {
		try {
			ScmInvCostConsume2 scmCostConsume = (ScmInvCostConsume2)bean.getObject();
			bean.setObject(biz.postBill(scmCostConsume,ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmInvCostConsumeWSBean cancelPostBillCheck(
			ScmInvCostConsumeWSBean bean) {
		try {
			ScmInvCostConsume2 scmInvCostConsume = (ScmInvCostConsume2)bean.getObject();
			bean.setList(biz.cancelPostBillCheck(scmInvCostConsume,ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmInvCostConsumeWSBean cancelPostBill(ScmInvCostConsumeWSBean bean) {
		try {
			ScmInvCostConsume2 scmInvCostConsume = (ScmInvCostConsume2)bean.getObject();
			bean.setObject(biz.cancelPostBill(scmInvCostConsume,ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

    @Override
    public ScmInvCostConsumeWSBean importExcel(ScmInvCostConsumeWSBean bean) {
        try {
            bean.setObject(biz.importExcel((ScmInvCostConsumeImPort)bean.getObject(),ParamHelper.createParam(bean)));
        } catch (AppException e) {
            Message.inMessage(bean, e);
        } catch (Exception e) {
            Message.inMessage(bean, e);
        }
        return bean;
    }

	@Override
	public ScmInvCostConsumeWSBean updatePrtCount(ScmInvCostConsumeWSBean bean) {
		try {
			bean.setObject(biz.updatePrtCount((ScmInvCostConsume2)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}
	
}
