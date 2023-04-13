package com.armitage.server.iscm.basedata.dao;

import java.util.HashMap;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.basedata.model.ScmSupplierQualifieInfoBill;
import com.armitage.server.iscm.basedata.model.ScmSupplierQualifieInfoBill2;

public interface ScmSupplierQualifieInfoBillDAO extends BasicDAO<ScmSupplierQualifieInfoBill> {

	public ScmSupplierQualifieInfoBill2 selectByVendorId(HashMap<String, Object> map) throws DAOException;

}
