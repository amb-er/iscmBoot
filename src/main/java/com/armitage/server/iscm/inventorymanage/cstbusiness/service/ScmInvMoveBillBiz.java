package com.armitage.server.iscm.inventorymanage.cstbusiness.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.armitage.server.api.business.datasync.params.InvMoveListParams;
import com.armitage.server.api.business.datasync.result.DataSyncResult;
import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvMoveBill2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.CommonAuditParams;

public interface ScmInvMoveBillBiz extends BaseBiz<ScmInvMoveBill2> {
    public List<String> postBillCheck(ScmInvMoveBill2 scmInvMoveBill,String type, Param param) throws AppException;
    public ScmInvMoveBill2 postBill(ScmInvMoveBill2 scmInvMoveBill, Param param) throws AppException;
    public ScmInvMoveBill2 cancelPostBill(ScmInvMoveBill2 scmInvMoveBill, Param param) throws AppException;

	/**
	 * 根据财务组织检查是否存在未过账单据
	 * @param finOrgUnitNo
	 * @param periodId
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public List<ScmInvMoveBill2> checkUnPostBill(String finOrgUnitNo,long periodId, Param param) throws AppException;
	/**
	 * 根据财务组织检查是否存在已过账单据
	 * @param finOrgUnitNo
	 * @param periodId
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public List<ScmInvMoveBill2> checkPostedBill(String finOrgUnitNo,long periodId, Param param) throws AppException;
    /**
     * 单据提交
     * @param scmInvMoveBill
     * @param param
     * @return
     * @throws AppException
     */
    public ScmInvMoveBill2 submit(ScmInvMoveBill2 scmInvMoveBill, Param param) throws AppException;
    /**
     * 取消提交
     * @param scmInvMoveBill
     * @param param
     * @return
     * @throws AppException
     */
    public ScmInvMoveBill2 undoSubmit(ScmInvMoveBill2 scmInvMoveBill, Param param) throws AppException;
    
    /**
     * 查询成本转移单详情接口
     * @param scmInvMoveBill
     * @param param
     * @return
     * @throws AppException
     */
    public ScmInvMoveBill2 queryInvMove(ScmInvMoveBill2 scmInvMoveBill, Param param) throws AppException;
    
    /**
     * 审批成本转移单
     * @param commonAuditParams
     * @param param
     * @return
     * @throws AppException
     */
    public ScmInvMoveBill2 doAuditInvMove(CommonAuditParams commonAuditParams, Param param) throws AppException;
    
    /**
     * 取消审批成本转移单
     * @param scmInvMoveBill
     * @param param
     * @return
     * @throws AppException
     */
    public ScmInvMoveBill2 doUnAuditInvMove(ScmInvMoveBill2 scmInvMoveBill, Param param) throws AppException;

    /**
     * 生产盘点单时统计成本转移单未过账单据
     * @param map
     * @return
     */
	public List<Map<String,Object>> countUnPostBill(HashMap<String, Object> map);
	public ScmInvMoveBill2 updatePrtCount(ScmInvMoveBill2 scmInvMoveBill, Param param) throws AppException;
	public DataSyncResult dataSync(InvMoveListParams invMoveListParam, List<ScmInvMoveBill2> scmInvMoveBills,
			Param param) throws AppException;
}
