package com.armitage.server.iscm.inventorymanage.cstbusiness.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.armitage.server.api.business.costconsume.params.CostConsumeAddParams;
import com.armitage.server.api.business.datasync.params.InvCostConsumeListParams;
import com.armitage.server.api.business.datasync.result.DataSyncResult;
import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCostConsume2;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCostConsumeImPort;

public interface ScmInvCostConsumeBiz extends BaseBiz<ScmInvCostConsume2> {
    public ScmInvCostConsume2 submit(ScmInvCostConsume2 scmInvCostConsume, Param param) throws AppException;
    public ScmInvCostConsume2 undoSubmit(ScmInvCostConsume2 scmInvCostConsume, Param param) throws AppException;
    public List<String> postBillCheck(ScmInvCostConsume2 scmInvCostConsume, Param param) throws AppException;
	public ScmInvCostConsume2 postBill(ScmInvCostConsume2 scmInvCostConsume, Param param) throws AppException;
	public List<String> cancelPostBillCheck(ScmInvCostConsume2 scmInvCostConsume, Param param) throws AppException;
	public ScmInvCostConsume2 cancelPostBill(ScmInvCostConsume2 scmInvCostConsume, Param param) throws AppException;

	/**
	 * 根据财务组织检查是否存在未过账单据
	 * @param finOrgUnitNo
	 * @param periodId
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public List<ScmInvCostConsume2> checkUnPostBill(String finOrgUnitNo,long periodId, Param param) throws AppException;
	/**
	 * 根据财务组织检查是否存在已过账单据
	 * @param finOrgUnitNo
	 * @param periodId
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public List<ScmInvCostConsume2> checkPostedBill(String finOrgUnitNo,long periodId, Param param) throws AppException;
	
	public ScmInvCostConsume2 importExcel(ScmInvCostConsumeImPort object, Param param) throws AppException;
	public List<ScmInvCostConsume2> selectGenerateBill(long periodId,String finOrgUnitNo, Param param) throws AppException;
	public ScmInvCostConsume2 doAddCostConsume(CostConsumeAddParams costConsumeAddParams, Param param) throws AppException;
	public List<ScmInvCostConsume2> selectGenerateBillBySourceType(String useOrgUnitNos, String sourceType, Date bizDate,Param param) throws AppException;
	public ScmInvCostConsume2 updatePrtCount(ScmInvCostConsume2 scmInvCostConsume, Param param) throws AppException;
	/**
	 * 接口调用同步数据
	 * @param invCostConsumeListParam 同步的单据
	 * @param scmInvCostConsumes iscm单据
	 * @return DataSyncResult
	 * @throws AppException
	 */
	public DataSyncResult dataSync(InvCostConsumeListParams invCostConsumeListParam,
			List<ScmInvCostConsume2> scmInvCostConsumes, Param param)throws AppException;
	public List<Map<String, Object>> countUnPostBill(HashMap<String, Object> map);
}
