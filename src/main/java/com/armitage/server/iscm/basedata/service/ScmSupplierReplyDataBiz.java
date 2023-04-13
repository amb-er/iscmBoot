package com.armitage.server.iscm.basedata.service;


import java.util.List;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.basedata.model.ScmSupplierReplyData;
import com.armitage.server.iscm.basedata.model.ScmSupplierReplyData2;

public interface ScmSupplierReplyDataBiz extends BaseBiz<ScmSupplierReplyData> {
	public ScmSupplierReplyData selectMaxUpdateTimeByCtrl(String controlUnitNo,Param param) throws AppException;
	
	public List<ScmSupplierReplyData2> selectPendingPushByCtrl(long dataId, String controlUnitNo,String updateTimestamp,Param param) throws AppException;
	
	public ScmSupplierReplyData selectByReplyDataId(long replyDataId,Param param) throws AppException;
}

