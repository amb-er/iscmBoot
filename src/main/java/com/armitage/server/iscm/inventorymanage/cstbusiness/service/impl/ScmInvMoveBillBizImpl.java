package com.armitage.server.iscm.inventorymanage.cstbusiness.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.armitage.server.api.business.datasync.params.InvMoveDetailParams;
import com.armitage.server.api.business.datasync.params.InvMoveListParams;
import com.armitage.server.api.business.datasync.result.DataSyncResult;
import com.armitage.server.api.business.invmove.params.InvMoveParams;
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
import com.armitage.server.common.util.CalendarUtil;
import com.armitage.server.common.util.ClassUtils;
import com.armitage.server.common.util.FormatUtils;
import com.armitage.server.common.util.ListSortUtil;
import com.armitage.server.common.util.SimpleDataDisplayUtil;
import com.armitage.server.iars.basedata.model.PeriodCalendar;
import com.armitage.server.iars.basedata.service.PeriodCalendarBiz;
import com.armitage.server.iscm.basedata.model.PubSysBasicInfo;
import com.armitage.server.iscm.basedata.model.ScmBillPending;
import com.armitage.server.iscm.basedata.model.ScmBillPending2;
import com.armitage.server.iscm.basedata.model.ScmMaterial2;
import com.armitage.server.iscm.basedata.model.ScmMaterialInventory2;
import com.armitage.server.iscm.basedata.service.PubSysBasicInfoBiz;
import com.armitage.server.iscm.basedata.service.ScmBillPendingBiz;
import com.armitage.server.iscm.basedata.service.ScmMaterialBiz;
import com.armitage.server.iscm.basedata.service.ScmMaterialInventoryBiz;
import com.armitage.server.iscm.basedata.util.ScmMaterialUtil;
import com.armitage.server.iscm.common.model.CommonAuditOpinion;
import com.armitage.server.iscm.common.model.CommonEventHistory;
import com.armitage.server.iscm.common.service.CommonEventHistoryBiz;
import com.armitage.server.iscm.inventorymanage.cstbusiness.dao.ScmInvMoveBillDAO;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvMoveBill2;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvMoveBillAdvQuery;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvMoveBillEntry2;
import com.armitage.server.iscm.inventorymanage.cstbusiness.service.ScmInvMoveBillBiz;
import com.armitage.server.iscm.inventorymanage.cstbusiness.service.ScmInvMoveBillEntryBiz;
import com.armitage.server.iscm.inventorymanage.inventorydata.model.ScmInvStock2;
import com.armitage.server.iscm.inventorymanage.inventorydata.service.ScmInvBalBiz;
import com.armitage.server.iscm.inventorymanage.inventorydata.service.ScmInvStockBiz;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.CommonAuditParams;
import com.armitage.server.system.model.BillType2;
import com.armitage.server.system.model.Code;
import com.armitage.server.system.model.OrgBaseUnit;
import com.armitage.server.system.model.OrgCompany2;
import com.armitage.server.system.model.OrgCostCenter2;
import com.armitage.server.system.model.SystemState;
import com.armitage.server.system.service.BillTypeBiz;
import com.armitage.server.system.service.CodeBiz;
import com.armitage.server.system.service.OrgCostCenterBiz;
import com.armitage.server.system.service.OrgUnitBiz;
import com.armitage.server.system.service.OrgUnitRelationBiz;
import com.armitage.server.system.service.SysParamBiz;
import com.armitage.server.system.service.SystemStateBiz;
import com.armitage.server.system.util.CodeAutoCalUtil;
import com.armitage.server.system.util.OrgUnitRelationType;
import com.armitage.server.user.model.Usr;
import com.armitage.server.user.service.UsrBiz;

@Service("scmInvMoveBillBiz")
public class ScmInvMoveBillBizImpl extends BaseBizImpl<ScmInvMoveBill2> implements ScmInvMoveBillBiz {

	private UsrBiz usrBiz;
	private OrgUnitBiz orgUnitBiz;
	private ScmInvMoveBillEntryBiz scmInvMoveBillEntryBiz;
	private ScmInvStockBiz scmInvStockBiz;
	private ScmInvBalBiz scmInvBalBiz;
	private CommonEventHistoryBiz commonEventHistoryBiz;
	private ScmMaterialBiz scmMaterialBiz;
	private ScmMaterialInventoryBiz scmMaterialInventoryBiz;
	private PubSysBasicInfoBiz pubSysBasicInfoBiz;
	private OrgUnitRelationBiz orgUnitRelationBiz;
	private PeriodCalendarBiz periodCalendarBiz;
	private SystemStateBiz systemStateBiz;
	private SysParamBiz sysParamBiz;
	private BillTypeBiz billTypeBiz;
	private CodeBiz codeBiz;
	private ScmBillPendingBiz scmBillPendingBiz;
	private OrgCostCenterBiz orgCostCenterBiz;
	
	protected  HashMap<String,Long> scmMatrialMap = new HashMap<String,Long>();
	protected  HashMap<Long,Long> scmUnitMap = new HashMap<Long,Long>();
	
    public void setScmMaterialInventoryBiz(ScmMaterialInventoryBiz scmMaterialInventoryBiz) {
		this.scmMaterialInventoryBiz = scmMaterialInventoryBiz;
	}
    
	public void setUsrBiz(UsrBiz usrBiz) {
		this.usrBiz = usrBiz;
	}

	public void setOrgUnitBiz(OrgUnitBiz orgUnitBiz) {
		this.orgUnitBiz = orgUnitBiz;
	}

	public void setScmInvMoveBillEntryBiz(
			ScmInvMoveBillEntryBiz scmInvMoveBillEntryBiz) {
		this.scmInvMoveBillEntryBiz = scmInvMoveBillEntryBiz;
	}

	public void setScmInvStockBiz(ScmInvStockBiz scmInvStockBiz) {
		this.scmInvStockBiz = scmInvStockBiz;
	}

	public void setScmInvBalBiz(ScmInvBalBiz scmInvBalBiz) {
		this.scmInvBalBiz = scmInvBalBiz;
	}

	public void setCommonEventHistoryBiz(CommonEventHistoryBiz commonEventHistoryBiz) {
		this.commonEventHistoryBiz = commonEventHistoryBiz;
	}

	public void setScmMaterialBiz(ScmMaterialBiz scmMaterialBiz) {
		this.scmMaterialBiz = scmMaterialBiz;
	}

	public void setPubSysBasicInfoBiz(PubSysBasicInfoBiz pubSysBasicInfoBiz) {
		this.pubSysBasicInfoBiz = pubSysBasicInfoBiz;
	}

	public void setOrgUnitRelationBiz(OrgUnitRelationBiz orgUnitRelationBiz) {
		this.orgUnitRelationBiz = orgUnitRelationBiz;
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

	public void setBillTypeBiz(BillTypeBiz billTypeBiz) {
		this.billTypeBiz = billTypeBiz;
	}
	
	public void setCodeBiz(CodeBiz codeBiz) {
		this.codeBiz = codeBiz;
	}
	
	public void setScmBillPendingBiz(ScmBillPendingBiz scmBillPendingBiz) {
		this.scmBillPendingBiz = scmBillPendingBiz;
	}
	
	public void setOrgCostCenterBiz(OrgCostCenterBiz orgCostCenterBiz) {
		this.orgCostCenterBiz = orgCostCenterBiz;
	}

	@Override
	protected void beforeFindPage(Page page, Param param) throws AppException {
		List<OrgCostCenter2> orgCostCenterList = orgCostCenterBiz.findChild(param.getOrgUnitNo(), param);
		if(orgCostCenterList!=null && !orgCostCenterList.isEmpty()) {
			StringBuffer orgunitList=new StringBuffer("");
			for(OrgCostCenter2 orgCostCenter:orgCostCenterList) {
				if(StringUtils.isNotBlank(orgunitList.toString()))
					orgunitList.append(",");
				orgunitList.append("'").append(orgCostCenter.getOrgUnitNo()).append("'");
			}
			page.getParam().put(
					(ClassUtils.getFinalModelSimpleName(ScmInvMoveBill2.class)
							+ "." + ScmInvMoveBill2.FN_OUTCSTORGUNITNO),
					new QueryParam(
							(ClassUtils
									.getFinalModelSimpleName(ScmInvMoveBill2.class)
									+ "." + ScmInvMoveBill2.FN_OUTCSTORGUNITNO), QueryParam.QUERY_IN, orgunitList.toString()));
		}else{
			page.getParam().put(
					(ClassUtils.getFinalModelSimpleName(ScmInvMoveBill2.class)
							+ "." + ScmInvMoveBill2.FN_OUTCSTORGUNITNO),
					new QueryParam(
							(ClassUtils
									.getFinalModelSimpleName(ScmInvMoveBill2.class)
									+ "." + ScmInvMoveBill2.FN_OUTCSTORGUNITNO), QueryParam.QUERY_EQ, "0"));
		}
	}

	@Override
	protected void beforeAdd(ScmInvMoveBill2 entity, Param param)
			throws AppException {
		if (entity != null) {
			// 创建转移单号
			String code = CodeAutoCalUtil.getBillCode("InvMoveBill", entity, param);
			entity.setWtNo(code);
			// 获取期间
			PeriodCalendar periodCalendar = periodCalendarBiz.selectByBizdate(entity.getBizDate(), param);
			if (periodCalendar == null) {
				throw new AppException("com.armitage.server.iars.daily.selectByBizdate.error");
			}
			entity.setPeriodId(periodCalendar.getPeriodId());
			entity.setAccountYear(periodCalendar.getAccountYear());
			entity.setAccountPeriod(periodCalendar.getAccountPeriod());
			// 设置移出成本中心和移入成本中心
			setOutOrInCostCenter(entity, param);
			List<OrgCompany2> orgCompanylist2 = orgUnitRelationBiz.findToOrgUnitByType(OrgUnitRelationType.COSTTOFIN, entity.getOutCstOrgUnitNo(), false, null, param);
			if(orgCompanylist2 != null && !orgCompanylist2.isEmpty()){
				entity.setFinOrgUnitNo(orgCompanylist2.get(0).getOrgUnitNo());
			}
		}
	}

	private void setOutOrInCostCenter(ScmInvMoveBill2 entity, Param param) {
		List<OrgCostCenter2> outOrgCostCenterList = orgUnitRelationBiz.findToOrgUnitByType(OrgUnitRelationType.ADMINTOCOST,entity.getOutOrgUnitNo(), false, null, param);
		if (outOrgCostCenterList == null || outOrgCostCenterList.isEmpty()) {
			throw new AppException("iscm.inventorymanage.cstbusiness.service.impl.ScmInvMoveBillBizImpl.noOutCst");
		}
		entity.setOutCstOrgUnitNo(outOrgCostCenterList.get(0).getOrgUnitNo());
		List<OrgCostCenter2> inOrgCostCenterList = orgUnitRelationBiz.findToOrgUnitByType(OrgUnitRelationType.ADMINTOCOST,entity.getInOrgUnitNo(), false, null, param);
		if (inOrgCostCenterList == null || inOrgCostCenterList.isEmpty()) {
			throw new AppException("iscm.inventorymanage.cstbusiness.service.impl.ScmInvMoveBillBizImpl.noInCst");
		}
		entity.setInCstOrgUnitNo(inOrgCostCenterList.get(0).getOrgUnitNo());
	}

	@Override
	protected void beforeUpdate(ScmInvMoveBill2 oldEntity,
			ScmInvMoveBill2 newEntity, Param param) throws AppException {
		// 获取期间
		PeriodCalendar periodCalendar = periodCalendarBiz.selectByBizdate(
				newEntity.getBizDate(), param);
		if (periodCalendar == null) {
			throw new AppException(
					"com.armitage.server.iars.daily.selectByBizdate.error");
		}
		newEntity.setPeriodId(periodCalendar.getPeriodId());
		newEntity.setAccountYear(periodCalendar.getAccountYear());
		newEntity.setAccountPeriod(periodCalendar.getAccountPeriod());
		setOutOrInCostCenter(newEntity, param);
	}

	@Override
	protected void afterAdd(CommonBean bean, Param param) throws AppException {
		ScmInvMoveBill2 scmInvMoveBill = (ScmInvMoveBill2) bean.getList().get(0);
		if (scmInvMoveBill != null && scmInvMoveBill.getWtId() > 0) {
			// 新增入库明细
			List<ScmInvMoveBillEntry2> scmInvMoveBillEntryList = bean.getList2();
			if (scmInvMoveBillEntryList != null	&& !scmInvMoveBillEntryList.isEmpty()) {
				int lineId = 1;
				for (ScmInvMoveBillEntry2 scmInvMoveBillEntry : scmInvMoveBillEntryList) {
					scmInvMoveBillEntry.setLineId(lineId);
					scmInvMoveBillEntry.setWtId(scmInvMoveBill.getWtId());
					ScmMaterial2 scmMaterial = scmMaterialBiz.selectDirect(scmInvMoveBillEntry.getItemId(), param);
					if (scmMaterial == null) {
						throw new AppException("iscm.purchasemanage.purchasebusiness.ScmPurRequireBizImpl.error.itemnorexists");
					}
					scmInvMoveBillEntry.setBaseUnit(scmMaterial.getBaseUnitId());
					BigDecimal invConvRate = ScmMaterialUtil.getConvRate(scmInvMoveBillEntry.getItemId(),scmInvMoveBillEntry.getUnit(), param);// 库存单位转换系数
					scmInvMoveBillEntry.setBaseQty(scmInvMoveBillEntry.getQty().multiply(invConvRate));
					scmInvMoveBillEntryBiz.add(scmInvMoveBillEntry, param);
					lineId = lineId + 1;
				}
				checkBillQty(scmInvMoveBill,param);
			}
		}
	}
	
	@Override
	protected void afterSelect(ScmInvMoveBill2 entity, Param param)
			throws AppException {
		if (entity != null) {
			HashMap<String, Object> cacheMap = new HashMap<String, Object>();
			// 库存组织
			if (StringUtils.isNotBlank(entity.getCreateOrgUnitNo())) {
				OrgBaseUnit orgBaseUnit = orgUnitBiz.selectbyOrgNo(
						entity.getCreateOrgUnitNo(), param);
				if (orgBaseUnit != null) {
					entity.setConvertMap(ScmInvMoveBill2.FN_CREATEORGUNITNO,
							orgBaseUnit);
				}
			}
			// 移入部门
			if (StringUtils.isNotBlank(entity.getInOrgUnitNo())) {
				OrgBaseUnit orgBaseUnit = orgUnitBiz.selectbyOrgNo(
						entity.getInOrgUnitNo(), param);
				if (orgBaseUnit != null) {
					entity.setConvertMap(ScmInvMoveBill2.FN_INORGUNITNO,
							orgBaseUnit);
				}
			}
			// 移出部门
			if (StringUtils.isNotBlank(entity.getOutOrgUnitNo())) {
				OrgBaseUnit orgBaseUnit = orgUnitBiz.selectbyOrgNo(
						entity.getOutOrgUnitNo(), param);
				if (orgBaseUnit != null) {
					entity.setConvertMap(ScmInvMoveBill2.FN_OUTORGUNITNO,
							orgBaseUnit);
				}
			}
			if (StringUtils.isNotBlank(entity.getCreator())) {
				// 制单人
				Usr usr = (Usr) cacheMap.get(ClassUtils
						.getFinalModelSimpleName(Usr.class)
						+ "_"
						+ entity.getCreator());
				if (usr == null) {
					usr = usrBiz.selectByCode(entity.getCreator(), param);
					cacheMap.put(ClassUtils.getFinalModelSimpleName(Usr.class)
							+ "_" + entity.getCreator(), usr);
				}
				if (usr != null) {
					entity.setConvertMap(ScmInvMoveBill2.FN_CREATOR, usr);
				}
			}
			if (StringUtils.isNotBlank(entity.getEditor())) {
				// 修改人
				Usr usr = (Usr) cacheMap.get(ClassUtils
						.getFinalModelSimpleName(Usr.class)
						+ "_"
						+ entity.getEditor());
				if (usr == null) {
					usr = usrBiz.selectByCode(entity.getEditor(), param);
					cacheMap.put(ClassUtils.getFinalModelSimpleName(Usr.class)
							+ "_" + entity.getEditor(), usr);
				}
				if (usr != null) {
					entity.setConvertMap(ScmInvMoveBill2.FN_EDITOR, usr);
				}
			}
			if (StringUtils.isNotBlank(entity.getChecker())) {
				// 审核人
				Usr usr = (Usr) cacheMap.get(ClassUtils
						.getFinalModelSimpleName(Usr.class)
						+ "_"
						+ entity.getChecker());
				if (usr == null) {
					usr = usrBiz.selectByCode(entity.getChecker(), param);
					cacheMap.put(ClassUtils.getFinalModelSimpleName(Usr.class)
							+ "_" + entity.getChecker(), usr);
				}
				if (usr != null) {
					entity.setConvertMap(ScmInvMoveBill2.FN_CHECKER, usr);
				}
			}
			
			entity.setTaxAmount(entity.getTaxAmt().subtract(entity.getAmt()));
		}
	}

	@Override
	protected void afterSelect(CommonBean bean, Param param)
			throws AppException {
		ScmInvMoveBill2 scmInvMoveBill = (ScmInvMoveBill2) bean.getList()
				.get(0);
		if (scmInvMoveBill != null && scmInvMoveBill.getWtId() > 0) {
			HashMap<String, Object> cacheMap = new HashMap<String, Object>();
			List<ScmInvMoveBillEntry2> scmInvMoveBillEntryList = scmInvMoveBillEntryBiz
					.selectByWtId(scmInvMoveBill.getWtId(), param);
			if (scmInvMoveBillEntryList != null
					&& !scmInvMoveBillEntryList.isEmpty()) {
				for (ScmInvMoveBillEntry2 scmInvMoveBillEntry : scmInvMoveBillEntryList) {
					if (scmInvMoveBillEntry.getTaxRate() != null) {
						// 税率
						PubSysBasicInfo pubSysBasicInfo = pubSysBasicInfoBiz
								.selectByTaxRate("TaxRate", scmInvMoveBillEntry
										.getTaxRate().toString(), null, param);
						if (pubSysBasicInfo != null) {
							scmInvMoveBillEntry.setTaxRateStr(pubSysBasicInfo
									.getFInfoNo());
							scmInvMoveBillEntry.setConvertMap(
									ScmInvMoveBillEntry2.FN_TAXRATESTR,
									pubSysBasicInfo);
						}
					}
					if (StringUtils.isNotBlank(scmInvMoveBillEntry.getLot())) {
		                //批次
					    scmInvMoveBillEntry.setConvertMap(ScmInvMoveBillEntry2.FN_LOT, scmInvMoveBillEntry);
		            }
				}
				bean.setList2(scmInvMoveBillEntryList);
			}
		}
	}

	@Override
	protected void afterFindPage(List list, Page page, Param param)	throws AppException {
		if (list != null && !list.isEmpty()) {
			for (ScmInvMoveBill2 scmInvMoveBill : (List<ScmInvMoveBill2>)list) {
				this.setConvertMap(scmInvMoveBill, param);
				//视图增加待审人
				ScmBillPending2 scmBillPendingUsr = scmBillPendingBiz.selectPendingUsr(scmInvMoveBill.getWtId(), "InvMoveBill",param);
				StringBuffer usrName = new StringBuffer("");
				if (scmBillPendingUsr != null) {
					scmInvMoveBill.setPendingUsr(scmBillPendingUsr.getUsrCodes());
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
				scmInvMoveBill.setPendingUsrName(usrName.toString());
			}
		}
	}

	private void setConvertMap(ScmInvMoveBill2 scmInvMoveBill,Param param) {
		if(scmInvMoveBill!=null) {
			if (StringUtils.isNotBlank(scmInvMoveBill.getCreator())) {
				// 制单人
				Usr usr = usrBiz.selectByCode(scmInvMoveBill.getCreator(),param);
				if (usr != null) {
					if (scmInvMoveBill.getDataDisplayMap() == null) {
						scmInvMoveBill.setDataDisplayMap(new HashMap<String, SimpleDataDisplay>());
					}
					scmInvMoveBill.getDataDisplayMap().put(ScmInvMoveBill2.FN_CREATOR,SimpleDataDisplayUtil.convert(usr));
					scmInvMoveBill.setCreatorName(usr.getName());	
				}
			}
			if (StringUtils.isNotBlank(scmInvMoveBill.getCreateOrgUnitNo())) {
				// 库存组织
				OrgBaseUnit orgBaseUnit = orgUnitBiz.selectbyOrgNo(scmInvMoveBill.getCreateOrgUnitNo(), param);
				if (orgBaseUnit != null) {
					scmInvMoveBill.setConvertMap(ScmInvMoveBill2.FN_CREATEORGUNITNO,orgBaseUnit);
					scmInvMoveBill.setCreateOrgUnitName(orgBaseUnit.getOrgUnitName());
				}
			}
			if (StringUtils.isNotBlank(scmInvMoveBill.getOutOrgUnitNo())) {
				// 移出部门
				OrgBaseUnit orgBaseUnit = orgUnitBiz.selectbyOrgNo(scmInvMoveBill.getOutOrgUnitNo(), param);
				if (orgBaseUnit != null) {
					scmInvMoveBill.setConvertMap(ScmInvMoveBill2.FN_OUTORGUNITNO, orgBaseUnit);
					scmInvMoveBill.setOutOrgUnitName(orgBaseUnit.getOrgUnitName());
				}
			}
			if (StringUtils.isNotBlank(scmInvMoveBill.getInOrgUnitNo())) {
				// 移入部门
				OrgBaseUnit orgBaseUnit = orgUnitBiz.selectbyOrgNo(scmInvMoveBill.getInOrgUnitNo(), param);
				if (orgBaseUnit != null) {
					scmInvMoveBill.setConvertMap(ScmInvMoveBill2.FN_INORGUNITNO, orgBaseUnit);
					scmInvMoveBill.setInOrgUnitName(orgBaseUnit.getOrgUnitName());
				}
			}
			if (StringUtils.isNotBlank(scmInvMoveBill.getEditor())) {
				// 修改人
				Usr usr = usrBiz.selectByCode(scmInvMoveBill.getEditor(), param);
				if (usr != null) {
					scmInvMoveBill.setConvertMap(ScmInvMoveBill2.FN_EDITOR, usr);
					scmInvMoveBill.setEditorName(usr.getName());
				}
			}
			if (StringUtils.isNotBlank(scmInvMoveBill.getChecker())) {
				// 审核人
				Usr usr = usrBiz.selectByCode(scmInvMoveBill.getChecker(), param);
				if (usr != null) {
					scmInvMoveBill.setConvertMap(ScmInvMoveBill2.FN_CHECKER, usr);
					scmInvMoveBill.setCheckerName(usr.getName());
				}
			}
			if(StringUtils.isNotBlank(scmInvMoveBill.getStatus())){
				Code code = codeBiz.selectByCategoryAndCode("cstStatus", scmInvMoveBill.getStatus());
				if(code!=null)
					scmInvMoveBill.setStatusName(code.getName());
			}
			
			scmInvMoveBill.setTaxAmount(scmInvMoveBill.getTaxAmt().subtract(scmInvMoveBill.getAmt()));
			
		}
	}
	
	@Override
	protected void afterUpdate(CommonBean bean, Param param)
			throws AppException {
		ScmInvMoveBill2 scmInvMoveBill = (ScmInvMoveBill2) bean.getList().get(0);
		if (scmInvMoveBill != null && scmInvMoveBill.getWtId() > 0) {
			// 更新入库明细
			List<ScmInvMoveBillEntry2> scmInvMoveBillEntryList = bean.getList2();
			if (scmInvMoveBillEntryList != null	&& !scmInvMoveBillEntryList.isEmpty()) {
				int lineId = 1;
				for (ScmInvMoveBillEntry2 scmInvMoveBillEntry : scmInvMoveBillEntryList) {
					if (StringUtils.equals("I", scmInvMoveBill.getStatus())) {
						scmInvMoveBillEntry.setLineId(lineId);
					}
					scmInvMoveBillEntry.setWtId(scmInvMoveBill.getWtId());
					ScmMaterial2 scmMaterial = scmMaterialBiz.selectDirect(scmInvMoveBillEntry.getItemId(), param);
					if (scmMaterial == null) {
						throw new AppException("iscm.purchasemanage.purchasebusiness.ScmPurRequireBizImpl.error.itemnorexists");
					}
					scmInvMoveBillEntry.setBaseUnit(scmMaterial.getBaseUnitId());
					BigDecimal invConvRate = ScmMaterialUtil.getConvRate(scmInvMoveBillEntry.getItemId(),scmInvMoveBillEntry.getUnit(), param);// 库存单位转换系数
					scmInvMoveBillEntry.setBaseQty(scmInvMoveBillEntry.getQty().multiply(invConvRate));
					scmInvMoveBillEntry.setWtId(scmInvMoveBill.getWtId());
					lineId = lineId + 1;
				}
				scmInvMoveBillEntryBiz.update(scmInvMoveBill, scmInvMoveBillEntryList, ScmInvMoveBillEntry2.FN_WTID, param);
				checkBillQty(scmInvMoveBill,param);
			}
		}
	}

	private void checkBillQty(ScmInvMoveBill2 scmInvMoveBill, Param param) {
		List<ScmInvMoveBill2> list = new ArrayList<>();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("wtId", scmInvMoveBill.getWtId());
		map.put("type", "1");
		list = ((ScmInvMoveBillDAO) dao).selectInvQty(map);
		if (list != null && list.size() > 0) {
			StringBuffer itemName = new StringBuffer("");
			for (ScmInvMoveBill2 scmInvMoveBill2 : list) {
				if (scmInvMoveBill2.getInvQty().compareTo(scmInvMoveBill2.getQty()) < 0) {
					if(StringUtils.isNotBlank(itemName.toString())) itemName.append(",");
					itemName.append(scmInvMoveBill2.getItemName());
				}
			}
			if(StringUtils.isNotBlank(itemName.toString())) {
				throw new AppException(Message.getMessage(param.getLang(), "iscm.cstbusiness.controller.ScmInvMoveBill.Qty.outOfRange", new String[]{itemName.toString()}));
			}
		}
	}
	
	private ScmInvMoveBill2 updateStatus(ScmInvMoveBill2 scmInvMoveBill, Param param)
			throws AppException {
		if (scmInvMoveBill != null) {
			return this.updateDirect(scmInvMoveBill,param);
		}
		return null;
	}

	@Override
	protected void afterDelete(ScmInvMoveBill2 entity, Param param)
			throws AppException {
		if (entity != null && entity.getWtId() > 0) {
			// 删除入库明细
			scmInvMoveBillEntryBiz.deleteByWtId(entity.getWtId(), param);
		}
	}

	@Override
	public ScmInvMoveBill2 postBill(ScmInvMoveBill2 scmInvMoveBill, Param param) throws AppException {
		scmInvMoveBill = this.selectDirect(scmInvMoveBill.getWtId(), param);
	    if (scmInvMoveBill != null) {
            if(!StringUtils.equals("A",scmInvMoveBill.getStatus())) {
            	throw new AppException(Message.getMessage(param.getLang(), "iscm.inventorymanage.post.error2", new String[] {scmInvMoveBill.getWtNo()}));
    		}
			// 1 查出明细
			List<ScmInvMoveBillEntry2> scmInvMoveBillEntryList = scmInvMoveBillEntryBiz.selectByWtId(scmInvMoveBill.getWtId(), param);
			if (scmInvMoveBillEntryList != null	&& !scmInvMoveBillEntryList.isEmpty()) {
	        	String fields[]={"itemId","id"};
				String sorts[]={"ASC","DESC"};
				scmInvMoveBillEntryList = (List<ScmInvMoveBillEntry2>)ListSortUtil.sort(scmInvMoveBillEntryList, fields,sorts);	//按物资排序
				int amtPrecision = Integer.parseInt(sysParamBiz.getValue(param.getOrgUnitNo(), "SCM_AmtPrecision", "2", param));
				HashMap<String,List<ScmInvStock2>> qtyMap = new HashMap<String,List<ScmInvStock2>>();
				int lineId=1;
				for (ScmInvMoveBillEntry2 scmInvMoveBillEntry2 : scmInvMoveBillEntryList) {
					StringBuffer info = new StringBuffer("");
					info.append(scmInvMoveBillEntry2.getOutOrgUnitNo()).append("_")
							.append(String.valueOf(scmInvMoveBillEntry2.getItemId()));
					if(!qtyMap.containsKey((info.toString()))){
						// 2 有没有批次都要拆单, 查询结存(先进先出还是后进先出)
						Page page = new Page();
						page.setModelClass(ScmMaterial2.class);
						page.setShowCount(Integer.MAX_VALUE);
						page.setSqlCondition("ScmMaterial.id="+ scmInvMoveBillEntry2.getItemId());
						List<String> argList = new ArrayList<String>();
						argList.add("orgUnitNo=" + param.getOrgUnitNo());
						argList.add("controlUnitNo=" + param.getControlUnitNo());
						List<ScmMaterial2> materialList = scmMaterialBiz.queryPage(page, argList, "findBySingleFinAllPage", param);
						HashMap<String, Object> map = new HashMap<String, Object>();
						map.put("orgUnitNo", scmInvMoveBill.getOutOrgUnitNo());
						map.put("itemId", scmInvMoveBillEntry2.getItemId());
						map.put("wareHouseId", 0);
						map.put("bizDate", scmInvMoveBill.getBizDate());
						map.put("unit", scmInvMoveBillEntry2.getUnit());
						// TODO 获取costMethod来决定升序还是降序排序
						if (materialList != null && materialList.size() > 0) {
							map.put("costMethod", materialList.get(0).getCosting());
						}
						List<ScmInvStock2> list = scmInvStockBiz.findByDate(map,param);
						if (list == null || list.size() == 0) {
							throw new AppException(
									"com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.service.impl.common.stockNotEnough",
									new String[] { materialList.get(0)
											.getItemName() });
						}
						qtyMap.put(info.toString(), list);
					}
					List<ScmInvStock2> stocklist = qtyMap.get(info.toString());
    				if(stocklist != null && !stocklist.isEmpty()){
    					if(StringUtils.isNotBlank(scmInvMoveBillEntry2.getLot())) {
                    		//有批次则先按批次找，如不够再按先进先出拆单
                    		for (ScmInvStock2 scmInvStock : stocklist) {
                    			if(StringUtils.equals(scmInvMoveBillEntry2.getLot(), scmInvStock.getLot())) {
    		                		if(scmInvMoveBillEntry2.getQty().compareTo(BigDecimal.ZERO)>0) {
    		                			if(setDataFromStock(scmInvStock,scmInvMoveBillEntry2,amtPrecision,lineId,param))
    		                				lineId = lineId +1;
    		                		}else {
    		                			break;
    		                		}
                    			}
                    		}
                    	}
                    	for (ScmInvStock2 scmInvStock : stocklist) {
                    		if(scmInvMoveBillEntry2.getQty().compareTo(BigDecimal.ZERO)>0) {
                    			if(setDataFromStock(scmInvStock,scmInvMoveBillEntry2,amtPrecision,lineId,param))
	                				lineId = lineId +1;
                    		}else {
                    			break;
                    		}
                    	}
						// 删除原来的明细
						scmInvMoveBillEntryBiz.delete(scmInvMoveBillEntry2, param);
					}
				}
			}
			// 即使库存(结存)
			scmInvMoveBillEntryList = scmInvMoveBillEntryBiz.selectOutEffectRow(scmInvMoveBill.getWtId(), param);
			int updateRow = scmInvStockBiz.updateByMoveBillOutSub(scmInvMoveBill.getWtId(),param);	//减移出方结存
			if(updateRow<scmInvMoveBillEntryList.size()){
    			throw new AppException("iscm.inventorymanage.common.post.error.rowsnotequal");
    		}
			scmInvStockBiz.updateByMoveBillInPlus(scmInvMoveBill.getWtId(),param);	//增加移入方结存
			scmInvStockBiz.addByMoveBillIn(scmInvMoveBill.getWtId(), param);
			// 期间余额
			scmInvBalBiz.updateByMoveBillOutSub(scmInvMoveBill.getWtId(), param);
			scmInvBalBiz.updateByMoveBillInPlus(scmInvMoveBill.getWtId(), param);
			scmInvBalBiz.addByMoveBillIn(scmInvMoveBill.getWtId(), param);
			// 期间移动平均即时成本表
//			scmInvCostBiz.updateByMoveBillOutSub(scmInvMoveBill.getWtId(),param);
//			scmInvCostBiz.updateByMoveBillInPlus(scmInvMoveBill.getWtId(),param);
//			scmInvCostBiz.addByMoveBillIn(scmInvMoveBill.getWtId(), param);
			if (!(ParamType.Operation.equals(param.getParamType()) && ((OperationParam) param).getOperationId()==629223199L)) {
				scmInvMoveBill.setCheckDate(CalendarUtil.getDate(param));
				scmInvMoveBill.setChecker(param.getUsrCode());
				scmInvMoveBill.setPostDate(CalendarUtil.getDate(param));
			}
			scmInvMoveBill.setStatus("E");
			updateRow = this.updatePostedStatus(scmInvMoveBill, param);
			if(updateRow!=1) {
				throw new AppException(Message.getMessage(param.getLang(), "iscm.inventorymanage.post.error2", new String[] {scmInvMoveBill.getWtNo()}));
			}
		}
		return null;
	}

	private int updatePostedStatus(ScmInvMoveBill2 scmInvMoveBill, Param param) {
		HashMap<String,Object> map = new HashMap<String,Object> ();
		map.put("wtId",scmInvMoveBill.getWtId());
		map.put("checker",scmInvMoveBill.getChecker());
		map.put("checkDate",scmInvMoveBill.getCheckDate()==null?null:FormatUtils.fmtDate(scmInvMoveBill.getCheckDate()));
		map.put("status", scmInvMoveBill.getStatus());
		map.put("postDate", scmInvMoveBill.getPostDate()==null?null:FormatUtils.fmtDateTime(scmInvMoveBill.getPostDate()));
		return ((ScmInvMoveBillDAO)dao).updatePostedStatus(map);
	}

	private boolean setDataFromStock(ScmInvStock2 scmInvStock,ScmInvMoveBillEntry2 scmInvMoveBillEntry2,int amtPrecision,int lineId,Param param) {
		boolean flag = false;
		BigDecimal stockQty = scmInvStock.getQty();
		BigDecimal stockPieQty = scmInvStock.getPieQty();
		BigDecimal stockBaseQty = scmInvStock.getBaseQty();
		BigDecimal stockAmt = (scmInvStock.getAmt());
		BigDecimal stockTaxAmt = (scmInvStock.getTaxAmt());
		if (stockQty.compareTo(scmInvMoveBillEntry2.getQty()) > 0) {
			ScmInvMoveBillEntry2 scmInvMoveBillEntry = new ScmInvMoveBillEntry2();
			BeanUtils.copyProperties(scmInvMoveBillEntry2,scmInvMoveBillEntry,new String[] { "id" });
			scmInvMoveBillEntry.setLot(scmInvStock.getLot());
			scmInvMoveBillEntry.setStockId(scmInvStock.getId());
			scmInvMoveBillEntry.setPrice(scmInvStock.getPrice());
			scmInvMoveBillEntry.setTaxPrice(scmInvStock.getTaxPrice());
			scmInvMoveBillEntry.setTaxRate(scmInvStock.getTaxRate());
			scmInvMoveBillEntry.setAmt(scmInvMoveBillEntry.getQty().multiply(scmInvMoveBillEntry.getPrice()).setScale(amtPrecision, RoundingMode.HALF_UP));
			scmInvMoveBillEntry.setTaxAmt(scmInvMoveBillEntry.getQty().multiply(scmInvMoveBillEntry.getTaxPrice()).setScale(amtPrecision, RoundingMode.HALF_UP));
			scmInvMoveBillEntry.setLineId(lineId);
			scmInvMoveBillEntry.setOrgSourceId(scmInvStock.getSourceBillId());
			scmInvMoveBillEntry.setOrgSourceVendorId(scmInvStock.getVendorId());
			scmInvMoveBillEntry.setInvDate(scmInvStock.getInvDate());
			scmInvMoveBillEntryBiz.add(scmInvMoveBillEntry,param);
			scmInvMoveBillEntry2.setQty(BigDecimal.ZERO);
			scmInvMoveBillEntry2.setPieQty(BigDecimal.ZERO);
			scmInvMoveBillEntry2.setBaseQty(BigDecimal.ZERO);
    		scmInvStock.setQty(scmInvStock.getQty().subtract(scmInvMoveBillEntry.getQty()));
    		scmInvStock.setPieQty(scmInvStock.getPieQty().subtract(scmInvMoveBillEntry.getPieQty()));
    		scmInvStock.setBaseQty(scmInvStock.getBaseQty().subtract(scmInvMoveBillEntry.getBaseQty()));
    		scmInvStock.setAmt(scmInvStock.getAmt().subtract(scmInvMoveBillEntry.getAmt()));
    		scmInvStock.setTaxAmt(scmInvStock.getTaxAmt().subtract(scmInvMoveBillEntry.getTaxAmt()));
    		flag = true;
		}else if (stockQty.compareTo(BigDecimal.ZERO) > 0) {
			ScmInvMoveBillEntry2 scmInvMoveBillEntry = new ScmInvMoveBillEntry2();
			BeanUtils.copyProperties(scmInvMoveBillEntry2,scmInvMoveBillEntry,new String[] { "id" });
			scmInvMoveBillEntry.setLot(scmInvStock.getLot());
			scmInvMoveBillEntry.setStockId(scmInvStock.getId());
			scmInvMoveBillEntry.setPrice(scmInvStock.getPrice());
			scmInvMoveBillEntry.setTaxPrice(scmInvStock.getTaxPrice());
			scmInvMoveBillEntry.setTaxRate(scmInvStock.getTaxRate());
			scmInvMoveBillEntry.setQty(stockQty);
			scmInvMoveBillEntry.setPieQty(stockPieQty);
			scmInvMoveBillEntry.setBaseQty(stockBaseQty);
			scmInvMoveBillEntry.setAmt(stockAmt);
			scmInvMoveBillEntry.setTaxAmt(stockTaxAmt);
			scmInvMoveBillEntry.setLineId(lineId);
			scmInvMoveBillEntry.setOrgSourceId(scmInvStock.getSourceBillId());
			scmInvMoveBillEntry.setOrgSourceVendorId(scmInvStock.getVendorId());
			scmInvMoveBillEntry.setInvDate(scmInvStock.getInvDate());
			scmInvMoveBillEntryBiz.add(scmInvMoveBillEntry,param);
			scmInvMoveBillEntry2.setQty(scmInvMoveBillEntry2.getQty().subtract(scmInvMoveBillEntry.getQty()));
			scmInvMoveBillEntry2.setPieQty(scmInvMoveBillEntry2.getPieQty().subtract(scmInvMoveBillEntry.getPieQty()));
			scmInvMoveBillEntry2.setBaseQty(scmInvMoveBillEntry2.getBaseQty().subtract(scmInvMoveBillEntry.getBaseQty()));
    		scmInvStock.setQty(BigDecimal.ZERO);
    		scmInvStock.setPieQty(BigDecimal.ZERO);
    		scmInvStock.setBaseQty(BigDecimal.ZERO);
    		scmInvStock.setAmt(BigDecimal.ZERO);
    		scmInvStock.setTaxAmt(BigDecimal.ZERO);
    		flag = true;
		}
		return flag;
	}
	@Override
	public List<String> postBillCheck(ScmInvMoveBill2 scmInvMoveBill, String type,Param param)
			throws AppException {
		//type:1过账；2反过账
	    // 1 财务组织会计期间等于当前期间
	    List<String> msgList = new ArrayList<String>();// 提示列表
	    scmInvMoveBill = this.selectDirect(scmInvMoveBill.getWtId(), param);
	    if (scmInvMoveBill != null) {
	    	if(StringUtils.equals(type, "1")) {
	            if(!StringUtils.equals("A",scmInvMoveBill.getStatus())) {
	    			msgList.add(Message.getMessage(param.getLang(), "iscm.inventorymanage.post.error2", new String[] {scmInvMoveBill.getWtNo()}));
	    			return msgList;
	    		}
	    	}else {
	            if(!StringUtils.equals("E",scmInvMoveBill.getStatus())) {
	    			msgList.add(Message.getMessage(param.getLang(), "iscm.inventorymanage.cancelPost.error2", new String[] {scmInvMoveBill.getWtNo()}));
	    			return msgList;
	    		}
	    	}
	        SystemState systemState = systemStateBiz.selectBySystemId(scmInvMoveBill.getFinOrgUnitNo(), 170, param);
            if(systemState==null){
                msgList.add(Message.getMessage(param.getLang(),"com.armitage.iars.daily.util.disenable.unable"));
                return msgList;
            }
            if (systemState.getCurrentPeriodId() != scmInvMoveBill.getPeriodId()) {
                msgList.add(Message.getMessage(param.getLang(), 
                        "com.armitage.server.iscm.postBill.peroidId.error.notCurrent", new String[] {scmInvMoveBill.getWtNo()}));
                return msgList;
            }
            // 检查盘存物资冻结
            // 检查冻结仓库
            HashMap<String,Object> checkFreeMap = new HashMap<String,Object>();
            checkFreeMap.put("wtId", scmInvMoveBill.getWtId());
            List<ScmInvMoveBill2> scmInvMoveBillList = ((ScmInvMoveBillDAO) dao).checkWareHouseFree(checkFreeMap);
            if (scmInvMoveBillList != null && !scmInvMoveBillList.isEmpty()) {
            	msgList.add(Message.getMessage(param.getLang(), 
                        "com.armitage.server.iscm.postBill.peroidId.error.countingTask.countingCostcenter"));
                return msgList;
            }
        }
		// 2 批次, 过账数小于结存数
		List<ScmInvMoveBill2> list = new ArrayList<>();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("wtId", scmInvMoveBill.getWtId());
		map.put("type", type);
		list = ((ScmInvMoveBillDAO) dao).selectInvQty(map);
		if (list != null && list.size() > 0) {
			for (ScmInvMoveBill2 scmInvMoveBill2 : list) {
				if (scmInvMoveBill2.getInvQty().compareTo(scmInvMoveBill2.getQty()) < 0) {
					String[] params = new String[] {scmInvMoveBill2.getItemName()};
					if(StringUtils.equals("1", type)) {
						msgList.add(Message.getMessage(param.getLang(),"iscm.inventorymanage.cstbusiness.service.impl.ScmInvMoveBillBizImpl.count.error",params));
					}else {
						msgList.add(Message.getMessage(param.getLang(),"iscm.inventorymanage.cstbusiness.service.impl.cancelpost.error.notqty",params));
					}
				}
			}
			return msgList;
		}
		return msgList;
	}

	@Override
	public ScmInvMoveBill2 cancelPostBill(ScmInvMoveBill2 scmInvMoveBill,
			Param param) throws AppException {
		scmInvMoveBill = this.selectDirect(scmInvMoveBill.getWtId(), param);
	    if (scmInvMoveBill != null) {
            if(!StringUtils.equals("E",scmInvMoveBill.getStatus())) {
            	throw new AppException(Message.getMessage(param.getLang(), "iscm.inventorymanage.cancelPost.error2", new String[] {scmInvMoveBill.getWtNo()}));
    		}	
            
            // 检查盘存物资冻结
            // 检查冻结仓库
            HashMap<String,Object> checkFreeMap = new HashMap<String,Object>();
            checkFreeMap.put("wtId", scmInvMoveBill.getWtId());
            List<ScmInvMoveBill2> scmInvMoveBillList = ((ScmInvMoveBillDAO) dao).checkWareHouseFree(checkFreeMap);
            if (scmInvMoveBillList != null && !scmInvMoveBillList.isEmpty()) {
            	throw new AppException(Message.getMessage(param.getLang(), 
                        "com.armitage.server.iscm.postBill.peroidId.error.countingTask.countingCostcenter"));
            }
            
			// 即使库存(结存)
			List<ScmInvMoveBillEntry2> scmInvMoveBillEntryList = scmInvMoveBillEntryBiz.selectCancelEffectRow(scmInvMoveBill.getWtId(), param);
			int updateRow = scmInvStockBiz.updateByMoveBillInSub(scmInvMoveBill.getWtId(),param);
			if(updateRow<scmInvMoveBillEntryList.size()){
    			throw new AppException("iscm.inventorymanage.common.post.error.rowsnotequal");
    		}
			scmInvStockBiz.updateByMoveBillOutPlus(scmInvMoveBill.getWtId(),param);
			scmInvStockBiz.addByMoveBillOut(scmInvMoveBill.getWtId(), param);
			// 期间余额
			scmInvBalBiz.updateByMoveBillInSub(scmInvMoveBill.getWtId(), param);
			scmInvBalBiz.updateByMoveBillOutPlus(scmInvMoveBill.getWtId(),param);
			scmInvBalBiz.addByMoveBillOut(scmInvMoveBill.getWtId(), param);
			// 期间移动平均即时成本表
//			scmInvCostBiz.updateByMoveBillInSub(scmInvMoveBill.getWtId(), param);
//			scmInvCostBiz.updateByMoveBillOutPlus(scmInvMoveBill.getWtId(),	param);
//			scmInvCostBiz.addByMoveBillOut(scmInvMoveBill.getWtId(), param);
			scmInvMoveBill.setCheckDate(null);
			scmInvMoveBill.setChecker("");
			scmInvMoveBill.setStatus("A");
			updateRow = this.updatePostedStatus(scmInvMoveBill, param);
			if(updateRow!=1) {
				throw new AppException(Message.getMessage(param.getLang(), "iscm.inventorymanage.cancelPost.error2", new String[] {scmInvMoveBill.getWtNo()}));
			}
		}

		return scmInvMoveBill;
	}
	
	@Override
	protected void beforeDelete(ScmInvMoveBill2 entity, Param param)
			throws AppException {
		ScmInvMoveBill2 scmInvMoveBill = this.selectDirect(entity.getWtId(), param);
		if (scmInvMoveBill!=null && !StringUtils.equals(scmInvMoveBill.getStatus(), "I")) {
			throw new AppException(Message.getMessage(param.getLang(),
					"iscm.inventorymanage.delete.error",
					new String[] { entity.getWtNo() }));
		}
	}

	@Override
	protected void beforeUpdate(CommonBean bean, Param param) throws AppException {
		ScmInvMoveBill2 entry = (ScmInvMoveBill2) bean.getList().get(0);
		if(entry!=null){
			ScmInvMoveBill2 scmInvMoveBill = this.select(entry.getPK(), param);
			if(!StringUtils.contains("I,D,P", scmInvMoveBill.getStatus())){
				throw new AppException(Message.getMessage(param.getLang(), "iscm.commom.edit.error.notnewstatus"));
			}
		}
	}

	@Override
	public List<ScmInvMoveBill2> checkUnPostBill(String finOrgUnitNo,long periodId, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("finOrgUnitNo", finOrgUnitNo);
		map.put("periodId", periodId);
		return ((ScmInvMoveBillDAO)dao).checkUnPostBill(map);
	}

	@Override
	public List<ScmInvMoveBill2> checkPostedBill(String finOrgUnitNo,long periodId, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("finOrgUnitNo", finOrgUnitNo);
		map.put("periodId", periodId);
		return ((ScmInvMoveBillDAO)dao).checkPostedBill(map);
	}

	@Override
	public ScmInvMoveBill2 submit(ScmInvMoveBill2 scmInvMoveBill, Param param) throws AppException {
		ScmInvMoveBill2 scmInvMoveBillRtn = null;
		if(scmInvMoveBill.getWtId()>0){
			scmInvMoveBillRtn = this.selectDirect(scmInvMoveBill.getWtId(), param);
		}
		if(scmInvMoveBillRtn!=null){
			if(!StringUtils.equals(scmInvMoveBillRtn.getStatus(),"I")){
				throw new AppException("iscm.purchasemanage.error.commit");
			}else if(scmInvMoveBillRtn.getStatus().equals("I")){
				List<ScmInvMoveBillEntry2> scmInvMoveBillEntryList = scmInvMoveBillEntryBiz.selectByWtId(scmInvMoveBillRtn.getWtId(), param);
				
				BillType2 billType = billTypeBiz.selectByOrgAndCode(scmInvMoveBillRtn.getFinOrgUnitNo(), "InvMoveBill", param);
				boolean needAudit = false;
				if(billType!=null && billType.isNeedAudit())
					needAudit = true;
				if(needAudit){
					String processInstanceId = this.startWorkFlow(billType, scmInvMoveBillRtn, scmInvMoveBillEntryList, param);
					scmInvMoveBillRtn.setWorkFlowNo(processInstanceId);
					ActivityUtil activityUtil = new ActivityUtil();
					//判断当前流程是否结束
					boolean isCompleteTask = activityUtil.queryProInstanceStateByProInstanceId(processInstanceId);
					if(isCompleteTask) {
						if(param.getUsrCode()!=null ){
							scmInvMoveBillRtn.setChecker(param.getUsrCode());
							scmInvMoveBillRtn.setSubmitter(param.getUsrCode());
						}
						scmInvMoveBillRtn.setCheckDate(CalendarUtil.getDate(param));
						scmInvMoveBillRtn.setSubmitDate(CalendarUtil.getDate(param));
						scmInvMoveBillRtn.setStatus("A");
					}else {
						if(param.getUsrCode()!=null ){
							scmInvMoveBillRtn.setSubmitter(param.getUsrCode());
						}
						scmInvMoveBillRtn.setSubmitDate(CalendarUtil.getDate(param));
						scmInvMoveBillRtn.setStatus("D");
						this.sendAuditMsg(scmInvMoveBillRtn, billType.getBillCode(), param);
					}
				}else{
					if(billType.isUseFlow()) {
						//启用工作流（不影响状态）
						String processInstanceId = this.startWorkFlow(billType, scmInvMoveBillRtn, scmInvMoveBillEntryList, param);
						scmInvMoveBillRtn.setWorkFlowNo(processInstanceId);
						scmInvMoveBillRtn.setSubmitDate(CalendarUtil.getDate(param));
						ActivityUtil activityUtil = new ActivityUtil();
						//判断当前流程是否结束
						boolean isCompleteTask = activityUtil.queryProInstanceStateByProInstanceId(processInstanceId);
						if(!isCompleteTask) {
							sendAuditMsg(scmInvMoveBillRtn,billType.getBillCode(),param);
						}
					}
					if(param.getUsrCode()!=null ){
						scmInvMoveBillRtn.setChecker(param.getUsrCode());
						scmInvMoveBillRtn.setSubmitter(param.getUsrCode());
					}
					scmInvMoveBillRtn.setCheckDate(CalendarUtil.getDate(param));
					scmInvMoveBillRtn.setSubmitDate(CalendarUtil.getDate(param));
					scmInvMoveBillRtn.setStatus("A");
				}
				try {
					this.updateStatus(scmInvMoveBillRtn, param);
				} catch (Exception e) {
					throw e;
				}
				SystemState systemState = systemStateBiz.selectBySystemId(scmInvMoveBillRtn.getFinOrgUnitNo(), 170, param);
				if(systemState==null){
					throw new AppException("com.armitage.iars.daily.util.disenable.unable");
				}
				if (systemState.getCurrentPeriodId() == scmInvMoveBillRtn.getPeriodId()) {
					if(StringUtils.equals("A", scmInvMoveBillRtn.getStatus())) {
						//通过或部分通过时检查是否自动过帐
						if(billType!=null && billType.isAutoRelease()) {
							List<String> msgList = this.postBillCheck(scmInvMoveBillRtn, "1", param);
							if (msgList != null && !msgList.isEmpty()) {
								StringBuilder detailInfo = new StringBuilder("");
		                        detailInfo.append(Message.getMessage(param.getLang(),"iscm.inventorymanage.moveissuesbill.post.errorTitle"));
	
								for (String str : msgList) {
		                            detailInfo.append(str).append("\n");
		                        }
								
								throw new AppException(detailInfo.toString(), new String[]{scmInvMoveBillRtn.getWtNo()});
							}
							this.postBill(scmInvMoveBillRtn, param);
						}
					}
				}
			}
		}else{
			throw new AppException("iscm.purchasemanage.purchasebusiness.find.ordernotexists");
		}
		return scmInvMoveBillRtn;
	}

	private String startWorkFlow(BillType2 billType,ScmInvMoveBill2 scmInvMoveBillRtn,List<ScmInvMoveBillEntry2> scmInvMoveBillEntryList, Param param) {
		CommonBean bean = new CommonBean();
		List<ScmInvMoveBill2> scmInvMoveBillList = new ArrayList<>();
		scmInvMoveBillList.add(scmInvMoveBillRtn);
		bean.setList(scmInvMoveBillList);
		bean.setList2(scmInvMoveBillEntryList);
		String processInstanceId = ActivityUtil.startProcessInstance(bean, billType.getId(), billType.getWorkFlowNo(), param);
		return processInstanceId;
	}
	
	private void sendAuditMsg(ScmInvMoveBill2 scmInvMoveBillRtn,String billCode,Param param) {
		String usrList= ActivityUtil.findAssigneeByProcessInstanceId(scmInvMoveBillRtn.getWorkFlowNo(),param);
		if(StringUtils.isNotBlank(usrList)) {
			scmBillPendingBiz.addPendingBill(usrList, scmInvMoveBillRtn, param);
			InvMoveParams invMoveParams = new InvMoveParams();
			invMoveParams.setWtNo(scmInvMoveBillRtn.getWtNo());
			AuditMsgUtil.sendAuditMsg(billCode,scmInvMoveBillRtn,invMoveParams, usrList, param);
		}
	}
	@Override
	public ScmInvMoveBill2 undoSubmit(ScmInvMoveBill2 scmInvMoveBill, Param param) throws AppException {
		ScmInvMoveBill2 scmInvMoveBillRtn = null;
		if(scmInvMoveBill.getWtId()>0){
			scmInvMoveBillRtn = this.selectDirect(scmInvMoveBill.getWtId(), param);
		}
		if(scmInvMoveBillRtn!=null){
			BillType2 billType = billTypeBiz.selectByOrgAndCode(scmInvMoveBillRtn.getFinOrgUnitNo(), "InvMoveBill", param);
			boolean needAudit = false;
			if(billType!=null && billType.isNeedAudit() && StringUtils.isNotBlank(scmInvMoveBillRtn.getWorkFlowNo()))
				needAudit = true;
			
			//单据设置审批流程，但设置没有审批节点，状态为通过，可以取消通过
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put(ScmBillPending.FN_BILLID, new QueryParam(ScmBillPending.FN_BILLID, QueryParam.QUERY_EQ, String.valueOf(scmInvMoveBillRtn.getWtId())));
			map.put(ScmBillPending.FN_BILLTYPE, new QueryParam(ScmBillPending.FN_BILLTYPE, QueryParam.QUERY_EQ, billType.getBillCode()));
			List<ScmBillPending> scmBillPendingList = scmBillPendingBiz.findAll(map, param);
			
			if(needAudit){
				if (scmBillPendingList == null || scmBillPendingList.isEmpty()) {
					if(!StringUtils.equals(scmInvMoveBillRtn.getStatus(),"A"))
						throw new AppException("iscm.purchasemanage.error.cancelCommit");
				} else {
					if(!StringUtils.equals(scmInvMoveBillRtn.getStatus(),"D"))
						throw new AppException("iscm.purchasemanage.error.cancelCommit");
				}
			}else{
				//启用审批进入审核中(再调整为启用工作流)的单据的状态不是A
				if (scmBillPendingList == null || scmBillPendingList.isEmpty()) {
					if(!StringUtils.equals(scmInvMoveBillRtn.getStatus(),"A"))
						throw new AppException("iscm.purchasemanage.error.cancelCommit");
				} else {
					if(!StringUtils.contains("D,A",scmInvMoveBillRtn.getStatus()))
						throw new AppException("iscm.purchasemanage.error.cancelCommit");
				}
			} 
			if(scmInvMoveBillRtn.getStatus().equals("A") || scmInvMoveBillRtn.getStatus().equals("D")){
				scmInvMoveBillRtn.setChecker(null);
				scmInvMoveBillRtn.setSubmitter(null);
				scmInvMoveBillRtn.setCheckDate(null);
				scmInvMoveBillRtn.setSubmitDate(null);
				scmInvMoveBillRtn.setStatus("I");
				try {
					this.updateStatus(scmInvMoveBillRtn, param);
					scmBillPendingBiz.cancelPendingBill(param.getUsrCode(),scmInvMoveBillRtn, param);
				} catch (Exception e) {
					throw e;
				}
			}
		}else{
			throw new AppException("iscm.purchasemanage.purchasebusiness.find.ordernotexists");
		}
		return scmInvMoveBillRtn;
	}

	@Override
	public ScmInvMoveBill2 queryInvMove(ScmInvMoveBill2 scmInvMoveBill,
			Param param) throws AppException {
		Page page=new Page();
		page.setModelClass(ScmInvMoveBill2.class);
		page.setShowCount(Integer.MAX_VALUE);
		page.getParam().put(ScmInvMoveBill2.FN_WTNO,new QueryParam(ScmInvMoveBill2.FN_WTNO, QueryParam.QUERY_EQ, scmInvMoveBill.getWtNo()));
		
		List<ScmInvMoveBill2> scmInvMoveList =this.findPage(page, param);
		ScmInvMoveBill2 scmInvMove2 = new ScmInvMoveBill2();
		if(scmInvMoveList!=null && !scmInvMoveList.isEmpty()){
			scmInvMove2 = scmInvMoveList.get(0);
			scmInvMove2.setScmInvMoveBillEntryList(scmInvMoveBillEntryBiz.selectByWtId(scmInvMove2.getWtId(), param));
			
			//增加待审人
			ScmBillPending2 scmBillPendingUsr = scmBillPendingBiz.selectPendingUsr(scmInvMove2.getWtId(), "InvMoveBill",param);
			if (scmBillPendingUsr != null) {
				scmInvMove2.setPendingUsr(scmBillPendingUsr.getUsrCodes());
			}
		}else{
			throw new AppException("iscm.purchasemanage.pricemanage.find.ordernotexists");
		}
		return scmInvMove2;
	}

	@Override
	public ScmInvMoveBill2 doAuditInvMove(CommonAuditParams commonAuditParams,
			Param param) throws AppException {
		ScmInvMoveBill2 invMoveBill = null;
		
		ScmInvMoveBill2 scmInvMoveBill= new ScmInvMoveBill2();
		scmInvMoveBill.setWtId(commonAuditParams.getBillId());
		scmInvMoveBill.setWtNo(commonAuditParams.getBillNo());
		String opinion = commonAuditParams.getOpinion();
		if(StringUtils.equals("Y", opinion) || StringUtils.equals("agree", opinion)) {
			opinion="agree";
		}else if(StringUtils.equals("N", opinion) || StringUtils.equals("refuse", opinion)) {
			opinion="refuse";
		}
		if(scmInvMoveBill.getWtId()>0){
			invMoveBill = this.selectDirect(scmInvMoveBill.getWtId(), param);
		}else{
			HashMap<String,Object> map=new HashMap	<String,Object>();
			map.put(
					ScmInvMoveBill2.FN_WTNO,
					new QueryParam(ScmInvMoveBill2.FN_WTNO, QueryParam.QUERY_EQ,
							scmInvMoveBill.getWtNo()));
			map.put(ScmInvMoveBill2.FN_CONTROLUNITNO, new QueryParam(ScmInvMoveBill2.FN_CONTROLUNITNO, QueryParam.QUERY_EQ,
							param.getControlUnitNo()));
			List<ScmInvMoveBill2> scmInvMoveBillList =this.findAll(map, param);
			if(scmInvMoveBillList!=null && !scmInvMoveBillList.isEmpty()){
				invMoveBill = scmInvMoveBillList.get(0);
			}
		}
		
		if (invMoveBill != null) {
			this.setConvertMap(invMoveBill, param);
			BillType2 billType = billTypeBiz.selectByOrgAndCode(invMoveBill.getFinOrgUnitNo(), "InvMoveBill", param);
			if(!(invMoveBill.getStatus().equals("D") || invMoveBill.getStatus().equals("P")) && billType.isNeedAudit()){
				throw new AppException("iscm.purchasemanage.error.audit");
			}
			if(StringUtils.equals("R", opinion)) {
				scmBillPendingBiz.deletePendingBill(param.getUsrCode(),invMoveBill, param);
				commonEventHistoryBiz.updateInvalid(invMoveBill,"",param);
				
				CommonAuditOpinion commonAuditOpinionR = new CommonAuditOpinion();
				commonAuditOpinionR.setPreStepNo(invMoveBill.getStepNo());
				String stepNo = StringUtils.replace(UUID.randomUUID().toString(), "-", "");
				commonAuditOpinionR.setStepNo(stepNo);
				commonAuditOpinionR.setActiveType("A");
				commonAuditOpinionR.setOpinion(commonAuditParams.getOpinion());
				commonAuditOpinionR.setRemarks(commonAuditParams.getOpinionRemarks());
				commonEventHistoryBiz.addEventHistory(invMoveBill, commonAuditOpinionR, param);
				
				if(billType.isNeedAudit()) {
					//驳回则将单据变回新单状态
					invMoveBill.setStatus("I");
					invMoveBill.setChecker(null);
					invMoveBill.setCheckDate(null);
					return this.updateDirect(invMoveBill, param);
				}else {
					//不需要审批时驳回需模拟反过账、反提交
					if(StringUtils.equals("E",invMoveBill.getStatus())) {
						invMoveBill = this.cancelPostBill(invMoveBill, param);
					}
					if(StringUtils.equals("A",invMoveBill.getStatus())) {
						this.undoSubmit(invMoveBill, param);
					}
					
					invMoveBill.setStatus("I");
					invMoveBill.setChecker(null);
					invMoveBill.setCheckDate(null);
					return this.updateDirect(invMoveBill, param);
				}
			}
			String processInstanceId = invMoveBill.getWorkFlowNo();
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
			
			//启用审批，或启用工作流（对于历史数据的处理）
			if(billType.isNeedAudit() || (billType.isUseFlow() && StringUtils.contains("D,P", invMoveBill.getStatus()))) {
				//P：审核中，A：通过，N：未通过
				if ("agree".equals(opinion)) {
					if (isCompleteTask) {
						invMoveBill.setStatus("A");
					} else {
						invMoveBill.setStatus("P");
					}
				} else {
					invMoveBill.setStatus("N");
				}
			} else {
				if (billType.isUseFlow()) {
					if(StringUtils.equals("refuse", opinion)) {
						//审批拒绝
						if(StringUtils.equals("E",invMoveBill.getStatus())) {
							invMoveBill = this.cancelPostBill(invMoveBill, param);
						}
						invMoveBill.setStatus("N");
					}
					
				}
			}
			scmBillPendingBiz.updatePendingBill(param.getUsrCode(), invMoveBill, param);
			invMoveBill.setChecker(param.getUsrCode());
			if ("agree".equals(opinion)) {
				this.sendAuditMsg(invMoveBill, billType.getBillCode(), param);
			}
			CommonAuditOpinion commonAuditOpinion = new CommonAuditOpinion();
			commonAuditOpinion.setPreStepNo(invMoveBill.getStepNo());
			String stepNo = StringUtils.replace(UUID.randomUUID().toString(), "-", "");
			try {
				invMoveBill.setStepNo(stepNo);
				invMoveBill.setCheckDate(CalendarUtil.getDate(param));
				this.updateStatus(invMoveBill, param);
			} catch (Exception e) {
				throw e;
			}
			commonAuditOpinion.setStepNo(stepNo);
			commonAuditOpinion.setActiveType("A");
			commonAuditOpinion.setOpinion(commonAuditParams.getOpinion());
			commonAuditOpinion.setRemarks(commonAuditParams.getOpinionRemarks());
			commonEventHistoryBiz.addEventHistory(invMoveBill, commonAuditOpinion, param);
			SystemState systemState = systemStateBiz.selectBySystemId(invMoveBill.getFinOrgUnitNo(), 170, param);
			if(systemState==null){
				throw new AppException("com.armitage.iars.daily.util.disenable.unable");
			}
			if (systemState.getCurrentPeriodId() == invMoveBill.getPeriodId()) {
				if(StringUtils.equals("A", invMoveBill.getStatus())) {
					//通过或部分通过时检查是否自动过帐
					if(billType!=null && billType.isAutoRelease()) {
						List<String> msgList = this.postBillCheck(invMoveBill, "1", param);
						if (msgList != null && !msgList.isEmpty()) {
							StringBuilder detailInfo = new StringBuilder("");
	                        detailInfo.append(Message.getMessage(param.getLang(),"iscm.inventorymanage.moveissuesbill.post.errorTitle"));
	
							for (String str : msgList) {
	                            detailInfo.append(str).append("\n");
	                        }
							
							throw new AppException(detailInfo.toString(), new String[]{invMoveBill.getWtNo()});
						}
						
						this.postBill(invMoveBill, param);
					}
				}
			}
		} else {
			throw new AppException("iscm.purchasemanage.purchasebusiness.find.ordernotexists");
		}
		
		return invMoveBill;
	}

	@Override
	public ScmInvMoveBill2 doUnAuditInvMove(ScmInvMoveBill2 scmInvMoveBill,
			Param param) throws AppException {
		ScmInvMoveBill2 invMoveBill = null;
		List<ScmInvMoveBill2> scmInvMoveBillList = new ArrayList<> ();
		boolean isFirstTask = false;
		
		if(scmInvMoveBill.getWtId()>0){
			invMoveBill = this.selectDirect(scmInvMoveBill.getWtId(), param);
		}else{
			HashMap<String,Object> map=new HashMap<String,Object>();
			map.put(
					ScmInvMoveBill2.FN_WTNO,
					new QueryParam(ScmInvMoveBill2.FN_WTNO, QueryParam.QUERY_EQ,
							scmInvMoveBill.getWtNo()));
			map.put(ScmInvMoveBill2.FN_CONTROLUNITNO, new QueryParam(ScmInvMoveBill2.FN_CONTROLUNITNO, QueryParam.QUERY_EQ,
					param.getControlUnitNo()));
			scmInvMoveBillList =this.findAll(map, param);
			if(scmInvMoveBillList!=null && !scmInvMoveBillList.isEmpty()){
				invMoveBill=scmInvMoveBillList.get(0);
			}
		}
		
		if (invMoveBill != null) {
			BillType2 billType = billTypeBiz.selectByOrgAndCode(invMoveBill.getFinOrgUnitNo(), "InvMoveBill", param);
			if(!StringUtils.contains("A,B,N,P", invMoveBill.getStatus()) && billType.isNeedAudit()){
				throw new AppException("iscm.purchasemanage.unaudit.error.status");
			}
			if(!StringUtils.contains("A,B,N,P,E", invMoveBill.getStatus()) && billType.isUseFlow()){
				throw new AppException("iscm.purchasemanage.unaudit.error.status");
			}
			String processInstanceId = invMoveBill.getWorkFlowNo();
			List<ScmInvMoveBillEntry2> scmInvMoveBillEntryList = scmInvMoveBillEntryBiz.selectByWtId(invMoveBill.getWtId(), param);
			if(scmInvMoveBillEntryList == null || scmInvMoveBillEntryList.isEmpty()){
				//抛出异常处理
				throw new AppException("iscm.inventorymanage.AllocationApplication.service.Impl.ScmInvMaterialRequestBillBizImpl.generateMaterialReqBill.notExitMaterialRequestBillDetail");
			}
			
			if (processInstanceId != null && !"".equals(processInstanceId)) {
				//反审批当前已审节点
				CommonBean bean = new CommonBean();
				scmInvMoveBillList.add(invMoveBill);
				bean.setList(scmInvMoveBillList);
				bean.setList2(scmInvMoveBillEntryList);

				long billTypeId = 0;
				if (billType!=null && billType.isNeedAudit()) {
					billTypeId = billType.getId();
				}
				
				processInstanceId = ActivityUtil.antiAuditProcess(bean, billTypeId, processInstanceId, param.getUsrCode(), param);
				isFirstTask = ActivityUtil.isFirstTask(processInstanceId, param.getUsrCode(), param);
				invMoveBill.setWorkFlowNo(processInstanceId);
			} else {
				//流程编号不存在
				throw new AppException("com.armitage.server.iscm.purchasemanage.purchasebusiness.service.impl.ScmPurRequireBizImpl.doAuditPurRequire.processInstanceId");
			}
			
			//启用审批，或启用工作流（对于历史数据的处理）
			if(billType.isNeedAudit() || (billType.isUseFlow() && StringUtils.contains("N,D,P", invMoveBill.getStatus()))) {
				String status = "";
				if (isFirstTask) {
					status = "D";
				} else {
					status = "P";
				}
				invMoveBill.setStatus(status);
				String tableName = ClassUtils.getFinalModelSimpleName(invMoveBill);
				CommonEventHistory commonEventHistory = commonEventHistoryBiz.loadAuditEventHistory(tableName,invMoveBill.getStepNo(),invMoveBill.getPK(),param);
				if(commonEventHistory!=null && StringUtils.isNotBlank(commonEventHistory.getPreStepNo())) {
					commonEventHistory = commonEventHistoryBiz.loadAuditEventHistory(tableName,commonEventHistory.getPreStepNo(),invMoveBill.getPK(),param);
				}
				if(commonEventHistory!=null) {
					invMoveBill.setChecker(commonEventHistory.getOper());
					invMoveBill.setCheckDate(commonEventHistory.getOperDate());
				}else {
					invMoveBill.setChecker(null);
					invMoveBill.setCheckDate(null);
				}
				this.updateStatus(invMoveBill, param);
			}
			scmBillPendingBiz.cancelPendingBill(param.getUsrCode(),invMoveBill, param);
			commonEventHistoryBiz.updateInvalid(invMoveBill,invMoveBill.getStepNo(),param);
			CommonAuditOpinion commonAuditOpinion = new CommonAuditOpinion();
			commonAuditOpinion.setStepNo(invMoveBill.getStepNo());
			commonAuditOpinion.setActiveType("U");
			commonAuditOpinion.setOpinion("Y");
			commonEventHistoryBiz.addEventHistory(invMoveBill, commonAuditOpinion, param);
		} else {
			throw new AppException("iscm.purchasemanage.purchasebusiness.find.ordernotexists");
		}
		
		
		return invMoveBill;
	}
	
	@Override
	public void doAdvQuery(Page page, Param param) {
		if (page.getModel() != null) {
			if (page.getModel() instanceof ScmInvMoveBillAdvQuery) {
				ScmInvMoveBillAdvQuery scmInvMoveBillAdvQuery = (ScmInvMoveBillAdvQuery) page.getModel();
				if(scmInvMoveBillAdvQuery.getBizDateFrom()!=null){
					if(scmInvMoveBillAdvQuery.getBizDateTo()!=null) {
						page.getParam().put(ScmInvMoveBill2.FN_BIZDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMoveBill2.class) + "." +ScmInvMoveBill2.FN_BIZDATE, QueryParam.QUERY_BETWEEN,FormatUtils.fmtDate(scmInvMoveBillAdvQuery.getBizDateFrom()),FormatUtils.fmtDate(scmInvMoveBillAdvQuery.getBizDateTo())));
					}else {
						page.getParam().put(ScmInvMoveBill2.FN_BIZDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMoveBill2.class) + "." +ScmInvMoveBill2.FN_BIZDATE, QueryParam.QUERY_GE,FormatUtils.fmtDate(scmInvMoveBillAdvQuery.getBizDateFrom())));
					}
				}else if(scmInvMoveBillAdvQuery.getBizDateTo()!=null) {
					page.getParam().put(ScmInvMoveBill2.FN_BIZDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMoveBill2.class) + "." +ScmInvMoveBill2.FN_BIZDATE, QueryParam.QUERY_LE,FormatUtils.fmtDate(scmInvMoveBillAdvQuery.getBizDateTo())));
				}
				if(scmInvMoveBillAdvQuery.getCreateDateFrom()!=null){
					if(scmInvMoveBillAdvQuery.getCreateDateTo()!=null) {
						page.getParam().put(ScmInvMoveBill2.FN_CREATEDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMoveBill2.class) + "." +ScmInvMoveBill2.FN_CREATEDATE, QueryParam.QUERY_BETWEEN,FormatUtils.fmtDate(scmInvMoveBillAdvQuery.getCreateDateFrom()),FormatUtils.fmtDate(CalendarUtil.relativeDate(scmInvMoveBillAdvQuery.getCreateDateTo(),1))));
					}else {
						page.getParam().put(ScmInvMoveBill2.FN_CREATEDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMoveBill2.class) + "." +ScmInvMoveBill2.FN_CREATEDATE, QueryParam.QUERY_GE,FormatUtils.fmtDate(scmInvMoveBillAdvQuery.getCreateDateFrom())));
					}
				}else if(scmInvMoveBillAdvQuery.getCreateDateTo()!=null) {
					page.getParam().put(ScmInvMoveBill2.FN_CREATEDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMoveBill2.class) + "." +ScmInvMoveBill2.FN_CREATEDATE, QueryParam.QUERY_LE,FormatUtils.fmtDate(CalendarUtil.relativeDate(scmInvMoveBillAdvQuery.getCreateDateTo(),1))));
				}
				if(StringUtils.isNotBlank(scmInvMoveBillAdvQuery.getOutOrgUnitNo())) {
					page.getParam().put(ScmInvMoveBill2.FN_OUTORGUNITNO,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMoveBill2.class) + "." +ScmInvMoveBill2.FN_OUTORGUNITNO, QueryParam.QUERY_EQ,String.valueOf(scmInvMoveBillAdvQuery.getOutOrgUnitNo())));
				}
				if(StringUtils.isNotBlank(scmInvMoveBillAdvQuery.getInOrgUnitNo())) {
					page.getParam().put(ScmInvMoveBill2.FN_INORGUNITNO,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMoveBill2.class) + "." +ScmInvMoveBill2.FN_INORGUNITNO, QueryParam.QUERY_EQ,scmInvMoveBillAdvQuery.getInOrgUnitNo()));
				}
			}
		}
	}

	@Override
	public List<Map<String, Object>> countUnPostBill(HashMap<String, Object> map) {
		return ((ScmInvMoveBillDAO) dao).countUnPostBill(map);
	}

	@Override
	public ScmInvMoveBill2 updatePrtCount(ScmInvMoveBill2 scmInvMoveBill, Param param) throws AppException {
		if(scmInvMoveBill.getWtId()>0){
			ScmInvMoveBill2 bill = this.selectDirect(scmInvMoveBill.getWtId(), param);
			if(bill != null){
				int prtCount = bill.getPrtcount();
				bill.setPrtcount((prtCount+1));
				this.updateDirect(bill, param);
				return bill;
			}
		}
		return scmInvMoveBill;
	}
	
	@Override
	public DataSyncResult dataSync(InvMoveListParams invMoveListParam,List<ScmInvMoveBill2> scmInvMoveBills,Param param) throws AppException {
		DataSyncResult dataSyncResult = new DataSyncResult();
		if(StringUtils.isBlank(invMoveListParam.getWtNo()) && StringUtils.equals(invMoveListParam.getDelete(), "N")) {
			throw new AppException("iscm.api.datePut.billNo.notExist");
		}
		if((StringUtils.isBlank(invMoveListParam.getInOrgUnitNo())||StringUtils.isBlank(invMoveListParam.getOutOrgUnitNo())) && StringUtils.equals(invMoveListParam.getDelete(), "N")) {
			throw new AppException("iscm.api.datePut.billOrgUnitNo.notExis");
		}
		if(StringUtils.isBlank(invMoveListParam.getPostStatus()) && StringUtils.equals(invMoveListParam.getDelete(), "N")) {
			throw new AppException("iscm.api.datePut.billStatus.notExist");
		}
		if(invMoveListParam.getBizDate() == null && StringUtils.equals(invMoveListParam.getDelete(), "N")) {
			throw new AppException("iscm.api.datePut.billBizDate.notExist");
		}
		if((invMoveListParam.getDetailList() == null || invMoveListParam.getDetailList().isEmpty()) && StringUtils.equals(invMoveListParam.getDelete(), "N")) {
			throw new AppException("iscm.api.datePut.billEntry.notExist");
		}
		for(ScmInvMoveBill2 scmInvMoveBill2:scmInvMoveBills) {
			if(StringUtils.equals(invMoveListParam.getWtNo(), scmInvMoveBill2.getWtNo())) {
				//删除
				if(StringUtils.equals(invMoveListParam.getDelete(),"Y")) {
					scmInvMoveBillEntryBiz.deleteByWtId(scmInvMoveBill2.getWtId(), param);
					((ScmInvMoveBillDAO) dao).delete(scmInvMoveBill2.getPK()+"");
					dataSyncResult.setNo(invMoveListParam.getWtNo());
					dataSyncResult.setErrCode("0");
					return dataSyncResult;
				}
				//构建明细				
				ArrayList<ScmInvMoveBillEntry2> scmInvMoveBillEntryAdd = new ArrayList<ScmInvMoveBillEntry2>();
				for(InvMoveDetailParams invMoveDetailParams :invMoveListParam.getDetailList()) {
					ScmInvMoveBillEntry2 scmInvMoveBillEntry = new ScmInvMoveBillEntry2(true);
					//获取物资和单位			
					if(!scmMatrialMap.containsKey(invMoveDetailParams.getItemNo())) {
						ScmMaterial2 selectByItemNo = scmMaterialBiz.selectByItemNo(param.getControlUnitNo(), invMoveDetailParams.getItemNo(), param);
						if(selectByItemNo == null)
							throw new AppException("iscm.api.datePut.billItemNo.notExist");
						scmMatrialMap.put(selectByItemNo.getItemNo(), selectByItemNo.getId());
					}
					BeanUtils.copyProperties(invMoveDetailParams, scmInvMoveBillEntry);
					scmInvMoveBillEntry.setItemId(scmMatrialMap.get(invMoveDetailParams.getItemNo()));
					if(!scmUnitMap.containsKey(scmInvMoveBillEntry.getItemId())) {
						ScmMaterialInventory2 selectByItemIdAndOrgUnitNo = scmMaterialInventoryBiz.selectByItemId(scmInvMoveBillEntry.getItemId(), param.getOrgUnitNo(), param.getControlUnitNo(), param);
						if(selectByItemIdAndOrgUnitNo == null)
							throw new AppException("iscm.api.datePut.billUnitNo.notExist");
						scmUnitMap.put(selectByItemIdAndOrgUnitNo.getItemId(), selectByItemIdAndOrgUnitNo.getUnitId());
					}
					scmInvMoveBillEntry.setUnit(scmUnitMap.get(scmInvMoveBillEntry.getItemId()));
					scmInvMoveBillEntry.setWtId(scmInvMoveBill2.getWtId());
					scmInvMoveBillEntry.setWtNo(scmInvMoveBill2.getWtNo());
					scmInvMoveBillEntry.setBaseQty(invMoveDetailParams.getQty());
					scmInvMoveBillEntry.setInvDate(scmInvMoveBill2.getBizDate());
					scmInvMoveBillEntryAdd.add(scmInvMoveBillEntry);
				}
				//2、	过账 》过账 一般不存在，v7不能反过账
				if(StringUtils.equals(invMoveListParam.getPostStatus(), "1")&& "E,C".contains(scmInvMoveBill2.getStatus())) {
					throw new AppException("iscm.api.business.datasync.controller.posted");
				//3、	非过账 》过账
				}else if(!"E,C".contains(scmInvMoveBill2.getStatus())&& StringUtils.equals(invMoveListParam.getPostStatus(), "1")) {
					//更新单据
					BeanUtils.copyProperties(invMoveListParam, scmInvMoveBill2);
					beforeAdd(scmInvMoveBill2, param);
					scmInvMoveBill2.setWtNo(invMoveListParam.getWtNo());
					scmInvMoveBill2.setControlUnitNo(param.getControlUnitNo());
					scmInvMoveBill2.setCreateOrgUnitNo(param.getOrgUnitNo());
					scmInvMoveBill2.setStatus("E");
					update(scmInvMoveBill2, param);
					scmInvMoveBillEntryBiz.deleteByWtId(scmInvMoveBill2.getWtId(), param);
					scmInvMoveBillEntryBiz.add(scmInvMoveBillEntryAdd, param);
					//过账					
					postForInvBal(scmInvMoveBill2.getWtId(), param);
					//返回结果					
					dataSyncResult.setNo(invMoveListParam.getWtNo());
					dataSyncResult.setErrCode("0");
					return dataSyncResult;
				//4、	过账 》非过账 一般不存在，v7不能反过账					
				}else if("E,C".contains(scmInvMoveBill2.getStatus()) && StringUtils.equals(invMoveListParam.getPostStatus(), "0")){
					throw new AppException("iscm.api.business.datasync.controller.cancelPost");
				//5、	非过账 》非过账					
				}else{
					//未过账、关闭的单据 更新单据
					BeanUtils.copyProperties(invMoveListParam, scmInvMoveBill2);
					beforeAdd(scmInvMoveBill2, param);
					scmInvMoveBill2.setWtNo(invMoveListParam.getWtNo());
					scmInvMoveBill2.setControlUnitNo(param.getControlUnitNo());
					scmInvMoveBill2.setCreateOrgUnitNo(param.getOrgUnitNo());
					update(scmInvMoveBill2, param);
					scmInvMoveBillEntryBiz.deleteByWtId(scmInvMoveBill2.getWtId(), param);
					scmInvMoveBillEntryBiz.add(scmInvMoveBillEntryAdd, param);
					dataSyncResult.setNo(invMoveListParam.getWtNo());
					dataSyncResult.setErrCode("0");
					return dataSyncResult;
				}
			}
		}
		if(StringUtils.equals(invMoveListParam.getDelete(),"Y")) {
			dataSyncResult.setNo(invMoveListParam.getWtNo());
			dataSyncResult.setErrCode("0");
			return dataSyncResult;
		}
		//1、	新增		
		ArrayList<ScmInvMoveBillEntry2> scmInvMoveBillEntryAdd2 = new ArrayList<ScmInvMoveBillEntry2>();
		//新增主表		
		ScmInvMoveBill2 scmInvMoveBill = new ScmInvMoveBill2(true);
		BeanUtils.copyProperties(invMoveListParam, scmInvMoveBill);
		beforeAdd(scmInvMoveBill, param);
		scmInvMoveBill.setStatus("I");
		if(StringUtils.equals(invMoveListParam.getPostStatus(),"1")) 
			scmInvMoveBill.setStatus("E");
		scmInvMoveBill.setCreator("");
		scmInvMoveBill.setCreateDate(invMoveListParam.getBizDate());
		scmInvMoveBill.setWtNo(invMoveListParam.getWtNo());
		scmInvMoveBill.setControlUnitNo(param.getControlUnitNo());
		scmInvMoveBill.setCreateOrgUnitNo(param.getOrgUnitNo());
		((ScmInvMoveBillDAO) dao).add(scmInvMoveBill);
		//新增明细		
		for(InvMoveDetailParams invMoveDetailParams :invMoveListParam.getDetailList()) {
			ScmInvMoveBillEntry2 scmInvMoveBillEntry = new ScmInvMoveBillEntry2(true);
			BeanUtils.copyProperties(invMoveDetailParams, scmInvMoveBillEntry);
			//获取物资和单位			
			if(!scmMatrialMap.containsKey(invMoveDetailParams.getItemNo())) {
				ScmMaterial2 selectByItemNo = scmMaterialBiz.selectByItemNo(param.getControlUnitNo(), invMoveDetailParams.getItemNo(), param);
				if(selectByItemNo == null)
					throw new AppException("iscm.api.datePut.billItemNo.notExist");
				scmMatrialMap.put(selectByItemNo.getItemNo(), selectByItemNo.getId());
			}
			scmInvMoveBillEntry.setItemId(scmMatrialMap.get(invMoveDetailParams.getItemNo()));
			if(!scmUnitMap.containsKey(scmInvMoveBillEntry.getItemId())) {
				ScmMaterialInventory2 selectByItemIdAndOrgUnitNo = scmMaterialInventoryBiz.selectByItemId(scmInvMoveBillEntry.getItemId(), param.getOrgUnitNo(), param.getControlUnitNo(), param);
				if(selectByItemIdAndOrgUnitNo == null)
					throw new AppException("iscm.api.datePut.billUnitNo.notExist");
				scmUnitMap.put(selectByItemIdAndOrgUnitNo.getItemId(), selectByItemIdAndOrgUnitNo.getUnitId());
			}
			scmInvMoveBillEntry.setStatus("I");
			if(StringUtils.equals(invMoveListParam.getPostStatus(),"1")) 
				scmInvMoveBillEntry.setStatus("E");
			scmInvMoveBillEntry.setUnit(scmUnitMap.get(scmInvMoveBillEntry.getItemId()));
			scmInvMoveBillEntry.setWtNo(scmInvMoveBill.getWtNo());
			scmInvMoveBillEntry.setBaseQty(invMoveDetailParams.getQty());
			scmInvMoveBillEntry.setInvDate(scmInvMoveBill.getBizDate());
			scmInvMoveBillEntry.setWtId(scmInvMoveBill.getWtId());
			scmInvMoveBillEntryAdd2.add(scmInvMoveBillEntry);
		}
		scmInvMoveBillEntryBiz.add(scmInvMoveBillEntryAdd2, param);
		if(StringUtils.equals(invMoveListParam.getPostStatus(),"1")) {
			//未同步的 过账单据 期间余额
			long wtId = scmInvMoveBill.getWtId();
			postForInvBal(wtId, param);
		}
		dataSyncResult.setNo(invMoveListParam.getWtNo());
		dataSyncResult.setErrCode("0");
		return dataSyncResult;
	}

	private void postForInvBal(long wtId, Param param) {
		scmInvBalBiz.updateByMoveBillOutSub(wtId, param);
		scmInvBalBiz.updateByMoveBillInPlus(wtId, param);
		scmInvBalBiz.addByMoveBillIn(wtId, param);
		scmInvBalBiz.addByPostMoveBillForOut(wtId, param);
	}

	private void cancelForInvBal(long wtId, Param param) {
		scmInvBalBiz.updateByMoveBillInSub(wtId, param);
		scmInvBalBiz.updateByMoveBillOutPlus(wtId,param);
		scmInvBalBiz.addByMoveBillOut(wtId, param);
		scmInvBalBiz.addByCancelMoveBillForIn(wtId, param);
	}
}
