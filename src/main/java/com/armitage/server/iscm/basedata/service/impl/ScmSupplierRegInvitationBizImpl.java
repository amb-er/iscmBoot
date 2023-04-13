
package com.armitage.server.iscm.basedata.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.util.CalendarUtil;
import com.armitage.server.common.util.FormatUtils;
import com.armitage.server.iscm.basedata.dao.ScmSupplierRegInvitationDAO;
import com.armitage.server.iscm.basedata.model.ScmSupplierRegInvitation;
import com.armitage.server.iscm.basedata.model.Scmsupplier2;
import com.armitage.server.iscm.basedata.service.ScmSupplierRegInvitationBiz;
import com.armitage.server.system.service.SysParamBiz;
import com.armitage.server.system.util.AppServiceUtil;
import org.springframework.stereotype.Service;

@Service("scmSupplierRegInvitationBiz")
public class ScmSupplierRegInvitationBizImpl extends BaseBizImpl<ScmSupplierRegInvitation> implements ScmSupplierRegInvitationBiz {
	private SysParamBiz sysParamBiz;
	
	public void setSysParamBiz(SysParamBiz sysParamBiz) {
		this.sysParamBiz = sysParamBiz;
	}

	@Override
	public ScmSupplierRegInvitation selectByVendorIdAndCtrl(long vendorId, String controlUnitNo, Param param)
			throws AppException {
		HashMap<String, Object> map = new HashMap<>();
		map.put("vendorId", vendorId);
		map.put("controlUnitNo", controlUnitNo);
		return ((ScmSupplierRegInvitationDAO) dao).selectByVendorIdAndCtrl(map);
	}
	
	@Override
	public ScmSupplierRegInvitation selectByPlatVendorIdAndCtrl(long platVendorId, String controlUnitNo, Param param)
			throws AppException {
		HashMap<String, Object> map = new HashMap<>();
		map.put("platVendorId", platVendorId);
		map.put("controlUnitNo", controlUnitNo);
		return ((ScmSupplierRegInvitationDAO) dao).selectByPlatVendorIdAndCtrl(map);
	}
	
	@Override
	public ScmSupplierRegInvitation selectByCtrl(String controlUnitNo, Param param) throws AppException {
		HashMap<String, Object> map = new HashMap<>();
		map.put("controlUnitNo", controlUnitNo);
		return ((ScmSupplierRegInvitationDAO) dao).selectByCtrl(map);
	}
	
	@Override
	public List<ScmSupplierRegInvitation> selectBindedByCtrl(String controlUnitNo, Param param) throws AppException {
		HashMap<String, Object> map = new HashMap<>();
		map.put("controlUnitNo", controlUnitNo);
		return ((ScmSupplierRegInvitationDAO) dao).selectBindedByCtrl(map);
	}
	
	@Override
	public ScmSupplierRegInvitation getOrAddByVendor(Scmsupplier2 scmsupplier, Param param) throws AppException {
		if(scmsupplier != null){
			int days = Integer.valueOf(sysParamBiz.getValue(param.getOrgUnitNo(), "SCM_InvitationValidDays", "7", param));
			Date createDate = new Date();
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(createDate);
	        calendar.add(Calendar.DATE, days);
	        Date expiryDate= calendar.getTime();
	        long times = calendar.getTimeInMillis();
			String url =sysParamBiz.getValue(param.getOrgUnitNo(), "SCM_MerchantPlatForm", "", param);
			if(StringUtils.isBlank(url))
				throw new AppException("iscm.service.ScmSupplierRegInvitationBizImpl.error.notparam");
			try {
				url = url + "/iwechat/getFlatFormOauth?r=WG-MAIN-ISP" +
				        "&refVendorNo=" + scmsupplier.getVendorNo() +
				        "&systemType=" + "iSCM" +
				        "&unionNo=" + scmsupplier.getUniqueNo() +
				        "&taxNo=" + scmsupplier.getTaxNo()  +
				        "&timestamp=" + times +
				        "&vendorName=" + URLEncoder.encode(scmsupplier.getVendorName(), "UTF-8");
			} catch (UnsupportedEncodingException e) {
			}
			ScmSupplierRegInvitation scmSupplierRegInvitation = this.selectByVendorIdAndCtrl(scmsupplier.getId(), scmsupplier.getControlUnitNo(), param);
			if(scmSupplierRegInvitation != null){
				scmSupplierRegInvitation.setInvitationContent(url);
				scmSupplierRegInvitation.setExpiryDate(expiryDate);
				scmSupplierRegInvitation.setEditor(param.getUsrCode());
				scmSupplierRegInvitation.setEditDate(createDate);
				this.update(scmSupplierRegInvitation, param);
				return scmSupplierRegInvitation;
			}else{
				scmSupplierRegInvitation = new ScmSupplierRegInvitation(true);
				scmSupplierRegInvitation.setVendorId(scmsupplier.getId());
				scmSupplierRegInvitation.setInvitationContent(url);
				scmSupplierRegInvitation.setExpiryDate(expiryDate);
				scmSupplierRegInvitation.setCreator(param.getUsrCode());
				scmSupplierRegInvitation.setCreateDate(createDate);
				scmSupplierRegInvitation.setControlUnitNo(scmsupplier.getControlUnitNo());
				this.add(scmSupplierRegInvitation, param);
				return scmSupplierRegInvitation;
			}
		}
		return null;
	}

	@Override
	public List<ScmSupplierRegInvitation> selectBindedByCtrlAndVendor(String controlUnitNo, String vendorIds,
			Param param) throws AppException {
		HashMap<String, Object> map = new HashMap<>();
		map.put("controlUnitNo", controlUnitNo);
		map.put("vendorIds", vendorIds);
		return ((ScmSupplierRegInvitationDAO) dao).selectBindedByCtrlAndVendor(map);
	}
}
