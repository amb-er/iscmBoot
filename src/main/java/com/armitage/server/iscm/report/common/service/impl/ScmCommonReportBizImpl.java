package com.armitage.server.iscm.report.common.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.util.AppContextUtil;
import com.armitage.server.common.util.DesUtils;
import com.armitage.server.common.util.FormatUtils;
import com.armitage.server.common.util.JSONUtils;
import com.armitage.server.iars.basedata.model.PeriodCalendar;
import com.armitage.server.iars.basedata.service.PeriodCalendarBiz;
import com.armitage.server.iscm.basedata.model.ScmMaterialGroup;
import com.armitage.server.iscm.basedata.service.ScmMaterialGroupBiz;
import com.armitage.server.iscm.basedata.service.ScmMeasureUnitBiz;
import com.armitage.server.iscm.report.common.dao.ScmCommonReportDAO;
import com.armitage.server.iscm.report.common.service.ScmCommonReportBiz;
import com.armitage.server.system.model.Code;
import com.armitage.server.system.model.OrgCostCenter2;
import com.armitage.server.system.model.OrgStorage2;
import com.armitage.server.system.service.CodeBiz;
import com.armitage.server.system.service.OrgCostCenterBiz;
import com.armitage.server.system.service.OrgStorageBiz;
import com.armitage.server.webservice.model.ReportRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Service;

@Service("scmCommonReportBiz")
public class ScmCommonReportBizImpl extends BaseBizImpl<BaseModel> implements ScmCommonReportBiz {
	private ScmMaterialGroupBiz scmMaterialGroupBiz;
	private ScmMeasureUnitBiz scmMeasureUnitBiz;
	private CodeBiz codeBiz;
	private HashMap<String, Object> beanMap = new HashMap<String, Object>();
	private HashMap<String, String> beanDataMap = new HashMap<String, String>();
	

	public void setCodeBiz(CodeBiz codeBiz) {
		this.codeBiz = codeBiz;
	}

	public void setScmMeasureUnitBiz(ScmMeasureUnitBiz scmMeasureUnitBiz) {
		this.scmMeasureUnitBiz = scmMeasureUnitBiz;
	}

	public void setScmMaterialGroupBiz(ScmMaterialGroupBiz scmMaterialGroupBiz) {
		this.scmMaterialGroupBiz = scmMaterialGroupBiz;
	}

	@Override
	public List queryCommonReportData(HttpServletRequest request) throws AppException {
    	List list = null;
		String paramValueMap=request.getParameter("paramValueMap");
		String xmlId = request.getParameter("xmlId");
		String orgUnitNo=request.getParameter("orgUnitNo");
		String controlUnitNo=request.getParameter("controlUnitNo");
		if(StringUtils.isNotBlank(paramValueMap) && StringUtils.isNotBlank(xmlId)) {
			try {
				Param param = new Param();
				param.setOrgUnitNo(orgUnitNo);
				param.setControlUnitNo(controlUnitNo);
				DesUtils des = new DesUtils("GyYo!N9T");// 自定义密钥
				paramValueMap = des.decrypt(paramValueMap);
				Gson gson = JSONUtils.newGson();
				ReportRequest reportRequest = gson.fromJson(paramValueMap, new TypeToken<ReportRequest>(){}.getType());
				LinkedHashMap<String, Object> map = reportRequest.getParamValueMap();
				map.put("orgUnitNo", orgUnitNo);
				map.put("controlUnitNo", controlUnitNo);
				
				
				StringBuffer sbMaterilaClass = new StringBuffer("");
				if (map.containsKey("materialClassName")&& StringUtils.isNotBlank((String) map.get("materialClassName"))) {
					String[] materialClassNameList = StringUtils.split((String) map.get("materialClassName"), ",");
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
					map.put("materialClassName", sbMaterilaClass.toString());
				}
//				if(map.containsKey("taxRate")&& StringUtils.isNotBlank((String) map.get("taxRate"))) {
//					PubSysBasicInfo selectByValue = pubSysBasicInfoBiz.selectByValue("TaxRate", (String) map.get("taxRate"), param);
//					map.put("taxRate", selectByValue.getFInfoNo());
//				}
				beforeQueryReportData(map,xmlId,param);
				list = ((ScmCommonReportDAO) dao).queryCommonReportData(map,xmlId);
				list = convertDisplay(list,param);
				afterQueryReportData(list,xmlId,param);
			} catch (Exception ex) {
				ex.printStackTrace(); 
            }
		}
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
    
    protected String valueToDisplay(String beanId, String key, Param param) throws AppException {
		try {
			if(beanDataMap.containsKey(beanId+"_"+key)) {
				return beanDataMap.get(beanId+"_"+key);
			}
			BaseBiz biz =(BaseBiz) beanMap.get(beanId);
			if(biz==null) {
				biz = (BaseBiz) AppContextUtil.getBean(beanId);
				beanMap.put(beanId, biz);
				
			}
			if (biz != null) {
				String rnt = biz.getDisplayByKey(key, param);
				beanDataMap.put(beanId+"_"+key, rnt);
				return rnt;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return key;
	}
    
    private void beforeQueryReportData(LinkedHashMap<String, Object> map,String xmlId,Param param) {
    	switch (StringUtils.lowerCase(xmlId)) {
    		case "scminvsalepricedetail":
    			String invOrgUnitNo2 = (String) map.get("invOrgUnitNo");
        		if(StringUtils.isNotBlank(invOrgUnitNo2)) {
        			OrgStorageBiz orgStorageBiz = (OrgStorageBiz)AppContextUtil.getBean("orgStorageBiz");
        			List<OrgStorage2> orgStorageList = orgStorageBiz.findChild(invOrgUnitNo2, param);
        			if(orgStorageList!=null && !orgStorageList.isEmpty()) {
        				StringBuffer orgStringBuffer = new StringBuffer("");
        				for(OrgStorage2 orgStorage:orgStorageList) {
        					if(StringUtils.isNotBlank(orgStringBuffer.toString()))
        						orgStringBuffer.append(",");
        					orgStringBuffer.append("'").append(orgStorage.getOrgUnitNo()).append("'");
        				}
        				if(StringUtils.isNotBlank(orgStringBuffer.toString()))
        					map.put("invOrgUnitNo", orgStringBuffer.toString());
        			}
        		}
    	    	break;
	    	case "scmsupsupplyofmaterialsummary": 
	    	case "scmsupsupplyofmaterialdetails": 
    		String invOrgUnitNo = (String) map.get("invOrgUnitNo");
    		if(StringUtils.isNotBlank(invOrgUnitNo)) {
    			OrgStorageBiz orgStorageBiz = (OrgStorageBiz)AppContextUtil.getBean("orgStorageBiz");
    			List<OrgStorage2> orgStorageList = orgStorageBiz.findChild(invOrgUnitNo, param);
    			if(orgStorageList!=null && !orgStorageList.isEmpty()) {
    				StringBuffer orgStringBuffer = new StringBuffer("");
    				for(OrgStorage2 orgStorage:orgStorageList) {
    					if(StringUtils.isNotBlank(orgStringBuffer.toString()))
    						orgStringBuffer.append(",");
    					orgStringBuffer.append("'").append(orgStorage.getOrgUnitNo()).append("'");
    				}
    				if(StringUtils.isNotBlank(orgStringBuffer.toString()))
    					map.put("invOrgUnitNo", orgStringBuffer.toString());
    			}
    		}
	    	break;
	    	case "selectscminvpriceanalysis":
			String materialClassName = (String) map.get("materialClassName");
			if (StringUtils.isNotBlank(materialClassName)) {
				StringBuffer sbMaterilaClass = new StringBuffer("");
				ScmMaterialGroupBiz scmMaterialGroupBiz = (ScmMaterialGroupBiz) AppContextUtil.getBean("scmMaterialGroupBiz");
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
				if(StringUtils.isNotBlank(sbMaterilaClass.toString()))
					map.put("materialClassName", sbMaterilaClass.toString());
			}
			break;
	    	case "scmdeliveryterm":
			String materialClassName2 = (String) map.get("materialClassName");
			if (StringUtils.isNotBlank(materialClassName2)) {
				StringBuffer sbMaterilaClass = new StringBuffer("");
				ScmMaterialGroupBiz scmMaterialGroupBiz = (ScmMaterialGroupBiz) AppContextUtil
						.getBean("scmMaterialGroupBiz");
				String[] materialClassNameList = StringUtils.split(materialClassName2, ",");
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
				if (StringUtils.isNotBlank(sbMaterilaClass.toString()))
					map.put("materialClassName", sbMaterilaClass.toString());
			}
			break;
	    	case "scminvcostconsumedetail":
	    	String periodId = (String) map.get("periodId");
	    	if (StringUtils.isNotBlank(periodId) && StringUtils.isNumeric(periodId)) {
	    		PeriodCalendarBiz periodCalendarBiz = (PeriodCalendarBiz) AppContextUtil
						.getBean("periodCalendarBiz");
				PeriodCalendar periodCalendar = periodCalendarBiz.select(Long.parseLong(periodId), param);
				if(periodCalendar != null){
					String accountDate = FormatUtils.fmtDate(periodCalendar.getStartDate());
					map.put("accountDate", accountDate);
				}
			}
	    	String statea = (String) map.get("state");
	    	if (StringUtils.isNotBlank(statea)) {
				map.put("state", statea);
			}
	    	String materialClassName3 = (String) map.get("materialClassName");
			if (StringUtils.isNotBlank(materialClassName3)) {
				StringBuffer sbMaterilaClass = new StringBuffer("");
				ScmMaterialGroupBiz scmMaterialGroupBiz = (ScmMaterialGroupBiz) AppContextUtil
						.getBean("scmMaterialGroupBiz");
				String[] materialClassNameList = StringUtils.split(materialClassName3, ",");
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
				if (StringUtils.isNotBlank(sbMaterilaClass.toString()))
					map.put("materialClassName", sbMaterilaClass.toString());
			}
			String costOrgUnitNo2 = (String) map.get("costOrgUnitNo");
			if(StringUtils.isNotBlank(costOrgUnitNo2)) {
				StringBuffer orgUnitNos=new StringBuffer("");
				OrgCostCenterBiz orgCostCenterBiz = (OrgCostCenterBiz) AppContextUtil
						.getBean("orgCostCenterBiz");
	            String[] orgUnitNoList = StringUtils.split(costOrgUnitNo2, ",");
	            for(String org:orgUnitNoList) {
	            	String orgUnitNo;
	                if(StringUtils.isBlank(org)) { 
	                	continue;
	                	}else {
	                		orgUnitNo = org.replace("'", "");
						}
	                List<OrgCostCenter2> orgCostCenterList = orgCostCenterBiz.findChild(orgUnitNo, param);
	        		if(orgCostCenterList!=null && !orgCostCenterList.isEmpty()) {
	        			for(OrgCostCenter2 orgCostCenter:orgCostCenterList) {
	        				if(StringUtils.isNotBlank(orgUnitNos.toString()))
	        					orgUnitNos.append(",");
	        				orgUnitNos.append("'").append(orgCostCenter.getOrgUnitNo()).append("'");
	        			}
	        		}
	            }
	            if (StringUtils.isNotBlank(orgUnitNos.toString()))
					map.put("costOrgUnitNo", orgUnitNos.toString());
	        }
			break;
	    	case "scmcostconsumesumcust":
			String periodId2 = (String) map.get("periodId");
			String queryType=(String) map.get("queryType");
			map.put("queryType", StringUtils.isBlank(queryType)?"1":queryType);
			if (StringUtils.isNotBlank(periodId2) && StringUtils.isNumeric(periodId2)) {
				PeriodCalendarBiz periodCalendarBiz = (PeriodCalendarBiz) AppContextUtil.getBean("periodCalendarBiz");
				PeriodCalendar periodCalendar = periodCalendarBiz.select(Long.parseLong(periodId2), param);
				if (periodCalendar != null) {
					String accountDate = FormatUtils.fmtDate(periodCalendar.getStartDate());
					map.put("accountDate", accountDate);
				}
			}
			String state = (String) map.get("state");
			if (StringUtils.isNotBlank(state)) {
				map.put("state", state);
			}
			String materialClassName4 = (String) map.get("materialClassName");
			if (StringUtils.isNotBlank(materialClassName4)) {
				StringBuffer sbMaterilaClass = new StringBuffer("");
				ScmMaterialGroupBiz scmMaterialGroupBiz = (ScmMaterialGroupBiz) AppContextUtil
						.getBean("scmMaterialGroupBiz");
				String[] materialClassNameList = StringUtils.split(materialClassName4, ",");
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
				if (StringUtils.isNotBlank(sbMaterilaClass.toString()))
					map.put("materialClassName", sbMaterilaClass.toString());
			}
			String costOrgUnitNo3 = (String) map.get("costOrgUnitNo");
			if (StringUtils.isNotBlank(costOrgUnitNo3)) {
				StringBuffer orgUnitNos = new StringBuffer("");
				StringBuffer orgUnitNosByCostCenTerType = new StringBuffer("");
				OrgCostCenterBiz orgCostCenterBiz = (OrgCostCenterBiz) AppContextUtil.getBean("orgCostCenterBiz");
				String[] orgUnitNoList = StringUtils.split(costOrgUnitNo3, ",");
				for (String org : orgUnitNoList) {
					String orgUnitNo;
					if (StringUtils.isBlank(org)) {
						continue;
					}else {
						orgUnitNo = org.replace("'", "");
					}
					List<OrgCostCenter2> orgCostCenterList = orgCostCenterBiz.findChild(orgUnitNo, param);
					if (orgCostCenterList != null && !orgCostCenterList.isEmpty()) {
						for (OrgCostCenter2 orgCostCenter : orgCostCenterList) {
							if (StringUtils.isNotBlank(orgUnitNos.toString()))
								orgUnitNos.append(",");
							orgUnitNos.append("'").append(orgCostCenter.getOrgUnitNo()).append("'");
							if (StringUtils.equals("1", orgCostCenter.getCostCenterType())) {
								if (StringUtils.isNotBlank(orgUnitNosByCostCenTerType.toString()))
									orgUnitNosByCostCenTerType.append(",");
								orgUnitNosByCostCenTerType.append("'").append(orgCostCenter.getOrgUnitNo()).append("'");
							}
						}
					}
				}
				if (StringUtils.isNotBlank(orgUnitNos.toString()))
					map.put("costOrgUnitNo", orgUnitNos.toString());
				if (StringUtils.isNotBlank(orgUnitNosByCostCenTerType.toString()))
					map.put("orgUnitNosByCostCenTerType", orgUnitNosByCostCenTerType.toString());
			}
			break;
			case "scminvmovebillsummary":
			String tempMaterialClassName = (String) map.get("materialClassName");
			if (StringUtils.isNotBlank(tempMaterialClassName)) {
				StringBuffer sbMaterilaClass = new StringBuffer("");
				ScmMaterialGroupBiz scmMaterialGroupBiz = (ScmMaterialGroupBiz) AppContextUtil
						.getBean("scmMaterialGroupBiz");
				String[] materialClassNameList = StringUtils.split(tempMaterialClassName, ",");
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
				if (StringUtils.isNotBlank(sbMaterilaClass.toString()))
					map.put("materialClassName", sbMaterilaClass.toString());
			}
			break;
			case "scminvmovebilldetailsummary":
			String tempMaterialClassName2 = (String) map.get("materialClassName");
			if (StringUtils.isNotBlank(tempMaterialClassName2)) {
				StringBuffer sbMaterilaClass = new StringBuffer("");
				ScmMaterialGroupBiz scmMaterialGroupBiz = (ScmMaterialGroupBiz) AppContextUtil
						.getBean("scmMaterialGroupBiz");
				String[] materialClassNameList = StringUtils.split(tempMaterialClassName2, ",");
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
				if (StringUtils.isNotBlank(sbMaterilaClass.toString()))
					map.put("materialClassName", sbMaterilaClass.toString());
			}
			break;
			case "scminvsupplierwritemsum":
			String materialClassIds = (String) map.get("materialClassIds");
			if (StringUtils.isNotBlank(materialClassIds)) {
				StringBuffer sbMaterilaClass = new StringBuffer("");
				ScmMaterialGroupBiz scmMaterialGroupBiz = (ScmMaterialGroupBiz) AppContextUtil
						.getBean("scmMaterialGroupBiz");
				String[] materialClassNameList = StringUtils.split(materialClassIds, ",");
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
				if (StringUtils.isNotBlank(sbMaterilaClass.toString()))
					map.put("materialClassIds", sbMaterilaClass.toString());
			}
			break;
			case "scminvexpirationdateanalysis":{
				String whName = (String) map.get("whName");
				String costOrgUnitNo = (String) map.get("costOrgUnitNo");
				String invOrgUnitNo1 = (String) map.get("invOrgUnitNo");
				if (StringUtils.isEmpty(invOrgUnitNo1)) {
					OrgStorageBiz orgStorageBiz = (OrgStorageBiz) AppContextUtil.getBean("orgStorageBiz");
					List<OrgStorage2> orgStorageList = orgStorageBiz.findChild(param.getOrgUnitNo(), param);
					if (orgStorageList != null && !orgStorageList.isEmpty()) {
						StringBuffer orgStringBuffer = new StringBuffer("");
						for (OrgStorage2 orgStorage : orgStorageList) {
							if (StringUtils.isNotBlank(orgStringBuffer.toString()))
								orgStringBuffer.append(",");
							orgStringBuffer.append("'").append(orgStorage.getOrgUnitNo()).append("'");
						}
						if (StringUtils.isNotBlank(orgStringBuffer.toString()))
							map.put("invOrgUnitNo", orgStringBuffer.toString());
					}
				}
				if (StringUtils.isEmpty(whName) && StringUtils.isEmpty(costOrgUnitNo)) {
					break;
				}
				if (StringUtils.isNotEmpty(whName) && StringUtils.isNotEmpty(costOrgUnitNo)) {
					break;
				}
				if (StringUtils.isEmpty(whName)) {
					map.put("whName", "0");
				}
				if (StringUtils.isEmpty(costOrgUnitNo)) {
					map.put("costOrgUnitNo","0");
				}
				break;
			}
			case "scminvpricechangesanalysis":
		    	String pricechangePeriodId = (String) map.get("periodId");
		    	if (StringUtils.isNotBlank(pricechangePeriodId) && StringUtils.isNumeric(pricechangePeriodId)) {
		    		PeriodCalendarBiz periodCalendarBiz = (PeriodCalendarBiz) AppContextUtil
							.getBean("periodCalendarBiz");
					PeriodCalendar periodCalendar = periodCalendarBiz.select(Long.parseLong(pricechangePeriodId), param);
					if(periodCalendar != null){
						String accountDate = FormatUtils.fmtDate(periodCalendar.getStartDate());
						map.put("accountDate", accountDate);
					}
				}
		    	String pricechangeMaterialClassIds = (String) map.get("materialClassIds");
				if (StringUtils.isNotBlank(pricechangeMaterialClassIds)) {
					StringBuffer sbMaterilaClass = new StringBuffer("");
					ScmMaterialGroupBiz scmMaterialGroupBiz = (ScmMaterialGroupBiz) AppContextUtil
							.getBean("scmMaterialGroupBiz");
					String[] materialClassNameList = StringUtils.split(pricechangeMaterialClassIds, ",");
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
					if (StringUtils.isNotBlank(sbMaterilaClass.toString()))
						map.put("materialClassIds", sbMaterilaClass.toString());
				}
				break;
			case "selectscminvitemwrsummaterials":
				String invitemwrsummaterialsMaterialClassName = (String) map.get("materialClassName");
				if (StringUtils.isNotBlank(invitemwrsummaterialsMaterialClassName)) {
					StringBuffer sbMaterilaClass = new StringBuffer("");
					ScmMaterialGroupBiz scmMaterialGroupBiz = (ScmMaterialGroupBiz) AppContextUtil.getBean("scmMaterialGroupBiz");
					String[] materialClassNameList = StringUtils.split(invitemwrsummaterialsMaterialClassName, ",");
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
					if(StringUtils.isNotBlank(sbMaterilaClass.toString()))
						map.put("materialClassName", sbMaterilaClass.toString());
				}
				break;
    	}
    }
    
    private void afterQueryReportData(List list,String xmlId,Param param) {
    	if(list != null && list.size() > 0){
			switch (StringUtils.lowerCase(xmlId)) {
			case "scmsupsupplyofmaterialdetails":
				list.add("end");
				Map<String, Object> countMap = new HashMap<String, Object>();
				Map<String, Object> map = new HashMap<String, Object>();
				Map<String, Object> whCountMap = new HashMap<String, Object>();
				Map<String, Object> whMap = new HashMap<String, Object>();
				List list1 = new ArrayList<>();
				List list2 = new ArrayList();//供应商合计
				List list3 = new ArrayList<>();
				List list4 = new ArrayList<>();//仓库合计
				List list5 = new ArrayList<>();
				int n = 0;
				int b = 0;
				if (list != null && list.size() > 0) {
					list1.addAll(list);
				}
				String vendorNo = (String) ((HashMap<String, Object>) list.get(0)).get("vendorNo");
				String wareHouseName = (String) ((HashMap<String, Object>) list.get(0)).get("wareHouseName");
				
				for (int i = 0; i < list.size(); i++) {
					boolean flag = true;
					if (!list1.get(i).equals("end") && i>0) {
						//判断与前面的供应商是否相同，不相同则生成小计行
						if (!StringUtils.equals((String) ((HashMap<String, Object>) list.get(i)).get("vendorNo"), (String) ((HashMap<String, Object>) list.get(i-1)).get("vendorNo"))) {
							flag=false;
						}
					}
					if (!list.get(i).equals("end") && ((HashMap<String, Object>) list.get(i)).containsValue(wareHouseName)
							 && flag) {
						loadData(list, i, whCountMap);
					} else{
						whCountMap.put("wareHouseName", "仓库/部门小计:");
						whMap.put("index" + i, i);
						whMap.put("whCountMap" + i, whCountMap);
						list4.add(whMap);
						list5.add(i);
						if (i != list.size() - 1) {
							wareHouseName = (String) ((HashMap<String, Object>) list.get(i)).get("wareHouseName");
							whCountMap = new HashMap<String, Object>();
							flag=true;
							loadData(list, i, whCountMap);
						}
					}
				}
				for (int j = 0; j < list4.size(); j++) {
					int index = (int) ((HashMap<String, Object>) list4.get(j)).get("index" + list5.get(j));
					list1.add(index + b, ((HashMap<String, Object>) list4.get(j)).get("whCountMap" + list5.get(j)));
					b += 1;
				}
				
				for (int i = 0; i < list1.size(); i++) {
					if (!list1.get(i).equals("end")) {
						wareHouseName = (String) ((HashMap<String, Object>) list1.get(i)).get("wareHouseName");
					}
					if (!list1.get(i).equals("end") && ((HashMap<String, Object>) list1.get(i)).containsValue(vendorNo) && !StringUtils.equals("仓库/部门小计:", wareHouseName)) {
						loadData(list1, i, countMap);
					} else if(!StringUtils.equals("仓库/部门小计:", wareHouseName) || list1.size()-1==i){
						countMap.put("vendorName", "供应商小计:");
						map.put("index" + i, i);
						map.put("countMap" + i, countMap);
						list2.add(map);
						list3.add(i);
						if (i != list1.size() - 1) {
							vendorNo = (String) ((HashMap<String, Object>) list1.get(i)).get("vendorNo");
							countMap = new HashMap<String, Object>();
							loadData(list1, i, countMap);
						}

					}
				}
				for (int j = 0; j < list2.size(); j++) {
					int index = (int) ((HashMap<String, Object>) list2.get(j)).get("index" + list3.get(j));
					list1.add(index + n, ((HashMap<String, Object>) list2.get(j)).get("countMap" + list3.get(j)));
					n += 1;
				}
				list1.remove(list1.size() - 1);
				list.clear();
				list.addAll(list1);
				break;
			case "scmpurbuyerachievingratedetail" :
				for(int i = 0 ; i< list.size() ; i++) {
					HashMap map1 = (HashMap<String, Object>)list.get(i);
					String rowStatus = (String) map1.get("rowstatus");
					switch (rowStatus){
						case "I" :
							map1.put("rowstatus", "新建");
						break;
						case "C" :
							map1.put("rowstatus", "关闭");
						break;
						case "E" :
							map1.put("rowstatus", "下达");
						break;
						case "D" :
							map1.put("rowstatus", "待审");
						break;
						case "A" :
							map1.put("rowstatus", "通过");
						break;
						case "N" :
							map1.put("rowstatus", "未通过");
						break;
						case "P" :
							map1.put("rowstatus", "审核中");
						break;
						default :
							map1.put("rowstatus", "无");
					}
				}
				break;
			case "scmscmmatrialconvratesum" :
				for(int i = 0 ; i< list.size() ; i++) {
					HashMap map1 = (HashMap<String, Object>)list.get(i);
					String scmstatus = (String) map1.get("scmstatus");
					Code code = codeBiz.selectByCategoryAndCode("scmStatus", scmstatus);
					map1.put("scmstatus", code.getName());
				}
				break;
			}
    	}
    }
    
    private static void loadData(List list,int i,Map<String,Object> countMap){
    	BigDecimal qtyValue=(BigDecimal) ((HashMap<String, Object>) list.get(i)).get("qty");
        BigDecimal amtValue=(BigDecimal) ((HashMap<String, Object>) list.get(i)).get("amt");
        BigDecimal taxAmountValue=(BigDecimal) ((HashMap<String, Object>) list.get(i)).get("taxAmount");
        BigDecimal taxAmtValue=(BigDecimal) ((HashMap<String, Object>) list.get(i)).get("taxAmt");
        BigDecimal qty=(BigDecimal) (countMap.get("qty")==null?new BigDecimal(0):countMap.get("qty"));
        BigDecimal amt=(BigDecimal) (countMap.get("amt")==null?new BigDecimal(0):countMap.get("amt"));
        BigDecimal taxAmount=(BigDecimal) (countMap.get("taxAmount")==null?new BigDecimal(0):countMap.get("taxAmount"));
        BigDecimal taxAmt=(BigDecimal) (countMap.get("taxAmt")==null?new BigDecimal(0):countMap.get("taxAmt"));
        if(qtyValue!=null){countMap.put("qty",qtyValue.add(qty));}
        if(amtValue!=null){countMap.put("amt",amtValue.add(amt));}
        if(taxAmountValue!=null){countMap.put("taxAmount",taxAmountValue.add(taxAmount));}
        if(taxAmtValue!=null){countMap.put("taxAmt",taxAmtValue.add(taxAmt));}
    }
}

