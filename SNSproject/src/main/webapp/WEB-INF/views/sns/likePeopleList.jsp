<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>좋아요 리스트</title>
</head>
<body>
	<table border="1">
	<tr>
	    
		<th width="100">이름</th>
		<th width="100">좋아요한 시간</th>
	</tr>
	<c:forEach var="like" items="${likePeopleList}">
	<tr>
		<!--HashMap을 썼으므로 대문자로 가져옴 -->
		<td align ="center">${like.MEMBER_NAME}</td>
		<td align ="center">${like.LIKETIME}</td> <!--alias name으로 가져옴 -->
		</tr>
	</c:forEach>
	</table>
</body>
</html>