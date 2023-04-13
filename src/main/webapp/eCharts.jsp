<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
	<title>ECharts</title>
	<script type="text/javascript" src="${pageContext.request.contextPath}/echarts/jquery.min.js"></script>
<%-- 	<script type="text/javascript" src="${pageContext.request.contextPath}/echarts/esl.js"></script> --%>
    <script type="text/javascript" src="${pageContext.request.contextPath}/echarts/echarts.js"></script>
    
	<style type="text/css">
		html,body {
			margin: 0; 
			padding : 0;
			width: 100%;
			height: 100%;
			overflow: hidden;
			padding: 0;
		}
		
		#chart {
			margin: 0;
			padding: 0;
			height: 100%;
			width: 100%;
			overflow: hidden;
		}
	</style>
</head>
<body>
    <div id="chart" />
    <script type="text/javascript" language="javascript">
	    var chart;  
	    
        require.config({
            paths: {
                echarts: '${pageContext.request.contextPath}/echarts'
            }
        });
        require(
            [
                'echarts',
                'echarts/chart/line',
                'echarts/chart/bar',
                'echarts/chart/pie',
                'echarts/chart/funnel'
            ], draw //异步加载的回调函数绘制图表
        );

        //创建ECharts图表方法  
        function draw(ec) {  
        	chart = ec.init(document.getElementById('chart'));  
           	window.onresize = chart.resize;
           	
           	refresh();   
        }
        
        function refresh() {
        	chart.clear();
            chart.showLoading({effect : 'whirling'});  
            
            <%
           	request.setAttribute("url", request.getParameter("url"));
           	request.setAttribute("userCode", request.getParameter("userCode"));
           	request.setAttribute("token", request.getParameter("token"));
           	request.setAttribute("content", request.getParameter("content"));
           	
           	System.out.println(">>>eCharts.jsp: url=" + request.getParameter("url"));
           	System.out.println(">>>eCharts.jsp: userCode=" + request.getParameter("userCode"));
           	System.out.println(">>>eCharts.jsp: content=" + request.getParameter("content"));
           	%>
            
            //通过Ajax获取数据  
            $.ajax({
            	type : "post",
                url : "${url}",
                dataType : "text",
                data : {userCode: "${userCode}",token:"${token}", content: "${content}"},
                success : function(result) {
            		if (result) {
            			if (result.indexOf('"errCode":') > 0 && result.indexOf('"errText":') > 0) {
             				errorHandler.handle(result);
            			} else {
            				var json = JSON.parse(result);
                            chart.setOption(json);
            			}
                    }  
                },  
                error: function(XMLHttpRequest, textStatus, errorThrown) {
                	chart.hideLoading();
                	errorHandler.handle(textStatus);
                },
                complete: function() {
                    chart.hideLoading();
                }
            });
        }  
    </script>  
</body> 
</html>
