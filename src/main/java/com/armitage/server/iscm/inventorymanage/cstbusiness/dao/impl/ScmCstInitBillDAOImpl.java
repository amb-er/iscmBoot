package com.armitage.server.iscm.inventorymanage.cstbusiness.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.inventorymanage.cstbusiness.dao.ScmCstInitBillDAO;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmCstInitBill;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmCstInitBill2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmCstInitBillDAO")
public class ScmCstInitBillDAOImpl extends BasicDAOImpl<ScmCstInitBill> implements ScmCstInitBillDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}

    @Override
    public ScmCstInitBill2 selectMaxIdByDate(HashMap<String, Object> map) throws DAOException {
        try {
            sqlSession.clearCache();
            return sqlSession.selectOne(simpleName + ".selectMaxIdByDate", map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("com.armitage.server.iscm.inventorymanage.cstbusiness.dao.impl.ScmCstInitBillDAOImpl.error.selectMaxIdByDate");
        }
    }

	@Override
	public int updatePostedStatus(HashMap<String, Object> map) throws DAOException {
		try {
			return sqlSession.update(simpleName + ".updatePostedStatus", map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.inventorymanage.cstbusiness.dao.impl.ScmCstInitBillDAOImpl.error.updatePostedStatus", e);
		}
	}

	@Override
	public List<Map<String, Object>> countUnPostBill(HashMap<String, Object> map) throws DAOException {
		try {
			return sqlSession.selectList(simpleName + ".countUnPostBill", map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.inventorymanage.cstbusiness.dao.impl.ScmCstInitBillDAOImpl.error.countUnPostBill", e);
		}
	}

}