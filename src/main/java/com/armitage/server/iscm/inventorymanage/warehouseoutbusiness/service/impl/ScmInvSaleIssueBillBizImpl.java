package com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;

import com.armitage.server.api.business.invSaleIssueBill.params.InvSaleIssueBillAddParams;
import com.armitage.server.api.business.invSaleIssueBill.params.InvSaleIssueBillDetailParams;
import com.armitage.server.api.business.invSaleIssueBill.params.InvSaleIssueReturnBillAddParams;
import com.armitage.server.api.business.invsaleorder.params.InvSaleOrderParams;
import com.armitage.server.activity.util.ActivityUtil;
import com.armitage.server.activity.util.AuditMsgUtil;
import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.CommonBean;
import com.armitage.server.common.base.model.OperationParam;
import com.armitage.server.common.base.model.Page;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.base.model.Param.ParamType;
import com.armitage.server.common.base.model.QueryParam;
import com.armitage.server.common.base.model.SimpleDataDisplay;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.util.BeanUtil;
import com.armitage.server.common.util.CalendarUtil;
import com.armitage.server.common.util.ClassUtils;
import com.armitage.server.common.util.FormatUtils;
import com.armitage.server.common.util.ListSortUtil;
import com.armitage.server.common.util.LogUtils;
import com.armitage.server.common.util.SimpleDataDisplayUtil;
import com.armitage.server.iars.basedata.model.Customer2;
import com.armitage.server.iars.basedata.model.PeriodCalendar;
import com.armitage.server.iars.basedata.service.CustomerBiz;
import com.armitage.server.iars.basedata.service.PeriodCalendarBiz;
import com.armitage.server.iars.daily.model.Arinvoice2;
import com.armitage.server.iars.daily.model.SaleIssueBill;
import com.armitage.server.iars.daily.model.SaleIssueBillEntry;
import com.armitage.server.iars.daily.service.ArinvoiceBiz;
import com.armitage.server.icrm.profile.model.Salesman2;
import com.armitage.server.icrm.profile.service.SalesmanBiz;
import com.armitage.server.iscm.basedata.model.ScmBillPending;
import com.armitage.server.iscm.basedata.model.ScmBillPending2;
import com.armitage.server.iscm.basedata.model.ScmMaterial2;
import com.armitage.server.iscm.basedata.model.ScmMaterialGroup;
import com.armitage.server.iscm.basedata.service.ScmBillPendingBiz;
import com.armitage.server.iscm.basedata.service.ScmMaterialBiz;
import com.armitage.server.iscm.basedata.service.ScmMaterialGroupBiz;
import com.armitage.server.iscm.basedata.util.ScmMaterialUtil;
import com.armitage.server.iscm.common.model.CommonAuditOpinion;
import com.armitage.server.iscm.common.model.CommonEventHistory;
import com.armitage.server.iscm.common.model.ScmDataCollectionStepState;
import com.armitage.server.iscm.common.model.ScmDataCollectionStepState2;
import com.armitage.server.iscm.common.service.CommonEventHistoryBiz;
import com.armitage.server.iscm.common.service.ScmDataCollectionStepStateBiz;
import com.armitage.server.iscm.inventorymanage.internaltrans.model.ScmInvSaleOrder;
import com.armitage.server.iscm.inventorymanage.internaltrans.model.ScmInvSaleOrder2;
import com.armitage.server.iscm.inventorymanage.internaltrans.service.ScmInvSaleOrderBiz;
import com.armitage.server.iscm.inventorymanage.inventorydata.model.ScmInvStock2;
import com.armitage.server.iscm.inventorymanage.inventorydata.service.ScmInvBalBiz;
import com.armitage.server.iscm.inventorymanage.inventorydata.service.ScmInvStockBiz;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvWareHouse;
import com.armitage.server.iscm.inventorymanage.inventorysetting.service.ScmInvWareHouseBiz;
import com.armitage.server.iscm.inventorymanage.util.ScmWarehouseUtil;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvPurInWarehsBill2;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.service.ScmInvPurInWarehsBillBiz;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.dao.ScmInvSaleIssueBillDAO;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvSaleIssueBill;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvSaleIssueBill2;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvSaleIssueBillAdvQuery;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvSaleIssueBillEntry2;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.service.ScmInvSaleIssueBillBiz;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.service.ScmInvSaleIssueBillEntryBiz;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.service.ScmInvSalePriceBiz;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmMaterialPrice;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.CommonAuditParams;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.service.ScmPurReceiveBiz;
import com.armitage.server.system.model.BillType2;
import com.armitage.server.system.model.Code;
import com.armitage.server.system.model.OrgBaseUnit;
import com.armitage.server.system.model.OrgCompany2;
import com.armitage.server.system.model.OrgStorage2;
import com.armitage.server.system.model.SystemState;
import com.armitage.server.system.service.BillTypeBiz;
import com.armitage.server.system.service.CodeBiz;
import com.armitage.server.system.service.OrgCompanyBiz;
import com.armitage.server.system.service.OrgStorageBiz;
import com.armitage.server.system.service.OrgUnitBiz;
import com.armitage.server.system.service.OrgUnitRelationBiz;
import com.armitage.server.system.service.SysParamBiz;
import com.armitage.server.system.service.SystemStateBiz;
import com.armitage.server.system.util.CodeAutoCalUtil;
import com.armitage.server.system.util.OrgUnitRelationType;
import com.armitage.server.user.model.Usr;
import com.armitage.server.user.service.UsrBiz;
import org.springframework.stereotype.Service;

@Service("scmInvSaleIssueBillBiz")
public class ScmInvSaleIssueBillBizImpl extends BaseBizImpl<ScmInvSaleIssueBill2> implements ScmInvSaleIssueBillBiz {

    private UsrBiz usrBiz;
    private ScmInvSaleIssueBillEntryBiz scmInvSaleIssueBillEntryBiz;
    private OrgUnitBiz orgUnitBiz;
    private CustomerBiz customerBiz;
    private ScmInvStockBiz scmInvStockBiz;
    private ScmInvBalBiz scmInvBalBiz;
    private BillTypeBiz billTypeBiz;
    private ScmMaterialBiz scmMaterialBiz;
    private ScmPurReceiveBiz scmPurReceiveBiz;
    private PeriodCalendarBiz periodCalendarBiz;
    private SystemStateBiz systemStateBiz;
	private SysParamBiz sysParamBiz;
    private ScmInvPurInWarehsBillBiz scmInvPurInWarehsBillBiz;
    private ScmInvSaleOrderBiz scmInvSaleOrderBiz;
    private OrgStorageBiz orgStorageBiz;
    private OrgCompanyBiz orgCompanyBiz;
    private ScmInvWareHouseBiz scmInvWareHouseBiz;
    private ScmInvSalePriceBiz scmInvSalePriceBiz;
    private CodeBiz codeBiz;
    private ScmDataCollectionStepStateBiz scmDataCollectionStepStateBiz;
    private ArinvoiceBiz arinvoiceBiz;
    private SalesmanBiz salesmanBiz;
    private ScmMaterialGroupBiz scmMaterialGroupBiz;
	private ScmBillPendingBiz scmBillPendingBiz;
    private CommonEventHistoryBiz commonEventHistoryBiz;
    private OrgUnitRelationBiz orgUnitRelationBiz;
    
    public void setOrgCompanyBiz(OrgCompanyBiz orgCompanyBiz) {
		this.orgCompanyBiz = orgCompanyBiz;
	}

	public void setScmInvWareHouseBiz(ScmInvWareHouseBiz scmInvWareHouseBiz) {
		this.scmInvWareHouseBiz = scmInvWareHouseBiz;
	}

	public void setScmInvSalePriceBiz(ScmInvSalePriceBiz scmInvSalePriceBiz) {
		this.scmInvSalePriceBiz = scmInvSalePriceBiz;
	}

	public void setCodeBiz(CodeBiz codeBiz) {
		this.codeBiz = codeBiz;
	}

	public void setUsrBiz(UsrBiz usrBiz) {
        this.usrBiz = usrBiz;
    }

    public void setScmInvSaleIssueBillEntryBiz(ScmInvSaleIssueBillEntryBiz scmInvSaleIssueBillEntryBiz) {
        this.scmInvSaleIssueBillEntryBiz = scmInvSaleIssueBillEntryBiz;
    }

    public void setOrgUnitBiz(OrgUnitBiz orgUnitBiz) {
        this.orgUnitBiz = orgUnitBiz;
    }

    public void setCustomerBiz(CustomerBiz customerBiz) {
        this.customerBiz = customerBiz;
    }

    public void setScmInvStockBiz(ScmInvStockBiz scmInvStockBiz) {
        this.scmInvStockBiz = scmInvStockBiz;
    }

    public void setScmInvBalBiz(ScmInvBalBiz scmInvBalBiz) {
        this.scmInvBalBiz = scmInvBalBiz;
    }

    public void setBillTypeBiz(BillTypeBiz billTypeBiz) {
		this.billTypeBiz = billTypeBiz;
	}

	public void setScmMaterialBiz(ScmMaterialBiz scmMaterialBiz) {
        this.scmMaterialBiz = scmMaterialBiz;
    }

    public void setScmPurReceiveBiz(ScmPurReceiveBiz scmPurReceiveBiz) {
		this.scmPurReceiveBiz = scmPurReceiveBiz;
	}

	public void setPeriodCalendarBiz(PeriodCalendarBiz periodCalendarBiz) {
		this.periodCalendarBiz = periodCalendarBiz;
	}

	public void setSystemStateBiz(SystemStateBiz systemStateBiz) {
        this.systemStateBiz = systemStateBiz;
    }

    public void setSysParamBiz(SysParamBiz sysParamBiz) {
		this.sysParamBiz = sysParamBiz;
	}

	public void setScmInvPurInWarehsBillBiz(ScmInvPurInWarehsBillBiz scmInvPurInWarehsBillBiz) {
		this.scmInvPurInWarehsBillBiz = scmInvPurInWarehsBillBiz;
	}

	public void setScmInvSaleOrderBiz(ScmInvSaleOrderBiz scmInvSaleOrderBiz) {
		this.scmInvSaleOrderBiz = scmInvSaleOrderBiz;
	}
	
	public void setOrgStorageBiz(OrgStorageBiz orgStorageBiz) {
		this.orgStorageBiz = orgStorageBiz;
	}

	public void setScmDataCollectionStepStateBiz(ScmDataCollectionStepStateBiz scmDataCollectionStepStateBiz) {
		this.scmDataCollectionStepStateBiz = scmDataCollectionStepStateBiz;
	}

	public void setArinvoiceBiz(ArinvoiceBiz arinvoiceBiz) {
		this.arinvoiceBiz = arinvoiceBiz;
	}

	public void setSalesmanBiz(SalesmanBiz salesmanBiz) {
		this.salesmanBiz = salesmanBiz;
	}

	public void setScmMaterialGroupBiz(ScmMaterialGroupBiz scmMaterialGroupBiz) {
		this.scmMaterialGroupBiz = scmMaterialGroupBiz;
	}

	public void setScmBillPendingBiz(ScmBillPendingBiz scmBillPendingBiz) {
		this.scmBillPendingBiz = scmBillPendingBiz;
	}

	public void setCommonEventHistoryBiz(CommonEventHistoryBiz commonEventHistoryBiz) {
		this.commonEventHistoryBiz = commonEventHistoryBiz;
	}

	public void setOrgUnitRelationBiz(OrgUnitRelationBiz orgUnitRelationBiz) {
		this.orgUnitRelationBiz = orgUnitRelationBiz;
	}

	@Override
    protected void beforeFindPage(Page page, Param param) throws AppException {
		List<OrgStorage2> orgStorageList = orgStorageBiz.findChild(param.getOrgUnitNo(), param);
		if (orgStorageList != null && !orgStorageList.isEmpty()) {
			StringBuffer orgunitList = new StringBuffer("");
			for (OrgStorage2 orgStorage : orgStorageList) {
				if(StringUtils.isNotBlank(orgunitList.toString()))
					orgunitList.append(",");
				orgunitList.append("'").append(orgStorage.getOrgUnitNo()).append("'");
			}
			page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBill2.class) + "." + ScmInvSaleIssueBill2.FN_ORGUNITNO), 
	                new QueryParam((ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBill2.class) + "." + ScmInvSaleIssueBill2.FN_ORGUNITNO), QueryParam.QUERY_IN, orgunitList.toString()));
		}else{
			page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBill2.class) + "." + ScmInvSaleIssueBill2.FN_ORGUNITNO), 
	                new QueryParam((ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBill2.class) + "." + ScmInvSaleIssueBill2.FN_ORGUNITNO), QueryParam.QUERY_EQ, "0"));
		}
	}

    @Override
    protected void afterSelect(ScmInvSaleIssueBill2 entity, Param param) throws AppException {
        if (entity != null){
        	setConvertMap(entity,param);
        }
    }

    @Override
    protected void afterFindPage(List list, Page page, Param param) throws AppException {
        if(list != null && !list.isEmpty()){
            for(ScmInvSaleIssueBill2 scmInvSaleIssueBill : (List<ScmInvSaleIssueBill2>)list){
            	//视图增加待审人
				if(!("I,R".contains(scmInvSaleIssueBill.getStatus()))) {
					ScmBillPending2 scmBillPendingUsr = scmBillPendingBiz.selectPendingUsr(scmInvSaleIssueBill.getOtId(), "InvSaleIssueBill",param);
					StringBuffer usrName = new StringBuffer("");
					if (scmBillPendingUsr != null) {
						scmInvSaleIssueBill.setPendingUsr(scmBillPendingUsr.getUsrCodes());
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
					scmInvSaleIssueBill.setPendingUsrName(usrName.toString());
				}
            	setConvertMap(scmInvSaleIssueBill,param);
            }
        }
    }

    private void setConvertMap(ScmInvSaleIssueBill2 scmInvSaleIssueBill,Param param) {
    	if (scmInvSaleIssueBill != null){
            if (StringUtils.isNotBlank(scmInvSaleIssueBill.getCreator())){
                //制单人
                Usr usr = usrBiz.selectByCode(scmInvSaleIssueBill.getCreator(), param);
                if (usr != null) {
                	if(scmInvSaleIssueBill.getDataDisplayMap()==null){
                        scmInvSaleIssueBill.setDataDisplayMap(new HashMap<String,SimpleDataDisplay>());
                    }
                	scmInvSaleIssueBill.setCreatorName(usr.getName());
                    scmInvSaleIssueBill.getDataDisplayMap().put(ScmInvSaleIssueBill2.FN_CREATOR, SimpleDataDisplayUtil.convert(usr));
                    scmInvSaleIssueBill.setConvertMap(ScmInvSaleIssueBill.FN_CREATOR, usr);
                }
            }
            
            if (StringUtils.isNotBlank(scmInvSaleIssueBill.getEditor())){
                //修改人
                Usr usr = usrBiz.selectByCode(scmInvSaleIssueBill.getEditor(), param);
                if (usr != null) {
                    scmInvSaleIssueBill.setConvertMap(ScmInvSaleIssueBill.FN_EDITOR, usr);
                }
            }
            
            if (StringUtils.isNotBlank(scmInvSaleIssueBill.getChecker())){
                //审核人
                Usr usr = usrBiz.selectByCode(scmInvSaleIssueBill.getChecker(), param);
                if (usr != null) {
                    scmInvSaleIssueBill.setConvertMap(ScmInvSaleIssueBill.FN_CHECKER, usr);
                }
            }
            if (StringUtils.isNotBlank(scmInvSaleIssueBill.getCreateOrgUnitNo())){
                //制单组织
                OrgBaseUnit orgBaseUnit = orgUnitBiz.selectbyOrgNo(scmInvSaleIssueBill.getCreateOrgUnitNo(), param);
                if (orgBaseUnit != null) {
                    scmInvSaleIssueBill.setConvertMap(ScmInvSaleIssueBill2.FN_CREATEORGUNITNO, orgBaseUnit);
                }
            }
            if (scmInvSaleIssueBill.getCustId() > 0){
                //客户
                Customer2 customer = customerBiz.selectDirect(scmInvSaleIssueBill.getCustId(), param);
                if (customer != null) {
                    scmInvSaleIssueBill.setConvertMap(ScmInvSaleIssueBill2.FN_CUSTID, customer);
                }
            }
            if (StringUtils.isNotBlank(scmInvSaleIssueBill.getOrgUnitNo())){
                //库存组织
                OrgBaseUnit orgBaseUnit = orgUnitBiz.selectbyOrgNo(scmInvSaleIssueBill.getOrgUnitNo(), param);
                if (orgBaseUnit != null) {
                	scmInvSaleIssueBill.setOrgUnitName(orgBaseUnit.getOrgUnitName());
                    scmInvSaleIssueBill.setConvertMap(ScmInvSaleIssueBill2.FN_ORGUNITNO, orgBaseUnit);
                }
            }
            if (scmInvSaleIssueBill.getSalesId()>0) {
            	Salesman2 salesman2 = salesmanBiz.select(scmInvSaleIssueBill.getSalesId(), param);
            	if (salesman2 != null) {
            		scmInvSaleIssueBill.setConvertMap(ScmInvSaleIssueBill2.FN_SALESID, salesman2);
				}
			}
            if(StringUtils.isNotBlank(scmInvSaleIssueBill.getStatus())){
				Code code = codeBiz.selectByCategoryAndCode("warehouseStatus", scmInvSaleIssueBill.getStatus());
				if(code!=null)
					scmInvSaleIssueBill.setStatusName(code.getName());
			}
            if(StringUtils.isNotBlank(scmInvSaleIssueBill.getBizType())){
				Code code = codeBiz.selectByCategoryAndCode("saleOWBizType", scmInvSaleIssueBill.getBizType());
				if(code!=null)
					scmInvSaleIssueBill.setBizTypeName(code.getName());
			}
            scmInvSaleIssueBill.setNetAmt(scmInvSaleIssueBill.getTaxAmt().subtract(scmInvSaleIssueBill.getAmt()));
        }
    }
    @Override
    protected void afterSelect(CommonBean bean, Param param) throws AppException {
        ScmInvSaleIssueBill2 scmInvSaleIssueBill = (ScmInvSaleIssueBill2) bean.getList().get(0);
        if(scmInvSaleIssueBill != null && scmInvSaleIssueBill.getOtId() > 0){
            bean.setList2(scmInvSaleIssueBillEntryBiz.selectByOtId(scmInvSaleIssueBill.getOtId(), param));
        }
    }

    @Override
    protected void beforeAdd(ScmInvSaleIssueBill2 entity, Param param) throws AppException {
        if(entity != null){
        	String code = CodeAutoCalUtil.getBillCode("InvSaleIssueBill", entity, param);
			entity.setOtNo(code);
          //获取期间
			PeriodCalendar periodCalendar = periodCalendarBiz.selectByBizdate(entity.getBizDate(), param);
			if(periodCalendar==null){
				throw new AppException("com.armitage.server.iars.daily.selectByBizdate.error");
			}
			if(entity.getCustId()>0) {
				Customer2 customer = customerBiz.select(entity.getCustId(), param);
				if(customer!= null) {
					entity.setCustomerRole(customer.getRole());
				}
			}
			entity.setPeriodId(periodCalendar.getPeriodId());
			entity.setAccountYear(periodCalendar.getAccountYear());
			entity.setAccountPeriod(periodCalendar.getAccountPeriod());
        }
    }

    @Override
	protected void beforeUpdate(ScmInvSaleIssueBill2 oldEntity,
			ScmInvSaleIssueBill2 newEntity, Param param) throws AppException {
    	//获取期间
		PeriodCalendar periodCalendar = periodCalendarBiz.selectByBizdate(newEntity.getBizDate(), param);
		if(periodCalendar==null){
			throw new AppException("com.armitage.server.iars.daily.selectByBizdate.error");
		}
		newEntity.setPeriodId(periodCalendar.getPeriodId());
		newEntity.setAccountYear(periodCalendar.getAccountYear());
		newEntity.setAccountPeriod(periodCalendar.getAccountPeriod());
	}

    @Override
    protected void afterAdd(CommonBean bean, Param param) throws AppException {
        ScmInvSaleIssueBill2 scmInvSaleIssueBill = (ScmInvSaleIssueBill2) bean.getList().get(0);
        if(scmInvSaleIssueBill != null && scmInvSaleIssueBill.getOtId() > 0){
            //新增出库明细
            List<ScmInvSaleIssueBillEntry2> scmInvSaleIssueBillEntryList = bean.getList2();
            if(scmInvSaleIssueBillEntryList != null && !scmInvSaleIssueBillEntryList.isEmpty()){
            	generateLot(scmInvSaleIssueBill,scmInvSaleIssueBillEntryList);
                int lineId = 1;
                for(ScmInvSaleIssueBillEntry2 scmInvSaleIssueBillEntry : scmInvSaleIssueBillEntryList){
                    scmInvSaleIssueBillEntry.setOtId(scmInvSaleIssueBill.getOtId());
                    scmInvSaleIssueBillEntry.setLineId(lineId);
					ScmMaterial2 scmMaterial = scmMaterialBiz.selectDirect(scmInvSaleIssueBillEntry.getItemId(), param);
					if(scmMaterial==null){
						throw new AppException("iscm.purchasemanage.purchasebusiness.ScmPurRequireBizImpl.error.itemnorexists");
					}
					scmInvSaleIssueBillEntry.setBaseUnit(scmMaterial.getBaseUnitId());
					BigDecimal invConvRate = ScmMaterialUtil.getConvRate(scmInvSaleIssueBillEntry.getItemId(), scmInvSaleIssueBillEntry.getUnit(), param);//库存单位转换系数
					scmInvSaleIssueBillEntry.setBaseQty(scmInvSaleIssueBillEntry.getQty().multiply(invConvRate));
                    scmInvSaleIssueBillEntryBiz.add(scmInvSaleIssueBillEntry, param);
                    lineId = lineId+1;
                }
            }
        }
    }
    
    private void generateLot(ScmInvSaleIssueBill2 scmInvSaleIssueBill,List<ScmInvSaleIssueBillEntry2> scmInvSaleIssueBillEntryList){
    	if(StringUtils.contains("6,8", scmInvSaleIssueBill.getBizType())){
            String addLot = CodeAutoCalUtil.autoAddOne("00");
            for(int i= 0;i<scmInvSaleIssueBillEntryList.size();i++){
                if(StringUtils.isBlank(scmInvSaleIssueBillEntryList.get(i).getLot())){
                    scmInvSaleIssueBillEntryList.get(i).setLot((scmInvSaleIssueBill.getOtNo())+"-"+addLot);
                    long itemId = scmInvSaleIssueBillEntryList.get(i).getItemId();
                    String outLot = addLot;
                    for(int j= i+1;j<scmInvSaleIssueBillEntryList.size();j++){
                    	if(StringUtils.isBlank(scmInvSaleIssueBillEntryList.get(j).getLot())){
                    		if(itemId == scmInvSaleIssueBillEntryList.get(j).getItemId()){
                    			outLot = CodeAutoCalUtil.autoAddOne(outLot);
                    			scmInvSaleIssueBillEntryList.get(j).setLot((scmInvSaleIssueBill.getOtNo())+"-"+outLot);
                            }
                    	}
                    }
                }
            }
        }
    }
    
	@Override
    protected void afterUpdate(CommonBean bean, Param param) throws AppException {
        ScmInvSaleIssueBill2 scmInvSaleIssueBill = (ScmInvSaleIssueBill2) bean.getList().get(0);
        if(scmInvSaleIssueBill != null && scmInvSaleIssueBill.getOtId() > 0){
            //更新出库明细
            List<ScmInvSaleIssueBillEntry2> scmInvSaleIssueBillEntryList = bean.getList2();
            if(scmInvSaleIssueBillEntryList != null && !scmInvSaleIssueBillEntryList.isEmpty()){
            	generateLot(scmInvSaleIssueBill,scmInvSaleIssueBillEntryList);
                int lineId = 1;
                for(ScmInvSaleIssueBillEntry2 scmInvSaleIssueBillEntry : scmInvSaleIssueBillEntryList){
                    scmInvSaleIssueBillEntry.setOtId(scmInvSaleIssueBill.getOtId());
                    if (StringUtils.equals("I", scmInvSaleIssueBill.getStatus())) {
                    	scmInvSaleIssueBillEntry.setLineId(lineId);
                    }
					ScmMaterial2 scmMaterial = scmMaterialBiz.selectDirect(scmInvSaleIssueBillEntry.getItemId(), param);
					if(scmMaterial==null){
						throw new AppException("iscm.purchasemanage.purchasebusiness.ScmPurRequireBizImpl.error.itemnorexists");
					}
					scmInvSaleIssueBillEntry.setBaseUnit(scmMaterial.getBaseUnitId());
					BigDecimal invConvRate = ScmMaterialUtil.getConvRate(scmInvSaleIssueBillEntry.getItemId(), scmInvSaleIssueBillEntry.getUnit(), param);//库存单位转换系数
					scmInvSaleIssueBillEntry.setBaseQty(scmInvSaleIssueBillEntry.getQty().multiply(invConvRate));
                    scmInvSaleIssueBillEntry.setOtId(scmInvSaleIssueBill.getOtId());
                    lineId = lineId+1;
                }
                scmInvSaleIssueBillEntryBiz.update(scmInvSaleIssueBill, scmInvSaleIssueBillEntryList, ScmInvSaleIssueBillEntry2.FN_OTID, param);
            }
        }
    }
    
    private ScmInvSaleIssueBill2 updateStatus(ScmInvSaleIssueBill2 scmInvSaleIssueBill,Param param) throws AppException {
        if(scmInvSaleIssueBill != null){
        	return this.updateDirect(scmInvSaleIssueBill, param);
        }
        return null;
    }

    @Override
    public ScmInvSaleIssueBill2 postBill(ScmInvSaleIssueBill2 scmInvSaleIssueBill, Param param) throws AppException {
    	scmInvSaleIssueBill = this.selectDirect(scmInvSaleIssueBill.getOtId(), param);
        if (scmInvSaleIssueBill != null) {
            if(!StringUtils.equals("A",scmInvSaleIssueBill.getStatus())) {
            	throw new AppException(Message.getMessage(param.getLang(), "iscm.inventorymanage.post.error2", new String[] {scmInvSaleIssueBill.getOtNo()}));
    		}
        	if(StringUtils.contains("1,3", scmInvSaleIssueBill.getBizType())){
	            // 1 查出明细
	            List<ScmInvSaleIssueBillEntry2> scmInvSaleIssueBillEntryList = scmInvSaleIssueBillEntryBiz.selectByOtId(scmInvSaleIssueBill.getOtId(), param);
				scmInvSaleIssueBillEntryList = (List<ScmInvSaleIssueBillEntry2>)ListSortUtil.sort(scmInvSaleIssueBillEntryList,  "lineId","Desc");	//按行号排序
				int amtPrecision = Integer.parseInt(sysParamBiz.getValue(param.getOrgUnitNo(), "SCM_AmtPrecision", "2", param));
				HashMap<String,List<ScmInvStock2>> qtyMap = new HashMap<String,List<ScmInvStock2>>();
				Integer lineId = 1;
	            if(scmInvSaleIssueBillEntryList != null && !scmInvSaleIssueBillEntryList.isEmpty()){
	                for (ScmInvSaleIssueBillEntry2 scmInvSaleIssueBillEntry2 : scmInvSaleIssueBillEntryList) {
	                	if(BigDecimal.ZERO.compareTo(scmInvSaleIssueBillEntry2.getQty())==0)
	                		continue;
	                	StringBuffer info = new StringBuffer("");
	                	if(scmInvSaleIssueBillEntry2.getWareHouseId()>0) {
		    				info.append(scmInvSaleIssueBillEntry2.getOrgUnitNo()).append("_")
		    						.append(scmInvSaleIssueBillEntry2.getWareHouseId()).append("_")
		    						.append(String.valueOf(scmInvSaleIssueBillEntry2.getItemId())).append("_");
	                	}else {
		    				info.append(scmInvSaleIssueBillEntry2.getOutCostOrgUnitNo()).append("_")
	    						.append(scmInvSaleIssueBillEntry2.getOutOrgUnitNo()).append("_")
	    						.append(String.valueOf(scmInvSaleIssueBillEntry2.getItemId())).append("_");
	                	}
	    				if(!qtyMap.containsKey((info.toString()))){
		                    // 2 有没有批次都要拆单, 查询结存(先进先出还是后进先出)
		                    Page page = new Page();
		                    page.setModelClass(ScmMaterial2.class);
		                    page.setShowCount(Integer.MAX_VALUE);
		                    page.setSqlCondition("ScmMaterial.id="+scmInvSaleIssueBillEntry2.getItemId());
		                    List<String> argList = new ArrayList<String>();
		                    argList.add("orgUnitNo=" + scmInvSaleIssueBill.getFinOrgUnitNo());
		                    argList.add("controlUnitNo=" + param.getControlUnitNo());
		                    List<ScmMaterial2> materialList = scmMaterialBiz.queryPage(page, argList, "findBySingleFinAllPage", param);
		                    HashMap<String, Object> map = new HashMap<String, Object>();
		                	if(scmInvSaleIssueBillEntry2.getWareHouseId()>0) {
			                    map.put("orgUnitNo", scmInvSaleIssueBillEntry2.getOrgUnitNo());
			                    map.put("wareHouseId", scmInvSaleIssueBillEntry2.getWareHouseId());
		                	}else {
			                    map.put("orgUnitNo", scmInvSaleIssueBillEntry2.getOutOrgUnitNo());
			                    map.put("wareHouseId", 0);
		                	}
		                    map.put("itemId", scmInvSaleIssueBillEntry2.getItemId());
		                    map.put("bizDate", scmInvSaleIssueBill.getBizDate());
		                    map.put("unit", scmInvSaleIssueBillEntry2.getUnit());
		                    // 获取costMethod来决定升序还是降序排序
		                    if (materialList != null && materialList.size() > 0) {
		                        map.put("costMethod", materialList.get(0).getCosting());
		                    }
		                    List<ScmInvStock2> list = scmInvStockBiz.findByDate(map, param);
		                    if (list == null || list.size() == 0) {
		                    	throw new AppException("com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.service.impl.common.stockNotEnough",new String[]{materialList.get(0).getItemName()});
		                    }
		                    qtyMap.put(info.toString(), list);
	    				}
	    				List<ScmInvStock2> stocklist = qtyMap.get(info.toString());
	    				if(stocklist != null && !stocklist.isEmpty()){
	    					if(StringUtils.isNotBlank(scmInvSaleIssueBillEntry2.getLot())) {
	                    		//有批次则先按批次找，如不够再按先进先出拆单
	                    		for (ScmInvStock2 scmInvStock : stocklist) {
	                    			if(StringUtils.equals(scmInvSaleIssueBillEntry2.getLot(), scmInvStock.getLot())) {
	    		                		if(scmInvSaleIssueBillEntry2.getQty().compareTo(BigDecimal.ZERO)>0) {
	    		                			if(setDataFromStock(scmInvStock,scmInvSaleIssueBillEntry2,amtPrecision,lineId,param))
	    		                				lineId = lineId + 1;
	    		                		}else {
	    		                			break;
	    		                		}
	                    			}
	                    		}
	                    	}
	                    	for (ScmInvStock2 scmInvStock : stocklist) {
	                    		if(scmInvSaleIssueBillEntry2.getQty().compareTo(BigDecimal.ZERO)>0) {
	                    			if(setDataFromStock(scmInvStock,scmInvSaleIssueBillEntry2,amtPrecision,lineId,param))
		                				lineId = lineId + 1;
	                    		}else {
	                    			break;
	                    		}
	                    	}
	    				}
	                    // 删除原来的明细
	                    scmInvSaleIssueBillEntryBiz.delete(scmInvSaleIssueBillEntry2, param);
	                } 
	            }
        	}
            List<String> bizTypeList = new ArrayList<>();
            bizTypeList.add("1");
            bizTypeList.add("2");
            bizTypeList.add("3");
        	List<ScmInvSaleIssueBillEntry2> scmInvSaleIssueBillEntryList = scmInvSaleIssueBillEntryBiz.selectOutEffectRow(scmInvSaleIssueBill.getOtId(), param);                // 更新结存
            if (bizTypeList.contains((String)scmInvSaleIssueBill.getBizType())) {
                int updateRow = scmInvStockBiz.updateBySaleIssuePostSub(scmInvSaleIssueBill.getOtId(), param);
                if(updateRow<scmInvSaleIssueBillEntryList.size()){
        			throw new AppException("iscm.inventorymanage.common.post.error.rowsnotequal");
        		}
                // 更新期间余额表
                scmInvBalBiz.updateBySaleIssuePostPlus(scmInvSaleIssueBill.getOtId(), param);
                // 更新移动平均即时成本表
//                scmInvCostBiz.updateBySaleIssuePostSub(scmInvSaleIssueBill.getOtId(), param);
            } else {
                // 更新结存，插入结存表
                if (scmInvSaleIssueBill.isOffset()) {
                } else {
                    scmInvStockBiz.updateBySaleIssueNotOffset(scmInvSaleIssueBill.getOtId(), param);
                    scmInvStockBiz.addBySaleIssuePost(scmInvSaleIssueBill.getOtId(), param);
                }
                // 更新调出期间余额表，插入期间余额表
                scmInvBalBiz.updateBySaleIssuePostSub(scmInvSaleIssueBill.getOtId(), param);
                scmInvBalBiz.addBySaleIssuePost(scmInvSaleIssueBill.getOtId(), param);
                // 更新移动平均即时成本表，插入移动平均即时成本表
//                scmInvCostBiz.updateBySaleIssuePostPlus(scmInvSaleIssueBill.getOtId(), param);
//                scmInvCostBiz.addBySaleIssuePost(scmInvSaleIssueBill.getOtId(), param);
            }
			if (!(ParamType.Operation.equals(param.getParamType()) && ((OperationParam) param).getOperationId()==629223199L)) {
	            scmInvSaleIssueBill.setCheckDate(CalendarUtil.getDate(param));
	            scmInvSaleIssueBill.setChecker(param.getUsrCode());
	            scmInvSaleIssueBill.setPostDate(CalendarUtil.getDate(param));
			}
            scmInvSaleIssueBill.setStatus("E");
            int updateRow = this.updatePostedStatus(scmInvSaleIssueBill, param);
			if(updateRow!=1) {
				throw new AppException(Message.getMessage(param.getLang(), "iscm.inventorymanage.post.error2", new String[] {scmInvSaleIssueBill.getOtNo()}));
			}
			if (!(ParamType.Operation.equals(param.getParamType()) && ((OperationParam) param).getOperationId()==629223199L)) {
				this.afterPostBill(scmInvSaleIssueBill, param);	//过账生成收货单
			}
        }
        return scmInvSaleIssueBill;
    }

	private int updatePostedStatus(ScmInvSaleIssueBill2 scmInvSaleIssueBill, Param param) {
		ScmInvSaleIssueBill2 oldEntity = this.selectDirect(scmInvSaleIssueBill.getOtId(), param);
		HashMap<String,Object> map = new HashMap<String,Object> ();
		map.put("otId",scmInvSaleIssueBill.getOtId());
		map.put("checker",scmInvSaleIssueBill.getChecker());
		map.put("checkDate",scmInvSaleIssueBill.getCheckDate()==null?null:FormatUtils.fmtDate(scmInvSaleIssueBill.getCheckDate()));
		map.put("status", scmInvSaleIssueBill.getStatus());
		map.put("postDate", scmInvSaleIssueBill.getPostDate()==null?null:FormatUtils.fmtDateTime(scmInvSaleIssueBill.getPostDate()));
		int i = ((ScmInvSaleIssueBillDAO)dao).updatePostedStatus(map);
		// 记录操作日志
		try {
			LogUtils.logEvent(scmInvSaleIssueBill, param);
			LogUtils.logModifyData(scmInvSaleIssueBill, oldEntity, param);
		} catch (Exception e) {

		}
		return i;
	}

	private boolean setDataFromStock(ScmInvStock2 scmInvStock,ScmInvSaleIssueBillEntry2 scmInvSaleIssueBillEntry2,int amtPrecision,Integer lineId,Param param) throws AppException{
		boolean flag=false;
		BigDecimal stockQty = scmInvStock.getQty();
		BigDecimal stockPieQty = scmInvStock.getPieQty();
		BigDecimal stockBaseQty = scmInvStock.getBaseQty();
		BigDecimal stockAmt = (scmInvStock.getAmt());
		BigDecimal stockTaxAmt = (scmInvStock.getTaxAmt());
		if (stockQty.compareTo(scmInvSaleIssueBillEntry2.getQty()) > 0) {
			ScmInvSaleIssueBillEntry2 scmInvSaleIssueBillEntry = new ScmInvSaleIssueBillEntry2();
			BeanUtils.copyProperties(scmInvSaleIssueBillEntry2, scmInvSaleIssueBillEntry, new String[] { "id" });
			scmInvSaleIssueBillEntry.setLot(scmInvStock.getLot());
			scmInvSaleIssueBillEntry.setStockId(scmInvStock.getId());
			scmInvSaleIssueBillEntry.setPrice(scmInvStock.getPrice());
			scmInvSaleIssueBillEntry.setTaxPrice(scmInvStock.getTaxPrice());
			scmInvSaleIssueBillEntry.setAmt(scmInvSaleIssueBillEntry.getQty().multiply(scmInvSaleIssueBillEntry.getPrice()).setScale(amtPrecision, RoundingMode.HALF_UP));
			scmInvSaleIssueBillEntry.setTaxAmt(scmInvSaleIssueBillEntry.getQty().multiply(scmInvSaleIssueBillEntry.getTaxPrice()).setScale(amtPrecision, RoundingMode.HALF_UP));
			scmInvSaleIssueBillEntry.setTaxRate(scmInvStock.getTaxRate());
			ScmMaterial2 scmMaterial2 = scmMaterialBiz.findByInvItemId(param.getControlUnitNo(), scmInvSaleIssueBillEntry2.getOrgUnitNo(), scmInvSaleIssueBillEntry2.getItemId(), param);
			if (scmInvStock.getTaxRate().compareTo(new BigDecimal("0.13")) == 0
					|| scmInvStock.getTaxRate().compareTo(new BigDecimal("0.09")) == 0) {
				scmInvSaleIssueBillEntry.setSaleTaxRate(scmInvStock.getTaxRate());
			} else {
				scmInvSaleIssueBillEntry.setSaleTaxRate(scmMaterial2.getSaleTaxRate());
			}
			String saleRateAllowZero = sysParamBiz.getValue(param.getOrgUnitNo(), "SCM_SaleRateAllowZero", "N", param);
			if(scmInvSaleIssueBillEntry.getSaleTaxRate().compareTo(BigDecimal.ZERO)==0 && StringUtils.equals(saleRateAllowZero, "N")) {
				throw new AppException("iscm.inventorymanage.ScmInvSaleIssueBill.SaleTaxRate.zero",new String[]{scmMaterial2.getItemName()});
			}
			scmInvSaleIssueBillEntry.setOrgSourceId(scmInvStock.getSourceBillId());
			scmInvSaleIssueBillEntry.setOrgSourceVendorId(scmInvStock.getVendorId());
			scmInvSaleIssueBillEntry.setInvDate(scmInvStock.getInvDate());
			scmInvSaleIssueBillEntry.setLineId(lineId);
			scmInvSaleIssueBillEntryBiz.add(scmInvSaleIssueBillEntry, param);
			scmInvSaleIssueBillEntry2.setQty(BigDecimal.ZERO);
			scmInvSaleIssueBillEntry2.setPieQty(BigDecimal.ZERO);
			scmInvSaleIssueBillEntry2.setBaseQty(BigDecimal.ZERO);
    		scmInvStock.setQty(scmInvStock.getQty().subtract(scmInvSaleIssueBillEntry.getQty()));
    		scmInvStock.setPieQty(scmInvStock.getPieQty().subtract(scmInvSaleIssueBillEntry.getPieQty()));
    		scmInvStock.setBaseQty(scmInvStock.getBaseQty().subtract(scmInvSaleIssueBillEntry.getBaseQty()));
    		scmInvStock.setAmt(scmInvStock.getAmt().subtract(scmInvSaleIssueBillEntry.getAmt()));
    		scmInvStock.setTaxAmt(scmInvStock.getTaxAmt().subtract(scmInvSaleIssueBillEntry.getTaxAmt()));
    		flag=true;
		} else if (stockQty.compareTo(BigDecimal.ZERO) > 0) {
			ScmInvSaleIssueBillEntry2 scmInvSaleIssueBillEntry = new ScmInvSaleIssueBillEntry2();
			BeanUtils.copyProperties(scmInvSaleIssueBillEntry2, scmInvSaleIssueBillEntry, new String[] { "id" });
			scmInvSaleIssueBillEntry.setLot(scmInvStock.getLot());
			scmInvSaleIssueBillEntry.setStockId(scmInvStock.getId());
			scmInvSaleIssueBillEntry.setPrice(scmInvStock.getPrice());
			scmInvSaleIssueBillEntry.setTaxPrice(scmInvStock.getTaxPrice());
			scmInvSaleIssueBillEntry.setQty(stockQty);
			scmInvSaleIssueBillEntry.setPieQty(stockPieQty);
			scmInvSaleIssueBillEntry.setBaseQty(stockBaseQty);
			scmInvSaleIssueBillEntry.setAmt(stockAmt);
			scmInvSaleIssueBillEntry.setTaxAmt(stockTaxAmt);
			scmInvSaleIssueBillEntry.setSaleQty(scmInvSaleIssueBillEntry.getQty());
			if (stockQty.compareTo(scmInvSaleIssueBillEntry2.getQty())< 0) {
				scmInvSaleIssueBillEntry.setSaleTaxAmt(scmInvSaleIssueBillEntry.getSaleQty().multiply(scmInvSaleIssueBillEntry.getSaleTaxPrice()).setScale(amtPrecision, RoundingMode.HALF_UP));
			}
			scmInvSaleIssueBillEntry.setTaxRate(scmInvStock.getTaxRate());
			ScmMaterial2 scmMaterial2 = scmMaterialBiz.findByInvItemId(param.getControlUnitNo(), scmInvSaleIssueBillEntry2.getOrgUnitNo(), scmInvSaleIssueBillEntry2.getItemId(), param);
			if (scmInvStock.getTaxRate().compareTo(new BigDecimal("0.13")) == 0
					|| scmInvStock.getTaxRate().compareTo(new BigDecimal("0.09")) == 0) {
				scmInvSaleIssueBillEntry.setSaleTaxRate(scmInvStock.getTaxRate());
			} else {
				scmInvSaleIssueBillEntry.setSaleTaxRate(scmMaterial2.getSaleTaxRate());
			}
			String saleRateAllowZero = sysParamBiz.getValue(param.getOrgUnitNo(), "SCM_SaleRateAllowZero", "N", param);
			if(scmInvSaleIssueBillEntry.getSaleTaxRate().compareTo(BigDecimal.ZERO)==0 && StringUtils.equals(saleRateAllowZero, "N")) {
				throw new AppException("iscm.inventorymanage.ScmInvSaleIssueBill.SaleTaxRate.zero",new String[]{scmMaterial2.getItemName()});
			}
			scmInvSaleIssueBillEntry.setOrgSourceId(scmInvStock.getSourceBillId());
			scmInvSaleIssueBillEntry.setOrgSourceVendorId(scmInvStock.getVendorId());
			scmInvSaleIssueBillEntry.setInvDate(scmInvStock.getInvDate());
			scmInvSaleIssueBillEntry.setLineId(lineId);
			scmInvSaleIssueBillEntryBiz.add(scmInvSaleIssueBillEntry, param);
			scmInvSaleIssueBillEntry2.setQty(scmInvSaleIssueBillEntry2.getQty().subtract(scmInvSaleIssueBillEntry.getQty()));
			scmInvSaleIssueBillEntry2.setPieQty(scmInvSaleIssueBillEntry2.getPieQty().subtract(scmInvSaleIssueBillEntry.getPieQty()));
			scmInvSaleIssueBillEntry2.setBaseQty(scmInvSaleIssueBillEntry2.getBaseQty().subtract(scmInvSaleIssueBillEntry.getBaseQty()));
			scmInvSaleIssueBillEntry2.setSaleQty(scmInvSaleIssueBillEntry2.getSaleQty().subtract(scmInvSaleIssueBillEntry.getSaleQty()));
			scmInvSaleIssueBillEntry2.setSaleTaxAmt(scmInvSaleIssueBillEntry2.getSaleTaxAmt().subtract(scmInvSaleIssueBillEntry.getSaleQty().multiply(scmInvSaleIssueBillEntry.getSaleTaxPrice()).setScale(amtPrecision, RoundingMode.HALF_UP)));
    		scmInvStock.setQty(scmInvStock.getQty().subtract(scmInvSaleIssueBillEntry.getQty()));
    		scmInvStock.setPieQty(scmInvStock.getPieQty().subtract(scmInvSaleIssueBillEntry.getPieQty()));
    		scmInvStock.setBaseQty(scmInvStock.getBaseQty().subtract(scmInvSaleIssueBillEntry.getBaseQty()));
    		scmInvStock.setAmt(scmInvStock.getAmt().subtract(scmInvSaleIssueBillEntry.getAmt()));
    		scmInvStock.setTaxAmt(scmInvStock.getTaxAmt().subtract(scmInvSaleIssueBillEntry.getTaxAmt()));
    		flag=true;
		}
		return flag;
	}
    private void afterPostBill(ScmInvSaleIssueBill2 scmInvSaleIssueBill, Param param){
   		scmPurReceiveBiz.generateFromSaleIssue(scmInvSaleIssueBill, param);
    }
    
    @Override
    public ScmInvSaleIssueBill2 cancelPostBill(ScmInvSaleIssueBill2 scmInvSaleIssueBill, Param param)
            throws AppException {
    	scmInvSaleIssueBill = this.selectDirect(scmInvSaleIssueBill.getOtId(), param);
        if (scmInvSaleIssueBill != null) {
            if(!StringUtils.equals("E",scmInvSaleIssueBill.getStatus())) {
            	throw new AppException(Message.getMessage(param.getLang(), "iscm.inventorymanage.cancelPost.error2", new String[] {scmInvSaleIssueBill.getOtNo()}));
    		}
            SystemState systemState = systemStateBiz.selectBySystemId(scmInvSaleIssueBill.getFinOrgUnitNo(), 170, param);
            if(systemState==null){
            	throw new AppException(Message.getMessage(param.getLang(),"com.armitage.iars.daily.util.disenable.unable"));
            }
            if (systemState.getCurrentPeriodId() != scmInvSaleIssueBill.getPeriodId()) {
            	throw new AppException(Message.getMessage(param.getLang(), 
                        "com.armitage.server.iscm.postBill.peroidId.error.notCurrent", new String[] {scmInvSaleIssueBill.getOtNo()}));
            }            
            // 检查盘点物资冻结
            // 检查冻结仓库
            List<ScmInvSaleIssueBill2> cmInvSaleIssueBillList = ((ScmInvSaleIssueBillDAO) dao).checkWareHouseFree(scmInvSaleIssueBill.getOtId());
            if (cmInvSaleIssueBillList != null && !cmInvSaleIssueBillList.isEmpty()) {
            	throw new AppException(Message.getMessage(param.getLang(), 
                        "com.armitage.server.iscm.postBill.peroidId.error.countingTask.countingTable"));
//            	for (ScmInvSaleIssueBill2 scmInvSaleIssueBill2 : cmInvSaleIssueBillList) {
//	                // 检查冻结物资
//	                HashMap<String,Object> map = new HashMap<String,Object>();
//	                map.put("taskId", scmInvSaleIssueBill2.getTaskId());
//	                map.put("otId", scmInvSaleIssueBill.getOtId());
//	                int count = ((ScmInvSaleIssueBillDAO) dao).checkMaterialFree(map);
//	                if (count > 0) {
//	                	throw new AppException(Message.getMessage(param.getLang(), 
//	                            "com.armitage.server.iscm.postBill.peroidId.error.countingTask.countingTable"));
//	                }
//            	}
            }
            //检查冻结部门
            List<ScmInvSaleIssueBill2> scmInvSaleIssueBillByorgList = ((ScmInvSaleIssueBillDAO) dao).checkOrgFree(scmInvSaleIssueBill.getOtId());
            if (scmInvSaleIssueBillByorgList != null && !scmInvSaleIssueBillByorgList.isEmpty()) {
            	throw new AppException(Message.getMessage(param.getLang(), 
                        "com.armitage.server.iscm.postBill.peroidId.error.countingTask.countingCostcenter"));
			}
            
            String flag = "";
	        List<String> bizTypeList = new ArrayList<>();
	        bizTypeList.add("1");
	        bizTypeList.add("2");
	        bizTypeList.add("3");
	        if (bizTypeList.contains((String)scmInvSaleIssueBill.getBizType())) {
	            flag = "0";
	        } else {
	            flag = "1";
	        }
	        // 更新结存
	        int updateRow = scmInvStockBiz.updateBySaleIssueUnPost(scmInvSaleIssueBill.getOtId(), flag, param);
	        if(StringUtils.equals("1", flag)){
	        	List<ScmInvSaleIssueBillEntry2> scmInvSaleIssueBillEntryList = scmInvSaleIssueBillEntryBiz.selectCancelPostEffectRow(scmInvSaleIssueBill.getOtId(), param);
	        	if(updateRow<scmInvSaleIssueBillEntryList.size()){
        			throw new AppException("iscm.inventorymanage.common.post.error.rowsnotequal");
        		}
	        }
	        // 更新期间余额表
	        scmInvBalBiz.updateBySaleIssueUnPost(scmInvSaleIssueBill.getOtId(), flag, param);
	        // 更新移动平均即时成本表
//	        scmInvCostBiz.updateBySaleIssueUnPost(scmInvSaleIssueBill.getOtId(), flag, param);
	        scmInvSaleIssueBill.setCheckDate(null);
	        scmInvSaleIssueBill.setChecker("");
	        scmInvSaleIssueBill.setStatus("A");
	        updateRow = this.updatePostedStatus(scmInvSaleIssueBill, param);
			if(updateRow!=1) {
				throw new AppException(Message.getMessage(param.getLang(), "iscm.inventorymanage.cancelPost.error2", new String[] {scmInvSaleIssueBill.getOtNo()}));
			}
	        afterCancelPostBill(scmInvSaleIssueBill,param);
	        return scmInvSaleIssueBill;
        }
        return null;
    }

    private void afterCancelPostBill(ScmInvSaleIssueBill2 scmInvSaleIssueBill, Param param){
    	//销售出库取消过账时如是内部交易则取消生成对方的收货单及验收收
    	Customer2 customer = customerBiz.selectDirect(scmInvSaleIssueBill.getCustId(), param);
    	if(StringUtils.equals("2", customer.getRole())) {
    		scmPurReceiveBiz.deleteFromSaleIssue(scmInvSaleIssueBill, param);
    	}
    }
    
    @Override
    public List<String> postBillCheck(ScmInvSaleIssueBill2 scmInvSaleIssueBill, Param param)
            throws AppException {
        List<String> msgList = new ArrayList<String>();// 提示列表
        scmInvSaleIssueBill = this.selectDirect(scmInvSaleIssueBill.getOtId(), param);
        if (scmInvSaleIssueBill != null) {
            if(!StringUtils.equals("A",scmInvSaleIssueBill.getStatus())) {
    			msgList.add(Message.getMessage(param.getLang(), "iscm.inventorymanage.post.error2", new String[] {scmInvSaleIssueBill.getOtNo()}));
    			return msgList;
    		}
            SystemState systemState = systemStateBiz.selectBySystemId(scmInvSaleIssueBill.getFinOrgUnitNo(), 170, param);
            if(systemState==null){
                msgList.add(Message.getMessage(param.getLang(),"com.armitage.iars.daily.util.disenable.unable"));
                return msgList;
            }
            if (systemState.getCurrentPeriodId() != scmInvSaleIssueBill.getPeriodId()) {
                msgList.add(Message.getMessage(param.getLang(), 
                        "com.armitage.server.iscm.postBill.peroidId.error.notCurrent", new String[] {scmInvSaleIssueBill.getOtNo()}));
                return msgList;
            }
            
            // 检查盘点物资冻结
            // 检查冻结仓库
            List<ScmInvSaleIssueBill2> cmInvSaleIssueBillList = ((ScmInvSaleIssueBillDAO) dao).checkWareHouseFree(scmInvSaleIssueBill.getOtId());
            if (cmInvSaleIssueBillList != null && !cmInvSaleIssueBillList.isEmpty()) {
            	throw new AppException(Message.getMessage(param.getLang(), 
                        "com.armitage.server.iscm.postBill.peroidId.error.countingTask.countingTable"));
//            	for (ScmInvSaleIssueBill2 scmInvSaleIssueBill2 : cmInvSaleIssueBillList) {
//	                // 检查冻结物资
//	                HashMap<String,Object> map = new HashMap<String,Object>();
//	                map.put("taskId", scmInvSaleIssueBill2.getTaskId());
//	                map.put("otId", scmInvSaleIssueBill.getOtId());
//	                int count = ((ScmInvSaleIssueBillDAO) dao).checkMaterialFree(map);
//	                if (count > 0) {
//	                	throw new AppException(Message.getMessage(param.getLang(), 
//	                            "com.armitage.server.iscm.postBill.peroidId.error.countingTask.countingTable"));
//	                }
//            	}
            }
            //检查冻结部门
            List<ScmInvSaleIssueBill2> scmInvSaleIssueBillByorgList = ((ScmInvSaleIssueBillDAO) dao).checkOrgFree(scmInvSaleIssueBill.getOtId());
            if (scmInvSaleIssueBillByorgList != null && !scmInvSaleIssueBillByorgList.isEmpty()) {
            	throw new AppException(Message.getMessage(param.getLang(), 
                        "com.armitage.server.iscm.postBill.peroidId.error.countingTask.countingCostcenter"));
			}
        }
        if (Integer.parseInt(scmInvSaleIssueBill.getBizType()) < 6) {
            List<ScmInvSaleIssueBillEntry2> list = scmInvSaleIssueBillEntryBiz.selectInvQty(scmInvSaleIssueBill.getOtId(), param);
            
            if (list != null && list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    if (StringUtils.isNotBlank(list.get(i).getLot())) {
                        msgList.add(Message.getMessage(param.getLang(), 
                                "com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.service.impl.ScmInvSaleIssueBillBizImpl.count", 
                                new String[] {list.get(i).getItemNo(), list.get(i).getItemName(), list.get(i).getLot(),
                                        list.get(i).getQty().toString(), list.get(i).getInvQty().toString()}));
                    } else {
                        msgList.add(Message.getMessage(param.getLang(), 
                                "com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.service.impl.ScmInvSaleIssueBillBizImpl.count2", 
                                new String[] {list.get(i).getItemNo(), list.get(i).getItemName(), 
                                        list.get(i).getQty().toString(), list.get(i).getInvQty().toString()}));
                    }
                }
                return msgList;
            }
        }else {
        	//退货
        	List<ScmInvSaleIssueBillEntry2> list = scmInvSaleIssueBillEntryBiz.selectByOtId(scmInvSaleIssueBill.getOtId(), param);
        	if (list != null && list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                	if(list.get(i).getWareHouseId()==0 && StringUtils.isEmpty(list.get(i).getOutOrgUnitNo())) {
                        msgList.add(Message.getMessage(param.getLang(), 
                                "iscm.inventorymanage.warehouseoutbusiness.ScmInvMaterialReqBill.postBillCheck.error.wareHouseNull", 
                                new String[] {list.get(i).getItemNo(), list.get(i).getItemName()}));
                	}
                }
        	}
        }
        return msgList;
    }

	@Override
	public ScmInvSaleIssueBill2 doSubmit(ScmInvSaleIssueBill2 scmInvSaleIssueBill, Param param) throws AppException {
		ScmInvSaleIssueBill2 invSaleOrder = null;
		if(scmInvSaleIssueBill.getOtId()>0){
			invSaleOrder = this.selectDirect(scmInvSaleIssueBill.getOtId(), param);
		}else{
			Page page=new Page();
			page.setModelClass(ScmInvSaleOrder.class);
			page.setShowCount(Integer.MAX_VALUE);
			page.getParam().put(ScmInvSaleOrder.FN_OTNO,new QueryParam(ScmInvSaleOrder.FN_OTNO, QueryParam.QUERY_EQ,scmInvSaleIssueBill.getOtNo()));
			
			List<ScmInvSaleIssueBill2> scmInvSaleOrderList =this.findPage(page, param);
			
			if(scmInvSaleOrderList!=null && !scmInvSaleOrderList.isEmpty()){
				invSaleOrder = scmInvSaleOrderList.get(0);
			}
		}
		
		if(invSaleOrder != null){
			if(!invSaleOrder.getStatus().equals("I")){
				throw new AppException("iscm.purchasemanage.error.commit");
			}else if(invSaleOrder.getStatus().equals("I")){
				List<ScmInvSaleIssueBillEntry2> scmInvSaleOrderEntryList = scmInvSaleIssueBillEntryBiz.selectByOtId(invSaleOrder.getOtId(), param);
				
				List<OrgCompany2> orgCompanyList = orgUnitRelationBiz.findToOrgUnitByType(OrgUnitRelationType.INVTOFIN, invSaleOrder.getOrgUnitNo(), false, null, param);
				if(orgCompanyList==null || orgCompanyList.isEmpty()){
					throw new AppException("iscm.purchasemanage.purchasebusiness.ScmPurRequireBizImpl.error.notfin");
				}			
		        BillType2 billType = billTypeBiz.selectByOrgAndCode(orgCompanyList.get(0).getOrgUnitNo(), "InvSaleIssueBill", param);
				boolean needAudit = false;
				if(billType!=null && billType.isNeedAudit())
					needAudit = true;
				if(needAudit){
					CommonBean bean = new CommonBean();
					List<ScmInvSaleIssueBill2> scmInvSaleOrderList = new ArrayList<>();
					scmInvSaleOrderList.add(invSaleOrder);
					bean.setList(scmInvSaleOrderList);
					bean.setList2(scmInvSaleOrderEntryList);
					String processInstanceId = ActivityUtil.startProcessInstance(bean, billType.getId(), billType.getWorkFlowNo(), param);
					invSaleOrder.setWorkFlowNo(processInstanceId);
					ActivityUtil activityUtil = new ActivityUtil();
					//判断当前流程是否结束
					boolean isCompleteTask = activityUtil.queryProInstanceStateByProInstanceId(processInstanceId);
					if(isCompleteTask) {
						if(param.getUsrCode()!=null ){
							invSaleOrder.setChecker(param.getUsrCode());
							invSaleOrder.setSubmitter(param.getUsrCode());
						}
						invSaleOrder.setCheckDate(CalendarUtil.getDate(param));
						invSaleOrder.setSubmitDate(CalendarUtil.getDate(param));
						invSaleOrder.setStatus("A");
					}else {
						invSaleOrder.setStatus("D");
						if(param.getUsrCode()!=null ){
							invSaleOrder.setSubmitter(param.getUsrCode());
						}
						invSaleOrder.setSubmitDate(CalendarUtil.getDate(param));
						String usrList= ActivityUtil.findAssigneeByProcessInstanceId(processInstanceId,param);
						if(StringUtils.isNotBlank(usrList)) {
							scmBillPendingBiz.addPendingBill(usrList, invSaleOrder, param);
							InvSaleOrderParams invSaleOrderParams = new InvSaleOrderParams();
							invSaleOrderParams.setOtNo(invSaleOrder.getOtNo());
							AuditMsgUtil.sendAuditMsg(billType.getBillCode(),invSaleOrder,invSaleOrderParams, usrList, param);
						}
					}
				}else{
					if(param.getUsrCode()!=null ){
						invSaleOrder.setChecker(param.getUsrCode());
						invSaleOrder.setSubmitter(param.getUsrCode());
					}
					invSaleOrder.setCheckDate(CalendarUtil.getDate(param));
					invSaleOrder.setSubmitDate(CalendarUtil.getDate(param));
					invSaleOrder.setStatus("A");
				}
				try {
					this.updateStatus(invSaleOrder, param);
				} catch (Exception e) {
					throw e;
				}
				SystemState systemState = systemStateBiz.selectBySystemId(invSaleOrder.getFinOrgUnitNo(), 170, param);
				if(systemState==null){
					throw new AppException("com.armitage.iars.daily.util.disenable.unable");
				}
				if (systemState.getCurrentPeriodId() == invSaleOrder.getPeriodId()) {
					if(StringUtils.equals("A", invSaleOrder.getStatus())) {
						//通过时检查是否自动过帐
						if(billType!=null && billType.isAutoRelease()) {
							List<String> msgList = this.postBillCheck(invSaleOrder, param);
							if (msgList != null && !msgList.isEmpty()) {
								StringBuilder detailInfo = new StringBuilder("");
		                        detailInfo.append(Message.getMessage(param.getLang(),"iscm.inventorymanage.saleissuesbill.post.errorTitle"));
		
								for (String str : msgList) {
		                            detailInfo.append(str).append("\n");
		                        }
								
								throw new AppException(detailInfo.toString(), new String[]{invSaleOrder.getOtNo()});
							}
							this.postBill(invSaleOrder, param);
						}
					}
				}
			}
		}else{
			throw new AppException("iscm.purchasemanage.purchasebusiness.find.ordernotexists");
		}
		return invSaleOrder;
	}

	@Override
	public ScmInvSaleIssueBill2 doUnSubmit(ScmInvSaleIssueBill2 scmInvSaleIssueBill, Param param) throws AppException {
		ScmInvSaleIssueBill2 invSaleOrder = null;
		if(scmInvSaleIssueBill.getOtId()>0){
			invSaleOrder = this.selectDirect(scmInvSaleIssueBill.getOtId(), param);
		}else{
			Page page=new Page();
			page.setModelClass(ScmInvSaleIssueBill2.class);
			page.setShowCount(Integer.MAX_VALUE);
			page.getParam().put(
					ScmInvSaleIssueBill2.FN_OTNO,
					new QueryParam(ScmInvSaleIssueBill2.FN_OTNO, QueryParam.QUERY_EQ,
							scmInvSaleIssueBill.getOtNo()));
			
			List<ScmInvSaleIssueBill2> scmPurRequireList =this.findPage(page, param);
			
			if(scmPurRequireList!=null && !scmPurRequireList.isEmpty()){
				invSaleOrder=scmPurRequireList.get(0);
			}
		}
		
		if(invSaleOrder!=null){
			List<OrgCompany2> orgCompanyList = orgUnitRelationBiz.findToOrgUnitByType(OrgUnitRelationType.INVTOFIN, invSaleOrder.getOrgUnitNo(), false, null, param);
			if(orgCompanyList==null || orgCompanyList.isEmpty()){
				throw new AppException("iscm.purchasemanage.purchasebusiness.ScmPurRequireBizImpl.error.notfin");
			}			
	        BillType2 billType = billTypeBiz.selectByOrgAndCode(orgCompanyList.get(0).getOrgUnitNo(), "InvSaleIssueBill", param);
			boolean needAudit = false;
			if(billType!=null && billType.isNeedAudit() && StringUtils.isNotBlank(invSaleOrder.getWorkFlowNo()))
				needAudit = true;
			
			//单据设置审批流程，但设置没有审批节点，状态为通过，可以取消通过
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put(ScmBillPending.FN_BILLID, new QueryParam(ScmBillPending.FN_BILLID, QueryParam.QUERY_EQ, String.valueOf(invSaleOrder.getOtId())));
			map.put(ScmBillPending.FN_BILLTYPE, new QueryParam(ScmBillPending.FN_BILLTYPE, QueryParam.QUERY_EQ, billType.getBillCode()));
			List<ScmBillPending> scmBillPendingList = scmBillPendingBiz.findAll(map, param);
			
			if(needAudit){
				if (scmBillPendingList == null || scmBillPendingList.isEmpty()) {
					if(!StringUtils.equals(invSaleOrder.getStatus(),"A"))
						throw new AppException("iscm.purchasemanage.error.cancelCommit");
				} else {
					if(!StringUtils.equals(invSaleOrder.getStatus(),"D"))
						throw new AppException("iscm.purchasemanage.error.cancelCommit");
				}
			}else{
				if(!StringUtils.equals(invSaleOrder.getStatus(),"A"))
					throw new AppException("iscm.purchasemanage.error.cancelCommit");
			} 
			if(invSaleOrder.getStatus().equals("A") || invSaleOrder.getStatus().equals("D")){
				invSaleOrder.setChecker(null);
				invSaleOrder.setSubmitter(null);
				invSaleOrder.setCheckDate(null);
				invSaleOrder.setSubmitDate(null);
				invSaleOrder.setStatus("I");
				try{
					this.updateStatus(invSaleOrder, param);
					scmBillPendingBiz.cancelPendingBill(param.getUsrCode(),invSaleOrder, param);
				}catch(AppException e){
					throw e;
				}
			}
		}else{
			throw new AppException("iscm.purchasemanage.purchasebusiness.find.ordernotexists");
		}
		return invSaleOrder;
	}

	@Override
	protected void beforeDelete(ScmInvSaleIssueBill2 entity, Param param)
			throws AppException {
		ScmInvSaleIssueBill2 scmInvSaleIssueBill = this.selectDirect(entity.getOtId(), param);
		if(scmInvSaleIssueBill!=null && !StringUtils.equals(scmInvSaleIssueBill.getStatus(),"I")){
			throw new AppException(Message.getMessage(param.getLang(), "iscm.inventorymanage.delete.error", new String[]{entity.getOtNo()}));
		}
		if(StringUtils.equals("6",entity.getBizType())) {
			//普通销售退货则不能删除
			List<ScmInvPurInWarehsBill2> scmInvPurInWarehsBillList = scmInvPurInWarehsBillBiz.selectBySaleIssueBill(entity.getOtId(), param);
			if(scmInvPurInWarehsBillList!=null && !scmInvPurInWarehsBillList.isEmpty()) {
				StringBuffer wrNos = new StringBuffer("");
				for(ScmInvPurInWarehsBill2 scmInvPurInWarehsBill:scmInvPurInWarehsBillList) {
					if(StringUtils.equals("E",scmInvPurInWarehsBill.getStatus())){
						if(StringUtils.isNotBlank(wrNos.toString()))
							wrNos.append(",");
						wrNos.append(scmInvPurInWarehsBill.getWrNo());
					}
				}
				if(StringUtils.isNotBlank(wrNos.toString()))
					throw new AppException(Message.getMessage(param.getLang(), "iscm.inventorymanage.scmInvSaleIssueBill.delete.buildByPurWarehsBill", new String[]{entity.getOtNo(),wrNos.toString()}));
			}
		}
		if(StringUtils.equals("1",entity.getBizType()) && !entity.isDelBySaleOrder()) {
			List<ScmInvSaleOrder2> scmInvSaleOrderList = scmInvSaleOrderBiz.selectBySaleIssue(entity.getOtId(), param);
			if(scmInvSaleOrderList!=null && !scmInvSaleOrderList.isEmpty()) {
				StringBuffer otNos = new StringBuffer("");
				for(ScmInvSaleOrder2 scmInvSaleOrder:scmInvSaleOrderList) {
					if(StringUtils.isNotBlank(otNos.toString()))
						otNos.append(",");
					otNos.append(scmInvSaleOrder.getOtNo());
				}
				if(StringUtils.isNotBlank(otNos.toString()))
					throw new AppException(Message.getMessage(param.getLang(), "iscm.inventorymanage.scmInvSaleIssueBill.delete.buildBySaleOrder", new String[]{entity.getOtNo(),otNos.toString()}));

			}
		}
		//因为存在回写时需判断销售出库单类型，故需要删除主表前删除子表
        if(entity != null && entity.getOtId() > 0){
            //删除出库明细
            scmInvSaleIssueBillEntryBiz.deleteByOtId(entity.getOtId(), param);
        }
	}

	@Override
	public List<ScmInvSaleIssueBill2> SelectBySaleOrder(ScmInvSaleOrder2 scmInvSaleOrder, Param param) throws AppException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("otId", scmInvSaleOrder.getOtId());
		return ((ScmInvSaleIssueBillDAO)dao).SelectBySaleOrder(map);
	}

	@Override
	protected void beforeUpdate(CommonBean bean, Param param) throws AppException {
		ScmInvSaleIssueBill2 entry = (ScmInvSaleIssueBill2) bean.getList().get(0);
		if(entry!=null){
			ScmInvSaleIssueBill2 scmInvSaleIssueBill = this.select(entry.getPK(), param);
			if(!StringUtils.equals(scmInvSaleIssueBill.getStatus(),"I")){
				throw new AppException(Message.getMessage(param.getLang(), "iscm.commom.edit.error.notnewstatus"));
			}
		}
	}

	@Override
	public List<ScmInvSaleIssueBill2> checkUnPostBill(String finOrgUnitNo,long periodId, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("finOrgUnitNo", finOrgUnitNo);
		map.put("periodId", periodId);
		return ((ScmInvSaleIssueBillDAO)dao).checkUnPostBill(map);
	}

	@Override
	public List<ScmInvSaleIssueBill2> checkPostedBill(String finOrgUnitNo,long periodId, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("finOrgUnitNo", finOrgUnitNo);
		map.put("periodId", periodId);
		return ((ScmInvSaleIssueBillDAO)dao).checkPostedBill(map);
	}
	
	@Override
	public void doAdvQuery(Page page, Param param) {
		if (page.getModel() != null) {
			if (page.getModel() instanceof ScmInvSaleIssueBillAdvQuery) {
				ScmInvSaleIssueBillAdvQuery scmInvSaleIssueBillAdvQuery = (ScmInvSaleIssueBillAdvQuery) page.getModel();
				if(scmInvSaleIssueBillAdvQuery.getCustId()>0) {
					page.getParam().put(ScmInvSaleIssueBill2.FN_CUSTID,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBill2.class) + "." +ScmInvSaleIssueBill2.FN_CUSTID, QueryParam.QUERY_EQ,String.valueOf(scmInvSaleIssueBillAdvQuery.getCustId())));
				}
				if(scmInvSaleIssueBillAdvQuery.getBizDateFrom()!=null){
					if(scmInvSaleIssueBillAdvQuery.getBizDateTo()!=null) {
						page.getParam().put(ScmInvSaleIssueBill2.FN_BIZDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBill2.class) + "." +ScmInvSaleIssueBill2.FN_BIZDATE, QueryParam.QUERY_BETWEEN,FormatUtils.fmtDate(scmInvSaleIssueBillAdvQuery.getBizDateFrom()),FormatUtils.fmtDate(scmInvSaleIssueBillAdvQuery.getBizDateTo())));
					}else {
						page.getParam().put(ScmInvSaleIssueBill2.FN_BIZDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBill2.class) + "." +ScmInvSaleIssueBill2.FN_BIZDATE, QueryParam.QUERY_GE,FormatUtils.fmtDate(scmInvSaleIssueBillAdvQuery.getBizDateFrom())));
					}
				}else if(scmInvSaleIssueBillAdvQuery.getBizDateTo()!=null) {
					page.getParam().put(ScmInvSaleIssueBill2.FN_BIZDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBill2.class) + "." +ScmInvSaleIssueBill2.FN_BIZDATE, QueryParam.QUERY_LE,FormatUtils.fmtDate(scmInvSaleIssueBillAdvQuery.getBizDateTo())));
				}
				if(scmInvSaleIssueBillAdvQuery.getCreateDateFrom()!=null){
					if(scmInvSaleIssueBillAdvQuery.getCreateDateTo()!=null) {
						page.getParam().put(ScmInvSaleIssueBill2.FN_CREATEDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBill2.class) + "." +ScmInvSaleIssueBill2.FN_CREATEDATE, QueryParam.QUERY_BETWEEN,FormatUtils.fmtDate(scmInvSaleIssueBillAdvQuery.getCreateDateTo()),FormatUtils.fmtDate(CalendarUtil.relativeDate(scmInvSaleIssueBillAdvQuery.getCreateDateTo(),1))));
					}else {
						page.getParam().put(ScmInvSaleIssueBill2.FN_CREATEDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBill2.class) + "." +ScmInvSaleIssueBill2.FN_CREATEDATE, QueryParam.QUERY_GE,FormatUtils.fmtDate(scmInvSaleIssueBillAdvQuery.getCreateDateFrom())));
					}
				}else if(scmInvSaleIssueBillAdvQuery.getCreateDateTo()!=null) {
					page.getParam().put(ScmInvSaleIssueBill2.FN_CREATEDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBill2.class) + "." +ScmInvSaleIssueBill2.FN_CREATEDATE, QueryParam.QUERY_LE,FormatUtils.fmtDate(CalendarUtil.relativeDate(scmInvSaleIssueBillAdvQuery.getCreateDateTo(),1))));
				}
				if (scmInvSaleIssueBillAdvQuery.getClassType()>0) {
					List<ScmMaterialGroup> findChild = scmMaterialGroupBiz.findChild(scmInvSaleIssueBillAdvQuery.getClassType(), param);
					StringBuffer classType = new StringBuffer("0");
					if (findChild != null && !findChild.isEmpty()) {
						for (ScmMaterialGroup scmMaterialGroup : findChild) {
							if (StringUtils.isNotBlank(classType.toString())) {
								classType.append(",");
							}
							classType.append(scmMaterialGroup.getId());
						}
					}
					Page page2 = new Page();
					page2.setModelClass(ScmMaterial2.class);
					page2.setShowCount(Integer.MAX_VALUE);
					List<String> arglist = new ArrayList<>();
					arglist.add("groupList="+classType.toString());
					arglist.add("controlUnitNo="+param.getControlUnitNo());
					List<ScmMaterial2> scmmaterial = scmMaterialBiz.queryPage(page2, arglist, "findByGroupPage", param);
					StringBuffer itemid = new StringBuffer("0");
					if (scmmaterial != null && !scmmaterial.isEmpty()) {
						for (ScmMaterial2 scmMaterial2 : scmmaterial) {
							if (StringUtils.isNotBlank(itemid.toString())) {
								itemid.append(",");
							}
							itemid.append(scmMaterial2.getId());
						}
					}
					if(StringUtils.isNotBlank(page.getSqlCondition())){
						page.setSqlCondition((page.getSqlCondition()+"and scminvsaleissuebill.otId in(SELECT otId FROM scminvsaleissuebillentry where ItemId in("+itemid.toString()+"))"));	
					}else{
						page.setSqlCondition("scminvsaleissuebill.otId in(SELECT otId FROM scminvsaleissuebillentry where ItemId in("+itemid.toString()+"))");	
					}
				}
				if (scmInvSaleIssueBillAdvQuery.getSalesId()>0) {
					page.getParam().put(ScmInvSaleIssueBill2.FN_SALESID,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBill2.class) + "." +ScmInvSaleIssueBill2.FN_SALESID, QueryParam.QUERY_EQ,String.valueOf(scmInvSaleIssueBillAdvQuery.getSalesId())));
				}
				if (scmInvSaleIssueBillAdvQuery.getCustType()>0) {
					Page page2 = new Page();
					page2.setModelClass(modelClass);
					page2.setShowCount(Integer.MAX_VALUE);
					page2.getParam().put(Customer2.FN_FLAG,new QueryParam(ClassUtils.getFinalModelSimpleName(Customer2.class) + "." +Customer2.FN_FLAG, QueryParam.QUERY_EQ,"1"));
					page2.getParam().put(Customer2.FN_CUSTTYPE,new QueryParam(ClassUtils.getFinalModelSimpleName(Customer2.class) + "." +Customer2.FN_CUSTTYPE, QueryParam.QUERY_EQ,String.valueOf(scmInvSaleIssueBillAdvQuery.getCustType())));
					List<Customer2> customer2List = customerBiz.findPage(page2, param);
					StringBuffer classType = new StringBuffer("0");
					if (customer2List != null && !customer2List.isEmpty()) {
						for (Customer2 customer2 : customer2List) {
							if (StringUtils.isNotBlank(classType.toString())) {
								classType.append(",");
							}
							classType.append(customer2.getId());
						}
					}
					page.getParam().put(ScmInvSaleIssueBill2.FN_CUSTID,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBill2.class) + "." +ScmInvSaleIssueBill2.FN_CUSTID, QueryParam.QUERY_IN,classType.toString()));
				}
				String filterWarehouseByUsr = ScmWarehouseUtil.filterWarehouseByUsr("", param);
				if (StringUtils.isNotBlank(filterWarehouseByUsr)) {
					if(StringUtils.isNotBlank(page.getSqlCondition())){
						page.setSqlCondition((page.getSqlCondition()+" and ScmInvSaleIssueBill.otid in (Select otid From scminvsaleissuebillentry Where scminvsaleissuebillentry.wareHouseId in("+filterWarehouseByUsr+"))"));	
					}else{
						page.setSqlCondition("ScmInvSaleIssueBill.otid in (Select otid From scminvsaleissuebillentry Where scminvsaleissuebillentry.wareHouseId in("+filterWarehouseByUsr+"))");
					}
				}
			}
		}
	}

	@Override
	public List<ScmInvSaleIssueBill2> selectByPurInwareHouse(long wrId, Param param) throws AppException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("wrId", wrId);
		return ((ScmInvSaleIssueBillDAO)dao).selectByPurInwareHouse(map);
	}
	
	@Override
	public List<Map<String, Object>> countUnPostBill(HashMap<String, Object> map) {
		return ((ScmInvSaleIssueBillDAO)dao).countUnPostBill(map);
	}
	
	@Override
	public ScmInvSaleIssueBill2 doAddSaleIssueBill(InvSaleIssueBillAddParams invSaleIssueBillAddParams, Param param)
			throws AppException {
		CommonBean bean = new CommonBean();
        List<ScmInvSaleIssueBill2> scmInvSaleIssueBillList = new ArrayList<>();
        List<ScmInvSaleIssueBillEntry2> scmInvSaleIssueBillEntryList = new ArrayList<>();
        ScmInvSaleIssueBill2 scmInvSaleIssueBill = new ScmInvSaleIssueBill2(true);
        try {
        	//销售出库主表
			BeanUtils.copyProperties(invSaleIssueBillAddParams, scmInvSaleIssueBill);
			Page page = new Page();
			page.setModelClass(OrgCompany2.class);
			page.setShowCount(Integer.MAX_VALUE);
			List<String> arglist = new ArrayList<>();
			arglist.add("type=to");
			arglist.add("relationType="+OrgUnitRelationType.INVTOFIN);
			arglist.add("fromOrgUnitNo="+invSaleIssueBillAddParams.getInvOrgUnitNo());
			List<OrgCompany2> orgCompanyList = orgCompanyBiz.queryPage(page, arglist, "queryPageEx", param);
			if(orgCompanyList==null || orgCompanyList.isEmpty()){
				throw new AppException("iscm.inventorymanage.common.invOrgUnit.notfinorg");
			}
			scmInvSaleIssueBill.setFinOrgUnitNo(orgCompanyList.get(0).getOrgUnitNo());
			//客户
            HashMap<String, Object> custIdMap = new HashMap<String, Object>();
            custIdMap.put(Customer2.FN_FLAG, 1);
            custIdMap.put(Customer2.FN_ORGUNITNO, invSaleIssueBillAddParams.getInvOrgUnitNo());
            custIdMap.put(Customer2.FN_CONTROLUNITNO, param.getControlUnitNo());
            List<Customer2> customerList = customerBiz.findAll(custIdMap, param);
            if (customerList == null || customerList.isEmpty()) {
                throw new AppException("iscm.purchasemanage.purchasebusiness.service.impl.ScmPurOrderBizImpl.generateSaleOrder.noCustId");
            }
	        scmInvSaleIssueBill.setCustId(customerList.get(0).getId());
			scmInvSaleIssueBill.setStatus("I");
			scmInvSaleIssueBill.setOrgUnitNo(invSaleIssueBillAddParams.getInvOrgUnitNo());
			scmInvSaleIssueBill.setCreateDate(CalendarUtil.getDate(param));
			scmInvSaleIssueBill.setCreator(param.getUsrCode());
			if(scmInvSaleIssueBill.getBizDate() != null){
				scmInvSaleIssueBill.setBizDate(FormatUtils.parseDateTime(FormatUtils.fmtDate(scmInvSaleIssueBill.getBizDate()) + " 00:00:00"));
			}
	        scmInvSaleIssueBill.setBizType("1");
	        scmInvSaleIssueBill.setCreateOrgUnitNo(param.getOrgUnitNo());
	        scmInvSaleIssueBill.setControlUnitNo(param.getControlUnitNo());
	        scmInvSaleIssueBill.setFinOrgUnitNo(orgCompanyList.get(0).getOrgUnitNo());
	        scmInvSaleIssueBillList.add(scmInvSaleIssueBill);
	        bean.setList(scmInvSaleIssueBillList);
	        int amtPrecision = Integer.parseInt(sysParamBiz.getValue(param.getOrgUnitNo(), "SCM_AmtPrecision", "2", param));
	        // 销售出库明细表
	        List<InvSaleIssueBillDetailParams> detailList = invSaleIssueBillAddParams.getDetailList();
	        List<ScmInvSaleIssueBillEntry2> msgList = new ArrayList<>();
			if(detailList!=null && !detailList.isEmpty()){
				StringBuffer itemNos = new StringBuffer("");
				for(InvSaleIssueBillDetailParams detailParams:detailList){
					if(StringUtils.isNotBlank(itemNos.toString()))
						itemNos.append(",");
					itemNos.append("'").append(detailParams.getItemNo()).append("'");
				}
				List<ScmMaterialPrice> scmMaterialPriceList = scmInvSalePriceBiz.getPrice(scmInvSaleIssueBill.getOrgUnitNo(),
						itemNos.toString(), scmInvSaleIssueBill.getBizDate(),param);
				for (int i = 0; i < detailList.size(); i++) {
					ScmInvSaleIssueBillEntry2 scmInvSaleIssueBillEntry = new ScmInvSaleIssueBillEntry2(true);
					boolean existStock = false;
					BeanUtils.copyProperties(detailList.get(i), scmInvSaleIssueBillEntry);
					ScmMaterial2 scmMaterial = scmMaterialBiz.selectByItemNo(param.getControlUnitNo(), scmInvSaleIssueBillEntry.getItemNo(), param);
					if(scmMaterial == null){
						scmInvSaleIssueBillEntry.setResultCode("-1");
						scmInvSaleIssueBillEntry.setResultText(Message.getMessage(param.getLang(), "iscm.inventorymanage.scmInvMaterialReqBill.esp.itemNotExist", new String[] {scmInvSaleIssueBillEntry.getItemNo()}));
						msgList.add(scmInvSaleIssueBillEntry);
						continue;
					}
					ScmInvWareHouse scmInvWareHouse = null;
					if(StringUtils.isNotBlank(detailList.get(i).getWareHouseNo())){
						scmInvWareHouse = scmInvWareHouseBiz.selectByWhNo(scmInvSaleIssueBill.getOrgUnitNo(), detailList.get(i).getWareHouseNo(), param);
						if(scmInvWareHouse == null){
							scmInvSaleIssueBillEntry.setResultCode("-1");
							scmInvSaleIssueBillEntry.setResultText(Message.getMessage(param.getLang(), "iscm.inventorymanage.warehouseoutbusiness.ScmInvSaleIssueBillBizImpl.doAddSaleIssueBill.warehouseisnull", new String[] {scmMaterial.getItemName(), detailList.get(i).getWareHouseNo()}));
							msgList.add(scmInvSaleIssueBillEntry);
							continue;
						}else{
							scmInvSaleIssueBillEntry.setWareHouseId(scmInvWareHouse.getId());
						}
					}
					scmInvSaleIssueBillEntry.setOrgUnitNo(scmInvSaleIssueBill.getOrgUnitNo());
					scmInvSaleIssueBillEntry.setItemId(scmMaterial.getId());
					scmInvSaleIssueBillEntry.setBaseUnit(scmMaterial.getBaseUnitId());
					scmInvSaleIssueBillEntry.setPieUnit(scmMaterial.getPieUnitId());
					Page stockPage = new Page();
					stockPage.setModelClass(ScmInvStock2.class);
					stockPage.setShowCount(Integer.MAX_VALUE);
					if(StringUtils.isNotBlank(scmInvSaleIssueBillEntry.getLot())){
						stockPage.setSqlCondition("scminvstock.lot='"+scmInvSaleIssueBillEntry.getLot()+"'");
					}
					ArrayList stockArgList = new ArrayList();
					stockArgList.add("orgUnitNo="+scmInvSaleIssueBill.getOrgUnitNo());
					stockArgList.add("bizDate="+ FormatUtils.fmtDate(scmInvSaleIssueBill.getBizDate()));
					stockArgList.add("costCenter=0");
					stockArgList.add("wareHouseId="+scmInvSaleIssueBillEntry.getWareHouseId());
					stockArgList.add("itemId=" + scmMaterial.getId());
			        List<ScmInvStock2> scmInvStockList = scmInvStockBiz.queryPage(stockPage, stockArgList, "selectSaleIssueLot", param);
		            if (scmInvStockList != null && !scmInvStockList.isEmpty()) {
		            	for(ScmInvStock2 scmInvStock:scmInvStockList) {
		            		if(scmInvStock.getQty().compareTo(scmInvSaleIssueBillEntry.getQty())>=0) {
		            			existStock = true;
		    		            scmInvSaleIssueBillEntry.setUnit(scmInvStock.getUnit());
		    		            BigDecimal convRate = ScmMaterialUtil.getConvRate(scmInvSaleIssueBillEntry.getItemId(), scmInvSaleIssueBillEntry.getUnit(), param);
		    		            scmInvSaleIssueBillEntry.setBaseQty(scmInvSaleIssueBillEntry.getQty().multiply(convRate));
		    		            scmInvSaleIssueBillEntry.setPrice(scmInvStock.getPrice());
		    					scmInvSaleIssueBillEntry.setTaxPrice(scmInvStock.getTaxPrice());
		    					scmInvSaleIssueBillEntry.setAmt(scmInvSaleIssueBillEntry.getQty().multiply(scmInvSaleIssueBillEntry.getPrice()).setScale(amtPrecision, RoundingMode.HALF_UP));
		    					scmInvSaleIssueBillEntry.setTaxAmt(scmInvSaleIssueBillEntry.getQty().multiply(scmInvSaleIssueBillEntry.getTaxPrice()).setScale(amtPrecision, RoundingMode.HALF_UP));
		    					if(StringUtils.isNotBlank(scmInvSaleIssueBillEntry.getLot())){
		    						scmInvSaleIssueBillEntry.setTaxRate(scmInvStock.getTaxRate());
		    					}
		    					scmInvSaleIssueBillEntry.setSaleTaxRate(BigDecimal.ZERO);
		    					scmInvSaleIssueBillEntry.setSaleQty(scmInvSaleIssueBillEntry.getQty());
		    					if(scmInvSaleIssueBillEntry.getSaleTaxPrice() == null){
		    						scmInvSaleIssueBillEntry.setSaleTaxPrice(BigDecimal.ZERO);
		    						if(scmMaterialPriceList != null && !scmMaterialPriceList.isEmpty()){
			    						for(ScmMaterialPrice scmMaterialPrice : scmMaterialPriceList){
			    							if(scmMaterialPrice.getItemId() == scmInvSaleIssueBillEntry.getItemId()){
			    								scmInvSaleIssueBillEntry.setSaleTaxPrice(scmMaterialPrice.getTaxPrice());
			    								//scmInvSaleIssueBillEntry.setSaleTaxRate(scmMaterialPrice.getTaxRate());
			    							}
			    						}
			    					}
		    					}
		    					scmInvSaleIssueBillEntry.setSaleTaxAmt(scmInvSaleIssueBillEntry.getSaleQty().multiply(scmInvSaleIssueBillEntry.getSaleTaxPrice()).setScale(amtPrecision, RoundingMode.HALF_UP));
		    		            scmInvSaleIssueBillEntry.setBalanceCustId(scmInvSaleIssueBill.getCustId());
		                        break;
		            		}
		            	}
		            }
		            if(existStock){
		            	scmInvSaleIssueBillEntryList.add(scmInvSaleIssueBillEntry);
		            	scmInvSaleIssueBillEntry.setResultCode("0");
		            	scmInvSaleIssueBillEntry.setResultText("");
					}else{
						scmInvSaleIssueBillEntry.setResultCode("-1");
						if(StringUtils.isBlank(scmInvSaleIssueBillEntry.getLot())){
							scmInvSaleIssueBillEntry.setResultText(Message.getMessage(param.getLang(), "iscm.inventorymanage.warehouseoutbusiness.ScmInvSaleIssueBillBizImpl.doAddSaleIssueBill.stockisnull", new String[] {scmInvWareHouse.getWhName(), scmMaterial.getItemName()}));
						}else{
							scmInvSaleIssueBillEntry.setResultText(Message.getMessage(param.getLang(), "iscm.inventorymanage.warehouseoutbusiness.ScmInvSaleIssueBillBizImpl.doAddSaleIssueBill.stocklotisnull", new String[] {scmInvWareHouse.getWhName(), scmInvSaleIssueBillEntry.getLot(), scmMaterial.getItemName()}));
						}
					}
					msgList.add(scmInvSaleIssueBillEntry);
		        }
			}
			scmInvSaleIssueBill.setScmInvSaleIssueBillEntryList(msgList);
			if(scmInvSaleIssueBillEntryList == null || scmInvSaleIssueBillEntryList.isEmpty()){
				throw new AppException("iscm.inventorymanage.warehouseoutbusiness.ScmInvSaleIssueBillBizImpl.doAddSaleIssueBill.itemAllNotExist");
			}
	        bean.setList2(scmInvSaleIssueBillEntryList);
	        this.add(bean, param);
		} catch (Exception e) {
			throw e;
		}
		return scmInvSaleIssueBill;
	}

	@Override
	public ScmInvSaleIssueBill2 queryInvSaleIssueBill(ScmInvSaleIssueBill2 scmInvSaleIssueBill, Param param)
			throws AppException {
		Page page=new Page();
		page.setModelClass(ScmInvSaleIssueBill2.class);
		page.setShowCount(Integer.MAX_VALUE);
		page.getParam().put(ScmInvSaleIssueBill2.FN_OTHERNO,new QueryParam(ScmInvSaleIssueBill2.FN_OTHERNO, QueryParam.QUERY_EQ, scmInvSaleIssueBill.getOtherNo()));
		
		List<ScmInvSaleIssueBill2> scmInvSaleIssueBillList =this.findPage(page, param);
		ScmInvSaleIssueBill2 scmInvSaleIssueBill2 = new ScmInvSaleIssueBill2();
		if(scmInvSaleIssueBillList!=null && !scmInvSaleIssueBillList.isEmpty()){
			scmInvSaleIssueBill2 = scmInvSaleIssueBillList.get(0);
		}else{
			throw new AppException("iscm.purchasemanage.pricemanage.find.ordernotexists");
		}
		return scmInvSaleIssueBill2;
	}

	@Override
	public ScmInvSaleIssueBill2 doAddSaleIssueReturnBill(
			InvSaleIssueReturnBillAddParams invSaleIssueReturnBillAddParams, Param param) throws AppException {
		CommonBean bean = new CommonBean();
        List<ScmInvSaleIssueBill2> scmInvSaleIssueBillList = new ArrayList<>();
        List<ScmInvSaleIssueBillEntry2> scmInvSaleIssueBillEntryList = new ArrayList<>();
        ScmInvSaleIssueBill2 scmInvSaleIssueBill = new ScmInvSaleIssueBill2(true);
        try {
        	//销售出库主表
			BeanUtils.copyProperties(invSaleIssueReturnBillAddParams, scmInvSaleIssueBill);
			Page page = new Page();
			page.setModelClass(OrgCompany2.class);
			page.setShowCount(Integer.MAX_VALUE);
			List<String> arglist = new ArrayList<>();
			arglist.add("type=to");
			arglist.add("relationType="+OrgUnitRelationType.INVTOFIN);
			arglist.add("fromOrgUnitNo="+invSaleIssueReturnBillAddParams.getInvOrgUnitNo());
			List<OrgCompany2> orgCompanyList = orgCompanyBiz.queryPage(page, arglist, "queryPageEx", param);
			if(orgCompanyList==null || orgCompanyList.isEmpty()){
				throw new AppException("iscm.inventorymanage.common.invOrgUnit.notfinorg");
			}
			scmInvSaleIssueBill.setFinOrgUnitNo(orgCompanyList.get(0).getOrgUnitNo());
			//客户
            HashMap<String, Object> custIdMap = new HashMap<String, Object>();
            custIdMap.put(Customer2.FN_FLAG, 1);
            custIdMap.put(Customer2.FN_ORGUNITNO, invSaleIssueReturnBillAddParams.getInvOrgUnitNo());
            custIdMap.put(Customer2.FN_CONTROLUNITNO, param.getControlUnitNo());
            List<Customer2> customerList = customerBiz.findAll(custIdMap, param);
            if (customerList == null || customerList.isEmpty()) {
                throw new AppException("iscm.purchasemanage.purchasebusiness.service.impl.ScmPurOrderBizImpl.generateSaleOrder.noCustId");
            }
	        scmInvSaleIssueBill.setCustId(customerList.get(0).getId());
			scmInvSaleIssueBill.setStatus("I");
			scmInvSaleIssueBill.setOrgUnitNo(invSaleIssueReturnBillAddParams.getInvOrgUnitNo());
			scmInvSaleIssueBill.setCreateDate(CalendarUtil.getDate(param));
			scmInvSaleIssueBill.setCreator(param.getUsrCode());
			if(scmInvSaleIssueBill.getBizDate() != null){
				scmInvSaleIssueBill.setBizDate(FormatUtils.parseDateTime(FormatUtils.fmtDate(scmInvSaleIssueBill.getBizDate()) + " 00:00:00"));
			}
	        scmInvSaleIssueBill.setBizType("6");
	        scmInvSaleIssueBill.setCreateOrgUnitNo(param.getOrgUnitNo());
	        scmInvSaleIssueBill.setControlUnitNo(param.getControlUnitNo());
	        scmInvSaleIssueBill.setFinOrgUnitNo(orgCompanyList.get(0).getOrgUnitNo());
	        scmInvSaleIssueBillList.add(scmInvSaleIssueBill);
	        bean.setList(scmInvSaleIssueBillList);
	        int amtPrecision = Integer.parseInt(sysParamBiz.getValue(param.getOrgUnitNo(), "SCM_AmtPrecision", "2", param));
	        // 销售出库明细表
	        List<InvSaleIssueBillDetailParams> detailList = invSaleIssueReturnBillAddParams.getDetailList();
	        List<ScmInvSaleIssueBillEntry2> msgList = new ArrayList<>();
			if(detailList!=null && !detailList.isEmpty()){
				StringBuffer itemNos = new StringBuffer("");
				for(InvSaleIssueBillDetailParams detailParams:detailList){
					if(StringUtils.isNotBlank(itemNos.toString()))
						itemNos.append(",");
					itemNos.append("'").append(detailParams.getItemNo()).append("'");
				}
				List<ScmMaterialPrice> scmMaterialPriceList = scmInvSalePriceBiz.getPrice(scmInvSaleIssueBill.getOrgUnitNo(),
						itemNos.toString(), scmInvSaleIssueBill.getBizDate(),param);
				for (int i = 0; i < detailList.size(); i++) {
					ScmInvSaleIssueBillEntry2 scmInvSaleIssueBillEntry = new ScmInvSaleIssueBillEntry2(true);
					boolean existStock = false;
					BeanUtils.copyProperties(detailList.get(i), scmInvSaleIssueBillEntry);
					ScmMaterial2 scmMaterial = scmMaterialBiz.selectByItemNo(param.getControlUnitNo(), scmInvSaleIssueBillEntry.getItemNo(), param);
					if(scmMaterial == null){
						scmInvSaleIssueBillEntry.setResultCode("-1");
						scmInvSaleIssueBillEntry.setResultText(Message.getMessage(param.getLang(), "iscm.inventorymanage.scmInvMaterialReqBill.esp.itemNotExist", new String[] {scmInvSaleIssueBillEntry.getItemNo()}));
						msgList.add(scmInvSaleIssueBillEntry);
						continue;
					}
					ScmInvWareHouse scmInvWareHouse = null;
					if(StringUtils.isNotBlank(detailList.get(i).getWareHouseNo())){
						scmInvWareHouse = scmInvWareHouseBiz.selectByWhNo(scmInvSaleIssueBill.getOrgUnitNo(), detailList.get(i).getWareHouseNo(), param);
						if(scmInvWareHouse == null){
							scmInvSaleIssueBillEntry.setResultCode("-1");
							scmInvSaleIssueBillEntry.setResultText(Message.getMessage(param.getLang(), "iscm.inventorymanage.warehouseoutbusiness.ScmInvSaleIssueBillBizImpl.doAddSaleIssueBill.warehouseisnull", new String[] {scmMaterial.getItemName(), detailList.get(i).getWareHouseNo()}));
							msgList.add(scmInvSaleIssueBillEntry);
							continue;
						}else{
							scmInvSaleIssueBillEntry.setWareHouseId(scmInvWareHouse.getId());
						}
					}
					scmInvSaleIssueBillEntry.setOrgUnitNo(scmInvSaleIssueBill.getOrgUnitNo());
					scmInvSaleIssueBillEntry.setItemId(scmMaterial.getId());
					scmInvSaleIssueBillEntry.setBaseUnit(scmMaterial.getBaseUnitId());
					scmInvSaleIssueBillEntry.setPieUnit(scmMaterial.getPieUnitId());
					Page stockPage = new Page();
					stockPage.setModelClass(ScmInvStock2.class);
					stockPage.setShowCount(Integer.MAX_VALUE);
					if(StringUtils.isNotBlank(scmInvSaleIssueBillEntry.getLot())){
						stockPage.setSqlCondition("scminvstock.lot='"+scmInvSaleIssueBillEntry.getLot()+"'");
					}
					ArrayList stockArgList = new ArrayList();
					stockArgList.add("orgUnitNo="+scmInvSaleIssueBill.getOrgUnitNo());
					stockArgList.add("bizDate="+ FormatUtils.fmtDate(scmInvSaleIssueBill.getBizDate()));
					stockArgList.add("costCenter=0");
					stockArgList.add("wareHouseId="+scmInvSaleIssueBillEntry.getWareHouseId());
					stockArgList.add("itemId=" + scmMaterial.getId());
			        List<ScmInvStock2> scmInvStockList = scmInvStockBiz.queryPage(stockPage, stockArgList, "selectSaleIssueLot", param);
		            if (scmInvStockList != null && !scmInvStockList.isEmpty()) {
		            	for(ScmInvStock2 scmInvStock:scmInvStockList) {
		            		if(scmInvStock.getQty().compareTo(BigDecimal.ZERO)>=0) {
		            			existStock = true;
		    		            scmInvSaleIssueBillEntry.setUnit(scmInvStock.getUnit());
		    		            BigDecimal convRate = ScmMaterialUtil.getConvRate(scmInvSaleIssueBillEntry.getItemId(), scmInvSaleIssueBillEntry.getUnit(), param);
		    		            scmInvSaleIssueBillEntry.setBaseQty(scmInvSaleIssueBillEntry.getQty().multiply(convRate));
		    		            scmInvSaleIssueBillEntry.setPrice(scmInvStock.getPrice());
		    					scmInvSaleIssueBillEntry.setTaxPrice(scmInvStock.getTaxPrice());
		    					scmInvSaleIssueBillEntry.setAmt(scmInvSaleIssueBillEntry.getQty().multiply(scmInvSaleIssueBillEntry.getPrice()).setScale(amtPrecision, RoundingMode.HALF_UP));
		    					scmInvSaleIssueBillEntry.setTaxAmt(scmInvSaleIssueBillEntry.getQty().multiply(scmInvSaleIssueBillEntry.getTaxPrice()).setScale(amtPrecision, RoundingMode.HALF_UP));
		    					if(StringUtils.isNotBlank(scmInvSaleIssueBillEntry.getLot())){
		    						scmInvSaleIssueBillEntry.setTaxRate(scmInvStock.getTaxRate());
		    					}
		    					scmInvSaleIssueBillEntry.setSaleTaxRate(BigDecimal.ZERO);
		    					scmInvSaleIssueBillEntry.setSaleQty(scmInvSaleIssueBillEntry.getQty());
		    					if(scmInvSaleIssueBillEntry.getSaleTaxPrice() == null){
		    						scmInvSaleIssueBillEntry.setSaleTaxPrice(BigDecimal.ZERO);
		    						if(scmMaterialPriceList != null && !scmMaterialPriceList.isEmpty()){
			    						for(ScmMaterialPrice scmMaterialPrice : scmMaterialPriceList){
			    							if(scmMaterialPrice.getItemId() == scmInvSaleIssueBillEntry.getItemId()){
			    								scmInvSaleIssueBillEntry.setSaleTaxPrice(scmMaterialPrice.getTaxPrice());
			    								//scmInvSaleIssueBillEntry.setSaleTaxRate(scmMaterialPrice.getTaxRate());
			    							}
			    						}
			    					}
		    					}
		    					scmInvSaleIssueBillEntry.setSaleTaxAmt(scmInvSaleIssueBillEntry.getSaleQty().multiply(scmInvSaleIssueBillEntry.getSaleTaxPrice()).setScale(amtPrecision, RoundingMode.HALF_UP));
		    		            scmInvSaleIssueBillEntry.setBalanceCustId(scmInvSaleIssueBill.getCustId());
		                        break;
		            		}
		            	}
		            }
		            if(existStock){
		            	scmInvSaleIssueBillEntryList.add(scmInvSaleIssueBillEntry);
		            	scmInvSaleIssueBillEntry.setResultCode("0");
		            	scmInvSaleIssueBillEntry.setResultText("");
					}else{
						scmInvSaleIssueBillEntry.setResultCode("-1");
						if(StringUtils.isBlank(scmInvSaleIssueBillEntry.getLot())){
							scmInvSaleIssueBillEntry.setResultText(Message.getMessage(param.getLang(), "iscm.inventorymanage.warehouseoutbusiness.ScmInvSaleIssueBillBizImpl.doAddSaleIssueBill.stockisnull", new String[] {scmInvWareHouse.getWhName(), scmMaterial.getItemName()}));
						}else{
							scmInvSaleIssueBillEntry.setResultText(Message.getMessage(param.getLang(), "iscm.inventorymanage.warehouseoutbusiness.ScmInvSaleIssueBillBizImpl.doAddSaleIssueBill.stocklotisnull", new String[] {scmInvWareHouse.getWhName(), scmInvSaleIssueBillEntry.getLot(), scmMaterial.getItemName()}));
						}
					}
					msgList.add(scmInvSaleIssueBillEntry);
		        }
			}
			scmInvSaleIssueBill.setScmInvSaleIssueBillEntryList(msgList);
			if(scmInvSaleIssueBillEntryList == null || scmInvSaleIssueBillEntryList.isEmpty()){
				throw new AppException("iscm.inventorymanage.warehouseoutbusiness.ScmInvSaleIssueBillBizImpl.doAddSaleIssueBill.itemAllNotExist");
			}
	        bean.setList2(scmInvSaleIssueBillEntryList);
	        this.add(bean, param);
		} catch (Exception e) {
			throw e;
		}
		return scmInvSaleIssueBill;
	}

	@Override
	public CommonBean generateArInvoice(ScmDataCollectionStepState2 stepState,final List<ScmInvSaleIssueBill2> scmInvSaleIssueBillList, final Param param)
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
					List<Arinvoice2> rtnList = asynGenerateArInvoice(scmInvSaleIssueBillList, param);
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
	private List<Arinvoice2> asynGenerateArInvoice(List<ScmInvSaleIssueBill2> scmInvSaleIssueBillList,Param param){
		List<Arinvoice2> rtnList = new ArrayList();
		if(scmInvSaleIssueBillList!=null && !scmInvSaleIssueBillList.isEmpty()) {
			OperationParam genParam = new OperationParam();
			BeanUtils.copyProperties(param, genParam);
			genParam.setOrgUnitNo(scmInvSaleIssueBillList.get(0).getFinOrgUnitNo());
			for(ScmInvSaleIssueBill2 scmInvSaleIssueBill:scmInvSaleIssueBillList) {
				scmInvSaleIssueBill = this.selectDirect(scmInvSaleIssueBill.getOtId(), param);
				if(StringUtils.equals(scmInvSaleIssueBill.getStatus(), "E") && !scmInvSaleIssueBill.isBuildAr()) {
					List<ScmInvSaleIssueBillEntry2> scmInvSaleIssueBillEntryList = scmInvSaleIssueBillEntryBiz.selectByOtId(scmInvSaleIssueBill.getOtId(), param);
					if(scmInvSaleIssueBillEntryList!=null && !scmInvSaleIssueBillEntryList.isEmpty()) {
						SaleIssueBill saleIssueBill = new SaleIssueBill();
						BeanUtils.copyProperties(scmInvSaleIssueBill,saleIssueBill);
						List<SaleIssueBillEntry> saleIssueBillEntryList = new ArrayList();
						saleIssueBill.setSaleIssueBillEntryList(saleIssueBillEntryList);
						for(ScmInvSaleIssueBillEntry2 scmInvSaleIssueBillEntry:scmInvSaleIssueBillEntryList) {
							SaleIssueBillEntry saleIssueBillEntry = new SaleIssueBillEntry();
							BeanUtils.copyProperties(scmInvSaleIssueBillEntry,saleIssueBillEntry);
							saleIssueBillEntryList.add(saleIssueBillEntry);
						}
						rtnList.add(arinvoiceBiz.generateArInvoice(saleIssueBill, genParam));
						scmInvSaleIssueBill.setBuildAr(true);
						this.updateDirect(scmInvSaleIssueBill, param);
					}
				}
			}
		}
		return rtnList;
	}

	@Override
	public ScmInvSaleIssueBill2 updatePrtCount(ScmInvSaleIssueBill2 scmInvSaleIssueBill, Param param)
			throws AppException {
		if(scmInvSaleIssueBill.getOtId()>0){
			ScmInvSaleIssueBill2 bill = this.selectDirect(scmInvSaleIssueBill.getOtId(), param);
			if(bill != null){
				int prtCount = bill.getPrtcount();
				bill.setPrtcount((prtCount+1));
				this.updateDirect(bill, param);
				return bill;
			}
		}
		return scmInvSaleIssueBill;
	}

	@Override
	public ScmInvSaleIssueBill2 doAudit(CommonAuditParams commonAuditParams, Param param) throws AppException {
		ScmInvSaleIssueBill2 invSaleOrder = null;
		
		ScmInvSaleIssueBill2 scmInvSaleOrder= new ScmInvSaleIssueBill2();
		scmInvSaleOrder.setOtId(commonAuditParams.getBillId());
		scmInvSaleOrder.setOtNo(commonAuditParams.getBillNo());
		String opinion = commonAuditParams.getOpinion();
		if(StringUtils.equals("Y", opinion) || StringUtils.equals("agree", opinion)) {
			opinion="agree";
		}else if(StringUtils.equals("N", opinion) || StringUtils.equals("refuse", opinion)) {
			opinion="refuse";
		}	
		if(scmInvSaleOrder.getOtId()>0){
			invSaleOrder = this.selectDirect(scmInvSaleOrder.getOtId(), param);
		}else{
			HashMap<String,Object> map=new HashMap<String,Object>();
			map.put(ScmInvSaleOrder.FN_OTNO,new QueryParam(ScmInvSaleOrder.FN_OTNO, QueryParam.QUERY_EQ,scmInvSaleOrder.getOtNo()));
			map.put(ScmInvSaleOrder.FN_CONTROLUNITNO, new QueryParam(ScmInvSaleOrder.FN_CONTROLUNITNO, QueryParam.QUERY_EQ,param.getControlUnitNo()));
			List<ScmInvSaleIssueBill2> scmInvSaleOrderList =this.findAll(map, param);
			
			if(scmInvSaleOrderList!=null && !scmInvSaleOrderList.isEmpty()){
				invSaleOrder = scmInvSaleOrderList.get(0);
			}
		}
		
		if (invSaleOrder != null) {
			this.setConvertMap(invSaleOrder, param);
			if(!(invSaleOrder.getStatus().equals("D") || invSaleOrder.getStatus().equals("P"))){
				throw new AppException("iscm.purchasemanage.error.audit");
			}
			if(StringUtils.equals("R", opinion)) {
				scmBillPendingBiz.deletePendingBill(param.getUsrCode(),invSaleOrder, param);
				commonEventHistoryBiz.updateInvalid(invSaleOrder,"",param);
				
				CommonAuditOpinion commonAuditOpinionR = new CommonAuditOpinion();
				commonAuditOpinionR.setPreStepNo(invSaleOrder.getStepNo());
				String stepNo = StringUtils.replace(UUID.randomUUID().toString(), "-", "");
				commonAuditOpinionR.setStepNo(stepNo);
				commonAuditOpinionR.setActiveType("A");
				commonAuditOpinionR.setOpinion(commonAuditParams.getOpinion());
				commonAuditOpinionR.setRemarks(commonAuditParams.getOpinionRemarks());
				commonEventHistoryBiz.addEventHistory(invSaleOrder, commonAuditOpinionR, param);
				
				//驳回则将单据变回新单状态
				invSaleOrder.setStatus("I");
				invSaleOrder.setChecker(null);
				invSaleOrder.setCheckDate(null);
				return this.updateDirect(invSaleOrder, param);
			}
			String processInstanceId = invSaleOrder.getWorkFlowNo();
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
					invSaleOrder.setStatus("A");
				} else {
					invSaleOrder.setStatus("P");
				}
			} else {
				invSaleOrder.setStatus("N");
			}
			scmBillPendingBiz.updatePendingBill(param.getUsrCode(), invSaleOrder, param);
			invSaleOrder.setChecker(param.getUsrCode());
			if ("agree".equals(opinion)) {
				String usrList= ActivityUtil.findAssigneeByProcessInstanceId(processInstanceId,param);
				if(StringUtils.isNotBlank(usrList)) {
					scmBillPendingBiz.addPendingBill(usrList, invSaleOrder, param);
					InvSaleOrderParams invSaleOrderParams = new InvSaleOrderParams();
					invSaleOrderParams.setOtNo(invSaleOrder.getOtNo());
					AuditMsgUtil.sendAuditMsg("InvSaleIssueBill",invSaleOrder,invSaleOrderParams, usrList, param);
				}
			}
			CommonAuditOpinion commonAuditOpinion = new CommonAuditOpinion();
			commonAuditOpinion.setPreStepNo(invSaleOrder.getStepNo());
			String stepNo = StringUtils.replace(UUID.randomUUID().toString(), "-", "");
			try {
				invSaleOrder.setStepNo(stepNo);
				invSaleOrder.setCheckDate(CalendarUtil.getDate(param));
				this.updateStatus(invSaleOrder, param);
			} catch (Exception e) {
				throw e;
			}
			commonAuditOpinion.setStepNo(stepNo);
			commonAuditOpinion.setActiveType("A");
			commonAuditOpinion.setOpinion(commonAuditParams.getOpinion());
			commonAuditOpinion.setRemarks(commonAuditParams.getOpinionRemarks());
			commonEventHistoryBiz.addEventHistory(invSaleOrder, commonAuditOpinion, param);
			if(StringUtils.equals("A", invSaleOrder.getStatus())) {
				//通过或部分通过时检查是否自动过帐
				List<OrgCompany2> orgCompanyList = orgUnitRelationBiz.findToOrgUnitByType(OrgUnitRelationType.INVTOFIN, invSaleOrder.getOrgUnitNo(), false, null, param);
				if(orgCompanyList==null || orgCompanyList.isEmpty()){
					throw new AppException("iscm.purchasemanage.purchasebusiness.ScmPurRequireBizImpl.error.notfin");
				}	
				BillType2 billType = billTypeBiz.selectByOrgAndCode(orgCompanyList.get(0).getOrgUnitNo(), "InvSaleIssueBill", param);
				if(billType!=null && billType.isAutoRelease()) {
					List<String> msgList = this.postBillCheck(invSaleOrder, param);
					if (msgList != null && !msgList.isEmpty()) {
						StringBuilder detailInfo = new StringBuilder("");
                        detailInfo.append(Message.getMessage(param.getLang(),"iscm.inventorymanage.saleissuesbill.post.errorTitle"));

						for (String str : msgList) {
                            detailInfo.append(str).append("\n");
                        }
						
						throw new AppException(detailInfo.toString(), new String[]{invSaleOrder.getOtNo()});
					}
					this.postBill(invSaleOrder, param);
				}
			}
		} else {
			throw new AppException("iscm.purchasemanage.purchasebusiness.find.ordernotexists");
		}
		
		return invSaleOrder;
	}

	@Override
	public ScmInvSaleIssueBill2 doUnAudit(ScmInvSaleIssueBill2 scmInvSaleIssueBill, Param param) throws AppException {
		ScmInvSaleIssueBill2 invSaleOrder = null;
		List<ScmInvSaleIssueBill2> scmInvSaleOrderList = new ArrayList<> ();
		List<ScmInvSaleIssueBillEntry2> scmInvSaleOrderEntryList = new ArrayList<> ();
		boolean isFirstTask = false;
		
		if(scmInvSaleIssueBill.getOtId()>0){
			invSaleOrder = this.selectDirect(scmInvSaleIssueBill.getOtId(), param);
		}else{
			HashMap<String,Object> map=new HashMap<String,Object>();
			map.put(ScmInvSaleIssueBill2.FN_OTNO,new QueryParam(ScmInvSaleIssueBill2.FN_OTNO, QueryParam.QUERY_EQ,scmInvSaleIssueBill.getOtNo()));
			map.put(ScmInvSaleOrder.FN_CONTROLUNITNO, new QueryParam(ScmInvSaleOrder.FN_CONTROLUNITNO, QueryParam.QUERY_EQ,param.getControlUnitNo()));
			
			scmInvSaleOrderList =this.findAll(map, param);
			if(scmInvSaleOrderList!=null && !scmInvSaleOrderList.isEmpty()){
				invSaleOrder=scmInvSaleOrderList.get(0);
			}
		}
		
		if (invSaleOrder != null) {
			if(!StringUtils.contains("A,B,N,P", invSaleOrder.getStatus())){
				throw new AppException("iscm.purchasemanage.unaudit.error.status");
			}
			String processInstanceId = invSaleOrder.getWorkFlowNo();
			scmInvSaleOrderEntryList = scmInvSaleIssueBillEntryBiz.selectByOtId(invSaleOrder.getOtId(), param);
			if(scmInvSaleOrderEntryList == null || scmInvSaleOrderEntryList.isEmpty()){
				//抛出异常处理
				throw new AppException("iscm.inventorymanage.AllocationApplication.service.Impl.ScmInvMaterialRequestBillBizImpl.generateMaterialReqBill.notExitMaterialRequestBillDetail");
			}
			
			if (processInstanceId != null && !"".equals(processInstanceId)) {
				//反审批当前已审节点
				CommonBean bean = new CommonBean();
				scmInvSaleOrderList.add(invSaleOrder);
				bean.setList(scmInvSaleOrderList);
				bean.setList2(scmInvSaleOrderEntryList);
				
				BillType2 billType = billTypeBiz.selectByOrgAndCode(invSaleOrder.getFinOrgUnitNo(), "InvSaleIssueBill", param);
				long billTypeId = 0;
				if (billType!=null && billType.isNeedAudit()) {
					billTypeId = billType.getId();
				}
				
				processInstanceId = ActivityUtil.antiAuditProcess(bean, billTypeId, processInstanceId, param.getUsrCode(),  param);
				isFirstTask = ActivityUtil.isFirstTask(processInstanceId, param.getUsrCode(),  param);
				invSaleOrder.setWorkFlowNo(processInstanceId);
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
			invSaleOrder.setStatus(status);
			String tableName = ClassUtils.getFinalModelSimpleName(invSaleOrder);
			CommonEventHistory commonEventHistory = commonEventHistoryBiz.loadAuditEventHistory(tableName,invSaleOrder.getStepNo(),invSaleOrder.getPK(),param);
			if(commonEventHistory!=null && StringUtils.isNotBlank(commonEventHistory.getPreStepNo())) {
				commonEventHistory = commonEventHistoryBiz.loadAuditEventHistory(tableName,commonEventHistory.getPreStepNo(),invSaleOrder.getPK(),param);
			}
			if(commonEventHistory!=null) {
				invSaleOrder.setChecker(commonEventHistory.getOper());
				invSaleOrder.setCheckDate(commonEventHistory.getOperDate());
			}else {
				invSaleOrder.setChecker(null);
				invSaleOrder.setCheckDate(null);
			}
			this.updateStatus(invSaleOrder, param);
			scmBillPendingBiz.cancelPendingBill(param.getUsrCode(),invSaleOrder, param);
			commonEventHistoryBiz.updateInvalid(invSaleOrder,invSaleOrder.getStepNo(),param);
			CommonAuditOpinion commonAuditOpinion = new CommonAuditOpinion();
			commonAuditOpinion.setStepNo(invSaleOrder.getStepNo());
			commonAuditOpinion.setActiveType("U");
			commonAuditOpinion.setOpinion("Y");
			commonEventHistoryBiz.addEventHistory(invSaleOrder, commonAuditOpinion, param);
		} else {
			throw new AppException("iscm.purchasemanage.purchasebusiness.find.ordernotexists");
		}
		
		return invSaleOrder;
	}
}
