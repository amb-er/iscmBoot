package com.armitage.server.iscm.common.service.impl;

import java.util.HashMap;

import org.apache.commons.lang.StringUtils;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.common.dao.ScmSyncDataDAO;
import com.armitage.server.iscm.common.model.ScmSyncData;
import com.armitage.server.iscm.common.model.ScmSyncTaskInfo2;
import com.armitage.server.iscm.common.service.ScmSyncDataBiz;
import org.springframework.stereotype.Service;

@Service("scmSyncDataBiz")
public class ScmSyncDataBizImpl extends BaseBizImpl<ScmSyncData> implements ScmSyncDataBiz {

	@Override
	public ScmSyncData selectByScmSyncData(ScmSyncData scmSyncData, Param param) throws AppException {
		HashMap<String, Object> map = new HashMap<>();
		map.put("orgUnitNo", scmSyncData.getOrgUnitNo());
		map.put("billType", scmSyncData.getBillType());
		map.put("dataId", scmSyncData.getDataId());
		map.put("controlUnitNo", scmSyncData.getControlUnitNo());
		map.put("dataClass", scmSyncData.getDataClass());
		map.put("taskCode", scmSyncData.getTaskCode());
		return ((ScmSyncDataDAO) dao).selectByScmSyncData(map);
	}

	@Override
	public ScmSyncData addBySyncTaskInfo(ScmSyncTaskInfo2 scmSyncTaskInfo, Param param) throws AppException {
		ScmSyncData scmSyncData = new ScmSyncData();
		scmSyncData.setOrgUnitNo(scmSyncTaskInfo.getOrgUnitNo());
		switch (StringUtils.lowerCase(scmSyncTaskInfo.getTaskType())) {
	        case "purorderpush":{
	    		scmSyncData.setBillType("PurOrder");
	        	break;
	        }
	        case "invpurinwarehsbillpush":{
	    		scmSyncData.setBillType("InvPurInWhsBill");
	        	break;
	        }
	        case "supplierconfirmrulepush":{
	    		scmSyncData.setBillType("ConfirmRule");
	        	break;
	        }
		}
		scmSyncData.setSyncType("edit");
		scmSyncData.setSynchron("Y");	//根据任务生成标识时标记已处理
		scmSyncData.setDataClass(scmSyncTaskInfo.getTaskAction());
		scmSyncData.setDataId(scmSyncTaskInfo.getDataId());
		scmSyncData.setControlUnitNo(param.getControlUnitNo());
		scmSyncData.setTaskCode(scmSyncTaskInfo.getTaskCode());
		ScmSyncData scmSyncData2 = this.selectByScmSyncData(scmSyncData, param);
		if(scmSyncData2==null)
			scmSyncData2 = this.add(scmSyncData, param);
		return scmSyncData2;
	}

	@Override
	public void updateBillNoChangeTime(ScmSyncData scmSyncData, Param param) throws AppException {
		if(scmSyncData == null || scmSyncData.getId() <= 0){
			throw new AppException("argument.null", new String[] { "scmSyncData" });
		}
		ScmSyncData scmSyncData2 = this.selectDirect(scmSyncData.getId(), param);
		if(scmSyncData2 != null){
			((ScmSyncDataDAO)dao).updateBillNoChangeTime(scmSyncData);
		}
	}
		
}
