package com.armitage.server.iscm.report.costcenter.dao.impl;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.report.costcenter.dao.ScmCostReportDAO;
import com.armitage.server.iscm.report.costcenter.model.ScmCostDirectTransfer;
import com.armitage.server.iscm.report.costcenter.model.ScmCostRealtimeStockSum;
import com.armitage.server.iscm.report.costcenter.model.ScmCostReport;
import com.armitage.server.iscm.report.costcenter.model.ScmCountingTaskSum;
import com.armitage.server.iscm.report.costcenter.model.ScmDeptConsume;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmCostReportDAO")
public class ScmCostReportDAOImpl extends BasicDAOImpl<ScmCostReport> implements ScmCostReportDAO {

    @Override
    @Autowired
    @Resource(name = "sqlSessionIscm")
    public void setSqlSession(SqlSessionTemplate sqlSession){
        super.setSqlSession(sqlSession);
    }

	@Override
	public List<ScmCostDirectTransfer> selectDirectTransDetails(HashMap<String, Object> map) throws DAOException {
		try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".selectDirectTransDetails", map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("com.armitage.server.iscm.inventorymanage.warehouseinbusiness.dao.impl.ScmInvPurInWarehsBillDAOImpl.error.selectDirectTransDetails");
        }
	}
	
	@Override
	public List<ScmCountingTaskSum> selectCostCenterInventory(HashMap<String, Object> map) throws DAOException {
		try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".selectCostCenterInventory", map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("com.armitage.server.iscm.inventorymanage.cstbusiness.dao.impl.ScmInvCountingTaskDAOImpl.error.selectCostCenterInventory");
        }
	}

	@Override
	public List<ScmDeptConsume> selectDeptConsume(HashMap<String, Object> map) throws DAOException {
		try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".selectDeptConsume", map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("com.armitage.server.iscm.report.costcenter.dao.impl.ScmCostReportDAOImpl.error.selectDeptConsume");
        }
	}

	@Override
	public List<ScmCostRealtimeStockSum> selectImmediateCostSum(HashMap<String, Object> map) throws DAOException {
		try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".selectImmediateCostSum", map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("com.armitage.server.iscm.report.costcenter.dao.impl.ScmCostReportDAOImpl.error.selectImmediateCostSum");
        }
	}

	@Override
	public List selectmovebillDetails(HashMap<String, Object> map) throws DAOException {
		try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".scmmovebillDetails", map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("com.armitage.server.iscm.report.costcenter.dao.impl.ScmCostReportDAOImpl.error.selectmovebillDetails");
        }
	}

	@Override
	public List<ScmDeptConsume> selectDeptSummaryConsume(HashMap<String, Object> map) throws DAOException {
		try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".selectDeptSummaryConsumes", map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("com.armitage.server.iscm.report.costcenter.dao.impl.ScmCostReportDAOImpl.error.selectDeptSummaryConsume");
        }
	}
	
}
