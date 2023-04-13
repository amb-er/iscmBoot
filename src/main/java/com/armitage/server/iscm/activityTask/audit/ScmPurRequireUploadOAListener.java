package com.armitage.server.iscm.activityTask.audit;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.armitage.server.common.base.model.IntegratedRequest;
import com.armitage.server.common.base.model.Page;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.base.model.QueryParam;
import com.armitage.server.common.util.AppContextUtil;
import com.armitage.server.common.util.ParamHelper;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurRequire2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.service.ScmPurRequireBiz;

@Component
public class ScmPurRequireUploadOAListener implements Serializable, TaskListener {

	private static final long serialVersionUID = 7960387497099642910L;

	private static Log log = LogFactory.getLog(ScmPurRequireUploadOAListener.class);
	
	private static ScmPurRequireBiz scmPurRequireBiz = (ScmPurRequireBiz) AppContextUtil.getBean("scmPurRequireBiz");

	@Override
	public void notify(DelegateTask delegateTask) {

		String eventName = delegateTask.getEventName();
		if ("create".endsWith(eventName)) {
			System.out.println("create=========");
		} else if ("assignment".endsWith(eventName)) {
			System.out.println("assignment========");
		} else if ("complete".endsWith(eventName)) {
			System.out.println("complete===========");
		} else if ("delete".endsWith(eventName)) {
			System.out.println("delete=============");
		}
	}

	public void execute(DelegateTask delegateTask) {

		String eventName = delegateTask.getEventName();
		Map<String, Object> map = delegateTask.getVariables();
		if(map == null || map.isEmpty()){
			log.info("未设置流程变量！");
			return;
		}
		if ("create".endsWith(eventName)) {
			String prNo = "";
			if (map.containsKey("prNo")) {
				prNo = String.valueOf(map.get("prNo"));
			} else {
				log.info("请购单上传OA失败：单据编号为空！");
				return;
			}
			String orgUnitNo = "";
			if (map.containsKey("orgUnitNo")) {
				orgUnitNo = String.valueOf(map.get("orgUnitNo"));
			} else {
				log.info("请购单上传OA失败：申请组织为空！");
				return;
			}
			IntegratedRequest integratedRequest = new IntegratedRequest();
			integratedRequest.setOrgUnitNo(orgUnitNo);
			Param param = ParamHelper.createParam(integratedRequest, "");
			
			Page page=new Page();
			page.setModelClass(ScmPurRequire2.class);
			page.setShowCount(Integer.MAX_VALUE);
			page.getParam().put(
					ScmPurRequire2.FN_PRNO,
					new QueryParam(ScmPurRequire2.FN_PRNO, QueryParam.QUERY_EQ,
							prNo));
			
			List<ScmPurRequire2> scmPurRequireList =scmPurRequireBiz.findPage(page, param);
			if(scmPurRequireList!=null && !scmPurRequireList.isEmpty()){
				ScmPurRequire2 require=scmPurRequireList.get(0);
				if(require == null){
					log.info("未找到当前请购单据！");
					return;
				}
				String outAuditType = "1";
				if(StringUtils.isNotBlank(require.getOtherAuditNo())){
					outAuditType = "5";
				}
				require.setOutAuditType(outAuditType);
				scmPurRequireBiz.updateOutAudit(require, param);
			}
		}
	}
}
