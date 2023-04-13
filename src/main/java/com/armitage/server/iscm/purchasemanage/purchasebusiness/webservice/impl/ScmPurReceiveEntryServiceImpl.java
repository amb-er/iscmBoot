package com.armitage.server.iscm.purchasemanage.purchasebusiness.webservice.impl;

import java.util.List;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.util.CommonBeanHelper;
import com.armitage.server.common.util.ParamHelper;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurReceiveEntry2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurReceiveEntryAdvQuery;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurReceiveEntryWSBean;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.service.ScmPurReceiveEntryBiz;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.webservice.ScmPurReceiveEntryService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmPurReceiveEntryService")
public class ScmPurReceiveEntryServiceImpl extends BaseServiceImpl<ScmPurReceiveEntryBiz, ScmPurReceiveEntryWSBean> implements ScmPurReceiveEntryService {
	
	@Override
	public ScmPurReceiveEntryWSBean updateStatus(ScmPurReceiveEntryWSBean bean) {
		try {
			List<ScmPurReceiveEntry2> scmPurReceiveEntryList = (List<ScmPurReceiveEntry2>)bean.getList();
			bean.setList(biz.updateStatus(scmPurReceiveEntryList,ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}
	
	@Override
	public ScmPurReceiveEntryWSBean getDataForLeadInto(ScmPurReceiveEntryWSBean bean) {
		try {
			CommonBeanHelper.toWSBean(biz.getDataForLeadInto((ScmPurReceiveEntryAdvQuery)bean.getObject(),ParamHelper.createParam(bean)),bean);
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}
}

