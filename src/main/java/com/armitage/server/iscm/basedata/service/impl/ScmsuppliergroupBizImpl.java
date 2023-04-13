package com.armitage.server.iscm.basedata.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Page;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.base.model.QueryParam;
import com.armitage.server.common.cache.ModelCacheMana;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.basedata.dao.ScmsuppliergroupDAO;
import com.armitage.server.iscm.basedata.model.Scmsuppliergroup;
import com.armitage.server.iscm.basedata.model.Scmsuppliergroup2;
import com.armitage.server.iscm.basedata.model.Scmsuppliergroupstandard;
import com.armitage.server.iscm.basedata.service.ScmsuppliergroupBiz;
import com.armitage.server.iscm.basedata.service.ScmsuppliergroupdetailBiz;
import com.armitage.server.iscm.basedata.service.ScmsuppliergroupstandardBiz;
import org.springframework.stereotype.Service;

@Service("scmsuppliergroupBiz")
public class ScmsuppliergroupBizImpl extends BaseBizImpl<Scmsuppliergroup2> implements ScmsuppliergroupBiz {

	private ScmsuppliergroupstandardBiz scmsuppliergroupstandardBiz;
	private ScmsuppliergroupdetailBiz scmsuppliergroupdetailBiz;
	private List<Long> longNoList = new ArrayList<Long>();

	public void setScmsuppliergroupstandardBiz(ScmsuppliergroupstandardBiz scmsuppliergroupstandardBiz) {
		this.scmsuppliergroupstandardBiz = scmsuppliergroupstandardBiz;
	}
	
	public void setScmsuppliergroupdetailBiz(ScmsuppliergroupdetailBiz scmsuppliergroupdetailBiz) {
		this.scmsuppliergroupdetailBiz = scmsuppliergroupdetailBiz;
	}
	
	@Override
	protected void beforeFindPage(Page page, Param param) throws AppException {
		page.getParam().put(("a." + Scmsuppliergroup2.FN_CONTROLUNITNO), 
				new QueryParam(("a." + Scmsuppliergroup2.FN_CONTROLUNITNO), QueryParam.QUERY_EQ, param.getControlUnitNo()));
	}

	@Override
	protected void afterSelect(Scmsuppliergroup2 entity, Param param) throws AppException {
		Scmsuppliergroup2 scmsuppliergroup = entity;
		if(scmsuppliergroup !=null && scmsuppliergroup.getStandardId() > 0){
			//分类标准
			Scmsuppliergroupstandard scmsuppliergroupstandard = scmsuppliergroupstandardBiz.selectDirect(scmsuppliergroup.getStandardId(), param);
			if(scmsuppliergroupstandard != null){
				scmsuppliergroup.setConvertMap(Scmsuppliergroup.FN_STANDARDID, scmsuppliergroupstandard);
			}
		}
	}
	
	@Override
	protected void afterDelete(Scmsuppliergroup2 entity, Param param) throws AppException {
		Scmsuppliergroup2 scmsuppliergroup = entity;
		//删除供应商分类关系
		if(scmsuppliergroup != null && scmsuppliergroup.getId() > 0){
			scmsuppliergroupdetailBiz.deleteByVendorIdOrGroupId(0, scmsuppliergroup.getId(), param);
		}
	}

    @Override
    public List<Scmsuppliergroup2> findChild(long venderClassId, Param param) throws AppException {
        HashMap<String, Object> map = new HashMap<String, Object>();
        Scmsuppliergroup2 scmSupplierGroup = this.select(venderClassId, param);
        if (scmSupplierGroup == null) {
            return null;
        }
        map.put("controlUnitNo", scmSupplierGroup.getControlUnitNo());
        if (scmSupplierGroup.getParentId() == 0) {
            map.put("longNo", venderClassId+",%"); 
        } else if (scmSupplierGroup.getParentId() > 0) {
            map.put("longNo", "%," + venderClassId+",%");
        }
        return ((ScmsuppliergroupDAO)dao).findChild(map);
    }

	@Override
	protected void beforeAdd(Scmsuppliergroup2 entity, Param param) throws AppException {
	    if (entity != null) {
            if (entity.getParentId() == 0) {
                entity.setLongNo("0");
            } else if(entity.getParentId() > 0) {
                StringBuilder sbLongNo = new StringBuilder("");
                selectParent(entity.getParentId(), param);
                if (longNoList != null && !longNoList.isEmpty()) {
                    for (int i = longNoList.size()-1; i >= 0; i--) {
                        sbLongNo.append(longNoList.get(i)).append(",");
                    }
                    longNoList.clear();
                    entity.setLongNo(sbLongNo.toString());
                }
            }
        }
	}

	private void selectParent(Long id, Param param){
	    Scmsuppliergroup parentEntity = this.select(id, param);
        if (parentEntity != null) {
            longNoList.add(parentEntity.getId());
            selectParent(parentEntity.getParentId(), param);
        }
    }
	
    @Override
    protected void afterAdd(Scmsuppliergroup2 entity, Param param) throws AppException {
        if (entity != null) {
            if (entity.getParentId() == 0) {
                entity.setLongNo(entity.getId()+",");
            } else if(entity.getParentId() > 0) {
                entity.setLongNo(entity.getLongNo()+entity.getId()+",");
            }
            this.updateDirect(entity, param);
        }
    }

	@Override
	public Scmsuppliergroup2 selectByClassCode(long standardId, String classCode, Param param) throws AppException {
		HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("standardId", standardId);
        map.put("classCode", classCode);
        map.put("controlUnitNo", param.getControlUnitNo());
        return ((ScmsuppliergroupDAO)dao).selectByClassCode(map);
	}

	@Override
	public Scmsuppliergroup2 selectByVendorId(long vendorId, Param param) throws AppException {
		String key = "supplier_"+vendorId;
		if (ModelCacheMana.keyExists(key, modelClassById == null ? modelClass : modelClassById)) {
			Object obj = ModelCacheMana.get(key, modelClassById == null ? modelClass : modelClassById);
			if (obj != null && obj.getClass().equals(modelClass)) {
				return (Scmsuppliergroup2) obj;
			}
		}
		HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("vendorId", vendorId);
        Scmsuppliergroup2 scmsuppliergroup =  ((ScmsuppliergroupDAO)dao).selectByVendorId(map);
		if (scmsuppliergroup != null) {
			ModelCacheMana.set(key, scmsuppliergroup);
		}
		return scmsuppliergroup;
	}
    
}
