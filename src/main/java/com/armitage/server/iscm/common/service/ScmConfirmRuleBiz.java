package com.armitage.server.iscm.common.service;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.common.model.ScmConfirmRule;

public interface ScmConfirmRuleBiz extends BaseBiz<ScmConfirmRule> {
	public ScmConfirmRule selectByBillType(String billType,Param param) throws AppException;
}
