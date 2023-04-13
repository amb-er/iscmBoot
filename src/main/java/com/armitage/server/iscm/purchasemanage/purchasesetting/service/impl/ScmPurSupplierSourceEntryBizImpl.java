
package com.armitage.server.iscm.purchasemanage.purchasesetting.service.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.basedata.model.PubSysBasicInfo;
import com.armitage.server.iscm.basedata.service.PubSysBasicInfoBiz;
import com.armitage.server.iscm.purchasemanage.purchasesetting.dao.ScmPurSupplierSourceEntryDAO;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurSupplierSourceEntry2;
import com.armitage.server.iscm.purchasemanage.purchasesetting.service.ScmPurSupplierSourceEntryBiz;
import com.armitage.server.system.model.Code;
import com.armitage.server.system.service.CodeBiz;
import org.springframework.stereotype.Service;

@Service("scmPurSupplierSourceEntryBiz")
public class ScmPurSupplierSourceEntryBizImpl extends BaseBizImpl<ScmPurSupplierSourceEntry2> implements ScmPurSupplierSourceEntryBiz {
	private PubSysBasicInfoBiz pubSysBasicInfoBiz;
	private CodeBiz codeBiz;
	
	public void setPubSysBasicInfoBiz(PubSysBasicInfoBiz pubSysBasicInfoBiz) {
		this.pubSysBasicInfoBiz = pubSysBasicInfoBiz;
	}

	public void setCodeBiz(CodeBiz codeBiz) {
		this.codeBiz = codeBiz;
	}

	@Override
	public void deleteByBillId(long billId, Param param) throws AppException {
		if(billId > 0){
			List<ScmPurSupplierSourceEntry2> scmPurSupplierSourceEntryList = this.selectByBillId(billId,param);
			this.delete(scmPurSupplierSourceEntryList, param);
		}
	}

	@Override
	public List<ScmPurSupplierSourceEntry2> selectByBillId(long billId, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("billId", billId);
		List<ScmPurSupplierSourceEntry2> scmPurSupplierSourceEntryList = ((ScmPurSupplierSourceEntryDAO)dao).selectByBillId(map);
		if(scmPurSupplierSourceEntryList!=null && !scmPurSupplierSourceEntryList.isEmpty()) {
			for(ScmPurSupplierSourceEntry2 scmPurSupplierSourceEntry:scmPurSupplierSourceEntryList) {
				this.setConvertMap(scmPurSupplierSourceEntry, param);
			}
		}
		return scmPurSupplierSourceEntryList;
	}
	
	private void setConvertMap(ScmPurSupplierSourceEntry2 scmPurSupplierSourceEntry,Param param) {
		if(scmPurSupplierSourceEntry!=null) {
			if (scmPurSupplierSourceEntry.getTaxRate() != null){
				//税率
				PubSysBasicInfo pubSysBasicInfo = pubSysBasicInfoBiz.selectByTaxRate("TaxRate", scmPurSupplierSourceEntry.getTaxRate().toString(), null, param);
				if (pubSysBasicInfo != null) {
					scmPurSupplierSourceEntry.setTaxRateStr(pubSysBasicInfo.getFInfoNo());
					scmPurSupplierSourceEntry.setConvertMap(ScmPurSupplierSourceEntry2.FN_TAXRATESTR, pubSysBasicInfo);
				}
			}
			if(StringUtils.isNotBlank(scmPurSupplierSourceEntry.getRefPriceStatus())) {
				Code code = codeBiz.selectByCategoryAndCode("ScmRefPriceType", scmPurSupplierSourceEntry.getRefPriceStatus());
				if(code!=null)
					scmPurSupplierSourceEntry.setRefPriceStatusName(code.getName());
			}
		}
	}
}
