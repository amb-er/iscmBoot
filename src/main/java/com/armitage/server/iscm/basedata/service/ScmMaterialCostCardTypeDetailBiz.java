
package com.armitage.server.iscm.basedata.service;

import java.util.List;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.basedata.model.ScmMaterialCostCardTypeDetail2;

public interface ScmMaterialCostCardTypeDetailBiz extends BaseBiz<ScmMaterialCostCardTypeDetail2> {
	public List<ScmMaterialCostCardTypeDetail2> selectByTypeId(long typeId, Param param) throws AppException;

	public void deleteByTypeId(long typeId, Param param) throws AppException;

}
