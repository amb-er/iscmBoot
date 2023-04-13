
package com.armitage.server.iscm.purchasemanage.purchasesetting.service;

import java.util.List;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurchaseCanuseSetMC2;

public interface ScmPurchaseCanuseSetMCBiz extends BaseBiz<ScmPurchaseCanuseSetMC2> {
	public List<ScmPurchaseCanuseSetMC2> selectByPcsId(long pcsId, Param param) throws AppException;

	public void deleteByPcsId(long pcsId, Param param) throws AppException;
}
