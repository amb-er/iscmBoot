package com.armitage.server.iscm.basedata.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.armitage.server.api.business.basedata.params.QualifieBillParams;
import com.armitage.server.activity.util.ActivityUtil;
import com.armitage.server.activity.util.AuditMsgUtil;
import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.CommonBean;
import com.armitage.server.common.base.model.Page;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.base.model.QueryParam;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.util.CalendarUtil;
import com.armitage.server.common.util.ClassUtils;
import com.armitage.server.common.util.FormatUtils;
import com.armitage.server.common.util.LangUtil;
import com.armitage.server.iscm.basedata.dao.ScmSupplierQualifieInfoBillDAO;
import com.armitage.server.iscm.basedata.model.ScmBillPending;
import com.armitage.server.iscm.basedata.model.ScmBillPending2;
import com.armitage.server.iscm.basedata.model.ScmQualifieInfo2;
import com.armitage.server.iscm.basedata.model.ScmSupplierQualifieInfoBill2;
import com.armitage.server.iscm.basedata.model.ScmSupplierQualifieInfoBillAdvQuery;
import com.armitage.server.iscm.basedata.model.ScmSupplierQualifieInfoBillEntry;
import com.armitage.server.iscm.basedata.model.ScmSupplierQualifieInfoBillEntry2;
import com.armitage.server.iscm.basedata.model.Scmsupplier2;
import com.armitage.server.iscm.basedata.model.Scmsuppliercompanyinfo2;
import com.armitage.server.iscm.basedata.model.Scmsuppliergroup;
import com.armitage.server.iscm.basedata.model.Scmsupplierlinkman;
import com.armitage.server.iscm.basedata.model.Scmsupplierpurchaseinfo2;
import com.armitage.server.iscm.basedata.service.ScmBillPendingBiz;
import com.armitage.server.iscm.basedata.service.ScmQualifieInfoBiz;
import com.armitage.server.iscm.basedata.service.ScmSupplierQualifieInfoBillBiz;
import com.armitage.server.iscm.basedata.service.ScmSupplierQualifieInfoBillEntryBiz;
import com.armitage.server.iscm.basedata.service.ScmsupplierBiz;
import com.armitage.server.iscm.basedata.service.ScmsuppliercompanyinfoBiz;
import com.armitage.server.iscm.basedata.service.ScmsuppliergroupBiz;
import com.armitage.server.iscm.basedata.service.ScmsupplierlinkmanBiz;
import com.armitage.server.iscm.basedata.service.ScmsupplierpurchaseinfoBiz;
import com.armitage.server.iscm.common.model.CommonAuditOpinion;
import com.armitage.server.iscm.common.model.CommonBillEntryStatus;
import com.armitage.server.iscm.common.model.CommonEventHistory;
import com.armitage.server.iscm.common.service.CommonEventHistoryBiz;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.CommonAuditParams;
import com.armitage.server.system.model.BillType2;
import com.armitage.server.system.model.Code;
import com.armitage.server.system.service.BillTypeBiz;
import com.armitage.server.system.service.CodeBiz;
import com.armitage.server.system.util.CodeAutoCalUtil;
import com.armitage.server.user.model.Usr;
import com.armitage.server.user.service.UsrBiz;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Service;

@Service("scmSupplierQualifieInfoBillBiz")
public class ScmSupplierQualifieInfoBillBizImpl extends BaseBizImpl<ScmSupplierQualifieInfoBill2> implements ScmSupplierQualifieInfoBillBiz {
	private ScmSupplierQualifieInfoBillEntryBiz scmSupplierQualifieInfoBillEntryBiz;
	private UsrBiz usrBiz;
	private ScmsupplierBiz scmsupplierBiz;
	private BillTypeBiz billTypeBiz;
	private ScmBillPendingBiz scmBillPendingBiz;
	private CommonEventHistoryBiz commonEventHistoryBiz;
	private ScmQualifieInfoBiz scmQualifieInfoBiz;
	private CodeBiz codeBiz;
	private ScmsupplierlinkmanBiz scmsupplierlinkmanBiz;
	private ScmsuppliergroupBiz scmsuppliergroupBiz;
	private ScmsupplierpurchaseinfoBiz scmsupplierpurchaseinfoBiz;
	private ScmsuppliercompanyinfoBiz scmsuppliercompanyinfoBiz;
	private Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss.SSS").create();
	private Log log = LogFactory.getLog(ScmSupplierQualifieInfoBillBizImpl.class);
	
	public void setScmSupplierQualifieInfoBillEntryBiz(
			ScmSupplierQualifieInfoBillEntryBiz scmSupplierQualifieInfoBillEntryBiz) {
		this.scmSupplierQualifieInfoBillEntryBiz = scmSupplierQualifieInfoBillEntryBiz;
	}

	public void setUsrBiz(UsrBiz usrBiz) {
		this.usrBiz = usrBiz;
	}

	public void setScmsupplierBiz(ScmsupplierBiz scmsupplierBiz) {
		this.scmsupplierBiz = scmsupplierBiz;
	}

	public void setBillTypeBiz(BillTypeBiz billTypeBiz) {
		this.billTypeBiz = billTypeBiz;
	}

	public void setScmBillPendingBiz(ScmBillPendingBiz scmBillPendingBiz) {
		this.scmBillPendingBiz = scmBillPendingBiz;
	}

	public void setCommonEventHistoryBiz(CommonEventHistoryBiz commonEventHistoryBiz) {
		this.commonEventHistoryBiz = commonEventHistoryBiz;
	}

	public void setScmQualifieInfoBiz(ScmQualifieInfoBiz scmQualifieInfoBiz) {
		this.scmQualifieInfoBiz = scmQualifieInfoBiz;
	}

	public void setCodeBiz(CodeBiz codeBiz) {
		this.codeBiz = codeBiz;
	}

	public void setScmsupplierlinkmanBiz(ScmsupplierlinkmanBiz scmsupplierlinkmanBiz) {
		this.scmsupplierlinkmanBiz = scmsupplierlinkmanBiz;
	}

	public void setScmsuppliergroupBiz(ScmsuppliergroupBiz scmsuppliergroupBiz) {
		this.scmsuppliergroupBiz = scmsuppliergroupBiz;
	}

	public void setScmsupplierpurchaseinfoBiz(ScmsupplierpurchaseinfoBiz scmsupplierpurchaseinfoBiz) {
		this.scmsupplierpurchaseinfoBiz = scmsupplierpurchaseinfoBiz;
	}

	public void setScmsuppliercompanyinfoBiz(ScmsuppliercompanyinfoBiz scmsuppliercompanyinfoBiz) {
		this.scmsuppliercompanyinfoBiz = scmsuppliercompanyinfoBiz;
	}

	@Override
	protected HashMap<String, Object> addFindAllPageParam(Page page, HashMap<String, Object> map, Param param) {
		map.put("controlUnitNo", param.getControlUnitNo());
		return map;
	}

	@Override
	protected void afterSelect(CommonBean bean, Param param) throws AppException {
		if(bean!=null && bean.getList()!=null && !bean.getList().isEmpty()) {
			ScmSupplierQualifieInfoBill2 scmSupplierQualifieInfoBill = (ScmSupplierQualifieInfoBill2) bean.getList().get(0);
			bean.setList2(scmSupplierQualifieInfoBillEntryBiz.selectByBillId(scmSupplierQualifieInfoBill.getId(), param));
		}
	}

	@Override
	protected void afterFindPage(List list, Page page, Param param) throws AppException {
		if(list!=null && !list.isEmpty()) {
			for(ScmSupplierQualifieInfoBill2 scmSupplierQualifieInfoBill:(List<ScmSupplierQualifieInfoBill2>)list) {
				setConvertMap(scmSupplierQualifieInfoBill, param);
			}
		}
		super.afterFindPage(list, page, param);
	}

	@Override
	protected void afterSelect(ScmSupplierQualifieInfoBill2 entity, Param param) throws AppException {
		if(entity!=null)
			setConvertMap(entity, param);
	}
	
	private void setConvertMap(ScmSupplierQualifieInfoBill2 scmSupplierQualifieInfoBill, Param param) throws AppException {
		if(scmSupplierQualifieInfoBill!=null) {
			if(scmSupplierQualifieInfoBill.getVendorId()>0) {
				Scmsupplier2 scmsupplier = scmsupplierBiz.selectDirect(scmSupplierQualifieInfoBill.getVendorId(), param);
				if(scmsupplier!=null) {
					scmSupplierQualifieInfoBill.setVendorName(scmsupplier.getVendorName());
					scmSupplierQualifieInfoBill.setConvertMap(ScmSupplierQualifieInfoBill2.FN_VENDORID, scmsupplier);
				}
			}
			if(StringUtils.isNotBlank(scmSupplierQualifieInfoBill.getCreator())) {
				Usr usr = usrBiz.selectByCode(scmSupplierQualifieInfoBill.getCreator(), param);
				if(usr!=null) {
					scmSupplierQualifieInfoBill.setCreatorName(usr.getName());
					scmSupplierQualifieInfoBill.setConvertMap(ScmSupplierQualifieInfoBill2.FN_CREATOR, usr);
				}
			}
			if(StringUtils.isNotBlank(scmSupplierQualifieInfoBill.getEditor())) {
				Usr usr = usrBiz.selectByCode(scmSupplierQualifieInfoBill.getEditor(), param);
				if(usr!=null)
					scmSupplierQualifieInfoBill.setConvertMap(ScmSupplierQualifieInfoBill2.FN_EDITOR, usr);
			}
			if(StringUtils.isNotBlank(scmSupplierQualifieInfoBill.getChecker())) {
				Usr usr = usrBiz.selectByCode(scmSupplierQualifieInfoBill.getChecker(), param);
				if(usr!=null)
					scmSupplierQualifieInfoBill.setConvertMap(ScmSupplierQualifieInfoBill2.FN_CREATOR, usr);
			}
			if(StringUtils.isNotBlank(scmSupplierQualifieInfoBill.getStatus())) {
				Code code = codeBiz.selectByCategoryAndCode("quotationStatus", scmSupplierQualifieInfoBill.getStatus());
				if(code!=null)
					scmSupplierQualifieInfoBill.setStatusName(LangUtil.getLang(code, "name", param));
			}
		}
	}

	@Override
	public void UpdateByQualifieInfo(List<ScmQualifieInfo2> scmQualifieInfoList2, Param param) throws AppException {
		if(scmQualifieInfoList2!=null && !scmQualifieInfoList2.isEmpty()) {
			HashMap<String,Object> map1 = new HashMap<String,Object>();
			map1.put("vendorId", scmQualifieInfoList2.get(0).getVendorId());
			ScmSupplierQualifieInfoBill2 scmSupplierQualifieInfoBill =  ((ScmSupplierQualifieInfoBillDAO)dao).selectByVendorId(map1);
			CommonBean bean = new CommonBean();
			List<ScmSupplierQualifieInfoBill2> scmSupplierQualifieInfoBillList = new ArrayList();
			List<ScmSupplierQualifieInfoBillEntry2> scmSupplierQualifieInfoBillEntryList = new ArrayList();
			boolean addFlag=false;
			if(scmSupplierQualifieInfoBill==null) {
				addFlag = true;
				scmSupplierQualifieInfoBill = new ScmSupplierQualifieInfoBill2(true);
				scmSupplierQualifieInfoBill.setSource("iESP");
				scmSupplierQualifieInfoBill.setBizDate(FormatUtils.parseDate(FormatUtils.fmtDate(CalendarUtil.getDate(param))));
				scmSupplierQualifieInfoBill.setCreator(param.getUsrCode());
				scmSupplierQualifieInfoBill.setCreateDate(CalendarUtil.getDate(param));
				HashMap<Long,ScmQualifieInfo2> map = new HashMap<Long,ScmQualifieInfo2>();
				for(ScmQualifieInfo2 scmQualifieInfo:scmQualifieInfoList2) {
					if(!map.containsKey(scmQualifieInfo.getTypeId())) {
						ScmSupplierQualifieInfoBillEntry2 scmSupplierQualifieInfoBillEntry = new ScmSupplierQualifieInfoBillEntry2(true);
						scmSupplierQualifieInfoBillEntry.setTypeId(scmQualifieInfo.getTypeId());
						scmSupplierQualifieInfoBillEntry.setRequired(scmQualifieInfo.isRequired());
						scmSupplierQualifieInfoBillEntry.setQualifyAudit(false);
						scmSupplierQualifieInfoBillEntry.setSourceBillDtlId(scmQualifieInfo.getId());
						scmSupplierQualifieInfoBillEntry.setRemarks(scmQualifieInfo.getRemarks());
						scmSupplierQualifieInfoBillEntryList.add(scmSupplierQualifieInfoBillEntry);
						map.put(scmQualifieInfo.getTypeId(), scmQualifieInfo);
					}
				}
			}else {
				if(StringUtils.contains("I,R", scmSupplierQualifieInfoBill.getStatus())) {
					scmSupplierQualifieInfoBill.setStatus("I");
					scmSupplierQualifieInfoBill.setSource("iESP");
					scmSupplierQualifieInfoBill.setEditor(param.getUsrCode());
					scmSupplierQualifieInfoBill.setEditDate(CalendarUtil.getDate(param));
				}
				scmSupplierQualifieInfoBillEntryList = scmSupplierQualifieInfoBillEntryBiz.selectByBillId(scmSupplierQualifieInfoBill.getId(), param);
				if(scmSupplierQualifieInfoBillEntryList==null || scmSupplierQualifieInfoBillEntryList.isEmpty()) {
					HashMap<Long,ScmQualifieInfo2> map = new HashMap<Long,ScmQualifieInfo2>();
					for(ScmQualifieInfo2 scmQualifieInfo:scmQualifieInfoList2) {
						if(!map.containsKey(scmQualifieInfo.getTypeId())) {
							ScmSupplierQualifieInfoBillEntry2 scmSupplierQualifieInfoBillEntry = new ScmSupplierQualifieInfoBillEntry2(true);
							scmSupplierQualifieInfoBillEntry.setTypeId(scmQualifieInfo.getTypeId());
							scmSupplierQualifieInfoBillEntry.setRequired(scmQualifieInfo.isRequired());
							scmSupplierQualifieInfoBillEntry.setQualifyAudit(false);
							scmSupplierQualifieInfoBillEntry.setSourceBillDtlId(scmQualifieInfo.getId());
							scmSupplierQualifieInfoBillEntry.setRemarks(scmQualifieInfo.getRemarks());
							scmSupplierQualifieInfoBillEntryList.add(scmSupplierQualifieInfoBillEntry);
							map.put(scmQualifieInfo.getTypeId(), scmQualifieInfo);
						}
					}
				}else {
					for(ScmQualifieInfo2 scmQualifieInfo:scmQualifieInfoList2) {
						boolean exists=false;
						for(ScmSupplierQualifieInfoBillEntry scmSupplierQualifieInfoBillEntry:scmSupplierQualifieInfoBillEntryList) {
							if(scmQualifieInfo.getTypeId()==scmSupplierQualifieInfoBillEntry.getTypeId()) {
								exists=true;
								break;
							}
							
						}
						if(!exists) {
							ScmSupplierQualifieInfoBillEntry2 scmSupplierQualifieInfoBillEntry = new ScmSupplierQualifieInfoBillEntry2(true);
							scmSupplierQualifieInfoBillEntry.setTypeId(scmQualifieInfo.getTypeId());
							scmSupplierQualifieInfoBillEntry.setRequired(scmQualifieInfo.isRequired());
							scmSupplierQualifieInfoBillEntry.setQualifyAudit(false);
							scmSupplierQualifieInfoBillEntry.setSourceBillDtlId(scmQualifieInfo.getId());
							scmSupplierQualifieInfoBillEntry.setRemarks(scmQualifieInfo.getRemarks());
							scmSupplierQualifieInfoBillEntryList.add(scmSupplierQualifieInfoBillEntry);
						}
					}
					for (int i = scmSupplierQualifieInfoBillEntryList.size() - 1; i >= 0; i--) {
						boolean exists=false;
						for(ScmQualifieInfo2 scmQualifieInfo:scmQualifieInfoList2) {
							if(scmQualifieInfo.getTypeId()==scmSupplierQualifieInfoBillEntryList.get(i).getTypeId()) {
								exists=true;
								break;
							}
						}
						if(!exists) {
							scmSupplierQualifieInfoBillEntryList.remove(i);
						}
					}
				}
			}
			scmSupplierQualifieInfoBill.setVendorId(scmQualifieInfoList2.get(0).getVendorId());
			scmSupplierQualifieInfoBillList.add(scmSupplierQualifieInfoBill);
			bean.setList(scmSupplierQualifieInfoBillList);
			bean.setList2(scmSupplierQualifieInfoBillEntryList);
			if(addFlag) {
				this.add(bean, param);
			}else {
				this.update(bean, param);
			}
			if(StringUtils.equals("I", scmSupplierQualifieInfoBill.getStatus())) {
				if(param.getSystemIdList()==null || param.getSystemIdList().isEmpty()) {
					List<Long> systemIdList = new ArrayList();
					systemIdList.add(170L);
					param.setSystemIdList(systemIdList);
				}
				this.doSubmitQualifieInfo(scmSupplierQualifieInfoBill, param);
			}
		}
	}

	@Override
	protected void beforeAdd(CommonBean bean, Param param) throws AppException {
		ScmSupplierQualifieInfoBill2 scmSupplierQualifieInfoBill = (ScmSupplierQualifieInfoBill2) bean.getList().get(0);
		if(scmSupplierQualifieInfoBill != null){
			String code = CodeAutoCalUtil.getBillCode("ScmQualifieInfo", scmSupplierQualifieInfoBill, param);
			scmSupplierQualifieInfoBill.setBillNo(code);
		}
	}

	@Override
	protected void afterAdd(CommonBean bean, Param param) throws AppException {
		ScmSupplierQualifieInfoBill2 scmSupplierQualifieInfoBill = (ScmSupplierQualifieInfoBill2) bean.getList().get(0);
		if(scmSupplierQualifieInfoBill != null && scmSupplierQualifieInfoBill.getId() > 0){
			//新增订货明细
			List<ScmSupplierQualifieInfoBillEntry> scmSupplierQualifieInfoBillEntryList = bean.getList2();
			if(scmSupplierQualifieInfoBillEntryList != null && !scmSupplierQualifieInfoBillEntryList.isEmpty()){
				int lineId = 1;
				for(ScmSupplierQualifieInfoBillEntry scmSupplierQualifieInfoBillEntry : scmSupplierQualifieInfoBillEntryList){
					scmSupplierQualifieInfoBillEntry.setLineId(lineId);
					scmSupplierQualifieInfoBillEntry.setBillId(scmSupplierQualifieInfoBill.getId());
				}
				scmSupplierQualifieInfoBillEntryBiz.update(scmSupplierQualifieInfoBill, scmSupplierQualifieInfoBillEntryList, ScmSupplierQualifieInfoBillEntry.FN_BILLID, param);
			}
		}
	}

	@Override
	protected void afterUpdate(CommonBean bean, Param param) throws AppException {
		this.afterAdd(bean, param);
	}

	@Override
	protected void afterUpdate(ScmSupplierQualifieInfoBill2 oldEntity, ScmSupplierQualifieInfoBill2 newEntity,
			Param param) throws AppException {
		if(oldEntity!=null && newEntity!=null && !StringUtils.equals(newEntity.getStatus(), oldEntity.getStatus())) {
			Scmsupplier2 scmsupplier = scmsupplierBiz.selectDirect(newEntity.getVendorId(), param);
			if(scmsupplier!=null) {
				if(!StringUtils.equals("P", newEntity.getStatus())) {
					String status="";
					if(StringUtils.equals("A", newEntity.getStatus())) {
						status="O";
					}else if(StringUtils.equals("R", newEntity.getStatus())) {
						status="RJ";
					}else if(StringUtils.equals("N", newEntity.getStatus())) {
						status="N";
					}else if(StringUtils.equals("D", newEntity.getStatus())) {
						status="W";
					}
					scmsupplier.setQualificationStatus(status);
				}
				if(StringUtils.contains("A,R,N,D", newEntity.getStatus()))
					scmsupplier.setStatus(newEntity.getStatus());
				scmsupplierBiz.update(scmsupplier, param);
				if (StringUtils.equals(scmsupplier.getStatus(), "A")) {
					Scmsupplierpurchaseinfo2 scmsupplierpurchaseinfo2 = scmsupplierpurchaseinfoBiz.selectByVendorIdAndOrgUnitNo(scmsupplier.getId(), param.getOrgUnitNo(), param);
					if (scmsupplierpurchaseinfo2 != null) {
						scmsupplierpurchaseinfo2.setStatus(scmsupplier.getStatus());
						scmsupplierpurchaseinfoBiz.updateDirect(scmsupplierpurchaseinfo2, param);
					}
					Scmsuppliercompanyinfo2 scmsuppliercompanyinfo2 = scmsuppliercompanyinfoBiz.selectByVendorIdAndOrgUnitNo(scmsupplier.getId(), param.getOrgUnitNo(), param);
					if (scmsuppliercompanyinfo2 != null) {
						scmsuppliercompanyinfo2.setStatus(scmsupplier.getStatus());
						scmsuppliercompanyinfoBiz.updateDirect(scmsuppliercompanyinfo2, param);
					}
				}
			}
		}
	}

	@Override
	public ScmSupplierQualifieInfoBill2 doSubmitQualifieInfo(ScmSupplierQualifieInfoBill2 scmSupplierQualifieInfoBill,
			Param param) throws AppException {
		ScmSupplierQualifieInfoBill2 require = null;
		if(scmSupplierQualifieInfoBill.getId()>0){
			require = this.selectDirect(scmSupplierQualifieInfoBill.getId(), param);
		}else{
			Page page=new Page();
			page.setModelClass(ScmSupplierQualifieInfoBill2.class);
			page.setShowCount(Integer.MAX_VALUE);
			page.getParam().put(
					ScmSupplierQualifieInfoBill2.FN_BILLNO,
					new QueryParam(ScmSupplierQualifieInfoBill2.FN_BILLNO, QueryParam.QUERY_EQ,
							scmSupplierQualifieInfoBill.getBillNo()));
			
			List<ScmSupplierQualifieInfoBill2> requireList =this.findPage(page, param);
			if(requireList!=null && !requireList.isEmpty()){
				require=requireList.get(0);
			}
		}
		if(require!=null){
			this.setConvertMap(require, param);
			if(!StringUtils.equals(require.getStatus(),"I")){
				throw new AppException("iscm.purchasemanage.error.commit");
			}else if(require.getStatus().equals("I")){
				BillType2 billType = billTypeBiz.selectByOrgAndCode(require.getControlUnitNo(), "ScmQualifieInfo", param);
				boolean needAudit = false;
				if(billType!=null && billType.isNeedAudit())
					needAudit = true;
				if(needAudit){
					String processInstanceId = startWorkFlow(billType,require, param);
					require.setWorkFlowNo(processInstanceId);
					ActivityUtil activityUtil = new ActivityUtil();
					//判断当前流程是否结束
					boolean isCompleteTask = activityUtil.queryProInstanceStateByProInstanceId(processInstanceId);
					if(isCompleteTask) {
						if(param.getUsrCode()!=null ){
							require.setChecker(param.getUsrCode());
							require.setSubmitter(param.getUsrCode());
						}
						require.setCheckDate(CalendarUtil.getDate(param));
						require.setSubmitDate(CalendarUtil.getDate(param));
						require.setStatus("A");
					}else {
						if(param.getUsrCode()!=null ){
							require.setSubmitter(param.getUsrCode());
						}
						require.setSubmitDate(CalendarUtil.getDate(param));
						require.setStatus("D");
						sendAuditMsg(require,"A",billType.getBillCode(),param);
					}
				}else{
					if(param.getUsrCode()!=null ){
						require.setChecker(param.getUsrCode());
						require.setSubmitter(param.getUsrCode());
					}
					require.setCheckDate(CalendarUtil.getDate(param));
					require.setSubmitDate(CalendarUtil.getDate(param));
					require.setStatus("A");
				}
				try {
					this.updateStatus(require, param);
				} catch (Exception e) {
					throw e;
				}
			}
		}else{
			throw new AppException("iscm.purchasemanage.purchasebusiness.find.ordernotexists");
		}
		return require;
	}

	private String startWorkFlow(BillType2 billType,ScmSupplierQualifieInfoBill2 scmSupplierQualifieInfoBill, Param param) {
		//发起流程
		CommonBean bean = new CommonBean();
		List<ScmSupplierQualifieInfoBill2> scmSupplierQualifieInfoBillList = new ArrayList<>();
		scmSupplierQualifieInfoBillList.add(scmSupplierQualifieInfoBill);
		bean.setList(scmSupplierQualifieInfoBillList);
		String processInstanceId = ActivityUtil.startProcessInstance(bean, billType.getId(), billType.getWorkFlowNo(), param);
		return processInstanceId;
	}
	
	private void sendAuditMsg(ScmSupplierQualifieInfoBill2 require,String activeType,String billCode,Param param) {
		String usrList= "";
		if(StringUtils.equals("A", activeType)) {
			usrList = ActivityUtil.findAssigneeByProcessInstanceId(require.getWorkFlowNo(),param);
		}else {
			usrList = require.getCreator();
		}
		if(StringUtils.isNotBlank(usrList)) {
			if(StringUtils.equals("A", activeType)) {
				scmBillPendingBiz.addPendingBill(usrList, require, param);
			}
			QualifieBillParams qualifieBillParams = new QualifieBillParams();
			qualifieBillParams.setBillNo(require.getBillNo());
			AuditMsgUtil.sendAuditMsg(billCode,activeType,require,qualifieBillParams, usrList, param);
		}
	}

	private ScmSupplierQualifieInfoBill2 updateStatus(ScmSupplierQualifieInfoBill2 scmSupplierQualifieInfoBill, Param param) throws AppException {
		if(scmSupplierQualifieInfoBill != null){
			ScmSupplierQualifieInfoBill2 scmSupplierQualifieInfoBill2 = this.update(scmSupplierQualifieInfoBill, param);
			if(scmSupplierQualifieInfoBill2 != null){
				scmSupplierQualifieInfoBillEntryBiz.updateRowStatusByBillId(scmSupplierQualifieInfoBill.getId(),
						scmSupplierQualifieInfoBill.getStatus(), scmSupplierQualifieInfoBill.getChecker(),
						scmSupplierQualifieInfoBill.getCheckDate(), param);
				return scmSupplierQualifieInfoBill2;
			}
		}
		return null;
	}
	
	@Override
	public ScmSupplierQualifieInfoBill2 doAuditQualifieInfo(CommonAuditParams commonAuditParams, Param param)
			throws AppException {
		ScmSupplierQualifieInfoBill2 order = null;
		
		ScmSupplierQualifieInfoBill2 scmSupplierQualifieInfoBill= new ScmSupplierQualifieInfoBill2();
		scmSupplierQualifieInfoBill.setId(commonAuditParams.getBillId());
		scmSupplierQualifieInfoBill.setBillNo(commonAuditParams.getBillNo());
		String opinion = commonAuditParams.getOpinion();
		if(StringUtils.equals("Y", opinion) || StringUtils.equals("agree", opinion)) {
			opinion="agree";
		}else if(StringUtils.equals("N", opinion) || StringUtils.equals("refuse", opinion)) {
			opinion="refuse";
		}
		List<ScmSupplierQualifieInfoBill2> scmSupplierQualifieInfoBillList = new ArrayList<> ();
		
		if(scmSupplierQualifieInfoBill.getId()>0){
			order = this.selectDirect(scmSupplierQualifieInfoBill.getId(), param);
		}else{
			HashMap<String,Object> map=new HashMap<String,Object>();
			map.put(
					ScmSupplierQualifieInfoBill2.FN_BILLNO,
					new QueryParam(ScmSupplierQualifieInfoBill2.FN_BILLNO, QueryParam.QUERY_EQ,
							scmSupplierQualifieInfoBill.getBillNo()));
			map.put(ScmSupplierQualifieInfoBill2.FN_CONTROLUNITNO, new QueryParam(ScmSupplierQualifieInfoBill2.FN_CONTROLUNITNO, QueryParam.QUERY_EQ,
							param.getControlUnitNo()));
			scmSupplierQualifieInfoBillList =this.findAll(map, param);
			if(scmSupplierQualifieInfoBillList!=null && !scmSupplierQualifieInfoBillList.isEmpty()){
				order=scmSupplierQualifieInfoBillList.get(0);
			}
		}
		
		if (order != null) {
			this.setConvertMap(order,param);
			BillType2 billType = billTypeBiz.selectByOrgAndCode(order.getControlUnitNo(), "ScmQualifieInfo", param);
			if(StringUtils.equals("R", opinion)) {
				scmBillPendingBiz.deletePendingBill(param.getUsrCode(),order, param);
				commonEventHistoryBiz.updateInvalid(order,"",param);
				
				CommonAuditOpinion commonAuditOpinionR = new CommonAuditOpinion();
				commonAuditOpinionR.setPreStepNo(order.getStepNo());
				String stepNo = StringUtils.replace(UUID.randomUUID().toString(), "-", "");
				commonAuditOpinionR.setStepNo(stepNo);
				commonAuditOpinionR.setActiveType("A");
				commonAuditOpinionR.setOpinion(commonAuditParams.getOpinion());
				commonAuditOpinionR.setRemarks(commonAuditParams.getOpinionRemarks());
				commonEventHistoryBiz.addEventHistory(order, commonAuditOpinionR, param);
				
				//驳回则将单据变回新单状态
				order.setStatus("R");
				order.setChecker(null);
				order.setCheckDate(null);
				this.updateStatus(order, param);
				this.sendAuditMsg(order,"R",billType.getBillCode(), param);
				return order;
			}
			String processInstanceId = order.getWorkFlowNo();
			boolean isCompleteTask = true;
			if (processInstanceId != null && !"".equals(processInstanceId)) {
				//完成审批任务
				ActivityUtil.completeTaskByAssigneeAndOpinion(processInstanceId, param.getUsrCode(), opinion,  param);
				ActivityUtil activityUtil = new ActivityUtil();
				//判断当前流程是否结束
				isCompleteTask = activityUtil.queryProInstanceStateByProInstanceId(processInstanceId);
			} else {
				//流程编号不存在
				throw new AppException("com.armitage.server.iscm.purchasemanage.purchasebusiness.service.impl.ScmPurRequireBizImpl.doAuditPurRequire.processInstanceId");
			}
			if ("agree".equals(opinion)) {
				if (isCompleteTask) {
					order.setStatus("A");
				}else {
					order.setStatus("P");
				}
			}else {
				order.setStatus("N");
			}
			scmBillPendingBiz.updatePendingBill(param.getUsrCode(), order, param);
			order.setChecker(param.getUsrCode());
			if ("agree".equals(opinion)) {
				String usrList= ActivityUtil.findAssigneeByProcessInstanceId(processInstanceId,param);
				if(StringUtils.isNotBlank(usrList)) {
					scmBillPendingBiz.addPendingBill(usrList, order, param);
					QualifieBillParams qualifieBillParams = new QualifieBillParams();
					qualifieBillParams.setBillNo(order.getBillNo());
					AuditMsgUtil.sendAuditMsg("ScmQualifieInfo",order,qualifieBillParams, usrList, param);
				}
			}
			CommonAuditOpinion commonAuditOpinion = new CommonAuditOpinion();
			commonAuditOpinion.setPreStepNo(order.getStepNo());
			String stepNo = StringUtils.replace(UUID.randomUUID().toString(), "-", "");
			try {
				order.setStepNo(stepNo);
				order.setCheckDate(CalendarUtil.getDate(param));
				this.updateStatus(order, param);
			} catch (Exception e) {
				throw e;
			}
			
			commonAuditOpinion.setStepNo(stepNo);
			commonAuditOpinion.setActiveType("A");
			commonAuditOpinion.setOpinion(commonAuditParams.getOpinion());
			commonAuditOpinion.setHandlerContent("");
			commonAuditOpinion.setRemarks(commonAuditParams.getOpinionRemarks());
			commonEventHistoryBiz.addEventHistory(order, commonAuditOpinion, param);
		} else {
			throw new AppException("iscm.purchasemanage.purchasebusiness.find.ordernotexists");
		}
		
		return order;
	}

	@Override
	public ScmSupplierQualifieInfoBill2 doUnAuditQualifieInfo(ScmSupplierQualifieInfoBill2 scmSupplierQualifieInfoBill,
			Param param) throws AppException {
		ScmSupplierQualifieInfoBill2 require = null;
		List<ScmSupplierQualifieInfoBill2> scmSupplierQualifieInfoBillList = new ArrayList<> ();
		List<ScmSupplierQualifieInfoBillEntry2> scmSupplierQualifieInfoBillEntryList = new ArrayList<> ();
		boolean isFirstTask = false;
		
		if(scmSupplierQualifieInfoBill.getId()>0){
			require = this.selectDirect(scmSupplierQualifieInfoBill.getId(), param);
		}else{
			HashMap<String,Object> map=new HashMap<String,Object>();
			map.put(
					ScmSupplierQualifieInfoBill2.FN_BILLNO,
					new QueryParam(ScmSupplierQualifieInfoBill2.FN_BILLNO, QueryParam.QUERY_EQ,
							scmSupplierQualifieInfoBill.getBillNo()));
			map.put(ScmSupplierQualifieInfoBill2.FN_CONTROLUNITNO, new QueryParam(ScmSupplierQualifieInfoBill2.FN_CONTROLUNITNO, QueryParam.QUERY_EQ,
					param.getControlUnitNo()));
			
			scmSupplierQualifieInfoBillList =this.findAll(map, param);
			if(scmSupplierQualifieInfoBillList!=null && !scmSupplierQualifieInfoBillList.isEmpty()){
				require=scmSupplierQualifieInfoBillList.get(0);
			}
		}
		
		if (require != null) {
			if(!StringUtils.contains("A,B,N,P", require.getStatus())){
				throw new AppException("iscm.purchasemanage.unaudit.error.status");
			}
			scmSupplierQualifieInfoBillEntryList = scmSupplierQualifieInfoBillEntryBiz.selectByBillId(require.getId(), param);
			
			if(scmSupplierQualifieInfoBillEntryList == null || scmSupplierQualifieInfoBillEntryList.isEmpty()){
				//抛出异常处理
				throw new AppException("iscm.inventorymanage.AllocationApplication.service.Impl.ScmInvMaterialRequestBillBizImpl.generateMaterialReqBill.notExitMaterialRequestBillDetail");
			}
			
			String processInstanceId = require.getWorkFlowNo();
			if (processInstanceId != null && !"".equals(processInstanceId)) {
				//反审批当前已审节点
				CommonBean bean = new CommonBean();
				scmSupplierQualifieInfoBillList.add(require);
				bean.setList(scmSupplierQualifieInfoBillList);
				bean.setList2(scmSupplierQualifieInfoBillEntryList);
				
				BillType2 billType = billTypeBiz.selectByOrgAndCode(require.getControlUnitNo(), "ScmQualifieInfo", param);
				long billTypeId = 0;
				if (billType!=null && billType.isNeedAudit()) {
					billTypeId = billType.getId();
				}
				
				processInstanceId = ActivityUtil.antiAuditProcess(bean, billTypeId, processInstanceId, param.getUsrCode(),  param);
				isFirstTask = ActivityUtil.isFirstTask(processInstanceId, param.getUsrCode(), param);
				require.setWorkFlowNo(processInstanceId);
			} else {
				//流程编号不存在
				throw new AppException("com.armitage.server.iscm.purchasemanage.purchasebusiness.service.impl.ScmPurRequireBizImpl.doAuditPurRequire.processInstanceId");
			}
			
			String tableName = ClassUtils.getFinalModelSimpleName(require);
			CommonEventHistory commonEventHistory = commonEventHistoryBiz.loadAuditEventHistory(tableName,require.getStepNo(),require.getPK(),param);
			if(commonEventHistory!=null && StringUtils.isNotBlank(commonEventHistory.getHandlerContent())) {
				List<CommonBillEntryStatus> commonBillEntryStatusList = gson.fromJson(commonEventHistory.getHandlerContent(),new TypeToken<List<CommonBillEntryStatus>>() {}.getType());
				if(commonBillEntryStatusList!=null && !commonBillEntryStatusList.isEmpty()) {
					for (ScmSupplierQualifieInfoBillEntry scmSupplierQualifieInfoBillEntry : scmSupplierQualifieInfoBillEntryList) {
						for(CommonBillEntryStatus commonBillEntryStatus:commonBillEntryStatusList) {
							if(commonBillEntryStatus.getLineId()==scmSupplierQualifieInfoBillEntry.getLineId()) {
								scmSupplierQualifieInfoBillEntry.setRowStatus(commonBillEntryStatus.getRowStatus());
								commonBillEntryStatusList.remove(commonBillEntryStatus);
								break;
							}
						}
					}
				}
				if(StringUtils.isNotBlank(commonEventHistory.getPreStepNo())){
					CommonEventHistory preCommonEventHistory = commonEventHistoryBiz.loadAuditEventHistory(tableName,require.getStepNo(),require.getPK(),param);
					if(preCommonEventHistory!=null) {
						require.setChecker(preCommonEventHistory.getOper());
						require.setCheckDate(preCommonEventHistory.getOperDate());
					}else {
						require.setChecker(null);
						require.setCheckDate(null);
					}
				}else {
					require.setChecker(null);
					require.setCheckDate(null);
				}
			}
			String status = "";
			if (isFirstTask) {
				status = "D";
			} else {
				status = "P";
			}
			require.setStatus(status);
		} else {
			throw new AppException("iscm.purchasemanage.purchasebusiness.find.ordernotexists");
		}
		
		try {
			this.updateStatus(require, param);
			scmQualifieInfoBiz.doUnAuditQualifieInfo(require,"W",param);
			scmBillPendingBiz.cancelPendingBill(param.getUsrCode(),require, param);
			commonEventHistoryBiz.updateInvalid(require,require.getStepNo(),param);
		} catch (Exception e) {
			throw e;
		}
		
		CommonAuditOpinion commonAuditOpinion = new CommonAuditOpinion();
		commonAuditOpinion.setStepNo(require.getStepNo());
		commonAuditOpinion.setActiveType("U");
		commonAuditOpinion.setOpinion("Y");
		commonEventHistoryBiz.addEventHistory(require, commonAuditOpinion, param);
		return require;
	}

	@Override
	public ScmSupplierQualifieInfoBill2 undoSubmitQualifieInfo(ScmSupplierQualifieInfoBill2 scmSupplierQualifieInfoBill,
			Param param) throws AppException {
		ScmSupplierQualifieInfoBill2 pruOrder = null;
		if(scmSupplierQualifieInfoBill.getId()>0){
			pruOrder = this.selectDirect(scmSupplierQualifieInfoBill.getId(), param);
		}
		if(pruOrder!=null){
			BillType2 billType = billTypeBiz.selectByOrgAndCode(pruOrder.getControlUnitNo(), "ScmQualifieInfo", param);
			boolean needAudit = false;
			if(billType!=null && billType.isNeedAudit() && StringUtils.isNotBlank(pruOrder.getWorkFlowNo()))
				needAudit = true;
			
			//单据设置审批流程，但设置没有审批节点，状态为通过，可以取消通过
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put(ScmBillPending.FN_BILLID, new QueryParam(ScmBillPending.FN_BILLID, QueryParam.QUERY_EQ, String.valueOf(pruOrder.getId())));
			map.put(ScmBillPending.FN_BILLTYPE, new QueryParam(ScmBillPending.FN_BILLTYPE, QueryParam.QUERY_EQ, billType.getBillCode()));
			List<ScmBillPending> scmBillPendingList = scmBillPendingBiz.findAll(map, param);
			
			if(needAudit){
				if (scmBillPendingList == null || scmBillPendingList.isEmpty()) {
					if(!StringUtils.equals(pruOrder.getStatus(),"A"))
						throw new AppException("iscm.purchasemanage.error.cancelCommit");
				} else {
					if(!StringUtils.equals(pruOrder.getStatus(),"D"))
						throw new AppException("iscm.purchasemanage.error.cancelCommit");
				}
			}else{
				if(!StringUtils.equals(pruOrder.getStatus(),"A"))
					throw new AppException("iscm.purchasemanage.error.cancelCommit");
			} 
			if(pruOrder.getStatus().equals("A") || pruOrder.getStatus().equals("D")){
				if(param.getUsrCode()!=null ){
					pruOrder.setChecker(null);
					pruOrder.setSubmitter(null);
				}
				pruOrder.setCheckDate(null);
				pruOrder.setSubmitDate(null);
				pruOrder.setStatus("I");
				try {
					this.updateStatus(pruOrder, param);
					scmBillPendingBiz.cancelPendingBill(param.getUsrCode(),pruOrder, param);
				} catch (Exception e) {
					throw e;
				}
			}
		}else{
			throw new AppException("iscm.purchasemanage.purchasebusiness.find.ordernotexists");
		}
		return pruOrder;
	}

	@Override
	public List<ScmSupplierQualifieInfoBill2> queryQualifieInfoBillList(ScmSupplierQualifieInfoBillAdvQuery scmSupplierQualifieInfoBillAdvQuery, int pageNum, Param param)
			throws AppException {
		Page page=new Page();
		page.setModelClass(ScmSupplierQualifieInfoBill2.class);
		if (pageNum == -1) {
			page.setPagePos(1);
			page.setShowCount(Integer.MAX_VALUE);
		} else {
			page.setPagePos(pageNum);
			page.setShowCount(20);
		}
		
		page.setModel(scmSupplierQualifieInfoBillAdvQuery);	//findPage时底层会触发doAdvQuery，增加查询条件
		
		List<ScmSupplierQualifieInfoBill2> scmSupplierQualifieInfoBillList = this.findPage(page, param);
		
		return scmSupplierQualifieInfoBillList;
	}

	@Override
	public void doAdvQuery(Page page, Param param) {
		if (page.getModel() != null) {
			if (page.getModel() instanceof ScmSupplierQualifieInfoBillAdvQuery) {
				ScmSupplierQualifieInfoBillAdvQuery scmSupplierQualifieInfoBillAdvQuery = (ScmSupplierQualifieInfoBillAdvQuery) page.getModel();
				if(StringUtils.isNotBlank(scmSupplierQualifieInfoBillAdvQuery.getBillNo())){
					page.getParam().put(ScmSupplierQualifieInfoBill2.FN_BILLNO,new QueryParam(ScmSupplierQualifieInfoBill2.FN_BILLNO, QueryParam.QUERY_EQ,scmSupplierQualifieInfoBillAdvQuery.getBillNo()));
				}
				if(scmSupplierQualifieInfoBillAdvQuery.getBizDateFrom()!=null){
					if(scmSupplierQualifieInfoBillAdvQuery.getBizDateTo()!=null) {
						page.getParam().put(ScmSupplierQualifieInfoBill2.FN_BIZDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmSupplierQualifieInfoBill2.class) + "." +ScmSupplierQualifieInfoBill2.FN_BIZDATE, QueryParam.QUERY_BETWEEN,FormatUtils.fmtDate(scmSupplierQualifieInfoBillAdvQuery.getBizDateFrom()),FormatUtils.fmtDate(CalendarUtil.relativeDate(scmSupplierQualifieInfoBillAdvQuery.getBizDateTo(),1))));
					}else {
						page.getParam().put(ScmSupplierQualifieInfoBill2.FN_BIZDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmSupplierQualifieInfoBill2.class) + "." +ScmSupplierQualifieInfoBill2.FN_BIZDATE, QueryParam.QUERY_GE,FormatUtils.fmtDate(scmSupplierQualifieInfoBillAdvQuery.getBizDateFrom())));
					}
				}else if(scmSupplierQualifieInfoBillAdvQuery.getBizDateTo()!=null) {
					page.getParam().put(ScmSupplierQualifieInfoBill2.FN_BIZDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmSupplierQualifieInfoBill2.class) + "." +ScmSupplierQualifieInfoBill2.FN_BIZDATE, QueryParam.QUERY_LE,FormatUtils.fmtDate(CalendarUtil.relativeDate(scmSupplierQualifieInfoBillAdvQuery.getBizDateTo(),1))));
				}
				if(scmSupplierQualifieInfoBillAdvQuery.getCreateDateFrom()!=null){
					if(scmSupplierQualifieInfoBillAdvQuery.getCreateDateTo()!=null) {
						page.getParam().put(ScmSupplierQualifieInfoBill2.FN_CREATEDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmSupplierQualifieInfoBill2.class) + "." +ScmSupplierQualifieInfoBill2.FN_CREATEDATE, QueryParam.QUERY_BETWEEN,FormatUtils.fmtDate(scmSupplierQualifieInfoBillAdvQuery.getCreateDateFrom()),FormatUtils.fmtDate(CalendarUtil.relativeDate(scmSupplierQualifieInfoBillAdvQuery.getCreateDateTo(),1))));
					}else {
						page.getParam().put(ScmSupplierQualifieInfoBill2.FN_CREATEDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmSupplierQualifieInfoBill2.class) + "." +ScmSupplierQualifieInfoBill2.FN_CREATEDATE, QueryParam.QUERY_GE,FormatUtils.fmtDate(scmSupplierQualifieInfoBillAdvQuery.getCreateDateFrom())));
					}
				}else if(scmSupplierQualifieInfoBillAdvQuery.getCreateDateTo()!=null) {
					page.getParam().put(ScmSupplierQualifieInfoBill2.FN_CREATEDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmSupplierQualifieInfoBill2.class) + "." +ScmSupplierQualifieInfoBill2.FN_CREATEDATE, QueryParam.QUERY_LE,FormatUtils.fmtDate(CalendarUtil.relativeDate(scmSupplierQualifieInfoBillAdvQuery.getCreateDateTo(),1))));
				}
				if(scmSupplierQualifieInfoBillAdvQuery.getVendorId() > 0){
					page.getParam().put(ScmSupplierQualifieInfoBill2.FN_VENDORID,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmSupplierQualifieInfoBill2.class) + "." +ScmSupplierQualifieInfoBill2.FN_VENDORID, QueryParam.QUERY_EQ,String.valueOf(scmSupplierQualifieInfoBillAdvQuery.getVendorId())));
				}else if(StringUtils.isNotBlank(scmSupplierQualifieInfoBillAdvQuery.getVendorCode())) {
					Scmsupplier2 scmsupplier = scmsupplierBiz.selectByCode(scmSupplierQualifieInfoBillAdvQuery.getVendorCode(), param);
					page.getParam().put(ScmSupplierQualifieInfoBill2.FN_VENDORID,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmSupplierQualifieInfoBill2.class) + "." +ScmSupplierQualifieInfoBill2.FN_VENDORID, QueryParam.QUERY_EQ,(scmsupplier==null?"0":String.valueOf(scmsupplier.getId()))));
				}
				if(StringUtils.isNotBlank(scmSupplierQualifieInfoBillAdvQuery.getStatus())){
					String status[] = StringUtils.split(scmSupplierQualifieInfoBillAdvQuery.getStatus(), ",");
					StringBuffer statusBuffer = new StringBuffer("");
					for(String sta:status){
						if(StringUtils.isNotBlank(statusBuffer.toString()))
							statusBuffer.append(",");
						statusBuffer.append("'").append(sta).append("'");
					}
					if(StringUtils.isNotBlank(statusBuffer.toString())){
						page.getParam().put(ScmSupplierQualifieInfoBill2.FN_STATUS,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmSupplierQualifieInfoBill2.class) + "." + ScmSupplierQualifieInfoBill2.FN_STATUS, QueryParam.QUERY_IN,statusBuffer.toString()));
					}
				}
			}
		}
	}

	@Override
	public ScmSupplierQualifieInfoBill2 queryQualifieInfoBill(ScmSupplierQualifieInfoBill2 scmSupplierQualifieInfoBill,
			Param param) throws AppException {
		Page page=new Page();
		page.setModelClass(ScmSupplierQualifieInfoBill2.class);
		page.setShowCount(Integer.MAX_VALUE);
		page.getParam().put(ScmSupplierQualifieInfoBill2.FN_BILLNO,new QueryParam(ScmSupplierQualifieInfoBill2.FN_BILLNO, QueryParam.QUERY_EQ,scmSupplierQualifieInfoBill.getBillNo()));
		
		List<ScmSupplierQualifieInfoBill2> scmSupplierQualifieInfoBillList =this.findPage(page, param);
		ScmSupplierQualifieInfoBill2 scmSupplierQualifieInfoBill2 = new ScmSupplierQualifieInfoBill2();
		if(scmSupplierQualifieInfoBillList!=null && !scmSupplierQualifieInfoBillList.isEmpty()){
			scmSupplierQualifieInfoBill2 = scmSupplierQualifieInfoBillList.get(0);
			setVenderInfo(scmSupplierQualifieInfoBill2,param);
			scmSupplierQualifieInfoBill2.setScmSupplierQualifieInfoBillEntryList(scmSupplierQualifieInfoBillEntryBiz.selectByBillId(scmSupplierQualifieInfoBill2.getId(), param));
			
			//增加待审人
			ScmBillPending2 scmBillPendingUsr = scmBillPendingBiz.selectPendingUsr(scmSupplierQualifieInfoBill2.getId(), "ScmQualifieInfo",param);
			if (scmBillPendingUsr != null) {
				scmSupplierQualifieInfoBill2.setPendingUsr(scmBillPendingUsr.getUsrCodes());
			}
		}else{
			throw new AppException("iscm.purchasemanage.pricemanage.find.ordernotexists");
		}
		return scmSupplierQualifieInfoBill2;
	}
	
	private void setVenderInfo(ScmSupplierQualifieInfoBill2 scmSupplierQualifieInfoBill,Param param) throws AppException{
		if(scmSupplierQualifieInfoBill.getVendorId()>0) {
			Scmsupplier2 scmsupplier = scmsupplierBiz.selectDirect(scmSupplierQualifieInfoBill.getVendorId(), param);
			if(scmsupplier!=null) {
				scmSupplierQualifieInfoBill.setVendorName(scmsupplier.getVendorName());
				scmSupplierQualifieInfoBill.setTaxNo(scmsupplier.getTaxNo());
				Scmsupplierpurchaseinfo2 scmsupplierpurchaseinfo = scmsupplierpurchaseinfoBiz.selectByVendorIdAndOrgUnitNo(scmSupplierQualifieInfoBill.getVendorId(), param.getControlUnitNo(), param);
				if(scmsupplierpurchaseinfo!=null) {
					scmSupplierQualifieInfoBill.setTaxRate(scmsupplierpurchaseinfo.getVatRate());
					if(StringUtils.isNotBlank(scmsupplierpurchaseinfo.getTaxpayerType())) {
						Code code = codeBiz.selectByCategoryAndCode("scmTaxpayerType", scmsupplierpurchaseinfo.getTaxpayerType());
						if(code!=null)
							scmSupplierQualifieInfoBill.setTaxpayerTypeName(code.getName());
					}
				}
			}
			List<Scmsupplierlinkman> scmsupplierlinkmanList = scmsupplierlinkmanBiz.selectByVendorId(scmSupplierQualifieInfoBill.getVendorId(), param);
			if(scmsupplierlinkmanList!=null && !scmsupplierlinkmanList.isEmpty()) {
				scmSupplierQualifieInfoBill.setContactPerson(scmsupplierlinkmanList.get(0).getContactPerson());
				scmSupplierQualifieInfoBill.setMobile((StringUtils.isBlank(scmsupplierlinkmanList.get(0).getMobile())?scmsupplierlinkmanList.get(0).getTel():scmsupplierlinkmanList.get(0).getMobile()));
			}
			Scmsuppliergroup scmsuppliergroup = scmsuppliergroupBiz.selectByVendorId(scmSupplierQualifieInfoBill.getVendorId(), param);
			if(scmsuppliergroup!=null)
				scmSupplierQualifieInfoBill.setClassName(scmsuppliergroup.getClassName());
		}
	}
}
