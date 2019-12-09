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
		<table border="1" align="center">
			<tr>
				<td>学号</td>
				<td>姓名</td>
				<td>性别</td>
				
			</tr>
			<c:forEach items="${list }" var="li">
			<tr>
			<td><input type="text" name="id" id="id" value="${li.id}"/></td>
				<td><input type="text" name="name" id="name"  value="${li.name }"></td>
				<td><input type="text" name="sex" id="sex" value="${li.sex }"></td>
			</tr>
			</c:forEach>
	
			<tr>
			<td><input type="button" value="修改" onclick="xiu()"></td>
			</tr>
		</table>
		<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
//修改
		function xiu() {
	    var id=$("#id").val();
	    var name=$("#name").val();
	    var sex=$("#sex").val();
	  $.ajax({
		  type:"post",
		  url:"${pageContext.request.contextPath}/xiu.do",
		  data:{"id":id,"name":name,"sex":sex},//参数值
		  dataType:"json",
		  success:function(str){
			  if(str=2){
				  alert("修改成功！");
					setTimeout("document.location='${pageContext.request.contextPath }/test.do'",500);
			  }else{
				  alert("修改失败！");
			  }
			  
		  }
		  
		  
		  
	  });
}
	</script>
</body>
</html>