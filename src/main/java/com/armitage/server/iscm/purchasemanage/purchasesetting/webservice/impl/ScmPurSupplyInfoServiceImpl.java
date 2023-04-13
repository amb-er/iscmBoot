
package com.armitage.server.iscm.purchasemanage.purchasesetting.webservice.impl;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.util.ParamHelper;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurSupplyInfo2;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurSupplyInfoWSBean;
import com.armitage.server.iscm.purchasemanage.purchasesetting.service.ScmPurSupplyInfoBiz;
import com.armitage.server.iscm.purchasemanage.purchasesetting.webservice.ScmPurSupplyInfoService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmPurSupplyInfoService")
public class ScmPurSupplyInfoServiceImpl extends BaseServiceImpl<ScmPurSupplyInfoBiz, ScmPurSupplyInfoWSBean> implements ScmPurSupplyInfoService {

	@Override
	public ScmPurSupplyInfoWSBean findVendor(ScmPurSupplyInfoWSBean bean) {
		try {
			bean.setList(biz.findVendor((ScmPurSupplyInfo2)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e){
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmPurSupplyInfoWSBean getSupplyInfoByItemSAndVendorS(ScmPurSupplyInfoWSBean bean) {
		try {
			bean.setList(biz.getSupplyInfoByItemSAndVendorS(bean.getList(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e){
			Message.inMessage(bean, e);
		}
		return bean;
	}
	
}
