
package com.armitage.server.iscm.basedata.service;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.basedata.model.ScmMeasureUnit;

public interface ScmMeasureUnitBiz extends BaseBiz<ScmMeasureUnit> {
	public boolean checkUnitUse(ScmMeasureUnit scmMeasureUnit, Param param) throws AppException;
	public ScmMeasureUnit selectByUnitNo(String controlUnitNo,String unitNo, Param param) throws AppException;
}
