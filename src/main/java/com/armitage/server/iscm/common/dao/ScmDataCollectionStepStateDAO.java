package com.armitage.server.iscm.common.dao;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.common.model.ScmDataCollectionStepState;
import com.armitage.server.iscm.common.model.ScmDataCollectionStepState2;

public interface ScmDataCollectionStepStateDAO extends BasicDAO<ScmDataCollectionStepState> {

	public List<ScmDataCollectionStepState2> findAsynStep(HashMap<String, Object> map) throws DAOException;

	public ScmDataCollectionStepState2 selectByStepIdAndOrgUnitNo(HashMap<String, Object> map) throws DAOException;

}
