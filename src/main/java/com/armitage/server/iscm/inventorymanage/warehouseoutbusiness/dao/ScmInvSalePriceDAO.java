package com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.dao;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvSalePrice;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvSalePrice2;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmMaterialPrice;

public interface ScmInvSalePriceDAO extends BasicDAO<ScmInvSalePrice>{
	public ScmInvSalePrice selectMaxIdByDate(HashMap<String, Object> map) throws DAOException;

    public List<ScmInvSalePrice2> findByBizDateAndItemId(HashMap<String, Object> map) throws DAOException;

	public List<ScmMaterialPrice> getPrice(HashMap<String, Object> map) throws DAOException;

	public int undoReleaseCheck(HashMap<String, Object> map) throws DAOException;
}