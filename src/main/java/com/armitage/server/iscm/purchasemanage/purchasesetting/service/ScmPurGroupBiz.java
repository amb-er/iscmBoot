
package com.armitage.server.iscm.purchasemanage.purchasesetting.service;

import java.util.List;
import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurGroup;

public interface ScmPurGroupBiz extends BaseBiz<ScmPurGroup> {
	public List<ScmPurGroup> selectByPurGroupNo(ScmPurGroup scmPurGroup,Param param) throws AppException;
	
}
