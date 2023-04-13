package com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.dao.impl;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.dao.ScmInvSalePriceDAO;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvSalePrice;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvSalePrice2;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmMaterialPrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmInvSalePriceDAO")
public class ScmInvSalePriceDAOImpl extends BasicDAOImpl<ScmInvSalePrice> implements ScmInvSalePriceDAO{

    @Override
    @Autowired
    @Resource(name = "sqlSessionIscm")
    public void setSqlSession(SqlSessionTemplate sqlSession){
        super.setSqlSession(sqlSession);
    }

	@Override
	public ScmInvSalePrice selectMaxIdByDate(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".selectMaxIdByDate", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.dao.impl.ScmInvSalePriceDaoImpl.error.selectMaxIdByDate");
		}
	}

    @Override
    public List<ScmInvSalePrice2> findByBizDateAndItemId(HashMap<String, Object> map) throws DAOException {
        try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".findByBizDateAndItemId", map);
        } catch (Exception e) {
            throw new DAOException("com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.dao.impl.ScmInvSalePriceDaoImpl.error.findByBizDateAndItemId", e);
        }
    }

	@Override
	public List<ScmMaterialPrice> getPrice(HashMap<String, Object> map)	throws DAOException {
		try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".getPrice", map);
        } catch (Exception e) {
            throw new DAOException("com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.dao.impl.ScmInvSalePriceDaoImpl.error.getPrice", e);
        }
	}

	@Override
	public int undoReleaseCheck(HashMap<String, Object> map) throws DAOException {
		try {
            sqlSession.clearCache();
            return sqlSession.selectOne(simpleName + ".undoReleaseCheck", map);
        } catch (Exception e) {
            throw new DAOException("com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.dao.impl.ScmInvSalePriceDaoImpl.error.undoReleaseCheck", e);
        }
	}
}