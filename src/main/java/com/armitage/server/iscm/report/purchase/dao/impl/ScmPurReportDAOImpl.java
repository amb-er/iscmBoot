package com.armitage.server.iscm.report.purchase.dao.impl;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.basedata.model.Scmsupplier2;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurPrice2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurOrder2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurRequire2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurRequireEntry2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurReturns2;
import com.armitage.server.iscm.report.purchase.dao.ScmPurReportDAO;
import com.armitage.server.iscm.report.purchase.model.ScmPurHistoryPrice;
import com.armitage.server.iscm.report.purchase.model.ScmPurOrderTransInfo;
import com.armitage.server.iscm.report.purchase.model.ScmPurOrderTransTotal;
import com.armitage.server.iscm.report.purchase.model.ScmPurReport;
import com.armitage.server.iscm.report.purchase.model.ScmPurReturnInfo;
import com.armitage.server.iscm.report.purchase.model.ScmPurSupplierAppraiseDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmPurReportDAO")
public class ScmPurReportDAOImpl extends BasicDAOImpl<ScmPurReport> implements ScmPurReportDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}

	/*供应商综合情况表*/
	public List<ScmPurOrder2> selectSupplierConsolidation(HashMap<String, Object> map) throws DAOException{
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName+".selectSupplierConsolidation",map);
		} catch (Exception e) {
			throw new DAOException("iscm.purchasemanage.purchasebusiness.dao.ScmPurOrderDAOImpl.error.selectSupplierConsolidation", e);
		}
	}
	
	//物资采购排行榜
	public List<ScmPurOrder2> selectMaterialProcurement(HashMap<String, Object> map) throws DAOException{
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName+".selectMaterialProcurement",map);
		} catch (Exception e) {
			throw new DAOException("iscm.purchasemanage.purchasebusiness.dao.ScmPurOrderDAOImpl.error.selectMaterialProcurement", e);
		}
	}
	
	//物资交易明细表（按供应商*库存组织）
	public List<ScmPurOrder2> selectMaterialTransDetails(HashMap<String, Object> map) throws Exception {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName+".selectMaterialTransDetails",map);
		} catch (Exception e) {
			throw new DAOException("iscm.purchasemanage.purchasebusiness.dao.ScmPurOrderDAOImpl.error.selectMaterialTransDetails", e);
		}
	}
	
	//供应商交易汇总表（退货按业务日期）
	@Override
	public List<ScmPurOrderTransTotal> selectSupTransSummary(HashMap<String, Object> map) throws Exception {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName+".selectSupTransSummary",map);
		} catch (Exception e) {
			throw new DAOException("iscm.purchasemanage.purchasebusiness.dao.ScmPurOrderDAOImpl.error.selectSupTransSummary", e);
		}
	}

	//供应商交易物资汇总表
	@Override
	public List<ScmPurOrderTransInfo> selectSupTransItemSummary(HashMap<String, Object> map) throws Exception {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName+".selectSupTransItemSummary",map);
		} catch (Exception e) {
			throw new DAOException("iscm.purchasemanage.purchasebusiness.dao.ScmPurOrderDAOImpl.error.selectSupTransItemSummary", e);
		}
	}
	
	@Override
    public List<ScmPurOrder2> selectPODueOrNot(HashMap<String, Object> map) throws DAOException {
        try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".selectPODueOrNot", map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("com.armitage.server.iscm.purchasemanage.purchasebusiness.dao.impl.ScmPurOrderDAOImpl.error.selectPODueOrNot");
        }
    }

	// 订货发货汇总表
    @Override
    public List<ScmPurRequire2> selectOrderDeliverySummary(HashMap<String, Object> map) {
        try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".selectOrderDeliverySummary", map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("com.armitage.server.iscm.purchasemanage.purchasebusiness.dao.impl.ScmPurRequireDAOImpl.error.selectOrderDeliverySummary");
        }
    }

    // 部门申购汇总表
    @Override
    public List<ScmPurRequire2> selectDeptApplySummary(HashMap<String, Object> map) {
        try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".selectDeptApplySummary", map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("com.armitage.server.iscm.purchasemanage.purchasebusiness.dao.impl.ScmPurRequireDAOImpl.error.selectDeptApplySummary");
        }
    }

    // 采购价格查询明细表
    @Override
    public List<ScmPurPrice2> selectPurPriceInfo(HashMap<String, Object> map)  throws DAOException{
        try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".selectPurPriceInfo", map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("com.armitage.server.iscm.purchasemanage.pricemanage.dao.impl.ScmPurPriceDAOImpl.error.selectPurPriceInfo");
        }
    }
    
    //采购价格信息核查
	@Override
	public List<ScmPurPrice2> selectPurPriceInfoCheck(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectPurPriceInfoCheck", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.purchasemanage.pricemanage.dao.impl.ScmPurPriceDAOImpl.error.selectPurPriceInfoCheck");
		}
	}
	
	/*采购历史价格查询表*/
	@Override
	public List<ScmPurHistoryPrice> selectPurHistoryPrice(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectPurHistoryPrice",map);
		} catch (Exception e) {
			throw new DAOException("iscm.purchasemanage.purchasebusiness.dao.ScmPurOrderEntryDAOImpl.error.selectPurHistoryPrice", e);
		}
	} 
	
	/*供应商交易明细表*/
	@Override
	public List<ScmPurOrderTransInfo> selectSupplierDetails(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName+".selectSupplierDetails",map);
		} catch (Exception e) {
			throw new DAOException("iscm.purchasemanage.purchasebusiness.dao.ScmPurOrderEntryDAOImpl.error.selectSupplierDetails", e);
		}
	}
	
	/*供应商交易汇总表*/
	@Override
	public List<ScmPurOrderTransTotal> selectSupplierSummary(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName+".selectSupplierSummary",map);
		} catch (Exception e) {
			throw new DAOException("iscm.purchasemanage.purchasebusiness.dao.ScmPurOrderEntryDAOImpl.error.selectSupplierSummary", e);
		}
	}


	/* 供应商订货汇总表*/
	@Override
	public List<ScmPurRequireEntry2> selectSupplierOrderSummary(HashMap<String, Object> map) {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectSupplierOrderSummary",map);
		} catch (Exception e) {
			throw new DAOException("iscm.purchasemanage.purchasebusiness.dao.ScmPurRequireEntryDAOImpl.error.selectSupplierOrderSummary", e);
		}
	}

	/* 采购退货情况表*/
	@Override
	public List<ScmPurReturnInfo> selectPurchaseReturn(HashMap<String, Object> map) {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName+".selectPurchaseReturn",map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.purchasemanage.purchasebusiness.dao.impl.ScmPurReturnsDAOImpl.error.selectPurchaseReturn");
		}
	}
	
	/*供应商考核明细表*/
	@Override
	public List<ScmPurSupplierAppraiseDetails> selectScmPurSupplierAppraiseDetails(
			HashMap<String, Object> map) {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName+".selectScmPurSupplierAppraiseDetails",map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.report.purchase.dao.impl.error.selectScmPurSupplierAppraiseDetails");
		}
	}
	
}
