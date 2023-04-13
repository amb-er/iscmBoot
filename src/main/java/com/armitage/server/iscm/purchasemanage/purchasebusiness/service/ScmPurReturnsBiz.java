
package com.armitage.server.iscm.purchasemanage.purchasebusiness.service;

import java.util.List;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.CommonAuditParams;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurAuditParam;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurReceive2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurReturns2;

public interface ScmPurReturnsBiz extends BaseBiz<ScmPurReturns2> {
	public ScmPurReturns2 submit(ScmPurReturns2 scmPurReturns, Param param) throws AppException;
	public ScmPurReturns2 undoSubmit(ScmPurReturns2 scmPurReturns, Param param) throws AppException;
	public ScmPurReturns2 release(ScmPurReturns2 scmPurReturns, Param param) throws AppException;
	public ScmPurReturns2 undoRelease(ScmPurReturns2 scmPurReturns, Param param) throws AppException;
	
	/**
	 * 根据收货情况生成退货单
	 * @param receive
	 * @param param
	 * @throws AppException
	 */
	public void generateReturns(ScmPurReceive2 receive, Param param) throws AppException;
	/**
	 * 审核
	 * @param scmPurAuditParam
	 * @param scmPurReturnsList
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public List<ScmPurReturns2> audit(ScmPurAuditParam scmPurAuditParam, List<ScmPurReturns2> scmPurReturnsList, Param param) throws AppException;
	/**
	 * 反审核
	 * @param scmPurReturnsList
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public List<ScmPurReturns2> antiAudit(List<ScmPurReturns2> scmPurReturnsList, Param param) throws AppException;
	/**
	 * 根据采购入库单获取退货申请单
	 * @param wrId
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public List<ScmPurReturns2> selectByPurInwareHouse(long wrId, Param param) throws AppException;
	/**
	 * 根据采购入库退货单获取退货申请单
	 * @param wrId
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public List<ScmPurReturns2> selectByPurInwareHouseReturn(long wrId, Param param) throws AppException;
	
	/**
	 * 获取退货申请单详情
	 * @param scmPurReturns
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public ScmPurReturns2 queryPurReturns(ScmPurReturns2 scmPurReturns, Param param) throws AppException;
	
	/**
	 * 审批退货申请单
	 * @param commonAuditParams
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public ScmPurReturns2 doAuditPurReturns(CommonAuditParams commonAuditParams, Param param) throws AppException;
	
	/**
	 * 取消审批退货申请单
	 * @param scmPurturns
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public ScmPurReturns2 doUnAuditPurReturns(ScmPurReturns2 scmPurturns, Param param) throws AppException;
	public ScmPurReturns2 updatePrtCount(ScmPurReturns2 smPurReturns, Param param) throws AppException;
}
