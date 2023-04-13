package com.armitage.server.iscm.basedata.webservice.impl;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.util.ParamHelper;
import com.armitage.server.iscm.basedata.model.ScmSupplierQualifieInfoBill2;
import com.armitage.server.iscm.basedata.model.ScmSupplierQualifieInfoBillWSBean;
import com.armitage.server.iscm.basedata.service.ScmSupplierQualifieInfoBillBiz;
import com.armitage.server.iscm.basedata.webservice.ScmSupplierQualifieInfoBillService;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.CommonAuditParams;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmSupplierQualifieInfoBillService")
public class ScmSupplierQualifieInfoBillServiceImpl extends BaseServiceImpl<ScmSupplierQualifieInfoBillBiz, ScmSupplierQualifieInfoBillWSBean> implements ScmSupplierQualifieInfoBillService {

	@Override
	public ScmSupplierQualifieInfoBillWSBean submit(ScmSupplierQualifieInfoBillWSBean bean) {
		try {
			bean.setObject(biz.doSubmitQualifieInfo((ScmSupplierQualifieInfoBill2)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmSupplierQualifieInfoBillWSBean undoSubmit(ScmSupplierQualifieInfoBillWSBean bean) {
		try {
			bean.setObject(biz.undoSubmitQualifieInfo((ScmSupplierQualifieInfoBill2)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmSupplierQualifieInfoBillWSBean doAudit(ScmSupplierQualifieInfoBillWSBean bean) {
		try {
			bean.setObject(biz.doAuditQualifieInfo((CommonAuditParams)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmSupplierQualifieInfoBillWSBean undoAudit(ScmSupplierQualifieInfoBillWSBean bean) {
		try {
			bean.setObject(biz.doUnAuditQualifieInfo((ScmSupplierQualifieInfoBill2)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}
	
}
