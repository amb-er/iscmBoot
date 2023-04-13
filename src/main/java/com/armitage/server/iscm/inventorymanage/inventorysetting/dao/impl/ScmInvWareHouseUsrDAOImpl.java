package com.armitage.server.iscm.inventorymanage.inventorysetting.dao.impl;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.inventorymanage.inventorysetting.dao.ScmInvWareHouseUsrDAO;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvWareHouseUsr;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvWareHouseUsr2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;


@Repository("scmInvWareHouseUsrDAO")
public class ScmInvWareHouseUsrDAOImpl extends BasicDAOImpl<ScmInvWareHouseUsr> implements ScmInvWareHouseUsrDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}

	@Override
	public List<ScmInvWareHouseUsr2> selectByWareHouseId(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectByWareHouseId",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorysetting.dao.impl.ScmInvWareHouseUsrDAOImpl.error.selectByWareHouseId", e);
		}
	}

	@Override
	public List<ScmInvWareHouseUsr2> selectByUsrCode(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectByUsrCode",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorysetting.dao.impl.ScmInvWareHouseUsrDAOImpl.error.selectByUsrCode", e);
		}
	}

}

