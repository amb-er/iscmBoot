package com.armitage.server.iscm.inventorymanage.warehouseinbusiness.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvMoveInWarehsBill2;

public interface ScmInvMoveInWarehsBillBiz extends BaseBiz<ScmInvMoveInWarehsBill2> {

    public ScmInvMoveInWarehsBill2 submit(ScmInvMoveInWarehsBill2 scmInvMoveInWarehsBill, Param param) throws AppException;

    public ScmInvMoveInWarehsBill2 undoSubmit(ScmInvMoveInWarehsBill2 scmInvMoveInWarehsBill, Param param) throws AppException;

    public ScmInvMoveInWarehsBill2 postBill(ScmInvMoveInWarehsBill2 scmInvMoveInWarehsBill, Param param) throws AppException;

    public ScmInvMoveInWarehsBill2 cancelPostBill(ScmInvMoveInWarehsBill2 scmInvMoveInWarehsBill, Param param) throws AppException;

    public List<String> postBillCheck(ScmInvMoveInWarehsBill2 scmInvMoveInWarehsBill, Param param) throws AppException;
    
    /**
     * 过账前检查
     * @param scmInvMoveInWarehsBill
     * @param param
     * @return
     */
    public List<String> cancelPostBillCheck(ScmInvMoveInWarehsBill2 scmInvMoveInWarehsBill, Param param) throws AppException;

	/**
	 * 根据财务组织检查是否存在未过账单据
	 * @param finOrgUnitNo
	 * @param periodId
	 * @param param
	 * @return
	 */
	public List<ScmInvMoveInWarehsBill2> checkUnPostBill(String finOrgUnitNo,long periodId, Param param) throws AppException;

	/**
	 * 根据财务组织检查是否存在已过账单据
	 * @param finOrgUnitNo
	 * @param periodId
	 * @param param
	 * @return
	 */
	public List<ScmInvMoveInWarehsBill2> checkPostedBill(String finOrgUnitNo,long periodId, Param param) throws AppException;

	public List<ScmInvMoveInWarehsBill2> selectByMoveIssue(long otId, Param param) throws AppException;


	/**
	 * 盘点时统计调拨入库未过账单据
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> countUnPostBill(HashMap<String, Object> map) throws AppException;
	
	/**
	 * 盘存时统计调拨入库未过账单据
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> countCostUnPostBill(HashMap<String, Object> map) throws AppException;

	public ScmInvMoveInWarehsBill2 updatePrtCount(ScmInvMoveInWarehsBill2 scmInvMoveInWarehsBill, Param param) throws AppException;
}

