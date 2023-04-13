
package com.armitage.server.iscm.basedata.service;

import java.util.List;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.basedata.model.ScmBillPending;
import com.armitage.server.iscm.basedata.model.ScmBillPending2;
import com.armitage.server.iscm.basedata.model.ScmBillPendingAdvQuery;

public interface ScmBillPendingBiz extends BaseBiz<ScmBillPending> {
	public int checkExistPendingBill(String usrCode,Param param)throws AppException;
	public void addPendingBill(String usrCodes,BaseModel model,Param param)throws AppException;
	public void updatePendingBill(String usrCode,BaseModel model,Param param)throws AppException;
	public void cancelPendingBill(String usrCode,BaseModel model,Param param)throws AppException;
	public ScmBillPending selectByUsrCode(String usrCode, long billId, String billType, Param param)throws AppException;
	public ScmBillPending selectLastUsrPended(String usrCode, long billId, String billType, Param param)throws AppException;
	public ScmBillPending selectLastUsrPending(long billId, String billType, Param param)throws AppException;
	public ScmBillPending2 selectPendingUsr(long billId, String billType, Param param)throws AppException;
	public List<ScmBillPending2> queryPendingBill(ScmBillPendingAdvQuery scmBillPendingAdvQuery, int pageNum,Param param)throws AppException;
	public List<ScmBillPending2> queryPendingBillType(Param param)throws AppException;
	public void deletePendingBill(String usrCode,BaseModel model, Param param)throws AppException;
}
