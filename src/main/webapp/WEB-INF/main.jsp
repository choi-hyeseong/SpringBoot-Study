<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<script src="http://code.jquery.com/jquery-latest.js"></script>
<body>

<div class="container">
    <table class="table table-hover table table-striped">
        <tr>
            <INPUT type="button" value="추가" onclick="addUser()">
            <INPUT type="button" value="불러오기" onclick="getUser()">
        </tr>
        <tr>
            <p id="result">결과영역</p>
        </tr>



    </table>
</div>

</body>
<script>
    function addUser() {
        $.ajax({
            url : "http://localhost:8080/user/add?name=" + document.getElementById("name").value + "&age=" + document.getElementById("age").value,
            type: "get",
            contentType: "application/x-www-form-urlencoded; charset=euc-kr",
            success : () => {
                document.getElementById("result").innerText = "성공"
            }
        })

    }
    function getUser() {
        $.ajax({
            url : "http://localhost:8080/user",
            type: "get",
            contentType: "application/x-www-form-urlencoded; charset=euc-kr",
            dataType: "text",
            success : (result) => {
                document.getElementById("result").innerText = result
            }
        })

    }
</script>
</html>