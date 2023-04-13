package com.armitage.server.iscm.purchasemanage.purchasebusiness.webservice.impl;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.util.ParamHelper;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurCheck2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurCheckWSBean;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.service.ScmPurCheckBiz;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.webservice.ScmPurCheckService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmPurCheckService")
public class ScmPurCheckServiceImpl extends BaseServiceImpl<ScmPurCheckBiz, ScmPurCheckWSBean> implements ScmPurCheckService {

	@Override
	public ScmPurCheckWSBean confirm(ScmPurCheckWSBean bean) {
		try {
			biz.confirm(bean.getList(),ParamHelper.createParam(bean));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmPurCheckWSBean unConfirm(ScmPurCheckWSBean bean) {
		try {
			bean.setList(biz.unConfirm(bean.getList(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmPurCheckWSBean updatePrtCount(ScmPurCheckWSBean bean) {
		try {
			bean.setObject(biz.updatePrtCount((ScmPurCheck2)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}
	
}

