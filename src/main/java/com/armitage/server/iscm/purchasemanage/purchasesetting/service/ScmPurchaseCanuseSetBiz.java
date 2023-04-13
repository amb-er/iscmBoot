
package com.armitage.server.iscm.purchasemanage.purchasesetting.service;

import java.util.List;
import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurRequire2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurRequireEntry2;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurchaseCanuseSet;

public interface ScmPurchaseCanuseSetBiz extends BaseBiz<ScmPurchaseCanuseSet> {

	public String checkDate(ScmPurRequire2 object,List<ScmPurRequireEntry2> scmPurRequireEntryList, Param param) throws AppException;

}
