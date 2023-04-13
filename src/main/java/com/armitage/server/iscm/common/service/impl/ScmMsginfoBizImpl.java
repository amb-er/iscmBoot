package com.armitage.server.iscm.common.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.armitage.server.activity.util.AuditMsgUtil;
import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Page;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.base.model.QueryParam;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.util.ClassUtils;
import com.armitage.server.common.util.FormatUtils;
import com.armitage.server.iscm.common.model.ScmMsginfo;
import com.armitage.server.iscm.common.model.ScmMsginfoAdvQuery;
import com.armitage.server.iscm.common.service.ScmMsginfoBiz;
import org.springframework.stereotype.Service;

@Service("scmMsginfoBiz")
public class ScmMsginfoBizImpl extends BaseBizImpl<ScmMsginfo> implements ScmMsginfoBiz{

	@Override
	public List<ScmMsginfo> queryMsgList(Page page, Param param) throws AppException {
		if (page.getModel() != null) {
			if (page.getModel() instanceof ScmMsginfoAdvQuery) {
				ScmMsginfoAdvQuery scmMsginfoAdvQuery = (ScmMsginfoAdvQuery) page.getModel();
				if (page.getParamOr() != null) {
					Map<String, QueryParam> paramOr = page.getParamOr();
					if (paramOr != null) {
						QueryParam queryParam = paramOr.get((ClassUtils.getFinalModelSimpleName(ScmMsginfo.class) + "." + ScmMsginfo.FN_SUBJECT));
						if (queryParam != null) {
							scmMsginfoAdvQuery.setSubject(queryParam.getValue());
						}
					}
				}
				if (scmMsginfoAdvQuery.getSendTim_() != null) {
					Calendar calendar = Calendar.getInstance();
			        calendar.setTime(scmMsginfoAdvQuery.getSendTim_());
			        calendar.add(Calendar.DATE, +1);
			        Date time = calendar.getTime();
			        scmMsginfoAdvQuery.setSendTim_(time);
				}
				if (scmMsginfoAdvQuery.getGenTime_() != null) {
					Calendar calendar = Calendar.getInstance();
			        calendar.setTime(scmMsginfoAdvQuery.getGenTime_());
			        calendar.add(Calendar.DATE, +1);
			        Date time = calendar.getTime();
			        scmMsginfoAdvQuery.setGenTime_(time);
				}
				scmMsginfoAdvQuery.setState(page.getSqlCondition());
				return AuditMsgUtil.queryMsgList(scmMsginfoAdvQuery, param);
			}
		}
		return null;
	}
}
