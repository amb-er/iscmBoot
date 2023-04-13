package com.armitage.server.ifbc.report.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.ifbc.report.model.ScmDeptAndOutltProfit;
import com.armitage.server.ifbc.report.model.ScmDishSaleStructureAnalysis;
import com.armitage.server.ifbc.report.model.ScmFbcReport;
import com.armitage.server.iscm.report.inventory.model.ScmInvInWarehsItemSum;

public interface ScmFbcReportDAO  extends BasicDAO<ScmFbcReport>{

	public List<Map> queryFbcReportData(HashMap<String, Object> map,String xmlId) throws DAOException;
	
	public List<ScmDeptAndOutltProfit> selectOrgAndOutltProfit(HashMap<String, Object> map) throws DAOException;
	
	public List<ScmDishSaleStructureAnalysis> selectDishSaleStructureAnalysis(HashMap<String, Object> map) throws DAOException;
  	
 }
