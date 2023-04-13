
package com.armitage.server.iscm.basedata.dao;

import java.util.HashMap;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.basedata.model.ScmMaterialCompanyInfo;

public interface ScmMaterialCompanyInfoDAO extends BasicDAO<ScmMaterialCompanyInfo> {
	public ScmMaterialCompanyInfo selectByItemIdAndOrgUnitNo(HashMap<String, Object> map) throws DAOException;
}
