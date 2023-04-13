package com.armitage.server.iscm.common.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.common.dao.ScmDataCollectionStepStateDAO;
import com.armitage.server.iscm.common.model.ScmDataCollectionStepState2;
import com.armitage.server.iscm.common.service.ScmDataCollectionStepStateBiz;
import org.springframework.stereotype.Service;

@Service("scmDataCollectionStepStateBiz")
public class ScmDataCollectionStepStateBizImpl extends BaseBizImpl<ScmDataCollectionStepState2> implements ScmDataCollectionStepStateBiz {
	public static final int ERRMAG_DEFAULT_LENGTH = 500;

	@Override
	public List<ScmDataCollectionStepState2> findAsynStep(String orgUnitNo,String category,String reqNo, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("orgUnitNo", orgUnitNo);
		map.put("category", category);
		map.put("reqNo", reqNo);
		return ((ScmDataCollectionStepStateDAO)dao).findAsynStep(map);
	}

	@Override
	public ScmDataCollectionStepState2 selectByStepIdAndOrgUnitNo(String orgUnitNo,String reqNo, long stepId, Param param)
			throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("orgUnitNo", orgUnitNo);
		map.put("stepId", stepId);
		map.put("reqNo", reqNo);
		return ((ScmDataCollectionStepStateDAO)dao).selectByStepIdAndOrgUnitNo(map);
	}

	@Override
	public synchronized ScmDataCollectionStepState2 updateByAsynProcessed(ScmDataCollectionStepState2 scmFbcRptData,
			String state, String msgInfo, Param param) throws AppException {
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			new AppException(e);
		}
		if(scmFbcRptData !=null){
			ScmDataCollectionStepState2 scmDataCollectionStepState = this.selectByStepIdAndOrgUnitNo(scmFbcRptData.getOrgUnitNo(),scmFbcRptData.getReqNo(),scmFbcRptData.getStepId(),param);
			if(scmDataCollectionStepState==null){
				scmDataCollectionStepState = new ScmDataCollectionStepState2(true);
				scmDataCollectionStepState.setOrgUnitNo(scmFbcRptData.getOrgUnitNo());
				scmDataCollectionStepState.setReqNo(scmFbcRptData.getReqNo());
				scmDataCollectionStepState.setStepId(scmFbcRptData.getStepId());
				scmDataCollectionStepState.setState(state);
				scmDataCollectionStepState.setLastRunTime(new Date());
				if(StringUtils.isNotBlank(msgInfo) && msgInfo.length()>ERRMAG_DEFAULT_LENGTH){
					msgInfo = StringUtils.substring(msgInfo, 0, ERRMAG_DEFAULT_LENGTH-1);
				}
				scmDataCollectionStepState.setMsgInfo(msgInfo);
				scmDataCollectionStepState = this.add(scmDataCollectionStepState, param);
				
			}else{
				scmDataCollectionStepState.setState(state);
				scmDataCollectionStepState.setLastRunTime(new Date());
				if(StringUtils.isNotBlank(msgInfo) && msgInfo.length()>ERRMAG_DEFAULT_LENGTH){
					msgInfo = StringUtils.substring(msgInfo, 0, ERRMAG_DEFAULT_LENGTH-1);
				}
				scmDataCollectionStepState.setMsgInfo(msgInfo);
				scmDataCollectionStepState = this.update(scmDataCollectionStepState, param);
			}
			return scmDataCollectionStepState;
		}
		return null;
	}
		
}
