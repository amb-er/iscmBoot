package com.armitage.server.iscm.purchasemanage.pricemanage.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;

import com.armitage.server.api.business.purprice.params.PurPriceParams;
import com.armitage.server.activity.util.ActivityUtil;
import com.armitage.server.activity.util.AuditMsgUtil;
import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.CommonBean;
import com.armitage.server.common.base.model.Page;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.base.model.QueryParam;
import com.armitage.server.common.base.model.SimpleDataDisplay;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.util.CalendarUtil;
import com.armitage.server.common.util.ClassUtils;
import com.armitage.server.common.util.FormatUtils;
import com.armitage.server.common.util.SimpleDataDisplayUtil;
import com.armitage.server.iscm.basedata.model.PubSysBasicInfo;
import com.armitage.server.iscm.basedata.model.ScmBillPending;
import com.armitage.server.iscm.basedata.model.ScmBillPending2;
import com.armitage.server.iscm.basedata.model.ScmMaterial2;
import com.armitage.server.iscm.basedata.model.ScmSupplierRegInvitation;
import com.armitage.server.iscm.basedata.model.Scmsupplier2;
import com.armitage.server.iscm.basedata.service.PubSysBasicInfoBiz;
import com.armitage.server.iscm.basedata.service.ScmBillPendingBiz;
import com.armitage.server.iscm.basedata.service.ScmMaterialBiz;
import com.armitage.server.iscm.basedata.service.ScmSupplierRegInvitationBiz;
import com.armitage.server.iscm.basedata.service.ScmsupplierBiz;
import com.armitage.server.iscm.common.model.CommonAuditOpinion;
import com.armitage.server.iscm.common.model.CommonEventHistory;
import com.armitage.server.iscm.common.model.ScmSyncData;
import com.armitage.server.iscm.common.service.CommonEventHistoryBiz;
import com.armitage.server.iscm.common.service.ScmSyncDataBiz;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvPurInWarehsBillEntry2;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.service.ScmInvPurInWarehsBillEntryBiz;
import com.armitage.server.iscm.purchasemanage.pricemanage.dao.ScmPurPriceDAO;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmMaterialPrePrice;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmMaterialPrice;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurPrice;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurPrice2;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurPriceAdvQuery;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurPriceAssign2;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurPriceEntry2;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurPriceImPort;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurPriceImPortDetail;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurPricePrepareEntry2;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurPriceQuery;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurQuotation2;
import com.armitage.server.iscm.purchasemanage.pricemanage.service.ScmPurPriceAssignBiz;
import com.armitage.server.iscm.purchasemanage.pricemanage.service.ScmPurPriceBiz;
import com.armitage.server.iscm.purchasemanage.pricemanage.service.ScmPurPriceEntryBiz;
import com.armitage.server.iscm.purchasemanage.pricemanage.service.ScmPurPricePrepareEntryBiz;
import com.armitage.server.iscm.purchasemanage.pricemanage.service.ScmPurQuotationBiz;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.CommonAuditParams;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmBaseAttachment;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurOrderEntry2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurReceiveEntry2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurRequire2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurRequireEntry;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurRequireEntry2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.service.ScmBaseAttachmentBiz;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.service.ScmPurOrderEntryBiz;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.service.ScmPurReceiveEntryBiz;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.service.ScmPurRequireBiz;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.service.ScmPurRequireEntryBiz;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurInquiryGroup;
import com.armitage.server.iscm.purchasemanage.purchasesetting.service.ScmPurInquiryGroupBiz;
import com.armitage.server.quartz.util.SupplierPlatUtil;
import com.armitage.server.system.model.BillType2;
import com.armitage.server.system.model.Code;
import com.armitage.server.system.model.OrgBaseUnit;
import com.armitage.server.system.model.OrgCompany2;
import com.armitage.server.system.model.OrgPurchase2;
import com.armitage.server.system.model.SystemState;
import com.armitage.server.system.service.BillTypeBiz;
import com.armitage.server.system.service.CodeBiz;
import com.armitage.server.system.service.OrgBaseUnitBiz;
import com.armitage.server.system.service.OrgPurchaseBiz;
import com.armitage.server.system.service.OrgUnitRelationBiz;
import com.armitage.server.system.service.SysParamBiz;
import com.armitage.server.system.service.SystemStateBiz;
import com.armitage.server.system.util.CodeAutoCalUtil;
import com.armitage.server.system.util.OrgUnitRelationType;
import com.armitage.server.user.model.Usr;
import com.armitage.server.user.service.UsrBiz;
import org.springframework.stereotype.Service;

@Service("scmPurPriceBiz")
public class ScmPurPriceBizImpl extends BaseBizImpl<ScmPurPrice2> implements ScmPurPriceBiz {

	private static Log log = LogFactory.getLog(ScmPurPriceBizImpl.class);
	private ScmPurPriceEntryBiz scmPurPriceEntryBiz;
	private OrgBaseUnitBiz orgBaseUnitBiz;
	private UsrBiz usrBiz;
	private ScmsupplierBiz scmsupplierBiz;
	private ScmMaterialBiz scmMaterialBiz;
	private PubSysBasicInfoBiz pubSysBasicInfoBiz;
	private ScmPurInquiryGroupBiz scmPurInquiryGroupBiz;
	private SystemStateBiz systemStateBiz;
	private OrgUnitRelationBiz orgUnitRelationBiz;
	private CodeBiz codeBiz;
	private BillTypeBiz billTypeBiz;
	private ScmBillPendingBiz scmBillPendingBiz;
	private SysParamBiz sysParamBiz;
	private CommonEventHistoryBiz commonEventHistoryBiz;
	private OrgPurchaseBiz orgPurchaseBiz;
	private ScmPurPriceAssignBiz scmPurPriceAssignBiz;
	private ScmPurRequireEntryBiz scmPurRequireEntryBiz;
	private ScmPurRequireBiz scmPurRequireBiz;
	private ScmPurOrderEntryBiz scmPurOrderEntryBiz;
	private ScmPurPricePrepareEntryBiz scmPurPricePrepareEntryBiz;
	private ScmBaseAttachmentBiz scmBaseAttachmentBiz;
	private ScmPurReceiveEntryBiz scmPurReceiveEntryBiz;
	private ScmInvPurInWarehsBillEntryBiz scmInvPurInWarehsBillEntryBiz;
	private ScmSupplierRegInvitationBiz scmSupplierRegInvitationBiz;
	private ScmSyncDataBiz scmSyncDataBiz;
	private ScmPurQuotationBiz scmPurQuotationBiz;

	public void setScmPurPricePrepareEntryBiz(ScmPurPricePrepareEntryBiz scmPurPricePrepareEntryBiz) {
		this.scmPurPricePrepareEntryBiz = scmPurPricePrepareEntryBiz;
	}

	public void setScmInvPurInWarehsBillEntryBiz(ScmInvPurInWarehsBillEntryBiz scmInvPurInWarehsBillEntryBiz) {
		this.scmInvPurInWarehsBillEntryBiz = scmInvPurInWarehsBillEntryBiz;
	}

	public void setScmPurReceiveEntryBiz(ScmPurReceiveEntryBiz scmPurReceiveEntryBiz) {
		this.scmPurReceiveEntryBiz = scmPurReceiveEntryBiz;
	}

	public void setScmPurOrderEntryBiz(ScmPurOrderEntryBiz scmPurOrderEntryBiz) {
		this.scmPurOrderEntryBiz = scmPurOrderEntryBiz;
	}

	public void setScmPurPriceEntryBiz(ScmPurPriceEntryBiz scmPurPriceEntryBiz) {
		this.scmPurPriceEntryBiz = scmPurPriceEntryBiz;
	}
	
	public void setOrgBaseUnitBiz(OrgBaseUnitBiz orgBaseUnitBiz) {
		this.orgBaseUnitBiz = orgBaseUnitBiz;
	}

	public void setUsrBiz(UsrBiz usrBiz) {
		this.usrBiz = usrBiz;
	}

	public void setScmsupplierBiz(ScmsupplierBiz scmsupplierBiz) {
		this.scmsupplierBiz = scmsupplierBiz;
	}
	
	public static void setLog(Log log) {
        ScmPurPriceBizImpl.log = log;
    }

    public ScmMaterialBiz getScmMaterialBiz() {
		return scmMaterialBiz;
	}

	public void setScmMaterialBiz(ScmMaterialBiz scmMaterialBiz) {
		this.scmMaterialBiz = scmMaterialBiz;
	}

    public void setPubSysBasicInfoBiz(PubSysBasicInfoBiz pubSysBasicInfoBiz) {
		this.pubSysBasicInfoBiz = pubSysBasicInfoBiz;
	}

	public void setScmPurInquiryGroupBiz(ScmPurInquiryGroupBiz scmPurInquiryGroupBiz) {
		this.scmPurInquiryGroupBiz = scmPurInquiryGroupBiz;
	}

    public void setSystemStateBiz(SystemStateBiz systemStateBiz) {
        this.systemStateBiz = systemStateBiz;
    }

    public void setOrgUnitRelationBiz(OrgUnitRelationBiz orgUnitRelationBiz) {
        this.orgUnitRelationBiz = orgUnitRelationBiz;
    }
    
    public void setCodeBiz(CodeBiz codeBiz) {
		this.codeBiz = codeBiz;
	}
    
    public void setBillTypeBiz(BillTypeBiz billTypeBiz) {
		this.billTypeBiz = billTypeBiz;
	}

    public void setScmBillPendingBiz(ScmBillPendingBiz scmBillPendingBiz) {
		this.scmBillPendingBiz = scmBillPendingBiz;
	}

	public void setSysParamBiz(SysParamBiz sysParamBiz) {
		this.sysParamBiz = sysParamBiz;
	}

	public void setCommonEventHistoryBiz(CommonEventHistoryBiz commonEventHistoryBiz) {
		this.commonEventHistoryBiz = commonEventHistoryBiz;
	}
	
	public void setOrgPurchaseBiz(OrgPurchaseBiz orgPurchaseBiz) {
		this.orgPurchaseBiz = orgPurchaseBiz;
	}
	
	public void setScmPurPriceAssignBiz(ScmPurPriceAssignBiz scmPurPriceAssignBiz) {
		this.scmPurPriceAssignBiz = scmPurPriceAssignBiz;
	}
	
	public void setScmPurRequireEntryBiz(ScmPurRequireEntryBiz scmPurRequireEntryBiz) {
		this.scmPurRequireEntryBiz = scmPurRequireEntryBiz;
	}
	
	public void setScmPurRequireBiz(ScmPurRequireBiz scmPurRequireBiz) {
		this.scmPurRequireBiz = scmPurRequireBiz;
	}
	
	public void setScmBaseAttachmentBiz(ScmBaseAttachmentBiz scmBaseAttachmentBiz) {
		this.scmBaseAttachmentBiz = scmBaseAttachmentBiz;
	}

	public void setScmSupplierRegInvitationBiz(ScmSupplierRegInvitationBiz scmSupplierRegInvitationBiz) {
		this.scmSupplierRegInvitationBiz = scmSupplierRegInvitationBiz;
	}

	public void setScmSyncDataBiz(ScmSyncDataBiz scmSyncDataBiz) {
		this.scmSyncDataBiz = scmSyncDataBiz;
	}

	public void setScmPurQuotationBiz(ScmPurQuotationBiz scmPurQuotationBiz) {
		this.scmPurQuotationBiz = scmPurQuotationBiz;
	}

	@Override
	protected void beforeFindPage(Page page, Param param) throws AppException {
		List<OrgPurchase2> orgPurchaseList = orgPurchaseBiz.findChild(param.getOrgUnitNo(), param);
		if (orgPurchaseList != null && !orgPurchaseList.isEmpty()) {
			StringBuffer orgunitList = new StringBuffer("");
			for (OrgPurchase2 orgPurchase : orgPurchaseList) {
				if(StringUtils.isNotBlank(orgunitList.toString()))
					orgunitList.append(",");
				orgunitList.append("'").append(orgPurchase.getOrgUnitNo()).append("'");
			}
			page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmPurPrice2.class) + "." + ScmPurPrice2.FN_ORGUNITNO), 
					new QueryParam((ClassUtils.getFinalModelSimpleName(ScmPurPrice2.class) + "." + ScmPurPrice2.FN_ORGUNITNO), QueryParam.QUERY_IN, orgunitList.toString()));
		}else{
			page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmPurPrice2.class) + "." + ScmPurPrice2.FN_ORGUNITNO), 
					new QueryParam((ClassUtils.getFinalModelSimpleName(ScmPurPrice2.class) + "." + ScmPurPrice2.FN_ORGUNITNO), QueryParam.QUERY_EQ, "0"));
		}
	}
    
	@Override
	protected void beforeDelete(ScmPurPrice2 entity, Param param)
			throws AppException {
		ScmPurPrice2 scmPurPrice = this.selectDirect(entity.getId(), param);
		if(scmPurPrice!=null && !StringUtils.equals(scmPurPrice.getStatus(),"I")){
			throw new AppException(Message.getMessage(param.getLang(), "iscm.inventorymanage.delete.error", new String[]{entity.getPmNo()}));
		}
		SupplierPlatUtil supplierPlatUtil = new SupplierPlatUtil();
		supplierPlatUtil.changeSyncData(scmPurPrice, param);
	}

	@Override
	protected void afterFindPage(List list, Page page, Param param)
			throws AppException {
		if(list != null && !list.isEmpty()){
			for(ScmPurPrice2 scmPurPrice : (List<ScmPurPrice2>)list){
				//视图增加待审人
				ScmBillPending2 scmBillPendingUsr = scmBillPendingBiz.selectPendingUsr(scmPurPrice.getId(), "ScmPurPrice",param);
				StringBuffer usrName = new StringBuffer("");
				if (scmBillPendingUsr != null) {
					scmPurPrice.setPendingUsr(scmBillPendingUsr.getUsrCodes());
					String[] usrCodes = StringUtils.split(scmBillPendingUsr.getUsrCodes(), ",");
					for(String usrCode : usrCodes) {
						Usr usr = usrBiz.selectByCode(usrCode, param);
						if(usr != null) {
							if(StringUtils.isNotBlank(usrName.toString())) 
								usrName.append(",");
							usrName.append(usr.getName());
						}
					}
				}
				scmPurPrice.setPendingUsrName(usrName.toString());
				//设置商户报价信息
				if(scmPurPrice.isBusinessAutoQuotation()){
					ScmSyncData scmSyncData = new ScmSyncData(true);
					scmSyncData.setOrgUnitNo(scmPurPrice.getOrgUnitNo());
					scmSyncData.setDataClass("Bill");
					scmSyncData.setDataId(scmPurPrice.getId());
					scmSyncData.setBillType("ScmPurPrice");
					scmSyncData.setTaskCode("SCM_ESP");
					scmSyncData.setControlUnitNo(param.getControlUnitNo());
					ScmSyncData scmSyncData2 = scmSyncDataBiz.selectByScmSyncData(scmSyncData, param);
					if(scmSyncData2 != null){
						String vendorIds = scmPurPrice.getVendorId1()+","+scmPurPrice.getVendorId2()+","+scmPurPrice.getVendorId3();
						List<ScmSupplierRegInvitation> scmSupplierRegInvitationList = scmSupplierRegInvitationBiz.selectBindedByCtrlAndVendor(param.getControlUnitNo(),vendorIds,param);
						if(scmSupplierRegInvitationList != null && !scmSupplierRegInvitationList.isEmpty()){
							int vendorPqCount = 0,vendorNoPqCount = 0;
							for(ScmSupplierRegInvitation scmSupplierRegInvitation : scmSupplierRegInvitationList){
				                if(scmSupplierRegInvitation.getVendorId() == scmPurPrice.getVendorId1()){
				                	if(scmPurPrice.getVendorPqDate1() != null){
				                		vendorPqCount++;
				                	}else{
				                		vendorNoPqCount++;
				                	}
				                }else if(scmSupplierRegInvitation.getVendorId() == scmPurPrice.getVendorId2()){
				                	if(scmPurPrice.getVendorPqDate2() != null){
				                		vendorPqCount++;
				                	}else{
				                		vendorNoPqCount++;
				                	}
				                }else if(scmSupplierRegInvitation.getVendorId() == scmPurPrice.getVendorId3()){
				                	if(scmPurPrice.getVendorPqDate3() != null){
				                		vendorPqCount++;
				                	}else{
				                		vendorNoPqCount++;
				                	}
				                }
				            }
							if(vendorPqCount >= 1 && vendorNoPqCount >= 1){
								scmPurPrice.setBusinessQuotationSign("part");
							}else if(vendorPqCount > 0 && vendorNoPqCount == 0){
								scmPurPrice.setBusinessQuotationSign("all");
							}else{
								scmPurPrice.setBusinessQuotationSign("none");
							}
						}
					}
				}
				setConvertMap(scmPurPrice,param);
				if("I,R".contains(scmPurPrice.getStatus())) {
					scmPurPrice.setPendingUsrName("");
				}
			}
		}
	}

	@Override
	protected void afterSelect(ScmPurPrice2 entity, Param param) throws AppException {
		if (entity != null){
			setConvertMap(entity,param);
		}
	}
	
	private void setConvertMap(ScmPurPrice2 scmPurPrice, Param param) throws AppException {
		if(StringUtils.isNotBlank(scmPurPrice.getOrgUnitNo())){
			//采购组织
			OrgBaseUnit orgBaseUnit = orgBaseUnitBiz.selectbyOrgNo(scmPurPrice.getOrgUnitNo(), param);
			if (orgBaseUnit != null) {
				scmPurPrice.setConvertMap(ScmPurPrice2.FN_ORGUNITNO, orgBaseUnit);
				scmPurPrice.setOrgUnitName(orgBaseUnit.getOrgUnitName());
			}
		}
		if(StringUtils.isNotBlank(scmPurPrice.getFinOrgUnitNo())){
			//财务组织
			OrgBaseUnit orgBaseUnit = orgBaseUnitBiz.selectbyOrgNo(scmPurPrice.getFinOrgUnitNo(), param);
			if (orgBaseUnit != null) {
				scmPurPrice.setFinOrgUnitName(orgBaseUnit.getOrgUnitName());
			}
		}
		if (StringUtils.isNotBlank(scmPurPrice.getCreator())){
			//制单人
			Usr usr = usrBiz.selectByCode(scmPurPrice.getCreator(), param);
			if (usr != null) {
				scmPurPrice.setConvertMap(ScmPurPrice2.FN_CREATOR, usr);
				if(scmPurPrice.getDataDisplayMap()==null){
					scmPurPrice.setDataDisplayMap(new HashMap<String,SimpleDataDisplay>());
				}
				scmPurPrice.getDataDisplayMap().put(ScmPurPrice2.FN_CREATOR, SimpleDataDisplayUtil.convert(usr));
				scmPurPrice.setCreatorName(usr.getName());
			}
		}
		if(scmPurPrice.getSelVndrId() > 0){
			//供应商
			Scmsupplier2 scmsupplier = scmsupplierBiz.selectDirect(scmPurPrice.getSelVndrId(), param);
			if(scmsupplier != null){
				scmPurPrice.setConvertMap(ScmPurPrice2.FN_SELVNDRID, scmsupplier);
				scmPurPrice.setSelVndrCode(scmsupplier.getVendorNo());
				scmPurPrice.setSelVndrName(scmsupplier.getVendorName());
			}
		}
		//供应商1
		if(scmPurPrice.getVendorId1()>0){
			Scmsupplier2 scmsupplier = scmsupplierBiz.selectDirect(scmPurPrice.getVendorId1(), param);
			if(scmsupplier != null){
				scmPurPrice.setVendorName1(scmsupplier.getVendorName());
				scmPurPrice.setConvertMap(ScmPurPrice2.FN_VENDORID1, scmsupplier);
			}
		}
		//供应商2
		if(scmPurPrice.getVendorId2()>0){
			Scmsupplier2 scmsupplier = scmsupplierBiz.selectDirect(scmPurPrice.getVendorId2(), param);
			if(scmsupplier != null){
				scmPurPrice.setVendorName2(scmsupplier.getVendorName());
				scmPurPrice.setConvertMap(ScmPurPrice2.FN_VENDORID2, scmsupplier);
			}
		}
		//供应商3
		if(scmPurPrice.getVendorId3()>0){
			Scmsupplier2 scmsupplier = scmsupplierBiz.selectDirect(scmPurPrice.getVendorId3(), param);
			if(scmsupplier != null){
				scmPurPrice.setVendorName3(scmsupplier.getVendorName());
				scmPurPrice.setConvertMap(ScmPurPrice2.FN_VENDORID3, scmsupplier);
			}
		}
		//市调组1
		if(scmPurPrice.getGroupId1()>0){
			ScmPurInquiryGroup scmPurInquiryGroup = scmPurInquiryGroupBiz.selectDirect(scmPurPrice.getGroupId1(), param);
			if(scmPurInquiryGroup!=null)
				scmPurPrice.setGroupName1(scmPurInquiryGroup.getGroupName());
		}
		//市调组2
		if(scmPurPrice.getGroupId2()>0){
			ScmPurInquiryGroup scmPurInquiryGroup = scmPurInquiryGroupBiz.selectDirect(scmPurPrice.getGroupId2(), param);
			if(scmPurInquiryGroup!=null)
				scmPurPrice.setGroupName2(scmPurInquiryGroup.getGroupName());
		}
		//市调组1
		if(scmPurPrice.getGroupId3()>0){
			ScmPurInquiryGroup scmPurInquiryGroup = scmPurInquiryGroupBiz.selectDirect(scmPurPrice.getGroupId3(), param);
			if(scmPurInquiryGroup!=null)
				scmPurPrice.setGroupName3(scmPurInquiryGroup.getGroupName());
		}
		if (StringUtils.isNotBlank(scmPurPrice.getPriceName())){
			//定价员
			Usr usr = usrBiz.selectByCode(scmPurPrice.getPriceName(), param);
			if (usr != null) {
				scmPurPrice.setPriceOfficer(usr.getName());
				scmPurPrice.setConvertMap(ScmPurPrice2.FN_PRICENAME, usr);
			}
		}
		if (StringUtils.isNotBlank(scmPurPrice.getEditor())){
			//修改人
			Usr usr = usrBiz.selectByCode(scmPurPrice.getEditor(), param);
			if (usr != null) {
				scmPurPrice.setConvertMap(ScmPurPrice2.FN_EDITOR, usr);
				scmPurPrice.setEditorName(usr.getName());
			}
		}
		if (StringUtils.isNotBlank(scmPurPrice.getChecker())){
			//审核人
			Usr usr = usrBiz.selectByCode(scmPurPrice.getChecker(), param);
			if (usr != null) {
				scmPurPrice.setConvertMap(ScmPurPrice2.FN_CHECKER, usr);
				scmPurPrice.setCheckerName(usr.getName());
			}
		}
		if(StringUtils.isNotBlank(scmPurPrice.getStatus())){
			Code code = codeBiz.selectByCategoryAndCode("quotationStatus", scmPurPrice.getStatus());
			if(code!=null)
				scmPurPrice.setStatusName(code.getName());
		}
		if(StringUtils.isNotBlank(scmPurPrice.getSelVndrIdDtl())){
			String[] selVndrIdDtls = StringUtils.split(scmPurPrice.getSelVndrIdDtl(), ",");
			StringBuffer selVndrIdDtlName = new StringBuffer("");
			for(String selVndrIdDtl : selVndrIdDtls) {
				Scmsupplier2 scmsupplier = scmsupplierBiz.selectDirect(Integer.parseInt(selVndrIdDtl), param);
				if(scmsupplier != null) {
					if(StringUtils.isNotBlank(selVndrIdDtlName.toString())) 
						selVndrIdDtlName.append(",");
					selVndrIdDtlName.append(scmsupplier.getVendorName());
				}
			}
			scmPurPrice.setSelVndrIdDtlName(selVndrIdDtlName.toString());
		}
	}
	
	@Override
	protected void afterSelect(CommonBean bean, Param param) throws AppException {
		ScmPurPrice2 scmPurPrice = (ScmPurPrice2) bean.getList().get(0);
		if(scmPurPrice != null && scmPurPrice.getId() > 0){
			List<ScmPurPriceEntry2> scmPurPriceEntryList = scmPurPriceEntryBiz.selectByPmId(scmPurPrice.getId(), param);
			if(scmPurPriceEntryList != null && !scmPurPriceEntryList.isEmpty()){
				for(ScmPurPriceEntry2 scmPurPriceEntry:scmPurPriceEntryList){
					if (scmPurPriceEntry.getTaxRate() != null){
						//税率
						PubSysBasicInfo pubSysBasicInfo = pubSysBasicInfoBiz.selectByTaxRate("TaxRate", scmPurPriceEntry.getTaxRate().toString(), null, param);
						if (pubSysBasicInfo != null) {
							scmPurPriceEntry.setTaxRateStr(pubSysBasicInfo.getFInfoNo());
							scmPurPriceEntry.setConvertMap(ScmPurPriceEntry2.FN_TAXRATESTR, pubSysBasicInfo);
						}
					}
				}
				bean.setList2(scmPurPriceEntryList);
			}
			
			List<ScmPurPriceAssign2> scmPurPriceAssignList = scmPurPriceAssignBiz.selectByPmId(scmPurPrice.getId(), param);
			if(scmPurPriceAssignList != null && !scmPurPriceAssignList.isEmpty()){
				bean.setList3(scmPurPriceAssignList);
			}
			
			//备选定价明细
			List<ScmPurPricePrepareEntry2> scmPurPricePrepareEntryList = scmPurPricePrepareEntryBiz.selectByPmId(scmPurPrice.getId(), param);
			if(scmPurPricePrepareEntryList != null && !scmPurPricePrepareEntryList.isEmpty()){
				bean.setList6(scmPurPricePrepareEntryList);
			}
		} 
	}
	
	@Override
	protected void beforeAdd(ScmPurPrice2 entity, Param param) throws AppException {
		if(entity != null){
			String code = CodeAutoCalUtil.getBillCode("ScmPurPrice", entity, param);
			entity.setPmNo(code);
			List<OrgCompany2> orgCompanyList = orgUnitRelationBiz.findToOrgUnitByType(OrgUnitRelationType.PURTOFIN, entity.getOrgUnitNo(), false, null, param);
			if(orgCompanyList==null || orgCompanyList.isEmpty()){
				throw new AppException("iscm.purchasemanage.purchasebusiness.ScmPurRequireBizImpl.error.notfin");
			}
			entity.setFinOrgUnitNo(orgCompanyList.get(0).getOrgUnitNo());
//			BillType2 billType = billTypeBiz.selectByOrgAndCode(entity.getFinOrgUnitNo(), "ScmPurPrice", param);
//			if(billType!=null && billType.isNeedAudit()) {
//				if(StringUtils.isBlank(billType.getWorkFlowNo())) {
//					throw new AppException(Message.getMessage(param.getLang(), "iscm.workflow.needaudit.notprocess", new String[] {Message.getMessage(param.getLang(),"field.ScmPurPrice")}));
//				}
//				entity.setWorkFlowNo(billType.getWorkFlowNo());
//			}
		}
	}
	
	@Override
	protected void afterAdd(CommonBean bean, Param param) throws AppException {
		ScmPurPrice2 scmPurPrice = (ScmPurPrice2) bean.getList().get(0);
		if(scmPurPrice != null && scmPurPrice.getId() > 0){
			//新增定价明细
			List<ScmPurPriceEntry2> scmPurPriceEntryList = bean.getList2();
			if(scmPurPriceEntryList != null && !scmPurPriceEntryList.isEmpty()){
				int lineId = 1;
				for(ScmPurPriceEntry2 scmPurPriceEntry : scmPurPriceEntryList){
					scmPurPriceEntry.setLineId(lineId);
					scmPurPriceEntry.setPmId(scmPurPrice.getId());
					if(scmPurPrice.getSelVndrId()>0 && scmPurPriceEntry.getSelVndrId()==0)
						scmPurPriceEntry.setSelVndrId(scmPurPrice.getSelVndrId());
					scmPurPriceEntryBiz.add(scmPurPriceEntry, param);
					lineId = lineId+1;
				}
			}
			//新增指定组织
			List<ScmPurPriceAssign2> scmPurPriceAssignList = bean.getList3();
			if(scmPurPriceAssignList != null && !scmPurPriceAssignList.isEmpty()){
				int lineId = 1;
				for(ScmPurPriceAssign2 scmPurPriceAssign : scmPurPriceAssignList){
					scmPurPriceAssign.setPmId(scmPurPrice.getId());
					
					scmPurPriceAssignBiz.add(scmPurPriceAssign, param);
					lineId = lineId+1;
				}
			}
			//临时定价单保存时刷新请购明细价格来源
			if("2".equals(scmPurPrice.getBizType())){
				List<ScmPurRequireEntry2> scmPurRequireEntryList = bean.getList4();
				if(scmPurRequireEntryList != null && !scmPurRequireEntryList.isEmpty()
						&& scmPurPriceEntryList != null && !scmPurPriceEntryList.isEmpty()){
					for(ScmPurRequireEntry2 scmPurRequireEntry : scmPurRequireEntryList) {
						for(ScmPurPriceEntry2 scmPurPriceEntry : scmPurPriceEntryList){
							if(scmPurRequireEntry.getItemId() == scmPurPriceEntry.getItemId()){
								if(scmPurPriceEntry.getTaxRate().compareTo(BigDecimal.ZERO)>0){
									scmPurRequireEntry.setRefPriceStatus("7");  	//临时定价定税
								}else{
									scmPurRequireEntry.setRefPriceStatus("6");  	//临时定价不定税
								}
								scmPurRequireEntry.setPriceBillId(scmPurPrice.getId());
					        	scmPurRequireEntryBiz.updateDirect(scmPurRequireEntry, param);
					        	break;
							}
						}
			        }
				}
				
				List<ScmPurOrderEntry2> scmPurOrderEntryList = bean.getList5();
				if(scmPurOrderEntryList != null && !scmPurOrderEntryList.isEmpty()
						&& scmPurPriceEntryList != null && !scmPurPriceEntryList.isEmpty()){
					for(ScmPurOrderEntry2 scmPurOrderEntry : scmPurOrderEntryList) {
						for(ScmPurPriceEntry2 scmPurPriceEntry : scmPurPriceEntryList){
							if(scmPurPriceEntry.getTaxRate().compareTo(BigDecimal.ZERO)>0){
								scmPurOrderEntry.setRefPriceStatus("7");  	//临时定价定税
							}else{
								scmPurOrderEntry.setRefPriceStatus("6");  	//临时定价不定税
							}
							scmPurOrderEntry.setPriceBillId(scmPurPrice.getId());
							scmPurOrderEntryBiz.updateDirect(scmPurOrderEntry, param);
				        	break;
						}
			        }
				}
			}
			
			//新增备选定价明细
			List<ScmPurPricePrepareEntry2> scmPurPricePrepareEntryList = bean.getList6();
			if(scmPurPricePrepareEntryList != null && !scmPurPricePrepareEntryList.isEmpty()){
				for(ScmPurPricePrepareEntry2 scmPurPricePrepareEntry : scmPurPricePrepareEntryList){
					scmPurPricePrepareEntry.setPmId(scmPurPrice.getId());
					scmPurPricePrepareEntryBiz.add(scmPurPricePrepareEntry, param);
				}
			}
		}
	}
	

	@Override
	protected void afterUpdate(CommonBean bean, Param param) throws AppException {
		ScmPurPrice2 scmPurPrice = (ScmPurPrice2) bean.getList().get(0);
		if(scmPurPrice != null && scmPurPrice.getId() > 0){
			//更新定价明细
			List<ScmPurPriceEntry2> scmPurPriceEntryList = bean.getList2();
			List<ScmPurPriceEntry2> orgScmPurPriceEntryList = new ArrayList<>();
			if(scmPurPriceEntryList != null && !scmPurPriceEntryList.isEmpty()){
				int lineId = 1;
				for(ScmPurPriceEntry2 scmPurPriceEntry : scmPurPriceEntryList){
					if (StringUtils.equals("I", scmPurPrice.getStatus())) {
						scmPurPriceEntry.setLineId(lineId);
					}
					scmPurPriceEntry.setPmId(scmPurPrice.getId());
					lineId = lineId+1;
				}
				if (StringUtils.equals("2", scmPurPrice.getBizType())) {
					//临时定价明细删除时，清空对应的请购单单的价格来源
					orgScmPurPriceEntryList = scmPurPriceEntryBiz.selectByPmId(scmPurPrice.getId(), param);
				}
				scmPurPriceEntryBiz.update(scmPurPrice, scmPurPriceEntryList, ScmPurPriceEntry2.FN_PMID, param);
				
				if (!orgScmPurPriceEntryList.isEmpty() 
						&& orgScmPurPriceEntryList.size() != scmPurPriceEntryList.size()) {
					updatePurRequestPriceBillIdByPmId(orgScmPurPriceEntryList, scmPurPriceEntryList, param);
				}
			}
			
			//更新指定组织
			List<ScmPurPriceAssign2> scmPurPriceAssignList = bean.getList3();
			if(scmPurPriceAssignList != null && !scmPurPriceAssignList.isEmpty()){
				int lineId = 1;
				for(ScmPurPriceAssign2 scmPurPriceAssign : scmPurPriceAssignList){
					scmPurPriceAssign.setPmId(scmPurPrice.getId());
				}
				scmPurPriceAssignBiz.update(scmPurPrice, scmPurPriceAssignList, ScmPurPriceEntry2.FN_PMID, param);
			}
			
			//更新备选定价明细
			List<ScmPurPricePrepareEntry2> scmPurPricePrepareEntryList = bean.getList6();
			if(scmPurPricePrepareEntryList != null && !scmPurPricePrepareEntryList.isEmpty()){
				for(ScmPurPricePrepareEntry2 scmPurPricePrepareEntry : scmPurPricePrepareEntryList){
					scmPurPricePrepareEntry.setPmId(scmPurPrice.getId());
				}
				scmPurPricePrepareEntryBiz.update(scmPurPrice, scmPurPricePrepareEntryList, ScmPurPriceEntry2.FN_PMID, param);
			}
		}
	}
	
	@Override
	protected void afterUpdate(ScmPurPrice2 oldEntity, ScmPurPrice2 newEntity, Param param) throws AppException {
		if(oldEntity!=null && newEntity!=null && oldEntity.isBusinessAutoQuotation() && !newEntity.isBusinessAutoQuotation()) {
			SupplierPlatUtil supplierPlatUtil = new SupplierPlatUtil();
			supplierPlatUtil.changeSyncData(oldEntity, param);
		}
	}
	
	private void updatePurRequestPriceBillIdByPmId(List<ScmPurPriceEntry2> orgList, List<ScmPurPriceEntry2> newList, Param param) {
		for(int i = 0; i < orgList.size(); i++) {
			boolean exists = false;
			for(int j = 0; j < newList.size(); j++) {
				if(orgList.get(i).getId() == newList.get(j).getId()) {
					exists = true;
					break;
				}
			}
			if(!exists) {
				scmPurRequireEntryBiz.updatePurRequestPriceBillIdByPmId(orgList.get(i), param);
			}
		}
	} 
	
	@Override
	protected void afterDelete(ScmPurPrice2 entity, Param param) throws AppException {
		if(entity != null && entity.getId() > 0){
			//删除请购单明细上的价格来源信息
			//clearPurRequirePrice(entity, param);
			//删除定价明细
			scmPurPriceEntryBiz.deleteByPmId(entity.getId(), param);
			//删除订货单明细上临时定价单来源
			scmPurOrderEntryBiz.updatePurOrderPriceBillIdByPmId(entity.getId(), param);
			//删除备选定价明细
			scmPurPricePrepareEntryBiz.deleteByPmId(entity.getId(), param);
			//删除附件
			scmBaseAttachmentBiz.update(entity,null,ScmBaseAttachment.FN_BILLID,param);
		}
	}

	private void clearPurRequirePrice(ScmPurPrice2 entity, Param param) {
		if (entity !=null) {
			HashMap<Object, Object> map = new HashMap<>();
			map.put(ScmPurRequireEntry.FN_PRICEBILLID, entity.getId());
			List<ScmPurRequireEntry2> scmPurRequireEntry2s=scmPurRequireEntryBiz.clearPurRequirePrice(map, param);//获取使用的单据
			if (scmPurRequireEntry2s !=null && scmPurRequireEntry2s.size()>0) {
				boolean check=true;
				for (ScmPurRequireEntry2 scmPurRequireEntry2 : scmPurRequireEntry2s) {
					if (!StringUtils.equals("I", scmPurRequireEntry2.getRowStatus())) {
						check=false;
						throw new AppException(Message.getMessage(param.getLang(),"iscm.purchasemanage.pricemanage.ScmPurPriceBizImpl.purPriceDeleteError"));
					}
				}
				if (check) {
					for (ScmPurRequireEntry2 scmPurRequireEntry2 : scmPurRequireEntry2s) {
						if (StringUtils.equals("1", scmPurRequireEntry2.getBizType())) {// 请购单
							ScmPurRequireEntry2 scmPurRequireEntry = scmPurRequireEntryBiz.select(scmPurRequireEntry2.getId(), param);
							scmPurRequireEntry.setVendorId(0);
							scmPurRequireEntry.setPriceBillId(0);
							scmPurRequireEntry.setPriceBillNo(null);
							scmPurRequireEntry.setAmt(BigDecimal.ZERO);
							scmPurRequireEntry.setPrice(BigDecimal.ZERO);
							scmPurRequireEntry.setRefPriceStatus("0");
							scmPurRequireEntryBiz.update(scmPurRequireEntry, param);
						} else if (StringUtils.equals("2", scmPurRequireEntry2.getBizType())) {// 订货单
							ScmPurOrderEntry2 scmPurOrderEntry = scmPurOrderEntryBiz.select(scmPurRequireEntry2.getId(), param);
							scmPurOrderEntry.setAmt(BigDecimal.ZERO);
							scmPurOrderEntry.setPrice(BigDecimal.ZERO);
							scmPurOrderEntry.setRefPriceStatus("0");
							scmPurOrderEntryBiz.update(scmPurOrderEntry, param);
						} else if (StringUtils.equals("3", scmPurRequireEntry2.getBizType())) {// 收货单
							ScmPurReceiveEntry2 scmPurReceiveEntry = scmPurReceiveEntryBiz.select(scmPurRequireEntry2.getId(), param);
							scmPurReceiveEntry.setAmt(BigDecimal.ZERO);
							scmPurReceiveEntry.setPrice(BigDecimal.ZERO);
							scmPurReceiveEntry.setRefPriceStatus("0");
							scmPurReceiveEntryBiz.update(scmPurReceiveEntry, param);
						} else if (StringUtils.equals("4", scmPurRequireEntry2.getBizType())) {// 采购入库
							ScmInvPurInWarehsBillEntry2 scmInvPurInWarehsBillEntry = scmInvPurInWarehsBillEntryBiz.select(scmPurRequireEntry2.getId(), param);
							scmInvPurInWarehsBillEntry.setAmt(BigDecimal.ZERO);
							scmInvPurInWarehsBillEntry.setPrice(BigDecimal.ZERO);
							scmInvPurInWarehsBillEntry.setRefPriceStatus("0");
							scmInvPurInWarehsBillEntryBiz.update(scmInvPurInWarehsBillEntry, param);
						}
					}
				}
			}
		}
	}
	
	private ScmPurPrice2 updateStatus(ScmPurPrice2 scmPurPrice, Param param) throws AppException {
		if(scmPurPrice != null){
			ScmPurPrice2 scmPurPrice2 = this.updateDirect(scmPurPrice, param);
			if(scmPurPrice2 != null){
				scmPurPriceEntryBiz.updateRowStatusByPmId(scmPurPrice2.getId(), scmPurPrice2.getStatus(), param);
			}
			return scmPurPrice2;
		}
		return null;
	}
	
	
	@Override
	public void doDelPurPrice(ScmPurPrice2 scmPurPrice, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put(ScmPurPrice2.FN_PMNO, scmPurPrice.getPmNo());
		map.put(ScmPurPrice2.FN_ORGUNITNO, param.getOrgUnitNo());
		List<ScmPurPrice2> scmPurPriceList = this.findAll(map, param);
		if(scmPurPriceList!=null && !scmPurPriceList.isEmpty()){
			try{
				this.delete(scmPurPriceList, param);
			}catch(AppException e){
				throw e;
			}
		}else{
			throw new AppException("iscm.purchasemanage.pricemanage.find.ordernotexists");
		}
	}

	@Override
	public ScmPurPrice2 doSubmitPurPrice(ScmPurPrice2 scmPurPrice, Param param)
			throws AppException {
		ScmPurPrice2 purPrice = null;
		List<ScmPurPrice2> scmPurPriceList = new ArrayList<> ();
		if(scmPurPrice.getId()>0){
			purPrice = this.selectDirect(scmPurPrice.getId(),param);
		}else{
			Page page=new Page();
			page.setModelClass(ScmPurPrice2.class);
			page.setShowCount(Integer.MAX_VALUE);
			page.getParam().put(ScmPurPrice2.FN_PMNO,new QueryParam(ScmPurPrice2.FN_PMNO, QueryParam.QUERY_EQ,scmPurPrice.getPmNo()));
			
			scmPurPriceList =this.findPage(page, param);
			if(scmPurPriceList!=null && !scmPurPriceList.isEmpty()){
				purPrice=scmPurPriceList.get(0);
			}
		}
		if(purPrice!=null){
			this.setConvertMap(purPrice,param);
			if(!purPrice.getStatus().equals("I")){
				throw new AppException("iscm.purchasemanage.error.commit");
			}else if(purPrice.getStatus().equals("I")){
				String priceRule = sysParamBiz.getValue(purPrice.getOrgUnitNo(), "SCM_PurPriceRule", "1", param);
				if(purPrice.isBusinessAutoQuotation()){
			        if(StringUtils.isBlank(purPrice.getConfirmStatus()) || !StringUtils.containsIgnoreCase(",O,B,", purPrice.getConfirmStatus())){
			        	throw new AppException("iscm.purchasemanage.pricemanage.businessAutoQuotation.noConfirm");
			        }
				}
				BillType2 billType = billTypeBiz.selectByOrgAndCode(purPrice.getFinOrgUnitNo(), "ScmPurPrice", param);
				boolean needAudit = false;
				if(billType!=null && billType.isNeedAudit())
					needAudit = true;
				List<ScmPurPriceEntry2> scmPurPriceEntryList = scmPurPriceEntryBiz.selectByPmId(scmPurPrice.getId(), param);
				if(purPrice.isBusinessAutoQuotation()){
					StringBuffer errorMsg = new StringBuffer("");
					if(scmPurPriceEntryList!=null && !scmPurPriceEntryList.isEmpty()) {
						int i=0;
						for(ScmPurPriceEntry2 scmPurPriceEntry:scmPurPriceEntryList) {
							if (scmPurPriceEntry.getSelVndrId() == 0) {       //自主报价模式时供应商为必填
								if(StringUtils.isNotBlank(errorMsg.toString()))
									errorMsg.append("\r\n");
								errorMsg.append(Message.getMessage(param.getLang(),"iscm.purchasemanage.pricemanage.controller.ScmPurPriceFormController.validateSelVndrId", new String[]{i + 1 + ""}));
							}
						}
					}
					if(StringUtils.isNotBlank(errorMsg.toString()))
						throw new AppException(errorMsg.toString());
				}
				if(needAudit){
					//发起流程
					CommonBean bean = new CommonBean();
					scmPurPriceList.add(purPrice);
					bean.setList(scmPurPriceList);
					bean.setList2(scmPurPriceEntryList);
					String processInstanceId = ActivityUtil.startProcessInstance(bean, billType.getId(), billType.getWorkFlowNo(), param);
					purPrice.setWorkFlowNo(processInstanceId);
					ActivityUtil activityUtil = new ActivityUtil();
					//判断当前流程是否结束
					boolean isCompleteTask = activityUtil.queryProInstanceStateByProInstanceId(processInstanceId);
					if(isCompleteTask) {
						if(param.getUsrCode()!=null ){
							purPrice.setChecker(param.getUsrCode());
							purPrice.setSubmitter(param.getUsrCode());
						}
						purPrice.setCheckDate(CalendarUtil.getDate(param));
						purPrice.setSubmitDate(CalendarUtil.getDate(param));
						purPrice.setStatus("A");
					}else {
						if(param.getUsrCode()!=null ){
							purPrice.setSubmitter(param.getUsrCode());
						}
						purPrice.setSubmitDate(CalendarUtil.getDate(param));
						purPrice.setStatus("D");
						String usrList= ActivityUtil.findAssigneeByProcessInstanceId(processInstanceId,param);
						if(StringUtils.isNotBlank(usrList)) {
							scmBillPendingBiz.addPendingBill(usrList, purPrice, param);
							PurPriceParams purPriceParams = new PurPriceParams();
							purPriceParams.setPmNo(purPrice.getPmNo());
							AuditMsgUtil.sendAuditMsg(billType.getBillCode(), purPrice, purPriceParams, usrList, param);
						}
					}
				}else{
					if(param.getUsrCode()!=null ){
						purPrice.setEditor(param.getUsrCode());
						purPrice.setSubmitter(param.getUsrCode());
					}
					purPrice.setEditDate(CalendarUtil.getDate(param));
					purPrice.setSubmitDate(CalendarUtil.getDate(param));
					purPrice.setStatus("A");
				}
				
				try{
					this.updateStatus(purPrice, param);
				}catch(AppException e){
					throw e;
				}
				if(StringUtils.contains("A,B", purPrice.getStatus())) {
					//通过或部分通过时检查是否自动下达
					if(billType!=null && billType.isAutoRelease()) {
						this.doReleasePurPrice(purPrice, param);
					}
				}
			}
		}else{
			throw new AppException("iscm.purchasemanage.pricemanage.find.ordernotexists");
		}
		return purPrice;
	}

	@Override
	public ScmPurPrice2 doUnSubmitPurPrice(ScmPurPrice2 scmPurPrice, Param param) throws AppException {
		ScmPurPrice2 purPrice = null;
		if(scmPurPrice.getId()>0){
			purPrice = this.selectDirect(scmPurPrice.getId(),param);
		}else{
			Page page=new Page();
			page.setModelClass(ScmPurPrice2.class);
			page.setShowCount(Integer.MAX_VALUE);
			page.getParam().put(ScmPurPrice2.FN_PMNO,new QueryParam(ScmPurPrice2.FN_PMNO, QueryParam.QUERY_EQ,scmPurPrice.getPmNo()));
			
			List<ScmPurPrice2> scmPurPriceList =this.findPage(page, param);
			if(scmPurPriceList!=null && !scmPurPriceList.isEmpty()){
				purPrice=scmPurPriceList.get(0);
			}
		}
		
		if(purPrice!=null){
			BillType2 billType = billTypeBiz.selectByOrgAndCode(purPrice.getFinOrgUnitNo(), "ScmPurPrice", param);
			boolean needAudit = false;
			if(billType!=null && billType.isNeedAudit() && StringUtils.isNotBlank(purPrice.getWorkFlowNo()))
				needAudit = true;
			
			//单据设置审批流程，但设置没有审批节点，状态为通过，可以取消通过
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put(ScmBillPending.FN_BILLID, new QueryParam(ScmBillPending.FN_BILLID, QueryParam.QUERY_EQ, String.valueOf(purPrice.getId())));
			map.put(ScmBillPending.FN_BILLTYPE, new QueryParam(ScmBillPending.FN_BILLTYPE, QueryParam.QUERY_EQ, billType.getBillCode()));
			List<ScmBillPending> scmBillPendingList = scmBillPendingBiz.findAll(map, param);

			if(needAudit){
				if (scmBillPendingList == null || scmBillPendingList.isEmpty()) {
					if(!StringUtils.equals(purPrice.getStatus(),"A"))
						throw new AppException("iscm.purchasemanage.error.cancelCommit");
				} else {
					if(!StringUtils.equals(purPrice.getStatus(),"D"))
						throw new AppException("iscm.purchasemanage.error.cancelCommit");
				}
			}else{
				if(!StringUtils.equals(purPrice.getStatus(),"A"))
					throw new AppException("iscm.purchasemanage.error.cancelCommit");
			} 
			if(purPrice.getStatus().equals("A") || purPrice.getStatus().equals("D")){
				if(param.getUsrCode()!=null ){
					purPrice.setChecker(null);
					purPrice.setSubmitter(null);
				}
				purPrice.setCheckDate(null);
				purPrice.setSubmitDate(null);
				purPrice.setStatus("I");
				if(StringUtils.equals(sysParamBiz.getValue(scmPurPrice.getOrgUnitNo(), "SCM_PurPriceRule", "1", param), "2")){
					//取最低报价时取消提交自动取消确认
					scmPurPrice.setConfirmStatus("R");
				}
				try{
					this.updateStatus(purPrice, param);
					scmBillPendingBiz.cancelPendingBill(param.getUsrCode(),purPrice, param);
				}catch(AppException e){
					throw e;
				}
			}
		}else{
			throw new AppException("iscm.purchasemanage.pricemanage.find.ordernotexists");
		}
		return purPrice;
	}

	@Override
	public List<ScmPurPrice2> queryPurPriceList(ScmPurPriceAdvQuery scmPurPriceAdvQuery, int pageNum, Param param) throws AppException {
		Page page=new Page();
		page.setModelClass(ScmPurPrice2.class);
		if (pageNum == -1) {
			page.setPagePos(1);
			page.setShowCount(Integer.MAX_VALUE);
		} else {
			page.setPagePos(pageNum);
			page.setShowCount(20);
		}
		page.setModel(scmPurPriceAdvQuery);	//findPage时底层会触发doAdvQuery，增加查询条件
		
		return this.findPage(page, param);
	}

	@Override
	public void doAdvQuery(Page page, Param param) {
		if (page.getModel() != null) {
			if (page.getModel() instanceof ScmPurPriceAdvQuery) {
				ScmPurPriceAdvQuery scmPurPriceAdvQuery = (ScmPurPriceAdvQuery) page.getModel();
				if(StringUtils.isNotBlank(scmPurPriceAdvQuery.getPmNo())){
					page.getParam().put(ScmPurPrice2.FN_PMNO,new QueryParam(ScmPurPrice2.FN_PMNO, QueryParam.QUERY_EQ,scmPurPriceAdvQuery.getPmNo()));
				}
				if(scmPurPriceAdvQuery.getPmDateFrom()!=null && scmPurPriceAdvQuery.getPmDateTo()!=null){
					page.getParam().put(ScmPurPrice2.FN_PMDATE,new QueryParam(ScmPurPrice2.FN_PMDATE, QueryParam.QUERY_BETWEEN,FormatUtils.fmtDate(scmPurPriceAdvQuery.getPmDateFrom()),FormatUtils.fmtDate(CalendarUtil.relativeDate(scmPurPriceAdvQuery.getPmDateTo(),1))));
				}
				if(StringUtils.isNotBlank(scmPurPriceAdvQuery.getPricingCode())){
					page.getParam().put(ScmPurPrice2.FN_PRICENAME,new QueryParam(ScmPurPrice2.FN_PRICENAME, QueryParam.QUERY_EQ,scmPurPriceAdvQuery.getPricingCode()));
				}	
				if(scmPurPriceAdvQuery.getCreateDateFrom()!=null){
					if(scmPurPriceAdvQuery.getCreateDateTo()!=null) {
						page.getParam().put(ScmPurPrice2.FN_CREATEDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmPurPrice2.class) + "." +ScmPurPrice2.FN_CREATEDATE, QueryParam.QUERY_BETWEEN,FormatUtils.fmtDate(scmPurPriceAdvQuery.getCreateDateFrom()),FormatUtils.fmtDate(CalendarUtil.relativeDate(scmPurPriceAdvQuery.getCreateDateTo(),1))));
					}else {
						page.getParam().put(ScmPurPrice2.FN_CREATEDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmPurPrice2.class) + "." +ScmPurPrice2.FN_CREATEDATE, QueryParam.QUERY_GE,FormatUtils.fmtDate(scmPurPriceAdvQuery.getCreateDateFrom())));
					}
				}else if(scmPurPriceAdvQuery.getCreateDateTo()!=null) {
					page.getParam().put(ScmPurPrice2.FN_CREATEDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmPurPrice2.class) + "." +ScmPurPrice2.FN_CREATEDATE, QueryParam.QUERY_LE,FormatUtils.fmtDate(CalendarUtil.relativeDate(scmPurPriceAdvQuery.getCreateDateTo(),1))));
				}
				if(scmPurPriceAdvQuery.getSelVndrId() > 0){
					page.getParam().put(ScmPurPrice2.FN_SELVNDRID,new QueryParam(ScmPurPrice2.FN_SELVNDRID, QueryParam.QUERY_EQ,String.valueOf(scmPurPriceAdvQuery.getSelVndrId())));
				}
				if(StringUtils.isNotBlank(scmPurPriceAdvQuery.getPriceBizType())){
					page.getParam().put(ScmPurPrice2.FN_BIZTYPE,new QueryParam(ScmPurPrice2.FN_BIZTYPE, QueryParam.QUERY_EQ,scmPurPriceAdvQuery.getPriceBizType()));
				}
			}
		}
	}

	@Override
	public ScmPurPrice2 queryPurPrice(ScmPurPrice scmPurPrice, Param param) throws AppException {
		Page page=new Page();
		page.setModelClass(ScmPurQuotation2.class);
		page.setShowCount(Integer.MAX_VALUE);
		page.getParam().put(ScmPurPrice2.FN_PMNO,new QueryParam(ScmPurPrice2.FN_PMNO, QueryParam.QUERY_EQ,scmPurPrice.getPmNo()));
		
		List<ScmPurPrice2> scmPurPriceList =this.findPage(page, param);
		ScmPurPrice2 scmPurPrice2 = new ScmPurPrice2();
		if(scmPurPriceList!=null && !scmPurPriceList.isEmpty()){
			scmPurPrice2 = scmPurPriceList.get(0);
			scmPurPrice2.setScmPurPriceEntryList(scmPurPriceEntryBiz.selectByPmId(scmPurPrice2.getId(), param));
			
			//增加待审人
			ScmBillPending2 scmBillPendingUsr = scmBillPendingBiz.selectPendingUsr(scmPurPrice2.getId(), "ScmPurPrice",param);
			if (scmBillPendingUsr != null) {
				scmPurPrice2.setPendingUsr(scmBillPendingUsr.getUsrCodes());
			}
		}else{
			throw new AppException("iscm.purchasemanage.pricemanage.find.ordernotexists");
		}
		return scmPurPrice2;
	}


	@Override
	public List<ScmMaterialPrice> getPrice(String purOrgUnitNo, String itemIds,Date bizDate, String finOrgUnitNo, String pmId, Param param) throws AppException {
		String priceType =sysParamBiz.getValue(param.getOrgUnitNo(), "SCM_PriceType", "1", param);
		String pricePrec = sysParamBiz.getValue(param.getOrgUnitNo(), "SCM_PricePrecision", "2", param);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("orgUnitNo", purOrgUnitNo);
		map.put("itemIds", itemIds);
		map.put("bizDate", FormatUtils.fmtDate(bizDate));
		map.put("priceType", priceType);
		map.put("pricePrec", pricePrec);
		map.put("finOrgUnitNo", finOrgUnitNo);
		map.put("pmId", pmId);
		return ((ScmPurPriceDAO)dao).getPrice(map);
	}

	@Override
	public ScmPurPrice2 getPrePrice(String purOrgUnitNo, long itemId,Date bizDate, Param param) throws AppException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("orgUnitNo", purOrgUnitNo);
		map.put("itemId", itemId);
		map.put("bizDate", FormatUtils.fmtDate(bizDate));
		return ((ScmPurPriceDAO)dao).getPrePrice(map);
	}

	@Override
	public ScmPurPrice2 getLastYearPrice(String purOrgUnitNo, long itemId,Date begDate, Date endDate, Param param) throws AppException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("orgUnitNo", purOrgUnitNo);
		map.put("itemId", itemId);
		map.put("begDate", FormatUtils.fmtDate(begDate));
		map.put("endDate", FormatUtils.fmtDate(endDate));
		return ((ScmPurPriceDAO)dao).getLastYearPrice(map);
	}

	@Override
	public ScmPurPrice2 doReleasePurPrice(ScmPurPrice2 scmPurPrice, Param param) throws AppException {
		ScmPurPrice2 purPrice = null;
		if(scmPurPrice.getId()>0){
			purPrice = this.selectDirect(scmPurPrice.getId(),param);
		}else{
			Page page=new Page();
			page.setModelClass(ScmPurPrice2.class);
			page.setShowCount(Integer.MAX_VALUE);
			page.getParam().put(ScmPurPrice2.FN_PMNO,new QueryParam(ScmPurPrice2.FN_PMNO, QueryParam.QUERY_EQ,scmPurPrice.getPmNo()));
			
			List<ScmPurPrice2> scmPurPriceList =this.findPage(page, param);
			if(scmPurPriceList!=null && !scmPurPriceList.isEmpty()){
				purPrice=scmPurPriceList.get(0);
			}
		}
		if(purPrice!=null){
			if(!purPrice.getStatus().equals("A")){
				throw new AppException("iscm.purchasemanage.error.release");
			}else if(purPrice.getStatus().equals("A")){
				purPrice.setStatus("E");
				try{
					this.updateStatus(purPrice, param);
				}catch(AppException e){
					throw e;
				}
			}
			
			//校验单价是否大于0
			List<ScmPurPriceEntry2> scmPurPriceEntryList = scmPurPriceEntryBiz.selectByPmId(scmPurPrice.getId(), param);
			for (ScmPurPriceEntry2 scmPurPriceEntry : scmPurPriceEntryList) {
				if(scmPurPriceEntry.getPrice().compareTo(BigDecimal.ZERO) < 1) {
					throw new AppException("iscm.purchasemanage.pricemanage.controller.ScmPurPriceFormController.validatePrice",new String[]{String.valueOf(scmPurPriceEntry.getLineId())});
				}
			}
			
			if(StringUtils.equals(scmPurPrice.getBizType(),"2")) {
				if(scmPurPriceEntryList != null && !scmPurPriceEntryList.isEmpty()) {
					for (ScmPurPriceEntry2 scmPurPriceEntry : scmPurPriceEntryList) {
						if(scmPurPrice.getSelVndrId() > 0 && scmPurPriceEntry.getSelVndrId() == 0) {
							scmPurPriceEntry.setSelVndrId(scmPurPrice.getSelVndrId());
						} 
						scmPurPriceEntry.setUndoRelease(false);
						scmPurRequireEntryBiz.updatePurRequestByPmId(scmPurPriceEntry, param);
					}
				}
				//订货单明细生成的临时定价单下达触发
				scmPurOrderEntryBiz.updatePurOrderEntryByPmId(scmPurPrice,param);
			}
			scmPurQuotationBiz.releaseByPurPrice(scmPurPrice, param);
		}else{
			throw new AppException("iscm.purchasemanage.pricemanage.find.ordernotexists");
		}
		
		return purPrice;
	}

	@Override
	public CommonBean doUndoReleasePurPrice(List<ScmPurPrice2> scmPurPriceList1, int type, Param param)
			throws AppException {
		CommonBean commonBean = undoReleaseCheck(scmPurPriceList1, type, param);//检查是否有正在使用的单据
		List<ScmPurPrice2> scmPurPrice1 = commonBean.getList2();
		if (scmPurPrice1 != null && scmPurPrice1.size() > 0) {
			for (ScmPurPrice2 scmPurPrice : scmPurPrice1) {
				ScmPurPrice2 purPrice = null;
				if (scmPurPrice.getId() > 0) {
					purPrice = this.selectDirect(scmPurPrice.getId(), param);
				} else {
					Page page = new Page();
					page.setModelClass(ScmPurPrice2.class);
					page.setShowCount(Integer.MAX_VALUE);
					page.getParam().put(ScmPurPrice2.FN_PMNO,
							new QueryParam(ScmPurPrice2.FN_PMNO, QueryParam.QUERY_EQ, scmPurPrice.getPmNo()));

					List<ScmPurPrice2> scmPurPriceList = this.findPage(page, param);
					if (scmPurPriceList != null && !scmPurPriceList.isEmpty()) {
						purPrice = scmPurPriceList.get(0);
					}
				}
				if (purPrice != null) {
					if (!purPrice.getStatus().equals("E")) {
						throw new AppException("iscm.purchasemanage.error.cancelRelease");
					} else if (purPrice.getStatus().equals("E")) {
						purPrice.setStatus("A");
						try {
							this.updateStatus(purPrice, param);
						} catch (AppException e) {
							throw e;
						}
					}

					if (StringUtils.equals(scmPurPrice.getBizType(), "2")) {
						List<ScmPurPriceEntry2> scmPurPriceEntryList = scmPurPriceEntryBiz
								.selectByPmId(scmPurPrice.getId(), param);
						for (ScmPurPriceEntry2 scmPurPriceEntry : scmPurPriceEntryList) {
							HashMap<String, Object> map = new HashMap<String, Object>();

							String prNos = scmPurPriceEntry.getPrNos();
							if (StringUtils.isNotBlank(prNos)) {
								StringBuffer prNo = new StringBuffer("");
								String str[] = prNos.split(",");
								List<String> prNosList = Arrays.asList(str);
								if (prNosList != null && !prNosList.isEmpty()) {
									for (String prNoTemp : prNosList) {
										if (StringUtils.isNotBlank(prNo.toString()))
											prNo.append(",");
										prNo.append("'").append(prNoTemp).append("'");
									}
								}

								map.put(ScmPurRequire2.FN_PRNO,
										new QueryParam(ScmPurRequire2.FN_PRNO, QueryParam.QUERY_IN, prNo.toString()));
								map.put(ScmPurRequire2.FN_CONTROLUNITNO, new QueryParam(ScmPurRequire2.FN_CONTROLUNITNO,
										QueryParam.QUERY_EQ, param.getControlUnitNo()));

								List<ScmPurRequire2> scmPurRequireList = scmPurRequireBiz.findAll(map, param);
								List<ScmPurRequireEntry2> scmPurRequireEntryList = new ArrayList<>();
								ScmPurRequire2 require = null;
								boolean isFinsh = false;
								if (scmPurRequireList != null && !scmPurRequireList.isEmpty()) {
									for (int i = 0; i < scmPurRequireList.size(); i++) {
										require = scmPurRequireList.get(i);
										if (require != null) {
											scmPurRequireEntryList = scmPurRequireEntryBiz.selectByPrId(require.getId(),
													param);
											for (ScmPurRequireEntry2 scmPurRequireEntry : scmPurRequireEntryList) {
												if (scmPurRequireEntry.getItemId() == scmPurPriceEntry.getItemId()) {
													if (StringUtils.contains("C", scmPurRequireEntry.getRowStatus())) {
														throw new AppException(
																"iscm.purchasemanage.pricemanage.controller.ScmPurPriceFormController.validateRowStatus",
																new String[] {
																		String.valueOf(scmPurPriceEntry.getLineId()) });
													}
													if (StringUtils.contains("E", scmPurRequireEntry.getRowStatus())) {
														throw new AppException(
																"iscm.purchasemanage.pricemanage.controller.ScmPurPriceFormController.validateRowStatus2",
																new String[] {
																		String.valueOf(scmPurPriceEntry.getLineId()) });
													}
													scmPurPriceEntry.setUndoRelease(true);
													scmPurRequireEntryBiz.updatePurRequestByPmId(scmPurPriceEntry,
															param);
													isFinsh = true;
													break;
												}
											}
										}
										if (isFinsh) {
											break;
										}
									}

								}
							}
							List<ScmPurOrderEntry2> scmPurOrderEntryList = scmPurOrderEntryBiz
									.selectByPriceId(scmPurPrice.getId(), param);
							if (scmPurOrderEntryList != null && !scmPurOrderEntryList.isEmpty()) {
								for (ScmPurOrderEntry2 scmPurOrderEntry : scmPurOrderEntryList) {
									if (scmPurOrderEntry.getItemId() == scmPurPriceEntry.getItemId()) {
										if (StringUtils.contains("C", scmPurOrderEntry.getRowStatus())) {
											throw new AppException(
													"iscm.purchasemanage.pricemanage.ScmPurPrice.validateRowStatus2",
													new String[] { scmPurOrderEntry.getItemName() });
										}
										if (StringUtils.contains("E", scmPurOrderEntry.getRowStatus())) {
											throw new AppException(
													"iscm.purchasemanage.pricemanage.ScmPurPrice.validateRowStatus",
													new String[] { scmPurOrderEntry.getItemName() });
										}
										scmPurPriceEntry.setUndoRelease(true);
									}
								}
							}
						}
						// 订货单明细生成的临时定价单取消下达触发
						scmPurOrderEntryBiz.updatePurOrderEntryByPmId(scmPurPrice, param);
					}
					scmPurQuotationBiz.undoReleaseByPurPrice(scmPurPrice, param);
				} else {
					throw new AppException("iscm.purchasemanage.pricemanage.find.ordernotexists");
				}
			}
		}
		return commonBean;
	}

	@Override
	public ScmPurPrice2 finish(ScmPurPrice2 scmPurPrice, Param param)
			throws AppException {
		ScmPurPrice2 purPrice = null;
		if(scmPurPrice.getId()>0){
			purPrice = this.selectDirect(scmPurPrice.getId(),param);
		}else{
			Page page=new Page();
			page.setModelClass(ScmPurPrice2.class);
			page.setShowCount(Integer.MAX_VALUE);
			page.getParam().put(ScmPurPrice2.FN_PMNO,new QueryParam(ScmPurPrice2.FN_PMNO, QueryParam.QUERY_EQ,scmPurPrice.getPmNo()));
			
			List<ScmPurPrice2> scmPurPriceList =this.findPage(page, param);
			if(scmPurPriceList!=null && !scmPurPriceList.isEmpty()){
				purPrice=scmPurPriceList.get(0);
			}
		}
		if(purPrice!=null){
			if(!purPrice.getStatus().equals("E")){
				throw new AppException("iscm.purchasemanage.error.finish");
			}else if(purPrice.getStatus().equals("E")){
				if(param.getUsrCode()!=null ){
					purPrice.setChecker(param.getUsrCode());
					purPrice.setCheckDate(CalendarUtil.getDate(param));
				}
				purPrice.setStatus("C");
				try{
					this.updateStatus(purPrice, param);
				}catch(AppException e){
					throw e;
				}
			}
		}else{
			throw new AppException("iscm.purchasemanage.pricemanage.find.ordernotexists");
		}
		return purPrice;
	}

	@Override
	public ScmPurPrice2 undoFinish(ScmPurPrice2 scmPurPrice, Param param)
			throws AppException {
		ScmPurPrice2 purPrice = null;
		if(scmPurPrice.getId()>0){
			purPrice = this.selectDirect(scmPurPrice.getId(),param);
		}else{
			Page page=new Page();
			page.setModelClass(ScmPurPrice2.class);
			page.setShowCount(Integer.MAX_VALUE);
			page.getParam().put(ScmPurPrice2.FN_PMNO,new QueryParam(ScmPurPrice2.FN_PMNO, QueryParam.QUERY_EQ,scmPurPrice.getPmNo()));
			
			List<ScmPurPrice2> scmPurPriceList =this.findPage(page, param);
			if(scmPurPriceList!=null && !scmPurPriceList.isEmpty()){
				purPrice=scmPurPriceList.get(0);
			}
		}
		if(purPrice!=null){
			if(!purPrice.getStatus().equals("C")){
				throw new AppException("iscm.purchasemanage.error.cancelFinish");
			}else if(purPrice.getStatus().equals("C")){
				if(param.getUsrCode()!=null ){
					purPrice.setChecker(param.getUsrCode());
					purPrice.setCheckDate(CalendarUtil.getDate(param));
				}
				purPrice.setStatus("E");
				try{
					this.updateStatus(purPrice, param);
				}catch(AppException e){
					throw e;
				}
			}
		}else{
			throw new AppException("iscm.purchasemanage.pricemanage.find.ordernotexists");
		}
		return purPrice;
	}
	@Override
	protected void beforeUpdate(CommonBean bean, Param param) throws AppException {
		ScmPurPrice2 entity = (ScmPurPrice2) bean.getList().get(0);
		if(entity!=null){
			ScmPurPrice2 scmPurPrice = this.select(entity.getPK(), param);
			if(!StringUtils.contains("I,D,P", scmPurPrice.getStatus())){
				throw new AppException(Message.getMessage(param.getLang(), "iscm.commom.edit.error.notnewstatus"));
			}
		}
		List<OrgCompany2> orgCompanyList = orgUnitRelationBiz.findToOrgUnitByType(OrgUnitRelationType.PURTOFIN, entity.getOrgUnitNo(), false, null, param);
		if(orgCompanyList==null || orgCompanyList.isEmpty()){
			throw new AppException("iscm.purchasemanage.purchasebusiness.ScmPurRequireBizImpl.error.notfin");
		}
		entity.setFinOrgUnitNo(orgCompanyList.get(0).getOrgUnitNo());	
	}

    @Override
    public ScmPurPrice2 importExcel(ScmPurPriceImPort scmPurPriceImPort, Param param) {
        if(scmPurPriceImPort != null) {
            List<OrgCompany2> orgCompanyList = orgUnitRelationBiz.findToOrgUnitByType(OrgUnitRelationType.PURTOFIN, param.getOrgUnitNo(), false, null, param);
            if(orgCompanyList==null || orgCompanyList.isEmpty()) {
                throw new AppException(Message.getMessage(param.getLang(), "iscm.inventorymanage.common.purOrgUnit.notfinorg"));
            }
            SystemState systemState = systemStateBiz.selectBySystemId(orgCompanyList.get(0).getOrgUnitNo(), 170, param);
            if(systemState==null){
                throw new AppException(Message.getMessage(param.getLang(), "com.armitage.iars.daily.util.disenable.unable"));
            }
            CommonBean bean = new CommonBean();
            List<ScmPurPrice2> scmPurPriceList = new ArrayList();
            ScmPurPrice2 scmPurPrice = new ScmPurPrice2(true);
            scmPurPrice.setPmDate(scmPurPriceImPort.getPmDate());
            scmPurPrice.setBegDate(scmPurPriceImPort.getBegDate());
            scmPurPrice.setEndDate(scmPurPriceImPort.getEndDate());
            scmPurPrice.setOrgUnitNo(param.getOrgUnitNo());
            scmPurPrice.setSelVndrId(scmPurPriceImPort.getSelVndrId());
            scmPurPrice.setVendorId1(scmPurPriceImPort.getVendorId1());
            scmPurPrice.setVendorId2(scmPurPriceImPort.getVendorId2());
            scmPurPrice.setVendorId3(scmPurPriceImPort.getVendorId3());
            scmPurPrice.setGroupId1(scmPurPriceImPort.getGroupId1());
            scmPurPrice.setGroupId2(scmPurPriceImPort.getGroupId2());
            scmPurPrice.setGroupId3(scmPurPriceImPort.getGroupId3());
            scmPurPrice.setCurrencyNo(
                    StringUtils.isBlank(orgCompanyList.get(0).getBaseCurrency())?"RMB":orgCompanyList.get(0).getBaseCurrency());
            scmPurPrice.setCreator(param.getUsrCode());
            scmPurPrice.setCreateDate(CalendarUtil.getDate(param));
            scmPurPrice.setStatus("I");
            scmPurPrice.setRemarks(scmPurPriceImPort.getRemarks());
            scmPurPrice.setControlUnitNo(param.getControlUnitNo());
            scmPurPriceList.add(scmPurPrice);
            bean.setList(scmPurPriceList);
            
            StringBuffer itemNos = new StringBuffer("");
            List<ScmPurPriceEntry2> scmPurPriceEntryList = new ArrayList<ScmPurPriceEntry2>();
            ArrayList<ScmPurPriceQuery> scmPurPriceQuerys = new ArrayList<ScmPurPriceQuery>();
            List<ScmPurPriceImPortDetail> scmPurPriceImPortDetailList = scmPurPriceImPort.getDetailList();
            for(ScmPurPriceImPortDetail scmPurPriceImPortDetail : scmPurPriceImPortDetailList) {
                if(StringUtils.isBlank(scmPurPriceImPortDetail.getItemNo())){
                    throw new AppException("iscm.purchasemanage.purchasebusiness.ScmPurRequireBizImpl.error.itemisnull");
                }
                if(StringUtils.isNotBlank(itemNos.toString()))
                    itemNos.append(",");
                itemNos.append("'").append(scmPurPriceImPortDetail.getItemNo()).append("'");
                ScmPurPriceQuery scmPurPriceQuery = new ScmPurPriceQuery();
                scmPurPriceQuery.setItemId(scmPurPriceImPortDetail.getItemId());
                scmPurPriceQuery.setBeginDate(scmPurPrice.getBegDate());
                scmPurPriceQuery.setEndDate(scmPurPrice.getEndDate());
                scmPurPriceQuery.setPurOrgUnitNo(param.getOrgUnitNo());
                scmPurPriceQuerys.add(scmPurPriceQuery);
            }
                
            Page page = new Page();
            page.setModelClass(ScmMaterial2.class);
            page.setShowCount(Integer.MAX_VALUE);
            page.setSqlCondition("ScmMaterial.itemNo in ("+itemNos.toString()+")");
            ArrayList argList = new ArrayList();
            argList.add("orgUnitNo=" + param.getOrgUnitNo());
            argList.add("controlUnitNo=" + param.getControlUnitNo());
            List<ScmMaterial2> scmMaterialList = scmMaterialBiz.queryPage(page, argList, "findByPurAllPage", param);
            StringBuffer itemIds=new StringBuffer("");
            if (scmMaterialList !=null && scmMaterialList.size()>0) {
				for (ScmMaterial2 scmMaterial : scmMaterialList) {
					if(StringUtils.isNotBlank(itemIds.toString())) {
						itemIds.append(",");
					}
					itemIds.append(scmMaterial.getId());
				}
			}
            //根据物资获取上期定价
            List<ScmMaterialPrePrice> lastPriceList = this.selectLastYearPriceByVendor(scmPurPriceQuerys, param);
            List<ScmMaterialPrePrice> scmMaterialPrices=this.selectPrePrice(scmPurPrice.getOrgUnitNo(),scmPurPrice.getSelVndrId(), itemIds.toString(), scmPurPrice.getBegDate(), param);
            for(ScmPurPriceImPortDetail scmPurPriceImPortDetail : scmPurPriceImPortDetailList) {
                ScmPurPriceEntry2 scmPurPriceEntry = new ScmPurPriceEntry2(true);
                BeanUtils.copyProperties(scmPurPriceImPortDetail,scmPurPriceEntry);
                for(ScmMaterial2 scmMaterial:scmMaterialList){
                    if(StringUtils.equals(scmMaterial.getItemNo(), scmPurPriceImPortDetail.getItemNo())){
                        scmPurPriceEntry.setItemId(scmMaterial.getId());
                        scmPurPriceEntry.setSpec(scmMaterial.getSpec());
                        scmPurPriceEntry.setPurUnit(scmMaterial.getPurUnitId());
                        break;
                    }
                }
                scmPurPriceEntry.setPrePrice(scmPurPriceImPortDetail.getPrePrice());
                if (scmMaterialPrices !=null && scmMaterialPrices.size()>0) {
					for (ScmMaterialPrePrice scmMaterialPrice : scmMaterialPrices) {
						if (scmMaterialPrice.getItemId()==scmPurPriceImPortDetail.getItemId() && scmPurPriceEntry.getPrePrice() != null && scmPurPriceEntry.getPrePrice().compareTo(BigDecimal.ZERO)==0) {
							scmPurPriceEntry.setPrePrice(scmMaterialPrice.getPrice());
						}
					}
				}
                if (lastPriceList != null && !lastPriceList.isEmpty()) {
                    for (ScmMaterialPrePrice scmMaterialPrePrice : lastPriceList) {
                        if (scmMaterialPrePrice.getPrice().compareTo(BigDecimal.ZERO) > 0) {
                            if (scmMaterialPrePrice.getItemId() == scmPurPriceEntry.getItemId() && scmPurPriceEntry.getLastYearPrice() != null && scmPurPriceEntry.getLastYearPrice().compareTo(BigDecimal.ZERO)==0) {
                            	scmPurPriceEntry.setLastYearPrice(scmMaterialPrePrice.getPrice());
                            }
                        }
                    }
                }
                scmPurPriceEntry.setSelVndrId(scmPurPriceImPort.getSelVndrId());
                scmPurPriceEntry.setRowStatus("I");
                scmPurPriceEntry.setRemarks(scmPurPriceImPortDetail.getRemarks());
                scmPurPriceEntryList.add(scmPurPriceEntry);
            }
            bean.setList2(scmPurPriceEntryList);
            bean = this.add(bean, param);
            return (ScmPurPrice2) bean.getList().get(0);
        }
        return null;
    }

	private List<ScmMaterialPrePrice> selectPrePrice(String orgUnitNo,long vendorId, String itemIds, Date begDate, Param param) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("orgUnitNo", orgUnitNo);
		map.put("itemIds",itemIds);
		map.put("begDate", begDate);
		return ((ScmPurPriceDAO)dao).selectPrePrice(map,param);
	}

	private List<ScmMaterialPrePrice> getPrePrice(String orgUnitNo, String itemIds, Date begDate, Param param) throws AppException{
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("orgUnitNo", orgUnitNo);
		map.put("itemIds",itemIds);
		map.put("begDate", begDate);
		return ((ScmPurPriceDAO)dao).getPrePriceList(map);
	}

	@Override
	public ScmPurPrice2 doAuditPurPrice(CommonAuditParams commonAuditParams,
			Param param) throws AppException {
		ScmPurPrice2 purPrice = null;
		
		ScmPurPrice2 scmPurPrice = new ScmPurPrice2();
		scmPurPrice.setId(commonAuditParams.getBillId());
		scmPurPrice.setPmNo(commonAuditParams.getBillNo());
		String opinion = commonAuditParams.getOpinion();
		if(StringUtils.equals("Y", opinion) || StringUtils.equals("agree", opinion)) {
			opinion="agree";
		}else if(StringUtils.equals("N", opinion) || StringUtils.equals("refuse", opinion)) {
			opinion="refuse";
		}
		if(scmPurPrice.getId()>0){
			purPrice = this.selectDirect(scmPurPrice.getId(), param);
		}else{
			HashMap<String,Object> map=new HashMap<String,Object>();
			map.put(
					ScmPurPrice2.FN_PMNO,
					new QueryParam(ScmPurPrice2.FN_PMNO, QueryParam.QUERY_EQ,
							scmPurPrice.getPmNo()));
			map.put(ScmPurPrice2.FN_CONTROLUNITNO, new QueryParam(ScmPurPrice2.FN_CONTROLUNITNO, QueryParam.QUERY_EQ,
							param.getControlUnitNo()));
			List<ScmPurPrice2> scmPurPriceList =this.findAll(map, param);
			if(scmPurPriceList!=null && !scmPurPriceList.isEmpty()){
				purPrice = scmPurPriceList.get(0);
			}
		}
		
		if (purPrice != null) {
			this.setConvertMap(purPrice,param);
			if(!(purPrice.getStatus().equals("D") || purPrice.getStatus().equals("P"))){
				throw new AppException("iscm.purchasemanage.error.audit");
			}
			if(StringUtils.equals("R", opinion)) {
				scmBillPendingBiz.deletePendingBill(param.getUsrCode(),purPrice, param);
				commonEventHistoryBiz.updateInvalid(purPrice,"",param);
				
				CommonAuditOpinion commonAuditOpinionR = new CommonAuditOpinion();
				commonAuditOpinionR.setPreStepNo(purPrice.getStepNo());
				String stepNo = StringUtils.replace(UUID.randomUUID().toString(), "-", "");
				commonAuditOpinionR.setStepNo(stepNo);
				commonAuditOpinionR.setActiveType("A");
				commonAuditOpinionR.setOpinion(commonAuditParams.getOpinion());
				commonAuditOpinionR.setRemarks(commonAuditParams.getOpinionRemarks());
				commonEventHistoryBiz.addEventHistory(purPrice, commonAuditOpinionR, param);	
				
				//驳回则将单据变回新单状态
				purPrice.setStatus("I");
				purPrice.setChecker(null);
				purPrice.setCheckDate(null);
				return this.updateDirect(purPrice, param);
			}
			String processInstanceId = purPrice.getWorkFlowNo();
			boolean isCompleteTask = true;
			
			if (processInstanceId != null && !"".equals(processInstanceId)) {
				//完成审批任务
				ActivityUtil.completeTaskByAssigneeAndOpinion(processInstanceId, param.getUsrCode(), opinion, param);
				ActivityUtil activityUtil = new ActivityUtil();
				//判断当前流程是否结束
				isCompleteTask = activityUtil.queryProInstanceStateByProInstanceId(processInstanceId);
			} else {
				//流程编号不存在
				throw new AppException("com.armitage.server.iscm.purchasemanage.purchasebusiness.service.impl.ScmPurRequireBizImpl.doAuditPurRequire.processInstanceId");
			}
			
			//P：审核中，A：通过，N：未通过
			if ("agree".equals(opinion)) {
				if (isCompleteTask) {
					purPrice.setStatus("A");
				} else {
					purPrice.setStatus("P");
				}
			} else {
				purPrice.setStatus("N");
			}
			scmBillPendingBiz.updatePendingBill(param.getUsrCode(), purPrice, param);
			purPrice.setChecker(param.getUsrCode());
			if ("agree".equals(opinion)) {
				String usrList= ActivityUtil.findAssigneeByProcessInstanceId(processInstanceId,param);
				if(StringUtils.isNotBlank(usrList)) {
					scmBillPendingBiz.addPendingBill(usrList, purPrice, param);
					PurPriceParams purPriceParams = new PurPriceParams();
					purPriceParams.setPmNo(purPrice.getPmNo());
					AuditMsgUtil.sendAuditMsg("ScmPurPrice",purPrice,purPriceParams, usrList, param);
				}
			}
			CommonAuditOpinion commonAuditOpinion = new CommonAuditOpinion();
			commonAuditOpinion.setPreStepNo(purPrice.getStepNo());
			String stepNo = StringUtils.replace(UUID.randomUUID().toString(), "-", "");
			try {
				purPrice.setStepNo(stepNo);
				purPrice.setCheckDate(CalendarUtil.getDate(param));
				this.updateStatus(purPrice, param);
			} catch (Exception e) {
				throw e;
			}
			commonAuditOpinion.setStepNo(stepNo);
			commonAuditOpinion.setActiveType("A");
			commonAuditOpinion.setOpinion(commonAuditParams.getOpinion());
			commonAuditOpinion.setRemarks(commonAuditParams.getOpinionRemarks());
			commonEventHistoryBiz.addEventHistory(purPrice, commonAuditOpinion, param);
			if(StringUtils.contains("A,B", purPrice.getStatus())) {
				//通过或部分通过时检查是否自动下达
				BillType2 billType = billTypeBiz.selectByOrgAndCode(purPrice.getFinOrgUnitNo(), "ScmPurPrice", param);
				if(billType!=null && billType.isAutoRelease()) {
					this.doReleasePurPrice(purPrice, param);
				}
			}
		} else {
			throw new AppException("iscm.purchasemanage.purchasebusiness.find.ordernotexists");
		}
		
		return purPrice;
	}

	@Override
	public ScmPurPrice2 doUnAuditPurPrice(ScmPurPrice2 scmPurPrice, Param param)
			throws AppException {
		ScmPurPrice2 purPrice = null;
		List<ScmPurPrice2> scmPurPriceList = new ArrayList<> ();
		boolean isFirstTask = false;
		
		if(scmPurPrice.getId()>0){
			purPrice = this.selectDirect(scmPurPrice.getId(), param);
		}else{
			HashMap<String,Object> map=new HashMap<String,Object>();
			map.put(
					ScmPurPrice2.FN_PMNO,
					new QueryParam(ScmPurPrice2.FN_PMNO, QueryParam.QUERY_EQ,
							scmPurPrice.getPmNo()));
			map.put(ScmPurPrice2.FN_CONTROLUNITNO, new QueryParam(ScmPurPrice2.FN_CONTROLUNITNO, QueryParam.QUERY_EQ,
					param.getControlUnitNo()));
			
			scmPurPriceList =this.findAll(map, param);
			if(scmPurPriceList!=null && !scmPurPriceList.isEmpty()){
				purPrice=scmPurPriceList.get(0);
			}
		}
		
		if (purPrice != null) {
			if(!StringUtils.contains("A,B,N,P", purPrice.getStatus())){
				throw new AppException("iscm.purchasemanage.unaudit.error.status");
			}
			String processInstanceId = purPrice.getWorkFlowNo();
			List<ScmPurPriceEntry2> scmPurPriceEntryList = scmPurPriceEntryBiz.selectByPmId(purPrice.getId(), param);
			
			if(scmPurPriceEntryList == null || scmPurPriceEntryList.isEmpty()){
				//抛出异常处理
				throw new AppException("iscm.inventorymanage.AllocationApplication.service.Impl.ScmInvMaterialRequestBillBizImpl.generateMaterialReqBill.notExitMaterialRequestBillDetail");
			}
			
			if (processInstanceId != null && !"".equals(processInstanceId)) {
				//反审批当前已审节点
				CommonBean bean = new CommonBean();
				scmPurPriceList.add(purPrice);
				bean.setList(scmPurPriceList);
				bean.setList2(scmPurPriceEntryList);
				BillType2 billType = billTypeBiz.selectByOrgAndCode(purPrice.getFinOrgUnitNo(), "ScmPurPrice", param);
				long billTypeId = 0;
				if (billType!=null && billType.isNeedAudit()) {
					billTypeId = billType.getId();
				}
				
				processInstanceId = ActivityUtil.antiAuditProcess(bean, billTypeId, processInstanceId, param.getUsrCode(), param);
				isFirstTask = ActivityUtil.isFirstTask(processInstanceId, param.getUsrCode(),  param);
				purPrice.setWorkFlowNo(processInstanceId);
				
			} else {
				//流程编号不存在
				throw new AppException("com.armitage.server.iscm.purchasemanage.purchasebusiness.service.impl.ScmPurRequireBizImpl.doAuditPurRequire.processInstanceId");
			}
			
			String status = "";
			if (isFirstTask) {
				status = "D";
			} else {
				status = "P";
			}
			
			purPrice.setStatus(status);
			String tableName = ClassUtils.getFinalModelSimpleName(purPrice);
			CommonEventHistory commonEventHistory = commonEventHistoryBiz.loadAuditEventHistory(tableName,purPrice.getStepNo(),purPrice.getPK(),param);
			if(commonEventHistory!=null && StringUtils.isNotBlank(commonEventHistory.getPreStepNo())) {
				commonEventHistory = commonEventHistoryBiz.loadAuditEventHistory(tableName,commonEventHistory.getPreStepNo(),purPrice.getPK(),param);
			}
			if(commonEventHistory!=null) {
				purPrice.setChecker(commonEventHistory.getOper());
				purPrice.setCheckDate(commonEventHistory.getOperDate());
			}else {
				purPrice.setChecker(null);
				purPrice.setCheckDate(null);
			}
			this.updateStatus(purPrice, param);
			scmBillPendingBiz.cancelPendingBill(param.getUsrCode(),purPrice, param);
			commonEventHistoryBiz.updateInvalid(purPrice,purPrice.getStepNo(),param);
			CommonAuditOpinion commonAuditOpinion = new CommonAuditOpinion();
			commonAuditOpinion.setStepNo(purPrice.getStepNo());
			commonAuditOpinion.setActiveType("U");
			commonAuditOpinion.setOpinion("Y");
			commonEventHistoryBiz.addEventHistory(purPrice, commonAuditOpinion, param);
		} else {
			throw new AppException("iscm.purchasemanage.purchasebusiness.find.ordernotexists");
		}
		
		return purPrice;
	}

	@Override
	public ScmPurPrice2 selectByPmNo(String pmNo, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("controlUnitNo", param.getControlUnitNo());
		map.put("pmNo", pmNo);
		return ((ScmPurPriceDAO)dao).selectByPmNo(map);
	}

	@Override
	public void updateVendorPqData(ScmPurPrice2 scmPurPrice, Param param) throws AppException {
		String buildBill = sysParamBiz.getValue(scmPurPrice.getOrgUnitNo(), "SCM_PmBillToPqBill", "N", param);
		HashMap<String,Object> map = new HashMap<String,Object>();
		if(scmPurPrice.getVendorPqDate1() != null){
			map.put("vendorPqDate1", scmPurPrice.getVendorPqDate1());
			if(StringUtils.equals("Y",buildBill)) {
				ScmPurQuotation2 scmPurQuotation = scmPurQuotationBiz.generateBillFromPmBill(scmPurPrice, scmPurPrice.getVendorId1(), param);
				if(scmPurQuotation!=null)
					map.put("pqId1", scmPurQuotation.getId());
			}
		}
		if(scmPurPrice.getVendorPqDate2() != null){
			map.put("vendorPqDate2", scmPurPrice.getVendorPqDate2());
			if(StringUtils.equals("Y",buildBill)) {
				ScmPurQuotation2 scmPurQuotation = scmPurQuotationBiz.generateBillFromPmBill(scmPurPrice, scmPurPrice.getVendorId2(), param);
				if(scmPurQuotation!=null)
					map.put("pqId2", scmPurQuotation.getId());
			}
		}
			
		if(scmPurPrice.getVendorPqDate3() != null){
			map.put("vendorPqDate3", scmPurPrice.getVendorPqDate3());
			if(StringUtils.equals("Y",buildBill)) {
				ScmPurQuotation2 scmPurQuotation = scmPurQuotationBiz.generateBillFromPmBill(scmPurPrice, scmPurPrice.getVendorId3(), param);
				if(scmPurQuotation!=null)
					map.put("pqId3", scmPurQuotation.getId());
			}
		}
		if(StringUtils.isNotBlank(scmPurPrice.getConfirmStatus())){
			map.put("confirmStatus", scmPurPrice.getConfirmStatus());
		}
		map.put("pmId", scmPurPrice.getId());
		map.put("controlUnitNo", param.getControlUnitNo());
		((ScmPurPriceDAO)dao).updateVendorPqData(map);
	}

	@Override
	public List<ScmSupplierRegInvitation> getEnterSupplierPlat(String vendorIds, Param param) throws AppException {
		List<ScmSupplierRegInvitation> scmSupplierRegInvitationList = scmSupplierRegInvitationBiz.selectBindedByCtrlAndVendor(param.getControlUnitNo(),vendorIds,param);
		if(scmSupplierRegInvitationList != null && !scmSupplierRegInvitationList.isEmpty()){
			return scmSupplierRegInvitationList;
		}
		return null;
	}

	@Override
	public List<ScmMaterialPrePrice> getPreMaterialPrice(String purOrgUnitNo, String itemIds, Date bizDate, String finOrgUnitNo,
			String pmId, Param param) throws AppException {
		String priceType =sysParamBiz.getValue(param.getOrgUnitNo(), "SCM_PriceType", "1", param);
		String pricePrec = sysParamBiz.getValue(param.getOrgUnitNo(), "SCM_PricePrecision", "2", param);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("orgUnitNo", purOrgUnitNo);
		map.put("itemIds", itemIds);
		map.put("bizDate", FormatUtils.fmtDate(bizDate));
		map.put("priceType", priceType);
		map.put("pricePrec", pricePrec);
		map.put("finOrgUnitNo", finOrgUnitNo);
		map.put("pmId", pmId);
		return ((ScmPurPriceDAO)dao).getPreMaterialPrice(map);
	}
	
	public CommonBean undoReleaseCheck(List<ScmPurPrice2> scmPurPrice2lList, int type, Param param)
			throws AppException {
		CommonBean commonBean = new CommonBean();
		List<ScmPurRequireEntry2> scmPurRequireEntry2s = null;
		List<ScmPurPrice2> scmPurPrice2List = new ArrayList<ScmPurPrice2>();
		int sucess = 0;
		if (scmPurPrice2lList != null && scmPurPrice2lList.size() > 0) {
			scmPurPrice2List.addAll(scmPurPrice2lList);
			StringBuffer stringBuffer = new StringBuffer("");
			StringBuffer messageBuffer = new StringBuffer("");
			HashMap<Object, Object> map = new HashMap<>();
			for (ScmPurPrice2 scmPurPrice2 : scmPurPrice2lList) {
				if (StringUtils.equals(scmPurPrice2.getBizType(), "1")) {// 日常定价的定价单
					if (StringUtils.isNotBlank(stringBuffer.toString()))
						stringBuffer.append(",");
					stringBuffer.append(String.valueOf(scmPurPrice2.getId()));
				}
			}
			map.put(ScmPurRequireEntry.FN_PRICEBILLID, stringBuffer);
			map.put("billType", type);
			if(StringUtils.isNotBlank(stringBuffer.toString())){
				scmPurRequireEntry2s = scmPurRequireEntryBiz.undoReleaseCheck(map, param);//获取使用的单据
			}
			if (scmPurRequireEntry2s != null && scmPurRequireEntry2s.size() > 0) {
				// 移除有被使用的定价单
				if (type == 1) {
					for (ScmPurPrice2 scmPurPrice : scmPurPrice2lList) {
						if (StringUtils.equals(scmPurPrice.getBizType(), "1")) {
							for (ScmPurRequireEntry2 scmPurRequireEntry : scmPurRequireEntry2s) {
								if (scmPurRequireEntry.getPriceBillId() == scmPurPrice.getId()) {
									scmPurPrice2List.remove(scmPurPrice);
									switch (scmPurRequireEntry.getBizType()) {
									case "1":
										messageBuffer.append(Message.getMessage(param.getLang(),"iscm.purchasemanage.pricemanage.undoReleaseCheck.checkPurQuotation",
												new String[] {scmPurPrice.getPmNo(),scmPurRequireEntry.getPrNo()}));
										messageBuffer.append("\r\n");
										break;
									case "2":
										messageBuffer.append(Message.getMessage(param.getLang(),"iscm.purchasemanage.pricemanage.undoReleaseCheck.PurOrder",
												new String[] {scmPurPrice.getPmNo(),scmPurRequireEntry.getPrNo()}));
										messageBuffer.append("\r\n");
										break;
									case "3":
										messageBuffer.append(Message.getMessage(param.getLang(),"iscm.purchasemanage.pricemanage.undoReleaseCheck.PurReceive",
												new String[] {scmPurPrice.getPmNo(),scmPurRequireEntry.getPrNo()}));
										messageBuffer.append("\r\n");
										break;
									case "4":
										messageBuffer.append(Message.getMessage(param.getLang(),"iscm.purchasemanage.pricemanage.undoReleaseCheck.InvPurInWarehsBill",
												new String[] {scmPurPrice.getPmNo(),scmPurRequireEntry.getPrNo()}));
										messageBuffer.append("\r\n");
										break;
									case "5":
										messageBuffer.append(Message.getMessage(param.getLang(),"iscm.purchasemanage.pricemanage.undoReleaseCheck.Scmpursuppliersource",
												new String[] {scmPurPrice.getPmNo(),scmPurRequireEntry.getPrNo()}));
										messageBuffer.append("\r\n");
										break;
									}
								}
							}
						}
					}
				}
			}
			ArrayList<ScmPurPrice2> arrayList = new ArrayList<>();
			if (scmPurPrice2List != null && scmPurPrice2List.size() > 0) {
				for (ScmPurPrice2 scmPurPrice2 : scmPurPrice2List) {
					if (StringUtils.equals(scmPurPrice2.getStatus(), "E")) {
						arrayList.add(scmPurPrice2);
						sucess++;
					} else {
						messageBuffer.append(Message.getMessage(param.getLang(),"iscm.purchasemanage.pricemanage.undoReleaseCheck.undoReleaseCheckCode",
								new String[] {scmPurPrice2.getPmNo()}));
						messageBuffer.append("\r\n");
					}
				}
			}
			String message = Message.getMessage("iscm.purchasemanage.pricemanage.undoReleaseCheck.undoReleaseCheckMessage",
					new String[] {String.valueOf(sucess),String.valueOf((scmPurPrice2lList.size() - sucess))});
			commonBean.setObject(messageBuffer.toString());
			commonBean.setObject2(message);
			commonBean.setList(scmPurRequireEntry2s);
			commonBean.setList2(arrayList);
		}
		return commonBean;
	}
	@Override
	public List<ScmMaterialPrice> getPreParePrice(String purOrgUnitNo, String itemIds, Date bizDate,
			String finOrgUnitNo, String pmId, Param param) throws AppException {
		String priceType =sysParamBiz.getValue(param.getOrgUnitNo(), "SCM_PriceType", "1", param);
		String pricePrec = sysParamBiz.getValue(param.getOrgUnitNo(), "SCM_PricePrecision", "2", param);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("orgUnitNo", purOrgUnitNo);
		map.put("itemIds", itemIds);
		map.put("bizDate", FormatUtils.fmtDate(bizDate));
		map.put("priceType", priceType);
		map.put("pricePrec", pricePrec);
		map.put("finOrgUnitNo", finOrgUnitNo);
		map.put("pmId", pmId);
		return ((ScmPurPriceDAO)dao).getPreParePrice(map);
	}

	@Override
	public List<ScmMaterialPrice> getPreParePriceByVendorId(String purOrgUnitNo, long vendorId, String itemIds,
			Date bizDate, String finOrgUnitNo, String pmId, Param param) throws AppException {
		String priceType =sysParamBiz.getValue(param.getOrgUnitNo(), "SCM_PriceType", "1", param);
		String pricePrec = sysParamBiz.getValue(param.getOrgUnitNo(), "SCM_PricePrecision", "2", param);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("orgUnitNo", purOrgUnitNo);
		map.put("itemIds", itemIds);
		map.put("bizDate", FormatUtils.fmtDate(bizDate));
		map.put("priceType", priceType);
		map.put("pricePrec", pricePrec);
		map.put("finOrgUnitNo", finOrgUnitNo);
		map.put("pmId", pmId);
		map.put("vendorId", vendorId);
		return ((ScmPurPriceDAO)dao).getPreParePriceByVendorId(map);
	}

	@Override
	public List<ScmMaterialPrice> getPreParePrice(String purOrgUnitNo, String itemIds, Date bizDate,
			String finOrgUnitNo, String pmId, long vendorId, Param param) throws AppException {
		String priceType =sysParamBiz.getValue(param.getOrgUnitNo(), "SCM_PriceType", "1", param);
		String pricePrec = sysParamBiz.getValue(param.getOrgUnitNo(), "SCM_PricePrecision", "2", param);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("orgUnitNo", purOrgUnitNo);
		map.put("itemIds", itemIds);
		map.put("bizDate", FormatUtils.fmtDate(bizDate));
		map.put("priceType", priceType);
		map.put("pricePrec", pricePrec);
		map.put("finOrgUnitNo", finOrgUnitNo);
		map.put("pmId", pmId);
		if(vendorId > 0){
			map.put("vendorId", vendorId);
		}
		return ((ScmPurPriceDAO)dao).getPreParePrice(map,param);
	}

	@Override
	public List<ScmMaterialPrice> getPrice(String purOrgUnitNo, String itemIds, Date bizDate, String finOrgUnitNo,
			String pmId, long vendorId, Param param) throws AppException {
		String priceType =sysParamBiz.getValue(param.getOrgUnitNo(), "SCM_PriceType", "1", param);
		String pricePrec = sysParamBiz.getValue(param.getOrgUnitNo(), "SCM_PricePrecision", "2", param);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("orgUnitNo", purOrgUnitNo);
		map.put("itemIds", itemIds);
		map.put("bizDate", FormatUtils.fmtDate(bizDate));
		map.put("priceType", priceType);
		map.put("pricePrec", pricePrec);
		map.put("finOrgUnitNo", finOrgUnitNo);
		map.put("pmId", pmId);
		if(vendorId > 0){
			map.put("vendorId", vendorId);
		}
		return ((ScmPurPriceDAO)dao).getPrice(map,param);
	}

	@Override
	public List<ScmMaterialPrice> getMaterialPriceByItemidsAndVendorIdsList(List<ScmPurPriceQuery> object, Param param) throws AppException {
		if (object != null && !object.isEmpty()) {
			String priceType = sysParamBiz.getValue(param.getOrgUnitNo(), "SCM_PriceType", "1", param);
			String pricePrec = sysParamBiz.getValue(param.getOrgUnitNo(), "SCM_PricePrecision", "2", param);
			String getPriceWay = sysParamBiz.getValue(object.get(0).getPurOrgUnitNo(), "SCM_GetPriceWay", "N", param);
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("ScmPurPriceQuery", object);
			map.put("priceType", priceType);
			map.put("pricePrec", pricePrec);
			if (StringUtils.equals("Y", getPriceWay)) {
				map.put("getPriceWay", getPriceWay);
			}
			return ((ScmPurPriceDAO) dao).getMaterialPriceByItemidsAndVendorIdsList(map, param);
		} else {
			return new ArrayList<>();
		}
	}

	@Override
	public List<ScmMaterialPrePrice> getPrePrice(ScmPurPriceQuery scmPurPriceQuery, Param param) throws AppException {
		return this.getPrePrice(param.getOrgUnitNo(), scmPurPriceQuery.getItemIds(), scmPurPriceQuery.getBizDate(), param);
	}

	@Override
	public List selectPrePriceByVendor(List<ScmPurPriceQuery> object, Param param) throws AppException {
		if (object != null && !object.isEmpty()) {
			String getPriceWay = sysParamBiz.getValue(object.get(0).getPurOrgUnitNo(), "SCM_GetPriceWay", "N", param);
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("ScmPurPriceQuery", object);
			if (StringUtils.equals("Y", getPriceWay)) {
				map.put("getPriceWay", getPriceWay);
			}
			return ((ScmPurPriceDAO) dao).selectPrePriceByVendor(map, param);
		} else {
			return new ArrayList<>();
		}
	}

	@Override
	public List selectLastYearPriceByVendor(List<ScmPurPriceQuery> object, Param param) throws AppException {
		if (object != null && !object.isEmpty()) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("ScmPurPriceQuery", object);
			return ((ScmPurPriceDAO) dao).selectLastYearPriceByVendor(map, param);
		} else {
			return new ArrayList<>();
		}
	}

	@Override
	public ScmPurPrice2 updatePrtCount(ScmPurPrice2 scmPurPrice, Param param) throws AppException {
		if(scmPurPrice.getId()>0){
			ScmPurPrice2 bill = this.selectDirect(scmPurPrice.getId(), param);
			if(bill != null){
				int prtCount = bill.getPrtcount();
				bill.setPrtcount((prtCount+1));
				this.updateDirect(bill, param);
				return bill;
			}
		}
		return scmPurPrice;
	}
}
