
package com.armitage.server.iscm.basedata.service.impl;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Page;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.base.model.QueryParam;
import com.armitage.server.common.cache.ModelCacheMana;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.util.ClassUtils;
import com.armitage.server.iscm.basedata.dao.ScmMeasureUnitDAO;
import com.armitage.server.iscm.basedata.model.ScmMaterialUnitRelation2;
import com.armitage.server.iscm.basedata.model.ScmMeasureUnit;
import com.armitage.server.iscm.basedata.model.Scmsupplier2;
import com.armitage.server.iscm.basedata.service.ScmMaterialUnitRelationBiz;
import com.armitage.server.iscm.basedata.service.ScmMeasureUnitBiz;
import org.springframework.stereotype.Service;

@Service("scmMeasureUnitBiz")
public class ScmMeasureUnitBizImpl extends BaseBizImpl<ScmMeasureUnit> implements ScmMeasureUnitBiz {

	private ScmMaterialUnitRelationBiz scmMaterialUnitRelationBiz;
	
	public void setScmMaterialUnitRelationBiz(ScmMaterialUnitRelationBiz scmMaterialUnitRelationBiz) {
		this.scmMaterialUnitRelationBiz = scmMaterialUnitRelationBiz;
	}

	@Override
	protected void beforeFindPage(Page page, Param param) throws AppException {
		page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmMeasureUnit.class) + "." + ScmMeasureUnit.FN_CONTROLUNITNO), 
				new QueryParam((ClassUtils.getFinalModelSimpleName(ScmMeasureUnit.class) + "." + ScmMeasureUnit.FN_CONTROLUNITNO), QueryParam.QUERY_EQ, param.getControlUnitNo()));
	}
	
	@Override
	public boolean checkUnitUse(ScmMeasureUnit scmMeasureUnit, Param param) throws AppException {
		if(scmMeasureUnit != null && scmMeasureUnit.getId() > 0){
			List<ScmMaterialUnitRelation2> list = scmMaterialUnitRelationBiz.selectByItemOrUnit(0, scmMeasureUnit.getId(), param);
			if(list != null && !list.isEmpty()){
				return true;
			}
		}
		return false;
	}

	@Override
	public ScmMeasureUnit selectByUnitNo(String controlUnitNo, String unitNo,Param param) throws AppException {
		String key = controlUnitNo+"_"+unitNo;
		if (ModelCacheMana.keyExists(key, modelClassById == null ? modelClass : modelClassById)) {
			Object obj = ModelCacheMana.get(key, modelClassById == null ? modelClass : modelClassById);
			if (obj != null && obj.getClass().equals(modelClass)) {
				return (ScmMeasureUnit) obj;
			}
		}
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("controlUnitNo", controlUnitNo);
		map.put("unitNo", unitNo);
		return ((ScmMeasureUnitDAO)dao).selectByUnitNo(map);
	}
}
