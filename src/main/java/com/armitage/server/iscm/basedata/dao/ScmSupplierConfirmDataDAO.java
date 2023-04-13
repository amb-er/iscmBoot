package com.armitage.server.iscm.basedata.dao;


import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.basedata.model.ScmSupplierConfirmData;
import com.armitage.server.iscm.basedata.model.ScmSupplierConfirmData2;

public interface ScmSupplierConfirmDataDAO extends BasicDAO<ScmSupplierConfirmData> {
	public ScmSupplierConfirmData2 selectByBcId(HashMap<String, Object> map) throws DAOException;
	public ScmSupplierConfirmData2 selectByBillNoAndType(HashMap<String, Object> map) throws DAOException;
	public void deleteListByBillNoAndType(HashMap<String, Object> map) throws DAOException;
	public List<ScmSupplierConfirmData2> selectByBillNoAndPurPrice(HashMap<String, Object> map) throws DAOException;
}

