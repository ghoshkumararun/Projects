<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<body class="login">
	<script>
		alert("HTTP : 404 ไม่พบ page ที่เรียก โปรดตรวจสอบ url อีกครั้ง");
		window.location = '${pageContext.request.contextPath}';
	</script>
</body>
</html>