
package com.armitage.server.iscm.basedata.dao.impl;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.basedata.dao.ScmMaterialGroupDetailDAO;
import com.armitage.server.iscm.basedata.model.ScmMaterialGroupDetail;
import com.armitage.server.iscm.basedata.model.ScmMaterialGroupDetail2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmMaterialGroupDetailDAO")
public class ScmMaterialGroupDetailDAOImpl extends BasicDAOImpl<ScmMaterialGroupDetail> implements ScmMaterialGroupDetailDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}

	@Override
	public void deleteByItemIdOrGroupId(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			sqlSession.delete(simpleName + ".deleteByItemIdOrGroupId",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.basedata.dao.ScmMaterialGroupDetailDAOImpl.error.deleteByItemIdOrGroupId", e);
		}
		
	}

	@Override
	public List<ScmMaterialGroupDetail2> selectByItemId(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectByItemId",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.basedata.dao.ScmMaterialGroupDetailDAOImpl.error.selectByItemId", e);
		}
	}
	
}
