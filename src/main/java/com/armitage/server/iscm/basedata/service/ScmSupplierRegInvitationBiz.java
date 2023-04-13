
package com.armitage.server.iscm.basedata.service;

import java.util.List;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.basedata.model.ScmSupplierRegInvitation;
import com.armitage.server.iscm.basedata.model.Scmsupplier2;

public interface ScmSupplierRegInvitationBiz extends BaseBiz<ScmSupplierRegInvitation> {
	public ScmSupplierRegInvitation selectByVendorIdAndCtrl(long vendorId,String controlUnitNo,Param param) throws AppException;
	public ScmSupplierRegInvitation selectByCtrl(String controlUnitNo,Param param) throws AppException;
	public ScmSupplierRegInvitation selectByPlatVendorIdAndCtrl(long platVendorId,String controlUnitNo,Param param) throws AppException;
	public List<ScmSupplierRegInvitation> selectBindedByCtrl(String controlUnitNo,Param param) throws AppException;
	public ScmSupplierRegInvitation getOrAddByVendor(Scmsupplier2 scmsupplier, Param param) throws AppException;
	public List<ScmSupplierRegInvitation> selectBindedByCtrlAndVendor(String controlUnitNo,String vendorIds,Param param) throws AppException;
}
