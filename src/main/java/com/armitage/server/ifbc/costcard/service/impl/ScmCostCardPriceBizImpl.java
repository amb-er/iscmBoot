package com.armitage.server.ifbc.costcard.service.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Page;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.util.BeanUtil;
import com.armitage.server.common.util.CalendarUtil;
import com.armitage.server.common.util.FormatUtils;
import com.armitage.server.ifbc.basedata.model.ScmAccountingCycle;
import com.armitage.server.ifbc.basedata.model.ScmResOrgUnitMap;
import com.armitage.server.ifbc.basedata.service.ScmAccountingCycleBiz;
import com.armitage.server.ifbc.basedata.service.ScmResOrgUnitMapBiz;
import com.armitage.server.ifbc.costcard.dao.ScmCostCardPriceDAO;
import com.armitage.server.ifbc.costcard.model.ScmCostCard2;
import com.armitage.server.ifbc.costcard.model.ScmCostCardPrice;
import com.armitage.server.ifbc.costcard.service.ScmCostCardBiz;
import com.armitage.server.ifbc.costcard.service.ScmCostCardPriceBiz;
import com.armitage.server.ifbm.model.FbmDishPrc2;
import com.armitage.server.ifbm.service.FbmDishPrcBiz;
import org.springframework.stereotype.Service;

@Service("scmCostCardPriceBiz")
public class ScmCostCardPriceBizImpl extends BaseBizImpl<ScmCostCardPrice> implements ScmCostCardPriceBiz {
	
	private ScmCostCardBiz scmCostCardBiz;
	private FbmDishPrcBiz fbmDishPrcBiz;
	private ScmResOrgUnitMapBiz scmResOrgUnitMapBiz;
	private ScmAccountingCycleBiz scmAccountingCycleBiz;
	
	public void setScmCostCardBiz(ScmCostCardBiz scmCostCardBiz) {
		this.scmCostCardBiz = scmCostCardBiz;
	}

	public void setFbmDishPrcBiz(FbmDishPrcBiz fbmDishPrcBiz) {
		this.fbmDishPrcBiz = fbmDishPrcBiz;
	}

	public void setScmResOrgUnitMapBiz(ScmResOrgUnitMapBiz scmResOrgUnitMapBiz) {
		this.scmResOrgUnitMapBiz = scmResOrgUnitMapBiz;
	}

	public void setScmAccountingCycleBiz(ScmAccountingCycleBiz scmAccountingCycleBiz) {
		this.scmAccountingCycleBiz = scmAccountingCycleBiz;
	}

	@Override
	public boolean modifySalePrice(String orgUnitNo,Param param) throws AppException {
		boolean flag = modifySalePrice(orgUnitNo,CalendarUtil.relativeDate(FormatUtils.parseDate(FormatUtils.fmtDate(new Date())),-1),param);
		if(flag)
			flag = modifySalePrice(orgUnitNo,new Date(),param);
		return flag;
	}

	private boolean modifySalePrice(String orgUnitNo,Date accDate, Param param) throws AppException{
		if(StringUtils.isNotBlank(orgUnitNo)){
			Page page = new Page();
			page.setModelClass(ScmCostCard2.class);
			page.setShowCount(Integer.MAX_VALUE);
			List<ScmCostCard2> scmCostCardList = scmCostCardBiz.findPage(page, param);
			if(scmCostCardList != null && !scmCostCardList.isEmpty()){
				List<Long> dishList = new ArrayList();
				for(ScmCostCard2 scmCostCard : scmCostCardList){
					dishList.add(scmCostCard.getDishId());
				}
				String fbmOrgUnitNo = orgUnitNo;
				String fbmControlUnitNo = param.getControlUnitNo();
				ScmResOrgUnitMap scmResOrgUnitMap = scmResOrgUnitMapBiz.selectByResOrgUnit(orgUnitNo, param);
				if(scmResOrgUnitMap!=null && !scmResOrgUnitMap.isShared()) {
					fbmOrgUnitNo = scmResOrgUnitMap.getFbmResOrgUnitNo();
					fbmControlUnitNo = scmResOrgUnitMap.getFbmControlUnitNo();
				}
				List<FbmDishPrc2> fbmDishPrcList = fbmDishPrcBiz.selectDishSalePrice(fbmOrgUnitNo,fbmControlUnitNo,orgUnitNo ,accDate, dishList, param);
				if(fbmDishPrcList!=null && !fbmDishPrcList.isEmpty()) {
					List<ScmCostCardPrice> scmCostCardPriceList = new ArrayList();
					for(FbmDishPrc2 fbmDishPrc:fbmDishPrcList) {
						ScmCostCardPrice scmCostCardPrice = new ScmCostCardPrice(true);
						BeanUtil.copyProperties(scmCostCardPrice, fbmDishPrc);
						scmCostCardPriceList.add(scmCostCardPrice);
					}
					this.batchAddSalePrice(scmCostCardPriceList, param);
				}
				return true;
			}
		}
		return false;
	}
	
	@Override
	public ScmCostCardPrice selectByScmCostCardPrice(ScmCostCardPrice scmCostCardPrice, Param param)
			throws AppException {
		if(scmCostCardPrice != null){
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("orgUnitNo", scmCostCardPrice.getOrgUnitNo());
			map.put("dishId",scmCostCardPrice.getDishId());
			map.put("accDate",FormatUtils.fmtDate(scmCostCardPrice.getAccDate()));
			return ((ScmCostCardPriceDAO)dao).selectByScmCostCardPrice(map);
		}
		return null;
	}

	@Override
	public int updateCostPrice(String orgUnitNo, Param param) throws AppException {
		return updateCostPrice(orgUnitNo,CalendarUtil.relativeDate(FormatUtils.parseDate(FormatUtils.fmtDate(new Date())),-1),new Date(),param);
	}

	public int updateCostPrice(String orgUnitNo,Date begDate,Date endDate, Param param) throws AppException {
		if(StringUtils.isNoneBlank(orgUnitNo)){
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("controlUnitNo",param.getControlUnitNo());
			map.put("orgUnitNo",orgUnitNo);
			map.put("begDate",FormatUtils.fmtDate(begDate));
			map.put("endDate",FormatUtils.fmtDate(endDate));
			((ScmCostCardPriceDAO)dao).batchAddCostPrice(map);
		}
		return 0;
	}
	
	private void batchAddSalePrice(List<ScmCostCardPrice> scmCostCardPriceList, Param param) throws AppException{
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("scmCostCardPriceList", scmCostCardPriceList);
		((ScmCostCardPriceDAO)dao).batchAddSalePrice(map);
	}

	@Override
	public void getSalePrice(String orgUnitNo, long begPeriodId, long endPeriodId, Param param) throws AppException {
		ScmAccountingCycle begScmAccountingCycle = scmAccountingCycleBiz.selectDirect(begPeriodId, param);
		ScmAccountingCycle endScmAccountingCycle = scmAccountingCycleBiz.selectDirect(endPeriodId, param);
		if(begScmAccountingCycle==null || endScmAccountingCycle==null) {
			throw new AppException("field.ScmAccountingCycle.error.wrongperiod");
		}
		modifySalePrice(orgUnitNo,begScmAccountingCycle.getStartDate(),param);
		addPeriodSalePrice(orgUnitNo,begScmAccountingCycle.getStartDate(),endScmAccountingCycle.getEndDate(),param);
	}

	private void addPeriodSalePrice(String orgUnitNo, Date startDate, Date endDate, Param param) {
		if(StringUtils.isNoneBlank(orgUnitNo)){
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("orgUnitNo",orgUnitNo);
			map.put("begDate",FormatUtils.fmtDate(startDate));
			map.put("endDate",FormatUtils.fmtDate(endDate));
			((ScmCostCardPriceDAO)dao).addPeriodSalePrice(map);
		}
	}

	@Override
	public void calcCostPrice(String orgUnitNo, long begPeriodId, long endPeriodId, Param param) throws AppException {
		ScmAccountingCycle begScmAccountingCycle = scmAccountingCycleBiz.selectDirect(begPeriodId, param);
		ScmAccountingCycle endScmAccountingCycle = scmAccountingCycleBiz.selectDirect(endPeriodId, param);
		if(begScmAccountingCycle==null || endScmAccountingCycle==null) {
			throw new AppException("field.ScmAccountingCycle.error.wrongperiod");
		}
		updateCostPrice(orgUnitNo,begScmAccountingCycle.getStartDate(),endScmAccountingCycle.getEndDate(),param);
	}

	@Override
	public void getSalePriceByTask(String orgUnitNo, Date begDate, Date endDate, Param param) throws AppException {
		param.setOrgUnitNo(orgUnitNo);
		modifySalePrice(orgUnitNo,begDate,param);
		addPeriodSalePrice(orgUnitNo,begDate,endDate,param);
	}

	@Override
	public void calcCostPriceByTask(String orgUnitNo, Date begDate, Date endDate, Param param) throws AppException {
		param.setOrgUnitNo(orgUnitNo);
		updateCostPrice(orgUnitNo,begDate,endDate,param);
	}
}

