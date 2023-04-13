package com.armitage.server.iscm.basedata.service.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.InterfaceParam;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.util.BeanUtil;
import com.armitage.server.iscm.basedata.dao.ScmSupplierQualifieInfoBillEntryDAO;
import com.armitage.server.iscm.basedata.model.ScmQualifieInfo2;
import com.armitage.server.iscm.basedata.model.ScmSupplierQualifieInfoBillEntry2;
import com.armitage.server.iscm.basedata.model.ScmSupplierQualifyType;
import com.armitage.server.iscm.basedata.service.ScmQualifieInfoBiz;
import com.armitage.server.iscm.basedata.service.ScmSupplierQualifieInfoBillEntryBiz;
import com.armitage.server.iscm.basedata.service.ScmSupplierQualifyTypeBiz;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmBaseAttachment;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmBaseAttachment2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.service.ScmBaseAttachmentBiz;
import com.armitage.server.mongodb.biz.MongoDBImageBiz;
import com.armitage.server.user.model.Usr;
import com.armitage.server.user.service.UsrBiz;
import org.springframework.stereotype.Service;

@Service("scmSupplierQualifieInfoBillEntryBiz")
public class ScmSupplierQualifieInfoBillEntryBizImpl extends BaseBizImpl<ScmSupplierQualifieInfoBillEntry2> implements ScmSupplierQualifieInfoBillEntryBiz {
	private ScmQualifieInfoBiz scmQualifieInfoBiz;
	private UsrBiz usrBiz;
	private ScmSupplierQualifyTypeBiz scmSupplierQualifyTypeBiz;
	private ScmBaseAttachmentBiz scmBaseAttachmentBiz;
	private MongoDBImageBiz mongoDBImageBiz;
	
	public void setScmQualifieInfoBiz(ScmQualifieInfoBiz scmQualifieInfoBiz) {
		this.scmQualifieInfoBiz = scmQualifieInfoBiz;
	}

	public void setUsrBiz(UsrBiz usrBiz) {
		this.usrBiz = usrBiz;
	}

	public void setScmSupplierQualifyTypeBiz(ScmSupplierQualifyTypeBiz scmSupplierQualifyTypeBiz) {
		this.scmSupplierQualifyTypeBiz = scmSupplierQualifyTypeBiz;
	}

	public void setScmBaseAttachmentBiz(ScmBaseAttachmentBiz scmBaseAttachmentBiz) {
		this.scmBaseAttachmentBiz = scmBaseAttachmentBiz;
	}

	public void setMongoDBImageBiz(MongoDBImageBiz mongoDBImageBiz) {
		this.mongoDBImageBiz = mongoDBImageBiz;
	}

	@Override
	public List<ScmSupplierQualifieInfoBillEntry2> selectByBillId(long billId, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("billId", billId);
		List<ScmSupplierQualifieInfoBillEntry2> scmSupplierQualifieInfoBillEntryList = ((ScmSupplierQualifieInfoBillEntryDAO)dao).selectByBillId(map);
		if(scmSupplierQualifieInfoBillEntryList!=null && !scmSupplierQualifieInfoBillEntryList.isEmpty()) {
			for(ScmSupplierQualifieInfoBillEntry2 scmSupplierQualifieInfoBillEntry:scmSupplierQualifieInfoBillEntryList) {
				if(scmSupplierQualifieInfoBillEntry.getTypeId()>0){
					ScmSupplierQualifyType scmSupplierQualifyType = scmSupplierQualifyTypeBiz.selectDirect(scmSupplierQualifieInfoBillEntry.getTypeId(), param);
					if(scmSupplierQualifyType!=null)
						scmSupplierQualifieInfoBillEntry.setTypeName(scmSupplierQualifyType.getName());
				}
				if(scmSupplierQualifieInfoBillEntry.getSourceBillDtlId()>0) {
					HashMap<String, Object> attachMap = new HashMap<String, Object>();
					attachMap.put("billId", scmSupplierQualifieInfoBillEntry.getSourceBillDtlId());
					attachMap.put("billtype", "ScmQualifieInfo");
					attachMap.put("controlUnitNo", param.getControlUnitNo());
					List<ScmBaseAttachment> scmBaseAttachmentList = scmBaseAttachmentBiz.findBybillTypeId(attachMap, param);
					if(scmBaseAttachmentList!=null && !scmBaseAttachmentList.isEmpty()) {
						List<ScmBaseAttachment2> rtnList = new ArrayList();
						for(ScmBaseAttachment scmBaseAttachment:scmBaseAttachmentList) {
							ScmBaseAttachment2 rnt = new ScmBaseAttachment2();
							BeanUtil.copyProperties(rnt, scmBaseAttachment);
							if(param instanceof InterfaceParam) {
								rnt.setPicData(String.valueOf(scmBaseAttachment.getId()));
							}
							rtnList.add(rnt);
						}
						scmSupplierQualifieInfoBillEntry.setScmBaseAttachmentList(rtnList);
					}
				}
			}
		}
		return scmSupplierQualifieInfoBillEntryList;
	}

	@Override
	public void updateRowStatusByBillId(long billId, String status,String checker,Date checkDate, Param param) throws AppException {
		List<ScmSupplierQualifieInfoBillEntry2> scmSupplierQualifieInfoBillEntryList = this.selectByBillId(billId, param);
		if(scmSupplierQualifieInfoBillEntryList!=null && !scmSupplierQualifieInfoBillEntryList.isEmpty()) {
			String checkerName = checker;
			if(StringUtils.isNotBlank(checker)) {
				Usr usr = usrBiz.selectByCode(checker, param);
				if(usr!=null)
					checkerName = usr.getName();
			}
			for(ScmSupplierQualifieInfoBillEntry2 scmSupplierQualifieInfoBillEntry:scmSupplierQualifieInfoBillEntryList) {
				scmSupplierQualifieInfoBillEntry.setChecker(checkerName);
				scmSupplierQualifieInfoBillEntry.setCheckDate(checkDate);
				scmSupplierQualifieInfoBillEntry.setRowStatus(status);
				this.update(scmSupplierQualifieInfoBillEntry, param);
			}
		}
	}

	@Override
	protected void afterUpdate(ScmSupplierQualifieInfoBillEntry2 oldEntity, ScmSupplierQualifieInfoBillEntry2 newEntity,
			Param param) throws AppException {
		if(oldEntity!=null && newEntity!=null && !StringUtils.equals(oldEntity.getRowStatus(), newEntity.getRowStatus())
				&& StringUtils.contains("A,N,R", newEntity.getRowStatus()) && newEntity.getSourceBillDtlId()>0 ) {
			ScmQualifieInfo2 scmQualifieInfo = scmQualifieInfoBiz.selectDirect(newEntity.getSourceBillDtlId(), param);
			if(scmQualifieInfo!=null) {
				scmQualifieInfo.setChecker(newEntity.getChecker());
				scmQualifieInfo.setCheckDate(newEntity.getCheckDate());
				scmQualifieInfo.setQualificationStatus(StringUtils.equals("A", newEntity.getRowStatus())?"O":(StringUtils.equals("R", newEntity.getRowStatus())?"RJ":"N"));
				scmQualifieInfoBiz.update(scmQualifieInfo,param);
			}
		}
	}

}
