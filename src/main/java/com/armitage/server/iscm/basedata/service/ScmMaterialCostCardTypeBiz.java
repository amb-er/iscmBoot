
package com.armitage.server.iscm.basedata.service;

import java.util.List;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.ifbc.costcard.model.ScmMaterialOrGroupAdvQuery;
import com.armitage.server.iscm.basedata.model.ScmMaterialCostCardType2;

public interface ScmMaterialCostCardTypeBiz extends BaseBiz<ScmMaterialCostCardType2> {

	List<ScmMaterialCostCardType2> selectForCostCard(ScmMaterialOrGroupAdvQuery scmMaterialOrGroupAdvQuery, Param param) throws AppException;

}
