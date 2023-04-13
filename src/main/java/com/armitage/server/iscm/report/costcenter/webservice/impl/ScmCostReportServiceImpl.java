package com.armitage.server.iscm.report.costcenter.webservice.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Path;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.armitage.server.common.base.model.BaseWSBean;
import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.iscm.inventorymanage.inventorydata.model.ScmCostFinDeptConsume;
import com.armitage.server.iscm.report.costcenter.model.ScmCostDirectTransfer;
import com.armitage.server.iscm.report.costcenter.model.ScmCostItemInAndOutDetail;
import com.armitage.server.iscm.report.costcenter.model.ScmCostItemInAndOutSum;
import com.armitage.server.iscm.report.costcenter.model.ScmCostRealtimeStockSum;
import com.armitage.server.iscm.report.costcenter.model.ScmCostTransferOccurSum;
import com.armitage.server.iscm.report.costcenter.model.ScmCountingTaskSum;
import com.armitage.server.iscm.report.costcenter.model.ScmDeptConsume;
import com.armitage.server.iscm.report.costcenter.model.ScmNewCostTransferOccurDetail;
import com.armitage.server.iscm.report.costcenter.model.ScmNewCostTransferOccurSum;
import com.armitage.server.iscm.report.costcenter.service.ScmCostReportBiz;
import com.armitage.server.iscm.report.costcenter.webservice.ScmCostReportService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Controller;

@Controller
@Path("/scmCostReportService")
public class ScmCostReportServiceImpl extends BaseServiceImpl<ScmCostReportBiz, BaseWSBean> implements ScmCostReportService {
	private static Log log = LogFactory.getLog(ScmCostReportServiceImpl.class);

	@Override
	public Object selectDirectTransDetails(HttpServletRequest request) {
		long ss = new Date().getTime();
		List<ScmCostDirectTransfer> list=new ArrayList<>();
		Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		try {
			list=biz.selectDirectTransDetails(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info("selectDirectTransDetails取数用时：" + (new Date().getTime() - ss) + "毫秒");
		return gson.toJson(list);
	}
    
		@Override
		public Object selectCostCenterInventory(HttpServletRequest request) {
			long ss = new Date().getTime();
			List<ScmCountingTaskSum> list=new ArrayList<>();
			Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();
			try {
				list=biz.selectCostCenterInventory(request);
			} catch (Exception e) {
				e.printStackTrace();
			}
			log.info("selectCostCenterInventory取数用时：" + (new Date().getTime() - ss) + "毫秒");
			return gson.toJson(list);
		}
		
		@Override
		public Object selectSummaryOfMaterials(HttpServletRequest request) {
			long ss = new Date().getTime();
			List<ScmCostItemInAndOutSum> list=new ArrayList<>();
			Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();
			try {
				list=biz.selectSummaryOfMaterials(request);
			} catch (Exception e) {
				e.printStackTrace();
			}
			log.info("selectSummaryOfMaterials取数用时：" + (new Date().getTime() - ss) + "毫秒");
			return gson.toJson(list);
		}

		@Override
		public Object selectListOfMaterials(HttpServletRequest request) {
			long ss = new Date().getTime();
			List<ScmCostItemInAndOutDetail> list=new ArrayList<>();
			Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			try {
				list=biz.selectListOfMaterials(request);
			} catch (Exception e) {
				e.printStackTrace();
			}
			log.info("selectListOfMaterials取数用时：" + (new Date().getTime() - ss) + "毫秒");
			return gson.toJson(list);
		}

        @Override
        public String selectScmCostTransferOccurSum(HttpServletRequest request, HttpServletResponse response) {
            List<ScmCostTransferOccurSum> list=new ArrayList<>();
            Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();
            try {
                list=biz.selectScmCostTransferOccurSum(request);
            } catch (Exception e) {
                e.printStackTrace();
            }
//            return gson.toJson(list);
            return "successCallback("+gson.toJson(list)+")";
        }

		@Override
		public Object selectDeptConsume(HttpServletRequest request) {
			long ss = new Date().getTime();
			List<ScmDeptConsume> list=new ArrayList<>();
			Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();
			try {
				list=biz.selectDeptConsume(request);
			} catch (Exception e) {
				e.printStackTrace();
			}
			log.info("selectDeptConsume取数用时：" + (new Date().getTime() - ss) + "毫秒");
			return gson.toJson(list);
		}	

		@Override
        public Object selectNewScmCostTransferOccurSum(HttpServletRequest request, HttpServletResponse response) {
            List<ScmNewCostTransferOccurSum> list=new ArrayList<>();
            Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();
            try {
                list=biz.selectNewScmCostTransferOccurSum(request);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return gson.toJson(list);
        }
		
		@Override
        public Object selectScmCostTransferOccurDetail(HttpServletRequest request, HttpServletResponse response) {
            List<ScmNewCostTransferOccurDetail> list=new ArrayList<>();
            Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();
            try {
                list=biz.selectScmCostTransferOccurDetail(request);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return gson.toJson(list);
        }
		

		@Override
		public Object selectImmediateCostSum(HttpServletRequest httpServletRequest) {
			long ss = new Date().getTime();
			List<ScmCostRealtimeStockSum> list=new ArrayList<>();
			Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();
			try {
				list=biz.selectImmediateCostSum(httpServletRequest);
			} catch (Exception e) {
				e.printStackTrace();
			}
			log.info("selectImmediateCostSum取数用时：" + (new Date().getTime() - ss) + "毫秒");
			return gson.toJson(list);
		}

		@Override
		public Object selectmovebillDetails(HttpServletRequest httpServletRequest) {
			List list=new ArrayList<>();
            Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();
            try {
                list=biz.selectmovebillDetails(httpServletRequest);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return gson.toJson(list);
		}

		@Override
		public Object selectSummaryOfMaterialsForInOfOut(HttpServletRequest request) {
			long ss = new Date().getTime();
			List<ScmCostItemInAndOutSum> list=new ArrayList<>();
			Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();
			try {
				list=biz.selectSummaryOfMaterialsForInOfOut(request);
			} catch (Exception e) {
				e.printStackTrace();
			}
			log.info("selectSummaryOfMaterialsForInOfOut取数用时：" + (new Date().getTime() - ss) + "毫秒");
			return gson.toJson(list);
		}

		@Override
		public Object selectDeptSummaryConsume(HttpServletRequest request) {
			long ss = new Date().getTime();
			List<ScmDeptConsume> list=new ArrayList<>();
			Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();
			try {
				list=biz.selectDeptSummaryConsume(request);
			} catch (Exception e) {
				e.printStackTrace();
			}
			log.info("selectDeptSummaryConsume取数用时：" + (new Date().getTime() - ss) + "毫秒");
			return gson.toJson(list);
		}

		@Override
		public Object selectDeptConsumeOfCostUse(HttpServletRequest httpServletRequest) {
			long ss = new Date().getTime();
			List<ScmCostFinDeptConsume> list=new ArrayList<>();
			Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();
			try {
				list=biz.selectDeptConsumeOfCostUse(httpServletRequest);
			} catch (Exception e) {
				e.printStackTrace();
			}
			log.info("selectDeptConsumeOfCostUse取数用时：" + (new Date().getTime() - ss) + "毫秒");
			return gson.toJson(list);
		}

		@Override
		public Object selectFinSummaryOfMaterials(HttpServletRequest request) {
			long ss = new Date().getTime();
			List<ScmCostItemInAndOutSum> list=new ArrayList<>();
			Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();
			try {
				list=biz.selectFinSummaryOfMaterials(request);
			} catch (Exception e) {
				e.printStackTrace();
			}
			log.info("selectFinSummaryOfMaterials取数用时：" + (new Date().getTime() - ss) + "毫秒");
			return gson.toJson(list);
		}

}
