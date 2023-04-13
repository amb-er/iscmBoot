package com.armitage.server.iscm.inventorymanage.inventorydata.service.impl;



import java.util.HashMap;

import org.apache.commons.lang.StringUtils;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvCostDAO;
import com.armitage.server.iscm.inventorymanage.inventorydata.model.ScmInvCost;
import com.armitage.server.iscm.inventorymanage.inventorydata.service.ScmInvCostBiz;
import org.springframework.stereotype.Service;

@Service("scmInvCostBiz")
public class ScmInvCostBizImpl extends BaseBizImpl<ScmInvCost> implements ScmInvCostBiz {

	@Override
	public void addByOtherInWarehsBill(long wrId, Param param) throws AppException {
		if(wrId > 0){
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("wrId",wrId);
			((ScmInvCostDAO)dao).addByOtherInWarehsBill(map);
		}
	}

	@Override
	public void updateByOtherInWarehsBill(long wrId, Param param) throws AppException {
		if(wrId > 0){
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("wrId",wrId);
			((ScmInvCostDAO)dao).updateByOtherInWarehsBill(map);
		}
	}
	
	@Override
	public void addByPurInWarehsBill(long wrId, Param param) throws AppException {
		if(wrId > 0){
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("wrId",wrId);
			((ScmInvCostDAO)dao).addByPurInWarehsBill(map);
		}
	}

	@Override
	public void updateByPurInWarehsBill(long wrId, Param param) throws AppException {
		if(wrId > 0){
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("wrId",wrId);
			((ScmInvCostDAO)dao).updateByPurInWarehsBill(map);
		}
	}

	@Override
	public void updateByPurInWarehsBillOut(long wrId, Param param)
			throws AppException {
		if(wrId > 0){
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("wrId",wrId);
			((ScmInvCostDAO)dao).updateByPurInWarehsBillOut(map);
		}
	}
	
	@Override
	public void updateByCancelPurInWarehsBillOut(long wrId, Param param)
			throws AppException {
		if(wrId > 0){
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("wrId",wrId);
			((ScmInvCostDAO)dao).updateByCancelPurInWarehsBillOut(map);
		}
	}

	@Override
	public void updateByCancelPurInWarehsBill(long wrId, Param param)
			throws AppException {
		if(wrId > 0){
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("wrId",wrId);
			((ScmInvCostDAO)dao).updateByCancelPurInWarehsBill(map);
		}
	}

    @Override
    public void updateByMoveBillOutSub(long wtId, Param param) throws AppException {
        if(wtId > 0){
            HashMap<String,Object> map = new HashMap<String,Object>();
            map.put("wtId",wtId);
            ((ScmInvCostDAO)dao).updateByMoveBillOutSub(map);
        }
        
    }

    @Override
    public void updateByMoveBillInPlus(long wtId, Param param) throws AppException {
        if(wtId > 0){
            HashMap<String,Object> map = new HashMap<String,Object>();
            map.put("wtId",wtId);
            ((ScmInvCostDAO)dao).updateByMoveBillInPlus(map);
        }
        
    }

    @Override
    public void addByMoveBillIn(long wtId, Param param) throws AppException {
        if(wtId > 0){
            HashMap<String,Object> map = new HashMap<String,Object>();
            map.put("wtId",wtId);
            ((ScmInvCostDAO)dao).addByMoveBillIn(map);
        }
        
    }

    @Override
    public void addByMoveBillOut(long wtId, Param param) throws AppException {
        if(wtId > 0){
            HashMap<String,Object> map = new HashMap<String,Object>();
            map.put("wtId",wtId);
            ((ScmInvCostDAO)dao).addByMoveBillOut(map);
        }
        
    }

    @Override
    public void updateByMoveBillOutPlus(long wtId, Param param) throws AppException {
        if(wtId > 0){
            HashMap<String,Object> map = new HashMap<String,Object>();
            map.put("wtId",wtId);
            ((ScmInvCostDAO)dao).updateByMoveBillOutPlus(map);
        }
        
    }

    @Override
    public void updateByMoveBillInSub(long wtId, Param param) throws AppException {
        if(wtId > 0){
            HashMap<String,Object> map = new HashMap<String,Object>();
            map.put("wtId",wtId);
            ((ScmInvCostDAO)dao).updateByMoveBillInSub(map);
        }
        
    }


    @Override
    public void updateBySaleIssuePostSub(long otId, Param param) throws AppException{
        if(otId > 0){
            HashMap<String,Object> map = new HashMap<String,Object>();
            map.put("otId",otId);
            ((ScmInvCostDAO)dao).updateBySaleIssuePostSub(map);
        }
    }
    
    @Override
    public void updateBySaleIssueUnPost(long otId, String flag, Param param) throws AppException{
        if(otId > 0){
            HashMap<String,Object> map = new HashMap<String,Object>();
            map.put("otId",otId);
            map.put("flag",flag);
            ((ScmInvCostDAO)dao).updateBySaleIssueUnPost(map);
        }
    }

    @Override
    public void addBySaleIssuePost(long otId, Param param) throws AppException{
        if(otId > 0){
            HashMap<String,Object> map = new HashMap<String,Object>();
            map.put("otId",otId);
            ((ScmInvCostDAO)dao).addBySaleIssuePost(map);
        }
    }

    @Override
    public void updateBySaleIssuePostPlus(long otId, Param param) throws AppException{
        if(otId > 0){
            HashMap<String,Object> map = new HashMap<String,Object>();
            map.put("otId",otId);
            ((ScmInvCostDAO)dao).updateBySaleIssuePostPlus(map);
        }
    }


	@Override
	public void updateByOtherIssueOutSub(long otId, Param param) throws AppException {
		 if(otId > 0){
			 HashMap<String,Object> map = new HashMap<String,Object>();
			 map.put("otId",otId);
			 ((ScmInvCostDAO)dao).updateByOtherIssueOutSub(map);
		 }
	}

	@Override
	public void updateByOtherIssueInSub(long otId, Param param) throws AppException {
		if(otId > 0){
			 HashMap<String,Object> map = new HashMap<String,Object>();
			 map.put("otId",otId);
			 ((ScmInvCostDAO)dao).updateByOtherIssueInSub(map);
		 }
	}

	@Override
	public void addByMaterialReqBillOutOrgunitNo(long otId, Param param) throws AppException {
		if(otId > 0){
			 HashMap<String,Object> map = new HashMap<String,Object>();
			 map.put("otId",otId);
			 ((ScmInvCostDAO)dao).addByMaterialReqBillOutOrgunitNo(map);
		 }
	}

	@Override
	public void updateByMaterialReqBillOut(long otId, Param param) throws AppException {
		if(otId > 0){
			 HashMap<String,Object> map = new HashMap<String,Object>();
			 map.put("otId",otId);
			 ((ScmInvCostDAO)dao).updateByMaterialReqBillOut(map);
		 }
	}

	@Override
	public void updateByMaterialReqBillOutOrgunitNo(long otId, Param param) throws AppException {
		if(otId > 0){
			 HashMap<String,Object> map = new HashMap<String,Object>();
			 map.put("otId",otId);
			 ((ScmInvCostDAO)dao).updateByMaterialReqBillOutOrgunitNo(map);
		 }
	}

	@Override
	public void updateByMaterialReqBillReturn(long otId, Param param) throws AppException {
		if(otId > 0){
			 HashMap<String,Object> map = new HashMap<String,Object>();
			 map.put("otId",otId);
			 ((ScmInvCostDAO)dao).updateByMaterialReqBillReturn(map);
		 }
	}

	@Override
	public void updateByMaterialReqBillReturnOrgunitNo(long otId, Param param) throws AppException {
		if(otId > 0){
			 HashMap<String,Object> map = new HashMap<String,Object>();
			 map.put("otId",otId);
			 ((ScmInvCostDAO)dao).updateByMaterialReqBillReturnOrgunitNo(map);
		 }
	}

	@Override
	public void updateByCancelOtherInWarehsBill(long wrId, Param param) throws AppException{
		if(wrId > 0){
			 HashMap<String,Object> map = new HashMap<String,Object>();
			 map.put("wrId",wrId);
			 ((ScmInvCostDAO)dao).updateByCancelOtherInWarehsBill(map);
		 }
	}

	@Override
	public void updateByMoveInWarehsBill(long wrId, Param param)
			throws AppException {
		if(wrId > 0){
			 HashMap<String,Object> map = new HashMap<String,Object>();
			 map.put("wrId",wrId);
			 ((ScmInvCostDAO)dao).updateByMoveInWarehsBill(map);
		 }
	}

	@Override
	public void addByMoveInWarehsBill(long wrId, Param param)
			throws AppException {
		if(wrId > 0){
			 HashMap<String,Object> map = new HashMap<String,Object>();
			 map.put("wrId",wrId);
			 ((ScmInvCostDAO)dao).addByMoveInWarehsBill(map);
		 }
	}

	@Override
	public void updateByCancelMoveInWarehsBill(long wrId, Param param)
			throws AppException {
		if(wrId > 0){
			 HashMap<String,Object> map = new HashMap<String,Object>();
			 map.put("wrId",wrId);
			 ((ScmInvCostDAO)dao).updateByCancelMoveInWarehsBill(map);
		 }
	}

	@Override
	public void updateByMoveIssueUnPost(long otId, Param param)
			throws AppException {
		if(otId > 0){
			 HashMap<String,Object> map = new HashMap<String,Object>();
			 map.put("otId",otId);
			 ((ScmInvCostDAO)dao).updateByMoveIssueUnPost(map);
		 }
	}

	@Override
	public void updateByMoveIssuePost(long otId, Param param)
			throws AppException {
		if(otId > 0){
			 HashMap<String,Object> map = new HashMap<String,Object>();
			 map.put("otId",otId);
			 ((ScmInvCostDAO)dao).updateByMoveIssuePost(map);
		 }
	}

	@Override
	public void updateByCostCenter(long taskId, String orgUnitNo, Param param) throws AppException{
		if(taskId > 0 && StringUtils.isNotBlank(orgUnitNo)){
			HashMap<String, Object> map = new HashMap<>();
			map.put("taskId", taskId);
			map.put("finOrgUnitNo", orgUnitNo);
			((ScmInvCostDAO)dao).updateByCostCenter(map);
		}
	}

	@Override
	public void addByCostCenter(long taskId, String orgUnitNo, Param param) throws AppException{
		if(taskId > 0 && StringUtils.isNotBlank(orgUnitNo)){
			HashMap<String, Object> map = new HashMap<>();
			map.put("taskId", taskId);
			map.put("finOrgUnitNo", orgUnitNo);
			((ScmInvCostDAO)dao).addByCostCenter(map);
		}
	}

	@Override
	public void updateByDepositInReturn(long wrId, Param param) throws AppException {
		if(wrId > 0){
            HashMap<String,Object> map = new HashMap<String,Object>();
            map.put("wrId",wrId);
            ((ScmInvCostDAO)dao).updateByDepositInReturn(map);
        }
	}

	@Override
	public void updateByCancelDepositInReturn(long wrId, Param param) throws AppException {
		if(wrId > 0){
            HashMap<String,Object> map = new HashMap<String,Object>();
            map.put("wrId",wrId);
            ((ScmInvCostDAO)dao).updateByCancelDepositInReturn(map);
        }
	}

}


