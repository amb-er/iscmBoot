package com.armitage.server.iscm.inventorymanage.warehouseinbusiness.webservice.impl;


import java.util.List;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.util.ParamHelper;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvMoveBill2;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvMoveBillEntry2;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvMoveInWarehsBill2;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvMoveInWarehsBillEntry2;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvMoveInWarehsBillWSBean;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.service.ScmInvMoveInWarehsBillBiz;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.webservice.ScmInvMoveInWarehsBillService;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvMoveIssueBillEntry2;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmInvMoveInWarehsBillService")
public class ScmInvMoveInWarehsBillServiceImpl extends BaseServiceImpl<ScmInvMoveInWarehsBillBiz, ScmInvMoveInWarehsBillWSBean> implements ScmInvMoveInWarehsBillService {

    @Override
    public ScmInvMoveInWarehsBillWSBean updateDirect(ScmInvMoveInWarehsBillWSBean bean) {
        return null;
    }

    @Override
    public ScmInvMoveInWarehsBillWSBean submit(ScmInvMoveInWarehsBillWSBean bean) {
        try {
            bean.setObject(biz.submit((ScmInvMoveInWarehsBill2)bean.getObject(), ParamHelper.createParam(bean)));
        } catch (AppException e) {
            Message.inMessage(bean, e);
        } catch (Exception e) {
            Message.inMessage(bean, e);
        }
        return bean;
    }

    @Override
    public ScmInvMoveInWarehsBillWSBean undoSubmit(ScmInvMoveInWarehsBillWSBean bean) {
        try {
            bean.setObject(biz.undoSubmit((ScmInvMoveInWarehsBill2)bean.getObject(), ParamHelper.createParam(bean)));
        } catch (AppException e) {
            Message.inMessage(bean, e);
        } catch (Exception e) {
            Message.inMessage(bean, e);
        }
        return bean;
    }
    
    @Override
    public ScmInvMoveInWarehsBillWSBean postBill(ScmInvMoveInWarehsBillWSBean bean) {
        try {
            ScmInvMoveInWarehsBill2 scmInvMoveInWarehsBill = (ScmInvMoveInWarehsBill2)bean.getObject();
            bean.setObject(biz.postBill(scmInvMoveInWarehsBill,ParamHelper.createParam(bean)));
        } catch (AppException e) {
            Message.inMessage(bean, e);
        } catch (Exception e) {
            Message.inMessage(bean, e);
        }
        return bean;
    }

    @Override
    public ScmInvMoveInWarehsBillWSBean cancelPostBill(ScmInvMoveInWarehsBillWSBean bean) {
        try {
            ScmInvMoveInWarehsBill2 scmInvMoveInWarehsBill = (ScmInvMoveInWarehsBill2)bean.getObject();
            List<ScmInvMoveInWarehsBillEntry2> scmInvMoveInWarehsBillEntryList = (List<ScmInvMoveInWarehsBillEntry2>)bean.getList();
            bean.setObject(biz.cancelPostBill(scmInvMoveInWarehsBill,ParamHelper.createParam(bean)));
        } catch (AppException e) {
            Message.inMessage(bean, e);
        } catch (Exception e) {
            Message.inMessage(bean, e);
        }
        return bean;
    }

    @Override
    public ScmInvMoveInWarehsBillWSBean postBillCheck(ScmInvMoveInWarehsBillWSBean bean) {
        try {
            ScmInvMoveInWarehsBill2 scmInvMoveInWarehsBill = (ScmInvMoveInWarehsBill2)bean.getObject();
            bean.setList(biz.postBillCheck(scmInvMoveInWarehsBill,ParamHelper.createParam(bean)));
        } catch (AppException e) {
            Message.inMessage(bean, e);
        } catch (Exception e) {
            Message.inMessage(bean, e);
        }
        return bean;
    }

	@Override
	public ScmInvMoveInWarehsBillWSBean cancelPostBillCheck(
			ScmInvMoveInWarehsBillWSBean bean) {
		try {
            ScmInvMoveInWarehsBill2 scmInvMoveInWarehsBill = (ScmInvMoveInWarehsBill2)bean.getObject();
            bean.setList(biz.cancelPostBillCheck(scmInvMoveInWarehsBill,ParamHelper.createParam(bean)));
        } catch (AppException e) {
            Message.inMessage(bean, e);
        } catch (Exception e) {
            Message.inMessage(bean, e);
        }
        return bean;
	}

	@Override
	public ScmInvMoveInWarehsBillWSBean updatePrtCount(ScmInvMoveInWarehsBillWSBean bean) {
		try {
			bean.setObject(biz.updatePrtCount((ScmInvMoveInWarehsBill2)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}
	
}

