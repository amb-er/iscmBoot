package com.armitage.server.iscm.basedata.service;

import java.util.List;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.basedata.model.PubSysBasicInfo;

public interface PubSysBasicInfoBiz extends BaseBiz<PubSysBasicInfo> {
	public boolean checkTaxRateExist(PubSysBasicInfo pubSysBasicInfo, Param param) throws AppException;
	public PubSysBasicInfo selectByTaxRate(String fRegNo, String fValue, String fMemo, Param param) throws AppException;
	public PubSysBasicInfo selectByValue(String fRegNo, String fValue, Param param) throws AppException;
	public List<PubSysBasicInfo> queryPurRecRateList(Param param) throws AppException;
	public List<PubSysBasicInfo> queryTaxRateList(int pageNum, Param param) throws AppException;
}
