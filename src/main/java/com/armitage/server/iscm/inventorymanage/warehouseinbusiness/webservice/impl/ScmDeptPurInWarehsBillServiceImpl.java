package com.armitage.server.iscm.inventorymanage.warehouseinbusiness.webservice.impl;


import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.util.ParamHelper;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvPurInWarehsBill2;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvPurInWarehsBillWSBean;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.service.ScmDeptPurInWarehsBillBiz;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.webservice.ScmDeptPurInWarehsBillService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmDeptPurInWarehsBillService")
public class ScmDeptPurInWarehsBillServiceImpl extends BaseServiceImpl<ScmDeptPurInWarehsBillBiz, ScmInvPurInWarehsBillWSBean> implements ScmDeptPurInWarehsBillService {

	@Override
	public ScmInvPurInWarehsBillWSBean updatePrtCount(ScmInvPurInWarehsBillWSBean bean) {
		try {
			bean.setObject(biz.updatePrtCount((ScmInvPurInWarehsBill2)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}
	
}

