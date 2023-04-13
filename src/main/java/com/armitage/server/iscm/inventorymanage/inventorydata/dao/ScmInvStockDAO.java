package com.armitage.server.iscm.inventorymanage.inventorydata.dao;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.inventorymanage.inventorydata.model.ScmFinDeptConsume;
import com.armitage.server.iscm.inventorymanage.inventorydata.model.ScmInvStock;
import com.armitage.server.iscm.inventorymanage.inventorydata.model.ScmInvStock2;

public interface ScmInvStockDAO extends BasicDAO<ScmInvStock> {
	public int addByOtherInWarehsBill(HashMap<String, Object> map) throws DAOException;
	public int updateByOtherInWarehsBill(HashMap<String, Object> map) throws DAOException;
	public int updateByCancelOtherInWarehsBill(HashMap<String, Object> map) throws DAOException;
	
	public int addByPurInWarehsBill(HashMap<String, Object> map) throws DAOException;
	public int updateByPurInWarehsBill(HashMap<String, Object> map) throws DAOException;
	public int updateByCancelPurInWarehsBill(HashMap<String, Object> map) throws DAOException;
	public int updateByPurInWarehsBillOut(HashMap<String, Object> map) throws DAOException;
	public int updateByCancelPurInWarehsBillOut(HashMap<String, Object> map) throws DAOException;
	
	public int addByMoveBillIn(HashMap<String, Object> map) throws DAOException;
    public int updateByMoveBillOutSub(HashMap<String, Object> map) throws DAOException;
    public int updateByMoveBillInPlus(HashMap<String, Object> map) throws DAOException;
    public int addByMoveBillOut(HashMap<String, Object> map) throws DAOException;
    public int updateByMoveBillOutPlus(HashMap<String, Object> map) throws DAOException;
    public int updateByMoveBillInSub(HashMap<String, Object> map) throws DAOException;

    
    public int updateBySaleIssuePostSub(HashMap<String, Object> map) throws DAOException;
    public int updateBySaleIssueUnPost(HashMap<String, Object> map) throws DAOException;
    public int updateBySaleIssueNotOffset(HashMap<String, Object> map) throws DAOException;
    public int addBySaleIssuePost(HashMap<String, Object> map) throws DAOException;

    
    //其他出库
    public int updateByOtherIssueOutSub(HashMap<String, Object> map) throws AppException;
    public int updateByOtherIssueInSub(HashMap<String, Object> map) throws AppException;

    public List<ScmInvStock2> findByDate(HashMap<String, Object> map) throws DAOException;
    public List<ScmInvStock2> findByOutWarehouse(HashMap<String, Object> map) throws DAOException;
    public List<ScmInvStock2> findByReturnWarehouse(HashMap<String, Object> map) throws DAOException;
    public List<ScmInvStock2> findByOutAndReturnWarehouse(HashMap<String, Object> map) throws DAOException;
    public List<ScmInvStock2> findByWareHouse(HashMap<String, Object> map) throws DAOException;
    public List<ScmInvStock2> findByUseOrgUnitNo(HashMap<String, Object> map) throws DAOException;
    
    public int addByMaterialReqBillOutOrgunitNo(HashMap<String, Object> map) throws DAOException;
    public int addByMaterialReqBillReturn(HashMap<String, Object> map) throws DAOException;
    public int updateByMaterialReqBillOut(HashMap<String, Object> map) throws DAOException;
    public int updateByMaterialReqBillOutOrgunitNo(HashMap<String, Object> map) throws DAOException;
    public int updateByMaterialReqBillReturn(HashMap<String, Object> map) throws DAOException;
    public int updateByMaterialReqBillReturnOrgunitNo(HashMap<String, Object> map) throws DAOException;
    public List<ScmInvStock2> selectPriceByStock(HashMap<String, Object> map) throws DAOException;
    
    public int updateByMaterialReqBillIn(HashMap<String, Object> map) throws DAOException;
    public int updateByMaterialReqBillInOrgunitNo(HashMap<String, Object> map) throws DAOException;
    public int updateByMaterialReqBillOrgunitNo(HashMap<String, Object> map) throws DAOException;
    public int updateByMaterialReqBill(HashMap<String, Object> map) throws DAOException;
    
    //期初结存
    public int updateByInitBill(HashMap<String, Object> map) throws DAOException;
    public int addByInitBill(HashMap<String, Object> map) throws DAOException;
    public int updateByCancelInitBill(HashMap<String, Object> map) throws DAOException;

    public int updateByMoveIssuePost(HashMap<String, Object> map) throws DAOException;
    
    //部门期初结存
    public int updateByCstInitBill(HashMap<String, Object> map) throws DAOException;
    public int addByCstInitBill(HashMap<String, Object> map) throws DAOException;
    public int updateByCancelCstInitBill(HashMap<String, Object> map) throws DAOException;

    
    //成本中心盘存
    public int checkCostCenter(HashMap<String, Object> map) throws DAOException;
    public int checkCostCenter2(HashMap<String, Object> map) throws DAOException;
    public int addByCostCenter(HashMap<String, Object> map) throws DAOException;
    public int updateByCostCenter(HashMap<String, Object> map) throws DAOException;

    public int updateByMoveIssueUnPost(HashMap<String, Object> map) throws DAOException;
    
    //调拨入库
    public int updateByMoveInWarehsBill(HashMap<String, Object> map) throws DAOException;
    public int addByMoveInWarehsBill(HashMap<String, Object> map) throws DAOException;
    public int updateByCancelMoveInWarehsBill(HashMap<String, Object> map) throws DAOException;
    
	//成本中心耗用
    public int updateByCostConsume(HashMap<String, Object> map) throws DAOException;
    public int updateByCancelCostConsume(HashMap<String, Object> map) throws DAOException;
    //清除零数量结存
	public int deleteZeroQty(HashMap<String, Object> map) throws DAOException;
	public int writeBackZeroQty(HashMap<String, Object> map) throws DAOException;
	public List<ScmInvStock2> selectWareHsForSale(HashMap<String, Object> map) throws DAOException;
	public int updateByDepositInReturn(HashMap<String, Object> map) throws DAOException;
	public int updateByCancelDepositInReturn(HashMap<String, Object> map) throws DAOException;
	public List<ScmInvStock2> findByUseOrgCounting(HashMap<String, Object> map) throws DAOException;
	
	//成本中心报损
    public int updateByCstFrmLoss(HashMap<String, Object> map) throws DAOException;
    public int updateByCancelCstFrmLoss(HashMap<String, Object> map) throws DAOException;
    
    //部门耗用
    public List<ScmFinDeptConsume> selectFinDeptConsume(HashMap<String, Object> map) throws DAOException;
    public List<ScmFinDeptConsume> selectReqFinDeptConsume(HashMap<String, Object> map) throws DAOException;
    
    public List<ScmInvStock2> selectByOrgUnitNos(HashMap<String, Object> map) throws DAOException;
	public ScmInvStock2 selectIdleItemStock(HashMap<String, Object> map) throws DAOException;
	
	public ScmInvStock2 getStockQty(HashMap<String, Object> map) throws DAOException;
	public ScmInvStock2 selectCostPrice(HashMap<String, Object> map) throws DAOException;
	public ScmInvStock2 getStockQtyByReqOrg(HashMap<String, Object> map) throws DAOException;
	public List<ScmInvStock2> getStockQtyList(HashMap<String, Object> map) throws DAOException;
	public List<ScmInvStock2> selectOrgForSale(HashMap<String, Object> map) throws DAOException;
	public List<ScmInvStock2> selectCostPriceByItems(HashMap<String, Object> map) throws DAOException;
}

