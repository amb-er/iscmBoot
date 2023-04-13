package com.armitage.server.iscm.basedata.dao.impl;


import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.basedata.dao.ScmSupplierReplyDataDAO;
import com.armitage.server.iscm.basedata.model.ScmSupplierReplyData;
import com.armitage.server.iscm.basedata.model.ScmSupplierReplyData2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmSupplierReplyDataDAO")
public class ScmSupplierReplyDataDAOImpl extends BasicDAOImpl<ScmSupplierReplyData> implements ScmSupplierReplyDataDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}

	@Override
	public ScmSupplierReplyData selectMaxUpdateTimeByCtrl(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".selectMaxUpdateTimeByCtrl",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.basedata.dao.impl.ScmSupplierReplyDataDAOImpl.error.selectMaxUpdateTimeByCtrl", e);
		}
	}

	@Override
	public List<ScmSupplierReplyData2> selectPendingPushByCtrl(HashMap<String, Object> map) throws DAOException {
		try{
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName+".selectPendingPushByCtrl",map);
		}catch(Exception e){
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.basedata.dao.impl.ScmSupplierReplyDataDAOImpl.error.selectPendingPushByCtrl");
		}
	}

	@Override
	public ScmSupplierReplyData selectByReplyDataId(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".selectByReplyDataId",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.basedata.dao.impl.ScmSupplierReplyDataDAOImpl.error.selectByReplyDataId", e);
		}
	}

}

