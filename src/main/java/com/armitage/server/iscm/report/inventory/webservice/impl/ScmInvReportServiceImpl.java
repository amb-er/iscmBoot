
package com.armitage.server.iscm.report.inventory.webservice.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Path;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.iscm.inventorymanage.inventorydata.model.ScmInvStockWSBean;
import com.armitage.server.iscm.report.inventory.model.RealtimeStock;
import com.armitage.server.iscm.report.inventory.model.ScmInvDepositorySumSup;
import com.armitage.server.iscm.report.inventory.model.ScmInvGlobalStock;
import com.armitage.server.iscm.report.inventory.model.ScmInvInWareMonthAnalysis;
import com.armitage.server.iscm.report.inventory.model.ScmInvInWarehsItemClass2;
import com.armitage.server.iscm.report.inventory.model.ScmInvItemInAndOutDetail;
import com.armitage.server.iscm.report.inventory.model.ScmInvItemInAndOutSum;
import com.armitage.server.iscm.report.inventory.model.ScmInvItemSaleSum;
import com.armitage.server.iscm.report.inventory.model.ScmInvItemWrDetails;
import com.armitage.server.iscm.report.inventory.model.ScmInvItemWrSum;
import com.armitage.server.iscm.report.inventory.model.ScmInvPurSalePrice;
import com.armitage.server.iscm.report.inventory.model.ScmInvRealtimeStockSum;
import com.armitage.server.iscm.report.inventory.model.ScmInvSaleBusiness;
import com.armitage.server.iscm.report.inventory.model.ScmInvStorageAgeAnalysis;
import com.armitage.server.iscm.report.inventory.model.ScmInvStorageAgePrimnessAnalysis;
import com.armitage.server.iscm.report.inventory.model.ScmPurVendorInfo;
import com.armitage.server.iscm.report.inventory.model.ScmVendorItemContrast;
import com.armitage.server.iscm.report.inventory.service.ScmInvReportBiz;
import com.armitage.server.iscm.report.inventory.webservice.ScmInvReportService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.armitage.server.iscm.report.inventory.model.ScmInvInWarehsItemSum;
import org.springframework.stereotype.Controller;

@Controller
@Path("/scmInvReportService")
public class ScmInvReportServiceImpl extends BaseServiceImpl<ScmInvReportBiz, ScmInvStockWSBean> implements ScmInvReportService {
	private static Log log = LogFactory.getLog(ScmInvReportServiceImpl.class);

	@Override
	public Object selectRealtimeStock(HttpServletRequest httpServletRequest) {
		long ss = new Date().getTime();
		List<RealtimeStock> list = new ArrayList<>();
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		try {
			list = biz.selectRealtimeStock(httpServletRequest);
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info("selectRealtimeStock取数用时：" + (new Date().getTime() - ss) + "毫秒");
		return gson.toJson(list);
	}

	@Override
	public Object selectGlobalInventory(HttpServletRequest httpServletRequest) {
		long ss = new Date().getTime();
		List<ScmInvGlobalStock> list = new ArrayList<>();
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		try {
			list = biz.selectGlobalInventory(httpServletRequest);
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info("selectGlobalInventory取数用时：" + (new Date().getTime() - ss) + "毫秒");
		return gson.toJson(list);
	}

	@Override
	public Object selectImmediateInvSum(HttpServletRequest httpServletRequest) {
		long ss = new Date().getTime();
		List<ScmInvRealtimeStockSum> list = new ArrayList<>();
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();
		try {
			list = biz.selectImmediateInvSum(httpServletRequest);
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info("selectImmediateInvSum取数用时：" + (new Date().getTime() - ss) + "毫秒");
		return gson.toJson(list);
	}

	@Override
	public Object selectScmInOutSummary(HttpServletRequest httpServletRequest) {
		long ss = new Date().getTime();
		List<ScmInvItemInAndOutSum> list = new ArrayList<>();
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		try {
			list = biz.selectScmInOutSummary(httpServletRequest);
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info("selectScmInOutSummary取数用时：" + (new Date().getTime() - ss) + "毫秒");
		return gson.toJson(list);
	}

	@Override
	public Object selectScmInvItemWrSum(HttpServletRequest httpServletRequest) {
		long ss = new Date().getTime();
		List<ScmInvItemWrSum> list = new ArrayList<>();
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		try {
			list = biz.selectScmInvItemWrSum(httpServletRequest);
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info("selectScmInvItemWrSum取数用时：" + (new Date().getTime() - ss) + "毫秒");
		return gson.toJson(list);
	}

	@Override
	public Object selectScmInOutDetail(HttpServletRequest httpServletRequest) {
		long ss = new Date().getTime();
		List<ScmInvItemInAndOutDetail> list = new ArrayList<>();
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		try {
			list = biz.selectScmInOutDetail(httpServletRequest);
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info("selectScmInOutDetail取数用时：" + (new Date().getTime() - ss) + "毫秒");
		return gson.toJson(list);
	}

	@Override
	public Object selectScmInvItemSaleSum(HttpServletRequest httpServletRequest) {
		long ss = new Date().getTime();
		List<ScmInvItemSaleSum> list = new ArrayList<>();
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		try {
			list = biz.selectScmInvItemSaleSum(httpServletRequest);
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info("selectScmInvItemSaleSum取数用时：" + (new Date().getTime() - ss) + "毫秒");
		return gson.toJson(list);
	}

	@Override
	public String selectScmInvInWarehsItemClass(HttpServletRequest request, HttpServletResponse response) {
		List<HashMap> list = new ArrayList<>();
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		try {
			list = biz.selectScmInvInWarehsItemClass(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "successCallback("+gson.toJson(list)+")";
	}

    @Override
    public Object selectScmInvItemWrDetails(HttpServletRequest httpServletRequest) {
        long ss = new Date().getTime();
        List<ScmInvItemWrDetails> list = new ArrayList<>();
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        try {
            list = biz.selectScmInvItemWrDetails(httpServletRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("selectScmInvItemWrDetails取数用时：" + (new Date().getTime() - ss) + "毫秒");
        return gson.toJson(list);
    }

    @Override
    public Object selectScmInvConsignSumSup(HttpServletRequest httpServletRequest) {
        long ss = new Date().getTime();
        List<ScmInvItemWrSum> list = new ArrayList<>();
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        try {
            list = biz.selectScmInvConsignSumSup(httpServletRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("selectScmInvConsignSumSup取数用时：" + (new Date().getTime() - ss) + "毫秒");
        return gson.toJson(list);
    }

	@Override
	public Object selectScmInvStorageAgePrimnessAnalysis(
			HttpServletRequest httpServletRequest) {
		long ss = new Date().getTime();
        List<ScmInvStorageAgePrimnessAnalysis> list = new ArrayList<>();
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        try {
            list = biz.selectScmInvStorageAgePrimnessAnalysis(httpServletRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("selectScmInvStorageAgePrimnessAnalysis取数用时：" + (new Date().getTime() - ss) + "毫秒");
        return gson.toJson(list);
	}

	@Override
	public Object selectScmInvDepositorySumSup(
			HttpServletRequest httpServletRequest) {
		long ss = new Date().getTime();
        List<ScmInvDepositorySumSup> list = new ArrayList<>();
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        try {
            list = biz.selectScmInvDepositorySumSup(httpServletRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("selectScmInvDepositorySumSup取数用时：" + (new Date().getTime() - ss) + "毫秒");
        return gson.toJson(list);
	}

	@Override
	public Object selectNewScmInvInWarehsItemClass(HttpServletRequest request, HttpServletResponse response) {
		long ss = new Date().getTime();
		List<ScmInvInWarehsItemClass2> list = new ArrayList<>();
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		try {
			list = biz.selectNewScmInvInWarehsItemClass(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info("selectNewScmInvInWarehsItemClass取数用时：" + (new Date().getTime() - ss) + "毫秒");
        return gson.toJson(list);
	}

	@Override
	public Object selectScmInvItemWrSupplierSum(HttpServletRequest httpServletRequest) {
		long ss = new Date().getTime();
		List<ScmInvItemWrSum> list = new ArrayList<>();
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		try {
			list = biz.selectScmInvItemWrSupplierSum(httpServletRequest);
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info("selectScmInvItemWrSupplierSum取数用时：" + (new Date().getTime() - ss) + "毫秒");
		return gson.toJson(list);
	}

	@Override
	public Object selectScmInvStorageAgeAnalysis(
			HttpServletRequest httpServletRequest) {
		long ss = new Date().getTime();
		List<ScmInvStorageAgeAnalysis> list=new ArrayList<>();
        Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();
        try {
            list=biz.selectScmInvStorageAgeAnalysis(httpServletRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("selectScmInvItemWrSupplierSum取数用时：" + (new Date().getTime() - ss) + "毫秒");
        return gson.toJson(list);
	}

	@Override
	public Object selectScmInvInWareMonthAnalysis(HttpServletRequest httpServletRequest) {
		long ss = new Date().getTime();
		List<ScmInvInWareMonthAnalysis> list=new ArrayList<>();
        Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();
        try {
            list=biz.selectScmInvInWareMonthAnalysis(httpServletRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("selectScmInvInWareMonthAnalysis取数用时：" + (new Date().getTime() - ss) + "毫秒");
        return gson.toJson(list);
	}
	
	@Override
	public Object selectScmInvSaleMonthAnalysis(HttpServletRequest httpServletRequest) {
		long ss = new Date().getTime();
		List<ScmInvInWareMonthAnalysis> list=new ArrayList<>();
        Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();
        try {
            list=biz.selectScmInvSaleMonthAnalysis(httpServletRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("selectScmInvSaleMonthAnalysis取数用时：" + (new Date().getTime() - ss) + "毫秒");
        return gson.toJson(list);
	}
	
	@Override
	public Object selectScmInvInWarehsItemSum(HttpServletRequest httpServletRequest) {
		long ss = new Date().getTime();
		List<ScmInvInWarehsItemSum> list=new ArrayList<>();
        Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();
        try {
            list=biz.selectScmInvInWarehsItemSum(httpServletRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("selectScmInvItemWrSupplierSum取数用时：" + (new Date().getTime() - ss) + "毫秒");
        return gson.toJson(list);
	}

	@Override
	public Object selectScmInvSaleItemSum(HttpServletRequest httpServletRequest) {
		long ss = new Date().getTime();
		List<ScmInvInWarehsItemSum> list=new ArrayList<>();
        Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();
        try {
            list=biz.selectScmInvSaleItemSum(httpServletRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("selectScmInvItemWrSupplierSum取数用时：" + (new Date().getTime() - ss) + "毫秒");
        return gson.toJson(list);
	}

	
	
	@Override
	public Object selectScmInvSaleBusiness(HttpServletRequest httpServletRequest) {

		long ss = new Date().getTime();
		List<ScmInvSaleBusiness> list=new ArrayList<>();
        Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();
        try {
            list=biz.selectScmInvSaleBusiness(httpServletRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("selectScmInvSaleBusiness取数用时：" + (new Date().getTime() - ss) + "毫秒");
        return gson.toJson(list);
	
	}
	
	@Override
	public Object selectScmInvPurSalePrice(HttpServletRequest httpServletRequest) {

		long ss = new Date().getTime();
		List<ScmInvPurSalePrice> list=new ArrayList<>();
        Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();
        try {
            list=biz.selectScmInvPurSalePrice(httpServletRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("selectScmInvPurSalePrice取数用时：" + (new Date().getTime() - ss) + "毫秒");
        return gson.toJson(list);
	
	}

	@Override
	public Object selectScmVendorItemContrast(HttpServletRequest httpServletRequest) {
	
		long ss = new Date().getTime();
		List<ScmVendorItemContrast> list=new ArrayList<>();
        Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();
        try {
            list=biz.selectScmVendorItemContrast(httpServletRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("selectScmInvPurSalePrice取数用时：" + (new Date().getTime() - ss) + "毫秒");
        return gson.toJson(list);

	}

	@Override
	public Object selectScmPurVendorInfo(HttpServletRequest httpServletRequest) {
	
		long ss = new Date().getTime();
		List<ScmPurVendorInfo> list=new ArrayList<>();
        Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();
        try {
            list=biz.selectScmPurVendorInfo(httpServletRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("ScmPurVendorInfo取数用时：" + (new Date().getTime() - ss) + "毫秒");
        return gson.toJson(list);
		
	}

}
