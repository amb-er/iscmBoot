
package com.armitage.server.iscm.inventorymanage.inventorysetting.webservice.impl;

import java.util.List;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.util.CommonBeanHelper;
import com.armitage.server.common.util.ParamHelper;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvWareHouse;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvWareHouseWSBean;
import com.armitage.server.iscm.inventorymanage.inventorysetting.service.ScmInvWareHouseBiz;
import com.armitage.server.iscm.inventorymanage.inventorysetting.webservice.ScmInvWareHouseService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmInvWareHouseService")
public class ScmInvWareHouseServiceImpl extends BaseServiceImpl<ScmInvWareHouseBiz, ScmInvWareHouseWSBean> implements ScmInvWareHouseService {
	
	@Override
	public ScmInvWareHouseWSBean enableWareHouse(ScmInvWareHouseWSBean bean) {
		try {
			bean.setObject(biz.enableWareHouse((ScmInvWareHouse)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}
	
	@Override
	public ScmInvWareHouseWSBean selectWareHouseSet(ScmInvWareHouseWSBean bean) {
		try {
			List<ScmInvWareHouse> scmInvWareHouse = (List<ScmInvWareHouse>)bean.getList();
			bean.setList(biz.selectWareHouseSet(scmInvWareHouse,ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmInvWareHouseWSBean selectByOrgUnitNo(ScmInvWareHouseWSBean bean) {
		try {
			String orgUnitNo = (String)bean.getObject();
			String fromWhNo = (String)bean.getObject2();
			String toWhNo =(String)bean.getObject3();
			bean.setList(biz.selectByOrgUnitNo(orgUnitNo,fromWhNo,toWhNo,ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmInvWareHouseWSBean selectByWhName(ScmInvWareHouseWSBean bean) {
		try {
			bean.setList(biz.selectByWhName((ScmInvWareHouse)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e){
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmInvWareHouseWSBean selectWareHouseUsr(ScmInvWareHouseWSBean bean) {
		try {
			CommonBeanHelper.toWSBean(biz.selectWareHouseUsr((ScmInvWareHouse)bean.getObject(), ParamHelper.createParam(bean)), bean);
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmInvWareHouseWSBean saveWareHouseUsr(ScmInvWareHouseWSBean bean) {
		try {
			biz.saveWareHouseUsr(CommonBeanHelper.fromWSBean(bean),ParamHelper.createParam(bean));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;	}
}
