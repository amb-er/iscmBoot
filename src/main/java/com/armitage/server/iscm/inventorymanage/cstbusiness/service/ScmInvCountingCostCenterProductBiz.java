
package com.armitage.server.iscm.inventorymanage.cstbusiness.service;

import java.util.List;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCountingCostCenterProduct;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCountingCostCenterProduct2;

public interface ScmInvCountingCostCenterProductBiz extends BaseBiz<ScmInvCountingCostCenterProduct2> {

	List<ScmInvCountingCostCenterProduct2> selectByTableId(long tableId, Param param) throws AppException;

	void deleteByTableId(long tableId, Param param) throws AppException;

}
