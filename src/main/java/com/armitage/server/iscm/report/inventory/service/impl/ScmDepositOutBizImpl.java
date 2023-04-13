package com.armitage.server.iscm.report.inventory.service.impl;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.util.AppContextUtil;
import com.armitage.server.common.util.DAOHelper;
import com.armitage.server.iscm.basedata.model.ScmMaterial2;
import com.armitage.server.iscm.basedata.model.ScmMeasureUnit;
import com.armitage.server.iscm.basedata.model.Scmsupplier2;
import com.armitage.server.iscm.basedata.service.ScmMaterialBiz;
import com.armitage.server.iscm.basedata.service.ScmMeasureUnitBiz;
import com.armitage.server.iscm.basedata.service.ScmsupplierBiz;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvWareHouse;
import com.armitage.server.iscm.inventorymanage.inventorysetting.service.ScmInvWareHouseBiz;
import com.armitage.server.iscm.report.inventory.dao.ISCMInventoryDAO;
import com.armitage.server.iscm.report.inventory.model.ISCMInventory;
import com.armitage.server.system.model.OrgBaseUnit2;
import com.armitage.server.system.model.Report2;
import com.armitage.server.system.service.BaseReportDataBiz;
import com.armitage.server.system.service.OrgBaseUnitBiz;
import com.armitage.server.system.service.impl.BaseReportDataBizImpl;
import com.armitage.server.user.model.Usr;
import com.armitage.server.user.service.UsrBiz;

public class ScmDepositOutBizImpl extends BaseReportDataBizImpl implements BaseReportDataBiz{
	
    private ScmMaterialBiz scmMaterialBiz = (ScmMaterialBiz) AppContextUtil.getBean("scmMaterialBiz");
    private ScmInvWareHouseBiz scmInvWareHouseBiz = (ScmInvWareHouseBiz) AppContextUtil.getBean("scmInvWareHouseBiz");
    private UsrBiz usrBiz = (UsrBiz) AppContextUtil.getBean("usrBiz");
    private ScmMeasureUnitBiz scmMeasureUnitBiz = (ScmMeasureUnitBiz) AppContextUtil.getBean("scmMeasureUnitBiz");
    private OrgBaseUnitBiz orgBaseUnitBiz = (OrgBaseUnitBiz) AppContextUtil.getBean("orgBaseUnitBiz");
    
    protected void beforeGetReportData(Report2 report,
			LinkedHashMap<String, Object> paramValueMap, Param param) throws AppException {
		
	}
	public List getReportDataList(Report2 report,
			LinkedHashMap<String, Object> paramValueMap, Param param)
			throws AppException {
		if (report != null)  {
			try {
			    ISCMInventoryDAO dao = (ISCMInventoryDAO) DAOHelper.getDAO(ISCMInventory.class);
				List list =  dao.exec(report.getExecSql(), paramValueMap); 
				return list;
			} catch (Exception e) {
				throw new AppException(
						"system.service.imp.ReportBizImpl.exec.error", e);
			}
		}
		
		return null;
	}
	@Override
	protected void afterGetReportData(Report2 report,
			LinkedHashMap<String, Object> paramValueMap, List list, Param param)
			throws AppException {
		ScmMaterial2 scmMaterial = null;
	    ScmInvWareHouse scmInvWareHouse = null;
	    OrgBaseUnit2 orgBaseUnit = null;
	    Usr creator = null;
        Usr checker = null;
        ScmMeasureUnit scmMeasureUnit = null;
	    if (list != null && list.size() > 0) {
            for(int i=0;i<list.size();i++){
	        	Object obj = list.get(i);
		        HashMap<String, Object> keyMap=(HashMap<String, Object>) obj;
	            if (keyMap.get("creator") != null && StringUtils.isNotBlank((String)keyMap.get("creator"))) {
	            	creator = usrBiz.selectByCode((String)keyMap.get("creator"), param);
	            	if(creator!=null)
	            		((HashMap) obj).put("creator", creator.getName());
	            }
	            if (keyMap.get("checker") != null && StringUtils.isNotBlank((String)keyMap.get("checker"))) {
		            checker = usrBiz.selectByCode((String)keyMap.get("checker"), param);
		            if (checker != null)
		                ((HashMap) obj).put("checker", checker.getName());
	            }
		        if(keyMap.get("wareHouseId") != null && (Long)keyMap.get("wareHouseId")>0){
		        	scmInvWareHouse = scmInvWareHouseBiz.selectDirect((Long)keyMap.get("wareHouseId"), param);
		            if (scmInvWareHouse != null) {
		                ((HashMap) obj).put("wareHouseName", scmInvWareHouse.getWhName());
		            }
		        }
		        if(keyMap.get("itemId")!=null && (Long)keyMap.get("itemId")>0) {
		        	scmMaterial = scmMaterialBiz.selectDirect((Long)keyMap.get("itemId"), param);
		        	if(scmMaterial!=null) {
		        		((HashMap) obj).put("itemNo", scmMaterial.getItemNo());
		        		if(StringUtils.isBlank(scmMaterial.getSpec())) {
		        			((HashMap) obj).put("itemNameAndSpec", scmMaterial.getItemName());	
		        		}else {
		        			((HashMap) obj).put("itemNameAndSpec", scmMaterial.getItemName()+"("+scmMaterial.getSpec()+")");	
		        		}
		        	}
		        }
		        if(keyMap.get("unit") != null && (Long)keyMap.get("unit")>0){
		        	scmMeasureUnit = scmMeasureUnitBiz.selectDirect((Long)keyMap.get("unit"), param);
		            if (scmMeasureUnit != null)
		                ((HashMap) obj).put("unitName", scmMeasureUnit.getUnitName());
		        }
		        if (keyMap.get("reqFinOrgUnitNo") != null && StringUtils.isNotBlank((String)keyMap.get("reqFinOrgUnitNo"))) {
		        	orgBaseUnit = orgBaseUnitBiz.selectbyOrgNo((String)keyMap.get("reqFinOrgUnitNo"), param);
		            if (orgBaseUnit != null)
		                ((HashMap) obj).put("reqFinOrgUnitName", orgBaseUnit.getOrgUnitName());
	            }
			}
        }
	}

}
