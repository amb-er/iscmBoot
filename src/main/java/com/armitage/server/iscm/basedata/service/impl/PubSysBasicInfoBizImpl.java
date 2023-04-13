package com.armitage.server.iscm.basedata.service.impl;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Page;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.cache.ModelCacheMana;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.basedata.dao.PubSysBasicInfoDAO;
import com.armitage.server.iscm.basedata.model.PubSysBasicInfo;
import com.armitage.server.iscm.basedata.service.PubSysBasicInfoBiz;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvAccreditWh;
import com.armitage.server.system.model.Employee;
import org.springframework.stereotype.Service;

@Service("pubSysBasicInfoBiz")
public class PubSysBasicInfoBizImpl extends BaseBizImpl<PubSysBasicInfo> implements PubSysBasicInfoBiz {

	@Override
	public boolean checkTaxRateExist(PubSysBasicInfo pubSysBasicInfo, Param param) throws AppException {
		HashMap<String, Object> map = new HashMap<>();
		map.put("fRegNo", pubSysBasicInfo.getFRegNo());
		map.put("fValue", pubSysBasicInfo.getFValue());
		map.put("fMemo", pubSysBasicInfo.getFMemo());
		List<PubSysBasicInfo> list = ((PubSysBasicInfoDAO) dao).selectTaxRate(map);
		if(list != null && !list.isEmpty()){
			return true;
		}
		return false;
	}

	@Override
	public PubSysBasicInfo selectByTaxRate(String fRegNo, String fValue, String fMemo, Param param)
			throws AppException {
		String key = fRegNo + "_" + fValue;
		// 从缓存获取
		if (ModelCacheMana.keyExists(key, modelClass)) {
			Object obj = ModelCacheMana.get(key, modelClass);
			if (obj != null && obj.getClass().equals(modelClass)) {
				return (PubSysBasicInfo) obj;
			}
		}
		HashMap<String, Object> map = new HashMap<>();
		map.put("fRegNo", fRegNo);
		map.put("fValue", fValue);
		map.put("fMemo", fMemo);
		List<PubSysBasicInfo> list = ((PubSysBasicInfoDAO) dao).selectTaxRate(map);
		if(list != null && !list.isEmpty()){
			ModelCacheMana.set(key, list.get(0));
			return list.get(0);
		}
		return null;
	}

	@Override
	public PubSysBasicInfo selectByValue(String fRegNo, String fValue,
			Param param) throws AppException {
		String key = fRegNo + "_" + fValue;
		// 从缓存获取
		if (ModelCacheMana.keyExists(key, modelClass)) {
			Object obj = ModelCacheMana.get(key, modelClass);
			if (obj != null && obj.getClass().equals(modelClass)) {
				return (PubSysBasicInfo) obj;
			}
		}
		HashMap<String, Object> map = new HashMap<>();
		map.put("fRegNo", fRegNo);
		map.put("fValue", fValue);
		List<PubSysBasicInfo> list = ((PubSysBasicInfoDAO) dao).selectTaxRate(map);
		if(list != null && !list.isEmpty()){
			ModelCacheMana.set(key, list.get(0));
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<PubSysBasicInfo> queryPurRecRateList(Param param) throws AppException {
		Page page=new Page();
		page.setModelClass(PubSysBasicInfo.class);
		page.setPagePos(1);
		page.setShowCount(Integer.MAX_VALUE);
		page.setSqlCondition("PubSysBasicInfo.fRegNo='scmAbnRate' and PubSysBasicInfo.fStatus='1'");
		
		return this.findPage(page, param);
	}

	@Override
	public List<PubSysBasicInfo> queryTaxRateList(int pageNum, Param param) throws AppException {
		Page page=new Page();
		if (pageNum == -1) {
			page.setPagePos(1);
			page.setShowCount(Integer.MAX_VALUE);
		} else {
			page.setPagePos(pageNum);
			page.setShowCount(20);
		}
		page.setModelClass(PubSysBasicInfo.class);
		page.setPagePos(1);
		page.setShowCount(Integer.MAX_VALUE);
		page.setSqlCondition("PubSysBasicInfo.fRegNo='TaxRate' and PubSysBasicInfo.fStatus='1'");
		
		return this.findPage(page, param);
	}

}
