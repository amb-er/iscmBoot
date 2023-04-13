
package com.armitage.server.iscm.basedata.service;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.basedata.model.ScmIdleCause;

public interface ScmIdleCauseBiz extends BaseBiz<ScmIdleCause> {

	public ScmIdleCause selectByCode(String object, long object2, Param createParam) throws AppException;

}
