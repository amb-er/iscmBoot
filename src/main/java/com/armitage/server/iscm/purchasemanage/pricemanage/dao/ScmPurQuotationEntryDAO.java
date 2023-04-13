package com.armitage.server.iscm.purchasemanage.pricemanage.dao;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurQuotationEntry;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurQuotationEntry2;

public interface ScmPurQuotationEntryDAO extends BasicDAO<ScmPurQuotationEntry> {
	public List<ScmPurQuotationEntry2> selectByPqId(HashMap<String, Object> map) throws DAOException;
	public void updateRowStatusByPqId(HashMap<String, Object> map) throws DAOException;
	public ScmPurQuotationEntry2 selectTaxRateByPqId(HashMap<String, Object> hashMap) throws DAOException;
}
