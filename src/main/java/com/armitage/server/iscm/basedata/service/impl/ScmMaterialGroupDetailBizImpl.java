
package com.armitage.server.iscm.basedata.service.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.codec.binary.StringUtils;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.basedata.dao.ScmMaterialGroupDetailDAO;
import com.armitage.server.iscm.basedata.model.ScmMaterialGroupDetail;
import com.armitage.server.iscm.basedata.model.ScmMaterialGroupDetail2;
import com.armitage.server.iscm.basedata.model.ScmMaterialGroupStandard2;
import com.armitage.server.iscm.basedata.service.ScmMaterialGroupDetailBiz;
import org.springframework.stereotype.Service;

@Service("scmMaterialGroupDetailBiz")
public class ScmMaterialGroupDetailBizImpl extends BaseBizImpl<ScmMaterialGroupDetail> implements ScmMaterialGroupDetailBiz {

	@Override
	public void deleteByItemIdOrGroupId(long itemId, long groupId, Param param)
			throws AppException {
		HashMap<String, Object> map = new HashMap<>();
		boolean flag = false;
		if(itemId > 0){
			flag = true;
			map.put("itemId", itemId);
		}
		if(groupId > 0){
			flag = true;
			map.put("classId", groupId);
		}
		if(flag){
			((ScmMaterialGroupDetailDAO) dao).deleteByItemIdOrGroupId(map);
		}
	}

	@Override
	public void updateAuxiGroup(long itemId, List<ScmMaterialGroupStandard2> scmMaterialGroupStandardList, Param param)
			throws AppException {
		List<ScmMaterialGroupDetail2> scmMaterialGroupDetailList = this.selectByItemId(itemId, param);
		if(scmMaterialGroupDetailList!=null && !scmMaterialGroupDetailList.isEmpty()) {
			if(scmMaterialGroupStandardList!=null && !scmMaterialGroupStandardList.isEmpty()) {
				for(ScmMaterialGroupStandard2 scmMaterialGroupStandard:scmMaterialGroupStandardList) {
					if(scmMaterialGroupStandard.getClassId()>0) {
						boolean exists=false;
						for(ScmMaterialGroupDetail2 scmMaterialGroupDetail2:scmMaterialGroupDetailList) {
							if(scmMaterialGroupStandard.getId()==scmMaterialGroupDetail2.getStandardId() && scmMaterialGroupDetail2.getClassId()==scmMaterialGroupStandard.getClassId()) {
								exists = true;
								break;
							}
						}
						if(!exists) {
							ScmMaterialGroupDetail scmMaterialGroupDetail = new ScmMaterialGroupDetail(true);
							scmMaterialGroupDetail.setStandardId(scmMaterialGroupStandard.getId());
							scmMaterialGroupDetail.setClassId(scmMaterialGroupStandard.getClassId());
							scmMaterialGroupDetail.setItemId(itemId);
							this.add(scmMaterialGroupDetail, param);
						}
					}
				}
				for (int i = scmMaterialGroupDetailList.size() - 1; i >= 0; i--) {
					ScmMaterialGroupDetail2 scmMaterialGroupDetail2=scmMaterialGroupDetailList.get(i);
					if(StringUtils.equals("1", scmMaterialGroupDetail2.getStandardType()))
						continue;
					boolean exists=false;
					for(ScmMaterialGroupStandard2 scmMaterialGroupStandard:scmMaterialGroupStandardList) {
						if(scmMaterialGroupStandard.getId()==scmMaterialGroupDetail2.getStandardId() && scmMaterialGroupDetail2.getClassId()==scmMaterialGroupStandard.getClassId()) {
							exists = true;
							break;
						}
					}
					if(!exists) {
						this.delete(scmMaterialGroupDetail2, param);
					}
				}
			}
		}else {
			if(scmMaterialGroupStandardList!=null && !scmMaterialGroupStandardList.isEmpty()) {
				for(ScmMaterialGroupStandard2 scmMaterialGroupStandard:scmMaterialGroupStandardList) {
					if(scmMaterialGroupStandard.getClassId()>0) {
						ScmMaterialGroupDetail scmMaterialGroupDetail = new ScmMaterialGroupDetail(true);
						scmMaterialGroupDetail.setStandardId(scmMaterialGroupStandard.getId());
						scmMaterialGroupDetail.setClassId(scmMaterialGroupStandard.getClassId());
						scmMaterialGroupDetail.setItemId(itemId);
						this.add(scmMaterialGroupDetail, param);
					}
				}
			}
		}
	}

	@Override
	public List<ScmMaterialGroupDetail2> selectByItemId(long itemId, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("itemId", itemId);
		return ((ScmMaterialGroupDetailDAO)dao).selectByItemId(map);
	}
	
}
