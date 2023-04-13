package com.armitage.server.iscm.report.costcenter.dao;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.report.costcenter.model.ScmCostDirectTransfer;
import com.armitage.server.iscm.report.costcenter.model.ScmCostRealtimeStockSum;
import com.armitage.server.iscm.report.costcenter.model.ScmCostReport;
import com.armitage.server.iscm.report.costcenter.model.ScmCountingTaskSum;
import com.armitage.server.iscm.report.costcenter.model.ScmDeptConsume;

public interface ScmCostReportDAO  extends BasicDAO<ScmCostReport>{

	public List<ScmCostDirectTransfer> selectDirectTransDetails(HashMap<String, Object> map) throws DAOException;

  	public List<ScmCountingTaskSum> selectCostCenterInventory(HashMap<String, Object> map) throws DAOException;
  	
  	public List<ScmDeptConsume> selectDeptConsume(HashMap<String, Object> map) throws DAOException;
  	
  	public List<ScmDeptConsume> selectDeptSummaryConsume(HashMap<String, Object> map) throws DAOException;
  	
  	public List<ScmCostRealtimeStockSum> selectImmediateCostSum(HashMap<String, Object> map) throws DAOException;

	public List selectmovebillDetails(HashMap<String, Object> map) throws DAOException;
  	
 }
