package com.armitage.server.iscm.inventorymanage.inventorydata.service.impl;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Page;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.base.model.QueryParam;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.util.FormatUtils;
import com.armitage.server.iscm.basedata.model.PubSysBasicInfo;
import com.armitage.server.iscm.basedata.model.ScmMaterial2;
import com.armitage.server.iscm.basedata.model.ScmMaterialGroup;
import com.armitage.server.iscm.basedata.model.ScmMeasureUnit;
import com.armitage.server.iscm.basedata.model.Scmsupplier2;
import com.armitage.server.iscm.basedata.service.PubSysBasicInfoBiz;
import com.armitage.server.iscm.basedata.service.ScmMaterialBiz;
import com.armitage.server.iscm.basedata.service.ScmMaterialGroupBiz;
import com.armitage.server.iscm.basedata.service.ScmMeasureUnitBiz;
import com.armitage.server.iscm.basedata.service.ScmsupplierBiz;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCostConsumeEntry2;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCountingCostCenterEntry2;
import com.armitage.server.iscm.inventorymanage.cstbusiness.service.ScmInvCostConsumeEntryBiz;
import com.armitage.server.iscm.inventorymanage.cstbusiness.service.ScmInvCountingCostCenterEntryBiz;
import com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvStockDAO;
import com.armitage.server.iscm.inventorymanage.inventorydata.model.ScmDeptConsumeQuery;
import com.armitage.server.iscm.inventorymanage.inventorydata.model.ScmFinDeptConsume;
import com.armitage.server.iscm.inventorymanage.inventorydata.model.ScmInvRealtimeStockQuery;
import com.armitage.server.iscm.inventorymanage.inventorydata.model.ScmInvStock2;
import com.armitage.server.iscm.inventorymanage.inventorydata.service.ScmInvStockBiz;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvWareHouse;
import com.armitage.server.iscm.inventorymanage.inventorysetting.service.ScmInvWareHouseBiz;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvPurInWarehsBillEntry2;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.service.ScmInvPurInWarehsBillEntryBiz;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurRequire2;
import com.armitage.server.system.model.OrgBaseUnit;
import com.armitage.server.system.model.OrgCostCenter2;
import com.armitage.server.system.model.OrgStorage2;
import com.armitage.server.system.service.OrgBaseUnitBiz;
import com.armitage.server.system.service.OrgCostCenterBiz;
import com.armitage.server.system.service.OrgStorageBiz;
import com.armitage.server.system.service.SysParamBiz;
import com.armitage.server.system.util.OrgUnitRelationType;
import org.springframework.stereotype.Service;

@Service("scmInvStockBiz")
public class ScmInvStockBizImpl extends BaseBizImpl<ScmInvStock2> implements ScmInvStockBiz {
	private OrgStorageBiz orgStorageBiz;
    private ScmMaterialBiz scmMaterialBiz;
    private OrgBaseUnitBiz orgBaseUnitBiz;
    private ScmsupplierBiz scmsupplierBiz;
    private ScmInvWareHouseBiz scmInvWareHouseBiz;
    private ScmMeasureUnitBiz scmMeasureUnitBiz;
    private ScmMaterialGroupBiz scmMaterialGroupBiz;
	private OrgCostCenterBiz orgCostCenterBiz;
	private ScmInvCostConsumeEntryBiz scmInvCostConsumeEntryBiz;
	private ScmInvCountingCostCenterEntryBiz scmInvCountingCostCenterEntryBiz;
	private ScmInvPurInWarehsBillEntryBiz scmInvPurInWarehsBillEntryBiz;
	private PubSysBasicInfoBiz pubSysBasicInfoBiz;
	private SysParamBiz sysParamBiz;
	
    public void setScmInvCostConsumeEntryBiz(ScmInvCostConsumeEntryBiz scmInvCostConsumeEntryBiz) {
		this.scmInvCostConsumeEntryBiz = scmInvCostConsumeEntryBiz;
	}
	public void setScmInvCountingCostCenterEntryBiz(ScmInvCountingCostCenterEntryBiz scmInvCountingCostCenterEntryBiz) {
		this.scmInvCountingCostCenterEntryBiz = scmInvCountingCostCenterEntryBiz;
	}
	public void setScmInvPurInWarehsBillEntryBiz(ScmInvPurInWarehsBillEntryBiz scmInvPurInWarehsBillEntryBiz) {
		this.scmInvPurInWarehsBillEntryBiz = scmInvPurInWarehsBillEntryBiz;
	}
	public void setOrgStorageBiz(OrgStorageBiz orgStorageBiz) {
        this.orgStorageBiz = orgStorageBiz;
    }
    public void setScmMaterialBiz(ScmMaterialBiz scmMaterialBiz) {
        this.scmMaterialBiz = scmMaterialBiz;
    }
    public void setOrgBaseUnitBiz(OrgBaseUnitBiz orgBaseUnitBiz) {
        this.orgBaseUnitBiz = orgBaseUnitBiz;
    }
    public void setScmsupplierBiz(ScmsupplierBiz scmsupplierBiz) {
        this.scmsupplierBiz = scmsupplierBiz;
    }
    public void setScmInvWareHouseBiz(ScmInvWareHouseBiz scmInvWareHouseBiz) {
        this.scmInvWareHouseBiz = scmInvWareHouseBiz;
    }
    public void setScmMeasureUnitBiz(ScmMeasureUnitBiz scmMeasureUnitBiz) {
        this.scmMeasureUnitBiz = scmMeasureUnitBiz;
    }
    public void setScmMaterialGroupBiz(ScmMaterialGroupBiz scmMaterialGroupBiz) {
        this.scmMaterialGroupBiz = scmMaterialGroupBiz;
    }
    public void setOrgCostCenterBiz(OrgCostCenterBiz orgCostCenterBiz) {
		this.orgCostCenterBiz = orgCostCenterBiz;
	}
    public void setPubSysBasicInfoBiz(PubSysBasicInfoBiz pubSysBasicInfoBiz) {
		this.pubSysBasicInfoBiz = pubSysBasicInfoBiz;
	}
    public void setSysParamBiz(SysParamBiz sysParamBiz) {
		this.sysParamBiz = sysParamBiz;
	}
    
	@Override
	public int addByOtherInWarehsBill(long wrId, long unitedBillId, Param param) throws AppException {
		if(wrId > 0){
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("wrId",wrId);
			map.put("unitedBillId",unitedBillId);
			return ((ScmInvStockDAO)dao).addByOtherInWarehsBill(map);
		}
		return 0;
	}

	@Override
	public int updateByOtherInWarehsBill(long wrId, Param param) throws AppException {
		if(wrId > 0){
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("wrId",wrId);
			return ((ScmInvStockDAO)dao).updateByOtherInWarehsBill(map);
		}
		return 0;
	}
	
	@Override
	public int addByPurInWarehsBill(long wrId, long unitedBillId, String orgUnitNoList, Param param) throws AppException {
		if(wrId > 0){
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("wrId",wrId);
			map.put("unitedBillId",unitedBillId);
			map.put("orgUnitNoList",orgUnitNoList);
			return ((ScmInvStockDAO)dao).addByPurInWarehsBill(map);
		}
		return 0;
	}

	@Override
	public int updateByPurInWarehsBill(long wrId, String orgUnitNoList, Param param) throws AppException {
		if(wrId > 0){
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("wrId",wrId);
			map.put("orgUnitNoList",orgUnitNoList);
			return ((ScmInvStockDAO)dao).updateByPurInWarehsBill(map);
		}
		return 0;
	}

    @Override
    public int addByMoveBillIn(long wtId, Param param) throws AppException {
        if(wtId > 0){
            HashMap<String,Object> map = new HashMap<String,Object>();
            map.put("wtId",wtId);
            return ((ScmInvStockDAO)dao).addByMoveBillIn(map);
        }
        return 0;
    }

    @Override
    public int updateByMoveBillOutSub(long wtId, Param param) throws AppException {
        if(wtId > 0){
            HashMap<String,Object> map = new HashMap<String,Object>();
            map.put("wtId",wtId);
            return ((ScmInvStockDAO)dao).updateByMoveBillOutSub(map);
        }
        return 0;
    }

    @Override
    public int updateByMoveBillInPlus(long wtId, Param param) throws AppException {
        if(wtId > 0){
            HashMap<String,Object> map = new HashMap<String,Object>();
            map.put("wtId",wtId);
            return ((ScmInvStockDAO)dao).updateByMoveBillInPlus(map);
        }
        return 0;
    }

    @Override
    public int addByMoveBillOut(long wtId, Param param) throws AppException {
        if(wtId > 0){
            HashMap<String,Object> map = new HashMap<String,Object>();
            map.put("wtId",wtId);
            return ((ScmInvStockDAO)dao).addByMoveBillOut(map);
        }
        return 0;
    }

    @Override
    public int updateByMoveBillOutPlus(long wtId, Param param) throws AppException {
        if(wtId > 0){
            HashMap<String,Object> map = new HashMap<String,Object>();
            map.put("wtId",wtId);
            return ((ScmInvStockDAO)dao).updateByMoveBillOutPlus(map);
        }
        return 0;
    }

    @Override
    public int updateByMoveBillInSub(long wtId, Param param) throws AppException {
        if(wtId > 0){
            HashMap<String,Object> map = new HashMap<String,Object>();
            map.put("wtId",wtId);
            return ((ScmInvStockDAO)dao).updateByMoveBillInSub(map);
        }
        return 0;
    }

    @Override
    public int updateBySaleIssuePostSub(long otId, Param param) {
        if(otId > 0){
            HashMap<String,Object> map = new HashMap<String,Object>();
            map.put("otId",otId);
            return ((ScmInvStockDAO)dao).updateBySaleIssuePostSub(map);
        }
        return 0;
    }

    @Override
    public int updateBySaleIssueUnPost(long otId, String flag, Param param) {
        if(otId > 0){
            HashMap<String,Object> map = new HashMap<String,Object>();
            map.put("otId",otId);
            map.put("flag",flag);
            return ((ScmInvStockDAO)dao).updateBySaleIssueUnPost(map);
        }
        return 0;
    }

    @Override
    public int updateBySaleIssueNotOffset(long otId, Param param) {
        if(otId > 0){
            HashMap<String,Object> map = new HashMap<String,Object>();
            map.put("otId",otId);
            return ((ScmInvStockDAO)dao).updateBySaleIssueNotOffset(map);
        }
        return 0;
    }

    @Override
    public int addBySaleIssuePost(long otId, Param param) {
        if(otId > 0){
            HashMap<String,Object> map = new HashMap<String,Object>();
            map.put("otId",otId);
            return ((ScmInvStockDAO)dao).addBySaleIssuePost(map);
        }
        return 0;
    }

	@Override
	public int updateByOtherIssueOutSub(long otId, Param param) throws AppException {
		 if(otId > 0){
            HashMap<String,Object> map = new HashMap<String,Object>();
            map.put("otId",otId);
            return ((ScmInvStockDAO)dao).updateByOtherIssueOutSub(map);
		 }
		 return 0;
	}

	@Override
	public int updateByOtherIssueInSub(long otId, Param param) throws AppException {
		if (otId > 0) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("otId", otId);
			return ((ScmInvStockDAO) dao).updateByOtherIssueInSub(map);
		}
		return 0;
	}

    @Override
    public List<ScmInvStock2> findByDate(HashMap<String, Object> map, Param param) throws AppException {
    	if (map.containsKey("wareHouseId") && Integer.parseInt(map.get("wareHouseId").toString())>0) {
			String expirationOut = sysParamBiz.getValue((String)map.get("orgUnitNo"), "SCM_ExpirationOut", "N", param);
			if (StringUtils.equals("Y", expirationOut)) {
				map.put("expirationOut", expirationOut);
			}
		}else {
			Page page1 = new Page();
	        page1.setModelClass(OrgStorage2.class);
	        page1.setShowCount(Integer.MAX_VALUE);
			List<String> arglist1 = new ArrayList<String>();
			arglist1.add("type=to");
			arglist1.add("relationType="+OrgUnitRelationType.ADMINTOINV);
			arglist1.add("fromOrgUnitNo="+map.get("orgUnitNo"));
			List<OrgStorage2> orgStorageList = orgStorageBiz.queryPage(page1, arglist1, "queryPageEx", param);
			String invOrgUnitNo = "";
			if (orgStorageList != null && !orgStorageList.isEmpty()) {
				boolean exists = false;
				for (OrgStorage2 orgStorage2 : orgStorageList) {
					if (orgStorage2.isDefault()) {
						invOrgUnitNo=orgStorage2.getOrgUnitNo();
						exists=true;
						break;
					}
				}
				if (!exists) {
	                if (!StringUtils.equals(invOrgUnitNo, orgStorageList.get(0).getOrgUnitNo())) {
	                    invOrgUnitNo = orgStorageList.get(0).getOrgUnitNo();
	                }
	            }
			}
			String expirationOut = sysParamBiz.getValue(invOrgUnitNo, "SCM_ExpirationOut", "N", param);
			if (StringUtils.equals("Y", expirationOut)) {
				map.put("expirationOut", expirationOut);
			}
		}
        return ((ScmInvStockDAO) dao).findByDate(map);
    }

	@Override
	public List<ScmInvStock2> findByOutWarehouse(HashMap<String, Object> map, Param param) throws AppException {
		if (map.containsKey("wareHouseId") && Integer.parseInt(map.get("wareHouseId").toString())>0) {
			String expirationOut = sysParamBiz.getValue((String)map.get("orgUnitNo"), "SCM_ExpirationOut", "N", param);
			if (StringUtils.equals("Y", expirationOut)) {
				map.put("expirationOut", expirationOut);
			}
		}else {
			Page page1 = new Page();
	        page1.setModelClass(OrgStorage2.class);
	        page1.setShowCount(Integer.MAX_VALUE);
			List<String> arglist1 = new ArrayList<String>();
			arglist1.add("type=to");
			arglist1.add("relationType="+OrgUnitRelationType.ADMINTOINV);
			arglist1.add("fromOrgUnitNo="+map.get("orgUnitNo"));
			List<OrgStorage2> orgStorageList = orgStorageBiz.queryPage(page1, arglist1, "queryPageEx", param);
			String invOrgUnitNo = "";
			if (orgStorageList != null && !orgStorageList.isEmpty()) {
				boolean exists = false;
				for (OrgStorage2 orgStorage2 : orgStorageList) {
					if (orgStorage2.isDefault()) {
						invOrgUnitNo=orgStorage2.getOrgUnitNo();
						exists=true;
						break;
					}
				}
				if (!exists) {
	                if (!StringUtils.equals(invOrgUnitNo, orgStorageList.get(0).getOrgUnitNo())) {
	                    invOrgUnitNo = orgStorageList.get(0).getOrgUnitNo();
	                }
	            }
			}
			String expirationOut = sysParamBiz.getValue(invOrgUnitNo, "SCM_ExpirationOut", "N", param);
			if (StringUtils.equals("Y", expirationOut)) {
				map.put("expirationOut", expirationOut);
			}
		}
		return ((ScmInvStockDAO) dao).findByOutWarehouse(map);
	}

	@Override
	public List<ScmInvStock2> findByReturnWarehouse(HashMap<String, Object> map, Param param) throws AppException {
		if (map.containsKey("wareHouseId") && Integer.parseInt(map.get("wareHouseId").toString())>0) {
			String expirationOut = sysParamBiz.getValue((String)map.get("useOrgUnitNo"), "SCM_ExpirationOut", "N", param);
			if (StringUtils.equals("Y", expirationOut)) {
				map.put("expirationOut", expirationOut);
			}
		}else {
			Page page1 = new Page();
	        page1.setModelClass(OrgStorage2.class);
	        page1.setShowCount(Integer.MAX_VALUE);
			List<String> arglist1 = new ArrayList<String>();
			arglist1.add("type=to");
			arglist1.add("relationType="+OrgUnitRelationType.ADMINTOINV);
			arglist1.add("fromOrgUnitNo="+map.get("useOrgUnitNo"));
			List<OrgStorage2> orgStorageList = orgStorageBiz.queryPage(page1, arglist1, "queryPageEx", param);
			String invOrgUnitNo = "";
			if (orgStorageList != null && !orgStorageList.isEmpty()) {
				boolean exists = false;
				for (OrgStorage2 orgStorage2 : orgStorageList) {
					if (orgStorage2.isDefault()) {
						invOrgUnitNo=orgStorage2.getOrgUnitNo();
						exists=true;
						break;
					}
				}
				if (!exists) {
	                if (!StringUtils.equals(invOrgUnitNo, orgStorageList.get(0).getOrgUnitNo())) {
	                    invOrgUnitNo = orgStorageList.get(0).getOrgUnitNo();
	                }
	            }
			}
			String expirationOut = sysParamBiz.getValue(invOrgUnitNo, "SCM_ExpirationOut", "N", param);
			if (StringUtils.equals("Y", expirationOut)) {
				map.put("expirationOut", expirationOut);
			}
		}
		return ((ScmInvStockDAO) dao).findByReturnWarehouse(map);
	}

	@Override
	public int addByMaterialReqBillOutOrgunitNo(long otId, Param param) throws AppException {
		if(otId > 0){
            HashMap<String,Object> map = new HashMap<String,Object>();
            map.put("otId",otId);
            return ((ScmInvStockDAO)dao).addByMaterialReqBillOutOrgunitNo(map);
		}
		return 0;
	}

	@Override
	public int addByMaterialReqBillReturn(long otId, Param param) throws AppException {
		if(otId > 0){
            HashMap<String,Object> map = new HashMap<String,Object>();
            map.put("otId",otId);
            return ((ScmInvStockDAO)dao).addByMaterialReqBillReturn(map);
		}
		return 0;
	}

	@Override
	public int updateByMaterialReqBillOut(long otId, Param param) throws AppException {
		if(otId > 0){
            HashMap<String,Object> map = new HashMap<String,Object>();
            map.put("otId",otId);
            return ((ScmInvStockDAO)dao).updateByMaterialReqBillOut(map);
		}
		return 0;
	}
	
	@Override
	public int updateByMaterialReqBillInOrgunitNo(long otId, Param param) throws AppException {
		if(otId > 0){
            HashMap<String,Object> map = new HashMap<String,Object>();
            map.put("otId",otId);
            return ((ScmInvStockDAO)dao).updateByMaterialReqBillInOrgunitNo(map);
		}
		return 0;
	}
	
	@Override
	public int updateByMaterialReqBillIn(long otId, Param param) throws AppException {
		if(otId > 0){
            HashMap<String,Object> map = new HashMap<String,Object>();
            map.put("otId",otId);
            return ((ScmInvStockDAO)dao).updateByMaterialReqBillIn(map);
		}
		return 0;
	}

	@Override
	public int updateByMaterialReqBillOutOrgunitNo(long otId, Param param) throws AppException {
		if(otId > 0){
            HashMap<String,Object> map = new HashMap<String,Object>();
            map.put("otId",otId);
            return ((ScmInvStockDAO)dao).updateByMaterialReqBillOutOrgunitNo(map);
		}
		return 0;
	}

	@Override
	public int updateByMaterialReqBillReturn(long otId, Param param) throws AppException {
		if(otId > 0){
            HashMap<String,Object> map = new HashMap<String,Object>();
            map.put("otId",otId);
            return ((ScmInvStockDAO)dao).updateByMaterialReqBillReturn(map);
		}
		return 0;
	}
	
	@Override
	public int updateByMaterialReqBillOrgunitNo(long otId, Param param) throws AppException {
		if(otId > 0){
            HashMap<String,Object> map = new HashMap<String,Object>();
            map.put("otId",otId);
            return ((ScmInvStockDAO)dao).updateByMaterialReqBillOrgunitNo(map);
		}
		return 0;
	}

	@Override
	public int updateByMaterialReqBill(long otId, Param param) throws AppException {
		if(otId > 0){
            HashMap<String,Object> map = new HashMap<String,Object>();
            map.put("otId",otId);
            return ((ScmInvStockDAO)dao).updateByMaterialReqBill(map);
		}
		return 0;
	}

	@Override
	public int updateByMaterialReqBillReturnOrgunitNo(long otId, Param param) throws AppException {
		if(otId > 0){
            HashMap<String,Object> map = new HashMap<String,Object>();
            map.put("otId",otId);
            return ((ScmInvStockDAO)dao).updateByMaterialReqBillReturnOrgunitNo(map);
		}
		return 0;
	}

	@Override
	public int updateByCancelOtherInWarehsBill(long wrId, Param param) throws AppException {
		if(wrId > 0){
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("wrId",wrId);
			return ((ScmInvStockDAO)dao).updateByCancelOtherInWarehsBill(map);
		}
		return 0;
	}

    @Override
    public List<ScmInvStock2> selectPriceByStock(String orgUnitNo, long itemId, long unit, Param param)
            throws AppException {
        if (itemId > 0) {
            HashMap<String,Object> map = new HashMap<String,Object>();
            map.put("orgUnitNo", orgUnitNo);
            map.put("itemId", itemId);
            map.put("unit", unit);
            return ((ScmInvStockDAO)dao).selectPriceByStock(map);
        }
        return null;
    }

	@Override
	public int updateByInitBill(long initId, Param param) throws AppException {
		if(initId > 0){
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("initId",initId);
			return ((ScmInvStockDAO)dao).updateByInitBill(map);
		}
		return 0;
	}

	@Override
	public int addByInitBill(long initId, Param param)
			throws AppException {
		if(initId > 0){
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("initId",initId);
			return ((ScmInvStockDAO)dao).addByInitBill(map);
		}
		return 0;
	}

	@Override
	public int updateByCancelInitBill(long initId, Param param)
			throws AppException {
		if(initId > 0){
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("initId", initId);
			return ((ScmInvStockDAO)dao).updateByCancelInitBill(map);
		}
		return 0;
	}

	@Override
	public int updateByCancelPurInWarehsBill(long wrId, Param param) throws AppException {
		if(wrId > 0){
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("wrId",wrId);
			return ((ScmInvStockDAO)dao).updateByCancelPurInWarehsBill(map);
		}
		return 0;
	}

	@Override
	public List<ScmInvStock2> findByOutAndReturnWarehouse(HashMap<String, Object> map, Param param) throws AppException {
		return ((ScmInvStockDAO) dao).findByOutAndReturnWarehouse(map);
	}

    @Override
    public int updateByMoveIssuePost(long otId, Param param) throws AppException {
        if(otId > 0){
            HashMap<String,Object> map = new HashMap<String,Object>();
            map.put("otId",otId);
            return ((ScmInvStockDAO)dao).updateByMoveIssuePost(map);
        }
        return 0;
    }

    @Override
    public int updateByMoveIssueUnPost(long otId, Param param) throws AppException {
        if(otId > 0){
            HashMap<String,Object> map = new HashMap<String,Object>();
            map.put("otId",otId);
            return ((ScmInvStockDAO)dao).updateByMoveIssueUnPost(map);
        }
        return 0;
    }
	
	@Override
	public int updateByPurInWarehsBillOut(long wrId, Param param) throws AppException {
		if(wrId > 0){
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("wrId",wrId);
			return ((ScmInvStockDAO)dao).updateByPurInWarehsBillOut(map);
		}
		return 0;
	}
	
	@Override
	public int updateByCancelPurInWarehsBillOut(long wrId, Param param) throws AppException {
		if(wrId > 0){
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("wrId",wrId);
			return ((ScmInvStockDAO)dao).updateByCancelPurInWarehsBillOut(map);
		}
		return 0;
	}

    @Override
    public int updateByMoveInWarehsBill(long wrId, Param param) throws AppException {
        if(wrId > 0){
            HashMap<String,Object> map = new HashMap<String,Object>();
            map.put("wrId",wrId);
            return ((ScmInvStockDAO)dao).updateByMoveInWarehsBill(map);
        }
        return 0;
    }

    @Override
    public int addByMoveInWarehsBill(long wrId, Param param) throws AppException {
        if(wrId > 0){
            HashMap<String,Object> map = new HashMap<String,Object>();
            map.put("wrId",wrId);
            return ((ScmInvStockDAO)dao).addByMoveInWarehsBill(map);
        }
        return 0;
    }
	@Override
	public int updateByCstInitBill(long initId, Param param)
			throws AppException {
		if(initId > 0){
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("initId",initId);
			return ((ScmInvStockDAO)dao).updateByCstInitBill(map);
		}
		return 0;
	}

	@Override
	public int addByCstInitBill(long initId, Param param) throws AppException {
		if(initId > 0){
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("initId",initId);
			return ((ScmInvStockDAO)dao).addByCstInitBill(map);
		}
		return 0;
	}

	@Override
	public int updateByCancelCstInitBill(long initId, Param param)
			throws AppException {
		if(initId > 0){
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("initId", initId);
			return ((ScmInvStockDAO)dao).updateByCancelCstInitBill(map);
		}
		return 0;
	}

	@Override
	public int checkCostCenter(long taskId, String finOrgUnitNo, Param param) throws AppException {
		if(taskId > 0 && StringUtils.isNotBlank(finOrgUnitNo)){
			HashMap<String, Object> map = new HashMap<>();
			map.put("taskId", taskId);
			map.put("finOrgUnitNo", finOrgUnitNo);
			return ((ScmInvStockDAO)dao).checkCostCenter(map);
		}
		return 0;
	}

	@Override
	public int checkCostCenter2(long taskId, String finOrgUnitNo, Param param) throws AppException {
		if(taskId > 0 && StringUtils.isNotBlank(finOrgUnitNo)){
			HashMap<String, Object> map = new HashMap<>();
			map.put("taskId", taskId);
			map.put("finOrgUnitNo", finOrgUnitNo);
			return ((ScmInvStockDAO)dao).checkCostCenter2(map);
		}
		return 0;
	}

	@Override
	public int addByCostCenter(long taskId, String finOrgUnitNo, Param param) throws AppException {
		if(taskId > 0 && StringUtils.isNotBlank(finOrgUnitNo)){
			HashMap<String, Object> map = new HashMap<>();
			map.put("taskId", taskId);
			map.put("finOrgUnitNo", finOrgUnitNo);
			return ((ScmInvStockDAO)dao).addByCostCenter(map);
		}
		return 0;
	}

	@Override
	public int updateByCostCenter(long taskId, String finOrgUnitNo, Param param) throws AppException {
		if(taskId > 0 && StringUtils.isNotBlank(finOrgUnitNo)){
			HashMap<String, Object> map = new HashMap<>();
			map.put("taskId", taskId);
			map.put("finOrgUnitNo", finOrgUnitNo);
			return ((ScmInvStockDAO)dao).updateByCostCenter(map);
		}
		return 0;
	}

	@Override
	public int updateByCostConsume(long dcId, Param param) throws AppException {
		if(dcId > 0){
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("dcId",dcId);
			return ((ScmInvStockDAO)dao).updateByCostConsume(map);
		}
		return 0;
	}

	@Override
	public int updateByCancelCostConsume(long dcId, Param param)
			throws AppException {
		if(dcId > 0){
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("dcId", dcId);
			return ((ScmInvStockDAO)dao).updateByCancelCostConsume(map);
		}
		return 0;
	}

	@Override
	public int updateByCancelMoveInWarehsBill(long wrId, Param param)
			throws AppException {
		if(wrId > 0){
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("wrId",wrId);
			return ((ScmInvStockDAO)dao).updateByCancelMoveInWarehsBill(map);
		}
		return 0;
	}

	@Override
	public int deleteZeroQty(String orgUnitNo, boolean costCenter, Param param)	throws AppException {
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
		if(StringUtils.isBlank(costOrgUnitNos.toString())) {
			costOrgUnitNos=costOrgUnitNos.append("'0'");
		}
		HashMap<String, Object> map = new HashMap<>();
		map.put("orgUnitNo", orgUnitNo);
		map.put("costOrgUnitNos", costOrgUnitNos);
		return ((ScmInvStockDAO)dao).deleteZeroQty(map);
	}

	@Override
	public int writeBackZeroQty(String orgUnitNo, boolean costCenter,long periodId, Param param) throws AppException {
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
		if(StringUtils.isBlank(costOrgUnitNos.toString())) {
			costOrgUnitNos=costOrgUnitNos.append("'0'");
		}
		HashMap<String, Object> map = new HashMap<>();
		map.put("orgUnitNo", orgUnitNo);
		map.put("periodId", periodId);
		map.put("costCenter", costCenter?"1":"0");
		map.put("costOrgUnitNos", costOrgUnitNos);
		return ((ScmInvStockDAO)dao).writeBackZeroQty(map);
	}

	@Override
	public List<ScmInvStock2> findByWareHouse(HashMap<String, Object> map, Param param) throws AppException {
		return ((ScmInvStockDAO) dao).findByWareHouse(map);
	}
	@Override
	public List<ScmInvStock2> findByUseOrgUnitNo(HashMap<String, Object> map, Param param) throws AppException {
		return ((ScmInvStockDAO) dao).findByUseOrgUnitNo(map);
	}
    
	@Override
	public List<ScmInvStock2> selectRealtimeStock(ScmInvRealtimeStockQuery scmInvRealtimeStockQuery,int pageNum, Param param) throws AppException {
		Page page=new Page();
		page.setModelClass(ScmPurRequire2.class);
		if (pageNum == -1) {
			page.setPagePos(1);
			page.setShowCount(Integer.MAX_VALUE);
		} else {
			page.setPagePos(pageNum);
			page.setShowCount(20);
		}
		// 1 获取查询参数
        HashMap<String, Object> map = new HashMap<>();
        String invOrgUnitNo = scmInvRealtimeStockQuery.getOrgUnitNo();
        long vendorId = scmInvRealtimeStockQuery.getVendorId();
        if (vendorId==0 && StringUtils.isNotBlank(scmInvRealtimeStockQuery.getVendorCode())) {
        	Scmsupplier2 scmsupplier = scmsupplierBiz.selectByCode(scmInvRealtimeStockQuery.getVendorCode(), param);
        	if(scmsupplier!=null)
        		vendorId = scmsupplier.getId();
        }
        if(vendorId>0){
        	page.getParam().put(ScmInvStock2.FN_VENDORID,new QueryParam(ScmInvStock2.FN_VENDORID,QueryParam.QUERY_EQ, String.valueOf(vendorId)));
        }
        long materialId =scmInvRealtimeStockQuery.getItemId();
        if (materialId==0 && StringUtils.isNotBlank(scmInvRealtimeStockQuery.getItemNo())) {
        	ScmMaterial2 scmMaterial = scmMaterialBiz.selectByItemNo(param.getControlUnitNo(), scmInvRealtimeStockQuery.getItemNo(), param);
        	if(scmMaterial!=null)
        		materialId = scmMaterial.getId();
        }
        if(materialId>0){
        	page.getParam().put(ScmInvStock2.FN_ITEMID,new QueryParam(ScmInvStock2.FN_ITEMID,QueryParam.QUERY_EQ, String.valueOf(materialId)));
        }
        long materialClassId = scmInvRealtimeStockQuery.getItemClassId();
        if (materialClassId==0 && StringUtils.isNotBlank(scmInvRealtimeStockQuery.getItemClass())) {
        	ScmMaterialGroup scmMaterialGroup = scmMaterialGroupBiz.selectByClassCode(scmInvRealtimeStockQuery.getItemClass(), param);
        	if(scmMaterialGroup!=null)
        		materialClassId = scmMaterialGroup.getId();
            
        }
        if(materialClassId>0){
	        List<ScmMaterialGroup> scmMaterialGroupList = scmMaterialGroupBiz.findChild(materialClassId, param);
	        StringBuffer sbMaterilaClass = new StringBuffer("");
	        if (scmMaterialGroupList != null && scmMaterialGroupList.size() > 0) {
	            sbMaterilaClass.append("(");
	            for(int i=0; i<scmMaterialGroupList.size(); i++) {
	                sbMaterilaClass.append(scmMaterialGroupList.get(i).getId());
	                if (i == scmMaterialGroupList.size()-1) {
	                    break;
	                }
	                sbMaterilaClass.append(",");
	            }
	            sbMaterilaClass.append(")");
	            page.setSqlCondition("ScmInvStock.itemId in(Select itemId From scmmaterialgroupdetail Where classId in "+sbMaterilaClass.toString()+")");
	        }
        }
        long wareHouseId = scmInvRealtimeStockQuery.getWareHouseId();
        if (wareHouseId==0 && StringUtils.isNotBlank(scmInvRealtimeStockQuery.getWareHouseNo())) {
        	ScmInvWareHouse scmInvWareHouse = scmInvWareHouseBiz.selectByWhNo(scmInvRealtimeStockQuery.getOrgUnitNo(), scmInvRealtimeStockQuery.getWareHouseNo(), param);
            if(scmInvWareHouse!=null)
            	wareHouseId = scmInvWareHouse.getId();
        }
        if(wareHouseId>0){
        	page.getParam().put(ScmInvStock2.FN_WAREHOUSEID,new QueryParam(ScmInvStock2.FN_WAREHOUSEID,QueryParam.QUERY_EQ, String.valueOf(wareHouseId)));
        }
        // 2 设置查询条件
        List<OrgStorage2> orgStorageList = orgStorageBiz.findChild(invOrgUnitNo, param);
        StringBuffer sbInv = new StringBuffer("");
        if (orgStorageList == null || orgStorageList.isEmpty()) {
        	page.getParam().put(ScmInvStock2.FN_ORGUNITNO,new QueryParam(ScmInvStock2.FN_ORGUNITNO,QueryParam.QUERY_EQ, invOrgUnitNo));
        }else{
            for(OrgStorage2 orgStorage:orgStorageList) {
            	if(StringUtils.isNotBlank(sbInv.toString()))
            		sbInv.append(",");
                sbInv.append("'").append(orgStorage.getOrgUnitNo()).append("'");
            }
            page.getParam().put(ScmInvStock2.FN_ORGUNITNO,new QueryParam(ScmInvStock2.FN_ORGUNITNO,QueryParam.QUERY_IN, sbInv.toString()));
        }
        page.getParam().put(ScmInvStock2.FN_COSTCENTER,new QueryParam(ScmInvStock2.FN_COSTCENTER,QueryParam.QUERY_EQ, "0"));
        return this.findPage(page, param);
	}
	
	@Override
	protected void afterFindPage(List list, Page page, Param param) throws AppException {
		if(list!=null && !list.isEmpty()){
			for(ScmInvStock2 scmInvStock:(List<ScmInvStock2>)list){
				setConvertMap(scmInvStock,param);
			}
		}
	}
	
	@Override
	protected void afterQueryPage(List list, Page page, String xmlId, Param param) throws AppException {
		if(list!=null && !list.isEmpty()){
			for(ScmInvStock2 scmInvStock:(List<ScmInvStock2>)list){
				if (scmInvStock.getVendorId() > 0){
		            //供应商
		            Scmsupplier2 scmsupplier = scmsupplierBiz.selectDirect(scmInvStock.getVendorId(), param);
		            if (scmsupplier != null) {
		                scmInvStock.setVendorName(scmsupplier.getVendorName());
		            }
		            if (scmInvStock.getUnit() > 0){
		                //库存单位
		                ScmMeasureUnit scmMeasureUnit = scmMeasureUnitBiz.selectDirect(scmInvStock.getUnit(), param);
		                if (scmMeasureUnit != null) {
		                    scmInvStock.setUnitName(scmMeasureUnit.getUnitName());
		                }
		            }
		        }
				if (scmInvStock.getTaxRate() !=null) {
					PubSysBasicInfo pubSysBasicInfo = pubSysBasicInfoBiz.selectByTaxRate("TaxRate", scmInvStock.getTaxRate().toString(), null, param);
		            if (pubSysBasicInfo != null) {
		            	scmInvStock.setConvertMap(scmInvStock.FN_TAXRATE, pubSysBasicInfo);
		            }
				}
			}
		}
	}
	private void setConvertMap(ScmInvStock2 scmInvStock,Param param){
       if (scmInvStock != null) {
          if (StringUtils.isNotBlank(scmInvStock.getOrgUnitNo())){
              //库存组织
              OrgBaseUnit orgBaseUnit =  orgBaseUnitBiz.selectbyOrgNo(scmInvStock.getOrgUnitNo(), param);
              if (orgBaseUnit != null) {
                  scmInvStock.setOrgUnitName(orgBaseUnit.getOrgUnitName());
              }
          } 
          if (scmInvStock.getVendorId() > 0){
              //供应商
              Scmsupplier2 scmsupplier = scmsupplierBiz.selectDirect(scmInvStock.getVendorId(), param);
              if (scmsupplier != null) {
                  scmInvStock.setVendorName(scmsupplier.getVendorName());
              }
          }
          if (scmInvStock.getWareHouseId() > 0){
              //仓库
              ScmInvWareHouse scmInvWareHouse = scmInvWareHouseBiz.selectDirect(scmInvStock.getWareHouseId(), param);
              if (scmInvWareHouse != null) {
                  scmInvStock.setWhName(scmInvWareHouse.getWhName());
              }
          }
          if (scmInvStock.getUnit() > 0){
              //库存单位
              ScmMeasureUnit scmMeasureUnit = scmMeasureUnitBiz.selectDirect(scmInvStock.getUnit(), param);
              if (scmMeasureUnit != null) {
                  scmInvStock.setUnitName(scmMeasureUnit.getUnitName());
              }
          }
          if (scmInvStock.getPieUnit() > 0){
              //辅助单位
              ScmMeasureUnit scmMeasureUnit = scmMeasureUnitBiz.selectDirect(scmInvStock.getPieUnit(), param);
              if (scmMeasureUnit != null) {
                  scmInvStock.setPieUnitName(scmMeasureUnit.getUnitName());
              }
          }
          if(scmInvStock.getItemId()>0){
        	  ScmMaterial2 scmMaterial = scmMaterialBiz.selectDirect(scmInvStock.getItemId(), param);
        	  if(scmMaterial!=null){
        		  scmInvStock.setItemNo(scmMaterial.getItemNo());
        		  scmInvStock.setItemName(scmMaterial.getItemName());
        		  scmInvStock.setSpec(scmMaterial.getSpec());
        	  }
          }
       }
	}
	@Override
	public List<ScmInvStock2> selectWareHsForSale(String orgUnitNo, long itemId, Param param) throws AppException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("orgUnitNo", orgUnitNo);
		map.put("itemId", itemId);
		return ((ScmInvStockDAO)dao).selectWareHsForSale(map);
	}
	@Override
	public int updateByDepositInReturn(long wrId, Param param) throws AppException {
		if(wrId > 0){
            HashMap<String,Object> map = new HashMap<String,Object>();
            map.put("wrId",wrId);
            return ((ScmInvStockDAO)dao).updateByDepositInReturn(map);
		 }
		 return 0;
	}
	@Override
	public int updateByCancelDepositInReturn(long wrId, Param param) throws AppException {
		if(wrId > 0){
            HashMap<String,Object> map = new HashMap<String,Object>();
            map.put("wrId",wrId);
            return ((ScmInvStockDAO)dao).updateByCancelDepositInReturn(map);
		 }
		 return 0;
	}
	@Override
	public List<ScmInvStock2> findByUseOrgCounting(String orgUnitNo, String useOrgUnitNo, Param param) {
       	HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("orgUnitNo", orgUnitNo);
        map.put("useOrgUnitNo", useOrgUnitNo);
		return ((ScmInvStockDAO)dao).findByUseOrgCounting(map);
	}
	@Override
	public HashMap<String, List<BigDecimal>> selectFinConsume(ScmDeptConsumeQuery scmDeptConsumeQuery,
			Param param) throws AppException {
		HashMap<String, List<BigDecimal>> consumeMap = new HashMap<String, List<BigDecimal>>();
		//计算盘存耗用
		//获取财务组织下的成本中心
		Page costCenterPage = new Page();
		costCenterPage.setModelClass(OrgCostCenter2.class);
		costCenterPage.setShowCount(Integer.MAX_VALUE);
		List<String> costCenterArgList = new ArrayList<String>();
		costCenterArgList.add("type=from");
		costCenterArgList.add("relationType=" + OrgUnitRelationType.COSTTOFIN);
		costCenterArgList.add("toOrgUnitNo=" + scmDeptConsumeQuery.getFinOrgUnitNo());
		List<OrgCostCenter2> orgCostCenterList = orgCostCenterBiz.queryPage(costCenterPage, costCenterArgList, "queryPageEx", param);
		StringBuffer costOrgUnitNos = new StringBuffer("");
		if(orgCostCenterList!=null && !orgCostCenterList.isEmpty()) {
			for(OrgCostCenter2 orgCostCenter:orgCostCenterList) {
				if(StringUtils.isNotBlank(costOrgUnitNos.toString()))
					costOrgUnitNos.append(",");
				costOrgUnitNos.append("'").append(orgCostCenter.getOrgUnitNo()).append("'");
			}
		}
		if(StringUtils.isBlank(costOrgUnitNos.toString())) {
			costOrgUnitNos=costOrgUnitNos.append("'0'");
		}
		Page costPage = new Page();
		costPage.setModelClass(ScmInvCountingCostCenterEntry2.class);
		costPage.setShowCount(Integer.MAX_VALUE);
		List<String> costArglist = new ArrayList<>();
		costArglist.add("costOrgUnitNos="+costOrgUnitNos.toString());
		costArglist.add("summaryLevel="+scmDeptConsumeQuery.getSummaryLevel());
		costArglist.add("begDate="+FormatUtils.fmtDateTime(scmDeptConsumeQuery.getBegDate()));
		costArglist.add("endDate="+FormatUtils.fmtDateTime(scmDeptConsumeQuery.getEndDate()));
		List<ScmInvCountingCostCenterEntry2> scmInvCountingCostCenterEntryList = scmInvCountingCostCenterEntryBiz.queryPage(costPage, costArglist, "selectFinConsume", param);
		if(scmInvCountingCostCenterEntryList != null && !scmInvCountingCostCenterEntryList.isEmpty()){
			for(ScmInvCountingCostCenterEntry2 scmInvCountingCostCenterEntry : scmInvCountingCostCenterEntryList){
				String classId = (scmInvCountingCostCenterEntry.getLongNo()).split(",")[(scmDeptConsumeQuery.getSummaryLevel()-1)];
				String consumeKey = scmInvCountingCostCenterEntry.getUseOrgUnitNo()+"_"+classId;
				if(consumeMap.containsKey(consumeKey)){
					List<BigDecimal> consumeList = (List<BigDecimal>)consumeMap.get(consumeKey);
					BigDecimal consumeAmt = consumeList.get(0);
					BigDecimal consumeTaxAmt = consumeList.get(1);
					BigDecimal amt = consumeList.get(2);
					BigDecimal taxAmt = consumeList.get(3);
					consumeAmt = consumeAmt.add(scmInvCountingCostCenterEntry.getDifferAmt().negate());
					consumeTaxAmt = consumeTaxAmt.add(scmInvCountingCostCenterEntry.getDifferTaxAmt().negate());
					amt = amt.add(scmInvCountingCostCenterEntry.getDifferAmt().negate());
					taxAmt = taxAmt.add(scmInvCountingCostCenterEntry.getDifferTaxAmt().negate());
					consumeList.clear();
					consumeList.add(consumeAmt);
					consumeList.add(consumeTaxAmt);
					consumeList.add(amt);
					consumeList.add(taxAmt);
				}else{
					List<BigDecimal> consumeList = new ArrayList<>();
					consumeList.add(scmInvCountingCostCenterEntry.getDifferAmt().negate());
					consumeList.add(scmInvCountingCostCenterEntry.getDifferTaxAmt().negate());
					consumeList.add(scmInvCountingCostCenterEntry.getDifferAmt().negate());
					consumeList.add(scmInvCountingCostCenterEntry.getDifferTaxAmt().negate());
					consumeMap.put(consumeKey, consumeList);
				}
			}
		}
		//计算成本耗用
		Page page2 = new Page();
		page2.setModelClass(ScmInvCostConsumeEntry2.class);
		page2.setShowCount(Integer.MAX_VALUE);
		List<String> arglist = new ArrayList<>();
		arglist.add("finOrgUnitNo="+scmDeptConsumeQuery.getFinOrgUnitNo());
		arglist.add("summaryLevel="+scmDeptConsumeQuery.getSummaryLevel());
		arglist.add("begDate="+FormatUtils.fmtDateTime(scmDeptConsumeQuery.getBegDate()));
		arglist.add("endDate="+FormatUtils.fmtDateTime(scmDeptConsumeQuery.getEndDate()));
		List<ScmInvCostConsumeEntry2> scmInvCostConsumeEntryList = scmInvCostConsumeEntryBiz.queryPage(page2, arglist, "selectFinConsume", param);
		if(scmInvCostConsumeEntryList != null && !scmInvCostConsumeEntryList.isEmpty()){
			for(ScmInvCostConsumeEntry2 scmInvCostConsumeEntry : scmInvCostConsumeEntryList){
				String classId = (scmInvCostConsumeEntry.getLongNo()).split(",")[(scmDeptConsumeQuery.getSummaryLevel()-1)];
				String consumeKey = scmInvCostConsumeEntry.getUseOrgUnitNo()+"_"+classId;
				if(consumeMap.containsKey(consumeKey)){
					List<BigDecimal> consumeList = (List<BigDecimal>)consumeMap.get(consumeKey);
					BigDecimal consumeAmt = consumeList.get(0);
					BigDecimal consumeTaxAmt = consumeList.get(1);
					BigDecimal amt = consumeList.get(2);
					BigDecimal taxAmt = consumeList.get(3);
					consumeAmt = consumeAmt.add(scmInvCostConsumeEntry.getAmt());
					consumeTaxAmt = consumeTaxAmt.add(scmInvCostConsumeEntry.getTaxAmt());
					amt = amt.add(scmInvCostConsumeEntry.getAmt());
					taxAmt = taxAmt.add(scmInvCostConsumeEntry.getTaxAmt());
					consumeList.clear();
					consumeList.add(consumeAmt);
					consumeList.add(consumeTaxAmt);
					consumeList.add(amt);
					consumeList.add(taxAmt);
				}else{
					List<BigDecimal> consumeList = new ArrayList<>();
					consumeList.add(scmInvCostConsumeEntry.getAmt());
					consumeList.add(scmInvCostConsumeEntry.getTaxAmt());
					consumeList.add(scmInvCostConsumeEntry.getAmt());
					consumeList.add(scmInvCostConsumeEntry.getTaxAmt());
					consumeMap.put(consumeKey, consumeList);
				}
			}
		}
		//计算入账
		Page page3 = new Page();
		page3.setModelClass(ScmInvPurInWarehsBillEntry2.class);
		page3.setShowCount(Integer.MAX_VALUE);
		List<ScmInvPurInWarehsBillEntry2> scmInvPurInWarehsBillEntryList = scmInvPurInWarehsBillEntryBiz.queryPage(page3, arglist, "selectFinConsume", param);
		if(scmInvPurInWarehsBillEntryList != null && !scmInvPurInWarehsBillEntryList.isEmpty()){
			for(ScmInvPurInWarehsBillEntry2 scmInvPurInWarehsBillEntry : scmInvPurInWarehsBillEntryList){
				String classId = (scmInvPurInWarehsBillEntry.getLongNo()).split(",")[(scmDeptConsumeQuery.getSummaryLevel()-1)];
				String consumeKey = scmInvPurInWarehsBillEntry.getUseOrgUnitNo()+"_"+classId;
				if(consumeMap.containsKey(consumeKey)){
					List<BigDecimal> consumeList = (List<BigDecimal>)consumeMap.get(consumeKey);
					BigDecimal consumeAmt = consumeList.get(0);
					BigDecimal consumeTaxAmt = consumeList.get(1);
					BigDecimal amt = consumeList.get(2);
					BigDecimal taxAmt = consumeList.get(3);
					amt = amt.subtract(scmInvPurInWarehsBillEntry.getAmt());
					taxAmt = taxAmt.subtract(scmInvPurInWarehsBillEntry.getTaxAmt());
					consumeList.clear();
					consumeList.add(consumeAmt);
					consumeList.add(consumeTaxAmt);
					consumeList.add(amt);
					consumeList.add(taxAmt);
				}else{
					List<BigDecimal> consumeList = new ArrayList<>();
					consumeList.add(BigDecimal.ZERO);
					consumeList.add(BigDecimal.ZERO);
					consumeList.add(scmInvPurInWarehsBillEntry.getAmt().negate());
					consumeList.add(scmInvPurInWarehsBillEntry.getTaxAmt().negate());
					consumeMap.put(consumeKey, consumeList);
				}
			}
		}
		if(consumeMap != null && !consumeMap.isEmpty()){
			HashMap<String, List<BigDecimal>> consumeAmtMap = new HashMap<String, List<BigDecimal>>();
			for(String key : consumeMap.keySet()){
				long classId = Long.parseLong(key.split("_")[1]);
				ScmMaterialGroup scmMaterialGroup = scmMaterialGroupBiz.selectDirect(classId, param);
				if(scmMaterialGroup != null){
					List<BigDecimal> consumeList = (List<BigDecimal>)consumeMap.get(key);
					BigDecimal consumeAmt = consumeList.get(0);
					BigDecimal consumeTaxAmt = consumeList.get(1);
					BigDecimal amt = consumeList.get(2);
					BigDecimal taxAmt = consumeList.get(3);
					consumeList.clear();
					consumeList.add(consumeAmt);
					consumeList.add((consumeTaxAmt.subtract(consumeAmt)));
					consumeList.add(amt);
					consumeList.add((taxAmt.subtract(amt)));
					consumeList.add(consumeTaxAmt);
					consumeList.add(taxAmt);
					consumeAmtMap.put(key.split("_")[0]+'_'+scmMaterialGroup.getClassCode(), consumeList);
				}
			}
			return consumeAmtMap;
		}
		return null;
	}
	@Override
	public int updateByCstFrmLoss(long billId, Param param) throws AppException {
		if(billId > 0){
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("billId",billId);
			return ((ScmInvStockDAO)dao).updateByCstFrmLoss(map);
		}
		return 0;
	}
	@Override
	public int updateByCancelCstFrmLoss(long billId, Param param)
			throws AppException {
		if(billId > 0){
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("billId", billId);
			return ((ScmInvStockDAO)dao).updateByCancelCstFrmLoss(map);
		}
		return 0;
	}
	@Override
	public List<ScmFinDeptConsume> selectFinDeptConsume(ScmDeptConsumeQuery scmDeptConsumeQuery, Param param) {
		//获取财务组织下的成本中心
		/*Page costCenterPage = new Page();
		costCenterPage.setModelClass(OrgCostCenter2.class);
		costCenterPage.setShowCount(Integer.MAX_VALUE);
		List<String> costCenterArgList = new ArrayList<String>();
		costCenterArgList.add("type=from");
		costCenterArgList.add("relationType=" + OrgUnitRelationType.COSTTOFIN);
		costCenterArgList.add("toOrgUnitNo=" + scmDeptConsumeQuery.getFinOrgUnitNo());
		List<OrgCostCenter2> orgCostCenterList = orgCostCenterBiz.queryPage(costCenterPage, costCenterArgList, "queryPageEx", param);*/
		StringBuffer costOrgUnitNos = new StringBuffer("");
		if(StringUtils.isNotBlank(scmDeptConsumeQuery.getFinOrgUnitNo())) {
			List<OrgCostCenter2> orgCostCenterChildList = orgCostCenterBiz.findChild(scmDeptConsumeQuery.getFinOrgUnitNo(), param);
    		if(orgCostCenterChildList!=null && !orgCostCenterChildList.isEmpty()) {
    			for(OrgCostCenter2 childOrgCostCenter:orgCostCenterChildList) {
    				if(StringUtils.isNotBlank(costOrgUnitNos.toString()))
    					costOrgUnitNos.append(",");
    				costOrgUnitNos.append("'").append(childOrgCostCenter.getOrgUnitNo()).append("'");
    			}
    		}else{
    			if(StringUtils.isNotBlank(costOrgUnitNos.toString()))
					costOrgUnitNos.append(",");
				costOrgUnitNos.append("'").append(scmDeptConsumeQuery.getFinOrgUnitNo()).append("'");
    		}
		}
		if(StringUtils.isBlank(costOrgUnitNos.toString())) {
			costOrgUnitNos=costOrgUnitNos.append("'0'");
		}
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("costOrgUnitNos",costOrgUnitNos.toString());
		map.put("summaryLevel",scmDeptConsumeQuery.getSummaryLevel());
		map.put("begDate",FormatUtils.fmtDateTime(scmDeptConsumeQuery.getBegDate()));
		map.put("endDate",FormatUtils.fmtDateTime(scmDeptConsumeQuery.getEndDate()));
		List<ScmFinDeptConsume> scmFinDeptConsumeList = new ArrayList<>();
		if("1".equals(scmDeptConsumeQuery.getType())){
			scmFinDeptConsumeList = ((ScmInvStockDAO)dao).selectFinDeptConsume(map);
		}else if("2".equals(scmDeptConsumeQuery.getType())){
			scmFinDeptConsumeList = ((ScmInvStockDAO)dao).selectReqFinDeptConsume(map);
		}
		if(scmFinDeptConsumeList != null && !scmFinDeptConsumeList.isEmpty()){
			for (int i = scmFinDeptConsumeList.size() - 1; i >= 0; i--) {
				ScmFinDeptConsume scmFinDeptConsume = scmFinDeptConsumeList.get(i);
				BigDecimal amt = BigDecimal.ZERO;
				if(scmFinDeptConsume.getStartAmt() != null){
					amt = amt.add(scmFinDeptConsume.getStartAmt().abs());
				}
				if(scmFinDeptConsume.getEndAmt()!= null){
					amt = amt.add(scmFinDeptConsume.getEndAmt().abs());
				}
				if(scmFinDeptConsume.getReqAmt()!= null){
					amt = amt.add(scmFinDeptConsume.getReqAmt().abs());
				}
				if(scmFinDeptConsume.getAmt()!= null){
					amt = amt.add(scmFinDeptConsume.getAmt().abs());
				}
				if(amt.compareTo(BigDecimal.ZERO) == 0){
					scmFinDeptConsumeList.remove(scmFinDeptConsume);
				}
			}
			return scmFinDeptConsumeList;
		}
		return null;
	}
	
	@Override
	public List<ScmInvStock2> selectByOrgUnitNos(String finOrgUnitNo, String costOrgUnitNos, Param param) throws AppException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("finOrgUnitNo", finOrgUnitNo);
		map.put("costOrgUnitNos", costOrgUnitNos);
		return ((ScmInvStockDAO)dao).selectByOrgUnitNos(map);
	}
	@Override
	public ScmInvStock2 selectIdleItemStock(String orgUnitNo, String useOrgUnitNo, long itemId, Param param)
			throws AppException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("costOrgUnitNo", orgUnitNo);
		map.put("orgUnitNo", useOrgUnitNo);
		map.put("itemId", itemId);
		return ((ScmInvStockDAO)dao).selectIdleItemStock(map);
	}
	@Override
	public BigDecimal getStockQty(String orgUnitNo, String itemNo, Param param) throws AppException {
		HashMap<String, Object> map = new HashMap<>();
		map.put("orgUnitNo", orgUnitNo);
		map.put("itemNo", itemNo);
		ScmInvStock2 scmInvStock = ((ScmInvStockDAO)dao).getStockQty(map);
		if(scmInvStock==null) {
			return BigDecimal.ZERO;
		}else {
			return scmInvStock.getQty()==null?BigDecimal.ZERO:scmInvStock.getQty();
		}
	}
	@Override
	public ScmInvStock2 selectCostPrice(long itemId, String string,Param param) throws AppException {
		HashMap<String, Object> map = new HashMap<>();
		map.put("finOrgs", string);
		map.put("itemId", itemId);
		return ((ScmInvStockDAO)dao).selectCostPrice(map);
	}
	@Override
	public BigDecimal getStockQtyByReqOrg(String reqOrgUnitNo, String itemNo, Param param) throws AppException {
		HashMap<String, Object> map = new HashMap<>();
		map.put("reqOrgUnitNo", reqOrgUnitNo);
		map.put("itemNo", itemNo);
		ScmInvStock2 scmInvStock = ((ScmInvStockDAO)dao).getStockQtyByReqOrg(map);
		if(scmInvStock==null) {
			return BigDecimal.ZERO;
		}else {
			return scmInvStock.getQty()==null?BigDecimal.ZERO:scmInvStock.getQty();
		}
	}
	@Override
	public List<ScmInvStock2> getStockQtyList(String invOrgUnitNo, String itemIds, long orgOrWhStockQty, Param param)
			throws AppException {
		HashMap<String, Object> map = new HashMap<>();
		map.put("reqOrgUnitNo", invOrgUnitNo);
		map.put("itemIds", itemIds);
		map.put("orgOrWhStockQty", orgOrWhStockQty);
		return ((ScmInvStockDAO)dao).getStockQtyList(map);
	}
	@Override
	public List<ScmInvStock2> selectOrgForSale(String orgUnitNo, long itemId, Param param) throws AppException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("orgUnitNos", orgUnitNo);
		map.put("itemId", itemId);
		return ((ScmInvStockDAO)dao).selectOrgForSale(map);
	}
	@Override
	public List<ScmInvStock2> selectCostPriceByItems(String string, String finOrg, Param param) throws AppException {
		HashMap<String, Object> map = new HashMap<>();
		map.put("finOrgs", finOrg);
		map.put("itemIds", string);
		return ((ScmInvStockDAO)dao).selectCostPriceByItems(map);
	}
}

