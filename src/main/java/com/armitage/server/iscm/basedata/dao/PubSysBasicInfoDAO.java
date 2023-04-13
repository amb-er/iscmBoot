package com.armitage.server.iscm.basedata.dao;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.basedata.model.PubSysBasicInfo;

public interface PubSysBasicInfoDAO extends BasicDAO<PubSysBasicInfo> {
	public List<PubSysBasicInfo> selectTaxRate(HashMap<String, Object> map) throws DAOException;
}
