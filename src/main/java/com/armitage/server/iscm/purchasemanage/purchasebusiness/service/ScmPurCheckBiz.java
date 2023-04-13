package com.armitage.server.iscm.purchasemanage.purchasebusiness.service;


import java.util.List;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurCheck2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurOrder2;

public interface ScmPurCheckBiz extends BaseBiz<ScmPurCheck2> {

	public void confirm(List<ScmPurCheck2> scmPurCheckList, Param param) throws AppException;

	public void delByPurOrder(ScmPurOrder2 scmPurOrder, Param param) throws AppException;
	public List<ScmPurCheck2> selectByPoId(long poId, Param param) throws AppException;
	
	public List<String> unConfirm(List<ScmPurCheck2> scmPurCheckList, Param param) throws AppException;

	public List<ScmPurCheck2> selectBySaleIssueBill(long otId, Param param) throws AppException;

	public List<ScmPurCheck2> selectByOtherIssueBill(long otId, Param param) throws AppException;

	public ScmPurCheck2 updatePrtCount(ScmPurCheck2 scmPurCheck, Param param) throws AppException;
}

