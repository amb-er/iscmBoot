
package com.armitage.server.iscm.basedata.service.impl;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.CommonBean;
import com.armitage.server.common.base.model.Page;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.base.model.QueryParam;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.util.ClassUtils;
import com.armitage.server.iscm.basedata.dao.ScmAuditMsgTempletDAO;
import com.armitage.server.iscm.basedata.model.ScmAuditMsgTemplet2;
import com.armitage.server.iscm.basedata.model.ScmAuditMsgTempletChannel;
import com.armitage.server.iscm.basedata.service.ScmAuditMsgTempletBiz;
import com.armitage.server.iscm.basedata.service.ScmAuditMsgTempletChannelBiz;
import org.springframework.stereotype.Service;

@Service("scmAuditMsgTempletBiz")
public class ScmAuditMsgTempletBizImpl extends BaseBizImpl<ScmAuditMsgTemplet2> implements ScmAuditMsgTempletBiz {
	private ScmAuditMsgTempletChannelBiz scmAuditMsgTempletChannelBiz;
	
	public void setScmAuditMsgTempletChannelBiz(ScmAuditMsgTempletChannelBiz scmAuditMsgTempletChannelBiz) {
		this.scmAuditMsgTempletChannelBiz = scmAuditMsgTempletChannelBiz;
	}

	@Override
	public ScmAuditMsgTemplet2 selectByTempetType(String templetType,Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("templetType", templetType);
		map.put("controlUnitNo", param.getControlUnitNo());
		ScmAuditMsgTemplet2 scmAuditMsgTemplet= ((ScmAuditMsgTempletDAO)dao).selectByTempetType(map);
		if(scmAuditMsgTemplet!=null)
			scmAuditMsgTemplet.setScmAuditMsgTempletChannelList(scmAuditMsgTempletChannelBiz.selectByTempetId(scmAuditMsgTemplet.getId(),param));
		return scmAuditMsgTemplet;
	}
	
	@Override
	protected void beforeAdd(CommonBean bean, Param param) throws AppException {
		ScmAuditMsgTemplet2 scmAuditMsgTemplet2 = (ScmAuditMsgTemplet2)bean.getList().get(0);
		if (scmAuditMsgTemplet2 != null) {
			scmAuditMsgTemplet2.setControlUnitNo(param.getControlUnitNo());
		}
	}
	
	@Override
	protected void afterAdd(CommonBean bean, Param param) throws AppException {
		ScmAuditMsgTemplet2 scmAuditMsgTemplet2 = (ScmAuditMsgTemplet2)bean.getList().get(0);
		if (scmAuditMsgTemplet2 != null && scmAuditMsgTemplet2.getId()>0) {
			ScmAuditMsgTempletChannel scmAuditMsgTempletChannel = new ScmAuditMsgTempletChannel(true);
			scmAuditMsgTempletChannel.setTempletId(scmAuditMsgTemplet2.getId());
			scmAuditMsgTempletChannel.setChannelCode(scmAuditMsgTemplet2.getChannelCode());
			scmAuditMsgTempletChannel.setTemplateId(scmAuditMsgTemplet2.getTemplateId());
			scmAuditMsgTempletChannelBiz.add(scmAuditMsgTempletChannel, param);
			scmAuditMsgTemplet2.setChannelId(scmAuditMsgTempletChannel.getId());
		}
	}
	
	protected void afterSelect(CommonBean bean, Param param) throws AppException {
		ScmAuditMsgTempletChannel scmAuditMsgTempletChannel = (ScmAuditMsgTempletChannel)bean.getObject();
		ScmAuditMsgTemplet2 scmAuditMsgTemplet2 = (ScmAuditMsgTemplet2)bean.getList().get(0);
		if (scmAuditMsgTempletChannel != null && scmAuditMsgTempletChannel.getId()>0 && scmAuditMsgTemplet2 != null && scmAuditMsgTemplet2.getId()>0) {
			scmAuditMsgTemplet2.setChannelCode(scmAuditMsgTempletChannel.getChannelCode());
			scmAuditMsgTemplet2.setChannelId(scmAuditMsgTempletChannel.getId());
			scmAuditMsgTemplet2.setTemplateId(scmAuditMsgTempletChannel.getTemplateId());
		}
	}
	
	protected void beforeSelect(CommonBean bean, Param param) throws AppException {
		ScmAuditMsgTemplet2 scmAuditMsgTemplet2 = (ScmAuditMsgTemplet2)bean.getList().get(0);
		if (scmAuditMsgTemplet2 != null && scmAuditMsgTemplet2.getChannelId()>0) {
			ScmAuditMsgTempletChannel scmAuditMsgTempletChannel = scmAuditMsgTempletChannelBiz.select(scmAuditMsgTemplet2.getChannelId(), param);
			if (scmAuditMsgTempletChannel != null) {
				bean.setObject(scmAuditMsgTempletChannel);
			}
		}
	}
	
	@Override
	protected void beforeUpdate(CommonBean bean, Param param) throws AppException {
		ScmAuditMsgTemplet2 scmAuditMsgTemplet2 = (ScmAuditMsgTemplet2)bean.getList().get(0);
		if (scmAuditMsgTemplet2 != null && scmAuditMsgTemplet2.getId()>0) {
			ScmAuditMsgTempletChannel scmAuditMsgTempletChannel = new ScmAuditMsgTempletChannel(true);
			if (scmAuditMsgTemplet2.getChannelId() >0) {
				scmAuditMsgTempletChannel.setId(scmAuditMsgTemplet2.getChannelId());
				scmAuditMsgTempletChannel.setTempletId(scmAuditMsgTemplet2.getId());
				scmAuditMsgTempletChannel.setChannelCode(scmAuditMsgTemplet2.getChannelCode());
				scmAuditMsgTempletChannel.setTemplateId(scmAuditMsgTemplet2.getTemplateId());
				scmAuditMsgTempletChannelBiz.update(scmAuditMsgTempletChannel, param);
			}else {
				scmAuditMsgTempletChannel.setId(scmAuditMsgTemplet2.getChannelId());
				scmAuditMsgTempletChannel.setTempletId(scmAuditMsgTemplet2.getId());
				scmAuditMsgTempletChannel.setChannelCode(scmAuditMsgTemplet2.getChannelCode());
				scmAuditMsgTempletChannel.setTemplateId(scmAuditMsgTemplet2.getTemplateId());
				scmAuditMsgTempletChannelBiz.add(scmAuditMsgTempletChannel, param);
			}
		}
	}
	
	@Override
	protected void beforeDelete(ScmAuditMsgTemplet2 entity, Param param) throws AppException {
		if (entity != null && entity.getChannelId()>0) {
			ScmAuditMsgTempletChannel scmAuditMsgTempletChannel = new ScmAuditMsgTempletChannel(true);
			scmAuditMsgTempletChannel.setId(entity.getChannelId());
			scmAuditMsgTempletChannelBiz.delete(scmAuditMsgTempletChannel, param);
		}
	}
	
	@Override
	protected void afterDelete(ScmAuditMsgTemplet2 entity, Param param) throws AppException {
		if (entity != null) {
			List<ScmAuditMsgTempletChannel> scmAuditMsgTempletChannels = scmAuditMsgTempletChannelBiz.selectByTempetId(entity.getId(), param);
			if (scmAuditMsgTempletChannels != null && !scmAuditMsgTempletChannels.isEmpty()) {
				int add = ((ScmAuditMsgTempletDAO)dao).add(entity);
				for (ScmAuditMsgTempletChannel scmAuditMsgTempletChannel : scmAuditMsgTempletChannels) {
					scmAuditMsgTempletChannel.setTempletId(entity.getId());
				}
				scmAuditMsgTempletChannelBiz.update(scmAuditMsgTempletChannels, param);
			}
		}
	}
	
	@Override
	protected void beforeFindPage(Page page, Param param) throws AppException {
		page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmAuditMsgTemplet2.class) + "." + ScmAuditMsgTemplet2.FN_CONTROLUNITNO), 
				new QueryParam((ClassUtils.getFinalModelSimpleName(ScmAuditMsgTemplet2.class) + "." + ScmAuditMsgTemplet2.FN_CONTROLUNITNO), QueryParam.QUERY_EQ, param.getControlUnitNo()));
	}
	
}
