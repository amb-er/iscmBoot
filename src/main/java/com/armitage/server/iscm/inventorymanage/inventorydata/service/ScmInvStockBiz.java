package com.armitage.server.iscm.inventorymanage.inventorydata.service;


import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.inventorymanage.inventorydata.model.ScmDeptConsumeQuery;
import com.armitage.server.iscm.inventorymanage.inventorydata.model.ScmFinDeptConsume;
import com.armitage.server.iscm.inventorymanage.inventorydata.model.ScmInvRealtimeStockQuery;
import com.armitage.server.iscm.inventorymanage.inventorydata.model.ScmInvStock2;

public interface ScmInvStockBiz extends BaseBiz<ScmInvStock2> {
	//其他入库
	public int addByOtherInWarehsBill(long wrId, long unitedBillId,Param param) throws AppException;
	public int updateByOtherInWarehsBill(long wrId,Param param) throws AppException;
	public int updateByCancelOtherInWarehsBill(long wrId,Param param) throws AppException;
	
	public int addByPurInWarehsBill(long wrId, long unitedBillId,String orgUnitNoList,Param param) throws AppException;
	public int updateByPurInWarehsBill(long wrId,String orgUnitNoList,Param param) throws AppException;
	public int updateByCancelPurInWarehsBill(long wrId,Param param) throws AppException;
	public int updateByPurInWarehsBillOut(long wrId,Param param) throws AppException;
	public int updateByCancelPurInWarehsBillOut(long wrId,Param param) throws AppException;
	
	public int addByMoveBillIn(long wtId, Param param) throws AppException;
    public int updateByMoveBillOutSub(long wtId, Param param) throws AppException;
    public int updateByMoveBillOutPlus(long wtId, Param param) throws AppException;
    public int updateByMoveBillInSub(long wtId, Param param) throws AppException;
    public int updateByMoveBillInPlus(long wtId, Param param) throws AppException;
    public int addByMoveBillOut(long wtId, Param param) throws AppException;
    
    public int updateBySaleIssueUnPost(long otId, String flag, Param param);
    public int updateBySaleIssueNotOffset(long otId, Param param);
    public int addBySaleIssuePost(long otId, Param param);
    public int updateBySaleIssuePostSub(long otId, Param param);
    
    //其他出库 
    public int updateByOtherIssueOutSub(long otId, Param param) throws AppException;
    public int updateByOtherIssueInSub(long otId, Param param) throws AppException;
 
    public List<ScmInvStock2> findByDate(HashMap<String, Object> map, Param param) throws AppException;
    public List<ScmInvStock2> findByOutWarehouse(HashMap<String, Object> map, Param param) throws AppException;
    public List<ScmInvStock2> findByReturnWarehouse(HashMap<String, Object> map, Param param) throws AppException;
    public List<ScmInvStock2> findByOutAndReturnWarehouse(HashMap<String, Object> map, Param param) throws AppException;
    public List<ScmInvStock2> findByWareHouse(HashMap<String, Object> map, Param param) throws AppException;
    public List<ScmInvStock2> findByUseOrgUnitNo(HashMap<String, Object> map, Param param) throws AppException;
    
    public int addByMaterialReqBillOutOrgunitNo(long otId, Param param) throws AppException;
    public int addByMaterialReqBillReturn(long otId, Param param) throws AppException;
    public int updateByMaterialReqBillOut(long otId, Param param) throws AppException;
    public int updateByMaterialReqBillOutOrgunitNo(long otId, Param param) throws AppException;
    public int updateByMaterialReqBillReturn(long otId, Param param) throws AppException;
    public int updateByMaterialReqBillReturnOrgunitNo(long otId, Param param) throws AppException;
    public List<ScmInvStock2> selectPriceByStock(String orgUnitNo, long itemId, long unit, Param param) throws AppException;
    
    //取消过账更新出库仓库结存
    public int updateByMaterialReqBillIn(long otId, Param param) throws AppException;
    //更新部门结存
    public int updateByMaterialReqBillInOrgunitNo(long otId, Param param) throws AppException;
    //更新部门结存
    public int updateByMaterialReqBillOrgunitNo(long otId, Param param) throws AppException;
    //更新仓库结存
    public int updateByMaterialReqBill(long otId, Param param) throws AppException;
    
    
    //期初结存
	public int updateByInitBill(long initId, Param param) throws AppException;
	public int addByInitBill(long initId, Param param) throws AppException;
	public int updateByCancelInitBill(long initId,Param param) throws AppException;

    public int updateByMoveIssuePost(long otId, Param param) throws AppException;
    public int updateByMoveIssueUnPost(long otId, Param param) throws AppException;
    
    //部门期初结存
	public int updateByCstInitBill(long initId, Param param) throws AppException;
	public int addByCstInitBill(long initId, Param param) throws AppException;
	public int updateByCancelCstInitBill(long initId,Param param) throws AppException;
    
	//调拨入库
    public int updateByMoveInWarehsBill(long wrId, Param param) throws AppException;
    public int addByMoveInWarehsBill(long wrId, Param param) throws AppException;
    public int updateByCancelMoveInWarehsBill(long wrId, Param param) throws AppException;

    //成本中心盘存
    public int checkCostCenter(long taskId,String finOrgUnitNo, Param param) throws AppException;
    public int checkCostCenter2(long taskId,String finOrgUnitNo, Param param) throws AppException;
    public int addByCostCenter(long taskId,String finOrgUnitNo, Param param) throws AppException;
    public int updateByCostCenter(long taskId,String finOrgUnitNo, Param param) throws AppException;
    
    //成本中心耗用
    public int updateByCostConsume(long dcId, Param param) throws AppException;
	public int updateByCancelCostConsume(long dcId,Param param) throws AppException;
	public int deleteZeroQty(String orgUnitNo, boolean costCenter, Param param)throws AppException;
	public int writeBackZeroQty(String orgUnitNo, boolean costCenter,long periodId, Param param)throws AppException;
	
    public List<ScmInvStock2> selectRealtimeStock(ScmInvRealtimeStockQuery ScmInvRealtimeStockQuery,int pageNum, Param param)throws AppException;
    public List<ScmInvStock2> selectWareHsForSale(String orgUnitNo,long itemId,Param param) throws AppException;
    //寄存退货
	public int updateByDepositInReturn(long wrId, Param param) throws AppException;
	public int updateByCancelDepositInReturn(long wrId, Param param) throws AppException;
	public List<ScmInvStock2> findByUseOrgCounting(String orgUnitNo, String useOrgUnitNo, Param param);
	
	public HashMap<String,List<BigDecimal>> selectFinConsume(ScmDeptConsumeQuery scmDeptConsumeQuery, Param param) throws AppException;
	public List<ScmFinDeptConsume> selectFinDeptConsume(ScmDeptConsumeQuery scmDeptConsumeQuery, Param param);
	
	//成本中心报损
    public int updateByCstFrmLoss(long billId, Param param) throws AppException;
	public int updateByCancelCstFrmLoss(long billId,Param param) throws AppException;
	public List<ScmInvStock2> selectByOrgUnitNos(String string, String costOrgUnitNos, Param param) throws AppException;
	public ScmInvStock2 selectIdleItemStock(String orgUnitNo, String useOrgUnitNo, long itemId, Param param) throws AppException;
	
	//查詢對應物資數量
	public BigDecimal getStockQty(String orgUnitNo, String itemNo,Param param) throws AppException;
	public ScmInvStock2 selectCostPrice(long id, String string, Param param) throws AppException;
	public BigDecimal getStockQtyByReqOrg(String reqOrgUnitNo, String itemNo, Param param) throws AppException;
	public List<ScmInvStock2> getStockQtyList(String invOrgUnitNo, String itemIds, long orgOrWhStockQty, Param param) throws AppException;
	public List<ScmInvStock2> selectOrgForSale(String orgUnitNo,long itemId,Param param) throws AppException;
	public List<ScmInvStock2> selectCostPriceByItems(String string, String finOrg, Param param) throws AppException;
}

