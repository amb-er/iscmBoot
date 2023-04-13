package com.armitage.server.iscm.common.dao;

 
import java.util.List;

import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.common.model.CommonEventHistory;
import com.armitage.server.iscm.common.model.CommonEventHistory2;

public interface CommonEventHistoryDAO{ 
	public int saveEventHistory(String tableName,CommonEventHistory commonEventHistory) throws DAOException ;
	public CommonEventHistory2 loadAuditEventHistory(String tableName, String stepNo,long billId) throws DAOException;
	public List<CommonEventHistory2> loadAuditEventHistory(String tableName, long billId,boolean onlyAudit) throws DAOException;
	public void updateInvalid(String tableName, long billId, String stepNo) throws DAOException;
}
