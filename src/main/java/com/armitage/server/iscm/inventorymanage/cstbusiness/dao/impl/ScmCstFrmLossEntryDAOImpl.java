package com.armitage.server.iscm.inventorymanage.cstbusiness.dao.impl;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.inventorymanage.cstbusiness.dao.ScmCstFrmLossEntryDAO;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmCstFrmLossEntry;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmCstFrmLossEntry2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmCstFrmLossEntryDAO")
public class ScmCstFrmLossEntryDAOImpl extends BasicDAOImpl<ScmCstFrmLossEntry> implements ScmCstFrmLossEntryDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}

	@Override
	public List<ScmCstFrmLossEntry2> selectByBillId(HashMap<String, Object> map)
			throws DAOException {
		try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".selectByBillId",map);
        } catch (Exception e) {
            throw new DAOException("iscm.inventorymanage.cstbusiness.dao.impl.ScmCstFrmLossEntryDAOImpl.error.selectByBillId", e);
        }
	}

	@Override
	public void deleteByBillId(HashMap<String, Object> map) throws DAOException {
		try {
            sqlSession.clearCache();
            sqlSession.delete(simpleName + ".deleteByBillId", map);
        } catch (Exception e) {
            throw new DAOException("iscm.inventorymanage.cstbusiness.dao.impl.ScmCstFrmLossEntryDAOImpl.error.deleteByBillId", e);
        }
	}

	@Override
	public List<ScmCstFrmLossEntry2> selectOutEffectRow(
			HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectOutEffectRow",map);
		} catch (Exception e) {
			throw new DAOException("iscm.inventorymanage.cstbusiness.dao.impl.ScmCstFrmLossEntryDAOImpl.error.selectOutEffectRow", e);
		}
	}

}
