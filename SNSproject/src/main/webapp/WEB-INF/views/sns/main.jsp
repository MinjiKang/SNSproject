<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Main Page </title>
</head>
	<body>
		<form action="main">
		    <h2>회원 전용 페이지</h2>
		    ${member_id}님으로 로그인 하셨습니다.<br>
		    ${member_name}님 환영합니다<br>
	  
		    <input type="button" value="로그아웃" onclick="location.href='logout.do'">  
		    <input type="button" value="모든회원보기" onclick="location.href='memberList.do'">
		    <input type="button" value="회원수정페이지" onclick="location.href='memberUpdateForm.do'"> 
	    </form> 
	</body>
</html>
