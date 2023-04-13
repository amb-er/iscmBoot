package com.armitage.server.iscm.common.service;


import java.util.List;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.common.model.CommonAuditOpinion;
import com.armitage.server.iscm.common.model.CommonEventHistory;
import com.armitage.server.iscm.common.model.CommonEventHistory2;

public interface CommonEventHistoryBiz extends BaseBiz<CommonEventHistory> {
	public void addEventHistory(BaseModel entity,CommonAuditOpinion commonAuditOpinion, Param param) throws AppException;
	public CommonEventHistory2 loadAuditEventHistory(String tableName, String stepNo,long billId,Param param) throws AppException;
	public List<CommonEventHistory2> loadAuditEventHistory(String tableName,long billId,boolean onlyAudit,Param param) throws AppException;
	public List<CommonEventHistory2> loadAuditStatus(String tableName,String billTypeCode,long billId,Param param) throws AppException;
	public List<CommonEventHistory2> loadAuditStatus(BaseModel entity,Param param) throws AppException;
	public void updateInvalid(BaseModel entity,String stepNo,Param param) throws AppException;
	public List<CommonEventHistory2> loadAuditStatus(String billTypeCode,String billCode,Param param) throws AppException;
}
