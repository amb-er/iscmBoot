package com.armitage.server.iscm.inventorymanage.cstbusiness.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmCstInitBill2;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmCstInitBillImPort;

public interface ScmCstInitBillBiz extends BaseBiz<ScmCstInitBill2> {
    public ScmCstInitBill2 submit(ScmCstInitBill2 scmCstInitBill,Param param) throws AppException;
    public ScmCstInitBill2 undoSubmit(ScmCstInitBill2 scmCstInitBill,Param param) throws AppException;
    public List<String> postBillCheck(ScmCstInitBill2 scmCstInitBill, Param param) throws AppException;
	public ScmCstInitBill2 postBill(ScmCstInitBill2 scmCstInitBill, Param param) throws AppException;
	public List<String> cancelPostBillCheck(ScmCstInitBill2 scmCstInitBill, Param param) throws AppException;
	public ScmCstInitBill2 cancelPostBill(ScmCstInitBill2 scmCstInitBill, Param param) throws AppException;

	public ScmCstInitBill2 importExcel(ScmCstInitBillImPort scmCstInitBillImPort, Param param) throws AppException;
	public ScmCstInitBill2 updatePrtCount(ScmCstInitBill2 scmCstInitBill, Param param) throws AppException;
	public List<Map<String, Object>> countUnPostBill(HashMap<String, Object> map) throws AppException;
}