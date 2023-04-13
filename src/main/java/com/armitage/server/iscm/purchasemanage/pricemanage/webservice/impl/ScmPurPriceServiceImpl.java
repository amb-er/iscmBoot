
package com.armitage.server.iscm.purchasemanage.pricemanage.webservice.impl;

import java.util.List;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.util.CommonBeanHelper;
import com.armitage.server.common.util.ParamHelper;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurMarketPrice2;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurPrice2;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurPriceAllQuery;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurPriceImPort;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurPriceQuery;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurPriceWSBean;
import com.armitage.server.iscm.purchasemanage.pricemanage.service.ScmPurPriceBiz;
import com.armitage.server.iscm.purchasemanage.pricemanage.webservice.ScmPurPriceService;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.CommonAuditParams;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmPurPriceService")
public class ScmPurPriceServiceImpl extends BaseServiceImpl<ScmPurPriceBiz, ScmPurPriceWSBean> implements ScmPurPriceService {
	
	@Override
	public ScmPurPriceWSBean submit(ScmPurPriceWSBean bean) {
		try {
			ScmPurPrice2 scmPurPrice = (ScmPurPrice2)bean.getObject();
			bean.setObject(biz.doSubmitPurPrice(scmPurPrice,ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmPurPriceWSBean undoSubmit(ScmPurPriceWSBean bean) {
		try {
			ScmPurPrice2 scmPurPrice = (ScmPurPrice2)bean.getObject();
			bean.setObject(biz.doUnSubmitPurPrice(scmPurPrice,ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmPurPriceWSBean release(ScmPurPriceWSBean bean) {
		try {
			ScmPurPrice2 scmPurPrice = (ScmPurPrice2)bean.getObject();
			bean.setObject(biz.doReleasePurPrice(scmPurPrice,ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmPurPriceWSBean undoRelease(ScmPurPriceWSBean bean) {
		try {
			List<ScmPurPrice2> scmPurPrice2lList = bean.getList();
			int type = (int) bean.getObject();
			CommonBeanHelper.toWSBean(biz.doUndoReleasePurPrice(scmPurPrice2lList,type,ParamHelper.createParam(bean)),bean);
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmPurPriceWSBean finish(ScmPurPriceWSBean bean) {
		try {
			ScmPurPrice2 scmPurPrice = (ScmPurPrice2)bean.getObject();
			bean.setObject(biz.finish(scmPurPrice,ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmPurPriceWSBean undoFinish(ScmPurPriceWSBean bean) {
		try {
			ScmPurPrice2 scmPurPrice = (ScmPurPrice2)bean.getObject();
			bean.setObject(biz.undoFinish(scmPurPrice,ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

    @Override
    public ScmPurPriceWSBean importExcel(ScmPurPriceWSBean bean) {
        try {
            bean.setObject(biz.importExcel((ScmPurPriceImPort)bean.getObject(),ParamHelper.createParam(bean)));
        } catch (AppException e) {
            Message.inMessage(bean, e);
        } catch (Exception e) {
            Message.inMessage(bean, e);
        }
        return bean;
    }

	@Override
	public ScmPurPriceWSBean doAudit(ScmPurPriceWSBean bean) {
		try {
			bean.setObject(biz.doAuditPurPrice((CommonAuditParams)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmPurPriceWSBean undoAudit(ScmPurPriceWSBean bean) {
		try {
			bean.setObject(biz.doUnAuditPurPrice((ScmPurPrice2)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmPurPriceWSBean getEnterSupplierPlat(ScmPurPriceWSBean bean) {
		try {
			bean.setList(biz.getEnterSupplierPlat(String.valueOf(bean.getObject()),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmPurPriceWSBean getPrePrice(ScmPurPriceWSBean bean) {
		try {
			bean.setList(biz.getPrePrice((ScmPurPriceQuery)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmPurPriceWSBean selectPrePriceByVendor(ScmPurPriceWSBean bean) {
		try {
			bean.setList(biz.selectPrePriceByVendor((List<ScmPurPriceQuery>)bean.getList(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmPurPriceWSBean selectLastYearPriceByVendor(ScmPurPriceWSBean bean) {
		try {
			bean.setList(biz.selectLastYearPriceByVendor((List<ScmPurPriceQuery>)bean.getList(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmPurPriceWSBean updatePrtCount(ScmPurPriceWSBean bean) {
		try {
			bean.setObject(biz.updatePrtCount((ScmPurPrice2)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}
	
}
