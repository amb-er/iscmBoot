
package com.armitage.server.iscm.basedata.service;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.basedata.model.ScmPurchaseType2;
import com.armitage.server.iscm.basedata.model.ScmPurchaseTypeOrg;

public interface ScmPurchaseTypeOrgBiz extends BaseBiz<ScmPurchaseTypeOrg> {

	public void updateByPurchaseType(ScmPurchaseType2 scmPurchaseType, Param param) throws AppException ;
	public ScmPurchaseTypeOrg selectByTypeAndOrgNo(long typeId,String orgUnitNo,Param param) throws AppException ;
}
