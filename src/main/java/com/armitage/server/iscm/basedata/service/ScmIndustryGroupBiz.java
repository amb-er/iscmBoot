package com.armitage.server.iscm.basedata.service;


import java.util.List;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.basedata.model.ScmIndustryGroup;

public interface ScmIndustryGroupBiz extends BaseBiz<ScmIndustryGroup> {
	public List<ScmIndustryGroup> selectByTypeId(long typeId, Param param) throws AppException;
	public ScmIndustryGroup selectByClassCode(String classCode, Param param) throws AppException;
}
