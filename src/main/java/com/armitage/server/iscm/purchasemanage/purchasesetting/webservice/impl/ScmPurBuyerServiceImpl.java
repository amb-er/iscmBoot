
package com.armitage.server.iscm.purchasemanage.purchasesetting.webservice.impl;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.util.ParamHelper;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurBuyer2;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurBuyerWSBean;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurGroup;
import com.armitage.server.iscm.purchasemanage.purchasesetting.service.ScmPurBuyerBiz;
import com.armitage.server.iscm.purchasemanage.purchasesetting.webservice.ScmPurBuyerService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmPurBuyerService")
public class ScmPurBuyerServiceImpl extends BaseServiceImpl<ScmPurBuyerBiz, ScmPurBuyerWSBean> implements ScmPurBuyerService {

	@Override
	public ScmPurBuyerWSBean updateGroup(ScmPurBuyerWSBean bean) {
		try {
			bean.setObject(biz.updateGroup((ScmPurGroup)bean.getObject(), ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e){
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmPurBuyerWSBean selectByParentId(ScmPurBuyerWSBean bean) {
		try {
			bean.setList(biz.selectByParentId((Long)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e){
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmPurBuyerWSBean deleteBuyerCheck(ScmPurBuyerWSBean bean) {
		try {
			bean.setList(biz.deleteBuyerCheck(bean.getList(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e){
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmPurBuyerWSBean getGrantedOrg(ScmPurBuyerWSBean bean) {
		try {
			bean = biz.getGrantedOrg((ScmPurBuyer2)bean.getObject(), ParamHelper.createParam(bean));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmPurBuyerWSBean grantOrgUnit(ScmPurBuyerWSBean bean) {
		try {
			biz.grantOrgUnit((ScmPurBuyer2)bean.getObject(), bean.getList(), ParamHelper.createParam(bean));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}
	
}
