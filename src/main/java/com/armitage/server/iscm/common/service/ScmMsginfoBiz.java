package com.armitage.server.iscm.common.service;

import java.util.List;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Page;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.common.model.ScmMsginfo;

public interface ScmMsginfoBiz extends BaseBiz<ScmMsginfo>{

	List queryMsgList(Page page, Param createParam) throws AppException;

}
