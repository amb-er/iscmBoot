package com.armitage.server.iscm.inventorymanage.inventorydata.service.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Page;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvBalDAO;
import com.armitage.server.iscm.inventorymanage.inventorydata.model.ScmInvBal;
import com.armitage.server.iscm.inventorymanage.inventorydata.model.ScmInvBal2;
import com.armitage.server.iscm.inventorymanage.inventorydata.service.ScmInvBalBiz;
import com.armitage.server.system.model.OrgCostCenter2;
import com.armitage.server.system.service.OrgCostCenterBiz;
import com.armitage.server.system.util.OrgUnitRelationType;
import org.springframework.stereotype.Service;

@Service("scmInvBalBiz")
public class ScmInvBalBizImpl extends BaseBizImpl<ScmInvBal> implements ScmInvBalBiz {
	private OrgCostCenterBiz orgCostCenterBiz;
	
	public void setOrgCostCenterBiz(OrgCostCenterBiz orgCostCenterBiz) {
		this.orgCostCenterBiz = orgCostCenterBiz;
	}

	@Override
	public void addByOtherInWarehsBill(long wrId, Param param) throws AppException {
		if(wrId > 0){
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("wrId",wrId);
			((ScmInvBalDAO)dao).addByOtherInWarehsBill(map);
		}
	}

	@Override
	public void updateByOtherInWarehsBill(long wrId, Param param) throws AppException {
		if(wrId > 0){
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("wrId",wrId);
			((ScmInvBalDAO)dao).updateByOtherInWarehsBill(map);
		}
	}
	
	@Override
	public void addByPurInWarehsBill(long wrId, Param param) throws AppException {
		if(wrId > 0){
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("wrId",wrId);
			((ScmInvBalDAO)dao).addByPurInWarehsBill(map);
		}
	}

	@Override
	public void updateByPurInWarehsBill(long wrId, Param param) throws AppException {
		if(wrId > 0){
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("wrId",wrId);
			((ScmInvBalDAO)dao).updateByPurInWarehsBill(map);
		}
	}

    @Override
    public void updateByMoveBillOutSub(long wtId, Param param) throws AppException {
        if(wtId > 0){
            HashMap<String,Object> map = new HashMap<String,Object>();
            map.put("wtId",wtId);
            ((ScmInvBalDAO)dao).updateByMoveBillOutSub(map);
        }
        
    }

    @Override
    public void updateByMoveBillInPlus(long wtId, Param param) throws AppException {
        if(wtId > 0){
            HashMap<String,Object> map = new HashMap<String,Object>();
            map.put("wtId",wtId);
            ((ScmInvBalDAO)dao).updateByMoveBillInPlus(map);
        }
        
    }

    @Override
    public void addByMoveBillIn(long wtId, Param param) throws AppException {
        if(wtId > 0){
            HashMap<String,Object> map = new HashMap<String,Object>();
            map.put("wtId",wtId);
            ((ScmInvBalDAO)dao).addByMoveBillIn(map);
        }
        
    }
    @Override
    public void addByPostMoveBillForOut(long wtId, Param param) throws AppException {
        if(wtId > 0){
            HashMap<String,Object> map = new HashMap<String,Object>();
            map.put("wtId",wtId);
            ((ScmInvBalDAO)dao).addByPostMoveBillForOut(map);
        }
        
    }

    @Override
    public void addByMoveBillOut(long wtId, Param param) throws AppException {
        if(wtId > 0){
            HashMap<String,Object> map = new HashMap<String,Object>();
            map.put("wtId",wtId);
            ((ScmInvBalDAO)dao).addByMoveBillOut(map);
        }
        
    }

    @Override
    public void addByCancelMoveBillForIn(long wtId, Param param) throws AppException {
        if(wtId > 0){
            HashMap<String,Object> map = new HashMap<String,Object>();
            map.put("wtId",wtId);
            ((ScmInvBalDAO)dao).addByCancelMoveBillForIn(map);
        }
        
    }
    
    @Override
    public void updateByMoveBillOutPlus(long wtId, Param param) throws AppException {
        if(wtId > 0){
            HashMap<String,Object> map = new HashMap<String,Object>();
            map.put("wtId",wtId);
            ((ScmInvBalDAO)dao).updateByMoveBillOutPlus(map);
        }
        
    }

    @Override
    public void updateByMoveBillInSub(long wtId, Param param) throws AppException {
        if(wtId > 0){
            HashMap<String,Object> map = new HashMap<String,Object>();
            map.put("wtId",wtId);
            ((ScmInvBalDAO)dao).updateByMoveBillInSub(map);
        }
        
    }

    @Override
    public void updateBySaleIssuePostPlus(long otId, Param param) {
        if(otId > 0){
            HashMap<String,Object> map = new HashMap<String,Object>();
            map.put("otId",otId);
            ((ScmInvBalDAO)dao).updateBySaleIssuePostPlus(map);
        }
    }

    @Override
    public void updateBySaleIssueUnPost(long otId, String flag, Param param) {
        if(otId > 0){
            HashMap<String,Object> map = new HashMap<String,Object>();
            map.put("otId",otId);
            map.put("flag",flag);
            ((ScmInvBalDAO)dao).updateBySaleIssueUnPost(map);
        }
    }

    @Override
    public void addBySaleIssuePost(long otId, Param param) {
        if(otId > 0){
            HashMap<String,Object> map = new HashMap<String,Object>();
            map.put("otId",otId);
            ((ScmInvBalDAO)dao).addBySaleIssuePost(map);
        }
    }

    @Override
    public void updateBySaleIssuePostSub(long otId, Param param) {
        if(otId > 0){
            HashMap<String,Object> map = new HashMap<String,Object>();
            map.put("otId",otId);
            ((ScmInvBalDAO)dao).updateBySaleIssuePostSub(map);
        }
    }


	@Override
	public void updateByOtherIssueOutSub(long otId, Param param) throws AppException {
		 if(otId > 0){
			 HashMap<String,Object> map = new HashMap<String,Object>();
			 map.put("otId",otId);
			 ((ScmInvBalDAO)dao).updateByOtherIssueOutSub(map);
		 }	
	}

	@Override
	public void updateByOtherIssueInSub(long otId, Param param) throws AppException {
		 if(otId > 0){
			 HashMap<String,Object> map = new HashMap<String,Object>();
			 map.put("otId",otId);
			 ((ScmInvBalDAO)dao).updateByOtherIssueInSub(map);
		 }	
	}
	
	@Override
	public void addByOtherIssueInForCancelDataSnyc(long otId, Param param) throws AppException {
		 if(otId > 0){
			 HashMap<String,Object> map = new HashMap<String,Object>();
			 map.put("otId",otId);
			 ((ScmInvBalDAO)dao).addByOtherIssueInForCancelDataSnyc(map);
		 }	
	}
	
	@Override
	public void addByOtherIssueOutForDataSnyc(long otId, Param param) throws AppException {
		 if(otId > 0){
			 HashMap<String,Object> map = new HashMap<String,Object>();
			 map.put("otId",otId);
			 ((ScmInvBalDAO)dao).addByOtherIssueOutForDataSnyc(map);
		 }	
	}

	@Override
	public void addByMaterialReqBillOutOrgunitNo(long otId, Param param) throws AppException {
		if(otId > 0){
			 HashMap<String,Object> map = new HashMap<String,Object>();
			 map.put("otId",otId);
			 ((ScmInvBalDAO)dao).addByMaterialReqBillOutOrgunitNo(map);
		 }	
	}
	
	@Override
	public void addByMaterialReqBillOut(long otId, Param param) throws AppException {
		if(otId > 0){
			 HashMap<String,Object> map = new HashMap<String,Object>();
			 map.put("otId",otId);
			 ((ScmInvBalDAO)dao).addByMaterialReqBillOut(map);
		 }	
	}
	
	@Override
	public void addByMaterialReqBillIn(long otId, Param param) throws AppException {
		if(otId > 0){
			 HashMap<String,Object> map = new HashMap<String,Object>();
			 map.put("otId",otId);
			 ((ScmInvBalDAO)dao).addByMaterialReqBillIn(map);
		 }	
	}
	
	@Override
	public void addByMaterialReqBillInOrgunitNo(long otId, Param param) throws AppException {
		if(otId > 0){
			 HashMap<String,Object> map = new HashMap<String,Object>();
			 map.put("otId",otId);
			 ((ScmInvBalDAO)dao).addByMaterialReqBillInOrgunitNo(map);
		 }	
	}

	@Override
	public void addByMaterialReqBillReturn(long otId, Param param) throws AppException {
		if(otId > 0){
			 HashMap<String,Object> map = new HashMap<String,Object>();
			 map.put("otId",otId);
			 ((ScmInvBalDAO)dao).addByMaterialReqBillReturn(map);
		 }	
	}

	@Override
	public int updateByMaterialReqBillOut(long otId, Param param) throws AppException {
		if(otId > 0){
			 HashMap<String,Object> map = new HashMap<String,Object>();
			 map.put("otId",otId);
			 return ((ScmInvBalDAO)dao).updateByMaterialReqBillOut(map);
		 }
		return 0;
	}
	
	@Override
	public void updateByMaterialReqBillIn(long otId, Param param) throws AppException {
		if(otId > 0){
			 HashMap<String,Object> map = new HashMap<String,Object>();
			 map.put("otId",otId);
			 ((ScmInvBalDAO)dao).updateByMaterialReqBillIn(map);
		 }
	}
	
	@Override
	public int updateByMaterialReqBillInOrgunitNo(long otId, Param param) throws AppException {
		if(otId > 0){
			 HashMap<String,Object> map = new HashMap<String,Object>();
			 map.put("otId",otId);
			 return ((ScmInvBalDAO)dao).updateByMaterialReqBillInOrgunitNo(map);
		 }
		return 0;
	}
	
	@Override
	public void updateByMaterialReqBillOrgunitNo(long otId, Param param) throws AppException {
		if(otId > 0){
			 HashMap<String,Object> map = new HashMap<String,Object>();
			 map.put("otId",otId);
			 ((ScmInvBalDAO)dao).updateByMaterialReqBillOrgunitNo(map);
		 }
	}

	@Override
	public void updateByMaterialReqBill(long otId, Param param) throws AppException {
		if(otId > 0){
			 HashMap<String,Object> map = new HashMap<String,Object>();
			 map.put("otId",otId);
			 ((ScmInvBalDAO)dao).updateByMaterialReqBill(map);
		 }
	}

	@Override
	public void updateByMaterialReqBillOutOrgunitNo(long otId, Param param) throws AppException {
		if(otId > 0){
			 HashMap<String,Object> map = new HashMap<String,Object>();
			 map.put("otId",otId);
			 ((ScmInvBalDAO)dao).updateByMaterialReqBillOutOrgunitNo(map);
		 }
	}

	@Override
	public void updateByMaterialReqBillReturn(long otId, Param param) throws AppException {
		if(otId > 0){
			 HashMap<String,Object> map = new HashMap<String,Object>();
			 map.put("otId",otId);
			 ((ScmInvBalDAO)dao).updateByMaterialReqBillReturn(map);
		 }
	}

	@Override
	public void updateByMaterialReqBillReturnOrgunitNo(long otId, Param param) throws AppException {
		if(otId > 0){
			 HashMap<String,Object> map = new HashMap<String,Object>();
			 map.put("otId",otId);
			 ((ScmInvBalDAO)dao).updateByMaterialReqBillReturnOrgunitNo(map);
		 }
	}

	@Override
	public void updateByCancelOtherInWarehsBill(long wrId, Param param) throws AppException {
		if(wrId > 0){
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("wrId",wrId);
			((ScmInvBalDAO)dao).updateByCancelOtherInWarehsBill(map);
		}
	}

	@Override
	public void updateByCancelPurInWarehsBill(long wrId, Param param) throws AppException {
		if(wrId > 0){
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("wrId",wrId);
			((ScmInvBalDAO)dao).updateByCancelPurInWarehsBill(map);
		}
	}

    @Override
    public void updateByMoveIssuePost(long otId, Param param) throws AppException {
        if(otId > 0){
            HashMap<String,Object> map = new HashMap<String,Object>();
            map.put("otId",otId);
            ((ScmInvBalDAO)dao).updateByMoveIssuePost(map);
        }
    }

    @Override
    public void updateByMoveIssueUnPost(long otId, Param param) throws AppException {
        if(otId > 0){
            HashMap<String,Object> map = new HashMap<String,Object>();
            map.put("otId",otId);
            ((ScmInvBalDAO)dao).updateByMoveIssueUnPost(map);
        }
    }
	
	@Override
	public void updateByInitBill(long initId, Param param) throws AppException {
		if(initId > 0){
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("initId",initId);
			((ScmInvBalDAO)dao).updateByInitBill(map);
		}
	}
	
	@Override
	public void addByInitBill(long initId, Param param) throws AppException {
		if(initId > 0){
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("initId",initId);
			((ScmInvBalDAO)dao).addByInitBill(map);
		}
	}

	@Override
	public void updateByCancelInitBill(long initId, Param param)
			throws AppException {
		if(initId > 0){
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("initId",initId);
			((ScmInvBalDAO)dao).updateByCancelInitBill(map);
		}
	}
	
	@Override
	public void addByPurInWarehsBillOut(long wrId, Param param) throws AppException {
		if(wrId > 0){
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("wrId",wrId);
			((ScmInvBalDAO)dao).addByPurInWarehsBillOut(map);
		}
	}

	@Override
	public void updateByPurInWarehsBillOut(long wrId, Param param) throws AppException {
		if(wrId > 0){
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("wrId",wrId);
			((ScmInvBalDAO)dao).updateByPurInWarehsBillOut(map);
		}
	}
	
	@Override
	public void updateByCancelPurInWarehsBillOut(long wrId, Param param) throws AppException {
		if(wrId > 0){
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("wrId",wrId);
			((ScmInvBalDAO)dao).updateByCancelPurInWarehsBillOut(map);
		}
	}

    @Override
    public void updateByMoveInWarehsBill(long wrId, Param param) throws AppException {
        if(wrId > 0){
            HashMap<String,Object> map = new HashMap<String,Object>();
            map.put("wrId",wrId);
            ((ScmInvBalDAO)dao).updateByMoveInWarehsBill(map);
        }
    }

    @Override
    public void addByMoveInWarehsBill(long wrId, Param param) throws AppException {
        if(wrId > 0){
            HashMap<String,Object> map = new HashMap<String,Object>();
            map.put("wrId",wrId);
            ((ScmInvBalDAO)dao).addByMoveInWarehsBill(map);
        }
    }

	@Override
	public void updateByCstInitBill(long initId, Param param)
			throws AppException {
		if(initId > 0){
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("initId",initId);
			((ScmInvBalDAO)dao).updateByCstInitBill(map);
		}
	}

	@Override
	public void addByCstInitBill(long initId, Param param) throws AppException {
		if(initId > 0){
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("initId",initId);
			((ScmInvBalDAO)dao).addByCstInitBill(map);
		}
	}

	@Override
	public void updateByCancelCstInitBill(long initId, Param param)
			throws AppException {
		if(initId > 0){
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("initId",initId);
			((ScmInvBalDAO)dao).updateByCancelCstInitBill(map);
		}
	}

	@Override
	public void addByCostCenter(long taskId, String finOrgUnitNo, long periodId, int accountYear, int accountPeriod, Param param)
			throws AppException {
		if(taskId > 0 && StringUtils.isNotBlank(finOrgUnitNo)){
			HashMap<String, Object> map = new HashMap<>();
			map.put("taskId", taskId);
			map.put("finOrgUnitNo", finOrgUnitNo);
			map.put("periodId", periodId);
			map.put("accountYear", accountYear);
			map.put("accountPeriod", accountPeriod);
			((ScmInvBalDAO)dao).addByCostCenter(map);
		}
	}

	@Override
	public void updateByCostCenter(long taskId, String finOrgUnitNo, int accountYear, int accountPeriod, Param param)
			throws AppException {
		if(taskId > 0 && StringUtils.isNotBlank(finOrgUnitNo)){
			HashMap<String, Object> map = new HashMap<>();
			map.put("taskId", taskId);
			map.put("finOrgUnitNo", finOrgUnitNo);
			map.put("accountYear", accountYear);
			map.put("accountPeriod", accountPeriod);
			((ScmInvBalDAO)dao).updateByCostCenter(map);
		}
	}

	@Override
	public void addByOtherInWarehsBillForInvProfit(long wrId, Param param) throws AppException {
		if(wrId > 0){
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("wrId",wrId);
			((ScmInvBalDAO)dao).addByOtherInWarehsBillForInvProfit(map);
		}
	}
	
	@Override
	public void addByOtherInWarehsBillForInvDataSync(long wrId, Param param) throws AppException {
		if(wrId > 0){
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("wrId",wrId);
			((ScmInvBalDAO)dao).addByOtherInWarehsBillForInvDataSync(map);
		}
	}
	
	@Override
	public void addByOtherInWarehsBillForCancel(long wrId, Param param) throws AppException {
		if(wrId > 0){
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("wrId",wrId);
			((ScmInvBalDAO)dao).addByOtherInWarehsBillForCancel(map);
		}
	}

	@Override
	public void updateByOtherInWarehsBillForInvProfit(long wrId, Param param) throws AppException {
		if(wrId > 0){
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("wrId",wrId);
			((ScmInvBalDAO)dao).updateByOtherInWarehsBillForInvProfit(map);
		}
	}
	
	@Override
	public void updateByOtherInWarehsBillForDataSnyc(long wrId, Param param) throws AppException {
		if(wrId > 0){
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("wrId",wrId);
			((ScmInvBalDAO)dao).updateByOtherInWarehsBillForDataSnyc(map);
		}
	}

	@Override
	public void updateByCancelOtherInWarehsBillForInvProfit(long wrId, Param param) throws AppException {
		if(wrId > 0){
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("wrId",wrId);
			((ScmInvBalDAO)dao).updateByCancelOtherInWarehsBillForInvProfit(map);
		}
	}
	
	@Override
	public void updateByCancelOtherInWarehsBillForDataSnyc(long wrId, Param param) throws AppException {
		if(wrId > 0){
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("wrId",wrId);
			((ScmInvBalDAO)dao).updateByCancelOtherInWarehsBillForDataSnyc(map);
		}
	}

	@Override
	public void updateByCancelMoveInWarehsBill(long wrId, Param param)
			throws AppException {
		if(wrId > 0){
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("wrId",wrId);
			((ScmInvBalDAO)dao).updateByCancelMoveInWarehsBill(map);
		}
	}

	@Override
	public void updateByCostConsume(long dcId, Param param) throws AppException {
		if(dcId > 0){
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("dcId",dcId);
			((ScmInvBalDAO)dao).updateByCostConsume(map);
		}
	}

	@Override
	public void updateByCancelCostConsume(long dcId, Param param)
			throws AppException {
		if(dcId > 0){
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("dcId",dcId);
			((ScmInvBalDAO)dao).updateByCancelCostConsume(map);
		}
	}
	
	@Override
	public void addByCostConsumPost(long dcId, Param param) throws AppException {
		if(dcId > 0){
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("dcId",dcId);
			((ScmInvBalDAO)dao).addByCostConsumPost(map);
		}
	}
	
	@Override
	public void addByCostConsumCancelPost(long dcId, Param param) throws AppException {
		if(dcId > 0){
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("dcId",dcId);
			((ScmInvBalDAO)dao).addByCostConsumCancelPost(map);
		}
	}

	@Override
	public void calcEndBal(String orgUnitNo, long periodId,Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("orgUnitNo",orgUnitNo);
		map.put("periodId",periodId);
		((ScmInvBalDAO)dao).calcEndBal(map);
	}

	@Override
	public void buildNextPeriodStartBal(String orgUnitNo, long periodId,long nextPeriodId,int accountYear,int accountPeriod,Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("orgUnitNo",orgUnitNo);
		map.put("periodId",periodId);
		map.put("nextPeriodId",nextPeriodId);
		map.put("accountYear",accountYear);
		map.put("accountPeriod",accountPeriod);
		((ScmInvBalDAO)dao).buildNextPeriodStartBal(map);
	}

	@Override
	public void clearStartBal(String orgUnitNo, long periodId,Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("orgUnitNo",orgUnitNo);
		map.put("periodId",periodId);
		((ScmInvBalDAO)dao).clearStartBal(map);
	}

	@Override
	public void delDeplete(String orgUnitNo, long periodId, Param param) throws AppException {
		//获取财务组织下以领代耗的成本中心
		Page page = new Page();
		page.setModelClass(OrgCostCenter2.class);
		page.setShowCount(Integer.MAX_VALUE);
		List<String> argList = new ArrayList<String>();
		argList.add("type=from");
        argList.add("relationType=" + OrgUnitRelationType.COSTTOFIN);
        argList.add("toOrgUnitNo=" + orgUnitNo);
		List<OrgCostCenter2> orgCostCenterList = orgCostCenterBiz.queryPage(page, argList, "queryPageEx", param);
		StringBuffer costOrgUnitNos = new StringBuffer("");
		if(orgCostCenterList!=null && !orgCostCenterList.isEmpty()) {
			for(OrgCostCenter2 orgCostCenter:orgCostCenterList) {
				if(StringUtils.equals("1",orgCostCenter.getCostCenterType())) {
					if(StringUtils.isNotBlank(costOrgUnitNos.toString()))
						costOrgUnitNos.append(",");
					costOrgUnitNos.append("'").append(orgCostCenter.getOrgUnitNo()).append("'");
				}
			}
		}
		//删除该财务下以领代耗的成本中心数据
		if(StringUtils.isNotBlank(costOrgUnitNos.toString())) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("orgUnitNo", orgUnitNo);
			map.put("costOrgUnitNos", costOrgUnitNos);
			((ScmInvBalDAO)dao).delDeplete(map);
		}
	}

	@Override
	public void updateByDepositInReturn(long wrId, Param param) throws AppException {
		if(wrId > 0){
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("wrId",wrId);
			((ScmInvBalDAO)dao).updateByDepositInReturn(map);
		}
	}

	@Override
	public void updateByCancelDepositInReturn(long wrId, Param param) throws AppException {
		if(wrId > 0){
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("wrId",wrId);
			((ScmInvBalDAO)dao).updateByCancelDepositInReturn(map);
		}
	}
	
	@Override
	public void updateByCstFrmLoss(long billId, Param param) throws AppException {
		if(billId > 0){
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("billId",billId);
			((ScmInvBalDAO)dao).updateByCstFrmLoss(map);
		}
	}

	@Override
	public void updateByCancelCstFrmLoss(long billId, Param param)
			throws AppException {
		if(billId > 0){
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("billId",billId);
			((ScmInvBalDAO)dao).updateByCancelCstFrmLoss(map);
		}
	}

}

