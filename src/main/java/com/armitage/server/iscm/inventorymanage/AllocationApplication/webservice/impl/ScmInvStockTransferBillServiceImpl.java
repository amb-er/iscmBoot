package com.armitage.server.iscm.inventorymanage.AllocationApplication.webservice.impl;

import java.util.List;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.util.ParamHelper;
import com.armitage.server.iscm.inventorymanage.AllocationApplication.model.ScmInvStockTransferBill2;
import com.armitage.server.iscm.inventorymanage.AllocationApplication.model.ScmInvStockTransferBillEntry2;
import com.armitage.server.iscm.inventorymanage.AllocationApplication.model.ScmInvStockTransferBillWSBean;
import com.armitage.server.iscm.inventorymanage.AllocationApplication.service.ScmInvStockTransferBillBiz;
import com.armitage.server.iscm.inventorymanage.AllocationApplication.webservice.ScmInvStockTransferBillService;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurOrder2;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmInvStockTransferBillService")
public class ScmInvStockTransferBillServiceImpl extends BaseServiceImpl<ScmInvStockTransferBillBiz, ScmInvStockTransferBillWSBean> implements ScmInvStockTransferBillService {
	
	@Override
	public ScmInvStockTransferBillWSBean updateStatus(ScmInvStockTransferBillWSBean bean) {
		try {
			ScmInvStockTransferBill2 scmInvStockTransferBill = (ScmInvStockTransferBill2)bean.getObject();
			List<ScmInvStockTransferBillEntry2> ScmInvStockTransferBillEntryList = (List<ScmInvStockTransferBillEntry2>)bean.getList();
			bean.setObject(biz.updateStatus(scmInvStockTransferBill,ScmInvStockTransferBillEntryList,ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

    @Override
    public ScmInvStockTransferBillWSBean generateMoveIssue(ScmInvStockTransferBillWSBean bean) {
        try {
            biz.generateMoveIssue((ScmInvStockTransferBill2)bean.getObject(),ParamHelper.createParam(bean));
        } catch (AppException e) {
            Message.inMessage(bean, e);
        } catch (Exception e) {
            Message.inMessage(bean, e);
        }
        return bean;
    }

	@Override
	public ScmInvStockTransferBillWSBean updatePrtCount(ScmInvStockTransferBillWSBean bean) {
		try {
			bean.setObject(biz.updatePrtCount((ScmInvStockTransferBill2)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}
}