package com.armitage.server.iscm.purchasemanage.purchasebusiness.service.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;

import com.armitage.server.api.business.attachment.params.AttachmentByBillTypeParams;
import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Page;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.base.model.QueryParam;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.util.ClassUtils;
import com.armitage.server.iaps.daily.model.Apinvoice2;
import com.armitage.server.iaps.daily.service.ApinvoiceBiz;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmCstFrmLoss2;
import com.armitage.server.iscm.inventorymanage.cstbusiness.service.ScmCstFrmLossBiz;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurPrice2;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurQuotation2;
import com.armitage.server.iscm.purchasemanage.pricemanage.service.ScmPurPriceBiz;
import com.armitage.server.iscm.purchasemanage.pricemanage.service.ScmPurQuotationBiz;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.dao.ScmBaseAttachmentDAO;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmBaseAttachment;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurOrder2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurRequire2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.service.ScmBaseAttachmentBiz;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.service.ScmPurOrderBiz;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.service.ScmPurRequireBiz;
import com.armitage.server.mongodb.biz.MongoDBImageBiz;
import org.springframework.stereotype.Service;

@Service("scmBaseAttachmentBiz")
public class ScmBaseAttachmentBizImpl extends BaseBizImpl<ScmBaseAttachment> implements ScmBaseAttachmentBiz {
	private static final String ISCMDBNAME = "iscm";
	private MongoDBImageBiz mongoDBImageBiz;
	private ScmPurPriceBiz scmPurPriceBiz;
	private ScmPurOrderBiz scmPurOrderBiz;
	private ScmPurRequireBiz scmPurRequireBiz;
	private ApinvoiceBiz apinvoiceBiz;
    private ScmPurQuotationBiz scmPurQuotationBiz;
    private ScmCstFrmLossBiz scmCstFrmLossBiz;

   
	public void setScmCstFrmLossBiz(ScmCstFrmLossBiz scmCstFrmLossBiz) {
		this.scmCstFrmLossBiz = scmCstFrmLossBiz;
	}

	public void setApinvoiceBiz(ApinvoiceBiz apinvoiceBiz) {
		this.apinvoiceBiz = apinvoiceBiz;
	}

	public void setScmPurQuotationBiz(ScmPurQuotationBiz scmPurQuotationBiz) {
		this.scmPurQuotationBiz = scmPurQuotationBiz;
	}

	public void setMongoDBImageBiz(MongoDBImageBiz mongoDBImageBiz) {
		this.mongoDBImageBiz = mongoDBImageBiz;
	}

	public void setScmPurPriceBiz(ScmPurPriceBiz scmPurPriceBiz) {
		this.scmPurPriceBiz = scmPurPriceBiz;
	}

	public void setScmPurOrderBiz(ScmPurOrderBiz scmPurOrderBiz) {
		this.scmPurOrderBiz = scmPurOrderBiz;
	}

	public void setScmPurRequireBiz(ScmPurRequireBiz scmPurRequireBiz) {
		this.scmPurRequireBiz = scmPurRequireBiz;
	}

	@Override
	public ScmBaseAttachment findBybillId(HashedMap hashedMap, Param param) throws AppException {
		return ((ScmBaseAttachmentDAO)dao).findBybillId(hashedMap,param);
	}

	@Override
	public List<ScmBaseAttachment> findBybillTypeId(HashMap<String, Object> hashedMap, Param param) throws AppException {
		return ((ScmBaseAttachmentDAO)dao).findBybillTypeId(hashedMap,param);
	}
	
	@Override
	protected void afterDelete(ScmBaseAttachment entity, Param param) throws AppException {
		if (entity != null) {
			mongoDBImageBiz.delete(ISCMDBNAME, ClassUtils.getFinalModelSimpleName(ScmBaseAttachment.class), entity.getFilePath());
		}
	}

	@Override
	public List<ScmBaseAttachment> queryAttachmentByBillType(AttachmentByBillTypeParams attachmentByBillTypeParams,Param param) throws AppException {
		long billId = 0;
		switch (StringUtils.lowerCase(attachmentByBillTypeParams.getBillType())) {
		     //定价单
			case "scmpurprice": {
				ScmPurPrice2 scmPurPrice2 = scmPurPriceBiz.selectByPmNo(attachmentByBillTypeParams.getBillNo(), param);
				if (scmPurPrice2 != null) {
					billId = scmPurPrice2.getId();
				}
				break;
			}
			//采购订单
			case "purorder": {
				ScmPurOrder2 scmPurOrder2 = scmPurOrderBiz.selectByPoNo(attachmentByBillTypeParams.getBillNo(), param);
				if (scmPurOrder2 != null) {
					billId = scmPurOrder2.getId();
				}
				break;
			}
			//采购订单
			case "purrequire":{
				ScmPurRequire2 scmPurRequire2 = scmPurRequireBiz.selectByPrNo(attachmentByBillTypeParams.getBillNo(), param);
				if (scmPurRequire2 != null) {
					billId = scmPurRequire2.getId();
				}
				break;
			}
			//应付单
			case "apinvoice":{
				Page page = new Page();
				page.setModelClass(Apinvoice2.class);
				page.getParam().put(ScmBaseAttachment.FN_CONTROLUNITNO, new QueryParam(ScmBaseAttachment.FN_CONTROLUNITNO, QueryParam.QUERY_EQ, param.getControlUnitNo()));
				page.getParam().put(Apinvoice2.FN_BILLNO, new QueryParam(Apinvoice2.FN_BILLNO,QueryParam.QUERY_EQ,attachmentByBillTypeParams.getBillNo()));
				List<Apinvoice2> apinvoice2List = apinvoiceBiz.findPage(page,param);
			    if(apinvoice2List!=null &&apinvoice2List.size()>0) {
			    	billId = apinvoice2List.get(0).getBillId();
			    }

				break;
			}
			//报价单
			case "purquotation":{
				Page page = new Page();
				page.setModelClass(ScmPurQuotation2.class);
				page.getParam().put(ScmPurQuotation2.FN_CONTROLUNITNO, new QueryParam("ScmPurQuotation."+ScmPurQuotation2.FN_CONTROLUNITNO, QueryParam.QUERY_EQ, param.getControlUnitNo()));
				page.getParam().put(ScmPurQuotation2.FN_PQNO, new QueryParam(ScmPurQuotation2.FN_PQNO,QueryParam.QUERY_EQ,attachmentByBillTypeParams.getBillNo()));
				List<ScmPurQuotation2> scmPurQuotation2List = scmPurQuotationBiz.findPage(page,param);
			    if(scmPurQuotation2List!=null &&scmPurQuotation2List.size()>0) {
			    	billId = scmPurQuotation2List.get(0).getId();
			    }
				break;
			}
			//成本中心报损
			case "cstfrmloss":{
				Page page = new Page();
				page.setModelClass(ScmCstFrmLoss2.class);
				page.getParam().put(ScmCstFrmLoss2.FN_CONTROLUNITNO, new QueryParam(ScmCstFrmLoss2.FN_CONTROLUNITNO, QueryParam.QUERY_EQ, param.getControlUnitNo()));
				page.getParam().put(ScmCstFrmLoss2.FN_BILLNO, new QueryParam(ScmCstFrmLoss2.FN_BILLNO,QueryParam.QUERY_EQ,attachmentByBillTypeParams.getBillNo()));
				List<ScmCstFrmLoss2> ScmCstFrmLoss2List = scmCstFrmLossBiz.findPage(page,param);
			    if(ScmCstFrmLoss2List!=null &&ScmCstFrmLoss2List.size()>0) {
			    	billId = ScmCstFrmLoss2List.get(0).getId();
			    }
				break;
			}
		}
		Page page = new Page();
		page.setModelClass(ScmBaseAttachment.class);
		page.setShowCount(Integer.MAX_VALUE);
		page.getParam().put(ScmBaseAttachment.FN_BILLID, new QueryParam(ScmBaseAttachment.FN_BILLID, QueryParam.QUERY_EQ, String.valueOf(billId)));
        page.getParam().put(ScmBaseAttachment.FN_BILLTYPE, new QueryParam(ScmBaseAttachment.FN_BILLTYPE, QueryParam.QUERY_EQ, attachmentByBillTypeParams.getBillType()));
        page.getParam().put(ScmBaseAttachment.FN_CONTROLUNITNO, new QueryParam(ScmBaseAttachment.FN_CONTROLUNITNO, QueryParam.QUERY_EQ, param.getControlUnitNo()));
		return this.findPage(page, param);
	}
}
