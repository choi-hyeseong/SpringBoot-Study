<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<title>회원가입</title>
<head></head>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<body>
<h1>Login</h1>
    <table>
        <tr>
            <td>User:</td>
            <td><input type='text' name='username' id='username' value=''></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input type='password' name='password' id="password" /></td>
        </tr>
        <tr>
            <td>name:</td>
            <td><input type='text' name='name' id="name"/></td>
        </tr>
        <tr>
            <td>age:</td>
            <td><input type='text' name='age' id="age"/></td>
        </tr>
        <tr>
            <td>email:</td>
            <td><input type='text' name='email' id="email"/></td>
        </tr>
        <tr>
            <td><input name="register" type="submit" id="register" value="register" /></td>
        </tr>
    </table>
</body>
<script>

    $('#register').on("click", (e) => {
        let data = {name: $("#name").val(), age: $("#age").val(), username: $("#username").val(), password: $("#password").val(), email: $("#email").val()}
        $.ajax({
            url : "http://127.0.0.1:8080/register", //이게 왜..
            async: false,
            type: "post",
            data: JSON.stringify(data),
            contentType: "application/json",
            success : (res) => {
                alert("회원가입이 정상적으로 처리되었습니다! 로그인 페이지로 이동합니다.");
                window.location.href = "/login"
            },
            error : (request, status, error) => {
                if (request.status === 406)
                    alert("이미 회원가입된 아이디 입니다.");
            }
        })
    })
</script>
</html>