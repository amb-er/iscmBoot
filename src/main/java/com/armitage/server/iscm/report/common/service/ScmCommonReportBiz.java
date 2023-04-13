package com.armitage.server.iscm.report.common.service;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.exception.AppException;

public interface ScmCommonReportBiz extends BaseBiz<BaseModel> {
	/**
	 * 通用报表数据查询
	 * @param param 
	 * @return
	 * @throws AppException
	 */
	public List queryCommonReportData(HttpServletRequest request) throws AppException;
}

