package com.armitage.server.iscm.purchasemanage.purchasebusiness.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

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
import com.armitage.server.iscm.basedata.model.Scmsupplier2;
import com.armitage.server.iscm.basedata.model.Scmsuppliergroup;
import com.armitage.server.iscm.basedata.service.ScmsupplierBiz;
import com.armitage.server.iscm.basedata.util.ScmMaterialUtil;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.dao.ScmPurCheckDAO;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurCheck2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurCheckAdvQuery;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurOrder2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurReceive2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurReceiveEntry2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.service.ScmPurCheckBiz;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.service.ScmPurReceiveBiz;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.service.ScmPurReceiveEntryBiz;
import com.armitage.server.system.model.OrgPurchase2;
import com.armitage.server.system.model.OrgStorage2;
import com.armitage.server.system.service.OrgPurchaseBiz;
import com.armitage.server.system.service.OrgStorageBiz;
import com.armitage.server.system.util.CodeAutoCalUtil;
import com.armitage.server.user.model.Usr;
import com.armitage.server.user.service.UsrBiz;
import org.springframework.stereotype.Service;

@Service("scmPurCheckBiz")
public class ScmPurCheckBizImpl extends BaseBizImpl<ScmPurCheck2> implements ScmPurCheckBiz {
	private UsrBiz usrBiz;
	private ScmPurReceiveEntryBiz scmPurReceiveEntryBiz;
	private ScmsupplierBiz scmsupplierBiz;
	private OrgStorageBiz orgStorageBiz;
	private ScmPurReceiveBiz scmPurReceiveBiz;
	private OrgPurchaseBiz orgPurchaseBiz;
	
	public void setUsrBiz(UsrBiz usrBiz) {
		this.usrBiz = usrBiz;
	}

	public void setScmPurReceiveEntryBiz(ScmPurReceiveEntryBiz scmPurReceiveEntryBiz) {
		this.scmPurReceiveEntryBiz = scmPurReceiveEntryBiz;
	}

	public void setScmPurReceiveBiz(ScmPurReceiveBiz scmPurReceiveBiz) {
		this.scmPurReceiveBiz = scmPurReceiveBiz;
	}
	
	public void setOrgPurchaseBiz(OrgPurchaseBiz orgPurchaseBiz) {
		this.orgPurchaseBiz = orgPurchaseBiz;
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
			page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmPurCheck2.class) + "." + ScmPurCheck2.FN_ORGUNITNO), 
					new QueryParam((ClassUtils.getFinalModelSimpleName(ScmPurCheck2.class) + "." + ScmPurCheck2.FN_ORGUNITNO), QueryParam.QUERY_IN, orgunitList.toString()));
		}else{
			page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmPurCheck2.class) + "." + ScmPurCheck2.FN_ORGUNITNO), 
					new QueryParam((ClassUtils.getFinalModelSimpleName(ScmPurCheck2.class) + "." + ScmPurCheck2.FN_ORGUNITNO), QueryParam.QUERY_EQ, "0"));
		}
	}

	@Override
	protected void beforeAdd(ScmPurCheck2 entity, Param param) throws AppException {
		if(entity != null){
//			String date = StringUtils.replace(FormatUtils.fmtDate(entity.getCreateDate()), "-", "");
//			StringBuffer s = new StringBuffer("");
//			s.append("CK").append(date);
//			HashMap<String, Object> map = new HashMap<String, Object>();
//			map.put("createDate", FormatUtils.fmtDate(entity.getCreateDate()));
//			map.put("controlUnitNo", param.getControlUnitNo());
//			ScmPurCheck2 scmPurCheck= ((ScmPurCheckDAO) dao).selectMaxIdByDate(map);
//			if(scmPurCheck != null && StringUtils.isNotBlank(scmPurCheck.getCkNo())
//					&& scmPurCheck.getCkNo().contains(date)){
//				String str = StringUtils.substring(scmPurCheck.getCkNo(), (scmPurCheck.getCkNo().indexOf(date)+date.length()));
//				str = CodeAutoCalUtil.autoAddOne(str);
//				str = (s.append(str)).toString();
//				entity.setCkNo(str);
//			}else{
//				entity.setCkNo((s.append("001").toString()));
//			}
			String code = CodeAutoCalUtil.getBillCode("PurCheck", entity, param);
			entity.setCkNo(code);
		}
	}

	@Override
	protected void beforeDelete(ScmPurCheck2 entity, Param param) throws AppException {
		ScmPurCheck2 scmPurCheck = this.selectDirect(entity.getId(), param);
		if(scmPurCheck!=null && scmPurCheck.isFlag()){
			throw new AppException(Message.getMessage(param.getLang(), "iscm.inventorymanage.delete.error", new String[]{entity.getCkNo()}));
		}
	}
	public void setScmsupplierBiz(ScmsupplierBiz scmsupplierBiz) {
		this.scmsupplierBiz = scmsupplierBiz;
	}

	public void setOrgStorageBiz(OrgStorageBiz orgStorageBiz) {
		this.orgStorageBiz = orgStorageBiz;
	}

	@Override
	protected void afterSelect(CommonBean bean, Param param) throws AppException {
		ScmPurCheck2 scmPurCheck = (ScmPurCheck2) bean.getList().get(0);
		if(scmPurCheck != null && scmPurCheck.getId()>0){
			bean.setList2(scmPurReceiveEntryBiz.selectByCkId(scmPurCheck.getId(), param));
		}
	}

	@Override
	protected void afterSelect(ScmPurCheck2 entity, Param param)	throws AppException {
		if (entity!=null) {
			setConvertMap(entity,param);
		}
	}
	
	private void setConvertMap(ScmPurCheck2 scmPurCheck, Param param) throws AppException {
		if(scmPurCheck!=null){
			if(StringUtils.isNotBlank(scmPurCheck.getCreator())){
				//制单人
				Usr usr = usrBiz.selectByCode(scmPurCheck.getCreator(), param);
				if (usr != null) {
					scmPurCheck.setConvertMap(ScmPurCheck2.FN_CREATOR, usr);
				}
			}
			if(scmPurCheck.getVendorId()>0){
				Scmsupplier2 scmsupplier = scmsupplierBiz.selectDirect(scmPurCheck.getVendorId(), param);
				if(scmsupplier!=null){
					scmPurCheck.setVendorName(scmsupplier.getVendorName());
					scmPurCheck.setConvertMap(ScmPurCheck2.FN_VENDORID, scmsupplier);
				}
			}
			if(StringUtils.isNotBlank(scmPurCheck.getInvOrgUnitNo())){
				OrgStorage2 orgStorage = orgStorageBiz.selectByOrgUnitNo(scmPurCheck.getInvOrgUnitNo(), param);
				if(orgStorage!=null)
					scmPurCheck.setConvertMap(ScmPurCheck2.FN_INVORGUNITNO,orgStorage);
			}
		}
	}

	@Override
	protected void afterUpdate(CommonBean bean, Param param) throws AppException {
		List<ScmPurReceiveEntry2> scmPurReceiveEntryCkList = bean.getList2();
		ScmPurCheck2 scmPurCheck = (ScmPurCheck2) bean.getList().get(0);
		if(scmPurCheck!=null && scmPurReceiveEntryCkList!= null && !scmPurReceiveEntryCkList.isEmpty()){
			ScmPurReceive2 scmPurReceive = scmPurReceiveBiz.selectByCkId(scmPurCheck.getId(),param);
			if(scmPurReceive!=null && scmPurReceive.getReceiveDate().compareTo(scmPurCheck.getCheckDate())!=0) {
				scmPurReceive.setRemarks(scmPurCheck.getRemarks());
				scmPurReceive.setReceiveDate(scmPurCheck.getCheckDate());
				scmPurReceiveBiz.updateDirect(scmPurReceive, param);
			}
			List<ScmPurReceiveEntry2> scmPurReceiveEntryList = scmPurReceiveEntryBiz.selectByCkId(scmPurCheck.getId(), param);
			if(scmPurReceiveEntryList!=null && !scmPurReceiveEntryList.isEmpty()){
				for(ScmPurReceiveEntry2 scmPurReceiveEntry:scmPurReceiveEntryList){
					for(ScmPurReceiveEntry2 scmPurReceiveEntryCk:scmPurReceiveEntryCkList){
						if(scmPurReceiveEntry.getId()==scmPurReceiveEntryCk.getId()){
							BigDecimal purConvRate = ScmMaterialUtil.getConvRate(scmPurReceiveEntry.getItemId(), scmPurReceiveEntry.getPurUnit(), param);//采购单位转换系数
							BigDecimal invConvRate = ScmMaterialUtil.getConvRate(scmPurReceiveEntry.getItemId(), scmPurReceiveEntry.getUnit(), param);//库存单位转换系数
							scmPurReceiveEntry.setDeliveryQty(scmPurReceiveEntryCk.getDeliveryQty());
							scmPurReceiveEntry.setInvQty(scmPurReceiveEntry.getDeliveryQty().multiply(purConvRate).divide(invConvRate,4,BigDecimal.ROUND_HALF_UP));	//入库数量
							scmPurReceiveEntry.setBaseQty(scmPurReceiveEntry.getInvQty().multiply(invConvRate));
							scmPurReceiveEntry.setTaxRate(scmPurReceiveEntryCk.getTaxRate());
							scmPurReceiveEntry.setPrice(scmPurReceiveEntryCk.getPrice());
							scmPurReceiveEntry.setTaxPrice(scmPurReceiveEntryCk.getTaxPrice());
							scmPurReceiveEntry.setQty(scmPurReceiveEntryCk.getQty());
							scmPurReceiveEntry.setAmt(scmPurReceiveEntryCk.getAmt());
							scmPurReceiveEntry.setCheckAmt(scmPurReceiveEntryCk.getCheckAmt());
							scmPurReceiveEntry.setTaxAmt(scmPurReceiveEntryCk.getTaxAmt());
							scmPurReceiveEntry.setCheckTaxAmt(scmPurReceiveEntryCk.getCheckTaxAmt());
							scmPurReceiveEntry.setRemarks(scmPurReceiveEntryCk.getRemarks());
							scmPurReceiveEntryCkList.remove(scmPurReceiveEntryCk);
							scmPurReceiveEntryBiz.update(scmPurReceiveEntry, param);
							break;
						}
					}
				}
			}
			if(scmPurReceive != null){
	    		List<ScmPurReceiveEntry2> scmPurReceiveEntryList2 = scmPurReceiveEntryBiz.selectByPvId(scmPurReceive.getId(), param);
	        	if(scmPurReceiveEntryList2 != null && !scmPurReceiveEntryList2.isEmpty()){
	        		BigDecimal amt = BigDecimal.ZERO;
	        		BigDecimal taxAmt = BigDecimal.ZERO;
	        		for(ScmPurReceiveEntry2 scmPurReceiveEntry2 : scmPurReceiveEntryList2){
	        			amt = amt.add(scmPurReceiveEntry2.getAmt());
	        			taxAmt = taxAmt.add(scmPurReceiveEntry2.getTaxAmt());
	        		}
	        		if((scmPurReceive.getAmt() != null && amt.compareTo(scmPurReceive.getAmt()) != 0)
	        				|| (scmPurReceive.getTaxAmt() != null && taxAmt.compareTo(scmPurReceive.getTaxAmt()) != 0)){
	        			scmPurReceive.setAmt(amt);
	            		scmPurReceive.setTaxAmt(taxAmt);
	            		scmPurReceiveBiz.updateDirect(scmPurReceive, param);
	        		}
	        	}
	    	}
		}
	}

	@Override
	public void confirm(List<ScmPurCheck2> scmPurCheckList, Param param) throws AppException {
		if(scmPurCheckList!=null && !scmPurCheckList.isEmpty()) {
			for(ScmPurCheck2 scmPurCheck:scmPurCheckList) {
				scmPurCheck = (ScmPurCheck2) this.select(scmPurCheck, param);
				scmPurCheck.setFlag(true);
				this.update(scmPurCheck, param);
			}
		}
	}

	@Override
	protected void afterFindPage(List list, Page page, Param param)
			throws AppException {
		if(list!=null && !list.isEmpty()){
			for(ScmPurCheck2 scmPurCheck:(List<ScmPurCheck2>)list){
				setConvertMap(scmPurCheck,param);
			}
		}
	}

	@Override
	public void doAdvQuery(Page page, Param param) {
		if (page.getModel() != null) {
			if (page.getModel() instanceof ScmPurCheckAdvQuery) {
				ScmPurCheckAdvQuery scmPurCheckAdvQuery = (ScmPurCheckAdvQuery) page.getModel();
				if(StringUtils.isNotBlank(scmPurCheckAdvQuery.getCkNo())){
					page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmPurCheck2.class)+"."+ScmPurCheck2.FN_CKNO,
							new QueryParam(ClassUtils.getFinalModelSimpleName(ScmPurCheck2.class)+"."+ScmPurCheck2.FN_CKNO, QueryParam.QUERY_LIKE,"%"+scmPurCheckAdvQuery.getCkNo()+"%"));
				}
				if(scmPurCheckAdvQuery.getCheckDateFrom()!=null){
					if(scmPurCheckAdvQuery.getCheckDateTo()!=null) {
						page.getParam().put(ScmPurCheck2.FN_CHECKDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmPurCheck2.class) + "." +ScmPurCheck2.FN_CHECKDATE, QueryParam.QUERY_BETWEEN,FormatUtils.fmtDate(scmPurCheckAdvQuery.getCheckDateFrom()),FormatUtils.fmtDate(scmPurCheckAdvQuery.getCheckDateTo())));
					}else {
						page.getParam().put(ScmPurCheck2.FN_CHECKDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmPurCheck2.class) + "." +ScmPurCheck2.FN_CHECKDATE, QueryParam.QUERY_GE,FormatUtils.fmtDate(scmPurCheckAdvQuery.getCheckDateFrom())));
					}
				}else if(scmPurCheckAdvQuery.getCheckDateTo()!=null) {
					page.getParam().put(ScmPurCheck2.FN_CHECKDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmPurCheck2.class) + "." +ScmPurCheck2.FN_CHECKDATE, QueryParam.QUERY_LE,FormatUtils.fmtDate(scmPurCheckAdvQuery.getCheckDateTo())));
				}
				if(scmPurCheckAdvQuery.getCreateDateFrom()!=null){
					if(scmPurCheckAdvQuery.getCreateDateTo()!=null) {
						page.getParam().put(ScmPurCheck2.FN_CREATEDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmPurCheck2.class) + "." +ScmPurCheck2.FN_CREATEDATE, QueryParam.QUERY_BETWEEN,FormatUtils.fmtDate(scmPurCheckAdvQuery.getCreateDateFrom()),FormatUtils.fmtDate(CalendarUtil.relativeDate(scmPurCheckAdvQuery.getCreateDateTo(),1))));
					}else {
						page.getParam().put(ScmPurCheck2.FN_CREATEDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmPurCheck2.class) + "." +ScmPurCheck2.FN_CREATEDATE, QueryParam.QUERY_GE,FormatUtils.fmtDate(CalendarUtil.relativeDate(scmPurCheckAdvQuery.getCreateDateFrom(),1))));
					}
				}else if(scmPurCheckAdvQuery.getCreateDateTo()!=null) {
					page.getParam().put(ScmPurCheck2.FN_CREATEDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmPurCheck2.class) + "." +ScmPurCheck2.FN_CREATEDATE, QueryParam.QUERY_LE,FormatUtils.fmtDate(scmPurCheckAdvQuery.getCreateDateTo())));
				}
				if(StringUtils.isNotBlank(scmPurCheckAdvQuery.getInvOrgUnitNo())){
					page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmPurReceive2.class)+"."+ScmPurReceive2.FN_ORGUNITNO,
							new QueryParam(ClassUtils.getFinalModelSimpleName(ScmPurReceive2.class)+"."+ScmPurReceive2.FN_ORGUNITNO, QueryParam.QUERY_EQ,scmPurCheckAdvQuery.getInvOrgUnitNo()));
				}
				if(scmPurCheckAdvQuery.getVendorId()>0){
					page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmPurReceive2.class)+"."+ScmPurReceive2.FN_VENDORID,
							new QueryParam(ClassUtils.getFinalModelSimpleName(ScmPurReceive2.class)+"."+ScmPurReceive2.FN_VENDORID, QueryParam.QUERY_EQ,String.valueOf(scmPurCheckAdvQuery.getVendorId())));
				}
				if(scmPurCheckAdvQuery.getVendorClassId()>0){
					page.getParam().put(Scmsuppliergroup.FN_ID,new QueryParam(ClassUtils.getFinalModelSimpleName(Scmsuppliergroup.class) + "." +Scmsuppliergroup.FN_ID, QueryParam.QUERY_EQ,String.valueOf(scmPurCheckAdvQuery.getVendorClassId())));
				}
			}
		}
	}

	@Override
	public void delByPurOrder(ScmPurOrder2 scmPurOrder, Param param) throws AppException {
		List<ScmPurCheck2> scmPurCheckList = this.selectByPoId(scmPurOrder.getId(), param);
    	if (scmPurCheckList != null && !scmPurCheckList.isEmpty()) {
    		for(ScmPurCheck2 scmPurCheck:scmPurCheckList) {
    			if(scmPurCheck.isFlag()){
    				throw new AppException(Message.getMessage(param.getLang(), "iscm.inventorymanage.scmPurCheck.delByPurOrder.error", new String[]{scmPurCheck.getCkNo()}));
    			}
    		}
    		this.delete(scmPurCheckList, param);
    	}
	}

	@Override
	public List<ScmPurCheck2> selectByPoId(long poId, Param param) throws AppException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("poId", poId);
		return ((ScmPurCheckDAO)dao).selectByPoId(map);
	}

	@Override
	public List<String> unConfirm(List<ScmPurCheck2> scmPurCheckList, Param param) throws AppException {
		List<String> rtnList = new ArrayList<String>();
		if(scmPurCheckList!=null && !scmPurCheckList.isEmpty()) {
			for(ScmPurCheck2 scmPurCheck:scmPurCheckList) {
				scmPurCheck = (ScmPurCheck2) this.select(scmPurCheck, param);
				ScmPurReceive2 scmPurReceive = scmPurReceiveBiz.selectByCkId(scmPurCheck.getId(),param);
				if(scmPurReceive!=null && !StringUtils.equals("I",scmPurReceive.getStatus())) {
					rtnList.add(Message.getMessage(param.getLang(), "iscm.ScmPurCheck.unConfirm.error.received", new String[] {scmPurReceive.getPvNo()}));
				}else {
					scmPurCheck.setFlag(false);
					this.update(scmPurCheck, param);
				}
			}
		}
		return rtnList;
	}

	@Override
	public List<ScmPurCheck2> selectBySaleIssueBill(long otId, Param param) throws AppException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("otId", otId);
		return ((ScmPurCheckDAO)dao).selectBySaleIssueBill(map);
	}

	@Override
	public List<ScmPurCheck2> selectByOtherIssueBill(long otId, Param param) throws AppException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("otId", otId);
		return ((ScmPurCheckDAO)dao).selectByOtherIssueBill(map);
	}

	@Override
	public ScmPurCheck2 updatePrtCount(ScmPurCheck2 scmPurCheck, Param param) throws AppException {
		if(scmPurCheck.getId()>0){
			ScmPurCheck2 bill = this.selectDirect(scmPurCheck.getId(), param);
			if(bill != null){
				int prtCount = bill.getPrtcount();
				bill.setPrtcount((prtCount+1));
				this.updateDirect(bill, param);
				return bill;
			}
		}
		return scmPurCheck;
	}
	
}

