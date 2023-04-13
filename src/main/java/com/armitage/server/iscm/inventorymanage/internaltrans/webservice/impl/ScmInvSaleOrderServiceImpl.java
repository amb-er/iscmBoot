package com.armitage.server.iscm.inventorymanage.internaltrans.webservice.impl;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.util.ParamHelper;
import com.armitage.server.iscm.inventorymanage.internaltrans.model.ScmInvSaleOrder2;
import com.armitage.server.iscm.inventorymanage.internaltrans.model.ScmInvSaleOrderWSBean;
import com.armitage.server.iscm.inventorymanage.internaltrans.service.ScmInvSaleOrderBiz;
import com.armitage.server.iscm.inventorymanage.internaltrans.webservice.ScmInvSaleOrderService;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.CommonAuditParams;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmInvSaleOrderService")
public class ScmInvSaleOrderServiceImpl extends BaseServiceImpl<ScmInvSaleOrderBiz, ScmInvSaleOrderWSBean> implements ScmInvSaleOrderService {

    @Override
    public ScmInvSaleOrderWSBean updateStatus(ScmInvSaleOrderWSBean bean) {
        try {
            ScmInvSaleOrder2 scmInvSaleOrder = (ScmInvSaleOrder2)bean.getObject();
            bean.setObject(biz.updateStatus(scmInvSaleOrder,ParamHelper.createParam(bean)));
        } catch (AppException e) {
            Message.inMessage(bean, e);
        } catch (Exception e) {
            Message.inMessage(bean, e);
        }
        return bean;
    }

    @Override
    public ScmInvSaleOrderWSBean generateOutBoundOrders(ScmInvSaleOrderWSBean bean) {
        try {
            biz.generateOutBoundOrders((ScmInvSaleOrder2)bean.getObject(),ParamHelper.createParam(bean));
        } catch (AppException e) {
            Message.inMessage(bean, e);
        } catch (Exception e) {
            Message.inMessage(bean, e);
        }
        return bean;
    }

	@Override
	public ScmInvSaleOrderWSBean checkFollowUpBill(ScmInvSaleOrderWSBean bean) {
		try {
            bean.setObject(biz.checkFollowUpBill((ScmInvSaleOrder2)bean.getObject(),ParamHelper.createParam(bean)));
        } catch (AppException e) {
            Message.inMessage(bean, e);
        } catch (Exception e) {
            Message.inMessage(bean, e);
        }
        return bean;
	}

	@Override
	public ScmInvSaleOrderWSBean finish(ScmInvSaleOrderWSBean bean) {
		try {
            ScmInvSaleOrder2 scmInvSaleOrder = (ScmInvSaleOrder2)bean.getObject();
            bean.setObject(biz.finish(scmInvSaleOrder,ParamHelper.createParam(bean)));
        } catch (AppException e) {
            Message.inMessage(bean, e);
        } catch (Exception e) {
            Message.inMessage(bean, e);
        }
        return bean;
	}

	@Override
	public ScmInvSaleOrderWSBean undoFinish(ScmInvSaleOrderWSBean bean) {
		try {
            ScmInvSaleOrder2 scmInvSaleOrder = (ScmInvSaleOrder2)bean.getObject();
            bean.setObject(biz.undoFinish(scmInvSaleOrder,ParamHelper.createParam(bean)));
        } catch (AppException e) {
            Message.inMessage(bean, e);
        } catch (Exception e) {
            Message.inMessage(bean, e);
        }
        return bean;
	}

	@Override
	public ScmInvSaleOrderWSBean undoGenerateOutBoundOrders(ScmInvSaleOrderWSBean bean) {
		try {
            ScmInvSaleOrder2 scmInvSaleOrder = (ScmInvSaleOrder2)bean.getObject();
            bean.setObject(biz.undoGenerateOutBoundOrders(scmInvSaleOrder,ParamHelper.createParam(bean)));
        } catch (AppException e) {
            Message.inMessage(bean, e);
        } catch (Exception e) {
            Message.inMessage(bean, e);
        }
        return bean;
	}

	@Override
	public ScmInvSaleOrderWSBean submit(ScmInvSaleOrderWSBean bean) {
		try {
			ScmInvSaleOrder2 scmInvSaleOrder = (ScmInvSaleOrder2)bean.getObject();
			bean.setObject(biz.doSubmitInvSaleOrder(scmInvSaleOrder,ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmInvSaleOrderWSBean undoSubmit(ScmInvSaleOrderWSBean bean) {
		try {
			ScmInvSaleOrder2 scmInvSaleOrder = (ScmInvSaleOrder2)bean.getObject();
			bean.setObject(biz.doUnSubmitInvSaleOrder(scmInvSaleOrder,ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmInvSaleOrderWSBean doAudit(ScmInvSaleOrderWSBean bean) {
		try {
			bean.setObject(biz.doAuditInvSaleOrder((CommonAuditParams)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmInvSaleOrderWSBean undoAudit(ScmInvSaleOrderWSBean bean) {
		try {
			bean.setObject(biz.doUnAuditInvSaleOrder((ScmInvSaleOrder2)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmInvSaleOrderWSBean updatePrtCount(ScmInvSaleOrderWSBean bean) {
		try {
			bean.setObject(biz.updatePrtCount((ScmInvSaleOrder2)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}
	
}
