
package com.armitage.server.iscm.basedata.service;

import java.util.List;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.basedata.model.ScmMaterial2;
import com.armitage.server.iscm.basedata.model.ScmMaterialGroupStandard;
import com.armitage.server.iscm.basedata.model.ScmMaterialGroupStandard2;

public interface ScmMaterialGroupStandardBiz extends BaseBiz<ScmMaterialGroupStandard> {
	public List<ScmMaterialGroupStandard2> selectSubsidiaryTypeByItem(ScmMaterial2 scmMaterial,Param param) throws AppException;
}
