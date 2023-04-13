package com.armitage.server.iscm.inventorymanage.internaltrans.dao.impl;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.inventorymanage.internaltrans.dao.ScmInvSaleOrderEntryDAO;
import com.armitage.server.iscm.inventorymanage.internaltrans.model.ScmInvSaleOrderEntry;
import com.armitage.server.iscm.inventorymanage.internaltrans.model.ScmInvSaleOrderEntry2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmInvSaleOrderEntryDAO")
public class ScmInvSaleOrderEntryDAOImpl extends BasicDAOImpl<ScmInvSaleOrderEntry> implements ScmInvSaleOrderEntryDAO {

    @Override
    @Autowired
    @Resource(name = "sqlSessionIscm")
    public void setSqlSession(SqlSessionTemplate sqlSession){
        super.setSqlSession(sqlSession);
    }


    @Override
    public List<ScmInvSaleOrderEntry2> selectByOtId(HashMap<String, Object> map) throws DAOException {
        try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".selectByOtId",map);
        } catch (Exception e) {
            throw new DAOException("iscm.inventorymanage.internaltrans.dao.impl.ScmInvSaleOrderEntryDAOImpl.error.selectByPvId", e);
        }
    }

}
