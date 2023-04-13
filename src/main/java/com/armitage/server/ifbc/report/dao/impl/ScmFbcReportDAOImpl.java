package com.armitage.server.ifbc.report.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.ifbc.report.dao.ScmFbcReportDAO;
import com.armitage.server.ifbc.report.model.ScmDeptAndOutltProfit;
import com.armitage.server.ifbc.report.model.ScmDishSaleStructureAnalysis;
import com.armitage.server.ifbc.report.model.ScmFbcReport;
import com.armitage.server.iscm.report.inventory.model.ScmInvInWarehsItemSum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmFbcReportDAO")
public class ScmFbcReportDAOImpl extends BasicDAOImpl<ScmFbcReport> implements ScmFbcReportDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}

	@Override
	public List<Map> queryFbcReportData(HashMap<String, Object> map, String xmlId) throws DAOException {
		try {
			return sqlSession.selectList(simpleName + "."+xmlId, map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.ifbc.report.dao.impl.error.queryFbcReportData", e);
		}
	}
	
	@Override
	public List<ScmDeptAndOutltProfit> selectOrgAndOutltProfit(HashMap<String, Object> map) throws DAOException {
		try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".selectOrgAndOutltProfit", map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("com.armitage.server.ifbc.report.dao.impl.error.selectOrgAndOutltProfit");
        }
	}

	@Override
	public List<ScmDishSaleStructureAnalysis> selectDishSaleStructureAnalysis(HashMap<String, Object> map){
	try {
        sqlSession.clearCache();
        return sqlSession.selectList(simpleName + ".selectDishSaleStructureAnalysis", map);
    } catch (Exception e) {
        e.printStackTrace();
        throw new DAOException("com.armitage.server.ifbc.report.dao.impl.error.selectDishSaleStructureAnalysis");
    }
	}
}
