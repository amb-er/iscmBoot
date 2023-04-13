package com.armitage.server.iscm.purchasemanage.purchasebusiness.webservice.impl;

import java.util.List;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.util.CommonBeanHelper;
import com.armitage.server.common.util.ParamHelper;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.CommonAuditParams;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurRequire2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurRequireEntry2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurRequireWSBean;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.service.ScmPurRequireBiz;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.webservice.ScmPurRequireService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmPurRequireService")
public class ScmPurRequireServiceImpl extends BaseServiceImpl<ScmPurRequireBiz, ScmPurRequireWSBean> implements ScmPurRequireService {
	@Override
	public ScmPurRequireWSBean submit(ScmPurRequireWSBean bean) {
		try {
			ScmPurRequire2 scmPurRequire = (ScmPurRequire2)bean.getObject();
			bean.setObject(biz.doSubmitPurRequire(scmPurRequire,ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmPurRequireWSBean undoSubmit(ScmPurRequireWSBean bean) {
		try {
			ScmPurRequire2 scmPurRequire = (ScmPurRequire2)bean.getObject();
			bean.setObject(biz.doUnSubmitPurRequire(scmPurRequire,ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

    @Override
    public ScmPurRequireWSBean saveTemplete(ScmPurRequireWSBean bean) {
        try {
            ScmPurRequire2 scmPurRequire = (ScmPurRequire2)bean.getObject();
            bean.setObject(biz.saveTemplete(scmPurRequire,ParamHelper.createParam(bean)));
        } catch (AppException e) {
            Message.inMessage(bean, e);
        } catch (Exception e) {
            Message.inMessage(bean, e);
        }
        return bean;
    }

    @Override
    public ScmPurRequireWSBean templeteMake(ScmPurRequireWSBean bean) {
        try {
            ScmPurRequire2 scmPurRequire = (ScmPurRequire2)bean.getObject();
            bean.setObject(biz.templeteMake(scmPurRequire,ParamHelper.createParam(bean)));
        } catch (AppException e) {
            Message.inMessage(bean, e);
        } catch (Exception e) {
            Message.inMessage(bean, e);
        }
        return bean; 
    }

	@Override
	public ScmPurRequireWSBean doAudit(ScmPurRequireWSBean bean) {
		try {
			bean.setObject(biz.doAuditPurRequire((CommonAuditParams)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmPurRequireWSBean undoAudit(ScmPurRequireWSBean bean) {
		try {
			bean.setObject(biz.doUnAuditPurRequire((ScmPurRequire2)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmPurRequireWSBean finish(ScmPurRequireWSBean bean) {
		try {
			bean.setObject(biz.finish((ScmPurRequire2)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmPurRequireWSBean undoFinish(ScmPurRequireWSBean bean) {
		try {
			bean.setObject(biz.undoFinish((ScmPurRequire2)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}
	
	@Override
	public ScmPurRequireWSBean selectDrillBills(ScmPurRequireWSBean bean) {
		try {
			ScmPurRequire2 scmPurRequire = (ScmPurRequire2)bean.getObject();
			bean.setList(biz.selectDrillBills(scmPurRequire,ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmPurRequireWSBean doEntryAudit(ScmPurRequireWSBean bean) {
		try {
			List<ScmPurRequireEntry2> list = bean.getList();
			CommonAuditParams commonAuditParams = (CommonAuditParams) bean.getObject();
			CommonBeanHelper.toWSBean(biz.doEntryAudit(list,commonAuditParams,ParamHelper.createParam(bean)), bean);
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmPurRequireWSBean deleteTemplete(ScmPurRequireWSBean bean) {
		try {
			biz.deleteTemplete((ScmPurRequire2)bean.getObject(),ParamHelper.createParam(bean));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmPurRequireWSBean queryTemplete(ScmPurRequireWSBean bean) {
		try {
			bean.setObject(biz.queryTemplete((ScmPurRequire2)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmPurRequireWSBean updatePrtCount(ScmPurRequireWSBean bean) {
		try {
			bean.setObject(biz.updatePrtCount((ScmPurRequire2)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}
}