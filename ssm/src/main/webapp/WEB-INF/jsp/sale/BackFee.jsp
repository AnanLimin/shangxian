<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">

<title>ETC-客户管理</title>
<!-- Bootstrap core CSS -->
<%-- <link href=" <%=basePath%>css/add_stu.css" rel="stylesheet" type="text/css"> --%>
<link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet">
<!-- page CSS -->
<link href="<%=basePath%>css/page.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="<%=basePath%>css/dashboard.css" rel="stylesheet">
<link href="<%=basePath%>css/bootstrap-datetimepicker.min.css" rel="stylesheet">


<!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
<!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
<script src="<%=basePath%>js/ie-emulation-modes-warning.js"></script>

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
  <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
  <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->
<script type="text/javascript" src="<%=basePath%>js/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/moment-with-locales.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/bootstrap-datetimepicker.min.js"></script>
<script src="<%=basePath%>myjs/BackMoney.js"></script>
<script src="<%=basePath%>js/page.js"></script>
</head>

<body>
	<jsp:include page="../public/navheader.jsp"></jsp:include>
	<input value="${pages }" id="pagecount" type="hidden">
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-3 col-md-2 sidebar">
				<ul class="nav nav-sidebar">
					<li><a href="<%=basePath%>user/adminindex.do">首页<span class="sr-only">(current)</span></a></li>
					<%-- <li class="active"><a href="<%=basePath%>signed/signedinfo.do">签单客户</a></li>
					<li><a href="<%=basePath%>signed/firstincomepay.do">收入支出</a></li> --%>
					<li class="active"><a href="<%=basePath %>signed/signedinfo.do">销售管理</a></li>
					<li><a href="<%=basePath %>signed/firstincomepay.do">财务管理</a></li>
					<li><a href="<%=basePath %>signed/ourmanagment.do">我的工作</a></li>
					
					<%-- <li><a href="<%=basePath %>signed/signedinfo.do">培训管理</a></li>
					<li><a href="<%=basePath %>signed/signedinfo.do">就业管理</a></li>
					<li><a href="<%=basePath %>signed/signedinfo.do">考勤管理</a></li>
					<li><a href="<%=basePath %>signed/signedinfo.do">基本信息</a></li> --%>
					
				</ul>
				<div id="tree"></div>
			</div>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
					<div class="container">
						<div class="row">
							<div>
								<a href="<%=basePath %>signed/signedinfo.do">我的订单</a> | 	
								<a href="<%=basePath %>signed/Back.do" style="text-decoration:underline;">我的申请</a> |
								<a href="<%=basePath %>signed/allsigninfo.do">全部订单</a> |
							 </div>
						</div>
					</div>
					<div class="container" style="height:20px;"></div>
						<table class="table table-striped table-hover table-responsive table-bordered" id="customerinfotable">
							<thead>
								<tr>
									<!-- <td colspan="14">
										<div class="btn-group">
											<button id="btn_add" type="button" class="btn btn-xs btn-danger" data-toggle="modal" data-target="#info"  >
												<span class="glyphicon glyphicon-arrow-up" aria-hidden="true" >退款/返款详情</span>
											</button>
										</div>
									</td> -->
								</tr>
								<tr class="active">
							
								  <th>申请编号</th>
								  <th>申请时间</th>
								  <th>经手人</th>
								  <th>申请金额</th>
								  <th>状态</th>
								  <th>类型</th>
								  <th>备注(原因)</th>
								</tr>
							  </thead>
							  <tbody>						 
							 	<c:forEach items="${Back}" var="signed">
							 	<tr>
								 
								  <td>${signed.dbid}</td>						 
								  <td>${signed.dbtime}</td>
								  <td>${signed.dbemp}</td>
								  <td>${signed.dbamount}</td>
								  <td>${signed.condition}</td>
								<c:choose>
									<c:when test="${signed.dbtype==2}">
										<td>退款</td>
									</c:when>
									<c:when test="${signed.dbtype==1}">
										<td>返款</td>
									</c:when>								
								</c:choose>
								  <td>${signed.dbremark}</td>			
								</tr>
							 	</c:forEach>	
							</tbody>
						</table>
						<!-- 分页按钮  -->
						<div class="container">
							<div class="row">
								<div id="myback" class="tcdPageCode sign col-xs-12 col-sm-6 col-md-8 col-lg-6 col-sm-offset-3 col-md-offset-2 col-lg-offset-3"> </div>
							</div>
						</div>
		</div>
	</div>
</div>

<%@ include file="../public/footer.jsp"%>
	<!-- Bootstrap core JavaScript  ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="<%=basePath%>js/bootstrap.js"></script>
	<!-- Just to make our placeholder images work. Don't actually copy the next line! -->
	<!-- <script src="Dashboard_files/holder.htm"></script> -->
	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script src="<%=basePath%>js/ie10-viewport-bug-workaround.js"></script>
	
</body>
</html>
						