package com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Page;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.base.model.QueryParam;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.util.ClassUtils;
import com.armitage.server.common.util.FormatUtils;
import com.armitage.server.iscm.basedata.model.ScmCostUseSet2;
import com.armitage.server.iscm.basedata.model.ScmCostUseType;
import com.armitage.server.iscm.basedata.service.ScmCostUseTypeBiz;
import com.armitage.server.iscm.common.model.ScmAuditDetailHistory2;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.dao.ScmInvMaterialRequestBillEntryDAO;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvMaterialReqBillEntry2;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvMaterialRequestBill2;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvMaterialRequestBillEntry2;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.service.ScmInvMaterialRequestBillBiz;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.service.ScmInvMaterialRequestBillEntryBiz;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurOrder2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurRequireEntry2;
import com.armitage.server.system.model.Code;
import com.armitage.server.system.service.CodeBiz;
import com.armitage.server.user.model.Usr;
import com.armitage.server.user.service.UsrBiz;
import org.springframework.stereotype.Service;

@Service("scmInvMaterialRequestBillEntryBiz")
public class ScmInvMaterialRequestBillEntryBizImpl extends BaseBizImpl<ScmInvMaterialRequestBillEntry2> implements ScmInvMaterialRequestBillEntryBiz {
	
	private ScmInvMaterialRequestBillBiz scmInvMaterialRequestBillBiz;
	private UsrBiz usrBiz;
	private CodeBiz codeBiz;
	private ScmCostUseTypeBiz scmCostUseTypeBiz;

	public ScmCostUseTypeBiz getScmCostUseTypeBiz() {
		return scmCostUseTypeBiz;
	}

	public void setScmCostUseTypeBiz(ScmCostUseTypeBiz scmCostUseTypeBiz) {
		this.scmCostUseTypeBiz = scmCostUseTypeBiz;
	}
	
	public ScmInvMaterialRequestBillBiz getScmInvMaterialRequestBillBiz() {
		return scmInvMaterialRequestBillBiz;
	}

	public void setScmInvMaterialRequestBillBiz(ScmInvMaterialRequestBillBiz scmInvMaterialRequestBillBiz) {
		this.scmInvMaterialRequestBillBiz = scmInvMaterialRequestBillBiz;
	}

	public void setUsrBiz(UsrBiz usrBiz) {
		this.usrBiz = usrBiz;
	}

	public void setCodeBiz(CodeBiz codeBiz) {
		this.codeBiz = codeBiz;
	}

	@Override
	protected void beforeFindPage(Page page, Param param) throws AppException {
		page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmInvMaterialRequestBill2.class) + "." + ScmInvMaterialRequestBill2.FN_ORGUNITNO), 
				new QueryParam((ClassUtils.getFinalModelSimpleName(ScmInvMaterialRequestBill2.class) + "." + ScmInvMaterialRequestBill2.FN_ORGUNITNO), QueryParam.QUERY_EQ, param.getOrgUnitNo()));
	}

	@Override
	protected void afterSelect(ScmInvMaterialRequestBillEntry2 entity, Param param) throws AppException {
		if(entity != null){
			setConvertMap(entity,param);
		}
	}

	@Override
	public List<ScmInvMaterialRequestBillEntry2> selectByReqId(long reqId, Param param) throws AppException {
		if(reqId > 0){
			HashMap<String, Object> map = new HashMap<>();
			map.put("reqId", reqId);
			List<ScmInvMaterialRequestBillEntry2> scmInvMaterialRequestBillEntryList = ((ScmInvMaterialRequestBillEntryDAO) dao).selectByReqId(map);
			if(scmInvMaterialRequestBillEntryList!=null && !scmInvMaterialRequestBillEntryList.isEmpty()){
				for(ScmInvMaterialRequestBillEntry2 scmInvMaterialRequestBillEntry:scmInvMaterialRequestBillEntryList){
					setConvertMap(scmInvMaterialRequestBillEntry,param);
				}
			}
			return scmInvMaterialRequestBillEntryList;
		}
		return null;
	}

	@Override
	public void deleteByReqId(long reqId, Param param) throws AppException {
		if(reqId > 0){
			HashMap<String, Object> map = new HashMap<>();
			map.put("reqId", reqId);
			((ScmInvMaterialRequestBillEntryDAO) dao).deleteByReqId(map);
		}
	}
	
	private void setConvertMap(ScmInvMaterialRequestBillEntry2 scmInvMaterialRequestBillEntry,Param param){
		if (scmInvMaterialRequestBillEntry.getCostUseTypeId() > 0 ) {
			ScmCostUseType scmCostUseType = scmCostUseTypeBiz.select(scmInvMaterialRequestBillEntry.getCostUseTypeId(), param);
			if (scmCostUseType != null) {
				ScmCostUseSet2 costUseSet = new ScmCostUseSet2();
				costUseSet.setCostUseTypeId(scmCostUseType.getId());
				costUseSet.setScmCostUseTypeName(scmCostUseType.getName());
				scmInvMaterialRequestBillEntry.setConvertMap(ScmInvMaterialRequestBillEntry2.FN_COSTUSETYPEID, costUseSet);
			}
        }else {
        	scmInvMaterialRequestBillEntry.setConvertMap(ScmInvMaterialRequestBillEntry2.FN_COSTUSETYPEID, new ScmCostUseSet2());
        }
    	if(scmInvMaterialRequestBillEntry != null && StringUtils.isNotBlank(scmInvMaterialRequestBillEntry.getRowAuditRemarks())){
    		List<ScmAuditDetailHistory2> auditDetailHistoryList = new ArrayList<>();
			StringBuffer rowAuditRemarks = new StringBuffer("");
			String[] rowAuditRemarksList = scmInvMaterialRequestBillEntry.getRowAuditRemarks().split(",");
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
				scmInvMaterialRequestBillEntry.setRowAuditRemarks(rowAuditRemarks.toString());
			}
			scmInvMaterialRequestBillEntry.setAuditDetailHistoryList(auditDetailHistoryList);
		}
    }

	@Override
	public void writeBackByReqOut(ScmInvMaterialReqBillEntry2 oldEntity, ScmInvMaterialReqBillEntry2 newEntity,
			Param param) throws AppException {
		BigDecimal qty = BigDecimal.ZERO;
		if(newEntity!=null && newEntity.getSourceBillDtlId()>0){
			qty = qty.add(newEntity.getReqQty());
		}
		if(oldEntity!=null && oldEntity.getSourceBillDtlId()>0){
			qty = qty.subtract(oldEntity.getReqQty());
		}
		if(newEntity!=null && newEntity.getSourceBillDtlId()>0 || (oldEntity!=null && oldEntity.getSourceBillDtlId()>0)){
			ScmInvMaterialRequestBillEntry2 scmInvMaterialRequestBillEntry = this.selectDirect(newEntity!=null?newEntity.getSourceBillDtlId():oldEntity.getSourceBillDtlId(), param);
			if(scmInvMaterialRequestBillEntry!=null) {
				scmInvMaterialRequestBillEntry.setOutQty(scmInvMaterialRequestBillEntry.getOutQty().add(qty));
				this.updateDirect(scmInvMaterialRequestBillEntry, param);
			}
		}
	}

	@Override
	public void updateRowStatusByReqId(long reqId, String status,
			String checker, Date checkDate, Param param) throws AppException {
		if (reqId > 0 && StringUtils.isNotBlank(status)) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("reqId", reqId);
            map.put("rowStatus", status);
            map.put("checker", checker);
            map.put("checkDate", checkDate);
            ((ScmInvMaterialRequestBillEntryDAO) dao).updateRowStatusByReqId(map);
        }
	}

	@Override
	public void updateRowStatusByLineId(long reqId, String status,
			String checker, Date checkDate, int lineId, Param param)
			throws AppException {
		if (reqId > 0 && StringUtils.isNotBlank(status)) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("reqId", reqId);
            map.put("rowStatus", status);
            map.put("checker", checker);
            map.put("checkDate", checkDate);
            map.put("lineId", lineId);
            ((ScmInvMaterialRequestBillEntryDAO) dao).updateRowStatusByLineId(map);
        }
	}

	@Override
	public void updateBillStatusByEntry(ScmInvMaterialRequestBillEntry2 scmInvMaterialRequestBillEntry, Param param)
			throws AppException {
		if (scmInvMaterialRequestBillEntry != null ) {
			ScmInvMaterialRequestBill2 scmInvMaterialRequestBill = scmInvMaterialRequestBillBiz.selectDirect(scmInvMaterialRequestBillEntry.getReqId(), param);
			if (scmInvMaterialRequestBill != null) {
				List<ScmInvMaterialRequestBillEntry2> list = this.selectByReqId(scmInvMaterialRequestBillEntry.getReqId(), param);
				if (list != null && !list.isEmpty()) {
					int count1 = 0;// 记录下达条数
					int count2 = 0;// 记录关闭条数
					for (ScmInvMaterialRequestBillEntry2 entry : list) {
						if (StringUtils.equals("E", entry.getRowStatus())) {
							count1++;
						} else if (StringUtils.equals("C", entry.getRowStatus())) {
							count2++;
						}
					}
					if (count2 > 0) {
						// 关闭条数大于0时，等于总条数时订货单状态为关闭，否则为部分关闭
						if (count2 == list.size()) {
							scmInvMaterialRequestBill.setStatus("C");
						} else {
							scmInvMaterialRequestBill.setStatus("F");
						}
					} else if (count2 == 0 && count1 > 0) {
						// 下达条数大于0时，等于总条数时订货单状态为下达，否则为部分下达
						if (count1 == list.size()) {
							scmInvMaterialRequestBill.setStatus("E");
						} else {
							scmInvMaterialRequestBill.setStatus("S");
						}
					} else if (count2 == 0 && count1 == 0) {
						scmInvMaterialRequestBill.setStatus("A");
					}
					scmInvMaterialRequestBillBiz.updateDirect(scmInvMaterialRequestBill, param);
				}
			}
		}	
	}

	@Override
	public void doRelease(ScmInvMaterialRequestBill2 scmInvMaterialRequestBill, Param param) throws AppException {
		if(scmInvMaterialRequestBill!=null) {
			List<ScmInvMaterialRequestBillEntry2> scmInvMaterialRequestBillEntryList = this.selectByReqId(scmInvMaterialRequestBill.getReqId(), param);
			if(scmInvMaterialRequestBillEntryList!=null && !scmInvMaterialRequestBillEntryList.isEmpty()) {
				for(ScmInvMaterialRequestBillEntry2 scmInvMaterialRequestBillEntry:scmInvMaterialRequestBillEntryList) {
					if(StringUtils.equals("A", scmInvMaterialRequestBillEntry.getRowStatus())) {
						scmInvMaterialRequestBillEntry.setRowStatus("E");
						this.updateDirect(scmInvMaterialRequestBillEntry, param);
					}
				}
			}
		}
	}

	@Override
	public void doUndoRelease(ScmInvMaterialRequestBill2 scmInvMaterialRequestBill, Param param)
			throws AppException {
		if(scmInvMaterialRequestBill!=null) {
			List<ScmInvMaterialRequestBillEntry2> scmInvMaterialRequestBillEntryList = this.selectByReqId(scmInvMaterialRequestBill.getReqId(), param);
			if(scmInvMaterialRequestBillEntryList!=null && !scmInvMaterialRequestBillEntryList.isEmpty()) {
				for(ScmInvMaterialRequestBillEntry2 scmInvMaterialRequestBillEntry:scmInvMaterialRequestBillEntryList) {
					if(StringUtils.equals("A", scmInvMaterialRequestBillEntry.getRowStatus())) {
						scmInvMaterialRequestBillEntry.setRowStatus("E");
						this.updateDirect(scmInvMaterialRequestBillEntry, param);
					}
				}
			}
		}
	}

}
