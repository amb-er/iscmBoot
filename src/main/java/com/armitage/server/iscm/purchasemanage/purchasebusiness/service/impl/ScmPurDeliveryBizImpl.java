package com.armitage.server.iscm.purchasemanage.purchasebusiness.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Page;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.base.model.QueryParam;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.util.ClassUtils;
import com.armitage.server.common.util.FormatUtils;
import com.armitage.server.iscm.basedata.model.ScmMaterialGroup;
import com.armitage.server.iscm.basedata.model.ScmMeasureUnit;
import com.armitage.server.iscm.basedata.model.Scmsuppliergroup;
import com.armitage.server.iscm.basedata.service.ScmMaterialGroupBiz;
import com.armitage.server.iscm.basedata.service.ScmMeasureUnitBiz;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurDelivery;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurDeliveryAdvQuery;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurOrder;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurOrderEntry;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.service.ScmPurDeliveryBiz;
import com.armitage.server.system.model.OrgBaseUnit2;
import com.armitage.server.system.service.OrgBaseUnitBiz;
import com.armitage.server.system.service.SysParamBiz;
import org.springframework.stereotype.Service;

@Service("scmPurDeliveryBiz")
public class ScmPurDeliveryBizImpl extends BaseBizImpl<ScmPurDelivery> implements ScmPurDeliveryBiz {
	private OrgBaseUnitBiz orgBaseUnitBiz;
	private ScmMeasureUnitBiz scmMeasureUnitBiz;
	private ScmMaterialGroupBiz scmMaterialGroupBiz;
	private SysParamBiz sysParamBiz;
	
	public void setOrgBaseUnitBiz(OrgBaseUnitBiz orgBaseUnitBiz) {
		this.orgBaseUnitBiz = orgBaseUnitBiz;
	}

	public void setScmMeasureUnitBiz(ScmMeasureUnitBiz scmMeasureUnitBiz) {
		this.scmMeasureUnitBiz = scmMeasureUnitBiz;
	}

	public void setScmMaterialGroupBiz(ScmMaterialGroupBiz scmMaterialGroupBiz) {
		this.scmMaterialGroupBiz = scmMaterialGroupBiz;
	}

	public void setSysParamBiz(SysParamBiz sysParamBiz) {
		this.sysParamBiz = sysParamBiz;
	}

	@Override
	protected HashMap<String, Object> addFindAllPageParam(Page page,HashMap<String, Object> map, Param param) {
		String sortType = sysParamBiz.getValue(param.getOrgUnitNo(), "SCM_DeliverySort", "V", param);
		map.put("sortType",sortType);
		map.put("orgUnitNo", param.getOrgUnitNo());
		return map;
	}

	@Override
	public void doAdvQuery(Page page, Param param) {
		if (page.getModel() != null) {
			if (page.getModel() instanceof ScmPurDeliveryAdvQuery) {
				ScmPurDeliveryAdvQuery scmPurDeliveryAdvQuery = (ScmPurDeliveryAdvQuery) page.getModel();
				if(scmPurDeliveryAdvQuery.getBegDate()!=null && scmPurDeliveryAdvQuery.getEndDate()!=null){
					page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmPurOrderEntry.class)+"."+ScmPurOrderEntry.FN_REQDATE,
							new QueryParam(ClassUtils.getFinalModelSimpleName(ScmPurOrderEntry.class)+"."+ScmPurOrderEntry.FN_REQDATE, QueryParam.QUERY_BETWEEN,FormatUtils.fmtDate(scmPurDeliveryAdvQuery.getBegDate()),FormatUtils.fmtDate(scmPurDeliveryAdvQuery.getEndDate())));
				}
				if(scmPurDeliveryAdvQuery.getVendorClassId()>0){
					page.getParam().put(ClassUtils.getFinalModelSimpleName(Scmsuppliergroup.class)+"."+Scmsuppliergroup.FN_ID,
							new QueryParam(ClassUtils.getFinalModelSimpleName(Scmsuppliergroup.class)+"."+Scmsuppliergroup.FN_ID, QueryParam.QUERY_EQ,String.valueOf(scmPurDeliveryAdvQuery.getVendorClassId())));
				}
				if(scmPurDeliveryAdvQuery.getVendorId()>0) {
					page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmPurOrder.class)+"."+ScmPurOrder.FN_VENDORID,
							new QueryParam(ClassUtils.getFinalModelSimpleName(ScmPurOrder.class)+"."+ScmPurOrder.FN_VENDORID, QueryParam.QUERY_EQ,String.valueOf(scmPurDeliveryAdvQuery.getVendorId())));
				}
				if(StringUtils.isNotBlank(scmPurDeliveryAdvQuery.getReqFinOrgUnitNo())){
					page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmPurOrderEntry.class)+"."+ScmPurOrderEntry.FN_REQFINORGUNITNO,
							new QueryParam(ClassUtils.getFinalModelSimpleName(ScmPurOrderEntry.class)+"."+ScmPurOrderEntry.FN_REQFINORGUNITNO, QueryParam.QUERY_EQ,scmPurDeliveryAdvQuery.getReqFinOrgUnitNo()));
				}
				if(scmPurDeliveryAdvQuery.getItemClassId()>0){
					StringBuffer ids = new StringBuffer("");
					List<ScmMaterialGroup> scmMaterialGroupList = scmMaterialGroupBiz.findChild(scmPurDeliveryAdvQuery.getItemClassId(), param);
					if(scmMaterialGroupList!=null && !scmMaterialGroupList.isEmpty()) {
						for(ScmMaterialGroup scmMaterialGroup:scmMaterialGroupList) {
							if(StringUtils.isNotBlank(ids.toString()))
								ids.append(",");
							ids.append(scmMaterialGroup.getId());
						}
					}else {
						ids.append(scmPurDeliveryAdvQuery.getItemClassId());
					}
					page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmMaterialGroup.class)+"."+ScmMaterialGroup.FN_ID,
							new QueryParam(ClassUtils.getFinalModelSimpleName(ScmMaterialGroup.class)+"."+ScmMaterialGroup.FN_ID, 	QueryParam.QUERY_IN,ids.toString()));
				}
				if(StringUtils.isNotBlank(scmPurDeliveryAdvQuery.getReqOrgUnitNo())){
					page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmPurOrderEntry.class)+"."+ScmPurOrderEntry.FN_REQORGUNITNO,
							new QueryParam(ClassUtils.getFinalModelSimpleName(ScmPurOrderEntry.class)+"."+ScmPurOrderEntry.FN_REQORGUNITNO, QueryParam.QUERY_EQ,scmPurDeliveryAdvQuery.getReqOrgUnitNo()));
				}
			}
		}
	}

	@Override
	protected void afterFindPage(List list, Page page, Param param) throws AppException {
		if(list!=null && !list.isEmpty()) {
			Date begReqDate = null;
			Date endReqDate = null;
			for(ScmPurDelivery scmPurDelivery:(List<ScmPurDelivery>)list) {
				if(begReqDate==null || begReqDate.compareTo(scmPurDelivery.getReqDate())>0) {
					begReqDate = scmPurDelivery.getReqDate();
				}
				if(endReqDate==null || endReqDate.compareTo(scmPurDelivery.getReqDate())<0) {
					endReqDate = scmPurDelivery.getReqDate();
				}
				if(StringUtils.isNotBlank(scmPurDelivery.getReqOrgUnitNo())) {
					OrgBaseUnit2 orgBaseUnit = orgBaseUnitBiz.selectbyOrgNo(scmPurDelivery.getReqOrgUnitNo(), param);
					if(orgBaseUnit!=null)
						scmPurDelivery.setReqOrgUnitName(orgBaseUnit.getOrgUnitName());
				}
				if(StringUtils.isNotBlank(scmPurDelivery.getReqFinOrgUnitNo())) {
					OrgBaseUnit2 orgBaseUnit = orgBaseUnitBiz.selectbyOrgNo(scmPurDelivery.getReqFinOrgUnitNo(), param);
					if(orgBaseUnit!=null)
						scmPurDelivery.setReqFinOrgUnitNo(orgBaseUnit.getOrgUnitName());
				}
				if(scmPurDelivery.getPurUnit()>0) {
					ScmMeasureUnit scmMeasureUnit = scmMeasureUnitBiz.selectDirect(scmPurDelivery.getPurUnit(), param);
					if(scmMeasureUnit!=null)
						scmPurDelivery.setUnitName(scmMeasureUnit.getUnitName());
				}
			}
			for(ScmPurDelivery scmPurDelivery:(List<ScmPurDelivery>)list) {
				scmPurDelivery.setBegReqDate(FormatUtils.fmtDate(begReqDate));
				scmPurDelivery.setEndReqDate(FormatUtils.fmtDate(endReqDate));
			}
		}
	}
	
}

