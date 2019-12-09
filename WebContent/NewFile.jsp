<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body align="center">
<input type="text" id="name" value="" name="name"/>
<input type="button"value="搜索" id="btn1" onclick="sou()"/>
	<table border="1" align="center" id="tb1">
		<tr>
			<td>学号</td>
			<td>姓名</td>
			<td>性别</td>
			<td>操作</td>
		
		</tr>
		<c:forEach items="${list}" var="li">
			<tr>
				<td>${li.id}</td>
				<td>${li.name}</td>
				<td>${li.sex}</td>
				<td><a href="#" onclick="del('${li.id}')">删除</a>
					<a href="${pageContext.request.contextPath}/add.jsp">添加</a>
					<a href="${pageContext.request.contextPath}/cha.do?id=${li.id}">编辑</a>
					<a href="${pageContext.request.contextPath}/biao.do">导出Excel表格</a>
				</td>
			</tr>

		</c:forEach>
	</table>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
/* eq相等 ne、neq不相等，
gt大于， lt小于
gt大于， lt小于
gte、ge大于等于  
lte、le 小于等于  
not非   mod求模  
is [not] div by是否能被某数整除  
is [not] even是否为偶数  
is [not] even by $b即($a / $b) % 2 == 0  
is [not] odd是否为奇  
is not odd by $b即($a / $b) % 2 != 0 */
//模糊查询
function sou() {
	$("#tb1 tr:gt(0)").remove();//移除
	var shu=$("#name").val();
	alert(shu)
	$.ajax({
		type:"post",//请求方式
		url:"${pageContext.request.contextPath}/sou.do",//请求的URL地址
		async:true,//请求是否为异步，默认位异步，这也是Ajax重要特性
		data:{name:shu},//参数值
		dataType:"json",
		success : function(data) {//与服务器交互成功以后回调函数			
	 		$.each(data, function(i, n) {
				var $tr = "<tr><td>" + n.id + "</td><td>"				      
				       + n.name + "</td><td>"
						+ n.sex+ "</td><tr>";
				$("#tb1").append($tr);
	/* 			append() - 在被选元素的结尾插入内容
				prepend() - 在被选元素的开头插入内容
				after() - 在被选元素之后插入内容
				before() - 在被选元素之前插入内容 */
			});  
		},
 		error : function() {	
			alert("搜索失败");
			
		}, 

		
	});
	
}
//删除
function del(id) {
	var stu=confirm("确认删除选中项吗？");//在js里写confirm,在页面中弹出提示信息
	if(!stu){//如果点击的是取消
		return false;//返回页面
	}else{//如果点击的是确定，就执行下面的操作
		$.ajax({
			type:"post",
			url:"${pageContext.request.contextPath}/shan.do",
			async:true,
			data:{"id":id},
			dataType:"json",
			success:function(str){
			  if(str==true){
					setTimeout("document.location='${pageContext.request.contextPath }/test.do'",500);
					//  setTimeout(function(){ 做一些事情;}, 500);在500秒之后执行方法
					alert("删除成功");
			  }				
			},
			error : function() {
				setTimeout("document.location='${ctx}/news/findAllNews.shtml'",500);
				alert("删除失败");			
			}, 		
		});
	}
}



</script>
</body>
</html>