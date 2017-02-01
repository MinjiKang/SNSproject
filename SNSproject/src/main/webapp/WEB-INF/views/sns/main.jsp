<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> <!--jstl 날짜 차이 계산하기 -->

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Main Page </title>
<script>
	function btnClick(){
		document.form2.action = 'findpeople';
		document.form2.submit();
	}
	
	function btnClick2(){
		document.form2.action = 'myfriend';
		document.form2.submit();
	}
	
	function btnClick3(){
		document.form1.action = 'request';
		document.form1.submit();
	}
	
	function btnClick4(){
		document.form1.action = 'friendsList';
		document.form1.submit();
	}
	
	function confirm(){
		document.boardForm.action = 'writeBoard';
		document.boardForm.submit();
	}
	
	function RemoveBoardContent(num){
		alert("삭제합니다.");
		document.selectBoardContents.board_no.value= num;
		document.selectBoardContents.action = 'deleteBoardContent';
		document.selectBoardContents.submit();
	}
	
</script>
</head>
	<body>
		<form name='form1' action="main">
		<input type="hidden" value="${sessionScope.userLoginInfo.member_no}" name="user1">
		<input type="hidden" value="${sessionScope.userLoginInfo.member_no}" name="user2">
		    <h2>회원 전용 페이지</h2>
		    ${sessionScope.userLoginInfo.member_name}님으로 로그인 하셨습니다.<br>
		    ${sessionScope.userLoginInfo.member_id}님 환영합니다<br>
	  
		    <input type="button" value="로그아웃" onclick="location.href='logout'"> <!-- controller @RequestMapping -->
		    <input type="button" value="친구조회" onclick="btnClick2()">
 	        <input type="button" value="친구신청현황" onclick="btnClick3()"> 
		    <input type="button" value="친구수락요청" onclick="btnClick4()">
		    <input type="button" value="회원수정페이지" onclick="location.href='memberUpdateForm'"> 
		    <input type="button" value="회원탈퇴" onclick="location.href='deleteForm'">
	    </form><br>
	    
	    <!-- 게시글 작성 -->
	    <form name = 'boardForm'>
			<textarea rows="5" cols="80" name="board_contents"></textarea>
			<input type="button" value="작성" onclick="confirm()" >
	    </form>
	    
	    <form name='selectBoardContents'>
	    <input type="hidden" name="board_no">			
		
	    <table border="1">
			<c:forEach var="Board" items="${listcontents}">
			<fmt:parseDate var="dateString" value="${Board.board_date}" pattern="yyyyMMddHHmmss"/> <!-- 날짜 형식 파싱 -->
			<tr>
				<td>작성자</td>
				<td><input type=text value="${Board.board_writer}" size=10 maxlength=8></td>
			</tr>
			<tr>
     			<td>작성일</td>
     			<td><input type=text value="<fmt:formatDate value="${dateString}" pattern="yyyy/MM/dd"/>" size=30></td>
    		</tr>
    		<tr>
     			<td>작성시간</td>
     			<td><input type=text value="${Board.diff_time}" size=30></td>
    		</tr>
			<tr>
     			<td>내 용</td>
     			<td><textarea name="content" rows ="5" cols="80">${Board.board_contents}</textarea></td>
    		</tr>
    		
				<!-- 로그인한 사용자와 글을 쓴 사용자가 같을때만 삭제버튼이 보여진다.
				    eq 는 ==와 동일한 의미
				    c:set 을 써서 el도 변수로 만들 수 있다. -->
				<c:set var="loginId" value="${sessionScope.userLoginInfo.member_id}"/>
				<c:if test="${loginId eq Board.board_writer}">
					<td align ="center"><input type="submit" value="글 삭제" onclick="RemoveBoardContent(${Board.board_no})"></td>
				</c:if>
			<!-- </tr> -->
			</c:forEach>
			
		</table>
		
	    </form>
	    
	    <!-- 친구검색 -->
		<form name='form2' action="findpeople">
		   <input type="text" value="찾고자하는 이름" name="member_name" onfocus="clearText(this)">
		   <input type="button" value="찾기" onclick="btnClick()" >
	    </form>  
	</body>
</html>
