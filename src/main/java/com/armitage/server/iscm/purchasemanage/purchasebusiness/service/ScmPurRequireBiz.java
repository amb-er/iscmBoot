package com.armitage.server.iscm.purchasemanage.purchasebusiness.service;

import java.util.List;
import java.util.Map;
import com.armitage.server.api.business.purrequire.params.PurReqMaterialListParams;
import com.armitage.server.api.business.purrequire.params.PurReqOrgUnitParams;
import com.armitage.server.api.business.purrequire.params.PurReqPersonParams;
import com.armitage.server.api.business.purrequire.params.PurReqPurOrgUnitParams;
import com.armitage.server.api.business.purrequire.params.PurReqWareHouseParams;
import com.armitage.server.api.business.purrequire.params.PurRequireAddParams;
import com.armitage.server.api.business.purrequire.params.PurRequireEditParams;
import com.armitage.server.api.business.purrequire.params.PurchaseTypeParams;
import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.CommonBean;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.basedata.model.ScmMaterial2;
import com.armitage.server.iscm.basedata.model.ScmPurchaseType2;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvWareHouse;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmMaterialPrice;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurPriceQuery;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.CommonAuditParams;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurBillDrillResult;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurRequire2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurRequireAdvQuery;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurRequireEntry2;
import com.armitage.server.system.model.Employee2;
import com.armitage.server.system.model.OrgAdmin2;
import com.armitage.server.system.model.OrgPurchase2;

public interface ScmPurRequireBiz extends BaseBiz<ScmPurRequire2> {
    /*
     * 删除请购单
     */
    public void doDelPurRequire(ScmPurRequire2 scmPurRequire,Param param) throws AppException ;
    
    /*
     * 提交请购单
     */
    public ScmPurRequire2 doSubmitPurRequire(ScmPurRequire2 scmPurRequire,Param param) throws AppException ;
    
    
    /*
     * 取消提交请购单
     */
    public ScmPurRequire2 doUnSubmitPurRequire(ScmPurRequire2 scmPurRequire,Param param) throws AppException ;
    
    /**
	 * 查询请购单列表接口
	 * @param scmPurRequireAdvQuery
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public List<ScmPurRequire2> queryPurRequireList(ScmPurRequireAdvQuery scmPurRequireAdvQuery, int pageNum, Param param) throws AppException;
	
	 /**
	  * 查询请购单详情接口
	  * @param scmPurRequireAdvQuery
	  * @param param
	  * @return
	  * @throws AppException
	  */
	public ScmPurRequire2 queryPurRequire(ScmPurRequire2 scmPurrequire, Param param) throws AppException;

	public ScmPurRequire2 doAddPurRequire(PurRequireAddParams purRequireAddParams, Param param) throws AppException;

	public void doEditPurRequire(PurRequireEditParams purRequireEditParams, Param param) throws AppException;

	public List<OrgPurchase2> queryPurReqPurOrgUnit(PurReqPurOrgUnitParams purReqPurOrgUnitParams, Param param) throws AppException;

	public List<ScmMaterial2> queryPurReqMaterialList(PurReqMaterialListParams purReqMaterialListParams, int pageNum,Param param) throws AppException;

	public List<OrgAdmin2> queryPurReqOrgUnit(PurReqOrgUnitParams purReqOrgUnitParams, Param param) throws AppException;

	public List<Employee2> queryPurReqPerson(PurReqPersonParams purReqPersonParams, int pageNum,Param param) throws AppException;

	public List<ScmInvWareHouse> queryPurReqWareHouse(PurReqWareHouseParams purReqWareHouseParams, Param param) throws AppException;

	public ScmMaterialPrice queryPurReqMaterialPrice(ScmPurPriceQuery scmPurPriceQuery, Param param) throws AppException;

	/**
	 * 另存模板
	 * @param scmPurRequire
	 * @param createParam
	 * @return
	 * @throws AppException
	 */
    public ScmPurRequire2 saveTemplete(ScmPurRequire2 scmPurRequire, Param param) throws AppException;
    
    /**
             * 模板制单
     * @param scmPurRequire
     * @param param
     * @return
     * @throws AppException
     */
    public ScmPurRequire2 templeteMake(ScmPurRequire2 scmPurRequire, Param param) throws AppException;
    
    /**
     * 审核请购单
     * @param purRequireAuditParams
     * @param param
     * @return
     * @throws AppException
     */
    public ScmPurRequire2 doAuditPurRequire(CommonAuditParams commonAuditParams,Param param) throws AppException ;
    
    /**
     * 反审核请购单
     * @param scmPurRequire
     * @param param
     * @return
     * @throws AppException
     */
    public ScmPurRequire2 doUnAuditPurRequire(ScmPurRequire2 scmPurRequire,Param param) throws AppException ;
	/**
	 * 下达
	 * @param scmPurRequire
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public ScmPurRequire2 doRelease(ScmPurRequire2 scmPurRequire, Param param) throws AppException;
	/**
	 * 取消下达
	 * @param scmPurRequire
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public ScmPurRequire2 doUndoRelease(ScmPurRequire2 scmPurRequire, Param param) throws AppException;
	/**
	 * 关闭
	 * @param scmPurRequire
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public ScmPurRequire2 finish(ScmPurRequire2 scmPurRequire, Param param) throws AppException;
	/**
	 * 取消关闭
	 * @param scmPurRequire
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public ScmPurRequire2 undoFinish(ScmPurRequire2 scmPurRequire, Param param) throws AppException;
	
	/**
	 * 获取采购类型接口
	 * @param purchaseTypeParams
	 * @param pageNum
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public List<ScmPurchaseType2> queryPurchaseType(PurchaseTypeParams purchaseTypeParams, Param param) throws AppException;
	
	/**
	 * 计算金额
	 */
	public Map selectPurRequireTotalAmt(long requireId, Param param);
	
	public void updateOutAudit(ScmPurRequire2 scmPurRequire,Param param) throws AppException ;
	
	public List<ScmPurBillDrillResult> selectDrillBills(ScmPurRequire2 scmPurRequire, Param param) throws AppException;

	public List<ScmPurRequire2> selectByIds(String string, Param param) throws AppException;

	public CommonBean doEntryAudit(List<ScmPurRequireEntry2> list,CommonAuditParams commonAuditParams, Param param) throws AppException;

	public void deleteTemplete(ScmPurRequire2 scmPurRequire, Param param) throws AppException;
	
	public void setConvertMapBiz(ScmPurRequire2 scmPurRequire, Param param) throws AppException;
	public ScmPurRequire2 queryTemplete(ScmPurRequire2 scmPurrequire, Param param) throws AppException;
	public ScmPurRequire2 selectByTypeCode(String code, Param param) throws AppException;

	public ScmPurRequire2 updatePrtCount(ScmPurRequire2 scmPurRequire, Param param) throws AppException;

	public ScmPurRequire2 selectByPrNo(String billNo, Param param) throws AppException;

	public void dataSyncSave(ScmPurRequire2 scmPurRequire2,Param param) throws AppException;
}
