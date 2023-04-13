
package com.armitage.server.iscm.inventorymanage.cstbusiness.dao.impl;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.inventorymanage.cstbusiness.dao.ScmInvCountingCostCenterProductDAO;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCountingCostCenterProduct;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCountingCostCenterProduct2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmIvCountingCostCenterProductDAO")
public class ScmInvCountingCostCenterProductDAOImpl extends BasicDAOImpl<ScmInvCountingCostCenterProduct> implements ScmInvCountingCostCenterProductDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}

	@Override
	public List<ScmInvCountingCostCenterProduct2> selectByTableId(HashMap<String, Object> map) throws DAOException {
		try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".selectByTableId", map);
        } catch (Exception e) {
            throw new DAOException("com.armitage.server.iscm.inventorymanage.cstbusiness.dao.impl.ScmInvCountingCostCenterProductDAOImpl.error.selectByTableId");
        }
	}

	@Override
	public void deleteByTableId(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			 sqlSession.delete(simpleName + ".deleteByTableId", map);
        } catch (Exception e) {
            throw new DAOException("com.armitage.server.iscm.inventorymanage.cstbusiness.dao.impl.ScmInvCountingCostCenterProductDAOImpl.error.deleteByTableId");
        }
	}

}
