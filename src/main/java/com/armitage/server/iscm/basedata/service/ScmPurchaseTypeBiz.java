
package com.armitage.server.iscm.basedata.service;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.basedata.model.ScmPurchaseType2;

public interface ScmPurchaseTypeBiz extends BaseBiz<ScmPurchaseType2> {
	public ScmPurchaseType2 selectByCodeAncCtrl(String code,Param param) throws AppException ;
}
