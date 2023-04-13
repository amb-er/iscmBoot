package com.armitage.server.iscm.purchasemanage.purchasebusiness.service;

import java.util.List;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurReceive2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurReceiveAdvQuery;

public interface ScmDeptReceiveBiz extends BaseBiz<ScmPurReceive2> {
	public List<ScmPurReceive2> queryPurReceiveList(ScmPurReceiveAdvQuery scmPurReceiveAdvQuery, int pageNum, Param param) throws AppException;
}

