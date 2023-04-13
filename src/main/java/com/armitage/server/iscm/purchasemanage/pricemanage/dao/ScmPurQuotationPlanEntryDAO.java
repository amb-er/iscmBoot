package com.armitage.server.iscm.purchasemanage.pricemanage.dao;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurQuotationPlanEntry;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurQuotationPlanEntry2;

public interface ScmPurQuotationPlanEntryDAO extends BasicDAO<ScmPurQuotationPlanEntry> {
	public List<ScmPurQuotationPlanEntry2> selectByPlanId(HashMap<String, Object> map) throws DAOException;
	public List<ScmPurQuotationPlanEntry2> selectPurChasIngQuery(HashMap<String, Object> map) throws AppException;
	
}
