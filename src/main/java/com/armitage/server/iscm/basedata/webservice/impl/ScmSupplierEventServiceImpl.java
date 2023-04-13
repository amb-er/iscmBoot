package com.armitage.server.iscm.basedata.webservice.impl;


import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.util.ParamHelper;
import com.armitage.server.iscm.basedata.model.ScmSupplierEvent2;
import com.armitage.server.iscm.basedata.model.ScmSupplierEventWSBean;
import com.armitage.server.iscm.basedata.service.ScmSupplierEventBiz;
import com.armitage.server.iscm.basedata.webservice.ScmSupplierEventService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmSupplierEventService")
public class ScmSupplierEventServiceImpl extends BaseServiceImpl<ScmSupplierEventBiz, ScmSupplierEventWSBean> implements ScmSupplierEventService {

	@Override
	public ScmSupplierEventWSBean generateEventQRcode(ScmSupplierEventWSBean bean) {
		try {
			bean.setObject2(biz.generateEventQRcode((ScmSupplierEvent2)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}
	
}

