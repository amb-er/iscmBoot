
package com.armitage.server.iscm.purchasemanage.pricemanage.dao.impl;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.purchasemanage.pricemanage.dao.ScmPurPriceDAO;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmMaterialPrePrice;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmMaterialPrice;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurPrice;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurPrice2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmPurPriceDAO")
public class ScmPurPriceDAOImpl extends BasicDAOImpl<ScmPurPrice> implements ScmPurPriceDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}

	@Override
	public ScmPurPrice2 selectMaxIdByDate(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".selectMaxIdByDate", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.purchasemanage.pricemanage.dao.impl.ScmPurPriceDAOImpl.error.selectMaxIdByDate");
		}
	}

	@Override
	public List<ScmMaterialPrice> getPrice(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".getPrice", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.purchasemanage.pricemanage.dao.impl.ScmPurPriceDAOImpl.error.getPrice");
		}
	}

	@Override
	public ScmPurPrice2 getPrePrice(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".getPrePrice", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.purchasemanage.pricemanage.dao.impl.ScmPurPriceDAOImpl.error.getPrePrice");
		}
	}

	@Override
	public ScmPurPrice2 getLastYearPrice(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".getLastYearPrice", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.purchasemanage.pricemanage.dao.impl.ScmPurPriceDAOImpl.error.getLastYearPrice");
		}
	}


	@Override
	public ScmPurPrice2 selectByPmNo(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".selectByPmNo", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.purchasemanage.pricemanage.dao.impl.ScmPurPriceDAOImpl.error.selectByPmNo");
		}
	}

	@Override
	public void updateVendorPqData(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.update(simpleName + ".updateVendorPqData", map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.purchasemanage.pricemanage.dao.impl.ScmPurPriceDAOImpl.error.updateVendorPqData");
		}
	}

	@Override
	public List<ScmMaterialPrePrice> getPreMaterialPrice(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".getPreMaterialPrice", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.purchasemanage.pricemanage.dao.impl.ScmPurPriceDAOImpl.error.getPreMaterialPrice");
		}
	}

	@Override
	public List<ScmMaterialPrice> getPreParePrice(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".getPreParePrice", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.purchasemanage.pricemanage.dao.impl.ScmPurPriceDAOImpl.error.getPreParePrice");
		}
	}

	@Override
	public List<ScmMaterialPrice> getPreParePriceByVendorId(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".getPreParePriceByVendorId", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.purchasemanage.pricemanage.dao.impl.ScmPurPriceDAOImpl.error.getPreParePriceByVendorId");
		}
	}

	@Override
	public List<ScmMaterialPrePrice> getPrePriceList(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".getPrePriceList", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.purchasemanage.pricemanage.dao.impl.ScmPurPriceDAOImpl.error.getPrePriceList");
		}
	}

	@Override
	public List<ScmMaterialPrice> getPreParePrice(HashMap<String, Object> map, Param param) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".getPreParePriceByVendor", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.purchasemanage.pricemanage.dao.impl.ScmPurPriceDAOImpl.error.getPreParePriceByVendor");
		}
	}

	@Override
	public List<ScmMaterialPrice> getPrice(HashMap<String, Object> map, Param param) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".getPriceByVendor", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.purchasemanage.pricemanage.dao.impl.ScmPurPriceDAOImpl.error.getPriceByVendor");
		}
	}

	@Override
	public List<ScmMaterialPrice> getMaterialPriceByItemidsAndVendorIdsList(HashMap<String, Object> map, Param param)
			throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".getMaterialPriceByItemidsAndVendorIdsList", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.purchasemanage.pricemanage.dao.impl.ScmPurPriceDAOImpl.error.getMaterialPriceByItemidsAndVendorIdsList");
		}
	}

	@Override
	public List<ScmMaterialPrePrice> selectPrePrice(HashMap<String, Object> map,Param param) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectPrePrice", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.purchasemanage.pricemanage.dao.impl.ScmPurPriceDAOImpl.error.selectPrePrice");
		}
	}

	@Override
	public List selectPrePriceByVendor(HashMap<String, Object> map, Param createParam) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectPrePriceByVendor", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.purchasemanage.pricemanage.dao.impl.ScmPurPriceDAOImpl.error.selectPrePriceByVendor");
		}
	}

	@Override
	public List selectLastYearPriceByVendor(HashMap<String, Object> map, Param createParam) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectLastYearPriceByVendor", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.purchasemanage.pricemanage.dao.impl.ScmPurPriceDAOImpl.error.selectLastYearPriceByVendor");
		}
	}
}
