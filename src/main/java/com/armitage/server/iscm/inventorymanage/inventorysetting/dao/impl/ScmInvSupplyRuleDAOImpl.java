
package com.armitage.server.iscm.inventorymanage.inventorysetting.dao.impl;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.basedata.model.ScmMaterial2;
import com.armitage.server.iscm.inventorymanage.inventorysetting.dao.ScmInvSupplyRuleDAO;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvSupplyRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmInvSupplyRuleDAO")
public class ScmInvSupplyRuleDAOImpl extends BasicDAOImpl<ScmInvSupplyRule> implements ScmInvSupplyRuleDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}

	@Override
	public List<ScmMaterial2> findAllBillQtyByItemIds(HashMap<String, Object> map) throws DAOException {
		// TODO Auto-generated method stub
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectAllBillQtyByItemIds",map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.basedata.dao.impl.ScmMaterialDAOImpl.error.findAllBillQtyByItemIds");
		}
	}

	@Override
	public List<Long> selectByOrgInv(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectByOrgInv",map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.basedata.dao.impl.ScmMaterialDAOImpl.error.findAllBillQtyByItemIds");
		}
	}
	
	@Override
	public String selectByIds(String value) throws DAOException{
		// DONE Auto-generated method stub
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".selectByIds",value);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.basedata.dao.impl.ScmMaterialDAOImpl.error.findByInvItemIds");
		}
	}
}
