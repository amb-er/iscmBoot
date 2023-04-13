
package com.armitage.server.iscm.purchasemanage.pricemanage.webservice.impl;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.util.ParamHelper;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurPriceEntryWSBean;
import com.armitage.server.iscm.purchasemanage.pricemanage.service.ScmPurPriceEntryBiz;
import com.armitage.server.iscm.purchasemanage.pricemanage.webservice.ScmPurPriceEntryService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;


@Controller
@Path("/scmPurPriceEntryService")
public class ScmPurPriceEntryServiceImpl extends BaseServiceImpl<ScmPurPriceEntryBiz, ScmPurPriceEntryWSBean> implements ScmPurPriceEntryService {

	@Override
	public ScmPurPriceEntryWSBean selectByPmId(ScmPurPriceEntryWSBean bean) {
		try {
			bean.setList(biz.selectByPmId((Long)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}
	
}
