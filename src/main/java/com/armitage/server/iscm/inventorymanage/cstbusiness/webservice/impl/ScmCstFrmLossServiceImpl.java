package com.armitage.server.iscm.inventorymanage.cstbusiness.webservice.impl;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.util.ParamHelper;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmCstFrmLoss2;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmCstFrmLossWSBean;
import com.armitage.server.iscm.inventorymanage.cstbusiness.service.ScmCstFrmLossBiz;
import com.armitage.server.iscm.inventorymanage.cstbusiness.webservice.ScmCstFrmLossService;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.CommonAuditParams;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmCstFrmLossService")
public class ScmCstFrmLossServiceImpl extends BaseServiceImpl<ScmCstFrmLossBiz, ScmCstFrmLossWSBean> implements ScmCstFrmLossService {

	@Override
	public ScmCstFrmLossWSBean updateDirect(ScmCstFrmLossWSBean bean) {
		return null;
	}

	@Override
	public ScmCstFrmLossWSBean submit(ScmCstFrmLossWSBean bean) {
		try {
            bean.setObject(biz.submit((ScmCstFrmLoss2)bean.getObject(),ParamHelper.createParam(bean)));
        } catch (AppException e) {
            Message.inMessage(bean, e);
        } catch (Exception e) {
            Message.inMessage(bean, e);
        }
        return bean;
	}

	@Override
	public ScmCstFrmLossWSBean undoSubmit(ScmCstFrmLossWSBean bean) {
		try {
            bean.setObject(biz.undoSubmit((ScmCstFrmLoss2)bean.getObject(),ParamHelper.createParam(bean)));
        } catch (AppException e) {
            Message.inMessage(bean, e);
        } catch (Exception e) {
            Message.inMessage(bean, e);
        }
        return bean;
	}

	@Override
	public ScmCstFrmLossWSBean postBillCheck(ScmCstFrmLossWSBean bean) {
		try {
			ScmCstFrmLoss2 scmCstFrmLoss = (ScmCstFrmLoss2)bean.getObject();
			bean.setList(biz.postBillCheck(scmCstFrmLoss,ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmCstFrmLossWSBean postBill(ScmCstFrmLossWSBean bean) {
		try {
			ScmCstFrmLoss2 scmCstFrmLoss = (ScmCstFrmLoss2)bean.getObject();
			bean.setObject(biz.postBill(scmCstFrmLoss,ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmCstFrmLossWSBean cancelPostBillCheck(ScmCstFrmLossWSBean bean) {
		try {
			ScmCstFrmLoss2 scmCstFrmLoss = (ScmCstFrmLoss2)bean.getObject();
			bean.setList(biz.cancelPostBillCheck(scmCstFrmLoss,ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmCstFrmLossWSBean cancelPostBill(ScmCstFrmLossWSBean bean) {
		try {
			ScmCstFrmLoss2 scmCstFrmLoss = (ScmCstFrmLoss2)bean.getObject();
			bean.setObject(biz.cancelPostBill(scmCstFrmLoss,ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmCstFrmLossWSBean doAudit(ScmCstFrmLossWSBean bean) {
		try {
			bean.setObject(biz.doAuditCstFrmLoss((CommonAuditParams)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmCstFrmLossWSBean undoAudit(ScmCstFrmLossWSBean bean) {
		try {
			bean.setObject(biz.doUnAuditCstFrmLoss((ScmCstFrmLoss2)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmCstFrmLossWSBean updatePrtCount(ScmCstFrmLossWSBean bean) {
		try {
			bean.setObject(biz.updatePrtCount((ScmCstFrmLoss2)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

}
