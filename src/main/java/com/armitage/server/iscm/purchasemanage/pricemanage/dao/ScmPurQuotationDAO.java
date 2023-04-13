package com.armitage.server.iscm.purchasemanage.pricemanage.dao;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmMaterialPrice;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurQuotation;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurQuotation2;

public interface ScmPurQuotationDAO extends BasicDAO<ScmPurQuotation> {
	public ScmPurQuotation2 selectMaxIdByDate(HashMap<String, Object> map) throws DAOException;

	public List<ScmMaterialPrice> getPrice(HashMap<String, Object> map) throws DAOException;

	public List<ScmPurQuotation2> selectRecentPrice(HashMap<String, Object> map) throws DAOException;

	public List<ScmMaterialPrice> getLastPrice(HashMap<String, Object> map) throws DAOException;
	
	public List<ScmPurQuotation2> selectItemsRecentPrice(HashMap<String, Object> map) throws DAOException;

	public List<ScmMaterialPrice> getPriceByVendorIds(HashMap<String, Object> map) throws DAOException;
	
}
