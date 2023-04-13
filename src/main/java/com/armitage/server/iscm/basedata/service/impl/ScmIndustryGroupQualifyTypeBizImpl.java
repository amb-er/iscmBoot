
package com.armitage.server.iscm.basedata.service.impl;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.basedata.dao.ScmIndustryGroupQualifyTypeDAO;
import com.armitage.server.iscm.basedata.model.ScmIndustryGroupQualifyType2;
import com.armitage.server.iscm.basedata.service.ScmIndustryGroupQualifyTypeBiz;
import org.springframework.stereotype.Service;

@Service("scmIndustryGroupQualifyTypeBiz")
public class ScmIndustryGroupQualifyTypeBizImpl extends BaseBizImpl<ScmIndustryGroupQualifyType2> implements ScmIndustryGroupQualifyTypeBiz {

	@Override
	public void deleteByClassId(long classId, Param param) throws AppException {
		if(classId > 0){
			List<ScmIndustryGroupQualifyType2> scmIndustryGroupQualifyTypeList = this.selectByClassId(classId,param);
			this.delete(scmIndustryGroupQualifyTypeList, param);
		}
	}

	@Override
	public List<ScmIndustryGroupQualifyType2> selectByClassId(long classId, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("classId", classId);
		return ((ScmIndustryGroupQualifyTypeDAO)dao).selectByClassId(map);
	}
}
