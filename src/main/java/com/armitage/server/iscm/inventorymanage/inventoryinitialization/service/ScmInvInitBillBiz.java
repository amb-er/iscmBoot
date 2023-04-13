package com.armitage.server.iscm.inventorymanage.inventoryinitialization.service;

import java.util.List;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.inventorymanage.inventoryinitialization.model.ScmInvInitBill2;
import com.armitage.server.iscm.inventorymanage.inventoryinitialization.model.ScmInvInitBillImPort;

public interface ScmInvInitBillBiz extends BaseBiz<ScmInvInitBill2>{
	public ScmInvInitBill2 submit(ScmInvInitBill2 scmInvInitBill, Param param) throws AppException;
	public ScmInvInitBill2 undoSubmit(ScmInvInitBill2 scmInvInitBill, Param param) throws AppException;

    public List<ScmInvInitBill2> selectNotPost(String orgUnitNo, long wareHouseId, Param param) throws AppException;
    
    public List<String> postBillCheck(ScmInvInitBill2 scmInvInitBill, Param param) throws AppException;
	public ScmInvInitBill2 postBill(ScmInvInitBill2 scmInvInitBill, Param param) throws AppException;
	public List<String> cancelPostBillCheck(ScmInvInitBill2 scmInvInitBill, Param param) throws AppException;
	public ScmInvInitBill2 cancelPostBill(ScmInvInitBill2 scmInvInitBill, Param param) throws AppException;

	public ScmInvInitBill2 importExcel(ScmInvInitBillImPort object, Param param) throws AppException;
	public ScmInvInitBill2 updatePrtCount(ScmInvInitBill2 scmInvInitBill, Param param) throws AppException;
	
}
