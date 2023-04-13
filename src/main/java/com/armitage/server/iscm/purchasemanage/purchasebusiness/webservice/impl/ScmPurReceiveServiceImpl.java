package com.armitage.server.iscm.purchasemanage.purchasebusiness.webservice.impl;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.util.ParamHelper;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.CommonAuditParams;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurReceive2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurReceiveWSBean;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.service.ScmPurReceiveBiz;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.webservice.ScmPurReceiveService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmPurReceiveService")
public class ScmPurReceiveServiceImpl extends BaseServiceImpl<ScmPurReceiveBiz, ScmPurReceiveWSBean> implements ScmPurReceiveService {
	
	@Override
	public ScmPurReceiveWSBean submit(ScmPurReceiveWSBean bean) {
		try {
			bean.setObject(biz.doSubmitPurReceive((ScmPurReceive2)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmPurReceiveWSBean undoSubmit(ScmPurReceiveWSBean bean) {
		try {
			bean.setObject(biz.doUnSubmitPurReceive((ScmPurReceive2)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmPurReceiveWSBean release(ScmPurReceiveWSBean bean) {
		try {
			bean.setObject(biz.doRelease((ScmPurReceive2)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmPurReceiveWSBean undoRelease(	ScmPurReceiveWSBean bean) {
		try {
			bean.setObject(biz.doUndoRelease((ScmPurReceive2)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmPurReceiveWSBean startReceive(ScmPurReceiveWSBean bean) {
		try {
			bean.setObject(biz.startReceive((ScmPurReceive2)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmPurReceiveWSBean generateWrReceipts(ScmPurReceiveWSBean bean) {
		try {
			bean.setList(biz.generateWrReceipts(bean.getList(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

    @Override
    public ScmPurReceiveWSBean undoFinish(ScmPurReceiveWSBean bean) {
        try {
            bean.setObject(biz.doUndoFinish((ScmPurReceive2)bean.getObject(),ParamHelper.createParam(bean)));
        } catch (AppException e) {
            Message.inMessage(bean, e);
        } catch (Exception e) {
            Message.inMessage(bean, e);
        }
        return bean;
    }

	@Override
	public ScmPurReceiveWSBean doAudit(ScmPurReceiveWSBean bean) {
		try {
            bean.setObject(biz.doAuditPurReceive((CommonAuditParams)bean.getObject(),ParamHelper.createParam(bean)));
        } catch (AppException e) {
            Message.inMessage(bean, e);
        } catch (Exception e) {
            Message.inMessage(bean, e);
        }
        return bean;
	}

	@Override
	public ScmPurReceiveWSBean undoAudit(ScmPurReceiveWSBean bean) {
		try {
            bean.setObject(biz.doUnAuditPurReceive((ScmPurReceive2)bean.getObject(),ParamHelper.createParam(bean)));
        } catch (AppException e) {
            Message.inMessage(bean, e);
        } catch (Exception e) {
            Message.inMessage(bean, e);
        }
        return bean;
	}
	
	@Override
	public ScmPurReceiveWSBean selectDrillBills(ScmPurReceiveWSBean bean) {
		try {
			ScmPurReceive2 scmPurReceive = (ScmPurReceive2)bean.getObject();
			bean.setList(biz.selectDrillBills(scmPurReceive,ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmPurReceiveWSBean updatePrtCount(ScmPurReceiveWSBean bean) {
		try {
			bean.setObject(biz.updatePrtCount((ScmPurReceive2)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}
}

