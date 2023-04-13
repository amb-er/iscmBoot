package com.armitage.server.iscm.inventorymanage.cstbusiness.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;

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
import com.armitage.server.common.util.SimpleDataDisplayUtil;
import com.armitage.server.iars.basedata.model.PeriodCalendar;
import com.armitage.server.iars.basedata.service.PeriodCalendarBiz;
import com.armitage.server.iscm.basedata.model.ScmMaterial2;
import com.armitage.server.iscm.basedata.service.ScmMaterialBiz;
import com.armitage.server.iscm.basedata.util.ScmMaterialUtil;
import com.armitage.server.iscm.inventorymanage.cstbusiness.dao.ScmCstInitBillDAO;
import com.armitage.server.iscm.inventorymanage.cstbusiness.dao.ScmInvMoveBillDAO;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmCstInitBill2;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmCstInitBillEntry2;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmCstInitBillImPort;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmCstInitBillImPortDetail;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmCstInitBillQuery;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCostConsume2;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCostConsumeQuery;
import com.armitage.server.iscm.inventorymanage.cstbusiness.service.ScmCstInitBillBiz;
import com.armitage.server.iscm.inventorymanage.cstbusiness.service.ScmCstInitBillEntryBiz;
import com.armitage.server.iscm.inventorymanage.inventorydata.service.ScmInvBalBiz;
import com.armitage.server.iscm.inventorymanage.inventorydata.service.ScmInvStockBiz;
import com.armitage.server.system.model.BillType2;
import com.armitage.server.system.model.OrgBaseUnit;
import com.armitage.server.system.model.OrgCompany2;
import com.armitage.server.system.model.OrgCostCenter2;
import com.armitage.server.system.model.OrgStorage2;
import com.armitage.server.system.model.SystemState;
import com.armitage.server.system.service.BillTypeBiz;
import com.armitage.server.system.service.OrgCompanyBiz;
import com.armitage.server.system.service.OrgUnitBiz;
import com.armitage.server.system.service.OrgUnitRelationBiz;
import com.armitage.server.system.service.SystemStateBiz;
import com.armitage.server.system.util.CodeAutoCalUtil;
import com.armitage.server.system.util.OrgUnitRelationType;
import com.armitage.server.user.model.Usr;
import com.armitage.server.user.service.UsrBiz;
import org.springframework.stereotype.Service;

@Service("scmCstInitBillBiz")
public class ScmCstInitBillBizImpl extends BaseBizImpl<ScmCstInitBill2> implements ScmCstInitBillBiz {

    private UsrBiz usrBiz;
    private OrgUnitBiz orgUnitBiz;
    private ScmCstInitBillEntryBiz scmCstInitBillEntryBiz;
    private ScmInvStockBiz scmInvStockBiz;
	private ScmInvBalBiz scmInvBalBiz;
	private ScmMaterialBiz scmMaterialBiz;
	private PeriodCalendarBiz periodCalendarBiz;
	private SystemStateBiz systemStateBiz;
	private OrgUnitRelationBiz orgUnitRelationBiz;
	private OrgCompanyBiz orgCompanyBiz;
	private BillTypeBiz billTypeBiz;
	
	public void setScmMaterialBiz(ScmMaterialBiz scmMaterialBiz) {
		this.scmMaterialBiz = scmMaterialBiz;
	}

    public void setUsrBiz(UsrBiz usrBiz) {
        this.usrBiz = usrBiz;
    }

    public void setOrgUnitBiz(OrgUnitBiz orgUnitBiz) {
        this.orgUnitBiz = orgUnitBiz;
    }

    public void setScmCstInitBillEntryBiz(ScmCstInitBillEntryBiz scmCstInitBillEntryBiz) {
        this.scmCstInitBillEntryBiz = scmCstInitBillEntryBiz;
    }
    
    public void setScmInvStockBiz(ScmInvStockBiz scmInvStockBiz) {
		this.scmInvStockBiz = scmInvStockBiz;
	}
	
	public void setScmInvBalBiz(ScmInvBalBiz scmInvBalBiz) {
		this.scmInvBalBiz = scmInvBalBiz;
	}

    public void setPeriodCalendarBiz(PeriodCalendarBiz periodCalendarBiz) {
		this.periodCalendarBiz = periodCalendarBiz;
	}

	public void setSystemStateBiz(SystemStateBiz systemStateBiz) {
        this.systemStateBiz = systemStateBiz;
    }

    public void setOrgUnitRelationBiz(OrgUnitRelationBiz orgUnitRelationBiz) {
		this.orgUnitRelationBiz = orgUnitRelationBiz;
	}
    
    public void setOrgCompanyBiz(OrgCompanyBiz orgCompanyBiz) {
		this.orgCompanyBiz = orgCompanyBiz;
	}

	public void setBillTypeBiz(BillTypeBiz billTypeBiz) {
		this.billTypeBiz = billTypeBiz;
	}

	@Override
	protected void beforeFindPage(Page page, Param param) throws AppException {
		List<OrgCompany2> orgCompanyList = orgCompanyBiz.findChild(param.getOrgUnitNo(), param);
		if (orgCompanyList != null && !orgCompanyList.isEmpty()) {
			StringBuffer orgunitList = new StringBuffer("");
			for (OrgCompany2 orgCompany : orgCompanyList) {
				if(StringUtils.isNotBlank(orgunitList.toString()))
					orgunitList.append(",");
				orgunitList.append("'").append(orgCompany.getOrgUnitNo()).append("'");
			}
			page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmCstInitBill2.class) + "." + ScmCstInitBill2.FN_FINORGUNITNO), 
					new QueryParam((ClassUtils.getFinalModelSimpleName(ScmCstInitBill2.class) + "." + ScmCstInitBill2.FN_FINORGUNITNO), QueryParam.QUERY_IN, orgunitList.toString()));
		}else{
			page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmCstInitBill2.class) + "." + ScmCstInitBill2.FN_FINORGUNITNO), 
					new QueryParam((ClassUtils.getFinalModelSimpleName(ScmCstInitBill2.class) + "." + ScmCstInitBill2.FN_FINORGUNITNO), QueryParam.QUERY_EQ, "0"));
		}
	}

	@Override
    protected void afterSelect(CommonBean bean, Param param) throws AppException {

        ScmCstInitBill2 scmCstInitBill = (ScmCstInitBill2) bean.getList().get(0);
        if(scmCstInitBill != null && scmCstInitBill.getInitId() > 0){
            bean.setList2(scmCstInitBillEntryBiz.selectByInitId(scmCstInitBill.getInitId(), param));
        }
    
    }

    @Override
    protected void afterSelect(ScmCstInitBill2 entity, Param param) throws AppException {
        if (entity != null){
        	entity.setTaxInAmt(entity.getTaxAmt().subtract(entity.getAmt()));
        	setConvertMap(entity,param);
        }
    }
    
    @Override
	protected void beforeAdd(CommonBean bean, Param param) throws AppException {
    	ScmCstInitBill2 entity = (ScmCstInitBill2) bean.getList().get(0);
    	if(entity != null){
            // 创建单据编号
//            String date = StringUtils.replace(FormatUtils.fmtDate(entity.getCreateDate()), "-", "");
//            StringBuffer s = new StringBuffer("");
//            s.append("DI").append(date);
//			HashMap<String, Object> map = new HashMap<String, Object>();
//			map.put("createDate", FormatUtils.fmtDate(FormatUtils.parseDateTime(date, "yyyyMMdd")));
//			map.put("controlUnitNo", param.getControlUnitNo());
//            ScmCstInitBill2 scmCstInitBill= ((ScmCstInitBillDAO) dao).selectMaxIdByDate(map);
//            if(scmCstInitBill != null && StringUtils.isNotBlank(scmCstInitBill.getBillNo())
//                    && scmCstInitBill.getBillNo().contains(date)){
//                String str = StringUtils.substring(scmCstInitBill.getBillNo(), (scmCstInitBill.getBillNo().indexOf(date)+date.length()));
//                str = CodeAutoCalUtil.autoAddOne(str);
//                str = (s.append(str)).toString();
//                entity.setBillNo(str);
//            }else{
//                entity.setBillNo((s.append("0001").toString()));
//            }
    		String code = CodeAutoCalUtil.getBillCode("InvCstInitBill", entity, param);
			entity.setBillNo(code);
            List<OrgCostCenter2> orgCostCenterlist = orgUnitRelationBiz.findToOrgUnitByType(OrgUnitRelationType.ADMINTOCOST, entity.getOrgUnitNo(), false, null, param);
			if(orgCostCenterlist==null || orgCostCenterlist.isEmpty()) {
				throw new AppException(Message.getMessage(param.getLang(), "iscm.inventorymanage.ScmCstInitBill.importExcel.noadmintoCst"));
			}
			entity.setCostOrgUnitNo(orgCostCenterlist.get(0).getOrgUnitNo());
            //获取期间
			PeriodCalendar periodCalendar = periodCalendarBiz.selectByBizdate(entity.getBizDate(), param);
			if(periodCalendar==null){
				throw new AppException("com.armitage.server.iars.daily.selectByBizdate.error");
			}
			entity.setPeriodId(periodCalendar.getPeriodId());
			entity.setAccountYear(periodCalendar.getAccountYear());
			entity.setAccountPeriod(periodCalendar.getAccountPeriod());
//			//新增入库明细
//			List<ScmCstInitBillEntry2> scmCstInitBillEntryList = bean.getList2();
//	        BigDecimal amt= BigDecimal.ZERO;
//	        BigDecimal taxAmt= BigDecimal.ZERO;
//			if(scmCstInitBillEntryList != null && !scmCstInitBillEntryList.isEmpty()){
//				for(ScmCstInitBillEntry2 scmCstInitBillEntry : scmCstInitBillEntryList){
//	                amt = amt.add(scmCstInitBillEntry.getAmt());
//	                taxAmt = taxAmt.add(scmCstInitBillEntry.getTaxAmt());
//				}
//			}
//			entity.setAmt(amt);
//			entity.setTaxAmt(taxAmt);
        }
 	}

   

    @Override
    protected void afterAdd(CommonBean bean, Param param) throws AppException {
        ScmCstInitBill2 scmCstInitBill = (ScmCstInitBill2) bean.getList().get(0);
        if(scmCstInitBill != null && scmCstInitBill.getInitId() > 0){
            //新增入库明细
            List<ScmCstInitBillEntry2> scmCstInitBillEntryList = bean.getList2();
            if(scmCstInitBillEntryList != null && !scmCstInitBillEntryList.isEmpty()){
				int lineId = 1;
                for(ScmCstInitBillEntry2 scmCstInitBillEntry : scmCstInitBillEntryList){
                	scmCstInitBillEntry.setLineId(lineId);
                    scmCstInitBillEntry.setInitId(scmCstInitBill.getInitId());
                    scmCstInitBillEntry.setLot(scmCstInitBill.getBillNo());
					ScmMaterial2 scmMaterial = scmMaterialBiz.selectDirect(scmCstInitBillEntry.getItemId(), param);
					if(scmMaterial==null){
						throw new AppException("iscm.purchasemanage.purchasebusiness.ScmPurRequireBizImpl.error.itemnorexists");
					}
					scmCstInitBillEntry.setBaseUnit(scmMaterial.getBaseUnitId());
					BigDecimal invConvRate = ScmMaterialUtil.getConvRate(scmCstInitBillEntry.getItemId(), scmCstInitBillEntry.getUnit(), param);//库存单位转换系数
					scmCstInitBillEntry.setBaseQty(scmCstInitBillEntry.getQty().multiply(invConvRate));
                    scmCstInitBillEntryBiz.add(scmCstInitBillEntry, param);
					lineId = lineId+1;
                }
            }
        }
    }
    
	@Override
    protected void afterFindPage(List list, Page page, Param param) throws AppException {
        if(list != null && !list.isEmpty()){
            for(ScmCstInitBill2 scmCstInitBill : (List<ScmCstInitBill2>)list){
            	scmCstInitBill.setTaxInAmt(scmCstInitBill.getTaxAmt().subtract(scmCstInitBill.getAmt()));
            	setConvertMap(scmCstInitBill,param);
            }
        }
    
    }

	private void setConvertMap(ScmCstInitBill2 scmCstInitBill,Param param) {
		if(scmCstInitBill!=null) {
			if (StringUtils.isNotBlank(scmCstInitBill.getCreator())){
	            //制单人
	            Usr usr = usrBiz.selectByCode(scmCstInitBill.getCreator(), param);
	            if (usr != null) {
	                if(scmCstInitBill.getDataDisplayMap()==null){
	                    scmCstInitBill.setDataDisplayMap(new HashMap<String,SimpleDataDisplay>());
	                }
	                scmCstInitBill.getDataDisplayMap().put(ScmCstInitBill2.FN_CREATOR, SimpleDataDisplayUtil.convert(usr));
	                scmCstInitBill.setConvertMap(ScmCstInitBill2.FN_CREATOR, usr);
	            }
	        }
	        if (StringUtils.isNotBlank(scmCstInitBill.getCostOrgUnitNo())){
	            //成本中心
	            OrgBaseUnit orgBaseUnit = orgUnitBiz.selectbyOrgNo(scmCstInitBill.getCostOrgUnitNo(), param);
	            if (orgBaseUnit != null) {
	                scmCstInitBill.setConvertMap(ScmCstInitBill2.FN_COSTORGUNITNO, orgBaseUnit);
	            }
	        }
	        if (StringUtils.isNotBlank(scmCstInitBill.getOrgUnitNo())){
	            //部门
	            OrgBaseUnit orgBaseUnit = orgUnitBiz.selectbyOrgNo(scmCstInitBill.getOrgUnitNo(), param);
	            if (orgBaseUnit != null) {
	                scmCstInitBill.setConvertMap(ScmCstInitBill2.FN_ORGUNITNO, orgBaseUnit);
	            }
	        }
            if (StringUtils.isNotBlank(scmCstInitBill.getEditor())){
                //修改人
                Usr usr = usrBiz.selectByCode(scmCstInitBill.getEditor(), param);
                if (usr != null) {
                	scmCstInitBill.setConvertMap(ScmCstInitBill2.FN_EDITOR, usr);
                }
            }
            if (StringUtils.isNotBlank(scmCstInitBill.getChecker())){
                //审核人
                Usr usr = usrBiz.selectByCode(scmCstInitBill.getChecker(), param);
                if (usr != null) {
                	scmCstInitBill.setConvertMap(ScmCstInitBill2.FN_CHECKER, usr);
                }
            }
        }
	}
	
    @Override
    protected void afterUpdate(CommonBean bean, Param param) throws AppException {
        ScmCstInitBill2 scmCstInitBill = (ScmCstInitBill2) bean.getList().get(0);
        if(scmCstInitBill != null && scmCstInitBill.getInitId() > 0){
            //更新入库明细
            List<ScmCstInitBillEntry2> scmCstInitBillEntryList = bean.getList2();
            if(scmCstInitBillEntryList != null && !scmCstInitBillEntryList.isEmpty()){
				int lineId = 1;
                for(ScmCstInitBillEntry2 scmCstInitBillEntry : scmCstInitBillEntryList){
                	if (StringUtils.equals("I", scmCstInitBill.getStatus())) {
                		scmCstInitBillEntry.setLineId(lineId);
                	}
                    scmCstInitBillEntry.setInitId(scmCstInitBill.getInitId());
                    scmCstInitBillEntry.setLot(scmCstInitBill.getBillNo());
					ScmMaterial2 scmMaterial = scmMaterialBiz.selectDirect(scmCstInitBillEntry.getItemId(), param);
					if(scmMaterial==null){
						throw new AppException("iscm.purchasemanage.purchasebusiness.ScmPurRequireBizImpl.error.itemnorexists");
					}
					scmCstInitBillEntry.setBaseUnit(scmMaterial.getBaseUnitId());
					BigDecimal invConvRate = ScmMaterialUtil.getConvRate(scmCstInitBillEntry.getItemId(), scmCstInitBillEntry.getUnit(), param);//库存单位转换系数
					scmCstInitBillEntry.setBaseQty(scmCstInitBillEntry.getQty().multiply(invConvRate));
                    scmCstInitBillEntry.setInitId(scmCstInitBill.getInitId());
                    if(scmCstInitBillEntry.getId() == 0){
                        scmCstInitBillEntry.setLot(scmCstInitBill.getBillNo());
                    }
					lineId = lineId+1;
                }
                scmCstInitBillEntryBiz.update(scmCstInitBill, scmCstInitBillEntryList, ScmCstInitBillEntry2.FN_INITID, param);
            }
        }
    }

    @Override
    protected void afterDelete(ScmCstInitBill2 entity, Param param) throws AppException {
        if(entity != null && entity.getInitId() > 0){
            //删除入库明细
            scmCstInitBillEntryBiz.deleteByInitId(entity.getInitId(), param);
        }
    }

    public ScmCstInitBill2 updateStatus(ScmCstInitBill2 scmCstInitBill,Param param) throws AppException {
        if(scmCstInitBill != null){
        	return this.updateDirect(scmCstInitBill, param);
        }
        return null;
    }

	@Override
	public List<String> postBillCheck(ScmCstInitBill2 scmCstInitBill,
			Param param) throws AppException {
		//过账时检查库存组织对应的财务组织是否有维护物资的财务资料
		List<String> msgList = new ArrayList<String>();// 提示列表
		scmCstInitBill = this.selectDirect(scmCstInitBill.getInitId(), param);
        if(scmCstInitBill != null){
            if(!StringUtils.equals("A",scmCstInitBill.getStatus())) {
    			msgList.add(Message.getMessage(param.getLang(), "iscm.inventorymanage.post.error2", new String[] {scmCstInitBill.getBillNo()}));
    			return msgList;
    		}
		    SystemState systemState = systemStateBiz.selectBySystemId(scmCstInitBill.getFinOrgUnitNo(), 170, param);
            if(systemState==null){
                msgList.add(Message.getMessage(param.getLang(),"com.armitage.iars.daily.util.disenable.unable"));
                return msgList;
            }
            if (systemState.getCurrentPeriodId() != scmCstInitBill.getPeriodId()) {
                msgList.add(Message.getMessage(param.getLang(), 
                        "com.armitage.server.iscm.postBill.peroidId.error.notCurrent", new String[] {scmCstInitBill.getBillNo()}));
                return msgList;
            }
			List<ScmCstInitBillEntry2> scmCstInitBillEntryList = scmCstInitBillEntryBiz.selectByInitId(scmCstInitBill.getInitId(), param);
			if(scmCstInitBillEntryList != null && !scmCstInitBillEntryList.isEmpty()){
				HashMap<String,Object> cacheMap = new HashMap<String,Object>();
				for(ScmCstInitBillEntry2 scmCstInitBillEntry : scmCstInitBillEntryList){
					String orgunitName ="";
					if (StringUtils.isNotBlank(scmCstInitBill.getOrgUnitNo())){
						//组织
						OrgBaseUnit orgBaseUnit = (OrgBaseUnit) cacheMap.get(ClassUtils.getFinalModelSimpleName(OrgBaseUnit.class)+"_"+scmCstInitBill.getOrgUnitNo());
						if(orgBaseUnit==null){
							orgBaseUnit = orgUnitBiz.selectbyOrgNo(scmCstInitBill.getOrgUnitNo(), param);
							cacheMap.put(ClassUtils.getFinalModelSimpleName(OrgBaseUnit.class)+"_"+scmCstInitBill.getOrgUnitNo(),orgBaseUnit);
						}
						if (orgBaseUnit != null) {
							orgunitName = orgBaseUnit.getOrgUnitName();
						}
					}
					String[] msparam = {orgunitName,scmCstInitBillEntry.getItemName()};
					if(StringUtils.isBlank(scmCstInitBill.getFinOrgUnitNo())){
						msgList.add(Message.getMessage(param.getLang(),
								"iscm.inventorymanage.otherinwarehsbill.post.error", msparam));
						continue;
					}else{
						Page page = new Page();
						page.setModelClass(ScmMaterial2.class);
						page.setShowCount(Integer.MAX_VALUE);
						page.setSqlCondition("ScmMaterial.id="+scmCstInitBillEntry.getItemId());
						List<String> arglist = new ArrayList<>();
						arglist.add("controlUnitNo="+param.getControlUnitNo());
						arglist.add("orgUnitNo="+scmCstInitBill.getFinOrgUnitNo());
						List<ScmMaterial2> scmMaterialList = scmMaterialBiz.queryPage(page, arglist, "findByFinAllPage", param);
						if(scmMaterialList==null || scmMaterialList.isEmpty()){
							msgList.add(Message.getMessage(param.getLang(),
									"iscm.inventorymanage.otherinwarehsbill.post.error", msparam));
							continue;
						}
					}
				}
			}
		}
		return msgList;
	}

	@Override
	public ScmCstInitBill2 postBill(ScmCstInitBill2 scmCstInitBill, Param param) throws AppException {
		scmCstInitBill = this.selectDirect(scmCstInitBill.getInitId(), param);
		if(scmCstInitBill != null){
			if(!StringUtils.equals("A",scmCstInitBill.getStatus())) {
				throw new AppException(Message.getMessage(param.getLang(), "iscm.inventorymanage.post.error2", new String[] {scmCstInitBill.getBillNo()}));
    		}
			//即时库存
			scmInvStockBiz.updateByCstInitBill(scmCstInitBill.getInitId(), param);
			scmInvStockBiz.addByCstInitBill(scmCstInitBill.getInitId(), param);
			//期间余额
			scmInvBalBiz.updateByCstInitBill(scmCstInitBill.getInitId(), param);
			scmInvBalBiz.addByCstInitBill(scmCstInitBill.getInitId(), param);
			//移动平均即时成本
			if (!(ParamType.Operation.equals(param.getParamType()) && ((OperationParam) param).getOperationId()==629223199L)) {
				scmCstInitBill.setCheckDate(CalendarUtil.getDate(param));
				scmCstInitBill.setChecker(param.getUsrCode());
				scmCstInitBill.setPostDate(CalendarUtil.getDate(param));
			}
			scmCstInitBill.setStatus("E");
			int updateRow = this.updatePostedStatus(scmCstInitBill, param);
			if(updateRow!=1) {
				throw new AppException(Message.getMessage(param.getLang(), "iscm.inventorymanage.post.error2", new String[] {scmCstInitBill.getBillNo()}));
			}
		}
		return scmCstInitBill;
	}

	private int updatePostedStatus(ScmCstInitBill2 scmCstInitBill, Param param) {
		HashMap<String,Object> map = new HashMap<String,Object> ();
		map.put("initId",scmCstInitBill.getInitId());
		map.put("checker",scmCstInitBill.getChecker());
		map.put("checkDate",scmCstInitBill.getCheckDate()==null?null:FormatUtils.fmtDate(scmCstInitBill.getCheckDate()));
		map.put("status", scmCstInitBill.getStatus());
		map.put("postDate", scmCstInitBill.getPostDate()==null?null:FormatUtils.fmtDateTime(scmCstInitBill.getPostDate()));
		return ((ScmCstInitBillDAO)dao).updatePostedStatus(map);
	}

	@Override
	public List<String> cancelPostBillCheck(ScmCstInitBill2 scmCstInitBill,
			Param param) throws AppException {
		List<String> msgList = new ArrayList<String>();// 提示列表
		scmCstInitBill = this.selectDirect(scmCstInitBill.getInitId(), param);
        if(scmCstInitBill != null){
            if(!StringUtils.equals("E",scmCstInitBill.getStatus())) {
    			msgList.add(Message.getMessage(param.getLang(), "iscm.inventorymanage.cancelPost.error2", new String[] {scmCstInitBill.getBillNo()}));
    			return msgList;
    		}
            SystemState systemState = systemStateBiz.selectBySystemId(scmCstInitBill.getFinOrgUnitNo(), 170, param);
            if(systemState==null){
                msgList.add(Message.getMessage(param.getLang(),"com.armitage.iars.daily.util.disenable.unable"));
                return msgList;
            }
            if (systemState.getCurrentPeriodId() != scmCstInitBill.getPeriodId()) {
                msgList.add(Message.getMessage(param.getLang(), 
                        "com.armitage.server.iscm.postBill.peroidId.error.notCurrent", new String[] {scmCstInitBill.getBillNo()}));
                return msgList;
            }
            
			List<ScmCstInitBillEntry2> list = scmCstInitBillEntryBiz.selectInvQty(scmCstInitBill.getInitId(), param);
	        
	        if (list != null && list.size() > 0) {
	            for (int i = 0; i < list.size(); i++) {
	                if (StringUtils.isNotBlank(list.get(i).getLot())) {
	                    msgList.add(Message.getMessage(param.getLang(), 
	                            "com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.service.impl.count", 
	                            new String[] {list.get(i).getItemNo(), list.get(i).getItemName(), list.get(i).getLot(),
	                                    list.get(i).getQty().toString(), list.get(i).getInvQty().toString()}));
	                } else {
	                    msgList.add(Message.getMessage(param.getLang(), 
	                            "com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.service.impl.count2", 
	                            new String[] {list.get(i).getItemNo(), list.get(i).getItemName(), 
	                                    list.get(i).getQty().toString(), list.get(i).getInvQty().toString()}));
	                }
	            }
	        }
        }
        return msgList;
	}

	@Override
	public ScmCstInitBill2 cancelPostBill(ScmCstInitBill2 scmCstInitBill,
			Param param) throws AppException {
		scmCstInitBill = this.selectDirect(scmCstInitBill.getInitId(), param);
        if(scmCstInitBill != null){
            if(!StringUtils.equals("E",scmCstInitBill.getStatus())) {
            	throw new AppException(Message.getMessage(param.getLang(), "iscm.inventorymanage.cancelPost.error2", new String[] {scmCstInitBill.getBillNo()}));
    		}
			//即时库存
            List<ScmCstInitBillEntry2> scmCstInitBillEntryList = scmCstInitBillEntryBiz.selectCancelPostEffectRow(scmCstInitBill.getInitId(), param);
			int updateRow = scmInvStockBiz.updateByCancelCstInitBill(scmCstInitBill.getInitId(), param);
			if(updateRow<scmCstInitBillEntryList.size()){
    			throw new AppException("iscm.inventorymanage.common.post.error.rowsnotequal");
    		}
			//期间余额
			scmInvBalBiz.updateByCancelCstInitBill(scmCstInitBill.getInitId(), param);
			//移动平均即时成本
			scmCstInitBill.setCheckDate(null);
			scmCstInitBill.setChecker("");
			scmCstInitBill.setStatus("A");
			scmCstInitBill.setPostDate(null);
			updateRow = this.updatePostedStatus(scmCstInitBill, param);
			if(updateRow!=1) {
				throw new AppException(Message.getMessage(param.getLang(), "iscm.inventorymanage.cancelPost.error2", new String[] {scmCstInitBill.getBillNo()}));
			}
		}
		return scmCstInitBill;
	}

	@Override
	protected void beforeDelete(ScmCstInitBill2 entity, Param param) throws AppException {
		ScmCstInitBill2 scmCstInitBill = this.selectDirect(entity.getInitId(), param);
		if(scmCstInitBill!=null && !StringUtils.equals(scmCstInitBill.getStatus(),"I")){
			throw new AppException(Message.getMessage(param.getLang(), "iscm.inventorymanage.delete.error", new String[]{entity.getBillNo()}));
		}
	}
    
	@Override
	protected void beforeUpdate(CommonBean bean, Param param) throws AppException {
		ScmCstInitBill2 entity = (ScmCstInitBill2) bean.getList().get(0);
		if(entity!=null){
			ScmCstInitBill2 scmCstInitBill = this.selectDirect(entity.getPK(), param);
			if(!StringUtils.equals(scmCstInitBill.getStatus(),"I")){
				throw new AppException(Message.getMessage(param.getLang(), "iscm.commom.edit.error.notnewstatus"));
			}
			//获取期间
			PeriodCalendar periodCalendar = periodCalendarBiz.selectByBizdate(entity.getBizDate(), param);
			if(periodCalendar==null){
				throw new AppException("com.armitage.server.iars.daily.selectByBizdate.error");
			}
			entity.setPeriodId(periodCalendar.getPeriodId());
			entity.setAccountYear(periodCalendar.getAccountYear());
			entity.setAccountPeriod(periodCalendar.getAccountPeriod());
		}
	}

	@Override
	public ScmCstInitBill2 importExcel(ScmCstInitBillImPort scmCstInitBillImPort, Param param) throws AppException {
		if(scmCstInitBillImPort!=null) {
			SystemState systemState = systemStateBiz.selectBySystemId(param.getOrgUnitNo(), 170, param);
            if(systemState==null){
            	throw new AppException(Message.getMessage(param.getLang(), "com.armitage.iars.daily.util.disenable.unable"));
            }
            PeriodCalendar periodCalendar = periodCalendarBiz.selectDirect(systemState.getCurrentPeriodId(), param);
			List<OrgCostCenter2> orgCostCenterlist = orgUnitRelationBiz.findToOrgUnitByType(OrgUnitRelationType.ADMINTOCOST, scmCstInitBillImPort.getOrgUnitNo(), false, null, param);
			if(orgCostCenterlist==null || orgCostCenterlist.isEmpty()) {
				throw new AppException(Message.getMessage(param.getLang(), "iscm.inventorymanage.ScmCstInitBill.importExcel.noadmintoCst"));
			}
			List<OrgStorage2> orgStoragelist = orgUnitRelationBiz.findToOrgUnitByType(OrgUnitRelationType.ADMINTOINV, scmCstInitBillImPort.getOrgUnitNo(), false, null, param);
			if(orgCostCenterlist==null || orgCostCenterlist.isEmpty()) {
				throw new AppException(Message.getMessage(param.getLang(), "iscm.inventorymanage.ScmCstInitBill.importExcel.notadmintoinv"));
			}
			CommonBean bean = new CommonBean();
			List<ScmCstInitBill2> scmCstInitBillList = new ArrayList();
			ScmCstInitBill2 scmCstInitBill = new ScmCstInitBill2(true);
			scmCstInitBill.setBizDate(periodCalendar.getStartDate());
			scmCstInitBill.setOrgUnitNo(scmCstInitBillImPort.getOrgUnitNo());
			scmCstInitBill.setCostOrgUnitNo(orgCostCenterlist.get(0).getOrgUnitNo());
			scmCstInitBill.setFinOrgUnitNo(param.getOrgUnitNo());
			scmCstInitBill.setCreator(param.getUsrCode());
			scmCstInitBill.setCreateDate(CalendarUtil.getDate(param));
			scmCstInitBill.setStatus("I");
			scmCstInitBill.setControlUnitNo(param.getControlUnitNo());
			scmCstInitBillList.add(scmCstInitBill);
			bean.setList(scmCstInitBillList);
			StringBuffer itemNos = new StringBuffer("");
			List<ScmCstInitBillEntry2> scmCstInitBillEntryList = new ArrayList<ScmCstInitBillEntry2>();
			List<ScmCstInitBillImPortDetail> scmCstInitBillImPortDetailList=scmCstInitBillImPort.getDetailList();
				
			for(ScmCstInitBillImPortDetail scmCstInitBillImPortDetail : scmCstInitBillImPortDetailList) {
				if(StringUtils.isBlank(scmCstInitBillImPortDetail.getItemNo())){
					throw new AppException("iscm.purchasemanage.purchasebusiness.ScmPurRequireBizImpl.error.itemisnull");
				}
				if(StringUtils.isNotBlank(itemNos.toString()))
					itemNos.append(",");
				itemNos.append("'").append(scmCstInitBillImPortDetail.getItemNo()).append("'");
			}
				
			Page page = new Page();
			page.setModelClass(ScmMaterial2.class);
			page.setShowCount(Integer.MAX_VALUE);
			page.setSqlCondition("ScmMaterial.itemNo in ("+itemNos.toString()+")");
			ArrayList argList = new ArrayList();
	        argList.add("orgUnitNo="+orgStoragelist.get(0).getOrgUnitNo());
	        argList.add("controlUnitNo=" + param.getControlUnitNo());
	        List<ScmMaterial2> scmMaterialList = scmMaterialBiz.queryPage(page, argList, "findByInvAllPage", param);
				
			for(ScmCstInitBillImPortDetail scmCstInitBillImPortDetail : scmCstInitBillImPortDetailList) {
				ScmCstInitBillEntry2 scmCstInitBillEntry = new ScmCstInitBillEntry2(true);
				BeanUtils.copyProperties(scmCstInitBillImPortDetail,scmCstInitBillEntry);
				for(ScmMaterial2 scmMaterial:scmMaterialList){
					if(StringUtils.equals(scmMaterial.getItemNo(), scmCstInitBillImPortDetail.getItemNo())){
						scmCstInitBillEntry.setItemId(scmMaterial.getId());
						scmCstInitBillEntry.setUnit(scmMaterial.getUnitId());
						break;
					}
				}
				scmCstInitBillEntry.setPrice(scmCstInitBillEntry.getTaxPrice());
				scmCstInitBillEntry.setAmt(scmCstInitBillEntry.getTaxAmt());
				scmCstInitBillEntry.setTaxRate(BigDecimal.ZERO);
				scmCstInitBillEntryList.add(scmCstInitBillEntry);

			}
			bean.setList2(scmCstInitBillEntryList);
			bean = this.add(bean, param);
			return (ScmCstInitBill2) bean.getList().get(0);
		}
		return null;
	}

	@Override
	public ScmCstInitBill2 submit(ScmCstInitBill2 scmCstInitBill, Param param) throws AppException {
		scmCstInitBill = this.selectDirect(scmCstInitBill.getInitId(), param);
		if(scmCstInitBill!=null){
			if(!scmCstInitBill.getStatus().equals("I")){
				throw new AppException("iscm.inventorymanage.error.commit");
			}else if(scmCstInitBill.getStatus().equals("I")){
				if(param.getUsrCode()!=null ){
					scmCstInitBill.setChecker(param.getUsrCode());
					scmCstInitBill.setSubmitter(param.getUsrCode());
				}
				scmCstInitBill.setCheckDate(CalendarUtil.getDate(param));
				scmCstInitBill.setSubmitDate(CalendarUtil.getDate(param));
				scmCstInitBill.setStatus("A");
				try{
					this.updateStatus(scmCstInitBill, param);
				}catch(AppException e){
					throw e;
				}
				SystemState systemState = systemStateBiz.selectBySystemId(scmCstInitBill.getFinOrgUnitNo(), 170, param);
				if(systemState==null){
					throw new AppException("com.armitage.iars.daily.util.disenable.unable");
				}
				if (systemState.getCurrentPeriodId() == scmCstInitBill.getPeriodId()) {
				if(StringUtils.equals("A", scmCstInitBill.getStatus())) {
					//通过或部分通过时检查是否自动过帐
					BillType2 billType = billTypeBiz.selectByOrgAndCode(scmCstInitBill.getFinOrgUnitNo(), "InvCstInitBill", param);
					if(billType!=null && billType.isAutoRelease()) {
						List<String> msgList = this.postBillCheck(scmCstInitBill, param);
						if (msgList != null && !msgList.isEmpty()) {
							StringBuilder detailInfo = new StringBuilder("");
	                        detailInfo.append(Message.getMessage(param.getLang(),"iscm.inventorymanage.scmcstinitbill.post.errorTitle"));
	
							for (String str : msgList) {
	                            detailInfo.append(str).append("\n");
	                        }
							
							throw new AppException(detailInfo.toString(), new String[]{scmCstInitBill.getBillNo()});
						}
						this.postBill(scmCstInitBill, param);
					}
				}
			}
			}
		}else{
			throw new AppException("iscm.inventorymanage.warehouseoutbusiness.find.ordernotexists");
		}
		return scmCstInitBill;
	}

	@Override
	public ScmCstInitBill2 undoSubmit(ScmCstInitBill2 scmCstInitBill, Param param) throws AppException {
		scmCstInitBill = this.selectDirect(scmCstInitBill.getInitId(), param);
		if(scmCstInitBill!=null){
			if(!scmCstInitBill.getStatus().equals("A")){
				throw new AppException("iscm.inventorymanage.error.cancelCommit");
			}else if(scmCstInitBill.getStatus().equals("A")){
				scmCstInitBill.setChecker(null);
				scmCstInitBill.setCheckDate(null);
				scmCstInitBill.setStatus("I");
				try{
					this.updateStatus(scmCstInitBill, param);
				}catch(AppException e){
					throw e;
				}
			}
		}else{
			throw new AppException("iscm.inventorymanage.warehouseoutbusiness.find.ordernotexists");
		}
		return scmCstInitBill;
	}

	@Override
	public void doAdvQuery(Page page, Param param) {
		// TODO Auto-generated method stub
		if (page.getModel() != null) {
			if (page.getModel() instanceof ScmCstInitBillQuery) {
				ScmCstInitBillQuery scmCstInitBillQuery = (ScmCstInitBillQuery) page.getModel();
				if(scmCstInitBillQuery.getBizDateFrom()!=null){
					if(scmCstInitBillQuery.getBizDateTo()!=null) {
						page.getParam().put(ScmCstInitBill2.FN_BIZDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmCstInitBill2.class) + "." +ScmCstInitBill2.FN_BIZDATE, QueryParam.QUERY_BETWEEN,FormatUtils.fmtDate(scmCstInitBillQuery.getBizDateFrom()),FormatUtils.fmtDate(scmCstInitBillQuery.getBizDateTo())));
					}else {
						page.getParam().put(ScmCstInitBill2.FN_BIZDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmCstInitBill2.class) + "." +ScmCstInitBill2.FN_BIZDATE, QueryParam.QUERY_GE,FormatUtils.fmtDate(scmCstInitBillQuery.getBizDateFrom())));
					}
				}else if(scmCstInitBillQuery.getBizDateTo()!=null) {
					page.getParam().put(ScmCstInitBill2.FN_BIZDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmCstInitBill2.class) + "." +ScmCstInitBill2.FN_BIZDATE, QueryParam.QUERY_LE,FormatUtils.fmtDate(scmCstInitBillQuery.getBizDateTo())));
				}
				if(scmCstInitBillQuery.getCreateDateFrom()!=null){
					if(scmCstInitBillQuery.getCreateDateTo()!=null) {
						page.getParam().put(ScmCstInitBill2.FN_CREATEDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmCstInitBill2.class) + "." +ScmCstInitBill2.FN_CREATEDATE, QueryParam.QUERY_BETWEEN,FormatUtils.fmtDate(scmCstInitBillQuery.getCreateDateFrom()),FormatUtils.fmtDate(CalendarUtil.relativeDate(scmCstInitBillQuery.getCreateDateTo(),1))));
					}else {
						page.getParam().put(ScmCstInitBill2.FN_CREATEDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmCstInitBill2.class) + "." +ScmCstInitBill2.FN_CREATEDATE, QueryParam.QUERY_GE,FormatUtils.fmtDate(scmCstInitBillQuery.getCreateDateFrom())));
					}
				}else if(scmCstInitBillQuery.getCreateDateTo()!=null) {
					page.getParam().put(ScmCstInitBill2.FN_CREATEDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmCstInitBill2.class) + "." +ScmCstInitBill2.FN_CREATEDATE, QueryParam.QUERY_LE,FormatUtils.fmtDate(CalendarUtil.relativeDate(scmCstInitBillQuery.getCreateDateTo(),1))));
				}
				if(StringUtils.isNotBlank(scmCstInitBillQuery.getOrgUnitNo())) {
					page.getParam().put(ScmCstInitBill2.FN_ORGUNITNO,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmCstInitBill2.class) + "." +ScmCstInitBill2.FN_ORGUNITNO, QueryParam.QUERY_EQ,String.valueOf(scmCstInitBillQuery.getOrgUnitNo())));
				}
			}
		}
	}

	@Override
	public ScmCstInitBill2 updatePrtCount(ScmCstInitBill2 scmCstInitBill, Param param) throws AppException {
		if(scmCstInitBill.getInitId()>0){
			ScmCstInitBill2 bill = this.selectDirect(scmCstInitBill.getInitId(), param);
			if(bill != null){
				int prtCount = bill.getPrtcount();
				bill.setPrtcount((prtCount+1));
				this.updateDirect(bill, param);
				return bill;
			}
		}
		return scmCstInitBill;
	}

	@Override
	public List<Map<String, Object>> countUnPostBill(HashMap<String, Object> map) throws AppException {
		return ((ScmCstInitBillDAO) dao).countUnPostBill(map);
	}
	

}