package com.armitage.server.iscm.inventorymanage.cstbusiness.service;

import java.util.List;

import com.armitage.server.api.business.costcounttask.params.CountCostMaterialListParams;
import com.armitage.server.api.business.costcounttask.params.CountCostTableSaveParams;
import com.armitage.server.api.business.datasync.params.InvCountingCostListParams;
import com.armitage.server.api.business.datasync.result.DataSyncResult;
import com.armitage.server.api.business.invcounttask.params.CountInvMaterialListParams;
import com.armitage.server.api.business.invcounttask.params.CountInvTableSaveParams;
import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.CommonBean;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.basedata.model.ScmMaterial2;
import com.armitage.server.iscm.inventorymanage.countbusiness.model.ScmInvCountInvTaskDiff;
import com.armitage.server.iscm.inventorymanage.countbusiness.model.ScmInvCountingTable2;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCountCostTaskDiff;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCountingCostCenter2;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCountingTask2;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCountingTaskAdvQuery;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvWareHouse;
import com.armitage.server.system.model.OrgAdmin2;

public interface ScmInvCountingTaskBiz extends BaseBiz<ScmInvCountingTask2> {
    public ScmInvCountingTask2 selectMaxIdByDate(String date, String taskType, Param param) throws AppException;
    public ScmInvCountingTask2 generateCounting(ScmInvCountingTask2 scmInvCountingTask, Param param) throws AppException;
    
    public List<ScmInvCountingTask2> selectByDate(String date, String orgUnitNo, Param param) throws AppException;
    public List<ScmInvCountingTask2> selectByOrgUnitNoAndWareHouseId(String orgUnitNo,long taskId, String warehouseIdList, Param param) throws AppException;
    public List<ScmInvCountingTask2> selectByOrgUnitNoAndUseOrgUnitNo(String orgUnitNo,long taskId, String orgUnitNoList, Param param) throws AppException;
    public ScmInvCountingTask2 generateCosting(ScmInvCountingTask2 scmInvCountingTask, Param param) throws AppException;
    public List<String> countingFinish(ScmInvCountingTask2 scmInvCountingTask, Param param) throws AppException;

	public List<ScmInvCountingTask2> queryCountCostTaskList(ScmInvCountingTaskAdvQuery scmInvCountingTaskAdvQuery,int pageNum, Param param) throws AppException;
	public List<OrgAdmin2> queryCountCostTaskDepteList(ScmInvCountingTask2 scmInvCountingTask, Param param) throws AppException;
	public ScmInvCountingCostCenter2 queryCountCostTable(ScmInvCountingTask2 scmInvCountingTask, Param param) throws AppException;
	public void doSaveCountCostTable(CountCostTableSaveParams countCostTableSaveParams, Param param) throws AppException;
	public List<ScmInvCountCostTaskDiff> queryCountCostTaskDiff(ScmInvCountingTask2 scmInvCountingTask, Param param) throws AppException;
	public List<ScmInvWareHouse> queryCountInvTaskWareHouseList(ScmInvCountingTask2 scmInvCountingTask, Param param) throws AppException;
	public ScmInvCountingTable2 queryCountInvTable(	ScmInvCountingTask2 scmInvCountingTask, Param param) throws AppException;
	public void doSaveCountInvTable(CountInvTableSaveParams countInvTableSaveParams, Param param) throws AppException;
	public List<ScmInvCountInvTaskDiff> queryCountInvTaskDiff(ScmInvCountingTask2 scmInvCountingTask, Param param) throws AppException;
	public List<ScmMaterial2> queryCountInvMaterialList(CountInvMaterialListParams countInvMaterialListParams,int pageNum, Param param) throws AppException;
	public List<ScmMaterial2> queryCountCostMaterialList(CountCostMaterialListParams countCostMaterialListParams,int pageNum, Param param) throws AppException;
	public List<OrgAdmin2> queryDeptList(OrgAdmin2 orgAdmin,int pageIndex, Param param) throws AppException;
	
	public ScmInvCountingTask2 refreshAccount(ScmInvCountingTask2 scmInvCountingTask, Param param) throws AppException;
	
	public ScmInvCountingTask2 selectByTaskNo(String taskNo,Param param) throws AppException;
	public List<ScmInvCountingTask2> checkUnPostBill(String finOrgUnitNo,long periodId, Param param) throws AppException;
	public ScmInvCountingTask2 confirm(ScmInvCountingTask2 scmInvCountingTask, Param param) throws AppException;
	
	public ScmInvCountingTask2 generateCountingCheck(ScmInvCountingTask2 scmInvCountingTask, Param param) throws AppException;
	
	public ScmInvCountingTask2 generateCostingCheck(ScmInvCountingTask2 scmInvCountingTask, Param param) throws AppException;
	public CommonBean splitOrgAdd(CommonBean fromWSBean, Param createParam) throws AppException;
	public ScmInvCountingTask2 queryByTaskNo(String object, Param createParam) throws AppException;
	public DataSyncResult dataSync(InvCountingCostListParams invCountingCostListParam,
			List<ScmInvCountingTask2> scmInvCountingTask2s, Param param) throws AppException;
}
