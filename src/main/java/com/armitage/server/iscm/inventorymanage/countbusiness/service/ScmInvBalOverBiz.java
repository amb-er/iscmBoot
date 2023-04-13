package com.armitage.server.iscm.inventorymanage.countbusiness.service;


import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.inventorymanage.countbusiness.model.ScmInvBalOver;
import com.armitage.server.iscm.inventorymanage.countbusiness.model.ScmInvBalOverResult;

public interface ScmInvBalOverBiz extends BaseBiz<ScmInvBalOver> {

	public ScmInvBalOverResult balOver(ScmInvBalOver scmInvBalOver, Param param) throws AppException;

}
