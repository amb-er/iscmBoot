
package com.armitage.server.iscm.basedata.service.impl;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.basedata.dao.ScmBillPendingToUsrDAO;
import com.armitage.server.iscm.basedata.model.ScmBillPendingToUsr;
import com.armitage.server.iscm.basedata.service.ScmBillPendingToUsrBiz;
import org.springframework.stereotype.Service;

@Service("scmBillPendingToUsrBiz")
public class ScmBillPendingToUsrBizImpl extends BaseBizImpl<ScmBillPendingToUsr> implements ScmBillPendingToUsrBiz {

	@Override
	public void updateProcessed(long pendingId, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("pendingId", pendingId);
		((ScmBillPendingToUsrDAO)dao).updateProcessed(map);
	}

	@Override
	public void updateUsrProcessed(long pendingId, String usrCode, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("pendingId", pendingId);
		map.put("usrCode", usrCode);
		((ScmBillPendingToUsrDAO)dao).updateUsrProcessed(map);
	}

	@Override
	public List<ScmBillPendingToUsr> selectByPendingId(long pendingId, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("pendingId", pendingId);
		return ((ScmBillPendingToUsrDAO)dao).selectByPendingId(map);
	}

	@Override
	public void cancelProcessed(long pendingId, Param param) {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("pendingId", pendingId);
		((ScmBillPendingToUsrDAO)dao).cancelProcessed(map);
	}

	@Override
	public void cancelUsrProcessed(long pendingId, String usrCode, Param param) {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("pendingId", pendingId);
		map.put("usrCode", usrCode);
		((ScmBillPendingToUsrDAO)dao).cancelUsrProcessed(map);
	}

}
