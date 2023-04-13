
package com.armitage.server.iscm.basedata.dao;

import java.util.HashMap;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.basedata.model.ScmMaterialPurchase;
import com.armitage.server.iscm.basedata.model.ScmMaterialPurchase2;

public interface ScmMaterialPurchaseDAO extends BasicDAO<ScmMaterialPurchase> {
	public ScmMaterialPurchase2 selectByItemIdAndOrgUnitNo(HashMap<String, Object> map) throws DAOException;

	public void addOrUpdateMaterialPurchase(ScmMaterialPurchase scmMaterialPurchase2, Param param) throws DAOException;
}
