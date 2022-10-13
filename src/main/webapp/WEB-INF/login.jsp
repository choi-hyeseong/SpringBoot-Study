<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<title>로그인</title>
<head></head>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<body>
<h1>Login</h1>
<table>
    <form name="f" action="/login" method="post"> <!--로그인 함수와 동일한 효과, submit 설정된 버튼 클릭시 post요청 발생-->
        <tr>
            <td>User:</td>
            <td><input type='text' name='username' id="username" value=''></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input type='password' name='password' id="password"/></td>
        </tr>
        <tr>
            <td><input name="submit" type="submit" value="submit"/></td> <!--제출용 버튼-->
        </tr>
    </form>
    <tr>
        <td><button name="register" type="button" value="register" onclick="register()">register</button></td>
    </tr>
</table>
<table>
<tr>
    <%String error = request.getAttribute("error") == null ? "" : request.getAttribute("error").toString();%>
    <td><%="<div style='color:red;'>" + error + "</div>"%></td>
</tr>
</table>
</body>
<script>
    function register() {
        window.location.href = "/register"
    }
    /*
    function login() {
        //찾았다ㅠㅜㅜㅜㅜㅜㅜㅜㅜ
        $.ajax({
            url: "/login",
            type: "POST",
            dataType: "json", //로그인할때는 application/json 쓰지말고, datatype으로 보냄
            data: {
                username: $("#username").val(),
                password: $("#password").val()
            },
            success: function (response) {
                alert("로그인에 성공하였습니다.")
            },

        })
    }*/
</script>
</html>