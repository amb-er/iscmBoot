package com.armitage.server.iscm.basedata.dao;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.basedata.model.ScmMaterialCostCardTypeDetail;
import com.armitage.server.iscm.basedata.model.ScmMaterialCostCardTypeDetail2;

public interface ScmMaterialCostCardTypeDetailDAO extends BasicDAO<ScmMaterialCostCardTypeDetail> {

	public List<ScmMaterialCostCardTypeDetail2> selectByTypeId(HashMap<String, Object> map) throws DAOException;

}
