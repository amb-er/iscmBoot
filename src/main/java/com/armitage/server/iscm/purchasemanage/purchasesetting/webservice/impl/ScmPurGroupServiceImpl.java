
package com.armitage.server.iscm.purchasemanage.purchasesetting.webservice.impl;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.util.ParamHelper;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurGroup;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurGroupWSBean;
import com.armitage.server.iscm.purchasemanage.purchasesetting.service.ScmPurGroupBiz;
import com.armitage.server.iscm.purchasemanage.purchasesetting.webservice.ScmPurGroupService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmPurGroupService")
public class ScmPurGroupServiceImpl extends BaseServiceImpl<ScmPurGroupBiz, ScmPurGroupWSBean> implements ScmPurGroupService {

	@Override
	public ScmPurGroupWSBean selectByPurGroupNo(ScmPurGroupWSBean bean) {
		try {
			bean.setList(biz.selectByPurGroupNo((ScmPurGroup)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e){
			Message.inMessage(bean, e);
		}
		return bean;
	}
	
}
