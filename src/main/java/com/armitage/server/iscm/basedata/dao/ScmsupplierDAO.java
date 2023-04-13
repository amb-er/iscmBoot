package com.armitage.server.iscm.basedata.dao;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.basedata.model.Scmsupplier;
import com.armitage.server.iscm.basedata.model.Scmsupplier2;

public interface ScmsupplierDAO extends BasicDAO<Scmsupplier> {
	public Scmsupplier2 selectMaxId(HashMap<String, Object> map) throws DAOException;
	public List<Scmsupplier2> selectByIds(List<Integer> ids) throws DAOException;
	public int checkUse(long vendorId) throws DAOException;
	public Scmsupplier2 selectByCode(HashMap<String, Object> map) throws DAOException;
	public Scmsupplier2 selectByName(HashMap<String, Object> map) throws DAOException;
	public List<Scmsupplier2> selectByIdAndGroup(HashMap<String, Object> map) throws DAOException;
	public Scmsupplier2 findSameNameVendor(HashMap<String, Object> map) throws DAOException;
	public List<Scmsupplier2> selectByTaxNo(HashMap<String, Object> map) throws DAOException;
	public List<Scmsupplier2> selectByIndustryGroupId(HashMap<String, Object> map) throws DAOException;
	public List<Scmsupplier2> queryByInvStockVendor(HashMap<String, Object> map) throws DAOException;
	public Scmsupplier2 findByExternalCode(HashMap<String, Object> map) throws DAOException;
	public Scmsupplier2 queryByOrg(HashMap<String, Object> map) throws DAOException;
}
