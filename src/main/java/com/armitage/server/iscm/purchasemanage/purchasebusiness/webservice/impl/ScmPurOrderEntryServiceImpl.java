package com.armitage.server.iscm.purchasemanage.purchasebusiness.webservice.impl;

import java.util.List;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.util.CommonBeanHelper;
import com.armitage.server.common.util.ParamHelper;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurOrderEntry2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurOrderEntryAdvQuery;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurOrderEntryWSBean;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.service.ScmPurOrderEntryBiz;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.webservice.ScmPurOrderEntryService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmPurOrderEntryService")
public class ScmPurOrderEntryServiceImpl extends BaseServiceImpl<ScmPurOrderEntryBiz, ScmPurOrderEntryWSBean> implements ScmPurOrderEntryService {

	@Override
	public ScmPurOrderEntryWSBean updateStatus(ScmPurOrderEntryWSBean bean) {
		try {
			List<ScmPurOrderEntry2> scmPurOrderEntry = (List<ScmPurOrderEntry2>)bean.getList();
			bean.setList(biz.updateStatus(scmPurOrderEntry,ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}
	
	@Override
	public ScmPurOrderEntryWSBean getDataForLeadInto(ScmPurOrderEntryWSBean bean) {
		try {
			CommonBeanHelper.toWSBean(biz.getDataForLeadInto((ScmPurOrderEntryAdvQuery)bean.getObject(),ParamHelper.createParam(bean)),bean);
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}
	
	@Override
	public ScmPurOrderEntryWSBean generateTempPrice(ScmPurOrderEntryWSBean bean) {
		try {
			List<ScmPurOrderEntry2> scmPurOrderEntryList = (List<ScmPurOrderEntry2>)bean.getList();
			//bean.setList(biz.generateTempPrice(scmPurOrderEntryList,ParamHelper.createParam(bean)));
			CommonBeanHelper.toWSBean(biz.generateTempPrice(scmPurOrderEntryList,ParamHelper.createParam(bean)),bean);
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}
}

