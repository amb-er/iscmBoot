
package com.armitage.server.iscm.basedata.service.impl;

import java.util.HashMap;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Page;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.base.model.QueryParam;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.util.CalendarUtil;
import com.armitage.server.common.util.ClassUtils;
import com.armitage.server.iscm.basedata.dao.ScmQualifieInfoDAO;
import com.armitage.server.iscm.basedata.model.ScmQualifieInfo2;
import com.armitage.server.iscm.basedata.model.ScmSupplierQualifieInfoBill;
import com.armitage.server.iscm.basedata.model.ScmSupplierQualifyType;
import com.armitage.server.iscm.basedata.model.Scmsupplier2;
import com.armitage.server.iscm.basedata.service.ScmQualifieInfoBiz;
import com.armitage.server.iscm.basedata.service.ScmSupplierQualifyTypeBiz;
import com.armitage.server.iscm.basedata.service.ScmsupplierBiz;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmBaseAttachment;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.service.ScmBaseAttachmentBiz;
import com.armitage.server.quartz.util.SupplierPlatUtil;
import com.sun.mail.imap.protocol.Status;
import org.springframework.stereotype.Service;

@Service("scmQualifieInfoBiz")
public class ScmQualifieInfoBizImpl extends BaseBizImpl<ScmQualifieInfo2> implements ScmQualifieInfoBiz {
	
	private ScmsupplierBiz scmsupplierBiz;
	private ScmSupplierQualifyTypeBiz scmSupplierQualifyTypeBiz;
	private ScmBaseAttachmentBiz scmBaseAttachmentBiz;
	
	public void setScmsupplierBiz(ScmsupplierBiz scmsupplierBiz) {
		this.scmsupplierBiz = scmsupplierBiz;
	}
	public void setScmSupplierQualifyTypeBiz(ScmSupplierQualifyTypeBiz scmSupplierQualifyTypeBiz) {
		this.scmSupplierQualifyTypeBiz = scmSupplierQualifyTypeBiz;
	}
	
	public void setScmBaseAttachmentBiz(ScmBaseAttachmentBiz scmBaseAttachmentBiz) {
		this.scmBaseAttachmentBiz = scmBaseAttachmentBiz;
	}
	@Override
	protected void beforeFindPage(Page page, Param param) throws AppException {
		page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmQualifieInfo2.class) + "." + ScmQualifieInfo2.FN_CONTROLUNITNO), 
				new QueryParam((ClassUtils.getFinalModelSimpleName(ScmQualifieInfo2.class) + "." + ScmQualifieInfo2.FN_CONTROLUNITNO), QueryParam.QUERY_EQ, param.getControlUnitNo()));
	}
	
	
	@Override
	protected void afterSelect(ScmQualifieInfo2 entity, Param param) throws AppException {
		Scmsupplier2 scmsup = scmsupplierBiz.selectDirect(entity.getVendorId(), param);
		ScmSupplierQualifyType scmq = scmSupplierQualifyTypeBiz.selectDirect(entity.getTypeId(), param);
		if(scmsup != null){
			entity.setVendorStatus(scmsup.getStatus());
		}
		if(scmq != null){
			entity.setTypeName(scmq.getName());
			entity.setQualifieTypeCode(scmq.getCode());
		}
		setConvertMap(entity,param);
	}
	private void setConvertMap(ScmQualifieInfo2 scmQualifieInfo, Param param){
		if(scmQualifieInfo!=null) {
			if (scmQualifieInfo.getTypeId() > 0){
				ScmSupplierQualifyType scmSupplierQualifyType = scmSupplierQualifyTypeBiz.selectDirect(scmQualifieInfo.getTypeId(), param);
				if (scmSupplierQualifyType != null) {
					scmQualifieInfo.setConvertMap(ScmQualifieInfo2.FN_TYPEID, scmSupplierQualifyType);
				}
			}
		}
	}
	@Override
	public List<ScmQualifieInfo2> selectByVendorId(long vendorId, Param param) throws AppException {
		if(vendorId > 0){
			HashMap<String, Object> map = new HashMap<>();
			map.put("vendorId", vendorId);
			List<ScmQualifieInfo2> scmQualifieInfoList = ((ScmQualifieInfoDAO) dao).selectByVendorId(map);
			if(scmQualifieInfoList!=null && !scmQualifieInfoList.isEmpty()) {
				for(ScmQualifieInfo2 scmQualifieInfo:scmQualifieInfoList) {
					if(scmQualifieInfo.getTypeId()>0){
						ScmSupplierQualifyType scmSupplierQualifyType = scmSupplierQualifyTypeBiz.selectDirect(scmQualifieInfo.getTypeId(), param);
						if(scmSupplierQualifyType!=null)
							scmQualifieInfo.setTypeName(scmSupplierQualifyType.getName());
					}
					HashMap<String, Object> attachMap = new HashMap<String, Object>();
					attachMap.put("billId", scmQualifieInfo.getId());
					attachMap.put("billtype", "ScmQualifieInfo");
					attachMap.put("controlUnitNo", param.getControlUnitNo());
					scmQualifieInfo.setScmBaseAttachmentList(scmBaseAttachmentBiz.findBybillTypeId(attachMap, param));
				}
			}
			return scmQualifieInfoList;
		}
		return null;
	}
	
	@Override
	protected void beforeDelete(ScmQualifieInfo2 entity, Param param)
			throws AppException {
		SupplierPlatUtil supplierPlatUtil = new SupplierPlatUtil();
		supplierPlatUtil.changeSyncData(entity, param);
	}
	@Override
	public List<ScmQualifieInfo2> selectAttachByVendorId(long vendorId, Param param) throws AppException {
		if(vendorId > 0){
			HashMap<String, Object> map = new HashMap<>();
			map.put("vendorId", vendorId);
			return ((ScmQualifieInfoDAO) dao).selectAttachByVendorId(map);
		}
		return null;
	}
	@Override
	public void auditQualify(ScmQualifieInfo2 scmQualifieInfo, String vendorOrQualifieInfo, Param param) throws AppException {
		if(scmQualifieInfo != null){
			if("1".equals(vendorOrQualifieInfo)){
				ScmQualifieInfo2 scmQualifieInfo2 = this.selectDirect(scmQualifieInfo.getId(), param);
				if(scmQualifieInfo2 != null){
					scmQualifieInfo2.setQualifyAudit(true);
					scmQualifieInfo2.setEditor(param.getUsrName());
					scmQualifieInfo2.setEditDate(CalendarUtil.getDate(param));
					this.updateDirect(scmQualifieInfo2, param);
				}
			}else if("2".equals(vendorOrQualifieInfo)){
				Scmsupplier2 scmsupplier = scmsupplierBiz.selectDirect(scmQualifieInfo.getVendorId(), param);
				if(scmsupplier != null){
					scmsupplier.setQualificationStatus(scmQualifieInfo.getNewQualificationStatus());
					if(StringUtils.isNotBlank(scmQualifieInfo.getNewVendorStatus())){
						scmsupplier.setStatus(scmQualifieInfo.getNewVendorStatus());
					}
					scmsupplierBiz.update(scmsupplier, param);
					List<ScmQualifieInfo2> scmQualifieInfoList = this.selectByVendorId(scmsupplier.getId(), param);
					if(scmQualifieInfoList != null && !scmQualifieInfoList.isEmpty()){
						scmQualifieInfoList.get(0).setRemarks(scmQualifieInfo.getRemarks());
						for(ScmQualifieInfo2 tempScmQualifieInfo : scmQualifieInfoList){
							if(StringUtils.isNotBlank(scmQualifieInfo.getNewQualificationStatus())){
								if(!StringUtils.equalsIgnoreCase("O", scmQualifieInfo.getNewQualificationStatus())){
									tempScmQualifieInfo.setQualifyAudit(false);
								}
							}
							tempScmQualifieInfo.setEditor(param.getUsrName());
							tempScmQualifieInfo.setEditDate(CalendarUtil.getDate(param));
						}
						this.update(scmQualifieInfoList, param);
					}
				}
			}
		}
	}
	@Override
	public void deleteQualifyByVendorId(long vendorId, Param param) throws AppException {
		if(vendorId > 0){
			HashMap<String, Object> map = new HashMap<>();
			map.put("vendorId", vendorId);
			((ScmQualifieInfoDAO) dao).deleteQualifyByVendorId(map);
		}
	}
	@Override
	public void doUnAuditQualifieInfo(ScmSupplierQualifieInfoBill require,String status,  Param param) throws AppException {
		if (require != null) {
			HashMap<String, Object> map = new HashMap<>();
			map.put("vendorId", require.getVendorId());
			map.put("controlUnitNo", param.getControlUnitNo());
			map.put("status", status);
			((ScmQualifieInfoDAO) dao).doUnAuditQualifieInfo(map);
		}
	}
	@Override
	protected void afterDelete(ScmQualifieInfo2 entity, Param param) throws AppException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("billId", entity.getId());
		map.put("billtype", "ScmQualifieInfo");
		map.put("controlUnitNo", param.getControlUnitNo());
		List<ScmBaseAttachment> scmBaseAttachmentList = scmBaseAttachmentBiz.findBybillTypeId(map, param);
		if(scmBaseAttachmentList!=null && !scmBaseAttachmentList.isEmpty()) {
			scmBaseAttachmentBiz.delete(scmBaseAttachmentList, param);
		}
	}

}
