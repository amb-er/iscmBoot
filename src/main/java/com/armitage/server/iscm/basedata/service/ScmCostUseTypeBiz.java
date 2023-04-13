/**
 * 广州万迅电脑软件有限公司(c)
 * @author Promise
 * 2020年12月16日 下午4:55:34
 *
 */
package com.armitage.server.iscm.basedata.service;

import java.util.List;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.basedata.model.ScmCostUseType;

public interface ScmCostUseTypeBiz extends BaseBiz<ScmCostUseType>{
	public List<ScmCostUseType> selectAll(Param param) throws AppException;

	public List<ScmCostUseType> queryByNameOrCode(String object, Param createParam) throws AppException;
}

