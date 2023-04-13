package com.armitage.server.iscm.report.common.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.report.common.dao.ScmCommonReportDAO;
import com.armitage.server.iscm.report.common.model.ScmCommonReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmCommonReportDAO")
public class ScmCommonReportDAOImpl extends BasicDAOImpl<ScmCommonReport> implements ScmCommonReportDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}

	@Override
	public List<Map> queryCommonReportData(HashMap<String, Object> map, String xmlId) throws DAOException {
		try {
			return sqlSession.selectList(simpleName + "."+xmlId, map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.report.common.dao.impl.error.queryCommonReportData", e);
		}
	}
	
}
