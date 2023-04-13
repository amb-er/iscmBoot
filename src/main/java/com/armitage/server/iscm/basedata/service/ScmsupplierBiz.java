package com.armitage.server.iscm.basedata.service;

import java.util.HashMap;
import java.util.List;
import com.armitage.server.api.business.basedata.params.SupplierOAParams;
import com.armitage.server.api.business.basedata.params.SupplierAddParams;
import com.armitage.server.api.business.basedata.params.SupplierUpdateParams;
import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.CommonBean;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.basedata.model.ScmSupplierPlatFormUser;
import com.armitage.server.iscm.basedata.model.ScmSupplierRegInvitation;
import com.armitage.server.iscm.basedata.model.ScmSupplierUnified;
import com.armitage.server.iscm.basedata.model.Scmsupplier2;
import com.armitage.server.iscm.basedata.model.ScmsupplierAdvQuery;

public interface ScmsupplierBiz extends BaseBiz<Scmsupplier2> {
	public BaseModel updateStatus(BaseModel baseModel, Param param) throws AppException;
	
	public boolean checkUse(long vendorId, Param param) throws AppException;
	public Scmsupplier2 selectByCode(String vendorCode, Param param) throws AppException;

	public List<Scmsupplier2> querySupplierList(ScmsupplierAdvQuery scmsupplierAdvQuery,int pageNum, Param param) throws AppException;
	public Scmsupplier2 querySupplier(ScmsupplierAdvQuery scmsupplierAdvQuery, Param param) throws AppException;
	public void saveUnified(Scmsupplier2 scmsupplier, List<ScmSupplierUnified> scmSupplierUnifiedList, Param param) throws AppException;
	public Scmsupplier2 selectByName(String vendorName, Param param) throws AppException;
	public List<ScmSupplierUnified> selectUnified(Scmsupplier2 scmsupplier, Param param) throws AppException;
	public List<Scmsupplier2> selectByIdAndGroup(long vendorId, String sbSupClass, Param param)throws AppException;
	public Scmsupplier2 findSameNameVendor(Scmsupplier2 scmsupplier, Param param) throws AppException;
	
	public Scmsupplier2 doAddSupplier(SupplierAddParams supplierAddParams, Param param) throws AppException;
	public HashMap<String,List<Scmsupplier2>> selectSupplierToFinList(Scmsupplier2 scmsupplier, Param param) throws AppException;
	public List<Scmsupplier2> selectByTaxNo(String taxNo, Param param) throws AppException;
	public ScmSupplierRegInvitation getInvitationCode(String vendorCode, Param param) throws AppException;

	public List<ScmSupplierPlatFormUser> selectPlatFormUser(Scmsupplier2 scmsupplier, Param param) throws AppException;

	public List<ScmSupplierPlatFormUser> changePlatFormAdmin(ScmSupplierPlatFormUser scmSupplierPlatFormUser, Param param) throws AppException;

	public List<ScmSupplierPlatFormUser> auditPlatFormAdmin(ScmSupplierPlatFormUser scmSupplierPlatFormUser, Param param) throws AppException;
	public List<String> disable(BaseModel baseModel,List<String> orgUnitList, Param param) throws AppException;
	public List<String> enable(BaseModel baseModel,List<String> orgUnitList, Param param) throws AppException;

	public Scmsupplier2 doUpdateSupplier(SupplierUpdateParams supplierUpdateParams, Param createParam) throws AppException;
	public CommonBean getQualifieInfo(Scmsupplier2 scmsupplier, Param param) throws AppException;
	public List<Scmsupplier2> selectByIndustryGroupId(long industryGroupId, Param param)throws AppException;

	public List<Scmsupplier2> querySupplierOA(SupplierOAParams supplierParams, Param createParam) throws AppException;

	public void updateSendOA(long id, Param createParam) throws AppException;

	public List<Scmsupplier2> queryByInvStockVendor(String orgUnitNo, String useOrgUnitNo, long itemId, Param param) throws AppException;
	public Scmsupplier2 queryByOrg(String orgUnitNo,Param param) throws AppException;

}
