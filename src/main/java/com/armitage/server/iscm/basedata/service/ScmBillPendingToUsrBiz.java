
package com.armitage.server.iscm.basedata.service;

import java.util.List;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.basedata.model.ScmBillPendingToUsr;

public interface ScmBillPendingToUsrBiz extends BaseBiz<ScmBillPendingToUsr> {

	public void updateProcessed(long pendingId, Param param) throws AppException;

	public void updateUsrProcessed(long pendingId, String usrCode, Param param) throws AppException;

	public List<ScmBillPendingToUsr> selectByPendingId(long pendingId, Param param) throws AppException;

	public void cancelProcessed(long pendingId, Param param);

	public void cancelUsrProcessed(long pendingId, String usrCode, Param param);
}
