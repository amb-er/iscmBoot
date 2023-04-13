package com.armitage.server.iscm.inventorymanage.internaltrans.dao.impl;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.inventorymanage.internaltrans.dao.ScmInvSaleOrderDAO;
import com.armitage.server.iscm.inventorymanage.internaltrans.model.ScmInvSaleOrder;
import com.armitage.server.iscm.inventorymanage.internaltrans.model.ScmInvSaleOrder2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmInvSaleOrderDAO")
public class ScmInvSaleOrderDAOImpl extends BasicDAOImpl<ScmInvSaleOrder> implements ScmInvSaleOrderDAO {

    @Override
    @Autowired
    @Resource(name = "sqlSessionIscm")
    public void setSqlSession(SqlSessionTemplate sqlSession){
        super.setSqlSession(sqlSession);
    }


    @Override
    public ScmInvSaleOrder selectMaxIdByDate(HashMap<String, Object> map) throws DAOException {
        try {
            sqlSession.clearCache();
            return sqlSession.selectOne(simpleName + ".selectMaxIdByDate", map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("com.armitage.server.iscm.inventorymanage.internaltrans.dao.impl.ScmInvSaleOrderDAOImpl.error.selectMaxIdByDate");
        }
    }

	@Override
	public List<ScmInvSaleOrder2> selectByPoId(HashMap<String, Object> map) throws DAOException {
		try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".selectByPoId", map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("com.armitage.server.iscm.inventorymanage.internaltrans.dao.impl.ScmInvSaleOrderDAOImpl.error.selectByPoId");
        }
	}

	@Override
	public List<ScmInvSaleOrder2> selectBySaleIssue(HashMap<String, Object> map) throws DAOException {
		try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".selectBySaleIssue", map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("com.armitage.server.iscm.inventorymanage.internaltrans.dao.impl.ScmInvSaleOrderDAOImpl.error.selectBySaleIssue");
        }
	}

}
