package com.armitage.server.iscm.report.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.armitage.server.common.cache.ModelCacheMana;

public class RDPReportUtil {

	public static String getQueryData(HttpServletRequest request){
		String RDPToken=request.getParameter("RDPToken");
		if(StringUtils.isNotBlank(RDPToken)){
			String timeKey = RDPToken.replace("-", "_")+"_time";
			String dataKey = RDPToken.replace("-", "_")+"_data";
			String time = ModelCacheMana.get(timeKey);
			if (StringUtils.isNotBlank(time)) {
				long t1 = Long.parseLong(time);
				long t2 = System.currentTimeMillis();
				if(t2 > t1 && t2-t1 <= 1000){
					//第二次进入
					return ModelCacheMana.get(dataKey);
				}
			}
		}
		return null;
	}
}
