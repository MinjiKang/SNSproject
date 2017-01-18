<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>회원가입 페이지</title>
        <meta charset="utf-8" >
        <script type="text/javascript">
        function clearText(field){
            if(field.defaultValue==field.value)
             field.value="";
   		}
 		</script>
</head>
<body>
		<form name="form1" method="post" action="Intro">

		<br>회원가입 페이지<br>
		<hr/>
		</form>        

        <form name="form2" action='insert' method='post'>
           <input type = "text" id = "name" value = "NAME" name="member_name" onfocus="clearText(this)"><br>
           <input type = "text" id = "id" value = "EMAIL OR PHONE NUMBER" name="member_id" onfocus="clearText(this)"><br>
           <input type = "password" id = "password" value = "PASSWORD" name="member_password" onfocus="clearText(this)"><br>
           <select name="member_birth" >
	           <option value="">년별 </option>
	         <c:forEach var="member_birth" begin="1980" end="2016" step="1">
	         <option value=${member_birth}>${member_birth}</option>
	           </c:forEach>
	            </select>  
	           <select name="member_birth">
	           <option value="">월별 </option>
	         <c:forEach var="member_birth" begin="1" end="12" step="1">
	         <option value=${member_birth}>${member_birth}</option>
	           </c:forEach>
	             </select>
	         <select name="member_birth">
	           <option value="">일별 </option>
	         <c:forEach var="member_birth" begin="1" end="31" step="1">
	         <option value=${member_birth}>${member_birth}</option>
	           </c:forEach>
             </select>
             <br>GENDER : <input type="radio" id= "1" name="member_sex" value="MALE">MALE
                <input type="radio" id = "1" name="member_sex" value="FEMALE">FEMALE<br>
         
            <input type="submit" value="가입" />
        </form>
</body>
</html>

