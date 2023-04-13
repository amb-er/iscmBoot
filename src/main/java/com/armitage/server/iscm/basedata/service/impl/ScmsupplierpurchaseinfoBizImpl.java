package com.armitage.server.iscm.basedata.service.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.util.BeanUtil;
import com.armitage.server.common.util.CalendarUtil;
import com.armitage.server.iscm.basedata.dao.ScmsupplierpurchaseinfoDAO;
import com.armitage.server.iscm.basedata.model.PubSysBasicInfo;
import com.armitage.server.iscm.basedata.model.Scmsupplierlinkman;
import com.armitage.server.iscm.basedata.model.Scmsupplierpurchaseinfo2;
import com.armitage.server.iscm.basedata.service.PubSysBasicInfoBiz;
import com.armitage.server.iscm.basedata.service.ScmsupplierlinkmanBiz;
import com.armitage.server.iscm.basedata.service.ScmsupplierpurchaseinfoBiz;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurBuyer2;
import com.armitage.server.iscm.purchasemanage.purchasesetting.service.ScmPurBuyerBiz;
import com.armitage.server.user.model.Usr;
import com.armitage.server.user.service.UsrBiz;
import org.springframework.stereotype.Service;

@Service("scmsupplierpurchaseinfoBiz")
public class ScmsupplierpurchaseinfoBizImpl extends BaseBizImpl<Scmsupplierpurchaseinfo2> implements ScmsupplierpurchaseinfoBiz {

	private ScmsupplierlinkmanBiz scmsupplierlinkmanBiz;
	private ScmPurBuyerBiz scmPurBuyerBiz;
	private UsrBiz usrBiz;
	private PubSysBasicInfoBiz pubSysBasicInfoBiz;
	
	public void setScmsupplierlinkmanBiz(ScmsupplierlinkmanBiz scmsupplierlinkmanBiz) {
		this.scmsupplierlinkmanBiz = scmsupplierlinkmanBiz;
	}

	public void setScmPurBuyerBiz(ScmPurBuyerBiz scmPurBuyerBiz) {
		this.scmPurBuyerBiz = scmPurBuyerBiz;
	}

	public void setUsrBiz(UsrBiz usrBiz) {
		this.usrBiz = usrBiz;
	}

	public void setPubSysBasicInfoBiz(PubSysBasicInfoBiz pubSysBasicInfoBiz) {
		this.pubSysBasicInfoBiz = pubSysBasicInfoBiz;
	}

	@Override
	protected void beforeAdd(Scmsupplierpurchaseinfo2 entity, Param param)
			throws AppException {
		if(entity!=null){
			entity.setCreator(param.getUsrCode());
			entity.setCreateDate(CalendarUtil.getDate(param));
//			if(StringUtils.isNotBlank(entity.getTaxRate())){
//				entity.setVatRate(new BigDecimal(entity.getTaxRate()));
//			}
		}
	}

	@Override
	protected void beforeUpdate(Scmsupplierpurchaseinfo2 oldEntity,
			Scmsupplierpurchaseinfo2 newEntity, Param param)
			throws AppException {
		if(newEntity!=null){
			if(StringUtils.isBlank(newEntity.getCreator()))
				newEntity.setCreator(param.getUsrCode());
			if(newEntity.getCreateDate()==null)
				newEntity.setCreateDate(CalendarUtil.getDate(param));
			newEntity.setEditor(param.getUsrCode());
			newEntity.setCreateDate(CalendarUtil.getDate(param));
//			if(StringUtils.isNotBlank(newEntity.getTaxRate())){
//				newEntity.setVatRate(new BigDecimal(newEntity.getTaxRate()));
//			}
		}
	}

	@Override
	public Scmsupplierpurchaseinfo2 selectByVendorIdAndOrgUnitNo(long vendorId, String resOrgUnitNo, Param param)
			throws AppException {
		HashMap<String, Object> map = new HashMap<>();
		map.put("vendorId", vendorId);
		map.put("orgUnitNo", resOrgUnitNo);
		Scmsupplierpurchaseinfo2 scmsupplierpurchaseinfo = ((ScmsupplierpurchaseinfoDAO) dao).selectByVendorIdAndOrgUnitNo(map);
		if(scmsupplierpurchaseinfo != null && scmsupplierpurchaseinfo.getVendorId() > 0){
			//加载联系人信息
			List<Scmsupplierlinkman> list = scmsupplierlinkmanBiz.selectByVendorId(scmsupplierpurchaseinfo.getVendorId(), param);
			if(list != null && !list.isEmpty()){
				scmsupplierpurchaseinfo.setScmsupplierlinkmanList(list);
			}
			setConvertMap(scmsupplierpurchaseinfo,param);
		}
		return scmsupplierpurchaseinfo;
	}
	
	@Override
	protected void afterAdd(Scmsupplierpurchaseinfo2 entity, Param param) throws AppException {
		if(entity != null 
				&& entity.getScmsupplierlinkmanList() != null
				&& !entity.getScmsupplierlinkmanList().isEmpty()){
			//新增联系人信息
			List<Scmsupplierlinkman> scmsupplierlinkmanList = entity.getScmsupplierlinkmanList();
			for(Scmsupplierlinkman scmsupplierlinkman : scmsupplierlinkmanList){
				if(scmsupplierlinkman.getId() > 0){
					scmsupplierlinkmanBiz.update(scmsupplierlinkman, param);
				}else{
					scmsupplierlinkmanBiz.add(scmsupplierlinkman, param);
				}
			}
		}
	}
	
	@Override
	protected void afterUpdate(Scmsupplierpurchaseinfo2 oldEntity,Scmsupplierpurchaseinfo2 entity, Param param) throws AppException {
		if(entity != null 
				&& entity.getScmsupplierlinkmanList() != null
				&& !entity.getScmsupplierlinkmanList().isEmpty()){
			//更新联系人信息
			List<Scmsupplierlinkman> scmsupplierlinkmanList = entity.getScmsupplierlinkmanList();
			for(Scmsupplierlinkman scmsupplierlinkman : scmsupplierlinkmanList){
				if(scmsupplierlinkman.getId() > 0){
					scmsupplierlinkmanBiz.update(scmsupplierlinkman, param);
				}else{
					scmsupplierlinkmanBiz.add(scmsupplierlinkman, param);
				}
			}
		}
	}
	
	@Override
	protected void afterDelete(Scmsupplierpurchaseinfo2 entity, Param param) throws AppException {
		if(entity != null && entity.getVendorId() > 0){
			//删除联系人信息
			scmsupplierlinkmanBiz.deleteByVendorId(entity.getVendorId(), param);
		}
	}
	
	private void setConvertMap(Scmsupplierpurchaseinfo2 scmsupplierpurchaseinfo, Param param){
		if(scmsupplierpurchaseinfo != null){
			if (scmsupplierpurchaseinfo.getBuyerId() > 0){
				//采购员
				ScmPurBuyer2 scmPurBuyer = scmPurBuyerBiz.selectDirect(scmsupplierpurchaseinfo.getBuyerId(), param);
				if (scmPurBuyer != null) {
					scmsupplierpurchaseinfo.setConvertMap(Scmsupplierpurchaseinfo2.FN_BUYERID, scmPurBuyer);
				}
			}
			if (StringUtils.isNotBlank(scmsupplierpurchaseinfo.getCreator())){
				//创建人
				Usr usr = usrBiz.selectByCode(scmsupplierpurchaseinfo.getCreator(), param);
				if (usr != null) {
					scmsupplierpurchaseinfo.setConvertMap(Scmsupplierpurchaseinfo2.FN_CREATOR, usr);
				}
			}
			if (StringUtils.isNotBlank(scmsupplierpurchaseinfo.getEditor())){
				//修改人
				Usr usr = usrBiz.selectByCode(scmsupplierpurchaseinfo.getEditor(), param);
				if (usr != null) {
					scmsupplierpurchaseinfo.setConvertMap(Scmsupplierpurchaseinfo2.FN_EDITOR, usr);
				}
			}
			if(scmsupplierpurchaseinfo != null && scmsupplierpurchaseinfo.getVatRate()!=null){
				PubSysBasicInfo pubSysBasicInfo = pubSysBasicInfoBiz.selectByTaxRate("TaxRate", scmsupplierpurchaseinfo.getVatRate().toString(), null, param);
				if (pubSysBasicInfo != null) {
					scmsupplierpurchaseinfo.setTaxRate(pubSysBasicInfo.getFInfoNo());
					scmsupplierpurchaseinfo.setConvertMap(Scmsupplierpurchaseinfo2.FN_TAXRATE, pubSysBasicInfo);
				}
			}

		}
	}

	@Override
	public Scmsupplierpurchaseinfo2 updateByPurchase(Scmsupplierpurchaseinfo2 scmsupplierpurchaseinfo, Param param)
			throws AppException {
		if(scmsupplierpurchaseinfo!=null) {
			Scmsupplierpurchaseinfo2 oldScmsupplierpurchaseinfo = this.selectByVendorIdAndOrgUnitNo(scmsupplierpurchaseinfo.getVendorId(),scmsupplierpurchaseinfo.getOrgUnitNo(),param);
			boolean exists = true;
			if(oldScmsupplierpurchaseinfo==null) {
				oldScmsupplierpurchaseinfo = new Scmsupplierpurchaseinfo2(true);
				exists = false;
			}
			long id = oldScmsupplierpurchaseinfo.getId();
			BeanUtil.copyProperties(oldScmsupplierpurchaseinfo, scmsupplierpurchaseinfo);
			oldScmsupplierpurchaseinfo.setId(id);
			if(exists) {
				this.update(oldScmsupplierpurchaseinfo, param);
			}else {
				this.add(oldScmsupplierpurchaseinfo, param);
			}
			return oldScmsupplierpurchaseinfo;
		}
		return null;
	}
}

