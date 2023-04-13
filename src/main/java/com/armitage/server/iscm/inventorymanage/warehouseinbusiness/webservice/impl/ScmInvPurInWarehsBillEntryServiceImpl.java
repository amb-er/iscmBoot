package com.armitage.server.iscm.inventorymanage.warehouseinbusiness.webservice.impl;


import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvPurInWarehsBillEntryWSBean;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.service.ScmInvPurInWarehsBillEntryBiz;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.webservice.ScmInvPurInWarehsBillEntryService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmInvPurInWarehsBillEntryService")
public class ScmInvPurInWarehsBillEntryServiceImpl extends BaseServiceImpl<ScmInvPurInWarehsBillEntryBiz, ScmInvPurInWarehsBillEntryWSBean> implements ScmInvPurInWarehsBillEntryService {
	
	@Override
	public ScmInvPurInWarehsBillEntryWSBean getDataForLeadInto(ScmInvPurInWarehsBillEntryWSBean bean) {
		try {
			//CommonBeanHelper.toWSBean(biz.getDataForLeadInto((ScmInvPurInWarehsBillEntryAdvQuery)bean.getObject(),ParamHelper.createParam(bean)),bean);
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}
}

