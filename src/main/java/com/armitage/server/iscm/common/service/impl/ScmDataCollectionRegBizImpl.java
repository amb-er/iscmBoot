package com.armitage.server.iscm.common.service.impl;

import java.util.List;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.CommonBean;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.util.AppContextUtil;
import com.armitage.server.common.util.ClassUtils;
import com.armitage.server.common.util.LangUtil;
import com.armitage.server.common.util.VarConstant;
import com.armitage.server.iscm.common.model.ScmDataCollectionReg;
import com.armitage.server.iscm.common.model.ScmDataCollectionReg2;
import com.armitage.server.iscm.common.model.ScmDataCollectionStepState2;
import com.armitage.server.iscm.common.service.ScmDataCollectionRegBiz;
import com.armitage.server.iscm.common.service.ScmDataCollectionStepStateBiz;
import com.armitage.server.system.dao.CommonLangDAO;
import com.armitage.server.system.model.CommonLang;
import org.springframework.stereotype.Service;

@Service("scmDataCollectionRegBiz")
public class ScmDataCollectionRegBizImpl extends BaseBizImpl<ScmDataCollectionReg> implements ScmDataCollectionRegBiz {
	private ScmDataCollectionStepStateBiz scmDataCollectionStepStateBiz;
	
	public void setScmDataCollectionStepStateBiz(ScmDataCollectionStepStateBiz scmDataCollectionStepStateBiz) {
		this.scmDataCollectionStepStateBiz = scmDataCollectionStepStateBiz;
	}

	@Override
	public CommonBean findAsynStep(ScmDataCollectionReg2 scmDataCollectionReg, Param param) throws AppException {
		//获取数据汇集步骤
		List<ScmDataCollectionStepState2> scmStepStateList = scmDataCollectionStepStateBiz.findAsynStep(scmDataCollectionReg.getOrgUnitNo(),scmDataCollectionReg.getCategory(),scmDataCollectionReg.getReqNo(),param);
		
		if (!StringUtils.equalsIgnoreCase(Locale.CHINA.toString(), param.getLang())) {
			if (scmStepStateList != null && !scmStepStateList.isEmpty()) {
				for (ScmDataCollectionStepState2 scmDataCollectionStepState : scmStepStateList) {
					// 加载多语言数据
					if (VarConstant.HAVE_LANG_TABLE_SET.contains(ClassUtils.getFinalModelSimpleName(ScmDataCollectionReg.class))) {
						CommonLangDAO commonLangDAO = (CommonLangDAO) AppContextUtil.getBean("commonLangDAO");
						List<CommonLang> langList = commonLangDAO
								.loadLang(ClassUtils.getFinalModelSimpleName(ScmDataCollectionReg.class), scmDataCollectionStepState.getStepId());
						scmDataCollectionStepState.setLangList(langList);
						scmDataCollectionStepState.setName(LangUtil.getLang(scmDataCollectionStepState, ScmDataCollectionStepState2.FN_NAME, param));
					}

				}
			}
		}
		
		CommonBean commonBean = new CommonBean();
		commonBean.setList(scmStepStateList);
		return commonBean;
	}

	@Override
	public ScmDataCollectionStepState2 selectByStepIdAndOrgUnitNo(ScmDataCollectionStepState2 scmDataCollectionStepState, Param param) throws AppException {
		if(scmDataCollectionStepState !=null){
			return scmDataCollectionStepStateBiz.selectByStepIdAndOrgUnitNo(scmDataCollectionStepState.getOrgUnitNo(),scmDataCollectionStepState.getReqNo(), scmDataCollectionStepState.getStepId(), param);
		}
		return null;
	}
		
}
