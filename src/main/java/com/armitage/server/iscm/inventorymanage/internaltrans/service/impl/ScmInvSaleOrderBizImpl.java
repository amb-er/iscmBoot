package com.armitage.server.iscm.inventorymanage.internaltrans.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;

import com.armitage.server.api.business.invsaleorder.params.InvSaleOrderParams;
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
import com.armitage.server.iars.basedata.model.Customer;
import com.armitage.server.iars.basedata.model.PeriodCalendar;
import com.armitage.server.iars.basedata.service.CustomerBiz;
import com.armitage.server.iars.basedata.service.PeriodCalendarBiz;
import com.armitage.server.icrm.profile.model.Salesman2;
import com.armitage.server.icrm.profile.service.SalesmanBiz;
import com.armitage.server.iscm.basedata.model.ScmBillPending;
import com.armitage.server.iscm.basedata.model.ScmBillPending2;
import com.armitage.server.iscm.basedata.model.ScmMaterial2;
import com.armitage.server.iscm.basedata.service.ScmBillPendingBiz;
import com.armitage.server.iscm.basedata.service.ScmMaterialBiz;
import com.armitage.server.iscm.basedata.util.ScmMaterialUtil;
import com.armitage.server.iscm.common.model.CommonAuditOpinion;
import com.armitage.server.iscm.common.model.CommonEventHistory;
import com.armitage.server.iscm.common.service.CommonEventHistoryBiz;
import com.armitage.server.iscm.inventorymanage.internaltrans.dao.ScmInvSaleOrderDAO;
import com.armitage.server.iscm.inventorymanage.internaltrans.model.ScmInvSaleOrder;
import com.armitage.server.iscm.inventorymanage.internaltrans.model.ScmInvSaleOrder2;
import com.armitage.server.iscm.inventorymanage.internaltrans.model.ScmInvSaleOrderAdvQuery;
import com.armitage.server.iscm.inventorymanage.internaltrans.model.ScmInvSaleOrderEntry2;
import com.armitage.server.iscm.inventorymanage.internaltrans.service.ScmInvSaleOrderBiz;
import com.armitage.server.iscm.inventorymanage.internaltrans.service.ScmInvSaleOrderEntryBiz;
import com.armitage.server.iscm.inventorymanage.inventorydata.model.ScmInvStock2;
import com.armitage.server.iscm.inventorymanage.inventorydata.service.ScmInvStockBiz;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvSaleIssueBill2;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvSaleIssueBillEntry2;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.service.ScmInvSaleIssueBillBiz;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.CommonAuditParams;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurOrder2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.service.ScmPurOrderBiz;
import com.armitage.server.system.model.BillType2;
import com.armitage.server.system.model.Code;
import com.armitage.server.system.model.OrgAdmin2;
import com.armitage.server.system.model.OrgBaseUnit;
import com.armitage.server.system.model.OrgCompany2;
import com.armitage.server.system.model.OrgStorage2;
import com.armitage.server.system.service.BillTypeBiz;
import com.armitage.server.system.service.CodeBiz;
import com.armitage.server.system.service.OrgAdminBiz;
import com.armitage.server.system.service.OrgStorageBiz;
import com.armitage.server.system.service.OrgUnitBiz;
import com.armitage.server.system.service.OrgUnitRelationBiz;
import com.armitage.server.system.service.SysParamBiz;
import com.armitage.server.system.util.CodeAutoCalUtil;
import com.armitage.server.system.util.OrgUnitRelationType;
import com.armitage.server.user.model.Usr;
import com.armitage.server.user.service.UsrBiz;
import org.springframework.stereotype.Service;

@Service("scmInvSaleOrderBiz")
public class ScmInvSaleOrderBizImpl extends BaseBizImpl<ScmInvSaleOrder2> implements ScmInvSaleOrderBiz {

    private ScmInvSaleOrderEntryBiz scmInvSaleOrderEntryBiz;
    private UsrBiz usrBiz;
    private OrgUnitBiz orgUnitBiz;
    private CustomerBiz customerBiz;
    private OrgUnitRelationBiz orgUnitRelationBiz;
    private ScmMaterialBiz scmMaterialBiz;
    private ScmInvSaleIssueBillBiz scmInvSaleIssueBillBiz;
    private ScmInvStockBiz scmInvStockBiz;
    private PeriodCalendarBiz periodCalendarBiz;
    private SysParamBiz sysParamBiz;
    private BillTypeBiz billTypeBiz;
    private CodeBiz codeBiz;
	private ScmBillPendingBiz scmBillPendingBiz;
    private CommonEventHistoryBiz commonEventHistoryBiz;
	private OrgStorageBiz orgStorageBiz;
    private ScmPurOrderBiz scmPurOrderBiz;
    private SalesmanBiz salesmanBiz;
    private OrgAdminBiz orgAdminBiz;
    
    public OrgAdminBiz getOrgAdminBiz() {
		return orgAdminBiz;
	}

	public void setOrgAdminBiz(OrgAdminBiz orgAdminBiz) {
		this.orgAdminBiz = orgAdminBiz;
	}

	public void setSysParamBiz(SysParamBiz sysParamBiz) {
		this.sysParamBiz = sysParamBiz;
	}

	public void setScmInvSaleOrderEntryBiz(ScmInvSaleOrderEntryBiz scmInvSaleOrderEntryBiz) {
        this.scmInvSaleOrderEntryBiz = scmInvSaleOrderEntryBiz;
    }

    public void setUsrBiz(UsrBiz usrBiz) {
        this.usrBiz = usrBiz;
    }

    public void setOrgUnitBiz(OrgUnitBiz orgUnitBiz) {
        this.orgUnitBiz = orgUnitBiz;
    }

    public void setCustomerBiz(CustomerBiz customerBiz) {
        this.customerBiz = customerBiz;
    }

    public void setOrgUnitRelationBiz(OrgUnitRelationBiz orgUnitRelationBiz) {
        this.orgUnitRelationBiz = orgUnitRelationBiz;
    }

    public void setScmMaterialBiz(ScmMaterialBiz scmMaterialBiz) {
        this.scmMaterialBiz = scmMaterialBiz;
    }

    public void setScmInvSaleIssueBillBiz(ScmInvSaleIssueBillBiz scmInvSaleIssueBillBiz) {
        this.scmInvSaleIssueBillBiz = scmInvSaleIssueBillBiz;
    }

    public void setScmInvStockBiz(ScmInvStockBiz scmInvStockBiz) {
        this.scmInvStockBiz = scmInvStockBiz;
    }

    public void setPeriodCalendarBiz(PeriodCalendarBiz periodCalendarBiz) {
		this.periodCalendarBiz = periodCalendarBiz;
	}

	public void setBillTypeBiz(BillTypeBiz billTypeBiz) {
        this.billTypeBiz = billTypeBiz;
    }
	
	public void setCodeBiz(CodeBiz codeBiz) {
		this.codeBiz = codeBiz;
	}
	
    public void setScmBillPendingBiz(ScmBillPendingBiz scmBillPendingBiz) {
		this.scmBillPendingBiz = scmBillPendingBiz;
	}
    
    public void setOrgStorageBiz(OrgStorageBiz orgStorageBiz) {
		this.orgStorageBiz = orgStorageBiz;
	}

	public void setCommonEventHistoryBiz(CommonEventHistoryBiz commonEventHistoryBiz) {
		this.commonEventHistoryBiz = commonEventHistoryBiz;
	}

	public void setScmPurOrderBiz(ScmPurOrderBiz scmPurOrderBiz) {
		this.scmPurOrderBiz = scmPurOrderBiz;
	}

	public void setSalesmanBiz(SalesmanBiz salesmanBiz) {
		this.salesmanBiz = salesmanBiz;
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
			page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmInvSaleOrder.class) + "." + ScmInvSaleOrder.FN_ORGUNITNO), 
	                new QueryParam((ClassUtils.getFinalModelSimpleName(ScmInvSaleOrder.class) + "." + ScmInvSaleOrder.FN_ORGUNITNO), QueryParam.QUERY_IN, orgunitList.toString()));
		}else{
			page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmInvSaleOrder.class) + "." + ScmInvSaleOrder.FN_ORGUNITNO), 
	                new QueryParam((ClassUtils.getFinalModelSimpleName(ScmInvSaleOrder.class) + "." + ScmInvSaleOrder.FN_ORGUNITNO), QueryParam.QUERY_EQ, "0"));
		}
    }

    @Override
    protected void afterFindPage(List list, Page page, Param param) throws AppException {
        if(list != null && !list.isEmpty()){
            for(ScmInvSaleOrder2 scmInvSaleOrder : (List<ScmInvSaleOrder2>)list){
            	//视图增加待审人
				ScmBillPending2 scmBillPendingUsr = scmBillPendingBiz.selectPendingUsr(scmInvSaleOrder.getOtId(), "InvSaleOrder",param);
				StringBuffer usrName = new StringBuffer("");
				if (scmBillPendingUsr != null) {
					scmInvSaleOrder.setPendingUsr(scmBillPendingUsr.getUsrCodes());
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
				scmInvSaleOrder.setPendingUsrName(usrName.toString());
            	
                setConvertMap(scmInvSaleOrder,param);
				if("I,R".contains(scmInvSaleOrder.getStatus())) {
					scmInvSaleOrder.setPendingUsrName("");
				}
            }
        }
    }

    @Override
    protected void afterSelect(ScmInvSaleOrder2 entity, Param param) throws AppException {
        setConvertMap(entity,param);
    }
    
    private void setConvertMap(ScmInvSaleOrder2 scmInvSaleOrder,Param param){
        HashMap<String,Object> cacheMap = new HashMap<String,Object>();
        if (StringUtils.isNotBlank(scmInvSaleOrder.getCreator())){
            //制单人
            Usr usr = usrBiz.selectByCode(scmInvSaleOrder.getCreator(), param);
            if (usr != null) {
                if(scmInvSaleOrder.getDataDisplayMap()==null){
                    scmInvSaleOrder.setDataDisplayMap(new HashMap<String,SimpleDataDisplay>());
                }
                scmInvSaleOrder.getDataDisplayMap().put(ScmInvSaleOrder.FN_CREATOR, SimpleDataDisplayUtil.convert(usr));
                scmInvSaleOrder.setConvertMap(ScmInvSaleOrder.FN_CREATOR, usr);
                scmInvSaleOrder.setCreatorName(usr.getName());
            }
        }
        if (StringUtils.isNotBlank(scmInvSaleOrder.getOrgUnitNo())){
            //库存组织
            OrgBaseUnit orgBaseUnit = orgUnitBiz.selectbyOrgNo(scmInvSaleOrder.getOrgUnitNo(), param);
            if (orgBaseUnit != null) {
                scmInvSaleOrder.setConvertMap(ScmInvSaleOrder.FN_ORGUNITNO, orgBaseUnit);
            }
        }
        if (scmInvSaleOrder.getCustId() > 0){
            //客户
            Customer customer = (Customer) cacheMap.get(ClassUtils.getFinalModelSimpleName(Customer.class)+"_"+scmInvSaleOrder.getCustId());
            if(customer==null){
                customer = customerBiz.selectDirect(scmInvSaleOrder.getCustId(), param);
                cacheMap.put(ClassUtils.getFinalModelSimpleName(Customer.class)+"_"+scmInvSaleOrder.getCustId(),customer);
            }
            if (customer != null) {
                scmInvSaleOrder.setConvertMap(ScmInvSaleIssueBill2.FN_CUSTID, customer);
                scmInvSaleOrder.setCustName(customer.getCustName());
            }
        }

        if (StringUtils.isNotBlank(scmInvSaleOrder.getEditor())){
            //修改人
            Usr usr = usrBiz.selectByCode(scmInvSaleOrder.getEditor(), param);
            if (usr != null) {
                scmInvSaleOrder.setConvertMap(ScmInvSaleOrder.FN_EDITOR, usr);
                scmInvSaleOrder.setEditorName(usr.getName());
            }
        }
        if (StringUtils.isNotBlank(scmInvSaleOrder.getChecker())){
            //审核人
            Usr usr = usrBiz.selectByCode(scmInvSaleOrder.getChecker(), param);
            if (usr != null) {
                scmInvSaleOrder.setConvertMap(ScmInvSaleOrder.FN_CHECKER, usr);
                scmInvSaleOrder.setCheckerName(usr.getName());
            }
        }
        if (StringUtils.isNotBlank(scmInvSaleOrder.getBillType())){
            //来源单类型
			List<OrgCompany2> orgCompanyList = orgUnitRelationBiz.findToOrgUnitByType(OrgUnitRelationType.INVTOFIN, scmInvSaleOrder.getOrgUnitNo(), false, null, param);
			if(orgCompanyList==null || orgCompanyList.isEmpty()){
				throw new AppException("iscm.purchasemanage.purchasebusiness.ScmPurRequireBizImpl.error.notfin");
			}			
            BillType2 billType = billTypeBiz.selectByOrgAndCode(orgCompanyList.get(0).getOrgUnitNo(), scmInvSaleOrder.getBillType(), param);
            if (billType != null) {
                scmInvSaleOrder.setConvertMap(ScmInvSaleOrder.FN_BILLTYPE, billType);
                scmInvSaleOrder.setBillTypeName( billType.getBillName());
            }
        }
        if (scmInvSaleOrder.getSalesId()>0) {
        	Salesman2 salesman2 = salesmanBiz.select(scmInvSaleOrder.getSalesId(), param);
        	if (salesman2 != null) {
        		scmInvSaleOrder.setConvertMap(ScmInvSaleIssueBill2.FN_SALESID, salesman2);
			}
		}
        if(StringUtils.isNotBlank(scmInvSaleOrder.getStatus())){
			Code code = codeBiz.selectByCategoryAndCode("quotationStatus", scmInvSaleOrder.getStatus());
			if(code!=null)
				scmInvSaleOrder.setStatusName(code.getName());
		}
    }

    @Override
    protected void afterSelect(CommonBean bean, Param param) throws AppException {
        ScmInvSaleOrder2 scmInvSaleOrder = (ScmInvSaleOrder2) bean.getList().get(0);
        HashMap<String,Object> cacheMap = new HashMap<String,Object>();
        if(scmInvSaleOrder != null && scmInvSaleOrder.getOtId() > 0){
        	bean.setList2(scmInvSaleOrderEntryBiz.selectByOtId(scmInvSaleOrder.getOtId(), param));
        }
    }

    @Override
    protected void beforeAdd(ScmInvSaleOrder2 entity, Param param) throws AppException {
        if(entity != null){
        	String code = CodeAutoCalUtil.getBillCode("InvSaleOrder", entity, param);
			entity.setOtNo(code);
            //获取期间
			PeriodCalendar periodCalendar = periodCalendarBiz.selectByBizdate(entity.getBizDate(), param);
			if(periodCalendar==null){
				throw new AppException("com.armitage.server.iars.daily.selectByBizdate.error");
			}
			entity.setPeriodId(periodCalendar.getPeriodId());
			entity.setAccountYear(periodCalendar.getAccountYear());
			entity.setAccountPeriod(periodCalendar.getAccountPeriod());
			
			List<OrgCompany2> orgCompanyList = orgUnitRelationBiz.findToOrgUnitByType(OrgUnitRelationType.INVTOFIN, entity.getOrgUnitNo(), false, null, param);
			if(orgCompanyList==null || orgCompanyList.isEmpty()){
				throw new AppException("iscm.purchasemanage.purchasebusiness.ScmPurRequireBizImpl.error.notfin");
			}
			entity.setFinOrgUnitNo(orgCompanyList.get(0).getOrgUnitNo());
            BillType2 billType = billTypeBiz.selectByOrgAndCode(orgCompanyList.get(0).getOrgUnitNo(), "InvSaleOrder", param);
			if(billType!=null && billType.isNeedAudit()) {
				if(StringUtils.isBlank(billType.getWorkFlowNo())) {
					throw new AppException(Message.getMessage(param.getLang(), "iscm.workflow.needaudit.notprocess", new String[] {Message.getMessage(param.getLang(),"field.ScmInvSaleOrder")}));
				}
				entity.setWorkFlowNo(billType.getWorkFlowNo());
			}
        }
    }

    @Override
	protected void beforeUpdate(ScmInvSaleOrder2 oldEntity,ScmInvSaleOrder2 newEntity, Param param) throws AppException {
    	//获取期间
		PeriodCalendar periodCalendar = periodCalendarBiz.selectByBizdate(oldEntity.getBizDate(), param);
		if(periodCalendar==null){
			throw new AppException("com.armitage.server.iars.daily.selectByBizdate.error");
		}
		oldEntity.setPeriodId(periodCalendar.getPeriodId());
		oldEntity.setAccountYear(periodCalendar.getAccountYear());
		oldEntity.setAccountPeriod(periodCalendar.getAccountPeriod());
		
		List<OrgCompany2> orgCompanyList = orgUnitRelationBiz.findToOrgUnitByType(OrgUnitRelationType.INVTOFIN, newEntity.getOrgUnitNo(), false, null, param);
		if(orgCompanyList==null || orgCompanyList.isEmpty()){
			throw new AppException("iscm.purchasemanage.purchasebusiness.ScmPurRequireBizImpl.error.notfin");
		}
		newEntity.setFinOrgUnitNo(orgCompanyList.get(0).getOrgUnitNo());
//        BillType2 billType = billTypeBiz.selectByOrgAndCode(newEntity.getFinOrgUnitNo(), "InvSaleOrder", param);
//		if(billType!=null && billType.isNeedAudit()) {
//			if(StringUtils.isBlank(billType.getWorkFlowNo())) {
//				throw new AppException(Message.getMessage(param.getLang(), "iscm.workflow.needaudit.notprocess", new String[] {Message.getMessage(param.getLang(),"field.ScmInvSaleOrder")}));
//			}
//			newEntity.setWorkFlowNo(billType.getWorkFlowNo());
//		}
	}

	@Override
    protected void afterAdd(CommonBean bean, Param param) throws AppException {
        ScmInvSaleOrder2 scmInvSaleOrder = (ScmInvSaleOrder2) bean.getList().get(0);
        if(scmInvSaleOrder != null && scmInvSaleOrder.getOtId() > 0){
            //新增收货明细
            List<ScmInvSaleOrderEntry2> scmInvSaleOrderEntryList = bean.getList2();
            if(scmInvSaleOrderEntryList != null && !scmInvSaleOrderEntryList.isEmpty()){
                int lineId = 1;
                for(ScmInvSaleOrderEntry2 scmInvSaleOrderEntry : scmInvSaleOrderEntryList){
                    scmInvSaleOrderEntry.setLineId(lineId);
                    scmInvSaleOrderEntry.setOtId(scmInvSaleOrder.getOtId());
                    ScmMaterial2 scmMaterial = scmMaterialBiz.selectDirect(scmInvSaleOrderEntry.getItemId(), param);
                    if(scmMaterial==null){
                        throw new AppException("iscm.purchasemanage.purchasebusiness.ScmPurRequireBizImpl.error.itemnorexists");
                    }
                    scmInvSaleOrderEntry.setBaseUnit(scmMaterial.getBaseUnitId());
                    BigDecimal invConvRate = ScmMaterialUtil.getConvRate(scmInvSaleOrderEntry.getItemId(), scmInvSaleOrderEntry.getUnit(), param);//库存单位转换系数
                    scmInvSaleOrderEntry.setBaseQty(scmInvSaleOrderEntry.getQty().multiply(invConvRate));
                    scmInvSaleOrderEntryBiz.add(scmInvSaleOrderEntry, param);
                    lineId = lineId+1;
                }
            }
			scmPurOrderBiz.writeBackMovedQtyAndStatus(scmInvSaleOrderEntryList,param);	//整单回写订货单
        }
    }

    @Override
    protected void afterUpdate(CommonBean bean, Param param) throws AppException {
        ScmInvSaleOrder2 scmInvSaleOrder = (ScmInvSaleOrder2) bean.getList().get(0);
        if(scmInvSaleOrder != null && scmInvSaleOrder.getOtId() > 0){
            //更新销售单明细
            List<ScmInvSaleOrderEntry2> scmInvSaleOrderEntryList = bean.getList2();
            if(scmInvSaleOrderEntryList != null && !scmInvSaleOrderEntryList.isEmpty()){
                int lineId = 1;
                for(ScmInvSaleOrderEntry2 scmInvSaleOrderEntry : scmInvSaleOrderEntryList){
                	if (StringUtils.equals("I", scmInvSaleOrder.getStatus())) {
                		scmInvSaleOrderEntry.setLineId(lineId);
                	}
                    ScmMaterial2 scmMaterial = scmMaterialBiz.selectDirect(scmInvSaleOrderEntry.getItemId(), param);
                    if(scmMaterial==null){
                        throw new AppException("iscm.purchasemanage.purchasebusiness.ScmPurRequireBizImpl.error.itemnorexists");
                    }
                    scmInvSaleOrderEntry.setBaseUnit(scmMaterial.getBaseUnitId());
                    BigDecimal invConvRate = ScmMaterialUtil.getConvRate(scmInvSaleOrderEntry.getItemId(), scmInvSaleOrderEntry.getUnit(), param);//库存单位转换系数
                    scmInvSaleOrderEntry.setBaseQty(scmInvSaleOrderEntry.getQty().multiply(invConvRate));
                    scmInvSaleOrderEntry.setOtId(scmInvSaleOrder.getOtId());
                    lineId = lineId+1;
                }
                scmInvSaleOrderEntryBiz.update(scmInvSaleOrder, scmInvSaleOrderEntryList, ScmInvSaleOrderEntry2.FN_OTID, param);
            }
        }
    }

    @Override
    protected void afterDelete(ScmInvSaleOrder2 entity, Param param) throws AppException {
        if(entity != null && entity.getOtId() > 0){
            //删除销售订单明细
            scmInvSaleOrderEntryBiz.deleteByOtId(entity.getOtId(), param);
        }
    }

    @Override
    public ScmInvSaleOrder2 updateStatus(ScmInvSaleOrder2 scmInvSaleOrder, Param param) throws AppException {
        if(scmInvSaleOrder != null){
            ScmInvSaleOrder2 scmInvSaleOrder2 = this.updateDirect(scmInvSaleOrder, param);
            return scmInvSaleOrder2;
        }
        return null;
    }
    
    @Override
    public void generateOutBoundOrders(ScmInvSaleOrder2 scmInvSaleOrder, Param param) throws AppException {
        List<ScmInvSaleOrderEntry2> scmInvSaleOrderEntryList = scmInvSaleOrderEntryBiz.selectNotOut(scmInvSaleOrder.getOtId(), param);
        if (scmInvSaleOrderEntryList == null || scmInvSaleOrderEntryList.isEmpty()) {
            throw new AppException("iscm.inventorymanage.internaltrans.service.impl.ScmInvSaleOrderBizImpl.notExitDetail");
        }
        CommonBean bean = new CommonBean();
        List<ScmInvSaleIssueBill2> scmInvSaleIssueBillList = new ArrayList<>();
        List<ScmInvSaleIssueBillEntry2> scmInvSaleIssueBillEntryList = new ArrayList<>();
        ScmInvSaleIssueBill2 scmInvSaleIssueBill = new ScmInvSaleIssueBill2(true);
        // 销售出库主表
        scmInvSaleIssueBill.setStatus("I");
        scmInvSaleIssueBill.setBizDate(FormatUtils.parseDate(FormatUtils.fmtDate(CalendarUtil.getDate(param))));
        scmInvSaleIssueBill.setBizType("1");
        scmInvSaleIssueBill.setBillType("InvSaleOrder");
        scmInvSaleIssueBill.setCurrencyNo(scmInvSaleOrder.getCurrencyNo());
        scmInvSaleIssueBill.setCustId(scmInvSaleOrder.getCustId());
        scmInvSaleIssueBill.setExchangeRate(scmInvSaleOrder.getExchangeRate());
        scmInvSaleIssueBill.setCreator(param.getUsrCode());
        scmInvSaleIssueBill.setCreateDate(CalendarUtil.getDate(param));
        scmInvSaleIssueBill.setRemarks(scmInvSaleOrder.getRemarks());
        scmInvSaleIssueBill.setCreateOrgUnitNo(param.getOrgUnitNo());
        scmInvSaleIssueBill.setControlUnitNo(param.getControlUnitNo());
        scmInvSaleIssueBill.setOrgUnitNo(scmInvSaleOrder.getOrgUnitNo());
        scmInvSaleIssueBill.setSaleOrgUnitNo(scmInvSaleOrder.getSaleOrgUnitNo());
        scmInvSaleIssueBill.setSalesId(scmInvSaleOrder.getSalesId());
        List<OrgCompany2> orgCompanyList = orgUnitRelationBiz.findToOrgUnitByType(OrgUnitRelationType.INVTOFIN, scmInvSaleOrder.getOrgUnitNo(), false, null, param);
        if(orgCompanyList==null || orgCompanyList.isEmpty()){
          throw new AppException("iscm.purchasemanage.purchasebusiness.ScmPurRequireBizImpl.error.notfin");
        }
        scmInvSaleIssueBill.setFinOrgUnitNo(orgCompanyList.get(0).getOrgUnitNo());
        scmInvSaleIssueBillList.add(scmInvSaleIssueBill);
        bean.setList(scmInvSaleIssueBillList);
        int amtPrecision = Integer.parseInt(sysParamBiz.getValue(param.getOrgUnitNo(), "SCM_AmtPrecision", "2", param));
        HashMap<Long, List<ScmInvStock2>> stockMap = new HashMap<Long, List<ScmInvStock2>>();
        HashMap<Long, List<ScmInvStock2>> orgStockMap = new HashMap<Long, List<ScmInvStock2>>();
        // 销售出库明细表
        for (int i = 0; i < scmInvSaleOrderEntryList.size(); i++) {
            ScmInvSaleIssueBillEntry2 scmInvSaleIssueBillEntry = new ScmInvSaleIssueBillEntry2(true);
            scmInvSaleIssueBillEntry.setLineId(i+1);
            scmInvSaleIssueBillEntry.setItemId(scmInvSaleOrderEntryList.get(i).getItemId());
            scmInvSaleIssueBillEntry.setUnit(scmInvSaleOrderEntryList.get(i).getUnit());
            scmInvSaleIssueBillEntry.setBaseUnit(scmInvSaleOrderEntryList.get(i).getBaseUnit());
            scmInvSaleIssueBillEntry.setPieQty(scmInvSaleOrderEntryList.get(i).getPieQty());
            scmInvSaleIssueBillEntry.setPieUnit(scmInvSaleOrderEntryList.get(i).getPieUnit());
            scmInvSaleIssueBillEntry.setOrgUnitNo(scmInvSaleOrder.getOrgUnitNo());
            scmInvSaleIssueBillEntry.setQty(scmInvSaleOrderEntryList.get(i).getQty());
            scmInvSaleIssueBillEntry.setSaleQty(scmInvSaleOrderEntryList.get(i).getQty());
            scmInvSaleIssueBillEntry.setBaseQty(scmInvSaleOrderEntryList.get(i).getBaseQty());
            scmInvSaleIssueBillEntry.setTaxRate(BigDecimal.ZERO);
            scmInvSaleIssueBillEntry.setSaleTaxRate(BigDecimal.ZERO);
            scmInvSaleIssueBillEntry.setBalanceCustId(scmInvSaleOrder.getCustId());
            scmInvSaleIssueBillEntry.setSourceBillDtlId(scmInvSaleOrderEntryList.get(i).getId());
            scmInvSaleIssueBillEntry.setRemarks(scmInvSaleOrderEntryList.get(i).getRemarks());
            scmInvSaleIssueBillEntry.setSaleTaxPrice(scmInvSaleOrderEntryList.get(i).getSaleTaxPrice());
            scmInvSaleIssueBillEntry.setSaleTaxAmt(scmInvSaleOrderEntryList.get(i).getSaleTaxAmt());
            scmInvSaleIssueBillEntry.setPriceBillId(scmInvSaleOrderEntryList.get(i).getPriceBillId());
            scmInvSaleIssueBillEntry.setRefPriceStatus(scmInvSaleOrderEntryList.get(i).getRefPriceStatus());
            //查询物资的仓库结存，一条物资对应几条数据 scmInvStockList           
            List<ScmInvStock2> scmInvStockList;
            if(stockMap.containsKey(scmInvSaleOrderEntryList.get(i).getItemId())) {
            	scmInvStockList = stockMap.get(scmInvSaleOrderEntryList.get(i).getItemId());
            }else {
            	scmInvStockList = scmInvStockBiz.selectWareHsForSale(scmInvSaleOrder.getOrgUnitNo(),
                    scmInvSaleOrderEntryList.get(i).getItemId(), param);
            	stockMap.put(scmInvSaleOrderEntryList.get(i).getItemId(), scmInvStockList);
            }
            if (scmInvStockList != null && !scmInvStockList.isEmpty()) {
            	for(ScmInvStock2 scmInvStock:scmInvStockList) {
            		if(scmInvStock.getQty().compareTo(scmInvSaleIssueBillEntry.getQty())>=0) {
            			scmInvSaleIssueBillEntry.setWareHouseId(scmInvStock.getWareHouseId());
                        scmInvSaleIssueBillEntry.setPrice(scmInvStock.getPrice());
                        scmInvSaleIssueBillEntry.setAmt(scmInvSaleIssueBillEntry.getQty().multiply(scmInvSaleIssueBillEntry.getPrice()).setScale(amtPrecision, RoundingMode.HALF_UP));
                        scmInvSaleIssueBillEntry.setTaxPrice(scmInvStock.getTaxPrice());
                        scmInvSaleIssueBillEntry.setTaxAmt(scmInvSaleIssueBillEntry.getQty().multiply(scmInvSaleIssueBillEntry.getTaxPrice()).setScale(amtPrecision, RoundingMode.HALF_UP));
                        scmInvStock.setQty(scmInvStock.getQty().subtract(scmInvSaleIssueBillEntry.getQty()));
                        break;
            		}
            	}
            }
            
            //查询物资的部门结存，一条物资对应几条数据 scmOrgStockList
            String sysParam = sysParamBiz.getValue(param.getControlUnitNo(), "SCM_EnableIdleItems", "N", param);
            //开启了闲置物资参数
            if("Y".equalsIgnoreCase(sysParam)) {
            	List<ScmInvStock2> scmOrgStockList;
                if(orgStockMap.containsKey(scmInvSaleOrderEntryList.get(i).getItemId())) {
                	scmOrgStockList = orgStockMap.get(scmInvSaleOrderEntryList.get(i).getItemId());
                }else {
                	Page page = new Page();
                    page.setShowCount(Integer.MAX_VALUE);
                    page.setModelClass(ScmInvStock2.class);
                    ArrayList arrayList = new ArrayList();
                    arrayList.add("itemId=" + scmInvSaleIssueBillEntry.getItemId());
                    arrayList.add("unit=" + scmInvSaleIssueBillEntry.getUnit());
                    arrayList.add("bizDate=" + scmInvSaleIssueBill.getBizDate());
                    arrayList.add("finOrgUnitNo=" + scmInvSaleIssueBill.getFinOrgUnitNo());
                    List<ScmInvStock2> list = scmInvStockBiz.queryPage(page, arrayList, "selectInvStockCostOrgByItemId", param);
                    StringBuffer stringBuffer = new StringBuffer();
                    if (list != null && !list.isEmpty()){
                        for (ScmInvStock2 scmInvStock2 : list) {
                            if (StringUtils.isNotBlank(stringBuffer.toString())){
                                stringBuffer.append(",");
                            }
                            stringBuffer.append("'").append(scmInvStock2.getCostOrgUnitNo()).append("'");
                        }
                        if (StringUtils.isEmpty(stringBuffer.toString())){
                            stringBuffer.append("0");
                        }
                        //行政委托了库存，并且有成本归集(范围为上面结存中的成本组织)的组织（成本组织）                      
                        page = new Page();
                    	page.setShowCount(Integer.MAX_VALUE);
                    	page.setModelClass(OrgAdmin2.class);
                    	String sqlCondition = "OrgAdmin.orgType='2' and OrgAdmin.orgUnitNo in (SELECT fromOrgUnitNo from orgunitrelation where toOrgUnitNo='"+scmInvSaleOrder.getOrgUnitNo()+"' and relationType='AdminToInv'" +
                    					"and fromOrgUnitNo in (Select fromOrgUnitNo from orgunitrelation where relationType='AdminToCost' and toOrgUnitNo in " +
                    					"(SELECT orgUnitNo from orgcostcenter where isBizUnit=1 and longNo like '%"+param.getControlUnitNo()+"%'))) and OrgAdmin.orgUnitNo in("+stringBuffer.toString()+")";
                    	page.setSqlCondition(sqlCondition);
                    	List<OrgAdmin2> orgAdmins = orgAdminBiz.queryPage(page, new ArrayList(), "", param);
                    	stringBuffer = new StringBuffer();
                    	if(orgAdmins!= null && !orgAdmins.isEmpty()) {
                    		for (OrgAdmin2 orgAdmin : orgAdmins) {
                                if (StringUtils.isNotBlank(stringBuffer.toString())){
                                    stringBuffer.append(",");
                                }
                                stringBuffer.append("'").append(orgAdmin.getOrgUnitNo()).append("'");
                            }
                            if (StringUtils.isEmpty(stringBuffer.toString())){
                                stringBuffer.append("0");
                            }
                            scmOrgStockList = scmInvStockBiz.selectOrgForSale(stringBuffer.toString(),scmInvSaleOrderEntryList.get(i).getItemId(), param);
                        	orgStockMap.put(scmInvSaleOrderEntryList.get(i).getItemId(), scmOrgStockList);
                    	}else {
                    		scmOrgStockList = null;
                    	}
                    }else {
                    	scmOrgStockList = null;
                    }
                }
                if (scmOrgStockList != null && !scmOrgStockList.isEmpty()) {
                	for(ScmInvStock2 scmInvStock:scmOrgStockList) {
                		if(scmInvStock.getQty().compareTo(scmInvSaleIssueBillEntry.getQty())>=0) {
                			scmInvSaleIssueBillEntry.setWareHouseId(0);
                			scmInvSaleIssueBillEntry.setOutOrgUnitNo(scmInvStock.getOrgUnitNo());
                			scmInvSaleIssueBillEntry.setOutCostOrgUnitNo(scmInvStock.getCostOrgUnitNo());
                            scmInvSaleIssueBillEntry.setPrice(scmInvStock.getPrice());
                            scmInvSaleIssueBillEntry.setAmt(scmInvSaleIssueBillEntry.getQty().multiply(scmInvSaleIssueBillEntry.getPrice()).setScale(amtPrecision, RoundingMode.HALF_UP));
                            scmInvSaleIssueBillEntry.setTaxPrice(scmInvStock.getTaxPrice());
                            scmInvSaleIssueBillEntry.setTaxAmt(scmInvSaleIssueBillEntry.getQty().multiply(scmInvSaleIssueBillEntry.getTaxPrice()).setScale(amtPrecision, RoundingMode.HALF_UP));
                            scmInvStock.setQty(scmInvStock.getQty().subtract(scmInvSaleIssueBillEntry.getQty()));
                            break;
                		}
                	}
                }
            }
            
            scmInvSaleIssueBillEntryList.add(scmInvSaleIssueBillEntry);
        }
        bean.setList2(scmInvSaleIssueBillEntryList);
        scmInvSaleIssueBillBiz.add(bean, param);
        
    }

	@Override
	protected void beforeDelete(ScmInvSaleOrder2 entity, Param param)
			throws AppException {
		ScmInvSaleOrder2 scmInvSaleOrder = this.selectDirect(entity.getOtId(), param);
		if(scmInvSaleOrder!=null && !StringUtils.equals(scmInvSaleOrder.getStatus(),"I")){
			throw new AppException(Message.getMessage(param.getLang(), "iscm.inventorymanage.delete.error", new String[]{entity.getOtNo()}));
		}
	}

	@Override
	public Integer checkFollowUpBill(ScmInvSaleOrder2 scmInvSaleOrder,Param param) throws AppException {
		List<ScmInvSaleIssueBill2> scmInvSaleIssueBillList = scmInvSaleIssueBillBiz.SelectBySaleOrder(scmInvSaleOrder,param);
		return scmInvSaleIssueBillList.size();
	}

	@Override
	public void delByPurOrder(ScmPurOrder2 scmPurOrder, Param param) throws AppException {
		String toSaleOut = sysParamBiz.getValue(param.getOrgUnitNo(), "SCM_ToSaleOut", "N", param);
		List<ScmInvSaleOrder2> scmInvSaleOrderList = this.selectByPoId(scmPurOrder.getId(), param);
		if (scmInvSaleOrderList != null && !scmInvSaleOrderList.isEmpty()) {
			for (ScmInvSaleOrder2 scmInvSaleOrder:scmInvSaleOrderList) {
				if(StringUtils.equals("Y", toSaleOut)) {
					//取消生成出库
					this.undoGenerateOutBoundOrders(scmInvSaleOrder, param);
					//取消下达
					scmInvSaleOrder.setStatus("A");
					this.updateDirect(scmInvSaleOrder, param);
					//取消提交
					scmInvSaleOrder.setStatus("I");
					this.updateDirect(scmInvSaleOrder, param);
				}else {
					if(scmInvSaleOrder!=null){
						if(!StringUtils.equals("I",scmInvSaleOrder.getStatus())){
							throw new AppException(Message.getMessage(param.getLang(), "iscm.inventorymanage.scmInvSaleOrder.delByPurOrder.error", new String[]{scmInvSaleOrder.getOtNo()}));
						}
					}
				}
			}
			this.delete(scmInvSaleOrderList, param);
		}
	}

	@Override
	public List<ScmInvSaleOrder2> selectByPoId(long poId, Param param) throws AppException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("poId", poId);
		return ((ScmInvSaleOrderDAO)dao).selectByPoId(map);
	}
    
	@Override
	protected void beforeUpdate(CommonBean bean, Param param) throws AppException {
		ScmInvSaleOrder2 entry = (ScmInvSaleOrder2) bean.getList().get(0);
		if(entry!=null){
			ScmInvSaleOrder2 scmInvSaleOrder = this.select(entry.getPK(), param);
			if(!StringUtils.equals(scmInvSaleOrder.getStatus(),"I")){
				throw new AppException(Message.getMessage(param.getLang(), "iscm.commom.edit.error.notnewstatus"));
			}
		}
	}

	@Override
	public ScmInvSaleOrder2 finish(ScmInvSaleOrder2 scmInvSaleOrder, Param param) throws AppException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ScmInvSaleOrder2 undoFinish(ScmInvSaleOrder2 scmInvSaleOrder, Param param) throws AppException {
		if(scmInvSaleOrder!=null) {
			scmInvSaleOrder = this.selectDirect(scmInvSaleOrder.getOtId(), param);
	        if(scmInvSaleOrder!=null){
	            if (!StringUtils.equals("C", scmInvSaleOrder.getStatus()) && !StringUtils.equals("F", scmInvSaleOrder.getStatus())) {
	                throw new AppException("iscm.purchasemanage.error.cancelFinish");
	            }else if("C".equals(scmInvSaleOrder.getStatus()) || "F".equals(scmInvSaleOrder.getStatus())){
	            	List<ScmInvSaleIssueBill2> scmInvSaleIssueBillList = scmInvSaleIssueBillBiz.SelectBySaleOrder(scmInvSaleOrder, param);
	                if(scmInvSaleIssueBillList!=null && !scmInvSaleIssueBillList.isEmpty()){
	                    throw new AppException(Message.getMessage(param.getLang(),"iscm.inventorymanage.ScmInvSaleOrder.undofinish.existissue",new String[] {scmInvSaleIssueBillList.get(0).getOtNo()}));
	                }
	                scmInvSaleOrder.setStatus("E");
	                try {
	                    this.updateStatus(scmInvSaleOrder, param);
	                } catch (Exception e) {
	                    throw e;
	                }
	            }
	        }else{
	            throw new AppException("iscm.purchasemanage.purchasebusiness.find.ordernotexists");
	        }
	        return scmInvSaleOrder;
		}
		return null;
	}

	@Override
	public ScmInvSaleOrder2 undoGenerateOutBoundOrders(ScmInvSaleOrder2 scmInvSaleOrder, Param param) throws AppException {
		List<ScmInvSaleIssueBill2> scmInvSaleIssueBillList = scmInvSaleIssueBillBiz.SelectBySaleOrder(scmInvSaleOrder, param);
        if(scmInvSaleIssueBillList!=null && !scmInvSaleIssueBillList.isEmpty()){
        	for(ScmInvSaleIssueBill2 scmInvSaleIssueBill:scmInvSaleIssueBillList) {
        		if(!StringUtils.equals(scmInvSaleIssueBill.getStatus(),"I")){
        			throw new AppException(Message.getMessage(param.getLang(), "iscm.inventorymanage.scmInvSaleOrder.undoGenerateOutBoundOrders.error.IssueNotNewSattus", new String[]{scmInvSaleIssueBill.getOtNo()}));
        		}
        		scmInvSaleIssueBill.setDelBySaleOrder(true);
        	}
        	scmInvSaleIssueBillBiz.delete(scmInvSaleIssueBillList, param);
        }
        return scmInvSaleOrder;
	}

	@Override
	public List<ScmInvSaleOrder2> selectBySaleIssue(long otId, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("otId", otId);
		return ((ScmInvSaleOrderDAO)dao).selectBySaleIssue(map);
	}
	@Override
	public void doAdvQuery(Page page, Param param) {
		if (page.getModel() != null) {
			if (page.getModel() instanceof ScmInvSaleOrderAdvQuery) {
				ScmInvSaleOrderAdvQuery scmInvSaleOrderAdvQuery = (ScmInvSaleOrderAdvQuery) page.getModel();
				if(scmInvSaleOrderAdvQuery.getBegBizDate()!=null){
					if(scmInvSaleOrderAdvQuery.getEndBizDate()!=null) {
						page.getParam().put(ScmInvSaleOrder2.FN_REQDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvSaleOrder2.class) + "." +ScmInvSaleOrder2.FN_REQDATE, QueryParam.QUERY_BETWEEN,FormatUtils.fmtDate(scmInvSaleOrderAdvQuery.getBegBizDate()),FormatUtils.fmtDate(scmInvSaleOrderAdvQuery.getEndBizDate())));
					}else {
						page.getParam().put(ScmInvSaleOrder2.FN_REQDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvSaleOrder2.class) + "." +ScmInvSaleOrder2.FN_REQDATE, QueryParam.QUERY_GE,FormatUtils.fmtDate(scmInvSaleOrderAdvQuery.getBegBizDate())));
					}
				}else if(scmInvSaleOrderAdvQuery.getEndBizDate()!=null) {
					page.getParam().put(ScmInvSaleOrder2.FN_REQDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvSaleOrder2.class) + "." +ScmInvSaleOrder2.FN_REQDATE, QueryParam.QUERY_LE,FormatUtils.fmtDate(scmInvSaleOrderAdvQuery.getEndBizDate())));
				}
				if(scmInvSaleOrderAdvQuery.getCreateDateFrom()!=null){
					if(scmInvSaleOrderAdvQuery.getCreateDateTo()!=null) {
						page.getParam().put(ScmInvSaleOrder2.FN_CREATEDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvSaleOrder2.class) + "." +ScmInvSaleOrder2.FN_CREATEDATE, QueryParam.QUERY_BETWEEN,FormatUtils.fmtDate(scmInvSaleOrderAdvQuery.getCreateDateFrom()),FormatUtils.fmtDate(CalendarUtil.relativeDate(scmInvSaleOrderAdvQuery.getCreateDateTo(),1))));
					}else {
						page.getParam().put(ScmInvSaleOrder2.FN_CREATEDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvSaleOrder2.class) + "." +ScmInvSaleOrder2.FN_CREATEDATE, QueryParam.QUERY_GE,FormatUtils.fmtDate(scmInvSaleOrderAdvQuery.getCreateDateFrom())));
					}
				}else if(scmInvSaleOrderAdvQuery.getCreateDateTo()!=null) {
					page.getParam().put(ScmInvSaleOrder2.FN_CREATEDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvSaleOrder2.class) + "." +ScmInvSaleOrder2.FN_CREATEDATE, QueryParam.QUERY_LE,FormatUtils.fmtDate(CalendarUtil.relativeDate(scmInvSaleOrderAdvQuery.getCreateDateTo(),1))));
				}
				if(scmInvSaleOrderAdvQuery.getCustId()>0){
					page.getParam().put(ScmInvSaleOrder2.FN_CUSTID,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvSaleOrder2.class) + "." +ScmInvSaleOrder2.FN_CUSTID, QueryParam.QUERY_EQ,String.valueOf(scmInvSaleOrderAdvQuery.getCustId())));
				}
			}
		}
	}

	@Override
	public ScmInvSaleOrder2 queryInvSaleOrder(ScmInvSaleOrder2 scmInvSaleOrder,
			Param param) throws AppException {
		Page page=new Page();
		page.setModelClass(ScmInvSaleOrder2.class);
		page.setShowCount(Integer.MAX_VALUE);
		page.getParam().put(ScmInvSaleOrder2.FN_OTNO,new QueryParam(ScmInvSaleOrder2.FN_OTNO, QueryParam.QUERY_EQ, scmInvSaleOrder.getOtNo()));
		
		List<ScmInvSaleOrder2> scmInvSaleOrderList =this.findPage(page, param);
		ScmInvSaleOrder2 scmInvSaleOrder2 = new ScmInvSaleOrder2();
		if(scmInvSaleOrderList!=null && !scmInvSaleOrderList.isEmpty()){
			scmInvSaleOrder2 = scmInvSaleOrderList.get(0);
			scmInvSaleOrder2.setScmInvSaleOrderEntryList(scmInvSaleOrderEntryBiz.selectByOtId(scmInvSaleOrder2.getOtId(), param));
			
			//增加待审人
			ScmBillPending2 scmBillPendingUsr = scmBillPendingBiz.selectPendingUsr(scmInvSaleOrder2.getOtId(), "InvSaleOrder",param);
			if (scmBillPendingUsr != null) {
				scmInvSaleOrder2.setPendingUsr(scmBillPendingUsr.getUsrCodes());
			}
		}else{
			throw new AppException("iscm.purchasemanage.pricemanage.find.ordernotexists");
		}
		return scmInvSaleOrder2;
	}

	@Override
	public ScmInvSaleOrder2 doSubmitInvSaleOrder(
			ScmInvSaleOrder2 scmInvSaleOrder, Param param) throws AppException {
		ScmInvSaleOrder2 invSaleOrder = null;
		if(scmInvSaleOrder.getOtId()>0){
			invSaleOrder = this.selectDirect(scmInvSaleOrder.getOtId(), param);
		}else{
			Page page=new Page();
			page.setModelClass(ScmInvSaleOrder.class);
			page.setShowCount(Integer.MAX_VALUE);
			page.getParam().put(
					ScmInvSaleOrder.FN_OTNO,
					new QueryParam(ScmInvSaleOrder.FN_OTNO, QueryParam.QUERY_EQ,
							scmInvSaleOrder.getOtNo()));
			
			List<ScmInvSaleOrder2> scmInvSaleOrderList =this.findPage(page, param);
			
			if(scmInvSaleOrderList!=null && !scmInvSaleOrderList.isEmpty()){
				invSaleOrder = scmInvSaleOrderList.get(0);
			}
		}
		
		if(invSaleOrder != null){
			if(!invSaleOrder.getStatus().equals("I")){
				throw new AppException("iscm.purchasemanage.error.commit");
			}else if(invSaleOrder.getStatus().equals("I")){
				List<ScmInvSaleOrderEntry2> scmInvSaleOrderEntryList = scmInvSaleOrderEntryBiz.selectByOtId(invSaleOrder.getOtId(), param);
				
				List<OrgCompany2> orgCompanyList = orgUnitRelationBiz.findToOrgUnitByType(OrgUnitRelationType.INVTOFIN, invSaleOrder.getOrgUnitNo(), false, null, param);
				if(orgCompanyList==null || orgCompanyList.isEmpty()){
					throw new AppException("iscm.purchasemanage.purchasebusiness.ScmPurRequireBizImpl.error.notfin");
				}			
		        BillType2 billType = billTypeBiz.selectByOrgAndCode(orgCompanyList.get(0).getOrgUnitNo(), "InvSaleOrder", param);
				boolean needAudit = false;
				if(billType!=null && billType.isNeedAudit())
					needAudit = true;
				if(needAudit){
					CommonBean bean = new CommonBean();
					List<ScmInvSaleOrder2> scmInvSaleOrderList = new ArrayList<>();
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
			}
		}else{
			throw new AppException("iscm.purchasemanage.purchasebusiness.find.ordernotexists");
		}
		return invSaleOrder;
	}
	
	@Override
	public ScmInvSaleOrder2 doUnSubmitInvSaleOrder(
			ScmInvSaleOrder2 scmInvSaleOrder, Param param) throws AppException {
		ScmInvSaleOrder2 invSaleOrder = null;
		if(scmInvSaleOrder.getOtId()>0){
			invSaleOrder = this.selectDirect(scmInvSaleOrder.getOtId(), param);
		}else{
			Page page=new Page();
			page.setModelClass(ScmInvSaleOrder2.class);
			page.setShowCount(Integer.MAX_VALUE);
			page.getParam().put(
					ScmInvSaleOrder2.FN_OTNO,
					new QueryParam(ScmInvSaleOrder2.FN_OTNO, QueryParam.QUERY_EQ,
							scmInvSaleOrder.getOtNo()));
			
			List<ScmInvSaleOrder2> scmPurRequireList =this.findPage(page, param);
			
			if(scmPurRequireList!=null && !scmPurRequireList.isEmpty()){
				invSaleOrder=scmPurRequireList.get(0);
			}
		}
		
		if(invSaleOrder!=null){
			List<OrgCompany2> orgCompanyList = orgUnitRelationBiz.findToOrgUnitByType(OrgUnitRelationType.INVTOFIN, invSaleOrder.getOrgUnitNo(), false, null, param);
			if(orgCompanyList==null || orgCompanyList.isEmpty()){
				throw new AppException("iscm.purchasemanage.purchasebusiness.ScmPurRequireBizImpl.error.notfin");
			}			
	        BillType2 billType = billTypeBiz.selectByOrgAndCode(orgCompanyList.get(0).getOrgUnitNo(), "InvSaleOrder", param);
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
	public ScmInvSaleOrder2 doAuditInvSaleOrder(
			CommonAuditParams commonAuditParams, Param param)
			throws AppException {
		ScmInvSaleOrder2 invSaleOrder = null;
		
		ScmInvSaleOrder2 scmInvSaleOrder= new ScmInvSaleOrder2();
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
			map.put(
					ScmInvSaleOrder.FN_OTNO,
					new QueryParam(ScmInvSaleOrder.FN_OTNO, QueryParam.QUERY_EQ,
							scmInvSaleOrder.getOtNo()));
			map.put(ScmInvSaleOrder.FN_CONTROLUNITNO, new QueryParam(ScmInvSaleOrder.FN_CONTROLUNITNO, QueryParam.QUERY_EQ,
							param.getControlUnitNo()));
			List<ScmInvSaleOrder2> scmInvSaleOrderList =this.findAll(map, param);
			
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
					AuditMsgUtil.sendAuditMsg("InvSaleOrder",invSaleOrder,invSaleOrderParams, usrList, param);
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
            if(StringUtils.contains("A,B", invSaleOrder.getStatus())) {
				//通过或部分通过时检查是否自动下达
				List<OrgCompany2> orgCompanyList = orgUnitRelationBiz.findToOrgUnitByType(OrgUnitRelationType.INVTOFIN, invSaleOrder.getOrgUnitNo(), false, null, param);
				if(orgCompanyList==null || orgCompanyList.isEmpty()){
					throw new AppException("iscm.purchasemanage.purchasebusiness.ScmPurRequireBizImpl.error.notfin");
				}			
				BillType2 billType = billTypeBiz.selectByOrgAndCode(orgCompanyList.get(0).getOrgUnitNo(), "InvSaleOrder", param);
				if(billType!=null && billType.isAutoRelease()) {
					this.doRelease(invSaleOrder, param);
				}
			}
		} else {
			throw new AppException("iscm.purchasemanage.purchasebusiness.find.ordernotexists");
		}
		
		return invSaleOrder;
	}

	@Override
	public ScmInvSaleOrder2 doUnAuditInvSaleOrder(
			ScmInvSaleOrder2 scmInvSaleOrder, Param param) throws AppException {
		ScmInvSaleOrder2 invSaleOrder = null;
		List<ScmInvSaleOrder2> scmInvSaleOrderList = new ArrayList<> ();
		List<ScmInvSaleOrderEntry2> scmInvSaleOrderEntryList = new ArrayList<> ();
		boolean isFirstTask = false;
		
		if(scmInvSaleOrder.getOtId()>0){
			invSaleOrder = this.selectDirect(scmInvSaleOrder.getOtId(), param);
		}else{
			HashMap<String,Object> map=new HashMap<String,Object>();
			map.put(
					ScmInvSaleOrder2.FN_OTNO,
					new QueryParam(ScmInvSaleOrder2.FN_OTNO, QueryParam.QUERY_EQ,
							scmInvSaleOrder.getOtNo()));
			map.put(ScmInvSaleOrder.FN_CONTROLUNITNO, new QueryParam(ScmInvSaleOrder.FN_CONTROLUNITNO, QueryParam.QUERY_EQ,
					param.getControlUnitNo()));
			
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
			scmInvSaleOrderEntryList = scmInvSaleOrderEntryBiz.selectByOtId(invSaleOrder.getOtId(), param);
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
				
				BillType2 billType = billTypeBiz.selectByOrgAndCode(invSaleOrder.getFinOrgUnitNo(), "InvSaleOrder", param);
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

	@Override
	public ScmInvSaleOrder2 doRelease(ScmInvSaleOrder2 scmInvSaleOrder, Param param) throws AppException {
		ScmInvSaleOrder2 scmInvSaleOrder2 = null;
		if(scmInvSaleOrder.getOtId()>0){
			scmInvSaleOrder2 = this.selectDirect(scmInvSaleOrder.getOtId(),param);
		}else{
			Page page=new Page();
			page.setModelClass(ScmInvSaleOrder2.class);
			page.setShowCount(Integer.MAX_VALUE);
			page.getParam().put(ScmInvSaleOrder2.FN_OTNO,new QueryParam(ScmInvSaleOrder2.FN_OTNO, QueryParam.QUERY_EQ,scmInvSaleOrder.getOtNo()));
			
			List<ScmInvSaleOrder2> scmInvSaleOrderList =this.findPage(page, param);
			if(scmInvSaleOrderList!=null && !scmInvSaleOrderList.isEmpty()){
				scmInvSaleOrder2=scmInvSaleOrderList.get(0);
			}
		}
		if(scmInvSaleOrder2!=null){
			if(!scmInvSaleOrder2.getStatus().equals("A") && !scmInvSaleOrder2.getStatus().equals("P")){
				throw new AppException("iscm.purchasemanage.error.release");
			}else {
				if(scmInvSaleOrder2.getStatus().equals("A")){
					scmInvSaleOrder2.setStatus("E");
				}else {
					scmInvSaleOrder2.setStatus("P");
				}
				try{
					this.updateDirect(scmInvSaleOrder2, param);
				}catch(AppException e){
					throw e;
				}
			}
		}else{
			throw new AppException("iscm.purchasemanage.pricemanage.find.ordernotexists");
		}
		return scmInvSaleOrder2;
	}

	@Override
	public ScmInvSaleOrder2 doUndoRelease(ScmInvSaleOrder2 scmInvSaleOrder, Param param) throws AppException {
		ScmInvSaleOrder2 scmInvSaleOrder2 = null;
		if(scmInvSaleOrder.getOtId()>0){
			scmInvSaleOrder2 = this.selectDirect(scmInvSaleOrder.getOtId(),param);
		}else{
			Page page=new Page();
			page.setModelClass(ScmInvSaleOrder2.class);
			page.setShowCount(Integer.MAX_VALUE);
			page.getParam().put(ScmInvSaleOrder2.FN_OTNO,new QueryParam(ScmInvSaleOrder2.FN_OTNO, QueryParam.QUERY_EQ,scmInvSaleOrder.getOtNo()));
			
			List<ScmInvSaleOrder2> scmInvSaleOrderList =this.findPage(page, param);
			if(scmInvSaleOrderList!=null && !scmInvSaleOrderList.isEmpty()){
				scmInvSaleOrder2=scmInvSaleOrderList.get(0);
			}
		}
		if(scmInvSaleOrder2!=null){
			if(!StringUtils.contains("E,S", scmInvSaleOrder2.getStatus())){
				throw new AppException("iscm.purchasemanage.error.cancelRelease2");
			}else{
				if(StringUtils.equals("E",scmInvSaleOrder2.getStatus())){
					scmInvSaleOrder2.setStatus("A");
				}else {
					scmInvSaleOrder2.setStatus("B");
				}
				try{
					this.updateDirect(scmInvSaleOrder2, param);
				}catch(AppException e){
					throw e;
				}
			}
		}else{
			throw new AppException("iscm.purchasemanage.pricemanage.find.ordernotexists");
		}
		return scmInvSaleOrder2;
	}

	@Override
	public ScmInvSaleOrder2 updatePrtCount(ScmInvSaleOrder2 scmInvSaleOrder, Param param) throws AppException {
		if(scmInvSaleOrder.getOtId()>0){
			ScmInvSaleOrder2 bill = this.selectDirect(scmInvSaleOrder.getOtId(), param);
			if(bill != null){
				int prtCount = bill.getPrtcount();
				bill.setPrtcount((prtCount+1));
				this.updateDirect(bill, param);
				return bill;
			}
		}
		return scmInvSaleOrder;
	}
}
