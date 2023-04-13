package com.armitage.server.iscm.basedata.webservice.impl;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.util.ParamHelper;
import com.armitage.server.iscm.basedata.model.ScmBillPendingWSBean;
import com.armitage.server.iscm.basedata.service.ScmBillPendingBiz;
import com.armitage.server.iscm.basedata.webservice.ScmBillPendingService;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurPriceAllQuery;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmBillPendingService")
public class ScmBillPendingServiceImpl extends BaseServiceImpl<ScmBillPendingBiz, ScmBillPendingWSBean> implements ScmBillPendingService {

	@Override
	public ScmBillPendingWSBean checkExistPendingBill(ScmBillPendingWSBean bean) {
		try {
			bean.setObject(biz.checkExistPendingBill((String)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

}
