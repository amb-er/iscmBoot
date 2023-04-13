package com.armitage.server.iscm.inventorymanage.util;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.util.AppContextUtil;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvWareHouseUsr2;
import com.armitage.server.iscm.inventorymanage.inventorysetting.service.ScmInvWareHouseUsrBiz;
import com.armitage.server.system.model.OrgCompany2;
import com.armitage.server.system.service.OrgUnitRelationBiz;
import com.armitage.server.system.service.SysParamBiz;
import com.armitage.server.system.util.OrgUnitRelationType;

public class ScmWarehouseUtil {
	private static ScmInvWareHouseUsrBiz scmInvWareHouseUsrBiz = (ScmInvWareHouseUsrBiz) AppContextUtil.getBean("scmInvWareHouseUsrBiz");
	private static SysParamBiz sysParamBiz = (SysParamBiz) AppContextUtil.getBean("sysParamBiz");
	private static OrgUnitRelationBiz orgUnitRelationBiz = (OrgUnitRelationBiz) AppContextUtil.getBean("orgUnitRelationBiz");
	
	/**
	 * 获取当前用户
	 * @param finOrg
	 * @param param
	 * @return
	 */
	public static String filterWarehouseByUsr(String finOrg, Param param) {
		if (StringUtils.isEmpty(finOrg)) {
			List<OrgCompany2> finOrgList = orgUnitRelationBiz.findToOrgUnitByType(OrgUnitRelationType.INVTOFIN, param.getOrgUnitNo(), false, null, param);
			if (finOrgList == null || finOrgList.isEmpty()) 
				return null;
			finOrg = finOrgList.get(0).getOrgUnitNo();
		}
		String value = sysParamBiz.getValue(finOrg, "SCM_WareHouseAuth", "N", param);
		if (StringUtils.equals("Y", value)) {
			List<ScmInvWareHouseUsr2> scmInvWareHouseUsr2List= scmInvWareHouseUsrBiz.selectByUsrCode(param.getUsrCode(),param);
			if (scmInvWareHouseUsr2List != null && !scmInvWareHouseUsr2List.isEmpty()) {
				StringBuffer stringBuffer = new StringBuffer();
				for (ScmInvWareHouseUsr2 scmInvWareHouseUsr2 : scmInvWareHouseUsr2List) {
					if (StringUtils.isNotBlank(stringBuffer.toString())) {
						stringBuffer.append(",");
					}
					stringBuffer.append(scmInvWareHouseUsr2.getWareHouseId());
				}
				return stringBuffer.toString();
			}
		}
		return null;
	}

}
