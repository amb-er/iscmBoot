package com.armitage.server.api.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="页码对象",description="页码")
public class PageNum {
    @ApiModelProperty(value="*页码",dataType="Integer",example="1",required=true)
    private Integer pageNum;
    public PageNum() {
        super();
    }

    public PageNum(Integer pageNum) {
        super();
        this.pageNum = pageNum;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    @Override
    public String toString() {
        return "PageNum [pageNum=" + pageNum + "]";
    }
    
}
