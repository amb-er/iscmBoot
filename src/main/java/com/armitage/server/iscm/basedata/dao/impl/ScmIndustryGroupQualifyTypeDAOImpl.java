
package com.armitage.server.iscm.basedata.dao.impl;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.basedata.dao.ScmIndustryGroupQualifyTypeDAO;
import com.armitage.server.iscm.basedata.model.ScmIndustryGroupQualifyType;
import com.armitage.server.iscm.basedata.model.ScmIndustryGroupQualifyType2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmIndustryGroupQualifyTypeDAO")
public class ScmIndustryGroupQualifyTypeDAOImpl extends BasicDAOImpl<ScmIndustryGroupQualifyType> implements ScmIndustryGroupQualifyTypeDAO {

    @Override
    @Autowired
    @Resource(name = "sqlSessionIscm")
    public void setSqlSession(SqlSessionTemplate sqlSession){
        super.setSqlSession(sqlSession);
    }

	@Override
	public List<ScmIndustryGroupQualifyType2> selectByClassId(HashMap<String, Object> map) throws DAOException {
		try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".selectByClassId",map);
        } catch (Exception e) {
            throw new DAOException("com.armitage.server.iscm.basedata.dao.impl.ScmIndustryGroupQualifyTypeDAOImpl.error.selectByClassId", e);
        }
	}

}
