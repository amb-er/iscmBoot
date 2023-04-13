
package com.armitage.server.iscm.basedata.service;

import java.util.List;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.basedata.model.ScmMaterialGroup;
import com.armitage.server.iscm.basedata.model.ScmMaterialGroup2;
import com.armitage.server.iscm.basedata.model.ScmMaterialGroupAdvQuery;

public interface ScmMaterialGroupBiz extends BaseBiz<ScmMaterialGroup> {
    public List<ScmMaterialGroup> findChild(long materialId, Param param) throws AppException;
    public ScmMaterialGroup selectByClassCode(String classCode,Param param) throws AppException;
	public List<ScmMaterialGroup> queryMaterialClassList(ScmMaterialGroupAdvQuery scmMaterialGroupAdvQuery,int pageNum, Param param) throws AppException;
	public ScmMaterialGroup selectByItemId(long itemId, Param param) throws AppException;
	public List<ScmMaterialGroup> findChild(String string, Param createParam) throws AppException;
	public List<ScmMaterialGroup2> queryDetailClassList(String finOrgUnitNo,Param param) throws AppException;
	public void updateCostType(List<ScmMaterialGroup> scmMaterialGroupList, Param param) throws AppException;
}
