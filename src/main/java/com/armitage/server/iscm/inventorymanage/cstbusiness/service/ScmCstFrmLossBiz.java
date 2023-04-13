package com.armitage.server.iscm.inventorymanage.cstbusiness.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmCstFrmLoss2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.CommonAuditParams;

public interface ScmCstFrmLossBiz extends BaseBiz<ScmCstFrmLoss2> {
	public ScmCstFrmLoss2 submit(ScmCstFrmLoss2 scmCstFrmLoss, Param param) throws AppException;
    public ScmCstFrmLoss2 undoSubmit(ScmCstFrmLoss2 scmCstFrmLoss, Param param) throws AppException;
    public List<String> postBillCheck(ScmCstFrmLoss2 scmCstFrmLoss, Param param) throws AppException;
	public ScmCstFrmLoss2 postBill(ScmCstFrmLoss2 scmCstFrmLoss, Param param) throws AppException;
	public List<String> cancelPostBillCheck(ScmCstFrmLoss2 scmCstFrmLoss, Param param) throws AppException;
	public ScmCstFrmLoss2 cancelPostBill(ScmCstFrmLoss2 scmCstFrmLoss, Param param) throws AppException;
	
	/**
	 * 查询成本中心报损单详情接口
	 * @param scmCstFrmLoss
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public ScmCstFrmLoss2 queryCstFrmLoss(ScmCstFrmLoss2 scmCstFrmLoss, Param param) throws AppException;
	
	/**
	 * 审核成本中心报损单
	 * @param commonAuditParams
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public ScmCstFrmLoss2 doAuditCstFrmLoss(CommonAuditParams commonAuditParams, Param param) throws AppException;
    
    /**
     * 取消审批成本中心报损单
     * @param scmCstFrmLoss
     * @param param
     * @return
     * @throws AppException
     */
    public ScmCstFrmLoss2 doUnAuditCstFrmLoss(ScmCstFrmLoss2 scmCstFrmLoss, Param param) throws AppException;
    
    /**
     * 根据财务组织检查是否存在未过账单据
     * @param finOrgUnitNo
     * @param periodId
     * @param param
     * @return
     * @throws AppException
     */
    public List<ScmCstFrmLoss2> checkUnPostBill(String finOrgUnitNo,long periodId, Param param) throws AppException;
    
    public List<ScmCstFrmLoss2> checkPostedBill(String finOrgUnitNo,long periodId, Param param) throws AppException;
    
    /**
     * 生成盘存单时统计成本中心报损未过账单据
     * @param map
     * @return
     */
	public List<Map<String, Object>> countCostUnPostBill(HashMap<String, Object> map);
	public ScmCstFrmLoss2 updatePrtCount(ScmCstFrmLoss2 scmCstFrmLoss, Param param) throws AppException;
}
