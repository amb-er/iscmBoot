package com.armitage.server.iscm.purchasemanage.pricemanage.dao;

import java.util.HashMap;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurQuotationPlan;

public interface ScmPurQuotationPlanDAO extends BasicDAO<ScmPurQuotationPlan> {

	public ScmPurQuotationPlan selectMaxIdByDate(HashMap<String,Object> map) throws DAOException;

}
