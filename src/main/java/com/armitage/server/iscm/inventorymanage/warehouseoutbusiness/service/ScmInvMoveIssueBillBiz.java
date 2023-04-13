package com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvMoveIssueBill2;

public interface ScmInvMoveIssueBillBiz extends BaseBiz<ScmInvMoveIssueBill2> {

    public ScmInvMoveIssueBill2 submit(ScmInvMoveIssueBill2 scmInvMoveIssueBill, Param param) throws AppException;

    public ScmInvMoveIssueBill2 undoSubmit(ScmInvMoveIssueBill2 scmInvMoveIssueBill, Param param) throws AppException;

    public ScmInvMoveIssueBill2 postBill(ScmInvMoveIssueBill2 scmInvMoveIssueBill, Param param) throws AppException;

    public List<String> postBillCheck(ScmInvMoveIssueBill2 scmInvMoveIssueBill, Param param) throws AppException;

    public ScmInvMoveIssueBill2 cancelPostBill(ScmInvMoveIssueBill2 scmInvMoveIssueBill, Param param) throws AppException;

    /**
     * 根据财务组织检查是否存在未过账单据
     * @param finOrgUnitNo
     * @param periodId
     * @param param
     * @return
     * @throws AppException
     */
    public List<ScmInvMoveIssueBill2> checkUnPostBill(String finOrgUnitNo, long periodId,Param param) throws AppException;
    /**
     * 根据财务组织检查是否存在已过账单据
     * @param finOrgUnitNo
     * @param periodId
     * @param param
     * @return
     * @throws AppException
     */
    public List<ScmInvMoveIssueBill2> checkPostedBill(String finOrgUnitNo,long periodId, Param param) throws AppException;

	public List<ScmInvMoveIssueBill2> selectByMoveIn(long wrId, Param param) throws AppException;
	
	/**
	 * 盘点时统计调拨出库未过账单据
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> countUnPostBill(HashMap<String, Object> map) throws AppException;

	public ScmInvMoveIssueBill2 updatePrtCount(ScmInvMoveIssueBill2 scmInvMoveIssueBill, Param param) throws AppException;

}