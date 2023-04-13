
package com.armitage.server.iscm.basedata.service;

import java.util.List;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.basedata.model.ScmMaterialGroupDetail;
import com.armitage.server.iscm.basedata.model.ScmMaterialGroupDetail2;
import com.armitage.server.iscm.basedata.model.ScmMaterialGroupStandard2;

public interface ScmMaterialGroupDetailBiz extends BaseBiz<ScmMaterialGroupDetail> {

	/**
	 * 根据itemId或groupId删除
	 * @param itemId
	 * @param groupId
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public void deleteByItemIdOrGroupId(long itemId, long groupId,Param param) throws AppException;
	/**
	 * 更新辅助分类
	 * @param itemId
	 * @param scmMaterialGroupStandardList
	 * @param param
	 * @throws AppException
	 */
	public void updateAuxiGroup(long itemId, List<ScmMaterialGroupStandard2> scmMaterialGroupStandardList,Param param) throws AppException;
	public List<ScmMaterialGroupDetail2> selectByItemId(long itemId,Param param) throws AppException;
}
