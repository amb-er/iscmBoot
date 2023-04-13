
package com.armitage.server.iscm.purchasemanage.purchasesetting.service;

import java.util.List;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurBuyerOrg;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurBuyerOrg2;

public interface ScmPurBuyerOrgBiz extends BaseBiz<ScmPurBuyerOrg> {
	public List<ScmPurBuyerOrg2> selectByBuyerId(long buyerId, Param param) throws AppException;
}
