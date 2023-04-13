package com.armitage.server.iscm.basedata.dao;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.basedata.model.ScmMaterial;
import com.armitage.server.iscm.basedata.model.ScmMaterial2;

public interface ScmMaterialDAO extends BasicDAO<ScmMaterial> {
	public ScmMaterial2 selectMaxId(HashMap<String, Object> map) throws DAOException;
	public ScmMaterial2 selectByItemId(long itemId) throws DAOException;
	public List<ScmMaterial2> selectByOrgUnitNo(HashMap<String, Object> map) throws DAOException;
	public List<ScmMaterial2> findCountingMaterial(HashMap<String, Object> map) throws DAOException;
	public ScmMaterial2 selectByItemNo(HashMap<String, Object> map) throws DAOException;
	public List<ScmMaterial2> selectByCostOrgUnitNo(HashMap<String, Object> map) throws DAOException;
	public int checkUse(long itemId) throws DAOException;
	public int checkAllUse(long itemId) throws DAOException;
	public ScmMaterial2 findByInvItemId(HashMap<String, Object> map) throws DAOException;
	public ScmMaterial2 findByPurItemId(HashMap<String, Object> map) throws DAOException;
	public ScmMaterial2 selectByStock(HashMap<String, Object> map) throws DAOException;
	public ScmMaterial2 findSameNameMaterial(HashMap<String, Object> map) throws DAOException;
	public List<ScmMaterial2> findByFinAllList(HashMap<String, Object> map) throws DAOException;
	public ScmMaterial2 findByFinItemId(HashMap<String, Object> map) throws DAOException;
	public List<ScmMaterial2> findByPurItemIds(HashMap<String, Object> map) throws DAOException;
	public List<ScmMaterial2> findByInvItemIds(HashMap<String, Object> map) throws DAOException;
	public List<ScmMaterial2> checkUnitRelation(HashMap<String, Object> map) throws DAOException;
}
