package com.armitage.server.iscm.report.inventory.dao.impl;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.basedata.model.ScmMaterial2;
import com.armitage.server.iscm.report.inventory.model.ScmInvStorageAgeAnalysis;
import com.armitage.server.iscm.report.inventory.dao.ScmInvReportDAO;
import com.armitage.server.iscm.report.inventory.model.ScmInvDepositorySumSup;
import com.armitage.server.iscm.report.inventory.model.ScmInvGlobalStock;
import com.armitage.server.iscm.report.inventory.model.ScmInvInWareMonthAnalysis;
import com.armitage.server.iscm.report.inventory.model.ScmInvInWarehsItemClass;
import com.armitage.server.iscm.report.inventory.model.ScmInvInWarehsItemClass2;
import com.armitage.server.iscm.report.inventory.model.ScmInvItemSaleSum;
import com.armitage.server.iscm.report.inventory.model.ScmInvItemWrDetails;
import com.armitage.server.iscm.report.inventory.model.ScmInvItemWrSum;
import com.armitage.server.iscm.report.inventory.model.ScmInvPurSalePrice;
import com.armitage.server.iscm.report.inventory.model.ScmInvRealtimeStock;
import com.armitage.server.iscm.report.inventory.model.ScmInvRealtimeStockSum;
import com.armitage.server.iscm.report.inventory.model.ScmInvReport;
import com.armitage.server.iscm.report.inventory.model.ScmInvSaleBusiness;
import com.armitage.server.iscm.report.inventory.model.ScmInvStorageAgePrimnessAnalysis;
import com.armitage.server.iscm.report.inventory.model.ScmPurVendorInfo;
import com.armitage.server.iscm.report.inventory.model.ScmVendorItemContrast;
import com.armitage.server.iscm.report.inventory.model.ScmInvInWarehsItemSum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmInvReportDAO")
public class ScmInvReportDAOImpl extends BasicDAOImpl<ScmInvReport> implements ScmInvReportDAO {

    @Override
    @Autowired
    @Resource(name = "sqlSessionIscm")
    public void setSqlSession(SqlSessionTemplate sqlSession){
        super.setSqlSession(sqlSession);
    }

	@Override
	public List<ScmInvRealtimeStock> selectRealtimeStock(HashMap<String, Object> map) throws DAOException {
        try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".selectRealtimeStock",map);
        } catch (Exception e) {
            throw new DAOException("com.armitage.server.iscm.report.inventory.dao.impl.ScmInvReportDAOImpl.error.selectRealtimeStock", e);
        }
	}

	@Override
    public List<ScmInvGlobalStock> selectGlobalInventory(HashMap<String, Object> map) throws DAOException {
        try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".selectGlobalInventory",map);
        } catch (Exception e) {
        	throw new DAOException("com.armitage.server.iscm.report.inventory.dao.impl.ScmInvReportDAOImpl.error.selectGlobalInventory", e);
        }
    }

	@Override
	public List<ScmInvRealtimeStockSum> selectImmediateInvSum(HashMap<String, Object> map) throws DAOException {
		try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".selectImmediateInvSum",map);
        } catch (Exception e) {
        	throw new DAOException("com.armitage.server.iscm.report.inventory.dao.impl.ScmInvReportDAOImpl.error.selectImmediateInvSum", e);
        }
	}
	
	@Override
    public List<ScmMaterial2> selectScmInOutSummary(HashMap<String, Object> map) {
        try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".selectScmInOutSummary",map);
        } catch (Exception e) {
            throw new DAOException("com.armitage.server.iscm.report.inventory.dao.impl.ScmInvReportDAOImpl.error.selectScmInOutSummary", e);
        }
    }

    @Override
    public List<ScmInvItemWrSum> selectScmInvItemWrSum(HashMap<String, Object> map) throws DAOException {
        try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".selectScmInvItemWrSum", map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("com.armitage.server.iscm.report.inventory.dao.impl.ScmInvReportDAOImpl.error.selectScmInvItemWrSum");
        }
    }

	@Override
	public List<ScmInvItemSaleSum> selectScmInvItemSaleSum(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName+".selectScmInvItemSaleSum",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.report.inventory.dao.impl.ScmInvReportDAOImpl.error.selectScmInvItemSaleSum");
		}
	}

	@Override
	public List<ScmInvInWarehsItemClass> selectScmInvInWarehsItemClass(
			HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName+".selectScmInvInWarehsItemClass",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.report.inventory.dao.impl.ScmInvReportDAOImpl.error.selectScmInvInWarehsItemClass");
		}
	}
	
	@Override
	public List<ScmInvInWarehsItemClass2> selectNewScmInvInWarehsItemClass(
			HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName+".selectNewScmInvInWarehsItemClass",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.report.inventory.dao.impl.ScmInvReportDAOImpl.error.selectNewScmInvInWarehsItemClass");
		}
	}

    @Override
    public List<ScmInvItemWrDetails> selectScmInvItemWrDetails(HashMap<String, Object> map) throws DAOException {
        try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".selectScmInvItemWrDetails", map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("com.armitage.server.iscm.report.inventory.dao.impl.ScmInvReportDAOImpl.error.selectScmInvItemWrDetails");
        }
    }

    @Override
    public List<ScmInvItemWrSum> selectScmInvConsignSumSup(HashMap<String, Object> map) throws DAOException {
        try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".selectScmInvConsignSumSup", map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("com.armitage.server.iscm.report.inventory.dao.impl.ScmInvReportDAOImpl.error.selectScmInvConsignSumSup");
        }
    }

	@Override
	public List<ScmInvStorageAgePrimnessAnalysis> selectScmInvStorageAgePrimnessAnalysis(
			HashMap<String, Object> map) throws DAOException {
		try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".selectScmInvStorageAgePrimnessAnalysis", map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("com.armitage.server.iscm.report.inventory.dao.impl.ScmInvReportDAOImpl.error.selectScmInvStorageAgePrimnessAnalysis");
        }
	}

	@Override
	public List<ScmInvDepositorySumSup> selectScmInvDepositorySumSup(
			HashMap<String, Object> map) throws DAOException {
		try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".selectScmInvDepositorySumSup", map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("com.armitage.server.iscm.report.inventory.dao.impl.ScmInvReportDAOImpl.error.selectScmInvDepositorySumSup");
        }
	}

	@Override
	public List<ScmInvStorageAgeAnalysis> selectScmInvStorageAgeAnalysis(
			HashMap<String, Object> map) throws DAOException {
		try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".selectScmInvStorageAgeAnalysis", map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("com.armitage.server.iscm.report.inventory.dao.impl.ScmInvReportDAOImpl.error.selectScmInvStorageAgeAnalysis");
        }
	}

	@Override
	public List<ScmInvInWareMonthAnalysis> selectScmInvInWareMonthAnalysis(HashMap<String, Object> map)
			throws DAOException {
		try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".selectScmInvInWareMonthAnalysis", map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("com.armitage.server.iscm.report.inventory.dao.impl.ScmInvReportDAOImpl.error.selectScmInvInWareMonthAnalysis");
        }
	}

	@Override
	public List<ScmInvInWareMonthAnalysis> selectScmInvSaleMonthAnalysis(HashMap<String, Object> map)
			throws DAOException {
		try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".selectScmInvSaleMonthAnalysis", map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("com.armitage.server.iscm.report.inventory.dao.impl.ScmInvReportDAOImpl.error.selectScmInvSaleMonthAnalysis");
        }
	}
	
	@Override
	public List<ScmInvInWarehsItemSum> selectScmInvInWarehsItemSum(HashMap<String, Object> map) throws DAOException {
		try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".selectScmInvInWarehsItemSum", map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("com.armitage.server.iscm.report.inventory.dao.impl.ScmInvReportDAOImpl.error.selectScmInvInWarehsItemSum");
        }
	}

	@Override
	public List<ScmInvInWarehsItemSum> selectScmInvSaleItemSum(HashMap<String, Object> map) throws DAOException {
		try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".selectScmInvSaleItemSum", map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("com.armitage.server.iscm.report.inventory.dao.impl.ScmInvReportDAOImpl.error.selectScmInvSaleItemSum");
        }
	}

	@Override
	public List<ScmInvSaleBusiness> selectScmInvSaleBusinessPurchase(HashMap<String, Object> map) throws DAOException {
		try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".selectScmInvSaleBusinessPurchase", map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("com.armitage.server.iscm.report.inventory.dao.impl.ScmInvReportDAOImpl.error.selectScmInvSaleBusinessPurchase");
        }
	}

	@Override
	public List<ScmInvSaleBusiness> selectScmInvSaleBusinessSale(HashMap<String, Object> map) throws DAOException {
		try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".selectScmInvSaleBusinessSale", map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("com.armitage.server.iscm.report.inventory.dao.impl.ScmInvReportDAOImpl.error.selectScmInvSaleBusinessSale");
        }
	}

	
	@Override
	public List<ScmInvPurSalePrice> selectScmInvPurSalePrice_Sale(HashMap<String, Object> map) throws DAOException {
		try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".selectScmInvPurSalePrice_Sale", map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("com.armitage.server.iscm.report.inventory.dao.impl.ScmInvReportDAOImpl.error.selectScmInvPurSalePrice_Sale");
        }
	}

	
	@Override
	public List<ScmInvPurSalePrice> selectScmInvPurSalePrice_Pur(HashMap<String, Object> map) throws DAOException {
		try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".selectScmInvPurSalePrice_Pur", map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("com.armitage.server.iscm.report.inventory.dao.impl.ScmInvReportDAOImpl.error.selectScmInvPurSalePrice_Pur");
        }
	}

	@Override
	public List<ScmVendorItemContrast> selectScmVendorItemContras(HashMap<String, Object> map) throws DAOException {
		try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".selectScmVendorItemContras", map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("com.armitage.server.iscm.report.inventory.dao.impl.ScmInvReportDAOImpl.error.selectScmVendorItemContras");
        }
	}

	@Override
	public List<ScmPurVendorInfo> selectScmPurVendorInfo(HashMap<String, Object> map) {
		try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".selectScmPurVendorInfo", map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("com.armitage.server.iscm.report.inventory.dao.impl.ScmInvReportDAOImpl.error.selectScmPurVendorInfo");
        }
	}
}
