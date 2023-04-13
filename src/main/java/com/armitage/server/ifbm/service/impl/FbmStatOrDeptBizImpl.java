package com.armitage.server.ifbm.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Page;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.util.BeanUtil;
import com.armitage.server.ifbc.basedata.model.ScmProductionDept;
import com.armitage.server.ifbc.basedata.service.ScmProductionDeptBiz;
import com.armitage.server.ifbm.model.FbmStat;
import com.armitage.server.ifbm.model.FbmStatOrDept;
import com.armitage.server.ifbm.service.FbmStatBiz;
import com.armitage.server.ifbm.service.FbmStatOrDeptBiz;
import org.springframework.stereotype.Service;

@Service("fbmStatOrDeptBiz")
public class FbmStatOrDeptBizImpl extends BaseBizImpl<FbmStatOrDept> implements FbmStatOrDeptBiz {
	private FbmStatBiz fbmStatBiz;
	private ScmProductionDeptBiz scmProductionDeptBiz;
	
	public void setFbmStatBiz(FbmStatBiz fbmStatBiz) {
		this.fbmStatBiz = fbmStatBiz;
	}

	public void setScmProductionDeptBiz(ScmProductionDeptBiz scmProductionDeptBiz) {
		this.scmProductionDeptBiz = scmProductionDeptBiz;
	}


	@Override
	public List queryPageEx(Page page, List<String> arglist, String xmlId, Param param) throws AppException {
		if(arglist!=null && !arglist.isEmpty()) {
			String type = StringUtils.right(arglist.get(0),StringUtils.length(arglist.get(0))-StringUtils.indexOf(arglist.get(0), "=") - 1);
			List<FbmStatOrDept> fbmStatOrDeptList = new ArrayList();
			if(StringUtils.equals("D", type)) {
				List<ScmProductionDept> scmProductionDeptList = scmProductionDeptBiz.findPage(page, param);
				if(scmProductionDeptList!=null && !scmProductionDeptList.isEmpty()) {
					for(ScmProductionDept scmProductionDept:scmProductionDeptList) {
						FbmStatOrDept fbmStatOrDept = new FbmStatOrDept();
						BeanUtil.copyProperties(fbmStatOrDept, scmProductionDept);
						fbmStatOrDeptList.add(fbmStatOrDept);
					}
				}
			}else {
				List<FbmStat> fbmStatList = fbmStatBiz.findPage(page, param);
				if(fbmStatList!=null && !fbmStatList.isEmpty()) {
					for(FbmStat fbmStat:fbmStatList) {
						FbmStatOrDept fbmStatOrDept = new FbmStatOrDept();
						BeanUtil.copyProperties(fbmStatOrDept, fbmStat);
						fbmStatOrDeptList.add(fbmStatOrDept);
					}
				}
			}
			return fbmStatOrDeptList;
		}
		return null;
	}

}
