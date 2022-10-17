<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<style>
    table {
        width: 100%;
        border: 1px solid #444444;
        border-collapse: collapse;
    }
    th, td {
        border: 1px solid #444444;
    }
</style>
<title>게시판</title>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<body>

<div class="container">
    <table>
        <!--tr : 행 td : 열 -->
        <c:forEach var="board" items="${boardList}">
            <tr>
                <td>${board.id}</td>
                <td><a href="#"><p onclick="showDetail(${board.id})"> ${board.title}</p></a></td>
                <td>${board.text}</td>
                <td>${board.user.username}</td>
                <td>${board.writtenDate}</td>
            </tr>
        </c:forEach>
        <button name="write" type="button" value="button" onclick="location.href = 'board/write'">글 작성하기</button>
    </table>
</div>

<script>
    function showDetail(id) {
        window.location.href = "/board/detail?id=" + id;
    }
</script>

</body>
</html>