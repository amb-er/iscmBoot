package com.armitage.server.iscm.basedata.dao;

import java.util.HashMap;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.basedata.model.Scmsuppliercompanyinfo;
import com.armitage.server.iscm.basedata.model.Scmsuppliercompanyinfo2;

public interface ScmsuppliercompanyinfoDAO extends BasicDAO<Scmsuppliercompanyinfo> {
	public Scmsuppliercompanyinfo2 selectByVendorIdAndOrgUnitNo(HashMap<String, Object> map) throws DAOException;
}

