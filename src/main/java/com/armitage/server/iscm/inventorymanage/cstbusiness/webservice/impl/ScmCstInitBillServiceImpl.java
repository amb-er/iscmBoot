package com.armitage.server.iscm.inventorymanage.cstbusiness.webservice.impl;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.util.ParamHelper;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmCstInitBill2;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmCstInitBillImPort;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmCstInitBillWSBean;
import com.armitage.server.iscm.inventorymanage.cstbusiness.service.ScmCstInitBillBiz;
import com.armitage.server.iscm.inventorymanage.cstbusiness.webservice.ScmCstInitBillService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmCstInitBillService")
public class ScmCstInitBillServiceImpl extends BaseServiceImpl<ScmCstInitBillBiz, ScmCstInitBillWSBean> implements ScmCstInitBillService {

	@Override
	public ScmCstInitBillWSBean updateDirect(ScmCstInitBillWSBean bean) {
		return null;
	}

	@Override
    public ScmCstInitBillWSBean submit(ScmCstInitBillWSBean bean) {
        
        try {
            bean.setObject(biz.submit((ScmCstInitBill2)bean.getObject(),ParamHelper.createParam(bean)));
        } catch (AppException e) {
            Message.inMessage(bean, e);
        } catch (Exception e) {
            Message.inMessage(bean, e);
        }
        return bean;
    }
    
    @Override
    public ScmCstInitBillWSBean undoSubmit(ScmCstInitBillWSBean bean) {
        
        try {
            bean.setObject(biz.undoSubmit((ScmCstInitBill2)bean.getObject(),ParamHelper.createParam(bean)));
        } catch (AppException e) {
            Message.inMessage(bean, e);
        } catch (Exception e) {
            Message.inMessage(bean, e);
        }
        return bean;
    }

	@Override
	public ScmCstInitBillWSBean postBillCheck(ScmCstInitBillWSBean bean) {
		try {
			ScmCstInitBill2 scmCstInitBill = (ScmCstInitBill2)bean.getObject();
			bean.setList(biz.postBillCheck(scmCstInitBill,ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmCstInitBillWSBean postBill(ScmCstInitBillWSBean bean) {
		try {
			ScmCstInitBill2 scmCstInitBill = (ScmCstInitBill2)bean.getObject();
			bean.setObject(biz.postBill(scmCstInitBill,ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmCstInitBillWSBean cancelPostBillCheck(ScmCstInitBillWSBean bean) {
		try {
			ScmCstInitBill2 scmCstInitBill = (ScmCstInitBill2)bean.getObject();
			bean.setList(biz.cancelPostBillCheck(scmCstInitBill,ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmCstInitBillWSBean cancelPostBill(ScmCstInitBillWSBean bean) {
		try {
			ScmCstInitBill2 scmCstInitBill = (ScmCstInitBill2)bean.getObject();
			bean.setObject(biz.cancelPostBill(scmCstInitBill,ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmCstInitBillWSBean importExcel(ScmCstInitBillWSBean bean) {
		try {
			bean.setObject(biz.importExcel((ScmCstInitBillImPort)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmCstInitBillWSBean updatePrtCount(ScmCstInitBillWSBean bean) {
		try {
			bean.setObject(biz.updatePrtCount((ScmCstInitBill2)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}
    
}