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
import com.armitage.server.ifbc.costcard.dao.ScmCookCostCardPriceDAO;
import com.armitage.server.ifbc.costcard.model.ScmCookCostCard;
import com.armitage.server.ifbc.costcard.model.ScmCookCostCardPrice;
import com.armitage.server.ifbc.costcard.service.ScmCookCostCardBiz;
import com.armitage.server.ifbc.costcard.service.ScmCookCostCardPriceBiz;
import com.armitage.server.ifbm.model.FbmCookResInfo2;
import com.armitage.server.ifbm.service.FbmCookResInfoBiz;
import org.springframework.stereotype.Service;

@Service("scmCookCostCardPriceBiz")
public class ScmCookCostCardPriceBizImpl extends BaseBizImpl<ScmCookCostCardPrice> implements ScmCookCostCardPriceBiz {

	private ScmCookCostCardBiz scmCookCostCardBiz;
	private ScmResOrgUnitMapBiz scmResOrgUnitMapBiz;
	private FbmCookResInfoBiz fbmCookResInfoBiz;
	private ScmAccountingCycleBiz scmAccountingCycleBiz;
	
	public void setScmCookCostCardBiz(ScmCookCostCardBiz scmCookCostCardBiz) {
		this.scmCookCostCardBiz = scmCookCostCardBiz;
	}
	
	public void setScmResOrgUnitMapBiz(ScmResOrgUnitMapBiz scmResOrgUnitMapBiz) {
		this.scmResOrgUnitMapBiz = scmResOrgUnitMapBiz;
	}

	public void setFbmCookResInfoBiz(FbmCookResInfoBiz fbmCookResInfoBiz) {
		this.fbmCookResInfoBiz = fbmCookResInfoBiz;
	}

	public void setScmAccountingCycleBiz(ScmAccountingCycleBiz scmAccountingCycleBiz) {
		this.scmAccountingCycleBiz = scmAccountingCycleBiz;
	}

	@Override
	public ScmCookCostCardPrice selectCurrPriceByCookId(long cookId, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("cookId", cookId);
		map.put("orgUnitNo", param.getOrgUnitNo());
		map.put("accDate", FormatUtils.fmtDate(new Date()));
		return ((ScmCookCostCardPriceDAO)dao).selectCurrPriceByCookId(map);
	}

	@Override
	public int updateCostPrice(String orgUnitNo, Param param) throws AppException {
		return updateCostPrice(orgUnitNo,CalendarUtil.relativeDate(FormatUtils.parseDate(FormatUtils.fmtDate(new Date())),-1),new Date(),param);
	}

	private int updateCostPrice(String orgUnitNo,Date begDate,Date endDate, Param param) throws AppException {
		if(StringUtils.isNoneBlank(orgUnitNo)){
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("controlUnitNo",param.getControlUnitNo());
			map.put("orgUnitNo",orgUnitNo);
			map.put("begDate",FormatUtils.fmtDate(begDate));
			map.put("endDate",FormatUtils.fmtDate(endDate));
			((ScmCookCostCardPriceDAO)dao).batchAddCostPrice(map);
		}
		return 0;
	}
	@Override
	public boolean modifySalePrice(String orgUnitNo, Param param) throws AppException {
		boolean flag = modifySalePrice(orgUnitNo,CalendarUtil.relativeDate(FormatUtils.parseDate(FormatUtils.fmtDate(new Date())),-1),param);
		if(flag)
			flag = modifySalePrice(orgUnitNo,new Date(),param);
		return flag;
	}

	private boolean modifySalePrice(String orgUnitNo,Date accDate, Param param) throws AppException {
		if(StringUtils.isNotBlank(orgUnitNo)){
			Page page = new Page();
			page.setModelClass(ScmCookCostCard.class);
			page.setShowCount(Integer.MAX_VALUE);
			List<ScmCookCostCard> scmCookCostCardList = scmCookCostCardBiz.findPage(page, param);
			if(scmCookCostCardList != null && !scmCookCostCardList.isEmpty()){
				List<Long> cookList = new ArrayList();
				for(ScmCookCostCard scmCookCostCard : scmCookCostCardList){
					cookList.add(scmCookCostCard.getCookId());
				}
				String fbmOrgUnitNo = orgUnitNo;
				String fbmControlUnitNo = param.getControlUnitNo();
				ScmResOrgUnitMap scmResOrgUnitMap = scmResOrgUnitMapBiz.selectByResOrgUnit(orgUnitNo, param);
				if(scmResOrgUnitMap!=null && !scmResOrgUnitMap.isShared())
					fbmOrgUnitNo = scmResOrgUnitMap.getFbmControlUnitNo();
				List<FbmCookResInfo2> fbmCookResInfoList = fbmCookResInfoBiz.selectCookSalePrice(fbmOrgUnitNo,fbmControlUnitNo, orgUnitNo, accDate, cookList, param);
				if(fbmCookResInfoList!=null && !fbmCookResInfoList.isEmpty()) {
					List<ScmCookCostCardPrice> scmCookCostCardPriceList = new ArrayList();
					for(FbmCookResInfo2 fbmCookResInfo:fbmCookResInfoList) {
						ScmCookCostCardPrice scmCookCostCardPrice = new ScmCookCostCardPrice(true);
						BeanUtil.copyProperties(scmCookCostCardPrice, fbmCookResInfo);
						scmCookCostCardPriceList.add(scmCookCostCardPrice);
					}
					this.batchAddSalePrice(scmCookCostCardPriceList, param);
				}
				return true;
			}
		}
		return false;
	}

	@Override
	public ScmCookCostCardPrice selectByScmCookCostCardPrice(ScmCookCostCardPrice scmCookCostCardPrice, Param param)
			throws AppException {
		if(scmCookCostCardPrice != null){
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("orgUnitNo", scmCookCostCardPrice.getOrgUnitNo());
			map.put("cookId",scmCookCostCardPrice.getCookId());
			map.put("accDate",FormatUtils.fmtDate(scmCookCostCardPrice.getAccDate()));
			return ((ScmCookCostCardPriceDAO)dao).selectByScmCookCostCardPrice(map);
		}
		return null;
	}

	private void batchAddSalePrice(List<ScmCookCostCardPrice> scmCookCostCardPriceList, Param param) throws AppException{
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("scmCookCostCardPriceList", scmCookCostCardPriceList);
		((ScmCookCostCardPriceDAO)dao).batchAddSalePrice(map);
		
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
			((ScmCookCostCardPriceDAO)dao).addPeriodSalePrice(map);
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
