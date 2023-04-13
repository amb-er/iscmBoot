package com.armitage.server.iscm.purchasemanage.purchasebusiness.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.dao.ScmPurRequireDAO;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurBillDrillResult;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurRequire;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurRequire2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmPurRequireDAO")
public class ScmPurRequireDAOImpl extends BasicDAOImpl<ScmPurRequire> implements ScmPurRequireDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}
	
	@Override
	public ScmPurRequire2 selectMaxIdByDate(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".selectMaxIdByDate", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.purchasemanage.purchasebusiness.dao.impl.ScmPurRequireDAOImpl.error.selectMaxIdByDate");
		}
	}

	@Override
	public Map selectPurRequireTotalAmt(HashMap<String, Object> map) {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".selectPurRequireTotalAmt", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.purchasemanage.purchasebusiness.dao.impl.ScmPurRequireDAOImpl.error.selectPurRequireTotalAmt",e);
		}
	}

	@Override
	public int updateOutAudit(HashMap<String, Object> map) throws DAOException {
		try {
			return sqlSession.update(simpleName + ".updateOutAudit",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.purchasemanage.purchasebusiness.dao.impl.ScmPurRequireDAOImpl.error.updateOutAudit", e);
		}
	}

	
	@Override
	public List<ScmPurBillDrillResult> selectDrillBills(HashMap<String, Object> map) throws DAOException {
		try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".selectDrillBills", map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("com.armitage.server.iscm.purchasemanage.purchasebusiness.dao.impl.ScmPurRequireDAOImpl.error.selectDrillBills");
        }
	}

	@Override
	public List selectByIds(HashMap<String, Object> map) throws DAOException {
		try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".selectByIds", map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("com.armitage.server.iscm.purchasemanage.purchasebusiness.dao.impl.ScmPurRequireDAOImpl.error.selectByIds");
        }
	}

	@Override
	public ScmPurRequire2 selectByTypeCode(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".selectByTypeCode", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.purchasemanage.purchasebusiness.dao.impl.ScmPurRequireDAOImpl.error.selectByTypeCode",e);
		}
	}

	@Override
	public ScmPurRequire2 selectByPrNo(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".selectByPrNo", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.purchasemanage.purchasebusiness.dao.impl.ScmPurRequireDAOImpl.error.selectByPrNo",e);
		}
	}
}

