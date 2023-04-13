package com.armitage.server.iscm.purchasemanage.pricemanage.webservice.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.armitage.server.api.business.purquotation.params.PurQuotaionDetailParams;
import com.armitage.server.api.business.purquotation.params.PurQuotationAddParams;
import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.util.CommonBeanHelper;
import com.armitage.server.common.util.ParamHelper;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurQuotaionDetail;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurQuotation2;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurQuotationAdd;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurQuotationWSBean;
import com.armitage.server.iscm.purchasemanage.pricemanage.service.ScmPurQuotationBiz;
import com.armitage.server.iscm.purchasemanage.pricemanage.webservice.ScmPurQuotationService;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.CommonAuditParams;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmPurQuotationService")
public class ScmPurQuotationServiceImpl extends BaseServiceImpl<ScmPurQuotationBiz, ScmPurQuotationWSBean> implements ScmPurQuotationService {
	
	@Override
	public ScmPurQuotationWSBean release(ScmPurQuotationWSBean bean) {
		try {
			ScmPurQuotation2 scmPurQuotation = (ScmPurQuotation2)bean.getObject();
			bean.setObject(biz.release(scmPurQuotation,ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmPurQuotationWSBean undoRelease(ScmPurQuotationWSBean bean) {
		try {
			List<ScmPurQuotation2> list = bean.getList();
			int type = (int) bean.getObject();
			CommonBeanHelper.toWSBean(biz.undoRelease(list,type, ParamHelper.createParam(bean)), bean);
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmPurQuotationWSBean importPurQuotation(ScmPurQuotationWSBean bean) {
		try {
			ScmPurQuotationAdd scmPurQuotationAdd = (ScmPurQuotationAdd)bean.getObject();
			PurQuotationAddParams purQuotationAddParams = new PurQuotationAddParams();
			BeanUtils.copyProperties(scmPurQuotationAdd, purQuotationAddParams);
			purQuotationAddParams.setDetailList(null);
			
			List<ScmPurQuotaionDetail> detailList = scmPurQuotationAdd.getDetailList();
			List<PurQuotaionDetailParams> detailParamsList=new ArrayList<PurQuotaionDetailParams>();
			for(ScmPurQuotaionDetail scmPurQuotaionDetail : detailList) {
				PurQuotaionDetailParams purQuotaionDetailParams = new PurQuotaionDetailParams();
				BeanUtils.copyProperties(scmPurQuotaionDetail,purQuotaionDetailParams);
				
				detailParamsList.add(purQuotaionDetailParams);
			}
			
			purQuotationAddParams.setDetailList(detailParamsList);
			ScmPurQuotation2 scmPurQuotation = biz.doAddPurQuotation(purQuotationAddParams,ParamHelper.createParam(bean));
			
			bean.setObject(scmPurQuotation);
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmPurQuotationWSBean submit(ScmPurQuotationWSBean bean) {
		try {
			ScmPurQuotation2 scmPurQuotation = (ScmPurQuotation2)bean.getObject();
			bean.setObject(biz.doSubmitPurQuotation(scmPurQuotation,ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmPurQuotationWSBean undoSubmit(ScmPurQuotationWSBean bean) {
		try {
			ScmPurQuotation2 scmPurQuotation = (ScmPurQuotation2)bean.getObject();
			bean.setObject(biz.doUnSubmitPurQuotation(scmPurQuotation,ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmPurQuotationWSBean doAudit(ScmPurQuotationWSBean bean) {
		try {
			bean.setObject(biz.doAuditPurQuotation((CommonAuditParams)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmPurQuotationWSBean undoAudit(ScmPurQuotationWSBean bean) {
		try {
			bean.setObject(biz.doUnAuditPurQuotation((ScmPurQuotation2)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmPurQuotationWSBean finish(ScmPurQuotationWSBean bean) {
		try {
			bean.setObject(biz.finish((ScmPurQuotation2)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmPurQuotationWSBean undoFinish(ScmPurQuotationWSBean bean) {
		try {
			bean.setObject(biz.undoFinish((ScmPurQuotation2)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmPurQuotationWSBean updatePrtCount(ScmPurQuotationWSBean bean) {
		try {
			bean.setObject(biz.updatePrtCount((ScmPurQuotation2)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

}
