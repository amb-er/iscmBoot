package com.armitage.server.iscm.basedata.dao;

import java.util.HashMap;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.basedata.model.Scmsupplierpurchaseinfo;
import com.armitage.server.iscm.basedata.model.Scmsupplierpurchaseinfo2;

public interface ScmsupplierpurchaseinfoDAO extends BasicDAO<Scmsupplierpurchaseinfo> {
	public Scmsupplierpurchaseinfo2 selectByVendorIdAndOrgUnitNo(HashMap<String, Object> map) throws DAOException;
}
