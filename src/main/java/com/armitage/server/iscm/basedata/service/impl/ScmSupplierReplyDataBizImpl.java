package com.armitage.server.iscm.basedata.service.impl;


import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.basedata.dao.ScmSupplierReplyDataDAO;
import com.armitage.server.iscm.basedata.model.ScmSupplierReplyData;
import com.armitage.server.iscm.basedata.model.ScmSupplierReplyData2;
import com.armitage.server.iscm.basedata.service.ScmSupplierReplyDataBiz;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvPurInWarehsBill2;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.service.ScmInvPurInWarehsBillBiz;
import org.springframework.stereotype.Service;

@Service("scmSupplierReplyDataBiz")
public class ScmSupplierReplyDataBizImpl extends BaseBizImpl<ScmSupplierReplyData> implements ScmSupplierReplyDataBiz {

	private ScmInvPurInWarehsBillBiz scmInvPurInWarehsBillBiz;

	public void setScmInvPurInWarehsBillBiz(ScmInvPurInWarehsBillBiz scmInvPurInWarehsBillBiz) {
		this.scmInvPurInWarehsBillBiz = scmInvPurInWarehsBillBiz;
	}

	@Override
	public ScmSupplierReplyData selectMaxUpdateTimeByCtrl(String controlUnitNo, Param param) throws AppException {
		HashMap<String, Object> map = new HashMap<>();
		map.put("controlUnitNo", param.getControlUnitNo());
		return ((ScmSupplierReplyDataDAO) dao).selectMaxUpdateTimeByCtrl(map);
	}

	@Override
	public List<ScmSupplierReplyData2> selectPendingPushByCtrl(long dataId, String controlUnitNo,String updateTimestamp, Param param) throws AppException {
		HashMap<String, Object> map = new HashMap<>();
		map.put("controlUnitNo", param.getControlUnitNo());
		if(dataId > 0){
			map.put("dataId", dataId);
		}
		if(StringUtils.isNotEmpty(updateTimestamp)) {
			map.put("updateTime", updateTimestamp);
		}
		List<ScmSupplierReplyData2> scmSupplierReplyDataList =  ((ScmSupplierReplyDataDAO) dao).selectPendingPushByCtrl(map);
		if(scmSupplierReplyDataList != null && !scmSupplierReplyDataList.isEmpty()){
			for(ScmSupplierReplyData2 scmSupplierReplyData : scmSupplierReplyDataList){
				if(StringUtils.isNotBlank(scmSupplierReplyData.getConfirmStatus())){
					ScmInvPurInWarehsBill2 scmInvPurInWarehsBill = scmInvPurInWarehsBillBiz.selectByWrNo(scmSupplierReplyData.getRefBillNo(), param);
					if(scmInvPurInWarehsBill!=null) {
						scmSupplierReplyData.setConfirmStatus(scmInvPurInWarehsBill.getConfirmStatus());
					}
				}
			}
		}
		return scmSupplierReplyDataList;
	}

	@Override
	public ScmSupplierReplyData selectByReplyDataId(long replyDataId, Param param) throws AppException {
		if(replyDataId > 0){
			HashMap<String, Object> map = new HashMap<>();
			map.put("replyDataId", replyDataId);
			map.put("controlUnitNo", param.getControlUnitNo());
			return ((ScmSupplierReplyDataDAO) dao).selectByReplyDataId(map);
		}
		return null;
	}
	
	
}

