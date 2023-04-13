package com.armitage.server.iscm.inventorymanage.warehouseinbusiness.service;


import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.armitage.server.api.business.datareport.params.SupSupplyOfMaterialDetailsParams;
import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.CommonBean;
import com.armitage.server.common.base.model.Page;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iaps.daily.model.Apinvoice2;
import com.armitage.server.iscm.common.model.ScmDataCollectionStepState2;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvPurInWarehsBill2;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvPurInWarehsBillAdvQuery;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvPurInWarehsBillEntry2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.CommonAuditParams;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurBillDrillResult;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurReceive2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurReturns2;

public interface ScmInvPurInWarehsBillBiz extends BaseBiz<ScmInvPurInWarehsBill2> {
	public List<String> postBillCheck(ScmInvPurInWarehsBill2 scmInvPurInWarehsBill, Param param) throws AppException;
	public List<String> postBill(ScmInvPurInWarehsBill2 scmInvPurInWarehsBill, Param param) throws AppException;
	public List<String> cancelPostBillCheck(ScmInvPurInWarehsBill2 scmInvPurInWarehsBill, Param param) throws AppException;
	public ScmInvPurInWarehsBill2 cancelPostBill(ScmInvPurInWarehsBill2 scmInvPurInWarehsBill, Param param) throws AppException;
	public ScmInvPurInWarehsBill2 generatePurInWarehsBillByReceive(ScmPurReceive2 receive, Param param) throws AppException;
	/**
	 * 结转时根据财务组织检查是否存在未过账单据
	 * @param finOrgUnitNo
	 * @param periodId
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public List<ScmInvPurInWarehsBill2> checkUnPostBill(String finOrgUnitNo, long periodId, Param param) throws AppException;
	public void generatePurInWarehsBillByReturns(ScmPurReturns2 scmPurReturns,Param param) throws AppException;
	/**
	 * 查询收货单所涉及的入库单
	 * @param receive
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public List<ScmInvPurInWarehsBill2> selectByPurReceive(ScmPurReceive2 receive, Param param) throws AppException;
	/**
	 * 查询退货单所涉及的入库单
	 * @param pruReturns
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public List<ScmInvPurInWarehsBill2> selectByPurReturns(ScmPurReturns2 pruReturns, Param param) throws AppException;
	
	/**
	 * 检查财务组织下已过账单据
	 * @param finOrgUnitNo
	 * @param periodId
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public List<ScmInvPurInWarehsBill2> checkPostedBill(String finOrgUnitNo, long periodId, Param param) throws AppException;
	
	/**
	 * 生成应付单
	 * @param scmInvPurInWarehsBillList
	 * @param param
	 * @return
	 * @throws AppException
	 */
    public List<Apinvoice2> generateApInvoice(List<ScmInvPurInWarehsBill2> scmInvPurInWarehsBillList, Param param) throws AppException;
    /**
	 * 异步生成应付单
	 * @param scmInvPurInWarehsBillList
	 * @param param
	 * @return
	 * @throws AppException
	 */
    public CommonBean asyngenerateApInvoice(ScmDataCollectionStepState2 stepState,final List<ScmInvPurInWarehsBill2> scmInvPurInWarehsBillList,final Param param) throws AppException;
	/**
	 * 单据提交
	 * @param scmPurReceiveList
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public List<String> submit(List<ScmInvPurInWarehsBill2> scmInvPurInWarehsBillList, Param param) throws AppException;
	/**
	 * 取消提交
	 * @param scmPurReceiveList
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public List<String> undoSubmit(List<ScmInvPurInWarehsBill2> scmInvPurInWarehsBillList, Param param) throws AppException;
	
	/**
	 * 根据销售退货查询采购退货单
	 * @param otId
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public List<ScmInvPurInWarehsBill2> selectBySaleIssueBill(long otId, Param param) throws AppException;
	
	/**
	 * 获取采购入库单详情
	 * @param scmInvPurInWarehsBill
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public ScmInvPurInWarehsBill2 queryInvPurInWarehs(ScmInvPurInWarehsBill2 scmInvPurInWarehsBill, Param param) throws AppException;
	
	/**
	 * 审批采购入库单
	 * @param commonAuditParams
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public ScmInvPurInWarehsBill2 doAuditInvPurInWarehs(CommonAuditParams commonAuditParams, Param param) throws AppException;
	
	/**
	 * 取消审批采购入库单
	 * @param scmInvPurInWarehsBill
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public ScmInvPurInWarehsBill2 doUnAuditInvPurInWarehs(ScmInvPurInWarehsBill2 scmInvPurInWarehsBill, Param param) throws AppException;
	
	/**
	 * 查询由订货单过来的采购入库单
	 * @param sourceBillIdList
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public List<ScmInvPurInWarehsBill2> selectByPurOrder(String detailIdList, Param param) throws AppException;
	
	/**
	 * 获取一段时间的采购入库量
	 * @param scmInvPurInWarehsBillAdvQuery
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public BigDecimal getTotalPurInWarehsQty(ScmInvPurInWarehsBillAdvQuery scmInvPurInWarehsBillAdvQuery, Param param) throws AppException ;
	
	public ScmInvPurInWarehsBill2 selectPoNoAndPvNoById(long wrId, Param param) throws AppException;
	
	public void updatePushed(ScmInvPurInWarehsBill2 scmInvPurInWarehsBill,Param param) throws AppException ;
	public void updateConfirmStatus(ScmInvPurInWarehsBill2 scmInvPurInWarehsBill,Param param) throws AppException ;
	public void updateVersion(ScmInvPurInWarehsBill2 scmInvPurInWarehsBill,Param param) throws AppException ;

	/**
	 * 盘点时统计采购入库未过账单据
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> countUnPostBill(HashMap<String, Object> map);
	
	/**
	 * 盘存时统计采购入库未过账单据
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> countCostUnPostBill(HashMap<String, Object> map);
	public ScmInvPurInWarehsBill2 selectByWrNo(String wrNo, Param param) throws AppException;
	public void setConvertMap(ScmInvPurInWarehsBill2 scmInvPurInWarehsBill,Param param)throws AppException ;
	public void doAdvQuery(Page page, Param param)throws AppException;
	public List<ScmInvPurInWarehsBill2> querySupSupplyOfMaterialDetails(SupSupplyOfMaterialDetailsParams supplyOfMaterialDetailsParams,Param param) throws AppException;
	public List getTotalPurInWarehsQtyByItems(ScmInvPurInWarehsBillAdvQuery object, Param createParam) throws AppException;
	
	public List<ScmPurBillDrillResult> selectDrillBills(ScmInvPurInWarehsBill2 scmInvPurInWarehsBill,Param param)throws AppException;
	public List<ScmPurBillDrillResult> selectDrillBillsISCM(List<String> list)throws AppException;
	public BigDecimal getInvPrice(String orgUnitNo, String itemNo, Param param) throws AppException;
	public List<ScmInvPurInWarehsBillEntry2> getInvPriceList(String invOrgUnitNo, String itemIds, Param param)throws AppException;
	public ScmInvPurInWarehsBill2 updatePrtCount(ScmInvPurInWarehsBill2 scmInvPurInWarehsBill, Param param)throws AppException;
}

