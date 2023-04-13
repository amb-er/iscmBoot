package com.armitage.server.iscm.inventorymanage.warehouseinbusiness.service;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.armitage.server.api.business.datasync.params.InvOtherInWarehsListParams;
import com.armitage.server.api.business.datasync.result.DataSyncResult;
import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvOtherInWarehsBill2;

public interface ScmInvOtherInWarehsBillBiz extends BaseBiz<ScmInvOtherInWarehsBill2> {
	public ScmInvOtherInWarehsBill2 submit(ScmInvOtherInWarehsBill2 scmInvOtherInWarehsBill,Param param) throws AppException;
	public ScmInvOtherInWarehsBill2 undoSubmit(ScmInvOtherInWarehsBill2 scmInvOtherInWarehsBill,Param param) throws AppException;
	public List<String> postBillCheck(ScmInvOtherInWarehsBill2 scmInvOtherInWarehsBill, Param param) throws AppException;
	public ScmInvOtherInWarehsBill2 postBill(ScmInvOtherInWarehsBill2 scmInvOtherInWarehsBill, Param param) throws AppException;
	public List<String> cancelPostBillCheck(ScmInvOtherInWarehsBill2 scmInvOtherInWarehsBill, Param param) throws AppException;
	public ScmInvOtherInWarehsBill2 cancelPostBill(ScmInvOtherInWarehsBill2 scmInvOtherInWarehsBill, Param param) throws AppException;
	public String getBillNo(Date date, Param param) throws AppException;
	/**
	 * 根据财务组织检查是否存在未过账单据
	 * @param finOrgUnitNo
	 * @param periodId
	 * @param param
	 * @return
	 */
	public List<ScmInvOtherInWarehsBill2> checkUnPostBill(String finOrgUnitNo,long periodId, Param param);
	/**
	 * 根据财务组织检查指定期间已过账单据
	 * @param finOrgUnitNo
	 * @param periodId
	 * @param param
	 * @return
	 */
	public List<ScmInvOtherInWarehsBill2> checkPostedBill(String finOrgUnitNo,long periodId, Param param);

	/**
	 * 盘点时统计未过账单据
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> countUnPostBill(HashMap<String, Object> map);
	public ScmInvOtherInWarehsBill2 updatePrtCount(ScmInvOtherInWarehsBill2 scmInvOtherInWarehsBill, Param param) throws AppException;
	
	public DataSyncResult dataSync(InvOtherInWarehsListParams invOtherInWarehsListParam,
			List<ScmInvOtherInWarehsBill2> scmInvOtherInWarehsBill2s, Param param) throws AppException;

}

