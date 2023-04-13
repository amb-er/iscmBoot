
package com.armitage.server.iscm.purchasemanage.pricemanage.webservice.impl;

import java.util.List;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.util.ParamHelper;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmInvPriceQuery;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmMaterialPrice;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmMaterialPriceWSBean;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurPriceAllQuery;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurPriceQuery;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurSupplyInfoQuery;
import com.armitage.server.iscm.purchasemanage.pricemanage.service.ScmMaterialPriceBiz;
import com.armitage.server.iscm.purchasemanage.pricemanage.webservice.ScmMaterialPriceService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmMaterialPriceService")
public class ScmMaterialPriceServiceImpl extends BaseServiceImpl<ScmMaterialPriceBiz, ScmMaterialPriceWSBean> implements ScmMaterialPriceService {

	@Override
	public ScmMaterialPriceWSBean getMaterialPrice(ScmMaterialPriceWSBean bean) {
		try {
			bean.setList(biz.getMaterialPrice((ScmPurPriceQuery)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmMaterialPriceWSBean getMaterialPriceAll(ScmMaterialPriceWSBean bean) {
		try {
			bean.setObject(biz.getMaterialPriceAll((ScmPurPriceAllQuery)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmMaterialPriceWSBean getLastQuotationPrice(
			ScmMaterialPriceWSBean bean) {
		try {
			bean.setList(biz.getLastQuotationPrice((ScmPurPriceQuery)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmMaterialPriceWSBean getMaterialSalePrice(
			ScmMaterialPriceWSBean bean) {
		try {
			bean.setList(biz.getMaterialSalePrice((ScmInvPriceQuery)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmMaterialPriceWSBean getMaterialSupplyInfo(
			ScmMaterialPriceWSBean bean) {
		try {
			bean.setObject(biz.getMaterialSupplyInfo((ScmPurSupplyInfoQuery)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmMaterialPriceWSBean getMaterialSupplyInfoList(ScmMaterialPriceWSBean bean) {
		try {
			List<ScmMaterialPrice> scmMaterialPrices = biz.getMaterialSupplyInfoList((ScmPurSupplyInfoQuery)bean.getObject(),ParamHelper.createParam(bean));
			bean.setList(scmMaterialPrices);
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}
	
	@Override
	public ScmMaterialPriceWSBean getMaterialSupplyInfos(ScmMaterialPriceWSBean bean) {
		try {
			bean.setList(biz.getMaterialSupplyInfos((ScmPurSupplyInfoQuery)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmMaterialPriceWSBean getMaterialPriceAllList(ScmMaterialPriceWSBean bean) {
		try {
			bean.setList(biz.getMaterialPriceAllList((ScmPurPriceAllQuery)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmMaterialPriceWSBean getMaterialPreParePrice(ScmMaterialPriceWSBean bean) {
		try {
			bean.setList(biz.getPreParePrice((ScmPurPriceQuery)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmMaterialPriceWSBean getMaterialPriceByItemidsAndVendorIdsList(ScmMaterialPriceWSBean bean) {
		try {
			bean.setList(biz.getMaterialPriceByItemidsAndVendorIdsList(bean.getList(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmMaterialPriceWSBean getRecentPriceAndStock(ScmMaterialPriceWSBean bean) {
		try {
			bean.setObject(biz.getRecentPriceAndStock((ScmInvPriceQuery)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmMaterialPriceWSBean selectCostPrice(ScmMaterialPriceWSBean bean) {
		try {
			bean.setObject(biz.selectCostPrice((long)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmMaterialPriceWSBean getRecentPriceAndStockList(ScmMaterialPriceWSBean bean) {
		try {
			bean.setList(biz.getRecentPriceAndStockList((ScmInvPriceQuery) bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}
	
}
