package com.armitage.server.iscm.inventorymanage.cstbusiness.dao.impl;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.inventorymanage.cstbusiness.dao.ScmCostBillDao;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmCostBill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmCostBillDao")
public class ScmCostBillDaoImpl extends BasicDAOImpl<ScmCostBill> implements ScmCostBillDao{

    @Override
    @Autowired
    @Resource(name = "sqlSessionIscm")
    public void setSqlSession(SqlSessionTemplate sqlSession){
        super.setSqlSession(sqlSession);
    }

	@Override
	public List<ScmCostBill> selectPostAndDateByItem(HashMap<String, Object> map) throws DAOException {
		try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".selectPostAndDateByItem",map);
        } catch (Exception e) {
            throw new DAOException("iscm.inventorymanage.cstbusiness.dao.impl.ScmCostBillDaoImpl.error.selectPostAndDateByItem", e);
        }
	}

}
