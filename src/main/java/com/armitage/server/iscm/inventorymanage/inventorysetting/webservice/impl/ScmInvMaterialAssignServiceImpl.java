package com.armitage.server.iscm.inventorymanage.inventorysetting.webservice.impl;


import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.util.ParamHelper;
import com.armitage.server.iscm.basedata.model.ScmMeasureUnit;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvMaterialAssignWSBean;
import com.armitage.server.iscm.inventorymanage.inventorysetting.service.ScmInvMaterialAssignBiz;
import com.armitage.server.iscm.inventorymanage.inventorysetting.webservice.ScmInvMaterialAssignService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmInvMaterialAssignService")
public class ScmInvMaterialAssignServiceImpl extends BaseServiceImpl<ScmInvMaterialAssignBiz, ScmInvMaterialAssignWSBean> implements ScmInvMaterialAssignService {
	@Override
	public ScmInvMaterialAssignWSBean selectMaterialAssign(ScmInvMaterialAssignWSBean bean) {
		try {
			long wareHouseId = (Long)bean.getObject();
			bean.setList(biz.selectMaterialAssign(wareHouseId,ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmInvMaterialAssignWSBean checkItemAssign(ScmInvMaterialAssignWSBean bean) {
		try {
			long wareHouseId = (Long)bean.getObject();
			long itemId = (Long)bean.getObject2();
			String orgUnitNo = String.valueOf(bean.getObject3());
			bean.setObject(biz.checkItemAssign(wareHouseId,itemId,orgUnitNo,ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}
}

