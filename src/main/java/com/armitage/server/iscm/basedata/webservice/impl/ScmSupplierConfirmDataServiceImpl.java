package com.armitage.server.iscm.basedata.webservice.impl;


import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.util.ParamHelper;
import com.armitage.server.iscm.basedata.model.ScmSupplierConfirmData2;
import com.armitage.server.iscm.basedata.model.ScmSupplierConfirmDataWSBean;
import com.armitage.server.iscm.basedata.service.ScmSupplierConfirmDataBiz;
import com.armitage.server.iscm.basedata.webservice.ScmSupplierConfirmDataService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmSupplierConfirmDataService")
public class ScmSupplierConfirmDataServiceImpl extends BaseServiceImpl<ScmSupplierConfirmDataBiz, ScmSupplierConfirmDataWSBean> implements ScmSupplierConfirmDataService {

	@Override
	public ScmSupplierConfirmDataWSBean saveConfirmAdj(ScmSupplierConfirmDataWSBean bean) {
		try {
			biz.saveConfirmAdj((ScmSupplierConfirmData2)bean.getObject(),ParamHelper.createParam(bean));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}
	
}

