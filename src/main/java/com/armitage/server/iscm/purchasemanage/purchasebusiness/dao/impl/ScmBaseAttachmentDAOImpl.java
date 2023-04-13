
package com.armitage.server.iscm.purchasemanage.purchasebusiness.dao.impl;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.SqlSessionTemplate;
import org.apache.commons.collections.map.HashedMap;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.dao.ScmBaseAttachmentDAO;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmBaseAttachment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;


@Repository("scmBaseAttachmentDAO")
public class ScmBaseAttachmentDAOImpl extends BasicDAOImpl<ScmBaseAttachment> implements ScmBaseAttachmentDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}

	@Override
	public ScmBaseAttachment findBybillId(HashedMap hashedMap, Param param) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".findBybillId", hashedMap);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.purchasemanage.purchasebusiness.dao.impl.ScmPurCheckDAOImpl.error.findBybillId");
		}
	}

	@Override
	public List<ScmBaseAttachment> findBybillTypeId(HashMap<String, Object> hashedMap, Param param) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".findBybillTypeId", hashedMap);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.purchasemanage.purchasebusiness.dao.impl.ScmPurCheckDAOImpl.error.findBybillTypeId");
		}
	}

}
