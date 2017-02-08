<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> <!--jstl 날짜 차이 계산하기 -->

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="resources/css/screen.css" rel="stylesheet" type="text/css">
<title>Main Page </title>
<script type = "text/javascript" src = "resources/js/jquery-3.1.1.min.js"></script>
<script>
	function btnClick(){
		document.form2.action = 'findpeople';
		document.form2.submit();
	}
	
	function btnClick2(){
		document.form1.action = 'myfriend';
		document.form1.submit();
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
		document.deleteForm.board_no.value= num;
		document.deleteForm.action = 'deleteBoardContent';
		document.deleteForm.submit();
	}

	function resize(obj) {
		  obj.style.height = "1px";
		  obj.style.height = (12+obj.scrollHeight)+"px";
    }
	
	function clickLikeButton(num,like){
		document.likeForm.board_no.value= num;
		document.likeForm.like_status.value= like;
		document.likeForm.action = 'clickLikeButton';
	    document.likeForm.submit();
	}
	
	function likeList(num){
		document.likeListForm.board_no.value = num;
		document.likeListForm.action = 'likePeoplList';
		document.likeListForm.submit();
	}
</script>
</head>
	<body>
	
	<form name='deleteForm'>
		<input type="hidden" name="board_no">
    </form>	
    
    <form name='likeForm'>
		<input type="hidden" name="board_no">
 		<input type="hidden" name="like_status">
    </form>	
    
    <form name='likeListForm'>
    	<input type="hidden" name="board_no">
    </form>
	
	<div id="wrap">

	    <div id="header">
	    	<div id="image">
	    	<input type="image" src="resources/img/home.png" >
	    	</div>
	    	<div id ="find">
	    	<!-- 친구검색 -->
			<form name='form2' action="findpeople">
		    	
				<input type="hidden" value="${sessionScope.userLoginInfo.member_no}" name="member_no">
			   <input type="text" value="찾고자하는 이름" name="member_name" onfocus="clearText(this)">
			   <input type="button" value="찾기" onclick="btnClick()" >
		    </form>	
		    </div>
	    </div>

	    <div id="container">
	        <div id="content">
	        <!-- 게시글 작성 -->
			    <div id = "center_user_content">
				    <form name = 'boardForm'>
						<textarea class="write" name="board_contents" onkeydown="resize(this)" onkeyup="resize(this)" placeholder="무슨 일이 일어나고 있나요?"></textarea>
						<div id = "write_button">
						<input type="button" value="작성" onclick="confirm()">
						</div>
				    </form>
				</div>
				    
				    <form name='selectBoardContents' method="post">
			    	<input type="hidden" name="delno">
			    	<input type="hidden" name="like_status">
			    
				
						<c:forEach var="Board" items="${listcontents}">
							<div id="content_center">
								<div id="userid">
									♣ ${Board.BOARD_WRITER}
								</div>
								<div id="menu_button">
								
								<c:set var="loginId" value="${sessionScope.userLoginInfo.member_id}"/>
								<c:choose>
									<c:when test="${loginId eq Board.BOARD_WRITER}">
										<ul class="one"> 
										  <li><a href="#">menu</a>
										    <ul>
										       <li><a onclick="RemoveBoardContent(${Board.BOARD_NO})">글 삭제</a></li>
										       <li><a href="#">글 수정</a></li>
										       <li><a onclick="javascript:window.open('http://share.naver.com/web/shareView.nhn?url='
												+encodeURIComponent(document.URL)+'&title='+encodeURIComponent(document.title),
												 'naversharedialog', 'menubar=no,toolbar=no,resizable=yes,scrollbars=yes,height=500,width=500');return false;" 
												target="_blank" alt="Share on Naver">공유하기</a></li>
										    </ul>
										  </li>
										</ul>
									</c:when>
									 <c:otherwise>
										 <ul class="one"> 
											  <li><a href="#">menu</a>
											    <ul>
											       <li><a onclick="javascript:window.open('http://share.naver.com/web/shareView.nhn?url='
												+encodeURIComponent(document.URL)+'&title='+encodeURIComponent(document.title),
												 'naversharedialog', 'menubar=no,toolbar=no,resizable=yes,scrollbars=yes,height=500,width=500');return false;" 
												target="_blank" alt="Share on Naver">공유하기</a></li>
											       <li><a href="#">친구 신청 </a></li>
											    </ul>
											  </li>
											</ul>
									 </c:otherwise>
								</c:choose>
								</div>
								<div id = "autosize_div">
									<textarea class="autosize" readonly>${Board.BOARD_CONTENTS}</textarea>
								</div>
								<br><br><br><br><br><br>${Board.DIFF_TIME}
								<c:if test="${Board.LIKEBUTTON eq 1 }">
								<input type="button" value="좋아요취소" onclick="clickLikeButton(${Board.BOARD_NO},${Board.LIKEBUTTON})"><br>
								</c:if>
								<c:if test="${Board.LIKEBUTTON ne 1 }">
								<input type="button" value="좋아요" onclick="clickLikeButton(${Board.BOARD_NO},${Board.LIKEBUTTON})"><br>
								</c:if>
								<input type="text" value="${Board.LIKECOUNT}">
								<input type="button" value="좋아요list" onclick="likeList(${Board.BOARD_NO})">
							</div>
						</c:forEach>
			   		 </form>
	        </div>
	    </div>
	    
	    <div id="sidebar">
	    <form name='form1'>
		    <input type="hidden" value="${sessionScope.userLoginInfo.member_no}" name="user1">
			<h2>회원 전용 페이지</h2>
			${sessionScope.userLoginInfo.member_name}님으로 로그인 하셨습니다.<br>
		    ${sessionScope.userLoginInfo.member_id}님 환영합니다<br>
		    <input type="button" value="로그아웃" onclick="location.href='logout'"><br> <!-- controller @RequestMapping -->
		    <input type="button" value="친구조회" onclick="btnClick2()"><br>
 	        <input type="button" value="친구신청현황" onclick="btnClick3()"><br>
		    <input type="button" value="친구수락요청" onclick="btnClick4()"><br>
		    <input type="button" value="회원수정페이지" onclick="location.href='memberUpdateForm'"><br> 
		    <input type="button" value="회원탈퇴" onclick="location.href='deleteForm'"><br>
		</form>
		</div>
		
	    <div id="extra"> extra</div>
	    
	    
	   <!--<div id="footer"></div> -->
    
	</div>
		
	</body>
</html>
