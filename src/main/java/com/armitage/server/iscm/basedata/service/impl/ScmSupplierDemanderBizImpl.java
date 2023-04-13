package com.armitage.server.iscm.basedata.service.impl;

import java.util.Calendar;
import java.util.HashMap;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.basedata.dao.ScmSupplierDemanderDAO;
import com.armitage.server.iscm.basedata.model.ScmSupplierDemander;
import com.armitage.server.iscm.basedata.service.ScmSupplierDemanderBiz;
import org.springframework.stereotype.Service;

@Service("scmSupplierDemanderBiz")
public class ScmSupplierDemanderBizImpl extends BaseBizImpl<ScmSupplierDemander> implements ScmSupplierDemanderBiz {
	
	@Override
	public ScmSupplierDemander selectByCtrl(String controlUnitNo, Param param) throws AppException {
		HashMap<String, Object> map = new HashMap<>();
		map.put("controlUnitNo", controlUnitNo);
		return ((ScmSupplierDemanderDAO) dao).selectByCtrl(map);
	}
	
	@Override
	public ScmSupplierDemander selectByDemanderId(long demanderId, Param param) throws AppException {
		HashMap<String, Object> map = new HashMap<>();
		map.put("demanderId", demanderId);
		return ((ScmSupplierDemanderDAO) dao).selectByDemanderId(map);
	}
	
	@Override
	public ScmSupplierDemander addByIdAndCtrl(long demanderId, String controlUnitNo, Param param) throws AppException {
		if(StringUtils.isNotBlank(controlUnitNo) && demanderId > 0){
			ScmSupplierDemander scmSupplierDemander = this.selectByCtrl(controlUnitNo, param);
			if(scmSupplierDemander != null && scmSupplierDemander.getDemanderId() > 0){
				return null;
			}else{
				String uniqueNo = Calendar.getInstance().getTimeInMillis() + StringUtils.replace(UUID.randomUUID().toString(), "-", "");
				if(scmSupplierDemander != null){
					scmSupplierDemander.setDemanderId(demanderId);
					if(StringUtils.isBlank(scmSupplierDemander.getUniqueNo())){
						scmSupplierDemander.setUniqueNo(uniqueNo);
					}
					this.update(scmSupplierDemander, param);
				}else{
					scmSupplierDemander = new ScmSupplierDemander(true);
					scmSupplierDemander.setDemanderId(demanderId);
					scmSupplierDemander.setUniqueNo(uniqueNo);
					scmSupplierDemander.setControlUnitNo(controlUnitNo);
					this.add(scmSupplierDemander, param);
				}
				if(scmSupplierDemander.getDemanderId() > 0){
					return scmSupplierDemander;
				}
			}
		}
		return null;
	}
}

