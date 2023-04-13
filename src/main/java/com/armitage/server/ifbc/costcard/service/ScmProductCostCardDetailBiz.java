
package com.armitage.server.ifbc.costcard.service;

import java.util.List;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.ifbc.costcard.model.ScmProductCostCardDetail2;

public interface ScmProductCostCardDetailBiz extends BaseBiz<ScmProductCostCardDetail2> {

	List<ScmProductCostCardDetail2> selectByCardId(long id,Param param);

	void deleteByCardId(long id);

}
