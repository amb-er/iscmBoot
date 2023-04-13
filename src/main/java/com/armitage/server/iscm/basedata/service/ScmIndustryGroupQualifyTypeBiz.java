
package com.armitage.server.iscm.basedata.service;

import java.util.List;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.basedata.model.ScmIndustryGroupQualifyType2;

public interface ScmIndustryGroupQualifyTypeBiz extends BaseBiz<ScmIndustryGroupQualifyType2> {
	public List<ScmIndustryGroupQualifyType2> selectByClassId(long classId, Param param) throws AppException;

	public void deleteByClassId(long classId, Param param) throws AppException;
}
