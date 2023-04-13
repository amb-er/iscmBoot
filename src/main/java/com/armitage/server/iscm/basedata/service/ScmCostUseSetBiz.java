/**
 * 广州万迅电脑软件有限公司(c)
 * @author Promise
 * 2020年12月16日 下午5:55:20
 *
 */
package com.armitage.server.iscm.basedata.service;

import java.util.List;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.basedata.model.ScmCostUseSet2;
import com.armitage.server.iscm.basedata.model.ScmMaterialGroup2;

public interface ScmCostUseSetBiz extends BaseBiz<ScmCostUseSet2>{

	public void buildMap(ScmCostUseSet2 scmCostUseSet, List<ScmMaterialGroup2> scmMaterialGroupList, Param param) throws AppException;

	public List<ScmCostUseSet2> getScmCostUseSetByItemId(String itemIds, String costOrgUnitNo, Param param);

}

