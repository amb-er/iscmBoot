package com.armitage.server.iscm.basedata.service.impl;


import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Page;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.base.model.QueryParam;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.util.CalendarUtil;
import com.armitage.server.common.util.ClassUtils;
import com.armitage.server.common.util.FormatUtils;
import com.armitage.server.iscm.basedata.dao.ScmSupplierConfirmDataDAO;
import com.armitage.server.iscm.basedata.model.ScmSupplierConfirmData2;
import com.armitage.server.iscm.basedata.model.ScmSupplierConfirmOrReplyDataAdvQuery;
import com.armitage.server.iscm.basedata.model.ScmSupplierReplyData;
import com.armitage.server.iscm.basedata.service.ScmSupplierConfirmDataBiz;
import com.armitage.server.iscm.basedata.service.ScmSupplierReplyDataBiz;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvPurInWarehsBill2;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.service.ScmInvPurInWarehsBillBiz;
import com.armitage.server.system.model.BillType2;
import com.armitage.server.system.service.BillTypeBiz;
import org.springframework.stereotype.Service;

@Service("scmSupplierConfirmDataBiz")
public class ScmSupplierConfirmDataBizImpl extends BaseBizImpl<ScmSupplierConfirmData2> implements ScmSupplierConfirmDataBiz {
	private BillTypeBiz billTypeBiz;
	private ScmInvPurInWarehsBillBiz scmInvPurInWarehsBillBiz;
	private ScmSupplierReplyDataBiz scmSupplierReplyDataBiz;
	public void setBillTypeBiz(BillTypeBiz billTypeBiz) {
		this.billTypeBiz = billTypeBiz;
	}

	public void setScmInvPurInWarehsBillBiz(ScmInvPurInWarehsBillBiz scmInvPurInWarehsBillBiz) {
		this.scmInvPurInWarehsBillBiz = scmInvPurInWarehsBillBiz;
	}

	public void setScmSupplierReplyDataBiz(ScmSupplierReplyDataBiz scmSupplierReplyDataBiz) {
		this.scmSupplierReplyDataBiz = scmSupplierReplyDataBiz;
	}

	@Override
	protected void beforeFindPage(Page page, Param param) throws AppException {
		page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmSupplierConfirmData2.class) + "." + ScmSupplierConfirmData2.FN_ORGUNITNO), 
				new QueryParam((ClassUtils.getFinalModelSimpleName(ScmSupplierConfirmData2.class) + "." + ScmSupplierConfirmData2.FN_ORGUNITNO), QueryParam.QUERY_EQ, param.getOrgUnitNo()));
		page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmSupplierConfirmData2.class) + "." + ScmSupplierConfirmData2.FN_CONTROLUNITNO), 
				new QueryParam((ClassUtils.getFinalModelSimpleName(ScmSupplierConfirmData2.class) + "." + ScmSupplierConfirmData2.FN_CONTROLUNITNO), QueryParam.QUERY_EQ, param.getControlUnitNo()));
	}
	
	@Override
	public void doAdvQuery(Page page, Param param) {
		if (page.getModel() != null) {
			if (page.getModel() instanceof ScmSupplierConfirmOrReplyDataAdvQuery) {
				ScmSupplierConfirmOrReplyDataAdvQuery scmSupplierConfirmOrReplyDataAdvQuery = (ScmSupplierConfirmOrReplyDataAdvQuery) page.getModel();
				if(StringUtils.isNotBlank(scmSupplierConfirmOrReplyDataAdvQuery.getBillNo())){
					page.getParam().put(ScmSupplierConfirmData2.FN_BILLNO,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmSupplierConfirmData2.class) + "." +ScmSupplierConfirmData2.FN_BILLNO, QueryParam.QUERY_LIKE,"%"+scmSupplierConfirmOrReplyDataAdvQuery.getBillNo()+"%"));
				}
				if(scmSupplierConfirmOrReplyDataAdvQuery.getPlatVendorId()>0){
					page.getParam().put(ScmSupplierConfirmData2.FN_PLATSUPPLIERID,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmSupplierConfirmData2.class) + "." +ScmSupplierConfirmData2.FN_PLATSUPPLIERID, QueryParam.QUERY_EQ,String.valueOf(scmSupplierConfirmOrReplyDataAdvQuery.getPlatVendorId())));
				}
				if(scmSupplierConfirmOrReplyDataAdvQuery.getBegDate()!=null){
					if(scmSupplierConfirmOrReplyDataAdvQuery.getEndDate()!=null) {
						page.getParam().put(ScmSupplierConfirmData2.FN_CONFIRMTIME,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmSupplierConfirmData2.class) + "." +ScmSupplierConfirmData2.FN_CONFIRMTIME, QueryParam.QUERY_BETWEEN,FormatUtils.fmtDate(scmSupplierConfirmOrReplyDataAdvQuery.getBegDate()),FormatUtils.fmtDate(CalendarUtil.relativeDate(scmSupplierConfirmOrReplyDataAdvQuery.getEndDate(),1))));
					}else {
						page.getParam().put(ScmSupplierConfirmData2.FN_CONFIRMTIME,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmSupplierConfirmData2.class) + "." +ScmSupplierConfirmData2.FN_CONFIRMTIME, QueryParam.QUERY_GE,FormatUtils.fmtDate(scmSupplierConfirmOrReplyDataAdvQuery.getBegDate())));
					}
				}else if(scmSupplierConfirmOrReplyDataAdvQuery.getEndDate()!=null) {
					page.getParam().put(ScmSupplierConfirmData2.FN_CONFIRMTIME,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmSupplierConfirmData2.class) + "." +ScmSupplierConfirmData2.FN_CONFIRMTIME, QueryParam.QUERY_LE,FormatUtils.fmtDate(CalendarUtil.relativeDate(scmSupplierConfirmOrReplyDataAdvQuery.getEndDate(),1))));
				}
				if(StringUtils.isNotBlank(scmSupplierConfirmOrReplyDataAdvQuery.getStatus())){
					page.getParam().put(ScmSupplierConfirmData2.FN_STATUS,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmSupplierConfirmData2.class) + "." +ScmSupplierConfirmData2.FN_STATUS, QueryParam.QUERY_EQ,scmSupplierConfirmOrReplyDataAdvQuery.getStatus()));
				}
				if(StringUtils.isNotBlank(scmSupplierConfirmOrReplyDataAdvQuery.getBillType())){
					page.getParam().put(ScmSupplierConfirmData2.FN_BILLTYPE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmSupplierConfirmData2.class) + "." +ScmSupplierConfirmData2.FN_BILLTYPE, QueryParam.QUERY_EQ,scmSupplierConfirmOrReplyDataAdvQuery.getBillType()));
				}
			}
		}
 	}

	@Override
	public ScmSupplierConfirmData2 selectByBcId(long bcId,Param param) throws AppException {
		if(bcId>0){
			HashMap<String, Object> map = new HashMap<>();
			map.put("bcId", bcId);
			map.put("controlUnitNo", param.getControlUnitNo());
			return ((ScmSupplierConfirmDataDAO) dao).selectByBcId(map);
		}
		return null;
	}

	@Override
	protected void afterFindPage(List list, Page page, Param param) throws AppException {
		if(list!=null && !list.isEmpty()) {
			for(ScmSupplierConfirmData2 scmSupplierConfirmData:(List<ScmSupplierConfirmData2>)list) {
				this.setConvertMap(scmSupplierConfirmData,param);
			}
		}
	}

	private void setConvertMap(ScmSupplierConfirmData2 scmSupplierConfirmData, Param param) {
		if(scmSupplierConfirmData!=null) {
			if(StringUtils.isNotBlank(scmSupplierConfirmData.getBillType())) {
				BillType2 billType = billTypeBiz.selectByBillCode(scmSupplierConfirmData.getBillType(), param);
				if(billType!=null) {
					scmSupplierConfirmData.setConvertMap(ScmSupplierConfirmData2.FN_BILLTYPE, billType);
				}
			}
		}
	}

	@Override
	protected HashMap<String, Object> addFindAllPageParam(Page page, HashMap<String, Object> map, Param param) {
		if (StringUtils.equals("629252010", page.getArguments())) {
			map.put("msgType", "R");
		}else {
			map.put("msgType", "A");	//回复消息
		}
		return map;
	}

	@Override
	public void saveConfirmAdj(ScmSupplierConfirmData2 scmSupplierConfirmData, Param param) throws AppException {
		ScmInvPurInWarehsBill2 scmInvPurInWarehsBill = scmInvPurInWarehsBillBiz.selectByWrNo(scmSupplierConfirmData.getBillNo(), param);
		if(scmInvPurInWarehsBill!=null) {
			if(StringUtils.isNotBlank(scmSupplierConfirmData.getConfirmStatus())) {
				scmInvPurInWarehsBill.setConfirmStatus(scmSupplierConfirmData.getConfirmStatus());
				scmInvPurInWarehsBillBiz.updateConfirmStatus(scmInvPurInWarehsBill, param);
			}
			if(StringUtils.isNotBlank(scmSupplierConfirmData.getConfirmStatus()) || StringUtils.isNotBlank(scmSupplierConfirmData.getConfirmInfo())) {
				ScmSupplierReplyData scmSupplierReplyData = new ScmSupplierReplyData(true);
				scmSupplierReplyData.setCdId(scmSupplierConfirmData.getId());
				scmSupplierReplyData.setBcId(scmSupplierConfirmData.getBcId());
				scmSupplierReplyData.setConfirmBy(param.getUsrName());
				scmSupplierReplyData.setConfirmStatus(scmSupplierConfirmData.getConfirmStatus());
				scmSupplierReplyData.setMsgContent(scmSupplierConfirmData.getConfirmInfo());
				scmSupplierReplyData.setMsgSendTime(new Date());
				scmSupplierReplyData.setCreator(param.getUsrName());
				scmSupplierReplyData.setCreateDate(new Date());
				scmSupplierReplyData.setControlUnitNo(scmSupplierConfirmData.getControlUnitNo());
				scmSupplierReplyDataBiz.add(scmSupplierReplyData, param);
			}
		}
	}

	@Override
	public ScmSupplierConfirmData2 selectByBillNoAndType(String billNo, String billType, Param param)
			throws AppException {
		if(StringUtils.isNotBlank(billNo) && StringUtils.isNotBlank(billType)){
			HashMap<String, Object> map = new HashMap<>();
			map.put("billNo", billNo);
			map.put("billType", billType);
			map.put("controlUnitNo", param.getControlUnitNo());
			return ((ScmSupplierConfirmDataDAO) dao).selectByBillNoAndType(map);
		}
		return null;
	}

	@Override
	public void deleteListByBillNoAndType(String billNo, String billType, Param param) throws AppException {
		if(StringUtils.isNotBlank(billNo) && StringUtils.isNotBlank(billType)){
			HashMap<String, Object> map = new HashMap<>();
			map.put("billNo", billNo);
			map.put("billType", billType);
			map.put("controlUnitNo", param.getControlUnitNo());
			((ScmSupplierConfirmDataDAO) dao).deleteListByBillNoAndType(map);
		}
	}

	@Override
	public List<ScmSupplierConfirmData2> selectByBillNoAndPurPrice(String billNo, String billtype, Param param)
			throws AppException {
		if(StringUtils.isNotBlank(billNo) && StringUtils.isNotBlank(billtype)){
			HashMap<String, Object> map = new HashMap<>();
			map.put("billNo", billNo);
			map.put("billType", billtype);
			map.put("controlUnitNo", param.getControlUnitNo());
			return ((ScmSupplierConfirmDataDAO) dao).selectByBillNoAndPurPrice(map);
		}
		return null;
	}
	
}

