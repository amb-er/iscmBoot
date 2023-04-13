package com.armitage.server.api.business.security.controller;


import java.util.ArrayList;
import java.util.List;

import com.armitage.server.api.common.ApiIntegratedRequest;
import com.armitage.server.api.common.ApiLogicSymbol;
import com.armitage.server.api.common.ApiVersion;
import com.armitage.server.api.common.PageNum;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.armitage.server.api.business.security.params.CheckUsrParams;
import com.armitage.server.api.business.security.params.CheckUsrParamsApi;
import com.armitage.server.api.business.security.params.UsrFavoritesParams;
import com.armitage.server.api.business.security.params.UsrFavoritesParamsApi;
import com.armitage.server.api.business.security.params.UsrPendingBillQueryParams;
import com.armitage.server.api.business.security.params.UsrPendingBillQueryParamsApi;
import com.armitage.server.api.business.security.params.UsrPrivParams;
import com.armitage.server.api.business.security.params.UsrPrivParamsApi;
import com.armitage.server.api.business.security.params.UsrWechatBindingParams;
import com.armitage.server.api.business.security.params.UsrWechatBindingParamsApi;
import com.armitage.server.api.business.security.params.UsrWechatBindingQueryParams;
import com.armitage.server.api.business.security.params.UsrWechatBindingQueryParamsApi;
import com.armitage.server.api.business.security.params.UsrWechatUnbindingParams;
import com.armitage.server.api.business.security.params.UsrWechatUnbindingParamsApi;
import com.armitage.server.api.business.security.result.CheckUsrResult;
import com.armitage.server.api.business.security.result.CheckUsrResultApi;
import com.armitage.server.api.business.security.result.LoginOrgUnitResult;
import com.armitage.server.api.business.security.result.LoginOrgUnitResultApi;
import com.armitage.server.api.business.security.result.UsrBillQueryResult;
import com.armitage.server.api.business.security.result.UsrBillQueryResultApi;
import com.armitage.server.api.business.security.result.UsrFavoritesResultApi;
import com.armitage.server.api.business.security.result.UsrPrivResult;
import com.armitage.server.api.business.security.result.UsrPrivResultApi;
import com.armitage.server.api.business.security.result.UsrWechatBindingQueryResult;
import com.armitage.server.api.business.security.result.UsrWechatBindingQueryResultApi;
import com.armitage.server.api.business.security.result.UsrWechatBindingResultApi;
import com.armitage.server.api.business.security.result.UsrWechatUnbindingResultApi;
import com.armitage.server.common.base.model.IntegratedRequest;
import com.armitage.server.common.base.model.LogicSymbol;
import com.armitage.server.common.base.model.Page;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.security.SecurityUtils;
import com.armitage.server.common.util.AppContextUtil;
import com.armitage.server.common.util.ParamHelper;
import com.armitage.server.common.util.VarConstant;
import com.armitage.server.iscm.basedata.model.ScmBillPending2;
import com.armitage.server.iscm.basedata.model.ScmBillPendingAdvQuery;
import com.armitage.server.iscm.basedata.service.ScmBillPendingBiz;
import com.armitage.server.system.model.OrgBaseUnit2;
import com.armitage.server.system.service.OrgUnitBiz;
import com.armitage.server.user.model.ModuleWSBean;
import com.armitage.server.user.model.Usr;
import com.armitage.server.user.model.UsrFavorites;
import com.armitage.server.user.service.UsrBiz;
import com.armitage.server.user.service.UsrFavoritesBiz;
import com.armitage.server.user.service.UsrWechatBiz;
import com.armitage.server.webservice.model.CheckUserParams;
import com.armitage.server.webservice.model.CheckUserResult;
import com.armitage.server.webservice.model.DoUsrWechatBindingParams;
import com.armitage.server.webservice.model.DoUsrWechatUnbindingParams;
import com.armitage.server.webservice.model.QueryUsrWechatBindingGet;
import com.armitage.server.webservice.model.QueryUsrWechatBindingParams;
import com.armitage.server.webservice.model.QueryUsrWechatBindingResult;
import com.armitage.server.webservice.model.UpdateUsrFavoritesParams;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/api/usr")
@Api(tags="用户相关接口")
public class UsrController {
	private UsrBiz usrBiz = (UsrBiz) AppContextUtil.getBean("usrBiz");
	private OrgUnitBiz orgUnitBiz = (OrgUnitBiz) AppContextUtil.getBean("orgUnitBiz");
	private UsrFavoritesBiz usrFavoritesBiz = (UsrFavoritesBiz) AppContextUtil.getBean("usrFavoritesBiz");
	private UsrWechatBiz usrWechatBiz = (UsrWechatBiz) AppContextUtil.getBean("usrWechatBiz");
	private ScmBillPendingBiz scmBillPendingBiz = (ScmBillPendingBiz) AppContextUtil.getBean("scmBillPendingBiz");
	
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/doCheckUser", method=RequestMethod.POST)
    @ApiOperation(value="用户检查", consumes="application/json")
    public CheckUsrResultApi doCheckUser(@RequestBody CheckUsrParamsApi params) {
		CheckUsrResultApi result = new CheckUsrResultApi();//返回结果
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);

    	try {
    		SecurityUtils.verify(integratedRequest);
    		CheckUsrParams checkUsrParams = params.getParams();
	        if(checkUsrParams==null){
	            result.setErrCode("-1");
	            result.setErrText("params参数不能为空");
	            throw new AppException("webservice.params.null");
	        }
	        CheckUserParams checkUserParams = new CheckUserParams();
	        checkUserParams.setCode(checkUsrParams.getUsrCode());
	        checkUserParams.setPass(checkUsrParams.getPass());
	        CheckUserResult checkUserResult = usrBiz.checkUser(checkUserParams, ParamHelper.createParam(integratedRequest, "doCheckUser"));
	        if(checkUserResult!=null && checkUserResult.getUsr()!=null){
	        	if(StringUtils.isNotBlank(checkUserResult.getErrText())) {
	        		result.setErrCode("-1");
	        		result.setErrText(checkUserResult.getErrText());
	        	}else {
		        	CheckUsrResult checkUsrResult = new CheckUsrResult();
		        	checkUsrResult.setUsrCode(checkUserResult.getUsr().getCode());
		        	checkUsrResult.setUsrName(checkUserResult.getUsr().getName());
		        	result.setResult(checkUsrResult);
	        	}
	        }
    	} catch (Exception e) {
    		result.setErrCode("-1");
    		result.setErrText(Message.getMessage(VarConstant.DEFAULT_LOCALE, e.getMessage(), null));
		}
        return result;
    }
	
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/queryLoginOrgUnit", method=RequestMethod.POST)
    @ApiOperation(value="获取用户可登录组织", consumes="application/json")
    public LoginOrgUnitResultApi queryLoginOrgUnit(@RequestBody CheckUsrParamsApi params) {
		LoginOrgUnitResultApi result = new LoginOrgUnitResultApi();//返回结果
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);

    	try {
    		SecurityUtils.verify(integratedRequest);
    		CheckUsrParams checkUsrParams = params.getParams();
	        if(checkUsrParams==null){
	            result.setErrCode("-1");
	            result.setErrText("params参数不能为空");
	            throw new AppException("webservice.params.null");
	        }
	        CheckUserParams checkUserParams = new CheckUserParams();
	        checkUserParams.setCode(checkUsrParams.getUsrCode());
	        Param param = ParamHelper.createParam(integratedRequest, "queryLoginOrgUnit");
	        Page page = new Page();
	        page.setModelClass(OrgBaseUnit2.class);
	        page.setShowCount(Integer.MAX_VALUE);
	        List<OrgBaseUnit2> orgBaseUnitList = orgUnitBiz.findLoginOrgUnit(checkUserParams.getCode(), page, param);
	        if(orgBaseUnitList!=null && !orgBaseUnitList.isEmpty()){
	        	List< LoginOrgUnitResult > resultList = new ArrayList<>();
	        	for(OrgBaseUnit2 orgBaseUnit:orgBaseUnitList){
	        		LoginOrgUnitResult loginOrgUnitResult = new LoginOrgUnitResult();
	        		BeanUtils.copyProperties(orgBaseUnit, loginOrgUnitResult);
	        		loginOrgUnitResult.setCu(orgBaseUnit.isIsCu());
	        		resultList.add(loginOrgUnitResult);
	        	}
	        	result.setResultList(resultList);
	        }else{
	    		result.setErrCode("-1");
	    		result.setErrText("没有可登录的组织");
	        }
    	} catch (Exception e) {
    		result.setErrCode("-1");
    		result.setErrText(Message.getMessage(VarConstant.DEFAULT_LOCALE, e.getMessage(), null));
		}
        return result;
    }
	
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/queryUsrPrivileges", method=RequestMethod.POST)
    @ApiOperation(value="获取用户权限", consumes="application/json")
    public UsrPrivResultApi queryUsrPrivileges(@RequestBody UsrPrivParamsApi params) {
		UsrPrivResultApi result = new UsrPrivResultApi();//返回结果
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);

    	try {
    		SecurityUtils.verify(integratedRequest);
    		UsrPrivParams usrPrivParams = params.getParams();
	        if(usrPrivParams==null){
	            result.setErrCode("-1");
	            result.setErrText("params参数不能为空");
	            throw new AppException("webservice.params.null");
	        }
	        Param param = ParamHelper.createParam(integratedRequest,"queryUsrPrivileges");
			
			Usr usr = usrBiz.selectByCode(usrPrivParams.getUsrCode(), param);
			if (usr == null) {
				result.setErrCode("-1");
	    		result.setErrText("用户代码无效");
			}else{
				param.setUsrCode(usrPrivParams.getUsrCode());
				param.setProductCode(integratedRequest.getProductCode());
				List<Long> systemList = new ArrayList<>();
				systemList.add(170L);
				param.setSystemIdList(systemList);
				ModuleWSBean bean =usrBiz.queryUsrPrivileges(param);
				UsrPrivResult usrPrivResult = new UsrPrivResult();
				usrPrivResult.setModulelist(bean.getModulelist());
				usrPrivResult.setModuleOperationList(bean.getModuleOperationList());
				usrPrivResult.setUsrFavoriteList(bean.getList());
				result.setResult(usrPrivResult);
			}
    	} catch (Exception e) {
    		result.setErrCode("-1");
    		result.setErrText(Message.getMessage(VarConstant.DEFAULT_LOCALE, e.getMessage(), null));
		}
        return result;
    }
	
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/doSaveUsrFavorites", method=RequestMethod.POST)
    @ApiOperation(value="保存用户快捷操作", consumes="application/json")
    public UsrFavoritesResultApi doSaveUsrFavorites(@RequestBody UsrFavoritesParamsApi params) {
		UsrFavoritesResultApi result = new UsrFavoritesResultApi();//返回结果
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);

    	try {
    		SecurityUtils.verify(integratedRequest);
    		UsrFavoritesParams usrFavoritesParams = params.getParams();
	        if(usrFavoritesParams==null){
	            result.setErrCode("-1");
	            result.setErrText("params参数不能为空");
	            throw new AppException("webservice.params.null");
	        }
	        Param param = ParamHelper.createParam(integratedRequest, "doSaveUsrFavorites");
	        Usr usr = usrBiz.selectByCode(usrFavoritesParams.getUsrCode(), param);
	        if (usr == null) {
				result.setErrCode("-1");
	    		result.setErrText("用户代码无效");
	        }else{
		        UpdateUsrFavoritesParams updateUsrFavoritesParams = new UpdateUsrFavoritesParams();
		        updateUsrFavoritesParams.setOrgUnitNo(param.getOrgUnitNo());
		        updateUsrFavoritesParams.setUsrCode(usrFavoritesParams.getUsrCode());
		        List<Long> systemIdList = new ArrayList<>();
		        systemIdList.add(170L);
		        updateUsrFavoritesParams.setSystemIdList(systemIdList);
		        List<UsrFavorites> usrFavoriteList = new ArrayList<>();
		        for(long moduleId:usrFavoritesParams.getModuleIdList()){
			        UsrFavorites usrFavorites = new UsrFavorites();
			        usrFavorites.setUsrId(usr.getId());
			        usrFavorites.setOrgUnitNo(param.getOrgUnitNo());
			        usrFavorites.setModuleId(moduleId);
			        usrFavoriteList.add(usrFavorites);
		        }
		        updateUsrFavoritesParams.setUsrFavoriteList(usrFavoriteList);
		        usrFavoritesBiz.updateUsrFavorites(updateUsrFavoritesParams, param);
	        }
    	} catch (Exception e) {
    		result.setErrCode("-1");
    		result.setErrText(Message.getMessage(VarConstant.DEFAULT_LOCALE, e.getMessage(), null));
		}
        return result;
    }
	
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/doUsrWechatBinding", method=RequestMethod.POST)
    @ApiOperation(value="微信公众号绑定", consumes="application/json")
    public UsrWechatBindingResultApi doUsrWechatBinding(@RequestBody UsrWechatBindingParamsApi params) {
		UsrWechatBindingResultApi result = new UsrWechatBindingResultApi();//返回结果
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);

    	try {
    		SecurityUtils.verify(integratedRequest);
    		UsrWechatBindingParams usrWechatBindingParams = params.getParams();
	        if(usrWechatBindingParams==null){
	            result.setErrCode("-1");
	            result.setErrText("params参数不能为空");
	            throw new AppException("webservice.params.null");
	        }
        	DoUsrWechatBindingParams doUsrWechatBindingParams = new DoUsrWechatBindingParams();
        	BeanUtils.copyProperties(usrWechatBindingParams, doUsrWechatBindingParams);
        	doUsrWechatBindingParams.setUserCode(usrWechatBindingParams.getUsrCode());
        	usrWechatBiz.doUsrWechatBinding(doUsrWechatBindingParams,ParamHelper.createParam(integratedRequest,"doUsrWechatBinding"));
    	} catch (Exception e) {
    		result.setErrCode("-1");
    		result.setErrText(Message.getMessage(VarConstant.DEFAULT_LOCALE, e.getMessage(), null));
		}
        return result;
    }
	
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/doUsrWechatUnbinding", method=RequestMethod.POST)
    @ApiOperation(value="微信公众号解绑", consumes="application/json")
    public UsrWechatUnbindingResultApi doUsrWechatUnbinding(@RequestBody UsrWechatUnbindingParamsApi params) {
		//返回结果
		UsrWechatUnbindingResultApi result = new UsrWechatUnbindingResultApi();
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);

    	try {
    		SecurityUtils.verify(integratedRequest);
    		UsrWechatUnbindingParams usrWechatUnbindingParams = params.getParams();
	        if(usrWechatUnbindingParams==null){
	            result.setErrCode("-1");
	            result.setErrText("params参数不能为空");
	            throw new AppException("webservice.params.null");
	        }
	        DoUsrWechatUnbindingParams doUsrWechatUnbindingParams = new DoUsrWechatUnbindingParams();
        	BeanUtils.copyProperties(usrWechatUnbindingParams, doUsrWechatUnbindingParams);
        	doUsrWechatUnbindingParams.setUserCode(usrWechatUnbindingParams.getUsrCode());
        	usrWechatBiz.doUsrWechatUnbinding(doUsrWechatUnbindingParams,ParamHelper.createParam(integratedRequest,"doUsrWechatUnbinding"));
    	} catch (Exception e) {
    		result.setErrCode("-1");
    		result.setErrText(Message.getMessage(VarConstant.DEFAULT_LOCALE, e.getMessage(), null));
		}
        return result;
    }
	
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/queryUsrWechatBinding", method=RequestMethod.POST)
    @ApiOperation(value="查询用户是否已绑定微信公众号", consumes="application/json")
    public UsrWechatBindingQueryResultApi queryUsrWechatBinding(@RequestBody UsrWechatBindingQueryParamsApi params) {
		UsrWechatBindingQueryResultApi result = new UsrWechatBindingQueryResultApi();//返回结果
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);

    	try {
    		SecurityUtils.verify(integratedRequest);
    		UsrWechatBindingQueryParams usrWechatBindingQueryParams = params.getParams();
	        if(usrWechatBindingQueryParams==null){
	            result.setErrCode("-1");
	            result.setErrText("params参数不能为空");
	            throw new AppException("webservice.params.null");
	        }
	        QueryUsrWechatBindingParams queryUsrWechatBindingParams = new QueryUsrWechatBindingParams();
        	BeanUtils.copyProperties(usrWechatBindingQueryParams, queryUsrWechatBindingParams);
        	QueryUsrWechatBindingResult queryUsrWechatBindingResult = usrWechatBiz.queryUsrWechatBinding(queryUsrWechatBindingParams,ParamHelper.createParam(integratedRequest,"doUsrWechatUnbinding"));
        	if(queryUsrWechatBindingResult!=null && queryUsrWechatBindingResult.getUserList()!=null && !queryUsrWechatBindingResult.getUserList().isEmpty()) {
        		List<UsrWechatBindingQueryResult> resultList = new ArrayList();
        		for(QueryUsrWechatBindingGet queryUsrWechatBindingGet:queryUsrWechatBindingResult.getUserList()) {
        			UsrWechatBindingQueryResult usrWechatBindingQueryResult = new UsrWechatBindingQueryResult();
        			BeanUtils.copyProperties(queryUsrWechatBindingGet, usrWechatBindingQueryResult);
        			resultList.add(usrWechatBindingQueryResult);
        		}
        		result.setResultList(resultList);
        	}
    	} catch (Exception e) {
    		result.setErrCode("-1");
    		result.setErrText(Message.getMessage(VarConstant.DEFAULT_LOCALE, e.getMessage(), null));
		}
        return result;
    }
	
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/queryUsrPendingBill", method=RequestMethod.POST)
    @ApiOperation(value="查询用户待审单据", consumes="application/json")
    public UsrBillQueryResultApi queryUsrPendingBill(@RequestBody UsrPendingBillQueryParamsApi params) {
		UsrBillQueryResultApi result = new UsrBillQueryResultApi();//返回结果
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        ApiLogicSymbol apiLogicSymbol = params.getLogicSymbol();
        LogicSymbol logicSymbol = new LogicSymbol();
        BeanUtils.copyProperties(apiLogicSymbol, logicSymbol);
        int pageIndex = -1;
        PageNum pageNum = params.getPageNum();
        if(pageNum != null && pageNum.getPageNum() != 0) {
        	pageIndex = pageNum.getPageNum();
        }

    	try {
    		SecurityUtils.verify(integratedRequest);
    		UsrPendingBillQueryParams usrPendingBillQueryParams = params.getParams();
	        if(usrPendingBillQueryParams==null){
	            result.setErrCode("-1");
	            result.setErrText("params参数不能为空");
	            throw new AppException("webservice.params.null");
	        }
	        
	        ScmBillPendingAdvQuery scmBillPendingAdvQuery = new ScmBillPendingAdvQuery(); 
	        BeanUtils.copyProperties(usrPendingBillQueryParams, scmBillPendingAdvQuery);
	        scmBillPendingAdvQuery.setType("D");
	        List<ScmBillPending2> rntList = scmBillPendingBiz.queryPendingBill(scmBillPendingAdvQuery,pageIndex,ParamHelper.createParam(integratedRequest,"queryUsrPendingBill"));
        	if(rntList!=null && !rntList.isEmpty()) {
        		List<UsrBillQueryResult> resultList = new ArrayList();
        		for(ScmBillPending2 scmBillPending:rntList) {
        			UsrBillQueryResult usrBillQueryResult = new UsrBillQueryResult();
        			BeanUtils.copyProperties(scmBillPending, usrBillQueryResult);
        			resultList.add(usrBillQueryResult);
        		}
        		result.setResultList(resultList);
        	}
    	} catch (Exception e) {
    		result.setErrCode("-1");
    		result.setErrText(Message.getMessage(VarConstant.DEFAULT_LOCALE, e.getMessage(), null));
		}
        return result;
    }
	
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/queryUsrApprovedBill", method=RequestMethod.POST)
    @ApiOperation(value="查询用户已审单据", consumes="application/json")
    public UsrBillQueryResultApi queryUsrApprovedBill(@RequestBody UsrPendingBillQueryParamsApi params) {
		UsrBillQueryResultApi result = new UsrBillQueryResultApi();//返回结果
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        ApiLogicSymbol apiLogicSymbol = params.getLogicSymbol();
        LogicSymbol logicSymbol = new LogicSymbol();
        BeanUtils.copyProperties(apiLogicSymbol, logicSymbol);
        int pageIndex = -1;
        PageNum pageNum = params.getPageNum();
        if(pageNum != null && pageNum.getPageNum() != 0) {
        	pageIndex = pageNum.getPageNum();
        }

    	try {
    		SecurityUtils.verify(integratedRequest);
    		UsrPendingBillQueryParams usrPendingBillQueryParams = params.getParams();
	        if(usrPendingBillQueryParams==null){
	            result.setErrCode("-1");
	            result.setErrText("params参数不能为空");
	            throw new AppException("webservice.params.null");
	        }
	        
	        ScmBillPendingAdvQuery scmBillPendingAdvQuery = new ScmBillPendingAdvQuery(); 
	        BeanUtils.copyProperties(usrPendingBillQueryParams, scmBillPendingAdvQuery);
	        scmBillPendingAdvQuery.setType("A");
	        List<ScmBillPending2> rntList = scmBillPendingBiz.queryPendingBill(scmBillPendingAdvQuery,pageIndex,ParamHelper.createParam(integratedRequest,"queryUsrApprovedBill"));
        	if(rntList!=null && !rntList.isEmpty()) {
        		List<UsrBillQueryResult> resultList = new ArrayList();
        		for(ScmBillPending2 scmBillPending:rntList) {
        			UsrBillQueryResult usrBillQueryResult = new UsrBillQueryResult();
        			BeanUtils.copyProperties(scmBillPending, usrBillQueryResult);
        			resultList.add(usrBillQueryResult);
        		}
        		result.setResultList(resultList);
        	}
    	} catch (Exception e) {
    		result.setErrCode("-1");
    		result.setErrText(Message.getMessage(VarConstant.DEFAULT_LOCALE, e.getMessage(), null));
		}
        return result;
    }
	
}
	
	
