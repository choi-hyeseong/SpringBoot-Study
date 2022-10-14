<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
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
<title>게시판 상세보기</title>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<body>

<div class="container">
    <table>
        <!--tr : 행 td : 열 -->
        <tr>
            <td width="30">${board.id}</td>
            <td width="500">${board.title}</td>
            <td>${board.user.username}</td>
            <td>${board.writtenDate}</td>
        </tr>
        <tr>
            <td colspan="4" height="400">${board.text}</td>
        </tr>
    </table>
</div>

</body>
</html>