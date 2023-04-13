
package com.armitage.server.iscm.purchasemanage.pricemanage.webservice.impl;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.util.ParamHelper;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvMaterialRequestBill2;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurMarketPrice2;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurMarketPriceWSBean;
import com.armitage.server.iscm.purchasemanage.pricemanage.service.ScmPurMarketPriceBiz;
import com.armitage.server.iscm.purchasemanage.pricemanage.webservice.ScmPurMarketPriceService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmPurMarketPriceService")
public class ScmPurMarketPriceServiceImpl extends BaseServiceImpl<ScmPurMarketPriceBiz, ScmPurMarketPriceWSBean> implements ScmPurMarketPriceService {

	@Override
	public ScmPurMarketPriceWSBean submit(ScmPurMarketPriceWSBean bean) {
		try {
			bean.setObject(biz.submit((ScmPurMarketPrice2)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmPurMarketPriceWSBean undoSubmit(ScmPurMarketPriceWSBean bean) {
		try {
			bean.setObject(biz.undoSubmit((ScmPurMarketPrice2)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmPurMarketPriceWSBean release(ScmPurMarketPriceWSBean bean) {
		try {
			bean.setObject(biz.release((ScmPurMarketPrice2)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmPurMarketPriceWSBean undoRelease(	ScmPurMarketPriceWSBean bean) {
		try {
			bean.setObject(biz.undoRelease((ScmPurMarketPrice2)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

    @Override
    public ScmPurMarketPriceWSBean finish(ScmPurMarketPriceWSBean bean) {
        try {
            bean.setObject(biz.finish((ScmPurMarketPrice2)bean.getObject(),ParamHelper.createParam(bean)));
        } catch (AppException e) {
            Message.inMessage(bean, e);
        } catch (Exception e) {
            Message.inMessage(bean, e);
        }
        return bean;
    }

    @Override
    public ScmPurMarketPriceWSBean undoFinish(ScmPurMarketPriceWSBean bean) {
        try {
            bean.setObject(biz.undoFinish((ScmPurMarketPrice2)bean.getObject(),ParamHelper.createParam(bean)));
        } catch (AppException e) {
            Message.inMessage(bean, e);
        } catch (Exception e) {
            Message.inMessage(bean, e);
        }
        return bean;
    }

	@Override
	public ScmPurMarketPriceWSBean updatePrtCount(ScmPurMarketPriceWSBean bean) {
		try {
			bean.setObject(biz.updatePrtCount((ScmPurMarketPrice2)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

}
