package com.armitage.server.iscm.purchasemanage.purchasebusiness.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.armitage.server.api.business.purrequire.params.PurRequireParams;
import com.armitage.server.activity.util.AuditMsgUtil;
import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.CommonBean;
import com.armitage.server.common.base.model.Page;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.base.model.QueryParam;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.util.BeanUtil;
import com.armitage.server.common.util.CalendarUtil;
import com.armitage.server.common.util.ClassUtils;
import com.armitage.server.common.util.FormatUtils;
import com.armitage.server.common.util.ListSortUtil;
import com.armitage.server.common.util.LogUtils;
import com.armitage.server.iscm.basedata.model.PayMethod2;
import com.armitage.server.iscm.basedata.model.ScmMaterialGroup;
import com.armitage.server.iscm.basedata.model.ScmPurchaseType2;
import com.armitage.server.iscm.basedata.model.Scmsupplier2;
import com.armitage.server.iscm.basedata.model.SettleType2;
import com.armitage.server.iscm.basedata.service.PayMethodBiz;
import com.armitage.server.iscm.basedata.service.ScmMaterialGroupBiz;
import com.armitage.server.iscm.basedata.service.ScmPurchaseTypeBiz;
import com.armitage.server.iscm.basedata.service.ScmsupplierBiz;
import com.armitage.server.iscm.basedata.service.SettleTypeBiz;
import com.armitage.server.iscm.basedata.util.ScmMaterialUtil;
import com.armitage.server.iscm.common.model.ScmAuditDetailHistory2;
import com.armitage.server.iscm.common.model.ScmDataCollectionStepState;
import com.armitage.server.iscm.common.model.ScmDataCollectionStepState2;
import com.armitage.server.iscm.common.service.ScmDataCollectionStepStateBiz;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmMaterialPrice;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurPrice2;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurPriceEntry2;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurQuotation2;
import com.armitage.server.iscm.purchasemanage.pricemanage.service.ScmPurPriceBiz;
import com.armitage.server.iscm.purchasemanage.pricemanage.service.ScmPurQuotationBiz;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.dao.ScmPurRequireEntryDAO;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurOrder2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurOrderEntry2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurREReuse;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurRequire;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurRequire2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurRequireEntry2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurRequireEntryAdvQuery;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.service.ScmPurOrderBiz;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.service.ScmPurREReuseBiz;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.service.ScmPurRequireBiz;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.service.ScmPurRequireEntryBiz;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurBuyer2;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurSupplyInfo2;
import com.armitage.server.iscm.purchasemanage.purchasesetting.service.ScmPurBuyerBiz;
import com.armitage.server.iscm.purchasemanage.purchasesetting.service.ScmPurSupplyInfoBiz;
import com.armitage.server.system.model.BillType2;
import com.armitage.server.system.model.Code;
import com.armitage.server.system.model.OrgAdmin2;
import com.armitage.server.system.model.OrgBaseUnit;
import com.armitage.server.system.model.OrgCompany2;
import com.armitage.server.system.service.BillTypeBiz;
import com.armitage.server.system.service.CodeBiz;
import com.armitage.server.system.service.OrgAdminBiz;
import com.armitage.server.system.service.OrgUnitBiz;
import com.armitage.server.system.service.OrgUnitRelationBiz;
import com.armitage.server.system.service.SysParamBiz;
import com.armitage.server.system.util.OrgUnitRelationType;
import com.armitage.server.user.model.Usr;
import com.armitage.server.user.service.UsrBiz;
import org.springframework.stereotype.Service;

@Service("scmPurRequireEntryBiz")
public class ScmPurRequireEntryBizImpl extends BaseBizImpl<ScmPurRequireEntry2> implements ScmPurRequireEntryBiz {
	private static Log log = LogFactory.getLog(ScmPurRequireEntryBizImpl.class);

    private OrgUnitBiz orgUnitBiz;
    private ScmsupplierBiz scmsupplierBiz;
    private ScmMaterialGroupBiz scmMaterialGroupBiz;
    private ScmPurRequireBiz scmPurRequireBiz;
    private ScmPurOrderBiz scmPurOrderBiz;
    private SysParamBiz sysParamBiz;
    private ScmPurBuyerBiz scmPurBuyerBiz;
    private OrgUnitRelationBiz orgUnitRelationBiz;
    private ScmPurSupplyInfoBiz scmPurSupplyInfoBiz;
    private OrgAdminBiz orgAdminBiz;
    private CodeBiz codeBiz;
    private ScmDataCollectionStepStateBiz scmDataCollectionStepStateBiz;
    private ScmPurQuotationBiz scmPurQuotationBiz;
    private ScmPurPriceBiz scmPurPriceBiz;
    private BillTypeBiz billTypeBiz;
    private PayMethodBiz payMethodBiz;
	private SettleTypeBiz settleTypeBiz;
    private UsrBiz usrBiz;

    protected  HashMap<String,Object> myCacheDataMap = new HashMap<String,Object>();
    private ScmPurchaseTypeBiz scmPurchaseTypeBiz;
    private ScmPurREReuseBiz scmPurREReuseBiz;
    
	public void setScmPurBuyerBiz(ScmPurBuyerBiz scmPurBuyerBiz) {
        this.scmPurBuyerBiz = scmPurBuyerBiz;
    }

    public void setSysParamBiz(SysParamBiz sysParamBiz) {
        this.sysParamBiz = sysParamBiz;
    }

    public void setScmPurOrderBiz(ScmPurOrderBiz scmPurOrderBiz) {
        this.scmPurOrderBiz = scmPurOrderBiz;
    }

    public void setScmPurRequireBiz(ScmPurRequireBiz scmPurRequireBiz) {
        this.scmPurRequireBiz = scmPurRequireBiz;
    }

    public void setOrgUnitBiz(OrgUnitBiz orgUnitBiz) {
        this.orgUnitBiz = orgUnitBiz;
    }

    public void setScmMaterialGroupBiz(ScmMaterialGroupBiz scmMaterialGroupBiz) {
        this.scmMaterialGroupBiz = scmMaterialGroupBiz;
    }

    public void setScmsupplierBiz(ScmsupplierBiz scmsupplierBiz) {
        this.scmsupplierBiz = scmsupplierBiz;
    }

    public void setOrgUnitRelationBiz(OrgUnitRelationBiz orgUnitRelationBiz) {
        this.orgUnitRelationBiz = orgUnitRelationBiz;
    }
    
    public void setScmPurSupplyInfoBiz(ScmPurSupplyInfoBiz scmPurSupplyInfoBiz) {
        this.scmPurSupplyInfoBiz = scmPurSupplyInfoBiz;
    }
    
    public void setOrgAdminBiz(OrgAdminBiz orgAdminBiz) {
        this.orgAdminBiz = orgAdminBiz;
    }

    public void setCodeBiz(CodeBiz codeBiz) {
        this.codeBiz = codeBiz;
    }
    
    public void setScmDataCollectionStepStateBiz(ScmDataCollectionStepStateBiz scmDataCollectionStepStateBiz) {
		this.scmDataCollectionStepStateBiz = scmDataCollectionStepStateBiz;
	}

    
    public void setScmPurchaseTypeBiz(ScmPurchaseTypeBiz scmPurchaseTypeBiz) {
		this.scmPurchaseTypeBiz = scmPurchaseTypeBiz;
	}

	public void setScmPurQuotationBiz(ScmPurQuotationBiz scmPurQuotationBiz) {
		this.scmPurQuotationBiz = scmPurQuotationBiz;
	}

	public void setScmPurPriceBiz(ScmPurPriceBiz scmPurPriceBiz) {
		this.scmPurPriceBiz = scmPurPriceBiz;
	}

	public void setBillTypeBiz(BillTypeBiz billTypeBiz) {
		this.billTypeBiz = billTypeBiz;
	}

	public void setScmPurREReuseBiz(ScmPurREReuseBiz scmPurREReuseBiz) {
		this.scmPurREReuseBiz = scmPurREReuseBiz;
	}

	public void setUsrBiz(UsrBiz usrBiz) {
		this.usrBiz = usrBiz;
	}

	public void setPayMethodBiz(PayMethodBiz payMethodBiz) {
		this.payMethodBiz = payMethodBiz;
	}

	public void setSettleTypeBiz(SettleTypeBiz settleTypeBiz) {
		this.settleTypeBiz = settleTypeBiz;
	}

	@Override
    protected void beforeFindPage(Page page, Param param) throws AppException {
    	if (!StringUtils.equals("619323610", page.getArguments())) {
	        page.getParam()
	                .put((ClassUtils.getFinalModelSimpleName(ScmPurRequireEntry2.class) + "."
	                        + ScmPurRequireEntry2.FN_PURORGUNITNO),
	                        new QueryParam(
	                                (ClassUtils.getFinalModelSimpleName(ScmPurRequireEntry2.class) + "."
	                                        + ScmPurRequireEntry2.FN_PURORGUNITNO),
	                                QueryParam.QUERY_EQ, param.getOrgUnitNo()));
    	}else {
    		List<OrgAdmin2> orgAdminList = orgAdminBiz.findChild(param.getOrgUnitNo(), param);
    		StringBuffer orgUnitNos = new StringBuffer("");
    		if(orgAdminList!=null && !orgAdminList.isEmpty()) {
    			for(OrgAdmin2 orgAdmin:orgAdminList) {
    				if(StringUtils.isNotBlank(orgUnitNos.toString()))
    					orgUnitNos.append(",");
    				orgUnitNos.append("'").append(orgAdmin.getOrgUnitNo()).append("'");
    			}
    		}else {
    			orgUnitNos.append("'").append(param.getOrgUnitNo()).append("'");
    		}
    		String sqlCondition = page.getSqlCondition();
        	if(StringUtils.isBlank(sqlCondition)) {
        		sqlCondition="(ScmPurRequire.orgUnitNo in ("+orgUnitNos+") or ScmPurRequire.purOrgUnitNo='"+param.getOrgUnitNo()+"')";
        	}else {
        		sqlCondition = sqlCondition+"and (ScmPurRequire.orgUnitNo in ("+orgUnitNos+") or ScmPurRequire.purOrgUnitNo='"+param.getOrgUnitNo()+"')";
        	}
        	page.setSqlCondition(sqlCondition);
    	}
    }

    
    @Override
	protected HashMap<String, Object> addFindAllPageParam(Page page, HashMap<String, Object> map, Param param) {
    	if (!StringUtils.equals("619323610", page.getArguments())) {
    		map.put("orderQty", "Y");
    	}
    	String unifiedOrDirect = sysParamBiz.getValue(param.getOrgUnitNo(), "SCM_UnifiedOrDirect", "U", param);
    	map.put("unifiedOrDirect", unifiedOrDirect);
		return map;
	}

	@Override
    public List<ScmPurRequireEntry2> selectByPrId(long prId, Param param) throws AppException {
        if (prId > 0) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("prId", prId);
            List<ScmPurRequireEntry2> scmPurRequireEntryList = ((ScmPurRequireEntryDAO) dao).selectByPrId(map);
            if(scmPurRequireEntryList!=null && !scmPurRequireEntryList.isEmpty()) {
            	myCacheDataMap = new HashMap<String,Object>();
            	for(ScmPurRequireEntry2 scmPurRequireEntry:scmPurRequireEntryList) {
            		this.setConvertMap(scmPurRequireEntry, param);
            		setRowAuditRemarks(scmPurRequireEntry,param);
            	}
            }
            return scmPurRequireEntryList;
        }
        return null;
    }

	@Override
    public void deleteByPrId(long prId, Param param) throws AppException {
        if (prId > 0) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("prId", prId);
            ((ScmPurRequireEntryDAO) dao).deleteByPrId(map);
        }
    }

    @Override
    protected void afterSelect(ScmPurRequireEntry2 entity, Param param) throws AppException {
        if (entity != null) {
        	setConvertMap(entity,param);
        	setRowAuditRemarks(entity,param);
        }
    }

    @Override
    protected void afterFindPage(List list, Page page, Param param) throws AppException {
        if (list != null && !list.isEmpty()) {
        	myCacheDataMap = new HashMap<String,Object>();
            for (ScmPurRequireEntry2 scmPurRequireEntry : (List<ScmPurRequireEntry2>)list) {
            	setConvertMap(scmPurRequireEntry,param);
            }
        }
    }
    
    @Override
    protected void afterQueryPage(List list, Page page, String xmlId, Param param) throws AppException {
		if (list != null && list.size() > 0) {
			myCacheDataMap = new HashMap<String,Object>();
            List<ScmPurRequireEntry2> scmPurRequireEntry2s = list;
            for (ScmPurRequireEntry2 scmPurRequireEntry : scmPurRequireEntry2s) {
    	        if (scmPurRequireEntry.getBuyerId() > 0) {
    	            // 采购员
    	            ScmPurBuyer2 scmPurBuyer = scmPurBuyerBiz.selectDirect(scmPurRequireEntry.getBuyerId(), param);
    	            if (scmPurBuyer != null) {
    	            	scmPurRequireEntry.setBuyerName(scmPurBuyer.getBuyerName());
    	                scmPurRequireEntry.setConvertMap(ScmPurRequireEntry2.FN_BUYERID, scmPurBuyer);
    	            }
    	        }
    	        if (scmPurRequireEntry.getVendorId() > 0) {
    	            // 供应商
    	            Scmsupplier2 scmsupplier = scmsupplierBiz.selectDirect(scmPurRequireEntry.getVendorId(), param);
    	            if (scmsupplier != null) {
    	                scmPurRequireEntry.setConvertMap(ScmPurRequireEntry2.FN_VENDORID, scmsupplier);
    	                scmPurRequireEntry.setVendorName(scmsupplier.getVendorName());
    	            }
    	        }
    	        if (StringUtils.isNotBlank(scmPurRequireEntry.getOrgUnitNo())) {
    	            // 申购组织
    	            OrgBaseUnit orgBaseUnit = orgUnitBiz.selectbyOrgNo(scmPurRequireEntry.getOrgUnitNo(), param);
    	            if (orgBaseUnit != null) {
    	                scmPurRequireEntry.setConvertMap(ScmPurRequireEntry2.FN_ORGUNITNO, orgBaseUnit);
    	            }
    	        }
    	        if (StringUtils.isNotBlank(scmPurRequireEntry.getPurOrgUnitNo())) {
    	            // 采购组织
    	            OrgBaseUnit orgBaseUnit = orgUnitBiz.selectbyOrgNo(scmPurRequireEntry.getPurOrgUnitNo(), param);
    	            if (orgBaseUnit != null) {
    	                scmPurRequireEntry.setConvertMap(ScmPurRequireEntry2.FN_PURORGUNITNO, orgBaseUnit);
    	            }
    	        }
    	        if(scmPurRequireEntry.getPriceBillId() > 0 && StringUtils.isNotBlank(scmPurRequireEntry.getRefPriceStatus())){
    	        	//价格来源
    	        	if("1".equals(scmPurRequireEntry.getRefPriceStatus())){
    	        		//报价
    	        		ScmPurQuotation2 scmPurQuotation = (ScmPurQuotation2) myCacheDataMap.get(ClassUtils.getFinalModelSimpleName(ScmPurQuotation2.class)+"_"+scmPurRequireEntry.getPriceBillId());
    	        		if(scmPurQuotation==null) {
    	        			scmPurQuotation = scmPurQuotationBiz.selectDirect(scmPurRequireEntry.getPriceBillId(), param);
    	        			myCacheDataMap.put(ClassUtils.getFinalModelSimpleName(ScmPurQuotation2.class)+"_"+scmPurRequireEntry.getPriceBillId(),scmPurQuotation);
    	        		}
    		            if (scmPurQuotation != null) {
    		            	scmPurRequireEntry.setPriceBillNo(scmPurQuotation.getPqNo());
    		            	scmPurRequireEntry.setPriceBillStatus(scmPurQuotation.getStatus());
    		            }
    	        	}else{
    	        		//定价（包括临时定价）
    	        		ScmPurPrice2 scmPurPrice = (ScmPurPrice2) myCacheDataMap.get(ClassUtils.getFinalModelSimpleName(ScmPurPrice2.class)+"_"+scmPurRequireEntry.getPriceBillId());
    	        		if(scmPurPrice==null) {
    	        			scmPurPrice = scmPurPriceBiz.selectDirect(scmPurRequireEntry.getPriceBillId(), param);
    	        			myCacheDataMap.put(ClassUtils.getFinalModelSimpleName(ScmPurPrice2.class)+"_"+scmPurRequireEntry.getPriceBillId(),scmPurPrice);
    	        		}
    		            if (scmPurPrice != null) {
    		            	scmPurRequireEntry.setPriceBillNo(scmPurPrice.getPmNo());
    		            	scmPurRequireEntry.setPriceBillStatus(scmPurPrice.getStatus());
    		            }
    	        	}
    	        }
            }
        }
	}

    private void setRowAuditRemarks(ScmPurRequireEntry2 scmPurRequireEntry,Param param){
    	if(scmPurRequireEntry != null && StringUtils.isNotBlank(scmPurRequireEntry.getRowAuditRemarks())){
    		List<ScmAuditDetailHistory2> auditDetailHistoryList = new ArrayList<>();
			StringBuffer rowAuditRemarks = new StringBuffer("");
			String[] rowAuditRemarksList = scmPurRequireEntry.getRowAuditRemarks().split(",");
			for(String auditRemarks : rowAuditRemarksList){
				if (StringUtils.isNotBlank(rowAuditRemarks.toString())){
					rowAuditRemarks.append("；");
				}
				if(auditRemarks.indexOf("^") > 0){
					ScmAuditDetailHistory2 scmAuditDetailHistory = new ScmAuditDetailHistory2(true);
					String oper = auditRemarks.split("\\^")[0];
					String opinion = auditRemarks.split("\\^")[1];
					String operDate = auditRemarks.split("\\^")[2];
					String currrentRemarks = auditRemarks.split("\\^")[3];
					scmAuditDetailHistory.setOper(oper);
					scmAuditDetailHistory.setOperDate(FormatUtils.parseDateTime(operDate));
					scmAuditDetailHistory.setOpinion(opinion);
					scmAuditDetailHistory.setRemarks(currrentRemarks);
					Usr usr = usrBiz.selectByCode(oper, param);
					if(usr != null){
						rowAuditRemarks.append(usr.getName()).append("：");
						scmAuditDetailHistory.setOperName(usr.getName());
					}
					rowAuditRemarks.append(currrentRemarks);
					Code code = codeBiz.selectByCategoryAndCode("SCM_opinion", opinion);
					if(code!=null){
						scmAuditDetailHistory.setOpinionName(code.getName());
					}
					auditDetailHistoryList.add(scmAuditDetailHistory);
				}else{
					//rowAuditRemarks.append(auditRemarks);
				}
			}
			if(StringUtils.isNotBlank(rowAuditRemarks.toString())){
				scmPurRequireEntry.setRowAuditRemarks(rowAuditRemarks.toString());
			}
			scmPurRequireEntry.setAuditDetailHistoryList(auditDetailHistoryList);
		}
    }
    
    public void setConvertMap(ScmPurRequireEntry2 scmPurRequireEntry,Param param) {
    	if (scmPurRequireEntry != null) {
    		if (scmPurRequireEntry.getGroupId() > 0) {
	            // 物料分类
	            ScmMaterialGroup scmMaterialGroup = scmMaterialGroupBiz.selectDirect(scmPurRequireEntry.getGroupId(), param);
	            if (scmMaterialGroup != null) {
	                scmPurRequireEntry.setConvertMap(ScmPurRequireEntry2.FN_GROUPID, scmMaterialGroup);
	            }
	        }
	        if (scmPurRequireEntry.getBuyerId() > 0) {
	            // 采购员
	            ScmPurBuyer2 scmPurBuyer = scmPurBuyerBiz.selectDirect(scmPurRequireEntry.getBuyerId(), param);
	            if (scmPurBuyer != null) {
	            	scmPurRequireEntry.setBuyerName(scmPurBuyer.getBuyerName());
	                scmPurRequireEntry.setConvertMap(ScmPurRequireEntry2.FN_BUYERID, scmPurBuyer);
	            }
	        }
	        if (scmPurRequireEntry.getVendorId() > 0) {
	            // 供应商
	            Scmsupplier2 scmsupplier = scmsupplierBiz.selectDirect(scmPurRequireEntry.getVendorId(), param);
	            if (scmsupplier != null) {
	                scmPurRequireEntry.setConvertMap(ScmPurRequireEntry2.FN_VENDORID, scmsupplier);
	                scmPurRequireEntry.setVendorName(scmsupplier.getVendorName());
	                scmPurRequireEntry.setVendorNo(scmsupplier.getVendorNo());
	            }
	        }
	        if (StringUtils.isNotBlank(scmPurRequireEntry.getOrgUnitNo())) {
	            // 申购组织
	            OrgBaseUnit orgBaseUnit = orgUnitBiz.selectbyOrgNo(scmPurRequireEntry.getOrgUnitNo(), param);
	            if (orgBaseUnit != null) {
	                scmPurRequireEntry.setConvertMap(ScmPurRequireEntry2.FN_ORGUNITNO, orgBaseUnit);
	            }
	        }
	        if (StringUtils.isNotBlank(scmPurRequireEntry.getPurOrgUnitNo())) {
	            // 采购组织
	            OrgBaseUnit orgBaseUnit = orgUnitBiz.selectbyOrgNo(scmPurRequireEntry.getPurOrgUnitNo(), param);
	            if (orgBaseUnit != null) {
	                scmPurRequireEntry.setConvertMap(ScmPurRequireEntry2.FN_PURORGUNITNO, orgBaseUnit);
	            }
	        }
	        if (StringUtils.isNotBlank(scmPurRequireEntry.getRecStorageOrgUnitNo())) {
	            // 收货组织
	            OrgBaseUnit orgBaseUnit = orgUnitBiz.selectbyOrgNo(scmPurRequireEntry.getRecStorageOrgUnitNo(), param);
	            if (orgBaseUnit != null) {
	                scmPurRequireEntry.setConvertMap(ScmPurRequireEntry2.FN_RECSTORAGEORGUNITNO, orgBaseUnit);
	            }
	        }
	        if (StringUtils.isNotBlank(scmPurRequireEntry.getFinOrgUnitNo())) {
	            // 需求门店
	            OrgBaseUnit orgBaseUnit = orgUnitBiz.selectbyOrgNo(scmPurRequireEntry.getFinOrgUnitNo(), param);
	            if (orgBaseUnit != null) {
	                scmPurRequireEntry.setConvertMap(ScmPurRequireEntry2.FN_FINORGUNITNO, orgBaseUnit);
	            }
	        }
	        if(StringUtils.isNotBlank(scmPurRequireEntry.getPurOrderStatus())){
	        	// 订货单行状态
				Code code = codeBiz.selectByCategoryAndCode("quotationStatus", scmPurRequireEntry.getPurOrderStatus());
				if(code!=null)
					scmPurRequireEntry.setPurOrderStatusName(code.getName());
			}
	        if(StringUtils.isNotBlank(scmPurRequireEntry.getPurReceiveStatus())){
	        	// 收货单行状态
				Code code = codeBiz.selectByCategoryAndCode("quotationStatus", scmPurRequireEntry.getPurReceiveStatus());
				if(code!=null)
					scmPurRequireEntry.setPurReceiveStatusName(code.getName());
			}
	        if(StringUtils.isNotBlank(scmPurRequireEntry.getInvPurInwarehsStatus())){
	        	// 入库单状态
				Code code = codeBiz.selectByCategoryAndCode("warehouseStatus", scmPurRequireEntry.getInvPurInwarehsStatus());
				if(code!=null)
					scmPurRequireEntry.setInvPurInwarehsStatusName(code.getName());
			}
	        if(scmPurRequireEntry.getPriceBillId() > 0 && StringUtils.isNotBlank(scmPurRequireEntry.getRefPriceStatus())){
	        	//价格来源
	        	if("1".equals(scmPurRequireEntry.getRefPriceStatus())){
	        		//报价
	        		ScmPurQuotation2 scmPurQuotation = (ScmPurQuotation2) myCacheDataMap.get(ClassUtils.getFinalModelSimpleName(ScmPurQuotation2.class)+"_"+scmPurRequireEntry.getPriceBillId());
	        		if(scmPurQuotation==null) {
	        			scmPurQuotation = scmPurQuotationBiz.selectDirect(scmPurRequireEntry.getPriceBillId(), param);
	        			myCacheDataMap.put(ClassUtils.getFinalModelSimpleName(ScmPurQuotation2.class)+"_"+scmPurRequireEntry.getPriceBillId(),scmPurQuotation);
	        		}
		            if (scmPurQuotation != null) {
		            	scmPurRequireEntry.setPriceBillNo(scmPurQuotation.getPqNo());
		            	scmPurRequireEntry.setPriceBillStatus(scmPurQuotation.getStatus());
		            }
	        	}else{
	        		//定价（包括临时定价）
	        		ScmPurPrice2 scmPurPrice = (ScmPurPrice2) myCacheDataMap.get(ClassUtils.getFinalModelSimpleName(ScmPurPrice2.class)+"_"+scmPurRequireEntry.getPriceBillId());
	        		if(scmPurPrice==null) {
	        			scmPurPrice = scmPurPriceBiz.selectDirect(scmPurRequireEntry.getPriceBillId(), param);
	        			myCacheDataMap.put(ClassUtils.getFinalModelSimpleName(ScmPurPrice2.class)+"_"+scmPurRequireEntry.getPriceBillId(),scmPurPrice);
	        		}
		            if (scmPurPrice != null) {
		            	scmPurRequireEntry.setPriceBillNo(scmPurPrice.getPmNo());
		            	scmPurRequireEntry.setPriceBillStatus(scmPurPrice.getStatus());
		            }
	        	}
	        }
	        /*if(scmPurRequireEntry.getItemId()>0){
				//获取超收比例
				ScmMaterial2 scmMaterial = scmMaterialBiz.findByPurItemId(param.getControlUnitNo(), scmPurRequireEntry.getPurOrgUnitNo(), scmPurRequireEntry.getItemId(), param);
				if(scmMaterial!=null)
					scmPurRequireEntry.setSupplyCycle(scmMaterial.getPurSupplyCycle());
			}*/
	        //获取采购类型
	        if (StringUtils.isNotBlank(scmPurRequireEntry.getBizType())) {
	        	ScmPurchaseType2 scmPurchaseType = (ScmPurchaseType2) myCacheDataMap.get(ClassUtils.getFinalModelSimpleName(ScmPurchaseType2.class)+"_"+scmPurRequireEntry.getBizType());
	        	if (scmPurchaseType == null) {
	        		scmPurchaseType = scmPurchaseTypeBiz.selectByCodeAncCtrl(scmPurRequireEntry.getBizType(),param);
	        		myCacheDataMap.put(ClassUtils.getFinalModelSimpleName(ScmPurchaseType2.class)+"_"+scmPurRequireEntry.getBizType(), scmPurchaseType);
	        	}
	        	if (scmPurchaseType != null) {
	        		scmPurRequireEntry.setBizTypeName(scmPurchaseType.getName());
					scmPurRequireEntry.setConvertMap(scmPurRequireEntry.FN_BIZTYPE, scmPurchaseType);
				}
			}
    	}
    }
    @Override
    public void updateRowStatusByPrId(long prId, String status, String checker, Date checkDate, Param param)
            throws AppException {
        if (prId > 0 && StringUtils.isNotBlank(status)) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("prId", prId);
            map.put("rowStatus", status);
            map.put("checker", checker);
            map.put("checkDate", checkDate);
            ((ScmPurRequireEntryDAO) dao).updateRowStatusByPrId(map);
        }
    }

    @Override
    public List<ScmPurRequireEntry2> updateStatus(List<ScmPurRequireEntry2> scmPurRequireEntryList, Param param)
            throws AppException {
        if (scmPurRequireEntryList != null && !scmPurRequireEntryList.isEmpty()) {
            List<Long> prIdList = new ArrayList<>();// 记录需要更新的请购单
            List<ScmPurRequireEntry2> scmPurRequireEntryList2 = new ArrayList<>();
            for (ScmPurRequireEntry2 scmPurRequireEntry : scmPurRequireEntryList) {
            	
//                ScmPurRequireEntry2 scmPurRequireEntry2 = this.updateDirect(scmPurRequireEntry, param);
            	this.updateGrantedStatus(scmPurRequireEntry, param);
                if (scmPurRequireEntry != null) {
                    scmPurRequireEntryList2.add(scmPurRequireEntry);
                    if (scmPurRequireEntry.getPrId() > 0 && !prIdList.contains(scmPurRequireEntry.getPrId())) {
                        prIdList.add(scmPurRequireEntry.getPrId());
                    }
                }
            }
            for (long prId : prIdList) {
                ScmPurRequire2 scmPurRequire = scmPurRequireBiz.selectDirect(prId, param);
                if (scmPurRequire != null) {
                    List<ScmPurRequireEntry2> list = this.selectByPrId(prId, param);
                    if (list != null && !list.isEmpty()) {
                        int count1 = 0;// 记录已发放条数
                        int count2 = 0;// 记录拒绝条数
                        for (ScmPurRequireEntry2 requireEntry : list) {
                            if (StringUtils.equals("E", requireEntry.getRowStatus())) {
                                count1++;
                            } else if (StringUtils.equals("C", requireEntry.getRowStatus())) {
                                count2++;
                            }
                        }
                        if (count2 > 0) {
                            // 拒绝条数大于0时，等于总条数时请购单状态为关闭，否则为部分关闭
                            if (count2 == list.size()) {
                                scmPurRequire.setStatus("C");
                            } else {
                                scmPurRequire.setStatus("F");
                            }
                        } else if (count2 == 0 && count1 > 0) {
                            // 已发放条数大于0时，等于总条数时请购单状态为下达，否则为部分下达
                            if (count1 == list.size()) {
                                scmPurRequire.setStatus("E");
                            } else {
                                scmPurRequire.setStatus("S");
                            }
                        } else if (count2 == 0 && count1 == 0) {
                            scmPurRequire.setStatus("A");
                        }
                        scmPurRequireBiz.updateDirect(scmPurRequire, param);
                    }
                }
            }
            if (scmPurRequireEntryList2 != null && !scmPurRequireEntryList2.isEmpty()) {
                return scmPurRequireEntryList2;
            }
        }
        return null;
    }

    @Override
    public void updateBillStatusByEntry(ScmPurRequireEntry2 scmPurRequireEntry, Param param) throws AppException {
        if (scmPurRequireEntry != null) {
        	long currentTimeMillis = System.currentTimeMillis();
            ScmPurRequire2 scmPurRequire = scmPurRequireBiz.selectDirect(scmPurRequireEntry.getPrId(), param);
            System.out.println(System.currentTimeMillis()-currentTimeMillis+"查询请购主单耗时");
            if (scmPurRequire != null) {
            	long currentTimeMillis1 = System.currentTimeMillis();
                List<ScmPurRequireEntry2> list = this.selectByPrIdCount(scmPurRequireEntry.getPrId(), param);
                System.out.println(System.currentTimeMillis()-currentTimeMillis1+"查询请购明细耗时");
                if (list != null && !list.isEmpty()) {
                    int count1 = 0;// 记录已发放条数
                    int count2 = 0;// 记录拒绝条数
                    for (ScmPurRequireEntry2 requireEntry : list) {
                        if (StringUtils.equals("E", requireEntry.getRowStatus())) {
                            count1++;
                        } else if (StringUtils.equals("C", requireEntry.getRowStatus())) {
                            count2++;
                        }
                    }
                    if (count2 > 0) {
                        // 拒绝条数大于0时，等于总条数时请购单状态为关闭，否则为部分关闭
                        if (count2 == list.size()) {
                            scmPurRequire.setStatus("C"); // 关闭
                        } else {
                            scmPurRequire.setStatus("F"); // 部分关闭
                        }
                    } else if (count2 == 0 && count1 > 0) {
                        // 已发放条数大于0时，等于总条数时请购单状态为下达，否则为部分下达
                        if (count1 == list.size()) {
                            scmPurRequire.setStatus("E"); // 下达
                        } else {
                            scmPurRequire.setStatus("S"); // 部分下达
                        }
                    } else if (count2 == 0 && count1 == 0) {
                        scmPurRequire.setStatus("A");
                    }
                    long currentTimeMillis2 = System.currentTimeMillis();
                    scmPurRequireBiz.updateDirect(scmPurRequire, param);
                    System.out.println(System.currentTimeMillis()-currentTimeMillis1+"更新请购主单状态耗时");
                }
            }
        }
    }

    private List<ScmPurRequireEntry2> selectByPrIdCount(long prId, Param param) {
    	if (prId > 0) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("prId", prId);
            return ((ScmPurRequireEntryDAO) dao).selectByPrIdCount(map);
		}
		return null;
	}

	@Override
    public CommonBean generateOrder(ScmDataCollectionStepState2 stepState,final List<ScmPurRequireEntry2> scmPurRequireEntryList,final Param param)
            throws AppException {
    	final CommonBean commonBean = new CommonBean(); 
		ScmDataCollectionStepState2 scmDataCollectionStepState = scmDataCollectionStepStateBiz.updateByAsynProcessed(stepState,ScmDataCollectionStepState2.SATATE_RUN, null, param);
		final ScmDataCollectionStepState2 tempScmStepData = new ScmDataCollectionStepState2();
		BeanUtil.copyProperties(tempScmStepData, scmDataCollectionStepState);
		//进行夜核步骤操作
		ExecutorService executors = Executors.newCachedThreadPool();
		executors.execute(new Runnable() {
			@Override
			public void run() {
				try {
					List<ScmPurOrder2> rtnList = asynGenerateOrder(scmPurRequireEntryList, param);
					//更新状态
					scmDataCollectionStepStateBiz.updateByAsynProcessed(tempScmStepData, ScmDataCollectionStepState.SATATE_SUCCESS,String.valueOf(rtnList==null?0:rtnList.size()), param);
				} catch (Exception e) {
					//保存错误信息
					scmDataCollectionStepStateBiz.updateByAsynProcessed(tempScmStepData, ScmDataCollectionStepState.SATATE_FAIL, e.getMessage(), param);
				}
			}
		});
		commonBean.setObject(tempScmStepData);
		return commonBean;
    }
    
	public List<ScmPurOrder2> asynGenerateOrder(List<ScmPurRequireEntry2> scmPurRequireEntryList,Param param) throws AppException{
		return asynGenerateOrder(scmPurRequireEntryList,false,param);
	}
	@Override
	public List<ScmPurOrder2> asynGenerateOrder(List<ScmPurRequireEntry2> scmPurRequireEntryList,boolean directPurchase,Param param) throws AppException{
        if (scmPurRequireEntryList != null && !scmPurRequireEntryList.isEmpty()) {
        	String fields[]={"orgUnitNo","itemNo"};
			String sorts[]={"ASC","ASC"};
			scmPurRequireEntryList = (List<ScmPurRequireEntry2>)ListSortUtil.sort(scmPurRequireEntryList, fields,sorts);
            List<ScmPurOrder2> rtnList = new ArrayList<>();
            // 获取系统参数设置的付款方式和结算方式
            String payment = sysParamBiz.getValue(param.getOrgUnitNo(), "SCM_PayMethod", "", param);
            if (StringUtils.isBlank(payment))
                throw new AppException(
                        "iscm.purchasemanage.purchasebusiness.ScmPurRequireBizImpl.generateOrder.error.notPayMethod");
            String settlement = sysParamBiz.getValue(param.getOrgUnitNo(), "SCM_SettleType", "", param);
            String selectType = sysParamBiz.getValue(param.getOrgUnitNo(), "SCM_SelectType","1", param);
            int pricePrec = Integer.parseInt(sysParamBiz.getValue(param.getOrgUnitNo(), "SCM_PricePrecision", "2", param));
            int amtPrec = Integer.parseInt(sysParamBiz.getValue(param.getOrgUnitNo(), "SCM_AmtPrecision", "2", param));
            if (StringUtils.isBlank(settlement))
                throw new AppException(
                        "iscm.purchasemanage.purchasebusiness.ScmPurRequireBizImpl.generateOrder.error.notSettleType");
            SettleType2 settleType = settleTypeBiz.selectByCode(settlement, param);
            //付款方式
			PayMethod2 payMethod = payMethodBiz.selectByCode(payment, param);
			if (settleType == null) {
				throw new AppException("iscm.purchasemanage.purchasebusiness.ScmPurRequireBizImpl.generateOrder.error.SettleTypeType");
			}
			if (payMethod == null) {
				throw new AppException("iscm.purchasemanage.purchasebusiness.ScmPurRequireBizImpl.generateOrder.error.notPayMethodType");
			}
            String splitByStore = sysParamBiz.getValue(param.getOrgUnitNo(), "SCM_OrderSplitByStore", "N", param);
            List<String> list = new ArrayList<>();
            HashMap<String, String> purToFinMap = new HashMap<String, String>();
            HashMap<Long, ScmPurRequire2> purRequireMap = new HashMap<Long, ScmPurRequire2>();
            for (ScmPurRequireEntry2 scmPurRequireEntry : scmPurRequireEntryList) {
            	ScmPurRequire2 scmPurRequire = purRequireMap.get(scmPurRequireEntry.getPrId());
            	if(scmPurRequire==null) {
            		scmPurRequire = scmPurRequireBiz.selectDirect(scmPurRequireEntry.getPrId(), param);
            		purRequireMap.put(scmPurRequireEntry.getPrId(), scmPurRequire);
            	}
                // 按供应商、需求日期、统直配、采购员进行拆单
            	String key = scmPurRequireEntry.getVendorId() + "_"
                        + FormatUtils.fmtDateTime(scmPurRequireEntry.getReqDate(), "yyyyMMdd") + "_"
                        + (scmPurRequireEntry.isUnified() ? "1" : "0") + "_"
                        + scmPurRequireEntry.getBuyerId();
            	if(StringUtils.equals("Y", splitByStore)) {
            		key=key+"_"+scmPurRequireEntry.getRecStorageOrgUnitNo();
            	}
                if (!list.contains(key)) {
                    list.add(key);
                    CommonBean bean = new CommonBean();
                    List<ScmPurOrder2> scmPurOrderList = new ArrayList<>();
                    List<ScmPurOrderEntry2> scmPurOrderEntryList = new ArrayList<>();
                    ScmPurOrder2 scmPurOrder = new ScmPurOrder2(true);
                    scmPurOrder.setStatus("I");
                    scmPurOrder.setUnified(scmPurRequireEntry.isUnified());
                    scmPurOrder.setOrderDate(FormatUtils
                            .parseDateTime(FormatUtils.fmtDate(CalendarUtil.getDate(param)) + " 00:00:00"));
                    scmPurOrder.setBizType(scmPurRequireEntry.getBizType()); // 日常采购
                    scmPurOrder.setOrgUnitNo(scmPurRequireEntry.getPurOrgUnitNo());
                    if(!purToFinMap.containsKey(scmPurRequireEntry.getPurOrgUnitNo())) {
	                    List<OrgCompany2> orgCompanyList = orgUnitRelationBiz.findToOrgUnitByType(
	                            OrgUnitRelationType.PURTOFIN, scmPurRequireEntry.getPurOrgUnitNo(), false, null, param);
	                    if (orgCompanyList == null || orgCompanyList.isEmpty()) {
	                        throw new AppException(
	                                "iscm.purchasemanage.purchasebusiness.ScmPurRequireBizImpl.error.notfin");
	                    }
	                    purToFinMap.put(scmPurRequireEntry.getPurOrgUnitNo(), orgCompanyList.get(0).getOrgUnitNo());
                    }
                    scmPurOrder.setFinOrgUnitNo(purToFinMap.get(scmPurRequireEntry.getPurOrgUnitNo()));
                    scmPurOrder.setVendorId(scmPurRequireEntry.getVendorId());
                    scmPurOrder.setPayment(payment);
                    scmPurOrder.setSettlement(settlement);
                    scmPurOrder.setCreator(param.getUsrCode());
                    scmPurOrder.setCreateDate(CalendarUtil.getDate(param));
                    scmPurOrder.setBuyerId(scmPurRequireEntry.getBuyerId());
                    scmPurOrder.setPurGroupId(scmPurRequireEntry.getPurGroupId());
                    scmPurOrder.setDirectPurchase(directPurchase);
                    scmPurOrderList.add(scmPurOrder);
                    bean.setList(scmPurOrderList);
                    HashMap<String, String> invToFinMap = new HashMap<String, String>();
                    for (int i = 0; i < scmPurRequireEntryList.size(); i++) {
                        if (scmPurRequireEntryList.get(i).getVendorId() == scmPurOrder.getVendorId()
                                && scmPurRequireEntryList.get(i).getReqDate()
                                        .compareTo(scmPurRequireEntry.getReqDate()) == 0
                                        & scmPurRequireEntryList.get(i).isUnified() == scmPurOrder.isUnified()
                                 && scmPurRequireEntryList.get(i).getBuyerId() == scmPurOrder.getBuyerId() 
                                 && (StringUtils.equals("N", splitByStore) || (StringUtils.equals("Y", splitByStore) 
                                		 && StringUtils.equals(scmPurRequireEntryList.get(i).getRecStorageOrgUnitNo(), scmPurRequireEntry.getRecStorageOrgUnitNo())))) {
                            ScmPurOrderEntry2 scmPurOrderEntry = new ScmPurOrderEntry2(true);
                            scmPurOrderEntry.setLineId(i + 1);
                            scmPurOrderEntry.setItemId(scmPurRequireEntryList.get(i).getItemId());
                            scmPurOrderEntry.setSupplyCycle(scmPurRequireEntryList.get(i).getSupplyCycle());
                            scmPurOrderEntry.setPurUnit(scmPurRequireEntryList.get(i).getPurUnit());
                            scmPurOrderEntry.setPieQty(scmPurRequireEntryList.get(i).getPieQty());
                            scmPurOrderEntry.setPieUnit(scmPurRequireEntryList.get(i).getPieUnit());
                            scmPurOrderEntry.setReqDate(scmPurRequireEntryList.get(i).getReqDate());
                            scmPurOrderEntry.setRecentPrice(scmPurRequireEntryList.get(i).getRecentPrice());
                            scmPurOrderEntry.setStockQty(scmPurRequireEntryList.get(i).getStockQty());
                            scmPurOrderEntry.setDeliveryDate(scmPurRequireEntryList.get(i).getReqDate());
                            scmPurOrderEntry.setReqQty(scmPurRequireEntryList.get(i).getQty());
                            scmPurOrderEntry.setBaseUnit(scmPurRequireEntryList.get(i).getBaseUnit());
                            scmPurOrderEntry.setBaseQty(scmPurRequireEntryList.get(i).getBaseQty());
                            scmPurOrderEntry.setQty(scmPurRequireEntryList.get(i).getQty());
                            scmPurOrderEntry.setAttachmentId(scmPurRequireEntryList.get(i).getAttachmentId());
                            String priceBillId = "";
                            ScmMaterialPrice scmMaterialPrice=null;
							if (StringUtils.contains("6,7",scmPurRequireEntryList.get(i).getRefPriceStatus()) && StringUtils.isNotBlank(scmPurRequireEntryList.get(i).getRefPriceStatus())) {
								priceBillId = String.valueOf(scmPurRequireEntryList.get(i).getPriceBillId());
							}
							if (StringUtils.equals("1", selectType)) {
								scmMaterialPrice = ScmMaterialUtil.getMaterialPrice(
										scmPurRequireEntry.getPurOrgUnitNo(), scmPurOrder.getVendorId(),
										String.valueOf(scmPurOrderEntry.getItemId()), scmPurOrderEntry.getPurUnit(),
										scmPurOrder.getOrderDate(),
										scmPurRequireEntryList.get(i).getRecStorageOrgUnitNo(), priceBillId, param);
							} else if (StringUtils.equals("2", selectType)) {
								scmMaterialPrice = ScmMaterialUtil.getMaterialPrice(
										scmPurRequireEntry.getPurOrgUnitNo(), scmPurOrder.getVendorId(),
										String.valueOf(scmPurOrderEntry.getItemId()), scmPurOrderEntry.getPurUnit(),
										scmPurRequire.getReqDate(),
										scmPurRequireEntryList.get(i).getRecStorageOrgUnitNo(), priceBillId, param);
							}
							if (scmMaterialPrice != null) {
								if (StringUtils.contains("6,7",scmPurRequireEntryList.get(i).getRefPriceStatus()) && 
                            			!StringUtils.contains("6,7",scmMaterialPrice.getRefPriceStatus())&&StringUtils.isNotBlank(scmPurRequireEntryList.get(i).getRefPriceStatus())) {
									// 临时定价单还未下达
									scmPurOrderEntry.setPrice(BigDecimal.ZERO);
									scmPurOrderEntry.setAmt(BigDecimal.ZERO);
									scmPurOrderEntry.setTaxPrice(BigDecimal.ZERO);
									scmPurOrderEntry.setTaxAmt(BigDecimal.ZERO);
									scmPurOrderEntry.setPriceBillId(scmPurRequireEntryList.get(i).getPriceBillId());
									scmPurOrderEntry.setRefPriceStatus(scmPurRequireEntryList.get(i).getRefPriceStatus());
								} else if (scmMaterialPrice.getPriceBillId()>0) {
									scmPurOrderEntry.setPrice(scmMaterialPrice.getPrice());
									scmPurOrderEntry.setAmt(scmPurOrderEntry.getQty().multiply(scmMaterialPrice.getPrice()));
									scmPurOrderEntry.setTaxRate(scmMaterialPrice.getTaxRate());
									scmPurOrderEntry.setTaxPrice(scmMaterialPrice.getTaxPrice());
									scmPurOrderEntry.setTaxAmt(scmPurOrderEntry.getQty().multiply(scmMaterialPrice.getTaxPrice()));
									scmPurOrderEntry.setPriceBillId(scmMaterialPrice.getPriceBillId());
									scmPurOrderEntry.setRefPriceStatus(scmMaterialPrice.getRefPriceStatus());
								}else {
									scmPurOrderEntry.setTaxRate(scmMaterialPrice.getTaxRate());
									scmPurOrderEntry.setTaxRate(scmMaterialPrice.getTaxRate());
									scmPurOrderEntry.setTaxPrice(scmPurRequireEntryList.get(i).getPrice());
									scmPurOrderEntry.setTaxAmt(scmPurRequireEntryList.get(i).getAmt());
									if (scmPurOrderEntry.getTaxRate().compareTo(BigDecimal.ZERO)==0) {
										scmPurOrderEntry.setPrice(scmPurRequireEntryList.get(i).getPrice());
										scmPurOrderEntry.setAmt(scmPurRequireEntryList.get(i).getAmt());
									}else {
										BigDecimal b = (scmPurOrderEntry.getTaxPrice()).divide((BigDecimal.ONE).add(scmPurOrderEntry.getTaxRate()),pricePrec,RoundingMode.HALF_UP);
		                                scmPurOrderEntry.setPrice(b);
		                                BigDecimal c = (scmPurOrderEntry.getTaxAmt()).divide((BigDecimal.ONE).add(scmPurOrderEntry.getTaxRate()),amtPrec,RoundingMode.HALF_UP);
		                                scmPurOrderEntry.setAmt(c);
									}
									scmPurOrderEntry.setRefPriceStatus("0");
								}
							} else {
								if (StringUtils.contains("6,7",scmPurRequireEntryList.get(i).getRefPriceStatus())) {
									// 临时定价单还未下达
									scmPurOrderEntry.setPrice(BigDecimal.ZERO);
									scmPurOrderEntry.setAmt(BigDecimal.ZERO);
									scmPurOrderEntry.setTaxPrice(BigDecimal.ZERO);
									scmPurOrderEntry.setTaxAmt(BigDecimal.ZERO);
									scmPurOrderEntry.setPriceBillId(scmPurRequireEntryList.get(i).getPriceBillId());
									scmPurOrderEntry.setRefPriceStatus(scmPurRequireEntryList.get(i).getRefPriceStatus());
								} else {
									scmPurOrderEntry.setPrice(scmPurRequireEntryList.get(i).getPrice());
									scmPurOrderEntry.setAmt(scmPurRequireEntryList.get(i).getAmt());
									scmPurOrderEntry.setTaxPrice(scmPurRequireEntryList.get(i).getPrice());
									scmPurOrderEntry.setTaxAmt(scmPurRequireEntryList.get(i).getAmt());
//									scmPurOrderEntry.setPriceBillId(scmPurRequireEntryList.get(i).getPriceBillId());
									scmPurOrderEntry.setRefPriceStatus("0");
								}
							}

                            scmPurOrderEntry.setReqOrgUnitNo(scmPurRequireEntryList.get(i).getOrgUnitNo());
                            scmPurOrderEntry
                                    .setReqStorageOrgUnitNo(scmPurRequireEntryList.get(i).getRecStorageOrgUnitNo());
                            scmPurOrderEntry.setReceiveOrgUnitNo(scmPurOrderEntry.getReqStorageOrgUnitNo());
                            if (!invToFinMap.containsKey(scmPurOrderEntry.getReqStorageOrgUnitNo())) {
                            	List<OrgCompany2> orgCompanyList = orgUnitRelationBiz.findToOrgUnitByType(
                                        OrgUnitRelationType.INVTOFIN, scmPurOrderEntry.getReqStorageOrgUnitNo(),
                                        false, null, param);
                                if (orgCompanyList == null || orgCompanyList.isEmpty()) {
                                    throw new AppException(
                                            "iscm.purchasemanage.purchasebusiness.ScmPurRequireBizImpl.error.notfin");
                                }
                                invToFinMap.put(scmPurOrderEntry.getReqStorageOrgUnitNo(),
                                        orgCompanyList.get(0).getOrgUnitNo());
                            }
                            scmPurOrderEntry
                                    .setReceiveWareHouseId(scmPurRequireEntryList.get(i).getReceiveWareHouseId());
                            scmPurOrderEntry
                                    .setReqFinOrgUnitNo(invToFinMap.get(scmPurOrderEntry.getReqStorageOrgUnitNo()));
                            scmPurOrderEntry.setReceiveFinOrgUnitNo(
                                    invToFinMap.get(scmPurOrderEntry.getReqStorageOrgUnitNo()));
                            scmPurOrderEntry.setInstead(scmPurRequireEntryList.get(i).isEntrusted());
                            scmPurOrderEntry
                                    .setMstorageOrgUnitNo(scmPurRequireEntryList.get(i).getMsRecStorageOrgUnitNo());
                            scmPurOrderEntry.setBalanceSupplierId(scmPurRequireEntry.getVendorId());
                            scmPurOrderEntry.setBuyerId(scmPurRequireEntryList.get(i).getBuyerId());
                            scmPurOrderEntry.setPurGroupId(scmPurRequireEntryList.get(i).getPurGroupId());
                            scmPurOrderEntry.setPrDtlId(scmPurRequireEntryList.get(i).getId());
                            scmPurOrderEntry.setPrNo(scmPurRequireEntryList.get(i).getPrNo());
                            scmPurOrderEntry.setRemarks(scmPurRequireEntryList.get(i).getRemarks());
                            scmPurOrderEntry.setRowStatus("I");
                            scmPurOrderEntryList.add(scmPurOrderEntry);
                        }
                    }
                    bean.setList2(scmPurOrderEntryList);
                    rtnList.add(scmPurOrder);
                    scmPurOrderBiz.add(bean, param);
                }
            }
            return rtnList;
        }
        return null;
    }

    @Override
    public CommonBean getDataForLeadInto(ScmPurRequireEntryAdvQuery scmPurRequireEntryAdvQuery, Param param)
            throws AppException {
        CommonBean bean = new CommonBean();
        if (scmPurRequireEntryAdvQuery != null) {
            // 1、先查询符合条件的请购单
            Page page = new Page();
            page.setModelClass(ScmPurRequire.class);
            page.setShowCount(Integer.MAX_VALUE);
            if (scmPurRequireEntryAdvQuery.getBegBillDate() != null
                    && scmPurRequireEntryAdvQuery.getEndBillDate() != null) {
                page.getParam().put(
                        (ClassUtils.getFinalModelSimpleName(ScmPurRequire.class) + "." + ScmPurRequire.FN_CREATEDATE),
                        new QueryParam(
                                (ClassUtils.getFinalModelSimpleName(ScmPurRequire.class) + "."
                                        + ScmPurRequire.FN_CREATEDATE),
                                QueryParam.QUERY_BETWEEN,
                                FormatUtils.fmtDate(scmPurRequireEntryAdvQuery.getBegBillDate()),
                                FormatUtils.fmtDate(scmPurRequireEntryAdvQuery.getEndBillDate())));
            } else if (scmPurRequireEntryAdvQuery.getBegBillDate() != null) {
                page.getParam().put(
                        (ClassUtils.getFinalModelSimpleName(ScmPurRequire.class) + "." + ScmPurRequire.FN_CREATEDATE),
                        new QueryParam(
                                (ClassUtils.getFinalModelSimpleName(ScmPurRequire.class) + "."
                                        + ScmPurRequire.FN_CREATEDATE),
                                QueryParam.QUERY_GE, FormatUtils.fmtDate(scmPurRequireEntryAdvQuery.getBegBillDate())));
            } else if (scmPurRequireEntryAdvQuery.getEndBillDate() != null) {
                page.getParam().put(
                        (ClassUtils.getFinalModelSimpleName(ScmPurRequire.class) + "." + ScmPurRequire.FN_CREATEDATE),
                        new QueryParam(
                                (ClassUtils.getFinalModelSimpleName(ScmPurRequire.class) + "."
                                        + ScmPurRequire.FN_CREATEDATE),
                                QueryParam.QUERY_LE, FormatUtils.fmtDate(scmPurRequireEntryAdvQuery.getEndBillDate())));
            }
            if (StringUtils.isNotBlank(scmPurRequireEntryAdvQuery.getEndPrNo())) {
                if (!((scmPurRequireEntryAdvQuery.getEndPrNo()).startsWith("PR")
                        && (scmPurRequireEntryAdvQuery.getEndPrNo()).length() >= 13)) {
                    return bean;
                }
                if (StringUtils.isNotBlank(scmPurRequireEntryAdvQuery.getBegPrNo())
                        && ((scmPurRequireEntryAdvQuery.getBegPrNo()).startsWith("PR")
                                && (scmPurRequireEntryAdvQuery.getBegPrNo()).length() >= 13)) {
                    page.getParam().put(
                            (ClassUtils.getFinalModelSimpleName(ScmPurRequire.class) + "." + ScmPurRequire.FN_PRNO),
                            new QueryParam(
                                    (ClassUtils.getFinalModelSimpleName(ScmPurRequire.class) + "."
                                            + ScmPurRequire.FN_PRNO),
                                    QueryParam.QUERY_BETWEEN, scmPurRequireEntryAdvQuery.getBegPrNo(),
                                    scmPurRequireEntryAdvQuery.getEndPrNo()));
                } else {
                    page.getParam().put(
                            (ClassUtils.getFinalModelSimpleName(ScmPurRequire.class) + "." + ScmPurRequire.FN_PRNO),
                            new QueryParam(
                                    (ClassUtils.getFinalModelSimpleName(ScmPurRequire.class) + "."
                                            + ScmPurRequire.FN_PRNO),
                                    QueryParam.QUERY_LE, scmPurRequireEntryAdvQuery.getEndPrNo()));
                }
            } else if (StringUtils.isNotBlank(scmPurRequireEntryAdvQuery.getBegPrNo())
                    && ((scmPurRequireEntryAdvQuery.getBegPrNo()).startsWith("PR")
                            && (scmPurRequireEntryAdvQuery.getBegPrNo()).length() >= 13)) {
                page.getParam().put(
                        (ClassUtils.getFinalModelSimpleName(ScmPurRequire.class) + "." + ScmPurRequire.FN_PRNO),
                        new QueryParam(
                                (ClassUtils.getFinalModelSimpleName(ScmPurRequire.class) + "." + ScmPurRequire.FN_PRNO),
                                QueryParam.QUERY_GE, scmPurRequireEntryAdvQuery.getBegPrNo()));
            }
            if (StringUtils.isNotBlank(scmPurRequireEntryAdvQuery.getOrgUnitNo())) {
                page.getParam().put(
                        (ClassUtils.getFinalModelSimpleName(ScmPurRequire.class) + "." + ScmPurRequire.FN_ORGUNITNO),
                        new QueryParam(
                                (ClassUtils.getFinalModelSimpleName(ScmPurRequire.class) + "."
                                        + ScmPurRequire.FN_ORGUNITNO),
                                QueryParam.QUERY_EQ, scmPurRequireEntryAdvQuery.getOrgUnitNo()));
            }
            List<ScmPurRequire> resultList = scmPurRequireBiz.findPage(page, param);
            List<ScmPurRequire2> scmPurRequireList = new ArrayList<>();
            StringBuffer prIdstr = new StringBuffer("");
            if (resultList != null && !resultList.isEmpty()) {
                for (ScmPurRequire scmPurRequire : resultList) {
                    ScmPurRequire2 scmPurRequire2 = new ScmPurRequire2(true);
                    BeanUtil.copyProperties(scmPurRequire2, scmPurRequire);
                    scmPurRequireList.add(scmPurRequire2);
                    if (StringUtils.isEmpty(prIdstr.toString())) {
                        prIdstr.append("'").append(scmPurRequire.getId()).append("'");
                    } else {
                        prIdstr.append(",").append("'").append(scmPurRequire.getId()).append("'");
                    }
                }
            }
            // 2、根据符合条件的请购单和原有条件查询请购明细
            if (StringUtils.isNotBlank(prIdstr.toString())) {
                Page page2 = new Page();
                page2.setModelClass(ScmPurRequireEntry2.class);
                page2.setShowCount(Integer.MAX_VALUE);
                page2.getParam()
                        .put((ClassUtils.getFinalModelSimpleName(ScmPurRequireEntry2.class) + "."
                                + ScmPurRequireEntry2.FN_ROWSTATUS),
                                new QueryParam((ClassUtils.getFinalModelSimpleName(ScmPurRequireEntry2.class) + "."
                                        + ScmPurRequireEntry2.FN_ROWSTATUS), QueryParam.QUERY_EQ, "E"));
                page2.getParam()
                        .put((ClassUtils.getFinalModelSimpleName(ScmPurRequireEntry2.class) + "."
                                + ScmPurRequireEntry2.FN_PRID),
                                new QueryParam(
                                        (ClassUtils.getFinalModelSimpleName(ScmPurRequireEntry2.class) + "."
                                                + ScmPurRequireEntry2.FN_PRID),
                                        QueryParam.QUERY_IN, prIdstr.toString()));
                if (scmPurRequireEntryAdvQuery.getBuyerId() > 0) {
                    page2.getParam()
                            .put((ClassUtils.getFinalModelSimpleName(ScmPurRequireEntry2.class) + "."
                                    + ScmPurRequireEntry2.FN_BUYERID),
                                    new QueryParam(
                                            (ClassUtils.getFinalModelSimpleName(ScmPurRequireEntry2.class) + "."
                                                    + ScmPurRequireEntry2.FN_BUYERID),
                                            QueryParam.QUERY_EQ,
                                            String.valueOf(scmPurRequireEntryAdvQuery.getBuyerId())));
                }
                if (scmPurRequireEntryAdvQuery.getVendorId() > 0) {
                    page2.getParam()
                            .put((ClassUtils.getFinalModelSimpleName(ScmPurRequireEntry2.class) + "."
                                    + ScmPurRequireEntry2.FN_VENDORID),
                                    new QueryParam(
                                            (ClassUtils.getFinalModelSimpleName(ScmPurRequireEntry2.class) + "."
                                                    + ScmPurRequireEntry2.FN_VENDORID),
                                            QueryParam.QUERY_EQ,
                                            String.valueOf(scmPurRequireEntryAdvQuery.getVendorId())));
                }
                if (scmPurRequireEntryAdvQuery.getItemId() > 0) {
                    page2.getParam()
                            .put((ClassUtils.getFinalModelSimpleName(ScmPurRequireEntry2.class) + "."
                                    + ScmPurRequireEntry2.FN_ITEMID),
                                    new QueryParam(
                                            (ClassUtils.getFinalModelSimpleName(ScmPurRequireEntry2.class) + "."
                                                    + ScmPurRequireEntry2.FN_ITEMID),
                                            QueryParam.QUERY_EQ,
                                            String.valueOf(scmPurRequireEntryAdvQuery.getItemId())));
                }
                List<ScmPurRequireEntry2> scmPurRequireEntryList = this.findPage(page2, param);
                List<Long> prIdList = new ArrayList<>();
                if (scmPurRequireEntryList != null && !scmPurRequireEntryList.isEmpty()) {
                    for (int i = scmPurRequireEntryList.size() - 1; i >= 0; i--) {
                        if (scmPurRequireEntryAdvQuery.getMaterialGroupId() > 0 && scmPurRequireEntryAdvQuery
                                .getMaterialGroupId() != scmPurRequireEntryList.get(i).getGroupId()) {
                            scmPurRequireEntryList.remove(i);
                            continue;
                        }
                        if (scmPurRequireEntryList.get(i).getPrId() > 0
                                && !prIdList.contains(scmPurRequireEntryList.get(i).getPrId())) {
                            prIdList.add(scmPurRequireEntryList.get(i).getPrId());
                        }
                    }
                }
                // 3、根据查询到的请购明细过滤请购单
                if (prIdList != null && !prIdList.isEmpty() && scmPurRequireList != null
                        && !scmPurRequireList.isEmpty()) {
                    for (int i = scmPurRequireList.size() - 1; i >= 0; i--) {
                        boolean flag = false;
                        for (int j = prIdList.size() - 1; j >= 0; j--) {
                            if (prIdList.get(j) == scmPurRequireList.get(i).getId()) {
                                flag = true;
                                break;
                            }
                        }
                        if (!flag) {
                            scmPurRequireList.remove(i);
                        }
                    }
                    if (scmPurRequireEntryList != null && !scmPurRequireEntryList.isEmpty() && scmPurRequireList != null
                            && !scmPurRequireList.isEmpty()) {
                        bean.setList(scmPurRequireList);
                        bean.setList2(scmPurRequireEntryList);
                        return bean;
                    }
                }
            }
        }
        return bean;
    }

    @Override
    public void writeBackByPurOrder(ScmPurOrderEntry2 oldEntity, ScmPurOrderEntry2 newEntity, Param param)
            throws AppException {
        BigDecimal qty = BigDecimal.ZERO;
        if (newEntity != null && newEntity.getPrDtlId() > 0) {
            qty = qty.add(newEntity.getBaseQty());
        }
        if (oldEntity != null && oldEntity.getPrDtlId() > 0) {
            qty = qty.subtract(oldEntity.getBaseQty());
        }
        if ((newEntity != null && newEntity.getPrDtlId() > 0) || (oldEntity != null && oldEntity.getPrDtlId() > 0)) {
            ScmPurRequireEntry2 scmPurRequireEntry = this
                    .selectDirect(newEntity != null ? newEntity.getPrDtlId() : oldEntity.getPrDtlId(), param);
            scmPurRequireEntry.setOrderQty(scmPurRequireEntry.getOrderQty().add(qty));
            if (scmPurRequireEntry.getOrderQty().compareTo(scmPurRequireEntry.getBaseQty()) >= 0) {
                scmPurRequireEntry.setRowStatus("C");
            } else {
                scmPurRequireEntry.setRowStatus("E");
            }
            this.updateDirect(scmPurRequireEntry, param);
            this.updateBillStatusByEntry(scmPurRequireEntry, param);
        }
    }

	@Override
	public List<ScmPurRequireEntry2> selectByBuyerId(HashMap<String, Object> map)
			throws AppException {
		return ((ScmPurRequireEntryDAO) dao).selectByBuyerId(map);
	}

	@Override
	public List<ScmPurRequireEntry2> autoAssign(
			List<ScmPurRequireEntry2> scmPurRequireEntryList, Param param)
			throws AppException {
		if (scmPurRequireEntryList != null && !scmPurRequireEntryList.isEmpty()) {
			for(ScmPurRequireEntry2 scmPurRequireEntry:scmPurRequireEntryList) {
				if (scmPurRequireEntry == null) {
					return null;
				}
				String purOrgUnitNo = scmPurRequireEntry.getPurOrgUnitNo();
				String invOrgUnitNo = scmPurRequireEntry.getRecStorageOrgUnitNo();
				Long itemId = scmPurRequireEntry.getItemId();
				ScmPurSupplyInfo2 scmPurSupplyInfo = scmPurSupplyInfoBiz.getSupplyInfoByItem(purOrgUnitNo, invOrgUnitNo, itemId,scmPurRequireEntry.getApplyDate(), param);
				if (scmPurSupplyInfo != null) {
					Scmsupplier2 scmsupplier = scmsupplierBiz.selectDirect(scmPurSupplyInfo.getVendorId(), param);
					if(scmsupplier!=null && StringUtils.equals("A",scmsupplier.getStatus())) {
					scmPurRequireEntry.setVendorId(scmPurSupplyInfo.getVendorId());
					} 
					
				}
			}
			this.update(scmPurRequireEntryList, param);
			return scmPurRequireEntryList;
		}
		return null;
	}

    @Override
    public void doAdvQuery(Page page, Param param) {
        if (page.getModel() != null) {
            if (page.getModel() instanceof ScmPurRequireEntryAdvQuery) {
                ScmPurRequireEntryAdvQuery scmPurRequireEntryAdvQuery = (ScmPurRequireEntryAdvQuery) page.getModel();
                if(StringUtils.isNotBlank(scmPurRequireEntryAdvQuery.getPrNo())){
                    page.getParam().put(ScmPurRequire2.FN_PRNO,new QueryParam(	ScmPurRequire2.FN_PRNO, QueryParam.QUERY_LIKE,"%"+scmPurRequireEntryAdvQuery.getPrNo()+"%"));
                }
                if(StringUtils.isNotBlank(scmPurRequireEntryAdvQuery.getFinOrgUnitNo())){
                    page.getParam().put(ScmPurRequire2.FN_FINORGUNITNO,new QueryParam(ScmPurRequire2.FN_FINORGUNITNO, QueryParam.QUERY_EQ,scmPurRequireEntryAdvQuery.getFinOrgUnitNo()));
                }
                if(scmPurRequireEntryAdvQuery.getReqDateFrom()!=null && scmPurRequireEntryAdvQuery.getReqDateTo()!=null){
                	//需求日期
                    page.getParam().put(ScmPurRequireEntry2.FN_REQDATE,
                            new QueryParam(ClassUtils.getFinalModelSimpleName(ScmPurRequireEntry2.class)+"."+ScmPurRequireEntry2.FN_REQDATE, 
                                    QueryParam.QUERY_BETWEEN,
                                    FormatUtils.fmtDate(scmPurRequireEntryAdvQuery.getReqDateFrom()),
                                    FormatUtils.fmtDate(scmPurRequireEntryAdvQuery.getReqDateTo())));
                }else if(scmPurRequireEntryAdvQuery.getBegBillDate()!=null && scmPurRequireEntryAdvQuery.getEndBillDate()!=null){
                	//制单日期
                    page.getParam().put(ScmPurRequire2.FN_CREATEDATE,
                            new QueryParam(ClassUtils.getFinalModelSimpleName(ScmPurRequire2.class)+"."+ScmPurRequire2.FN_CREATEDATE, 
                                    QueryParam.QUERY_BETWEEN,
                                    FormatUtils.fmtDate(scmPurRequireEntryAdvQuery.getBegBillDate()),
                                    FormatUtils.fmtDate(CalendarUtil.relativeDate(scmPurRequireEntryAdvQuery.getEndBillDate(),1))));
                }
                if(scmPurRequireEntryAdvQuery.getVendorId()>0) {
                	page.getParam().put(ScmPurRequireEntry2.FN_VENDORID,
                            new QueryParam(ClassUtils.getFinalModelSimpleName(ScmPurRequireEntry2.class)+"."+ScmPurRequireEntry2.FN_VENDORID, 
                                    QueryParam.QUERY_EQ,String.valueOf(scmPurRequireEntryAdvQuery.getVendorId())));
                }
                if(scmPurRequireEntryAdvQuery.getVendorClassId()>0) {
                	String sqlCondition = page.getSqlCondition();
                	if(StringUtils.isBlank(sqlCondition)) {
                		sqlCondition="scmpurrequireentry.vendorId in (Select vendorId from scmsuppliergroupdetail where classId="+scmPurRequireEntryAdvQuery.getVendorClassId()+")";
                	}else {
                		sqlCondition = sqlCondition+"and (scmpurrequireentry.vendorId in (Select vendorId from scmsuppliergroupdetail where classId="+scmPurRequireEntryAdvQuery.getVendorClassId()+"))";
                	}
                	page.setSqlCondition(sqlCondition);
                }
                if(scmPurRequireEntryAdvQuery.getItemId()>0) {
                	page.getParam().put(ScmPurRequireEntry2.FN_ITEMID,
                            new QueryParam(ClassUtils.getFinalModelSimpleName(ScmPurRequireEntry2.class)+"."+ScmPurRequireEntry2.FN_ITEMID, 
                                    QueryParam.QUERY_EQ,String.valueOf(scmPurRequireEntryAdvQuery.getItemId())));
                }
                if(scmPurRequireEntryAdvQuery.getBuyerId()>0) {
                	page.getParam().put(ScmPurRequireEntry2.FN_BUYERID,
                            new QueryParam(ClassUtils.getFinalModelSimpleName(ScmPurRequireEntry2.class)+"."+ScmPurRequireEntry2.FN_BUYERID, 
                                    QueryParam.QUERY_EQ,String.valueOf(scmPurRequireEntryAdvQuery.getBuyerId())));
                }
                if(StringUtils.isNotBlank(scmPurRequireEntryAdvQuery.getBizType())) {
                	page.getParam().put(ScmPurRequire2.FN_BIZTYPE,
                            new QueryParam(ClassUtils.getFinalModelSimpleName(ScmPurRequire2.class)+"."+ScmPurRequire2.FN_BIZTYPE, 
                                    QueryParam.QUERY_IN,String.valueOf(scmPurRequireEntryAdvQuery.getBizType())));
                }
                if (StringUtils.isNotBlank(scmPurRequireEntryAdvQuery.getOrgUnitNo())) {
                	page.getParam().put(ScmPurRequireEntry2.FN_ORGUNITNO,
                            new QueryParam(ClassUtils.getFinalModelSimpleName(ScmPurRequireEntry2.class)+"."+ScmPurRequireEntry2.FN_ORGUNITNO, 
                                    QueryParam.QUERY_EQ,scmPurRequireEntryAdvQuery.getOrgUnitNo()));
				}
                if (StringUtils.isNotBlank(scmPurRequireEntryAdvQuery.getPurOrgUnitNo())) {
                	page.getParam().put(ScmPurRequireEntry2.FN_PURORGUNITNO,
                            new QueryParam(ClassUtils.getFinalModelSimpleName(ScmPurRequireEntry2.class)+"."+ScmPurRequireEntry2.FN_PURORGUNITNO, 
                                    QueryParam.QUERY_EQ,scmPurRequireEntryAdvQuery.getPurOrgUnitNo()));
				}
            }
        }
    }

	@Override
	public List<String> cancelRefuse(List<ScmPurRequireEntry2> scmPurRequireEntryList, Param param) throws AppException {
		List<String> rntList = new ArrayList<>();
		scmPurRequireEntryList = this.select(scmPurRequireEntryList, param);
		if (scmPurRequireEntryList != null && !scmPurRequireEntryList.isEmpty()) {
			StringBuffer ids = new StringBuffer();
			for(ScmPurRequireEntry2 scmPurRequireEntry:scmPurRequireEntryList) {
				if (StringUtils.isNotBlank(ids.toString())) {
					ids.append(",");
				}
				ids.append(scmPurRequireEntry.getId());
				if(scmPurRequireEntry.getBaseQty().compareTo(scmPurRequireEntry.getOrderQty())>0) {
					if(StringUtils.equals("C",scmPurRequireEntry.getRowStatus())) {
						scmPurRequireEntry.setRowStatus("A");
					}
				}else {
//					rntList.add(e)
				}
			}
			scmPurREReuseBiz.cancelRefuse(ids.toString(),param);
			this.updateStatus(scmPurRequireEntryList, param);
		}
		return null;
	}
	
	@Override
    public void updateRowStatusByLineId(long prId, String status, String checker, Date checkDate, int lineId, Param param)
            throws AppException {
        if (prId > 0 && StringUtils.isNotBlank(status)) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("prId", prId);
            map.put("rowStatus", status);
            map.put("checker", checker);
            map.put("checkDate", checkDate);
            map.put("lineId", lineId);
            ((ScmPurRequireEntryDAO) dao).updateRowStatusByLineId(map);
        }
    }

	@Override
	public void doRelease(ScmPurRequire2 scmPurRequire, Param param) throws AppException {
		if(scmPurRequire!=null) {
			List<ScmPurRequireEntry2> scmPurRequireEntryList = this.selectByPrId(scmPurRequire.getId(), param);
			if(scmPurRequireEntryList!=null && !scmPurRequireEntryList.isEmpty()) {
				for(ScmPurRequireEntry2 scmPurRequireEntry:scmPurRequireEntryList) {
					if(StringUtils.equals("A", scmPurRequireEntry.getRowStatus())) {
						scmPurRequireEntry.setRowStatus("E");
						this.updateDirect(scmPurRequireEntry, param);
					}
				}
			}
		}
	}

	@Override
	public void doUndoRelease(ScmPurRequire2 scmPurRequire, Param param) throws AppException {
		if(scmPurRequire!=null) {
			List<ScmPurRequireEntry2> scmPurRequireEntryList = this.selectByPrId(scmPurRequire.getId(), param);
			if(scmPurRequireEntryList!=null && !scmPurRequireEntryList.isEmpty()) {
				for(ScmPurRequireEntry2 scmPurRequireEntry:scmPurRequireEntryList) {
					if(StringUtils.equals("E", scmPurRequireEntry.getRowStatus())) {
						scmPurRequireEntry.setRowStatus("A");
						this.updateDirect(scmPurRequireEntry, param);
					}
				}
			}
		}
	}

	@Override
	public List<ScmPurRequireEntry2> viewPurRequestStatus(
			ScmPurRequire2 scmPurRequire, Param param)
			throws AppException {
		HashMap<String, Object> map = new HashMap<>();
		map.put("prNo", scmPurRequire.getPrNo());
        map.put("prId", scmPurRequire.getId());
		List<ScmPurRequireEntry2> scmPurRequireEntryList = ((ScmPurRequireEntryDAO) dao).viewPurRequestStatus(map);
        if(scmPurRequireEntryList!=null && !scmPurRequireEntryList.isEmpty()) {
        	for(ScmPurRequireEntry2 scmPurRequireEntry2:scmPurRequireEntryList) {
        		this.setConvertMap(scmPurRequireEntry2, param);
        	}
        }
        return scmPurRequireEntryList;
	}
	
	private void updateGrantedStatus(ScmPurRequireEntry2 scmPurRequireEntry, Param param) {
		if(scmPurRequireEntry == null)
			throw new AppException("argument.null", new String[] { "apinvoice" });
		ScmPurRequireEntry2 oldEntity = this.selectDirect(scmPurRequireEntry.getId(), param);
		int i = ((ScmPurRequireEntryDAO) dao).updateGrantedStatus(scmPurRequireEntry);
		if(i != 1){
			if ("E".equals(scmPurRequireEntry.getRowStatus())) {
				throw new AppException("ScmPurRequireEntryBizImpl.updateGrantedStatus.error.granted");
			} else if ("A".equals(scmPurRequireEntry.getRowStatus())) {
				throw new AppException("ScmPurRequireEntryBizImpl.updateGrantedStatus.error.cancelGranted");
			} else if ("C".equals(scmPurRequireEntry.getRowStatus())) {
				throw new AppException("ScmPurRequireEntryBizImpl.updateGrantedStatus.error.refuse");
			} else {
				throw new AppException("ScmPurRequireEntryBizImpl.updateGrantedStatus.error.other");
			}
		}
		try {
			LogUtils.logEvent(scmPurRequireEntry, param);
			LogUtils.logModifyData(scmPurRequireEntry, oldEntity, param);
		} catch (Exception e) {
			log.error("记录操作日志失败", e);
		}
	}


	@Override
	public CommonBean generateTempPrice(
			List<ScmPurRequireEntry2> scmPurRequireEntryList, Param param)
			throws AppException {
		CommonBean bean = new CommonBean();
        List<ScmPurPrice2> scmPurPriceList = new ArrayList();
        ScmPurPrice2 scmPurPrice = new ScmPurPrice2(true);
        scmPurPrice.setBizType("2");
        scmPurPrice.setPmDate(CalendarUtil.getDate(param));
        scmPurPrice.setBegDate(null);
        scmPurPrice.setEndDate(null);
        scmPurPrice.setOrgUnitNo(scmPurRequireEntryList.get(0).getPurOrgUnitNo());
        scmPurPrice.setSelVndrId(0);
        scmPurPrice.setVendorId1(0);
        scmPurPrice.setVendorId2(0);
        scmPurPrice.setVendorId3(0);
        scmPurPrice.setGroupId1(0);
        scmPurPrice.setGroupId2(0);
        scmPurPrice.setGroupId3(0);
        scmPurPrice.setCurrencyNo("RMB");
        scmPurPrice.setCreator(param.getUsrCode());
        scmPurPrice.setCreateDate(CalendarUtil.getDate(param));
        scmPurPrice.setStatus("I");
        scmPurPrice.setRemarks("");
        scmPurPrice.setControlUnitNo(param.getControlUnitNo());
        
        int count = 0;
        HashMap<String, String> itemMap = new HashMap<>();
        List<ScmPurPriceEntry2> scmPurPriceEntryList = new ArrayList<ScmPurPriceEntry2>();
        for(ScmPurRequireEntry2 scmPurRequireEntry : scmPurRequireEntryList) {
        	long itemId = scmPurRequireEntry.getItemId();
        	if (itemMap.containsKey(String.valueOf(itemId))) {
        		int index = Integer.parseInt(itemMap.get(String.valueOf(itemId)));
        		String prNos = scmPurPriceEntryList.get(index).getPrNos();
        		String prNo = scmPurRequireEntry.getPrNo();
        		
        		if(StringUtils.isNotBlank(prNos)) {
        			prNos += "," + prNo;
        		} else {
        			prNos = prNo;
        		}
        	
        		scmPurPriceEntryList.get(index).setPrNos(prNos);
        	} else {
	            ScmPurPriceEntry2 scmPurPriceEntry = new ScmPurPriceEntry2(true);
	            scmPurPriceEntry.setItemId(scmPurRequireEntry.getItemId());
	            scmPurPriceEntry.setItemNo(scmPurRequireEntry.getItemNo());
	            scmPurPriceEntry.setItemName(scmPurRequireEntry.getItemName());
	            scmPurPriceEntry.setSpec(scmPurRequireEntry.getSpec());
	            scmPurPriceEntry.setPurUnit(scmPurRequireEntry.getPurUnit());
	            scmPurPriceEntry.setPurUnitName(scmPurRequireEntry.getPurUnitName());
	            scmPurPriceEntry.setSelVndrId(0);
	            scmPurPriceEntry.setRowStatus("I");
	            scmPurPriceEntry.setRemarks(scmPurRequireEntry.getRemarks());
	            scmPurPriceEntry.setPrNos(scmPurRequireEntry.getPrNo());
	            scmPurPriceEntryList.add(scmPurPriceEntry);
	            itemMap.put(String.valueOf(itemId), String.valueOf(count));
	            count++;
        	}
        }
        
        scmPurPrice.setScmPurPriceEntryList(scmPurPriceEntryList);
        scmPurPriceList.add(scmPurPrice);
        bean.setList(scmPurPriceList);
        bean.setList2(scmPurPriceEntryList);
        /*bean = scmPurPriceBiz.add(bean, param);
        
        for(ScmPurRequireEntry2 scmPurRequireEntry : scmPurRequireEntryList) {
        	scmPurRequireEntry.setPriceBillId(scmPurPrice.getId());
        	scmPurRequireEntry.setRefPriceStatus("6");  	//临时定价
        	this.updateDirect(scmPurRequireEntry, param);
        }*/

        return bean;
	}

	@Override
	public void updatePurRequestByPmId(ScmPurPriceEntry2 scmPurPriceEntry,
			Param param) throws AppException {
		if (scmPurPriceEntry != null) {
            HashMap<String, Object> map = new HashMap<>();
            BigDecimal price = scmPurPriceEntry.getPrice();
            String priceType =sysParamBiz.getValue(param.getOrgUnitNo(), "SCM_PriceType", "1", param);
    		int pricePrec = Integer.parseInt(sysParamBiz.getValue(param.getOrgUnitNo(), "SCM_PricePrecision", "2", param));
    		if (StringUtils.equals(priceType, "2")) {
    			price = price.multiply(scmPurPriceEntry.getTaxRate().add(BigDecimal.ONE)).setScale(pricePrec,RoundingMode.HALF_UP);
    		}    
    		if (scmPurPriceEntry.isUndoRelease()) {
    			//取消下达清空价格
    			map.put("price", BigDecimal.ZERO);
                map.put("vendorId", 0);
    		} else {
    			map.put("price", price);
                map.put("vendorId", scmPurPriceEntry.getSelVndrId());
    		}
            
            map.put("pmId", scmPurPriceEntry.getPmId());
            String prNos = scmPurPriceEntry.getPrNos();
            
            if (StringUtils.isNotBlank(prNos)) {
	            StringBuffer prNo = new StringBuffer("");
	            if (StringUtils.isNotBlank(prNos)) {
		            String str[] = prNos.split(",");
		            List<String> prNosList = Arrays.asList(str);
		            if (prNosList != null && !prNosList.isEmpty()) {
		            	for(String prNoTemp :prNosList) {
		                    if(StringUtils.isNotBlank(prNo.toString()))
		                    	prNo.append(",");
		                    prNo.append("'").append(prNoTemp).append("'");
		            	}
		            }  
					
		            map.put("prNos", prNo.toString());
		            map.put("itemId", scmPurPriceEntry.getItemId());
		            ((ScmPurRequireEntryDAO) dao).updatePurRequestByPmId(map);
	            }
            }
        }
	}
	
	@Override
	public void updatePurRequestPriceBillIdByPmId(ScmPurPriceEntry2 scmPurPriceEntry,
			Param param) throws AppException {
		if (scmPurPriceEntry != null) {
			HashMap<String, Object> map = new HashMap<>();
			map.put("pmId", scmPurPriceEntry.getPmId());
			String prNos = scmPurPriceEntry.getPrNos();
			
			StringBuffer prNo = new StringBuffer("");
			if (StringUtils.isNotBlank(prNos)) {
				String str[] = prNos.split(",");
	            List<String> prNosList = Arrays.asList(str);
	            if (prNosList != null && !prNosList.isEmpty()) {
	            	for(String prNoTemp :prNosList) {
	                    if(StringUtils.isNotBlank(prNo.toString()))
	                    	prNo.append(",");
	                    prNo.append("'").append(prNoTemp).append("'");
	            	}
	            } 
	            
	            map.put("prNos", prNo.toString());
	            map.put("itemId", scmPurPriceEntry.getItemId());
	            ((ScmPurRequireEntryDAO) dao).updatePurRequestPriceBillIdByPmId(map);
			}
		}
	}

	@Override
	public List<ScmPurRequireEntry2> selectByPrId2(long prId, Param param) throws AppException {
		if (prId > 0) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("prId", prId);
            return ((ScmPurRequireEntryDAO) dao).selectByPrId2(map);
		}
		return null;
	}

	@Override
	public List<ScmPurRequireEntry2> undoReleaseCheck(HashMap<Object, Object> map, Param param) throws AppException {
		if (map !=null) {
			return ((ScmPurRequireEntryDAO) dao).undoReleaseCheck(map);
		}
		return null;
	}


	@Override
	public List<ScmPurRequireEntry2> clearPurRequirePrice(HashMap<Object, Object> map, Param param) throws AppException {
		if (map !=null) {
			return ((ScmPurRequireEntryDAO) dao).clearPurRequirePrice(map);
		}
		return null;
	}

	@Override
	public List selectByPurOrgUnitNo(String object, Param createParam) throws AppException {
		if (StringUtils.isNotEmpty(object)) {
			HashMap<String, Object> map = new HashMap<>();
			map.put("purOrgUnitNo", object);
			map.put("orderQty", "Y");
			String unifiedOrDirect = sysParamBiz.getValue(createParam.getOrgUnitNo(), "SCM_UnifiedOrDirect", "U",createParam);
			map.put("unifiedOrDirect", unifiedOrDirect);
			return ((ScmPurRequireEntryDAO) dao).selectByPurOrgUnitNo(map);
		}
		return null;
	}

	@Override
	public List<ScmPurRequireEntry2> selectByPrIds(String prids, Param createParam) throws AppException {
		if (StringUtils.isNotEmpty(prids)) {
			HashMap<String, Object> map = new HashMap<>();
			map.put("prIds", prids);
			return ((ScmPurRequireEntryDAO) dao).selectByPrIds(map);
		}
		return null;
	}

	@Override
	public List<ScmPurRequireEntry2> refuse(List<ScmPurRequireEntry2> scmPurRequireEntryList, Param createParam)
			throws AppException {
		List<ScmPurRequireEntry2> updateStatus = new ArrayList<>();
		List<ScmPurREReuse> scmPurREReuseList = new ArrayList<>();
		if (scmPurRequireEntryList != null && !scmPurRequireEntryList.isEmpty()) {
			updateStatus = this.updateStatus(scmPurRequireEntryList, createParam);
			StringBuffer stringBuffer = new StringBuffer();
			List<ScmPurRequireEntry2> scmPurRequireEntryListGroupByPrid = scmPurRequireEntryList.stream().filter(distinctByKey(ScmPurRequireEntry2 :: getPrId)).collect(Collectors.toList());
			if (scmPurRequireEntryListGroupByPrid != null && !scmPurRequireEntryListGroupByPrid.isEmpty()) {
				for (ScmPurRequireEntry2 scmPurRequireEntry2 : scmPurRequireEntryListGroupByPrid) {
					if (StringUtils.isNotBlank(stringBuffer.toString())) {
						stringBuffer.append(",");
					}
					stringBuffer.append(scmPurRequireEntry2.getPrId());
				}
			}
			for (ScmPurRequireEntry2 scmPurRequireEntry2 : scmPurRequireEntryList) {
				ScmPurREReuse scmPurREReuse = new ScmPurREReuse();
				scmPurREReuse.setOpinion(scmPurRequireEntry2.getRefuseReason());
				scmPurREReuse.setEntryBillId(scmPurRequireEntry2.getId());
				scmPurREReuse.setOper(createParam.getUsrCode());
				scmPurREReuse.setOperDate(new Date());
				scmPurREReuse.setFlag(true);
				scmPurREReuse.setRowStatus(scmPurRequireEntry2.getRowStatus());
				scmPurREReuseList.add(scmPurREReuse);
			}
			scmPurREReuseBiz.add(scmPurREReuseList, createParam);
			List<ScmPurRequire2> scmPurRequire2s = scmPurRequireBiz.selectByIds(stringBuffer.toString(), createParam);
			if (scmPurRequire2s != null && !scmPurRequire2s.isEmpty()) {
				for (ScmPurRequire2 require : scmPurRequire2s) {
					BillType2 billType = billTypeBiz.selectByOrgAndCode(require.getFinOrgUnitNo(), "PurRequire",createParam);
					PurRequireParams purRequireParams = new PurRequireParams();
					purRequireParams.setPrNo(require.getPrNo());
					AuditMsgUtil.sendAuditMsg(billType.getBillCode(), "M", require, purRequireParams,require.getCreator(), createParam);
				}
			}
		}
		return updateStatus;
	}
	
	protected void afterAdd(ScmPurRequireEntry2 entity, Param param) throws AppException {
		String requireIdleItems = sysParamBiz.getValue(param.getControlUnitNo(), "SCM_requireIdleItems", "Y", param);
		if (StringUtils.equals("Y", requireIdleItems) && entity != null && entity.isIdle()) {
			ScmPurREReuse scmPurREReuse = new ScmPurREReuse();
			scmPurREReuse.setOpinion(entity.getRefuseReason());
			scmPurREReuse.setEntryBillId(entity.getId());
			scmPurREReuse.setOper(param.getUsrCode());
			scmPurREReuse.setOperDate(new Date());
			scmPurREReuse.setFlag(true);
			scmPurREReuse.setRowStatus(entity.getRowStatus());
			scmPurREReuseBiz.add(scmPurREReuse,param);
		}
	}
	
	protected void afterUpdate(ScmPurRequireEntry2 oldEntity, ScmPurRequireEntry2 newEntity, Param param)throws AppException {
		ScmPurREReuse scmPurREReuse = scmPurREReuseBiz.selectByEntryId(newEntity.getId());
		if (newEntity.isIdle()) {
			if (scmPurREReuse != null) {
				scmPurREReuse.setOpinion(newEntity.getRefuseReason());
				scmPurREReuseBiz.update(scmPurREReuse, param);
			}else {
				scmPurREReuse = new ScmPurREReuse();
				scmPurREReuse.setOpinion(newEntity.getRefuseReason());
				scmPurREReuse.setEntryBillId(newEntity.getId());
				scmPurREReuse.setOper(param.getUsrCode());
				scmPurREReuse.setOperDate(new Date());
				scmPurREReuse.setRowStatus(newEntity.getRowStatus());
				scmPurREReuse.setFlag(true);
				scmPurREReuseBiz.add(scmPurREReuse,param);
			}
		}else {
			if (oldEntity.isIdle()) {
				scmPurREReuseBiz.delete(scmPurREReuse, param);
			}
		}
	}
	
	@Override
	protected void afterDelete(ScmPurRequireEntry2 entity, Param param) throws AppException {
		if(entity != null && entity.getId() > 0){
			scmPurREReuseBiz.deleteByEntryId(entity.getId(), param);
		}
	}
	
	private <T> Predicate<? super BaseModel> distinctByKey(Function<? super T, ?> keyExtractor) {
        Map<Object,Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply((T) t), Boolean.TRUE) == null;
    }

	@Override
	public List<ScmPurRequireEntry2> selectAutoOrderListByPrId(long prId, Param param) throws AppException {
		HashMap<String, Object> map = new HashMap<>();
		map.put("prId", prId);
		return ((ScmPurRequireEntryDAO)dao).selectAutoOrderListByPrId(map);
	}

	
}

	
