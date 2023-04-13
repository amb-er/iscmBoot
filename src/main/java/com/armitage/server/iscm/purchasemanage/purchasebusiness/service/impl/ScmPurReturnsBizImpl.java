
package com.armitage.server.iscm.purchasemanage.purchasebusiness.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;

import com.armitage.server.api.business.purreturns.params.PurReturnsParams;
import com.armitage.server.activity.util.ActivityUtil;
import com.armitage.server.activity.util.AuditMsgUtil;
import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.CommonBean;
import com.armitage.server.common.base.model.Page;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.base.model.QueryParam;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.util.CalendarUtil;
import com.armitage.server.common.util.ClassUtils;
import com.armitage.server.common.util.FormatUtils;
import com.armitage.server.iars.basedata.model.PeriodCalendar;
import com.armitage.server.iars.basedata.service.PeriodCalendarBiz;
import com.armitage.server.iscm.basedata.model.ScmBillPending;
import com.armitage.server.iscm.basedata.model.ScmBillPending2;
import com.armitage.server.iscm.basedata.model.ScmMaterial2;
import com.armitage.server.iscm.basedata.model.Scmsupplier2;
import com.armitage.server.iscm.basedata.service.ScmBillPendingBiz;
import com.armitage.server.iscm.basedata.service.ScmMaterialBiz;
import com.armitage.server.iscm.basedata.service.ScmsupplierBiz;
import com.armitage.server.iscm.basedata.util.ScmMaterialUtil;
import com.armitage.server.iscm.common.model.CommonAuditOpinion;
import com.armitage.server.iscm.common.model.CommonEventHistory;
import com.armitage.server.iscm.common.service.CommonEventHistoryBiz;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvPurInWarehsBill2;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.service.ScmInvPurInWarehsBillBiz;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.dao.ScmPurReturnsDAO;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.CommonAuditParams;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurAuditParam;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurReceive2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurReceiveEntry2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurReturns2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurReturnsAdvQuery;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurReturnsEntry2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.service.ScmPurReceiveEntryBiz;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.service.ScmPurReturnsBiz;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.service.ScmPurReturnsEntryBiz;
import com.armitage.server.system.model.BillType2;
import com.armitage.server.system.model.Code;
import com.armitage.server.system.model.OrgBaseUnit;
import com.armitage.server.system.model.OrgPurchase2;
import com.armitage.server.system.model.OrgStorage2;
import com.armitage.server.system.service.BillTypeBiz;
import com.armitage.server.system.service.CodeBiz;
import com.armitage.server.system.service.OrgPurchaseBiz;
import com.armitage.server.system.service.OrgStorageBiz;
import com.armitage.server.system.service.OrgUnitBiz;
import com.armitage.server.system.service.SysParamBiz;
import com.armitage.server.system.util.CodeAutoCalUtil;
import com.armitage.server.user.model.Usr;
import com.armitage.server.user.service.UsrBiz;
import org.springframework.stereotype.Service;

@Service("scmPurReturnsBiz")
public class ScmPurReturnsBizImpl extends BaseBizImpl<ScmPurReturns2> implements ScmPurReturnsBiz {
	private UsrBiz usrBiz;
	private OrgUnitBiz orgUnitBiz;
	private ScmPurReturnsEntryBiz scmPurReturnsEntryBiz;
	private ScmsupplierBiz scmsupplierBiz;
	private ScmPurReceiveEntryBiz scmPurReceiveEntryBiz;
	private ScmMaterialBiz scmMaterialBiz;
	private PeriodCalendarBiz periodCalendarBiz;
	private ScmInvPurInWarehsBillBiz scmInvPurInWarehsBillBiz;
	private OrgPurchaseBiz orgPurchaseBiz;
	private BillTypeBiz billTypeBiz;
	private OrgStorageBiz orgStorageBiz;
	private CodeBiz codeBiz;
	private ScmBillPendingBiz scmBillPendingBiz;
	private CommonEventHistoryBiz commonEventHistoryBiz;
	private SysParamBiz sysParamBiz;
	
	public void setUsrBiz(UsrBiz usrBiz) {
		this.usrBiz = usrBiz;
	}

	public void setOrgUnitBiz(OrgUnitBiz orgUnitBiz) {
		this.orgUnitBiz = orgUnitBiz;
	}

	public void setScmPurReturnsEntryBiz(ScmPurReturnsEntryBiz scmPurReturnsEntryBiz) {
		this.scmPurReturnsEntryBiz = scmPurReturnsEntryBiz;
	}

	public void setScmsupplierBiz(ScmsupplierBiz scmsupplierBiz) {
		this.scmsupplierBiz = scmsupplierBiz;
	}

	public void setScmPurReceiveEntryBiz(ScmPurReceiveEntryBiz scmPurReceiveEntryBiz) {
		this.scmPurReceiveEntryBiz = scmPurReceiveEntryBiz;
	}

	public void setScmMaterialBiz(ScmMaterialBiz scmMaterialBiz) {
		this.scmMaterialBiz = scmMaterialBiz;
	}

	public void setPeriodCalendarBiz(PeriodCalendarBiz periodCalendarBiz) {
		this.periodCalendarBiz = periodCalendarBiz;
	}

	public void setScmInvPurInWarehsBillBiz(
			ScmInvPurInWarehsBillBiz scmInvPurInWarehsBillBiz) {
		this.scmInvPurInWarehsBillBiz = scmInvPurInWarehsBillBiz;
	}

	public void setOrgPurchaseBiz(OrgPurchaseBiz orgPurchaseBiz) {
		this.orgPurchaseBiz = orgPurchaseBiz;
	}

	public void setBillTypeBiz(BillTypeBiz billTypeBiz) {
		this.billTypeBiz = billTypeBiz;
	}

	public void setOrgStorageBiz(OrgStorageBiz orgStorageBiz) {
		this.orgStorageBiz = orgStorageBiz;
	}
	
	public void setCodeBiz(CodeBiz codeBiz) {
		this.codeBiz = codeBiz;
	}
	
	public void setScmBillPendingBiz(ScmBillPendingBiz scmBillPendingBiz) {
		this.scmBillPendingBiz = scmBillPendingBiz;
	}

	public void setCommonEventHistoryBiz(CommonEventHistoryBiz commonEventHistoryBiz) {
		this.commonEventHistoryBiz = commonEventHistoryBiz;
	}

	public void setSysParamBiz(SysParamBiz sysParamBiz) {
		this.sysParamBiz = sysParamBiz;
	}

	@Override
	protected void beforeFindPage(Page page, Param param) throws AppException {
		List<OrgStorage2> orgStorageList = orgStorageBiz.findChild(param.getOrgUnitNo(), param);
		StringBuffer invOrgunitList = new StringBuffer("");
		StringBuffer purOrgunitList = new StringBuffer("");
		if (orgStorageList != null && !orgStorageList.isEmpty()) {
			for (OrgStorage2 orgStorage : orgStorageList) {
				if(StringUtils.isNotBlank(invOrgunitList.toString()))
					invOrgunitList.append(",");
				invOrgunitList.append("'").append(orgStorage.getOrgUnitNo()).append("'");
			}
		}else{
			invOrgunitList.append("'").append("0").append("'");
		}
		
		List<OrgPurchase2> orgPurchaseList = orgPurchaseBiz.findChild(param.getOrgUnitNo(), param);
		if (orgPurchaseList != null && !orgPurchaseList.isEmpty()) {
			for (OrgPurchase2 orgPurchase : orgPurchaseList) {
				if(StringUtils.isNotBlank(purOrgunitList.toString()))
					purOrgunitList.append(",");
				purOrgunitList.append("'").append(orgPurchase.getOrgUnitNo()).append("'");
			}
		}else{
			purOrgunitList.append("'").append("0").append("'");
		}
		if(StringUtils.isNotBlank(page.getSqlCondition())){
			page.setSqlCondition((page.getSqlCondition()+"and (ScmPurReturns.orgUnitNo in ("+invOrgunitList.toString()+") or ScmPurReturns.purOrgUnitNo in ("+purOrgunitList.toString()+"))"));
		}else {
			page.setSqlCondition("(ScmPurReturns.orgUnitNo in ("+invOrgunitList.toString()+") or ScmPurReturns.purOrgUnitNo in ("+purOrgunitList.toString()+"))");
		}
		
		OrgStorage2 orgStorage = orgStorageBiz.selectByOrgUnitNo(param.getOrgUnitNo(), param);
		if(orgStorage==null) {
			//不具有库存属性
			String status;
			if (StringUtils.equals("619423510", page.getArguments())) {
				status="'D'";
			}else {
				status="'D','A'";
			}
			page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmPurReturns2.class) + "." + ScmPurReturns2.FN_STATUS), 
				new QueryParam((ClassUtils.getFinalModelSimpleName(ScmPurReturns2.class) + "." + ScmPurReturns2.FN_STATUS), QueryParam.QUERY_IN, status));
		}
	}

	@Override
	protected void afterFindPage(List list, Page page, Param param)
			throws AppException {
		HashMap<String,Object> cacheMap = new HashMap<String,Object>();
		List<ScmPurReturns2> scmPurReturnsList = list;
		if(scmPurReturnsList != null && !scmPurReturnsList.isEmpty()){
			for(ScmPurReturns2 scmPurReturns : scmPurReturnsList){
				//视图增加待审人
				ScmBillPending2 scmBillPendingUsr = scmBillPendingBiz.selectPendingUsr(scmPurReturns.getId(), "PurReturns",param);
				StringBuffer usrName = new StringBuffer("");
				if (scmBillPendingUsr != null) {
					scmPurReturns.setPendingUsr(scmBillPendingUsr.getUsrCodes());
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
				scmPurReturns.setPendingUsrName(usrName.toString());
				
				setConvertMap(scmPurReturns,param);
				
			}
		}
	}
	
	@Override
	protected void afterSelect(ScmPurReturns2 entity, Param param) throws AppException {
		if (entity != null){
			setConvertMap(entity,param);
		}
	}
	
	private void setConvertMap(ScmPurReturns2 scmPurReturns, Param param) throws AppException {
		if (scmPurReturns.getVendorId() > 0){
			//供应商
			Scmsupplier2 scmsupplier = scmsupplierBiz.selectDirect(scmPurReturns.getVendorId(), param);
			if (scmsupplier != null) {
				scmPurReturns.setConvertMap(ScmPurReturns2.FN_VENDORID, scmsupplier);
				scmPurReturns.setVendorCode(scmsupplier.getVendorNo());
				scmPurReturns.setVendorName(scmsupplier.getVendorName());
			}
		}
		if (StringUtils.isNotBlank(scmPurReturns.getCreator())){
			//制单人
			Usr usr = usrBiz.selectByCode(scmPurReturns.getCreator(), param);
			if (usr != null) {
				scmPurReturns.setConvertMap(ScmPurReturns2.FN_CREATOR, usr);
				scmPurReturns.setCreatorName(usr.getName());
			}
		}
		if (StringUtils.isNotBlank(scmPurReturns.getEditor())){
			//修改人
			Usr usr = usrBiz.selectByCode(scmPurReturns.getEditor(), param);
			if (usr != null) {
				scmPurReturns.setConvertMap(ScmPurReturns2.FN_EDITOR, usr);
				scmPurReturns.setEditorName(usr.getName());
			}
		}
		if (StringUtils.isNotBlank(scmPurReturns.getChecker())){
			//审核人
			Usr usr = usrBiz.selectByCode(scmPurReturns.getChecker(), param);
			if (usr != null) {
				scmPurReturns.setConvertMap(ScmPurReturns2.FN_CHECKER, usr);
				scmPurReturns.setCheckerName(usr.getName());
			}
		}
		if (StringUtils.isNotBlank(scmPurReturns.getPurOrgUnitNo())){
			//采购组织
			OrgPurchase2 orgPurchase = orgPurchaseBiz.selectByOrgUnitNo(scmPurReturns.getPurOrgUnitNo(), param);
			if (orgPurchase != null) {
				scmPurReturns.setConvertMap(ScmPurReturns2.FN_PURORGUNITNO, orgPurchase);
				scmPurReturns.setPurOrgUnitName(orgPurchase.getOrgUnitName());
			}
		}
		
		if (StringUtils.isNotBlank(scmPurReturns.getOrgUnitNo())){
			//库存组织
			OrgBaseUnit orgBaseUnit = orgUnitBiz.selectbyOrgNo(scmPurReturns.getOrgUnitNo(), param);
			if (orgBaseUnit != null) {
				scmPurReturns.setConvertMap(ScmPurReturns2.FN_ORGUNITNO, orgBaseUnit);
			}
		}
		if(StringUtils.isNotBlank(scmPurReturns.getStatus())){
			Code code = codeBiz.selectByCategoryAndCode("quotationStatus", scmPurReturns.getStatus());
			if(code!=null)
				scmPurReturns.setStatusName(code.getName());
		}
		if(StringUtils.isNotBlank(scmPurReturns.getBizType())){
			Code code = codeBiz.selectByCategoryAndCode("returnBizType", scmPurReturns.getBizType());
			if(code!=null)
				scmPurReturns.setBizTypeName(code.getName());
		}
		scmPurReturns.setTaxAmount(scmPurReturns.getTaxAmt().subtract(scmPurReturns.getAmt()));
	}
	@Override
	protected void afterSelect(CommonBean bean, Param param) throws AppException {
		ScmPurReturns2 scmPurReturns = (ScmPurReturns2) bean.getList().get(0);
		HashMap<String,Object> cacheMap = new HashMap<String,Object>();
		if(scmPurReturns != null && scmPurReturns.getId() > 0){
			bean.setList2(scmPurReturnsEntryBiz.selectByRtId(scmPurReturns.getId(), param));
		}
	}

	@Override
	protected void beforeAdd(CommonBean bean, Param param) throws AppException {
		int amtPrec = Integer.parseInt(sysParamBiz.getValue(param.getOrgUnitNo(), "SCM_AmtPrecision", "2", param));
		ScmPurReturns2 scmPurReturns2 = (ScmPurReturns2) bean.getList().get(0);
		if(scmPurReturns2 != null){
//			String date = StringUtils.replace(FormatUtils.fmtDate(scmPurReturns2.getCreateDate()), "-", "");
//			StringBuffer s = new StringBuffer("");
//			s.append("RT").append(date);
//			HashMap<String, Object> map = new HashMap<String, Object>();
//			map.put("createDate", FormatUtils.fmtDate(FormatUtils.parseDateTime(date, "yyyyMMdd")));
//			map.put("controlUnitNo", param.getControlUnitNo());
//			ScmPurReturns scmPurReturns= ((ScmPurReturnsDAO) dao).selectMaxIdByDate(map);
//			if(scmPurReturns != null && StringUtils.isNotBlank(scmPurReturns.getRtNo())
//					&& scmPurReturns.getRtNo().contains(date)){
//				String str = StringUtils.substring(scmPurReturns.getRtNo(), (scmPurReturns.getRtNo().indexOf(date)+date.length()));
//				str = CodeAutoCalUtil.autoAddOne(str);
//				str = (s.append(str)).toString();
//				scmPurReturns2.setRtNo(str);
//			}else{
//				scmPurReturns2.setRtNo((s.append("001").toString()));
//			}
			String code = CodeAutoCalUtil.getBillCode("PurReturns", scmPurReturns2, param);
			scmPurReturns2.setRtNo(code);
			List<ScmPurReturnsEntry2> scmPurReturnsEntryList = bean.getList2();
            BigDecimal amt= BigDecimal.ZERO;
            BigDecimal taxAmt= BigDecimal.ZERO;
			if(scmPurReturnsEntryList != null && !scmPurReturnsEntryList.isEmpty()){
				for(ScmPurReturnsEntry2 scmPurReturnsEntry : scmPurReturnsEntryList){
					BigDecimal b = (scmPurReturnsEntry.getQty()).multiply(scmPurReturnsEntry.getPrice()).setScale(amtPrec, RoundingMode.HALF_UP);
                    scmPurReturnsEntry.setAmt(b);
                    BigDecimal d = (scmPurReturnsEntry.getQty()).multiply(scmPurReturnsEntry.getTaxPrice()).setScale(amtPrec, RoundingMode.HALF_UP);
                    scmPurReturnsEntry.setTaxAmt(d);
                    amt = amt.add(scmPurReturnsEntry.getAmt());
                    taxAmt = taxAmt.add(scmPurReturnsEntry.getTaxAmt());
				}
			}
			scmPurReturns2.setAmt(amt);
			scmPurReturns2.setTaxAmt(taxAmt);
			//获取期间
			PeriodCalendar periodCalendar = periodCalendarBiz.selectByBizdate(scmPurReturns2.getBizDate(), param);
			if(periodCalendar==null){
				throw new AppException("com.armitage.server.iars.daily.selectByBizdate.error");
			}
			scmPurReturns2.setPeriodId(periodCalendar.getPeriodId());
			scmPurReturns2.setAccountYear(periodCalendar.getAccountYear());
			scmPurReturns2.setAccountPeriod(periodCalendar.getAccountPeriod());
//			BillType2 billType = billTypeBiz.selectByOrgAndCode(scmPurReturns2.getFinOrgUnitNo(), "PurReturns", param);
//			if(billType!=null && billType.isNeedAudit()) {
//				if(StringUtils.isBlank(billType.getWorkFlowNo())) {
//					throw new AppException(Message.getMessage(param.getLang(), "iscm.workflow.needaudit.notprocess", new String[] {Message.getMessage(param.getLang(),"field.ScmPurReturns")}));
//				}
//				scmPurReturns2.setWorkFlowNo(billType.getWorkFlowNo());
//			}
		}
	}
	
	@Override
	protected void afterAdd(CommonBean bean, Param param) throws AppException {
		ScmPurReturns2 scmPurReturns = (ScmPurReturns2) bean.getList().get(0);
		if(scmPurReturns != null && scmPurReturns.getId() > 0){
			//新增退货明细
			List<ScmPurReturnsEntry2> scmPurReturnsEntryList = bean.getList2();
			if(scmPurReturnsEntryList != null && !scmPurReturnsEntryList.isEmpty()){
				int lineId = 1;
				for(ScmPurReturnsEntry2 scmPurReturnsEntry : scmPurReturnsEntryList){
					scmPurReturnsEntry.setLineId(lineId);
					scmPurReturnsEntry.setRtId(scmPurReturns.getId());
					scmPurReturnsEntry.setPurOrgUnitNo(scmPurReturns.getPurOrgUnitNo());
					ScmMaterial2 scmMaterial = scmMaterialBiz.selectDirect(scmPurReturnsEntry.getItemId(), param);
					if(scmMaterial==null){
						throw new AppException("iscm.purchasemanage.purchasebusiness.ScmPurRequireBizImpl.error.itemnorexists");
					}
					scmPurReturnsEntry.setBaseUnit(scmMaterial.getBaseUnitId());
					BigDecimal purConvRate = ScmMaterialUtil.getConvRate(scmPurReturnsEntry.getItemId(), scmPurReturnsEntry.getPurUnit(), param);//采购单位转换系数
					BigDecimal invConvRate = ScmMaterialUtil.getConvRate(scmPurReturnsEntry.getItemId(), scmPurReturnsEntry.getUnit(), param);//库存单位转换系数
					scmPurReturnsEntry.setBaseQty(scmPurReturnsEntry.getQty().multiply(purConvRate));
					scmPurReturnsEntry.setInvQty(scmPurReturnsEntry.getQty().multiply(purConvRate).divide(invConvRate,4,BigDecimal.ROUND_HALF_UP));	//退库数量
					scmPurReturnsEntryBiz.add(scmPurReturnsEntry, param);
					lineId = lineId+1;
				}
			}
		}
	}
	
	@Override
	protected void afterUpdate(CommonBean bean, Param param) throws AppException {
		ScmPurReturns2 scmPurReturns2 = (ScmPurReturns2) bean.getList().get(0);
		if(scmPurReturns2 != null && scmPurReturns2.getId() > 0){
			//更新退货明细
			List<ScmPurReturnsEntry2> ScmPurReturnsEntryList = bean.getList2();
			if(ScmPurReturnsEntryList != null && !ScmPurReturnsEntryList.isEmpty()){
				int lineId = 1;
				for(ScmPurReturnsEntry2 scmPurReturnsEntry : ScmPurReturnsEntryList){
					scmPurReturnsEntry.setRtId(scmPurReturns2.getId());
					if (StringUtils.equals("I", scmPurReturns2.getStatus())) {
						scmPurReturnsEntry.setLineId(lineId);
					}
					scmPurReturnsEntry.setPurOrgUnitNo(scmPurReturns2.getPurOrgUnitNo());
					ScmMaterial2 scmMaterial = scmMaterialBiz.selectDirect(scmPurReturnsEntry.getItemId(), param);
					if(scmMaterial==null){
						throw new AppException("iscm.purchasemanage.purchasebusiness.ScmPurRequireBizImpl.error.itemnorexists");
					}
					scmPurReturnsEntry.setBaseUnit(scmMaterial.getBaseUnitId());
					BigDecimal purConvRate = ScmMaterialUtil.getConvRate(scmPurReturnsEntry.getItemId(), scmPurReturnsEntry.getPurUnit(), param);//采购单位转换系数
					BigDecimal invConvRate = ScmMaterialUtil.getConvRate(scmPurReturnsEntry.getItemId(), scmPurReturnsEntry.getUnit(), param);//库存单位转换系数
					scmPurReturnsEntry.setBaseQty(scmPurReturnsEntry.getQty().multiply(purConvRate));
					scmPurReturnsEntry.setInvQty(scmPurReturnsEntry.getQty().multiply(purConvRate).divide(invConvRate,4,BigDecimal.ROUND_HALF_UP));	//退库数量
					lineId = lineId+1;
				}
				scmPurReturnsEntryBiz.update(scmPurReturns2, ScmPurReturnsEntryList, ScmPurReturnsEntry2.FN_RTID, param);
			}
		}
	}
	

	private ScmPurReturns2 updateStatus(ScmPurReturns2 scmPurReturns, Param param) throws AppException {
		if (scmPurReturns != null) {
			ScmPurReturns2 scmPurReturns2 = this.updateDirect(scmPurReturns, param);
			if (scmPurReturns2 != null) {
				scmPurReturnsEntryBiz.updateRowStatusByRtId(scmPurReturns2.getId(), scmPurReturns2.getStatus(), scmPurReturns2.getChecker(), scmPurReturns2.getCheckDate(), param);
				return scmPurReturns;
			}
		}
		return null;
	}

	@Override
	protected void afterDelete(ScmPurReturns2 entity, Param param) throws AppException {
		if(entity != null && entity.getId() > 0){
			//删除退货明细
			scmPurReturnsEntryBiz.deleteByRtId(entity.getId(), param);
		}
	}

	@Override
	public ScmPurReturns2 submit(ScmPurReturns2 scmPurReturns, Param param) throws AppException {
		ScmPurReturns2 pruReturns = null;
		if(scmPurReturns.getId()>0){
			pruReturns = this.selectDirect(scmPurReturns.getId(), param);
			setConvertMap(pruReturns,param);
		}
		if(pruReturns!=null){
			if(!StringUtils.equals(pruReturns.getStatus(),"I")){
				throw new AppException("iscm.purchasemanage.error.commit");
			}else if(pruReturns.getStatus().equals("I")){
				BillType2 billType = billTypeBiz.selectByOrgAndCode(pruReturns.getFinOrgUnitNo(), "PurReturns", param);
				boolean needAudit = false;
				if(billType!=null && billType.isNeedAudit())
					needAudit = true;
				if(needAudit){
					//发起流程
					List<ScmPurReturnsEntry2> scmPurReturnsEntryList = scmPurReturnsEntryBiz.selectByRtId(pruReturns.getId(),param);
					
					CommonBean bean = new CommonBean();
					List<ScmPurReturns2> scmPurReturnsList = new ArrayList<>();
					scmPurReturnsList.add(pruReturns);
					bean.setList(scmPurReturnsList);
					bean.setList2(scmPurReturnsEntryList);
					String processInstanceId = ActivityUtil.startProcessInstance(bean, billType.getId(), billType.getWorkFlowNo(), param);
					pruReturns.setWorkFlowNo(processInstanceId);
					ActivityUtil activityUtil = new ActivityUtil();
					//判断当前流程是否结束
					boolean isCompleteTask = activityUtil.queryProInstanceStateByProInstanceId(processInstanceId);
					if(isCompleteTask) {
						if(param.getUsrCode()!=null ){
							pruReturns.setChecker(param.getUsrCode());
							pruReturns.setSubmitter(param.getUsrCode());
						}
						pruReturns.setCheckDate(CalendarUtil.getDate(param));
						pruReturns.setSubmitDate(CalendarUtil.getDate(param));
						pruReturns.setStatus("A");
					}else {
						if(param.getUsrCode()!=null ){
							pruReturns.setSubmitter(param.getUsrCode());
						}
						pruReturns.setSubmitDate(CalendarUtil.getDate(param));
						pruReturns.setStatus("D");
						String usrList= ActivityUtil.findAssigneeByProcessInstanceId(processInstanceId,param);
						if(StringUtils.isNotBlank(usrList)) {
							scmBillPendingBiz.addPendingBill(usrList, pruReturns, param);
							PurReturnsParams purReturnsParams = new PurReturnsParams();
							purReturnsParams.setRtNo(pruReturns.getRtNo());
							AuditMsgUtil.sendAuditMsg("PurReturns",pruReturns,purReturnsParams, usrList, param);
						}
					}
					
					if(param.getUsrCode()!=null ){
						pruReturns.setSubmitter(param.getUsrCode());
					}
					pruReturns.setStatus("D");
				}else{
					if(param.getUsrCode()!=null ){
						pruReturns.setChecker(param.getUsrCode());
						pruReturns.setSubmitter(param.getUsrCode());
					}
					pruReturns.setCheckDate(CalendarUtil.getDate(param));
					pruReturns.setSubmitDate(CalendarUtil.getDate(param));
					pruReturns.setStatus("A");
				}
				try {
					this.updateStatus(pruReturns, param);
				} catch (Exception e) {
					throw e;
				}
				if(StringUtils.contains("A,B", pruReturns.getStatus())) {
					//通过或部分通过时检查是否自动下达
					if(billType!=null && billType.isAutoRelease()) {
						this.release(pruReturns, param);
					}
				}
			} else {
			}
		}else{
			throw new AppException("iscm.purchasemanage.purchasebusiness.find.ordernotexists");
		}
		return pruReturns;
	}

	@Override
	public ScmPurReturns2 undoSubmit(ScmPurReturns2 scmPurReturns, Param param) throws AppException {
		ScmPurReturns2 pruReturns = null;
		if(scmPurReturns.getId()>0){
			pruReturns = this.selectDirect(scmPurReturns.getId(), param);
		}
		if(pruReturns!=null){
			BillType2 billType = billTypeBiz.selectByOrgAndCode(pruReturns.getFinOrgUnitNo(), "PurReturns", param);
			boolean needAudit = false;
			if(billType!=null && billType.isNeedAudit())
				needAudit = true;
			
			//单据设置审批流程，但设置没有审批节点，状态为通过，可以取消通过
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put(ScmBillPending.FN_BILLID, new QueryParam(ScmBillPending.FN_BILLID, QueryParam.QUERY_EQ, String.valueOf(pruReturns.getId())));
			map.put(ScmBillPending.FN_BILLTYPE, new QueryParam(ScmBillPending.FN_BILLTYPE, QueryParam.QUERY_EQ, billType.getBillCode()));
			List<ScmBillPending> scmBillPendingList = scmBillPendingBiz.findAll(map, param);
			
			if(needAudit){
				if (scmBillPendingList == null || scmBillPendingList.isEmpty()) {
					if(!StringUtils.equals(pruReturns.getStatus(),"A"))
						throw new AppException("iscm.purchasemanage.error.cancelCommit");
				} else {
					if(!StringUtils.equals(pruReturns.getStatus(),"D"))
						throw new AppException("iscm.purchasemanage.error.cancelCommit");
				}
			}else{
				if(!StringUtils.equals(pruReturns.getStatus(),"A"))
					throw new AppException("iscm.purchasemanage.error.cancelCommit");
			} 
			if(pruReturns.getStatus().equals("A") || pruReturns.getStatus().equals("D")){
				pruReturns.setChecker(null);
				pruReturns.setSubmitter(null);
				pruReturns.setCheckDate(null);
				pruReturns.setSubmitDate(null);
				pruReturns.setStatus("I");
				try {
					this.updateStatus(pruReturns, param);
					scmBillPendingBiz.cancelPendingBill(param.getUsrCode(),pruReturns, param);
				} catch (Exception e) {
					throw e;
				}
			}
		}else{
			throw new AppException("iscm.purchasemanage.purchasebusiness.find.ordernotexists");
		}
		return pruReturns;
	}

	@Override
	public ScmPurReturns2 release(ScmPurReturns2 scmPurReturns, Param param)
			throws AppException {
		ScmPurReturns2 pruReturns = null;
		if(scmPurReturns.getId()>0){
			pruReturns = this.selectDirect(scmPurReturns.getId(), param);
		}
		if(pruReturns!=null){
			if(!StringUtils.equals(pruReturns.getStatus(),"A")){
				throw new AppException("iscm.purchasemanage.error.release");
			}else if(pruReturns.getStatus().equals("A")){
				pruReturns.setStatus("E");
				try {
					this.updateStatus(pruReturns, param);
					this.afterRelease(pruReturns, param);
				} catch (Exception e) {
					throw e;
				}
			}
		}else{
			throw new AppException("iscm.purchasemanage.purchasebusiness.find.ordernotexists");
		}
		return pruReturns;
	}

	private void afterRelease(ScmPurReturns2 scmPurReturns, Param param) throws AppException {
		scmInvPurInWarehsBillBiz.generatePurInWarehsBillByReturns(scmPurReturns, param);
	}

	@Override
	public ScmPurReturns2 undoRelease(ScmPurReturns2 scmPurReturns, Param param)
			throws AppException {
		ScmPurReturns2 pruReturns = null;
		if(scmPurReturns.getId()>0){
			pruReturns = this.selectDirect(scmPurReturns.getId(), param);
		}
		if(pruReturns!=null){
			if(!StringUtils.equals(pruReturns.getStatus(),"E")){
				throw new AppException("iscm.purchasemanage.error.cancelRelease");
			}else if(pruReturns.getStatus().equals("E")){
				pruReturns.setStatus("A");
				try {
					this.updateStatus(pruReturns, param);
					this.afterUndoRelease(pruReturns, param);
				} catch (Exception e) {
					throw e;
				}
			}
		}else{
			throw new AppException("iscm.purchasemanage.purchasebusiness.find.ordernotexists");
		}
		return pruReturns;
	}

	private void afterUndoRelease(ScmPurReturns2 pruReturns, Param param) throws AppException {
		//取消下达需同步删除新建状态生成的采购入库退货单
		List<ScmInvPurInWarehsBill2> scmInvPurInWarehsBillList = scmInvPurInWarehsBillBiz.selectByPurReturns(pruReturns,param);
		if(scmInvPurInWarehsBillList!=null && !scmInvPurInWarehsBillList.isEmpty()){
			for(ScmInvPurInWarehsBill2 scmInvPurInWarehsBill:scmInvPurInWarehsBillList) {
				if(scmInvPurInWarehsBill!=null && !StringUtils.equals(scmInvPurInWarehsBill.getStatus(),"I")){
					throw new AppException(Message.getMessage(param.getLang(), "iscm.inventorymanage.scmPurReturns.undoRelease.error.PurWarehsBillNotNewSattus", new String[]{scmInvPurInWarehsBill.getWrNo()}));
				}
			}
			scmInvPurInWarehsBillBiz.delete(scmInvPurInWarehsBillList, param);
		}
	}

	@Override
	public void generateReturns(ScmPurReceive2 receive, Param param) throws AppException {
		if(receive.isUnified()){
			List<ScmPurReceiveEntry2> scmPurReceiveEntryList = scmPurReceiveEntryBiz.selectByNotWr(receive.getId(), param);
			if(scmPurReceiveEntryList!=null && !scmPurReceiveEntryList.isEmpty()){
				for (int i = scmPurReceiveEntryList.size() - 1; i >= 0; i--) {
					ScmPurReceiveEntry2 scmPurReceiveEntry = scmPurReceiveEntryList.get(i);
					if(scmPurReceiveEntry.getQty().compareTo(scmPurReceiveEntry.getDeliveryQty())>=0 && scmPurReceiveEntry.getUnqualifiedQty().compareTo(BigDecimal.ZERO)==0){
						//收货数量与配送数量不存在差异，且不合格数量为0，则不需生成退货
						scmPurReceiveEntryList.remove(i);
					}
				}
				if(scmPurReceiveEntryList!=null && !scmPurReceiveEntryList.isEmpty()){
					CommonBean bean = new CommonBean();
	                List<ScmPurReturns2> scmPurReturnsList = new ArrayList<>();
	                List<ScmPurReturnsEntry2> scmPurReturnsEntryList = new ArrayList<>();
	                ScmPurReturns2 scmPurReturns = new ScmPurReturns2(true);
	                scmPurReturns.setStatus("I");
	                scmPurReturns.setBillTypeId("PurReceive");	//来自收货
	                scmPurReturns.setUnified(receive.isUnified());
	                scmPurReturns.setOrgUnitNo(receive.getOrgUnitNo());
	                scmPurReturns.setFinOrgUnitNo(receive.getFinOrgUnitNo());
	                scmPurReturns.setPurOrgUnitNo(receive.getPurOrgUnitNo());
	                scmPurReturns.setVendorId(receive.getVendorId());
	                scmPurReturns.setBizDate(FormatUtils.parseDate(FormatUtils.fmtDate(CalendarUtil.getDate(param))));
	                scmPurReturns.setCurrencyNo(receive.getCurrencyNo());
	                scmPurReturns.setExchangeRate(receive.getExchangeRate());
	                scmPurReturns.setCreator(param.getUsrCode());
	                scmPurReturns.setCreateDate(CalendarUtil.getDate(param));
	                scmPurReturns.setControlUnitNo(param.getControlUnitNo());
	                scmPurReturnsList.add(scmPurReturns);
	                bean.setList(scmPurReturnsList);
	                for(ScmPurReceiveEntry2 scmPurReceiveEntry:scmPurReceiveEntryList){
	                	if(scmPurReceiveEntry.getDeliveryQty().subtract(scmPurReceiveEntry.getQty()).subtract(scmPurReceiveEntry.getUnqualifiedQty()).compareTo(BigDecimal.ZERO)>0){
	                		//少供或缺供
	                		ScmPurReturnsEntry2 scmPurReturnsEntry = new ScmPurReturnsEntry2(true);
	                		scmPurReturnsEntry.setQty(scmPurReceiveEntry.getDeliveryQty().subtract(scmPurReceiveEntry.getQty()).subtract(scmPurReceiveEntry.getUnqualifiedQty()));
	                		scmPurReturnsEntry.setInvOrgUnitNo(scmPurReceiveEntry.getInvOrgUnitNo());
	                		scmPurReturnsEntry.setRowStatus("I");
	                		if(scmPurReceiveEntry.getQty().compareTo(BigDecimal.ZERO)==0) {
	                			scmPurReturnsEntry.setReasonCode("1");
	                			scmPurReturnsEntry.setRemarks(Message.getMessage(param.getLang(),"iscm.purchasebusiness.ScmPurReturns.detail.remarks.lackSupply"));
	                		}else {
	                			scmPurReturnsEntry.setReasonCode("2");
	                			scmPurReturnsEntry.setRemarks(Message.getMessage(param.getLang(),"iscm.purchasebusiness.ScmPurReturns.detail.remarks.lessSupply"));
	                		}
	                		SetEntryOtherInfo(scmPurReturnsEntry,scmPurReceiveEntry);
	                		scmPurReturnsEntryList.add(scmPurReturnsEntry);
	                	}
	                	if(scmPurReceiveEntry.getUnqualifiedQty().compareTo(BigDecimal.ZERO)>0){
	                		ScmPurReturnsEntry2 scmPurReturnsEntry = new ScmPurReturnsEntry2(true);
	                		scmPurReturnsEntry.setQty(scmPurReceiveEntry.getUnqualifiedQty().subtract(scmPurReceiveEntry.getConcessiveRecQty()));
	                		scmPurReturnsEntry.setInvOrgUnitNo(scmPurReceiveEntry.getInvOrgUnitNo());
	                		scmPurReturnsEntry.setRowStatus("I");
	                		DecimalFormat df1 = new DecimalFormat("0.00");  
	                	    String UnqualifiedQtyStr = df1.format(scmPurReceiveEntry.getUnqualifiedQty());  
	                	    String discountQtyStr = df1.format(scmPurReturnsEntry.getQty());
	                	    scmPurReturnsEntry.setReasonCode("3");
	                		scmPurReturnsEntry.setRemarks(Message.getMessage(param.getLang(), "iscm.purchasebusiness.ScmPurReturns.detail.remarks.Unqualified", new String[]{UnqualifiedQtyStr,discountQtyStr}));
	                		SetEntryOtherInfo(scmPurReturnsEntry,scmPurReceiveEntry);
	                		scmPurReturnsEntryList.add(scmPurReturnsEntry);
	                	}
	                }
	                bean.setList2(scmPurReturnsEntryList);
	                this.add(bean, param);
				}
			}
		}
	}
	
	private void SetEntryOtherInfo(ScmPurReturnsEntry2 scmPurReturnsEntry,ScmPurReceiveEntry2 scmPurReceiveEntry){
		scmPurReturnsEntry.setItemId(scmPurReceiveEntry.getItemId());
		scmPurReturnsEntry.setPurUnit(scmPurReceiveEntry.getPurUnit());
		scmPurReturnsEntry.setUnit(scmPurReceiveEntry.getUnit());
		scmPurReturnsEntry.setPieUnit(scmPurReceiveEntry.getPieUnit());
		scmPurReturnsEntry.setBaseUnit(scmPurReceiveEntry.getBaseUnit());
		scmPurReturnsEntry.setReceiveQty(scmPurReceiveEntry.getReceiveQty());
		scmPurReturnsEntry.setPrice(scmPurReceiveEntry.getPrice());
		scmPurReturnsEntry.setAmt(scmPurReturnsEntry.getQty().multiply(scmPurReturnsEntry.getPrice()));
		scmPurReturnsEntry.setTaxRate(scmPurReceiveEntry.getTaxRate());
		scmPurReturnsEntry.setTaxPrice(scmPurReceiveEntry.getTaxPrice());
		scmPurReturnsEntry.setTaxAmt(scmPurReturnsEntry.getQty().multiply(scmPurReturnsEntry.getTaxPrice()));
		scmPurReturnsEntry.setProductDate(scmPurReceiveEntry.getProductDate());
		scmPurReturnsEntry.setExpDate(scmPurReceiveEntry.getExpDate());
		scmPurReturnsEntry.setUseOrgUnitNo(scmPurReceiveEntry.getUseOrgUnitNo());
		scmPurReturnsEntry.setInvOrgUnitNo(scmPurReceiveEntry.getInvOrgUnitNo());
		scmPurReturnsEntry.setWareHouseId(scmPurReceiveEntry.getWareHouseId());
		scmPurReturnsEntry.setFinOrgUnitNo(scmPurReceiveEntry.getFinOrgUnitNo());
		scmPurReturnsEntry.setBalanceSupplierId(scmPurReceiveEntry.getBalanceSupplierId());
		scmPurReturnsEntry.setStorageOrgUnitNo(scmPurReceiveEntry.getStorageOrgUnitNo());
		scmPurReturnsEntry.setPurOrgUnitNo(scmPurReceiveEntry.getPurOrgUnitNo());
		scmPurReturnsEntry.setSourceDtlId(scmPurReceiveEntry.getId());
	}

	@Override
	protected void beforeDelete(ScmPurReturns2 entity, Param param)
			throws AppException {
		ScmPurReturns2 scmPurReturns = this.selectDirect(entity.getId(), param);
		if(scmPurReturns!=null && !StringUtils.equals(scmPurReturns.getStatus(),"I")){
			throw new AppException(Message.getMessage(param.getLang(), "iscm.inventorymanage.delete.error", new String[]{entity.getRtNo()}));
		}
	}

	@Override
	public List<ScmPurReturns2> audit(ScmPurAuditParam scmPurAuditParam,List<ScmPurReturns2> scmPurReturnsList, Param param) throws AppException {
		if(scmPurReturnsList!=null && !scmPurReturnsList.isEmpty()){
			scmPurReturnsList = this.select(scmPurReturnsList, param);
			for(ScmPurReturns2 scmPurReturns:scmPurReturnsList){
				if(!StringUtils.equals(scmPurReturns.getStatus(),"D")){
					throw new AppException("iscm.purchasemanage.error.audit");
				}else{
					if(StringUtils.equals("Y",scmPurAuditParam.getOpinion())){
						//同意
						scmPurReturns.setStatus("A");
					}else{
						//拒绝
						scmPurReturns.setStatus("N");
					}
					this.updateDirect(scmPurReturns, param);
				}
			}
			return scmPurReturnsList;
		}
		return null;
	}

	@Override
	public List<ScmPurReturns2> antiAudit(List<ScmPurReturns2> scmPurReturnsList, Param param) throws AppException {
		if(scmPurReturnsList!=null && !scmPurReturnsList.isEmpty()){
			scmPurReturnsList = this.select(scmPurReturnsList, param);
			for(ScmPurReturns2 scmPurReturns:scmPurReturnsList){
				if(!StringUtils.equals(scmPurReturns.getStatus(),"A")){
					throw new AppException("iscm.purchasemanage.error.antiAudit");
				}else{
					scmPurReturns.setStatus("D");
					this.updateDirect(scmPurReturns, param);
				}
			}
			return scmPurReturnsList;
		}
		return null;
	}

	@Override
	protected void beforeUpdate(CommonBean bean, Param param) throws AppException {
		int amtPrec = Integer.parseInt(sysParamBiz.getValue(param.getOrgUnitNo(), "SCM_AmtPrecision", "2", param));
		ScmPurReturns2 entry = (ScmPurReturns2) bean.getList().get(0);
		if(entry!=null) {
			ScmPurReturns2 scmPurReturns = this.select(entry.getPK(), param);
			if((!StringUtils.contains("I,D,P", scmPurReturns.getStatus()) && StringUtils.equals(scmPurReturns.getOrgUnitNo(),param.getOrgUnitNo()))){
				throw new AppException(Message.getMessage(param.getLang(), "iscm.commom.edit.error.notnewstatus"));
			}
			if ((!StringUtils.equals(scmPurReturns.getStatus(),"D") && StringUtils.equals(scmPurReturns.getPurOrgUnitNo(),param.getOrgUnitNo()))) {
			    throw new AppException(Message.getMessage(param.getLang(), "iscm.commom.edit.error.notPendingTrialtatus"));
	        }
			List<ScmPurReturnsEntry2> scmPurReturnsEntryList = bean.getList2();
	        BigDecimal amt= BigDecimal.ZERO;
	        BigDecimal taxAmt= BigDecimal.ZERO;
			if(scmPurReturnsEntryList != null && !scmPurReturnsEntryList.isEmpty()){
				for(ScmPurReturnsEntry2 scmPurReturnsEntry : scmPurReturnsEntryList){
					BigDecimal b = (scmPurReturnsEntry.getQty()).multiply(scmPurReturnsEntry.getPrice()).setScale(amtPrec, RoundingMode.HALF_UP);
                    scmPurReturnsEntry.setAmt(b);
                    BigDecimal d = (scmPurReturnsEntry.getQty()).multiply(scmPurReturnsEntry.getTaxPrice()).setScale(amtPrec, RoundingMode.HALF_UP);
                    scmPurReturnsEntry.setTaxAmt(d);
	                amt = amt.add(scmPurReturnsEntry.getAmt());
	                taxAmt = taxAmt.add(scmPurReturnsEntry.getTaxAmt());
				}
			}
			entry.setAmt(amt);
			entry.setTaxAmt(taxAmt);
			//获取期间
			PeriodCalendar periodCalendar = periodCalendarBiz.selectByBizdate(entry.getBizDate(), param);
			if(periodCalendar==null){
				throw new AppException("com.armitage.server.iars.daily.selectByBizdate.error");
			}
			entry.setPeriodId(periodCalendar.getPeriodId());
			entry.setAccountYear(periodCalendar.getAccountYear());
			entry.setAccountPeriod(periodCalendar.getAccountPeriod());
		}
	}

	@Override
	public void doAdvQuery(Page page, Param param) {
		if (page.getModel() != null) {
			if (page.getModel() instanceof ScmPurReturnsAdvQuery) {
				ScmPurReturnsAdvQuery scmPurReturnsAdvQuery = (ScmPurReturnsAdvQuery) page.getModel();
				if(scmPurReturnsAdvQuery.getBegBizDate()!=null){
					if(scmPurReturnsAdvQuery.getEndBizDate()!=null) {
						page.getParam().put(ScmPurReturns2.FN_BIZDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmPurReturns2.class) + "." +ScmPurReturns2.FN_BIZDATE, QueryParam.QUERY_BETWEEN,FormatUtils.fmtDate(scmPurReturnsAdvQuery.getBegBizDate()),FormatUtils.fmtDate(scmPurReturnsAdvQuery.getEndBizDate())));
					}else {
						page.getParam().put(ScmPurReturns2.FN_BIZDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmPurReturns2.class) + "." +ScmPurReturns2.FN_BIZDATE, QueryParam.QUERY_GE,FormatUtils.fmtDate(scmPurReturnsAdvQuery.getBegBizDate())));
					}
				}else if(scmPurReturnsAdvQuery.getEndBizDate()!=null) {
					page.getParam().put(ScmPurReturns2.FN_BIZDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmPurReturns2.class) + "." +ScmPurReturns2.FN_BIZDATE, QueryParam.QUERY_LE,FormatUtils.fmtDate(scmPurReturnsAdvQuery.getEndBizDate())));
				}
				if(scmPurReturnsAdvQuery.getCreateDateFrom()!=null){
					if(scmPurReturnsAdvQuery.getCreateDateTo()!=null) {
						page.getParam().put(ScmPurReturns2.FN_CREATEDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmPurReturns2.class) + "." +ScmPurReturns2.FN_CREATEDATE, QueryParam.QUERY_BETWEEN,FormatUtils.fmtDate(scmPurReturnsAdvQuery.getCreateDateFrom()),FormatUtils.fmtDate(CalendarUtil.relativeDate(scmPurReturnsAdvQuery.getCreateDateTo(),1))));
					}else {
						page.getParam().put(ScmPurReturns2.FN_CREATEDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmPurReturns2.class) + "." +ScmPurReturns2.FN_CREATEDATE, QueryParam.QUERY_GE,FormatUtils.fmtDate(scmPurReturnsAdvQuery.getCreateDateFrom())));
					}
				}else if(scmPurReturnsAdvQuery.getCreateDateTo()!=null) {
					page.getParam().put(ScmPurReturns2.FN_CREATEDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmPurReturns2.class) + "." +ScmPurReturns2.FN_CREATEDATE, QueryParam.QUERY_LE,FormatUtils.fmtDate(CalendarUtil.relativeDate(scmPurReturnsAdvQuery.getCreateDateTo(),1))));
				}
				if(StringUtils.isNotBlank(scmPurReturnsAdvQuery.getCreator())){
					page.getParam().put(ScmPurReturns2.FN_CREATOR,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmPurReturns2.class) + "." +ScmPurReturns2.FN_CREATOR, QueryParam.QUERY_EQ,scmPurReturnsAdvQuery.getCreator()));
				}
				if(scmPurReturnsAdvQuery.getVendorId()>0){
					page.getParam().put(ScmPurReturns2.FN_VENDORID,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmPurReturns2.class) + "." +ScmPurReturns2.FN_VENDORID, QueryParam.QUERY_EQ,String.valueOf(scmPurReturnsAdvQuery.getVendorId())));
				}
				if(StringUtils.isNotBlank(scmPurReturnsAdvQuery.getPurOrgUnitNo())){
					page.getParam().put(ScmPurReturns2.FN_PURORGUNITNO,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmPurReturns2.class) + "." +ScmPurReturns2.FN_PURORGUNITNO, QueryParam.QUERY_EQ,scmPurReturnsAdvQuery.getPurOrgUnitNo()));
				}
			}
		}
	}

	@Override
	public List<ScmPurReturns2> selectByPurInwareHouse(long wrId, Param param) throws AppException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("wrId", wrId);
		return ((ScmPurReturnsDAO)dao).selectByPurInwareHouse(map);
	}

	@Override
	public List<ScmPurReturns2> selectByPurInwareHouseReturn(long wrId, Param param) throws AppException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("wrId", wrId);
		return ((ScmPurReturnsDAO)dao).selectByPurInwareHouseReturn(map);
	}

	@Override
	public ScmPurReturns2 queryPurReturns(ScmPurReturns2 scmPurReturns,
			Param param) throws AppException {
		Page page=new Page();
		page.setModelClass(ScmPurReturns2.class);
		page.setShowCount(Integer.MAX_VALUE);
		page.getParam().put(ScmPurReturns2.FN_RTNO,new QueryParam(ScmPurReturns2.FN_RTNO, QueryParam.QUERY_EQ,scmPurReturns.getRtNo()));
		
		List<ScmPurReturns2> scmPurReturnsList =this.findPage(page, param);
		ScmPurReturns2 scmPurReturns2 = new ScmPurReturns2();
		if(scmPurReturnsList!=null && !scmPurReturnsList.isEmpty()){
			scmPurReturns2 = scmPurReturnsList.get(0);
			scmPurReturns2.setScmPurReturnsEntryList(scmPurReturnsEntryBiz.selectByRtId(scmPurReturns2.getId(), param));
			
			//增加待审人
			ScmBillPending2 scmBillPendingUsr = scmBillPendingBiz.selectPendingUsr(scmPurReturns2.getId(), "PurReturns",param);
			if (scmBillPendingUsr != null) {
				scmPurReturns2.setPendingUsr(scmBillPendingUsr.getUsrCodes());
			}
		}else{
			throw new AppException("iscm.purchasemanage.pricemanage.find.ordernotexists");
		}
		return scmPurReturns2;
	}

	@Override
	public ScmPurReturns2 doAuditPurReturns(
			CommonAuditParams commonAuditParams, Param param)
			throws AppException {
		ScmPurReturns2 returns = null;
		
		ScmPurReturns2 scmPurReturns= new ScmPurReturns2();
		scmPurReturns.setId(commonAuditParams.getBillId());
		scmPurReturns.setRtNo(commonAuditParams.getBillNo());
		String opinion = commonAuditParams.getOpinion();
		if(StringUtils.equals("Y", opinion) || StringUtils.equals("agree", opinion)) {
			opinion="agree";
		}else if(StringUtils.equals("N", opinion) || StringUtils.equals("refuse", opinion)) {
			opinion="refuse";
		}
		if(scmPurReturns.getId()>0){
			returns = this.selectDirect(scmPurReturns.getId(), param);
		}else{
			HashMap<String,Object> map=new HashMap<String,Object>();
			map.put(
					ScmPurReturns2.FN_RTNO,
					new QueryParam(ScmPurReturns2.FN_RTNO, QueryParam.QUERY_EQ,
							scmPurReturns.getRtNo()));
			map.put(ScmPurReturns2.FN_CONTROLUNITNO, new QueryParam(ScmPurReturns2.FN_CONTROLUNITNO, QueryParam.QUERY_EQ,
							param.getControlUnitNo()));
			
			List<ScmPurReturns2> scmPurReturnsList =this.findAll(map, param);
			
			if(scmPurReturnsList!=null && !scmPurReturnsList.isEmpty()){
				returns = scmPurReturnsList.get(0);
			}
		}
		
		if (returns != null) {
			this.setConvertMap(returns,param);
			if(!(returns.getStatus().equals("D") || returns.getStatus().equals("P"))){
				throw new AppException("iscm.purchasemanage.error.audit");
			}
			if(StringUtils.equals("R", opinion)) {
				scmBillPendingBiz.deletePendingBill(param.getUsrCode(),returns, param);
				commonEventHistoryBiz.updateInvalid(returns,"",param);
				
				CommonAuditOpinion commonAuditOpinionR = new CommonAuditOpinion();
				commonAuditOpinionR.setPreStepNo(returns.getStepNo());
				String stepNo = StringUtils.replace(UUID.randomUUID().toString(), "-", "");
				commonAuditOpinionR.setStepNo(stepNo);
				commonAuditOpinionR.setActiveType("A");
				commonAuditOpinionR.setOpinion(commonAuditParams.getOpinion());
				commonAuditOpinionR.setRemarks(commonAuditParams.getOpinionRemarks());
				commonEventHistoryBiz.addEventHistory(returns, commonAuditOpinionR, param);
				
				//驳回则将单据变回新单状态
				returns.setStatus("I");
				returns.setChecker(null);
				returns.setCheckDate(null);
				return this.updateDirect(returns, param);
			}
			String processInstanceId = returns.getWorkFlowNo();
			boolean isCompleteTask = true;
			
			if (processInstanceId != null && !"".equals(processInstanceId)) {
				//完成审批任务
				ActivityUtil.completeTaskByAssigneeAndOpinion(processInstanceId, param.getUsrCode(), opinion, param);
				ActivityUtil activityUtil = new ActivityUtil();
				//判断当前流程是否结束
				isCompleteTask = activityUtil.queryProInstanceStateByProInstanceId(processInstanceId);
				//P：审核中，A：通过，N：未通过
				if ("agree".equals(opinion)) {
					if (isCompleteTask) {
						returns.setStatus("A");
					} else {
						returns.setStatus("P");
					}
				} else {
					returns.setStatus("N");
				}
				scmBillPendingBiz.updatePendingBill(param.getUsrCode(), returns, param);
				returns.setChecker(param.getUsrCode());
				if ("agree".equals(opinion)) {
					String usrList= ActivityUtil.findAssigneeByProcessInstanceId(processInstanceId,param);
					if(StringUtils.isNotBlank(usrList)) {
						scmBillPendingBiz.addPendingBill(usrList, returns, param);
						PurReturnsParams purReturnsParams = new PurReturnsParams();
						purReturnsParams.setRtNo(returns.getRtNo());
						AuditMsgUtil.sendAuditMsg("PurReturns",returns,purReturnsParams, usrList, param);
					}
				}
				CommonAuditOpinion commonAuditOpinion = new CommonAuditOpinion();
				commonAuditOpinion.setPreStepNo(returns.getStepNo());
				String stepNo = StringUtils.replace(UUID.randomUUID().toString(), "-", "");
				returns.setStepNo(stepNo);
				returns.setCheckDate(CalendarUtil.getDate(param));
				try {
					this.updateStatus(returns, param);
				} catch (Exception e) {
					throw e;
				}
				
				commonAuditOpinion.setStepNo(stepNo);
				commonAuditOpinion.setActiveType("A");
				commonAuditOpinion.setOpinion(commonAuditParams.getOpinion());
				commonAuditOpinion.setRemarks(commonAuditParams.getOpinionRemarks());
				commonEventHistoryBiz.addEventHistory(returns, commonAuditOpinion, param);
			} else {
				//流程编号不存在,认为是旧模式
//				throw new AppException("com.armitage.server.iscm.purchasemanage.purchasebusiness.service.impl.ScmPurRequireBizImpl.doAuditPurRequire.processInstanceId");
				if ("agree".equals(opinion)) {
					returns.setStatus("A");
				} else {
					returns.setStatus("N");
				}
				try {
					this.updateStatus(returns, param);
				} catch (Exception e) {
					throw e;
				}
			}
			
			
			if(StringUtils.contains("A,B", returns.getStatus())) {
				//通过或部分通过时检查是否自动下达
				BillType2 billType = billTypeBiz.selectByOrgAndCode(returns.getFinOrgUnitNo(), "PurReturns", param);
				if(billType!=null && billType.isAutoRelease()) {
					this.release(returns, param);
				}
			}
		} else {
			throw new AppException("iscm.purchasemanage.purchasebusiness.find.ordernotexists");
		}
		return returns;
	}

	@Override
	public ScmPurReturns2 doUnAuditPurReturns(ScmPurReturns2 scmPurturns,
			Param param) throws AppException {
		ScmPurReturns2 returns = null;
		List<ScmPurReturns2> scmPurReturnsList = new ArrayList<> ();
		List<ScmPurReturnsEntry2> scmPurReturnsEntryList = new ArrayList<> ();
		boolean isFirstTask = false;
		
		if(scmPurturns.getId()>0){
			returns = this.selectDirect(scmPurturns.getId(), param);
		}else{
			HashMap<String,Object> map=new HashMap<String,Object>();
			map.put(
					ScmPurReturns2.FN_RTNO,
					new QueryParam(ScmPurReturns2.FN_RTNO, QueryParam.QUERY_EQ,
							scmPurturns.getRtNo()));
			map.put(ScmPurReturns2.FN_CONTROLUNITNO, new QueryParam(ScmPurReturns2.FN_CONTROLUNITNO, QueryParam.QUERY_EQ,
					param.getControlUnitNo()));
			
			scmPurReturnsList =this.findAll(map, param);
			if(scmPurReturnsList!=null && !scmPurReturnsList.isEmpty()){
				returns=scmPurReturnsList.get(0);
			}
		}
		
		if (returns != null) {
			if(!StringUtils.contains("A,B,N,P", returns.getStatus())){
				throw new AppException("iscm.purchasemanage.unaudit.error.status");
			}
			String processInstanceId = returns.getWorkFlowNo();
			scmPurReturnsEntryList = scmPurReturnsEntryBiz.selectByRtId(returns.getId(), param);
			
			if (processInstanceId != null && !"".equals(processInstanceId)) {
				//反审批当前已审节点
				CommonBean bean = new CommonBean();
				scmPurReturnsList.add(returns);
				bean.setList(scmPurReturnsList);
				bean.setList2(scmPurReturnsEntryList);
				
				BillType2 billType = billTypeBiz.selectByOrgAndCode(returns.getFinOrgUnitNo(), "PurReturns", param);
				long billTypeId = 0;
				if (billType!=null && billType.isNeedAudit()) {
					billTypeId = billType.getId();
				}
				
				processInstanceId = ActivityUtil.antiAuditProcess(bean, billTypeId, processInstanceId, param.getUsrCode(),  param);
				isFirstTask = ActivityUtil.isFirstTask(processInstanceId, param.getUsrCode(),  param);
				returns.setWorkFlowNo(processInstanceId);
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
			returns.setStatus(status);
			String tableName = ClassUtils.getFinalModelSimpleName(returns);
			CommonEventHistory commonEventHistory = commonEventHistoryBiz.loadAuditEventHistory(tableName,returns.getStepNo(),returns.getPK(),param);
			if(commonEventHistory!=null && StringUtils.isNotBlank(commonEventHistory.getPreStepNo())) {
				commonEventHistory = commonEventHistoryBiz.loadAuditEventHistory(tableName,commonEventHistory.getPreStepNo(),returns.getPK(),param);
			}
			if(commonEventHistory!=null) {
				returns.setChecker(commonEventHistory.getOper());
				returns.setCheckDate(commonEventHistory.getOperDate());
			}else {
				returns.setChecker(null);
				returns.setCheckDate(null);
			}
			this.updateStatus(returns, param);
			scmBillPendingBiz.cancelPendingBill(param.getUsrCode(),returns, param);
			commonEventHistoryBiz.updateInvalid(returns,returns.getStepNo(),param);
			CommonAuditOpinion commonAuditOpinion = new CommonAuditOpinion();
			commonAuditOpinion.setStepNo(returns.getStepNo());
			commonAuditOpinion.setActiveType("U");
			commonAuditOpinion.setOpinion("Y");
			commonEventHistoryBiz.addEventHistory(returns, commonAuditOpinion, param);
		} else {
			throw new AppException("iscm.purchasemanage.purchasebusiness.find.ordernotexists");
		}
		
		return returns;
	}

	@Override
	public ScmPurReturns2 updatePrtCount(ScmPurReturns2 smPurReturns, Param param) throws AppException {
		if(smPurReturns.getId()>0){
			ScmPurReturns2 bill = this.selectDirect(smPurReturns.getId(), param);
			if(bill != null){
				int prtCount = bill.getPrtcount();
				bill.setPrtcount((prtCount+1));
				this.updateDirect(bill, param);
				return bill;
			}
		}
		return smPurReturns;
	}
}
