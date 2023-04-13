
package com.armitage.server.iscm.purchasemanage.purchasesetting.webservice.impl;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.util.ParamHelper;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurRequire2;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurchaseCanuseSetWSBean;
import com.armitage.server.iscm.purchasemanage.purchasesetting.service.ScmPurchaseCanuseSetBiz;
import com.armitage.server.iscm.purchasemanage.purchasesetting.webservice.ScmPurchaseCanuseSetService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmPurchaseCanuseSetService")
public class ScmPurchaseCanuseSetServiceImpl extends BaseServiceImpl<ScmPurchaseCanuseSetBiz, ScmPurchaseCanuseSetWSBean> implements ScmPurchaseCanuseSetService {

	@Override
	public ScmPurchaseCanuseSetWSBean checkDate(ScmPurchaseCanuseSetWSBean bean) {
		try {
			bean.setObject(biz.checkDate((ScmPurRequire2)bean.getObject(),bean.getList(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e){
			Message.inMessage(bean, e);
		}
		return bean;
	}
	
}
