
package com.armitage.server.iscm.purchasemanage.pricemanage.service.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvOtherIssueBillEntry2;
import com.armitage.server.iscm.purchasemanage.pricemanage.dao.ScmPurPriceAssignDAO;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurPrice2;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurPriceAssign2;
import com.armitage.server.iscm.purchasemanage.pricemanage.service.ScmPurPriceAssignBiz;
import com.armitage.server.system.model.OrgBaseUnit;
import com.armitage.server.system.model.OrgCompany2;
import com.armitage.server.system.service.OrgCompanyBiz;
import org.springframework.stereotype.Service;

@Service("scmPurPriceAssignBiz")
public class ScmPurPriceAssignBizImpl extends BaseBizImpl<ScmPurPriceAssign2> implements ScmPurPriceAssignBiz {
	
	private OrgCompanyBiz orgCompanyBiz;
	
	public void setOrgCompanyBiz(OrgCompanyBiz orgCompanyBiz) {
		this.orgCompanyBiz = orgCompanyBiz;
	}
	
	@Override
	public List<ScmPurPriceAssign2> selectByPmId(long pmId, Param param)
			throws AppException {
		if(pmId > 0){
			HashMap<String, Object> map = new HashMap<>();
			map.put("pmId", pmId);
			List<ScmPurPriceAssign2> scmPurPriceAssignList = ((ScmPurPriceAssignDAO) dao).selectByPmId(map);
			if(scmPurPriceAssignList!=null && !scmPurPriceAssignList.isEmpty()){
				for(ScmPurPriceAssign2 scmPurPriceAssign:scmPurPriceAssignList){
					setConverMap(scmPurPriceAssign,param);
				}
			}
			return scmPurPriceAssignList;
		}
		return null;
	}
	
	private void setConverMap(ScmPurPriceAssign2 entity, Param param) throws AppException {
		if(entity != null && entity.getPmId() > 0){
			if(StringUtils.isNotBlank(entity.getFinOrgUnitNo())){
				//财务组织
				OrgCompany2 orgCompany = orgCompanyBiz.selectByOrgUnitNo(entity.getFinOrgUnitNo(), param);
	            if (orgCompany != null) {
	            	entity.setFinOrgUnitName(orgCompany.getOrgUnitName());
					entity.setIsBizUnit(orgCompany.isIsBizUnit());
	            }
			}
		}
	}
	
}
