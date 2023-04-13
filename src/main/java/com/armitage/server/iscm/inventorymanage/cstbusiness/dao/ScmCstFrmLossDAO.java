package com.armitage.server.iscm.inventorymanage.cstbusiness.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmCstFrmLoss;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmCstFrmLoss2;

public interface ScmCstFrmLossDAO extends BasicDAO<ScmCstFrmLoss> {
	public int updatePostedStatus(HashMap<String, Object> map) throws DAOException;
	public List<ScmCstFrmLoss2> checkUnPostBill(HashMap<String, Object> map) throws DAOException;
	public List<ScmCstFrmLoss2> checkPostedBill(HashMap<String, Object> map) throws DAOException;

	public List<ScmCstFrmLoss2> checkCostCenterFree(long id) throws DAOException;
	public int checkMaterialFree(HashMap<String, Object> map) throws DAOException;
	
	public List<Map<String, Object>> countCostUnPostBill(HashMap<String, Object> map);
}
