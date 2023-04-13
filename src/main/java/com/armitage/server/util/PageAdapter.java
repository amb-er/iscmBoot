/*
 * Copyright (c) 2018-2999 广州中昱信息科技有限公司 All rights reserved.
 *
 * https://www.huzhukang.com/
 *
 * 未经允许，不可做商业用途！
 *
 * 版权所有，侵权必究！
 */

package com.armitage.server.util;

import cn.hutool.core.util.PageUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

/**
 * 分页适配
 * @author LGH
 */
@Data
public class PageAdapter{

    private int begin;

    private int size;

    public PageAdapter(Page page) {
        int[] startEnd = PageUtil.transToStartEnd((int) page.getCurrent() - 1, (int) page.getSize());
        this.begin = startEnd[0];
        this.size = (int)page.getSize();
    }
}
