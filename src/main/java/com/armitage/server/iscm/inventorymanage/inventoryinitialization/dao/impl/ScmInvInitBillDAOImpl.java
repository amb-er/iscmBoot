package com.armitage.server.iscm.inventorymanage.inventoryinitialization.dao.impl;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.inventorymanage.inventoryinitialization.dao.ScmInvInitBillDAO;
import com.armitage.server.iscm.inventorymanage.inventoryinitialization.model.ScmInvInitBill;
import com.armitage.server.iscm.inventorymanage.inventoryinitialization.model.ScmInvInitBill2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmInvInitBillDAO")
public class ScmInvInitBillDAOImpl extends BasicDAOImpl<ScmInvInitBill> implements ScmInvInitBillDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}

	@Override
	public ScmInvInitBill2 selectMaxIdByDate(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".selectMaxIdByDate", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.inventorymanage.inventoryinitialization.dao.impl.ScmInvInitBillDAOImpl.error.selectMaxIdByDate");
		}
	}

    @Override
    public List<ScmInvInitBill2> selectNotPost(HashMap<String, Object> map) throws DAOException {
        try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".selectNotPost",map);
        } catch (Exception e) {
            throw new DAOException("com.armitage.server.iscm.inventorymanage.inventoryinitialization.dao.impl.ScmInvInitBillDAOImpl.error.selectNotPost", e);
        }
    }

	@Override
	public int updatePostedStatus(HashMap<String, Object> map) throws DAOException {
		try {
			return sqlSession.update(simpleName + ".updatePostedStatus", map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.inventorymanage.inventoryinitialization.dao.impl.ScmInvInitBillDAOImpl.error.updatePostedStatus", e);
		}
	}
	
}
