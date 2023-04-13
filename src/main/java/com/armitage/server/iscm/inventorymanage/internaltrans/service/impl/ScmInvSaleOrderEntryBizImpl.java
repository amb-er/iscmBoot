package com.armitage.server.iscm.inventorymanage.internaltrans.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.inventorymanage.internaltrans.dao.ScmInvSaleOrderEntryDAO;
import com.armitage.server.iscm.inventorymanage.internaltrans.model.ScmInvSaleOrder2;
import com.armitage.server.iscm.inventorymanage.internaltrans.model.ScmInvSaleOrderEntry2;
import com.armitage.server.iscm.inventorymanage.internaltrans.service.ScmInvSaleOrderBiz;
import com.armitage.server.iscm.inventorymanage.internaltrans.service.ScmInvSaleOrderEntryBiz;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvSaleIssueBillEntry2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.service.ScmPurOrderEntryBiz;
import org.springframework.stereotype.Service;

@Service("scmInvSaleOrderEntryBiz")
public class ScmInvSaleOrderEntryBizImpl extends BaseBizImpl<ScmInvSaleOrderEntry2> implements ScmInvSaleOrderEntryBiz {

    private ScmInvSaleOrderBiz scmInvSaleOrderBiz;
    private ScmPurOrderEntryBiz scmPurOrderEntryBiz;

    public void setScmInvSaleOrderBiz(ScmInvSaleOrderBiz scmInvSaleOrderBiz) {
		this.scmInvSaleOrderBiz = scmInvSaleOrderBiz;
	}
    
    public void setScmPurOrderEntryBiz(ScmPurOrderEntryBiz scmPurOrderEntryBiz) {
		this.scmPurOrderEntryBiz = scmPurOrderEntryBiz;
	}

	@Override
    public List<ScmInvSaleOrderEntry2> selectByOtId(long otId, Param param) throws AppException {
        if(otId > 0){
            HashMap<String, Object> map = new HashMap<>();
            map.put("otId", otId);
            return ((ScmInvSaleOrderEntryDAO) dao).selectByOtId(map);
        }
        return null;
    }

    @Override
    public void deleteByOtId(long otId, Param param) throws AppException {
        if(otId > 0){
        	List<ScmInvSaleOrderEntry2> scmInvSaleOrderEntryList = this.selectByOtId(otId, param);
            this.delete(scmInvSaleOrderEntryList, param);
        }
    }

	@Override
	public void writeBackBySaleIssue(ScmInvSaleIssueBillEntry2 oldEntity,ScmInvSaleIssueBillEntry2 newEntity, Param param)
			throws AppException {
		BigDecimal qty = BigDecimal.ZERO;
		if(newEntity!=null && newEntity.getSourceBillDtlId()>0){
			qty = qty.add(newEntity.getBaseQty());
		}
		if(oldEntity!=null && oldEntity.getSourceBillDtlId()>0){
			qty = qty.subtract(oldEntity.getBaseQty());
		}
		if(newEntity!=null && newEntity.getSourceBillDtlId()>0 || (oldEntity!=null && oldEntity.getSourceBillDtlId()>0)){
			ScmInvSaleOrderEntry2 scmInvSaleOrderEntry = this.selectDirect(newEntity!=null?newEntity.getSourceBillDtlId():oldEntity.getSourceBillDtlId(), param);
			if(scmInvSaleOrderEntry!=null) {
				scmInvSaleOrderEntry.setOutQty(scmInvSaleOrderEntry.getOutQty().add(qty));
				this.updateDirect(scmInvSaleOrderEntry, param);
				this.updateBillStatusByEntry(scmInvSaleOrderEntry, param);
			}
		}
	}

	private void updateBillStatusByEntry(ScmInvSaleOrderEntry2 scmInvSaleOrderEntry, Param param) {
		if (scmInvSaleOrderEntry != null ) {
			ScmInvSaleOrder2 scmInvSaleOrder = scmInvSaleOrderBiz.selectDirect(scmInvSaleOrderEntry.getOtId(), param);
			if (scmInvSaleOrder != null) {
				List<ScmInvSaleOrderEntry2> list = this.selectNotOut(scmInvSaleOrderEntry.getOtId(), param);
				if (list != null && !list.isEmpty()) {
					int count1 = 0;// 记录下达条数
					int count2 = 0;// 记录关闭条数
					for (ScmInvSaleOrderEntry2 entry : list) {
						if (entry.getOutQty().compareTo(entry.getQty())<0) {
							count1++;
						} else {
							count2++;
						}
					}
					if (count2 > 0) {
						// 关闭条数大于0时，等于总条数时订货单状态为关闭，否则为部分关闭
						if (count2 == list.size()) {
							scmInvSaleOrder.setStatus("C");
						} else {
							scmInvSaleOrder.setStatus("F");
						}
					} else if (count2 == 0 && count1 > 0) {
						// 下达条数大于0时，等于总条数时订货单状态为下达，否则为部分下达
						if (count1 == list.size()) {
							scmInvSaleOrder.setStatus("E");
						} else {
							scmInvSaleOrder.setStatus("S");
						}
					} else if (count2 == 0 && count1 == 0) {
						scmInvSaleOrder.setStatus("A");
					}
					scmInvSaleOrderBiz.updateDirect(scmInvSaleOrder, param);
				}else {
					scmInvSaleOrder.setStatus("C");
					scmInvSaleOrderBiz.updateDirect(scmInvSaleOrder, param);
				}
			}
		}
	}
	
//	@Override
//	protected void afterAdd(ScmInvSaleOrderEntry2 entity, Param param)
//			throws AppException {
//		scmPurOrderEntryBiz.writeBackBySaleOrder(null, entity, param);
//	}

	@Override
	protected void afterUpdate(ScmInvSaleOrderEntry2 oldEntity,
			ScmInvSaleOrderEntry2 newEntity, Param param) throws AppException {
		scmPurOrderEntryBiz.writeBackBySaleOrder(oldEntity, newEntity, param);
	}

	@Override
	protected void afterDelete(ScmInvSaleOrderEntry2 entity, Param param) throws AppException {
		scmPurOrderEntryBiz.writeBackBySaleOrder(entity, null, param);
	}

	@Override
	public List<ScmInvSaleOrderEntry2> selectNotOut(long otId, Param param) throws AppException {
		if(otId > 0){
            HashMap<String, Object> map = new HashMap<>();
            map.put("otId", otId);
            map.put("notout", "1");
            return ((ScmInvSaleOrderEntryDAO) dao).selectByOtId(map);
        }
        return null;
	}

}
