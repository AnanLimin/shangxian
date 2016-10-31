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

<title>ETC-财务管理-收支记录</title>
<!-- Bootstrap core CSS -->
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
<!-- <script type="text/javascript" src="<%=basePath%>js/moment-with-locales.min.js"></script> -->
<!-- <script type="text/javascript" src="<%=basePath%>js/bootstrap-datetimepicker.min.js"></script> -->
<script type="text/javascript" src="<%=basePath%>myjs/iaer.js"></script>
</head>

<body>
	<jsp:include page="../public/navheader.jsp"></jsp:include>
	<input value="${pages }" id="pagecount" type="hidden">
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-3 col-md-2 sidebar">
				<ul class="nav nav-sidebar">
					<li><a href="<%=basePath%>user/adminindex.do">首页<span class="sr-only">(current)</span></a></li>
					<li><a href="<%=basePath %>signed/signedinfo.do">销售管理</a></li>
					<li class="active"><a href="<%=basePath %>signed/firstincomepay.do">财务管理</a></li>
					<li><a href="<%=basePath %>signed/ourmanagment.do">我的工作</a></li>
				</ul>
			</div>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				
				<div class="container">
						<div class="row">
							<div>
								<a href="<%=basePath %>signed/firstincomepay.do">收款</a> | 
								<a href="<%=basePath %>signed/backmoney.do">返款</a> | 
								<a href="<%=basePath %>signed/refund.do">退款</a> | 
								<a href="<%=basePath %>signed/iaer.do" style="text-decoration:underline;">收支记录</a> | 
							</div>
						</div> 
				</div>
				<div class="container" style="height:20px;"></div>
					<table class="table table-striped table-hover table-responsive table-bordered" id="iaerinfotable">
						<thead>
							<tr>
								<td colspan="14">
									<!-- <form action="">
										<select name="">
											<option>类型</option>
											<option value="收入">收入</option>
											<option value="支出">支出</option>
										</select>
										<select name="">
											<option>经手人</option>
											<option value="收入">张三</option>
											<option value="支出">袁雯</option>
										</select>
									</form> -->
								</td>
							</tr>
							<tr class="warning">
							  <th>日期</th>
							  <th>说明</th>
							  <th>收入</th>
							  <th>支出  </th>
							  <th>经手人</th>
							  <th>更多</th>
							</tr>
						  </thead>
						  <tbody>
						 	<c:forEach items="${iaers }" var="iaer">
						 	<c:if test="${iaer.type=='收入'}">
						 	<tr class="success">
							  <td>${iaer.time}</td>
							  <td>${iaer.remark}</td>
							  <td>${iaer.amount}</td>
							  <td></td>
							  <td>${iaer.handler}</td>
							  <td>${iaer.sid}</td>
							</tr>
						 	</c:if>
						 	<c:if test="${iaer.type=='支出'}">
						 	<tr class="danger">
							  <td>${iaer.time}</td>
							  <td>${iaer.remark}</td>
							  <td></td>
							  <td>${iaer.amount}</td>
							  <td>${iaer.handler}</td>
							  <td>${iaer.sid}</td>
							</tr>
						 	</c:if>
						 	</c:forEach>
						</tbody>
					</table>
					<div class="container">
						<div class="row">
							<div id="iaerpagegroup" class="tcdPageCode sign col-xs-12 col-sm-6 col-md-8 col-lg-6 col-sm-offset-3 col-md-offset-2 col-lg-offset-3"> </div>
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
	<%-- <script type="text/javascript" src="<%=basePath%>myjs/customer.js"></script> --%>
	<script src="<%=basePath%>js/page.js"></script>
	<script src="<%=basePath%>js/move-model.js"></script>

</body>
</html>
						