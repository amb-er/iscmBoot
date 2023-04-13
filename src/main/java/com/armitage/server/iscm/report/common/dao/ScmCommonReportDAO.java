package com.armitage.server.iscm.report.common.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.report.common.model.ScmCommonReport;

public interface ScmCommonReportDAO  extends BasicDAO<ScmCommonReport>{

	public List<Map> queryCommonReportData(HashMap<String, Object> map,String xmlId) throws DAOException;
  	
 }
