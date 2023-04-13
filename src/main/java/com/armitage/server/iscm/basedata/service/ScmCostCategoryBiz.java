
package com.armitage.server.iscm.basedata.service;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.basedata.model.ScmCostCategory;

public interface ScmCostCategoryBiz extends BaseBiz<ScmCostCategory> {
	public ScmCostCategory selectByCode(String code,long excludeId, Param param) throws AppException;
}
