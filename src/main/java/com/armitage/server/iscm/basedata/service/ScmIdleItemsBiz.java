
package com.armitage.server.iscm.basedata.service;

import java.util.List;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.basedata.model.ScmIdleItems;
import com.armitage.server.iscm.basedata.model.ScmIdleItems2;

public interface ScmIdleItemsBiz extends BaseBiz<ScmIdleItems> {

	public List<ScmIdleItems> selectByCostCenterOrg(String orgUnitNo, String useOrgUnitNo, Param param) throws AppException;

	public List<ScmIdleItems2> selectIdleItemsByItems(String object, Param createParam) throws AppException;

	public List<ScmIdleItems2> selectIdleDrillData(Long object, Param createParam) throws AppException;

	public List<ScmIdleItems2> selectByIdleCauseId(long id, Param param) throws AppException;

	public int deleteZeroQty(String orgUnitNo, Param param) throws AppException;

}
