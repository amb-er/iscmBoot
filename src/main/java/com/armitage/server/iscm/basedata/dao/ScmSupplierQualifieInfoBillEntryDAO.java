package com.armitage.server.iscm.basedata.dao;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.basedata.model.ScmSupplierQualifieInfoBillEntry;
import com.armitage.server.iscm.basedata.model.ScmSupplierQualifieInfoBillEntry2;

public interface ScmSupplierQualifieInfoBillEntryDAO extends BasicDAO<ScmSupplierQualifieInfoBillEntry> {

	public List<ScmSupplierQualifieInfoBillEntry2> selectByBillId(HashMap<String, Object> map) throws DAOException;

}
