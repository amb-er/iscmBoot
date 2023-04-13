package com.armitage.server.iscm.basedata.dao.impl;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.basedata.dao.ScmsuppliergroupDAO;
import com.armitage.server.iscm.basedata.model.Scmsuppliergroup;
import com.armitage.server.iscm.basedata.model.Scmsuppliergroup2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmsuppliergroupDAO")
public class ScmsuppliergroupDAOImpl extends BasicDAOImpl<Scmsuppliergroup> implements ScmsuppliergroupDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}

    @Override
    public List<Scmsuppliergroup2> findChild(HashMap<String, Object> map) throws DAOException {
        try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".findChild",map);
            
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("iscm.basedata.dao.impl.ScmsuppliergroupDAOImpl.error.findChild");
        }
    }

	@Override
	public Scmsuppliergroup2 selectByClassCode(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".selectByClassCode",map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("iscm.basedata.dao.impl.ScmsuppliergroupDAOImpl.error.selectByClassCode");
		}
	}

	@Override
	public Scmsuppliergroup2 selectByVendorId(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".selectByVendorId",map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("iscm.basedata.dao.impl.ScmsuppliergroupDAOImpl.error.selectByVendorId");
		}
	}

    
}

