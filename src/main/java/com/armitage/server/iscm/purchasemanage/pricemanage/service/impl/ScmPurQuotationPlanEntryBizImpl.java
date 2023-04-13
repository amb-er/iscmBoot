package com.armitage.server.iscm.purchasemanage.pricemanage.service.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.util.CalendarUtil;
import com.armitage.server.common.util.FormatUtils;
import com.armitage.server.iscm.basedata.model.ScmMaterialGroup;
import com.armitage.server.iscm.basedata.service.ScmMaterialGroupBiz;
import com.armitage.server.iscm.purchasemanage.pricemanage.dao.ScmPurQuotationPlanEntryDAO;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurQuotationPlanAdvQuery;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurQuotationPlanEntry2;
import com.armitage.server.iscm.purchasemanage.pricemanage.service.ScmPurQuotationPlanEntryBiz;
import org.springframework.stereotype.Service;

@Service("scmPurQuotationPlanEntryBiz")
public class ScmPurQuotationPlanEntryBizImpl extends BaseBizImpl<ScmPurQuotationPlanEntry2> implements ScmPurQuotationPlanEntryBiz {

	private ScmMaterialGroupBiz scmMaterialGroupBiz;
	
	public void setScmMaterialGroupBiz(ScmMaterialGroupBiz scmMaterialGroupBiz) {
		this.scmMaterialGroupBiz = scmMaterialGroupBiz;
	}

	@Override
	public List<ScmPurQuotationPlanEntry2> selectByPlanId(long planId, Param param) throws AppException {
		if(planId > 0){
			HashMap<String, Object> map = new HashMap<>();
			map.put("planId", planId);
			return ((ScmPurQuotationPlanEntryDAO) dao).selectByPlanId(map);
		}
		return null;
	}

	@Override
	public List<ScmPurQuotationPlanEntry2> selectQurChasIngQuery(ScmPurQuotationPlanAdvQuery scmPurQuotationPlanAdvQuery,
			Param param) throws AppException {
		if (scmPurQuotationPlanAdvQuery != null) {
			HashMap<String, Object> map = new HashMap<>();
			StringBuffer sbMaterilaClass = new StringBuffer("");
			if (StringUtils.isNotBlank(scmPurQuotationPlanAdvQuery.getPurChasIngQuery())) {
				String[] materialClassNameList = StringUtils.split(scmPurQuotationPlanAdvQuery.getPurChasIngQuery(), ",");
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
			map.put("groupIds", sbMaterilaClass.toString());
			map.put("sort", "Y".equals(scmPurQuotationPlanAdvQuery.getSort())?"amt":"qty");
			map.put("qtyRecord", Integer.parseInt(scmPurQuotationPlanAdvQuery.getQtyRecord()));
			map.put("purOrgUnitNo", scmPurQuotationPlanAdvQuery.getPurOrgUnitNo());
			map.put("beginDateTo", FormatUtils.fmtDateTime(scmPurQuotationPlanAdvQuery.getBeginDateTo()));
			map.put("endDateTo", FormatUtils.fmtDateTime(scmPurQuotationPlanAdvQuery.getEndDateTo()));
			List<ScmPurQuotationPlanEntry2> scmPurQuotationPlanEntryList = ((ScmPurQuotationPlanEntryDAO) dao)
					.selectPurChasIngQuery(map);
			if (scmPurQuotationPlanEntryList != null && !scmPurQuotationPlanEntryList.isEmpty()) {
				return scmPurQuotationPlanEntryList;
			}
		}
		return null;
	}

}
