package com.armitage.server.ifbc.costcard.service.impl;

import java.util.List;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Page;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.base.model.QueryParam;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.util.ClassUtils;
import com.armitage.server.ifbc.costcard.model.ScmStandardRatio2;
import com.armitage.server.ifbc.costcard.model.ScmStandardRatioDetail;
import com.armitage.server.ifbc.costcard.service.ScmStandardRatioBiz;
import com.armitage.server.ifbc.costcard.service.ScmStandardRatioDetailBiz;
import com.armitage.server.iscm.basedata.model.ScmMaterial2;
import com.armitage.server.iscm.basedata.model.ScmMeasureUnit;
import com.armitage.server.iscm.basedata.service.ScmMaterialBiz;
import com.armitage.server.iscm.basedata.service.ScmMeasureUnitBiz;
import org.springframework.stereotype.Service;

@Service("scmStandardRatioBiz")
public class ScmStandardRatioBizImpl extends BaseBizImpl<ScmStandardRatio2> implements ScmStandardRatioBiz {
	private ScmStandardRatioDetailBiz scmStandardRatioDetailBiz;
	private ScmMeasureUnitBiz scmMeasureUnitBiz;
	private ScmMaterialBiz scmMaterialBiz;
	
	public void setScmStandardRatioDetailBiz(ScmStandardRatioDetailBiz scmStandardRatioDetailBiz) {
		this.scmStandardRatioDetailBiz = scmStandardRatioDetailBiz;
	}

	public void setScmMeasureUnitBiz(ScmMeasureUnitBiz scmMeasureUnitBiz) {
		this.scmMeasureUnitBiz = scmMeasureUnitBiz;
	}

	public void setScmMaterialBiz(ScmMaterialBiz scmMaterialBiz) {
		this.scmMaterialBiz = scmMaterialBiz;
	}

	@Override
	protected void afterAdd(ScmStandardRatio2 entity, Param param) throws AppException {
		ScmStandardRatioDetail scmStandardRatioDetail = new ScmStandardRatioDetail(true);
		scmStandardRatioDetail.setRateId(entity.getId());
		scmStandardRatioDetail.setItemId(entity.getItemId());
		scmStandardRatioDetail.setInvUnit(entity.getInvUnit());
		scmStandardRatioDetail.setQty(entity.getWoolQty());
		scmStandardRatioDetail.setNetRate(entity.getNetRate());
		scmStandardRatioDetailBiz.add(scmStandardRatioDetail, param);
	}

	@Override
	protected void afterDelete(ScmStandardRatio2 entity, Param param) throws AppException {
		scmStandardRatioDetailBiz.deleteByRateId(entity.getId(),param);
	}

	@Override
	protected void afterUpdate(ScmStandardRatio2 oldEntity, ScmStandardRatio2 newEntity, Param param)
			throws AppException {
		List<ScmStandardRatioDetail> scmStandardRatioDetailList = scmStandardRatioDetailBiz.selectByRateId(newEntity.getId(),param);
		if(scmStandardRatioDetailList!=null && !scmStandardRatioDetailList.isEmpty()) {
			for(ScmStandardRatioDetail scmStandardRatioDetail:scmStandardRatioDetailList) {
				scmStandardRatioDetail.setRateId(newEntity.getId());
				scmStandardRatioDetail.setItemId(newEntity.getItemId());
				scmStandardRatioDetail.setInvUnit(newEntity.getInvUnit());
				scmStandardRatioDetail.setQty(newEntity.getWoolQty());
				scmStandardRatioDetail.setNetRate(newEntity.getNetRate());
				scmStandardRatioDetailBiz.update(scmStandardRatioDetail, param);
			}
		}
	}
	
	@Override
	protected void afterSelect(ScmStandardRatio2 entity, Param param) throws AppException {
		List<ScmStandardRatioDetail> scmStandardRatioDetailList = scmStandardRatioDetailBiz.selectByRateId(entity.getId(),param);
		if(scmStandardRatioDetailList!=null && !scmStandardRatioDetailList.isEmpty()) {
			entity.setWoolQty(scmStandardRatioDetailList.get(0).getQty());
			entity.setNetRate(scmStandardRatioDetailList.get(0).getNetRate());
		}
		setConvertMap(entity,param);
	}

	@Override
	protected void afterFindPage(List list, Page page, Param param) throws AppException {
		if(list!=null) {
			for(ScmStandardRatio2 scmStandardRatio:(List<ScmStandardRatio2>)list) {
				setConvertMap(scmStandardRatio,param);
			}
		}
	}

	@Override
	protected void beforeFindPage(Page page, Param param) throws AppException {
		page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmStandardRatio2.class) + "." + ScmStandardRatio2.FN_ORGUNITNO), 
				new QueryParam((ClassUtils.getFinalModelSimpleName(ScmStandardRatio2.class) + "." + ScmStandardRatio2.FN_ORGUNITNO), QueryParam.QUERY_EQ, param.getOrgUnitNo()));
	}

	private void setConvertMap(ScmStandardRatio2 scmStandardRatio,Param param) {
		if(scmStandardRatio!=null) {
			if(scmStandardRatio.getInvUnit()>0) {
				ScmMeasureUnit scmMeasureUnit = scmMeasureUnitBiz.selectDirect(scmStandardRatio.getInvUnit(), param);
				if(scmMeasureUnit!=null) {
					scmStandardRatio.setConvertMap(ScmStandardRatio2.FN_INVUNIT, scmMeasureUnit);
				}
			}
			if(scmStandardRatio.getItemId()>0) {
				ScmMaterial2 scmMaterial = scmMaterialBiz.selectDirect(scmStandardRatio.getItemId(), param);
				if(scmMaterial!=null) {
					scmStandardRatio.setItemNo(scmMaterial.getItemNo());
					scmStandardRatio.setItemName(scmMaterial.getItemName());
				}
			}
		}
	}
}
