
package com.armitage.server.iscm.basedata.service.impl;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.basedata.dao.ScmAuditMsgTempletChannelDAO;
import com.armitage.server.iscm.basedata.model.ScmAuditMsgTempletChannel;
import com.armitage.server.iscm.basedata.service.ScmAuditMsgTempletChannelBiz;
import org.springframework.stereotype.Service;

@Service("scmAuditMsgTempletChannelBiz")
public class ScmAuditMsgTempletChannelBizImpl extends BaseBizImpl<ScmAuditMsgTempletChannel> implements ScmAuditMsgTempletChannelBiz {

	@Override
	public List<ScmAuditMsgTempletChannel> selectByTempetId(long templetId, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("templetId", templetId);
		return ((ScmAuditMsgTempletChannelDAO)dao).selectByTempetId(map);
	}

}
