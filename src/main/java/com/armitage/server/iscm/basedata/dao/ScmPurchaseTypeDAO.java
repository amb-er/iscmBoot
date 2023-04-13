
package com.armitage.server.iscm.basedata.dao;

import java.util.HashMap;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.basedata.model.ScmPurchaseType;
import com.armitage.server.iscm.basedata.model.ScmPurchaseType2;

public interface ScmPurchaseTypeDAO extends BasicDAO<ScmPurchaseType> {

	public ScmPurchaseType2 selectByCodeAncCtrl(HashMap<String, Object> map) throws AppException ;

}
