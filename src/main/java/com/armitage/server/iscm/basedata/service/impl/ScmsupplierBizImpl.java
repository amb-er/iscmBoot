package com.armitage.server.iscm.basedata.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.armitage.server.api.business.basedata.params.SupplierAddParams;
import com.armitage.server.api.business.basedata.params.SupplierUpdateParams;
import com.armitage.server.api.business.basedata.params.SupplierOAParams;
import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.CommonBean;
import com.armitage.server.common.base.model.InterfaceParam;
import com.armitage.server.common.base.model.OperationParam;
import com.armitage.server.common.base.model.Page;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.base.model.QueryParam;
import com.armitage.server.common.cache.ModelCacheMana;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.util.BigDecimalUtil;
import com.armitage.server.common.util.CalendarUtil;
import com.armitage.server.common.util.ClassUtils;
import com.armitage.server.common.util.JSONUtils;
import com.armitage.server.iscm.basedata.dao.ScmsupplierDAO;
import com.armitage.server.iscm.basedata.model.PubSysBasicInfo;
import com.armitage.server.iscm.basedata.model.ScmIndustryGroup;
import com.armitage.server.iscm.basedata.model.ScmIndustryGroupQualifyType2;
import com.armitage.server.iscm.basedata.model.ScmQualifieInfo2;
import com.armitage.server.iscm.basedata.model.ScmSupplierDemander;
import com.armitage.server.iscm.basedata.model.ScmSupplierPlatFormUser;
import com.armitage.server.iscm.basedata.model.ScmSupplierQualifyType;
import com.armitage.server.iscm.basedata.model.ScmSupplierRegInvitation;
import com.armitage.server.iscm.basedata.model.ScmSupplierUnified;
import com.armitage.server.iscm.basedata.model.Scmsupplier2;
import com.armitage.server.iscm.basedata.model.ScmsupplierAdvQuery;
import com.armitage.server.iscm.basedata.model.Scmsupplierbank;
import com.armitage.server.iscm.basedata.model.Scmsuppliercompanyinfo2;
import com.armitage.server.iscm.basedata.model.Scmsuppliergroup;
import com.armitage.server.iscm.basedata.model.Scmsuppliergroup2;
import com.armitage.server.iscm.basedata.model.Scmsuppliergroupdetail;
import com.armitage.server.iscm.basedata.model.Scmsupplierlinkman;
import com.armitage.server.iscm.basedata.model.Scmsupplierpurchaseinfo2;
import com.armitage.server.iscm.basedata.service.PubSysBasicInfoBiz;
import com.armitage.server.iscm.basedata.service.ScmIndustryGroupBiz;
import com.armitage.server.iscm.basedata.service.ScmIndustryGroupQualifyTypeBiz;
import com.armitage.server.iscm.basedata.service.ScmQualifieInfoBiz;
import com.armitage.server.iscm.basedata.service.ScmSupplierDemanderBiz;
import com.armitage.server.iscm.basedata.service.ScmSupplierQualifyTypeBiz;
import com.armitage.server.iscm.basedata.service.ScmSupplierRegInvitationBiz;
import com.armitage.server.iscm.basedata.service.ScmSupplierUnifiedBiz;
import com.armitage.server.iscm.basedata.service.ScmsupplierBiz;
import com.armitage.server.iscm.basedata.service.ScmsupplierbankBiz;
import com.armitage.server.iscm.basedata.service.ScmsuppliercompanyinfoBiz;
import com.armitage.server.iscm.basedata.service.ScmsuppliergroupBiz;
import com.armitage.server.iscm.basedata.service.ScmsuppliergroupdetailBiz;
import com.armitage.server.iscm.basedata.service.ScmsupplierlinkmanBiz;
import com.armitage.server.iscm.basedata.service.ScmsupplierpurchaseinfoBiz;
import com.armitage.server.quartz.model.AppInfo;
import com.armitage.server.quartz.model.SupplierPlatIntegratedRequest;
import com.armitage.server.quartz.util.SupplierPlatUtil;
import com.armitage.server.system.model.Code;
import com.armitage.server.system.model.OrgBaseUnit;
import com.armitage.server.system.service.CodeBiz;
import com.armitage.server.system.service.OrgUnitBiz;
import com.armitage.server.system.service.SysParamBiz;
import com.armitage.server.system.util.CodeAutoCalUtil;
import com.armitage.server.user.model.Usr;
import com.armitage.server.user.service.UsrBiz;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

@Service("scmsupplierBiz")
public class ScmsupplierBizImpl extends BaseBizImpl<Scmsupplier2> implements ScmsupplierBiz {
	private Log log = LogFactory.getLog(ScmsupplierBizImpl.class);
	private UsrBiz usrBiz;
	private OrgUnitBiz orgUnitBiz;
	private ScmsupplierpurchaseinfoBiz scmsupplierpurchaseinfoBiz;
	private ScmsuppliercompanyinfoBiz scmsuppliercompanyinfoBiz;
	private ScmsuppliergroupBiz scmsuppliergroupBiz;
	private ScmsuppliergroupdetailBiz scmsuppliergroupdetailBiz;
	private ScmSupplierUnifiedBiz scmSupplierUnifiedBiz;
	private CodeBiz codeBiz;
	private ScmSupplierRegInvitationBiz scmSupplierRegInvitationBiz;
	private ScmSupplierDemanderBiz scmSupplierDemanderBiz;
	private ScmIndustryGroupBiz scmIndustryGroupBiz;
	private ScmQualifieInfoBiz scmQualifieInfoBiz;
	private PubSysBasicInfoBiz pubSysBasicInfoBiz;
	private ScmIndustryGroupQualifyTypeBiz scmIndustryGroupQualifyTypeBiz;
	private SysParamBiz sysParamBiz;	
	private ScmSupplierQualifyTypeBiz scmSupplierQualifyTypeBiz;
	private ScmsupplierlinkmanBiz scmsupplierlinkmanBiz;
	private ScmsupplierbankBiz scmsupplierbankBiz;
	public void setUsrBiz(UsrBiz usrBiz) {
		this.usrBiz = usrBiz;
	}

	public void setOrgUnitBiz(OrgUnitBiz orgUnitBiz) {
		this.orgUnitBiz = orgUnitBiz;
	}

	public void setScmsupplierpurchaseinfoBiz(ScmsupplierpurchaseinfoBiz scmsupplierpurchaseinfoBiz) {
		this.scmsupplierpurchaseinfoBiz = scmsupplierpurchaseinfoBiz;
	}

	public void setScmsuppliercompanyinfoBiz(ScmsuppliercompanyinfoBiz scmsuppliercompanyinfoBiz) {
		this.scmsuppliercompanyinfoBiz = scmsuppliercompanyinfoBiz;
	}

	public void setScmsuppliergroupBiz(ScmsuppliergroupBiz scmsuppliergroupBiz) {
		this.scmsuppliergroupBiz = scmsuppliergroupBiz;
	}

	public void setScmsuppliergroupdetailBiz(ScmsuppliergroupdetailBiz scmsuppliergroupdetailBiz) {
		this.scmsuppliergroupdetailBiz = scmsuppliergroupdetailBiz;
	}
	
	public void setScmSupplierUnifiedBiz(ScmSupplierUnifiedBiz scmSupplierUnifiedBiz) {
		this.scmSupplierUnifiedBiz = scmSupplierUnifiedBiz;
	}

	public void setCodeBiz(CodeBiz codeBiz) {
		this.codeBiz = codeBiz;
	}

	public void setScmSupplierRegInvitationBiz(ScmSupplierRegInvitationBiz scmSupplierRegInvitationBiz) {
		this.scmSupplierRegInvitationBiz = scmSupplierRegInvitationBiz;
	}
	
	public void setScmSupplierDemanderBiz(ScmSupplierDemanderBiz scmSupplierDemanderBiz) {
		this.scmSupplierDemanderBiz = scmSupplierDemanderBiz;
	}

	public void setScmSupplierQualifyTypeBiz(ScmSupplierQualifyTypeBiz scmSupplierQualifyTypeBiz) {
		this.scmSupplierQualifyTypeBiz = scmSupplierQualifyTypeBiz;
	}

	public void setScmIndustryGroupBiz(ScmIndustryGroupBiz scmIndustryGroupBiz) {
		this.scmIndustryGroupBiz = scmIndustryGroupBiz;
	}

	public void setScmQualifieInfoBiz(ScmQualifieInfoBiz scmQualifieInfoBiz) {
		this.scmQualifieInfoBiz = scmQualifieInfoBiz;
	}

	public void setPubSysBasicInfoBiz(PubSysBasicInfoBiz pubSysBasicInfoBiz) {
		this.pubSysBasicInfoBiz = pubSysBasicInfoBiz;
	}

	public void setScmIndustryGroupQualifyTypeBiz(ScmIndustryGroupQualifyTypeBiz scmIndustryGroupQualifyTypeBiz) {
		this.scmIndustryGroupQualifyTypeBiz = scmIndustryGroupQualifyTypeBiz;
	}
	
	public void setSysParamBiz(SysParamBiz sysParamBiz) {
		this.sysParamBiz = sysParamBiz;
	}

	public void setScmsupplierlinkmanBiz(ScmsupplierlinkmanBiz scmsupplierlinkmanBiz) {
		this.scmsupplierlinkmanBiz = scmsupplierlinkmanBiz;
	}

	public void setScmsupplierbankBiz(ScmsupplierbankBiz scmsupplierbankBiz) {
		this.scmsupplierbankBiz = scmsupplierbankBiz;
	}

	@Override
	protected HashMap<String, Object> addFindAllPageParam(Page page,HashMap<String, Object> map, Param param) {
		map.put("orgUnitNo", param.getOrgUnitNo());
		map.put("controlUnitNo", param.getControlUnitNo());
		return map;
	}
	
	@Override
	protected void beforeFindPage(Page page, Param param) throws AppException {
		page.getParam().put((ClassUtils.getFinalModelSimpleName(Scmsupplier2.class) + "." + Scmsupplier2.FN_CONTROLUNITNO), 
				new QueryParam((ClassUtils.getFinalModelSimpleName(Scmsupplier2.class) + "." + Scmsupplier2.FN_CONTROLUNITNO), QueryParam.QUERY_EQ, param.getControlUnitNo()));
	}
	
	@Override
	protected void afterFindPage(List list, Page page, Param param) throws AppException {
		if (list != null && !list.isEmpty()) {
			for (Scmsupplier2 scmsupplier:(List<Scmsupplier2>)list) {
				setConvertMap(scmsupplier,param);
			}
		}
	}
	
	@Override
	protected void afterSelect(CommonBean bean, Param param) throws AppException {
		Scmsupplier2 scmsupplier = (Scmsupplier2) bean.getList().get(0);
		if(scmsupplier != null && scmsupplier.getId() > 0){
			//加载采购资料、财务资料
			List<Scmsupplierpurchaseinfo2> scmsupplierpurchaseinfoList = new ArrayList();
			Scmsupplierpurchaseinfo2 scmsupplierpurchaseinfo = scmsupplierpurchaseinfoBiz.selectByVendorIdAndOrgUnitNo(scmsupplier.getId(), param.getOrgUnitNo(), param);
			if(scmsupplierpurchaseinfo == null){
				scmsupplierpurchaseinfo = scmsupplierpurchaseinfoBiz.selectByVendorIdAndOrgUnitNo(scmsupplier.getId(), param.getControlUnitNo(), param);
			}
			if(scmsupplierpurchaseinfo != null && scmsupplierpurchaseinfo.getVatRate()!=null){
				scmsupplierpurchaseinfo.setTaxRate(BigDecimalUtil.round(scmsupplierpurchaseinfo.getVatRate(),2).toString());
			}
			if(scmsupplierpurchaseinfo!=null) {
				scmsupplierpurchaseinfoList.add(scmsupplierpurchaseinfo);
				bean.setList2(scmsupplierpurchaseinfoList);
			}
			List<Scmsuppliercompanyinfo2> scmsuppliercompanyinfoList = new ArrayList();
			Scmsuppliercompanyinfo2 scmsuppliercompanyinfo = scmsuppliercompanyinfoBiz.selectByVendorIdAndOrgUnitNo(scmsupplier.getId(), param.getOrgUnitNo(), param);
			if(scmsuppliercompanyinfo == null){
				scmsuppliercompanyinfo = scmsuppliercompanyinfoBiz.selectByVendorIdAndOrgUnitNo(scmsupplier.getId(), param.getControlUnitNo(), param);
			}
			if(scmsuppliercompanyinfo!=null) {
				scmsuppliercompanyinfoList.add(scmsuppliercompanyinfo);
				bean.setList3(scmsuppliercompanyinfoList);
			}
			bean.setList4(scmQualifieInfoBiz.selectByVendorId(scmsupplier.getId(), param));
		}
	}

	@Override
	protected void afterSelect(Scmsupplier2 entity, Param param) throws AppException {
		Scmsupplier2 scmsupplier = entity;
		HashMap<String,Object> cacheMap = new HashMap<String,Object>();
		if(scmsupplier != null){
			setConvertMap(scmsupplier,param);
			if (scmsupplier.getId() > 0){
				//基本分类
				if(scmsupplier != null && scmsupplier.getClassId() > 0){
					Scmsuppliergroup scmsuppliergroup = scmsuppliergroupBiz.selectDirect(scmsupplier.getClassId(), param); 
					if(scmsuppliergroup != null){
						scmsupplier.setConvertMap(Scmsupplier2.FN_CLASSID, scmsuppliergroup);
					}
				}
			}
			
		}
	}
	
	@Override
	protected void beforeAdd(Scmsupplier2 entity , Param param) throws AppException {
		if(entity!=null) {
			if(StringUtils.isNotBlank(entity.getTaxNo()) && !(param instanceof InterfaceParam)) {
				List<Scmsupplier2> scmsupplierList = this.selectByTaxNo(entity.getTaxNo(), param);
				if(scmsupplierList!=null && !scmsupplierList.isEmpty()) {
					throw new AppException("iscm.Scmsupplier.taxno.error.repeat");
				}
			}
			HashMap<String, Object> map = new HashMap<>();
			map.put("controlUnitNo", param.getControlUnitNo());
			if(entity != null){
				Scmsupplier2 scmsupplier = ((ScmsupplierDAO) dao).selectMaxId(map);
				if(scmsupplier != null){
					String code = CodeAutoCalUtil.getBillCode("ScmSupplier", entity, param);
					entity.setVendorNo(code);
				}else{
					entity.setVendorNo("00001");
				}
			}
		}
	}
	
	@Override
	protected void beforeUpdate(Scmsupplier2 oldEntity, Scmsupplier2 newEntity, Param param) throws AppException {
		if(newEntity!=null) {
			if(StringUtils.isNotBlank(newEntity.getTaxNo()) && !(param instanceof InterfaceParam)) {
				List<Scmsupplier2> scmsupplierList = this.selectByTaxNo(newEntity.getTaxNo(), param);
				if(scmsupplierList!=null && !scmsupplierList.isEmpty()) {
					for(Scmsupplier2 scmsupplier:scmsupplierList) {
						if(scmsupplier.getId()!=newEntity.getId())
							throw new AppException("iscm.Scmsupplier.taxno.error.repeat");
					}
				}
			}
		}
	}

	@Override
	protected void afterAdd(CommonBean bean, Param param) throws AppException {
		Scmsupplier2 scmsupplier = (Scmsupplier2) bean.getList().get(0);
		if(scmsupplier != null){
			if(param instanceof OperationParam && ((OperationParam)param).getOperationId()==809123240){
				String addVendorQualifyRequire = sysParamBiz.getValue(param.getControlUnitNo(), "SCM_AddVendorQualifyRequire", "N", param);
				if("Y".equals(addVendorQualifyRequire)){
					List<ScmQualifieInfo2> scmQualifieInfoList = scmQualifieInfoBiz.selectByVendorId(scmsupplier.getId(), param);
					if(scmQualifieInfoList == null || scmQualifieInfoList.isEmpty()){
						throw new AppException("com.armitage.server.iscm.basedata.service.impl.ScmsupplierBizImpl.error.notQualityInfo");
					}
				}
			}
			if(bean.getList2() != null && !bean.getList2().isEmpty()){
				//新增采购资料
				Scmsupplierpurchaseinfo2 scmsupplierpurchaseinfo = (Scmsupplierpurchaseinfo2) bean.getList2().get(0);
				if(scmsupplierpurchaseinfo.getVendorId() > 0){
					scmsupplierpurchaseinfoBiz.add(scmsupplierpurchaseinfo, param);
				}
			}
			if(bean.getList3() != null && !bean.getList3().isEmpty()){
				//新增财务资料
				Scmsuppliercompanyinfo2 scmsuppliercompanyinfo = (Scmsuppliercompanyinfo2) bean.getList3().get(0);
				if(scmsuppliercompanyinfo.getVendorId() > 0){
					scmsuppliercompanyinfoBiz.add(scmsuppliercompanyinfo, param);
				}
			}
			//新增供应商分类关系
			if(scmsupplier.getId() > 0){
				Scmsuppliergroupdetail scmsuppliergroupdetail = new Scmsuppliergroupdetail(true);
				scmsuppliergroupdetail.setVendorId(scmsupplier.getId());
				scmsuppliergroupdetail.setClassId(scmsupplier.getClassId());
				scmsuppliergroupdetailBiz.add(scmsuppliergroupdetail, param);
			}
		}
	}
	
	@Override
	protected void afterUpdate(CommonBean bean, Param param)
			throws AppException {
		Scmsupplier2 scmsupplier = (Scmsupplier2) bean.getList().get(0);
		if(scmsupplier != null){
			if(bean.getList2() != null && !bean.getList2().isEmpty()){
				//更新采购资料
				Scmsupplierpurchaseinfo2 scmsupplierpurchaseinfo = (Scmsupplierpurchaseinfo2) bean.getList2().get(0);
				Scmsupplierpurchaseinfo2 scmsupplierpurchaseinfo2 = scmsupplierpurchaseinfoBiz.selectByVendorIdAndOrgUnitNo(scmsupplier.getId(), param.getOrgUnitNo(), param);
				if(scmsupplierpurchaseinfo2 != null){
					BeanUtils.copyProperties(scmsupplierpurchaseinfo,scmsupplierpurchaseinfo2);
					scmsupplierpurchaseinfoBiz.update(scmsupplierpurchaseinfo2, param);
				}else {
					Scmsupplierpurchaseinfo2 scmsupplierpurchaseinfo3 = scmsupplierpurchaseinfoBiz.selectByVendorIdAndOrgUnitNo(scmsupplier.getId(), param.getControlUnitNo(), param);
					Gson newGson = JSONUtils.newGson();
					if (scmsupplierpurchaseinfo3 != null) {
						scmsupplierpurchaseinfo3.setTaxRate(null);
					}
					if (scmsupplierpurchaseinfo != null) {
						scmsupplierpurchaseinfo.setTaxRate(null);
					}
					String json = newGson.toJson(scmsupplierpurchaseinfo3);
					String json1 = newGson.toJson(scmsupplierpurchaseinfo);
					//判断是否有修改过该资料
					if (!StringUtils.equals(json, json1)) {
						scmsupplierpurchaseinfo.setOrgUnitNo(param.getOrgUnitNo());
						scmsupplierpurchaseinfoBiz.add(scmsupplierpurchaseinfo, param);
					}
				}
			}
			if(bean.getList3() != null && !bean.getList3().isEmpty()){
				//更新财务资料
				Scmsuppliercompanyinfo2 scmsuppliercompanyinfo = (Scmsuppliercompanyinfo2) bean.getList3().get(0);
				Scmsuppliercompanyinfo2 scmsuppliercompanyinfo2 = scmsuppliercompanyinfoBiz.selectByVendorIdAndOrgUnitNo(scmsupplier.getId(), param.getOrgUnitNo(), param);
				if(scmsuppliercompanyinfo2 != null){
					BeanUtils.copyProperties(scmsuppliercompanyinfo,scmsuppliercompanyinfo2);
					scmsuppliercompanyinfoBiz.update(scmsuppliercompanyinfo, param);
				}else {
					Scmsuppliercompanyinfo2 scmsuppliercompanyinfo3 = scmsuppliercompanyinfoBiz.selectByVendorIdAndOrgUnitNo(scmsupplier.getId(), param.getControlUnitNo(), param);
					Gson newGson = JSONUtils.newGson();
					String json = newGson.toJson(scmsuppliercompanyinfo);
					String json1 = newGson.toJson(scmsuppliercompanyinfo3);
					//判断是否有修改过该资料
					if (!StringUtils.equals(json, json1)) {
						scmsuppliercompanyinfo.setOrgUnitNo(param.getOrgUnitNo());
						scmsuppliercompanyinfoBiz.add(scmsuppliercompanyinfo, param);
					}
				}
			}
			//更新供应商分类关系
			Page page=new Page();
			page.setModelClass(Scmsuppliergroupdetail.class);
			page.setShowCount(Integer.MAX_VALUE);
			page.getParam().put(
					Scmsuppliergroupdetail.FN_VENDORID,
					new QueryParam(Scmsuppliergroupdetail.FN_VENDORID, QueryParam.QUERY_EQ,
							String.valueOf(scmsupplier.getId())));
			
			List<Scmsuppliergroupdetail> scmsuppliergroupdetailList =scmsuppliergroupdetailBiz.findPage(page, param);
			if(scmsuppliergroupdetailList!=null && !scmsuppliergroupdetailList.isEmpty()){
				Scmsuppliergroupdetail scmsuppliergroupdetail = scmsuppliergroupdetailList.get(0);
				if(scmsuppliergroupdetail.getClassId() != scmsupplier.getClassId()){
					scmsuppliergroupdetail.setClassId(scmsupplier.getClassId());
					scmsuppliergroupdetailBiz.update(scmsuppliergroupdetail, param);
				}
			}
		}
	}
	
	@Override
	protected void afterDelete(Scmsupplier2 entity, Param param) throws AppException {
		Scmsupplier2 scmsupplier = entity;
		if(scmsupplier != null){
			//删除采购资料、财务资料
			scmsupplierpurchaseinfoBiz.update(scmsupplier, null, Scmsupplierpurchaseinfo2.FN_VENDORID, param);
			scmsuppliercompanyinfoBiz.update(scmsupplier, null, Scmsuppliercompanyinfo2.FN_VENDORID, param);
			//删除供应商分类关系
			if(scmsupplier.getId() > 0){
				scmsuppliergroupdetailBiz.deleteByVendorIdOrGroupId(scmsupplier.getId(), 0, param);
			}
		}
	}
	
	private void setConvertMap(Scmsupplier2 scmsupplier, Param param){
		if(scmsupplier!=null) {
			if (!StringUtils.equals("1", scmsupplier.getRole()) && StringUtils.isNotBlank(scmsupplier.getOrgUnitNo())){
				//内部公司
				OrgBaseUnit orgBaseUnit= orgUnitBiz.selectbyOrgNo(scmsupplier.getOrgUnitNo(), param);
				if(orgBaseUnit != null){
					scmsupplier.setConvertMap(Scmsupplier2.FN_ORGUNITNO, orgBaseUnit);
				}
			}
			if (StringUtils.isNotBlank(scmsupplier.getCreator())){
				//创建人
				Usr usr = usrBiz.selectByCode(scmsupplier.getCreator(), param);
				if (usr != null) {
					scmsupplier.setConvertMap(Scmsupplier2.FN_CREATOR, usr);
				}
			}
			if (StringUtils.isNotBlank(scmsupplier.getEditor())){
				//修改人
				Usr usr = usrBiz.selectByCode(scmsupplier.getEditor(), param);
				if (usr != null) {
					scmsupplier.setConvertMap(Scmsupplier2.FN_EDITOR, usr);
				}
			}
			if(scmsupplier.getId() > 0){
				ScmSupplierRegInvitation scmSupplierRegInvitation = scmSupplierRegInvitationBiz.selectByVendorIdAndCtrl(scmsupplier.getId(), param.getControlUnitNo(), param);
				if(scmSupplierRegInvitation != null && scmSupplierRegInvitation.getPlatSupplierId() > 0){
					scmsupplier.setPlatVendorId(scmSupplierRegInvitation.getPlatSupplierId());
					scmsupplier.setBinded(true);
				}
			}
		}
	}
	
	@Override
	public BaseModel updateStatus(BaseModel baseModel, Param param) throws AppException {
		if(baseModel instanceof Scmsupplier2){
			return this.updateDirect(((Scmsupplier2)baseModel), param);
		}
		if(baseModel instanceof Scmsupplierpurchaseinfo2){
			Scmsupplier2 scmsupplier = this.selectDirect(((Scmsupplierpurchaseinfo2)baseModel).getVendorId(), param);
			ModelCacheMana.delete(scmsupplier);
			return scmsupplierpurchaseinfoBiz.updateDirect(((Scmsupplierpurchaseinfo2)baseModel), param);
		}
		if(baseModel instanceof Scmsuppliercompanyinfo2){
			Scmsupplier2 scmsupplier = this.selectDirect(((Scmsuppliercompanyinfo2)baseModel).getVendorId(), param);
			ModelCacheMana.delete(scmsupplier);
			return scmsuppliercompanyinfoBiz.updateDirect(((Scmsuppliercompanyinfo2)baseModel), param);
		}
		return null;
	}

	@Override
	public boolean checkUse(long vendorId, Param param) throws AppException {
		if(vendorId > 0){
			int count = ((ScmsupplierDAO) dao).checkUse(vendorId);
			if(count > 0){
				return true;
			}else{
				return false;
			}
		}
		return false;
	}

	@Override
	public Scmsupplier2 selectByCode(String vendorCode, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("vendorNo", vendorCode);
		map.put("controlUnitNo", param.getControlUnitNo());
		return ((ScmsupplierDAO)dao).selectByCode(map);
	}

	@Override
	public List<Scmsupplier2> querySupplierList(ScmsupplierAdvQuery scmsupplierAdvQuery,int pageNum, Param param)
			throws AppException {
		Page page=new Page();
		page.setModelClass(Scmsupplier2.class);
		if (pageNum == -1) {
			page.setPagePos(1);
			page.setShowCount(Integer.MAX_VALUE);
		} else {
			page.setPagePos(pageNum);
			page.setShowCount(20);
		}
		page.setModel(scmsupplierAdvQuery);	//findPage时底层会触发doAdvQuery，增加查询条件
		
		if(StringUtils.isNotBlank(scmsupplierAdvQuery.getMixParam())){
			page.setSqlCondition("(Scmsupplier.vendorNo like '%"+scmsupplierAdvQuery.getMixParam()+"%' or Scmsupplier.vendorName like '%"+scmsupplierAdvQuery.getMixParam()+"%')");
		}
		
		return this.findPage(page, param);
	}

	@Override
	public void doAdvQuery(Page page, Param param) {
		if (page.getModel() != null) {
			if (page.getModel() instanceof ScmsupplierAdvQuery) {
				ScmsupplierAdvQuery scmsupplierAdvQuery = (ScmsupplierAdvQuery) page.getModel();
				if(StringUtils.isNotBlank(scmsupplierAdvQuery.getVendorName())){
					page.getParam().put(ClassUtils.getFinalModelSimpleName(Scmsupplier2.class) + "." +Scmsupplier2.FN_VENDORNAME,new QueryParam(ClassUtils.getFinalModelSimpleName(Scmsupplier2.class) + "." +Scmsupplier2.FN_VENDORNAME, QueryParam.QUERY_LIKE,scmsupplierAdvQuery.getVendorName()));
				}
				if(StringUtils.isNotBlank(scmsupplierAdvQuery.getVendorNo())){
					page.getParam().put(ClassUtils.getFinalModelSimpleName(Scmsupplier2.class) + "." +Scmsupplier2.FN_VENDORNO,new QueryParam(ClassUtils.getFinalModelSimpleName(Scmsupplier2.class) + "." +Scmsupplier2.FN_VENDORNO, QueryParam.QUERY_EQ,scmsupplierAdvQuery.getVendorNo()));
				}
			}
		}
	}

	@Override
	public void saveUnified(Scmsupplier2 scmsupplier,List<ScmSupplierUnified> scmSupplierUnifiedList, Param param)
			throws AppException {
			scmSupplierUnifiedBiz.saveUnified(scmsupplier,scmSupplierUnifiedList,param);
	}

	@Override
	public List<ScmSupplierUnified> selectUnified(Scmsupplier2 scmsupplier,
			Param param) throws AppException {
		return scmSupplierUnifiedBiz.selectByVendorId(scmsupplier.getId(),param);
	}

	@Override
	public Scmsupplier2 selectByName(String vendorName, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("vendorName", vendorName);
		map.put("controlUnitNo", param.getControlUnitNo());
		return ((ScmsupplierDAO)dao).selectByCode(map);
	}
	
	@Override
    public List<Scmsupplier2> selectByIdAndGroup(long vendorId, String sbSupClass, Param param) throws AppException {
        HashMap<String,Object> map = new HashMap<String,Object>();
        map.put("vendorId", vendorId);
        map.put("sbSupClass", sbSupClass);
        return ((ScmsupplierDAO)dao).selectByIdAndGroup(map);
    }

	@Override
	public Scmsupplier2 findSameNameVendor(Scmsupplier2 scmsupplier, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("controlUnitNo", param.getControlUnitNo());
        map.put("vendorName", scmsupplier.getVendorName());
        map.put("id", scmsupplier.getId());
        return ((ScmsupplierDAO)dao).findSameNameVendor(map);
	}
	
	/**
	 * @param supplierAddParams
	 * @param param
	 * @return true 新增；false 修改
	 */
	public boolean doAddOrUpdate(SupplierAddParams supplierAddParams, Param param) {
		if (StringUtils.isNotBlank(supplierAddParams.getExternalCode())) {
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("controlUnitNo",param.getControlUnitNo());
			map.put("externalCode", supplierAddParams.getExternalCode());
			Scmsupplier2 scmsupplier = ((ScmsupplierDAO)dao).findByExternalCode(map);
			if (scmsupplier != null) {
				BeanUtils.copyProperties(supplierAddParams, scmsupplier);
				CommonBean bean = new CommonBean();
				ArrayList<Scmsupplier2> scmsupplier2s = new ArrayList<>();
				ArrayList<Scmsupplierpurchaseinfo2> scmsupplierpurchaseinfo2s = new ArrayList<>();
				ArrayList<Scmsuppliercompanyinfo2> scmsuppliercompanyinfo2s = new ArrayList<>();
				scmsupplier2s.add(scmsupplier);
				//更新采购资料-联系人信息
				Scmsupplierlinkman scmsupplierlinkman = null;
				ArrayList<Scmsupplierlinkman> scmsupplierlinkmans = (ArrayList<Scmsupplierlinkman>) scmsupplierlinkmanBiz.selectByVendorId(scmsupplier.getId(), param);
				if (scmsupplierlinkmans != null && !scmsupplierlinkmans.isEmpty()) {
					scmsupplierlinkman = scmsupplierlinkmans.get(0);
					scmsupplierlinkmans.remove(0);
				}else {
					scmsupplierlinkmans = new ArrayList<>();
					scmsupplierlinkman =new Scmsupplierlinkman();
					scmsupplierlinkman.setVendorId(scmsupplier.getId());
				}
				scmsupplierlinkman.setAddress(supplierAddParams.getAddress());
				scmsupplierlinkman.setTel(supplierAddParams.getTel());
				scmsupplierlinkman.setContactPerson(supplierAddParams.getContactPerson());
				scmsupplierlinkmans.add(0,scmsupplierlinkman);
				Scmsupplierpurchaseinfo2 scmsupplierpurchaseinfo2 = scmsupplierpurchaseinfoBiz.selectByVendorIdAndOrgUnitNo(scmsupplier.getId(), param.getOrgUnitNo(), param);
				if (scmsupplierpurchaseinfo2 != null) {
					scmsupplierpurchaseinfo2.setTaxpayerType(supplierAddParams.getTaxpayerType());
					scmsupplierpurchaseinfo2.setVatRate(supplierAddParams.getVatRate());
					
				}else {
					scmsupplierpurchaseinfo2 = new Scmsupplierpurchaseinfo2(true);
					scmsupplierpurchaseinfo2.setOrgUnitNo(param.getOrgUnitNo());
					scmsupplierpurchaseinfo2.setTaxpayerType(supplierAddParams.getTaxpayerType());
					scmsupplierpurchaseinfo2.setVatRate(supplierAddParams.getVatRate());
					scmsupplierpurchaseinfo2.setStatus("I");
					scmsupplierpurchaseinfo2.setVendorId(scmsupplier.getId());
				}
				scmsupplierpurchaseinfo2.setScmsupplierlinkmanList(scmsupplierlinkmans);
				scmsupplierpurchaseinfo2s.add(scmsupplierpurchaseinfo2);
				//更新财务资料-银行信息
				Scmsupplierbank scmsupplierbank = null;
				List<Scmsupplierbank> scmsupplierbanks = scmsupplierbankBiz.selectByVendorIdAndOrgUnitNo(scmsupplier.getId(), param.getOrgUnitNo(), param);
				if (scmsupplierbanks != null && !scmsupplierbanks.isEmpty()) {
					scmsupplierbank = scmsupplierbanks.get(0);
					scmsupplierbanks.remove(0);
				}else {
					scmsupplierlinkmans = new ArrayList<>();
					scmsupplierbank = new Scmsupplierbank(true);
					scmsupplierbank.setVendorId(scmsupplier.getId());
				}
				scmsupplierbank.setBankAccNo(supplierAddParams.getBankAccNo());
				scmsupplierbank.setBankAddress(supplierAddParams.getBankAddress());
				scmsupplierbank.setBankName(supplierAddParams.getBankName());
				scmsupplierbanks.add(scmsupplierbank);
				Scmsuppliercompanyinfo2 scmsuppliercompanyinfo = scmsuppliercompanyinfoBiz.selectByVendorIdAndOrgUnitNo(scmsupplier.getId(), param.getOrgUnitNo(), param);
				if (scmsuppliercompanyinfo != null) {
					scmsuppliercompanyinfo.setScmsupplierbankList(scmsupplierbanks);
				}else {
					scmsuppliercompanyinfo = new Scmsuppliercompanyinfo2(true);
					scmsuppliercompanyinfo.setVendorId(scmsupplier.getId());
					scmsuppliercompanyinfo.setOrgUnitNo(param.getOrgUnitNo());
					scmsuppliercompanyinfo.setSettleCycle("M");
					scmsuppliercompanyinfo.setDays(30);
					scmsuppliercompanyinfo.setCreator(param.getUsrCode());
					scmsuppliercompanyinfo.setCreateDate(CalendarUtil.getDate(param));
					scmsuppliercompanyinfo.setStatus("I");
					scmsuppliercompanyinfo.setScmsupplierbankList(scmsupplierbanks);
				}
				scmsuppliercompanyinfo.setScmsupplierbankList(scmsupplierbanks);
				scmsuppliercompanyinfo2s.add(scmsuppliercompanyinfo);
				bean.setList(scmsupplier2s);
				bean.setList2(scmsupplierpurchaseinfo2s);
				bean.setList3(scmsuppliercompanyinfo2s);
				this.update(bean, param);
				return false;
			}
		}
		return true;
	}

	@Override
	public Scmsupplier2 doAddSupplier(SupplierAddParams supplierAddParams, Param param) throws AppException {
		if (!doAddOrUpdate(supplierAddParams, param) && supplierAddParams.isExternal()) {
			return null;
		}
		//基本资料
		Scmsupplier2 scmsupplier = new Scmsupplier2(true);
		scmsupplier.setVendorName(supplierAddParams.getVendorName());
		Scmsuppliergroup2 scmsuppliergroup = scmsuppliergroupBiz.selectByClassCode(1, "OAadd", param);
		if(scmsuppliergroup == null){
			Scmsuppliergroup2 scmsuppliergroup2 = new Scmsuppliergroup2(true);
			scmsuppliergroup2.setStandardId(1);
			scmsuppliergroup2.setClassCode("OAadd");
			scmsuppliergroup2.setClassName("未分类");
			scmsuppliergroup2.setParentId(0);
			scmsuppliergroup2.setCreator(param.getUsrCode());
			scmsuppliergroup2.setCreateDate(CalendarUtil.getDate(param));
			scmsuppliergroup2.setEditor(param.getUsrCode());
			scmsuppliergroup2.setEditDate(CalendarUtil.getDate(param));
			scmsuppliergroupBiz.add(scmsuppliergroup2, param);
			scmsupplier.setClassId(scmsuppliergroup2.getId());
		}else{
			scmsupplier.setClassId(scmsuppliergroup.getId());
		}
		scmsupplier.setRole(supplierAddParams.getRole());
		scmsupplier.setOrgUnitNo(param.getOrgUnitNo());
		if(StringUtils.isNotBlank(supplierAddParams.getRemarks())) {
			scmsupplier.setRemarks(supplierAddParams.getRemarks());
		}
		scmsupplier.setCreator(param.getUsrCode());
		scmsupplier.setCreateDate(CalendarUtil.getDate(param));
		scmsupplier.setStatus("I");
		scmsupplier.setExternalCode(supplierAddParams.getExternalCode());
		scmsupplier.setManageOrgUnitNo(param.getControlUnitNo());
		this.add(scmsupplier, param);
		//新增供应商分类关系
		if(scmsupplier.getId() > 0){
			Scmsuppliergroupdetail scmsuppliergroupdetail = new Scmsuppliergroupdetail(true);
			scmsuppliergroupdetail.setVendorId(scmsupplier.getId());
			scmsuppliergroupdetail.setClassId(scmsupplier.getClassId());
			scmsuppliergroupdetailBiz.add(scmsuppliergroupdetail, param);
		}
		//采购资料
		Scmsupplierpurchaseinfo2 scmsupplierpurchaseinfo = new Scmsupplierpurchaseinfo2(true);
		scmsupplierpurchaseinfo.setVendorId(scmsupplier.getId());
		scmsupplierpurchaseinfo.setOrgUnitNo(param.getOrgUnitNo());
		scmsupplierpurchaseinfo.setTaxpayerType(supplierAddParams.getTaxpayerType());
		scmsupplierpurchaseinfo.setVatRate(supplierAddParams.getVatRate());
		scmsupplierpurchaseinfo.setStatus("I");
		Scmsupplierlinkman scmsupplierlinkman = new Scmsupplierlinkman(true);
		scmsupplierlinkman.setVendorId(scmsupplier.getId());
		scmsupplierlinkman.setContactPerson(supplierAddParams.getContactPerson());
		if(StringUtils.isNotBlank(supplierAddParams.getTel())) {
			scmsupplierlinkman.setTel(supplierAddParams.getTel());
		}
		if(StringUtils.isNotBlank(supplierAddParams.getAddress())) {
			scmsupplierlinkman.setAddress(supplierAddParams.getAddress());
		}
		List<Scmsupplierlinkman> linkManList = new ArrayList<>();
		linkManList.add(scmsupplierlinkman);
		scmsupplierpurchaseinfo.setScmsupplierlinkmanList(linkManList);
		//财务资料
		Scmsuppliercompanyinfo2 scmsuppliercompanyinfo = new Scmsuppliercompanyinfo2(true);
		scmsuppliercompanyinfo.setVendorId(scmsupplier.getId());
		scmsuppliercompanyinfo.setOrgUnitNo(param.getOrgUnitNo());
		scmsuppliercompanyinfo.setSettleCycle("M");
		scmsuppliercompanyinfo.setDays(30);
		scmsuppliercompanyinfo.setCreator(param.getUsrCode());
		scmsuppliercompanyinfo.setCreateDate(CalendarUtil.getDate(param));
		scmsuppliercompanyinfo.setStatus("I");
		Scmsupplierbank scmsupplierbank = new Scmsupplierbank(true);
		scmsupplierbank.setVendorId(scmsupplier.getId());
		scmsupplierbank.setOrgUnitNo(param.getOrgUnitNo());
		scmsupplierbank.setBankName(supplierAddParams.getBankName());
		scmsupplierbank.setBankAccNo(supplierAddParams.getBankAccNo());
		if(StringUtils.isNotBlank(supplierAddParams.getBankAddress())) {
			scmsupplierbank.setBankAddress(supplierAddParams.getBankAddress());
		}
		List<Scmsupplierbank> bankList = new ArrayList<>();
		bankList.add(scmsupplierbank);
		scmsuppliercompanyinfo.setScmsupplierbankList(bankList);
		
		List<Scmsupplier2> scmsupplierList = new ArrayList<>();
		scmsupplierList.add(scmsupplier);
		List<Scmsupplierpurchaseinfo2> scmsupplierpurchaseinfoList = new ArrayList<>();
		scmsupplierpurchaseinfoList.add(scmsupplierpurchaseinfo);
		List<Scmsuppliercompanyinfo2> scmsuppliercompanyinfoList = new ArrayList<>();
		scmsuppliercompanyinfoList.add(scmsuppliercompanyinfo);
		CommonBean bean = new CommonBean();
		bean.setList(scmsupplierList);
		bean.setList2(scmsupplierpurchaseinfoList);
		bean.setList3(scmsuppliercompanyinfoList);
		this.update(bean, param);
		return scmsupplier;
	}

	@Override
	public HashMap<String, List<Scmsupplier2>> selectSupplierToFinList(Scmsupplier2 scmsupplier, Param param)
			throws AppException {
		HashMap<String, List<Scmsupplier2>> supplierMap = new HashMap<String, List<Scmsupplier2>>();
		Page page = new Page();
		page.setModelClass(Scmsupplier2.class);
		page.setShowCount(Integer.MAX_VALUE);
		List<String> arglist = new ArrayList<>();
		if(StringUtils.isBlank(scmsupplier.getControlUnitNo())){
			arglist.add("orgUnitNo="+param.getControlUnitNo());
		}else{
			arglist.add("orgUnitNo="+scmsupplier.getControlUnitNo());
		}
		arglist.add("controlUnitNo="+param.getControlUnitNo());
		String status[] = StringUtils.split(scmsupplier.getStatus(), ",");
		StringBuffer statusBuffer = new StringBuffer("");
		for(String sta:status){
			if(StringUtils.isNotBlank(statusBuffer.toString()))
				statusBuffer.append(",");
			statusBuffer.append("'").append(sta).append("'");
		}
		arglist.add("status="+statusBuffer.toString());
		scmsupplier.setGroupLevel(scmsupplier.getGroupLevel()>=1?scmsupplier.getGroupLevel():1);
		arglist.add("groupLevel="+scmsupplier.getGroupLevel());
		List<Scmsupplier2> scmsupplierList = this.queryPage(page, arglist, "findByControlAndGroup", param);
		if(scmsupplierList != null && !scmsupplierList.isEmpty()){
			for(Scmsupplier2 scmsupplier2 : scmsupplierList){
				int groupLevel = ((scmsupplier2.getLongNo()).split(",")).length;
				scmsupplier2.setGroupLevel(groupLevel);
				String classId = (scmsupplier2.getLongNo()).split(",")[(scmsupplier.getGroupLevel()-1)];
				if(supplierMap.containsKey(classId)){
					List<Scmsupplier2> scmsupplierToFinList = (List<Scmsupplier2>)supplierMap.get(classId);
					scmsupplierToFinList.add(scmsupplier2);
				}else{
					List<Scmsupplier2> scmsupplierToFinList = new ArrayList<>();
					scmsupplierToFinList.add(scmsupplier2);
					supplierMap.put(classId, scmsupplierToFinList);
				}
			}
			if(supplierMap != null && !supplierMap.isEmpty()){
				HashMap<String, List<Scmsupplier2>> supplierToFinMap = new HashMap<String, List<Scmsupplier2>>();
				for(String key : supplierMap.keySet()){
					long classId = Long.parseLong(key);
					Scmsuppliergroup scmsupplierGroup = scmsuppliergroupBiz.selectDirect(classId, param);
					if(scmsupplierGroup != null){
						List<Scmsupplier2> scmsupplierToFinList = (List<Scmsupplier2>)supplierMap.get(key);
						supplierToFinMap.put(scmsupplierGroup.getClassCode()+'_'+scmsupplierGroup.getClassName(), scmsupplierToFinList);
					}
				}
				return supplierToFinMap;
			}
		}
		return null;
	}

	@Override
	public Scmsupplier2 querySupplier(ScmsupplierAdvQuery scmsupplierAdvQuery, Param param) throws AppException {
		Page page=new Page();
		page.setModelClass(Scmsupplier2.class);
		page.setPagePos(1);
		page.setShowCount(Integer.MAX_VALUE);
		page.setModel(scmsupplierAdvQuery);	//findPage时底层会触发doAdvQuery，增加查询条件
		
		List<Scmsupplier2> scmSupplierList = this.findPage(page, param);
		if(scmSupplierList != null && !scmSupplierList.isEmpty()){
			Scmsupplier2 scmsupplier = scmSupplierList.get(0);
			if(scmsupplier != null){
				if(scmsupplier.getId() > 0){
					ScmSupplierDemander scmSupplierDemander =scmSupplierDemanderBiz.selectByCtrl(param.getControlUnitNo(), param);
					if(scmSupplierDemander != null && StringUtils.isNotBlank(scmSupplierDemander.getUniqueNo())){
						scmsupplier.setUniqueNo(scmSupplierDemander.getUniqueNo());
					}
				}
				if(scmsupplier.getClassId() > 0){
					Scmsuppliergroup scmsuppliergroup = scmsuppliergroupBiz.selectDirect(scmsupplier.getClassId(), param); 
					if(scmsuppliergroup != null){
						scmsupplier.setClassName(scmsuppliergroup.getClassName());
					}
				}
				if(StringUtils.isNotBlank(scmsupplier.getRole())){
					Code code = codeBiz.selectByCategoryAndCode("scmSupplierRole", scmsupplier.getRole());
					if(code!=null)
						scmsupplier.setRoleName(code.getName());
				}
				if (StringUtils.isNotBlank(scmsupplier.getCreator())){
					//创建人
					Usr usr = usrBiz.selectByCode(scmsupplier.getCreator(), param);
					if (usr != null) {
						scmsupplier.setCreatorName(usr.getName());
					}
				}
				if (StringUtils.isNotBlank(scmsupplier.getEditor())){
					//修改人
					Usr usr = usrBiz.selectByCode(scmsupplier.getEditor(), param);
					if (usr != null) {
						scmsupplier.setEditorName(usr.getName());
					}
				}
			}
			return scmsupplier;
		}
		return null;
	}

	@Override
	public List<Scmsupplier2> selectByTaxNo(String taxNo, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("taxNo", taxNo);
		map.put("controlUnitNo", param.getControlUnitNo());
		return ((ScmsupplierDAO)dao).selectByTaxNo(map);
	}

	@Override
	public ScmSupplierRegInvitation getInvitationCode(String vendorCode, Param param) throws AppException {
		Page page=new Page();
		page.setModelClass(Scmsupplier2.class);
		page.setPagePos(1);
		page.setShowCount(Integer.MAX_VALUE);
		ScmsupplierAdvQuery scmsupplierAdvQuery = new ScmsupplierAdvQuery();
		scmsupplierAdvQuery.setVendorNo(vendorCode);
		page.setModel(scmsupplierAdvQuery);	
		
		List<Scmsupplier2> scmSupplierList = this.findPage(page, param);
		if(scmSupplierList!=null && !scmSupplierList.isEmpty()) {
			Scmsupplier2 scmsupplier = scmSupplierList.get(0);
			ScmSupplierDemander scmSupplierDemander =scmSupplierDemanderBiz.selectByCtrl(param.getControlUnitNo(), param);
			if(scmSupplierDemander != null && StringUtils.isNotBlank(scmSupplierDemander.getUniqueNo())){
				scmsupplier.setUniqueNo(scmSupplierDemander.getUniqueNo());
			}else {
				throw new AppException("iscm.server.Scmsupplier.error.notopenSupplierplat");
			}
			return scmSupplierRegInvitationBiz.getOrAddByVendor(scmsupplier, param);
		}else {
			throw new AppException("iscm.server.Scmsupplier.error.notExist");
		}
	}

	@Override
	public List<ScmSupplierPlatFormUser> selectPlatFormUser(Scmsupplier2 scmsupplier, Param param) throws AppException {
		if(scmsupplier.getPlatVendorId()>0) {
			AppInfo appInfo = new AppInfo(AppInfo.APP_NAME,param.getOrgUnitNo(),param.getControlUnitNo());
			SupplierPlatUtil supplierPlatUtil = new SupplierPlatUtil();
			appInfo = supplierPlatUtil.getAPP(appInfo);
			String params = "{}";
			// 获取 token
			JSONObject jsonObj = JSONObject
					.parseObject(supplierPlatUtil.getToken(appInfo.getUrl(), appInfo.getUsrCode(), appInfo.getPass()));
			String token = jsonObj.getString("securityToken");
			// 授权
			SupplierPlatIntegratedRequest integratedRequest = new SupplierPlatIntegratedRequest();
			integratedRequest.setHostName("iSCM-PC");
			integratedRequest.setLang("zh-CN");
			integratedRequest.setProductCode("iSCM");
			integratedRequest.setOptCode(appInfo.getUsrCode());
			integratedRequest.setSupplierId(scmsupplier.getPlatVendorId());
			integratedRequest.setSecurityToken(token);
			integratedRequest.setUserCode(appInfo.getUsrCode());
			String integrated = JSONObject.toJSONString(integratedRequest);
			String reqJson = "{\"integratedRequest\":" + integrated
					+ ",\"pageCount\":\"0\",\"pageNum\":\"-1\",\"params\": " + params + "}";
			String data="";
			// 调用应用服务
			try {
				log.info("请求地址："+appInfo.getUrl()+"/isp/woUser/queryPage");
				log.info("请求参数："+reqJson);
				data = supplierPlatUtil.post(appInfo.getUrl()+"/isp/woUser/queryPage", reqJson);
			} catch (Exception e) {
				throw new AppException(e.getMessage());
			}
			JSONObject jsonObject = JSON.parseObject(data);
			if(jsonObject != null && "0".equals(String.valueOf(jsonObject.get("errCode")))){
				JSONArray resultarray = jsonObject.getJSONArray("data");
				List<ScmSupplierPlatFormUser> scmSupplierPlatFormUserList =  resultarray.toJavaList(ScmSupplierPlatFormUser.class);
				for(ScmSupplierPlatFormUser scmSupplierPlatFormUser:scmSupplierPlatFormUserList) {
					scmSupplierPlatFormUser.setPlatVendorId(scmsupplier.getPlatVendorId());
					if(StringUtils.equals("00", scmSupplierPlatFormUser.getUsrType())) {
						scmSupplierPlatFormUser.setAdminUsr(true);
					}
				}
				return scmSupplierPlatFormUserList;
			}
		}
		return null;
	}

	@Override
	public List<ScmSupplierPlatFormUser> changePlatFormAdmin(ScmSupplierPlatFormUser scmSupplierPlatFormUser, Param param) throws AppException {
		if(scmSupplierPlatFormUser.getPlatVendorId()>0) {
			AppInfo appInfo = new AppInfo(AppInfo.APP_NAME,param.getOrgUnitNo(),param.getControlUnitNo());
			SupplierPlatUtil supplierPlatUtil = new SupplierPlatUtil();
			appInfo = supplierPlatUtil.getAPP(appInfo);
			String params = "{\"code\": \""+scmSupplierPlatFormUser.getCode()+"\"}";
			// 获取 token
			JSONObject jsonObj = JSONObject
					.parseObject(supplierPlatUtil.getToken(appInfo.getUrl(), appInfo.getUsrCode(), appInfo.getPass()));
			String token = jsonObj.getString("securityToken");
			// 授权
			SupplierPlatIntegratedRequest integratedRequest = new SupplierPlatIntegratedRequest();
			integratedRequest.setHostName("iSCM-PC");
			integratedRequest.setLang("zh-CN");
			integratedRequest.setProductCode("iSCM");
			integratedRequest.setOptCode(appInfo.getUsrCode());
			integratedRequest.setSupplierId(scmSupplierPlatFormUser.getPlatVendorId());
			integratedRequest.setSecurityToken(token);
			integratedRequest.setUserCode(appInfo.getUsrCode());
			String integrated = JSONObject.toJSONString(integratedRequest);
			String reqJson = "{\"integratedRequest\":" + integrated
					+ ",\"params\": " + params + "}";
			String data="";
			// 调用应用服务
			try {
				log.info("请求地址："+appInfo.getUrl()+"/isp/woUser/modifyAdmin");
				log.info("请求参数："+reqJson);
				data = supplierPlatUtil.post(appInfo.getUrl()+"/isp/woUser/modifyAdmin", reqJson);
			} catch (Exception e) {
				throw new AppException(e.getMessage());
			}
			JSONObject jsonObject = JSON.parseObject(data);
			if(jsonObject != null) {
				if(!"0".equals(String.valueOf(jsonObject.get("errCode")))){
					throw new AppException(String.valueOf(jsonObject.get("errText")));
				}
			}
			Scmsupplier2 scmsupplier = new Scmsupplier2();
			scmsupplier.setPlatVendorId(scmSupplierPlatFormUser.getPlatVendorId());
			return selectPlatFormUser(scmsupplier,param);
		}
		return null;
	}

	@Override
	public List<ScmSupplierPlatFormUser> auditPlatFormAdmin(ScmSupplierPlatFormUser scmSupplierPlatFormUser, Param param) throws AppException {
		if(scmSupplierPlatFormUser.getPlatVendorId()>0) {
			AppInfo appInfo = new AppInfo(AppInfo.APP_NAME,param.getOrgUnitNo(),param.getControlUnitNo());
			SupplierPlatUtil supplierPlatUtil = new SupplierPlatUtil();
			appInfo = supplierPlatUtil.getAPP(appInfo);
			String params = "{\"code\": \""+scmSupplierPlatFormUser.getCode()+"\",\"approvalBy\": \""+param.getUsrName()+"\",\"modifyFlag\": \"1\"}";
			// 获取 token
			JSONObject jsonObj = JSONObject
					.parseObject(supplierPlatUtil.getToken(appInfo.getUrl(), appInfo.getUsrCode(), appInfo.getPass()));
			String token = jsonObj.getString("securityToken");
			// 授权
			SupplierPlatIntegratedRequest integratedRequest = new SupplierPlatIntegratedRequest();
			integratedRequest.setHostName("iSCM-PC");
			integratedRequest.setLang("zh-CN");
			integratedRequest.setProductCode("iSCM");
			integratedRequest.setOptCode(appInfo.getUsrCode());
			integratedRequest.setSupplierId(scmSupplierPlatFormUser.getPlatVendorId());
			integratedRequest.setSecurityToken(token);
			integratedRequest.setUserCode(appInfo.getUsrCode());
			String integrated = JSONObject.toJSONString(integratedRequest);
			String reqJson = "{\"integratedRequest\":" + integrated
					+ ",\"params\": " + params + "}";
			String data="";
			// 调用应用服务
			try {
				log.info("请求地址："+appInfo.getUrl()+"/isp/woUser/modifyFlag");
				log.info("请求参数："+reqJson);
				data = supplierPlatUtil.post(appInfo.getUrl()+"/isp/woUser/modifyFlag", reqJson);
			} catch (Exception e) {
				throw new AppException(e.getMessage());
			}
			JSONObject jsonObject = JSON.parseObject(data);
			if(jsonObject != null) {
				if(!"0".equals(String.valueOf(jsonObject.get("errCode")))){
					throw new AppException(String.valueOf(jsonObject.get("errText")));
				}
			}
			Scmsupplier2 scmsupplier = new Scmsupplier2();
			scmsupplier.setPlatVendorId(scmSupplierPlatFormUser.getPlatVendorId());
			return selectPlatFormUser(scmsupplier,param);
		}
		return null;
	}

	@Override
	public List<String> disable(BaseModel baseModel, List<String> orgUnitList, Param param) throws AppException {
		List<String> msgInfoList = new ArrayList();
		if (baseModel instanceof Scmsupplier2) {
			Scmsupplier2 scmsupplier = this.selectDirect(((Scmsupplier2) baseModel).getId(), param);
			scmsupplier.setStatus("S");
			this.updateDirect(scmsupplier, param);
		}
		if(baseModel instanceof Scmsupplierpurchaseinfo2){
			if(orgUnitList!=null && !orgUnitList.isEmpty()) {
				for(String orgUnitNo:orgUnitList) {
					Scmsupplierpurchaseinfo2 scmsupplierpurchaseinfo = (Scmsupplierpurchaseinfo2)baseModel;
					scmsupplierpurchaseinfo.setOrgUnitNo(orgUnitNo);
					scmsupplierpurchaseinfo.setStatus("S");
					scmsupplierpurchaseinfoBiz.updateByPurchase(scmsupplierpurchaseinfo,param);
				}
			}else {
				Scmsupplierpurchaseinfo2 scmsupplierpurchaseinfo = (Scmsupplierpurchaseinfo2)baseModel;
				scmsupplierpurchaseinfo.setOrgUnitNo(param.getOrgUnitNo());
				scmsupplierpurchaseinfo.setStatus("S");
				scmsupplierpurchaseinfoBiz.updateByPurchase(scmsupplierpurchaseinfo,param);
			}
		}
		if(baseModel instanceof Scmsuppliercompanyinfo2){
			if(orgUnitList!=null && !orgUnitList.isEmpty()) {
				for(String orgUnitNo:orgUnitList) {
					Scmsuppliercompanyinfo2 scmsuppliercompanyinfo = (Scmsuppliercompanyinfo2)baseModel;
					scmsuppliercompanyinfo.setOrgUnitNo(orgUnitNo);
					scmsuppliercompanyinfo.setStatus("S");
					scmsuppliercompanyinfoBiz.updateByCompanyInfo(scmsuppliercompanyinfo,param);
				}
			}else {
				Scmsuppliercompanyinfo2 scmsuppliercompanyinfo = (Scmsuppliercompanyinfo2)baseModel;
				scmsuppliercompanyinfo.setOrgUnitNo(param.getOrgUnitNo());
				scmsuppliercompanyinfo.setStatus("S");
				scmsuppliercompanyinfoBiz.updateByCompanyInfo(scmsuppliercompanyinfo,param);
			}
		}
		return msgInfoList;
	}

	@Override
	public List<String> enable(BaseModel baseModel, List<String> orgUnitList, Param param) throws AppException {
		List<String> msgInfoList = new ArrayList();
		if (baseModel instanceof Scmsupplier2) {
			Scmsupplier2 scmsupplier = this.selectDirect(((Scmsupplier2) baseModel).getId(), param);
			scmsupplier.setStatus("A");
			this.updateDirect(scmsupplier, param);
		}
		if(baseModel instanceof Scmsupplierpurchaseinfo2){
			if(orgUnitList!=null && !orgUnitList.isEmpty()) {
				for(String orgUnitNo:orgUnitList) {
					Scmsupplierpurchaseinfo2 scmsupplierpurchaseinfo = (Scmsupplierpurchaseinfo2)baseModel;
					scmsupplierpurchaseinfo.setOrgUnitNo(orgUnitNo);
					scmsupplierpurchaseinfo.setStatus("A");
					scmsupplierpurchaseinfoBiz.updateByPurchase(scmsupplierpurchaseinfo,param);
				}
			}else {
				Scmsupplierpurchaseinfo2 scmsupplierpurchaseinfo = (Scmsupplierpurchaseinfo2)baseModel;
				scmsupplierpurchaseinfo.setOrgUnitNo(param.getOrgUnitNo());
				scmsupplierpurchaseinfo.setStatus("A");
				scmsupplierpurchaseinfoBiz.updateByPurchase(scmsupplierpurchaseinfo,param);
			}
		}
		if(baseModel instanceof Scmsuppliercompanyinfo2){
			if(orgUnitList!=null && !orgUnitList.isEmpty()) {
				for(String orgUnitNo:orgUnitList) {
					Scmsuppliercompanyinfo2 scmsuppliercompanyinfo = (Scmsuppliercompanyinfo2)baseModel;
					scmsuppliercompanyinfo.setOrgUnitNo(orgUnitNo);
					scmsuppliercompanyinfo.setStatus("A");
					scmsuppliercompanyinfoBiz.updateByCompanyInfo(scmsuppliercompanyinfo,param);
				}
			}else {
				Scmsuppliercompanyinfo2 scmsuppliercompanyinfo = (Scmsuppliercompanyinfo2)baseModel;
				scmsuppliercompanyinfo.setOrgUnitNo(param.getOrgUnitNo());
				scmsuppliercompanyinfo.setStatus("A");
				scmsuppliercompanyinfoBiz.updateByCompanyInfo(scmsuppliercompanyinfo,param);
			}
		}
		return msgInfoList;
	}

	@Override
	public Scmsupplier2 doUpdateSupplier(SupplierUpdateParams supplierUpdateParams, Param createParam)throws AppException {
		Page page = new Page();
		page.setModelClass(Scmsupplier2.class);
		page.setShowCount(Integer.MAX_VALUE);
		if (StringUtils.isNotBlank(supplierUpdateParams.getVendorName())) {
			if (StringUtils.isNotBlank(supplierUpdateParams.getOrgunitNo())) {
				page.getParam().put(Scmsupplier2.FN_ORGUNITNO,
						new QueryParam(
								ClassUtils.getFinalModelSimpleName(Scmsupplier2.class) + "." + Scmsupplier2.FN_ORGUNITNO,
								QueryParam.QUERY_EQ, supplierUpdateParams.getOrgunitNo()));
			}else {
				throw new AppException("iscm.Scmsupplier.doUpdateSupplier.error.orgunitNoIsEmpty");
			}
			if (StringUtils.isNotBlank(supplierUpdateParams.getVendorNo())) {
				page.getParam().put(Scmsupplier2.FN_VENDORNO,
						new QueryParam(
								ClassUtils.getFinalModelSimpleName(Scmsupplier2.class) + "." + Scmsupplier2.FN_VENDORNO,
								QueryParam.QUERY_EQ, supplierUpdateParams.getVendorNo()));
			}
			if (StringUtils.isNotBlank(supplierUpdateParams.getExternalCode())) {
				page.getParam().put(Scmsupplier2.FN_EXTERNALCODE,
						new QueryParam(
								ClassUtils.getFinalModelSimpleName(Scmsupplier2.class) + "."
										+ Scmsupplier2.FN_EXTERNALCODE,
								QueryParam.QUERY_EQ, supplierUpdateParams.getExternalCode()));
			}
			List scmsupplierList = this.findPage(page, createParam);
			if (scmsupplierList != null && !scmsupplierList.isEmpty()) {
				Scmsupplier2 scmsupplier2 = (Scmsupplier2) scmsupplierList.get(0);
				scmsupplier2.setVendorName(supplierUpdateParams.getVendorName());
				this.update(scmsupplier2, createParam);
			} else {
				throw new AppException("iscm.Scmsupplier.doUpdateSupplier.error.isEmpty");
			}
		}else {
			throw new AppException("iscm.Scmsupplier.doUpdateSupplier.error.nameIsEmpty");
		}
		return null;
	}

	@Override
	public List<Scmsupplier2> querySupplierOA(SupplierOAParams supplierParams, Param createParam) throws AppException {
		Page page=new Page();
		page.setModelClass(Scmsupplier2.class);
		page.setShowCount(Integer.MAX_VALUE);
		ArrayList<String> argList = new ArrayList<>();
		argList.add("controlUnitNo="+createParam.getControlUnitNo());
		if (StringUtils.isNotBlank(supplierParams.getSupplierStatus())) {
			StringBuffer statusBuffer = new StringBuffer("");
			String status[] = StringUtils.split(supplierParams.getSupplierStatus(), ",");
			for (String sta : status) {
				if (StringUtils.isNotBlank(statusBuffer.toString()))
					statusBuffer.append(",");
				statusBuffer.append("'").append(sta).append("'");
			}
			argList.add("supplierStatus=" + statusBuffer);
		} else {
			return null;
		}
		if (StringUtils.isNotBlank(supplierParams.getQualificationStatus())) {
			StringBuffer statusBuffer = new StringBuffer("");
			String status[] = StringUtils.split(supplierParams.getQualificationStatus(), ",");
			for (String sta : status) {
				if (StringUtils.isNotBlank(statusBuffer.toString()))
					statusBuffer.append(",");
				statusBuffer.append("'").append(sta).append("'");
			}
			argList.add("qualificationStatus="+statusBuffer);
		}else {
			return null;
		}
		List<Scmsupplier2>  scmsupplier2List= this.queryPage(page, argList, "querySupplierOA", createParam);
		if (scmsupplier2List != null && !scmsupplier2List.isEmpty()) {
			for (int i = scmsupplier2List.size(); i > 0; i--) {
				Scmsupplier2 scmsupplier2 = scmsupplier2List.get(i-1);
				List<ScmSupplierQualifyType> scmSupplierQualifyTypeList = scmSupplierQualifyTypeBiz.selectByVendor(scmsupplier2.getId(),createParam);
				if (scmSupplierQualifyTypeList != null && !scmSupplierQualifyTypeList.isEmpty()) {
					StringBuffer qualifyType = new StringBuffer();
					for (ScmSupplierQualifyType scmSupplierQualifyType : scmSupplierQualifyTypeList) {
						if (StringUtils.isNotBlank(qualifyType.toString())) {
							qualifyType.append(",");
						}
						switch (scmSupplierQualifyType.getName()) {
						case "营业执照": {
							qualifyType.append("0");
							break;
						}
						case "检测报告": {
							qualifyType.append("1");
							break;
						}
						case "生产许可": {
							qualifyType.append("2");
							break;
						}
						case "供货客户目录": {
							qualifyType.append("3");
							break;
						}
						}
					}
					if (StringUtils.isNotBlank(qualifyType.toString())) {
						scmsupplier2.setQualifyType(qualifyType.toString());
					}else {
						scmsupplier2List.remove(scmsupplier2);
					}
				}
				if (scmsupplier2.getVatRate().compareTo(new BigDecimal("0"))==0) {
					scmsupplier2.setVatRateString("6");
				}else if (scmsupplier2.getVatRate().compareTo(new BigDecimal("0.01"))==0) {
					scmsupplier2.setVatRateString("7");
				}else if (scmsupplier2.getVatRate().compareTo(new BigDecimal("0.03"))==0) {
					scmsupplier2.setVatRateString("0");
				}else if (scmsupplier2.getVatRate().compareTo(new BigDecimal("0.05"))==0) {
					scmsupplier2.setVatRateString("1");
				}else if (scmsupplier2.getVatRate().compareTo(new BigDecimal("0.06"))==0) {
					scmsupplier2.setVatRateString("2");
				}else if (scmsupplier2.getVatRate().compareTo(new BigDecimal("0.09"))==0) {
					scmsupplier2.setVatRateString("3");
				}else if (scmsupplier2.getVatRate().compareTo(new BigDecimal("0.13"))==0) {
					scmsupplier2.setVatRateString("4");
				}else if (scmsupplier2.getVatRate().compareTo(new BigDecimal("0.16"))==0) {
					scmsupplier2.setVatRateString("5");
				}else {
					scmsupplier2List.remove(scmsupplier2);
				}
				if (StringUtils.contains(scmsupplier2.getClassName(), "食品类")) {
					scmsupplier2.setClassName("0");
				}else if (StringUtils.contains(scmsupplier2.getClassName(), "维修材料类")) {
					scmsupplier2.setClassName("1");
				}else if (StringUtils.contains(scmsupplier2.getClassName(), "其他类")) {
					scmsupplier2.setClassName("5");
				}else if (StringUtils.contains(scmsupplier2.getClassName(), "易耗物料类")) {
					scmsupplier2.setClassName("2");
				} else if (StringUtils.contains(scmsupplier2.getClassName(), "固定资产类")) {
					scmsupplier2.setClassName("3");
				}else if (StringUtils.contains(scmsupplier2.getClassName(), "低值易耗品类")) {
					scmsupplier2.setClassName("4");
				}else {
					scmsupplier2List.remove(scmsupplier2);
				}
			}
		}
		return scmsupplier2List;
	}

	@Override
	public void updateSendOA(long id, Param createParam) throws AppException {
		Scmsupplier2 scmsupplier2 = this.select(id, createParam);
		if (scmsupplier2 != null) {
			scmsupplier2.setSendOA(true);
			this.update(scmsupplier2, createParam);
		}
	}

	@Override
	public CommonBean getQualifieInfo(Scmsupplier2 scmsupplier, Param param) throws AppException {
		if(scmsupplier != null && scmsupplier.getId() > 0){
			List<Scmsupplier2> scmsupplierTempList = new ArrayList<Scmsupplier2>();
			Scmsupplier2 tempScmsupplier = new Scmsupplier2(true);
			tempScmsupplier.setId(scmsupplier.getId());
			scmsupplierTempList.add(tempScmsupplier);
			CommonBean scmsupplierCommonBean = new CommonBean();
			scmsupplierCommonBean.setList(scmsupplierTempList);
			scmsupplierCommonBean = this.select(scmsupplierCommonBean, param);
			if(scmsupplierCommonBean == null || scmsupplierCommonBean.getList() == null || scmsupplierCommonBean.getList().isEmpty()){
				return null;
			}
			CommonBean dataCommonBean = new CommonBean();
			scmsupplier = (Scmsupplier2)scmsupplierCommonBean.getList().get(0);
			List<ScmIndustryGroupQualifyType2> scmIndustryGroupQualifyTypeList = new ArrayList<ScmIndustryGroupQualifyType2>();
			if(scmsupplier.getIndustryGroupId() > 0){
				ScmIndustryGroup scmIndustryGroup = scmIndustryGroupBiz.select(scmsupplier.getIndustryGroupId(), param); 
				if(scmIndustryGroup != null){
					scmsupplier.setConvertMap(Scmsupplier2.FN_INDUSTRYGROUPID, scmIndustryGroup);
					scmIndustryGroupQualifyTypeList = scmIndustryGroupQualifyTypeBiz.selectByClassId(scmIndustryGroup.getId(), param);
				}
			}
			if(scmsupplierCommonBean.getList2() != null && !scmsupplierCommonBean.getList2().isEmpty()){
				Scmsupplierpurchaseinfo2 scmsupplierpurchaseinfo =  (Scmsupplierpurchaseinfo2)scmsupplierCommonBean.getList2().get(0);
				scmsupplier.setTaxpayerType(scmsupplierpurchaseinfo.getTaxpayerType());
				if(scmsupplierpurchaseinfo != null && scmsupplierpurchaseinfo.getVatRate()!=null){
					PubSysBasicInfo pubSysBasicInfo = pubSysBasicInfoBiz.selectByTaxRate("TaxRate", scmsupplierpurchaseinfo.getVatRate().toString(), null, param);
					if (pubSysBasicInfo != null) {
						scmsupplier.setVendorTaxRate(scmsupplierpurchaseinfo.getTaxRate());
						scmsupplier.setConvertMap(Scmsupplier2.FN_VENDORTAXRATE, pubSysBasicInfo);
					}
				}
				if(scmsupplierpurchaseinfo.getScmsupplierlinkmanList() != null && !scmsupplierpurchaseinfo.getScmsupplierlinkmanList().isEmpty()){
					scmsupplier.setVendorContactPerson(scmsupplierpurchaseinfo.getScmsupplierlinkmanList().get(0).getContactPerson());
				}
			}
			if(scmsupplierCommonBean.getList3() != null && !scmsupplierCommonBean.getList3().isEmpty()){
				Scmsuppliercompanyinfo2 scmsuppliercompanyinfo =  (Scmsuppliercompanyinfo2)scmsupplierCommonBean.getList3().get(0);
				if(scmsuppliercompanyinfo.getScmsupplierbankList() !=null && !scmsuppliercompanyinfo.getScmsupplierbankList().isEmpty()){
					scmsupplier.setVendorBankName(scmsuppliercompanyinfo.getScmsupplierbankList().get(0).getBankName());
					scmsupplier.setVendorbankAccNo(scmsuppliercompanyinfo.getScmsupplierbankList().get(0).getBankAccNo());
				}
			}
			List<ScmQualifieInfo2> scmQualifieInfoList = scmQualifieInfoBiz.selectByVendorId(scmsupplier.getId(), param);
			if(scmQualifieInfoList != null && !scmQualifieInfoList.isEmpty()){
				StringBuffer qualifieAuditRemarks = new StringBuffer("");
				String registerSource = scmQualifieInfoList.get(0).getSource();
				for(ScmQualifieInfo2 scmQualifieInfo : scmQualifieInfoList){
					if(StringUtils.isNotBlank(scmQualifieInfo.getRemarks())){
						if(StringUtils.isNotBlank(qualifieAuditRemarks.toString())){
							qualifieAuditRemarks.append(";");
						}
						qualifieAuditRemarks.append(scmQualifieInfo.getRemarks());
					}
					if(scmQualifieInfo.isQualifyAudit()){
						if(scmIndustryGroupQualifyTypeList != null && !scmIndustryGroupQualifyTypeList.isEmpty()){
							for(ScmIndustryGroupQualifyType2 scmIndustryGroupQualifyType : scmIndustryGroupQualifyTypeList){
								if(scmIndustryGroupQualifyType.getTypeId() == scmQualifieInfo.getTypeId()){
									scmIndustryGroupQualifyType.setAudited(true);
									break;
								}
							}
						}
					}
				}
				scmsupplier.setQualifieAuditRemarks(qualifieAuditRemarks.toString());
				scmsupplier.setRegisterSource(registerSource);
			}
			dataCommonBean.setObject(scmsupplier);
			dataCommonBean.setList(scmIndustryGroupQualifyTypeList);
			dataCommonBean.setList2(scmQualifieInfoList);
			return dataCommonBean;
		}
		return null;
	}

	@Override
	public List<Scmsupplier2> selectByIndustryGroupId(long industryGroupId, Param param) throws AppException {
		if(industryGroupId > 0){
			HashMap<String,Object> map = new HashMap<String,Object>();
	        map.put("industryGroupId", industryGroupId);
	        return ((ScmsupplierDAO)dao).selectByIndustryGroupId(map);
		}
		return null;
	}

	@Override
	public List<Scmsupplier2> queryByInvStockVendor(String orgUnitNo, String useOrgUnitNo, long itemId, Param param)throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("costOrgUnitNo", orgUnitNo);
		map.put("orgUnitNo", useOrgUnitNo);
		map.put("itemId", itemId);
		return ((ScmsupplierDAO)dao).queryByInvStockVendor(map);
	}

	@Override
	public Scmsupplier2 queryByOrg(String orgUnitNo, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("controlUnitNo", param.getControlUnitNo());
		map.put("orgUnitNo", orgUnitNo);
		return ((ScmsupplierDAO)dao).queryByOrg(map);
	}
}
