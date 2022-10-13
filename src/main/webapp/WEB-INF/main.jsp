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
            <INPUT type="button" value="글 추가" onclick="addText()">
        </tr>
        <tr>
            <p id="result">결과영역</p>
        </tr>



    </table>
</div>

</body>
<script>
    function addText() {
        var parseData = {title: "테스트", text: "테스트 내용입니다."}
        $.ajax({
            url : "http://127.0.0.1:8080/board/write",
            type: "post",
            contentType: "application/json",
            data: JSON.stringify(parseData),
            success : () => {
                document.getElementById("result").innerText = "성공"
            }
        })

    }

</script>
</html>