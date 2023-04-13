package com.armitage.server.iscm.basedata.webservice.impl;

import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.util.CommonBeanHelper;
import com.armitage.server.common.util.ParamHelper;
import com.armitage.server.iscm.basedata.model.ScmSupplierPlatFormUser;
import com.armitage.server.iscm.basedata.model.Scmsupplier2;
import com.armitage.server.iscm.basedata.model.ScmsupplierWSBean;
import com.armitage.server.iscm.basedata.service.ScmsupplierBiz;
import com.armitage.server.iscm.basedata.webservice.ScmsupplierService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmsupplierService")
public class ScmsupplierServiceImpl extends BaseServiceImpl<ScmsupplierBiz, ScmsupplierWSBean> implements ScmsupplierService {
	
	@Override
	public ScmsupplierWSBean updateStatus(ScmsupplierWSBean bean) {
		try {
			BaseModel baseModel = (BaseModel)bean.getObject();
			bean.setObject(biz.updateStatus(baseModel,ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmsupplierWSBean checkUse(ScmsupplierWSBean bean) {
		try {
			bean.setObject(biz.checkUse((Long)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmsupplierWSBean saveUnified(ScmsupplierWSBean bean) {
		try {
			biz.saveUnified((Scmsupplier2)bean.getObject(),bean.getList(),ParamHelper.createParam(bean));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmsupplierWSBean selectUnified(ScmsupplierWSBean bean) {
		try {
			bean.setList(biz.selectUnified((Scmsupplier2)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmsupplierWSBean selectByCode(ScmsupplierWSBean bean) {
		try {
			String vendorCode = (String)bean.getObject();
			bean.setObject(biz.selectByCode(vendorCode,ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmsupplierWSBean findSameNameVendor(ScmsupplierWSBean bean) {
		try {
			bean.setObject(biz.findSameNameVendor((Scmsupplier2)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmsupplierWSBean selectPlatFormUser(ScmsupplierWSBean bean) {
		try {
			bean.setList(biz.selectPlatFormUser((Scmsupplier2)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmsupplierWSBean changePlatFormAdmin(ScmsupplierWSBean bean) {
		try {
			bean.setList(biz.changePlatFormAdmin((ScmSupplierPlatFormUser)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmsupplierWSBean auditPlatFormAdmin(ScmsupplierWSBean bean) {
		try {
			bean.setList(biz.auditPlatFormAdmin((ScmSupplierPlatFormUser)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmsupplierWSBean disable(ScmsupplierWSBean bean) {
		try {
			bean.setList(biz.disable((BaseModel) bean.getObject(),bean.getList(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmsupplierWSBean enable(ScmsupplierWSBean bean) {
		try {
			bean.setList(biz.enable((BaseModel) bean.getObject(),bean.getList(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmsupplierWSBean getQualifieInfo(ScmsupplierWSBean bean) {
		try {
			CommonBeanHelper.toWSBean(biz.getQualifieInfo((Scmsupplier2)bean.getObject(),ParamHelper.createParam(bean)),bean);
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}
}
