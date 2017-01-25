<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Main Page </title>
</head>
	<body>
		<form action="main">
		    <h2>회원 전용 페이지</h2>
		    ${sessionScope.userLoginInfo.member_name}님으로 로그인 하셨습니다.<br>
		    ${sessionScope.userLoginInfo.member_id}님 환영합니다<br>
	  
		    <input type="button" value="로그아웃" onclick="location.href='logout'">  
		    <input type="button" value="모든회원보기" onclick="location.href='memberList'">
		    <input type="button" value="회원수정페이지" onclick="location.href='memberUpdateForm'"> 
		    <input type="button" value="회원탈퇴" onclick="location.href='deleteForm'">
	    </form> 
	</body>
</html>
