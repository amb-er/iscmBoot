package com.armitage.server.iscm.report.inventory.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Page;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.base.model.QueryParam;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.util.ClassUtils;
import com.armitage.server.common.util.FormatUtils;
import com.armitage.server.common.util.ListSortUtil;
import com.armitage.server.iars.basedata.model.Customer;
import com.armitage.server.iars.basedata.model.Customer2;
import com.armitage.server.iars.basedata.model.PeriodCalendar;
import com.armitage.server.iars.basedata.service.CustomerBiz;
import com.armitage.server.iars.basedata.service.PeriodCalendarBiz;
import com.armitage.server.icrm.profile.model.Salesman2;
import com.armitage.server.icrm.profile.service.SalesmanBiz;
import com.armitage.server.ifbc.basedata.model.ScmAccountingCycleType2;
import com.armitage.server.iscm.basedata.model.ScmMaterial2;
import com.armitage.server.iscm.basedata.model.ScmMaterialGroup;
import com.armitage.server.iscm.basedata.model.ScmMeasureUnit;
import com.armitage.server.iscm.basedata.model.Scmsupplier2;
import com.armitage.server.iscm.basedata.model.Scmsuppliergroup2;
import com.armitage.server.iscm.basedata.service.ScmMaterialBiz;
import com.armitage.server.iscm.basedata.service.ScmMaterialGroupBiz;
import com.armitage.server.iscm.basedata.service.ScmMeasureUnitBiz;
import com.armitage.server.iscm.basedata.service.ScmsupplierBiz;
import com.armitage.server.iscm.basedata.service.ScmsuppliergroupBiz;
import com.armitage.server.iscm.basedata.util.ScmMaterialUtil;
import com.armitage.server.iscm.inventorymanage.inventorydata.model.ScmInvBal;
import com.armitage.server.iscm.inventorymanage.inventorydata.model.ScmInvBal2;
import com.armitage.server.iscm.inventorymanage.inventorydata.service.ScmInvBalBiz;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvSupplyRule;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvWareHouse;
import com.armitage.server.iscm.inventorymanage.inventorysetting.service.ScmInvWareHouseBiz;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvMoveInWarehsBill;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvMoveInWarehsBillEntry2;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvOtherInWarehsBill;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvOtherInWarehsBillEntry2;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvPurInWarehsBill2;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvPurInWarehsBillEntry2;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.service.ScmInvMoveInWarehsBillEntryBiz;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.service.ScmInvOtherInWarehsBillEntryBiz;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.service.ScmInvPurInWarehsBillEntryBiz;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvMaterialReqBill;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvMaterialReqBill2;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvMaterialReqBillEntry2;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvMoveIssueBill;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvMoveIssueBillEntry2;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvOtherIssueBill;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvOtherIssueBillEntry2;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvSaleIssueBill;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvSaleIssueBillEntry2;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.service.ScmInvMaterialReqBillEntryBiz;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.service.ScmInvMoveIssueBillEntryBiz;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.service.ScmInvOtherIssueBillEntryBiz;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.service.ScmInvSaleIssueBillEntryBiz;
import com.armitage.server.iscm.purchasemanage.pricemanage.service.ScmMaterialPriceBiz;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurBuyer2;
import com.armitage.server.iscm.purchasemanage.purchasesetting.service.ScmPurBuyerBiz;
import com.armitage.server.iscm.report.costcenter.model.ScmCostItemInAndOutSum;
import com.armitage.server.iscm.report.inventory.dao.ScmInvReportDAO;
import com.armitage.server.iscm.report.inventory.model.RealtimeStock;
import com.armitage.server.iscm.report.inventory.model.ScmInvDepositorySumSup;
import com.armitage.server.iscm.report.inventory.model.ScmInvGlobalStock;
import com.armitage.server.iscm.report.inventory.model.ScmInvInWareMonthAnalysis;
import com.armitage.server.iscm.report.inventory.model.ScmInvInWarehsItemClass;
import com.armitage.server.iscm.report.inventory.model.ScmInvInWarehsItemClass2;
import com.armitage.server.iscm.report.inventory.model.ScmInvInWarehsItemSum;
import com.armitage.server.iscm.report.inventory.model.ScmInvItemInAndOutDetail;
import com.armitage.server.iscm.report.inventory.model.ScmInvItemInAndOutSum;
import com.armitage.server.iscm.report.inventory.model.ScmInvItemSaleSum;
import com.armitage.server.iscm.report.inventory.model.ScmInvItemWrDetails;
import com.armitage.server.iscm.report.inventory.model.ScmInvItemWrSum;
import com.armitage.server.iscm.report.inventory.model.ScmInvPurSalePrice;
import com.armitage.server.iscm.report.inventory.model.ScmInvRealtimeStock;
import com.armitage.server.iscm.report.inventory.model.ScmInvRealtimeStockSum;
import com.armitage.server.iscm.report.inventory.model.ScmInvReport;
import com.armitage.server.iscm.report.inventory.model.ScmInvSaleBusiness;
import com.armitage.server.iscm.report.inventory.model.ScmInvStorageAgeAnalysis;
import com.armitage.server.iscm.report.inventory.model.ScmInvStorageAgePrimnessAnalysis;
import com.armitage.server.iscm.report.inventory.model.ScmPurVendorInfo;
import com.armitage.server.iscm.report.inventory.model.ScmVendorItemContrast;
import com.armitage.server.iscm.report.inventory.service.ScmInvReportBiz;
import com.armitage.server.system.model.Code;
import com.armitage.server.system.model.OrgBaseUnit;
import com.armitage.server.system.model.OrgBaseUnit2;
import com.armitage.server.system.model.OrgCompany2;
import com.armitage.server.system.model.OrgCostCenter2;
import com.armitage.server.system.model.OrgPurchase2;
import com.armitage.server.system.model.OrgStorage2;
import com.armitage.server.system.service.CodeBiz;
import com.armitage.server.system.service.OrgBaseUnitBiz;
import com.armitage.server.system.service.OrgCompanyBiz;
import com.armitage.server.system.service.OrgCostCenterBiz;
import com.armitage.server.system.service.OrgPurchaseBiz;
import com.armitage.server.system.service.OrgStorageBiz;
import com.armitage.server.system.service.OrgUnitBiz;
import com.armitage.server.system.service.OrgUnitRelationBiz;
import com.armitage.server.system.util.OrgUnitRelationType;
import org.springframework.stereotype.Service;

@Service("scmInvReportBiz")
public class ScmInvReportBizImpl extends BaseBizImpl<ScmInvReport> implements ScmInvReportBiz {
	private static Log log = LogFactory.getLog(ScmInvReportBizImpl.class);
	private OrgStorageBiz orgStorageBiz;
	private ScmMaterialBiz scmMaterialBiz;
	private OrgBaseUnitBiz orgBaseUnitBiz;
	private ScmsupplierBiz scmsupplierBiz;
	private ScmsuppliergroupBiz scmsuppliergroupBiz;
	private ScmInvWareHouseBiz scmInvWareHouseBiz;
	private ScmMeasureUnitBiz scmMeasureUnitBiz;
	private OrgCompanyBiz orgCompanyBiz;
	private ScmMaterialGroupBiz scmMaterialGroupBiz;
	private PeriodCalendarBiz periodCalendarBiz;
	private ScmInvBalBiz scmInvBalBiz;
	private ScmInvPurInWarehsBillEntryBiz scmInvPurInWarehsBillEntryBiz;
	private OrgUnitBiz orgUnitBiz;
	private CodeBiz codeBiz;
	private ScmInvOtherInWarehsBillEntryBiz scmInvOtherInWarehsBillEntryBiz;
	private ScmInvMaterialReqBillEntryBiz scmInvMaterialReqBillEntryBiz;
	private ScmInvSaleIssueBillEntryBiz scmInvSaleIssueBillEntryBiz;
	private ScmInvMoveInWarehsBillEntryBiz scmInvMoveInWarehsBillEntryBiz;
	private ScmInvMoveIssueBillEntryBiz scmInvMoveIssueBillEntryBiz;
	private ScmInvOtherIssueBillEntryBiz scmInvOtherIssueBillEntryBiz;
	private OrgCostCenterBiz orgCostCenterBiz;
	private CustomerBiz customerBiz;
	private ScmPurBuyerBiz scmPurBuyerBiz;
	private SalesmanBiz salesmanBiz;
    private OrgPurchaseBiz orgPurchaseBiz;
    
	public void setOrgPurchaseBiz(OrgPurchaseBiz orgPurchaseBiz) {
		this.orgPurchaseBiz = orgPurchaseBiz;
	}

	public void setScmsuppliergroupBiz(ScmsuppliergroupBiz scmsuppliergroupBiz) {
		this.scmsuppliergroupBiz = scmsuppliergroupBiz;
	}

	public SalesmanBiz getSalesmanBiz() {
		return salesmanBiz;
	}

	public void setSalesmanBiz(SalesmanBiz salesmanBiz) {
		this.salesmanBiz = salesmanBiz;
	}

	private OrgUnitRelationBiz orgUnitRelationBiz;
	private ScmMaterialPriceBiz scmMaterialPriceBiz;

	public ScmMaterialPriceBiz getScmMaterialPriceBiz() {
		return scmMaterialPriceBiz;
	}

	public void setScmMaterialPriceBiz(ScmMaterialPriceBiz scmMaterialPriceBiz) {
		this.scmMaterialPriceBiz = scmMaterialPriceBiz;
	}

	public OrgUnitRelationBiz getOrgUnitRelationBiz() {
		return orgUnitRelationBiz;
	}

	public void setOrgUnitRelationBiz(OrgUnitRelationBiz orgUnitRelationBiz) {
		this.orgUnitRelationBiz = orgUnitRelationBiz;
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

	public void setOrgCompanyBiz(OrgCompanyBiz orgCompanyBiz) {
		this.orgCompanyBiz = orgCompanyBiz;
	}

	public void setScmMaterialGroupBiz(ScmMaterialGroupBiz scmMaterialGroupBiz) {
		this.scmMaterialGroupBiz = scmMaterialGroupBiz;
	}

	public void setPeriodCalendarBiz(PeriodCalendarBiz periodCalendarBiz) {
		this.periodCalendarBiz = periodCalendarBiz;
	}

	public void setScmInvBalBiz(ScmInvBalBiz scmInvBalBiz) {
		this.scmInvBalBiz = scmInvBalBiz;
	}

	public void setScmInvPurInWarehsBillEntryBiz(ScmInvPurInWarehsBillEntryBiz scmInvPurInWarehsBillEntryBiz) {
		this.scmInvPurInWarehsBillEntryBiz = scmInvPurInWarehsBillEntryBiz;
	}

	public void setOrgUnitBiz(OrgUnitBiz orgUnitBiz) {
		this.orgUnitBiz = orgUnitBiz;
	}

	public void setCodeBiz(CodeBiz codeBiz) {
		this.codeBiz = codeBiz;
	}

	public void setScmInvOtherInWarehsBillEntryBiz(ScmInvOtherInWarehsBillEntryBiz scmInvOtherInWarehsBillEntryBiz) {
		this.scmInvOtherInWarehsBillEntryBiz = scmInvOtherInWarehsBillEntryBiz;
	}

	public void setScmInvMaterialReqBillEntryBiz(ScmInvMaterialReqBillEntryBiz scmInvMaterialReqBillEntryBiz) {
		this.scmInvMaterialReqBillEntryBiz = scmInvMaterialReqBillEntryBiz;
	}

	public void setScmInvSaleIssueBillEntryBiz(ScmInvSaleIssueBillEntryBiz scmInvSaleIssueBillEntryBiz) {
		this.scmInvSaleIssueBillEntryBiz = scmInvSaleIssueBillEntryBiz;
	}

	public void setScmInvMoveInWarehsBillEntryBiz(ScmInvMoveInWarehsBillEntryBiz scmInvMoveInWarehsBillEntryBiz) {
		this.scmInvMoveInWarehsBillEntryBiz = scmInvMoveInWarehsBillEntryBiz;
	}

	public void setScmInvMoveIssueBillEntryBiz(ScmInvMoveIssueBillEntryBiz scmInvMoveIssueBillEntryBiz) {
		this.scmInvMoveIssueBillEntryBiz = scmInvMoveIssueBillEntryBiz;
	}

	public void setScmInvOtherIssueBillEntryBiz(ScmInvOtherIssueBillEntryBiz scmInvOtherIssueBillEntryBiz) {
		this.scmInvOtherIssueBillEntryBiz = scmInvOtherIssueBillEntryBiz;
	}

	public void setOrgCostCenterBiz(OrgCostCenterBiz orgCostCenterBiz) {
		this.orgCostCenterBiz = orgCostCenterBiz;
	}

	public void setCustomerBiz(CustomerBiz customerBiz) {
		this.customerBiz = customerBiz;
	}

	public void setScmPurBuyerBiz(ScmPurBuyerBiz scmPurBuyerBiz) {
		this.scmPurBuyerBiz = scmPurBuyerBiz;
	}
	

	@Override
	public List<RealtimeStock> selectRealtimeStock(HttpServletRequest httpServletRequest) throws AppException {
		// 1 获取查询参数
		HashMap<String, Object> map = new HashMap<>();
		String currentOrgUnitNo = httpServletRequest.getParameter("orgUnitNo");
		String currentControlUnitNo = httpServletRequest.getParameter("controlUnitNo");
		String invOrgUnitNo = httpServletRequest.getParameter("invOrgUnitNo");
		String vendorName = httpServletRequest.getParameter("vendorName");
		int vendorId = 0;
		if (StringUtils.isNotBlank(vendorName) && StringUtils.isNumeric(vendorName)) {
			vendorId = (Integer.parseInt(vendorName));
		}
		String materialName = httpServletRequest.getParameter("materialName");
		int materialId = 0;
		if (StringUtils.isNotBlank(materialName) && StringUtils.isNumeric(materialName)) {
			materialId = (Integer.parseInt(materialName));
		}
		String materialClassName = httpServletRequest.getParameter("materialClassName");
		int materialClassId = 0;
		if (StringUtils.isBlank(invOrgUnitNo))
			invOrgUnitNo = currentOrgUnitNo;
		Param param = new Param();
		param.setOrgUnitNo(currentOrgUnitNo);
		param.setControlUnitNo(currentControlUnitNo);
		if (StringUtils.isNotBlank(materialClassName) && StringUtils.isNumeric(materialClassName)) {
			materialClassId = (Integer.parseInt(materialClassName));
			List<ScmMaterialGroup> scmMaterialGroupList = scmMaterialGroupBiz.findChild(materialClassId, param);
			StringBuffer sbMaterilaClass = new StringBuffer("");
			if (scmMaterialGroupList != null && scmMaterialGroupList.size() > 0) {
				sbMaterilaClass.append("(");
				for (int i = 0; i < scmMaterialGroupList.size(); i++) {
					sbMaterilaClass.append(scmMaterialGroupList.get(i).getId());
					if (i == scmMaterialGroupList.size() - 1) {
						break;
					}
					sbMaterilaClass.append(",");
				}
				sbMaterilaClass.append(")");
				map.put("materialClassIds", sbMaterilaClass.toString());
			}
		}
		String whName = httpServletRequest.getParameter("whName");
		int wareHouseId = 0;
		if (StringUtils.isNotBlank(whName) && StringUtils.isNumeric(whName)) {
			wareHouseId = (Integer.parseInt(whName));
		}
		// 2 设置查询条件
		List<OrgStorage2> orgStorageList = orgStorageBiz.findChild(invOrgUnitNo, param);
		StringBuffer sbInv = new StringBuffer("");
		if (orgStorageList == null || orgStorageList.isEmpty()) {
			map.put("reqOrgUnitNo", null);
		}
		if (orgStorageList != null && orgStorageList.size() > 0) {
			sbInv.append("(");
			for (int i = 0; i < orgStorageList.size(); i++) {
				sbInv.append(orgStorageList.get(i).getOrgUnitNo());
				if (i == orgStorageList.size() - 1) {
					break;
				}
				sbInv.append(",");
			}
			sbInv.append(")");
			map.put("invOrgUnitNo", sbInv.toString());
		}
		map.put("materialId", materialId);
		map.put("vendorId", vendorId);
		map.put("wareHouseId", wareHouseId);
		if (materialId > 0) {
			ScmMaterial2 scmMaterial = scmMaterialBiz.selectDirect(materialId, param);
			if (scmMaterial == null) {
				throw new AppException("iscm.purchasemanage.purchasebusiness.ScmPurRequireBizImpl.error.itemnorexists");
			}
			BigDecimal invConvRate = ScmMaterialUtil.getConvRate(materialId, scmMaterial.getUnitId(), param);// 库存单位转换系数
			map.put("convrate", invConvRate);
		}
		// 3 查询数据库
		List<ScmInvRealtimeStock> list = ((ScmInvReportDAO) dao).selectRealtimeStock(map);

		// 4 数据处理及返回
		List<RealtimeStock> rtnList = new ArrayList();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				ScmInvRealtimeStock scmInvStock = list.get(i);
				if (scmInvStock.getOrgUnitNo() != null) {
					// 库存组织
					OrgBaseUnit orgBaseUnit = orgBaseUnitBiz.selectbyOrgNo(scmInvStock.getOrgUnitNo(), param);
					if (orgBaseUnit != null) {
						scmInvStock.setOrgUnitName(orgBaseUnit.getOrgUnitName());
					}
				}
				if (scmInvStock.getVendorId() > 0) {
					// 供应商
					Scmsupplier2 scmsupplier = scmsupplierBiz.selectDirect(scmInvStock.getVendorId(), param);
					if (scmsupplier != null) {
						scmInvStock.setVendorName(scmsupplier.getVendorName());
					}
				}
				if (scmInvStock.getWareHouseId() > 0) {
					// 仓库
					ScmInvWareHouse scmInvWareHouse = scmInvWareHouseBiz.selectDirect(scmInvStock.getWareHouseId(),
							param);
					if (scmInvWareHouse != null) {
						scmInvStock.setWhName(scmInvWareHouse.getWhName());
					}
				} else if (scmInvStock.getWareHouseId() == -1) {
					scmInvStock.setWhName("调拨在途");
				}
				if (scmInvStock.getUnit() > 0) {
					// 库存单位
					ScmMeasureUnit scmMeasureUnit = scmMeasureUnitBiz.selectDirect(scmInvStock.getUnit(), param);
					if (scmMeasureUnit != null) {
						scmInvStock.setUnitName(scmMeasureUnit.getUnitName());
					}
				}
				if (scmInvStock.getPieUnit() > 0) {
					// 辅助单位
					ScmMeasureUnit scmMeasureUnit = scmMeasureUnitBiz.selectDirect(scmInvStock.getPieUnit(), param);
					if (scmMeasureUnit != null) {
						scmInvStock.setPieUnitName(scmMeasureUnit.getUnitName());
					}
				}
				RealtimeStock realtimeStock = new RealtimeStock();
				BeanUtils.copyProperties(scmInvStock, realtimeStock);
				rtnList.add(realtimeStock);
			}
		}
		return rtnList;
	}

	@Override
	public List<ScmInvGlobalStock> selectGlobalInventory(HttpServletRequest httpServletRequest) throws AppException {
		// 1 获取查询参数
		HashMap<String, Object> map = new HashMap<>();
		String currentOrgUnitNo = httpServletRequest.getParameter("orgUnitNo");
		String currentControlUnitNo = httpServletRequest.getParameter("controlUnitNo");
		String invOrgUnitNoParam = httpServletRequest.getParameter("invOrgUnitNo");
		String flag = httpServletRequest.getParameter("flag");
		if (StringUtils.isBlank(flag)) {
			flag = "0";
		}
		String materialName = httpServletRequest.getParameter("materialName");
		int materialId = 0;
		if (StringUtils.isNotBlank(materialName) && StringUtils.isNumeric(materialName)) {
			materialId = (Integer.parseInt(materialName));
		}
		String materialClassName = httpServletRequest.getParameter("materialClassName");
		int materialClassId = 0;
		if (StringUtils.isBlank(invOrgUnitNoParam))
			invOrgUnitNoParam = currentOrgUnitNo;
		Param param = new Param();
		param.setOrgUnitNo(currentOrgUnitNo);
		param.setControlUnitNo(currentControlUnitNo);
		if (StringUtils.isNotBlank(materialClassName) && StringUtils.isNumeric(materialClassName)) {
			materialClassId = (Integer.parseInt(materialClassName));
			List<ScmMaterialGroup> scmMaterialGroupList = scmMaterialGroupBiz.findChild(materialClassId, param);
			StringBuffer sbMaterilaClass = new StringBuffer("");
			if (scmMaterialGroupList != null && scmMaterialGroupList.size() > 0) {
				sbMaterilaClass.append("(");
				for (int i = 0; i < scmMaterialGroupList.size(); i++) {
					sbMaterilaClass.append(scmMaterialGroupList.get(i).getId());
					if (i == scmMaterialGroupList.size() - 1) {
						break;
					}
					sbMaterilaClass.append(",");
				}
				sbMaterilaClass.append(")");
				map.put("materialClassIds", sbMaterilaClass.toString());
			}
		}

		// 2 设置查询条件
		map.put("materialId", materialId);
		map.put("flag", flag);
		StringBuffer sb = new StringBuffer("");
		List<OrgStorage2> orgStorageList = orgStorageBiz.findChild(invOrgUnitNoParam, param);
		if (orgStorageList == null || orgStorageList.isEmpty()) {
			map.put("finOrgUnitNo", null);
		}
		if (orgStorageList != null && orgStorageList.size() > 0) {
			Page page = new Page();
			page.setModelClass(OrgCompany2.class);
			page.setShowCount(Integer.MAX_VALUE);
			for (OrgStorage2 orgStorage : orgStorageList) {
				List<String> arglist = new ArrayList<>();
				arglist.add("type=to");
				arglist.add("relationType=" + OrgUnitRelationType.INVTOFIN);
				arglist.add("fromOrgUnitNo=" + orgStorage.getOrgUnitNo());
				List<OrgCompany2> orgCompanyList = orgCompanyBiz.queryPage(page, arglist, "queryPageEx", param);
				if (orgCompanyList != null && !orgCompanyList.isEmpty()) {
					if (StringUtils.isNotBlank(sb.toString()))
						sb.append(",");
					sb.append(orgCompanyList.get(0).getOrgUnitNo());
				}
			}
			if (StringUtils.isNotBlank(sb.toString())) {
				map.put("finOrgUnitNo", "(" + sb.toString() + ")");
			}
		}
		// 3 查询数据库
		List<ScmInvGlobalStock> list = ((ScmInvReportDAO) dao).selectGlobalInventory(map);

		// 4 数据处理及返回
		BigDecimal totalTaxAmt = BigDecimal.ZERO;
		BigDecimal totalAmt = BigDecimal.ZERO;
		BigDecimal finTotalAmt = BigDecimal.ZERO;
		BigDecimal finTotalTaxAmt = BigDecimal.ZERO;
		List<String> finList = new ArrayList<String>();
		Set<String> finSet = new HashSet<>();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				ScmInvGlobalStock scmInvGlobalStock = list.get(i);
				if (scmInvGlobalStock.isCostCenter()) {
					OrgCostCenter2 orgCostCenter = orgCostCenterBiz
							.selectByOrgUnitNo(scmInvGlobalStock.getCostOrgUnitNo(), param);
					if (StringUtils.equals("1", orgCostCenter.getCostCenterType())) {
						// 以领代耗的结存删除
						list.remove(i);
						continue;
					}
				}
				if (scmInvGlobalStock.getFinOrgUnitNo() != null) {
					if (finSet.add(scmInvGlobalStock.getFinOrgUnitNo())) {
						finList.add(scmInvGlobalStock.getFinOrgUnitNo());// 财务组织的个数
					}
				}
			}
			// 合计财务, 成本中心, 库存组织
			if (finList != null && finList.size() > 0) {
				for (String finString : finList) {
					ScmInvGlobalStock finScmInvStock = new ScmInvGlobalStock();
					for (ScmInvGlobalStock scmInvGlobalStock : list) {
						if (StringUtils.equals(finString, scmInvGlobalStock.getFinOrgUnitNo())) {
							// 根据财务组织求和
							finTotalAmt = finTotalAmt.add(scmInvGlobalStock.getAmt());
							finTotalTaxAmt = finTotalTaxAmt.add(scmInvGlobalStock.getTaxAmt());
							finScmInvStock.setOrgUnitNo("");
							finScmInvStock.setCostOrgUnitNo("");
							finScmInvStock.setCostCenter(true); // 用于排序
							finScmInvStock.setWareHouseId(0);
						}
					}
					finScmInvStock.setFinOrgUnitNo(finString);
					finScmInvStock.setAmt(finTotalAmt);
					finScmInvStock.setTaxAmt(finTotalTaxAmt);
					finScmInvStock.setLevel(1);// 第一级
					list.add(finScmInvStock);
					finTotalAmt = BigDecimal.ZERO;
					finTotalTaxAmt = BigDecimal.ZERO;
				}
			}
			for (ScmInvGlobalStock scmInvGlobalStock : list) {
				if (!scmInvGlobalStock.isCostCenter()) {
					scmInvGlobalStock.setCostOrgUnitNo("");
				}
			}
			// 排序
			Collections.sort(list, new Comparator<ScmInvGlobalStock>() {

				@Override
				public int compare(ScmInvGlobalStock o1, ScmInvGlobalStock o2) {
					if (o1.getFinOrgUnitNo().compareTo(o2.getFinOrgUnitNo()) > 0) {
						return 1;
					} else if (o1.getFinOrgUnitNo().compareTo(o2.getFinOrgUnitNo()) < 0) {
						return -1;
					} else {
						if (o1.isCostCenter() ^ o2.isCostCenter()) {
							return o1.isCostCenter() ? -1 : 1;
						} else {
							if (o1.getCostOrgUnitNo().compareTo(o2.getCostOrgUnitNo()) > 0) {
								return 1;
							} else if (o1.getCostOrgUnitNo().compareTo(o2.getCostOrgUnitNo()) < 0) {
								return -1;
							} else {
								if (o1.getOrgUnitNo().compareTo(o2.getOrgUnitNo()) < 0) {
									return 1;
								} else if (o1.getOrgUnitNo().compareTo(o2.getOrgUnitNo()) < 0) {
									return -1;
								} else {
									if (o1.getWareHouseId() > o2.getWareHouseId()) {
										return 1;
									} else if (o1.getWareHouseId() < o2.getWareHouseId()) {
										return -1;
									} else {
										if (o1.getLevel() > o2.getLevel()) {
											return 1;
										} else {
											return -1;
										}
									}
								}
							}
						}
					}
				}

			});
			OrgBaseUnit finOrgUnitNo = new OrgBaseUnit();
			OrgBaseUnit orgUnitNo = new OrgBaseUnit();
			ScmInvWareHouse scmInvWareHouse = new ScmInvWareHouse();
			for (int i = 0; i < list.size(); i++) {
				ScmInvGlobalStock scmInvGlobalStock = list.get(i);
				if (scmInvGlobalStock.getFinOrgUnitNo() != null) {
					// 财务组织
					finOrgUnitNo = orgBaseUnitBiz.selectbyOrgNo(scmInvGlobalStock.getFinOrgUnitNo(), param);
				}
				if (scmInvGlobalStock.getOrgUnitNo() != null) {
					// 组织
					orgUnitNo = orgBaseUnitBiz.selectbyOrgNo(scmInvGlobalStock.getOrgUnitNo(), param);
				}
				if (scmInvGlobalStock.getWareHouseId() > 0) {
					// 仓库
					scmInvWareHouse = scmInvWareHouseBiz.selectDirect(scmInvGlobalStock.getWareHouseId(), param);
				}
				if (scmInvGlobalStock.getUnit() > 0) {
					// 库存单位
					ScmMeasureUnit scmMeasureUnit = scmMeasureUnitBiz.selectDirect(scmInvGlobalStock.getUnit(), param);
					if (scmMeasureUnit != null) {
						scmInvGlobalStock.setUnitName(scmMeasureUnit.getUnitName());
					}
				} else {
					scmInvGlobalStock.setUnitName("");
				}
				if (scmInvGlobalStock.getLevel() == 1) {
					if (finOrgUnitNo != null) {
						scmInvGlobalStock
								.setOrgOrWhName(scmInvGlobalStock.getLevel() + "  " + finOrgUnitNo.getOrgUnitName());
						totalTaxAmt = totalTaxAmt.add(scmInvGlobalStock.getTaxAmt());
						totalAmt = totalAmt.add(scmInvGlobalStock.getAmt());
					}
				} else if (scmInvGlobalStock.getLevel() == 2) {
					if (scmInvGlobalStock.isCostCenter()) {
						if (orgUnitNo != null) {
							scmInvGlobalStock.setOrgOrWhName(
									"--" + scmInvGlobalStock.getLevel() + "  " + orgUnitNo.getOrgUnitName());
						}
					} else {
						if (scmInvWareHouse != null) {
							scmInvGlobalStock.setOrgOrWhName(
									"--" + scmInvGlobalStock.getLevel() + "  " + scmInvWareHouse.getWhName());
						}
					}
				}

			}
			for (int i = 0; i < list.size(); i++) {
				list.get(i).setTotalAmt(totalAmt);
				list.get(i).setTotalTaxAmt(totalTaxAmt);
			}
		}
		return list;
	}

	@Override
	public List<ScmInvRealtimeStockSum> selectImmediateInvSum(HttpServletRequest request) throws AppException {
		HashMap<String, Object> map = new HashMap<>();
		String currentOrgUnitNo = request.getParameter("orgUnitNo");
		String currentControlUnitNo = request.getParameter("controlUnitNo");
		String invOrgUnitNo = request.getParameter("invOrgUnitNo");
		if (StringUtils.isBlank(invOrgUnitNo))
			invOrgUnitNo = currentOrgUnitNo;
		Param param = new Param();
		param.setOrgUnitNo(currentOrgUnitNo);
		param.setControlUnitNo(currentControlUnitNo);
		if (StringUtils.isNotBlank(invOrgUnitNo)) {
			List<OrgStorage2> orgStorageList = orgStorageBiz.findChild(invOrgUnitNo, param);
			StringBuffer sbInv = new StringBuffer("");
			if (orgStorageList == null || orgStorageList.isEmpty()) {
				map.put("invOrgUnitNo", "'" + invOrgUnitNo + "'");
			} else {
				for (OrgStorage2 orgStorage : orgStorageList) {
					if (StringUtils.isNotBlank(sbInv.toString()))
						sbInv.append(",");
					sbInv.append("'").append(orgStorage.getOrgUnitNo()).append("'");
				}
				map.put("invOrgUnitNo", sbInv.toString());
			}
		}
		String whName = request.getParameter("whName");
		int wareHouseId = 0;
		if (StringUtils.isNotBlank(whName) && StringUtils.isNumeric(whName)) {
			wareHouseId = (Integer.parseInt(whName));
		}
		map.put("wareHouseId", wareHouseId);
		String materialClassName = request.getParameter("materialClassName");
		int materialClassId = 0;
		if (StringUtils.isNotBlank(materialClassName) && StringUtils.isNumeric(materialClassName)) {
			materialClassId = (Integer.parseInt(materialClassName));
			List<ScmMaterialGroup> scmMaterialGroupList = scmMaterialGroupBiz.findChild(materialClassId, param);
			StringBuffer sbMaterilaClass = new StringBuffer("");
			if (scmMaterialGroupList != null && scmMaterialGroupList.size() > 0) {
				for (ScmMaterialGroup scmMaterialGroup : scmMaterialGroupList) {
					if (StringUtils.isNotBlank(sbMaterilaClass.toString()))
						sbMaterilaClass.append(",");
					sbMaterilaClass.append(String.valueOf(scmMaterialGroup.getId()));
				}
				map.put("materialClassIds", sbMaterilaClass.toString());
			}
		}
		String materialName = request.getParameter("materialName");
		int materialId = 0;
		if (StringUtils.isNotBlank(materialName) && StringUtils.isNumeric(materialName)) {
			materialId = (Integer.parseInt(materialName));
		}
		map.put("materialId", materialId);
		List<ScmInvRealtimeStockSum> list = ((ScmInvReportDAO) dao).selectImmediateInvSum(map);
		if (list != null && list.size() > 0) {
//			BigDecimal totalAmt = BigDecimal.ZERO;
//			BigDecimal totalTaxAmt = BigDecimal.ZERO;
//			BigDecimal totalAddInAmt = BigDecimal.ZERO;
//			BigDecimal totalAddInTaxAmt = BigDecimal.ZERO;
//			BigDecimal totalOutAmt = BigDecimal.ZERO;
//			BigDecimal totalOutTaxAmt = BigDecimal.ZERO;
//			BigDecimal totalAllStockAmt = BigDecimal.ZERO;
//			BigDecimal totalAllStockTaxAmt = BigDecimal.ZERO;
			for (ScmInvRealtimeStockSum scmInvRealtimeStockSum : list) {
				if (scmInvRealtimeStockSum.getOrgUnitNo() != null) {
					// 库存组织
					OrgBaseUnit orgBaseUnit = orgBaseUnitBiz.selectbyOrgNo(scmInvRealtimeStockSum.getOrgUnitNo(),
							param);
					if (orgBaseUnit != null) {
						scmInvRealtimeStockSum.setOrgUnitName(orgBaseUnit.getOrgUnitName());
					}
				}
				if (scmInvRealtimeStockSum.getWareHouseId() > 0) {
					// 仓库
					ScmInvWareHouse scmInvWareHouse = scmInvWareHouseBiz
							.selectDirect(scmInvRealtimeStockSum.getWareHouseId(), param);
					if (scmInvWareHouse != null) {
						scmInvRealtimeStockSum.setWhName(scmInvWareHouse.getWhName());
					}
				}
				if (scmInvRealtimeStockSum.getUnit() > 0) {
					// 库存单位
					ScmMeasureUnit scmMeasureUnit = scmMeasureUnitBiz.selectDirect(scmInvRealtimeStockSum.getUnit(),
							param);
					if (scmMeasureUnit != null) {
						scmInvRealtimeStockSum.setUnitName(scmMeasureUnit.getUnitName());
					}
				}
				if (scmInvRealtimeStockSum.getPieUnit() > 0) {
					// 辅助单位
					ScmMeasureUnit scmMeasureUnit = scmMeasureUnitBiz.selectDirect(scmInvRealtimeStockSum.getPieUnit(),
							param);
					if (scmMeasureUnit != null) {
						scmInvRealtimeStockSum.setPieUnitName(scmMeasureUnit.getUnitName());
					}
				}
//				totalAmt = totalAmt.add(scmInvRealtimeStockSum.getAmt());
//				totalTaxAmt = totalTaxAmt.add(scmInvRealtimeStockSum.getTaxAmt());
//				totalAddInAmt = totalAddInAmt.add(scmInvRealtimeStockSum.getAddInAmt());
//				totalAddInTaxAmt = totalAddInTaxAmt.add(scmInvRealtimeStockSum.getAddInTaxAmt());
//				totalOutAmt = totalOutAmt.add(scmInvRealtimeStockSum.getOutAmt());
//				totalOutTaxAmt = totalOutTaxAmt.add(scmInvRealtimeStockSum.getOutTaxAmt());
//				totalAllStockAmt = totalAllStockAmt.add(scmInvRealtimeStockSum.getAllStockAmt());
//				totalAllStockTaxAmt = totalAllStockTaxAmt.add(scmInvRealtimeStockSum.getAllStockTaxAmt());
			}
//			for (int i = 0; i < list.size(); i++) {
//				list.get(i).setTotalAmt(totalAmt);
//				list.get(i).setTotalTaxAmt(totalTaxAmt);
//				list.get(i).setTotalAddInAmt(totalAddInAmt);
//				list.get(i).setTotalAddInTaxAmt(totalAddInTaxAmt);
//				list.get(i).setTotalOutAmt(totalOutAmt);
//				list.get(i).setTotalOutTaxAmt(totalOutTaxAmt);
//				list.get(i).setTotalAllStockAmt(totalAllStockAmt);
//				list.get(i).setTotalAllStockTaxAmt(totalAllStockTaxAmt);
//			}
		}
		return list;
	}

	@Override
	public List<ScmInvItemInAndOutSum> selectScmInOutSummary(HttpServletRequest httpServletRequest) {
		List<ScmInvItemInAndOutSum> scmInvItemInAndOutSumList = new ArrayList<ScmInvItemInAndOutSum>();
		String currentOrgUnitNo = httpServletRequest.getParameter("orgUnitNo");
		String currentControlUnitNo = httpServletRequest.getParameter("controlUnitNo");
		String orgUnitNo = httpServletRequest.getParameter("invOrgUnitNo");
		String beginDate = httpServletRequest.getParameter("beginDate");
		String endDate = httpServletRequest.getParameter("endDate");
		String groupBy = httpServletRequest.getParameter("groupBy");
		String status = "1".equals(httpServletRequest.getParameter("status")) ? "Y" : "N";
		if (StringUtils.isBlank(beginDate) || StringUtils.isBlank(endDate))
			return scmInvItemInAndOutSumList;
		if (StringUtils.isBlank(orgUnitNo))
			orgUnitNo = currentOrgUnitNo;
		Param param = new Param();
		param.setOrgUnitNo(currentOrgUnitNo);
		param.setControlUnitNo(currentControlUnitNo);
		StringBuffer orgUnitNos = new StringBuffer("");
		List<OrgStorage2> orgStorageList = orgStorageBiz.findChild(orgUnitNo, param);
		if (orgStorageList != null && !orgStorageList.isEmpty()) {
			for (OrgStorage2 orgStorage : orgStorageList) {
				if (StringUtils.isNotBlank(orgUnitNos.toString()))
					orgUnitNos.append(",");
				orgUnitNos.append("'").append(orgStorage.getOrgUnitNo()).append("'");
			}
		} else {
			orgUnitNos.append("'").append(orgUnitNo).append("'");
		}
		String materialClassName = httpServletRequest.getParameter("materialClassName");
		StringBuffer sbMaterilaClass = new StringBuffer("");
		if (StringUtils.isNotBlank(materialClassName)) {
			String[] materialClassNameList = StringUtils.split(materialClassName, ",");
			for (String materialClass : materialClassNameList) {
				if (StringUtils.isBlank(materialClass))
					continue;
				int materialClassId = Integer.parseInt(materialClass);
				List<ScmMaterialGroup> scmMaterialGroupList = scmMaterialGroupBiz.findChild(materialClassId, param);
				if (scmMaterialGroupList != null && scmMaterialGroupList.size() > 0) {
					for (ScmMaterialGroup scmMaterialGroup : scmMaterialGroupList) {
						if (StringUtils.isNotBlank(sbMaterilaClass.toString()))
							sbMaterilaClass.append(",");
						sbMaterilaClass.append(String.valueOf(scmMaterialGroup.getId()));
					}
				}
			}
		}
		String materialName = httpServletRequest.getParameter("materialName");
		StringBuffer sbMaterila = new StringBuffer("");
		if (StringUtils.isNotBlank(materialName)) {
			String[] materialNameList = StringUtils.split(materialName, ",");
			for (String material : materialNameList) {
				if (StringUtils.isBlank(material))
					continue;
				int materialId = Integer.parseInt(material);
				if (StringUtils.isNotBlank(sbMaterila.toString()))
					sbMaterila.append(",");
				sbMaterila.append(String.valueOf(materialId));
			}
		}
		String whName = httpServletRequest.getParameter("whName");
		StringBuffer sbWh = new StringBuffer("");
		if (StringUtils.isNotBlank(whName)) {
			String[] whNameList = StringUtils.split(whName, ",");
			for (String wh : whNameList) {
				if (StringUtils.isBlank(wh))
					continue;
				int whId = Integer.parseInt(wh);
				if (StringUtils.isNotBlank(sbWh.toString()))
					sbWh.append(",");
				sbWh.append(String.valueOf(whId));
			}
		}
		// 获取期间期初数
		PeriodCalendar periodCalendar = periodCalendarBiz.selectByBizdate(FormatUtils.parseDate(beginDate), param);
		if (periodCalendar != null) {
			Page page = new Page();
			page.setModelClass(ScmInvBal.class);
			page.setShowCount(Integer.MAX_VALUE);
			page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvBal.class) + "." + ScmInvBal.FN_ORGUNITNO,
					new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvBal.class) + "." + ScmInvBal.FN_ORGUNITNO,
							QueryParam.QUERY_IN, orgUnitNos.toString()));
			if (StringUtils.isNotBlank(sbWh.toString())) {
				page.getParam().put(
						ClassUtils.getFinalModelSimpleName(ScmInvBal.class) + "." + ScmInvBal.FN_WAREHOUSEID,
						new QueryParam(
								ClassUtils.getFinalModelSimpleName(ScmInvBal.class) + "." + ScmInvBal.FN_WAREHOUSEID,
								QueryParam.QUERY_IN, sbWh.toString()));
			}
			if (StringUtils.isNotBlank(sbMaterilaClass.toString())) {
				page.getParam()
						.put(ClassUtils.getFinalModelSimpleName(ScmMaterialGroup.class) + "." + ScmMaterialGroup.FN_ID,
								new QueryParam(
										ClassUtils.getFinalModelSimpleName(ScmMaterialGroup.class) + "."
												+ ScmMaterialGroup.FN_ID,
										QueryParam.QUERY_IN, sbMaterilaClass.toString()));
			}
			if (StringUtils.isNotBlank(sbMaterila.toString())) {
				page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvBal.class) + "." + ScmInvBal.FN_ITEMID,
						new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvBal.class) + "." + ScmInvBal.FN_ITEMID,
								QueryParam.QUERY_IN, sbMaterila.toString()));
			}
			page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvBal.class) + "." + ScmInvBal.FN_STARTQTY,
					new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvBal.class) + "." + ScmInvBal.FN_STARTQTY,
							QueryParam.QUERY_GT, "0"));
			page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvBal.class) + "." + ScmInvBal.FN_PERIODID,
					new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvBal.class) + "." + ScmInvBal.FN_PERIODID,
							QueryParam.QUERY_EQ, String.valueOf(periodCalendar.getPeriodId())));
			page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvBal.class) + "." + ScmInvBal.FN_COSTCENTER,
					new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvBal.class) + "." + ScmInvBal.FN_COSTCENTER,
							QueryParam.QUERY_EQ, "0"));
			List<String> arglist = new ArrayList<>();
			arglist.add("controlUnitNo=" + currentControlUnitNo);
			List<ScmInvBal2> scmInvBalList = scmInvBalBiz.queryPage(page, arglist, "findItemPage", param);
			if (scmInvBalList != null && !scmInvBalList.isEmpty()) {
				for (ScmInvBal2 scmInvBal : scmInvBalList) {
					boolean exists = false;
					if (!scmInvItemInAndOutSumList.isEmpty()) {
						for (ScmInvItemInAndOutSum existScmInvItemInAndOutSum : scmInvItemInAndOutSumList) {
							if (existScmInvItemInAndOutSum.getItemId() == scmInvBal.getItemId() && StringUtils
									.equals(existScmInvItemInAndOutSum.getOrgUnitNo(), scmInvBal.getOrgUnitNo())) {
								if (StringUtils.equals("W", groupBy)||StringUtils.equals("M", groupBy)) {
									if (existScmInvItemInAndOutSum.getWareHouseId() == scmInvBal.getWareHouseId()) {
										existScmInvItemInAndOutSum.setInitQty(
												existScmInvItemInAndOutSum.getInitQty().add(scmInvBal.getStartQty()));
										existScmInvItemInAndOutSum.setInitAmt(
												existScmInvItemInAndOutSum.getInitAmt().add(scmInvBal.getStartAmt()));
										existScmInvItemInAndOutSum.setInitTax(existScmInvItemInAndOutSum.getInitTax()
												.add(scmInvBal.getStartTaxAmt().subtract(scmInvBal.getStartAmt())));
										existScmInvItemInAndOutSum.setInitTaxAmt(existScmInvItemInAndOutSum
												.getInitTaxAmt().add(scmInvBal.getStartTaxAmt()));
										exists = true;
										break;
									}
								} else {
									if (StringUtils.equals(existScmInvItemInAndOutSum.getWareHouseName(),
											scmInvBal.getLot())) {
										existScmInvItemInAndOutSum.setInitQty(
												existScmInvItemInAndOutSum.getInitQty().add(scmInvBal.getStartQty()));
										existScmInvItemInAndOutSum.setInitAmt(
												existScmInvItemInAndOutSum.getInitAmt().add(scmInvBal.getStartAmt()));
										existScmInvItemInAndOutSum.setInitTax(existScmInvItemInAndOutSum.getInitTax()
												.add(scmInvBal.getStartTaxAmt().subtract(scmInvBal.getStartAmt())));
										existScmInvItemInAndOutSum.setInitTaxAmt(existScmInvItemInAndOutSum
												.getInitTaxAmt().add(scmInvBal.getStartTaxAmt()));
										exists = true;
										break;
									}
								}
							}
						}
					}
					if (!exists) {
						ScmInvItemInAndOutSum scmInvItemInAndOutSum = new ScmInvItemInAndOutSum(true);
						scmInvItemInAndOutSum.setItemId(scmInvBal.getItemId());
						scmInvItemInAndOutSum.setWareHouseName(scmInvBal.getWhName());
						scmInvItemInAndOutSum.setUnitName(scmInvBal.getUnitName());
						scmInvItemInAndOutSum.setItemNo(scmInvBal.getItemNo());
						scmInvItemInAndOutSum.setItemName(scmInvBal.getItemName());
						scmInvItemInAndOutSum.setSpec(scmInvBal.getSpec());
						scmInvItemInAndOutSum.setClassName(scmInvBal.getGroupName());
						scmInvItemInAndOutSum.setUnit(scmInvBal.getUnit());
						scmInvItemInAndOutSum.setOrgUnitNo(scmInvBal.getOrgUnitNo());
						if (StringUtils.equals("W", groupBy)||StringUtils.equals("M", groupBy)) {
							scmInvItemInAndOutSum.setWareHouseId(scmInvBal.getWareHouseId());
						} else {
							scmInvItemInAndOutSum.setWareHouseName(scmInvBal.getLot());
						}
						scmInvItemInAndOutSum.setInitQty(scmInvBal.getStartQty());
						scmInvItemInAndOutSum.setInitAmt(scmInvBal.getStartAmt());
						scmInvItemInAndOutSum.setInitTax(scmInvBal.getStartTaxAmt().subtract(scmInvBal.getStartAmt()));
						scmInvItemInAndOutSum.setInitTaxAmt(scmInvBal.getStartTaxAmt());
						scmInvItemInAndOutSumList.add(scmInvItemInAndOutSum);
					}
				}
			}
		}
		// 获取入库数据
		Page page = new Page();
		page.setModelClass(ScmInvPurInWarehsBillEntry2.class);
		page.setShowCount(Integer.MAX_VALUE);
		if (StringUtils.isNotBlank(sbMaterilaClass.toString())) {
			page.getParam().put(
					ClassUtils.getFinalModelSimpleName(ScmMaterialGroup.class) + "." + ScmMaterialGroup.FN_ID,
					new QueryParam(
							ClassUtils.getFinalModelSimpleName(ScmMaterialGroup.class) + "." + ScmMaterialGroup.FN_ID,
							QueryParam.QUERY_IN, sbMaterilaClass.toString()));
		}
		if (StringUtils.isNotBlank(sbMaterila.toString())) {
			page.getParam()
					.put(ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBillEntry2.class) + "."
							+ ScmInvPurInWarehsBillEntry2.FN_ITEMID,
							new QueryParam(
									ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBillEntry2.class) + "."
											+ ScmInvPurInWarehsBillEntry2.FN_ITEMID,
									QueryParam.QUERY_IN, sbMaterila.toString()));
		}
		page.getParam()
				.put(ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBillEntry2.class) + "."
						+ ScmInvPurInWarehsBillEntry2.FN_ORGUNITNO,
						new QueryParam(
								ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBillEntry2.class) + "."
										+ ScmInvPurInWarehsBillEntry2.FN_ORGUNITNO,
								QueryParam.QUERY_IN, orgUnitNos.toString()));
		if (StringUtils.isNotBlank(sbWh.toString())) {
			page.getParam()
					.put(ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBillEntry2.class) + "."
							+ ScmInvPurInWarehsBillEntry2.FN_WAREHOUSEID,
							new QueryParam(
									ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBillEntry2.class) + "."
											+ ScmInvPurInWarehsBillEntry2.FN_WAREHOUSEID,
									QueryParam.QUERY_IN, sbWh.toString()));
		} else {
			page.getParam()
					.put(ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBillEntry2.class) + "."
							+ ScmInvPurInWarehsBillEntry2.FN_WAREHOUSEID,
							new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBillEntry2.class) + "."
									+ ScmInvPurInWarehsBillEntry2.FN_WAREHOUSEID, QueryParam.QUERY_GT, "0"));
		}
		page.getParam()
				.put(ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBill2.class) + "."
						+ ScmInvPurInWarehsBill2.FN_BIZDATE,
						new QueryParam(
								ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBill2.class) + "."
										+ ScmInvPurInWarehsBill2.FN_BIZDATE,
								QueryParam.QUERY_BETWEEN, FormatUtils.fmtDate(periodCalendar.getStartDate()), endDate));
		if (!StringUtils.equals("Y", status)) {
			page.getParam()
					.put(ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBill2.class) + "."
							+ ScmInvPurInWarehsBill2.FN_STATUS,
							new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBill2.class) + "."
									+ ScmInvPurInWarehsBill2.FN_STATUS, QueryParam.QUERY_EQ, "E"));
		}
		List<String> arglist = new ArrayList<>();
		arglist.add("controlUnitNo=" + currentControlUnitNo);
		List<ScmInvPurInWarehsBillEntry2> scmInvPurInWarehsBillEntryList = scmInvPurInWarehsBillEntryBiz.queryPage(page,
				arglist, "findAllPage", param);
		if (scmInvPurInWarehsBillEntryList != null && !scmInvPurInWarehsBillEntryList.isEmpty()) {
			for (ScmInvPurInWarehsBillEntry2 scmInvPurInWarehsBillEntry : scmInvPurInWarehsBillEntryList) {
				BigDecimal sideFlag = BigDecimal.ONE;
				if (StringUtils.contains("6,8", scmInvPurInWarehsBillEntry.getBizType())) {
					sideFlag = new BigDecimal("-1");
				}
				if (scmInvPurInWarehsBillEntry.getBizDate().compareTo(FormatUtils.parseDate(beginDate)) < 0) {
					// 比查询日期小的统计到期初数据中，并从List中删除
					boolean exists = false;
					if (!scmInvItemInAndOutSumList.isEmpty()) {
						for (ScmInvItemInAndOutSum existScmInvItemInAndOutSum : scmInvItemInAndOutSumList) {
							if (existScmInvItemInAndOutSum.getItemId() == scmInvPurInWarehsBillEntry.getItemId()
									&& StringUtils.equals(existScmInvItemInAndOutSum.getOrgUnitNo(),
											scmInvPurInWarehsBillEntry.getOrgUnitNo())) {
								if (StringUtils.equals("W", groupBy)||StringUtils.equals("M", groupBy)) {
									if (existScmInvItemInAndOutSum.getWareHouseId() == scmInvPurInWarehsBillEntry
											.getWareHouseId()) {
										existScmInvItemInAndOutSum.setInitQty(existScmInvItemInAndOutSum.getInitQty()
												.add(sideFlag.multiply(scmInvPurInWarehsBillEntry.getQty())));
										existScmInvItemInAndOutSum.setInitAmt(existScmInvItemInAndOutSum.getInitAmt()
												.add(sideFlag.multiply(scmInvPurInWarehsBillEntry.getAmt())));
										existScmInvItemInAndOutSum.setInitTax(existScmInvItemInAndOutSum.getInitTax()
												.add(sideFlag.multiply(scmInvPurInWarehsBillEntry.getTaxAmt()
														.subtract(scmInvPurInWarehsBillEntry.getAmt()))));
										existScmInvItemInAndOutSum
												.setInitTaxAmt(existScmInvItemInAndOutSum.getInitTaxAmt().add(
														sideFlag.multiply(scmInvPurInWarehsBillEntry.getTaxAmt())));
										exists = true;
										break;
									}
								} else {
									if (StringUtils.equals(existScmInvItemInAndOutSum.getWareHouseName(),
											scmInvPurInWarehsBillEntry.getLot())) {
										existScmInvItemInAndOutSum.setInitQty(existScmInvItemInAndOutSum.getInitQty()
												.add(sideFlag.multiply(scmInvPurInWarehsBillEntry.getQty())));
										existScmInvItemInAndOutSum.setInitAmt(existScmInvItemInAndOutSum.getInitAmt()
												.add(sideFlag.multiply(scmInvPurInWarehsBillEntry.getAmt())));
										existScmInvItemInAndOutSum.setInitTax(existScmInvItemInAndOutSum.getInitTax()
												.add(sideFlag.multiply(scmInvPurInWarehsBillEntry.getTaxAmt()
														.subtract(scmInvPurInWarehsBillEntry.getAmt()))));
										existScmInvItemInAndOutSum
												.setInitTaxAmt(existScmInvItemInAndOutSum.getInitTaxAmt().add(
														sideFlag.multiply(scmInvPurInWarehsBillEntry.getTaxAmt())));
										exists = true;
										break;
									}
								}
							}
						}
					}
					if (!exists) {
						ScmInvItemInAndOutSum scmInvItemInAndOutSum = new ScmInvItemInAndOutSum(true);
						scmInvItemInAndOutSum.setItemId(scmInvPurInWarehsBillEntry.getItemId());
						scmInvItemInAndOutSum.setWareHouseName(scmInvPurInWarehsBillEntry.getWhName());
						scmInvItemInAndOutSum.setUnitName(scmInvPurInWarehsBillEntry.getUnitName());
						scmInvItemInAndOutSum.setItemNo(scmInvPurInWarehsBillEntry.getItemNo());
						scmInvItemInAndOutSum.setItemName(scmInvPurInWarehsBillEntry.getItemName());
						scmInvItemInAndOutSum.setSpec(scmInvPurInWarehsBillEntry.getSpec());
						scmInvItemInAndOutSum.setClassName(scmInvPurInWarehsBillEntry.getGroupName());
						scmInvItemInAndOutSum.setUnit(scmInvPurInWarehsBillEntry.getUnit());
						scmInvItemInAndOutSum.setOrgUnitNo(scmInvPurInWarehsBillEntry.getOrgUnitNo());
						if (StringUtils.equals("W", groupBy)||StringUtils.equals("M", groupBy)) {
							scmInvItemInAndOutSum.setWareHouseId(scmInvPurInWarehsBillEntry.getWareHouseId());
						} else {
							scmInvItemInAndOutSum.setWareHouseName(scmInvPurInWarehsBillEntry.getLot());
						}
						scmInvItemInAndOutSum.setInitQty(sideFlag.multiply(scmInvPurInWarehsBillEntry.getQty()));
						scmInvItemInAndOutSum.setInitAmt(sideFlag.multiply(scmInvPurInWarehsBillEntry.getAmt()));
						scmInvItemInAndOutSum.setInitTax(sideFlag.multiply(
								scmInvPurInWarehsBillEntry.getTaxAmt().subtract(scmInvPurInWarehsBillEntry.getAmt())));
						scmInvItemInAndOutSum.setInitTaxAmt(sideFlag.multiply(scmInvPurInWarehsBillEntry.getTaxAmt()));
						scmInvItemInAndOutSumList.add(scmInvItemInAndOutSum);
					}
				} else {
					boolean exists = false;
					if (!scmInvItemInAndOutSumList.isEmpty()) {
						for (ScmInvItemInAndOutSum existScmInvItemInAndOutSum : scmInvItemInAndOutSumList) {
							if (existScmInvItemInAndOutSum.getItemId() == scmInvPurInWarehsBillEntry.getItemId()
									&& StringUtils.equals(existScmInvItemInAndOutSum.getOrgUnitNo(),
											scmInvPurInWarehsBillEntry.getOrgUnitNo())) {
								if (StringUtils.equals("W", groupBy)||StringUtils.equals("M", groupBy)) {
									if (existScmInvItemInAndOutSum.getWareHouseId() == scmInvPurInWarehsBillEntry
											.getWareHouseId()) {
										existScmInvItemInAndOutSum.setAddInQty(existScmInvItemInAndOutSum.getAddInQty()
												.add(sideFlag.multiply(scmInvPurInWarehsBillEntry.getQty())));
										existScmInvItemInAndOutSum.setAddInAmt(existScmInvItemInAndOutSum.getAddInAmt()
												.add(sideFlag.multiply(scmInvPurInWarehsBillEntry.getAmt())));
										existScmInvItemInAndOutSum.setAddInTax(existScmInvItemInAndOutSum.getAddInTax()
												.add(sideFlag.multiply(scmInvPurInWarehsBillEntry.getTaxAmt()
														.subtract(scmInvPurInWarehsBillEntry.getAmt()))));
										existScmInvItemInAndOutSum
												.setAddInTaxAmt(existScmInvItemInAndOutSum.getAddInTaxAmt().add(
														sideFlag.multiply(scmInvPurInWarehsBillEntry.getTaxAmt())));
										exists = true;
										break;
									}
								} else {
									if (StringUtils.equals(existScmInvItemInAndOutSum.getWareHouseName(),
											scmInvPurInWarehsBillEntry.getLot())) {
										existScmInvItemInAndOutSum.setAddInQty(existScmInvItemInAndOutSum.getAddInQty()
												.add(sideFlag.multiply(scmInvPurInWarehsBillEntry.getQty())));
										existScmInvItemInAndOutSum.setAddInAmt(existScmInvItemInAndOutSum.getAddInAmt()
												.add(sideFlag.multiply(scmInvPurInWarehsBillEntry.getAmt())));
										existScmInvItemInAndOutSum.setAddInTax(existScmInvItemInAndOutSum.getAddInTax()
												.add(sideFlag.multiply(scmInvPurInWarehsBillEntry.getTaxAmt()
														.subtract(scmInvPurInWarehsBillEntry.getAmt()))));
										existScmInvItemInAndOutSum
												.setAddInTaxAmt(existScmInvItemInAndOutSum.getAddInTaxAmt().add(
														sideFlag.multiply(scmInvPurInWarehsBillEntry.getTaxAmt())));
										exists = true;
										break;
									}
								}
							}
						}
					}
					if (!exists) {
						// 放入明细记录结果集中
						ScmInvItemInAndOutSum scmInvItemInAndOutSum = new ScmInvItemInAndOutSum(true);
						scmInvItemInAndOutSum.setItemId(scmInvPurInWarehsBillEntry.getItemId());
						scmInvItemInAndOutSum.setWareHouseName(scmInvPurInWarehsBillEntry.getWhName());
						scmInvItemInAndOutSum.setUnitName(scmInvPurInWarehsBillEntry.getUnitName());
						scmInvItemInAndOutSum.setItemNo(scmInvPurInWarehsBillEntry.getItemNo());
						scmInvItemInAndOutSum.setItemName(scmInvPurInWarehsBillEntry.getItemName());
						scmInvItemInAndOutSum.setSpec(scmInvPurInWarehsBillEntry.getSpec());
						scmInvItemInAndOutSum.setClassName(scmInvPurInWarehsBillEntry.getGroupName());
						scmInvItemInAndOutSum.setUnit(scmInvPurInWarehsBillEntry.getUnit());
						scmInvItemInAndOutSum.setOrgUnitNo(scmInvPurInWarehsBillEntry.getOrgUnitNo());
						if (StringUtils.equals("W", groupBy)||StringUtils.equals("M", groupBy)) {
							scmInvItemInAndOutSum.setWareHouseId(scmInvPurInWarehsBillEntry.getWareHouseId());
						} else {
							scmInvItemInAndOutSum.setWareHouseName(scmInvPurInWarehsBillEntry.getLot());
						}
						scmInvItemInAndOutSum.setAddInQty(sideFlag.multiply(scmInvPurInWarehsBillEntry.getQty()));
						scmInvItemInAndOutSum.setAddInAmt(sideFlag.multiply(scmInvPurInWarehsBillEntry.getAmt()));
						scmInvItemInAndOutSum.setAddInTax(sideFlag.multiply(
								scmInvPurInWarehsBillEntry.getTaxAmt().subtract(scmInvPurInWarehsBillEntry.getAmt())));
						scmInvItemInAndOutSum.setAddInTaxAmt(sideFlag.multiply(scmInvPurInWarehsBillEntry.getTaxAmt()));
						scmInvItemInAndOutSumList.add(scmInvItemInAndOutSum);
					}
				}
			}
		}
		// 其他入库
		page = new Page();
		page.setModelClass(ScmInvOtherInWarehsBillEntry2.class);
		page.setShowCount(Integer.MAX_VALUE);
		if (StringUtils.isNotBlank(sbMaterilaClass.toString())) {
			page.getParam().put(
					ClassUtils.getFinalModelSimpleName(ScmMaterialGroup.class) + "." + ScmMaterialGroup.FN_ID,
					new QueryParam(
							ClassUtils.getFinalModelSimpleName(ScmMaterialGroup.class) + "." + ScmMaterialGroup.FN_ID,
							QueryParam.QUERY_IN, sbMaterilaClass.toString()));
		}
		if (StringUtils.isNotBlank(sbMaterila.toString())) {
			page.getParam()
					.put(ClassUtils.getFinalModelSimpleName(ScmInvOtherInWarehsBillEntry2.class) + "."
							+ ScmInvOtherInWarehsBillEntry2.FN_ITEMID,
							new QueryParam(
									ClassUtils.getFinalModelSimpleName(ScmInvOtherInWarehsBillEntry2.class) + "."
											+ ScmInvOtherInWarehsBillEntry2.FN_ITEMID,
									QueryParam.QUERY_IN, sbMaterila.toString()));
		}
		page.getParam()
				.put(ClassUtils.getFinalModelSimpleName(ScmInvOtherInWarehsBill.class) + "."
						+ ScmInvOtherInWarehsBill.FN_ORGUNITNO,
						new QueryParam(
								ClassUtils.getFinalModelSimpleName(ScmInvOtherInWarehsBill.class) + "."
										+ ScmInvOtherInWarehsBill.FN_ORGUNITNO,
								QueryParam.QUERY_IN, orgUnitNos.toString()));
		if (StringUtils.isNotBlank(sbWh.toString())) {
			page.getParam()
					.put(ClassUtils.getFinalModelSimpleName(ScmInvOtherInWarehsBill.class) + "."
							+ ScmInvOtherInWarehsBill.FN_WAREHOUSEID,
							new QueryParam(
									ClassUtils.getFinalModelSimpleName(ScmInvOtherInWarehsBill.class) + "."
											+ ScmInvOtherInWarehsBill.FN_WAREHOUSEID,
									QueryParam.QUERY_IN, sbWh.toString()));
		}
		page.getParam()
				.put(ClassUtils.getFinalModelSimpleName(ScmInvOtherInWarehsBill.class) + "."
						+ ScmInvOtherInWarehsBill.FN_BIZDATE,
						new QueryParam(
								ClassUtils.getFinalModelSimpleName(ScmInvOtherInWarehsBill.class) + "."
										+ ScmInvOtherInWarehsBill.FN_BIZDATE,
								QueryParam.QUERY_BETWEEN, FormatUtils.fmtDate(periodCalendar.getStartDate()), endDate));
		if (!StringUtils.equals("Y", status)) {
			page.getParam()
					.put(ClassUtils.getFinalModelSimpleName(ScmInvOtherInWarehsBill.class) + "."
							+ ScmInvOtherInWarehsBill.FN_STATUS,
							new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvOtherInWarehsBill.class) + "."
									+ ScmInvOtherInWarehsBill.FN_STATUS, QueryParam.QUERY_EQ, "E"));
		}
		List<ScmInvOtherInWarehsBillEntry2> scmInvOtherInWarehsBillEntryList = scmInvOtherInWarehsBillEntryBiz
				.queryPage(page, arglist, "findAllPage", param);
		if (scmInvOtherInWarehsBillEntryList != null && !scmInvOtherInWarehsBillEntryList.isEmpty()) {
			for (ScmInvOtherInWarehsBillEntry2 scmInvOtherInWarehsBillEntry : scmInvOtherInWarehsBillEntryList) {
				if (scmInvOtherInWarehsBillEntry.getBizDate().compareTo(FormatUtils.parseDate(beginDate)) < 0) {
					// 比查询日期小的统计到期初数据中，并从List中删除
					boolean exists = false;
					if (!scmInvItemInAndOutSumList.isEmpty()) {
						for (ScmInvItemInAndOutSum existScmInvItemInAndOutSum : scmInvItemInAndOutSumList) {
							if (existScmInvItemInAndOutSum.getItemId() == scmInvOtherInWarehsBillEntry.getItemId()
									&& StringUtils.equals(existScmInvItemInAndOutSum.getOrgUnitNo(),
											scmInvOtherInWarehsBillEntry.getOrgUnitNo())) {
								if (StringUtils.equals("W", groupBy)||StringUtils.equals("M", groupBy)) {
									if (existScmInvItemInAndOutSum.getWareHouseId() == scmInvOtherInWarehsBillEntry
											.getWareHouseId()) {
										existScmInvItemInAndOutSum.setInitQty(existScmInvItemInAndOutSum.getInitQty()
												.add(scmInvOtherInWarehsBillEntry.getQty()));
										existScmInvItemInAndOutSum.setInitAmt(existScmInvItemInAndOutSum.getInitAmt()
												.add(scmInvOtherInWarehsBillEntry.getAmt()));
										existScmInvItemInAndOutSum.setInitTax(
												existScmInvItemInAndOutSum.getInitTax().add(scmInvOtherInWarehsBillEntry
														.getTaxAmt().subtract(scmInvOtherInWarehsBillEntry.getAmt())));
										existScmInvItemInAndOutSum.setInitTaxAmt(existScmInvItemInAndOutSum
												.getInitTaxAmt().add(scmInvOtherInWarehsBillEntry.getTaxAmt()));
										exists = true;
										break;
									}
								} else {
									if (StringUtils.equals(existScmInvItemInAndOutSum.getWareHouseName(),
											scmInvOtherInWarehsBillEntry.getLot())) {
										existScmInvItemInAndOutSum.setInitQty(existScmInvItemInAndOutSum.getInitQty()
												.add(scmInvOtherInWarehsBillEntry.getQty()));
										existScmInvItemInAndOutSum.setInitAmt(existScmInvItemInAndOutSum.getInitAmt()
												.add(scmInvOtherInWarehsBillEntry.getAmt()));
										existScmInvItemInAndOutSum.setInitTax(
												existScmInvItemInAndOutSum.getInitTax().add(scmInvOtherInWarehsBillEntry
														.getTaxAmt().subtract(scmInvOtherInWarehsBillEntry.getAmt())));
										existScmInvItemInAndOutSum.setInitTaxAmt(existScmInvItemInAndOutSum
												.getInitTaxAmt().add(scmInvOtherInWarehsBillEntry.getTaxAmt()));
										exists = true;
										break;
									}
								}
							}
						}
					}
					if (!exists) {
						ScmInvItemInAndOutSum scmInvItemInAndOutSum = new ScmInvItemInAndOutSum(true);
						scmInvItemInAndOutSum.setItemId(scmInvOtherInWarehsBillEntry.getItemId());
						scmInvItemInAndOutSum.setWareHouseName(scmInvOtherInWarehsBillEntry.getWhName());
						scmInvItemInAndOutSum.setUnitName(scmInvOtherInWarehsBillEntry.getUnitName());
						scmInvItemInAndOutSum.setItemNo(scmInvOtherInWarehsBillEntry.getItemNo());
						scmInvItemInAndOutSum.setItemName(scmInvOtherInWarehsBillEntry.getItemName());
						scmInvItemInAndOutSum.setSpec(scmInvOtherInWarehsBillEntry.getSpec());
						scmInvItemInAndOutSum.setClassName(scmInvOtherInWarehsBillEntry.getGroupName());
						scmInvItemInAndOutSum.setUnit(scmInvOtherInWarehsBillEntry.getUnit());
						scmInvItemInAndOutSum.setOrgUnitNo(scmInvOtherInWarehsBillEntry.getOrgUnitNo());
						if (StringUtils.equals("W", groupBy)||StringUtils.equals("M", groupBy)) {
							scmInvItemInAndOutSum.setWareHouseId(scmInvOtherInWarehsBillEntry.getWareHouseId());
						} else {
							scmInvItemInAndOutSum.setWareHouseName(scmInvOtherInWarehsBillEntry.getLot());
						}
						scmInvItemInAndOutSum.setInitQty(scmInvOtherInWarehsBillEntry.getQty());
						scmInvItemInAndOutSum.setInitAmt(scmInvOtherInWarehsBillEntry.getAmt());
						scmInvItemInAndOutSum.setInitTax(scmInvOtherInWarehsBillEntry.getTaxAmt()
								.subtract(scmInvOtherInWarehsBillEntry.getAmt()));
						scmInvItemInAndOutSum.setInitTaxAmt(scmInvOtherInWarehsBillEntry.getTaxAmt());
						scmInvItemInAndOutSumList.add(scmInvItemInAndOutSum);
					}
				} else {
					boolean exists = false;
					if (!scmInvItemInAndOutSumList.isEmpty()) {
						for (ScmInvItemInAndOutSum existScmInvItemInAndOutSum : scmInvItemInAndOutSumList) {
							if (existScmInvItemInAndOutSum.getItemId() == scmInvOtherInWarehsBillEntry.getItemId()
									&& StringUtils.equals(existScmInvItemInAndOutSum.getOrgUnitNo(),
											scmInvOtherInWarehsBillEntry.getOrgUnitNo())) {
								if (StringUtils.equals("W", groupBy)||StringUtils.equals("M", groupBy)) {
									if (existScmInvItemInAndOutSum.getWareHouseId() == scmInvOtherInWarehsBillEntry
											.getWareHouseId()) {
										if (StringUtils.equals("1", scmInvOtherInWarehsBillEntry.getBizType())) {
											// 盘盈
											existScmInvItemInAndOutSum.setProfitQty(existScmInvItemInAndOutSum
													.getProfitQty().add(scmInvOtherInWarehsBillEntry.getQty()));
											existScmInvItemInAndOutSum.setProfitAmt(existScmInvItemInAndOutSum
													.getProfitAmt().add(scmInvOtherInWarehsBillEntry.getAmt()));
											existScmInvItemInAndOutSum.setProfitTax(existScmInvItemInAndOutSum
													.getProfitTax().add(scmInvOtherInWarehsBillEntry.getTaxAmt()
															.subtract(scmInvOtherInWarehsBillEntry.getAmt())));
											existScmInvItemInAndOutSum.setProfitTaxAmt(existScmInvItemInAndOutSum
													.getProfitTaxAmt().add(scmInvOtherInWarehsBillEntry.getTaxAmt()));
										} else {
											existScmInvItemInAndOutSum.setAddInQty(existScmInvItemInAndOutSum
													.getAddInQty().add(scmInvOtherInWarehsBillEntry.getQty()));
											existScmInvItemInAndOutSum.setAddInAmt(existScmInvItemInAndOutSum
													.getAddInAmt().add(scmInvOtherInWarehsBillEntry.getAmt()));
											existScmInvItemInAndOutSum.setAddInTax(existScmInvItemInAndOutSum
													.getAddInTax().add(scmInvOtherInWarehsBillEntry.getTaxAmt()
															.subtract(scmInvOtherInWarehsBillEntry.getAmt())));
											existScmInvItemInAndOutSum.setAddInTaxAmt(existScmInvItemInAndOutSum
													.getAddInTaxAmt().add(scmInvOtherInWarehsBillEntry.getTaxAmt()));
										}
										exists = true;
										break;
									}
								} else {
									if (StringUtils.equals(existScmInvItemInAndOutSum.getWareHouseName(),
											scmInvOtherInWarehsBillEntry.getLot())) {
										if (StringUtils.equals("1", scmInvOtherInWarehsBillEntry.getBizType())) {
											// 盘盈
											existScmInvItemInAndOutSum.setProfitQty(existScmInvItemInAndOutSum
													.getProfitQty().add(scmInvOtherInWarehsBillEntry.getQty()));
											existScmInvItemInAndOutSum.setProfitAmt(existScmInvItemInAndOutSum
													.getProfitAmt().add(scmInvOtherInWarehsBillEntry.getAmt()));
											existScmInvItemInAndOutSum.setProfitTax(existScmInvItemInAndOutSum
													.getProfitTax().add(scmInvOtherInWarehsBillEntry.getTaxAmt()
															.subtract(scmInvOtherInWarehsBillEntry.getAmt())));
											existScmInvItemInAndOutSum.setProfitTaxAmt(existScmInvItemInAndOutSum
													.getProfitTaxAmt().add(scmInvOtherInWarehsBillEntry.getTaxAmt()));
										} else {
											existScmInvItemInAndOutSum.setAddInQty(existScmInvItemInAndOutSum
													.getAddInQty().add(scmInvOtherInWarehsBillEntry.getQty()));
											existScmInvItemInAndOutSum.setAddInAmt(existScmInvItemInAndOutSum
													.getAddInAmt().add(scmInvOtherInWarehsBillEntry.getAmt()));
											existScmInvItemInAndOutSum.setAddInTax(existScmInvItemInAndOutSum
													.getAddInTax().add(scmInvOtherInWarehsBillEntry.getTaxAmt()
															.subtract(scmInvOtherInWarehsBillEntry.getAmt())));
											existScmInvItemInAndOutSum.setAddInTaxAmt(existScmInvItemInAndOutSum
													.getAddInTaxAmt().add(scmInvOtherInWarehsBillEntry.getTaxAmt()));
										}
										exists = true;
										break;
									}
								}
							}
						}
					}
					if (!exists) {
						// 放入明细记录结果集中
						ScmInvItemInAndOutSum scmInvItemInAndOutSum = new ScmInvItemInAndOutSum(true);
						scmInvItemInAndOutSum.setItemId(scmInvOtherInWarehsBillEntry.getItemId());
						scmInvItemInAndOutSum.setWareHouseName(scmInvOtherInWarehsBillEntry.getWhName());
						scmInvItemInAndOutSum.setUnitName(scmInvOtherInWarehsBillEntry.getUnitName());
						scmInvItemInAndOutSum.setItemNo(scmInvOtherInWarehsBillEntry.getItemNo());
						scmInvItemInAndOutSum.setItemName(scmInvOtherInWarehsBillEntry.getItemName());
						scmInvItemInAndOutSum.setSpec(scmInvOtherInWarehsBillEntry.getSpec());
						scmInvItemInAndOutSum.setClassName(scmInvOtherInWarehsBillEntry.getGroupName());
						scmInvItemInAndOutSum.setUnit(scmInvOtherInWarehsBillEntry.getUnit());
						scmInvItemInAndOutSum.setOrgUnitNo(scmInvOtherInWarehsBillEntry.getOrgUnitNo());
						if (StringUtils.equals("W", groupBy)||StringUtils.equals("M", groupBy)) {
							scmInvItemInAndOutSum.setWareHouseId(scmInvOtherInWarehsBillEntry.getWareHouseId());
						} else {
							scmInvItemInAndOutSum.setWareHouseName(scmInvOtherInWarehsBillEntry.getLot());
						}
						if (StringUtils.equals("1", scmInvOtherInWarehsBillEntry.getBizType())) {
							// 盘盈
							scmInvItemInAndOutSum.setProfitQty(scmInvOtherInWarehsBillEntry.getQty());
							scmInvItemInAndOutSum.setProfitAmt(scmInvOtherInWarehsBillEntry.getAmt());
							scmInvItemInAndOutSum.setProfitTax(scmInvOtherInWarehsBillEntry.getTaxAmt()
									.subtract(scmInvOtherInWarehsBillEntry.getAmt()));
							scmInvItemInAndOutSum.setProfitTaxAmt(scmInvOtherInWarehsBillEntry.getTaxAmt());
						} else {
							scmInvItemInAndOutSum.setAddInQty(scmInvOtherInWarehsBillEntry.getQty());
							scmInvItemInAndOutSum.setAddInAmt(scmInvOtherInWarehsBillEntry.getAmt());
							scmInvItemInAndOutSum.setAddInTax(scmInvOtherInWarehsBillEntry.getTaxAmt()
									.subtract(scmInvOtherInWarehsBillEntry.getAmt()));
							scmInvItemInAndOutSum.setAddInTaxAmt(scmInvOtherInWarehsBillEntry.getTaxAmt());
						}
						scmInvItemInAndOutSumList.add(scmInvItemInAndOutSum);
					}
				}
			}
		}

		// 获取领料出库数据
		page = new Page();
		page.setModelClass(ScmInvPurInWarehsBillEntry2.class);
		page.setShowCount(Integer.MAX_VALUE);
		if (StringUtils.isNotBlank(sbMaterilaClass.toString())) {
			page.getParam().put(
					ClassUtils.getFinalModelSimpleName(ScmMaterialGroup.class) + "." + ScmMaterialGroup.FN_ID,
					new QueryParam(
							ClassUtils.getFinalModelSimpleName(ScmMaterialGroup.class) + "." + ScmMaterialGroup.FN_ID,
							QueryParam.QUERY_IN, sbMaterilaClass.toString()));
		}
		if (StringUtils.isNotBlank(sbMaterila.toString())) {
			page.getParam()
					.put(ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBillEntry2.class) + "."
							+ ScmInvMaterialReqBillEntry2.FN_ITEMID,
							new QueryParam(
									ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBillEntry2.class) + "."
											+ ScmInvMaterialReqBillEntry2.FN_ITEMID,
									QueryParam.QUERY_IN, sbMaterila.toString()));
		}
		page.getParam()
				.put(ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBill.class) + "."
						+ ScmInvMaterialReqBill.FN_ORGUNITNO,
						new QueryParam(
								ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBill.class) + "."
										+ ScmInvMaterialReqBill.FN_ORGUNITNO,
								QueryParam.QUERY_IN, orgUnitNos.toString()));
		if (StringUtils.isNotBlank(sbWh.toString())) {
			page.getParam()
					.put(ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBill.class) + "."
							+ ScmInvMaterialReqBill.FN_WAREHOUSEID,
							new QueryParam(
									ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBill.class) + "."
											+ ScmInvMaterialReqBill.FN_WAREHOUSEID,
									QueryParam.QUERY_IN, sbWh.toString()));
		}
		page.getParam()
				.put(ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBill2.class) + "."
						+ ScmInvMaterialReqBill2.FN_BIZDATE,
						new QueryParam(
								ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBill2.class) + "."
										+ ScmInvMaterialReqBill2.FN_BIZDATE,
								QueryParam.QUERY_BETWEEN, FormatUtils.fmtDate(periodCalendar.getStartDate()), endDate));
		if (!StringUtils.equals("Y", status)) {
			page.getParam()
					.put(ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBill2.class) + "."
							+ ScmInvMaterialReqBill2.FN_STATUS,
							new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBill2.class) + "."
									+ ScmInvMaterialReqBill2.FN_STATUS, QueryParam.QUERY_EQ, "E"));
		}
		List<ScmInvMaterialReqBillEntry2> scmInvMaterialReqBillEntryList = scmInvMaterialReqBillEntryBiz.queryPage(page,
				arglist, "findAllPage", param);
		if (scmInvMaterialReqBillEntryList != null && !scmInvMaterialReqBillEntryList.isEmpty()) {
			for (ScmInvMaterialReqBillEntry2 scmInvMaterialReqBillEntry : scmInvMaterialReqBillEntryList) {
				BigDecimal sideFlag = BigDecimal.ONE;
				if (StringUtils.equals("2", scmInvMaterialReqBillEntry.getBizType())) {
					sideFlag = new BigDecimal("-1"); // 领料退库
				}
				if (scmInvMaterialReqBillEntry.getBizDate().compareTo(FormatUtils.parseDate(beginDate)) < 0) {
					// 比查询日期小的统计到期初数据中
					boolean exists = false;
					if (!scmInvItemInAndOutSumList.isEmpty()) {
						for (ScmInvItemInAndOutSum existScmInvItemInAndOutSum : scmInvItemInAndOutSumList) {
							if (existScmInvItemInAndOutSum.getItemId() == scmInvMaterialReqBillEntry.getItemId()
									&& StringUtils.equals(existScmInvItemInAndOutSum.getOrgUnitNo(),
											scmInvMaterialReqBillEntry.getOrgUnitNo())) {
								if (StringUtils.equals("W", groupBy)||StringUtils.equals("M", groupBy)) {
									if (existScmInvItemInAndOutSum.getWareHouseId() == scmInvMaterialReqBillEntry
											.getWareHouseId()) {
										existScmInvItemInAndOutSum.setInitQty(existScmInvItemInAndOutSum.getInitQty()
												.subtract(sideFlag.multiply(scmInvMaterialReqBillEntry.getQty())));
										existScmInvItemInAndOutSum.setInitAmt(existScmInvItemInAndOutSum.getInitAmt()
												.subtract(sideFlag.multiply(scmInvMaterialReqBillEntry.getAmt())));
										existScmInvItemInAndOutSum.setInitTax(existScmInvItemInAndOutSum.getInitTax()
												.subtract(sideFlag.multiply(scmInvMaterialReqBillEntry.getTaxAmt()
														.subtract(scmInvMaterialReqBillEntry.getAmt()))));
										existScmInvItemInAndOutSum
												.setInitTaxAmt(existScmInvItemInAndOutSum.getInitTaxAmt().subtract(
														sideFlag.multiply(scmInvMaterialReqBillEntry.getTaxAmt())));
										exists = true;
										break;
									}
								} else {
									if (StringUtils.equals(existScmInvItemInAndOutSum.getWareHouseName(),
											scmInvMaterialReqBillEntry.getLot())) {
										existScmInvItemInAndOutSum.setInitQty(existScmInvItemInAndOutSum.getInitQty()
												.subtract(sideFlag.multiply(scmInvMaterialReqBillEntry.getQty())));
										existScmInvItemInAndOutSum.setInitAmt(existScmInvItemInAndOutSum.getInitAmt()
												.subtract(sideFlag.multiply(scmInvMaterialReqBillEntry.getAmt())));
										existScmInvItemInAndOutSum.setInitTax(existScmInvItemInAndOutSum.getInitTax()
												.subtract(sideFlag.multiply(scmInvMaterialReqBillEntry.getTaxAmt()
														.subtract(scmInvMaterialReqBillEntry.getAmt()))));
										existScmInvItemInAndOutSum
												.setInitTaxAmt(existScmInvItemInAndOutSum.getInitTaxAmt().subtract(
														sideFlag.multiply(scmInvMaterialReqBillEntry.getTaxAmt())));
										exists = true;
										break;
									}
								}
							}
						}
					}
					if (!exists) {
						ScmInvItemInAndOutSum scmInvItemInAndOutSum = new ScmInvItemInAndOutSum(true);
						scmInvItemInAndOutSum.setItemId(scmInvMaterialReqBillEntry.getItemId());
						scmInvItemInAndOutSum.setWareHouseName(scmInvMaterialReqBillEntry.getWhName());
						scmInvItemInAndOutSum.setUnitName(scmInvMaterialReqBillEntry.getUnitName());
						scmInvItemInAndOutSum.setItemNo(scmInvMaterialReqBillEntry.getItemNo());
						scmInvItemInAndOutSum.setItemName(scmInvMaterialReqBillEntry.getItemName());
						scmInvItemInAndOutSum.setSpec(scmInvMaterialReqBillEntry.getSpec());
						scmInvItemInAndOutSum.setClassName(scmInvMaterialReqBillEntry.getGroupName());
						scmInvItemInAndOutSum.setUnit(scmInvMaterialReqBillEntry.getUnit());
						scmInvItemInAndOutSum.setOrgUnitNo(scmInvMaterialReqBillEntry.getOrgUnitNo());
						if (StringUtils.equals("W", groupBy)||StringUtils.equals("M", groupBy)) {
							scmInvItemInAndOutSum.setWareHouseId(scmInvMaterialReqBillEntry.getWareHouseId());
						} else {
							scmInvItemInAndOutSum.setWareHouseName(scmInvMaterialReqBillEntry.getLot());
						}
						scmInvItemInAndOutSum.setInitQty(
								BigDecimal.ZERO.subtract(sideFlag.multiply(scmInvMaterialReqBillEntry.getQty())));
						scmInvItemInAndOutSum.setInitAmt(
								BigDecimal.ZERO.subtract(sideFlag.multiply(scmInvMaterialReqBillEntry.getAmt())));
						scmInvItemInAndOutSum.setInitTax(BigDecimal.ZERO.subtract(sideFlag.multiply(
								scmInvMaterialReqBillEntry.getTaxAmt().subtract(scmInvMaterialReqBillEntry.getAmt()))));
						scmInvItemInAndOutSum.setInitTaxAmt(
								BigDecimal.ZERO.subtract(sideFlag.multiply(scmInvMaterialReqBillEntry.getTaxAmt())));
						scmInvItemInAndOutSumList.add(scmInvItemInAndOutSum);
					}
				} else {
					boolean exists = false;
					if (!scmInvItemInAndOutSumList.isEmpty()) {
						for (ScmInvItemInAndOutSum existScmInvItemInAndOutSum : scmInvItemInAndOutSumList) {
							if (existScmInvItemInAndOutSum.getItemId() == scmInvMaterialReqBillEntry.getItemId()
									&& StringUtils.equals(existScmInvItemInAndOutSum.getOrgUnitNo(),
											scmInvMaterialReqBillEntry.getOrgUnitNo())) {
								if (StringUtils.equals("W", groupBy)||StringUtils.equals("M", groupBy)) {
									if (existScmInvItemInAndOutSum.getWareHouseId() == scmInvMaterialReqBillEntry
											.getWareHouseId()) {
										existScmInvItemInAndOutSum.setReqQty(existScmInvItemInAndOutSum.getReqQty()
												.add(sideFlag.multiply(scmInvMaterialReqBillEntry.getQty())));
										existScmInvItemInAndOutSum.setReqAmt(existScmInvItemInAndOutSum.getReqAmt()
												.add(sideFlag.multiply(scmInvMaterialReqBillEntry.getAmt())));
										existScmInvItemInAndOutSum.setReqTax(existScmInvItemInAndOutSum.getReqTax()
												.add(sideFlag.multiply(scmInvMaterialReqBillEntry.getTaxAmt()
														.subtract(scmInvMaterialReqBillEntry.getAmt()))));
										existScmInvItemInAndOutSum
												.setReqTaxAmt(existScmInvItemInAndOutSum.getReqTaxAmt().add(
														sideFlag.multiply(scmInvMaterialReqBillEntry.getTaxAmt())));
										exists = true;
										break;
									}
								} else {
									if (StringUtils.equals(existScmInvItemInAndOutSum.getWareHouseName(),
											scmInvMaterialReqBillEntry.getLot())) {
										existScmInvItemInAndOutSum.setReqQty(existScmInvItemInAndOutSum.getReqQty()
												.add(sideFlag.multiply(scmInvMaterialReqBillEntry.getQty())));
										existScmInvItemInAndOutSum.setReqAmt(existScmInvItemInAndOutSum.getReqAmt()
												.add(sideFlag.multiply(scmInvMaterialReqBillEntry.getAmt())));
										existScmInvItemInAndOutSum.setReqTax(existScmInvItemInAndOutSum.getReqTax()
												.add(sideFlag.multiply(scmInvMaterialReqBillEntry.getTaxAmt()
														.subtract(scmInvMaterialReqBillEntry.getAmt()))));
										existScmInvItemInAndOutSum
												.setReqTaxAmt(existScmInvItemInAndOutSum.getReqTaxAmt().add(
														sideFlag.multiply(scmInvMaterialReqBillEntry.getTaxAmt())));
										exists = true;
										break;
									}
								}
							}
						}
					}
					if (!exists) {
						// 放入明细记录结果集中
						ScmInvItemInAndOutSum scmInvItemInAndOutSum = new ScmInvItemInAndOutSum(true);
						scmInvItemInAndOutSum.setItemId(scmInvMaterialReqBillEntry.getItemId());
						scmInvItemInAndOutSum.setWareHouseName(scmInvMaterialReqBillEntry.getWhName());
						scmInvItemInAndOutSum.setUnitName(scmInvMaterialReqBillEntry.getUnitName());
						scmInvItemInAndOutSum.setItemNo(scmInvMaterialReqBillEntry.getItemNo());
						scmInvItemInAndOutSum.setItemName(scmInvMaterialReqBillEntry.getItemName());
						scmInvItemInAndOutSum.setSpec(scmInvMaterialReqBillEntry.getSpec());
						scmInvItemInAndOutSum.setClassName(scmInvMaterialReqBillEntry.getGroupName());
						scmInvItemInAndOutSum.setUnit(scmInvMaterialReqBillEntry.getUnit());
						scmInvItemInAndOutSum.setOrgUnitNo(scmInvMaterialReqBillEntry.getOrgUnitNo());
						if (StringUtils.equals("W", groupBy)||StringUtils.equals("M", groupBy)) {
							scmInvItemInAndOutSum.setWareHouseId(scmInvMaterialReqBillEntry.getWareHouseId());
						} else {
							scmInvItemInAndOutSum.setWareHouseName(scmInvMaterialReqBillEntry.getLot());
						}
						scmInvItemInAndOutSum.setReqQty(sideFlag.multiply(scmInvMaterialReqBillEntry.getQty()));
						scmInvItemInAndOutSum.setReqAmt(sideFlag.multiply(scmInvMaterialReqBillEntry.getAmt()));
						scmInvItemInAndOutSum.setReqTax(sideFlag.multiply(
								scmInvMaterialReqBillEntry.getTaxAmt().subtract(scmInvMaterialReqBillEntry.getAmt())));
						scmInvItemInAndOutSum.setReqTaxAmt(sideFlag.multiply(scmInvMaterialReqBillEntry.getTaxAmt()));
						scmInvItemInAndOutSumList.add(scmInvItemInAndOutSum);
					}
				}
			}
		}
		// 销售出库
		page = new Page();
		page.setModelClass(ScmInvSaleIssueBillEntry2.class);
		page.setShowCount(Integer.MAX_VALUE);
		if (StringUtils.isNotBlank(sbMaterilaClass.toString())) {
			page.getParam().put(
					ClassUtils.getFinalModelSimpleName(ScmMaterialGroup.class) + "." + ScmMaterialGroup.FN_ID,
					new QueryParam(
							ClassUtils.getFinalModelSimpleName(ScmMaterialGroup.class) + "." + ScmMaterialGroup.FN_ID,
							QueryParam.QUERY_IN, sbMaterilaClass.toString()));
		}
		if (StringUtils.isNotBlank(sbMaterila.toString())) {
			page.getParam()
					.put(ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBillEntry2.class) + "."
							+ ScmInvSaleIssueBillEntry2.FN_ITEMID,
							new QueryParam(
									ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBillEntry2.class) + "."
											+ ScmInvSaleIssueBillEntry2.FN_ITEMID,
									QueryParam.QUERY_IN, sbMaterila.toString()));
		}
		page.getParam()
				.put(ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBillEntry2.class) + "."
						+ ScmInvSaleIssueBillEntry2.FN_ORGUNITNO,
						new QueryParam(
								ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBillEntry2.class) + "."
										+ ScmInvSaleIssueBillEntry2.FN_ORGUNITNO,
								QueryParam.QUERY_IN, orgUnitNos.toString()));
		if (StringUtils.isNotBlank(sbWh.toString())) {
			page.getParam()
					.put(ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBillEntry2.class) + "."
							+ ScmInvSaleIssueBillEntry2.FN_WAREHOUSEID,
							new QueryParam(
									ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBillEntry2.class) + "."
											+ ScmInvSaleIssueBillEntry2.FN_WAREHOUSEID,
									QueryParam.QUERY_IN, sbWh.toString()));
		} else {
			page.getParam()
					.put(ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBillEntry2.class) + "."
							+ ScmInvSaleIssueBillEntry2.FN_WAREHOUSEID,
							new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBillEntry2.class) + "."
									+ ScmInvSaleIssueBillEntry2.FN_WAREHOUSEID, QueryParam.QUERY_GT, "0"));
		}
		page.getParam().put(
				ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBill.class) + "." + ScmInvSaleIssueBill.FN_BIZDATE,
				new QueryParam(
						ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBill.class) + "."
								+ ScmInvSaleIssueBill.FN_BIZDATE,
						QueryParam.QUERY_BETWEEN, FormatUtils.fmtDate(periodCalendar.getStartDate()), endDate));
		if (!StringUtils.equals("Y", status)) {
			page.getParam().put(
					ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBill.class) + "." + ScmInvSaleIssueBill.FN_STATUS,
					new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBill.class) + "."
							+ ScmInvSaleIssueBill.FN_STATUS, QueryParam.QUERY_EQ, "E"));
		}
		List<ScmInvSaleIssueBillEntry2> scmInvSaleIssueBillEntryList = scmInvSaleIssueBillEntryBiz.queryPage(page,
				arglist, "findAllPage", param);
		if (scmInvSaleIssueBillEntryList != null && !scmInvSaleIssueBillEntryList.isEmpty()) {
			for (ScmInvSaleIssueBillEntry2 scmInvSaleIssueBillEntry : scmInvSaleIssueBillEntryList) {
				BigDecimal sideFlag = BigDecimal.ONE;
				if (StringUtils.contains("6,7,8", scmInvSaleIssueBillEntry.getBizType())) {
					sideFlag = new BigDecimal("-1");
				}
				if (scmInvSaleIssueBillEntry.getBizDate().compareTo(FormatUtils.parseDate(beginDate)) < 0) {
					// 比查询日期小的统计到期初数据中
					boolean exists = false;
					if (!scmInvItemInAndOutSumList.isEmpty()) {
						for (ScmInvItemInAndOutSum existScmInvItemInAndOutSum : scmInvItemInAndOutSumList) {
							if (existScmInvItemInAndOutSum.getItemId() == scmInvSaleIssueBillEntry.getItemId()
									&& StringUtils.equals(existScmInvItemInAndOutSum.getOrgUnitNo(),
											scmInvSaleIssueBillEntry.getOrgUnitNo())) {
								if (StringUtils.equals("W", groupBy)||StringUtils.equals("M", groupBy)) {
									if (existScmInvItemInAndOutSum.getWareHouseId() == scmInvSaleIssueBillEntry
											.getWareHouseId()) {
										existScmInvItemInAndOutSum.setInitQty(existScmInvItemInAndOutSum.getInitQty()
												.subtract(sideFlag.multiply(scmInvSaleIssueBillEntry.getQty())));
										existScmInvItemInAndOutSum.setInitAmt(existScmInvItemInAndOutSum.getInitAmt()
												.subtract(sideFlag.multiply(scmInvSaleIssueBillEntry.getAmt())));
										existScmInvItemInAndOutSum.setInitTax(existScmInvItemInAndOutSum.getInitTax()
												.subtract(sideFlag.multiply(scmInvSaleIssueBillEntry.getTaxAmt()
														.subtract(scmInvSaleIssueBillEntry.getAmt()))));
										existScmInvItemInAndOutSum
												.setInitTaxAmt(existScmInvItemInAndOutSum.getInitTaxAmt().subtract(
														sideFlag.multiply(scmInvSaleIssueBillEntry.getTaxAmt())));
										exists = true;
										break;
									}
								} else {
									if (StringUtils.equals(existScmInvItemInAndOutSum.getWareHouseName(),
											scmInvSaleIssueBillEntry.getLot())) {
										existScmInvItemInAndOutSum.setInitQty(existScmInvItemInAndOutSum.getInitQty()
												.subtract(sideFlag.multiply(scmInvSaleIssueBillEntry.getQty())));
										existScmInvItemInAndOutSum.setInitAmt(existScmInvItemInAndOutSum.getInitAmt()
												.subtract(sideFlag.multiply(scmInvSaleIssueBillEntry.getAmt())));
										existScmInvItemInAndOutSum.setInitTax(existScmInvItemInAndOutSum.getInitTax()
												.subtract(sideFlag.multiply(scmInvSaleIssueBillEntry.getTaxAmt()
														.subtract(scmInvSaleIssueBillEntry.getAmt()))));
										existScmInvItemInAndOutSum
												.setInitTaxAmt(existScmInvItemInAndOutSum.getInitTaxAmt().subtract(
														sideFlag.multiply(scmInvSaleIssueBillEntry.getTaxAmt())));
										exists = true;
										break;
									}
								}
							}
						}
					}
					if (!exists) {
						ScmInvItemInAndOutSum scmInvItemInAndOutSum = new ScmInvItemInAndOutSum(true);
						scmInvItemInAndOutSum.setItemId(scmInvSaleIssueBillEntry.getItemId());
						scmInvItemInAndOutSum.setWareHouseName(scmInvSaleIssueBillEntry.getWhName());
						scmInvItemInAndOutSum.setUnitName(scmInvSaleIssueBillEntry.getUnitName());
						scmInvItemInAndOutSum.setItemNo(scmInvSaleIssueBillEntry.getItemNo());
						scmInvItemInAndOutSum.setItemName(scmInvSaleIssueBillEntry.getItemName());
						scmInvItemInAndOutSum.setSpec(scmInvSaleIssueBillEntry.getSpec());
						scmInvItemInAndOutSum.setClassName(scmInvSaleIssueBillEntry.getGroupName());
						scmInvItemInAndOutSum.setUnit(scmInvSaleIssueBillEntry.getUnit());
						scmInvItemInAndOutSum.setOrgUnitNo(scmInvSaleIssueBillEntry.getOrgUnitNo());
						if (StringUtils.equals("W", groupBy)||StringUtils.equals("M", groupBy)) {
							scmInvItemInAndOutSum.setWareHouseId(scmInvSaleIssueBillEntry.getWareHouseId());
						} else {
							scmInvItemInAndOutSum.setWareHouseName(scmInvSaleIssueBillEntry.getLot());
						}
						scmInvItemInAndOutSum.setInitQty(
								BigDecimal.ZERO.subtract(sideFlag.multiply(scmInvSaleIssueBillEntry.getQty())));
						scmInvItemInAndOutSum.setInitAmt(
								BigDecimal.ZERO.subtract(sideFlag.multiply(scmInvSaleIssueBillEntry.getAmt())));
						scmInvItemInAndOutSum.setInitTax(BigDecimal.ZERO.subtract(sideFlag.multiply(
								scmInvSaleIssueBillEntry.getTaxAmt().subtract(scmInvSaleIssueBillEntry.getAmt()))));
						scmInvItemInAndOutSum.setInitTaxAmt(
								BigDecimal.ZERO.subtract(sideFlag.multiply(scmInvSaleIssueBillEntry.getTaxAmt())));
						scmInvItemInAndOutSumList.add(scmInvItemInAndOutSum);
					}
				} else {
					boolean exists = false;
					if (!scmInvItemInAndOutSumList.isEmpty()) {
						for (ScmInvItemInAndOutSum existScmInvItemInAndOutSum : scmInvItemInAndOutSumList) {
							if (existScmInvItemInAndOutSum.getItemId() == scmInvSaleIssueBillEntry.getItemId()
									&& StringUtils.equals(existScmInvItemInAndOutSum.getOrgUnitNo(),
											scmInvSaleIssueBillEntry.getOrgUnitNo())) {
								if (StringUtils.equals("W", groupBy)||StringUtils.equals("M", groupBy)) {
									if (existScmInvItemInAndOutSum.getWareHouseId() == scmInvSaleIssueBillEntry
											.getWareHouseId()) {
										existScmInvItemInAndOutSum.setOutQty(existScmInvItemInAndOutSum.getOutQty()
												.add(sideFlag.multiply(scmInvSaleIssueBillEntry.getQty())));
										existScmInvItemInAndOutSum.setOutAmt(existScmInvItemInAndOutSum.getOutAmt()
												.add(sideFlag.multiply(scmInvSaleIssueBillEntry.getAmt())));
										existScmInvItemInAndOutSum.setOutTax(existScmInvItemInAndOutSum.getOutTax()
												.add(sideFlag.multiply(scmInvSaleIssueBillEntry.getTaxAmt()
														.subtract(scmInvSaleIssueBillEntry.getAmt()))));
										existScmInvItemInAndOutSum
												.setOutTaxAmt(existScmInvItemInAndOutSum.getOutTaxAmt()
														.add(sideFlag.multiply(scmInvSaleIssueBillEntry.getTaxAmt())));
										exists = true;
										break;
									}
								} else {
									if (StringUtils.equals(existScmInvItemInAndOutSum.getWareHouseName(),
											scmInvSaleIssueBillEntry.getLot())) {
										existScmInvItemInAndOutSum.setOutQty(existScmInvItemInAndOutSum.getOutQty()
												.add(sideFlag.multiply(scmInvSaleIssueBillEntry.getQty())));
										existScmInvItemInAndOutSum.setOutAmt(existScmInvItemInAndOutSum.getOutAmt()
												.add(sideFlag.multiply(scmInvSaleIssueBillEntry.getAmt())));
										existScmInvItemInAndOutSum.setOutTax(existScmInvItemInAndOutSum.getOutTax()
												.add(sideFlag.multiply(scmInvSaleIssueBillEntry.getTaxAmt()
														.subtract(scmInvSaleIssueBillEntry.getAmt()))));
										existScmInvItemInAndOutSum
												.setOutTaxAmt(existScmInvItemInAndOutSum.getOutTaxAmt()
														.add(sideFlag.multiply(scmInvSaleIssueBillEntry.getTaxAmt())));
										exists = true;
										break;
									}
								}
							}
						}
					}
					if (!exists) {
						// 放入明细记录结果集中
						ScmInvItemInAndOutSum scmInvItemInAndOutSum = new ScmInvItemInAndOutSum(true);
						scmInvItemInAndOutSum.setItemId(scmInvSaleIssueBillEntry.getItemId());
						scmInvItemInAndOutSum.setWareHouseName(scmInvSaleIssueBillEntry.getWhName());
						scmInvItemInAndOutSum.setUnitName(scmInvSaleIssueBillEntry.getUnitName());
						scmInvItemInAndOutSum.setItemNo(scmInvSaleIssueBillEntry.getItemNo());
						scmInvItemInAndOutSum.setItemName(scmInvSaleIssueBillEntry.getItemName());
						scmInvItemInAndOutSum.setSpec(scmInvSaleIssueBillEntry.getSpec());
						scmInvItemInAndOutSum.setClassName(scmInvSaleIssueBillEntry.getGroupName());
						scmInvItemInAndOutSum.setUnit(scmInvSaleIssueBillEntry.getUnit());
						scmInvItemInAndOutSum.setOrgUnitNo(scmInvSaleIssueBillEntry.getOrgUnitNo());
						if (StringUtils.equals("W", groupBy)||StringUtils.equals("M", groupBy)) {
							scmInvItemInAndOutSum.setWareHouseId(scmInvSaleIssueBillEntry.getWareHouseId());
						} else {
							scmInvItemInAndOutSum.setWareHouseName(scmInvSaleIssueBillEntry.getLot());
						}
						scmInvItemInAndOutSum.setOutQty(sideFlag.multiply(scmInvSaleIssueBillEntry.getQty()));
						scmInvItemInAndOutSum.setOutAmt(sideFlag.multiply(scmInvSaleIssueBillEntry.getAmt()));
						scmInvItemInAndOutSum.setOutTax(sideFlag.multiply(
								scmInvSaleIssueBillEntry.getTaxAmt().subtract(scmInvSaleIssueBillEntry.getAmt())));
						scmInvItemInAndOutSum.setOutTaxAmt(sideFlag.multiply(scmInvSaleIssueBillEntry.getTaxAmt()));
						scmInvItemInAndOutSumList.add(scmInvItemInAndOutSum);
					}
				}
			}
		}
		// 其他出库
		page = new Page();
		page.setModelClass(ScmInvOtherIssueBillEntry2.class);
		page.setShowCount(Integer.MAX_VALUE);
		if (StringUtils.isNotBlank(sbMaterilaClass.toString())) {
			page.getParam().put(
					ClassUtils.getFinalModelSimpleName(ScmMaterialGroup.class) + "." + ScmMaterialGroup.FN_ID,
					new QueryParam(
							ClassUtils.getFinalModelSimpleName(ScmMaterialGroup.class) + "." + ScmMaterialGroup.FN_ID,
							QueryParam.QUERY_IN, sbMaterilaClass.toString()));
		}
		if (StringUtils.isNotBlank(sbMaterila.toString())) {
			page.getParam()
					.put(ClassUtils.getFinalModelSimpleName(ScmInvOtherIssueBillEntry2.class) + "."
							+ ScmInvOtherIssueBillEntry2.FN_ITEMID,
							new QueryParam(
									ClassUtils.getFinalModelSimpleName(ScmInvOtherIssueBillEntry2.class) + "."
											+ ScmInvOtherIssueBillEntry2.FN_ITEMID,
									QueryParam.QUERY_IN, sbMaterila.toString()));
		}
		page.getParam()
				.put(ClassUtils.getFinalModelSimpleName(ScmInvOtherIssueBillEntry2.class) + "."
						+ ScmInvOtherIssueBillEntry2.FN_ORGUNITNO,
						new QueryParam(
								ClassUtils.getFinalModelSimpleName(ScmInvOtherIssueBillEntry2.class) + "."
										+ ScmInvOtherIssueBillEntry2.FN_ORGUNITNO,
								QueryParam.QUERY_IN, orgUnitNos.toString()));
		if (StringUtils.isNotBlank(sbWh.toString())) {
			page.getParam()
					.put(ClassUtils.getFinalModelSimpleName(ScmInvOtherIssueBillEntry2.class) + "."
							+ ScmInvOtherIssueBillEntry2.FN_WAREHOUSEID,
							new QueryParam(
									ClassUtils.getFinalModelSimpleName(ScmInvOtherIssueBillEntry2.class) + "."
											+ ScmInvOtherIssueBillEntry2.FN_WAREHOUSEID,
									QueryParam.QUERY_IN, sbWh.toString()));
		}
		page.getParam().put(
				ClassUtils.getFinalModelSimpleName(ScmInvOtherIssueBill.class) + "." + ScmInvOtherIssueBill.FN_BIZDATE,
				new QueryParam(
						ClassUtils.getFinalModelSimpleName(ScmInvOtherIssueBill.class) + "."
								+ ScmInvOtherIssueBill.FN_BIZDATE,
						QueryParam.QUERY_BETWEEN, FormatUtils.fmtDate(periodCalendar.getStartDate()), endDate));
		if (!StringUtils.equals("Y", status)) {
			page.getParam()
					.put(ClassUtils.getFinalModelSimpleName(ScmInvOtherIssueBill.class) + "."
							+ ScmInvOtherIssueBill.FN_STATUS,
							new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvOtherIssueBill.class) + "."
									+ ScmInvOtherIssueBill.FN_STATUS, QueryParam.QUERY_EQ, "E"));
		}
		List<ScmInvOtherIssueBillEntry2> scmInvOtherIssueBillEntryList = scmInvOtherIssueBillEntryBiz.queryPage(page,
				arglist, "findAllPage", param);
		if (scmInvOtherIssueBillEntryList != null && !scmInvOtherIssueBillEntryList.isEmpty()) {
			for (ScmInvOtherIssueBillEntry2 scmInvOtherIssueBillEntry : scmInvOtherIssueBillEntryList) {
				if (scmInvOtherIssueBillEntry.getBizDate().compareTo(FormatUtils.parseDate(beginDate)) < 0) {
					// 比查询日期小的统计到期初数据中
					boolean exists = false;
					if (!scmInvItemInAndOutSumList.isEmpty()) {
						for (ScmInvItemInAndOutSum existScmInvItemInAndOutSum : scmInvItemInAndOutSumList) {
							if (existScmInvItemInAndOutSum.getItemId() == scmInvOtherIssueBillEntry.getItemId()
									&& StringUtils.equals(existScmInvItemInAndOutSum.getOrgUnitNo(),
											scmInvOtherIssueBillEntry.getOrgUnitNo())) {
								if (StringUtils.equals("W", groupBy)||StringUtils.equals("M", groupBy)) {
									if (existScmInvItemInAndOutSum.getWareHouseId() == scmInvOtherIssueBillEntry
											.getWareHouseId()) {
										existScmInvItemInAndOutSum.setInitQty(existScmInvItemInAndOutSum.getInitQty()
												.subtract(scmInvOtherIssueBillEntry.getQty()));
										existScmInvItemInAndOutSum.setInitAmt(existScmInvItemInAndOutSum.getInitAmt()
												.subtract(scmInvOtherIssueBillEntry.getAmt()));
										existScmInvItemInAndOutSum.setInitTax(existScmInvItemInAndOutSum.getInitTax()
												.subtract(scmInvOtherIssueBillEntry.getTaxAmt()
														.subtract(scmInvOtherIssueBillEntry.getAmt())));
										existScmInvItemInAndOutSum.setInitTaxAmt(existScmInvItemInAndOutSum
												.getInitTaxAmt().subtract(scmInvOtherIssueBillEntry.getTaxAmt()));
										exists = true;
										break;
									}
								} else {
									if (StringUtils.equals(existScmInvItemInAndOutSum.getWareHouseName(),
											scmInvOtherIssueBillEntry.getLot())) {
										existScmInvItemInAndOutSum.setInitQty(existScmInvItemInAndOutSum.getInitQty()
												.subtract(scmInvOtherIssueBillEntry.getQty()));
										existScmInvItemInAndOutSum.setInitAmt(existScmInvItemInAndOutSum.getInitAmt()
												.subtract(scmInvOtherIssueBillEntry.getAmt()));
										existScmInvItemInAndOutSum.setInitTax(existScmInvItemInAndOutSum.getInitTax()
												.subtract(scmInvOtherIssueBillEntry.getTaxAmt()
														.subtract(scmInvOtherIssueBillEntry.getAmt())));
										existScmInvItemInAndOutSum.setInitTaxAmt(existScmInvItemInAndOutSum
												.getInitTaxAmt().subtract(scmInvOtherIssueBillEntry.getTaxAmt()));
										exists = true;
										break;
									}
								}
							}
						}
					}
					if (!exists) {
						ScmInvItemInAndOutSum scmInvItemInAndOutSum = new ScmInvItemInAndOutSum(true);
						scmInvItemInAndOutSum.setItemId(scmInvOtherIssueBillEntry.getItemId());
						scmInvItemInAndOutSum.setWareHouseName(scmInvOtherIssueBillEntry.getWhName());
						scmInvItemInAndOutSum.setUnitName(scmInvOtherIssueBillEntry.getUnitName());
						scmInvItemInAndOutSum.setItemNo(scmInvOtherIssueBillEntry.getItemNo());
						scmInvItemInAndOutSum.setItemName(scmInvOtherIssueBillEntry.getItemName());
						scmInvItemInAndOutSum.setSpec(scmInvOtherIssueBillEntry.getSpec());
						scmInvItemInAndOutSum.setClassName(scmInvOtherIssueBillEntry.getGroupName());
						scmInvItemInAndOutSum.setUnit(scmInvOtherIssueBillEntry.getUnit());
						scmInvItemInAndOutSum.setOrgUnitNo(scmInvOtherIssueBillEntry.getOrgUnitNo());
						if (StringUtils.equals("W", groupBy)||StringUtils.equals("M", groupBy)) {
							scmInvItemInAndOutSum.setWareHouseId(scmInvOtherIssueBillEntry.getWareHouseId());
						} else {
							scmInvItemInAndOutSum.setWareHouseName(scmInvOtherIssueBillEntry.getLot());
						}
						scmInvItemInAndOutSum.setInitQty(BigDecimal.ZERO.subtract(scmInvOtherIssueBillEntry.getQty()));
						scmInvItemInAndOutSum.setInitAmt(BigDecimal.ZERO.subtract(scmInvOtherIssueBillEntry.getAmt()));
						scmInvItemInAndOutSum.setInitTax(BigDecimal.ZERO.subtract(
								scmInvOtherIssueBillEntry.getTaxAmt().subtract(scmInvOtherIssueBillEntry.getAmt())));
						scmInvItemInAndOutSum
								.setInitTaxAmt(BigDecimal.ZERO.subtract(scmInvOtherIssueBillEntry.getTaxAmt()));
						scmInvItemInAndOutSumList.add(scmInvItemInAndOutSum);
					}
				} else {
					boolean exists = false;
					if (!scmInvItemInAndOutSumList.isEmpty()) {
						for (ScmInvItemInAndOutSum existScmInvItemInAndOutSum : scmInvItemInAndOutSumList) {
							if (existScmInvItemInAndOutSum.getItemId() == scmInvOtherIssueBillEntry.getItemId()
									&& StringUtils.equals(existScmInvItemInAndOutSum.getOrgUnitNo(),
											scmInvOtherIssueBillEntry.getOrgUnitNo())) {
								if (StringUtils.equals("W", groupBy)||StringUtils.equals("M", groupBy)) {
									if (existScmInvItemInAndOutSum.getWareHouseId() == scmInvOtherIssueBillEntry
											.getWareHouseId()) {
										if (StringUtils.equals("1", scmInvOtherIssueBillEntry.getBizType())) {
											existScmInvItemInAndOutSum.setProfitQty(existScmInvItemInAndOutSum
													.getProfitQty().subtract(scmInvOtherIssueBillEntry.getQty()));
											existScmInvItemInAndOutSum.setProfitAmt(existScmInvItemInAndOutSum
													.getProfitAmt().subtract(scmInvOtherIssueBillEntry.getAmt()));
											existScmInvItemInAndOutSum.setProfitTax(existScmInvItemInAndOutSum
													.getProfitTax().subtract(scmInvOtherIssueBillEntry.getTaxAmt()
															.subtract(scmInvOtherIssueBillEntry.getAmt())));
											existScmInvItemInAndOutSum.setProfitTaxAmt(existScmInvItemInAndOutSum
													.getProfitTaxAmt().subtract(scmInvOtherIssueBillEntry.getTaxAmt()));
										} else if (StringUtils.equals("2", scmInvOtherIssueBillEntry.getBizType())) {
											existScmInvItemInAndOutSum.setScrapQty(existScmInvItemInAndOutSum
													.getScrapQty().add(scmInvOtherIssueBillEntry.getQty()));
											existScmInvItemInAndOutSum.setScrapAmt(existScmInvItemInAndOutSum
													.getScrapAmt().add(scmInvOtherIssueBillEntry.getAmt()));
											existScmInvItemInAndOutSum.setScrapTax(existScmInvItemInAndOutSum
													.getScrapTax().add(scmInvOtherIssueBillEntry.getTaxAmt()
															.subtract(scmInvOtherIssueBillEntry.getAmt())));
											existScmInvItemInAndOutSum.setScrapTaxAmt(existScmInvItemInAndOutSum
													.getScrapTaxAmt().add(scmInvOtherIssueBillEntry.getTaxAmt()));
										} else {
											existScmInvItemInAndOutSum.setOutQty(existScmInvItemInAndOutSum.getOutQty()
													.add(scmInvOtherIssueBillEntry.getQty()));
											existScmInvItemInAndOutSum.setOutAmt(existScmInvItemInAndOutSum.getOutAmt()
													.add(scmInvOtherIssueBillEntry.getAmt()));
											existScmInvItemInAndOutSum.setOutTax(
													existScmInvItemInAndOutSum.getOutTax().add(scmInvOtherIssueBillEntry
															.getTaxAmt().subtract(scmInvOtherIssueBillEntry.getAmt())));
											existScmInvItemInAndOutSum.setOutTaxAmt(existScmInvItemInAndOutSum
													.getOutTaxAmt().add(scmInvOtherIssueBillEntry.getTaxAmt()));
										}
										exists = true;
										break;
									}
								} else {
									if (StringUtils.equals(existScmInvItemInAndOutSum.getWareHouseName(),
											scmInvOtherIssueBillEntry.getLot())) {
										if (StringUtils.equals("1", scmInvOtherIssueBillEntry.getBizType())) {
											existScmInvItemInAndOutSum.setProfitQty(existScmInvItemInAndOutSum
													.getProfitQty().subtract(scmInvOtherIssueBillEntry.getQty()));
											existScmInvItemInAndOutSum.setProfitAmt(existScmInvItemInAndOutSum
													.getProfitAmt().subtract(scmInvOtherIssueBillEntry.getAmt()));
											existScmInvItemInAndOutSum.setProfitTax(existScmInvItemInAndOutSum
													.getProfitTax().subtract(scmInvOtherIssueBillEntry.getTaxAmt()
															.subtract(scmInvOtherIssueBillEntry.getAmt())));
											existScmInvItemInAndOutSum.setProfitTaxAmt(existScmInvItemInAndOutSum
													.getProfitTaxAmt().subtract(scmInvOtherIssueBillEntry.getTaxAmt()));
										} else if (StringUtils.equals("2", scmInvOtherIssueBillEntry.getBizType())) {
											existScmInvItemInAndOutSum.setScrapQty(existScmInvItemInAndOutSum
													.getScrapQty().add(scmInvOtherIssueBillEntry.getQty()));
											existScmInvItemInAndOutSum.setScrapAmt(existScmInvItemInAndOutSum
													.getScrapAmt().add(scmInvOtherIssueBillEntry.getAmt()));
											existScmInvItemInAndOutSum.setScrapTax(existScmInvItemInAndOutSum
													.getScrapTax().add(scmInvOtherIssueBillEntry.getTaxAmt()
															.subtract(scmInvOtherIssueBillEntry.getAmt())));
											existScmInvItemInAndOutSum.setScrapTaxAmt(existScmInvItemInAndOutSum
													.getScrapTaxAmt().add(scmInvOtherIssueBillEntry.getTaxAmt()));
										} else {
											existScmInvItemInAndOutSum.setOutQty(existScmInvItemInAndOutSum.getOutQty()
													.add(scmInvOtherIssueBillEntry.getQty()));
											existScmInvItemInAndOutSum.setOutAmt(existScmInvItemInAndOutSum.getOutAmt()
													.add(scmInvOtherIssueBillEntry.getAmt()));
											existScmInvItemInAndOutSum.setOutTax(
													existScmInvItemInAndOutSum.getOutTax().add(scmInvOtherIssueBillEntry
															.getTaxAmt().subtract(scmInvOtherIssueBillEntry.getAmt())));
											existScmInvItemInAndOutSum.setOutTaxAmt(existScmInvItemInAndOutSum
													.getOutTaxAmt().add(scmInvOtherIssueBillEntry.getTaxAmt()));
										}
										exists = true;
										break;
									}
								}
							}
						}
					}
					if (!exists) {
						// 放入明细记录结果集中
						ScmInvItemInAndOutSum scmInvItemInAndOutSum = new ScmInvItemInAndOutSum(true);
						scmInvItemInAndOutSum.setItemId(scmInvOtherIssueBillEntry.getItemId());
						scmInvItemInAndOutSum.setWareHouseName(scmInvOtherIssueBillEntry.getWhName());
						scmInvItemInAndOutSum.setUnitName(scmInvOtherIssueBillEntry.getUnitName());
						scmInvItemInAndOutSum.setItemNo(scmInvOtherIssueBillEntry.getItemNo());
						scmInvItemInAndOutSum.setItemName(scmInvOtherIssueBillEntry.getItemName());
						scmInvItemInAndOutSum.setSpec(scmInvOtherIssueBillEntry.getSpec());
						scmInvItemInAndOutSum.setClassName(scmInvOtherIssueBillEntry.getGroupName());
						scmInvItemInAndOutSum.setUnit(scmInvOtherIssueBillEntry.getUnit());
						scmInvItemInAndOutSum.setOrgUnitNo(scmInvOtherIssueBillEntry.getOrgUnitNo());
						if (StringUtils.equals("W", groupBy)||StringUtils.equals("M", groupBy)) {
							scmInvItemInAndOutSum.setWareHouseId(scmInvOtherIssueBillEntry.getWareHouseId());
						} else {
							scmInvItemInAndOutSum.setWareHouseName(scmInvOtherIssueBillEntry.getLot());
						}
						if (StringUtils.equals("1", scmInvOtherIssueBillEntry.getBizType())) {
							scmInvItemInAndOutSum
									.setProfitQty(BigDecimal.ZERO.subtract(scmInvOtherIssueBillEntry.getQty()));
							scmInvItemInAndOutSum
									.setProfitAmt(BigDecimal.ZERO.subtract(scmInvOtherIssueBillEntry.getAmt()));
							scmInvItemInAndOutSum.setProfitTax(BigDecimal.ZERO.subtract(scmInvOtherIssueBillEntry
									.getTaxAmt().subtract(scmInvOtherIssueBillEntry.getAmt())));
							scmInvItemInAndOutSum
									.setProfitTaxAmt(BigDecimal.ZERO.subtract(scmInvOtherIssueBillEntry.getTaxAmt()));
						} else if (StringUtils.equals("2", scmInvOtherIssueBillEntry.getBizType())) {
							scmInvItemInAndOutSum.setScrapQty(scmInvOtherIssueBillEntry.getQty());
							scmInvItemInAndOutSum.setScrapAmt(scmInvOtherIssueBillEntry.getAmt());
							scmInvItemInAndOutSum.setScrapTax(
									scmInvOtherIssueBillEntry.getTaxAmt().subtract(scmInvOtherIssueBillEntry.getAmt()));
							scmInvItemInAndOutSum.setScrapTaxAmt(scmInvOtherIssueBillEntry.getTaxAmt());
						} else {
							scmInvItemInAndOutSum.setOutQty(scmInvOtherIssueBillEntry.getQty());
							scmInvItemInAndOutSum.setOutAmt(scmInvOtherIssueBillEntry.getAmt());
							scmInvItemInAndOutSum.setOutTax(
									scmInvOtherIssueBillEntry.getTaxAmt().subtract(scmInvOtherIssueBillEntry.getAmt()));
							scmInvItemInAndOutSum.setOutTaxAmt(scmInvOtherIssueBillEntry.getTaxAmt());
						}
						scmInvItemInAndOutSumList.add(scmInvItemInAndOutSum);
					}
				}
			}
		}

		// 调拨入库
		page = new Page();
		page.setModelClass(ScmInvMoveInWarehsBillEntry2.class);
		page.setShowCount(Integer.MAX_VALUE);
		if (StringUtils.isNotBlank(sbMaterilaClass.toString())) {
			page.getParam().put(
					ClassUtils.getFinalModelSimpleName(ScmMaterialGroup.class) + "." + ScmMaterialGroup.FN_ID,
					new QueryParam(
							ClassUtils.getFinalModelSimpleName(ScmMaterialGroup.class) + "." + ScmMaterialGroup.FN_ID,
							QueryParam.QUERY_IN, sbMaterilaClass.toString()));
		}
		if (StringUtils.isNotBlank(sbMaterila.toString())) {
			page.getParam()
					.put(ClassUtils.getFinalModelSimpleName(ScmInvMoveInWarehsBillEntry2.class) + "."
							+ ScmInvMoveInWarehsBillEntry2.FN_ITEMID,
							new QueryParam(
									ClassUtils.getFinalModelSimpleName(ScmInvMoveInWarehsBillEntry2.class) + "."
											+ ScmInvMoveInWarehsBillEntry2.FN_ITEMID,
									QueryParam.QUERY_IN, sbMaterila.toString()));
		}
		page.getParam()
				.put(ClassUtils.getFinalModelSimpleName(ScmInvMoveInWarehsBillEntry2.class) + "."
						+ ScmInvMoveInWarehsBillEntry2.FN_ORGUNITNO,
						new QueryParam(
								ClassUtils.getFinalModelSimpleName(ScmInvMoveInWarehsBillEntry2.class) + "."
										+ ScmInvMoveInWarehsBillEntry2.FN_ORGUNITNO,
								QueryParam.QUERY_IN, orgUnitNos.toString()));
		if (StringUtils.isNotBlank(sbWh.toString())) {
			page.getParam()
					.put(ClassUtils.getFinalModelSimpleName(ScmInvMoveInWarehsBillEntry2.class) + "."
							+ ScmInvMoveInWarehsBillEntry2.FN_WAREHOUSEID,
							new QueryParam(
									ClassUtils.getFinalModelSimpleName(ScmInvMoveInWarehsBillEntry2.class) + "."
											+ ScmInvMoveInWarehsBillEntry2.FN_WAREHOUSEID,
									QueryParam.QUERY_IN, sbWh.toString()));
		}
		page.getParam()
				.put(ClassUtils.getFinalModelSimpleName(ScmInvMoveInWarehsBill.class) + "."
						+ ScmInvMoveInWarehsBill.FN_BIZDATE,
						new QueryParam(
								ClassUtils.getFinalModelSimpleName(ScmInvMoveInWarehsBill.class) + "."
										+ ScmInvMoveInWarehsBill.FN_BIZDATE,
								QueryParam.QUERY_BETWEEN, FormatUtils.fmtDate(periodCalendar.getStartDate()), endDate));
		if (!StringUtils.equals("Y", status)) {
			page.getParam()
					.put(ClassUtils.getFinalModelSimpleName(ScmInvMoveInWarehsBill.class) + "."
							+ ScmInvMoveInWarehsBill.FN_STATUS,
							new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMoveInWarehsBill.class) + "."
									+ ScmInvMoveInWarehsBill.FN_STATUS, QueryParam.QUERY_EQ, "E"));
		}
		List<ScmInvMoveInWarehsBillEntry2> scmInvMoveInWarehsBillEntryList = scmInvMoveInWarehsBillEntryBiz
				.queryPage(page, arglist, "findAllPage", param);
		if (scmInvMoveInWarehsBillEntryList != null && !scmInvMoveInWarehsBillEntryList.isEmpty()) {
			for (ScmInvMoveInWarehsBillEntry2 scmInvMoveInWarehsBillEntry : scmInvMoveInWarehsBillEntryList) {
				if (scmInvMoveInWarehsBillEntry.getBizDate().compareTo(FormatUtils.parseDate(beginDate)) < 0) {
					// 比查询日期小的统计到期初数据中
					boolean exists = false;
					if (!scmInvItemInAndOutSumList.isEmpty()) {
						for (ScmInvItemInAndOutSum existScmInvItemInAndOutSum : scmInvItemInAndOutSumList) {
							if (existScmInvItemInAndOutSum.getItemId() == scmInvMoveInWarehsBillEntry.getItemId()
									&& StringUtils.equals(existScmInvItemInAndOutSum.getOrgUnitNo(),
											scmInvMoveInWarehsBillEntry.getOrgUnitNo())) {
								if (StringUtils.equals("W", groupBy)||StringUtils.equals("M", groupBy)) {
									if (existScmInvItemInAndOutSum.getWareHouseId() == scmInvMoveInWarehsBillEntry
											.getWareHouseId()) {
										existScmInvItemInAndOutSum.setInitQty(existScmInvItemInAndOutSum.getInitQty()
												.add(scmInvMoveInWarehsBillEntry.getQty()));
										existScmInvItemInAndOutSum.setInitAmt(existScmInvItemInAndOutSum.getInitAmt()
												.add(scmInvMoveInWarehsBillEntry.getAmt()));
										existScmInvItemInAndOutSum.setInitTax(
												existScmInvItemInAndOutSum.getInitTax().add(scmInvMoveInWarehsBillEntry
														.getTaxAmt().subtract(scmInvMoveInWarehsBillEntry.getAmt())));
										existScmInvItemInAndOutSum.setInitTaxAmt(existScmInvItemInAndOutSum
												.getInitTaxAmt().add(scmInvMoveInWarehsBillEntry.getTaxAmt()));
										exists = true;
										break;
									}
								} else {
									if (StringUtils.equals(existScmInvItemInAndOutSum.getWareHouseName(),
											scmInvMoveInWarehsBillEntry.getLot())) {
										existScmInvItemInAndOutSum.setInitQty(existScmInvItemInAndOutSum.getInitQty()
												.add(scmInvMoveInWarehsBillEntry.getQty()));
										existScmInvItemInAndOutSum.setInitAmt(existScmInvItemInAndOutSum.getInitAmt()
												.add(scmInvMoveInWarehsBillEntry.getAmt()));
										existScmInvItemInAndOutSum.setInitTax(
												existScmInvItemInAndOutSum.getInitTax().add(scmInvMoveInWarehsBillEntry
														.getTaxAmt().subtract(scmInvMoveInWarehsBillEntry.getAmt())));
										existScmInvItemInAndOutSum.setInitTaxAmt(existScmInvItemInAndOutSum
												.getInitTaxAmt().add(scmInvMoveInWarehsBillEntry.getTaxAmt()));
										exists = true;
										break;
									}
								}
							}
						}
					}
					if (!exists) {
						ScmInvItemInAndOutSum scmInvItemInAndOutSum = new ScmInvItemInAndOutSum(true);
						scmInvItemInAndOutSum.setItemId(scmInvMoveInWarehsBillEntry.getItemId());
						scmInvItemInAndOutSum.setWareHouseName(scmInvMoveInWarehsBillEntry.getWhName());
						scmInvItemInAndOutSum.setUnitName(scmInvMoveInWarehsBillEntry.getUnitName());
						scmInvItemInAndOutSum.setItemNo(scmInvMoveInWarehsBillEntry.getItemNo());
						scmInvItemInAndOutSum.setItemName(scmInvMoveInWarehsBillEntry.getItemName());
						scmInvItemInAndOutSum.setSpec(scmInvMoveInWarehsBillEntry.getSpec());
						scmInvItemInAndOutSum.setClassName(scmInvMoveInWarehsBillEntry.getGroupName());
						scmInvItemInAndOutSum.setUnit(scmInvMoveInWarehsBillEntry.getUnit());
						scmInvItemInAndOutSum.setOrgUnitNo(scmInvMoveInWarehsBillEntry.getOrgUnitNo());
						if (StringUtils.equals("W", groupBy)) {
							scmInvItemInAndOutSum.setWareHouseId(scmInvMoveInWarehsBillEntry.getWareHouseId());
						} else {
							scmInvItemInAndOutSum.setWareHouseName(scmInvMoveInWarehsBillEntry.getLot());
						}
						scmInvItemInAndOutSum.setInitQty(scmInvMoveInWarehsBillEntry.getQty());
						scmInvItemInAndOutSum.setInitAmt(scmInvMoveInWarehsBillEntry.getAmt());
						scmInvItemInAndOutSum.setInitTax(
								scmInvMoveInWarehsBillEntry.getTaxAmt().subtract(scmInvMoveInWarehsBillEntry.getAmt()));
						scmInvItemInAndOutSum.setInitTaxAmt(scmInvMoveInWarehsBillEntry.getTaxAmt());
						scmInvItemInAndOutSumList.add(scmInvItemInAndOutSum);
					}
				} else {
					boolean exists = false;
					if (!scmInvItemInAndOutSumList.isEmpty()) {
						for (ScmInvItemInAndOutSum existScmInvItemInAndOutSum : scmInvItemInAndOutSumList) {
							if (existScmInvItemInAndOutSum.getItemId() == scmInvMoveInWarehsBillEntry.getItemId()
									&& StringUtils.equals(existScmInvItemInAndOutSum.getOrgUnitNo(),
											scmInvMoveInWarehsBillEntry.getOrgUnitNo())) {
								if (StringUtils.equals("W", groupBy)||StringUtils.equals("M", groupBy)) {
									if (existScmInvItemInAndOutSum.getWareHouseId() == scmInvMoveInWarehsBillEntry
											.getWareHouseId()) {
										existScmInvItemInAndOutSum.setMoveQty(existScmInvItemInAndOutSum.getMoveQty()
												.add(scmInvMoveInWarehsBillEntry.getQty()));
										existScmInvItemInAndOutSum.setMoveAmt(existScmInvItemInAndOutSum.getMoveAmt()
												.add(scmInvMoveInWarehsBillEntry.getAmt()));
										existScmInvItemInAndOutSum.setMoveTax(
												existScmInvItemInAndOutSum.getMoveTax().add(scmInvMoveInWarehsBillEntry
														.getTaxAmt().subtract(scmInvMoveInWarehsBillEntry.getAmt())));
										existScmInvItemInAndOutSum.setMoveTaxAmt(existScmInvItemInAndOutSum
												.getMoveTaxAmt().add(scmInvMoveInWarehsBillEntry.getTaxAmt()));
										exists = true;
										break;
									}
								} else {
									if (StringUtils.equals(existScmInvItemInAndOutSum.getWareHouseName(),
											scmInvMoveInWarehsBillEntry.getLot())) {
										existScmInvItemInAndOutSum.setMoveQty(existScmInvItemInAndOutSum.getMoveQty()
												.add(scmInvMoveInWarehsBillEntry.getQty()));
										existScmInvItemInAndOutSum.setMoveAmt(existScmInvItemInAndOutSum.getMoveAmt()
												.add(scmInvMoveInWarehsBillEntry.getAmt()));
										existScmInvItemInAndOutSum.setMoveTax(
												existScmInvItemInAndOutSum.getMoveTax().add(scmInvMoveInWarehsBillEntry
														.getTaxAmt().subtract(scmInvMoveInWarehsBillEntry.getAmt())));
										existScmInvItemInAndOutSum.setMoveTaxAmt(existScmInvItemInAndOutSum
												.getMoveTaxAmt().add(scmInvMoveInWarehsBillEntry.getTaxAmt()));
										exists = true;
										break;
									}
								}
							}
						}
					}
					if (!exists) {
						// 放入明细记录结果集中
						ScmInvItemInAndOutSum scmInvItemInAndOutSum = new ScmInvItemInAndOutSum(true);
						scmInvItemInAndOutSum.setItemId(scmInvMoveInWarehsBillEntry.getItemId());
						scmInvItemInAndOutSum.setWareHouseName(scmInvMoveInWarehsBillEntry.getWhName());
						scmInvItemInAndOutSum.setUnitName(scmInvMoveInWarehsBillEntry.getUnitName());
						scmInvItemInAndOutSum.setItemNo(scmInvMoveInWarehsBillEntry.getItemNo());
						scmInvItemInAndOutSum.setItemName(scmInvMoveInWarehsBillEntry.getItemName());
						scmInvItemInAndOutSum.setSpec(scmInvMoveInWarehsBillEntry.getSpec());
						scmInvItemInAndOutSum.setClassName(scmInvMoveInWarehsBillEntry.getGroupName());
						scmInvItemInAndOutSum.setUnit(scmInvMoveInWarehsBillEntry.getUnit());
						scmInvItemInAndOutSum.setOrgUnitNo(scmInvMoveInWarehsBillEntry.getOrgUnitNo());
						if (StringUtils.equals("W", groupBy)||StringUtils.equals("M", groupBy)) {
							scmInvItemInAndOutSum.setWareHouseId(scmInvMoveInWarehsBillEntry.getWareHouseId());
						} else {
							scmInvItemInAndOutSum.setWareHouseName(scmInvMoveInWarehsBillEntry.getLot());
						}
						scmInvItemInAndOutSum.setMoveQty(scmInvMoveInWarehsBillEntry.getQty());
						scmInvItemInAndOutSum.setMoveAmt(scmInvMoveInWarehsBillEntry.getAmt());
						scmInvItemInAndOutSum.setMoveTax(
								scmInvMoveInWarehsBillEntry.getTaxAmt().subtract(scmInvMoveInWarehsBillEntry.getAmt()));
						scmInvItemInAndOutSum.setMoveTaxAmt(scmInvMoveInWarehsBillEntry.getTaxAmt());
						scmInvItemInAndOutSumList.add(scmInvItemInAndOutSum);
					}
				}
			}
		}
		// 调拨出库
		page = new Page();
		page.setModelClass(ScmInvMoveIssueBillEntry2.class);
		page.setShowCount(Integer.MAX_VALUE);
		if (StringUtils.isNotBlank(sbMaterilaClass.toString())) {
			page.getParam().put(
					ClassUtils.getFinalModelSimpleName(ScmMaterialGroup.class) + "." + ScmMaterialGroup.FN_ID,
					new QueryParam(
							ClassUtils.getFinalModelSimpleName(ScmMaterialGroup.class) + "." + ScmMaterialGroup.FN_ID,
							QueryParam.QUERY_IN, sbMaterilaClass.toString()));
		}
		if (StringUtils.isNotBlank(sbMaterila.toString())) {
			page.getParam()
					.put(ClassUtils.getFinalModelSimpleName(ScmInvMoveIssueBillEntry2.class) + "."
							+ ScmInvMoveIssueBillEntry2.FN_ITEMID,
							new QueryParam(
									ClassUtils.getFinalModelSimpleName(ScmInvMoveIssueBillEntry2.class) + "."
											+ ScmInvMoveIssueBillEntry2.FN_ITEMID,
									QueryParam.QUERY_IN, sbMaterila.toString()));
		}
		page.getParam()
				.put(ClassUtils.getFinalModelSimpleName(ScmInvMoveIssueBillEntry2.class) + "."
						+ ScmInvMoveIssueBillEntry2.FN_ORGUNITNO,
						new QueryParam(
								ClassUtils.getFinalModelSimpleName(ScmInvMoveIssueBillEntry2.class) + "."
										+ ScmInvMoveIssueBillEntry2.FN_ORGUNITNO,
								QueryParam.QUERY_IN, orgUnitNos.toString()));
		if (StringUtils.isNotBlank(sbWh.toString())) {
			page.getParam()
					.put(ClassUtils.getFinalModelSimpleName(ScmInvMoveIssueBillEntry2.class) + "."
							+ ScmInvMoveIssueBillEntry2.FN_WAREHOUSEID,
							new QueryParam(
									ClassUtils.getFinalModelSimpleName(ScmInvMoveIssueBillEntry2.class) + "."
											+ ScmInvMoveIssueBillEntry2.FN_WAREHOUSEID,
									QueryParam.QUERY_IN, sbWh.toString()));
		}
		page.getParam().put(
				ClassUtils.getFinalModelSimpleName(ScmInvMoveIssueBill.class) + "." + ScmInvMoveIssueBill.FN_BIZDATE,
				new QueryParam(
						ClassUtils.getFinalModelSimpleName(ScmInvMoveIssueBill.class) + "."
								+ ScmInvMoveIssueBill.FN_BIZDATE,
						QueryParam.QUERY_BETWEEN, FormatUtils.fmtDate(periodCalendar.getStartDate()), endDate));
		if (!StringUtils.equals("Y", status)) {
			page.getParam().put(
					ClassUtils.getFinalModelSimpleName(ScmInvMoveIssueBill.class) + "." + ScmInvMoveIssueBill.FN_STATUS,
					new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMoveIssueBill.class) + "."
							+ ScmInvMoveIssueBill.FN_STATUS, QueryParam.QUERY_EQ, "E"));
		}
		List<ScmInvMoveIssueBillEntry2> scmInvMoveIssueBillEntryList = scmInvMoveIssueBillEntryBiz.queryPage(page,
				arglist, "findAllPage", param);
		if (scmInvMoveIssueBillEntryList != null && !scmInvMoveIssueBillEntryList.isEmpty()) {
			for (ScmInvMoveIssueBillEntry2 scmInvMoveIssueBillEntry : scmInvMoveIssueBillEntryList) {
				if (scmInvMoveIssueBillEntry.getBizDate().compareTo(FormatUtils.parseDate(beginDate)) < 0) {
					// 比查询日期小的统计到期初数据中
					boolean exists = false;
					if (!scmInvItemInAndOutSumList.isEmpty()) {
						for (ScmInvItemInAndOutSum existScmInvItemInAndOutSum : scmInvItemInAndOutSumList) {
							if (existScmInvItemInAndOutSum.getItemId() == scmInvMoveIssueBillEntry.getItemId()
									&& StringUtils.equals(existScmInvItemInAndOutSum.getOrgUnitNo(),
											scmInvMoveIssueBillEntry.getOrgUnitNo())) {
								if (StringUtils.equals("W", groupBy)||StringUtils.equals("M", groupBy)) {
									if (existScmInvItemInAndOutSum.getWareHouseId() == scmInvMoveIssueBillEntry
											.getWareHouseId()) {
										existScmInvItemInAndOutSum.setInitQty(existScmInvItemInAndOutSum.getInitQty()
												.subtract(scmInvMoveIssueBillEntry.getQty()));
										existScmInvItemInAndOutSum.setInitAmt(existScmInvItemInAndOutSum.getInitAmt()
												.subtract(scmInvMoveIssueBillEntry.getAmt()));
										existScmInvItemInAndOutSum.setInitTax(existScmInvItemInAndOutSum.getInitTax()
												.subtract(scmInvMoveIssueBillEntry.getTaxAmt()
														.subtract(scmInvMoveIssueBillEntry.getAmt())));
										existScmInvItemInAndOutSum.setInitTaxAmt(existScmInvItemInAndOutSum
												.getInitTaxAmt().subtract(scmInvMoveIssueBillEntry.getTaxAmt()));
										exists = true;
										break;
									}
								} else {
									if (StringUtils.equals(existScmInvItemInAndOutSum.getWareHouseName(),
											scmInvMoveIssueBillEntry.getLot())) {
										existScmInvItemInAndOutSum.setInitQty(existScmInvItemInAndOutSum.getInitQty()
												.subtract(scmInvMoveIssueBillEntry.getQty()));
										existScmInvItemInAndOutSum.setInitAmt(existScmInvItemInAndOutSum.getInitAmt()
												.subtract(scmInvMoveIssueBillEntry.getAmt()));
										existScmInvItemInAndOutSum.setInitTax(existScmInvItemInAndOutSum.getInitTax()
												.subtract(scmInvMoveIssueBillEntry.getTaxAmt()
														.subtract(scmInvMoveIssueBillEntry.getAmt())));
										existScmInvItemInAndOutSum.setInitTaxAmt(existScmInvItemInAndOutSum
												.getInitTaxAmt().subtract(scmInvMoveIssueBillEntry.getTaxAmt()));
										exists = true;
										break;
									}
								}
							}
						}
					}
					if (!exists) {
						ScmInvItemInAndOutSum scmInvItemInAndOutSum = new ScmInvItemInAndOutSum(true);
						scmInvItemInAndOutSum.setItemId(scmInvMoveIssueBillEntry.getItemId());
						scmInvItemInAndOutSum.setWareHouseName(scmInvMoveIssueBillEntry.getWhName());
						scmInvItemInAndOutSum.setUnitName(scmInvMoveIssueBillEntry.getUnitName());
						scmInvItemInAndOutSum.setItemNo(scmInvMoveIssueBillEntry.getItemNo());
						scmInvItemInAndOutSum.setItemName(scmInvMoveIssueBillEntry.getItemName());
						scmInvItemInAndOutSum.setSpec(scmInvMoveIssueBillEntry.getSpec());
						scmInvItemInAndOutSum.setClassName(scmInvMoveIssueBillEntry.getGroupName());
						scmInvItemInAndOutSum.setUnit(scmInvMoveIssueBillEntry.getUnit());
						scmInvItemInAndOutSum.setOrgUnitNo(scmInvMoveIssueBillEntry.getOrgUnitNo());
						if (StringUtils.equals("W", groupBy)||StringUtils.equals("M", groupBy)) {
							scmInvItemInAndOutSum.setWareHouseId(scmInvMoveIssueBillEntry.getWareHouseId());
						} else {
							scmInvItemInAndOutSum.setWareHouseName(scmInvMoveIssueBillEntry.getLot());
						}
						scmInvItemInAndOutSum.setInitQty(BigDecimal.ZERO.subtract(scmInvMoveIssueBillEntry.getQty()));
						scmInvItemInAndOutSum.setInitAmt(BigDecimal.ZERO.subtract(scmInvMoveIssueBillEntry.getAmt()));
						scmInvItemInAndOutSum.setInitTax(BigDecimal.ZERO.subtract(
								scmInvMoveIssueBillEntry.getTaxAmt().subtract(scmInvMoveIssueBillEntry.getAmt())));
						scmInvItemInAndOutSum
								.setInitTaxAmt(BigDecimal.ZERO.subtract(scmInvMoveIssueBillEntry.getTaxAmt()));
						scmInvItemInAndOutSumList.add(scmInvItemInAndOutSum);
					}
				} else {
					boolean exists = false;
					if (!scmInvItemInAndOutSumList.isEmpty()) {
						for (ScmInvItemInAndOutSum existScmInvItemInAndOutSum : scmInvItemInAndOutSumList) {
							if (existScmInvItemInAndOutSum.getItemId() == scmInvMoveIssueBillEntry.getItemId()
									&& StringUtils.equals(existScmInvItemInAndOutSum.getOrgUnitNo(),
											scmInvMoveIssueBillEntry.getOrgUnitNo())) {
								if (StringUtils.equals("W", groupBy)||StringUtils.equals("M", groupBy)) {
									if (existScmInvItemInAndOutSum.getWareHouseId() == scmInvMoveIssueBillEntry
											.getWareHouseId()) {
										existScmInvItemInAndOutSum.setMoveQty(existScmInvItemInAndOutSum.getMoveQty()
												.subtract(scmInvMoveIssueBillEntry.getQty()));
										existScmInvItemInAndOutSum.setMoveAmt(existScmInvItemInAndOutSum.getMoveAmt()
												.subtract(scmInvMoveIssueBillEntry.getAmt()));
										existScmInvItemInAndOutSum.setMoveTax(existScmInvItemInAndOutSum.getMoveTax()
												.subtract(scmInvMoveIssueBillEntry.getTaxAmt()
														.subtract(scmInvMoveIssueBillEntry.getAmt())));
										existScmInvItemInAndOutSum.setMoveTaxAmt(existScmInvItemInAndOutSum
												.getMoveTaxAmt().subtract(scmInvMoveIssueBillEntry.getTaxAmt()));
										exists = true;
										break;
									}
								} else {
									if (StringUtils.equals(existScmInvItemInAndOutSum.getWareHouseName(),
											scmInvMoveIssueBillEntry.getLot())) {
										existScmInvItemInAndOutSum.setMoveQty(existScmInvItemInAndOutSum.getMoveQty()
												.subtract(scmInvMoveIssueBillEntry.getQty()));
										existScmInvItemInAndOutSum.setMoveAmt(existScmInvItemInAndOutSum.getMoveAmt()
												.subtract(scmInvMoveIssueBillEntry.getAmt()));
										existScmInvItemInAndOutSum.setMoveTax(existScmInvItemInAndOutSum.getMoveTax()
												.subtract(scmInvMoveIssueBillEntry.getTaxAmt()
														.subtract(scmInvMoveIssueBillEntry.getAmt())));
										existScmInvItemInAndOutSum.setMoveTaxAmt(existScmInvItemInAndOutSum
												.getMoveTaxAmt().subtract(scmInvMoveIssueBillEntry.getTaxAmt()));
										exists = true;
										break;
									}
								}
							}
						}
					}
					if (!exists) {
						// 放入明细记录结果集中
						ScmInvItemInAndOutSum scmInvItemInAndOutSum = new ScmInvItemInAndOutSum(true);
						scmInvItemInAndOutSum.setItemId(scmInvMoveIssueBillEntry.getItemId());
						scmInvItemInAndOutSum.setWareHouseName(scmInvMoveIssueBillEntry.getWhName());
						scmInvItemInAndOutSum.setUnitName(scmInvMoveIssueBillEntry.getUnitName());
						scmInvItemInAndOutSum.setItemNo(scmInvMoveIssueBillEntry.getItemNo());
						scmInvItemInAndOutSum.setItemName(scmInvMoveIssueBillEntry.getItemName());
						scmInvItemInAndOutSum.setSpec(scmInvMoveIssueBillEntry.getSpec());
						scmInvItemInAndOutSum.setClassName(scmInvMoveIssueBillEntry.getGroupName());
						scmInvItemInAndOutSum.setUnit(scmInvMoveIssueBillEntry.getUnit());
						scmInvItemInAndOutSum.setOrgUnitNo(scmInvMoveIssueBillEntry.getOrgUnitNo());
						if (StringUtils.equals("W", groupBy)||StringUtils.equals("M", groupBy)) {
							scmInvItemInAndOutSum.setWareHouseId(scmInvMoveIssueBillEntry.getWareHouseId());
						} else {
							scmInvItemInAndOutSum.setWareHouseName(scmInvMoveIssueBillEntry.getLot());
						}
						scmInvItemInAndOutSum.setMoveQty(BigDecimal.ZERO.subtract(scmInvMoveIssueBillEntry.getQty()));
						scmInvItemInAndOutSum.setMoveAmt(BigDecimal.ZERO.subtract(scmInvMoveIssueBillEntry.getAmt()));
						scmInvItemInAndOutSum.setMoveTax(BigDecimal.ZERO.subtract(
								scmInvMoveIssueBillEntry.getTaxAmt().subtract(scmInvMoveIssueBillEntry.getAmt())));
						scmInvItemInAndOutSum
								.setMoveTaxAmt(BigDecimal.ZERO.subtract(scmInvMoveIssueBillEntry.getTaxAmt()));
						scmInvItemInAndOutSumList.add(scmInvItemInAndOutSum);
					}
				}
			}
		}
		if (!scmInvItemInAndOutSumList.isEmpty()) {
			HashMap<String, Object> cacheMap = new HashMap<String, Object>();
			for (ScmInvItemInAndOutSum scmInvItemInAndOutSum : scmInvItemInAndOutSumList) {
				scmInvItemInAndOutSum.setGroupBy(groupBy);
				OrgBaseUnit2 orgBaseUnit = (OrgBaseUnit2) cacheMap
						.get(ClassUtils.getFinalModelSimpleName(OrgBaseUnit2.class) + "_"
								+ scmInvItemInAndOutSum.getOrgUnitNo());
				if (orgBaseUnit == null) {
					orgBaseUnit = orgUnitBiz.selectbyOrgNo(scmInvItemInAndOutSum.getOrgUnitNo(), param);
					if (orgBaseUnit != null) {
						cacheMap.put(ClassUtils.getFinalModelSimpleName(OrgBaseUnit2.class) + "_"
								+ scmInvItemInAndOutSum.getOrgUnitNo(), orgBaseUnit);
					}
				}
				if (orgBaseUnit != null) {
					scmInvItemInAndOutSum.setOrgUnitName(orgBaseUnit.getOrgUnitName());
				} else {
					scmInvItemInAndOutSum.setOrgUnitName(scmInvItemInAndOutSum.getOrgUnitNo());
				}

				if (scmInvItemInAndOutSum.getWareHouseId() > 0) {
					ScmInvWareHouse scmInvWareHouse = scmInvWareHouseBiz
							.selectDirect(scmInvItemInAndOutSum.getWareHouseId(), param);
					if (scmInvWareHouse != null) {
						scmInvItemInAndOutSum.setWareHouseName(scmInvWareHouse.getWhName());
					}
				}
				if (scmInvItemInAndOutSum.getUnit() > 0) {
					ScmMeasureUnit scmMeasureUnit = scmMeasureUnitBiz.selectDirect(scmInvItemInAndOutSum.getUnit(),
							param);
					if (scmMeasureUnit != null)
						scmInvItemInAndOutSum.setUnitName(scmMeasureUnit.getUnitName());
				}
				if (scmInvItemInAndOutSum.getItemId() > 0) {
					ScmMaterial2 scmMaterial = scmMaterialBiz.selectByItemId(scmInvItemInAndOutSum.getItemId(), param);
					if (scmMaterial != null) {
						scmInvItemInAndOutSum.setItemNo(scmMaterial.getItemNo());
						scmInvItemInAndOutSum.setItemName(scmMaterial.getItemName());
						scmInvItemInAndOutSum.setSpec(scmMaterial.getSpec());
						if (scmMaterial.getGroupId() > 0) {
							ScmMaterialGroup scmMaterialGroup = scmMaterialGroupBiz
									.selectDirect(scmMaterial.getGroupId(), param);
							if (scmMaterialGroup != null) {
								scmInvItemInAndOutSum.setClassName(scmMaterialGroup.getClassName());
							}
						}
					}
				}

				scmInvItemInAndOutSum.setStockQty(scmInvItemInAndOutSum.getInitQty()
						.add(scmInvItemInAndOutSum.getAddInQty()).add(scmInvItemInAndOutSum.getMoveQty())
						.subtract(scmInvItemInAndOutSum.getOutQty()).subtract(scmInvItemInAndOutSum.getReqQty())
						.add(scmInvItemInAndOutSum.getProfitQty()).subtract(scmInvItemInAndOutSum.getScrapQty()));
				scmInvItemInAndOutSum.setStockAmt(scmInvItemInAndOutSum.getInitAmt()
						.add(scmInvItemInAndOutSum.getAddInAmt()).add(scmInvItemInAndOutSum.getMoveAmt())
						.subtract(scmInvItemInAndOutSum.getOutAmt()).subtract(scmInvItemInAndOutSum.getReqAmt())
						.add(scmInvItemInAndOutSum.getProfitAmt()).subtract(scmInvItemInAndOutSum.getScrapAmt()));
				scmInvItemInAndOutSum.setStockTax(scmInvItemInAndOutSum.getInitTax()
						.add(scmInvItemInAndOutSum.getAddInTax()).add(scmInvItemInAndOutSum.getMoveTax())
						.subtract(scmInvItemInAndOutSum.getOutTax()).subtract(scmInvItemInAndOutSum.getReqTax())
						.add(scmInvItemInAndOutSum.getProfitTax()).subtract(scmInvItemInAndOutSum.getScrapTax()));
				scmInvItemInAndOutSum.setStockTaxAmt(scmInvItemInAndOutSum.getInitTaxAmt()
						.add(scmInvItemInAndOutSum.getAddInTaxAmt()).add(scmInvItemInAndOutSum.getMoveTaxAmt())
						.subtract(scmInvItemInAndOutSum.getOutTaxAmt()).subtract(scmInvItemInAndOutSum.getReqTaxAmt())
						.add(scmInvItemInAndOutSum.getProfitTaxAmt()).subtract(scmInvItemInAndOutSum.getScrapTaxAmt()));
			}
		}
		
		// 数据排序
		if(StringUtils.equals("M", groupBy)) {
			String fields[] = { "className", "wareHouseName" };
			String sorts[] = { "ASC", "ASC" };
			scmInvItemInAndOutSumList = (List<ScmInvItemInAndOutSum>) ListSortUtil.sort(scmInvItemInAndOutSumList,
					fields, sorts);
		}
		return scmInvItemInAndOutSumList;
	}

	// 入库事务汇总表-供应商*物资
	@Override
	public List<ScmInvItemWrSum> selectScmInvItemWrSum(HttpServletRequest httpServletRequest) throws AppException {
		List<ScmInvItemWrSum> scmInvItemWrSumList = new ArrayList<>();
		// 1 获取查询参数
		String currentOrgUnitNo = httpServletRequest.getParameter("orgUnitNo");
		String currentControlUnitNo = httpServletRequest.getParameter("controlUnitNo");
		String invOrgUnitNo = httpServletRequest.getParameter("invOrgUnitNo");
		String whOrDept = httpServletRequest.getParameter("whOrDept");
		String begDate = httpServletRequest.getParameter("begDate");
		String endDate = httpServletRequest.getParameter("endDate");
		String materialClassName = httpServletRequest.getParameter("materialClassName");
		String materialName = httpServletRequest.getParameter("materialName");
		StringBuffer materialIds = new StringBuffer("");
		if (StringUtils.isNotBlank(materialName)) {
			String[] idList = StringUtils.split(materialName, ",");
			for (String id : idList) {
				if (StringUtils.isBlank(id))
					continue;
				if (StringUtils.isNotBlank(materialIds.toString()))
					materialIds.append(",");
				materialIds.append(id);
			}
		}
		String vendorName = httpServletRequest.getParameter("vendorName");
		StringBuffer vendorIds = new StringBuffer("");
		if (StringUtils.isNotBlank(vendorName)) {
			String[] idList = StringUtils.split(vendorName, ",");
			for (String id : idList) {
				if (StringUtils.isBlank(id))
					continue;
				if (StringUtils.isNotBlank(vendorIds.toString()))
					vendorIds.append(",");
				vendorIds.append(id);
			}
		}
		String stockType = httpServletRequest.getParameter("stockType");
		String noPost = "1".equals(httpServletRequest.getParameter("noPost")) ? "Y" : "N";
		String taxRate = httpServletRequest.getParameter("taxRate");
		String type = "M";
		if (StringUtils.isBlank(begDate) || StringUtils.isBlank(endDate))
			return scmInvItemWrSumList;
		Param param = new Param();
		param.setOrgUnitNo(currentOrgUnitNo);
		param.setControlUnitNo(currentControlUnitNo);
		HashMap<String, Object> map = new HashMap<>();
		StringBuffer orgUnitNos = new StringBuffer("");
		if (StringUtils.isNotBlank(invOrgUnitNo)) {
			String[] noList = StringUtils.split(invOrgUnitNo, ",");
			for (String no : noList) {
				if (StringUtils.isBlank(no))
					continue;
				List<OrgStorage2> orgStorageList = orgStorageBiz.findChild(no, param);
				if (orgStorageList != null && !orgStorageList.isEmpty()) {
					for (OrgStorage2 orgStorage : orgStorageList) {
						if (StringUtils.isNotBlank(orgUnitNos.toString()))
							orgUnitNos.append(",");
						orgUnitNos.append("'").append(orgStorage.getOrgUnitNo()).append("'");
					}
				} else {
					orgUnitNos.append("'").append(no).append("'");
				}
			}
		}
//        List<OrgStorage2> orgStorageList = orgStorageBiz.findChild(currentOrgUnitNo, param);
//        if(orgStorageList != null && !orgStorageList.isEmpty()) {
//            for(OrgStorage2 orgStorage:orgStorageList) {
//                if(StringUtils.isNotBlank(orgUnitNos.toString()))
//                    orgUnitNos.append(",");
//                orgUnitNos.append("'").append(orgStorage.getOrgUnitNo()).append("'");
//            }
//        }else {
//            orgUnitNos.append("'").append(currentOrgUnitNo).append("'");
//        }
//        map.put("orgUnitNo", invOrgUnitNo);
		int materialClassId = 0;
		StringBuffer sbMaterilaClass = new StringBuffer("");
		if (StringUtils.isNotBlank(materialClassName) && StringUtils.isNumeric(materialClassName)) {
			materialClassId = (Integer.parseInt(materialClassName));
			List<ScmMaterialGroup> scmMaterialGroupList = scmMaterialGroupBiz.findChild(materialClassId, param);
			if (scmMaterialGroupList != null && scmMaterialGroupList.size() > 0) {
				for (ScmMaterialGroup scmMaterialGroup : scmMaterialGroupList) {
					if (StringUtils.isNotBlank(sbMaterilaClass.toString()))
						sbMaterilaClass.append(",");
					sbMaterilaClass.append(scmMaterialGroup.getId());
				}
			} else {
				sbMaterilaClass.append(materialClassId);
			}
		}
		map.put("orgUnitNo", orgUnitNos.toString());
		map.put("begDate", begDate);
		map.put("endDate", endDate);
		map.put("materialIds", materialIds.toString());
		map.put("materialClassIds", sbMaterilaClass.toString());
		map.put("vendorIds", vendorIds.toString());
		long wareHouseId = 0;
		if (StringUtils.isBlank(stockType))
			stockType = "0";
		map.put("stockType", stockType);
		if (StringUtils.isNotBlank(whOrDept)) {
			if (!StringUtils.equals("0", StringUtils.left(whOrDept, 1))) {
				map.put("wareHouseId", whOrDept);
			} else {
				map.put("useOrgUnitNo", whOrDept);
			}
		}
		if ("Y".equals(noPost)) {
			noPost = "1";
		} else {
			noPost = "0";
		}
		map.put("noPost", noPost);
		if (StringUtils.isNotBlank(taxRate) && !StringUtils.equals("-1", taxRate)) {
			map.put("taxRate", taxRate);
		}
		map.put("type", type);
		// 2 查询数据库
		List<ScmInvItemWrSum> list = ((ScmInvReportDAO) dao).selectScmInvItemWrSum(map);
		// 3 数据处理及返回
		if (list != null && list.size() > 0) {
			for (ScmInvItemWrSum scmInvItemWrSum : list) {
				if (StringUtils.isNotBlank(scmInvItemWrSum.getOrgUnitNo())) {
					OrgBaseUnit2 orgBaseUnit = orgBaseUnitBiz.selectbyOrgNo(scmInvItemWrSum.getOrgUnitNo(), param);
					if (orgBaseUnit != null) {
						scmInvItemWrSum.setOrgUnitName(orgBaseUnit.getOrgUnitName());
					}
				}
				if (BigDecimal.ZERO.compareTo(scmInvItemWrSum.getAddInQty()) != 0) {
					scmInvItemWrSum.setAddInPrice(scmInvItemWrSum.getAddInAmt().divide(scmInvItemWrSum.getAddInQty(), 2,
							RoundingMode.HALF_UP));
					scmInvItemWrSum.setAddInTaxPrice(scmInvItemWrSum.getAddInTaxAmt()
							.divide(scmInvItemWrSum.getAddInQty(), 2, RoundingMode.HALF_UP));
				}
				if ("V".equals(type)) {
					if (scmInvItemWrSum.getWareHouseId() > 0) {
						// 仓库
						ScmInvWareHouse scmInvWareHouse = scmInvWareHouseBiz
								.selectDirect(scmInvItemWrSum.getWareHouseId(), param);
						if (scmInvWareHouse != null) {
							scmInvItemWrSum.setWareHouseName(scmInvWareHouse.getWhName());
						}
					}
					if (StringUtils.isNotBlank(scmInvItemWrSum.getUseOrgUnitNo())) {
						OrgBaseUnit2 orgBaseUnit = orgBaseUnitBiz.selectbyOrgNo(scmInvItemWrSum.getUseOrgUnitNo(),
								param);
						if (orgBaseUnit != null) {
							scmInvItemWrSum.setWareHouseName(orgBaseUnit.getOrgUnitName());
						}
					}
				}
			}
		}
		return list;
	}

	@Override
	public List<ScmInvItemInAndOutDetail> selectScmInOutDetail(HttpServletRequest request) throws AppException {
		List<ScmInvItemInAndOutDetail> scmInvItemInAndOutDetailList = new ArrayList<ScmInvItemInAndOutDetail>();
		String currentOrgUnitNo = request.getParameter("orgUnitNo");
		String currentControlUnitNo = request.getParameter("controlUnitNo");
		String orgUnitNo = request.getParameter("invOrgUnitNo");
		String whName = request.getParameter("whName");
		String beginDate = request.getParameter("beginDate");
		String endDate = request.getParameter("endDate");
		String materialName = request.getParameter("materialName");
		String status = "1".equals(request.getParameter("status")) ? "Y" : "N";
		String lot = request.getParameter("lot");
		if (StringUtils.isBlank(beginDate) || StringUtils.isBlank(endDate) || StringUtils.isBlank(materialName))
			return scmInvItemInAndOutDetailList;
		if (StringUtils.isBlank(orgUnitNo))
			orgUnitNo = currentOrgUnitNo;
		Param param = new Param();
		param.setOrgUnitNo(currentOrgUnitNo);
		param.setControlUnitNo(currentControlUnitNo);
		StringBuffer orgUnitNos = new StringBuffer("");
		List<OrgStorage2> orgStorageList = orgStorageBiz.findChild(orgUnitNo, param);
		if (orgStorageList != null && !orgStorageList.isEmpty()) {
			for (OrgStorage2 orgStorage : orgStorageList) {
				if (StringUtils.isNotBlank(orgUnitNos.toString()))
					orgUnitNos.append(",");
				orgUnitNos.append("'").append(orgStorage.getOrgUnitNo()).append("'");
			}
		} else {
			orgUnitNos.append("'").append(orgUnitNo).append("'");
		}
		// 获取期间期初数
		PeriodCalendar periodCalendar = periodCalendarBiz.selectByBizdate(FormatUtils.parseDate(beginDate), param);
		HashMap<String, BigDecimal> initQtyMap = new HashMap<String, BigDecimal>();
		HashMap<String, BigDecimal> initAmtMap = new HashMap<String, BigDecimal>();
		HashMap<String, BigDecimal> initTaxAmtMap = new HashMap<String, BigDecimal>();
		BigDecimal initQty = BigDecimal.ZERO;
		BigDecimal initAmt = BigDecimal.ZERO;
		BigDecimal initTaxAmt = BigDecimal.ZERO;
		if (periodCalendar != null) {
			Page page = new Page();
			page.setModelClass(ScmInvBal.class);
			page.setShowCount(Integer.MAX_VALUE);
			if (StringUtils.isNotBlank(lot)) {
				page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvBal.class) + "." + ScmInvBal.FN_LOT,
						new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvBal.class) + "." + ScmInvBal.FN_LOT,
								QueryParam.QUERY_LIKE, "%" + lot + "%"));
			}
			page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvBal.class) + "." + ScmInvBal.FN_ORGUNITNO,
					new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvBal.class) + "." + ScmInvBal.FN_ORGUNITNO,
							QueryParam.QUERY_IN, orgUnitNos.toString()));
			page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvBal.class) + "." + ScmInvBal.FN_STARTQTY,
					new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvBal.class) + "." + ScmInvBal.FN_STARTQTY,
							QueryParam.QUERY_GT, "0"));
			if (StringUtils.isNotBlank(whName)) {
				page.getParam().put(
						ClassUtils.getFinalModelSimpleName(ScmInvBal.class) + "." + ScmInvBal.FN_WAREHOUSEID,
						new QueryParam(
								ClassUtils.getFinalModelSimpleName(ScmInvBal.class) + "." + ScmInvBal.FN_WAREHOUSEID,
								QueryParam.QUERY_EQ, whName));
			}
			page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvBal.class) + "." + ScmInvBal.FN_ITEMID,
					new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvBal.class) + "." + ScmInvBal.FN_ITEMID,
							QueryParam.QUERY_EQ, materialName));
			page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvBal.class) + "." + ScmInvBal.FN_PERIODID,
					new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvBal.class) + "." + ScmInvBal.FN_PERIODID,
							QueryParam.QUERY_EQ, String.valueOf(periodCalendar.getPeriodId())));
			page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvBal.class) + "." + ScmInvBal.FN_COSTCENTER,
					new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvBal.class) + "." + ScmInvBal.FN_COSTCENTER,
							QueryParam.QUERY_EQ, "0"));
			List<String> arglist = new ArrayList<>();
			arglist.add("controlUnitNo=" + currentControlUnitNo);
			List<ScmInvBal> scmInvBalList = scmInvBalBiz.queryPage(page, arglist, "findAllPage", param);
			if (scmInvBalList != null && !scmInvBalList.isEmpty()) {
				for (ScmInvBal scmInvBal : scmInvBalList) {
					if (initQtyMap.containsKey(scmInvBal.getOrgUnitNo() + "_" + scmInvBal.getWareHouseId())) {
						initQty = initQtyMap.get(scmInvBal.getOrgUnitNo() + "_" + scmInvBal.getWareHouseId());
						initQty = initQty.add(scmInvBal.getStartQty());
						initQtyMap.put(scmInvBal.getOrgUnitNo() + "_" + scmInvBal.getWareHouseId(), initQty);
					} else {
						initQtyMap.put(scmInvBal.getOrgUnitNo() + "_" + scmInvBal.getWareHouseId(),
								scmInvBal.getStartQty());
					}
					if (initAmtMap.containsKey(scmInvBal.getOrgUnitNo() + "_" + scmInvBal.getWareHouseId())) {
						initAmt = initAmtMap.get(scmInvBal.getOrgUnitNo() + "_" + scmInvBal.getWareHouseId());
						initAmt = initAmt.add(scmInvBal.getStartAmt());
						initAmtMap.put(scmInvBal.getOrgUnitNo() + "_" + scmInvBal.getWareHouseId(), initAmt);
					} else {
						initAmtMap.put(scmInvBal.getOrgUnitNo() + "_" + scmInvBal.getWareHouseId(),
								scmInvBal.getStartAmt());
					}
					if (initTaxAmtMap.containsKey(scmInvBal.getOrgUnitNo() + "_" + scmInvBal.getWareHouseId())) {
						initTaxAmt = initTaxAmtMap.get(scmInvBal.getOrgUnitNo() + "_" + scmInvBal.getWareHouseId());
						initTaxAmt = initTaxAmt.add(scmInvBal.getStartTaxAmt());
						initTaxAmtMap.put(scmInvBal.getOrgUnitNo() + "_" + scmInvBal.getWareHouseId(), initTaxAmt);
					} else {
						initTaxAmtMap.put(scmInvBal.getOrgUnitNo() + "_" + scmInvBal.getWareHouseId(),
								scmInvBal.getStartTaxAmt());
					}
				}
			}
		}
		// 获取入库数据
		Page page = new Page();
		page.setModelClass(ScmInvPurInWarehsBillEntry2.class);
		page.setShowCount(Integer.MAX_VALUE);
		if (StringUtils.isNotBlank(lot)) {
			page.getParam()
					.put(ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBillEntry2.class) + "."
							+ ScmInvPurInWarehsBillEntry2.FN_LOT,
							new QueryParam(
									ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBillEntry2.class) + "."
											+ ScmInvPurInWarehsBillEntry2.FN_LOT,
									QueryParam.QUERY_LIKE, "%" + lot + "%"));
		}
		page.getParam()
				.put(ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBillEntry2.class) + "."
						+ ScmInvPurInWarehsBillEntry2.FN_ITEMID,
						new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBillEntry2.class) + "."
								+ ScmInvPurInWarehsBillEntry2.FN_ITEMID, QueryParam.QUERY_EQ, materialName));
		page.getParam()
				.put(ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBillEntry2.class) + "."
						+ ScmInvPurInWarehsBillEntry2.FN_ORGUNITNO,
						new QueryParam(
								ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBillEntry2.class) + "."
										+ ScmInvPurInWarehsBillEntry2.FN_ORGUNITNO,
								QueryParam.QUERY_IN, orgUnitNos.toString()));
		if (StringUtils.isNotBlank(whName)) {
			page.getParam()
					.put(ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBillEntry2.class) + "."
							+ ScmInvPurInWarehsBillEntry2.FN_WAREHOUSEID,
							new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBillEntry2.class) + "."
									+ ScmInvPurInWarehsBillEntry2.FN_WAREHOUSEID, QueryParam.QUERY_EQ, whName));
		} else {
			page.getParam()
					.put(ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBillEntry2.class) + "."
							+ ScmInvPurInWarehsBillEntry2.FN_WAREHOUSEID,
							new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBillEntry2.class) + "."
									+ ScmInvPurInWarehsBillEntry2.FN_WAREHOUSEID, QueryParam.QUERY_GT, "0"));
		}
		page.getParam()
				.put(ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBill2.class) + "."
						+ ScmInvPurInWarehsBill2.FN_BIZDATE,
						new QueryParam(
								ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBill2.class) + "."
										+ ScmInvPurInWarehsBill2.FN_BIZDATE,
								QueryParam.QUERY_BETWEEN, FormatUtils.fmtDate(periodCalendar.getStartDate()), endDate));
		if (!StringUtils.equals("Y", status)) {
			page.getParam()
					.put(ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBill2.class) + "."
							+ ScmInvPurInWarehsBill2.FN_STATUS,
							new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBill2.class) + "."
									+ ScmInvPurInWarehsBill2.FN_STATUS, QueryParam.QUERY_EQ, "E"));
		}
		List<String> arglist = new ArrayList<>();
		arglist.add("controlUnitNo=" + currentControlUnitNo);
		List<ScmInvPurInWarehsBillEntry2> scmInvPurInWarehsBillEntryList = scmInvPurInWarehsBillEntryBiz.queryPage(page,
				arglist, "findAllPage", param);
		if (scmInvPurInWarehsBillEntryList != null && !scmInvPurInWarehsBillEntryList.isEmpty()) {
			for (ScmInvPurInWarehsBillEntry2 scmInvPurInWarehsBillEntry : scmInvPurInWarehsBillEntryList) {
				BigDecimal sideFlag = BigDecimal.ONE;
				if (StringUtils.contains("6,8", scmInvPurInWarehsBillEntry.getBizType())) {
					sideFlag = new BigDecimal("-1");
				}
				if (scmInvPurInWarehsBillEntry.getBizDate().compareTo(FormatUtils.parseDate(beginDate)) < 0) {
					// 比查询日期小的统计到期初数据中
					if (initQtyMap.containsKey(scmInvPurInWarehsBillEntry.getOrgUnitNo() + "_"
							+ scmInvPurInWarehsBillEntry.getWareHouseId())) {
						initQty = initQtyMap.get(scmInvPurInWarehsBillEntry.getOrgUnitNo() + "_"
								+ scmInvPurInWarehsBillEntry.getWareHouseId());
						initQty = initQty.add(sideFlag.multiply(scmInvPurInWarehsBillEntry.getQty()));
						initQtyMap.put(scmInvPurInWarehsBillEntry.getOrgUnitNo() + "_"
								+ scmInvPurInWarehsBillEntry.getWareHouseId(), initQty);
					} else {
						initQtyMap.put(
								scmInvPurInWarehsBillEntry.getOrgUnitNo() + "_"
										+ scmInvPurInWarehsBillEntry.getWareHouseId(),
								sideFlag.multiply(scmInvPurInWarehsBillEntry.getQty()));
					}
					if (initAmtMap.containsKey(scmInvPurInWarehsBillEntry.getOrgUnitNo() + "_"
							+ scmInvPurInWarehsBillEntry.getWareHouseId())) {
						initAmt = initAmtMap.get(scmInvPurInWarehsBillEntry.getOrgUnitNo() + "_"
								+ scmInvPurInWarehsBillEntry.getWareHouseId());
						initAmt = initAmt.add(sideFlag.multiply(scmInvPurInWarehsBillEntry.getAmt()));
						initAmtMap.put(scmInvPurInWarehsBillEntry.getOrgUnitNo() + "_"
								+ scmInvPurInWarehsBillEntry.getWareHouseId(), initAmt);
					} else {
						initAmtMap.put(
								scmInvPurInWarehsBillEntry.getOrgUnitNo() + "_"
										+ scmInvPurInWarehsBillEntry.getWareHouseId(),
								sideFlag.multiply(scmInvPurInWarehsBillEntry.getAmt()));
					}
					if (initTaxAmtMap.containsKey(scmInvPurInWarehsBillEntry.getOrgUnitNo() + "_"
							+ scmInvPurInWarehsBillEntry.getWareHouseId())) {
						initTaxAmt = initTaxAmtMap.get(scmInvPurInWarehsBillEntry.getOrgUnitNo() + "_"
								+ scmInvPurInWarehsBillEntry.getWareHouseId());
						initTaxAmt = initTaxAmt.add(sideFlag.multiply(scmInvPurInWarehsBillEntry.getTaxAmt()));
						initTaxAmtMap.put(scmInvPurInWarehsBillEntry.getOrgUnitNo() + "_"
								+ scmInvPurInWarehsBillEntry.getWareHouseId(), initTaxAmt);
					} else {
						initTaxAmtMap.put(
								scmInvPurInWarehsBillEntry.getOrgUnitNo() + "_"
										+ scmInvPurInWarehsBillEntry.getWareHouseId(),
								sideFlag.multiply(scmInvPurInWarehsBillEntry.getTaxAmt()));
					}
				} else {
					// 放入明细记录结果集中
					ScmInvItemInAndOutDetail scmInvItemInAndOutDetail = new ScmInvItemInAndOutDetail(true);
					scmInvItemInAndOutDetail.setOrgUnitNo(scmInvPurInWarehsBillEntry.getOrgUnitNo());
					scmInvItemInAndOutDetail.setWareHouseId(scmInvPurInWarehsBillEntry.getWareHouseId());
					scmInvItemInAndOutDetail.setBillNo(scmInvPurInWarehsBillEntry.getWrNo());
					Code code = codeBiz.selectByCategoryAndCode("warehouseStatus",
							scmInvPurInWarehsBillEntry.getStatus());
					if (code != null) {
						scmInvItemInAndOutDetail.setStatusName(code.getName());
					} else {
						scmInvItemInAndOutDetail.setStatusName(scmInvPurInWarehsBillEntry.getStatus());
					}
					scmInvItemInAndOutDetail.setBizDate(scmInvPurInWarehsBillEntry.getBizDate());
					scmInvItemInAndOutDetail.setPostDate(scmInvPurInWarehsBillEntry.getPostDate());
					code = codeBiz.selectByCategoryAndCode("wareHouseBizType", scmInvPurInWarehsBillEntry.getBizType());
					if (code != null) {
						scmInvItemInAndOutDetail.setBizTypeName(code.getName());
					} else {
						scmInvItemInAndOutDetail.setBizTypeName(scmInvPurInWarehsBillEntry.getBizType());
					}
					scmInvItemInAndOutDetail.setUnit(scmInvPurInWarehsBillEntry.getUnit());
					scmInvItemInAndOutDetail.setLot(scmInvPurInWarehsBillEntry.getLot());
					scmInvItemInAndOutDetail.setVendorId(scmInvPurInWarehsBillEntry.getVendorId());
					scmInvItemInAndOutDetail.setAddInQty(sideFlag.multiply(scmInvPurInWarehsBillEntry.getQty()));
					scmInvItemInAndOutDetail.setAddInAmt(sideFlag.multiply(scmInvPurInWarehsBillEntry.getAmt()));
					scmInvItemInAndOutDetail.setAddInTax(sideFlag.multiply(
							scmInvPurInWarehsBillEntry.getTaxAmt().subtract(scmInvPurInWarehsBillEntry.getAmt())));
					scmInvItemInAndOutDetail.setAddInTaxAmt(sideFlag.multiply(scmInvPurInWarehsBillEntry.getTaxAmt()));
					scmInvItemInAndOutDetailList.add(scmInvItemInAndOutDetail);
				}
			}
		}
		// 其他入库
		page = new Page();
		page.setModelClass(ScmInvOtherInWarehsBillEntry2.class);
		page.setShowCount(Integer.MAX_VALUE);
		if (StringUtils.isNotBlank(lot)) {
			page.getParam()
					.put(ClassUtils.getFinalModelSimpleName(ScmInvOtherInWarehsBillEntry2.class) + "."
							+ ScmInvOtherInWarehsBillEntry2.FN_LOT,
							new QueryParam(
									ClassUtils.getFinalModelSimpleName(ScmInvOtherInWarehsBillEntry2.class) + "."
											+ ScmInvOtherInWarehsBillEntry2.FN_LOT,
									QueryParam.QUERY_LIKE, "%" + lot + "%"));
		}
		page.getParam()
				.put(ClassUtils.getFinalModelSimpleName(ScmInvOtherInWarehsBillEntry2.class) + "."
						+ ScmInvOtherInWarehsBillEntry2.FN_ITEMID,
						new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvOtherInWarehsBillEntry2.class) + "."
								+ ScmInvOtherInWarehsBillEntry2.FN_ITEMID, QueryParam.QUERY_EQ, materialName));
		page.getParam()
				.put(ClassUtils.getFinalModelSimpleName(ScmInvOtherInWarehsBill.class) + "."
						+ ScmInvOtherInWarehsBill.FN_ORGUNITNO,
						new QueryParam(
								ClassUtils.getFinalModelSimpleName(ScmInvOtherInWarehsBill.class) + "."
										+ ScmInvOtherInWarehsBill.FN_ORGUNITNO,
								QueryParam.QUERY_IN, orgUnitNos.toString()));
		if (StringUtils.isNotBlank(whName)) {
			page.getParam()
					.put(ClassUtils.getFinalModelSimpleName(ScmInvOtherInWarehsBill.class) + "."
							+ ScmInvOtherInWarehsBill.FN_WAREHOUSEID,
							new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvOtherInWarehsBill.class) + "."
									+ ScmInvOtherInWarehsBill.FN_WAREHOUSEID, QueryParam.QUERY_EQ, whName));
		}
		page.getParam()
				.put(ClassUtils.getFinalModelSimpleName(ScmInvOtherInWarehsBill.class) + "."
						+ ScmInvOtherInWarehsBill.FN_BIZDATE,
						new QueryParam(
								ClassUtils.getFinalModelSimpleName(ScmInvOtherInWarehsBill.class) + "."
										+ ScmInvOtherInWarehsBill.FN_BIZDATE,
								QueryParam.QUERY_BETWEEN, FormatUtils.fmtDate(periodCalendar.getStartDate()), endDate));
		if (!StringUtils.equals("Y", status)) {
			page.getParam()
					.put(ClassUtils.getFinalModelSimpleName(ScmInvOtherInWarehsBill.class) + "."
							+ ScmInvOtherInWarehsBill.FN_STATUS,
							new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvOtherInWarehsBill.class) + "."
									+ ScmInvOtherInWarehsBill.FN_STATUS, QueryParam.QUERY_EQ, "E"));
		}
		List<ScmInvOtherInWarehsBillEntry2> scmInvOtherInWarehsBillEntryList = scmInvOtherInWarehsBillEntryBiz
				.queryPage(page, arglist, "findAllPage", param);
		if (scmInvOtherInWarehsBillEntryList != null && !scmInvOtherInWarehsBillEntryList.isEmpty()) {
			for (ScmInvOtherInWarehsBillEntry2 scmInvOtherInWarehsBillEntry : scmInvOtherInWarehsBillEntryList) {
				if (scmInvOtherInWarehsBillEntry.getBizDate().compareTo(FormatUtils.parseDate(beginDate)) < 0) {
					// 比查询日期小的统计到期初数据中
					if (initQtyMap.containsKey(scmInvOtherInWarehsBillEntry.getOrgUnitNo() + "_"
							+ scmInvOtherInWarehsBillEntry.getWareHouseId())) {
						initQty = initQtyMap.get(scmInvOtherInWarehsBillEntry.getOrgUnitNo() + "_"
								+ scmInvOtherInWarehsBillEntry.getWareHouseId());
						initQty = initQty.add(scmInvOtherInWarehsBillEntry.getQty());
						initQtyMap.put(scmInvOtherInWarehsBillEntry.getOrgUnitNo() + "_"
								+ scmInvOtherInWarehsBillEntry.getWareHouseId(), initQty);
					} else {
						initQtyMap.put(
								scmInvOtherInWarehsBillEntry.getOrgUnitNo() + "_"
										+ scmInvOtherInWarehsBillEntry.getWareHouseId(),
								scmInvOtherInWarehsBillEntry.getQty());
					}
					if (initAmtMap.containsKey(scmInvOtherInWarehsBillEntry.getOrgUnitNo() + "_"
							+ scmInvOtherInWarehsBillEntry.getWareHouseId())) {
						initAmt = initAmtMap.get(scmInvOtherInWarehsBillEntry.getOrgUnitNo() + "_"
								+ scmInvOtherInWarehsBillEntry.getWareHouseId());
						initAmt = initAmt.add(scmInvOtherInWarehsBillEntry.getAmt());
						initAmtMap.put(scmInvOtherInWarehsBillEntry.getOrgUnitNo() + "_"
								+ scmInvOtherInWarehsBillEntry.getWareHouseId(), initAmt);
					} else {
						initAmtMap.put(
								scmInvOtherInWarehsBillEntry.getOrgUnitNo() + "_"
										+ scmInvOtherInWarehsBillEntry.getWareHouseId(),
								scmInvOtherInWarehsBillEntry.getAmt());
					}
					if (initTaxAmtMap.containsKey(scmInvOtherInWarehsBillEntry.getOrgUnitNo() + "_"
							+ scmInvOtherInWarehsBillEntry.getWareHouseId())) {
						initTaxAmt = initTaxAmtMap.get(scmInvOtherInWarehsBillEntry.getOrgUnitNo() + "_"
								+ scmInvOtherInWarehsBillEntry.getWareHouseId());
						initTaxAmt = initTaxAmt.add(scmInvOtherInWarehsBillEntry.getTaxAmt());
						initTaxAmtMap.put(scmInvOtherInWarehsBillEntry.getOrgUnitNo() + "_"
								+ scmInvOtherInWarehsBillEntry.getWareHouseId(), initTaxAmt);
					} else {
						initTaxAmtMap.put(
								scmInvOtherInWarehsBillEntry.getOrgUnitNo() + "_"
										+ scmInvOtherInWarehsBillEntry.getWareHouseId(),
								scmInvOtherInWarehsBillEntry.getTaxAmt());
					}
				} else {
					// 放入明细记录结果集中
					ScmInvItemInAndOutDetail scmInvItemInAndOutDetail = new ScmInvItemInAndOutDetail(true);
					scmInvItemInAndOutDetail.setOrgUnitNo(scmInvOtherInWarehsBillEntry.getOrgUnitNo());
					scmInvItemInAndOutDetail.setWareHouseId(scmInvOtherInWarehsBillEntry.getWareHouseId());
					scmInvItemInAndOutDetail.setBillNo(scmInvOtherInWarehsBillEntry.getWrNo());
					Code code = codeBiz.selectByCategoryAndCode("warehouseStatus",
							scmInvOtherInWarehsBillEntry.getStatus());
					if (code != null) {
						scmInvItemInAndOutDetail.setStatusName(code.getName());
					} else {
						scmInvItemInAndOutDetail.setStatusName(scmInvOtherInWarehsBillEntry.getStatus());
					}
					scmInvItemInAndOutDetail.setBizDate(scmInvOtherInWarehsBillEntry.getBizDate());
					scmInvItemInAndOutDetail.setPostDate(scmInvOtherInWarehsBillEntry.getPostDate());
					code = codeBiz.selectByCategoryAndCode("otherWHBizType", scmInvOtherInWarehsBillEntry.getBizType());
					if (code != null) {
						scmInvItemInAndOutDetail.setBizTypeName(code.getName());
					} else {
						scmInvItemInAndOutDetail.setBizTypeName(scmInvOtherInWarehsBillEntry.getBizType());
					}
					scmInvItemInAndOutDetail.setUnit(scmInvOtherInWarehsBillEntry.getUnit());
					scmInvItemInAndOutDetail.setLot(scmInvOtherInWarehsBillEntry.getLot());
					if (StringUtils.equals("1", scmInvOtherInWarehsBillEntry.getBizType())) {
						// 盘盈
						scmInvItemInAndOutDetail.setProfitQty(scmInvOtherInWarehsBillEntry.getQty());
						scmInvItemInAndOutDetail.setProfitAmt(scmInvOtherInWarehsBillEntry.getAmt());
						scmInvItemInAndOutDetail.setProfitTax(scmInvOtherInWarehsBillEntry.getTaxAmt()
								.subtract(scmInvOtherInWarehsBillEntry.getAmt()));
						scmInvItemInAndOutDetail.setProfitTaxAmt(scmInvOtherInWarehsBillEntry.getTaxAmt());
					} else {
						scmInvItemInAndOutDetail.setAddInQty(scmInvOtherInWarehsBillEntry.getQty());
						scmInvItemInAndOutDetail.setAddInAmt(scmInvOtherInWarehsBillEntry.getAmt());
						scmInvItemInAndOutDetail.setAddInTax(scmInvOtherInWarehsBillEntry.getTaxAmt()
								.subtract(scmInvOtherInWarehsBillEntry.getAmt()));
						scmInvItemInAndOutDetail.setAddInTaxAmt(scmInvOtherInWarehsBillEntry.getTaxAmt());
					}
					scmInvItemInAndOutDetailList.add(scmInvItemInAndOutDetail);
				}
			}
		}

		// 获取领料出库数据
		page = new Page();
		page.setModelClass(ScmInvPurInWarehsBillEntry2.class);
		page.setShowCount(Integer.MAX_VALUE);
		if (StringUtils.isNotBlank(lot)) {
			page.getParam()
					.put(ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBillEntry2.class) + "."
							+ ScmInvMaterialReqBillEntry2.FN_LOT,
							new QueryParam(
									ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBillEntry2.class) + "."
											+ ScmInvMaterialReqBillEntry2.FN_LOT,
									QueryParam.QUERY_LIKE, "%" + lot + "%"));
		}
		page.getParam()
				.put(ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBillEntry2.class) + "."
						+ ScmInvMaterialReqBillEntry2.FN_ITEMID,
						new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBillEntry2.class) + "."
								+ ScmInvMaterialReqBillEntry2.FN_ITEMID, QueryParam.QUERY_EQ, materialName));
		page.getParam()
				.put(ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBill.class) + "."
						+ ScmInvMaterialReqBill.FN_ORGUNITNO,
						new QueryParam(
								ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBill.class) + "."
										+ ScmInvMaterialReqBill.FN_ORGUNITNO,
								QueryParam.QUERY_IN, orgUnitNos.toString()));
		if (StringUtils.isNotBlank(whName)) {
			page.getParam()
					.put(ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBill.class) + "."
							+ ScmInvMaterialReqBill.FN_WAREHOUSEID,
							new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBill.class) + "."
									+ ScmInvMaterialReqBill.FN_WAREHOUSEID, QueryParam.QUERY_EQ, whName));
		}
		page.getParam()
				.put(ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBill2.class) + "."
						+ ScmInvMaterialReqBill2.FN_BIZDATE,
						new QueryParam(
								ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBill2.class) + "."
										+ ScmInvMaterialReqBill2.FN_BIZDATE,
								QueryParam.QUERY_BETWEEN, FormatUtils.fmtDate(periodCalendar.getStartDate()), endDate));
		if (!StringUtils.equals("Y", status)) {
			page.getParam()
					.put(ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBill2.class) + "."
							+ ScmInvMaterialReqBill2.FN_STATUS,
							new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBill2.class) + "."
									+ ScmInvMaterialReqBill2.FN_STATUS, QueryParam.QUERY_EQ, "E"));
		}
		List<ScmInvMaterialReqBillEntry2> scmInvMaterialReqBillEntryList = scmInvMaterialReqBillEntryBiz.queryPage(page,
				arglist, "findAllPage", param);
		if (scmInvMaterialReqBillEntryList != null && !scmInvMaterialReqBillEntryList.isEmpty()) {
			for (ScmInvMaterialReqBillEntry2 scmInvMaterialReqBillEntry : scmInvMaterialReqBillEntryList) {
				BigDecimal sideFlag = BigDecimal.ONE;
				if (StringUtils.equals("2", scmInvMaterialReqBillEntry.getBizType())) {
					sideFlag = new BigDecimal("-1"); // 领料退库
				}
				if (scmInvMaterialReqBillEntry.getBizDate().compareTo(FormatUtils.parseDate(beginDate)) < 0) {
					// 比查询日期小的统计到期初数据中
					if (initQtyMap.containsKey(scmInvMaterialReqBillEntry.getOrgUnitNo() + "_"
							+ scmInvMaterialReqBillEntry.getWareHouseId())) {
						initQty = initQtyMap.get(scmInvMaterialReqBillEntry.getOrgUnitNo() + "_"
								+ scmInvMaterialReqBillEntry.getWareHouseId());
						initQty = initQty.subtract(sideFlag.multiply(scmInvMaterialReqBillEntry.getQty()));
						initQtyMap.put(scmInvMaterialReqBillEntry.getOrgUnitNo() + "_"
								+ scmInvMaterialReqBillEntry.getWareHouseId(), initQty);
					} else {
						initQtyMap.put(
								scmInvMaterialReqBillEntry.getOrgUnitNo() + "_"
										+ scmInvMaterialReqBillEntry.getWareHouseId(),
								BigDecimal.ZERO.subtract(sideFlag.multiply(scmInvMaterialReqBillEntry.getQty())));
					}
					if (initAmtMap.containsKey(scmInvMaterialReqBillEntry.getOrgUnitNo() + "_"
							+ scmInvMaterialReqBillEntry.getWareHouseId())) {
						initAmt = initAmtMap.get(scmInvMaterialReqBillEntry.getOrgUnitNo() + "_"
								+ scmInvMaterialReqBillEntry.getWareHouseId());
						initAmt = initAmt.subtract(sideFlag.multiply(scmInvMaterialReqBillEntry.getAmt()));
						initAmtMap.put(scmInvMaterialReqBillEntry.getOrgUnitNo() + "_"
								+ scmInvMaterialReqBillEntry.getWareHouseId(), initAmt);
					} else {
						initAmtMap.put(
								scmInvMaterialReqBillEntry.getOrgUnitNo() + "_"
										+ scmInvMaterialReqBillEntry.getWareHouseId(),
								BigDecimal.ZERO.subtract(sideFlag.multiply(scmInvMaterialReqBillEntry.getAmt())));
					}
					if (initTaxAmtMap.containsKey(scmInvMaterialReqBillEntry.getOrgUnitNo() + "_"
							+ scmInvMaterialReqBillEntry.getWareHouseId())) {
						initTaxAmt = initTaxAmtMap.get(scmInvMaterialReqBillEntry.getOrgUnitNo() + "_"
								+ scmInvMaterialReqBillEntry.getWareHouseId());
						initTaxAmt = initTaxAmt.subtract(sideFlag.multiply(scmInvMaterialReqBillEntry.getTaxAmt()));
						initTaxAmtMap.put(scmInvMaterialReqBillEntry.getOrgUnitNo() + "_"
								+ scmInvMaterialReqBillEntry.getWareHouseId(), initTaxAmt);
					} else {
						initTaxAmtMap.put(
								scmInvMaterialReqBillEntry.getOrgUnitNo() + "_"
										+ scmInvMaterialReqBillEntry.getWareHouseId(),
								BigDecimal.ZERO.subtract(sideFlag.multiply(scmInvMaterialReqBillEntry.getTaxAmt())));
					}
				} else {
					// 放入明细记录结果集中
					ScmInvItemInAndOutDetail scmInvItemInAndOutDetail = new ScmInvItemInAndOutDetail(true);
					scmInvItemInAndOutDetail.setOrgUnitNo(scmInvMaterialReqBillEntry.getOrgUnitNo());
					scmInvItemInAndOutDetail.setWareHouseId(scmInvMaterialReqBillEntry.getWareHouseId());
					scmInvItemInAndOutDetail.setBillNo(scmInvMaterialReqBillEntry.getOtNo());
					Code code = codeBiz.selectByCategoryAndCode("warehouseStatus",
							scmInvMaterialReqBillEntry.getStatus());
					if (code != null) {
						scmInvItemInAndOutDetail.setStatusName(code.getName());
					} else {
						scmInvItemInAndOutDetail.setStatusName(scmInvMaterialReqBillEntry.getStatus());
					}
					scmInvItemInAndOutDetail.setBizDate(scmInvMaterialReqBillEntry.getBizDate());
					scmInvItemInAndOutDetail.setPostDate(scmInvMaterialReqBillEntry.getPostDate());
					code = codeBiz.selectByCategoryAndCode("collectWHBizType", scmInvMaterialReqBillEntry.getBizType());
					if (code != null) {
						scmInvItemInAndOutDetail.setBizTypeName(code.getName());
					} else {
						scmInvItemInAndOutDetail.setBizTypeName(scmInvMaterialReqBillEntry.getBizType());
					}
					scmInvItemInAndOutDetail.setUnit(scmInvMaterialReqBillEntry.getUnit());
					scmInvItemInAndOutDetail.setLot(scmInvMaterialReqBillEntry.getLot());
					scmInvItemInAndOutDetail.setReqQty(sideFlag.multiply(scmInvMaterialReqBillEntry.getQty()));
					scmInvItemInAndOutDetail.setReqAmt(sideFlag.multiply(scmInvMaterialReqBillEntry.getAmt()));
					scmInvItemInAndOutDetail.setReqTax(sideFlag.multiply(
							scmInvMaterialReqBillEntry.getTaxAmt().subtract(scmInvMaterialReqBillEntry.getAmt())));
					scmInvItemInAndOutDetail.setReqTaxAmt(sideFlag.multiply(scmInvMaterialReqBillEntry.getTaxAmt()));
					scmInvItemInAndOutDetailList.add(scmInvItemInAndOutDetail);
				}
			}
		}
		// 销售出库
		page = new Page();
		page.setModelClass(ScmInvSaleIssueBillEntry2.class);
		page.setShowCount(Integer.MAX_VALUE);
		if (StringUtils.isNotBlank(lot)) {
			page.getParam()
					.put(ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBillEntry2.class) + "."
							+ ScmInvSaleIssueBillEntry2.FN_LOT,
							new QueryParam(
									ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBillEntry2.class) + "."
											+ ScmInvSaleIssueBillEntry2.FN_LOT,
									QueryParam.QUERY_LIKE, "%" + lot + "%"));
		}
		page.getParam()
				.put(ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBillEntry2.class) + "."
						+ ScmInvSaleIssueBillEntry2.FN_ITEMID,
						new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBillEntry2.class) + "."
								+ ScmInvSaleIssueBillEntry2.FN_ITEMID, QueryParam.QUERY_EQ, materialName));
		page.getParam()
				.put(ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBillEntry2.class) + "."
						+ ScmInvSaleIssueBillEntry2.FN_ORGUNITNO,
						new QueryParam(
								ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBillEntry2.class) + "."
										+ ScmInvSaleIssueBillEntry2.FN_ORGUNITNO,
								QueryParam.QUERY_IN, orgUnitNos.toString()));
		if (StringUtils.isNotBlank(whName)) {
			page.getParam()
					.put(ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBillEntry2.class) + "."
							+ ScmInvSaleIssueBillEntry2.FN_WAREHOUSEID,
							new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBillEntry2.class) + "."
									+ ScmInvSaleIssueBillEntry2.FN_WAREHOUSEID, QueryParam.QUERY_EQ, whName));
		} else {
			page.getParam()
					.put(ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBillEntry2.class) + "."
							+ ScmInvSaleIssueBillEntry2.FN_WAREHOUSEID,
							new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBillEntry2.class) + "."
									+ ScmInvSaleIssueBillEntry2.FN_WAREHOUSEID, QueryParam.QUERY_GT, "0"));
		}
		page.getParam().put(
				ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBill.class) + "." + ScmInvSaleIssueBill.FN_BIZDATE,
				new QueryParam(
						ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBill.class) + "."
								+ ScmInvSaleIssueBill.FN_BIZDATE,
						QueryParam.QUERY_BETWEEN, FormatUtils.fmtDate(periodCalendar.getStartDate()), endDate));
		if (!StringUtils.equals("Y", status)) {
			page.getParam().put(
					ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBill.class) + "." + ScmInvSaleIssueBill.FN_STATUS,
					new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBill.class) + "."
							+ ScmInvSaleIssueBill.FN_STATUS, QueryParam.QUERY_EQ, "E"));
		}
		List<ScmInvSaleIssueBillEntry2> scmInvSaleIssueBillEntryList = scmInvSaleIssueBillEntryBiz.queryPage(page,
				arglist, "findAllPage", param);
		if (scmInvSaleIssueBillEntryList != null && !scmInvSaleIssueBillEntryList.isEmpty()) {
			for (ScmInvSaleIssueBillEntry2 scmInvSaleIssueBillEntry : scmInvSaleIssueBillEntryList) {
				BigDecimal sideFlag = BigDecimal.ONE;
				if (StringUtils.contains("6,7,8", scmInvSaleIssueBillEntry.getBizType())) {
					sideFlag = new BigDecimal("-1");
				}
				if (scmInvSaleIssueBillEntry.getBizDate().compareTo(FormatUtils.parseDate(beginDate)) < 0) {
					// 比查询日期小的统计到期初数据中
					if (initQtyMap.containsKey(scmInvSaleIssueBillEntry.getOrgUnitNo() + "_"
							+ scmInvSaleIssueBillEntry.getWareHouseId())) {
						initQty = initQtyMap.get(scmInvSaleIssueBillEntry.getOrgUnitNo() + "_"
								+ scmInvSaleIssueBillEntry.getWareHouseId());
						initQty = initQty.subtract(sideFlag.multiply(scmInvSaleIssueBillEntry.getQty()));
						initQtyMap.put(scmInvSaleIssueBillEntry.getOrgUnitNo() + "_"
								+ scmInvSaleIssueBillEntry.getWareHouseId(), initQty);
					} else {
						initQtyMap.put(
								scmInvSaleIssueBillEntry.getOrgUnitNo() + "_"
										+ scmInvSaleIssueBillEntry.getWareHouseId(),
								BigDecimal.ZERO.subtract(sideFlag.multiply(scmInvSaleIssueBillEntry.getQty())));
					}
					if (initAmtMap.containsKey(scmInvSaleIssueBillEntry.getOrgUnitNo() + "_"
							+ scmInvSaleIssueBillEntry.getWareHouseId())) {
						initAmt = initAmtMap.get(scmInvSaleIssueBillEntry.getOrgUnitNo() + "_"
								+ scmInvSaleIssueBillEntry.getWareHouseId());
						initAmt = initAmt.subtract(sideFlag.multiply(scmInvSaleIssueBillEntry.getTaxAmt().divide(
								BigDecimal.ONE.subtract(scmInvSaleIssueBillEntry.getTaxRate()), 2,
								RoundingMode.HALF_UP)));
						initAmtMap.put(scmInvSaleIssueBillEntry.getOrgUnitNo() + "_"
								+ scmInvSaleIssueBillEntry.getWareHouseId(), initAmt);
					} else {
						initAmtMap.put(
								scmInvSaleIssueBillEntry.getOrgUnitNo() + "_"
										+ scmInvSaleIssueBillEntry.getWareHouseId(),
								BigDecimal.ZERO.subtract(sideFlag.multiply(scmInvSaleIssueBillEntry.getTaxAmt().divide(
										BigDecimal.ONE.subtract(scmInvSaleIssueBillEntry.getTaxRate()), 2,
										RoundingMode.HALF_UP))));
					}
					if (initTaxAmtMap.containsKey(scmInvSaleIssueBillEntry.getOrgUnitNo() + "_"
							+ scmInvSaleIssueBillEntry.getWareHouseId())) {
						initTaxAmt = initTaxAmtMap.get(scmInvSaleIssueBillEntry.getOrgUnitNo() + "_"
								+ scmInvSaleIssueBillEntry.getWareHouseId());
						initTaxAmt = initTaxAmt.subtract(sideFlag.multiply(scmInvSaleIssueBillEntry.getTaxAmt()));
						initTaxAmtMap.put(scmInvSaleIssueBillEntry.getOrgUnitNo() + "_"
								+ scmInvSaleIssueBillEntry.getWareHouseId(), initTaxAmt);
					} else {
						initTaxAmtMap.put(
								scmInvSaleIssueBillEntry.getOrgUnitNo() + "_"
										+ scmInvSaleIssueBillEntry.getWareHouseId(),
								BigDecimal.ZERO.subtract(sideFlag.multiply(scmInvSaleIssueBillEntry.getTaxAmt())));
					}
				} else {
					// 放入明细记录结果集中
					ScmInvItemInAndOutDetail scmInvItemInAndOutDetail = new ScmInvItemInAndOutDetail(true);
					scmInvItemInAndOutDetail.setOrgUnitNo(scmInvSaleIssueBillEntry.getOrgUnitNo());
					scmInvItemInAndOutDetail.setWareHouseId(scmInvSaleIssueBillEntry.getWareHouseId());
					scmInvItemInAndOutDetail.setBillNo(scmInvSaleIssueBillEntry.getOtNo());
					Code code = codeBiz.selectByCategoryAndCode("warehouseStatus",
							scmInvSaleIssueBillEntry.getStatus());
					if (code != null) {
						scmInvItemInAndOutDetail.setStatusName(code.getName());
					} else {
						scmInvItemInAndOutDetail.setStatusName(scmInvSaleIssueBillEntry.getStatus());
					}
					scmInvItemInAndOutDetail.setBizDate(scmInvSaleIssueBillEntry.getBizDate());
					scmInvItemInAndOutDetail.setPostDate(scmInvSaleIssueBillEntry.getPostDate());
					code = codeBiz.selectByCategoryAndCode("saleOWBizType", scmInvSaleIssueBillEntry.getBizType());
					if (code != null) {
						scmInvItemInAndOutDetail.setBizTypeName(code.getName());
					} else {
						scmInvItemInAndOutDetail.setBizTypeName(scmInvSaleIssueBillEntry.getBizType());
					}
					scmInvItemInAndOutDetail.setUnit(scmInvSaleIssueBillEntry.getUnit());
					scmInvItemInAndOutDetail.setLot(scmInvSaleIssueBillEntry.getLot());
					scmInvItemInAndOutDetail.setOutQty(sideFlag.multiply(scmInvSaleIssueBillEntry.getQty()));
					scmInvItemInAndOutDetail.setOutAmt(sideFlag.multiply(scmInvSaleIssueBillEntry.getAmt()));
					scmInvItemInAndOutDetail.setOutTax(sideFlag.multiply(
							scmInvSaleIssueBillEntry.getTaxAmt().subtract(scmInvSaleIssueBillEntry.getAmt())));
					scmInvItemInAndOutDetail.setOutTaxAmt(sideFlag.multiply(scmInvSaleIssueBillEntry.getTaxAmt()));
					scmInvItemInAndOutDetailList.add(scmInvItemInAndOutDetail);
				}
			}
		}
		// 其他出库
		page = new Page();
		page.setModelClass(ScmInvOtherIssueBillEntry2.class);
		page.setShowCount(Integer.MAX_VALUE);
		if (StringUtils.isNotBlank(lot)) {
			page.getParam()
					.put(ClassUtils.getFinalModelSimpleName(ScmInvOtherIssueBillEntry2.class) + "."
							+ ScmInvOtherIssueBillEntry2.FN_LOT,
							new QueryParam(
									ClassUtils.getFinalModelSimpleName(ScmInvOtherIssueBillEntry2.class) + "."
											+ ScmInvOtherIssueBillEntry2.FN_LOT,
									QueryParam.QUERY_LIKE, "%" + lot + "%"));
		}
		page.getParam()
				.put(ClassUtils.getFinalModelSimpleName(ScmInvOtherIssueBillEntry2.class) + "."
						+ ScmInvOtherIssueBillEntry2.FN_ITEMID,
						new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvOtherIssueBillEntry2.class) + "."
								+ ScmInvOtherIssueBillEntry2.FN_ITEMID, QueryParam.QUERY_EQ, materialName));
		page.getParam()
				.put(ClassUtils.getFinalModelSimpleName(ScmInvOtherIssueBillEntry2.class) + "."
						+ ScmInvOtherIssueBillEntry2.FN_ORGUNITNO,
						new QueryParam(
								ClassUtils.getFinalModelSimpleName(ScmInvOtherIssueBillEntry2.class) + "."
										+ ScmInvOtherIssueBillEntry2.FN_ORGUNITNO,
								QueryParam.QUERY_IN, orgUnitNos.toString()));
		if (StringUtils.isNotBlank(whName)) {
			page.getParam()
					.put(ClassUtils.getFinalModelSimpleName(ScmInvOtherIssueBillEntry2.class) + "."
							+ ScmInvOtherIssueBillEntry2.FN_WAREHOUSEID,
							new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvOtherIssueBillEntry2.class) + "."
									+ ScmInvOtherIssueBillEntry2.FN_WAREHOUSEID, QueryParam.QUERY_EQ, whName));
		}
		page.getParam().put(
				ClassUtils.getFinalModelSimpleName(ScmInvOtherIssueBill.class) + "." + ScmInvOtherIssueBill.FN_BIZDATE,
				new QueryParam(
						ClassUtils.getFinalModelSimpleName(ScmInvOtherIssueBill.class) + "."
								+ ScmInvOtherIssueBill.FN_BIZDATE,
						QueryParam.QUERY_BETWEEN, FormatUtils.fmtDate(periodCalendar.getStartDate()), endDate));
		if (!StringUtils.equals("Y", status)) {
			page.getParam()
					.put(ClassUtils.getFinalModelSimpleName(ScmInvOtherIssueBill.class) + "."
							+ ScmInvOtherIssueBill.FN_STATUS,
							new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvOtherIssueBill.class) + "."
									+ ScmInvOtherIssueBill.FN_STATUS, QueryParam.QUERY_EQ, "E"));
		}
		List<ScmInvOtherIssueBillEntry2> scmInvOtherIssueBillEntryList = scmInvOtherIssueBillEntryBiz.queryPage(page,
				arglist, "findAllPage", param);
		if (scmInvOtherIssueBillEntryList != null && !scmInvOtherIssueBillEntryList.isEmpty()) {
			for (ScmInvOtherIssueBillEntry2 scmInvOtherIssueBillEntry : scmInvOtherIssueBillEntryList) {
				if (scmInvOtherIssueBillEntry.getBizDate().compareTo(FormatUtils.parseDate(beginDate)) < 0) {
					// 比查询日期小的统计到期初数据中
					if (initQtyMap.containsKey(scmInvOtherIssueBillEntry.getOrgUnitNo() + "_"
							+ scmInvOtherIssueBillEntry.getWareHouseId())) {
						initQty = initQtyMap.get(scmInvOtherIssueBillEntry.getOrgUnitNo() + "_"
								+ scmInvOtherIssueBillEntry.getWareHouseId());
						initQty = initQty.subtract(scmInvOtherIssueBillEntry.getQty());
						initQtyMap.put(scmInvOtherIssueBillEntry.getOrgUnitNo() + "_"
								+ scmInvOtherIssueBillEntry.getWareHouseId(), initQty);
					} else {
						initQtyMap.put(
								scmInvOtherIssueBillEntry.getOrgUnitNo() + "_"
										+ scmInvOtherIssueBillEntry.getWareHouseId(),
								BigDecimal.ZERO.subtract(scmInvOtherIssueBillEntry.getQty()));
					}
					if (initAmtMap.containsKey(scmInvOtherIssueBillEntry.getOrgUnitNo() + "_"
							+ scmInvOtherIssueBillEntry.getWareHouseId())) {
						initAmt = initAmtMap.get(scmInvOtherIssueBillEntry.getOrgUnitNo() + "_"
								+ scmInvOtherIssueBillEntry.getWareHouseId());
						initAmt = initAmt.subtract(scmInvOtherIssueBillEntry.getAmt());
						initAmtMap.put(scmInvOtherIssueBillEntry.getOrgUnitNo() + "_"
								+ scmInvOtherIssueBillEntry.getWareHouseId(), initAmt);
					} else {
						initAmtMap.put(
								scmInvOtherIssueBillEntry.getOrgUnitNo() + "_"
										+ scmInvOtherIssueBillEntry.getWareHouseId(),
								BigDecimal.ZERO.subtract(scmInvOtherIssueBillEntry.getAmt()));
					}
					if (initTaxAmtMap.containsKey(scmInvOtherIssueBillEntry.getOrgUnitNo() + "_"
							+ scmInvOtherIssueBillEntry.getWareHouseId())) {
						initTaxAmt = initTaxAmtMap.get(scmInvOtherIssueBillEntry.getOrgUnitNo() + "_"
								+ scmInvOtherIssueBillEntry.getWareHouseId());
						initTaxAmt = initTaxAmt.subtract(scmInvOtherIssueBillEntry.getTaxAmt());
						initTaxAmtMap.put(scmInvOtherIssueBillEntry.getOrgUnitNo() + "_"
								+ scmInvOtherIssueBillEntry.getWareHouseId(), initTaxAmt);
					} else {
						initTaxAmtMap.put(
								scmInvOtherIssueBillEntry.getOrgUnitNo() + "_"
										+ scmInvOtherIssueBillEntry.getWareHouseId(),
								BigDecimal.ZERO.subtract(scmInvOtherIssueBillEntry.getTaxAmt()));
					}
				} else {
					// 放入明细记录结果集中
					ScmInvItemInAndOutDetail scmInvItemInAndOutDetail = new ScmInvItemInAndOutDetail(true);
					scmInvItemInAndOutDetail.setOrgUnitNo(scmInvOtherIssueBillEntry.getOrgUnitNo());
					scmInvItemInAndOutDetail.setWareHouseId(scmInvOtherIssueBillEntry.getWareHouseId());
					scmInvItemInAndOutDetail.setBillNo(scmInvOtherIssueBillEntry.getOtNo());
					Code code = codeBiz.selectByCategoryAndCode("warehouseStatus",
							scmInvOtherIssueBillEntry.getStatus());
					if (code != null) {
						scmInvItemInAndOutDetail.setStatusName(code.getName());
					} else {
						scmInvItemInAndOutDetail.setStatusName(scmInvOtherIssueBillEntry.getStatus());
					}
					scmInvItemInAndOutDetail.setBizDate(scmInvOtherIssueBillEntry.getBizDate());
					scmInvItemInAndOutDetail.setPostDate(scmInvOtherIssueBillEntry.getPostDate());
					code = codeBiz.selectByCategoryAndCode("devoteWHBizType", scmInvOtherIssueBillEntry.getBizType());
					if (code != null) {
						scmInvItemInAndOutDetail.setBizTypeName(code.getName());
					} else {
						scmInvItemInAndOutDetail.setBizTypeName(scmInvOtherIssueBillEntry.getBizType());
					}
					scmInvItemInAndOutDetail.setUnit(scmInvOtherIssueBillEntry.getUnit());
					scmInvItemInAndOutDetail.setLot(scmInvOtherIssueBillEntry.getLot());
					if (StringUtils.equals("1", scmInvOtherIssueBillEntry.getBizType())) {
						// 盘亏
						scmInvItemInAndOutDetail
								.setProfitQty(BigDecimal.ZERO.subtract(scmInvOtherIssueBillEntry.getQty()));
						scmInvItemInAndOutDetail
								.setProfitAmt(BigDecimal.ZERO.subtract(scmInvOtherIssueBillEntry.getAmt()));
						scmInvItemInAndOutDetail.setProfitTax(BigDecimal.ZERO.subtract(
								scmInvOtherIssueBillEntry.getTaxAmt().subtract(scmInvOtherIssueBillEntry.getAmt())));
						scmInvItemInAndOutDetail
								.setProfitTaxAmt(BigDecimal.ZERO.subtract(scmInvOtherIssueBillEntry.getTaxAmt()));
					} else if (StringUtils.equals("2", scmInvOtherIssueBillEntry.getBizType())) {
						scmInvItemInAndOutDetail.setScrapQty(scmInvOtherIssueBillEntry.getQty());
						scmInvItemInAndOutDetail.setScrapAmt(scmInvOtherIssueBillEntry.getAmt());
						scmInvItemInAndOutDetail.setScrapTax(
								scmInvOtherIssueBillEntry.getTaxAmt().subtract(scmInvOtherIssueBillEntry.getAmt()));
						scmInvItemInAndOutDetail.setScrapTaxAmt(scmInvOtherIssueBillEntry.getTaxAmt());
					} else {
						scmInvItemInAndOutDetail.setOutQty(scmInvOtherIssueBillEntry.getQty());
						scmInvItemInAndOutDetail.setOutAmt(scmInvOtherIssueBillEntry.getAmt());
						scmInvItemInAndOutDetail.setOutTax(
								scmInvOtherIssueBillEntry.getTaxAmt().subtract(scmInvOtherIssueBillEntry.getAmt()));
						scmInvItemInAndOutDetail.setOutTaxAmt(scmInvOtherIssueBillEntry.getTaxAmt());
					}
					scmInvItemInAndOutDetailList.add(scmInvItemInAndOutDetail);
				}
			}
		}

		// 调拨入库
		page = new Page();
		page.setModelClass(ScmInvMoveInWarehsBillEntry2.class);
		page.setShowCount(Integer.MAX_VALUE);
		if (StringUtils.isNotBlank(lot)) {
			page.getParam()
					.put(ClassUtils.getFinalModelSimpleName(ScmInvMoveInWarehsBillEntry2.class) + "."
							+ ScmInvMoveInWarehsBillEntry2.FN_LOT,
							new QueryParam(
									ClassUtils.getFinalModelSimpleName(ScmInvMoveInWarehsBillEntry2.class) + "."
											+ ScmInvMoveInWarehsBillEntry2.FN_LOT,
									QueryParam.QUERY_LIKE, "%" + lot + "%"));
		}
		page.getParam()
				.put(ClassUtils.getFinalModelSimpleName(ScmInvMoveInWarehsBillEntry2.class) + "."
						+ ScmInvMoveInWarehsBillEntry2.FN_ITEMID,
						new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMoveInWarehsBillEntry2.class) + "."
								+ ScmInvMoveInWarehsBillEntry2.FN_ITEMID, QueryParam.QUERY_EQ, materialName));
		page.getParam()
				.put(ClassUtils.getFinalModelSimpleName(ScmInvMoveInWarehsBillEntry2.class) + "."
						+ ScmInvMoveInWarehsBillEntry2.FN_ORGUNITNO,
						new QueryParam(
								ClassUtils.getFinalModelSimpleName(ScmInvMoveInWarehsBillEntry2.class) + "."
										+ ScmInvMoveInWarehsBillEntry2.FN_ORGUNITNO,
								QueryParam.QUERY_IN, orgUnitNos.toString()));
		if (StringUtils.isNotBlank(whName)) {
			page.getParam()
					.put(ClassUtils.getFinalModelSimpleName(ScmInvMoveInWarehsBillEntry2.class) + "."
							+ ScmInvMoveInWarehsBillEntry2.FN_WAREHOUSEID,
							new QueryParam(
									ClassUtils.getFinalModelSimpleName(ScmInvMoveInWarehsBillEntry2.class) + "."
											+ ScmInvMoveInWarehsBillEntry2.FN_WAREHOUSEID,
									QueryParam.QUERY_EQ, whName));
		}
		page.getParam()
				.put(ClassUtils.getFinalModelSimpleName(ScmInvMoveInWarehsBill.class) + "."
						+ ScmInvMoveInWarehsBill.FN_BIZDATE,
						new QueryParam(
								ClassUtils.getFinalModelSimpleName(ScmInvMoveInWarehsBill.class) + "."
										+ ScmInvMoveInWarehsBill.FN_BIZDATE,
								QueryParam.QUERY_BETWEEN, FormatUtils.fmtDate(periodCalendar.getStartDate()), endDate));
		if (!StringUtils.equals("Y", status)) {
			page.getParam()
					.put(ClassUtils.getFinalModelSimpleName(ScmInvMoveInWarehsBill.class) + "."
							+ ScmInvMoveInWarehsBill.FN_STATUS,
							new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMoveInWarehsBill.class) + "."
									+ ScmInvMoveInWarehsBill.FN_STATUS, QueryParam.QUERY_EQ, "E"));
		}
		List<ScmInvMoveInWarehsBillEntry2> scmInvMoveInWarehsBillEntryList = scmInvMoveInWarehsBillEntryBiz
				.queryPage(page, arglist, "findAllPage", param);
		if (scmInvMoveInWarehsBillEntryList != null && !scmInvMoveInWarehsBillEntryList.isEmpty()) {
			for (ScmInvMoveInWarehsBillEntry2 scmInvMoveInWarehsBillEntry : scmInvMoveInWarehsBillEntryList) {
				if (scmInvMoveInWarehsBillEntry.getBizDate().compareTo(FormatUtils.parseDate(beginDate)) < 0) {
					// 比查询日期小的统计到期初数据中，并从List中删除
					if (initQtyMap.containsKey(scmInvMoveInWarehsBillEntry.getOrgUnitNo() + "_"
							+ scmInvMoveInWarehsBillEntry.getWareHouseId())) {
						initQty = initQtyMap.get(scmInvMoveInWarehsBillEntry.getOrgUnitNo() + "_"
								+ scmInvMoveInWarehsBillEntry.getWareHouseId());
						initQty = initQty.add(scmInvMoveInWarehsBillEntry.getQty());
						initQtyMap.put(scmInvMoveInWarehsBillEntry.getOrgUnitNo() + "_"
								+ scmInvMoveInWarehsBillEntry.getWareHouseId(), initQty);
					} else {
						initQtyMap.put(
								scmInvMoveInWarehsBillEntry.getOrgUnitNo() + "_"
										+ scmInvMoveInWarehsBillEntry.getWareHouseId(),
								scmInvMoveInWarehsBillEntry.getQty());
					}
					if (initAmtMap.containsKey(scmInvMoveInWarehsBillEntry.getOrgUnitNo() + "_"
							+ scmInvMoveInWarehsBillEntry.getUseOrgUnitNo())) {
						initAmt = initAmtMap.get(scmInvMoveInWarehsBillEntry.getOrgUnitNo() + "_"
								+ scmInvMoveInWarehsBillEntry.getWareHouseId());
						initAmt = initAmt.add(scmInvMoveInWarehsBillEntry.getAmt());
						initAmtMap.put(scmInvMoveInWarehsBillEntry.getOrgUnitNo() + "_"
								+ scmInvMoveInWarehsBillEntry.getWareHouseId(), initAmt);
					} else {
						initAmtMap.put(
								scmInvMoveInWarehsBillEntry.getOrgUnitNo() + "_"
										+ scmInvMoveInWarehsBillEntry.getWareHouseId(),
								scmInvMoveInWarehsBillEntry.getAmt());
					}
					if (initTaxAmtMap.containsKey(scmInvMoveInWarehsBillEntry.getOrgUnitNo() + "_"
							+ scmInvMoveInWarehsBillEntry.getUseOrgUnitNo())) {
						initTaxAmt = initTaxAmtMap.get(scmInvMoveInWarehsBillEntry.getOrgUnitNo() + "_"
								+ scmInvMoveInWarehsBillEntry.getWareHouseId());
						initTaxAmt = initTaxAmt.add(scmInvMoveInWarehsBillEntry.getTaxAmt());
						initTaxAmtMap.put(scmInvMoveInWarehsBillEntry.getOrgUnitNo() + "_"
								+ scmInvMoveInWarehsBillEntry.getWareHouseId(), initTaxAmt);
					} else {
						initTaxAmtMap.put(
								scmInvMoveInWarehsBillEntry.getOrgUnitNo() + "_"
										+ scmInvMoveInWarehsBillEntry.getWareHouseId(),
								scmInvMoveInWarehsBillEntry.getTaxAmt());
					}
				} else {
					// 放入明细记录结果集中
					ScmInvItemInAndOutDetail scmInvItemInAndOutDetail = new ScmInvItemInAndOutDetail(true);
					scmInvItemInAndOutDetail.setOrgUnitNo(scmInvMoveInWarehsBillEntry.getOrgUnitNo());
					scmInvItemInAndOutDetail.setWareHouseId(scmInvMoveInWarehsBillEntry.getWareHouseId());
					scmInvItemInAndOutDetail.setBillNo(scmInvMoveInWarehsBillEntry.getWrNo());
					Code code = codeBiz.selectByCategoryAndCode("warehouseStatus",
							scmInvMoveInWarehsBillEntry.getStatus());
					if (code != null) {
						scmInvItemInAndOutDetail.setStatusName(code.getName());
					} else {
						scmInvItemInAndOutDetail.setStatusName(scmInvMoveInWarehsBillEntry.getStatus());
					}
					scmInvItemInAndOutDetail.setBizDate(scmInvMoveInWarehsBillEntry.getBizDate());
					scmInvItemInAndOutDetail.setPostDate(scmInvMoveInWarehsBillEntry.getPostDate());
					code = codeBiz.selectByCategoryAndCode("wareHouseBizType",
							scmInvMoveInWarehsBillEntry.getBizType());
					if (code != null) {
						scmInvItemInAndOutDetail.setBizTypeName(code.getName());
					} else {
						scmInvItemInAndOutDetail.setBizTypeName(scmInvMoveInWarehsBillEntry.getBizType());
					}
					scmInvItemInAndOutDetail.setUnit(scmInvMoveInWarehsBillEntry.getUnit());
					scmInvItemInAndOutDetail.setLot(scmInvMoveInWarehsBillEntry.getLot());
					scmInvItemInAndOutDetail.setMoveQty(scmInvMoveInWarehsBillEntry.getQty());
					scmInvItemInAndOutDetail.setMoveAmt(scmInvMoveInWarehsBillEntry.getAmt());
					scmInvItemInAndOutDetail.setMoveTax(
							scmInvMoveInWarehsBillEntry.getTaxAmt().subtract(scmInvMoveInWarehsBillEntry.getAmt()));
					scmInvItemInAndOutDetail.setMoveTaxAmt(scmInvMoveInWarehsBillEntry.getTaxAmt());
					scmInvItemInAndOutDetailList.add(scmInvItemInAndOutDetail);
				}
			}
		}
		// 调拨出库
		page = new Page();
		page.setModelClass(ScmInvMoveIssueBillEntry2.class);
		page.setShowCount(Integer.MAX_VALUE);
		if (StringUtils.isNotBlank(lot)) {
			page.getParam()
					.put(ClassUtils.getFinalModelSimpleName(ScmInvMoveIssueBillEntry2.class) + "."
							+ ScmInvMoveIssueBillEntry2.FN_LOT,
							new QueryParam(
									ClassUtils.getFinalModelSimpleName(ScmInvMoveIssueBillEntry2.class) + "."
											+ ScmInvMoveIssueBillEntry2.FN_LOT,
									QueryParam.QUERY_LIKE, "%" + lot + "%"));
		}
		page.getParam()
				.put(ClassUtils.getFinalModelSimpleName(ScmInvMoveIssueBillEntry2.class) + "."
						+ ScmInvMoveIssueBillEntry2.FN_ITEMID,
						new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMoveIssueBillEntry2.class) + "."
								+ ScmInvMoveIssueBillEntry2.FN_ITEMID, QueryParam.QUERY_EQ, materialName));
		page.getParam()
				.put(ClassUtils.getFinalModelSimpleName(ScmInvMoveIssueBillEntry2.class) + "."
						+ ScmInvMoveIssueBillEntry2.FN_ORGUNITNO,
						new QueryParam(
								ClassUtils.getFinalModelSimpleName(ScmInvMoveIssueBillEntry2.class) + "."
										+ ScmInvMoveIssueBillEntry2.FN_ORGUNITNO,
								QueryParam.QUERY_IN, orgUnitNos.toString()));
		if (StringUtils.isNotBlank(whName)) {
			page.getParam()
					.put(ClassUtils.getFinalModelSimpleName(ScmInvMoveIssueBillEntry2.class) + "."
							+ ScmInvMoveIssueBillEntry2.FN_WAREHOUSEID,
							new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMoveIssueBillEntry2.class) + "."
									+ ScmInvMoveIssueBillEntry2.FN_WAREHOUSEID, QueryParam.QUERY_EQ, whName));
		}
		page.getParam().put(
				ClassUtils.getFinalModelSimpleName(ScmInvMoveIssueBill.class) + "." + ScmInvMoveIssueBill.FN_BIZDATE,
				new QueryParam(
						ClassUtils.getFinalModelSimpleName(ScmInvMoveIssueBill.class) + "."
								+ ScmInvMoveIssueBill.FN_BIZDATE,
						QueryParam.QUERY_BETWEEN, FormatUtils.fmtDate(periodCalendar.getStartDate()), endDate));
		if (!StringUtils.equals("Y", status)) {
			page.getParam().put(
					ClassUtils.getFinalModelSimpleName(ScmInvMoveIssueBill.class) + "." + ScmInvMoveIssueBill.FN_STATUS,
					new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMoveIssueBill.class) + "."
							+ ScmInvMoveIssueBill.FN_STATUS, QueryParam.QUERY_EQ, "E"));
		}
		List<ScmInvMoveIssueBillEntry2> scmInvMoveIssueBillEntryList = scmInvMoveIssueBillEntryBiz.queryPage(page,
				arglist, "findAllPage", param);
		if (scmInvMoveIssueBillEntryList != null && !scmInvMoveIssueBillEntryList.isEmpty()) {
			for (ScmInvMoveIssueBillEntry2 scmInvMoveIssueBillEntry : scmInvMoveIssueBillEntryList) {
				if (scmInvMoveIssueBillEntry.getBizDate().compareTo(FormatUtils.parseDate(beginDate)) < 0) {
					// 比查询日期小的统计到期初数据中，并从List中删除
					if (initQtyMap.containsKey(scmInvMoveIssueBillEntry.getOrgUnitNo() + "_"
							+ scmInvMoveIssueBillEntry.getWareHouseId())) {
						initQty = initQtyMap.get(scmInvMoveIssueBillEntry.getOrgUnitNo() + "_"
								+ scmInvMoveIssueBillEntry.getWareHouseId());
						initQty = initQty.subtract(scmInvMoveIssueBillEntry.getQty());
						initQtyMap.put(scmInvMoveIssueBillEntry.getOrgUnitNo() + "_"
								+ scmInvMoveIssueBillEntry.getWareHouseId(), initQty);
					} else {
						initQtyMap.put(
								scmInvMoveIssueBillEntry.getOrgUnitNo() + "_"
										+ scmInvMoveIssueBillEntry.getWareHouseId(),
								BigDecimal.ZERO.subtract(scmInvMoveIssueBillEntry.getQty()));
					}
					if (initAmtMap.containsKey(scmInvMoveIssueBillEntry.getOrgUnitNo() + "_"
							+ scmInvMoveIssueBillEntry.getUseOrgUnitNo())) {
						initAmt = initAmtMap.get(scmInvMoveIssueBillEntry.getOrgUnitNo() + "_"
								+ scmInvMoveIssueBillEntry.getWareHouseId());
						initAmt = initAmt.subtract(scmInvMoveIssueBillEntry.getAmt());
						initAmtMap.put(scmInvMoveIssueBillEntry.getOrgUnitNo() + "_"
								+ scmInvMoveIssueBillEntry.getWareHouseId(), initAmt);
					} else {
						initAmtMap.put(
								scmInvMoveIssueBillEntry.getOrgUnitNo() + "_"
										+ scmInvMoveIssueBillEntry.getWareHouseId(),
								BigDecimal.ZERO.subtract(scmInvMoveIssueBillEntry.getAmt()));
					}
					if (initTaxAmtMap.containsKey(scmInvMoveIssueBillEntry.getOrgUnitNo() + "_"
							+ scmInvMoveIssueBillEntry.getUseOrgUnitNo())) {
						initTaxAmt = initTaxAmtMap.get(scmInvMoveIssueBillEntry.getOrgUnitNo() + "_"
								+ scmInvMoveIssueBillEntry.getWareHouseId());
						initTaxAmt = initTaxAmt.subtract(scmInvMoveIssueBillEntry.getTaxAmt());
						initTaxAmtMap.put(scmInvMoveIssueBillEntry.getOrgUnitNo() + "_"
								+ scmInvMoveIssueBillEntry.getWareHouseId(), initTaxAmt);
					} else {
						initTaxAmtMap.put(
								scmInvMoveIssueBillEntry.getOrgUnitNo() + "_"
										+ scmInvMoveIssueBillEntry.getWareHouseId(),
								BigDecimal.ZERO.subtract(scmInvMoveIssueBillEntry.getTaxAmt()));
					}
				} else {
					// 放入明细记录结果集中
					ScmInvItemInAndOutDetail scmInvItemInAndOutDetail = new ScmInvItemInAndOutDetail(true);
					scmInvItemInAndOutDetail.setOrgUnitNo(scmInvMoveIssueBillEntry.getOrgUnitNo());
					scmInvItemInAndOutDetail.setWareHouseId(scmInvMoveIssueBillEntry.getWareHouseId());
					scmInvItemInAndOutDetail.setBillNo(scmInvMoveIssueBillEntry.getOtNo());
					Code code = codeBiz.selectByCategoryAndCode("warehouseStatus",
							scmInvMoveIssueBillEntry.getStatus());
					if (code != null) {
						scmInvItemInAndOutDetail.setStatusName(code.getName());
					} else {
						scmInvItemInAndOutDetail.setStatusName(scmInvMoveIssueBillEntry.getStatus());
					}
					scmInvItemInAndOutDetail.setBizDate(scmInvMoveIssueBillEntry.getBizDate());
					scmInvItemInAndOutDetail.setPostDate(scmInvMoveIssueBillEntry.getPostDate());
					code = codeBiz.selectByCategoryAndCode("devoteWHBizType", scmInvMoveIssueBillEntry.getBizType());
					if (code != null) {
						scmInvItemInAndOutDetail.setBizTypeName(code.getName());
					} else {
						scmInvItemInAndOutDetail.setBizTypeName(scmInvMoveIssueBillEntry.getBizType());
					}
					scmInvItemInAndOutDetail.setUnit(scmInvMoveIssueBillEntry.getUnit());
					scmInvItemInAndOutDetail.setLot(scmInvMoveIssueBillEntry.getLot());
					scmInvItemInAndOutDetail.setMoveQty(BigDecimal.ZERO.subtract(scmInvMoveIssueBillEntry.getQty()));
					scmInvItemInAndOutDetail.setMoveAmt(BigDecimal.ZERO.subtract(scmInvMoveIssueBillEntry.getAmt()));
					scmInvItemInAndOutDetail.setMoveTax(BigDecimal.ZERO.subtract(
							scmInvMoveIssueBillEntry.getTaxAmt().subtract(scmInvMoveIssueBillEntry.getAmt())));
					scmInvItemInAndOutDetail
							.setMoveTaxAmt(BigDecimal.ZERO.subtract(scmInvMoveIssueBillEntry.getTaxAmt()));
					scmInvItemInAndOutDetailList.add(scmInvItemInAndOutDetail);
				}
			}
		}
		// 写入期初数据
		String key;
		Iterator it = initQtyMap.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			key = (String) entry.getKey();
			String keys[] = StringUtils.split(key, "_");
			ScmInvItemInAndOutDetail scmInvItemInAndOutDetail = new ScmInvItemInAndOutDetail(true);
			scmInvItemInAndOutDetail.setOrgUnitNo(keys[0]);
			scmInvItemInAndOutDetail.setWareHouseId(Long.valueOf(keys[1]));
			scmInvItemInAndOutDetail.setInitflag(1);
			scmInvItemInAndOutDetail.setBizTypeName("期初余额");
			scmInvItemInAndOutDetail.setStockQty(initQtyMap.get(key));
			scmInvItemInAndOutDetail.setStockAmt(initAmtMap.get(key));
			scmInvItemInAndOutDetail.setStockTaxAmt(initTaxAmtMap.get(key));
			scmInvItemInAndOutDetail.setStockTax(
					scmInvItemInAndOutDetail.getStockTaxAmt().subtract(scmInvItemInAndOutDetail.getStockAmt()));
			scmInvItemInAndOutDetailList.add(scmInvItemInAndOutDetail);
		}

		// 数据排序
		String fields[] = { "orgUnitNo", "wareHouseId", "initflag", "bizDate", "postDate" };
		String sorts[] = { "ASC", "ASC", "DESC", "ASC", "ASC" };
		scmInvItemInAndOutDetailList = (List<ScmInvItemInAndOutDetail>) ListSortUtil.sort(scmInvItemInAndOutDetailList,
				fields, sorts);
		for (ScmInvItemInAndOutDetail scmInvItemInAndOutDetail : scmInvItemInAndOutDetailList) {
			BigDecimal stockQty = BigDecimal.ZERO;
			BigDecimal stockAmt = BigDecimal.ZERO;
			BigDecimal stockTax = BigDecimal.ZERO;
			BigDecimal stockTaxAmt = BigDecimal.ZERO;
			OrgBaseUnit2 orgBaseUnit = orgUnitBiz.selectbyOrgNo(scmInvItemInAndOutDetail.getOrgUnitNo(), param);
			if (orgBaseUnit != null) {
				scmInvItemInAndOutDetail.setOrgUnitName(orgBaseUnit.getOrgUnitName());
			} else {
				scmInvItemInAndOutDetail.setOrgUnitName(scmInvItemInAndOutDetail.getOrgUnitNo());
			}
			if (scmInvItemInAndOutDetail.getWareHouseId() > 0) {
				ScmInvWareHouse scmInvWareHouse = scmInvWareHouseBiz
						.selectDirect(scmInvItemInAndOutDetail.getWareHouseId(), param);
				if (scmInvWareHouse != null) {
					scmInvItemInAndOutDetail.setWareHouseName(scmInvWareHouse.getWhName());
				}
			}
			if (scmInvItemInAndOutDetail.getUnit() > 0) {
				ScmMeasureUnit scmMeasureUnit = scmMeasureUnitBiz.selectDirect(scmInvItemInAndOutDetail.getUnit(),
						param);
				if (scmMeasureUnit != null)
					scmInvItemInAndOutDetail.setUnitName(scmMeasureUnit.getUnitName());
			}
			if (scmInvItemInAndOutDetail.getVendorId() > 0) {
				Scmsupplier2 scmsupplier = scmsupplierBiz.selectDirect(scmInvItemInAndOutDetail.getVendorId(), param);
				if (scmsupplier != null)
					scmInvItemInAndOutDetail.setVendorName(scmsupplier.getVendorName());
			}
			if (scmInvItemInAndOutDetail.getInitflag() == 1) {
				stockQty = scmInvItemInAndOutDetail.getStockQty();
				stockAmt = scmInvItemInAndOutDetail.getStockAmt();
				stockTax = scmInvItemInAndOutDetail.getStockTax();
				stockTaxAmt = scmInvItemInAndOutDetail.getStockTaxAmt();
			} else {
				stockQty = stockQty.add(scmInvItemInAndOutDetail.getAddInQty())
						.add(scmInvItemInAndOutDetail.getMoveQty()).subtract(scmInvItemInAndOutDetail.getOutQty())
						.subtract(scmInvItemInAndOutDetail.getReqQty()).add(scmInvItemInAndOutDetail.getProfitQty())
						.subtract(scmInvItemInAndOutDetail.getScrapQty());
				stockAmt = stockAmt.add(scmInvItemInAndOutDetail.getAddInAmt())
						.add(scmInvItemInAndOutDetail.getMoveAmt()).subtract(scmInvItemInAndOutDetail.getOutAmt())
						.subtract(scmInvItemInAndOutDetail.getReqAmt()).add(scmInvItemInAndOutDetail.getProfitAmt())
						.subtract(scmInvItemInAndOutDetail.getScrapAmt());
				stockTaxAmt = stockTaxAmt.add(scmInvItemInAndOutDetail.getAddInTaxAmt())
						.add(scmInvItemInAndOutDetail.getMoveTaxAmt()).subtract(scmInvItemInAndOutDetail.getOutTaxAmt())
						.subtract(scmInvItemInAndOutDetail.getReqTaxAmt())
						.add(scmInvItemInAndOutDetail.getProfitTaxAmt())
						.subtract(scmInvItemInAndOutDetail.getScrapTaxAmt());
				stockTax = stockTax.add(scmInvItemInAndOutDetail.getAddInTax())
						.add(scmInvItemInAndOutDetail.getMoveTax()).subtract(scmInvItemInAndOutDetail.getOutTax())
						.subtract(scmInvItemInAndOutDetail.getReqTax()).add(scmInvItemInAndOutDetail.getProfitTax())
						.subtract(scmInvItemInAndOutDetail.getScrapTax());
				scmInvItemInAndOutDetail.setStockQty(stockQty);
				scmInvItemInAndOutDetail.setStockAmt(stockAmt);
				scmInvItemInAndOutDetail.setStockTax(stockTax);
				scmInvItemInAndOutDetail.setStockTaxAmt(stockTaxAmt);
			}
		}
		return scmInvItemInAndOutDetailList;
	}

	@Override
	public List<ScmInvItemSaleSum> selectScmInvItemSaleSum(HttpServletRequest httpServletRequest) throws AppException {
		List<ScmInvItemSaleSum> scmInvItemSaleSumList = new ArrayList<>();
		String currentOrgUnitNo = httpServletRequest.getParameter("orgUnitNo");
		String currentControlUnitNo = httpServletRequest.getParameter("controlUnitNo");
		String orgUnitNo = httpServletRequest.getParameter("invOrgUnitNo");
		String whOrDept = httpServletRequest.getParameter("whOrDept");
		String beginDate = httpServletRequest.getParameter("beginDate");
		String endDate = httpServletRequest.getParameter("endDate");
		String materialClassName = httpServletRequest.getParameter("materialClassName");
		String materialName = httpServletRequest.getParameter("materialName");
		String custNo = httpServletRequest.getParameter("custNo");
		StringBuffer custIds = new StringBuffer("");
		if (StringUtils.isNotBlank(custNo)) {
			String[] idList = StringUtils.split(custNo, ",");
			for (String id : idList) {
				if (StringUtils.isBlank(id))
					continue;
				if (StringUtils.isNotBlank(custIds.toString()))
					custIds.append(",");
				custIds.append(id);
			}
		}
		Param param = new Param();
		param.setOrgUnitNo(currentOrgUnitNo);
		param.setControlUnitNo(currentControlUnitNo);
		String status = httpServletRequest.getParameter("status");
		String saleTaxRate = httpServletRequest.getParameter("saleTaxRate");

		if (StringUtils.isBlank(beginDate) || StringUtils.isBlank(endDate))
			return scmInvItemSaleSumList;
		if (StringUtils.isBlank(orgUnitNo))
			orgUnitNo = currentOrgUnitNo;
		StringBuffer orgUnitNos = new StringBuffer("");
		List<OrgStorage2> orgStorageList = orgStorageBiz.findChild(orgUnitNo, param);
		if (orgStorageList != null && !orgStorageList.isEmpty()) {
			for (OrgStorage2 orgStorage : orgStorageList) {
				if (StringUtils.isNotBlank(orgUnitNos.toString()))
					orgUnitNos.append(",");
				orgUnitNos.append("'").append(orgStorage.getOrgUnitNo()).append("'");
			}
		} else {
			orgUnitNos.append("'").append(orgUnitNo).append("'");
		}
		if (StringUtils.isNotBlank(materialClassName)) {
			StringBuffer sbMaterilaClass = new StringBuffer("");
			String[] materialClassNameList = StringUtils.split(materialClassName, ",");
			for (String materialClass : materialClassNameList) {
				if (StringUtils.isBlank(materialClass))
					continue;
				int materialClassId = Integer.parseInt(materialClass);
				List<ScmMaterialGroup> scmMaterialGroupList = scmMaterialGroupBiz.findChild(materialClassId, param);
				if (scmMaterialGroupList != null && scmMaterialGroupList.size() > 0) {
					for (ScmMaterialGroup scmMaterialGroup : scmMaterialGroupList) {
						if (StringUtils.isNotBlank(sbMaterilaClass.toString()))
							sbMaterilaClass.append(",");
						sbMaterilaClass.append(String.valueOf(scmMaterialGroup.getId()));
					}
				}
			}
			if (StringUtils.isNotBlank(sbMaterilaClass.toString())) {
				materialClassName = sbMaterilaClass.toString();
			}
		}
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("orgUnitNos", orgUnitNos);
		map.put("beginDate", beginDate);
		map.put("endDate", endDate);
		map.put("whOrDept", whOrDept);
		map.put("materialClassName", materialClassName);
		map.put("materialName", materialName);
		map.put("status", status);
		map.put("custIds", custIds.toString());
		if (StringUtils.isNotBlank(saleTaxRate) && !StringUtils.equals("-1", saleTaxRate)) {
			map.put("saleTaxRate", saleTaxRate);
		}
		scmInvItemSaleSumList = ((ScmInvReportDAO) dao).selectScmInvItemSaleSum(map);
		if (scmInvItemSaleSumList != null && !scmInvItemSaleSumList.isEmpty()) {
			for (ScmInvItemSaleSum scmInvItemSaleSum : scmInvItemSaleSumList) {
				if (scmInvItemSaleSum.getItemId() > 0) {
					ScmMaterial2 scmMaterial = scmMaterialBiz.selectByItemId(scmInvItemSaleSum.getItemId(), param);
					if (scmMaterial != null) {
						scmInvItemSaleSum.setItemNo(scmMaterial.getItemNo());
						scmInvItemSaleSum.setItemName(scmMaterial.getItemName());
						scmInvItemSaleSum.setSpec(scmMaterial.getSpec());
						if (scmMaterial.getGroupId() > 0) {
							ScmMaterialGroup scmMaterialGroup = scmMaterialGroupBiz
									.selectDirect(scmMaterial.getGroupId(), param);
							if (scmMaterialGroup != null) {
								scmInvItemSaleSum.setClassName(scmMaterialGroup.getClassName());
							}
						}
					}
				}
				if (scmInvItemSaleSum.getCustId() > 0) {
					Customer2 customer = customerBiz.selectDirect(scmInvItemSaleSum.getCustId(), param);
					if (customer != null)
						scmInvItemSaleSum.setCustName(customer.getCustName());
				}
				if (StringUtils.isNotBlank(scmInvItemSaleSum.getOrgUnitNo())) {
					OrgBaseUnit2 orgBaseUnit = orgUnitBiz.selectbyOrgNo(scmInvItemSaleSum.getOrgUnitNo(), param);
					if (orgBaseUnit != null) {
						scmInvItemSaleSum.setOrgUnitName(orgBaseUnit.getOrgUnitName());
					} else {
						scmInvItemSaleSum.setOrgUnitName(scmInvItemSaleSum.getOrgUnitNo());
					}
				}
				if (scmInvItemSaleSum.getUnit() > 0) {
					ScmMeasureUnit scmMeasureUnit = scmMeasureUnitBiz.selectDirect(scmInvItemSaleSum.getUnit(), param);
					if (scmMeasureUnit != null)
						scmInvItemSaleSum.setUnitName(scmMeasureUnit.getUnitName());
				}
				if (scmInvItemSaleSum.getWareHouseId() > 0) {
					ScmInvWareHouse scmInvWareHouse = scmInvWareHouseBiz
							.selectDirect(scmInvItemSaleSum.getWareHouseId(), param);
					if (scmInvWareHouse != null) {
						scmInvItemSaleSum.setWareHouseName(scmInvWareHouse.getWhName());
					}
				}
				if (StringUtils.isNotBlank(scmInvItemSaleSum.getLot())) {
					scmInvItemSaleSum.setWareHouseName(scmInvItemSaleSum.getLot());
				}
				scmInvItemSaleSum
						.setTotalQty(scmInvItemSaleSum.getSaleQty().subtract(scmInvItemSaleSum.getReturnQty()));
				scmInvItemSaleSum.setTotalTaxAmt(
						scmInvItemSaleSum.getSaleTaxAmt().subtract(scmInvItemSaleSum.getReturnTaxAmt()));
				if (scmInvItemSaleSum.getSaleQty().compareTo(BigDecimal.ZERO) != 0
						&& scmInvItemSaleSum.getSaleTaxAmt().compareTo(BigDecimal.ZERO) != 0) {
					scmInvItemSaleSum.setSaleTaxPrice(scmInvItemSaleSum.getSaleTaxAmt()
							.divide(scmInvItemSaleSum.getSaleQty(), 2, RoundingMode.HALF_UP));
				} else {
					scmInvItemSaleSum.setSaleTaxPrice((BigDecimal.ZERO));
				}
			}
		}
		return scmInvItemSaleSumList;
	}

	@Override
	public List<HashMap> selectScmInvInWarehsItemClass(HttpServletRequest httpServletRequest) throws AppException {
		List<HashMap> resultList = new ArrayList<>();
		// 1 获取查询参数
		String currentOrgUnitNo = httpServletRequest.getParameter("currentOrgUnitNo");
		String currentControlUnitNo = httpServletRequest.getParameter("currentControlUnitNo");
		String invOrgUnitNo = httpServletRequest.getParameter("invOrgUnitNo");
		String begDate = httpServletRequest.getParameter("begDate");
		String endDate = httpServletRequest.getParameter("endDate");
		String standardName = httpServletRequest.getParameter("standardName");
		String materialClassName = httpServletRequest.getParameter("materialClassName");
		String whOrDept = httpServletRequest.getParameter("whOrDept");
		String vendorName = httpServletRequest.getParameter("vendorName");
		String venderClassName = httpServletRequest.getParameter("venderClassName");
		String stockType = httpServletRequest.getParameter("stockType");
		String noPost = httpServletRequest.getParameter("noPost");
		String longNo = httpServletRequest.getParameter("longNo");

		if (StringUtils.isBlank(begDate) || StringUtils.isBlank(endDate))
			return resultList;
		Param param = new Param();
		param.setOrgUnitNo(currentOrgUnitNo);
		param.setControlUnitNo(currentControlUnitNo);
		HashMap<String, Object> map = new HashMap<>();
		if (StringUtils.isBlank(invOrgUnitNo)) {
			List<OrgStorage2> orgStorageList = orgStorageBiz.findChild(currentOrgUnitNo, param);
			if (orgStorageList != null) {
				invOrgUnitNo = orgStorageList.get(0).getOrgUnitNo();
			} else {
				invOrgUnitNo = currentOrgUnitNo;
			}
		}
		map.put("orgUnitNo", invOrgUnitNo);
		map.put("begDate", begDate);
		map.put("endDate", endDate);

		if (StringUtils.isNotBlank(vendorName) && StringUtils.isNumeric(vendorName)) {
			map.put("vendorId", vendorName);
		}
		if (StringUtils.isNotBlank(venderClassName) && StringUtils.isNumeric(venderClassName)) {
			map.put("materialClassId", venderClassName);
		}
		long wareHouseId = 0;
		if (StringUtils.isBlank(stockType))
			stockType = "0";
		map.put("stockType", stockType);
		if (StringUtils.isNotBlank(whOrDept)) {
			if (!StringUtils.equals("0", StringUtils.left(whOrDept, 1))) {
				map.put("wareHouseId", whOrDept);
			} else {
				map.put("useOrgUnitNo", whOrDept);
			}
		}

		if (StringUtils.isNotBlank(materialClassName) && StringUtils.isNumeric(materialClassName)) {
			map.put("materialClassId", materialClassName);
		}

		if (StringUtils.isNotBlank(standardName) && StringUtils.isNumeric(standardName)) {
			map.put("standardName", standardName);
		}

		if ("Y".equals(noPost)) {
			noPost = "1";
		} else {
			noPost = "0";
		}
		map.put("noPost", noPost);

		map.put("longNo", longNo);
		// 2 查询数据库
		List<ScmInvInWarehsItemClass> scmInvInWarehsItemClassList = ((ScmInvReportDAO) dao)
				.selectScmInvInWarehsItemClass(map);
		// 3 数据处理及返回

		LinkedHashMap<String, String> classCodeMap = new LinkedHashMap<>();
		LinkedHashMap<String, String> classNameMap = new LinkedHashMap<>();
		// 供应商，对应汇总记录
		HashMap<Integer, ScmInvInWarehsItemClass> vendorMap = new HashMap<>();

		// 供应商，对应明细记录
		HashMap<Integer, List<ScmInvInWarehsItemClass>> vendorEntryMap = new HashMap<>();

		int mapCount = 0;
		long vendorId = 0;
		String classCode = "";

		// 穷举物资类别，并塞到classNameMap
		if (scmInvInWarehsItemClassList != null && scmInvInWarehsItemClassList.size() > 0) {
			for (ScmInvInWarehsItemClass scmInvInWarehsItemClass : scmInvInWarehsItemClassList) {
				classCode = scmInvInWarehsItemClass.getClassCode();
				String className = scmInvInWarehsItemClass.getClassName();
				if (!classCodeMap.containsKey(classCode)) {
					classCodeMap.put(classCode, String.valueOf(mapCount));
					classNameMap.put(classCode, className);
					mapCount++;
				}
			}
		}

		// 把所有的vendorId塞到vendorMap
		if (scmInvInWarehsItemClassList != null && scmInvInWarehsItemClassList.size() > 0) {
			int maxValue = scmInvInWarehsItemClassList.size() - 1;
			long verdorIdNext = 0;
			BigDecimal totalAmt = BigDecimal.ZERO;
			BigDecimal taxTotalAmt = BigDecimal.ZERO;
			BigDecimal taxTotalAmount = BigDecimal.ZERO;
			List<ScmInvInWarehsItemClass> scmInvInWarehsItemClassEntryList = new ArrayList<>();
			List<String> classNameList = new ArrayList<>();
			classCode = "";

			for (int i = 0; i < scmInvInWarehsItemClassList.size(); i++) {
				ScmInvInWarehsItemClass scmInvInWarehsItemClass = scmInvInWarehsItemClassList.get(i);
				ScmInvInWarehsItemClass scmInvInWarehsItemClassSum = new ScmInvInWarehsItemClass();

				if (i < maxValue) {
					ScmInvInWarehsItemClass scmInvInWarehsItemClassTemp = scmInvInWarehsItemClassList.get(i + 1);
					verdorIdNext = scmInvInWarehsItemClassTemp.getVendorId();
				} else {
					verdorIdNext = 0;
				}

				vendorId = scmInvInWarehsItemClass.getVendorId();
				classCode = scmInvInWarehsItemClass.getClassCode();
				totalAmt = totalAmt.add(scmInvInWarehsItemClass.getAmt());
				taxTotalAmt = taxTotalAmt.add(scmInvInWarehsItemClass.getTaxAmt());
				taxTotalAmount = taxTotalAmount.add(scmInvInWarehsItemClass.getTaxAmount());
				scmInvInWarehsItemClassEntryList.add(scmInvInWarehsItemClass);

				// vendorMap存储供应商的合计金额
				// vendorEntryMap存储供应商的明细金额
				if (vendorId != verdorIdNext || verdorIdNext == 0) {
					scmInvInWarehsItemClassSum.setVendorId(vendorId);
					scmInvInWarehsItemClassSum.setTotalAmt(totalAmt);
					scmInvInWarehsItemClassSum.setTaxTotalAmt(taxTotalAmt);
					scmInvInWarehsItemClassSum.setTaxTotalAmount(taxTotalAmount);
					vendorMap.put((int) vendorId, scmInvInWarehsItemClassSum);
					vendorEntryMap.put((int) vendorId, scmInvInWarehsItemClassEntryList);

					totalAmt = BigDecimal.ZERO;
					taxTotalAmt = BigDecimal.ZERO;
					taxTotalAmount = BigDecimal.ZERO;
					scmInvInWarehsItemClassEntryList = new ArrayList<ScmInvInWarehsItemClass>();
				}
			}
		}

		List<String> classNameList = new ArrayList<>();
		for (String classCodeKey : classNameMap.keySet()) {
			String className = classNameMap.get(classCodeKey);
			classNameList.add(className);
		}

		// 遍历vendorMap存储供应商的合计金额
		for (Integer vendorIdKey : vendorMap.keySet()) {
			HashMap<String, Object> resultMap = new HashMap<>();
			List<ScmInvInWarehsItemClass> scmInvInWarehsItemClassEntryList = vendorEntryMap.get(vendorIdKey);
			ScmInvInWarehsItemClass scmInvInWarehsItemClassSum = vendorMap.get(vendorIdKey);
			BigDecimal totalAmt = scmInvInWarehsItemClassSum.getTotalAmt();
			BigDecimal taxTotalAmt = scmInvInWarehsItemClassSum.getTaxTotalAmt();
			BigDecimal taxTotalAmount = scmInvInWarehsItemClassSum.getTaxTotalAmount();
			int[] flag = new int[mapCount];

			for (int i = 0; i < flag.length; i++) {
				flag[i] = 0;
			}

			HashMap<Integer, String> classCodeTempMap = new HashMap<>();

			// 供应商
			Scmsupplier2 scmsupplier = scmsupplierBiz.selectDirect(vendorIdKey, param);

			for (ScmInvInWarehsItemClass scmInvInWarehsItemClass : scmInvInWarehsItemClassEntryList) {
				classCode = scmInvInWarehsItemClass.getClassCode();
				int index = Integer.parseInt(classCodeMap.get(classCode));
				flag[index] = 1;

				if (scmsupplier != null) {
					vendorName = scmsupplier.getVendorName();
				} else {
					vendorName = "";
				}

				BigDecimal amt = scmInvInWarehsItemClass.getAmt();
				BigDecimal taxamt = scmInvInWarehsItemClass.getTaxAmt();
				BigDecimal taxamount = scmInvInWarehsItemClass.getTaxAmount();

				resultMap.put("vendorName", vendorName);
				resultMap.put("totalAmt", totalAmt);
				resultMap.put("taxTotalAmt", taxTotalAmt);
				resultMap.put("taxTotalAmount", taxTotalAmount);

				if (index == 0) {
					resultMap.put("amt", amt);
					resultMap.put("taxAmt", taxamt);
					resultMap.put("taxAmount", taxamount);
				} else {
					resultMap.put("amt" + "_" + String.valueOf(index), amt);
					resultMap.put("taxAmt" + "_" + String.valueOf(index), taxamt);
					resultMap.put("taxAmount" + "_" + String.valueOf(index), taxamount);
				}
			}

			for (int i = 0; i < flag.length; i++) {
				if (flag[i] == 0) {
					if (i == 0) {
						resultMap.put("amt", BigDecimal.ZERO);
						resultMap.put("taxAmt", BigDecimal.ZERO);
						resultMap.put("taxAmount", BigDecimal.ZERO);
					} else {
						resultMap.put("amt" + "_" + String.valueOf(i), BigDecimal.ZERO);
						resultMap.put("taxAmt" + "_" + String.valueOf(i), BigDecimal.ZERO);
						resultMap.put("taxAmount" + "_" + String.valueOf(i), BigDecimal.ZERO);
					}
				}
			}

			if (resultList != null && resultList.size() == 0) {
				resultMap.put("classNameList", classNameList);
			}
			resultList.add(resultMap);
		}

		return resultList;
	}

	@Override
	public List<ScmInvItemWrDetails> selectScmInvItemWrDetails(HttpServletRequest httpServletRequest)
			throws AppException {
		List<ScmInvItemWrDetails> scmInvItemWrDetailsList = new ArrayList<>();
		// 1 获取查询参数
		String currentOrgUnitNo = httpServletRequest.getParameter("orgUnitNo");
		String currentControlUnitNo = httpServletRequest.getParameter("controlUnitNo");
		String invOrgUnitNo = httpServletRequest.getParameter("invOrgUnitNo");
		String whOrDept = httpServletRequest.getParameter("whOrDept");
		String begDate = httpServletRequest.getParameter("begDate");
		String endDate = httpServletRequest.getParameter("endDate");
		String materialClassName = httpServletRequest.getParameter("materialClassName");
		String materialName = httpServletRequest.getParameter("materialName");
		StringBuffer materialIds = new StringBuffer("");
		if (StringUtils.isNotBlank(materialName)) {
			String[] idList = StringUtils.split(materialName, "|");
			for (String id : idList) {
				if (StringUtils.isBlank(id))
					continue;
				if (StringUtils.isNotBlank(materialIds.toString()))
					materialIds.append(",");
				materialIds.append(id);
			}
		}
		String vendorName = httpServletRequest.getParameter("vendorName");
		StringBuffer vendorIds = new StringBuffer("");
		if (StringUtils.isNotBlank(vendorName)) {
			String[] idList = StringUtils.split(vendorName, "|");
			for (String id : idList) {
				if (StringUtils.isBlank(id))
					continue;
				if (StringUtils.isNotBlank(vendorIds.toString()))
					vendorIds.append(",");
				vendorIds.append(id);
			}
		}
		String stockType = httpServletRequest.getParameter("stockType");
		String noPost = "1".equals(httpServletRequest.getParameter("noPost")) ? "Y" : "N";

		Param param = new Param();
		param.setOrgUnitNo(currentOrgUnitNo);
		param.setControlUnitNo(currentControlUnitNo);
		String taxRate = httpServletRequest.getParameter("taxRate");

		String type = httpServletRequest.getParameter("type");
		if (StringUtils.isBlank(begDate) || StringUtils.isBlank(endDate))
			return scmInvItemWrDetailsList;
		HashMap<String, Object> map = new HashMap<>();
		StringBuffer orgUnitNos = new StringBuffer("");
		if (StringUtils.isNotBlank(invOrgUnitNo)) {
			String[] noList = StringUtils.split(invOrgUnitNo, "|");
			for (String no : noList) {
				if (StringUtils.isBlank(no))
					continue;
				List<OrgStorage2> orgStorageList = orgStorageBiz.findChild(no, param);
				if (orgStorageList != null && !orgStorageList.isEmpty()) {
					for (OrgStorage2 orgStorage : orgStorageList) {
						if (StringUtils.isNotBlank(orgUnitNos.toString()))
							orgUnitNos.append(",");
						orgUnitNos.append("'").append(orgStorage.getOrgUnitNo()).append("'");
					}
				} else {
					orgUnitNos.append("'").append(no).append("'");
				}
			}
		}
//        List<OrgStorage2> orgStorageList = orgStorageBiz.findChild(currentOrgUnitNo, param);
//        if(orgStorageList != null && !orgStorageList.isEmpty()) {
//            for(OrgStorage2 orgStorage:orgStorageList) {
//                if(StringUtils.isNotBlank(orgUnitNos.toString()))
//                    orgUnitNos.append(",");
//                orgUnitNos.append("'").append(orgStorage.getOrgUnitNo()).append("'");
//            }
//        }else {
//            orgUnitNos.append("'").append(currentOrgUnitNo).append("'");
//        }
		int materialClassId = 0;
		StringBuffer sbMaterilaClass = new StringBuffer("");
		if (StringUtils.isNotBlank(materialClassName) && StringUtils.isNumeric(materialClassName)) {
			materialClassId = (Integer.parseInt(materialClassName));
			List<ScmMaterialGroup> scmMaterialGroupList = scmMaterialGroupBiz.findChild(materialClassId, param);
			if (scmMaterialGroupList != null && scmMaterialGroupList.size() > 0) {
				for (ScmMaterialGroup scmMaterialGroup : scmMaterialGroupList) {
					if (StringUtils.isNotBlank(sbMaterilaClass.toString()))
						sbMaterilaClass.append(",");
					sbMaterilaClass.append(scmMaterialGroup.getId());
				}
			} else {
				sbMaterilaClass.append(materialClassId);
			}
		}
		map.put("orgUnitNo", orgUnitNos.toString());
		map.put("begDate", begDate);
		map.put("endDate", endDate);
		map.put("materialIds", materialIds.toString());
		map.put("materialClassIds", sbMaterilaClass.toString());
		map.put("vendorIds", vendorIds.toString());
		long wareHouseId = 0;
		if (StringUtils.isBlank(stockType))
			stockType = "0";
		map.put("stockType", stockType);
		if (StringUtils.isNotBlank(whOrDept)) {
			if (!StringUtils.equals("0", StringUtils.left(whOrDept, 1))) {
				map.put("wareHouseId", whOrDept);
			} else {
				map.put("useOrgUnitNo", whOrDept);
			}
		}
		if ("Y".equals(noPost)) {
			noPost = "1";
		} else {
			noPost = "0";
		}
		map.put("noPost", noPost);
		if (StringUtils.isNotBlank(taxRate) && !StringUtils.equals("-1", taxRate)) {
			map.put("taxRate", taxRate);
		}
		map.put("type", type);
		// 2 查询数据库
		List<ScmInvItemWrDetails> list = ((ScmInvReportDAO) dao).selectScmInvItemWrDetails(map);
		// 3 数据处理及返回
		if (list != null && list.size() > 0) {
			for (ScmInvItemWrDetails scmInvItemWrDetails : list) {
				if (StringUtils.isNotBlank(scmInvItemWrDetails.getOrgUnitNo())) {
					OrgBaseUnit2 orgBaseUnit = orgBaseUnitBiz.selectbyOrgNo(scmInvItemWrDetails.getOrgUnitNo(), param);
					if (orgBaseUnit != null) {
						scmInvItemWrDetails.setOrgUnitName(orgBaseUnit.getOrgUnitName());
					}
				}
				if (BigDecimal.ZERO.compareTo(scmInvItemWrDetails.getAddInQty()) != 0) {
					scmInvItemWrDetails.setAddInPrice(scmInvItemWrDetails.getAddInAmt()
							.divide(scmInvItemWrDetails.getAddInQty(), 2, RoundingMode.HALF_UP));
					scmInvItemWrDetails.setAddInTaxPrice(scmInvItemWrDetails.getAddInTaxAmt()
							.divide(scmInvItemWrDetails.getAddInQty(), 2, RoundingMode.HALF_UP));
				}
				if (scmInvItemWrDetails.getBuyerId() > 0) {
					ScmPurBuyer2 scmPurBuyer = scmPurBuyerBiz.selectDirect(scmInvItemWrDetails.getBuyerId(), param);
					scmInvItemWrDetails.setBuyerName(scmPurBuyer.getBuyerName());
				}
			}
		}
		return list;
	}

	// 寄存入库汇总-供应商
	@Override
	public List<ScmInvItemWrSum> selectScmInvConsignSumSup(HttpServletRequest httpServletRequest) throws AppException {
		List<ScmInvItemWrSum> scmInvItemWrSumList = new ArrayList<>();
		// 1 获取查询参数
		String currentOrgUnitNo = httpServletRequest.getParameter("orgUnitNo");
		String currentControlUnitNo = httpServletRequest.getParameter("controlUnitNo");
		String invOrgUnitNo = httpServletRequest.getParameter("invOrgUnitNo");
		String whName = httpServletRequest.getParameter("whName");
		int wareHouseId = 0;
		if (StringUtils.isNotBlank(whName) && StringUtils.isNumeric(whName)) {
			wareHouseId = (Integer.parseInt(whName));
		}
		String begDate = httpServletRequest.getParameter("begDate");
		String endDate = httpServletRequest.getParameter("endDate");
		String materialClassName = httpServletRequest.getParameter("materialClassName");
		String materialName = httpServletRequest.getParameter("materialName");
		StringBuffer materialIds = new StringBuffer("");
		if (StringUtils.isNotBlank(materialName)) {
			String[] idList = StringUtils.split(materialName, ",");
			for (String id : idList) {
				if (StringUtils.isBlank(id))
					continue;
				if (StringUtils.isNotBlank(materialIds.toString()))
					materialIds.append(",");
				materialIds.append(id);
			}
		}
		String vendorName = httpServletRequest.getParameter("vendorName");
		StringBuffer vendorIds = new StringBuffer("");
		if (StringUtils.isNotBlank(vendorName)) {
			String[] idList = StringUtils.split(vendorName, ",");
			for (String id : idList) {
				if (StringUtils.isBlank(id))
					continue;
				if (StringUtils.isNotBlank(vendorIds.toString()))
					vendorIds.append(",");
				vendorIds.append(id);
			}
		}
		String noPost = "1".equals(httpServletRequest.getParameter("noPost")) ? "Y" : "N";
		Param param = new Param();
		param.setOrgUnitNo(currentOrgUnitNo);
		param.setControlUnitNo(currentControlUnitNo);
		String taxRate = httpServletRequest.getParameter("taxRate");

		String type = "V";

		if (StringUtils.isBlank(begDate) || StringUtils.isBlank(endDate))
			return scmInvItemWrSumList;

		HashMap<String, Object> map = new HashMap<>();
		StringBuffer orgUnitNos = new StringBuffer("");
		if (StringUtils.isNotBlank(invOrgUnitNo)) {
			String[] noList = StringUtils.split(invOrgUnitNo, ",");
			for (String no : noList) {
				if (StringUtils.isBlank(no))
					continue;
				List<OrgStorage2> orgStorageList = orgStorageBiz.findChild(no, param);
				if (orgStorageList != null && !orgStorageList.isEmpty()) {
					for (OrgStorage2 orgStorage : orgStorageList) {
						if (StringUtils.isNotBlank(orgUnitNos.toString()))
							orgUnitNos.append(",");
						orgUnitNos.append("'").append(orgStorage.getOrgUnitNo()).append("'");
					}
				} else {
					orgUnitNos.append("'").append(no).append("'");
				}
			}
		}
		int materialClassId = 0;
		StringBuffer sbMaterilaClass = new StringBuffer("");
		if (StringUtils.isNotBlank(materialClassName) && StringUtils.isNumeric(materialClassName)) {
			materialClassId = (Integer.parseInt(materialClassName));
			List<ScmMaterialGroup> scmMaterialGroupList = scmMaterialGroupBiz.findChild(materialClassId, param);
			if (scmMaterialGroupList != null && scmMaterialGroupList.size() > 0) {
				for (ScmMaterialGroup scmMaterialGroup : scmMaterialGroupList) {
					if (StringUtils.isNotBlank(sbMaterilaClass.toString()))
						sbMaterilaClass.append(",");
					sbMaterilaClass.append(scmMaterialGroup.getId());
				}
			} else {
				sbMaterilaClass.append(materialClassId);
			}
		}
		map.put("orgUnitNo", orgUnitNos.toString());
		map.put("begDate", begDate);
		map.put("endDate", endDate);
		map.put("materialIds", materialIds.toString());
		map.put("materialClassIds", sbMaterilaClass.toString());
		map.put("vendorIds", vendorIds.toString());
		map.put("wareHouseId", wareHouseId);
		if ("Y".equals(noPost)) {
			noPost = "1";
		} else {
			noPost = "0";
		}
		map.put("noPost", noPost);
		if (StringUtils.isNotBlank(taxRate) && !StringUtils.equals("-1", taxRate)) {
			map.put("taxRate", taxRate);
		}
		map.put("type", type);
		// 2 查询数据库
		List<ScmInvItemWrSum> list = ((ScmInvReportDAO) dao).selectScmInvConsignSumSup(map);
		// 3 数据处理及返回
		if (list != null && list.size() > 0) {
			for (ScmInvItemWrSum scmInvItemWrSum : list) {
				if (StringUtils.isNotBlank(scmInvItemWrSum.getOrgUnitNo())) {
					OrgBaseUnit2 orgBaseUnit = orgBaseUnitBiz.selectbyOrgNo(scmInvItemWrSum.getOrgUnitNo(), param);
					if (orgBaseUnit != null) {
						scmInvItemWrSum.setOrgUnitName(orgBaseUnit.getOrgUnitName());
					}
				}
				if (scmInvItemWrSum.getVendorId() > 0) {
					// 供应商
					Scmsupplier2 scmsupplier = scmsupplierBiz.selectDirect(scmInvItemWrSum.getVendorId(), param);
					if (scmsupplier != null) {
						scmInvItemWrSum.setVendorName(scmsupplier.getVendorName());
					}
				}
				if (scmInvItemWrSum.getWareHouseId() > 0) {
					// 仓库
					ScmInvWareHouse scmInvWareHouse = scmInvWareHouseBiz.selectDirect(scmInvItemWrSum.getWareHouseId(),
							param);
					if (scmInvWareHouse != null) {
						scmInvItemWrSum.setWareHouseName(scmInvWareHouse.getWhName());
					}
				}
				if (StringUtils.isNotBlank(scmInvItemWrSum.getUseOrgUnitNo())) {
					OrgBaseUnit2 orgBaseUnit = orgBaseUnitBiz.selectbyOrgNo(scmInvItemWrSum.getUseOrgUnitNo(), param);
					if (orgBaseUnit != null) {
						scmInvItemWrSum.setWareHouseName(orgBaseUnit.getOrgUnitName());
					}
				}
				if (scmInvItemWrSum.getItemId() > 0) {
					ScmMaterial2 scmMaterial = scmMaterialBiz.selectByItemId(scmInvItemWrSum.getItemId(), param);
					if (scmMaterial != null) {
						scmInvItemWrSum.setItemNo(scmMaterial.getItemNo());
						scmInvItemWrSum.setItemName(scmMaterial.getItemName());
						scmInvItemWrSum.setSpec(scmMaterial.getSpec());
						if (scmMaterial.getGroupId() > 0) {
							ScmMaterialGroup scmMaterialGroup = scmMaterialGroupBiz
									.selectDirect(scmMaterial.getGroupId(), param);
							if (scmMaterialGroup != null) {
								scmInvItemWrSum.setClassName(scmMaterialGroup.getClassName());
							}
						}
					}
				}
				if (scmInvItemWrSum.getUnit() > 0) {
					// 库存单位
					ScmMeasureUnit scmMeasureUnit = scmMeasureUnitBiz.selectDirect(scmInvItemWrSum.getUnit(), param);
					if (scmMeasureUnit != null) {
						scmInvItemWrSum.setUnitName(scmMeasureUnit.getUnitName());
					}
				}
				if (scmInvItemWrSum.getAddInQty() != null) {
					if (BigDecimal.ZERO.compareTo(scmInvItemWrSum.getAddInQty()) != 0) {
						scmInvItemWrSum.setAddInPrice(scmInvItemWrSum.getAddInAmt()
								.divide(scmInvItemWrSum.getAddInQty(), 2, RoundingMode.HALF_UP));
						scmInvItemWrSum.setAddInTaxPrice(scmInvItemWrSum.getAddInTaxAmt()
								.divide(scmInvItemWrSum.getAddInQty(), 2, RoundingMode.HALF_UP));
					}
				}

			}
		}
		return list;
	}

	@Override
	public List<ScmInvStorageAgePrimnessAnalysis> selectScmInvStorageAgePrimnessAnalysis(
			HttpServletRequest httpServletRequest) throws AppException {
		// 1 获取查询参数
		String currentOrgUnitNo = httpServletRequest.getParameter("orgUnitNo");
		String currentControlUnitNo = httpServletRequest.getParameter("controlUnitNo");
		String invOrgUnitNo = httpServletRequest.getParameter("invOrgUnitNo");

		String whName = httpServletRequest.getParameter("whName");
		int wareHouseId = 0;
		if (StringUtils.isNotBlank(whName) && StringUtils.isNumeric(whName)) {
			wareHouseId = (Integer.parseInt(whName));
		}

		String materialName = httpServletRequest.getParameter("materialName");
		int materialId = 0;
		if (StringUtils.isNotBlank(materialName) && StringUtils.isNumeric(materialName)) {
			materialId = (Integer.parseInt(materialName));
		}

		String lot = httpServletRequest.getParameter("lot");
		String storageAgeStr = httpServletRequest.getParameter("storageAge");
		String primnessDaysStr = httpServletRequest.getParameter("primnessDays");

		int storageAge = 0;
		if (StringUtils.isNotBlank(storageAgeStr)) {
			storageAge = Integer.parseInt(storageAgeStr);
		}

		int primnessDays = 0;
		if (StringUtils.isNotBlank(primnessDaysStr)) {
			primnessDays = Integer.parseInt(primnessDaysStr);
		}

		// 2 设置查询条件
		HashMap<String, Object> map = new HashMap<>();

		Param param = new Param();
		param.setOrgUnitNo(currentOrgUnitNo);
		param.setControlUnitNo(currentControlUnitNo);

		List<OrgCompany2> orgCompanyList = orgCompanyBiz.findChild(currentOrgUnitNo, param);
		if (orgCompanyList != null && orgCompanyList.size() > 0) {
			StringBuffer orgCommpanys = new StringBuffer("");
			for (int i = 0; i < orgCompanyList.size(); i++) {
				if (StringUtils.isNotBlank(orgCommpanys.toString()))
					orgCommpanys.append(",");
				orgCommpanys.append(orgCompanyList.get(i).getOrgUnitNo());
			}
			map.put("finOrgUnitNo", orgCommpanys.toString());
		}

		if (StringUtils.isNotBlank(invOrgUnitNo)) {
			List<OrgStorage2> orgStorageList = orgStorageBiz.findChild(invOrgUnitNo, param);
			StringBuffer sbInv = new StringBuffer("");
			if (orgStorageList == null || orgStorageList.isEmpty()) {
				map.put("invOrgUnitNo", "'" + invOrgUnitNo + "'");
			} else {
				for (OrgStorage2 orgStorage : orgStorageList) {
					if (StringUtils.isNotBlank(sbInv.toString()))
						sbInv.append(",");
					sbInv.append("'").append(orgStorage.getOrgUnitNo()).append("'");
				}
				map.put("invOrgUnitNo", sbInv.toString());
			}
		}

		map.put("wareHouseId", wareHouseId);
		map.put("materialId", materialId);
		map.put("lot", lot);
		map.put("storageAge", storageAge);
		map.put("primnessDays", primnessDays);

		// 3 查询数据库
		List<ScmInvStorageAgePrimnessAnalysis> list = ((ScmInvReportDAO) dao)
				.selectScmInvStorageAgePrimnessAnalysis(map);

		// 4 数据处理及返回
		if (list != null && list.size() > 0) {
			for (ScmInvStorageAgePrimnessAnalysis storageAgePrimnessAnalysis : list) {
				if (storageAgePrimnessAnalysis.getUnit() > 0) {
					// 库存单位
					ScmMeasureUnit scmMeasureUnit = scmMeasureUnitBiz.selectDirect(storageAgePrimnessAnalysis.getUnit(),
							param);
					if (scmMeasureUnit != null) {
						storageAgePrimnessAnalysis.setUnitName(scmMeasureUnit.getUnitName());
					}
				}

				if (StringUtils.isNotBlank(storageAgePrimnessAnalysis.getOrgUnitNo())) {
					OrgBaseUnit2 orgBaseUnit = orgBaseUnitBiz.selectbyOrgNo(storageAgePrimnessAnalysis.getOrgUnitNo(),
							param);
					if (orgBaseUnit != null) {
						storageAgePrimnessAnalysis.setOrgUnitName(orgBaseUnit.getOrgUnitName());
					}
				}

				if (storageAgePrimnessAnalysis.getWareHouseId() > 0) {
					// 仓库
					ScmInvWareHouse scmInvWareHouse = scmInvWareHouseBiz
							.selectDirect(storageAgePrimnessAnalysis.getWareHouseId(), param);
					if (scmInvWareHouse != null) {
						storageAgePrimnessAnalysis.setWareHouseName(scmInvWareHouse.getWhName());
					}
				}

				if (BigDecimal.ZERO.compareTo(storageAgePrimnessAnalysis.getStockQty()) != 0) {
					storageAgePrimnessAnalysis.setStockPrice(storageAgePrimnessAnalysis.getStockAmt()
							.divide(storageAgePrimnessAnalysis.getStockQty(), 2, RoundingMode.HALF_UP));
				}
			}
		}

		return list;
	}

	@Override
	public List<ScmInvDepositorySumSup> selectScmInvDepositorySumSup(HttpServletRequest httpServletRequest)
			throws AppException {
		List<ScmInvDepositorySumSup> scmInvDepositorySumSupList = new ArrayList<>();
		// 1 获取查询参数
		String currentOrgUnitNo = httpServletRequest.getParameter("orgUnitNo");
		String currentControlUnitNo = httpServletRequest.getParameter("controlUnitNo");
		String finOrgUnitNo = httpServletRequest.getParameter("finOrgUnitNo");
		String whName = httpServletRequest.getParameter("whName");
		int wareHouseId = 0;
		if (StringUtils.isNotBlank(whName) && StringUtils.isNumeric(whName)) {
			wareHouseId = (Integer.parseInt(whName));
		}
		String begDate = httpServletRequest.getParameter("begDate");
		String endDate = httpServletRequest.getParameter("endDate");
		String materialClassName = httpServletRequest.getParameter("materialClassName");
		String materialName = httpServletRequest.getParameter("materialName");
		StringBuffer materialIds = new StringBuffer("");
		if (StringUtils.isNotBlank(materialName)) {
			String[] idList = StringUtils.split(materialName, ",");
			for (String id : idList) {
				if (StringUtils.isBlank(id))
					continue;
				if (StringUtils.isNotBlank(materialIds.toString()))
					materialIds.append(",");
				materialIds.append(id);
			}
		}
		String vendorName = httpServletRequest.getParameter("vendorName");
		StringBuffer vendorIds = new StringBuffer("");
		if (StringUtils.isNotBlank(vendorName)) {
			String[] idList = StringUtils.split(vendorName, ",");
			for (String id : idList) {
				if (StringUtils.isBlank(id))
					continue;
				if (StringUtils.isNotBlank(vendorIds.toString()))
					vendorIds.append(",");
				vendorIds.append(id);
			}
		}
		String noPost = "1".equals(httpServletRequest.getParameter("noPost")) ? "Y" : "N";
		String taxRate = httpServletRequest.getParameter("taxRate");
		String type = "M";

		if (StringUtils.isBlank(begDate) || StringUtils.isBlank(endDate))
			return scmInvDepositorySumSupList;
		Param param = new Param();
		param.setOrgUnitNo(currentOrgUnitNo);
		param.setControlUnitNo(currentControlUnitNo);
		HashMap<String, Object> map = new HashMap<>();
		StringBuffer reqOrgUnitNos = new StringBuffer("");

		List<OrgCompany2> orgCompanyList = orgCompanyBiz.findChild(finOrgUnitNo, param);
		if (orgCompanyList != null && orgCompanyList.size() > 0) {
			for (int i = 0; i < orgCompanyList.size(); i++) {
				if (StringUtils.isNotBlank(reqOrgUnitNos.toString()))
					reqOrgUnitNos.append(",");
				reqOrgUnitNos.append(orgCompanyList.get(i).getOrgUnitNo());
			}
			map.put("reqFinOrgUnitNo", reqOrgUnitNos.toString());
		}

		int materialClassId = 0;
		StringBuffer sbMaterilaClass = new StringBuffer("");
		if (StringUtils.isNotBlank(materialClassName) && StringUtils.isNumeric(materialClassName)) {
			materialClassId = (Integer.parseInt(materialClassName));
			List<ScmMaterialGroup> scmMaterialGroupList = scmMaterialGroupBiz.findChild(materialClassId, param);
			if (scmMaterialGroupList != null && scmMaterialGroupList.size() > 0) {
				for (ScmMaterialGroup scmMaterialGroup : scmMaterialGroupList) {
					if (StringUtils.isNotBlank(sbMaterilaClass.toString()))
						sbMaterilaClass.append(",");
					sbMaterilaClass.append(scmMaterialGroup.getId());
				}
			} else {
				sbMaterilaClass.append(materialClassId);
			}
		}

		StringBuffer orgUnitNos = new StringBuffer("");
		String invOrgUnitNo = currentOrgUnitNo;
		if (StringUtils.isNotBlank(invOrgUnitNo)) {
			String[] noList = StringUtils.split(invOrgUnitNo, ",");
			for (String no : noList) {
				if (StringUtils.isBlank(no))
					continue;
				List<OrgStorage2> orgStorageList = orgStorageBiz.findChild(no, param);
				if (orgStorageList != null && !orgStorageList.isEmpty()) {
					for (OrgStorage2 orgStorage : orgStorageList) {
						if (StringUtils.isNotBlank(orgUnitNos.toString()))
							orgUnitNos.append(",");
						orgUnitNos.append("'").append(orgStorage.getOrgUnitNo()).append("'");
					}
				} else {
					orgUnitNos.append("'").append(no).append("'");
				}
			}
		}

		map.put("orgUnitNo", orgUnitNos.toString());
		map.put("begDate", begDate);
		map.put("endDate", endDate);
		map.put("materialIds", materialIds.toString());
		map.put("materialClassIds", sbMaterilaClass.toString());
		map.put("vendorIds", vendorIds.toString());
		map.put("wareHouseId", wareHouseId);
		if ("Y".equals(noPost)) {
			noPost = "1";
		} else {
			noPost = "0";
		}
		map.put("noPost", noPost);
		if (StringUtils.isNotBlank(taxRate) && !StringUtils.equals("-1", taxRate)) {
			map.put("taxRate", taxRate);
		}
		map.put("type", type);
		// 2 查询数据库
		List<ScmInvDepositorySumSup> list = ((ScmInvReportDAO) dao).selectScmInvDepositorySumSup(map);
		// 3 数据处理及返回
		if (list != null && list.size() > 0) {
			for (ScmInvDepositorySumSup scmInvDepositorySumSup : list) {
				if (StringUtils.isNotBlank(scmInvDepositorySumSup.getReqFinOrgUnitNo())) {
					OrgBaseUnit2 orgBaseUnit = orgBaseUnitBiz.selectbyOrgNo(scmInvDepositorySumSup.getReqFinOrgUnitNo(),
							param);
					if (orgBaseUnit != null) {
						scmInvDepositorySumSup.setReqFinOrgUnitName(orgBaseUnit.getOrgUnitName());
					}
				}

				if (StringUtils.isNotBlank(scmInvDepositorySumSup.getOrgUnitNo())) {
					OrgBaseUnit2 orgBaseUnit = orgBaseUnitBiz.selectbyOrgNo(scmInvDepositorySumSup.getOrgUnitNo(),
							param);
					if (orgBaseUnit != null) {
						scmInvDepositorySumSup.setOrgUnitName(orgBaseUnit.getOrgUnitName());
					}
				}
				if (scmInvDepositorySumSup.getVendorId() > 0) {
					// 供应商
					Scmsupplier2 scmsupplier = scmsupplierBiz.selectDirect(scmInvDepositorySumSup.getVendorId(), param);
					if (scmsupplier != null) {
						scmInvDepositorySumSup.setVendorName(scmsupplier.getVendorName());
					}
				}
				if (scmInvDepositorySumSup.getWareHouseId() > 0) {
					// 仓库
					ScmInvWareHouse scmInvWareHouse = scmInvWareHouseBiz
							.selectDirect(scmInvDepositorySumSup.getWareHouseId(), param);
					if (scmInvWareHouse != null) {
						scmInvDepositorySumSup.setWareHouseName(scmInvWareHouse.getWhName());
					}
				}
				if (StringUtils.isNotBlank(scmInvDepositorySumSup.getUseOrgUnitNo())) {
					OrgBaseUnit2 orgBaseUnit = orgBaseUnitBiz.selectbyOrgNo(scmInvDepositorySumSup.getUseOrgUnitNo(),
							param);
					if (orgBaseUnit != null) {
						scmInvDepositorySumSup.setWareHouseName(orgBaseUnit.getOrgUnitName());
					}
				}
				if (scmInvDepositorySumSup.getItemId() > 0) {
					ScmMaterial2 scmMaterial = scmMaterialBiz.selectByItemId(scmInvDepositorySumSup.getItemId(), param);
					if (scmMaterial != null) {
						scmInvDepositorySumSup.setItemNo(scmMaterial.getItemNo());
						scmInvDepositorySumSup.setItemName(scmMaterial.getItemName());
						scmInvDepositorySumSup.setSpec(scmMaterial.getSpec());
						if (scmMaterial.getGroupId() > 0) {
							ScmMaterialGroup scmMaterialGroup = scmMaterialGroupBiz
									.selectDirect(scmMaterial.getGroupId(), param);
							if (scmMaterialGroup != null) {
								scmInvDepositorySumSup.setClassName(scmMaterialGroup.getClassName());
							}
						}
					}
				}
				if (scmInvDepositorySumSup.getUnit() > 0) {
					// 库存单位
					ScmMeasureUnit scmMeasureUnit = scmMeasureUnitBiz.selectDirect(scmInvDepositorySumSup.getUnit(),
							param);
					if (scmMeasureUnit != null) {
						scmInvDepositorySumSup.setUnitName(scmMeasureUnit.getUnitName());
					}
				}
			}
		}
		return list;
	}

	@Override
	public List<ScmInvInWarehsItemClass2> selectNewScmInvInWarehsItemClass(HttpServletRequest httpServletRequest)
			throws AppException {
		// 1 获取查询参数
		String currentOrgUnitNo = httpServletRequest.getParameter("orgUnitNo");
		String currentControlUnitNo = httpServletRequest.getParameter("controlUnitNo");
		String invOrgUnitNo = httpServletRequest.getParameter("invOrgUnitNo");
		String begDate = httpServletRequest.getParameter("begDate");
		String endDate = httpServletRequest.getParameter("endDate");
		String standardName = httpServletRequest.getParameter("standardName");
		String materialClassName = httpServletRequest.getParameter("materialClassName");
		String whOrDept = httpServletRequest.getParameter("whOrDept");
		String vendorName = httpServletRequest.getParameter("vendorName");
		String venderClassName = httpServletRequest.getParameter("venderClassName");
		String stockType = httpServletRequest.getParameter("stockType");
		String noPost = "1".equals(httpServletRequest.getParameter("noPost")) ? "Y" : "N";
		String longNo = httpServletRequest.getParameter("longNo");

		if (StringUtils.isBlank(begDate) || StringUtils.isBlank(endDate))
			return null;
		Param param = new Param();
		param.setOrgUnitNo(currentOrgUnitNo);
		param.setControlUnitNo(currentControlUnitNo);
		HashMap<String, Object> map = new HashMap<>();
		if (StringUtils.isBlank(invOrgUnitNo)) {
			List<OrgStorage2> orgStorageList = orgStorageBiz.findChild(currentOrgUnitNo, param);
			if (orgStorageList != null) {
				invOrgUnitNo = orgStorageList.get(0).getOrgUnitNo();
			} else {
				invOrgUnitNo = currentOrgUnitNo;
			}
		}
		map.put("orgUnitNo", invOrgUnitNo);
		map.put("begDate", begDate);
		map.put("endDate", endDate);

		if (StringUtils.isNotBlank(vendorName)) {
			map.put("vendorId", vendorName);
		}
		/*
		 * if (StringUtils.isNotBlank(venderClassName) &&
		 * StringUtils.isNumeric(venderClassName)) { map.put("materialClassId",
		 * venderClassName); }
		 */
		long wareHouseId = 0;
		if (StringUtils.isBlank(stockType))
			stockType = "0";
		map.put("stockType", stockType);
		if (StringUtils.isNotBlank(whOrDept)) {
			map.put("whOrDept", whOrDept);
		}

		StringBuffer sbMaterilaClass = new StringBuffer("");
		if (StringUtils.isNotBlank(materialClassName)) {
			String[] materialClassNameList = StringUtils.split(materialClassName, ",");
			for (String materialClass : materialClassNameList) {
				if (StringUtils.isBlank(materialClass))
					continue;
				int materialClassId = Integer.parseInt(materialClass);
				List<ScmMaterialGroup> scmMaterialGroupList = scmMaterialGroupBiz.findChild(materialClassId, param);
				if (scmMaterialGroupList != null && scmMaterialGroupList.size() > 0) {
					for (ScmMaterialGroup scmMaterialGroup : scmMaterialGroupList) {
						if (StringUtils.isNotBlank(sbMaterilaClass.toString()))
							sbMaterilaClass.append(",");
						sbMaterilaClass.append(String.valueOf(scmMaterialGroup.getId()));
					}
				}
			}
		}
		if (StringUtils.isNotBlank(sbMaterilaClass.toString())) {
			map.put("materialClassId", sbMaterilaClass.toString());
		}

		if (StringUtils.isNotBlank(standardName) && StringUtils.isNumeric(standardName)) {
			map.put("standardName", standardName);
		}

		if ("Y".equals(noPost)) {
			noPost = "1";
		} else {
			noPost = "0";
		}
		map.put("noPost", noPost);

		map.put("longNo", longNo);
		// 2 查询数据库
		List<ScmInvInWarehsItemClass2> scmInvInWarehsItemClassList = ((ScmInvReportDAO) dao)
				.selectNewScmInvInWarehsItemClass(map);
		return scmInvInWarehsItemClassList;
	}

	@Override
	public List<ScmInvItemWrSum> selectScmInvItemWrSupplierSum(HttpServletRequest httpServletRequest)
			throws AppException {
		List<ScmInvItemWrSum> scmInvItemWrSumList = new ArrayList<>();
		// 1 获取查询参数
		String currentOrgUnitNo = httpServletRequest.getParameter("orgUnitNo");
		String currentControlUnitNo = httpServletRequest.getParameter("controlUnitNo");
		String invOrgUnitNo = httpServletRequest.getParameter("invOrgUnitNo");
		String whOrDept = httpServletRequest.getParameter("whOrDept");
		String begDate = httpServletRequest.getParameter("begDate");
		String endDate = httpServletRequest.getParameter("endDate");
		String materialClassName = httpServletRequest.getParameter("materialClassName");
		String materialName = httpServletRequest.getParameter("materialName");
		StringBuffer materialIds = new StringBuffer("");
		if (StringUtils.isNotBlank(materialName)) {
			String[] idList = StringUtils.split(materialName, ",");
			for (String id : idList) {
				if (StringUtils.isBlank(id))
					continue;
				if (StringUtils.isNotBlank(materialIds.toString()))
					materialIds.append(",");
				materialIds.append(id);
			}
		}
		String vendorName = httpServletRequest.getParameter("vendorName");
		StringBuffer vendorIds = new StringBuffer("");
		if (StringUtils.isNotBlank(vendorName)) {
			String[] idList = StringUtils.split(vendorName, ",");
			for (String id : idList) {
				if (StringUtils.isBlank(id))
					continue;
				if (StringUtils.isNotBlank(vendorIds.toString()))
					vendorIds.append(",");
				vendorIds.append(id);
			}
		}
		String stockType = httpServletRequest.getParameter("stockType");
		String noPost = "1".equals(httpServletRequest.getParameter("noPost")) ? "Y" : "N";
		Param param = new Param();
		param.setOrgUnitNo(currentOrgUnitNo);
		param.setControlUnitNo(currentControlUnitNo);
		String taxRate = httpServletRequest.getParameter("taxRate");

		String type = "V";
		if (StringUtils.isBlank(begDate) || StringUtils.isBlank(endDate))
			return scmInvItemWrSumList;

		HashMap<String, Object> map = new HashMap<>();
		StringBuffer orgUnitNos = new StringBuffer("");
		if (StringUtils.isNotBlank(invOrgUnitNo)) {
			String[] noList = StringUtils.split(invOrgUnitNo, ",");
			for (String no : noList) {
				if (StringUtils.isBlank(no))
					continue;
				List<OrgStorage2> orgStorageList = orgStorageBiz.findChild(no, param);
				if (orgStorageList != null && !orgStorageList.isEmpty()) {
					for (OrgStorage2 orgStorage : orgStorageList) {
						if (StringUtils.isNotBlank(orgUnitNos.toString()))
							orgUnitNos.append(",");
						orgUnitNos.append("'").append(orgStorage.getOrgUnitNo()).append("'");
					}
				} else {
					orgUnitNos.append("'").append(no).append("'");
				}
			}
		}
//        List<OrgStorage2> orgStorageList = orgStorageBiz.findChild(currentOrgUnitNo, param);
//        if(orgStorageList != null && !orgStorageList.isEmpty()) {
//            for(OrgStorage2 orgStorage:orgStorageList) {
//                if(StringUtils.isNotBlank(orgUnitNos.toString()))
//                    orgUnitNos.append(",");
//                orgUnitNos.append("'").append(orgStorage.getOrgUnitNo()).append("'");
//            }
//        }else {
//            orgUnitNos.append("'").append(currentOrgUnitNo).append("'");
//        }
//        map.put("orgUnitNo", invOrgUnitNo);
		int materialClassId = 0;
		StringBuffer sbMaterilaClass = new StringBuffer("");
		if (StringUtils.isNotBlank(materialClassName) && StringUtils.isNumeric(materialClassName)) {
			materialClassId = (Integer.parseInt(materialClassName));
			List<ScmMaterialGroup> scmMaterialGroupList = scmMaterialGroupBiz.findChild(materialClassId, param);
			if (scmMaterialGroupList != null && scmMaterialGroupList.size() > 0) {
				for (ScmMaterialGroup scmMaterialGroup : scmMaterialGroupList) {
					if (StringUtils.isNotBlank(sbMaterilaClass.toString()))
						sbMaterilaClass.append(",");
					sbMaterilaClass.append(scmMaterialGroup.getId());
				}
			} else {
				sbMaterilaClass.append(materialClassId);
			}
		}
		map.put("orgUnitNo", orgUnitNos.toString());
		map.put("begDate", begDate);
		map.put("endDate", endDate);
		map.put("materialIds", materialIds.toString());
		map.put("materialClassIds", sbMaterilaClass.toString());
		map.put("vendorIds", vendorIds.toString());
		long wareHouseId = 0;
		if (StringUtils.isBlank(stockType))
			stockType = "0";
		map.put("stockType", stockType);
		if (StringUtils.isNotBlank(whOrDept)) {
			if (!StringUtils.equals("0", StringUtils.left(whOrDept, 1))) {
				map.put("wareHouseId", whOrDept);
			} else {
				map.put("useOrgUnitNo", whOrDept);
			}
		}
		if ("Y".equals(noPost)) {
			noPost = "1";
		} else {
			noPost = "0";
		}
		map.put("noPost", noPost);
		if (StringUtils.isNotBlank(taxRate) && !StringUtils.equals("-1", taxRate)) {
			map.put("taxRate", taxRate);
		}
		map.put("type", type);
		// 2 查询数据库
		List<ScmInvItemWrSum> list = ((ScmInvReportDAO) dao).selectScmInvItemWrSum(map);
		// 3 数据处理及返回
		if (list != null && list.size() > 0) {
			for (ScmInvItemWrSum scmInvItemWrSum : list) {
				if (StringUtils.isNotBlank(scmInvItemWrSum.getOrgUnitNo())) {
					OrgBaseUnit2 orgBaseUnit = orgBaseUnitBiz.selectbyOrgNo(scmInvItemWrSum.getOrgUnitNo(), param);
					if (orgBaseUnit != null) {
						scmInvItemWrSum.setOrgUnitName(orgBaseUnit.getOrgUnitName());
					}
				}
				if (BigDecimal.ZERO.compareTo(scmInvItemWrSum.getAddInQty()) != 0) {
					scmInvItemWrSum.setAddInPrice(scmInvItemWrSum.getAddInAmt().divide(scmInvItemWrSum.getAddInQty(), 2,
							RoundingMode.HALF_UP));
					scmInvItemWrSum.setAddInTaxPrice(scmInvItemWrSum.getAddInTaxAmt()
							.divide(scmInvItemWrSum.getAddInQty(), 2, RoundingMode.HALF_UP));
				}
				if ("V".equals(type)) {
					if (scmInvItemWrSum.getWareHouseId() > 0) {
						// 仓库
						ScmInvWareHouse scmInvWareHouse = scmInvWareHouseBiz
								.selectDirect(scmInvItemWrSum.getWareHouseId(), param);
						if (scmInvWareHouse != null) {
							scmInvItemWrSum.setWareHouseName(scmInvWareHouse.getWhName());
						}
					}
					if (StringUtils.isNotBlank(scmInvItemWrSum.getUseOrgUnitNo())) {
						OrgBaseUnit2 orgBaseUnit = orgBaseUnitBiz.selectbyOrgNo(scmInvItemWrSum.getUseOrgUnitNo(),
								param);
						if (orgBaseUnit != null) {
							scmInvItemWrSum.setWareHouseName(orgBaseUnit.getOrgUnitName());
						}
					}
				}
			}
		}
		return list;
	}

	@Override
	public List<ScmInvStorageAgeAnalysis> selectScmInvStorageAgeAnalysis(HttpServletRequest httpServletRequest)
			throws AppException {
		List<ScmInvItemWrSum> scmInvItemWrSumList = new ArrayList<>();
		// 1 获取查询参数
		String currentOrgUnitNo = httpServletRequest.getParameter("orgUnitNo");
		String currentControlUnitNo = httpServletRequest.getParameter("controlUnitNo");
		String invOrgUnitNo = httpServletRequest.getParameter("invOrgUnitNo");
		invOrgUnitNo = StringUtils.replace(invOrgUnitNo, "'", "");
		String whName = httpServletRequest.getParameter("whName");
		String materialClassName = httpServletRequest.getParameter("materialClassName");
		String materialName = httpServletRequest.getParameter("materialName");
		String storageAge = httpServletRequest.getParameter("storageAge");
		String interval = httpServletRequest.getParameter("divideDate");

		StringBuffer materialIds = new StringBuffer("");
		if (StringUtils.isNotBlank(materialName)) {
			String[] idList = StringUtils.split(materialName, ",");
			for (String id : idList) {
				if (StringUtils.isBlank(id))
					continue;
				if (StringUtils.isNotBlank(materialIds.toString()))
					materialIds.append(",");
				materialIds.append(id);
			}
		}
		Param param = new Param();
		param.setOrgUnitNo(currentOrgUnitNo);
		param.setControlUnitNo(currentControlUnitNo);
		HashMap<String, Object> map = new HashMap<>();
		StringBuffer orgUnitNos = new StringBuffer("");
		HashMap<String, String> orgUnitNameMap = new HashMap<>();
		if (StringUtils.isNotBlank(invOrgUnitNo)) {
			String[] noList = StringUtils.split(invOrgUnitNo, ",");
			for (String no : noList) {
				if (StringUtils.isBlank(no))
					continue;
				List<OrgStorage2> orgStorageList = orgStorageBiz.findChild(no, param);
				if (orgStorageList != null && !orgStorageList.isEmpty()) {
					for (OrgStorage2 orgStorage : orgStorageList) {
						if (StringUtils.isNotBlank(orgUnitNos.toString()))
							orgUnitNos.append(",");
						orgUnitNos.append("'").append(orgStorage.getOrgUnitNo()).append("'");
						orgUnitNameMap.put(orgStorage.getOrgUnitNo(), orgStorage.getOrgUnitName());
					}
				} else {
					orgUnitNos.append("'").append(no).append("'");
				}
			}
		}
		int materialClassId = 0;
		StringBuffer sbMaterilaClass = new StringBuffer("");
		if (StringUtils.isNotBlank(materialClassName) && StringUtils.isNumeric(materialClassName)) {
			materialClassId = (Integer.parseInt(materialClassName));
			List<ScmMaterialGroup> scmMaterialGroupList = scmMaterialGroupBiz.findChild(materialClassId, param);
			if (scmMaterialGroupList != null && scmMaterialGroupList.size() > 0) {
				for (ScmMaterialGroup scmMaterialGroup : scmMaterialGroupList) {
					if (StringUtils.isNotBlank(sbMaterilaClass.toString()))
						sbMaterilaClass.append(",");
					sbMaterilaClass.append(scmMaterialGroup.getId());
				}
			} else {
				sbMaterilaClass.append(materialClassId);
			}
		}
		map.put("invOrgUnitNo", orgUnitNos.toString());
		map.put("materialIds", materialIds.toString());
		map.put("materialClassIds", sbMaterilaClass.toString());
		map.put("wareHouseId", whName);

		// 2 查询数据库
		List<ScmInvStorageAgeAnalysis> list = ((ScmInvReportDAO) dao).selectScmInvStorageAgeAnalysis(map);

		List<String> intervalList = Arrays.asList(interval.split(","));
		List<Integer> dayslist = new ArrayList<Integer>();
		HashMap<Integer, Integer> daysMap = new HashMap<>();

		for (String days : intervalList) {
			if (StringUtils.isNotBlank(days) && StringUtils.isNumeric(days)) {
				dayslist.add(Integer.parseInt(days));
				daysMap.put(Integer.parseInt(days), 0);
			}
		}
		Collections.sort(dayslist); // 升序排列

		int indexDays = dayslist.size();
		int maxDays = dayslist.get(indexDays - 1);
		for (ScmInvStorageAgeAnalysis invStorageAgeAnalysis : list) {
			for (int i = 0; i < dayslist.size(); i++) {
				Integer days = dayslist.get(i);
				if (invStorageAgeAnalysis.getStorageAge() < days && !(invStorageAgeAnalysis.getStorageAge() > days)) {
					invStorageAgeAnalysis.setIntervaltype("库存" + days + "天");
					daysMap.put(days, 1);
					break;
				}

				if ((i + 1) == indexDays) {
					invStorageAgeAnalysis.setIntervaltype("库存大于" + maxDays + "天");
				}
			}

			if (orgUnitNameMap.containsKey(invStorageAgeAnalysis.getOrgUnitNo())) {
				invStorageAgeAnalysis.setOrgUnitName(orgUnitNameMap.get(invStorageAgeAnalysis.getOrgUnitNo()));
			}

		}

		for (Integer days : daysMap.keySet()) {
			Integer daysFlag = daysMap.get(days);
			if (daysFlag == 0) {
				ScmInvStorageAgeAnalysis scmInvStorageAgeAnalysis = new ScmInvStorageAgeAnalysis();
				BeanUtils.copyProperties(list.get(0), scmInvStorageAgeAnalysis);
				scmInvStorageAgeAnalysis.setQty(BigDecimal.ZERO);
				scmInvStorageAgeAnalysis.setTaxAmt(BigDecimal.ZERO);
				scmInvStorageAgeAnalysis.setIntervaltype("库存" + days + "天");
				list.add(scmInvStorageAgeAnalysis);
			}

		}

		return list;
	}

	@Override
	public List<ScmInvInWareMonthAnalysis> selectScmInvInWareMonthAnalysis(HttpServletRequest httpServletRequest)
			throws AppException {
		List<ScmInvItemWrSum> scmInvItemWrSumList = new ArrayList<>();
		// 1 获取查询参数
		String currentOrgUnitNo = httpServletRequest.getParameter("orgUnitNo");
		String currentControlUnitNo = httpServletRequest.getParameter("controlUnitNo");
		String invOrgUnitNo = httpServletRequest.getParameter("invOrgUnitNo");
		invOrgUnitNo = StringUtils.replace(invOrgUnitNo, "'", "");
		String materialClassName = httpServletRequest.getParameter("materialClassName");
		String materialName = httpServletRequest.getParameter("materialName");
		String begDate = httpServletRequest.getParameter("begDate");
		String endDate = httpServletRequest.getParameter("endDate");
		String noPost = httpServletRequest.getParameter("noPost");
		String brand = httpServletRequest.getParameter("brand");// 品牌信息
		Param param = new Param();
		param.setOrgUnitNo(currentOrgUnitNo);
		param.setControlUnitNo(currentControlUnitNo);
		if (StringUtils.isNotBlank(begDate) && StringUtils.isNumeric(begDate)) {
			PeriodCalendar periodCalendar = periodCalendarBiz.select(Long.parseLong(begDate), param);
			if (periodCalendar != null) {
				begDate = FormatUtils.fmtDate(periodCalendar.getStartDate());
			}
		}
		if (StringUtils.isNotBlank(endDate) && StringUtils.isNumeric(endDate)) {
			PeriodCalendar periodCalendar = periodCalendarBiz.select(Long.parseLong(endDate), param);
			if (periodCalendar != null) {
				endDate = FormatUtils.fmtDate(periodCalendar.getEndDate());
			}
		}
		StringBuffer materialIds = new StringBuffer("");
		if (StringUtils.isNotBlank(materialName)) {
			String[] idList = StringUtils.split(materialName, ",");
			for (String id : idList) {
				if (StringUtils.isBlank(id))
					continue;
				if (StringUtils.isNotBlank(materialIds.toString()))
					materialIds.append(",");
				materialIds.append(id);
			}
		}
		HashMap<String, Object> map = new HashMap<>();
		StringBuffer orgUnitNos = new StringBuffer("");
		HashMap<String, String> orgUnitNameMap = new HashMap<>();
		if (StringUtils.isNotBlank(invOrgUnitNo)) {
			String[] noList = StringUtils.split(invOrgUnitNo, ",");
			for (String no : noList) {
				if (StringUtils.isBlank(no))
					continue;
				List<OrgStorage2> orgStorageList = orgStorageBiz.findChild(no, param);
				if (orgStorageList != null && !orgStorageList.isEmpty()) {
					for (OrgStorage2 orgStorage : orgStorageList) {
						if (StringUtils.isNotBlank(orgUnitNos.toString()))
							orgUnitNos.append(",");
						orgUnitNos.append("'").append(orgStorage.getOrgUnitNo()).append("'");
						orgUnitNameMap.put(orgStorage.getOrgUnitNo(), orgStorage.getOrgUnitName());
					}
				} else {
					orgUnitNos.append("'").append(no).append("'");
				}
			}
		}
		int materialClassId = 0;
		StringBuffer sbMaterilaClass = new StringBuffer("");
		if (StringUtils.isNotBlank(materialClassName) && StringUtils.isNumeric(materialClassName)) {
			materialClassId = (Integer.parseInt(materialClassName));
			List<ScmMaterialGroup> scmMaterialGroupList = scmMaterialGroupBiz.findChild(materialClassId, param);
			if (scmMaterialGroupList != null && scmMaterialGroupList.size() > 0) {
				for (ScmMaterialGroup scmMaterialGroup : scmMaterialGroupList) {
					if (StringUtils.isNotBlank(sbMaterilaClass.toString()))
						sbMaterilaClass.append(",");
					sbMaterilaClass.append(scmMaterialGroup.getId());
				}
			} else {
				sbMaterilaClass.append(materialClassId);
			}
		}
		map.put("invOrgUnitNo", orgUnitNos.toString());
		map.put("materialIds", materialIds.toString());
		map.put("materialClassIds", sbMaterilaClass.toString());
		map.put("begDate", begDate);
		map.put("endDate", endDate);
		map.put("noPost", noPost);
		if(brand!=null&&StringUtils.isNotBlank(brand)) {
			map.put("brand", brand);
		}
		// 2 查询数据库
		List<ScmInvInWareMonthAnalysis> list = ((ScmInvReportDAO) dao).selectScmInvInWareMonthAnalysis(map);

		return list;
	}

	@Override
	public List<ScmInvInWareMonthAnalysis> selectScmInvSaleMonthAnalysis(HttpServletRequest httpServletRequest) {
		List<ScmInvItemWrSum> scmInvItemWrSumList = new ArrayList<>();
		// 1 获取查询参数
		String currentOrgUnitNo = httpServletRequest.getParameter("orgUnitNo");
		String currentControlUnitNo = httpServletRequest.getParameter("controlUnitNo");
		String invOrgUnitNo = httpServletRequest.getParameter("invOrgUnitNo");
		invOrgUnitNo = StringUtils.replace(invOrgUnitNo, "'", "");
		String materialClassName = httpServletRequest.getParameter("materialClassName");
		String materialName = httpServletRequest.getParameter("materialName");
		String custType = httpServletRequest.getParameter("custType");
		String begDate = httpServletRequest.getParameter("begDate");
		String endDate = httpServletRequest.getParameter("endDate");
		String noPost = httpServletRequest.getParameter("noPost");
		String brand = httpServletRequest.getParameter("brand");// 品牌信息
		Param param = new Param();
		param.setOrgUnitNo(currentOrgUnitNo);
		param.setControlUnitNo(currentControlUnitNo);
		if (StringUtils.isNotBlank(begDate) && StringUtils.isNumeric(begDate)) {
			PeriodCalendar periodCalendar = periodCalendarBiz.select(Long.parseLong(begDate), param);
			if (periodCalendar != null) {
				begDate = FormatUtils.fmtDate(periodCalendar.getStartDate());
			}
		}
		if (StringUtils.isNotBlank(endDate) && StringUtils.isNumeric(endDate)) {
			PeriodCalendar periodCalendar = periodCalendarBiz.select(Long.parseLong(endDate), param);
			if (periodCalendar != null) {
				endDate = FormatUtils.fmtDate(periodCalendar.getEndDate());
			}
		}
		StringBuffer materialIds = new StringBuffer("");
		if (StringUtils.isNotBlank(materialName)) {
			String[] idList = StringUtils.split(materialName, ",");
			for (String id : idList) {
				if (StringUtils.isBlank(id))
					continue;
				if (StringUtils.isNotBlank(materialIds.toString()))
					materialIds.append(",");
				materialIds.append(id);
			}
		}
		// 客户名称
		String custId = httpServletRequest.getParameter("custId");
		Page page = new Page();
		page.setModelClass(Customer.class);
		page.setShowCount(Integer.MAX_VALUE);
		param.setOrgUnitNo(currentControlUnitNo);
		if(StringUtils.isNotBlank(custType)&&StringUtils.isBlank(custId)) {
			page.getParam().put((ClassUtils.getFinalModelSimpleName(Customer.class) + "." + Customer.FN_CUSTTYPE), 
					new QueryParam((ClassUtils.getFinalModelSimpleName(Customer.class) + "." + Customer.FN_CUSTTYPE), QueryParam.QUERY_IN, custType));
		}else if(StringUtils.isNotBlank(custId)&&StringUtils.isBlank(custType)){
			page.getParam().put((ClassUtils.getFinalModelSimpleName(Customer.class) + "." + Customer.FN_ID), 
					new QueryParam((ClassUtils.getFinalModelSimpleName(Customer.class) + "." + Customer.FN_ID), QueryParam.QUERY_IN, custId));
		}else if(StringUtils.isNotBlank(custType)&&StringUtils.isNotBlank(custId)) {
			page.setSqlCondition(ClassUtils.getFinalModelSimpleName(Customer.class) + "." + Customer.FN_ID+" "+QueryParam.QUERY_IN+"("+custId+")"+" or "
		+ClassUtils.getFinalModelSimpleName(Customer.class) + "." + Customer.FN_CUSTTYPE+" "+ QueryParam.QUERY_IN+"("+custType+")");
		}
		HashMap<Long, String> custMap = new HashMap<Long, String>();
		StringBuffer custTypeId = new StringBuffer("");
		List<Customer> custList = customerBiz.queryPage(page, new ArrayList<String>(), "", param);
		if (custList != null && !custList.isEmpty()) {
			for (Customer customer : custList) {
				if(StringUtils.isNotBlank(custTypeId.toString()))
					custTypeId.append(",");
				custTypeId.append(customer.getId());
				custMap.put(customer.getId(), customer.getCustName());
			}
		}
		param.setOrgUnitNo(currentOrgUnitNo);
		HashMap<String, Object> map = new HashMap<>();
		StringBuffer orgUnitNos = new StringBuffer("");
		HashMap<String, String> orgUnitNameMap = new HashMap<>();
		if (StringUtils.isNotBlank(invOrgUnitNo)) {
			String[] noList = StringUtils.split(invOrgUnitNo, ",");
			for (String no : noList) {
				if (StringUtils.isBlank(no))
					continue;
				List<OrgStorage2> orgStorageList = orgStorageBiz.findChild(no, param);
				if (orgStorageList != null && !orgStorageList.isEmpty()) {
					for (OrgStorage2 orgStorage : orgStorageList) {
						if (StringUtils.isNotBlank(orgUnitNos.toString()))
							orgUnitNos.append(",");
						orgUnitNos.append("'").append(orgStorage.getOrgUnitNo()).append("'");
						orgUnitNameMap.put(orgStorage.getOrgUnitNo(), orgStorage.getOrgUnitName());
					}
				} else {
					orgUnitNos.append("'").append(no).append("'");
				}
			}
		}
		int materialClassId = 0;
		StringBuffer sbMaterilaClass = new StringBuffer("");
		if (StringUtils.isNotBlank(materialClassName) && StringUtils.isNumeric(materialClassName)) {
			materialClassId = (Integer.parseInt(materialClassName));
			List<ScmMaterialGroup> scmMaterialGroupList = scmMaterialGroupBiz.findChild(materialClassId, param);
			if (scmMaterialGroupList != null && scmMaterialGroupList.size() > 0) {
				for (ScmMaterialGroup scmMaterialGroup : scmMaterialGroupList) {
					if (StringUtils.isNotBlank(sbMaterilaClass.toString()))
						sbMaterilaClass.append(",");
					sbMaterilaClass.append(scmMaterialGroup.getId());
				}
			} else {
				sbMaterilaClass.append(materialClassId);
			}
		}
		map.put("invOrgUnitNo", orgUnitNos.toString());
		map.put("materialIds", materialIds.toString());
		map.put("materialClassIds", sbMaterilaClass.toString());
		map.put("begDate", begDate);
		map.put("endDate", endDate);
		map.put("noPost", noPost);
		map.put("custIds", custTypeId.toString());
		if(brand!=null&&StringUtils.isNotBlank(brand)) {
			map.put("brand", brand);
		}

		// 2 查询数据库
		List<ScmInvInWareMonthAnalysis> list = ((ScmInvReportDAO) dao).selectScmInvSaleMonthAnalysis(map);
		if (list != null && !list.isEmpty()) {
			for (ScmInvInWareMonthAnalysis scmInvInWareMonthAnalysis : list) {
				if (scmInvInWareMonthAnalysis.getCustId() != null) {
					scmInvInWareMonthAnalysis.setCustName(custMap.get(scmInvInWareMonthAnalysis.getCustId()));
				}
			}
		}

		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ScmInvInWarehsItemSum> selectScmInvInWarehsItemSum(HttpServletRequest httpServletRequest)
			throws AppException {
		ArrayList<ScmInvInWarehsItemSum> scmInvItemWrDetailsList = new ArrayList<ScmInvInWarehsItemSum>();
		// 1 获取查询参数
		String currentOrgUnitNo = httpServletRequest.getParameter("orgUnitNo");
		String currentControlUnitNo = httpServletRequest.getParameter("controlUnitNo");
		String invOrgUnitNo = httpServletRequest.getParameter("invOrgUnitNo");// 库存组织
		String whOrDept = httpServletRequest.getParameter("whOrDept");// 部门/仓库
		String begDate = httpServletRequest.getParameter("begDate");
		String endDate = httpServletRequest.getParameter("endDate");
		String materialClassName = httpServletRequest.getParameter("materialClassName");// 物资类别
		String materialName = httpServletRequest.getParameter("materialName");// 物资
		String summary1 = httpServletRequest.getParameter("summary1");// 汇总方式一
		String summary2 = httpServletRequest.getParameter("summary2");// 汇总方式二
		String vendorName = httpServletRequest.getParameter("vendor");// 供应商
		String stockType = httpServletRequest.getParameter("stockType");// 结存类型
		String noPost = "1".equals(httpServletRequest.getParameter("noPost")) ? "Y" : "N";// 是否过账
		String taxRate = httpServletRequest.getParameter("taxRate"); // 税率
		String brand = httpServletRequest.getParameter("brand");// 客户
		
		if (summary1 == null) {
			return scmInvItemWrDetailsList;
		}
		if (summary1 != null && summary2 != null && summary2.equals(summary1)) {
			summary2 = null;
		}
		if (StringUtils.isBlank(begDate) || StringUtils.isBlank(endDate))
			return scmInvItemWrDetailsList;
		StringBuffer materialIds = new StringBuffer("");
		if (StringUtils.isNotBlank(materialName)) {
			String[] idList = StringUtils.split(materialName, "|");
			for (String id : idList) {
				if (StringUtils.isBlank(id))
					continue;
				if (StringUtils.isNotBlank(materialIds.toString()))
					materialIds.append(",");
				materialIds.append(id);
			}
		}

		StringBuffer vendorIds = new StringBuffer("");
		if (StringUtils.isNotBlank(vendorName)) {
			String[] idList = StringUtils.split(vendorName, "|");
			for (String id : idList) {
				if (StringUtils.isBlank(id))
					continue;
				if (StringUtils.isNotBlank(vendorIds.toString()))
					vendorIds.append(",");
				vendorIds.append(id);
			}
		}

		Param param = new Param();
		param.setOrgUnitNo(currentOrgUnitNo);
		param.setControlUnitNo(currentControlUnitNo);
		HashMap<String, Object> map = new HashMap<>();
		HashMap<String, String> orgMap = new HashMap<String, String>();
		StringBuffer orgUnitNos = new StringBuffer("");
		if (StringUtils.isNotBlank(invOrgUnitNo)) {
			String[] noList = StringUtils.split(invOrgUnitNo, "|");
			for (String no : noList) {
				if (StringUtils.isBlank(no))
					continue;
				List<OrgStorage2> orgStorageList = orgStorageBiz.findChild(no, param);
				if (orgStorageList != null && !orgStorageList.isEmpty()) {
					for (OrgStorage2 orgStorage : orgStorageList) {
						if (StringUtils.isNotBlank(orgUnitNos.toString()))
							orgUnitNos.append(",");
						orgUnitNos.append("'").append(orgStorage.getOrgUnitNo()).append("'");
						orgMap.put(orgStorage.getOrgUnitNo(), orgStorage.getOrgUnitName());
					}
				} else {
					orgUnitNos.append("'").append(no).append("'");
				}
			}
		}
		int materialClassId = 0;
		StringBuffer sbMaterilaClass = new StringBuffer("");
		if (StringUtils.isNotBlank(materialClassName) && StringUtils.isNumeric(materialClassName)) {
			materialClassId = (Integer.parseInt(materialClassName));
			List<ScmMaterialGroup> scmMaterialGroupList = scmMaterialGroupBiz.findChild(materialClassId, param);
			if (scmMaterialGroupList != null && scmMaterialGroupList.size() > 0) {
				for (ScmMaterialGroup scmMaterialGroup : scmMaterialGroupList) {
					if (StringUtils.isNotBlank(sbMaterilaClass.toString()))
						sbMaterilaClass.append(",");
					sbMaterilaClass.append(scmMaterialGroup.getId());
				}
			} else {
				sbMaterilaClass.append(materialClassId);
			}
		}
		map.put("orgUnitNo", orgUnitNos.toString());
		map.put("begDate", begDate);
		map.put("endDate", endDate);
		map.put("materialIds", materialIds.toString());
		map.put("materialClassIds", sbMaterilaClass.toString());
		map.put("vendorIds", vendorIds.toString());
		if (StringUtils.isBlank(stockType))
			stockType = "0";
		map.put("stockType", stockType);
		if (StringUtils.isNotBlank(whOrDept)) {
			map.put("whOrDept", whOrDept);
		}
		if ("Y".equals(noPost)) {
			noPost = "1";
		} else {
			noPost = "0";
		}
		map.put("noPost", noPost);
		if (StringUtils.isNotBlank(taxRate) && !StringUtils.equals("-1", taxRate)) {
			map.put("taxRate", taxRate);
		}
		if(brand!=null&&StringUtils.isNotBlank(brand)) {
			map.put("brand", brand);
		}
		// 2 查询数据库
		List<ScmInvInWarehsItemSum> list = ((ScmInvReportDAO) dao).selectScmInvInWarehsItemSum(map);
		HashMap<Long, String> buyerMap = new HashMap<Long, String>();
		HashMap<String, ArrayList<String>> sortName = new HashMap<String, ArrayList<String>>();
		sortName.put("no", new ArrayList<String>(Arrays.asList("no")));
		sortName.put("itemNo", new ArrayList<String>(Arrays.asList("classCode", "itemNo")));
		sortName.put("vendorName", new ArrayList<String>(Arrays.asList("merchantType", "merchantName")));
		sortName.put("buyerName", new ArrayList<String>(Arrays.asList("staffId")));
		sortName.put("brand", new ArrayList<String>(Arrays.asList("brandId")));
		// 拼接其他结果集

		for (ScmInvInWarehsItemSum temp : list) {
			if (temp.getStaffId() > 0) {
				if (buyerMap.get(temp.getStaffId()) == null) {
					ScmPurBuyer2 scmPurBuyer = scmPurBuyerBiz.selectDirect(temp.getStaffId(), param);
					if (scmPurBuyer != null) {
						temp.setStaffName(scmPurBuyer.getBuyerName());
						buyerMap.put(temp.getStaffId(), scmPurBuyer.getBuyerName());
					}
				} else {
					temp.setStaffName(buyerMap.get(temp.getStaffId()));
				}
			}
		}
		ArrayList<String> sortNameList = new ArrayList<String>();
		sortNameList.addAll(sortName.get(summary1));
		if (summary2 != null) {
			sortNameList.addAll(sortName.get(summary2));
		}
		String[] array = (String[]) sortNameList.toArray(new String[sortNameList.size()]);
		String sorts[] = new String[array.length];
		for (int i = 0; i < array.length; i++) {
			sorts[i] = "ASC";
		}
		list = (List<ScmInvInWarehsItemSum>) ListSortUtil.sort(list, array, sorts);
		return list;
	}

	@Override
	public List<ScmInvInWarehsItemSum> selectScmInvSaleItemSum(HttpServletRequest httpServletRequest) {
		ArrayList<ScmInvInWarehsItemSum> scmInvItemWrDetailsList = new ArrayList<ScmInvInWarehsItemSum>();
		String currentOrgUnitNo = httpServletRequest.getParameter("orgUnitNo");
		String currentControlUnitNo = httpServletRequest.getParameter("controlUnitNo");
		String invOrgUnitNo = httpServletRequest.getParameter("invOrgUnitNo");// 库存组织
		String whOrDept = httpServletRequest.getParameter("whOrDept");// 部门或仓库
		String begDate = httpServletRequest.getParameter("begDate");
		String endDate = httpServletRequest.getParameter("endDate");
		String materialClassName = httpServletRequest.getParameter("materialClassName");
		String materialName = httpServletRequest.getParameter("materialName");// --
		String custType = httpServletRequest.getParameter("custType");// -- 客户类型
		String summary1 = httpServletRequest.getParameter("summary1");
		String summary2 = httpServletRequest.getParameter("summary2");
		String lot = httpServletRequest.getParameter("lot");// 批次
		String stockType = httpServletRequest.getParameter("stockType");// 类型
		String noPost = "1".equals(httpServletRequest.getParameter("noPost")) ? "Y" : "N";
//        String taxRate = httpServletRequest.getParameter("taxRate");//税率
		String vendorName = httpServletRequest.getParameter("vendorName");// 供应商
		String cust = httpServletRequest.getParameter("custNo");// 客户
		String sales = httpServletRequest.getParameter("sales");// 客户
		String brand = httpServletRequest.getParameter("brand");// 品牌信息
		if (summary1 == null) {
			return null;
		}
		if (summary1 != null && summary2 != null && summary2.equals(summary1)) {
			summary2 = null;
		}
		if (StringUtils.isBlank(begDate) || StringUtils.isBlank(endDate))
			return scmInvItemWrDetailsList;
		StringBuffer materialIds = new StringBuffer("");
		if (StringUtils.isNotBlank(materialName)) {
			String[] idList = StringUtils.split(materialName, "|");
			for (String id : idList) {
				if (StringUtils.isBlank(id))
					continue;
				if (StringUtils.isNotBlank(materialIds.toString()))
					materialIds.append(",");
				materialIds.append(id);
			}
		}
		StringBuffer vendorIds = new StringBuffer("");
		if (StringUtils.isNotBlank(vendorName)) {
			String[] idList = StringUtils.split(vendorName, "|");
			for (String id : idList) {
				if (StringUtils.isBlank(id))
					continue;
				if (StringUtils.isNotBlank(vendorIds.toString()))
					vendorIds.append(",");
				vendorIds.append(id);
			}
		}

		Param param = new Param();
		param.setOrgUnitNo(currentOrgUnitNo);
		param.setControlUnitNo(currentControlUnitNo);
		HashMap<String, String> orgMap = new HashMap<String, String>();
		StringBuffer orgUnitNos = new StringBuffer("");
		if (StringUtils.isNotBlank(invOrgUnitNo)) {
			String[] noList = StringUtils.split(invOrgUnitNo, "|");
			for (String no : noList) {
				if (StringUtils.isBlank(no))
					continue;
				List<OrgStorage2> orgStorageList = orgStorageBiz.findChild(no, param);
				if (orgStorageList != null && !orgStorageList.isEmpty()) {
					for (OrgStorage2 orgStorage : orgStorageList) {
						if (StringUtils.isNotBlank(orgUnitNos.toString()))
							orgUnitNos.append(",");
						orgUnitNos.append("'").append(orgStorage.getOrgUnitNo()).append("'");
						orgMap.put(orgStorage.getOrgUnitNo(), orgStorage.getOrgUnitName());
					}
				} else {
					orgUnitNos.append("'").append(no).append("'");
				}
			}
		}
		int materialClassId = 0;
		StringBuffer sbMaterilaClass = new StringBuffer("");
		if (StringUtils.isNotBlank(materialClassName) && StringUtils.isNumeric(materialClassName)) {
			materialClassId = (Integer.parseInt(materialClassName));
			List<ScmMaterialGroup> scmMaterialGroupList = scmMaterialGroupBiz.findChild(materialClassId, param);
			if (scmMaterialGroupList != null && scmMaterialGroupList.size() > 0) {
				for (ScmMaterialGroup scmMaterialGroup : scmMaterialGroupList) {
					if (StringUtils.isNotBlank(sbMaterilaClass.toString()))
						sbMaterilaClass.append(",");
					sbMaterilaClass.append(scmMaterialGroup.getId());
				}
			} else {
				sbMaterilaClass.append(materialClassId);
			}
		}
		
		// 客户名称
		Page page = new Page();
		page.setModelClass(Customer.class);
		page.setShowCount(Integer.MAX_VALUE);
		param.setOrgUnitNo(currentControlUnitNo);
		if(StringUtils.isNotBlank(custType)&&StringUtils.isBlank(cust)) {
			page.getParam().put((ClassUtils.getFinalModelSimpleName(Customer.class) + "." + Customer.FN_CUSTTYPE), 
					new QueryParam((ClassUtils.getFinalModelSimpleName(Customer.class) + "." + Customer.FN_CUSTTYPE), QueryParam.QUERY_IN, custType));
		}else if(StringUtils.isNotBlank(cust)&&StringUtils.isBlank(custType)){
			page.getParam().put((ClassUtils.getFinalModelSimpleName(Customer.class) + "." + Customer.FN_ID), 
					new QueryParam((ClassUtils.getFinalModelSimpleName(Customer.class) + "." + Customer.FN_ID), QueryParam.QUERY_IN, cust));
		}else if(StringUtils.isNotBlank(custType)&&StringUtils.isNotBlank(cust)) {
			page.setSqlCondition(ClassUtils.getFinalModelSimpleName(Customer.class) + "." + Customer.FN_ID+" "+QueryParam.QUERY_IN+"("+cust+")"+" or "
		+ClassUtils.getFinalModelSimpleName(Customer.class) + "." + Customer.FN_CUSTTYPE+" "+ QueryParam.QUERY_IN+"("+custType+")");
		}
		HashMap<Long, String> custMap = new HashMap<Long, String>();
		StringBuffer custTypeId = new StringBuffer("");
		List<Customer> custList = customerBiz.queryPage(page, new ArrayList<String>(), "", param);
		if (custList != null && !custList.isEmpty()) {
			for (Customer customer : custList) {
				if(StringUtils.isNotBlank(custTypeId.toString()))
					custTypeId.append(",");
				custTypeId.append(customer.getId());
				custMap.put(customer.getId(), customer.getCustName());
			}
		}
		param.setOrgUnitNo(currentOrgUnitNo);
		HashMap<String, Object> map = new HashMap<>();
		map.put("orgUnitNo", orgUnitNos.toString());
		map.put("begDate", begDate);
		map.put("endDate", endDate);
		map.put("materialIds", materialIds.toString());
		map.put("materialClassIds", sbMaterilaClass.toString());
		map.put("vendorIds", vendorIds.toString());
		map.put("lot", lot);
		map.put("custIds", custTypeId.toString());
		map.put("sales", sales);
		if (StringUtils.isBlank(stockType))
			stockType = "0";
		map.put("stockType", stockType);
		if (StringUtils.isNotBlank(whOrDept)) {
			map.put("whOrDept", whOrDept);
		}
		if ("Y".equals(noPost)) {
			noPost = "1";
		} else {
			noPost = "0";
		}
		map.put("noPost", noPost);
		if(brand!=null&&StringUtils.isNotBlank(brand)) {
			map.put("brand", brand);
		}
		
//        if(StringUtils.isNotBlank(taxRate) && !StringUtils.equals("-1", taxRate)) {
//            map.put("taxRate", taxRate);
//        }
		// 2 查询数据库
		List<ScmInvInWarehsItemSum> list = ((ScmInvReportDAO) dao).selectScmInvSaleItemSum(map);

		HashMap<Long, String> buyerMap = new HashMap<Long, String>();
		// 拼接其他结果集
		for (ScmInvInWarehsItemSum temp : list) {
			temp.setMerchantName(custMap.get(temp.getMerchantId()));
			if (temp.getStaffId() > 0) {
				if (buyerMap.get(temp.getStaffId()) == null) {
					Salesman2 salesman = salesmanBiz.select(temp.getStaffId(), param);
					if (salesman != null) {
						temp.setStaffName(salesman.getName());
						buyerMap.put(temp.getStaffId(), salesman.getName());
					}
				} else {
					temp.setStaffName(buyerMap.get(temp.getStaffId()));
				}
			}
		}
		HashMap<String, ArrayList<String>> sortName = new HashMap<String, ArrayList<String>>();
		sortName.put("no", new ArrayList<String>(Arrays.asList("no")));
		sortName.put("itemNo", new ArrayList<String>(Arrays.asList("classCode", "itemNo", "unit")));
		sortName.put("vendorName", new ArrayList<String>(Arrays.asList("merchantName")));
		sortName.put("buyerName", new ArrayList<String>(Arrays.asList("staffId")));
		sortName.put("brand", new ArrayList<String>(Arrays.asList("brandId")));
		ArrayList<String> sortNameList = new ArrayList<String>();
		sortNameList.addAll(sortName.get(summary1));
		if (summary2 != null) {
			sortNameList.addAll(sortName.get(summary2));
		}
		String[] array = (String[]) sortNameList.toArray(new String[sortNameList.size()]);
		String sorts[] = new String[array.length];
		for (int i = 0; i < array.length; i++) {
			sorts[i] = "ASC";
		}
		list = (List<ScmInvInWarehsItemSum>) ListSortUtil.sort(list, array, sorts);
		return list;
	}

	@Override
	public List<ScmInvSaleBusiness> selectScmInvSaleBusiness(HttpServletRequest httpServletRequest)
			throws AppException {
		String currentOrgUnitNo = httpServletRequest.getParameter("orgUnitNo");
		String currentControlUnitNo = httpServletRequest.getParameter("controlUnitNo");
		String invOrgUnitNo = httpServletRequest.getParameter("invOrgUnitNo");
		String whOrDept = httpServletRequest.getParameter("whOrDept");
		String begDate = httpServletRequest.getParameter("begDate");
		String endDate = httpServletRequest.getParameter("endDate");
		String summary = httpServletRequest.getParameter("summary");
		String date = httpServletRequest.getParameter("dateTime");
		String materialClassName = httpServletRequest.getParameter("materialClassName");
		String materialName = httpServletRequest.getParameter("materialName");
		String custType = httpServletRequest.getParameter("custType");
		String vendorType = httpServletRequest.getParameter("vendorType");
		String brand = httpServletRequest.getParameter("brand");// 品牌信息
		HashMap<String, Object> map = new HashMap<>();
		map.put("currentOrgUnitNo", currentOrgUnitNo);
		map.put("currentControlUnitNo", currentControlUnitNo);
		map.put("invOrgUnitNo", invOrgUnitNo);
		map.put("whOrDept", whOrDept);
		map.put("begDate", begDate);
		map.put("endDate", endDate);
		if (summary == null) {
			date = "1";
		}

		// 客户ID
		String custId = httpServletRequest.getParameter("custId");
		StringBuffer custIds = new StringBuffer("");
		
		Param param = new Param();
		param.setOrgUnitNo(currentControlUnitNo);
		param.setControlUnitNo(currentControlUnitNo);
		
		// 客户名称
		Page page = new Page();
		page.setModelClass(Customer.class);
		page.setShowCount(Integer.MAX_VALUE);
		if(StringUtils.isNotBlank(custType)&&StringUtils.isBlank(custId)) {
			page.getParam().put((ClassUtils.getFinalModelSimpleName(Customer.class) + "." + Customer.FN_CUSTTYPE), 
					new QueryParam((ClassUtils.getFinalModelSimpleName(Customer.class) + "." + Customer.FN_CUSTTYPE), QueryParam.QUERY_IN, custType));
		}else if(StringUtils.isNotBlank(custId)&&StringUtils.isBlank(custType)){
			page.getParam().put((ClassUtils.getFinalModelSimpleName(Customer.class) + "." + Customer.FN_ID), 
					new QueryParam((ClassUtils.getFinalModelSimpleName(Customer.class) + "." + Customer.FN_ID), QueryParam.QUERY_IN, custId));
		}else if(StringUtils.isNotBlank(custType)&&StringUtils.isNotBlank(custId)) {
			page.setSqlCondition(ClassUtils.getFinalModelSimpleName(Customer.class) + "." + Customer.FN_ID+" "+QueryParam.QUERY_IN+"("+custId+")"+" or "
		+ClassUtils.getFinalModelSimpleName(Customer.class) + "." + Customer.FN_CUSTTYPE+" "+ QueryParam.QUERY_IN+"("+custType+")");
		}
		HashMap<Long, Customer> custMap = new HashMap<Long, Customer>();
		StringBuffer custTypeId = new StringBuffer("");
		List<Customer> custList = customerBiz.queryPage(page, new ArrayList<String>(), "", param);
		if (custList != null && !custList.isEmpty()) {
			for (Customer customer : custList) {
				if(StringUtils.isNotBlank(custTypeId.toString()))
					custTypeId.append(",");
				custTypeId.append(customer.getId());
				custMap.put(customer.getId(), customer);
			}
		}

		param.setOrgUnitNo(currentOrgUnitNo);
		if (custIds != null && !"".equals(custTypeId.toString().trim()))
			map.put("custIds", custTypeId.toString());

		// 销售员ID
		String saleId = httpServletRequest.getParameter("sales");
		StringBuffer saleIds = new StringBuffer("");
		if (StringUtils.isNotBlank(saleId)) {
			String[] idList = StringUtils.split(saleId, "|");
			for (String id : idList) {
				if (StringUtils.isBlank(id))
					continue;
				if (StringUtils.isNotBlank(saleIds.toString()))
					saleIds.append(",");
				saleIds.append(id);
			}
		}
		if (saleIds != null && !"".equals(saleIds.toString().trim()))
			map.put("salesIds", saleIds.toString());

		StringBuffer materialIds = new StringBuffer("");
		if (StringUtils.isNotBlank(materialName)) {
			String[] idList = StringUtils.split(materialName, "|");
			for (String id : idList) {
				if (StringUtils.isBlank(id))
					continue;
				if (StringUtils.isNotBlank(materialIds.toString()))
					materialIds.append(",");
				materialIds.append(id);
			}
		}

		if (materialIds != null && StringUtils.isNotBlank(materialIds.toString()))
			map.put("materialIds", materialIds.toString());

		List<OrgStorage2> orgStorageList = orgStorageBiz.findChild(invOrgUnitNo, param);
		StringBuffer sbInv = new StringBuffer("");
		if (orgStorageList == null || orgStorageList.isEmpty()) {
			map.put("invOrgUnitNo", "'" + invOrgUnitNo + "'");
		} else {
			for (OrgStorage2 orgStorage : orgStorageList) {
				if (StringUtils.isNotBlank(sbInv.toString()))
					sbInv.append(",");
				sbInv.append("'").append(orgStorage.getOrgUnitNo()).append("'");
			}
			map.put("invOrgUnitNo", sbInv.toString());
		}

		// 物资类别
		int materialClassId = 0;
		StringBuffer sbMaterilaClass = new StringBuffer("");
		if (StringUtils.isNotBlank(materialClassName) && StringUtils.isNumeric(materialClassName)) {
			materialClassId = (Integer.parseInt(materialClassName));
			List<ScmMaterialGroup> scmMaterialGroupList = scmMaterialGroupBiz.findChild(materialClassId, param);
			if (scmMaterialGroupList != null && scmMaterialGroupList.size() > 0) {
				for (ScmMaterialGroup scmMaterialGroup : scmMaterialGroupList) {
					if (StringUtils.isNotBlank(sbMaterilaClass.toString()))
						sbMaterilaClass.append(",");
					sbMaterilaClass.append(scmMaterialGroup.getId());
				}
			} else {
				sbMaterilaClass.append(materialClassId);
			}
		}
		if (sbMaterilaClass != null && StringUtils.isNotBlank(sbMaterilaClass.toString()))
			map.put("materialClassIds", sbMaterilaClass);

		// 供应商类别
		int vendortypeId = 0;
		StringBuilder vendorTypeIds = new StringBuilder("");
		if (StringUtils.isNotBlank(vendorType) && StringUtils.isNumeric(vendorType)) {
			vendortypeId = (Integer.parseInt(vendorType));
			List<Scmsuppliergroup2> scmsuppliergroupList = scmsuppliergroupBiz.findChild(vendortypeId, param);
			if (scmsuppliergroupList != null && scmsuppliergroupList.size() > 0) {
				for (Scmsuppliergroup2 scmsuppliergroup : scmsuppliergroupList) {
					if (StringUtils.isNotBlank(vendorTypeIds.toString()))
						vendorTypeIds.append(",");
					vendorTypeIds.append(scmsuppliergroup.getId());
				}
			} else {
				vendorTypeIds.append(vendortypeId);
			}
		}
		if (vendorTypeIds != null && StringUtils.isNotBlank(vendorTypeIds.toString()))
			map.put("vendorTypeIds", vendorTypeIds);

		if(brand!=null&&StringUtils.isNotBlank(brand)) {
			map.put("brand", brand);
		}
		List<ScmInvSaleBusiness> saleList = ((ScmInvReportDAO) dao).selectScmInvSaleBusinessSale(map);
		List<ScmInvSaleBusiness> purchaseList = ((ScmInvReportDAO) dao).selectScmInvSaleBusinessPurchase(map);

		saleList.addAll(purchaseList);

		ArrayList<String> sortNameList = new ArrayList<String>();
		if (date != null && !"".equals(date) && date.equals("1")) {
			sortNameList.add("date");
		}
//      赋初始值，防止出现 润乾报表第一行为空会出现之后的列值也为空（偶现）
		for (ScmInvSaleBusiness temp : saleList) {
			if (temp.getCustId() != null) {
				temp.setCustName(custMap.get(temp.getCustId()).getCustName());
				temp.setCustNo(custMap.get(temp.getCustId()).getCustNo());
			}else {
				temp.setCustName("");
				temp.setCustNo("");
			}
			if (temp.getSaleAmt() == null) {
				temp.setSaleAmt(BigDecimal.ZERO);
			}
			if (temp.getPurAmt() == null) {
				temp.setPurAmt(BigDecimal.ZERO);
			}
			if (temp.getPurTaxAmt() == null) {
				temp.setPurTaxAmt(BigDecimal.ZERO);
			}
			if (temp.getSaleTaxAmt() == null) {
				temp.setSaleTaxAmt(BigDecimal.ZERO);
			}
			if (temp.getVendorType() == null) {
				temp.setVendorType("");
			}
		}
//        如果选择了汇总方式
		if (summary != null && !"".equals(summary)) {
//        	销售客户汇总
			if ("customer".equals(summary)) {
				sortNameList.add("custNo");
//          供应商分类汇总
			}
			if ("vendorType".equals(summary)) {
				sortNameList.add("vendorType");
			}
//      没有选择汇总方式
		} else {
			if (sortNameList.size() == 0) {
				sortNameList.add("date");
			}
		}
		String[] array = (String[]) sortNameList.toArray(new String[sortNameList.size()]);
		String sorts[] = new String[array.length];
		for (int i = 0; i < array.length; i++) {
			sorts[i] = "ASC";
		}
		saleList = (List<ScmInvSaleBusiness>) ListSortUtil.sort(saleList, array, sorts);
		return saleList;
	}

	@Override
	public List<ScmInvPurSalePrice> selectScmInvPurSalePrice(HttpServletRequest httpServletRequest)
			throws AppException {
		String OrgUnitNo = httpServletRequest.getParameter("OrgUnitNo");
		String invOrgUnitNo = httpServletRequest.getParameter("invOrgUnitNo");
		String controlUnitNo = httpServletRequest.getParameter("controlUnitNo");
		String materialClassName = httpServletRequest.getParameter("materialClassName");
		String businessDate = httpServletRequest.getParameter("businessDate");
		String materialName = httpServletRequest.getParameter("materialName");
		Param paramCla = new Param();
		paramCla.setOrgUnitNo(OrgUnitNo);
		paramCla.setControlUnitNo(controlUnitNo);
		// 物资类别
		int materialClassId = 0;
		StringBuffer sbMaterilaClass = new StringBuffer("");
		if (StringUtils.isNotBlank(materialClassName) && StringUtils.isNumeric(materialClassName)) {
			materialClassId = (Integer.parseInt(materialClassName));
			List<ScmMaterialGroup> scmMaterialGroupList = scmMaterialGroupBiz.findChild(materialClassId, paramCla);
			if (scmMaterialGroupList != null && scmMaterialGroupList.size() > 0) {
				for (ScmMaterialGroup scmMaterialGroup : scmMaterialGroupList) {
					if (StringUtils.isNotBlank(sbMaterilaClass.toString()))
						sbMaterilaClass.append(",");
					sbMaterilaClass.append(scmMaterialGroup.getId());
				}
			} else {
				sbMaterilaClass.append(materialClassId);
			}
		}

//		// 物资
//		StringBuffer materialIds = new StringBuffer("");
//		if (StringUtils.isNotBlank(materialName)) {
//			String[] idList = StringUtils.split(materialName, ",");
//			for (String id : idList) {
//				if (StringUtils.isBlank(id))
//					continue;
//				if (StringUtils.isNotBlank(materialIds.toString()))
//					materialIds.append(",");
//				materialIds.append(id);
//			}
//		}

		Page page = new Page();
		page.setShowCount(Integer.MAX_VALUE);
		Param param = new Param();
		param.setOrgUnitNo(OrgUnitNo);
		param.setControlUnitNo(controlUnitNo);
		// 通过库存组织获取 库存委托的采购组织
		List<OrgPurchase2> OrgPurchase = orgUnitRelationBiz.findFromOrgUnitByType("PurToInv", invOrgUnitNo, true, null,
				param);

		HashMap<String, Object> map = new HashMap<>();
		map.put("orgUnitNo", invOrgUnitNo);
		if (businessDate != null && StringUtils.isNotBlank(businessDate))
			map.put("businessDate", businessDate);
//		if (materialIds != null && StringUtils.isNotBlank(materialIds.toString()))
		map.put("materialIds", materialName);
		if (sbMaterilaClass != null && StringUtils.isNotBlank(sbMaterilaClass.toString()))
			map.put("materialClassIds", sbMaterilaClass);
		// 查询销售数据	
		List<ScmInvPurSalePrice> scmInvPurSalePriceList = ((ScmInvReportDAO) dao).selectScmInvPurSalePrice_Sale(map);
		if (scmInvPurSalePriceList == null || scmInvPurSalePriceList.size() == 0) {
			return null;
		}
		ArrayList<List<ScmInvPurSalePrice>> orgList = new ArrayList<List<ScmInvPurSalePrice>>();
		StringBuffer items = new StringBuffer();
		for (ScmInvPurSalePrice scm : scmInvPurSalePriceList) {
			if (StringUtils.isNotBlank(items.toString()))
				items.append(",");
				
			items.append(scm.getItemId().toString());
		}

		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("materialIds", items);
		if (businessDate != null && StringUtils.isNotBlank(businessDate))
			paramMap.put("businessDate", businessDate);
		if (OrgPurchase == null) {
			return scmInvPurSalePriceList;
		}

		// 查询采购数据 , 根据查询出的销售物资过滤
		for (OrgPurchase2 orgPurchase : OrgPurchase) {
			paramMap.put("orgUnitNo", orgPurchase.getOrgUnitNo());
			List<ScmInvPurSalePrice> temp = ((ScmInvReportDAO) dao).selectScmInvPurSalePrice_Pur(paramMap);
			orgList.add(temp);
		}

		// 进行单位换算
		for (List<ScmInvPurSalePrice> temp : orgList) {// 循环所有采购组织列表
			for (ScmInvPurSalePrice sale : scmInvPurSalePriceList) {// 循环销售数据
				for (ScmInvPurSalePrice pur : temp) {// 循环采购组织列表里面的采购数据
					if (pur.getItemId().equals(sale.getItemId())) {// 匹配，对相同数据做换算
						if (pur.getPurUnitId().equals(sale.getSaleUnitId())) {// 判断单位是否相同
							// 单位相同 赋值取平均价就行
							if (sale.getPutTaxPrice() == null) {// 判断是否做过单价处理，
								sale.setPurUnitName(pur.getPurUnitName());
								sale.setPutTaxPrice(pur.getPutTaxPrice());
							} else {
								// 做过处理就求平均价 (赋值的价格 + 新价格)/2
								sale.setPutTaxPrice(sale.getPutTaxPrice().add(pur.getPutTaxPrice())
										.divide(new BigDecimal(2), 2, BigDecimal.ROUND_HALF_UP));
							}
						} else {
							// 单位不同 换算计量单位后取价
							if (sale.getPutTaxPrice() == null) {// 判断是否做过单价处理，
								sale.setPurUnitName(sale.getSaleUnitName());
								sale.setPutTaxPrice(pur.getPutTaxPrice()
										.divide(pur.getPurConvrate(), 30, BigDecimal.ROUND_HALF_UP)
										.multiply(sale.getSaleConvrate()).setScale(2, BigDecimal.ROUND_HALF_UP));
							} else {
								sale.setPutTaxPrice(sale.getPutTaxPrice()
										.add(pur.getPutTaxPrice()
												.divide(pur.getPurConvrate(), 30, BigDecimal.ROUND_HALF_UP)
												.multiply(sale.getSaleConvrate()))
										.divide(new BigDecimal(2)).setScale(2, BigDecimal.ROUND_HALF_UP));
							}
						}
					}
				}
			}
		}
		return scmInvPurSalePriceList;
	}

	/**
	 * 供应商比价
	 */
	@Override
	public List<ScmVendorItemContrast> selectScmVendorItemContrast(HttpServletRequest httpServletRequest) {
		
		String currentOrgUnitNo = httpServletRequest.getParameter("orgUnitNo");
		String currentControlUnitNo = httpServletRequest.getParameter("controlUnitNo");
		String invOrgUnitNo = httpServletRequest.getParameter("invOrgUnitNo");
		String purOrgUnitNo = httpServletRequest.getParameter("purOrgUnitNo");
		String begDate = httpServletRequest.getParameter("begDate");
		String endDate = httpServletRequest.getParameter("endDate");
		String materialClassName = httpServletRequest.getParameter("materialClassName");
		String materialName = httpServletRequest.getParameter("materialName");
		String vendor = httpServletRequest.getParameter("vendor");
		String stockType = httpServletRequest.getParameter("stockType");
		String whOrDept = httpServletRequest.getParameter("whOrDept");
		String noPost = httpServletRequest.getParameter("noPost");	
		
		HashMap<String,Object>hashMap = new HashMap<String, Object>();
		hashMap.put("orgunitno", invOrgUnitNo);
		
		if(StringUtils.isNotBlank(purOrgUnitNo))
			hashMap.put("purOrgUnitNo", purOrgUnitNo);

		if(StringUtils.isNotBlank(begDate))
			hashMap.put("begDate", begDate);
		
		if(StringUtils.isNotBlank(endDate))
			hashMap.put("endDate", endDate);
		
		if(StringUtils.isNotBlank(materialClassName))
			hashMap.put("materialClassName", materialClassName);
			
		if(StringUtils.isNotBlank(materialName))
			hashMap.put("materialName", materialName);
		
		if(StringUtils.isNotBlank(vendor))
			hashMap.put("vendor", vendor);
		
		if (StringUtils.isBlank(stockType))
			stockType = "0";
		hashMap.put("stockType", stockType);
		
		if(StringUtils.isNotBlank(whOrDept))
			hashMap.put("whOrDept", whOrDept);
		
		if(StringUtils.isNotBlank(noPost))
		hashMap.put("noPost", noPost);
		
		List<ScmVendorItemContrast> list = ((ScmInvReportDAO) dao).selectScmVendorItemContras(hashMap);
		return list;
	}

	@Override
	public List<ScmPurVendorInfo> selectScmPurVendorInfo(HttpServletRequest httpServletRequest) {
		String currentOrgUnitNo = httpServletRequest.getParameter("orgUnitNo");
		String currentControlUnitNo = httpServletRequest.getParameter("controlUnitNo");
		String purOrgUnitNo = httpServletRequest.getParameter("currentOrgUnitNo");
		String begDate = httpServletRequest.getParameter("begDate");
		String endDate = httpServletRequest.getParameter("endDate");
		String vendor = httpServletRequest.getParameter("vendor");
		String noPost = httpServletRequest.getParameter("noPost");
		Param param = new Param();
		param.setOrgUnitNo(purOrgUnitNo);
		param.setControlUnitNo(currentControlUnitNo);
		List<OrgPurchase2> orgList = orgPurchaseBiz.findChild(purOrgUnitNo, param);
		StringBuffer buff = new StringBuffer();
		for(int i=0;i<orgList.size();i++) {
			buff.append(orgList.get(i).getOrgUnitNo());
			if(i!=orgList.size()-1) {
				buff.append(",");
			}
		}
		HashMap<String,Object> hashMap = new HashMap<String, Object>();
		hashMap.put("orgunitno",buff.toString());
		
		if(StringUtils.isNotBlank(begDate))
		hashMap.put("begDate",begDate);
		
		if(StringUtils.isNotBlank(endDate))
		hashMap.put("endDate",endDate);
		
		if(StringUtils.isNotBlank(vendor))
		hashMap.put("vendor",vendor);
		List<ScmPurVendorInfo> list = ((ScmInvReportDAO) dao).selectScmPurVendorInfo(hashMap);
		OrgBaseUnit2 org = orgBaseUnitBiz.selectbyOrgNo(purOrgUnitNo, param);
		for(ScmPurVendorInfo scmPurVendorInfo:list) {
			scmPurVendorInfo.setOrgUnitCode(purOrgUnitNo);
			scmPurVendorInfo.setOrgUnitName(org.getOrgUnitName());
		}
		
		return list;
	}
}
