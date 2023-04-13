package com.armitage.server.iscm.purchasemanage.purchasebusiness.webservice.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;


import com.armitage.server.common.base.model.CommonBean;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.util.CommonBeanHelper;
import com.armitage.server.common.util.ParamHelper;
import com.armitage.server.iscm.common.model.ScmDataCollectionStepState2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurRequire2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurRequireEntry2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurRequireEntryAdvQuery;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurRequireEntryWSBean;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.service.ScmPurRequireEntryBiz;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.webservice.ScmPurRequireEntryService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmPurRequireEntryService")
public class ScmPurRequireEntryServiceImpl extends BaseServiceImpl<ScmPurRequireEntryBiz, ScmPurRequireEntryWSBean> implements ScmPurRequireEntryService {
	
	
	@Override
	public ScmPurRequireEntryWSBean updateStatus(ScmPurRequireEntryWSBean bean) {
		try {
			List<ScmPurRequireEntry2> scmPurRequireEntryList = (List<ScmPurRequireEntry2>)bean.getList();
			bean.setList(biz.updateStatus(scmPurRequireEntryList,ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}
	
	@Override
	public ScmPurRequireEntryWSBean generateOrder(ScmPurRequireEntryWSBean bean) {
		try {
			List<ScmPurRequireEntry2> list = bean.getList();
			Param param = ParamHelper.createParam(bean);
			ArrayList<ScmPurRequireEntry2> arrayList = new ArrayList<>();
			StringBuffer stringBuffer = new StringBuffer("");
			for (ScmPurRequireEntry2 scmPurRequireEntry2 : list) {
				if (scmPurRequireEntry2.getVendorId() > 0) {
					arrayList.add(scmPurRequireEntry2);
				} else {
					String message = Message.getMessage(
							"iscm.purchasemanage.purchasebusiness.ScmPurRequireEntryServiceImpl.generateOrder.validateVendor");
					stringBuffer.append(scmPurRequireEntry2.getPrNo()).append(scmPurRequireEntry2.getItemName())
							.append(" ").append(message).append("\r\n");
				}
			}
			CommonBean generateOrder = biz.generateOrder((ScmDataCollectionStepState2)bean.getObject(),arrayList,param);
			String message = Message.getMessage(param.getLang(),"iscm.purchasemanage.information.partSuccess",new String[] {String.valueOf(arrayList.size()),String.valueOf((list.size()-arrayList.size()))});
			generateOrder.setObject10(stringBuffer.toString());
			generateOrder.setObject11(message);
			CommonBeanHelper.toWSBean(generateOrder,bean);
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}
	
	@Override
	public ScmPurRequireEntryWSBean getDataForLeadInto(ScmPurRequireEntryWSBean bean) {
		try {
			CommonBeanHelper.toWSBean(biz.getDataForLeadInto((ScmPurRequireEntryAdvQuery)bean.getObject(),ParamHelper.createParam(bean)),bean);
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmPurRequireEntryWSBean autoAssign(ScmPurRequireEntryWSBean bean) {
		try {
			List<ScmPurRequireEntry2> scmPurRequireEntryList = (List<ScmPurRequireEntry2>)bean.getList();
			bean.setList(biz.autoAssign(scmPurRequireEntryList,ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmPurRequireEntryWSBean cancelRefuse(ScmPurRequireEntryWSBean bean) {
		try {
			bean.setList(biz.cancelRefuse((List<ScmPurRequireEntry2>)bean.getList(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmPurRequireEntryWSBean viewPurRequestStatus(
			ScmPurRequireEntryWSBean bean) {
		try {
			ScmPurRequire2 scmPurRequire = (ScmPurRequire2)bean.getObject();
			bean.setList(biz.viewPurRequestStatus(scmPurRequire,ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmPurRequireEntryWSBean generateTempPrice(
			ScmPurRequireEntryWSBean bean) {
		try {
			List<ScmPurRequireEntry2> scmPurRequireEntryList = (List<ScmPurRequireEntry2>)bean.getList();
			CommonBeanHelper.toWSBean(biz.generateTempPrice(scmPurRequireEntryList,ParamHelper.createParam(bean)),bean);
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}
	
	@Override
	public ScmPurRequireEntryWSBean selectByPurOrgUnitNo(ScmPurRequireEntryWSBean bean) {
		try {
			String object = (String) bean.getObject();
			bean.setList((biz.selectByPurOrgUnitNo(object,ParamHelper.createParam(bean))));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmPurRequireEntryWSBean refuse(ScmPurRequireEntryWSBean bean) {
		try {
			List<ScmPurRequireEntry2> scmPurRequireEntryList = (List<ScmPurRequireEntry2>)bean.getList();
			bean.setList(biz.refuse(scmPurRequireEntryList,ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}
	
}
