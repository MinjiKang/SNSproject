<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<title>로그인 </title>
	<script type="text/javascript">

	   function Login()
	   {
	        var form = document.form1;
	
            //아이디에서 입력 필수 조건문
            if (form.member_id.value == "")
            {
                    alert("아이디를 입력하세요!");
                    form.member_id.focus();//포커스를 id박스로 이동.
                    return false;
            }
            
            //비밀번호에서 입력 필수 조건문
            if (form.member_password.value == "")
            {
                 alert("패스워드를  입력하세요!");
                 form.member_password.focus();//포커스를 Password박스로 이동.
                 return false;
            }
            
            form.submit();
	   }
	
    </script>
</head>
<body>

		<h1>로그인</h1>
		<form name="form1" method="post" action="loginProcess" onSubmit="return Login()">
			<table>
				<tr height="40px">
					<td>Email: <br></td>
					<td><input type="text" name="member_id"></td>
				</tr>
				<tr height="40px">
					<td>Password:<br></td>
					<td><input type="password" name="member_password"></td>
				</tr>
			</table>
			<table>
				<tr>
					<td align="center"><input type="submit" value="로그인"></td>
					<td align="center"><input type="reset" value="초기화"></td>
				</tr>
			</table>
			<br><a href="application">계정 만들기</a>
			<br><a href="pass">비밀번호찾기</a>
		</form>
</body>
</html>

<%-- <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> <!--jstl 날짜 차이 계산하기 -->

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="resources/css/loginpage.css" rel="stylesheet" type="text/css">
<link href="resources/css/style.css" rel="stylesheet" type="text/css">
<title>Login Page </title>
<script type = "text/javascript" src = "resources/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript">
        //텍스트박스 클릭시 보조문구(value)비우기
        function clearText(field){
            if(field.defaultValue==field.value)
             field.value="";
   		}
        
        //비밀번호 유효성 검사
        function CheckPassword(){
	        var pw1 = document.getElementById("password");
	    
	        if(!/^[a-zA-Z0-9]{8,20}$/.test(pw1.value))
	
	        { 
	        	document.getElementById('checkPwd').style.color = "red";
	        	document.getElementById('checkPwd').innerHTML = "비밀번호는 숫자와 영문자 조합으로 8~20자리를 사용해야 합니다.";
	        }	       
	        else{
	        	document.getElementById('checkPwd').style.color = "green";
	        	document.getElementById('checkPwd').innerHTML = "적절한 비밀번호 입니다.";
	        }

	        var chk_num = pw1.value.search(/[0-9]/g); 
	        var chk_eng = pw1.value.search(/[a-z]/ig);
	
	        if(chk_num < 0 || chk_eng < 0)
	        { 
	        	document.getElementById('checkPwd').style.color = "red";
	        	document.getElementById('checkPwd').innerHTML = "비밀번호는 숫자와 영문자를 혼용하여야 합니다.";
	            return false;
	        }
        }
        
        //이메일 유효성 검사
        function CheckEmail(){
        	var email = document.getElementById("id");
        	var aIndex = email.value.indexOf("@");
        	if(aIndex == 0) { //@가 처음에 위치한다.
        		document.getElementById('checkId').style.color = "red";
	        	document.getElementById('checkId').innerHTML = "메일 입력형식이 올바르지 않습니다.";
	        	email.focus();
	        	return false;
        	}
        	else{
        		document.getElementById('checkId').innerHTML = "";
        	}
        	
        	var dotIndex = email.value.indexOf('.');
        	var len = email.value.length;
        	
        	if(len == (dotIndex+1)) { // '.'이 마지막에 위치한다.
        		document.getElementById('checkId').style.color = "red";
	        	document.getElementById('checkId').innerHTML = "메일 입력형식이 올바르지 않습니다.";
	        	email.focus();
	        	return false;
        	}
        	else{
        		document.getElementById('checkId').innerHTML = "";
        	}
        	
        	if(aIndex == -1 || dotIndex == -1 || aIndex >= (dotIndex-1)) { // '@'나 '.'가 없거나 '@'가 '.'뒤에 위치하거나 붙어있다면
        		document.getElementById('checkId').style.color = "red";
	        	document.getElementById('checkId').innerHTML = "메일 입력형식이 올바르지 않습니다.";
	        	email.focus();
	        	return false;
        	}
        	document.getElementById('checkId').innerHTML = "";
        }
        
        //회원가입에 필요한 문항을 전부다 채워 넣었는지 check
        function Check_input_form(){
      
        	var name = document.form2.member_name.value;
        	var id = document.form2.member_id.value;
        	var password = document.form2.member_password.value;
        	
        	var member_birth = document.form2.member_birth_y.value;
        	if ( document.form2.member_birth_m.value.length == 1 ) 
        		member_birth = member_birth + "0" + document.form2.member_birth_m.value;
        	else  member_birth = member_birth + document.form2.member_birth_m.value; 
        	
        	if ( document.form2.member_birth_d.value.length == 1 )
        		member_birth = member_birth + "0" + document.form2.member_birth_d.value;
        	else  member_birth = member_birth + document.form2.member_birth_d.value; 

        	document.form2.member_birth.value = member_birth;
        	var member_sex = document.form2.member_sex.value;

        	if(name == null || id == null || password == null || member_birth == null || member_sex == null ||
        			name == "" || id == "" || password == "" || member_birth == ""   || member_sex == "" ) {
        		alert("공백을 채우세요");
        		return false; //submit 진행 차단
        	} else {
        		// 모든조건이 충족되면 true반환
        		document.form2.action = 'insert';
        		document.form2.submit();
        	} 

        }
        
        function CheckValue(){
        	if(document.form2.password.value != document.form2.password_re.value){
        		document.getElementById('checkValue').style.color = "red";
	        	document.getElementById('checkValue').innerHTML = "비밀번호가 일치하지 않습니다.";
        	}
        	else{
        		document.getElementById('checkValue').innerHTML ="";
        	}
        }

        function CheckID(){
        	if(document.form2.member_id.value == null || document.form2.member_id.value =="" || document.form2.member_id.value =="이메일"){
        		alert("아이디를 입력하세요");
        		form2.member_id.focus();
        		return false;
        	}
        	else{
        		document.form2.action = 'CheckID';
        		document.form2.submit();
        	}
        }
        
        window.onload = function(){
      	   var msg = '${msg2}';
      	   
      	       if ( msg != '')	{   
      		   alert('${msg2}');
      	       document.form2.member_id.value = '${joinmember.member_id}';
      	       }
        }

 
        $(function () {

            $('#id').data('holder', $('#id').attr('placeholder'));

            $('#id').focusin(function () {
                $(this).attr('placeholder', '');
            });
            $('#id').focusout(function () {
                $(this).attr('placeholder', $(this).data('holder'));
            });
            
        });
        </script>
</head>
	<body>
	
	<div id="wrap">

		    <div id="header">
		    </div>
		    
	    	<div id="id_form">
	    		<label> 이메일: <input type="text" size = 15 name="member_id"></label>
	    		<label> 비밀번호: </label><input type="password" size = 15 name="member_password">
	    		<input type="submit" value="로그인"><br>
	    		<a href="pass">계정을 잊으셨나요?</a>
	    	</div>

	    	<div id="application_form">
	    	<h1>가입하기</h1>
		        <form name="form2" method="post">
				<div class = "placeholder_box">
		  		   <input type = "text" id = "id" placeholder = "이메일" name="member_id" onfocus="clearText(this)" onkeyup="CheckEmail()" onkeydown = "check_Overlap_Id()">
		           <input type ="button" id = "checkid" value = "중복검사" onclick="CheckID()"><br> <!-- 아이디 중복검사 버튼 -->
		           <div id="checkId"></div> <!-- 이메일 유효성 체크 메세지 -->
		        </div>
		        <div class = "placeholder_box"> 
		           <input type = "text" id = "name" placeholder = "이름" name="member_name" onfocus="clearText(this)"><br>
		        </div>
		        <div class = "placeholder_box"> 
		           <input type = "password" id = "password" placeholder = "비밀번호" name="member_password" onfocus="clearText(this)" onkeyup="CheckPassword()" >
		           <div id="checkPwd"></div> <!-- 비밀번호 유효성 체크 메세지 -->
		        </div>
		        <div class = "placeholder_box">
		           <input type = "password" id = "password_re" placeholder = "비밀번호 재입력" onfocus="clearText(this)" onkeyup="CheckValue()">
		           <div id="checkValue"></div> <!-- 비밀번호 일치 여부 메세지  -->
		        </div>
		        <div class = "placeholder_box">
		 		   <select  name="password_q" >
			         <option value="">비밀번호 찾기 질문 </option>
					 <option value="아버지의 성함은?">아버지의 성함은?</option>
					 <option value="가장 좋아하는 색깔은?">가장 좋아하는 색깔은?</option>
					 <option value="가장 좋아하는 음식은?">가장 좋아하는 음식은?</option>
			       </select>
			     </div>
			     <div class = "placeholder_box"> 
			       <input type = "text" id = "password_a" name="password_a" placeholder = "비밀번호 찾기 답변" onfocus="clearText(this)"><br>   
			     </div>
			     <div class = "placeholder_box_birth">   
		           <select name="member_birth_y">
			         <option value="">연도 </option>
				         <c:forEach var="member_birth" begin="1980" end="2016" step="1">
				        	 <option value=${member_birth}>${member_birth}</option>
				         </c:forEach>
			       </select>  
			       <select name="member_birth_m">
			         <option value="">월 </option>
				         <c:forEach var="member_birth" begin="1" end="12" step="1">
				        	 <option value=${member_birth}>${member_birth}</option>
				         </c:forEach>
			       </select>
			       <select name="member_birth_d">
			         <option value="">일 </option>
				         <c:forEach var="member_birth" begin="1" end="31" step="1">
				    		 <option value=${member_birth}>${member_birth}</option>
			          	 </c:forEach>
		           </select>
		          </div>
		           <input type="hidden" name="member_birth" id="member_birth" value="">
		             <br><input type="radio" id= "1" name="member_sex" value="FEMALE">여성
		                 <input type="radio" id = "1" name="member_sex" value="MALE">남성<br><br>
		                 <!-- <input type="button" value="계정	 만들기" onClick="Check_input_form()"/> -->
		          		 <input type="image" src="resources/img/loginbutton.png" onClick="Check_input_form()" style="width: 170px; height: 70px;">
		        </form>
	    	</div>
    </div>
    </body>
</html> --%>
	    