package com.armitage.server.iscm.report.costcenter.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Page;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.base.model.QueryParam;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.util.AppContextUtil;
import com.armitage.server.common.util.BeanUtil;
import com.armitage.server.common.util.ClassUtils;
import com.armitage.server.common.util.DesUtils;
import com.armitage.server.common.util.FormatUtils;
import com.armitage.server.common.util.JSONUtils;
import com.armitage.server.common.util.ListSortUtil;
import com.armitage.server.iars.basedata.model.PeriodCalendar;
import com.armitage.server.iars.basedata.service.PeriodCalendarBiz;
import com.armitage.server.iscm.basedata.model.ScmCostUseSet;
import com.armitage.server.iscm.basedata.model.ScmCostUseSet2;
import com.armitage.server.iscm.basedata.model.ScmCostUseType;
import com.armitage.server.iscm.basedata.model.ScmMaterial2;
import com.armitage.server.iscm.basedata.model.ScmMaterialGroup;
import com.armitage.server.iscm.basedata.model.ScmMeasureUnit;
import com.armitage.server.iscm.basedata.model.Scmsupplier2;
import com.armitage.server.iscm.basedata.service.ScmCostUseSetBiz;
import com.armitage.server.iscm.basedata.service.ScmCostUseTypeBiz;
import com.armitage.server.iscm.basedata.service.ScmMaterialBiz;
import com.armitage.server.iscm.basedata.service.ScmMaterialGroupBiz;
import com.armitage.server.iscm.basedata.service.ScmMeasureUnitBiz;
import com.armitage.server.iscm.basedata.service.ScmsupplierBiz;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmCstFrmLoss;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmCstFrmLossEntry2;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCostConsume;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCostConsumeEntry2;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCountingCostCenter;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCountingCostCenterEntry2;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCountingTask;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvMoveBill;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvMoveBillEntry2;
import com.armitage.server.iscm.inventorymanage.cstbusiness.service.ScmCstFrmLossEntryBiz;
import com.armitage.server.iscm.inventorymanage.cstbusiness.service.ScmInvCostConsumeEntryBiz;
import com.armitage.server.iscm.inventorymanage.cstbusiness.service.ScmInvCountingCostCenterEntryBiz;
import com.armitage.server.iscm.inventorymanage.cstbusiness.service.ScmInvMoveBillEntryBiz;
import com.armitage.server.iscm.inventorymanage.inventorydata.model.ScmCostFinDeptConsume;
import com.armitage.server.iscm.inventorymanage.inventorydata.model.ScmCostFinDeptConsumeQuery;
import com.armitage.server.iscm.inventorymanage.inventorydata.model.ScmInvBal;
import com.armitage.server.iscm.inventorymanage.inventorydata.model.ScmInvBal2;
import com.armitage.server.iscm.inventorymanage.inventorydata.service.ScmInvBalBiz;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvOtherInWarehsBill;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvOtherInWarehsBillEntry2;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvPurInWarehsBill2;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvPurInWarehsBillEntry2;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.service.ScmInvOtherInWarehsBillEntryBiz;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.service.ScmInvPurInWarehsBillEntryBiz;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvMaterialReqBill;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvMaterialReqBill2;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvMaterialReqBillEntry2;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvOtherIssueBill;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvOtherIssueBillEntry2;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvSaleIssueBill;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvSaleIssueBillEntry;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvSaleIssueBillEntry2;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.service.ScmInvMaterialReqBillEntryBiz;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.service.ScmInvOtherIssueBillEntryBiz;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.service.ScmInvSaleIssueBillEntryBiz;
import com.armitage.server.iscm.report.costcenter.dao.ScmCostReportDAO;
import com.armitage.server.iscm.report.costcenter.model.ScmCostDirectTransfer;
import com.armitage.server.iscm.report.costcenter.model.ScmCostItemInAndOutDetail;
import com.armitage.server.iscm.report.costcenter.model.ScmCostItemInAndOutSum;
import com.armitage.server.iscm.report.costcenter.model.ScmCostRealtimeStockSum;
import com.armitage.server.iscm.report.costcenter.model.ScmCostReport;
import com.armitage.server.iscm.report.costcenter.model.ScmCostTransferOccurSum;
import com.armitage.server.iscm.report.costcenter.model.ScmCountingTaskSum;
import com.armitage.server.iscm.report.costcenter.model.ScmDeptConsume;
import com.armitage.server.iscm.report.costcenter.model.ScmNewCostTransferOccurDetail;
import com.armitage.server.iscm.report.costcenter.model.ScmNewCostTransferOccurSum;
import com.armitage.server.iscm.report.costcenter.service.ScmCostReportBiz;
import com.armitage.server.iscm.report.inventory.model.ScmInvItemInAndOutSum;
import com.armitage.server.system.model.Code;
import com.armitage.server.system.model.OrgBaseUnit2;
import com.armitage.server.system.model.OrgCompany;
import com.armitage.server.system.model.OrgCompany2;
import com.armitage.server.system.model.OrgCostCenter2;
import com.armitage.server.system.model.OrgStorage2;
import com.armitage.server.system.service.CodeBiz;
import com.armitage.server.system.service.OrgCompanyBiz;
import com.armitage.server.system.service.OrgCostCenterBiz;
import com.armitage.server.system.service.OrgStorageBiz;
import com.armitage.server.system.service.OrgUnitBiz;
import com.armitage.server.system.service.OrgUnitRelationBiz;
import com.armitage.server.webservice.model.ReportRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Service;

@Service("scmCostReportBiz")
public class ScmCostReportBizImpl extends BaseBizImpl<ScmCostReport> implements ScmCostReportBiz {
	private OrgUnitBiz orgUnitBiz;
	private CodeBiz codeBiz;
	private ScmInvBalBiz scmInvBalBiz;
	private PeriodCalendarBiz periodCalendarBiz;
	private ScmInvPurInWarehsBillEntryBiz scmInvPurInWarehsBillEntryBiz;
	private ScmMeasureUnitBiz scmMeasureUnitBiz;
	private ScmsupplierBiz scmsupplierBiz;
	private ScmInvMaterialReqBillEntryBiz scmInvMaterialReqBillEntryBiz;
	private ScmInvCostConsumeEntryBiz scmInvCostConsumeEntryBiz;
	private ScmInvMoveBillEntryBiz scmInvMoveBillEntryBiz;
	private ScmInvSaleIssueBillEntryBiz scmInvSaleIssueBillEntryBiz;
	private OrgCostCenterBiz orgCostCenterBiz;
	private ScmMaterialBiz scmMaterialBiz;
	private ScmMaterialGroupBiz scmMaterialGroupBiz;
	private ScmInvCountingCostCenterEntryBiz scmInvCountingCostCenterEntryBiz;
	private ScmCstFrmLossEntryBiz scmCstFrmLossEntryBiz;
	private ScmCostUseSetBiz scmCostUseSetBiz;
	private ScmCostUseTypeBiz scmCostUseTypeBiz;
	private OrgCompanyBiz orgCompanyBiz;
	private OrgStorageBiz orgStorageBiz;
	private ScmInvOtherInWarehsBillEntryBiz scmInvOtherInWarehsBillEntryBiz;
	private ScmInvOtherIssueBillEntryBiz scmInvOtherIssueBillEntryBiz;
	private OrgUnitRelationBiz orgUnitRelationBiz;
	public void setOrgUnitRelationBiz(OrgUnitRelationBiz orgUnitRelationBiz) {
		this.orgUnitRelationBiz = orgUnitRelationBiz;
	}

	private static Log log = LogFactory.getLog(ScmCostReportBizImpl.class);
	private HashMap<String, Object> beanMap = new HashMap<String, Object>();
	
	
	public void setScmInvOtherInWarehsBillEntryBiz(ScmInvOtherInWarehsBillEntryBiz scmInvOtherInWarehsBillEntryBiz) {
		this.scmInvOtherInWarehsBillEntryBiz = scmInvOtherInWarehsBillEntryBiz;
	}

	public void setOrgCompanyBiz(OrgCompanyBiz orgCompanyBiz) {
		this.orgCompanyBiz = orgCompanyBiz;
	}

	public void setOrgUnitBiz(OrgUnitBiz orgUnitBiz) {
		this.orgUnitBiz = orgUnitBiz;
	}

	public void setCodeBiz(CodeBiz codeBiz) {
		this.codeBiz = codeBiz;
	}

	public void setScmInvSaleIssueBillEntryBiz(ScmInvSaleIssueBillEntryBiz scmInvSaleIssueBillEntryBiz) {
		this.scmInvSaleIssueBillEntryBiz = scmInvSaleIssueBillEntryBiz;
	}

	public void setScmInvBalBiz(ScmInvBalBiz scmInvBalBiz) {
		this.scmInvBalBiz = scmInvBalBiz;
	}

	public void setPeriodCalendarBiz(PeriodCalendarBiz periodCalendarBiz) {
		this.periodCalendarBiz = periodCalendarBiz;
	}

	public void setScmInvPurInWarehsBillEntryBiz(ScmInvPurInWarehsBillEntryBiz scmInvPurInWarehsBillEntryBiz) {
		this.scmInvPurInWarehsBillEntryBiz = scmInvPurInWarehsBillEntryBiz;
	}

	public void setScmMeasureUnitBiz(ScmMeasureUnitBiz scmMeasureUnitBiz) {
		this.scmMeasureUnitBiz = scmMeasureUnitBiz;
	}

	public void setScmsupplierBiz(ScmsupplierBiz scmsupplierBiz) {
		this.scmsupplierBiz = scmsupplierBiz;
	}

	public void setScmInvMaterialReqBillEntryBiz(ScmInvMaterialReqBillEntryBiz scmInvMaterialReqBillEntryBiz) {
		this.scmInvMaterialReqBillEntryBiz = scmInvMaterialReqBillEntryBiz;
	}

	public void setScmInvCostConsumeEntryBiz(ScmInvCostConsumeEntryBiz scmInvCostConsumeEntryBiz) {
		this.scmInvCostConsumeEntryBiz = scmInvCostConsumeEntryBiz;
	}

	public void setScmInvMoveBillEntryBiz(ScmInvMoveBillEntryBiz scmInvMoveBillEntryBiz) {
		this.scmInvMoveBillEntryBiz = scmInvMoveBillEntryBiz;
	}

	public void setOrgCostCenterBiz(OrgCostCenterBiz orgCostCenterBiz) {
		this.orgCostCenterBiz = orgCostCenterBiz;
	}

	public void setScmMaterialBiz(ScmMaterialBiz scmMaterialBiz) {
		this.scmMaterialBiz = scmMaterialBiz;
	}

	public void setScmMaterialGroupBiz(ScmMaterialGroupBiz scmMaterialGroupBiz) {
		this.scmMaterialGroupBiz = scmMaterialGroupBiz;
	}

	public void setScmInvCountingCostCenterEntryBiz(ScmInvCountingCostCenterEntryBiz scmInvCountingCostCenterEntryBiz) {
		this.scmInvCountingCostCenterEntryBiz = scmInvCountingCostCenterEntryBiz;
	}

	public void setScmCstFrmLossEntryBiz(ScmCstFrmLossEntryBiz scmCstFrmLossEntryBiz) {
        this.scmCstFrmLossEntryBiz = scmCstFrmLossEntryBiz;
    }

    public void setScmCostUseSetBiz(ScmCostUseSetBiz scmCostUseSetBiz) {
		this.scmCostUseSetBiz = scmCostUseSetBiz;
	}

	public void setScmCostUseTypeBiz(ScmCostUseTypeBiz scmCostUseTypeBiz) {
		this.scmCostUseTypeBiz = scmCostUseTypeBiz;
	}
	

	public void setOrgStorageBiz(OrgStorageBiz orgStorageBiz) {
		this.orgStorageBiz = orgStorageBiz;
	}

	public void setScmInvOtherIssueBillEntryBiz(ScmInvOtherIssueBillEntryBiz scmInvOtherIssueBillEntryBiz) {
		this.scmInvOtherIssueBillEntryBiz = scmInvOtherIssueBillEntryBiz;
	}

	@Override
	public List<ScmCostDirectTransfer> selectDirectTransDetails(HttpServletRequest request) throws AppException {
		List<ScmCostDirectTransfer> list = new ArrayList<>();
		String currentOrgUnitNo=request.getParameter("orgUnitNo");
		String currentControlUnitNo=request.getParameter("controlUnitNo");
		String costOrgUnitNo=request.getParameter("costOrgUnitNo");
		String beginDate=request.getParameter("beginDate");
		String endDate=request.getParameter("endDate");
		String standardId=request.getParameter("standardId");
		String materialName=request.getParameter("materialName");
		String vendorName=request.getParameter("vendorName");
		String countType=request.getParameter("countType");
		String status="1".equals(request.getParameter("status"))?"Y":"N";
		if(StringUtils.isBlank(beginDate) || StringUtils.isBlank(endDate))
			return list;
		if(StringUtils.isBlank(costOrgUnitNo))
			costOrgUnitNo = currentOrgUnitNo;
		Param param = new Param();
		param.setOrgUnitNo(currentOrgUnitNo);
		param.setControlUnitNo(currentControlUnitNo);
		StringBuffer materialClassIds=new StringBuffer("");
		String materialClassName=request.getParameter("materialClassName");
		if (StringUtils.isNotBlank(materialClassName) && StringUtils.isNumeric(materialClassName)) {
            int materialClassId = (Integer.parseInt(materialClassName));
            List<ScmMaterialGroup> scmMaterialGroupList = scmMaterialGroupBiz.findChild(materialClassId, param);
            if (scmMaterialGroupList != null && scmMaterialGroupList.size() > 0) {
                for (ScmMaterialGroup scmMaterialGroup : scmMaterialGroupList) {
                    if (StringUtils.isNotBlank(materialClassIds.toString())) 
                    	materialClassIds.append(",");
                    materialClassIds.append(scmMaterialGroup.getId());
                }
            } else {
            	materialClassIds.append(materialClassId);
            }
        }
		StringBuffer costOrgUnitNos = new StringBuffer("");
		List<OrgCostCenter2> orgCostCenterList = orgCostCenterBiz.findChild(costOrgUnitNo, param);
		if(orgCostCenterList!=null && !orgCostCenterList.isEmpty()) {
			for(OrgCostCenter2 orgCostCenter:orgCostCenterList) {
				if(StringUtils.isNotBlank(costOrgUnitNos.toString()))
					costOrgUnitNos.append(",");
				costOrgUnitNos.append("'").append(orgCostCenter.getOrgUnitNo()).append("'");
			}
		}else {
			costOrgUnitNos.append("'").append(costOrgUnitNo).append("'");
		}
		HashMap<String, Object> map = new HashMap<>();	
		map.put("costOrgUnitNos", costOrgUnitNos);
		map.put("beginDate", beginDate);
		map.put("endDate", endDate);
		map.put("standardId", standardId);
		map.put("materialClassIds", materialClassIds.toString());
		map.put("materialName", materialName);
		map.put("vendorName", vendorName);
		map.put("countType", countType);
		map.put("status", status); 
		list = ((ScmCostReportDAO) dao).selectDirectTransDetails(map);
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				ScmCostDirectTransfer scmCostDirectTransfer = list.get(i);
				OrgBaseUnit2 orgBaseUnit = orgUnitBiz.selectbyOrgNo(scmCostDirectTransfer.getCostOrgUnitNo(), param);
				if (orgBaseUnit != null) {
					scmCostDirectTransfer.setCostOrgUnitName(orgBaseUnit.getOrgUnitName());
				}
				OrgBaseUnit2 orgBaseUnit2 = orgUnitBiz.selectbyOrgNo(scmCostDirectTransfer.getUseOrgUnitNo(), param);
				if (orgBaseUnit2 != null) {
					scmCostDirectTransfer.setUseOrgUnitName(orgBaseUnit.getOrgUnitName());
				}
				if(StringUtils.isNotBlank(scmCostDirectTransfer.getBizType())) {
					Code code = codeBiz.selectByCategoryAndCode("wareHouseBizType", scmCostDirectTransfer.getBizType());
					if(code!=null)
						scmCostDirectTransfer.setBizTypeName(code.getName());
				}
			}
		}
		return list;
	}
	
	@Override
	public List<ScmCountingTaskSum> selectCostCenterInventory(HttpServletRequest request) throws AppException {
		String currentOrgUnitNo=request.getParameter("orgUnitNo");
		String currentControlUnitNo=request.getParameter("controlUnitNo");
		String finOrgUnitNo=request.getParameter("finOrgUnitNo");
		String beginDate=request.getParameter("beginDate");
		String endDate=request.getParameter("endDate");
		String materialClassName=request.getParameter("materialClassName");
		if(StringUtils.isBlank(finOrgUnitNo))
			finOrgUnitNo = currentOrgUnitNo;
		Param param = new Param();
		param.setOrgUnitNo(currentOrgUnitNo);
		param.setControlUnitNo(currentControlUnitNo);
		HashMap<String, Object> map = new HashMap<>();	
		map.put("finOrgUnitNo", finOrgUnitNo); 
		map.put("beginDate", beginDate);
		map.put("endDate", endDate);
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
            map.put("materialClassName", sbMaterilaClass.toString());
        }
		List<ScmCountingTaskSum> list = ((ScmCostReportDAO) dao).selectCostCenterInventory(map);
		if (list != null && !list.isEmpty()) {
			for (ScmCountingTaskSum scmCountingTaskSum:list) {
				OrgBaseUnit2 orgBaseUnit = orgUnitBiz.selectbyOrgNo(scmCountingTaskSum.getFinOrgUnitNo(), param);
				if(orgBaseUnit!=null)
					scmCountingTaskSum.setFinOrgUnitName(orgBaseUnit.getOrgUnitName());
				if(scmCountingTaskSum.isCostCenter()) {
					orgBaseUnit = orgUnitBiz.selectbyOrgNo(scmCountingTaskSum.getWhName(), param);
					if (orgBaseUnit != null) {
						scmCountingTaskSum.setWhName(orgBaseUnit.getOrgUnitName());
					}
				}
			}
		}
		return list;
	}
	
	@Override
	public List<ScmCostItemInAndOutSum> selectSummaryOfMaterials(HttpServletRequest request) throws AppException {
		List<ScmCostItemInAndOutSum> scmCostItemInAndOutSumList = new ArrayList<ScmCostItemInAndOutSum>();
		String currentOrgUnitNo = request.getParameter("orgUnitNo");
		String currentControlUnitNo = request.getParameter("controlUnitNo");
		String costOrgUnitNo = request.getParameter("costOrgUnitNo");
		String beginDate = request.getParameter("beginDate");
		String endDate = request.getParameter("endDate");
		String materialClassName = request.getParameter("materialClassName");
		String materialName = request.getParameter("materialName");
		String status = "1".equals(request.getParameter("status")) ? "Y" : "N";
		if(StringUtils.isBlank(beginDate) || StringUtils.isBlank(endDate))
			return scmCostItemInAndOutSumList;
		if(StringUtils.isBlank(costOrgUnitNo))
			costOrgUnitNo = currentOrgUnitNo;
		Param param = new Param();
		param.setOrgUnitNo(currentOrgUnitNo);
		param.setControlUnitNo(currentControlUnitNo);
		StringBuffer costOrgUnitNos = new StringBuffer("");
		HashMap<String,String> costCenterTypeMap = new HashMap<>();
		if (StringUtils.isNotBlank(costOrgUnitNo)) {
			String[] orgUnitNoList = StringUtils.split(costOrgUnitNo, ",");
			for (String org : orgUnitNoList) {
				if (StringUtils.isBlank(org))
					continue;
				List<OrgCostCenter2> orgCostCenterList = orgCostCenterBiz.findChild(org, param);
				if (orgCostCenterList != null && !orgCostCenterList.isEmpty()) {
					for (OrgCostCenter2 orgCostCenter : orgCostCenterList) {
						if (StringUtils.isNotBlank(costOrgUnitNos.toString()))
							costOrgUnitNos.append(",");
						costOrgUnitNos.append("'").append(orgCostCenter.getOrgUnitNo()).append("'");
						OrgCostCenter2 orgCostCenterTemp = orgCostCenterBiz.selectByOrgUnitNo(orgCostCenter.getOrgUnitNo(),
								param);
						if (orgCostCenterTemp != null) {
							costCenterTypeMap.put(orgCostCenterTemp.getOrgUnitNo(), orgCostCenterTemp.getCostCenterType());
						}
					}
				}
			}
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
		
	    long beginTime = System.currentTimeMillis();
		// 获取期间期初数
		PeriodCalendar periodCalendar = periodCalendarBiz.selectByBizdate(FormatUtils.parseDate(beginDate), param);
		if (periodCalendar != null) {
			Page page = new Page();
			page.setModelClass(ScmInvBal.class);
			page.setShowCount(Integer.MAX_VALUE);
			page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvBal.class) + "." + ScmInvBal.FN_COSTCENTER,
					new QueryParam(
							ClassUtils.getFinalModelSimpleName(ScmInvBal.class) + "." + ScmInvBal.FN_COSTCENTER,
							QueryParam.QUERY_EQ, "1"));
			page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvBal.class) + "." + ScmInvBal.FN_COSTORGUNITNO,
					new QueryParam(
							ClassUtils.getFinalModelSimpleName(ScmInvBal.class) + "." + ScmInvBal.FN_COSTORGUNITNO,
							QueryParam.QUERY_IN, costOrgUnitNos.toString()));
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
			page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvBal.class) + "." + ScmInvBal.FN_PERIODID,
					new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvBal.class) + "." + ScmInvBal.FN_PERIODID,
							QueryParam.QUERY_EQ, String.valueOf(periodCalendar.getPeriodId())));
			List<String> arglist = new ArrayList<>();
			arglist.add("controlUnitNo=" + currentControlUnitNo);
			long dataEndTime11 = System.currentTimeMillis();
			log.info("期初数据查询准备耗时："+(dataEndTime11-beginTime)+"毫秒");
			List<ScmInvBal2> scmInvBalList = scmInvBalBiz.queryPage(page, arglist, "findItemPage", param);
			long dataEndTime = System.currentTimeMillis();
			log.info("期初数据查询耗时："+(dataEndTime-dataEndTime11)+"毫秒");
			if (scmInvBalList != null && !scmInvBalList.isEmpty()) {
				log.info("期初数据查询结果："+(scmInvBalList.size())+"条记录");
				for (ScmInvBal2 scmInvBal : scmInvBalList) {
					if (scmInvBal.isCostCenter()) {
						// 以领代耗的期初不处理，认为是零
//						OrgCostCenter2 orgCostCenter = orgCostCenterBiz.selectByOrgUnitNo(scmInvBal.getCostOrgUnitNo(),
//								param);
//						if (orgCostCenter != null && StringUtils.equals("1", orgCostCenter.getCostCenterType())) {
//							continue;
//						}
						if (costCenterTypeMap.containsKey(scmInvBal.getCostOrgUnitNo())) {
							if (StringUtils.equals("1",costCenterTypeMap.get(scmInvBal.getCostOrgUnitNo()))) {
								continue;
							}
						}
					}
					boolean exists = false;
					if (!scmCostItemInAndOutSumList.isEmpty()) {
						for (ScmCostItemInAndOutSum existScmCostItemInAndOutSum : scmCostItemInAndOutSumList) {
							if (existScmCostItemInAndOutSum.getItemId() == scmInvBal.getItemId() && StringUtils.equals(
									existScmCostItemInAndOutSum.getCostOrgUnitNo(), scmInvBal.getCostOrgUnitNo())) {
								existScmCostItemInAndOutSum.setInitQty(
										existScmCostItemInAndOutSum.getInitQty().add(scmInvBal.getStartQty()));
								existScmCostItemInAndOutSum.setInitAmt(
										existScmCostItemInAndOutSum.getInitAmt().add(scmInvBal.getStartAmt()));
								existScmCostItemInAndOutSum.setInitAvgPrice(
										BigDecimal.ZERO.compareTo(existScmCostItemInAndOutSum.getInitQty()) == 0
												? BigDecimal.ZERO
												: existScmCostItemInAndOutSum.getInitAmt().divide(
														existScmCostItemInAndOutSum.getInitQty(), 2,
														BigDecimal.ROUND_HALF_UP));
								existScmCostItemInAndOutSum.setInitTax(existScmCostItemInAndOutSum.getInitTax()
										.add(scmInvBal.getStartTaxAmt().subtract(scmInvBal.getStartAmt())));
								existScmCostItemInAndOutSum.setInitTaxAmt(
										existScmCostItemInAndOutSum.getInitTaxAmt().add(scmInvBal.getStartTaxAmt()));
								exists = true;
								break;
							}
						}
					}
					if (!exists) {
						ScmCostItemInAndOutSum scmCostItemInAndOutSum = new ScmCostItemInAndOutSum(true);
						scmCostItemInAndOutSum.setItemId(scmInvBal.getItemId());
						scmCostItemInAndOutSum.setItemNo(scmInvBal.getItemNo());
						scmCostItemInAndOutSum.setItemName(scmInvBal.getItemName());
						scmCostItemInAndOutSum.setSpec(scmInvBal.getSpec());
						scmCostItemInAndOutSum.setGroupId(scmInvBal.getGroupId());
						scmCostItemInAndOutSum.setClassCode(scmInvBal.getGroupCode());
						scmCostItemInAndOutSum.setClassName(scmInvBal.getGroupName());
						scmCostItemInAndOutSum.setLongNo(scmInvBal.getLongNo());
						scmCostItemInAndOutSum.setUnitName(scmInvBal.getUnitName());
						scmCostItemInAndOutSum.setUnit(scmInvBal.getUnit());
						scmCostItemInAndOutSum.setCostOrgUnitNo(scmInvBal.getCostOrgUnitNo());
						scmCostItemInAndOutSum.setInitQty(scmInvBal.getStartQty());
						scmCostItemInAndOutSum.setInitAmt(scmInvBal.getStartAmt());
						scmCostItemInAndOutSum.setInitAvgPrice(
								BigDecimal.ZERO.compareTo(scmCostItemInAndOutSum.getInitQty()) == 0 ? BigDecimal.ZERO
										: scmCostItemInAndOutSum.getInitAmt().divide(
												scmCostItemInAndOutSum.getInitQty(), 2, BigDecimal.ROUND_HALF_UP));
						scmCostItemInAndOutSum.setInitTax(scmInvBal.getStartTaxAmt().subtract(scmInvBal.getStartAmt()));
						scmCostItemInAndOutSum.setInitTaxAmt(scmInvBal.getStartTaxAmt());
						scmCostItemInAndOutSumList.add(scmCostItemInAndOutSum);
					}
				}
			}
			long dataEndTime1 = System.currentTimeMillis();
			log.info("期初数据加工耗时："+(dataEndTime1-dataEndTime)+"毫秒");
		}
		  long beginTime1 = System.currentTimeMillis();
		// 获取入库直拨数据
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
						+ ScmInvPurInWarehsBillEntry2.FN_COSTORGUNITNO,
				new QueryParam(
						ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBillEntry2.class) + "."
								+ ScmInvPurInWarehsBillEntry2.FN_COSTORGUNITNO,
						QueryParam.QUERY_IN, costOrgUnitNos.toString()));
		page.getParam()
				.put(ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBill2.class) + "."
						+ ScmInvPurInWarehsBill2.FN_BIZDATE, new QueryParam(
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
		long dataEndTime2 = System.currentTimeMillis();
		log.info("采购入库明细查询耗时："+(dataEndTime2-beginTime1)+"毫秒");
		if (scmInvPurInWarehsBillEntryList != null && !scmInvPurInWarehsBillEntryList.isEmpty()) {
			log.info("采购入库明细查询结果："+(scmInvPurInWarehsBillEntryList.size())+"条记录");
			for (ScmInvPurInWarehsBillEntry2 scmInvPurInWarehsBillEntry : scmInvPurInWarehsBillEntryList) {
				BigDecimal sideFlag = BigDecimal.ONE;
				if (StringUtils.contains("6,8", scmInvPurInWarehsBillEntry.getBizType())) {
					sideFlag = new BigDecimal("-1");
				}
//				OrgCostCenter2 orgCostCenter = orgCostCenterBiz
//						.selectByOrgUnitNo(scmInvPurInWarehsBillEntry.getCostOrgUnitNo(), param);
				if (scmInvPurInWarehsBillEntry.getBizDate().compareTo(FormatUtils.parseDate(beginDate)) < 0) {
//					if (orgCostCenter != null && StringUtils.equals("1", orgCostCenter.getCostCenterType())) {
//						// 以领代耗的不用统计，认为全用完
//						continue;
//					}
					if (costCenterTypeMap.containsKey(scmInvPurInWarehsBillEntry.getCostOrgUnitNo())) {
						if (StringUtils.equals("1",costCenterTypeMap.get(scmInvPurInWarehsBillEntry.getCostOrgUnitNo()))) {
							continue;
						}
					}
					// 比查询日期小的统计到期初数据中，并从List中删除
					boolean exists = false;
					if (!scmCostItemInAndOutSumList.isEmpty()) {
						for (ScmCostItemInAndOutSum existScmCostItemInAndOutSum : scmCostItemInAndOutSumList) {
							if (existScmCostItemInAndOutSum.getItemId() == scmInvPurInWarehsBillEntry.getItemId()
									&& StringUtils.equals(existScmCostItemInAndOutSum.getCostOrgUnitNo(),
											scmInvPurInWarehsBillEntry.getCostOrgUnitNo())) {
								existScmCostItemInAndOutSum.setInitQty(existScmCostItemInAndOutSum.getInitQty()
										.add(sideFlag.multiply(scmInvPurInWarehsBillEntry.getQty())));
								existScmCostItemInAndOutSum.setInitAmt(existScmCostItemInAndOutSum.getInitAmt()
										.add(sideFlag.multiply(scmInvPurInWarehsBillEntry.getAmt())));
								existScmCostItemInAndOutSum.setInitAvgPrice(
										BigDecimal.ZERO.compareTo(existScmCostItemInAndOutSum.getInitQty()) == 0
												? BigDecimal.ZERO
												: existScmCostItemInAndOutSum.getInitAmt().divide(
														existScmCostItemInAndOutSum.getInitQty(), 2,
														BigDecimal.ROUND_HALF_UP));
								existScmCostItemInAndOutSum.setInitTax(existScmCostItemInAndOutSum.getInitTax()
										.add(sideFlag.multiply(scmInvPurInWarehsBillEntry.getTaxAmt()
												.subtract(scmInvPurInWarehsBillEntry.getAmt()))));
								existScmCostItemInAndOutSum.setInitTaxAmt(existScmCostItemInAndOutSum.getInitTaxAmt()
										.add(sideFlag.multiply(scmInvPurInWarehsBillEntry.getTaxAmt())));
								exists = true;
								break;
							}
						}
					}
					if (!exists) {
						ScmCostItemInAndOutSum scmCostItemInAndOutSum = new ScmCostItemInAndOutSum(true);
						scmCostItemInAndOutSum.setItemId(scmInvPurInWarehsBillEntry.getItemId());
						scmCostItemInAndOutSum.setItemNo(scmInvPurInWarehsBillEntry.getItemNo());
						scmCostItemInAndOutSum.setItemName(scmInvPurInWarehsBillEntry.getItemName());
						scmCostItemInAndOutSum.setSpec(scmInvPurInWarehsBillEntry.getSpec());
						scmCostItemInAndOutSum.setGroupId(scmInvPurInWarehsBillEntry.getGroupId());
						scmCostItemInAndOutSum.setClassCode(scmInvPurInWarehsBillEntry.getGroupCode());
						scmCostItemInAndOutSum.setClassName(scmInvPurInWarehsBillEntry.getGroupName());
						scmCostItemInAndOutSum.setLongNo(scmInvPurInWarehsBillEntry.getLongNo());
						scmCostItemInAndOutSum.setUnitName(scmInvPurInWarehsBillEntry.getUnitName());
						scmCostItemInAndOutSum.setUnit(scmInvPurInWarehsBillEntry.getUnit());
						scmCostItemInAndOutSum.setCostOrgUnitNo(scmInvPurInWarehsBillEntry.getCostOrgUnitNo());
						scmCostItemInAndOutSum.setInitQty(sideFlag.multiply(scmInvPurInWarehsBillEntry.getQty()));
						scmCostItemInAndOutSum.setInitAmt(sideFlag.multiply(scmInvPurInWarehsBillEntry.getAmt()));
						scmCostItemInAndOutSum.setInitAvgPrice(
								BigDecimal.ZERO.compareTo(scmCostItemInAndOutSum.getInitQty()) == 0 ? BigDecimal.ZERO
										: scmCostItemInAndOutSum.getInitAmt().divide(
												scmCostItemInAndOutSum.getInitQty(), 2, BigDecimal.ROUND_HALF_UP));
						scmCostItemInAndOutSum.setInitTax(sideFlag.multiply(
								scmInvPurInWarehsBillEntry.getTaxAmt().subtract(scmInvPurInWarehsBillEntry.getAmt())));
						scmCostItemInAndOutSum.setInitTaxAmt(sideFlag.multiply(scmInvPurInWarehsBillEntry.getTaxAmt()));
						scmCostItemInAndOutSumList.add(scmCostItemInAndOutSum);
					}
				} else {
					boolean exists = false;
					if (!scmCostItemInAndOutSumList.isEmpty()) {
						for (ScmCostItemInAndOutSum existScmCostItemInAndOutSum : scmCostItemInAndOutSumList) {
							if (existScmCostItemInAndOutSum.getItemId() == scmInvPurInWarehsBillEntry.getItemId()
									&& StringUtils.equals(existScmCostItemInAndOutSum.getCostOrgUnitNo(),
											scmInvPurInWarehsBillEntry.getCostOrgUnitNo())) {
								existScmCostItemInAndOutSum.setAddInQty(existScmCostItemInAndOutSum.getAddInQty()
										.add(sideFlag.multiply(scmInvPurInWarehsBillEntry.getQty())));
								existScmCostItemInAndOutSum.setAddInAmt(existScmCostItemInAndOutSum.getAddInAmt()
										.add(sideFlag.multiply(scmInvPurInWarehsBillEntry.getAmt())));
								existScmCostItemInAndOutSum.setPurInQty(existScmCostItemInAndOutSum.getPurInQty()
										.add(sideFlag.multiply(scmInvPurInWarehsBillEntry.getQty())));
								existScmCostItemInAndOutSum.setPurInAvgPrice(
										BigDecimal.ZERO.compareTo(existScmCostItemInAndOutSum.getPurInQty()) == 0
												? BigDecimal.ZERO
												: existScmCostItemInAndOutSum.getPurInAmt().divide(
														existScmCostItemInAndOutSum.getPurInQty(), 2,
														BigDecimal.ROUND_HALF_UP));
								existScmCostItemInAndOutSum.setPurInAmt(existScmCostItemInAndOutSum.getPurInAmt()
										.add(sideFlag.multiply(scmInvPurInWarehsBillEntry.getAmt())));
								existScmCostItemInAndOutSum.setPurInTax(existScmCostItemInAndOutSum.getPurInTax()
										.add(sideFlag.multiply(scmInvPurInWarehsBillEntry.getTaxAmt()
												.subtract(scmInvPurInWarehsBillEntry.getAmt()))));
								existScmCostItemInAndOutSum.setPurInTaxAmt(existScmCostItemInAndOutSum.getPurInTaxAmt()
										.add(sideFlag.multiply(scmInvPurInWarehsBillEntry.getTaxAmt())));
								existScmCostItemInAndOutSum.setAddInAvgPrice(
										BigDecimal.ZERO.compareTo(existScmCostItemInAndOutSum.getAddInQty()) == 0
												? BigDecimal.ZERO
												: existScmCostItemInAndOutSum.getAddInAmt().divide(
														existScmCostItemInAndOutSum.getAddInQty(), 2,
														BigDecimal.ROUND_HALF_UP));
								existScmCostItemInAndOutSum.setAddInTax(existScmCostItemInAndOutSum.getAddInTax()
										.add(sideFlag.multiply(scmInvPurInWarehsBillEntry.getTaxAmt()
												.subtract(scmInvPurInWarehsBillEntry.getAmt()))));
								existScmCostItemInAndOutSum.setAddInTaxAmt(existScmCostItemInAndOutSum.getAddInTaxAmt()
										.add(sideFlag.multiply(scmInvPurInWarehsBillEntry.getTaxAmt())));
								
								if (costCenterTypeMap.containsKey(scmInvPurInWarehsBillEntry.getCostOrgUnitNo())) {
									if (StringUtils.equals("1", costCenterTypeMap.get(scmInvPurInWarehsBillEntry.getCostOrgUnitNo()))) {
										// 以领代耗时也需写出库数
										existScmCostItemInAndOutSum.setOutQty(existScmCostItemInAndOutSum.getOutQty()
												.add(sideFlag.multiply(scmInvPurInWarehsBillEntry.getQty())));
										existScmCostItemInAndOutSum.setOutAmt(existScmCostItemInAndOutSum.getOutAmt()
												.add(sideFlag.multiply(scmInvPurInWarehsBillEntry.getAmt())));
										existScmCostItemInAndOutSum.setOutAvgPrice(
												BigDecimal.ZERO.compareTo(existScmCostItemInAndOutSum.getOutQty()) == 0
														? BigDecimal.ZERO
														: existScmCostItemInAndOutSum.getOutAmt().divide(
																existScmCostItemInAndOutSum.getOutQty(), 2,
																BigDecimal.ROUND_HALF_UP));
										existScmCostItemInAndOutSum.setOutTax(existScmCostItemInAndOutSum.getOutTax()
												.add(sideFlag.multiply(scmInvPurInWarehsBillEntry.getTaxAmt()
														.subtract(scmInvPurInWarehsBillEntry.getAmt()))));
										existScmCostItemInAndOutSum.setOutTaxAmt(existScmCostItemInAndOutSum.getOutTaxAmt()
												.add(sideFlag.multiply(scmInvPurInWarehsBillEntry.getTaxAmt())));
									}
								}
								exists = true;
								break;
							}
						}
					}
					if (!exists) {
						// 放入明细记录结果集中
						ScmCostItemInAndOutSum scmCostItemInAndOutSum = new ScmCostItemInAndOutSum(true);
						scmCostItemInAndOutSum.setItemId(scmInvPurInWarehsBillEntry.getItemId());
						scmCostItemInAndOutSum.setItemNo(scmInvPurInWarehsBillEntry.getItemNo());
						scmCostItemInAndOutSum.setItemName(scmInvPurInWarehsBillEntry.getItemName());
						scmCostItemInAndOutSum.setSpec(scmInvPurInWarehsBillEntry.getSpec());
						scmCostItemInAndOutSum.setGroupId(scmInvPurInWarehsBillEntry.getGroupId());
						scmCostItemInAndOutSum.setClassCode(scmInvPurInWarehsBillEntry.getGroupCode());
						scmCostItemInAndOutSum.setClassName(scmInvPurInWarehsBillEntry.getGroupName());
						scmCostItemInAndOutSum.setLongNo(scmInvPurInWarehsBillEntry.getLongNo());
						scmCostItemInAndOutSum.setUnitName(scmInvPurInWarehsBillEntry.getUnitName());
						scmCostItemInAndOutSum.setUnit(scmInvPurInWarehsBillEntry.getUnit());
						scmCostItemInAndOutSum.setCostOrgUnitNo(scmInvPurInWarehsBillEntry.getCostOrgUnitNo());
						scmCostItemInAndOutSum.setAddInQty(sideFlag.multiply(scmInvPurInWarehsBillEntry.getQty()));
						scmCostItemInAndOutSum.setAddInAmt(sideFlag.multiply(scmInvPurInWarehsBillEntry.getAmt()));
						scmCostItemInAndOutSum.setAddInAvgPrice(
								BigDecimal.ZERO.compareTo(scmCostItemInAndOutSum.getAddInQty()) == 0 ? BigDecimal.ZERO
										: scmCostItemInAndOutSum.getAddInAmt().divide(
												scmCostItemInAndOutSum.getAddInQty(), 2, BigDecimal.ROUND_HALF_UP));
						scmCostItemInAndOutSum.setAddInTax(sideFlag.multiply(
								scmInvPurInWarehsBillEntry.getTaxAmt().subtract(scmInvPurInWarehsBillEntry.getAmt())));
						scmCostItemInAndOutSum
								.setAddInTaxAmt(sideFlag.multiply(scmInvPurInWarehsBillEntry.getTaxAmt()));
						scmCostItemInAndOutSum.setPurInQty(sideFlag.multiply(scmInvPurInWarehsBillEntry.getQty()));
						scmCostItemInAndOutSum.setPurInAvgPrice(
								BigDecimal.ZERO.compareTo(scmCostItemInAndOutSum.getPurInQty()) == 0 ? BigDecimal.ZERO
										: scmCostItemInAndOutSum.getPurInAmt().divide(
												scmCostItemInAndOutSum.getPurInQty(), 2, BigDecimal.ROUND_HALF_UP));
						scmCostItemInAndOutSum.setPurInAmt(sideFlag.multiply(scmInvPurInWarehsBillEntry.getAmt()));
						scmCostItemInAndOutSum.setPurInTax(sideFlag.multiply(
								scmInvPurInWarehsBillEntry.getTaxAmt().subtract(scmInvPurInWarehsBillEntry.getAmt())));
						scmCostItemInAndOutSum.setPurInTaxAmt(sideFlag.multiply(scmInvPurInWarehsBillEntry.getTaxAmt()));
						if (costCenterTypeMap.containsKey(scmInvPurInWarehsBillEntry.getCostOrgUnitNo())) {
							if (StringUtils.equals("1", costCenterTypeMap.get(scmInvPurInWarehsBillEntry.getCostOrgUnitNo()))) {
								// 以领代耗时也需写出库数
								scmCostItemInAndOutSum.setOutQty(sideFlag.multiply(scmInvPurInWarehsBillEntry.getQty()));
								scmCostItemInAndOutSum.setOutAmt(sideFlag.multiply(scmInvPurInWarehsBillEntry.getAmt()));
								scmCostItemInAndOutSum.setOutAvgPrice(
										BigDecimal.ZERO.compareTo(scmCostItemInAndOutSum.getOutQty()) == 0 ? BigDecimal.ZERO
												: scmCostItemInAndOutSum.getOutAmt().divide(
														scmCostItemInAndOutSum.getOutQty(), 2, BigDecimal.ROUND_HALF_UP));
								scmCostItemInAndOutSum.setOutTax(sideFlag.multiply(scmInvPurInWarehsBillEntry.getTaxAmt()
										.subtract(scmInvPurInWarehsBillEntry.getAmt())));
								scmCostItemInAndOutSum
									.setOutTaxAmt(sideFlag.multiply(scmInvPurInWarehsBillEntry.getTaxAmt()));
							}
						}
						scmCostItemInAndOutSumList.add(scmCostItemInAndOutSum);
					}
				}
			}
			long dataEndTime3 = System.currentTimeMillis();
			log.info("采购入库明细加工耗时："+(dataEndTime3-dataEndTime2)+"毫秒");
		}
		long beginTime2 = System.currentTimeMillis();
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
						+ ScmInvMaterialReqBill.FN_COSTORGUNITNO,
				new QueryParam(
						ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBill.class) + "."
								+ ScmInvMaterialReqBill.FN_COSTORGUNITNO,
						QueryParam.QUERY_IN, costOrgUnitNos.toString()));
		page.getParam()
				.put(ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBill2.class) + "."
						+ ScmInvMaterialReqBill2.FN_BIZDATE, new QueryParam(
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
		long dataEndTime3 = System.currentTimeMillis();
		log.info("领料出库明细查询耗时："+(dataEndTime3-beginTime2)+"毫秒");
		if (scmInvMaterialReqBillEntryList != null && !scmInvMaterialReqBillEntryList.isEmpty()) {
			log.info("领料出库明细查询结果："+(scmInvMaterialReqBillEntryList.size())+"条记录");
			for (ScmInvMaterialReqBillEntry2 scmInvMaterialReqBillEntry : scmInvMaterialReqBillEntryList) {
				BigDecimal sideFlag = BigDecimal.ONE;
//				2退仓
				if (StringUtils.contains("2", scmInvMaterialReqBillEntry.getBizType())) {
					sideFlag = new BigDecimal("-1");
				}
//				OrgCostCenter2 orgCostCenter = orgCostCenterBiz
//						.selectByOrgUnitNo(scmInvMaterialReqBillEntry.getCostOrgUnitNo(), param);
				if (scmInvMaterialReqBillEntry.getBizDate().compareTo(FormatUtils.parseDate(beginDate)) < 0) {
//					if (orgCostCenter != null && StringUtils.equals("1", orgCostCenter.getCostCenterType())) {
//						continue;
//					}
					if (costCenterTypeMap.containsKey(scmInvMaterialReqBillEntry.getCostOrgUnitNo())) {
						if (StringUtils.equals("1",costCenterTypeMap.get(scmInvMaterialReqBillEntry.getCostOrgUnitNo()))) {
							continue;
						}
					}
					// 比查询日期小的统计到期初数据中，并从List中删除
					boolean exists = false;
					if (!scmCostItemInAndOutSumList.isEmpty()) {
						for (ScmCostItemInAndOutSum existScmCostItemInAndOutSum : scmCostItemInAndOutSumList) {
							if (existScmCostItemInAndOutSum.getItemId() == scmInvMaterialReqBillEntry.getItemId()
									&& StringUtils.equals(existScmCostItemInAndOutSum.getCostOrgUnitNo(),
											scmInvMaterialReqBillEntry.getCostOrgUnitNo())) {
								existScmCostItemInAndOutSum.setInitQty(existScmCostItemInAndOutSum.getInitQty()
										.add(sideFlag.multiply(scmInvMaterialReqBillEntry.getQty())));
								existScmCostItemInAndOutSum.setInitAmt(existScmCostItemInAndOutSum.getInitAmt()
										.add(sideFlag.multiply(scmInvMaterialReqBillEntry.getAmt())));
								existScmCostItemInAndOutSum.setInitAvgPrice(
										BigDecimal.ZERO.compareTo(existScmCostItemInAndOutSum.getInitQty()) == 0
												? BigDecimal.ZERO
												: existScmCostItemInAndOutSum.getInitAmt().divide(
														existScmCostItemInAndOutSum.getInitQty(), 2,
														BigDecimal.ROUND_HALF_UP));
								existScmCostItemInAndOutSum.setInitTax(existScmCostItemInAndOutSum.getInitTax()
										.add(sideFlag.multiply(scmInvMaterialReqBillEntry.getTaxAmt()
												.subtract(scmInvMaterialReqBillEntry.getAmt()))));
								existScmCostItemInAndOutSum.setInitTaxAmt(existScmCostItemInAndOutSum.getInitTaxAmt()
										.add(sideFlag.multiply(scmInvMaterialReqBillEntry.getTaxAmt())));
								exists = true;
								break;
							}
						}
					}
					if (!exists) {
						ScmCostItemInAndOutSum scmCostItemInAndOutSum = new ScmCostItemInAndOutSum(true);
						scmCostItemInAndOutSum.setItemId(scmInvMaterialReqBillEntry.getItemId());
						scmCostItemInAndOutSum.setItemNo(scmInvMaterialReqBillEntry.getItemNo());
						scmCostItemInAndOutSum.setItemName(scmInvMaterialReqBillEntry.getItemName());
						scmCostItemInAndOutSum.setSpec(scmInvMaterialReqBillEntry.getSpec());
						scmCostItemInAndOutSum.setGroupId(scmInvMaterialReqBillEntry.getGroupId());
						scmCostItemInAndOutSum.setClassCode(scmInvMaterialReqBillEntry.getGroupCode());
						scmCostItemInAndOutSum.setClassName(scmInvMaterialReqBillEntry.getGroupName());
						scmCostItemInAndOutSum.setLongNo(scmInvMaterialReqBillEntry.getLongNo());
						scmCostItemInAndOutSum.setUnitName(scmInvMaterialReqBillEntry.getUnitName());
						scmCostItemInAndOutSum.setUnit(scmInvMaterialReqBillEntry.getUnit());
						scmCostItemInAndOutSum.setCostOrgUnitNo(scmInvMaterialReqBillEntry.getCostOrgUnitNo());
						scmCostItemInAndOutSum.setInitQty(sideFlag.multiply(scmInvMaterialReqBillEntry.getQty()));
						scmCostItemInAndOutSum.setInitAmt(sideFlag.multiply(scmInvMaterialReqBillEntry.getAmt()));
						scmCostItemInAndOutSum.setInitAvgPrice(
								BigDecimal.ZERO.compareTo(scmCostItemInAndOutSum.getInitQty()) == 0 ? BigDecimal.ZERO
										: scmCostItemInAndOutSum.getInitAmt().divide(
												scmCostItemInAndOutSum.getInitQty(), 2, BigDecimal.ROUND_HALF_UP));
						scmCostItemInAndOutSum.setInitTax(sideFlag.multiply(
								scmInvMaterialReqBillEntry.getTaxAmt().subtract(scmInvMaterialReqBillEntry.getAmt())));
						scmCostItemInAndOutSum.setInitTaxAmt(sideFlag.multiply(scmInvMaterialReqBillEntry.getTaxAmt()));
						scmCostItemInAndOutSumList.add(scmCostItemInAndOutSum);
					}
				} else {
					boolean exists = false;
					if (!scmCostItemInAndOutSumList.isEmpty()) {
						for (ScmCostItemInAndOutSum existScmCostItemInAndOutSum : scmCostItemInAndOutSumList) {
							if (existScmCostItemInAndOutSum.getItemId() == scmInvMaterialReqBillEntry.getItemId()
									&& StringUtils.equals(existScmCostItemInAndOutSum.getCostOrgUnitNo(),
											scmInvMaterialReqBillEntry.getCostOrgUnitNo())) {
								existScmCostItemInAndOutSum.setAddInQty(existScmCostItemInAndOutSum.getAddInQty()
										.add(sideFlag.multiply(scmInvMaterialReqBillEntry.getQty())));
								existScmCostItemInAndOutSum.setAddInAmt(existScmCostItemInAndOutSum.getAddInAmt()
										.add(sideFlag.multiply(scmInvMaterialReqBillEntry.getAmt())));
								existScmCostItemInAndOutSum.setReceiveInQty(existScmCostItemInAndOutSum
										.getReceiveInQty().add(sideFlag.multiply(scmInvMaterialReqBillEntry.getQty())));
								existScmCostItemInAndOutSum.setReceiveInAvgPrice(
										BigDecimal.ZERO.compareTo(existScmCostItemInAndOutSum.getReceiveInQty()) == 0
												? BigDecimal.ZERO
												: existScmCostItemInAndOutSum.getReceiveInAmt().divide(
														existScmCostItemInAndOutSum.getReceiveInQty(), 2,
														BigDecimal.ROUND_HALF_UP));
								existScmCostItemInAndOutSum.setReceiveInAmt(existScmCostItemInAndOutSum
										.getReceiveInAmt().add(sideFlag.multiply(scmInvMaterialReqBillEntry.getAmt())));
								existScmCostItemInAndOutSum.setReceiveInTax(existScmCostItemInAndOutSum
										.getReceiveInTax().add(sideFlag.multiply(scmInvMaterialReqBillEntry.getTaxAmt()
												.subtract(scmInvMaterialReqBillEntry.getAmt()))));
								existScmCostItemInAndOutSum
										.setReceiveInTaxAmt(existScmCostItemInAndOutSum.getReceiveInTaxAmt()
												.add(sideFlag.multiply(scmInvMaterialReqBillEntry.getTaxAmt())));
								existScmCostItemInAndOutSum.setAddInAvgPrice(
										BigDecimal.ZERO.compareTo(existScmCostItemInAndOutSum.getAddInQty()) == 0
												? BigDecimal.ZERO
												: existScmCostItemInAndOutSum.getAddInAmt().divide(
														existScmCostItemInAndOutSum.getAddInQty(), 2,
														BigDecimal.ROUND_HALF_UP));
								existScmCostItemInAndOutSum.setAddInTax(existScmCostItemInAndOutSum.getAddInTax()
										.add(sideFlag.multiply(scmInvMaterialReqBillEntry.getTaxAmt()
												.subtract(scmInvMaterialReqBillEntry.getAmt()))));
								existScmCostItemInAndOutSum.setAddInTaxAmt(existScmCostItemInAndOutSum.getAddInTaxAmt()
										.add(sideFlag.multiply(scmInvMaterialReqBillEntry.getTaxAmt())));
								if (costCenterTypeMap.containsKey(scmInvMaterialReqBillEntry.getCostOrgUnitNo())) {
									if (StringUtils.equals("1", costCenterTypeMap.get(scmInvMaterialReqBillEntry.getCostOrgUnitNo()))) {
										// 以领代耗时也需写出库数
										existScmCostItemInAndOutSum.setOutQty(existScmCostItemInAndOutSum.getOutQty()
												.add(sideFlag.multiply(scmInvMaterialReqBillEntry.getQty())));
										existScmCostItemInAndOutSum.setOutAmt(existScmCostItemInAndOutSum.getOutAmt()
												.add(sideFlag.multiply(scmInvMaterialReqBillEntry.getAmt())));
										existScmCostItemInAndOutSum.setOutAvgPrice(
												BigDecimal.ZERO.compareTo(existScmCostItemInAndOutSum.getOutQty()) == 0
														? BigDecimal.ZERO
														: existScmCostItemInAndOutSum.getOutAmt().divide(
																existScmCostItemInAndOutSum.getOutQty(), 2,
																BigDecimal.ROUND_HALF_UP));
										existScmCostItemInAndOutSum.setOutTax(existScmCostItemInAndOutSum.getOutTax()
												.add(sideFlag.multiply(scmInvMaterialReqBillEntry.getTaxAmt()
														.subtract(scmInvMaterialReqBillEntry.getAmt()))));
										existScmCostItemInAndOutSum.setOutTaxAmt(existScmCostItemInAndOutSum.getOutTaxAmt()
												.add(sideFlag.multiply(scmInvMaterialReqBillEntry.getTaxAmt())));
									}
								}
								exists = true;
								break;
							}
						}
					}
					if (!exists) {
						// 放入明细记录结果集中
						ScmCostItemInAndOutSum scmCostItemInAndOutSum = new ScmCostItemInAndOutSum(true);
						scmCostItemInAndOutSum.setItemId(scmInvMaterialReqBillEntry.getItemId());
						scmCostItemInAndOutSum.setItemNo(scmInvMaterialReqBillEntry.getItemNo());
						scmCostItemInAndOutSum.setItemName(scmInvMaterialReqBillEntry.getItemName());
						scmCostItemInAndOutSum.setSpec(scmInvMaterialReqBillEntry.getSpec());
						scmCostItemInAndOutSum.setGroupId(scmInvMaterialReqBillEntry.getGroupId());
						scmCostItemInAndOutSum.setClassCode(scmInvMaterialReqBillEntry.getGroupCode());
						scmCostItemInAndOutSum.setClassName(scmInvMaterialReqBillEntry.getGroupName());
						scmCostItemInAndOutSum.setLongNo(scmInvMaterialReqBillEntry.getLongNo());
						scmCostItemInAndOutSum.setUnitName(scmInvMaterialReqBillEntry.getUnitName());
						scmCostItemInAndOutSum.setUnit(scmInvMaterialReqBillEntry.getUnit());
						scmCostItemInAndOutSum.setCostOrgUnitNo(scmInvMaterialReqBillEntry.getCostOrgUnitNo());
						scmCostItemInAndOutSum.setAddInQty(sideFlag.multiply(scmInvMaterialReqBillEntry.getQty()));
						scmCostItemInAndOutSum.setAddInAmt(sideFlag.multiply(scmInvMaterialReqBillEntry.getAmt()));
						scmCostItemInAndOutSum.setAddInAvgPrice(
								BigDecimal.ZERO.compareTo(scmCostItemInAndOutSum.getAddInQty()) == 0 ? BigDecimal.ZERO
										: scmCostItemInAndOutSum.getAddInAmt().divide(
												scmCostItemInAndOutSum.getAddInQty(), 2, BigDecimal.ROUND_HALF_UP));
						scmCostItemInAndOutSum.setAddInTax(sideFlag.multiply(
								scmInvMaterialReqBillEntry.getTaxAmt().subtract(scmInvMaterialReqBillEntry.getAmt())));
						scmCostItemInAndOutSum
								.setAddInTaxAmt(sideFlag.multiply(scmInvMaterialReqBillEntry.getTaxAmt()));
						scmCostItemInAndOutSum.setReceiveInQty(sideFlag.multiply(scmInvMaterialReqBillEntry.getQty()));
						scmCostItemInAndOutSum.setReceiveInAvgPrice(
								BigDecimal.ZERO.compareTo(scmCostItemInAndOutSum.getReceiveInQty()) == 0 ? BigDecimal.ZERO
										: scmCostItemInAndOutSum.getReceiveInAmt().divide(
												scmCostItemInAndOutSum.getReceiveInQty(), 2, BigDecimal.ROUND_HALF_UP));
						scmCostItemInAndOutSum.setReceiveInAmt(sideFlag.multiply(scmInvMaterialReqBillEntry.getAmt()));
						scmCostItemInAndOutSum.setReceiveInTax(sideFlag.multiply(
								scmInvMaterialReqBillEntry.getTaxAmt().subtract(scmInvMaterialReqBillEntry.getAmt())));
						scmCostItemInAndOutSum.setReceiveInTaxAmt(sideFlag.multiply(scmInvMaterialReqBillEntry.getTaxAmt()));
						
						if (costCenterTypeMap.containsKey(scmInvMaterialReqBillEntry.getCostOrgUnitNo())) {
							if (StringUtils.equals("1", costCenterTypeMap.get(scmInvMaterialReqBillEntry.getCostOrgUnitNo()))) {
								// 以领代耗时也需写出库数
								scmCostItemInAndOutSum.setOutQty(sideFlag.multiply(scmInvMaterialReqBillEntry.getQty()));
								scmCostItemInAndOutSum.setOutAmt(sideFlag.multiply(scmInvMaterialReqBillEntry.getAmt()));
								scmCostItemInAndOutSum.setOutAvgPrice(
										BigDecimal.ZERO.compareTo(scmCostItemInAndOutSum.getOutQty()) == 0 ? BigDecimal.ZERO
												: scmCostItemInAndOutSum.getOutAmt().divide(
														scmCostItemInAndOutSum.getOutQty(), 2, BigDecimal.ROUND_HALF_UP));
								scmCostItemInAndOutSum.setOutTax(sideFlag.multiply(scmInvMaterialReqBillEntry.getTaxAmt()
										.subtract(scmInvMaterialReqBillEntry.getAmt())));
								scmCostItemInAndOutSum
										.setOutTaxAmt(sideFlag.multiply(scmInvMaterialReqBillEntry.getTaxAmt()));
							}
						}
						scmCostItemInAndOutSumList.add(scmCostItemInAndOutSum);
					}
				}
			}
			long dataEndTime4 = System.currentTimeMillis();
			log.info("领料出库明细加工耗时："+(dataEndTime4-dataEndTime3)+"毫秒");
		}
		long beginTime3 = System.currentTimeMillis();
		// 获取耗用数据
		page = new Page();
		page.setModelClass(ScmInvCostConsumeEntry2.class);
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
					.put(ClassUtils.getFinalModelSimpleName(ScmInvCostConsumeEntry2.class) + "."
							+ ScmInvCostConsumeEntry2.FN_ITEMID, new QueryParam(
									ClassUtils.getFinalModelSimpleName(ScmInvCostConsumeEntry2.class) + "."
											+ ScmInvCostConsumeEntry2.FN_ITEMID,
									QueryParam.QUERY_IN, sbMaterila.toString()));
		}
		page.getParam()
				.put(ClassUtils.getFinalModelSimpleName(ScmInvCostConsume.class) + "." + ScmInvCostConsume.FN_ORGUNITNO,
						new QueryParam(
								ClassUtils.getFinalModelSimpleName(ScmInvCostConsume.class) + "."
										+ ScmInvCostConsume.FN_ORGUNITNO,
								QueryParam.QUERY_IN, costOrgUnitNos.toString()));
		
		page.getParam().put(
				ClassUtils.getFinalModelSimpleName(ScmInvCostConsume.class) + "." + ScmInvCostConsume.FN_BIZDATE,
				new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvCostConsume.class) + "."
						+ ScmInvCostConsume.FN_BIZDATE, QueryParam.QUERY_BETWEEN, FormatUtils.fmtDate(periodCalendar.getStartDate()), endDate));
		if (!StringUtils.equals("Y", status)) {
			page.getParam()
					.put(ClassUtils.getFinalModelSimpleName(ScmInvCostConsume.class) + "."
							+ ScmInvCostConsume.FN_STATUS,
					new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvCostConsume.class) + "."
							+ ScmInvCostConsume.FN_STATUS, QueryParam.QUERY_EQ, "E"));
		}
		List<ScmInvCostConsumeEntry2> scmInvCostConsumeEntryList = scmInvCostConsumeEntryBiz.queryPage(page, arglist,
				"findAllPage", param);
		long dataEndTime5 = System.currentTimeMillis();
		log.info("成本中心耗用明细查询耗时："+(dataEndTime5-beginTime3)+"毫秒");
		if (scmInvCostConsumeEntryList != null && !scmInvCostConsumeEntryList.isEmpty()) {
			log.info("成本中心耗用明细查询结果："+(scmInvCostConsumeEntryList.size())+"条记录");
			for (ScmInvCostConsumeEntry2 scmInvCostConsumeEntry : scmInvCostConsumeEntryList) {
//				OrgCostCenter2 orgCostCenter = orgCostCenterBiz
//						.selectByOrgUnitNo(scmInvCostConsumeEntry.getCostOrgUnitNo(), param);
//				if (orgCostCenter != null && StringUtils.equals("1", orgCostCenter.getCostCenterType())) {
//					continue;
//				}
				if (costCenterTypeMap.containsKey(scmInvCostConsumeEntry.getCostOrgUnitNo())) {
					if (StringUtils.equals("1",costCenterTypeMap.get(scmInvCostConsumeEntry.getCostOrgUnitNo()))) {
						continue;
					}
				}
				if (scmInvCostConsumeEntry.getBizDate().compareTo(FormatUtils.parseDate(beginDate)) < 0) {
					// 比查询日期小的统计到期初数据中，并从List中删除
					boolean exists = false;
					if (!scmCostItemInAndOutSumList.isEmpty()) {
						for (ScmCostItemInAndOutSum existScmCostItemInAndOutSum : scmCostItemInAndOutSumList) {
							if (existScmCostItemInAndOutSum.getItemId() == scmInvCostConsumeEntry.getItemId()
									&& StringUtils.equals(existScmCostItemInAndOutSum.getCostOrgUnitNo(),
											scmInvCostConsumeEntry.getCostOrgUnitNo())) {
								existScmCostItemInAndOutSum.setInitQty(existScmCostItemInAndOutSum.getInitQty()
										.subtract(scmInvCostConsumeEntry.getQty()));
								existScmCostItemInAndOutSum.setInitAmt(existScmCostItemInAndOutSum.getInitAmt()
										.subtract(scmInvCostConsumeEntry.getAmt()));
								existScmCostItemInAndOutSum.setInitAvgPrice(
										BigDecimal.ZERO.compareTo(existScmCostItemInAndOutSum.getInitQty()) == 0
												? BigDecimal.ZERO
												: existScmCostItemInAndOutSum.getInitAmt().divide(
														existScmCostItemInAndOutSum.getInitQty(), 2,
														BigDecimal.ROUND_HALF_UP));
								existScmCostItemInAndOutSum.setInitTax(
										existScmCostItemInAndOutSum.getInitTax().subtract(scmInvCostConsumeEntry
												.getTaxAmt().subtract(scmInvCostConsumeEntry.getAmt())));
								existScmCostItemInAndOutSum.setInitTaxAmt(existScmCostItemInAndOutSum.getInitTaxAmt()
										.subtract(scmInvCostConsumeEntry.getTaxAmt()));
								exists = true;
								break;
							}
						}
					}
					if (!exists) {
						ScmCostItemInAndOutSum scmCostItemInAndOutSum = new ScmCostItemInAndOutSum(true);
						scmCostItemInAndOutSum.setItemId(scmInvCostConsumeEntry.getItemId());
						scmCostItemInAndOutSum.setItemNo(scmInvCostConsumeEntry.getItemNo());
						scmCostItemInAndOutSum.setItemName(scmInvCostConsumeEntry.getItemName());
						scmCostItemInAndOutSum.setSpec(scmInvCostConsumeEntry.getSpec());
						scmCostItemInAndOutSum.setGroupId(scmInvCostConsumeEntry.getGroupId());
						scmCostItemInAndOutSum.setClassCode(scmInvCostConsumeEntry.getGroupCode());
						scmCostItemInAndOutSum.setClassName(scmInvCostConsumeEntry.getGroupName());
						scmCostItemInAndOutSum.setLongNo(scmInvCostConsumeEntry.getLongNo());
						scmCostItemInAndOutSum.setUnitName(scmInvCostConsumeEntry.getUnitName());
						scmCostItemInAndOutSum.setUnit(scmInvCostConsumeEntry.getUnit());
						scmCostItemInAndOutSum.setCostOrgUnitNo(scmInvCostConsumeEntry.getCostOrgUnitNo());
						scmCostItemInAndOutSum.setInitQty(BigDecimal.ZERO.subtract(scmInvCostConsumeEntry.getQty()));
						scmCostItemInAndOutSum.setInitAmt(BigDecimal.ZERO.subtract(scmInvCostConsumeEntry.getAmt()));
						scmCostItemInAndOutSum.setInitAvgPrice(
								BigDecimal.ZERO.compareTo(scmCostItemInAndOutSum.getInitQty()) == 0 ? BigDecimal.ZERO
										: scmCostItemInAndOutSum.getInitAmt().divide(
												scmCostItemInAndOutSum.getInitQty(), 2, BigDecimal.ROUND_HALF_UP));
						scmCostItemInAndOutSum.setInitTax(BigDecimal.ZERO.subtract(
								scmInvCostConsumeEntry.getTaxAmt().subtract(scmInvCostConsumeEntry.getAmt())));
						scmCostItemInAndOutSum
								.setInitTaxAmt(BigDecimal.ZERO.subtract(scmInvCostConsumeEntry.getTaxAmt()));
						scmCostItemInAndOutSumList.add(scmCostItemInAndOutSum);
					}
				} else {
					boolean exists = false;
					if (!scmCostItemInAndOutSumList.isEmpty()) {
						for (ScmCostItemInAndOutSum existScmCostItemInAndOutSum : scmCostItemInAndOutSumList) {
							if (existScmCostItemInAndOutSum.getItemId() == scmInvCostConsumeEntry.getItemId()
									&& StringUtils.equals(existScmCostItemInAndOutSum.getCostOrgUnitNo(),
											scmInvCostConsumeEntry.getCostOrgUnitNo())) {
								existScmCostItemInAndOutSum.setOutQty(
										existScmCostItemInAndOutSum.getOutQty().add(scmInvCostConsumeEntry.getQty()));
								existScmCostItemInAndOutSum.setOutAmt(
										existScmCostItemInAndOutSum.getOutAmt().add(scmInvCostConsumeEntry.getAmt()));
								existScmCostItemInAndOutSum.setConOutQty(existScmCostItemInAndOutSum.getConOutQty().add((scmInvCostConsumeEntry.getQty())));
								existScmCostItemInAndOutSum.setConOutAvgPrice(
										BigDecimal.ZERO.compareTo(existScmCostItemInAndOutSum.getOutQty().add(scmInvCostConsumeEntry.getQty())) == 0
												? BigDecimal.ZERO
												: existScmCostItemInAndOutSum.getConOutAmt().add((scmInvCostConsumeEntry.getAmt())).divide(
														existScmCostItemInAndOutSum.getOutQty().add(scmInvCostConsumeEntry.getQty()), 2,
														BigDecimal.ROUND_HALF_UP));
								existScmCostItemInAndOutSum.setConOutAmt(existScmCostItemInAndOutSum.getConOutAmt().add((scmInvCostConsumeEntry.getAmt())));
								existScmCostItemInAndOutSum.setConOutTax(existScmCostItemInAndOutSum.getConOutTax().add((scmInvCostConsumeEntry.getTaxAmt().subtract(scmInvCostConsumeEntry.getAmt()))));
								existScmCostItemInAndOutSum.setConOutTaxAmt(existScmCostItemInAndOutSum.getConOutTaxAmt().add((scmInvCostConsumeEntry.getTaxAmt())));
								
								existScmCostItemInAndOutSum.setOutAvgPrice(
										BigDecimal.ZERO.compareTo(existScmCostItemInAndOutSum.getOutQty()) == 0
												? BigDecimal.ZERO
												: existScmCostItemInAndOutSum.getOutAmt().divide(
														existScmCostItemInAndOutSum.getOutQty(), 2,
														BigDecimal.ROUND_HALF_UP));
								existScmCostItemInAndOutSum.setOutTax(existScmCostItemInAndOutSum.getOutTax().add(
										scmInvCostConsumeEntry.getTaxAmt().subtract(scmInvCostConsumeEntry.getAmt())));
								existScmCostItemInAndOutSum.setOutTaxAmt(existScmCostItemInAndOutSum.getOutTaxAmt()
										.add(scmInvCostConsumeEntry.getTaxAmt()));
								exists = true;
								break;
							}
						}
					}
					if (!exists) {
						// 放入明细记录结果集中
						ScmCostItemInAndOutSum scmCostItemInAndOutSum = new ScmCostItemInAndOutSum(true);
						scmCostItemInAndOutSum.setItemId(scmInvCostConsumeEntry.getItemId());
						scmCostItemInAndOutSum.setItemNo(scmInvCostConsumeEntry.getItemNo());
						scmCostItemInAndOutSum.setItemName(scmInvCostConsumeEntry.getItemName());
						scmCostItemInAndOutSum.setSpec(scmInvCostConsumeEntry.getSpec());
						scmCostItemInAndOutSum.setGroupId(scmInvCostConsumeEntry.getGroupId());
						scmCostItemInAndOutSum.setClassCode(scmInvCostConsumeEntry.getGroupCode());
						scmCostItemInAndOutSum.setClassName(scmInvCostConsumeEntry.getGroupName());
						scmCostItemInAndOutSum.setLongNo(scmInvCostConsumeEntry.getLongNo());
						scmCostItemInAndOutSum.setUnitName(scmInvCostConsumeEntry.getUnitName());
						scmCostItemInAndOutSum.setUnit(scmInvCostConsumeEntry.getUnit());
						scmCostItemInAndOutSum.setCostOrgUnitNo(scmInvCostConsumeEntry.getCostOrgUnitNo());
						scmCostItemInAndOutSum.setOutQty(scmInvCostConsumeEntry.getQty());
						scmCostItemInAndOutSum.setOutAmt(scmInvCostConsumeEntry.getAmt());
						scmCostItemInAndOutSum.setConOutQty(scmInvCostConsumeEntry.getQty());
						scmCostItemInAndOutSum.setConOutAmt(scmInvCostConsumeEntry.getAmt());
						scmCostItemInAndOutSum.setConOutTax(scmInvCostConsumeEntry.getTaxAmt().subtract(scmInvCostConsumeEntry.getAmt()));
						scmCostItemInAndOutSum.setConOutTaxAmt(scmInvCostConsumeEntry.getTaxAmt());
						
						scmCostItemInAndOutSum.setOutAvgPrice(
								BigDecimal.ZERO.compareTo(scmCostItemInAndOutSum.getOutQty()) == 0 ? BigDecimal.ZERO
										: scmCostItemInAndOutSum.getOutAmt().divide(scmCostItemInAndOutSum.getOutQty(),
												2, BigDecimal.ROUND_HALF_UP));
						scmCostItemInAndOutSum.setConOutAvgPrice(
								BigDecimal.ZERO.compareTo(scmCostItemInAndOutSum.getConOutQty()) == 0 ? BigDecimal.ZERO
										: scmCostItemInAndOutSum.getConOutAmt().divide(scmCostItemInAndOutSum.getConOutQty(),
												2, BigDecimal.ROUND_HALF_UP));
						scmCostItemInAndOutSum.setOutTax(
								scmInvCostConsumeEntry.getTaxAmt().subtract(scmInvCostConsumeEntry.getAmt()));
						scmCostItemInAndOutSum.setOutTaxAmt(scmInvCostConsumeEntry.getTaxAmt());
						scmCostItemInAndOutSumList.add(scmCostItemInAndOutSum);
					}
				}
			}
			long dataEndTime6 = System.currentTimeMillis();
			log.info("成本中心耗用明细加工耗时："+(dataEndTime6-dataEndTime5)+"毫秒");
		}
		long beginTimeInventory = System.currentTimeMillis();
		// 盘存差异
		page = new Page();
		page.setModelClass(ScmInvCountingCostCenterEntry2.class);
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
					.put(ClassUtils.getFinalModelSimpleName(ScmInvCountingCostCenterEntry2.class) + "."
							+ ScmInvCountingCostCenterEntry2.FN_ITEMID,
					new QueryParam(
							ClassUtils.getFinalModelSimpleName(ScmInvCountingCostCenterEntry2.class) + "."
									+ ScmInvCountingCostCenterEntry2.FN_ITEMID,
							QueryParam.QUERY_IN, sbMaterila.toString()));
		}
		page.getParam()
				.put(ClassUtils.getFinalModelSimpleName(ScmInvCountingCostCenter.class) + "."
						+ ScmInvCountingCostCenter.FN_ORGUNITNO,
				new QueryParam(
						ClassUtils.getFinalModelSimpleName(ScmInvCountingCostCenter.class) + "."
								+ ScmInvCountingCostCenter.FN_ORGUNITNO,
						QueryParam.QUERY_IN, costOrgUnitNos.toString()));
		page.getParam().put(
				ClassUtils.getFinalModelSimpleName(ScmInvCountingTask.class) + "." + ScmInvCountingTask.FN_BIZDATE,
				new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvCountingTask.class) + "."
						+ ScmInvCountingTask.FN_BIZDATE, QueryParam.QUERY_BETWEEN, FormatUtils.fmtDate(periodCalendar.getStartDate()), endDate));
		page.getParam()
				.put(ClassUtils.getFinalModelSimpleName(ScmInvCountingCostCenterEntry2.class) + "."
						+ ScmInvCountingCostCenterEntry2.FN_DIFFERQTY,
				new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvCountingCostCenterEntry2.class) + "."
						+ ScmInvCountingCostCenterEntry2.FN_DIFFERQTY, QueryParam.QUERY_NE, "0"));
		if (!StringUtils.equals("Y", status)) {
			page.getParam()
					.put(ClassUtils.getFinalModelSimpleName(ScmInvCountingTask.class) + "."
							+ ScmInvCountingTask.FN_STATUS,
					new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvCountingTask.class) + "."
							+ ScmInvCountingTask.FN_STATUS, QueryParam.QUERY_EQ, "O"));
		} else {
			page.getParam()
					.put(ClassUtils.getFinalModelSimpleName(ScmInvCountingTask.class) + "."
							+ ScmInvCountingTask.FN_STATUS,
					new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvCountingTask.class) + "."
							+ ScmInvCountingTask.FN_STATUS, QueryParam.QUERY_IN, "'T','B','O'"));
		}
		List<ScmInvCountingCostCenterEntry2> scmInvCountingCostCenterEntryList = scmInvCountingCostCenterEntryBiz
				.queryPage(page, arglist, "findAllPage", param);
		long dataEndTimeInventory = System.currentTimeMillis();
		log.info("成本中心盘存差异明细查询耗时："+(dataEndTimeInventory-beginTimeInventory)+"毫秒");
		if (scmInvCountingCostCenterEntryList != null && !scmInvCountingCostCenterEntryList.isEmpty()) {
			log.info("成本中心盘存差异明细查询结果："+(scmInvCountingCostCenterEntryList.size())+"条记录");
			for (ScmInvCountingCostCenterEntry2 scmInvCountingCostCenterEntry : scmInvCountingCostCenterEntryList) {
//				OrgCostCenter2 orgCostCenter = orgCostCenterBiz
//						.selectByOrgUnitNo(scmInvCountingCostCenterEntry.getOrgUnitNo(), param);
//				if (orgCostCenter != null && StringUtils.equals("1", orgCostCenter.getCostCenterType())) {
//					continue;
//				}
				if (costCenterTypeMap.containsKey(scmInvCountingCostCenterEntry.getOrgUnitNo())) {
					if (StringUtils.equals("1",costCenterTypeMap.get(scmInvCountingCostCenterEntry.getOrgUnitNo()))) {
						continue;
					}
				}
				if (scmInvCountingCostCenterEntry.getBizDate().compareTo(FormatUtils.parseDate(beginDate)) < 0) {
					// 比查询日期小的统计到期初数据中，并从List中删除
					boolean exists = false;
					if (!scmCostItemInAndOutSumList.isEmpty()) {
						for (ScmCostItemInAndOutSum existScmCostItemInAndOutSum : scmCostItemInAndOutSumList) {
							if (existScmCostItemInAndOutSum.getItemId() == scmInvCountingCostCenterEntry.getItemId()
									&& StringUtils.equals(existScmCostItemInAndOutSum.getCostOrgUnitNo(),
											scmInvCountingCostCenterEntry.getOrgUnitNo())) {
								existScmCostItemInAndOutSum.setInitQty(existScmCostItemInAndOutSum.getInitQty()
										.add(scmInvCountingCostCenterEntry.getDifferQty()));
								existScmCostItemInAndOutSum.setInitAmt(existScmCostItemInAndOutSum.getInitAmt()
										.add(scmInvCountingCostCenterEntry.getDifferAmt()));
								existScmCostItemInAndOutSum.setInitAvgPrice(
										BigDecimal.ZERO.compareTo(existScmCostItemInAndOutSum.getInitQty()) == 0
												? BigDecimal.ZERO
												: existScmCostItemInAndOutSum.getInitAmt().divide(
														existScmCostItemInAndOutSum.getInitQty(), 2,
														BigDecimal.ROUND_HALF_UP));
								existScmCostItemInAndOutSum.setInitTax(existScmCostItemInAndOutSum.getInitTax()
										.add(scmInvCountingCostCenterEntry.getDifferTaxAmt()
												.subtract(scmInvCountingCostCenterEntry.getDifferAmt())));
								existScmCostItemInAndOutSum.setInitTaxAmt(existScmCostItemInAndOutSum.getInitTaxAmt()
										.add(scmInvCountingCostCenterEntry.getDifferTaxAmt()));
								exists = true;
								break;
							}
						}
					}
					if (!exists) {
						ScmCostItemInAndOutSum scmCostItemInAndOutSum = new ScmCostItemInAndOutSum(true);
						scmCostItemInAndOutSum.setItemId(scmInvCountingCostCenterEntry.getItemId());
						scmCostItemInAndOutSum.setItemNo(scmInvCountingCostCenterEntry.getItemNo());
						scmCostItemInAndOutSum.setItemName(scmInvCountingCostCenterEntry.getItemName());
						scmCostItemInAndOutSum.setSpec(scmInvCountingCostCenterEntry.getSpec());
						scmCostItemInAndOutSum.setGroupId(scmInvCountingCostCenterEntry.getGroupId());
						scmCostItemInAndOutSum.setClassCode(scmInvCountingCostCenterEntry.getGroupCode());
						scmCostItemInAndOutSum.setClassName(scmInvCountingCostCenterEntry.getGroupName());
						scmCostItemInAndOutSum.setLongNo(scmInvCountingCostCenterEntry.getLongNo());
						scmCostItemInAndOutSum.setUnitName(scmInvCountingCostCenterEntry.getUnitName());
						scmCostItemInAndOutSum.setUnit(scmInvCountingCostCenterEntry.getUnit());
						scmCostItemInAndOutSum.setCostOrgUnitNo(scmInvCountingCostCenterEntry.getOrgUnitNo());
						scmCostItemInAndOutSum.setInitQty(scmInvCountingCostCenterEntry.getDifferQty());
						scmCostItemInAndOutSum.setInitAmt(scmInvCountingCostCenterEntry.getDifferAmt());
						scmCostItemInAndOutSum.setInitAvgPrice(
								BigDecimal.ZERO.compareTo(scmCostItemInAndOutSum.getInitQty()) == 0 ? BigDecimal.ZERO
										: scmCostItemInAndOutSum.getInitAmt().divide(
												scmCostItemInAndOutSum.getInitQty(), 2, BigDecimal.ROUND_HALF_UP));
						scmCostItemInAndOutSum.setInitTax(scmInvCountingCostCenterEntry.getDifferTaxAmt()
								.subtract(scmInvCountingCostCenterEntry.getDifferAmt()));
						scmCostItemInAndOutSum.setInitTaxAmt(scmInvCountingCostCenterEntry.getDifferTaxAmt());
						scmCostItemInAndOutSumList.add(scmCostItemInAndOutSum);
					}
				} else {
					boolean exists = false;
					if (!scmCostItemInAndOutSumList.isEmpty()) {
						for (ScmCostItemInAndOutSum existScmCostItemInAndOutSum : scmCostItemInAndOutSumList) {
							if (existScmCostItemInAndOutSum.getItemId() == scmInvCountingCostCenterEntry.getItemId()
									&& StringUtils.equals(existScmCostItemInAndOutSum.getCostOrgUnitNo(),
											scmInvCountingCostCenterEntry.getOrgUnitNo())) {
//								差异数量是 负，则 outQty - differQty ,即发出数量加上 差异数量
								if (scmInvCountingCostCenterEntry.getDifferQty().compareTo(BigDecimal.ZERO) < 0) {
									existScmCostItemInAndOutSum.setOutQty(existScmCostItemInAndOutSum.getOutQty()
											.subtract(scmInvCountingCostCenterEntry.getDifferQty()));
									existScmCostItemInAndOutSum.setOutAmt(existScmCostItemInAndOutSum.getOutAmt()
											.subtract(scmInvCountingCostCenterEntry.getDifferAmt()));
									existScmCostItemInAndOutSum.setInvenOutQty(existScmCostItemInAndOutSum.getInvenOutQty().add((scmInvCountingCostCenterEntry.getDifferQty())));
									existScmCostItemInAndOutSum.setInvenOutAvgPrice(
											BigDecimal.ZERO.compareTo(existScmCostItemInAndOutSum.getInvenOutQty().add((scmInvCountingCostCenterEntry.getDifferQty()))) == 0
													? BigDecimal.ZERO
													: existScmCostItemInAndOutSum.getInvenOutAmt().add((scmInvCountingCostCenterEntry.getDifferAmt())).divide(
															existScmCostItemInAndOutSum.getInvenOutQty().add((scmInvCountingCostCenterEntry.getDifferQty())), 2,
															BigDecimal.ROUND_HALF_UP));
									existScmCostItemInAndOutSum.setInvenOutAmt(existScmCostItemInAndOutSum.getInvenOutAmt().add((scmInvCountingCostCenterEntry.getDifferAmt())));
									existScmCostItemInAndOutSum.setInvenOutTax(existScmCostItemInAndOutSum.getInvenOutTax().add((scmInvCountingCostCenterEntry.getDifferTaxAmt().subtract(scmInvCountingCostCenterEntry.getDifferAmt()))));
									existScmCostItemInAndOutSum.setInvenOutTaxAmt(existScmCostItemInAndOutSum.getInvenOutTaxAmt().add((scmInvCountingCostCenterEntry.getDifferTaxAmt())));
									existScmCostItemInAndOutSum.setOutAvgPrice(
											BigDecimal.ZERO.compareTo(existScmCostItemInAndOutSum.getOutQty()) == 0
													? BigDecimal.ZERO
													: existScmCostItemInAndOutSum.getOutAmt().divide(
															existScmCostItemInAndOutSum.getOutQty(), 2,
															BigDecimal.ROUND_HALF_UP));
									existScmCostItemInAndOutSum.setOutTax(existScmCostItemInAndOutSum.getOutTax()
											.subtract(scmInvCountingCostCenterEntry.getDifferTaxAmt()
													.subtract(scmInvCountingCostCenterEntry.getDifferAmt())));
									existScmCostItemInAndOutSum.setOutTaxAmt(existScmCostItemInAndOutSum.getOutTaxAmt()
											.subtract(scmInvCountingCostCenterEntry.getDifferTaxAmt()));
								} else {
//									差异数量是 正，则 addQty + differQty ,即收入数量加上 差异数量
									existScmCostItemInAndOutSum.setAddInQty(existScmCostItemInAndOutSum.getAddInQty()
											.add(scmInvCountingCostCenterEntry.getDifferQty()));
									existScmCostItemInAndOutSum.setAddInAmt(existScmCostItemInAndOutSum.getAddInAmt()
											.add(scmInvCountingCostCenterEntry.getDifferAmt()));
									existScmCostItemInAndOutSum.setAddInAvgPrice(
											BigDecimal.ZERO.compareTo(existScmCostItemInAndOutSum.getAddInQty()) == 0
													? BigDecimal.ZERO
													: existScmCostItemInAndOutSum.getAddInAmt().divide(
															existScmCostItemInAndOutSum.getAddInQty(), 2,
															BigDecimal.ROUND_HALF_UP));
									existScmCostItemInAndOutSum.setAddInTax(existScmCostItemInAndOutSum.getAddInTax()
											.add(scmInvCountingCostCenterEntry.getDifferTaxAmt()
													.subtract(scmInvCountingCostCenterEntry.getDifferAmt())));
									existScmCostItemInAndOutSum.setAddInTaxAmt(existScmCostItemInAndOutSum
											.getAddInTaxAmt().add(scmInvCountingCostCenterEntry.getDifferTaxAmt()));
									existScmCostItemInAndOutSum.setInvenOutQty(existScmCostItemInAndOutSum.getInvenOutQty().add((scmInvCountingCostCenterEntry.getDifferQty())));
									existScmCostItemInAndOutSum.setInvenOutAvgPrice(
											BigDecimal.ZERO.compareTo(existScmCostItemInAndOutSum.getInvenOutQty().add((scmInvCountingCostCenterEntry.getDifferQty()))) == 0
													? BigDecimal.ZERO
													: existScmCostItemInAndOutSum.getInvenOutAmt().add((scmInvCountingCostCenterEntry.getDifferAmt())).divide(
															existScmCostItemInAndOutSum.getInvenOutQty().add((scmInvCountingCostCenterEntry.getDifferQty())), 2,
															BigDecimal.ROUND_HALF_UP));
									existScmCostItemInAndOutSum.setInvenOutAmt(existScmCostItemInAndOutSum.getInvenOutAmt().add((scmInvCountingCostCenterEntry.getDifferAmt())));
									existScmCostItemInAndOutSum.setInvenOutTax(existScmCostItemInAndOutSum.getInvenOutTax().add((scmInvCountingCostCenterEntry.getDifferTaxAmt().subtract(scmInvCountingCostCenterEntry.getDifferAmt()))));
									existScmCostItemInAndOutSum.setInvenOutTaxAmt(existScmCostItemInAndOutSum.getInvenOutTaxAmt().add((scmInvCountingCostCenterEntry.getDifferTaxAmt())));
								}
								exists = true;
								break;
							}
						}
					}
					if (!exists) {
						// 放入明细记录结果集中
						ScmCostItemInAndOutSum scmCostItemInAndOutSum = new ScmCostItemInAndOutSum(true);
						scmCostItemInAndOutSum.setItemId(scmInvCountingCostCenterEntry.getItemId());
						scmCostItemInAndOutSum.setItemNo(scmInvCountingCostCenterEntry.getItemNo());
						scmCostItemInAndOutSum.setItemName(scmInvCountingCostCenterEntry.getItemName());
						scmCostItemInAndOutSum.setSpec(scmInvCountingCostCenterEntry.getSpec());
						scmCostItemInAndOutSum.setGroupId(scmInvCountingCostCenterEntry.getGroupId());
						scmCostItemInAndOutSum.setClassCode(scmInvCountingCostCenterEntry.getGroupCode());
						scmCostItemInAndOutSum.setClassName(scmInvCountingCostCenterEntry.getGroupName());
						scmCostItemInAndOutSum.setLongNo(scmInvCountingCostCenterEntry.getLongNo());
						scmCostItemInAndOutSum.setUnitName(scmInvCountingCostCenterEntry.getUnitName());
						scmCostItemInAndOutSum.setUnit(scmInvCountingCostCenterEntry.getUnit());
						scmCostItemInAndOutSum.setCostOrgUnitNo(scmInvCountingCostCenterEntry.getOrgUnitNo());
						if (scmInvCountingCostCenterEntry.getDifferQty().compareTo(BigDecimal.ZERO) < 0) {
							scmCostItemInAndOutSum.setOutQty(BigDecimal.ZERO.subtract(scmInvCountingCostCenterEntry.getDifferQty()));
							scmCostItemInAndOutSum.setOutAmt(BigDecimal.ZERO.subtract(scmInvCountingCostCenterEntry.getDifferAmt()));
							scmCostItemInAndOutSum.setInvenOutQty(scmInvCountingCostCenterEntry.getDifferQty());
							scmCostItemInAndOutSum.setInvenOutAvgPrice(
									BigDecimal.ZERO.compareTo(scmInvCountingCostCenterEntry.getDifferQty()) == 0 ? BigDecimal.ZERO
											: scmInvCountingCostCenterEntry.getDifferAmt().divide(
													scmInvCountingCostCenterEntry.getDifferQty(), 2, BigDecimal.ROUND_HALF_UP));
							scmCostItemInAndOutSum.setInvenOutAmt(scmInvCountingCostCenterEntry.getDifferAmt());
							scmCostItemInAndOutSum.setInvenOutTax(scmInvCountingCostCenterEntry.getDifferAmt()
									.subtract(scmInvCountingCostCenterEntry.getDifferTaxAmt()));
							scmCostItemInAndOutSum.setInvenOutTaxAmt(scmInvCountingCostCenterEntry.getDifferAmt());
						
							scmCostItemInAndOutSum.setOutAvgPrice(
									BigDecimal.ZERO.compareTo(scmCostItemInAndOutSum.getOutQty()) == 0 ? BigDecimal.ZERO
											: scmCostItemInAndOutSum.getOutAmt().divide(
													scmCostItemInAndOutSum.getOutQty(), 2, BigDecimal.ROUND_HALF_UP));
							scmCostItemInAndOutSum.setOutTax(scmInvCountingCostCenterEntry.getDifferAmt()
									.subtract(scmInvCountingCostCenterEntry.getDifferTaxAmt()));
							scmCostItemInAndOutSum.setOutTaxAmt(BigDecimal.ZERO.subtract(scmInvCountingCostCenterEntry.getDifferTaxAmt()));
						} else {
							scmCostItemInAndOutSum.setAddInQty(scmInvCountingCostCenterEntry.getDifferQty());
							scmCostItemInAndOutSum.setAddInAmt(scmInvCountingCostCenterEntry.getDifferAmt());
							scmCostItemInAndOutSum
									.setAddInAvgPrice(
											BigDecimal.ZERO.compareTo(scmCostItemInAndOutSum.getAddInQty()) == 0
													? BigDecimal.ZERO
													: scmCostItemInAndOutSum.getAddInAmt().divide(
															scmCostItemInAndOutSum.getAddInQty(), 2,
															BigDecimal.ROUND_HALF_UP));
							scmCostItemInAndOutSum.setAddInTax(scmInvCountingCostCenterEntry.getDifferTaxAmt()
									.subtract(scmInvCountingCostCenterEntry.getDifferAmt()));
							scmCostItemInAndOutSum.setAddInTaxAmt(scmInvCountingCostCenterEntry.getDifferTaxAmt());
							scmCostItemInAndOutSum.setInvenOutQty(scmInvCountingCostCenterEntry.getDifferQty());
							scmCostItemInAndOutSum
							.setInvenOutAvgPrice(
									BigDecimal.ZERO.compareTo(scmInvCountingCostCenterEntry.getDifferQty()) == 0
											? BigDecimal.ZERO
											: scmInvCountingCostCenterEntry.getDifferAmt().divide(
													scmInvCountingCostCenterEntry.getDifferQty(), 2,
													BigDecimal.ROUND_HALF_UP));
							scmCostItemInAndOutSum.setInvenOutAmt(scmInvCountingCostCenterEntry.getDifferAmt());
							scmCostItemInAndOutSum.setInvenOutTax(scmInvCountingCostCenterEntry.getDifferTaxAmt()
									.subtract(scmInvCountingCostCenterEntry.getDifferAmt()));
							scmCostItemInAndOutSum.setInvenOutTaxAmt(scmInvCountingCostCenterEntry.getDifferAmt());
						}
						scmCostItemInAndOutSumList.add(scmCostItemInAndOutSum);
					}
				}
			}
			long dataEndTimeInventory1 = System.currentTimeMillis();
			log.info("成本中心盘存差异明细加工耗时："+(dataEndTimeInventory1-dataEndTimeInventory)+"毫秒");
		}
//		获取销售出库数据
		long beginTimeInvSale = System.currentTimeMillis();
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
							+ ScmInvSaleIssueBillEntry2.FN_ITEMID, new QueryParam(
									ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBillEntry2.class) + "."
											+ ScmInvSaleIssueBillEntry2.FN_ITEMID,
									QueryParam.QUERY_IN, sbMaterila.toString()));
		}
		page.getParamOr()
				.put(ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBillEntry.class) + "." + ScmInvSaleIssueBillEntry.FN_OUTCOSTORGUNITNO,
						new QueryParam(
								ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBillEntry.class) + "."
										+ ScmInvSaleIssueBillEntry.FN_OUTCOSTORGUNITNO,
								QueryParam.QUERY_IN, costOrgUnitNos.toString()));
		page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBill.class) + "." + ScmInvSaleIssueBill.FN_BIZDATE,
				new QueryParam(
						ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBill.class) + "." + ScmInvSaleIssueBill.FN_BIZDATE,
						QueryParam.QUERY_BETWEEN, FormatUtils.fmtDate(periodCalendar.getStartDate()), endDate));
		if (!StringUtils.equals("Y", status)) {
			page.getParam()
					.put(ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBill.class) + "."
							+ ScmInvSaleIssueBill.FN_STATUS,
					new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBill.class) + "."
							+ ScmInvSaleIssueBill.FN_STATUS, QueryParam.QUERY_EQ, "E"));
		}
		List<ScmInvSaleIssueBillEntry2> scmInvSaleIssueBillEntry2s = scmInvSaleIssueBillEntryBiz.queryPage(page,
				arglist, "findAllPage", param);
		long endTimeInvSale = System.currentTimeMillis();
		log.info("销售出库明细查询耗时："+(endTimeInvSale-beginTimeInvSale)+"毫秒");
		if(scmInvSaleIssueBillEntry2s != null && !scmInvSaleIssueBillEntry2s.isEmpty()) {
			log.info("销售出库明细查询结果："+(scmInvSaleIssueBillEntry2s.size())+"条记录");
			for(ScmInvSaleIssueBillEntry2 scmInvSaleIssueBillEntry : scmInvSaleIssueBillEntry2s) {
				BigDecimal sideFlag = BigDecimal.ONE;
				if(StringUtils.contains("1,2,3",scmInvSaleIssueBillEntry.getBizType())) {
					sideFlag = new BigDecimal("-1");
				}
				if(scmInvSaleIssueBillEntry.getBizDate().compareTo(FormatUtils.parseDate(beginDate)) < 0) {
					if (costCenterTypeMap.containsKey(scmInvSaleIssueBillEntry.getOutCostOrgUnitNo())) {
						if (StringUtils.equals("1",costCenterTypeMap.get(scmInvSaleIssueBillEntry.getOutCostOrgUnitNo()))) {
							continue;
						}
					}
					// 比查询日期小的统计到期初数据中，并从List中删除
					boolean exists = false;
					if (!scmCostItemInAndOutSumList.isEmpty()) {
						for (ScmCostItemInAndOutSum existScmCostItemInAndOutSum : scmCostItemInAndOutSumList) {
							if (existScmCostItemInAndOutSum.getItemId() == scmInvSaleIssueBillEntry.getItemId()
									&& StringUtils.equals(existScmCostItemInAndOutSum.getCostOrgUnitNo(),
											scmInvSaleIssueBillEntry.getOutCostOrgUnitNo())) {
								// 期间 数据存在   出库：减期初数据 
								existScmCostItemInAndOutSum.setInitQty(existScmCostItemInAndOutSum.getInitQty()
										.add(sideFlag.multiply(scmInvSaleIssueBillEntry.getQty())));
								existScmCostItemInAndOutSum.setInitAmt(existScmCostItemInAndOutSum.getInitAmt()
										.add(sideFlag.multiply(scmInvSaleIssueBillEntry.getAmt())));
								existScmCostItemInAndOutSum.setInitAvgPrice(
										BigDecimal.ZERO.compareTo(existScmCostItemInAndOutSum.getInitQty()) == 0
												? BigDecimal.ZERO
												: existScmCostItemInAndOutSum.getInitAmt().divide(
														existScmCostItemInAndOutSum.getInitQty(), 2,
														BigDecimal.ROUND_HALF_UP));
								existScmCostItemInAndOutSum.setInitTax(existScmCostItemInAndOutSum.getInitTax()
										.add(sideFlag.multiply(scmInvSaleIssueBillEntry.getTaxAmt()
												.subtract(scmInvSaleIssueBillEntry.getAmt()))));
								existScmCostItemInAndOutSum.setInitTaxAmt(existScmCostItemInAndOutSum.getInitTaxAmt()
										.add(sideFlag.multiply(scmInvSaleIssueBillEntry.getTaxAmt())));
								exists = true;
								break;
							}
						}
					}
//					期间数据不存在
					if (!exists) {
						ScmCostItemInAndOutSum scmCostItemInAndOutSum = new ScmCostItemInAndOutSum(true);
						scmCostItemInAndOutSum.setItemId(scmInvSaleIssueBillEntry.getItemId());
						scmCostItemInAndOutSum.setItemNo(scmInvSaleIssueBillEntry.getItemNo());
						scmCostItemInAndOutSum.setItemName(scmInvSaleIssueBillEntry.getItemName());
						scmCostItemInAndOutSum.setSpec(scmInvSaleIssueBillEntry.getSpec());
						scmCostItemInAndOutSum.setGroupId(scmInvSaleIssueBillEntry.getGroupId());
//						scmCostItemInAndOutSum.setClassCode(scmInvSaleIssueBillEntry.getGroupCode());
						scmCostItemInAndOutSum.setClassName(scmInvSaleIssueBillEntry.getGroupName());
						scmCostItemInAndOutSum.setLongNo(scmInvSaleIssueBillEntry.getLongNo());
						scmCostItemInAndOutSum.setUnitName(scmInvSaleIssueBillEntry.getUnitName());
						scmCostItemInAndOutSum.setUnit(scmInvSaleIssueBillEntry.getUnit());
						scmCostItemInAndOutSum.setCostOrgUnitNo(scmInvSaleIssueBillEntry.getOutCostOrgUnitNo());
						scmCostItemInAndOutSum.setInitQty(sideFlag.multiply(scmInvSaleIssueBillEntry.getQty()));
						scmCostItemInAndOutSum.setInitAmt(sideFlag.multiply(scmInvSaleIssueBillEntry.getAmt()));
						scmCostItemInAndOutSum.setInitAvgPrice(
								BigDecimal.ZERO.compareTo(scmCostItemInAndOutSum.getInitQty()) == 0 ? BigDecimal.ZERO
										: scmCostItemInAndOutSum.getInitAmt().divide(
												scmCostItemInAndOutSum.getInitQty(), 2, BigDecimal.ROUND_HALF_UP));
						scmCostItemInAndOutSum.setInitTax(sideFlag.multiply(
								scmInvSaleIssueBillEntry.getTaxAmt().subtract(scmInvSaleIssueBillEntry.getAmt())));
						scmCostItemInAndOutSum.setInitTaxAmt(sideFlag.multiply(scmInvSaleIssueBillEntry.getTaxAmt()));
						scmCostItemInAndOutSumList.add(scmCostItemInAndOutSum);
					}
				}else {
					boolean exists = false;
					if (!scmCostItemInAndOutSumList.isEmpty()) {
						for (ScmCostItemInAndOutSum existScmCostItemInAndOutSum : scmCostItemInAndOutSumList) {
							if (existScmCostItemInAndOutSum.getItemId() == scmInvSaleIssueBillEntry.getItemId()
									&& StringUtils.equals(existScmCostItemInAndOutSum.getCostOrgUnitNo(),
											scmInvSaleIssueBillEntry.getOutCostOrgUnitNo())) {
//								出库：AddInQty 减
								existScmCostItemInAndOutSum.setAddInQty(existScmCostItemInAndOutSum.getAddInQty().add(sideFlag.multiply(scmInvSaleIssueBillEntry.getQty())));
								existScmCostItemInAndOutSum.setAddInAmt(existScmCostItemInAndOutSum.getAddInAmt().add(sideFlag.multiply(scmInvSaleIssueBillEntry.getAmt())));
								existScmCostItemInAndOutSum.setInvSaleOutQty(existScmCostItemInAndOutSum.getInvSaleOutQty().add(sideFlag.multiply(scmInvSaleIssueBillEntry.getQty())));
								existScmCostItemInAndOutSum.setInvSaleOutAvgPrice(
										BigDecimal.ZERO.compareTo(existScmCostItemInAndOutSum.getInvSaleOutQty()) == 0
												? BigDecimal.ZERO
												: existScmCostItemInAndOutSum.getInvSaleOutAmt().divide(
														existScmCostItemInAndOutSum.getInvSaleOutQty(), 2,
														BigDecimal.ROUND_HALF_UP));
								existScmCostItemInAndOutSum.setInvSaleOutAmt(existScmCostItemInAndOutSum.getInvSaleOutAmt().add(sideFlag.multiply(scmInvSaleIssueBillEntry.getAmt())));
								existScmCostItemInAndOutSum.setInvSaleOutTax(existScmCostItemInAndOutSum.getInvSaleOutTax().add(sideFlag.multiply(scmInvSaleIssueBillEntry.getTaxAmt().subtract(scmInvSaleIssueBillEntry.getAmt()))));
								existScmCostItemInAndOutSum.setInvSaleOutTaxAmt(existScmCostItemInAndOutSum.getInvSaleOutTaxAmt().add(sideFlag.multiply(scmInvSaleIssueBillEntry.getTaxAmt())));
								existScmCostItemInAndOutSum.setAddInAvgPrice(
										BigDecimal.ZERO.compareTo(existScmCostItemInAndOutSum.getAddInQty()) == 0
												? BigDecimal.ZERO
												: existScmCostItemInAndOutSum.getAddInAmt().divide(
														existScmCostItemInAndOutSum.getAddInQty(), 2,
														BigDecimal.ROUND_HALF_UP));
								existScmCostItemInAndOutSum.setAddInTax(existScmCostItemInAndOutSum.getAddInTax().add(sideFlag.multiply(scmInvSaleIssueBillEntry.getTaxAmt().subtract(scmInvSaleIssueBillEntry.getAmt()))));
								existScmCostItemInAndOutSum.setAddInTaxAmt(existScmCostItemInAndOutSum.getAddInTaxAmt().add(sideFlag.multiply(scmInvSaleIssueBillEntry.getTaxAmt())));
								if (costCenterTypeMap.containsKey(scmInvSaleIssueBillEntry.getOutCostOrgUnitNo())) {
									if (StringUtils.equals("1", costCenterTypeMap.get(scmInvSaleIssueBillEntry.getOutCostOrgUnitNo()))) {
										// 以领代耗时也需写出库数 OutQty 减 
										existScmCostItemInAndOutSum.setOutQty(existScmCostItemInAndOutSum.getOutQty().add(sideFlag.multiply(scmInvSaleIssueBillEntry.getQty())));
										existScmCostItemInAndOutSum.setOutAmt(existScmCostItemInAndOutSum.getOutAmt().add(sideFlag.multiply(scmInvSaleIssueBillEntry.getAmt())));
										existScmCostItemInAndOutSum.setOutAvgPrice(
												BigDecimal.ZERO.compareTo(existScmCostItemInAndOutSum.getOutQty()) == 0
														? BigDecimal.ZERO
														: existScmCostItemInAndOutSum.getOutAmt().divide(
																existScmCostItemInAndOutSum.getOutQty(), 2,
																BigDecimal.ROUND_HALF_UP));
										existScmCostItemInAndOutSum.setOutTax(existScmCostItemInAndOutSum.getOutTax().add(sideFlag.multiply(scmInvSaleIssueBillEntry.getTaxAmt().subtract(scmInvSaleIssueBillEntry.getAmt()))));
										existScmCostItemInAndOutSum.setOutTaxAmt(existScmCostItemInAndOutSum.getOutTaxAmt().add(sideFlag.multiply(scmInvSaleIssueBillEntry.getTaxAmt())));
									}
								}
								exists = true;
								break;
							}
						}
					}
					if (!exists) {
						// 放入明细记录结果集中
						ScmCostItemInAndOutSum scmCostItemInAndOutSum = new ScmCostItemInAndOutSum(true);
						scmCostItemInAndOutSum.setItemId(scmInvSaleIssueBillEntry.getItemId());
						scmCostItemInAndOutSum.setItemNo(scmInvSaleIssueBillEntry.getItemNo());
						scmCostItemInAndOutSum.setItemName(scmInvSaleIssueBillEntry.getItemName());
						scmCostItemInAndOutSum.setSpec(scmInvSaleIssueBillEntry.getSpec());
						scmCostItemInAndOutSum.setGroupId(scmInvSaleIssueBillEntry.getGroupId());
//						scmCostItemInAndOutSum.setClassCode(scmInvSaleIssueBillEntry.getGroupCode());
						scmCostItemInAndOutSum.setClassName(scmInvSaleIssueBillEntry.getGroupName());
						scmCostItemInAndOutSum.setLongNo(scmInvSaleIssueBillEntry.getLongNo());
						scmCostItemInAndOutSum.setUnitName(scmInvSaleIssueBillEntry.getUnitName());
						scmCostItemInAndOutSum.setUnit(scmInvSaleIssueBillEntry.getUnit());
						scmCostItemInAndOutSum.setCostOrgUnitNo(scmInvSaleIssueBillEntry.getOutCostOrgUnitNo());
						scmCostItemInAndOutSum.setAddInQty(sideFlag.multiply(scmInvSaleIssueBillEntry.getQty()));
						scmCostItemInAndOutSum.setAddInAmt(sideFlag.multiply(scmInvSaleIssueBillEntry.getAmt()));
						scmCostItemInAndOutSum.setAddInAvgPrice(
								BigDecimal.ZERO.compareTo(scmCostItemInAndOutSum.getAddInQty()) == 0 ? BigDecimal.ZERO
										: scmCostItemInAndOutSum.getAddInAmt().divide(
												scmCostItemInAndOutSum.getAddInQty(), 2, BigDecimal.ROUND_HALF_UP));
						scmCostItemInAndOutSum.setAddInTax(sideFlag.multiply(
								scmInvSaleIssueBillEntry.getTaxAmt().subtract(scmInvSaleIssueBillEntry.getAmt())));
						scmCostItemInAndOutSum
								.setAddInTaxAmt(sideFlag.multiply(scmInvSaleIssueBillEntry.getTaxAmt()));
						scmCostItemInAndOutSum.setInvSaleOutQty(sideFlag.multiply(scmInvSaleIssueBillEntry.getQty()));
						scmCostItemInAndOutSum.setInvSaleOutAvgPrice(
								BigDecimal.ZERO.compareTo(scmCostItemInAndOutSum.getInvSaleOutQty()) == 0 ? BigDecimal.ZERO
										: scmCostItemInAndOutSum.getInvSaleOutAmt().divide(
												scmCostItemInAndOutSum.getInvSaleOutQty(), 2, BigDecimal.ROUND_HALF_UP));
						scmCostItemInAndOutSum.setInvSaleOutAmt(sideFlag.multiply(scmInvSaleIssueBillEntry.getAmt()));
						scmCostItemInAndOutSum.setInvSaleOutTax(sideFlag.multiply(
								scmInvSaleIssueBillEntry.getTaxAmt().subtract(scmInvSaleIssueBillEntry.getAmt())));
						scmCostItemInAndOutSum.setInvSaleOutTaxAmt(sideFlag.multiply(scmInvSaleIssueBillEntry.getTaxAmt()));
						
						if (costCenterTypeMap.containsKey(scmInvSaleIssueBillEntry.getOutCostOrgUnitNo())) {
							if (StringUtils.equals("1", costCenterTypeMap.get(scmInvSaleIssueBillEntry.getOutCostOrgUnitNo()))) {
								// 以领代耗时也需写出库数
								scmCostItemInAndOutSum.setOutQty(sideFlag.multiply(scmInvSaleIssueBillEntry.getQty()));
								scmCostItemInAndOutSum.setOutAmt(sideFlag.multiply(scmInvSaleIssueBillEntry.getAmt()));
								scmCostItemInAndOutSum.setOutAvgPrice(
										BigDecimal.ZERO.compareTo(scmCostItemInAndOutSum.getOutQty()) == 0 ? BigDecimal.ZERO
												: scmCostItemInAndOutSum.getOutAmt().divide(
														scmCostItemInAndOutSum.getOutQty(), 2, BigDecimal.ROUND_HALF_UP));
								scmCostItemInAndOutSum.setOutTax(sideFlag.multiply(scmInvSaleIssueBillEntry.getTaxAmt()
										.subtract(scmInvSaleIssueBillEntry.getAmt())));
								scmCostItemInAndOutSum
										.setOutTaxAmt(sideFlag.multiply(scmInvSaleIssueBillEntry.getTaxAmt()));
							}
						}
						scmCostItemInAndOutSumList.add(scmCostItemInAndOutSum);
					}
				}
			}
			long endTimeInvSale2 = System.currentTimeMillis();
			log.info("销售出库明细加工耗时："+(endTimeInvSale2-endTimeInvSale)+"毫秒");
		}
		
		long beginTimeTransfer = System.currentTimeMillis();
		// 获取成本转移数据
		page = new Page();
		page.setModelClass(ScmInvMoveBillEntry2.class);
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
					.put(ClassUtils.getFinalModelSimpleName(ScmInvMoveBillEntry2.class) + "."
							+ ScmInvMoveBillEntry2.FN_ITEMID, new QueryParam(
									ClassUtils.getFinalModelSimpleName(ScmInvMoveBillEntry2.class) + "."
											+ ScmInvMoveBillEntry2.FN_ITEMID,
									QueryParam.QUERY_IN, sbMaterila.toString()));
		}
		page.getParamOr()
				.put(ClassUtils.getFinalModelSimpleName(ScmInvMoveBill.class) + "." + ScmInvMoveBill.FN_OUTCSTORGUNITNO,
						new QueryParam(
								ClassUtils.getFinalModelSimpleName(ScmInvMoveBill.class) + "."
										+ ScmInvMoveBill.FN_OUTCSTORGUNITNO,
								QueryParam.QUERY_IN, costOrgUnitNos.toString()));
		page.getParamOr()
				.put(ClassUtils.getFinalModelSimpleName(ScmInvMoveBill.class) + "." + ScmInvMoveBill.FN_INCSTORGUNITNO,
						new QueryParam(
								ClassUtils.getFinalModelSimpleName(ScmInvMoveBill.class) + "."
										+ ScmInvMoveBill.FN_INCSTORGUNITNO,
								QueryParam.QUERY_IN, costOrgUnitNos.toString()));
		page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvMoveBill.class) + "." + ScmInvMoveBill.FN_BIZDATE,
				new QueryParam(
						ClassUtils.getFinalModelSimpleName(ScmInvMoveBill.class) + "." + ScmInvMoveBill.FN_BIZDATE,
						QueryParam.QUERY_BETWEEN, FormatUtils.fmtDate(periodCalendar.getStartDate()), endDate));
		if (!StringUtils.equals("Y", status)) {
			page.getParam().put(
					ClassUtils.getFinalModelSimpleName(ScmInvMoveBill.class) + "." + ScmInvMoveBill.FN_STATUS,
					new QueryParam(
							ClassUtils.getFinalModelSimpleName(ScmInvMoveBill.class) + "." + ScmInvMoveBill.FN_STATUS,
							QueryParam.QUERY_EQ, "E"));
		}
		List<ScmInvMoveBillEntry2> scmInvMoveBillEntryList = scmInvMoveBillEntryBiz.queryPage(page, arglist,
				"findAllPage", param);
		long dataEndTimeTransfer = System.currentTimeMillis();
		log.info("成本转移明细查询耗时："+(dataEndTimeTransfer-beginTimeTransfer)+"毫秒");
		if (scmInvMoveBillEntryList != null && !scmInvMoveBillEntryList.isEmpty()) {
			log.info("成本转移明细查询结果："+(scmInvMoveBillEntryList.size())+"条记录");
			for (ScmInvMoveBillEntry2 scmInvMoveBillEntry : scmInvMoveBillEntryList) {
				if (scmInvMoveBillEntry.getBizDate().compareTo(FormatUtils.parseDate(beginDate)) < 0) {
					// 比查询日期小的统计到期初数据中
					if (StringUtils.contains(costOrgUnitNos.toString(), scmInvMoveBillEntry.getOutCstOrgUnitNo())) {
						// 调出
						boolean exists = false;
						if (!scmCostItemInAndOutSumList.isEmpty()) {
							for (ScmCostItemInAndOutSum existScmCostItemInAndOutSum : scmCostItemInAndOutSumList) {
								if (existScmCostItemInAndOutSum.getItemId() == scmInvMoveBillEntry.getItemId()
										&& StringUtils.equals(existScmCostItemInAndOutSum.getCostOrgUnitNo(),
												scmInvMoveBillEntry.getOutCstOrgUnitNo())) {
									existScmCostItemInAndOutSum.setInitQty(existScmCostItemInAndOutSum.getInitQty()
											.subtract(scmInvMoveBillEntry.getQty()));
									existScmCostItemInAndOutSum.setInitAmt(existScmCostItemInAndOutSum.getInitAmt()
											.subtract(scmInvMoveBillEntry.getAmt()));
									existScmCostItemInAndOutSum.setInitAvgPrice(
											BigDecimal.ZERO.compareTo(existScmCostItemInAndOutSum.getInitQty()) == 0
													? BigDecimal.ZERO
													: existScmCostItemInAndOutSum.getInitAmt().divide(
															existScmCostItemInAndOutSum.getInitQty(), 2,
															BigDecimal.ROUND_HALF_UP));
									existScmCostItemInAndOutSum.setInitTax(
											existScmCostItemInAndOutSum.getInitTax().subtract(scmInvMoveBillEntry
													.getTaxAmt().subtract(scmInvMoveBillEntry.getAmt())));
									existScmCostItemInAndOutSum.setInitTaxAmt(existScmCostItemInAndOutSum
											.getInitTaxAmt().subtract(scmInvMoveBillEntry.getTaxAmt()));
									exists = true;
									break;
								}
							}
						}
						if (!exists) {
							ScmCostItemInAndOutSum scmCostItemInAndOutSum = new ScmCostItemInAndOutSum(true);
							scmCostItemInAndOutSum.setItemId(scmInvMoveBillEntry.getItemId());
							scmCostItemInAndOutSum.setItemNo(scmInvMoveBillEntry.getItemNo());
							scmCostItemInAndOutSum.setItemName(scmInvMoveBillEntry.getItemName());
							scmCostItemInAndOutSum.setSpec(scmInvMoveBillEntry.getSpec());
							scmCostItemInAndOutSum.setGroupId(scmInvMoveBillEntry.getGroupId());
							scmCostItemInAndOutSum.setClassCode(scmInvMoveBillEntry.getGroupCode());
							scmCostItemInAndOutSum.setClassName(scmInvMoveBillEntry.getGroupName());
							scmCostItemInAndOutSum.setLongNo(scmInvMoveBillEntry.getLongNo());
							scmCostItemInAndOutSum.setUnitName(scmInvMoveBillEntry.getUnitName());
							scmCostItemInAndOutSum.setUnit(scmInvMoveBillEntry.getUnit());
							scmCostItemInAndOutSum.setCostOrgUnitNo(scmInvMoveBillEntry.getOutCstOrgUnitNo());
							scmCostItemInAndOutSum.setInitQty(BigDecimal.ZERO.subtract(scmInvMoveBillEntry.getQty()));
							scmCostItemInAndOutSum.setInitAmt(BigDecimal.ZERO.subtract(scmInvMoveBillEntry.getAmt()));
							scmCostItemInAndOutSum
									.setInitAvgPrice(
											BigDecimal.ZERO.compareTo(scmCostItemInAndOutSum.getInitQty()) == 0
													? BigDecimal.ZERO
													: scmCostItemInAndOutSum.getInitAmt().divide(
															scmCostItemInAndOutSum.getInitQty(), 2,
															BigDecimal.ROUND_HALF_UP));
							scmCostItemInAndOutSum.setInitTax(BigDecimal.ZERO
									.subtract(scmInvMoveBillEntry.getTaxAmt().subtract(scmInvMoveBillEntry.getAmt())));
							scmCostItemInAndOutSum
									.setInitTaxAmt(BigDecimal.ZERO.subtract(scmInvMoveBillEntry.getTaxAmt()));
							scmCostItemInAndOutSumList.add(scmCostItemInAndOutSum);
						}
					}
					if (StringUtils.contains(costOrgUnitNos.toString(), scmInvMoveBillEntry.getInCstOrgUnitNo())) {
						if (StringUtils.contains(costOrgUnitNos.toString(), scmInvMoveBillEntry.getInCstOrgUnitNo())) {
							if (StringUtils.equals("1",costCenterTypeMap.get(scmInvMoveBillEntry.getInCstOrgUnitNo()))) {
								continue;
							}
						}
						
//						OrgCostCenter2 orgCostCenter = orgCostCenterBiz
//								.selectByOrgUnitNo(scmInvMoveBillEntry.getInCstOrgUnitNo(), param);
//						
//						if (orgCostCenter != null && StringUtils.equals("1", orgCostCenter.getCostCenterType())) {
//							continue;
//						}
						// 调入
						boolean exists = false;
						if (!scmCostItemInAndOutSumList.isEmpty()) {
							for (ScmCostItemInAndOutSum existScmCostItemInAndOutSum : scmCostItemInAndOutSumList) {
								if (existScmCostItemInAndOutSum.getItemId() == scmInvMoveBillEntry.getItemId()
										&& StringUtils.equals(existScmCostItemInAndOutSum.getCostOrgUnitNo(),
												scmInvMoveBillEntry.getInCstOrgUnitNo())) {
									existScmCostItemInAndOutSum.setInitQty(
											existScmCostItemInAndOutSum.getInitQty().add(scmInvMoveBillEntry.getQty()));
									existScmCostItemInAndOutSum.setInitAmt(
											existScmCostItemInAndOutSum.getInitAmt().add(scmInvMoveBillEntry.getAmt()));
									existScmCostItemInAndOutSum.setInitAvgPrice(
											BigDecimal.ZERO.compareTo(existScmCostItemInAndOutSum.getInitQty()) == 0
													? BigDecimal.ZERO
													: existScmCostItemInAndOutSum.getInitAmt().divide(
															existScmCostItemInAndOutSum.getInitQty(), 2,
															BigDecimal.ROUND_HALF_UP));
									existScmCostItemInAndOutSum.setInitTax(existScmCostItemInAndOutSum.getInitTax().add(
											scmInvMoveBillEntry.getTaxAmt().subtract(scmInvMoveBillEntry.getAmt())));
									existScmCostItemInAndOutSum.setInitTaxAmt(existScmCostItemInAndOutSum
											.getInitTaxAmt().add(scmInvMoveBillEntry.getTaxAmt()));
									exists = true;
									break;
								}
							}
						}
						if (!exists) {
							ScmCostItemInAndOutSum scmCostItemInAndOutSum = new ScmCostItemInAndOutSum(true);
							scmCostItemInAndOutSum.setItemId(scmInvMoveBillEntry.getItemId());
							scmCostItemInAndOutSum.setItemNo(scmInvMoveBillEntry.getItemNo());
							scmCostItemInAndOutSum.setItemName(scmInvMoveBillEntry.getItemName());
							scmCostItemInAndOutSum.setSpec(scmInvMoveBillEntry.getSpec());
							scmCostItemInAndOutSum.setGroupId(scmInvMoveBillEntry.getGroupId());
							scmCostItemInAndOutSum.setClassCode(scmInvMoveBillEntry.getGroupCode());
							scmCostItemInAndOutSum.setClassName(scmInvMoveBillEntry.getGroupName());
							scmCostItemInAndOutSum.setLongNo(scmInvMoveBillEntry.getLongNo());
							scmCostItemInAndOutSum.setUnitName(scmInvMoveBillEntry.getUnitName());
							scmCostItemInAndOutSum.setUnit(scmInvMoveBillEntry.getUnit());
							scmCostItemInAndOutSum.setCostOrgUnitNo(scmInvMoveBillEntry.getInCstOrgUnitNo());
							scmCostItemInAndOutSum.setInitQty(scmInvMoveBillEntry.getQty());
							scmCostItemInAndOutSum.setInitAmt(scmInvMoveBillEntry.getAmt());
							scmCostItemInAndOutSum
									.setInitAvgPrice(
											BigDecimal.ZERO.compareTo(scmCostItemInAndOutSum.getInitQty()) == 0
													? BigDecimal.ZERO
													: scmCostItemInAndOutSum.getInitAmt().divide(
															scmCostItemInAndOutSum.getInitQty(), 2,
															BigDecimal.ROUND_HALF_UP));
							scmCostItemInAndOutSum
									.setInitTax(scmInvMoveBillEntry.getTaxAmt().subtract(scmInvMoveBillEntry.getAmt()));
							scmCostItemInAndOutSum.setInitTaxAmt(scmInvMoveBillEntry.getTaxAmt());
							scmCostItemInAndOutSumList.add(scmCostItemInAndOutSum);
						}
					}
				} else {
					// 放入明细记录结果集中
					if (StringUtils.contains(costOrgUnitNos.toString(), scmInvMoveBillEntry.getOutCstOrgUnitNo())) {
						boolean exists = false;
						if (!scmCostItemInAndOutSumList.isEmpty()) {
							for (ScmCostItemInAndOutSum existScmCostItemInAndOutSum : scmCostItemInAndOutSumList) {
								if (existScmCostItemInAndOutSum.getItemId() == scmInvMoveBillEntry.getItemId()
										&& StringUtils.equals(existScmCostItemInAndOutSum.getCostOrgUnitNo(),
												scmInvMoveBillEntry.getOutCstOrgUnitNo())) {
									existScmCostItemInAndOutSum.setOutQty(
											existScmCostItemInAndOutSum.getOutQty().add(scmInvMoveBillEntry.getQty()));
									existScmCostItemInAndOutSum.setOutAmt(
											existScmCostItemInAndOutSum.getOutAmt().add(scmInvMoveBillEntry.getAmt()));
									existScmCostItemInAndOutSum.setAllotOutQty(existScmCostItemInAndOutSum
											.getAllotOutQty().add(scmInvMoveBillEntry.getQty()));
									existScmCostItemInAndOutSum.setAllotOutAvgPrice(
											BigDecimal.ZERO.compareTo(existScmCostItemInAndOutSum
													.getAllotOutQty().add(scmInvMoveBillEntry.getQty())) == 0
													? BigDecimal.ZERO
													: existScmCostItemInAndOutSum
													.getAllotOutAmt().add(scmInvMoveBillEntry.getAmt()).divide(
															existScmCostItemInAndOutSum
															.getAllotOutQty().add(scmInvMoveBillEntry.getQty()), 2,
															BigDecimal.ROUND_HALF_UP));
									existScmCostItemInAndOutSum.setAllotOutAmt(existScmCostItemInAndOutSum
											.getAllotOutAmt().add(scmInvMoveBillEntry.getAmt()));
									existScmCostItemInAndOutSum.setAllotOutTax(
											existScmCostItemInAndOutSum.getAllotOutTax().add(scmInvMoveBillEntry
													.getTaxAmt().subtract(scmInvMoveBillEntry.getAmt())));
									existScmCostItemInAndOutSum.setAllotOutTaxAmt(existScmCostItemInAndOutSum
											.getAllotOutTaxAmt().add(scmInvMoveBillEntry.getTaxAmt()));
									existScmCostItemInAndOutSum.setOutAvgPrice(
											BigDecimal.ZERO.compareTo(existScmCostItemInAndOutSum.getOutQty()) == 0
													? BigDecimal.ZERO
													: existScmCostItemInAndOutSum.getOutAmt().divide(
															existScmCostItemInAndOutSum.getOutQty(), 2,
															BigDecimal.ROUND_HALF_UP));
									existScmCostItemInAndOutSum.setOutTax(existScmCostItemInAndOutSum.getOutTax().add(
											scmInvMoveBillEntry.getTaxAmt().subtract(scmInvMoveBillEntry.getAmt())));
									existScmCostItemInAndOutSum.setOutTaxAmt(existScmCostItemInAndOutSum.getOutTaxAmt()
											.add(scmInvMoveBillEntry.getTaxAmt()));
									exists = true;
									break;
								}
							}
						}
						if (!exists) {
							ScmCostItemInAndOutSum scmCostItemInAndOutSum = new ScmCostItemInAndOutSum(true);
							scmCostItemInAndOutSum.setItemId(scmInvMoveBillEntry.getItemId());
							scmCostItemInAndOutSum.setItemNo(scmInvMoveBillEntry.getItemNo());
							scmCostItemInAndOutSum.setItemName(scmInvMoveBillEntry.getItemName());
							scmCostItemInAndOutSum.setSpec(scmInvMoveBillEntry.getSpec());
							scmCostItemInAndOutSum.setGroupId(scmInvMoveBillEntry.getGroupId());
							scmCostItemInAndOutSum.setClassCode(scmInvMoveBillEntry.getGroupCode());
							scmCostItemInAndOutSum.setClassName(scmInvMoveBillEntry.getGroupName());
							scmCostItemInAndOutSum.setLongNo(scmInvMoveBillEntry.getLongNo());
							scmCostItemInAndOutSum.setUnitName(scmInvMoveBillEntry.getUnitName());
							scmCostItemInAndOutSum.setUnit(scmInvMoveBillEntry.getUnit());
							scmCostItemInAndOutSum.setCostOrgUnitNo(scmInvMoveBillEntry.getOutCstOrgUnitNo());
							scmCostItemInAndOutSum.setOutQty(scmInvMoveBillEntry.getQty());
							scmCostItemInAndOutSum.setOutAmt(scmInvMoveBillEntry.getAmt());
							scmCostItemInAndOutSum.setAllotOutQty(scmInvMoveBillEntry.getQty());
							scmCostItemInAndOutSum.setOutAvgPrice(
									BigDecimal.ZERO.compareTo(scmInvMoveBillEntry.getQty()) == 0 ? BigDecimal.ZERO
											: scmInvMoveBillEntry.getAmt().divide(
													scmInvMoveBillEntry.getQty(), 2, BigDecimal.ROUND_HALF_UP));
							scmCostItemInAndOutSum.setAllotOutAmt(scmInvMoveBillEntry.getAmt());
							scmCostItemInAndOutSum.setAllotOutTax(scmCostItemInAndOutSum.getAllotOutTaxAmt().add((scmInvMoveBillEntry.getTaxAmt())));
							scmCostItemInAndOutSum.setAllotOutTaxAmt(scmInvMoveBillEntry.getTaxAmt());

							scmCostItemInAndOutSum.setOutAvgPrice(
									BigDecimal.ZERO.compareTo(scmCostItemInAndOutSum.getOutQty()) == 0 ? BigDecimal.ZERO
											: scmCostItemInAndOutSum.getOutAmt().divide(
													scmCostItemInAndOutSum.getOutQty(), 2, BigDecimal.ROUND_HALF_UP));
							scmCostItemInAndOutSum
									.setOutTax(scmInvMoveBillEntry.getTaxAmt().subtract(scmInvMoveBillEntry.getAmt()));
							scmCostItemInAndOutSum.setOutTaxAmt(scmInvMoveBillEntry.getTaxAmt());
							scmCostItemInAndOutSumList.add(scmCostItemInAndOutSum);
						}
					}
					if (StringUtils.contains(costOrgUnitNos.toString(), scmInvMoveBillEntry.getInCstOrgUnitNo())) {
//						OrgCostCenter2 orgCostCenter = orgCostCenterBiz
//								.selectByOrgUnitNo(scmInvMoveBillEntry.getInCstOrgUnitNo(), param);
						boolean exists = false;
						if (!scmCostItemInAndOutSumList.isEmpty()) {
							for (ScmCostItemInAndOutSum existScmCostItemInAndOutSum : scmCostItemInAndOutSumList) {
								if (existScmCostItemInAndOutSum.getItemId() == scmInvMoveBillEntry.getItemId()
										&& StringUtils.equals(existScmCostItemInAndOutSum.getCostOrgUnitNo(),
												scmInvMoveBillEntry.getInCstOrgUnitNo())) {
									existScmCostItemInAndOutSum.setAddInQty(existScmCostItemInAndOutSum.getAddInQty()
											.add(scmInvMoveBillEntry.getQty()));
									existScmCostItemInAndOutSum.setAddInAmt(existScmCostItemInAndOutSum.getAddInAmt()
											.add(scmInvMoveBillEntry.getAmt()));
									existScmCostItemInAndOutSum.setAllotInQty(existScmCostItemInAndOutSum
											.getAllotInQty().add((scmInvMoveBillEntry.getQty())));
									existScmCostItemInAndOutSum.setAddInAvgPrice(
											BigDecimal.ZERO.compareTo(existScmCostItemInAndOutSum
													.getAllotInQty().add((scmInvMoveBillEntry.getQty()))) == 0
													? BigDecimal.ZERO
													: existScmCostItemInAndOutSum
													.getAllotInAmt().add((scmInvMoveBillEntry.getAmt())).divide(
															existScmCostItemInAndOutSum
															.getAllotInQty().add((scmInvMoveBillEntry.getQty())), 2,
															BigDecimal.ROUND_HALF_UP));
									existScmCostItemInAndOutSum.setAllotInAmt(existScmCostItemInAndOutSum
											.getAllotInAmt().add((scmInvMoveBillEntry.getAmt())));
									existScmCostItemInAndOutSum.setAllotInTax(
											existScmCostItemInAndOutSum.getAllotInTax().add((scmInvMoveBillEntry
													.getTaxAmt().subtract(scmInvMoveBillEntry.getAmt()))));
									existScmCostItemInAndOutSum.setAllotInTaxAmt(existScmCostItemInAndOutSum
											.getAllotInTaxAmt().add((scmInvMoveBillEntry.getTaxAmt())));
									existScmCostItemInAndOutSum.setAddInAvgPrice(
											BigDecimal.ZERO.compareTo(existScmCostItemInAndOutSum.getAddInQty()) == 0
													? BigDecimal.ZERO
													: existScmCostItemInAndOutSum.getAddInAmt().divide(
															existScmCostItemInAndOutSum.getAddInQty(), 2,
															BigDecimal.ROUND_HALF_UP));
									existScmCostItemInAndOutSum.setAddInTax(
											existScmCostItemInAndOutSum.getAddInTax().add(scmInvMoveBillEntry
													.getTaxAmt().subtract(scmInvMoveBillEntry.getAmt())));
									existScmCostItemInAndOutSum.setAddInTaxAmt(existScmCostItemInAndOutSum
											.getAddInTaxAmt().add(scmInvMoveBillEntry.getTaxAmt()));
									if (costCenterTypeMap.containsKey(scmInvMoveBillEntry.getInCstOrgUnitNo())) {
										if (StringUtils.equals("1",costCenterTypeMap.get(scmInvMoveBillEntry.getInCstOrgUnitNo()))) {
//										if (orgCostCenter != null
//												&& StringUtils.equals("1", orgCostCenter.getCostCenterType())) {
											existScmCostItemInAndOutSum.setOutQty(existScmCostItemInAndOutSum.getOutQty()
													.add(scmInvMoveBillEntry.getQty()));
											existScmCostItemInAndOutSum.setOutAmt(existScmCostItemInAndOutSum.getOutAmt()
													.add(scmInvMoveBillEntry.getAmt()));
											existScmCostItemInAndOutSum.setOutAvgPrice(
													BigDecimal.ZERO.compareTo(existScmCostItemInAndOutSum.getOutQty()) == 0
															? BigDecimal.ZERO
															: existScmCostItemInAndOutSum.getOutAmt().divide(
																	existScmCostItemInAndOutSum.getOutQty(), 2,
																	BigDecimal.ROUND_HALF_UP));
											existScmCostItemInAndOutSum.setOutTax(
													existScmCostItemInAndOutSum.getOutTax().add(scmInvMoveBillEntry
															.getTaxAmt().subtract(scmInvMoveBillEntry.getAmt())));
											existScmCostItemInAndOutSum.setOutTaxAmt(existScmCostItemInAndOutSum
													.getOutTaxAmt().add(scmInvMoveBillEntry.getTaxAmt()));
										}
									}
									exists = true;
									break;
								}
							}
						}
						if (!exists) {
							ScmCostItemInAndOutSum scmCostItemInAndOutSum = new ScmCostItemInAndOutSum(true);
							scmCostItemInAndOutSum.setItemId(scmInvMoveBillEntry.getItemId());
							scmCostItemInAndOutSum.setItemNo(scmInvMoveBillEntry.getItemNo());
							scmCostItemInAndOutSum.setItemName(scmInvMoveBillEntry.getItemName());
							scmCostItemInAndOutSum.setSpec(scmInvMoveBillEntry.getSpec());
							scmCostItemInAndOutSum.setGroupId(scmInvMoveBillEntry.getGroupId());
							scmCostItemInAndOutSum.setClassCode(scmInvMoveBillEntry.getGroupCode());
							scmCostItemInAndOutSum.setClassName(scmInvMoveBillEntry.getGroupName());
							scmCostItemInAndOutSum.setLongNo(scmInvMoveBillEntry.getLongNo());
							scmCostItemInAndOutSum.setUnitName(scmInvMoveBillEntry.getUnitName());
							scmCostItemInAndOutSum.setUnit(scmInvMoveBillEntry.getUnit());
							scmCostItemInAndOutSum.setCostOrgUnitNo(scmInvMoveBillEntry.getInCstOrgUnitNo());
							scmCostItemInAndOutSum.setAddInQty(scmInvMoveBillEntry.getQty());
							scmCostItemInAndOutSum.setAddInAmt(scmInvMoveBillEntry.getAmt());
							scmCostItemInAndOutSum.setAllotInQty(scmInvMoveBillEntry.getQty());
							scmCostItemInAndOutSum
							.setAddInAvgPrice(
									BigDecimal.ZERO.compareTo(scmInvMoveBillEntry.getQty()) == 0
											? BigDecimal.ZERO
											: scmInvMoveBillEntry.getAmt().divide(
													scmInvMoveBillEntry.getQty(), 2,
													BigDecimal.ROUND_HALF_UP));
							scmCostItemInAndOutSum.setAllotInAmt(scmInvMoveBillEntry.getAmt());
							scmCostItemInAndOutSum.setAllotInTax(
									scmInvMoveBillEntry.getTaxAmt().subtract(scmInvMoveBillEntry.getAmt()));
							scmCostItemInAndOutSum.setAllotInTaxAmt(scmInvMoveBillEntry.getTaxAmt());

							scmCostItemInAndOutSum
									.setAddInAvgPrice(
											BigDecimal.ZERO.compareTo(scmCostItemInAndOutSum.getAddInQty()) == 0
													? BigDecimal.ZERO
													: scmCostItemInAndOutSum.getAddInAmt().divide(
															scmCostItemInAndOutSum.getAddInQty(), 2,
															BigDecimal.ROUND_HALF_UP));
							scmCostItemInAndOutSum.setAddInTax(
									scmInvMoveBillEntry.getTaxAmt().subtract(scmInvMoveBillEntry.getAmt()));
							scmCostItemInAndOutSum.setAddInTaxAmt(scmInvMoveBillEntry.getTaxAmt());
							if (costCenterTypeMap.containsKey(scmInvMoveBillEntry.getInCstOrgUnitNo())) {
								if (StringUtils.equals("1",costCenterTypeMap.get(scmInvMoveBillEntry.getInCstOrgUnitNo()))) {
									scmCostItemInAndOutSum.setOutQty(scmInvMoveBillEntry.getQty());
									scmCostItemInAndOutSum.setOutAmt(scmInvMoveBillEntry.getAmt());
									scmCostItemInAndOutSum.setOutAvgPrice(
											BigDecimal.ZERO.compareTo(scmCostItemInAndOutSum.getOutQty()) == 0
													? BigDecimal.ZERO
													: scmCostItemInAndOutSum.getOutAmt().divide(
															scmCostItemInAndOutSum.getOutQty(), 2,
															BigDecimal.ROUND_HALF_UP));
									scmCostItemInAndOutSum.setOutTax(
											scmInvMoveBillEntry.getTaxAmt().subtract(scmInvMoveBillEntry.getAmt()));
									scmCostItemInAndOutSum.setOutTaxAmt(scmInvMoveBillEntry.getTaxAmt());
								}
							}
							scmCostItemInAndOutSumList.add(scmCostItemInAndOutSum);
						}
					}
				}
			}
				long dataEndTimeTransfer1 = System.currentTimeMillis();
				log.info("成本转移明细加工耗时："+(dataEndTimeTransfer1-dataEndTimeTransfer)+"毫秒");
			
			
		}
		 long beginTimeBreakage = System.currentTimeMillis();
		// 获取报损单数据
		page = new Page();
		page.setModelClass(ScmCstFrmLossEntry2.class);
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
					.put(ClassUtils.getFinalModelSimpleName(ScmCstFrmLossEntry2.class) + "."
							+ ScmCstFrmLossEntry2.FN_ITEMID, new QueryParam(
									ClassUtils.getFinalModelSimpleName(ScmCstFrmLossEntry2.class) + "."
											+ ScmCstFrmLossEntry2.FN_ITEMID,
									QueryParam.QUERY_IN, sbMaterila.toString()));
		}
		page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmCstFrmLoss.class) + "." + ScmCstFrmLoss.FN_COSTORGUNITNO,
				new QueryParam(
						ClassUtils.getFinalModelSimpleName(ScmCstFrmLoss.class) + "." + ScmCstFrmLoss.FN_COSTORGUNITNO,
						QueryParam.QUERY_IN, costOrgUnitNos.toString()));
		page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmCstFrmLoss.class) + "." + ScmCstFrmLoss.FN_BIZDATE,
				new QueryParam(ClassUtils.getFinalModelSimpleName(ScmCstFrmLoss.class) + "." + ScmCstFrmLoss.FN_BIZDATE,
						QueryParam.QUERY_BETWEEN, FormatUtils.fmtDate(periodCalendar.getStartDate()), endDate));
		if (!StringUtils.equals("Y", status)) {
			page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmCstFrmLoss.class) + "." + ScmCstFrmLoss.FN_STATUS,
					new QueryParam(
							ClassUtils.getFinalModelSimpleName(ScmCstFrmLoss.class) + "." + ScmCstFrmLoss.FN_STATUS,
							QueryParam.QUERY_EQ, "E"));
		}
		List<ScmCstFrmLossEntry2> scmCstFrmLossEntryList = scmCstFrmLossEntryBiz.queryPage(page, arglist, "findAllPage",
				param);
		long dataEndTimeBreakage = System.currentTimeMillis();
		log.info("成本中心报损明细查询耗时："+(dataEndTimeBreakage-beginTimeBreakage)+"毫秒");
		if (scmCstFrmLossEntryList != null && !scmCstFrmLossEntryList.isEmpty()) {
			log.info("成本中心报损明细查询结果："+(scmCstFrmLossEntryList.size())+"条记录");
			for (ScmCstFrmLossEntry2 scmCstFrmLossEntry : scmCstFrmLossEntryList) {
//				OrgCostCenter2 orgCostCenter = orgCostCenterBiz.selectByOrgUnitNo(scmCstFrmLossEntry.getCostOrgUnitNo(),
//						param);
//				if (orgCostCenter != null && StringUtils.equals("1", orgCostCenter.getCostCenterType())) {
//					continue;
//				}
				if (costCenterTypeMap.containsKey(scmCstFrmLossEntry.getCostOrgUnitNo())) {
					if (StringUtils.equals("1",costCenterTypeMap.get(scmCstFrmLossEntry.getCostOrgUnitNo()))) {
						continue;
					}
				}
				if (scmCstFrmLossEntry.getBizDate().compareTo(FormatUtils.parseDate(beginDate)) < 0) {
					// 比查询日期小的统计到期初数据中，并从List中删除
					boolean exists = false;
					if (!scmCostItemInAndOutSumList.isEmpty()) {
						for (ScmCostItemInAndOutSum existScmCostItemInAndOutSum : scmCostItemInAndOutSumList) {
							if (existScmCostItemInAndOutSum.getItemId() == scmCstFrmLossEntry.getItemId()
									&& StringUtils.equals(existScmCostItemInAndOutSum.getCostOrgUnitNo(),
											scmCstFrmLossEntry.getCostOrgUnitNo())) {
								existScmCostItemInAndOutSum.setInitQty(
										existScmCostItemInAndOutSum.getInitQty().subtract(scmCstFrmLossEntry.getQty()));
								existScmCostItemInAndOutSum.setInitAmt(
										existScmCostItemInAndOutSum.getInitAmt().subtract(scmCstFrmLossEntry.getAmt()));
								existScmCostItemInAndOutSum.setInitAvgPrice(
										BigDecimal.ZERO.compareTo(existScmCostItemInAndOutSum.getInitQty()) == 0
												? BigDecimal.ZERO
												: existScmCostItemInAndOutSum.getInitAmt().divide(
														existScmCostItemInAndOutSum.getInitQty(), 2,
														BigDecimal.ROUND_HALF_UP));
								existScmCostItemInAndOutSum
										.setInitTax(existScmCostItemInAndOutSum.getInitTax().subtract(
												scmCstFrmLossEntry.getTaxAmt().subtract(scmCstFrmLossEntry.getAmt())));
								existScmCostItemInAndOutSum.setInitTaxAmt(existScmCostItemInAndOutSum.getInitTaxAmt()
										.subtract(scmCstFrmLossEntry.getTaxAmt()));
								exists = true;
								break;
							}
						}
					}
					if (!exists) {
						ScmCostItemInAndOutSum scmCostItemInAndOutSum = new ScmCostItemInAndOutSum(true);
						scmCostItemInAndOutSum.setItemId(scmCstFrmLossEntry.getItemId());
						scmCostItemInAndOutSum.setItemNo(scmCstFrmLossEntry.getItemNo());
						scmCostItemInAndOutSum.setItemName(scmCstFrmLossEntry.getItemName());
						scmCostItemInAndOutSum.setSpec(scmCstFrmLossEntry.getSpec());
						scmCostItemInAndOutSum.setGroupId(scmCstFrmLossEntry.getGroupId());
						scmCostItemInAndOutSum.setClassCode(scmCstFrmLossEntry.getGroupCode());
						scmCostItemInAndOutSum.setClassName(scmCstFrmLossEntry.getGroupName());
						scmCostItemInAndOutSum.setLongNo(scmCstFrmLossEntry.getLongNo());
						scmCostItemInAndOutSum.setUnitName(scmCstFrmLossEntry.getUnitName());
						scmCostItemInAndOutSum.setUnit(scmCstFrmLossEntry.getUnit());
						scmCostItemInAndOutSum.setCostOrgUnitNo(scmCstFrmLossEntry.getCostOrgUnitNo());
						scmCostItemInAndOutSum.setInitQty(BigDecimal.ZERO.subtract(scmCstFrmLossEntry.getQty()));
						scmCostItemInAndOutSum.setInitAmt(BigDecimal.ZERO.subtract(scmCstFrmLossEntry.getAmt()));
						scmCostItemInAndOutSum.setInitAvgPrice(
								BigDecimal.ZERO.compareTo(scmCostItemInAndOutSum.getInitQty()) == 0 ? BigDecimal.ZERO
										: scmCostItemInAndOutSum.getInitAmt().divide(
												scmCostItemInAndOutSum.getInitQty(), 2, BigDecimal.ROUND_HALF_UP));
						scmCostItemInAndOutSum.setInitTax(BigDecimal.ZERO
								.subtract(scmCstFrmLossEntry.getTaxAmt().subtract(scmCstFrmLossEntry.getAmt())));
						scmCostItemInAndOutSum.setInitTaxAmt(BigDecimal.ZERO.subtract(scmCstFrmLossEntry.getTaxAmt()));
						scmCostItemInAndOutSumList.add(scmCostItemInAndOutSum);
					}
				} else {
					boolean exists = false;
					if (!scmCostItemInAndOutSumList.isEmpty()) {
						for (ScmCostItemInAndOutSum existScmCostItemInAndOutSum : scmCostItemInAndOutSumList) {
							if (existScmCostItemInAndOutSum.getItemId() == scmCstFrmLossEntry.getItemId()
									&& StringUtils.equals(existScmCostItemInAndOutSum.getCostOrgUnitNo(),
											scmCstFrmLossEntry.getCostOrgUnitNo())) {
								existScmCostItemInAndOutSum.setOutQty(
										existScmCostItemInAndOutSum.getOutQty().add(scmCstFrmLossEntry.getQty()));
								existScmCostItemInAndOutSum.setOutAmt(
										existScmCostItemInAndOutSum.getOutAmt().add(scmCstFrmLossEntry.getAmt()));
								existScmCostItemInAndOutSum.setBreakOutQty(existScmCostItemInAndOutSum.getBreakOutQty()
										.add((scmCstFrmLossEntry.getQty())));
								existScmCostItemInAndOutSum.setBreakOutAvgPrice(
										BigDecimal.ZERO.compareTo(existScmCostItemInAndOutSum.getBreakOutQty()
												.add((scmCstFrmLossEntry.getQty()))) == 0
												? BigDecimal.ZERO
												: existScmCostItemInAndOutSum.getBreakOutAmt()
												.add((scmCstFrmLossEntry.getAmt())).divide(
														existScmCostItemInAndOutSum.getBreakOutQty()
														.add((scmCstFrmLossEntry.getQty())), 2,
														BigDecimal.ROUND_HALF_UP));
								existScmCostItemInAndOutSum.setBreakOutAmt(existScmCostItemInAndOutSum.getBreakOutAmt()
										.add((scmCstFrmLossEntry.getAmt())));
								existScmCostItemInAndOutSum.setBreakOutTax(existScmCostItemInAndOutSum.getBreakOutTax()
										.add((scmCstFrmLossEntry.getTaxAmt().subtract(scmCstFrmLossEntry.getAmt()))));
								existScmCostItemInAndOutSum.setBreakOutTaxAmt(existScmCostItemInAndOutSum
										.getBreakOutTaxAmt().add((scmCstFrmLossEntry.getTaxAmt())));
								existScmCostItemInAndOutSum.setOutAvgPrice(
										BigDecimal.ZERO.compareTo(existScmCostItemInAndOutSum.getOutQty()) == 0
												? BigDecimal.ZERO
												: existScmCostItemInAndOutSum.getOutAmt().divide(
														existScmCostItemInAndOutSum.getOutQty(), 2,
														BigDecimal.ROUND_HALF_UP));
								existScmCostItemInAndOutSum.setOutTax(existScmCostItemInAndOutSum.getOutTax()
										.add(scmCstFrmLossEntry.getTaxAmt().subtract(scmCstFrmLossEntry.getAmt())));
								existScmCostItemInAndOutSum.setOutTaxAmt(
										existScmCostItemInAndOutSum.getOutTaxAmt().add(scmCstFrmLossEntry.getTaxAmt()));
								exists = true;
								break;
							}
						}
					}
					if (!exists) {
						// 放入明细记录结果集中
						ScmCostItemInAndOutSum scmCostItemInAndOutSum = new ScmCostItemInAndOutSum(true);
						scmCostItemInAndOutSum.setItemId(scmCstFrmLossEntry.getItemId());
						scmCostItemInAndOutSum.setItemNo(scmCstFrmLossEntry.getItemNo());
						scmCostItemInAndOutSum.setItemName(scmCstFrmLossEntry.getItemName());
						scmCostItemInAndOutSum.setSpec(scmCstFrmLossEntry.getSpec());
						scmCostItemInAndOutSum.setGroupId(scmCstFrmLossEntry.getGroupId());
						scmCostItemInAndOutSum.setClassCode(scmCstFrmLossEntry.getGroupCode());
						scmCostItemInAndOutSum.setClassName(scmCstFrmLossEntry.getGroupName());
						scmCostItemInAndOutSum.setLongNo(scmCstFrmLossEntry.getLongNo());
						scmCostItemInAndOutSum.setUnitName(scmCstFrmLossEntry.getUnitName());
						scmCostItemInAndOutSum.setUnit(scmCstFrmLossEntry.getUnit());
						scmCostItemInAndOutSum.setCostOrgUnitNo(scmCstFrmLossEntry.getCostOrgUnitNo());
						scmCostItemInAndOutSum.setOutQty(scmCstFrmLossEntry.getQty());
						scmCostItemInAndOutSum.setOutAmt(scmCstFrmLossEntry.getAmt());
						scmCostItemInAndOutSum.setBreakOutQty(scmCstFrmLossEntry.getQty());
						scmCostItemInAndOutSum.setBreakOutAvgPrice(
								BigDecimal.ZERO.compareTo(scmCstFrmLossEntry.getQty()) == 0 ? BigDecimal.ZERO
										: scmCstFrmLossEntry.getAmt().divide(scmCstFrmLossEntry.getQty(),
												2, BigDecimal.ROUND_HALF_UP));
						scmCostItemInAndOutSum.setBreakOutAmt(scmCstFrmLossEntry.getAmt());
						scmCostItemInAndOutSum.setBreakOutTax(scmCstFrmLossEntry.getTaxAmt().subtract(scmCstFrmLossEntry.getAmt()));
						scmCostItemInAndOutSum.setBreakOutTaxAmt(scmCstFrmLossEntry.getTaxAmt());
					
						scmCostItemInAndOutSum.setOutAvgPrice(
								BigDecimal.ZERO.compareTo(scmCostItemInAndOutSum.getOutQty()) == 0 ? BigDecimal.ZERO
										: scmCostItemInAndOutSum.getOutAmt().divide(scmCostItemInAndOutSum.getOutQty(),
												2, BigDecimal.ROUND_HALF_UP));
						scmCostItemInAndOutSum
								.setOutTax(scmCstFrmLossEntry.getTaxAmt().subtract(scmCstFrmLossEntry.getAmt()));
						scmCostItemInAndOutSum.setOutTaxAmt(scmCstFrmLossEntry.getTaxAmt());
						scmCostItemInAndOutSumList.add(scmCostItemInAndOutSum);
					}
				}
			}
			long dataEndTimeBreakage1 = System.currentTimeMillis();
			log.info("成本中心报损明细加工耗时："+(dataEndTimeBreakage1-dataEndTimeBreakage)+"毫秒");
		}
		
		long dataEndTime1 = System.currentTimeMillis();
		if (!scmCostItemInAndOutSumList.isEmpty()) {
			HashMap<String,Object> cacheMap = new HashMap<String,Object>();
			for (ScmCostItemInAndOutSum scmCostItemInAndOutSum : scmCostItemInAndOutSumList) {
				if (StringUtils.isNotBlank(scmCostItemInAndOutSum.getCostOrgUnitNo())) {
					OrgCostCenter2 orgCostCenter = (OrgCostCenter2) cacheMap.get(ClassUtils.getFinalModelSimpleName(OrgCostCenter2.class)+"_"+scmCostItemInAndOutSum.getCostOrgUnitNo());
					if(orgCostCenter==null) {
						orgCostCenter = orgCostCenterBiz
								.selectByOrgUnitNo(scmCostItemInAndOutSum.getCostOrgUnitNo(), param);
						if (orgCostCenter != null) {
							cacheMap.put(ClassUtils.getFinalModelSimpleName(OrgCostCenter2.class)+"_"+scmCostItemInAndOutSum.getCostOrgUnitNo(), orgCostCenter);
						}
					}
					if (orgCostCenter != null) {
						scmCostItemInAndOutSum.setCostOrgUnitName(orgCostCenter.getOrgUnitName());
					} else {
						scmCostItemInAndOutSum.setCostOrgUnitName(scmCostItemInAndOutSum.getCostOrgUnitNo());
					}
				}
				if (scmCostItemInAndOutSum.getItemId() > 0) {
					if (StringUtils.isNotBlank(scmCostItemInAndOutSum.getLongNo())) {
						String[] ids = StringUtils.split(scmCostItemInAndOutSum.getLongNo(), ",");
						if (Long.valueOf(ids[0]) != scmCostItemInAndOutSum.getGroupId()) {
							ScmMaterialGroup scmMaterialGroup = (ScmMaterialGroup) cacheMap.get(ClassUtils.getFinalModelSimpleName(ScmMaterialGroup.class)+"_"+(ids[0]));
							if(scmMaterialGroup==null) {
								scmMaterialGroup = scmMaterialGroupBiz.selectDirect(Long.valueOf(ids[0]),
									param);
								if (scmMaterialGroup != null) {
									cacheMap.put(ClassUtils.getFinalModelSimpleName(ScmMaterialGroup.class)+"_"+(ids[0]), scmMaterialGroup);
								}
							}
							if (scmMaterialGroup != null){
								scmCostItemInAndOutSum.setBroadClassName(scmMaterialGroup.getClassName());
							}
						}
					}
				}
				scmCostItemInAndOutSum.setStockQty(scmCostItemInAndOutSum.getInitQty()
						.add(scmCostItemInAndOutSum.getAddInQty()).subtract(scmCostItemInAndOutSum.getOutQty()));
				scmCostItemInAndOutSum.setStockAmt(scmCostItemInAndOutSum.getInitAmt()
						.add(scmCostItemInAndOutSum.getAddInAmt()).subtract(scmCostItemInAndOutSum.getOutAmt()));
				scmCostItemInAndOutSum.setStockAvgPrice(
						BigDecimal.ZERO.compareTo(scmCostItemInAndOutSum.getStockQty()) == 0 ? BigDecimal.ZERO
								: scmCostItemInAndOutSum.getStockAmt().divide(scmCostItemInAndOutSum.getStockQty(), 2,
										BigDecimal.ROUND_HALF_UP));
				scmCostItemInAndOutSum.setStockTax(scmCostItemInAndOutSum.getInitTax()
						.add(scmCostItemInAndOutSum.getAddInTax()).subtract(scmCostItemInAndOutSum.getOutTax()));
				scmCostItemInAndOutSum.setStockTaxAmt(scmCostItemInAndOutSum.getInitTaxAmt()
						.add(scmCostItemInAndOutSum.getAddInTaxAmt()).subtract(scmCostItemInAndOutSum.getOutTaxAmt()));
			}
		}
		long dataEndTime22 = System.currentTimeMillis();
		log.info("查询结果后加工耗时："+(dataEndTime22-dataEndTime1)+"毫秒");

		// 数据排序
		String fields[] = { "costOrgUnitNo", "classCode", "itemNo" };
		String sorts[] = { "ASC", "ASC", "ASC" };
		/*long costTypedate = System.currentTimeMillis();
		if (scmCostItemInAndOutSumList!=null && scmCostItemInAndOutSumList.size()>0) {
			Page page2 = new Page();
			page2.setModelClass(ScmCostUseSet2.class);
			page2.setShowCount(Integer.MAX_VALUE);
			page2.getParam().put(ClassUtils.getFinalModelSimpleName(ScmCostUseSet2.class) + "." + ScmCostUseSet.FN_COSTORGUNITNO,
					new QueryParam(
							ClassUtils.getFinalModelSimpleName(ScmCostUseSet2.class) + "." + ScmCostUseSet.FN_COSTORGUNITNO,
							QueryParam.QUERY_IN, costOrgUnitNos.toString()));
			page2.getParam().put(ClassUtils.getFinalModelSimpleName(ScmCostUseSet2.class) + "." + ScmCostUseSet.FN_CONTROLUNITNO, 
					new QueryParam(ClassUtils.getFinalModelSimpleName(ScmCostUseSet2.class) + "." + ScmCostUseSet.FN_CONTROLUNITNO,
							QueryParam.QUERY_EQ,currentControlUnitNo));
			List<ScmCostUseSet2> scmCostUseSet2List = scmCostUseSetBiz.findPageDirect(page2, param);
			for (ScmCostUseSet2 scmCostUseSet : scmCostUseSet2List) {
				for (ScmCostItemInAndOutSum scmCostItemInAndOutSum2 : scmCostItemInAndOutSumList) {
					if (scmCostUseSet.getClassId()==scmCostItemInAndOutSum2.getGroupId() 
							&& StringUtils.equals(scmCostUseSet.getCostOrgUnitNo(), scmCostItemInAndOutSum2.getCostOrgUnitNo())) {
						scmCostItemInAndOutSum2.setCostTypeName(scmCostUseSet.getScmCostUseTypeName());
					}
				}
			}
			long costTypedate1 = System.currentTimeMillis();
			log.info("添加成本用途耗时："+(costTypedate1-costTypedate)+"毫秒");
		}*/
		scmCostItemInAndOutSumList = (List<ScmCostItemInAndOutSum>) ListSortUtil.sort(scmCostItemInAndOutSumList,
				fields, sorts);

		if (scmCostItemInAndOutSumList != null && !scmCostItemInAndOutSumList.isEmpty()) {
			log.info("报表总记录："+(scmCostItemInAndOutSumList.size())+"条数");
		}

		return scmCostItemInAndOutSumList;
	}


	@Override
	public List<ScmCostItemInAndOutDetail> selectListOfMaterials(HttpServletRequest request) throws AppException {
		List<ScmCostItemInAndOutDetail> scmCostItemInAndOutDetailList = new ArrayList<ScmCostItemInAndOutDetail>();
		String currentOrgUnitNo = request.getParameter("orgUnitNo");
		String currentControlUnitNo = request.getParameter("controlUnitNo");
		String costOrgUnitNo = request.getParameter("costOrgUnitNo");
		String beginDate = request.getParameter("beginDate");
		String endDate = request.getParameter("endDate");
		String materialName = request.getParameter("materialName");
		String status = "1".equals(request.getParameter("status")) ? "Y" : "N";
		if(StringUtils.isBlank(beginDate) || StringUtils.isBlank(endDate) || StringUtils.isBlank(materialName))
			return scmCostItemInAndOutDetailList;
		if(StringUtils.isBlank(costOrgUnitNo))
			costOrgUnitNo = currentOrgUnitNo;
		Param param = new Param();
		param.setOrgUnitNo(currentOrgUnitNo);
		param.setControlUnitNo(currentControlUnitNo);
		StringBuffer costOrgUnitNos = new StringBuffer("");
		List<OrgCostCenter2> orgCostCenterList = orgCostCenterBiz.findChild(costOrgUnitNo, param);
		if (orgCostCenterList != null && !orgCostCenterList.isEmpty()) {
			for (OrgCostCenter2 orgCostCenter : orgCostCenterList) {
				if (StringUtils.isNotBlank(costOrgUnitNos.toString()))
					costOrgUnitNos.append(",");
				costOrgUnitNos.append("'").append(orgCostCenter.getOrgUnitNo()).append("'");
			}
		} else {
			costOrgUnitNos.append("'").append(costOrgUnitNo).append("'");
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
			page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvBal.class) + "." + ScmInvBal.FN_COSTORGUNITNO,
					new QueryParam(
							ClassUtils.getFinalModelSimpleName(ScmInvBal.class) + "." + ScmInvBal.FN_COSTORGUNITNO,
							QueryParam.QUERY_IN, costOrgUnitNos.toString()));
			page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvBal.class) + "." + ScmInvBal.FN_STARTQTY,
					new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvBal.class) + "." + ScmInvBal.FN_STARTQTY,
							QueryParam.QUERY_GT, "0"));
			page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvBal.class) + "." + ScmInvBal.FN_ITEMID,
					new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvBal.class) + "." + ScmInvBal.FN_ITEMID,
							QueryParam.QUERY_EQ, materialName));
			page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvBal.class) + "." + ScmInvBal.FN_PERIODID,
					new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvBal.class) + "." + ScmInvBal.FN_PERIODID,
							QueryParam.QUERY_EQ, String.valueOf(periodCalendar.getPeriodId())));
			List<String> arglist = new ArrayList<>();
			arglist.add("controlUnitNo=" + currentControlUnitNo);
			List<ScmInvBal> scmInvBalList = scmInvBalBiz.queryPage(page, arglist, "findAllPage", param);
			if (scmInvBalList != null && !scmInvBalList.isEmpty()) {
				for (ScmInvBal scmInvBal : scmInvBalList) {
					OrgCostCenter2 orgCostCenter = orgCostCenterBiz.selectByOrgUnitNo(scmInvBal.getCostOrgUnitNo(),
							param);
//					判断成本中心的 以领代耗 返回
					if (orgCostCenter != null && StringUtils.equals("1", orgCostCenter.getCostCenterType())) {
						continue;
					}
					if (initQtyMap.containsKey(scmInvBal.getCostOrgUnitNo() + "_" + scmInvBal.getOrgUnitNo())) {
//						initQtyMap 键 CostOrgUnitNo_OrgUnitNo 值 initQty
						initQty = initQtyMap.get(scmInvBal.getCostOrgUnitNo() + "_" + scmInvBal.getOrgUnitNo());
						initQty = initQty.add(scmInvBal.getStartQty());
						initQtyMap.put(scmInvBal.getCostOrgUnitNo() + "_" + scmInvBal.getOrgUnitNo(), initQty);
					} else {
						initQtyMap.put(scmInvBal.getCostOrgUnitNo() + "_" + scmInvBal.getOrgUnitNo(),
								scmInvBal.getStartQty());
					}
					if (initAmtMap.containsKey(scmInvBal.getCostOrgUnitNo() + "_" + scmInvBal.getOrgUnitNo())) {
						initAmt = initAmtMap.get(scmInvBal.getCostOrgUnitNo() + "_" + scmInvBal.getOrgUnitNo());
						initAmt = initAmt.add(scmInvBal.getStartAmt());
						initAmtMap.put(scmInvBal.getCostOrgUnitNo() + "_" + scmInvBal.getOrgUnitNo(), initAmt);
					} else {
						initAmtMap.put(scmInvBal.getCostOrgUnitNo() + "_" + scmInvBal.getOrgUnitNo(),
								scmInvBal.getStartAmt());
					}
					if (initTaxAmtMap.containsKey(scmInvBal.getCostOrgUnitNo() + "_" + scmInvBal.getOrgUnitNo())) {
						initTaxAmt = initTaxAmtMap.get(scmInvBal.getCostOrgUnitNo() + "_" + scmInvBal.getOrgUnitNo());
						initTaxAmt = initTaxAmt.add(scmInvBal.getStartTaxAmt());
						initTaxAmtMap.put(scmInvBal.getCostOrgUnitNo() + "_" + scmInvBal.getOrgUnitNo(), initTaxAmt);
					} else {
						initTaxAmtMap.put(scmInvBal.getCostOrgUnitNo() + "_" + scmInvBal.getOrgUnitNo(),
								scmInvBal.getStartTaxAmt());
					}
				}
			}
		}
		// 获取入库直拨数据
		Page page = new Page();
		page.setModelClass(ScmInvPurInWarehsBillEntry2.class);
		page.setShowCount(Integer.MAX_VALUE);
		page.getParam()
				.put(ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBillEntry2.class) + "."
						+ ScmInvPurInWarehsBillEntry2.FN_ITEMID,
				new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBillEntry2.class) + "."
						+ ScmInvPurInWarehsBillEntry2.FN_ITEMID, QueryParam.QUERY_EQ, materialName));
		page.getParam()
				.put(ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBillEntry2.class) + "."
						+ ScmInvPurInWarehsBillEntry2.FN_COSTORGUNITNO,
				new QueryParam(
						ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBillEntry2.class) + "."
								+ ScmInvPurInWarehsBillEntry2.FN_COSTORGUNITNO,
						QueryParam.QUERY_IN, costOrgUnitNos.toString()));
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
		} else {
			page.getParam()
					.put(ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBill2.class) + "."
							+ ScmInvPurInWarehsBill2.FN_STATUS,
							new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBill2.class) + "."
									+ ScmInvPurInWarehsBill2.FN_STATUS, QueryParam.QUERY_NOTIN, "'N'"));
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
				OrgCostCenter2 orgCostCenter = orgCostCenterBiz
						.selectByOrgUnitNo(scmInvPurInWarehsBillEntry.getCostOrgUnitNo(), param);
				if (scmInvPurInWarehsBillEntry.getBizDate().compareTo(FormatUtils.parseDate(beginDate)) < 0) {
					if (orgCostCenter != null && StringUtils.equals("1", orgCostCenter.getCostCenterType())) {
						continue;
					}
					// 比查询日期小的统计到期初数据中
					if (initQtyMap.containsKey(scmInvPurInWarehsBillEntry.getCostOrgUnitNo() + "_"
							+ scmInvPurInWarehsBillEntry.getUseOrgUnitNo())) {
						initQty = initQtyMap.get(scmInvPurInWarehsBillEntry.getCostOrgUnitNo() + "_"
								+ scmInvPurInWarehsBillEntry.getUseOrgUnitNo());
						initQty = initQty.add(sideFlag.multiply(scmInvPurInWarehsBillEntry.getQty()));
						initQtyMap.put(scmInvPurInWarehsBillEntry.getCostOrgUnitNo() + "_"
								+ scmInvPurInWarehsBillEntry.getUseOrgUnitNo(), initQty);
					} else {
						initQtyMap.put(
								scmInvPurInWarehsBillEntry.getCostOrgUnitNo() + "_"
										+ scmInvPurInWarehsBillEntry.getUseOrgUnitNo(),
								sideFlag.multiply(scmInvPurInWarehsBillEntry.getQty()));
					}
					if (initAmtMap.containsKey(scmInvPurInWarehsBillEntry.getCostOrgUnitNo() + "_"
							+ scmInvPurInWarehsBillEntry.getUseOrgUnitNo())) {
						initAmt = initAmtMap.get(scmInvPurInWarehsBillEntry.getCostOrgUnitNo() + "_"
								+ scmInvPurInWarehsBillEntry.getUseOrgUnitNo());
						initAmt = initAmt.add(sideFlag.multiply(scmInvPurInWarehsBillEntry.getAmt()));
						initAmtMap.put(scmInvPurInWarehsBillEntry.getCostOrgUnitNo() + "_"
								+ scmInvPurInWarehsBillEntry.getUseOrgUnitNo(), initAmt);
					} else {
						initAmtMap.put(
								scmInvPurInWarehsBillEntry.getCostOrgUnitNo() + "_"
										+ scmInvPurInWarehsBillEntry.getUseOrgUnitNo(),
								sideFlag.multiply(scmInvPurInWarehsBillEntry.getAmt()));
					}
					if (initTaxAmtMap.containsKey(scmInvPurInWarehsBillEntry.getCostOrgUnitNo() + "_"
							+ scmInvPurInWarehsBillEntry.getUseOrgUnitNo())) {
						initTaxAmt = initTaxAmtMap.get(scmInvPurInWarehsBillEntry.getCostOrgUnitNo() + "_"
								+ scmInvPurInWarehsBillEntry.getUseOrgUnitNo());
						initTaxAmt = initTaxAmt.add(sideFlag.multiply(scmInvPurInWarehsBillEntry.getTaxAmt()));
						initTaxAmtMap.put(scmInvPurInWarehsBillEntry.getCostOrgUnitNo() + "_"
								+ scmInvPurInWarehsBillEntry.getUseOrgUnitNo(), initTaxAmt);
					} else {
						initTaxAmtMap.put(
								scmInvPurInWarehsBillEntry.getCostOrgUnitNo() + "_"
										+ scmInvPurInWarehsBillEntry.getUseOrgUnitNo(),
								sideFlag.multiply(scmInvPurInWarehsBillEntry.getTaxAmt()));
					}
				} else {
					// 放入明细记录结果集中 
//					new scmCostItemInAndOutDetail set 成本中心 useOrg CostOrgUnitName BillNo StatusName BizDate PostDate BizTypeName UseOrgUnitName UnitName VendorName
					ScmCostItemInAndOutDetail scmCostItemInAndOutDetail = new ScmCostItemInAndOutDetail(true);
					scmCostItemInAndOutDetail.setCostOrgUnitNo(scmInvPurInWarehsBillEntry.getCostOrgUnitNo());
					scmCostItemInAndOutDetail.setUseOrgUnitNo(scmInvPurInWarehsBillEntry.getUseOrgUnitNo());
					OrgBaseUnit2 orgBaseUnit = orgUnitBiz.selectbyOrgNo(scmInvPurInWarehsBillEntry.getCostOrgUnitNo(),
							param);
					if (orgBaseUnit != null) {
						scmCostItemInAndOutDetail.setCostOrgUnitName(orgBaseUnit.getOrgUnitName());
					} else {
						scmCostItemInAndOutDetail.setCostOrgUnitName(scmInvPurInWarehsBillEntry.getCostOrgUnitNo());
					}
					scmCostItemInAndOutDetail.setBillNo(scmInvPurInWarehsBillEntry.getWrNo());
					Code code = codeBiz.selectByCategoryAndCode("warehouseStatus",
							scmInvPurInWarehsBillEntry.getStatus());
					if (code != null) {
						scmCostItemInAndOutDetail.setStatusName(code.getName());
					} else {
						scmCostItemInAndOutDetail.setStatusName(scmInvPurInWarehsBillEntry.getStatus());
					}
					scmCostItemInAndOutDetail.setBizDate(scmInvPurInWarehsBillEntry.getBizDate());
					scmCostItemInAndOutDetail.setPostDate(scmInvPurInWarehsBillEntry.getPostDate());
					code = codeBiz.selectByCategoryAndCode("wareHouseBizType", scmInvPurInWarehsBillEntry.getBizType());
					if (code != null) {
						scmCostItemInAndOutDetail.setBizTypeName(code.getName());
					} else {
						scmCostItemInAndOutDetail.setBizTypeName(scmInvPurInWarehsBillEntry.getBizType());
					}
					orgBaseUnit = orgUnitBiz.selectbyOrgNo(scmInvPurInWarehsBillEntry.getUseOrgUnitNo(), param);
					if (orgBaseUnit != null) {
						scmCostItemInAndOutDetail.setUseOrgUnitName(orgBaseUnit.getOrgUnitName());
					} else {
						scmCostItemInAndOutDetail.setUseOrgUnitName(scmInvPurInWarehsBillEntry.getUseOrgUnitNo());
					}
					if (scmInvPurInWarehsBillEntry.getUnit() > 0) {
						ScmMeasureUnit scmMeasureUnit = scmMeasureUnitBiz
								.selectDirect(scmInvPurInWarehsBillEntry.getUnit(), param);
						if (scmMeasureUnit != null)
							scmCostItemInAndOutDetail.setUnitName(scmMeasureUnit.getUnitName());
					}
					scmCostItemInAndOutDetail.setLot(scmInvPurInWarehsBillEntry.getLot());
					if (scmInvPurInWarehsBillEntry.getVendorId() > 0) {
						Scmsupplier2 scmsupplier = scmsupplierBiz.selectDirect(scmInvPurInWarehsBillEntry.getVendorId(),
								param);
						if (scmsupplier != null)
							scmCostItemInAndOutDetail.setVendorName(scmsupplier.getVendorName());
					}
//					addQty 加减 qty 		purInQty 加减 qty
					scmCostItemInAndOutDetail.setAddInQty(sideFlag.multiply(scmInvPurInWarehsBillEntry.getQty()));
					scmCostItemInAndOutDetail.setAddInAmt(sideFlag.multiply(scmInvPurInWarehsBillEntry.getAmt()));
					scmCostItemInAndOutDetail.setAddInTax(sideFlag.multiply(scmInvPurInWarehsBillEntry.getTaxAmt().subtract(scmInvPurInWarehsBillEntry.getAmt())));
					scmCostItemInAndOutDetail.setAddInTaxAmt(sideFlag.multiply(scmInvPurInWarehsBillEntry.getTaxAmt()));	
					scmCostItemInAndOutDetail.setPurInQty(sideFlag.multiply(scmInvPurInWarehsBillEntry.getQty()));
					scmCostItemInAndOutDetail.setPurInAmt(sideFlag.multiply(scmInvPurInWarehsBillEntry.getAmt()));
					scmCostItemInAndOutDetail.setPurInTax(sideFlag.multiply(scmInvPurInWarehsBillEntry.getTaxAmt().subtract(scmInvPurInWarehsBillEntry.getAmt())));
					scmCostItemInAndOutDetail.setPurInTaxAmt(sideFlag.multiply(scmInvPurInWarehsBillEntry.getTaxAmt()));
//					以领代耗 outQty 加减 qty
					if (orgCostCenter != null && StringUtils.equals("1", orgCostCenter.getCostCenterType())) {
						scmCostItemInAndOutDetail.setOutQty(sideFlag.multiply(scmInvPurInWarehsBillEntry.getQty()));
						scmCostItemInAndOutDetail.setOutAmt(sideFlag.multiply(scmInvPurInWarehsBillEntry.getAmt()));
						scmCostItemInAndOutDetail.setOutTax(sideFlag.multiply(
								scmInvPurInWarehsBillEntry.getTaxAmt().subtract(scmInvPurInWarehsBillEntry.getAmt())));
						scmCostItemInAndOutDetail
								.setOutTaxAmt(sideFlag.multiply(scmInvPurInWarehsBillEntry.getTaxAmt()));
					}
					scmCostItemInAndOutDetailList.add(scmCostItemInAndOutDetail);
				}
			}
		}
		// 获取领料出库数据
		page = new Page();
		page.setModelClass(ScmInvPurInWarehsBillEntry2.class);
		page.setShowCount(Integer.MAX_VALUE);
		page.getParam()
				.put(ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBillEntry2.class) + "."
						+ ScmInvMaterialReqBillEntry2.FN_ITEMID,
				new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBillEntry2.class) + "."
						+ ScmInvMaterialReqBillEntry2.FN_ITEMID, QueryParam.QUERY_EQ, materialName));
		page.getParam()
				.put(ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBill.class) + "."
						+ ScmInvMaterialReqBill.FN_COSTORGUNITNO,
				new QueryParam(
						ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBill.class) + "."
								+ ScmInvMaterialReqBill.FN_COSTORGUNITNO,
						QueryParam.QUERY_IN, costOrgUnitNos.toString()));
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
		} else {
			page.getParam()
					.put(ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBill2.class) + "."
							+ ScmInvMaterialReqBill2.FN_STATUS,
							new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBill2.class) + "."
									+ ScmInvMaterialReqBill2.FN_STATUS, QueryParam.QUERY_NOTIN, "'N'"));
		}
		List<ScmInvMaterialReqBillEntry2> scmInvMaterialReqBillEntryList = scmInvMaterialReqBillEntryBiz.queryPage(page,
				arglist, "findAllPage", param);
		if (scmInvMaterialReqBillEntryList != null && !scmInvMaterialReqBillEntryList.isEmpty()) {
			for (ScmInvMaterialReqBillEntry2 scmInvMaterialReqBillEntry : scmInvMaterialReqBillEntryList) {
				BigDecimal sideFlag = BigDecimal.ONE;
				if (StringUtils.contains("2", scmInvMaterialReqBillEntry.getBizType())) {
					sideFlag = new BigDecimal("-1");
				}
				OrgCostCenter2 orgCostCenter = orgCostCenterBiz
						.selectByOrgUnitNo(scmInvMaterialReqBillEntry.getCostOrgUnitNo(), param);
				if (scmInvMaterialReqBillEntry.getBizDate().compareTo(FormatUtils.parseDate(beginDate)) < 0) {
//					以领代耗 不需要统计到期间初数
					if (orgCostCenter != null && StringUtils.equals("1", orgCostCenter.getCostCenterType())) {
						continue;
					}
					// 比查询日期小的统计到期初数据中，并从List中删除
					if (initQtyMap.containsKey(scmInvMaterialReqBillEntry.getCostOrgUnitNo() + "_"
							+ scmInvMaterialReqBillEntry.getUseOrgUnitNo())) {
						initQty = initQtyMap.get(scmInvMaterialReqBillEntry.getCostOrgUnitNo() + "_"
								+ scmInvMaterialReqBillEntry.getUseOrgUnitNo());
						initQty = initQty.add(sideFlag.multiply(scmInvMaterialReqBillEntry.getQty()));
						initQtyMap.put(scmInvMaterialReqBillEntry.getCostOrgUnitNo() + "_"
								+ scmInvMaterialReqBillEntry.getUseOrgUnitNo(), initQty);
					} else {
						initQtyMap.put(
								scmInvMaterialReqBillEntry.getCostOrgUnitNo() + "_"
										+ scmInvMaterialReqBillEntry.getUseOrgUnitNo(),
								sideFlag.multiply(scmInvMaterialReqBillEntry.getQty()));
					}
					if (initAmtMap.containsKey(scmInvMaterialReqBillEntry.getCostOrgUnitNo() + "_"
							+ scmInvMaterialReqBillEntry.getUseOrgUnitNo())) {
						initAmt = initAmtMap.get(scmInvMaterialReqBillEntry.getCostOrgUnitNo() + "_"
								+ scmInvMaterialReqBillEntry.getUseOrgUnitNo());
						initAmt = initAmt.add(sideFlag.multiply(scmInvMaterialReqBillEntry.getAmt()));
						initAmtMap.put(scmInvMaterialReqBillEntry.getCostOrgUnitNo() + "_"
								+ scmInvMaterialReqBillEntry.getUseOrgUnitNo(), initAmt);
					} else {
						initAmtMap.put(
								scmInvMaterialReqBillEntry.getCostOrgUnitNo() + "_"
										+ scmInvMaterialReqBillEntry.getUseOrgUnitNo(),
								sideFlag.multiply(scmInvMaterialReqBillEntry.getAmt()));
					}
					if (initTaxAmtMap.containsKey(scmInvMaterialReqBillEntry.getCostOrgUnitNo() + "_"
							+ scmInvMaterialReqBillEntry.getUseOrgUnitNo())) {
						initTaxAmt = initTaxAmtMap.get(scmInvMaterialReqBillEntry.getCostOrgUnitNo() + "_"
								+ scmInvMaterialReqBillEntry.getUseOrgUnitNo());
						initTaxAmt = initTaxAmt.add(sideFlag.multiply(scmInvMaterialReqBillEntry.getTaxAmt()));
						initTaxAmtMap.put(scmInvMaterialReqBillEntry.getCostOrgUnitNo() + "_"
								+ scmInvMaterialReqBillEntry.getUseOrgUnitNo(), initTaxAmt);
					} else {
						initTaxAmtMap.put(
								scmInvMaterialReqBillEntry.getCostOrgUnitNo() + "_"
										+ scmInvMaterialReqBillEntry.getUseOrgUnitNo(),
								sideFlag.multiply(scmInvMaterialReqBillEntry.getTaxAmt()));
					}
				} else {
					// 放入明细记录结果集中
//					set CostOrgUnitNo UseOrgUnitNo CostOrgUnitName BillNo StatusName BizDate PostDate BizTypeName UseOrgUnitName UnitName Lot
					ScmCostItemInAndOutDetail scmCostItemInAndOutDetail = new ScmCostItemInAndOutDetail(true);
					scmCostItemInAndOutDetail.setCostOrgUnitNo(scmInvMaterialReqBillEntry.getCostOrgUnitNo());
					scmCostItemInAndOutDetail.setUseOrgUnitNo(scmInvMaterialReqBillEntry.getUseOrgUnitNo());
					OrgBaseUnit2 orgBaseUnit = orgUnitBiz.selectbyOrgNo(scmInvMaterialReqBillEntry.getCostOrgUnitNo(),
							param);
					if (orgBaseUnit != null) {
						scmCostItemInAndOutDetail.setCostOrgUnitName(orgBaseUnit.getOrgUnitName());
					} else {
						scmCostItemInAndOutDetail.setCostOrgUnitName(scmInvMaterialReqBillEntry.getCostOrgUnitNo());
					}
					scmCostItemInAndOutDetail.setBillNo(scmInvMaterialReqBillEntry.getOtNo());
					Code code = codeBiz.selectByCategoryAndCode("warehouseStatus",
							scmInvMaterialReqBillEntry.getStatus());
					if (code != null) {
						scmCostItemInAndOutDetail.setStatusName(code.getName());
					} else {
						scmCostItemInAndOutDetail.setStatusName(scmInvMaterialReqBillEntry.getStatus());
					}
					scmCostItemInAndOutDetail.setBizDate(scmInvMaterialReqBillEntry.getBizDate());
					scmCostItemInAndOutDetail.setPostDate(scmInvMaterialReqBillEntry.getPostDate());
					code = codeBiz.selectByCategoryAndCode("collectWHBizType", scmInvMaterialReqBillEntry.getBizType());
					if (code != null) {
						scmCostItemInAndOutDetail.setBizTypeName(code.getName());
					} else {
						scmCostItemInAndOutDetail.setBizTypeName(scmInvMaterialReqBillEntry.getBizType());
					}
					orgBaseUnit = orgUnitBiz.selectbyOrgNo(scmInvMaterialReqBillEntry.getUseOrgUnitNo(), param);
					if (orgBaseUnit != null) {
						scmCostItemInAndOutDetail.setUseOrgUnitName(orgBaseUnit.getOrgUnitName());
					} else {
						scmCostItemInAndOutDetail.setUseOrgUnitName(scmInvMaterialReqBillEntry.getUseOrgUnitNo());
					}
					if (scmInvMaterialReqBillEntry.getUnit() > 0) {
						ScmMeasureUnit scmMeasureUnit = scmMeasureUnitBiz
								.selectDirect(scmInvMaterialReqBillEntry.getUnit(), param);
						if (scmMeasureUnit != null)
							scmCostItemInAndOutDetail.setUnitName(scmMeasureUnit.getUnitName());
					}
					scmCostItemInAndOutDetail.setLot(scmInvMaterialReqBillEntry.getLot());
//					addqty 加减 qty  receiveqty 加减 qty
					scmCostItemInAndOutDetail.setAddInQty(sideFlag.multiply(scmInvMaterialReqBillEntry.getQty()));
					scmCostItemInAndOutDetail.setAddInAmt(sideFlag.multiply(scmInvMaterialReqBillEntry.getAmt()));
					scmCostItemInAndOutDetail.setAddInTax(sideFlag.multiply(
							scmInvMaterialReqBillEntry.getTaxAmt().subtract(scmInvMaterialReqBillEntry.getAmt())));
					scmCostItemInAndOutDetail.setAddInTaxAmt(sideFlag.multiply(scmInvMaterialReqBillEntry.getTaxAmt()));
					
					scmCostItemInAndOutDetail.setReceiveInQty(sideFlag.multiply(scmInvMaterialReqBillEntry.getQty()));
					scmCostItemInAndOutDetail.setReceiveInAmt(sideFlag.multiply(scmInvMaterialReqBillEntry.getAmt()));
					scmCostItemInAndOutDetail.setReceiveInTax(sideFlag.multiply(
							scmInvMaterialReqBillEntry.getTaxAmt().subtract(scmInvMaterialReqBillEntry.getAmt())));
					scmCostItemInAndOutDetail.setReceiveInTaxAmt(sideFlag.multiply(scmInvMaterialReqBillEntry.getTaxAmt()));
					if (orgCostCenter != null && StringUtils.equals("1", orgCostCenter.getCostCenterType())) {
//						以领代耗 outqty 加减qty
						scmCostItemInAndOutDetail.setOutQty(sideFlag.multiply(scmInvMaterialReqBillEntry.getQty()));
						scmCostItemInAndOutDetail.setOutAmt(sideFlag.multiply(scmInvMaterialReqBillEntry.getAmt()));
						scmCostItemInAndOutDetail.setOutTax(sideFlag.multiply(
								scmInvMaterialReqBillEntry.getTaxAmt().subtract(scmInvMaterialReqBillEntry.getAmt())));
						scmCostItemInAndOutDetail
								.setOutTaxAmt(sideFlag.multiply(scmInvMaterialReqBillEntry.getTaxAmt()));
					}
					scmCostItemInAndOutDetailList.add(scmCostItemInAndOutDetail);
				}
			}
		}
		
		// 获取销售出库数据
		page = new Page();
		page.setModelClass(ScmInvSaleIssueBillEntry2.class);
		page.setShowCount(Integer.MAX_VALUE);
		page.getParam()
				.put(ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBillEntry2.class) + "."
						+ ScmInvSaleIssueBillEntry2.FN_ITEMID,
				new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBillEntry2.class) + "."
						+ ScmInvSaleIssueBillEntry2.FN_ITEMID, QueryParam.QUERY_EQ, materialName));
		page.getParam()
				.put(ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBillEntry2.class) + "."
						+ ScmInvSaleIssueBillEntry2.FN_OUTCOSTORGUNITNO,
				new QueryParam(
						ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBillEntry2.class) + "."
								+ ScmInvSaleIssueBillEntry2.FN_OUTCOSTORGUNITNO,
						QueryParam.QUERY_IN, costOrgUnitNos.toString()));
		page.getParam()
				.put(ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBill2.class) + "."
						+ ScmInvMaterialReqBill2.FN_BIZDATE,
				new QueryParam(
						ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBill.class) + "."
								+ ScmInvSaleIssueBill.FN_BIZDATE,
						QueryParam.QUERY_BETWEEN, FormatUtils.fmtDate(periodCalendar.getStartDate()), endDate));
		if (!StringUtils.equals("Y", status)) {
			page.getParam()
					.put(ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBill.class) + "."
							+ ScmInvSaleIssueBill.FN_STATUS,
					new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBill.class) + "."
							+ ScmInvSaleIssueBill.FN_STATUS, QueryParam.QUERY_EQ, "E"));
		} else {
			page.getParam().put(
					ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBill.class) + "." + ScmInvSaleIssueBill.FN_STATUS,
					new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBill.class) + "."
							+ ScmInvSaleIssueBill.FN_STATUS, QueryParam.QUERY_NOTIN, "'N'"));
		}
		List<ScmInvSaleIssueBillEntry2> scmInvSaleIssueBillEntryList = scmInvSaleIssueBillEntryBiz.queryPage(page,
				arglist, "findAllPage", param);
		if (scmInvSaleIssueBillEntryList != null && !scmInvSaleIssueBillEntryList.isEmpty()) {
			for (ScmInvSaleIssueBillEntry2 scmInvSaleIssueBillEntry : scmInvSaleIssueBillEntryList) {
				BigDecimal sideFlag = BigDecimal.ONE;
				if (StringUtils.contains("1,2,3", scmInvSaleIssueBillEntry.getBizType())) {
					sideFlag = new BigDecimal("-1");
				}
				OrgCostCenter2 orgCostCenter = orgCostCenterBiz
						.selectByOrgUnitNo(scmInvSaleIssueBillEntry.getOutCostOrgUnitNo(), param);
				if (scmInvSaleIssueBillEntry.getBizDate().compareTo(FormatUtils.parseDate(beginDate)) < 0) {
//							以领代耗 不需要统计到期间初数
					if (orgCostCenter != null && StringUtils.equals("1", orgCostCenter.getCostCenterType())) {
						continue;
					}
					// 比查询日期小的统计到期初数据中，并从List中删除
					if (initQtyMap.containsKey(scmInvSaleIssueBillEntry.getOutCostOrgUnitNo() + "_"
							+ scmInvSaleIssueBillEntry.getOutOrgUnitNo())) {
						initQty = initQtyMap.get(scmInvSaleIssueBillEntry.getOutCostOrgUnitNo() + "_"
								+ scmInvSaleIssueBillEntry.getOutOrgUnitNo());
						initQty = initQty.add(sideFlag.multiply(scmInvSaleIssueBillEntry.getQty()));
						initQtyMap.put(scmInvSaleIssueBillEntry.getOutCostOrgUnitNo() + "_"
								+ scmInvSaleIssueBillEntry.getOutOrgUnitNo(), initQty);
					} else {
						initQtyMap.put(
								scmInvSaleIssueBillEntry.getOutCostOrgUnitNo() + "_"
										+ scmInvSaleIssueBillEntry.getOutOrgUnitNo(),
								sideFlag.multiply(scmInvSaleIssueBillEntry.getQty()));
					}
					if (initAmtMap.containsKey(scmInvSaleIssueBillEntry.getOutCostOrgUnitNo() + "_"
							+ scmInvSaleIssueBillEntry.getOutOrgUnitNo())) {
						initAmt = initAmtMap.get(scmInvSaleIssueBillEntry.getOutCostOrgUnitNo() + "_"
								+ scmInvSaleIssueBillEntry.getOutOrgUnitNo());
						initAmt = initAmt.add(sideFlag.multiply(scmInvSaleIssueBillEntry.getAmt()));
						initAmtMap.put(scmInvSaleIssueBillEntry.getOutCostOrgUnitNo() + "_"
								+ scmInvSaleIssueBillEntry.getOutOrgUnitNo(), initAmt);
					} else {
						initAmtMap.put(
								scmInvSaleIssueBillEntry.getOutCostOrgUnitNo() + "_"
										+ scmInvSaleIssueBillEntry.getOutOrgUnitNo(),
								sideFlag.multiply(scmInvSaleIssueBillEntry.getAmt()));
					}
					if (initTaxAmtMap.containsKey(scmInvSaleIssueBillEntry.getOutCostOrgUnitNo() + "_"
							+ scmInvSaleIssueBillEntry.getOutOrgUnitNo())) {
						initTaxAmt = initTaxAmtMap.get(scmInvSaleIssueBillEntry.getOutCostOrgUnitNo() + "_"
								+ scmInvSaleIssueBillEntry.getOutOrgUnitNo());
						initTaxAmt = initTaxAmt.add(sideFlag.multiply(scmInvSaleIssueBillEntry.getTaxAmt()));
						initTaxAmtMap.put(scmInvSaleIssueBillEntry.getOutCostOrgUnitNo() + "_"
								+ scmInvSaleIssueBillEntry.getOutOrgUnitNo(), initTaxAmt);
					} else {
						initTaxAmtMap.put(
								scmInvSaleIssueBillEntry.getOutCostOrgUnitNo() + "_"
										+ scmInvSaleIssueBillEntry.getOutOrgUnitNo(),
								sideFlag.multiply(scmInvSaleIssueBillEntry.getTaxAmt()));
					}
				} else {
					// 放入明细记录结果集中
//							set CostOrgUnitNo UseOrgUnitNo CostOrgUnitName BillNo StatusName BizDate PostDate BizTypeName UseOrgUnitName UnitName Lot
					ScmCostItemInAndOutDetail scmCostItemInAndOutDetail = new ScmCostItemInAndOutDetail(true);
					scmCostItemInAndOutDetail.setCostOrgUnitNo(scmInvSaleIssueBillEntry.getOutCostOrgUnitNo());
					scmCostItemInAndOutDetail.setUseOrgUnitNo(scmInvSaleIssueBillEntry.getOutOrgUnitNo());
					OrgBaseUnit2 orgBaseUnit = orgUnitBiz.selectbyOrgNo(scmInvSaleIssueBillEntry.getOutCostOrgUnitNo(),
							param);
					if (orgBaseUnit != null) {
						scmCostItemInAndOutDetail.setCostOrgUnitName(orgBaseUnit.getOrgUnitName());
					} else {
						scmCostItemInAndOutDetail.setCostOrgUnitName(scmInvSaleIssueBillEntry.getOutCostOrgUnitNo());
					}
					scmCostItemInAndOutDetail.setBillNo(scmInvSaleIssueBillEntry.getOtNo());
					Code code = codeBiz.selectByCategoryAndCode("warehouseStatus",
							scmInvSaleIssueBillEntry.getStatus());
					if (code != null) {
						scmCostItemInAndOutDetail.setStatusName(code.getName());
					} else {
						scmCostItemInAndOutDetail.setStatusName(scmInvSaleIssueBillEntry.getStatus());
					}
					scmCostItemInAndOutDetail.setBizDate(scmInvSaleIssueBillEntry.getBizDate());
					scmCostItemInAndOutDetail.setPostDate(scmInvSaleIssueBillEntry.getPostDate());
					code = codeBiz.selectByCategoryAndCode("saleOWBizType", scmInvSaleIssueBillEntry.getBizType());
					if (code != null) {
						scmCostItemInAndOutDetail.setBizTypeName(code.getName());
					} else {
						scmCostItemInAndOutDetail.setBizTypeName(scmInvSaleIssueBillEntry.getBizType());
					}
					orgBaseUnit = orgUnitBiz.selectbyOrgNo(scmInvSaleIssueBillEntry.getOutOrgUnitNo(), param);
					if (orgBaseUnit != null) {
						scmCostItemInAndOutDetail.setUseOrgUnitName(orgBaseUnit.getOrgUnitName());
					} else {
						scmCostItemInAndOutDetail.setUseOrgUnitName(scmInvSaleIssueBillEntry.getOutOrgUnitNo());
					}
					if (scmInvSaleIssueBillEntry.getUnit() > 0) {
						ScmMeasureUnit scmMeasureUnit = scmMeasureUnitBiz
								.selectDirect(scmInvSaleIssueBillEntry.getUnit(), param);
						if (scmMeasureUnit != null)
							scmCostItemInAndOutDetail.setUnitName(scmMeasureUnit.getUnitName());
					}
					scmCostItemInAndOutDetail.setLot(scmInvSaleIssueBillEntry.getLot());
//							addqty 加减 qty  receiveqty 加减 qty
					scmCostItemInAndOutDetail.setAddInQty(sideFlag.multiply(scmInvSaleIssueBillEntry.getQty()));
					scmCostItemInAndOutDetail.setAddInAmt(sideFlag.multiply(scmInvSaleIssueBillEntry.getAmt()));
					scmCostItemInAndOutDetail.setAddInTax(sideFlag.multiply(
							scmInvSaleIssueBillEntry.getTaxAmt().subtract(scmInvSaleIssueBillEntry.getAmt())));
					scmCostItemInAndOutDetail.setAddInTaxAmt(sideFlag.multiply(scmInvSaleIssueBillEntry.getTaxAmt()));
					
					scmCostItemInAndOutDetail.setInvSaleOutQty(sideFlag.multiply(scmInvSaleIssueBillEntry.getQty()));
					scmCostItemInAndOutDetail.setInvSaleOutAmt(sideFlag.multiply(scmInvSaleIssueBillEntry.getAmt()));
					scmCostItemInAndOutDetail.setInvSaleOutTax(sideFlag.multiply(
							scmInvSaleIssueBillEntry.getTaxAmt().subtract(scmInvSaleIssueBillEntry.getAmt())));
					scmCostItemInAndOutDetail.setInvSaleOutTaxAmt(sideFlag.multiply(scmInvSaleIssueBillEntry.getTaxAmt()));
					if (orgCostCenter != null && StringUtils.equals("1", orgCostCenter.getCostCenterType())) {
//								以领代耗 outqty 加减qty
						scmCostItemInAndOutDetail.setOutQty(sideFlag.multiply(scmInvSaleIssueBillEntry.getQty()));
						scmCostItemInAndOutDetail.setOutAmt(sideFlag.multiply(scmInvSaleIssueBillEntry.getAmt()));
						scmCostItemInAndOutDetail.setOutTax(sideFlag.multiply(
								scmInvSaleIssueBillEntry.getTaxAmt().subtract(scmInvSaleIssueBillEntry.getAmt())));
						scmCostItemInAndOutDetail
								.setOutTaxAmt(sideFlag.multiply(scmInvSaleIssueBillEntry.getTaxAmt()));
					}
					scmCostItemInAndOutDetailList.add(scmCostItemInAndOutDetail);
				}
			}
		}
		
		// 获取耗用数据
		page = new Page();
		page.setModelClass(ScmInvCostConsumeEntry2.class);
		page.setShowCount(Integer.MAX_VALUE);
		page.getParam()
				.put(ClassUtils.getFinalModelSimpleName(ScmInvCostConsumeEntry2.class) + "."
						+ ScmInvCostConsumeEntry2.FN_ITEMID,
				new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvCostConsumeEntry2.class) + "."
						+ ScmInvCostConsumeEntry2.FN_ITEMID, QueryParam.QUERY_EQ, materialName));
		page.getParam()
				.put(ClassUtils.getFinalModelSimpleName(ScmInvCostConsume.class) + "." + ScmInvCostConsume.FN_ORGUNITNO,
						new QueryParam(
								ClassUtils.getFinalModelSimpleName(ScmInvCostConsume.class) + "."
										+ ScmInvCostConsume.FN_ORGUNITNO,
								QueryParam.QUERY_IN, costOrgUnitNos.toString()));
		page.getParam().put(
				ClassUtils.getFinalModelSimpleName(ScmInvCostConsume.class) + "." + ScmInvCostConsume.FN_BIZDATE,
				new QueryParam(
						ClassUtils.getFinalModelSimpleName(ScmInvCostConsume.class) + "."
								+ ScmInvCostConsume.FN_BIZDATE,
						QueryParam.QUERY_BETWEEN, FormatUtils.fmtDate(periodCalendar.getStartDate()), endDate));
		if (!StringUtils.equals("Y", status)) {
			page.getParam()
					.put(ClassUtils.getFinalModelSimpleName(ScmInvCostConsume.class) + "."
							+ ScmInvCostConsume.FN_STATUS,
					new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvCostConsume.class) + "."
							+ ScmInvCostConsume.FN_STATUS, QueryParam.QUERY_EQ, "E"));
		} else {
			page.getParam().put(
					ClassUtils.getFinalModelSimpleName(ScmInvCostConsume.class) + "." + ScmInvCostConsume.FN_STATUS,
					new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvCostConsume.class) + "."
							+ ScmInvCostConsume.FN_STATUS, QueryParam.QUERY_NOTIN, "'N'"));
		}
		List<ScmInvCostConsumeEntry2> scmInvCostConsumeEntryList = scmInvCostConsumeEntryBiz.queryPage(page, arglist,
				"findAllPage", param);
		if (scmInvCostConsumeEntryList != null && !scmInvCostConsumeEntryList.isEmpty()) {
			for (ScmInvCostConsumeEntry2 scmInvCostConsumeEntry : scmInvCostConsumeEntryList) {
				OrgCostCenter2 orgCostCenter = orgCostCenterBiz
						.selectByOrgUnitNo(scmInvCostConsumeEntry.getCostOrgUnitNo(), param);
				if (orgCostCenter != null && StringUtils.equals("1", orgCostCenter.getCostCenterType())) {
					continue;
				}
				if (scmInvCostConsumeEntry.getBizDate().compareTo(FormatUtils.parseDate(beginDate)) < 0) {
					// 比查询日期小的统计到期初数据中，并从List中删除
					if (initQtyMap.containsKey(scmInvCostConsumeEntry.getCostOrgUnitNo() + "_"
							+ scmInvCostConsumeEntry.getUseOrgUnitNo())) {
						initQty = initQtyMap.get(scmInvCostConsumeEntry.getCostOrgUnitNo() + "_"
								+ scmInvCostConsumeEntry.getUseOrgUnitNo());
						initQty = initQty.subtract(scmInvCostConsumeEntry.getQty());
						initQtyMap.put(scmInvCostConsumeEntry.getCostOrgUnitNo() + "_"
								+ scmInvCostConsumeEntry.getUseOrgUnitNo(), initQty);
					} else {
						initQtyMap.put(
								scmInvCostConsumeEntry.getCostOrgUnitNo() + "_"
										+ scmInvCostConsumeEntry.getUseOrgUnitNo(),
								BigDecimal.ZERO.subtract(scmInvCostConsumeEntry.getQty()));
					}
					if (initAmtMap.containsKey(scmInvCostConsumeEntry.getCostOrgUnitNo() + "_"
							+ scmInvCostConsumeEntry.getUseOrgUnitNo())) {
						initAmt = initAmtMap.get(scmInvCostConsumeEntry.getCostOrgUnitNo() + "_"
								+ scmInvCostConsumeEntry.getUseOrgUnitNo());
						initAmt = initAmt.subtract(scmInvCostConsumeEntry.getAmt());
						initAmtMap.put(scmInvCostConsumeEntry.getCostOrgUnitNo() + "_"
								+ scmInvCostConsumeEntry.getUseOrgUnitNo(), initAmt);
					} else {
						initAmtMap.put(
								scmInvCostConsumeEntry.getCostOrgUnitNo() + "_"
										+ scmInvCostConsumeEntry.getUseOrgUnitNo(),
								BigDecimal.ZERO.subtract(scmInvCostConsumeEntry.getAmt()));
					}
					if (initTaxAmtMap.containsKey(scmInvCostConsumeEntry.getCostOrgUnitNo() + "_"
							+ scmInvCostConsumeEntry.getUseOrgUnitNo())) {
						initTaxAmt = initTaxAmtMap.get(scmInvCostConsumeEntry.getCostOrgUnitNo() + "_"
								+ scmInvCostConsumeEntry.getUseOrgUnitNo());
						initTaxAmt = initTaxAmt.subtract(scmInvCostConsumeEntry.getTaxAmt());
						initTaxAmtMap.put(scmInvCostConsumeEntry.getCostOrgUnitNo() + "_"
								+ scmInvCostConsumeEntry.getUseOrgUnitNo(), initTaxAmt);
					} else {
						initTaxAmtMap.put(
								scmInvCostConsumeEntry.getCostOrgUnitNo() + "_"
										+ scmInvCostConsumeEntry.getUseOrgUnitNo(),
								BigDecimal.ZERO.subtract(scmInvCostConsumeEntry.getTaxAmt()));
					}
				} else {
					// 放入明细记录结果集中
					ScmCostItemInAndOutDetail scmCostItemInAndOutDetail = new ScmCostItemInAndOutDetail(true);
					scmCostItemInAndOutDetail.setCostOrgUnitNo(scmInvCostConsumeEntry.getCostOrgUnitNo());
					scmCostItemInAndOutDetail.setUseOrgUnitNo(scmInvCostConsumeEntry.getUseOrgUnitNo());
					OrgBaseUnit2 orgBaseUnit = orgUnitBiz.selectbyOrgNo(scmInvCostConsumeEntry.getCostOrgUnitNo(),
							param);
					if (orgBaseUnit != null) {
						scmCostItemInAndOutDetail.setCostOrgUnitName(orgBaseUnit.getOrgUnitName());
					} else {
						scmCostItemInAndOutDetail.setCostOrgUnitName(scmInvCostConsumeEntry.getCostOrgUnitNo());
					}
					scmCostItemInAndOutDetail.setBillNo(scmInvCostConsumeEntry.getDcNo());
					Code code = codeBiz.selectByCategoryAndCode("cstStatus", scmInvCostConsumeEntry.getStatus());
					if (code != null) {
						scmCostItemInAndOutDetail.setStatusName(code.getName());
					} else {
						scmCostItemInAndOutDetail.setStatusName(scmInvCostConsumeEntry.getStatus());
					}
					scmCostItemInAndOutDetail.setBizDate(scmInvCostConsumeEntry.getBizDate());
					scmCostItemInAndOutDetail.setPostDate(scmInvCostConsumeEntry.getPostDate());
					scmCostItemInAndOutDetail
							.setBizTypeName(Message.getMessage("CN", "iscm.ScmInvCostConsume.Consume")); // 耗用
					orgBaseUnit = orgUnitBiz.selectbyOrgNo(scmInvCostConsumeEntry.getUseOrgUnitNo(), param);
					if (orgBaseUnit != null) {
						scmCostItemInAndOutDetail.setUseOrgUnitName(orgBaseUnit.getOrgUnitName());
					} else {
						scmCostItemInAndOutDetail.setUseOrgUnitName(scmInvCostConsumeEntry.getUseOrgUnitNo());
					}
					if (scmInvCostConsumeEntry.getUnit() > 0) {
						ScmMeasureUnit scmMeasureUnit = scmMeasureUnitBiz.selectDirect(scmInvCostConsumeEntry.getUnit(),
								param);
						if (scmMeasureUnit != null)
							scmCostItemInAndOutDetail.setUnitName(scmMeasureUnit.getUnitName());
					}
					scmCostItemInAndOutDetail.setLot(scmInvCostConsumeEntry.getLot());
					scmCostItemInAndOutDetail.setOutQty(scmInvCostConsumeEntry.getQty());
					scmCostItemInAndOutDetail.setOutAmt(scmInvCostConsumeEntry.getAmt());
					scmCostItemInAndOutDetail
							.setOutTax(scmInvCostConsumeEntry.getTaxAmt().subtract(scmInvCostConsumeEntry.getAmt()));
					scmCostItemInAndOutDetail.setOutTaxAmt(scmInvCostConsumeEntry.getTaxAmt());
					
					scmCostItemInAndOutDetail.setConOutQty(scmInvCostConsumeEntry.getQty());
					scmCostItemInAndOutDetail.setConOutAmt(scmInvCostConsumeEntry.getAmt());
					scmCostItemInAndOutDetail
							.setConOutTax(scmInvCostConsumeEntry.getTaxAmt().subtract(scmInvCostConsumeEntry.getAmt()));
					scmCostItemInAndOutDetail.setConOutTaxAmt(scmInvCostConsumeEntry.getTaxAmt());
					scmCostItemInAndOutDetailList.add(scmCostItemInAndOutDetail);
				}
			}
		}
		// 获取成本转移数据
		page = new Page();
		page.setModelClass(ScmInvMoveBillEntry2.class);
		page.setShowCount(Integer.MAX_VALUE);
		page.getParam()
				.put(ClassUtils.getFinalModelSimpleName(ScmInvMoveBillEntry2.class) + "."
						+ ScmInvMoveBillEntry2.FN_ITEMID,
				new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMoveBillEntry2.class) + "."
						+ ScmInvMoveBillEntry2.FN_ITEMID, QueryParam.QUERY_EQ, materialName));
		page.getParamOr()
				.put(ClassUtils.getFinalModelSimpleName(ScmInvMoveBill.class) + "." + ScmInvMoveBill.FN_OUTCSTORGUNITNO,
						new QueryParam(
								ClassUtils.getFinalModelSimpleName(ScmInvMoveBill.class) + "."
										+ ScmInvMoveBill.FN_OUTCSTORGUNITNO,
								QueryParam.QUERY_IN, costOrgUnitNos.toString()));
		page.getParamOr()
				.put(ClassUtils.getFinalModelSimpleName(ScmInvMoveBill.class) + "." + ScmInvMoveBill.FN_INCSTORGUNITNO,
						new QueryParam(
								ClassUtils.getFinalModelSimpleName(ScmInvMoveBill.class) + "."
										+ ScmInvMoveBill.FN_INCSTORGUNITNO,
								QueryParam.QUERY_IN, costOrgUnitNos.toString()));
		page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvMoveBill.class) + "." + ScmInvMoveBill.FN_BIZDATE,
				new QueryParam(
						ClassUtils.getFinalModelSimpleName(ScmInvMoveBill.class) + "." + ScmInvMoveBill.FN_BIZDATE,
						QueryParam.QUERY_BETWEEN, FormatUtils.fmtDate(periodCalendar.getStartDate()), endDate));
		if (!StringUtils.equals("Y", status)) {
			page.getParam().put(
					ClassUtils.getFinalModelSimpleName(ScmInvMoveBill.class) + "." + ScmInvMoveBill.FN_STATUS,
					new QueryParam(
							ClassUtils.getFinalModelSimpleName(ScmInvMoveBill.class) + "." + ScmInvMoveBill.FN_STATUS,
							QueryParam.QUERY_EQ, "E"));
		} else {
			page.getParam().put(
					ClassUtils.getFinalModelSimpleName(ScmInvMoveBill.class) + "." + ScmInvMoveBill.FN_STATUS,
					new QueryParam(
							ClassUtils.getFinalModelSimpleName(ScmInvMoveBill.class) + "." + ScmInvMoveBill.FN_STATUS,
							QueryParam.QUERY_NOTIN, "'N'"));
		}
		List<ScmInvMoveBillEntry2> scmInvMoveBillEntryList = scmInvMoveBillEntryBiz.queryPage(page, arglist,
				"findAllPage", param);
		if (scmInvMoveBillEntryList != null && !scmInvMoveBillEntryList.isEmpty()) {
			for (ScmInvMoveBillEntry2 scmInvMoveBillEntry : scmInvMoveBillEntryList) {
				String cstOrg = "";
				String usrOrg = "";
				if (scmInvMoveBillEntry.getBizDate().compareTo(FormatUtils.parseDate(beginDate)) < 0) {
					// 比查询日期小的统计到期初数据中
					if (StringUtils.contains(costOrgUnitNos.toString(), scmInvMoveBillEntry.getOutCstOrgUnitNo())) {
						// 调出
						cstOrg = scmInvMoveBillEntry.getOutCstOrgUnitNo();
						usrOrg = scmInvMoveBillEntry.getOutOrgUnitNo();
						if (initQtyMap.containsKey(cstOrg + "_" + usrOrg)) {
							initQty = initQtyMap.get(cstOrg + "_" + usrOrg);
							initQty = initQty.subtract(scmInvMoveBillEntry.getQty());
							initQtyMap.put(cstOrg + "_" + usrOrg, initQty);
						} else {
							initQtyMap.put(cstOrg + "_" + usrOrg,
									BigDecimal.ZERO.subtract(scmInvMoveBillEntry.getQty()));
						}
						if (initAmtMap.containsKey(cstOrg + "_" + usrOrg)) {
							initAmt = initAmtMap.get(cstOrg + "_" + usrOrg);
							initAmt = initAmt.subtract(scmInvMoveBillEntry.getAmt());
							initAmtMap.put(cstOrg + "_" + usrOrg, initAmt);
						} else {
							initAmtMap.put(cstOrg + "_" + usrOrg,
									BigDecimal.ZERO.subtract(scmInvMoveBillEntry.getAmt()));
						}
						if (initTaxAmtMap.containsKey(cstOrg + "_" + usrOrg)) {
							initTaxAmt = initTaxAmtMap.get(cstOrg + "_" + usrOrg);
							initTaxAmt = initTaxAmt.subtract(scmInvMoveBillEntry.getTaxAmt());
							initTaxAmtMap.put(cstOrg + "_" + usrOrg, initTaxAmt);
						} else {
							initTaxAmtMap.put(cstOrg + "_" + usrOrg,
									BigDecimal.ZERO.subtract(scmInvMoveBillEntry.getTaxAmt()));
						}
					}
					if (StringUtils.contains(costOrgUnitNos.toString(), scmInvMoveBillEntry.getInCstOrgUnitNo())) {
						// 调入
						cstOrg = scmInvMoveBillEntry.getInCstOrgUnitNo();
						usrOrg = scmInvMoveBillEntry.getInOrgUnitNo();
						OrgCostCenter2 orgCostCenter = orgCostCenterBiz.selectByOrgUnitNo(cstOrg, param);
						if (orgCostCenter != null && StringUtils.equals("1", orgCostCenter.getCostCenterType())) {
							continue;
						}
						if (initQtyMap.containsKey(cstOrg + "_" + usrOrg)) {
							initQty = initQtyMap.get(cstOrg + "_" + usrOrg);
							initQty = initQty.add(scmInvMoveBillEntry.getQty());
							initQtyMap.put(cstOrg + "_" + usrOrg, initQty);
						} else {
							initQtyMap.put(cstOrg + "_" + usrOrg, scmInvMoveBillEntry.getQty());
						}
						if (initAmtMap.containsKey(cstOrg + "_" + usrOrg)) {
							initAmt = initAmtMap.get(cstOrg + "_" + usrOrg);
							initAmt = initAmt.add(scmInvMoveBillEntry.getAmt());
							initAmtMap.put(cstOrg + "_" + usrOrg, initAmt);
						} else {
							initAmtMap.put(cstOrg + "_" + usrOrg, scmInvMoveBillEntry.getAmt());
						}
						if (initTaxAmtMap.containsKey(cstOrg + "_" + usrOrg)) {
							initTaxAmt = initTaxAmtMap.get(cstOrg + "_" + usrOrg);
							initTaxAmt = initTaxAmt.add(scmInvMoveBillEntry.getTaxAmt());
							initTaxAmtMap.put(cstOrg + "_" + usrOrg, initTaxAmt);
						} else {
							initTaxAmtMap.put(cstOrg + "_" + usrOrg, scmInvMoveBillEntry.getTaxAmt());
						}
					}
				} else {
					// 放入明细记录结果集中
					if (StringUtils.contains(costOrgUnitNos.toString(), scmInvMoveBillEntry.getOutCstOrgUnitNo())) {
						ScmCostItemInAndOutDetail scmCostItemInAndOutDetail = new ScmCostItemInAndOutDetail(true);
						scmCostItemInAndOutDetail.setOutQty(scmInvMoveBillEntry.getQty());
						scmCostItemInAndOutDetail.setOutAmt(scmInvMoveBillEntry.getAmt());
						scmCostItemInAndOutDetail
								.setOutTax(scmInvMoveBillEntry.getTaxAmt().subtract(scmInvMoveBillEntry.getAmt()));	
						scmCostItemInAndOutDetail.setOutTaxAmt(scmInvMoveBillEntry.getTaxAmt());
						scmCostItemInAndOutDetail.setAllotOutQty(scmInvMoveBillEntry.getQty());
						scmCostItemInAndOutDetail.setAllotOutAmt(scmInvMoveBillEntry.getAmt());
						scmCostItemInAndOutDetail
								.setAllotOutTax(scmInvMoveBillEntry.getTaxAmt().subtract(scmInvMoveBillEntry.getAmt()));
						scmCostItemInAndOutDetail.setAllotOutTaxAmt(scmInvMoveBillEntry.getTaxAmt());
						
						cstOrg = scmInvMoveBillEntry.getOutCstOrgUnitNo();
						usrOrg = scmInvMoveBillEntry.getOutOrgUnitNo();
						scmCostItemInAndOutDetail.setCostOrgUnitNo(cstOrg);
						scmCostItemInAndOutDetail.setUseOrgUnitNo(usrOrg);
						OrgBaseUnit2 orgBaseUnit = orgUnitBiz.selectbyOrgNo(cstOrg, param);
						if (orgBaseUnit != null) {
							scmCostItemInAndOutDetail.setCostOrgUnitName(orgBaseUnit.getOrgUnitName());
						} else {
							scmCostItemInAndOutDetail.setCostOrgUnitName(cstOrg);
						}
						scmCostItemInAndOutDetail.setBillNo(scmInvMoveBillEntry.getWtNo());
						Code code = codeBiz.selectByCategoryAndCode("cstStatus", scmInvMoveBillEntry.getStatus());
						if (code != null) {
							scmCostItemInAndOutDetail.setStatusName(code.getName());
						} else {
							scmCostItemInAndOutDetail.setStatusName(scmInvMoveBillEntry.getStatus());
						}
						scmCostItemInAndOutDetail.setBizDate(scmInvMoveBillEntry.getBizDate());
						scmCostItemInAndOutDetail.setPostDate(scmInvMoveBillEntry.getPostDate());
						scmCostItemInAndOutDetail
								.setBizTypeName(Message.getMessage("CN", "iscm.ScmInvCostConsume.changeout")); // 转出
						orgBaseUnit = orgUnitBiz.selectbyOrgNo(usrOrg, param);
						if (orgBaseUnit != null) {
							scmCostItemInAndOutDetail.setUseOrgUnitName(orgBaseUnit.getOrgUnitName());
						} else {
							scmCostItemInAndOutDetail.setUseOrgUnitName(usrOrg);
						}
						if (scmInvMoveBillEntry.getUnit() > 0) {
							ScmMeasureUnit scmMeasureUnit = scmMeasureUnitBiz
									.selectDirect(scmInvMoveBillEntry.getUnit(), param);
							if (scmMeasureUnit != null)
								scmCostItemInAndOutDetail.setUnitName(scmMeasureUnit.getUnitName());
						}
						scmCostItemInAndOutDetail.setLot(scmInvMoveBillEntry.getLot());
						scmCostItemInAndOutDetailList.add(scmCostItemInAndOutDetail);
					}
					if (StringUtils.contains(costOrgUnitNos.toString(), scmInvMoveBillEntry.getInCstOrgUnitNo())) {
						ScmCostItemInAndOutDetail scmCostItemInAndOutDetail = new ScmCostItemInAndOutDetail(true);
						scmCostItemInAndOutDetail.setAddInQty(scmInvMoveBillEntry.getQty());
						scmCostItemInAndOutDetail.setAddInAmt(scmInvMoveBillEntry.getAmt());
						scmCostItemInAndOutDetail
								.setAddInTax(scmInvMoveBillEntry.getTaxAmt().subtract(scmInvMoveBillEntry.getAmt()));
						scmCostItemInAndOutDetail.setAddInTaxAmt(scmInvMoveBillEntry.getTaxAmt());
						
						scmCostItemInAndOutDetail.setAllotInQty(scmInvMoveBillEntry.getQty());
						scmCostItemInAndOutDetail.setAllotInAmt(scmInvMoveBillEntry.getAmt());
						scmCostItemInAndOutDetail
								.setAllotInTax(scmInvMoveBillEntry.getTaxAmt().subtract(scmInvMoveBillEntry.getAmt()));
						scmCostItemInAndOutDetail.setAllotInTaxAmt(scmInvMoveBillEntry.getTaxAmt());
						cstOrg = scmInvMoveBillEntry.getInCstOrgUnitNo();
						usrOrg = scmInvMoveBillEntry.getInOrgUnitNo();
						scmCostItemInAndOutDetail.setCostOrgUnitNo(cstOrg);
						scmCostItemInAndOutDetail.setUseOrgUnitNo(usrOrg);
						OrgCostCenter2 orgCostCenter = orgCostCenterBiz.selectByOrgUnitNo(cstOrg, param);
						if (orgCostCenter != null) {
							if (StringUtils.equals("1", orgCostCenter.getCostCenterType())) {
								scmCostItemInAndOutDetail.setOutQty(scmInvMoveBillEntry.getQty());
								scmCostItemInAndOutDetail.setOutAmt(scmInvMoveBillEntry.getAmt());
								scmCostItemInAndOutDetail.setOutTax(
										scmInvMoveBillEntry.getTaxAmt().subtract(scmInvMoveBillEntry.getAmt()));
								scmCostItemInAndOutDetail.setOutTaxAmt(scmInvMoveBillEntry.getTaxAmt());
							}
							scmCostItemInAndOutDetail.setCostOrgUnitName(orgCostCenter.getOrgUnitName());
						} else {
							scmCostItemInAndOutDetail.setCostOrgUnitName(cstOrg);
						}
						scmCostItemInAndOutDetail.setBillNo(scmInvMoveBillEntry.getWtNo());
						Code code = codeBiz.selectByCategoryAndCode("cstStatus", scmInvMoveBillEntry.getStatus());
						if (code != null) {
							scmCostItemInAndOutDetail.setStatusName(code.getName());
						} else {
							scmCostItemInAndOutDetail.setStatusName(scmInvMoveBillEntry.getStatus());
						}
						scmCostItemInAndOutDetail.setBizDate(scmInvMoveBillEntry.getBizDate());
						scmCostItemInAndOutDetail.setPostDate(scmInvMoveBillEntry.getPostDate());
						scmCostItemInAndOutDetail
								.setBizTypeName(Message.getMessage("CN", "iscm.ScmInvCostConsume.changeinto")); // 转入
						OrgBaseUnit2 orgBaseUnit = orgUnitBiz.selectbyOrgNo(usrOrg, param);
						if (orgBaseUnit != null) {
							scmCostItemInAndOutDetail.setUseOrgUnitName(orgBaseUnit.getOrgUnitName());
						} else {
							scmCostItemInAndOutDetail.setUseOrgUnitName(usrOrg);
						}
						if (scmInvMoveBillEntry.getUnit() > 0) {
							ScmMeasureUnit scmMeasureUnit = scmMeasureUnitBiz
									.selectDirect(scmInvMoveBillEntry.getUnit(), param);
							if (scmMeasureUnit != null)
								scmCostItemInAndOutDetail.setUnitName(scmMeasureUnit.getUnitName());
						}
						scmCostItemInAndOutDetail.setLot(scmInvMoveBillEntry.getLot());
						scmCostItemInAndOutDetailList.add(scmCostItemInAndOutDetail);
					}
				}
			}
		}
		// 盘存差异
		page = new Page();
		page.setModelClass(ScmInvCountingCostCenterEntry2.class);
		page.setShowCount(Integer.MAX_VALUE);
		page.getParam()
				.put(ClassUtils.getFinalModelSimpleName(ScmInvCountingCostCenterEntry2.class) + "."
						+ ScmInvCountingCostCenterEntry2.FN_ITEMID,
				new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvCountingCostCenterEntry2.class) + "."
						+ ScmInvCountingCostCenterEntry2.FN_ITEMID, QueryParam.QUERY_EQ, materialName));
		page.getParam()
				.put(ClassUtils.getFinalModelSimpleName(ScmInvCountingCostCenter.class) + "."
						+ ScmInvCountingCostCenter.FN_ORGUNITNO,
				new QueryParam(
						ClassUtils.getFinalModelSimpleName(ScmInvCountingCostCenter.class) + "."
								+ ScmInvCountingCostCenter.FN_ORGUNITNO,
						QueryParam.QUERY_IN, costOrgUnitNos.toString()));
		page.getParam().put(
				ClassUtils.getFinalModelSimpleName(ScmInvCountingTask.class) + "." + ScmInvCountingTask.FN_BIZDATE,
				new QueryParam(
						ClassUtils.getFinalModelSimpleName(ScmInvCountingTask.class) + "."
								+ ScmInvCountingTask.FN_BIZDATE,
						QueryParam.QUERY_BETWEEN, FormatUtils.fmtDate(periodCalendar.getStartDate()), endDate));
		page.getParam()
				.put(ClassUtils.getFinalModelSimpleName(ScmInvCountingCostCenterEntry2.class) + "."
						+ ScmInvCountingCostCenterEntry2.FN_DIFFERQTY,
				new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvCountingCostCenterEntry2.class) + "."
						+ ScmInvCountingCostCenterEntry2.FN_DIFFERQTY, QueryParam.QUERY_NE, "0"));
		if (!StringUtils.equals("Y", status)) {
			page.getParam()
					.put(ClassUtils.getFinalModelSimpleName(ScmInvCountingTask.class) + "."
							+ ScmInvCountingTask.FN_STATUS,
					new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvCountingTask.class) + "."
							+ ScmInvCountingTask.FN_STATUS, QueryParam.QUERY_EQ, "O"));
		} else {
			page.getParam()
					.put(ClassUtils.getFinalModelSimpleName(ScmInvCountingTask.class) + "."
							+ ScmInvCountingTask.FN_STATUS,
					new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvCountingTask.class) + "."
							+ ScmInvCountingTask.FN_STATUS, QueryParam.QUERY_IN, "'T','B','O'"));
		}
		List<ScmInvCountingCostCenterEntry2> scmInvCountingCostCenterEntryList = scmInvCountingCostCenterEntryBiz
				.queryPage(page, arglist, "findAllPage", param);
		if (scmInvCountingCostCenterEntryList != null && !scmInvCountingCostCenterEntryList.isEmpty()) {
			for (ScmInvCountingCostCenterEntry2 scmInvCountingCostCenterEntry : scmInvCountingCostCenterEntryList) {
				OrgCostCenter2 orgCostCenter = orgCostCenterBiz
						.selectByOrgUnitNo(scmInvCountingCostCenterEntry.getOrgUnitNo(), param);
				if (orgCostCenter != null && StringUtils.equals("1", orgCostCenter.getCostCenterType())) {
					continue;
				}
				if (scmInvCountingCostCenterEntry.getBizDate().compareTo(FormatUtils.parseDate(beginDate)) < 0) {
					// 比查询日期小的统计到期初数据中
					if (initQtyMap.containsKey(scmInvCountingCostCenterEntry.getOrgUnitNo() + "_"
							+ scmInvCountingCostCenterEntry.getUseOrgUnitNo())) {
						initQty = initQtyMap.get(scmInvCountingCostCenterEntry.getOrgUnitNo() + "_"
								+ scmInvCountingCostCenterEntry.getUseOrgUnitNo());
						initQty = initQty.add(scmInvCountingCostCenterEntry.getDifferQty());
						initQtyMap.put(scmInvCountingCostCenterEntry.getOrgUnitNo() + "_"
								+ scmInvCountingCostCenterEntry.getUseOrgUnitNo(), initQty);
					} else {
						initQtyMap.put(
								scmInvCountingCostCenterEntry.getOrgUnitNo() + "_"
										+ scmInvCountingCostCenterEntry.getUseOrgUnitNo(),
								scmInvCountingCostCenterEntry.getDifferQty());
					}
					if (initAmtMap.containsKey(scmInvCountingCostCenterEntry.getOrgUnitNo() + "_"
							+ scmInvCountingCostCenterEntry.getUseOrgUnitNo())) {
						initAmt = initAmtMap.get(scmInvCountingCostCenterEntry.getOrgUnitNo() + "_"
								+ scmInvCountingCostCenterEntry.getUseOrgUnitNo());
						initAmt = initAmt.add(scmInvCountingCostCenterEntry.getDifferAmt());
						initAmtMap.put(scmInvCountingCostCenterEntry.getOrgUnitNo() + "_"
								+ scmInvCountingCostCenterEntry.getUseOrgUnitNo(), initAmt);
					} else {
						initAmtMap.put(
								scmInvCountingCostCenterEntry.getOrgUnitNo() + "_"
										+ scmInvCountingCostCenterEntry.getUseOrgUnitNo(),
								scmInvCountingCostCenterEntry.getDifferAmt());
					}
					if (initTaxAmtMap.containsKey(scmInvCountingCostCenterEntry.getOrgUnitNo() + "_"
							+ scmInvCountingCostCenterEntry.getUseOrgUnitNo())) {
						initTaxAmt = initTaxAmtMap.get(scmInvCountingCostCenterEntry.getOrgUnitNo() + "_"
								+ scmInvCountingCostCenterEntry.getUseOrgUnitNo());
						initTaxAmt = initTaxAmt.add(scmInvCountingCostCenterEntry.getDifferTaxAmt());
						initTaxAmtMap.put(scmInvCountingCostCenterEntry.getOrgUnitNo() + "_"
								+ scmInvCountingCostCenterEntry.getUseOrgUnitNo(), initTaxAmt);
					} else {
						initTaxAmtMap.put(
								scmInvCountingCostCenterEntry.getOrgUnitNo() + "_"
										+ scmInvCountingCostCenterEntry.getUseOrgUnitNo(),
								scmInvCountingCostCenterEntry.getDifferTaxAmt());
					}
				} else {
					// 放入明细记录结果集中
					ScmCostItemInAndOutDetail scmCostItemInAndOutDetail = new ScmCostItemInAndOutDetail(true);
					scmCostItemInAndOutDetail.setCostOrgUnitNo(scmInvCountingCostCenterEntry.getOrgUnitNo());
					scmCostItemInAndOutDetail.setUseOrgUnitNo(scmInvCountingCostCenterEntry.getUseOrgUnitNo());
					OrgBaseUnit2 orgBaseUnit = orgUnitBiz.selectbyOrgNo(scmInvCountingCostCenterEntry.getOrgUnitNo(),
							param);
					if (orgBaseUnit != null) {
						scmCostItemInAndOutDetail.setCostOrgUnitName(orgBaseUnit.getOrgUnitName());
					} else {
						scmCostItemInAndOutDetail.setCostOrgUnitName(scmInvCountingCostCenterEntry.getOrgUnitNo());
					}
					scmCostItemInAndOutDetail.setBillNo(scmInvCountingCostCenterEntry.getTaskNo());
					Code code = codeBiz.selectByCategoryAndCode("countingStatus",
							scmInvCountingCostCenterEntry.getStatus());
					if (code != null) {
						scmCostItemInAndOutDetail.setStatusName(code.getName());
					} else {
						scmCostItemInAndOutDetail.setStatusName(scmInvCountingCostCenterEntry.getStatus());
					}
					scmCostItemInAndOutDetail.setBizDate(scmInvCountingCostCenterEntry.getBizDate());
					scmCostItemInAndOutDetail.setPostDate(scmInvCountingCostCenterEntry.getPostDate());
					scmCostItemInAndOutDetail
							.setBizTypeName(Message.getMessage("CN", "iscm.ScmInvCostConsume.Consume")); // 耗用
					orgBaseUnit = orgUnitBiz.selectbyOrgNo(scmInvCountingCostCenterEntry.getUseOrgUnitNo(), param);
					if (orgBaseUnit != null) {
						scmCostItemInAndOutDetail.setUseOrgUnitName(orgBaseUnit.getOrgUnitName());
					} else {
						scmCostItemInAndOutDetail.setUseOrgUnitName(scmInvCountingCostCenterEntry.getUseOrgUnitNo());
					}
					if (scmInvCountingCostCenterEntry.getUnit() > 0) {
						ScmMeasureUnit scmMeasureUnit = scmMeasureUnitBiz
								.selectDirect(scmInvCountingCostCenterEntry.getUnit(), param);
						if (scmMeasureUnit != null)
							scmCostItemInAndOutDetail.setUnitName(scmMeasureUnit.getUnitName());
					}
					scmCostItemInAndOutDetail.setLot(scmInvCountingCostCenterEntry.getLot());
					if (scmInvCountingCostCenterEntry.getDifferQty().compareTo(BigDecimal.ZERO) < 0) {
						scmCostItemInAndOutDetail
								.setOutQty(BigDecimal.ZERO.subtract(scmInvCountingCostCenterEntry.getDifferQty()));
						scmCostItemInAndOutDetail
								.setOutAmt(BigDecimal.ZERO.subtract(scmInvCountingCostCenterEntry.getDifferAmt()));
						scmCostItemInAndOutDetail.setOutTax(BigDecimal.ZERO.subtract(scmInvCountingCostCenterEntry
								.getDifferTaxAmt().subtract(scmInvCountingCostCenterEntry.getDifferAmt())));
						scmCostItemInAndOutDetail.setOutTaxAmt(
								BigDecimal.ZERO.subtract(scmInvCountingCostCenterEntry.getDifferTaxAmt()));
						scmCostItemInAndOutDetail.setInvenOutQty(scmInvCountingCostCenterEntry.getDifferQty());
						scmCostItemInAndOutDetail.setInvenOutAmt(scmInvCountingCostCenterEntry.getDifferAmt());
						scmCostItemInAndOutDetail.setInvenOutTax(scmInvCountingCostCenterEntry.getDifferTaxAmt().subtract(scmInvCountingCostCenterEntry.getDifferAmt()));
						scmCostItemInAndOutDetail.setInvenOutTaxAmt(scmInvCountingCostCenterEntry.getDifferTaxAmt());
					} else {
						scmCostItemInAndOutDetail.setAddInQty(scmInvCountingCostCenterEntry.getDifferQty());
						scmCostItemInAndOutDetail.setAddInAmt(scmInvCountingCostCenterEntry.getDifferAmt());
						scmCostItemInAndOutDetail.setAddInTax(scmInvCountingCostCenterEntry.getDifferTaxAmt()
								.subtract(scmInvCountingCostCenterEntry.getDifferAmt()));
						scmCostItemInAndOutDetail.setAddInTaxAmt(scmInvCountingCostCenterEntry.getDifferTaxAmt());
						scmCostItemInAndOutDetail.setInvenOutQty(scmInvCountingCostCenterEntry.getDifferQty());
						scmCostItemInAndOutDetail.setInvenOutAmt(scmInvCountingCostCenterEntry.getDifferAmt());
						scmCostItemInAndOutDetail.setInvenOutTax(scmInvCountingCostCenterEntry.getDifferTaxAmt().subtract(scmInvCountingCostCenterEntry.getDifferAmt()));
						scmCostItemInAndOutDetail.setInvenOutTaxAmt(scmInvCountingCostCenterEntry.getDifferTaxAmt());
					}
					scmCostItemInAndOutDetailList.add(scmCostItemInAndOutDetail);
				}
			}
		}

		// 获取报损单数据
		page = new Page();
		page.setModelClass(ScmCstFrmLossEntry2.class);
		page.setShowCount(Integer.MAX_VALUE);
		page.getParam()
				.put(ClassUtils.getFinalModelSimpleName(ScmCstFrmLossEntry2.class) + "."
						+ ScmCstFrmLossEntry2.FN_ITEMID,
				new QueryParam(ClassUtils.getFinalModelSimpleName(ScmCstFrmLossEntry2.class) + "."
						+ ScmCstFrmLossEntry2.FN_ITEMID, QueryParam.QUERY_EQ, materialName));
		page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmCstFrmLoss.class) + "." + ScmCstFrmLoss.FN_ORGUNITNO,
				new QueryParam(
						ClassUtils.getFinalModelSimpleName(ScmCstFrmLoss.class) + "." + ScmCstFrmLoss.FN_ORGUNITNO,
						QueryParam.QUERY_IN, costOrgUnitNos.toString()));
		page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmCstFrmLoss.class) + "." + ScmCstFrmLoss.FN_BIZDATE,
				new QueryParam(ClassUtils.getFinalModelSimpleName(ScmCstFrmLoss.class) + "." + ScmCstFrmLoss.FN_BIZDATE,
						QueryParam.QUERY_BETWEEN, beginDate, endDate));
		if (!StringUtils.equals("Y", status)) {
			page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmCstFrmLoss.class) + "." + ScmCstFrmLoss.FN_STATUS,
					new QueryParam(
							ClassUtils.getFinalModelSimpleName(ScmCstFrmLoss.class) + "." + ScmCstFrmLoss.FN_STATUS,
							QueryParam.QUERY_EQ, "E"));
		}else {
			page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmCstFrmLoss.class) + "." + ScmCstFrmLoss.FN_STATUS,
					new QueryParam(
							ClassUtils.getFinalModelSimpleName(ScmCstFrmLoss.class) + "." + ScmCstFrmLoss.FN_STATUS,
							QueryParam.QUERY_NOTIN, "'N'"));
		}
		List<ScmCstFrmLossEntry2> scmCstFrmLossEntryList = scmCstFrmLossEntryBiz.queryPage(page, arglist,
				"findAllPage", param);
		if (scmCstFrmLossEntryList != null && !scmCstFrmLossEntryList.isEmpty()) {
			for (ScmCstFrmLossEntry2 scmCstFrmLossEntry : scmCstFrmLossEntryList) {
				OrgCostCenter2 orgCostCenter = orgCostCenterBiz.selectByOrgUnitNo(scmCstFrmLossEntry.getCostOrgUnitNo(),
						param);
				if (orgCostCenter != null && StringUtils.equals("1", orgCostCenter.getCostCenterType())) {
					continue;
				}
				if (scmCstFrmLossEntry.getBizDate().compareTo(FormatUtils.parseDate(beginDate)) < 0) {
					// 比查询日期小的统计到期初数据中，并从List中删除
					if (initQtyMap.containsKey(
							scmCstFrmLossEntry.getCostOrgUnitNo() + "_" + scmCstFrmLossEntry.getUseOrgUnitNo())) {
						initQty = initQtyMap.get(
								scmCstFrmLossEntry.getCostOrgUnitNo() + "_" + scmCstFrmLossEntry.getUseOrgUnitNo());
					}
					if (initAmtMap.containsKey(
							scmCstFrmLossEntry.getCostOrgUnitNo() + "_" + scmCstFrmLossEntry.getUseOrgUnitNo())) {
						initAmt = initAmtMap.get(
								scmCstFrmLossEntry.getCostOrgUnitNo() + "_" + scmCstFrmLossEntry.getUseOrgUnitNo());
					}
					if (initTaxAmtMap.containsKey(
							scmCstFrmLossEntry.getCostOrgUnitNo() + "_" + scmCstFrmLossEntry.getUseOrgUnitNo())) {
						initTaxAmt = initTaxAmtMap.get(
								scmCstFrmLossEntry.getCostOrgUnitNo() + "_" + scmCstFrmLossEntry.getUseOrgUnitNo());
					}
					initQty = initQty.subtract(scmCstFrmLossEntry.getQty());
					initAmt = initAmt.subtract(scmCstFrmLossEntry.getAmt());
					initTaxAmt = initTaxAmt.subtract(scmCstFrmLossEntry.getTaxAmt());
					initQtyMap.put(scmCstFrmLossEntry.getCostOrgUnitNo() + "_" + scmCstFrmLossEntry.getUseOrgUnitNo(),
							initQty);
					initAmtMap.put(scmCstFrmLossEntry.getCostOrgUnitNo() + "_" + scmCstFrmLossEntry.getUseOrgUnitNo(),
							initAmt);
					initTaxAmtMap.put(
							scmCstFrmLossEntry.getCostOrgUnitNo() + "_" + scmCstFrmLossEntry.getUseOrgUnitNo(),
							initTaxAmt);
				} else {
					// 放入明细记录结果集中
					ScmCostItemInAndOutDetail scmCostItemInAndOutDetail = new ScmCostItemInAndOutDetail(true);
					scmCostItemInAndOutDetail.setCostOrgUnitNo(scmCstFrmLossEntry.getCostOrgUnitNo());
					scmCostItemInAndOutDetail.setUseOrgUnitNo(scmCstFrmLossEntry.getUseOrgUnitNo());
					OrgBaseUnit2 orgBaseUnit = orgUnitBiz.selectbyOrgNo(scmCstFrmLossEntry.getCostOrgUnitNo(), param);
					if (orgBaseUnit != null) {
						scmCostItemInAndOutDetail.setCostOrgUnitName(orgBaseUnit.getOrgUnitName());
					} else {
						scmCostItemInAndOutDetail.setCostOrgUnitName(scmCstFrmLossEntry.getCostOrgUnitNo());
					}
					scmCostItemInAndOutDetail.setBillNo(scmCstFrmLossEntry.getBillNo());
					Code code = codeBiz.selectByCategoryAndCode("cstStatus", scmCstFrmLossEntry.getStatus());
					if (code != null) {
						scmCostItemInAndOutDetail.setStatusName(code.getName());
					} else {
						scmCostItemInAndOutDetail.setStatusName(scmCstFrmLossEntry.getStatus());
					}
					scmCostItemInAndOutDetail.setBizDate(scmCstFrmLossEntry.getBizDate());
					scmCostItemInAndOutDetail.setPostDate(scmCstFrmLossEntry.getPostDate());
					scmCostItemInAndOutDetail
							.setBizTypeName(Message.getMessage("CN", "iscm.ScmInvCostConsume.Consume")); // 耗用
					orgBaseUnit = orgUnitBiz.selectbyOrgNo(scmCstFrmLossEntry.getUseOrgUnitNo(), param);
					if (orgBaseUnit != null) {
						scmCostItemInAndOutDetail.setUseOrgUnitName(orgBaseUnit.getOrgUnitName());
					} else {
						scmCostItemInAndOutDetail.setUseOrgUnitName(scmCstFrmLossEntry.getUseOrgUnitNo());
					}
					if (scmCstFrmLossEntry.getUnit() > 0) {
						ScmMeasureUnit scmMeasureUnit = scmMeasureUnitBiz.selectDirect(scmCstFrmLossEntry.getUnit(),
								param);
						if (scmMeasureUnit != null)
							scmCostItemInAndOutDetail.setUnitName(scmMeasureUnit.getUnitName());
					}
					scmCostItemInAndOutDetail.setLot(scmCstFrmLossEntry.getLot());
					scmCostItemInAndOutDetail.setOutQty(scmCstFrmLossEntry.getQty());
					scmCostItemInAndOutDetail.setOutAmt(scmCstFrmLossEntry.getAmt());
					scmCostItemInAndOutDetail
							.setOutTax(scmCstFrmLossEntry.getTaxAmt().subtract(scmCstFrmLossEntry.getAmt()));
					scmCostItemInAndOutDetail.setOutTaxAmt(scmCstFrmLossEntry.getTaxAmt());
					scmCostItemInAndOutDetail.setBreakOutQty(scmCstFrmLossEntry.getQty());
					scmCostItemInAndOutDetail.setBreakOutAmt(scmCstFrmLossEntry.getAmt());
					scmCostItemInAndOutDetail
							.setBreakOutTax(scmCstFrmLossEntry.getTaxAmt().subtract(scmCstFrmLossEntry.getAmt()));
					scmCostItemInAndOutDetail.setBreakOutTaxAmt(scmCstFrmLossEntry.getTaxAmt());
					scmCostItemInAndOutDetailList.add(scmCostItemInAndOutDetail);
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
			ScmCostItemInAndOutDetail scmCostItemInAndOutDetail = new ScmCostItemInAndOutDetail(true);
			scmCostItemInAndOutDetail.setCostOrgUnitNo(keys[0]);
			scmCostItemInAndOutDetail.setUseOrgUnitNo(keys[1]);
			OrgBaseUnit2 orgBaseUnit = orgUnitBiz.selectbyOrgNo(scmCostItemInAndOutDetail.getCostOrgUnitNo(), param);
			if (orgBaseUnit != null) {
				scmCostItemInAndOutDetail.setCostOrgUnitName(orgBaseUnit.getOrgUnitName());
			} else {
				scmCostItemInAndOutDetail.setCostOrgUnitName(scmCostItemInAndOutDetail.getCostOrgUnitNo());
			}
			orgBaseUnit = orgUnitBiz.selectbyOrgNo(scmCostItemInAndOutDetail.getUseOrgUnitNo(), param);
			if (orgBaseUnit != null) {
				scmCostItemInAndOutDetail.setUseOrgUnitName(orgBaseUnit.getOrgUnitName());
			} else {
				scmCostItemInAndOutDetail.setUseOrgUnitName(scmCostItemInAndOutDetail.getUseOrgUnitNo());
			}
			scmCostItemInAndOutDetail.setBizTypeName("");
			scmCostItemInAndOutDetail.setBillNo("期初余额");
			scmCostItemInAndOutDetail.setInitflag(1);
			scmCostItemInAndOutDetail.setBizDate(FormatUtils.parseDate(beginDate));
			scmCostItemInAndOutDetail.setPostDate(FormatUtils.parseDate(beginDate));
			scmCostItemInAndOutDetail.setStockQty(initQtyMap.get(key));
			scmCostItemInAndOutDetail.setStockAmt(initAmtMap.get(key));
			scmCostItemInAndOutDetail.setStockTaxAmt(initTaxAmtMap.get(key));
			scmCostItemInAndOutDetail.setStockTax(
					scmCostItemInAndOutDetail.getStockTaxAmt().subtract(scmCostItemInAndOutDetail.getStockAmt()));
			scmCostItemInAndOutDetailList.add(scmCostItemInAndOutDetail);
		}

		// 数据排序
		String fields[] = { "costOrgUnitNo", "useOrgUnitNo", "initflag", "bizDate", "postDate" };
		String sorts[] = { "ASC", "ASC", "DESC", "ASC", "ASC" };
		scmCostItemInAndOutDetailList = (List<ScmCostItemInAndOutDetail>) ListSortUtil
				.sort(scmCostItemInAndOutDetailList, fields, sorts);
		BigDecimal stockQty = BigDecimal.ZERO;
		BigDecimal stockAmt = BigDecimal.ZERO;
		BigDecimal stockTax = BigDecimal.ZERO;
		BigDecimal stockTaxAmt = BigDecimal.ZERO;
		for (ScmCostItemInAndOutDetail scmCostItemInAndOutDetail : scmCostItemInAndOutDetailList) {
			if (scmCostItemInAndOutDetail.getInitflag() == 1) {
				stockQty = scmCostItemInAndOutDetail.getStockQty();
				stockAmt = scmCostItemInAndOutDetail.getStockAmt();
				stockTax = scmCostItemInAndOutDetail.getStockTax();
				stockTaxAmt = scmCostItemInAndOutDetail.getStockTaxAmt();
			} else {
				stockQty = stockQty.add(scmCostItemInAndOutDetail.getAddInQty()).subtract(scmCostItemInAndOutDetail.getOutQty());
				stockAmt = stockAmt.add(scmCostItemInAndOutDetail.getAddInAmt()).subtract(scmCostItemInAndOutDetail.getOutAmt());
				stockTax = stockTax.add(scmCostItemInAndOutDetail.getAddInTax()).subtract(scmCostItemInAndOutDetail.getOutTax());
				stockTaxAmt = stockTaxAmt.add(scmCostItemInAndOutDetail.getAddInTaxAmt())
						.subtract(scmCostItemInAndOutDetail.getOutTaxAmt());
				scmCostItemInAndOutDetail.setStockQty(stockQty);
				scmCostItemInAndOutDetail.setStockAmt(stockAmt);
				scmCostItemInAndOutDetail.setStockTax(stockTax);
				scmCostItemInAndOutDetail.setStockTaxAmt(stockTaxAmt);
			}
		}
		return scmCostItemInAndOutDetailList;
	}

    @Override
    public List<ScmCostTransferOccurSum> selectScmCostTransferOccurSum(HttpServletRequest request) throws AppException {
        List<ScmCostTransferOccurSum> scmCostTransferOccurSumList = new ArrayList<ScmCostTransferOccurSum>();
        String currentOrgUnitNo=request.getParameter("currentOrgUnitNo");
        String currentControlUnitNo=request.getParameter("currentControlUnitNo");
        String costOrgUnitNo=request.getParameter("costOrgUnitNo");
        String beginDate=request.getParameter("beginDate");
        String endDate=request.getParameter("endDate");
        String materialClassName=request.getParameter("materialClassName");
        String materialName=request.getParameter("materialName");
        String purInwarehs=request.getParameter("purInwarehs");
        String purInwarehsReturn=request.getParameter("purInwarehsReturn");
        String materialReq=request.getParameter("materialReq");
        String materialReqReturn=request.getParameter("materialReqReturn");
        String moveIn=request.getParameter("moveIn");
        String moveOut=request.getParameter("moveOut");
        String costConsume=request.getParameter("costConsume");
        String costConsume2=request.getParameter("costConsume2");
        String counting=request.getParameter("counting");
        if(StringUtils.isBlank(beginDate) || StringUtils.isBlank(endDate))
            return scmCostTransferOccurSumList;
        if(StringUtils.isBlank(costOrgUnitNo))
            costOrgUnitNo = currentOrgUnitNo;
        Param param = new Param();
        param.setOrgUnitNo(currentOrgUnitNo);
        param.setControlUnitNo(currentControlUnitNo);
        StringBuffer costOrgUnitNos = new StringBuffer("");
        List<OrgCostCenter2> orgCostCenterList = orgCostCenterBiz.findChild(costOrgUnitNo, param);
        if(orgCostCenterList!=null && !orgCostCenterList.isEmpty()) {
            for(OrgCostCenter2 orgCostCenter:orgCostCenterList) {
                if(StringUtils.isNotBlank(costOrgUnitNos.toString()))
                    costOrgUnitNos.append(",");
                costOrgUnitNos.append("'").append(orgCostCenter.getOrgUnitNo()).append("'");
            }
        }else {
            costOrgUnitNos.append("'").append(costOrgUnitNo).append("'");
        }
        int index = 0;
        List<String> intervalBizType = new ArrayList<String>();
        //入库单
        Page page = new Page();
        page.setModelClass(ScmInvPurInWarehsBillEntry2.class);
        page.setShowCount(Integer.MAX_VALUE);
        if(StringUtils.isNotBlank(materialClassName)) {
            page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmMaterialGroup.class)+"."+ScmMaterialGroup.FN_CLASSCODE, 
                    new QueryParam(ClassUtils.getFinalModelSimpleName(ScmMaterialGroup.class)+"."+ScmMaterialGroup.FN_CLASSCODE, QueryParam.QUERY_EQ, materialClassName));
        }
        if(StringUtils.isNotBlank(materialName)) {
            page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBillEntry2.class)+"."+ScmInvPurInWarehsBillEntry2.FN_ITEMID, 
                    new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBillEntry2.class)+"."+ScmInvPurInWarehsBillEntry2.FN_ITEMID, QueryParam.QUERY_EQ, materialName));
        }
        page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBillEntry2.class)+"."+ScmInvPurInWarehsBillEntry2.FN_COSTORGUNITNO, 
                new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBillEntry2.class)+"."+ScmInvPurInWarehsBillEntry2.FN_COSTORGUNITNO, QueryParam.QUERY_IN, costOrgUnitNos.toString()));
        page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBill2.class)+"."+ScmInvPurInWarehsBill2.FN_BIZDATE, 
                new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBill2.class)+"."+ScmInvPurInWarehsBill2.FN_BIZDATE, QueryParam.QUERY_BETWEEN, beginDate,endDate));
        page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBill2.class)+"."+ScmInvPurInWarehsBill2.FN_STATUS, 
                new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBill2.class)+"."+ScmInvPurInWarehsBill2.FN_STATUS, QueryParam.QUERY_EQ, "E"));
        if (StringUtils.equals("Y", purInwarehs) && !StringUtils.equals("Y", purInwarehsReturn)) {
            page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBill2.class)+"."+ScmInvPurInWarehsBill2.FN_BIZTYPE, 
                    new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBill2.class)+"."+ScmInvPurInWarehsBill2.FN_BIZTYPE, QueryParam.QUERY_IN, "1,2,3,4,5"));
        }
        if (!StringUtils.equals("Y", purInwarehs) && StringUtils.equals("Y", purInwarehsReturn)) {
            page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBill2.class)+"."+ScmInvPurInWarehsBill2.FN_BIZTYPE, 
                    new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBill2.class)+"."+ScmInvPurInWarehsBill2.FN_BIZTYPE, QueryParam.QUERY_IN, "6,7,8,9,10"));
        }
        List<String> arglist = new ArrayList<>();
        arglist.add("controlUnitNo="+currentControlUnitNo);
        if (StringUtils.equals("Y", purInwarehs) || StringUtils.equals("Y", purInwarehsReturn) || StringUtils.equals("Y", costConsume2)) {
            List<ScmInvPurInWarehsBillEntry2> scmInvPurInWarehsBillEntryList = scmInvPurInWarehsBillEntryBiz.queryPage(page, arglist, "findAllPage", param);
            if(scmInvPurInWarehsBillEntryList!=null && !scmInvPurInWarehsBillEntryList.isEmpty()) {
                List<ScmInvPurInWarehsBillEntry2> scmInvPurInWarehsBillEntryListIn = new ArrayList<ScmInvPurInWarehsBillEntry2>();
                List<ScmInvPurInWarehsBillEntry2> scmInvPurInWarehsBillEntryListReturn = new ArrayList<ScmInvPurInWarehsBillEntry2>();
                String[] bizTypeIn={"1","2","3","4","5"};
                List<String> bizTypeListIn = Arrays.asList(bizTypeIn);
                String[] bizTypeReturn={"6","7","8","9","10"};
                List<String> bizTypeListReturn = Arrays.asList(bizTypeReturn);
                for (ScmInvPurInWarehsBillEntry2 scmInvPurInWarehsBillEntry : scmInvPurInWarehsBillEntryList) {
                    if (bizTypeListIn.contains(scmInvPurInWarehsBillEntry.getBizType())) {
                        scmInvPurInWarehsBillEntryListIn.add(scmInvPurInWarehsBillEntry);
                    } else if (bizTypeListReturn.contains(scmInvPurInWarehsBillEntry.getBizType())) {
                        scmInvPurInWarehsBillEntryListReturn.add(scmInvPurInWarehsBillEntry);
                    }
                }
                if (scmInvPurInWarehsBillEntryListIn != null && scmInvPurInWarehsBillEntryListIn.size() > 0) {
                    // 采购入库
                    if (StringUtils.equals("Y", purInwarehs)) {
                        intervalBizType.add(index, "采购入库");
                        index = index + 1;
                        for (ScmInvPurInWarehsBillEntry2 scmInvPurInWarehsBillEntry : scmInvPurInWarehsBillEntryListIn) {
                            boolean exists=false;
                            if(!scmCostTransferOccurSumList.isEmpty()) {
                                for (ScmCostTransferOccurSum existScmCostTransferOccurSum:scmCostTransferOccurSumList) {
                                    if (existScmCostTransferOccurSum.getItemId() == scmInvPurInWarehsBillEntry.getItemId() && StringUtils.equals(
                                            existScmCostTransferOccurSum.getCostOrgUnitNo(), scmInvPurInWarehsBillEntry.getCostOrgUnitNo())) {
                                        switch (index) {
                                        case 1:
                                            existScmCostTransferOccurSum.setQty(existScmCostTransferOccurSum.getQty().add(scmInvPurInWarehsBillEntry.getQty()));
                                            existScmCostTransferOccurSum.setAmt(existScmCostTransferOccurSum.getAmt().add(scmInvPurInWarehsBillEntry.getAmt()));
                                            existScmCostTransferOccurSum.setTaxAmt(existScmCostTransferOccurSum.getTaxAmt().add(scmInvPurInWarehsBillEntry.getTaxAmt()));
                                            exists = true;
                                            break;
                                        case 2:
                                            existScmCostTransferOccurSum.setQty_1(existScmCostTransferOccurSum.getQty_1().add(scmInvPurInWarehsBillEntry.getQty()));
                                            existScmCostTransferOccurSum.setAmt_1(existScmCostTransferOccurSum.getAmt_1().add(scmInvPurInWarehsBillEntry.getAmt()));
                                            existScmCostTransferOccurSum.setTaxAmt_1(existScmCostTransferOccurSum.getTaxAmt_1().add(scmInvPurInWarehsBillEntry.getTaxAmt()));
                                            exists = true;
                                            break;
                                        case 3:
                                            existScmCostTransferOccurSum.setQty_2(existScmCostTransferOccurSum.getQty_2().add(scmInvPurInWarehsBillEntry.getQty()));
                                            existScmCostTransferOccurSum.setAmt_2(existScmCostTransferOccurSum.getAmt_2().add(scmInvPurInWarehsBillEntry.getAmt()));
                                            existScmCostTransferOccurSum.setTaxAmt_2(existScmCostTransferOccurSum.getTaxAmt_2().add(scmInvPurInWarehsBillEntry.getTaxAmt()));
                                            exists = true;
                                            break;                                    
                                        case 4:
                                            existScmCostTransferOccurSum.setQty_3(existScmCostTransferOccurSum.getQty_3().add(scmInvPurInWarehsBillEntry.getQty()));
                                            existScmCostTransferOccurSum.setAmt_3(existScmCostTransferOccurSum.getAmt_3().add(scmInvPurInWarehsBillEntry.getAmt()));
                                            existScmCostTransferOccurSum.setTaxAmt_3(existScmCostTransferOccurSum.getTaxAmt_3().add(scmInvPurInWarehsBillEntry.getTaxAmt()));
                                            exists = true;
                                            break;                                    
                                        case 5:
                                            existScmCostTransferOccurSum.setQty_4(existScmCostTransferOccurSum.getQty_4().add(scmInvPurInWarehsBillEntry.getQty()));
                                            existScmCostTransferOccurSum.setAmt_4(existScmCostTransferOccurSum.getAmt_4().add(scmInvPurInWarehsBillEntry.getAmt()));
                                            existScmCostTransferOccurSum.setTaxAmt_4(existScmCostTransferOccurSum.getTaxAmt_4().add(scmInvPurInWarehsBillEntry.getTaxAmt()));
                                            exists = true;
                                            break;
                                        case 6:
                                            existScmCostTransferOccurSum.setQty_5(existScmCostTransferOccurSum.getQty_5().add(scmInvPurInWarehsBillEntry.getQty()));
                                            existScmCostTransferOccurSum.setAmt_5(existScmCostTransferOccurSum.getAmt_5().add(scmInvPurInWarehsBillEntry.getAmt()));
                                            existScmCostTransferOccurSum.setTaxAmt_5(existScmCostTransferOccurSum.getTaxAmt_5().add(scmInvPurInWarehsBillEntry.getTaxAmt()));
                                            exists = true;
                                            break;                                    
                                        case 7:
                                            existScmCostTransferOccurSum.setQty_6(existScmCostTransferOccurSum.getQty_6().add(scmInvPurInWarehsBillEntry.getQty()));
                                            existScmCostTransferOccurSum.setAmt_6(existScmCostTransferOccurSum.getAmt_6().add(scmInvPurInWarehsBillEntry.getAmt()));
                                            existScmCostTransferOccurSum.setTaxAmt_6(existScmCostTransferOccurSum.getTaxAmt_6().add(scmInvPurInWarehsBillEntry.getTaxAmt()));
                                            exists = true;
                                            break;                                    
                                        case 8:
                                            existScmCostTransferOccurSum.setQty_7(existScmCostTransferOccurSum.getQty_7().add(scmInvPurInWarehsBillEntry.getQty()));
                                            existScmCostTransferOccurSum.setAmt_7(existScmCostTransferOccurSum.getAmt_7().add(scmInvPurInWarehsBillEntry.getAmt()));
                                            existScmCostTransferOccurSum.setTaxAmt_7(existScmCostTransferOccurSum.getTaxAmt_7().add(scmInvPurInWarehsBillEntry.getTaxAmt()));
                                            exists = true;
                                            break;                                    
                                        case 9:
                                            existScmCostTransferOccurSum.setQty_8(existScmCostTransferOccurSum.getQty_8().add(scmInvPurInWarehsBillEntry.getQty()));
                                            existScmCostTransferOccurSum.setAmt_8(existScmCostTransferOccurSum.getAmt_8().add(scmInvPurInWarehsBillEntry.getAmt()));
                                            existScmCostTransferOccurSum.setTaxAmt_8(existScmCostTransferOccurSum.getTaxAmt_8().add(scmInvPurInWarehsBillEntry.getTaxAmt()));
                                            exists = true;
                                            break;                                    
                                        case 10:
                                            existScmCostTransferOccurSum.setQty_9(existScmCostTransferOccurSum.getQty_9().add(scmInvPurInWarehsBillEntry.getQty()));
                                            existScmCostTransferOccurSum.setAmt_9(existScmCostTransferOccurSum.getAmt_9().add(scmInvPurInWarehsBillEntry.getAmt()));
                                            existScmCostTransferOccurSum.setTaxAmt_9(existScmCostTransferOccurSum.getTaxAmt_9().add(scmInvPurInWarehsBillEntry.getTaxAmt()));
                                            exists = true;
                                            break;                                    
                                        default:
                                            break;
                                        }
                                    }
                                }
                            }
                            if(!exists) {
                                ScmCostTransferOccurSum scmCostTransferOccurSum = new ScmCostTransferOccurSum(true);
                                scmCostTransferOccurSum.setClassName(scmInvPurInWarehsBillEntry.getGroupName());
                                scmCostTransferOccurSum.setItemId(scmInvPurInWarehsBillEntry.getItemId());
                                scmCostTransferOccurSum.setItemNo(scmInvPurInWarehsBillEntry.getItemNo());
                                scmCostTransferOccurSum.setItemName(scmInvPurInWarehsBillEntry.getItemName());
                                scmCostTransferOccurSum.setSpec(scmInvPurInWarehsBillEntry.getSpec());
                                scmCostTransferOccurSum.setUnit(scmInvPurInWarehsBillEntry.getUnit());
                                scmCostTransferOccurSum.setUnitName(scmInvPurInWarehsBillEntry.getUnitName());
                                scmCostTransferOccurSum.setCostOrgUnitNo(scmInvPurInWarehsBillEntry.getCostOrgUnitNo());
                                switch (index) {
                                case 1:
                                    scmCostTransferOccurSum.setQty(scmCostTransferOccurSum.getQty().add(scmInvPurInWarehsBillEntry.getQty()));
                                    scmCostTransferOccurSum.setAmt(scmCostTransferOccurSum.getAmt().add(scmInvPurInWarehsBillEntry.getAmt()));
                                    scmCostTransferOccurSum.setTaxAmt(scmCostTransferOccurSum.getTaxAmt().add(scmInvPurInWarehsBillEntry.getTaxAmt()));
                                    break;
                                case 2:
                                    scmCostTransferOccurSum.setQty_1(scmCostTransferOccurSum.getQty_1().add(scmInvPurInWarehsBillEntry.getQty()));
                                    scmCostTransferOccurSum.setAmt_1(scmCostTransferOccurSum.getAmt_1().add(scmInvPurInWarehsBillEntry.getAmt()));
                                    scmCostTransferOccurSum.setTaxAmt_1(scmCostTransferOccurSum.getTaxAmt_1().add(scmInvPurInWarehsBillEntry.getTaxAmt()));
                                    break;                                    
                                case 3:
                                    scmCostTransferOccurSum.setQty_2(scmCostTransferOccurSum.getQty_2().add(scmInvPurInWarehsBillEntry.getQty()));
                                    scmCostTransferOccurSum.setAmt_2(scmCostTransferOccurSum.getAmt_2().add(scmInvPurInWarehsBillEntry.getAmt()));
                                    scmCostTransferOccurSum.setTaxAmt_2(scmCostTransferOccurSum.getTaxAmt_2().add(scmInvPurInWarehsBillEntry.getTaxAmt()));
                                    break;                                    
                                case 4:
                                    scmCostTransferOccurSum.setQty_3(scmCostTransferOccurSum.getQty_3().add(scmInvPurInWarehsBillEntry.getQty()));
                                    scmCostTransferOccurSum.setAmt_3(scmCostTransferOccurSum.getAmt_3().add(scmInvPurInWarehsBillEntry.getAmt()));
                                    scmCostTransferOccurSum.setTaxAmt_3(scmCostTransferOccurSum.getTaxAmt_3().add(scmInvPurInWarehsBillEntry.getTaxAmt()));
                                    break;
                                case 5:
                                    scmCostTransferOccurSum.setQty_4(scmCostTransferOccurSum.getQty_4().add(scmInvPurInWarehsBillEntry.getQty()));
                                    scmCostTransferOccurSum.setAmt_4(scmCostTransferOccurSum.getAmt_4().add(scmInvPurInWarehsBillEntry.getAmt()));
                                    scmCostTransferOccurSum.setTaxAmt_4(scmCostTransferOccurSum.getTaxAmt_4().add(scmInvPurInWarehsBillEntry.getTaxAmt()));
                                    break;                                    
                                case 6:
                                    scmCostTransferOccurSum.setQty_5(scmCostTransferOccurSum.getQty_5().add(scmInvPurInWarehsBillEntry.getQty()));
                                    scmCostTransferOccurSum.setAmt_5(scmCostTransferOccurSum.getAmt_5().add(scmInvPurInWarehsBillEntry.getAmt()));
                                    scmCostTransferOccurSum.setTaxAmt_5(scmCostTransferOccurSum.getTaxAmt_5().add(scmInvPurInWarehsBillEntry.getTaxAmt()));
                                    break;                                    
                                case 7:
                                    scmCostTransferOccurSum.setQty_6(scmCostTransferOccurSum.getQty_6().add(scmInvPurInWarehsBillEntry.getQty()));
                                    scmCostTransferOccurSum.setAmt_6(scmCostTransferOccurSum.getAmt_6().add(scmInvPurInWarehsBillEntry.getAmt()));
                                    scmCostTransferOccurSum.setTaxAmt_6(scmCostTransferOccurSum.getTaxAmt_6().add(scmInvPurInWarehsBillEntry.getTaxAmt()));
                                    break;                                    
                                case 8:
                                    scmCostTransferOccurSum.setQty_7(scmCostTransferOccurSum.getQty_7().add(scmInvPurInWarehsBillEntry.getQty()));
                                    scmCostTransferOccurSum.setAmt_7(scmCostTransferOccurSum.getAmt_7().add(scmInvPurInWarehsBillEntry.getAmt()));
                                    scmCostTransferOccurSum.setTaxAmt_7(scmCostTransferOccurSum.getTaxAmt_7().add(scmInvPurInWarehsBillEntry.getTaxAmt()));
                                    break;                                    
                                case 9:
                                    scmCostTransferOccurSum.setQty_8(scmCostTransferOccurSum.getQty_8().add(scmInvPurInWarehsBillEntry.getQty()));
                                    scmCostTransferOccurSum.setAmt_8(scmCostTransferOccurSum.getAmt_8().add(scmInvPurInWarehsBillEntry.getAmt()));
                                    scmCostTransferOccurSum.setTaxAmt_8(scmCostTransferOccurSum.getTaxAmt_8().add(scmInvPurInWarehsBillEntry.getTaxAmt()));
                                    break;                                    
                                case 10:
                                    scmCostTransferOccurSum.setQty_9(scmCostTransferOccurSum.getQty_9().add(scmInvPurInWarehsBillEntry.getQty()));
                                    scmCostTransferOccurSum.setAmt_9(scmCostTransferOccurSum.getAmt_9().add(scmInvPurInWarehsBillEntry.getAmt()));
                                    scmCostTransferOccurSum.setTaxAmt_9(scmCostTransferOccurSum.getTaxAmt_9().add(scmInvPurInWarehsBillEntry.getTaxAmt()));
                                    break;                                    
                                default:
                                    break;
                                }
                                scmCostTransferOccurSumList.add(scmCostTransferOccurSum);
                            }                    
                        }
                    }
                    // 以领代耗
                    if (StringUtils.equals("Y", costConsume2)) {
                        intervalBizType.add(index, "以领代耗");
                        index = index + 1;
                        for (ScmInvPurInWarehsBillEntry2 scmInvPurInWarehsBillEntry : scmInvPurInWarehsBillEntryListIn) {
                        	OrgCostCenter2 orgCostCenter = orgCostCenterBiz.selectByOrgUnitNo(scmInvPurInWarehsBillEntry.getCostOrgUnitNo(), param);
                        	if(orgCostCenter!=null && StringUtils.equals("2", orgCostCenter.getCostCenterType())) {
    							continue;
    						}
                            boolean exists=false;
                            if(!scmCostTransferOccurSumList.isEmpty()) {
                                for (ScmCostTransferOccurSum existScmCostTransferOccurSum:scmCostTransferOccurSumList) {
                                    if (existScmCostTransferOccurSum.getItemId() == scmInvPurInWarehsBillEntry.getItemId() && StringUtils.equals(
                                            existScmCostTransferOccurSum.getCostOrgUnitNo(), scmInvPurInWarehsBillEntry.getCostOrgUnitNo())) {
                                        switch (index) {
                                        case 1:
                                            existScmCostTransferOccurSum.setQty(existScmCostTransferOccurSum.getQty().add(scmInvPurInWarehsBillEntry.getQty()));
                                            existScmCostTransferOccurSum.setAmt(existScmCostTransferOccurSum.getAmt().add(scmInvPurInWarehsBillEntry.getAmt()));
                                            existScmCostTransferOccurSum.setTaxAmt(existScmCostTransferOccurSum.getTaxAmt().add(scmInvPurInWarehsBillEntry.getTaxAmt()));
                                            exists = true;
                                            break;
                                        case 2:
                                            existScmCostTransferOccurSum.setQty_1(existScmCostTransferOccurSum.getQty_1().add(scmInvPurInWarehsBillEntry.getQty()));
                                            existScmCostTransferOccurSum.setAmt_1(existScmCostTransferOccurSum.getAmt_1().add(scmInvPurInWarehsBillEntry.getAmt()));
                                            existScmCostTransferOccurSum.setTaxAmt_1(existScmCostTransferOccurSum.getTaxAmt_1().add(scmInvPurInWarehsBillEntry.getTaxAmt()));
                                            exists = true;
                                            break;
                                        case 3:
                                            existScmCostTransferOccurSum.setQty_2(existScmCostTransferOccurSum.getQty_2().add(scmInvPurInWarehsBillEntry.getQty()));
                                            existScmCostTransferOccurSum.setAmt_2(existScmCostTransferOccurSum.getAmt_2().add(scmInvPurInWarehsBillEntry.getAmt()));
                                            existScmCostTransferOccurSum.setTaxAmt_2(existScmCostTransferOccurSum.getTaxAmt_2().add(scmInvPurInWarehsBillEntry.getTaxAmt()));
                                            exists = true;
                                            break;                                    
                                        case 4:
                                            existScmCostTransferOccurSum.setQty_3(existScmCostTransferOccurSum.getQty_3().add(scmInvPurInWarehsBillEntry.getQty()));
                                            existScmCostTransferOccurSum.setAmt_3(existScmCostTransferOccurSum.getAmt_3().add(scmInvPurInWarehsBillEntry.getAmt()));
                                            existScmCostTransferOccurSum.setTaxAmt_3(existScmCostTransferOccurSum.getTaxAmt_3().add(scmInvPurInWarehsBillEntry.getTaxAmt()));
                                            exists = true;
                                            break;                                    
                                        case 5:
                                            existScmCostTransferOccurSum.setQty_4(existScmCostTransferOccurSum.getQty_4().add(scmInvPurInWarehsBillEntry.getQty()));
                                            existScmCostTransferOccurSum.setAmt_4(existScmCostTransferOccurSum.getAmt_4().add(scmInvPurInWarehsBillEntry.getAmt()));
                                            existScmCostTransferOccurSum.setTaxAmt_4(existScmCostTransferOccurSum.getTaxAmt_4().add(scmInvPurInWarehsBillEntry.getTaxAmt()));
                                            exists = true;
                                            break;
                                        case 6:
                                            existScmCostTransferOccurSum.setQty_5(existScmCostTransferOccurSum.getQty_5().add(scmInvPurInWarehsBillEntry.getQty()));
                                            existScmCostTransferOccurSum.setAmt_5(existScmCostTransferOccurSum.getAmt_5().add(scmInvPurInWarehsBillEntry.getAmt()));
                                            existScmCostTransferOccurSum.setTaxAmt_5(existScmCostTransferOccurSum.getTaxAmt_5().add(scmInvPurInWarehsBillEntry.getTaxAmt()));
                                            exists = true;
                                            break;                                    
                                        case 7:
                                            existScmCostTransferOccurSum.setQty_6(existScmCostTransferOccurSum.getQty_6().add(scmInvPurInWarehsBillEntry.getQty()));
                                            existScmCostTransferOccurSum.setAmt_6(existScmCostTransferOccurSum.getAmt_6().add(scmInvPurInWarehsBillEntry.getAmt()));
                                            existScmCostTransferOccurSum.setTaxAmt_6(existScmCostTransferOccurSum.getTaxAmt_6().add(scmInvPurInWarehsBillEntry.getTaxAmt()));
                                            exists = true;
                                            break;                                    
                                        case 8:
                                            existScmCostTransferOccurSum.setQty_7(existScmCostTransferOccurSum.getQty_7().add(scmInvPurInWarehsBillEntry.getQty()));
                                            existScmCostTransferOccurSum.setAmt_7(existScmCostTransferOccurSum.getAmt_7().add(scmInvPurInWarehsBillEntry.getAmt()));
                                            existScmCostTransferOccurSum.setTaxAmt_7(existScmCostTransferOccurSum.getTaxAmt_7().add(scmInvPurInWarehsBillEntry.getTaxAmt()));
                                            exists = true;
                                            break;                                    
                                        case 9:
                                            existScmCostTransferOccurSum.setQty_8(existScmCostTransferOccurSum.getQty_8().add(scmInvPurInWarehsBillEntry.getQty()));
                                            existScmCostTransferOccurSum.setAmt_8(existScmCostTransferOccurSum.getAmt_8().add(scmInvPurInWarehsBillEntry.getAmt()));
                                            existScmCostTransferOccurSum.setTaxAmt_8(existScmCostTransferOccurSum.getTaxAmt_8().add(scmInvPurInWarehsBillEntry.getTaxAmt()));
                                            exists = true;
                                            break;                                    
                                        case 10:
                                            existScmCostTransferOccurSum.setQty_9(existScmCostTransferOccurSum.getQty_9().add(scmInvPurInWarehsBillEntry.getQty()));
                                            existScmCostTransferOccurSum.setAmt_9(existScmCostTransferOccurSum.getAmt_9().add(scmInvPurInWarehsBillEntry.getAmt()));
                                            existScmCostTransferOccurSum.setTaxAmt_9(existScmCostTransferOccurSum.getTaxAmt_9().add(scmInvPurInWarehsBillEntry.getTaxAmt()));
                                            exists = true;
                                            break;                                    
                                        default:
                                            break;
                                        }
                                    }
                                }
                            }
                            if(!exists) {
                                ScmCostTransferOccurSum scmCostTransferOccurSum = new ScmCostTransferOccurSum(true);
                                scmCostTransferOccurSum.setClassName(scmInvPurInWarehsBillEntry.getGroupName());
                                scmCostTransferOccurSum.setItemId(scmInvPurInWarehsBillEntry.getItemId());
                                scmCostTransferOccurSum.setItemNo(scmInvPurInWarehsBillEntry.getItemNo());
                                scmCostTransferOccurSum.setItemName(scmInvPurInWarehsBillEntry.getItemName());
                                scmCostTransferOccurSum.setSpec(scmInvPurInWarehsBillEntry.getSpec());
                                scmCostTransferOccurSum.setUnit(scmInvPurInWarehsBillEntry.getUnit());
                                scmCostTransferOccurSum.setUnitName(scmInvPurInWarehsBillEntry.getUnitName());
                                scmCostTransferOccurSum.setCostOrgUnitNo(scmInvPurInWarehsBillEntry.getCostOrgUnitNo());
                                switch (index) {
                                case 1:
                                    scmCostTransferOccurSum.setQty(scmCostTransferOccurSum.getQty().add(scmInvPurInWarehsBillEntry.getQty()));
                                    scmCostTransferOccurSum.setAmt(scmCostTransferOccurSum.getAmt().add(scmInvPurInWarehsBillEntry.getAmt()));
                                    scmCostTransferOccurSum.setTaxAmt(scmCostTransferOccurSum.getTaxAmt().add(scmInvPurInWarehsBillEntry.getTaxAmt()));
                                    break;
                                case 2:
                                    scmCostTransferOccurSum.setQty_1(scmCostTransferOccurSum.getQty_1().add(scmInvPurInWarehsBillEntry.getQty()));
                                    scmCostTransferOccurSum.setAmt_1(scmCostTransferOccurSum.getAmt_1().add(scmInvPurInWarehsBillEntry.getAmt()));
                                    scmCostTransferOccurSum.setTaxAmt_1(scmCostTransferOccurSum.getTaxAmt_1().add(scmInvPurInWarehsBillEntry.getTaxAmt()));
                                    break;                                    
                                case 3:
                                    scmCostTransferOccurSum.setQty_2(scmCostTransferOccurSum.getQty_2().add(scmInvPurInWarehsBillEntry.getQty()));
                                    scmCostTransferOccurSum.setAmt_2(scmCostTransferOccurSum.getAmt_2().add(scmInvPurInWarehsBillEntry.getAmt()));
                                    scmCostTransferOccurSum.setTaxAmt_2(scmCostTransferOccurSum.getTaxAmt_2().add(scmInvPurInWarehsBillEntry.getTaxAmt()));
                                    break;                                    
                                case 4:
                                    scmCostTransferOccurSum.setQty_3(scmCostTransferOccurSum.getQty_3().add(scmInvPurInWarehsBillEntry.getQty()));
                                    scmCostTransferOccurSum.setAmt_3(scmCostTransferOccurSum.getAmt_3().add(scmInvPurInWarehsBillEntry.getAmt()));
                                    scmCostTransferOccurSum.setTaxAmt_3(scmCostTransferOccurSum.getTaxAmt_3().add(scmInvPurInWarehsBillEntry.getTaxAmt()));
                                    break;
                                case 5:
                                    scmCostTransferOccurSum.setQty_4(scmCostTransferOccurSum.getQty_4().add(scmInvPurInWarehsBillEntry.getQty()));
                                    scmCostTransferOccurSum.setAmt_4(scmCostTransferOccurSum.getAmt_4().add(scmInvPurInWarehsBillEntry.getAmt()));
                                    scmCostTransferOccurSum.setTaxAmt_4(scmCostTransferOccurSum.getTaxAmt_4().add(scmInvPurInWarehsBillEntry.getTaxAmt()));
                                    break;                                    
                                case 6:
                                    scmCostTransferOccurSum.setQty_5(scmCostTransferOccurSum.getQty_5().add(scmInvPurInWarehsBillEntry.getQty()));
                                    scmCostTransferOccurSum.setAmt_5(scmCostTransferOccurSum.getAmt_5().add(scmInvPurInWarehsBillEntry.getAmt()));
                                    scmCostTransferOccurSum.setTaxAmt_5(scmCostTransferOccurSum.getTaxAmt_5().add(scmInvPurInWarehsBillEntry.getTaxAmt()));
                                    break;                                    
                                case 7:
                                    scmCostTransferOccurSum.setQty_6(scmCostTransferOccurSum.getQty_6().add(scmInvPurInWarehsBillEntry.getQty()));
                                    scmCostTransferOccurSum.setAmt_6(scmCostTransferOccurSum.getAmt_6().add(scmInvPurInWarehsBillEntry.getAmt()));
                                    scmCostTransferOccurSum.setTaxAmt_6(scmCostTransferOccurSum.getTaxAmt_6().add(scmInvPurInWarehsBillEntry.getTaxAmt()));
                                    break;                                    
                                case 8:
                                    scmCostTransferOccurSum.setQty_7(scmCostTransferOccurSum.getQty_7().add(scmInvPurInWarehsBillEntry.getQty()));
                                    scmCostTransferOccurSum.setAmt_7(scmCostTransferOccurSum.getAmt_7().add(scmInvPurInWarehsBillEntry.getAmt()));
                                    scmCostTransferOccurSum.setTaxAmt_7(scmCostTransferOccurSum.getTaxAmt_7().add(scmInvPurInWarehsBillEntry.getTaxAmt()));
                                    break;                                    
                                case 9:
                                    scmCostTransferOccurSum.setQty_8(scmCostTransferOccurSum.getQty_8().add(scmInvPurInWarehsBillEntry.getQty()));
                                    scmCostTransferOccurSum.setAmt_8(scmCostTransferOccurSum.getAmt_8().add(scmInvPurInWarehsBillEntry.getAmt()));
                                    scmCostTransferOccurSum.setTaxAmt_8(scmCostTransferOccurSum.getTaxAmt_8().add(scmInvPurInWarehsBillEntry.getTaxAmt()));
                                    break;                                    
                                case 10:
                                    scmCostTransferOccurSum.setQty_9(scmCostTransferOccurSum.getQty_9().add(scmInvPurInWarehsBillEntry.getQty()));
                                    scmCostTransferOccurSum.setAmt_9(scmCostTransferOccurSum.getAmt_9().add(scmInvPurInWarehsBillEntry.getAmt()));
                                    scmCostTransferOccurSum.setTaxAmt_9(scmCostTransferOccurSum.getTaxAmt_9().add(scmInvPurInWarehsBillEntry.getTaxAmt()));
                                    break;                                    
                                default:
                                    break;
                                }
                                scmCostTransferOccurSumList.add(scmCostTransferOccurSum);
                            }                    
                        }
                    }
                
                }
                if (scmInvPurInWarehsBillEntryListReturn != null && scmInvPurInWarehsBillEntryListReturn.size() > 0) {
                    // 采购入库（退货）
                    if (StringUtils.equals("Y", purInwarehsReturn)) {
                        intervalBizType.add(index, "采购入库（退货）");
                        index = index + 1;
                        for (ScmInvPurInWarehsBillEntry2 scmInvPurInWarehsBillEntry : scmInvPurInWarehsBillEntryListReturn) {
                            boolean exists=false;
                            if(!scmCostTransferOccurSumList.isEmpty()) {
                                for (ScmCostTransferOccurSum existScmCostTransferOccurSum:scmCostTransferOccurSumList) {
                                    if (existScmCostTransferOccurSum.getItemId() == scmInvPurInWarehsBillEntry.getItemId() && StringUtils.equals(
                                            existScmCostTransferOccurSum.getCostOrgUnitNo(), scmInvPurInWarehsBillEntry.getCostOrgUnitNo())) {
                                        switch (index) {
                                        case 1:
                                            existScmCostTransferOccurSum.setQty(existScmCostTransferOccurSum.getQty().add(scmInvPurInWarehsBillEntry.getQty()));
                                            existScmCostTransferOccurSum.setAmt(existScmCostTransferOccurSum.getAmt().add(scmInvPurInWarehsBillEntry.getAmt()));
                                            existScmCostTransferOccurSum.setTaxAmt(existScmCostTransferOccurSum.getTaxAmt().add(scmInvPurInWarehsBillEntry.getTaxAmt()));
                                            exists = true;
                                            break;
                                        case 2:
                                            existScmCostTransferOccurSum.setQty_1(existScmCostTransferOccurSum.getQty_1().add(scmInvPurInWarehsBillEntry.getQty()));
                                            existScmCostTransferOccurSum.setAmt_1(existScmCostTransferOccurSum.getAmt_1().add(scmInvPurInWarehsBillEntry.getAmt()));
                                            existScmCostTransferOccurSum.setTaxAmt_1(existScmCostTransferOccurSum.getTaxAmt_1().add(scmInvPurInWarehsBillEntry.getTaxAmt()));
                                            exists = true;
                                            break;
                                        case 3:
                                            existScmCostTransferOccurSum.setQty_2(existScmCostTransferOccurSum.getQty_2().add(scmInvPurInWarehsBillEntry.getQty()));
                                            existScmCostTransferOccurSum.setAmt_2(existScmCostTransferOccurSum.getAmt_2().add(scmInvPurInWarehsBillEntry.getAmt()));
                                            existScmCostTransferOccurSum.setTaxAmt_2(existScmCostTransferOccurSum.getTaxAmt_2().add(scmInvPurInWarehsBillEntry.getTaxAmt()));
                                            exists = true;
                                            break;                                    
                                        case 4:
                                            existScmCostTransferOccurSum.setQty_3(existScmCostTransferOccurSum.getQty_3().add(scmInvPurInWarehsBillEntry.getQty()));
                                            existScmCostTransferOccurSum.setAmt_3(existScmCostTransferOccurSum.getAmt_3().add(scmInvPurInWarehsBillEntry.getAmt()));
                                            existScmCostTransferOccurSum.setTaxAmt_3(existScmCostTransferOccurSum.getTaxAmt_3().add(scmInvPurInWarehsBillEntry.getTaxAmt()));
                                            exists = true;
                                            break;                                    
                                        case 5:
                                            existScmCostTransferOccurSum.setQty_4(existScmCostTransferOccurSum.getQty_4().add(scmInvPurInWarehsBillEntry.getQty()));
                                            existScmCostTransferOccurSum.setAmt_4(existScmCostTransferOccurSum.getAmt_4().add(scmInvPurInWarehsBillEntry.getAmt()));
                                            existScmCostTransferOccurSum.setTaxAmt_4(existScmCostTransferOccurSum.getTaxAmt_4().add(scmInvPurInWarehsBillEntry.getTaxAmt()));
                                            exists = true;
                                            break;
                                        case 6:
                                            existScmCostTransferOccurSum.setQty_5(existScmCostTransferOccurSum.getQty_5().add(scmInvPurInWarehsBillEntry.getQty()));
                                            existScmCostTransferOccurSum.setAmt_5(existScmCostTransferOccurSum.getAmt_5().add(scmInvPurInWarehsBillEntry.getAmt()));
                                            existScmCostTransferOccurSum.setTaxAmt_5(existScmCostTransferOccurSum.getTaxAmt_5().add(scmInvPurInWarehsBillEntry.getTaxAmt()));
                                            exists = true;
                                            break;                                    
                                        case 7:
                                            existScmCostTransferOccurSum.setQty_6(existScmCostTransferOccurSum.getQty_6().add(scmInvPurInWarehsBillEntry.getQty()));
                                            existScmCostTransferOccurSum.setAmt_6(existScmCostTransferOccurSum.getAmt_6().add(scmInvPurInWarehsBillEntry.getAmt()));
                                            existScmCostTransferOccurSum.setTaxAmt_6(existScmCostTransferOccurSum.getTaxAmt_6().add(scmInvPurInWarehsBillEntry.getTaxAmt()));
                                            exists = true;
                                            break;                                    
                                        case 8:
                                            existScmCostTransferOccurSum.setQty_7(existScmCostTransferOccurSum.getQty_7().add(scmInvPurInWarehsBillEntry.getQty()));
                                            existScmCostTransferOccurSum.setAmt_7(existScmCostTransferOccurSum.getAmt_7().add(scmInvPurInWarehsBillEntry.getAmt()));
                                            existScmCostTransferOccurSum.setTaxAmt_7(existScmCostTransferOccurSum.getTaxAmt_7().add(scmInvPurInWarehsBillEntry.getTaxAmt()));
                                            exists = true;
                                            break;                                    
                                        case 9:
                                            existScmCostTransferOccurSum.setQty_8(existScmCostTransferOccurSum.getQty_8().add(scmInvPurInWarehsBillEntry.getQty()));
                                            existScmCostTransferOccurSum.setAmt_8(existScmCostTransferOccurSum.getAmt_8().add(scmInvPurInWarehsBillEntry.getAmt()));
                                            existScmCostTransferOccurSum.setTaxAmt_8(existScmCostTransferOccurSum.getTaxAmt_8().add(scmInvPurInWarehsBillEntry.getTaxAmt()));
                                            exists = true;
                                            break;                                    
                                        case 10:
                                            existScmCostTransferOccurSum.setQty_9(existScmCostTransferOccurSum.getQty_9().add(scmInvPurInWarehsBillEntry.getQty()));
                                            existScmCostTransferOccurSum.setAmt_9(existScmCostTransferOccurSum.getAmt_9().add(scmInvPurInWarehsBillEntry.getAmt()));
                                            existScmCostTransferOccurSum.setTaxAmt_9(existScmCostTransferOccurSum.getTaxAmt_9().add(scmInvPurInWarehsBillEntry.getTaxAmt()));
                                            exists = true;
                                            break;                                    
                                        default:
                                            break;
                                        }
                                    }
                                }
                            }
                            if(!exists) {
                                ScmCostTransferOccurSum scmCostTransferOccurSum = new ScmCostTransferOccurSum(true);
                                scmCostTransferOccurSum.setClassName(scmInvPurInWarehsBillEntry.getGroupName());
                                scmCostTransferOccurSum.setItemId(scmInvPurInWarehsBillEntry.getItemId());
                                scmCostTransferOccurSum.setItemNo(scmInvPurInWarehsBillEntry.getItemNo());
                                scmCostTransferOccurSum.setItemName(scmInvPurInWarehsBillEntry.getItemName());
                                scmCostTransferOccurSum.setSpec(scmInvPurInWarehsBillEntry.getSpec());
                                scmCostTransferOccurSum.setUnit(scmInvPurInWarehsBillEntry.getUnit());
                                scmCostTransferOccurSum.setUnitName(scmInvPurInWarehsBillEntry.getUnitName());
                                scmCostTransferOccurSum.setCostOrgUnitNo(scmInvPurInWarehsBillEntry.getCostOrgUnitNo());
                                switch (index) {
                                case 1:
                                    scmCostTransferOccurSum.setQty(scmCostTransferOccurSum.getQty().add(scmInvPurInWarehsBillEntry.getQty()));
                                    scmCostTransferOccurSum.setAmt(scmCostTransferOccurSum.getAmt().add(scmInvPurInWarehsBillEntry.getAmt()));
                                    scmCostTransferOccurSum.setTaxAmt(scmCostTransferOccurSum.getTaxAmt().add(scmInvPurInWarehsBillEntry.getTaxAmt()));
                                    break;
                                case 2:
                                    scmCostTransferOccurSum.setQty_1(scmCostTransferOccurSum.getQty_1().add(scmInvPurInWarehsBillEntry.getQty()));
                                    scmCostTransferOccurSum.setAmt_1(scmCostTransferOccurSum.getAmt_1().add(scmInvPurInWarehsBillEntry.getAmt()));
                                    scmCostTransferOccurSum.setTaxAmt_1(scmCostTransferOccurSum.getTaxAmt_1().add(scmInvPurInWarehsBillEntry.getTaxAmt()));
                                    break;                                    
                                case 3:
                                    scmCostTransferOccurSum.setQty_2(scmCostTransferOccurSum.getQty_2().add(scmInvPurInWarehsBillEntry.getQty()));
                                    scmCostTransferOccurSum.setAmt_2(scmCostTransferOccurSum.getAmt_2().add(scmInvPurInWarehsBillEntry.getAmt()));
                                    scmCostTransferOccurSum.setTaxAmt_2(scmCostTransferOccurSum.getTaxAmt_2().add(scmInvPurInWarehsBillEntry.getTaxAmt()));
                                    break;                                    
                                case 4:
                                    scmCostTransferOccurSum.setQty_3(scmCostTransferOccurSum.getQty_3().add(scmInvPurInWarehsBillEntry.getQty()));
                                    scmCostTransferOccurSum.setAmt_3(scmCostTransferOccurSum.getAmt_3().add(scmInvPurInWarehsBillEntry.getAmt()));
                                    scmCostTransferOccurSum.setTaxAmt_3(scmCostTransferOccurSum.getTaxAmt_3().add(scmInvPurInWarehsBillEntry.getTaxAmt()));
                                    break;
                                case 5:
                                    scmCostTransferOccurSum.setQty_4(scmCostTransferOccurSum.getQty_4().add(scmInvPurInWarehsBillEntry.getQty()));
                                    scmCostTransferOccurSum.setAmt_4(scmCostTransferOccurSum.getAmt_4().add(scmInvPurInWarehsBillEntry.getAmt()));
                                    scmCostTransferOccurSum.setTaxAmt_4(scmCostTransferOccurSum.getTaxAmt_4().add(scmInvPurInWarehsBillEntry.getTaxAmt()));
                                    break;                                    
                                case 6:
                                    scmCostTransferOccurSum.setQty_5(scmCostTransferOccurSum.getQty_5().add(scmInvPurInWarehsBillEntry.getQty()));
                                    scmCostTransferOccurSum.setAmt_5(scmCostTransferOccurSum.getAmt_5().add(scmInvPurInWarehsBillEntry.getAmt()));
                                    scmCostTransferOccurSum.setTaxAmt_5(scmCostTransferOccurSum.getTaxAmt_5().add(scmInvPurInWarehsBillEntry.getTaxAmt()));
                                    break;                                    
                                case 7:
                                    scmCostTransferOccurSum.setQty_6(scmCostTransferOccurSum.getQty_6().add(scmInvPurInWarehsBillEntry.getQty()));
                                    scmCostTransferOccurSum.setAmt_6(scmCostTransferOccurSum.getAmt_6().add(scmInvPurInWarehsBillEntry.getAmt()));
                                    scmCostTransferOccurSum.setTaxAmt_6(scmCostTransferOccurSum.getTaxAmt_6().add(scmInvPurInWarehsBillEntry.getTaxAmt()));
                                    break;                                    
                                case 8:
                                    scmCostTransferOccurSum.setQty_7(scmCostTransferOccurSum.getQty_7().add(scmInvPurInWarehsBillEntry.getQty()));
                                    scmCostTransferOccurSum.setAmt_7(scmCostTransferOccurSum.getAmt_7().add(scmInvPurInWarehsBillEntry.getAmt()));
                                    scmCostTransferOccurSum.setTaxAmt_7(scmCostTransferOccurSum.getTaxAmt_7().add(scmInvPurInWarehsBillEntry.getTaxAmt()));
                                    break;                                    
                                case 9:
                                    scmCostTransferOccurSum.setQty_8(scmCostTransferOccurSum.getQty_8().add(scmInvPurInWarehsBillEntry.getQty()));
                                    scmCostTransferOccurSum.setAmt_8(scmCostTransferOccurSum.getAmt_8().add(scmInvPurInWarehsBillEntry.getAmt()));
                                    scmCostTransferOccurSum.setTaxAmt_8(scmCostTransferOccurSum.getTaxAmt_8().add(scmInvPurInWarehsBillEntry.getTaxAmt()));
                                    break;                                    
                                case 10:
                                    scmCostTransferOccurSum.setQty_9(scmCostTransferOccurSum.getQty_9().add(scmInvPurInWarehsBillEntry.getQty()));
                                    scmCostTransferOccurSum.setAmt_9(scmCostTransferOccurSum.getAmt_9().add(scmInvPurInWarehsBillEntry.getAmt()));
                                    scmCostTransferOccurSum.setTaxAmt_9(scmCostTransferOccurSum.getTaxAmt_9().add(scmInvPurInWarehsBillEntry.getTaxAmt()));
                                    break;                                    
                                default:
                                    break;
                                }
                                scmCostTransferOccurSumList.add(scmCostTransferOccurSum);
                            }                   
                        }
                    }
                    
                    // 以领代耗
                    if (StringUtils.equals("Y", costConsume2)) {
                    	int existIndex=-1;
                    	if(!intervalBizType.isEmpty()) {
                    		for(int i=0;i<intervalBizType.size();i++) {
                    			if(StringUtils.equals("以领代耗", intervalBizType.get(i))) {
                    				existIndex=i;
                    				break;
                    			}
                    		}
                    	}
                    	if(existIndex==-1) {
	                        intervalBizType.add(index, "以领代耗");
	                        index = index + 1;
	                        existIndex=index;
                    	}else {
                    		existIndex = existIndex + 1;
                    	}
                        for (ScmInvPurInWarehsBillEntry2 scmInvPurInWarehsBillEntry : scmInvPurInWarehsBillEntryListReturn) {
                        	OrgCostCenter2 orgCostCenter = orgCostCenterBiz.selectByOrgUnitNo(scmInvPurInWarehsBillEntry.getCostOrgUnitNo(), param);
                        	if(orgCostCenter!=null && StringUtils.equals("2", orgCostCenter.getCostCenterType())) {
    							continue;
    						}
                            boolean exists=false;
                            if(!scmCostTransferOccurSumList.isEmpty()) {
                                for (ScmCostTransferOccurSum existScmCostTransferOccurSum:scmCostTransferOccurSumList) {
                                    if (existScmCostTransferOccurSum.getItemId() == scmInvPurInWarehsBillEntry.getItemId() && StringUtils.equals(
                                            existScmCostTransferOccurSum.getCostOrgUnitNo(), scmInvPurInWarehsBillEntry.getCostOrgUnitNo())) {
                                        switch (existIndex) {
                                        case 1:
                                            existScmCostTransferOccurSum.setQty(existScmCostTransferOccurSum.getQty().subtract(scmInvPurInWarehsBillEntry.getQty()));
                                            existScmCostTransferOccurSum.setAmt(existScmCostTransferOccurSum.getAmt().subtract(scmInvPurInWarehsBillEntry.getAmt()));
                                            existScmCostTransferOccurSum.setTaxAmt(existScmCostTransferOccurSum.getTaxAmt().subtract(scmInvPurInWarehsBillEntry.getTaxAmt()));
                                            exists = true;
                                            break;
                                        case 2:
                                            existScmCostTransferOccurSum.setQty_1(existScmCostTransferOccurSum.getQty_1().subtract(scmInvPurInWarehsBillEntry.getQty()));
                                            existScmCostTransferOccurSum.setAmt_1(existScmCostTransferOccurSum.getAmt_1().subtract(scmInvPurInWarehsBillEntry.getAmt()));
                                            existScmCostTransferOccurSum.setTaxAmt_1(existScmCostTransferOccurSum.getTaxAmt_1().subtract(scmInvPurInWarehsBillEntry.getTaxAmt()));
                                            exists = true;
                                            break;
                                        case 3:
                                            existScmCostTransferOccurSum.setQty_2(existScmCostTransferOccurSum.getQty_2().subtract(scmInvPurInWarehsBillEntry.getQty()));
                                            existScmCostTransferOccurSum.setAmt_2(existScmCostTransferOccurSum.getAmt_2().subtract(scmInvPurInWarehsBillEntry.getAmt()));
                                            existScmCostTransferOccurSum.setTaxAmt_2(existScmCostTransferOccurSum.getTaxAmt_2().subtract(scmInvPurInWarehsBillEntry.getTaxAmt()));
                                            exists = true;
                                            break;                                    
                                        case 4:
                                            existScmCostTransferOccurSum.setQty_3(existScmCostTransferOccurSum.getQty_3().subtract(scmInvPurInWarehsBillEntry.getQty()));
                                            existScmCostTransferOccurSum.setAmt_3(existScmCostTransferOccurSum.getAmt_3().subtract(scmInvPurInWarehsBillEntry.getAmt()));
                                            existScmCostTransferOccurSum.setTaxAmt_3(existScmCostTransferOccurSum.getTaxAmt_3().subtract(scmInvPurInWarehsBillEntry.getTaxAmt()));
                                            exists = true;
                                            break;                                    
                                        case 5:
                                            existScmCostTransferOccurSum.setQty_4(existScmCostTransferOccurSum.getQty_4().subtract(scmInvPurInWarehsBillEntry.getQty()));
                                            existScmCostTransferOccurSum.setAmt_4(existScmCostTransferOccurSum.getAmt_4().subtract(scmInvPurInWarehsBillEntry.getAmt()));
                                            existScmCostTransferOccurSum.setTaxAmt_4(existScmCostTransferOccurSum.getTaxAmt_4().subtract(scmInvPurInWarehsBillEntry.getTaxAmt()));
                                            exists = true;
                                            break;
                                        case 6:
                                            existScmCostTransferOccurSum.setQty_5(existScmCostTransferOccurSum.getQty_5().subtract(scmInvPurInWarehsBillEntry.getQty()));
                                            existScmCostTransferOccurSum.setAmt_5(existScmCostTransferOccurSum.getAmt_5().subtract(scmInvPurInWarehsBillEntry.getAmt()));
                                            existScmCostTransferOccurSum.setTaxAmt_5(existScmCostTransferOccurSum.getTaxAmt_5().subtract(scmInvPurInWarehsBillEntry.getTaxAmt()));
                                            exists = true;
                                            break;                                    
                                        case 7:
                                            existScmCostTransferOccurSum.setQty_6(existScmCostTransferOccurSum.getQty_6().subtract(scmInvPurInWarehsBillEntry.getQty()));
                                            existScmCostTransferOccurSum.setAmt_6(existScmCostTransferOccurSum.getAmt_6().subtract(scmInvPurInWarehsBillEntry.getAmt()));
                                            existScmCostTransferOccurSum.setTaxAmt_6(existScmCostTransferOccurSum.getTaxAmt_6().subtract(scmInvPurInWarehsBillEntry.getTaxAmt()));
                                            exists = true;
                                            break;                                    
                                        case 8:
                                            existScmCostTransferOccurSum.setQty_7(existScmCostTransferOccurSum.getQty_7().subtract(scmInvPurInWarehsBillEntry.getQty()));
                                            existScmCostTransferOccurSum.setAmt_7(existScmCostTransferOccurSum.getAmt_7().subtract(scmInvPurInWarehsBillEntry.getAmt()));
                                            existScmCostTransferOccurSum.setTaxAmt_7(existScmCostTransferOccurSum.getTaxAmt_7().subtract(scmInvPurInWarehsBillEntry.getTaxAmt()));
                                            exists = true;
                                            break;                                    
                                        case 9:
                                            existScmCostTransferOccurSum.setQty_8(existScmCostTransferOccurSum.getQty_8().subtract(scmInvPurInWarehsBillEntry.getQty()));
                                            existScmCostTransferOccurSum.setAmt_8(existScmCostTransferOccurSum.getAmt_8().subtract(scmInvPurInWarehsBillEntry.getAmt()));
                                            existScmCostTransferOccurSum.setTaxAmt_8(existScmCostTransferOccurSum.getTaxAmt_8().subtract(scmInvPurInWarehsBillEntry.getTaxAmt()));
                                            exists = true;
                                            break;                                    
                                        case 10:
                                            existScmCostTransferOccurSum.setQty_9(existScmCostTransferOccurSum.getQty_9().subtract(scmInvPurInWarehsBillEntry.getQty()));
                                            existScmCostTransferOccurSum.setAmt_9(existScmCostTransferOccurSum.getAmt_9().subtract(scmInvPurInWarehsBillEntry.getAmt()));
                                            existScmCostTransferOccurSum.setTaxAmt_9(existScmCostTransferOccurSum.getTaxAmt_9().subtract(scmInvPurInWarehsBillEntry.getTaxAmt()));
                                            exists = true;
                                            break;                                    
                                        default:
                                            break;
                                        }
                                    }
                                }
                            }
                            if(!exists) {
                                ScmCostTransferOccurSum scmCostTransferOccurSum = new ScmCostTransferOccurSum(true);
                                scmCostTransferOccurSum.setClassName(scmInvPurInWarehsBillEntry.getGroupName());
                                scmCostTransferOccurSum.setItemId(scmInvPurInWarehsBillEntry.getItemId());
                                scmCostTransferOccurSum.setItemNo(scmInvPurInWarehsBillEntry.getItemNo());
                                scmCostTransferOccurSum.setItemName(scmInvPurInWarehsBillEntry.getItemName());
                                scmCostTransferOccurSum.setSpec(scmInvPurInWarehsBillEntry.getSpec());
                                scmCostTransferOccurSum.setUnit(scmInvPurInWarehsBillEntry.getUnit());
                                scmCostTransferOccurSum.setUnitName(scmInvPurInWarehsBillEntry.getUnitName());
                                scmCostTransferOccurSum.setCostOrgUnitNo(scmInvPurInWarehsBillEntry.getCostOrgUnitNo());
                                switch (index) {
                                case 1:
                                    scmCostTransferOccurSum.setQty(scmCostTransferOccurSum.getQty().subtract(scmInvPurInWarehsBillEntry.getQty()));
                                    scmCostTransferOccurSum.setAmt(scmCostTransferOccurSum.getAmt().subtract(scmInvPurInWarehsBillEntry.getAmt()));
                                    scmCostTransferOccurSum.setTaxAmt(scmCostTransferOccurSum.getTaxAmt().subtract(scmInvPurInWarehsBillEntry.getTaxAmt()));
                                    break;
                                case 2:
                                    scmCostTransferOccurSum.setQty_1(scmCostTransferOccurSum.getQty_1().subtract(scmInvPurInWarehsBillEntry.getQty()));
                                    scmCostTransferOccurSum.setAmt_1(scmCostTransferOccurSum.getAmt_1().subtract(scmInvPurInWarehsBillEntry.getAmt()));
                                    scmCostTransferOccurSum.setTaxAmt_1(scmCostTransferOccurSum.getTaxAmt_1().subtract(scmInvPurInWarehsBillEntry.getTaxAmt()));
                                    break;                                    
                                case 3:
                                    scmCostTransferOccurSum.setQty_2(scmCostTransferOccurSum.getQty_2().subtract(scmInvPurInWarehsBillEntry.getQty()));
                                    scmCostTransferOccurSum.setAmt_2(scmCostTransferOccurSum.getAmt_2().subtract(scmInvPurInWarehsBillEntry.getAmt()));
                                    scmCostTransferOccurSum.setTaxAmt_2(scmCostTransferOccurSum.getTaxAmt_2().subtract(scmInvPurInWarehsBillEntry.getTaxAmt()));
                                    break;                                    
                                case 4:
                                    scmCostTransferOccurSum.setQty_3(scmCostTransferOccurSum.getQty_3().subtract(scmInvPurInWarehsBillEntry.getQty()));
                                    scmCostTransferOccurSum.setAmt_3(scmCostTransferOccurSum.getAmt_3().subtract(scmInvPurInWarehsBillEntry.getAmt()));
                                    scmCostTransferOccurSum.setTaxAmt_3(scmCostTransferOccurSum.getTaxAmt_3().subtract(scmInvPurInWarehsBillEntry.getTaxAmt()));
                                    break;
                                case 5:
                                    scmCostTransferOccurSum.setQty_4(scmCostTransferOccurSum.getQty_4().subtract(scmInvPurInWarehsBillEntry.getQty()));
                                    scmCostTransferOccurSum.setAmt_4(scmCostTransferOccurSum.getAmt_4().subtract(scmInvPurInWarehsBillEntry.getAmt()));
                                    scmCostTransferOccurSum.setTaxAmt_4(scmCostTransferOccurSum.getTaxAmt_4().subtract(scmInvPurInWarehsBillEntry.getTaxAmt()));
                                    break;                                    
                                case 6:
                                    scmCostTransferOccurSum.setQty_5(scmCostTransferOccurSum.getQty_5().subtract(scmInvPurInWarehsBillEntry.getQty()));
                                    scmCostTransferOccurSum.setAmt_5(scmCostTransferOccurSum.getAmt_5().subtract(scmInvPurInWarehsBillEntry.getAmt()));
                                    scmCostTransferOccurSum.setTaxAmt_5(scmCostTransferOccurSum.getTaxAmt_5().subtract(scmInvPurInWarehsBillEntry.getTaxAmt()));
                                    break;                                    
                                case 7:
                                    scmCostTransferOccurSum.setQty_6(scmCostTransferOccurSum.getQty_6().subtract(scmInvPurInWarehsBillEntry.getQty()));
                                    scmCostTransferOccurSum.setAmt_6(scmCostTransferOccurSum.getAmt_6().subtract(scmInvPurInWarehsBillEntry.getAmt()));
                                    scmCostTransferOccurSum.setTaxAmt_6(scmCostTransferOccurSum.getTaxAmt_6().subtract(scmInvPurInWarehsBillEntry.getTaxAmt()));
                                    break;                                    
                                case 8:
                                    scmCostTransferOccurSum.setQty_7(scmCostTransferOccurSum.getQty_7().subtract(scmInvPurInWarehsBillEntry.getQty()));
                                    scmCostTransferOccurSum.setAmt_7(scmCostTransferOccurSum.getAmt_7().subtract(scmInvPurInWarehsBillEntry.getAmt()));
                                    scmCostTransferOccurSum.setTaxAmt_7(scmCostTransferOccurSum.getTaxAmt_7().subtract(scmInvPurInWarehsBillEntry.getTaxAmt()));
                                    break;                                    
                                case 9:
                                    scmCostTransferOccurSum.setQty_8(scmCostTransferOccurSum.getQty_8().subtract(scmInvPurInWarehsBillEntry.getQty()));
                                    scmCostTransferOccurSum.setAmt_8(scmCostTransferOccurSum.getAmt_8().subtract(scmInvPurInWarehsBillEntry.getAmt()));
                                    scmCostTransferOccurSum.setTaxAmt_8(scmCostTransferOccurSum.getTaxAmt_8().subtract(scmInvPurInWarehsBillEntry.getTaxAmt()));
                                    break;                                    
                                case 10:
                                    scmCostTransferOccurSum.setQty_9(scmCostTransferOccurSum.getQty_9().subtract(scmInvPurInWarehsBillEntry.getQty()));
                                    scmCostTransferOccurSum.setAmt_9(scmCostTransferOccurSum.getAmt_9().subtract(scmInvPurInWarehsBillEntry.getAmt()));
                                    scmCostTransferOccurSum.setTaxAmt_9(scmCostTransferOccurSum.getTaxAmt_9().subtract(scmInvPurInWarehsBillEntry.getTaxAmt()));
                                    break;                                    
                                default:
                                    break;
                                }
                                scmCostTransferOccurSumList.add(scmCostTransferOccurSum);
                            }                    
                        }
                    }
                }
            }    
        }
        //领料单
        page = new Page();
        page.setModelClass(ScmInvPurInWarehsBillEntry2.class);
        page.setShowCount(Integer.MAX_VALUE);
        if(StringUtils.isNotBlank(materialClassName)) {
            page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmMaterialGroup.class)+"."+ScmMaterialGroup.FN_CLASSCODE, 
                    new QueryParam(ClassUtils.getFinalModelSimpleName(ScmMaterialGroup.class)+"."+ScmMaterialGroup.FN_CLASSCODE, QueryParam.QUERY_EQ, materialClassName));
        }
        if(StringUtils.isNotBlank(materialName)) {
            page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBillEntry2.class)+"."+ScmInvPurInWarehsBillEntry2.FN_ITEMID, 
                    new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBillEntry2.class)+"."+ScmInvPurInWarehsBillEntry2.FN_ITEMID, QueryParam.QUERY_EQ, materialName));
        }
        page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBill.class)+"."+ScmInvMaterialReqBill.FN_COSTORGUNITNO, 
                new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBill.class)+"."+ScmInvMaterialReqBill.FN_COSTORGUNITNO, QueryParam.QUERY_IN, costOrgUnitNos.toString()));
        page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBill2.class)+"."+ScmInvMaterialReqBill2.FN_BIZDATE, 
                new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBill2.class)+"."+ScmInvMaterialReqBill2.FN_BIZDATE, QueryParam.QUERY_BETWEEN, beginDate,endDate));
        page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBill2.class)+"."+ScmInvMaterialReqBill2.FN_STATUS, 
                new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBill2.class)+"."+ScmInvMaterialReqBill2.FN_STATUS, QueryParam.QUERY_EQ, "E"));
        if (StringUtils.equals("Y", materialReq) && !StringUtils.equals("Y", materialReqReturn)) {
            page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBill2.class)+"."+ScmInvMaterialReqBill2.FN_BIZTYPE, 
                    new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBill2.class)+"."+ScmInvMaterialReqBill2.FN_BIZTYPE, QueryParam.QUERY_EQ, "1"));
        }
        if (!StringUtils.equals("Y", materialReq) && StringUtils.equals("Y", materialReqReturn)) {
            page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBill2.class)+"."+ScmInvMaterialReqBill2.FN_BIZTYPE, 
                    new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBill2.class)+"."+ScmInvMaterialReqBill2.FN_BIZTYPE, QueryParam.QUERY_EQ, "2"));
        }
        if (StringUtils.equals("Y", materialReq) || StringUtils.equals("Y", materialReqReturn) || StringUtils.equals("Y", costConsume2)) {
            List<ScmInvMaterialReqBillEntry2> scmInvMaterialReqBillEntryList = scmInvMaterialReqBillEntryBiz.queryPage(page, arglist, "findAllPage", param);
            if(scmInvMaterialReqBillEntryList!=null && !scmInvMaterialReqBillEntryList.isEmpty()) {
                List<ScmInvMaterialReqBillEntry2> scmInvMaterialReqBillEntryListIn = new ArrayList<ScmInvMaterialReqBillEntry2>();
                List<ScmInvMaterialReqBillEntry2> scmInvMaterialReqBillEntryListReturn = new ArrayList<ScmInvMaterialReqBillEntry2>();
                for (ScmInvMaterialReqBillEntry2 scmInvMaterialReqBillEntry : scmInvMaterialReqBillEntryList) {
                    if (StringUtils.equals("1",scmInvMaterialReqBillEntry.getBizType())) {
                        scmInvMaterialReqBillEntryListIn.add(scmInvMaterialReqBillEntry);
                    } else if (StringUtils.equals("2",scmInvMaterialReqBillEntry.getBizType())) {
                        scmInvMaterialReqBillEntryListReturn.add(scmInvMaterialReqBillEntry);
                    }
                }
                if (scmInvMaterialReqBillEntryListIn != null && scmInvMaterialReqBillEntryListIn.size() > 0) {
                    // 领料出库
                    if (StringUtils.equals("Y", materialReq)) {
                        intervalBizType.add(index, "领料出库");
                        index = index + 1;
                        for (ScmInvMaterialReqBillEntry2 scmInvMaterialReqBillEntry : scmInvMaterialReqBillEntryListIn) {
                            boolean exists=false;
                            if(!scmCostTransferOccurSumList.isEmpty()) {
                                for (ScmCostTransferOccurSum existScmCostTransferOccurSum:scmCostTransferOccurSumList) {
                                    if (existScmCostTransferOccurSum.getItemId() == scmInvMaterialReqBillEntry.getItemId() && StringUtils.equals(
                                            existScmCostTransferOccurSum.getCostOrgUnitNo(), scmInvMaterialReqBillEntry.getCostOrgUnitNo())) {
                                        switch (index) {
                                        case 1:
                                            existScmCostTransferOccurSum.setQty(existScmCostTransferOccurSum.getQty().add(scmInvMaterialReqBillEntry.getQty()));
                                            existScmCostTransferOccurSum.setAmt(existScmCostTransferOccurSum.getAmt().add(scmInvMaterialReqBillEntry.getAmt()));
                                            existScmCostTransferOccurSum.setTaxAmt(existScmCostTransferOccurSum.getTaxAmt().add(scmInvMaterialReqBillEntry.getTaxAmt()));
                                            exists = true;
                                            break;
                                        case 2:
                                            existScmCostTransferOccurSum.setQty_1(existScmCostTransferOccurSum.getQty_1().add(scmInvMaterialReqBillEntry.getQty()));
                                            existScmCostTransferOccurSum.setAmt_1(existScmCostTransferOccurSum.getAmt_1().add(scmInvMaterialReqBillEntry.getAmt()));
                                            existScmCostTransferOccurSum.setTaxAmt_1(existScmCostTransferOccurSum.getTaxAmt_1().add(scmInvMaterialReqBillEntry.getTaxAmt()));
                                            exists = true;
                                            break;
                                        case 3:
                                            existScmCostTransferOccurSum.setQty_2(existScmCostTransferOccurSum.getQty_2().add(scmInvMaterialReqBillEntry.getQty()));
                                            existScmCostTransferOccurSum.setAmt_2(existScmCostTransferOccurSum.getAmt_2().add(scmInvMaterialReqBillEntry.getAmt()));
                                            existScmCostTransferOccurSum.setTaxAmt_2(existScmCostTransferOccurSum.getTaxAmt_2().add(scmInvMaterialReqBillEntry.getTaxAmt()));
                                            exists = true;
                                            break;                                    
                                        case 4:
                                            existScmCostTransferOccurSum.setQty_3(existScmCostTransferOccurSum.getQty_3().add(scmInvMaterialReqBillEntry.getQty()));
                                            existScmCostTransferOccurSum.setAmt_3(existScmCostTransferOccurSum.getAmt_3().add(scmInvMaterialReqBillEntry.getAmt()));
                                            existScmCostTransferOccurSum.setTaxAmt_3(existScmCostTransferOccurSum.getTaxAmt_3().add(scmInvMaterialReqBillEntry.getTaxAmt()));
                                            exists = true;
                                            break;                                    
                                        case 5:
                                            existScmCostTransferOccurSum.setQty_4(existScmCostTransferOccurSum.getQty_4().add(scmInvMaterialReqBillEntry.getQty()));
                                            existScmCostTransferOccurSum.setAmt_4(existScmCostTransferOccurSum.getAmt_4().add(scmInvMaterialReqBillEntry.getAmt()));
                                            existScmCostTransferOccurSum.setTaxAmt_4(existScmCostTransferOccurSum.getTaxAmt_4().add(scmInvMaterialReqBillEntry.getTaxAmt()));
                                            exists = true;
                                            break;
                                        case 6:
                                            existScmCostTransferOccurSum.setQty_5(existScmCostTransferOccurSum.getQty_5().add(scmInvMaterialReqBillEntry.getQty()));
                                            existScmCostTransferOccurSum.setAmt_5(existScmCostTransferOccurSum.getAmt_5().add(scmInvMaterialReqBillEntry.getAmt()));
                                            existScmCostTransferOccurSum.setTaxAmt_5(existScmCostTransferOccurSum.getTaxAmt_5().add(scmInvMaterialReqBillEntry.getTaxAmt()));
                                            exists = true;
                                            break;                                    
                                        case 7:
                                            existScmCostTransferOccurSum.setQty_6(existScmCostTransferOccurSum.getQty_6().add(scmInvMaterialReqBillEntry.getQty()));
                                            existScmCostTransferOccurSum.setAmt_6(existScmCostTransferOccurSum.getAmt_6().add(scmInvMaterialReqBillEntry.getAmt()));
                                            existScmCostTransferOccurSum.setTaxAmt_6(existScmCostTransferOccurSum.getTaxAmt_6().add(scmInvMaterialReqBillEntry.getTaxAmt()));
                                            exists = true;
                                            break;                                    
                                        case 8:
                                            existScmCostTransferOccurSum.setQty_7(existScmCostTransferOccurSum.getQty_7().add(scmInvMaterialReqBillEntry.getQty()));
                                            existScmCostTransferOccurSum.setAmt_7(existScmCostTransferOccurSum.getAmt_7().add(scmInvMaterialReqBillEntry.getAmt()));
                                            existScmCostTransferOccurSum.setTaxAmt_7(existScmCostTransferOccurSum.getTaxAmt_7().add(scmInvMaterialReqBillEntry.getTaxAmt()));
                                            exists = true;
                                            break;                                    
                                        case 9:
                                            existScmCostTransferOccurSum.setQty_8(existScmCostTransferOccurSum.getQty_8().add(scmInvMaterialReqBillEntry.getQty()));
                                            existScmCostTransferOccurSum.setAmt_8(existScmCostTransferOccurSum.getAmt_8().add(scmInvMaterialReqBillEntry.getAmt()));
                                            existScmCostTransferOccurSum.setTaxAmt_8(existScmCostTransferOccurSum.getTaxAmt_8().add(scmInvMaterialReqBillEntry.getTaxAmt()));
                                            exists = true;
                                            break;                                    
                                        case 10:
                                            existScmCostTransferOccurSum.setQty_9(existScmCostTransferOccurSum.getQty_9().add(scmInvMaterialReqBillEntry.getQty()));
                                            existScmCostTransferOccurSum.setAmt_9(existScmCostTransferOccurSum.getAmt_9().add(scmInvMaterialReqBillEntry.getAmt()));
                                            existScmCostTransferOccurSum.setTaxAmt_9(existScmCostTransferOccurSum.getTaxAmt_9().add(scmInvMaterialReqBillEntry.getTaxAmt()));
                                            exists = true;
                                            break;                                    
                                        default:
                                            break;
                                        }
                                    }
                                }
                            }
                            if(!exists) {
                                ScmCostTransferOccurSum scmCostTransferOccurSum = new ScmCostTransferOccurSum(true);
                                scmCostTransferOccurSum.setClassName(scmInvMaterialReqBillEntry.getGroupName());
                                scmCostTransferOccurSum.setItemId(scmInvMaterialReqBillEntry.getItemId());
                                scmCostTransferOccurSum.setItemNo(scmInvMaterialReqBillEntry.getItemNo());
                                scmCostTransferOccurSum.setItemName(scmInvMaterialReqBillEntry.getItemName());
                                scmCostTransferOccurSum.setSpec(scmInvMaterialReqBillEntry.getSpec());
                                scmCostTransferOccurSum.setUnit(scmInvMaterialReqBillEntry.getUnit());
                                scmCostTransferOccurSum.setUnitName(scmInvMaterialReqBillEntry.getUnitName());
                                scmCostTransferOccurSum.setCostOrgUnitNo(scmInvMaterialReqBillEntry.getCostOrgUnitNo());
                                switch (index) {
                                case 1:
                                    scmCostTransferOccurSum.setQty(scmCostTransferOccurSum.getQty().add(scmInvMaterialReqBillEntry.getQty()));
                                    scmCostTransferOccurSum.setAmt(scmCostTransferOccurSum.getAmt().add(scmInvMaterialReqBillEntry.getAmt()));
                                    scmCostTransferOccurSum.setTaxAmt(scmCostTransferOccurSum.getTaxAmt().add(scmInvMaterialReqBillEntry.getTaxAmt()));
                                    break;
                                case 2:
                                    scmCostTransferOccurSum.setQty_1(scmCostTransferOccurSum.getQty_1().add(scmInvMaterialReqBillEntry.getQty()));
                                    scmCostTransferOccurSum.setAmt_1(scmCostTransferOccurSum.getAmt_1().add(scmInvMaterialReqBillEntry.getAmt()));
                                    scmCostTransferOccurSum.setTaxAmt_1(scmCostTransferOccurSum.getTaxAmt_1().add(scmInvMaterialReqBillEntry.getTaxAmt()));
                                    break;                                    
                                case 3:
                                    scmCostTransferOccurSum.setQty_2(scmCostTransferOccurSum.getQty_2().add(scmInvMaterialReqBillEntry.getQty()));
                                    scmCostTransferOccurSum.setAmt_2(scmCostTransferOccurSum.getAmt_2().add(scmInvMaterialReqBillEntry.getAmt()));
                                    scmCostTransferOccurSum.setTaxAmt_2(scmCostTransferOccurSum.getTaxAmt_2().add(scmInvMaterialReqBillEntry.getTaxAmt()));
                                    break;                                    
                                case 4:
                                    scmCostTransferOccurSum.setQty_3(scmCostTransferOccurSum.getQty_3().add(scmInvMaterialReqBillEntry.getQty()));
                                    scmCostTransferOccurSum.setAmt_3(scmCostTransferOccurSum.getAmt_3().add(scmInvMaterialReqBillEntry.getAmt()));
                                    scmCostTransferOccurSum.setTaxAmt_3(scmCostTransferOccurSum.getTaxAmt_3().add(scmInvMaterialReqBillEntry.getTaxAmt()));
                                    break;
                                case 5:
                                    scmCostTransferOccurSum.setQty_4(scmCostTransferOccurSum.getQty_4().add(scmInvMaterialReqBillEntry.getQty()));
                                    scmCostTransferOccurSum.setAmt_4(scmCostTransferOccurSum.getAmt_4().add(scmInvMaterialReqBillEntry.getAmt()));
                                    scmCostTransferOccurSum.setTaxAmt_4(scmCostTransferOccurSum.getTaxAmt_4().add(scmInvMaterialReqBillEntry.getTaxAmt()));
                                    break;                                    
                                case 6:
                                    scmCostTransferOccurSum.setQty_5(scmCostTransferOccurSum.getQty_5().add(scmInvMaterialReqBillEntry.getQty()));
                                    scmCostTransferOccurSum.setAmt_5(scmCostTransferOccurSum.getAmt_5().add(scmInvMaterialReqBillEntry.getAmt()));
                                    scmCostTransferOccurSum.setTaxAmt_5(scmCostTransferOccurSum.getTaxAmt_5().add(scmInvMaterialReqBillEntry.getTaxAmt()));
                                    break;                                    
                                case 7:
                                    scmCostTransferOccurSum.setQty_6(scmCostTransferOccurSum.getQty_6().add(scmInvMaterialReqBillEntry.getQty()));
                                    scmCostTransferOccurSum.setAmt_6(scmCostTransferOccurSum.getAmt_6().add(scmInvMaterialReqBillEntry.getAmt()));
                                    scmCostTransferOccurSum.setTaxAmt_6(scmCostTransferOccurSum.getTaxAmt_6().add(scmInvMaterialReqBillEntry.getTaxAmt()));
                                    break;                                    
                                case 8:
                                    scmCostTransferOccurSum.setQty_7(scmCostTransferOccurSum.getQty_7().add(scmInvMaterialReqBillEntry.getQty()));
                                    scmCostTransferOccurSum.setAmt_7(scmCostTransferOccurSum.getAmt_7().add(scmInvMaterialReqBillEntry.getAmt()));
                                    scmCostTransferOccurSum.setTaxAmt_7(scmCostTransferOccurSum.getTaxAmt_7().add(scmInvMaterialReqBillEntry.getTaxAmt()));
                                    break;                                    
                                case 9:
                                    scmCostTransferOccurSum.setQty_8(scmCostTransferOccurSum.getQty_8().add(scmInvMaterialReqBillEntry.getQty()));
                                    scmCostTransferOccurSum.setAmt_8(scmCostTransferOccurSum.getAmt_8().add(scmInvMaterialReqBillEntry.getAmt()));
                                    scmCostTransferOccurSum.setTaxAmt_8(scmCostTransferOccurSum.getTaxAmt_8().add(scmInvMaterialReqBillEntry.getTaxAmt()));
                                    break;                                    
                                case 10:
                                    scmCostTransferOccurSum.setQty_9(scmCostTransferOccurSum.getQty_9().add(scmInvMaterialReqBillEntry.getQty()));
                                    scmCostTransferOccurSum.setAmt_9(scmCostTransferOccurSum.getAmt_9().add(scmInvMaterialReqBillEntry.getAmt()));
                                    scmCostTransferOccurSum.setTaxAmt_9(scmCostTransferOccurSum.getTaxAmt_9().add(scmInvMaterialReqBillEntry.getTaxAmt()));
                                    break;                                    
                                default:
                                    break;
                                }
                                scmCostTransferOccurSumList.add(scmCostTransferOccurSum);
                            }                    
                        }
                    }
                    // 以领代耗
                    if (StringUtils.equals("Y", costConsume2)) {
                    	int existIndex=-1;
                    	if(!intervalBizType.isEmpty()) {
                    		for(int i=0;i<intervalBizType.size();i++) {
                    			if(StringUtils.equals("以领代耗", intervalBizType.get(i))) {
                    				existIndex=i;
                    				break;
                    			}
                    		}
                    	}
                    	if(existIndex==-1) {
	                        intervalBizType.add(index, "以领代耗");
	                        index = index + 1;
	                        existIndex=index;
                    	}else {
                    		existIndex = existIndex + 1;
                    	}
                    	for (ScmInvMaterialReqBillEntry2 scmInvMaterialReqBillEntry : scmInvMaterialReqBillEntryListIn) {
                        	OrgCostCenter2 orgCostCenter = orgCostCenterBiz.selectByOrgUnitNo(scmInvMaterialReqBillEntry.getCostOrgUnitNo(), param);
                        	if(orgCostCenter!=null && StringUtils.equals("2", orgCostCenter.getCostCenterType())) {
    							continue;
    						}
                            boolean exists=false;
                            if(!scmCostTransferOccurSumList.isEmpty()) {
                                for (ScmCostTransferOccurSum existScmCostTransferOccurSum:scmCostTransferOccurSumList) {
                                    if (existScmCostTransferOccurSum.getItemId() == scmInvMaterialReqBillEntry.getItemId() && StringUtils.equals(
                                            existScmCostTransferOccurSum.getCostOrgUnitNo(), scmInvMaterialReqBillEntry.getCostOrgUnitNo())) {
                                        switch (existIndex) {
                                        case 1:
                                            existScmCostTransferOccurSum.setQty(existScmCostTransferOccurSum.getQty().add(scmInvMaterialReqBillEntry.getQty()));
                                            existScmCostTransferOccurSum.setAmt(existScmCostTransferOccurSum.getAmt().add(scmInvMaterialReqBillEntry.getAmt()));
                                            existScmCostTransferOccurSum.setTaxAmt(existScmCostTransferOccurSum.getTaxAmt().add(scmInvMaterialReqBillEntry.getTaxAmt()));
                                            exists = true;
                                            break;
                                        case 2:
                                            existScmCostTransferOccurSum.setQty_1(existScmCostTransferOccurSum.getQty_1().add(scmInvMaterialReqBillEntry.getQty()));
                                            existScmCostTransferOccurSum.setAmt_1(existScmCostTransferOccurSum.getAmt_1().add(scmInvMaterialReqBillEntry.getAmt()));
                                            existScmCostTransferOccurSum.setTaxAmt_1(existScmCostTransferOccurSum.getTaxAmt_1().add(scmInvMaterialReqBillEntry.getTaxAmt()));
                                            exists = true;
                                            break;
                                        case 3:
                                            existScmCostTransferOccurSum.setQty_2(existScmCostTransferOccurSum.getQty_2().add(scmInvMaterialReqBillEntry.getQty()));
                                            existScmCostTransferOccurSum.setAmt_2(existScmCostTransferOccurSum.getAmt_2().add(scmInvMaterialReqBillEntry.getAmt()));
                                            existScmCostTransferOccurSum.setTaxAmt_2(existScmCostTransferOccurSum.getTaxAmt_2().add(scmInvMaterialReqBillEntry.getTaxAmt()));
                                            exists = true;
                                            break;                                    
                                        case 4:
                                            existScmCostTransferOccurSum.setQty_3(existScmCostTransferOccurSum.getQty_3().add(scmInvMaterialReqBillEntry.getQty()));
                                            existScmCostTransferOccurSum.setAmt_3(existScmCostTransferOccurSum.getAmt_3().add(scmInvMaterialReqBillEntry.getAmt()));
                                            existScmCostTransferOccurSum.setTaxAmt_3(existScmCostTransferOccurSum.getTaxAmt_3().add(scmInvMaterialReqBillEntry.getTaxAmt()));
                                            exists = true;
                                            break;                                    
                                        case 5:
                                            existScmCostTransferOccurSum.setQty_4(existScmCostTransferOccurSum.getQty_4().add(scmInvMaterialReqBillEntry.getQty()));
                                            existScmCostTransferOccurSum.setAmt_4(existScmCostTransferOccurSum.getAmt_4().add(scmInvMaterialReqBillEntry.getAmt()));
                                            existScmCostTransferOccurSum.setTaxAmt_4(existScmCostTransferOccurSum.getTaxAmt_4().add(scmInvMaterialReqBillEntry.getTaxAmt()));
                                            exists = true;
                                            break;
                                        case 6:
                                            existScmCostTransferOccurSum.setQty_5(existScmCostTransferOccurSum.getQty_5().add(scmInvMaterialReqBillEntry.getQty()));
                                            existScmCostTransferOccurSum.setAmt_5(existScmCostTransferOccurSum.getAmt_5().add(scmInvMaterialReqBillEntry.getAmt()));
                                            existScmCostTransferOccurSum.setTaxAmt_5(existScmCostTransferOccurSum.getTaxAmt_5().add(scmInvMaterialReqBillEntry.getTaxAmt()));
                                            exists = true;
                                            break;                                    
                                        case 7:
                                            existScmCostTransferOccurSum.setQty_6(existScmCostTransferOccurSum.getQty_6().add(scmInvMaterialReqBillEntry.getQty()));
                                            existScmCostTransferOccurSum.setAmt_6(existScmCostTransferOccurSum.getAmt_6().add(scmInvMaterialReqBillEntry.getAmt()));
                                            existScmCostTransferOccurSum.setTaxAmt_6(existScmCostTransferOccurSum.getTaxAmt_6().add(scmInvMaterialReqBillEntry.getTaxAmt()));
                                            exists = true;
                                            break;                                    
                                        case 8:
                                            existScmCostTransferOccurSum.setQty_7(existScmCostTransferOccurSum.getQty_7().add(scmInvMaterialReqBillEntry.getQty()));
                                            existScmCostTransferOccurSum.setAmt_7(existScmCostTransferOccurSum.getAmt_7().add(scmInvMaterialReqBillEntry.getAmt()));
                                            existScmCostTransferOccurSum.setTaxAmt_7(existScmCostTransferOccurSum.getTaxAmt_7().add(scmInvMaterialReqBillEntry.getTaxAmt()));
                                            exists = true;
                                            break;                                    
                                        case 9:
                                            existScmCostTransferOccurSum.setQty_8(existScmCostTransferOccurSum.getQty_8().add(scmInvMaterialReqBillEntry.getQty()));
                                            existScmCostTransferOccurSum.setAmt_8(existScmCostTransferOccurSum.getAmt_8().add(scmInvMaterialReqBillEntry.getAmt()));
                                            existScmCostTransferOccurSum.setTaxAmt_8(existScmCostTransferOccurSum.getTaxAmt_8().add(scmInvMaterialReqBillEntry.getTaxAmt()));
                                            exists = true;
                                            break;                                    
                                        case 10:
                                            existScmCostTransferOccurSum.setQty_9(existScmCostTransferOccurSum.getQty_9().add(scmInvMaterialReqBillEntry.getQty()));
                                            existScmCostTransferOccurSum.setAmt_9(existScmCostTransferOccurSum.getAmt_9().add(scmInvMaterialReqBillEntry.getAmt()));
                                            existScmCostTransferOccurSum.setTaxAmt_9(existScmCostTransferOccurSum.getTaxAmt_9().add(scmInvMaterialReqBillEntry.getTaxAmt()));
                                            exists = true;
                                            break;                                    
                                        default:
                                            break;
                                        }
                                    }
                                }
                            }
                            if(!exists) {
                                ScmCostTransferOccurSum scmCostTransferOccurSum = new ScmCostTransferOccurSum(true);
                                scmCostTransferOccurSum.setClassName(scmInvMaterialReqBillEntry.getGroupName());
                                scmCostTransferOccurSum.setItemId(scmInvMaterialReqBillEntry.getItemId());
                                scmCostTransferOccurSum.setItemNo(scmInvMaterialReqBillEntry.getItemNo());
                                scmCostTransferOccurSum.setItemName(scmInvMaterialReqBillEntry.getItemName());
                                scmCostTransferOccurSum.setSpec(scmInvMaterialReqBillEntry.getSpec());
                                scmCostTransferOccurSum.setUnit(scmInvMaterialReqBillEntry.getUnit());
                                scmCostTransferOccurSum.setUnitName(scmInvMaterialReqBillEntry.getUnitName());
                                scmCostTransferOccurSum.setCostOrgUnitNo(scmInvMaterialReqBillEntry.getCostOrgUnitNo());
                                switch (index) {
                                case 1:
                                    scmCostTransferOccurSum.setQty(scmCostTransferOccurSum.getQty().add(scmInvMaterialReqBillEntry.getQty()));
                                    scmCostTransferOccurSum.setAmt(scmCostTransferOccurSum.getAmt().add(scmInvMaterialReqBillEntry.getAmt()));
                                    scmCostTransferOccurSum.setTaxAmt(scmCostTransferOccurSum.getTaxAmt().add(scmInvMaterialReqBillEntry.getTaxAmt()));
                                    break;
                                case 2:
                                    scmCostTransferOccurSum.setQty_1(scmCostTransferOccurSum.getQty_1().add(scmInvMaterialReqBillEntry.getQty()));
                                    scmCostTransferOccurSum.setAmt_1(scmCostTransferOccurSum.getAmt_1().add(scmInvMaterialReqBillEntry.getAmt()));
                                    scmCostTransferOccurSum.setTaxAmt_1(scmCostTransferOccurSum.getTaxAmt_1().add(scmInvMaterialReqBillEntry.getTaxAmt()));
                                    break;                                    
                                case 3:
                                    scmCostTransferOccurSum.setQty_2(scmCostTransferOccurSum.getQty_2().add(scmInvMaterialReqBillEntry.getQty()));
                                    scmCostTransferOccurSum.setAmt_2(scmCostTransferOccurSum.getAmt_2().add(scmInvMaterialReqBillEntry.getAmt()));
                                    scmCostTransferOccurSum.setTaxAmt_2(scmCostTransferOccurSum.getTaxAmt_2().add(scmInvMaterialReqBillEntry.getTaxAmt()));
                                    break;                                    
                                case 4:
                                    scmCostTransferOccurSum.setQty_3(scmCostTransferOccurSum.getQty_3().add(scmInvMaterialReqBillEntry.getQty()));
                                    scmCostTransferOccurSum.setAmt_3(scmCostTransferOccurSum.getAmt_3().add(scmInvMaterialReqBillEntry.getAmt()));
                                    scmCostTransferOccurSum.setTaxAmt_3(scmCostTransferOccurSum.getTaxAmt_3().add(scmInvMaterialReqBillEntry.getTaxAmt()));
                                    break;
                                case 5:
                                    scmCostTransferOccurSum.setQty_4(scmCostTransferOccurSum.getQty_4().add(scmInvMaterialReqBillEntry.getQty()));
                                    scmCostTransferOccurSum.setAmt_4(scmCostTransferOccurSum.getAmt_4().add(scmInvMaterialReqBillEntry.getAmt()));
                                    scmCostTransferOccurSum.setTaxAmt_4(scmCostTransferOccurSum.getTaxAmt_4().add(scmInvMaterialReqBillEntry.getTaxAmt()));
                                    break;                                    
                                case 6:
                                    scmCostTransferOccurSum.setQty_5(scmCostTransferOccurSum.getQty_5().add(scmInvMaterialReqBillEntry.getQty()));
                                    scmCostTransferOccurSum.setAmt_5(scmCostTransferOccurSum.getAmt_5().add(scmInvMaterialReqBillEntry.getAmt()));
                                    scmCostTransferOccurSum.setTaxAmt_5(scmCostTransferOccurSum.getTaxAmt_5().add(scmInvMaterialReqBillEntry.getTaxAmt()));
                                    break;                                    
                                case 7:
                                    scmCostTransferOccurSum.setQty_6(scmCostTransferOccurSum.getQty_6().add(scmInvMaterialReqBillEntry.getQty()));
                                    scmCostTransferOccurSum.setAmt_6(scmCostTransferOccurSum.getAmt_6().add(scmInvMaterialReqBillEntry.getAmt()));
                                    scmCostTransferOccurSum.setTaxAmt_6(scmCostTransferOccurSum.getTaxAmt_6().add(scmInvMaterialReqBillEntry.getTaxAmt()));
                                    break;                                    
                                case 8:
                                    scmCostTransferOccurSum.setQty_7(scmCostTransferOccurSum.getQty_7().add(scmInvMaterialReqBillEntry.getQty()));
                                    scmCostTransferOccurSum.setAmt_7(scmCostTransferOccurSum.getAmt_7().add(scmInvMaterialReqBillEntry.getAmt()));
                                    scmCostTransferOccurSum.setTaxAmt_7(scmCostTransferOccurSum.getTaxAmt_7().add(scmInvMaterialReqBillEntry.getTaxAmt()));
                                    break;                                    
                                case 9:
                                    scmCostTransferOccurSum.setQty_8(scmCostTransferOccurSum.getQty_8().add(scmInvMaterialReqBillEntry.getQty()));
                                    scmCostTransferOccurSum.setAmt_8(scmCostTransferOccurSum.getAmt_8().add(scmInvMaterialReqBillEntry.getAmt()));
                                    scmCostTransferOccurSum.setTaxAmt_8(scmCostTransferOccurSum.getTaxAmt_8().add(scmInvMaterialReqBillEntry.getTaxAmt()));
                                    break;                                    
                                case 10:
                                    scmCostTransferOccurSum.setQty_9(scmCostTransferOccurSum.getQty_9().add(scmInvMaterialReqBillEntry.getQty()));
                                    scmCostTransferOccurSum.setAmt_9(scmCostTransferOccurSum.getAmt_9().add(scmInvMaterialReqBillEntry.getAmt()));
                                    scmCostTransferOccurSum.setTaxAmt_9(scmCostTransferOccurSum.getTaxAmt_9().add(scmInvMaterialReqBillEntry.getTaxAmt()));
                                    break;                                    
                                default:
                                    break;
                                }
                                scmCostTransferOccurSumList.add(scmCostTransferOccurSum);
                            }                    
                        }
                    }
                }
                if (scmInvMaterialReqBillEntryListReturn != null && scmInvMaterialReqBillEntryListReturn.size() > 0) {
                    // 领料退仓
                    if (StringUtils.equals("Y", materialReqReturn)) {
                        intervalBizType.add(index, "领料退仓");
                        index = index + 1;
                        for (ScmInvMaterialReqBillEntry2 scmInvMaterialReqBillEntry : scmInvMaterialReqBillEntryListReturn) {
                            boolean exists=false;
                            if(!scmCostTransferOccurSumList.isEmpty()) {
                                for (ScmCostTransferOccurSum existScmCostTransferOccurSum:scmCostTransferOccurSumList) {
                                    if (existScmCostTransferOccurSum.getItemId() == scmInvMaterialReqBillEntry.getItemId() && StringUtils.equals(
                                            existScmCostTransferOccurSum.getCostOrgUnitNo(), scmInvMaterialReqBillEntry.getCostOrgUnitNo())) {
                                        switch (index) {
                                        case 1:
                                            existScmCostTransferOccurSum.setQty(existScmCostTransferOccurSum.getQty().add(scmInvMaterialReqBillEntry.getQty()));
                                            existScmCostTransferOccurSum.setAmt(existScmCostTransferOccurSum.getAmt().add(scmInvMaterialReqBillEntry.getAmt()));
                                            existScmCostTransferOccurSum.setTaxAmt(existScmCostTransferOccurSum.getTaxAmt().add(scmInvMaterialReqBillEntry.getTaxAmt()));
                                            exists = true;
                                            break;
                                        case 2:
                                            existScmCostTransferOccurSum.setQty_1(existScmCostTransferOccurSum.getQty_1().add(scmInvMaterialReqBillEntry.getQty()));
                                            existScmCostTransferOccurSum.setAmt_1(existScmCostTransferOccurSum.getAmt_1().add(scmInvMaterialReqBillEntry.getAmt()));
                                            existScmCostTransferOccurSum.setTaxAmt_1(existScmCostTransferOccurSum.getTaxAmt_1().add(scmInvMaterialReqBillEntry.getTaxAmt()));
                                            exists = true;
                                            break;
                                        case 3:
                                            existScmCostTransferOccurSum.setQty_2(existScmCostTransferOccurSum.getQty_2().add(scmInvMaterialReqBillEntry.getQty()));
                                            existScmCostTransferOccurSum.setAmt_2(existScmCostTransferOccurSum.getAmt_2().add(scmInvMaterialReqBillEntry.getAmt()));
                                            existScmCostTransferOccurSum.setTaxAmt_2(existScmCostTransferOccurSum.getTaxAmt_2().add(scmInvMaterialReqBillEntry.getTaxAmt()));
                                            exists = true;
                                            break;                                    
                                        case 4:
                                            existScmCostTransferOccurSum.setQty_3(existScmCostTransferOccurSum.getQty_3().add(scmInvMaterialReqBillEntry.getQty()));
                                            existScmCostTransferOccurSum.setAmt_3(existScmCostTransferOccurSum.getAmt_3().add(scmInvMaterialReqBillEntry.getAmt()));
                                            existScmCostTransferOccurSum.setTaxAmt_3(existScmCostTransferOccurSum.getTaxAmt_3().add(scmInvMaterialReqBillEntry.getTaxAmt()));
                                            exists = true;
                                            break;                                    
                                        case 5:
                                            existScmCostTransferOccurSum.setQty_4(existScmCostTransferOccurSum.getQty_4().add(scmInvMaterialReqBillEntry.getQty()));
                                            existScmCostTransferOccurSum.setAmt_4(existScmCostTransferOccurSum.getAmt_4().add(scmInvMaterialReqBillEntry.getAmt()));
                                            existScmCostTransferOccurSum.setTaxAmt_4(existScmCostTransferOccurSum.getTaxAmt_4().add(scmInvMaterialReqBillEntry.getTaxAmt()));
                                            exists = true;
                                            break;
                                        case 6:
                                            existScmCostTransferOccurSum.setQty_5(existScmCostTransferOccurSum.getQty_5().add(scmInvMaterialReqBillEntry.getQty()));
                                            existScmCostTransferOccurSum.setAmt_5(existScmCostTransferOccurSum.getAmt_5().add(scmInvMaterialReqBillEntry.getAmt()));
                                            existScmCostTransferOccurSum.setTaxAmt_5(existScmCostTransferOccurSum.getTaxAmt_5().add(scmInvMaterialReqBillEntry.getTaxAmt()));
                                            exists = true;
                                            break;                                    
                                        case 7:
                                            existScmCostTransferOccurSum.setQty_6(existScmCostTransferOccurSum.getQty_6().add(scmInvMaterialReqBillEntry.getQty()));
                                            existScmCostTransferOccurSum.setAmt_6(existScmCostTransferOccurSum.getAmt_6().add(scmInvMaterialReqBillEntry.getAmt()));
                                            existScmCostTransferOccurSum.setTaxAmt_6(existScmCostTransferOccurSum.getTaxAmt_6().add(scmInvMaterialReqBillEntry.getTaxAmt()));
                                            exists = true;
                                            break;                                    
                                        case 8:
                                            existScmCostTransferOccurSum.setQty_7(existScmCostTransferOccurSum.getQty_7().add(scmInvMaterialReqBillEntry.getQty()));
                                            existScmCostTransferOccurSum.setAmt_7(existScmCostTransferOccurSum.getAmt_7().add(scmInvMaterialReqBillEntry.getAmt()));
                                            existScmCostTransferOccurSum.setTaxAmt_7(existScmCostTransferOccurSum.getTaxAmt_7().add(scmInvMaterialReqBillEntry.getTaxAmt()));
                                            exists = true;
                                            break;                                    
                                        case 9:
                                            existScmCostTransferOccurSum.setQty_8(existScmCostTransferOccurSum.getQty_8().add(scmInvMaterialReqBillEntry.getQty()));
                                            existScmCostTransferOccurSum.setAmt_8(existScmCostTransferOccurSum.getAmt_8().add(scmInvMaterialReqBillEntry.getAmt()));
                                            existScmCostTransferOccurSum.setTaxAmt_8(existScmCostTransferOccurSum.getTaxAmt_8().add(scmInvMaterialReqBillEntry.getTaxAmt()));
                                            exists = true;
                                            break;                                    
                                        case 10:
                                            existScmCostTransferOccurSum.setQty_9(existScmCostTransferOccurSum.getQty_8().add(scmInvMaterialReqBillEntry.getQty()));
                                            existScmCostTransferOccurSum.setAmt_9(existScmCostTransferOccurSum.getAmt_8().add(scmInvMaterialReqBillEntry.getAmt()));
                                            existScmCostTransferOccurSum.setTaxAmt_9(existScmCostTransferOccurSum.getTaxAmt_8().add(scmInvMaterialReqBillEntry.getTaxAmt()));
                                            exists = true;
                                            break;                                    
                                        default:
                                            break;
                                        }
                                    }
                                }
                            }
                            if(!exists) {
                                ScmCostTransferOccurSum scmCostTransferOccurSum = new ScmCostTransferOccurSum(true);
                                scmCostTransferOccurSum.setClassName(scmInvMaterialReqBillEntry.getGroupName());
                                scmCostTransferOccurSum.setItemId(scmInvMaterialReqBillEntry.getItemId());
                                scmCostTransferOccurSum.setItemNo(scmInvMaterialReqBillEntry.getItemNo());
                                scmCostTransferOccurSum.setItemName(scmInvMaterialReqBillEntry.getItemName());
                                scmCostTransferOccurSum.setSpec(scmInvMaterialReqBillEntry.getSpec());
                                scmCostTransferOccurSum.setUnit(scmInvMaterialReqBillEntry.getUnit());
                                scmCostTransferOccurSum.setUnitName(scmInvMaterialReqBillEntry.getUnitName());
                                scmCostTransferOccurSum.setCostOrgUnitNo(scmInvMaterialReqBillEntry.getCostOrgUnitNo());
                                switch (index) {
                                case 1:
                                    scmCostTransferOccurSum.setQty(scmCostTransferOccurSum.getQty().add(scmInvMaterialReqBillEntry.getQty()));
                                    scmCostTransferOccurSum.setAmt(scmCostTransferOccurSum.getAmt().add(scmInvMaterialReqBillEntry.getAmt()));
                                    scmCostTransferOccurSum.setTaxAmt(scmCostTransferOccurSum.getTaxAmt().add(scmInvMaterialReqBillEntry.getTaxAmt()));
                                    break;
                                case 2:
                                    scmCostTransferOccurSum.setQty_1(scmCostTransferOccurSum.getQty_1().add(scmInvMaterialReqBillEntry.getQty()));
                                    scmCostTransferOccurSum.setAmt_1(scmCostTransferOccurSum.getAmt_1().add(scmInvMaterialReqBillEntry.getAmt()));
                                    scmCostTransferOccurSum.setTaxAmt_1(scmCostTransferOccurSum.getTaxAmt_1().add(scmInvMaterialReqBillEntry.getTaxAmt()));
                                    break;                                    
                                case 3:
                                    scmCostTransferOccurSum.setQty_2(scmCostTransferOccurSum.getQty_2().add(scmInvMaterialReqBillEntry.getQty()));
                                    scmCostTransferOccurSum.setAmt_2(scmCostTransferOccurSum.getAmt_2().add(scmInvMaterialReqBillEntry.getAmt()));
                                    scmCostTransferOccurSum.setTaxAmt_2(scmCostTransferOccurSum.getTaxAmt_2().add(scmInvMaterialReqBillEntry.getTaxAmt()));
                                    break;                                    
                                case 4:
                                    scmCostTransferOccurSum.setQty_3(scmCostTransferOccurSum.getQty_3().add(scmInvMaterialReqBillEntry.getQty()));
                                    scmCostTransferOccurSum.setAmt_3(scmCostTransferOccurSum.getAmt_3().add(scmInvMaterialReqBillEntry.getAmt()));
                                    scmCostTransferOccurSum.setTaxAmt_3(scmCostTransferOccurSum.getTaxAmt_3().add(scmInvMaterialReqBillEntry.getTaxAmt()));
                                    break;
                                case 5:
                                    scmCostTransferOccurSum.setQty_4(scmCostTransferOccurSum.getQty_4().add(scmInvMaterialReqBillEntry.getQty()));
                                    scmCostTransferOccurSum.setAmt_4(scmCostTransferOccurSum.getAmt_4().add(scmInvMaterialReqBillEntry.getAmt()));
                                    scmCostTransferOccurSum.setTaxAmt_4(scmCostTransferOccurSum.getTaxAmt_4().add(scmInvMaterialReqBillEntry.getTaxAmt()));
                                    break;                                    
                                case 6:
                                    scmCostTransferOccurSum.setQty_5(scmCostTransferOccurSum.getQty_5().add(scmInvMaterialReqBillEntry.getQty()));
                                    scmCostTransferOccurSum.setAmt_5(scmCostTransferOccurSum.getAmt_5().add(scmInvMaterialReqBillEntry.getAmt()));
                                    scmCostTransferOccurSum.setTaxAmt_5(scmCostTransferOccurSum.getTaxAmt_5().add(scmInvMaterialReqBillEntry.getTaxAmt()));
                                    break;                                    
                                case 7:
                                    scmCostTransferOccurSum.setQty_6(scmCostTransferOccurSum.getQty_6().add(scmInvMaterialReqBillEntry.getQty()));
                                    scmCostTransferOccurSum.setAmt_6(scmCostTransferOccurSum.getAmt_6().add(scmInvMaterialReqBillEntry.getAmt()));
                                    scmCostTransferOccurSum.setTaxAmt_6(scmCostTransferOccurSum.getTaxAmt_6().add(scmInvMaterialReqBillEntry.getTaxAmt()));
                                    break;                                    
                                case 8:
                                    scmCostTransferOccurSum.setQty_7(scmCostTransferOccurSum.getQty_7().add(scmInvMaterialReqBillEntry.getQty()));
                                    scmCostTransferOccurSum.setAmt_7(scmCostTransferOccurSum.getAmt_7().add(scmInvMaterialReqBillEntry.getAmt()));
                                    scmCostTransferOccurSum.setTaxAmt_7(scmCostTransferOccurSum.getTaxAmt_7().add(scmInvMaterialReqBillEntry.getTaxAmt()));
                                    break;                                    
                                case 9:
                                    scmCostTransferOccurSum.setQty_8(scmCostTransferOccurSum.getQty_8().add(scmInvMaterialReqBillEntry.getQty()));
                                    scmCostTransferOccurSum.setAmt_8(scmCostTransferOccurSum.getAmt_8().add(scmInvMaterialReqBillEntry.getAmt()));
                                    scmCostTransferOccurSum.setTaxAmt_8(scmCostTransferOccurSum.getTaxAmt_8().add(scmInvMaterialReqBillEntry.getTaxAmt()));
                                    break;                                    
                                case 10:
                                    scmCostTransferOccurSum.setQty_9(scmCostTransferOccurSum.getQty_9().add(scmInvMaterialReqBillEntry.getQty()));
                                    scmCostTransferOccurSum.setAmt_9(scmCostTransferOccurSum.getAmt_9().add(scmInvMaterialReqBillEntry.getAmt()));
                                    scmCostTransferOccurSum.setTaxAmt_9(scmCostTransferOccurSum.getTaxAmt_9().add(scmInvMaterialReqBillEntry.getTaxAmt()));
                                    break;                                    
                                default:
                                    break;
                                }
                                scmCostTransferOccurSumList.add(scmCostTransferOccurSum);
                            }                   
                        }
                    }
                    // 以领代耗
                    if (StringUtils.equals("Y", costConsume2)) {
                    	int existIndex=-1;
                    	if(!intervalBizType.isEmpty()) {
                    		for(int i=0;i<intervalBizType.size();i++) {
                    			if(StringUtils.equals("以领代耗", intervalBizType.get(i))) {
                    				existIndex=i;
                    				break;
                    			}
                    		}
                    	}
                    	if(existIndex==-1) {
	                        intervalBizType.add(index, "以领代耗");
	                        index = index + 1;
	                        existIndex=index;
                    	}else {
                    		existIndex = existIndex + 1;
                    	}
                    	for (ScmInvMaterialReqBillEntry2 scmInvMaterialReqBillEntry : scmInvMaterialReqBillEntryListReturn) {
                        	OrgCostCenter2 orgCostCenter = orgCostCenterBiz.selectByOrgUnitNo(scmInvMaterialReqBillEntry.getCostOrgUnitNo(), param);
                        	if(orgCostCenter!=null && StringUtils.equals("2", orgCostCenter.getCostCenterType())) {
    							continue;
    						}
                            boolean exists=false;
                            if(!scmCostTransferOccurSumList.isEmpty()) {
                                for (ScmCostTransferOccurSum existScmCostTransferOccurSum:scmCostTransferOccurSumList) {
                                    if (existScmCostTransferOccurSum.getItemId() == scmInvMaterialReqBillEntry.getItemId() && StringUtils.equals(
                                            existScmCostTransferOccurSum.getCostOrgUnitNo(), scmInvMaterialReqBillEntry.getCostOrgUnitNo())) {
                                        switch (existIndex) {
                                        case 1:
                                            existScmCostTransferOccurSum.setQty(existScmCostTransferOccurSum.getQty().subtract(scmInvMaterialReqBillEntry.getQty()));
                                            existScmCostTransferOccurSum.setAmt(existScmCostTransferOccurSum.getAmt().subtract(scmInvMaterialReqBillEntry.getAmt()));
                                            existScmCostTransferOccurSum.setTaxAmt(existScmCostTransferOccurSum.getTaxAmt().subtract(scmInvMaterialReqBillEntry.getTaxAmt()));
                                            exists = true;
                                            break;
                                        case 2:
                                            existScmCostTransferOccurSum.setQty_1(existScmCostTransferOccurSum.getQty_1().subtract(scmInvMaterialReqBillEntry.getQty()));
                                            existScmCostTransferOccurSum.setAmt_1(existScmCostTransferOccurSum.getAmt_1().subtract(scmInvMaterialReqBillEntry.getAmt()));
                                            existScmCostTransferOccurSum.setTaxAmt_1(existScmCostTransferOccurSum.getTaxAmt_1().add(scmInvMaterialReqBillEntry.getTaxAmt()));
                                            exists = true;
                                            break;
                                        case 3:
                                            existScmCostTransferOccurSum.setQty_2(existScmCostTransferOccurSum.getQty_2().subtract(scmInvMaterialReqBillEntry.getQty()));
                                            existScmCostTransferOccurSum.setAmt_2(existScmCostTransferOccurSum.getAmt_2().subtract(scmInvMaterialReqBillEntry.getAmt()));
                                            existScmCostTransferOccurSum.setTaxAmt_2(existScmCostTransferOccurSum.getTaxAmt_2().subtract(scmInvMaterialReqBillEntry.getTaxAmt()));
                                            exists = true;
                                            break;                                    
                                        case 4:
                                            existScmCostTransferOccurSum.setQty_3(existScmCostTransferOccurSum.getQty_3().subtract(scmInvMaterialReqBillEntry.getQty()));
                                            existScmCostTransferOccurSum.setAmt_3(existScmCostTransferOccurSum.getAmt_3().subtract(scmInvMaterialReqBillEntry.getAmt()));
                                            existScmCostTransferOccurSum.setTaxAmt_3(existScmCostTransferOccurSum.getTaxAmt_3().subtract(scmInvMaterialReqBillEntry.getTaxAmt()));
                                            exists = true;
                                            break;                                    
                                        case 5:
                                            existScmCostTransferOccurSum.setQty_4(existScmCostTransferOccurSum.getQty_4().subtract(scmInvMaterialReqBillEntry.getQty()));
                                            existScmCostTransferOccurSum.setAmt_4(existScmCostTransferOccurSum.getAmt_4().subtract(scmInvMaterialReqBillEntry.getAmt()));
                                            existScmCostTransferOccurSum.setTaxAmt_4(existScmCostTransferOccurSum.getTaxAmt_4().subtract(scmInvMaterialReqBillEntry.getTaxAmt()));
                                            exists = true;
                                            break;
                                        case 6:
                                            existScmCostTransferOccurSum.setQty_5(existScmCostTransferOccurSum.getQty_5().subtract(scmInvMaterialReqBillEntry.getQty()));
                                            existScmCostTransferOccurSum.setAmt_5(existScmCostTransferOccurSum.getAmt_5().subtract(scmInvMaterialReqBillEntry.getAmt()));
                                            existScmCostTransferOccurSum.setTaxAmt_5(existScmCostTransferOccurSum.getTaxAmt_5().subtract(scmInvMaterialReqBillEntry.getTaxAmt()));
                                            exists = true;
                                            break;                                    
                                        case 7:
                                            existScmCostTransferOccurSum.setQty_6(existScmCostTransferOccurSum.getQty_6().subtract(scmInvMaterialReqBillEntry.getQty()));
                                            existScmCostTransferOccurSum.setAmt_6(existScmCostTransferOccurSum.getAmt_6().subtract(scmInvMaterialReqBillEntry.getAmt()));
                                            existScmCostTransferOccurSum.setTaxAmt_6(existScmCostTransferOccurSum.getTaxAmt_6().subtract(scmInvMaterialReqBillEntry.getTaxAmt()));
                                            exists = true;
                                            break;                                    
                                        case 8:
                                            existScmCostTransferOccurSum.setQty_7(existScmCostTransferOccurSum.getQty_7().subtract(scmInvMaterialReqBillEntry.getQty()));
                                            existScmCostTransferOccurSum.setAmt_7(existScmCostTransferOccurSum.getAmt_7().subtract(scmInvMaterialReqBillEntry.getAmt()));
                                            existScmCostTransferOccurSum.setTaxAmt_7(existScmCostTransferOccurSum.getTaxAmt_7().subtract(scmInvMaterialReqBillEntry.getTaxAmt()));
                                            exists = true;
                                            break;                                    
                                        case 9:
                                            existScmCostTransferOccurSum.setQty_8(existScmCostTransferOccurSum.getQty_8().subtract(scmInvMaterialReqBillEntry.getQty()));
                                            existScmCostTransferOccurSum.setAmt_8(existScmCostTransferOccurSum.getAmt_8().subtract(scmInvMaterialReqBillEntry.getAmt()));
                                            existScmCostTransferOccurSum.setTaxAmt_8(existScmCostTransferOccurSum.getTaxAmt_8().subtract(scmInvMaterialReqBillEntry.getTaxAmt()));
                                            exists = true;
                                            break;                                    
                                        case 10:
                                            existScmCostTransferOccurSum.setQty_9(existScmCostTransferOccurSum.getQty_9().subtract(scmInvMaterialReqBillEntry.getQty()));
                                            existScmCostTransferOccurSum.setAmt_9(existScmCostTransferOccurSum.getAmt_9().subtract(scmInvMaterialReqBillEntry.getAmt()));
                                            existScmCostTransferOccurSum.setTaxAmt_9(existScmCostTransferOccurSum.getTaxAmt_9().subtract(scmInvMaterialReqBillEntry.getTaxAmt()));
                                            exists = true;
                                            break;                                    
                                        default:
                                            break;
                                        }
                                    }
                                }
                            }
                            if(!exists) {
                                ScmCostTransferOccurSum scmCostTransferOccurSum = new ScmCostTransferOccurSum(true);
                                scmCostTransferOccurSum.setClassName(scmInvMaterialReqBillEntry.getGroupName());
                                scmCostTransferOccurSum.setItemId(scmInvMaterialReqBillEntry.getItemId());
                                scmCostTransferOccurSum.setItemNo(scmInvMaterialReqBillEntry.getItemNo());
                                scmCostTransferOccurSum.setItemName(scmInvMaterialReqBillEntry.getItemName());
                                scmCostTransferOccurSum.setSpec(scmInvMaterialReqBillEntry.getSpec());
                                scmCostTransferOccurSum.setUnit(scmInvMaterialReqBillEntry.getUnit());
                                scmCostTransferOccurSum.setUnitName(scmInvMaterialReqBillEntry.getUnitName());
                                scmCostTransferOccurSum.setCostOrgUnitNo(scmInvMaterialReqBillEntry.getCostOrgUnitNo());
                                switch (index) {
                                case 1:
                                    scmCostTransferOccurSum.setQty(scmCostTransferOccurSum.getQty().subtract(scmInvMaterialReqBillEntry.getQty()));
                                    scmCostTransferOccurSum.setAmt(scmCostTransferOccurSum.getAmt().subtract(scmInvMaterialReqBillEntry.getAmt()));
                                    scmCostTransferOccurSum.setTaxAmt(scmCostTransferOccurSum.getTaxAmt().subtract(scmInvMaterialReqBillEntry.getTaxAmt()));
                                    break;
                                case 2:
                                    scmCostTransferOccurSum.setQty_1(scmCostTransferOccurSum.getQty_1().subtract(scmInvMaterialReqBillEntry.getQty()));
                                    scmCostTransferOccurSum.setAmt_1(scmCostTransferOccurSum.getAmt_1().subtract(scmInvMaterialReqBillEntry.getAmt()));
                                    scmCostTransferOccurSum.setTaxAmt_1(scmCostTransferOccurSum.getTaxAmt_1().subtract(scmInvMaterialReqBillEntry.getTaxAmt()));
                                    break;                                    
                                case 3:
                                    scmCostTransferOccurSum.setQty_2(scmCostTransferOccurSum.getQty_2().subtract(scmInvMaterialReqBillEntry.getQty()));
                                    scmCostTransferOccurSum.setAmt_2(scmCostTransferOccurSum.getAmt_2().subtract(scmInvMaterialReqBillEntry.getAmt()));
                                    scmCostTransferOccurSum.setTaxAmt_2(scmCostTransferOccurSum.getTaxAmt_2().subtract(scmInvMaterialReqBillEntry.getTaxAmt()));
                                    break;                                    
                                case 4:
                                    scmCostTransferOccurSum.setQty_3(scmCostTransferOccurSum.getQty_3().subtract(scmInvMaterialReqBillEntry.getQty()));
                                    scmCostTransferOccurSum.setAmt_3(scmCostTransferOccurSum.getAmt_3().subtract(scmInvMaterialReqBillEntry.getAmt()));
                                    scmCostTransferOccurSum.setTaxAmt_3(scmCostTransferOccurSum.getTaxAmt_3().subtract(scmInvMaterialReqBillEntry.getTaxAmt()));
                                    break;
                                case 5:
                                    scmCostTransferOccurSum.setQty_4(scmCostTransferOccurSum.getQty_4().subtract(scmInvMaterialReqBillEntry.getQty()));
                                    scmCostTransferOccurSum.setAmt_4(scmCostTransferOccurSum.getAmt_4().subtract(scmInvMaterialReqBillEntry.getAmt()));
                                    scmCostTransferOccurSum.setTaxAmt_4(scmCostTransferOccurSum.getTaxAmt_4().subtract(scmInvMaterialReqBillEntry.getTaxAmt()));
                                    break;                                    
                                case 6:
                                    scmCostTransferOccurSum.setQty_5(scmCostTransferOccurSum.getQty_5().subtract(scmInvMaterialReqBillEntry.getQty()));
                                    scmCostTransferOccurSum.setAmt_5(scmCostTransferOccurSum.getAmt_5().subtract(scmInvMaterialReqBillEntry.getAmt()));
                                    scmCostTransferOccurSum.setTaxAmt_5(scmCostTransferOccurSum.getTaxAmt_5().subtract(scmInvMaterialReqBillEntry.getTaxAmt()));
                                    break;                                    
                                case 7:
                                    scmCostTransferOccurSum.setQty_6(scmCostTransferOccurSum.getQty_6().subtract(scmInvMaterialReqBillEntry.getQty()));
                                    scmCostTransferOccurSum.setAmt_6(scmCostTransferOccurSum.getAmt_6().subtract(scmInvMaterialReqBillEntry.getAmt()));
                                    scmCostTransferOccurSum.setTaxAmt_6(scmCostTransferOccurSum.getTaxAmt_6().subtract(scmInvMaterialReqBillEntry.getTaxAmt()));
                                    break;                                    
                                case 8:
                                    scmCostTransferOccurSum.setQty_7(scmCostTransferOccurSum.getQty_7().subtract(scmInvMaterialReqBillEntry.getQty()));
                                    scmCostTransferOccurSum.setAmt_7(scmCostTransferOccurSum.getAmt_7().subtract(scmInvMaterialReqBillEntry.getAmt()));
                                    scmCostTransferOccurSum.setTaxAmt_7(scmCostTransferOccurSum.getTaxAmt_7().subtract(scmInvMaterialReqBillEntry.getTaxAmt()));
                                    break;                                    
                                case 9:
                                    scmCostTransferOccurSum.setQty_8(scmCostTransferOccurSum.getQty_8().subtract(scmInvMaterialReqBillEntry.getQty()));
                                    scmCostTransferOccurSum.setAmt_8(scmCostTransferOccurSum.getAmt_8().subtract(scmInvMaterialReqBillEntry.getAmt()));
                                    scmCostTransferOccurSum.setTaxAmt_8(scmCostTransferOccurSum.getTaxAmt_8().subtract(scmInvMaterialReqBillEntry.getTaxAmt()));
                                    break;                                    
                                case 10:
                                    scmCostTransferOccurSum.setQty_9(scmCostTransferOccurSum.getQty_9().subtract(scmInvMaterialReqBillEntry.getQty()));
                                    scmCostTransferOccurSum.setAmt_9(scmCostTransferOccurSum.getAmt_9().subtract(scmInvMaterialReqBillEntry.getAmt()));
                                    scmCostTransferOccurSum.setTaxAmt_9(scmCostTransferOccurSum.getTaxAmt_9().subtract(scmInvMaterialReqBillEntry.getTaxAmt()));
                                    break;                                    
                                default:
                                    break;
                                }
                                scmCostTransferOccurSumList.add(scmCostTransferOccurSum);
                            }                    
                        }
                    }
                }
            }
        }
        
        //成本转移单
        page = new Page();
        page.setModelClass(ScmInvMoveBillEntry2.class);
        page.setShowCount(Integer.MAX_VALUE);
        if(StringUtils.isNotBlank(materialClassName)) {
            page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmMaterialGroup.class)+"."+ScmMaterialGroup.FN_CLASSCODE, 
                    new QueryParam(ClassUtils.getFinalModelSimpleName(ScmMaterialGroup.class)+"."+ScmMaterialGroup.FN_CLASSCODE, QueryParam.QUERY_EQ, materialClassName));
        }
        if(StringUtils.isNotBlank(materialName)) {
            page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvMoveBillEntry2.class)+"."+ScmInvMoveBillEntry2.FN_ITEMID, 
                    new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMoveBillEntry2.class)+"."+ScmInvMoveBillEntry2.FN_ITEMID, QueryParam.QUERY_EQ, materialName));
        }
        page.getParamOr().put(ClassUtils.getFinalModelSimpleName(ScmInvMoveBill.class)+"."+ScmInvMoveBill.FN_OUTCSTORGUNITNO, 
                new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMoveBill.class)+"."+ScmInvMoveBill.FN_OUTCSTORGUNITNO, QueryParam.QUERY_IN, costOrgUnitNos.toString()));
        page.getParamOr().put(ClassUtils.getFinalModelSimpleName(ScmInvMoveBill.class)+"."+ScmInvMoveBill.FN_INCSTORGUNITNO, 
                new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMoveBill.class)+"."+ScmInvMoveBill.FN_INCSTORGUNITNO, QueryParam.QUERY_EQ, costOrgUnitNo));
        page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvMoveBill.class)+"."+ScmInvMoveBill.FN_BIZDATE, 
                new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMoveBill.class)+"."+ScmInvMoveBill.FN_BIZDATE, QueryParam.QUERY_BETWEEN, beginDate,endDate));
        page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvMoveBill.class)+"."+ScmInvMoveBill.FN_STATUS, 
                new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMoveBill.class)+"."+ScmInvMoveBill.FN_STATUS, QueryParam.QUERY_EQ, "E"));
        if (StringUtils.equals("Y", moveIn) || StringUtils.equals("Y", moveOut) || StringUtils.equals("Y", costConsume2)) {
            List<ScmInvMoveBillEntry2> scmInvMoveBillEntryList = scmInvMoveBillEntryBiz.queryPage(page, arglist, "findAllPage", param);
            if(scmInvMoveBillEntryList!=null && !scmInvMoveBillEntryList.isEmpty()) {
                List<ScmInvMoveBillEntry2> scmInvMoveBillEntryListIn = new ArrayList<ScmInvMoveBillEntry2>(); 
                List<ScmInvMoveBillEntry2> scmInvMoveBillEntryListOut = new ArrayList<ScmInvMoveBillEntry2>(); 
                for (ScmInvMoveBillEntry2 scmInvMoveBillEntry : scmInvMoveBillEntryList) {
                    if (StringUtils.contains(costOrgUnitNos.toString(), scmInvMoveBillEntry.getInCstOrgUnitNo())) {//入
                        scmInvMoveBillEntryListIn.add(scmInvMoveBillEntry);
                    }
                    if (StringUtils.contains(costOrgUnitNos.toString(), scmInvMoveBillEntry.getOutCstOrgUnitNo())) {//出
                        scmInvMoveBillEntryListOut.add(scmInvMoveBillEntry);
                    }
                }
                if (scmInvMoveBillEntryListIn != null && scmInvMoveBillEntryListIn.size() > 0) {
                    if (StringUtils.equals("Y", moveIn)) {
                        intervalBizType.add(index, "成本转移单入");
                        index = index + 1;
                        for (ScmInvMoveBillEntry2 scmInvMoveBillEntry : scmInvMoveBillEntryListIn) {
                            boolean exists=false;
                            if(!scmCostTransferOccurSumList.isEmpty()) {
                                for (ScmCostTransferOccurSum existScmCostTransferOccurSum:scmCostTransferOccurSumList) {
                                    if (existScmCostTransferOccurSum.getItemId() == scmInvMoveBillEntry.getItemId() && StringUtils.equals(
                                            existScmCostTransferOccurSum.getCostOrgUnitNo(), scmInvMoveBillEntry.getInCstOrgUnitNo())) {
                                        switch (index) {
                                        case 1:
                                            existScmCostTransferOccurSum.setQty(existScmCostTransferOccurSum.getQty().add(scmInvMoveBillEntry.getQty()));
                                            existScmCostTransferOccurSum.setAmt(existScmCostTransferOccurSum.getAmt().add(scmInvMoveBillEntry.getAmt()));
                                            existScmCostTransferOccurSum.setTaxAmt(existScmCostTransferOccurSum.getTaxAmt().add(scmInvMoveBillEntry.getTaxAmt()));
                                            exists = true;
                                            break;
                                        case 2:
                                            existScmCostTransferOccurSum.setQty_1(existScmCostTransferOccurSum.getQty_1().add(scmInvMoveBillEntry.getQty()));
                                            existScmCostTransferOccurSum.setAmt_1(existScmCostTransferOccurSum.getAmt_1().add(scmInvMoveBillEntry.getAmt()));
                                            existScmCostTransferOccurSum.setTaxAmt_1(existScmCostTransferOccurSum.getTaxAmt_1().add(scmInvMoveBillEntry.getTaxAmt()));
                                            exists = true;
                                            break;
                                        case 3:
                                            existScmCostTransferOccurSum.setQty_2(existScmCostTransferOccurSum.getQty_2().add(scmInvMoveBillEntry.getQty()));
                                            existScmCostTransferOccurSum.setAmt_2(existScmCostTransferOccurSum.getAmt_2().add(scmInvMoveBillEntry.getAmt()));
                                            existScmCostTransferOccurSum.setTaxAmt_2(existScmCostTransferOccurSum.getTaxAmt_2().add(scmInvMoveBillEntry.getTaxAmt()));
                                            exists = true;
                                            break;                                    
                                        case 4:
                                            existScmCostTransferOccurSum.setQty_3(existScmCostTransferOccurSum.getQty_3().add(scmInvMoveBillEntry.getQty()));
                                            existScmCostTransferOccurSum.setAmt_3(existScmCostTransferOccurSum.getAmt_3().add(scmInvMoveBillEntry.getAmt()));
                                            existScmCostTransferOccurSum.setTaxAmt_3(existScmCostTransferOccurSum.getTaxAmt_3().add(scmInvMoveBillEntry.getTaxAmt()));
                                            exists = true;
                                            break;                                    
                                        case 5:
                                            existScmCostTransferOccurSum.setQty_4(existScmCostTransferOccurSum.getQty_4().add(scmInvMoveBillEntry.getQty()));
                                            existScmCostTransferOccurSum.setAmt_4(existScmCostTransferOccurSum.getAmt_4().add(scmInvMoveBillEntry.getAmt()));
                                            existScmCostTransferOccurSum.setTaxAmt_4(existScmCostTransferOccurSum.getTaxAmt_4().add(scmInvMoveBillEntry.getTaxAmt()));
                                            exists = true;
                                            break;
                                        case 6:
                                            existScmCostTransferOccurSum.setQty_5(existScmCostTransferOccurSum.getQty_5().add(scmInvMoveBillEntry.getQty()));
                                            existScmCostTransferOccurSum.setAmt_5(existScmCostTransferOccurSum.getAmt_5().add(scmInvMoveBillEntry.getAmt()));
                                            existScmCostTransferOccurSum.setTaxAmt_5(existScmCostTransferOccurSum.getTaxAmt_5().add(scmInvMoveBillEntry.getTaxAmt()));
                                            exists = true;
                                            break;                                    
                                        case 7:
                                            existScmCostTransferOccurSum.setQty_6(existScmCostTransferOccurSum.getQty_6().add(scmInvMoveBillEntry.getQty()));
                                            existScmCostTransferOccurSum.setAmt_6(existScmCostTransferOccurSum.getAmt_6().add(scmInvMoveBillEntry.getAmt()));
                                            existScmCostTransferOccurSum.setTaxAmt_6(existScmCostTransferOccurSum.getTaxAmt_6().add(scmInvMoveBillEntry.getTaxAmt()));
                                            exists = true;
                                            break;                                    
                                        case 8:
                                            existScmCostTransferOccurSum.setQty_7(existScmCostTransferOccurSum.getQty_7().add(scmInvMoveBillEntry.getQty()));
                                            existScmCostTransferOccurSum.setAmt_7(existScmCostTransferOccurSum.getAmt_7().add(scmInvMoveBillEntry.getAmt()));
                                            existScmCostTransferOccurSum.setTaxAmt_7(existScmCostTransferOccurSum.getTaxAmt_7().add(scmInvMoveBillEntry.getTaxAmt()));
                                            exists = true;
                                            break;                                    
                                        case 9:
                                            existScmCostTransferOccurSum.setQty_8(existScmCostTransferOccurSum.getQty_8().add(scmInvMoveBillEntry.getQty()));
                                            existScmCostTransferOccurSum.setAmt_8(existScmCostTransferOccurSum.getAmt_8().add(scmInvMoveBillEntry.getAmt()));
                                            existScmCostTransferOccurSum.setTaxAmt_8(existScmCostTransferOccurSum.getTaxAmt_8().add(scmInvMoveBillEntry.getTaxAmt()));
                                            exists = true;
                                            break;                                    
                                        case 10:
                                            existScmCostTransferOccurSum.setQty_9(existScmCostTransferOccurSum.getQty_9().add(scmInvMoveBillEntry.getQty()));
                                            existScmCostTransferOccurSum.setAmt_9(existScmCostTransferOccurSum.getAmt_9().add(scmInvMoveBillEntry.getAmt()));
                                            existScmCostTransferOccurSum.setTaxAmt_9(existScmCostTransferOccurSum.getTaxAmt_9().add(scmInvMoveBillEntry.getTaxAmt()));
                                            exists = true;
                                            break;                                    
                                        default:
                                            break;
                                        }
                                    }
                                }
                            }
                            if(!exists) {
                                ScmCostTransferOccurSum scmCostTransferOccurSum = new ScmCostTransferOccurSum(true);
                                scmCostTransferOccurSum.setClassName(scmInvMoveBillEntry.getGroupName());
                                scmCostTransferOccurSum.setItemId(scmInvMoveBillEntry.getItemId());
                                scmCostTransferOccurSum.setItemNo(scmInvMoveBillEntry.getItemNo());
                                scmCostTransferOccurSum.setItemName(scmInvMoveBillEntry.getItemName());
                                scmCostTransferOccurSum.setSpec(scmInvMoveBillEntry.getSpec());
                                scmCostTransferOccurSum.setUnit(scmInvMoveBillEntry.getUnit());
                                scmCostTransferOccurSum.setUnitName(scmInvMoveBillEntry.getUnitName());
                                scmCostTransferOccurSum.setCostOrgUnitNo(scmInvMoveBillEntry.getInCstOrgUnitNo());
                                switch (index) {
                                case 1:
                                    scmCostTransferOccurSum.setQty(scmCostTransferOccurSum.getQty().add(scmInvMoveBillEntry.getQty()));
                                    scmCostTransferOccurSum.setAmt(scmCostTransferOccurSum.getAmt().add(scmInvMoveBillEntry.getAmt()));
                                    scmCostTransferOccurSum.setTaxAmt(scmCostTransferOccurSum.getTaxAmt().add(scmInvMoveBillEntry.getTaxAmt()));
                                    break;
                                case 2:
                                    scmCostTransferOccurSum.setQty_1(scmCostTransferOccurSum.getQty_1().add(scmInvMoveBillEntry.getQty()));
                                    scmCostTransferOccurSum.setAmt_1(scmCostTransferOccurSum.getAmt_1().add(scmInvMoveBillEntry.getAmt()));
                                    scmCostTransferOccurSum.setTaxAmt_1(scmCostTransferOccurSum.getTaxAmt_1().add(scmInvMoveBillEntry.getTaxAmt()));
                                    break;                                    
                                case 3:
                                    scmCostTransferOccurSum.setQty_2(scmCostTransferOccurSum.getQty_2().add(scmInvMoveBillEntry.getQty()));
                                    scmCostTransferOccurSum.setAmt_2(scmCostTransferOccurSum.getAmt_2().add(scmInvMoveBillEntry.getAmt()));
                                    scmCostTransferOccurSum.setTaxAmt_2(scmCostTransferOccurSum.getTaxAmt_2().add(scmInvMoveBillEntry.getTaxAmt()));
                                    break;                                    
                                case 4:
                                    scmCostTransferOccurSum.setQty_3(scmCostTransferOccurSum.getQty_3().add(scmInvMoveBillEntry.getQty()));
                                    scmCostTransferOccurSum.setAmt_3(scmCostTransferOccurSum.getAmt_3().add(scmInvMoveBillEntry.getAmt()));
                                    scmCostTransferOccurSum.setTaxAmt_3(scmCostTransferOccurSum.getTaxAmt_3().add(scmInvMoveBillEntry.getTaxAmt()));
                                    break;
                                case 5:
                                    scmCostTransferOccurSum.setQty_4(scmCostTransferOccurSum.getQty_4().add(scmInvMoveBillEntry.getQty()));
                                    scmCostTransferOccurSum.setAmt_4(scmCostTransferOccurSum.getAmt_4().add(scmInvMoveBillEntry.getAmt()));
                                    scmCostTransferOccurSum.setTaxAmt_4(scmCostTransferOccurSum.getTaxAmt_4().add(scmInvMoveBillEntry.getTaxAmt()));
                                    break;                                    
                                case 6:
                                    scmCostTransferOccurSum.setQty_5(scmCostTransferOccurSum.getQty_5().add(scmInvMoveBillEntry.getQty()));
                                    scmCostTransferOccurSum.setAmt_5(scmCostTransferOccurSum.getAmt_5().add(scmInvMoveBillEntry.getAmt()));
                                    scmCostTransferOccurSum.setTaxAmt_5(scmCostTransferOccurSum.getTaxAmt_5().add(scmInvMoveBillEntry.getTaxAmt()));
                                    break;                                    
                                case 7:
                                    scmCostTransferOccurSum.setQty_6(scmCostTransferOccurSum.getQty_6().add(scmInvMoveBillEntry.getQty()));
                                    scmCostTransferOccurSum.setAmt_6(scmCostTransferOccurSum.getAmt_6().add(scmInvMoveBillEntry.getAmt()));
                                    scmCostTransferOccurSum.setTaxAmt_6(scmCostTransferOccurSum.getTaxAmt_6().add(scmInvMoveBillEntry.getTaxAmt()));
                                    break;                                    
                                case 8:
                                    scmCostTransferOccurSum.setQty_7(scmCostTransferOccurSum.getQty_7().add(scmInvMoveBillEntry.getQty()));
                                    scmCostTransferOccurSum.setAmt_7(scmCostTransferOccurSum.getAmt_7().add(scmInvMoveBillEntry.getAmt()));
                                    scmCostTransferOccurSum.setTaxAmt_7(scmCostTransferOccurSum.getTaxAmt_7().add(scmInvMoveBillEntry.getTaxAmt()));
                                    break;                                    
                                case 9:
                                    scmCostTransferOccurSum.setQty_8(scmCostTransferOccurSum.getQty_8().add(scmInvMoveBillEntry.getQty()));
                                    scmCostTransferOccurSum.setAmt_8(scmCostTransferOccurSum.getAmt_8().add(scmInvMoveBillEntry.getAmt()));
                                    scmCostTransferOccurSum.setTaxAmt_8(scmCostTransferOccurSum.getTaxAmt_8().add(scmInvMoveBillEntry.getTaxAmt()));
                                    break;                                    
                                case 10:
                                    scmCostTransferOccurSum.setQty_9(scmCostTransferOccurSum.getQty_9().add(scmInvMoveBillEntry.getQty()));
                                    scmCostTransferOccurSum.setAmt_9(scmCostTransferOccurSum.getAmt_9().add(scmInvMoveBillEntry.getAmt()));
                                    scmCostTransferOccurSum.setTaxAmt_9(scmCostTransferOccurSum.getTaxAmt_9().add(scmInvMoveBillEntry.getTaxAmt()));
                                    break;                                    
                                default:
                                    break;
                                }
                                scmCostTransferOccurSumList.add(scmCostTransferOccurSum);
                            }  
                        }
                    }
                    // 以领代耗
                    if (StringUtils.equals("Y", costConsume2)) {
                    	int existIndex=-1;
                    	if(!intervalBizType.isEmpty()) {
                    		for(int i=0;i<intervalBizType.size();i++) {
                    			if(StringUtils.equals("以领代耗", intervalBizType.get(i))) {
                    				existIndex=i;
                    				break;
                    			}
                    		}
                    	}
                    	if(existIndex==-1) {
	                        intervalBizType.add(index, "以领代耗");
	                        index = index + 1;
	                        existIndex=index;
                    	}else {
                    		existIndex = existIndex + 1;
                    	}
                    	 for (ScmInvMoveBillEntry2 scmInvMoveBillEntry : scmInvMoveBillEntryListIn) {
                        	OrgCostCenter2 orgCostCenter = orgCostCenterBiz.selectByOrgUnitNo(scmInvMoveBillEntry.getInCstOrgUnitNo(), param);
                        	if(orgCostCenter!=null && StringUtils.equals("2", orgCostCenter.getCostCenterType())) {
    							continue;
    						}
                            boolean exists=false;
                            if(!scmCostTransferOccurSumList.isEmpty()) {
                            	for (ScmCostTransferOccurSum existScmCostTransferOccurSum:scmCostTransferOccurSumList) {
                                    if (existScmCostTransferOccurSum.getItemId() == scmInvMoveBillEntry.getItemId() && StringUtils.equals(
                                            existScmCostTransferOccurSum.getCostOrgUnitNo(), scmInvMoveBillEntry.getInCstOrgUnitNo())) {
                                        switch (existIndex) {
                                        case 1:
                                            existScmCostTransferOccurSum.setQty(existScmCostTransferOccurSum.getQty().add(scmInvMoveBillEntry.getQty()));
                                            existScmCostTransferOccurSum.setAmt(existScmCostTransferOccurSum.getAmt().add(scmInvMoveBillEntry.getAmt()));
                                            existScmCostTransferOccurSum.setTaxAmt(existScmCostTransferOccurSum.getTaxAmt().add(scmInvMoveBillEntry.getTaxAmt()));
                                            exists = true;
                                            break;
                                        case 2:
                                            existScmCostTransferOccurSum.setQty_1(existScmCostTransferOccurSum.getQty_1().add(scmInvMoveBillEntry.getQty()));
                                            existScmCostTransferOccurSum.setAmt_1(existScmCostTransferOccurSum.getAmt_1().add(scmInvMoveBillEntry.getAmt()));
                                            existScmCostTransferOccurSum.setTaxAmt_1(existScmCostTransferOccurSum.getTaxAmt_1().add(scmInvMoveBillEntry.getTaxAmt()));
                                            exists = true;
                                            break;
                                        case 3:
                                            existScmCostTransferOccurSum.setQty_2(existScmCostTransferOccurSum.getQty_2().add(scmInvMoveBillEntry.getQty()));
                                            existScmCostTransferOccurSum.setAmt_2(existScmCostTransferOccurSum.getAmt_2().add(scmInvMoveBillEntry.getAmt()));
                                            existScmCostTransferOccurSum.setTaxAmt_2(existScmCostTransferOccurSum.getTaxAmt_2().add(scmInvMoveBillEntry.getTaxAmt()));
                                            exists = true;
                                            break;                                    
                                        case 4:
                                            existScmCostTransferOccurSum.setQty_3(existScmCostTransferOccurSum.getQty_3().add(scmInvMoveBillEntry.getQty()));
                                            existScmCostTransferOccurSum.setAmt_3(existScmCostTransferOccurSum.getAmt_3().add(scmInvMoveBillEntry.getAmt()));
                                            existScmCostTransferOccurSum.setTaxAmt_3(existScmCostTransferOccurSum.getTaxAmt_3().add(scmInvMoveBillEntry.getTaxAmt()));
                                            exists = true;
                                            break;                                    
                                        case 5:
                                            existScmCostTransferOccurSum.setQty_4(existScmCostTransferOccurSum.getQty_4().add(scmInvMoveBillEntry.getQty()));
                                            existScmCostTransferOccurSum.setAmt_4(existScmCostTransferOccurSum.getAmt_4().add(scmInvMoveBillEntry.getAmt()));
                                            existScmCostTransferOccurSum.setTaxAmt_4(existScmCostTransferOccurSum.getTaxAmt_4().add(scmInvMoveBillEntry.getTaxAmt()));
                                            exists = true;
                                            break;
                                        case 6:
                                            existScmCostTransferOccurSum.setQty_5(existScmCostTransferOccurSum.getQty_5().add(scmInvMoveBillEntry.getQty()));
                                            existScmCostTransferOccurSum.setAmt_5(existScmCostTransferOccurSum.getAmt_5().add(scmInvMoveBillEntry.getAmt()));
                                            existScmCostTransferOccurSum.setTaxAmt_5(existScmCostTransferOccurSum.getTaxAmt_5().add(scmInvMoveBillEntry.getTaxAmt()));
                                            exists = true;
                                            break;                                    
                                        case 7:
                                            existScmCostTransferOccurSum.setQty_6(existScmCostTransferOccurSum.getQty_6().add(scmInvMoveBillEntry.getQty()));
                                            existScmCostTransferOccurSum.setAmt_6(existScmCostTransferOccurSum.getAmt_6().add(scmInvMoveBillEntry.getAmt()));
                                            existScmCostTransferOccurSum.setTaxAmt_6(existScmCostTransferOccurSum.getTaxAmt_6().add(scmInvMoveBillEntry.getTaxAmt()));
                                            exists = true;
                                            break;                                    
                                        case 8:
                                            existScmCostTransferOccurSum.setQty_7(existScmCostTransferOccurSum.getQty_7().add(scmInvMoveBillEntry.getQty()));
                                            existScmCostTransferOccurSum.setAmt_7(existScmCostTransferOccurSum.getAmt_7().add(scmInvMoveBillEntry.getAmt()));
                                            existScmCostTransferOccurSum.setTaxAmt_7(existScmCostTransferOccurSum.getTaxAmt_7().add(scmInvMoveBillEntry.getTaxAmt()));
                                            exists = true;
                                            break;                                    
                                        case 9:
                                            existScmCostTransferOccurSum.setQty_8(existScmCostTransferOccurSum.getQty_8().add(scmInvMoveBillEntry.getQty()));
                                            existScmCostTransferOccurSum.setAmt_8(existScmCostTransferOccurSum.getAmt_8().add(scmInvMoveBillEntry.getAmt()));
                                            existScmCostTransferOccurSum.setTaxAmt_8(existScmCostTransferOccurSum.getTaxAmt_8().add(scmInvMoveBillEntry.getTaxAmt()));
                                            exists = true;
                                            break;                                    
                                        case 10:
                                            existScmCostTransferOccurSum.setQty_9(existScmCostTransferOccurSum.getQty_9().add(scmInvMoveBillEntry.getQty()));
                                            existScmCostTransferOccurSum.setAmt_9(existScmCostTransferOccurSum.getAmt_9().add(scmInvMoveBillEntry.getAmt()));
                                            existScmCostTransferOccurSum.setTaxAmt_9(existScmCostTransferOccurSum.getTaxAmt_9().add(scmInvMoveBillEntry.getTaxAmt()));
                                            exists = true;
                                            break;                                    
                                        default:
                                            break;
                                        }
                                    }
                                }
                            }
                            if(!exists) {
                                ScmCostTransferOccurSum scmCostTransferOccurSum = new ScmCostTransferOccurSum(true);
                                scmCostTransferOccurSum.setClassName(scmInvMoveBillEntry.getGroupName());
                                scmCostTransferOccurSum.setItemId(scmInvMoveBillEntry.getItemId());
                                scmCostTransferOccurSum.setItemNo(scmInvMoveBillEntry.getItemNo());
                                scmCostTransferOccurSum.setItemName(scmInvMoveBillEntry.getItemName());
                                scmCostTransferOccurSum.setSpec(scmInvMoveBillEntry.getSpec());
                                scmCostTransferOccurSum.setUnit(scmInvMoveBillEntry.getUnit());
                                scmCostTransferOccurSum.setUnitName(scmInvMoveBillEntry.getUnitName());
                                scmCostTransferOccurSum.setCostOrgUnitNo(scmInvMoveBillEntry.getInCstOrgUnitNo());
                                switch (index) {
                                case 1:
                                    scmCostTransferOccurSum.setQty(scmCostTransferOccurSum.getQty().add(scmInvMoveBillEntry.getQty()));
                                    scmCostTransferOccurSum.setAmt(scmCostTransferOccurSum.getAmt().add(scmInvMoveBillEntry.getAmt()));
                                    scmCostTransferOccurSum.setTaxAmt(scmCostTransferOccurSum.getTaxAmt().add(scmInvMoveBillEntry.getTaxAmt()));
                                    break;
                                case 2:
                                    scmCostTransferOccurSum.setQty_1(scmCostTransferOccurSum.getQty_1().add(scmInvMoveBillEntry.getQty()));
                                    scmCostTransferOccurSum.setAmt_1(scmCostTransferOccurSum.getAmt_1().add(scmInvMoveBillEntry.getAmt()));
                                    scmCostTransferOccurSum.setTaxAmt_1(scmCostTransferOccurSum.getTaxAmt_1().add(scmInvMoveBillEntry.getTaxAmt()));
                                    break;                                    
                                case 3:
                                    scmCostTransferOccurSum.setQty_2(scmCostTransferOccurSum.getQty_2().add(scmInvMoveBillEntry.getQty()));
                                    scmCostTransferOccurSum.setAmt_2(scmCostTransferOccurSum.getAmt_2().add(scmInvMoveBillEntry.getAmt()));
                                    scmCostTransferOccurSum.setTaxAmt_2(scmCostTransferOccurSum.getTaxAmt_2().add(scmInvMoveBillEntry.getTaxAmt()));
                                    break;                                    
                                case 4:
                                    scmCostTransferOccurSum.setQty_3(scmCostTransferOccurSum.getQty_3().add(scmInvMoveBillEntry.getQty()));
                                    scmCostTransferOccurSum.setAmt_3(scmCostTransferOccurSum.getAmt_3().add(scmInvMoveBillEntry.getAmt()));
                                    scmCostTransferOccurSum.setTaxAmt_3(scmCostTransferOccurSum.getTaxAmt_3().add(scmInvMoveBillEntry.getTaxAmt()));
                                    break;
                                case 5:
                                    scmCostTransferOccurSum.setQty_4(scmCostTransferOccurSum.getQty_4().add(scmInvMoveBillEntry.getQty()));
                                    scmCostTransferOccurSum.setAmt_4(scmCostTransferOccurSum.getAmt_4().add(scmInvMoveBillEntry.getAmt()));
                                    scmCostTransferOccurSum.setTaxAmt_4(scmCostTransferOccurSum.getTaxAmt_4().add(scmInvMoveBillEntry.getTaxAmt()));
                                    break;                                    
                                case 6:
                                    scmCostTransferOccurSum.setQty_5(scmCostTransferOccurSum.getQty_5().add(scmInvMoveBillEntry.getQty()));
                                    scmCostTransferOccurSum.setAmt_5(scmCostTransferOccurSum.getAmt_5().add(scmInvMoveBillEntry.getAmt()));
                                    scmCostTransferOccurSum.setTaxAmt_5(scmCostTransferOccurSum.getTaxAmt_5().add(scmInvMoveBillEntry.getTaxAmt()));
                                    break;                                    
                                case 7:
                                    scmCostTransferOccurSum.setQty_6(scmCostTransferOccurSum.getQty_6().add(scmInvMoveBillEntry.getQty()));
                                    scmCostTransferOccurSum.setAmt_6(scmCostTransferOccurSum.getAmt_6().add(scmInvMoveBillEntry.getAmt()));
                                    scmCostTransferOccurSum.setTaxAmt_6(scmCostTransferOccurSum.getTaxAmt_6().add(scmInvMoveBillEntry.getTaxAmt()));
                                    break;                                    
                                case 8:
                                    scmCostTransferOccurSum.setQty_7(scmCostTransferOccurSum.getQty_7().add(scmInvMoveBillEntry.getQty()));
                                    scmCostTransferOccurSum.setAmt_7(scmCostTransferOccurSum.getAmt_7().add(scmInvMoveBillEntry.getAmt()));
                                    scmCostTransferOccurSum.setTaxAmt_7(scmCostTransferOccurSum.getTaxAmt_7().add(scmInvMoveBillEntry.getTaxAmt()));
                                    break;                                    
                                case 9:
                                    scmCostTransferOccurSum.setQty_8(scmCostTransferOccurSum.getQty_8().add(scmInvMoveBillEntry.getQty()));
                                    scmCostTransferOccurSum.setAmt_8(scmCostTransferOccurSum.getAmt_8().add(scmInvMoveBillEntry.getAmt()));
                                    scmCostTransferOccurSum.setTaxAmt_8(scmCostTransferOccurSum.getTaxAmt_8().add(scmInvMoveBillEntry.getTaxAmt()));
                                    break;                                    
                                case 10:
                                    scmCostTransferOccurSum.setQty_9(scmCostTransferOccurSum.getQty_9().add(scmInvMoveBillEntry.getQty()));
                                    scmCostTransferOccurSum.setAmt_9(scmCostTransferOccurSum.getAmt_9().add(scmInvMoveBillEntry.getAmt()));
                                    scmCostTransferOccurSum.setTaxAmt_9(scmCostTransferOccurSum.getTaxAmt_9().add(scmInvMoveBillEntry.getTaxAmt()));
                                    break;                                    
                                default:
                                    break;
                                }
                                scmCostTransferOccurSumList.add(scmCostTransferOccurSum);
                            }                    
                        }
                    }
                }
                if (scmInvMoveBillEntryListOut != null && scmInvMoveBillEntryListOut.size() > 0) {
                    if (StringUtils.equals("Y", moveOut)) {
                        intervalBizType.add(index, "成本转移单出");
                        index = index + 1;
                        for (ScmInvMoveBillEntry2 scmInvMoveBillEntry : scmInvMoveBillEntryListOut) {
                            boolean exists=false;
                            if(!scmCostTransferOccurSumList.isEmpty()) {
                                for (ScmCostTransferOccurSum existScmCostTransferOccurSum:scmCostTransferOccurSumList) {
                                    if (existScmCostTransferOccurSum.getItemId() == scmInvMoveBillEntry.getItemId() && StringUtils.equals(
                                            existScmCostTransferOccurSum.getCostOrgUnitNo(), scmInvMoveBillEntry.getOutCstOrgUnitNo())) {
                                        switch (index) {
                                        case 1:
                                            existScmCostTransferOccurSum.setQty(existScmCostTransferOccurSum.getQty().add(scmInvMoveBillEntry.getQty()));
                                            existScmCostTransferOccurSum.setAmt(existScmCostTransferOccurSum.getAmt().add(scmInvMoveBillEntry.getAmt()));
                                            existScmCostTransferOccurSum.setTaxAmt(existScmCostTransferOccurSum.getTaxAmt().add(scmInvMoveBillEntry.getTaxAmt()));
                                            exists = true;
                                            break;
                                        case 2:
                                            existScmCostTransferOccurSum.setQty_1(existScmCostTransferOccurSum.getQty_1().add(scmInvMoveBillEntry.getQty()));
                                            existScmCostTransferOccurSum.setAmt_1(existScmCostTransferOccurSum.getAmt_1().add(scmInvMoveBillEntry.getAmt()));
                                            existScmCostTransferOccurSum.setTaxAmt_1(existScmCostTransferOccurSum.getTaxAmt_1().add(scmInvMoveBillEntry.getTaxAmt()));
                                            exists = true;
                                            break;
                                        case 3:
                                            existScmCostTransferOccurSum.setQty_2(existScmCostTransferOccurSum.getQty_2().add(scmInvMoveBillEntry.getQty()));
                                            existScmCostTransferOccurSum.setAmt_2(existScmCostTransferOccurSum.getAmt_2().add(scmInvMoveBillEntry.getAmt()));
                                            existScmCostTransferOccurSum.setTaxAmt_2(existScmCostTransferOccurSum.getTaxAmt_2().add(scmInvMoveBillEntry.getTaxAmt()));
                                            exists = true;
                                            break;                                    
                                        case 4:
                                            existScmCostTransferOccurSum.setQty_3(existScmCostTransferOccurSum.getQty_3().add(scmInvMoveBillEntry.getQty()));
                                            existScmCostTransferOccurSum.setAmt_3(existScmCostTransferOccurSum.getAmt_3().add(scmInvMoveBillEntry.getAmt()));
                                            existScmCostTransferOccurSum.setTaxAmt_3(existScmCostTransferOccurSum.getTaxAmt_3().add(scmInvMoveBillEntry.getTaxAmt()));
                                            exists = true;
                                            break;                                    
                                        case 5:
                                            existScmCostTransferOccurSum.setQty_4(existScmCostTransferOccurSum.getQty_4().add(scmInvMoveBillEntry.getQty()));
                                            existScmCostTransferOccurSum.setAmt_4(existScmCostTransferOccurSum.getAmt_4().add(scmInvMoveBillEntry.getAmt()));
                                            existScmCostTransferOccurSum.setTaxAmt_4(existScmCostTransferOccurSum.getTaxAmt_4().add(scmInvMoveBillEntry.getTaxAmt()));
                                            exists = true;
                                            break;
                                        case 6:
                                            existScmCostTransferOccurSum.setQty_5(existScmCostTransferOccurSum.getQty_5().add(scmInvMoveBillEntry.getQty()));
                                            existScmCostTransferOccurSum.setAmt_5(existScmCostTransferOccurSum.getAmt_5().add(scmInvMoveBillEntry.getAmt()));
                                            existScmCostTransferOccurSum.setTaxAmt_5(existScmCostTransferOccurSum.getTaxAmt_5().add(scmInvMoveBillEntry.getTaxAmt()));
                                            exists = true;
                                            break;                                    
                                        case 7:
                                            existScmCostTransferOccurSum.setQty_6(existScmCostTransferOccurSum.getQty_6().add(scmInvMoveBillEntry.getQty()));
                                            existScmCostTransferOccurSum.setAmt_6(existScmCostTransferOccurSum.getAmt_6().add(scmInvMoveBillEntry.getAmt()));
                                            existScmCostTransferOccurSum.setTaxAmt_6(existScmCostTransferOccurSum.getTaxAmt_6().add(scmInvMoveBillEntry.getTaxAmt()));
                                            exists = true;
                                            break;                                    
                                        case 8:
                                            existScmCostTransferOccurSum.setQty_7(existScmCostTransferOccurSum.getQty_7().add(scmInvMoveBillEntry.getQty()));
                                            existScmCostTransferOccurSum.setAmt_7(existScmCostTransferOccurSum.getAmt_7().add(scmInvMoveBillEntry.getAmt()));
                                            existScmCostTransferOccurSum.setTaxAmt_7(existScmCostTransferOccurSum.getTaxAmt_7().add(scmInvMoveBillEntry.getTaxAmt()));
                                            exists = true;
                                            break;                                    
                                        case 9:
                                            existScmCostTransferOccurSum.setQty_8(existScmCostTransferOccurSum.getQty_8().add(scmInvMoveBillEntry.getQty()));
                                            existScmCostTransferOccurSum.setAmt_8(existScmCostTransferOccurSum.getAmt_8().add(scmInvMoveBillEntry.getAmt()));
                                            existScmCostTransferOccurSum.setTaxAmt_8(existScmCostTransferOccurSum.getTaxAmt_8().add(scmInvMoveBillEntry.getTaxAmt()));
                                            exists = true;
                                            break;                                    
                                        case 10:
                                            existScmCostTransferOccurSum.setQty_9(existScmCostTransferOccurSum.getQty_9().add(scmInvMoveBillEntry.getQty()));
                                            existScmCostTransferOccurSum.setAmt_9(existScmCostTransferOccurSum.getAmt_9().add(scmInvMoveBillEntry.getAmt()));
                                            existScmCostTransferOccurSum.setTaxAmt_9(existScmCostTransferOccurSum.getTaxAmt_9().add(scmInvMoveBillEntry.getTaxAmt()));
                                            exists = true;
                                            break;                                    
                                        default:
                                            break;
                                        }
                                    }
                                }
                            }
                            if(!exists) {
                                ScmCostTransferOccurSum scmCostTransferOccurSum = new ScmCostTransferOccurSum(true);
                                scmCostTransferOccurSum.setClassName(scmInvMoveBillEntry.getGroupName());
                                scmCostTransferOccurSum.setItemId(scmInvMoveBillEntry.getItemId());
                                scmCostTransferOccurSum.setItemNo(scmInvMoveBillEntry.getItemNo());
                                scmCostTransferOccurSum.setItemName(scmInvMoveBillEntry.getItemName());
                                scmCostTransferOccurSum.setSpec(scmInvMoveBillEntry.getSpec());
                                scmCostTransferOccurSum.setUnit(scmInvMoveBillEntry.getUnit());
                                scmCostTransferOccurSum.setUnitName(scmInvMoveBillEntry.getUnitName());
                                scmCostTransferOccurSum.setCostOrgUnitNo(scmInvMoveBillEntry.getOutCstOrgUnitNo());
                                switch (index) {
                                case 1:
                                    scmCostTransferOccurSum.setQty(scmCostTransferOccurSum.getQty().add(scmInvMoveBillEntry.getQty()));
                                    scmCostTransferOccurSum.setAmt(scmCostTransferOccurSum.getAmt().add(scmInvMoveBillEntry.getAmt()));
                                    scmCostTransferOccurSum.setTaxAmt(scmCostTransferOccurSum.getTaxAmt().add(scmInvMoveBillEntry.getTaxAmt()));
                                    break;
                                case 2:
                                    scmCostTransferOccurSum.setQty_1(scmCostTransferOccurSum.getQty_1().add(scmInvMoveBillEntry.getQty()));
                                    scmCostTransferOccurSum.setAmt_1(scmCostTransferOccurSum.getAmt_1().add(scmInvMoveBillEntry.getAmt()));
                                    scmCostTransferOccurSum.setTaxAmt_1(scmCostTransferOccurSum.getTaxAmt_1().add(scmInvMoveBillEntry.getTaxAmt()));
                                    break;                                    
                                case 3:
                                    scmCostTransferOccurSum.setQty_2(scmCostTransferOccurSum.getQty_2().add(scmInvMoveBillEntry.getQty()));
                                    scmCostTransferOccurSum.setAmt_2(scmCostTransferOccurSum.getAmt_2().add(scmInvMoveBillEntry.getAmt()));
                                    scmCostTransferOccurSum.setTaxAmt_2(scmCostTransferOccurSum.getTaxAmt_2().add(scmInvMoveBillEntry.getTaxAmt()));
                                    break;                                    
                                case 4:
                                    scmCostTransferOccurSum.setQty_3(scmCostTransferOccurSum.getQty_3().add(scmInvMoveBillEntry.getQty()));
                                    scmCostTransferOccurSum.setAmt_3(scmCostTransferOccurSum.getAmt_3().add(scmInvMoveBillEntry.getAmt()));
                                    scmCostTransferOccurSum.setTaxAmt_3(scmCostTransferOccurSum.getTaxAmt_3().add(scmInvMoveBillEntry.getTaxAmt()));
                                    break;
                                case 5:
                                    scmCostTransferOccurSum.setQty_4(scmCostTransferOccurSum.getQty_4().add(scmInvMoveBillEntry.getQty()));
                                    scmCostTransferOccurSum.setAmt_4(scmCostTransferOccurSum.getAmt_4().add(scmInvMoveBillEntry.getAmt()));
                                    scmCostTransferOccurSum.setTaxAmt_4(scmCostTransferOccurSum.getTaxAmt_4().add(scmInvMoveBillEntry.getTaxAmt()));
                                    break;                                    
                                case 6:
                                    scmCostTransferOccurSum.setQty_5(scmCostTransferOccurSum.getQty_5().add(scmInvMoveBillEntry.getQty()));
                                    scmCostTransferOccurSum.setAmt_5(scmCostTransferOccurSum.getAmt_5().add(scmInvMoveBillEntry.getAmt()));
                                    scmCostTransferOccurSum.setTaxAmt_5(scmCostTransferOccurSum.getTaxAmt_5().add(scmInvMoveBillEntry.getTaxAmt()));
                                    break;                                    
                                case 7:
                                    scmCostTransferOccurSum.setQty_6(scmCostTransferOccurSum.getQty_6().add(scmInvMoveBillEntry.getQty()));
                                    scmCostTransferOccurSum.setAmt_6(scmCostTransferOccurSum.getAmt_6().add(scmInvMoveBillEntry.getAmt()));
                                    scmCostTransferOccurSum.setTaxAmt_6(scmCostTransferOccurSum.getTaxAmt_6().add(scmInvMoveBillEntry.getTaxAmt()));
                                    break;                                    
                                case 8:
                                    scmCostTransferOccurSum.setQty_7(scmCostTransferOccurSum.getQty_7().add(scmInvMoveBillEntry.getQty()));
                                    scmCostTransferOccurSum.setAmt_7(scmCostTransferOccurSum.getAmt_7().add(scmInvMoveBillEntry.getAmt()));
                                    scmCostTransferOccurSum.setTaxAmt_7(scmCostTransferOccurSum.getTaxAmt_7().add(scmInvMoveBillEntry.getTaxAmt()));
                                    break;                                    
                                case 9:
                                    scmCostTransferOccurSum.setQty_8(scmCostTransferOccurSum.getQty_8().add(scmInvMoveBillEntry.getQty()));
                                    scmCostTransferOccurSum.setAmt_8(scmCostTransferOccurSum.getAmt_8().add(scmInvMoveBillEntry.getAmt()));
                                    scmCostTransferOccurSum.setTaxAmt_8(scmCostTransferOccurSum.getTaxAmt_8().add(scmInvMoveBillEntry.getTaxAmt()));
                                    break;                                    
                                case 10:
                                    scmCostTransferOccurSum.setQty_9(scmCostTransferOccurSum.getQty_9().add(scmInvMoveBillEntry.getQty()));
                                    scmCostTransferOccurSum.setAmt_9(scmCostTransferOccurSum.getAmt_9().add(scmInvMoveBillEntry.getAmt()));
                                    scmCostTransferOccurSum.setTaxAmt_9(scmCostTransferOccurSum.getTaxAmt_9().add(scmInvMoveBillEntry.getTaxAmt()));
                                    break;                                    
                                default:
                                    break;
                                }
                                scmCostTransferOccurSumList.add(scmCostTransferOccurSum);
                            }  
                        }
                    }
                }
            }
        }
        //耗用单
        page = new Page();
        page.setModelClass(ScmInvCostConsumeEntry2.class);
        page.setShowCount(Integer.MAX_VALUE);
        if(StringUtils.isNotBlank(materialClassName)) {
            page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmMaterialGroup.class)+"."+ScmMaterialGroup.FN_CLASSCODE, 
                    new QueryParam(ClassUtils.getFinalModelSimpleName(ScmMaterialGroup.class)+"."+ScmMaterialGroup.FN_CLASSCODE, QueryParam.QUERY_EQ, materialClassName));
        }
        if(StringUtils.isNotBlank(materialName)) {
            page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvCostConsumeEntry2.class)+"."+ScmInvCostConsumeEntry2.FN_ITEMID, 
                    new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvCostConsumeEntry2.class)+"."+ScmInvCostConsumeEntry2.FN_ITEMID, QueryParam.QUERY_EQ, materialName));
        }
        page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvCostConsume.class)+"."+ScmInvCostConsume.FN_ORGUNITNO, 
                new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvCostConsume.class)+"."+ScmInvCostConsume.FN_ORGUNITNO, QueryParam.QUERY_IN, costOrgUnitNos.toString()));
        page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvCostConsume.class)+"."+ScmInvCostConsume.FN_BIZDATE, 
                new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvCostConsume.class)+"."+ScmInvCostConsume.FN_BIZDATE, QueryParam.QUERY_BETWEEN, beginDate,endDate));
        page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvCostConsume.class)+"."+ScmInvCostConsume.FN_STATUS, 
                new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvCostConsume.class)+"."+ScmInvCostConsume.FN_STATUS, QueryParam.QUERY_EQ, "E"));
        if (StringUtils.equals("Y", costConsume)) {
            List<ScmInvCostConsumeEntry2> scmInvCostConsumeEntryList = scmInvCostConsumeEntryBiz.queryPage(page, arglist, "findAllPage", param);
            if(scmInvCostConsumeEntryList!=null && !scmInvCostConsumeEntryList.isEmpty()) {
                intervalBizType.add(index, "耗用单");
                index = index + 1;
                for (ScmInvCostConsumeEntry2 scmInvCostConsumeEntry : scmInvCostConsumeEntryList) {
                    boolean exists=false;
                    if(!scmCostTransferOccurSumList.isEmpty()) {
                        for (ScmCostTransferOccurSum existScmCostTransferOccurSum:scmCostTransferOccurSumList) {
                            if (existScmCostTransferOccurSum.getItemId() == scmInvCostConsumeEntry.getItemId() && StringUtils.equals(
                                    existScmCostTransferOccurSum.getCostOrgUnitNo(), scmInvCostConsumeEntry.getCostOrgUnitNo())) {
                                switch (index) {
                                case 1:
                                    existScmCostTransferOccurSum.setQty(existScmCostTransferOccurSum.getQty().add(scmInvCostConsumeEntry.getQty()));
                                    existScmCostTransferOccurSum.setAmt(existScmCostTransferOccurSum.getAmt().add(scmInvCostConsumeEntry.getAmt()));
                                    existScmCostTransferOccurSum.setTaxAmt(existScmCostTransferOccurSum.getTaxAmt().add(scmInvCostConsumeEntry.getTaxAmt()));
                                    exists = true;
                                    break;
                                case 2:
                                    existScmCostTransferOccurSum.setQty_1(existScmCostTransferOccurSum.getQty_1().add(scmInvCostConsumeEntry.getQty()));
                                    existScmCostTransferOccurSum.setAmt_1(existScmCostTransferOccurSum.getAmt_1().add(scmInvCostConsumeEntry.getAmt()));
                                    existScmCostTransferOccurSum.setTaxAmt_1(existScmCostTransferOccurSum.getTaxAmt_1().add(scmInvCostConsumeEntry.getTaxAmt()));
                                    exists = true;
                                    break;
                                case 3:
                                    existScmCostTransferOccurSum.setQty_2(existScmCostTransferOccurSum.getQty_2().add(scmInvCostConsumeEntry.getQty()));
                                    existScmCostTransferOccurSum.setAmt_2(existScmCostTransferOccurSum.getAmt_2().add(scmInvCostConsumeEntry.getAmt()));
                                    existScmCostTransferOccurSum.setTaxAmt_2(existScmCostTransferOccurSum.getTaxAmt_2().add(scmInvCostConsumeEntry.getTaxAmt()));
                                    exists = true;
                                    break;                                    
                                case 4:
                                    existScmCostTransferOccurSum.setQty_3(existScmCostTransferOccurSum.getQty_3().add(scmInvCostConsumeEntry.getQty()));
                                    existScmCostTransferOccurSum.setAmt_3(existScmCostTransferOccurSum.getAmt_3().add(scmInvCostConsumeEntry.getAmt()));
                                    existScmCostTransferOccurSum.setTaxAmt_3(existScmCostTransferOccurSum.getTaxAmt_3().add(scmInvCostConsumeEntry.getTaxAmt()));
                                    exists = true;
                                    break;                                    
                                case 5:
                                    existScmCostTransferOccurSum.setQty_4(existScmCostTransferOccurSum.getQty_4().add(scmInvCostConsumeEntry.getQty()));
                                    existScmCostTransferOccurSum.setAmt_4(existScmCostTransferOccurSum.getAmt_4().add(scmInvCostConsumeEntry.getAmt()));
                                    existScmCostTransferOccurSum.setTaxAmt_4(existScmCostTransferOccurSum.getTaxAmt_4().add(scmInvCostConsumeEntry.getTaxAmt()));
                                    exists = true;
                                    break;
                                case 6:
                                    existScmCostTransferOccurSum.setQty_5(existScmCostTransferOccurSum.getQty_5().add(scmInvCostConsumeEntry.getQty()));
                                    existScmCostTransferOccurSum.setAmt_5(existScmCostTransferOccurSum.getAmt_5().add(scmInvCostConsumeEntry.getAmt()));
                                    existScmCostTransferOccurSum.setTaxAmt_5(existScmCostTransferOccurSum.getTaxAmt_5().add(scmInvCostConsumeEntry.getTaxAmt()));
                                    exists = true;
                                    break;                                    
                                case 7:
                                    existScmCostTransferOccurSum.setQty_6(existScmCostTransferOccurSum.getQty_6().add(scmInvCostConsumeEntry.getQty()));
                                    existScmCostTransferOccurSum.setAmt_6(existScmCostTransferOccurSum.getAmt_6().add(scmInvCostConsumeEntry.getAmt()));
                                    existScmCostTransferOccurSum.setTaxAmt_6(existScmCostTransferOccurSum.getTaxAmt_6().add(scmInvCostConsumeEntry.getTaxAmt()));
                                    exists = true;
                                    break;                                    
                                case 8:
                                    existScmCostTransferOccurSum.setQty_7(existScmCostTransferOccurSum.getQty_7().add(scmInvCostConsumeEntry.getQty()));
                                    existScmCostTransferOccurSum.setAmt_7(existScmCostTransferOccurSum.getAmt_7().add(scmInvCostConsumeEntry.getAmt()));
                                    existScmCostTransferOccurSum.setTaxAmt_7(existScmCostTransferOccurSum.getTaxAmt_7().add(scmInvCostConsumeEntry.getTaxAmt()));
                                    exists = true;
                                    break;                                    
                                case 9:
                                    existScmCostTransferOccurSum.setQty_8(existScmCostTransferOccurSum.getQty_8().add(scmInvCostConsumeEntry.getQty()));
                                    existScmCostTransferOccurSum.setAmt_8(existScmCostTransferOccurSum.getAmt_8().add(scmInvCostConsumeEntry.getAmt()));
                                    existScmCostTransferOccurSum.setTaxAmt_8(existScmCostTransferOccurSum.getTaxAmt_8().add(scmInvCostConsumeEntry.getTaxAmt()));
                                    exists = true;
                                    break;                                    
                                case 10:
                                    existScmCostTransferOccurSum.setQty_9(existScmCostTransferOccurSum.getQty_9().add(scmInvCostConsumeEntry.getQty()));
                                    existScmCostTransferOccurSum.setAmt_9(existScmCostTransferOccurSum.getAmt_9().add(scmInvCostConsumeEntry.getAmt()));
                                    existScmCostTransferOccurSum.setTaxAmt_9(existScmCostTransferOccurSum.getTaxAmt_9().add(scmInvCostConsumeEntry.getTaxAmt()));
                                    exists = true;
                                    break;                                    
                                default:
                                    break;
                                }
                            }
                        }
                    }
                    if(!exists) {
                        ScmCostTransferOccurSum scmCostTransferOccurSum = new ScmCostTransferOccurSum(true);
                        scmCostTransferOccurSum.setClassName(scmInvCostConsumeEntry.getGroupName());
                        scmCostTransferOccurSum.setItemId(scmInvCostConsumeEntry.getItemId());
                        scmCostTransferOccurSum.setItemNo(scmInvCostConsumeEntry.getItemNo());
                        scmCostTransferOccurSum.setItemName(scmInvCostConsumeEntry.getItemName());
                        scmCostTransferOccurSum.setSpec(scmInvCostConsumeEntry.getSpec());
                        scmCostTransferOccurSum.setUnit(scmInvCostConsumeEntry.getUnit());
                        scmCostTransferOccurSum.setUnitName(scmInvCostConsumeEntry.getUnitName());
                        scmCostTransferOccurSum.setCostOrgUnitNo(scmInvCostConsumeEntry.getCostOrgUnitNo());
                        switch (index) {
                        case 1:
                            scmCostTransferOccurSum.setQty(scmCostTransferOccurSum.getQty().add(scmInvCostConsumeEntry.getQty()));
                            scmCostTransferOccurSum.setAmt(scmCostTransferOccurSum.getAmt().add(scmInvCostConsumeEntry.getAmt()));
                            scmCostTransferOccurSum.setTaxAmt(scmCostTransferOccurSum.getTaxAmt().add(scmInvCostConsumeEntry.getTaxAmt()));
                            break;
                        case 2:
                            scmCostTransferOccurSum.setQty_1(scmCostTransferOccurSum.getQty_1().add(scmInvCostConsumeEntry.getQty()));
                            scmCostTransferOccurSum.setAmt_1(scmCostTransferOccurSum.getAmt_1().add(scmInvCostConsumeEntry.getAmt()));
                            scmCostTransferOccurSum.setTaxAmt_1(scmCostTransferOccurSum.getTaxAmt_1().add(scmInvCostConsumeEntry.getTaxAmt()));
                            break;                                    
                        case 3:
                            scmCostTransferOccurSum.setQty_2(scmCostTransferOccurSum.getQty_2().add(scmInvCostConsumeEntry.getQty()));
                            scmCostTransferOccurSum.setAmt_2(scmCostTransferOccurSum.getAmt_2().add(scmInvCostConsumeEntry.getAmt()));
                            scmCostTransferOccurSum.setTaxAmt_2(scmCostTransferOccurSum.getTaxAmt_2().add(scmInvCostConsumeEntry.getTaxAmt()));
                            break;                                    
                        case 4:
                            scmCostTransferOccurSum.setQty_3(scmCostTransferOccurSum.getQty_3().add(scmInvCostConsumeEntry.getQty()));
                            scmCostTransferOccurSum.setAmt_3(scmCostTransferOccurSum.getAmt_3().add(scmInvCostConsumeEntry.getAmt()));
                            scmCostTransferOccurSum.setTaxAmt_3(scmCostTransferOccurSum.getTaxAmt_3().add(scmInvCostConsumeEntry.getTaxAmt()));
                            break;
                        case 5:
                            scmCostTransferOccurSum.setQty_4(scmCostTransferOccurSum.getQty_4().add(scmInvCostConsumeEntry.getQty()));
                            scmCostTransferOccurSum.setAmt_4(scmCostTransferOccurSum.getAmt_4().add(scmInvCostConsumeEntry.getAmt()));
                            scmCostTransferOccurSum.setTaxAmt_4(scmCostTransferOccurSum.getTaxAmt_4().add(scmInvCostConsumeEntry.getTaxAmt()));
                            break;                                    
                        case 6:
                            scmCostTransferOccurSum.setQty_5(scmCostTransferOccurSum.getQty_5().add(scmInvCostConsumeEntry.getQty()));
                            scmCostTransferOccurSum.setAmt_5(scmCostTransferOccurSum.getAmt_5().add(scmInvCostConsumeEntry.getAmt()));
                            scmCostTransferOccurSum.setTaxAmt_5(scmCostTransferOccurSum.getTaxAmt_5().add(scmInvCostConsumeEntry.getTaxAmt()));
                            break;                                    
                        case 7:
                            scmCostTransferOccurSum.setQty_6(scmCostTransferOccurSum.getQty_6().add(scmInvCostConsumeEntry.getQty()));
                            scmCostTransferOccurSum.setAmt_6(scmCostTransferOccurSum.getAmt_6().add(scmInvCostConsumeEntry.getAmt()));
                            scmCostTransferOccurSum.setTaxAmt_6(scmCostTransferOccurSum.getTaxAmt_6().add(scmInvCostConsumeEntry.getTaxAmt()));
                            break;                                    
                        case 8:
                            scmCostTransferOccurSum.setQty_7(scmCostTransferOccurSum.getQty_7().add(scmInvCostConsumeEntry.getQty()));
                            scmCostTransferOccurSum.setAmt_7(scmCostTransferOccurSum.getAmt_7().add(scmInvCostConsumeEntry.getAmt()));
                            scmCostTransferOccurSum.setTaxAmt_7(scmCostTransferOccurSum.getTaxAmt_7().add(scmInvCostConsumeEntry.getTaxAmt()));
                            break;                                    
                        case 9:
                            scmCostTransferOccurSum.setQty_8(scmCostTransferOccurSum.getQty_8().add(scmInvCostConsumeEntry.getQty()));
                            scmCostTransferOccurSum.setAmt_8(scmCostTransferOccurSum.getAmt_8().add(scmInvCostConsumeEntry.getAmt()));
                            scmCostTransferOccurSum.setTaxAmt_8(scmCostTransferOccurSum.getTaxAmt_8().add(scmInvCostConsumeEntry.getTaxAmt()));
                            break;                                    
                        case 10:
                            scmCostTransferOccurSum.setQty_9(scmCostTransferOccurSum.getQty_9().add(scmInvCostConsumeEntry.getQty()));
                            scmCostTransferOccurSum.setAmt_9(scmCostTransferOccurSum.getAmt_9().add(scmInvCostConsumeEntry.getAmt()));
                            scmCostTransferOccurSum.setTaxAmt_9(scmCostTransferOccurSum.getTaxAmt_9().add(scmInvCostConsumeEntry.getTaxAmt()));
                            break;                                    
                        default:
                            break;
                        }
                        scmCostTransferOccurSumList.add(scmCostTransferOccurSum);
                    }                    
                }
            }
        }
        //盘存
        page = new Page();
        page.setModelClass(ScmInvCountingCostCenterEntry2.class);
        page.setShowCount(Integer.MAX_VALUE);
        if(StringUtils.isNotBlank(materialClassName)) {
            page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmMaterialGroup.class)+"."+ScmMaterialGroup.FN_CLASSCODE, 
                    new QueryParam(ClassUtils.getFinalModelSimpleName(ScmMaterialGroup.class)+"."+ScmMaterialGroup.FN_CLASSCODE, QueryParam.QUERY_EQ, materialClassName));
        }
        if(StringUtils.isNotBlank(materialName)) {
            page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvCountingCostCenterEntry2.class)+"."+ScmInvCountingCostCenterEntry2.FN_ITEMID, 
                    new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvCountingCostCenterEntry2.class)+"."+ScmInvCountingCostCenterEntry2.FN_ITEMID, QueryParam.QUERY_EQ, materialName));
        }
        page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvCountingCostCenter.class)+"."+ScmInvCountingCostCenter.FN_ORGUNITNO, 
                new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvCountingCostCenter.class)+"."+ScmInvCountingCostCenter.FN_ORGUNITNO, QueryParam.QUERY_IN, costOrgUnitNos.toString()));
        page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvCountingTask.class)+"."+ScmInvCountingTask.FN_BIZDATE, 
                new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvCountingTask.class)+"."+ScmInvCountingTask.FN_BIZDATE, QueryParam.QUERY_BETWEEN, beginDate,endDate));
        page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvCountingCostCenterEntry2.class)+"."+ScmInvCountingCostCenterEntry2.FN_DIFFERQTY, 
                new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvCountingCostCenterEntry2.class)+"."+ScmInvCountingCostCenterEntry2.FN_DIFFERQTY, QueryParam.QUERY_NE, "0"));
        page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvCountingTask.class)+"."+ScmInvCountingTask.FN_STATUS, 
                new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvCountingTask.class)+"."+ScmInvCountingTask.FN_STATUS, QueryParam.QUERY_EQ, "O"));
        if (StringUtils.equals("Y", counting)) {
            List<ScmInvCountingCostCenterEntry2> scmInvCountingCostCenterEntryList = scmInvCountingCostCenterEntryBiz.queryPage(page, arglist, "findAllPage", param);
            if(scmInvCountingCostCenterEntryList!=null && !scmInvCountingCostCenterEntryList.isEmpty()) {
                intervalBizType.add(index, "盘存(耗用)");
                index = index + 1;
                for (ScmInvCountingCostCenterEntry2 scmInvCountingCostCenterEntry : scmInvCountingCostCenterEntryList) {
                    boolean exists=false;
                    if(!scmCostTransferOccurSumList.isEmpty()) {
                        for (ScmCostTransferOccurSum existScmCostTransferOccurSum:scmCostTransferOccurSumList) {
                            if (existScmCostTransferOccurSum.getItemId() == scmInvCountingCostCenterEntry.getItemId() && StringUtils.equals(
                                    existScmCostTransferOccurSum.getCostOrgUnitNo(), scmInvCountingCostCenterEntry.getOrgUnitNo())) {
                                switch (index) {
                                case 1:
                                    existScmCostTransferOccurSum.setQty(existScmCostTransferOccurSum.getQty().add((new BigDecimal("-1")).multiply(scmInvCountingCostCenterEntry.getDifferQty())));
                                    existScmCostTransferOccurSum.setAmt(existScmCostTransferOccurSum.getAmt().add((new BigDecimal("-1")).multiply(scmInvCountingCostCenterEntry.getDifferAmt())));
                                    existScmCostTransferOccurSum.setTaxAmt(existScmCostTransferOccurSum.getTaxAmt().add((new BigDecimal("-1")).multiply(scmInvCountingCostCenterEntry.getDifferTaxAmt())));
                                    exists = true;
                                    break;
                                case 2:
                                    existScmCostTransferOccurSum.setQty_1(existScmCostTransferOccurSum.getQty_1().add((new BigDecimal("-1")).multiply(scmInvCountingCostCenterEntry.getDifferQty())));
                                    existScmCostTransferOccurSum.setAmt_1(existScmCostTransferOccurSum.getAmt_1().add((new BigDecimal("-1")).multiply(scmInvCountingCostCenterEntry.getDifferAmt())));
                                    existScmCostTransferOccurSum.setTaxAmt_1(existScmCostTransferOccurSum.getTaxAmt_1().add((new BigDecimal("-1")).multiply(scmInvCountingCostCenterEntry.getDifferTaxAmt())));
                                    exists = true;
                                    break;
                                case 3:
                                    existScmCostTransferOccurSum.setQty_2(existScmCostTransferOccurSum.getQty_2().add((new BigDecimal("-1")).multiply(scmInvCountingCostCenterEntry.getDifferQty())));
                                    existScmCostTransferOccurSum.setAmt_2(existScmCostTransferOccurSum.getAmt_2().add((new BigDecimal("-1")).multiply(scmInvCountingCostCenterEntry.getDifferAmt())));
                                    existScmCostTransferOccurSum.setTaxAmt_2(existScmCostTransferOccurSum.getTaxAmt_2().add((new BigDecimal("-1")).multiply(scmInvCountingCostCenterEntry.getDifferTaxAmt())));
                                    exists = true;
                                    break;                                    
                                case 4:
                                    existScmCostTransferOccurSum.setQty_3(existScmCostTransferOccurSum.getQty_3().add((new BigDecimal("-1")).multiply(scmInvCountingCostCenterEntry.getDifferQty())));
                                    existScmCostTransferOccurSum.setAmt_3(existScmCostTransferOccurSum.getAmt_3().add((new BigDecimal("-1")).multiply(scmInvCountingCostCenterEntry.getDifferAmt())));
                                    existScmCostTransferOccurSum.setTaxAmt_3(existScmCostTransferOccurSum.getTaxAmt_3().add((new BigDecimal("-1")).multiply(scmInvCountingCostCenterEntry.getDifferTaxAmt())));
                                    exists = true;
                                    break;                                    
                                case 5:
                                    existScmCostTransferOccurSum.setQty_4(existScmCostTransferOccurSum.getQty_4().add((new BigDecimal("-1")).multiply(scmInvCountingCostCenterEntry.getDifferQty())));
                                    existScmCostTransferOccurSum.setAmt_4(existScmCostTransferOccurSum.getAmt_4().add((new BigDecimal("-1")).multiply(scmInvCountingCostCenterEntry.getDifferAmt())));
                                    existScmCostTransferOccurSum.setTaxAmt_4(existScmCostTransferOccurSum.getTaxAmt_4().add((new BigDecimal("-1")).multiply(scmInvCountingCostCenterEntry.getDifferTaxAmt())));
                                    exists = true;
                                    break;
                                case 6:
                                    existScmCostTransferOccurSum.setQty_5(existScmCostTransferOccurSum.getQty_5().add((new BigDecimal("-1")).multiply(scmInvCountingCostCenterEntry.getDifferQty())));
                                    existScmCostTransferOccurSum.setAmt_5(existScmCostTransferOccurSum.getAmt_5().add((new BigDecimal("-1")).multiply(scmInvCountingCostCenterEntry.getDifferAmt())));
                                    existScmCostTransferOccurSum.setTaxAmt_5(existScmCostTransferOccurSum.getTaxAmt_5().add((new BigDecimal("-1")).multiply(scmInvCountingCostCenterEntry.getDifferTaxAmt())));
                                    exists = true;
                                    break;                                    
                                case 7:
                                    existScmCostTransferOccurSum.setQty_6(existScmCostTransferOccurSum.getQty_6().add((new BigDecimal("-1")).multiply(scmInvCountingCostCenterEntry.getDifferQty())));
                                    existScmCostTransferOccurSum.setAmt_6(existScmCostTransferOccurSum.getAmt_6().add((new BigDecimal("-1")).multiply(scmInvCountingCostCenterEntry.getDifferAmt())));
                                    existScmCostTransferOccurSum.setTaxAmt_6(existScmCostTransferOccurSum.getTaxAmt_6().add((new BigDecimal("-1")).multiply(scmInvCountingCostCenterEntry.getDifferTaxAmt())));
                                    exists = true;
                                    break;                                    
                                case 8:
                                    existScmCostTransferOccurSum.setQty_7(existScmCostTransferOccurSum.getQty_7().add((new BigDecimal("-1")).multiply(scmInvCountingCostCenterEntry.getDifferQty())));
                                    existScmCostTransferOccurSum.setAmt_7(existScmCostTransferOccurSum.getAmt_7().add((new BigDecimal("-1")).multiply(scmInvCountingCostCenterEntry.getDifferAmt())));
                                    existScmCostTransferOccurSum.setTaxAmt_7(existScmCostTransferOccurSum.getTaxAmt_7().add((new BigDecimal("-1")).multiply(scmInvCountingCostCenterEntry.getDifferTaxAmt())));
                                    exists = true;
                                    break;                                    
                                case 9:
                                    existScmCostTransferOccurSum.setQty_8(existScmCostTransferOccurSum.getQty_8().add((new BigDecimal("-1")).multiply(scmInvCountingCostCenterEntry.getDifferQty())));
                                    existScmCostTransferOccurSum.setAmt_8(existScmCostTransferOccurSum.getAmt_8().add((new BigDecimal("-1")).multiply(scmInvCountingCostCenterEntry.getDifferAmt())));
                                    existScmCostTransferOccurSum.setTaxAmt_8(existScmCostTransferOccurSum.getTaxAmt_8().add((new BigDecimal("-1")).multiply(scmInvCountingCostCenterEntry.getDifferTaxAmt())));
                                    exists = true;
                                    break;                                    
                                case 10:
                                    existScmCostTransferOccurSum.setQty_9(existScmCostTransferOccurSum.getQty_9().add((new BigDecimal("-1")).multiply(scmInvCountingCostCenterEntry.getDifferQty())));
                                    existScmCostTransferOccurSum.setAmt_9(existScmCostTransferOccurSum.getAmt_9().add((new BigDecimal("-1")).multiply(scmInvCountingCostCenterEntry.getDifferAmt())));
                                    existScmCostTransferOccurSum.setTaxAmt_9(existScmCostTransferOccurSum.getTaxAmt_9().add((new BigDecimal("-1")).multiply(scmInvCountingCostCenterEntry.getDifferTaxAmt())));
                                    exists = true;
                                    break;                                    
                                default:
                                    break;
                                }
                            }
                        }
                    }
                    if(!exists) {
                        ScmCostTransferOccurSum scmCostTransferOccurSum = new ScmCostTransferOccurSum(true);
                        scmCostTransferOccurSum.setClassName(scmInvCountingCostCenterEntry.getGroupName());
                        scmCostTransferOccurSum.setItemId(scmInvCountingCostCenterEntry.getItemId());
                        scmCostTransferOccurSum.setItemNo(scmInvCountingCostCenterEntry.getItemNo());
                        scmCostTransferOccurSum.setItemName(scmInvCountingCostCenterEntry.getItemName());
                        scmCostTransferOccurSum.setSpec(scmInvCountingCostCenterEntry.getSpec());
                        scmCostTransferOccurSum.setUnit(scmInvCountingCostCenterEntry.getUnit());
                        scmCostTransferOccurSum.setUnitName(scmInvCountingCostCenterEntry.getUnitName());
                        scmCostTransferOccurSum.setCostOrgUnitNo(scmInvCountingCostCenterEntry.getOrgUnitNo());
                        switch (index) {
                        case 1:
                            scmCostTransferOccurSum.setQty(scmCostTransferOccurSum.getQty().add((new BigDecimal("-1")).multiply(scmInvCountingCostCenterEntry.getDifferQty())));
                            scmCostTransferOccurSum.setAmt(scmCostTransferOccurSum.getAmt().add((new BigDecimal("-1")).multiply(scmInvCountingCostCenterEntry.getDifferAmt())));
                            scmCostTransferOccurSum.setTaxAmt(scmCostTransferOccurSum.getTaxAmt().add((new BigDecimal("-1")).multiply(scmInvCountingCostCenterEntry.getDifferTaxAmt())));
                            break;
                        case 2:
                            scmCostTransferOccurSum.setQty_1(scmCostTransferOccurSum.getQty_1().add((new BigDecimal("-1")).multiply(scmInvCountingCostCenterEntry.getDifferQty())));
                            scmCostTransferOccurSum.setAmt_1(scmCostTransferOccurSum.getAmt_1().add((new BigDecimal("-1")).multiply(scmInvCountingCostCenterEntry.getDifferAmt())));
                            scmCostTransferOccurSum.setTaxAmt_1(scmCostTransferOccurSum.getTaxAmt_1().add((new BigDecimal("-1")).multiply(scmInvCountingCostCenterEntry.getDifferTaxAmt())));
                            break;                                    
                        case 3:
                            scmCostTransferOccurSum.setQty_2(scmCostTransferOccurSum.getQty_2().add((new BigDecimal("-1")).multiply(scmInvCountingCostCenterEntry.getDifferQty())));
                            scmCostTransferOccurSum.setAmt_2(scmCostTransferOccurSum.getAmt_2().add((new BigDecimal("-1")).multiply(scmInvCountingCostCenterEntry.getDifferAmt())));
                            scmCostTransferOccurSum.setTaxAmt_2(scmCostTransferOccurSum.getTaxAmt_2().add((new BigDecimal("-1")).multiply(scmInvCountingCostCenterEntry.getDifferTaxAmt())));
                            break;                                    
                        case 4:
                            scmCostTransferOccurSum.setQty_3(scmCostTransferOccurSum.getQty_3().add((new BigDecimal("-1")).multiply(scmInvCountingCostCenterEntry.getDifferQty())));
                            scmCostTransferOccurSum.setAmt_3(scmCostTransferOccurSum.getAmt_3().add((new BigDecimal("-1")).multiply(scmInvCountingCostCenterEntry.getDifferAmt())));
                            scmCostTransferOccurSum.setTaxAmt_3(scmCostTransferOccurSum.getTaxAmt_3().add((new BigDecimal("-1")).multiply(scmInvCountingCostCenterEntry.getDifferTaxAmt())));
                            break;
                        case 5:
                            scmCostTransferOccurSum.setQty_4(scmCostTransferOccurSum.getQty_4().add((new BigDecimal("-1")).multiply(scmInvCountingCostCenterEntry.getDifferQty())));
                            scmCostTransferOccurSum.setAmt_4(scmCostTransferOccurSum.getAmt_4().add((new BigDecimal("-1")).multiply(scmInvCountingCostCenterEntry.getDifferAmt())));
                            scmCostTransferOccurSum.setTaxAmt_4(scmCostTransferOccurSum.getTaxAmt_4().add((new BigDecimal("-1")).multiply(scmInvCountingCostCenterEntry.getDifferTaxAmt())));
                            break;                                    
                        case 6:
                            scmCostTransferOccurSum.setQty_5(scmCostTransferOccurSum.getQty_5().add((new BigDecimal("-1")).multiply(scmInvCountingCostCenterEntry.getDifferQty())));
                            scmCostTransferOccurSum.setAmt_5(scmCostTransferOccurSum.getAmt_5().add((new BigDecimal("-1")).multiply(scmInvCountingCostCenterEntry.getDifferAmt())));
                            scmCostTransferOccurSum.setTaxAmt_5(scmCostTransferOccurSum.getTaxAmt_5().add((new BigDecimal("-1")).multiply(scmInvCountingCostCenterEntry.getDifferTaxAmt())));
                            break;                                    
                        case 7:
                            scmCostTransferOccurSum.setQty_6(scmCostTransferOccurSum.getQty_6().add((new BigDecimal("-1")).multiply(scmInvCountingCostCenterEntry.getDifferQty())));
                            scmCostTransferOccurSum.setAmt_6(scmCostTransferOccurSum.getAmt_6().add((new BigDecimal("-1")).multiply(scmInvCountingCostCenterEntry.getDifferAmt())));
                            scmCostTransferOccurSum.setTaxAmt_6(scmCostTransferOccurSum.getTaxAmt_6().add((new BigDecimal("-1")).multiply(scmInvCountingCostCenterEntry.getDifferTaxAmt())));
                            break;                                    
                        case 8:
                            scmCostTransferOccurSum.setQty_7(scmCostTransferOccurSum.getQty_7().add((new BigDecimal("-1")).multiply(scmInvCountingCostCenterEntry.getDifferQty())));
                            scmCostTransferOccurSum.setAmt_7(scmCostTransferOccurSum.getAmt_7().add((new BigDecimal("-1")).multiply(scmInvCountingCostCenterEntry.getDifferAmt())));
                            scmCostTransferOccurSum.setTaxAmt_7(scmCostTransferOccurSum.getTaxAmt_7().add((new BigDecimal("-1")).multiply(scmInvCountingCostCenterEntry.getDifferTaxAmt())));
                            break;                                    
                        case 9:
                            scmCostTransferOccurSum.setQty_8(scmCostTransferOccurSum.getQty_8().add((new BigDecimal("-1")).multiply(scmInvCountingCostCenterEntry.getDifferQty())));
                            scmCostTransferOccurSum.setAmt_8(scmCostTransferOccurSum.getAmt_8().add((new BigDecimal("-1")).multiply(scmInvCountingCostCenterEntry.getDifferAmt())));
                            scmCostTransferOccurSum.setTaxAmt_8(scmCostTransferOccurSum.getTaxAmt_8().add((new BigDecimal("-1")).multiply(scmInvCountingCostCenterEntry.getDifferTaxAmt())));
                            break;                                    
                        case 10:
                            scmCostTransferOccurSum.setQty_9(scmCostTransferOccurSum.getQty_9().add((new BigDecimal("-1")).multiply(scmInvCountingCostCenterEntry.getDifferQty())));
                            scmCostTransferOccurSum.setAmt_9(scmCostTransferOccurSum.getAmt_9().add((new BigDecimal("-1")).multiply(scmInvCountingCostCenterEntry.getDifferAmt())));
                            scmCostTransferOccurSum.setTaxAmt_9(scmCostTransferOccurSum.getTaxAmt_9().add((new BigDecimal("-1")).multiply(scmInvCountingCostCenterEntry.getDifferTaxAmt())));
                            break;                                    
                        default:
                            break;
                        }
                        scmCostTransferOccurSumList.add(scmCostTransferOccurSum);
                    }                    
                }
            }
        }
        
        if (scmCostTransferOccurSumList != null && scmCostTransferOccurSumList.size() > 0) {
        	HashMap<String, Object> groupMap = new HashMap<String, Object>();
            for (ScmCostTransferOccurSum scmCostTransferOccurSum : scmCostTransferOccurSumList) {
                OrgBaseUnit2 orgBaseUnit = orgUnitBiz.selectbyOrgNo(scmCostTransferOccurSum.getCostOrgUnitNo(), param);
                if (orgBaseUnit != null) {
                    scmCostTransferOccurSum.setCostOrgUnitName(orgBaseUnit.getOrgUnitName());
                }
                
                if(scmCostTransferOccurSum.getItemId()>0) {
                    ScmMaterial2 scmMaterial = scmMaterialBiz.selectByItemId(scmCostTransferOccurSum.getItemId(), param);
                    if(scmMaterial!=null) {
                        scmCostTransferOccurSum.setItemNo(scmMaterial.getItemNo());
                        scmCostTransferOccurSum.setItemName(scmMaterial.getItemName());
                        if(scmMaterial.getGroupId()>0) {
                            ScmMaterialGroup scmMaterialGroup = scmMaterialGroupBiz.selectDirect(scmMaterial.getGroupId(), param);
                            if(scmMaterialGroup!=null) {
                                scmCostTransferOccurSum.setClassCode(scmMaterialGroup.getClassCode());
//                                scmCostTransferOccurSum.setClassName(scmMaterialGroup.getClassName());
                                if(StringUtils.isNotBlank(scmMaterialGroup.getLongNo())) {
                                	if(!groupMap.containsKey(scmMaterialGroup.getLongNo())) {
	                                    String[] ids = StringUtils.split(scmMaterialGroup.getLongNo(),",");
	                                    if(Long.valueOf(ids[0])!=scmMaterial.getGroupId()) {
	                                        scmMaterialGroup = scmMaterialGroupBiz.selectDirect(Long.valueOf(ids[0]), param);
	                                        if(scmMaterialGroup!=null)
	                                        	groupMap.put(scmMaterialGroup.getLongNo(), scmMaterialGroup);
	                                    }
                                	}
                                	scmMaterialGroup = (ScmMaterialGroup) groupMap.get(scmMaterialGroup.getLongNo());
                                	if(scmMaterialGroup!=null)
                                		scmCostTransferOccurSum.setClassName(scmMaterialGroup.getClassName());
                                }
                            }
                        }
                    }
                }
                scmCostTransferOccurSum.setTax(scmCostTransferOccurSum.getTaxAmt().subtract(scmCostTransferOccurSum.getAmt()));
                scmCostTransferOccurSum.setTax_1(scmCostTransferOccurSum.getTaxAmt_1().subtract(scmCostTransferOccurSum.getAmt_1()));
                scmCostTransferOccurSum.setTax_2(scmCostTransferOccurSum.getTaxAmt_2().subtract(scmCostTransferOccurSum.getAmt_2()));
                scmCostTransferOccurSum.setTax_3(scmCostTransferOccurSum.getTaxAmt_3().subtract(scmCostTransferOccurSum.getAmt_3()));
                scmCostTransferOccurSum.setTax_4(scmCostTransferOccurSum.getTaxAmt_4().subtract(scmCostTransferOccurSum.getAmt_4()));
                scmCostTransferOccurSum.setTax_5(scmCostTransferOccurSum.getTaxAmt_5().subtract(scmCostTransferOccurSum.getAmt_5()));
                scmCostTransferOccurSum.setTax_6(scmCostTransferOccurSum.getTaxAmt_6().subtract(scmCostTransferOccurSum.getAmt_6()));
                scmCostTransferOccurSum.setTax_7(scmCostTransferOccurSum.getTaxAmt_7().subtract(scmCostTransferOccurSum.getAmt_7()));
                scmCostTransferOccurSum.setTax_8(scmCostTransferOccurSum.getTaxAmt_8().subtract(scmCostTransferOccurSum.getAmt_8()));
            }
        }
        //数据排序
        String fields[]={"className","costOrgUnitNo","itemNo"};
        String sorts[]={"ASC","ASC","ASC"};
        
        scmCostTransferOccurSumList = (List<ScmCostTransferOccurSum>)ListSortUtil.sort(scmCostTransferOccurSumList, fields, sorts);
        if (scmCostTransferOccurSumList != null && scmCostTransferOccurSumList.size() > 0) {
            scmCostTransferOccurSumList.get(0).setIntervaltype(intervalBizType);
        }
        
        return scmCostTransferOccurSumList;
    }

	@Override
	public List<ScmDeptConsume> selectDeptConsume(HttpServletRequest request) throws AppException {
		String materialClassName=request.getParameter("materialClassName2");
		if (StringUtils.isBlank(materialClassName)){
			return null;
		}
		String currentOrgUnitNo=request.getParameter("orgUnitNo");
		String currentControlUnitNo=request.getParameter("controlUnitNo");
		String queryType=request.getParameter("queryType");
		String periodId=request.getParameter("periodId");
		String accountDate = "";
		List<ScmDeptConsume> list = new ArrayList<>();
		HashMap<String, Object> map = new HashMap<>();
		StringBuffer materialClassIds=new StringBuffer("");
		String[] classIdList = StringUtils.split(materialClassName, ",");
        for(String classId:classIdList) {
            if(StringUtils.isBlank(classId)) continue;
            if(StringUtils.isNotBlank(materialClassIds.toString()))
            	materialClassIds.append(",");
            materialClassIds.append(classId);
        }
        String orgUnitNo=request.getParameter("costOrgUnitNo");
        StringBuffer orgUnitNos=new StringBuffer("");
        Param param = new Param();
		param.setOrgUnitNo(currentOrgUnitNo);
		param.setControlUnitNo(currentControlUnitNo);
		if (StringUtils.isNotBlank(periodId) && StringUtils.isNumeric(periodId)) {
			PeriodCalendar periodCalendar = periodCalendarBiz.select(Long.parseLong(periodId), param);
			if(periodCalendar != null){
				accountDate = FormatUtils.fmtDate(periodCalendar.getStartDate());
			}
		}
		if(StringUtils.isBlank(accountDate)) {
			return null;
		}
        if(StringUtils.isNotBlank(orgUnitNo)) {
            String[] orgUnitNoList = StringUtils.split(orgUnitNo, ",");
            for(String org:orgUnitNoList) {
                if(StringUtils.isBlank(org)) continue;
                List<OrgCostCenter2> orgCostCenterList = orgCostCenterBiz.findChild(org, param);
        		if(orgCostCenterList!=null && !orgCostCenterList.isEmpty()) {
        			for(OrgCostCenter2 orgCostCenter:orgCostCenterList) {
        				if(StringUtils.isNotBlank(orgUnitNos.toString()))
        					orgUnitNos.append(",");
        				orgUnitNos.append("'").append(orgCostCenter.getOrgUnitNo()).append("'");
        			}
        		}
            }
        }else{
        	List<OrgCostCenter2> orgCostCenterList = orgCostCenterBiz.findChild(currentOrgUnitNo, param);
    		if(orgCostCenterList!=null && !orgCostCenterList.isEmpty()) {
    			for(OrgCostCenter2 orgCostCenter:orgCostCenterList) {
    				if(StringUtils.isNotBlank(orgUnitNos.toString()))
    					orgUnitNos.append(",");
    				orgUnitNos.append("'").append(orgCostCenter.getOrgUnitNo()).append("'");
    			}
    		}else {
    			orgUnitNos.append("'").append(currentOrgUnitNo).append("'");
    		}
        }
        map.put("costOrgUnitNos", orgUnitNos.toString());
        map.put("materialClassIds", materialClassIds.toString());
		map.put("accountDate", accountDate);
		map.put("queryType", StringUtils.isBlank(queryType)?"1":queryType);
		list = ((ScmCostReportDAO) dao).selectDeptConsume(map);
		if (list != null && list.size() > 0) {
			for (int i = list.size() - 1; i >= 0; i--) {
				ScmDeptConsume scmDeptConsume = list.get(i);
				//过滤金额为0的数据
				BigDecimal amt = BigDecimal.ZERO;
				if(scmDeptConsume.getStartAmt() != null){
					amt = amt.add(scmDeptConsume.getStartAmt().abs());
				}
				if(scmDeptConsume.getPurinAmt()!= null){
					amt = amt.add(scmDeptConsume.getPurinAmt().abs());
				}
				if(scmDeptConsume.getReqAmt()!= null){
					amt = amt.add(scmDeptConsume.getReqAmt().abs());
				}
				if(scmDeptConsume.getCstinAmt()!= null){
					amt = amt.add(scmDeptConsume.getCstinAmt().abs());
				}
				if(scmDeptConsume.getCstoutAmt()!= null){
					amt = amt.add(scmDeptConsume.getCstoutAmt().abs());
				}
				if(scmDeptConsume.getEndAmt()!= null){
					amt = amt.add(scmDeptConsume.getEndAmt().abs());
				}
				if(amt.compareTo(BigDecimal.ZERO) == 0){
					list.remove(scmDeptConsume);
				}
				OrgBaseUnit2 orgBaseUnit = orgUnitBiz.selectbyOrgNo(scmDeptConsume.getCostOrgUnitNo(), param);
				if (orgBaseUnit != null) {
					scmDeptConsume.setOrgUnitName(orgBaseUnit.getOrgUnitName());
				}
			}
		}
		return list;
	}


	@Override
	public List<ScmNewCostTransferOccurSum> selectNewScmCostTransferOccurSum(HttpServletRequest request) throws AppException {
		long begin = System.currentTimeMillis();
	      List<ScmNewCostTransferOccurSum> scmCostTransferOccurSumList = new ArrayList<ScmNewCostTransferOccurSum>();
	        String currentOrgUnitNo=request.getParameter("orgUnitNo");
	        String currentControlUnitNo=request.getParameter("controlUnitNo");
	        String costOrgUnitNo=request.getParameter("costOrgUnitNo");
	        String beginDate=request.getParameter("beginDate");
	        String endDate=request.getParameter("endDate");
	        String materialClassName=request.getParameter("materialClassName");
	        String materialName=request.getParameter("materialName");
	        String purInwarehs="1".equals(request.getParameter("purInwarehs"))?"Y":"N";
	        String purInwarehsReturn="1".equals(request.getParameter("purInwarehsReturn"))?"Y":"N";
	        String materialReq="1".equals(request.getParameter("materialReq"))?"Y":"N";
	        String materialReqReturn="1".equals(request.getParameter("materialReqReturn"))?"Y":"N";
	        String moveIn="1".equals(request.getParameter("moveIn"))?"Y":"N";
	        String moveOut="1".equals(request.getParameter("moveOut"))?"Y":"N";
	        String costConsume="1".equals(request.getParameter("costConsume"))?"Y":"N";
	        String costConsume2="1".equals(request.getParameter("costConsume2"))?"Y":"N";
	        String summary1 = request.getParameter("summary1");
	        String summary2 = request.getParameter("summary2");
	        String counting="1".equals(request.getParameter("counting"))?"Y":"N";
	        String frmLoss="1".equals(request.getParameter("frmLoss"))?"Y":"N";
	        String InvSaleOut="1".equals(request.getParameter("InvSaleOut"))?"Y":"N";
	        String InvSaleIn="1".equals(request.getParameter("InvSaleIn"))?"Y":"N";
	        String status = "1".equals(request.getParameter("status")) ? "Y" : "N";
	        if(StringUtils.isBlank(beginDate) || StringUtils.isBlank(endDate))
	            return scmCostTransferOccurSumList;
	        if(StringUtils.isBlank(costOrgUnitNo))
	            costOrgUnitNo = currentOrgUnitNo;
	        Param param = new Param();
	        param.setOrgUnitNo(currentOrgUnitNo);
	        param.setControlUnitNo(currentControlUnitNo);
	        StringBuffer costOrgUnitNos = new StringBuffer("");
	        HashMap<String,String> map=new HashMap<>();
	        HashMap<String,OrgCostCenter2> costMap=new HashMap<>();
			if (StringUtils.isNotBlank(costOrgUnitNo)) {
				String[] orgUnitNoList = StringUtils.split(costOrgUnitNo, ",");
				for (String org : orgUnitNoList) {
					if (StringUtils.isBlank(org))
						continue;
					List<OrgCostCenter2> orgCostCenterList = orgCostCenterBiz.findChild(org, param);
					if (orgCostCenterList != null && !orgCostCenterList.isEmpty()) {
						for (OrgCostCenter2 orgCostCenter : orgCostCenterList) {
							map.put(orgCostCenter.getOrgUnitNo(), orgCostCenter.getOrgUnitName());
							costMap.put(orgCostCenter.getOrgUnitNo(), orgCostCenter);
							if (StringUtils.isNotBlank(costOrgUnitNos.toString()))
								costOrgUnitNos.append(",");
							costOrgUnitNos.append("'").append(orgCostCenter.getOrgUnitNo()).append("'");
						}
					}
				}
			}
	        StringBuffer sbMaterilaClass = new StringBuffer("");
	        if (StringUtils.isNotBlank(materialClassName)) {
				String[] materialClassNameList = StringUtils.split(materialClassName, ",");
	            for(String materialClass:materialClassNameList) {
	                if(StringUtils.isBlank(materialClass)) continue;
					int materialClassId = Integer.parseInt(materialClass);
					List<ScmMaterialGroup> scmMaterialGroupList = scmMaterialGroupBiz.findChild(materialClassId, param);
					if (scmMaterialGroupList != null && scmMaterialGroupList.size() > 0) {
						for(ScmMaterialGroup scmMaterialGroup:scmMaterialGroupList) {
							if(StringUtils.isNotBlank(sbMaterilaClass.toString()))
								sbMaterilaClass.append(",");
							sbMaterilaClass.append(String.valueOf(scmMaterialGroup.getId()));
						}
					}
				}
	        }
	        StringBuffer sbMaterila = new StringBuffer("");
	        if (StringUtils.isNotBlank(materialName)) {
	        	String[] materialNameList = StringUtils.split(materialName, ",");
	            for(String material:materialNameList) {
	                if(StringUtils.isBlank(material)) continue;
					int materialId = Integer.parseInt(material);
					if(StringUtils.isNotBlank(sbMaterila.toString()))
						sbMaterila.append(",");
					sbMaterila.append(String.valueOf(materialId));
	            }
	        }
	        int index = 0;
	        Set<Map.Entry<String,String>> entrySet = map.entrySet();
	        List<String> intervalBizType = new ArrayList<String>();
	        //入库单
	        Page page = new Page();
	        page.setModelClass(ScmInvPurInWarehsBillEntry2.class);
	        page.setShowCount(Integer.MAX_VALUE);
	        if(StringUtils.isNotBlank(sbMaterilaClass.toString())) {
	            page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmMaterialGroup.class)+"."+ScmMaterialGroup.FN_ID, 
	                    new QueryParam(ClassUtils.getFinalModelSimpleName(ScmMaterialGroup.class)+"."+ScmMaterialGroup.FN_ID, QueryParam.QUERY_IN, sbMaterilaClass.toString()));
	        }
	        if(StringUtils.isNotBlank(sbMaterila.toString())) {
	            page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBillEntry2.class)+"."+ScmInvPurInWarehsBillEntry2.FN_ITEMID, 
	                    new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBillEntry2.class)+"."+ScmInvPurInWarehsBillEntry2.FN_ITEMID, QueryParam.QUERY_IN, sbMaterila.toString()));
	        }
	        page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBillEntry2.class)+"."+ScmInvPurInWarehsBillEntry2.FN_COSTORGUNITNO, 
	                new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBillEntry2.class)+"."+ScmInvPurInWarehsBillEntry2.FN_COSTORGUNITNO, QueryParam.QUERY_IN, costOrgUnitNos.toString()));
	        page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBill2.class)+"."+ScmInvPurInWarehsBill2.FN_BIZDATE, 
	                new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBill2.class)+"."+ScmInvPurInWarehsBill2.FN_BIZDATE, QueryParam.QUERY_BETWEEN, beginDate,endDate));
	        if (!StringUtils.equals("Y", status)) {
	        	page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBill2.class)+"."+ScmInvPurInWarehsBill2.FN_STATUS, 
		                new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBill2.class)+"."+ScmInvPurInWarehsBill2.FN_STATUS, QueryParam.QUERY_EQ, "E"));
			}else{
				page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBill2.class)+"."+ScmInvPurInWarehsBill2.FN_STATUS, 
		                new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBill2.class)+"."+ScmInvPurInWarehsBill2.FN_STATUS, QueryParam.QUERY_NE, "N"));
			}
	        if (StringUtils.equals("Y", purInwarehs) && !StringUtils.equals("Y", purInwarehsReturn)) {
	            page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBill2.class)+"."+ScmInvPurInWarehsBill2.FN_BIZTYPE, 
	                    new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBill2.class)+"."+ScmInvPurInWarehsBill2.FN_BIZTYPE, QueryParam.QUERY_IN, "1,2,3,4,5"));
	        }
	        if (!StringUtils.equals("Y", purInwarehs) && StringUtils.equals("Y", purInwarehsReturn)) {
	            page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBill2.class)+"."+ScmInvPurInWarehsBill2.FN_BIZTYPE, 
	                    new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBill2.class)+"."+ScmInvPurInWarehsBill2.FN_BIZTYPE, QueryParam.QUERY_IN, "6,7,8,9,10"));
	        }
	        List<String> arglist = new ArrayList<>();
	        arglist.add("controlUnitNo="+currentControlUnitNo);
	        if (StringUtils.equals("Y", purInwarehs) || StringUtils.equals("Y", purInwarehsReturn) || StringUtils.equals("Y", costConsume2)) {
	            List<ScmInvPurInWarehsBillEntry2> scmInvPurInWarehsBillEntryList = scmInvPurInWarehsBillEntryBiz.queryPage(page, arglist, "findAllPage", param);
	            if(scmInvPurInWarehsBillEntryList!=null && !scmInvPurInWarehsBillEntryList.isEmpty()) {
	                List<ScmInvPurInWarehsBillEntry2> scmInvPurInWarehsBillEntryListIn = new ArrayList<ScmInvPurInWarehsBillEntry2>();
	                List<ScmInvPurInWarehsBillEntry2> scmInvPurInWarehsBillEntryListReturn = new ArrayList<ScmInvPurInWarehsBillEntry2>();
	                String[] bizTypeIn={"1","2","3","4","5"};
	                List<String> bizTypeListIn = Arrays.asList(bizTypeIn);
	                String[] bizTypeReturn={"6","7","8","9","10"};
	                List<String> bizTypeListReturn = Arrays.asList(bizTypeReturn);
	                for (ScmInvPurInWarehsBillEntry2 scmInvPurInWarehsBillEntry : scmInvPurInWarehsBillEntryList) {
	                    if (bizTypeListIn.contains(scmInvPurInWarehsBillEntry.getBizType())) {
	                        scmInvPurInWarehsBillEntryListIn.add(scmInvPurInWarehsBillEntry);
	                    } else if (bizTypeListReturn.contains(scmInvPurInWarehsBillEntry.getBizType())) {
	                        scmInvPurInWarehsBillEntryListReturn.add(scmInvPurInWarehsBillEntry);
	                    }
	                }
	                if (scmInvPurInWarehsBillEntryListIn != null && scmInvPurInWarehsBillEntryListIn.size() > 0) {
	                    // 采购入库
	                    if (StringUtils.equals("Y", purInwarehs)) {
	                        intervalBizType.add(index, "采购入库");
	                        index = index + 1;
	                        for (ScmInvPurInWarehsBillEntry2 scmInvPurInWarehsBillEntry : scmInvPurInWarehsBillEntryListIn) {
                        		ScmNewCostTransferOccurSum scmCostTransferOccurSum = new ScmNewCostTransferOccurSum(true);
                        		scmCostTransferOccurSum.setClassCode(scmInvPurInWarehsBillEntry.getGroupCode());
                                scmCostTransferOccurSum.setClassName(scmInvPurInWarehsBillEntry.getGroupName());
                                scmCostTransferOccurSum.setItemId(scmInvPurInWarehsBillEntry.getItemId());
                                scmCostTransferOccurSum.setItemNo(scmInvPurInWarehsBillEntry.getItemNo());
                                scmCostTransferOccurSum.setItemName(scmInvPurInWarehsBillEntry.getItemName());
                                scmCostTransferOccurSum.setSpec(scmInvPurInWarehsBillEntry.getSpec());
                                scmCostTransferOccurSum.setUnit(scmInvPurInWarehsBillEntry.getUnit());
                                scmCostTransferOccurSum.setUnitName(scmInvPurInWarehsBillEntry.getUnitName());
                                scmCostTransferOccurSum.setCostOrgUnitName(map.get(scmInvPurInWarehsBillEntry.getCostOrgUnitNo()));
                                scmCostTransferOccurSum.setQty(scmCostTransferOccurSum.getQty().add(scmInvPurInWarehsBillEntry.getQty()));
                                scmCostTransferOccurSum.setAmt(scmCostTransferOccurSum.getAmt().add(scmInvPurInWarehsBillEntry.getAmt()));
                                scmCostTransferOccurSum.setTaxAmt(scmCostTransferOccurSum.getTaxAmt().add(scmInvPurInWarehsBillEntry.getTaxAmt()));
								scmCostTransferOccurSum.setTax(scmCostTransferOccurSum.getTaxAmt().subtract(scmCostTransferOccurSum.getAmt()));
                                scmCostTransferOccurSum.setIntervaltype("采购入库");
                                scmCostTransferOccurSumList.add(scmCostTransferOccurSum);
	                        }
	                    }
	                    // 以领代耗
	                    if (StringUtils.equals("Y", costConsume2)) {
	                        intervalBizType.add(index, "以领代耗");
	                        index = index + 1;
	                        for (ScmInvPurInWarehsBillEntry2 scmInvPurInWarehsBillEntry : scmInvPurInWarehsBillEntryListIn) {
	                        	OrgCostCenter2 orgCostCenter = costMap.get(scmInvPurInWarehsBillEntry.getCostOrgUnitNo());
	                        	if (orgCostCenter == null) {
	                        		orgCostCenter = orgCostCenterBiz.selectByOrgUnitNo(scmInvPurInWarehsBillEntry.getCostOrgUnitNo(), param);
	                        		costMap.put(orgCostCenter.getOrgUnitNo(), orgCostCenter);
								}
	                        	if(orgCostCenter!=null && StringUtils.equals("2", orgCostCenter.getCostCenterType())) {
	    							continue;
	    						}
                        		ScmNewCostTransferOccurSum scmCostTransferOccurSum = new ScmNewCostTransferOccurSum(true);
                        		scmCostTransferOccurSum.setClassCode(scmInvPurInWarehsBillEntry.getGroupCode());
                                scmCostTransferOccurSum.setClassName(scmInvPurInWarehsBillEntry.getGroupName());
                                scmCostTransferOccurSum.setItemId(scmInvPurInWarehsBillEntry.getItemId());
                                scmCostTransferOccurSum.setItemNo(scmInvPurInWarehsBillEntry.getItemNo());
                                scmCostTransferOccurSum.setItemName(scmInvPurInWarehsBillEntry.getItemName());
                                scmCostTransferOccurSum.setSpec(scmInvPurInWarehsBillEntry.getSpec());
                                scmCostTransferOccurSum.setUnit(scmInvPurInWarehsBillEntry.getUnit());
                                scmCostTransferOccurSum.setUnitName(scmInvPurInWarehsBillEntry.getUnitName());
                                scmCostTransferOccurSum.setCostOrgUnitName(map.get(scmInvPurInWarehsBillEntry.getCostOrgUnitNo()));
                                scmCostTransferOccurSum.setQty(scmCostTransferOccurSum.getQty().add(scmInvPurInWarehsBillEntry.getQty()));
                                scmCostTransferOccurSum.setAmt(scmCostTransferOccurSum.getAmt().add(scmInvPurInWarehsBillEntry.getAmt()));
                                scmCostTransferOccurSum.setTaxAmt(scmCostTransferOccurSum.getTaxAmt().add(scmInvPurInWarehsBillEntry.getTaxAmt()));
								scmCostTransferOccurSum.setTax(scmCostTransferOccurSum.getTaxAmt().subtract(scmCostTransferOccurSum.getAmt()));
                                scmCostTransferOccurSum.setIntervaltype("以领代耗");
                                scmCostTransferOccurSumList.add(scmCostTransferOccurSum);
	                        }
	                    }
	                }
	                if (scmInvPurInWarehsBillEntryListReturn != null && scmInvPurInWarehsBillEntryListReturn.size() > 0) {
	                    // 采购入库（退货）
	                    if (StringUtils.equals("Y", purInwarehsReturn)) {
	                        intervalBizType.add(index, "采购入库（退货）");
	                        index = index + 1;
	                        for (ScmInvPurInWarehsBillEntry2 scmInvPurInWarehsBillEntry : scmInvPurInWarehsBillEntryListReturn) {
                        		 ScmNewCostTransferOccurSum scmCostTransferOccurSum = new ScmNewCostTransferOccurSum(true);
                        		 scmCostTransferOccurSum.setClassCode(scmInvPurInWarehsBillEntry.getGroupCode());
	                             scmCostTransferOccurSum.setClassName(scmInvPurInWarehsBillEntry.getGroupName());
	                             scmCostTransferOccurSum.setItemId(scmInvPurInWarehsBillEntry.getItemId());
	                             scmCostTransferOccurSum.setItemNo(scmInvPurInWarehsBillEntry.getItemNo());
	                             scmCostTransferOccurSum.setItemName(scmInvPurInWarehsBillEntry.getItemName());
	                             scmCostTransferOccurSum.setSpec(scmInvPurInWarehsBillEntry.getSpec());
	                             scmCostTransferOccurSum.setUnit(scmInvPurInWarehsBillEntry.getUnit());
	                             scmCostTransferOccurSum.setUnitName(scmInvPurInWarehsBillEntry.getUnitName());
	                             scmCostTransferOccurSum.setCostOrgUnitName(map.get(scmInvPurInWarehsBillEntry.getCostOrgUnitNo()));
	                             scmCostTransferOccurSum.setQty(scmCostTransferOccurSum.getQty().add(scmInvPurInWarehsBillEntry.getQty()));
	                             scmCostTransferOccurSum.setAmt(scmCostTransferOccurSum.getAmt().add(scmInvPurInWarehsBillEntry.getAmt()));
	                             scmCostTransferOccurSum.setTaxAmt(scmCostTransferOccurSum.getTaxAmt().add(scmInvPurInWarehsBillEntry.getTaxAmt()));
								 scmCostTransferOccurSum.setTax(scmCostTransferOccurSum.getTaxAmt().subtract(scmCostTransferOccurSum.getAmt()));
	                             scmCostTransferOccurSum.setIntervaltype("采购入库（退货）");
	                             scmCostTransferOccurSumList.add(scmCostTransferOccurSum);
	                        }
	                    }
	                    
	                    // 以领代耗
	                    if (StringUtils.equals("Y", costConsume2)) {
	                    	int existIndex=-1;
	                    	if(!intervalBizType.isEmpty()) {
	                    		for(int i=0;i<intervalBizType.size();i++) {
	                    			if(StringUtils.equals("以领代耗", intervalBizType.get(i))) {
	                    				existIndex=i;
	                    				break;
	                    			}
	                    		}
	                    	}
	                    	if(existIndex==-1) {
	                         intervalBizType.add(index, "以领代耗");
	                         index = index + 1;
	                         existIndex=index;
	                    	}else {
	                    		existIndex = existIndex + 1;
	                    	}
	                        for (ScmInvPurInWarehsBillEntry2 scmInvPurInWarehsBillEntry : scmInvPurInWarehsBillEntryListReturn) {
	                        	OrgCostCenter2 orgCostCenter = costMap.get(scmInvPurInWarehsBillEntry.getCostOrgUnitNo());
	                        	if (orgCostCenter == null) {
	                        		orgCostCenter = orgCostCenterBiz.selectByOrgUnitNo(scmInvPurInWarehsBillEntry.getCostOrgUnitNo(), param);
	                        		costMap.put(scmInvPurInWarehsBillEntry.getCostOrgUnitNo(), orgCostCenter);
								}
	                        	if(orgCostCenter!=null && StringUtils.equals("2", orgCostCenter.getCostCenterType())) {
	    							continue;
	    						}
                        		ScmNewCostTransferOccurSum scmCostTransferOccurSum = new ScmNewCostTransferOccurSum(true);
                        		scmCostTransferOccurSum.setClassCode(scmInvPurInWarehsBillEntry.getGroupCode());
                                scmCostTransferOccurSum.setClassName(scmInvPurInWarehsBillEntry.getGroupName());
                                scmCostTransferOccurSum.setItemId(scmInvPurInWarehsBillEntry.getItemId());
                                scmCostTransferOccurSum.setItemNo(scmInvPurInWarehsBillEntry.getItemNo());
                                scmCostTransferOccurSum.setItemName(scmInvPurInWarehsBillEntry.getItemName());
                                scmCostTransferOccurSum.setSpec(scmInvPurInWarehsBillEntry.getSpec());
                                scmCostTransferOccurSum.setUnit(scmInvPurInWarehsBillEntry.getUnit());
                                scmCostTransferOccurSum.setUnitName(scmInvPurInWarehsBillEntry.getUnitName());
                                scmCostTransferOccurSum.setCostOrgUnitName(map.get(scmInvPurInWarehsBillEntry.getCostOrgUnitNo()));
                                scmCostTransferOccurSum.setQty(scmCostTransferOccurSum.getQty().subtract(scmInvPurInWarehsBillEntry.getQty()));
                                scmCostTransferOccurSum.setAmt(scmCostTransferOccurSum.getAmt().subtract(scmInvPurInWarehsBillEntry.getAmt()));
                                scmCostTransferOccurSum.setTaxAmt(scmCostTransferOccurSum.getTaxAmt().subtract(scmInvPurInWarehsBillEntry.getTaxAmt()));
								scmCostTransferOccurSum.setTax(scmCostTransferOccurSum.getTaxAmt().subtract(scmCostTransferOccurSum.getAmt()));
                                scmCostTransferOccurSum.setIntervaltype("以领代耗");
                                scmCostTransferOccurSumList.add(scmCostTransferOccurSum); 
	                        }
	                    }
	                }
	            }    
	        }
	        //领料单
	        page = new Page();
	        page.setModelClass(ScmInvPurInWarehsBillEntry2.class);
	        page.setShowCount(Integer.MAX_VALUE);
	        if(StringUtils.isNotBlank(sbMaterilaClass.toString())) {
	            page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmMaterialGroup.class)+"."+ScmMaterialGroup.FN_ID, 
	                    new QueryParam(ClassUtils.getFinalModelSimpleName(ScmMaterialGroup.class)+"."+ScmMaterialGroup.FN_ID, QueryParam.QUERY_IN, sbMaterilaClass.toString()));
	        }
	        if(StringUtils.isNotBlank(sbMaterila.toString())) {
	            page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBillEntry2.class)+"."+ScmInvPurInWarehsBillEntry2.FN_ITEMID, 
	                    new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBillEntry2.class)+"."+ScmInvPurInWarehsBillEntry2.FN_ITEMID, QueryParam.QUERY_IN, sbMaterila.toString()));
	        }
	        page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBill.class)+"."+ScmInvMaterialReqBill.FN_COSTORGUNITNO, 
	                new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBill.class)+"."+ScmInvMaterialReqBill.FN_COSTORGUNITNO, QueryParam.QUERY_IN, costOrgUnitNos.toString()));
	        page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBill2.class)+"."+ScmInvMaterialReqBill2.FN_BIZDATE, 
	                new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBill2.class)+"."+ScmInvMaterialReqBill2.FN_BIZDATE, QueryParam.QUERY_BETWEEN, beginDate,endDate));
	        if (!StringUtils.equals("Y", status)) {
	        	page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBill2.class)+"."+ScmInvMaterialReqBill2.FN_STATUS, 
		                new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBill2.class)+"."+ScmInvMaterialReqBill2.FN_STATUS, QueryParam.QUERY_EQ, "E"));
			}else{
				page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBill2.class)+"."+ScmInvMaterialReqBill2.FN_STATUS, 
		                new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBill2.class)+"."+ScmInvMaterialReqBill2.FN_STATUS, QueryParam.QUERY_NE, "N"));
			}
	        if (StringUtils.equals("Y", materialReq) && !StringUtils.equals("Y", materialReqReturn)) {
	            page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBill2.class)+"."+ScmInvMaterialReqBill2.FN_BIZTYPE, 
	                    new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBill2.class)+"."+ScmInvMaterialReqBill2.FN_BIZTYPE, QueryParam.QUERY_EQ, "1"));
	        }
	        if (!StringUtils.equals("Y", materialReq) && StringUtils.equals("Y", materialReqReturn)) {
	            page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBill2.class)+"."+ScmInvMaterialReqBill2.FN_BIZTYPE, 
	                    new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBill2.class)+"."+ScmInvMaterialReqBill2.FN_BIZTYPE, QueryParam.QUERY_EQ, "2"));
	        }
	        if (StringUtils.equals("Y", materialReq) || StringUtils.equals("Y", materialReqReturn) || StringUtils.equals("Y", costConsume2)) {
	            List<ScmInvMaterialReqBillEntry2> scmInvMaterialReqBillEntryList = scmInvMaterialReqBillEntryBiz.queryPage(page, arglist, "findAllPage", param);
	            if(scmInvMaterialReqBillEntryList!=null && !scmInvMaterialReqBillEntryList.isEmpty()) {
	                List<ScmInvMaterialReqBillEntry2> scmInvMaterialReqBillEntryListIn = new ArrayList<ScmInvMaterialReqBillEntry2>();
	                List<ScmInvMaterialReqBillEntry2> scmInvMaterialReqBillEntryListReturn = new ArrayList<ScmInvMaterialReqBillEntry2>();
	                for (ScmInvMaterialReqBillEntry2 scmInvMaterialReqBillEntry : scmInvMaterialReqBillEntryList) {
	                    if (StringUtils.equals("1",scmInvMaterialReqBillEntry.getBizType())) {
	                        scmInvMaterialReqBillEntryListIn.add(scmInvMaterialReqBillEntry);
	                    } else if (StringUtils.equals("2",scmInvMaterialReqBillEntry.getBizType())) {
	                        scmInvMaterialReqBillEntryListReturn.add(scmInvMaterialReqBillEntry);
	                    }
	                }
	                if (scmInvMaterialReqBillEntryListIn != null && scmInvMaterialReqBillEntryListIn.size() > 0) {
	                    // 领料出库
	                    if (StringUtils.equals("Y", materialReq)) {
	                        intervalBizType.add(index, "领料出库");
	                        index = index + 1;
	                        for (ScmInvMaterialReqBillEntry2 scmInvMaterialReqBillEntry : scmInvMaterialReqBillEntryListIn) {
                        		ScmNewCostTransferOccurSum scmCostTransferOccurSum = new ScmNewCostTransferOccurSum(true);
                        		scmCostTransferOccurSum.setClassCode(scmInvMaterialReqBillEntry.getGroupCode());
                                scmCostTransferOccurSum.setClassName(scmInvMaterialReqBillEntry.getGroupName());
                                scmCostTransferOccurSum.setItemId(scmInvMaterialReqBillEntry.getItemId());
                                scmCostTransferOccurSum.setItemNo(scmInvMaterialReqBillEntry.getItemNo());
                                scmCostTransferOccurSum.setItemName(scmInvMaterialReqBillEntry.getItemName());
                                scmCostTransferOccurSum.setSpec(scmInvMaterialReqBillEntry.getSpec());
                                scmCostTransferOccurSum.setUnit(scmInvMaterialReqBillEntry.getUnit());
                                scmCostTransferOccurSum.setUnitName(scmInvMaterialReqBillEntry.getUnitName());
                                scmCostTransferOccurSum.setCostOrgUnitName(map.get(scmInvMaterialReqBillEntry.getCostOrgUnitNo()));
                                scmCostTransferOccurSum.setQty(scmCostTransferOccurSum.getQty().add(scmInvMaterialReqBillEntry.getQty()));
                                scmCostTransferOccurSum.setAmt(scmCostTransferOccurSum.getAmt().add(scmInvMaterialReqBillEntry.getAmt()));
                                scmCostTransferOccurSum.setTaxAmt(scmCostTransferOccurSum.getTaxAmt().add(scmInvMaterialReqBillEntry.getTaxAmt()));
								scmCostTransferOccurSum.setTax(scmCostTransferOccurSum.getTaxAmt().subtract(scmCostTransferOccurSum.getAmt()));
                                scmCostTransferOccurSum.setIntervaltype("领料出库");
                                scmCostTransferOccurSumList.add(scmCostTransferOccurSum);
	                        }
	                    }
	                    // 以领代耗
	                    if (StringUtils.equals("Y", costConsume2)) {
	                    	int existIndex=-1;
	                    	if(!intervalBizType.isEmpty()) {
	                    		for(int i=0;i<intervalBizType.size();i++) {
	                    			if(StringUtils.equals("以领代耗", intervalBizType.get(i))) {
	                    				existIndex=i;
	                    				break;
	                    			}
	                    		}
	                    	}
	                    	if(existIndex==-1) {
	                         intervalBizType.add(index, "以领代耗");
	                         index = index + 1;
	                         existIndex=index;
	                    	}else {
	                    		existIndex = existIndex + 1;
	                    	}
	                    	for (ScmInvMaterialReqBillEntry2 scmInvMaterialReqBillEntry : scmInvMaterialReqBillEntryListIn) {
	                    		OrgCostCenter2 orgCostCenter = costMap.get(scmInvMaterialReqBillEntry.getCostOrgUnitNo());
	                    		if (orgCostCenter == null) {
	                    			orgCostCenter = orgCostCenterBiz.selectByOrgUnitNo(scmInvMaterialReqBillEntry.getCostOrgUnitNo(), param);
	                    			costMap.put(scmInvMaterialReqBillEntry.getCostOrgUnitNo(), orgCostCenter);
								}
	                        	if(orgCostCenter!=null && StringUtils.equals("2", orgCostCenter.getCostCenterType())) {
	    							continue;
	    						}
                        		ScmNewCostTransferOccurSum scmCostTransferOccurSum = new ScmNewCostTransferOccurSum(true);
                        		scmCostTransferOccurSum.setClassCode(scmInvMaterialReqBillEntry.getGroupCode());
                                scmCostTransferOccurSum.setClassName(scmInvMaterialReqBillEntry.getGroupName());
                                scmCostTransferOccurSum.setItemId(scmInvMaterialReqBillEntry.getItemId());
                                scmCostTransferOccurSum.setItemNo(scmInvMaterialReqBillEntry.getItemNo());
                                scmCostTransferOccurSum.setItemName(scmInvMaterialReqBillEntry.getItemName());
                                scmCostTransferOccurSum.setSpec(scmInvMaterialReqBillEntry.getSpec());
                                scmCostTransferOccurSum.setUnit(scmInvMaterialReqBillEntry.getUnit());
                                scmCostTransferOccurSum.setUnitName(scmInvMaterialReqBillEntry.getUnitName());
                                scmCostTransferOccurSum.setCostOrgUnitName(map.get(scmInvMaterialReqBillEntry.getCostOrgUnitNo()));
                                scmCostTransferOccurSum.setQty(scmCostTransferOccurSum.getQty().add(scmInvMaterialReqBillEntry.getQty()));
                                scmCostTransferOccurSum.setAmt(scmCostTransferOccurSum.getAmt().add(scmInvMaterialReqBillEntry.getAmt()));
                                scmCostTransferOccurSum.setTaxAmt(scmCostTransferOccurSum.getTaxAmt().add(scmInvMaterialReqBillEntry.getTaxAmt()));
								scmCostTransferOccurSum.setTax(scmCostTransferOccurSum.getTaxAmt().subtract(scmCostTransferOccurSum.getAmt()));
                                scmCostTransferOccurSum.setIntervaltype("以领代耗");
                                scmCostTransferOccurSumList.add(scmCostTransferOccurSum);
	                        }
	                    }
	                }
	                if (scmInvMaterialReqBillEntryListReturn != null && scmInvMaterialReqBillEntryListReturn.size() > 0) {
	                    // 领料退仓
	                    if (StringUtils.equals("Y", materialReqReturn)) {
	                        intervalBizType.add(index, "领料退仓");
	                        index = index + 1;
	                        for (ScmInvMaterialReqBillEntry2 scmInvMaterialReqBillEntry : scmInvMaterialReqBillEntryListReturn) {
                    		 	ScmNewCostTransferOccurSum scmCostTransferOccurSum = new ScmNewCostTransferOccurSum(true);
                    		 	scmCostTransferOccurSum.setClassCode(scmInvMaterialReqBillEntry.getGroupCode());
                                scmCostTransferOccurSum.setClassName(scmInvMaterialReqBillEntry.getGroupName());
                                scmCostTransferOccurSum.setItemId(scmInvMaterialReqBillEntry.getItemId());
                                scmCostTransferOccurSum.setItemNo(scmInvMaterialReqBillEntry.getItemNo());
                                scmCostTransferOccurSum.setItemName(scmInvMaterialReqBillEntry.getItemName());
                                scmCostTransferOccurSum.setSpec(scmInvMaterialReqBillEntry.getSpec());
                                scmCostTransferOccurSum.setUnit(scmInvMaterialReqBillEntry.getUnit());
                                scmCostTransferOccurSum.setUnitName(scmInvMaterialReqBillEntry.getUnitName());
                                scmCostTransferOccurSum.setCostOrgUnitName(map.get(scmInvMaterialReqBillEntry.getCostOrgUnitNo()));
                                scmCostTransferOccurSum.setQty(scmCostTransferOccurSum.getQty().add(scmInvMaterialReqBillEntry.getQty()));
                                scmCostTransferOccurSum.setAmt(scmCostTransferOccurSum.getAmt().add(scmInvMaterialReqBillEntry.getAmt()));
                                scmCostTransferOccurSum.setTaxAmt(scmCostTransferOccurSum.getTaxAmt().add(scmInvMaterialReqBillEntry.getTaxAmt()));
								scmCostTransferOccurSum.setTax(scmCostTransferOccurSum.getTaxAmt().subtract(scmCostTransferOccurSum.getAmt()));
                                scmCostTransferOccurSum.setIntervaltype("领料退仓");
                                scmCostTransferOccurSumList.add(scmCostTransferOccurSum);
	                        }
	                    }
	                    // 以领代耗
	                    if (StringUtils.equals("Y", costConsume2)) {
	                    	int existIndex=-1;
	                    	if(!intervalBizType.isEmpty()) {
	                    		for(int i=0;i<intervalBizType.size();i++) {
	                    			if(StringUtils.equals("以领代耗", intervalBizType.get(i))) {
	                    				existIndex=i;
	                    				break;
	                    			}
	                    		}
	                    	}
	                    	if(existIndex==-1) {
	                         intervalBizType.add(index, "以领代耗");
	                         index = index + 1;
	                         existIndex=index;
	                    	}else {
	                    		existIndex = existIndex + 1;
	                    	}
	                    	for (ScmInvMaterialReqBillEntry2 scmInvMaterialReqBillEntry : scmInvMaterialReqBillEntryListReturn) {
	                    		OrgCostCenter2 orgCostCenter = costMap.get(scmInvMaterialReqBillEntry.getCostOrgUnitNo());
	                    		if (orgCostCenter == null) {
	                    			orgCostCenter = orgCostCenterBiz.selectByOrgUnitNo(scmInvMaterialReqBillEntry.getCostOrgUnitNo(), param);
	                    			costMap.put(scmInvMaterialReqBillEntry.getCostOrgUnitNo(), orgCostCenter);
								}
	                        	if(orgCostCenter!=null && StringUtils.equals("2", orgCostCenter.getCostCenterType())) {
	    							continue;
	    						}
	                        		ScmNewCostTransferOccurSum scmCostTransferOccurSum = new ScmNewCostTransferOccurSum(true);
	                        		scmCostTransferOccurSum.setClassCode(scmInvMaterialReqBillEntry.getGroupCode());
	                                scmCostTransferOccurSum.setClassName(scmInvMaterialReqBillEntry.getGroupName());
	                                scmCostTransferOccurSum.setItemId(scmInvMaterialReqBillEntry.getItemId());
	                                scmCostTransferOccurSum.setItemNo(scmInvMaterialReqBillEntry.getItemNo());
	                                scmCostTransferOccurSum.setItemName(scmInvMaterialReqBillEntry.getItemName());
	                                scmCostTransferOccurSum.setSpec(scmInvMaterialReqBillEntry.getSpec());
	                                scmCostTransferOccurSum.setUnit(scmInvMaterialReqBillEntry.getUnit());
	                                scmCostTransferOccurSum.setUnitName(scmInvMaterialReqBillEntry.getUnitName());
	                                scmCostTransferOccurSum.setCostOrgUnitName(map.get(scmInvMaterialReqBillEntry.getCostOrgUnitNo()));
	                                scmCostTransferOccurSum.setQty(scmCostTransferOccurSum.getQty().subtract(scmInvMaterialReqBillEntry.getQty()));
	                                scmCostTransferOccurSum.setAmt(scmCostTransferOccurSum.getAmt().subtract(scmInvMaterialReqBillEntry.getAmt()));
	                                scmCostTransferOccurSum.setTaxAmt(scmCostTransferOccurSum.getTaxAmt().subtract(scmInvMaterialReqBillEntry.getTaxAmt()));
									scmCostTransferOccurSum.setTax(scmCostTransferOccurSum.getTaxAmt().subtract(scmCostTransferOccurSum.getAmt()));
	                                scmCostTransferOccurSum.setIntervaltype("以领代耗");
	                                scmCostTransferOccurSumList.add(scmCostTransferOccurSum);
	                        }
	                    }
	                }
	            }
	        }
	        
        	//销售出库单
	        page = new Page();
	        page.setModelClass(ScmInvSaleIssueBillEntry2.class);
	        page.setShowCount(Integer.MAX_VALUE);
	        if(StringUtils.isNotBlank(sbMaterilaClass.toString())) {
	            page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmMaterialGroup.class)+"."+ScmMaterialGroup.FN_ID, 
	                    new QueryParam(ClassUtils.getFinalModelSimpleName(ScmMaterialGroup.class)+"."+ScmMaterialGroup.FN_ID, QueryParam.QUERY_IN, sbMaterilaClass.toString()));
	        }
	        if(StringUtils.isNotBlank(sbMaterila.toString())) {
	            page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBillEntry2.class)+"."+ScmInvSaleIssueBillEntry2.FN_ITEMID, 
	                    new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBillEntry2.class)+"."+ScmInvSaleIssueBillEntry2.FN_ITEMID, QueryParam.QUERY_IN, sbMaterila.toString()));
	        }
	        page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBillEntry2.class)+"."+ScmInvSaleIssueBillEntry2.FN_OUTCOSTORGUNITNO, 
	                new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBillEntry2.class)+"."+ScmInvSaleIssueBillEntry2.FN_OUTCOSTORGUNITNO, QueryParam.QUERY_IN, costOrgUnitNos.toString()));
	        page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBill.class)+"."+ScmInvSaleIssueBill.FN_BIZDATE, 
	                new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBill.class)+"."+ScmInvSaleIssueBill.FN_BIZDATE, QueryParam.QUERY_BETWEEN, beginDate,endDate));
	        if (!StringUtils.equals("Y", status)) {
	        	page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBill.class)+"."+ScmInvSaleIssueBill.FN_STATUS, 
		                new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBill.class)+"."+ScmInvSaleIssueBill.FN_STATUS, QueryParam.QUERY_EQ, "E"));
			}else{
				page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBill.class)+"."+ScmInvSaleIssueBill.FN_STATUS, 
		                new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBill.class)+"."+ScmInvSaleIssueBill.FN_STATUS, QueryParam.QUERY_NE, "N"));
			}
	        if (StringUtils.equals("Y", InvSaleOut) && !StringUtils.equals("Y", InvSaleIn)) {
	            page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBill.class)+"."+ScmInvSaleIssueBill.FN_BIZTYPE, 
	                    new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBill.class)+"."+ScmInvSaleIssueBill.FN_BIZTYPE, QueryParam.QUERY_IN, "1,2,3"));
	        }
	        if (!StringUtils.equals("Y", InvSaleOut) && StringUtils.equals("Y", InvSaleIn)) {
	            page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBill.class)+"."+ScmInvSaleIssueBill.FN_BIZTYPE, 
	                    new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBill.class)+"."+ScmInvSaleIssueBill.FN_BIZTYPE, QueryParam.QUERY_IN, "6,7,8"));
	        }
	        if (StringUtils.equals("Y", InvSaleOut) || StringUtils.equals("Y", InvSaleIn) || StringUtils.equals("Y", costConsume2)) {
	            List<ScmInvSaleIssueBillEntry2> scmInvSaleIssueBillEntryList = scmInvSaleIssueBillEntryBiz.queryPage(page, arglist, "findAllPage", param);
	            if(scmInvSaleIssueBillEntryList!=null && !scmInvSaleIssueBillEntryList.isEmpty()) {
	                List<ScmInvSaleIssueBillEntry2> scmInvSaleIssueBillEntryListIn = new ArrayList<ScmInvSaleIssueBillEntry2>();
	                List<ScmInvSaleIssueBillEntry2> scmInvSaleIssueBillEntryListOut = new ArrayList<ScmInvSaleIssueBillEntry2>();
	                String[] bizTypeIn={"6","7","8"};
	                List<String> bizTypeListIn = Arrays.asList(bizTypeIn);
	                String[] bizTypeOut={"1","2","3"};
	                List<String> bizTypeListOut = Arrays.asList(bizTypeOut);
	                for (ScmInvSaleIssueBillEntry2 scmInvSaleIssueBillEntry : scmInvSaleIssueBillEntryList) {
	                    if (bizTypeListIn.contains(scmInvSaleIssueBillEntry.getBizType())) {
	                    	scmInvSaleIssueBillEntryListIn.add(scmInvSaleIssueBillEntry);
	                    } else if (bizTypeListOut.contains(scmInvSaleIssueBillEntry.getBizType())) {
	                    	scmInvSaleIssueBillEntryListOut.add(scmInvSaleIssueBillEntry);
	                    }
	                }
	                if (scmInvSaleIssueBillEntryListIn != null && scmInvSaleIssueBillEntryListIn.size() > 0) {
	                    // 销售退货
	                    if (StringUtils.equals("Y", InvSaleIn)) {
	                        intervalBizType.add(index, "销售退货");
	                        index = index + 1;
	                        for (ScmInvSaleIssueBillEntry2 scmInvSaleIssueBillEntry : scmInvSaleIssueBillEntryListIn) {
                        		ScmNewCostTransferOccurSum scmCostTransferOccurSum = new ScmNewCostTransferOccurSum(true);
                        		scmCostTransferOccurSum.setClassCode(scmInvSaleIssueBillEntry.getGroupCode());
                                scmCostTransferOccurSum.setClassName(scmInvSaleIssueBillEntry.getGroupName());
                                scmCostTransferOccurSum.setItemId(scmInvSaleIssueBillEntry.getItemId());
                                scmCostTransferOccurSum.setItemNo(scmInvSaleIssueBillEntry.getItemNo());
                                scmCostTransferOccurSum.setItemName(scmInvSaleIssueBillEntry.getItemName());
                                scmCostTransferOccurSum.setSpec(scmInvSaleIssueBillEntry.getSpec());
                                scmCostTransferOccurSum.setUnit(scmInvSaleIssueBillEntry.getUnit());
                                scmCostTransferOccurSum.setUnitName(scmInvSaleIssueBillEntry.getUnitName());
                                scmCostTransferOccurSum.setCostOrgUnitName(map.get(scmInvSaleIssueBillEntry.getOutCostOrgUnitNo()));
                                scmCostTransferOccurSum.setQty(scmCostTransferOccurSum.getQty().add(scmInvSaleIssueBillEntry.getQty()));
                                scmCostTransferOccurSum.setAmt(scmCostTransferOccurSum.getAmt().add(scmInvSaleIssueBillEntry.getAmt()));
                                scmCostTransferOccurSum.setTaxAmt(scmCostTransferOccurSum.getTaxAmt().add(scmInvSaleIssueBillEntry.getTaxAmt()));
								scmCostTransferOccurSum.setTax(scmCostTransferOccurSum.getTaxAmt().subtract(scmCostTransferOccurSum.getAmt()));
                                scmCostTransferOccurSum.setIntervaltype("销售退货");
                                scmCostTransferOccurSumList.add(scmCostTransferOccurSum);
	                        }
	                    }
	                    // 以领代耗
	                    if (StringUtils.equals("Y", costConsume2)) {
	                    	int existIndex=-1;
	                    	if(!intervalBizType.isEmpty()) {
	                    		for(int i=0;i<intervalBizType.size();i++) {
	                    			if(StringUtils.equals("以领代耗", intervalBizType.get(i))) {
	                    				existIndex=i;
	                    				break;
	                    			}
	                    		}
	                    	}
	                    	if(existIndex==-1) {
	                         intervalBizType.add(index, "以领代耗");
	                         index = index + 1;
	                         existIndex=index;
	                    	}else {
	                    		existIndex = existIndex + 1;
	                    	}
	                    	for (ScmInvSaleIssueBillEntry2 scmInvSaleIssueBillEntry : scmInvSaleIssueBillEntryListOut) {
	                    		OrgCostCenter2 orgCostCenter = costMap.get(scmInvSaleIssueBillEntry.getOutCostOrgUnitNo());
	                    		if (orgCostCenter == null) {
	                    			orgCostCenter = orgCostCenterBiz.selectByOrgUnitNo(scmInvSaleIssueBillEntry.getOutCostOrgUnitNo(), param);
	                    			costMap.put(scmInvSaleIssueBillEntry.getOutCostOrgUnitNo(), orgCostCenter);
								}
	                        	if(orgCostCenter!=null && StringUtils.equals("2", orgCostCenter.getCostCenterType())) {
	    							continue;
	    						}
                        		ScmNewCostTransferOccurSum scmCostTransferOccurSum = new ScmNewCostTransferOccurSum(true);
                        		scmCostTransferOccurSum.setClassCode(scmInvSaleIssueBillEntry.getGroupCode());
                                scmCostTransferOccurSum.setClassName(scmInvSaleIssueBillEntry.getGroupName());
                                scmCostTransferOccurSum.setItemId(scmInvSaleIssueBillEntry.getItemId());
                                scmCostTransferOccurSum.setItemNo(scmInvSaleIssueBillEntry.getItemNo());
                                scmCostTransferOccurSum.setItemName(scmInvSaleIssueBillEntry.getItemName());
                                scmCostTransferOccurSum.setSpec(scmInvSaleIssueBillEntry.getSpec());
                                scmCostTransferOccurSum.setUnit(scmInvSaleIssueBillEntry.getUnit());
                                scmCostTransferOccurSum.setUnitName(scmInvSaleIssueBillEntry.getUnitName());
                                scmCostTransferOccurSum.setCostOrgUnitName(map.get(scmInvSaleIssueBillEntry.getOutCostOrgUnitNo()));
                                scmCostTransferOccurSum.setQty(scmCostTransferOccurSum.getQty().add(scmInvSaleIssueBillEntry.getQty()));
                                scmCostTransferOccurSum.setAmt(scmCostTransferOccurSum.getAmt().add(scmInvSaleIssueBillEntry.getAmt()));
                                scmCostTransferOccurSum.setTaxAmt(scmCostTransferOccurSum.getTaxAmt().add(scmInvSaleIssueBillEntry.getTaxAmt()));
								scmCostTransferOccurSum.setTax(scmCostTransferOccurSum.getTaxAmt().subtract(scmCostTransferOccurSum.getAmt()));
                                scmCostTransferOccurSum.setIntervaltype("以领代耗");
                                scmCostTransferOccurSumList.add(scmCostTransferOccurSum);
	                        }
	                    }
	                }
	                if (scmInvSaleIssueBillEntryListOut != null && scmInvSaleIssueBillEntryListOut.size() > 0) {
	                    // 销售出库
	                    if (StringUtils.equals("Y", InvSaleOut)) {
	                        intervalBizType.add(index, "销售出库");
	                        index = index + 1;
	                        for (ScmInvSaleIssueBillEntry2 scmInvSaleIssueBillEntry : scmInvSaleIssueBillEntryListOut) {
                    		 	ScmNewCostTransferOccurSum scmCostTransferOccurSum = new ScmNewCostTransferOccurSum(true);
                    		 	scmCostTransferOccurSum.setClassCode(scmInvSaleIssueBillEntry.getGroupCode());
                                scmCostTransferOccurSum.setClassName(scmInvSaleIssueBillEntry.getGroupName());
                                scmCostTransferOccurSum.setItemId(scmInvSaleIssueBillEntry.getItemId());
                                scmCostTransferOccurSum.setItemNo(scmInvSaleIssueBillEntry.getItemNo());
                                scmCostTransferOccurSum.setItemName(scmInvSaleIssueBillEntry.getItemName());
                                scmCostTransferOccurSum.setSpec(scmInvSaleIssueBillEntry.getSpec());
                                scmCostTransferOccurSum.setUnit(scmInvSaleIssueBillEntry.getUnit());
                                scmCostTransferOccurSum.setUnitName(scmInvSaleIssueBillEntry.getUnitName());
                                scmCostTransferOccurSum.setCostOrgUnitName(map.get(scmInvSaleIssueBillEntry.getOutCostOrgUnitNo()));
                                scmCostTransferOccurSum.setQty(scmCostTransferOccurSum.getQty().add(scmInvSaleIssueBillEntry.getQty()));
                                scmCostTransferOccurSum.setAmt(scmCostTransferOccurSum.getAmt().add(scmInvSaleIssueBillEntry.getAmt()));
                                scmCostTransferOccurSum.setTaxAmt(scmCostTransferOccurSum.getTaxAmt().add(scmInvSaleIssueBillEntry.getTaxAmt()));
								scmCostTransferOccurSum.setTax(scmCostTransferOccurSum.getTaxAmt().subtract(scmCostTransferOccurSum.getAmt()));
                                scmCostTransferOccurSum.setIntervaltype("销售出库");
                                scmCostTransferOccurSumList.add(scmCostTransferOccurSum);
	                        }
	                    }
	                    // 以领代耗
	                    if (StringUtils.equals("Y", costConsume2)) {
	                    	int existIndex=-1;
	                    	if(!intervalBizType.isEmpty()) {
	                    		for(int i=0;i<intervalBizType.size();i++) {
	                    			if(StringUtils.equals("以领代耗", intervalBizType.get(i))) {
	                    				existIndex=i;
	                    				break;
	                    			}
	                    		}
	                    	}
	                    	if(existIndex==-1) {
	                         intervalBizType.add(index, "以领代耗");
	                         index = index + 1;
	                         existIndex=index;
	                    	}else {
	                    		existIndex = existIndex + 1;
	                    	}
	                    	for (ScmInvSaleIssueBillEntry2 scmInvSaleIssueBillEntry : scmInvSaleIssueBillEntryListOut) {
	                    		OrgCostCenter2 orgCostCenter = costMap.get(scmInvSaleIssueBillEntry.getOutCostOrgUnitNo());
	                    		if (orgCostCenter == null) {
	                    			orgCostCenter = orgCostCenterBiz.selectByOrgUnitNo(scmInvSaleIssueBillEntry.getOutCostOrgUnitNo(), param);
	                    			costMap.put(scmInvSaleIssueBillEntry.getOutCostOrgUnitNo(), orgCostCenter);
								}
	                        	if(orgCostCenter!=null && StringUtils.equals("2", orgCostCenter.getCostCenterType())) {
	    							continue;
	    						}
                        		ScmNewCostTransferOccurSum scmCostTransferOccurSum = new ScmNewCostTransferOccurSum(true);
                        		scmCostTransferOccurSum.setClassCode(scmInvSaleIssueBillEntry.getGroupCode());
                                scmCostTransferOccurSum.setClassName(scmInvSaleIssueBillEntry.getGroupName());
                                scmCostTransferOccurSum.setItemId(scmInvSaleIssueBillEntry.getItemId());
                                scmCostTransferOccurSum.setItemNo(scmInvSaleIssueBillEntry.getItemNo());
                                scmCostTransferOccurSum.setItemName(scmInvSaleIssueBillEntry.getItemName());
                                scmCostTransferOccurSum.setSpec(scmInvSaleIssueBillEntry.getSpec());
                                scmCostTransferOccurSum.setUnit(scmInvSaleIssueBillEntry.getUnit());
                                scmCostTransferOccurSum.setUnitName(scmInvSaleIssueBillEntry.getUnitName());
                                scmCostTransferOccurSum.setCostOrgUnitName(map.get(scmInvSaleIssueBillEntry.getOutCostOrgUnitNo()));
                                scmCostTransferOccurSum.setQty(scmCostTransferOccurSum.getQty().subtract(scmInvSaleIssueBillEntry.getQty()));
                                scmCostTransferOccurSum.setAmt(scmCostTransferOccurSum.getAmt().subtract(scmInvSaleIssueBillEntry.getAmt()));
                                scmCostTransferOccurSum.setTaxAmt(scmCostTransferOccurSum.getTaxAmt().subtract(scmInvSaleIssueBillEntry.getTaxAmt()));
								scmCostTransferOccurSum.setTax(scmCostTransferOccurSum.getTaxAmt().subtract(scmCostTransferOccurSum.getAmt()));
                                scmCostTransferOccurSum.setIntervaltype("以领代耗");
                                scmCostTransferOccurSumList.add(scmCostTransferOccurSum);
	                        }
	                    }
	                }
	            }
	        }
	        
	        //成本转移单
	        page = new Page();
	        page.setModelClass(ScmInvMoveBillEntry2.class);
	        page.setShowCount(Integer.MAX_VALUE);
	        if(StringUtils.isNotBlank(sbMaterilaClass.toString())) {
	            page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmMaterialGroup.class)+"."+ScmMaterialGroup.FN_ID, 
	                    new QueryParam(ClassUtils.getFinalModelSimpleName(ScmMaterialGroup.class)+"."+ScmMaterialGroup.FN_ID, QueryParam.QUERY_IN, sbMaterilaClass.toString()));
	        }
	        if(StringUtils.isNotBlank(sbMaterila.toString())) {
	            page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvMoveBillEntry2.class)+"."+ScmInvMoveBillEntry2.FN_ITEMID, 
	                    new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMoveBillEntry2.class)+"."+ScmInvMoveBillEntry2.FN_ITEMID, QueryParam.QUERY_IN, sbMaterila.toString()));
	        }
	        page.getParamOr().put(ClassUtils.getFinalModelSimpleName(ScmInvMoveBill.class)+"."+ScmInvMoveBill.FN_OUTCSTORGUNITNO, 
	                new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMoveBill.class)+"."+ScmInvMoveBill.FN_OUTCSTORGUNITNO, QueryParam.QUERY_IN, costOrgUnitNos.toString()));
	        page.getParamOr().put(ClassUtils.getFinalModelSimpleName(ScmInvMoveBill.class)+"."+ScmInvMoveBill.FN_INCSTORGUNITNO, 
	                new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMoveBill.class)+"."+ScmInvMoveBill.FN_INCSTORGUNITNO, QueryParam.QUERY_IN, costOrgUnitNos.toString()));
	        page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvMoveBill.class)+"."+ScmInvMoveBill.FN_BIZDATE, 
	                new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMoveBill.class)+"."+ScmInvMoveBill.FN_BIZDATE, QueryParam.QUERY_BETWEEN, beginDate,endDate));
	        if (!StringUtils.equals("Y", status)) {
	        	page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvMoveBill.class)+"."+ScmInvMoveBill.FN_STATUS, 
		                new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMoveBill.class)+"."+ScmInvMoveBill.FN_STATUS, QueryParam.QUERY_EQ, "E"));
			}else{
				page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvMoveBill.class)+"."+ScmInvMoveBill.FN_STATUS, 
		                new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMoveBill.class)+"."+ScmInvMoveBill.FN_STATUS, QueryParam.QUERY_NE, "N"));
			}
	        if (StringUtils.equals("Y", moveIn) || StringUtils.equals("Y", moveOut) || StringUtils.equals("Y", costConsume2)) {
	            List<ScmInvMoveBillEntry2> scmInvMoveBillEntryList = scmInvMoveBillEntryBiz.queryPage(page, arglist, "findAllPage", param);
	            if(scmInvMoveBillEntryList!=null && !scmInvMoveBillEntryList.isEmpty()) {
	                List<ScmInvMoveBillEntry2> scmInvMoveBillEntryListIn = new ArrayList<ScmInvMoveBillEntry2>(); 
	                List<ScmInvMoveBillEntry2> scmInvMoveBillEntryListOut = new ArrayList<ScmInvMoveBillEntry2>(); 
	                for (ScmInvMoveBillEntry2 scmInvMoveBillEntry : scmInvMoveBillEntryList) {
	                    if (StringUtils.contains(costOrgUnitNos.toString(), scmInvMoveBillEntry.getInCstOrgUnitNo())) {//入
	                        scmInvMoveBillEntryListIn.add(scmInvMoveBillEntry);
	                    }
	                    if (StringUtils.contains(costOrgUnitNos.toString(), scmInvMoveBillEntry.getOutCstOrgUnitNo())) {//出
	                        scmInvMoveBillEntryListOut.add(scmInvMoveBillEntry);
	                    }
	                }
	                if (scmInvMoveBillEntryListIn != null && scmInvMoveBillEntryListIn.size() > 0) {
	                    if (StringUtils.equals("Y", moveIn)) {
	                        intervalBizType.add(index, "成本转移单入");
	                        index = index + 1;
	                        for (ScmInvMoveBillEntry2 scmInvMoveBillEntry : scmInvMoveBillEntryListIn) {
                        		ScmNewCostTransferOccurSum scmCostTransferOccurSum = new ScmNewCostTransferOccurSum(true);
                        		scmCostTransferOccurSum.setClassCode(scmInvMoveBillEntry.getGroupCode());
                                scmCostTransferOccurSum.setClassName(scmInvMoveBillEntry.getGroupName());
                                scmCostTransferOccurSum.setItemId(scmInvMoveBillEntry.getItemId());
                                scmCostTransferOccurSum.setItemNo(scmInvMoveBillEntry.getItemNo());
                                scmCostTransferOccurSum.setItemName(scmInvMoveBillEntry.getItemName());
                                scmCostTransferOccurSum.setSpec(scmInvMoveBillEntry.getSpec());
                                scmCostTransferOccurSum.setUnit(scmInvMoveBillEntry.getUnit());
                                scmCostTransferOccurSum.setUnitName(scmInvMoveBillEntry.getUnitName());
                                scmCostTransferOccurSum.setCostOrgUnitNo(scmInvMoveBillEntry.getInCstOrgUnitNo());
                                scmCostTransferOccurSum.setCostOrgUnitName(map.get(scmInvMoveBillEntry.getInCstOrgUnitNo()));
                                scmCostTransferOccurSum.setQty(scmCostTransferOccurSum.getQty().add(scmInvMoveBillEntry.getQty()));
                                scmCostTransferOccurSum.setAmt(scmCostTransferOccurSum.getAmt().add(scmInvMoveBillEntry.getAmt()));
                                scmCostTransferOccurSum.setTaxAmt(scmCostTransferOccurSum.getTaxAmt().add(scmInvMoveBillEntry.getTaxAmt()));
								scmCostTransferOccurSum.setTax(scmCostTransferOccurSum.getTaxAmt().subtract(scmCostTransferOccurSum.getAmt()));
                                scmCostTransferOccurSum.setIntervaltype("成本转移单入");
                                scmCostTransferOccurSumList.add(scmCostTransferOccurSum);
	                        }
	                    }
	                    // 以领代耗
	                    if (StringUtils.equals("Y", costConsume2)) {
	                    	int existIndex=-1;
	                    	if(!intervalBizType.isEmpty()) {
	                    		for(int i=0;i<intervalBizType.size();i++) {
	                    			if(StringUtils.equals("以领代耗", intervalBizType.get(i))) {
	                    				existIndex=i;
	                    				break;
	                    			}
	                    		}
	                    	}
	                    	if(existIndex==-1) {
	                         intervalBizType.add(index, "以领代耗");
	                         index = index + 1;
	                         existIndex=index;
	                    	}else {
	                    		existIndex = existIndex + 1;
	                    	}
	                    	 for (ScmInvMoveBillEntry2 scmInvMoveBillEntry : scmInvMoveBillEntryListIn) {
	                    		 OrgCostCenter2 orgCostCenter = costMap.get(scmInvMoveBillEntry.getInCstOrgUnitNo());
	                    		 if (orgCostCenter == null) {
	                    			 orgCostCenter = orgCostCenterBiz.selectByOrgUnitNo(scmInvMoveBillEntry.getInCstOrgUnitNo(), param);
	                    			 costMap.put(scmInvMoveBillEntry.getInCstOrgUnitNo(), orgCostCenter);
								}
	                        	if(orgCostCenter!=null && StringUtils.equals("2", orgCostCenter.getCostCenterType())) {
	    							continue;
	    						}
                        		ScmNewCostTransferOccurSum scmCostTransferOccurSum = new ScmNewCostTransferOccurSum(true);
                        		scmCostTransferOccurSum.setClassCode(scmInvMoveBillEntry.getGroupCode());
                                scmCostTransferOccurSum.setClassName(scmInvMoveBillEntry.getGroupName());
                                scmCostTransferOccurSum.setItemId(scmInvMoveBillEntry.getItemId());
                                scmCostTransferOccurSum.setItemNo(scmInvMoveBillEntry.getItemNo());
                                scmCostTransferOccurSum.setItemName(scmInvMoveBillEntry.getItemName());
                                scmCostTransferOccurSum.setSpec(scmInvMoveBillEntry.getSpec());
                                scmCostTransferOccurSum.setUnit(scmInvMoveBillEntry.getUnit());
                                scmCostTransferOccurSum.setUnitName(scmInvMoveBillEntry.getUnitName());
                                scmCostTransferOccurSum.setCostOrgUnitName(map.get(scmInvMoveBillEntry.getInCstOrgUnitNo()));
                                scmCostTransferOccurSum.setQty(scmCostTransferOccurSum.getQty().add(scmInvMoveBillEntry.getQty()));
                                scmCostTransferOccurSum.setAmt(scmCostTransferOccurSum.getAmt().add(scmInvMoveBillEntry.getAmt()));
                                scmCostTransferOccurSum.setTaxAmt(scmCostTransferOccurSum.getTaxAmt().add(scmInvMoveBillEntry.getTaxAmt()));
								scmCostTransferOccurSum.setTax(scmCostTransferOccurSum.getTaxAmt().subtract(scmCostTransferOccurSum.getAmt()));
                                scmCostTransferOccurSum.setIntervaltype("以领代耗");
                                scmCostTransferOccurSumList.add(scmCostTransferOccurSum);
	                        }
	                    }
	                }
	                if (scmInvMoveBillEntryListOut != null && scmInvMoveBillEntryListOut.size() > 0) {
	                    if (StringUtils.equals("Y", moveOut)) {
	                        intervalBizType.add(index, "成本转移单出");
	                        index = index + 1;
	                        for (ScmInvMoveBillEntry2 scmInvMoveBillEntry : scmInvMoveBillEntryListOut) {
                        		ScmNewCostTransferOccurSum scmCostTransferOccurSum = new ScmNewCostTransferOccurSum(true);
                        		scmCostTransferOccurSum.setClassCode(scmInvMoveBillEntry.getGroupCode());
                                scmCostTransferOccurSum.setClassName(scmInvMoveBillEntry.getGroupName());
                                scmCostTransferOccurSum.setItemId(scmInvMoveBillEntry.getItemId());
                                scmCostTransferOccurSum.setItemNo(scmInvMoveBillEntry.getItemNo());
                                scmCostTransferOccurSum.setItemName(scmInvMoveBillEntry.getItemName());
                                scmCostTransferOccurSum.setSpec(scmInvMoveBillEntry.getSpec());
                                scmCostTransferOccurSum.setUnit(scmInvMoveBillEntry.getUnit());
                                scmCostTransferOccurSum.setUnitName(scmInvMoveBillEntry.getUnitName());
                                scmCostTransferOccurSum.setCostOrgUnitName(map.get(scmInvMoveBillEntry.getOutCstOrgUnitNo()));
                                scmCostTransferOccurSum.setQty(scmCostTransferOccurSum.getQty().add(scmInvMoveBillEntry.getQty()));
                                scmCostTransferOccurSum.setAmt(scmCostTransferOccurSum.getAmt().add(scmInvMoveBillEntry.getAmt()));
                                scmCostTransferOccurSum.setTaxAmt(scmCostTransferOccurSum.getTaxAmt().add(scmInvMoveBillEntry.getTaxAmt()));
								scmCostTransferOccurSum.setTax(scmCostTransferOccurSum.getTaxAmt().subtract(scmCostTransferOccurSum.getAmt()));	
                                scmCostTransferOccurSum.setIntervaltype("成本转移单出");
                                scmCostTransferOccurSumList.add(scmCostTransferOccurSum); 
	                        }
	                    }
	                }
	            }
	        }
	        //耗用单
	        page = new Page();
	        page.setModelClass(ScmInvCostConsumeEntry2.class);
	        page.setShowCount(Integer.MAX_VALUE);
	        if(StringUtils.isNotBlank(sbMaterilaClass.toString())) {
	            page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmMaterialGroup.class)+"."+ScmMaterialGroup.FN_ID, 
	                    new QueryParam(ClassUtils.getFinalModelSimpleName(ScmMaterialGroup.class)+"."+ScmMaterialGroup.FN_ID, QueryParam.QUERY_IN, sbMaterilaClass.toString()));
	        }
	        if(StringUtils.isNotBlank(sbMaterila.toString())) {
	            page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvCostConsumeEntry2.class)+"."+ScmInvCostConsumeEntry2.FN_ITEMID, 
	                    new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvCostConsumeEntry2.class)+"."+ScmInvCostConsumeEntry2.FN_ITEMID, QueryParam.QUERY_IN, sbMaterila.toString()));
	        }
			page.getParam().put(
					ClassUtils.getFinalModelSimpleName(ScmInvCostConsume.class) + "." + ScmInvCostConsume.FN_GENERATE,
					new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvCostConsume.class) + "."
							+ ScmInvCostConsume.FN_GENERATE, QueryParam.QUERY_EQ, "0"));
	        page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvCostConsume.class)+"."+ScmInvCostConsume.FN_ORGUNITNO, 
	                new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvCostConsume.class)+"."+ScmInvCostConsume.FN_ORGUNITNO, QueryParam.QUERY_IN, costOrgUnitNos.toString()));
	        page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvCostConsume.class)+"."+ScmInvCostConsume.FN_BIZDATE, 
	                new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvCostConsume.class)+"."+ScmInvCostConsume.FN_BIZDATE, QueryParam.QUERY_BETWEEN, beginDate,endDate));
	        if (!StringUtils.equals("Y", status)) {
	        	page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvCostConsume.class)+"."+ScmInvCostConsume.FN_STATUS, 
		                new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvCostConsume.class)+"."+ScmInvCostConsume.FN_STATUS, QueryParam.QUERY_EQ, "E"));
			}else{
				page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvCostConsume.class)+"."+ScmInvCostConsume.FN_STATUS, 
		                new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvCostConsume.class)+"."+ScmInvCostConsume.FN_STATUS, QueryParam.QUERY_NE, "N"));
			}
	        if (StringUtils.equals("Y", costConsume)) {
	            List<ScmInvCostConsumeEntry2> scmInvCostConsumeEntryList = scmInvCostConsumeEntryBiz.queryPage(page, arglist, "findAllPage", param);
	            if(scmInvCostConsumeEntryList!=null && !scmInvCostConsumeEntryList.isEmpty()) {
	                intervalBizType.add(index, "耗用单");
	                index = index + 1;
	                for (ScmInvCostConsumeEntry2 scmInvCostConsumeEntry : scmInvCostConsumeEntryList) {
	                		 	ScmNewCostTransferOccurSum scmCostTransferOccurSum = new ScmNewCostTransferOccurSum(true);
	                		 	scmCostTransferOccurSum.setClassCode(scmInvCostConsumeEntry.getGroupCode());
		                        scmCostTransferOccurSum.setClassName(scmInvCostConsumeEntry.getGroupName());
		                        scmCostTransferOccurSum.setItemId(scmInvCostConsumeEntry.getItemId());
		                        scmCostTransferOccurSum.setItemNo(scmInvCostConsumeEntry.getItemNo());
		                        scmCostTransferOccurSum.setItemName(scmInvCostConsumeEntry.getItemName());
		                        scmCostTransferOccurSum.setSpec(scmInvCostConsumeEntry.getSpec());
		                        scmCostTransferOccurSum.setUnit(scmInvCostConsumeEntry.getUnit());
		                        scmCostTransferOccurSum.setUnitName(scmInvCostConsumeEntry.getUnitName());
		                        scmCostTransferOccurSum.setCostOrgUnitName(map.get(scmInvCostConsumeEntry.getCostOrgUnitNo()));
		                        scmCostTransferOccurSum.setQty(scmCostTransferOccurSum.getQty().add(scmInvCostConsumeEntry.getQty()));
		                        scmCostTransferOccurSum.setAmt(scmCostTransferOccurSum.getAmt().add(scmInvCostConsumeEntry.getAmt()));
		                        scmCostTransferOccurSum.setTaxAmt(scmCostTransferOccurSum.getTaxAmt().add(scmInvCostConsumeEntry.getTaxAmt())); 
								scmCostTransferOccurSum.setTax(scmCostTransferOccurSum.getTaxAmt().subtract(scmCostTransferOccurSum.getAmt()));
		                        scmCostTransferOccurSum.setIntervaltype("耗用单");
		                        scmCostTransferOccurSumList.add(scmCostTransferOccurSum);
	                }
	            }
	        }
	        //盘存
	        page = new Page();
	        page.setModelClass(ScmInvCountingCostCenterEntry2.class);
	        page.setShowCount(Integer.MAX_VALUE);
	        if(StringUtils.isNotBlank(sbMaterilaClass.toString())) {
	            page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmMaterialGroup.class)+"."+ScmMaterialGroup.FN_ID, 
	                    new QueryParam(ClassUtils.getFinalModelSimpleName(ScmMaterialGroup.class)+"."+ScmMaterialGroup.FN_ID, QueryParam.QUERY_IN, sbMaterilaClass.toString()));
	        }
	        if(StringUtils.isNotBlank(sbMaterila.toString())) {
	            page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvCountingCostCenterEntry2.class)+"."+ScmInvCountingCostCenterEntry2.FN_ITEMID, 
	                    new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvCountingCostCenterEntry2.class)+"."+ScmInvCountingCostCenterEntry2.FN_ITEMID, QueryParam.QUERY_IN, sbMaterila.toString()));
	        }
	        page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvCountingCostCenter.class)+"."+ScmInvCountingCostCenter.FN_ORGUNITNO, 
	                new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvCountingCostCenter.class)+"."+ScmInvCountingCostCenter.FN_ORGUNITNO, QueryParam.QUERY_IN, costOrgUnitNos.toString()));
	        page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvCountingTask.class)+"."+ScmInvCountingTask.FN_BIZDATE, 
	                new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvCountingTask.class)+"."+ScmInvCountingTask.FN_BIZDATE, QueryParam.QUERY_BETWEEN, beginDate,endDate));
	        page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvCountingCostCenterEntry2.class)+"."+ScmInvCountingCostCenterEntry2.FN_DIFFERQTY, 
	                new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvCountingCostCenterEntry2.class)+"."+ScmInvCountingCostCenterEntry2.FN_DIFFERQTY, QueryParam.QUERY_NE, "0"));
	        if (!StringUtils.equals("Y", status)) {
	        	 page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvCountingTask.class)+"."+ScmInvCountingTask.FN_STATUS, 
	 	                new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvCountingTask.class)+"."+ScmInvCountingTask.FN_STATUS, QueryParam.QUERY_EQ, "O"));
			}else{
				 page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvCountingTask.class)+"."+ScmInvCountingTask.FN_STATUS, 
			                new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvCountingTask.class)+"."+ScmInvCountingTask.FN_STATUS, QueryParam.QUERY_IN, "'T','B','O','E'"));
			}
	        if (StringUtils.equals("Y", counting)) {
	            List<ScmInvCountingCostCenterEntry2> scmInvCountingCostCenterEntryList = scmInvCountingCostCenterEntryBiz.queryPage(page, arglist, "findAllPage", param);
	            if(scmInvCountingCostCenterEntryList!=null && !scmInvCountingCostCenterEntryList.isEmpty()) {
	                intervalBizType.add(index, "盘存(耗用)");
	                index = index + 1;
	                for (ScmInvCountingCostCenterEntry2 scmInvCountingCostCenterEntry : scmInvCountingCostCenterEntryList) {
	                		ScmNewCostTransferOccurSum scmCostTransferOccurSum = new ScmNewCostTransferOccurSum(true);
	                		scmCostTransferOccurSum.setClassCode(scmInvCountingCostCenterEntry.getGroupCode());
	                        scmCostTransferOccurSum.setClassName(scmInvCountingCostCenterEntry.getGroupName());
	                        scmCostTransferOccurSum.setItemId(scmInvCountingCostCenterEntry.getItemId());
	                        scmCostTransferOccurSum.setItemNo(scmInvCountingCostCenterEntry.getItemNo());
	                        scmCostTransferOccurSum.setItemName(scmInvCountingCostCenterEntry.getItemName());
	                        scmCostTransferOccurSum.setSpec(scmInvCountingCostCenterEntry.getSpec());
	                        scmCostTransferOccurSum.setUnit(scmInvCountingCostCenterEntry.getUnit());
	                        scmCostTransferOccurSum.setUnitName(scmInvCountingCostCenterEntry.getUnitName());
	                        scmCostTransferOccurSum.setCostOrgUnitName(map.get(scmInvCountingCostCenterEntry.getOrgUnitNo()));
	                        scmCostTransferOccurSum.setQty(scmCostTransferOccurSum.getQty().add((new BigDecimal("-1")).multiply(scmInvCountingCostCenterEntry.getDifferQty())));
	                        scmCostTransferOccurSum.setAmt(scmCostTransferOccurSum.getAmt().add((new BigDecimal("-1")).multiply(scmInvCountingCostCenterEntry.getDifferAmt())));
	                        scmCostTransferOccurSum.setTaxAmt(scmCostTransferOccurSum.getTaxAmt().add((new BigDecimal("-1")).multiply(scmInvCountingCostCenterEntry.getDifferTaxAmt())));
							scmCostTransferOccurSum.setTax(scmCostTransferOccurSum.getTaxAmt().subtract(scmCostTransferOccurSum.getAmt()));
	                        scmCostTransferOccurSum.setIntervaltype( "盘存(耗用)");
	                        scmCostTransferOccurSumList.add(scmCostTransferOccurSum);
	                }
				}
	        }
	        //报损单
	        page = new Page();
	        page.setModelClass(ScmCstFrmLossEntry2.class);
	        page.setShowCount(Integer.MAX_VALUE);
	        if(StringUtils.isNotBlank(materialClassName)) {
	            page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmMaterialGroup.class)+"."+ScmMaterialGroup.FN_CLASSCODE, 
	                    new QueryParam(ClassUtils.getFinalModelSimpleName(ScmMaterialGroup.class)+"."+ScmMaterialGroup.FN_CLASSCODE, QueryParam.QUERY_EQ, materialClassName));
	        }
	        if(StringUtils.isNotBlank(materialName)) {
	            page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmCstFrmLossEntry2.class)+"."+ScmCstFrmLossEntry2.FN_ITEMID, 
	                    new QueryParam(ClassUtils.getFinalModelSimpleName(ScmCstFrmLossEntry2.class)+"."+ScmCstFrmLossEntry2.FN_ITEMID, QueryParam.QUERY_EQ, materialName));
	        }
	        page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmCstFrmLoss.class)+"."+ScmCstFrmLoss.FN_ORGUNITNO, 
	                new QueryParam(ClassUtils.getFinalModelSimpleName(ScmCstFrmLoss.class)+"."+ScmCstFrmLoss.FN_ORGUNITNO, QueryParam.QUERY_IN, costOrgUnitNos.toString()));
	        page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmCstFrmLoss.class)+"."+ScmCstFrmLoss.FN_BIZDATE, 
	                new QueryParam(ClassUtils.getFinalModelSimpleName(ScmCstFrmLoss.class)+"."+ScmCstFrmLoss.FN_BIZDATE, QueryParam.QUERY_BETWEEN, beginDate,endDate));
	        if (!StringUtils.equals("Y", status)) {
	        	page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmCstFrmLoss.class)+"."+ScmCstFrmLoss.FN_STATUS, 
		                new QueryParam(ClassUtils.getFinalModelSimpleName(ScmCstFrmLoss.class)+"."+ScmCstFrmLoss.FN_STATUS, QueryParam.QUERY_EQ, "E"));
			}else{
				page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmCstFrmLoss.class)+"."+ScmCstFrmLoss.FN_STATUS, 
		                new QueryParam(ClassUtils.getFinalModelSimpleName(ScmCstFrmLoss.class)+"."+ScmCstFrmLoss.FN_STATUS, QueryParam.QUERY_NE, "N"));
			}
	        if (StringUtils.equals("Y", frmLoss)) {
	            List<ScmCstFrmLossEntry2> scmCstFrmLossEntryList = scmCstFrmLossEntryBiz.queryPage(page, arglist, "findAllPage", param);
	            if(scmCstFrmLossEntryList!=null && !scmCstFrmLossEntryList.isEmpty()) {
	                intervalBizType.add(index, "报损单");
	                index = index + 1;
	                for (ScmCstFrmLossEntry2 scmCstFrmLossEntry : scmCstFrmLossEntryList) {
	                		ScmNewCostTransferOccurSum scmCostTransferOccurSum = new ScmNewCostTransferOccurSum(true);
	                		scmCostTransferOccurSum.setClassCode(scmCstFrmLossEntry.getGroupCode());
	                        scmCostTransferOccurSum.setClassName(scmCstFrmLossEntry.getGroupName());
	                        scmCostTransferOccurSum.setItemId(scmCstFrmLossEntry.getItemId());
	                        scmCostTransferOccurSum.setItemNo(scmCstFrmLossEntry.getItemNo());
	                        scmCostTransferOccurSum.setItemName(scmCstFrmLossEntry.getItemName());
	                        scmCostTransferOccurSum.setSpec(scmCstFrmLossEntry.getSpec());
	                        scmCostTransferOccurSum.setUnit(scmCstFrmLossEntry.getUnit());
	                        scmCostTransferOccurSum.setUnitName(scmCstFrmLossEntry.getUnitName());
	                        scmCostTransferOccurSum.setCostOrgUnitName(map.get(scmCstFrmLossEntry.getCostOrgUnitNo()));
	                        scmCostTransferOccurSum.setQty(scmCostTransferOccurSum.getQty().add(scmCstFrmLossEntry.getQty()));
	                        scmCostTransferOccurSum.setAmt(scmCostTransferOccurSum.getAmt().add(scmCstFrmLossEntry.getAmt()));
	                        scmCostTransferOccurSum.setTaxAmt(scmCostTransferOccurSum.getTaxAmt().add(scmCstFrmLossEntry.getTaxAmt())); 
	                        scmCostTransferOccurSum.setTax(scmCostTransferOccurSum.getTaxAmt().subtract(scmCostTransferOccurSum.getAmt()));
	                        scmCostTransferOccurSum.setIntervaltype("报损单");
	                        scmCostTransferOccurSumList.add(scmCostTransferOccurSum);
	                }
	            }
	        }
	        if (scmCostTransferOccurSumList != null && scmCostTransferOccurSumList.size() > 0) {
	        	HashMap<String, Object> groupMap = new HashMap<String, Object>();
	        	HashMap<String, Object> groupMap2 = new HashMap<String, Object>();
	        	HashMap<String,Object> cacheMap = new HashMap<String,Object>();
	            for (ScmNewCostTransferOccurSum scmCostTransferOccurSum : scmCostTransferOccurSumList) {
	                if(scmCostTransferOccurSum.getItemId()>0) {
	                	ScmMaterial2 scmMaterial = (ScmMaterial2) cacheMap.get(ClassUtils.getFinalModelSimpleName(ScmMaterial2.class)+"_"+scmCostTransferOccurSum.getItemId());
						if(scmMaterial==null){
							scmMaterial = scmMaterialBiz.selectByItemId(scmCostTransferOccurSum.getItemId(), param);
							cacheMap.put(ClassUtils.getFinalModelSimpleName(ScmMaterial2.class)+"_"+scmCostTransferOccurSum.getItemId(),scmMaterial);
						}
	                    if(scmMaterial!=null) {
	                        if(scmMaterial.getGroupId()>0) {
	                        	ScmMaterialGroup scmMaterialGroup = (ScmMaterialGroup) cacheMap.get(ClassUtils.getFinalModelSimpleName(ScmMaterialGroup.class)+"_"+scmMaterial.getGroupId());
	                        	ScmMaterialGroup scmMaterialGroup2 = (ScmMaterialGroup) cacheMap.get(ClassUtils.getFinalModelSimpleName(ScmMaterialGroup.class)+"_"+scmMaterial.getGroupId());
	                        	
	    						if(scmMaterialGroup==null){
	    							scmMaterialGroup = scmMaterialGroupBiz.selectDirect(scmMaterial.getGroupId(), param);
	    							scmMaterialGroup2 = scmMaterialGroupBiz.selectDirect(scmMaterial.getGroupId(), param);
	    							cacheMap.put(ClassUtils.getFinalModelSimpleName(ScmMaterialGroup.class)+"_"+scmMaterial.getGroupId(),scmMaterialGroup);
	    							cacheMap.put(ClassUtils.getFinalModelSimpleName(ScmMaterialGroup.class)+"_"+scmMaterial.getGroupId(),scmMaterialGroup2);
	    						}
	                            if(scmMaterialGroup!=null) {
	                                if(StringUtils.isNotBlank(scmMaterialGroup.getLongNo())) {
	                                	if(!groupMap.containsKey(scmMaterialGroup.getLongNo())) {
		                                    String[] ids = StringUtils.split(scmMaterialGroup.getLongNo(),",");
		                                    if(Long.valueOf(ids[0])!=scmMaterial.getGroupId()) {
		                                    	ScmMaterialGroup tempScmMaterialGroup = scmMaterialGroupBiz.selectDirect(Long.valueOf(ids[0]), param);
		                                        if(tempScmMaterialGroup!=null)
		                                        	groupMap.put(scmMaterialGroup.getLongNo(), tempScmMaterialGroup);
		                                        	groupMap.put(tempScmMaterialGroup.getLongNo(), tempScmMaterialGroup);
		                                    }
	                                	}
	                                	scmMaterialGroup = (ScmMaterialGroup) groupMap.get(scmMaterialGroup.getLongNo());
	                                	if(scmMaterialGroup!=null)
	                                		scmCostTransferOccurSum.setClassName(scmMaterialGroup.getClassName());
	                                }
	                                
	                                //查找二级分类
	                                if(StringUtils.isNotBlank(scmMaterialGroup2.getLongNo())) {
	                                	String[] ids = StringUtils.split(scmMaterialGroup2.getLongNo(),",");
	                                	if (ids.length >= 2 && StringUtils.isNotBlank(ids[1]) && !groupMap2.containsKey(scmMaterialGroup2.getLongNo())) {
			                                page = new Page();
			                                page.setModelClass(ScmMaterialGroup.class);
			                    	        page.setShowCount(Integer.MAX_VALUE);
			                    	        page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmMaterialGroup.class)+"."+ScmMaterialGroup.FN_CONTROLUNITNO, 
			                    	                new QueryParam(ClassUtils.getFinalModelSimpleName(ScmMaterialGroup.class)+"."+ScmMaterialGroup.FN_CONTROLUNITNO, QueryParam.QUERY_EQ, currentControlUnitNo));
			                    	        page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmMaterialGroup.class)+"."+ScmMaterialGroup.FN_LONGNO, 
			                    	                new QueryParam(ClassUtils.getFinalModelSimpleName(ScmMaterialGroup.class)+"."+ScmMaterialGroup.FN_LONGNO, QueryParam.QUERY_EQ, ids[0] + "," + ids[1] + ","));
			                        		List<ScmMaterialGroup> scmMaterialGroupList = scmMaterialGroupBiz.queryPage(page,
			                        				arglist, "findAllPage", param);
			                        		if (scmMaterialGroupList != null && scmMaterialGroupList.size() > 0) {
			                        			ScmMaterialGroup tempScmMaterialGroup2 = scmMaterialGroupList.get(0);
			                        			groupMap2.put(scmMaterialGroup2.getLongNo(), tempScmMaterialGroup2);
			                        		}
	                                	}
	                                	scmMaterialGroup2 = (ScmMaterialGroup) groupMap2.get(scmMaterialGroup2.getLongNo());
	                                	if(scmMaterialGroup2!=null)
	                                		scmCostTransferOccurSum.setClassNameTwo(scmMaterialGroup2.getClassName());
	                                } 
	                            }
	                        }
	                    }
	                }
	            }
	        }
//	        添加排序
	        HashMap<String, ArrayList<String>> sortName = new HashMap<String,  ArrayList<String>>();
	        sortName.put("costOrg", new ArrayList<String>(Arrays.asList("costOrgUnitName")));
	        sortName.put("itemClass", new ArrayList<String>(Arrays.asList("classCode","itemNo")));
	        ArrayList<String> sortNameList = new ArrayList<String>();
	        sortNameList.addAll(sortName.get(summary1));
	        if(summary2!=null && !summary2.equals(summary1)) {
	        	sortNameList.addAll(sortName.get(summary2));
	        }
	        //数据排序
	        String fields[]=(String[])sortNameList.toArray(new String[sortNameList.size()]);
	        String sorts[]={"ASC","DESC","ASC"};
	        
	        scmCostTransferOccurSumList = (List<ScmNewCostTransferOccurSum>)ListSortUtil.sort(scmCostTransferOccurSumList, fields, sorts);
	        // TODO 结束
	        log.info(System.currentTimeMillis()-begin+"-----总耗时；记录数"+scmCostTransferOccurSumList.size());
	        return scmCostTransferOccurSumList;
	}


	@Override
	public List<ScmNewCostTransferOccurDetail> selectScmCostTransferOccurDetail(HttpServletRequest request) throws AppException {
		long begin = System.currentTimeMillis();
		List<ScmNewCostTransferOccurDetail> scmCostTransferOccurSumList = new ArrayList<ScmNewCostTransferOccurDetail>();
        String currentOrgUnitNo=request.getParameter("orgUnitNo");
        String currentControlUnitNo=request.getParameter("controlUnitNo");
        String costOrgUnitNo=request.getParameter("costOrgUnitNo");
        String beginDate=request.getParameter("beginDate");
        String endDate=request.getParameter("endDate");
        String materialClassName=request.getParameter("materialClassName");
        String materialName=request.getParameter("materialName");
        String purInwarehs="1".equals(request.getParameter("purInwarehs"))?"Y":"N";
        String purInwarehsReturn="1".equals(request.getParameter("purInwarehsReturn"))?"Y":"N";
        String materialReq="1".equals(request.getParameter("materialReq"))?"Y":"N";
        String materialReqReturn="1".equals(request.getParameter("materialReqReturn"))?"Y":"N";
        String moveIn="1".equals(request.getParameter("moveIn"))?"Y":"N";
        String moveOut="1".equals(request.getParameter("moveOut"))?"Y":"N";
        String costConsume="1".equals(request.getParameter("costConsume"))?"Y":"N";
        String costConsume2="1".equals(request.getParameter("costConsume2"))?"Y":"N";
        String counting="1".equals(request.getParameter("counting"))?"Y":"N";
        String frmLoss="1".equals(request.getParameter("frmLoss"))?"Y":"N";
        String InvSaleOut="1".equals(request.getParameter("InvSaleOut"))?"Y":"N";
        String InvSaleIn="1".equals(request.getParameter("InvSaleIn"))?"Y":"N";
        String status = "1".equals(request.getParameter("status")) ? "Y" : "N";
        if(StringUtils.isBlank(beginDate) || StringUtils.isBlank(endDate))
            return scmCostTransferOccurSumList;
        if(StringUtils.isBlank(costOrgUnitNo))
            costOrgUnitNo = currentOrgUnitNo;
        Param param = new Param();
        param.setOrgUnitNo(currentOrgUnitNo);
        param.setControlUnitNo(currentControlUnitNo);
        StringBuffer costOrgUnitNos = new StringBuffer("");
        HashMap<String,String> map=new HashMap<>();
        HashMap<String,OrgCostCenter2> costMap=new HashMap<>();
		if (StringUtils.isNotBlank(costOrgUnitNo)) {
			String[] orgUnitNoList = StringUtils.split(costOrgUnitNo, ",");
			for (String org : orgUnitNoList) {
				if (StringUtils.isBlank(org))
					continue;
				List<OrgCostCenter2> orgCostCenterList = orgCostCenterBiz.findChild(org, param);
				if (orgCostCenterList != null && !orgCostCenterList.isEmpty()) {
					for (OrgCostCenter2 orgCostCenter : orgCostCenterList) {
						map.put(orgCostCenter.getOrgUnitNo(), orgCostCenter.getOrgUnitName());
						costMap.put(orgCostCenter.getOrgUnitNo(), orgCostCenter);
						if (StringUtils.isNotBlank(costOrgUnitNos.toString()))
							costOrgUnitNos.append(",");
						costOrgUnitNos.append("'").append(orgCostCenter.getOrgUnitNo()).append("'");
					}
				}
			}
		}
        StringBuffer sbMaterilaClass = new StringBuffer("");
        if (StringUtils.isNotBlank(materialClassName)) {
			String[] materialClassNameList = StringUtils.split(materialClassName, ",");
            for(String materialClass:materialClassNameList) {
                if(StringUtils.isBlank(materialClass)) continue;
				int materialClassId = Integer.parseInt(materialClass);
				List<ScmMaterialGroup> scmMaterialGroupList = scmMaterialGroupBiz.findChild(materialClassId, param);
				if (scmMaterialGroupList != null && scmMaterialGroupList.size() > 0) {
					for(ScmMaterialGroup scmMaterialGroup:scmMaterialGroupList) {
						if(StringUtils.isNotBlank(sbMaterilaClass.toString()))
							sbMaterilaClass.append(",");
						sbMaterilaClass.append(String.valueOf(scmMaterialGroup.getId()));
					}
				}
			}
        }
        StringBuffer sbMaterila = new StringBuffer("");
        if (StringUtils.isNotBlank(materialName)) {
        	String[] materialNameList = StringUtils.split(materialName, ",");
            for(String material:materialNameList) {
                if(StringUtils.isBlank(material)) continue;
				int materialId = Integer.parseInt(material);
				if(StringUtils.isNotBlank(sbMaterila.toString()))
					sbMaterila.append(",");
				sbMaterila.append(String.valueOf(materialId));
            }
        }
        Set<Map.Entry<String,String>> entrySet = map.entrySet();
        //入库单
        Page page = new Page();
        page.setModelClass(ScmInvPurInWarehsBillEntry2.class);
        page.setShowCount(Integer.MAX_VALUE);
        if(StringUtils.isNotBlank(sbMaterilaClass.toString())) {
            page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmMaterialGroup.class)+"."+ScmMaterialGroup.FN_ID, 
                    new QueryParam(ClassUtils.getFinalModelSimpleName(ScmMaterialGroup.class)+"."+ScmMaterialGroup.FN_ID, QueryParam.QUERY_IN, sbMaterilaClass.toString()));
        }
        if(StringUtils.isNotBlank(sbMaterila.toString())) {
            page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBillEntry2.class)+"."+ScmInvPurInWarehsBillEntry2.FN_ITEMID, 
                    new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBillEntry2.class)+"."+ScmInvPurInWarehsBillEntry2.FN_ITEMID, QueryParam.QUERY_IN, sbMaterila.toString()));
        }
        page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBillEntry2.class)+"."+ScmInvPurInWarehsBillEntry2.FN_COSTORGUNITNO, 
                new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBillEntry2.class)+"."+ScmInvPurInWarehsBillEntry2.FN_COSTORGUNITNO, QueryParam.QUERY_IN, costOrgUnitNos.toString()));
        page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBill2.class)+"."+ScmInvPurInWarehsBill2.FN_BIZDATE, 
                new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBill2.class)+"."+ScmInvPurInWarehsBill2.FN_BIZDATE, QueryParam.QUERY_BETWEEN, beginDate,endDate));
        if (!StringUtils.equals("Y", status)) {
        	page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBill2.class)+"."+ScmInvPurInWarehsBill2.FN_STATUS, 
	                new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBill2.class)+"."+ScmInvPurInWarehsBill2.FN_STATUS, QueryParam.QUERY_EQ, "E"));
		}else{
			page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBill2.class)+"."+ScmInvPurInWarehsBill2.FN_STATUS, 
	                new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBill2.class)+"."+ScmInvPurInWarehsBill2.FN_STATUS, QueryParam.QUERY_NE, "N"));
		}
        if (StringUtils.equals("Y", purInwarehs) && !StringUtils.equals("Y", purInwarehsReturn)) {
            page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBill2.class)+"."+ScmInvPurInWarehsBill2.FN_BIZTYPE, 
                    new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBill2.class)+"."+ScmInvPurInWarehsBill2.FN_BIZTYPE, QueryParam.QUERY_IN, "1,2,3,4,5"));
        }
        if (!StringUtils.equals("Y", purInwarehs) && StringUtils.equals("Y", purInwarehsReturn)) {
            page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBill2.class)+"."+ScmInvPurInWarehsBill2.FN_BIZTYPE, 
                    new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBill2.class)+"."+ScmInvPurInWarehsBill2.FN_BIZTYPE, QueryParam.QUERY_IN, "6,7,8,9,10"));
        }
        List<String> arglist = new ArrayList<>();
        arglist.add("controlUnitNo="+currentControlUnitNo);
        if (StringUtils.equals("Y", purInwarehs) || StringUtils.equals("Y", purInwarehsReturn) || StringUtils.equals("Y", costConsume2)) {
            List<ScmInvPurInWarehsBillEntry2> scmInvPurInWarehsBillEntryList = scmInvPurInWarehsBillEntryBiz.queryPage(page, arglist, "findAllPage", param);
            if(scmInvPurInWarehsBillEntryList!=null && !scmInvPurInWarehsBillEntryList.isEmpty()) {
                List<ScmInvPurInWarehsBillEntry2> scmInvPurInWarehsBillEntryListIn = new ArrayList<ScmInvPurInWarehsBillEntry2>();
                List<ScmInvPurInWarehsBillEntry2> scmInvPurInWarehsBillEntryListReturn = new ArrayList<ScmInvPurInWarehsBillEntry2>();
                String[] bizTypeIn={"1","2","3","4","5"};
                List<String> bizTypeListIn = Arrays.asList(bizTypeIn);
                String[] bizTypeReturn={"6","7","8","9","10"};
                List<String> bizTypeListReturn = Arrays.asList(bizTypeReturn);
                for (ScmInvPurInWarehsBillEntry2 scmInvPurInWarehsBillEntry : scmInvPurInWarehsBillEntryList) {
                    if (bizTypeListIn.contains(scmInvPurInWarehsBillEntry.getBizType())) {
                        scmInvPurInWarehsBillEntryListIn.add(scmInvPurInWarehsBillEntry);
                    } else if (bizTypeListReturn.contains(scmInvPurInWarehsBillEntry.getBizType())) {
                        scmInvPurInWarehsBillEntryListReturn.add(scmInvPurInWarehsBillEntry);
                    }
                }
                if (scmInvPurInWarehsBillEntryListIn != null && scmInvPurInWarehsBillEntryListIn.size() > 0) {
                    // 采购入库
                    if (StringUtils.equals("Y", purInwarehs)) {
                        for (ScmInvPurInWarehsBillEntry2 scmInvPurInWarehsBillEntry : scmInvPurInWarehsBillEntryListIn) {
                        	ScmNewCostTransferOccurDetail scmCostTransferOccurSum = new ScmNewCostTransferOccurDetail(true);
                        	scmCostTransferOccurSum.setBillNo(scmInvPurInWarehsBillEntry.getWrNo());
                            scmCostTransferOccurSum.setUnitName(scmInvPurInWarehsBillEntry.getUnitName());
                            OrgBaseUnit2 orgBaseUnit = orgUnitBiz.selectbyOrgNo(scmInvPurInWarehsBillEntry.getUseOrgUnitNo(), param);
        					if (orgBaseUnit != null) {
        						scmCostTransferOccurSum.setUseOrgUnitName(orgBaseUnit.getOrgUnitName());
        					} else {
        						scmCostTransferOccurSum.setUseOrgUnitName(scmCostTransferOccurSum.getUseOrgUnitNo());
        					}
        					Code code = codeBiz.selectByCategoryAndCode("warehouseStatus",
        							scmInvPurInWarehsBillEntry.getStatus());
        					if (code != null) {
        						scmCostTransferOccurSum.setStatusName(code.getName());
        					} else {
        						scmCostTransferOccurSum.setStatusName(scmInvPurInWarehsBillEntry.getStatus());
        					}
                            scmCostTransferOccurSum.setBizDate(scmInvPurInWarehsBillEntry.getBizDate());
                            
                            code = codeBiz.selectByCategoryAndCode("wareHouseBizType", scmInvPurInWarehsBillEntry.getBizType());
        					if (code != null) {
        						scmCostTransferOccurSum.setBizTypeName(code.getName());
        					} else {
        						scmCostTransferOccurSum.setBizTypeName(scmInvPurInWarehsBillEntry.getBizType());
        					}
                            scmCostTransferOccurSum.setLot(scmInvPurInWarehsBillEntry.getLot());
                            scmCostTransferOccurSum.setVendorName(scmInvPurInWarehsBillEntry.getVendorName());
                            scmCostTransferOccurSum.setCostOrgUnitName(map.get(scmInvPurInWarehsBillEntry.getCostOrgUnitNo()));
                            scmCostTransferOccurSum.setAddInQty(scmCostTransferOccurSum.getAddInQty().add(scmInvPurInWarehsBillEntry.getQty()));
                            scmCostTransferOccurSum.setAddInAmt(scmCostTransferOccurSum.getAddInAmt().add(scmInvPurInWarehsBillEntry.getAmt()));
                            scmCostTransferOccurSum.setAddInTaxAmt(scmCostTransferOccurSum.getAddInTaxAmt().add(scmInvPurInWarehsBillEntry.getTaxAmt()));
							scmCostTransferOccurSum.setAddInTax(scmCostTransferOccurSum.getAddInTaxAmt().subtract(scmCostTransferOccurSum.getAddInAmt()));
                            scmCostTransferOccurSumList.add(scmCostTransferOccurSum);
                        }
                    }
                }
                if (scmInvPurInWarehsBillEntryListReturn != null && scmInvPurInWarehsBillEntryListReturn.size() > 0) {
                    // 采购入库（退货）
                    if (StringUtils.equals("Y", purInwarehsReturn)) {
                        for (ScmInvPurInWarehsBillEntry2 scmInvPurInWarehsBillEntry : scmInvPurInWarehsBillEntryListReturn) {
                    		 ScmNewCostTransferOccurDetail scmCostTransferOccurSum = new ScmNewCostTransferOccurDetail(true);
                    		 scmCostTransferOccurSum.setBillNo(scmInvPurInWarehsBillEntry.getWrNo());
                             OrgBaseUnit2 orgBaseUnit = orgUnitBiz.selectbyOrgNo(scmInvPurInWarehsBillEntry.getUseOrgUnitNo(), param);
         					if (orgBaseUnit != null) {
         						scmCostTransferOccurSum.setUseOrgUnitName(orgBaseUnit.getOrgUnitName());
         					} else {
         						scmCostTransferOccurSum.setUseOrgUnitName(scmCostTransferOccurSum.getUseOrgUnitNo());
         					}
	                         scmCostTransferOccurSum.setBizDate(scmInvPurInWarehsBillEntry.getBizDate());
							Code code = codeBiz.selectByCategoryAndCode("warehouseStatus",
									scmInvPurInWarehsBillEntry.getStatus());
							if (code != null) {
								scmCostTransferOccurSum.setStatusName(code.getName());
							} else {
								scmCostTransferOccurSum.setStatusName(scmInvPurInWarehsBillEntry.getStatus());
							}
                            code = codeBiz.selectByCategoryAndCode("wareHouseBizType", scmInvPurInWarehsBillEntry.getBizType());
        					if (code != null) {
        						scmCostTransferOccurSum.setBizTypeName(code.getName());
        					} else {
        						scmCostTransferOccurSum.setBizTypeName(scmInvPurInWarehsBillEntry.getBizType());
        					}
                             scmCostTransferOccurSum.setLot(scmInvPurInWarehsBillEntry.getLot());
                             scmCostTransferOccurSum.setVendorName(scmInvPurInWarehsBillEntry.getVendorName());
                             scmCostTransferOccurSum.setUnitName(scmInvPurInWarehsBillEntry.getUnitName());
                             scmCostTransferOccurSum.setCostOrgUnitName(map.get(scmInvPurInWarehsBillEntry.getCostOrgUnitNo()));
                             scmCostTransferOccurSum.setOutQty(scmCostTransferOccurSum.getOutQty().add(scmInvPurInWarehsBillEntry.getQty()));
                             scmCostTransferOccurSum.setOutAmt(scmCostTransferOccurSum.getOutAmt().add(scmInvPurInWarehsBillEntry.getAmt()));
                             scmCostTransferOccurSum.setOutTaxAmt(scmCostTransferOccurSum.getOutTaxAmt().add(scmInvPurInWarehsBillEntry.getTaxAmt()));
							 scmCostTransferOccurSum.setOutTax(scmCostTransferOccurSum.getOutTaxAmt().subtract(scmCostTransferOccurSum.getOutAmt()));
                             scmCostTransferOccurSumList.add(scmCostTransferOccurSum);
                        }
                    }
                }
            }    
        }
        //领料单
        page = new Page();
        page.setModelClass(ScmInvPurInWarehsBillEntry2.class);
        page.setShowCount(Integer.MAX_VALUE);
        if(StringUtils.isNotBlank(sbMaterilaClass.toString())) {
            page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmMaterialGroup.class)+"."+ScmMaterialGroup.FN_ID, 
                    new QueryParam(ClassUtils.getFinalModelSimpleName(ScmMaterialGroup.class)+"."+ScmMaterialGroup.FN_ID, QueryParam.QUERY_IN, sbMaterilaClass.toString()));
        }
        if(StringUtils.isNotBlank(sbMaterila.toString())) {
            page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBillEntry2.class)+"."+ScmInvPurInWarehsBillEntry2.FN_ITEMID, 
                    new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBillEntry2.class)+"."+ScmInvPurInWarehsBillEntry2.FN_ITEMID, QueryParam.QUERY_IN, sbMaterila.toString()));
        }
        page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBill.class)+"."+ScmInvMaterialReqBill.FN_COSTORGUNITNO, 
                new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBill.class)+"."+ScmInvMaterialReqBill.FN_COSTORGUNITNO, QueryParam.QUERY_IN, costOrgUnitNos.toString()));
        page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBill2.class)+"."+ScmInvMaterialReqBill2.FN_BIZDATE, 
                new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBill2.class)+"."+ScmInvMaterialReqBill2.FN_BIZDATE, QueryParam.QUERY_BETWEEN, beginDate,endDate));
        if (!StringUtils.equals("Y", status)) {
        	page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBill2.class)+"."+ScmInvMaterialReqBill2.FN_STATUS, 
	                new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBill2.class)+"."+ScmInvMaterialReqBill2.FN_STATUS, QueryParam.QUERY_EQ, "E"));
		}else{
			page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBill2.class)+"."+ScmInvMaterialReqBill2.FN_STATUS, 
	                new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBill2.class)+"."+ScmInvMaterialReqBill2.FN_STATUS, QueryParam.QUERY_NE, "N"));
		}
        if (StringUtils.equals("Y", materialReq) && !StringUtils.equals("Y", materialReqReturn)) {
            page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBill2.class)+"."+ScmInvMaterialReqBill2.FN_BIZTYPE, 
                    new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBill2.class)+"."+ScmInvMaterialReqBill2.FN_BIZTYPE, QueryParam.QUERY_EQ, "1"));
        }
        if (!StringUtils.equals("Y", materialReq) && StringUtils.equals("Y", materialReqReturn)) {
            page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBill2.class)+"."+ScmInvMaterialReqBill2.FN_BIZTYPE, 
                    new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBill2.class)+"."+ScmInvMaterialReqBill2.FN_BIZTYPE, QueryParam.QUERY_EQ, "2"));
        }
        if (StringUtils.equals("Y", materialReq) || StringUtils.equals("Y", materialReqReturn) || StringUtils.equals("Y", costConsume2)) {
            List<ScmInvMaterialReqBillEntry2> scmInvMaterialReqBillEntryList = scmInvMaterialReqBillEntryBiz.queryPage(page, arglist, "findAllPage", param);
            if(scmInvMaterialReqBillEntryList!=null && !scmInvMaterialReqBillEntryList.isEmpty()) {
                List<ScmInvMaterialReqBillEntry2> scmInvMaterialReqBillEntryListIn = new ArrayList<ScmInvMaterialReqBillEntry2>();
                List<ScmInvMaterialReqBillEntry2> scmInvMaterialReqBillEntryListReturn = new ArrayList<ScmInvMaterialReqBillEntry2>();
                for (ScmInvMaterialReqBillEntry2 scmInvMaterialReqBillEntry : scmInvMaterialReqBillEntryList) {
                    if (StringUtils.equals("1",scmInvMaterialReqBillEntry.getBizType())) {
                        scmInvMaterialReqBillEntryListIn.add(scmInvMaterialReqBillEntry);
                    } else if (StringUtils.equals("2",scmInvMaterialReqBillEntry.getBizType())) {
                        scmInvMaterialReqBillEntryListReturn.add(scmInvMaterialReqBillEntry);
                    }
                }
                if (scmInvMaterialReqBillEntryListIn != null && scmInvMaterialReqBillEntryListIn.size() > 0) {
                    // 领料出库
                    if (StringUtils.equals("Y", materialReq)) {
//                        intervalBizType.add(index, "领料出库");
                        for (ScmInvMaterialReqBillEntry2 scmInvMaterialReqBillEntry : scmInvMaterialReqBillEntryListIn) {
                    		ScmNewCostTransferOccurDetail scmCostTransferOccurSum = new ScmNewCostTransferOccurDetail(true);
                    		scmCostTransferOccurSum.setBillNo(scmInvMaterialReqBillEntry.getOtNo());
                            OrgBaseUnit2 orgBaseUnit = orgUnitBiz.selectbyOrgNo(scmInvMaterialReqBillEntry.getUseOrgUnitNo(), param);
        					if (orgBaseUnit != null) {
        						scmCostTransferOccurSum.setUseOrgUnitName(orgBaseUnit.getOrgUnitName());
        					} else {
        						scmCostTransferOccurSum.setUseOrgUnitName(scmCostTransferOccurSum.getUseOrgUnitNo());
        					}
        					Code code = codeBiz.selectByCategoryAndCode("warehouseStatus",
        							scmInvMaterialReqBillEntry.getStatus());
        					if (code != null) {
        						scmCostTransferOccurSum.setStatusName(code.getName());
        					} else {
        						scmCostTransferOccurSum.setStatusName(scmInvMaterialReqBillEntry.getStatus());
        					}
                            scmCostTransferOccurSum.setBizDate(scmInvMaterialReqBillEntry.getBizDate());
        					code = codeBiz.selectByCategoryAndCode("collectWHBizType", scmInvMaterialReqBillEntry.getBizType());
        					if (code != null) {
        						scmCostTransferOccurSum.setBizTypeName(code.getName());
        					} else {
        						scmCostTransferOccurSum.setBizTypeName(scmInvMaterialReqBillEntry.getBizType());
        					}
                            scmCostTransferOccurSum.setLot(scmInvMaterialReqBillEntry.getLot());
                            scmCostTransferOccurSum.setUnitName(scmInvMaterialReqBillEntry.getUnitName());
                            scmCostTransferOccurSum.setCostOrgUnitName(map.get(scmInvMaterialReqBillEntry.getCostOrgUnitNo()));
                            scmCostTransferOccurSum.setAddInQty(scmCostTransferOccurSum.getAddInQty().add(scmInvMaterialReqBillEntry.getQty()));
                            scmCostTransferOccurSum.setAddInAmt(scmCostTransferOccurSum.getAddInAmt().add(scmInvMaterialReqBillEntry.getAmt()));
                            scmCostTransferOccurSum.setAddInTaxAmt(scmCostTransferOccurSum.getAddInTaxAmt().add(scmInvMaterialReqBillEntry.getTaxAmt()));
							scmCostTransferOccurSum.setAddInTax(scmCostTransferOccurSum.getAddInTaxAmt().subtract(scmCostTransferOccurSum.getAddInAmt()));
                            scmCostTransferOccurSumList.add(scmCostTransferOccurSum);
                        }
                    }
                }
                if (scmInvMaterialReqBillEntryListReturn != null && scmInvMaterialReqBillEntryListReturn.size() > 0) {
                    // 领料退仓
                    if (StringUtils.equals("Y", materialReqReturn)) {
//                        intervalBizType.add(index, "领料退仓");
                        for (ScmInvMaterialReqBillEntry2 scmInvMaterialReqBillEntry : scmInvMaterialReqBillEntryListReturn) {
                		 	ScmNewCostTransferOccurDetail scmCostTransferOccurSum = new ScmNewCostTransferOccurDetail(true);
                		 	scmCostTransferOccurSum.setBillNo(scmInvMaterialReqBillEntry.getOtNo());
                            OrgBaseUnit2 orgBaseUnit = orgUnitBiz.selectbyOrgNo(scmInvMaterialReqBillEntry.getUseOrgUnitNo(), param);
        					if (orgBaseUnit != null) {
        						scmCostTransferOccurSum.setUseOrgUnitName(orgBaseUnit.getOrgUnitName());
        					} else {
        						scmCostTransferOccurSum.setUseOrgUnitName(scmCostTransferOccurSum.getUseOrgUnitNo());
        					}
        					Code code = codeBiz.selectByCategoryAndCode("warehouseStatus",
        							scmInvMaterialReqBillEntry.getStatus());
        					if (code != null) {
        						scmCostTransferOccurSum.setStatusName(code.getName());
        					} else {
        						scmCostTransferOccurSum.setStatusName(scmInvMaterialReqBillEntry.getStatus());
        					}
                            scmCostTransferOccurSum.setBizDate(scmInvMaterialReqBillEntry.getBizDate());
        					code = codeBiz.selectByCategoryAndCode("collectWHBizType", scmInvMaterialReqBillEntry.getBizType());
        					if (code != null) {
        						scmCostTransferOccurSum.setBizTypeName(code.getName());
        					} else {
        						scmCostTransferOccurSum.setBizTypeName(scmInvMaterialReqBillEntry.getBizType());
        					}
                            scmCostTransferOccurSum.setLot(scmInvMaterialReqBillEntry.getLot());
                            scmCostTransferOccurSum.setUnitName(scmInvMaterialReqBillEntry.getUnitName());
                            scmCostTransferOccurSum.setCostOrgUnitName(map.get(scmInvMaterialReqBillEntry.getCostOrgUnitNo()));
                            scmCostTransferOccurSum.setOutQty(scmCostTransferOccurSum.getOutQty().add(scmInvMaterialReqBillEntry.getQty()));
                            scmCostTransferOccurSum.setOutAmt(scmCostTransferOccurSum.getOutAmt().add(scmInvMaterialReqBillEntry.getAmt()));
                            scmCostTransferOccurSum.setOutTaxAmt(scmCostTransferOccurSum.getOutTaxAmt().add(scmInvMaterialReqBillEntry.getTaxAmt()));
							scmCostTransferOccurSum.setOutTax(scmCostTransferOccurSum.getOutTaxAmt().subtract(scmCostTransferOccurSum.getOutAmt()));
                            scmCostTransferOccurSumList.add(scmCostTransferOccurSum);
                        }
                    }
                }
            }
        }
        
  	//销售出库单
        page = new Page();
        page.setModelClass(ScmInvSaleIssueBillEntry2.class);
        page.setShowCount(Integer.MAX_VALUE);
        if(StringUtils.isNotBlank(sbMaterilaClass.toString())) {
            page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmMaterialGroup.class)+"."+ScmMaterialGroup.FN_ID, 
                    new QueryParam(ClassUtils.getFinalModelSimpleName(ScmMaterialGroup.class)+"."+ScmMaterialGroup.FN_ID, QueryParam.QUERY_IN, sbMaterilaClass.toString()));
        }
        if(StringUtils.isNotBlank(sbMaterila.toString())) {
            page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBillEntry2.class)+"."+ScmInvSaleIssueBillEntry2.FN_ITEMID, 
                    new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBillEntry2.class)+"."+ScmInvSaleIssueBillEntry2.FN_ITEMID, QueryParam.QUERY_IN, sbMaterila.toString()));
        }
        page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBillEntry2.class)+"."+ScmInvSaleIssueBillEntry2.FN_OUTCOSTORGUNITNO, 
                new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBillEntry2.class)+"."+ScmInvSaleIssueBillEntry2.FN_OUTCOSTORGUNITNO, QueryParam.QUERY_IN, costOrgUnitNos.toString()));
        page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBill.class)+"."+ScmInvSaleIssueBill.FN_BIZDATE, 
                new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBill.class)+"."+ScmInvSaleIssueBill.FN_BIZDATE, QueryParam.QUERY_BETWEEN, beginDate,endDate));
        if (!StringUtils.equals("Y", status)) {
        	page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBill.class)+"."+ScmInvSaleIssueBill.FN_STATUS, 
	                new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBill.class)+"."+ScmInvSaleIssueBill.FN_STATUS, QueryParam.QUERY_EQ, "E"));
		}else{
			page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBill.class)+"."+ScmInvSaleIssueBill.FN_STATUS, 
	                new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBill.class)+"."+ScmInvSaleIssueBill.FN_STATUS, QueryParam.QUERY_NE, "N"));
		}
        if (StringUtils.equals("Y", InvSaleOut) && !StringUtils.equals("Y", InvSaleIn)) {
            page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBill.class)+"."+ScmInvSaleIssueBill.FN_BIZTYPE, 
                    new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBill.class)+"."+ScmInvSaleIssueBill.FN_BIZTYPE, QueryParam.QUERY_IN, "1,2,3"));
        }
        if (!StringUtils.equals("Y", InvSaleOut) && StringUtils.equals("Y", InvSaleIn)) {
            page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBill.class)+"."+ScmInvSaleIssueBill.FN_BIZTYPE, 
                    new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBill.class)+"."+ScmInvSaleIssueBill.FN_BIZTYPE, QueryParam.QUERY_IN, "6,7,8"));
        }
        if (StringUtils.equals("Y", InvSaleOut) || StringUtils.equals("Y", InvSaleIn) || StringUtils.equals("Y", costConsume2)) {
            List<ScmInvSaleIssueBillEntry2> scmInvSaleIssueBillEntryList = scmInvSaleIssueBillEntryBiz.queryPage(page, arglist, "findAllPage", param);
            if(scmInvSaleIssueBillEntryList!=null && !scmInvSaleIssueBillEntryList.isEmpty()) {
                List<ScmInvSaleIssueBillEntry2> scmInvSaleIssueBillEntryListIn = new ArrayList<ScmInvSaleIssueBillEntry2>();
                List<ScmInvSaleIssueBillEntry2> scmInvSaleIssueBillEntryListOut = new ArrayList<ScmInvSaleIssueBillEntry2>();
                String[] bizTypeIn={"6","7","8"};
                List<String> bizTypeListIn = Arrays.asList(bizTypeIn);
                String[] bizTypeOut={"1","2","3"};
                List<String> bizTypeListOut = Arrays.asList(bizTypeOut);
                for (ScmInvSaleIssueBillEntry2 scmInvSaleIssueBillEntry : scmInvSaleIssueBillEntryList) {
                    if (bizTypeListIn.contains(scmInvSaleIssueBillEntry.getBizType())) {
                    	scmInvSaleIssueBillEntryListIn.add(scmInvSaleIssueBillEntry);
                    } else if (bizTypeListOut.contains(scmInvSaleIssueBillEntry.getBizType())) {
                    	scmInvSaleIssueBillEntryListOut.add(scmInvSaleIssueBillEntry);
                    }
                }
                if (scmInvSaleIssueBillEntryListIn != null && scmInvSaleIssueBillEntryListIn.size() > 0) {
                    // 销售退货
                    if (StringUtils.equals("Y", InvSaleIn)) {
//                        intervalBizType.add(index, "销售退货"); 结存加
                        for (ScmInvSaleIssueBillEntry2 scmInvSaleIssueBillEntry : scmInvSaleIssueBillEntryListIn) {
                    		ScmNewCostTransferOccurDetail scmCostTransferOccurSum = new ScmNewCostTransferOccurDetail(true);
                            OrgBaseUnit2 orgBaseUnit = orgUnitBiz.selectbyOrgNo(scmInvSaleIssueBillEntry.getOutOrgUnitNo(), param);
        					if (orgBaseUnit != null) {
        						scmCostTransferOccurSum.setUseOrgUnitName(orgBaseUnit.getOrgUnitName());
        					} else {
        						scmCostTransferOccurSum.setUseOrgUnitName(scmCostTransferOccurSum.getUseOrgUnitNo());
        					}
        					Code code = codeBiz.selectByCategoryAndCode("saleOWBizType", scmInvSaleIssueBillEntry.getBizType());
        					if (code != null) {
        						scmCostTransferOccurSum.setBizTypeName(code.getName());
        					} else {
        						scmCostTransferOccurSum.setBizTypeName(scmInvSaleIssueBillEntry.getBizType());
        					}
        					code = codeBiz.selectByCategoryAndCode("warehouseStatus",
        							scmInvSaleIssueBillEntry.getStatus());
        					if (code != null) {
        						scmCostTransferOccurSum.setStatusName(code.getName());
        					} else {
        						scmCostTransferOccurSum.setStatusName(scmInvSaleIssueBillEntry.getStatus());
        					}
        					scmCostTransferOccurSum.setBillNo(scmInvSaleIssueBillEntry.getOtNo());
                            scmCostTransferOccurSum.setBizDate(scmInvSaleIssueBillEntry.getBizDate());
                            scmCostTransferOccurSum.setLot(scmInvSaleIssueBillEntry.getLot());
                            scmCostTransferOccurSum.setUnitName(scmInvSaleIssueBillEntry.getUnitName());
                            scmCostTransferOccurSum.setCostOrgUnitName(map.get(scmInvSaleIssueBillEntry.getOutCostOrgUnitNo()));
                            scmCostTransferOccurSum.setAddInQty(scmCostTransferOccurSum.getAddInQty().add(scmInvSaleIssueBillEntry.getQty()));
                            scmCostTransferOccurSum.setAddInAmt(scmCostTransferOccurSum.getAddInAmt().add(scmInvSaleIssueBillEntry.getAmt()));
                            scmCostTransferOccurSum.setAddInTaxAmt(scmCostTransferOccurSum.getAddInTaxAmt().add(scmInvSaleIssueBillEntry.getTaxAmt()));
							scmCostTransferOccurSum.setAddInTax(scmCostTransferOccurSum.getAddInTaxAmt().subtract(scmCostTransferOccurSum.getAddInAmt()));
                            scmCostTransferOccurSumList.add(scmCostTransferOccurSum);
                        }
                    }
                }
                if (scmInvSaleIssueBillEntryListOut != null && scmInvSaleIssueBillEntryListOut.size() > 0) {
                    // 销售出库
                    if (StringUtils.equals("Y", InvSaleOut)) {
//                        intervalBizType.add(index, "销售出库");
                        for (ScmInvSaleIssueBillEntry2 scmInvSaleIssueBillEntry : scmInvSaleIssueBillEntryListOut) {
                		 	ScmNewCostTransferOccurDetail scmCostTransferOccurSum = new ScmNewCostTransferOccurDetail(true);
                            OrgBaseUnit2 orgBaseUnit = orgUnitBiz.selectbyOrgNo(scmInvSaleIssueBillEntry.getOutOrgUnitNo(), param);
        					if (orgBaseUnit != null) {
        						scmCostTransferOccurSum.setUseOrgUnitName(orgBaseUnit.getOrgUnitName());
        					} else {
        						scmCostTransferOccurSum.setUseOrgUnitName(scmCostTransferOccurSum.getUseOrgUnitNo());
        					}
        					Code code = codeBiz.selectByCategoryAndCode("saleOWBizType", scmInvSaleIssueBillEntry.getBizType());
        					if (code != null) {
        						scmCostTransferOccurSum.setBizTypeName(code.getName());
        					} else {
        						scmCostTransferOccurSum.setBizTypeName(scmInvSaleIssueBillEntry.getBizType());
        					}
        					code = codeBiz.selectByCategoryAndCode("warehouseStatus",
        							scmInvSaleIssueBillEntry.getStatus());
        					if (code != null) {
        						scmCostTransferOccurSum.setStatusName(code.getName());
        					} else {
        						scmCostTransferOccurSum.setStatusName(scmInvSaleIssueBillEntry.getStatus());
        					}
        					scmCostTransferOccurSum.setBillNo(scmInvSaleIssueBillEntry.getOtNo());
                            scmCostTransferOccurSum.setBizDate(scmInvSaleIssueBillEntry.getBizDate());
                            scmCostTransferOccurSum.setLot(scmInvSaleIssueBillEntry.getLot());
                            scmCostTransferOccurSum.setUnitName(scmInvSaleIssueBillEntry.getUnitName());
                            scmCostTransferOccurSum.setCostOrgUnitName(map.get(scmInvSaleIssueBillEntry.getOutCostOrgUnitNo()));
                            scmCostTransferOccurSum.setOutQty(scmCostTransferOccurSum.getOutQty().add(scmInvSaleIssueBillEntry.getQty()));
                            scmCostTransferOccurSum.setOutAmt(scmCostTransferOccurSum.getOutAmt().add(scmInvSaleIssueBillEntry.getAmt()));
                            scmCostTransferOccurSum.setOutTaxAmt(scmCostTransferOccurSum.getOutTaxAmt().add(scmInvSaleIssueBillEntry.getTaxAmt()));
							scmCostTransferOccurSum.setOutTax(scmCostTransferOccurSum.getOutTaxAmt().subtract(scmCostTransferOccurSum.getOutAmt()));
                            scmCostTransferOccurSumList.add(scmCostTransferOccurSum);
                        }
                    }
                }
            }
        }
        
        //成本转移单
        page = new Page();
        page.setModelClass(ScmInvMoveBillEntry2.class);
        page.setShowCount(Integer.MAX_VALUE);
        if(StringUtils.isNotBlank(sbMaterilaClass.toString())) {
            page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmMaterialGroup.class)+"."+ScmMaterialGroup.FN_ID, 
                    new QueryParam(ClassUtils.getFinalModelSimpleName(ScmMaterialGroup.class)+"."+ScmMaterialGroup.FN_ID, QueryParam.QUERY_IN, sbMaterilaClass.toString()));
        }
        if(StringUtils.isNotBlank(sbMaterila.toString())) {
            page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvMoveBillEntry2.class)+"."+ScmInvMoveBillEntry2.FN_ITEMID, 
                    new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMoveBillEntry2.class)+"."+ScmInvMoveBillEntry2.FN_ITEMID, QueryParam.QUERY_IN, sbMaterila.toString()));
        }
        page.getParamOr().put(ClassUtils.getFinalModelSimpleName(ScmInvMoveBill.class)+"."+ScmInvMoveBill.FN_OUTCSTORGUNITNO, 
                new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMoveBill.class)+"."+ScmInvMoveBill.FN_OUTCSTORGUNITNO, QueryParam.QUERY_IN, costOrgUnitNos.toString()));
        page.getParamOr().put(ClassUtils.getFinalModelSimpleName(ScmInvMoveBill.class)+"."+ScmInvMoveBill.FN_INCSTORGUNITNO, 
                new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMoveBill.class)+"."+ScmInvMoveBill.FN_INCSTORGUNITNO, QueryParam.QUERY_IN, costOrgUnitNos.toString()));
        page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvMoveBill.class)+"."+ScmInvMoveBill.FN_BIZDATE, 
                new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMoveBill.class)+"."+ScmInvMoveBill.FN_BIZDATE, QueryParam.QUERY_BETWEEN, beginDate,endDate));
        if (!StringUtils.equals("Y", status)) {
        	page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvMoveBill.class)+"."+ScmInvMoveBill.FN_STATUS, 
	                new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMoveBill.class)+"."+ScmInvMoveBill.FN_STATUS, QueryParam.QUERY_EQ, "E"));
		}else{
			page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvMoveBill.class)+"."+ScmInvMoveBill.FN_STATUS, 
	                new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMoveBill.class)+"."+ScmInvMoveBill.FN_STATUS, QueryParam.QUERY_NE, "N"));
		}
        if (StringUtils.equals("Y", moveIn) || StringUtils.equals("Y", moveOut) || StringUtils.equals("Y", costConsume2)) {
            List<ScmInvMoveBillEntry2> scmInvMoveBillEntryList = scmInvMoveBillEntryBiz.queryPage(page, arglist, "findAllPage", param);
            if(scmInvMoveBillEntryList!=null && !scmInvMoveBillEntryList.isEmpty()) {
                List<ScmInvMoveBillEntry2> scmInvMoveBillEntryListIn = new ArrayList<ScmInvMoveBillEntry2>(); 
                List<ScmInvMoveBillEntry2> scmInvMoveBillEntryListOut = new ArrayList<ScmInvMoveBillEntry2>(); 
                for (ScmInvMoveBillEntry2 scmInvMoveBillEntry : scmInvMoveBillEntryList) {
                    if (StringUtils.contains(costOrgUnitNos.toString(), scmInvMoveBillEntry.getInCstOrgUnitNo())) {//入
                        scmInvMoveBillEntryListIn.add(scmInvMoveBillEntry);
                    }
                    if (StringUtils.contains(costOrgUnitNos.toString(), scmInvMoveBillEntry.getOutCstOrgUnitNo())) {//出
                        scmInvMoveBillEntryListOut.add(scmInvMoveBillEntry);
                    }
                }
                if (scmInvMoveBillEntryListIn != null && scmInvMoveBillEntryListIn.size() > 0) {
                    if (StringUtils.equals("Y", moveIn)) {
//                        intervalBizType.add(index, "成本转移单入");
                        for (ScmInvMoveBillEntry2 scmInvMoveBillEntry : scmInvMoveBillEntryListIn) {
                    		ScmNewCostTransferOccurDetail scmCostTransferOccurSum = new ScmNewCostTransferOccurDetail(true);
                            OrgBaseUnit2 orgBaseUnit = orgUnitBiz.selectbyOrgNo(scmInvMoveBillEntry.getInOrgUnitNo(), param);
        					if (orgBaseUnit != null) {
        						scmCostTransferOccurSum.setUseOrgUnitName(orgBaseUnit.getOrgUnitName());
        					} else {
        						scmCostTransferOccurSum.setUseOrgUnitName(scmInvMoveBillEntry.getInOrgUnitNo());
        					}
    						Code code = codeBiz.selectByCategoryAndCode("cstStatus", scmInvMoveBillEntry.getStatus());
    						if (code != null) {
    							scmCostTransferOccurSum.setStatusName(code.getName());
    						} else {
    							scmCostTransferOccurSum.setStatusName(scmInvMoveBillEntry.getStatus());
    						}
    						scmCostTransferOccurSum.setBillNo(scmInvMoveBillEntry.getWtNo());
                            scmCostTransferOccurSum.setBizDate(scmInvMoveBillEntry.getBizDate());
                            scmCostTransferOccurSum.setBizTypeName("成本转移入");
                            scmCostTransferOccurSum.setLot(scmInvMoveBillEntry.getLot());
                            scmCostTransferOccurSum.setUnitName(scmInvMoveBillEntry.getUnitName());
                            scmCostTransferOccurSum.setCostOrgUnitNo(scmInvMoveBillEntry.getInCstOrgUnitNo());
                            scmCostTransferOccurSum.setCostOrgUnitName(map.get(scmInvMoveBillEntry.getInCstOrgUnitNo()));
                            scmCostTransferOccurSum.setAddInQty(scmCostTransferOccurSum.getAddInQty().add(scmInvMoveBillEntry.getQty()));
                            scmCostTransferOccurSum.setAddInAmt(scmCostTransferOccurSum.getAddInAmt().add(scmInvMoveBillEntry.getAmt()));
                            scmCostTransferOccurSum.setAddInTaxAmt(scmCostTransferOccurSum.getAddInTaxAmt().add(scmInvMoveBillEntry.getTaxAmt()));
							scmCostTransferOccurSum.setAddInTax(scmCostTransferOccurSum.getAddInTaxAmt().subtract(scmCostTransferOccurSum.getAddInAmt()));
                            scmCostTransferOccurSumList.add(scmCostTransferOccurSum);
                        }
                    }
                }
                if (scmInvMoveBillEntryListOut != null && scmInvMoveBillEntryListOut.size() > 0) {
                    if (StringUtils.equals("Y", moveOut)) {
//                        intervalBizType.add(index, "成本转移单出");
                        for (ScmInvMoveBillEntry2 scmInvMoveBillEntry : scmInvMoveBillEntryListOut) {
                    		ScmNewCostTransferOccurDetail scmCostTransferOccurSum = new ScmNewCostTransferOccurDetail(true);
                            OrgBaseUnit2 orgBaseUnit = orgUnitBiz.selectbyOrgNo(scmInvMoveBillEntry.getOutOrgUnitNo(), param);
        					if (orgBaseUnit != null) {
        						scmCostTransferOccurSum.setUseOrgUnitName(orgBaseUnit.getOrgUnitName());
        					} else {
        						scmCostTransferOccurSum.setUseOrgUnitName(scmInvMoveBillEntry.getOutOrgUnitNo());
        					}
    						Code code = codeBiz.selectByCategoryAndCode("cstStatus", scmInvMoveBillEntry.getStatus());
    						if (code != null) {
    							scmCostTransferOccurSum.setStatusName(code.getName());
    						} else {
    							scmCostTransferOccurSum.setStatusName(scmInvMoveBillEntry.getStatus());
    						}
                            scmCostTransferOccurSum.setBizDate(scmInvMoveBillEntry.getBizDate());
                            scmCostTransferOccurSum.setBillNo(scmInvMoveBillEntry.getWtNo());
                            scmCostTransferOccurSum.setBizTypeName("成本转移出");
                            scmCostTransferOccurSum.setLot(scmInvMoveBillEntry.getLot());
                            scmCostTransferOccurSum.setUnitName(scmInvMoveBillEntry.getUnitName());
                            scmCostTransferOccurSum.setCostOrgUnitName(map.get(scmInvMoveBillEntry.getOutCstOrgUnitNo()));
                            scmCostTransferOccurSum.setOutQty(scmCostTransferOccurSum.getOutQty().add(scmInvMoveBillEntry.getQty()));
                            scmCostTransferOccurSum.setOutAmt(scmCostTransferOccurSum.getOutAmt().add(scmInvMoveBillEntry.getAmt()));
                            scmCostTransferOccurSum.setOutTaxAmt(scmCostTransferOccurSum.getOutTaxAmt().add(scmInvMoveBillEntry.getTaxAmt()));
							scmCostTransferOccurSum.setOutTax(scmCostTransferOccurSum.getOutTaxAmt().subtract(scmCostTransferOccurSum.getOutAmt()));	
                            scmCostTransferOccurSumList.add(scmCostTransferOccurSum); 
                        }
                    }
                }
            }
        }
        //耗用单
        page = new Page();
        page.setModelClass(ScmInvCostConsumeEntry2.class);
        page.setShowCount(Integer.MAX_VALUE);
        if(StringUtils.isNotBlank(sbMaterilaClass.toString())) {
            page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmMaterialGroup.class)+"."+ScmMaterialGroup.FN_ID, 
                    new QueryParam(ClassUtils.getFinalModelSimpleName(ScmMaterialGroup.class)+"."+ScmMaterialGroup.FN_ID, QueryParam.QUERY_IN, sbMaterilaClass.toString()));
        }
        if(StringUtils.isNotBlank(sbMaterila.toString())) {
            page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvCostConsumeEntry2.class)+"."+ScmInvCostConsumeEntry2.FN_ITEMID, 
                    new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvCostConsumeEntry2.class)+"."+ScmInvCostConsumeEntry2.FN_ITEMID, QueryParam.QUERY_IN, sbMaterila.toString()));
        }
		page.getParam().put(
				ClassUtils.getFinalModelSimpleName(ScmInvCostConsume.class) + "." + ScmInvCostConsume.FN_GENERATE,
				new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvCostConsume.class) + "."
						+ ScmInvCostConsume.FN_GENERATE, QueryParam.QUERY_EQ, "0"));
        page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvCostConsume.class)+"."+ScmInvCostConsume.FN_ORGUNITNO, 
                new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvCostConsume.class)+"."+ScmInvCostConsume.FN_ORGUNITNO, QueryParam.QUERY_IN, costOrgUnitNos.toString()));
        page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvCostConsume.class)+"."+ScmInvCostConsume.FN_BIZDATE, 
                new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvCostConsume.class)+"."+ScmInvCostConsume.FN_BIZDATE, QueryParam.QUERY_BETWEEN, beginDate,endDate));
        if (!StringUtils.equals("Y", status)) {
        	page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvCostConsume.class)+"."+ScmInvCostConsume.FN_STATUS, 
	                new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvCostConsume.class)+"."+ScmInvCostConsume.FN_STATUS, QueryParam.QUERY_EQ, "E"));
		}else{
			page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvCostConsume.class)+"."+ScmInvCostConsume.FN_STATUS, 
	                new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvCostConsume.class)+"."+ScmInvCostConsume.FN_STATUS, QueryParam.QUERY_NE, "N"));
		}
        if (StringUtils.equals("Y", costConsume)) {
            List<ScmInvCostConsumeEntry2> scmInvCostConsumeEntryList = scmInvCostConsumeEntryBiz.queryPage(page, arglist, "findAllPage", param);
            if(scmInvCostConsumeEntryList!=null && !scmInvCostConsumeEntryList.isEmpty()) {
//                intervalBizType.add(index, "耗用单");
                for (ScmInvCostConsumeEntry2 scmInvCostConsumeEntry : scmInvCostConsumeEntryList) {
        		 	ScmNewCostTransferOccurDetail scmCostTransferOccurSum = new ScmNewCostTransferOccurDetail(true);
                    OrgBaseUnit2 orgBaseUnit = orgUnitBiz.selectbyOrgNo(scmInvCostConsumeEntry.getUseOrgUnitNo(), param);
					if (orgBaseUnit != null) {
						scmCostTransferOccurSum.setUseOrgUnitName(orgBaseUnit.getOrgUnitName());
					} else {
						scmCostTransferOccurSum.setUseOrgUnitName(scmInvCostConsumeEntry.getUseOrgUnitNo());
					}
					Code code = codeBiz.selectByCategoryAndCode("cstStatus", scmInvCostConsumeEntry.getStatus());
					if (code != null) {
						scmCostTransferOccurSum.setStatusName(code.getName());
					} else {
						scmCostTransferOccurSum.setStatusName(scmInvCostConsumeEntry.getStatus());
					}
					scmCostTransferOccurSum.setBillNo(scmInvCostConsumeEntry.getDcNo());
                    scmCostTransferOccurSum.setBizDate(scmInvCostConsumeEntry.getBizDate());
                    scmCostTransferOccurSum.setBizTypeName("耗用");
                    scmCostTransferOccurSum.setLot(scmInvCostConsumeEntry.getLot());
//                    scmCostTransferOccurSum.setVendorName(scmInvCostConsumeEntry.getVendorName());
                    scmCostTransferOccurSum.setUnitName(scmInvCostConsumeEntry.getUnitName());
                    scmCostTransferOccurSum.setCostOrgUnitName(map.get(scmInvCostConsumeEntry.getCostOrgUnitNo()));
                    scmCostTransferOccurSum.setOutQty(scmCostTransferOccurSum.getOutQty().add(scmInvCostConsumeEntry.getQty()));
                    scmCostTransferOccurSum.setOutAmt(scmCostTransferOccurSum.getOutAmt().add(scmInvCostConsumeEntry.getAmt()));
                    scmCostTransferOccurSum.setOutTaxAmt(scmCostTransferOccurSum.getOutTaxAmt().add(scmInvCostConsumeEntry.getTaxAmt())); 
					scmCostTransferOccurSum.setOutTax(scmCostTransferOccurSum.getOutTaxAmt().subtract(scmCostTransferOccurSum.getOutAmt()));
                    scmCostTransferOccurSumList.add(scmCostTransferOccurSum);
                }
            }
        }
        //盘存
        page = new Page();
        page.setModelClass(ScmInvCountingCostCenterEntry2.class);
        page.setShowCount(Integer.MAX_VALUE);
        if(StringUtils.isNotBlank(sbMaterilaClass.toString())) {
            page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmMaterialGroup.class)+"."+ScmMaterialGroup.FN_ID, 
                    new QueryParam(ClassUtils.getFinalModelSimpleName(ScmMaterialGroup.class)+"."+ScmMaterialGroup.FN_ID, QueryParam.QUERY_IN, sbMaterilaClass.toString()));
        }
        if(StringUtils.isNotBlank(sbMaterila.toString())) {
            page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvCountingCostCenterEntry2.class)+"."+ScmInvCountingCostCenterEntry2.FN_ITEMID, 
                    new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvCountingCostCenterEntry2.class)+"."+ScmInvCountingCostCenterEntry2.FN_ITEMID, QueryParam.QUERY_IN, sbMaterila.toString()));
        }
        page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvCountingCostCenter.class)+"."+ScmInvCountingCostCenter.FN_ORGUNITNO, 
                new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvCountingCostCenter.class)+"."+ScmInvCountingCostCenter.FN_ORGUNITNO, QueryParam.QUERY_IN, costOrgUnitNos.toString()));
        page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvCountingTask.class)+"."+ScmInvCountingTask.FN_BIZDATE, 
                new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvCountingTask.class)+"."+ScmInvCountingTask.FN_BIZDATE, QueryParam.QUERY_BETWEEN, beginDate,endDate));
        page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvCountingCostCenterEntry2.class)+"."+ScmInvCountingCostCenterEntry2.FN_DIFFERQTY, 
                new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvCountingCostCenterEntry2.class)+"."+ScmInvCountingCostCenterEntry2.FN_DIFFERQTY, QueryParam.QUERY_NE, "0"));
        if (!StringUtils.equals("Y", status)) {
        	 page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvCountingTask.class)+"."+ScmInvCountingTask.FN_STATUS, 
 	                new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvCountingTask.class)+"."+ScmInvCountingTask.FN_STATUS, QueryParam.QUERY_EQ, "O"));
		}else{
			 page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvCountingTask.class)+"."+ScmInvCountingTask.FN_STATUS, 
		                new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvCountingTask.class)+"."+ScmInvCountingTask.FN_STATUS, QueryParam.QUERY_IN, "'T','B','O','E'"));
		}
        if (StringUtils.equals("Y", counting)) {
            List<ScmInvCountingCostCenterEntry2> scmInvCountingCostCenterEntryList = scmInvCountingCostCenterEntryBiz.queryPage(page, arglist, "findAllPage", param);
            if(scmInvCountingCostCenterEntryList!=null && !scmInvCountingCostCenterEntryList.isEmpty()) {
//                intervalBizType.add(index, "盘存(耗用)");
                for (ScmInvCountingCostCenterEntry2 scmInvCountingCostCenterEntry : scmInvCountingCostCenterEntryList) {
            		ScmNewCostTransferOccurDetail scmCostTransferOccurSum = new ScmNewCostTransferOccurDetail(true);
                    OrgBaseUnit2 orgBaseUnit = orgUnitBiz.selectbyOrgNo(scmInvCountingCostCenterEntry.getUseOrgUnitNo(), param);
					if (orgBaseUnit != null) {
						scmCostTransferOccurSum.setUseOrgUnitName(orgBaseUnit.getOrgUnitName());
					} else {
						scmCostTransferOccurSum.setUseOrgUnitName(scmInvCountingCostCenterEntry.getUseOrgUnitNo());
					}
					Code code = codeBiz.selectByCategoryAndCode("countingStatus",
							scmInvCountingCostCenterEntry.getStatus());
					if (code != null) {
						scmCostTransferOccurSum.setStatusName(code.getName());
					} else {
						scmCostTransferOccurSum.setStatusName(scmInvCountingCostCenterEntry.getStatus());
					}
					scmCostTransferOccurSum.setBillNo(scmInvCountingCostCenterEntry.getTaskNo());
                    scmCostTransferOccurSum.setBizDate(scmInvCountingCostCenterEntry.getBizDate());
                    scmCostTransferOccurSum.setBizTypeName("盘存(耗用)");
                    scmCostTransferOccurSum.setLot(scmInvCountingCostCenterEntry.getLot());
//                    scmCostTransferOccurSum.setVendorName(scmInvCountingCostCenterEntry.getVendorName());
                    scmCostTransferOccurSum.setUnitName(scmInvCountingCostCenterEntry.getUnitName());
                    scmCostTransferOccurSum.setCostOrgUnitName(map.get(scmInvCountingCostCenterEntry.getOrgUnitNo()));
                    scmCostTransferOccurSum.setAddInQty(scmCostTransferOccurSum.getAddInQty().add((new BigDecimal("-1")).multiply(scmInvCountingCostCenterEntry.getDifferQty())));
                    scmCostTransferOccurSum.setAddInAmt(scmCostTransferOccurSum.getAddInAmt().add((new BigDecimal("-1")).multiply(scmInvCountingCostCenterEntry.getDifferAmt())));
                    scmCostTransferOccurSum.setAddInTaxAmt(scmCostTransferOccurSum.getAddInTaxAmt().add((new BigDecimal("-1")).multiply(scmInvCountingCostCenterEntry.getDifferTaxAmt())));
					scmCostTransferOccurSum.setAddInTax(scmCostTransferOccurSum.getAddInTaxAmt().subtract(scmCostTransferOccurSum.getAddInAmt()));
                    scmCostTransferOccurSumList.add(scmCostTransferOccurSum);
                }
			}
        }
        //报损单
        page = new Page();
        page.setModelClass(ScmCstFrmLossEntry2.class);
        page.setShowCount(Integer.MAX_VALUE);
        if(StringUtils.isNotBlank(materialClassName)) {
            page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmMaterialGroup.class)+"."+ScmMaterialGroup.FN_CLASSCODE, 
                    new QueryParam(ClassUtils.getFinalModelSimpleName(ScmMaterialGroup.class)+"."+ScmMaterialGroup.FN_CLASSCODE, QueryParam.QUERY_EQ, materialClassName));
        }
        if(StringUtils.isNotBlank(materialName)) {
            page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmCstFrmLossEntry2.class)+"."+ScmCstFrmLossEntry2.FN_ITEMID, 
                    new QueryParam(ClassUtils.getFinalModelSimpleName(ScmCstFrmLossEntry2.class)+"."+ScmCstFrmLossEntry2.FN_ITEMID, QueryParam.QUERY_EQ, materialName));
        }
        page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmCstFrmLoss.class)+"."+ScmCstFrmLoss.FN_ORGUNITNO, 
                new QueryParam(ClassUtils.getFinalModelSimpleName(ScmCstFrmLoss.class)+"."+ScmCstFrmLoss.FN_ORGUNITNO, QueryParam.QUERY_IN, costOrgUnitNos.toString()));
        page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmCstFrmLoss.class)+"."+ScmCstFrmLoss.FN_BIZDATE, 
                new QueryParam(ClassUtils.getFinalModelSimpleName(ScmCstFrmLoss.class)+"."+ScmCstFrmLoss.FN_BIZDATE, QueryParam.QUERY_BETWEEN, beginDate,endDate));
        if (!StringUtils.equals("Y", status)) {
        	page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmCstFrmLoss.class)+"."+ScmCstFrmLoss.FN_STATUS, 
	                new QueryParam(ClassUtils.getFinalModelSimpleName(ScmCstFrmLoss.class)+"."+ScmCstFrmLoss.FN_STATUS, QueryParam.QUERY_EQ, "E"));
		}else{
			page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmCstFrmLoss.class)+"."+ScmCstFrmLoss.FN_STATUS, 
	                new QueryParam(ClassUtils.getFinalModelSimpleName(ScmCstFrmLoss.class)+"."+ScmCstFrmLoss.FN_STATUS, QueryParam.QUERY_NE, "N"));
		}
        if (StringUtils.equals("Y", frmLoss)) {
            List<ScmCstFrmLossEntry2> scmCstFrmLossEntryList = scmCstFrmLossEntryBiz.queryPage(page, arglist, "findAllPage", param);
            if(scmCstFrmLossEntryList!=null && !scmCstFrmLossEntryList.isEmpty()) {
//                intervalBizType.add(index, "报损单");
                for (ScmCstFrmLossEntry2 scmCstFrmLossEntry : scmCstFrmLossEntryList) {
            		ScmNewCostTransferOccurDetail scmCostTransferOccurSum = new ScmNewCostTransferOccurDetail(true);
                    OrgBaseUnit2 orgBaseUnit = orgUnitBiz.selectbyOrgNo(scmCstFrmLossEntry.getUseOrgUnitNo(), param);
					if (orgBaseUnit != null) {
						scmCostTransferOccurSum.setUseOrgUnitName(orgBaseUnit.getOrgUnitName());
					} else {
						scmCostTransferOccurSum.setUseOrgUnitName(scmCstFrmLossEntry.getUseOrgUnitNo());
					}
					Code code = codeBiz.selectByCategoryAndCode("cstStatus", scmCstFrmLossEntry.getStatus());
					if (code != null) {
						scmCostTransferOccurSum.setStatusName(code.getName());
					} else {
						scmCostTransferOccurSum.setStatusName(scmCstFrmLossEntry.getStatus());
					}
					scmCostTransferOccurSum.setBillNo(scmCstFrmLossEntry.getBillNo());
                    scmCostTransferOccurSum.setBizDate(scmCstFrmLossEntry.getBizDate());
                    scmCostTransferOccurSum.setBizTypeName("报损");
                    scmCostTransferOccurSum.setLot(scmCstFrmLossEntry.getLot());
//                    scmCostTransferOccurSum.setVendorName(scmCstFrmLossEntry.getVendorName());
                    scmCostTransferOccurSum.setUnitName(scmCstFrmLossEntry.getUnitName());
                    scmCostTransferOccurSum.setCostOrgUnitName(map.get(scmCstFrmLossEntry.getCostOrgUnitNo()));
                    scmCostTransferOccurSum.setOutQty(scmCostTransferOccurSum.getOutQty().add(scmCstFrmLossEntry.getQty()));
                    scmCostTransferOccurSum.setOutAmt(scmCostTransferOccurSum.getOutAmt().add(scmCstFrmLossEntry.getAmt()));
                    scmCostTransferOccurSum.setOutTaxAmt(scmCostTransferOccurSum.getOutTaxAmt().add(scmCstFrmLossEntry.getTaxAmt())); 
                    scmCostTransferOccurSum.setOutTax(scmCostTransferOccurSum.getOutTaxAmt().subtract(scmCostTransferOccurSum.getOutAmt()));
                    scmCostTransferOccurSumList.add(scmCostTransferOccurSum);
                }
            }
        }
        //数据排序
        String fields[]={"costOrgUnitName"};
        String sorts[]={"ASC"};
        
        scmCostTransferOccurSumList = (List<ScmNewCostTransferOccurDetail>)ListSortUtil.sort(scmCostTransferOccurSumList, fields, sorts);
        // TODO 结束
        log.info(System.currentTimeMillis()-begin+"-----总耗时；记录数"+scmCostTransferOccurSumList.size());
        return scmCostTransferOccurSumList;
	}
	
	@Override
	public List<ScmCostRealtimeStockSum> selectImmediateCostSum(HttpServletRequest request) throws AppException {
		HashMap<String, Object> map = new HashMap<>();	
		String currentOrgUnitNo=request.getParameter("orgUnitNo");
		String currentControlUnitNo=request.getParameter("controlUnitNo");
  		String costOrgUnitNo=request.getParameter("costOrgUnitNo");
		if(StringUtils.isBlank(costOrgUnitNo))
			costOrgUnitNo = currentOrgUnitNo;
		Param param = new Param();
		param.setOrgUnitNo(currentOrgUnitNo);
		param.setControlUnitNo(currentControlUnitNo);
		StringBuffer orgUnitNos=new StringBuffer("");
		HashMap<String,String> orgUnitNameMap = new HashMap<>();
		if(StringUtils.isNotBlank(costOrgUnitNo)) {
            String[] orgUnitNoList = StringUtils.split(costOrgUnitNo, ",");
            for(String org:orgUnitNoList) {
                if(StringUtils.isBlank(org)) continue;
                List<OrgCostCenter2> orgCostCenterList = orgCostCenterBiz.findChild(org, param);
        		if(orgCostCenterList!=null && !orgCostCenterList.isEmpty()) {
        			for(OrgCostCenter2 orgCostCenter:orgCostCenterList) {
        				if(StringUtils.isNotBlank(orgUnitNos.toString()))
        					orgUnitNos.append(",");
        				orgUnitNos.append("'").append(orgCostCenter.getOrgUnitNo()).append("'");
        				orgUnitNameMap.put(orgCostCenter.getOrgUnitNo(), orgCostCenter.getOrgUnitName());
        			}
        		}
            }
			map.put("costOrgUnitNo", orgUnitNos.toString());
        }
  		String materialClassName=request.getParameter("materialClassName");
        if (StringUtils.isNotBlank(materialClassName)) {
			String[] materialClassNameList = StringUtils.split(materialClassName, ",");
			StringBuffer sbMaterilaClass = new StringBuffer("");
            for(String materialClass:materialClassNameList) {
                if(StringUtils.isBlank(materialClass)) continue;
				int materialClassId = Integer.parseInt(materialClass);
				List<ScmMaterialGroup> scmMaterialGroupList = scmMaterialGroupBiz.findChild(materialClassId, param);
				if (scmMaterialGroupList != null && scmMaterialGroupList.size() > 0) {
					for(ScmMaterialGroup scmMaterialGroup:scmMaterialGroupList) {
						if(StringUtils.isNotBlank(sbMaterilaClass.toString()))
							sbMaterilaClass.append(",");
						sbMaterilaClass.append(String.valueOf(scmMaterialGroup.getId()));
					}
				}
			}
			map.put("materialClassIds", sbMaterilaClass.toString());			
        }
  		String materialName=request.getParameter("materialName");
        if (StringUtils.isNotBlank(materialName)) {
        	String[] materialNameList = StringUtils.split(materialName, ",");
			StringBuffer sbMaterila = new StringBuffer("");
            for(String material:materialNameList) {
                if(StringUtils.isBlank(material)) continue;
				int materialId = Integer.parseInt(material);
				if(StringUtils.isNotBlank(sbMaterila.toString()))
					sbMaterila.append(",");
				sbMaterila.append(String.valueOf(materialId));
            }
			map.put("materialIds", sbMaterila.toString());
        } 
  		List<ScmCostRealtimeStockSum> list = ((ScmCostReportDAO) dao).selectImmediateCostSum(map);
  		if (list != null && list.size() > 0) {
  			for (ScmCostRealtimeStockSum scmCostRealtimeStockSum: list) {
  				if (scmCostRealtimeStockSum.getCostOrgUnitNo() != null){
                    //成本中心
  					OrgBaseUnit2 orgBaseUnit =  orgUnitBiz.selectbyOrgNo(scmCostRealtimeStockSum.getCostOrgUnitNo(), param);
                    if (orgBaseUnit != null) {
                    	scmCostRealtimeStockSum.setCostOrgUnitName(orgBaseUnit.getOrgUnitName());
                    }
                } 
                if (scmCostRealtimeStockSum.getUseOrgUnitNo() != null){
                    //部门
                	OrgBaseUnit2 orgBaseUnit =  orgUnitBiz.selectbyOrgNo(scmCostRealtimeStockSum.getUseOrgUnitNo(), param);
                    if (orgBaseUnit != null) {
                    	scmCostRealtimeStockSum.setUseOrgUnitName(orgBaseUnit.getOrgUnitName());
                    }
                }
                if (scmCostRealtimeStockSum.getUnit() > 0){
                    //计量单位
                    ScmMeasureUnit scmMeasureUnit = scmMeasureUnitBiz.selectDirect(scmCostRealtimeStockSum.getUnit(), param);
                    if (scmMeasureUnit != null) {
                    	scmCostRealtimeStockSum.setUnitName(scmMeasureUnit.getUnitName());
                    }
                }
                if (scmCostRealtimeStockSum.getPieUnit() > 0){
                    //辅助单位
                    ScmMeasureUnit scmMeasureUnit = scmMeasureUnitBiz.selectDirect(scmCostRealtimeStockSum.getPieUnit(), param);
                    if (scmMeasureUnit != null) {
                    	scmCostRealtimeStockSum.setPieUnitName(scmMeasureUnit.getUnitName());
                    }
                }
            }
  		}
  		return list;
	}

	@Override
	public List selectmovebillDetails(HttpServletRequest httpServletRequest) throws AppException {
		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		String paramValueMap=httpServletRequest.getParameter("paramValueMap");
		if(StringUtils.isNotBlank(paramValueMap)) {
			try {
				DesUtils des = new DesUtils("GyYo!N9T");// 自定义密钥
				paramValueMap = des.decrypt(paramValueMap);
				Gson gson = JSONUtils.newGson();
				ReportRequest reportRequest = gson.fromJson(paramValueMap, new TypeToken<ReportRequest>(){}.getType());
				map = reportRequest.getParamValueMap();
			} catch (Exception ex) {
				ex.printStackTrace(); 
            }
		}
		String currentOrgUnitNo=httpServletRequest.getParameter("orgUnitNo");
		String currentControlUnitNo=httpServletRequest.getParameter("controlUnitNo");
  		String costOrgUnitNo=httpServletRequest.getParameter("costOrgUnitNo");
		if(StringUtils.isBlank(costOrgUnitNo))
			costOrgUnitNo = currentOrgUnitNo;
		Param param = new Param();
		param.setOrgUnitNo(currentOrgUnitNo);
		param.setControlUnitNo(currentControlUnitNo);
		StringBuffer orgUnitNos=new StringBuffer("");
		HashMap<String,String> orgUnitNameMap = new HashMap<>();
		if(StringUtils.isNotBlank(costOrgUnitNo)) {
            String[] orgUnitNoList = StringUtils.split(costOrgUnitNo, ",");
            for(String org:orgUnitNoList) {
                if(StringUtils.isBlank(org)) continue;
                List<OrgCostCenter2> orgCostCenterList = orgCostCenterBiz.findChild(org, param);
        		if(orgCostCenterList!=null && !orgCostCenterList.isEmpty()) {
        			for(OrgCostCenter2 orgCostCenter:orgCostCenterList) {
        				if(StringUtils.isNotBlank(orgUnitNos.toString()))
        					orgUnitNos.append(",");
        				orgUnitNos.append("'").append(orgCostCenter.getOrgUnitNo()).append("'");
        				orgUnitNameMap.put(orgCostCenter.getOrgUnitNo(), orgCostCenter.getOrgUnitName());
        			}
        		}
            }
			map.put("costOrgUnitNo", orgUnitNos.toString());
        }
		List list = ((ScmCostReportDAO) dao).selectmovebillDetails(map);
		list = convertDisplay(list,param);
		return list;

	}
	
	private List convertDisplay(List list,Param param) throws AppException {
    	if (list != null && !list.isEmpty()) {
			if (list.get(0) instanceof HashMap) {
				for (Object obj : list) {
					if (obj instanceof HashMap) {
						HashMap map = (HashMap) obj;
						if (map != null) {
							for (Object key : map.keySet()) {
								if (map.get(key) != null && map.get(key) instanceof String) {
									String value0 = (String) map.get(key);
									if (StringUtils.contains(value0, "#$")) {
										// 不是以分隔符结尾则补分隔符，标准的格式形如：#$outletBiz#$营业点id#$
										if (!StringUtils.endsWith(value0, "#$")) {
											value0 += "#$";
										}
										
										String prefix = StringUtils.substringBefore(value0, "#$");
										String beanId = StringUtils.substringBetween(value0, "#$");
										String value1 = StringUtils.substringBetween(value0, "#$" + beanId + "#$", "#$");
										String suffix = StringUtils.substringAfterLast(value0, "#$");
										
										map.put(key, prefix + valueToDisplay(beanId, value1, param) + suffix);
									}
								}
							}
						}
					}
				}
			}
		}
    	return list;
    }

	public List<ScmCostItemInAndOutSum> selectSummaryOfMaterialsForInOfOut(HttpServletRequest request) throws AppException {
		List<ScmCostItemInAndOutSum> scmCostItemInAndOutSumList = new ArrayList<ScmCostItemInAndOutSum>();
		String currentOrgUnitNo = request.getParameter("orgUnitNo");
		String currentControlUnitNo = request.getParameter("controlUnitNo");
		String costOrgUnitNo = request.getParameter("costOrgUnitNo");
		String beginDate = request.getParameter("beginDate");
		String endDate = request.getParameter("endDate");
		String materialClassName = request.getParameter("materialClassName");
		String materialName = request.getParameter("materialName");
		String status = "1".equals(request.getParameter("status")) ? "Y" : "N";
		if(StringUtils.isBlank(beginDate) || StringUtils.isBlank(endDate))
			return scmCostItemInAndOutSumList;
		if(StringUtils.isBlank(costOrgUnitNo))
			costOrgUnitNo = currentOrgUnitNo;
		Param param = new Param();
		param.setOrgUnitNo(currentOrgUnitNo);
		param.setControlUnitNo(currentControlUnitNo);
		StringBuffer costOrgUnitNos = new StringBuffer("");
		HashMap<String,String> costCenterTypeMap = new HashMap<>();
		if (StringUtils.isNotBlank(costOrgUnitNo)) {
			String[] orgUnitNoList = StringUtils.split(costOrgUnitNo, ",");
			for (String org : orgUnitNoList) {
				if (StringUtils.isBlank(org))
					continue;
				List<OrgCostCenter2> orgCostCenterList = orgCostCenterBiz.findChild(org, param);
				if (orgCostCenterList != null && !orgCostCenterList.isEmpty()) {
					for (OrgCostCenter2 orgCostCenter : orgCostCenterList) {
						if (StringUtils.isNotBlank(costOrgUnitNos.toString()))
							costOrgUnitNos.append(",");
						costOrgUnitNos.append("'").append(orgCostCenter.getOrgUnitNo()).append("'");
						OrgCostCenter2 orgCostCenterTemp = orgCostCenterBiz.selectByOrgUnitNo(orgCostCenter.getOrgUnitNo(),
								param);
						if (orgCostCenterTemp != null) {
							costCenterTypeMap.put(orgCostCenterTemp.getOrgUnitNo(), orgCostCenterTemp.getCostCenterType());
						}
					}
				}
			}
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
		
	    long beginTime = System.currentTimeMillis();
		// 获取期间期初数
		PeriodCalendar periodCalendar = periodCalendarBiz.selectByBizdate(FormatUtils.parseDate(beginDate), param);
		if (periodCalendar != null) {
			Page page = new Page();
			page.setModelClass(ScmInvBal.class);
			page.setShowCount(Integer.MAX_VALUE);
			page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvBal.class) + "." + ScmInvBal.FN_COSTORGUNITNO,
					new QueryParam(
							ClassUtils.getFinalModelSimpleName(ScmInvBal.class) + "." + ScmInvBal.FN_COSTORGUNITNO,
							QueryParam.QUERY_IN, costOrgUnitNos.toString()));
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
			page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvBal.class) + "." + ScmInvBal.FN_PERIODID,
					new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvBal.class) + "." + ScmInvBal.FN_PERIODID,
							QueryParam.QUERY_EQ, String.valueOf(periodCalendar.getPeriodId())));
			List<String> arglist = new ArrayList<>();
			arglist.add("controlUnitNo=" + currentControlUnitNo);
			long dataEndTime11 = System.currentTimeMillis();
			log.info("期初数据查询准备耗时："+(dataEndTime11-beginTime)+"毫秒");
			List<ScmInvBal2> scmInvBalList = scmInvBalBiz.queryPage(page, arglist, "findItemPage", param);
			long dataEndTime = System.currentTimeMillis();
			log.info("期初数据查询耗时："+(dataEndTime-dataEndTime11)+"毫秒");
			if (scmInvBalList != null && !scmInvBalList.isEmpty()) {
				log.info("期初数据查询结果："+(scmInvBalList.size())+"条记录");
				for (ScmInvBal2 scmInvBal : scmInvBalList) {
					if (scmInvBal.isCostCenter()) {
						// 以领代耗的期初不处理，认为是零
//						OrgCostCenter2 orgCostCenter = orgCostCenterBiz.selectByOrgUnitNo(scmInvBal.getCostOrgUnitNo(),
//								param);
//						if (orgCostCenter != null && StringUtils.equals("1", orgCostCenter.getCostCenterType())) {
//							continue;
//						}
						if (costCenterTypeMap.containsKey(scmInvBal.getCostOrgUnitNo())) {
							if (StringUtils.equals("1",costCenterTypeMap.get(scmInvBal.getCostOrgUnitNo()))) {
								continue;
							}
						}
					}
					boolean exists = false;
					if (!scmCostItemInAndOutSumList.isEmpty()) {
						for (ScmCostItemInAndOutSum existScmCostItemInAndOutSum : scmCostItemInAndOutSumList) {
							if (existScmCostItemInAndOutSum.getItemId() == scmInvBal.getItemId() && StringUtils.equals(
									existScmCostItemInAndOutSum.getCostOrgUnitNo(), scmInvBal.getCostOrgUnitNo())) {
								existScmCostItemInAndOutSum.setInitQty(
										existScmCostItemInAndOutSum.getInitQty().add(scmInvBal.getStartQty()));
								existScmCostItemInAndOutSum.setInitAmt(
										existScmCostItemInAndOutSum.getInitAmt().add(scmInvBal.getStartAmt()));
								existScmCostItemInAndOutSum.setInitAvgPrice(
										BigDecimal.ZERO.compareTo(existScmCostItemInAndOutSum.getInitQty()) == 0
												? BigDecimal.ZERO
												: existScmCostItemInAndOutSum.getInitAmt().divide(
														existScmCostItemInAndOutSum.getInitQty(), 2,
														BigDecimal.ROUND_HALF_UP));
								existScmCostItemInAndOutSum.setInitTax(existScmCostItemInAndOutSum.getInitTax()
										.add(scmInvBal.getStartTaxAmt().subtract(scmInvBal.getStartAmt())));
								existScmCostItemInAndOutSum.setInitTaxAmt(
										existScmCostItemInAndOutSum.getInitTaxAmt().add(scmInvBal.getStartTaxAmt()));
								exists = true;
								break;
							}
						}
					}
					if (!exists) {
						ScmCostItemInAndOutSum scmCostItemInAndOutSum = new ScmCostItemInAndOutSum(true);
						scmCostItemInAndOutSum.setItemId(scmInvBal.getItemId());
						scmCostItemInAndOutSum.setItemNo(scmInvBal.getItemNo());
						scmCostItemInAndOutSum.setItemName(scmInvBal.getItemName());
						scmCostItemInAndOutSum.setSpec(scmInvBal.getSpec());
						scmCostItemInAndOutSum.setGroupId(scmInvBal.getGroupId());
						scmCostItemInAndOutSum.setClassCode(scmInvBal.getGroupCode());
						scmCostItemInAndOutSum.setClassName(scmInvBal.getGroupName());
						scmCostItemInAndOutSum.setLongNo(scmInvBal.getLongNo());
						scmCostItemInAndOutSum.setUnitName(scmInvBal.getUnitName());
						scmCostItemInAndOutSum.setUnit(scmInvBal.getUnit());
						scmCostItemInAndOutSum.setCostOrgUnitNo(scmInvBal.getCostOrgUnitNo());
						scmCostItemInAndOutSum.setInitQty(scmInvBal.getStartQty());
						scmCostItemInAndOutSum.setInitAmt(scmInvBal.getStartAmt());
						scmCostItemInAndOutSum.setInitAvgPrice(
								BigDecimal.ZERO.compareTo(scmCostItemInAndOutSum.getInitQty()) == 0 ? BigDecimal.ZERO
										: scmCostItemInAndOutSum.getInitAmt().divide(
												scmCostItemInAndOutSum.getInitQty(), 2, BigDecimal.ROUND_HALF_UP));
						scmCostItemInAndOutSum.setInitTax(scmInvBal.getStartTaxAmt().subtract(scmInvBal.getStartAmt()));
						scmCostItemInAndOutSum.setInitTaxAmt(scmInvBal.getStartTaxAmt());
						scmCostItemInAndOutSumList.add(scmCostItemInAndOutSum);
					}
				}
			}
			long dataEndTime1 = System.currentTimeMillis();
			log.info("期初数据加工耗时："+(dataEndTime1-dataEndTime)+"毫秒");
		}
		  long beginTime1 = System.currentTimeMillis();
		// 获取入库直拨数据
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
						+ ScmInvPurInWarehsBillEntry2.FN_COSTORGUNITNO,
				new QueryParam(
						ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBillEntry2.class) + "."
								+ ScmInvPurInWarehsBillEntry2.FN_COSTORGUNITNO,
						QueryParam.QUERY_IN, costOrgUnitNos.toString()));
		page.getParam()
				.put(ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBill2.class) + "."
						+ ScmInvPurInWarehsBill2.FN_BIZDATE, new QueryParam(
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
		long dataEndTime2 = System.currentTimeMillis();
		log.info("采购入库明细查询耗时："+(dataEndTime2-beginTime1)+"毫秒");
		if (scmInvPurInWarehsBillEntryList != null && !scmInvPurInWarehsBillEntryList.isEmpty()) {
			log.info("采购入库明细查询结果："+(scmInvPurInWarehsBillEntryList.size())+"条记录");
			for (ScmInvPurInWarehsBillEntry2 scmInvPurInWarehsBillEntry : scmInvPurInWarehsBillEntryList) {
				BigDecimal sideFlag = BigDecimal.ONE;
				if (StringUtils.contains("6,8", scmInvPurInWarehsBillEntry.getBizType())) {
					sideFlag = new BigDecimal("-1");
				}
//				OrgCostCenter2 orgCostCenter = orgCostCenterBiz
//						.selectByOrgUnitNo(scmInvPurInWarehsBillEntry.getCostOrgUnitNo(), param);
				if (scmInvPurInWarehsBillEntry.getBizDate().compareTo(FormatUtils.parseDate(beginDate)) < 0) {
//					if (orgCostCenter != null && StringUtils.equals("1", orgCostCenter.getCostCenterType())) {
//						// 以领代耗的不用统计，认为全用完
//						continue;
//					}
					if (costCenterTypeMap.containsKey(scmInvPurInWarehsBillEntry.getCostOrgUnitNo())) {
						if (StringUtils.equals("1",costCenterTypeMap.get(scmInvPurInWarehsBillEntry.getCostOrgUnitNo()))) {
							continue;
						}
					}
					// 比查询日期小的统计到期初数据中，并从List中删除
					boolean exists = false;
					if (!scmCostItemInAndOutSumList.isEmpty()) {
						for (ScmCostItemInAndOutSum existScmCostItemInAndOutSum : scmCostItemInAndOutSumList) {
							if (existScmCostItemInAndOutSum.getItemId() == scmInvPurInWarehsBillEntry.getItemId()
									&& StringUtils.equals(existScmCostItemInAndOutSum.getCostOrgUnitNo(),
											scmInvPurInWarehsBillEntry.getCostOrgUnitNo())) {
								existScmCostItemInAndOutSum.setInitQty(existScmCostItemInAndOutSum.getInitQty()
										.add(sideFlag.multiply(scmInvPurInWarehsBillEntry.getQty())));
								existScmCostItemInAndOutSum.setInitAmt(existScmCostItemInAndOutSum.getInitAmt()
										.add(sideFlag.multiply(scmInvPurInWarehsBillEntry.getAmt())));
								existScmCostItemInAndOutSum.setInitAvgPrice(
										BigDecimal.ZERO.compareTo(existScmCostItemInAndOutSum.getInitQty()) == 0
												? BigDecimal.ZERO
												: existScmCostItemInAndOutSum.getInitAmt().divide(
														existScmCostItemInAndOutSum.getInitQty(), 2,
														BigDecimal.ROUND_HALF_UP));
								existScmCostItemInAndOutSum.setInitTax(existScmCostItemInAndOutSum.getInitTax()
										.add(sideFlag.multiply(scmInvPurInWarehsBillEntry.getTaxAmt()
												.subtract(scmInvPurInWarehsBillEntry.getAmt()))));
								existScmCostItemInAndOutSum.setInitTaxAmt(existScmCostItemInAndOutSum.getInitTaxAmt()
										.add(sideFlag.multiply(scmInvPurInWarehsBillEntry.getTaxAmt())));
								exists = true;
								break;
							}
						}
					}
					if (!exists) {
						ScmCostItemInAndOutSum scmCostItemInAndOutSum = new ScmCostItemInAndOutSum(true);
						scmCostItemInAndOutSum.setItemId(scmInvPurInWarehsBillEntry.getItemId());
						scmCostItemInAndOutSum.setItemNo(scmInvPurInWarehsBillEntry.getItemNo());
						scmCostItemInAndOutSum.setItemName(scmInvPurInWarehsBillEntry.getItemName());
						scmCostItemInAndOutSum.setSpec(scmInvPurInWarehsBillEntry.getSpec());
						scmCostItemInAndOutSum.setGroupId(scmInvPurInWarehsBillEntry.getGroupId());
						scmCostItemInAndOutSum.setClassCode(scmInvPurInWarehsBillEntry.getGroupCode());
						scmCostItemInAndOutSum.setClassName(scmInvPurInWarehsBillEntry.getGroupName());
						scmCostItemInAndOutSum.setLongNo(scmInvPurInWarehsBillEntry.getLongNo());
						scmCostItemInAndOutSum.setUnitName(scmInvPurInWarehsBillEntry.getUnitName());
						scmCostItemInAndOutSum.setUnit(scmInvPurInWarehsBillEntry.getUnit());
						scmCostItemInAndOutSum.setCostOrgUnitNo(scmInvPurInWarehsBillEntry.getCostOrgUnitNo());
						scmCostItemInAndOutSum.setInitQty(sideFlag.multiply(scmInvPurInWarehsBillEntry.getQty()));
						scmCostItemInAndOutSum.setInitAmt(sideFlag.multiply(scmInvPurInWarehsBillEntry.getAmt()));
						scmCostItemInAndOutSum.setInitAvgPrice(
								BigDecimal.ZERO.compareTo(scmCostItemInAndOutSum.getInitQty()) == 0 ? BigDecimal.ZERO
										: scmCostItemInAndOutSum.getInitAmt().divide(
												scmCostItemInAndOutSum.getInitQty(), 2, BigDecimal.ROUND_HALF_UP));
						scmCostItemInAndOutSum.setInitTax(sideFlag.multiply(
								scmInvPurInWarehsBillEntry.getTaxAmt().subtract(scmInvPurInWarehsBillEntry.getAmt())));
						scmCostItemInAndOutSum.setInitTaxAmt(sideFlag.multiply(scmInvPurInWarehsBillEntry.getTaxAmt()));
						scmCostItemInAndOutSumList.add(scmCostItemInAndOutSum);
					}
				} else {
					boolean exists = false;
					if (!scmCostItemInAndOutSumList.isEmpty()) {
						for (ScmCostItemInAndOutSum existScmCostItemInAndOutSum : scmCostItemInAndOutSumList) {
							if (existScmCostItemInAndOutSum.getItemId() == scmInvPurInWarehsBillEntry.getItemId()
									&& StringUtils.equals(existScmCostItemInAndOutSum.getCostOrgUnitNo(),
											scmInvPurInWarehsBillEntry.getCostOrgUnitNo())) {
								existScmCostItemInAndOutSum.setAddInQty(existScmCostItemInAndOutSum.getAddInQty()
										.add(sideFlag.multiply(scmInvPurInWarehsBillEntry.getQty())));
								existScmCostItemInAndOutSum.setAddInAmt(existScmCostItemInAndOutSum.getAddInAmt()
										.add(sideFlag.multiply(scmInvPurInWarehsBillEntry.getAmt())));
								existScmCostItemInAndOutSum.setPurInQty(existScmCostItemInAndOutSum.getPurInQty()
										.add(sideFlag.multiply(scmInvPurInWarehsBillEntry.getQty())));
								existScmCostItemInAndOutSum.setPurInAvgPrice(
										BigDecimal.ZERO.compareTo(existScmCostItemInAndOutSum.getPurInQty()) == 0
												? BigDecimal.ZERO
												: existScmCostItemInAndOutSum.getPurInAmt().divide(
														existScmCostItemInAndOutSum.getPurInQty(), 2,
														BigDecimal.ROUND_HALF_UP));
								existScmCostItemInAndOutSum.setPurInAmt(existScmCostItemInAndOutSum.getPurInAmt()
										.add(sideFlag.multiply(scmInvPurInWarehsBillEntry.getAmt())));
								existScmCostItemInAndOutSum.setPurInTax(existScmCostItemInAndOutSum.getPurInTax()
										.add(sideFlag.multiply(scmInvPurInWarehsBillEntry.getTaxAmt()
												.subtract(scmInvPurInWarehsBillEntry.getAmt()))));
								existScmCostItemInAndOutSum.setPurInTaxAmt(existScmCostItemInAndOutSum.getPurInTaxAmt()
										.add(sideFlag.multiply(scmInvPurInWarehsBillEntry.getTaxAmt())));
								existScmCostItemInAndOutSum.setAddInAvgPrice(
										BigDecimal.ZERO.compareTo(existScmCostItemInAndOutSum.getAddInQty()) == 0
												? BigDecimal.ZERO
												: existScmCostItemInAndOutSum.getAddInAmt().divide(
														existScmCostItemInAndOutSum.getAddInQty(), 2,
														BigDecimal.ROUND_HALF_UP));
								existScmCostItemInAndOutSum.setAddInTax(existScmCostItemInAndOutSum.getAddInTax()
										.add(sideFlag.multiply(scmInvPurInWarehsBillEntry.getTaxAmt()
												.subtract(scmInvPurInWarehsBillEntry.getAmt()))));
								existScmCostItemInAndOutSum.setAddInTaxAmt(existScmCostItemInAndOutSum.getAddInTaxAmt()
										.add(sideFlag.multiply(scmInvPurInWarehsBillEntry.getTaxAmt())));
								
								if (costCenterTypeMap.containsKey(scmInvPurInWarehsBillEntry.getCostOrgUnitNo())) {
									if (StringUtils.equals("1", costCenterTypeMap.get(scmInvPurInWarehsBillEntry.getCostOrgUnitNo()))) {
										// 以领代耗时也需写出库数
										existScmCostItemInAndOutSum.setOutQty(existScmCostItemInAndOutSum.getOutQty()
												.add(sideFlag.multiply(scmInvPurInWarehsBillEntry.getQty())));
										existScmCostItemInAndOutSum.setOutAmt(existScmCostItemInAndOutSum.getOutAmt()
												.add(sideFlag.multiply(scmInvPurInWarehsBillEntry.getAmt())));
										existScmCostItemInAndOutSum.setOutAvgPrice(
												BigDecimal.ZERO.compareTo(existScmCostItemInAndOutSum.getOutQty()) == 0
														? BigDecimal.ZERO
														: existScmCostItemInAndOutSum.getOutAmt().divide(
																existScmCostItemInAndOutSum.getOutQty(), 2,
																BigDecimal.ROUND_HALF_UP));
										existScmCostItemInAndOutSum.setOutTax(existScmCostItemInAndOutSum.getOutTax()
												.add(sideFlag.multiply(scmInvPurInWarehsBillEntry.getTaxAmt()
														.subtract(scmInvPurInWarehsBillEntry.getAmt()))));
										existScmCostItemInAndOutSum.setOutTaxAmt(existScmCostItemInAndOutSum.getOutTaxAmt()
												.add(sideFlag.multiply(scmInvPurInWarehsBillEntry.getTaxAmt())));
									}
								}
								exists = true;
								break;
							}
						}
					}
					if (!exists) {
						// 放入明细记录结果集中
						ScmCostItemInAndOutSum scmCostItemInAndOutSum = new ScmCostItemInAndOutSum(true);
						scmCostItemInAndOutSum.setItemId(scmInvPurInWarehsBillEntry.getItemId());
						scmCostItemInAndOutSum.setItemNo(scmInvPurInWarehsBillEntry.getItemNo());
						scmCostItemInAndOutSum.setItemName(scmInvPurInWarehsBillEntry.getItemName());
						scmCostItemInAndOutSum.setSpec(scmInvPurInWarehsBillEntry.getSpec());
						scmCostItemInAndOutSum.setGroupId(scmInvPurInWarehsBillEntry.getGroupId());
						scmCostItemInAndOutSum.setClassCode(scmInvPurInWarehsBillEntry.getGroupCode());
						scmCostItemInAndOutSum.setClassName(scmInvPurInWarehsBillEntry.getGroupName());
						scmCostItemInAndOutSum.setLongNo(scmInvPurInWarehsBillEntry.getLongNo());
						scmCostItemInAndOutSum.setUnitName(scmInvPurInWarehsBillEntry.getUnitName());
						scmCostItemInAndOutSum.setUnit(scmInvPurInWarehsBillEntry.getUnit());
						scmCostItemInAndOutSum.setCostOrgUnitNo(scmInvPurInWarehsBillEntry.getCostOrgUnitNo());
						scmCostItemInAndOutSum.setAddInQty(sideFlag.multiply(scmInvPurInWarehsBillEntry.getQty()));
						scmCostItemInAndOutSum.setAddInAmt(sideFlag.multiply(scmInvPurInWarehsBillEntry.getAmt()));
						scmCostItemInAndOutSum.setAddInAvgPrice(
								BigDecimal.ZERO.compareTo(scmCostItemInAndOutSum.getAddInQty()) == 0 ? BigDecimal.ZERO
										: scmCostItemInAndOutSum.getAddInAmt().divide(
												scmCostItemInAndOutSum.getAddInQty(), 2, BigDecimal.ROUND_HALF_UP));
						scmCostItemInAndOutSum.setAddInTax(sideFlag.multiply(
								scmInvPurInWarehsBillEntry.getTaxAmt().subtract(scmInvPurInWarehsBillEntry.getAmt())));
						scmCostItemInAndOutSum
								.setAddInTaxAmt(sideFlag.multiply(scmInvPurInWarehsBillEntry.getTaxAmt()));
						scmCostItemInAndOutSum.setPurInQty(sideFlag.multiply(scmInvPurInWarehsBillEntry.getQty()));
						scmCostItemInAndOutSum.setPurInAvgPrice(
								BigDecimal.ZERO.compareTo(scmCostItemInAndOutSum.getPurInQty()) == 0 ? BigDecimal.ZERO
										: scmCostItemInAndOutSum.getPurInAmt().divide(
												scmCostItemInAndOutSum.getPurInQty(), 2, BigDecimal.ROUND_HALF_UP));
						scmCostItemInAndOutSum.setPurInAmt(sideFlag.multiply(scmInvPurInWarehsBillEntry.getAmt()));
						scmCostItemInAndOutSum.setPurInTax(sideFlag.multiply(
								scmInvPurInWarehsBillEntry.getTaxAmt().subtract(scmInvPurInWarehsBillEntry.getAmt())));
						scmCostItemInAndOutSum.setPurInTaxAmt(sideFlag.multiply(scmInvPurInWarehsBillEntry.getTaxAmt()));
						if (costCenterTypeMap.containsKey(scmInvPurInWarehsBillEntry.getCostOrgUnitNo())) {
							if (StringUtils.equals("1", costCenterTypeMap.get(scmInvPurInWarehsBillEntry.getCostOrgUnitNo()))) {
								// 以领代耗时也需写出库数
								scmCostItemInAndOutSum.setOutQty(sideFlag.multiply(scmInvPurInWarehsBillEntry.getQty()));
								scmCostItemInAndOutSum.setOutAmt(sideFlag.multiply(scmInvPurInWarehsBillEntry.getAmt()));
								scmCostItemInAndOutSum.setOutAvgPrice(
										BigDecimal.ZERO.compareTo(scmCostItemInAndOutSum.getOutQty()) == 0 ? BigDecimal.ZERO
												: scmCostItemInAndOutSum.getOutAmt().divide(
														scmCostItemInAndOutSum.getOutQty(), 2, BigDecimal.ROUND_HALF_UP));
								scmCostItemInAndOutSum.setOutTax(sideFlag.multiply(scmInvPurInWarehsBillEntry.getTaxAmt()
										.subtract(scmInvPurInWarehsBillEntry.getAmt())));
								scmCostItemInAndOutSum
									.setOutTaxAmt(sideFlag.multiply(scmInvPurInWarehsBillEntry.getTaxAmt()));
							}
						}
						scmCostItemInAndOutSumList.add(scmCostItemInAndOutSum);
					}
				}
			}
			long dataEndTime3 = System.currentTimeMillis();
			log.info("采购入库明细加工耗时："+(dataEndTime3-dataEndTime2)+"毫秒");
		}
		 long beginTime2 = System.currentTimeMillis();
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
						+ ScmInvMaterialReqBill.FN_COSTORGUNITNO,
				new QueryParam(
						ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBill.class) + "."
								+ ScmInvMaterialReqBill.FN_COSTORGUNITNO,
						QueryParam.QUERY_IN, costOrgUnitNos.toString()));
		page.getParam()
				.put(ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBill2.class) + "."
						+ ScmInvMaterialReqBill2.FN_BIZDATE, new QueryParam(
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
		long dataEndTime3 = System.currentTimeMillis();
		log.info("领料出库明细查询耗时："+(dataEndTime3-beginTime2)+"毫秒");
		if (scmInvMaterialReqBillEntryList != null && !scmInvMaterialReqBillEntryList.isEmpty()) {
			log.info("领料出库明细查询结果："+(scmInvMaterialReqBillEntryList.size())+"条记录");
			for (ScmInvMaterialReqBillEntry2 scmInvMaterialReqBillEntry : scmInvMaterialReqBillEntryList) {
				BigDecimal sideFlag = BigDecimal.ONE;
				if (StringUtils.contains("2", scmInvMaterialReqBillEntry.getBizType())) {
					sideFlag = new BigDecimal("-1");
				}
//				OrgCostCenter2 orgCostCenter = orgCostCenterBiz
//						.selectByOrgUnitNo(scmInvMaterialReqBillEntry.getCostOrgUnitNo(), param);
				if (scmInvMaterialReqBillEntry.getBizDate().compareTo(FormatUtils.parseDate(beginDate)) < 0) {
//					if (orgCostCenter != null && StringUtils.equals("1", orgCostCenter.getCostCenterType())) {
//						continue;
//					}
					if (costCenterTypeMap.containsKey(scmInvMaterialReqBillEntry.getCostOrgUnitNo())) {
						if (StringUtils.equals("1",costCenterTypeMap.get(scmInvMaterialReqBillEntry.getCostOrgUnitNo()))) {
							continue;
						}
					}
					// 比查询日期小的统计到期初数据中，并从List中删除
					boolean exists = false;
					if (!scmCostItemInAndOutSumList.isEmpty()) {
						for (ScmCostItemInAndOutSum existScmCostItemInAndOutSum : scmCostItemInAndOutSumList) {
							if (existScmCostItemInAndOutSum.getItemId() == scmInvMaterialReqBillEntry.getItemId()
									&& StringUtils.equals(existScmCostItemInAndOutSum.getCostOrgUnitNo(),
											scmInvMaterialReqBillEntry.getCostOrgUnitNo())) {
								existScmCostItemInAndOutSum.setInitQty(existScmCostItemInAndOutSum.getInitQty()
										.add(sideFlag.multiply(scmInvMaterialReqBillEntry.getQty())));
								existScmCostItemInAndOutSum.setInitAmt(existScmCostItemInAndOutSum.getInitAmt()
										.add(sideFlag.multiply(scmInvMaterialReqBillEntry.getAmt())));
								existScmCostItemInAndOutSum.setInitAvgPrice(
										BigDecimal.ZERO.compareTo(existScmCostItemInAndOutSum.getInitQty()) == 0
												? BigDecimal.ZERO
												: existScmCostItemInAndOutSum.getInitAmt().divide(
														existScmCostItemInAndOutSum.getInitQty(), 2,
														BigDecimal.ROUND_HALF_UP));
								existScmCostItemInAndOutSum.setInitTax(existScmCostItemInAndOutSum.getInitTax()
										.add(sideFlag.multiply(scmInvMaterialReqBillEntry.getTaxAmt()
												.subtract(scmInvMaterialReqBillEntry.getAmt()))));
								existScmCostItemInAndOutSum.setInitTaxAmt(existScmCostItemInAndOutSum.getInitTaxAmt()
										.add(sideFlag.multiply(scmInvMaterialReqBillEntry.getTaxAmt())));
								exists = true;
								break;
							}
						}
					}
					if (!exists) {
						ScmCostItemInAndOutSum scmCostItemInAndOutSum = new ScmCostItemInAndOutSum(true);
						scmCostItemInAndOutSum.setItemId(scmInvMaterialReqBillEntry.getItemId());
						scmCostItemInAndOutSum.setItemNo(scmInvMaterialReqBillEntry.getItemNo());
						scmCostItemInAndOutSum.setItemName(scmInvMaterialReqBillEntry.getItemName());
						scmCostItemInAndOutSum.setSpec(scmInvMaterialReqBillEntry.getSpec());
						scmCostItemInAndOutSum.setGroupId(scmInvMaterialReqBillEntry.getGroupId());
						scmCostItemInAndOutSum.setClassCode(scmInvMaterialReqBillEntry.getGroupCode());
						scmCostItemInAndOutSum.setClassName(scmInvMaterialReqBillEntry.getGroupName());
						scmCostItemInAndOutSum.setLongNo(scmInvMaterialReqBillEntry.getLongNo());
						scmCostItemInAndOutSum.setUnitName(scmInvMaterialReqBillEntry.getUnitName());
						scmCostItemInAndOutSum.setUnit(scmInvMaterialReqBillEntry.getUnit());
						scmCostItemInAndOutSum.setCostOrgUnitNo(scmInvMaterialReqBillEntry.getCostOrgUnitNo());
						scmCostItemInAndOutSum.setInitQty(sideFlag.multiply(scmInvMaterialReqBillEntry.getQty()));
						scmCostItemInAndOutSum.setInitAmt(sideFlag.multiply(scmInvMaterialReqBillEntry.getAmt()));
						scmCostItemInAndOutSum.setInitAvgPrice(
								BigDecimal.ZERO.compareTo(scmCostItemInAndOutSum.getInitQty()) == 0 ? BigDecimal.ZERO
										: scmCostItemInAndOutSum.getInitAmt().divide(
												scmCostItemInAndOutSum.getInitQty(), 2, BigDecimal.ROUND_HALF_UP));
						scmCostItemInAndOutSum.setInitTax(sideFlag.multiply(
								scmInvMaterialReqBillEntry.getTaxAmt().subtract(scmInvMaterialReqBillEntry.getAmt())));
						scmCostItemInAndOutSum.setInitTaxAmt(sideFlag.multiply(scmInvMaterialReqBillEntry.getTaxAmt()));
						scmCostItemInAndOutSumList.add(scmCostItemInAndOutSum);
					}
				} else {
					boolean exists = false;
					if (!scmCostItemInAndOutSumList.isEmpty()) {
						for (ScmCostItemInAndOutSum existScmCostItemInAndOutSum : scmCostItemInAndOutSumList) {
							if (existScmCostItemInAndOutSum.getItemId() == scmInvMaterialReqBillEntry.getItemId()
									&& StringUtils.equals(existScmCostItemInAndOutSum.getCostOrgUnitNo(),
											scmInvMaterialReqBillEntry.getCostOrgUnitNo())) {
								existScmCostItemInAndOutSum.setAddInQty(existScmCostItemInAndOutSum.getAddInQty()
										.add(sideFlag.multiply(scmInvMaterialReqBillEntry.getQty())));
								existScmCostItemInAndOutSum.setAddInAmt(existScmCostItemInAndOutSum.getAddInAmt()
										.add(sideFlag.multiply(scmInvMaterialReqBillEntry.getAmt())));
								existScmCostItemInAndOutSum.setReceiveInQty(existScmCostItemInAndOutSum
										.getReceiveInQty().add(sideFlag.multiply(scmInvMaterialReqBillEntry.getQty())));
								existScmCostItemInAndOutSum.setReceiveInAvgPrice(
										BigDecimal.ZERO.compareTo(existScmCostItemInAndOutSum.getReceiveInQty()) == 0
												? BigDecimal.ZERO
												: existScmCostItemInAndOutSum.getReceiveInAmt().divide(
														existScmCostItemInAndOutSum.getReceiveInQty(), 2,
														BigDecimal.ROUND_HALF_UP));
								existScmCostItemInAndOutSum.setReceiveInAmt(existScmCostItemInAndOutSum
										.getReceiveInAmt().add(sideFlag.multiply(scmInvMaterialReqBillEntry.getAmt())));
								existScmCostItemInAndOutSum.setReceiveInTax(existScmCostItemInAndOutSum
										.getReceiveInTax().add(sideFlag.multiply(scmInvMaterialReqBillEntry.getTaxAmt()
												.subtract(scmInvMaterialReqBillEntry.getAmt()))));
								existScmCostItemInAndOutSum
										.setReceiveInTaxAmt(existScmCostItemInAndOutSum.getReceiveInTaxAmt()
												.add(sideFlag.multiply(scmInvMaterialReqBillEntry.getTaxAmt())));
								existScmCostItemInAndOutSum.setAddInAvgPrice(
										BigDecimal.ZERO.compareTo(existScmCostItemInAndOutSum.getAddInQty()) == 0
												? BigDecimal.ZERO
												: existScmCostItemInAndOutSum.getAddInAmt().divide(
														existScmCostItemInAndOutSum.getAddInQty(), 2,
														BigDecimal.ROUND_HALF_UP));
								existScmCostItemInAndOutSum.setAddInTax(existScmCostItemInAndOutSum.getAddInTax()
										.add(sideFlag.multiply(scmInvMaterialReqBillEntry.getTaxAmt()
												.subtract(scmInvMaterialReqBillEntry.getAmt()))));
								existScmCostItemInAndOutSum.setAddInTaxAmt(existScmCostItemInAndOutSum.getAddInTaxAmt()
										.add(sideFlag.multiply(scmInvMaterialReqBillEntry.getTaxAmt())));
								if (costCenterTypeMap.containsKey(scmInvMaterialReqBillEntry.getCostOrgUnitNo())) {
									if (StringUtils.equals("1", costCenterTypeMap.get(scmInvMaterialReqBillEntry.getCostOrgUnitNo()))) {
										// 以领代耗时也需写出库数
										existScmCostItemInAndOutSum.setOutQty(existScmCostItemInAndOutSum.getOutQty()
												.add(sideFlag.multiply(scmInvMaterialReqBillEntry.getQty())));
										existScmCostItemInAndOutSum.setOutAmt(existScmCostItemInAndOutSum.getOutAmt()
												.add(sideFlag.multiply(scmInvMaterialReqBillEntry.getAmt())));
										existScmCostItemInAndOutSum.setOutAvgPrice(
												BigDecimal.ZERO.compareTo(existScmCostItemInAndOutSum.getOutQty()) == 0
														? BigDecimal.ZERO
														: existScmCostItemInAndOutSum.getOutAmt().divide(
																existScmCostItemInAndOutSum.getOutQty(), 2,
																BigDecimal.ROUND_HALF_UP));
										existScmCostItemInAndOutSum.setOutTax(existScmCostItemInAndOutSum.getOutTax()
												.add(sideFlag.multiply(scmInvMaterialReqBillEntry.getTaxAmt()
														.subtract(scmInvMaterialReqBillEntry.getAmt()))));
										existScmCostItemInAndOutSum.setOutTaxAmt(existScmCostItemInAndOutSum.getOutTaxAmt()
												.add(sideFlag.multiply(scmInvMaterialReqBillEntry.getTaxAmt())));
									}
								}
								exists = true;
								break;
							}
						}
					}
					if (!exists) {
						// 放入明细记录结果集中
						ScmCostItemInAndOutSum scmCostItemInAndOutSum = new ScmCostItemInAndOutSum(true);
						scmCostItemInAndOutSum.setItemId(scmInvMaterialReqBillEntry.getItemId());
						scmCostItemInAndOutSum.setItemNo(scmInvMaterialReqBillEntry.getItemNo());
						scmCostItemInAndOutSum.setItemName(scmInvMaterialReqBillEntry.getItemName());
						scmCostItemInAndOutSum.setSpec(scmInvMaterialReqBillEntry.getSpec());
						scmCostItemInAndOutSum.setGroupId(scmInvMaterialReqBillEntry.getGroupId());
						scmCostItemInAndOutSum.setClassCode(scmInvMaterialReqBillEntry.getGroupCode());
						scmCostItemInAndOutSum.setClassName(scmInvMaterialReqBillEntry.getGroupName());
						scmCostItemInAndOutSum.setLongNo(scmInvMaterialReqBillEntry.getLongNo());
						scmCostItemInAndOutSum.setUnitName(scmInvMaterialReqBillEntry.getUnitName());
						scmCostItemInAndOutSum.setUnit(scmInvMaterialReqBillEntry.getUnit());
						scmCostItemInAndOutSum.setCostOrgUnitNo(scmInvMaterialReqBillEntry.getCostOrgUnitNo());
						scmCostItemInAndOutSum.setAddInQty(sideFlag.multiply(scmInvMaterialReqBillEntry.getQty()));
						scmCostItemInAndOutSum.setAddInAmt(sideFlag.multiply(scmInvMaterialReqBillEntry.getAmt()));
						scmCostItemInAndOutSum.setAddInAvgPrice(
								BigDecimal.ZERO.compareTo(scmCostItemInAndOutSum.getAddInQty()) == 0 ? BigDecimal.ZERO
										: scmCostItemInAndOutSum.getAddInAmt().divide(
												scmCostItemInAndOutSum.getAddInQty(), 2, BigDecimal.ROUND_HALF_UP));
						scmCostItemInAndOutSum.setAddInTax(sideFlag.multiply(
								scmInvMaterialReqBillEntry.getTaxAmt().subtract(scmInvMaterialReqBillEntry.getAmt())));
						scmCostItemInAndOutSum
								.setAddInTaxAmt(sideFlag.multiply(scmInvMaterialReqBillEntry.getTaxAmt()));
						scmCostItemInAndOutSum.setReceiveInQty(sideFlag.multiply(scmInvMaterialReqBillEntry.getQty()));
						scmCostItemInAndOutSum.setReceiveInAvgPrice(
								BigDecimal.ZERO.compareTo(scmCostItemInAndOutSum.getReceiveInQty()) == 0 ? BigDecimal.ZERO
										: scmCostItemInAndOutSum.getReceiveInAmt().divide(
												scmCostItemInAndOutSum.getReceiveInQty(), 2, BigDecimal.ROUND_HALF_UP));
						scmCostItemInAndOutSum.setReceiveInAmt(sideFlag.multiply(scmInvMaterialReqBillEntry.getAmt()));
						scmCostItemInAndOutSum.setReceiveInTax(sideFlag.multiply(
								scmInvMaterialReqBillEntry.getTaxAmt().subtract(scmInvMaterialReqBillEntry.getAmt())));
						scmCostItemInAndOutSum.setReceiveInTaxAmt(sideFlag.multiply(scmInvMaterialReqBillEntry.getTaxAmt()));
						
						if (costCenterTypeMap.containsKey(scmInvMaterialReqBillEntry.getCostOrgUnitNo())) {
							if (StringUtils.equals("1", costCenterTypeMap.get(scmInvMaterialReqBillEntry.getCostOrgUnitNo()))) {
								// 以领代耗时也需写出库数
								scmCostItemInAndOutSum.setOutQty(sideFlag.multiply(scmInvMaterialReqBillEntry.getQty()));
								scmCostItemInAndOutSum.setOutAmt(sideFlag.multiply(scmInvMaterialReqBillEntry.getAmt()));
								scmCostItemInAndOutSum.setOutAvgPrice(
										BigDecimal.ZERO.compareTo(scmCostItemInAndOutSum.getOutQty()) == 0 ? BigDecimal.ZERO
												: scmCostItemInAndOutSum.getOutAmt().divide(
														scmCostItemInAndOutSum.getOutQty(), 2, BigDecimal.ROUND_HALF_UP));
								scmCostItemInAndOutSum.setOutTax(sideFlag.multiply(scmInvMaterialReqBillEntry.getTaxAmt()
										.subtract(scmInvMaterialReqBillEntry.getAmt())));
								scmCostItemInAndOutSum
										.setOutTaxAmt(sideFlag.multiply(scmInvMaterialReqBillEntry.getTaxAmt()));
							}
						}
						scmCostItemInAndOutSumList.add(scmCostItemInAndOutSum);
					}
				}
			}
			long dataEndTime4 = System.currentTimeMillis();
			log.info("领料出库明细加工耗时："+(dataEndTime4-dataEndTime3)+"毫秒");
		}
		
//		获取销售出库数据
		long beginTimeInvSale = System.currentTimeMillis();
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
							+ ScmInvSaleIssueBillEntry2.FN_ITEMID, new QueryParam(
									ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBillEntry2.class) + "."
											+ ScmInvSaleIssueBillEntry2.FN_ITEMID,
									QueryParam.QUERY_IN, sbMaterila.toString()));
		}
		page.getParamOr()
				.put(ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBillEntry.class) + "." + ScmInvSaleIssueBillEntry.FN_OUTCOSTORGUNITNO,
						new QueryParam(
								ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBillEntry.class) + "."
										+ ScmInvSaleIssueBillEntry.FN_OUTCOSTORGUNITNO,
								QueryParam.QUERY_IN, costOrgUnitNos.toString()));
		page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBill.class) + "." + ScmInvSaleIssueBill.FN_BIZDATE,
				new QueryParam(
						ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBill.class) + "." + ScmInvSaleIssueBill.FN_BIZDATE,
						QueryParam.QUERY_BETWEEN, FormatUtils.fmtDate(periodCalendar.getStartDate()), endDate));
		if (!StringUtils.equals("Y", status)) {
			page.getParam()
					.put(ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBill.class) + "."
							+ ScmInvSaleIssueBill.FN_STATUS,
					new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBill.class) + "."
							+ ScmInvSaleIssueBill.FN_STATUS, QueryParam.QUERY_EQ, "E"));
		}
		List<ScmInvSaleIssueBillEntry2> scmInvSaleIssueBillEntry2s = scmInvSaleIssueBillEntryBiz.queryPage(page,
				arglist, "findAllPage", param);
		long endTimeInvSale = System.currentTimeMillis();
		log.info("销售出库明细查询耗时："+(endTimeInvSale-beginTimeInvSale)+"毫秒");
		if(scmInvSaleIssueBillEntry2s != null && !scmInvSaleIssueBillEntry2s.isEmpty()) {
			log.info("销售出库明细查询结果："+(scmInvSaleIssueBillEntry2s.size())+"条记录");
			for(ScmInvSaleIssueBillEntry2 scmInvSaleIssueBillEntry : scmInvSaleIssueBillEntry2s) {
				BigDecimal sideFlag = BigDecimal.ONE;
				if(StringUtils.contains("1,2,3",scmInvSaleIssueBillEntry.getBizType())) {
					sideFlag = new BigDecimal("-1");
				}
				if(scmInvSaleIssueBillEntry.getBizDate().compareTo(FormatUtils.parseDate(beginDate)) < 0) {
					if (costCenterTypeMap.containsKey(scmInvSaleIssueBillEntry.getOutCostOrgUnitNo())) {
						if (StringUtils.equals("1",costCenterTypeMap.get(scmInvSaleIssueBillEntry.getOutCostOrgUnitNo()))) {
							continue;
						}
					}
					// 比查询日期小的统计到期初数据中，并从List中删除
					boolean exists = false;
					if (!scmCostItemInAndOutSumList.isEmpty()) {
						for (ScmCostItemInAndOutSum existScmCostItemInAndOutSum : scmCostItemInAndOutSumList) {
							if (existScmCostItemInAndOutSum.getItemId() == scmInvSaleIssueBillEntry.getItemId()
									&& StringUtils.equals(existScmCostItemInAndOutSum.getCostOrgUnitNo(),
											scmInvSaleIssueBillEntry.getOutCostOrgUnitNo())) {
								// 期间 数据存在   出库：减期初数据 
								existScmCostItemInAndOutSum.setInitQty(existScmCostItemInAndOutSum.getInitQty()
										.add(sideFlag.multiply(scmInvSaleIssueBillEntry.getQty())));
								existScmCostItemInAndOutSum.setInitAmt(existScmCostItemInAndOutSum.getInitAmt()
										.add(sideFlag.multiply(scmInvSaleIssueBillEntry.getAmt())));
								existScmCostItemInAndOutSum.setInitAvgPrice(
										BigDecimal.ZERO.compareTo(existScmCostItemInAndOutSum.getInitQty()) == 0
												? BigDecimal.ZERO
												: existScmCostItemInAndOutSum.getInitAmt().divide(
														existScmCostItemInAndOutSum.getInitQty(), 2,
														BigDecimal.ROUND_HALF_UP));
								existScmCostItemInAndOutSum.setInitTax(existScmCostItemInAndOutSum.getInitTax()
										.add(sideFlag.multiply(scmInvSaleIssueBillEntry.getTaxAmt()
												.subtract(scmInvSaleIssueBillEntry.getAmt()))));
								existScmCostItemInAndOutSum.setInitTaxAmt(existScmCostItemInAndOutSum.getInitTaxAmt()
										.add(sideFlag.multiply(scmInvSaleIssueBillEntry.getTaxAmt())));
								exists = true;
								break;
							}
						}
					}
//					期间数据不存在
					if (!exists) {
						ScmCostItemInAndOutSum scmCostItemInAndOutSum = new ScmCostItemInAndOutSum(true);
						scmCostItemInAndOutSum.setItemId(scmInvSaleIssueBillEntry.getItemId());
						scmCostItemInAndOutSum.setItemNo(scmInvSaleIssueBillEntry.getItemNo());
						scmCostItemInAndOutSum.setItemName(scmInvSaleIssueBillEntry.getItemName());
						scmCostItemInAndOutSum.setSpec(scmInvSaleIssueBillEntry.getSpec());
						scmCostItemInAndOutSum.setGroupId(scmInvSaleIssueBillEntry.getGroupId());
//						scmCostItemInAndOutSum.setClassCode(scmInvSaleIssueBillEntry.getGroupCode());
						scmCostItemInAndOutSum.setClassName(scmInvSaleIssueBillEntry.getGroupName());
						scmCostItemInAndOutSum.setLongNo(scmInvSaleIssueBillEntry.getLongNo());
						scmCostItemInAndOutSum.setUnitName(scmInvSaleIssueBillEntry.getUnitName());
						scmCostItemInAndOutSum.setUnit(scmInvSaleIssueBillEntry.getUnit());
						scmCostItemInAndOutSum.setCostOrgUnitNo(scmInvSaleIssueBillEntry.getOutCostOrgUnitNo());
						scmCostItemInAndOutSum.setInitQty(sideFlag.multiply(scmInvSaleIssueBillEntry.getQty()));
						scmCostItemInAndOutSum.setInitAmt(sideFlag.multiply(scmInvSaleIssueBillEntry.getAmt()));
						scmCostItemInAndOutSum.setInitAvgPrice(
								BigDecimal.ZERO.compareTo(scmCostItemInAndOutSum.getInitQty()) == 0 ? BigDecimal.ZERO
										: scmCostItemInAndOutSum.getInitAmt().divide(
												scmCostItemInAndOutSum.getInitQty(), 2, BigDecimal.ROUND_HALF_UP));
						scmCostItemInAndOutSum.setInitTax(sideFlag.multiply(
								scmInvSaleIssueBillEntry.getTaxAmt().subtract(scmInvSaleIssueBillEntry.getAmt())));
						scmCostItemInAndOutSum.setInitTaxAmt(sideFlag.multiply(scmInvSaleIssueBillEntry.getTaxAmt()));
						scmCostItemInAndOutSumList.add(scmCostItemInAndOutSum);
					}
				}else {
					boolean exists = false;
					if (!scmCostItemInAndOutSumList.isEmpty()) {
						for (ScmCostItemInAndOutSum existScmCostItemInAndOutSum : scmCostItemInAndOutSumList) {
							if (existScmCostItemInAndOutSum.getItemId() == scmInvSaleIssueBillEntry.getItemId()
									&& StringUtils.equals(existScmCostItemInAndOutSum.getCostOrgUnitNo(),
											scmInvSaleIssueBillEntry.getOutCostOrgUnitNo())) {
//								出库：AddInQty 减
								existScmCostItemInAndOutSum.setAddInQty(existScmCostItemInAndOutSum.getAddInQty().add(sideFlag.multiply(scmInvSaleIssueBillEntry.getQty())));
								existScmCostItemInAndOutSum.setAddInAmt(existScmCostItemInAndOutSum.getAddInAmt().add(sideFlag.multiply(scmInvSaleIssueBillEntry.getAmt())));
								existScmCostItemInAndOutSum.setInvSaleOutQty(existScmCostItemInAndOutSum.getInvSaleOutQty().add(sideFlag.multiply(scmInvSaleIssueBillEntry.getQty())));
								existScmCostItemInAndOutSum.setInvSaleOutAvgPrice(
										BigDecimal.ZERO.compareTo(existScmCostItemInAndOutSum.getInvSaleOutQty()) == 0
												? BigDecimal.ZERO
												: existScmCostItemInAndOutSum.getInvSaleOutTaxAmt().divide(
														existScmCostItemInAndOutSum.getInvSaleOutQty(), 2,
														BigDecimal.ROUND_HALF_UP));
								existScmCostItemInAndOutSum.setInvSaleOutAmt(existScmCostItemInAndOutSum.getInvSaleOutAmt().add(sideFlag.multiply(scmInvSaleIssueBillEntry.getAmt())));
								existScmCostItemInAndOutSum.setInvSaleOutTax(existScmCostItemInAndOutSum.getInvSaleOutTax().add(sideFlag.multiply(scmInvSaleIssueBillEntry.getTaxAmt().subtract(scmInvSaleIssueBillEntry.getAmt()))));
								existScmCostItemInAndOutSum.setInvSaleOutTaxAmt(existScmCostItemInAndOutSum.getInvSaleOutTaxAmt().add(sideFlag.multiply(scmInvSaleIssueBillEntry.getTaxAmt())));
								existScmCostItemInAndOutSum.setAddInAvgPrice(
										BigDecimal.ZERO.compareTo(existScmCostItemInAndOutSum.getAddInQty()) == 0
												? BigDecimal.ZERO
												: existScmCostItemInAndOutSum.getAddInAmt().divide(
														existScmCostItemInAndOutSum.getAddInQty(), 2,
														BigDecimal.ROUND_HALF_UP));
								existScmCostItemInAndOutSum.setAddInTax(existScmCostItemInAndOutSum.getAddInTax().add(sideFlag.multiply(scmInvSaleIssueBillEntry.getTaxAmt().subtract(scmInvSaleIssueBillEntry.getAmt()))));
								existScmCostItemInAndOutSum.setAddInTaxAmt(existScmCostItemInAndOutSum.getAddInTaxAmt().add(sideFlag.multiply(scmInvSaleIssueBillEntry.getTaxAmt())));
								if (costCenterTypeMap.containsKey(scmInvSaleIssueBillEntry.getOutCostOrgUnitNo())) {
									if (StringUtils.equals("1", costCenterTypeMap.get(scmInvSaleIssueBillEntry.getOutCostOrgUnitNo()))) {
										// 以领代耗时也需写出库数 OutQty 减 
										existScmCostItemInAndOutSum.setOutQty(existScmCostItemInAndOutSum.getOutQty().add(sideFlag.multiply(scmInvSaleIssueBillEntry.getQty())));
										existScmCostItemInAndOutSum.setOutAmt(existScmCostItemInAndOutSum.getOutAmt().add(sideFlag.multiply(scmInvSaleIssueBillEntry.getAmt())));
										existScmCostItemInAndOutSum.setOutAvgPrice(
												BigDecimal.ZERO.compareTo(existScmCostItemInAndOutSum.getOutQty()) == 0
														? BigDecimal.ZERO
														: existScmCostItemInAndOutSum.getOutAmt().divide(
																existScmCostItemInAndOutSum.getOutQty(), 2,
																BigDecimal.ROUND_HALF_UP));
										existScmCostItemInAndOutSum.setOutTax(existScmCostItemInAndOutSum.getOutTax().add(sideFlag.multiply(scmInvSaleIssueBillEntry.getTaxAmt().subtract(scmInvSaleIssueBillEntry.getAmt()))));
										existScmCostItemInAndOutSum.setOutTaxAmt(existScmCostItemInAndOutSum.getOutTaxAmt().add(sideFlag.multiply(scmInvSaleIssueBillEntry.getTaxAmt())));
									}
								}
								exists = true;
								break;
							}
						}
					}
					if (!exists) {
						// 放入明细记录结果集中
						ScmCostItemInAndOutSum scmCostItemInAndOutSum = new ScmCostItemInAndOutSum(true);
						scmCostItemInAndOutSum.setItemId(scmInvSaleIssueBillEntry.getItemId());
						scmCostItemInAndOutSum.setItemNo(scmInvSaleIssueBillEntry.getItemNo());
						scmCostItemInAndOutSum.setItemName(scmInvSaleIssueBillEntry.getItemName());
						scmCostItemInAndOutSum.setSpec(scmInvSaleIssueBillEntry.getSpec());
						scmCostItemInAndOutSum.setGroupId(scmInvSaleIssueBillEntry.getGroupId());
//						scmCostItemInAndOutSum.setClassCode(scmInvSaleIssueBillEntry.getGroupCode());
						scmCostItemInAndOutSum.setClassName(scmInvSaleIssueBillEntry.getGroupName());
						scmCostItemInAndOutSum.setLongNo(scmInvSaleIssueBillEntry.getLongNo());
						scmCostItemInAndOutSum.setUnitName(scmInvSaleIssueBillEntry.getUnitName());
						scmCostItemInAndOutSum.setUnit(scmInvSaleIssueBillEntry.getUnit());
						scmCostItemInAndOutSum.setCostOrgUnitNo(scmInvSaleIssueBillEntry.getOutCostOrgUnitNo());
						scmCostItemInAndOutSum.setAddInQty(sideFlag.multiply(scmInvSaleIssueBillEntry.getQty()));
						scmCostItemInAndOutSum.setAddInAmt(sideFlag.multiply(scmInvSaleIssueBillEntry.getAmt()));
						scmCostItemInAndOutSum.setAddInAvgPrice(
								BigDecimal.ZERO.compareTo(scmCostItemInAndOutSum.getAddInQty()) == 0 ? BigDecimal.ZERO
										: scmCostItemInAndOutSum.getAddInAmt().divide(
												scmCostItemInAndOutSum.getAddInQty(), 2, BigDecimal.ROUND_HALF_UP));
						scmCostItemInAndOutSum.setAddInTax(sideFlag.multiply(
								scmInvSaleIssueBillEntry.getTaxAmt().subtract(scmInvSaleIssueBillEntry.getAmt())));
						scmCostItemInAndOutSum
								.setAddInTaxAmt(sideFlag.multiply(scmInvSaleIssueBillEntry.getTaxAmt()));
						scmCostItemInAndOutSum.setInvSaleOutQty(sideFlag.multiply(scmInvSaleIssueBillEntry.getQty()));
						scmCostItemInAndOutSum.setInvSaleOutAvgPrice(
								BigDecimal.ZERO.compareTo(scmCostItemInAndOutSum.getInvSaleOutQty()) == 0 ? BigDecimal.ZERO
										: scmCostItemInAndOutSum.getInvSaleOutAmt().divide(
												scmCostItemInAndOutSum.getInvSaleOutQty(), 2, BigDecimal.ROUND_HALF_UP));
						scmCostItemInAndOutSum.setInvSaleOutAmt(sideFlag.multiply(scmInvSaleIssueBillEntry.getAmt()));
						scmCostItemInAndOutSum.setInvSaleOutTax(sideFlag.multiply(
								scmInvSaleIssueBillEntry.getTaxAmt().subtract(scmInvSaleIssueBillEntry.getAmt())));
						scmCostItemInAndOutSum.setInvSaleOutTaxAmt(sideFlag.multiply(scmInvSaleIssueBillEntry.getTaxAmt()));
						
						if (costCenterTypeMap.containsKey(scmInvSaleIssueBillEntry.getOutCostOrgUnitNo())) {
							if (StringUtils.equals("1", costCenterTypeMap.get(scmInvSaleIssueBillEntry.getOutCostOrgUnitNo()))) {
								// 以领代耗时也需写出库数
								scmCostItemInAndOutSum.setOutQty(sideFlag.multiply(scmInvSaleIssueBillEntry.getQty()));
								scmCostItemInAndOutSum.setOutAmt(sideFlag.multiply(scmInvSaleIssueBillEntry.getAmt()));
								scmCostItemInAndOutSum.setOutAvgPrice(
										BigDecimal.ZERO.compareTo(scmCostItemInAndOutSum.getOutQty()) == 0 ? BigDecimal.ZERO
												: scmCostItemInAndOutSum.getOutAmt().divide(
														scmCostItemInAndOutSum.getOutQty(), 2, BigDecimal.ROUND_HALF_UP));
								scmCostItemInAndOutSum.setOutTax(sideFlag.multiply(scmInvSaleIssueBillEntry.getTaxAmt()
										.subtract(scmInvSaleIssueBillEntry.getAmt())));
								scmCostItemInAndOutSum
										.setOutTaxAmt(sideFlag.multiply(scmInvSaleIssueBillEntry.getTaxAmt()));
							}
						}
						scmCostItemInAndOutSumList.add(scmCostItemInAndOutSum);
					}
				}
			}
			long endTimeInvSale2 = System.currentTimeMillis();
			log.info("销售出库明细加工耗时："+(endTimeInvSale2-endTimeInvSale)+"毫秒");
		}
		
		
		long beginTime3 = System.currentTimeMillis();
		// 获取耗用数据
		page = new Page();
		page.setModelClass(ScmInvCostConsumeEntry2.class);
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
					.put(ClassUtils.getFinalModelSimpleName(ScmInvCostConsumeEntry2.class) + "."
							+ ScmInvCostConsumeEntry2.FN_ITEMID, new QueryParam(
									ClassUtils.getFinalModelSimpleName(ScmInvCostConsumeEntry2.class) + "."
											+ ScmInvCostConsumeEntry2.FN_ITEMID,
									QueryParam.QUERY_IN, sbMaterila.toString()));
		}
		page.getParam()
				.put(ClassUtils.getFinalModelSimpleName(ScmInvCostConsume.class) + "." + ScmInvCostConsume.FN_ORGUNITNO,
						new QueryParam(
								ClassUtils.getFinalModelSimpleName(ScmInvCostConsume.class) + "."
										+ ScmInvCostConsume.FN_ORGUNITNO,
								QueryParam.QUERY_IN, costOrgUnitNos.toString()));
		
		page.getParam().put(
				ClassUtils.getFinalModelSimpleName(ScmInvCostConsume.class) + "." + ScmInvCostConsume.FN_BIZDATE,
				new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvCostConsume.class) + "."
						+ ScmInvCostConsume.FN_BIZDATE, QueryParam.QUERY_BETWEEN, FormatUtils.fmtDate(periodCalendar.getStartDate()), endDate));
		if (!StringUtils.equals("Y", status)) {
			page.getParam()
					.put(ClassUtils.getFinalModelSimpleName(ScmInvCostConsume.class) + "."
							+ ScmInvCostConsume.FN_STATUS,
					new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvCostConsume.class) + "."
							+ ScmInvCostConsume.FN_STATUS, QueryParam.QUERY_EQ, "E"));
		}
		List<ScmInvCostConsumeEntry2> scmInvCostConsumeEntryList = scmInvCostConsumeEntryBiz.queryPage(page, arglist,
				"findAllPage", param);
		long dataEndTime5 = System.currentTimeMillis();
		log.info("成本中心耗用明细查询耗时："+(dataEndTime5-beginTime3)+"毫秒");
		if (scmInvCostConsumeEntryList != null && !scmInvCostConsumeEntryList.isEmpty()) {
			log.info("成本中心耗用明细查询结果："+(scmInvCostConsumeEntryList.size())+"条记录");
			for (ScmInvCostConsumeEntry2 scmInvCostConsumeEntry : scmInvCostConsumeEntryList) {
//				OrgCostCenter2 orgCostCenter = orgCostCenterBiz
//						.selectByOrgUnitNo(scmInvCostConsumeEntry.getCostOrgUnitNo(), param);
//				if (orgCostCenter != null && StringUtils.equals("1", orgCostCenter.getCostCenterType())) {
//					continue;
//				}
				if (costCenterTypeMap.containsKey(scmInvCostConsumeEntry.getCostOrgUnitNo())) {
					if (StringUtils.equals("1",costCenterTypeMap.get(scmInvCostConsumeEntry.getCostOrgUnitNo()))) {
						continue;
					}
				}
				if (scmInvCostConsumeEntry.getBizDate().compareTo(FormatUtils.parseDate(beginDate)) < 0) {
					// 比查询日期小的统计到期初数据中，并从List中删除
					boolean exists = false;
					if (!scmCostItemInAndOutSumList.isEmpty()) {
						for (ScmCostItemInAndOutSum existScmCostItemInAndOutSum : scmCostItemInAndOutSumList) {
							if (existScmCostItemInAndOutSum.getItemId() == scmInvCostConsumeEntry.getItemId()
									&& StringUtils.equals(existScmCostItemInAndOutSum.getCostOrgUnitNo(),
											scmInvCostConsumeEntry.getCostOrgUnitNo())) {
								existScmCostItemInAndOutSum.setInitQty(existScmCostItemInAndOutSum.getInitQty()
										.subtract(scmInvCostConsumeEntry.getQty()));
								existScmCostItemInAndOutSum.setInitAmt(existScmCostItemInAndOutSum.getInitAmt()
										.subtract(scmInvCostConsumeEntry.getAmt()));
								existScmCostItemInAndOutSum.setInitAvgPrice(
										BigDecimal.ZERO.compareTo(existScmCostItemInAndOutSum.getInitQty()) == 0
												? BigDecimal.ZERO
												: existScmCostItemInAndOutSum.getInitAmt().divide(
														existScmCostItemInAndOutSum.getInitQty(), 2,
														BigDecimal.ROUND_HALF_UP));
								existScmCostItemInAndOutSum.setInitTax(
										existScmCostItemInAndOutSum.getInitTax().subtract(scmInvCostConsumeEntry
												.getTaxAmt().subtract(scmInvCostConsumeEntry.getAmt())));
								existScmCostItemInAndOutSum.setInitTaxAmt(existScmCostItemInAndOutSum.getInitTaxAmt()
										.subtract(scmInvCostConsumeEntry.getTaxAmt()));
								exists = true;
								break;
							}
						}
					}
					if (!exists) {
						ScmCostItemInAndOutSum scmCostItemInAndOutSum = new ScmCostItemInAndOutSum(true);
						scmCostItemInAndOutSum.setItemId(scmInvCostConsumeEntry.getItemId());
						scmCostItemInAndOutSum.setItemNo(scmInvCostConsumeEntry.getItemNo());
						scmCostItemInAndOutSum.setItemName(scmInvCostConsumeEntry.getItemName());
						scmCostItemInAndOutSum.setSpec(scmInvCostConsumeEntry.getSpec());
						scmCostItemInAndOutSum.setGroupId(scmInvCostConsumeEntry.getGroupId());
						scmCostItemInAndOutSum.setClassCode(scmInvCostConsumeEntry.getGroupCode());
						scmCostItemInAndOutSum.setClassName(scmInvCostConsumeEntry.getGroupName());
						scmCostItemInAndOutSum.setLongNo(scmInvCostConsumeEntry.getLongNo());
						scmCostItemInAndOutSum.setUnitName(scmInvCostConsumeEntry.getUnitName());
						scmCostItemInAndOutSum.setUnit(scmInvCostConsumeEntry.getUnit());
						scmCostItemInAndOutSum.setCostOrgUnitNo(scmInvCostConsumeEntry.getCostOrgUnitNo());
						scmCostItemInAndOutSum.setInitQty(BigDecimal.ZERO.subtract(scmInvCostConsumeEntry.getQty()));
						scmCostItemInAndOutSum.setInitAmt(BigDecimal.ZERO.subtract(scmInvCostConsumeEntry.getAmt()));
						scmCostItemInAndOutSum.setInitAvgPrice(
								BigDecimal.ZERO.compareTo(scmCostItemInAndOutSum.getInitQty()) == 0 ? BigDecimal.ZERO
										: scmCostItemInAndOutSum.getInitAmt().divide(
												scmCostItemInAndOutSum.getInitQty(), 2, BigDecimal.ROUND_HALF_UP));
						scmCostItemInAndOutSum.setInitTax(BigDecimal.ZERO.subtract(
								scmInvCostConsumeEntry.getTaxAmt().subtract(scmInvCostConsumeEntry.getAmt())));
						scmCostItemInAndOutSum
								.setInitTaxAmt(BigDecimal.ZERO.subtract(scmInvCostConsumeEntry.getTaxAmt()));
						scmCostItemInAndOutSumList.add(scmCostItemInAndOutSum);
					}
				} else {
					boolean exists = false;
					if (!scmCostItemInAndOutSumList.isEmpty()) {
						for (ScmCostItemInAndOutSum existScmCostItemInAndOutSum : scmCostItemInAndOutSumList) {
							if (existScmCostItemInAndOutSum.getItemId() == scmInvCostConsumeEntry.getItemId()
									&& StringUtils.equals(existScmCostItemInAndOutSum.getCostOrgUnitNo(),
											scmInvCostConsumeEntry.getCostOrgUnitNo())) {
								existScmCostItemInAndOutSum.setOutQty(
										existScmCostItemInAndOutSum.getOutQty().add(scmInvCostConsumeEntry.getQty()));
								existScmCostItemInAndOutSum.setOutAmt(
										existScmCostItemInAndOutSum.getOutAmt().add(scmInvCostConsumeEntry.getAmt()));
								existScmCostItemInAndOutSum.setConOutQty(existScmCostItemInAndOutSum.getConOutQty().add((scmInvCostConsumeEntry.getQty())));
								existScmCostItemInAndOutSum.setConOutAvgPrice(
										BigDecimal.ZERO.compareTo(existScmCostItemInAndOutSum.getOutQty().add(scmInvCostConsumeEntry.getQty())) == 0
												? BigDecimal.ZERO
												: existScmCostItemInAndOutSum.getConOutAmt().add((scmInvCostConsumeEntry.getAmt())).divide(
														existScmCostItemInAndOutSum.getOutQty().add(scmInvCostConsumeEntry.getQty()), 2,
														BigDecimal.ROUND_HALF_UP));
								existScmCostItemInAndOutSum.setConOutAmt(existScmCostItemInAndOutSum.getConOutAmt().add((scmInvCostConsumeEntry.getAmt())));
								existScmCostItemInAndOutSum.setConOutTax(existScmCostItemInAndOutSum.getConOutTax().add((scmInvCostConsumeEntry.getTaxAmt().subtract(scmInvCostConsumeEntry.getAmt()))));
								existScmCostItemInAndOutSum.setConOutTaxAmt(existScmCostItemInAndOutSum.getConOutTaxAmt().add((scmInvCostConsumeEntry.getTaxAmt())));
								
								existScmCostItemInAndOutSum.setOutAvgPrice(
										BigDecimal.ZERO.compareTo(existScmCostItemInAndOutSum.getOutQty()) == 0
												? BigDecimal.ZERO
												: existScmCostItemInAndOutSum.getOutAmt().divide(
														existScmCostItemInAndOutSum.getOutQty(), 2,
														BigDecimal.ROUND_HALF_UP));
								existScmCostItemInAndOutSum.setOutTax(existScmCostItemInAndOutSum.getOutTax().add(
										scmInvCostConsumeEntry.getTaxAmt().subtract(scmInvCostConsumeEntry.getAmt())));
								existScmCostItemInAndOutSum.setOutTaxAmt(existScmCostItemInAndOutSum.getOutTaxAmt()
										.add(scmInvCostConsumeEntry.getTaxAmt()));
								exists = true;
								break;
							}
						}
					}
					if (!exists) {
						// 放入明细记录结果集中
						ScmCostItemInAndOutSum scmCostItemInAndOutSum = new ScmCostItemInAndOutSum(true);
						scmCostItemInAndOutSum.setItemId(scmInvCostConsumeEntry.getItemId());
						scmCostItemInAndOutSum.setItemNo(scmInvCostConsumeEntry.getItemNo());
						scmCostItemInAndOutSum.setItemName(scmInvCostConsumeEntry.getItemName());
						scmCostItemInAndOutSum.setSpec(scmInvCostConsumeEntry.getSpec());
						scmCostItemInAndOutSum.setGroupId(scmInvCostConsumeEntry.getGroupId());
						scmCostItemInAndOutSum.setClassCode(scmInvCostConsumeEntry.getGroupCode());
						scmCostItemInAndOutSum.setClassName(scmInvCostConsumeEntry.getGroupName());
						scmCostItemInAndOutSum.setLongNo(scmInvCostConsumeEntry.getLongNo());
						scmCostItemInAndOutSum.setUnitName(scmInvCostConsumeEntry.getUnitName());
						scmCostItemInAndOutSum.setUnit(scmInvCostConsumeEntry.getUnit());
						scmCostItemInAndOutSum.setCostOrgUnitNo(scmInvCostConsumeEntry.getCostOrgUnitNo());
						scmCostItemInAndOutSum.setOutQty(scmInvCostConsumeEntry.getQty());
						scmCostItemInAndOutSum.setOutAmt(scmInvCostConsumeEntry.getAmt());
						scmCostItemInAndOutSum.setConOutQty(scmInvCostConsumeEntry.getQty());
						scmCostItemInAndOutSum.setConOutAmt(scmInvCostConsumeEntry.getAmt());
						scmCostItemInAndOutSum.setConOutTax(scmInvCostConsumeEntry.getTaxAmt().subtract(scmInvCostConsumeEntry.getAmt()));
						scmCostItemInAndOutSum.setConOutTaxAmt(scmInvCostConsumeEntry.getTaxAmt());
						
						scmCostItemInAndOutSum.setOutAvgPrice(
								BigDecimal.ZERO.compareTo(scmCostItemInAndOutSum.getOutQty()) == 0 ? BigDecimal.ZERO
										: scmCostItemInAndOutSum.getOutAmt().divide(scmCostItemInAndOutSum.getOutQty(),
												2, BigDecimal.ROUND_HALF_UP));
						scmCostItemInAndOutSum.setConOutAvgPrice(
								BigDecimal.ZERO.compareTo(scmCostItemInAndOutSum.getConOutQty()) == 0 ? BigDecimal.ZERO
										: scmCostItemInAndOutSum.getConOutAmt().divide(scmCostItemInAndOutSum.getConOutQty(),
												2, BigDecimal.ROUND_HALF_UP));
						scmCostItemInAndOutSum.setOutTax(
								scmInvCostConsumeEntry.getTaxAmt().subtract(scmInvCostConsumeEntry.getAmt()));
						scmCostItemInAndOutSum.setOutTaxAmt(scmInvCostConsumeEntry.getTaxAmt());
						scmCostItemInAndOutSumList.add(scmCostItemInAndOutSum);
					}
				}
			}
			long dataEndTime6 = System.currentTimeMillis();
			log.info("成本中心耗用明细加工耗时："+(dataEndTime6-dataEndTime5)+"毫秒");
		}
		long beginTimeInventory = System.currentTimeMillis();
		// 盘存差异
		page = new Page();
		page.setModelClass(ScmInvCountingCostCenterEntry2.class);
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
					.put(ClassUtils.getFinalModelSimpleName(ScmInvCountingCostCenterEntry2.class) + "."
							+ ScmInvCountingCostCenterEntry2.FN_ITEMID,
					new QueryParam(
							ClassUtils.getFinalModelSimpleName(ScmInvCountingCostCenterEntry2.class) + "."
									+ ScmInvCountingCostCenterEntry2.FN_ITEMID,
							QueryParam.QUERY_IN, sbMaterila.toString()));
		}
		page.getParam()
				.put(ClassUtils.getFinalModelSimpleName(ScmInvCountingCostCenter.class) + "."
						+ ScmInvCountingCostCenter.FN_ORGUNITNO,
				new QueryParam(
						ClassUtils.getFinalModelSimpleName(ScmInvCountingCostCenter.class) + "."
								+ ScmInvCountingCostCenter.FN_ORGUNITNO,
						QueryParam.QUERY_IN, costOrgUnitNos.toString()));
		page.getParam().put(
				ClassUtils.getFinalModelSimpleName(ScmInvCountingTask.class) + "." + ScmInvCountingTask.FN_BIZDATE,
				new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvCountingTask.class) + "."
						+ ScmInvCountingTask.FN_BIZDATE, QueryParam.QUERY_BETWEEN, FormatUtils.fmtDate(periodCalendar.getStartDate()), endDate));
		page.getParam()
				.put(ClassUtils.getFinalModelSimpleName(ScmInvCountingCostCenterEntry2.class) + "."
						+ ScmInvCountingCostCenterEntry2.FN_DIFFERQTY,
				new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvCountingCostCenterEntry2.class) + "."
						+ ScmInvCountingCostCenterEntry2.FN_DIFFERQTY, QueryParam.QUERY_NE, "0"));
		if (!StringUtils.equals("Y", status)) {
			page.getParam()
					.put(ClassUtils.getFinalModelSimpleName(ScmInvCountingTask.class) + "."
							+ ScmInvCountingTask.FN_STATUS,
					new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvCountingTask.class) + "."
							+ ScmInvCountingTask.FN_STATUS, QueryParam.QUERY_EQ, "O"));
		} else {
			page.getParam()
					.put(ClassUtils.getFinalModelSimpleName(ScmInvCountingTask.class) + "."
							+ ScmInvCountingTask.FN_STATUS,
					new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvCountingTask.class) + "."
							+ ScmInvCountingTask.FN_STATUS, QueryParam.QUERY_IN, "'T','B','O'"));
		}
		List<ScmInvCountingCostCenterEntry2> scmInvCountingCostCenterEntryList = scmInvCountingCostCenterEntryBiz
				.queryPage(page, arglist, "findAllPage", param);
		long dataEndTimeInventory = System.currentTimeMillis();
		log.info("成本中心盘存差异明细查询耗时："+(dataEndTimeInventory-beginTimeInventory)+"毫秒");
		if (scmInvCountingCostCenterEntryList != null && !scmInvCountingCostCenterEntryList.isEmpty()) {
			log.info("成本中心盘存差异明细查询结果："+(scmInvCountingCostCenterEntryList.size())+"条记录");
			for (ScmInvCountingCostCenterEntry2 scmInvCountingCostCenterEntry : scmInvCountingCostCenterEntryList) {
//				OrgCostCenter2 orgCostCenter = orgCostCenterBiz
//						.selectByOrgUnitNo(scmInvCountingCostCenterEntry.getOrgUnitNo(), param);
//				if (orgCostCenter != null && StringUtils.equals("1", orgCostCenter.getCostCenterType())) {
//					continue;
//				}
				if (costCenterTypeMap.containsKey(scmInvCountingCostCenterEntry.getOrgUnitNo())) {
					if (StringUtils.equals("1",costCenterTypeMap.get(scmInvCountingCostCenterEntry.getOrgUnitNo()))) {
						continue;
					}
				}
				if (scmInvCountingCostCenterEntry.getBizDate().compareTo(FormatUtils.parseDate(beginDate)) < 0) {
					// 比查询日期小的统计到期初数据中，并从List中删除
					boolean exists = false;
					if (!scmCostItemInAndOutSumList.isEmpty()) {
						for (ScmCostItemInAndOutSum existScmCostItemInAndOutSum : scmCostItemInAndOutSumList) {
							if (existScmCostItemInAndOutSum.getItemId() == scmInvCountingCostCenterEntry.getItemId()
									&& StringUtils.equals(existScmCostItemInAndOutSum.getCostOrgUnitNo(),
											scmInvCountingCostCenterEntry.getOrgUnitNo())) {
								existScmCostItemInAndOutSum.setInitQty(existScmCostItemInAndOutSum.getInitQty()
										.add(scmInvCountingCostCenterEntry.getDifferQty()));
								existScmCostItemInAndOutSum.setInitAmt(existScmCostItemInAndOutSum.getInitAmt()
										.add(scmInvCountingCostCenterEntry.getDifferAmt()));
								existScmCostItemInAndOutSum.setInitAvgPrice(
										BigDecimal.ZERO.compareTo(existScmCostItemInAndOutSum.getInitQty()) == 0
												? BigDecimal.ZERO
												: existScmCostItemInAndOutSum.getInitAmt().divide(
														existScmCostItemInAndOutSum.getInitQty(), 2,
														BigDecimal.ROUND_HALF_UP));
								existScmCostItemInAndOutSum.setInitTax(existScmCostItemInAndOutSum.getInitTax()
										.add(scmInvCountingCostCenterEntry.getDifferTaxAmt()
												.subtract(scmInvCountingCostCenterEntry.getDifferAmt())));
								existScmCostItemInAndOutSum.setInitTaxAmt(existScmCostItemInAndOutSum.getInitTaxAmt()
										.add(scmInvCountingCostCenterEntry.getDifferTaxAmt()));
								exists = true;
								break;
							}
						}
					}
					if (!exists) {
						ScmCostItemInAndOutSum scmCostItemInAndOutSum = new ScmCostItemInAndOutSum(true);
						scmCostItemInAndOutSum.setItemId(scmInvCountingCostCenterEntry.getItemId());
						scmCostItemInAndOutSum.setItemNo(scmInvCountingCostCenterEntry.getItemNo());
						scmCostItemInAndOutSum.setItemName(scmInvCountingCostCenterEntry.getItemName());
						scmCostItemInAndOutSum.setSpec(scmInvCountingCostCenterEntry.getSpec());
						scmCostItemInAndOutSum.setGroupId(scmInvCountingCostCenterEntry.getGroupId());
						scmCostItemInAndOutSum.setClassCode(scmInvCountingCostCenterEntry.getGroupCode());
						scmCostItemInAndOutSum.setClassName(scmInvCountingCostCenterEntry.getGroupName());
						scmCostItemInAndOutSum.setLongNo(scmInvCountingCostCenterEntry.getLongNo());
						scmCostItemInAndOutSum.setUnitName(scmInvCountingCostCenterEntry.getUnitName());
						scmCostItemInAndOutSum.setUnit(scmInvCountingCostCenterEntry.getUnit());
						scmCostItemInAndOutSum.setCostOrgUnitNo(scmInvCountingCostCenterEntry.getOrgUnitNo());
						scmCostItemInAndOutSum.setInitQty(scmInvCountingCostCenterEntry.getDifferQty());
						scmCostItemInAndOutSum.setInitAmt(scmInvCountingCostCenterEntry.getDifferAmt());
						scmCostItemInAndOutSum.setInitAvgPrice(
								BigDecimal.ZERO.compareTo(scmCostItemInAndOutSum.getInitQty()) == 0 ? BigDecimal.ZERO
										: scmCostItemInAndOutSum.getInitAmt().divide(
												scmCostItemInAndOutSum.getInitQty(), 2, BigDecimal.ROUND_HALF_UP));
						scmCostItemInAndOutSum.setInitTax(scmInvCountingCostCenterEntry.getDifferTaxAmt()
								.subtract(scmInvCountingCostCenterEntry.getDifferAmt()));
						scmCostItemInAndOutSum.setInitTaxAmt(scmInvCountingCostCenterEntry.getDifferTaxAmt());
						scmCostItemInAndOutSumList.add(scmCostItemInAndOutSum);
					}
				} else {
					boolean exists = false;
					if (!scmCostItemInAndOutSumList.isEmpty()) {
						for (ScmCostItemInAndOutSum existScmCostItemInAndOutSum : scmCostItemInAndOutSumList) {
							if (existScmCostItemInAndOutSum.getItemId() == scmInvCountingCostCenterEntry.getItemId()
									&& StringUtils.equals(existScmCostItemInAndOutSum.getCostOrgUnitNo(),
											scmInvCountingCostCenterEntry.getOrgUnitNo())) {
								if (scmInvCountingCostCenterEntry.getDifferQty().compareTo(BigDecimal.ZERO) < 0) {
									existScmCostItemInAndOutSum.setOutQty(existScmCostItemInAndOutSum.getOutQty()
											.subtract(scmInvCountingCostCenterEntry.getDifferQty()));
									existScmCostItemInAndOutSum.setOutAmt(existScmCostItemInAndOutSum.getOutAmt()
											.subtract(scmInvCountingCostCenterEntry.getDifferAmt()));
									existScmCostItemInAndOutSum.setInvenOutQty(existScmCostItemInAndOutSum.getInvenOutQty().add((scmInvCountingCostCenterEntry.getDifferQty())));
									existScmCostItemInAndOutSum.setInvenOutAvgPrice(
											BigDecimal.ZERO.compareTo(existScmCostItemInAndOutSum.getInvenOutQty().add((scmInvCountingCostCenterEntry.getDifferQty()))) == 0
													? BigDecimal.ZERO
													: existScmCostItemInAndOutSum.getInvenOutAmt().add((scmInvCountingCostCenterEntry.getDifferAmt())).divide(
															existScmCostItemInAndOutSum.getInvenOutQty().add((scmInvCountingCostCenterEntry.getDifferQty())), 2,
															BigDecimal.ROUND_HALF_UP));
									existScmCostItemInAndOutSum.setInvenOutAmt(existScmCostItemInAndOutSum.getInvenOutAmt().add((scmInvCountingCostCenterEntry.getDifferAmt())));
									existScmCostItemInAndOutSum.setInvenOutTax(existScmCostItemInAndOutSum.getInvenOutTax().add((scmInvCountingCostCenterEntry.getDifferTaxAmt().subtract(scmInvCountingCostCenterEntry.getDifferAmt()))));
									existScmCostItemInAndOutSum.setInvenOutTaxAmt(existScmCostItemInAndOutSum.getInvenOutTaxAmt().add((scmInvCountingCostCenterEntry.getDifferTaxAmt())));
									existScmCostItemInAndOutSum.setOutAvgPrice(
											BigDecimal.ZERO.compareTo(existScmCostItemInAndOutSum.getOutQty()) == 0
													? BigDecimal.ZERO
													: existScmCostItemInAndOutSum.getOutAmt().divide(
															existScmCostItemInAndOutSum.getOutQty(), 2,
															BigDecimal.ROUND_HALF_UP));
									existScmCostItemInAndOutSum.setOutTax(existScmCostItemInAndOutSum.getOutTax()
											.subtract(scmInvCountingCostCenterEntry.getDifferTaxAmt()
													.subtract(scmInvCountingCostCenterEntry.getDifferAmt())));
									existScmCostItemInAndOutSum.setOutTaxAmt(existScmCostItemInAndOutSum.getOutTaxAmt()
											.subtract(scmInvCountingCostCenterEntry.getDifferTaxAmt()));
								} else {
									existScmCostItemInAndOutSum.setOutQty(existScmCostItemInAndOutSum.getOutQty()
											.subtract(scmInvCountingCostCenterEntry.getDifferQty()));
									existScmCostItemInAndOutSum.setOutAmt(existScmCostItemInAndOutSum.getOutAmt()
											.subtract(scmInvCountingCostCenterEntry.getDifferAmt()));
									existScmCostItemInAndOutSum.setOutAvgPrice(
											BigDecimal.ZERO.compareTo(existScmCostItemInAndOutSum.getOutQty()) == 0
													? BigDecimal.ZERO
													: existScmCostItemInAndOutSum.getOutAmt().divide(
															existScmCostItemInAndOutSum.getOutQty(), 2,
															BigDecimal.ROUND_HALF_UP));
									existScmCostItemInAndOutSum.setOutTax(existScmCostItemInAndOutSum.getOutTax()
											.subtract(scmInvCountingCostCenterEntry.getDifferTaxAmt()
													.subtract(scmInvCountingCostCenterEntry.getDifferAmt())));
									existScmCostItemInAndOutSum.setOutTaxAmt(existScmCostItemInAndOutSum.getOutTaxAmt()
											.subtract(scmInvCountingCostCenterEntry.getDifferTaxAmt()));
									existScmCostItemInAndOutSum.setInvenOutQty(existScmCostItemInAndOutSum.getInvenOutQty().add((scmInvCountingCostCenterEntry.getDifferQty())));
									existScmCostItemInAndOutSum.setInvenOutAvgPrice(
											BigDecimal.ZERO.compareTo(existScmCostItemInAndOutSum.getInvenOutQty().add((scmInvCountingCostCenterEntry.getDifferQty()))) == 0
													? BigDecimal.ZERO
													: existScmCostItemInAndOutSum.getInvenOutAmt().add((scmInvCountingCostCenterEntry.getDifferAmt())).divide(
															existScmCostItemInAndOutSum.getInvenOutQty().add((scmInvCountingCostCenterEntry.getDifferQty())), 2,
															BigDecimal.ROUND_HALF_UP));
									existScmCostItemInAndOutSum.setInvenOutAmt(existScmCostItemInAndOutSum.getInvenOutAmt().add((scmInvCountingCostCenterEntry.getDifferAmt())));
									existScmCostItemInAndOutSum.setInvenOutTax(existScmCostItemInAndOutSum.getInvenOutTax().add((scmInvCountingCostCenterEntry.getDifferTaxAmt().subtract(scmInvCountingCostCenterEntry.getDifferAmt()))));
									existScmCostItemInAndOutSum.setInvenOutTaxAmt(existScmCostItemInAndOutSum.getInvenOutTaxAmt().add((scmInvCountingCostCenterEntry.getDifferTaxAmt())));
								}
								exists = true;
								break;
							}
						}
					}
					if (!exists) {
						// 放入明细记录结果集中
						ScmCostItemInAndOutSum scmCostItemInAndOutSum = new ScmCostItemInAndOutSum(true);
						scmCostItemInAndOutSum.setItemId(scmInvCountingCostCenterEntry.getItemId());
						scmCostItemInAndOutSum.setItemNo(scmInvCountingCostCenterEntry.getItemNo());
						scmCostItemInAndOutSum.setItemName(scmInvCountingCostCenterEntry.getItemName());
						scmCostItemInAndOutSum.setSpec(scmInvCountingCostCenterEntry.getSpec());
						scmCostItemInAndOutSum.setGroupId(scmInvCountingCostCenterEntry.getGroupId());
						scmCostItemInAndOutSum.setClassCode(scmInvCountingCostCenterEntry.getGroupCode());
						scmCostItemInAndOutSum.setClassName(scmInvCountingCostCenterEntry.getGroupName());
						scmCostItemInAndOutSum.setLongNo(scmInvCountingCostCenterEntry.getLongNo());
						scmCostItemInAndOutSum.setUnitName(scmInvCountingCostCenterEntry.getUnitName());
						scmCostItemInAndOutSum.setUnit(scmInvCountingCostCenterEntry.getUnit());
						scmCostItemInAndOutSum.setCostOrgUnitNo(scmInvCountingCostCenterEntry.getOrgUnitNo());
						if (scmInvCountingCostCenterEntry.getDifferQty().compareTo(BigDecimal.ZERO) < 0) {
							scmCostItemInAndOutSum.setOutQty(BigDecimal.ZERO.subtract(scmInvCountingCostCenterEntry.getDifferQty()));
							scmCostItemInAndOutSum.setOutAmt(BigDecimal.ZERO.subtract(scmInvCountingCostCenterEntry.getDifferAmt()));
							scmCostItemInAndOutSum.setInvenOutQty(BigDecimal.ZERO.subtract(scmInvCountingCostCenterEntry.getDifferQty()));
							scmCostItemInAndOutSum.setInvenOutAvgPrice(
									BigDecimal.ZERO.compareTo(scmInvCountingCostCenterEntry.getDifferQty()) == 0 ? BigDecimal.ZERO
											: scmInvCountingCostCenterEntry.getDifferAmt().divide(
													scmInvCountingCostCenterEntry.getDifferQty(), 2, BigDecimal.ROUND_HALF_UP));
							scmCostItemInAndOutSum.setInvenOutAmt(BigDecimal.ZERO.subtract(scmInvCountingCostCenterEntry.getDifferAmt()));
							scmCostItemInAndOutSum.setInvenOutTax(scmInvCountingCostCenterEntry.getDifferAmt()
									.subtract(scmInvCountingCostCenterEntry.getDifferTaxAmt()));
							scmCostItemInAndOutSum.setInvenOutTaxAmt(BigDecimal.ZERO.subtract(scmInvCountingCostCenterEntry.getDifferAmt()));
						
							scmCostItemInAndOutSum.setOutAvgPrice(
									BigDecimal.ZERO.compareTo(scmCostItemInAndOutSum.getOutQty()) == 0 ? BigDecimal.ZERO
											: scmCostItemInAndOutSum.getOutAmt().divide(
													scmCostItemInAndOutSum.getOutQty(), 2, BigDecimal.ROUND_HALF_UP));
							scmCostItemInAndOutSum.setOutTax(scmInvCountingCostCenterEntry.getDifferAmt()
									.subtract(scmInvCountingCostCenterEntry.getDifferTaxAmt()));
							scmCostItemInAndOutSum.setOutTaxAmt(BigDecimal.ZERO.subtract(scmInvCountingCostCenterEntry.getDifferTaxAmt()));
						} else {
							scmCostItemInAndOutSum.setOutQty(BigDecimal.ZERO.subtract(scmInvCountingCostCenterEntry.getDifferQty()));
							scmCostItemInAndOutSum.setOutAmt(BigDecimal.ZERO.subtract(scmInvCountingCostCenterEntry.getDifferAmt()));
							scmCostItemInAndOutSum
									.setOutAvgPrice(
											BigDecimal.ZERO.compareTo(scmCostItemInAndOutSum.getOutQty()) == 0
													? BigDecimal.ZERO
													: scmCostItemInAndOutSum.getOutAmt().divide(
															scmCostItemInAndOutSum.getOutQty(), 2,
															BigDecimal.ROUND_HALF_UP));
							scmCostItemInAndOutSum.setOutTax(scmInvCountingCostCenterEntry.getDifferAmt()
									.subtract(scmInvCountingCostCenterEntry.getDifferTaxAmt()));
							scmCostItemInAndOutSum.setOutTaxAmt(BigDecimal.ZERO.subtract(scmInvCountingCostCenterEntry.getDifferTaxAmt()));
							scmCostItemInAndOutSum.setInvenOutQty(scmInvCountingCostCenterEntry.getDifferQty());
							scmCostItemInAndOutSum
							.setInvenOutAvgPrice(
									BigDecimal.ZERO.compareTo(scmInvCountingCostCenterEntry.getDifferQty()) == 0
											? BigDecimal.ZERO
											: scmInvCountingCostCenterEntry.getDifferAmt().divide(
													scmInvCountingCostCenterEntry.getDifferQty(), 2,
													BigDecimal.ROUND_HALF_UP));
							scmCostItemInAndOutSum.setInvenOutAmt(scmInvCountingCostCenterEntry.getDifferAmt());
							scmCostItemInAndOutSum.setInvenOutTax(scmInvCountingCostCenterEntry.getDifferTaxAmt()
									.subtract(scmInvCountingCostCenterEntry.getDifferAmt()));
							scmCostItemInAndOutSum.setInvenOutTaxAmt(scmInvCountingCostCenterEntry.getDifferAmt());
						}
						scmCostItemInAndOutSumList.add(scmCostItemInAndOutSum);
					}
				}
			}
			long dataEndTimeInventory1 = System.currentTimeMillis();
			log.info("成本中心盘存差异明细加工耗时："+(dataEndTimeInventory1-dataEndTimeInventory)+"毫秒");
		}
		 long beginTimeTransfer = System.currentTimeMillis();
		// 获取成本转移数据
		page = new Page();
		page.setModelClass(ScmInvMoveBillEntry2.class);
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
					.put(ClassUtils.getFinalModelSimpleName(ScmInvMoveBillEntry2.class) + "."
							+ ScmInvMoveBillEntry2.FN_ITEMID, new QueryParam(
									ClassUtils.getFinalModelSimpleName(ScmInvMoveBillEntry2.class) + "."
											+ ScmInvMoveBillEntry2.FN_ITEMID,
									QueryParam.QUERY_IN, sbMaterila.toString()));
		}
		page.getParamOr()
				.put(ClassUtils.getFinalModelSimpleName(ScmInvMoveBill.class) + "." + ScmInvMoveBill.FN_OUTCSTORGUNITNO,
						new QueryParam(
								ClassUtils.getFinalModelSimpleName(ScmInvMoveBill.class) + "."
										+ ScmInvMoveBill.FN_OUTCSTORGUNITNO,
								QueryParam.QUERY_IN, costOrgUnitNos.toString()));
		page.getParamOr()
				.put(ClassUtils.getFinalModelSimpleName(ScmInvMoveBill.class) + "." + ScmInvMoveBill.FN_INCSTORGUNITNO,
						new QueryParam(
								ClassUtils.getFinalModelSimpleName(ScmInvMoveBill.class) + "."
										+ ScmInvMoveBill.FN_INCSTORGUNITNO,
								QueryParam.QUERY_IN, costOrgUnitNos.toString()));
		page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvMoveBill.class) + "." + ScmInvMoveBill.FN_BIZDATE,
				new QueryParam(
						ClassUtils.getFinalModelSimpleName(ScmInvMoveBill.class) + "." + ScmInvMoveBill.FN_BIZDATE,
						QueryParam.QUERY_BETWEEN, FormatUtils.fmtDate(periodCalendar.getStartDate()), endDate));
		if (!StringUtils.equals("Y", status)) {
			page.getParam().put(
					ClassUtils.getFinalModelSimpleName(ScmInvMoveBill.class) + "." + ScmInvMoveBill.FN_STATUS,
					new QueryParam(
							ClassUtils.getFinalModelSimpleName(ScmInvMoveBill.class) + "." + ScmInvMoveBill.FN_STATUS,
							QueryParam.QUERY_EQ, "E"));
		}
		List<ScmInvMoveBillEntry2> scmInvMoveBillEntryList = scmInvMoveBillEntryBiz.queryPage(page, arglist,
				"findAllPage", param);
		long dataEndTimeTransfer = System.currentTimeMillis();
		log.info("成本转移明细查询耗时："+(dataEndTimeTransfer-beginTimeTransfer)+"毫秒");
		if (scmInvMoveBillEntryList != null && !scmInvMoveBillEntryList.isEmpty()) {
			log.info("成本转移明细查询结果："+(scmInvMoveBillEntryList.size())+"条记录");
			for (ScmInvMoveBillEntry2 scmInvMoveBillEntry : scmInvMoveBillEntryList) {
				if (scmInvMoveBillEntry.getBizDate().compareTo(FormatUtils.parseDate(beginDate)) < 0) {
					// 比查询日期小的统计到期初数据中
					if (StringUtils.contains(costOrgUnitNos.toString(), scmInvMoveBillEntry.getOutCstOrgUnitNo())) {
						// 调出
						boolean exists = false;
						if (!scmCostItemInAndOutSumList.isEmpty()) {
							for (ScmCostItemInAndOutSum existScmCostItemInAndOutSum : scmCostItemInAndOutSumList) {
								if (existScmCostItemInAndOutSum.getItemId() == scmInvMoveBillEntry.getItemId()
										&& StringUtils.equals(existScmCostItemInAndOutSum.getCostOrgUnitNo(),
												scmInvMoveBillEntry.getOutCstOrgUnitNo())) {
									existScmCostItemInAndOutSum.setInitQty(existScmCostItemInAndOutSum.getInitQty()
											.subtract(scmInvMoveBillEntry.getQty()));
									existScmCostItemInAndOutSum.setInitAmt(existScmCostItemInAndOutSum.getInitAmt()
											.subtract(scmInvMoveBillEntry.getAmt()));
									existScmCostItemInAndOutSum.setInitAvgPrice(
											BigDecimal.ZERO.compareTo(existScmCostItemInAndOutSum.getInitQty()) == 0
													? BigDecimal.ZERO
													: existScmCostItemInAndOutSum.getInitAmt().divide(
															existScmCostItemInAndOutSum.getInitQty(), 2,
															BigDecimal.ROUND_HALF_UP));
									existScmCostItemInAndOutSum.setInitTax(
											existScmCostItemInAndOutSum.getInitTax().subtract(scmInvMoveBillEntry
													.getTaxAmt().subtract(scmInvMoveBillEntry.getAmt())));
									existScmCostItemInAndOutSum.setInitTaxAmt(existScmCostItemInAndOutSum
											.getInitTaxAmt().subtract(scmInvMoveBillEntry.getTaxAmt()));
									exists = true;
									break;
								}
							}
						}
						if (!exists) {
							ScmCostItemInAndOutSum scmCostItemInAndOutSum = new ScmCostItemInAndOutSum(true);
							scmCostItemInAndOutSum.setItemId(scmInvMoveBillEntry.getItemId());
							scmCostItemInAndOutSum.setItemNo(scmInvMoveBillEntry.getItemNo());
							scmCostItemInAndOutSum.setItemName(scmInvMoveBillEntry.getItemName());
							scmCostItemInAndOutSum.setSpec(scmInvMoveBillEntry.getSpec());
							scmCostItemInAndOutSum.setGroupId(scmInvMoveBillEntry.getGroupId());
							scmCostItemInAndOutSum.setClassCode(scmInvMoveBillEntry.getGroupCode());
							scmCostItemInAndOutSum.setClassName(scmInvMoveBillEntry.getGroupName());
							scmCostItemInAndOutSum.setLongNo(scmInvMoveBillEntry.getLongNo());
							scmCostItemInAndOutSum.setUnitName(scmInvMoveBillEntry.getUnitName());
							scmCostItemInAndOutSum.setUnit(scmInvMoveBillEntry.getUnit());
							scmCostItemInAndOutSum.setCostOrgUnitNo(scmInvMoveBillEntry.getOutCstOrgUnitNo());
							scmCostItemInAndOutSum.setInitQty(BigDecimal.ZERO.subtract(scmInvMoveBillEntry.getQty()));
							scmCostItemInAndOutSum.setInitAmt(BigDecimal.ZERO.subtract(scmInvMoveBillEntry.getAmt()));
							scmCostItemInAndOutSum
									.setInitAvgPrice(
											BigDecimal.ZERO.compareTo(scmCostItemInAndOutSum.getInitQty()) == 0
													? BigDecimal.ZERO
													: scmCostItemInAndOutSum.getInitAmt().divide(
															scmCostItemInAndOutSum.getInitQty(), 2,
															BigDecimal.ROUND_HALF_UP));
							scmCostItemInAndOutSum.setInitTax(BigDecimal.ZERO
									.subtract(scmInvMoveBillEntry.getTaxAmt().subtract(scmInvMoveBillEntry.getAmt())));
							scmCostItemInAndOutSum
									.setInitTaxAmt(BigDecimal.ZERO.subtract(scmInvMoveBillEntry.getTaxAmt()));
							scmCostItemInAndOutSumList.add(scmCostItemInAndOutSum);
						}
					}
					if (StringUtils.contains(costOrgUnitNos.toString(), scmInvMoveBillEntry.getInCstOrgUnitNo())) {
						if (StringUtils.contains(costOrgUnitNos.toString(), scmInvMoveBillEntry.getInCstOrgUnitNo())) {
							if (StringUtils.equals("1",costCenterTypeMap.get(scmInvMoveBillEntry.getInCstOrgUnitNo()))) {
								continue;
							}
						}
						
//						OrgCostCenter2 orgCostCenter = orgCostCenterBiz
//								.selectByOrgUnitNo(scmInvMoveBillEntry.getInCstOrgUnitNo(), param);
//						
//						if (orgCostCenter != null && StringUtils.equals("1", orgCostCenter.getCostCenterType())) {
//							continue;
//						}
						// 调入
						boolean exists = false;
						if (!scmCostItemInAndOutSumList.isEmpty()) {
							for (ScmCostItemInAndOutSum existScmCostItemInAndOutSum : scmCostItemInAndOutSumList) {
								if (existScmCostItemInAndOutSum.getItemId() == scmInvMoveBillEntry.getItemId()
										&& StringUtils.equals(existScmCostItemInAndOutSum.getCostOrgUnitNo(),
												scmInvMoveBillEntry.getInCstOrgUnitNo())) {
									existScmCostItemInAndOutSum.setInitQty(
											existScmCostItemInAndOutSum.getInitQty().add(scmInvMoveBillEntry.getQty()));
									existScmCostItemInAndOutSum.setInitAmt(
											existScmCostItemInAndOutSum.getInitAmt().add(scmInvMoveBillEntry.getAmt()));
									existScmCostItemInAndOutSum.setInitAvgPrice(
											BigDecimal.ZERO.compareTo(existScmCostItemInAndOutSum.getInitQty()) == 0
													? BigDecimal.ZERO
													: existScmCostItemInAndOutSum.getInitAmt().divide(
															existScmCostItemInAndOutSum.getInitQty(), 2,
															BigDecimal.ROUND_HALF_UP));
									existScmCostItemInAndOutSum.setInitTax(existScmCostItemInAndOutSum.getInitTax().add(
											scmInvMoveBillEntry.getTaxAmt().subtract(scmInvMoveBillEntry.getAmt())));
									existScmCostItemInAndOutSum.setInitTaxAmt(existScmCostItemInAndOutSum
											.getInitTaxAmt().add(scmInvMoveBillEntry.getTaxAmt()));
									exists = true;
									break;
								}
							}
						}
						if (!exists) {
							ScmCostItemInAndOutSum scmCostItemInAndOutSum = new ScmCostItemInAndOutSum(true);
							scmCostItemInAndOutSum.setItemId(scmInvMoveBillEntry.getItemId());
							scmCostItemInAndOutSum.setItemNo(scmInvMoveBillEntry.getItemNo());
							scmCostItemInAndOutSum.setItemName(scmInvMoveBillEntry.getItemName());
							scmCostItemInAndOutSum.setSpec(scmInvMoveBillEntry.getSpec());
							scmCostItemInAndOutSum.setGroupId(scmInvMoveBillEntry.getGroupId());
							scmCostItemInAndOutSum.setClassCode(scmInvMoveBillEntry.getGroupCode());
							scmCostItemInAndOutSum.setClassName(scmInvMoveBillEntry.getGroupName());
							scmCostItemInAndOutSum.setLongNo(scmInvMoveBillEntry.getLongNo());
							scmCostItemInAndOutSum.setUnitName(scmInvMoveBillEntry.getUnitName());
							scmCostItemInAndOutSum.setUnit(scmInvMoveBillEntry.getUnit());
							scmCostItemInAndOutSum.setCostOrgUnitNo(scmInvMoveBillEntry.getInCstOrgUnitNo());
							scmCostItemInAndOutSum.setInitQty(scmInvMoveBillEntry.getQty());
							scmCostItemInAndOutSum.setInitAmt(scmInvMoveBillEntry.getAmt());
							scmCostItemInAndOutSum
									.setInitAvgPrice(
											BigDecimal.ZERO.compareTo(scmCostItemInAndOutSum.getInitQty()) == 0
													? BigDecimal.ZERO
													: scmCostItemInAndOutSum.getInitAmt().divide(
															scmCostItemInAndOutSum.getInitQty(), 2,
															BigDecimal.ROUND_HALF_UP));
							scmCostItemInAndOutSum
									.setInitTax(scmInvMoveBillEntry.getTaxAmt().subtract(scmInvMoveBillEntry.getAmt()));
							scmCostItemInAndOutSum.setInitTaxAmt(scmInvMoveBillEntry.getTaxAmt());
							scmCostItemInAndOutSumList.add(scmCostItemInAndOutSum);
						}
					}
				} else {
					// 放入明细记录结果集中
					if (StringUtils.contains(costOrgUnitNos.toString(), scmInvMoveBillEntry.getOutCstOrgUnitNo())) {
						boolean exists = false;
						if (!scmCostItemInAndOutSumList.isEmpty()) {
							for (ScmCostItemInAndOutSum existScmCostItemInAndOutSum : scmCostItemInAndOutSumList) {
								if (existScmCostItemInAndOutSum.getItemId() == scmInvMoveBillEntry.getItemId()
										&& StringUtils.equals(existScmCostItemInAndOutSum.getCostOrgUnitNo(),
												scmInvMoveBillEntry.getOutCstOrgUnitNo())) {
									existScmCostItemInAndOutSum.setOutQty(
											existScmCostItemInAndOutSum.getOutQty().add(scmInvMoveBillEntry.getQty()));
									existScmCostItemInAndOutSum.setOutAmt(
											existScmCostItemInAndOutSum.getOutAmt().add(scmInvMoveBillEntry.getAmt()));
									existScmCostItemInAndOutSum.setAllotOutQty(existScmCostItemInAndOutSum
											.getAllotOutQty().add(scmInvMoveBillEntry.getQty()));
									existScmCostItemInAndOutSum.setAllotOutAvgPrice(
											BigDecimal.ZERO.compareTo(existScmCostItemInAndOutSum
													.getAllotOutQty().add(scmInvMoveBillEntry.getQty())) == 0
													? BigDecimal.ZERO
													: existScmCostItemInAndOutSum
													.getAllotOutAmt().add(scmInvMoveBillEntry.getAmt()).divide(
															existScmCostItemInAndOutSum
															.getAllotOutQty().add(scmInvMoveBillEntry.getQty()), 2,
															BigDecimal.ROUND_HALF_UP));
									existScmCostItemInAndOutSum.setAllotOutAmt(existScmCostItemInAndOutSum
											.getAllotOutAmt().add(scmInvMoveBillEntry.getAmt()));
									existScmCostItemInAndOutSum.setAllotOutTax(
											existScmCostItemInAndOutSum.getAllotOutTax().add(scmInvMoveBillEntry
													.getTaxAmt().subtract(scmInvMoveBillEntry.getAmt())));
									existScmCostItemInAndOutSum.setAllotOutTaxAmt(existScmCostItemInAndOutSum
											.getAllotOutTaxAmt().add(scmInvMoveBillEntry.getTaxAmt()));
									existScmCostItemInAndOutSum.setOutAvgPrice(
											BigDecimal.ZERO.compareTo(existScmCostItemInAndOutSum.getOutQty()) == 0
													? BigDecimal.ZERO
													: existScmCostItemInAndOutSum.getOutAmt().divide(
															existScmCostItemInAndOutSum.getOutQty(), 2,
															BigDecimal.ROUND_HALF_UP));
									existScmCostItemInAndOutSum.setOutTax(existScmCostItemInAndOutSum.getOutTax().add(
											scmInvMoveBillEntry.getTaxAmt().subtract(scmInvMoveBillEntry.getAmt())));
									existScmCostItemInAndOutSum.setOutTaxAmt(existScmCostItemInAndOutSum.getOutTaxAmt()
											.add(scmInvMoveBillEntry.getTaxAmt()));
									exists = true;
									break;
								}
							}
						}
						if (!exists) {
							ScmCostItemInAndOutSum scmCostItemInAndOutSum = new ScmCostItemInAndOutSum(true);
							scmCostItemInAndOutSum.setItemId(scmInvMoveBillEntry.getItemId());
							scmCostItemInAndOutSum.setItemNo(scmInvMoveBillEntry.getItemNo());
							scmCostItemInAndOutSum.setItemName(scmInvMoveBillEntry.getItemName());
							scmCostItemInAndOutSum.setSpec(scmInvMoveBillEntry.getSpec());
							scmCostItemInAndOutSum.setGroupId(scmInvMoveBillEntry.getGroupId());
							scmCostItemInAndOutSum.setClassCode(scmInvMoveBillEntry.getGroupCode());
							scmCostItemInAndOutSum.setClassName(scmInvMoveBillEntry.getGroupName());
							scmCostItemInAndOutSum.setLongNo(scmInvMoveBillEntry.getLongNo());
							scmCostItemInAndOutSum.setUnitName(scmInvMoveBillEntry.getUnitName());
							scmCostItemInAndOutSum.setUnit(scmInvMoveBillEntry.getUnit());
							scmCostItemInAndOutSum.setCostOrgUnitNo(scmInvMoveBillEntry.getOutCstOrgUnitNo());
							scmCostItemInAndOutSum.setOutQty(scmInvMoveBillEntry.getQty());
							scmCostItemInAndOutSum.setOutAmt(scmInvMoveBillEntry.getAmt());
							scmCostItemInAndOutSum.setAllotOutQty(scmInvMoveBillEntry.getQty());
							scmCostItemInAndOutSum.setOutAvgPrice(
									BigDecimal.ZERO.compareTo(scmInvMoveBillEntry.getQty()) == 0 ? BigDecimal.ZERO
											: scmInvMoveBillEntry.getAmt().divide(
													scmInvMoveBillEntry.getQty(), 2, BigDecimal.ROUND_HALF_UP));
							scmCostItemInAndOutSum.setAllotOutAmt(scmInvMoveBillEntry.getAmt());
							scmCostItemInAndOutSum.setAllotOutTax(scmCostItemInAndOutSum.getAllotOutTaxAmt().add((scmInvMoveBillEntry.getTaxAmt())));
							scmCostItemInAndOutSum.setAllotOutTaxAmt(scmInvMoveBillEntry.getTaxAmt());

							scmCostItemInAndOutSum.setOutAvgPrice(
									BigDecimal.ZERO.compareTo(scmCostItemInAndOutSum.getOutQty()) == 0 ? BigDecimal.ZERO
											: scmCostItemInAndOutSum.getOutAmt().divide(
													scmCostItemInAndOutSum.getOutQty(), 2, BigDecimal.ROUND_HALF_UP));
							scmCostItemInAndOutSum
									.setOutTax(scmInvMoveBillEntry.getTaxAmt().subtract(scmInvMoveBillEntry.getAmt()));
							scmCostItemInAndOutSum.setOutTaxAmt(scmInvMoveBillEntry.getTaxAmt());
							scmCostItemInAndOutSumList.add(scmCostItemInAndOutSum);
						}
					}
					if (StringUtils.contains(costOrgUnitNos.toString(), scmInvMoveBillEntry.getInCstOrgUnitNo())) {
//						OrgCostCenter2 orgCostCenter = orgCostCenterBiz
//								.selectByOrgUnitNo(scmInvMoveBillEntry.getInCstOrgUnitNo(), param);
						boolean exists = false;
						if (!scmCostItemInAndOutSumList.isEmpty()) {
							for (ScmCostItemInAndOutSum existScmCostItemInAndOutSum : scmCostItemInAndOutSumList) {
								if (existScmCostItemInAndOutSum.getItemId() == scmInvMoveBillEntry.getItemId()
										&& StringUtils.equals(existScmCostItemInAndOutSum.getCostOrgUnitNo(),
												scmInvMoveBillEntry.getInCstOrgUnitNo())) {
									existScmCostItemInAndOutSum.setAddInQty(existScmCostItemInAndOutSum.getAddInQty()
											.add(scmInvMoveBillEntry.getQty()));
									existScmCostItemInAndOutSum.setAddInAmt(existScmCostItemInAndOutSum.getAddInAmt()
											.add(scmInvMoveBillEntry.getAmt()));
									existScmCostItemInAndOutSum.setAllotInQty(existScmCostItemInAndOutSum
											.getAllotInQty().add((scmInvMoveBillEntry.getQty())));
									existScmCostItemInAndOutSum.setAddInAvgPrice(
											BigDecimal.ZERO.compareTo(existScmCostItemInAndOutSum
													.getAllotInQty().add((scmInvMoveBillEntry.getQty()))) == 0
													? BigDecimal.ZERO
													: existScmCostItemInAndOutSum
													.getAllotInAmt().add((scmInvMoveBillEntry.getAmt())).divide(
															existScmCostItemInAndOutSum
															.getAllotInQty().add((scmInvMoveBillEntry.getQty())), 2,
															BigDecimal.ROUND_HALF_UP));
									existScmCostItemInAndOutSum.setAllotInAmt(existScmCostItemInAndOutSum
											.getAllotInAmt().add((scmInvMoveBillEntry.getAmt())));
									existScmCostItemInAndOutSum.setAllotInTax(
											existScmCostItemInAndOutSum.getAllotInTax().add((scmInvMoveBillEntry
													.getTaxAmt().subtract(scmInvMoveBillEntry.getAmt()))));
									existScmCostItemInAndOutSum.setAllotInTaxAmt(existScmCostItemInAndOutSum
											.getAllotInTaxAmt().add((scmInvMoveBillEntry.getTaxAmt())));
									existScmCostItemInAndOutSum.setAddInAvgPrice(
											BigDecimal.ZERO.compareTo(existScmCostItemInAndOutSum.getAddInQty()) == 0
													? BigDecimal.ZERO
													: existScmCostItemInAndOutSum.getAddInAmt().divide(
															existScmCostItemInAndOutSum.getAddInQty(), 2,
															BigDecimal.ROUND_HALF_UP));
									existScmCostItemInAndOutSum.setAddInTax(
											existScmCostItemInAndOutSum.getAddInTax().add(scmInvMoveBillEntry
													.getTaxAmt().subtract(scmInvMoveBillEntry.getAmt())));
									existScmCostItemInAndOutSum.setAddInTaxAmt(existScmCostItemInAndOutSum
											.getAddInTaxAmt().add(scmInvMoveBillEntry.getTaxAmt()));
									if (costCenterTypeMap.containsKey(scmInvMoveBillEntry.getInCstOrgUnitNo())) {
										if (StringUtils.equals("1",costCenterTypeMap.get(scmInvMoveBillEntry.getInCstOrgUnitNo()))) {
//										if (orgCostCenter != null
//												&& StringUtils.equals("1", orgCostCenter.getCostCenterType())) {
											existScmCostItemInAndOutSum.setOutQty(existScmCostItemInAndOutSum.getOutQty()
													.add(scmInvMoveBillEntry.getQty()));
											existScmCostItemInAndOutSum.setOutAmt(existScmCostItemInAndOutSum.getOutAmt()
													.add(scmInvMoveBillEntry.getAmt()));
											existScmCostItemInAndOutSum.setOutAvgPrice(
													BigDecimal.ZERO.compareTo(existScmCostItemInAndOutSum.getOutQty()) == 0
															? BigDecimal.ZERO
															: existScmCostItemInAndOutSum.getOutAmt().divide(
																	existScmCostItemInAndOutSum.getOutQty(), 2,
																	BigDecimal.ROUND_HALF_UP));
											existScmCostItemInAndOutSum.setOutTax(
													existScmCostItemInAndOutSum.getOutTax().add(scmInvMoveBillEntry
															.getTaxAmt().subtract(scmInvMoveBillEntry.getAmt())));
											existScmCostItemInAndOutSum.setOutTaxAmt(existScmCostItemInAndOutSum
													.getOutTaxAmt().add(scmInvMoveBillEntry.getTaxAmt()));
										}
									}
									exists = true;
									break;
								}
							}
						}
						if (!exists) {
							ScmCostItemInAndOutSum scmCostItemInAndOutSum = new ScmCostItemInAndOutSum(true);
							scmCostItemInAndOutSum.setItemId(scmInvMoveBillEntry.getItemId());
							scmCostItemInAndOutSum.setItemNo(scmInvMoveBillEntry.getItemNo());
							scmCostItemInAndOutSum.setItemName(scmInvMoveBillEntry.getItemName());
							scmCostItemInAndOutSum.setSpec(scmInvMoveBillEntry.getSpec());
							scmCostItemInAndOutSum.setGroupId(scmInvMoveBillEntry.getGroupId());
							scmCostItemInAndOutSum.setClassCode(scmInvMoveBillEntry.getGroupCode());
							scmCostItemInAndOutSum.setClassName(scmInvMoveBillEntry.getGroupName());
							scmCostItemInAndOutSum.setLongNo(scmInvMoveBillEntry.getLongNo());
							scmCostItemInAndOutSum.setUnitName(scmInvMoveBillEntry.getUnitName());
							scmCostItemInAndOutSum.setUnit(scmInvMoveBillEntry.getUnit());
							scmCostItemInAndOutSum.setCostOrgUnitNo(scmInvMoveBillEntry.getInCstOrgUnitNo());
							scmCostItemInAndOutSum.setAddInQty(scmInvMoveBillEntry.getQty());
							scmCostItemInAndOutSum.setAddInAmt(scmInvMoveBillEntry.getAmt());
							scmCostItemInAndOutSum.setAllotInQty(scmInvMoveBillEntry.getQty());
							scmCostItemInAndOutSum
							.setAddInAvgPrice(
									BigDecimal.ZERO.compareTo(scmInvMoveBillEntry.getQty()) == 0
											? BigDecimal.ZERO
											: scmInvMoveBillEntry.getAmt().divide(
													scmInvMoveBillEntry.getQty(), 2,
													BigDecimal.ROUND_HALF_UP));
							scmCostItemInAndOutSum.setAllotInAmt(scmInvMoveBillEntry.getAmt());
							scmCostItemInAndOutSum.setAllotInTax(
									scmInvMoveBillEntry.getTaxAmt().subtract(scmInvMoveBillEntry.getAmt()));
							scmCostItemInAndOutSum.setAllotInTaxAmt(scmInvMoveBillEntry.getTaxAmt());

							scmCostItemInAndOutSum
									.setAddInAvgPrice(
											BigDecimal.ZERO.compareTo(scmCostItemInAndOutSum.getAddInQty()) == 0
													? BigDecimal.ZERO
													: scmCostItemInAndOutSum.getAddInAmt().divide(
															scmCostItemInAndOutSum.getAddInQty(), 2,
															BigDecimal.ROUND_HALF_UP));
							scmCostItemInAndOutSum.setAddInTax(
									scmInvMoveBillEntry.getTaxAmt().subtract(scmInvMoveBillEntry.getAmt()));
							scmCostItemInAndOutSum.setAddInTaxAmt(scmInvMoveBillEntry.getTaxAmt());
							if (costCenterTypeMap.containsKey(scmInvMoveBillEntry.getInCstOrgUnitNo())) {
								if (StringUtils.equals("1",costCenterTypeMap.get(scmInvMoveBillEntry.getInCstOrgUnitNo()))) {
									scmCostItemInAndOutSum.setOutQty(scmInvMoveBillEntry.getQty());
									scmCostItemInAndOutSum.setOutAmt(scmInvMoveBillEntry.getAmt());
									scmCostItemInAndOutSum.setOutAvgPrice(
											BigDecimal.ZERO.compareTo(scmCostItemInAndOutSum.getOutQty()) == 0
													? BigDecimal.ZERO
													: scmCostItemInAndOutSum.getOutAmt().divide(
															scmCostItemInAndOutSum.getOutQty(), 2,
															BigDecimal.ROUND_HALF_UP));
									scmCostItemInAndOutSum.setOutTax(
											scmInvMoveBillEntry.getTaxAmt().subtract(scmInvMoveBillEntry.getAmt()));
									scmCostItemInAndOutSum.setOutTaxAmt(scmInvMoveBillEntry.getTaxAmt());
								}
							}
							scmCostItemInAndOutSumList.add(scmCostItemInAndOutSum);
						}
					}
				}
			}
				long dataEndTimeTransfer1 = System.currentTimeMillis();
				log.info("成本转移明细加工耗时："+(dataEndTimeTransfer1-dataEndTimeTransfer)+"毫秒");
			
			
		}
		 long beginTimeBreakage = System.currentTimeMillis();
		// 获取报损单数据
		page = new Page();
		page.setModelClass(ScmCstFrmLossEntry2.class);
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
					.put(ClassUtils.getFinalModelSimpleName(ScmCstFrmLossEntry2.class) + "."
							+ ScmCstFrmLossEntry2.FN_ITEMID, new QueryParam(
									ClassUtils.getFinalModelSimpleName(ScmCstFrmLossEntry2.class) + "."
											+ ScmCstFrmLossEntry2.FN_ITEMID,
									QueryParam.QUERY_IN, sbMaterila.toString()));
		}
		page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmCstFrmLoss.class) + "." + ScmCstFrmLoss.FN_ORGUNITNO,
				new QueryParam(
						ClassUtils.getFinalModelSimpleName(ScmCstFrmLoss.class) + "." + ScmCstFrmLoss.FN_ORGUNITNO,
						QueryParam.QUERY_IN, costOrgUnitNos.toString()));
		page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmCstFrmLoss.class) + "." + ScmCstFrmLoss.FN_BIZDATE,
				new QueryParam(ClassUtils.getFinalModelSimpleName(ScmCstFrmLoss.class) + "." + ScmCstFrmLoss.FN_BIZDATE,
						QueryParam.QUERY_BETWEEN, FormatUtils.fmtDate(periodCalendar.getStartDate()), endDate));
		if (!StringUtils.equals("Y", status)) {
			page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmCstFrmLoss.class) + "." + ScmCstFrmLoss.FN_STATUS,
					new QueryParam(
							ClassUtils.getFinalModelSimpleName(ScmCstFrmLoss.class) + "." + ScmCstFrmLoss.FN_STATUS,
							QueryParam.QUERY_EQ, "E"));
		}
		List<ScmCstFrmLossEntry2> scmCstFrmLossEntryList = scmCstFrmLossEntryBiz.queryPage(page, arglist, "findAllPage",
				param);
		long dataEndTimeBreakage = System.currentTimeMillis();
		log.info("成本中心报损明细查询耗时："+(dataEndTimeBreakage-beginTimeBreakage)+"毫秒");
		if (scmCstFrmLossEntryList != null && !scmCstFrmLossEntryList.isEmpty()) {
			log.info("成本中心报损明细查询结果："+(scmCstFrmLossEntryList.size())+"条记录");
			for (ScmCstFrmLossEntry2 scmCstFrmLossEntry : scmCstFrmLossEntryList) {
//				OrgCostCenter2 orgCostCenter = orgCostCenterBiz.selectByOrgUnitNo(scmCstFrmLossEntry.getCostOrgUnitNo(),
//						param);
//				if (orgCostCenter != null && StringUtils.equals("1", orgCostCenter.getCostCenterType())) {
//					continue;
//				}
				if (costCenterTypeMap.containsKey(scmCstFrmLossEntry.getCostOrgUnitNo())) {
					if (StringUtils.equals("1",costCenterTypeMap.get(scmCstFrmLossEntry.getCostOrgUnitNo()))) {
						continue;
					}
				}
				if (scmCstFrmLossEntry.getBizDate().compareTo(FormatUtils.parseDate(beginDate)) < 0) {
					// 比查询日期小的统计到期初数据中，并从List中删除
					boolean exists = false;
					if (!scmCostItemInAndOutSumList.isEmpty()) {
						for (ScmCostItemInAndOutSum existScmCostItemInAndOutSum : scmCostItemInAndOutSumList) {
							if (existScmCostItemInAndOutSum.getItemId() == scmCstFrmLossEntry.getItemId()
									&& StringUtils.equals(existScmCostItemInAndOutSum.getCostOrgUnitNo(),
											scmCstFrmLossEntry.getCostOrgUnitNo())) {
								existScmCostItemInAndOutSum.setInitQty(
										existScmCostItemInAndOutSum.getInitQty().subtract(scmCstFrmLossEntry.getQty()));
								existScmCostItemInAndOutSum.setInitAmt(
										existScmCostItemInAndOutSum.getInitAmt().subtract(scmCstFrmLossEntry.getAmt()));
								existScmCostItemInAndOutSum.setInitAvgPrice(
										BigDecimal.ZERO.compareTo(existScmCostItemInAndOutSum.getInitQty()) == 0
												? BigDecimal.ZERO
												: existScmCostItemInAndOutSum.getInitAmt().divide(
														existScmCostItemInAndOutSum.getInitQty(), 2,
														BigDecimal.ROUND_HALF_UP));
								existScmCostItemInAndOutSum
										.setInitTax(existScmCostItemInAndOutSum.getInitTax().subtract(
												scmCstFrmLossEntry.getTaxAmt().subtract(scmCstFrmLossEntry.getAmt())));
								existScmCostItemInAndOutSum.setInitTaxAmt(existScmCostItemInAndOutSum.getInitTaxAmt()
										.subtract(scmCstFrmLossEntry.getTaxAmt()));
								exists = true;
								break;
							}
						}
					}
					if (!exists) {
						ScmCostItemInAndOutSum scmCostItemInAndOutSum = new ScmCostItemInAndOutSum(true);
						scmCostItemInAndOutSum.setItemId(scmCstFrmLossEntry.getItemId());
						scmCostItemInAndOutSum.setItemNo(scmCstFrmLossEntry.getItemNo());
						scmCostItemInAndOutSum.setItemName(scmCstFrmLossEntry.getItemName());
						scmCostItemInAndOutSum.setSpec(scmCstFrmLossEntry.getSpec());
						scmCostItemInAndOutSum.setGroupId(scmCstFrmLossEntry.getGroupId());
						scmCostItemInAndOutSum.setClassCode(scmCstFrmLossEntry.getGroupCode());
						scmCostItemInAndOutSum.setClassName(scmCstFrmLossEntry.getGroupName());
						scmCostItemInAndOutSum.setLongNo(scmCstFrmLossEntry.getLongNo());
						scmCostItemInAndOutSum.setUnitName(scmCstFrmLossEntry.getUnitName());
						scmCostItemInAndOutSum.setUnit(scmCstFrmLossEntry.getUnit());
						scmCostItemInAndOutSum.setCostOrgUnitNo(scmCstFrmLossEntry.getCostOrgUnitNo());
						scmCostItemInAndOutSum.setInitQty(BigDecimal.ZERO.subtract(scmCstFrmLossEntry.getQty()));
						scmCostItemInAndOutSum.setInitAmt(BigDecimal.ZERO.subtract(scmCstFrmLossEntry.getAmt()));
						scmCostItemInAndOutSum.setInitAvgPrice(
								BigDecimal.ZERO.compareTo(scmCostItemInAndOutSum.getInitQty()) == 0 ? BigDecimal.ZERO
										: scmCostItemInAndOutSum.getInitAmt().divide(
												scmCostItemInAndOutSum.getInitQty(), 2, BigDecimal.ROUND_HALF_UP));
						scmCostItemInAndOutSum.setInitTax(BigDecimal.ZERO
								.subtract(scmCstFrmLossEntry.getTaxAmt().subtract(scmCstFrmLossEntry.getAmt())));
						scmCostItemInAndOutSum.setInitTaxAmt(BigDecimal.ZERO.subtract(scmCstFrmLossEntry.getTaxAmt()));
						scmCostItemInAndOutSumList.add(scmCostItemInAndOutSum);
					}
				} else {
					boolean exists = false;
					if (!scmCostItemInAndOutSumList.isEmpty()) {
						for (ScmCostItemInAndOutSum existScmCostItemInAndOutSum : scmCostItemInAndOutSumList) {
							if (existScmCostItemInAndOutSum.getItemId() == scmCstFrmLossEntry.getItemId()
									&& StringUtils.equals(existScmCostItemInAndOutSum.getCostOrgUnitNo(),
											scmCstFrmLossEntry.getCostOrgUnitNo())) {
								existScmCostItemInAndOutSum.setOutQty(
										existScmCostItemInAndOutSum.getOutQty().add(scmCstFrmLossEntry.getQty()));
								existScmCostItemInAndOutSum.setOutAmt(
										existScmCostItemInAndOutSum.getOutAmt().add(scmCstFrmLossEntry.getAmt()));
								existScmCostItemInAndOutSum.setBreakOutQty(existScmCostItemInAndOutSum.getBreakOutQty()
										.add((scmCstFrmLossEntry.getQty())));
								existScmCostItemInAndOutSum.setBreakOutAvgPrice(
										BigDecimal.ZERO.compareTo(existScmCostItemInAndOutSum.getBreakOutQty()
												.add((scmCstFrmLossEntry.getQty()))) == 0
												? BigDecimal.ZERO
												: existScmCostItemInAndOutSum.getBreakOutAmt()
												.add((scmCstFrmLossEntry.getAmt())).divide(
														existScmCostItemInAndOutSum.getBreakOutQty()
														.add((scmCstFrmLossEntry.getQty())), 2,
														BigDecimal.ROUND_HALF_UP));
								existScmCostItemInAndOutSum.setBreakOutAmt(existScmCostItemInAndOutSum.getBreakOutAmt()
										.add((scmCstFrmLossEntry.getAmt())));
								existScmCostItemInAndOutSum.setBreakOutTax(existScmCostItemInAndOutSum.getBreakOutTax()
										.add((scmCstFrmLossEntry.getTaxAmt().subtract(scmCstFrmLossEntry.getAmt()))));
								existScmCostItemInAndOutSum.setBreakOutTaxAmt(existScmCostItemInAndOutSum
										.getBreakOutTaxAmt().add((scmCstFrmLossEntry.getTaxAmt())));
								existScmCostItemInAndOutSum.setOutAvgPrice(
										BigDecimal.ZERO.compareTo(existScmCostItemInAndOutSum.getOutQty()) == 0
												? BigDecimal.ZERO
												: existScmCostItemInAndOutSum.getOutAmt().divide(
														existScmCostItemInAndOutSum.getOutQty(), 2,
														BigDecimal.ROUND_HALF_UP));
								existScmCostItemInAndOutSum.setOutTax(existScmCostItemInAndOutSum.getOutTax()
										.add(scmCstFrmLossEntry.getTaxAmt().subtract(scmCstFrmLossEntry.getAmt())));
								existScmCostItemInAndOutSum.setOutTaxAmt(
										existScmCostItemInAndOutSum.getOutTaxAmt().add(scmCstFrmLossEntry.getTaxAmt()));
								exists = true;
								break;
							}
						}
					}
					if (!exists) {
						// 放入明细记录结果集中
						ScmCostItemInAndOutSum scmCostItemInAndOutSum = new ScmCostItemInAndOutSum(true);
						scmCostItemInAndOutSum.setItemId(scmCstFrmLossEntry.getItemId());
						scmCostItemInAndOutSum.setItemNo(scmCstFrmLossEntry.getItemNo());
						scmCostItemInAndOutSum.setItemName(scmCstFrmLossEntry.getItemName());
						scmCostItemInAndOutSum.setSpec(scmCstFrmLossEntry.getSpec());
						scmCostItemInAndOutSum.setGroupId(scmCstFrmLossEntry.getGroupId());
						scmCostItemInAndOutSum.setClassCode(scmCstFrmLossEntry.getGroupCode());
						scmCostItemInAndOutSum.setClassName(scmCstFrmLossEntry.getGroupName());
						scmCostItemInAndOutSum.setLongNo(scmCstFrmLossEntry.getLongNo());
						scmCostItemInAndOutSum.setUnitName(scmCstFrmLossEntry.getUnitName());
						scmCostItemInAndOutSum.setUnit(scmCstFrmLossEntry.getUnit());
						scmCostItemInAndOutSum.setCostOrgUnitNo(scmCstFrmLossEntry.getCostOrgUnitNo());
						scmCostItemInAndOutSum.setOutQty(scmCstFrmLossEntry.getQty());
						scmCostItemInAndOutSum.setOutAmt(scmCstFrmLossEntry.getAmt());
						scmCostItemInAndOutSum.setBreakOutQty(scmCstFrmLossEntry.getQty());
						scmCostItemInAndOutSum.setBreakOutAvgPrice(
								BigDecimal.ZERO.compareTo(scmCstFrmLossEntry.getQty()) == 0 ? BigDecimal.ZERO
										: scmCstFrmLossEntry.getAmt().divide(scmCstFrmLossEntry.getQty(),
												2, BigDecimal.ROUND_HALF_UP));
						scmCostItemInAndOutSum.setBreakOutAmt(scmCstFrmLossEntry.getAmt());
						scmCostItemInAndOutSum.setBreakOutTax(scmCstFrmLossEntry.getTaxAmt().subtract(scmCstFrmLossEntry.getAmt()));
						scmCostItemInAndOutSum.setBreakOutTaxAmt(scmCstFrmLossEntry.getTaxAmt());
					
						scmCostItemInAndOutSum.setOutAvgPrice(
								BigDecimal.ZERO.compareTo(scmCostItemInAndOutSum.getOutQty()) == 0 ? BigDecimal.ZERO
										: scmCostItemInAndOutSum.getOutAmt().divide(scmCostItemInAndOutSum.getOutQty(),
												2, BigDecimal.ROUND_HALF_UP));
						scmCostItemInAndOutSum
								.setOutTax(scmCstFrmLossEntry.getTaxAmt().subtract(scmCstFrmLossEntry.getAmt()));
						scmCostItemInAndOutSum.setOutTaxAmt(scmCstFrmLossEntry.getTaxAmt());
						scmCostItemInAndOutSumList.add(scmCostItemInAndOutSum);
					}
				}
			}
			long dataEndTimeBreakage1 = System.currentTimeMillis();
			log.info("成本中心报损明细加工耗时："+(dataEndTimeBreakage1-dataEndTimeBreakage)+"毫秒");
		}
		
		long dataEndTime1 = System.currentTimeMillis();
		if (!scmCostItemInAndOutSumList.isEmpty()) {
			HashMap<String,Object> cacheMap = new HashMap<String,Object>();
			for (ScmCostItemInAndOutSum scmCostItemInAndOutSum : scmCostItemInAndOutSumList) {
				if (StringUtils.isNotBlank(scmCostItemInAndOutSum.getCostOrgUnitNo())) {
					OrgCostCenter2 orgCostCenter = (OrgCostCenter2) cacheMap.get(ClassUtils.getFinalModelSimpleName(OrgCostCenter2.class)+"_"+scmCostItemInAndOutSum.getCostOrgUnitNo());
					if(orgCostCenter==null) {
						orgCostCenter = orgCostCenterBiz
								.selectByOrgUnitNo(scmCostItemInAndOutSum.getCostOrgUnitNo(), param);
						if (orgCostCenter != null) {
							cacheMap.put(ClassUtils.getFinalModelSimpleName(OrgCostCenter2.class)+"_"+scmCostItemInAndOutSum.getCostOrgUnitNo(), orgCostCenter);
						}
					}
					if (orgCostCenter != null) {
						scmCostItemInAndOutSum.setCostOrgUnitName(orgCostCenter.getOrgUnitName());
					} else {
						scmCostItemInAndOutSum.setCostOrgUnitName(scmCostItemInAndOutSum.getCostOrgUnitNo());
					}
				}
				if (scmCostItemInAndOutSum.getItemId() > 0) {
					if (StringUtils.isNotBlank(scmCostItemInAndOutSum.getLongNo())) {
						String[] ids = StringUtils.split(scmCostItemInAndOutSum.getLongNo(), ",");
						if (Long.valueOf(ids[0]) != scmCostItemInAndOutSum.getGroupId()) {
							ScmMaterialGroup scmMaterialGroup = (ScmMaterialGroup) cacheMap.get(ClassUtils.getFinalModelSimpleName(ScmMaterialGroup.class)+"_"+(ids[0]));
							if(scmMaterialGroup==null) {
								scmMaterialGroup = scmMaterialGroupBiz.selectDirect(Long.valueOf(ids[0]),
									param);
								if (scmMaterialGroup != null) {
									cacheMap.put(ClassUtils.getFinalModelSimpleName(ScmMaterialGroup.class)+"_"+(ids[0]), scmMaterialGroup);
								}
							}
							if (scmMaterialGroup != null){
								scmCostItemInAndOutSum.setBroadClassName(scmMaterialGroup.getClassName());
							}
						}
					}
				}
				scmCostItemInAndOutSum.setStockQty(scmCostItemInAndOutSum.getInitQty()
						.add(scmCostItemInAndOutSum.getAddInQty()).subtract(scmCostItemInAndOutSum.getOutQty()));
				scmCostItemInAndOutSum.setStockAmt(scmCostItemInAndOutSum.getInitAmt()
						.add(scmCostItemInAndOutSum.getAddInAmt()).subtract(scmCostItemInAndOutSum.getOutAmt()));
				scmCostItemInAndOutSum.setStockAvgPrice(
						BigDecimal.ZERO.compareTo(scmCostItemInAndOutSum.getStockQty()) == 0 ? BigDecimal.ZERO
								: scmCostItemInAndOutSum.getStockAmt().divide(scmCostItemInAndOutSum.getStockQty(), 2,
										BigDecimal.ROUND_HALF_UP));
				scmCostItemInAndOutSum.setStockTax(scmCostItemInAndOutSum.getInitTax()
						.add(scmCostItemInAndOutSum.getAddInTax()).subtract(scmCostItemInAndOutSum.getOutTax()));
				scmCostItemInAndOutSum.setStockTaxAmt(scmCostItemInAndOutSum.getInitTaxAmt()
						.add(scmCostItemInAndOutSum.getAddInTaxAmt()).subtract(scmCostItemInAndOutSum.getOutTaxAmt()));
			}
		}
		long dataEndTime22 = System.currentTimeMillis();
		log.info("查询结果后加工耗时："+(dataEndTime22-dataEndTime1)+"毫秒");

		// 数据排序
		String fields[] = { "costOrgUnitNo", "classCode", "itemNo" };
		String sorts[] = { "ASC", "ASC", "ASC" };
		long costTypedate = System.currentTimeMillis();
		if (scmCostItemInAndOutSumList!=null && scmCostItemInAndOutSumList.size()>0) {
			Page page2 = new Page();
			page2.setModelClass(ScmCostUseSet2.class);
			page2.setShowCount(Integer.MAX_VALUE);
			page2.getParam().put(ClassUtils.getFinalModelSimpleName(ScmCostUseSet2.class) + "." + ScmCostUseSet.FN_COSTORGUNITNO,
					new QueryParam(
							ClassUtils.getFinalModelSimpleName(ScmCostUseSet2.class) + "." + ScmCostUseSet.FN_COSTORGUNITNO,
							QueryParam.QUERY_IN, costOrgUnitNos.toString()));
			page2.getParam().put(ClassUtils.getFinalModelSimpleName(ScmCostUseSet2.class) + "." + ScmCostUseSet.FN_CONTROLUNITNO, 
					new QueryParam(ClassUtils.getFinalModelSimpleName(ScmCostUseSet2.class) + "." + ScmCostUseSet.FN_CONTROLUNITNO,
							QueryParam.QUERY_EQ,currentControlUnitNo));
			List<ScmCostUseSet2> scmCostUseSet2List = scmCostUseSetBiz.findPageDirect(page2, param);
			for (ScmCostUseSet2 scmCostUseSet : scmCostUseSet2List) {
				for (ScmCostItemInAndOutSum scmCostItemInAndOutSum2 : scmCostItemInAndOutSumList) {
					if (scmCostUseSet.getClassId()==scmCostItemInAndOutSum2.getGroupId() 
							&& StringUtils.equals(scmCostUseSet.getCostOrgUnitNo(), scmCostItemInAndOutSum2.getCostOrgUnitNo())) {
						scmCostItemInAndOutSum2.setCostTypeName(scmCostUseSet.getScmCostUseTypeName());
					}
				}
			}
			long costTypedate1 = System.currentTimeMillis();
			log.info("添加成本用途耗时："+(costTypedate1-costTypedate)+"毫秒");
		}
		scmCostItemInAndOutSumList = (List<ScmCostItemInAndOutSum>) ListSortUtil.sort(scmCostItemInAndOutSumList,
				fields, sorts);

		if (scmCostItemInAndOutSumList != null && !scmCostItemInAndOutSumList.isEmpty()) {
			log.info("报表总记录："+(scmCostItemInAndOutSumList.size())+"条数");
		}

		return scmCostItemInAndOutSumList;
	}

	
	protected String valueToDisplay(String beanId, String key, Param param) throws AppException {
		try {
			BaseBiz biz =(BaseBiz) beanMap.get(beanId);
			if(biz==null) {
				biz = (BaseBiz) AppContextUtil.getBean(beanId);
				beanMap.put(beanId, biz);
			}
			if (biz != null) {
				return biz.getDisplayByKey(key, param);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return key;
	}

	@Override
	public List<ScmDeptConsume> selectDeptSummaryConsume(HttpServletRequest request) throws AppException {
		String materialClassName=request.getParameter("materialClassName2");
		if (StringUtils.isBlank(materialClassName)){
			return null;
		}
		String currentOrgUnitNo=request.getParameter("orgUnitNo");
		String currentControlUnitNo=request.getParameter("controlUnitNo");
		String queryType=request.getParameter("queryType");
		String periodId=request.getParameter("periodId");
		String accountDate = "";
		List<ScmDeptConsume> list = new ArrayList<>();
		HashMap<String, Object> map = new HashMap<>();
		if (StringUtils.isNotBlank(materialClassName)) {
			Param param = new Param();
		    param.setOrgUnitNo(currentOrgUnitNo);
		    param.setControlUnitNo(currentControlUnitNo);
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
				}else {
					if (StringUtils.isNotBlank(sbMaterilaClass.toString()))
						sbMaterilaClass.append(",");
					sbMaterilaClass.append(String.valueOf(materialClassId));
				}
			}
			if(StringUtils.isNotBlank(sbMaterilaClass.toString()))
				map.put("materialClassIds", sbMaterilaClass.toString());
		}
		/*StringBuffer materialClassIds=new StringBuffer("");
		String[] classIdList = StringUtils.split(materialClassName, ",");
        for(String classId:classIdList) {
            if(StringUtils.isBlank(classId)) continue;
            if(StringUtils.isNotBlank(materialClassIds.toString()))
            	materialClassIds.append(",");
            materialClassIds.append(classId);
        }*/
        String orgUnitNo=request.getParameter("costOrgUnitNo");
        StringBuffer orgUnitNos=new StringBuffer("");
        Param param = new Param();
		param.setOrgUnitNo(currentOrgUnitNo);
		param.setControlUnitNo(currentControlUnitNo);
		if (StringUtils.isNotBlank(periodId) && StringUtils.isNumeric(periodId)) {
			PeriodCalendar periodCalendar = periodCalendarBiz.select(Long.parseLong(periodId), param);
			if(periodCalendar != null){
				accountDate = FormatUtils.fmtDate(periodCalendar.getStartDate());
			}
		}
		if(StringUtils.isBlank(accountDate)) {
			return null;
		}
        if(StringUtils.isNotBlank(orgUnitNo)) {
            String[] orgUnitNoList = StringUtils.split(orgUnitNo, ",");
            for(String org:orgUnitNoList) {
                if(StringUtils.isBlank(org)) continue;
                List<OrgCostCenter2> orgCostCenterList = orgCostCenterBiz.findChild(org, param);
        		if(orgCostCenterList!=null && !orgCostCenterList.isEmpty()) {
        			for(OrgCostCenter2 orgCostCenter:orgCostCenterList) {
        				if(StringUtils.isNotBlank(orgUnitNos.toString()))
        					orgUnitNos.append(",");
        				orgUnitNos.append("'").append(orgCostCenter.getOrgUnitNo()).append("'");
        			}
        		}
            }
        }else{
        	List<OrgCostCenter2> orgCostCenterList = orgCostCenterBiz.findChild(currentOrgUnitNo, param);
    		if(orgCostCenterList!=null && !orgCostCenterList.isEmpty()) {
    			for(OrgCostCenter2 orgCostCenter:orgCostCenterList) {
    				if(StringUtils.isNotBlank(orgUnitNos.toString()))
    					orgUnitNos.append(",");
    				orgUnitNos.append("'").append(orgCostCenter.getOrgUnitNo()).append("'");
    			}
    		}else {
    			orgUnitNos.append("'").append(currentOrgUnitNo).append("'");
    		}
        }
        map.put("costOrgUnitNos", orgUnitNos.toString());
        //map.put("materialClassIds", materialClassIds.toString());
		map.put("accountDate", accountDate);
		map.put("queryType", StringUtils.isBlank(queryType)?"1":queryType);
		list = ((ScmCostReportDAO) dao).selectDeptSummaryConsume(map);
		if (list != null && list.size() > 0) {
			for (int i = list.size() - 1; i >= 0; i--) {
				ScmDeptConsume scmDeptConsume = list.get(i);
				//过滤金额为0的数据
				BigDecimal amt = BigDecimal.ZERO;
				if(scmDeptConsume.getStartAmt() != null){
					amt = amt.add(scmDeptConsume.getStartAmt().abs());
				}
				if(scmDeptConsume.getPurinAmt()!= null){
					amt = amt.add(scmDeptConsume.getPurinAmt().abs());
				}
				if(scmDeptConsume.getReqAmt()!= null){
					amt = amt.add(scmDeptConsume.getReqAmt().abs());
				}
				if(scmDeptConsume.getCstinAmt()!= null){
					amt = amt.add(scmDeptConsume.getCstinAmt().abs());
				}
				if(scmDeptConsume.getCstoutAmt()!= null){
					amt = amt.add(scmDeptConsume.getCstoutAmt().abs());
				}
				if(scmDeptConsume.getEndAmt()!= null){
					amt = amt.add(scmDeptConsume.getEndAmt().abs());
				}
				if(amt.compareTo(BigDecimal.ZERO) == 0){
					list.remove(scmDeptConsume);
				}
				OrgBaseUnit2 orgBaseUnit = orgUnitBiz.selectbyOrgNo(scmDeptConsume.getCostOrgUnitNo(), param);
				if (orgBaseUnit != null) {
					scmDeptConsume.setOrgUnitName(orgBaseUnit.getOrgUnitName());
				}
			}
		}
		return list;
	}

	@Override
	public List<ScmCostFinDeptConsume> selectDeptConsumeOfCostUse(HttpServletRequest httpServletRequest)
			throws AppException {
		String paramValueMap=httpServletRequest.getParameter("paramValueMap");
		String currentOrgUnitNo=httpServletRequest.getParameter("orgUnitNo");
		String currentControlUnitNo=httpServletRequest.getParameter("controlUnitNo");
        Param param = new Param();
		param.setOrgUnitNo(currentOrgUnitNo);
		param.setControlUnitNo(currentControlUnitNo);
		ScmCostFinDeptConsumeQuery scmDeptConsumeQuery = new ScmCostFinDeptConsumeQuery();
		if(StringUtils.isNotBlank(paramValueMap)) {
			try {
				DesUtils des = new DesUtils("GyYo!N9T");// 自定义密钥
				paramValueMap = des.decrypt(paramValueMap);
				Gson gson = JSONUtils.newGson();
				ReportRequest reportRequest = gson.fromJson(paramValueMap, new TypeToken<ReportRequest>(){}.getType());
				LinkedHashMap<String, Object> map = reportRequest.getParamValueMap();
				scmDeptConsumeQuery.setFinOrgUnitNo(currentOrgUnitNo);
				String periodId = (String)map.get("periodId");
				PeriodCalendar periodCalendar = periodCalendarBiz.selectDirect(Long.valueOf(periodId), param);
				if(periodCalendar==null) {
					throw new AppException("iscm.server.ScmCostReportBizImpl.selectDeptConsumeOfCostUse.periodnotexist");
				}
				scmDeptConsumeQuery.setBegDate(periodCalendar.getStartDate());
				scmDeptConsumeQuery.setEndDate(periodCalendar.getEndDate());
				scmDeptConsumeQuery.setSummaryLevel(Integer.valueOf((String)map.get("summaryLevel")));
				if(map.get("materialClass")!=null) {
					scmDeptConsumeQuery.setMaterialClass((String)map.get("materialClass"));
				}
				if(map.get("costOrgUnitNo")!=null) {
					scmDeptConsumeQuery.setCostOrgUnitNos((String)map.get("costOrgUnitNo"));
				}
				
				StringBuilder orgUnitNos=new StringBuilder("");
				if(StringUtils.isNotBlank(scmDeptConsumeQuery.getCostOrgUnitNos())) {
		            String[] orgUnitNoList = StringUtils.split(scmDeptConsumeQuery.getCostOrgUnitNos(), ",");
		            for(String org:orgUnitNoList) {
		                if(StringUtils.isBlank(org)) continue;
		                String regex = "'";
		                List<OrgCostCenter2> orgCostCenterList = orgCostCenterBiz.findChild(org.replaceAll(regex, ""), param);
		        		if(orgCostCenterList!=null && !orgCostCenterList.isEmpty()) {
		        			for(OrgCostCenter2 orgCostCenter:orgCostCenterList) {
		        				if(StringUtils.isNotBlank(orgUnitNos.toString()))
		        					orgUnitNos.append(",");
		        				orgUnitNos.append("'").append(orgCostCenter.getOrgUnitNo()).append("'");
		        			}
		        		}
		            }
		        }else{
		        	List<OrgCostCenter2> orgCostCenterList = orgCostCenterBiz.findChild(currentOrgUnitNo, param);
		    		if(orgCostCenterList!=null && !orgCostCenterList.isEmpty()) {
		    			for(OrgCostCenter2 orgCostCenter:orgCostCenterList) {
		    				if(StringUtils.isNotBlank(orgUnitNos.toString()))
		    					orgUnitNos.append(",");
		    				orgUnitNos.append("'").append(orgCostCenter.getOrgUnitNo()).append("'");
		    			}
		    		}else {
		    			orgUnitNos.append("'").append(currentOrgUnitNo).append("'");
		    		}
		        }
				if(StringUtils.isNotBlank(orgUnitNos.toString())) {
					scmDeptConsumeQuery.setCostOrgUnitNos(orgUnitNos.toString());
				}
				
			} catch (Exception ex) {
				ex.printStackTrace(); 
            }
		}else {
			throw new AppException("common.controller.baseformcontroller.error.argument");
		}
		return this.selectCostFinConsume(scmDeptConsumeQuery, param);
	}

	@Override
	public List<ScmCostFinDeptConsume> selectCostFinConsume(ScmCostFinDeptConsumeQuery scmDeptConsumeQuery, Param param)throws AppException {
		List<ScmCostFinDeptConsume> scmCostFinDeptConsumeList = new ArrayList<ScmCostFinDeptConsume>();
		String finOrgUnitNo = scmDeptConsumeQuery.getFinOrgUnitNo();
		Date beginDate = scmDeptConsumeQuery.getBegDate();
		Date endDate = scmDeptConsumeQuery.getEndDate();
		int summaryLevel = scmDeptConsumeQuery.getSummaryLevel();
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put(ScmCostUseSet2.FN_ORGUNITNO, finOrgUnitNo);
		List<ScmCostUseSet2> ScmCostUseSetList = scmCostUseSetBiz.findAll(map, param);
		HashMap<String,Long> costUseSetMap = new HashMap<String,Long>();
		if(ScmCostUseSetList!=null && !ScmCostUseSetList.isEmpty()) {
			for(ScmCostUseSet2 ScmCostUseSet:ScmCostUseSetList) {
				costUseSetMap.put(ScmCostUseSet.getCostOrgUnitNo()+"_"+ScmCostUseSet.getClassId(), ScmCostUseSet.getCostUseTypeId());
			}
		}
		
		List<OrgCompany2> orgCompanys = orgCompanyBiz.findChild(scmDeptConsumeQuery.getFinOrgUnitNo(), param);
		StringBuilder orgComNos = new StringBuilder();
		if(orgCompanys!=null && !orgCompanys.isEmpty()) {
			for(OrgCompany orgcompany:orgCompanys) {
				if(StringUtils.isNotBlank(orgComNos.toString())) 
					orgComNos.append(",");
				orgComNos.append("'").append(orgcompany.getOrgUnitNo()).append("'");
			}
		}else {
			orgComNos.append("'").append(scmDeptConsumeQuery.getFinOrgUnitNo()).append("'");
		}
		finOrgUnitNo = orgComNos.toString();
		
		StringBuffer sbMaterilaClass = new StringBuffer("");
		if (StringUtils.isNotBlank(scmDeptConsumeQuery.getMaterialClass())) {
			String[] materialClassNameList = StringUtils.split(scmDeptConsumeQuery.getMaterialClass(), ",");
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
		// 获取期间期初数
		PeriodCalendar periodCalendar = periodCalendarBiz.selectByBizdate(beginDate, param);
		if (periodCalendar != null) {
			Page page = new Page();
			page.setModelClass(ScmInvBal.class);
			page.setShowCount(Integer.MAX_VALUE);
			page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvBal.class) + "." + ScmInvBal.FN_FINORGUNITNO,
					new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvBal.class) + "." + ScmInvBal.FN_FINORGUNITNO,
							QueryParam.QUERY_IN, finOrgUnitNo));
			page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvBal.class) + "." + ScmInvBal.FN_PERIODID,
					new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvBal.class) + "." + ScmInvBal.FN_PERIODID,
							QueryParam.QUERY_EQ, String.valueOf(periodCalendar.getPeriodId())));
			page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvBal.class) + "." + ScmInvBal.FN_COSTCENTER,
					new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvBal.class) + "." + ScmInvBal.FN_COSTCENTER,
							QueryParam.QUERY_EQ, "1"));
			if (StringUtils.isNotBlank(sbMaterilaClass.toString())) {
				page.getParam()
						.put(ClassUtils.getFinalModelSimpleName(ScmMaterialGroup.class) + "." + ScmMaterialGroup.FN_ID,
								new QueryParam(
										ClassUtils.getFinalModelSimpleName(ScmMaterialGroup.class) + "."
												+ ScmMaterialGroup.FN_ID,
										QueryParam.QUERY_IN, sbMaterilaClass.toString()));
			}
			if (StringUtils.isNotBlank(scmDeptConsumeQuery.getCostOrgUnitNos())) {
				page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvBal.class) + "." + ScmInvBal.FN_COSTORGUNITNO,
						new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvBal.class) + "."+ ScmInvBal.FN_COSTORGUNITNO,
								QueryParam.QUERY_IN, scmDeptConsumeQuery.getCostOrgUnitNos()));
			}
			List<String> arglist = new ArrayList<>();
			List<ScmInvBal2> scmInvBalList = scmInvBalBiz.queryPage(page, arglist, "selectCostConsumePage", param);
			if (scmInvBalList != null && !scmInvBalList.isEmpty()) {
				for (ScmInvBal2 scmInvBal : scmInvBalList) {
					// 以领代耗的期初不处理，认为是零
					OrgCostCenter2 orgCostCenter = orgCostCenterBiz.selectByOrgUnitNo(scmInvBal.getCostOrgUnitNo(),param);
					if (orgCostCenter != null && StringUtils.equals("1", orgCostCenter.getCostCenterType())) {
						continue;
					}
					ScmCostFinDeptConsume scmCostFinDeptConsume = new ScmCostFinDeptConsume(true);
					scmCostFinDeptConsume.setFinOrgUnitNo(scmInvBal.getFinOrgUnitNo());
					scmCostFinDeptConsume.setCostOrgUnitNo(scmInvBal.getCostOrgUnitNo());
					scmCostFinDeptConsume.setCostOrgUnitName(orgCostCenter.getOrgUnitName());
					scmCostFinDeptConsume.setGroupId(scmInvBal.getGroupId());
					long costUseTypeId = 0;
					if(costUseSetMap.containsKey(scmInvBal.getCostOrgUnitNo()+"_"+scmInvBal.getGroupId())) {
						costUseTypeId = costUseSetMap.get(scmInvBal.getCostOrgUnitNo()+"_"+scmInvBal.getGroupId());
					}
					scmCostFinDeptConsume.setCostUseTypeId(costUseTypeId);
					scmCostFinDeptConsume.setLongNo(scmInvBal.getLongNo());
					scmCostFinDeptConsume.setInitAmt(scmInvBal.getStartAmt());
					scmCostFinDeptConsume.setInitTax(scmInvBal.getStartTaxAmt().subtract(scmInvBal.getStartAmt()));
					scmCostFinDeptConsume.setInitTaxAmt(scmInvBal.getStartTaxAmt());
					scmCostFinDeptConsumeList.add(scmCostFinDeptConsume);
				}
			}
		}
		Page page = new Page();
		//采购入库
        page.setModelClass(ScmInvPurInWarehsBillEntry2.class);
        page.setShowCount(Integer.MAX_VALUE);
        page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBill2.class)+"."+ScmInvPurInWarehsBill2.FN_STATUS, 
                new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBill2.class)+"."+ScmInvPurInWarehsBill2.FN_STATUS, QueryParam.QUERY_EQ, "E"));
        page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBill2.class)+"."+ScmInvPurInWarehsBill2.FN_FINORGUNITNO, 
                new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBill2.class)+"."+ScmInvPurInWarehsBill2.FN_FINORGUNITNO, QueryParam.QUERY_IN, finOrgUnitNo));
        page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBill2.class)+"."+ScmInvPurInWarehsBill2.FN_BIZDATE, 
                new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBill2.class)+"."+ScmInvPurInWarehsBill2.FN_BIZDATE, QueryParam.QUERY_BETWEEN, FormatUtils.fmtDate(beginDate),FormatUtils.fmtDate(endDate)));
        if (StringUtils.isNotBlank(sbMaterilaClass.toString())) {
			page.getParam()
					.put(ClassUtils.getFinalModelSimpleName(ScmMaterialGroup.class) + "." + ScmMaterialGroup.FN_ID,
							new QueryParam(
									ClassUtils.getFinalModelSimpleName(ScmMaterialGroup.class) + "."
											+ ScmMaterialGroup.FN_ID,
									QueryParam.QUERY_IN, sbMaterilaClass.toString()));
		}
        if (StringUtils.isNotBlank(scmDeptConsumeQuery.getCostOrgUnitNos())) {
			page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBillEntry2.class) + "." + ScmInvPurInWarehsBillEntry2.FN_COSTORGUNITNO,
					new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBillEntry2.class) + "."+ ScmInvPurInWarehsBillEntry2.FN_COSTORGUNITNO,
							QueryParam.QUERY_IN, scmDeptConsumeQuery.getCostOrgUnitNos()));
		}
        List<String> arglist = new ArrayList<>();
        arglist.add("controlUnitNo="+param.getControlUnitNo());
		List<ScmInvPurInWarehsBillEntry2> scmInvPurInWarehsBillEntryList = scmInvPurInWarehsBillEntryBiz.queryPage(page, arglist, "selectCostConsumePage", param);
		if(scmInvPurInWarehsBillEntryList!=null && !scmInvPurInWarehsBillEntryList.isEmpty()) {
			for (ScmInvPurInWarehsBillEntry2 scmInvPurInWarehsBillEntry : scmInvPurInWarehsBillEntryList) {
				OrgCostCenter2 orgCostCenter = orgCostCenterBiz.selectByOrgUnitNo(scmInvPurInWarehsBillEntry.getCostOrgUnitNo(),param);
				boolean exists=false;
				ScmCostFinDeptConsume scmCostFinDeptConsume = new ScmCostFinDeptConsume(true);
				if(!scmCostFinDeptConsumeList.isEmpty()) {
					for (ScmCostFinDeptConsume existScmCostFinDeptConsume:scmCostFinDeptConsumeList) {
						if (existScmCostFinDeptConsume.getGroupId() == scmInvPurInWarehsBillEntry.getGroupId() && StringUtils.equals(
								existScmCostFinDeptConsume.getCostOrgUnitNo(), scmInvPurInWarehsBillEntry.getCostOrgUnitNo())
								&& existScmCostFinDeptConsume.getCostUseTypeId()==scmInvPurInWarehsBillEntry.getCostUseTypeId()) {
							existScmCostFinDeptConsume.setAddInAmt(existScmCostFinDeptConsume.getAddInAmt().add(scmInvPurInWarehsBillEntry.getAmt()));
							existScmCostFinDeptConsume.setAddInTax(existScmCostFinDeptConsume.getAddInTax().add(scmInvPurInWarehsBillEntry.getTaxAmt().subtract(scmInvPurInWarehsBillEntry.getAmt())));
							existScmCostFinDeptConsume.setAddInTaxAmt(existScmCostFinDeptConsume.getAddInTaxAmt().add(scmInvPurInWarehsBillEntry.getTaxAmt()));
							scmCostFinDeptConsume = existScmCostFinDeptConsume;
							exists = true;
						}
					}
				}
				if(!exists) {
					//放入明细记录结果集中
					scmCostFinDeptConsume.setFinOrgUnitNo(scmInvPurInWarehsBillEntry.getFinOrgUnitNo());
					scmCostFinDeptConsume.setCostOrgUnitNo(scmInvPurInWarehsBillEntry.getCostOrgUnitNo());
					scmCostFinDeptConsume.setCostOrgUnitName(orgCostCenter==null?"":orgCostCenter.getOrgUnitName());
					scmCostFinDeptConsume.setGroupId(scmInvPurInWarehsBillEntry.getGroupId());
					scmCostFinDeptConsume.setLongNo(scmInvPurInWarehsBillEntry.getLongNo());
					scmCostFinDeptConsume.setCostUseTypeId(scmInvPurInWarehsBillEntry.getCostUseTypeId());
					scmCostFinDeptConsume.setAddInAmt(scmInvPurInWarehsBillEntry.getAmt());
					scmCostFinDeptConsume.setAddInTax(scmInvPurInWarehsBillEntry.getTaxAmt().subtract(scmInvPurInWarehsBillEntry.getAmt()));
					scmCostFinDeptConsume.setAddInTaxAmt(scmInvPurInWarehsBillEntry.getTaxAmt());
					scmCostFinDeptConsumeList.add(scmCostFinDeptConsume);
				}
				if (orgCostCenter != null && StringUtils.equals("1", orgCostCenter.getCostCenterType())) {
					//以领代耗时直拨则认为耗用
					scmCostFinDeptConsume.setCostAmt(scmCostFinDeptConsume.getCostAmt().add(scmInvPurInWarehsBillEntry.getAmt()));
					scmCostFinDeptConsume.setCostTax(scmCostFinDeptConsume.getCostTax().add(scmInvPurInWarehsBillEntry.getTaxAmt().subtract(scmInvPurInWarehsBillEntry.getAmt())));
					scmCostFinDeptConsume.setCostTaxAmt(scmCostFinDeptConsume.getOutTaxAmt().add(scmInvPurInWarehsBillEntry.getTaxAmt()));
				}
			}
		}
		//领料出库
		page = new Page();
        page.setModelClass(ScmInvPurInWarehsBillEntry2.class);
        page.setShowCount(Integer.MAX_VALUE);
        page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBill.class)+"."+ScmInvMaterialReqBill.FN_FINORGUNITNO, 
                new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBill.class)+"."+ScmInvMaterialReqBill.FN_FINORGUNITNO, QueryParam.QUERY_IN, finOrgUnitNo));
        page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBill2.class)+"."+ScmInvMaterialReqBill2.FN_BIZDATE, 
                new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBill2.class)+"."+ScmInvMaterialReqBill2.FN_BIZDATE, QueryParam.QUERY_BETWEEN, FormatUtils.fmtDate(beginDate),FormatUtils.fmtDate(endDate)));
        page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBill2.class)+"."+ScmInvMaterialReqBill2.FN_STATUS, 
                new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBill2.class)+"."+ScmInvMaterialReqBill2.FN_STATUS, QueryParam.QUERY_EQ, "E"));
        if (StringUtils.isNotBlank(sbMaterilaClass.toString())) {
			page.getParam()
					.put(ClassUtils.getFinalModelSimpleName(ScmMaterialGroup.class) + "." + ScmMaterialGroup.FN_ID,
							new QueryParam(
									ClassUtils.getFinalModelSimpleName(ScmMaterialGroup.class) + "."
											+ ScmMaterialGroup.FN_ID,
									QueryParam.QUERY_IN, sbMaterilaClass.toString()));
		}
        if (StringUtils.isNotBlank(scmDeptConsumeQuery.getCostOrgUnitNos())) {
			page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBill.class) + "." + ScmInvMaterialReqBill.FN_COSTORGUNITNO,
					new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBill.class) + "."+ ScmInvMaterialReqBill.FN_COSTORGUNITNO,
							QueryParam.QUERY_IN, scmDeptConsumeQuery.getCostOrgUnitNos()));
		}
        List<ScmInvMaterialReqBillEntry2> scmInvMaterialReqBillEntryList = scmInvMaterialReqBillEntryBiz.queryPage(page, arglist, "selectCostConsumePage", param);
		if(scmInvMaterialReqBillEntryList!=null && !scmInvMaterialReqBillEntryList.isEmpty()) {
			for (ScmInvMaterialReqBillEntry2 scmInvMaterialReqBillEntry : scmInvMaterialReqBillEntryList) {
				OrgCostCenter2 orgCostCenter = orgCostCenterBiz.selectByOrgUnitNo(scmInvMaterialReqBillEntry.getCostOrgUnitNo(),param);
				boolean exists=false;
				ScmCostFinDeptConsume scmCostFinDeptConsume = new ScmCostFinDeptConsume(true);
				if(!scmCostFinDeptConsumeList.isEmpty()) {
					for (ScmCostFinDeptConsume existScmCostFinDeptConsume:scmCostFinDeptConsumeList) {
						if (existScmCostFinDeptConsume.getGroupId() == scmInvMaterialReqBillEntry.getGroupId() && StringUtils.equals(
								existScmCostFinDeptConsume.getCostOrgUnitNo(), scmInvMaterialReqBillEntry.getCostOrgUnitNo())
								&& existScmCostFinDeptConsume.getCostUseTypeId()==scmInvMaterialReqBillEntry.getCostUseTypeId()) {
							existScmCostFinDeptConsume.setOutAmt(existScmCostFinDeptConsume.getOutAmt().add(scmInvMaterialReqBillEntry.getAmt()));
							existScmCostFinDeptConsume.setOutTax(existScmCostFinDeptConsume.getOutTax().add(scmInvMaterialReqBillEntry.getTaxAmt().subtract(scmInvMaterialReqBillEntry.getAmt())));
							existScmCostFinDeptConsume.setOutTaxAmt(existScmCostFinDeptConsume.getOutTaxAmt().add(scmInvMaterialReqBillEntry.getTaxAmt()));
							scmCostFinDeptConsume = existScmCostFinDeptConsume;
							exists = true;
						}
					}
				}
				if(!exists) {
					//放入明细记录结果集中
					scmCostFinDeptConsume.setFinOrgUnitNo(scmInvMaterialReqBillEntry.getFinOrgUnitNo());
					scmCostFinDeptConsume.setCostOrgUnitNo(scmInvMaterialReqBillEntry.getCostOrgUnitNo());
					scmCostFinDeptConsume.setCostOrgUnitName(orgCostCenter==null?"":orgCostCenter.getOrgUnitName());
					scmCostFinDeptConsume.setGroupId(scmInvMaterialReqBillEntry.getGroupId());
					scmCostFinDeptConsume.setLongNo(scmInvMaterialReqBillEntry.getLongNo());
					scmCostFinDeptConsume.setCostUseTypeId(scmInvMaterialReqBillEntry.getCostUseTypeId());
					scmCostFinDeptConsume.setOutAmt(scmInvMaterialReqBillEntry.getAmt());
					scmCostFinDeptConsume.setOutTax(scmInvMaterialReqBillEntry.getTaxAmt().subtract(scmInvMaterialReqBillEntry.getAmt()));
					scmCostFinDeptConsume.setOutTaxAmt(scmInvMaterialReqBillEntry.getTaxAmt());
					scmCostFinDeptConsumeList.add(scmCostFinDeptConsume);
				}
				if (orgCostCenter != null && StringUtils.equals("1", orgCostCenter.getCostCenterType())) {
					//以领代耗时领料则认为耗用
					scmCostFinDeptConsume.setCostAmt(scmCostFinDeptConsume.getCostAmt().add(scmInvMaterialReqBillEntry.getAmt()));
					scmCostFinDeptConsume.setCostTax(scmCostFinDeptConsume.getCostTax().add(scmInvMaterialReqBillEntry.getTaxAmt().subtract(scmInvMaterialReqBillEntry.getAmt())));
					scmCostFinDeptConsume.setCostTaxAmt(scmCostFinDeptConsume.getOutTaxAmt().add(scmInvMaterialReqBillEntry.getTaxAmt()));
				}
			}
		}
		//成本转移(转入)
		page = new Page();
        page.setModelClass(ScmInvMoveBillEntry2.class);
        page.setShowCount(Integer.MAX_VALUE);
        page.getParamOr().put(ClassUtils.getFinalModelSimpleName(ScmInvMoveBill.class)+"."+ScmInvMoveBill.FN_FINORGUNITNO, 
                new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMoveBill.class)+"."+ScmInvMoveBill.FN_FINORGUNITNO, QueryParam.QUERY_IN, finOrgUnitNo));
        page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvMoveBill.class)+"."+ScmInvMoveBill.FN_STATUS, 
                new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMoveBill.class)+"."+ScmInvMoveBill.FN_STATUS, QueryParam.QUERY_EQ, "E"));
        page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvMoveBill.class)+"."+ScmInvMoveBill.FN_BIZDATE, 
                new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMoveBill.class)+"."+ScmInvMoveBill.FN_BIZDATE, QueryParam.QUERY_BETWEEN, FormatUtils.fmtDate(beginDate),FormatUtils.fmtDate(endDate)));
        if (StringUtils.isNotBlank(sbMaterilaClass.toString())) {
			page.getParam()
					.put(ClassUtils.getFinalModelSimpleName(ScmMaterialGroup.class) + "." + ScmMaterialGroup.FN_ID,
							new QueryParam(
									ClassUtils.getFinalModelSimpleName(ScmMaterialGroup.class) + "."
											+ ScmMaterialGroup.FN_ID,
									QueryParam.QUERY_IN, sbMaterilaClass.toString()));
		}
        if (StringUtils.isNotBlank(scmDeptConsumeQuery.getCostOrgUnitNos())) {
			page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvMoveBill.class) + "." + ScmInvMoveBill.FN_INCSTORGUNITNO,
					new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMoveBill.class) + "."+ ScmInvMoveBill.FN_INCSTORGUNITNO,
							QueryParam.QUERY_IN, scmDeptConsumeQuery.getCostOrgUnitNos()));
		}
        List<ScmInvMoveBillEntry2> scmInvMoveBillEntryList = scmInvMoveBillEntryBiz.queryPage(page, arglist, "selectMoveInSumPage", param);
        if(scmInvMoveBillEntryList!=null && !scmInvMoveBillEntryList.isEmpty()) {
        	for (ScmInvMoveBillEntry2 scmInvMoveBillEntry : scmInvMoveBillEntryList) {
				OrgCostCenter2 orgCostCenter = orgCostCenterBiz.selectByOrgUnitNo(scmInvMoveBillEntry.getInCstOrgUnitNo(),param);
				boolean exists=false;
				ScmCostFinDeptConsume scmCostFinDeptConsume = new ScmCostFinDeptConsume(true);
				if(!scmCostFinDeptConsumeList.isEmpty()) {
					for (ScmCostFinDeptConsume existScmCostFinDeptConsume:scmCostFinDeptConsumeList) {
						if (existScmCostFinDeptConsume.getGroupId() == scmInvMoveBillEntry.getGroupId() && StringUtils.equals(
								existScmCostFinDeptConsume.getCostOrgUnitNo(), scmInvMoveBillEntry.getInCstOrgUnitNo())
								&& existScmCostFinDeptConsume.getCostUseTypeId()==scmInvMoveBillEntry.getCostUseTypeInId()) {
							existScmCostFinDeptConsume.setMoveInAmt(existScmCostFinDeptConsume.getMoveInAmt().add(scmInvMoveBillEntry.getAmt()));
							existScmCostFinDeptConsume.setMoveInTax(existScmCostFinDeptConsume.getMoveInTax().add(scmInvMoveBillEntry.getTaxAmt().subtract(scmInvMoveBillEntry.getAmt())));
							existScmCostFinDeptConsume.setMoveInTaxAmt(existScmCostFinDeptConsume.getMoveInTaxAmt().add(scmInvMoveBillEntry.getTaxAmt()));
							scmCostFinDeptConsume = existScmCostFinDeptConsume;
							exists = true;
						}
					}
				}
				if(!exists) {
					//放入明细记录结果集中
					scmCostFinDeptConsume.setFinOrgUnitNo(scmInvMoveBillEntry.getFinOrgUnitNo());
					scmCostFinDeptConsume.setCostOrgUnitNo(scmInvMoveBillEntry.getInCstOrgUnitNo());
					scmCostFinDeptConsume.setCostOrgUnitName(orgCostCenter==null?"":orgCostCenter.getOrgUnitName());
					scmCostFinDeptConsume.setGroupId(scmInvMoveBillEntry.getGroupId());
					scmCostFinDeptConsume.setLongNo(scmInvMoveBillEntry.getLongNo());
					scmCostFinDeptConsume.setCostUseTypeId(scmInvMoveBillEntry.getCostUseTypeInId());
					scmCostFinDeptConsume.setMoveInAmt(scmInvMoveBillEntry.getAmt());
					scmCostFinDeptConsume.setMoveInTax(scmInvMoveBillEntry.getTaxAmt().subtract(scmInvMoveBillEntry.getAmt()));
					scmCostFinDeptConsume.setMoveInTaxAmt(scmInvMoveBillEntry.getTaxAmt());
					scmCostFinDeptConsumeList.add(scmCostFinDeptConsume);
				}
				if (orgCostCenter != null && StringUtils.equals("1", orgCostCenter.getCostCenterType())) {
					//以领代耗时领料则认为耗用
					scmCostFinDeptConsume.setCostAmt(scmCostFinDeptConsume.getCostAmt().add(scmInvMoveBillEntry.getAmt()));
					scmCostFinDeptConsume.setCostTax(scmCostFinDeptConsume.getCostTax().add(scmInvMoveBillEntry.getTaxAmt().subtract(scmInvMoveBillEntry.getAmt())));
					scmCostFinDeptConsume.setCostTaxAmt(scmCostFinDeptConsume.getOutTaxAmt().add(scmInvMoveBillEntry.getTaxAmt()));
				}
			}
        }
      //成本转移(转出)
        page = new Page();
        page.setModelClass(ScmInvMoveBillEntry2.class);
        page.setShowCount(Integer.MAX_VALUE);
        page.getParamOr().put(ClassUtils.getFinalModelSimpleName(ScmInvMoveBill.class)+"."+ScmInvMoveBill.FN_FINORGUNITNO, 
                new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMoveBill.class)+"."+ScmInvMoveBill.FN_FINORGUNITNO, QueryParam.QUERY_IN, finOrgUnitNo));
        page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvMoveBill.class)+"."+ScmInvMoveBill.FN_STATUS, 
                new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMoveBill.class)+"."+ScmInvMoveBill.FN_STATUS, QueryParam.QUERY_EQ, "E"));
        page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvMoveBill.class)+"."+ScmInvMoveBill.FN_BIZDATE, 
                new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMoveBill.class)+"."+ScmInvMoveBill.FN_BIZDATE, QueryParam.QUERY_BETWEEN, FormatUtils.fmtDate(beginDate),FormatUtils.fmtDate(endDate)));
        if (StringUtils.isNotBlank(sbMaterilaClass.toString())) {
			page.getParam()
					.put(ClassUtils.getFinalModelSimpleName(ScmMaterialGroup.class) + "." + ScmMaterialGroup.FN_ID,
							new QueryParam(
									ClassUtils.getFinalModelSimpleName(ScmMaterialGroup.class) + "."
											+ ScmMaterialGroup.FN_ID,
									QueryParam.QUERY_IN, sbMaterilaClass.toString()));
		}
        if (StringUtils.isNotBlank(scmDeptConsumeQuery.getCostOrgUnitNos())) {
			page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvMoveBill.class) + "." + ScmInvMoveBill.FN_OUTCSTORGUNITNO,
					new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMoveBill.class) + "."+ ScmInvMoveBill.FN_OUTCSTORGUNITNO,
							QueryParam.QUERY_IN, scmDeptConsumeQuery.getCostOrgUnitNos()));
		}
        List<ScmInvMoveBillEntry2> scmInvMoveOutBillEntryList = scmInvMoveBillEntryBiz.queryPage(page, arglist, "selectMoveOutSumPage", param);
        if(scmInvMoveOutBillEntryList!=null && !scmInvMoveOutBillEntryList.isEmpty()) {
        	for (ScmInvMoveBillEntry2 scmInvMoveBillEntry : scmInvMoveOutBillEntryList) {
				OrgCostCenter2 orgCostCenter = orgCostCenterBiz.selectByOrgUnitNo(scmInvMoveBillEntry.getOutCstOrgUnitNo(),param);
				boolean exists=false;
				ScmCostFinDeptConsume scmCostFinDeptConsume = new ScmCostFinDeptConsume(true);
				if(!scmCostFinDeptConsumeList.isEmpty()) {
					for (ScmCostFinDeptConsume existScmCostFinDeptConsume:scmCostFinDeptConsumeList) {
						if (existScmCostFinDeptConsume.getGroupId() == scmInvMoveBillEntry.getGroupId() && StringUtils.equals(
								existScmCostFinDeptConsume.getCostOrgUnitNo(), scmInvMoveBillEntry.getOutCstOrgUnitNo())
								&& existScmCostFinDeptConsume.getCostUseTypeId()==scmInvMoveBillEntry.getCostUseTypeInId()) {
							existScmCostFinDeptConsume.setMoveOutAmt(existScmCostFinDeptConsume.getMoveOutAmt().add(scmInvMoveBillEntry.getAmt()));
							existScmCostFinDeptConsume.setMoveOutTax(existScmCostFinDeptConsume.getMoveOutTax().add(scmInvMoveBillEntry.getTaxAmt().subtract(scmInvMoveBillEntry.getAmt())));
							existScmCostFinDeptConsume.setMoveOutTaxAmt(existScmCostFinDeptConsume.getMoveOutTaxAmt().add(scmInvMoveBillEntry.getTaxAmt()));
							scmCostFinDeptConsume = existScmCostFinDeptConsume;
							exists = true;
						}
					}
				}
				if(!exists) {
					//放入明细记录结果集中
					scmCostFinDeptConsume.setFinOrgUnitNo(scmInvMoveBillEntry.getFinOrgUnitNo());
					scmCostFinDeptConsume.setCostOrgUnitNo(scmInvMoveBillEntry.getInCstOrgUnitNo());
					scmCostFinDeptConsume.setCostOrgUnitName(orgCostCenter==null?"":orgCostCenter.getOrgUnitName());
					scmCostFinDeptConsume.setGroupId(scmInvMoveBillEntry.getGroupId());
					scmCostFinDeptConsume.setLongNo(scmInvMoveBillEntry.getLongNo());
					scmCostFinDeptConsume.setCostUseTypeId(scmInvMoveBillEntry.getCostUseTypeInId());
					scmCostFinDeptConsume.setMoveOutAmt(scmInvMoveBillEntry.getAmt());
					scmCostFinDeptConsume.setMoveOutTax(scmInvMoveBillEntry.getTaxAmt().subtract(scmInvMoveBillEntry.getAmt()));
					scmCostFinDeptConsume.setMoveOutTaxAmt(scmInvMoveBillEntry.getTaxAmt());
					scmCostFinDeptConsumeList.add(scmCostFinDeptConsume);
				}
				if (orgCostCenter != null && StringUtils.equals("1", orgCostCenter.getCostCenterType())) {
					//以领代耗时领料则认为耗用
					scmCostFinDeptConsume.setCostAmt(scmCostFinDeptConsume.getCostAmt().subtract(scmInvMoveBillEntry.getAmt()));
					scmCostFinDeptConsume.setCostTax(scmCostFinDeptConsume.getCostTax().subtract(scmInvMoveBillEntry.getTaxAmt().subtract(scmInvMoveBillEntry.getAmt())));
					scmCostFinDeptConsume.setCostTaxAmt(scmCostFinDeptConsume.getOutTaxAmt().subtract(scmInvMoveBillEntry.getTaxAmt()));
				}
			}
        }
        //耗用单
        page = new Page();
        page.setModelClass(ScmInvCostConsumeEntry2.class);
        page.setShowCount(Integer.MAX_VALUE);
        page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvCostConsume.class) + "." + ScmInvCostConsume.FN_GENERATE,
				new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvCostConsume.class) + "."+ ScmInvCostConsume.FN_GENERATE, QueryParam.QUERY_EQ, "0"));
        page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvCostConsume.class)+"."+ScmInvCostConsume.FN_FINORGUNITNO, 
                new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvCostConsume.class)+"."+ScmInvCostConsume.FN_FINORGUNITNO, QueryParam.QUERY_IN, finOrgUnitNo));
        page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvCostConsume.class)+"."+ScmInvCostConsume.FN_BIZDATE, 
                new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvCostConsume.class)+"."+ScmInvCostConsume.FN_BIZDATE, QueryParam.QUERY_BETWEEN, FormatUtils.fmtDate(beginDate),FormatUtils.fmtDate(endDate)));
        page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvCostConsume.class)+"."+ScmInvCostConsume.FN_STATUS, 
        		new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvCostConsume.class)+"."+ScmInvCostConsume.FN_STATUS, QueryParam.QUERY_EQ, "E"));
        if (StringUtils.isNotBlank(sbMaterilaClass.toString())) {
			page.getParam()
					.put(ClassUtils.getFinalModelSimpleName(ScmMaterialGroup.class) + "." + ScmMaterialGroup.FN_ID,
							new QueryParam(
									ClassUtils.getFinalModelSimpleName(ScmMaterialGroup.class) + "."
											+ ScmMaterialGroup.FN_ID,
									QueryParam.QUERY_IN, sbMaterilaClass.toString()));
		}
        if (StringUtils.isNotBlank(scmDeptConsumeQuery.getCostOrgUnitNos())) {
			page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvCostConsume.class) + "." + ScmInvCostConsume.FN_ORGUNITNO,
					new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvCostConsume.class) + "."+ ScmInvCostConsume.FN_ORGUNITNO,
							QueryParam.QUERY_IN, scmDeptConsumeQuery.getCostOrgUnitNos()));
		}
        List<ScmInvCostConsumeEntry2> scmInvCostConsumeEntryList = scmInvCostConsumeEntryBiz.queryPage(page, arglist, "selectCostConsumePage", param);
        if(scmInvCostConsumeEntryList!=null && !scmInvCostConsumeEntryList.isEmpty()) {
        	for (ScmInvCostConsumeEntry2 scmInvCostConsumeEntry : scmInvCostConsumeEntryList) {
	        	OrgCostCenter2 orgCostCenter = orgCostCenterBiz.selectByOrgUnitNo(scmInvCostConsumeEntry.getCostOrgUnitNo(),param);
	        	if (orgCostCenter != null && StringUtils.equals("1", orgCostCenter.getCostCenterType())) {
					continue;
				}
	        	long costUseTypeId = 0;
				if(costUseSetMap.containsKey(scmInvCostConsumeEntry.getCostOrgUnitNo()+"_"+scmInvCostConsumeEntry.getGroupId())) {
					costUseTypeId = costUseSetMap.get(scmInvCostConsumeEntry.getCostOrgUnitNo()+"_"+scmInvCostConsumeEntry.getGroupId());
				}
				boolean exists=false;
				ScmCostFinDeptConsume scmCostFinDeptConsume = new ScmCostFinDeptConsume(true);
				if(!scmCostFinDeptConsumeList.isEmpty()) {
					for (ScmCostFinDeptConsume existScmCostFinDeptConsume:scmCostFinDeptConsumeList) {
						if (existScmCostFinDeptConsume.getGroupId() == scmInvCostConsumeEntry.getGroupId() && StringUtils.equals(
								existScmCostFinDeptConsume.getCostOrgUnitNo(), scmInvCostConsumeEntry.getCostOrgUnitNo())
								&& existScmCostFinDeptConsume.getCostUseTypeId()==costUseTypeId) {
							existScmCostFinDeptConsume.setCostAmt(existScmCostFinDeptConsume.getCostAmt().add(scmInvCostConsumeEntry.getAmt()));
							existScmCostFinDeptConsume.setCostTax(existScmCostFinDeptConsume.getCostTax().add(scmInvCostConsumeEntry.getTaxAmt().subtract(scmInvCostConsumeEntry.getAmt())));
							existScmCostFinDeptConsume.setCostTaxAmt(existScmCostFinDeptConsume.getCostTaxAmt().add(scmInvCostConsumeEntry.getTaxAmt()));
							scmCostFinDeptConsume = existScmCostFinDeptConsume;
							exists = true;
						}
					}
				}
				if(!exists) {
					//放入明细记录结果集中
					scmCostFinDeptConsume.setFinOrgUnitNo(scmInvCostConsumeEntry.getFinOrgUnitNo());
					scmCostFinDeptConsume.setCostOrgUnitNo(scmInvCostConsumeEntry.getCostOrgUnitNo());
					scmCostFinDeptConsume.setCostOrgUnitName(orgCostCenter==null?"":orgCostCenter.getOrgUnitName());
					scmCostFinDeptConsume.setGroupId(scmInvCostConsumeEntry.getGroupId());
					scmCostFinDeptConsume.setLongNo(scmInvCostConsumeEntry.getLongNo());
					scmCostFinDeptConsume.setCostUseTypeId(costUseTypeId);
					scmCostFinDeptConsume.setCostAmt(scmInvCostConsumeEntry.getAmt());
					scmCostFinDeptConsume.setCostTax(scmInvCostConsumeEntry.getTaxAmt().subtract(scmInvCostConsumeEntry.getAmt()));
					scmCostFinDeptConsume.setCostTaxAmt(scmInvCostConsumeEntry.getTaxAmt());
					scmCostFinDeptConsumeList.add(scmCostFinDeptConsume);
				}
        	}
        }
        //盘存
        page = new Page();
        page.setModelClass(ScmInvCountingCostCenterEntry2.class);
        page.setShowCount(Integer.MAX_VALUE);
        page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvCountingTask.class)+"."+ScmInvCountingTask.FN_STATUS, 
	            new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvCountingTask.class)+"."+ScmInvCountingTask.FN_STATUS, QueryParam.QUERY_EQ, "O"));
        page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvCountingTask.class)+"."+ScmInvCountingTask.FN_FINORGUNITNO, 
                new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvCountingTask.class)+"."+ScmInvCountingTask.FN_FINORGUNITNO, QueryParam.QUERY_IN, finOrgUnitNo));
        page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvCountingTask.class)+"."+ScmInvCountingTask.FN_BIZDATE, 
                new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvCountingTask.class)+"."+ScmInvCountingTask.FN_BIZDATE, QueryParam.QUERY_BETWEEN, FormatUtils.fmtDate(beginDate),FormatUtils.fmtDate(endDate)));
        if (StringUtils.isNotBlank(sbMaterilaClass.toString())) {
			page.getParam()
					.put(ClassUtils.getFinalModelSimpleName(ScmMaterialGroup.class) + "." + ScmMaterialGroup.FN_ID,
							new QueryParam(
									ClassUtils.getFinalModelSimpleName(ScmMaterialGroup.class) + "."
											+ ScmMaterialGroup.FN_ID,
									QueryParam.QUERY_IN, sbMaterilaClass.toString()));
		}
        if (StringUtils.isNotBlank(scmDeptConsumeQuery.getCostOrgUnitNos())) {
			page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvCountingCostCenter.class) + "." + ScmInvCountingCostCenter.FN_ORGUNITNO,
					new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvCountingCostCenter.class) + "."+ ScmInvCountingCostCenter.FN_ORGUNITNO,
							QueryParam.QUERY_IN, scmDeptConsumeQuery.getCostOrgUnitNos()));
		}
        List<ScmInvCountingCostCenterEntry2> scmInvCountingCostCenterEntryList = scmInvCountingCostCenterEntryBiz.queryPage(page, arglist, "selectCostConsumePage", param);
        if (scmInvCountingCostCenterEntryList != null && !scmInvCountingCostCenterEntryList.isEmpty()) {
        	for (ScmInvCountingCostCenterEntry2 scmInvCountingCostCenterEntry : scmInvCountingCostCenterEntryList) {
	        	OrgCostCenter2 orgCostCenter = orgCostCenterBiz.selectByOrgUnitNo(scmInvCountingCostCenterEntry.getOrgUnitNo(),param);
	        	if (orgCostCenter != null && StringUtils.equals("1", orgCostCenter.getCostCenterType())) {
					continue;
				}
	        	long costUseTypeId = 0;
				if(costUseSetMap.containsKey(scmInvCountingCostCenterEntry.getOrgUnitNo()+"_"+scmInvCountingCostCenterEntry.getGroupId())) {
					costUseTypeId = costUseSetMap.get(scmInvCountingCostCenterEntry.getOrgUnitNo()+"_"+scmInvCountingCostCenterEntry.getGroupId());
				}
				boolean exists=false;
				ScmCostFinDeptConsume scmCostFinDeptConsume = new ScmCostFinDeptConsume(true);
				if(!scmCostFinDeptConsumeList.isEmpty()) {
					for (ScmCostFinDeptConsume existScmCostFinDeptConsume:scmCostFinDeptConsumeList) {
						if (existScmCostFinDeptConsume.getGroupId() == scmInvCountingCostCenterEntry.getGroupId() && StringUtils.equals(
								existScmCostFinDeptConsume.getCostOrgUnitNo(), scmInvCountingCostCenterEntry.getOrgUnitNo())
								&& existScmCostFinDeptConsume.getCostUseTypeId()==costUseTypeId) {
							existScmCostFinDeptConsume.setCostAmt(existScmCostFinDeptConsume.getCostAmt().add(scmInvCountingCostCenterEntry.getAmt()));
							existScmCostFinDeptConsume.setCostTax(existScmCostFinDeptConsume.getCostTax().add(scmInvCountingCostCenterEntry.getTaxAmt().subtract(scmInvCountingCostCenterEntry.getAmt())));
							existScmCostFinDeptConsume.setCostTaxAmt(existScmCostFinDeptConsume.getCostTaxAmt().add(scmInvCountingCostCenterEntry.getTaxAmt()));
							scmCostFinDeptConsume = existScmCostFinDeptConsume;
							exists = true;
						}
					}
				}
				if(!exists) {
					//放入明细记录结果集中
					scmCostFinDeptConsume.setFinOrgUnitNo(scmInvCountingCostCenterEntry.getFinOrgUnitNo());
					scmCostFinDeptConsume.setCostOrgUnitNo(scmInvCountingCostCenterEntry.getOrgUnitNo());
					scmCostFinDeptConsume.setCostOrgUnitName(orgCostCenter==null?"":orgCostCenter.getOrgUnitName());
					scmCostFinDeptConsume.setGroupId(scmInvCountingCostCenterEntry.getGroupId());
					scmCostFinDeptConsume.setLongNo(scmInvCountingCostCenterEntry.getLongNo());
					scmCostFinDeptConsume.setCostUseTypeId(costUseTypeId);
					scmCostFinDeptConsume.setCostAmt(scmInvCountingCostCenterEntry.getAmt());
					scmCostFinDeptConsume.setCostTax(scmInvCountingCostCenterEntry.getTaxAmt().subtract(scmInvCountingCostCenterEntry.getAmt()));
					scmCostFinDeptConsume.setCostTaxAmt(scmInvCountingCostCenterEntry.getTaxAmt());
					scmCostFinDeptConsumeList.add(scmCostFinDeptConsume);
				}
        	}
		}
        List<ScmCostFinDeptConsume> rtnList = new ArrayList();
        if(!scmCostFinDeptConsumeList.isEmpty()) {
        	for(ScmCostFinDeptConsume scmCostFinDeptConsume:scmCostFinDeptConsumeList) {
        		if(BigDecimal.ZERO.compareTo(scmCostFinDeptConsume.getCostAmt())==0 && scmDeptConsumeQuery.isFromApi())
        			continue;
        		long groupId = scmCostFinDeptConsume.getGroupId();
        		if(groupId>0) {
        			String longNoList[] = StringUtils.split(scmCostFinDeptConsume.getLongNo(),",");
        			if(summaryLevel>0) {
	        			if(longNoList.length>=summaryLevel) {
	        				groupId = Long.valueOf(longNoList[summaryLevel-1]);
	        			}else {
	        				groupId = Long.valueOf(longNoList[longNoList.length-2]);
	        			}
        			}
        			ScmMaterialGroup scmMaterialGroup = scmMaterialGroupBiz.selectDirect(groupId, param);
        			if(scmMaterialGroup!=null) {
        				scmCostFinDeptConsume.setGroupId(groupId);
        				scmCostFinDeptConsume.setClassCode(scmMaterialGroup.getClassCode());
        				scmCostFinDeptConsume.setClassName(scmMaterialGroup.getClassName());
        			}
        		}
        		if(scmCostFinDeptConsume.getCostUseTypeId()>0) {
        			ScmCostUseType scmCostUseType = scmCostUseTypeBiz.selectDirect(scmCostFinDeptConsume.getCostUseTypeId(), param);
        			if(scmCostUseType!=null) {
        				scmCostFinDeptConsume.setCostUseTypeCode(scmCostUseType.getCode());
        				scmCostFinDeptConsume.setCostUseTypeName(scmCostUseType.getName());
        			}
        		}
				scmCostFinDeptConsume.setStockAmt(scmCostFinDeptConsume.getInitAmt()
						.add(scmCostFinDeptConsume.getAddInAmt()).add(scmCostFinDeptConsume.getOutAmt())
						.add(scmCostFinDeptConsume.getMoveInAmt()).subtract(scmCostFinDeptConsume.getMoveOutAmt())
						.subtract(scmCostFinDeptConsume.getCostAmt()));
				scmCostFinDeptConsume.setStockTaxAmt(scmCostFinDeptConsume.getInitTaxAmt()
						.add(scmCostFinDeptConsume.getAddInTaxAmt()).add(scmCostFinDeptConsume.getOutTaxAmt())
						.add(scmCostFinDeptConsume.getMoveInTaxAmt()).subtract(scmCostFinDeptConsume.getMoveOutTaxAmt())
						.subtract(scmCostFinDeptConsume.getCostTaxAmt()));
				scmCostFinDeptConsume.setStockTax(
						scmCostFinDeptConsume.getStockTaxAmt().subtract(scmCostFinDeptConsume.getStockAmt()));
				//合并分类返回
				boolean exists=false;
				if(!rtnList.isEmpty()) {
					for (ScmCostFinDeptConsume existScmCostFinDeptConsume:rtnList) {
						if (existScmCostFinDeptConsume.getGroupId() == scmCostFinDeptConsume.getGroupId() && StringUtils.equals(
								existScmCostFinDeptConsume.getCostOrgUnitNo(), scmCostFinDeptConsume.getCostOrgUnitNo())
								&& existScmCostFinDeptConsume.getCostUseTypeId()==scmCostFinDeptConsume.getCostUseTypeId()) {
							existScmCostFinDeptConsume.setInitAmt(existScmCostFinDeptConsume.getInitAmt().add(scmCostFinDeptConsume.getInitAmt()));
							existScmCostFinDeptConsume.setInitTax(existScmCostFinDeptConsume.getInitTax().add(scmCostFinDeptConsume.getInitTax()));
							existScmCostFinDeptConsume.setInitTaxAmt(existScmCostFinDeptConsume.getInitTaxAmt().add(scmCostFinDeptConsume.getInitTaxAmt()));
							existScmCostFinDeptConsume.setAddInAmt(existScmCostFinDeptConsume.getAddInAmt().add(scmCostFinDeptConsume.getAddInAmt()));
							existScmCostFinDeptConsume.setAddInTax(existScmCostFinDeptConsume.getAddInTax().add(scmCostFinDeptConsume.getAddInTax()));
							existScmCostFinDeptConsume.setAddInTaxAmt(existScmCostFinDeptConsume.getAddInTaxAmt().add(scmCostFinDeptConsume.getAddInTaxAmt()));
							existScmCostFinDeptConsume.setOutAmt(existScmCostFinDeptConsume.getOutAmt().add(scmCostFinDeptConsume.getOutAmt()));
							existScmCostFinDeptConsume.setOutTax(existScmCostFinDeptConsume.getOutTax().add(scmCostFinDeptConsume.getOutTax()));
							existScmCostFinDeptConsume.setOutTaxAmt(existScmCostFinDeptConsume.getOutTaxAmt().add(scmCostFinDeptConsume.getOutTaxAmt()));
							existScmCostFinDeptConsume.setMoveInAmt(existScmCostFinDeptConsume.getMoveInAmt().add(scmCostFinDeptConsume.getMoveInAmt()));
							existScmCostFinDeptConsume.setMoveInTax(existScmCostFinDeptConsume.getMoveInTax().add(scmCostFinDeptConsume.getMoveInTax()));
							existScmCostFinDeptConsume.setMoveInTaxAmt(existScmCostFinDeptConsume.getMoveInTaxAmt().add(scmCostFinDeptConsume.getMoveInTaxAmt()));
							existScmCostFinDeptConsume.setMoveOutAmt(existScmCostFinDeptConsume.getMoveOutAmt().add(scmCostFinDeptConsume.getMoveOutAmt()));
							existScmCostFinDeptConsume.setMoveOutTax(existScmCostFinDeptConsume.getMoveOutTax().add(scmCostFinDeptConsume.getMoveOutTax()));
							existScmCostFinDeptConsume.setMoveOutTaxAmt(existScmCostFinDeptConsume.getMoveOutTaxAmt().add(scmCostFinDeptConsume.getMoveOutTaxAmt()));
							existScmCostFinDeptConsume.setStockAmt(existScmCostFinDeptConsume.getStockAmt().add(scmCostFinDeptConsume.getStockAmt()));
							existScmCostFinDeptConsume.setStockTax(existScmCostFinDeptConsume.getStockTax().add(scmCostFinDeptConsume.getStockTax()));
							existScmCostFinDeptConsume.setStockTaxAmt(existScmCostFinDeptConsume.getStockTaxAmt().add(scmCostFinDeptConsume.getStockTaxAmt()));
							existScmCostFinDeptConsume.setCostAmt(existScmCostFinDeptConsume.getCostAmt().add(scmCostFinDeptConsume.getCostAmt()));
							existScmCostFinDeptConsume.setCostTax(existScmCostFinDeptConsume.getCostTax().add(scmCostFinDeptConsume.getCostTax()));
							existScmCostFinDeptConsume.setCostTaxAmt(existScmCostFinDeptConsume.getCostTaxAmt().add(scmCostFinDeptConsume.getCostTaxAmt()));
							exists = true;
						}
					}
				}
				if(!exists) {
					//放入明细记录结果集中
					ScmCostFinDeptConsume rtnScmCostFinDeptConsume = new ScmCostFinDeptConsume(true);
					BeanUtil.copyProperties(rtnScmCostFinDeptConsume, scmCostFinDeptConsume);
					rtnList.add(rtnScmCostFinDeptConsume);
				}
				
        	}
        }
        return rtnList;
	}
	//物资进出存汇总表
	@Override
	public List<ScmCostItemInAndOutSum> selectFinSummaryOfMaterials(HttpServletRequest request) throws AppException {
		List<ScmCostItemInAndOutSum> scmCostItemInAndOutSumList = new ArrayList<ScmCostItemInAndOutSum>();
		String currentOrgUnitNo = request.getParameter("orgUnitNo");
		String currentControlUnitNo = request.getParameter("controlUnitNo");
		String finOrgUnitNo = request.getParameter("finOrgUnitNo");
		String beginDate = request.getParameter("beginDate");
		String endDate = request.getParameter("endDate");
		String materialClassName = request.getParameter("materialClassName");
		String materialName = request.getParameter("materialName");
		String status = "1".equals(request.getParameter("status")) ? "Y" : "N";
		if(StringUtils.isBlank(beginDate) || StringUtils.isBlank(endDate))
			return scmCostItemInAndOutSumList;
		if(StringUtils.isBlank(finOrgUnitNo))
			finOrgUnitNo = currentOrgUnitNo;
		
		Param param = new Param();
		param.setOrgUnitNo(currentOrgUnitNo);
		param.setControlUnitNo(currentControlUnitNo);
		StringBuffer costOrgUnitNos = new StringBuffer("");
		HashMap<String,String> costCenterTypeMap = new HashMap<>();
		List<OrgCompany2> orgCompanys = orgCompanyBiz.findChild(finOrgUnitNo, param);
		List<OrgCostCenter2> orgCostCenter2s = null;
//		查询委托该财务组织的成本中心
		for(OrgCompany2 orgCompany:orgCompanys) {
			orgCostCenter2s = orgUnitRelationBiz.findFromOrgUnitByType("costToFin", orgCompany.getOrgUnitNo(), true, null,param);
			if (orgCostCenter2s != null && !orgCostCenter2s.isEmpty()) {
				for (OrgCostCenter2 orgCostCenter : orgCostCenter2s) {
					if (StringUtils.isNotBlank(costOrgUnitNos.toString()))
						costOrgUnitNos.append(",");
					costOrgUnitNos.append("'").append(orgCostCenter.getOrgUnitNo()).append("'");
					OrgCostCenter2 orgCostCenterTemp = orgCostCenterBiz.selectByOrgUnitNo(orgCostCenter.getOrgUnitNo(),
							param);
					if (orgCostCenterTemp != null) {
						costCenterTypeMap.put(orgCostCenterTemp.getOrgUnitNo(), orgCostCenterTemp.getCostCenterType());
					}
				}
			}
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
		PeriodCalendar periodCalendar = periodCalendarBiz.selectByBizdate(FormatUtils.parseDate(beginDate), param);
		Page page = new Page();
		if (orgCostCenter2s != null && !orgCostCenter2s.isEmpty()) {
		    long beginTime = System.currentTimeMillis();
			// 获取期初库存
			if (periodCalendar != null) {
				page.setModelClass(ScmInvBal.class);
				page.setShowCount(Integer.MAX_VALUE);
				page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvBal.class) + "." + ScmInvBal.FN_COSTCENTER,
						new QueryParam(
								ClassUtils.getFinalModelSimpleName(ScmInvBal.class) + "." + ScmInvBal.FN_COSTCENTER,
								QueryParam.QUERY_EQ, "1"));
				page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvBal.class) + "." + ScmInvBal.FN_COSTORGUNITNO,
						new QueryParam(
								ClassUtils.getFinalModelSimpleName(ScmInvBal.class) + "." + ScmInvBal.FN_COSTORGUNITNO,
								QueryParam.QUERY_IN, costOrgUnitNos.toString()));
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
				List<String> arglist = new ArrayList<>();
				arglist.add("controlUnitNo=" + currentControlUnitNo);
				long dataEndTime11 = System.currentTimeMillis();
				log.info("期初数据查询准备耗时："+(dataEndTime11-beginTime)+"毫秒");
				List<ScmInvBal2> scmInvBalList = scmInvBalBiz.queryPage(page, arglist, "findItemPage", param);
				long dataEndTime = System.currentTimeMillis();
				log.info("期初数据查询耗时："+(dataEndTime-dataEndTime11)+"毫秒");
				if (scmInvBalList != null && !scmInvBalList.isEmpty()) {
					log.info("期初数据查询结果："+(scmInvBalList.size())+"条记录");
					for (ScmInvBal2 scmInvBal : scmInvBalList) {
						if (scmInvBal.isCostCenter()) {
							// 以领代耗的期初不处理，认为是零
							if (costCenterTypeMap.containsKey(scmInvBal.getCostOrgUnitNo())) {
								if (StringUtils.equals("1",costCenterTypeMap.get(scmInvBal.getCostOrgUnitNo()))) {
									continue;
								}
							}
						}
						boolean exists = false;
						if (!scmCostItemInAndOutSumList.isEmpty()) {
							for (ScmCostItemInAndOutSum existScmCostItemInAndOutSum : scmCostItemInAndOutSumList) {
								if (existScmCostItemInAndOutSum.getItemId() == scmInvBal.getItemId()){ 
//										&& StringUtils.equals(existScmCostItemInAndOutSum.getCostOrgUnitNo(), scmInvBal.getCostOrgUnitNo())) {
									existScmCostItemInAndOutSum.setInitQty(
											existScmCostItemInAndOutSum.getInitQty().add(scmInvBal.getStartQty()));
									existScmCostItemInAndOutSum.setInitAmt(
											existScmCostItemInAndOutSum.getInitAmt().add(scmInvBal.getStartAmt()));
									existScmCostItemInAndOutSum.setInitAvgPrice(
											BigDecimal.ZERO.compareTo(existScmCostItemInAndOutSum.getInitQty()) == 0
													? BigDecimal.ZERO
													: existScmCostItemInAndOutSum.getInitAmt().divide(
															existScmCostItemInAndOutSum.getInitQty(), 2,
															BigDecimal.ROUND_HALF_UP));
									existScmCostItemInAndOutSum.setInitTax(existScmCostItemInAndOutSum.getInitTax()
											.add(scmInvBal.getStartTaxAmt().subtract(scmInvBal.getStartAmt())));
									existScmCostItemInAndOutSum.setInitTaxAmt(
											existScmCostItemInAndOutSum.getInitTaxAmt().add(scmInvBal.getStartTaxAmt()));
									exists = true;
									break;
								}
							}
						}
						if (!exists) {
							ScmCostItemInAndOutSum scmCostItemInAndOutSum = new ScmCostItemInAndOutSum(true);
							scmCostItemInAndOutSum.setItemId(scmInvBal.getItemId());
							scmCostItemInAndOutSum.setItemNo(scmInvBal.getItemNo());
							scmCostItemInAndOutSum.setItemName(scmInvBal.getItemName());
							scmCostItemInAndOutSum.setSpec(scmInvBal.getSpec());
							scmCostItemInAndOutSum.setGroupId(scmInvBal.getGroupId());
							scmCostItemInAndOutSum.setClassCode(scmInvBal.getGroupCode());
							scmCostItemInAndOutSum.setClassName(scmInvBal.getGroupName());
							scmCostItemInAndOutSum.setLongNo(scmInvBal.getLongNo());
							scmCostItemInAndOutSum.setUnitName(scmInvBal.getUnitName());
							scmCostItemInAndOutSum.setUnit(scmInvBal.getUnit());
							scmCostItemInAndOutSum.setCostOrgUnitNo(scmInvBal.getCostOrgUnitNo());
							scmCostItemInAndOutSum.setInitQty(scmInvBal.getStartQty());
							scmCostItemInAndOutSum.setInitAmt(scmInvBal.getStartAmt());
							scmCostItemInAndOutSum.setInitAvgPrice(
									BigDecimal.ZERO.compareTo(scmCostItemInAndOutSum.getInitQty()) == 0 ? BigDecimal.ZERO
											: scmCostItemInAndOutSum.getInitAmt().divide(
													scmCostItemInAndOutSum.getInitQty(), 2, BigDecimal.ROUND_HALF_UP));
							scmCostItemInAndOutSum.setInitTax(scmInvBal.getStartTaxAmt().subtract(scmInvBal.getStartAmt()));
							scmCostItemInAndOutSum.setInitTaxAmt(scmInvBal.getStartTaxAmt());
							scmCostItemInAndOutSumList.add(scmCostItemInAndOutSum);
						}
					}
				}
				long dataEndTime1 = System.currentTimeMillis();
				log.info("期初数据加工耗时："+(dataEndTime1-dataEndTime)+"毫秒");
			}
			
			long beginTime1 = System.currentTimeMillis();
			// 获取部门-采购入库数据
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
						.put(ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBillEntry2.class) + "."
								+ ScmInvPurInWarehsBillEntry2.FN_ITEMID,
						new QueryParam(
								ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBillEntry2.class) + "."
										+ ScmInvPurInWarehsBillEntry2.FN_ITEMID,
								QueryParam.QUERY_IN, sbMaterila.toString()));
			}
			page.getParam()
					.put(ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBillEntry2.class) + "."
							+ ScmInvPurInWarehsBillEntry2.FN_COSTORGUNITNO,
					new QueryParam(
							ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBillEntry2.class) + "."
									+ ScmInvPurInWarehsBillEntry2.FN_COSTORGUNITNO,
							QueryParam.QUERY_IN, costOrgUnitNos.toString()));
//			page.getParamOr();
			page.getParam()
					.put(ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBill2.class) + "."
							+ ScmInvPurInWarehsBill2.FN_BIZDATE, new QueryParam(
									ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBill2.class) + "."
											+ ScmInvPurInWarehsBill2.FN_BIZDATE,
									QueryParam.QUERY_BETWEEN, FormatUtils.fmtDate(periodCalendar.getStartDate()), endDate));
			if (!StringUtils.equals("Y", status)) {
				page.getParam()
						.put(ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBill2.class) + "."
								+ ScmInvPurInWarehsBill2.FN_STATUS,
						new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBill2.class) + "."
								+ ScmInvPurInWarehsBill2.FN_STATUS, QueryParam.QUERY_EQ, "E"));
			}else {
				page.getParam()
					.put(ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBill2.class) + "."
							+ ScmInvPurInWarehsBill2.FN_STATUS,
					new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBill2.class) + "."
							+ ScmInvPurInWarehsBill2.FN_STATUS, QueryParam.QUERY_NOTIN, "'N','C'"));
			}
			List<String> arglist = new ArrayList<>();
			arglist.add("controlUnitNo=" + currentControlUnitNo);
			List<ScmInvPurInWarehsBillEntry2> scmInvPurInWarehsBillEntryList = scmInvPurInWarehsBillEntryBiz.queryPage(page,
					arglist, "findAllPage", param);
			
			long clearTime1 = System.currentTimeMillis();
			List<ScmInvPurInWarehsBillEntry2> clearCache = scmInvPurInWarehsBillEntryBiz.selectByWrId(1,param);
			long dataEndTime2 = System.currentTimeMillis();
			log.info("清缓存耗时："+(dataEndTime2-clearTime1)+"毫秒");
			
			log.info("采购入库明细查询耗时："+(dataEndTime2-beginTime1)+"毫秒");
			if (scmInvPurInWarehsBillEntryList != null && !scmInvPurInWarehsBillEntryList.isEmpty()) {
				log.info("采购入库明细查询结果："+(scmInvPurInWarehsBillEntryList.size())+"条记录");
				for (ScmInvPurInWarehsBillEntry2 scmInvPurInWarehsBillEntry : scmInvPurInWarehsBillEntryList) {
					BigDecimal sideFlag = BigDecimal.ONE;
					if (StringUtils.contains("6,8", scmInvPurInWarehsBillEntry.getBizType())) {
						sideFlag = new BigDecimal("-1");
					}
					if (scmInvPurInWarehsBillEntry.getBizDate().compareTo(FormatUtils.parseDate(beginDate)) < 0) {
						if (costCenterTypeMap.containsKey(scmInvPurInWarehsBillEntry.getCostOrgUnitNo())) {
							if (StringUtils.equals("1",costCenterTypeMap.get(scmInvPurInWarehsBillEntry.getCostOrgUnitNo()))) {
								continue;
							}
						}
						// 比查询日期小的统计到期初数据中，并从List中删除
						boolean exists = false;
						if (!scmCostItemInAndOutSumList.isEmpty()) {
							for (ScmCostItemInAndOutSum existScmCostItemInAndOutSum : scmCostItemInAndOutSumList) {
								if (existScmCostItemInAndOutSum.getItemId() == scmInvPurInWarehsBillEntry.getItemId()) {
//										&& StringUtils.equals(existScmCostItemInAndOutSum.getCostOrgUnitNo(),scmInvPurInWarehsBillEntry.getCostOrgUnitNo())) {
									existScmCostItemInAndOutSum.setInitQty(existScmCostItemInAndOutSum.getInitQty()
											.add(sideFlag.multiply(scmInvPurInWarehsBillEntry.getQty())));
									existScmCostItemInAndOutSum.setInitAmt(existScmCostItemInAndOutSum.getInitAmt()
											.add(sideFlag.multiply(scmInvPurInWarehsBillEntry.getAmt())));
									existScmCostItemInAndOutSum.setInitAvgPrice(
											BigDecimal.ZERO.compareTo(existScmCostItemInAndOutSum.getInitQty()) == 0
													? BigDecimal.ZERO
													: existScmCostItemInAndOutSum.getInitAmt().divide(
															existScmCostItemInAndOutSum.getInitQty(), 2,
															BigDecimal.ROUND_HALF_UP));
									existScmCostItemInAndOutSum.setInitTax(existScmCostItemInAndOutSum.getInitTax()
											.add(sideFlag.multiply(scmInvPurInWarehsBillEntry.getTaxAmt()
													.subtract(scmInvPurInWarehsBillEntry.getAmt()))));
									existScmCostItemInAndOutSum.setInitTaxAmt(existScmCostItemInAndOutSum.getInitTaxAmt()
											.add(sideFlag.multiply(scmInvPurInWarehsBillEntry.getTaxAmt())));
									exists = true;
									break;
								}
							}
						}
						if (!exists) {
							ScmCostItemInAndOutSum scmCostItemInAndOutSum = new ScmCostItemInAndOutSum(true);
							scmCostItemInAndOutSum.setItemId(scmInvPurInWarehsBillEntry.getItemId());
							scmCostItemInAndOutSum.setItemNo(scmInvPurInWarehsBillEntry.getItemNo());
							scmCostItemInAndOutSum.setItemName(scmInvPurInWarehsBillEntry.getItemName());
							scmCostItemInAndOutSum.setSpec(scmInvPurInWarehsBillEntry.getSpec());
							scmCostItemInAndOutSum.setGroupId(scmInvPurInWarehsBillEntry.getGroupId());
							scmCostItemInAndOutSum.setClassCode(scmInvPurInWarehsBillEntry.getGroupCode());
							scmCostItemInAndOutSum.setClassName(scmInvPurInWarehsBillEntry.getGroupName());
							scmCostItemInAndOutSum.setLongNo(scmInvPurInWarehsBillEntry.getLongNo());
							scmCostItemInAndOutSum.setUnitName(scmInvPurInWarehsBillEntry.getUnitName());
							scmCostItemInAndOutSum.setUnit(scmInvPurInWarehsBillEntry.getUnit());
							scmCostItemInAndOutSum.setCostOrgUnitNo(scmInvPurInWarehsBillEntry.getCostOrgUnitNo());
							scmCostItemInAndOutSum.setInitQty(sideFlag.multiply(scmInvPurInWarehsBillEntry.getQty()));
							scmCostItemInAndOutSum.setInitAmt(sideFlag.multiply(scmInvPurInWarehsBillEntry.getAmt()));
							scmCostItemInAndOutSum.setInitAvgPrice(
									BigDecimal.ZERO.compareTo(scmCostItemInAndOutSum.getInitQty()) == 0 ? BigDecimal.ZERO
											: scmCostItemInAndOutSum.getInitAmt().divide(
													scmCostItemInAndOutSum.getInitQty(), 2, BigDecimal.ROUND_HALF_UP));
							scmCostItemInAndOutSum.setInitTax(sideFlag.multiply(
									scmInvPurInWarehsBillEntry.getTaxAmt().subtract(scmInvPurInWarehsBillEntry.getAmt())));
							scmCostItemInAndOutSum.setInitTaxAmt(sideFlag.multiply(scmInvPurInWarehsBillEntry.getTaxAmt()));
							scmCostItemInAndOutSumList.add(scmCostItemInAndOutSum);
						}
					} else {
						boolean exists = false;
						if (!scmCostItemInAndOutSumList.isEmpty()) {
							for (ScmCostItemInAndOutSum existScmCostItemInAndOutSum : scmCostItemInAndOutSumList) {
								if (existScmCostItemInAndOutSum.getItemId() == scmInvPurInWarehsBillEntry.getItemId()
//										&& StringUtils.equals(existScmCostItemInAndOutSum.getCostOrgUnitNo(),scmInvPurInWarehsBillEntry.getCostOrgUnitNo())
											) {
									existScmCostItemInAndOutSum.setAddInQty(existScmCostItemInAndOutSum.getAddInQty()
											.add(sideFlag.multiply(scmInvPurInWarehsBillEntry.getQty())));
									existScmCostItemInAndOutSum.setAddInAmt(existScmCostItemInAndOutSum.getAddInAmt()
											.add(sideFlag.multiply(scmInvPurInWarehsBillEntry.getAmt())));
									existScmCostItemInAndOutSum.setAddInAvgPrice(
											BigDecimal.ZERO.compareTo(existScmCostItemInAndOutSum.getAddInQty()) == 0
													? BigDecimal.ZERO
													: existScmCostItemInAndOutSum.getAddInAmt().divide(
															existScmCostItemInAndOutSum.getAddInQty(), 2,
															BigDecimal.ROUND_HALF_UP));
									existScmCostItemInAndOutSum.setAddInTax(existScmCostItemInAndOutSum.getAddInTax()
											.add(sideFlag.multiply(scmInvPurInWarehsBillEntry.getTaxAmt()
													.subtract(scmInvPurInWarehsBillEntry.getAmt()))));
									existScmCostItemInAndOutSum.setAddInTaxAmt(existScmCostItemInAndOutSum.getAddInTaxAmt()
											.add(sideFlag.multiply(scmInvPurInWarehsBillEntry.getTaxAmt())));
									
									if (costCenterTypeMap.containsKey(scmInvPurInWarehsBillEntry.getCostOrgUnitNo())) {
										if (StringUtils.equals("1", costCenterTypeMap.get(scmInvPurInWarehsBillEntry.getCostOrgUnitNo()))) {
											// 以领代耗时也需写出库数
											existScmCostItemInAndOutSum.setOutQty(existScmCostItemInAndOutSum.getOutQty()
													.add(sideFlag.multiply(scmInvPurInWarehsBillEntry.getQty())));
											existScmCostItemInAndOutSum.setOutAmt(existScmCostItemInAndOutSum.getOutAmt()
													.add(sideFlag.multiply(scmInvPurInWarehsBillEntry.getAmt())));
											existScmCostItemInAndOutSum.setOutAvgPrice(
													BigDecimal.ZERO.compareTo(existScmCostItemInAndOutSum.getOutQty()) == 0
															? BigDecimal.ZERO
															: existScmCostItemInAndOutSum.getOutAmt().divide(
																	existScmCostItemInAndOutSum.getOutQty(), 2,
																	BigDecimal.ROUND_HALF_UP));
											existScmCostItemInAndOutSum.setOutTax(existScmCostItemInAndOutSum.getOutTax()
													.add(sideFlag.multiply(scmInvPurInWarehsBillEntry.getTaxAmt()
															.subtract(scmInvPurInWarehsBillEntry.getAmt()))));
											existScmCostItemInAndOutSum.setOutTaxAmt(existScmCostItemInAndOutSum.getOutTaxAmt()
													.add(sideFlag.multiply(scmInvPurInWarehsBillEntry.getTaxAmt())));
										}
									}
									exists = true;
									break;
								}
							}
						}
						if (!exists) {
							// 放入明细记录结果集中
							ScmCostItemInAndOutSum scmCostItemInAndOutSum = new ScmCostItemInAndOutSum(true);
							scmCostItemInAndOutSum.setItemId(scmInvPurInWarehsBillEntry.getItemId());
							scmCostItemInAndOutSum.setItemNo(scmInvPurInWarehsBillEntry.getItemNo());
							scmCostItemInAndOutSum.setItemName(scmInvPurInWarehsBillEntry.getItemName());
							scmCostItemInAndOutSum.setSpec(scmInvPurInWarehsBillEntry.getSpec());
							scmCostItemInAndOutSum.setGroupId(scmInvPurInWarehsBillEntry.getGroupId());
							scmCostItemInAndOutSum.setClassCode(scmInvPurInWarehsBillEntry.getGroupCode());
							scmCostItemInAndOutSum.setClassName(scmInvPurInWarehsBillEntry.getGroupName());
							scmCostItemInAndOutSum.setLongNo(scmInvPurInWarehsBillEntry.getLongNo());
							scmCostItemInAndOutSum.setUnitName(scmInvPurInWarehsBillEntry.getUnitName());
							scmCostItemInAndOutSum.setUnit(scmInvPurInWarehsBillEntry.getUnit());
							scmCostItemInAndOutSum.setCostOrgUnitNo(scmInvPurInWarehsBillEntry.getCostOrgUnitNo());
							// 计算收入数量 addinqty
							scmCostItemInAndOutSum.setAddInQty(sideFlag.multiply(scmInvPurInWarehsBillEntry.getQty()));
							scmCostItemInAndOutSum.setAddInAmt(sideFlag.multiply(scmInvPurInWarehsBillEntry.getAmt()));
							scmCostItemInAndOutSum.setAddInAvgPrice(
									BigDecimal.ZERO.compareTo(scmCostItemInAndOutSum.getAddInQty()) == 0 ? BigDecimal.ZERO
											: scmCostItemInAndOutSum.getAddInAmt().divide(
													scmCostItemInAndOutSum.getAddInQty(), 2, BigDecimal.ROUND_HALF_UP));
							scmCostItemInAndOutSum.setAddInTax(sideFlag.multiply(
									scmInvPurInWarehsBillEntry.getTaxAmt().subtract(scmInvPurInWarehsBillEntry.getAmt())));
							scmCostItemInAndOutSum
									.setAddInTaxAmt(sideFlag.multiply(scmInvPurInWarehsBillEntry.getTaxAmt()));
							if (costCenterTypeMap.containsKey(scmInvPurInWarehsBillEntry.getCostOrgUnitNo())) {
								if (StringUtils.equals("1", costCenterTypeMap.get(scmInvPurInWarehsBillEntry.getCostOrgUnitNo()))) {
									// 以领代耗时也需写出库数
									scmCostItemInAndOutSum.setOutQty(sideFlag.multiply(scmInvPurInWarehsBillEntry.getQty()));
									scmCostItemInAndOutSum.setOutAmt(sideFlag.multiply(scmInvPurInWarehsBillEntry.getAmt()));
									scmCostItemInAndOutSum.setOutAvgPrice(
											BigDecimal.ZERO.compareTo(scmCostItemInAndOutSum.getOutQty()) == 0 ? BigDecimal.ZERO
													: scmCostItemInAndOutSum.getOutAmt().divide(
															scmCostItemInAndOutSum.getOutQty(), 2, BigDecimal.ROUND_HALF_UP));
									scmCostItemInAndOutSum.setOutTax(sideFlag.multiply(scmInvPurInWarehsBillEntry.getTaxAmt()
											.subtract(scmInvPurInWarehsBillEntry.getAmt())));
									scmCostItemInAndOutSum
										.setOutTaxAmt(sideFlag.multiply(scmInvPurInWarehsBillEntry.getTaxAmt()));
								}
							}
							scmCostItemInAndOutSumList.add(scmCostItemInAndOutSum);
						}
					}
				}
				long dataEndTime3 = System.currentTimeMillis();
				log.info("采购入库明细加工耗时："+(dataEndTime3-dataEndTime2)+"毫秒");
			}
			
			long beginTime2 = System.currentTimeMillis();
			// 获取部门-领料出库数据
			page = new Page();
			page.setModelClass(ScmInvMaterialReqBillEntry2.class);
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
							+ ScmInvMaterialReqBill.FN_COSTORGUNITNO,
					new QueryParam(
							ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBill.class) + "."
									+ ScmInvMaterialReqBill.FN_COSTORGUNITNO,
							QueryParam.QUERY_IN, costOrgUnitNos.toString()));
			page.getParam()
					.put(ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBill2.class) + "."
							+ ScmInvMaterialReqBill2.FN_BIZDATE, new QueryParam(
									ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBill2.class) + "."
											+ ScmInvMaterialReqBill2.FN_BIZDATE,
									QueryParam.QUERY_BETWEEN, FormatUtils.fmtDate(periodCalendar.getStartDate()), endDate));
			if (!StringUtils.equals("Y", status)) {
				page.getParam()
						.put(ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBill2.class) + "."
								+ ScmInvMaterialReqBill2.FN_STATUS,
						new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBill2.class) + "."
								+ ScmInvMaterialReqBill2.FN_STATUS, QueryParam.QUERY_EQ, "E"));
			}else {
				page.getParam()
					.put(ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBill2.class) + "."
							+ ScmInvMaterialReqBill2.FN_STATUS,
					new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBill2.class) + "."
							+ ScmInvMaterialReqBill2.FN_STATUS, QueryParam.QUERY_NOTIN, "'N','C'"));
			}
			
			List<ScmInvMaterialReqBillEntry2> scmInvMaterialReqBillEntryList = scmInvMaterialReqBillEntryBiz.queryPage(page,
					arglist, "findAllPage", param);
			
			long clearTime2 = System.currentTimeMillis();
			List<ScmInvMaterialReqBillEntry2> clearCache1 = scmInvMaterialReqBillEntryBiz.selectByOtId(1,param);
			long dataEndTime3 = System.currentTimeMillis();
			log.info("清缓存耗时："+(dataEndTime3-clearTime2)+"毫秒");
			
			log.info("领料出库明细查询耗时："+(dataEndTime3-beginTime2)+"毫秒");
			if (scmInvMaterialReqBillEntryList != null && !scmInvMaterialReqBillEntryList.isEmpty()) {
				log.info("领料出库明细查询结果："+(scmInvMaterialReqBillEntryList.size())+"条记录");
				for (ScmInvMaterialReqBillEntry2 scmInvMaterialReqBillEntry : scmInvMaterialReqBillEntryList) {
					BigDecimal sideFlag = BigDecimal.ONE;
	//				2退仓
					if (StringUtils.contains("2", scmInvMaterialReqBillEntry.getBizType())) {
						sideFlag = new BigDecimal("-1");
					}
	//				日期在开始日期之前
					if (scmInvMaterialReqBillEntry.getBizDate().compareTo(FormatUtils.parseDate(beginDate)) < 0) {
	//					如果成本中心是以领代耗，则期初数据相当于没变
						if (costCenterTypeMap.containsKey(scmInvMaterialReqBillEntry.getCostOrgUnitNo())) {
							if (StringUtils.equals("1",costCenterTypeMap.get(scmInvMaterialReqBillEntry.getCostOrgUnitNo()))) {
								continue;
							}
						}
						// 比查询日期小的统计到期初数据中，并从List中删除
						boolean exists = false;
						if (!scmCostItemInAndOutSumList.isEmpty()) {
							for (ScmCostItemInAndOutSum existScmCostItemInAndOutSum : scmCostItemInAndOutSumList) {
								if (existScmCostItemInAndOutSum.getItemId() == scmInvMaterialReqBillEntry.getItemId()) {
//										&& StringUtils.equals(existScmCostItemInAndOutSum.getCostOrgUnitNo(),
//												scmInvMaterialReqBillEntry.getCostOrgUnitNo())) {
									existScmCostItemInAndOutSum.setInitQty(existScmCostItemInAndOutSum.getInitQty()
											.add(sideFlag.multiply(scmInvMaterialReqBillEntry.getQty())));
									existScmCostItemInAndOutSum.setInitAmt(existScmCostItemInAndOutSum.getInitAmt()
											.add(sideFlag.multiply(scmInvMaterialReqBillEntry.getAmt())));
									existScmCostItemInAndOutSum.setInitAvgPrice(
											BigDecimal.ZERO.compareTo(existScmCostItemInAndOutSum.getInitQty()) == 0
													? BigDecimal.ZERO
													: existScmCostItemInAndOutSum.getInitAmt().divide(
															existScmCostItemInAndOutSum.getInitQty(), 2,
															BigDecimal.ROUND_HALF_UP));
									existScmCostItemInAndOutSum.setInitTax(existScmCostItemInAndOutSum.getInitTax()
											.add(sideFlag.multiply(scmInvMaterialReqBillEntry.getTaxAmt()
													.subtract(scmInvMaterialReqBillEntry.getAmt()))));
									existScmCostItemInAndOutSum.setInitTaxAmt(existScmCostItemInAndOutSum.getInitTaxAmt()
											.add(sideFlag.multiply(scmInvMaterialReqBillEntry.getTaxAmt())));
									exists = true;
									break;
								}
							}
						}
						if (!exists) {
							ScmCostItemInAndOutSum scmCostItemInAndOutSum = new ScmCostItemInAndOutSum(true);
							scmCostItemInAndOutSum.setItemId(scmInvMaterialReqBillEntry.getItemId());
							scmCostItemInAndOutSum.setItemNo(scmInvMaterialReqBillEntry.getItemNo());
							scmCostItemInAndOutSum.setItemName(scmInvMaterialReqBillEntry.getItemName());
							scmCostItemInAndOutSum.setSpec(scmInvMaterialReqBillEntry.getSpec());
							scmCostItemInAndOutSum.setGroupId(scmInvMaterialReqBillEntry.getGroupId());
							scmCostItemInAndOutSum.setClassCode(scmInvMaterialReqBillEntry.getGroupCode());
							scmCostItemInAndOutSum.setClassName(scmInvMaterialReqBillEntry.getGroupName());
							scmCostItemInAndOutSum.setLongNo(scmInvMaterialReqBillEntry.getLongNo());
							scmCostItemInAndOutSum.setUnitName(scmInvMaterialReqBillEntry.getUnitName());
							scmCostItemInAndOutSum.setUnit(scmInvMaterialReqBillEntry.getUnit());
							scmCostItemInAndOutSum.setCostOrgUnitNo(scmInvMaterialReqBillEntry.getCostOrgUnitNo());
							scmCostItemInAndOutSum.setInitQty(sideFlag.multiply(scmInvMaterialReqBillEntry.getQty()));
							scmCostItemInAndOutSum.setInitAmt(sideFlag.multiply(scmInvMaterialReqBillEntry.getAmt()));
							scmCostItemInAndOutSum.setInitAvgPrice(
									BigDecimal.ZERO.compareTo(scmCostItemInAndOutSum.getInitQty()) == 0 ? BigDecimal.ZERO
											: scmCostItemInAndOutSum.getInitAmt().divide(
													scmCostItemInAndOutSum.getInitQty(), 2, BigDecimal.ROUND_HALF_UP));
							scmCostItemInAndOutSum.setInitTax(sideFlag.multiply(
									scmInvMaterialReqBillEntry.getTaxAmt().subtract(scmInvMaterialReqBillEntry.getAmt())));
							scmCostItemInAndOutSum.setInitTaxAmt(sideFlag.multiply(scmInvMaterialReqBillEntry.getTaxAmt()));
							scmCostItemInAndOutSumList.add(scmCostItemInAndOutSum);
						}
					} else {
						boolean exists = false;
						if (!scmCostItemInAndOutSumList.isEmpty()) {
							for (ScmCostItemInAndOutSum existScmCostItemInAndOutSum : scmCostItemInAndOutSumList) {
								if (existScmCostItemInAndOutSum.getItemId() == scmInvMaterialReqBillEntry.getItemId()) {
									existScmCostItemInAndOutSum.setAddInQty(existScmCostItemInAndOutSum.getAddInQty()
											.add(sideFlag.multiply(scmInvMaterialReqBillEntry.getQty())));
									existScmCostItemInAndOutSum.setAddInAmt(existScmCostItemInAndOutSum.getAddInAmt()
											.add(sideFlag.multiply(scmInvMaterialReqBillEntry.getAmt())));
									existScmCostItemInAndOutSum.setAddInAvgPrice(
											BigDecimal.ZERO.compareTo(existScmCostItemInAndOutSum.getAddInQty()) == 0
													? BigDecimal.ZERO
													: existScmCostItemInAndOutSum.getAddInAmt().divide(
															existScmCostItemInAndOutSum.getAddInQty(), 2,
															BigDecimal.ROUND_HALF_UP));
									existScmCostItemInAndOutSum.setAddInTax(existScmCostItemInAndOutSum.getAddInTax()
											.add(sideFlag.multiply(scmInvMaterialReqBillEntry.getTaxAmt()
													.subtract(scmInvMaterialReqBillEntry.getAmt()))));
									existScmCostItemInAndOutSum.setAddInTaxAmt(existScmCostItemInAndOutSum.getAddInTaxAmt()
											.add(sideFlag.multiply(scmInvMaterialReqBillEntry.getTaxAmt())));
									if (costCenterTypeMap.containsKey(scmInvMaterialReqBillEntry.getCostOrgUnitNo())) {
										if (StringUtils.equals("1", costCenterTypeMap.get(scmInvMaterialReqBillEntry.getCostOrgUnitNo()))) {
											// 以领代耗时也需写出库数
											existScmCostItemInAndOutSum.setOutQty(existScmCostItemInAndOutSum.getOutQty()
													.add(sideFlag.multiply(scmInvMaterialReqBillEntry.getQty())));
											existScmCostItemInAndOutSum.setOutAmt(existScmCostItemInAndOutSum.getOutAmt()
													.add(sideFlag.multiply(scmInvMaterialReqBillEntry.getAmt())));
											existScmCostItemInAndOutSum.setOutAvgPrice(
													BigDecimal.ZERO.compareTo(existScmCostItemInAndOutSum.getOutQty()) == 0
															? BigDecimal.ZERO
															: existScmCostItemInAndOutSum.getOutAmt().divide(
																	existScmCostItemInAndOutSum.getOutQty(), 2,
																	BigDecimal.ROUND_HALF_UP));
											existScmCostItemInAndOutSum.setOutTax(existScmCostItemInAndOutSum.getOutTax()
													.add(sideFlag.multiply(scmInvMaterialReqBillEntry.getTaxAmt()
															.subtract(scmInvMaterialReqBillEntry.getAmt()))));
											existScmCostItemInAndOutSum.setOutTaxAmt(existScmCostItemInAndOutSum.getOutTaxAmt()
													.add(sideFlag.multiply(scmInvMaterialReqBillEntry.getTaxAmt())));
										}
									}
									exists = true;
									break;
								}
							}
						}
						if (!exists) {
							// 放入明细记录结果集中
							ScmCostItemInAndOutSum scmCostItemInAndOutSum = new ScmCostItemInAndOutSum(true);
							scmCostItemInAndOutSum.setItemId(scmInvMaterialReqBillEntry.getItemId());
							scmCostItemInAndOutSum.setItemNo(scmInvMaterialReqBillEntry.getItemNo());
							scmCostItemInAndOutSum.setItemName(scmInvMaterialReqBillEntry.getItemName());
							scmCostItemInAndOutSum.setSpec(scmInvMaterialReqBillEntry.getSpec());
							scmCostItemInAndOutSum.setGroupId(scmInvMaterialReqBillEntry.getGroupId());
							scmCostItemInAndOutSum.setClassCode(scmInvMaterialReqBillEntry.getGroupCode());
							scmCostItemInAndOutSum.setClassName(scmInvMaterialReqBillEntry.getGroupName());
							scmCostItemInAndOutSum.setLongNo(scmInvMaterialReqBillEntry.getLongNo());
							scmCostItemInAndOutSum.setUnitName(scmInvMaterialReqBillEntry.getUnitName());
							scmCostItemInAndOutSum.setUnit(scmInvMaterialReqBillEntry.getUnit());
							scmCostItemInAndOutSum.setCostOrgUnitNo(scmInvMaterialReqBillEntry.getCostOrgUnitNo());
							scmCostItemInAndOutSum.setAddInQty(sideFlag.multiply(scmInvMaterialReqBillEntry.getQty()));
							scmCostItemInAndOutSum.setAddInAmt(sideFlag.multiply(scmInvMaterialReqBillEntry.getAmt()));
							scmCostItemInAndOutSum.setAddInAvgPrice(
									BigDecimal.ZERO.compareTo(scmCostItemInAndOutSum.getAddInQty()) == 0 ? BigDecimal.ZERO
											: scmCostItemInAndOutSum.getAddInAmt().divide(
													scmCostItemInAndOutSum.getAddInQty(), 2, BigDecimal.ROUND_HALF_UP));
							scmCostItemInAndOutSum.setAddInTax(sideFlag.multiply(
									scmInvMaterialReqBillEntry.getTaxAmt().subtract(scmInvMaterialReqBillEntry.getAmt())));
							scmCostItemInAndOutSum
									.setAddInTaxAmt(sideFlag.multiply(scmInvMaterialReqBillEntry.getTaxAmt()));
							if (costCenterTypeMap.containsKey(scmInvMaterialReqBillEntry.getCostOrgUnitNo())) {
								if (StringUtils.equals("1", costCenterTypeMap.get(scmInvMaterialReqBillEntry.getCostOrgUnitNo()))) {
									// 以领代耗时也需写出库数
									scmCostItemInAndOutSum.setOutQty(sideFlag.multiply(scmInvMaterialReqBillEntry.getQty()));
									scmCostItemInAndOutSum.setOutAmt(sideFlag.multiply(scmInvMaterialReqBillEntry.getAmt()));
									scmCostItemInAndOutSum.setOutAvgPrice(
											BigDecimal.ZERO.compareTo(scmCostItemInAndOutSum.getOutQty()) == 0 ? BigDecimal.ZERO
													: scmCostItemInAndOutSum.getOutAmt().divide(
															scmCostItemInAndOutSum.getOutQty(), 2, BigDecimal.ROUND_HALF_UP));
									scmCostItemInAndOutSum.setOutTax(sideFlag.multiply(scmInvMaterialReqBillEntry.getTaxAmt()
											.subtract(scmInvMaterialReqBillEntry.getAmt())));
									scmCostItemInAndOutSum
											.setOutTaxAmt(sideFlag.multiply(scmInvMaterialReqBillEntry.getTaxAmt()));
								}
							}
							scmCostItemInAndOutSumList.add(scmCostItemInAndOutSum);
						}
					}
				}
				long dataEndTime4 = System.currentTimeMillis();
				log.info("领料出库明细加工耗时："+(dataEndTime4-dataEndTime3)+"毫秒");
			}
			long beginTime3 = System.currentTimeMillis();
			// 获取部门-耗用数据
			page = new Page();
			page.setModelClass(ScmInvCostConsumeEntry2.class);
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
						.put(ClassUtils.getFinalModelSimpleName(ScmInvCostConsumeEntry2.class) + "."
								+ ScmInvCostConsumeEntry2.FN_ITEMID, new QueryParam(
										ClassUtils.getFinalModelSimpleName(ScmInvCostConsumeEntry2.class) + "."
												+ ScmInvCostConsumeEntry2.FN_ITEMID,
										QueryParam.QUERY_IN, sbMaterila.toString()));
			}
			page.getParam()
					.put(ClassUtils.getFinalModelSimpleName(ScmInvCostConsume.class) + "." + ScmInvCostConsume.FN_ORGUNITNO,
							new QueryParam(
									ClassUtils.getFinalModelSimpleName(ScmInvCostConsume.class) + "."
											+ ScmInvCostConsume.FN_ORGUNITNO,
									QueryParam.QUERY_IN, costOrgUnitNos.toString()));
			
			page.getParam().put(
					ClassUtils.getFinalModelSimpleName(ScmInvCostConsume.class) + "." + ScmInvCostConsume.FN_BIZDATE,
					new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvCostConsume.class) + "."
							+ ScmInvCostConsume.FN_BIZDATE, QueryParam.QUERY_BETWEEN, FormatUtils.fmtDate(periodCalendar.getStartDate()), endDate));
			if (!StringUtils.equals("Y", status)) {
				page.getParam()
						.put(ClassUtils.getFinalModelSimpleName(ScmInvCostConsume.class) + "."
								+ ScmInvCostConsume.FN_STATUS,
						new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvCostConsume.class) + "."
								+ ScmInvCostConsume.FN_STATUS, QueryParam.QUERY_EQ, "E"));
			}else {
				page.getParam()
					.put(ClassUtils.getFinalModelSimpleName(ScmInvCostConsume.class) + "."
							+ ScmInvCostConsume.FN_STATUS,
					new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvCostConsume.class) + "."
							+ ScmInvCostConsume.FN_STATUS, QueryParam.QUERY_NOTIN, "'N','C'"));
			}
			List<ScmInvCostConsumeEntry2> scmInvCostConsumeEntryList = scmInvCostConsumeEntryBiz.queryPage(page, arglist,
					"findAllPage", param);
			long dataEndTime5 = System.currentTimeMillis();
			log.info("成本中心耗用明细查询耗时："+(dataEndTime5-beginTime3)+"毫秒");
			if (scmInvCostConsumeEntryList != null && !scmInvCostConsumeEntryList.isEmpty()) {
				log.info("成本中心耗用明细查询结果："+(scmInvCostConsumeEntryList.size())+"条记录");
				for (ScmInvCostConsumeEntry2 scmInvCostConsumeEntry : scmInvCostConsumeEntryList) {
					if (costCenterTypeMap.containsKey(scmInvCostConsumeEntry.getCostOrgUnitNo())) {
						if (StringUtils.equals("1",costCenterTypeMap.get(scmInvCostConsumeEntry.getCostOrgUnitNo()))) {
							continue;
						}
					}
					if (scmInvCostConsumeEntry.getBizDate().compareTo(FormatUtils.parseDate(beginDate)) < 0) {
						// 比查询日期小的统计到期初数据中，并从List中删除
						boolean exists = false;
						if (!scmCostItemInAndOutSumList.isEmpty()) {
							for (ScmCostItemInAndOutSum existScmCostItemInAndOutSum : scmCostItemInAndOutSumList) {
								if (existScmCostItemInAndOutSum.getItemId() == scmInvCostConsumeEntry.getItemId()) {
//										&& StringUtils.equals(existScmCostItemInAndOutSum.getCostOrgUnitNo(),
//												scmInvCostConsumeEntry.getCostOrgUnitNo())) {
									existScmCostItemInAndOutSum.setInitQty(existScmCostItemInAndOutSum.getInitQty()
											.subtract(scmInvCostConsumeEntry.getQty()));
									existScmCostItemInAndOutSum.setInitAmt(existScmCostItemInAndOutSum.getInitAmt()
											.subtract(scmInvCostConsumeEntry.getAmt()));
									existScmCostItemInAndOutSum.setInitAvgPrice(
											BigDecimal.ZERO.compareTo(existScmCostItemInAndOutSum.getInitQty()) == 0
													? BigDecimal.ZERO
													: existScmCostItemInAndOutSum.getInitAmt().divide(
															existScmCostItemInAndOutSum.getInitQty(), 2,
															BigDecimal.ROUND_HALF_UP));
									existScmCostItemInAndOutSum.setInitTax(
											existScmCostItemInAndOutSum.getInitTax().subtract(scmInvCostConsumeEntry
													.getTaxAmt().subtract(scmInvCostConsumeEntry.getAmt())));
									existScmCostItemInAndOutSum.setInitTaxAmt(existScmCostItemInAndOutSum.getInitTaxAmt()
											.subtract(scmInvCostConsumeEntry.getTaxAmt()));
									exists = true;
									break;
								}
							}
						}
						if (!exists) {
							ScmCostItemInAndOutSum scmCostItemInAndOutSum = new ScmCostItemInAndOutSum(true);
							scmCostItemInAndOutSum.setItemId(scmInvCostConsumeEntry.getItemId());
							scmCostItemInAndOutSum.setItemNo(scmInvCostConsumeEntry.getItemNo());
							scmCostItemInAndOutSum.setItemName(scmInvCostConsumeEntry.getItemName());
							scmCostItemInAndOutSum.setSpec(scmInvCostConsumeEntry.getSpec());
							scmCostItemInAndOutSum.setGroupId(scmInvCostConsumeEntry.getGroupId());
							scmCostItemInAndOutSum.setClassCode(scmInvCostConsumeEntry.getGroupCode());
							scmCostItemInAndOutSum.setClassName(scmInvCostConsumeEntry.getGroupName());
							scmCostItemInAndOutSum.setLongNo(scmInvCostConsumeEntry.getLongNo());
							scmCostItemInAndOutSum.setUnitName(scmInvCostConsumeEntry.getUnitName());
							scmCostItemInAndOutSum.setUnit(scmInvCostConsumeEntry.getUnit());
							scmCostItemInAndOutSum.setCostOrgUnitNo(scmInvCostConsumeEntry.getCostOrgUnitNo());
							scmCostItemInAndOutSum.setInitQty(BigDecimal.ZERO.subtract(scmInvCostConsumeEntry.getQty()));
							scmCostItemInAndOutSum.setInitAmt(BigDecimal.ZERO.subtract(scmInvCostConsumeEntry.getAmt()));
							scmCostItemInAndOutSum.setInitAvgPrice(
									BigDecimal.ZERO.compareTo(scmCostItemInAndOutSum.getInitQty()) == 0 ? BigDecimal.ZERO
											: scmCostItemInAndOutSum.getInitAmt().divide(
													scmCostItemInAndOutSum.getInitQty(), 2, BigDecimal.ROUND_HALF_UP));
							scmCostItemInAndOutSum.setInitTax(BigDecimal.ZERO.subtract(
									scmInvCostConsumeEntry.getTaxAmt().subtract(scmInvCostConsumeEntry.getAmt())));
							scmCostItemInAndOutSum
									.setInitTaxAmt(BigDecimal.ZERO.subtract(scmInvCostConsumeEntry.getTaxAmt()));
							scmCostItemInAndOutSumList.add(scmCostItemInAndOutSum);
						}
					} else {
						boolean exists = false;
						if (!scmCostItemInAndOutSumList.isEmpty()) {
							for (ScmCostItemInAndOutSum existScmCostItemInAndOutSum : scmCostItemInAndOutSumList) {
								if (existScmCostItemInAndOutSum.getItemId() == scmInvCostConsumeEntry.getItemId()) {
//										&& StringUtils.equals(existScmCostItemInAndOutSum.getCostOrgUnitNo(),
//												scmInvCostConsumeEntry.getCostOrgUnitNo())) {
									existScmCostItemInAndOutSum.setOutQty(
											existScmCostItemInAndOutSum.getOutQty().add(scmInvCostConsumeEntry.getQty()));
									existScmCostItemInAndOutSum.setOutAmt(
											existScmCostItemInAndOutSum.getOutAmt().add(scmInvCostConsumeEntry.getAmt()));
									existScmCostItemInAndOutSum.setOutAvgPrice(
											BigDecimal.ZERO.compareTo(existScmCostItemInAndOutSum.getOutQty()) == 0
													? BigDecimal.ZERO
													: existScmCostItemInAndOutSum.getOutAmt().divide(
															existScmCostItemInAndOutSum.getOutQty(), 2,
															BigDecimal.ROUND_HALF_UP));
									existScmCostItemInAndOutSum.setOutTax(existScmCostItemInAndOutSum.getOutTax().add(
											scmInvCostConsumeEntry.getTaxAmt().subtract(scmInvCostConsumeEntry.getAmt())));
									existScmCostItemInAndOutSum.setOutTaxAmt(existScmCostItemInAndOutSum.getOutTaxAmt()
											.add(scmInvCostConsumeEntry.getTaxAmt()));
									exists = true;
									break;
								}
							}
						}
						if (!exists) {
							// 放入明细记录结果集中
							ScmCostItemInAndOutSum scmCostItemInAndOutSum = new ScmCostItemInAndOutSum(true);
							scmCostItemInAndOutSum.setItemId(scmInvCostConsumeEntry.getItemId());
							scmCostItemInAndOutSum.setItemNo(scmInvCostConsumeEntry.getItemNo());
							scmCostItemInAndOutSum.setItemName(scmInvCostConsumeEntry.getItemName());
							scmCostItemInAndOutSum.setSpec(scmInvCostConsumeEntry.getSpec());
							scmCostItemInAndOutSum.setGroupId(scmInvCostConsumeEntry.getGroupId());
							scmCostItemInAndOutSum.setClassCode(scmInvCostConsumeEntry.getGroupCode());
							scmCostItemInAndOutSum.setClassName(scmInvCostConsumeEntry.getGroupName());
							scmCostItemInAndOutSum.setLongNo(scmInvCostConsumeEntry.getLongNo());
							scmCostItemInAndOutSum.setUnitName(scmInvCostConsumeEntry.getUnitName());
							scmCostItemInAndOutSum.setUnit(scmInvCostConsumeEntry.getUnit());
							scmCostItemInAndOutSum.setCostOrgUnitNo(scmInvCostConsumeEntry.getCostOrgUnitNo());
							scmCostItemInAndOutSum.setOutQty(scmInvCostConsumeEntry.getQty());
							scmCostItemInAndOutSum.setOutAmt(scmInvCostConsumeEntry.getAmt());
							scmCostItemInAndOutSum.setOutAvgPrice(
									BigDecimal.ZERO.compareTo(scmCostItemInAndOutSum.getOutQty()) == 0 ? BigDecimal.ZERO
											: scmCostItemInAndOutSum.getOutAmt().divide(scmCostItemInAndOutSum.getOutQty(),
													2, BigDecimal.ROUND_HALF_UP));
							scmCostItemInAndOutSum.setOutTax(
									scmInvCostConsumeEntry.getTaxAmt().subtract(scmInvCostConsumeEntry.getAmt()));
							scmCostItemInAndOutSum.setOutTaxAmt(scmInvCostConsumeEntry.getTaxAmt());
							scmCostItemInAndOutSumList.add(scmCostItemInAndOutSum);
						}
					}
				}
				long dataEndTime6 = System.currentTimeMillis();
				log.info("成本中心耗用明细加工耗时："+(dataEndTime6-dataEndTime5)+"毫秒");
			}
			long beginTimeInventory = System.currentTimeMillis();
			// 部门-盘存差异
			page = new Page();
			page.setModelClass(ScmInvCountingCostCenterEntry2.class);
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
						.put(ClassUtils.getFinalModelSimpleName(ScmInvCountingCostCenterEntry2.class) + "."
								+ ScmInvCountingCostCenterEntry2.FN_ITEMID,
						new QueryParam(
								ClassUtils.getFinalModelSimpleName(ScmInvCountingCostCenterEntry2.class) + "."
										+ ScmInvCountingCostCenterEntry2.FN_ITEMID,
								QueryParam.QUERY_IN, sbMaterila.toString()));
			}
			page.getParam()
					.put(ClassUtils.getFinalModelSimpleName(ScmInvCountingCostCenter.class) + "."
							+ ScmInvCountingCostCenter.FN_ORGUNITNO,
					new QueryParam(
							ClassUtils.getFinalModelSimpleName(ScmInvCountingCostCenter.class) + "."
									+ ScmInvCountingCostCenter.FN_ORGUNITNO,
							QueryParam.QUERY_IN, costOrgUnitNos.toString()));
			page.getParam().put(
					ClassUtils.getFinalModelSimpleName(ScmInvCountingTask.class) + "." + ScmInvCountingTask.FN_BIZDATE,
					new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvCountingTask.class) + "."
							+ ScmInvCountingTask.FN_BIZDATE, QueryParam.QUERY_BETWEEN, FormatUtils.fmtDate(periodCalendar.getStartDate()), endDate));
			page.getParam()
					.put(ClassUtils.getFinalModelSimpleName(ScmInvCountingCostCenterEntry2.class) + "."
							+ ScmInvCountingCostCenterEntry2.FN_DIFFERQTY,
					new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvCountingCostCenterEntry2.class) + "."
							+ ScmInvCountingCostCenterEntry2.FN_DIFFERQTY, QueryParam.QUERY_NE, "0"));
			if (!StringUtils.equals("Y", status)) {
				page.getParam()
						.put(ClassUtils.getFinalModelSimpleName(ScmInvCountingTask.class) + "."
								+ ScmInvCountingTask.FN_STATUS,
						new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvCountingTask.class) + "."
								+ ScmInvCountingTask.FN_STATUS, QueryParam.QUERY_EQ, "O"));
			} else {
				page.getParam()
						.put(ClassUtils.getFinalModelSimpleName(ScmInvCountingTask.class) + "."
								+ ScmInvCountingTask.FN_STATUS,
						new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvCountingTask.class) + "."
								+ ScmInvCountingTask.FN_STATUS, QueryParam.QUERY_IN, "'T','B','O'"));
			}
			List<ScmInvCountingCostCenterEntry2> scmInvCountingCostCenterEntryList = scmInvCountingCostCenterEntryBiz
					.queryPage(page, arglist, "findAllPage", param);
			long dataEndTimeInventory = System.currentTimeMillis();
			log.info("成本中心盘存差异明细查询耗时："+(dataEndTimeInventory-beginTimeInventory)+"毫秒");
			if (scmInvCountingCostCenterEntryList != null && !scmInvCountingCostCenterEntryList.isEmpty()) {
				log.info("成本中心盘存差异明细查询结果："+(scmInvCountingCostCenterEntryList.size())+"条记录");
				for (ScmInvCountingCostCenterEntry2 scmInvCountingCostCenterEntry : scmInvCountingCostCenterEntryList) {
					if (costCenterTypeMap.containsKey(scmInvCountingCostCenterEntry.getOrgUnitNo())) {
						if (StringUtils.equals("1",costCenterTypeMap.get(scmInvCountingCostCenterEntry.getOrgUnitNo()))) {
							continue;
						}
					}
					if (scmInvCountingCostCenterEntry.getBizDate().compareTo(FormatUtils.parseDate(beginDate)) < 0) {
						// 比查询日期小的统计到期初数据中，并从List中删除
						boolean exists = false;
						if (!scmCostItemInAndOutSumList.isEmpty()) {
							for (ScmCostItemInAndOutSum existScmCostItemInAndOutSum : scmCostItemInAndOutSumList) {
								if (existScmCostItemInAndOutSum.getItemId() == scmInvCountingCostCenterEntry.getItemId()) {
//										&& StringUtils.equals(existScmCostItemInAndOutSum.getCostOrgUnitNo(),
//												scmInvCountingCostCenterEntry.getOrgUnitNo())) {
									existScmCostItemInAndOutSum.setInitQty(existScmCostItemInAndOutSum.getInitQty()
											.add(scmInvCountingCostCenterEntry.getDifferQty()));
									existScmCostItemInAndOutSum.setInitAmt(existScmCostItemInAndOutSum.getInitAmt()
											.add(scmInvCountingCostCenterEntry.getDifferAmt()));
									existScmCostItemInAndOutSum.setInitAvgPrice(
											BigDecimal.ZERO.compareTo(existScmCostItemInAndOutSum.getInitQty()) == 0
													? BigDecimal.ZERO
													: existScmCostItemInAndOutSum.getInitAmt().divide(
															existScmCostItemInAndOutSum.getInitQty(), 2,
															BigDecimal.ROUND_HALF_UP));
									existScmCostItemInAndOutSum.setInitTax(existScmCostItemInAndOutSum.getInitTax()
											.add(scmInvCountingCostCenterEntry.getDifferTaxAmt()
													.subtract(scmInvCountingCostCenterEntry.getDifferAmt())));
									existScmCostItemInAndOutSum.setInitTaxAmt(existScmCostItemInAndOutSum.getInitTaxAmt()
											.add(scmInvCountingCostCenterEntry.getDifferTaxAmt()));
									exists = true;
									break;
								}
							}
						}
						if (!exists) {
							ScmCostItemInAndOutSum scmCostItemInAndOutSum = new ScmCostItemInAndOutSum(true);
							scmCostItemInAndOutSum.setItemId(scmInvCountingCostCenterEntry.getItemId());
							scmCostItemInAndOutSum.setItemNo(scmInvCountingCostCenterEntry.getItemNo());
							scmCostItemInAndOutSum.setItemName(scmInvCountingCostCenterEntry.getItemName());
							scmCostItemInAndOutSum.setSpec(scmInvCountingCostCenterEntry.getSpec());
							scmCostItemInAndOutSum.setGroupId(scmInvCountingCostCenterEntry.getGroupId());
							scmCostItemInAndOutSum.setClassCode(scmInvCountingCostCenterEntry.getGroupCode());
							scmCostItemInAndOutSum.setClassName(scmInvCountingCostCenterEntry.getGroupName());
							scmCostItemInAndOutSum.setLongNo(scmInvCountingCostCenterEntry.getLongNo());
							scmCostItemInAndOutSum.setUnitName(scmInvCountingCostCenterEntry.getUnitName());
							scmCostItemInAndOutSum.setUnit(scmInvCountingCostCenterEntry.getUnit());
							scmCostItemInAndOutSum.setCostOrgUnitNo(scmInvCountingCostCenterEntry.getOrgUnitNo());
							scmCostItemInAndOutSum.setInitQty(scmInvCountingCostCenterEntry.getDifferQty());
							scmCostItemInAndOutSum.setInitAmt(scmInvCountingCostCenterEntry.getDifferAmt());
							scmCostItemInAndOutSum.setInitAvgPrice(
									BigDecimal.ZERO.compareTo(scmCostItemInAndOutSum.getInitQty()) == 0 ? BigDecimal.ZERO
											: scmCostItemInAndOutSum.getInitAmt().divide(
													scmCostItemInAndOutSum.getInitQty(), 2, BigDecimal.ROUND_HALF_UP));
							scmCostItemInAndOutSum.setInitTax(scmInvCountingCostCenterEntry.getDifferTaxAmt()
									.subtract(scmInvCountingCostCenterEntry.getDifferAmt()));
							scmCostItemInAndOutSum.setInitTaxAmt(scmInvCountingCostCenterEntry.getDifferTaxAmt());
							scmCostItemInAndOutSumList.add(scmCostItemInAndOutSum);
						}
					} else {
						boolean exists = false;
						if (!scmCostItemInAndOutSumList.isEmpty()) {
							for (ScmCostItemInAndOutSum existScmCostItemInAndOutSum : scmCostItemInAndOutSumList) {
								if (existScmCostItemInAndOutSum.getItemId() == scmInvCountingCostCenterEntry.getItemId()) {
//										&& StringUtils.equals(existScmCostItemInAndOutSum.getCostOrgUnitNo(),
//												scmInvCountingCostCenterEntry.getOrgUnitNo())) {
	//								差异数量是 负，则 outQty - differQty ,即发出数量加上 差异数量
									if (scmInvCountingCostCenterEntry.getDifferQty().compareTo(BigDecimal.ZERO) < 0) {
										existScmCostItemInAndOutSum.setOutQty(existScmCostItemInAndOutSum.getOutQty()
												.subtract(scmInvCountingCostCenterEntry.getDifferQty()));
										existScmCostItemInAndOutSum.setOutAmt(existScmCostItemInAndOutSum.getOutAmt()
												.subtract(scmInvCountingCostCenterEntry.getDifferAmt()));
										existScmCostItemInAndOutSum.setOutAvgPrice(
												BigDecimal.ZERO.compareTo(existScmCostItemInAndOutSum.getOutQty()) == 0
														? BigDecimal.ZERO
														: existScmCostItemInAndOutSum.getOutAmt().divide(
																existScmCostItemInAndOutSum.getOutQty(), 2,
																BigDecimal.ROUND_HALF_UP));
										existScmCostItemInAndOutSum.setOutTax(existScmCostItemInAndOutSum.getOutTax()
												.subtract(scmInvCountingCostCenterEntry.getDifferTaxAmt()
														.subtract(scmInvCountingCostCenterEntry.getDifferAmt())));
										existScmCostItemInAndOutSum.setOutTaxAmt(existScmCostItemInAndOutSum.getOutTaxAmt()
												.subtract(scmInvCountingCostCenterEntry.getDifferTaxAmt()));
									} else {
	//									差异数量是 正，则 addQty + differQty ,即收入数量加上 差异数量
										existScmCostItemInAndOutSum.setAddInQty(existScmCostItemInAndOutSum.getAddInQty()
												.add(scmInvCountingCostCenterEntry.getDifferQty()));
										existScmCostItemInAndOutSum.setAddInAmt(existScmCostItemInAndOutSum.getAddInAmt()
												.add(scmInvCountingCostCenterEntry.getDifferAmt()));
										existScmCostItemInAndOutSum.setAddInAvgPrice(
												BigDecimal.ZERO.compareTo(existScmCostItemInAndOutSum.getAddInQty()) == 0
														? BigDecimal.ZERO
														: existScmCostItemInAndOutSum.getAddInAmt().divide(
																existScmCostItemInAndOutSum.getAddInQty(), 2,
																BigDecimal.ROUND_HALF_UP));
										existScmCostItemInAndOutSum.setAddInTax(existScmCostItemInAndOutSum.getAddInTax()
												.add(scmInvCountingCostCenterEntry.getDifferTaxAmt()
														.subtract(scmInvCountingCostCenterEntry.getDifferAmt())));
										existScmCostItemInAndOutSum.setAddInTaxAmt(existScmCostItemInAndOutSum
												.getAddInTaxAmt().add(scmInvCountingCostCenterEntry.getDifferTaxAmt()));
									}
									exists = true;
									break;
								}
							}
						}
						if (!exists) {
							// 放入明细记录结果集中
							ScmCostItemInAndOutSum scmCostItemInAndOutSum = new ScmCostItemInAndOutSum(true);
							scmCostItemInAndOutSum.setItemId(scmInvCountingCostCenterEntry.getItemId());
							scmCostItemInAndOutSum.setItemNo(scmInvCountingCostCenterEntry.getItemNo());
							scmCostItemInAndOutSum.setItemName(scmInvCountingCostCenterEntry.getItemName());
							scmCostItemInAndOutSum.setSpec(scmInvCountingCostCenterEntry.getSpec());
							scmCostItemInAndOutSum.setGroupId(scmInvCountingCostCenterEntry.getGroupId());
							scmCostItemInAndOutSum.setClassCode(scmInvCountingCostCenterEntry.getGroupCode());
							scmCostItemInAndOutSum.setClassName(scmInvCountingCostCenterEntry.getGroupName());
							scmCostItemInAndOutSum.setLongNo(scmInvCountingCostCenterEntry.getLongNo());
							scmCostItemInAndOutSum.setUnitName(scmInvCountingCostCenterEntry.getUnitName());
							scmCostItemInAndOutSum.setUnit(scmInvCountingCostCenterEntry.getUnit());
							scmCostItemInAndOutSum.setCostOrgUnitNo(scmInvCountingCostCenterEntry.getOrgUnitNo());
							if (scmInvCountingCostCenterEntry.getDifferQty().compareTo(BigDecimal.ZERO) < 0) {
								scmCostItemInAndOutSum.setOutQty(BigDecimal.ZERO.subtract(scmInvCountingCostCenterEntry.getDifferQty()));
								scmCostItemInAndOutSum.setOutAmt(BigDecimal.ZERO.subtract(scmInvCountingCostCenterEntry.getDifferAmt()));
							
								scmCostItemInAndOutSum.setOutAvgPrice(
										BigDecimal.ZERO.compareTo(scmCostItemInAndOutSum.getOutQty()) == 0 ? BigDecimal.ZERO
												: scmCostItemInAndOutSum.getOutAmt().divide(
														scmCostItemInAndOutSum.getOutQty(), 2, BigDecimal.ROUND_HALF_UP));
								scmCostItemInAndOutSum.setOutTax(scmInvCountingCostCenterEntry.getDifferAmt()
										.subtract(scmInvCountingCostCenterEntry.getDifferTaxAmt()));
								scmCostItemInAndOutSum.setOutTaxAmt(BigDecimal.ZERO.subtract(scmInvCountingCostCenterEntry.getDifferTaxAmt()));
							} else {
								scmCostItemInAndOutSum.setAddInQty(scmInvCountingCostCenterEntry.getDifferQty());
								scmCostItemInAndOutSum.setAddInAmt(scmInvCountingCostCenterEntry.getDifferAmt());
								scmCostItemInAndOutSum
										.setAddInAvgPrice(
												BigDecimal.ZERO.compareTo(scmCostItemInAndOutSum.getAddInQty()) == 0
														? BigDecimal.ZERO
														: scmCostItemInAndOutSum.getAddInAmt().divide(
																scmCostItemInAndOutSum.getAddInQty(), 2,
																BigDecimal.ROUND_HALF_UP));
								scmCostItemInAndOutSum.setAddInTax(scmInvCountingCostCenterEntry.getDifferTaxAmt()
										.subtract(scmInvCountingCostCenterEntry.getDifferAmt()));
								scmCostItemInAndOutSum.setAddInTaxAmt(scmInvCountingCostCenterEntry.getDifferTaxAmt());
							}
							scmCostItemInAndOutSumList.add(scmCostItemInAndOutSum);
						}
					}
				}
				long dataEndTimeInventory1 = System.currentTimeMillis();
				log.info("成本中心盘存差异明细加工耗时："+(dataEndTimeInventory1-dataEndTimeInventory)+"毫秒");
			}
	//		获取部门-销售出库数据
			long beginTimeInvSale = System.currentTimeMillis();
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
								+ ScmInvSaleIssueBillEntry2.FN_ITEMID, new QueryParam(
										ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBillEntry2.class) + "."
												+ ScmInvSaleIssueBillEntry2.FN_ITEMID,
										QueryParam.QUERY_IN, sbMaterila.toString()));
			}
			page.getParamOr()
					.put(ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBillEntry.class) + "." + ScmInvSaleIssueBillEntry.FN_OUTCOSTORGUNITNO,
							new QueryParam(
									ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBillEntry.class) + "."
											+ ScmInvSaleIssueBillEntry.FN_OUTCOSTORGUNITNO,
									QueryParam.QUERY_IN, costOrgUnitNos.toString()));
			page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBill.class) + "." + ScmInvSaleIssueBill.FN_BIZDATE,
					new QueryParam(
							ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBill.class) + "." + ScmInvSaleIssueBill.FN_BIZDATE,
							QueryParam.QUERY_BETWEEN, FormatUtils.fmtDate(periodCalendar.getStartDate()), endDate));
			if (!StringUtils.equals("Y", status)) {
				page.getParam()
						.put(ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBill.class) + "."
								+ ScmInvSaleIssueBill.FN_STATUS,
						new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBill.class) + "."
								+ ScmInvSaleIssueBill.FN_STATUS, QueryParam.QUERY_EQ, "E"));
			}else {
				page.getParam()
					.put(ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBill.class) + "."
							+ ScmInvSaleIssueBill.FN_STATUS,
					new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBill.class) + "."
							+ ScmInvSaleIssueBill.FN_STATUS, QueryParam.QUERY_NOTIN, "'N','C'"));
			}
			List<ScmInvSaleIssueBillEntry2> scmInvSaleIssueBillEntry2s = scmInvSaleIssueBillEntryBiz.queryPage(page,
					arglist, "findAllPage", param);
			
			long clearTime3 = System.currentTimeMillis();
			List<ScmInvSaleIssueBillEntry2> clearCache2 = scmInvSaleIssueBillEntryBiz.selectByOtId(1,param);
			long endTimeInvSale = System.currentTimeMillis();
			log.info("清缓存耗时："+(endTimeInvSale-clearTime3)+"毫秒");
			

			log.info("销售出库明细查询耗时："+(endTimeInvSale-beginTimeInvSale)+"毫秒");
			if(scmInvSaleIssueBillEntry2s != null && !scmInvSaleIssueBillEntry2s.isEmpty()) {
				log.info("销售出库明细查询结果："+(scmInvSaleIssueBillEntry2s.size())+"条记录");
				for(ScmInvSaleIssueBillEntry2 scmInvSaleIssueBillEntry : scmInvSaleIssueBillEntry2s) {
					BigDecimal sideFlag = BigDecimal.ONE;
					if(StringUtils.contains("1,2,3",scmInvSaleIssueBillEntry.getBizType())) {
						sideFlag = new BigDecimal("-1");
					}
					if(scmInvSaleIssueBillEntry.getBizDate().compareTo(FormatUtils.parseDate(beginDate)) < 0) {
						if (costCenterTypeMap.containsKey(scmInvSaleIssueBillEntry.getOutCostOrgUnitNo())) {
							if (StringUtils.equals("1",costCenterTypeMap.get(scmInvSaleIssueBillEntry.getOutCostOrgUnitNo()))) {
								continue;
							}
						}
						// 比查询日期小的统计到期初数据中，并从List中删除
						boolean exists = false;
						if (!scmCostItemInAndOutSumList.isEmpty()) {
							for (ScmCostItemInAndOutSum existScmCostItemInAndOutSum : scmCostItemInAndOutSumList) {
								if (existScmCostItemInAndOutSum.getItemId() == scmInvSaleIssueBillEntry.getItemId()) {
//										&& StringUtils.equals(existScmCostItemInAndOutSum.getCostOrgUnitNo(),
//												scmInvSaleIssueBillEntry.getOutCostOrgUnitNo())) {
									// 期间 数据存在   出库：减期初数据 
									existScmCostItemInAndOutSum.setInitQty(existScmCostItemInAndOutSum.getInitQty()
											.add(sideFlag.multiply(scmInvSaleIssueBillEntry.getQty())));
									existScmCostItemInAndOutSum.setInitAmt(existScmCostItemInAndOutSum.getInitAmt()
											.add(sideFlag.multiply(scmInvSaleIssueBillEntry.getAmt())));
									existScmCostItemInAndOutSum.setInitAvgPrice(
											BigDecimal.ZERO.compareTo(existScmCostItemInAndOutSum.getInitQty()) == 0
													? BigDecimal.ZERO
													: existScmCostItemInAndOutSum.getInitAmt().divide(
															existScmCostItemInAndOutSum.getInitQty(), 2,
															BigDecimal.ROUND_HALF_UP));
									existScmCostItemInAndOutSum.setInitTax(existScmCostItemInAndOutSum.getInitTax()
											.add(sideFlag.multiply(scmInvSaleIssueBillEntry.getTaxAmt()
													.subtract(scmInvSaleIssueBillEntry.getAmt()))));
									existScmCostItemInAndOutSum.setInitTaxAmt(existScmCostItemInAndOutSum.getInitTaxAmt()
											.add(sideFlag.multiply(scmInvSaleIssueBillEntry.getTaxAmt())));
									exists = true;
									break;
								}
							}
						}
	//					期间数据不存在
						if (!exists) {
							ScmCostItemInAndOutSum scmCostItemInAndOutSum = new ScmCostItemInAndOutSum(true);
							scmCostItemInAndOutSum.setItemId(scmInvSaleIssueBillEntry.getItemId());
							scmCostItemInAndOutSum.setItemNo(scmInvSaleIssueBillEntry.getItemNo());
							scmCostItemInAndOutSum.setItemName(scmInvSaleIssueBillEntry.getItemName());
							scmCostItemInAndOutSum.setSpec(scmInvSaleIssueBillEntry.getSpec());
							scmCostItemInAndOutSum.setGroupId(scmInvSaleIssueBillEntry.getGroupId());
	//						scmCostItemInAndOutSum.setClassCode(scmInvSaleIssueBillEntry.getGroupCode());
							scmCostItemInAndOutSum.setClassName(scmInvSaleIssueBillEntry.getGroupName());
							scmCostItemInAndOutSum.setLongNo(scmInvSaleIssueBillEntry.getLongNo());
							scmCostItemInAndOutSum.setUnitName(scmInvSaleIssueBillEntry.getUnitName());
							scmCostItemInAndOutSum.setUnit(scmInvSaleIssueBillEntry.getUnit());
							scmCostItemInAndOutSum.setCostOrgUnitNo(scmInvSaleIssueBillEntry.getOutCostOrgUnitNo());
							scmCostItemInAndOutSum.setInitQty(sideFlag.multiply(scmInvSaleIssueBillEntry.getQty()));
							scmCostItemInAndOutSum.setInitAmt(sideFlag.multiply(scmInvSaleIssueBillEntry.getAmt()));
							scmCostItemInAndOutSum.setInitAvgPrice(
									BigDecimal.ZERO.compareTo(scmCostItemInAndOutSum.getInitQty()) == 0 ? BigDecimal.ZERO
											: scmCostItemInAndOutSum.getInitAmt().divide(
													scmCostItemInAndOutSum.getInitQty(), 2, BigDecimal.ROUND_HALF_UP));
							scmCostItemInAndOutSum.setInitTax(sideFlag.multiply(
									scmInvSaleIssueBillEntry.getTaxAmt().subtract(scmInvSaleIssueBillEntry.getAmt())));
							scmCostItemInAndOutSum.setInitTaxAmt(sideFlag.multiply(scmInvSaleIssueBillEntry.getTaxAmt()));
							scmCostItemInAndOutSumList.add(scmCostItemInAndOutSum);
						}
					}else {
						boolean exists = false;
						if (!scmCostItemInAndOutSumList.isEmpty()) {
							for (ScmCostItemInAndOutSum existScmCostItemInAndOutSum : scmCostItemInAndOutSumList) {
								if (existScmCostItemInAndOutSum.getItemId() == scmInvSaleIssueBillEntry.getItemId()) {
//										&& StringUtils.equals(existScmCostItemInAndOutSum.getCostOrgUnitNo(),
//												scmInvSaleIssueBillEntry.getOutCostOrgUnitNo())) {
									existScmCostItemInAndOutSum.setOutQty(existScmCostItemInAndOutSum.getOutQty().subtract(sideFlag.multiply(scmInvSaleIssueBillEntry.getQty())));
									existScmCostItemInAndOutSum.setOutAmt(existScmCostItemInAndOutSum.getOutAmt().subtract(sideFlag.multiply(scmInvSaleIssueBillEntry.getAmt())));
									existScmCostItemInAndOutSum.setOutAvgPrice(
											BigDecimal.ZERO.compareTo(existScmCostItemInAndOutSum.getOutQty()) == 0
													? BigDecimal.ZERO
													: existScmCostItemInAndOutSum.getOutAmt().divide(
															existScmCostItemInAndOutSum.getOutQty(), 2,
															BigDecimal.ROUND_HALF_UP));
									existScmCostItemInAndOutSum.setOutTax(existScmCostItemInAndOutSum.getOutTax().subtract(sideFlag.multiply(scmInvSaleIssueBillEntry.getTaxAmt().subtract(scmInvSaleIssueBillEntry.getAmt()))));
									existScmCostItemInAndOutSum.setOutTaxAmt(existScmCostItemInAndOutSum.getOutTaxAmt().subtract(sideFlag.multiply(scmInvSaleIssueBillEntry.getTaxAmt())));
									exists = true;
									break;
								}
							}
						}
						if (!exists) {
							// 放入明细记录结果集中
							ScmCostItemInAndOutSum scmCostItemInAndOutSum = new ScmCostItemInAndOutSum(true);
							scmCostItemInAndOutSum.setItemId(scmInvSaleIssueBillEntry.getItemId());
							scmCostItemInAndOutSum.setItemNo(scmInvSaleIssueBillEntry.getItemNo());
							scmCostItemInAndOutSum.setItemName(scmInvSaleIssueBillEntry.getItemName());
							scmCostItemInAndOutSum.setSpec(scmInvSaleIssueBillEntry.getSpec());
							scmCostItemInAndOutSum.setGroupId(scmInvSaleIssueBillEntry.getGroupId());
	//						scmCostItemInAndOutSum.setClassCode(scmInvSaleIssueBillEntry.getGroupCode());
							scmCostItemInAndOutSum.setClassName(scmInvSaleIssueBillEntry.getGroupName());
							scmCostItemInAndOutSum.setLongNo(scmInvSaleIssueBillEntry.getLongNo());
							scmCostItemInAndOutSum.setUnitName(scmInvSaleIssueBillEntry.getUnitName());
							scmCostItemInAndOutSum.setUnit(scmInvSaleIssueBillEntry.getUnit());
							scmCostItemInAndOutSum.setCostOrgUnitNo(scmInvSaleIssueBillEntry.getOutCostOrgUnitNo());
							scmCostItemInAndOutSum.setOutQty(BigDecimal.ZERO.subtract(sideFlag.multiply(scmInvSaleIssueBillEntry.getQty())));
							scmCostItemInAndOutSum.setOutAmt(BigDecimal.ZERO.subtract(sideFlag.multiply(scmInvSaleIssueBillEntry.getAmt())));
							scmCostItemInAndOutSum.setOutAvgPrice(
									BigDecimal.ZERO.compareTo(scmCostItemInAndOutSum.getOutQty()) == 0 ? BigDecimal.ZERO
											: scmCostItemInAndOutSum.getOutAmt().divide(
													scmCostItemInAndOutSum.getOutQty(), 2, BigDecimal.ROUND_HALF_UP));
							scmCostItemInAndOutSum.setOutTax(BigDecimal.ZERO.subtract(sideFlag.multiply(scmInvSaleIssueBillEntry.getTaxAmt()
									.subtract(scmInvSaleIssueBillEntry.getAmt()))));
							scmCostItemInAndOutSum
									.setOutTaxAmt(BigDecimal.ZERO.subtract(sideFlag.multiply(scmInvSaleIssueBillEntry.getTaxAmt())));
							scmCostItemInAndOutSumList.add(scmCostItemInAndOutSum);
						}
					}
				}
				long endTimeInvSale2 = System.currentTimeMillis();
				log.info("销售出库明细加工耗时："+(endTimeInvSale2-endTimeInvSale)+"毫秒");
			}
			
			 long beginTimeBreakage = System.currentTimeMillis();
			// 获取部门-报损单数据
			page = new Page();
			page.setModelClass(ScmCstFrmLossEntry2.class);
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
						.put(ClassUtils.getFinalModelSimpleName(ScmCstFrmLossEntry2.class) + "."
								+ ScmCstFrmLossEntry2.FN_ITEMID, new QueryParam(
										ClassUtils.getFinalModelSimpleName(ScmCstFrmLossEntry2.class) + "."
												+ ScmCstFrmLossEntry2.FN_ITEMID,
										QueryParam.QUERY_IN, sbMaterila.toString()));
			}
			page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmCstFrmLoss.class) + "." + ScmCstFrmLoss.FN_COSTORGUNITNO,
					new QueryParam(
							ClassUtils.getFinalModelSimpleName(ScmCstFrmLoss.class) + "." + ScmCstFrmLoss.FN_COSTORGUNITNO,
							QueryParam.QUERY_IN, costOrgUnitNos.toString()));
			page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmCstFrmLoss.class) + "." + ScmCstFrmLoss.FN_BIZDATE,
					new QueryParam(ClassUtils.getFinalModelSimpleName(ScmCstFrmLoss.class) + "." + ScmCstFrmLoss.FN_BIZDATE,
							QueryParam.QUERY_BETWEEN, FormatUtils.fmtDate(periodCalendar.getStartDate()), endDate));
			if (!StringUtils.equals("Y", status)) {
				page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmCstFrmLoss.class) + "." + ScmCstFrmLoss.FN_STATUS,
						new QueryParam(
								ClassUtils.getFinalModelSimpleName(ScmCstFrmLoss.class) + "." + ScmCstFrmLoss.FN_STATUS,
								QueryParam.QUERY_EQ, "E"));
			}else {
				page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmCstFrmLoss.class) + "." + ScmCstFrmLoss.FN_STATUS,
						new QueryParam(
								ClassUtils.getFinalModelSimpleName(ScmCstFrmLoss.class) + "." + ScmCstFrmLoss.FN_STATUS,
								QueryParam.QUERY_NOTIN, "'N','C'"));
			}
			List<ScmCstFrmLossEntry2> scmCstFrmLossEntryList = scmCstFrmLossEntryBiz.queryPage(page, arglist, "findAllPage",
					param);
			long dataEndTimeBreakage = System.currentTimeMillis();
			log.info("成本中心报损明细查询耗时："+(dataEndTimeBreakage-beginTimeBreakage)+"毫秒");
			if (scmCstFrmLossEntryList != null && !scmCstFrmLossEntryList.isEmpty()) {
				log.info("成本中心报损明细查询结果："+(scmCstFrmLossEntryList.size())+"条记录");
				for (ScmCstFrmLossEntry2 scmCstFrmLossEntry : scmCstFrmLossEntryList) {
					if (costCenterTypeMap.containsKey(scmCstFrmLossEntry.getCostOrgUnitNo())) {
						if (StringUtils.equals("1",costCenterTypeMap.get(scmCstFrmLossEntry.getCostOrgUnitNo()))) {
							continue;
						}
					}
					if (scmCstFrmLossEntry.getBizDate().compareTo(FormatUtils.parseDate(beginDate)) < 0) {
						// 比查询日期小的统计到期初数据中，并从List中删除
						boolean exists = false;
						if (!scmCostItemInAndOutSumList.isEmpty()) {
							for (ScmCostItemInAndOutSum existScmCostItemInAndOutSum : scmCostItemInAndOutSumList) {
								if (existScmCostItemInAndOutSum.getItemId() == scmCstFrmLossEntry.getItemId()) {
//										&& StringUtils.equals(existScmCostItemInAndOutSum.getCostOrgUnitNo(),
//												scmCstFrmLossEntry.getCostOrgUnitNo())) {
									existScmCostItemInAndOutSum.setInitQty(
											existScmCostItemInAndOutSum.getInitQty().subtract(scmCstFrmLossEntry.getQty()));
									existScmCostItemInAndOutSum.setInitAmt(
											existScmCostItemInAndOutSum.getInitAmt().subtract(scmCstFrmLossEntry.getAmt()));
									existScmCostItemInAndOutSum.setInitAvgPrice(
											BigDecimal.ZERO.compareTo(existScmCostItemInAndOutSum.getInitQty()) == 0
													? BigDecimal.ZERO
													: existScmCostItemInAndOutSum.getInitAmt().divide(
															existScmCostItemInAndOutSum.getInitQty(), 2,
															BigDecimal.ROUND_HALF_UP));
									existScmCostItemInAndOutSum
											.setInitTax(existScmCostItemInAndOutSum.getInitTax().subtract(
													scmCstFrmLossEntry.getTaxAmt().subtract(scmCstFrmLossEntry.getAmt())));
									existScmCostItemInAndOutSum.setInitTaxAmt(existScmCostItemInAndOutSum.getInitTaxAmt()
											.subtract(scmCstFrmLossEntry.getTaxAmt()));
									exists = true;
									break;
								}
							}
						}
						if (!exists) {
							ScmCostItemInAndOutSum scmCostItemInAndOutSum = new ScmCostItemInAndOutSum(true);
							scmCostItemInAndOutSum.setItemId(scmCstFrmLossEntry.getItemId());
							scmCostItemInAndOutSum.setItemNo(scmCstFrmLossEntry.getItemNo());
							scmCostItemInAndOutSum.setItemName(scmCstFrmLossEntry.getItemName());
							scmCostItemInAndOutSum.setSpec(scmCstFrmLossEntry.getSpec());
							scmCostItemInAndOutSum.setGroupId(scmCstFrmLossEntry.getGroupId());
							scmCostItemInAndOutSum.setClassCode(scmCstFrmLossEntry.getGroupCode());
							scmCostItemInAndOutSum.setClassName(scmCstFrmLossEntry.getGroupName());
							scmCostItemInAndOutSum.setLongNo(scmCstFrmLossEntry.getLongNo());
							scmCostItemInAndOutSum.setUnitName(scmCstFrmLossEntry.getUnitName());
							scmCostItemInAndOutSum.setUnit(scmCstFrmLossEntry.getUnit());
							scmCostItemInAndOutSum.setCostOrgUnitNo(scmCstFrmLossEntry.getCostOrgUnitNo());
							scmCostItemInAndOutSum.setInitQty(BigDecimal.ZERO.subtract(scmCstFrmLossEntry.getQty()));
							scmCostItemInAndOutSum.setInitAmt(BigDecimal.ZERO.subtract(scmCstFrmLossEntry.getAmt()));
							scmCostItemInAndOutSum.setInitAvgPrice(
									BigDecimal.ZERO.compareTo(scmCostItemInAndOutSum.getInitQty()) == 0 ? BigDecimal.ZERO
											: scmCostItemInAndOutSum.getInitAmt().divide(
													scmCostItemInAndOutSum.getInitQty(), 2, BigDecimal.ROUND_HALF_UP));
							scmCostItemInAndOutSum.setInitTax(BigDecimal.ZERO
									.subtract(scmCstFrmLossEntry.getTaxAmt().subtract(scmCstFrmLossEntry.getAmt())));
							scmCostItemInAndOutSum.setInitTaxAmt(BigDecimal.ZERO.subtract(scmCstFrmLossEntry.getTaxAmt()));
							scmCostItemInAndOutSumList.add(scmCostItemInAndOutSum);
						}
					} else {
						boolean exists = false;
						if (!scmCostItemInAndOutSumList.isEmpty()) {
							for (ScmCostItemInAndOutSum existScmCostItemInAndOutSum : scmCostItemInAndOutSumList) {
								if (existScmCostItemInAndOutSum.getItemId() == scmCstFrmLossEntry.getItemId()) {
//										&& StringUtils.equals(existScmCostItemInAndOutSum.getCostOrgUnitNo(),
//												scmCstFrmLossEntry.getCostOrgUnitNo())) {
									existScmCostItemInAndOutSum.setOutQty(
											existScmCostItemInAndOutSum.getOutQty().add(scmCstFrmLossEntry.getQty()));
									existScmCostItemInAndOutSum.setOutAmt(
											existScmCostItemInAndOutSum.getOutAmt().add(scmCstFrmLossEntry.getAmt()));
									existScmCostItemInAndOutSum.setOutAvgPrice(
											BigDecimal.ZERO.compareTo(existScmCostItemInAndOutSum.getOutQty()) == 0
													? BigDecimal.ZERO
													: existScmCostItemInAndOutSum.getOutAmt().divide(
															existScmCostItemInAndOutSum.getOutQty(), 2,
															BigDecimal.ROUND_HALF_UP));
									existScmCostItemInAndOutSum.setOutTax(existScmCostItemInAndOutSum.getOutTax()
											.add(scmCstFrmLossEntry.getTaxAmt().subtract(scmCstFrmLossEntry.getAmt())));
									existScmCostItemInAndOutSum.setOutTaxAmt(
											existScmCostItemInAndOutSum.getOutTaxAmt().add(scmCstFrmLossEntry.getTaxAmt()));
									exists = true;
									break;
								}
							}
						}
						if (!exists) {
							// 放入明细记录结果集中
							ScmCostItemInAndOutSum scmCostItemInAndOutSum = new ScmCostItemInAndOutSum(true);
							scmCostItemInAndOutSum.setItemId(scmCstFrmLossEntry.getItemId());
							scmCostItemInAndOutSum.setItemNo(scmCstFrmLossEntry.getItemNo());
							scmCostItemInAndOutSum.setItemName(scmCstFrmLossEntry.getItemName());
							scmCostItemInAndOutSum.setSpec(scmCstFrmLossEntry.getSpec());
							scmCostItemInAndOutSum.setGroupId(scmCstFrmLossEntry.getGroupId());
							scmCostItemInAndOutSum.setClassCode(scmCstFrmLossEntry.getGroupCode());
							scmCostItemInAndOutSum.setClassName(scmCstFrmLossEntry.getGroupName());
							scmCostItemInAndOutSum.setLongNo(scmCstFrmLossEntry.getLongNo());
							scmCostItemInAndOutSum.setUnitName(scmCstFrmLossEntry.getUnitName());
							scmCostItemInAndOutSum.setUnit(scmCstFrmLossEntry.getUnit());
							scmCostItemInAndOutSum.setCostOrgUnitNo(scmCstFrmLossEntry.getCostOrgUnitNo());
							scmCostItemInAndOutSum.setOutQty(scmCstFrmLossEntry.getQty());
							scmCostItemInAndOutSum.setOutAmt(scmCstFrmLossEntry.getAmt());					
							scmCostItemInAndOutSum.setOutAvgPrice(
									BigDecimal.ZERO.compareTo(scmCostItemInAndOutSum.getOutQty()) == 0 ? BigDecimal.ZERO
											: scmCostItemInAndOutSum.getOutAmt().divide(scmCostItemInAndOutSum.getOutQty(),
													2, BigDecimal.ROUND_HALF_UP));
							scmCostItemInAndOutSum
									.setOutTax(scmCstFrmLossEntry.getTaxAmt().subtract(scmCstFrmLossEntry.getAmt()));
							scmCostItemInAndOutSum.setOutTaxAmt(scmCstFrmLossEntry.getTaxAmt());
							scmCostItemInAndOutSumList.add(scmCostItemInAndOutSum);
						}
					}
				}
				long dataEndTimeBreakage1 = System.currentTimeMillis();
				log.info("成本中心报损明细加工耗时："+(dataEndTimeBreakage1-dataEndTimeBreakage)+"毫秒");
			}
		}
		//库存类数据
		
		
		StringBuffer orgUnitNos = new StringBuffer("");
		List<OrgStorage2> orgStorageList = null;
		for(OrgCompany2 orgCompany:orgCompanys) {
			orgStorageList = orgUnitRelationBiz.findFromOrgUnitByType("invToFin", orgCompany.getOrgUnitNo(), true, null,param);
			if (orgStorageList != null && !orgStorageList.isEmpty()) {
				for (OrgStorage2 orgStorage : orgStorageList) {
					if (StringUtils.isNotBlank(orgUnitNos.toString()))
						orgUnitNos.append(",");
					orgUnitNos.append("'").append(orgStorage.getOrgUnitNo()).append("'");
				}
			}
		}
		if (orgStorageList != null && !orgStorageList.isEmpty()) {
			//s期初库存
			if (periodCalendar != null) {
				page= new Page();
				page.setModelClass(ScmInvBal.class);
				page.setShowCount(Integer.MAX_VALUE);
				page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvBal.class) + "." + ScmInvBal.FN_ORGUNITNO,
						new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvBal.class) + "." + ScmInvBal.FN_ORGUNITNO,
								QueryParam.QUERY_IN, orgUnitNos.toString()));
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
				List<String> arglist1 = new ArrayList<>();
				arglist1.add("controlUnitNo=" + currentControlUnitNo);
				List<ScmInvBal2> scmInvBalList = scmInvBalBiz.queryPage(page, arglist1, "findItemPage", param);
				if (scmInvBalList != null && !scmInvBalList.isEmpty()) {
					for (ScmInvBal2 scmInvBal : scmInvBalList) {
						boolean exists = false;
						if (!scmCostItemInAndOutSumList.isEmpty()) {
							for (ScmCostItemInAndOutSum existScmCostItemInAndOutSum : scmCostItemInAndOutSumList) {
								if (existScmCostItemInAndOutSum.getItemId() == scmInvBal.getItemId() ) {
//										&& StringUtils.equals(existScmCostItemInAndOutSum.getOrgUnitNo(), scmInvBal.getOrgUnitNo())) {
									existScmCostItemInAndOutSum.setInitQty(
											existScmCostItemInAndOutSum.getInitQty().add(scmInvBal.getStartQty()));
									existScmCostItemInAndOutSum.setInitAmt(
											existScmCostItemInAndOutSum.getInitAmt().add(scmInvBal.getStartAmt()));
									existScmCostItemInAndOutSum.setInitTax(existScmCostItemInAndOutSum.getInitTax()
											.add(scmInvBal.getStartTaxAmt().subtract(scmInvBal.getStartAmt())));
									existScmCostItemInAndOutSum.setInitTaxAmt(existScmCostItemInAndOutSum
											.getInitTaxAmt().add(scmInvBal.getStartTaxAmt()));
									exists = true;
									break;
								}
							}
						}
						if (!exists) {
							ScmCostItemInAndOutSum scmInvItemInAndOutSum = new ScmCostItemInAndOutSum(true);
							scmInvItemInAndOutSum.setItemId(scmInvBal.getItemId());
							scmInvItemInAndOutSum.setUnitName(scmInvBal.getUnitName());
							scmInvItemInAndOutSum.setItemNo(scmInvBal.getItemNo());
							scmInvItemInAndOutSum.setItemName(scmInvBal.getItemName());
							scmInvItemInAndOutSum.setSpec(scmInvBal.getSpec());
							scmInvItemInAndOutSum.setClassName(scmInvBal.getGroupName());
							scmInvItemInAndOutSum.setUnit(scmInvBal.getUnit());
							scmInvItemInAndOutSum.setOrgUnitNo(scmInvBal.getOrgUnitNo());
							scmInvItemInAndOutSum.setInitQty(scmInvBal.getStartQty());
							scmInvItemInAndOutSum.setInitAmt(scmInvBal.getStartAmt());
							scmInvItemInAndOutSum.setInitTax(scmInvBal.getStartTaxAmt().subtract(scmInvBal.getStartAmt()));
							scmInvItemInAndOutSum.setInitTaxAmt(scmInvBal.getStartTaxAmt());
							scmCostItemInAndOutSumList.add(scmInvItemInAndOutSum);
						}
					}
				}
			}
			// 获取仓库-采购入库
			long beginTime1 = System.currentTimeMillis();
			page= new Page();
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
			page.getParam()
					.put(ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBillEntry2.class) + "."
							+ ScmInvPurInWarehsBillEntry2.FN_WAREHOUSEID,
							new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBillEntry2.class) + "."
									+ ScmInvPurInWarehsBillEntry2.FN_WAREHOUSEID, QueryParam.QUERY_GT, "0"));
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
			}else {
				page.getParam()
					.put(ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBill2.class) + "."
							+ ScmInvPurInWarehsBill2.FN_STATUS,
							new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBill2.class) + "."
									+ ScmInvPurInWarehsBill2.FN_STATUS, QueryParam.QUERY_NOTIN, "'N','C'"));
			}
			List<String> arglist2 = new ArrayList<>();
			arglist2.add("controlUnitNo=" + currentControlUnitNo);
			List<ScmInvPurInWarehsBillEntry2> scmInvPurInWarehsBillEntryList2 = scmInvPurInWarehsBillEntryBiz.queryPage(page,
					arglist2,"findAllPage", param);
			long dataEndTime2 = System.currentTimeMillis();			
			log.info("采购入库明细查询耗时："+(dataEndTime2-beginTime1)+"毫秒");
			if (scmInvPurInWarehsBillEntryList2 != null && !scmInvPurInWarehsBillEntryList2.isEmpty()) {
				for (ScmInvPurInWarehsBillEntry2 scmInvPurInWarehsBillEntry : scmInvPurInWarehsBillEntryList2) {
					BigDecimal sideFlag = BigDecimal.ONE;
					if (StringUtils.contains("6,8", scmInvPurInWarehsBillEntry.getBizType())) {
						sideFlag = new BigDecimal("-1");
					}
					if (scmInvPurInWarehsBillEntry.getBizDate().compareTo(FormatUtils.parseDate(beginDate)) < 0) {
						// 比查询日期小的统计到期初数据中，并从List中删除
						boolean exists = false;
						if (!scmCostItemInAndOutSumList.isEmpty()) {
							for (ScmCostItemInAndOutSum existScmInvItemInAndOutSum : scmCostItemInAndOutSumList) {
								if (existScmInvItemInAndOutSum.getItemId() == scmInvPurInWarehsBillEntry.getItemId()) {
//										&& StringUtils.equals(existScmInvItemInAndOutSum.getOrgUnitNo(),
//												scmInvPurInWarehsBillEntry.getOrgUnitNo())) {
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
						if (!exists) {
							ScmCostItemInAndOutSum scmInvItemInAndOutSum = new ScmCostItemInAndOutSum(true);
							scmInvItemInAndOutSum.setItemId(scmInvPurInWarehsBillEntry.getItemId());
							scmInvItemInAndOutSum.setUnitName(scmInvPurInWarehsBillEntry.getUnitName());
							scmInvItemInAndOutSum.setItemNo(scmInvPurInWarehsBillEntry.getItemNo());
							scmInvItemInAndOutSum.setItemName(scmInvPurInWarehsBillEntry.getItemName());
							scmInvItemInAndOutSum.setSpec(scmInvPurInWarehsBillEntry.getSpec());
							scmInvItemInAndOutSum.setClassName(scmInvPurInWarehsBillEntry.getGroupName());
							scmInvItemInAndOutSum.setUnit(scmInvPurInWarehsBillEntry.getUnit());
							scmInvItemInAndOutSum.setOrgUnitNo(scmInvPurInWarehsBillEntry.getOrgUnitNo());
							scmInvItemInAndOutSum.setInitQty(sideFlag.multiply(scmInvPurInWarehsBillEntry.getQty()));
							scmInvItemInAndOutSum.setInitAmt(sideFlag.multiply(scmInvPurInWarehsBillEntry.getAmt()));
							scmInvItemInAndOutSum.setInitTax(sideFlag.multiply(
									scmInvPurInWarehsBillEntry.getTaxAmt().subtract(scmInvPurInWarehsBillEntry.getAmt())));
							scmInvItemInAndOutSum.setInitTaxAmt(sideFlag.multiply(scmInvPurInWarehsBillEntry.getTaxAmt()));
							scmCostItemInAndOutSumList.add(scmInvItemInAndOutSum);
						}
					} else {
						boolean exists = false;
						if (!scmCostItemInAndOutSumList.isEmpty()) {
							for (ScmCostItemInAndOutSum existScmInvItemInAndOutSum : scmCostItemInAndOutSumList) {
								if (existScmInvItemInAndOutSum.getItemId() == scmInvPurInWarehsBillEntry.getItemId()) {
//										&& StringUtils.equals(existScmInvItemInAndOutSum.getOrgUnitNo(),
//												scmInvPurInWarehsBillEntry.getOrgUnitNo())) {
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
						if (!exists) {
							// 放入明细记录结果集中
							ScmCostItemInAndOutSum scmInvItemInAndOutSum = new ScmCostItemInAndOutSum(true);
							scmInvItemInAndOutSum.setItemId(scmInvPurInWarehsBillEntry.getItemId());
							scmInvItemInAndOutSum.setUnitName(scmInvPurInWarehsBillEntry.getUnitName());
							scmInvItemInAndOutSum.setItemNo(scmInvPurInWarehsBillEntry.getItemNo());
							scmInvItemInAndOutSum.setItemName(scmInvPurInWarehsBillEntry.getItemName());
							scmInvItemInAndOutSum.setSpec(scmInvPurInWarehsBillEntry.getSpec());
							scmInvItemInAndOutSum.setClassName(scmInvPurInWarehsBillEntry.getGroupName());
							scmInvItemInAndOutSum.setUnit(scmInvPurInWarehsBillEntry.getUnit());
							scmInvItemInAndOutSum.setOrgUnitNo(scmInvPurInWarehsBillEntry.getOrgUnitNo());
							scmInvItemInAndOutSum.setAddInQty(sideFlag.multiply(scmInvPurInWarehsBillEntry.getQty()));
							scmInvItemInAndOutSum.setAddInAmt(sideFlag.multiply(scmInvPurInWarehsBillEntry.getAmt()));
							scmInvItemInAndOutSum.setAddInTax(sideFlag.multiply(
									scmInvPurInWarehsBillEntry.getTaxAmt().subtract(scmInvPurInWarehsBillEntry.getAmt())));
							scmInvItemInAndOutSum.setAddInTaxAmt(sideFlag.multiply(scmInvPurInWarehsBillEntry.getTaxAmt()));
							scmCostItemInAndOutSumList.add(scmInvItemInAndOutSum);
						}
					}
				}
			}
			long beginotherTime1 = System.currentTimeMillis();			
			// 仓库-其他入库
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
			}else {
				page.getParam()
					.put(ClassUtils.getFinalModelSimpleName(ScmInvOtherInWarehsBill.class) + "."
							+ ScmInvOtherInWarehsBill.FN_STATUS,
							new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvOtherInWarehsBill.class) + "."
									+ ScmInvOtherInWarehsBill.FN_STATUS, QueryParam.QUERY_NOTIN, "'N','C'"));
			}
			List<ScmInvOtherInWarehsBillEntry2> scmInvOtherInWarehsBillEntryList = scmInvOtherInWarehsBillEntryBiz
					.queryPage(page, arglist2, "findAllPage", param);
			long minotherTime = System.currentTimeMillis();			
			log.info("其他入库明细查询耗时："+(minotherTime-beginotherTime1)+"毫秒");
			if (scmInvOtherInWarehsBillEntryList != null && !scmInvOtherInWarehsBillEntryList.isEmpty()) {
				for (ScmInvOtherInWarehsBillEntry2 scmInvOtherInWarehsBillEntry : scmInvOtherInWarehsBillEntryList) {
					if (scmInvOtherInWarehsBillEntry.getBizDate().compareTo(FormatUtils.parseDate(beginDate)) < 0) {
						// 比查询日期小的统计到期初数据中，并从List中删除
						boolean exists = false;
						if (!scmCostItemInAndOutSumList.isEmpty()) {
							for (ScmCostItemInAndOutSum existScmInvItemInAndOutSum : scmCostItemInAndOutSumList) {
								if (existScmInvItemInAndOutSum.getItemId() == scmInvOtherInWarehsBillEntry.getItemId()) {
//										&& StringUtils.equals(existScmInvItemInAndOutSum.getOrgUnitNo(),
//												scmInvOtherInWarehsBillEntry.getOrgUnitNo())) {
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
						if (!exists) {
							ScmCostItemInAndOutSum scmInvItemInAndOutSum = new ScmCostItemInAndOutSum(true);
							scmInvItemInAndOutSum.setItemId(scmInvOtherInWarehsBillEntry.getItemId());
							scmInvItemInAndOutSum.setUnitName(scmInvOtherInWarehsBillEntry.getUnitName());
							scmInvItemInAndOutSum.setItemNo(scmInvOtherInWarehsBillEntry.getItemNo());
							scmInvItemInAndOutSum.setItemName(scmInvOtherInWarehsBillEntry.getItemName());
							scmInvItemInAndOutSum.setSpec(scmInvOtherInWarehsBillEntry.getSpec());
							scmInvItemInAndOutSum.setClassName(scmInvOtherInWarehsBillEntry.getGroupName());
							scmInvItemInAndOutSum.setUnit(scmInvOtherInWarehsBillEntry.getUnit());
							scmInvItemInAndOutSum.setOrgUnitNo(scmInvOtherInWarehsBillEntry.getOrgUnitNo());
							scmInvItemInAndOutSum.setInitQty(scmInvOtherInWarehsBillEntry.getQty());
							scmInvItemInAndOutSum.setInitAmt(scmInvOtherInWarehsBillEntry.getAmt());
							scmInvItemInAndOutSum.setInitTax(scmInvOtherInWarehsBillEntry.getTaxAmt()
									.subtract(scmInvOtherInWarehsBillEntry.getAmt()));
							scmInvItemInAndOutSum.setInitTaxAmt(scmInvOtherInWarehsBillEntry.getTaxAmt());
							scmCostItemInAndOutSumList.add(scmInvItemInAndOutSum);
						}
					} else {
						boolean exists = false;
						if (!scmCostItemInAndOutSumList.isEmpty()) {
							for (ScmCostItemInAndOutSum existScmInvItemInAndOutSum : scmCostItemInAndOutSumList) {
								if (existScmInvItemInAndOutSum.getItemId() == scmInvOtherInWarehsBillEntry.getItemId()) {
//										&& StringUtils.equals(existScmInvItemInAndOutSum.getOrgUnitNo(),
//												scmInvOtherInWarehsBillEntry.getOrgUnitNo())) {
									existScmInvItemInAndOutSum.setAddInQty(existScmInvItemInAndOutSum
											.getAddInQty().add(scmInvOtherInWarehsBillEntry.getQty()));
									existScmInvItemInAndOutSum.setAddInAmt(existScmInvItemInAndOutSum
											.getAddInAmt().add(scmInvOtherInWarehsBillEntry.getAmt()));
									existScmInvItemInAndOutSum.setAddInTax(existScmInvItemInAndOutSum
											.getAddInTax().add(scmInvOtherInWarehsBillEntry.getTaxAmt()
													.subtract(scmInvOtherInWarehsBillEntry.getAmt())));
									existScmInvItemInAndOutSum.setAddInTaxAmt(existScmInvItemInAndOutSum
											.getAddInTaxAmt().add(scmInvOtherInWarehsBillEntry.getTaxAmt()));
									exists = true;
									break;
								}
							}
						}
						if (!exists) {
							// 放入明细记录结果集中
							ScmCostItemInAndOutSum scmInvItemInAndOutSum = new ScmCostItemInAndOutSum(true);
							scmInvItemInAndOutSum.setItemId(scmInvOtherInWarehsBillEntry.getItemId());
							scmInvItemInAndOutSum.setUnitName(scmInvOtherInWarehsBillEntry.getUnitName());
							scmInvItemInAndOutSum.setItemNo(scmInvOtherInWarehsBillEntry.getItemNo());
							scmInvItemInAndOutSum.setItemName(scmInvOtherInWarehsBillEntry.getItemName());
							scmInvItemInAndOutSum.setSpec(scmInvOtherInWarehsBillEntry.getSpec());
							scmInvItemInAndOutSum.setClassName(scmInvOtherInWarehsBillEntry.getGroupName());
							scmInvItemInAndOutSum.setUnit(scmInvOtherInWarehsBillEntry.getUnit());
							scmInvItemInAndOutSum.setOrgUnitNo(scmInvOtherInWarehsBillEntry.getOrgUnitNo());
							scmInvItemInAndOutSum.setAddInQty(scmInvOtherInWarehsBillEntry.getQty());
							scmInvItemInAndOutSum.setAddInAmt(scmInvOtherInWarehsBillEntry.getAmt());
							scmInvItemInAndOutSum.setAddInTax(scmInvOtherInWarehsBillEntry.getTaxAmt()
									.subtract(scmInvOtherInWarehsBillEntry.getAmt()));
							scmInvItemInAndOutSum.setAddInTaxAmt(scmInvOtherInWarehsBillEntry.getTaxAmt());
							scmCostItemInAndOutSumList.add(scmInvItemInAndOutSum);
						}
					}
				}
			}
			long endotherTime = System.currentTimeMillis();			
			log.info("其他入库明细加工耗时："+(endotherTime-minotherTime)+"毫秒");
	
			long beginTime4 = System.currentTimeMillis();
			// 仓库-销售出库
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
	
			page.getParam()
					.put(ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBillEntry2.class) + "."
							+ ScmInvSaleIssueBillEntry2.FN_WAREHOUSEID,
							new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBillEntry2.class) + "."
									+ ScmInvSaleIssueBillEntry2.FN_WAREHOUSEID, QueryParam.QUERY_GT, "0"));
			
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
			}else {
				page.getParam().put(
						ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBill.class) + "." + ScmInvSaleIssueBill.FN_STATUS,
						new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBill.class) + "."
								+ ScmInvSaleIssueBill.FN_STATUS, QueryParam.QUERY_NOTIN, "'N','C'"));
			}
			List<ScmInvSaleIssueBillEntry2> scmInvSaleIssueBillEntryList = scmInvSaleIssueBillEntryBiz.queryPage(page,
					arglist2, "findAllPage", param);
			long minTime4 = System.currentTimeMillis();
			log.info("销售出库查询耗时："+(minTime4-beginTime4)+"毫秒");
			if (scmInvSaleIssueBillEntryList != null && !scmInvSaleIssueBillEntryList.isEmpty()) {
				for (ScmInvSaleIssueBillEntry2 scmInvSaleIssueBillEntry : scmInvSaleIssueBillEntryList) {
					BigDecimal sideFlag = BigDecimal.ONE;
					if (StringUtils.contains("6,7,8", scmInvSaleIssueBillEntry.getBizType())) {
						sideFlag = new BigDecimal("-1");
					}
					if (scmInvSaleIssueBillEntry.getBizDate().compareTo(FormatUtils.parseDate(beginDate)) < 0) {
						// 比查询日期小的统计到期初数据中
						boolean exists = false;
						if (!scmCostItemInAndOutSumList.isEmpty()) {
							for (ScmCostItemInAndOutSum existScmInvItemInAndOutSum : scmCostItemInAndOutSumList) {
								if (existScmInvItemInAndOutSum.getItemId() == scmInvSaleIssueBillEntry.getItemId()) {
//										&& StringUtils.equals(existScmInvItemInAndOutSum.getOrgUnitNo(),
//												scmInvSaleIssueBillEntry.getOrgUnitNo())) {
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
						if (!exists) {
							ScmCostItemInAndOutSum scmInvItemInAndOutSum = new ScmCostItemInAndOutSum(true);
							scmInvItemInAndOutSum.setItemId(scmInvSaleIssueBillEntry.getItemId());
							scmInvItemInAndOutSum.setUnitName(scmInvSaleIssueBillEntry.getUnitName());
							scmInvItemInAndOutSum.setItemNo(scmInvSaleIssueBillEntry.getItemNo());
							scmInvItemInAndOutSum.setItemName(scmInvSaleIssueBillEntry.getItemName());
							scmInvItemInAndOutSum.setSpec(scmInvSaleIssueBillEntry.getSpec());
							scmInvItemInAndOutSum.setClassName(scmInvSaleIssueBillEntry.getGroupName());
							scmInvItemInAndOutSum.setUnit(scmInvSaleIssueBillEntry.getUnit());
							scmInvItemInAndOutSum.setOrgUnitNo(scmInvSaleIssueBillEntry.getOrgUnitNo());
							scmInvItemInAndOutSum.setInitQty(
									BigDecimal.ZERO.subtract(sideFlag.multiply(scmInvSaleIssueBillEntry.getQty())));
							scmInvItemInAndOutSum.setInitAmt(
									BigDecimal.ZERO.subtract(sideFlag.multiply(scmInvSaleIssueBillEntry.getAmt())));
							scmInvItemInAndOutSum.setInitTax(BigDecimal.ZERO.subtract(sideFlag.multiply(
									scmInvSaleIssueBillEntry.getTaxAmt().subtract(scmInvSaleIssueBillEntry.getAmt()))));
							scmInvItemInAndOutSum.setInitTaxAmt(
									BigDecimal.ZERO.subtract(sideFlag.multiply(scmInvSaleIssueBillEntry.getTaxAmt())));
							scmCostItemInAndOutSumList.add(scmInvItemInAndOutSum);
						}
					} else {
						boolean exists = false;
						if (!scmCostItemInAndOutSumList.isEmpty()) {
							for (ScmCostItemInAndOutSum existScmInvItemInAndOutSum : scmCostItemInAndOutSumList) {
								if (existScmInvItemInAndOutSum.getItemId() == scmInvSaleIssueBillEntry.getItemId()) {
//										&& StringUtils.equals(existScmInvItemInAndOutSum.getOrgUnitNo(),
//												scmInvSaleIssueBillEntry.getOrgUnitNo())) {
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
						if (!exists) {
							// 放入明细记录结果集中
							ScmCostItemInAndOutSum scmInvItemInAndOutSum = new ScmCostItemInAndOutSum(true);
							scmInvItemInAndOutSum.setItemId(scmInvSaleIssueBillEntry.getItemId());
							scmInvItemInAndOutSum.setUnitName(scmInvSaleIssueBillEntry.getUnitName());
							scmInvItemInAndOutSum.setItemNo(scmInvSaleIssueBillEntry.getItemNo());
							scmInvItemInAndOutSum.setItemName(scmInvSaleIssueBillEntry.getItemName());
							scmInvItemInAndOutSum.setSpec(scmInvSaleIssueBillEntry.getSpec());
							scmInvItemInAndOutSum.setClassName(scmInvSaleIssueBillEntry.getGroupName());
							scmInvItemInAndOutSum.setUnit(scmInvSaleIssueBillEntry.getUnit());
							scmInvItemInAndOutSum.setOrgUnitNo(scmInvSaleIssueBillEntry.getOrgUnitNo());
							scmInvItemInAndOutSum.setOutQty(sideFlag.multiply(scmInvSaleIssueBillEntry.getQty()));
							scmInvItemInAndOutSum.setOutAmt(sideFlag.multiply(scmInvSaleIssueBillEntry.getAmt()));
							scmInvItemInAndOutSum.setOutTax(sideFlag.multiply(
									scmInvSaleIssueBillEntry.getTaxAmt().subtract(scmInvSaleIssueBillEntry.getAmt())));
							scmInvItemInAndOutSum.setOutTaxAmt(sideFlag.multiply(scmInvSaleIssueBillEntry.getTaxAmt()));
							scmCostItemInAndOutSumList.add(scmInvItemInAndOutSum);
						}
					}
				}
			}
			long endTime4 = System.currentTimeMillis();
			log.info("销售出库加工耗时："+(endTime4-minTime4)+"毫秒");
			
			// 获取仓库-领料出库数据
			long beginTime2 = System.currentTimeMillis();
			page = new Page();
			page.setModelClass(ScmInvMaterialReqBillEntry2.class);
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
			}else {
				page.getParam()
				.put(ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBill2.class) + "."
						+ ScmInvMaterialReqBill2.FN_STATUS,
						new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBill2.class) + "."
								+ ScmInvMaterialReqBill2.FN_STATUS, QueryParam.QUERY_NOTIN, "'N','C'"));
			}
			List<ScmInvMaterialReqBillEntry2> scmInvMaterialReqBillEntryList = scmInvMaterialReqBillEntryBiz.queryPage(page,
					arglist2, "findAllPage", param);
			
			long dataEndTime3 = System.currentTimeMillis();
			log.info("领料出库明细查询耗时："+(dataEndTime3-beginTime2)+"毫秒");
			if (scmInvMaterialReqBillEntryList != null && !scmInvMaterialReqBillEntryList.isEmpty()) {
				for (ScmInvMaterialReqBillEntry2 scmInvMaterialReqBillEntry : scmInvMaterialReqBillEntryList) {
					BigDecimal sideFlag = BigDecimal.ONE;
					//对于当前财务组织而言，退仓 为收入。所以 	出库 为-1				
					if (StringUtils.equals("1", scmInvMaterialReqBillEntry.getBizType())) {
						sideFlag = new BigDecimal("-1"); // 领料出库
					}
					if (scmInvMaterialReqBillEntry.getBizDate().compareTo(FormatUtils.parseDate(beginDate)) < 0) {
						// 比查询日期小的统计到期初数据中
						boolean exists = false;
						if (!scmCostItemInAndOutSumList.isEmpty()) {
							for (ScmCostItemInAndOutSum existScmInvItemInAndOutSum : scmCostItemInAndOutSumList) {
								if (existScmInvItemInAndOutSum.getItemId() == scmInvMaterialReqBillEntry.getItemId()) {
									existScmInvItemInAndOutSum.setInitQty(existScmInvItemInAndOutSum.getInitQty()
											.add(sideFlag.multiply(scmInvMaterialReqBillEntry.getQty())));
									existScmInvItemInAndOutSum.setInitAmt(existScmInvItemInAndOutSum.getInitAmt()
											.add(sideFlag.multiply(scmInvMaterialReqBillEntry.getAmt())));
									existScmInvItemInAndOutSum.setInitTax(existScmInvItemInAndOutSum.getInitTax()
											.add(sideFlag.multiply(scmInvMaterialReqBillEntry.getTaxAmt()
													.subtract(scmInvMaterialReqBillEntry.getAmt()))));
									existScmInvItemInAndOutSum
											.setInitTaxAmt(existScmInvItemInAndOutSum.getInitTaxAmt().add(
													sideFlag.multiply(scmInvMaterialReqBillEntry.getTaxAmt())));
									exists = true;
									break;
								}
							}
						}
						if (!exists) {
							ScmCostItemInAndOutSum scmInvItemInAndOutSum = new ScmCostItemInAndOutSum(true);
							scmInvItemInAndOutSum.setItemId(scmInvMaterialReqBillEntry.getItemId());
							scmInvItemInAndOutSum.setUnitName(scmInvMaterialReqBillEntry.getUnitName());
							scmInvItemInAndOutSum.setItemNo(scmInvMaterialReqBillEntry.getItemNo());
							scmInvItemInAndOutSum.setItemName(scmInvMaterialReqBillEntry.getItemName());
							scmInvItemInAndOutSum.setSpec(scmInvMaterialReqBillEntry.getSpec());
							scmInvItemInAndOutSum.setClassName(scmInvMaterialReqBillEntry.getGroupName());
							scmInvItemInAndOutSum.setUnit(scmInvMaterialReqBillEntry.getUnit());
							scmInvItemInAndOutSum.setOrgUnitNo(scmInvMaterialReqBillEntry.getOrgUnitNo());
							scmInvItemInAndOutSum.setInitQty(
									BigDecimal.ZERO.add(sideFlag.multiply(scmInvMaterialReqBillEntry.getQty())));
							scmInvItemInAndOutSum.setInitAmt(
									BigDecimal.ZERO.add(sideFlag.multiply(scmInvMaterialReqBillEntry.getAmt())));
							scmInvItemInAndOutSum.setInitTax(BigDecimal.ZERO.add(sideFlag.multiply(
									scmInvMaterialReqBillEntry.getTaxAmt().subtract(scmInvMaterialReqBillEntry.getAmt()))));
							scmInvItemInAndOutSum.setInitTaxAmt(
									BigDecimal.ZERO.add(sideFlag.multiply(scmInvMaterialReqBillEntry.getTaxAmt())));
							scmCostItemInAndOutSumList.add(scmInvItemInAndOutSum);
						}
					} else {
						boolean exists = false;
						if (!scmCostItemInAndOutSumList.isEmpty()) {
							for (ScmCostItemInAndOutSum existScmInvItemInAndOutSum : scmCostItemInAndOutSumList) {
								if (existScmInvItemInAndOutSum.getItemId() == scmInvMaterialReqBillEntry.getItemId()) {
									existScmInvItemInAndOutSum.setAddInQty(existScmInvItemInAndOutSum.getAddInQty()
											.add(sideFlag.multiply(scmInvMaterialReqBillEntry.getQty())));
									existScmInvItemInAndOutSum.setAddInAmt(existScmInvItemInAndOutSum.getAddInAmt()
											.add(sideFlag.multiply(scmInvMaterialReqBillEntry.getAmt())));
									existScmInvItemInAndOutSum.setAddInTax(existScmInvItemInAndOutSum.getAddInTax()
											.add(sideFlag.multiply(scmInvMaterialReqBillEntry.getTaxAmt()
													.subtract(scmInvMaterialReqBillEntry.getAmt()))));
									existScmInvItemInAndOutSum
											.setAddInTaxAmt(existScmInvItemInAndOutSum.getAddInTaxAmt().add(
													sideFlag.multiply(scmInvMaterialReqBillEntry.getTaxAmt())));
									exists = true;
									break;
								}
							}
						}
						if (!exists) {
							// 放入明细记录结果集中
							ScmCostItemInAndOutSum scmInvItemInAndOutSum = new ScmCostItemInAndOutSum(true);
							scmInvItemInAndOutSum.setItemId(scmInvMaterialReqBillEntry.getItemId());
							scmInvItemInAndOutSum.setUnitName(scmInvMaterialReqBillEntry.getUnitName());
							scmInvItemInAndOutSum.setItemNo(scmInvMaterialReqBillEntry.getItemNo());
							scmInvItemInAndOutSum.setItemName(scmInvMaterialReqBillEntry.getItemName());
							scmInvItemInAndOutSum.setSpec(scmInvMaterialReqBillEntry.getSpec());
							scmInvItemInAndOutSum.setClassName(scmInvMaterialReqBillEntry.getGroupName());
							scmInvItemInAndOutSum.setUnit(scmInvMaterialReqBillEntry.getUnit());
							scmInvItemInAndOutSum.setOrgUnitNo(scmInvMaterialReqBillEntry.getOrgUnitNo());
							scmInvItemInAndOutSum.setAddInQty(sideFlag.multiply(scmInvMaterialReqBillEntry.getQty()));
							scmInvItemInAndOutSum.setAddInAmt(sideFlag.multiply(scmInvMaterialReqBillEntry.getAmt()));
							scmInvItemInAndOutSum.setAddInTax(sideFlag.multiply(
									scmInvMaterialReqBillEntry.getTaxAmt().subtract(scmInvMaterialReqBillEntry.getAmt())));
							scmInvItemInAndOutSum.setAddInTaxAmt(sideFlag.multiply(scmInvMaterialReqBillEntry.getTaxAmt()));
							scmCostItemInAndOutSumList.add(scmInvItemInAndOutSum);
						}
					}
				}
			}
			long EndTime3 = System.currentTimeMillis();
			log.info("领料出库明细加工耗时："+(EndTime3-dataEndTime3)+"毫秒");
			
			long beginTime3 = System.currentTimeMillis();
			// 仓库-其他出库
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
			}else {
				page.getParam()
				.put(ClassUtils.getFinalModelSimpleName(ScmInvOtherIssueBill.class) + "."
						+ ScmInvOtherIssueBill.FN_STATUS,
						new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvOtherIssueBill.class) + "."
								+ ScmInvOtherIssueBill.FN_STATUS, QueryParam.QUERY_NOTIN, "'N','C'"));
			}
			List<ScmInvOtherIssueBillEntry2> scmInvOtherIssueBillEntryList = scmInvOtherIssueBillEntryBiz.queryPage(page,
					arglist2, "findAllPage", param);
			long minTime3 = System.currentTimeMillis();
			log.info("其他出库查询耗时："+(minTime3-beginTime3)+"毫秒");
			if (scmInvOtherIssueBillEntryList != null && !scmInvOtherIssueBillEntryList.isEmpty()) {
				for (ScmInvOtherIssueBillEntry2 scmInvOtherIssueBillEntry : scmInvOtherIssueBillEntryList) {
					if (scmInvOtherIssueBillEntry.getBizDate().compareTo(FormatUtils.parseDate(beginDate)) < 0) {
						// 比查询日期小的统计到期初数据中
						boolean exists = false;
						if (!scmCostItemInAndOutSumList.isEmpty()) {
							for (ScmCostItemInAndOutSum existScmInvItemInAndOutSum : scmCostItemInAndOutSumList) {
								if (existScmInvItemInAndOutSum.getItemId() == scmInvOtherIssueBillEntry.getItemId()) {
//										&& StringUtils.equals(existScmInvItemInAndOutSum.getOrgUnitNo(),
//												scmInvOtherIssueBillEntry.getOrgUnitNo())) {
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
						if (!exists) {
							ScmCostItemInAndOutSum scmInvItemInAndOutSum = new ScmCostItemInAndOutSum(true);
							scmInvItemInAndOutSum.setItemId(scmInvOtherIssueBillEntry.getItemId());
							scmInvItemInAndOutSum.setUnitName(scmInvOtherIssueBillEntry.getUnitName());
							scmInvItemInAndOutSum.setItemNo(scmInvOtherIssueBillEntry.getItemNo());
							scmInvItemInAndOutSum.setItemName(scmInvOtherIssueBillEntry.getItemName());
							scmInvItemInAndOutSum.setSpec(scmInvOtherIssueBillEntry.getSpec());
							scmInvItemInAndOutSum.setClassName(scmInvOtherIssueBillEntry.getGroupName());
							scmInvItemInAndOutSum.setUnit(scmInvOtherIssueBillEntry.getUnit());
							scmInvItemInAndOutSum.setOrgUnitNo(scmInvOtherIssueBillEntry.getOrgUnitNo());
							scmInvItemInAndOutSum.setInitQty(BigDecimal.ZERO.subtract(scmInvOtherIssueBillEntry.getQty()));
							scmInvItemInAndOutSum.setInitAmt(BigDecimal.ZERO.subtract(scmInvOtherIssueBillEntry.getAmt()));
							scmInvItemInAndOutSum.setInitTax(BigDecimal.ZERO.subtract(
									scmInvOtherIssueBillEntry.getTaxAmt().subtract(scmInvOtherIssueBillEntry.getAmt())));
							scmInvItemInAndOutSum
									.setInitTaxAmt(BigDecimal.ZERO.subtract(scmInvOtherIssueBillEntry.getTaxAmt()));
							scmCostItemInAndOutSumList.add(scmInvItemInAndOutSum);
						}
					} else {
						boolean exists = false;
						if (!scmCostItemInAndOutSumList.isEmpty()) {
							for (ScmCostItemInAndOutSum existScmInvItemInAndOutSum : scmCostItemInAndOutSumList) {
								if (existScmInvItemInAndOutSum.getItemId() == scmInvOtherIssueBillEntry.getItemId()) {
//										&& StringUtils.equals(existScmInvItemInAndOutSum.getOrgUnitNo(),
//									scmInvOtherIssueBillEntry.getOrgUnitNo())) {
									existScmInvItemInAndOutSum.setOutQty(existScmInvItemInAndOutSum.getOutQty()
											.add(scmInvOtherIssueBillEntry.getQty()));
									existScmInvItemInAndOutSum.setOutAmt(existScmInvItemInAndOutSum.getOutAmt()
											.add(scmInvOtherIssueBillEntry.getAmt()));
									existScmInvItemInAndOutSum.setOutTax(
											existScmInvItemInAndOutSum.getOutTax().add(scmInvOtherIssueBillEntry
													.getTaxAmt().subtract(scmInvOtherIssueBillEntry.getAmt())));
									existScmInvItemInAndOutSum.setOutTaxAmt(existScmInvItemInAndOutSum
											.getOutTaxAmt().add(scmInvOtherIssueBillEntry.getTaxAmt()));
									exists = true;
									break;
								}
							}
						}
						if (!exists) {
							// 放入明细记录结果集中
							ScmCostItemInAndOutSum scmInvItemInAndOutSum = new ScmCostItemInAndOutSum(true);
							scmInvItemInAndOutSum.setItemId(scmInvOtherIssueBillEntry.getItemId());
							scmInvItemInAndOutSum.setUnitName(scmInvOtherIssueBillEntry.getUnitName());
							scmInvItemInAndOutSum.setItemNo(scmInvOtherIssueBillEntry.getItemNo());
							scmInvItemInAndOutSum.setItemName(scmInvOtherIssueBillEntry.getItemName());
							scmInvItemInAndOutSum.setSpec(scmInvOtherIssueBillEntry.getSpec());
							scmInvItemInAndOutSum.setClassName(scmInvOtherIssueBillEntry.getGroupName());
							scmInvItemInAndOutSum.setUnit(scmInvOtherIssueBillEntry.getUnit());
							scmInvItemInAndOutSum.setOrgUnitNo(scmInvOtherIssueBillEntry.getOrgUnitNo());
							scmInvItemInAndOutSum.setOutQty(scmInvOtherIssueBillEntry.getQty());
							scmInvItemInAndOutSum.setOutAmt(scmInvOtherIssueBillEntry.getAmt());
							scmInvItemInAndOutSum.setOutTax(
									scmInvOtherIssueBillEntry.getTaxAmt().subtract(scmInvOtherIssueBillEntry.getAmt()));
							scmInvItemInAndOutSum.setOutTaxAmt(scmInvOtherIssueBillEntry.getTaxAmt());
							scmCostItemInAndOutSumList.add(scmInvItemInAndOutSum);
						}
					}
				}
			}
			long endTime3 = System.currentTimeMillis();
			log.info("其他出库加工耗时："+(endTime3-minTime3)+"毫秒");
	  
			long dataEndTimeBreakage = System.currentTimeMillis();
			if (!scmCostItemInAndOutSumList.isEmpty()) {
				for (ScmCostItemInAndOutSum scmInvItemInAndOutSum : scmCostItemInAndOutSumList) {
//					if (scmInvItemInAndOutSum.getUnit() > 0) {
//						ScmMeasureUnit scmMeasureUnit = scmMeasureUnitBiz.selectDirect(scmInvItemInAndOutSum.getUnit(),
//								param);
//						if (scmMeasureUnit != null)
//							scmInvItemInAndOutSum.setUnitName(scmMeasureUnit.getUnitName());
//					}
//					//物资名称、分类
//					if (scmInvItemInAndOutSum.getItemId() > 0) {
//						ScmMaterial2 scmMaterial = scmMaterialBiz.selectByItemId(scmInvItemInAndOutSum.getItemId(), param);
//						if (scmMaterial != null) {
//							scmInvItemInAndOutSum.setItemNo(scmMaterial.getItemNo());
//							scmInvItemInAndOutSum.setItemName(scmMaterial.getItemName());
//							scmInvItemInAndOutSum.setSpec(scmMaterial.getSpec());
//							if (scmMaterial.getGroupId() > 0) {
//								ScmMaterialGroup scmMaterialGroup = scmMaterialGroupBiz
//										.selectDirect(scmMaterial.getGroupId(), param);
//								if (scmMaterialGroup != null) {
//									scmInvItemInAndOutSum.setClassName(scmMaterialGroup.getClassName());
//								}
//							}
//						}
//					}
	
					scmInvItemInAndOutSum.setStockQty(scmInvItemInAndOutSum.getInitQty()
							.add(scmInvItemInAndOutSum.getAddInQty()).subtract(scmInvItemInAndOutSum.getOutQty()));
					scmInvItemInAndOutSum.setStockAmt(scmInvItemInAndOutSum.getInitAmt()
							.add(scmInvItemInAndOutSum.getAddInAmt()).subtract(scmInvItemInAndOutSum.getOutAmt()));
					scmInvItemInAndOutSum.setStockAvgPrice(
							BigDecimal.ZERO.compareTo(scmInvItemInAndOutSum.getStockQty()) == 0 ? BigDecimal.ZERO
									: scmInvItemInAndOutSum.getStockAmt().divide(scmInvItemInAndOutSum.getStockQty(), 2,
											BigDecimal.ROUND_HALF_UP));
					scmInvItemInAndOutSum.setStockTax(scmInvItemInAndOutSum.getInitTax()
							.add(scmInvItemInAndOutSum.getAddInTax()).subtract(scmInvItemInAndOutSum.getOutTax()));
					scmInvItemInAndOutSum.setStockTaxAmt(scmInvItemInAndOutSum.getInitTaxAmt()
							.add(scmInvItemInAndOutSum.getAddInTaxAmt()).subtract(scmInvItemInAndOutSum.getOutTaxAmt()));
				}
			}

			
			long dataEndTimeBreakage1 = System.currentTimeMillis();
			log.info("数据加工耗时："+(dataEndTimeBreakage1-dataEndTimeBreakage)+"毫秒");
		}
		
		// 数据排序
		String fields[] = { "className", "itemNo" };
		String sorts[] = { "ASC", "ASC" };
		scmCostItemInAndOutSumList = (List<ScmCostItemInAndOutSum>) ListSortUtil.sort(scmCostItemInAndOutSumList,
				fields, sorts);

		if (scmCostItemInAndOutSumList != null && !scmCostItemInAndOutSumList.isEmpty()) {
			log.info("报表总记录："+(scmCostItemInAndOutSumList.size())+"条数");
		}

		return scmCostItemInAndOutSumList;
	}
    
}

