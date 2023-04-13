
package com.armitage.server.iscm.purchasemanage.purchasebusiness.service;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurREReuse;

public interface ScmPurREReuseBiz extends BaseBiz<ScmPurREReuse> {

	void cancelRefuse(String string, Param param) throws AppException;

	public ScmPurREReuse selectByEntryId(long prId) throws AppException;

	void deleteByEntryId(long id, Param param) throws AppException;

}
