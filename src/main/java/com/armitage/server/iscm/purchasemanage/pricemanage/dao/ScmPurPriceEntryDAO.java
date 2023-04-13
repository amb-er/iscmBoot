package com.armitage.server.iscm.purchasemanage.pricemanage.dao;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurPriceEntry;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurPriceEntry2;

public interface ScmPurPriceEntryDAO extends BasicDAO<ScmPurPriceEntry> {
	public List<ScmPurPriceEntry2> selectByPmId(HashMap<String, Object> map) throws DAOException;
	public void updateRowStatusByPmId(HashMap<String, Object> map) throws DAOException;
	public void updateVendorQuotation(HashMap<String, Object> map) throws DAOException;
	public ScmPurPriceEntry2 selectTaxRateByPmId(HashMap<String, Object> hashMap) throws DAOException;
}
