<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="resources/js/jquery-1.9.1.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.5.1/jquery.min.js"></script>
<title>�α��� ������</title>
        <meta charset="utf-8" >
        <script type="text/javascript">
        function clearText(field){
            if(field.defaultValue==field.value)
             field.value="";
   		}
        
        function loginsuccess(){
    		alert("�α����߱�ٷ��ֽʽÿ�.");
//     		frm.submit();
    	}
        
        function loginCheck(){
        	var param = "id" + "=" + $("#member_id").val() + "&" +"pw" + "="+ $("#member_password").val();
    		$.ajax({
    			url : "/login.do",
    			type : "GET",
    			data : param,
    			cache : false,
    			async : false,
    			dataType : "text",
    	
    			success : function(response) {								
    				if(response=='1')
    				{
    					loginsuccess();
    				}
    				else
    				{
    					alert("���̵� �Ǵ� ����� Ʋ�Ƚ��ϴ�. �ٽ� �Է��ϼ���.")
    					return false;
    				}	
    				
    				alert(check);
    			},
    			error : function(request, status, error) {
    				if (request.status != '0') {
    					alert("���̵� �Ǵ� ��й�ȣ��  �Է��ϼ���.")
    					//alert("code : " + request.status + "\r\nmessage : "
    						//	+ request.reponseText + "\r\nerror : " + error);
    				}
    			}
    	
    		});
    	}
 		</script>
</head>
<body>
		<form name="form1" method="post" action="Intro">
		  �α���</br>
		        <hr/>
		        <input type = "text" id = "id" value = "ID" name="member_id" onfocus="clearText(this)">
		        <input type = "text" id = "password" value = "PASSWORD" name="member_password" onfocus="clearText(this)">
		        <input type="submit" value="�α���" onclick='loginCheck()'/><br><br>
		        ȸ������ ������</br>
		        <hr/>
		</form>        

        <form name="form2" action='insert' method='post'>
           <input type = "text" id = "name" value = "NAME" name="member_name" onfocus="clearText(this)"><br>
           <input type = "text" id = "id" value = "EMAIL OR PHONE NUMBER" name="member_id" onfocus="clearText(this)"><br>
           <input type = "password" id = "password" value = "PASSWORD" name="member_password" onfocus="clearText(this)"><br>
           <input type = "text" id = "birth" value = "BIRTH(90.01.02)" name="member_birth" onfocus="clearText(this)"><br>
            GENDER : <input type="radio" name="chk_info" value="MALE">MALE
                <input type="radio" name="chk_info" value="FEMALE">FEMALE
            <input type="submit" value="����" />
        </form>
</body>
</html>

