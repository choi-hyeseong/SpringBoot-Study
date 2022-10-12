<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<title>로그인</title>
<head></head>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<body>
<h1>Login</h1>
    <table>
        <tr>
            <td>User:</td>
            <td><input type='text' name='username' id="username" value=''></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input type='password' name='password' id = "password" /></td>
        </tr>
        <tr>
            <td><input type="hidden" id="token" name="${_csrf.headerName}" value="${_csrf.token}"></td>
        </tr>
        <tr>
            <td><input name="submit" type="submit" value="submit" onclick="login()"/></td>
            <td><input name="register" type="submit" value="register" onclick="register()"/></td>
        </tr>
    </table>
</body>
<script>
    function register() {
        window.location.href = "/register"
    }
    function login() {
        //찾았다ㅠㅜㅜㅜㅜㅜㅜㅜㅜ
        $.ajax({
                url:"/login",
                type :  "POST",
                dataType: "json", //로그인할때는 application/json 쓰지말고, datatype으로 보냄
                data: {
                    username: $("#username").val(),
                    password: $("#password").val()
                },
                success : function(response){
                    alert("로그인에 성공하였습니다.")
                },

            })
    }
</script>
</html>