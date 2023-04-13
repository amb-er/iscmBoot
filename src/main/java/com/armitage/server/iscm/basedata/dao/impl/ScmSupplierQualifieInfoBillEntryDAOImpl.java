package com.armitage.server.iscm.basedata.dao.impl;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.basedata.dao.ScmSupplierQualifieInfoBillEntryDAO;
import com.armitage.server.iscm.basedata.model.ScmSupplierQualifieInfoBillEntry;
import com.armitage.server.iscm.basedata.model.ScmSupplierQualifieInfoBillEntry2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmSupplierQualifieInfoBillEntryDAO")
public class ScmSupplierQualifieInfoBillEntryDAOImpl extends BasicDAOImpl<ScmSupplierQualifieInfoBillEntry> implements ScmSupplierQualifieInfoBillEntryDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}


	@Override
	public List<ScmSupplierQualifieInfoBillEntry2> selectByBillId(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectByBillId", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.basedata.dao.impl.selectByBillId.error.selectByBillId");
		}
	}

}
