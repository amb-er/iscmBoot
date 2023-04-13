package com.armitage.server.iscm.purchasemanage.pricemanage.dao;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmMaterialPrePrice;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmMaterialPrice;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurPrice;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurPrice2;

public interface ScmPurPriceDAO extends BasicDAO<ScmPurPrice> {

	public ScmPurPrice2 selectMaxIdByDate(HashMap<String, Object> map) throws DAOException;
	
	public List<ScmMaterialPrice> getPrice(HashMap<String, Object> map)  throws DAOException;

	public ScmPurPrice2 getPrePrice(HashMap<String, Object> map)  throws DAOException;

	public ScmPurPrice2 getLastYearPrice(HashMap<String, Object> map)  throws DAOException;
	
	public ScmPurPrice2 selectByPmNo(HashMap<String, Object> map) throws DAOException;
	
	public void updateVendorPqData(HashMap<String, Object> map) throws DAOException;
	
	public List<ScmMaterialPrePrice> getPreMaterialPrice(HashMap<String, Object> map)  throws DAOException;
	
	public List<ScmMaterialPrice> getPreParePrice(HashMap<String, Object> map)  throws DAOException;
	
	public List<ScmMaterialPrice> getPreParePriceByVendorId(HashMap<String, Object> map)  throws DAOException;

	public List<ScmMaterialPrePrice> getPrePriceList(HashMap<String, Object> map) throws DAOException;

	public List<ScmMaterialPrice> getPreParePrice(HashMap<String, Object> map, Param param) throws DAOException;

	public List<ScmMaterialPrice> getPrice(HashMap<String, Object> map, Param param) throws DAOException;

	public List<ScmMaterialPrice> getMaterialPriceByItemidsAndVendorIdsList(HashMap<String, Object> map, Param param) throws DAOException;

	public List<ScmMaterialPrePrice> selectPrePrice(HashMap<String, Object> map, Param param)throws DAOException;

	public List selectPrePriceByVendor(HashMap<String, Object> map, Param createParam) throws DAOException;

	public List selectLastYearPriceByVendor(HashMap<String, Object> map, Param createParam) throws DAOException;

}
