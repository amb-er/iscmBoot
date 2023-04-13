
package com.armitage.server.iscm.basedata.dao.impl;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.basedata.dao.ScmMaterialGroupStandardDAO;
import com.armitage.server.iscm.basedata.model.ScmMaterialGroupStandard;
import com.armitage.server.iscm.basedata.model.ScmMaterialGroupStandard2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmMaterialGroupStandardDAO")
public class ScmMaterialGroupStandardDAOImpl extends BasicDAOImpl<ScmMaterialGroupStandard> implements ScmMaterialGroupStandardDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}

	@Override
	public List<ScmMaterialGroupStandard2> selectSubsidiaryTypeByItem(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName+".selectSubsidiaryTypeByItem",map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.basedata.dao.impl.ScmMaterialGroupStandardDAOImpl.error.selectSubsidiaryTypeByItem",e);
		}
	}

}
