package com.armitage.server.api.common;

import java.util.HashMap;

import org.apache.commons.lang.StringUtils;

import com.armitage.server.common.base.model.QueryParam;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="ApiLogicSymbol",description="参数比较符号")
public class ApiLogicSymbol {
    @ApiModelProperty(value="*none",dataType="Map",required=true)
    private HashMap<String, String> map;

    public ApiLogicSymbol(HashMap<String, String> map) {
        this.map = map;
    }
    public ApiLogicSymbol() {
    }

    public HashMap<String, String> getMap() {
        // 对值进行转换,将=转化为eq
        if (map != null) {
            String[] keys = map.keySet().toArray(new String[0]);
            for (String key : keys) {
                if (StringUtils.equals(map.get(key), "="))
                    map.put(key, QueryParam.QUERY_EQ);
            }
        }

        return map;
    }

    public void setMap(HashMap<String, String> map) {
        this.map = map;
    }
}
