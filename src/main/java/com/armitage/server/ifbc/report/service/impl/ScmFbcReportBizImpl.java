package com.armitage.server.ifbc.report.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.Page;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.base.model.QueryParam;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.util.AppContextUtil;
import com.armitage.server.common.util.ClassUtils;
import com.armitage.server.common.util.DesUtils;
import com.armitage.server.common.util.JSONUtils;
import com.armitage.server.common.util.ListSortUtil;
import com.armitage.server.iaps.daily.model.ApinvoiceEntry2;
import com.armitage.server.ifbc.basedata.model.ScmAccountingCycleType2;
import com.armitage.server.ifbc.basedata.model.ScmResOrgUnitMap;
import com.armitage.server.ifbc.basedata.service.ScmAccountingCycleTypeBiz;
import com.armitage.server.ifbc.basedata.service.ScmResOrgUnitMapBiz;
import com.armitage.server.ifbc.report.dao.ScmFbcReportDAO;
import com.armitage.server.ifbc.report.model.ScmDeptAndOutltProfit;
import com.armitage.server.ifbc.report.model.ScmDishSaleStructureAnalysis;
import com.armitage.server.ifbc.report.service.ScmFbcReportBiz;
import com.armitage.server.ifbm.model.FbmDishSpec;
import com.armitage.server.ifbm.service.FbmDishSpecBiz;
import com.armitage.server.system.model.Code;
import com.armitage.server.system.model.OrgResource2;
import com.armitage.server.system.model.Outlet;
import com.armitage.server.system.service.CodeBiz;
import com.armitage.server.system.service.OrgResourceBiz;
import com.armitage.server.system.service.OutletBiz;
import com.armitage.server.system.service.SysParamBiz;
import com.armitage.server.webservice.model.ReportRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Service;

@Service("scmFbcReportBiz")
public class ScmFbcReportBizImpl extends BaseBizImpl<BaseModel> implements ScmFbcReportBiz {
	private OrgResourceBiz orgResourceBiz;
	private CodeBiz codeBiz;
	private ScmAccountingCycleTypeBiz scmAccountingCycleTypeBiz;
	private ScmResOrgUnitMapBiz scmResOrgUnitMapBiz;
	private OutletBiz outletBiz;
	private HashMap<String, Object> beanMap = new HashMap<String, Object>();
	private HashMap<String, String> valueMap = new HashMap<String, String>();
	private HashMap<Long, String> outletMap = new HashMap<Long, String>();
	private SysParamBiz sysParamBiz;
	private FbmDishSpecBiz fbmDishSpecBiz;
	
    public void setFbmDishSpecBiz(FbmDishSpecBiz fbmDishSpecBiz) {
		this.fbmDishSpecBiz = fbmDishSpecBiz;
	}

	public void setSysParamBiz(SysParamBiz sysParamBiz) {
		this.sysParamBiz = sysParamBiz;
	}

	public void setOutletBiz(OutletBiz outletBiz) {
		this.outletBiz = outletBiz;
	}

	public void setCodeBiz(CodeBiz codeBiz) {
		this.codeBiz = codeBiz;
	}

	public void setOrgResourceBiz(OrgResourceBiz orgResourceBiz) {
		this.orgResourceBiz = orgResourceBiz;
	}

	public void setScmAccountingCycleTypeBiz(ScmAccountingCycleTypeBiz scmAccountingCycleTypeBiz) {
		this.scmAccountingCycleTypeBiz = scmAccountingCycleTypeBiz;
	}

	
	public void setScmResOrgUnitMapBiz(ScmResOrgUnitMapBiz scmResOrgUnitMapBiz) {
		this.scmResOrgUnitMapBiz = scmResOrgUnitMapBiz;
	}

	public void setBeanMap(HashMap<String, Object> beanMap) {
		this.beanMap = beanMap;
	}

	public void setValueMap(HashMap<String, String> valueMap) {
		this.valueMap = valueMap;
	}

	@Override
	public List queryFbcReportData(HttpServletRequest request) throws AppException {
    	List list = null;
		String paramValueMap=request.getParameter("paramValueMap");
		String xmlId = request.getParameter("xmlId");
		String orgUnitNo=request.getParameter("orgUnitNo");
		String controlUnitNo=request.getParameter("controlUnitNo");
		if(StringUtils.isNotBlank(paramValueMap) && StringUtils.isNotBlank(xmlId)) {
			try {
				DesUtils des = new DesUtils("GyYo!N9T");// 自定义密钥
				paramValueMap = des.decrypt(paramValueMap);
				Gson gson = JSONUtils.newGson();
				ReportRequest reportRequest = gson.fromJson(paramValueMap, new TypeToken<ReportRequest>(){}.getType());
				LinkedHashMap<String, Object> map = reportRequest.getParamValueMap();
				map.put("controlUnitNo", controlUnitNo);
				beforeQueryFbcReportData(map,xmlId);
				list = ((ScmFbcReportDAO) dao).queryFbcReportData(map,xmlId);
				Param param = new Param();
				param.setOrgUnitNo(orgUnitNo);
				param.setControlUnitNo(controlUnitNo);
				list = convertDisplay(list,param);
			} catch (Exception ex) {
				ex.printStackTrace(); 
            }
		}
		return list;
	}
    
    private void beforeQueryFbcReportData(LinkedHashMap<String, Object> map,String xmlId) {
    	switch (StringUtils.lowerCase(xmlId)) {
        	case "selectdishitemanalysedetail" :{
        		//原料损益明细表
            	if(map.containsKey("dishCodes") && map.get("dishCodes") instanceof String && StringUtils.isNotBlank((String)map.get("dishCodes"))) {
            		String dishCodes = (String)map.get("dishCodes");
            		dishCodes = dishCodes.replaceAll("\\$", "'");
            		map.put("dishCodes", dishCodes);
            	}
            }
            break;
        }
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
								if(StringUtils.contains((String) key, "type")) {
									if(StringUtils.isNotBlank((String) map.get(key))) {
										Code code = codeBiz.selectByCategoryAndCode("ScmFormulaType", (String) map.get(key));
										if(code != null) {
											map.put(key, code.getName());
										}
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
			if(valueMap.containsKey(beanId+"_"+key)) {
				return valueMap.get(beanId+"_"+key);
			}
			BaseBiz biz =(BaseBiz) beanMap.get(beanId);
			if(biz==null) {
				biz = (BaseBiz) AppContextUtil.getBean(beanId);
				beanMap.put(beanId, biz);
			}
			if (biz != null) {
				String value = biz.getDisplayByKey(key, param);
				valueMap.put(beanId+"_"+key,value);
				return value;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return key;
	}

	@Override
	public List queryFbcStoreProfitData(HttpServletRequest request) throws AppException {
		List list = null;
		String paramValueMap=request.getParameter("paramValueMap");
		String xmlId = request.getParameter("xmlId");
		String orgUnitNo=request.getParameter("orgUnitNo");
		String controlUnitNo=request.getParameter("controlUnitNo");
		Param param = new Param();
		param.setOrgUnitNo(orgUnitNo);
		param.setControlUnitNo(controlUnitNo);
		if(StringUtils.isNotBlank(paramValueMap) && StringUtils.isNotBlank(xmlId)) {
			try {
				DesUtils des = new DesUtils("GyYo!N9T");// 自定义密钥
				paramValueMap = des.decrypt(paramValueMap);
				Gson gson = JSONUtils.newGson();
				ReportRequest reportRequest = gson.fromJson(paramValueMap, new TypeToken<ReportRequest>(){}.getType());
				LinkedHashMap<String, Object> map = reportRequest.getParamValueMap();
				String periodType="";
				long periodTypeId = Long.valueOf((String) map.get("periodType"));
				ScmAccountingCycleType2 scmAccountingCycleType2 = scmAccountingCycleTypeBiz.selectDirect(periodTypeId, param);
				if(scmAccountingCycleType2!=null) {
					periodType = scmAccountingCycleType2.getPeriodType();
				}
				String orgResUnitNo = (String) map.get("orgUnitNo");
				if(StringUtils.isEmpty(orgResUnitNo)) {
					orgResUnitNo = orgUnitNo;
				}
				Page page = new Page();
				page.setModelClass(ScmAccountingCycleType2.class);
				page.setShowCount(Integer.MAX_VALUE);
				List<String> arglist = new ArrayList();
				arglist.add("controlUnitNo="+controlUnitNo);
				List<ScmAccountingCycleType2> scmAccountingCycleTypeList = scmAccountingCycleTypeBiz.queryPage(page, arglist, "selectByCtrlUnit", param);
				String[] orgUnitList = StringUtils.split(orgResUnitNo, ",");
				StringBuffer orgList = new StringBuffer("");
				if(orgUnitList.length==1) {
					orgResUnitNo = StringUtils.replace(orgResUnitNo, "'", "");
					List<OrgResource2> orgResourceList = orgResourceBiz.findChild(orgResUnitNo,param);
					if(orgResourceList!=null && !orgResourceList.isEmpty()) {
						for(OrgResource2 orgResource:orgResourceList) {
							boolean exists=false;
							if(scmAccountingCycleTypeList!=null && !scmAccountingCycleTypeList.isEmpty()) {
								for(ScmAccountingCycleType2 scmAccountingCycleType:scmAccountingCycleTypeList) {
									if(StringUtils.equals(scmAccountingCycleType.getOrgUnitNo(), orgResource.getOrgUnitNo())
											&& StringUtils.equals(periodType,scmAccountingCycleType.getPeriodType())) {
										exists=true;
										break;
									}
								}
							}
							if(exists) {
								if(StringUtils.isNotBlank(orgList.toString()))
									orgList.append(",");
								orgList.append("'").append(orgResource.getOrgUnitNo()).append("'");
							}
						}
					}
				}else {
					for(String str:orgUnitList) {
						boolean exists=false;
						if(scmAccountingCycleTypeList!=null && !scmAccountingCycleTypeList.isEmpty()) {
							for(ScmAccountingCycleType2 scmAccountingCycleType:scmAccountingCycleTypeList) {
								if(StringUtils.equals(scmAccountingCycleType.getOrgUnitNo(), StringUtils.replace(str, "'", ""))
										&& StringUtils.equals(periodType,scmAccountingCycleType.getPeriodType())) {
									exists=true;
									break;
								}
							}
						}
						if(exists) {
							if(StringUtils.isNotBlank(orgList.toString()))
								orgList.append(",");
							orgList.append(str);
						}
					}
				}
				if(StringUtils.isEmpty(orgList.toString()))
					orgList.append("'0'");
				map.put("orgUnitNo", orgList.toString());
				list = ((ScmFbcReportDAO) dao).queryFbcReportData(map,xmlId);
				list = convertDisplay(list,param);
			} catch (Exception ex) {
				ex.printStackTrace(); 
            }
		}
		return list;
	}

	@Override
	public List<ScmDeptAndOutltProfit> selectOrgAndOutltProfit(HttpServletRequest request) throws AppException {
		List<ScmDeptAndOutltProfit> scmDeptAndOutltProfits = null;
		String orgUnitNo=request.getParameter("orgUnitNo");
		String controlUnitNo=request.getParameter("controlUnitNo");
		String begPeriodId=request.getParameter("begPeriodId");
		String endPeriodId=request.getParameter("endPeriodId");
		String whOrDept=request.getParameter("whOrDept");  
		String stockType=request.getParameter("stockType"); 
		String resOrgUnitNo =request.getParameter("resOrgUnitNo");
		Param param = new Param();
		param.setOrgUnitNo(orgUnitNo);
		param.setControlUnitNo(controlUnitNo);
		HashMap<String, Object> map = new HashMap();
		map.put("begPeriodId",begPeriodId);
		map.put("endPeriodId",endPeriodId);
		map.put("stockType", stockType);
		List<OrgResource2> orgResource2s = orgResourceBiz.findChild(resOrgUnitNo, param);
		StringBuffer orgResources = new StringBuffer();
		for(OrgResource2 orgResource:orgResource2s) {
			ScmResOrgUnitMap scmResOrgUnitMap = scmResOrgUnitMapBiz.selectByResOrgUnit(resOrgUnitNo, param);
			if(scmResOrgUnitMap!=null && !scmResOrgUnitMap.isShared()) {
				if(StringUtils.isNotBlank(orgResources.toString()))
					orgResources.append(",");
				orgResources.append(scmResOrgUnitMap.getFbmResOrgUnitNo());
				controlUnitNo = scmResOrgUnitMap.getFbmControlUnitNo();
			}else {
				if(StringUtils.isNotBlank(orgResources.toString()))
					orgResources.append(",");
				orgResources.append(orgResource.getOrgUnitNo());
			}
		}
		
		
		map.put("resOrgUnitNo", orgResources.toString());
		if (StringUtils.isNotBlank(whOrDept)) {
			if (!StringUtils.equals("1", stockType)) {
				map.put("deptIds", whOrDept);
			} else {
				map.put("ouletIds", whOrDept);
			}
		}
		
		scmDeptAndOutltProfits = ((ScmFbcReportDAO) dao).selectOrgAndOutltProfit(map);
		
		for(ScmDeptAndOutltProfit scmDeptAndOutltProfit:scmDeptAndOutltProfits) {
			scmDeptAndOutltProfit.setStdAmt(scmDeptAndOutltProfit.getStdAmt() == null?BigDecimal.ZERO:scmDeptAndOutltProfit.getStdAmt());
			scmDeptAndOutltProfit.setRealAmt(scmDeptAndOutltProfit.getRealAmt() == null?BigDecimal.ZERO:scmDeptAndOutltProfit.getRealAmt());
			if (StringUtils.equals("1", stockType)) {
				if(scmDeptAndOutltProfit.getOutletId()!=null && scmDeptAndOutltProfit.getOutletId() >0) {
					scmDeptAndOutltProfit.setName(getOutletName(scmDeptAndOutltProfit.getOutletId(),param));
				}else {
					scmDeptAndOutltProfit.setName("");
				}
			} 
		}
		
		// 数据排序
		String fields[] = { "name","realAmt"};
		String sorts[] = { "DESC","DESC"};
		scmDeptAndOutltProfits = (List<ScmDeptAndOutltProfit>) ListSortUtil.sort(scmDeptAndOutltProfits,
				fields, sorts);
		return scmDeptAndOutltProfits;
	}

	private String getOutletName(Long outletId, Param param) {
		if(outletMap.containsKey(outletId))
			return outletMap.get(outletId);
		Outlet select = outletBiz.select(outletId, param);
		if(select != null) {
			outletMap.put(outletId, select.getName());
		}else {
			outletMap.put(outletId, outletId+"");
		}
		return outletMap.get(outletId);
	}

	@Override
	public List<ScmDishSaleStructureAnalysis> selectDishSaleStructureAnalysis(HttpServletRequest request)
			throws AppException {
		
		String orgUnitNo=request.getParameter("orgUnitNo");
		String controlUnitNo=request.getParameter("controlUnitNo");
		String resOrgUnitNo=request.getParameter("resOrgUnitNo");
        String begDate = request.getParameter("begDate");
        String endDate = request.getParameter("endDate");
        String productDeptNo = request.getParameter("productDeptNo");
        String dishCodes = request.getParameter("dishCodes");
        
        String outlet    = request.getParameter("outlet");
        String dishTypes = request.getParameter("dishTypes");
        String noCost = request.getParameter("noCost");
        
        BigDecimal saleQty_sum = new BigDecimal(0); //总销量
        BigDecimal realSaleAmt_sum = new BigDecimal(0);//总销价
        BigDecimal stdAmt_sum = new BigDecimal(0);//总成本
		Param param = new Param();
		param.setOrgUnitNo(orgUnitNo);
		param.setControlUnitNo(controlUnitNo);
        
		List<OrgResource2> orgResource2s = orgResourceBiz.findChild(resOrgUnitNo, param);
		StringBuffer orgResources = new StringBuffer();
		for(OrgResource2 orgResource:orgResource2s) {
			ScmResOrgUnitMap scmResOrgUnitMap = scmResOrgUnitMapBiz.selectByResOrgUnit(resOrgUnitNo, param);
			if(scmResOrgUnitMap!=null && !scmResOrgUnitMap.isShared()) {
				if(StringUtils.isNotBlank(orgResources.toString()))
					orgResources.append(",");
				orgResources.append(scmResOrgUnitMap.getFbmResOrgUnitNo());
				controlUnitNo = scmResOrgUnitMap.getFbmControlUnitNo();
			}else {
				if(StringUtils.isNotBlank(orgResources.toString()))
					orgResources.append(",");
				orgResources.append(orgResource.getOrgUnitNo());
			}
		}
		
		HashMap<String,Object> map = new HashMap<String, Object>();
		map.put("resOrgUnitNo", orgResources.toString());
		map.put("begDate",begDate);
		map.put("endDate", endDate);
		map.put("noCost", noCost);
		map.put("deptIds", productDeptNo);
		map.put("outlet", outlet);
		if(StringUtils.isNotBlank(dishTypes)){
			String[] dishTypelist= dishTypes.split(",");
	        StringBuffer dishTypesBuff = new StringBuffer();
			for(String code :dishTypelist) {
				if(StringUtils.isNotBlank(dishTypesBuff.toString())) {
					dishTypesBuff.append(",");
				}
				dishTypesBuff.append("'"+code+"'");
			}
			map.put("dishTypes", dishTypesBuff);
		}
		if(StringUtils.isNotBlank(dishCodes)){
			String[] codes= dishCodes.split(",");
	        StringBuffer codeBuf = new StringBuffer();
			for(String code :codes) {
				if(StringUtils.isNotBlank(codeBuf.toString())) {
					codeBuf.append(",");
				}
				codeBuf.append("'"+code+"'");
			}
			map.put("dishCodes", codeBuf);
		}
		
		if(StringUtils.isNotBlank(productDeptNo)){
			String[] depts= productDeptNo.split(",");
	        StringBuffer deptBuf = new StringBuffer();
			for(String dept :depts) {
				if(StringUtils.isNotBlank(deptBuf.toString())) {
					deptBuf.append(",");
				}
				deptBuf.append("'"+dept+"'");
			}
			map.put("deptIds", deptBuf.toString());
		}		
		List<ScmDishSaleStructureAnalysis> scmDishSaleStructureAnalysisList = null;
		
		scmDishSaleStructureAnalysisList = ((ScmFbcReportDAO) dao).selectDishSaleStructureAnalysis(map);
		
		StringBuffer specidBuff = new StringBuffer();
		for(ScmDishSaleStructureAnalysis specid:scmDishSaleStructureAnalysisList) {
			if(specid.getDishSpecId()!=null) {
			if(StringUtils.isNotBlank(specidBuff.toString())) {
				specidBuff.append(",");
			}
			specidBuff.append(String.valueOf(specid.getDishSpecId()));
			}
			saleQty_sum=saleQty_sum.add(specid.getSaleQty());
			realSaleAmt_sum=realSaleAmt_sum.add(specid.getRealSaleAmt());
			stdAmt_sum=stdAmt_sum.add(specid.getStdAmt());
		}
		if(StringUtils.isBlank(specidBuff.toString())) {
			return scmDishSaleStructureAnalysisList;
		}
		
		String fbmDish_id = ClassUtils.getFinalModelSimpleName(FbmDishSpec.class)+"."+FbmDishSpec.FN_ID;
		List<String> arglist = new ArrayList<>();
		Page page = new Page();
		page.setModelClass(ApinvoiceEntry2.class);
		page.setShowCount(Integer.MAX_VALUE);
		page.getParam().put(fbmDish_id, new QueryParam(fbmDish_id,QueryParam.QUERY_IN,specidBuff.toString()));
		List<FbmDishSpec> specList = fbmDishSpecBiz.queryPage(page, arglist,"findAllPage", param);
		HashMap<String, String> fbmMap = new HashMap<String, String>();
		for(FbmDishSpec fbm_spec: specList) {
			fbmMap.put(String.valueOf(fbm_spec.getId()), fbm_spec.getName());
		}
		for(ScmDishSaleStructureAnalysis samdish : scmDishSaleStructureAnalysisList) {
			if(samdish.getDishSpecId()!=null)
			samdish.setDishSpecName(fbmMap.get(String.valueOf(samdish.getDishSpecId())));
		}
		//平均毛利 
		if(realSaleAmt_sum==null||realSaleAmt_sum.compareTo(BigDecimal.ZERO)<1) {
			return scmDishSaleStructureAnalysisList;
		}
		 
		
		BigDecimal avg_profit = stdAmt_sum.divide(realSaleAmt_sum,4,BigDecimal.ROUND_HALF_UP);
		for(ScmDishSaleStructureAnalysis samdish : scmDishSaleStructureAnalysisList) {
			if(samdish.getDishSpecId()!=null)
			samdish.setDishSpecName(fbmMap.get(String.valueOf(samdish.getDishSpecId())));
			
            if(BigDecimal.ZERO.compareTo(samdish.getSaleQty())==0
            		||BigDecimal.ZERO.compareTo(samdish.getRealSaleAmt())==0
            		||BigDecimal.ZERO.compareTo(samdish.getStdAmt())==0) {
				continue;
			}
			
		/**
		 * 销量标准:高：  销量/(总销量/菜品总数)>1.2
		 *       低：	  销量/(总销量/菜品总数)<0.8
	             一般： 销量/(总销量/菜品总数)>=0.8 and 销量/(总销量/菜品总数)<=1.2
	        
	        毛利标准：	平均毛利	高：	毛利额>=平均毛利	
		                    低：	毛利额<平均毛利	
           菜品类型
			 1：主打菜品：毛利高销量高 
			 2：盈利菜品：毛利高销量一般
			 3：引流菜品：毛利低销量高或销量一般
			 4：辅助菜品：毛利高销售低
			 5：需调整菜品：毛利低销售低
			 6:未分类菜品
		 */
		//毛利 <=0：高    1：低   
          int profit =avg_profit.compareTo(samdish.getStdAmt().divide(samdish.getRealSaleAmt(),4,BigDecimal.ROUND_HALF_UP));

    		String aaa=  sysParamBiz.getValue(controlUnitNo,"SCM_SalesVolume", param);
      		String[] par = aaa.split(",");
              BigDecimal sale_high = new BigDecimal(par[0]);
              BigDecimal sale_low = new BigDecimal(par[1]);
         //销量 比    销量/(总销量/菜品总数)
          BigDecimal sales = samdish.getSaleQty().divide(saleQty_sum.divide(new BigDecimal(scmDishSaleStructureAnalysisList.size()),4,BigDecimal.ROUND_HALF_UP),4,BigDecimal.ROUND_HALF_UP);
			if(profit==1) {
				if(sales.compareTo(sale_low)>=0) {//引流菜品
					samdish.setDishType("3");
				}else {
					samdish.setDishType("5");//未分类菜品
				}
				
			}else {
				if(sales.compareTo(sale_high)>0) {//主打菜品
					samdish.setDishType("1");
				}else if(sales.compareTo(sale_low)<0){
					samdish.setDishType("4");
				}else {
					samdish.setDishType("2");
				}
				
			}
          
		}
		String fields[] = { "dishType"};
		String sorts[] = { "ASC"};
		scmDishSaleStructureAnalysisList = (List<ScmDishSaleStructureAnalysis>) ListSortUtil.sort(scmDishSaleStructureAnalysisList,fields, sorts);
		
		return scmDishSaleStructureAnalysisList;
	}

}

