package com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.webservice.impl;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.util.ParamHelper;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvSalePrice;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvSalePriceAdd;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvSalePriceWSBean;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.service.ScmInvSalePriceBiz;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.webservice.ScmInvSalePriceService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmInvSalePriceService")
public class ScmInvSalePriceServiceImpl extends BaseServiceImpl<ScmInvSalePriceBiz, ScmInvSalePriceWSBean> implements ScmInvSalePriceService{
	
	@Override
	public ScmInvSalePriceWSBean submit(ScmInvSalePriceWSBean bean) {
		try {
			bean.setObject(biz.submit((ScmInvSalePrice)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmInvSalePriceWSBean undoSubmit(ScmInvSalePriceWSBean bean) {
		try {
			bean.setObject(biz.undoSubmit((ScmInvSalePrice)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmInvSalePriceWSBean release(ScmInvSalePriceWSBean bean) {
		try {
			bean.setObject(biz.release((ScmInvSalePrice)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmInvSalePriceWSBean undoRelease(ScmInvSalePriceWSBean bean) {
		try {
			bean.setObject(biz.undoRelease((ScmInvSalePrice)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmInvSalePriceWSBean finish(ScmInvSalePriceWSBean bean) {
		try {
			bean.setObject(biz.finish((ScmInvSalePrice)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmInvSalePriceWSBean undoFinish(ScmInvSalePriceWSBean bean) {
		try {
			bean.setObject(biz.undoFinish((ScmInvSalePrice)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmInvSalePriceWSBean importInvSalePrice(ScmInvSalePriceWSBean bean) {
		try {
			ScmInvSalePriceAdd scmInvSalePriceAdd = (ScmInvSalePriceAdd)bean.getObject();
			bean.setObject(biz.doAddInvSalePrice(scmInvSalePriceAdd,ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmInvSalePriceWSBean updatePrtCount(ScmInvSalePriceWSBean bean) {
		try {
			bean.setObject(biz.updatePrtCount((ScmInvSalePrice)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}
}
