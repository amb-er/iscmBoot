package com.armitage.server.iscm.basedata.dao.impl;


import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.basedata.dao.ScmsupplierDAO;
import com.armitage.server.iscm.basedata.model.Scmsupplier;
import com.armitage.server.iscm.basedata.model.Scmsupplier2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmsupplierDAO")
public class ScmsupplierDAOImpl extends BasicDAOImpl<Scmsupplier> implements ScmsupplierDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}


	@Override
	public Scmsupplier2 selectMaxId(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".selectMaxId",map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.basedata.dao.impl.ScmsupplierDAOImpl.error.selectMaxId");
		}
	}

	@Override
	public List<Scmsupplier2> selectByIds(List<Integer> ids) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".selectByIds",ids);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.basedata.dao.impl.ScmsupplierDAOImpl.error.selectByIds");
		}
	}

	@Override
	public int checkUse(long vendorId) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".checkUse", vendorId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.basedata.dao.impl.ScmsupplierDAOImpl.error.checkUse");
		}
	}

	@Override
	public Scmsupplier2 selectByCode(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".selectByCode", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.basedata.dao.impl.ScmsupplierDAOImpl.error.selectByCode");
		}
	}

	@Override
	public Scmsupplier2 selectByName(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".selectByName", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.basedata.dao.impl.ScmsupplierDAOImpl.error.selectByName");
		}
	}
	
	@Override
    public List<Scmsupplier2> selectByIdAndGroup(HashMap<String, Object> map) throws DAOException {
        try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".selectByIdAndGroup", map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("com.armitage.server.iscm.basedata.dao.impl.ScmsupplierDAOImpl.error.selectByIdAndGroup");
        }
    }

	@Override
	public Scmsupplier2 findSameNameVendor(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".findSameNameVendor", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.basedata.dao.impl.ScmsupplierDAOImpl.error.findSameNameVendor");
		}
	}

	@Override
	public List<Scmsupplier2> selectByTaxNo(HashMap<String, Object> map) throws DAOException {
		try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".selectByTaxNo", map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("com.armitage.server.iscm.basedata.dao.impl.ScmsupplierDAOImpl.error.selectByTaxNo");
        }
	}

	@Override
	public List<Scmsupplier2> selectByIndustryGroupId(HashMap<String, Object> map) throws DAOException {
		try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".selectByIndustryGroupId", map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("com.armitage.server.iscm.basedata.dao.impl.ScmsupplierDAOImpl.error.selectByIndustryGroupId");
        }
	}

	@Override
	public List<Scmsupplier2> queryByInvStockVendor(HashMap<String, Object> map) throws DAOException {
		try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".queryByInvStockVendor", map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("com.armitage.server.iscm.basedata.dao.impl.ScmsupplierDAOImpl.error.queryByInvStockVendor");
        }
	}

	@Override
	public Scmsupplier2 findByExternalCode(HashMap<String, Object> map) throws DAOException {
		try {
            sqlSession.clearCache();
            return sqlSession.selectOne(simpleName + ".findByExternalCode", map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("com.armitage.server.iscm.basedata.dao.impl.ScmsupplierDAOImpl.error.findByExternalCode");
        }
	}

	@Override
	public Scmsupplier2 queryByOrg(HashMap<String, Object> map) throws DAOException {
		try {
            sqlSession.clearCache();
            return sqlSession.selectOne(simpleName + ".queryByOrg", map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("com.armitage.server.iscm.basedata.dao.impl.ScmsupplierDAOImpl.error.queryByOrg");
        }
	}
}
