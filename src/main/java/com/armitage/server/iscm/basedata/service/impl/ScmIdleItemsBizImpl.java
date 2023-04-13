
package com.armitage.server.iscm.basedata.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Page;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.base.model.QueryParam;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.util.ClassUtils;
import com.armitage.server.common.util.FormatUtils;
import com.armitage.server.iscm.basedata.dao.ScmIdleItemsDAO;
import com.armitage.server.iscm.basedata.model.ScmIdleItems;
import com.armitage.server.iscm.basedata.model.ScmIdleItems2;
import com.armitage.server.iscm.basedata.model.ScmIdleItemsAdvQuery;
import com.armitage.server.iscm.basedata.model.ScmMaterialGroup;
import com.armitage.server.iscm.basedata.model.Scmsupplier2;
import com.armitage.server.iscm.basedata.service.ScmIdleItemsBiz;
import com.armitage.server.iscm.basedata.service.ScmMaterialGroupBiz;
import com.armitage.server.iscm.basedata.service.ScmsupplierBiz;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCountingTask;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurRequire2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.service.ScmPurRequireBiz;
import com.armitage.server.system.model.BillType2;
import com.armitage.server.system.model.OrgAdmin2;
import com.armitage.server.system.model.OrgBaseUnit;
import com.armitage.server.system.model.OrgCompany2;
import com.armitage.server.system.model.OrgCostCenter2;
import com.armitage.server.system.service.BillTypeBiz;
import com.armitage.server.system.service.OrgAdminBiz;
import com.armitage.server.system.service.OrgCompanyBiz;
import com.armitage.server.system.service.OrgCostCenterBiz;
import com.armitage.server.system.service.OrgUnitBiz;
import com.armitage.server.system.service.SysParamBiz;
import com.armitage.server.system.util.OrgUnitRelationType;
import com.armitage.server.user.model.Usr;
import com.armitage.server.user.service.UsrBiz;
import com.github.houbb.heaven.util.lang.StringUtil;
import org.springframework.stereotype.Service;

@Service("scmIdleItemsBiz")
public class ScmIdleItemsBizImpl extends BaseBizImpl<ScmIdleItems> implements ScmIdleItemsBiz {

	private OrgAdminBiz orgAdminBiz;
	private OrgCompanyBiz orgCompanyBiz;
	private SysParamBiz sysParamBiz;
	private OrgCostCenterBiz orgCostCenterBiz;
	private OrgUnitBiz orgUnitBiz;
	private ScmsupplierBiz scmsupplierBiz;
	private ScmPurRequireBiz scmPurRequireBiz;
	private UsrBiz usrBiz;
	private BillTypeBiz billTypeBiz;
	private ScmMaterialGroupBiz scmMaterialGroupBiz;

	public void setOrgAdminBiz(OrgAdminBiz orgAdminBiz) {
		this.orgAdminBiz = orgAdminBiz;
	}

	public void setOrgCompanyBiz(OrgCompanyBiz orgCompanyBiz) {
		this.orgCompanyBiz = orgCompanyBiz;
	}

	public void setSysParamBiz(SysParamBiz sysParamBiz) {
		this.sysParamBiz = sysParamBiz;
	}

	public void setOrgCostCenterBiz(OrgCostCenterBiz orgCostCenterBiz) {
		this.orgCostCenterBiz = orgCostCenterBiz;
	}

	public void setOrgUnitBiz(OrgUnitBiz orgUnitBiz) {
		this.orgUnitBiz = orgUnitBiz;
	}

	public void setScmsupplierBiz(ScmsupplierBiz scmsupplierBiz) {
		this.scmsupplierBiz = scmsupplierBiz;
	}

	public void setScmPurRequireBiz(ScmPurRequireBiz scmPurRequireBiz) {
		this.scmPurRequireBiz = scmPurRequireBiz;
	}

	public void setUsrBiz(UsrBiz usrBiz) {
		this.usrBiz = usrBiz;
	}

	public void setBillTypeBiz(BillTypeBiz billTypeBiz) {
		this.billTypeBiz = billTypeBiz;
	}

	public void setScmMaterialGroupBiz(ScmMaterialGroupBiz scmMaterialGroupBiz) {
		this.scmMaterialGroupBiz = scmMaterialGroupBiz;
	}

	@Override
	public List<ScmIdleItems> selectByCostCenterOrg(String orgUnitNo, String useOrgUnitNo, Param param)
			throws AppException {
		if (StringUtil.isNotBlank(useOrgUnitNo) && StringUtil.isNotBlank(orgUnitNo)) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("useOrgUnitNo", useOrgUnitNo);
			map.put("orgUnitNo", orgUnitNo);
			map.put("controlUnitNo", param.getControlUnitNo());
			return ((ScmIdleItemsDAO) dao).selectByCostCenterOrg(map);
		}
		return null;
	}

	@Override
	public List<ScmIdleItems2> selectIdleItemsByItems(String itemId, Param param) throws AppException {
		List<ScmIdleItems2> scmIdleItems= null;
		String enableIdleItems = sysParamBiz.getValue(param.getControlUnitNo(), "SCM_EnableIdleItems", "N",param);
		if (StringUtils.equals("N", enableIdleItems)) {
			return null;
		}
		if (StringUtils.isNotBlank(itemId)) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("itemId", itemId);
			String idleItemsQueryScope = sysParamBiz.getValue(param.getControlUnitNo(), "SCM_IdleItemsQueryScope", "S",param);
			map.put("idleItemsQueryScope", idleItemsQueryScope);
			if (StringUtils.equals(idleItemsQueryScope, "S")) {
				map.put("controlUnitNo", param.getControlUnitNo());
			} else if (StringUtils.equals(idleItemsQueryScope, "V")) {
				// 查询财务所有委托财务组织的行政组织
				StringBuffer buffer = new StringBuffer();
				Page finPage = new Page();
				finPage.setModelClass(OrgCompany2.class);
				finPage.setShowCount(Integer.MAX_VALUE);
				List<String> finArglist = new ArrayList<>();
				finArglist.add("type=to");
				finArglist.add("relationType=" + OrgUnitRelationType.ADMINTOFIN);
				finArglist.add("fromOrgUnitNo=" + param.getOrgUnitNo());
				List<OrgCompany2> orgCompanyList = orgCompanyBiz.queryPage(finPage, finArglist, "queryPageEx", param);
				List<OrgAdmin2> orgAdminList = new ArrayList<>();
				if (orgCompanyList != null && !orgCompanyList.isEmpty()) {
					List<String> argList = new ArrayList<>();
					argList.add("type=from");
					argList.add("relationType=" + OrgUnitRelationType.ADMINTOFIN);
					argList.add("toOrgUnitNo=" + orgCompanyList.get(0).getOrgUnitNo());
					finPage.setModelClass(OrgAdmin2.class);
					orgAdminList = orgAdminBiz.queryPage(finPage, argList, "queryPageEx", param);
				}
				if (orgAdminList != null && !orgAdminList.isEmpty()) {
					for (OrgAdmin2 orgAdmin2 : orgAdminList) {
						if (StringUtils.isNotBlank(buffer.toString())) {
							buffer.append(",");
						}
						buffer.append("'").append(orgAdmin2.getOrgUnitNo()).append("'");
					}
				}
				if (StringUtils.isEmpty(buffer.toString())) {
					buffer.append("0");
				}
				if (StringUtils.isNotBlank(buffer.toString())) {
					map.put("orgUnitNo", buffer.toString());
				}
			}
			
			scmIdleItems = ((ScmIdleItemsDAO) dao).selectIdleItemsByItems(map);
			if (scmIdleItems != null && !scmIdleItems.isEmpty()) {
				for (ScmIdleItems2 scmIdleItems2 : scmIdleItems) {
					Scmsupplier2 scmsupplier = scmsupplierBiz.queryByOrg(scmIdleItems2.getFinOrgUnitNo(),param);
					if(scmsupplier!=null) {
						scmIdleItems2.setVendorId(scmsupplier.getId());
						scmIdleItems2.setConvertMap(ScmIdleItems2.FN_VENDORID, scmsupplier);
					}
					setConvertMap(scmIdleItems2, param);
				}
			}
		}
		return scmIdleItems;
	}
	private void setConvertMap(ScmIdleItems2 scmIdleItems2,Param param) {
		if (scmIdleItems2 != null) {
			if (StringUtils.isNotBlank(scmIdleItems2.getFinOrgUnitNo())) {
				OrgCompany2 orgCompany = orgCompanyBiz.selectByOrgUnitNo(scmIdleItems2.getFinOrgUnitNo(), param);
				if (orgCompany != null) {
					scmIdleItems2.setConvertMap(scmIdleItems2.FN_FINORGUNITNO, orgCompany);
				}
			}
			//成本中心
			if (StringUtils.isNotBlank(scmIdleItems2.getOrgUnitNo())) {
				OrgBaseUnit orgBaseUnit = orgUnitBiz.selectbyOrgNo(scmIdleItems2.getOrgUnitNo(), param);
				if (orgBaseUnit != null) {
					scmIdleItems2.setConvertMap(scmIdleItems2.FN_ORGUNITNO, orgBaseUnit);
				}
			}
			//盘存部门
			if (StringUtils.isNotBlank(scmIdleItems2.getUseOrgUnitNo())) {
				OrgBaseUnit orgBaseUnit = orgUnitBiz.selectbyOrgNo(scmIdleItems2.getUseOrgUnitNo(), param);
				if (orgBaseUnit != null) {
					scmIdleItems2.setConvertMap(scmIdleItems2.FN_USEORGUNITNO, orgBaseUnit);
				}
			}
			//创建人
			if (StringUtils.isNotBlank(scmIdleItems2.getCreator())) {
				Usr usr = usrBiz.selectByCode(scmIdleItems2.getCreator(), param);
				if (usr != null) {
					scmIdleItems2.setConvertMap(ScmIdleItems2.FN_CREATOR, usr);
				}
			}
			//修改人
			if (StringUtils.isNotBlank(scmIdleItems2.getEditor())) {
				Usr usr = usrBiz.selectByCode(scmIdleItems2.getEditor(), param);
				if (usr != null) {
					scmIdleItems2.setConvertMap(ScmIdleItems2.FN_EDITOR, usr);
				}
			}
			if (StringUtils.isNotBlank(scmIdleItems2.getIdleBillType())) {
				if (StringUtils.equals("CountCostCenter", scmIdleItems2.getIdleBillType())) {
					scmIdleItems2.setIdleBillType(Message.getMessage(param.getLang(),"field.ScmIdleItems.IdleBillType.craft"));
				}else {
					BillType2 billType2 = billTypeBiz.selectByBillCode(scmIdleItems2.getIdleBillType(), param);
					if (billType2 != null) {
						scmIdleItems2.setIdleBillType(billType2.getBillName());
						scmIdleItems2.setConvertMap(ScmIdleItems2.FN_IDLEBILLTYPE, billType2);
					}
				}
			}
		}
	}

	@Override
	public List<ScmIdleItems2> selectIdleDrillData(Long billId, Param createParam) throws AppException {
		if (billId > 0) {
			ScmPurRequire2 scmPurRequire2 = scmPurRequireBiz.select(billId, createParam);
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("prId", billId);
			map.put("orgUnitNo", scmPurRequire2.getOrgUnitNo());
			List<ScmIdleItems2> scmIdleItems2List = ((ScmIdleItemsDAO) dao).selectIdleDrillData(map);
			if (scmIdleItems2List != null && !scmIdleItems2List.isEmpty()) {
				for (ScmIdleItems2 scmIdleItems2 : scmIdleItems2List) {
					setConvertMap(scmIdleItems2, createParam);
				}
			}
			return scmIdleItems2List;
		}
		return null;
	}
	
	public void doAdvQuery(Page page, Param param) {
		if (page.getModel() != null) {
			if (page.getModel() instanceof ScmIdleItemsAdvQuery) {
				ScmIdleItemsAdvQuery scmIdleItemsAdvQuery = (ScmIdleItemsAdvQuery) page.getModel();
				if (scmIdleItemsAdvQuery.getBizBeginDate() != null) {
					if (scmIdleItemsAdvQuery.getBizEndDate()!= null) {
						page.getParam().put(ScmIdleItems.FN_BIZDATE,
	                            new QueryParam(ClassUtils.getFinalModelSimpleName(ScmIdleItems.class)+"."+ScmIdleItems.FN_BIZDATE, 
	                                    QueryParam.QUERY_BETWEEN,
	                                    FormatUtils.fmtDate(scmIdleItemsAdvQuery.getBizBeginDate()),
	                                    FormatUtils.fmtDate(scmIdleItemsAdvQuery.getBizEndDate())));
					}else {
						page.getParam().put(ScmIdleItems.FN_BIZDATE,
	                            new QueryParam(ClassUtils.getFinalModelSimpleName(ScmIdleItems.class)+"."+ScmIdleItems.FN_BIZDATE, 
	                                    QueryParam.QUERY_GE,FormatUtils.fmtDate(scmIdleItemsAdvQuery.getBizBeginDate())));
					}
				}else if (scmIdleItemsAdvQuery.getBizEndDate()!= null) {
					page.getParam().put(ScmIdleItems.FN_BIZDATE,
                            new QueryParam(ClassUtils.getFinalModelSimpleName(ScmIdleItems.class)+"."+ScmIdleItems.FN_BIZDATE, 
                                    QueryParam.QUERY_LE,FormatUtils.fmtDate(scmIdleItemsAdvQuery.getBizEndDate())));
				}
				if (StringUtils.isNotBlank(scmIdleItemsAdvQuery.getOrgUnitNo())) {
					page.getParam().put(ScmIdleItems2.FN_ORGUNITNO,
                            new QueryParam(ClassUtils.getFinalModelSimpleName(ScmIdleItems2.class)+"."+ScmIdleItems2.FN_ORGUNITNO, 
                                    QueryParam.QUERY_EQ,scmIdleItemsAdvQuery.getOrgUnitNo()));
				}
				if (StringUtils.isNotBlank(scmIdleItemsAdvQuery.getUseOrgUnitNo())) {
					page.getParam().put(ScmIdleItems2.FN_USEORGUNITNO,
                            new QueryParam(ClassUtils.getFinalModelSimpleName(ScmIdleItems2.class)+"."+ScmIdleItems2.FN_USEORGUNITNO, 
                                    QueryParam.QUERY_EQ,scmIdleItemsAdvQuery.getUseOrgUnitNo()));
				}
				if (scmIdleItemsAdvQuery.getItemClassId()>0) {
					StringBuffer ids = new StringBuffer("");
					List<ScmMaterialGroup> scmMaterialGroupList = scmMaterialGroupBiz.findChild(scmIdleItemsAdvQuery.getItemClassId(), param);
					if(scmMaterialGroupList!=null && !scmMaterialGroupList.isEmpty()) {
						for(ScmMaterialGroup scmMaterialGroup:scmMaterialGroupList) {
							if(StringUtils.isNotBlank(ids.toString()))
								ids.append(",");
							ids.append(scmMaterialGroup.getId());
						}
					}else {
						ids.append(scmIdleItemsAdvQuery.getItemClassId());
					}
					page.getParam().put(ScmIdleItemsAdvQuery.FN_ITEMCLASSID,
                            new QueryParam(ClassUtils.getFinalModelSimpleName(ScmMaterialGroup.class)+"."+ScmMaterialGroup.FN_ID, 
                                    QueryParam.QUERY_IN,ids.toString()));
				}
				if (StringUtils.isNotBlank(scmIdleItemsAdvQuery.getIdleStatus())) {
					page.getParam().put(ScmIdleItems2.FN_IDLESTATUS,
							new QueryParam(ClassUtils.getFinalModelSimpleName(ScmIdleItems2.class) + "."
									+ ScmIdleItems2.FN_IDLESTATUS, QueryParam.QUERY_EQ, StringUtils.equals("Y", scmIdleItemsAdvQuery.getIdleStatus())?"1":"0"));
				} 
			
				if (StringUtils.isNotBlank(scmIdleItemsAdvQuery.getNewIdleState())) {
					page.getParam().put(ScmIdleItems2.FN_NEWIDLE, new QueryParam(
							ClassUtils.getFinalModelSimpleName(ScmIdleItems2.class) + "." + ScmIdleItems2.FN_NEWIDLE,
							QueryParam.QUERY_EQ, StringUtils.equals("Y", scmIdleItemsAdvQuery.getNewIdleState())?"1":"0"));
				}
			}
		}
	}
	
	protected void afterFindPage(List list, Page page, Param param)throws AppException {
		List<ScmIdleItems2> scmPurRequireList = list;
		if (list != null && !list.isEmpty()) {
			for (ScmIdleItems2 scmIdleItems2 : scmPurRequireList) {
				setConvertMap(scmIdleItems2,param);
			}
		}
	}
	
	protected void afterSelect(ScmIdleItems2 entity, Param param) throws AppException {
		if (entity != null){
			setConvertMap(entity,param);
		}
	}
	
	protected void beforeFindPage(Page page, Param param)throws AppException {
		String idleItemsQueryScope = sysParamBiz.getValue(param.getControlUnitNo(), "SCM_IdleItemsQueryScope", "S",param);
		if (!StringUtils.equals("S", idleItemsQueryScope)) {
			List<OrgCostCenter2> orgCostCenterList = orgCostCenterBiz.findChild(param.getOrgUnitNo(), param);
			if (orgCostCenterList != null && !orgCostCenterList.isEmpty()) {
				StringBuffer orgBuffer = new StringBuffer();
				for (OrgCostCenter2 orgCostCenter2 : orgCostCenterList) {
					if (StringUtils.isNotBlank(orgBuffer.toString())) {
						orgBuffer.append(",");
					}
					orgBuffer.append("'").append(orgCostCenter2.getOrgUnitNo()).append("'");
				}
				page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmIdleItems2.class) + "." + ScmIdleItems2.FN_ORGUNITNO),
	                    new QueryParam(ClassUtils.getFinalModelSimpleName(ScmIdleItems2.class)+"."+ScmIdleItems2.FN_ORGUNITNO, 
	                            QueryParam.QUERY_IN,orgBuffer.toString()));
			}else {
				page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmIdleItems2.class) + "." + ScmIdleItems2.FN_ORGUNITNO),
	                    new QueryParam(ClassUtils.getFinalModelSimpleName(ScmIdleItems2.class)+"."+ScmIdleItems2.FN_ORGUNITNO, 
	                            QueryParam.QUERY_EQ,"0"));
			}
		}else {
			page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmIdleItems2.class) + "." + ScmIdleItems2.FN_CONTROLUNITNO),
                    new QueryParam(ClassUtils.getFinalModelSimpleName(ScmIdleItems2.class)+"."+ScmIdleItems2.FN_CONTROLUNITNO, 
                            QueryParam.QUERY_EQ,param.getControlUnitNo()));
		}
	}

	@Override
	public List<ScmIdleItems2> selectByIdleCauseId(long id, Param param) throws AppException {
		if (id>0) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("idleCauseId", id);
			map.put("controlUnitNo", param.getControlUnitNo());
			return ((ScmIdleItemsDAO) dao).selectByIdleCauseId(map);
		}
		return null;
	}

	@Override
	public int deleteZeroQty(String orgUnitNo, Param param) throws AppException {
		Page page = new Page();
		page.setModelClass(OrgCostCenter2.class);
		page.setShowCount(Integer.MAX_VALUE);
		List<String> argList = new ArrayList<String>();
		argList.add("type=from");
		argList.add("relationType=" + OrgUnitRelationType.COSTTOFIN);
		argList.add("toOrgUnitNo=" + orgUnitNo);
		List<OrgCostCenter2> orgCostCenterList = orgCostCenterBiz.queryPage(page, argList, "queryPageEx", param);
		StringBuffer costOrgUnitNos = new StringBuffer("");
		if (orgCostCenterList != null && !orgCostCenterList.isEmpty()) {
			for (OrgCostCenter2 orgCostCenter : orgCostCenterList) {
				if (StringUtils.isNotBlank(costOrgUnitNos.toString()))
					costOrgUnitNos.append(",");
				costOrgUnitNos.append("'").append(orgCostCenter.getOrgUnitNo()).append("'");
			}
		}
		if (StringUtils.isBlank(costOrgUnitNos.toString())) {
			costOrgUnitNos = costOrgUnitNos.append("'0'");
		}
		HashMap<String, Object> map = new HashMap<>();
		map.put("orgUnitNo", orgUnitNo);
		map.put("costOrgUnitNos", costOrgUnitNos);
		return ((ScmIdleItemsDAO) dao).deleteZeroQty(map);
	}
	
}
