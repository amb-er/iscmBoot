package com.armitage.server.iscm.purchasemanage.pricemanage.model;

import java.util.Date;
import java.util.List;

public class ScmPurPriceImPort {
    
    private Date pmDate;
    private Date begDate;
    private Date endDate;
    private long groupId1;
    private long groupId2;
    private long groupId3;
    private long vendorId1;
    private long vendorId2;
    private long vendorId3;
    private long selVndrId;
    private String orgUnitNo;
    private String remarks;
    private List<ScmPurPriceImPortDetail> detailList;
    public Date getPmDate() {
        return pmDate;
    }
    public void setPmDate(Date pmDate) {
        this.pmDate = pmDate;
    }
    public Date getBegDate() {
        return begDate;
    }
    public void setBegDate(Date begDate) {
        this.begDate = begDate;
    }
    public Date getEndDate() {
        return endDate;
    }
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    public long getGroupId1() {
        return groupId1;
    }
    public void setGroupId1(long groupId1) {
        this.groupId1 = groupId1;
    }
    public long getGroupId2() {
        return groupId2;
    }
    public void setGroupId2(long groupId2) {
        this.groupId2 = groupId2;
    }
    public long getGroupId3() {
        return groupId3;
    }
    public void setGroupId3(long groupId3) {
        this.groupId3 = groupId3;
    }
    public long getVendorId1() {
        return vendorId1;
    }
    public void setVendorId1(long vendorId1) {
        this.vendorId1 = vendorId1;
    }
    public long getVendorId2() {
        return vendorId2;
    }
    public void setVendorId2(long vendorId2) {
        this.vendorId2 = vendorId2;
    }
    public long getVendorId3() {
        return vendorId3;
    }
    public void setVendorId3(long vendorId3) {
        this.vendorId3 = vendorId3;
    }
    public long getSelVndrId() {
        return selVndrId;
    }
    public void setSelVndrId(long selVndrId) {
        this.selVndrId = selVndrId;
    }
    public String getOrgUnitNo() {
        return orgUnitNo;
    }
    public void setOrgUnitNo(String orgUnitNo) {
        this.orgUnitNo = orgUnitNo;
    }
    public String getRemarks() {
        return remarks;
    }
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    public List<ScmPurPriceImPortDetail> getDetailList() {
        return detailList;
    }
    public void setDetailList(List<ScmPurPriceImPortDetail> detailList) {
        this.detailList = detailList;
    }
    
}
